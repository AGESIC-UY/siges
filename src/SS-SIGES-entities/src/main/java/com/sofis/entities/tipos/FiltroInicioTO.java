package com.sofis.entities.tipos;

import com.sofis.entities.data.Ambito;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.ObjetivoEstrategico;
import com.sofis.entities.data.OrganiIntProve;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FiltroInicioTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer organismoId;
	private Integer fichaFk;
	private Integer tipoFicha;
	private Integer progPk;
	private Integer nivel;
	private List<AreasTags> areasTematicas = new ArrayList();
	private String areatagCategoria;
	private Boolean porArea;
	private Areas areasOrganizacion;
	private String nombre;

	private OrganiIntProve interesadoOrganizacion;
	private OrganiIntProve orgaProveedor;
	private FuenteFinanciamiento fuenteFinanciamiento;
	private Integer calidadIndice;
	private Integer interesadoRol;
	private Ambito interesadoAmbitoOrganizacion;
	private String interesadoNombre;
	private List<Object> estados = new ArrayList();
	private Integer estado;
	private Integer gerenteOAdjunto;
	private Integer pmoFederada;
	private Integer sponsor;
	private Integer anioDesde;
	private Integer anioHasta;
	private List<Object> gradoRiesgo = new ArrayList();
	private Integer cantidadRiesgosAltos;
	private List<Object> indicadorAvance;
	private Boolean activo;
	private Integer codigo;
	private Date fechaAct;
	private ObjetivoEstrategico objEst;

	private Integer fechaActColor;
        
        private Integer colorActSem;

	private String orderBy;

