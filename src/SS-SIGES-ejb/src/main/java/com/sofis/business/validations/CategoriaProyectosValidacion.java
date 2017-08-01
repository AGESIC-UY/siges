package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.CategoriaProyectos;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class CategoriaProyectosValidacion {
    
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(CategoriaProyectos cp) throws BusinessException {
        BusinessException be = new BusinessException();

        if (cp == null) {
            be.addError(MensajesNegocio.ERROR_CAT_PROY_NULL);
        } else {
            if (StringsUtils.isEmpty(cp.getCatProyCodigo())) {
                be.addError(MensajesNegocio.ERROR_CAT_PROY_CODIGO);
            }
            if (StringsUtils.isEmpty(cp.getCatProyNombre())) {
                be.addError(MensajesNegocio.ERROR_CAT_PROY_NOMBRE);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
