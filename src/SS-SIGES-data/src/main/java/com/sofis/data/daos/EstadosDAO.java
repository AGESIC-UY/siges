package com.sofis.data.daos;

import com.sofis.entities.data.Estados;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class EstadosDAO extends HibernateJpaDAOImp<Estados, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(EstadosDAO.class.getName());    

    public EstadosDAO(EntityManager em) {
        super(em);
    }
}
