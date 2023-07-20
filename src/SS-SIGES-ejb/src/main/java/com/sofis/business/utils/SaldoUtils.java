package com.sofis.business.utils;

import com.sofis.entities.tipos.SaldoTO;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class SaldoUtils {

	private static final Comparator<SaldoTO> MONEDA_COMPARATOR = new Comparator<SaldoTO>() {
		@Override
		public int compare(SaldoTO o1, SaldoTO o2) {

			if (o1.getMoneda() != null && o2.getMoneda() != null) {
				return o1.getMoneda().getId().compareTo(o2.getMoneda().getId());
			}

			return 0;
		}
	};

	public static void ordenarPorMoneda(List<SaldoTO> saldos) {
		
		Collections.sort(saldos, MONEDA_COMPARATOR);
	}
}
