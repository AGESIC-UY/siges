package com.sofis.data.daos;

import com.sofis.entities.data.RolesInteresados;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class RolesInteresadosDAO extends HibernateJpaDAOImp<RolesInteresados, Integer> implements Serializable {

    public RolesInteresadosDAO(EntityManager em) {
        super(em);
    }
}
