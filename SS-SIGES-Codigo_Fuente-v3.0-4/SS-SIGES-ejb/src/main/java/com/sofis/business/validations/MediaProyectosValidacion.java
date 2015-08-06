package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.MediaProyectos;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class MediaProyectosValidacion {
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(MediaProyectos mp) throws BusinessException {
        logger.finest("Validar Adquisicion.");
        BusinessException be = new BusinessException();

        if (mp == null) {
            be.addError(MensajesNegocio.ERROR_MEDIA_PROY_NULL);
        } else {
            if (StringsUtils.isEmpty(mp.getMediaLink())) {
                be.addError(MensajesNegocio.ERROR_MEDIA_PROY_LINK);
            }
            if (mp.getMediaProyFk()==null) {
                be.addError(MensajesNegocio.ERROR_MEDIA_PROY_PROYECTO);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.logp(Level.WARNING, AdquisicionValidacion.class.getName(), "validar", be.getErrores().toString(), be);
            throw be;
        }

        return true;
    }
}
