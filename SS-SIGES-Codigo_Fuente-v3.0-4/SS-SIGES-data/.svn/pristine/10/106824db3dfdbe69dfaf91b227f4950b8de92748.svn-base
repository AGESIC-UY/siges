package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.entities.data.ProyIndices;
import com.sofis.entities.data.Proyectos;
import com.sofis.exceptions.BusinessException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class ProyectosDAO extends HibernateJpaDAOImp<Proyectos, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public ProyectosDAO(EntityManager em) {
        super(em);
    }

    public void updateIndices(Integer proyPk, ProyIndices ind) {
        if (ind != null) {
            String queryStr = "UPDATE Proyectos p SET p.proyIndices = :ind WHERE p.proyPk = :proyPk";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("ind", ind);
            query.setParameter("proyPk", proyPk);
            query.executeUpdate();
        }
    }

    public List<Proyectos> obtenerProyHermanos(Integer proyPk) {
        if (proyPk != null) {
            String queryStr = "SELECT p FROM Proyectos p"
                    + " WHERE p.proyProgFk IN("
                    + " SELECT y.proyProgFk FROM Proyectos y WHERE y.proyPk = :proyPk)";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("proyPk", proyPk);
            return query.getResultList();
        }
        return null;
    }

    public List<Proyectos> obtenerProyComboPorOrg(int orgId) {
        String queryStr = "SELECT NEW Proyectos(p.proyPk, p.proyNombre)"
                + " FROM Proyectos p"
                + " WHERE p.proyOrgFk.orgPk = :orgId"
                + " AND p.activo != :activo"
                + " AND p.proyEstFk.estOrdenProceso IS NOT NULL"
                + " AND p.proyEstFk.estOrdenProceso > :iniciado"
                + " ORDER BY p.proyNombre";
        Query query = getEm().createQuery(queryStr);
        query.setParameter("orgId", orgId);
        query.setParameter("activo", Boolean.FALSE);
        query.setParameter("iniciado", 1);
        List<Proyectos> proyectosList = query.getResultList();

        return proyectosList;
    }

    /**
     * Retorna true si el proyecto tiene solicitud de cambio de estado.
     *
     * @param progPk
     * @return Boolean
     */
    public boolean cambioEstadoPorProg(Integer progPk) {
        if (progPk != null) {
            String queryStr = "SELECT p.proyPk FROM Proyectos p"
                    + " WHERE p.proyProgFk.progPk = :progPk"
                    + " AND p.proyEstPendienteFk IS NOT NULL";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("progPk", progPk);

            return query.getResultList().size() > 0 ? true : false;
        }
        return false;
    }

    public List<Proyectos> obtenerProyProProgPk(Integer progPk) {
        if (progPk != null) {
            String queryStr = "SELECT p FROM Proyectos p"
                    + " WHERE p.proyProgFk.progPk = :progPk";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("progPk", progPk);

            List<Proyectos> result = query.getResultList();
            return result;
        }
        return null;
    }

    /**
     * Retorna primer fecha de todos los proyectos.
     *
     * @return Date
     */
    public Date obtenerPrimeraFecha() {
        String sql = "SELECT proyind_periodo_inicio"
                + " FROM proy_indices"
                + " WHERE proyind_periodo_inicio IS NOT NULL"
                + " ORDER BY proyind_periodo_inicio ASC"
                + " LIMIT 1";
        Query query = getEm().createNativeQuery(sql);
        List result = query.getResultList();

        if (result == null || result.isEmpty()) {
            return null;
        } else if (result.size() == 1) {
            Object obj = result.get(0);
            return (Date) obj;
        } else {
            BusinessException be = new BusinessException();
            be.addError(ConstantesErrores.ERROR_DEMASIADOS_RESULTADOS);
            logger.log(Level.SEVERE, be.getMessage(), be);
            throw be;
        }
    }

    /**
     * Retorna ultima fecha de todos los proyectos.
     *
     * @return Date
     */
    public Date obtenerUltimaFecha() {
        String sql = "SELECT proyind_periodo_fin"
                + " FROM proy_indices"
                + " WHERE proyind_periodo_fin IS NOT NULL"
                + " ORDER BY proyind_periodo_fin DESC"
                + " LIMIT 1";
        Query query = getEm().createNativeQuery(sql);
        List result = query.getResultList();

        if (result == null || result.isEmpty()) {
            return null;
        } else if (result.size() == 1) {
            Object obj = result.get(0);
            return (Date) obj;
        } else {
            BusinessException be = new BusinessException();
            be.addError(ConstantesErrores.ERROR_DEMASIADOS_RESULTADOS);
            logger.log(Level.SEVERE, be.getMessage(), be);
            throw be;
        }
    }
}
