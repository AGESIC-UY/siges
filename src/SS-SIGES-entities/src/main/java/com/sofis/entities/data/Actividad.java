package com.sofis.entities.data;

import com.sofis.entities.enums.OrigenActividad;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "wekan_actividad")
public class Actividad implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "tarjeta_fk", referencedColumnName = "id")
	private Tarjeta tarjeta;

	@Enumerated(EnumType.STRING)
	@Column(name = "origen", nullable = false)
	private OrigenActividad origen;

	@Column(name = "id_actividad", nullable = false)
	private String idActividad;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "id_usuario", nullable = false)
	private String idUsuario;

	@Column(name = "usuario", nullable = false)
	private String usuario;

	@Column(name = "actualizacion")
	private String actualizacion;

	@Column(name = "valor_anterior")
	private String valorAnterior;

	@Column(name = "valor_nuevo")
	private String valorNuevo;

	@Temporal(TIMESTAMP)
	@Column(name = "fecha_creacion", nullable = false)
	private Date fechaCreacion;

	@Temporal(TIMESTAMP)
	@Column(name = "fecha_aplicacion_cambio", nullable = false)
	private Date fechaAplicacionCambio;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
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
		int hash = 5;
		hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
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
		final Actividad other = (Actividad) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

}
