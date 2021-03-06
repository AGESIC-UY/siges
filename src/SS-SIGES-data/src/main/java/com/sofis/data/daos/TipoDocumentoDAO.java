package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.TipoDocumento;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class TipoDocumentoDAO extends HibernateJpaDAOImp<TipoDocumento, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(TipoDocumentoDAO.class.getName()); 

    public TipoDocumentoDAO(EntityManager em) {
        super(em);
    }

    public List<TipoDocumento> obtenerTipoDocsNoContenidosPorProgramaId(Integer progId, Integer orgId) throws DAOGeneralException {
        try {
            String query = "SELECT d FROM TipoDocumento d "
                    + " WHERE d.tipoDocOrgFk.orgPk =:orgPk "
                    + " AND d.tipdocPk not "
                    + " IN (SELECT DISTINCT d.docsTipo.tipdocPk "
                    + " FROM Programas t, IN(t.documentosSet) d "
                    + " WHERE t.progPk = :progId)";
            Query q = super.getEm().createQuery(query);
            q.setParameter("orgPk", orgId);
            q.setParameter("progId", progId);
            return q.getResultList();
        } catch (Exception w) {
            w.printStackTrace();
            throw new DAOGeneralException(w);
        }
    }

    public List<TipoDocumento> obtenerTipoDocsNoContenidosPorProyectoId(Integer proyId, Integer orgId) throws DAOGeneralException {
        try {
            String query = "SELECT d FROM TipoDocumento d"
                    + " WHERE d.tipoDocOrgFk.orgPk =:orgPk"
                    + " AND d.tipdocPk NOT IN (SELECT DISTINCT d.docsTipo.tipdocPk"
                    + " FROM Proyectos t, IN(t.documentosSet) d"
                    + " WHERE t.proyPk = :proyId)";
            Query q = super.getEm().createQuery(query);
            q.setParameter("orgPk", orgId);
            q.setParameter("proyId", proyId);
            return q.getResultList();
        } catch (Exception w) {
            w.printStackTrace();
            throw new DAOGeneralException(w);
        }
    }
}
