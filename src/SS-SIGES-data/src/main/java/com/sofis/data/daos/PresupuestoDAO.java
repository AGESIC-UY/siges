package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.Presupuesto;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PresupuestoDAO extends HibernateJpaDAOImp<Presupuesto, Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	public PresupuestoDAO(EntityManager em) {
		super(em);
	}

	public List<Moneda> obtenerMonedasPresupuesto(Integer prePk, Boolean conImporte) {
		String query;
		if (conImporte != null && conImporte) {
			query = "SELECT distinct b.adqMoneda"
					+ " FROM Presupuesto a,"
					+ " IN(a.adquisicionSet) b,"
					+ " IN(b.pagosSet) pa"
					+ " WHERE a.prePk = :prePk"
					+ " AND (pa.pagImportePlanificado > 0 OR pa.pagImporteReal > 0)";
		} else {
			query = "SELECT distinct b.adqMoneda"
					+ " FROM Presupuesto a,"
					+ " IN(a.adquisicionSet) b"
					+ " WHERE a.prePk = :prePk";
		}
		Query q = super.getEm().createQuery(query);
		q.setParameter("prePk", prePk);
		return q.getResultList();
	}

	public List<Moneda> obtenerMonedasPresupuestoPrograma(Integer progPk, Boolean conImporte) {
		String query;
		if (conImporte != null && conImporte) {
			query = "SELECT distinct b.adqMoneda"
					+ " FROM Programas p,"
					+ " IN(p.proyectosSet) proy,"
					+ " IN(proy.proyPreFk.adquisicionSet) b,"
					+ " IN(b.pagosSet) pa"
					+ " WHERE proy.proyProgFk.progPk = :progPk"
					+ " AND (pa.pagImportePlanificado > 0 OR pa.pagImporteReal > 0)";
		} else {
			query = "SELECT distinct b.adqMoneda"
					+ " FROM Programas p,"
					+ " IN(p.proyectosSet) proy,"
					+ " IN(proy.proyPreFk.adquisicionSet) b"
					+ " WHERE proy.proyProgFk.progPk = :progPk";
		}
		Query q = super.getEm().createQuery(query);
		q.setParameter("progPk", progPk);
		return q.getResultList();
	}

	public Double obtenerTotalPorMoneda(Integer prePk, Moneda p) {
		String query = "SELECT SUM(c.pagImportePlanificado)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
				+ " WHERE a.prePk = :prePk AND b.adqMoneda.monPk = :monPk";
		Query q = super.getEm().createQuery(query);
		q.setParameter("prePk", prePk);
		q.setParameter("monPk", p.getMonPk());

		Double reutValue = (Double) q.getSingleResult();
		if (reutValue == null) {
			return 0d;
		}
		return reutValue;
	}

	public Double obtenerTotalPorMonedaAnioProg(Integer progPk, Moneda p, Integer anio) {
		String query = "SELECT SUM(c.pagImportePlanificado)"
				+ " FROM Proyectos p, IN(p.proyPreFk.adquisicionSet) b, IN(b.pagosSet) c"
				+ " WHERE p.proyProgFk.progPk = :progPk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND YEAR(c.pagFechaPlanificada) = :anio";
		Query q = super.getEm().createQuery(query);
		q.setParameter("progPk", progPk);
		q.setParameter("monPk", p.getMonPk());
		q.setParameter("anio", anio);

		Double reutValue = (Double) q.getSingleResult();

		if (reutValue == null) {
			return 0d;
		}
		return reutValue;
	}

	public Double obtenerTotalPorMonedaAnio(Integer prePk, Moneda p, Integer anio) {
		String query = "SELECT SUM(c.pagImportePlanificado)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
				+ " WHERE a.prePk = :prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND YEAR(c.pagFechaPlanificada) = :anio";
		Query q = super.getEm().createQuery(query);
		q.setParameter("prePk", prePk);
		q.setParameter("monPk", p.getMonPk());
		q.setParameter("anio", anio);

		Double reutValue = (Double) q.getSingleResult();
		if (reutValue == null) {
			return 0d;
		}
		return reutValue;
	}

	/**
	 * Retorna el valor planificado por moneda hasta el día de hoy.
	 *
	 * @param prePk
	 * @param p
	 * @return Double
	 */
	public Double obtenerPVPorMoneda(Integer prePk, Moneda p) {
		String query = "SELECT SUM(c.pagImportePlanificado)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
				+ " WHERE a.prePk = :prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND c.pagFechaPlanificada <= :now";
		Query q = super.getEm().createQuery(query);
		q.setParameter("prePk", prePk);
		q.setParameter("monPk", p.getMonPk());
		Date d = new Date();
		q.setParameter("now", d);

		Double result = (Double) q.getSingleResult();
		return result != null ? result : 0D;
	}

	public Long obtenerCantidadPVPorMoneda(Integer prePk, Moneda p) {
		String query = "SELECT COUNT(c.pagImportePlanificado)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
				+ " WHERE a.prePk = :prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND c.pagFechaPlanificada <= :now";
		Query q = super.getEm().createQuery(query);
		q.setParameter("prePk", prePk);
		q.setParameter("monPk", p.getMonPk());
		Date d = new Date();
		q.setParameter("now", d);

		Long result = (Long) q.getSingleResult();
		return result != null ? result : 0;
	}

	/**
	 * Retorna el valor planificado por moneda del mes y año aportado. En caso
	 * de no aportar la fecha devuelve ºº
	 *
	 * @param prePk
	 * @param adqPk
	 * @param mon
	 * @param anio
	 * @param mes
	 * @return Double
	 */
	public Double obtenerPVPorMoneda(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda mon, Integer anio, Integer mes) {
		String query = "SELECT SUM(c.pagImportePlanificado)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
				+ " WHERE a.prePk = :prePk"
				+ " AND b.adqMoneda.monPk = :monPk";

		Map<String, Object> paramMap = new HashMap<>();

		if (anio != null) {
			query = query + " AND YEAR(c.pagFechaPlanificada) = :anio";
			paramMap.put("anio", anio);
			if (mes != null) {
				query = query + " AND MONTH(c.pagFechaPlanificada) = :mes";
				paramMap.put("mes", mes);
			}
		}

		if (adqPk != null) {
			query = query + " AND b.adqPk = :adqPk";
			paramMap.put("adqPk", adqPk);
		} else if (setAdq != null) {
			List<Integer> adqPks = new ArrayList<>();
			for (Adquisicion adq : setAdq) {
				adqPks.add(adq.getAdqPk());
			}
			query = query + " AND b.adqPk IN (:adqPks)";
			paramMap.put("adqPks", adqPks);
		}

		Query q = super.getEm().createQuery(query);
		q.setParameter("prePk", prePk);
		q.setParameter("monPk", mon.getMonPk());
		if (paramMap.size() > 0) {
			for (String key : paramMap.keySet()) {
				q.setParameter(key, paramMap.get(key));
			}
		}

		Double reutValue = (Double) q.getSingleResult();
		if (reutValue == null) {
			return 0d;
		}
		return reutValue;
	}

	/**
	 * Retorna el valor planificado acumulado por moneda hasta el mes y año
	 * aportado.
	 *
	 * @param prePk
	 * @param adqPk
	 * @param mon
	 * @param anio
	 * @param mes
	 * @return
	 */
	public Double obtenerPVPorMonedaAcu(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda mon, int anio, int mes) {
		String query = "SELECT SUM(c.pagImportePlanificado)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
				+ " WHERE a.prePk = :prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND (YEAR(c.pagFechaPlanificada) < :anio"
				+ " OR YEAR(c.pagFechaPlanificada) = :anio AND MONTH(c.pagFechaPlanificada) <= :mes)";

		if (adqPk != null) {
			query = query + " AND b.adqPk = :adqPk";
		} else if (setAdq != null && !setAdq.isEmpty()) {
			query = query + " AND b.adqPk IN (:adqPks)";
		}

		Query q = super.getEm().createQuery(query);
		q.setParameter("prePk", prePk);
		q.setParameter("monPk", mon.getMonPk());
		q.setParameter("anio", anio);
		q.setParameter("mes", mes);
		if (adqPk != null) {
			q.setParameter("adqPk", adqPk);
		} else if (setAdq != null && !setAdq.isEmpty()) {
			List<Integer> adqPks = new ArrayList<>();
			for (Adquisicion adq : setAdq) {
				adqPks.add(adq.getAdqPk());
			}
			q.setParameter("adqPks", adqPks);

		}

		Double reutValue = (Double) q.getSingleResult();
		if (reutValue == null) {
			return 0d;
		}
		return reutValue;
	}

	public Double obtenerPVPorMonedaProg(Integer progPk, Moneda p, int anio, int mes) {
		String query = "SELECT SUM(c.pagImportePlanificado)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c, Proyectos p"
				+ " WHERE p.proyProgFk.progPk = :progPk"
				+ " AND p.proyPreFk.prePk = a.prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND YEAR(c.pagFechaPlanificada) = :anio"
				+ " AND MONTH(c.pagFechaPlanificada) = :mes"
				+ " AND p.activo = TRUE";

		Query q = super.getEm().createQuery(query);
		q.setParameter("progPk", progPk);
		q.setParameter("monPk", p.getMonPk());
		Date d = new Date();
		q.setParameter("anio", anio);
		q.setParameter("mes", mes);

		Double reutValue = (Double) q.getSingleResult();
		if (reutValue == null) {
			return 0d;
		}
		return reutValue;
	}

	public Double obtenerPVPorMonedaProg(Integer progPk, Integer monPk) {
		String query = "SELECT SUM(c.pagImportePlanificado)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c, Proyectos p"
				+ " WHERE p.proyProgFk.progPk = :progPk"
				+ " AND p.proyPreFk.prePk = a.prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND c.pagFechaPlanificada <= :now"
				+ " AND p.activo = TRUE";

		Query q = super.getEm().createQuery(query);
		q.setParameter("progPk", progPk);
		q.setParameter("monPk", monPk);
		Date d = new Date();
		q.setParameter("now", d);

		Double reutValue = (Double) q.getSingleResult();
		if (reutValue == null) {
			return 0d;
		}
		return reutValue;
	}

	/**
	 * Retorna la suma del costo actual(real) confirmado y segÃºn la moneda.
	 *
	 * @param prePk
	 * @param p
	 * @return Double
	 */
	public Double obtenerACPorMoneda(Integer prePk, Moneda p) {
		String query = "SELECT SUM(c.pagImporteReal)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
				+ " WHERE a.prePk = :prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND c.pagConfirmar = :pagConfirmado";

		Query q = super.getEm().createQuery(query);
		q.setParameter("prePk", prePk);
		q.setParameter("monPk", p.getMonPk());
		q.setParameter("pagConfirmado", Boolean.TRUE);

		Double reutValue = (Double) q.getSingleResult();
		if (reutValue == null) {
			return 0d;
		}
		return reutValue;

	}

	public Integer obtenerCantidadACPorMoneda(Integer prePk, Moneda p) {
		String query = "SELECT COUNT(c.pagImporteReal)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
				+ " WHERE a.prePk = :prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND c.pagConfirmar = :pagConfirmado";

		Query q = super.getEm().createQuery(query);
		q.setParameter("prePk", prePk);
		q.setParameter("monPk", p.getMonPk());
		q.setParameter("pagConfirmado", Boolean.TRUE);

		Integer reutValue = (Integer) q.getSingleResult();
		if (reutValue == null) {
			return 0;
		}
		return reutValue;
	}

	/**
	 * Retorna la suma del costo actual(real) confirmado y según la moneda.
	 *
	 * @param prePk
	 * @param adqPk
	 * @param p
	 * @param anio
	 * @param mes
	 * @return Double
	 */
	public Double obtenerACPorMoneda(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda p, int anio, int mes) {
		String query = "SELECT SUM(c.pagImporteReal)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
				+ " WHERE a.prePk = :prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND c.pagConfirmar = :pagConfirmar"
				+ " AND YEAR(c.pagFechaReal) = :anio"
				+ " AND MONTH(c.pagFechaReal) = :mes";

		if (adqPk != null) {
			query = query + " AND b.adqPk = :adqPk";
		} else if (setAdq != null && !setAdq.isEmpty()) {
			query = query + " AND b.adqPk IN (:adqPks)";
		}

		Query q = super.getEm().createQuery(query);
		q.setParameter("prePk", prePk);
		q.setParameter("monPk", p.getMonPk());
		q.setParameter("pagConfirmar", Boolean.TRUE);
		q.setParameter("anio", anio);
		q.setParameter("mes", mes);
		if (adqPk != null) {
			q.setParameter("adqPk", adqPk);
		} else if (setAdq != null && !setAdq.isEmpty()) {
			List<Integer> adqPks = new ArrayList<>();
			for (Adquisicion adq : setAdq) {
				adqPks.add(adq.getAdqPk());
			}
			q.setParameter("adqPks", adqPks);
		}

		Double reutValue = (Double) q.getSingleResult();
		if (reutValue == null) {
			return 0d;
		}
		return reutValue;
	}

	public Double obtenerACPorMonedaAcu(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda p, int anio, int mes) {
		String query = "SELECT SUM(c.pagImporteReal)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
				+ " WHERE a.prePk = :prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND c.pagConfirmar = :pagConfirmar"
				+ " AND (YEAR(c.pagFechaReal) < :anio"
				+ " OR (YEAR(c.pagFechaReal) = :anio AND MONTH(c.pagFechaReal) <= :mes))";

		if (adqPk != null) {
			query = query + " AND b.adqPk = :adqPk";
		} else if (setAdq != null && !setAdq.isEmpty()) {
			query = query + " AND b.adqPk IN (:adqPks)";
		}

		Query q = super.getEm().createQuery(query);
		q.setParameter("prePk", prePk);
		q.setParameter("monPk", p.getMonPk());
		q.setParameter("pagConfirmar", Boolean.TRUE);
		q.setParameter("anio", anio);
		q.setParameter("mes", mes);
		if (adqPk != null) {
			q.setParameter("adqPk", adqPk);
		} else if (setAdq != null && !setAdq.isEmpty()) {
			List<Integer> adqPks = new ArrayList<>();
			for (Adquisicion adq : setAdq) {
				adqPks.add(adq.getAdqPk());
			}
			q.setParameter("adqPks", adqPks);
		}

		Double reutValue = (Double) q.getSingleResult();
		if (reutValue == null) {
			return 0d;
		}
		return reutValue;
	}

	public Double obtenerACPorMonedaProg(Integer progPk, Moneda moneda, int anio, int mes) {
		String query = "SELECT SUM(c.pagImporteReal)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c, Proyectos p"
				+ " WHERE p.proyProgFk.progPk = :progPk"
				+ " AND p.proyPreFk.prePk = a.prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND c.pagConfirmar = :pagConfirmar"
				+ " AND c.pagFechaReal <= :now"
				+ " AND YEAR(c.pagFechaReal) = :anio"
				+ " AND MONTH(c.pagFechaReal) = :mes"
				+ " AND p.activo = TRUE";

		Query q = super.getEm().createQuery(query);
		q.setParameter("progPk", progPk);
		q.setParameter("monPk", moneda.getMonPk());
		q.setParameter("pagConfirmar", Boolean.TRUE);
		Date d = new Date();
		q.setParameter("now", d);
		q.setParameter("anio", anio);
		q.setParameter("mes", mes);

		Double reutValue = (Double) q.getSingleResult();
		if (reutValue == null) {
			return 0d;
		}
		return reutValue;
	}

	public Double obtenerACPorMonedaProg(Integer progPk, Integer monPk) {
		String query = "SELECT SUM(c.pagImporteReal)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c, Proyectos p"
				+ " WHERE p.proyProgFk.progPk = :progPk"
				+ " AND p.proyPreFk.prePk = a.prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND c.pagFechaReal <= :now"
				+ " AND p.activo = :activo"
				+ " AND c.pagConfirmar = :pagConfirmar"
				+ " AND p.activo = TRUE";

		Query q = super.getEm().createQuery(query);
		q.setParameter("progPk", progPk);
		q.setParameter("monPk", monPk);
		Date d = new Date();
		q.setParameter("now", d);
		q.setParameter("activo", true);
		q.setParameter("pagConfirmar", true);

		Double reutValue = (Double) q.getSingleResult();
		if (reutValue == null) {
			return 0d;
		}
		return reutValue;
	}

	public Presupuesto obtenerPresupuestoPorProy(Integer proyPk) {
		String quer = "SELECT p.proyPreFk FROM Proyectos p WHERE p.proyPk = :proyPk";
		Query q = super.getEm().createQuery(quer);
		q.setParameter("proyPk", proyPk);

		try {
			List<Presupuesto> result = q.getResultList();
			return (Presupuesto) DAOUtils.obtenerSingleResult(result);
		} catch (Exception w) {
			return null;
		}
	}

	public Double obtenerPRPorMonedaProg(Integer progPk, Integer monPk, int anio, int mes, Boolean atrasado) {
		String query = "SELECT SUM(c.pagImporteReal)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c, Proyectos p"
				+ " WHERE p.proyProgFk.progPk = :progPk"
				+ " AND p.proyPreFk.prePk = a.prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND (c.pagConfirmar = :pagConfirmar OR c.pagConfirmar IS NULL)"
				+ " AND YEAR(c.pagFechaReal) = :anio"
				+ " AND MONTH(c.pagFechaReal) = :mes"
				+ " AND p.activo = TRUE";

		if (atrasado != null) {
			query = query + " AND c.pagFechaReal " + (atrasado ? "<" : ">=") + " NOW()";
		}

		Query q = super.getEm().createQuery(query);
		q.setParameter("progPk", progPk);
		q.setParameter("monPk", monPk);
		q.setParameter("pagConfirmar", Boolean.FALSE);
		q.setParameter("anio", anio);
		q.setParameter("mes", mes);

		Double reutValue = (Double) q.getSingleResult();
		if (reutValue == null) {
			return 0d;
		}
		return reutValue;
	}

	/**
	 * Retorna lo proyectado por moneda según el mes y año aportado.
	 *
	 * @param prePk
	 * @param adqPk
	 * @param mon
	 * @param anio
	 * @param mes
	 * @param atrasado true para los que estÃ¡n atrasados, false para los que
	 * no, y null para obtener todos.
	 * @return
	 */
	public Double obtenerPRPorMoneda(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda mon, int anio, int mes, Boolean atrasado) {
		String query = "SELECT SUM(c.pagImporteReal)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
				+ " WHERE a.prePk = :prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND (c.pagConfirmar = :pagConfirmar OR c.pagConfirmar IS NULL)"
				+ " AND YEAR(c.pagFechaReal) = :anio"
				+ " AND MONTH(c.pagFechaReal) = :mes";

		if (adqPk != null) {
			query = query + " AND b.adqPk = :adqPk";
		} else if (setAdq != null && !setAdq.isEmpty()) {
			query = query + " AND b.adqPk IN (:adqPks)";
		}
		if (atrasado != null) {
			query = query + " AND c.pagFechaReal " + (atrasado ? "<" : ">=") + " NOW()";
		}

		Query q = super.getEm().createQuery(query);
		q.setParameter("prePk", prePk);
		q.setParameter("monPk", mon.getMonPk());
		q.setParameter("pagConfirmar", Boolean.FALSE);
		q.setParameter("anio", anio);
		q.setParameter("mes", mes);
		if (adqPk != null) {
			q.setParameter("adqPk", adqPk);
		} else if (setAdq != null && !setAdq.isEmpty()) {
			List<Integer> adqPks = new ArrayList<>();
			for (Adquisicion adq : setAdq) {
				adqPks.add(adq.getAdqPk());
			}
			q.setParameter("adqPks", adqPks);
		}

		Double reutValue = (Double) q.getSingleResult();
		if (reutValue == null) {
			return 0d;
		}
		return reutValue;
	}

	public Double obtenerPRPorMonedaAcu(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda mon, int anio, int mes) {
		String query = "SELECT SUM(c.pagImporteReal)"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
				+ " WHERE a.prePk = :prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND (c.pagConfirmar = :pagConfirmar OR c.pagConfirmar IS NULL)"
				+ " AND (YEAR(c.pagFechaReal) < :anio"
				+ " OR (YEAR(c.pagFechaReal) = :anio AND MONTH(c.pagFechaReal) <= :mes))";

		if (adqPk != null) {
			query = query + " AND b.adqPk = :adqPk";
		} else if (setAdq != null && !setAdq.isEmpty()) {
			query = query + " AND b.adqPk IN (:adqPks)";
		}

		Query q = super.getEm().createQuery(query);
		q.setParameter("prePk", prePk);
		q.setParameter("monPk", mon.getMonPk());
		q.setParameter("pagConfirmar", Boolean.FALSE);
		q.setParameter("anio", anio);
		q.setParameter("mes", mes);
		if (adqPk != null) {
			q.setParameter("adqPk", adqPk);
		} else if (setAdq != null && !setAdq.isEmpty()) {
			List<Integer> adqPks = new ArrayList<>();
			for (Adquisicion adq : setAdq) {
				adqPks.add(adq.getAdqPk());
			}
			q.setParameter("adqPks", adqPks);
		}

		Double reutValue = (Double) q.getSingleResult();
		if (reutValue == null) {
			return 0d;
		}
		return reutValue;
	}

	public boolean obtenerPRAtrasado(Integer prePk, Integer adqPk, Moneda mon, int anio, int mes) {
		String query = "SELECT c.pagPk"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
				+ " WHERE a.prePk = :prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND c.pagFechaReal < :now"
				+ " AND (c.pagConfirmar IS NULL OR c.pagConfirmar = :pagConfirmar)"
				+ " AND ((YEAR(c.pagFechaReal) = :anio AND MONTH(c.pagFechaReal) <= :mes) "
				+ " OR (YEAR(c.pagFechaReal) < :anio))";

		if (adqPk != null) {
			query = query + " AND b.adqPk = :adqPk";
		}

		Query q = super.getEm().createQuery(query);
		q.setParameter("prePk", prePk);
		q.setParameter("monPk", mon.getMonPk());
		Date d = new Date();
		q.setParameter("now", d);
		q.setParameter("pagConfirmar", Boolean.FALSE);
		q.setParameter("anio", anio);
		q.setParameter("mes", mes);
		if (adqPk != null) {
			q.setParameter("adqPk", adqPk);
		}

		List result = q.getResultList();
		return result != null && !result.isEmpty();
	}

	public boolean obtenerPRAtrasadoProg(Integer progPk, Moneda mon, int anio, int mes) {
		String query = "SELECT c.pagPk"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c, Proyectos p"
				+ " WHERE p.proyProgFk.progPk = :progPk"
				+ " AND p.proyPreFk.prePk = a.prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND c.pagFechaReal < :now"
				+ " AND (c.pagConfirmar IS NULL OR c.pagConfirmar = :pagConfirmar)"
				+ " AND ((YEAR(c.pagFechaReal) = :anio AND MONTH(c.pagFechaReal) <= :mes) "
				+ " OR (YEAR(c.pagFechaReal) < :anio))"
				+ " AND p.activo = TRUE";

		Query q = super.getEm().createQuery(query);
		q.setParameter("progPk", progPk);
		q.setParameter("monPk", mon.getMonPk());
		Date d = new Date();
		q.setParameter("now", d);
		q.setParameter("pagConfirmar", Boolean.FALSE);
		q.setParameter("anio", anio);
		q.setParameter("mes", mes);

		List result = q.getResultList();
		return result != null && !result.isEmpty();
	}

	public Date obtenerPrimeraUltimaFechaPre(Integer prePk, Integer monPk, boolean primeraFecha) {
		String orden = primeraFecha ? "ASC" : "DESC";

		String query = "SELECT c.pagFechaPlanificada"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
				+ " WHERE a.prePk = :prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND c.pagFechaPlanificada IS NOT NULL"
				+ " ORDER BY c.pagFechaPlanificada " + orden;

		Query qPlan = super.getEm().createQuery(query);
		qPlan.setParameter("prePk", prePk);
		qPlan.setParameter("monPk", monPk);
		qPlan.setMaxResults(1);

		List resultPlan = qPlan.getResultList();
		Date ultPlan = (Date) DAOUtils.obtenerSingleResult(resultPlan);

		query = "SELECT c.pagFechaReal"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
				+ " WHERE a.prePk = :prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND c.pagFechaReal IS NOT NULL"
				+ " ORDER BY c.pagFechaReal " + orden;

		Query qReal = super.getEm().createQuery(query);
		qReal.setParameter("prePk", prePk);
		qReal.setParameter("monPk", monPk);
		qReal.setMaxResults(1);

		List resultReal = qReal.getResultList();
		Date ultReal = (Date) DAOUtils.obtenerSingleResult(resultReal);

		if (ultPlan != null && ultReal != null) {
			if (primeraFecha) {
				return DatesUtils.esMayor(ultPlan, ultReal) ? ultReal : ultPlan;
			}
			return DatesUtils.esMayor(ultPlan, ultReal) ? ultPlan : ultReal;
		} else {
			return ultPlan != null ? ultPlan : ultReal;
		}
	}

	public Date obtenerPrimeraUltimaFechaPreProg(Integer progPk, Integer monPk, boolean primeraFecha) {
		String orden = primeraFecha ? "ASC" : "DESC";

		String query = "SELECT c.pagFechaPlanificada"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c, Proyectos p"
				+ " WHERE p.proyProgFk.progPk = :progPk"
				+ " AND p.proyPreFk.prePk = a.prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND c.pagFechaPlanificada IS NOT NULL"
				+ " ORDER BY c.pagFechaPlanificada " + orden;

		Query qPlan = super.getEm().createQuery(query);
		qPlan.setParameter("progPk", progPk);
		qPlan.setParameter("monPk", monPk);
		qPlan.setMaxResults(1);

		List resultPlan = qPlan.getResultList();
		Date ultPlan = (Date) DAOUtils.obtenerSingleResult(resultPlan);

		query = "SELECT c.pagFechaReal"
				+ " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c, Proyectos p"
				+ " WHERE p.proyProgFk.progPk = :progPk"
				+ " AND p.proyPreFk.prePk = a.prePk"
				+ " AND b.adqMoneda.monPk = :monPk"
				+ " AND c.pagFechaReal IS NOT NULL"
				+ " ORDER BY c.pagFechaReal " + orden;

		Query qReal = super.getEm().createQuery(query);
		qReal.setParameter("progPk", progPk);
		qReal.setParameter("monPk", monPk);
		qReal.setMaxResults(1);

		List resultReal = qReal.getResultList();
		Date ultReal = (Date) DAOUtils.obtenerSingleResult(resultReal);

		if (ultPlan != null && ultReal != null) {
			if (primeraFecha) {
				return DatesUtils.esMayor(ultPlan, ultReal) ? ultReal : ultPlan;
			}
			return DatesUtils.esMayor(ultPlan, ultReal) ? ultPlan : ultReal;
		} else {
			return ultPlan != null ? ultPlan : ultReal;
		}
	}

}
