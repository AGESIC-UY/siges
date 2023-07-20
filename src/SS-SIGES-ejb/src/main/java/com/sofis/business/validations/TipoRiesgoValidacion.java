package com.sofis.business.validations;

import com.sofis.business.utils.Utils;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.TipoRiesgo;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Validaciones para TipoRiesgo
 *
 * @author mgarcia
 */
public class TipoRiesgoValidacion {

    private static final Logger logger = Logger.getLogger(TipoRiesgoValidacion.class.getName());

    public static boolean validar(TipoRiesgo ambito) throws BusinessException {
        BusinessException be = new BusinessException();

        if (ambito == null) {
            be.addError(MensajesNegocio.ERROR_AMBITO_NULL);
        } else {
            if (StringsUtils.isEmpty(ambito.getTrsNombre())) {
                be.addError(MensajesNegocio.ERROR_AMBITO_NOMBRE);
            } else if (ambito.getTrsNombre().length() > TipoRiesgo.NOMBRE_LENGHT) {
                be.addError(MensajesNegocio.ERROR_TIPO_RIESGO_NOMBRE_100);
            }

            if (ambito.getTrsOrgFk() == null) {
                be.addError(MensajesNegocio.ERROR_AMBITO_ORG);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
