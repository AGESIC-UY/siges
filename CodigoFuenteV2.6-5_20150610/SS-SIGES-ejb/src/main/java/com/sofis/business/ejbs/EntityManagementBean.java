package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import com.sofis.persistence.dao.reference.EntityReference;
import com.sofis.sofisform.to.CriteriaTO;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.sofis.business.utils.Utils;
import com.sofis.entities.constantes.ConstanteApp;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "EntityManagementBean")
@Interceptors({LoggedInterceptor.class})
public class EntityManagementBean implements EntityManagementBeanRemote {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    EntityManager em;

    @Override
    public Object saveOrUpdate(Object o, String userName, String desc)
            throws GeneralException {
        try {
            HibernateJpaDAOImp dao = new HibernateJpaDAOImp(em);
            Object toReturn = dao.update(o, userName, desc);
            return toReturn;
        } catch (DAOGeneralException ex) {
            // TODO: Agregar mensaje de excepcion
            throw new TechnicalException();
        }
    }

    @Override
    public Object delete(Object entity, int id, String userName, String desc)
            throws GeneralException {
        try {
            HibernateJpaDAOImp dao = new HibernateJpaDAOImp(em);
            Object objBorrar = getEntityById(entity.getClass().getName(), id);
            dao.delete(objBorrar, userName, desc);

            return entity;

        } catch (DAOGeneralException ex) {
            // TODO: Agregar mensaje de excepcion
            throw new TechnicalException();
        }
    }

    @Override
    public Object getEntityById(String className, Integer id)
            throws GeneralException {
        try {
            HibernateJpaDAOImp dao = new HibernateJpaDAOImp(em);
            Class class_;
            try {
                class_ = Class.forName(className);
                //System.out.println("2");
            } catch (ClassNotFoundException ex) {
                throw new DAOGeneralException(ex);
            }
            return em.find(class_, id);
            //return dao.findById(class_, id);

        } catch (DAOGeneralException ex) {
            // TODO: Agregar mensaje de excepcion
            throw new TechnicalException();
        }
    }

    @Override
    public List getEntities(String className) throws GeneralException {
        try {
            HibernateJpaDAOImp dao = new HibernateJpaDAOImp(em);
            Class class_;
            try {
                class_ = Class.forName(className);
            } catch (ClassNotFoundException ex) {
                throw new DAOGeneralException(ex);
            }
            return dao.findAll(class_);

        } catch (DAOGeneralException ex) {
            // TODO: Agregar mensaje de excepcion
            throw new TechnicalException();
        }
    }

    @Override
    public List<EntityReference<Integer>> getEntitiesReferenceByCriteria(
            String className, CriteriaTO criteria, Integer desde,
            Integer maximo, String[] propiedadesNombre, String[] orderBy,
            boolean[] ascending) throws GeneralException {
        try {
            HibernateJpaDAOImp dao = new HibernateJpaDAOImp(em);
            Class class_ = Utils.toClass(className);
            Long desdeL = Utils.toLong(desde);
            Long hastaL = Utils.toLong(maximo);

            return dao.findEntityReferenceByCriteria(class_, criteria, orderBy,
                    ascending, desdeL, hastaL, propiedadesNombre);

        } catch (DAOGeneralException ex) {
            // TODO: Agregar mensaje de excepcion

            throw new TechnicalException();
        } catch (Exception ex) {

            throw new TechnicalException();
        }
    }

    @Override
    public List getEntitiesByCriteria(String className, CriteriaTO criteria,
            Integer desde, Integer maximo, String[] propiedadesNombre,
            String[] orderBy, boolean[] ascending)
            throws GeneralException {
        try {
            HibernateJpaDAOImp dao = new HibernateJpaDAOImp(em);
            Class class_ = Utils.toClass(className);
            Long desdeL = Utils.toLong(desde);
            Long hastaL = Utils.toLong(maximo);
            List<EntityReference<Integer>> lista = dao.findEntityByCriteria(class_, criteria, orderBy,
                    ascending, desdeL, hastaL, propiedadesNombre);

            return lista;
        } catch (DAOGeneralException ex) {
            // TODO: Agregar mensaje de excepcion
            throw new TechnicalException();
        }
    }

    @Override
    public List getEntitiesByCriteria(String className, CriteriaTO criteria,
            Integer startPosition, Integer maxResult, String[] orderBy,
            boolean[] ascending) throws GeneralException {
        try {
            HibernateJpaDAOImp dao = new HibernateJpaDAOImp(em);
            Class class_ = Utils.toClass(className);
            Long desdeL = Utils.toLong(startPosition);
            Long hastaL = Utils.toLong(maxResult);
            return dao.findEntityByCriteria(class_, criteria, orderBy,
                    ascending, 0L, hastaL);

        } catch (DAOGeneralException ex) {
            // TODO: Agregar mensaje de excepcion
            throw new TechnicalException();
        }
    }

    @Override
    public Long getCountsByCriteria(String className, Integer desde,
            Integer maximo, CriteriaTO criteria) throws GeneralException {
        try {
            HibernateJpaDAOImp dao = new HibernateJpaDAOImp(em);
            Class class_ = Utils.toClass(className);
            Long desdeL = Utils.toLong(desde);
            Long hastaL = Utils.toLong(maximo);
            return dao.countByCriteria(class_, criteria, desdeL, hastaL);

        } catch (DAOGeneralException ex) {
            // TODO: Agregar mensaje de excepcion
            ex.printStackTrace();
            throw new TechnicalException();
        }
    }
}
