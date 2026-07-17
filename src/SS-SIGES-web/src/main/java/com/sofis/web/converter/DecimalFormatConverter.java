package com.sofis.web.converter;

import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.properties.Labels;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class DecimalFormatConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (StringsUtils.isEmpty(value)) {
            return null;
        }

        try {
            // El punto es separador de miles → eliminarlo
            String normalizado = value.replace(".", "");

            // La coma es separador decimal → convertirla en punto para Double
            normalizado = normalizado.replace(",", ".");

            return Double.parseDouble(normalizado);

        } catch (Exception e) {
            throw new ConverterException(
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    String.format(Labels.getValue("error_convert_decimal"), value),
                    null),
                e
            );
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return "";
        }

        if (value instanceof String) {
            return (String) value;
        }

        if (value instanceof Number) {
            double val = ((Number) value).doubleValue();

            // Mostrar simplemente "0" cuando es 0 exacto
            if (val == 0.0) {
                return "0";
            }

            // Configurar los símbolos correctos: miles = ".", decimal = ","
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            symbols.setGroupingSeparator('.');

            DecimalFormat df = new DecimalFormat(ConstantesEstandares.DECIMAL_FORMAT_PATTERN, symbols);

            return df.format(value);
        }

        return "";
    }
}
