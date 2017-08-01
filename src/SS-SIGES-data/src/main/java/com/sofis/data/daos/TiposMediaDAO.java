package com.sofis.data.daos;

import com.sofis.entities.data.TiposMedia;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class TiposMediaDAO extends HibernateJpaDAOImp<TiposMedia, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public TiposMediaDAO(EntityManager em) {
        super(em);
    }
}
