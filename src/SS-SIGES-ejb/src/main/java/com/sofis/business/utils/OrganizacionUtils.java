package com.sofis.business.utils;

import com.sofis.entities.data.OrganiIntProve;
import com.sofis.generico.utils.generalutils.CollectionsUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class OrganizacionUtils {

	private static final Comparator<OrganiIntProve> NOMBRE_COMPARATOR = new Comparator<OrganiIntProve>() {
		@Override
		public int compare(OrganiIntProve o1, OrganiIntProve o2) {
			if (o1 != null && o1.getOrgaNombre() != null && o2 != null && o2.getOrgaNombre() != null) {
				return o1.getOrgaNombre().compareTo(o2.getOrgaNombre());
			}
			return 0;
		}
	};

	public static List<OrganiIntProve> sortByNombre(List<OrganiIntProve> lista) {

		if (CollectionsUtils.isNotEmpty(lista)) {
			Collections.sort(lista, NOMBRE_COMPARATOR);
		}

		return lista;
	}

	public static List<OrganiIntProve> filtrarHabilitadosYOrdenarPorNombre(List<OrganiIntProve> lista,
			OrganiIntProve excepcion) {

		lista = new ArrayList<>(lista);

		ListIterator<OrganiIntProve> iter = lista.listIterator();
		while (iter.hasNext()) {
			OrganiIntProve org = iter.next();

			if (!org.getOrgaHabilitado() && !org.equals(excepcion)) {
				iter.remove();
			}
		}

		return sortByNombre(lista);
	}

}
