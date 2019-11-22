package com.sofis.web.converter;

import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.properties.Labels;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class IdAdquisicionFormatConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringsUtils.isEmpty(value)) {
            return null;
        }
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {

            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    Labels.getValue("error_adquisicion_id_adquisicion_vacio"), null));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if (value instanceof Integer) {
            return ((Integer) value).toString();
        } 
        
        return null;
    }
}