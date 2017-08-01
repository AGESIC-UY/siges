package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.TemasCalidad;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Validaciones para los Temas de Calidad
 *
 * @author mgarcia
 */
public class TemasCalidadValidacion {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(TemasCalidad temasCalidad) throws BusinessException {
        BusinessException be = new BusinessException();

        if (temasCalidad == null) {
            be.addError(MensajesNegocio.ERROR_TEMAS_CALIDAD_NULL);
        } else {
            if (StringsUtils.isEmpty(temasCalidad.getTcaNombre())) {
                be.addError(MensajesNegocio.ERROR_TEMAS_CALIDAD_NOMBRE);
            }else if(temasCalidad.getTcaNombre().length() > 100){
                be.addError(MensajesNegocio.ERROR_TEMAS_CALIDAD_NOMBRE_LARGO);
            }

            if (temasCalidad.getTcaOrgFk() == null) {
                be.addError(MensajesNegocio.ERROR_TEMAS_CALIDAD_ORG);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
