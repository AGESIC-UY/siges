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
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless(name = "PagosBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class PagosBean {

    private static final Logger LOGGER = Logger.getLogger(Pagos.class.getName());

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    @Inject
    private DatosUsuario datosUsuario;

    @EJB
    private ProyectosBean proyectosBean;

    @EJB
    private NotificacionEnvioBean notificacionEnvioBean;

    @Inject
    private ConfiguracionBean configuracionBean;

    @Inject
    private SsUsuarioBean usuarioBean;

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

        Configuracion cnfPagoFiltroProcedimientoCompra = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PAGO_FILTRO_PROCEDIMIENTO_COMPRA, orgPk);

        String procedimientoFiltrados = cnfPagoFiltroProcedimientoCompra != null ? cnfPagoFiltroProcedimientoCompra.getCnfValor() : "";

        PagoValidacion.validar(pago, exigeProveedorEnPago, exigeClienteEnPago,procedimientoFiltrados);

        PagosDAO pagoDao = new PagosDAO(em);

        try {
            pago = pagoDao.update(pago, datosUsuario.getCodigoUsuario(), datosUsuario.getOrigen());

            proyectosBean.actualizarFechaUltimaModificacion(proyPk);

            proyectosBean.guardarIndicadores(proyPk, orgPk);

        } catch (TechnicalException te) {
            LOGGER.log(Level.SEVERE, te.getMessage(), te);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_PAGO_GUARDAR);
            throw be;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_PAGO_GUARDAR);
            throw be;
        }

        return pago;
    }

    private void eliminarPago(Integer pagPk) throws GeneralException {

        PagosDAO pagoDao = new PagosDAO(em);

        try {
            Integer idProyecto = pagoDao.obtenerIdProyectoPorIdPago(pagPk);

            System.out.println("pagoDao.obtenerIdProyectoPorIdPago " + idProyecto);
            pagoDao.deletePagos(pagPk);

            proyectosBean.actualizarFechaUltimaModificacion(idProyecto);

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);

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

        if (pago == null) {
            return null;
        }

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

        if (pago.getPagFechaReal() != null) {
            notificacionEnvioBean.fechaProyectadaFinAnio(pago, proyPk, orgPk);
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
                LOGGER.log(Level.SEVERE, null, ex);
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
                LOGGER.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_PAGO_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public Set<Pagos> copiarProyPagos(Set<Pagos> pagosSet, Adquisicion nvaAdq, Map<Integer, Entregables> entMap, int desfasajeDias) {

        if (pagosSet != null && nvaAdq != null) {
            Set<Pagos> result = new HashSet<>();

            Iterator<Pagos> iterator = pagosSet.iterator();
            while (iterator.hasNext()) {
                Pagos pago = iterator.next();

                Pagos nvoPago = new Pagos();
                nvoPago.setPagAdqFk(nvaAdq);
                nvoPago.setEntregables(entMap.get(pago.getEntregables().getEntPk()));
                nvoPago.setPagObservacion(pago.getPagObservacion());

                nvoPago.setPagFechaPlanificada(DatesUtils.incrementarDias(pago.getPagFechaPlanificada(), desfasajeDias));
                nvoPago.setPagImportePlanificado(pago.getPagImportePlanificado());

                // fecha y monto real no se copian
                nvoPago.setPagConfirmar(Boolean.FALSE);

                nvoPago.setPagTxtReferencia(pago.getPagTxtReferencia());
                nvoPago.setPagGasto(pago.getPagGasto());
                nvoPago.setPagInversion(pago.getPagInversion());
                nvoPago.setPagContrOrganizacionFk(pago.getPagContrOrganizacionFk());
                nvoPago.setPagContrPorcentaje(pago.getPagContrPorcentaje());
                nvoPago.setPagProveedorFk(pago.getPagProveedorFk());

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

    public boolean confirmarPago(Integer idPago) {

        Pagos pago = obtenerPagosPorId(idPago);

        if (pago == null) {

            throw new BusinessException(MensajesNegocio.ERROR_PAGO_OBTENER);
        }

        SsUsuario usuario = usuarioBean.obtenerSsUsuarioPorMail(datosUsuario.getCodigoUsuario());

        if (usuario == null) {

            throw new BusinessException(MensajesNegocio.ERROR_USUARIO_OBTENER);
        }

        Proyectos proyecto = pago.getPagAdqFk().getAdqPreFk().getProyecto();

        if (pago.isPagConfirmado()) {

            if (SsUsuariosUtils.isUsuarioPMO(proyecto, usuario, proyecto.getProyOrgFk().getOrgPk())) {
                pago.setPagConfirmar(Boolean.FALSE);
            } else {
                throw new BusinessException(MensajesNegocio.ERROR_PAGO_REVERTIR_NO_PMO);
            }
        } else {
            pago.setPagConfirmar(Boolean.TRUE);
        }

        guardarPago(pago, proyecto.getProyPk(), null, proyecto.getProyOrgFk().getOrgPk());

        return pago.getPagConfirmar();
    }

}
