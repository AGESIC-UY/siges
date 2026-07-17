package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Riesgos;
import com.sofis.entities.tipos.FiltroRiesgosTO;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
    public List<Riesgos> obtenerRiesgosPorFiltro (FiltroRiesgosTO filtro) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Riesgos> cq = cb.createQuery(Riesgos.class);
        Root<Riesgos> root = cq.from(Riesgos.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (filtro.getId() != null) {
            predicates.add(cb.equal(root.get("riskPk"), filtro.getId()));
        }
        
        if (filtro.getOrgPk()!= null) {
            Join<Object, Object> proy = root.join("riskProyFk");
            Join<Object, Object> org = proy.join("proyOrgFk");
            predicates.add(cb.equal(org.get("orgPk"), filtro.getOrgPk()));
        }

        if (filtro.getNombreProyecto() != null && !filtro.getNombreProyecto().equals("")) {
            Join<Object, Object> proy = root.join("riskProyFk");
            predicates.add(cb.like(cb.lower(proy.<String>get("proyNombre")), "%" + filtro.getNombreProyecto().toLowerCase() + "%"));
        }
        
        if (filtro.getTipoRiesgoPk()!= null) {
            Join<Object, Object> tipo = root.join("riskTipoRiesgoFk");
            predicates.add(cb.equal(tipo.get("trsPk"), filtro.getTipoRiesgoPk()));
        }

        if (filtro.getIdOrganismo() != null) {
            // root -> Riesgos -> riskProyFk -> proyOrgFk -> orgPk
            Join<Object, Object> proy = root.join("riskProyFk");
            predicates.add(cb.equal(proy.get("proyOrgFk"), filtro.getIdOrganismo()));
        }

        if (filtro.getIdAdjunto() != null) {
            Join<Object, Object> proy = root.join("riskProyFk");
            
            predicates.add(cb.or(cb.equal(proy.get("proyUsrGerenteFk").get("usuId"), filtro.getIdAdjunto()),
                    cb.equal(proy.get("proyUsrAdjuntoFk").get("usuId"), filtro.getIdAdjunto())));
        }

        if (filtro.getIdPmof() != null) {
            Join<Object, Object> proy = root.join("riskProyFk");
            predicates.add(cb.equal(proy.get("proyUsrPmofedFk").get("usuId"), filtro.getIdPmof()));
        }
        
        if (filtro.getNombreRiesgo() != null && !filtro.getNombreRiesgo().equals("")) {
            predicates.add(cb.like(cb.lower(root.<String>get("riskNombre")), "%" + filtro.getNombreRiesgo().toLowerCase() + "%"));
        }

        if (filtro.getIdSponsor() != null) {
            Join<Object, Object> proy = root.join("riskProyFk");
            predicates.add(cb.equal(proy.get("proySponsorFk"), filtro.getIdSponsor()));
        }

        if (filtro.getIdArea() != null) {
            Join<Object, Object> proy = root.join("riskProyFk");
            predicates.add(cb.equal(proy.get("proyAreaFk").get("areaPk"), filtro.getIdArea()));
        }

        if (filtro.getEstadosProyecto() != null && filtro.getEstadosProyecto().length > 0) {
            Join<Object, Object> proy = root.join("riskProyFk");
            Join<Object, Object> estado = proy.join("proyEstFk");
            predicates.add(estado.get("estPk").in(filtro.getEstadosProyecto()));
        }
        
        if (filtro.getEstados()!= null && filtro.getEstados().length > 0) {
            agregarCondicionesDeEstado(filtro, predicates, cb, root);
        }
        
        if (filtro.getCodigo() != null && filtro.getCodigo() != 0) {
            Join<Object, Object> proy = root.join("riskProyFk");
            predicates.add(proy.get("proyPk").in(filtro.getCodigo()));        }

        // Ignorar filtro.activo según lo indicado

        Join<Object, Object> proy = root.join("riskProyFk");
        predicates.add(cb.equal(proy.get("activo"), Boolean.TRUE));
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        cq.orderBy(
            cb.asc(proy.get("proyPk")),
            cb.asc(proy.get("proyNombre")),
            cb.asc(root.get("riskNombre"))
        );
        
        return em.createQuery(cq).getResultList();
    }

    private void agregarCondicionesDeEstado(FiltroRiesgosTO filtro, List<Predicate> predicates, CriteriaBuilder cb, Root<Riesgos> root) {
        List<Predicate> condicionesPorEstado = new ArrayList<>();

        Date fechaActual = new Date();

        for (Integer estadoRiesgo : filtro.getEstados()) {
            List<Predicate> subCondiciones = new ArrayList<>();

            if (estadoRiesgo == 3) {
                System.out.println("Atrasado");
                subCondiciones.add(cb.lessThan(root.<Date>get("riskFechaLimite"), fechaActual));
                subCondiciones.add(
                    cb.or(
                        cb.equal(root.get("riskSuperado"), 0),
                        cb.isNull(root.get("riskSuperado"))
                    )
                );
            }
            if (estadoRiesgo == 2) { 
                System.out.println("Activo");
                subCondiciones.add(cb.greaterThanOrEqualTo(root.<Date>get("riskFechaLimite"), fechaActual));
                subCondiciones.add(
                    cb.or(
                        cb.equal(root.get("riskSuperado"), 0),
                        cb.isNull(root.get("riskSuperado"))
                    )
                );
            }
            if (estadoRiesgo == 1) { 
                System.out.println("Superado");
                subCondiciones.add(cb.equal(root.get("riskSuperado"), 1));
            }

            if (!subCondiciones.isEmpty()) {
                condicionesPorEstado.add(cb.and(subCondiciones.toArray(new Predicate[0])));
            }
        }

        if (!condicionesPorEstado.isEmpty()) {
            predicates.add(cb.or(condicionesPorEstado.toArray(new Predicate[0])));
        }
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
