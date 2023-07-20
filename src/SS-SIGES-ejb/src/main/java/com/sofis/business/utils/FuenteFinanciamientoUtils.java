package com.sofis.business.utils;

import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.generico.utils.generalutils.CollectionsUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class FuenteFinanciamientoUtils {

	private static final Comparator<FuenteFinanciamiento> NOMBRE_COMPARATOR = new Comparator<FuenteFinanciamiento>() {
		@Override
		public int compare(FuenteFinanciamiento o1, FuenteFinanciamiento o2) {
			if (o1 != null && o1.getFueNombre() != null && o2 != null && o2.getFueNombre() != null) {
				return o1.getFueNombre().compareTo(o2.getFueNombre());
			}
			return 0;
		}
	};

	public static List<FuenteFinanciamiento> sortByNombre(List<FuenteFinanciamiento> lista) {
		
		if (CollectionsUtils.isNotEmpty(lista)) {
			Collections.sort(lista, NOMBRE_COMPARATOR);
		}

		return lista;
	}

	public static List<FuenteFinanciamiento> filtrarHabilitadosYOrdenarPorNombre(List<FuenteFinanciamiento> lista,
			FuenteFinanciamiento excepcion) {

		lista = new ArrayList<>(lista);

		ListIterator<FuenteFinanciamiento> iter = lista.listIterator();
		while (iter.hasNext()) {
			FuenteFinanciamiento org = iter.next();

			if (!org.getFueHabilitada() && !org.equals(excepcion)) {
				iter.remove();
			}
		}

		return sortByNombre(lista);
	}

}
