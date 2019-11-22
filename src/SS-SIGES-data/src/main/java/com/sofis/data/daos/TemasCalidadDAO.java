package com.sofis.data.daos;

import com.sofis.entities.data.TemasCalidad;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class TemasCalidadDAO extends HibernateJpaDAOImp<TemasCalidad, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(TemasCalidadDAO.class.getName());    

    public TemasCalidadDAO(EntityManager em) {
        super(em);
    }
}
