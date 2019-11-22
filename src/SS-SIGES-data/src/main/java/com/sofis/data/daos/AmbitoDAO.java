package com.sofis.data.daos;

import com.sofis.entities.data.Ambito;
import com.sofis.entities.data.Areas;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class AmbitoDAO extends HibernateJpaDAOImp<Ambito, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(AmbitoDAO.class.getName());        

    public AmbitoDAO(EntityManager em) {
        super(em);
    }
}
