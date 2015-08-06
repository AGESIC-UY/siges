package com.sofis.web.converter;

import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.properties.Labels;
import java.text.DecimalFormat;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Usuario
 */
public class DecimalFormatConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringsUtils.isEmpty(value)) {
            return null;
        }
        try {
            String valor = value.replace(",", ".");
            return Double.parseDouble(valor);
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format(Labels.getValue("error_convert_decimal"), value), null), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else if (value instanceof String) {
            return (String) value;
        } else if (value instanceof Integer || value instanceof Long
                || value instanceof Double || value instanceof Float) {
            DecimalFormat df = new DecimalFormat(ConstantesEstandares.DECIMAL_FORMAT_PATTERN);
            return df.format(value);
        } else {
            return "";
        }
    }
}
