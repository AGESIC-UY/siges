package com.sofis.data.daos;

import com.sofis.entities.data.Organismos;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class OrganismoDAO extends HibernateJpaDAOImp<Organismos, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public OrganismoDAO(EntityManager em) {
        super(em);
    }
}
