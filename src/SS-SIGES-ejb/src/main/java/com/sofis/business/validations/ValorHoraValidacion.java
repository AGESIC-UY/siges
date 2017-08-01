package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.ValorHora;
import com.sofis.exceptions.BusinessException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ValorHoraValidacion {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(ValorHora valHora) throws BusinessException {
        BusinessException be = new BusinessException();

        if (valHora == null) {
            be.addError(MensajesNegocio.ERROR_VALOR_HORA_NULL);
        } else {
            if (valHora.getValHorUsuarioFk() == null) {
                be.addError(MensajesNegocio.ERROR_VALOR_HORA_USUARIO);
            }

            if (valHora.getValHorOrganismosFk() == null) {
                be.addError(MensajesNegocio.ERROR_VALOR_HORA_ORGANISMO);
            }
            if (valHora.getValHorMonedaFk() == null) {
                be.addError(MensajesNegocio.ERROR_VALOR_HORA_MONEDA);
            }
            if (valHora.getValHorValor() == null) {
                be.addError(MensajesNegocio.ERROR_VALOR_HORA_VALOR);
            }
            if (valHora.getValHorAnio() == null) {
                be.addError(MensajesNegocio.ERROR_VALOR_HORA_ANIO);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
