package com.sofis.web.utils;

import com.sofis.entities.tipos.PagoTO;
import com.sofis.generico.utils.generalutils.DatesUtils;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class PagoUtils {

	private static final String AMARILLO = "#FDC634";
	private static final String ROJO = "#FF0000";

	public static String obtenerColorFechaReal(PagoTO pago) {

		String result = "";

		if (pago.getConfirmado()) {

			return result;
		}

		Calendar cal5 = new GregorianCalendar();
		cal5.add(Calendar.DATE, 5);
		Calendar calNow = new GregorianCalendar();

		if (DatesUtils.esMayor(calNow.getTime(), pago.getFechaReal())) {

			result = ROJO; //Rojo
		} else if (DatesUtils.esMayor(cal5.getTime(), pago.getFechaReal())
				|| DatesUtils.fechasIguales(cal5.getTime(), pago.getFechaReal())) {

			result = AMARILLO; //Amarillo
		}

		return result;
	}
}
