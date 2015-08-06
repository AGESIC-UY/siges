package com.sofis.data.daos;

import com.sofis.entities.data.Configuracion;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class ConfiguracionDAO extends HibernateJpaDAOImp<Configuracion, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public ConfiguracionDAO(EntityManager em) {
        super(em);
    }
}
