package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.codigueras.EstadosCodigos;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.ProyIndices;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.tipos.FiltroMisAdquisicionesTO;
import com.sofis.entities.tipos.FiltroProyectoTO;
import com.sofis.entities.tipos.IdNombreTO;
import com.sofis.entities.tipos.ProyectoTO;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProyectosDAO extends HibernateJpaDAOImp<Proyectos, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public ProyectosDAO(EntityManager em) {
        super(em);
    }

    public Proyectos detach(Proyectos proy) {
        //Recorrer las colecciones necesarias antes de hacer detach.
        if (proy.getProyPreFk() != null && proy.getProyPreFk().getAdquisicionSet() != null) {
            proy.getProyPreFk().getAdquisicionSet().isEmpty();
        }
        getEm().detach(proy);
        return proy;
    }

    public List<Proyectos> detach(List<Proyectos> listProy) {
        if (listProy != null) {
            List<Proyectos> result = new ArrayList<>();
            for (Proyectos proy : listProy) {
                proy = detach(proy);
                result.add(proy);
            }
            return result;
        }
        return listProy;
    }

    public void updateIndices(Integer proyPk, ProyIndices ind) {
        if (ind != null) {
            String queryStr = "UPDATE Proyectos p"
                    + " SET p.proyIndices = :ind"
                    + " WHERE p.proyPk = :proyPk";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("ind", ind);
            query.setParameter("proyPk", proyPk);
            query.executeUpdate();
        }
    }

    public List<Proyectos> obtenerProyHermanos(Integer proyPk) {
        if (proyPk != null) {
            String progKf = "SELECT y.proyProgFk.progPk FROM Proyectos y WHERE y.proyPk = :proyPk";
            Query query = getEm().createQuery(progKf);
            query.setParameter("proyPk", proyPk);
            Integer progFkId = (Integer) query.getSingleResult();
            if (progFkId != null) {
                String queryStr = "SELECT new Proyectos(p.proyPk, p.proyNombre,p.proyPeso,p.activo,p.proyEstFk) FROM Proyectos p"
                        + " WHERE p.proyProgFk.progPk = " + progFkId;
                query = getEm().createQuery(queryStr);
                return query.getResultList();
            } else {
                return new ArrayList();
            }
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
     * Retorna una lista de ID y nombre de los proyectos en la que el usuario
     * aportado es colaborador.
     *
     * @param usuId ID del usuario.
     * @param orgPk ID del Organismo.
     * @return lista de IdNombreTO conteniendo el ID y nombre de los proyectos.
     */
    public List<IdNombreTO> obtenerProyIdNombre(Integer usuId, Integer orgPk) {
        if (orgPk != null) {
            String queryStr = "SELECT NEW com.sofis.entities.tipos.IdNombreTO(p.proyPk, p.proyNombre)"
                    + " FROM Proyectos p, IN (p.participantesList) pa"
                    + " WHERE p.proyOrgFk.orgPk = :orgId"
                    + " AND p.activo != :proyActivo"
                    + " AND p.proyEstFk.estPk IN ("
                    + "SELECT e.estPk"
                    + " FROM Estados e"
                    + " WHERE e.estCodigo = :estCod1"
                    + " OR e.estCodigo = :estCod2"
                    + " OR e.estCodigo = :estCod3)"
                    + " AND (pa.partUsuarioFk.usuId = :usuId AND pa.partActivo = :partActivo)"
                    + " ORDER BY p.proyNombre";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("orgId", orgPk);
            query.setParameter("proyActivo", Boolean.FALSE);
            query.setParameter("estCod1", EstadosCodigos.INICIO);
            query.setParameter("estCod2", EstadosCodigos.PLANIFICACION);
            query.setParameter("estCod3", EstadosCodigos.EJECUCION);
            query.setParameter("usuId", usuId);
            query.setParameter("partActivo", Boolean.TRUE);

            List<IdNombreTO> proyectosList = query.getResultList();
            return proyectosList;
        }
        return null;
    }

    public boolean esColaboradorProy(Integer usuId, Integer proyPk) {
        if (proyPk != null && usuId != null) {
            String queryStr = "SELECT p.proyPk"
                    + " FROM Proyectos p, IN (p.participantesList) pa"
                    + " WHERE p.proyPk = :proyPk"
                    + " AND p.activo != :proyActivo"
                    + " AND (pa.partUsuarioFk.usuId = :usuId AND pa.partActivo = :partActivo)";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("proyPk", proyPk);
            query.setParameter("proyActivo", Boolean.FALSE);
            query.setParameter("usuId", usuId);
            query.setParameter("partActivo", Boolean.TRUE);

            List<Integer> proyectosList = query.getResultList();

            return proyectosList != null && proyectosList.size() == 1;
        }
        return false;
    }

    /**
     * Retorna true si el proyecto tiene solicitud de cambio de estado.
     * 22-05-17: tiene que filtrar los activos
     *
     * @param progPk
     * @return Boolean
     */
    public boolean cambioEstadoPorProg(Integer progPk) {
        if (progPk != null) {
//            String queryStr = "SELECT p.proyPk"
//                    + " FROM Proyectos p INNER JOIN p.proyEstPendienteFk estPend"
//                    + " WHERE p.proyProgFk.progPk = :progPk"
//                    + " AND p.proyEstPendienteFk IS NOT NULL"
//                    + " AND p.activo = TRUE";
            String queryStr = "SELECT p.proyPk"
                    + " FROM Proyectos p INNER JOIN p.proyEstPendienteFk estPend"
                    + " WHERE p.proyProgFk.progPk = :progPk"
                    + " AND estPend IS NOT NULL"
                    + " AND p.proyEstFk.estCodigo IN (:codigos)"
                    + " AND p.activo = TRUE";

            Query query = getEm().createQuery(queryStr);
            query.setParameter("progPk", progPk);
            query.setParameter("codigos", Arrays.asList("INICIO", "PLANIFICACION", "EJECUCION", "FINALIZADO", "PENDIENTE_PMOT", "PENDIENTE_PMOF"));

            return !query.getResultList().isEmpty();
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
        return (Date) DAOUtils.obtenerSingleResult(result);
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
        return (Date) DAOUtils.obtenerSingleResult(result);
    }

    public Integer obtenerOrgPkPorProyPk(Integer proyPk) {
        String queryStr = "SELECT p.proyOrgFk.orgPk"
                + " FROM Proyectos p"
                + " WHERE p.proyPk = :proyPk";
        Query query = getEm().createQuery(queryStr);
        query.setParameter("proyPk", proyPk);

        List<Integer> result = query.getResultList();
        return (Integer) DAOUtils.obtenerSingleResult(result);
    }

    public List<Proyectos> obtenerProySimplePorProgId(Integer progPk) {
        String queryStr = "SELECT new Proyectos(p.proyPk, p.proyOrgFk, p.proyIndices, p.proyNombre, p.proyPeso, p.activo, p.proyEstFk, p.proyFechaAct)"
                + " FROM Proyectos p"
                + " WHERE p.proyProgFk.progPk = :progPk";
        Query query = getEm().createQuery(queryStr);
        query.setParameter("progPk", progPk);

        List<Proyectos> result = query.getResultList();
        return result;
    }

    public void actualizarFecha(Integer proyPk) {
        if (proyPk != null) {
            String queryStr = "UPDATE Proyectos p"
                    + " SET p.proyFechaAct = :dateNow"
                    + " WHERE p.proyPk = :proyPk";

            Query query = getEm().createQuery(queryStr);
            query.setParameter("proyPk", proyPk);
            query.setParameter("dateNow", new Date());
            query.executeUpdate();
        }
    }

    public Double obtenerPorcPesoTotalProy(Integer proyPk) throws DAOGeneralException {
        Double resultadoFinal = 0d;

        String queryStr = "SELECT (proyConsultado.proy_peso*100)/tablaPesoTotal.pesoTotal"
                + " FROM proyectos proyConsultado"
                + " INNER JOIN estados estProyConsultado ON proyConsultado.proy_est_fk = estProyConsultado.est_pk,"
                + " (SELECT SUM(proy.proy_peso) AS pesoTotal"
                + " FROM proyectos proy"
                + " INNER JOIN programas prog ON proy.proy_prog_fk = prog.prog_pk"
                + " INNER JOIN estados estProy ON proy.proy_est_fk = estProy.est_pk"
                + " WHERE prog.prog_pk ="
                + " (SELECT progIn.prog_pk"
                + " FROM proyectos proyIn"
                + " INNER JOIN programas progIn ON proyIn.proy_prog_fk = progIn.prog_pk"
                + " WHERE proyIn.proy_pk = :proyPk)"
                + " AND estProy.est_codigo IN ('INICIO', 'EJECUCION', 'PLANIFICACION')"
                + " AND proy.proy_activo = TRUE)tablaPesoTotal"
                + " WHERE proyConsultado.proy_pk = :proyPk"
                + " AND estProyConsultado.est_codigo IN ('INICIO', 'EJECUCION', 'PLANIFICACION')"
                + " AND proyConsultado.proy_activo = TRUE";

        Query query = getEm().createNativeQuery(queryStr);
        query.setParameter("proyPk", proyPk);

        List<Object> results = (List<Object>) query.getResultList();

        if ((results != null) && !(results.isEmpty())) {
            if (results.get(0) instanceof BigDecimal) {

                BigDecimal resultadoConsulta = (BigDecimal) results.get(0);

                if (resultadoConsulta != null) {
                    resultadoFinal = Double.parseDouble(resultadoConsulta.toString());
                }

            } else if (results.get(0) instanceof BigInteger) {

                BigInteger resultadoConsulta = (BigInteger) results.get(0);
                if (resultadoConsulta != null) {
                    resultadoFinal = Double.parseDouble(resultadoConsulta.toString());
                }

            }
        }

        return resultadoFinal;
    }

    public List<ProyectoTO> obtenerPorFiltro(FiltroProyectoTO filtro) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<ProyectoTO> cq = cb.createQuery(ProyectoTO.class);

        Root<Proyectos> root = cq.from(Proyectos.class);
        Join<Proyectos, Areas> area = root.join("proyAreaFk", JoinType.LEFT);

        cq.select(cb.construct(ProyectoTO.class,
                root.get("proyPk"),
                root.get("proyNombre"),
                root.get("proyEstFk").get("estPk"),
                root.get("proyEstFk").get("estNombre"),
                root.get("proyUsrGerenteFk").get("usuId"),
                root.get("proyUsrGerenteFk").get("usuPrimerNombre"),
                root.get("proyUsrGerenteFk").get("usuPrimerApellido"),
                root.get("proyUsrAdjuntoFk").get("usuId"),
                root.get("proyUsrAdjuntoFk").get("usuPrimerNombre"),
                root.get("proyUsrAdjuntoFk").get("usuPrimerApellido"),
                root.get("proyFechaAct"),
                root.get("activo"),
                root.get("fechaCambioActivacion"),
                root.get("usuarioCambioActivacion"),
                area.get("areaPk"),
                area.get("areaNombre"),
                root.get("proyUsrPmofedFk").get("usuId"),
                root.get("proyUsrPmofedFk").get("usuPrimerNombre"),
                root.get("proyUsrPmofedFk").get("usuPrimerApellido"),
                root.get("proyUsrSponsorFk").get("usuId"),
                root.get("proyUsrSponsorFk").get("usuPrimerNombre"),
                root.get("proyUsrSponsorFk").get("usuPrimerApellido"))
        );

        Predicate predicate;

        List<Predicate> predicates = new ArrayList<>();

        if (filtro.getId() != null && filtro.getId() > 0) {

            predicate = cb.equal(root.get("proyPk"), filtro.getId());

            predicates.add(predicate);
        }

        if (!StringsUtils.isEmpty(filtro.getNombre())) {

            predicate = cb.or(cb.like(root.<String>get("proyNombre"), "%" + filtro.getNombre() + "%"));

            predicates.add(predicate);
        }

        if (filtro.getIdOrganismo() != null) {

            predicate = cb.equal(root.get("proyOrgFk").get("orgPk"), filtro.getIdOrganismo());

            predicates.add(predicate);
        }

        if (filtro.getIdGerente() != null && filtro.getIdGerente() != -1) {

            predicate = cb.equal(root.get("proyUsrGerenteFk").get("usuId"), filtro.getIdGerente());

            predicates.add(predicate);
        }

        if (filtro.getIdAdjunto() != null && filtro.getIdAdjunto() != -1) {

            predicate = cb.equal(root.get("proyUsrAdjuntoFk").get("usuId"), filtro.getIdAdjunto());

            predicates.add(predicate);
        }

        if (filtro.getIdPmof() != null && filtro.getIdPmof() != -1) {

            predicate = cb.equal(root.get("proyUsrPmofedFk").get("usuId"), filtro.getIdPmof());

            predicates.add(predicate);
        }

        if (filtro.getIdSponsor() != null && filtro.getIdSponsor() != -1) {

            predicate = cb.equal(root.get("proyUsrSponsorFk").get("usuId"), filtro.getIdSponsor());

            predicates.add(predicate);

        }

        if (filtro.getActivo() != null) {

            predicate = cb.equal(root.get("activo"), filtro.getActivo());

            predicates.add(predicate);

        }

        if (filtro.getEstados() != null && !filtro.getEstados().isEmpty()) {

            predicate = root.get("proyEstFk").get("estPk").in(filtro.getEstados());

            predicates.add(predicate);
        }
        
        
        if (filtro.getIdArea() != null && filtro.getIdArea() != -1) {

            predicate = cb.equal(root.get("proyAreaFk").get("areaPk"), filtro.getIdArea());

            predicates.add(predicate);

        }
        

        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        cq.orderBy(cb.desc(root.get("proyPk")));

        return em.createQuery(cq).getResultList();
    }

    public List<ProyectoTO> obtenerProyectosConAdquisiciones(FiltroMisAdquisicionesTO filtro) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<ProyectoTO> cq = cb.createQuery(ProyectoTO.class);

        Root<Proyectos> root = cq.from(Proyectos.class);
        Join<Proyectos, Programas> programa = root.join("proyProgFk", JoinType.LEFT);
        Join<Proyectos, Areas> area = root.join("proyAreaFk", JoinType.LEFT);
        Join<Proyectos, Presupuesto> presupuesto = root.join("proyPreFk");

        Join<Presupuesto, Adquisicion> adquisicion = presupuesto.join("adquisicionSet");
        Join<Adquisicion, Pagos> pagos = adquisicion.join("pagosSet", JoinType.LEFT);

        cq.select(cb.construct(ProyectoTO.class,
                root.get("proyPk"),
                root.get("proyNombre"),
                root.get("proyEstFk").get("estPk"),
                root.get("proyEstFk").get("estNombre"),
                programa.get("progPk"),
                programa.get("progNombre"),
                root.get("proyUsrGerenteFk").get("usuId"),
                root.get("proyUsrGerenteFk").get("usuPrimerNombre"),
                root.get("proyUsrGerenteFk").get("usuPrimerApellido"),
                root.get("proyUsrAdjuntoFk").get("usuId"),
                root.get("proyUsrAdjuntoFk").get("usuPrimerNombre"),
                root.get("proyUsrAdjuntoFk").get("usuPrimerApellido"),
                root.get("proyUsrPmofedFk").get("usuId"),
                root.get("proyUsrPmofedFk").get("usuPrimerNombre"),
                root.get("proyUsrPmofedFk").get("usuPrimerApellido"),
                root.get("proyUsrSponsorFk").get("usuId"),
                root.get("proyUsrSponsorFk").get("usuPrimerNombre"),
                root.get("proyUsrSponsorFk").get("usuPrimerApellido"),
                area.get("areaPk"),
                area.get("areaNombre"),
                presupuesto.get("prePk"),
                cb.count(adquisicion)));

        Predicate predicate;

        List<Predicate> predicates = new ArrayList<>();

        if (!StringsUtils.isEmpty(filtro.getNombreProyectoPrograma())) {

            predicate = cb.or(cb.like(root.<String>get("proyNombre"), "%" + filtro.getNombreProyectoPrograma() + "%"),
                    cb.like(programa.<String>get("progNombre"), "%" + filtro.getNombreProyectoPrograma() + "%"));

            predicates.add(predicate);
        }

        if (filtro.getGerenteAdjunto() != null) {

            predicate = cb.or(cb.equal(root.get("proyUsrGerenteFk").get("usuId"), filtro.getGerenteAdjunto().getUsuId()),
                    cb.equal(root.get("proyUsrAdjuntoFk").get("usuId"), filtro.getGerenteAdjunto().getUsuId()));

            predicates.add(predicate);
        }

        if (filtro.getPmof() != null) {

            predicate = cb.equal(root.get("proyUsrPmofedFk").get("usuId"), filtro.getPmof().getUsuId());

            predicates.add(predicate);
        }

        if (filtro.getEstados() != null) {

            predicate = root.get("proyEstFk").get("estPk").in(filtro.getEstados());

            predicates.add(predicate);
        }

        if (filtro.getArea() != null) {

            predicate = cb.equal(area.get("areaPk"), filtro.getArea().getAreaPk());

            predicates.add(predicate);
        }

        if (filtro.getOrganismo() != null) {

            predicate = cb.equal(root.get("proyOrgFk").get("orgPk"), filtro.getOrganismo().getOrgPk());

            predicates.add(predicate);
        }

        if (filtro.getFuenteFinanciamiento() != null) {

            predicate = cb.equal(adquisicion.get("adqFuente").get("fuePk"), filtro.getFuenteFinanciamiento().getFuePk());

            predicates.add(predicate);
        }

        if (filtro.getProcedimientoCompra() != null) {

            predicate = cb.equal(adquisicion.get("adqProcedimientoCompra").get("procCompPk"), filtro.getProcedimientoCompra().getProcCompPk());

            predicates.add(predicate);
        }

        if (filtro.getProveedor() != null) {

            predicate = cb.or(cb.equal(adquisicion.get("adqProvOrga").get("orgaPk"), filtro.getProveedor().getOrgaPk()),
                    cb.equal(pagos.get("pagProveedorFk").get("orgaPk"), filtro.getProveedor().getOrgaPk()));

            predicates.add(predicate);
        }

        if (filtro.getMoneda() != null) {

            predicate = cb.equal(adquisicion.get("adqMoneda").get("monPk"), filtro.getMoneda().getMonPk());

            predicates.add(predicate);
        }

        if (filtro.getIdAdquisicion() != null && filtro.getIdAdquisicion() > 0) {

            predicate = cb.equal(adquisicion.get("adqIdAdquisicion"), filtro.getIdAdquisicion());

            predicates.add(predicate);
        }

        predicates.add(cb.equal(root.get("activo"), true));

        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        cq.groupBy(root.get("proyPk"),
                root.get("proyNombre"),
                root.get("proyEstFk").get("estPk"),
                root.get("proyEstFk").get("estNombre"),
                programa.get("progPk"),
                programa.get("progNombre"),
                root.get("proyUsrGerenteFk").get("usuId"),
                root.get("proyUsrGerenteFk").get("usuPrimerNombre"),
                root.get("proyUsrGerenteFk").get("usuPrimerApellido"),
                root.get("proyUsrAdjuntoFk").get("usuId"),
                root.get("proyUsrAdjuntoFk").get("usuPrimerNombre"),
                root.get("proyUsrAdjuntoFk").get("usuPrimerApellido"),
                root.get("proyUsrPmofedFk").get("usuId"),
                root.get("proyUsrPmofedFk").get("usuPrimerNombre"),
                root.get("proyUsrPmofedFk").get("usuPrimerApellido"),
                area.get("areaPk"),
                presupuesto.get("prePk"),
                area.get("areaNombre"));

        cq.having(cb.gt(cb.count(adquisicion), 0));

        cq.orderBy(cb.desc(root.get("proyPk")));

        return em.createQuery(cq).getResultList();
    }

    public Estados obtenerEstadoPorIdPresupuesto(Integer idPresupuesto) {

        return em.createNamedQuery("Proyectos.findEstadoByIdPresupuesto", Estados.class)
                .setParameter("idPresupuesto", idPresupuesto)
                .getSingleResult();
    }

    public Estados obtenerEstadoPorIdCronograma(Integer idCronograma) {

        return em.createNamedQuery("Proyectos.findEstadoByIdCronograma", Estados.class)
                .setParameter("idCronograma", idCronograma)
                .getSingleResult();
    }

    public Proyectos obtenerPorIdCronograma(Integer idCronograma) {

        return em.createNamedQuery("Proyectos.findByIdCronograma", Proyectos.class)
                .setParameter("idCronograma", idCronograma)
                .getSingleResult();
    }

    public Integer obtenerIdPorIdCronograma(Integer idCronograma) {

        return em.createNamedQuery("Proyectos.findIdByIdCronograma", Integer.class)
                .setParameter("idCronograma", idCronograma)
                .getSingleResult();
    }

    public void modificarFechaActualizacionProyecto(Integer idProyecto, Date fechaModificacion) {

        em.createNamedQuery("Proyectos.updateFechaActualizacionById")
                .setParameter("idProyecto", idProyecto)
                .setParameter("fechaModificacion", fechaModificacion)
                .executeUpdate();
    }

    public void updateUsuariosEnProyecto(String proyectsPk, Integer gerentePk, Integer adjuntoPk, Integer pmofPk, Integer sponsorPk) {

        String setS = " set ";

        boolean hasUser = false;

        if (gerentePk != null && gerentePk >  0) {

            setS = setS + " proy_usr_gerente_fk=" + gerentePk;
            hasUser = true;

        }

        if (adjuntoPk != null && adjuntoPk >  0) {

            if (hasUser) {
                setS = setS + " ,proy_usr_adjunto_fk=" + adjuntoPk;
            } else {
                setS = setS + " proy_usr_adjunto_fk=" + adjuntoPk;
            }

            hasUser = true;

        }

        if (pmofPk != null && pmofPk >  0 ) {

            if (hasUser) {
                setS = setS + " ,proy_usr_pmofed_fk=" + pmofPk;
            } else {
                setS = setS + " proy_usr_pmofed_fk=" + pmofPk;
            }

            hasUser = true;

        }

        if (sponsorPk != null && sponsorPk >  0) {

            if (hasUser) {
                setS = setS + " ,proy_usr_sponsor_fk=" + sponsorPk;
            } else {
                setS = setS + " proy_usr_sponsor_fk=" + sponsorPk;
            }

            hasUser = true;

        }

        if (hasUser) {

            String queryStr = "UPDATE Proyectos" + setS + " WHERE proyPk in (" + proyectsPk + ")";

            Query query = getEm().createQuery(queryStr);
            query.executeUpdate();
        }

    }

}
