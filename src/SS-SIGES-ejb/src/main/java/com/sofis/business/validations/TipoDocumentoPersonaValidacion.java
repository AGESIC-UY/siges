/*
 * 
 * 
 */
package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.generico.utils.generalutils.BooleanUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.entities.data.TipoDocumentoPersona;
import com.sofis.exceptions.BusinessException;

/**
 *
 * @author Usuario
 */
public class TipoDocumentoPersonaValidacion {

    public static boolean validar(TipoDocumentoPersona cnf) throws BusinessException {
        boolean respuesta = true;
        BusinessException ge = new BusinessException();
        if (cnf == null) {
            ge.addError(ConstantesErrores.ERROR_DATO_VACIO);
        } else {
            if (StringsUtils.isEmpty(cnf.getTipdocperCodigo())) {
                ge.addError(ConstantesErrores.ERROR_CODIGO_VACIO);
            }
            if (StringsUtils.isEmpty(cnf.getTipdocperDescripcion())) {
                ge.addError(ConstantesErrores.ERROR_DESCRIPCION_VACIO);
            }
        }
        if (ge.getErrores().size() > 0) {
            throw ge;
        }
        return respuesta;
    }

    public static boolean compararParaGrabar(TipoDocumentoPersona c1, TipoDocumentoPersona c2) {
        boolean respuesta = true;
        if (c1 != null) {
            if (c2 != null) {
                respuesta = StringsUtils.sonStringIguales(c1.getTipdocperCodigo(), c2.getTipdocperCodigo());
                respuesta = respuesta && StringsUtils.sonStringIguales(c1.getTipdocperDescripcion(), c2.getTipdocperDescripcion());
                respuesta = respuesta && BooleanUtils.sonBooleanIguales(c1.getTipdocperHabilitado(), c2.getTipdocperHabilitado());

            }
        } else {
            respuesta = c2 == null;
        }
        return respuesta;
    }
}
