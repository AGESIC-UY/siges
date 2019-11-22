package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.RolesInteresados;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class RolesInteresadosValidacion {
    
    private static final Logger logger = Logger.getLogger(RolesInteresadosValidacion.class.getName());

    public static boolean validar(RolesInteresados rol) throws BusinessException {
        BusinessException be = new BusinessException();

        if (rol == null) {
            be.addError(MensajesNegocio.ERROR_ROL_INT_NULL);
        } else {
            if (StringsUtils.isEmpty(rol.getRolintNombre())) {
                be.addError(MensajesNegocio.ERROR_ROL_INT_NOMBRE);
            }

            if (rol.getRolintOrgFk() == null) {
                be.addError(MensajesNegocio.ERROR_ROL_INT_ORG);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
