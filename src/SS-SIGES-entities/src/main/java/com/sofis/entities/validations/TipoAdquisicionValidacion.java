package com.sofis.entities.validations;

import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.TipoAdquisicion;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class TipoAdquisicionValidacion {

    private static final Logger logger = Logger.getLogger(TipoAdquisicionValidacion.class.getName());

    public static boolean validar(TipoAdquisicion adq) throws BusinessException {
        logger.finest("Validar Tipo Adquisicion.");
        BusinessException be = new BusinessException();

        if (adq == null) {
            be.addError(MensajesNegocio.ERROR_TIPO_ADQUISICION_NULL);
        } else {
            if (StringsUtils.isEmpty(adq.getTipAdqNombre())) {
                be.addError(MensajesNegocio.ERROR_TIPO_ADQUISICION_NOMBRE);
            }
            if (adq.getTipAdqNombre() != null && adq.getTipAdqNombre().length() > 100) {
                be.addError(MensajesNegocio.ERROR_TIPO_ADQUISICION_NOMBRE_LARGO);
            }
            if (adq.getTipAdqDescripcion() != null && adq.getTipAdqDescripcion().length() > 300) {
                be.addError(MensajesNegocio.ERROR_TIPO_ADQUISICION_DESCRIPCION_LARGO);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, be.getMessage(), be);
            throw be;
        }

        return true;
    }
}
