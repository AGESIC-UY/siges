package com.sofis.entities.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Pagos;
import com.sofis.exceptions.BusinessException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class FichaPagoValidacion {

    private static final Logger logger = Logger.getLogger(FichaPagoValidacion.class.getName());

    public static boolean validar(Pagos pago, boolean ejecucion, boolean exigeProveedorEnPago, boolean exigeClienteEnPago) throws BusinessException {
        BusinessException be = new BusinessException();

        if (pago == null) {
            be.addError(MensajesNegocio.ERROR_PAGO_NULL);
        } else {
            if (pago.getEntregables() == null) {
                be.addError(MensajesNegocio.ERROR_PAGO_ENTREGABLE);
            }
            if (pago.getPagFechaPlanificada() == null && !ejecucion) {
                be.addError(MensajesNegocio.ERROR_PAGO_FECHA_PLANIFICADA);
            }
            if (pago.getPagImportePlanificado() == null && !ejecucion) {
                be.addError(MensajesNegocio.ERROR_PAGO_IMPORTE_PLANIFICADO);
            }
            if (pago.getPagFechaReal() == null && ejecucion) {
                be.addError(MensajesNegocio.ERROR_PAGO_FECHA_REAL);
            }
            if (pago.getPagImporteReal() == null && ejecucion) {
                be.addError(MensajesNegocio.ERROR_PAGO_IMPORTE_REAL);
            }
            if (exigeProveedorEnPago && Boolean.TRUE.equals(pago.getPagConfirmar()) && pago.getPagProveedorFk() == null) {
                be.addError(MensajesNegocio.ERROR_PAGO_SIN_PROVEEDOR);
            }
            if (exigeClienteEnPago && Boolean.TRUE.equals(pago.getPagConfirmar()) && pago.getPagContrOrganizacionFk() == null) {
                be.addError(MensajesNegocio.ERROR_PAGO_SIN_CLIENTE);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, be.getErrores().toString(), be);
            throw be;
        }
        
        return true;
    }
}
