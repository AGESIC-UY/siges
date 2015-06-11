package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.Presupuesto;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class PresupuestoDAO extends HibernateJpaDAOImp<Presupuesto, Integer> implements Serializable {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final long serialVersionUID = 1L;

    public PresupuestoDAO(EntityManager em) {
        super(em);
    }

    public List<Moneda> obtenerMonedasPresupuesto(Integer prePk) {
        String query = "SELECT distinct b.adqMoneda"
                + " FROM Presupuesto a, IN(a.adquisicionSet) b"
                + " WHERE a.prePk = :prePk";
        Query q = super.getEm().createQuery(query);
        q.setParameter("prePk", prePk);
        return q.getResultList();
    }

    public List<Moneda> obtenerMonedasPresupuestoPrograma(Integer progPk) {
        String query = "SELECT distinct b.adqMoneda"
                + " FROM Programas p,"
                + " IN(p.proyectosSet) proy,"
                + " IN(proy.proyPreFk.adquisicionSet) b"
                + " WHERE proy.proyProgFk.progPk = :progPk";
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

        Double reutValue = (Double) q.getSingleResult();
        if (reutValue == null) {
            return 0d;
        }
        return reutValue;
    }

    /**
     * Retorna el valor planificado por moneda del mes y año aportado.
     *
     * @param prePk
     * @param adqPk
     * @param mon
     * @param anio
     * @param mes
     * @return Double
     */
    public Double obtenerPVPorMoneda(Integer prePk, Integer adqPk, Moneda mon, int anio, int mes) {
        String query = "SELECT SUM(c.pagImportePlanificado)"
                + " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
                + " WHERE a.prePk = :prePk"
                + " AND b.adqMoneda.monPk = :monPk"
                + " AND YEAR(c.pagFechaPlanificada) = :anio"
                + " AND MONTH(c.pagFechaPlanificada) = :mes";

        if (adqPk != null) {
            query = query + " AND b.adqPk = :adqPk";
        }

        Query q = super.getEm().createQuery(query);
        q.setParameter("prePk", prePk);
        q.setParameter("monPk", mon.getMonPk());
        q.setParameter("anio", anio);
        q.setParameter("mes", mes);
        if (adqPk != null) {
            q.setParameter("adqPk", adqPk);
        }

        Double reutValue = (Double) q.getSingleResult();
        if (reutValue == null) {
            return 0d;
        }
        return reutValue;
    }
    
    /**
     * Retorna el valor planificado acumulado por moneda hasta el mes y año aportado.
     * @param prePk
     * @param adqPk
     * @param mon
     * @param anio
     * @param mes
     * @return 
     */
    public Double obtenerPVPorMonedaAcu(Integer prePk, Integer adqPk, Moneda mon, int anio, int mes) {
        String query = "SELECT SUM(c.pagImportePlanificado)"
                + " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
                + " WHERE a.prePk = :prePk"
                + " AND b.adqMoneda.monPk = :monPk"
                + " AND (YEAR(c.pagFechaPlanificada) < :anio"
                + " OR YEAR(c.pagFechaPlanificada) = :anio AND MONTH(c.pagFechaPlanificada) <= :mes)";

        if (adqPk != null) {
            query = query + " AND b.adqPk = :adqPk";
        }

        Query q = super.getEm().createQuery(query);
        q.setParameter("prePk", prePk);
        q.setParameter("monPk", mon.getMonPk());
        q.setParameter("anio", anio);
        q.setParameter("mes", mes);
        if (adqPk != null) {
            q.setParameter("adqPk", adqPk);
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
                + " AND c.pagFechaPlanificada <= :now"
                + " AND YEAR(c.pagFechaPlanificada) = :anio"
                + " AND MONTH(c.pagFechaPlanificada) = :mes";

        Query q = super.getEm().createQuery(query);
        q.setParameter("progPk", progPk);
        q.setParameter("monPk", p.getMonPk());
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

    public Double obtenerPVPorMonedaProg(Integer progPk, Integer monPk) {
        String query = "SELECT SUM(c.pagImportePlanificado)"
                + " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c, Proyectos p"
                + " WHERE p.proyProgFk.progPk = :progPk"
                + " AND p.proyPreFk.prePk = a.prePk"
                + " AND b.adqMoneda.monPk = :monPk"
                + " AND c.pagFechaPlanificada <= :now";

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
     * Retorna la suma del costo actual(real) confirmado y según la moneda.
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

    /**
     * Retorna la suma del costo actual(real) confirmado y según la moneda.
     * @param prePk
     * @param adqPk
     * @param p
     * @param anio
     * @param mes
     * @return Double
     */
    public Double obtenerACPorMoneda(Integer prePk, Integer adqPk, Moneda p, int anio, int mes) {
        String query = "SELECT SUM(c.pagImporteReal)"
                + " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
                + " WHERE a.prePk = :prePk"
                + " AND b.adqMoneda.monPk = :monPk"
                + " AND c.pagConfirmar = :pagConfirmar"
                + " AND YEAR(c.pagFechaReal) = :anio"
                + " AND MONTH(c.pagFechaReal) = :mes";

        if (adqPk != null) {
            query = query + " AND b.adqPk = :adqPk";
        }

        Query q = super.getEm().createQuery(query);
        q.setParameter("prePk", prePk);
        q.setParameter("monPk", p.getMonPk());
        q.setParameter("pagConfirmar", Boolean.TRUE);
        q.setParameter("anio", anio);
        q.setParameter("mes", mes);
        if (adqPk != null) {
            q.setParameter("adqPk", adqPk);
        }

        Double reutValue = (Double) q.getSingleResult();
        if (reutValue == null) {
            return 0d;
        }
        return reutValue;
    }
    
    public Double obtenerACPorMonedaAcu(Integer prePk, Integer adqPk, Moneda p, int anio, int mes) {
        String query = "SELECT SUM(c.pagImporteReal)"
                + " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
                + " WHERE a.prePk = :prePk"
                + " AND b.adqMoneda.monPk = :monPk"
                + " AND c.pagConfirmar = :pagConfirmar"
                + " AND (YEAR(c.pagFechaReal) < :anio"
                + " OR (YEAR(c.pagFechaReal) = :anio AND MONTH(c.pagFechaReal) <= :mes))";

        if (adqPk != null) {
            query = query + " AND b.adqPk = :adqPk";
        }

        Query q = super.getEm().createQuery(query);
        q.setParameter("prePk", prePk);
        q.setParameter("monPk", p.getMonPk());
        q.setParameter("pagConfirmar", Boolean.TRUE);
        q.setParameter("anio", anio);
        q.setParameter("mes", mes);
        if (adqPk != null) {
            q.setParameter("adqPk", adqPk);
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
                + " AND c.pagFechaReal <= :now"
                + " AND YEAR(c.pagFechaReal) = :anio"
                + " AND MONTH(c.pagFechaReal) = :mes";

        Query q = super.getEm().createQuery(query);
        q.setParameter("progPk", progPk);
        q.setParameter("monPk", moneda.getMonPk());
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
                + " AND c.pagFechaReal <= :now";

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

    public Presupuesto obtenerPresupuestoPorProy(Integer proyPk) {
        String quer = "SELECT p.proyPreFk FROM Proyectos p WHERE p.proyPk = :proyPk";
        Query q = super.getEm().createQuery(quer);
        q.setParameter("proyPk", proyPk);

        try {
            return (Presupuesto) q.getSingleResult();
        } catch (Exception w) {
            return null;
        }
    }

    public Double obtenerPRPorMoneda(Integer prePk, Integer adqPk, Moneda mon, int anio, int mes) {
        String query = "SELECT SUM(c.pagImporteReal)"
                + " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
                + " WHERE a.prePk = :prePk"
                + " AND b.adqMoneda.monPk = :monPk"
                + " AND (c.pagConfirmar = :pagConfirmar OR c.pagConfirmar IS NULL)"
                + " AND YEAR(c.pagFechaReal) = :anio"
                + " AND MONTH(c.pagFechaReal) = :mes";
        
        if (adqPk != null) {
            query = query + " AND b.adqPk = :adqPk";
        }

        Query q = super.getEm().createQuery(query);
        q.setParameter("prePk", prePk);
        q.setParameter("monPk", mon.getMonPk());
//        Date d = new Date();
//        q.setParameter("now", d);
        q.setParameter("pagConfirmar", Boolean.FALSE);
        q.setParameter("anio", anio);
        q.setParameter("mes", mes);
        if (adqPk != null) {
            q.setParameter("adqPk", adqPk);
        }

        Double reutValue = (Double) q.getSingleResult();
        if (reutValue == null) {
            return 0d;
        }
        return reutValue;
    }
    
    public Double obtenerPRPorMonedaAcu(Integer prePk, Integer adqPk, Moneda mon, int anio, int mes) {
        String query = "SELECT SUM(c.pagImporteReal)"
                + " FROM Presupuesto a, IN(a.adquisicionSet) b, IN(b.pagosSet) c"
                + " WHERE a.prePk = :prePk"
                + " AND b.adqMoneda.monPk = :monPk"
                + " AND (c.pagConfirmar = :pagConfirmar OR c.pagConfirmar IS NULL)"
                + " AND (YEAR(c.pagFechaReal) < :anio"
                + " OR (YEAR(c.pagFechaReal) = :anio AND MONTH(c.pagFechaReal) <= :mes))";

        if (adqPk != null) {
            query = query + " AND b.adqPk = :adqPk";
        }

        Query q = super.getEm().createQuery(query);
        q.setParameter("prePk", prePk);
        q.setParameter("monPk", mon.getMonPk());
//        Date d = new Date();
//        q.setParameter("now", d);
        q.setParameter("pagConfirmar", Boolean.FALSE);
        q.setParameter("anio", anio);
        q.setParameter("mes", mes);
        if (adqPk != null) {
            q.setParameter("adqPk", adqPk);
        }

        Double reutValue = (Double) q.getSingleResult();
        if (reutValue == null) {
            return 0d;
        }
        return reutValue;
    }

    public boolean obtenerPRAtrasado(Integer prePk, Integer adqPk, Moneda mon, int anio, int mes) {
        String query = "SELECT c"
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
        return result != null && result.size() > 0;
    }
}