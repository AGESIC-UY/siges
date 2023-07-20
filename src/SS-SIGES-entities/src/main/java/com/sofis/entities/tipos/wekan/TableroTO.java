package com.sofis.entities.tipos.wekan;

import java.io.Serializable;

public class TableroTO implements Serializable {

	private long id;
	private String idTablero;
	private String urlServidor;
	private String urlTablero;
	private String token;
	private String idIntegracion;
	private String idCampoProyecto;
	private String idCampoAvance;
	private String idLista;
	private String idCarril;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdTablero() {
		return idTablero;
	}

	public void setIdTablero(String idTablero) {
		this.idTablero = idTablero;
	}

	public String getUrlServidor() {
		return urlServidor;
	}

	public void setUrlServidor(String urlServidor) {
		this.urlServidor = urlServidor;
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

}
