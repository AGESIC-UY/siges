package com.sofis.data.daos;

import com.sofis.entities.data.TipoLeccion;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class TipoLeccionDAO extends HibernateJpaDAOImp<TipoLeccion, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(TipoLeccionDAO.class.getName());   
    
    public TipoLeccionDAO(EntityManager em) {
        super(em);
    }
}
