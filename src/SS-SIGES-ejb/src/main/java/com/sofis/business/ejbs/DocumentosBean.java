package com.sofis.business.ejbs;

import com.sofis.business.utils.DatosAuditoria;
import com.sofis.business.validations.DocumentosValidacion;
import com.sofis.data.daos.DocFileDao;
import com.sofis.data.daos.DocumentosDao;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.DocFile;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.data.TipoDocumentoInstancia;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.LazyInitializationException;
import org.jboss.ejb3.annotation.TransactionTimeout;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "DocumentosBean")
@LocalBean
public class DocumentosBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    @Inject
    private ProgramasBean programasBean;
    @Inject
    private ProyectosBean proyectosBean;
    @Inject
    private ConfiguracionBean configuracionBean;
    @Inject
    private EstadosBean estadosBean;
    @Inject
    private TipoDocumentoInstanciaBean tipoDocumentoInstanciaBean;
    @Inject
    private OrganismoBean organismoBean;
    @Inject
    private DatosUsuario du;

    //private String usuario;
    //private String origen;

    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public Documentos guardar(Documentos documento) throws GeneralException {

        if (documento.getDocsFecha() == null) {
            documento.setDocsFecha(new Date());
        }
        if (documento.getDocsEstado() == null) {
            documento.setDocsEstado(0d);
        }

        DocumentosValidacion.validar(documento);
        DocumentosDao docDao = new DocumentosDao(em);

        try {
            documento = docDao.update(documento, du.getCodigoUsuario(),du.getOrigen());

        } catch (BusinessException be) {
            logger.log(Level.SEVERE, be.getMessage(), be);
            throw be;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }

        return documento;
    }

    /**
     * Guarda el documento asociado a un Programa o Proyecto.
     *
     * @param documento
     * @param fichaFk
     * @param tipoFicha
     * @param usuario
     * @param orgPk
     * @return Objeto Programas o Proyectos dependiento del tipo de ficha
     * aportado.
     * @throws GeneralException
     */
    public Object guardarDocumentos(Documentos documento, Integer fichaFk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) throws GeneralException {
        documento.setDocsFecha(new Date());

        if (documento.getDocsEstado() == null) {
            documento.setDocsEstado(0d);
        }
        DocumentosValidacion.validar(documento);
        if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
            Programas prog = programasBean.obtenerProgPorId(fichaFk);
            if (prog.getDocumentosSet().contains(documento)) {
                /**
                 * BRUNO 06-03-2016: No se borra el archivo del directorio para
                 * que pueda recuperarse en el histórico.
                 */
                prog.getDocumentosSet().remove(documento);
            }
            prog.getDocumentosSet().add(documento);

            prog = programasBean.guardarPrograma(prog, usuario, orgPk);
            return prog;

        } else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
            try {
                Proyectos proy = proyectosBean.obtenerProyPorId(fichaFk);
                if (proy.getDocumentosSet().contains(documento)) {
                    proy.getDocumentosSet().remove(documento);
                }

                DocumentosDao docDAO = new DocumentosDao(em);
                DocFile df = null;
                InputStream is;
                try {
                    df = documento.getDocFile();
                    is = df.getDocfileFileStream();
                    documento = docDAO.update(documento);
                    documento.getDocFile().setDocfileFileStream(is);
                } catch (LazyInitializationException ex) {
                    df = null;
                }
                proy.getDocumentosSet().add(documento);
                proy = proyectosBean.guardarProyecto(proy, usuario, orgPk);
                if (df != null && df.getDocfileFileStream() != null) {
                    /**
                     * Si modificó el archivo
                     */
                    this.guardarArchivoDocumento(documento, orgPk);
                }
                return proy;

            } catch (DAOGeneralException ex) {
                throw new TechnicalException(ex);
            }
        }
        return null;
    }

    public void borrarArchivoDocumento(Documentos d, Integer orgPk) throws GeneralException {

        try {
            if (d.getDocFile() != null) {
                String dir = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.DOCUMENTOS_DIR, null);
                File f = new File(dir + "/" + orgPk + "/" + d.getDocFile().getDocfilePath());
                if (f.exists()) {
                    if (!f.delete()) {
                        throw new TechnicalException("No se logró borrar el archivo del documento: " + d);
                    }
                }
            }
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }

    public List<DocFile> obtenerHistoricoDocFile(Documentos d) throws GeneralException {
        try {
            DocFileDao docFileDao = new DocFileDao(em);
            return docFileDao.obtenerHistoricoDocFile(d);
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }

    public void guardarArchivoDocumento(Documentos d, Integer orgPk) throws GeneralException {

        try {

            String dir = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.DOCUMENTOS_DIR, null);
            DocFile df = d.getDocFile();
            InputStream fi = df.getDocfileFileStream();
            String name = UUID.randomUUID().toString();
            FileUtils.forceMkdir(new File(dir + "/" + orgPk));
            File f = new File(dir + "/" + orgPk + "/" + name);
            while (f.exists()) {
                name = UUID.randomUUID().toString();
                f = new File(dir + "/" + orgPk + "/" + name);
            }
            df.setDocfilePath(name);
            df.setDocfileDocFk(d);
            DocFileDao docFileDao = new DocFileDao(em);
            DatosAuditoria da = new DatosAuditoria();
            df = da.registrarDatosAuditoria(df, du.getCodigoUsuario(),du.getOrigen());
            docFileDao.update(df);
            FileOutputStream fo = new FileOutputStream(f);
            IOUtils.copy(fi, fo);
            fo.close();

        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }

    public Documentos obtenerDocumentosPorId(Integer id) {
        DocumentosDao documentosDao = new DocumentosDao(em);
        try {
            Documentos documento = documentosDao.findById(Documentos.class, id);
            return documento;

        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public List<Double> obtenerEstados() {
        List<Double> result = new ArrayList<>();
        result.add(0D);
        result.add(0.5D);
        result.add(1D);

        return result;
    }

    /**
     * Retorna una lista de Documentos para utilizar en el resumen de la ficha.
     *
     * @param documentos Lista de documentos a filtrar.
     * @param size Tamanio maximo a retornar.
     * @return Lista de Documentos
     */
    public List<Documentos> obtenerDocumentosResumen(Integer fichaFk, Integer tipoFicha, Estados est, int size) {
        logger.fine("Obtener Documentos para el resumen de la ficha.");
        DocumentosDao dao = new DocumentosDao(em);
        List<Documentos> listDocs = dao.obtenerDocumentosPorFicha(fichaFk, tipoFicha, 0);
        if (listDocs != null && !listDocs.isEmpty()) {
            Map<TipoDocumentoInstancia, Documentos> mapDocs = new HashMap<>();
            for (Documentos doc : listDocs) {
                boolean docAlerta = doc.getDocsEstado() == null || doc.getDocsEstado().equals(0d) || doc.getDocsEstado().equals(0.5d);
                boolean docEstado = doc.getDocsTipo().getTipodocExigidoDesde() < est.getEstOrdenProceso();
                if (docAlerta && docEstado
                        && (!mapDocs.containsKey(doc.getDocsTipo())
                        || DatesUtils.esMayor(doc.getDocsFecha(), mapDocs.get(doc.getDocsTipo()).getDocsFecha()))) {
                    mapDocs.put(doc.getDocsTipo(), doc);
                }
            }

            return new ArrayList<Documentos>(mapDocs.values());
        }
        return null;
    }

    /**
     * Retorna una lista de Documentos para utilizar en el resumen de la ficha.
     *
     * @param documentos Lista de documentos a filtrar.
     * @param size Tamanio maximo a retornar.
     * @return Lista de Documentos
     */
    public List<Documentos> obtenerDocumentosPorFichaPk(Integer fichaFk, Integer tipoFicha) {
        DocumentosDao dao = new DocumentosDao(em);
        List<Documentos> listDocs = dao.obtenerDocumentosPorFicha(fichaFk, tipoFicha, 0);
        return listDocs;
    }

    public void eliminarDocumento(Integer docPk, Integer fichaPk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) throws TechnicalException {
        if (docPk != null) {
            DocumentosDao dao = new DocumentosDao(em);
            try {

                Documentos documento = this.obtenerDocumentosPorId(docPk);
                if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
                    Programas p = programasBean.obtenerProgPorId(fichaPk);
                    p.getDocumentosSet().remove(documento);
                    programasBean.guardarPrograma(p, usuario, orgPk);
                } else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
                    Proyectos p = proyectosBean.obtenerProyPorId(fichaPk);
                    p.getDocumentosSet().remove(documento);
                    proyectosBean.guardarProyecto(p, usuario, orgPk);
                }

                this.borrarArchivoDocumento(documento, orgPk);
                dao.delete(documento);

            } catch (BusinessException | DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                TechnicalException te = new TechnicalException(ex);
                te.addError(ex.getMessage());
                throw te;
            }
        }
    }

    public List<Documentos> obtenerDocumentosOrderByFecha(Integer fichaFk, Integer tipoFicha) {
        DocumentosDao dao = new DocumentosDao(em);
        return dao.obtenerDocumentosOrderByFecha(fichaFk, tipoFicha);
    }

    public Double calcularIndiceEstadoMetodologiaPrograma(Set<Proyectos> proyectos) {
        if (proyectos != null && !proyectos.isEmpty()) {
            double indice = 0d;
            int cant = 0;
            for (Proyectos proy : proyectos) {
                if (proy.isActivo()) {
                    List<Documentos> listDocumentos = obtenerDocumentosOrderByFecha(proy.getProyPk(), TipoFichaEnum.PROYECTO.id);
                    Double calc = calcularIndiceEstadoMetodologiaProyecto(listDocumentos, proy.getProyPk(), proy.getProyOrgFk().getOrgPk(), proy.getProyEstFk());
                    int peso = proy.getProyPeso();
                    if (calc != null && !calc.isNaN()) {
                        indice += calc * peso;
                        cant += peso;
                    }
                }
            }
            return cant == 0 ? null : indice / cant;
        }
        return null;
    }

    public Double calcularIndiceEstadoMetodologiaProyecto(Integer proyPk, Estados estado, Integer orgPk) {
        DocumentosDao docDao = new DocumentosDao(em);
        List<Documentos> documentos = docDao.obtenerDocumentosPorFicha(proyPk, TipoFichaEnum.PROYECTO.id, 0);
        return calcularIndiceEstadoMetodologiaProyecto(documentos, proyPk, orgPk, estado);
    }

    /**
     * Calcula el indice para los documentos cargados. Se toman en cuenta todos
     * los documentos que sean exigidos anteriores a la fase en la que se
     * encuentra la ficha.
     *
     * @param listDocumentos
     * @param proyPk
     * @param orgPk
     * @param estado
     * @return double con el porcentaje.
     */
    public Double calcularIndiceEstadoMetodologiaProyecto(List<Documentos> listDocumentos, Integer proyPk, Integer orgPk, Estados estado) {
        Map<Integer, Documentos> docsActualizados = new HashMap<>();

        if (CollectionsUtils.isNotEmpty(listDocumentos) && estado != null) {
            for (Documentos doc : listDocumentos) {
                if (doc.getDocsPk() != null && doc.getDocsPk() > 0
                        && doc.getDocsTipo().getTipodocExigidoDesde() != null
                        && doc.getDocsTipo().getTipodocExigidoDesde() != 0) {
                    Estados docEstado = estadosBean.obtenerEstadosPorId(doc.getDocsTipo().getTipodocExigidoDesde());
                    if (docEstado != null && docEstado.getEstOrdenProceso() != null
                            && estado.getEstOrdenProceso() != null
                            && docEstado.getEstOrdenProceso() < estado.getEstOrdenProceso()) {
                        if (!docsActualizados.containsKey(doc.getDocsTipo().getTipodocInstPk())) {
                            docsActualizados.put(doc.getDocsTipo().getTipodocInstPk(), doc);
                        } else if (docsActualizados.get(doc.getDocsTipo().getTipodocInstPk()).getDocsPk() < doc.getDocsPk()) {
                            docsActualizados.put(doc.getDocsTipo().getTipodocInstPk(), doc);
                        }
                    }
                }
            }
        }

        List<TipoDocumentoInstancia> tiposNoContenidos = tipoDocumentoInstanciaBean.obtenerTipoDocsNoContenidosPorProyectoId(proyPk, orgPk);

        for (TipoDocumentoInstancia tdi : tiposNoContenidos) {
            if (tdi.getTipodocExigidoDesde() != null
                    && estado != null
                    && estado.getEstOrdenProceso() != null
                    && tdi.getTipodocExigidoDesde() < estado.getEstOrdenProceso()) {
                Documentos doc = new Documentos();
                doc.setDocsEstado(0d);
                doc.setDocsTipo(tdi);

                if (!docsActualizados.containsKey(tdi.getTipodocInstPk())) {
                    docsActualizados.put(tdi.getTipodocInstPk(), doc);
                }
            }
        }

        Double promedio = 0d;
        Double totalExigido = 0d;
        for (Map.Entry<Integer, Documentos> d : docsActualizados.entrySet()) {
            Documentos doc = d.getValue();
            Double estadoD = doc.getDocsEstado();
            if (estadoD == null) {
                estadoD = 0d;
            }
            Integer exigidoDesde = doc.getDocsTipo().getTipodocExigidoDesde();
            if (exigidoDesde != null && exigidoDesde != 0) {
                promedio += estadoD * doc.getDocsTipo().getTipodocInstPeso();
                totalExigido += doc.getDocsTipo().getTipodocInstPeso();
            }
        }

        Double result = promedio * 100 / totalExigido;

        return (!result.isNaN() ? result : null);
    }

    /**
     * Dado el indice de estado calcula el color del semaforo de acuerdo a los
     * parametros de la configuración.
     *
     * @param indiceEstado
     * @return String con el código del color.
     */
    public String calcularIndiceEstadoMetodologiaColor(Double indiceEstado, Integer orgPk, Map<String, String> config) {
        if (indiceEstado != null && !indiceEstado.isNaN()) {
            Double confAmarillo;
            Double confRojo;

            if (config != null) {
                String sConfAmarillo = config.get(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO);
                String sConfRojo = config.get(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_ROJO);
                try {
                    confAmarillo = Double.valueOf(sConfAmarillo);
                    confRojo = Double.valueOf(sConfRojo);
                } catch (Exception ex) {
                    confAmarillo = 0d;
                    confRojo = 0d;
                }
            } else {
                String[] codigo = new String[]{
                    ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO,
                    ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_ROJO};
                Map<String, Configuracion> mapConf = configuracionBean.obtenerCnfPorCodigoYOrg(orgPk, codigo);
                try {
                    confAmarillo = Double.valueOf(mapConf.get(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO).getCnfValor());
                    confRojo = Double.valueOf(mapConf.get(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_ROJO).getCnfValor());
                } catch (Exception ex) {
                    confAmarillo = 0d;
                    confRojo = 0d;
                }
            }

            if (confAmarillo != null && confRojo != null) {
                if (indiceEstado < confAmarillo) {
                    return ConstantesEstandares.SEMAFORO_ROJO;
                }
                if (indiceEstado > confAmarillo && indiceEstado < confRojo) {
                    return ConstantesEstandares.SEMAFORO_AMARILLO;
                }
                if (indiceEstado > confRojo) {
                    return ConstantesEstandares.SEMAFORO_VERDE;
                }
            } else {
                return ConstantesEstandares.COLOR_TRANSPARENT;
            }
        }

        return ConstantesEstandares.COLOR_TRANSPARENT;
    }

    public Boolean calcularIndiceMetodologiaSinAprobarProgramas(Integer progPk) {
        DocumentosDao docDao = new DocumentosDao(em);
        return docDao.metodologiaSinAprobarProgramas(progPk);
    }

    public Boolean calcularIndiceMetodologiaSinAprobar(Integer proyPk) {
        DocumentosDao docDao = new DocumentosDao(em);
        return docDao.metodologiaSinAprobar(proyPk);
    }

    /**
     * Carga en el documento el color de acuerdo al estado que tenga.
     *
     * @param d
     * @param est
     * @return String
     */
    public String obtenerEstadosColor(Documentos d, Estados est) {
        if (d != null) {
            Estados exigidoDesde = estadosBean.obtenerEstadosPorId(d.getDocsTipo().getTipodocExigidoDesde());
            if (est != null && exigidoDesde != null
                    && exigidoDesde.getEstOrdenProceso() < est.getEstOrdenProceso()
                    && d.getDocsEstado() == null) {
                d.setDocsEstadoColor(ConstantesEstandares.SEMAFORO_ROJO);
            } else {
                d.setDocsEstadoColor(docEstadoColor(d.getDocsEstado()));
            }
            return d.getDocsEstadoColor();
        }
        return null;
    }

    public Documentos obtenerResumenEjecutivo(Integer fichaPk, TipoFichaEnum tipoFicha) {
        DocumentosDao dao = new DocumentosDao(em);
        return dao.obtenerResumenEjecutivo(fichaPk, tipoFicha);
    }

    public Documentos obtenerUltimoDocPorTipoDocInst(Integer tdiPk) {
        DocumentosDao dao = new DocumentosDao(em);
        return dao.obtenerUltimoDocPorTipoDocInst(tdiPk);
    }

    public void obtenerEstadosColorTipoDocInst(TipoDocumentoInstancia tDoc, Estados est) {
        Documentos d = obtenerUltimoDocPorTipoDocInst(tDoc.getTipodocInstPk());
        if (d != null) {
            tDoc.setDocsEstadoColor(obtenerEstadosColor(d, est));
        } else {
            Estados exigidoDesde = estadosBean.obtenerEstadosPorId(tDoc.getTipodocExigidoDesde());
            if (exigidoDesde != null && est != null
                    && exigidoDesde.getEstOrdenProceso() < est.getEstOrdenProceso()
                    && tDoc.getDocsEstado() == null) {
                tDoc.setDocsEstadoColor(ConstantesEstandares.SEMAFORO_ROJO);
            } else {
                tDoc.setDocsEstadoColor(docEstadoColor(tDoc.getDocsEstado()));
            }
        }
    }

    private String docEstadoColor(Double d) {
        if (d.doubleValue() == 0d) {
            return ConstantesEstandares.SEMAFORO_ROJO;
        } else if (d.doubleValue() == 0.5d) {
            return ConstantesEstandares.SEMAFORO_AMARILLO;
        } else if (d.doubleValue() == 1d) {
            return ConstantesEstandares.SEMAFORO_VERDE;
        } else {
            return ConstantesEstandares.COLOR_TRANSPARENT;
        }
    }

    public List<Documentos> cargarArchivosDocumentos(List<Documentos> listDocumentos) {
        if (CollectionsUtils.isNotEmpty(listDocumentos)) {
            for (Documentos doc : listDocumentos) {
                doc = cargarArchivosDocumentos(doc);
            }
        }
        return listDocumentos;
    }

    public Documentos cargarArchivosDocumentos(Documentos doc) {
        if (doc != null && doc.getDocsPk() != null) {
            doc.setDocFile(obtenerDocFilePorDocId(doc.getDocsPk()));
        }
        return doc;
    }

    public List<DocFile> obtenerDocFilePorDocId(List<Documentos> listDocs) {
        if (listDocs != null && !listDocs.isEmpty()) {
            List<DocFile> result = new ArrayList<>();
            for (Documentos doc : listDocs) {
                result.add(obtenerDocFilePorDocId(doc.getDocsPk()));
            }
            return result;
        }
        return null;
    }

    public DocFile obtenerDocFilePorDocId(Integer docPk) {
        if (docPk != null) {
            DocFileDao daoFile = new DocFileDao(em);
            List<DocFile> listDocFile = null;
            try {
                listDocFile = daoFile.findByOneProperty(DocFile.class, "docfileDocFk.docsPk", docPk);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_DOCS_FILE_OBTENER);
                throw be;
            }

            if (listDocFile != null && !listDocFile.isEmpty()
                    && listDocFile.get(0) != null) {
                return listDocFile.get(0);
            }
        }
        return null;
    }

    /**
     * Quita el entregable aportado a los documentos que lo tengan asociado.
     *
     * @param entPk
     */
    public void quitarEntregable(Integer entPk) {
        DocumentosDao dao = new DocumentosDao(em);
        try {
            dao.quitarEntregable(entPk);
        } catch (GeneralException e) {
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_DOC);
            throw be;
        }
    }

    /**
     * Mueve todos los documentos de todos los organismos en la DB hacia el FS.
     * Una vez movidos se eliminaran de la DB.
     *
     * @return
     */
    @TransactionTimeout(value = 240, unit = TimeUnit.MINUTES)
    public String moverArchivosDocToFS() {

        //Obtener la ruta de ss_configuracion "DOCUMENTOS_DIR"
        Configuracion confDocDir = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.DOCUMENTOS_DIR, null);

        if (confDocDir == null || confDocDir.getCnfDescripcion() == null || confDocDir.getCnfDescripcion().trim() == "") {
            //Si no está, tienen que crearla y retorna error.
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_CONFIG_OBTENER_DOC_DIR);
            throw be;
        }

        String dir = confDocDir.getCnfValor();

        //Borrar los archivos de la carpeta si es que se hizo una migración fallida.
