package com.sofis.web.mb;

import java.io.Serializable;
import java.util.HashMap;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Usuario
 */
@Named(value = "erroresMB")
@ApplicationScoped
public class ErroresMB implements Serializable {

    private static HashMap<String, String> MENSAJES_ERROR = new HashMap<String, String>();

    /**
     * Creates a new instance of ErroresMB
     */
    public ErroresMB() {
    }

    static {
        MENSAJES_ERROR.put("COD_VAC", "No se ha ingresado un código");
        MENSAJES_ERROR.put("DES_VAC", "No ha completado la descripción");
    }

    public static String obtenerDescripcion(String codigo) {
        return MENSAJES_ERROR.get(codigo);
    }
}
