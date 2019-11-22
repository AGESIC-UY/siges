package com.sofis.data.daos;

import com.sofis.entities.data.PgeConfiguraciones;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class PgeConfiguracionDAO extends HibernateJpaDAOImp<PgeConfiguraciones, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(PgeConfiguracionDAO.class.getName()); 
    
    public PgeConfiguracionDAO(EntityManager em) {
        super(em);
    }
}
