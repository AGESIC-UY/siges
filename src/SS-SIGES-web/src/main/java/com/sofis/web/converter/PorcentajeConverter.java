/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author bruno
 */
public class PorcentajeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringsUtils.isEmpty(value)) {
			return null;
		}
		try {
			String valor = value.replace(",", ".");
			Integer val = Integer.parseInt(valor);
			if (val < 0 || val > 100) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format(Labels.getValue("error_converter_porcentaje"), value), null), null);
			}
			return val;
		} catch (ConverterException e) {
			throw e;
		} catch (Exception e) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format(Labels.getValue("error_converter_porcentaje"), value), null), e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "";
		} else if (value instanceof String) {
			return (String) value;
		} else if (value instanceof Integer) {
			return "" + value;
		} else {
			return "";
		}
	}

}
