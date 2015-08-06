/*
 * 
 * 
 */
package com.sofis.business.ejbs;



import com.sofis.exceptions.GeneralException;
import com.sofis.persistence.dao.reference.EntityReference;
import com.sofis.sofisform.to.CriteriaTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Usuario
 */
@Remote
public interface EntityManagementBeanRemote {

    public Object delete(Object entity, int id, String userName, String desc) throws GeneralException;

    public Object getEntityById(String className, Integer id) throws GeneralException;

    public List getEntities(String className) throws GeneralException;

    public List<EntityReference<Integer>> getEntitiesReferenceByCriteria(String className, CriteriaTO criteria, Integer desde, Integer maximo, String[] propiedadesNombre, String[] orderBy, boolean[] ascending) throws GeneralException;

    public List getEntitiesByCriteria(String className, CriteriaTO criteria, Integer desde, Integer maximo, String[] propiedadesNombre, String[] orderBy, boolean[] ascending) throws GeneralException;

    public List getEntitiesByCriteria(String className, CriteriaTO criteria, Integer startPosition, Integer maxResult, String[] orderBy, boolean[] ascending) throws GeneralException;
    
     public Long getCountsByCriteria(String className, Integer desde,
            Integer maximo, CriteriaTO criteria) throws GeneralException;
     
     public Object saveOrUpdate(Object o, String userName, String desc) throws GeneralException;
}
