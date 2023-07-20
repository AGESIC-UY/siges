package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Pagos;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PagoValidacion {

    private static final Logger logger = Logger.getLogger(PagoValidacion.class.getName());

    public static boolean validar(Pagos pago, boolean exigeProveedorEnPago, boolean exigeClienteEnPago, String procedimientoFiltrados) throws BusinessException {
        BusinessException be = new BusinessException();

        if (pago == null) {
            be.addError(MensajesNegocio.ERROR_PAGO_NULL);
        } else {
            if (pago.getEntregables() == null) {
                be.addError(MensajesNegocio.ERROR_PAGO_ENTREGABLE);
            }

            if (pago.getPagFechaPlanificada() == null) {
                be.addError(MensajesNegocio.ERROR_PAGO_FECHA_PLANIFICADA);
            }

            if (pago.getPagImportePlanificado() == null) {
                be.addError(MensajesNegocio.ERROR_PAGO_IMPORTE_PLANIFICADO);
            }

            if (pago.isPagConfirmado()
                    && (pago.getPagFechaReal() == null || DatesUtils.esMayor(pago.getPagFechaReal(), new Date()))) {
                be.addError(MensajesNegocio.ERROR_PAGO_CONFIRMAR_REAL);
            }

            if (pago.getPagTxtReferencia() != null
                    && pago.getPagTxtReferencia().length() > 50) {
                be.addError(MensajesNegocio.ERROR_PAGO_REFERENCIA_LARGO);
            }

            if (exigeProveedorEnPago && Boolean.TRUE.equals(pago.getPagConfirmar())
                    && pago.getPagProveedorFk() == null && pago.getPagImporteReal() > 0) {

                be.addError(MensajesNegocio.ERROR_PAGO_SIN_PROVEEDOR);
            }

            if (exigeClienteEnPago && Boolean.TRUE.equals(pago.getPagConfirmar())
                    && pago.getPagContrOrganizacionFk() == null && pago.getPagImporteReal() > 0) {

                be.addError(MensajesNegocio.ERROR_PAGO_APROB_SIN_CLIENTE);
            }

            if (!procedimientoFiltrados.isEmpty()) {
                String[] arrOfStr = procedimientoFiltrados.split(";");
                for (String procedimientoAFiltrar : arrOfStr) {
                    System.out.println("filtramos "+ procedimientoAFiltrar);
                   // System.out.println("procediento "+ pago.getPagAdqFk().getAdqProcedimientoCompra().getProcCompNombre());
                    if (pago.getPagAdqFk() != null
                            && pago.getPagAdqFk().getAdqProcedimientoCompra() != null
                            && pago.getPagAdqFk().getAdqProcedimientoCompra().getProcCompNombre() != null
                            && pago.getPagAdqFk().getAdqProcedimientoCompra().getProcCompNombre().contains(procedimientoAFiltrar)) {
                        be.addError(MensajesNegocio.ERROR_PAGO_CAMBIAR_PROCEDIMIENTO_COMPRA);
                    }

                }

            }

        }

        if (be.getErrores().size() > 0) {
            logger.logp(Level.WARNING, PagoValidacion.class.getName(), "validar", be.getErrores().toString(), be);
            throw be;
        }

        return true;
    }
}
