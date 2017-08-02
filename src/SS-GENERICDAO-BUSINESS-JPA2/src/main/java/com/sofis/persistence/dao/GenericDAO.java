/*
 *  Clase desarrollada por Sofis Solutions
 */

package com.sofis.persistence.dao;

import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.reference.EntityReference;
import com.sofis.persistence.dao.reference.PropertyValueOperation;
import com.sofis.sofisform.to.CriteriaTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author sofis
 */
public interface GenericDAO<T, ID extends Serializable> {

    /**
     * Crea la entidad entity en la persistencia
     * @param entity
     * @return
     * @throws DAOGeneralException
     */
    T create(T entity) throws DAOGeneralException;
    /**
     * Actualiza la entidad entity en la persistencia
     * @param entity
     * @return
     * @throws DAOGeneralException
     */
    T update(T entity) throws DAOGeneralException;
    /**
     * Borra la entidad entity de la persistencia
     * @param entity
     * @throws DAOGeneralException
     */
    void delete(T entity) throws DAOGeneralException;

    /**
     * retorna la entidad de tipo tClass a partir de su Identificador id
     * @param tClass
     * @param id
     * @return
     * @throws DAOGeneralException
     */
    T findById(Class<T> tClass,ID id) throws DAOGeneralException;

    /**
     * Retorna todas las entidades de tipo tClass
     * @param tClass la clase de la entidades a retornar
     * @return
     * @throws DAOGeneralException
     */
    List<T> findAll(Class<T> tClass) throws DAOGeneralException;

    /**
     *
     * Retorna todas las entidades de tipo tClass
     * @param tClass la clase de la entidades a retornar
     * @param campoOrder Indica el campo por el que se quiere el order.
     * @return
     * @throws DAOGeneralException
     */
    List<T> findAll(Class<T> tClass, String campoOrder) throws DAOGeneralException;

    /**
     * Retorna el objeto de la entidad segun el criteria, retorna el objeto completo de la entidad
     * @param entityClass la clase de la entidad
     * @param criteria el criterio
     * @param orderBy la ordenación del resultado
     * @param ascending si es ascendente o no cada propiedad a ordenar
     * @param startPosition desde
     * @param maxResult hasta
     * @return
     * @throws DAOGeneralException
     */
    public List<T> findEntityByCriteria(Class<T> entityClass, CriteriaTO criteria, String[] orderBy, boolean[] ascending, Long startPosition, Long maxResult) throws DAOGeneralException;

    /**
     * Retorna el objeto de la entidad, no el objeto completo sino el objeto con las propiedades propertyNames cargadas
     * @param entityClass la clase de la entidad
     * @param criteria el criterio
     * @param orderBy la ordenación del resultado
     * @param ascending si es ascendente o no cada propiedad a ordenar
     * @param startPosition desde
     * @param maxResult hasta
     * @param propertyNames las propiedads que se quieren retornar de la entidad
     * @return
     * @throws DAOGeneralException
     */
    public List<T> findEntityByCriteria(Class<T> entityClass, CriteriaTO criteria, String[] orderBy, boolean[] ascending, Long startPosition, Long maxResult,String... propertyNames) throws DAOGeneralException;


    /**
     * Retorna la entidad que cumple con la query
     * @param query la consulta para obtener la entidad
     * @return
     * @throws DAOGeneralException en caso de que la query no retorne una sola entidad
     */
    public Object findByQuery(String query) throws DAOGeneralException;

    /**
     * Retorna las entidades que cumplen que la propiedad propiedad tiene el valor valor
     * @param entidad la entidad
     * @param propiedad el nombre de la propiedad
     * @param valor el valor que debe contener
     * @return
     * @throws DAOGeneralException
     */
    public List<T> findByOneProperty(Class entidad, String propiedad, Object valor) throws DAOGeneralException;

    /**
     * Retorna una coleccion de entityReference donde en el propertyMap posee las propiedades de propertyName
     * @param entityClass la clase de la entidad
     * @param criteria el criterio
     * @param orderBy la ordenación del resultado
     * @param ascending si es ascendente o no cada propiedad a ordenar
     * @param startPosition desde
     * @param maxResult hasta
     * @param propertyNames las propiedads que se quieren retornar de la entidad
     * @return
     * @throws DAOGeneralException
     */
    public List<EntityReference<ID>> findEntityReferenceByCriteria(Class<T> entityClass, CriteriaTO criteria, String[] orderBy, boolean[] ascending, Long startPosition, Long maxResult,String... propertyNames) throws DAOGeneralException;

    /**
     * Cuenta la cantidad de entidades a que cumplen con el criteria
     * @param entityClass la clase de la entidad
     * @param criteria el criteria  
     * @param startPosition null
     * @param maxResult null
     * @return
     * @throws DAOGeneralException
     */
    public Long countByCriteria(Class<T> entityClass, CriteriaTO criteria, Long startPosition, Long maxResult) throws DAOGeneralException;
}