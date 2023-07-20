package com.sofis.business.utils;

import com.sofis.entities.data.RolesInteresados;
import com.sofis.generico.utils.generalutils.CollectionsUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class RolesInteresadosUtils {

	private static final Comparator<RolesInteresados> NOMBRE_COMPARATOR = new Comparator<RolesInteresados>() {
		@Override
		public int compare(RolesInteresados o1, RolesInteresados o2) {
			if (o1 != null && o1.getRolintNombre() != null && o2 != null && o2.getRolintNombre() != null) {
				return o1.getRolintNombre().compareTo(o2.getRolintNombre());
			}
			return 0;
		}
	};

	public static List<RolesInteresados> sortByNombre(List<RolesInteresados> lista) {
		if (CollectionsUtils.isNotEmpty(lista)) {
			Collections.sort(lista, NOMBRE_COMPARATOR);
		}

		return lista;
	}

	public static List<RolesInteresados> filtrarHabilitadosYOrdenarPorNombre(List<RolesInteresados> lista, RolesInteresados excepcion) {

		lista = new ArrayList<>(lista);

		ListIterator<RolesInteresados> iter = lista.listIterator();
		while (iter.hasNext()) {
			RolesInteresados org = iter.next();

			if (!org.getHabilitado() && !org.equals(excepcion)) {
				iter.remove();
			}
		}

		return sortByNombre(lista);
	}

}
