package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.ComponenteProducto;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ComponenteProductoValidacion {
    
    private static final Logger logger = Logger.getLogger(ComponenteProductoValidacion.class.getName());

    public static boolean validar(ComponenteProducto componenteProducto) throws BusinessException {
        BusinessException be = new BusinessException();

        if (componenteProducto == null) {
            be.addError(MensajesNegocio.ERROR_COMPONENTE_PRODUCTO_NULL);
        } else {
            if (StringsUtils.isEmpty(componenteProducto.getComNombre())) {
                be.addError(MensajesNegocio.ERROR_COMPONENTE_PRODUCTO_NOMBRE);
            }

            if (componenteProducto.getComOrgFk() == null) {
                be.addError(MensajesNegocio.ERROR_COMPONENTE_PRODUCTO_ORG);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
