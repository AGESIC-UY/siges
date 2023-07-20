package com.sofis.entities.utils;

import com.sofis.entities.tipos.OrganizacionTO;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class OrganizacionTOUtils {

	private static final Comparator<OrganizacionTO> NOMBRE_COMPARATOR = new Comparator<OrganizacionTO>() {
		@Override
		public int compare(OrganizacionTO o1, OrganizacionTO o2) {
			if (o1 != null && o1.getNombre() != null && o2 != null && o2.getNombre() != null) {
				return o1.getNombre().compareTo(o2.getNombre());
			}
			return 0;
		}
	};

	public static List<OrganizacionTO> sortByNombre(List<OrganizacionTO> lista) {

		if (CollectionsUtils.isNotEmpty(lista)) {
			Collections.sort(lista, NOMBRE_COMPARATOR);
		}

		return lista;
	}
}
