package com.sofis.web.validations;

import com.sofis.entities.data.Telefono;
import com.sofis.entities.data.TipoTelefono;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.presentacion.tipos.MensajePresentacion;
import com.sofis.web.utils.MensajesError;
import com.sofis.web.utils.ProcesarMensajes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ValidacionTelefono {

    public List<MensajePresentacion> validarTelefonoGeneral(Telefono t, TipoTelefono tipTel) {
        List<MensajePresentacion> respuesta = new ArrayList();
        if (tipTel == null) {
            respuesta.add(ProcesarMensajes.generarError("msgTipTel", MensajesError.PROP_MENSAJES.getProperty("NO_TIP_TEL")));
        }
        if (t != null) {
            if (StringsUtils.isEmpty(t.getTelNumero())) {
                respuesta.add(ProcesarMensajes.generarError("msgNumTel", MensajesError.PROP_MENSAJES.getProperty("NO_NUM_TEL")));
            }
        } else {
            respuesta.add(ProcesarMensajes.generarError("", MensajesError.PROP_MENSAJES.getProperty("TEL_GENERAL")));
        }
        return respuesta;
    }
}
