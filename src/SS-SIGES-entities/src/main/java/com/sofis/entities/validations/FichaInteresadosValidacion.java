package com.sofis.entities.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Interesados;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.EmailValidator;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class FichaInteresadosValidacion {

    private static final Logger logger = Logger.getLogger(FichaInteresadosValidacion.class.getName());

    public static boolean validar(Interesados interesado) throws BusinessException {
        logger.finest("Validar Ficha Interesados.");

        BusinessException be = new BusinessException();

        if (interesado == null) {
            be.addError(MensajesNegocio.ERROR_INTERESADO_NULL);
        } else {

            if (interesado.getIntOrgaFk() == null) {
                be.addError(MensajesNegocio.ERROR_INTERESADO_ORGANIZACION);
            }
            if (interesado.getIntRolintFk() == null) {
                be.addError(MensajesNegocio.ERROR_INTERESADO_ROL);
            }
            if (interesado.getIntPersonaFk() == null
                    || StringsUtils.isEmpty(interesado.getIntPersonaFk().getPersNombre())) {
                be.addError(MensajesNegocio.ERROR_INTERESADO_NOMBRE);
            }
            if (interesado.getIntPersonaFk() == null
                    || StringsUtils.isEmpty(interesado.getIntPersonaFk().getPersCargo())) {
                be.addError(MensajesNegocio.ERROR_INTERESADO_CARGO);
            }
//            if (interesado.getIntPersonaFk() == null
//                    || StringsUtils.isEmpty(interesado.getIntPersonaFk().getPersTelefono())) {
//                be.addError(MensajesNegocio.ERROR_INTERESADO_TELEFONO);
//            }
            if (interesado.getIntPersonaFk() == null
                    || StringsUtils.isEmpty(interesado.getIntPersonaFk().getPersMail())) {
                be.addError(MensajesNegocio.ERROR_INTERESADO_CORREO);
            }
            
            if (interesado.getIntPersonaFk() != null && interesado.getIntPersonaFk().getPersMail() != null
                    && !EmailValidator.validateEmail(interesado.getIntPersonaFk().getPersMail())) {
                /**
                 * Verifico el formato del mail de la persona
                 */
                be.addError(MensajesNegocio.ERROR_INTERESADO_CORREO_INVALIDO);
            }

        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
