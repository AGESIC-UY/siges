package com.sofis.web.converter;

import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.properties.Labels;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Usuario
 */
public class LocalDateTimeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringsUtils.isEmpty(value)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(ConstantesEstandares.CALENDAR_TIME_PATTERN);
            Date d = sdf.parse(value);
            return d;
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format(Labels.getValue("error_convert_fecha"), value), null), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Date) {
            return DatesUtils.toStringFormat((Date) value, ConstantesEstandares.CALENDAR_TIME_PATTERN);
        }
        return "";
    }
}
