package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.RegistrosHoras;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.Date;
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
public class RegistrosHorasDao extends HibernateJpaDAOImp<RegistrosHoras, Integer> implements Serializable {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final long serialVersionUID = 1L;

    public RegistrosHorasDao(EntityManager em) {
        super(em);
    }

    public Double obtenerHorasAbrobPorProy(Integer proyPk, Integer usuId) {
        if (proyPk != null && usuId != null) {
            String queryStr = "SELECT SUM(rhHoras) FROM RegistrosHoras"
                    + " WHERE rhProyectoFk.proyPk = :proyPk"
                    + " AND rhUsuarioFk.usuId = :usuId"
                    + " AND rhAprobado = :aprobado";

            Query q = super.getEm().createQuery(queryStr);
            q.setParameter("proyPk", proyPk);
            q.setParameter("usuId", usuId);
            q.setParameter("aprobado", Boolean.TRUE);

            try {
                return (Double) q.getSingleResult();
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return null;
    }

    public Double obtenerHorasPendPorProy(Integer proyPk, Integer usuId) {
        if (proyPk != null && usuId != null) {
            String queryStr = "SELECT SUM(rhHoras) FROM RegistrosHoras"
                    + " WHERE rhProyectoFk.proyPk = :proyPk"
                    + " AND rhUsuarioFk.usuId = :usuId"
                    + " AND (rhAprobado IS NULL OR rhAprobado = :aprobado)";

            Query q = super.getEm().createQuery(queryStr);
            q.setParameter("proyPk", proyPk);
            q.setParameter("usuId", usuId);
            q.setParameter("aprobado", Boolean.FALSE);

            try {
                return (Double) q.getSingleResult();
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return null;
    }

    public boolean usuTieneHorasAprob(Integer usuId, Integer proyPk) {
        if (usuId != null && proyPk != null) {
            Double d = obtenerHorasAbrobPorProy(proyPk, usuId);
            if (d != null && d >= 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public List<Moneda> obtenerMonedasPorProy(Integer proyPk) {
        if (proyPk != null) {
            String queryStr = "SELECT v.valHorMonedaFk FROM RegistrosHoras h, ValorHora v"
                    + " WHERE h.rhUsuarioFk.usuId = v.valHorUsuarioFk.usuId"
                    + " AND h.rhProyectoFk.proyPk = :proyPk"
                    + " GROUP BY v.valHorMonedaFk";

            Query q = super.getEm().createQuery(queryStr);
            q.setParameter("proyPk", proyPk);

            return q.getResultList();
        }
        return null;
    }

    public Double obtenerImporteTotalHsAprob(Integer proyPk, Integer monPk, Integer mes, Integer year, Integer usuPk) {
        return this.obtenerImporteTotalHs(proyPk, monPk, mes, year, usuPk, Boolean.TRUE);
    }

    public Double obtenerImporteTotalHs(Integer proyPk, Integer monPk, Integer mes, Integer year, Integer usuPk, Boolean aprobado) {
        if (proyPk != null && monPk != null) {
            String queryStr = "SELECT SUM(h.rhHoras * v.valHorValor)"
                    + " FROM RegistrosHoras h, ValorHora v"
                    + " WHERE h.rhUsuarioFk.usuId = v.valHorUsuarioFk.usuId"
                    + " AND h.rhProyectoFk.proyPk = :proyPk"
                    + " AND v.valHorMonedaFk.monPk = :monPk";

            Map<String, Object> mapParam = new HashMap<>();
            if (aprobado != null) {
                mapParam.put("aprobado", aprobado);
                if (aprobado) {
                    queryStr = queryStr + " AND h.rhAprobado = :aprobado";
                } else {
                    queryStr = queryStr + " AND (h.rhAprobado IS NULL OR h.rhAprobado = :aprobado)";
                }
            }
            if (mes != null && year != null) {
                queryStr = queryStr + " AND MONTH(h.rhFecha) = :mes";
                mapParam.put("mes", mes);
            }
            if (year != null) {
                queryStr = queryStr + " AND YEAR(h.rhFecha) = :anio";
                mapParam.put("anio", year);
            }
            if (usuPk != null) {
                queryStr = queryStr + " AND h.rhUsuarioFk.usuId = :usuPk";
                mapParam.put("usuPk", usuPk);
            }

            Query q = super.getEm().createQuery(queryStr);
            q.setParameter("proyPk", proyPk);
            q.setParameter("monPk", monPk);

            Set<String> keys = mapParam.keySet();
            for (String key : keys) {
                q.setParameter(key, mapParam.get(key));
            }

            try {
                return (Double) q.getSingleResult();
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return null;
    }

    public Double obtenerImporteTotalHsPend(Integer proyPk, Integer monPk, Integer mes, Integer year, Integer usuPk) {
        return this.obtenerImporteTotalHs(proyPk, monPk, mes, year, usuPk, Boolean.FALSE);
    }
    
    public Date obtenerPrimeraFecha(Integer proyPk, boolean asc) {
        if (proyPk != null) {
            String queryStr = "SELECT h.rhFecha FROM RegistrosHoras h"
                    + " WHERE h.rhProyectoFk.proyPk = :proyPk"
                    + " ORDER BY h.rhFecha";
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

    public boolean tieneDependenciasEnt(Integer entPk) {
        if (entPk != null) {
            String queryStr = "SELECT COUNT(h.rhPk) AS cant FROM RegistrosHoras h"
                    + " WHERE h.rhEntregableFk.entPk = :entPk";
            Query query = super.getEm().createQuery(queryStr);
            query.setParameter("entPk", entPk);
            try {
                Long cant = (Long) query.getSingleResult();
                return cant > 0 ? true : false;
            } catch (Exception w) {
                logger.log(Level.SEVERE, null, w);
                return false;
            }
        }
        return false;
    }
}
