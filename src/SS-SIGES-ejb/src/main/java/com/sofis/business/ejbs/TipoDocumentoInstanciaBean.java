package com.sofis.business.ejbs;

import com.sofis.business.utils.TipoDocumentoInstanciaUtils;
import com.sofis.data.daos.TipoDocumentoDAO;
import com.sofis.data.daos.TipoDocumentoInstanciaDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.TipoDocumento;
import com.sofis.entities.data.TipoDocumentoInstancia;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
@Stateless(name = "TipoDocumentoInstanciaBean")
@LocalBean
public class TipoDocumentoInstanciaBean {

    private static final Logger logger = Logger.getLogger(TipoDocumentoInstanciaBean.class.getName());
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    @Inject
    private EstadosBean estadosBean;
    @EJB
    private DocumentosBean documentosBean;

    public List<TipoDocumentoInstancia> guardarTiposDocsIntancia(List<TipoDocumentoInstancia> instancias) throws GeneralException {

        TipoDocumentoInstanciaDAO tipoDocInstanciaDao = new TipoDocumentoInstanciaDAO(em);
        List<TipoDocumentoInstancia> result = new ArrayList<>();
        try {
            for (TipoDocumentoInstancia t : instancias) {
                if (t.getTipodocExigidoDesde() != null && t.getTipodocExigidoDesde().equals(0)) {
                    t.setTipodocExigidoDesde(null);
                }
                t = tipoDocInstanciaDao.update(t);
                result.add(t);
            }

            return result;
        } catch (DAOGeneralException ex) {
            logger.logp(Level.SEVERE, TipoDocumentoInstanciaBean.class.getName(), "guardar", ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    /**
     * Guarda una copia de los TipoDocumentoInstancia del proyecto aportado para
     * el nuevo proyecto.
     *
     * @param proyPk
     * @param orgPk
     * @return
     */
    public List<TipoDocumentoInstancia> guardarCopiaTDIProyecto(Integer proyPk, Integer nvoProyPk, Integer orgPk) {
        List<TipoDocumentoInstancia> tdiList = obtenerTipoDocsInstanciaPorProgProyId(proyPk, TipoFichaEnum.PROYECTO.id, orgPk);
        List<TipoDocumentoInstancia> tdiListResult = new ArrayList<>();
        for (TipoDocumentoInstancia tdi : tdiList) {
            TipoDocumentoInstancia nvaTDI = new TipoDocumentoInstancia();
            nvaTDI.setDocsEstado(tdi.getDocsEstado());
            nvaTDI.setTipodocExigidoDesde(tdi.getTipodocExigidoDesde());
            nvaTDI.setTipodocInstPeso(tdi.getTipodocInstPeso());
            nvaTDI.setTipodocInstProgFk(null);
            nvaTDI.setTipodocInstProyFk(nvoProyPk);
            nvaTDI.setTipodocInstResumenEjecutivo(tdi.getTipodocInstResumenEjecutivo());
            nvaTDI.setTipodocInstTipoDocFk(tdi.getTipodocInstTipoDocFk());
            tdiListResult.add(nvaTDI);
        }

        return guardarTiposDocsIntancia(tdiListResult);
    }
    
    /**
     * Guarda una copia de los TipoDocumentoInstancia del programa aportado para
     * el nuevo programa.
     *
     * @param progPk
     * @param orgPk
     * @return
     */
    public List<TipoDocumentoInstancia> guardarCopiaTDIPrograma(Integer progPk, Integer nvoProgPk, Integer orgPk) {
        List<TipoDocumentoInstancia> tdiList = obtenerTipoDocsInstanciaPorProgProyId(progPk, TipoFichaEnum.PROGRAMA.id, orgPk);
        List<TipoDocumentoInstancia> tdiListResult = new ArrayList<>();
        for (TipoDocumentoInstancia tdi : tdiList) {
            TipoDocumentoInstancia nvaTDI = new TipoDocumentoInstancia();
            nvaTDI.setDocsEstado(tdi.getDocsEstado());
            nvaTDI.setTipodocExigidoDesde(tdi.getTipodocExigidoDesde());
            nvaTDI.setTipodocInstPeso(tdi.getTipodocInstPeso());
            nvaTDI.setTipodocInstProgFk(nvoProgPk);
            nvaTDI.setTipodocInstProyFk(null);
            nvaTDI.setTipodocInstResumenEjecutivo(tdi.getTipodocInstResumenEjecutivo());
            nvaTDI.setTipodocInstTipoDocFk(tdi.getTipodocInstTipoDocFk());
            tdiListResult.add(nvaTDI);
        }

        return guardarTiposDocsIntancia(tdiListResult);
    }

    public List<TipoDocumentoInstancia> obtenerTipoDocInstResumen(Integer fichaFk, Integer tipoFicha, Estados est, int size) {

        if (fichaFk != null && tipoFicha != null) {
            TipoDocumentoInstanciaDAO tipoDocInstanciaDao = new TipoDocumentoInstanciaDAO(em);
            List<TipoDocumentoInstancia> tipoDocInst = null;
            try {
                tipoDocInst = tipoDocInstanciaDao.obtenerTipoDocsInstanciaPorFichaPk(fichaFk, tipoFicha);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
            }

            if (tipoDocInst != null) {
                List<TipoDocumentoInstancia> result = new ArrayList<>();
                for (TipoDocumentoInstancia tdi : tipoDocInst) {
                    Estados estTdi = estadosBean.obtenerEstadosPorId(tdi.getTipodocExigidoDesde());
                    if (estadosBean.isOrdenProcesoMenor(estTdi, est)) {
                        Documentos doc = documentosBean.obtenerUltimoDocPorTipoDocInst(tdi.getTipodocInstPk());
                        if (doc == null || (doc.getDocsEstado() == null
                                || doc.getDocsEstado().equals(0d) || doc.getDocsEstado().equals(0.5d))) {
                            
                            tdi.setDocsEstado(doc != null ? doc.getDocsEstado() : null);
                            result.add(tdi);
                            size--;
                            if (size == 0) {
                                break;
                            }
                            
                        }
                    }
                }
                result = TipoDocumentoInstanciaUtils.sortByDocumentosEstado(result, true);
                return result;
            }
        }
        return null;
    }

    /**
     * Genera el TipoDocumentoInstancia si no existe para dicho prog/proy. En
     * caso de que exista, controla que no falte algún tipo de documento.
     *
     * @param fichaId
     * @param tipoFicha
     * @param orgPk
     */
    private void copiarTDIFaltantes(Integer fichaId, Integer tipoFicha, Integer orgPk) {
        TipoDocumentoDAO tipoDocDao = new TipoDocumentoDAO(em);
        TipoDocumentoInstanciaDAO tipoDocInstanciaDao = new TipoDocumentoInstanciaDAO(em);

        try {
            List<TipoDocumentoInstancia> tiposDocumentosInstanciaList = tipoDocInstanciaDao.obtenerTipoDocsInstanciaPorProgProyId(fichaId, tipoFicha);

            if (tiposDocumentosInstanciaList == null) {
                //debemos cargar la instancia de tipos de documentos con los valores por defecto
                //en la base de tipos de documentos para la organizacion del proyecto
                List<TipoDocumento> tiposDocumentos = tipoDocDao.findByOneProperty(TipoDocumento.class, "tipoDocOrgFk.orgPk", orgPk);
                for (TipoDocumento t : tiposDocumentos) {
                    TipoDocumentoInstancia toInsert = new TipoDocumentoInstancia();
                    toInsert.setTipodocExigidoDesde(t.getTipodocExigidoDesde());
                    toInsert.setTipodocInstPeso(t.getTipodocPeso());
                    toInsert.setTipodocInstTipoDocFk(t);
                    if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
                        toInsert.setTipodocInstProgFk(fichaId);
                    } else {
                        toInsert.setTipodocInstProyFk(fichaId);
                    }
                    tipoDocInstanciaDao.update(toInsert);
                }
            } else {
                //ya existe la instancia de los tipos de doc, pero
                //se puede agregar algun nuevo tipo de documento que no se tiene la 
                //instancia por lo tanto se verifica y si es necesario se crea,
                //a validar que crear esta intancia no dependa del estado del proyecto
                //es decir si por ejemplo el proyecto ya esta en estado Iniciado si se tiene que crear la instancia
                List<TipoDocumento> tiposDocumentos = tipoDocInstanciaDao.obtenerTipoDocsSinInstanciaPorProgProyId(fichaId, tipoFicha, orgPk);

                for (TipoDocumento t : tiposDocumentos) {
                    TipoDocumentoInstancia toInsert = new TipoDocumentoInstancia();
                    toInsert.setTipodocExigidoDesde(t.getTipodocExigidoDesde());
                    toInsert.setTipodocInstPeso(t.getTipodocPeso());
                    toInsert.setTipodocInstTipoDocFk(t);
                    if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
                        toInsert.setTipodocInstProgFk(fichaId);
                    } else {
                        toInsert.setTipodocInstProyFk(fichaId);
                    }
                    tipoDocInstanciaDao.update(toInsert);
                }
            }
        } catch (DAOGeneralException ex) {
            logger.logp(Level.SEVERE, TipoDocumentoInstanciaBean.class.getName(), "obtenerTipoDocsNoContenidosPorProyectoId", ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public List<TipoDocumentoInstancia> obtenerTipoDocsInstanciaPorProgProyId(Integer fichaId, Integer tipoFicha, Integer orgPk) {
        TipoDocumentoInstanciaDAO tipoDocInstanciaDao = new TipoDocumentoInstanciaDAO(em);
        try {
            copiarTDIFaltantes(fichaId, tipoFicha, orgPk);
            List<TipoDocumentoInstancia> tiposDocumentosInstanciaList = tipoDocInstanciaDao.obtenerTipoDocsInstanciaPorProgProyId(fichaId, tipoFicha);
            return tiposDocumentosInstanciaList;
        } catch (DAOGeneralException ex) {
            logger.logp(Level.SEVERE, TipoDocumentoInstanciaBean.class.getName(), "obtenerTipoDocsNoContenidosPorProyectoId", ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public List<TipoDocumentoInstancia> obtenerTiposDocumentoInstanciaPorProyecto(Integer proyId) {
        TipoDocumentoInstanciaDAO tipoDocInstanciaDao = new TipoDocumentoInstanciaDAO(em);
        try {
            List<TipoDocumentoInstancia> tiposDocumentos = tipoDocInstanciaDao.findByOneProperty(TipoDocumentoInstancia.class, "tipodocInstProyFk", proyId, "tipodocInstTipoDocFk.tipodocNombre");
            return tiposDocumentos;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.logp(Level.SEVERE, TipoDocumentoInstanciaBean.class.getName(), "obtenerTiposDocumentoInstanciaPorProyecto", ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public List<TipoDocumentoInstancia> obtenerTipoDocsNoContenidosPorProyectoId(Integer proyId, Integer orgPk) {
        TipoDocumentoInstanciaDAO tipoDocInstanciaDao = new TipoDocumentoInstanciaDAO(em);
        try {
            return tipoDocInstanciaDao.obtenerTipoDocsNoContenidosPorProyectoId(proyId, orgPk);

        } catch (DAOGeneralException ex) {
            logger.logp(Level.SEVERE, TipoDocumentoInstanciaBean.class.getName(), "obtenerTipoDocsNoContenidosPorProyectoId", ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public List<TipoDocumentoInstancia> obtenerTipoDocsNoContenidosPorProgramaId(Integer progId, Integer orgPk) {
        TipoDocumentoInstanciaDAO tipoDocInstanciaDao = new TipoDocumentoInstanciaDAO(em);
        try {
            return tipoDocInstanciaDao.obtenerTipoDocsNoContenidosPorProgramaId(progId, orgPk);
        } catch (DAOGeneralException ex) {
            logger.logp(Level.SEVERE, TipoDocumentoInstanciaBean.class.getName(), "obtenerTipoDocsNoContenidosPorProgramaId", ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public void eliminarPorProyectoId(Integer proyPk) {
        TipoDocumentoInstanciaDAO tipoDocInstanciaDao = new TipoDocumentoInstanciaDAO(em);
        try {
            tipoDocInstanciaDao.eliminarPorProyectoId(proyPk);
        } catch (DAOGeneralException ex) {
            logger.logp(Level.SEVERE, TipoDocumentoInstanciaBean.class.getName(), "eliminarPorProyectoId", ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public void eliminar(List<TipoDocumentoInstancia> listTDI) {
        if (listTDI != null) {
            TipoDocumentoInstanciaDAO dao = new TipoDocumentoInstanciaDAO(em);
            for (TipoDocumentoInstancia tdi : listTDI) {
                try {
                    dao.eliminar(tdi.getTipodocInstPk());
                } catch (DAOGeneralException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
