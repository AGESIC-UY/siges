package com.sofis.data.daos;

import com.sofis.entities.data.Ayuda;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class AyudaDAO extends HibernateJpaDAOImp<Ayuda, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(AyudaDAO.class.getName());        

    public AyudaDAO(EntityManager em) {
        super(em);
    }
}
