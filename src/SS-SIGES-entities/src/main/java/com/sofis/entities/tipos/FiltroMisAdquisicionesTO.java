package com.sofis.entities.tipos;

import com.sofis.entities.data.Areas;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.entities.data.SsUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FiltroMisAdquisicionesTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombreProyectoPrograma;
	private SsUsuario gerenteAdjunto;
	private SsUsuario pmof;
	private List<Integer> estados;
	private Areas area;
	private OrganiIntProve proveedor;
	private FuenteFinanciamiento fuenteFinanciamiento;
	private Moneda moneda;
	private ProcedimientoCompra procedimientoCompra;
	private Integer idAdquisicion;
	private Organismos organismo;

	public FiltroMisAdquisicionesTO() {
		estados = new ArrayList<>();
	}

	public String getNombreProyectoPrograma() {
		return nombreProyectoPrograma;
	}

	public void setNombreProyectoPrograma(String nombreProyectoPrograma) {
		this.nombreProyectoPrograma = nombreProyectoPrograma;
	}

	public SsUsuario getGerenteAdjunto() {
		return gerenteAdjunto;
	}

	public void setGerenteAdjunto(SsUsuario gerenteAdjunto) {
		this.gerenteAdjunto = gerenteAdjunto;
	}

	public SsUsuario getPmof() {
		return pmof;
	}

	public void setPmof(SsUsuario pmof) {
		this.pmof = pmof;
	}

	public List<Integer> getEstados() {
		return estados;
	}

	public void setEstados(List<Integer> estados) {
		this.estados = estados;
	}

	public OrganiIntProve getProveedor() {
		return proveedor;
	}

	public void setProveedor(OrganiIntProve proveedor) {
		this.proveedor = proveedor;
	}

	public FuenteFinanciamiento getFuenteFinanciamiento() {
		return fuenteFinanciamiento;
	}

	public void setFuenteFinanciamiento(FuenteFinanciamiento fuenteFinanciamiento) {
		this.fuenteFinanciamiento = fuenteFinanciamiento;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public ProcedimientoCompra getProcedimientoCompra() {
		return procedimientoCompra;
	}

	public void setProcedimientoCompra(ProcedimientoCompra procedimientoCompra) {
		this.procedimientoCompra = procedimientoCompra;
	}

	public Integer getIdAdquisicion() {
		return idAdquisicion;
	}

	public void setIdAdquisicion(Integer idAdquisicion) {
		this.idAdquisicion = idAdquisicion;
	}

	public Areas getArea() {
		return area;
	}

	public void setArea(Areas area) {
		this.area = area;
	}

	public Organismos getOrganismo() {
		return organismo;
	}

	public void setOrganismo(Organismos organismo) {
		this.organismo = organismo;
	}

	@Override
	public String toString() {
		return "FiltroMisAdquisicionesTO{" + "nombreProyectoPrograma=" + nombreProyectoPrograma + ", gerenteAdjunto=" + gerenteAdjunto + ", pmof=" + pmof + ", estados=" + estados + ", area=" + area + ", proveedor=" + proveedor + ", fuenteFinanciamiento=" + fuenteFinanciamiento + ", moneda=" + moneda + ", procedimientoCompra=" + procedimientoCompra + ", idAdquisicion=" + idAdquisicion + ", organismo=" + organismo + '}';
	}

}
