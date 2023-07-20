package com.sofis.entities.tipos.wekan;

import com.sofis.entities.enums.OrigenActividad;
import java.io.Serializable;
import java.util.Date;

public class ActividadTO implements Serializable {

	private long id;
	private TarjetaTO tarjeta;
	private OrigenActividad origen;
	private String idActividad;
	private String descripcion;
	private String idUsuario;
	private String usuario;
	private String actualizacion;
	private String valorAnterior;
	private String valorNuevo;
	private Date fechaCreacion;
	private Date fechaAplicacionCambio;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TarjetaTO getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(TarjetaTO tarjeta) {
		this.tarjeta = tarjeta;
	}

	public OrigenActividad getOrigen() {
		return origen;
	}

	public void setOrigen(OrigenActividad origen) {
		this.origen = origen;
	}

	public String getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(String idActividad) {
		this.idActividad = idActividad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getActualizacion() {
		return actualizacion;
	}

	public void setActualizacion(String actualizacion) {
		this.actualizacion = actualizacion;
	}

	public String getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public String getValorNuevo() {
		return valorNuevo;
	}

	public void setValorNuevo(String valorNuevo) {
		this.valorNuevo = valorNuevo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaAplicacionCambio() {
		return fechaAplicacionCambio;
	}

	public void setFechaAplicacionCambio(Date fechaAplicacionCambio) {
		this.fechaAplicacionCambio = fechaAplicacionCambio;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ActividadTO other = (ActividadTO) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

}
