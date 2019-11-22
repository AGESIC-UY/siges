package com.sofis.data.daos;

import com.sofis.entities.data.MediaProyectos;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class MediaProyectosDAO extends HibernateJpaDAOImp<MediaProyectos, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(MediaProyectosDAO.class.getName());           

    public MediaProyectosDAO(EntityManager em) {
        super(em);
    }
}
