package com.sofis.entities.tipos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MisAdquisicionesTO implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer cantidadProyectos;
	private Integer cantidadAdquisiciones;
	
	private List<ProyectoTO> proyectos;

	public MisAdquisicionesTO() {
		
		proyectos = new ArrayList<>();
	}
	
	public Integer getCantidadProyectos() {
		return cantidadProyectos;
	}

	public void setCantidadProyectos(Integer cantidadProyectos) {
		this.cantidadProyectos = cantidadProyectos;
	}

	public Integer getCantidadAdquisiciones() {
		return cantidadAdquisiciones;
	}

	public void setCantidadAdquisiciones(Integer cantidadAdquisiciones) {
		this.cantidadAdquisiciones = cantidadAdquisiciones;
	}

	public List<ProyectoTO> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<ProyectoTO> proyectos) {
		this.proyectos = proyectos;
	}
	
}
