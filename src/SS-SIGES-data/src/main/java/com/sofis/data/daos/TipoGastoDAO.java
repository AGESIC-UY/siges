package com.sofis.data.daos;

import com.sofis.entities.data.TipoGasto;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class TipoGastoDAO extends HibernateJpaDAOImp<TipoGasto, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(TipoGastoDAO.class.getName());      

    public TipoGastoDAO(EntityManager em) {
        super(em);
    }
}
