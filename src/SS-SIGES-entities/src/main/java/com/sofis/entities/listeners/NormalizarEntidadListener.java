/*
 *  Nombre del cliente
 *  Sistema de Gestión
 *  Desarrollado por Sofis Solutions
 */
package com.sofis.entities.listeners;

import com.sofis.entities.annotations.AtributoNormalizable;
import java.util.logging.Level;
import java.lang.reflect.Field;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class NormalizarEntidadListener {
    
    public static String normalizarString(Object s) {
        if (s != null && (s instanceof String)) {
            return ((String) s).replaceAll("[ ]+", " ").trim();
        }
        return null;
    }

    public static <T> T normalizar(T o) {
        for (Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true); // You might want to set modifier to public first.
            if (field.isAnnotationPresent(AtributoNormalizable.class)) {
                String s;
                try {
                    s =  normalizarString(field.get(o));
                    field.set(o, s);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(NormalizarEntidadListener.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(NormalizarEntidadListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                //break;
            }
        }
        return o;
    }
}
