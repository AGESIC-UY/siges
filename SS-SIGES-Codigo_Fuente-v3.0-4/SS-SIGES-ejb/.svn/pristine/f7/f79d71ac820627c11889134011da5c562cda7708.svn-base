package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.generico.utils.generalutils.BooleanUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.entities.data.Ayuda;
import com.sofis.exceptions.BusinessException;

/**
 *
 * @author Usuario
 */
public class AyudaValidacion {
     public static boolean validar(Ayuda cgo) throws BusinessException {
        boolean respuesta = true;
        BusinessException ge = new BusinessException();
        if (cgo==null) {
            ge.addError(ConstantesErrores.ERROR_DATO_VACIO);
        } else {
            if (StringsUtils.isEmpty(cgo.getAyuCodigo())) {
                ge.addError(ConstantesErrores.ERROR_CODIGO_VACIO);
            }
            if (StringsUtils.isEmpty(cgo.getAyuTexto())) {
                ge.addError(ConstantesErrores.ERROR_DESCRIPCION_VACIO);
            }
        }
        if (ge.getErrores().size()>0) {
            throw ge;
        }
        return respuesta;
    }
    
    public static boolean compararParaGrabar(Ayuda c1, Ayuda c2) {
        boolean respuesta = true;
        if (c1!=null) {
            if (c2!=null) {
                respuesta = StringsUtils.sonStringIguales(c1.getAyuCodigo(), c2.getAyuCodigo());
                respuesta = respuesta && StringsUtils.sonStringIguales(c1.getAyuTexto(), c2.getAyuTexto());
             
                
            }  
        } else {
            respuesta = c2==null;
        }
        return respuesta;
    }
}
