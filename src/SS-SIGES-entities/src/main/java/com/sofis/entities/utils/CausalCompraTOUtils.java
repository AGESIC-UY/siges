package com.sofis.entities.utils;

import com.sofis.entities.tipos.CausalCompraTO;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class CausalCompraTOUtils {

	private static final Comparator<CausalCompraTO> NOMBRE_COMPARATOR = new Comparator<CausalCompraTO>() {
		@Override
		public int compare(CausalCompraTO o1, CausalCompraTO o2) {
			if (o1 != null && o1.getNombre() != null && o2 != null && o2.getNombre() != null) {
				return o1.getNombre().compareTo(o2.getNombre());
			}
			return 0;
		}
	};

	public static List<CausalCompraTO> sortByNombre(List<CausalCompraTO> lista) {

		if (CollectionsUtils.isNotEmpty(lista)) {
			Collections.sort(lista, NOMBRE_COMPARATOR);
		}

		return lista;
	}
}
