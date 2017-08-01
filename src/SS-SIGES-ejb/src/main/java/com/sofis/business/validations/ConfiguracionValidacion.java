package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.entities.data.Configuracion;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.BooleanUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;

/**
 *
 * @author Usuario
 */
public class ConfiguracionValidacion {

    public static boolean validar(Configuracion cnf) throws BusinessException {
        boolean respuesta = true;
        BusinessException ge = new BusinessException();
        if (cnf == null) {
            ge.addError(ConstantesErrores.ERROR_DATO_VACIO);
        } else {
            if (StringsUtils.isEmpty(cnf.getCnfCodigo())) {
                ge.addError(ConstantesErrores.ERROR_CODIGO_VACIO);
            }
            if (StringsUtils.isEmpty(cnf.getCnfValor())) {
                ge.addError(ConstantesErrores.ERROR_DATO_VACIO);
            }

        }
        if (ge.getErrores().size() > 0) {
            throw ge;
        }
        return respuesta;
    }

    /**
     * Este método devuelve true si los dos elementos son iguales como para
     * grabar
     *
     * @param c1
     * @param c2
     * @return
     */
    public static boolean compararParaGrabar(Configuracion c1, Configuracion c2) {
        boolean respuesta = true;
        if (c1 != null) {
            if (c2 != null) {
                respuesta = StringsUtils.sonStringIguales(c1.getCnfCodigo(), c2.getCnfCodigo());
                respuesta = respuesta && StringsUtils.sonStringIguales(c1.getCnfDescripcion(), c2.getCnfDescripcion());
                respuesta = respuesta && StringsUtils.sonStringIguales(c1.getCnfValor(), c2.getCnfValor());
                respuesta = respuesta && BooleanUtils.sonBooleanIguales(c1.getCnfProtegido(), c2.getCnfProtegido());
                respuesta = respuesta && BooleanUtils.sonBooleanIguales(c1.getCnfHtml(), c1.getCnfHtml());
            }
        } else {
            respuesta = c2 == null;
        }
        //logger.info("en Configuracion validaion = "+respuesta);
        return respuesta;
    }

}
