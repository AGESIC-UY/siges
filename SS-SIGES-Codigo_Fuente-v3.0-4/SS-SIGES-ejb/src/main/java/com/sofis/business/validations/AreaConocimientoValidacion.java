package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.AreaConocimiento;
import com.sofis.entities.data.Areas;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class AreaConocimientoValidacion {
    
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(AreaConocimiento area) throws BusinessException {
        BusinessException be = new BusinessException();

        if (area == null) {
            be.addError(MensajesNegocio.ERROR_AREAS_CONOC_NULL);
        } else {
            if (StringsUtils.isEmpty(area.getConNombre())) {
                be.addError(MensajesNegocio.ERROR_AREAS_CONOC_NOMBRE);
            }

            if (area.getAreaConPadreFk() != null 
                    && area.getAreaConPadreFk().getConPk().equals(area.getConPk())) {
                be.addError(MensajesNegocio.ERROR_AREAS_CONOC_PADRE);
            }

            if (area.getConOrganismo() == null) {
                be.addError(MensajesNegocio.ERROR_AREAS_CONOC_ORG);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
