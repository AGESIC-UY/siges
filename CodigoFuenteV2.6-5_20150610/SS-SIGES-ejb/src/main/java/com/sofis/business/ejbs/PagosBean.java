package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.PagoValidacion;
import com.sofis.data.daos.PagosDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Adquisicion;
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

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    @Inject
    private DatosUsuario du;
    @Inject
    private ConsultaHistorico<Pagos> ch;
    @Inject
    private ProgramasBean programasBean;
    @EJB
    private NotificacionEnvioBean notificacionEnvioBean;

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

    private Pagos guardar(Pagos pago, Integer progPk, Integer orgPk) {
        PagoValidacion.validar(pago);
        PagosDAO pagoDao = new PagosDAO(em);

        try {
            pago = pagoDao.update(pago, du.getCodigoUsuario(), du.getOrigen());

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

        if (progPk != null) {
            programasBean.guardarIndicadoresSimple(progPk, orgPk);
        }

        return pago;
    }

    public void eliminarPago(Integer pagPk) throws GeneralException {
        PagosDAO pagoDao = new PagosDAO(em);

        try {
            pagoDao.deletePagos(pagPk);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_PAGO_ELIMINAR);
            throw be;
        }
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
            pago = guardar(pago, progPk, orgPk);

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
                nvoPago.setPagFechaReal(null);
                nvoPago.setPagImporteReal(null);
                nvoPago.setPagObservacion(pago.getPagObservacion());
                nvoPago.setPagTxtReferencia(pago.getPagTxtReferencia());

                if (pago.getPagFechaPlanificada() != null) {
                    Date date = DatesUtils.incrementarDias(pago.getPagFechaPlanificada(), desfasajeDias);
                    nvoPago.setPagFechaPlanificada(date);
                }

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
}