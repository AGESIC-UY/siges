package com.sofis.business.utils;

import com.sofis.entities.data.Actividad;
import com.sofis.entities.tipos.wekan.ActividadTO;

public abstract class ActividadUtils {

	public static ActividadTO convert(Actividad actividad) {

		ActividadTO result = new ActividadTO();
		result.setId(actividad.getId());
		result.setOrigen(actividad.getOrigen());
		result.setIdActividad(actividad.getIdActividad());
		result.setDescripcion(actividad.getDescripcion());
		result.setIdUsuario(actividad.getIdUsuario());
		result.setUsuario(actividad.getUsuario());
		result.setActualizacion(actividad.getActualizacion());
		result.setValorAnterior(actividad.getValorAnterior());
		result.setValorNuevo(actividad.getValorNuevo());
		result.setFechaCreacion(actividad.getFechaCreacion());
		result.setFechaAplicacionCambio(actividad.getFechaAplicacionCambio());

		return result;
	}
}
