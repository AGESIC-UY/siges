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

		Boolean exigido = (Boolean) component.getAttributes().get("exigido");

		if (StringsUtils.isEmpty(value)) {
			return null;
		}
		try {
			Integer id = Integer.valueOf(value);

			if (id < 0 || (Boolean.TRUE.equals(exigido) && id == 0)) {

				throw new ConverterException();
			}

			return id;

		} catch (NumberFormatException | ConverterException e) {

			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Labels.getValue("error_adquisicion_id_adquisicion_invalido"), null));
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
