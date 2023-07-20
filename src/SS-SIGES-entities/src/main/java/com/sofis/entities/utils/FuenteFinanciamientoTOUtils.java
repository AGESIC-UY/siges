package com.sofis.entities.utils;

import com.sofis.entities.tipos.FuenteFinanciamientoTO;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class FuenteFinanciamientoTOUtils {

	private static final Comparator<FuenteFinanciamientoTO> NOMBRE_COMPARATOR = new Comparator<FuenteFinanciamientoTO>() {
		@Override
		public int compare(FuenteFinanciamientoTO o1, FuenteFinanciamientoTO o2) {
			if (o1 != null && o1.getNombre() != null && o2 != null && o2.getNombre() != null) {
				return o1.getNombre().compareTo(o2.getNombre());
			}
			return 0;
		}
	};

	public static List<FuenteFinanciamientoTO> sortByNombre(List<FuenteFinanciamientoTO> lista) {

		if (CollectionsUtils.isNotEmpty(lista)) {
			Collections.sort(lista, NOMBRE_COMPARATOR);
		}

		return lista;
	}
}
