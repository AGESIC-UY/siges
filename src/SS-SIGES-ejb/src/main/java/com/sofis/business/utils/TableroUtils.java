package com.sofis.business.utils;

import com.sofis.entities.data.Tablero;
import com.sofis.entities.tipos.wekan.TableroTO;


public abstract class TableroUtils {

	public static TableroTO convert(Tablero tablero) {
		
		TableroTO result = new TableroTO();
		result.setId(tablero.getId());
		result.setUrlServidor(tablero.getUrlServidor());
		result.setIdTablero(tablero.getIdTablero());
		result.setUrlTablero(tablero.getUrlTablero());
		result.setToken(tablero.getToken());
		result.setIdIntegracion(tablero.getIdIntegracion());
		result.setIdCampoProyecto(tablero.getIdCampoProyecto());
		result.setIdCampoAvance(tablero.getIdCampoAvance());
		result.setIdCarril(tablero.getIdCarril());
		result.setIdLista(result.getIdLista());
		
		return result;
	}
}
