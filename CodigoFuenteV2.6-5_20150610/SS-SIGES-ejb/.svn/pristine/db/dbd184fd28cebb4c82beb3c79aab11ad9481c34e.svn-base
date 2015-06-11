package com.sofis.business.validations;

import com.sofis.business.validations.AdquisicionValidacion;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.PlantillaCronograma;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class PlantillaCroValidacion {
    
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validarGenerar(PlantillaCronograma plantillaCro, Date plantillaFechaInicio) throws BusinessException {
        logger.finest("Validar Generar Plantilla Cronograma.");
        BusinessException be = new BusinessException();

        if (plantillaCro == null) {
            be.addError(MensajesNegocio.ERROR_PLANTILLA_NULL);
        } else {
            if (CollectionsUtils.isEmpty(plantillaCro.getPlantillaEntregablesSet())) {
                be.addError(MensajesNegocio.ERROR_PLANTILLA_ENTREGABLES_VACIO);
            }
        }
        
        if(plantillaFechaInicio == null){
            be.addError(MensajesNegocio.ERROR_PLANTILLA_FECHA_INICIO);
        }
        
        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
