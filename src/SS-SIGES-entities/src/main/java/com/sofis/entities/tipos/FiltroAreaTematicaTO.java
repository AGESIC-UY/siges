package com.sofis.entities.tipos;

import java.io.Serializable;

public class FiltroAreaTematicaTO implements Serializable{
    
    private Integer id;
    private String nombre;
    private Integer idOrganismo;
    private Boolean habilitada;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getIdOrganismo() {
		return idOrganismo;
	}

	public void setIdOrganismo(Integer idOrganismo) {
		this.idOrganismo = idOrganismo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getHabilitada() {
		return habilitada;
	}

	public void setHabilitada(Boolean habilitada) {
		this.habilitada = habilitada;
	}
	
}
