package com.sofis.entities.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Adquisicion;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class FichaAdquisicionValidacion {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(Adquisicion adq) throws BusinessException {
        logger.finest("Validar Ficha Adquisicion.");
        BusinessException be = new BusinessException();

        if (adq == null) {
            be.addError(MensajesNegocio.ERROR_ADQISICION_NULL);
        } else {
            if (StringsUtils.isEmpty(adq.getAdqNombre())) {
                be.addError(MensajesNegocio.ERROR_ADQISICION_NOMBRE);
            }
            if (adq.getAdqFuente() == null) {
                be.addError(MensajesNegocio.ERROR_ADQISICION_FUENTE);
            }
//            if (adq.getAdqProvOrga() == null) {
//                be.addError(MensajesNegocio.ERROR_ADQISICION_PROVEEDOR);
//            }
            if (adq.getAdqMoneda() == null) {
                be.addError(MensajesNegocio.ERROR_ADQISICION_MONEDAS);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, be.getErrores().toString(), be);
            throw be;
        }

        return true;
    }
}