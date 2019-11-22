/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Personas;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.EmailValidator;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class PersonaValidacion {

    private static final Logger logger = Logger.getLogger(PersonaValidacion.class.getName());

    public static boolean validar(Personas persona) throws BusinessException {
        logger.finest("Validar Personas.");
        BusinessException be = new BusinessException("Validación de la persona");

        if (persona == null) {
            be.addError(MensajesNegocio.ERROR_PERSONA_NULL);
        } else {
            if (persona.getPersOrgaFk() == null) {
                be.addError(MensajesNegocio.ERROR_PERSONA_ORGA);
            }
            if (StringsUtils.isEmpty(persona.getPersNombre())) {
                be.addError(MensajesNegocio.ERROR_PERSONA_NOMBRE);
            }
            if (persona.getPersRolFk() == null || persona.getPersRolFk().intValue() < 1) {
                be.addError(MensajesNegocio.ERROR_PERSONA_ROL);
            }
            if(persona.getPersMail() != null && !EmailValidator.validateEmail(persona.getPersMail())){
                be.addError(MensajesNegocio.ERROR_PERSONA_MAIL_INVALIDO);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.logp(Level.WARNING, PersonaValidacion.class.getName(), "validar", be.getErrores().toString(), be);
            throw be;
        }

        return true;
    }
}
