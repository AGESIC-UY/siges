package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.ProgIndices;
import com.sofis.entities.data.Programas;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class ProgramasDAO extends HibernateJpaDAOImp<Programas, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public ProgramasDAO(EntityManager em) {
        super(em);
    }

    public void updateIndices(Integer progPk, ProgIndices ind) {
        if (ind != null) {
            String queryStr = "UPDATE Programas p SET p.progIndices = :ind WHERE p.progPk = :progPk";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("ind", ind);
            query.setParameter("progPk", progPk);
            query.executeUpdate();
        }
    }

    public Integer calcularPesoPrograma(Integer progPk) {
        if (progPk == null) {
            return null;
        }
        String queryStr = "SELECT SUM(p.proyPeso)"
                + " FROM Proyectos p"
                + " WHERE p.proyProgFk.progPk = " + progPk + ""
                + " AND p.activo = true"
                + " AND p.proyPeso IS NOT NULL"
                + " AND  p.proyEstFk.estPk IN (" + Estados.ESTADOS.INICIO.estado_id + ","
                + Estados.ESTADOS.PLANIFICACION.estado_id + ","
                + Estados.ESTADOS.EJECUCION.estado_id + ","
                + Estados.ESTADOS.FINALIZADO.estado_id + ")";
        Query query = getEm().createQuery(queryStr);
        Long peso = (Long) query.getSingleResult();
        if (peso == null) {
            return null;
        }
        return peso.intValue();
    }

    public List<Programas> obtenerProgComboPorOrg(int orgId) {
        String queryStr = "SELECT NEW Programas(p.progPk, p.progNombre)"
                + " FROM Programas p"
                + " WHERE p.progOrgFk.orgPk = :orgId"
                + " AND p.activo != :activo"
                + " AND p.progEstFk.estOrdenProceso IS NOT NULL"
                + " AND p.progEstFk.estOrdenProceso > :iniciado"
                + " ORDER BY p.progNombre";
        Query query = getEm().createQuery(queryStr);
        query.setParameter("orgId", orgId);
        query.setParameter("activo", Boolean.FALSE);
        query.setParameter("iniciado", 1);
        List<Programas> programasList = query.getResultList();

        return programasList;
    }

    public Date obtenerMenorFechaActProy(Integer progPk) {
        if (progPk == null) {
            return null;
        }
        String queryStr = "SELECT p.proyFechaAct FROM Proyectos p"
                + " WHERE p.proyProgFk.progPk = :progPk"
                + " AND p.activo = :activo"
                + " AND  p.proyEstFk.estPk IN (" 
                + Estados.ESTADOS.INICIO.estado_id + ","
                + Estados.ESTADOS.PLANIFICACION.estado_id + ","
                + Estados.ESTADOS.EJECUCION.estado_id + ")"
                + " ORDER BY p.proyFechaAct ASC";

        Query query = getEm().createQuery(queryStr);
        query.setParameter("progPk", progPk);
        query.setParameter("activo", Boolean.TRUE);
        query.setMaxResults(1);

        List<Date> result = query.getResultList();
        return (Date) DAOUtils.obtenerSingleResult(result);
    }

    public Estados obtenerMenorEstadoProy(Integer progPk) {
        if (progPk == null) {
            return null;
        }
        String queryStr = "SELECT p.proyEstFk FROM Proyectos p"
                + " WHERE p.proyProgFk.progPk = " + progPk + ""
                + " AND p.activo = true"
                + " AND  p.proyEstFk.estPk IN (" 
                + Estados.ESTADOS.INICIO.estado_id + ","
                + Estados.ESTADOS.PLANIFICACION.estado_id + ","
                + Estados.ESTADOS.EJECUCION.estado_id + ","
                + Estados.ESTADOS.FINALIZADO.estado_id + ")"
                + " ORDER BY p.proyEstFk.estOrdenProceso ASC";

        Query query = getEm().createQuery(queryStr);
        query.setMaxResults(1);

        List<Estados> result = query.getResultList();
        return (Estados) DAOUtils.obtenerSingleResult(result);
    }

    
    public Date obtenerPrimeraFecha(Integer progPk) throws DAOGeneralException {
         String queryStr = "SELECT min(p.proyIndices.proyindPeriodoInicio) FROM Proyectos p "
                + " WHERE p.proyProgFk.progPk = " + progPk + ""
                + " AND p.activo = true";
        Query query = getEm().createQuery(queryStr);
        return (Date)query.getSingleResult();
    }
    public Date obtenerUltimaFecha(Integer progPk) throws DAOGeneralException {
        String queryStr = "SELECT max(p.proyIndices.proyindPeriodoFin) FROM Proyectos p "
                + " WHERE p.proyProgFk.progPk = " + progPk + ""
                + " AND p.activo = true";
        Query query = getEm().createQuery(queryStr);
        return (Date)query.getSingleResult();
    }
    
    public List<Integer> obtenerTodosIdsOrgFk(Integer orgFk) throws DAOGeneralException{
        String queryStr = "SELECT p.progPk"
                + " FROM Programas p"
                + " WHERE p.progOrgFk.orgPk = :orgFk"
                + " AND p.activo = :activo";
        Query query = getEm().createQuery(queryStr);
        query.setParameter("orgFk", orgFk);
        query.setParameter("activo", Boolean.TRUE);
        List<Integer> programasIdsList = query.getResultList();
        return programasIdsList == null ? new ArrayList<Integer>() : programasIdsList;

    }
}