//    @XmlJavaTypeAdapter(MapAdapter.class)
	private Map<String, Configuracion> configuracion;

	@XmlTransient
	private HashMap<Integer, Object> proyectosIdsMap;
	@XmlTransient
	private HashMap<Integer, Object> programasIdsMap;

	@XmlAnyElement(lax = true)
	private List<Object> anything;

	public FiltroInicioTO() {
	}

	public Integer getFichaFk() {
		return fichaFk;
	}

	public void setFichaFk(Integer fichaFk) {
		this.fichaFk = fichaFk;
	}

	public Integer getTipoFicha() {
		return tipoFicha;
	}

	public void setTipoFicha(Integer tipoFicha) {
		this.tipoFicha = tipoFicha;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getAreatagCategoria() {
		return areatagCategoria;
	}

	public void setAreatagCategoria(String areatagCategoria) {
		this.areatagCategoria = areatagCategoria;
	}

	public Boolean getPorArea() {
		return porArea;
	}

	public void setPorArea(Boolean porArea) {
		this.porArea = porArea;
	}

	public List<AreasTags> getAreasTematicas() {
		return areasTematicas;
	}

	public void setAreasTematicas(List<AreasTags> areasTematicas) {
		this.areasTematicas = areasTematicas;
	}

	public Areas getAreasOrganizacion() {
		return areasOrganizacion;
	}

	public void setAreasOrganizacion(Areas areasOrganizacion) {
		this.areasOrganizacion = areasOrganizacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public OrganiIntProve getInteresadoOrganizacion() {
		return interesadoOrganizacion;
	}

	public void setInteresadoOrganizacion(OrganiIntProve interesadoOrganizacion) {
		this.interesadoOrganizacion = interesadoOrganizacion;
	}

	public OrganiIntProve getOrgaProveedor() {
		return orgaProveedor;
	}

	public void setOrgaProveedor(OrganiIntProve orgaProveedor) {
		this.orgaProveedor = orgaProveedor;
	}

	public FuenteFinanciamiento getFuenteFinanciamiento() {
		return fuenteFinanciamiento;
	}

	public void setFuenteFinanciamiento(FuenteFinanciamiento fuenteFinanciamiento) {
		this.fuenteFinanciamiento = fuenteFinanciamiento;
	}

	public Integer getCalidadIndice() {
		return calidadIndice;
	}

	public void setCalidadIndice(Integer calidadIndice) {
		this.calidadIndice = calidadIndice;
	}

	public Integer getInteresadoRol() {
		return interesadoRol;
	}

	public void setInteresadoRol(Integer interesadoRol) {
		this.interesadoRol = interesadoRol;
	}

	public String getInteresadoNombre() {
		return interesadoNombre;
	}

	public void setInteresadoNombre(String interesadoNombre) {
		this.interesadoNombre = interesadoNombre;
	}

	public Integer getGerenteOAdjunto() {
		return gerenteOAdjunto;
	}

	public void setGerenteOAdjunto(Integer gerenteOAdjunto) {
		this.gerenteOAdjunto = gerenteOAdjunto;
	}

	public Integer getPmoFederada() {
		return pmoFederada;
	}

	public void setPmoFederada(Integer pmoFederada) {
		this.pmoFederada = pmoFederada;
	}

	public Integer getSponsor() {
		return sponsor;
	}

	public void setSponsor(Integer sponsor) {
		this.sponsor = sponsor;
	}

	public Integer getAnioDesde() {
		return anioDesde;
	}

	public void setAnioDesde(Integer anioDesde) {
		this.anioDesde = anioDesde;
	}

	public Integer getAnioHasta() {
		return anioHasta;
	}

	public void setAnioHasta(Integer anioHasta) {
		this.anioHasta = anioHasta;
	}

	public List<Object> getEstados() {
		return estados;
	}

	public void setEstados(List<Object> estados) {
		this.estados = estados;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public List<Object> getGradoRiesgo() {
		return gradoRiesgo;
	}

	public void setGradoRiesgo(List<Object> gradoRiesgo) {
		this.gradoRiesgo = gradoRiesgo;
	}

	public Integer getCantidadRiesgosAltos() {
		return cantidadRiesgosAltos;
	}

	public void setCantidadRiesgosAltos(Integer cantidadRiesgosAltos) {
		this.cantidadRiesgosAltos = cantidadRiesgosAltos;
	}

	public List<Object> getIndicadorAvance() {
		return indicadorAvance;
	}

	public Map<String, Configuracion> getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(Map<String, Configuracion> configuracion) {
		this.configuracion = configuracion;
	}

	public Date getFechaDesde() {
		if (anioDesde != null && anioDesde > 0) {
			Calendar c = new GregorianCalendar(anioDesde, 0, 1);
			return c.getTime();
		}
		return null;
	}

	public Date getFechaHasta() {
		if (anioHasta != null && anioHasta > 0) {
			Calendar c = new GregorianCalendar(anioHasta, 11, 31);
			return c.getTime();
		}
		return null;
	}

	public Boolean getAvanceParcial() {
		if (indicadorAvance == null || indicadorAvance.isEmpty()) {
			return false;
		}

		for (Object o : indicadorAvance) {
			if (o.toString().equalsIgnoreCase("2")) {
				return true;
			}
		}

		return false;
	}

	public Boolean getEntregableFinalizado() {
		if (indicadorAvance == null || indicadorAvance.isEmpty()) {
			return false;
		}

		for (Object o : indicadorAvance) {
			if (o.toString().equalsIgnoreCase("1")) {
				return true;
			}
		}

		return false;
	}

	public void setIndicadorAvance(List<Object> indicadorAvance) {
		this.indicadorAvance = indicadorAvance;
	}

	public Ambito getInteresadoAmbitoOrganizacion() {
		return interesadoAmbitoOrganizacion;
	}

	public void setInteresadoAmbitoOrganizacion(Ambito interesadoAmbitoOrganizacion) {
		this.interesadoAmbitoOrganizacion = interesadoAmbitoOrganizacion;
	}

	public Integer getOrganismoId() {
		return organismoId;
	}

	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
	}

	public Integer getProgPk() {
		return progPk;
	}

	public void setProgPk(Integer progPk) {
		this.progPk = progPk;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the anything
	 */
	public List<Object> getAnything() {
		return anything;
	}

	/**
	 * @param anything the anything to set
	 */
	public void setAnything(List<Object> anything) {
		this.anything = anything;
	}

	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Date getFechaAct() {
		return fechaAct;
	}

	public void setFechaAct(Date fechaAct) {
		this.fechaAct = fechaAct;
	}

	public ObjetivoEstrategico getObjEst() {
		return objEst;
	}

	public void setObjEst(ObjetivoEstrategico objEst) {
		this.objEst = objEst;
	}

	public HashMap<Integer, Object> getProyectosIdsMap() {
		return proyectosIdsMap;
	}

	public void setProyectosIdsMap(HashMap<Integer, Object> proyectosIdsMap) {
		this.proyectosIdsMap = proyectosIdsMap;
	}

	public HashMap<Integer, Object> getProgramasIdsMap() {
		return programasIdsMap;
	}

	public void setProgramasIdsMap(HashMap<Integer, Object> programasIdsMap) {
		this.programasIdsMap = programasIdsMap;
	}

	public Integer getFechaActColor() {
		return fechaActColor;
	}

	public void setFechaActColor(Integer fechaActColor) {
		this.fechaActColor = fechaActColor;
	}

        public Integer getColorActSem() {
            return colorActSem;
        }

        public void setColorActSem(Integer colorActSem) {
            this.colorActSem = colorActSem;
        }

        

}
