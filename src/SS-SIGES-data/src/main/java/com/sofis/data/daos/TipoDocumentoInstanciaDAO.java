package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.TipoDocumento;
import com.sofis.entities.data.TipoDocumentoInstancia;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class TipoDocumentoInstanciaDAO extends HibernateJpaDAOImp<TipoDocumentoInstancia, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(TipoDocumentoInstanciaDAO.class.getName()); 

    public TipoDocumentoInstanciaDAO(EntityManager em) {
        super(em);
    }

    public List<TipoDocumentoInstancia> obtenerTipoDocsInstanciaPorFichaPk(Integer fichaPk, Integer tipoFicha) throws DAOGeneralException {
        if (fichaPk != null && tipoFicha != null) {
            if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
                return obtenerTipoDocsInstanciaPorProgramaId(fichaPk);
            } else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
                return obtenerTipoDocsInstanciaPorProyectoId(fichaPk);
            }
        }
        return null;
    }

    public List<TipoDocumentoInstancia> obtenerTipoDocsInstanciaPorProgProyId(Integer fichaPk, Integer tipoFicha) throws DAOGeneralException {
        if (fichaPk != null && tipoFicha != null) {
            if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
                return obtenerTipoDocsInstanciaPorProgramaId(fichaPk);
            } else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
                return obtenerTipoDocsInstanciaPorProyectoId(fichaPk);
            }
        }
        return null;
    }

    public List<TipoDocumentoInstancia> obtenerTipoDocsInstanciaPorProyectoId(Integer proyId) throws DAOGeneralException {
        if (proyId != null) {
            try {
                String query = "SELECT d FROM TipoDocumentoInstancia d "
                        + " WHERE d.tipodocInstProyFk = :proyId";
                Query q = super.getEm().createQuery(query);
                q.setParameter("proyId", proyId);
                return q.getResultList();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                TechnicalException te = new TechnicalException(ex);
                te.addError(MensajesNegocio.ERROR_TIPO_DOC_INST_OBTENER);
                throw te;
            }
        }
        return null;
    }

    public List<TipoDocumentoInstancia> obtenerTipoDocsInstanciaPorProgramaId(Integer progId) throws DAOGeneralException {
        if (progId != null) {
            try {
                String query = "SELECT d FROM TipoDocumentoInstancia d "
                        + " WHERE d.tipodocInstProgFk = :progId";
                Query q = super.getEm().createQuery(query);
                q.setParameter("progId", progId);
                return q.getResultList();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
                throw new DAOGeneralException(ex);
            }
        }
        return null;
    }

    public List<TipoDocumento> obtenerTipoDocsSinInstanciaPorProgProyId(Integer fichaPk, Integer tipoFicha, Integer orgId) throws DAOGeneralException {
        if (fichaPk != null && tipoFicha != null) {
            if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
                return obtenerTipoDocsSinInstanciaPorProgramaId(fichaPk, orgId);
            } else {
                return obtenerTipoDocsSinInstanciaPorProyectoId(fichaPk, orgId);
            }
        }
        return null;
    }

    /**
     *
     * @param proyId
     * @param orgId
     * @return
     * @throws DAOGeneralException
     */
    public List<TipoDocumento> obtenerTipoDocsSinInstanciaPorProyectoId(Integer proyId, Integer orgId) throws DAOGeneralException {
        try {
            String query = "SELECT d FROM TipoDocumento d "
                    + " WHERE d.tipoDocOrgFk.orgPk = :orgPk "
                    + " AND d.tipdocPk NOT IN ( "
                    + " SELECT t.tipodocInstTipoDocFk.tipdocPk "
                    + " FROM TipoDocumentoInstancia t "
                    + " WHERE t.tipodocInstProyFk = :proyId )";
            Query q = super.getEm().createQuery(query);
            q.setParameter("proyId", proyId);
            q.setParameter("orgPk", orgId);
            return q.getResultList();
        } catch (Exception w) {
            logger.log(Level.SEVERE, null, w);
            throw new DAOGeneralException(w);
        }
    }

    /**
     *
     * @param progId
     * @param orgId
     * @return
     * @throws DAOGeneralException
     */
    public List<TipoDocumento> obtenerTipoDocsSinInstanciaPorProgramaId(Integer progId, Integer orgId) throws DAOGeneralException {
        try {
            String query = "select d from TipoDocumento d where d.tipoDocOrgFk.orgPk = :orgPk and  d.tipdocPk not in ( select t.tipodocInstTipoDocFk.tipdocPk from TipoDocumentoInstancia t where t.tipodocInstProgFk = :progId )";
            Query q = super.getEm().createQuery(query);
            q.setParameter("progId", progId);
            q.setParameter("orgPk", orgId);
            return q.getResultList();
        } catch (Exception w) {
            logger.log(Level.SEVERE, null, w);
            throw new DAOGeneralException(w);
        }
    }

    public List<TipoDocumentoInstancia> obtenerTipoDocsNoContenidosPorProgramaId(Integer progId, Integer orgId) throws DAOGeneralException {
        try {
            String query = "select d from TipoDocumentoInstancia d where d.tipodocInstProyFk =:progId and d.tipodocInstTipoDocFk.tipoDocOrgFk.orgPk =:orgPk and d.tipodocInstPk not in (select distinct d.docsTipo.tipodocInstPk from Programas t, IN(t.documentosSet) d where t.progPk = :progId)";
            Query q = super.getEm().createQuery(query);
            q.setParameter("orgPk", orgId);
            q.setParameter("progId", progId);
            return q.getResultList();
        } catch (Exception w) {
            logger.log(Level.SEVERE, null, w);
            throw new DAOGeneralException(w);
        }
    }

    public List<TipoDocumentoInstancia> obtenerTipoDocsNoContenidosPorProyectoId(Integer proyId, Integer orgId) throws DAOGeneralException {
        try {
            String query = "SELECT d FROM TipoDocumentoInstancia d"
                    + " WHERE d.tipodocInstProyFk =:proyId "
                    + " AND d.tipodocInstTipoDocFk.tipoDocOrgFk.orgPk =:orgPk"
                    + " AND d.tipodocInstPk NOT IN ("
                    + " SELECT DISTINCT d.docsTipo.tipodocInstPk "
                    + " FROM Proyectos t, IN(t.documentosSet) d "
                    + " WHERE t.proyPk = :proyId)";
            Query q = super.getEm().createQuery(query);
            q.setParameter("orgPk", orgId);
            q.setParameter("proyId", proyId);
            return q.getResultList();
        } catch (Exception w) {
            logger.log(Level.SEVERE, null, w);
            throw new DAOGeneralException(w);
        }
    }

    public List<TipoDocumentoInstancia> obtenerTipoDocsNoContenidosPorProyectoId(Integer proyId) throws DAOGeneralException {
        try {
            String query = "SELECT d FROM TipoDocumentoInstancia d"
                    + " WHERE d.tipodocInstProyFk = :proyId "
                    + " AND d.tipodocInstPk NOT IN ("
                    + " SELECT DISTINCT d.docsTipo.tipodocInstPk "
                    + " FROM Proyectos t, IN(t.documentosSet) d "
                    + " WHERE t.proyPk = :proyId)";
            Query q = super.getEm().createQuery(query);
            q.setParameter("proyId", proyId);
            return q.getResultList();
        } catch (Exception w) {
            logger.log(Level.SEVERE, null, w);
            throw new DAOGeneralException(w);
        }
    }

    public void eliminarPorProyectoId(Integer proyPk) throws DAOGeneralException {
//        List<TipoDocumentoInstancia> listaInstancia = obtenerTipoDocsNoContenidosPorProyectoId(proyPk);
        List<TipoDocumentoInstancia> listaInstancia = obtenerTipoDocsInstanciaPorProyectoId(proyPk);
        if (listaInstancia != null) {
            for (TipoDocumentoInstancia d : listaInstancia) {
                super.getEm().remove(d);
            }
        }
    }
    
    public void eliminar(Integer tdiPk) throws DAOGeneralException {
        TipoDocumentoInstancia tdi = this.findById(TipoDocumentoInstancia.class, tdiPk);
        if (tdi != null) {
                super.getEm().remove(tdi);
        }
    }
}
