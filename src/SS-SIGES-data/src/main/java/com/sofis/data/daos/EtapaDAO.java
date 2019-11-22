package com.sofis.data.daos;

import com.sofis.entities.data.Etapa;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class EtapaDAO extends HibernateJpaDAOImp<Etapa, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(EtapaDAO.class.getName());    

    public EtapaDAO(EntityManager em) {
        super(em);
    }
}
