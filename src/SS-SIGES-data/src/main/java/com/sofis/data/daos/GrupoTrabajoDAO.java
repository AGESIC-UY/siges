package com.sofis.data.daos;

import com.sofis.entities.data.GrupoTrabajo;
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
public class GrupoTrabajoDAO extends HibernateJpaDAOImp<GrupoTrabajo, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(GrupoTrabajoDAO.class.getName());        

    public GrupoTrabajoDAO(EntityManager em) {
        super(em);
    }

    
    public List<GrupoTrabajo> obtenerConfSinOrg(Integer orgPk) {
            String queryStr = "SELECT c"
                    + " FROM GrupoTrabajo c"
                    + " WHERE c.cnfOrgFk IS NULL";
            
            Query query = super.getEm().createQuery(queryStr);
              query.setParameter("orgPk", orgPk);
            List<GrupoTrabajo> resultList = query.getResultList();
            
            return resultList;
    }
}
