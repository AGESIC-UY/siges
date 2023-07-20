package com.sofis.business.ejbs;

import com.sofis.business.validations.AdquisicionValidacion;
import com.sofis.data.daos.AdquisicionDAO;
import com.sofis.data.daos.ProyectosDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Devengado;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.AdqPagosTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.ejb3.annotation.TransactionTimeout;

@Named
@Stateless(name = "AdquisicionBean")
@LocalBean
public class AdquisicionBean {

    private static final Logger LOGGER = Logger.getLogger(AdquisicionBean.class.getName());

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    @Inject
    private DatosUsuario du;

    @EJB
    private PresupuestoBean presupuestoBean;

    @EJB
    private PagosBean pagosBean;

    @EJB
    private DevengadoBean devengadoBean;

    @EJB
    private ProyectosBean proyectosBean;
    
     private static final long TRANSACTION_TIMEOUT_MINUTES = 120;

    /**
     * Compara en primer lugar por fecha de planificacion y luego por fecha real
     */
    private static final Comparator<Pagos> COMPARADOR_PLANIFICACION = new Comparator<Pagos>() {
        @Override
        public int compare(Pagos o1, Pagos o2) {

            int comparacionPlanificada = o1.getPagFechaPlanificada().compareTo(o2.getPagFechaPlanificada());

            if (comparacionPlanificada != 0) {

                return comparacionPlanificada;
            }

            int result;

            if (o1.getPagFechaReal() != null) {
                result = (o2.getPagFechaReal() != null) ? o1.getPagFechaReal().compareTo(o2.getPagFechaReal()) : -1;

            } else {
                result = (o2.getPagFechaReal() != null) ? 1 : 0;
            }

            return result;
        }
    };

    /**
     * Compara en primer lugar por fecha real y luego por fecha de planificacion
     */
    private static final Comparator<Pagos> COMPARADOR_EJECUCION = new Comparator<Pagos>() {
        @Override
        public int compare(Pagos o1, Pagos o2) {

            int result;

            if (o1.getPagFechaReal() != null) {
                result = (o2.getPagFechaReal() != null) ? o1.getPagFechaReal().compareTo(o2.getPagFechaReal()) : -1;

            } else {
                result = (o2.getPagFechaReal() != null) ? 1 : 0;
            }

            return (result != 0) ? result : o1.getPagFechaPlanificada().compareTo(o2.getPagFechaPlanificada());
        }
    };

