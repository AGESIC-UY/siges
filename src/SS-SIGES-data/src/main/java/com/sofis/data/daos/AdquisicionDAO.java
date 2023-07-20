package com.sofis.data.daos;

import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.CausalCompra;
import com.sofis.entities.data.CentroCosto;
import com.sofis.entities.data.ComponenteProducto;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.IdentificadorGrpErp;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.data.TipoAdquisicion;
import com.sofis.entities.tipos.AdquisicionTO;
import com.sofis.entities.tipos.FiltroMisAdquisicionesTO;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AdquisicionDAO extends HibernateJpaDAOImp<Adquisicion, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(AdquisicionDAO.class.getName());

    public AdquisicionDAO(EntityManager em) {
        super(em);
    }

    public List<Adquisicion> obtenerAdquisicionPorPre(Integer prePk, Integer monPk) throws DAOGeneralException {
        String query = "SELECT a"
                + " FROM Adquisicion a"
                + " WHERE a.adqPreFk.prePk = :prePk"
                + " AND a.adqMoneda.monPk = :monPk"
                + " ORDER BY a.orden";

        Query q = super.getEm().createQuery(query);
        q.setParameter("prePk", prePk);
        q.setParameter("monPk", monPk);

        List<Adquisicion> adqList = q.getResultList();
        return adqList;
    }

    public List<Adquisicion> obtenerAdquisicionPorPre(Integer prePk) throws DAOGeneralException {
        List<Adquisicion> adquisiciones = this.findByOneProperty(Adquisicion.class, "adqPreFk.prePk", prePk, "orden");
        return adquisiciones;
    }

    public List<AdquisicionTO> obtenerAdquisicionesPorPresupuesto(Integer prePk, FiltroMisAdquisicionesTO filtro) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<AdquisicionTO> cq = cb.createQuery(AdquisicionTO.class);

        Root<Adquisicion> root = cq.from(Adquisicion.class);
        Join<Adquisicion, OrganiIntProve> proveedor = root.join("adqProvOrga", JoinType.LEFT);
        Join<Adquisicion, ProcedimientoCompra> procedimientoCompra = root.join("adqProcedimientoCompra", JoinType.LEFT);
        Join<Adquisicion, FuenteFinanciamiento> fuenteFinanciamiento = root.join("adqFuente", JoinType.LEFT);
        Join<Adquisicion, CausalCompra> causalCompra = root.join("adqCausalCompra", JoinType.LEFT);
        Join<Adquisicion, Moneda> moneda = root.join("adqMoneda", JoinType.LEFT);
        Join<Adquisicion, Pagos> pagos = root.join("pagosSet", JoinType.LEFT);

        cq.select(cb.construct(AdquisicionTO.class,
                root.get("adqPk"),
                root.get("adqNombre"),
                root.get("adqIdAdquisicion"),
                proveedor.get("orgaPk"),
                proveedor.get("orgaNombre"),
                procedimientoCompra.get("procCompPk"),
                procedimientoCompra.get("procCompNombre"),
                fuenteFinanciamiento.get("fuePk"),
                fuenteFinanciamiento.get("fueNombre"),
                causalCompra.get("cauComPk"),
                causalCompra.get("cauComNombre"),
                moneda.get("monPk"),
                moneda.get("monNombre"),
                moneda.get("monSigno"),
                cb.count(pagos)));

        Predicate predicate;

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(root.get("adqPreFk").get("prePk"), prePk));

        if (filtro.getFuenteFinanciamiento() != null) {

            predicate = cb.equal(root.get("adqFuente").get("fuePk"), filtro.getFuenteFinanciamiento().getFuePk());

            predicates.add(predicate);
        }

        if (filtro.getProcedimientoCompra() != null) {

            predicate = cb.equal(root.get("adqProcedimientoCompra").get("procCompPk"), filtro.getProcedimientoCompra().getProcCompPk());

            predicates.add(predicate);
        }

        if (filtro.getProveedor() != null) {

            predicate = cb.or(cb.equal(root.get("adqProvOrga").get("orgaPk"), filtro.getProveedor().getOrgaPk()),
                    cb.equal(pagos.get("pagProveedorFk").get("orgaPk"), filtro.getProveedor().getOrgaPk()));

            predicates.add(predicate);
        }

        if (filtro.getMoneda() != null) {

            predicate = cb.equal(root.get("adqMoneda").get("monPk"), filtro.getMoneda().getMonPk());

            predicates.add(predicate);
        }

        if (filtro.getIdAdquisicion() != null && filtro.getIdAdquisicion() > 0) {

            predicate = cb.equal(root.get("adqIdAdquisicion"), filtro.getIdAdquisicion());

            predicates.add(predicate);
        }

        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        cq.groupBy(root.get("adqPk"),
                root.get("adqNombre"),
                root.get("adqIdAdquisicion"),
                proveedor.get("orgaPk"),
                proveedor.get("orgaNombre"),
                procedimientoCompra.get("procCompPk"),
                procedimientoCompra.get("procCompNombre"),
                fuenteFinanciamiento.get("fuePk"),
                fuenteFinanciamiento.get("fueNombre"),
                causalCompra.get("cauComPk"),
                causalCompra.get("cauComNombre"),
                moneda.get("monPk"),
                moneda.get("monNombre"),
                moneda.get("monSigno"));

        cq.orderBy(cb.asc(root.get("orden")));

        return em.createQuery(cq).getResultList();
    }

    public List<Adquisicion> obtenerAdquisicionPorPreProg(Integer progPk) throws DAOGeneralException {
        String query = "SELECT b"
                + " FROM Programas p,"
                + " IN(p.proyectosSet) proy,"
                + " IN(proy.proyPreFk.adquisicionSet) b"
                + " WHERE proy.proyProgFk.progPk = :progPk"
                + " ORDER BY b.orden";

        Query q = super.getEm().createQuery(query);
        q.setParameter("progPk", progPk);

        List<Adquisicion> adqList = q.getResultList();
        return adqList;
    }

    public List<Adquisicion> obtenerAdquisicionPorProy(Integer proyPk) {
        try {
            return this.findByOneProperty(Adquisicion.class, "adqPreFk.proyecto.proyPk", proyPk, "orden");
        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(MensajesNegocio.ERROR_ADQISICION_OBTENER);
            throw te;
        }
    }

    /**
     * Retorna la suma del costo actual(real) confirmado y según la moneda.
     *
     * @param adqPk
     * @return Double
     */
    public Double obtenerACPorMoneda(Integer adqPk) {
        String query = "SELECT SUM(p.pagImporteReal)"
                + " FROM Adquisicion a, IN(a.pagosSet) p"
                + " WHERE a.adqPk = :adqPk"
                + " AND p.pagConfirmar = :pagConfirmado"
                + " AND p.pagFechaReal <= :fecha";

        Query q = super.getEm().createQuery(query);
        q.setParameter("adqPk", adqPk);
        q.setParameter("pagConfirmado", Boolean.TRUE);
        q.setParameter("fecha", new Date());

        Double reutValue = (Double) q.getSingleResult();
        if (reutValue == null) {
            return 0d;
        }
        return reutValue;
    }

    public Double costoTotal(Integer adqPk) {
        String query = "SELECT SUM(p.pagImportePlanificado)"
                + " FROM Adquisicion a, IN(a.pagosSet) p"
                + " WHERE a.adqPk = :adqPk";

        Query q = super.getEm().createQuery(query);
        q.setParameter("adqPk", adqPk);

        Double reutValue = (Double) q.getSingleResult();
        if (reutValue == null) {
            return 0d;
        }
        return reutValue;
    }

    public Pagos obtenerUltimoPago(Integer adqPk) {
        String query = "SELECT p"
                + " FROM Adquisicion a JOIN a.pagosSet p"
                + " WHERE a.adqPk = :adqPk"
                + " ORDER BY p.pagPk DESC";

        Query q = super.getEm().createQuery(query);
        q.setParameter("adqPk", adqPk);

        List<Pagos> retorno = q.getResultList();

        Pagos pagoRetorno;

        if (!retorno.isEmpty()) {
            pagoRetorno = retorno.get(0);
        } else {
            pagoRetorno = new Pagos();
        }

        return pagoRetorno;
    }

    public AdquisicionTO obtenerAdquisicionTOPorId(Integer id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<AdquisicionTO> cq = cb.createQuery(AdquisicionTO.class);

        Root<Adquisicion> root = cq.from(Adquisicion.class);
        Join<Adquisicion, OrganiIntProve> proveedor = root.join("adqProvOrga", JoinType.LEFT);
        Join<Adquisicion, ProcedimientoCompra> procedimientoCompra = root.join("adqProcedimientoCompra", JoinType.LEFT);
        Join<Adquisicion, FuenteFinanciamiento> fuenteFinanciamiento = root.join("adqFuente", JoinType.LEFT);
        Join<Adquisicion, CausalCompra> causalCompra = root.join("adqCausalCompra", JoinType.LEFT);
        Join<Adquisicion, Moneda> moneda = root.join("adqMoneda", JoinType.LEFT);
        Join<Adquisicion, IdentificadorGrpErp> identificadorGrpErp = root.join("adqIdGrpErpFk", JoinType.LEFT);
        Join<Adquisicion, ComponenteProducto> componenteProducto = root.join("adqComponenteProducto", JoinType.LEFT);
        Join<Adquisicion, SsUsuario> usuarioCompartida = root.join("ssUsuarioCompartida", JoinType.LEFT);
        Join<Adquisicion, TipoAdquisicion> tipo = root.join("adqTipoAdquisicion", JoinType.LEFT);
        Join<Adquisicion, CentroCosto> centroCosto = root.join("adqCentroCosto", JoinType.LEFT);

        cq.select(cb.construct(AdquisicionTO.class,
                root.get("adqPk"),
                root.get("adqNombre"),
                root.get("adqIdAdquisicion"),
                root.get("adqCompartida"),
                root.get("adqTipoRegistro"),
                root.get("adqArrastre"),
                root.get("adqFechaEstimadaInicioCompra"),
                root.get("adqFechaEsperadaInicioEjecucion"),
                proveedor.get("orgaPk"),
                proveedor.get("orgaNombre"),
                procedimientoCompra.get("procCompPk"),
                procedimientoCompra.get("procCompNombre"),
                fuenteFinanciamiento.get("fuePk"),
                fuenteFinanciamiento.get("fueNombre"),
                causalCompra.get("cauComPk"),
                causalCompra.get("cauComNombre"),
                moneda.get("monPk"),
                moneda.get("monNombre"),
                moneda.get("monSigno"),
                identificadorGrpErp.get("idGrpErpPk"),
                identificadorGrpErp.get("idGrpErpNombre"),
                componenteProducto.get("comPk"),
                componenteProducto.get("comNombre"),
                usuarioCompartida.get("usuId"),
                usuarioCompartida.get("usuPrimerNombre"),
                usuarioCompartida.get("usuPrimerApellido"),
                tipo.get("tipAdqPk"),
                tipo.get("tipAdqNombre"),
                centroCosto.get("cenCosPk"),
                centroCosto.get("cenCosNombre")
        ));

        cq.where(cb.equal(root.get("adqPk"), id));

        return em.createQuery(cq).getSingleResult();
    }

    public Integer obtenerIdProyectoPorIdAquisicion(Integer idAdquisicion) {

        return em.createNamedQuery("Adquisicion.findIdProyectoByIdAdquisicion", Integer.class)
                .setParameter("idAdquisicion", idAdquisicion)
                .getSingleResult();
    }

    public List<Adquisicion> obtenerAdquisicionPorIdAdquisicionAndOrganisacion(Integer orgaPk, Integer idAdquisicion) throws DAOGeneralException {
        String query = "SELECT a \n"
                + "FROM Adquisicion a \n"
                + "where a.adqPreFk.proyecto.proyOrgFk.orgPk = :orgaPk and a.adqIdAdquisicion = :idAdquisicion";

        Query q = super.getEm().createQuery(query);

        q.setParameter("orgaPk", orgaPk);
        q.setParameter("idAdquisicion", idAdquisicion);

        List<Adquisicion> adqList = q.getResultList();
        return adqList;
    }

    public void actualizarAdquisiciones(Integer procAdquisicionId, Integer procCompPk, Integer orgPk) {
        String queryStr = "UPDATE adquisicion \n"
                + "JOIN proyectos ON adq_pre_fk = proy_pre_fk \n"
                + "SET adq_procedimiento_compra_fk =  " + procCompPk + " \n"
                + "where proy_activo = 1 and proy_org_fk in (" + orgPk + ") \n"
                + "and adq_id_adquisicion = " + procAdquisicionId;
              
        System.out.println(queryStr);

        Query query = super.getEm().createNativeQuery(queryStr);
        query.executeUpdate();
    }

}
