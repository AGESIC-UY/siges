package com.sofis.web.validations;

import com.sofis.entities.data.Departamento;
import com.sofis.entities.data.Domicilio;
import com.sofis.entities.data.Localidad;
import com.sofis.entities.data.TipoEntradaColectiva;
import com.sofis.entities.data.TipoVialidad;
import com.sofis.web.utils.MensajesError;
import com.sofis.web.presentacion.tipos.MensajePresentacion;
import com.sofis.web.utils.ProcesarMensajes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ValidacionDomicilio {

    public List<MensajePresentacion> validar(Domicilio dom, Departamento deptoSeleccionado, Localidad localidadSeleccionada,
            TipoVialidad tipoVialidadSeleccionada, TipoEntradaColectiva tipoEntradaSeleccionada) {
        List<MensajePresentacion> respuesta = new ArrayList();
        if (deptoSeleccionado==null) {
            respuesta.add(ProcesarMensajes.generarError("msgDepto",MensajesError.PROP_MENSAJES.getProperty("NO_DPTO")));
        }
        if (localidadSeleccionada==null) {
            respuesta.add(ProcesarMensajes.generarError("msgDepto",MensajesError.PROP_MENSAJES.getProperty("NO_LOCALIDAD")));
        }
        if (tipoVialidadSeleccionada==null) {
            respuesta.add(ProcesarMensajes.generarError("msgDepto",MensajesError.PROP_MENSAJES.getProperty("NO_TIPO_VIALIDAD")));
        }
        if (tipoEntradaSeleccionada==null) {
            respuesta.add(ProcesarMensajes.generarError("msgDepto",MensajesError.PROP_MENSAJES.getProperty("NO_TIPO_ENTIDAD_COL")));
        }
        return respuesta;
    }
}