//		String path = dir + File.separator + org.getOrgPk();
        File file = new File(dir);
        try {
            FileUtils.forceMkdir(file);
            FileUtils.cleanDirectory(file);
        } catch (IOException iOException) {
            logger.log(Level.SEVERE, "Error al crear o vaciar el directorio: {0}", dir);
        }

        DocFileDao docFileDao = new DocFileDao(em);
        docFileDao.removerAuditoria();

        //Obtener todos los Org.
        List<Organismos> listOrg = organismoBean.obtenerTodos();
        if (listOrg != null && !listOrg.isEmpty()) {
            DatosAuditoria da = new DatosAuditoria();

            //Recorrer todos los Org.
            for (Organismos org : listOrg) {
                logger.log(Level.INFO, "*** Procesando Organismo {0}-{1}", new Object[]{org.getOrgPk(), org.getOrgNombre()});

                //Obtener todos los Prog. por organismo y copiar los doc al FS.
                List<Programas> listProg = programasBean.obtenerProgPorOrganismo(org.getOrgPk());
                if (listProg != null && !listProg.isEmpty()) {

                    int countProg = 0;
                    int totalProg = listProg.size();
                    for (Programas prog : listProg) {
                        logger.log(Level.INFO, "Procesando Organismo {0}-Programa {1} ({2}/{3})", new Object[]{org.getOrgPk(), prog.getProgPk(), ++countProg, totalProg});
                        //Obtener todos los file por Prog.
                        List<DocFile> listDocFile = docFileDao.obtenerDocFilePorProg(prog.getProgPk());
                        if (listDocFile != null && !listDocFile.isEmpty()) {
                            for (DocFile docFile : listDocFile) {

                                byte[] fileDoc = docFileDao.obtenerDocFile(docFile.getDocfilePk());

                                if (fileDoc != null) {
                                    InputStream fi = new ByteArrayInputStream(fileDoc);
                                    String nameFile = saveFile(dir, org.getOrgPk(), fi);

                                    docFile.setDocfilePath(nameFile);
                                    docFile = da.registrarDatosAuditoria(docFile, du.getCodigoUsuario(),du.getOrigen());
                                    try {
                                        docFileDao.update(docFile);
                                    } catch (DAOGeneralException ex) {
                                        logger.log(Level.SEVERE, "Error al guardar DocFile.", ex);
                                        BusinessException be = new BusinessException();
                                        be.addError(MensajesNegocio.ERROR_DOCS_GUARDAR);
                                        throw be;
                                    }
                                }
                            }
                        }
                    }
                }

                //Obtener todos los Proy. por organismo y copiar los doc al FS
                List<Integer> listProy = proyectosBean.obtenerIdsProyPorOrg(org.getOrgPk());
                if (listProy != null && !listProy.isEmpty()) {

                    int countProy = 0;
                    int totalProy = listProy.size();
                    for (Integer proyPk : listProy) {
                        logger.log(Level.INFO, "Procesando Organismo {0}-Proyecto {1} ({2}/{3})", new Object[]{org.getOrgPk(), proyPk, ++countProy, totalProy});
                        //Obtener todos los file por Prog.
                        List<DocFile> listDocFile = docFileDao.obtenerDocFilePorProy(proyPk);
                        if (listDocFile != null && !listDocFile.isEmpty()) {
                            for (DocFile docFile : listDocFile) {

                                byte[] fileDoc = docFileDao.obtenerDocFile(docFile.getDocfilePk());

                                if (fileDoc != null) {
                                    InputStream fi = new ByteArrayInputStream(fileDoc);
                                    String nameFile = saveFile(dir, org.getOrgPk(), fi);

                                    docFile.setDocfilePath(nameFile);
                                    docFile = da.registrarDatosAuditoria(docFile, du.getCodigoUsuario(),du.getOrigen());
                                    try {
                                        docFileDao.update(docFile);
                                    } catch (DAOGeneralException ex) {
                                        logger.log(Level.SEVERE, "Error al guardar DocFile.", ex);
                                        BusinessException be = new BusinessException();
                                        be.addError(MensajesNegocio.ERROR_DOCS_GUARDAR);
                                        throw be;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //Cuando se hayan procesado todos los archivos correctamente, se deben eliminar los mismos de la DB.
//	    docFileDao.eliminarDocFiles();

        }

        return null;
    }

    private String saveFile(String dir, Integer orgPk, InputStream fi) {

        FileOutputStream fo = null;
        try {
//            String name = RandomStringUtils.randomAlphanumeric(8);
            String name = UUID.randomUUID().toString();
            FileUtils.forceMkdir(new File(dir + File.separator + orgPk));
            File f = new File(dir + File.separator + orgPk + File.separator + name);
            while (f.exists()) {
                name = UUID.randomUUID().toString();
                f = new File(dir + File.separator + orgPk + File.separator + name);
            }
//	    logger.log(Level.INFO, "Guardando archivo {0}", name);
            fo = new FileOutputStream(f);
            IOUtils.copy(fi, fo);
            return name;
        } catch (IOException iOException) {
            BusinessException be = new BusinessException(iOException);
            be.addError(MensajesNegocio.ERROR_DOCS_MOVER_TO_FS);
            throw be;
        } finally {
            if (fo != null) {
                try {
                    fo.close();
                } catch (IOException ex) {
                    Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Boolean procesarMoverDocFS() {
        DocFileDao docFileDao = new DocFileDao(em);
        return docFileDao.procesarMoverDocFS();
    }
}
