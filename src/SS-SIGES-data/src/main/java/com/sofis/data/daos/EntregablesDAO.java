package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.codigueras.EstadosCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.tipos.FiltroMisTareasTO;
import com.sofis.entities.tipos.MisTareasTO;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class EntregablesDAO extends HibernateJpaDAOImp<Entregables, Integer> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(EntregablesDAO.class.getName());

	public EntregablesDAO(EntityManager em) {
		super(em);
	}

	public void deleteEntregables(Integer entPk) {
		String queryStr = "DELETE FROM Entregables e WHERE e.entPk = :entPk";
		Query query = super.getEm().createQuery(queryStr);
		query.setParameter("entPk", entPk);

		query.executeUpdate();
	}

	public List<Entregables> obtenerEntregablesPorProy(Integer proyPk) {
		if (proyPk != null) {
			String queryStr = "SELECT e FROM Proyectos p, Cronogramas c, Entregables e"
					+ " WHERE p.proyPk = :proyPk"
					+ " AND p.proyCroFk.croPk = c.croPk"
					+ " AND c.croPk = e.entCroFk.croPk";
			Query q = super.getEm().createQuery(queryStr);
			q.setParameter("proyPk", proyPk);
			try {
				return q.getResultList();
			} catch (Exception ex) {
				logger.log(Level.SEVERE, ex.getMessage(), ex);
				TechnicalException te = new TechnicalException(ex);
				te.addError(ex.getMessage());
				throw te;
			}
		}
		return null;
	}

	public boolean tieneDependencias(Integer entPk) {
		if (entPk != null) {
			// Dependencia en Pagos.
			String queryStr = "SELECT COUNT(p.pagPk) AS cant"
					+ " FROM Pagos p"
					+ " WHERE p.entregables.entPk = :entPk";
			Query query = super.getEm().createQuery(queryStr);
			query.setParameter("entPk", entPk);
			try {
				Long cant = (Long) query.getSingleResult();
				if (cant > 0) {
					return true;
				}
			} catch (Exception w) {
				logger.log(Level.SEVERE, w.getMessage(), w);
			}

			// Dependencia en RegistrosHoras.
			queryStr = "SELECT COUNT(p.rhPk) AS cant FROM RegistrosHoras p"
					+ " WHERE p.rhEntregableFk.entPk = :entPk";
			query = super.getEm().createQuery(queryStr);
			query.setParameter("entPk", entPk);
			try {
				Long cant = (Long) query.getSingleResult();
				if (cant > 0) {
					return true;
				}
			} catch (Exception w) {
				logger.log(Level.SEVERE, w.getMessage(), w);
			}

			// Dependencia en Participantes.
			queryStr = "SELECT COUNT(p.partPk) AS cant FROM Participantes p"
					+ " WHERE p.partEntregablesFk.entPk = :entPk";
			query = super.getEm().createQuery(queryStr);
			query.setParameter("entPk", entPk);
			try {
				Long cant = (Long) query.getSingleResult();
				if (cant > 0) {
					return true;
				}
			} catch (Exception w) {
				logger.log(Level.SEVERE, w.getMessage(), w);
			}

			// Dependencia en Productos.
//            queryStr = "SELECT COUNT(p.prodPk) AS cant FROM Productos p"
//                    + " WHERE p.prodEntregableFk.entPk = :entPk";
//            query = super.getEm().createQuery(queryStr);
//            query.setParameter("entPk", entPk);
//            try {
//                Long cant = (Long) query.getSingleResult();
//                if (cant > 0) {
//                    return true;
//                }
//            } catch (Exception w) {
//                logger.log(Level.SEVERE, w.getMessage(), w);
//            }
			// Dependencia en Documentos.
			queryStr = "SELECT COUNT(p.docsPk) AS cant FROM Documentos p"
					+ " WHERE p.docsEntregable.entPk = :entPk";
			query = super.getEm().createQuery(queryStr);
			query.setParameter("entPk", entPk);
			try {
				Long cant = (Long) query.getSingleResult();
				if (cant > 0) {
					return true;
				}
			} catch (Exception w) {
				logger.log(Level.SEVERE, w.getMessage(), w);
			}

			// Dependencia en Riesgos.
			queryStr = "SELECT COUNT(p.riskPk) AS cant FROM Riesgos p"
					+ " WHERE p.riskEntregable.entPk = :entPk";
			query = super.getEm().createQuery(queryStr);
			query.setParameter("entPk", entPk);
			try {
				Long cant = (Long) query.getSingleResult();
				if (cant > 0) {
					return true;
				}
			} catch (Exception w) {
				logger.log(Level.SEVERE, w.getMessage(), w);
			}

			// Dependencia en Calidad.
			queryStr = "SELECT COUNT(p.calPk) AS cant FROM Calidad p"
					+ " WHERE p.calEntFk.entPk = :entPk";
			query = super.getEm().createQuery(queryStr);
			query.setParameter("entPk", entPk);
			try {
				Long cant = (Long) query.getSingleResult();
				if (cant > 0) {
					return true;
				}
			} catch (Exception w) {
				logger.log(Level.SEVERE, w.getMessage(), w);
			}

			// Dependencia en Interesados.
			queryStr = "SELECT COUNT(p.intPk) AS cant FROM Interesados p"
					+ " WHERE p.intEntregable.entPk = :entPk";
			query = super.getEm().createQuery(queryStr);
			query.setParameter("entPk", entPk);
			try {
				Long cant = (Long) query.getSingleResult();
				if (cant > 0) {
					return true;
				}
			} catch (Exception w) {
				logger.log(Level.SEVERE, w.getMessage(), w);
			}
		}
		return false;
	}

	public List<Entregables> obtenerEntregablesPorCoord(Integer proyPk, Integer coord) throws DAOGeneralException {
		return obtenerEntregablesPorCoord(proyPk, coord, Boolean.TRUE);
	}

	public List<Entregables> obtenerEntregablesPorCoord(Integer proyPk, Integer coord, Boolean conHitos) throws DAOGeneralException {
		if (proyPk != null) {
			String queryStr = "SELECT e"
					+ " FROM Proyectos p, Cronogramas c, Entregables e"
					+ " WHERE p.proyPk = :proyPk"
					+ " AND p.proyCroFk.croPk = c.croPk"
					+ " AND c.croPk = e.entCroFk.croPk"
					+ " AND e.coordinadorUsuFk.usuId = :coord";

			if (conHitos != null && !conHitos) {
				queryStr += " AND (e.entFinEsHito IS NULL OR e.entFinEsHito = FALSE)";
			}

			Query query = super.getEm().createQuery(queryStr);
			query.setParameter("proyPk", proyPk);
			query.setParameter("coord", coord);
			try {
				return query.getResultList();
			} catch (Exception ex) {
				logger.log(Level.SEVERE, ex.getMessage(), ex);
				DAOGeneralException te = new DAOGeneralException(ex.getMessage(), ex);
				throw te;
			}
		}
		return null;
	}

	public List<Entregables> obtenerEntPorProyPk(Integer proyPk) {
		if (proyPk != null) {
			String queryStr = "SELECT e FROM Proyectos p, Cronogramas c, Entregables e"
					+ " WHERE p.proyPk = :proyPk"
					+ " AND p.proyCroFk.croPk = c.croPk"
					+ " AND c.croPk = e.entCroFk.croPk";
			Query query = super.getEm().createQuery(queryStr);
			query.setParameter("proyPk", proyPk);
			try {
				return query.getResultList();
			} catch (Exception ex) {
				logger.log(Level.SEVERE, ex.getMessage(), ex);
				TechnicalException te = new TechnicalException(ex);
				te.addError(MensajesNegocio.ERROR_ENTREGABLES_OBTENER);
				throw te;
			}
		}
		return null;
	}
        
        public List<Entregables> obtenerEntSinReferenciaPorProyPk(Integer proyPk) {
		if (proyPk != null) {
			String queryStr = "SELECT e FROM Proyectos p, Cronogramas c, Entregables e"
					+ " WHERE p.proyPk = :proyPk"
					+ " AND p.proyCroFk.croPk = c.croPk"
					+ " AND c.croPk = e.entCroFk.croPk"
					+ " AND e.esReferencia = false";
			Query query = super.getEm().createQuery(queryStr);
			query.setParameter("proyPk", proyPk);
			try {
				return query.getResultList();
			} catch (Exception ex) {
				logger.log(Level.SEVERE, ex.getMessage(), ex);
				TechnicalException te = new TechnicalException(ex);
				te.addError(MensajesNegocio.ERROR_ENTREGABLES_OBTENER);
				throw te;
			}
		}
		return null;
	}

	public List<Entregables> obtenerEntPorProgPk(Integer progPk, Boolean proyActivo) {
		if (progPk != null) {
			String queryStr = "SELECT e FROM Proyectos p, Cronogramas c, Entregables e"
					+ " WHERE p.proyProgFk.progPk = :progPk"
					+ " AND p.proyCroFk.croPk = c.croPk"
					+ " AND c.croPk = e.entCroFk.croPk";
			if (proyActivo != null) {
				queryStr = queryStr + " AND p.activo = :activo";
			}
			Query query = super.getEm().createQuery(queryStr);
			query.setParameter("progPk", progPk);
			if (proyActivo != null) {
				query.setParameter("activo", proyActivo);
			}
			try {
				return query.getResultList();
			} catch (Exception ex) {
				logger.log(Level.SEVERE, ex.getMessage(), ex);
				TechnicalException te = new TechnicalException(ex);
				te.addError(MensajesNegocio.ERROR_ENTREGABLES_OBTENER);
				throw te;
			}
		}
		return null;
	}

	public List<Entregables> obtenerEntPorCroPk(Integer croPk) {
		if (croPk != null) {
			String queryStr = "SELECT e FROM Entregables e"
					+ " WHERE e.entCroFk.croPk = :croPk"
					+ " ORDER BY e.entId";
			Query query = super.getEm().createQuery(queryStr);
			query.setParameter("croPk", croPk);
			try {
				return query.getResultList();
			} catch (Exception ex) {
				logger.log(Level.SEVERE, ex.getMessage(), ex);
				TechnicalException te = new TechnicalException(ex);
				te.addError(MensajesNegocio.ERROR_ENTREGABLES_OBTENER);
				throw te;
			}
		}
		return null;
	}
	/**
	 * Obtiene los datos necesarios para desplegar el listado de Mis Tareas
	 * según el filtro aportado.
	 *
	 * @param filtro
	 * @param orgPk
	 * @return lista de MisTareasTO
	 */
	public List<MisTareasTO> obtenerMisTareasPorFiltro(FiltroMisTareasTO filtro, Integer orgPk) {
		if (filtro != null) {

			String queryStr = "SELECT NEW com.sofis.entities.tipos.MisTareasTO"
					+ "(prog.progPk, prog.progNombre, p.proyPk,"
					+ " p.proyNombre, e.entPk, e.entNombre, e.entInicio, e.entFin, e.entDuracion, e.entParent,"
					+ " e.entInicioLineaBase, e.entDuracionLineaBase, e.entFinLineaBase, e.entProgreso,"
					+ " e.entEsfuerzo, prodSet.prodPk, e.entId, p.proyEstFk)"
					+ " FROM Entregables e LEFT JOIN e.entCroFk.proyecto p LEFT JOIN e.entCroFk.proyecto.proyProgFk prog"
					+ " LEFT JOIN e.entProductosSet prodSet"
					+ " WHERE p.proyOrgFk.orgPk = :orgPk"
					+ " AND p.activo = :proyActivo"
					+ " AND e.entParent = :parent"
					+ " AND (p.proyEstFk.estPk = :est2 OR p.proyEstFk.estPk = :est3 OR p.proyEstFk.estPk = :est4)";

			Map<String, Object> parameterMap = new HashMap<>();
			if (filtro.getProgPk() != null) {
				queryStr = StringsUtils.concat(queryStr, " AND prog.progPk = :progPk");
				parameterMap.put("progPk", filtro.getProgPk());
			}

			if (!StringsUtils.isEmpty(filtro.getProyNombre())) {
				queryStr = StringsUtils.concat(queryStr, " AND p.proyNombre LIKE :proyNombre");
				parameterMap.put("proyNombre", StringsUtils.concat("%", filtro.getProyNombre(), "%"));
			}

			if (filtro.getUsuCoordPk() != null) {
				queryStr = StringsUtils.concat(queryStr, " AND e.coordinadorUsuFk.usuId = :usuCoordId");
				parameterMap.put("usuCoordId", filtro.getUsuCoordPk());
			}

			if (filtro.getTareaFinalizada() != null && filtro.getTareaFinalizada()) {
				queryStr = StringsUtils.concat(queryStr, " AND e.entProgreso >= :progreso");
				parameterMap.put("progreso", 100);
			} else {
				queryStr = StringsUtils.concat(queryStr, " AND e.entProgreso < :progreso");
				parameterMap.put("progreso", 100);
			}

			if (filtro.getAnio() != null && filtro.getAnio() > 0) {
				Calendar calIni = new GregorianCalendar(filtro.getAnio(), Calendar.JANUARY, 1, 0, 0);
				Calendar calFin = new GregorianCalendar(filtro.getAnio(), Calendar.DECEMBER, 31, 0, 0);
				Long timeIni = calIni.getTimeInMillis();
				Long timeFin = calFin.getTimeInMillis();

				queryStr = StringsUtils.concat(queryStr, " AND ((e.entInicio <= ", timeIni.toString(), " AND e.entFin >= ", timeIni.toString(), ")",
						" OR (e.entInicio <= ", timeFin.toString(), " AND e.entFin >= ", timeFin.toString(), ")",
						" OR (e.entInicio > ", timeIni.toString(), " AND e.entFin < ", timeFin.toString(), "))");
			}

			if (filtro.getConProductos() != null && filtro.getConProductos()) {
				queryStr = StringsUtils.concat(queryStr, " AND prodSet IS NOT NULL");
			}
			if (filtro.getConProductos() != null && !filtro.getConProductos()) {
				queryStr = StringsUtils.concat(queryStr, " AND prodSet IS NULL");
			}

//            queryStr = StringsUtils.concat(queryStr, " ORDER BY prog.progNombre, p.proyNombre, e.entId");
			Query query = super.getEm().createQuery(queryStr);
			query.setParameter("orgPk", orgPk);
			query.setParameter("proyActivo", Boolean.TRUE);
			query.setParameter("parent", Boolean.FALSE);
			query.setParameter("est2", 2);
			query.setParameter("est3", 3);
			query.setParameter("est4", 4);

			if (!parameterMap.isEmpty()) {
				Set<String> keySet = parameterMap.keySet();
				for (String str : keySet) {
					query.setParameter(str, parameterMap.get(str));
				}
			}

			try {
				List<MisTareasTO> result = query.getResultList();
				return result;
			} catch (Exception ex) {
				logger.log(Level.SEVERE, ex.getMessage(), ex);
				TechnicalException te = new TechnicalException(ex);
				te.addError(MensajesNegocio.ERROR_ENTREGABLES_OBTENER);
				throw te;
			}
		}
		return null;
	}

	public List<MisTareasTO> obtenerMisTareasPorFiltroConEntregable(FiltroMisTareasTO filtro, Integer orgPk) {
		if (filtro != null) {

			String queryStr = "SELECT NEW com.sofis.entities.tipos.MisTareasTO"
					+ "(prog.progPk, prog.progNombre, p.proyPk,"
					+ " p.proyNombre, e.entPk, e.entNombre, e.entInicio, e.entFin, e.entDuracion, e.entParent,"
					+ " e.entInicioLineaBase, e.entDuracionLineaBase, e.entFinLineaBase, e.entProgreso,"
					+ " e.entEsfuerzo, prodSet.prodPk, e, p.proyEstFk)"
					+ " FROM Entregables e LEFT JOIN e.entCroFk.proyecto p LEFT JOIN e.entCroFk.proyecto.proyProgFk prog"
					+ " LEFT JOIN e.entProductosSet prodSet"
					+ " WHERE p.proyOrgFk.orgPk = :orgPk"
					+ " AND p.activo = :proyActivo"
					+ " AND e.entParent = :parent"
                                        + " AND e.esReferencia = 0"
					+ " AND (p.proyEstFk.estPk = :est2 OR p.proyEstFk.estPk = :est3 OR p.proyEstFk.estPk = :est4)";

			Map<String, Object> parameterMap = new HashMap<>();
			if (filtro.getProgPk() != null) {
				queryStr = StringsUtils.concat(queryStr, " AND prog.progPk = :progPk");
				parameterMap.put("progPk", filtro.getProgPk());
			}

			if (!StringsUtils.isEmpty(filtro.getProyNombre())) {
				queryStr = StringsUtils.concat(queryStr, " AND p.proyNombre LIKE :proyNombre");
				parameterMap.put("proyNombre", StringsUtils.concat("%", filtro.getProyNombre(), "%"));
			}

			if (filtro.getUsuCoordPk() != null) {
				queryStr = StringsUtils.concat(queryStr, " AND e.coordinadorUsuFk.usuId = :usuCoordId");
				parameterMap.put("usuCoordId", filtro.getUsuCoordPk());
			}

			// por defecto no se seleccionan las finalizadas. si se marca el check del filtro, se incluyen.
			if (!(filtro.getTareaFinalizada() != null && filtro.getTareaFinalizada())) {
//                queryStr = StringsUtils.concat(queryStr, " AND e.entProgreso >= :progreso");
//                parameterMap.put("progreso", 100);
//            } else {
				queryStr = StringsUtils.concat(queryStr, " AND e.entProgreso < :progreso");
				parameterMap.put("progreso", 100);
			}

			if (filtro.getAnio() != null && filtro.getAnio() > 0) {
				Calendar calIni = new GregorianCalendar(filtro.getAnio(), Calendar.JANUARY, 1, 0, 0);
				Calendar calFin = new GregorianCalendar(filtro.getAnio(), Calendar.DECEMBER, 31, 0, 0);
				Long timeIni = calIni.getTimeInMillis();
				Long timeFin = calFin.getTimeInMillis();

				queryStr = StringsUtils.concat(queryStr, " AND ((e.entInicio <= ", timeIni.toString(), " AND e.entFin >= ", timeIni.toString(), ")",
						" OR (e.entInicio <= ", timeFin.toString(), " AND e.entFin >= ", timeFin.toString(), ")",
						" OR (e.entInicio > ", timeIni.toString(), " AND e.entFin < ", timeFin.toString(), "))");
			}

			if (filtro.getConProductos() != null && filtro.getConProductos()) {
				queryStr = StringsUtils.concat(queryStr, " AND prodSet IS NOT NULL");
			}
			if (filtro.getConProductos() != null && !filtro.getConProductos()) {
				queryStr = StringsUtils.concat(queryStr, " AND prodSet IS NULL");
			}

//            queryStr = StringsUtils.concat(queryStr, " ORDER BY prog.progNombre, p.proyNombre, e.entId");
			Query query = super.getEm().createQuery(queryStr);
			query.setParameter("orgPk", orgPk);
			query.setParameter("proyActivo", Boolean.TRUE);
			query.setParameter("parent", Boolean.FALSE);
			query.setParameter("est2", 2);
			query.setParameter("est3", 3);
			query.setParameter("est4", 4);

			if (!parameterMap.isEmpty()) {
				Set<String> keySet = parameterMap.keySet();
				for (String str : keySet) {
					query.setParameter(str, parameterMap.get(str));
				}
			}

			try {
				List<MisTareasTO> result = query.getResultList();
				return result;
			} catch (Exception ex) {
				logger.log(Level.SEVERE, ex.getMessage(), ex);
				TechnicalException te = new TechnicalException(ex);
				te.addError(MensajesNegocio.ERROR_ENTREGABLES_OBTENER);
				throw te;
			}
		}
		return null;
	}

	/**
	 * Rotorna la suma del esfuerzo de los entregables del proyecto aportado. Si
	 * se aporta el avance, se suman todos los que superan dicho avance.
	 *
	 * @param proyPk
	 * @param porcAvance
	 * @return Double
	 */
	public Double obtenerEsfuerzoTotal(Integer proyPk, Integer porcAvance) {
		if (proyPk != null) {
			String queryStr = "SELECT SUM(e.entEsfuerzo) FROM Proyectos p, Entregables e"
					+ " WHERE p.proyPk = :proyPk"
					+ " AND p.proyCroFk.croPk = e.entCroFk.croPk";

			if (porcAvance != null) {
				queryStr = queryStr + " AND e.entProgreso >= " + porcAvance;
			}

			Query query = super.getEm().createQuery(queryStr);
			query.setParameter("proyPk", proyPk);
			try {
				Long result = (Long) query.getSingleResult();
				return result != null ? (double) result : 0D;
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return null;
	}

	public boolean mesTieneEntClave(Integer mes, Integer anio, Integer proyPk, Integer tipo) {
		if (mes != null && anio != null) {
			String queryStr = "SELECT e"
					+ " FROM Proyectos p, Entregables e"
					+ " WHERE p.proyPk = :proyPk"
					+ " AND p.proyCroFk.croPk = e.entCroFk.croPk"
					//                    + " AND YEAR(e.entFin) = :anio"
					//                    + " AND MONTH(e.entFin) = :mes"
					+ " AND e.entRelevante IS NOT NULL AND e.entRelevante = :entRelevante";

			Query query = super.getEm().createQuery(queryStr);
			query.setParameter("proyPk", proyPk);
			query.setParameter("entRelevante", Boolean.TRUE);
//            query.setParameter("anio", anio);
//            query.setParameter("mes", mes);
			try {
				List<Entregables> result = query.getResultList();
				if (result != null) {
					for (Entregables ent : result) {
						Date d = null;
						if (tipo == null || tipo.equals(0)) {
							d = new Date(ent.getEntFinLineaBase() != null ? ent.getEntFinLineaBase() : ent.getEntFin());

						} else if (tipo.equals(1) || tipo.equals(2)) {
							d = new Date(ent.getEntFin());
						}

						if (d != null) {
							Calendar cal = new GregorianCalendar();
							cal.setTime(d);
							if (cal.get(Calendar.YEAR) == anio && cal.get(Calendar.MONTH) + 1 == mes) {
								return true;
							}
						}
					}
				}
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return false;
	}

	public boolean entregableTieneClave(Integer entPk, Integer tipo) {
		if (entPk != null) {
			String queryStr = "SELECT e.entPk"
					+ " FROM Entregables e"
					+ " WHERE e.entPk = :entPk"
					+ " AND e.entRelevante IS NOT NULL "
					+ " AND e.entRelevante = :entRelevante";

			Query query = super.getEm().createQuery(queryStr);
			query.setParameter("entPk", entPk);
			query.setParameter("entRelevante", Boolean.TRUE);
			try {
				List<Entregables> result = query.getResultList();
				if (result != null && !result.isEmpty()) {
					return true;
				}
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return false;
	}

	public Date obtenerPrimeraFecha(Integer orgPk) {
		if (orgPk != null) {
			String queryStr = "SELECT e.entInicio"
					+ " FROM Entregables e"
					+ " WHERE e.entCroFk.proyecto.proyOrgFk.orgPk = :orgPk"
					+ " AND (e.entCroFk.proyecto.proyEstFk.estCodigo = :estCod1"
					+ " OR e.entCroFk.proyecto.proyEstFk.estCodigo = :estCod2"
					+ " OR e.entCroFk.proyecto.proyEstFk.estCodigo = :estCod3)"
					+ " ORDER BY e.entInicio ASC";

			Query query = super.getEm().createQuery(queryStr);
			query.setParameter("orgPk", orgPk);
			query.setParameter("estCod1", EstadosCodigos.INICIO);
			query.setParameter("estCod2", EstadosCodigos.PLANIFICACION);
			query.setParameter("estCod3", EstadosCodigos.EJECUCION);
			query.setMaxResults(1);

			List<Long> result = query.getResultList();
			Long finLong = (Long) DAOUtils.obtenerSingleResult(result);

			if (finLong != null) {
				return new Date(finLong);
			}
		}
		return null;
	}

	public Date obtenerUltimaFecha(Integer orgPk) {
		if (orgPk != null) {
			String queryStr = "SELECT e.entFin"
					+ " FROM Entregables e"
					+ " WHERE e.entCroFk.proyecto.proyOrgFk.orgPk = :orgPk"
					+ " AND (e.entCroFk.proyecto.proyEstFk.estCodigo = :estCod1"
					+ " OR e.entCroFk.proyecto.proyEstFk.estCodigo = :estCod2"
					+ " OR e.entCroFk.proyecto.proyEstFk.estCodigo = :estCod3)"
					+ " ORDER BY e.entFin DESC";

			Query query = super.getEm().createQuery(queryStr);
			query.setParameter("orgPk", orgPk);
			query.setParameter("estCod1", EstadosCodigos.INICIO);
			query.setParameter("estCod2", EstadosCodigos.PLANIFICACION);
			query.setParameter("estCod3", EstadosCodigos.EJECUCION);
			query.setMaxResults(1);

			List<Long> result = query.getResultList();
			Long finLong = (Long) DAOUtils.obtenerSingleResult(result);

			if (finLong != null) {
				return new Date(finLong);
			}
		}
		return null;
	}

	public List<Integer> proyPkEntSinParent() {
		String queryStr = "SELECT p.proyPk"
				+ " FROM Proyectos p,"
				+ " IN(p.proyCroFk.entregablesSet) e"
				+ " WHERE e.entParent IS NULL";
		Query q = super.getEm().createQuery(queryStr);
		try {
			return q.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			TechnicalException te = new TechnicalException(ex);
			te.addError(ex.getMessage());
			throw te;
		}
	}

	public boolean entregableEsHito(Integer entPk) {
		String queryStr = "SELECT e.entFinEsHito"
				+ " FROM Entregables e"
				+ " WHERE e.entPk = :entPk";
		Query q = super.getEm().createQuery(queryStr);
		q.setParameter("entPk", entPk);

		try {
			Boolean result = (Boolean) q.getSingleResult();
			return result != null && result == true;

		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			TechnicalException te = new TechnicalException(ex);
			te.addError(ex.getMessage());
			throw te;
		}
	}
        
        public List<Entregables> obtenerEntregablesReferenciando(Integer idEntregable) {
		
		return em.createNamedQuery("Entregables.findEntregablesReferenciandoPorId", Entregables.class)
				.setParameter("idEntregable", idEntregable)
				.getResultList();
	}


}
