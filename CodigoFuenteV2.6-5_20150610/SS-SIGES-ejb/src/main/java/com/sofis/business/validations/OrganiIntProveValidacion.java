package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class OrganiIntProveValidacion {
    
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(OrganiIntProve orga) throws BusinessException {
        BusinessException be = new BusinessException();

        if (orga == null) {
            be.addError(MensajesNegocio.ERROR_ORGANIZACION_NULL);
        } else {
            if (StringsUtils.isEmpty(orga.getOrgaNombre())) {
                be.addError(MensajesNegocio.ERROR_ORGANIZACION_NOMBRE);
            }

            if (orga.getOrgaOrgFk() == null) {
                be.addError(MensajesNegocio.ERROR_ORGANIZACION_ORG);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
