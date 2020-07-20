package com.sofis.business.properties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class LabelsEJB {

	private static final String LABELS = "com.sofis.generico.ejb.mensajes.Labels";

	private static final ResourceBundle LABELS_BUNDLE = ResourceBundle.getBundle(LABELS);

	private static final Map<Integer, Map<String, String>> ETIQUETAS = new HashMap<>();

	public static String getValue(String key) {

		if (LABELS_BUNDLE.containsKey(key)) {
			return LABELS_BUNDLE.getString(key);
		} else {
			return "?" + key + "?";
		}
	}

	public static boolean containsKey(String key) {

		return LABELS_BUNDLE.containsKey(key);
	}

	public static List<String> getValues(List<String> keys) {

		List<String> res = new ArrayList<>();
		for (String k : keys) {
			res.add(getValue(k));
		}
		return res;
	}

	public static boolean containsKey(String key, Integer orgaPk) {

		return orgaPk != null && ETIQUETAS.containsKey(orgaPk)
				&& ETIQUETAS.get(orgaPk).containsKey(key);
	}

	public static synchronized String getValue(String key, Integer orgaPk) {

		if (orgaPk == null || !ETIQUETAS.containsKey(orgaPk)) {

			return getValue(key);
		}

		Map<String, String> etiquetasOrganismo = ETIQUETAS.get(orgaPk);

		return etiquetasOrganismo.containsKey(key) ? etiquetasOrganismo.get(key) : getValue(key);
	}

	public static synchronized void setEtiquetasOrganismo(Map<String, String> etiquetasOrg, Integer orgPk) {

		ETIQUETAS.put(orgPk, etiquetasOrg);
	}
}
