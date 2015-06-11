package com.sofis.data.daos;

import com.sofis.entities.data.Programas;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class FichaProgramaDAO extends HibernateJpaDAOImp<Programas, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public FichaProgramaDAO(EntityManager em) {
        super(em);
    }
}
