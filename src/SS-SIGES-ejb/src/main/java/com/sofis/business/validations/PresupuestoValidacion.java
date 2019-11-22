package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Presupuesto;
import com.sofis.exceptions.BusinessException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class PresupuestoValidacion {
    
    private static final Logger logger = Logger.getLogger(PresupuestoValidacion.class.getName());

    public static boolean validar(Presupuesto pre) throws BusinessException {
        logger.finest("Validar Presupuesto.");
        BusinessException be = new BusinessException();

        if (pre == null) {
            be.addError(MensajesNegocio.ERROR_RIESGOS_NULL);
        } else {
//            if (pre.getFuenteFinanciamiento() == null) {
//                be.addError(MensajesNegocio.ERROR_RIESGOS_NOMBRE);
//            }
//            if (pre.getPreBase() == null) {
//                be.addError(MensajesNegocio.ERROR_RIESGOS_NOMBRE);
//            }
//            if (pre.getPreMoneda() == null) {
//                be.addError(MensajesNegocio.ERROR_RIESGOS_NOMBRE);
//            }
        }
        
        if (be.getErrores().size() > 0) {
            logger.logp(Level.WARNING, PresupuestoValidacion.class.getName(), "validar", be.getErrores().toString(), be);
            throw be;
        }

        return true;
    }
}
