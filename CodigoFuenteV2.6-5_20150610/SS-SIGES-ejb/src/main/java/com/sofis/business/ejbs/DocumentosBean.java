package com.sofis.business.ejbs;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    private DatosUsuario du;
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
            documento = docDao.update(documento, du.getCodigoUsuario(), du.getOrigen());

        } catch (BusinessException be) {
            logger.log(Level.SEVERE, be.getMessage(), be);
            throw be;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException();
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
                prog.getDocumentosSet().remove(documento);
            }
            prog.getDocumentosSet().add(documento);
            prog = programasBean.guardarPrograma(prog, usuario, orgPk);
            return prog;

        } else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
            Proyectos proy = proyectosBean.obtenerProyPorId(fichaFk);

            if (proy.getDocumentosSet().contains(documento)) {
                proy.getDocumentosSet().remove(documento);
            }
            proy.getDocumentosSet().add(documento);
            proy = proyectosBean.guardarProyecto(proy, usuario, orgPk);
            return proy;
        }

        return null;
    }

    public Documentos obtenerDocumentosPorId(Integer id) {
        DocumentosDao documentosDao = new DocumentosDao(em);
        try {
            return documentosDao.findById(Documentos.class, id);

        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException te = new TechnicalException();
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
        logger.fine("Obtener Documentos de la ficha.");
        DocumentosDao dao = new DocumentosDao(em);
        List<Documentos> listDocs = dao.obtenerDocumentosPorFicha(fichaFk, tipoFicha, 0);
        return listDocs;
    }

    public void eliminarDocumento(Integer docPk, Integer fichaPk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) throws TechnicalException {
        logger.info("Eliminar el Documento con id:" + docPk);
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

            dao.delete(documento);

        } catch (BusinessException | DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException te = new TechnicalException();
            te.addError(ex.getMessage());
            throw te;
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
                        } else {
                            if (docsActualizados.get(doc.getDocsTipo().getTipodocInstPk()).getDocsPk() < doc.getDocsPk()) {
                                docsActualizados.put(doc.getDocsTipo().getTipodocInstPk(), doc);
                            }
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
                Configuracion cConfAmarillo = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO, orgPk);
                Configuracion cConfRojo = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_ROJO, orgPk);
                try {
                    confAmarillo = Double.valueOf(cConfAmarillo.getCnfValor());
                    confRojo = Double.valueOf(cConfRojo.getCnfValor());
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
}
