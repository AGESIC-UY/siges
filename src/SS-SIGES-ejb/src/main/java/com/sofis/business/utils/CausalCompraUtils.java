package com.sofis.business.utils;

import com.sofis.entities.data.CausalCompra;
import com.sofis.generico.utils.generalutils.CollectionsUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CausalCompraUtils {

	private static final Comparator<CausalCompra> NOMBRE_COMPARATOR = new Comparator<CausalCompra>() {
		@Override
		public int compare(CausalCompra o1, CausalCompra o2) {
			if (o1 != null && o1.getCauComNombre() != null && o2 != null && o2.getCauComNombre() != null) {
				return o1.getCauComNombre().compareTo(o2.getCauComNombre());
			}
			return 0;
		}
	};

	public static List<CausalCompra> sortByNombre(List<CausalCompra> lista) {
		
		if (CollectionsUtils.isNotEmpty(lista)) {
			Collections.sort(lista, NOMBRE_COMPARATOR);
		}

		return lista;
	}


}
