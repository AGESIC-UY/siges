package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Cronogramas;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class CronogramaValidaciones {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(Cronogramas cro) throws BusinessException {
        logger.finest("Validar Cronogramas.");
        BusinessException be = new BusinessException();

        if (cro == null) {
            be.addError(MensajesNegocio.ERROR_CRONOGRAMAS_NULL);
        } else {
            if (!CollectionsUtils.isEmpty(cro.getEntregablesSet())) {
                EntregablesValidacion.validar(cro.getEntregablesSet());
            }
        }

        if (be.getErrores().size() > 0) {
            logger.logp(Level.WARNING, CronogramaValidaciones.class.getName(), "validar", be.getErrores().toString(), be);
            throw be;
        }

        return true;
    }
}
