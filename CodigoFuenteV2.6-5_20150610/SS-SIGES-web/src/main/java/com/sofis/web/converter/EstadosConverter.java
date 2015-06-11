package com.sofis.web.converter;

import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.properties.Labels;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Usuario
 */
public class EstadosConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringsUtils.isEmpty(value)) {
            return null;
        }
//        Estados estado = new Estados(new Integer(value));
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        //TODO: Corregir este metodo para que no que de hardcode y use los datos de la DB.
        if (value == null) {
            return Labels.getValue("estado_NoExigido");
        }

        Integer valueInt = null;
        if (value instanceof Integer) {
            valueInt = (Integer) value;
        } else if (value instanceof String) {
            valueInt = Integer.valueOf((String) value);
        }

        if (valueInt == 2) {
            return Labels.getValue("estado_Inicio");
        }
        if (valueInt == 3) {
            return Labels.getValue("estado_Planificacion");
        }
        if (valueInt == 4) {
            return Labels.getValue("estado_Ejecucion");
        }
        if (valueInt == 5) {
            return Labels.getValue("estado_Finalizado");
        }
        if (valueInt == 6) {
            return Labels.getValue("estado_solicitud_cierre");
        }

        return "";

    }
}
