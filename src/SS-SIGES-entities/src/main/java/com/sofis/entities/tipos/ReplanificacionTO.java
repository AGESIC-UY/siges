package com.sofis.entities.tipos;

import java.util.Date;

public class ReplanificacionTO {

	private Integer id;
	private String descripcion;
	private Date fecha;

	private Integer idProyecto;

	private Boolean historial;
	private Boolean activo;

	private Boolean permiteEditar;
	private Boolean generarLineaBase;
	private Boolean generarPresupuesto;
	private Boolean generarProducto;

	public ReplanificacionTO() {
	}

	public ReplanificacionTO(Integer id, String descripcion, Date fecha,
			Integer idProyecto, Boolean historial, Boolean activo, Boolean permiteEditar,
			Boolean generarLineaBase, Boolean generarPresupuesto, Boolean generarProducto) {

		this.id = id;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.idProyecto = idProyecto;
		this.historial = historial;
		this.activo = activo;
		this.permiteEditar = permiteEditar;
		this.generarLineaBase = generarLineaBase;
		this.generarPresupuesto = generarPresupuesto;
		this.generarProducto = generarProducto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getHistorial() {
		return historial;
	}

	public void setHistorial(Boolean historial) {
		this.historial = historial;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Boolean getPermiteEditar() {
		return permiteEditar;
	}

	public void setPermiteEditar(Boolean permiteEditar) {
		this.permiteEditar = permiteEditar;
	}

	public Boolean getGenerarLineaBase() {
		return generarLineaBase;
	}

	public void setGenerarLineaBase(Boolean generarLineaBase) {
		this.generarLineaBase = generarLineaBase;
	}

	public Boolean getGenerarPresupuesto() {
		return generarPresupuesto;
	}

	public void setGenerarPresupuesto(Boolean generarPresupuesto) {
		this.generarPresupuesto = generarPresupuesto;
	}

	public Boolean getGenerarProducto() {
		return generarProducto;
	}

	public void setGenerarProducto(Boolean generarProducto) {
		this.generarProducto = generarProducto;
	}

}
