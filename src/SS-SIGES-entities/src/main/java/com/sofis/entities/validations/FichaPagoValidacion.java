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

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(Pagos pago) throws BusinessException {
        logger.finest("Validar Ficha Pago.");
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
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, be.getErrores().toString(), be);
            throw be;
        }

        return true;
    }
}
