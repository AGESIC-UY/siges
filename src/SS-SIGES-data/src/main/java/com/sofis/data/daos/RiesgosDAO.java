package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Riesgos;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class RiesgosDAO extends HibernateJpaDAOImp<Riesgos, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public RiesgosDAO(EntityManager em) {
        super(em);
    }

    public Date obtenerDateUltimaActualizacion(Integer fichaFk) {
        String queryStr = "SELECT r.riskFechaActualizacion"
                + " FROM com.sofis.entities.data.Riesgos r"
                + " WHERE r.riskProyFk.proyPk = :proyFk"
                + " ORDER BY r.riskFechaActualizacion DESC, r.riskPk DESC";
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("proyFk", fichaFk);
        query.setMaxResults(1);

        List resultList = query.getResultList();
        return (Date) DAOUtils.obtenerSingleResult(resultList);
    }

    public List<Riesgos> obtenerRiesgosNoSuperados(Integer proyPk) {
        String queryStr = "SELECT r"
                + " FROM com.sofis.entities.data.Riesgos r"
                + " WHERE ( r.riskSuperado IS NULL OR r.riskSuperado <> :superado)"
                + " AND r.riskProyFk.proyPk = :proyPk";
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("proyPk", proyPk);
        query.setParameter("superado", true);
        List<Riesgos> resultList = query.getResultList();

        return resultList;
    }

    public List<Riesgos> obtenerRiesgosPorProyecto(Integer proyPk) {
        String queryStr = "SELECT r"
                + " FROM com.sofis.entities.data.Riesgos r"
                + " WHERE r.riskProyFk.proyPk = :proyPk"
                + " ORDER BY r.exposicion DESC, r.riskPk DESC";
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("proyPk", proyPk);
        List<Riesgos> resultList = query.getResultList();
        return resultList;
    }

    public List<Riesgos> obtenerResumenRiesgos(Integer proyPk, int size) {
        if (proyPk != null) {
            String queryStr = "SELECT r"
                    + " FROM com.sofis.entities.data.Riesgos r"
                    + " WHERE (r.riskSuperado IS NULL OR r.riskSuperado = :superado)"
                    + " AND r.riskProyFk.proyPk = :proyPk"
                    + " ORDER BY r.exposicion DESC, r.riskPk DESC";
            Query query = super.getEm().createQuery(queryStr);
            query.setParameter("superado", false);
            query.setParameter("proyPk", proyPk);
            if (size > 0) {
                query.setMaxResults(size);
            }
            List<Riesgos> resultList = query.getResultList();
            return resultList;
        }
        return null;
    }

    public Long obtenerCantRiesgosAltosPrograma(Integer progPk, double semaforoRojo) {

        String queryStr = "SELECT count(r.riskPk)"
                + " FROM Proyectos p, IN(p.riesgosList) r"
                + " WHERE p.proyProgFk.progPk = " + progPk
                + " AND (p.proyEstFk.estPk = 2 OR p.proyEstFk.estPk = 3 OR p.proyEstFk.estPk = 4) "
                + " AND p.activo = true"
                + " AND (r.riskSuperado = :superado OR r.riskSuperado is NULL)"
                + " AND r.exposicion >= " + semaforoRojo;

        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("superado", false);
        return (Long) query.getSingleResult();
    }

    public Double obtenerExposicionRiesgoPrograma(Integer progPk) {
        String queryStr = "SELECT SUM( r.exposicion * p.proyPeso)/SUM(p.proyPeso)"
                + " FROM Proyectos p, IN(p.riesgosList) r"
                + " WHERE p.proyProgFk.progPk = " + progPk
                + " AND (p.proyEstFk.estPk = 2 OR p.proyEstFk.estPk = 3 OR p.proyEstFk.estPk = 4) "
                + " AND p.activo = true"
                + " AND (r.riskSuperado = :superado OR r.riskSuperado is NULL)"
                + " AND r.exposicion > 0";

        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("superado", false);
        return (Double) query.getSingleResult();
    }

    public Date obtenerDateUltimaActualizacionPrograma(Integer progPk) {
        String queryStr = "SELECT MAX(r.riskFechaActualizacion)"
                + " FROM  Proyectos p, IN(p.riesgosList) r "
                + " WHERE p.proyProgFk.progPk = " + progPk + " and  (r.riskSuperado IS NULL OR r.riskSuperado = :superado)";
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("superado", false);
        query.setMaxResults(1);
        return (Date) query.getSingleResult();
    }
    
    public void quitarEntregable(Integer entPk) {
        String queryStr = "UPDATE FROM Riesgos d"
                + " SET riskEntregable = null"
                + " WHERE riskEntregable.entPk = :entPk";
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("entPk", entPk);

        try {
            query.executeUpdate();
        } catch (Exception ex) {
            TechnicalException te = new TechnicalException(ex);
            te.addError(MensajesNegocio.ERROR_RIESGOS_QUITAR_ENT);
            throw te;
        }
    }
	
	public Integer obtenerIdProyectoPorIdRiesgo(Integer idRiesgo) {
		
		return em.createNamedQuery("Riesgos.findIdProyectoByIdRiesgo", Integer.class)
				.setParameter("idRiesgo", idRiesgo)
				.getSingleResult();
	}

}
