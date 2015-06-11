package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.LeccionesAprendidas;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class LeccAprendidasValidacion {
 
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);


    public static boolean validar(LeccionesAprendidas leccApre) throws BusinessException {

        BusinessException ge = new BusinessException();

        if (leccApre == null) {
            ge.addError(MensajesNegocio.ERROR_LECC_APRENDIDAS_NULL);
        } else {
            if (StringsUtils.isEmpty(leccApre.getLecaprDesc())) {
                ge.addError(MensajesNegocio.ERROR_LECC_APRENDIDAS_DESC);
            }
            if (leccApre.getLecaprFecha()==null) {
                ge.addError(MensajesNegocio.ERROR_LECC_APRENDIDAS_FECHA);
            }
            if (leccApre.getLecaprOrgFk()==null) {
                ge.addError(MensajesNegocio.ERROR_LECC_APRENDIDAS_ORG);
            }
            if (leccApre.getLecaprTipoFk()==null) {
                ge.addError(MensajesNegocio.ERROR_LECC_APRENDIDAS_TIPO);
            }
        }

        if (ge.getErrores().size() > 0) {
            logger.log(Level.WARNING, ge.getErrores().toString(), ge);
            throw ge;
        }

        return true;
    }
}
