package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class FuenteFinanciamientoValidacion {
    
    private static final Logger logger = Logger.getLogger(FuenteFinanciamientoValidacion.class.getName());

    public static boolean validar(FuenteFinanciamiento fuente) throws BusinessException {
        BusinessException be = new BusinessException();

        if (fuente == null) {
            be.addError(MensajesNegocio.ERROR_FUENTE_FINANC_NULL);
        } else {
            if (StringsUtils.isEmpty(fuente.getFueNombre())) {
                be.addError(MensajesNegocio.ERROR_FUENTE_FINANC_NOMBRE);
            }

            if (fuente.getFueOrgFk() == null) {
                be.addError(MensajesNegocio.ERROR_FUENTE_FINANC_ORG);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
