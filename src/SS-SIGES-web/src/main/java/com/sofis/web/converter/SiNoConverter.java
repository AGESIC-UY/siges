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
public class SiNoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringsUtils.isEmpty(value)) {
            return null;
        }
        value = value.trim();
        if (Labels.getValue("si").equalsIgnoreCase(value) || Labels.getValue("sí").equalsIgnoreCase(value)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || !(value instanceof Boolean)) {
            return "";
        }
        Boolean bValue = (Boolean) value;
        return bValue.booleanValue() ? Labels.getValue("sí") : Labels.getValue("no");
    }
}
