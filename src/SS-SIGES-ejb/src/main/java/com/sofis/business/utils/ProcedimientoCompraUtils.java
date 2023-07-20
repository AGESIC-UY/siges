package com.sofis.business.utils;

import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.generico.utils.generalutils.CollectionsUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class ProcedimientoCompraUtils {

	private static final Comparator<ProcedimientoCompra> NOMBRE_COMPARATOR = new Comparator<ProcedimientoCompra>() {
		@Override
		public int compare(ProcedimientoCompra o1, ProcedimientoCompra o2) {
			if (o1 != null && o1.getProcCompNombre() != null && o2 != null && o2.getProcCompNombre() != null) {
				return o1.getProcCompNombre().compareTo(o2.getProcCompNombre());
			}
			return 0;
		}
	};

	public static List<ProcedimientoCompra> sortByNombre(List<ProcedimientoCompra> lista) {
		
		if (CollectionsUtils.isNotEmpty(lista)) {
			Collections.sort(lista, NOMBRE_COMPARATOR);
		}

		return lista;
	}

	public static List<ProcedimientoCompra> filtrarHabilitadosYOrdenarPorNombre(List<ProcedimientoCompra> lista,
			ProcedimientoCompra excepcion) {

		lista = new ArrayList<>(lista);

		ListIterator<ProcedimientoCompra> iter = lista.listIterator();
		while (iter.hasNext()) {
			ProcedimientoCompra org = iter.next();

			if (!org.getProcCompHabilitado() && !org.equals(excepcion)) {
				iter.remove();
			}
		}

		return sortByNombre(lista);
	}

}
