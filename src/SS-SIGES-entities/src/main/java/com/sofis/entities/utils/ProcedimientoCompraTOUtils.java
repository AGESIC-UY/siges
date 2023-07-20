package com.sofis.entities.utils;

import com.sofis.entities.tipos.ProcedimientoCompraTO;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class ProcedimientoCompraTOUtils {

	private static final Comparator<ProcedimientoCompraTO> NOMBRE_COMPARATOR = new Comparator<ProcedimientoCompraTO>() {
		@Override
		public int compare(ProcedimientoCompraTO o1, ProcedimientoCompraTO o2) {
			if (o1 != null && o1.getNombre() != null && o2 != null && o2.getNombre() != null) {
				return o1.getNombre().compareTo(o2.getNombre());
			}
			return 0;
		}
	};

	public static List<ProcedimientoCompraTO> sortByNombre(List<ProcedimientoCompraTO> lista) {

		if (CollectionsUtils.isNotEmpty(lista)) {
			Collections.sort(lista, NOMBRE_COMPARATOR);
		}

		return lista;
	}
}
