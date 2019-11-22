package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.Moneda;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class MonedaValidacion {
    
    private static final Logger logger = Logger.getLogger(MonedaValidacion.class.getName());   

    public static boolean validar(Moneda moneda) throws BusinessException {
        BusinessException be = new BusinessException();

        if (moneda == null) {
            be.addError(MensajesNegocio.ERROR_MONEDA_NULL);
        } else {
            if (StringsUtils.isEmpty(moneda.getMonNombre())) {
                be.addError(MensajesNegocio.ERROR_MONEDA_NOMBRE);
            }
            
            if (StringsUtils.isEmpty(moneda.getMonSigno())) {
                be.addError(MensajesNegocio.ERROR_MONEDA_SIGNO);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
