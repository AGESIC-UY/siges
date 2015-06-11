package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Interesados;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class InteresadosValidacion {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(Set<Interesados> interesados) throws BusinessException {
        if (interesados != null) {
            for (Interesados i : interesados) {
                validar(i);
            }
        }
        return true;
    }

    public static boolean validar(Interesados interesados) throws BusinessException {

        BusinessException ge = new BusinessException();

        if (interesados == null) {
            ge.addError(MensajesNegocio.ERROR_INTERESADO_NULL);
        } else {
            if (interesados.getIntPersonaFk() == null
                    || StringsUtils.isEmpty(interesados.getIntPersonaFk().getPersNombre())) {
                ge.addError(MensajesNegocio.ERROR_INTERESADO_NOMBRE);
            }
            if (interesados.getIntRolintFk() == null) {
                ge.addError(MensajesNegocio.ERROR_INTERESADO_ROL);
            }
            if (interesados.getIntOrgaFk() == null) {
                ge.addError(MensajesNegocio.ERROR_INTERESADO_ORGANIZACION);
            }
            if (interesados.getIntPersonaFk() == null
                    || StringsUtils.isEmpty(interesados.getIntPersonaFk().getPersCargo())) {
                ge.addError(MensajesNegocio.ERROR_INTERESADO_CARGO);
            }
//            if (interesados.getIntPersonaFk() == null
//                    || StringsUtils.isEmpty(interesados.getIntPersonaFk().getPersTelefono())) {
//                ge.addError(MensajesNegocio.ERROR_INTERESADO_TELEFONO);
//            }
            if (interesados.getIntPersonaFk() == null
                    || StringsUtils.isEmpty(interesados.getIntPersonaFk().getPersMail())) {
                ge.addError(MensajesNegocio.ERROR_INTERESADO_CORREO);
            }
        }

        if (ge.getErrores().size() > 0) {
            logger.logp(Level.WARNING, InteresadosValidacion.class.getName(), "validar", ge.getErrores().toString(), ge);
            throw ge;
        }

        return true;
    }
}
