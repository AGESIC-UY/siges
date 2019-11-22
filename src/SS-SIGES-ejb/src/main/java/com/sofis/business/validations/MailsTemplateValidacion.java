package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.MailsTemplate;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class MailsTemplateValidacion {
    
    private static final Logger logger = Logger.getLogger(MailsTemplateValidacion.class.getName());   

    public static boolean validar(MailsTemplate mail) throws BusinessException {
        BusinessException be = new BusinessException();

        if (mail == null) {
            be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_NULL);
        } else {
            if (StringsUtils.isEmpty(mail.getMailTmpAsunto())) {
                be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_ASUNTO);
            }

            if (StringsUtils.isEmpty(mail.getMailTmpCod())) {
                be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_COD);
            }

            if (StringsUtils.isEmpty(mail.getMailTmpMensaje())) {
                be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_MENSAJE);
            }

            if (mail.getMailTmpOrgFk() == null) {
                be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_ORG);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
