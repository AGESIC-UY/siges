package com.sofis.entities.validations;

import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.CentroCosto;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class CentroCostoValidacion {

    private static final Logger logger = Logger.getLogger(CentroCostoValidacion.class.getName());

    public static boolean validar(CentroCosto cenCos) throws BusinessException {
        logger.finest("Validar CentroCosto.");
        BusinessException be = new BusinessException();

        if (cenCos == null) {
            be.addError(MensajesNegocio.ERROR_CENTRO_COSTO_NULL);
        } else {
            if (StringsUtils.isEmpty(cenCos.getCenCosNombre())) {
                be.addError(MensajesNegocio.ERROR_CENTRO_COSTO_NOMBRE);
            }
            if (cenCos.getCenCosNombre() != null && cenCos.getCenCosNombre().length() > 100) {
                be.addError(MensajesNegocio.ERROR_CENTRO_COSTO_NOMBRE_LARGO);
            }
            if (cenCos.getCenCosDescripcion() != null && cenCos.getCenCosDescripcion().length() > 300) {
                be.addError(MensajesNegocio.ERROR_CENTRO_COSTO_DESCRIPCION_LARGO);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, be.getMessage(), be);
            throw be;
        }

        return true;
    }
}
