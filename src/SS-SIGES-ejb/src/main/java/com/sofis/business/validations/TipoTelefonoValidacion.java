 
package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.generico.utils.generalutils.BooleanUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.entities.data.TipoTelefono;
import com.sofis.exceptions.BusinessException;

/**
 *
 * @author Usuario
 */
public class TipoTelefonoValidacion {
     public static boolean validar(TipoTelefono tel) throws BusinessException {
        boolean respuesta = true;
        BusinessException ge = new BusinessException();
        if (tel==null) {
            ge.addError(ConstantesErrores.ERROR_DATO_VACIO);
        } else {
            if (StringsUtils.isEmpty(tel.getTipTelCodigo())) {
                ge.addError(ConstantesErrores.ERROR_CODIGO_VACIO);
            }
            if (StringsUtils.isEmpty(tel.getTipTelDescripcion())) {
                ge.addError(ConstantesErrores.ERROR_DESCRIPCION_VACIO);
            }
        }
        if (ge.getErrores().size()>0) {
            throw ge;
        }
        return respuesta;
    }
    
    public static boolean compararParaGrabar(TipoTelefono c1, TipoTelefono c2) {
        boolean respuesta = true;
        if (c1!=null) {
            if (c2!=null) {
                respuesta = StringsUtils.sonStringIguales(c1.getTipTelCodigo(), c2.getTipTelCodigo());
                respuesta = respuesta && StringsUtils.sonStringIguales(c1.getTipTelDescripcion(), c2.getTipTelDescripcion());
                respuesta = respuesta && BooleanUtils.sonBooleanIguales(c1.getTipTelHabilitado(), c2.getTipTelHabilitado());
                
            }  
        } else {
            respuesta = c2==null;
        }
        return respuesta;
    }
}
