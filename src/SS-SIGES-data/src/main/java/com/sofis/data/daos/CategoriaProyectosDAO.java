package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.CategoriaProyectos;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class CategoriaProyectosDAO extends HibernateJpaDAOImp<CategoriaProyectos, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(CategoriaProyectosDAO.class.getName());

    public CategoriaProyectosDAO(EntityManager em) {
        super(em);
    }
    
}
