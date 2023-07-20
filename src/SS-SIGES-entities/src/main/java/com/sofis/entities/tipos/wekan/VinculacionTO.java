package com.sofis.entities.tipos.wekan;

import com.sofis.entities.tipos.CronogramaTO;
import java.io.Serializable;
import java.util.Date;

public class VinculacionTO implements Serializable {

	private TableroTO tablero;
	private CronogramaTO cronograma;

	private Date fechaAlta;
	private String usuarioAlta;

	public TableroTO getTablero() {
		return tablero;
	}

	public void setTablero(TableroTO tablero) {
		this.tablero = tablero;
	}

	public CronogramaTO getCronograma() {
		return cronograma;
	}

	public void setCronograma(CronogramaTO cronograma) {
		this.cronograma = cronograma;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

}
