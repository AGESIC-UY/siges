package com.sofis.business.validations;

import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.AdquisicionRango;
import com.sofis.exceptions.BusinessException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdquisicionRangoValidacion {

    private static final Logger logger = Logger.getLogger(AdquisicionRangoValidacion.class.getName());

    public static boolean validar(AdquisicionRango adq) throws BusinessException {
        logger.finest("Validar Adquisicion.");
        BusinessException be = new BusinessException();

        if (adq == null) {
            be.addError(MensajesNegocio.ERROR_ADQISICION_NULL);
        } else {

            if (adq.getAdrArea() == null) {
                be.addError(MensajesNegocio.ERROR_USUARIO_AREA);
            }

            if (adq.getAdrDesde() > adq.getAdrHasta()) {
                be.addError(MensajesNegocio.ERROR_ADQISICION_RANGO_DESDE_MAYOR_HASTA);
            }

            if (!(adq.getAdrDesde() > 0)) {
                be.addError(MensajesNegocio.ERROR_ADQISICION_RANGO_DESDE_MAYOR_0);
            }

            if (!(adq.getAdrHasta() > 0)) {
                be.addError(MensajesNegocio.ERROR_ADQISICION_RANGO_HASTA_MAYOR_0);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.logp(Level.WARNING, AdquisicionRangoValidacion.class.getName(), "validar", be.getErrores().toString(), be);
            throw be;
        }

        return true;
    }
}
