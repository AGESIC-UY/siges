package com.sofis.data.daos;

import com.sofis.entities.data.Departamentos;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class DepartamentosDAO extends HibernateJpaDAOImp<Departamentos, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(DepartamentosDAO.class.getName());        

    public DepartamentosDAO(EntityManager em) {
        super(em);
    }
}
