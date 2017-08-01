package com.sofis.data.daos;

import com.sofis.entities.data.PgeConfiguraciones;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class PgeConfiguracionDAO extends HibernateJpaDAOImp<PgeConfiguraciones, Integer> implements Serializable {

    public PgeConfiguracionDAO(EntityManager em) {
        super(em);
    }
}
