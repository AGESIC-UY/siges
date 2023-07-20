package com.sofis.data.daos;

import com.sofis.entities.data.ConfiguracionDefecto;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import javax.persistence.EntityManager;

public class ConfiguracionDefectoDAO extends HibernateJpaDAOImp<ConfiguracionDefecto, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public ConfiguracionDefectoDAO(EntityManager em) {
        super(em);
    }

}
