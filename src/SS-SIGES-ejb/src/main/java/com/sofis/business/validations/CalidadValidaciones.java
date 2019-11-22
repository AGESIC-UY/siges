package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Calidad;
import com.sofis.exceptions.BusinessException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class CalidadValidaciones {

    private static final Logger logger = Logger.getLogger(CalidadValidaciones.class.getName());

    public static boolean validar(List<Calidad> calidad) throws BusinessException {
        boolean result = true;
        if (calidad != null) {
            for (Calidad c : calidad) {
                boolean valido = validar(c);
                if (!valido) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public static boolean validar(Calidad calidad) throws BusinessException {
        BusinessException be = new BusinessException();

        if (calidad == null) {
            be.addError(MensajesNegocio.ERROR_CALIDAD_NULL);
        } else {
            if (calidad.getCalPeso() < 0
                    || calidad.getCalPeso() > 100) {
                be.addError(MensajesNegocio.ERROR_CALIDAD_PESO);
            }
            if (calidad.getCalVcaFk() == null) {
                be.addError(MensajesNegocio.ERROR_CALIDAD_VALOR);
            }
            if (calidad.getCalActualizacion() == null) {
                be.addError(MensajesNegocio.ERROR_CALIDAD_FECHA_ACT);
            }
            if (calidad.getCalEntFk() == null
                    && calidad.getCalProdFk() == null
                    && calidad.getCalTcaFk() == null) {
                be.addError(MensajesNegocio.ERROR_CALIDAD_SIN_ITEM);
            }
            if (calidad.getCalProyFk() == null) {
                be.addError(MensajesNegocio.ERROR_CALIDAD_PROYECTO);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.SEVERE, be.getMessage(), be);
            throw be;
        }

        return true;
    }
}
