package com.sofis.web.properties;

import com.sofis.business.properties.LabelsEJB;
import com.sofis.exceptions.GeneralException;
import com.sofis.web.mb.InicioMB;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

public class Labels {

	public static String getValue(String key) {

		return LabelsEJB.getValue(key, obtenerOrganismoId());
	}

	public static List<String> getValues(List<String> key) {

		List<String> ret = new LinkedList<>();
		if (key != null && key.size() > 0) {
			for (String k : key) {
				ret.add(getValue(k));
			}
		}
		return ret;
	}

	public static boolean containsKey(String key) {
		return LabelsEJB.containsKey(key);
	}

	public static String getMessage(GeneralException ex) {
		if (ex.getErrores() != null && !ex.getErrores().isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (String s : ex.getErrores()) {
				if (s.contains("_")) {
					sb.append(Labels.getValue(s)).append("\n");
				} else {
					sb.append(s).append("\n");
				}
			}
			return sb.toString();
		} else {
			return ex.getMessage();
		}
	}

	private static Integer obtenerOrganismoId() {

		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		InicioMB inicioMB = application.evaluateExpressionGet(context, "#{inicioMB}", InicioMB.class);

		return inicioMB.getOrganismoSeleccionado();
	}
}
