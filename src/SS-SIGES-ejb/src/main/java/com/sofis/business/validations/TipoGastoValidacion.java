package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Ambito;
import com.sofis.entities.data.TipoGasto;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class TipoGastoValidacion {
    
    private static final Logger logger = Logger.getLogger(TipoGastoValidacion.class.getName());

    public static boolean validar(TipoGasto tipoGasto) throws BusinessException {
        BusinessException be = new BusinessException();

        if (tipoGasto == null) {
            be.addError(MensajesNegocio.ERROR_TIPO_GASTO_NULL);
        } else {
            if (StringsUtils.isEmpty(tipoGasto.getTipogasNombre())) {
                be.addError(MensajesNegocio.ERROR_TIPO_GASTO_NOM);
            }

            if (tipoGasto.getTipogasOrgFk() == null) {
                be.addError(MensajesNegocio.ERROR_TIPO_GASTO_ORG);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
