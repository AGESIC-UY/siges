package com.sofis.entities.tipos;

import com.sofis.entities.data.Areas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultadoInicioTO implements Serializable {

	private Areas area;
	private ArrayList<Map.Entry<ItemInicioTO, List>> primerNivel = null;

	private int cantidadProyectos;
	private int cantidadProgramas;

	public Areas getArea() {
		return area;
	}

	public void setArea(Areas area) {
		this.area = area;
	}

	public ArrayList<Map.Entry<ItemInicioTO, List>> getPrimerNivel() {
		return primerNivel;
	}

	public void setPrimerNivel(ArrayList<Map.Entry<ItemInicioTO, List>> primerNivel) {
		this.primerNivel = primerNivel;
	}

	public int getCantidadProgramas() {
		return cantidadProgramas;
	}

	public void setCantidadProgramas(int cantidadProgramas) {
		this.cantidadProgramas = cantidadProgramas;
	}

	public int getCantidadProyectos() {
		return cantidadProyectos;
	}

	public void setCantidadProyectos(int cantidadProyectos) {
		this.cantidadProyectos = cantidadProyectos;
	}

}
