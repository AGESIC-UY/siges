package com.sofis.data.daos;

import com.sofis.entities.data.TemasCalidad;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class TemasCalidadDAO extends HibernateJpaDAOImp<TemasCalidad, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public TemasCalidadDAO(EntityManager em) {
        super(em);
    }
}
