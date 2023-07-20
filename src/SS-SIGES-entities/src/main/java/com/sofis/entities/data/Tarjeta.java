package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

@Entity
@Table(name = "wekan_tarjeta")
@NamedQueries({
	@NamedQuery(name = "Tarjeta.findByIdTablero", query = "SELECT t FROM Tarjeta t WHERE t.vinculacion.tablero.id = :idTablero"),
	@NamedQuery(name = "Tarjeta.findByIdCronograma", query = "SELECT t FROM Tarjeta t WHERE t.vinculacion.cronograma.croPk = :idCronograma"),
	@NamedQuery(name = "Tarjeta.findByIdLista", query = "SELECT t FROM Tarjeta t WHERE t.idLista = :idLista"),
	@NamedQuery(name = "Tarjeta.findByIdTarjeta", query = "SELECT t FROM Tarjeta t WHERE t.idTarjeta = :idTarjeta"),
	@NamedQuery(name = "Tarjeta.findByIdEntregable", query = "SELECT t FROM Tarjeta t WHERE t.entregable.entPk = :idEntregable"),
})
public class Tarjeta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "id_tarjeta", nullable = false)
	private String idTarjeta;

	@Column(name = "id_lista", nullable = false)
	private String idLista;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "entregable_fk", referencedColumnName = "ent_pk", nullable = false, unique = true)
	private Entregables entregable;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "tablero_fk", referencedColumnName = "tablero_fk"), 
		@JoinColumn(name = "cronograma_fk", referencedColumnName = "cronograma_fk")})
	private Vinculacion vinculacion;

	@Temporal(DATE)
	@Column(name = "fecha_alta")
	private Date fechaAlta;

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

	public Entregables getEntregable() {
		return entregable;
	}

	public void setEntregable(Entregables entregable) {
		this.entregable = entregable;
	}

	public Vinculacion getVinculacion() {
		return vinculacion;
	}

	public void setVinculacion(Vinculacion vinculacion) {
		this.vinculacion = vinculacion;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public int hashCode() {
		int hash = 7;
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
		final Tarjeta other = (Tarjeta) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

}
