package com.sofis.entities.tipos;

import java.io.Serializable;

public class UsuarioTO implements Serializable {

	private Integer id;
	private String primerNombre;
	private String primerApellido;

	public UsuarioTO() {
	}

	public UsuarioTO(Integer id) {
		this.id = id;
	}

	public UsuarioTO(Integer id, String primerNombre, String primerApellido) {

		this(id);


		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getNombreApellido() {

		return (this.primerNombre != null ? this.primerNombre : "") + " " + (this.primerApellido != null ? this.primerApellido : "");
	}
}
