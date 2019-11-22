package com.sofis.entities.validations;

import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.CausalCompra;
import com.sofis.entities.data.TipoAdquisicion;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class CausalCompraValidacion {

    private static final Logger logger = Logger.getLogger(CausalCompraValidacion.class.getName());

    public static boolean validar(CausalCompra cauCom) throws BusinessException {
        logger.finest("Validar CausalCompra.");
        BusinessException be = new BusinessException();

        if (cauCom == null) {
            be.addError(MensajesNegocio.ERROR_CAUSAL_COMPRA_NULL);
        } else {
            if (StringsUtils.isEmpty(cauCom.getCauComNombre())) {
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
