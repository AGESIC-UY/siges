package com.sofis.entities.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Interesados;
import com.sofis.entities.data.Participantes;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class FichaParticipantesValidacion {

    private static final Logger logger = Logger.getLogger(FichaParticipantesValidacion.class.getName());

    public static boolean validar(Participantes participante) throws BusinessException {
        logger.finest("Validar Ficha Participantes.");

        BusinessException be = new BusinessException();

        if (participante == null) {
            be.addError(MensajesNegocio.ERROR_PARTICIPANTE_NULL);
        } else {
            if (participante.getPartProyectoFk() == null) {
                be.addError(MensajesNegocio.ERROR_PARTICIPANTE_PROYECTO);
            }
            if (participante.getPartUsuarioFk() == null
                    || participante.getPartUsuarioFk().getUsuId() == null
                    || participante.getPartUsuarioFk().getUsuId().intValue() == 0) {
                be.addError(MensajesNegocio.ERROR_PARTICIPANTE_USUARIO);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
