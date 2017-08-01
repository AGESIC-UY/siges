package com.sofis.data.daos;

import com.sofis.entities.data.Ayuda;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class AyudaDAO extends HibernateJpaDAOImp<Ayuda, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public AyudaDAO(EntityManager em) {
        super(em);
    }
}
