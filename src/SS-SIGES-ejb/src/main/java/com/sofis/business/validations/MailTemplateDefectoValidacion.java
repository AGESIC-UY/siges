package com.sofis.business.validations;

import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.MailTemplateDefecto;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;

public class MailTemplateDefectoValidacion {

	public static boolean validar(MailTemplateDefecto mail) throws BusinessException {
		BusinessException be = new BusinessException();

		if (mail == null) {
			be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_NULL);
		} else {
			if (StringsUtils.isEmpty(mail.getAsunto())) {
				be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_ASUNTO);
			}

			if (StringsUtils.isEmpty(mail.getCodigo())) {
				be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_COD);
			}

			if (StringsUtils.isEmpty(mail.getMensaje())) {
				be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_MENSAJE);
			}
		}

		if (be.getErrores().size() > 0) {

			throw be;
		}

		return true;
	}
}
