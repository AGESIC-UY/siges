package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Productos;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ProductosValidacion {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(Productos prod) throws BusinessException {
        BusinessException be = new BusinessException();

        if (prod == null) {
            be.addError(MensajesNegocio.ERROR_PRODUCTO_NULL);
        } else {
            if (prod.getProdEntregableFk() == null) {
                be.addError(MensajesNegocio.ERROR_PRODUCTO_ENTREGABLE);
            }
            if (StringsUtils.isEmpty(prod.getProdDesc())) {
                be.addError(MensajesNegocio.ERROR_PRODUCTO_DESC);
            }
            if (prod.getProdFechaCrea() == null) {
                be.addError(MensajesNegocio.ERROR_PRODUCTO_FECHA);
            }
            if (prod.getProdPeso() <= 0) {
                be.addError(MensajesNegocio.ERROR_PRODUCTO_PESO);
            }
            if (StringsUtils.isEmpty(prod.getProdUndMedida())) {
                be.addError(MensajesNegocio.ERROR_PRODUCTO_MEDIDA);
            }

            if (CollectionsUtils.isNotEmpty(prod.getProdMesList())) {
                ProdMesValidacion.validar(prod.getProdMesList());
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, "validar", be);
            if (be.getErrores() != null) {
                for (String er : be.getErrores()) {
                    logger.log(Level.SEVERE, er);
                }
            }
            throw be;
        }

        return true;
    }
}
