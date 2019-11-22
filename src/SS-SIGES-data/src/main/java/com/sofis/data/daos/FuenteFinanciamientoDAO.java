package com.sofis.data.daos;

import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class FuenteFinanciamientoDAO extends HibernateJpaDAOImp<FuenteFinanciamiento, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(FuenteFinanciamientoDAO.class.getName());    

    public FuenteFinanciamientoDAO(EntityManager em) {
        super(em);
    }
    
    @SuppressWarnings("unchecked")
    public List<FuenteFinanciamiento> obtenerPorOrganismo(Integer orgPk) throws DAOGeneralException{
        return super.getEm()
                .createNamedQuery("FuenteFinanciamiento.findByOrgPk")
                .setParameter("orgPk", orgPk)
                .getResultList();
    }
}
