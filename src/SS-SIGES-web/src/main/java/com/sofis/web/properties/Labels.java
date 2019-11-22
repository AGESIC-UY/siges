package com.sofis.web.properties;

import com.sofis.business.properties.LabelsEJB;
import com.sofis.exceptions.GeneralException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Labels {

	public static String getValue(String key) {
		return LabelsEJB.getValue(key);
	}

	public static List<String> getValues(List<String> key) {
		List<String> ret = new LinkedList<String>();
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

}
