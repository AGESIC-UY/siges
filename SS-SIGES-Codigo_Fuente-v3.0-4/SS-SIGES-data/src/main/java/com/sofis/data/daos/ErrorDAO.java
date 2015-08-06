package com.sofis.data.daos;

import com.sofis.entities.data.Error;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class ErrorDAO extends HibernateJpaDAOImp<Error, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public ErrorDAO(EntityManager em) {
        super(em);
    }
}
