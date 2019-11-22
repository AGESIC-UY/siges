package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Gastos;
import com.sofis.entities.data.Moneda;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class GastosDAO extends HibernateJpaDAOImp<Gastos, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(GastosDAO.class.getName());      

    public GastosDAO(EntityManager em) {
        super(em);
    }

    /**
     *
     * @param proyPk
     * @param usuId
     * @param monPk
     * @param aprobado
     * @return
     */
    public Double obtenerGastosPorProyYMon(Integer proyPk, Integer usuId, Integer monPk, Boolean aprobado) {
        if (proyPk != null) {
            String queryStr = "SELECT SUM(gasImporte) FROM Gastos"
                    + " WHERE gasProyFk.proyPk = :proyPk"
                    + " AND gasMonFk.monPk = :monPk";

            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("proyPk", proyPk);
            parameterMap.put("monPk", monPk);

            if (usuId != null) {
                queryStr = queryStr + " AND gasUsuarioFk.usuId = :usuId";
                parameterMap.put("usuId", usuId);
            }

            if (aprobado != null) {
                if (aprobado) {
                    queryStr = queryStr + " AND gasAprobado = :aprobado";
                } else {
                    queryStr = queryStr + " AND (gasAprobado IS NULL OR gasAprobado = :aprobado)";
                }
                parameterMap.put("aprobado", aprobado);
            }

            Query q = super.getEm().createQuery(queryStr);

            Set<String> keySet = parameterMap.keySet();
            for (String str : keySet) {
                q.setParameter(str, parameterMap.get(str));
            }

            try {
                return (Double) q.getSingleResult();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public boolean usuTieneHorasAprob(Integer usuId, Integer proyPk) {
        if (usuId != null && proyPk != null) {
            String queryStr = "SELECT SUM(gasImporte) FROM Gastos"
                    + " WHERE gasProyFk.proyPk = :proyPk"
                    + " AND gasUsuarioFk.usuId = :usuId"
                    + " AND gasAprobado = :aprobado";

            Query q = super.getEm().createQuery(queryStr);
            q.setParameter("proyPk", proyPk);
            q.setParameter("usuId", usuId);
            q.setParameter("aprobado", Boolean.TRUE);

            Double result = (Double) q.getSingleResult();
            if (result != null && result >= 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public List<Moneda> obtenerMonedasPorProy(Integer proyPk, String db) {
        if (proyPk != null) {
            String queryStr = "SELECT g.gasMonFk FROM Gastos g"
                    + " WHERE g.gasProyFk.proyPk = :proyPk"
                    + " GROUP BY g.gasMonFk";

            if (db != null && db.trim().equalsIgnoreCase(ConstanteApp.DB_POSTGRESQL)) {
                queryStr = "SELECT m"
                        + " FROM Gastos g, Moneda m"
                        + " WHERE g.gasMonFk = m.monPk"
                        + " AND g.gasProyFk.proyPk = :proyPk"
                        + " GROUP BY m.monPk";
            }
            Query q = super.getEm().createQuery(queryStr);
            q.setParameter("proyPk", proyPk);

            return q.getResultList();
        }
        return null;
    }

    public Double obtenerImpTotalGastosPorProy(Integer proyPk, Integer monPk, Integer mes, Integer year, Boolean aprobado) {
        if (proyPk != null && monPk != null) {
            String queryStr = "SELECT SUM(gasImporte)"
                    + " FROM Gastos"
                    + " WHERE gasProyFk.proyPk = :proyPk"
                    + " AND gasMonFk.monPk = :monPk";

            if (mes != null && year != null) {
                queryStr = queryStr + " AND MONTH(gasFecha) = :mes";
            }
            if (year != null) {
                queryStr = queryStr + " AND YEAR(gasFecha) = :anio";
            }

            if (aprobado != null) {
                if (aprobado) {
                    queryStr = queryStr + " AND gasAprobado = :aprobado";
                } else {
                    queryStr = queryStr + " AND (gasAprobado IS NULL OR gasAprobado = :aprobado)";
                }
            }

            Query q = super.getEm().createQuery(queryStr);
            q.setParameter("proyPk", proyPk);
            q.setParameter("monPk", monPk);

            if (mes != null && year != null) {
                q.setParameter("mes", mes);
            }
            if (year != null) {
                q.setParameter("anio", year);
            }

            if (aprobado != null) {
                if (aprobado) {
                    q.setParameter("aprobado", Boolean.TRUE);
                } else {
                    q.setParameter("aprobado", Boolean.FALSE);
                }
            }

            try {
                return (Double) q.getSingleResult();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Si asc es true retorna la primera fecha, si no la última.
     *
     * @param proyPk
     * @param asc
     * @return
     */
    public Date obtenerPrimeraFecha(Integer proyPk, boolean asc) {
        if (proyPk != null) {
            String queryStr = "SELECT g.gasFecha FROM Gastos g"
                    + " WHERE gasProyFk.proyPk = :proyPk"
                    + " ORDER BY g.gasFecha";
            if (asc) {
                queryStr = queryStr + " ASC";
            } else {
                queryStr = queryStr + " DESC";
            }

            Query q = super.getEm().createQuery(queryStr);
            q.setParameter("proyPk", proyPk);
            q.setMaxResults(1);

            try {
                return (Date) q.getSingleResult();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
