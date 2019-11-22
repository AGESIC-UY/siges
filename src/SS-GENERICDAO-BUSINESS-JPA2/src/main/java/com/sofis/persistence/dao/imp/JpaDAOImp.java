/*
 *  Clase desarrollada por Sofis Solutions
 */
package com.sofis.persistence.dao.imp;

import com.sofis.persistence.dao.GenericDAO;

import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.reference.EntityReference;
import com.sofis.sofisform.to.*;
import com.sofis.utils.CriteriaTOUtils;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Transient;
import javax.persistence.Id;

/**
 * Clase desarrollada por Sofis Solutions, implementación del patrón de diseño
 * DAO para JPA
 *
 * @author Sofis Solutions
 */
public class JpaDAOImp<T, ID extends Serializable> implements GenericDAO<T, ID> {

	@Transient
	protected EntityManager em;
	boolean debug = false;

	public JpaDAOImp(EntityManager em) {
		try {
			Properties nb = new Properties();
			nb.load(JpaDAOImp.class.getClassLoader().getResourceAsStream("META-INF/configdao.properties"));
			String deb = nb.getProperty("debug", "false");
			if (deb != null && deb.equalsIgnoreCase("true")) {
				debug = true;
			}
			this.em = em;
		} catch (IOException ex) {
			Logger.getLogger(JpaDAOImp.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * CRUD OPERATIONS
	 */
	public void delete(T entity) throws DAOGeneralException {
		try {
			em.remove(entity);
			em.flush();
		} catch (Exception e) {
			DAOGeneralException eDao = new DAOGeneralException(e);
			throw eDao;
		}
	}

	public void delete(Class c) throws DAOGeneralException {
		try {
			String query = "delete from " + c.getSimpleName() + "";
			Query queryem = em.createQuery(query);
			queryem.executeUpdate();

		} catch (Exception e) {
			DAOGeneralException eDao = new DAOGeneralException(e);
			throw eDao;
		}
	}

	public T create(T entity) throws DAOGeneralException {
		try {
			em.persist(entity);
			em.flush();
			em.refresh(entity);
			return entity;
		} catch (Exception e) {
			DAOGeneralException eDao = new DAOGeneralException(e);
			throw eDao;
		}

	}

	public T update(T entity) throws DAOGeneralException {
		try {
			T toReturn = em.merge(entity);
			em.flush();
			em.refresh(toReturn);
			return toReturn;
		} catch (Exception e) {
			DAOGeneralException eDao = new DAOGeneralException(e);
			throw eDao;
		}
	}

	/**
	 * FIND OPERATIONS
	 */
	public T findById(Class<T> entityClass, ID id) throws DAOGeneralException {
		try {
			T toReturn = (T) em.find(entityClass, id);
			return toReturn;
		} catch (Exception e) {
			DAOGeneralException eDao = new DAOGeneralException(e);
			throw eDao;
		}
	}

	public List<T> findAll(Class<T> entityClass) throws DAOGeneralException {
		try {
			String sql = "SELECT a from " + entityClass.getSimpleName() + " a ";
			Query q = em.createQuery(sql);
			return q.getResultList();
		} catch (Exception e) {
			DAOGeneralException eDao = new DAOGeneralException(e);
			throw eDao;
		}
	}

	public List<T> findAll(Class<T> entityClass, String campoOrder) throws DAOGeneralException {
		try {
			String sql = "SELECT a FROM " + entityClass.getSimpleName() + " a ORDER BY a." + campoOrder;
			Query q = em.createQuery(sql);
			return q.getResultList();
		} catch (Exception e) {
			DAOGeneralException eDao = new DAOGeneralException(e);
			throw eDao;
		}
	}

	public Object findByQuery(String query) throws DAOGeneralException {
		try {
			Query q = em.createQuery(query);
			return (Object) (q.getSingleResult());
		} catch (Exception e) {
			DAOGeneralException eDao = new DAOGeneralException(e);
			throw eDao;
		}
	}

	/**
	 * Retorna el objeto de la entidad segun el criteria, retorna el objeto
	 * completo de la entidad
	 *
	 * @param entityClass la clase de la entidad
	 * @param criteria el criterio
	 * @param orderBy la ordenación del resultado
	 * @param ascending si es ascendente o no cada propiedad a ordenar
	 * @param startPosition desde
	 * @param maxResult hasta
	 * @return
	 * @throws DAOGeneralException
	 */
	public List<T> findEntityByCriteria(Class<T> entityClass, CriteriaTO criteria, String[] orderBy, boolean[] ascending, Long startPosition, Long maxResult) throws DAOGeneralException {
		try {

			//vemos todas las propiedades que tienen que navegar por colecciones.
			StringBuffer collection = new StringBuffer();
			StringBuffer fromClause = new StringBuffer(" FROM " + entityClass.getSimpleName() + " a ");
			//los left join generados
			HashMap leftJoin = new HashMap();
			HashMap col = new HashMap();
			HashMap colPr = new HashMap();

			HashMap<String, ValueCriteriaTO> valuesCriteria = null;
			Character index = 'b';
			if (criteria != null) {
				//obtenemos el hashmap de la forma KEY y ValueCriteriaTO estos son las hojas del arbol formado
				//por criteria
				valuesCriteria = criteria.getValueCriteriaTO();

				for (String key : valuesCriteria.keySet()) {
					ValueCriteriaTO valueCriteriaTO = valuesCriteria.get(key);
					Object o = valueCriteriaTO.getValue();
					if (o == null || o.toString().equalsIgnoreCase("")) {
						//continue;
					}

					//TODO esta muy unido el criteria a los componentes, analizar como desunir esta dependencia
					String property = valueCriteriaTO.getComponent().getProperty();

					HashMap str = this.isCollection(entityClass, property);
					if (str != null && collection.indexOf(str.toString()) < 0 && valueCriteriaTO.getValue() != null && !valueCriteriaTO.getValue().toString().equalsIgnoreCase("")) {
						String t = (String) str.keySet().iterator().next();
						if (!colPr.containsKey(t)) {
							col.put(valueCriteriaTO, index.toString());

							//collection.append(", IN(a.");
							collection.append(" LEFT OUTER JOIN a.");
							colPr.put(t, index.toString());
							collection.append(t);
							//collection.append(") ");
							collection.append(" ");
							collection.append(index);
							index = (char) (index + 1);
						} else {
							col.put(valueCriteriaTO, colPr.get(t));

						}
					} else {
						//genera los left join en las propiedades utilizadas en los campos de busqueda
						String[] propertySplit = property.split("[.]");
						if (propertySplit.length > 1) {
							////SELECT c, p.name FROM Country c LEFT OUTER JOIN c.capital p
							String navProp = "";
							for (int ite = 0; ite < propertySplit.length - 1; ite++) {
								if (ite == 0) {

									if (!leftJoin.containsKey(propertySplit[ite])) {
										fromClause.append(" LEFT OUTER JOIN a." + propertySplit[ite] + " lp" + leftJoin.size());
										navProp = propertySplit[ite];
										leftJoin.put(navProp, leftJoin.size());
									} else {
										navProp = propertySplit[ite];
									}
								} else {

									if (!leftJoin.containsKey(navProp + "." + propertySplit[ite])) {
										fromClause.append(" LEFT OUTER JOIN lp" + leftJoin.get(navProp) + "." + propertySplit[ite] + " lp" + leftJoin.size());
										navProp = navProp + "." + propertySplit[ite];
										leftJoin.put(navProp, leftJoin.size());
									} else {
										navProp = navProp + "." + propertySplit[ite];
									}
								}
							}
						}
					}

				}
			}
			StringBuffer sql = new StringBuffer();
			col.put(null, "a");
			sql = sql.append("SELECT DISTINCT  a");

			sql.append(fromClause);
			sql.append(collection);

			String whereClause = null;
			if (criteria != null) {
				whereClause = criteria.getStringQuery(col, leftJoin);
			}

			if (whereClause != null) {
				sql.append(" WHERE ");
				sql.append(whereClause);
			}

			if (ascending != null && ascending.length > 0) {
				sql.append(" ORDER BY ");
				for (int i = 0; i < ascending.length; i++) {
					if (i > 0) {
						sql.append(",");
					}
					if (orderBy[i].lastIndexOf('.') >= 0) {
						String toCheck = orderBy[i].substring(0, orderBy[i].lastIndexOf('.'));
						Integer leftJoinIndex = (Integer) leftJoin.get(toCheck);
						if (leftJoinIndex != null) {
							String index1 = "lp" + leftJoinIndex;
							sql.append(index1 + ".");
							sql.append(orderBy[i].substring(orderBy[i].lastIndexOf('.') + 1, orderBy[i].length()));
						} else {
							sql.append("a.");
							sql.append(orderBy[i]);
						}
					} else {
						sql.append("a.");
						sql.append(orderBy[i]);
					}

					if (!ascending[i]) {
						sql.append(" DESC");
					}
				}
			}

			if (debug) {
				System.out.println("LA CONSULTA ES " + sql + " " + em);
			}

			Query query = em.createQuery(sql.toString());

			if (whereClause != null) {
				for (String key : valuesCriteria.keySet()) {
					ValueCriteriaTO valueCriteriaTO = valuesCriteria.get(key);
					Object value = valueCriteriaTO.getValue();
					/* No esta funcionando con JDK 1.7
                     if (value instanceof XMLGregorianCalendarImpl) {
                     XMLGregorianCalendarImpl valueXML = (XMLGregorianCalendarImpl) value;
                     value = valueXML.toGregorianCalendar().getTime();

                     }
					 */
					if (value != null) {
						if (valueCriteriaTO instanceof MatchCriteriaTO) {

							MatchCriteriaTO mat = (MatchCriteriaTO) valueCriteriaTO;
							String matchType = "";
							if (mat.getMatchType().equalsIgnoreCase(MatchCriteriaTO.types.SELECTOR.getType())) {
								//si es selector el matchType es el seleccionado por el usuario qeu viene en el valueCriteriaTO
								matchType = valueCriteriaTO.getMatchCriteriaSelector();
							} else {
								matchType = mat.getMatchType();
							}

							if (matchType.equalsIgnoreCase(MatchCriteriaTO.types.CONTAINS.getType())) {
								//si es una fecha entonces no le debemos de poner los %%
								if (!(value instanceof Date)) {
									query.setParameter(key, "%" + value + "%");
								} else {
									query.setParameter(key, value);
								}
							} else if (matchType.equalsIgnoreCase(MatchCriteriaTO.types.START_WITH.getType())) {
								query.setParameter(key, value + "%");
							} else if (matchType.equalsIgnoreCase(MatchCriteriaTO.types.END_WITH.getType())) {
								query.setParameter(key, "%" + value);
							} else if (!matchType.equalsIgnoreCase(MatchCriteriaTO.types.EMPTY.getType())
								   && !matchType.equalsIgnoreCase(MatchCriteriaTO.types.NOT_EMPTY.getType())
								   && !matchType.equalsIgnoreCase(MatchCriteriaTO.types.NULL.getType())
								   && !matchType.equalsIgnoreCase(MatchCriteriaTO.types.NOT_NULL.getType())
								   && !matchType.equalsIgnoreCase(MatchCriteriaTO.types.SUBQUERY.getType())) {
								query.setParameter(key, value);
							}

						} else {
							query.setParameter(key, value);
						}

					}

				}
			}

			if (startPosition != null) {
				query.setFirstResult(startPosition.intValue());
			}
			if (maxResult != null) {
				query.setMaxResults(maxResult.intValue());
			}

			List<T> result = query.getResultList();
			List<T> referenceList = new ArrayList<T>(result.size());
			for (T row : result) {
				referenceList.add(row);
			}

			return referenceList;

		} catch (Exception e) {
			DAOGeneralException eDao = new DAOGeneralException(e);
			throw eDao;
		}
	}

	/**
	 * Retorna el objeto de la entidad, no el objeto completo sino el objeto
	 * con las propiedades propertyNames cargadas
	 *
	 * @param entityClass la clase de la entidad
	 * @param criteria el criterio
	 * @param orderBy la ordenación del resultado
	 * @param ascending si es ascendente o no cada propiedad a ordenar
	 * @param startPosition desde
	 * @param maxResult hasta
	 * @param propertyNames las propiedads que se quieren retornar de la
	 * entidad
	 * @return
	 * @throws DAOGeneralException
	 */
	public List<T> findEntityByCriteria(Class<T> entityClass, CriteriaTO criteria, String[] orderBy, boolean[] ascending, Long startPosition, Long maxResult, String... propertyNames) throws DAOGeneralException {
		try {

			HashMap<String, String> transientProperties = new HashMap<String, String>();
			List<String> propertyNamesList = Arrays.asList(propertyNames);
			propertyNamesList = new ArrayList<String>(propertyNamesList);
			List<Object[]> result = this.findByCriteriaUtil(false, entityClass, criteria, orderBy, ascending, startPosition, maxResult, transientProperties, propertyNamesList);
			List<T> referenceList = new ArrayList<T>(result.size());
			for (Object[] row : result) {
				T ent = entityClass.newInstance();
				this.setProperty(ent, getIdName(entityClass), row[0], getIdType(entityClass));
				for (int i = 1; i < row.length; i++) {
					Object o = row[i];
					String propertyName = propertyNamesList.get(i - 1);
					this.setProperty(ent, propertyName, o, o.getClass());
				}

				referenceList.add(ent);
			}
			return referenceList;

		} catch (Exception e) {
			DAOGeneralException eDao = new DAOGeneralException(e);
			throw eDao;
		}
	}

	/**
	 * Retorna una coleccion de entityReference donde en el propertyMap posee
	 * las propiedades de propertyName
	 *
	 * @param entityClass la clase de la entidad
	 * @param criteria el criterio
	 * @param orderBy la ordenación del resultado
	 * @param ascending si es ascendente o no cada propiedad a ordenar
	 * @param startPosition desde
	 * @param maxResult hasta
	 * @param idName el id de la propiedad
	 * @param propertyNames las propiedads que se quieren retornar de la
	 * entidad
	 * @return
	 * @throws DAOGeneralException
	 */
	public List<EntityReference<ID>> findEntityReferenceByCriteria(Class<T> entityClass, CriteriaTO criteria, String[] orderBy, boolean[] ascending, Long startPosition, Long maxResult, String... propertyNames) throws DAOGeneralException {
		try {
			HashMap<String, String> transientProperties = new HashMap<String, String>();
			List<String> propertyNamesList = Arrays.asList(propertyNames);
			propertyNamesList = new ArrayList<String>(propertyNamesList);

			List<Object[]> result = this.findByCriteriaUtil(false, entityClass, criteria, orderBy, ascending, startPosition, maxResult, transientProperties, propertyNamesList);

			List<EntityReference<ID>> referenceList = new ArrayList<EntityReference<ID>>(result.size());
			HashMap<String, Object> valueMap;
			for (Object[] row : result) {
				valueMap = new HashMap<String, Object>();
				for (int i = 1; i < row.length; i++) {
					String propertyName = propertyNamesList.get(i - 1);
					valueMap.put(propertyName, row[i]);
				}
				EntityReference<ID> ent = new EntityReference<ID>();
				ent.setId((ID) row[0]);
				//si tenemos alguna propiedad transient entonces tenemos que obtener la instancia de la propiedad y obtenerla por el get
				if (transientProperties.size() > 0) {
					Object entityObject = em.getReference(entityClass, ent.getId());
					for (String property : transientProperties.keySet()) {
						valueMap.put(property, this.getProperty(entityObject, property));
					}
				}

				ent.setPropertyMap(valueMap);
				referenceList.add(ent);
			}
			return referenceList;

		} catch (Exception e) {
			DAOGeneralException eDao = new DAOGeneralException(e);
			throw eDao;
		}
	}

	private List<Object[]> findByCriteriaUtil(boolean count, Class<T> entityClass, CriteriaTO criteria, String[] orderBy, boolean[] ascending, Long startPosition, Long maxResult, HashMap<String, String> transientProperties, List propertyNamesList) throws DAOGeneralException {
		try {
			//los order by tiene qeu estar en la propiedades del select, TODO si ya esta no volverlos a poner
			if (orderBy != null && orderBy.length > 0) {
				for (int i = 0; i < orderBy.length; i++) {
					propertyNamesList.add(orderBy[i]);
				}
			}
			String[] s = {};
			String[] propertyNames = (String[]) propertyNamesList.toArray(s);

			//vemos todas las propiedades que tienen que navegar por colecciones.
			StringBuffer collection = new StringBuffer();
			StringBuffer fromClause = new StringBuffer(" FROM " + entityClass.getSimpleName() + " a ");
			//los left join generados
			HashMap leftJoin = new HashMap();
			HashMap col = new HashMap();
			HashMap colPr = new HashMap();

			HashMap<String, ValueCriteriaTO> valuesCriteria = null;
			Character index = 'b';
			if (criteria != null) {
				//obtenemos el hashmap de la forma KEY y ValueCriteriaTO estos son las hojas del arbol formado
				//por criteria
				valuesCriteria = criteria.getValueCriteriaTO();
				for (String key : valuesCriteria.keySet()) {
					ValueCriteriaTO valueCriteriaTO = valuesCriteria.get(key);
					Object o = valueCriteriaTO.getValue();
					if (o == null || o.toString().equalsIgnoreCase("")) {
						//continue;
					}

					String property = valueCriteriaTO.getComponent().getProperty();
					HashMap str = this.isCollection(entityClass, property);
					if (str != null && collection.indexOf(str.toString()) < 0 && valueCriteriaTO.getValue() != null && !valueCriteriaTO.getValue().toString().equalsIgnoreCase("")) {
						String t = (String) str.keySet().iterator().next();
						if (!colPr.containsKey(t)) {
							col.put(valueCriteriaTO, index.toString());
							//collection.append(", IN(a.");
							collection.append(" LEFT OUTER JOIN a.");
							colPr.put(t, index.toString());
							collection.append(t);
							//collection.append(") ");
							collection.append(" ");
							collection.append(index);
							index = (char) (index + 1);
						} else {
							col.put(valueCriteriaTO, colPr.get(t));

						}
					} else {

						//genera los left join en las propiedades utilizadas en los campos de busqueda
						String[] propertySplit = property.split("[.]");
						if (propertySplit.length > 1) {
							////SELECT c, p.name FROM Country c LEFT OUTER JOIN c.capital p
							String navProp = "";
							for (int ite = 0; ite < propertySplit.length - 1; ite++) {
								if (ite == 0) {

									if (!leftJoin.containsKey(propertySplit[ite])) {
										fromClause.append(" LEFT OUTER JOIN a." + propertySplit[ite] + " lp" + leftJoin.size());
										navProp = propertySplit[ite];
										leftJoin.put(navProp, leftJoin.size());
									} else {
										navProp = propertySplit[ite];
									}
								} else {

									if (!leftJoin.containsKey(navProp + "." + propertySplit[ite])) {
										fromClause.append(" LEFT OUTER JOIN lp" + leftJoin.get(navProp) + "." + propertySplit[ite] + " lp" + leftJoin.size());
										navProp = navProp + "." + propertySplit[ite];
										leftJoin.put(navProp, leftJoin.size());
									} else {
										navProp = navProp + "." + propertySplit[ite];
									}
								}
							}
						}

					}
				}
			}

			StringBuffer sql = new StringBuffer();
			col.put(null, "a");
			if (count) {
				sql = sql.append("SELECT count(DISTINCT a.");
			} else {
				sql = sql.append("SELECT DISTINCT  a.");
			}

			String idName = getIdName(entityClass);
			if (idName != null) {
				sql = sql.append(idName);
				sql = sql.append(" ");
			}

			if (count) {
				sql = sql.append(") ");
			}

			//Las otras propiedades
			for (int i = 0; i < propertyNames.length; i++) {
				HashMap c = isCollection(entityClass, propertyNames[i]);
				if (c != null) {
					//si es coleccion se pone b. la propiedad y no a. la propiedad
					String t = (String) c.keySet().iterator().next();
					if (colPr.get(t) != null) {
						sql.append(",");
						sql.append(colPr.get(t) + ".");
						sql.append(c.get(t));
					} else {
						//collection.append(", IN(a.");
						collection.append(" LEFT OUTER JOIN a.");
						colPr.put(t, index.toString());
						collection.append(t);
						//collection.append(") ");
						collection.append(" ");
						collection.append(index);
						index = (char) (index + 1);
						sql.append(",");
						sql.append(colPr.get(t) + ".");
						sql.append(c.get(t));
					}

				} else {
					//chequear que la propiedad no sea transiente si es transiente no se agrega a la consulta
					boolean toPut = true;
					String propertyToCheck = propertyNames[i];
					try {
						Field f = entityClass.getDeclaredField(propertyToCheck);
						Annotation annotation = f.getAnnotation(Transient.class);

						if (annotation != null) {
							toPut = false;
						}
					} catch (Exception e) {
						toPut = true;
					}

					if (toPut) {
						//se chequea que no sea una referencia por lo tanto se tiene que generar el left join
						//si tengo mas de un token entonces es de la forma a.b.c.d genero letter.d dado que d es la propiedad final
						String[] propertySplit = propertyToCheck.split("[.]");
						if (propertySplit.length > 1) {
							////SELECT c, p.name FROM Country c LEFT OUTER JOIN c.capital p
							String navProp = "";
							for (int ite = 0; ite < propertySplit.length - 1; ite++) {
								if (ite == 0) {

									if (!leftJoin.containsKey(propertySplit[ite])) {
										fromClause.append(" LEFT OUTER JOIN a." + propertySplit[ite] + " lp" + leftJoin.size());
										navProp = propertySplit[ite];
										leftJoin.put(navProp, leftJoin.size());
									} else {
										navProp = propertySplit[ite];
									}
								} else {

									if (!leftJoin.containsKey(navProp + "." + propertySplit[ite])) {
										fromClause.append(" LEFT OUTER JOIN lp" + leftJoin.get(navProp) + "." + propertySplit[ite] + " lp" + leftJoin.size());
										navProp = navProp + "." + propertySplit[ite];
										leftJoin.put(navProp, leftJoin.size());
									} else {
										navProp = navProp + "." + propertySplit[ite];
									}
								}
							}
							String lastToken = propertySplit[propertySplit.length - 1];
							sql.append(",");
							sql.append("lp" + leftJoin.get(navProp) + ".");
							sql.append(lastToken);
						} else {
							sql.append(",");
							sql.append("a.");
							sql.append(propertyToCheck);
						}
					} else {
						//la propiedad es transient por lo tanto no obtenemos el valor de la base de datos
						//sino que lo obtenemos del get de la entidad
						transientProperties.put(propertyToCheck, propertyToCheck);
					}
				}
			}

			//las propiedades transiente que no se consideran
			if (transientProperties.size() > 0) {
				List finalList = new ArrayList();
				for (int i = 0; i < propertyNames.length; i++) {
					if (transientProperties.containsKey(propertyNames[i])) {
						//nothing
					} else {
						finalList.add(propertyNames[i]);
					}
				}
				propertyNames = (String[]) finalList.toArray(s);
			}

			sql.append(fromClause);
			sql.append(collection);

			String whereClause = null;
			if (criteria != null) {
				whereClause = criteria.getStringQuery(col, leftJoin);
			}

			if (whereClause != null) {
				sql.append(" WHERE ");
				sql.append(whereClause);
			}

			if (ascending != null && ascending.length > 0 && orderBy.length > 0) {
				sql.append(" ORDER BY ");
				for (int i = 0; i < ascending.length; i++) {
					if (i > 0) {
						sql.append(",");
					}
					if (orderBy[i].lastIndexOf('.') >= 0) {
						String toCheck = orderBy[i].substring(0, orderBy[i].lastIndexOf('.'));
						Integer leftJoinIndex = (Integer) leftJoin.get(toCheck);
						if (leftJoinIndex != null) {
							String index1 = "lp" + leftJoinIndex;
							sql.append(index1 + ".");
							sql.append(orderBy[i].substring(orderBy[i].lastIndexOf('.') + 1, orderBy[i].length()));
						} else {
							sql.append("a.");
							sql.append(orderBy[i]);
						}
					} else {
						sql.append("a.");
						sql.append(orderBy[i]);
					}

					if (!ascending[i]) {
						sql.append(" DESC");
					}
				}
			}

			if (debug) {
				System.out.println("LA CONSULTA ES " + sql + " " + em);
			}

			Query query = em.createQuery(sql.toString());

			if (whereClause != null) {
				for (String key : valuesCriteria.keySet()) {
					ValueCriteriaTO valueCriteriaTO = valuesCriteria.get(key);
					Object value = valueCriteriaTO.getValue();
					/*/* No esta funcionando con JDK 1.7
                     if (value instanceof XMLGregorianCalendarImpl) {
                     XMLGregorianCalendarImpl valueXML = (XMLGregorianCalendarImpl) value;
                     value = valueXML.toGregorianCalendar().getTime();

                     }
					 */

					if (value != null) {
						if (valueCriteriaTO instanceof MatchCriteriaTO) {

							MatchCriteriaTO mat = (MatchCriteriaTO) valueCriteriaTO;
							String matchType = "";
							if (mat.getMatchType().equalsIgnoreCase(MatchCriteriaTO.types.SELECTOR.getType())) {
								//si es selector el matchType es el seleccionado por el usuario qeu viene en el valueCriteriaTO
								matchType = valueCriteriaTO.getMatchCriteriaSelector();
							} else {
								matchType = mat.getMatchType();
							}

							if (matchType.equalsIgnoreCase(MatchCriteriaTO.types.CONTAINS.getType())) {
								//si es una fecha entonces no le debemos de poner los %%
								if (!(value instanceof Date)) {
									query.setParameter(key, "%" + value + "%");
								} else {
									query.setParameter(key, value);
								}
							} else if (matchType.equalsIgnoreCase(MatchCriteriaTO.types.START_WITH.getType())) {
								query.setParameter(key, value + "%");
							} else if (matchType.equalsIgnoreCase(MatchCriteriaTO.types.END_WITH.getType())) {
								query.setParameter(key, "%" + value);
							} else if (!matchType.equalsIgnoreCase(MatchCriteriaTO.types.EMPTY.getType())
								   && !matchType.equalsIgnoreCase(MatchCriteriaTO.types.NOT_EMPTY.getType())
								   && !matchType.equalsIgnoreCase(MatchCriteriaTO.types.NULL.getType())
								   && !matchType.equalsIgnoreCase(MatchCriteriaTO.types.NOT_NULL.getType())
								   && !matchType.equalsIgnoreCase(MatchCriteriaTO.types.SUBQUERY.getType())) {
								query.setParameter(key, value);
							}

						} else {
							query.setParameter(key, value);
						}
					}
				}
			}

			if (startPosition != null) {
				query.setFirstResult(startPosition.intValue());
			}
			if (maxResult != null) {
				query.setMaxResults(maxResult.intValue());
			}

			if (count) {
				Object res = query.getSingleResult();
				List<Object[]> toRe = new ArrayList();
				Object[] obj = {new Long(res + "")};
				toRe.add(obj);
				return toRe;
			} else {
				List<Object[]> result = query.getResultList();
				return result;
			}

		} catch (Exception e) {
			DAOGeneralException eDao = new DAOGeneralException(e);
			throw eDao;

		}
	}

	///los count (*)
	public Long countByCriteria(Class<T> entityClass, CriteriaTO criteria, Long startPosition, Long maxResult) throws DAOGeneralException {
		try {

			HashMap<String, String> transientProperties = new HashMap<String, String>();

			List<String> propertyNamesList = new ArrayList();
			List<Object[]> result = this.findByCriteriaUtil(true, entityClass, criteria, null, null, startPosition, maxResult, transientProperties, propertyNamesList);
			return (Long) result.get(0)[0];
		} catch (Exception e) {
			DAOGeneralException eDao = new DAOGeneralException(e);
			throw eDao;
		}
	}

	/**
	 * Retorna las entidades de clase entidad qeu cumple que la propiedad posee
	 * el valor enviado como parametro
	 *
	 * @param entidad la clase de la entidad
	 * @param propiedad el nombre de la propiedad
	 * @param valor el valor de la propiedad
	 * @return
	 * @throws com.sofis.persistence.dao.exceptions.DAOGeneralException
	 */
	public List<T> findByOneProperty(Class entidad, String propiedad, Object valor) throws DAOGeneralException {
		try {
			String sql = "SELECT o from " + entidad.getSimpleName() + " o" + " where o." + propiedad + "= :valor";
			Query q = em.createQuery(sql);
			q.setParameter("valor", valor);
			return q.getResultList();
		} catch (Exception e) {
			DAOGeneralException eDao = new DAOGeneralException(e);
			throw eDao;
		}
	}

	/**
	 * Retorna las entidades de clase entidad qeu cumple que la propiedad posee
	 * el valor enviado como parametro
	 *
	 * @param entidad la clase de la entidad
	 * @param propiedad el nombre de la propiedad
	 * @param valor el valor de la propiedad
	 * @return
	 * @throws com.sofis.persistence.dao.exceptions.DAOGeneralException
	 */
	public List<T> findByOneProperty(Class entidad, String propiedad, Object valor, String orderBy) throws DAOGeneralException {
		try {

			String sql = null;
			if (orderBy != null) {
				sql = "SELECT o from " + entidad.getSimpleName() + " o" + " where o." + propiedad + "= :valor order by " + orderBy;
			} else {
				sql = "SELECT o from " + entidad.getSimpleName() + " o" + " where o." + propiedad + "= :valor";
			}

			Query q = em.createQuery(sql);
			q.setParameter("valor", valor);
			return q.getResultList();
		} catch (Exception e) {
			DAOGeneralException eDao = new DAOGeneralException(e);
			throw eDao;
		}
	}

	/**
	 *
	 * Retorna true si propiedadA es una coleccion, siendo property de la forma
	 * propeidad1...propiedadN....propiedadA.propiedadB
	 *
	 * @param clase la clase que tiene propiedadA
	 * @param property propeidad1...propiedadN....propiedadA.propiedadB
	 * @return
	 */
	private HashMap isCollection(Class clase, String property) {
		//la parte que es la coleccion
		//ejemplo personaId.identificacionColecion
		String collectionProp = new String();
		//la parte despues de la coleccion
		//ejemplo pais.nombre
		String collectionPropProp = new String();

		int d = property.indexOf("Collection.");
		if (d >= 0) {
			collectionProp = property.substring(0, d) + "Collection";
			collectionPropProp = property.substring(d + 11, property.length());
			HashMap r = new HashMap();
			r.put(collectionProp, collectionPropProp);
			return r;
		}
		d = property.indexOf("List.");
		if (d >= 0) {
			collectionProp = property.substring(0, d) + "List";
			collectionPropProp = property.substring(d + 5, property.length());
			HashMap r = new HashMap();
			r.put(collectionProp, collectionPropProp);
			return r;
		}

		d = property.indexOf("Set.");
		if (d >= 0) {
			collectionProp = property.substring(0, d) + "Set";
			collectionPropProp = property.substring(d + 4, property.length());
			HashMap r = new HashMap();
			r.put(collectionProp, collectionPropProp);
			return r;
		}

		return null;
	}

	/**
	 * Metodo auxiliar que setea la propiedad de un objeto
	 *
	 * @param object
	 * @param property
	 * @return
	 */
	private void setProperty(Object object, String property, Object value, Class value_class) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, IllegalArgumentException, InvocationTargetException {
		StringTokenizer st = new StringTokenizer(property, ".");
		if (st.countTokens() == 1) {
			property = property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
			Method prpopertyMehtid = object.getClass().getMethod("set" + property, value_class);
			prpopertyMehtid.invoke(object, value);
		} else {
			//tenemos que navegar por get antes de llegar al metodo que queremos
			Object navigateObj = object;
			String lastToken = "";
			int count = st.countTokens();
			while (st.hasMoreTokens()) {
				String proNav = st.nextToken();
				proNav = proNav.substring(0, 1).toUpperCase() + proNav.substring(1, proNav.length());
				Method propNavM = navigateObj.getClass().getMethod("get" + proNav);
				count = count - 1;
				lastToken = proNav;
				if (count == 0) {
					//retorna la propiedad navegando
					Method method = navigateObj.getClass().getMethod("set" + lastToken, value_class);
					method.invoke(navigateObj, value);
					return;
				}
				navigateObj = propNavM.invoke(navigateObj);
				if (navigateObj == null) {
					throw new IllegalAccessException("Destino inaccesible " + property);
				}

			}
		}
	}

	/**
	 * Metodo auxiliar que retorna la propiedad de un objeto
	 *
	 * @param object
	 * @param property
	 * @return
	 */
	private Object getProperty(Object object, String property) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, IllegalArgumentException, InvocationTargetException {
		StringTokenizer st = new StringTokenizer(property, ".");
		if (st.countTokens() == 1) {
			property = property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
			Method prpopertyMehtid = object.getClass().getMethod("get" + property);
			return prpopertyMehtid.invoke(object);
		} else {
			//tenemos que navegar por get antes de llegar al metodo que queremos
			Object navigateObj = object;
			String lastToken = "";
			int count = st.countTokens();
			while (st.hasMoreTokens()) {
				String proNav = st.nextToken();
				proNav = proNav.substring(0, 1).toUpperCase() + proNav.substring(1, proNav.length());
				Method propNavM = navigateObj.getClass().getMethod("get" + proNav);
				count = count - 1;
				lastToken = proNav;
				if (count == 0) {
					//retorna la propiedad navegando
					Method method = navigateObj.getClass().getMethod("get" + lastToken);
					return method.invoke(navigateObj);
				}
				navigateObj = propNavM.invoke(navigateObj);
				if (navigateObj == null) {
					throw new IllegalAccessException("Destino inaccesible " + property);
				}

			}

		}
		return null;

	}

	/**
	 * Retorna a partir de la entidad de tipo entityClass el nombre de la
	 * propiedad anotada con
	 *
	 * @Id
	 * @param entityClass
	 * @return
	 */
	private String getIdName(Class entityClass) {
		for (Field field : entityClass.getDeclaredFields()) {
			Class type = field.getType();
			String name = field.getName();
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (int i = 0; i < annotations.length; i++) {
				if (annotations[i].annotationType().equals(Id.class)) {
					return name;
				}
			}
		}

		if (entityClass.getSuperclass() != null) {
			return getIdName(entityClass.getSuperclass());
		}
		return null;
	}

	/**
	 * Retorna a partir de la entidad de tipo entityClass el nombre de la
	 * propiedad anotada con
	 *
	 * @Id
	 * @param entityClass
	 * @return
	 */
	private Class getIdType(Class entityClass) {
		for (Field field : entityClass.getDeclaredFields()) {
			Class type = field.getType();
			String name = field.getName();
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (int i = 0; i < annotations.length; i++) {
				if (annotations[i].annotationType().equals(Id.class)) {
					return type;
				}
			}
		}
		return null;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public static void main(String... s) throws Exception {
		//SELECT c, p.name FROM Country c LEFT OUTER JOIN c.capital p
		JpaDAOImp a = new JpaDAOImp(null);
		MatchCriteriaTO m = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.MEMBER_OF, "proyCroFk.croEntBorrados", "asd");
		MatchCriteriaTO m1 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyCroFk.croEntBorrados", "asd");
		AND_TO andTo = CriteriaTOUtils.createANDTO(m, m1);
		//:rule IN elements(v.validationRules)
		a.findEntityByCriteria(Object.class, andTo, null, null, null, null);
	}
}
