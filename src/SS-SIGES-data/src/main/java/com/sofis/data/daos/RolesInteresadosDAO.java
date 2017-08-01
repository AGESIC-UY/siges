package com.sofis.data.daos;

import com.sofis.entities.data.RolesInteresados;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class RolesInteresadosDAO extends HibernateJpaDAOImp<RolesInteresados, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public RolesInteresadosDAO(EntityManager em) {
        super(em);
    }
    
    @SuppressWarnings("unchecked")
    public List<RolesInteresados> findByOrg(Integer orgPk){
        return this.getEm()
                .createNamedQuery("RolesInteresados.findByOrganismo")
                .setParameter("org_pk", orgPk)
                .getResultList();
    }
}
