package com.sofis.business.utils;

import com.sofis.entities.data.Vinculacion;
import com.sofis.entities.tipos.wekan.VinculacionTO;


public abstract class VinculacionUtils {

	public static VinculacionTO convert(Vinculacion vinculacion) {
		
		VinculacionTO result = new VinculacionTO();
		
		result.setFechaAlta(vinculacion.getFechaAlta());
		result.setUsuarioAlta(vinculacion.getUsuarioAlta());
		
		return result;
	}
}
