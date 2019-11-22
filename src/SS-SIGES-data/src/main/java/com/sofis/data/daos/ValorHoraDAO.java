package com.sofis.data.daos;

import com.sofis.entities.data.ValorHora;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class ValorHoraDAO extends HibernateJpaDAOImp<ValorHora, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ValorHoraDAO.class.getName());    
    
    public ValorHoraDAO(EntityManager em) {
        super(em);
    }
}
