package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class PagosDAO extends HibernateJpaDAOImp<Pagos, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public PagosDAO(EntityManager em) {
        super(em);
    }

    public List<Pagos> obtenerPagosPorFichaId(Integer fichaFk, Integer tipoFicha) {
        String queryStr = "";
        if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
            queryStr = "SELECT d FROM Programas p, IN(p.progPreFk.adquisicionSet) c, IN(c.pagosSet) d"
                    + " WHERE p.progPk = :fichaFk";

        } else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
            queryStr = "SELECT d FROM Proyectos p, IN(p.proyPreFk.adquisicionSet) c, IN(c.pagosSet) d"
                    + " WHERE p.proyPk = :fichaFk";
        }

        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("fichaFk", fichaFk);

        @SuppressWarnings("unchecked")
        List<Pagos> resultado = query.getResultList();
        return resultado;
    }

    public Pagos obtenerPagosPorId(Integer pagPk) {
        return super.getEm().find(Pagos.class, pagPk);
    }

    public void deletePagos(Integer pagPk) {
        String queryStr = "DELETE FROM Pagos p WHERE p.pagPk = :pagPk";
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("pagPk", pagPk);

        query.executeUpdate();
    }

    /**
     * Retorna los pagos no confirmados cuya fecha real esté pasada varios días,
     * según los días aportados.
     *
     * @param proyPk
     * @param diasVencido
     * @return
     */
    public List<Pagos> obtenerPagosDiasVenc(Integer proyPk, int diasVencido) {
        String queryStr = "SELECT d FROM Proyectos p, IN(p.proyPreFk.adquisicionSet) c, IN(c.pagosSet) d"
                + " WHERE p.proyPk = :proyPk"
                + " AND d.entregables.entProgreso >= :progreso"
                + " AND (d.pagConfirmar IS NULL OR d.pagConfirmar = :confirmar)"
                + " AND d.pagFechaReal <= :fecha";
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("proyPk", proyPk);
        query.setParameter("progreso", 100);
        query.setParameter("confirmar", Boolean.FALSE);

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DATE, (diasVencido * (-1)));
        query.setParameter("fecha", cal.getTime());

        List<Pagos> resultado = query.getResultList();
        return resultado;
    }

    public Date obtenerPrimeraFecha() {
        String queryStr = "SELECT MIN(p.pagFechaPlanificada), MIN(p.pagFechaReal) FROM Pagos p";
        Query query = super.getEm().createQuery(queryStr);
        try {
            Object[] result = (Object[]) query.getSingleResult();
            Date d1 = (Date) result[0];
            Date d2 = (Date) result[1];

            if (d1.before(d2)) {
                return d1;
            } else {
                return d2;
            }
        } catch (Exception w) {
            w.printStackTrace();
            return null;
        }

    }

    public Date obtenerUltimaFecha() {
       String queryStr = "SELECT MAX(p.pagFechaPlanificada), MAX(p.pagFechaReal) FROM Pagos p";
        Query query = super.getEm().createQuery(queryStr);
        try {
            Object[] result = (Object[]) query.getSingleResult();
            Date d1 = (Date) result[0];
            Date d2 = (Date) result[1];

            if (d1.before(d2)) {
                return d2;
            } else {
                return d1;
            }
        } catch (Exception w) {
            w.printStackTrace();
            return null;
        }

    }
}
