package com.sofis.entities.tipos;

import com.sofis.entities.data.Documentos;
import java.io.Serializable;
import java.util.Date;

public class AdqPagosTO implements Serializable {

	private static final long serialVersionUID = 1L;

	//Adquisiciones
	private Integer adqPk;
	private Integer prePk;
	private String adqNombre;
	private Integer orgaPk;
	private String orgaNombre;
	private Integer fuentePk;
	private String fuenteNombre;
	private Integer monPk;
	private Integer idAdquisicion;
	private String monedaSigno;
	private String procCompra;
	private String procCompraGrp;
	private Boolean tienePagos;

	private Integer orden;
	private boolean puedeSubir;
	private boolean puedeBajar;

	//Pagos
	private Integer pagPk;
	private Integer entPk;
	private String entNombre;
	private String observacion;
	private Date fechaPlan;
	private Double importePlan;
	private Date fechaReal;
	private Double importeReal;
	private Double importeSaldo;
	private Double ejecucion;
	private String referencia;
	private Boolean confirmado;
	private Documentos pagoDoc;

	//1-Adquisicion, 2-Pagos
	private Integer tipo;

	public void setEjecucion(Double ejecucion) {
		this.ejecucion = ejecucion;
	}

	public Double getEjecucion() {
		return this.ejecucion;
	}

	public Integer getAdqPk() {
		return adqPk;
	}

	public void setAdqPk(Integer adqPk) {
		this.adqPk = adqPk;
	}

	public Integer getPrePk() {
		return prePk;
	}

	public void setPrePk(Integer prePk) {
		this.prePk = prePk;
	}

	public String getAdqNombre() {
		return adqNombre;
	}

	public void setAdqNombre(String adqNombre) {
		this.adqNombre = adqNombre;
	}

	public Integer getOrgaPk() {
		return orgaPk;
	}

	public void setOrgaPk(Integer orgaPk) {
		this.orgaPk = orgaPk;
	}

	public String getOrgaNombre() {
		return orgaNombre;
	}

	public void setOrgaNombre(String orgaNombre) {
		this.orgaNombre = orgaNombre;
	}

	public void setImporteSaldo(Double importeSaldo) {
		this.importeSaldo = importeSaldo;
	}

	public Double getImporteSaldo() {
		return this.importeSaldo;
	}

	public Integer getTipo() {
		return tipo;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getFuentePk() {
		return fuentePk;
	}

	public void setFuentePk(Integer fuentePk) {
		this.fuentePk = fuentePk;
	}

	public String getFuenteNombre() {
		return fuenteNombre;
	}

	public void setFuenteNombre(String fuenteNombre) {
		this.fuenteNombre = fuenteNombre;
	}

	public Integer getMonPk() {
		return monPk;
	}

	public void setMonPk(Integer monPk) {
		this.monPk = monPk;
	}

	public String getMonedaSigno() {
		return monedaSigno;
	}

	public void setMonedaSigno(String monedaSigno) {
		this.monedaSigno = monedaSigno;
	}

	public Integer getPagPk() {
		return pagPk;
	}

	public void setPagPk(Integer pagPk) {
		this.pagPk = pagPk;
	}

	public Integer getEntPk() {
		return entPk;
	}

	public void setEntPk(Integer entPk) {
		this.entPk = entPk;
	}

	public String getEntNombre() {
		return entNombre;
	}

	public void setEntNombre(String entNombre) {
		this.entNombre = entNombre;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getFechaPlan() {
		return fechaPlan;
	}

	public void setFechaPlan(Date fechaPlan) {
		this.fechaPlan = fechaPlan;
	}

	public Double getImportePlan() {
		return importePlan;
	}

	public void setImportePlan(Double importePlan) {
		this.importePlan = importePlan;
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

	public String getProcCompra() {
		return procCompra;
	}

	public void setProcCompra(String procCompra) {
		this.procCompra = procCompra;
	}

	public String getProcCompraGrp() {
		return procCompraGrp;
	}

	public void setProcCompraGrp(String procCompraGrp) {
		this.procCompraGrp = procCompraGrp;
	}

	public Boolean getTienePagos() {
		return tienePagos;
	}

	public void setTienePagos(Boolean tienePagos) {
		this.tienePagos = tienePagos;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Boolean getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}

	public Documentos getPagoDoc() {
		return pagoDoc;
	}

	public void setPagoDoc(Documentos pagoDoc) {
		this.pagoDoc = pagoDoc;
	}

	public Integer getIdAdquisicion() {
		return idAdquisicion;
	}

	public void setIdAdquisicion(Integer idAdquisicion) {
		this.idAdquisicion = idAdquisicion;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public boolean getPuedeBajar() {
		return puedeBajar;
	}

	public void setPuedeBajar(boolean puedeBajar) {
		this.puedeBajar = puedeBajar;
	}

	public boolean getPuedeSubir() {
		return puedeSubir;
	}

	public void setPuedeSubir(boolean puedeSubir) {
		this.puedeSubir = puedeSubir;
	}

}
