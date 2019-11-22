package com.sofis.entities.validations;

import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.CausalCompra;
import com.sofis.entities.data.FuenteProcedimientoCompra;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class FuenteProcedimientoCompraValidacion {

    private static final Logger logger = Logger.getLogger(FuenteProcedimientoCompraValidacion.class.getName());

    public static boolean validar(FuenteProcedimientoCompra fueProCom) throws BusinessException {
        logger.finest("Validar FuenteProcedimientoCompra.");
        BusinessException be = new BusinessException();

        if (fueProCom == null) {
            be.addError(MensajesNegocio.ERROR_FUENTE_PROCEDIMIENTO_COMPRA_NULL);
        } else {
            if (StringsUtils.isEmpty(fueProCom.getFueProComFuente())) {
                be.addError(MensajesNegocio.ERROR_FUENTE_PROCEDIMIENTO_COMPRA_FUENTE);
            }
            if (StringsUtils.isEmpty(fueProCom.getFueProComProcedimientoCompra())) {
                be.addError(MensajesNegocio.ERROR_FUENTE_PROCEDIMIENTO_COMPRA_PROC_COMP);
            }
            if (fueProCom.getFueProComCausalesCompra() == null || fueProCom.getFueProComCausalesCompra().size() == 0) {
                be.addError(MensajesNegocio.ERROR_FUENTE_PROCEDIMIENTO_COMPRA_CAUSAL_VACIO);
            }
            if (hayDuplicados(fueProCom.getFueProComCausalesCompra())) {
                be.addError(MensajesNegocio.ERROR_FUENTE_PROCEDIMIENTO_COMPRA_CAUSAL_REPETIDO);
            }

        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, be.getMessage(), be);
            throw be;
        }

        return true;
    }

    private static boolean hayDuplicados(List<CausalCompra> lista) {
        for (CausalCompra cau1 : lista) {
            int cant = 0;
            for (CausalCompra cau2 : lista) {
                if (cau2.getCauComPk().equals(cau1.getCauComPk())) {
                    cant++;
                }
            }
            if (cant > 1) { // si aparece más de 1 vez es duplicado
                return true;
            }
        }
        return false;
    }
}
