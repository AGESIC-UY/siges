package com.sofis.data.daos;

import com.sofis.entities.data.GrupoTrabajoUsuario;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class GrupoTrabajoUsuarioDAO extends HibernateJpaDAOImp<GrupoTrabajoUsuario, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(GrupoTrabajoUsuarioDAO.class.getName());        

    public GrupoTrabajoUsuarioDAO(EntityManager em) {
        super(em);
    }

    
}
