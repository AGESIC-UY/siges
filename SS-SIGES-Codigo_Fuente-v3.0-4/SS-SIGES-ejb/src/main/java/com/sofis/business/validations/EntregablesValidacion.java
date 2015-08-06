package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Entregables;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class EntregablesValidacion {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(Set<Entregables> entList) throws BusinessException {
        if (CollectionsUtils.isNotEmpty(entList)) {
            for (Entregables ent : entList) {
                validar(ent);
            }
        }

        return true;
    }

    public static boolean validar(Entregables ent) throws BusinessException {
        logger.finest("Validar Entregables.");
        BusinessException be = new BusinessException();

        if (ent == null) {
            be.addError(MensajesNegocio.ERROR_ENTREGABLE_NULL);
        } else {
            if (StringsUtils.isEmpty(ent.getEntNombre())) {
                be.addError(MensajesNegocio.ERROR_ENTREGABLE_NOMBRE);
            }
            if (ent.getCoordinadorUsuFk() == null) {
                be.addError(MensajesNegocio.ERROR_ENTREGABLE_COORDINADOR);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.logp(Level.WARNING, AdquisicionValidacion.class.getName(), "validar", be.getErrores().toString(), be);
            throw be;
        }

        return true;
    }
}
