package com.sofis.business.validations;

import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.NotificacionDefecto;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;

public class NotificacionDefectoValidacion {

	public static boolean validar(NotificacionDefecto notificacion) throws BusinessException {

		BusinessException be = new BusinessException();

		if (notificacion == null) {
			be.addError(MensajesNegocio.ERROR_NOTIFICACION_NULL);
		} else {
			if (StringsUtils.isEmpty(notificacion.getCodigo())) {
				be.addError(MensajesNegocio.ERROR_NOTIFICACION_CODIGO);
			}

			if (StringsUtils.isEmpty(notificacion.getAsunto())) {
				be.addError(MensajesNegocio.ERROR_NOTIFICACION_ASUNTO);
			}

			if (StringsUtils.isEmpty(notificacion.getDescripcion())) {
				be.addError(MensajesNegocio.ERROR_NOTIFICACION_DESC);
			}

			if (StringsUtils.isEmpty(notificacion.getMensaje())) {
				be.addError(MensajesNegocio.ERROR_NOTIFICACION_MSG);
			}
		}

		if (!be.getErrores().isEmpty()) {

			throw be;
		}

		return true;
	}
}
