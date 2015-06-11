/*
 * 
 * 
 */
package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.entities.data.Noticia;
import com.sofis.exceptions.BusinessException;

/**
 *
 * @author Usuario
 */
public class NoticiaValidacion {
     public static boolean validar(Noticia not) throws BusinessException {
        boolean respuesta = true;
        BusinessException ge = new BusinessException();
        if (not==null) {
            ge.addError(ConstantesErrores.ERROR_DATO_VACIO);
        } else {
            if (StringsUtils.isEmpty(not.getNotCodigo())) {
                ge.addError(ConstantesErrores.ERROR_CODIGO_VACIO);
            }
            
        }
        if (ge.getErrores().size()>0) {
            throw ge;
        }
        return respuesta;
    }
    
    public static boolean compararParaGrabar(Noticia c1, Noticia c2) {
        boolean respuesta = true;
        if (c1!=null) {
            if (c2!=null) {
                respuesta = StringsUtils.sonStringIguales(c1.getNotCodigo(), c2.getNotCodigo());
                respuesta = respuesta && StringsUtils.sonStringIguales(c1.getNotContenido(), c2.getNotContenido());
                  respuesta =  respuesta &&StringsUtils.sonStringIguales(c1.getNotAmpliar(), c2.getNotAmpliar());
                respuesta = respuesta && StringsUtils.sonStringIguales(c1.getNotImagen(), c2.getNotImagen());
                 respuesta =  respuesta &&StringsUtils.sonStringIguales(c1.getNotTitulo(), c2.getNotTitulo());
                respuesta = respuesta && DatesUtils.sonStringIguales(c1.getNotDesde(), c2.getNotDesde());
                respuesta = respuesta && DatesUtils.sonStringIguales(c1.getNotHasta(), c2.getNotHasta());
            }  
        } else {
            respuesta = c2==null;
        }
        return respuesta;
    }
}
