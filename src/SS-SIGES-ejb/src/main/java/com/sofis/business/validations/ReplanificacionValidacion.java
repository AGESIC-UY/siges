package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.ProyReplanificacion;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ReplanificacionValidacion {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(ProyReplanificacion replan) throws BusinessException {
        BusinessException be = new BusinessException();

        if (replan == null) {
            be.addError(ConstantesErrores.ERROR_DATO_VACIO);
        } else {
            if (replan.getProyreplanFecha() == null) {
                be.addError(MensajesNegocio.ERROR_REPLANIFICACION_FECHA);
            }
            if (    replan.isProyreplanGenerarLineaBase() && 
                    replan.getProyreplanHistorial() && 
                    StringsUtils.isEmpty(replan.getProyreplanDesc())) {
                be.addError(MensajesNegocio.ERROR_REPLANIFICACION_DESC);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.INFO, be.getErrores().toString());
            throw be;
        }

        return true;
    }
}
