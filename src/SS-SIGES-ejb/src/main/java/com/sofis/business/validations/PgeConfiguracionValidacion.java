/*
 * 
 * 
 */
package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.generico.utils.generalutils.BooleanUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.entities.data.PgeConfiguraciones;
import com.sofis.exceptions.BusinessException;

/**
 *
 * @author Usuario
 */
public class PgeConfiguracionValidacion {

    public static boolean validar(PgeConfiguraciones pcn) throws BusinessException {
        boolean respuesta = true;
        BusinessException ge = new BusinessException();
        if (pcn == null) {
            ge.addError(ConstantesErrores.ERROR_DATO_VACIO);
        } else {
            if (StringsUtils.isEmpty(pcn.getCnfClave())) {
                ge.addError(ConstantesErrores.ERROR_CODIGO_VACIO);
            }
            if (StringsUtils.isEmpty(pcn.getCnfValor())) {
                ge.addError(ConstantesErrores.ERROR_VALOR_VACIO);
            }
        }
        if (ge.getErrores().size() > 0) {
            throw ge;
        }
        return respuesta;
    }

    public static boolean compararParaGrabar(PgeConfiguraciones c1, PgeConfiguraciones c2) {
        boolean respuesta = true;
        if (c1 != null) {
            if (c2 != null) {
                respuesta = StringsUtils.sonStringIguales(c1.getCnfClave(), c2.getCnfClave());
                respuesta = respuesta && StringsUtils.sonStringIguales(c1.getCnfValor(), c2.getCnfValor());
            }
        } else {
            respuesta = c2 == null;
        }
        return respuesta;
    }
}
