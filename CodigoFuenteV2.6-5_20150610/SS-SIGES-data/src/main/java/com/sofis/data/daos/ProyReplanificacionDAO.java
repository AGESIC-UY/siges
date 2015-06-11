package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.ProyIndices;
import com.sofis.entities.data.ProyReplanificacion;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class ProyReplanificacionDAO extends HibernateJpaDAOImp<ProyReplanificacion, Integer> implements Serializable {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final long serialVersionUID = 1L;

    public ProyReplanificacionDAO(EntityManager em) {
        super(em);
    }
}
