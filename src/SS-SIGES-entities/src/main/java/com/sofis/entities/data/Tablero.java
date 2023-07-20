package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wekan_tablero")
public class Tablero implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "url_servidor", nullable = false)
	private String urlServidor;

	@Column(name = "id_tablero", nullable = false, unique = true)
	private String idTablero;

	@Column(name = "url_tablero", nullable = false, unique = true)
	private String urlTablero;

	@Column(name = "token")
	private String token;

	@Column(name = "id_integracion")
	private String idIntegracion;

	@Column(name = "id_campo_proyecto")
	private String idCampoProyecto;

	@Column(name = "id_campo_avance")
	private String idCampoAvance;
        
	@Column(name = "id_lista")
	private String idLista;

	@Column(name = "id_carril")
	private String idCarril;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrlServidor() {
		return urlServidor;
	}

	public void setUrlServidor(String urlServidor) {
		this.urlServidor = urlServidor;
	}

	public String getIdTablero() {
		return idTablero;
	}

	public void setIdTablero(String idTablero) {
		this.idTablero = idTablero;
	}

	public String getUrlTablero() {
		return urlTablero;
	}

	public void setUrlTablero(String urlTablero) {
		this.urlTablero = urlTablero;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIdIntegracion() {
		return idIntegracion;
	}

	public void setIdIntegracion(String idIntegracion) {
		this.idIntegracion = idIntegracion;
	}

	public String getIdCampoProyecto() {
		return idCampoProyecto;
	}

	public void setIdCampoProyecto(String idCampoProyecto) {
		this.idCampoProyecto = idCampoProyecto;
	}

	public String getIdCampoAvance() {
		return idCampoAvance;
	}

	public void setIdCampoAvance(String idCampoAvance) {
		this.idCampoAvance = idCampoAvance;
	}
  
	public String getIdLista() {
		return idLista;
	}

	public void setIdLista(String idLista) {
		this.idLista = idLista;
	}

	public String getIdCarril() {
		return idCarril;
	}

	public void setIdCarril(String idCarril) {
		this.idCarril = idCarril;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
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
		final Tablero other = (Tablero) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

}
