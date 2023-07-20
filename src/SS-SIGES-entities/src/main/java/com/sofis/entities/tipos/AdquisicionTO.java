package com.sofis.entities.tipos;

import com.sofis.entities.enums.TipoRegistroCompra;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AdquisicionTO implements Serializable, Cloneable {

	private Integer id;
	private String nombre;
	private Integer idAdquisicion;
	private OrganizacionTO proveedor;
	private ProcedimientoCompraTO procedimientoCompra;
	private FuenteFinanciamientoTO fuenteFinanciamiento;
	private CausalCompraTO causalCompra;
	private Double importePlanificado;
	private Double importeReal;
	private MonedaTO moneda;
	private Double saldo;

	private IdentificadorGrpErpTO identificadorGrpErp;
	private ComponenteProductoTO componenteProducto;
	private Boolean compartida;
	private UsuarioTO responsable;
	private TipoRegistroCompra tipoRegistro;
	private Boolean arrastre;

	private Date fechaEstimadaInicioCompra;
	private Date fechaEsperadaInicioEjecucion;
	private TipoAdquisicionTO tipo;
	private CentroCostoTO centroCosto;

	private Long cantidadPagos;

	private List<PagoTO> pagos;

	private boolean editable;

	public AdquisicionTO() {

		pagos = new ArrayList<>();
	}

	public AdquisicionTO(Integer id, String nombre, Integer idAdquisicion,
			Integer idProveedor, String nombreProveedor,
			Integer idProcedimientoCompra, String nombreProcedimientoCompra,
			Integer idFuenteFinanciamiento, String nombreFuenteFinanciamiento,
			Integer idCausalCompra, String nombreCausalCompra,
			Integer idMoneda, String nombreMoneda, String signoMoneda,
			Long cantidadPagos) {

		this();
		this.id = id;
		this.nombre = nombre;
		this.idAdquisicion = idAdquisicion;

		if (idProveedor != null) {
			this.proveedor = new OrganizacionTO(idProveedor, nombreProveedor);
		}

		if (idProcedimientoCompra != null) {
			this.procedimientoCompra = new ProcedimientoCompraTO(idProcedimientoCompra, nombreProcedimientoCompra);
		}

		if (idFuenteFinanciamiento != null) {
			this.fuenteFinanciamiento = new FuenteFinanciamientoTO(idFuenteFinanciamiento, nombreFuenteFinanciamiento);
		}

		if (idCausalCompra != null) {
			this.causalCompra = new CausalCompraTO(idCausalCompra, nombreCausalCompra);
		}

		if (idMoneda != null) {
			this.moneda = new MonedaTO(idMoneda, nombreMoneda);
			this.moneda.setSigno(signoMoneda);
		}

		this.cantidadPagos = cantidadPagos;
	}

	public AdquisicionTO(Integer id, String nombre, Integer idAdquisicion,
			Boolean compartida, TipoRegistroCompra tipoRegistro, Boolean arrastre,
			Date fechaEstimadaInicioCompra, Date fechaEsperadaInicioEjecucion,
			Integer idProveedor, String nombreProveedor,
			Integer idProcedimientoCompra, String nombreProcedimientoCompra,
			Integer idFuenteFinanciamiento, String nombreFuenteFinanciamiento,
			Integer idCausalCompra, String nombreCausalCompra,
			Integer idMoneda, String nombreMoneda, String signoMoneda,
			Integer idIdentificadorGrpErp, String nombreIdentificadorGrpErp,
			Integer idComponenteProducto, String nombreComponenteProducto,
			Integer idResponsable, String nombreResponsable, String apellidoResponsable,
			Integer idTipo, String nombreTipo,
			Integer idCentroCosto, String nombreCentroCosto) {

		this();
		this.id = id;
		this.nombre = nombre;
		this.idAdquisicion = idAdquisicion;

		this.compartida = compartida;
		this.tipoRegistro = tipoRegistro;
		this.arrastre = arrastre;
		this.fechaEstimadaInicioCompra = fechaEstimadaInicioCompra;
		this.fechaEsperadaInicioEjecucion = fechaEsperadaInicioEjecucion;

		if (idProveedor != null) {
			this.proveedor = new OrganizacionTO(idProveedor, nombreProveedor);
		}

		if (idProcedimientoCompra != null) {
			this.procedimientoCompra = new ProcedimientoCompraTO(idProcedimientoCompra, nombreProcedimientoCompra);
		}

		if (idFuenteFinanciamiento != null) {
			this.fuenteFinanciamiento = new FuenteFinanciamientoTO(idFuenteFinanciamiento, nombreFuenteFinanciamiento);
		}

		if (idCausalCompra != null) {
			this.causalCompra = new CausalCompraTO(idCausalCompra, nombreCausalCompra);
		}

		if (idMoneda != null) {
			this.moneda = new MonedaTO(idMoneda, nombreMoneda);
			this.moneda.setSigno(signoMoneda);
		}

		if (idIdentificadorGrpErp != null) {
			this.identificadorGrpErp = new IdentificadorGrpErpTO(idIdentificadorGrpErp, nombreIdentificadorGrpErp);
		}

		if (idComponenteProducto != null) {
			this.componenteProducto = new ComponenteProductoTO(idComponenteProducto, nombreComponenteProducto);
		}

		if (idResponsable != null) {
			this.responsable = new UsuarioTO(idResponsable, nombreResponsable, apellidoResponsable);
		}

		if (idTipo != null) {
			this.tipo = new TipoAdquisicionTO(idTipo, nombreTipo);
		}

		if (idCentroCosto != null) {
			this.centroCosto = new CentroCostoTO(idCentroCosto, nombreCentroCosto);
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdAdquisicion() {
		return idAdquisicion;
	}

	public void setIdAdquisicion(Integer idAdquisicion) {
		this.idAdquisicion = idAdquisicion;
	}

	public OrganizacionTO getProveedor() {
		return proveedor;
	}

	public void setProveedor(OrganizacionTO proveedor) {
		this.proveedor = proveedor;
	}

	public ProcedimientoCompraTO getProcedimientoCompra() {
		return procedimientoCompra;
	}

	public void setProcedimientoCompra(ProcedimientoCompraTO procedimientoCompra) {
		this.procedimientoCompra = procedimientoCompra;
	}

	public FuenteFinanciamientoTO getFuenteFinanciamiento() {
		return fuenteFinanciamiento;
	}

	public void setFuenteFinanciamiento(FuenteFinanciamientoTO fuenteFinanciamiento) {
		this.fuenteFinanciamiento = fuenteFinanciamiento;
	}

	public CausalCompraTO getCausalCompra() {
		return causalCompra;
	}

	public void setCausalCompra(CausalCompraTO causalCompra) {
		this.causalCompra = causalCompra;
	}

	public Double getImportePlanificado() {
		return importePlanificado;
	}

	public void setImportePlanificado(Double importePlanificado) {
		this.importePlanificado = importePlanificado;
	}

	public Double getImporteReal() {
		return importeReal;
	}

	public void setImporteReal(Double importeReal) {
		this.importeReal = importeReal;
	}

	public MonedaTO getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaTO moneda) {
		this.moneda = moneda;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public IdentificadorGrpErpTO getIdentificadorGrpErp() {
		return identificadorGrpErp;
	}

	public void setIdentificadorGrpErp(IdentificadorGrpErpTO identificadorGrpErp) {
		this.identificadorGrpErp = identificadorGrpErp;
	}

	public ComponenteProductoTO getComponenteProducto() {
		return componenteProducto;
	}

	public void setComponenteProducto(ComponenteProductoTO componenteProducto) {
		this.componenteProducto = componenteProducto;
	}

	public Boolean getCompartida() {
		return compartida;
	}

	public void setCompartida(Boolean compartida) {
		this.compartida = compartida;
	}

	public UsuarioTO getResponsable() {
		return responsable;
	}

	public void setResponsable(UsuarioTO responsable) {
		this.responsable = responsable;
	}

	public TipoRegistroCompra getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(TipoRegistroCompra tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public Boolean getArrastre() {
		return arrastre;
	}

	public void setArrastre(Boolean arrastre) {
		this.arrastre = arrastre;
	}

	public Date getFechaEstimadaInicioCompra() {
		return fechaEstimadaInicioCompra;
	}

	public void setFechaEstimadaInicioCompra(Date fechaEstimadaInicioCompra) {
		this.fechaEstimadaInicioCompra = fechaEstimadaInicioCompra;
	}

	public Date getFechaEsperadaInicioEjecucion() {
		return fechaEsperadaInicioEjecucion;
	}

	public void setFechaEsperadaInicioEjecucion(Date fechaEsperadaInicioEjecucion) {
		this.fechaEsperadaInicioEjecucion = fechaEsperadaInicioEjecucion;
	}

	public TipoAdquisicionTO getTipo() {
		return tipo;
	}

	public void setTipo(TipoAdquisicionTO tipo) {
		this.tipo = tipo;
	}

	public CentroCostoTO getCentroCosto() {
		return centroCosto;
	}

	public void setCentroCosto(CentroCostoTO centroCosto) {
		this.centroCosto = centroCosto;
	}

	public Long getCantidadPagos() {
		return cantidadPagos;
	}

	public void setCantidadPagos(Long cantidadPagos) {
		this.cantidadPagos = cantidadPagos;
	}

	public List<PagoTO> getPagos() {
		return pagos;
	}

	public void setPagos(List<PagoTO> pagos) {
		this.pagos = pagos;
	}

	public boolean getEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
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
		final AdquisicionTO other = (AdquisicionTO) obj;
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
