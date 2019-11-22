package com.sofis.entities.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Riesgos;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class FichaRiesgosValidacion {

    private static final Logger logger = Logger.getLogger(FichaRiesgosValidacion.class.getName());

    public static boolean validar(Riesgos riesgo) throws BusinessException {
        BusinessException be = new BusinessException();

        if (riesgo == null) {
            be.addError(MensajesNegocio.ERROR_RIESGOS_NULL);
        } else {
            if (StringsUtils.isEmpty(riesgo.getRiskNombre())) {
                be.addError(MensajesNegocio.ERROR_RIESGOS_NOMBRE);
            }
            if (riesgo.getRiskProbabilidad() == null || riesgo.getRiskProbabilidad().equals(-1)) {
                be.addError(MensajesNegocio.ERROR_RIESGOS_PROBABILIDAD);
            }
            if (riesgo.getRiskImpacto() == null) {
                be.addError(MensajesNegocio.ERROR_RIESGOS_IMPACTO);
            }
            if (riesgo.getRiskFechaLimite() == null) {
                be.addError(MensajesNegocio.ERROR_RIESGOS_FECHA_LIMITE);
            }
            if (riesgo.getRiskObservaciones() != null && riesgo.getRiskObservaciones().length() > 400) {
                be.addError(MensajesNegocio.ERROR_RIESGOS_OBSERVACIONES);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }
        return true;
    }
}
