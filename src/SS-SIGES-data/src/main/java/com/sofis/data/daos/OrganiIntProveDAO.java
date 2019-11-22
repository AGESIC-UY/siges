package com.sofis.data.daos;

import com.sofis.entities.data.OrganiIntProve;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class OrganiIntProveDAO extends HibernateJpaDAOImp<OrganiIntProve, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(OrganiIntProveDAO.class.getName());    

    public OrganiIntProveDAO(EntityManager em) {
        super(em);
    }
}
