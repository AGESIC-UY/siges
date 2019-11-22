package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.PagoValidacion;
import com.sofis.data.daos.PagosDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Pagos;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "PagosBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class PagosBean {

    private static final Logger logger = Logger.getLogger(Pagos.class.getName());
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    @Inject
    private DatosUsuario du;
    @Inject
    private ConsultaHistorico<Pagos> ch;
    @EJB
    private ProyectosBean proyectosBean;
    @EJB
    private NotificacionEnvioBean notificacionEnvioBean;
    @Inject
    private ConfiguracionBean configuracionBean;

    //private String usuario;
    //private String origen;
    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    /**
     * Obtener la lista de los pagos deacuerdo a la ficha aportada.
     *
     * @param fichaFk
     * @param tipoFicha
     * @return Lista de Pagos por Ficha
     * @throws GeneralException
     */
    public List<Pagos> obtenerPagosPorFichaId(Integer fichaFk, Integer tipoFicha) throws GeneralException {
        PagosDAO pagosDAO = new PagosDAO(em);
        return pagosDAO.obtenerPagosPorFichaId(fichaFk, tipoFicha);
    }

    private Pagos guardar(Pagos pago, Integer proyPk, Integer orgPk) {
        
        Boolean exigeProveedorEnPago = false;
        Configuracion cnfExigeProveedorEnPago = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PROVEEDOR_ES_EXIGIDO_EN_PAGO, orgPk);
        if (cnfExigeProveedorEnPago != null && cnfExigeProveedorEnPago.getCnfValor().equalsIgnoreCase("true")) {
            exigeProveedorEnPago = true;
        }     
        
        //validación de cliente en pago
        Boolean exigeClienteEnPago = false;
        Configuracion cnfExigeClienteEnPago = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.CLIENTE_ES_EXIGIDO_EN_PAGO, orgPk);
        if (cnfExigeClienteEnPago != null && cnfExigeClienteEnPago.getCnfValor().equalsIgnoreCase("true")) {
            exigeClienteEnPago = true;
        }
        
        PagoValidacion.validar(pago, exigeProveedorEnPago, exigeClienteEnPago);
        
        
        PagosDAO pagoDao = new PagosDAO(em);

        try {
            pago = pagoDao.update(pago, du.getCodigoUsuario(), du.getOrigen());
            proyectosBean.guardarIndicadores(proyPk, orgPk);

        } catch (TechnicalException te) {
            logger.log(Level.SEVERE, te.getMessage(), te);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_PAGO_GUARDAR);
            throw be;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_PAGO_GUARDAR);
            throw be;
        }

        return pago;
    }

    private void eliminarPago(Integer pagPk) throws GeneralException {
        PagosDAO pagoDao = new PagosDAO(em);
        try {
            pagoDao.deletePagos(pagPk);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_PAGO_ELIMINAR);
            if (ex.getCause() instanceof javax.persistence.PersistenceException
                    && ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                be.addError(MensajesNegocio.ERROR_PAGO_CONST_VIOLATION);
            }
            throw be;
        }
    }

    /**
     * Eliminar el pago y actualizar los indicadores.
     *
     * @param pagPk
     * @throws GeneralException
     */
    public void eliminarPagoActInd(Integer pagPk, Integer proyPk, Integer orgPk) throws GeneralException {
        eliminarPago(pagPk);
        proyectosBean.guardarIndicadores(proyPk, orgPk);
    }

    public Pagos guardarPago(Pagos pago, Integer proyPk, Integer progPk, Integer orgPk) {
        if (pago != null) {
            if (null != pago.getPagPk()) {
                Pagos pagoOriginal = obtenerPagosPorId(pago.getPagPk());
                if (pagoOriginal != null
                        && ((!pagoOriginal.isPagConfirmado() && pago.isPagConfirmado())
                        || (null != pagoOriginal.getPagFechaReal() && null != pago.getPagFechaReal()
                        && !DatesUtils.fechasIguales(pagoOriginal.getPagFechaReal(), pago.getPagFechaReal())))) {
                    notificacionEnvioBean.superarFechaPagoVencida(proyPk);
                }
            }
            pago = guardar(pago, proyPk, orgPk);
            pago.getPagAdqFk().getAdqPreFk().getProyecto().setProyFechaAct(new Date());

            if (pago.getPagFechaReal() != null) {
                notificacionEnvioBean.fechaProyectadaFinAnio(pago, proyPk, orgPk);
            }
        }

        return pago;
    }

    public Pagos obtenerPagosPorId(Integer pagPk) {
        if (pagPk != null) {
            PagosDAO pagoDao = new PagosDAO(em);
            Pagos pago = null;
            try {
                pago = pagoDao.findById(Pagos.class, pagPk);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_PAGO_OBTENER);
                throw be;
            }
            return pago;
        }
        return null;
    }

    public List<Pagos> obtenerPagosPorEntPk(Integer entPk) {
        if (entPk != null) {
            PagosDAO dao = new PagosDAO(em);
            try {
                return dao.findByOneProperty(Pagos.class, "entregables.entPk", entPk);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_PAGO_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public Set<Pagos> copiarProyPagos(Set<Pagos> pagosSet, Adquisicion nvaAdq, Set<Entregables> entSet, int desfasajeDias) {
        if (pagosSet != null && nvaAdq != null) {
            Set<Pagos> result = new HashSet<>();

            Map<String, Entregables> entMap = new HashMap<>();
            if (CollectionsUtils.isNotEmpty(entSet)) {
                Iterator<Entregables> itEntregables = entSet.iterator();
                while (itEntregables.hasNext()) {
                    Entregables ent = itEntregables.next();
                    entMap.put(ent.getEntNombre(), ent);
                }
            }

            Iterator<Pagos> iterator = pagosSet.iterator();
            while (iterator.hasNext()) {
                Pagos pago = iterator.next();

                Pagos nvoPago = new Pagos();
                nvoPago.setEntregables(entMap.get(pago.getEntregables().getEntNombre()));
                nvoPago.setPagAdqFk(nvaAdq);
                nvoPago.setPagConfirmar(Boolean.FALSE);
                nvoPago.setPagImportePlanificado(pago.getPagImportePlanificado());
//                nvoPago.setPagImporteReal(pago.getPagImporteReal());
                nvoPago.setPagObservacion(pago.getPagObservacion());
                nvoPago.setPagTxtReferencia(pago.getPagTxtReferencia());

                nvoPago.setPagGasto(pago.getPagGasto());
                nvoPago.setPagInversion(pago.getPagInversion());
                nvoPago.setPagContrOrganizacionFk(pago.getPagContrOrganizacionFk());
                nvoPago.setPagContrPorcentaje(pago.getPagContrPorcentaje());

                Date datePlanificada;
                if (pago.getPagFechaPlanificada() != null) {
                    Date date = DatesUtils.incrementarDias(pago.getPagFechaPlanificada(), desfasajeDias);
                    datePlanificada = date;
                } else {
                    datePlanificada = new Date();
                }
                nvoPago.setPagFechaPlanificada(datePlanificada);

//                Date dateReal;
//                if (pago.getPagFechaReal() != null) {
//                    Date date = DatesUtils.incrementarDias(pago.getPagFechaReal(), desfasajeDias);
//                    dateReal = date;
//                } else {
//                    dateReal = new Date();
//                }
//                nvoPago.setPagFechaReal(dateReal);
                result.add(nvoPago);
            }
            return result;
        }
        return null;
    }

    public List<Pagos> obtenerPagosDiasVenc(Integer proyPk, int diasVencido) {
        PagosDAO dao = new PagosDAO(em);
        return dao.obtenerPagosDiasVenc(proyPk, diasVencido);
    }
    
    public Double obtenerImportePlanificado(Integer pagPk) {
        PagosDAO dao = new PagosDAO(em);
        return dao.obtenerImportePlanificado(pagPk);
    }
}
