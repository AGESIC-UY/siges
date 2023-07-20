package com.sofis.entities.tipos.wekan;

import com.sofis.entities.tipos.EntregableTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TarjetaTO implements Serializable {

	private long id;
	private String idTarjeta;
	private String idLista;

	private EntregableTO entregable;
	private VinculacionTO vinculacion;

	private Date fechaAlta;

	private List<ActividadTO> actividades;

	public TarjetaTO() {

		this.actividades = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(String idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getIdLista() {
		return idLista;
	}

	public void setIdLista(String idLista) {
		this.idLista = idLista;
	}

	public EntregableTO getEntregable() {
		return entregable;
	}

	public void setEntregable(EntregableTO entregable) {
		this.entregable = entregable;
	}

	public VinculacionTO getVinculacion() {
		return vinculacion;
	}

	public void setVinculacion(VinculacionTO vinculacion) {
		this.vinculacion = vinculacion;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public List<ActividadTO> getActividades() {
		return actividades;
	}

	public void setActividades(List<ActividadTO> actividades) {
		this.actividades = actividades;
	}

}
