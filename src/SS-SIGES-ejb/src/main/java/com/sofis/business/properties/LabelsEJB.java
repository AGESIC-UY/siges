package com.sofis.business.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author Usuario
 */
public class LabelsEJB {

	private static final String LABELS = "com.sofis.generico.ejb.mensajes.Labels";

	private static ResourceBundle bundle = ResourceBundle.getBundle(LABELS);

	public static String getValue(String key) {
		if (bundle.containsKey(key)) {
			return bundle.getString(key);
		} else {
			return "?" + key + "?";
		}

	}

	public static boolean containsKey(String key) {
		return bundle.containsKey(key);
	}

	public static List<String> getValues(List<String> keys) {
		List<String> res = new ArrayList<>();
		for (String k : keys) {
			res.add(getValue(k));
		}
		return res;
	}
}
