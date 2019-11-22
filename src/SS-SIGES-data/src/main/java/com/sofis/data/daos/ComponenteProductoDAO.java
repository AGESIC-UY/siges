package com.sofis.data.daos;

import com.sofis.entities.data.ComponenteProducto;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class ComponenteProductoDAO extends HibernateJpaDAOImp<ComponenteProducto, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ComponenteProductoDAO.class.getName());        

    public ComponenteProductoDAO(EntityManager em) {
        super(em);
    }
    
    @SuppressWarnings("unchecked")
    public List<ComponenteProducto> obtenerPorOrganismo(Integer orgPk) throws DAOGeneralException{
        return super.getEm()
                .createNamedQuery("ComponenteProducto.findByOrgPk")
                .setParameter("orgPk", orgPk)
                .getResultList();
    }
}
