package com.sofis.web.converter;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.properties.Labels;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Usuario
 */
public class IntegerFormatConverter implements Converter {

    private static final Logger logger = Logger.getLogger(IntegerFormatConverter.class.getName());
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringsUtils.isEmpty(value)) {
            return null;
        }
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format(Labels.getValue("error_convert_number"), value), null), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else if (value instanceof Integer) {
            return ((Integer) value).toString();
        } else if (value instanceof Double) {
            long l = Math.round((Double) value);
            try {
                return String.valueOf((int) l);
            } catch (Exception e) {
                logger.log(Level.WARNING, null, e);
            }
        } else if (value instanceof String) {
            return (String) value;
        }
        return "";
    }
}