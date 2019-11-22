package com.sofis.data.daos;

import com.sofis.entities.data.TiposMedia;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class TiposMediaDAO extends HibernateJpaDAOImp<TiposMedia, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(TiposMediaDAO.class.getName());    
    
    public TiposMediaDAO(EntityManager em) {
        super(em);
    }
}
