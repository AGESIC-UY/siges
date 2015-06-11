
package com.sofis.web.delegates;

import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.reference.EntityReference;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.web.utils.EJBUtils;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import com.sofis.business.ejbs.EntityManagementBeanRemote;

/**
 *
 * @author Usuario
 */
@Named
public class EntityManagementDelegate implements Serializable {

    private EntityManagementBeanRemote c;

    public EntityManagementDelegate() throws TechnicalException {
        c = EJBUtils.getEntityManagement();
    }

    public Object delete(Object entity, int id, String userName, String desc) throws GeneralException {

        return c.delete(entity, id, userName, desc);
    }

    public Object getEntityById(String className, Integer id) throws GeneralException {
        return c.getEntityById(className, id);
    }

    public List getEntities(String className) throws GeneralException {
        return c.getEntities(className);
    }

    public List<EntityReference<Integer>> getEntitiesReferenceByCriteria(String className, CriteriaTO criteria, Integer desde, Integer maximo, String[] propiedadesNombre, String[] orderBy, boolean[] ascending) throws GeneralException {
        return c.getEntitiesReferenceByCriteria(className, criteria, desde , maximo,propiedadesNombre, orderBy, ascending);
    }

    public List getEntitiesByCriteria(String className, CriteriaTO criteria, Integer desde, Integer maximo, String[] propiedadesNombre, String[] orderBy, boolean[] ascending) throws GeneralException {
        return c.getEntitiesByCriteria(className, criteria, desde, maximo, propiedadesNombre, orderBy, ascending);
    }

    public List getEntitiesByCriteria(String className, CriteriaTO criteria, Integer startPosition, Integer maxResult, String[] orderBy, boolean[] ascending) throws GeneralException {
        return c.getEntitiesReferenceByCriteria(className, criteria, maxResult, maxResult, orderBy, orderBy, ascending);
    }

    public Object saveOrUpdate(Object o, String userName, String desc) throws GeneralException {
        return c.saveOrUpdate(o, userName, desc);
    }

    public Long getCountsByCriteria(String className, Integer desde,
            Integer maximo, CriteriaTO criteria) throws GeneralException {
        Long cant = -1L;
        try {
            cant = c.getCountsByCriteria(className, desde, maximo, criteria);
        } catch (GeneralException ex) {
            throw ex;
        }
        return cant;
    }
}
