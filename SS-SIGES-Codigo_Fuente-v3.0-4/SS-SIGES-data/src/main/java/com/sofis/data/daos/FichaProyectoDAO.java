package com.sofis.data.daos;

import com.sofis.entities.data.Proyectos;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class FichaProyectoDAO extends HibernateJpaDAOImp<Proyectos, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public FichaProyectoDAO(EntityManager em) {
        super(em);
    }
}
