package com.sofis.web.utils;

import com.sofis.entities.data.Error;
import java.util.List;
import java.util.Properties;
import com.sofis.web.delegates.EntityManagementDelegate;

/**
 *
 * @author Usuario
 */
public class MensajesError {

    public static final Properties PROP_MENSAJES = new Properties();

    static {
        EntityManagementDelegate emd = new EntityManagementDelegate();
        List<Error> mensajes = emd.getEntities(Error.class.getName());
        //System.out.println("cantidad = " + mensajes.size());
        for (Error err : mensajes) {
           PROP_MENSAJES.put(err.getErrCodigo(), err.getErrDescripcion());
        }
    }

    public MensajesError() {
    }
}
