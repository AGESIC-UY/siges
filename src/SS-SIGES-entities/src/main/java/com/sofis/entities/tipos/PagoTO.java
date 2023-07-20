package com.sofis.entities.tipos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PagoTO implements Serializable, Cloneable {

	private Integer id;
	private EntregableTO entregable;
	private OrganizacionTO proveedor;
	private OrganizacionTO cliente;
	private String referencia;
	private Date fechaPlanificada;
	private Double importePlanificado;
	private Date fechaReal;
	private Double importeReal;
	private Boolean confirmado;

	private Integer gasto;
	private Integer inversion;
	private Integer porcentajeContribucion;
	private String observaciones;

	private Boolean editable;
	private Boolean puedeCambiarAprobacion;

	private MonedaTO moneda;

	public PagoTO() {
	}

	public PagoTO(Integer id, String referencia, Date fechaPlanificada, Double importePlanificado,
			Date fechaReal, Double importeReal, Boolean confirmado,
			Integer idEntregable, String nombreEntregable,
			Integer idProveedor, String nombreProveedor,
			Integer idCliente, String nombreCliente) {

		this.id = id;

		this.referencia = referencia;
		this.fechaPlanificada = fechaPlanificada;
		this.importePlanificado = importePlanificado;
		this.fechaReal = fechaReal;
		this.importeReal = importeReal;
		this.confirmado = confirmado;

		if (idEntregable != null) {
			entregable = new EntregableTO(idEntregable, nombreEntregable);
		}

		if (idProveedor != null) {
			this.proveedor = new OrganizacionTO(idProveedor, nombreProveedor);
		}

		if (idCliente != null) {
			this.cliente = new OrganizacionTO(idCliente, nombreCliente);
		}

	}

	public PagoTO(Integer id, String referencia, Date fechaPlanificada, Double importePlanificado,
			Date fechaReal, Double importeReal, Boolean confirmado, Integer gasto, Integer inversion,
			Integer porcentajeContribucion, String observaciones,
			Integer idEntregable, String nombreEntregable, Integer nivelEntregable, Long fechaInicioEntregable, Long fechaFinEntregable,
			Integer idProveedor, String nombreProveedor,
			Integer idCliente, String nombreCliente,
			Integer idMoneda, String nombreMoneda, String signoMoneda) {

		this.id = id;

		this.referencia = referencia;
		this.fechaPlanificada = fechaPlanificada;
		this.importePlanificado = importePlanificado;
		this.fechaReal = fechaReal;
		this.importeReal = importeReal;
		this.confirmado = confirmado;

		this.gasto = gasto;
		this.inversion = inversion;
		this.porcentajeContribucion = porcentajeContribucion;
		this.observaciones = observaciones;

		if (idEntregable != null) {
			entregable = new EntregableTO(idEntregable, nombreEntregable);
			entregable.setNivel(nivelEntregable);
			entregable.setFechaInicio(new Date(fechaInicioEntregable));
			entregable.setFechaFin(new Date(fechaFinEntregable));
		}

		if (idProveedor != null) {
			this.proveedor = new OrganizacionTO(idProveedor, nombreProveedor);
		}

		if (idCliente != null) {
			this.cliente = new OrganizacionTO(idCliente, nombreCliente);
		}

		if (idMoneda != null) {
			this.moneda = new MonedaTO(idMoneda, nombreMoneda);
			this.moneda.setSigno(signoMoneda);
		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EntregableTO getEntregable() {
		return entregable;
	}

	public void setEntregable(EntregableTO entregable) {
		this.entregable = entregable;
	}

	public OrganizacionTO getProveedor() {
		return proveedor;
	}

	public void setProveedor(OrganizacionTO proveedor) {
		this.proveedor = proveedor;
	}

	public OrganizacionTO getCliente() {
		return cliente;
	}

	public void setCliente(OrganizacionTO cliente) {
		this.cliente = cliente;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Date getFechaPlanificada() {
		return fechaPlanificada;
	}

	public void setFechaPlanificada(Date fechaPlanificada) {
		this.fechaPlanificada = fechaPlanificada;
	}

	public Double getImportePlanificado() {
		return importePlanificado;
	}

	public void setImportePlanificado(Double importePlanificado) {
		this.importePlanificado = importePlanificado;
	}

	public Date getFechaReal() {
		return fechaReal;
	}

	public void setFechaReal(Date fechaReal) {
		this.fechaReal = fechaReal;
	}

	public Double getImporteReal() {
		return importeReal;
	}

	public void setImporteReal(Double importeReal) {
		this.importeReal = importeReal;
	}

	public Boolean getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}

	public Integer getGasto() {
		return gasto;
	}

	public void setGasto(Integer gasto) {
		this.gasto = gasto;
	}

	public Integer getInversion() {
		return inversion;
	}

	public void setInversion(Integer inversion) {
		this.inversion = inversion;
	}

	public Integer getPorcentajeContribucion() {
		return porcentajeContribucion;
	}

	public void setPorcentajeContribucion(Integer porcentajeContribucion) {
		this.porcentajeContribucion = porcentajeContribucion;
	}

	public String getObservaciones() {

		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean getPuedeCambiarAprobacion() {
		return puedeCambiarAprobacion;
	}

	public void setPuedeCambiarAprobacion(Boolean puedeCambiarAprobacion) {
		this.puedeCambiarAprobacion = puedeCambiarAprobacion;
	}

	public MonedaTO getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaTO moneda) {
		this.moneda = moneda;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
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
		final PagoTO other = (PagoTO) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
