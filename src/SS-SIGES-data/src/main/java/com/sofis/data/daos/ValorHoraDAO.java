package com.sofis.data.daos;

import com.sofis.entities.data.ValorHora;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class ValorHoraDAO extends HibernateJpaDAOImp<ValorHora, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public ValorHoraDAO(EntityManager em) {
        super(em);
    }
}
