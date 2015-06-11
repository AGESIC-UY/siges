/*
 * 
 * 
 */
package com.sofis.business.utils;

import com.sofis.entities.annotations.AtributoUltimaModificacion;
import com.sofis.entities.annotations.AtributoUltimaOrigen;
import com.sofis.entities.annotations.AtributoUltimoUsuario;
import com.sofis.exceptions.BusinessException;
import java.lang.reflect.Field;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class DatosAuditoria {

    public <T> T registrarDatosAuditoria(T objeto, String codigoUsuario, String origen) {
        try {
            Field fU = obtenerCampoAnotado(objeto, AtributoUltimoUsuario.class);
            if (fU != null) {
                fU.set(objeto, codigoUsuario);
            }

            Field fO = obtenerCampoAnotado(objeto, AtributoUltimaOrigen.class);
            if (fO != null) {
                fO.set(objeto, origen);
            }
            
            Field fM = obtenerCampoAnotado(objeto, AtributoUltimaModificacion.class);
            if (fM != null) {
                fM.set(objeto, new Date());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            BusinessException be = new BusinessException();
            throw be;
        }
        return objeto;
    }

    private static Field obtenerCampoAnotado(Object o, Class claseAnotacion) {
        Field fieldSeleccionado = null;
        for (Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true); // You might want to set modifier to public first.
            if (field.isAnnotationPresent(claseAnotacion)) {
                fieldSeleccionado = field;
                break;
            }


        }
        return fieldSeleccionado;
    }
}
