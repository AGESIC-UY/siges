package com.sofis.entities.validations;

import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.IdentificadorGrpErp;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class IdentificadorGrpErpValidacion {

    private static final Logger logger = Logger.getLogger(IdentificadorGrpErpValidacion.class.getName());

    public static boolean validar(final IdentificadorGrpErp identificador) throws BusinessException {
        BusinessException be = new BusinessException();

        if (identificador == null) {
            be.addError(MensajesNegocio.ERROR_CAUSAL_COMPRA_NULL);
        } else {
            if (StringsUtils.isEmpty(identificador.getIdGrpErpNombre())) {
                be.addError(MensajesNegocio.ERROR_CAUSAL_COMPRA_NOMBRE);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, be.getMessage(), be);
            throw be;
        }

        return true;
    }
}