    public Adquisicion guardar(Adquisicion adquisicion, Integer orgPk) throws GeneralException {

        AdquisicionValidacion.validar(adquisicion, orgPk);
        AdquisicionDAO adquisicionDAO = new AdquisicionDAO(em);

        try {
            adquisicion = adquisicionDAO.update(adquisicion, du.getCodigoUsuario(), du.getOrigen());

            Integer idProyecto = adquisicionDAO.obtenerIdProyectoPorIdAquisicion(adquisicion.getAdqPk());

            proyectosBean.actualizarFechaUltimaModificacion(idProyecto);

        } catch (TechnicalException te) {
            LOGGER.log(Level.SEVERE, te.getMessage(), te);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ADQISICION_GUARDAR);
            throw be;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ADQISICION_GUARDAR);
            throw be;
        }

        return adquisicion;
    }

    public Adquisicion guardarAdquisicion(Adquisicion adquisicion, Integer fichaFk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) throws GeneralException {

        if (CollectionsUtils.isNotEmpty(adquisicion.getDevengadoList())) {
            List<Devengado> devList = new ArrayList<>();

            for (Devengado dev : adquisicion.getDevengadoList()) {
                if (!(dev.getDevPlan() == null && dev.getDevReal() == null)) {
                    devList.add(dev);
                }
            }
            adquisicion.setDevengadoList(devList);
        }

        if (adquisicion.getAdqPreFk() != null) {

            if (adquisicion.getAdqPk() == null) {

                Presupuesto presupuesto = presupuestoBean.obtenerPresupuestoPorId(adquisicion.getAdqPreFk().getPrePk());
                Integer orden = 1;

                for (Adquisicion adq : presupuesto.getAdquisicionSet()) {

                    if (orden <= adq.getOrden()) {
                        orden = adq.getOrden() + 1;
                    }
                }

                adquisicion.setOrden(orden);
            }

        } else {
            Presupuesto pre = new Presupuesto();
            pre = (Presupuesto) presupuestoBean.guardarPresupuesto(pre, fichaFk, tipoFicha, usuario, orgPk, true);
            adquisicion.setAdqPreFk(pre);
            adquisicion.setOrden(1);
        }

        return guardar(adquisicion, orgPk);
    }

    public List<AdqPagosTO> obtenerAdquisicionPagosList(Integer presupuestoId) {
        AdquisicionDAO adqDao = new AdquisicionDAO(em);
        ProyectosDAO proyectoDAO = new ProyectosDAO(em);
        try {

            List<AdqPagosTO> resultado = new ArrayList<>();
            List<Adquisicion> adquisiciones = adqDao.findByOneProperty(Adquisicion.class, "adqPreFk.prePk", presupuestoId, "orden");

            Integer idEstadoProyecto = null;
            if (!adquisiciones.isEmpty()) {

                idEstadoProyecto = proyectoDAO.obtenerEstadoPorIdPresupuesto(presupuestoId).getEstPk();
            }

            for (Adquisicion a : adquisiciones) {
                AdqPagosTO adqTO = new AdqPagosTO();
                adqTO.setTipo(1);
                adqTO.setAdqPk(a.getAdqPk());
                adqTO.setMonedaSigno(a.getAdqMoneda().getMonSigno());
                adqTO.setFuenteNombre(a.getAdqFuente().getFueNombre());
                adqTO.setAdqNombre(a.getAdqNombre());
                adqTO.setOrgaNombre(a.getAdqProvOrga() != null ? a.getAdqProvOrga().getOrgaNombre() : null);
                adqTO.setImportePlan(0d);
                adqTO.setImporteReal(0d);
                adqTO.setImporteSaldo(0d);
                adqTO.setIdAdquisicion(a.getAdqIdAdquisicion());
                adqTO.setTienePagos(!a.getPagosSet().isEmpty());

                adqTO.setOrden(a.getOrden());
                adqTO.setPuedeSubir(!a.getOrden().equals(1));
                adqTO.setPuedeBajar(!a.getOrden().equals(adquisiciones.size()));

                if (a.getAdqProcedimientoCompra() != null) {
                    adqTO.setProcCompra(a.getAdqProcedimientoCompra().getProcCompNombre());
                } else {
                    adqTO.setProcCompra(null);
                }

                adqTO.setProcCompraGrp(a.getAdqIdGrpErpFk() != null ? a.getAdqIdGrpErpFk().getIdGrpErpNombre() : null);

                resultado.add(adqTO);

                //procesa los pagos y suma
                SimpleDateFormat sf = new SimpleDateFormat(ConstantesEstandares.CALENDAR_PATTERN);

                List<Pagos> listaPagos = new ArrayList<>(a.getPagosSet());

                if (idEstadoProyecto == Estados.ESTADOS.INICIO.estado_id
                        || idEstadoProyecto == Estados.ESTADOS.PLANIFICACION.estado_id) {

                    Collections.sort(listaPagos, COMPARADOR_PLANIFICACION);

                } else {
                    Collections.sort(listaPagos, COMPARADOR_EJECUCION);
                }

                for (Pagos p : listaPagos) {
                    AdqPagosTO pago = new AdqPagosTO();
                    pago.setTipo(2);
                    pago.setPagPk(p.getPagPk());
                    if (p.getEntregables() != null) {
                        String dateS = "";
                        if (p.getEntregables().getEntFin() != null) {
                            Date d = new Date();
                            d.setTime(p.getEntregables().getEntFin());
                            dateS = StringsUtils.concat(" (", sf.format(d), ")");
                        }

                        pago.setAdqNombre(StringsUtils.concat(p.getEntregables().getEntNombre(), dateS));
                        pago.setAdqPk(p.getPagAdqFk().getAdqPk());
                    }
                    pago.setImportePlan(p.getPagImportePlanificado());
                    pago.setImporteReal(p.getPagImporteReal());
                    if (p.getPagImporteReal() != null) {
                        pago.setImporteSaldo(p.getPagImportePlanificado() - p.getPagImporteReal());
                    }
                    pago.setFechaPlan(p.getPagFechaPlanificada());
                    if (pago.getImporteReal() != null) {
                        pago.setFechaReal(p.getPagFechaReal());
                    }
                    if (pago.getImporteReal() != null && pago.getImportePlan() != null) {
                        boolean tienePlan = pago.getImportePlan() != null && pago.getImportePlan() > 0;
                        boolean tieneReal = pago.getImporteReal() != null && pago.getImporteReal() > 0;
                        pago.setEjecucion(tieneReal && tienePlan ? pago.getImporteReal() * 100 / pago.getImportePlan() : 0d);
                    }
                    if (p.getPagImportePlanificado() != null) {
                        adqTO.setImportePlan(adqTO.getImportePlan() + p.getPagImportePlanificado());
                    }

                    if (p.getPagImporteReal() != null) {
                        adqTO.setImporteReal(adqTO.getImporteReal() + p.getPagImporteReal());
                    }
                    if (pago.getImporteSaldo() != null) {
                        adqTO.setImporteSaldo(adqTO.getImporteSaldo() + pago.getImporteSaldo());
                    }

                    pago.setReferencia(p.getPagTxtReferencia());
                    pago.setConfirmado(p.isPagConfirmado());
                    //pago.setPagoDoc(p.getDocumento());
                    pago.setOrgaNombre(p.getPagProveedorFk() != null ? p.getPagProveedorFk().getOrgaNombre() : null);
                    resultado.add(pago);
                }
            }

            return resultado;

        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    public Adquisicion obtenerAdquisicionPorId(Integer adqPk) {
        AdquisicionDAO adqDao = new AdquisicionDAO(em);
        Adquisicion adq = null;
        try {
            adq = adqDao.findById(Adquisicion.class, adqPk);
        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
        return adq;
    }

    public List<Adquisicion> obtenerAdquisicionPorPre(Integer prePk, Integer monPk) {
        AdquisicionDAO dao = new AdquisicionDAO(em);
        try {
            return dao.obtenerAdquisicionPorPre(prePk, monPk);
        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    public List<Adquisicion> obtenerAdquisicionPorPre(Integer prePk) {
        AdquisicionDAO dao = new AdquisicionDAO(em);
        try {
            return dao.obtenerAdquisicionPorPre(prePk);
        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    public List<Adquisicion> obtenerAdquisicionPorPreProg(Integer progPk) {
        AdquisicionDAO dao = new AdquisicionDAO(em);
        try {
            return dao.obtenerAdquisicionPorPreProg(progPk);
        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    public void eliminarAdquisicion(Integer adqPk, Integer proyPk, Integer orgPk) {
        AdquisicionDAO adqDao = new AdquisicionDAO(em);

        if (tienePagosAprobados(adqPk)) {
            LOGGER.log(Level.INFO, MensajesNegocio.ERROR_ADQISICION_ELIMINAR_PAGO_CONF);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ADQISICION_ELIMINAR_PAGO_CONF);
            throw be;
        }

        try {
            Adquisicion adquisicion = adqDao.findById(Adquisicion.class, adqPk);

            Presupuesto presupuesto = adquisicion.getAdqPreFk();

            adqDao.delete(adquisicion, du.getCodigoUsuario(), du.getOrigen());
            proyectosBean.guardarIndicadores(proyPk, orgPk);

            for (Adquisicion adq : presupuesto.getAdquisicionSet()) {
                if (adq.getOrden() > adquisicion.getOrden()) {
                    adq.setOrden(adq.getOrden() - 1);
                }
            }

            proyectosBean.actualizarFechaUltimaModificacion(proyPk);

        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ADQISICION_ELIMINAR);
            throw be;
        }
    }

    public void eliminarDevengado(Integer adqPk) {

        Adquisicion adquisicion = obtenerAdquisicionPorId(adqPk);

        if (adquisicion == null) {
            return;
        }

        adquisicion.getDevengadoList().clear();

        AdquisicionDAO adquisicionDAO = new AdquisicionDAO(em);
        Integer idProyecto = adquisicionDAO.obtenerIdProyectoPorIdAquisicion(adquisicion.getAdqPk());

        proyectosBean.actualizarFechaUltimaModificacion(idProyecto);
    }

    public Set<Adquisicion> copiarProyAdquisicion(Set<Adquisicion> adquisicionSet, Presupuesto pre, Map<Integer, Entregables> entMap, int desfasajeDias, Boolean incluirAdquisicionesId) {
        if (adquisicionSet != null && pre != null) {
            Set<Adquisicion> result = new HashSet<>();

            Iterator<Adquisicion> iterator = adquisicionSet.iterator();
            while (iterator.hasNext()) {
                Adquisicion adq = iterator.next();

                Adquisicion nvaAdq = new Adquisicion();
                nvaAdq.setAdqPreFk(pre);
                nvaAdq.setAdqNombre(adq.getAdqNombre());
                nvaAdq.setAdqProvOrga(adq.getAdqProvOrga());
                nvaAdq.setAdqFuente(adq.getAdqFuente());
                nvaAdq.setAdqComponenteProducto(adq.getAdqComponenteProducto());
                nvaAdq.setAdqMoneda(adq.getAdqMoneda());

                nvaAdq.setAdqIdGrpErpFk(null);
                nvaAdq.setAdqProcedimientoCompra(adq.getAdqProcedimientoCompra());
                nvaAdq.setAdqCompartida(adq.getAdqCompartida());
                nvaAdq.setSsUsuarioCompartida(adq.getSsUsuarioCompartida());

                nvaAdq.setAdqTipoRegistro(adq.getAdqTipoRegistro());
                nvaAdq.setAdqArrastre(adq.getAdqArrastre());

                if (adq.getAdqFechaEstimadaInicioCompra() != null) {
                    nvaAdq.setAdqFechaEstimadaInicioCompra(DatesUtils.incrementarDias(adq.getAdqFechaEstimadaInicioCompra(), desfasajeDias));
                }
                if (adq.getAdqFechaEsperadaInicioEjecucion() != null) {
                    nvaAdq.setAdqFechaEsperadaInicioEjecucion(DatesUtils.incrementarDias(adq.getAdqFechaEsperadaInicioEjecucion(), desfasajeDias));
                }

                nvaAdq.setAdqTipoAdquisicion(adq.getAdqTipoAdquisicion());

                if (incluirAdquisicionesId) {
                    nvaAdq.setAdqIdAdquisicion(adq.getAdqIdAdquisicion() != null ? adq.getAdqIdAdquisicion() : 0);

                } else {
                    nvaAdq.setAdqIdAdquisicion(0);

                }

                nvaAdq.setAdqCentroCosto(adq.getAdqCentroCosto());
                nvaAdq.setAdqCausalCompra(adq.getAdqCausalCompra());

                nvaAdq.setOrden(adq.getOrden());

                nvaAdq.setPagosSet(pagosBean.copiarProyPagos(adq.getPagosSet(), nvaAdq, entMap, desfasajeDias));
                nvaAdq.setDevengadoList(devengadoBean.copiarProyDevengado(adq.getDevengadoList(), nvaAdq, desfasajeDias));

                result.add(nvaAdq);
            }

            return result;
        }

        return null;
    }

    public List<Adquisicion> obtenerAdquisicionPorProy(Integer proyPk) {
        if (proyPk != null) {
            AdquisicionDAO dao = new AdquisicionDAO(em);
            return dao.obtenerAdquisicionPorProy(proyPk);
        }
        return null;
    }

    /**
     * Retorna las adquisiciones que tienen devengados.
     *
     * @param proyPk
     * @return
     */
    public List<Adquisicion> obtenerAdqDevPorProy(Integer proyPk) {
        if (proyPk != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            MatchCriteriaTO criterioProy = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "adqPreFk.proyecto.proyPk", proyPk);
            criterios.add(criterioProy);

            MatchCriteriaTO criterioDev = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.NOT_EMPTY, "devengadoList", 1);
            criterios.add(criterioDev);

            CriteriaTO condicion = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));

            String[] orderBy = {};
            boolean[] asc = {};

            AdquisicionDAO dao = new AdquisicionDAO(em);

            try {
                return dao.findEntityByCriteria(Adquisicion.class, condicion, orderBy, asc, null, null);
            } catch (DAOGeneralException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_ADQISICION_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public boolean tienePagosAprobados(Integer adqPk) {
        if (adqPk != null) {
            MatchCriteriaTO criterioAdq = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "adqPk", adqPk);

            MatchCriteriaTO criterioPagAprob = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "pagosSet.pagConfirmar", Boolean.TRUE);

            CriteriaTO criteria = CriteriaTOUtils.createANDTO(criterioAdq, criterioPagAprob);

            AdquisicionDAO dao = new AdquisicionDAO(em);

            String[] orderBy = {};
            boolean[] asc = {};
            try {
                List<Adquisicion> listAdq = dao.findEntityByCriteria(Adquisicion.class, criteria, orderBy, asc, null, null);
                if (listAdq != null && !listAdq.isEmpty()) {
                    return true;
                }
            } catch (DAOGeneralException ex) {
                Logger.getLogger(AdquisicionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public Double costoEjecutado(Integer adqPk) {
        AdquisicionDAO dao = new AdquisicionDAO(em);
        return dao.obtenerACPorMoneda(adqPk);
    }

    public Double costoTotal(Integer adqPk) {
        AdquisicionDAO dao = new AdquisicionDAO(em);
        return dao.costoTotal(adqPk);
    }

    public Pagos obtenerUltimoPago(Integer adqPk) {
        AdquisicionDAO adqDAO = new AdquisicionDAO(em);
        return adqDAO.obtenerUltimoPago(adqPk);
    }

    public Date obtenerFechaPrimerPago(Integer adqPk) {

        AdquisicionDAO adquisicionDAO = new AdquisicionDAO(em);

        Adquisicion adquisicion;
        try {
            adquisicion = adquisicionDAO.findById(Adquisicion.class, adqPk);

        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, "Error al obtener adquisicion", ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ADQISICION_OBTENER);
            throw be;
        }

        return obtenerFechaPrimerPago(adquisicion);
    }

    public Adquisicion copiarAdquisicion(Adquisicion adquisicionModificada, Date fechaPrimerPago, Double porcentajeImporte, Integer orgPk, Boolean copiarReferenciaEnPagos) {

        if (fechaPrimerPago == null) {

            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ADQUISICION_DUPLICAR_FECHA_PRIMER_PAGO_INVALIDA);

            throw be;
        }

        if (porcentajeImporte == null || porcentajeImporte <= 0) {

            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ADQUISICION_DUPLICAR_PORCENTAJE_IMPORTE_INVALIDO);

            throw be;
        }

        AdquisicionDAO adquisicionDAO = new AdquisicionDAO(em);

        try {
            Adquisicion adquisicionOriginal = adquisicionDAO.findById(Adquisicion.class, adquisicionModificada.getAdqPk());

            Adquisicion adquisicion = clonarAdquisicion(adquisicionOriginal);

            adquisicion.setAdqNombre(adquisicionModificada.getAdqNombre());
            adquisicion.setAdqProvOrga(adquisicionModificada.getAdqProvOrga());
            adquisicion.setAdqFuente(adquisicionModificada.getAdqFuente());
            adquisicion.setAdqProcedimientoCompra(adquisicionModificada.getAdqProcedimientoCompra());
            adquisicion.setAdqIdAdquisicion(adquisicionModificada.getAdqIdAdquisicion());
            adquisicion.setAdqCausalCompra(adquisicionModificada.getAdqCausalCompra());
            adquisicion.setAdqTipoRegistro(adquisicionModificada.getAdqTipoRegistro());
            adquisicion.setAdqFechaEsperadaInicioEjecucion(adquisicionModificada.getAdqFechaEsperadaInicioEjecucion());
            adquisicion.setAdqFechaEstimadaInicioCompra(adquisicionModificada.getAdqFechaEstimadaInicioCompra());

            Integer orden = 1;
            for (Adquisicion adq : adquisicionOriginal.getAdqPreFk().getAdquisicionSet()) {

                if (orden <= adq.getOrden()) {
                    orden = adq.getOrden() + 1;
                }
            }
            adquisicion.setOrden(orden);

            AdquisicionValidacion.validar(adquisicion, orgPk);

            String estadoProyecto = adquisicionOriginal.getAdqPreFk().getProyecto().getProyEstFk().getEstCodigo();

            if (!estadoProyecto.equals(Estados.ESTADOS.PLANIFICACION.name())
                    && !estadoProyecto.equals(Estados.ESTADOS.EJECUCION.name()) && !estadoProyecto.equals(Estados.ESTADOS.INICIO.name())) {

                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_ADQUISICION_DUPLICAR_ESTADO_PROYECTO_INVALIDO);

                throw be;
            }

            Set<Pagos> pagos = copiarPagos(adquisicionOriginal, fechaPrimerPago, adquisicion, estadoProyecto, porcentajeImporte, copiarReferenciaEnPagos);

            adquisicion.setAdqPk(0);
            adquisicion.setPagosSet(pagos);

            adquisicion = adquisicionDAO.update(adquisicion, du.getCodigoUsuario(), du.getOrigen());

            return adquisicion;

        } catch (DAOGeneralException te) {
            LOGGER.log(Level.SEVERE, te.getMessage(), te);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ADQISICION_GUARDAR);
            throw be;
        }
    }

    private Set<Pagos> copiarPagos(Adquisicion adquisicionOriginal, Date fechaPrimerPago, Adquisicion adquisicion, String estadoProyecto,
            Double porcentajeImporte, Boolean copiarReferenciaEnPagos) {

        Set<Pagos> pagos = new HashSet<>();

        if (adquisicionOriginal.getPagosSet().isEmpty()) {
            return pagos;
        }

        Date fechaPrimerPagoOriginal = obtenerFechaPrimerPago(adquisicionOriginal);
        int diferenciaDias = diferenciaDias(fechaPrimerPago, fechaPrimerPagoOriginal);

        for (Pagos pagoOriginal : adquisicionOriginal.getPagosSet()) {

            Pagos pago = duplicarPago(pagoOriginal, adquisicion, estadoProyecto,
                    diferenciaDias, porcentajeImporte, copiarReferenciaEnPagos);

            pagos.add(pago);
        }

        return pagos;
    }

    private Pagos duplicarPago(Pagos pagoOriginal, Adquisicion adquisicion,
            String estadoProyecto, int diferenciaDias, Double porcentajeImporte, Boolean copiarReferenciaEnPagos) {

        Pagos pago = pagoOriginal.clone();
        // Se deja en blanco el campo referencia y descripción por un requerimiento de copiar adquisición
        pago.setPagObservacion("");
        if (copiarReferenciaEnPagos) {
            pago.setPagTxtReferencia(pagoOriginal.getPagTxtReferencia());
        } else {
            pago.setPagTxtReferencia("");
        }

        pago.setPagAdqFk(adquisicion);
        pago.setPagConfirmar(false);
        pago.setPagProveedorFk(adquisicion.getAdqProvOrga());

        if (estadoProyecto.equals(Estados.ESTADOS.PLANIFICACION.name()) || estadoProyecto.equals(Estados.ESTADOS.INICIO.name())) {

            Date fechaPlanificacion = sumarDias(pago.getPagFechaPlanificada(), diferenciaDias);
            pago.setPagFechaPlanificada(fechaPlanificacion);

            Double importePlanificado = calcularPorcentajeImporte(pago.getPagImportePlanificado(), porcentajeImporte);
            pago.setPagImportePlanificado(importePlanificado);

            pago.setPagFechaReal(null);
            pago.setPagImporteReal(null);

        } else {

            pago.setPagFechaPlanificada(new Date());
            pago.setPagImportePlanificado(0D);

            Date fechaReal = sumarDias(pago.getPagFechaReal(), diferenciaDias);
            pago.setPagFechaReal(fechaReal);

            Double importeReal = calcularPorcentajeImporte(pago.getPagImporteReal(), porcentajeImporte);
            pago.setPagImporteReal(importeReal);
        }

        return pago;
    }

    private Date obtenerFechaPrimerPago(Adquisicion adquisicion) {

        Set<Pagos> pagos = adquisicion.getPagosSet();

        String estado = adquisicion.getAdqPreFk().getProyecto().getProyEstFk().getEstCodigo();

        Date fecha = null;

        if (estado.equals(Estados.ESTADOS.INICIO.name())) {
            for (Pagos pago : pagos) {

                if (fecha == null || pago.getPagFechaPlanificada().compareTo(fecha) < 0) {

                    fecha = pago.getPagFechaPlanificada();
                }
            }
        } else if (estado.equals(Estados.ESTADOS.PLANIFICACION.name())) {

            for (Pagos pago : pagos) {

                if (fecha == null || pago.getPagFechaPlanificada().compareTo(fecha) < 0) {

                    fecha = pago.getPagFechaPlanificada();
                }
            }

        } else if (estado.equals(Estados.ESTADOS.EJECUCION.name())) {

            for (Pagos pago : pagos) {

                if (fecha == null || pago.getPagFechaReal().compareTo(fecha) < 0) {

                    fecha = pago.getPagFechaReal();
                }
            }
        }

        return fecha;
    }

    public int diferenciaDias(Date d1, Date d2) {

        long diferenciaMillies = Math.abs(d2.getTime() - d1.getTime());

        return Long.valueOf(TimeUnit.DAYS.convert(diferenciaMillies, TimeUnit.MILLISECONDS))
                .intValue();
    }

    private Date sumarDias(Date pagFechaPlanificada, int diferenciaDias) {

        Calendar c = Calendar.getInstance();
        c.setTime(pagFechaPlanificada);
        c.add(Calendar.DATE, diferenciaDias);

        return c.getTime();
    }

    private Double calcularPorcentajeImporte(Double importePago, Double porcentajeImporte) {

        BigDecimal importe = BigDecimal.valueOf(importePago);
        BigDecimal porcentaje = BigDecimal.valueOf(porcentajeImporte);
        BigDecimal cien = BigDecimal.valueOf(100);

        return importe.multiply(porcentaje).divide(cien, 4, RoundingMode.HALF_UP).doubleValue();
    }

    private Adquisicion clonarAdquisicion(Adquisicion original) {

        Adquisicion adquisicion = new Adquisicion();

        adquisicion.setAdqPk(original.getAdqPk());
        adquisicion.setAdqPreFk(original.getAdqPreFk());
        adquisicion.setAdqNombre(original.getAdqNombre());
        adquisicion.setAdqProvOrga(original.getAdqProvOrga());
        adquisicion.setAdqFuente(original.getAdqFuente());
        adquisicion.setAdqProcedimientoCompra(original.getAdqProcedimientoCompra());
        adquisicion.setAdqIdAdquisicion(original.getAdqIdAdquisicion());
        adquisicion.setAdqCausalCompra(original.getAdqCausalCompra());
        adquisicion.setAdqTipoRegistro(original.getAdqTipoRegistro());

        adquisicion.setAdqMoneda(original.getAdqMoneda());
        adquisicion.setAdqIdGrpErpFk(original.getAdqIdGrpErpFk());
        adquisicion.setAdqComponenteProducto(original.getAdqComponenteProducto());
        adquisicion.setAdqCompartida(original.getAdqCompartida());
        adquisicion.setSsUsuarioCompartida(original.getSsUsuarioCompartida());
        adquisicion.setAdqArrastre(original.getAdqArrastre());
        adquisicion.setAdqFechaEstimadaInicioCompra(original.getAdqFechaEstimadaInicioCompra());
        adquisicion.setAdqFechaEsperadaInicioEjecucion(original.getAdqFechaEsperadaInicioEjecucion());
        adquisicion.setAdqTipoAdquisicion(original.getAdqTipoAdquisicion());
        adquisicion.setAdqCentroCosto(original.getAdqCentroCosto());
        adquisicion.setOrden(original.getOrden());

        return adquisicion;
    }

    public void bajarAdquisicion(Integer adqPk) {

        AdquisicionDAO adquisicionDAO = new AdquisicionDAO(em);

        try {
            Adquisicion adquisicion = adquisicionDAO.findById(Adquisicion.class, adqPk);

            if (adquisicion == null) {
                return;
            }

            Set<Adquisicion> adquisiciones = adquisicion.getAdqPreFk().getAdquisicionSet();

            if (adquisicion.getOrden().equals(adquisiciones.size())) {

                // ya es la ultima, no puede bajar
                return;
            }

            for (Adquisicion adq : adquisiciones) {

                // busco adquisicion con numero siguiente y le asigno el anterior
                if (adq.getOrden().equals(adquisicion.getOrden() + 1)) {
                    adq.setOrden(adquisicion.getOrden());

                    break;
                }
            }

            adquisicion.setOrden(adquisicion.getOrden() + 1);

        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, null, ex);

            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ADQISICION_OBTENER);
            throw be;
        }
    }

    public void subirAdquisicion(Integer adqPk) {

        AdquisicionDAO adquisicionDAO = new AdquisicionDAO(em);

        try {
            Adquisicion adquisicion = adquisicionDAO.findById(Adquisicion.class, adqPk);

            if (adquisicion == null) {
                return;
            }

            if (adquisicion.getOrden().equals(1)) {

                // ya es la primera, no puede subir
                return;
            }

            Set<Adquisicion> adquisiciones = adquisicion.getAdqPreFk().getAdquisicionSet();
            for (Adquisicion adq : adquisiciones) {

                //busco adquisicion con numero anterior y le asigno el siguiente
                if (adq.getOrden().equals(adquisicion.getOrden() - 1)) {
                    adq.setOrden(adquisicion.getOrden());

                    break;
                }
            }

            adquisicion.setOrden(adquisicion.getOrden() - 1);

        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, null, ex);

            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ADQISICION_OBTENER);
            throw be;
        }
    }

    public List<Adquisicion> obtenerProOrganisacionYIdAdquisicion(Integer orgaPk, Integer idAdquisicon) {

        AdquisicionDAO adquisicionDAO = new AdquisicionDAO(em);

        List<Adquisicion> adquisiciones = new ArrayList<>();
        try {
            adquisiciones = adquisicionDAO.obtenerAdquisicionPorIdAdquisicionAndOrganisacion(orgaPk, idAdquisicon);

        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, "Error al obtener adquisicion", ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ADQISICION_OBTENER);
            throw be;
        }

        return adquisiciones;
    }

    public void actualizarPorCargaMasiva(Integer procAdquisicionId, Integer procCompPk, Integer orgPk) {
        AdquisicionDAO adquisicionDAO = new AdquisicionDAO(em);
        adquisicionDAO.actualizarAdquisiciones(procAdquisicionId,procCompPk,orgPk);
    }

}
