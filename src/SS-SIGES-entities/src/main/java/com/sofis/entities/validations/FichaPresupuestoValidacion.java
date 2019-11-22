/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.entities.validations;

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
public class FichaPresupuestoValidacion {

    private static final Logger logger = Logger.getLogger(FichaPresupuestoValidacion.class.getName());

    public static boolean validar(Presupuesto pre) throws BusinessException {
        logger.finest("Validar Ficha Presupuestos.");
        BusinessException be = new BusinessException();

        if (pre == null) {
            be.addError(MensajesNegocio.ERROR_PRESUPUESTO_NULL);
        } else {
            /* Se comenta porque en algunos casos tienen cargado solo el importe y ahora los bloquea para guardar el proy.
            if (pre.getFuenteFinanciamiento() == null) {
                be.addError(MensajesNegocio.ERROR_PRESUPUESTO_FUENTE_FINANC);
            }
            if (pre.getPreBase() == null) {
                be.addError(MensajesNegocio.ERROR_PRESUPUESTO_BASE);
            }
            if (pre.getPreMoneda() == null) {
                be.addError(MensajesNegocio.ERROR_PRESUPUESTO_MONEDA);
            }
            */
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
