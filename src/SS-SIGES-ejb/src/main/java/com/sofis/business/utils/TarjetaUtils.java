package com.sofis.business.utils;

import com.sofis.entities.data.Tarjeta;
import com.sofis.entities.tipos.wekan.TarjetaTO;

public abstract class TarjetaUtils {

	public static TarjetaTO convert(Tarjeta tarjeta) {

		TarjetaTO result = new TarjetaTO();

		result.setId(tarjeta.getId());
		result.setIdTarjeta(tarjeta.getIdTarjeta());
		result.setIdLista(tarjeta.getIdLista());
		result.setFechaAlta(tarjeta.getFechaAlta());

		return result;
	}
}
