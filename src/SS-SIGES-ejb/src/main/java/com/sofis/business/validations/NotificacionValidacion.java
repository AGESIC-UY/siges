package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Notificacion;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class NotificacionValidacion {

    private static final Logger logger = Logger.getLogger(NotificacionValidacion.class.getName());   

    public static boolean validar(Notificacion notif) throws BusinessException {
        BusinessException be = new BusinessException();

        if (notif == null) {
            be.addError(MensajesNegocio.ERROR_NOTIFICACION_NULL);
        } else {
            if (StringsUtils.isEmpty(notif.getNotCod())) {
                be.addError(MensajesNegocio.ERROR_NOTIFICACION_CODIGO);
            }

            if (StringsUtils.isEmpty(notif.getNotDesc())) {
                be.addError(MensajesNegocio.ERROR_NOTIFICACION_DESC);
            }

            if (StringsUtils.isEmpty(notif.getNotMsg())) {
                be.addError(MensajesNegocio.ERROR_NOTIFICACION_MSG);
            }

            if (notif.getNotOrgFk() == null) {
                be.addError(MensajesNegocio.ERROR_NOTIFICACION_ORG);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
