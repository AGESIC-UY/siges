package com.sofis.web.converter;

import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.properties.Labels;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Usuario
 */
public class ImporteFormatConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringsUtils.isEmpty(value)) {
            return null;
        }
        try {
            Locale locale = new Locale(ConstantesEstandares.CURRENT_LOCALE_LANGUAGE, ConstantesEstandares.CURRENT_LOCALE_COUNTRY);
            NumberFormat nf = NumberFormat.getInstance(locale);
            Double d = nf.parse(value).doubleValue();

            if ((d % 2) != 0) {
                d = (double) Math.round(d);
            }
            return d;
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format(Labels.getValue("error_convert_importe"), value), null), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || !(value instanceof Double || value instanceof Float
                || value instanceof Integer || value instanceof Long)) {
            return "";
        }

        Locale locale = new Locale(ConstantesEstandares.CURRENT_LOCALE_LANGUAGE, ConstantesEstandares.CURRENT_LOCALE_COUNTRY);
        DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        df.applyPattern(ConstantesEstandares.IMPORTE_FORMAT_PATTERN);

        return df.format(value);
    }
}
