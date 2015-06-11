package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.LeccionesAprendidas;
import com.sofis.entities.data.PlantillaCronograma;
import com.sofis.entities.data.PlantillaEntregables;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class PlantillaCronogramaValidacion {
 
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);


    public static boolean validar(PlantillaCronograma ob) throws BusinessException {

        BusinessException ge = new BusinessException();

        if (ob == null) {
            ge.addError(MensajesNegocio.ERROR_PLANTILLA_NULL);
        } else {
            if (StringsUtils.isEmpty(ob.getPcronoNombre())) {
                ge.addError(MensajesNegocio.ERROR_PLANTILLA_DESC);
            }
            
            if (ob.getPlantillaEntregablesSet() == null || ob.getPlantillaEntregablesSet().size() == 0){
                       ge.addError(MensajesNegocio.ERROR_PLANTILLA_ENTREGABLES_VACIO);
            }
            for (PlantillaEntregables e: ob.getPlantillaEntregablesSet()){
                if (e.getPentregablesNumeroAnt().equals(e.getPentregablesNumero())){
                    ge.addError(MensajesNegocio.ERROR_PLANTILLA_CIRCULAR);
                    break;
                }
            }
            
        }

        if (ge.getErrores().size() > 0) {
            logger.log(Level.WARNING, ge.getErrores().toString(), ge);
            throw ge;
        }

        return true;
    }
}
