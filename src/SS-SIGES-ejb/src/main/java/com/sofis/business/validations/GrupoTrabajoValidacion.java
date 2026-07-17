package com.sofis.business.validations;

import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.GrupoTrabajo;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Validaciones para TipoRiesgo
 *
 * @author mgarcia
 */
public class GrupoTrabajoValidacion {

    private static final Logger logger = Logger.getLogger(GrupoTrabajoValidacion.class.getName());

    public static boolean validar(GrupoTrabajo ambito) throws BusinessException {
        BusinessException be = new BusinessException();

        if (ambito == null) {
            be.addError(MensajesNegocio.ERROR_AMBITO_NULL);
        } else {
            if (StringsUtils.isEmpty(ambito.getGtbNombre())) {
                be.addError(MensajesNegocio.ERROR_AMBITO_NOMBRE);
            } else if (ambito.getGtbNombre().length() > GrupoTrabajo.NOMBRE_LENGHT) {
                be.addError(MensajesNegocio.ERROR_TIPO_RIESGO_NOMBRE_100);
            }

            if (ambito.getGtbNombre() == null) {
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
