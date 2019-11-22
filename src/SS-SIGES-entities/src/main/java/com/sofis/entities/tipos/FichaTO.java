package com.sofis.entities.tipos;

import com.sofis.entities.codigueras.EstadoPublicacionCodigos;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.Calidad;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Interesados;
import com.sofis.entities.data.LatlngProyectos;
import com.sofis.entities.data.MediaProyectos;
import com.sofis.entities.data.ObjetivoEstrategico;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Participantes;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.ProgIndices;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.ProgramasProyectos;
import com.sofis.entities.data.ProyIndices;
import com.sofis.entities.data.ProyOtrosDatos;
import com.sofis.entities.data.ProyectoExpVisualizador;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.Riesgos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.data.TipoDocumentoInstancia;
import com.sofis.entities.enums.TipoFichaEnum;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Usuario
 */
public class FichaTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private FichaTO fichaOriginal;
	private Integer fichaFk;
	private String proyProgId;
	private Integer proyProgVersion;
	private Integer tipoFicha;
	private Boolean activo;
	private Date ultimaModificacion;
	private Date fechaCrea;
	private Date fechaAct;
	private String fechaActColor;
	private String nombre;
	private Integer progPk;
	private String progNombre;
	private String descripcion;
	private String objetivo;
	private String objPublico;
	private String situacionActual;
	private String factorImpacto;
	private String grp;
	private Integer semaforoRojo;
	private Integer semaforoAmarillo;
	private Set<AreasTags> areasTematicas;
	private Set<Areas> areasRestringidas;
	private List<Interesados> interesados;
	private List<Documentos> documentos;
	private List<Riesgos> riesgos;
	private Set<Proyectos> proyectosSet;
	private SsUsuario usrPmofedFk;
	private SsUsuario usrSponsorFk;
	private SsUsuario usrAdjuntoFk;
	private SsUsuario usrGerenteFk;
	private Estados estado;
	private Estados estadoPendiente;
	private Organismos orgFk;
	private Areas areaFk;
	private Programas progFk;
	private Integer peso;

	private String fechaUltimaSitAct;
	private String leccionAprendida;
	private Cronogramas croFk;
	private Presupuesto preFk;
	private List<TipoDocumentoInstancia> tipoDocumentoInstancias;
	private String tipoSolicitud;
	private ProgIndices progIndices;
	private ProyIndices proyIndices;
	private Documentos resumenEjecutivo;
	private List<Participantes> participantes;
	//Otros Datos
	private Boolean publicable;
	private Date fechaPublicacion;
	private ProyOtrosDatos otrosDatos;
	private List<MediaProyectos> listMediaProy;

	private String latlngLatAux = new String();
	private String latlngLngAux = new String();

	private String deptoAux = new String();

	// Exportación al Visualizador
	private Boolean seleccionado;
	private Boolean exportado;
	private String errorDesc;

	private ObjetivoEstrategico objetivoEstrategico;

	private List<Calidad> calidadList;

	private Boolean todosLosMedia = false;

	private List<LatlngProyectos> latLngProyList;
	
	private Date fechaActPub;

	public FichaTO() {
		super();
	}

	public FichaTO(Integer fk) {
		this.fichaFk = fk;
	}

	public FichaTO(ProgramasProyectos p) {
		this.proyProgId = p.getId();
		this.fichaFk = p.getFichaFk();
		this.tipoFicha = (p.getTipoFicha()).intValue();
		this.fechaCrea = p.getFechaCrea();
		this.nombre = p.getNombre();
		this.estado = new Estados(p.getEstado());
		this.estado.setEstNombre(p.getEstadoNombre());
		this.areaFk = new Areas(p.getAreaPk());
		this.areaFk.setAreaNombre(p.getAreaNombre());
		this.usrGerenteFk = new SsUsuario(p.getGerente());
		this.usrGerenteFk.setUsuPrimerApellido(p.getGerentePrimerApellido());
		this.usrGerenteFk.setUsuPrimerNombre(p.getGerentePrimerNombre());
		this.activo = p.getActivo();

		if (p.getEstadoPendiente() != null) {
			this.estadoPendiente = new Estados(p.getEstadoPendiente());
		}
	}

	public FichaTO(Proyectos p) {
		this.proyProgId = "2-" + p.getProyPk();
		Programas prog = p.getProyProgFk();
		if (prog != null) {
			this.progFk = new Programas(prog.getProgPk(), prog.getProgNombre());
		}
		this.fichaFk = p.getProyPk();
		this.tipoFicha = TipoFichaEnum.PROYECTO.id;
		this.nombre = p.getProyNombre();
		this.estado = p.getProyEstFk();
		this.areaFk = p.getProyAreaFk();
		this.fechaAct = p.getProyFechaAct();
	}

	public FichaTO(ProyectoExpVisualizador p) {
		this.proyProgId = "2-" + p.getProyPk();
		Programas prog = p.getProyProgFk();
		if (prog != null) {
			this.progFk = new Programas(prog.getProgPk(), prog.getProgNombre());
		}
		this.fichaFk = p.getProyPk();
		this.tipoFicha = TipoFichaEnum.PROYECTO.id;
		this.nombre = p.getProyNombre();
		this.estado = p.getProyEstFk();
		this.areaFk = p.getProyAreaFk();
		this.fechaAct = p.getProyFechaAct();
		this.fechaPublicacion = p.getUltimaPublicacion();
		/**
		 * 14-12-2016 Debe tomar en cuenta el estado de la publicación. No
		 * pueden publicarse proyectos que están marcados como publicables pero
		 * pendiente de carga de datos.
		 */
		this.publicable = p.getProyPublicable() != null && p.getProyPublicable() && p.puedePublicarse();
		/**
		 * 24-10-2017 Se agrega la fecha de actualización de los publicables
		 */
		this.fechaActPub = p.getProyFechaActPub();

	}

	public FichaTO(Integer proyPk, String proyNombre, Integer progPk, String progNombre, Date fechaAct, Integer estadoPk, String estadoCod, String estadoLabel, Integer areaPk, String areaNombre) {
		this.proyProgId = "2-" + proyPk;
		if (progPk != null) {
			this.progFk = new Programas(progPk, progNombre);
		}
		this.fichaFk = proyPk;
		this.tipoFicha = TipoFichaEnum.PROYECTO.id;
		this.nombre = proyNombre;
		this.estado = new Estados(estadoPk, estadoCod, null, estadoLabel, null);
		this.areaFk = new Areas(areaPk, areaNombre);
		this.fechaAct = fechaAct;

	}

	public Boolean puedePublicarse() {
		return (this.publicable
			&& (this.otrosDatos == null
			|| this.otrosDatos.getProyOtrEstPubFk() != null
			&& (this.otrosDatos.getProyOtrEstPubFk().getEstPubCodigo().equals(EstadoPublicacionCodigos.PENDIENTE_PUBLICAR)
			|| this.otrosDatos.getProyOtrEstPubFk().getEstPubCodigo().equals(EstadoPublicacionCodigos.PUBLICADO))));

	}

	public FichaTO getFichaOriginal() {
		return fichaOriginal;
	}

	public void setFichaOriginal(FichaTO fichaOriginal) {
		this.fichaOriginal = fichaOriginal;
	}

	public Integer getTipoFicha() {
		return tipoFicha;
	}

	public void setTipoFicha(Integer tipoFicha) {
		this.tipoFicha = tipoFicha;
	}

	public Boolean getActivo() {
		return activo;
	}

	public boolean isActivo() {
		return activo != null ? activo : false;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public Date getFechaCrea() {
		return fechaCrea;
	}

	public void setFechaCrea(Date fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	public Date getFechaAct() {
		return fechaAct;
	}

	public void setFechaAct(Date fechaAct) {
		this.fechaAct = fechaAct;
	}

	public String getFechaActColor() {
		return fechaActColor;
	}

	public void setFechaActColor(String fechaActColor) {
		this.fechaActColor = fechaActColor;
	}

	public String getTipoFichaStr() {
		if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
			return "tipo_ficha_progamas";
		} else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
			return "tipo_ficha_proyectos";
		}

		return "";
	}

	public Integer getFichaFk() {
		return fichaFk;
	}

	public void setFichaFk(Integer fichaFk) {
		this.fichaFk = fichaFk;
	}

	public String getProyProgId() {
		if (proyProgId != null) {
			return proyProgId;
		} else {
			return tipoFicha + "-" + fichaFk;
		}

	}

	public void setProyProgId(String proyProgId) {
		this.proyProgId = proyProgId;
	}

	public String getNombre() {
		return nombre;
	}

	public String getFichaPkNombre() {
		return fichaFk + " - " + nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getProgPk() {
		return progPk;
	}

	public void setProgPk(Integer progPk) {
		this.progPk = progPk;
	}

	public String getProgNombre() {
		return progNombre;
	}

	public void setProgNombre(String progNombre) {
		this.progNombre = progNombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getObjPublico() {
		return objPublico;
	}

	public void setObjPublico(String objPublico) {
		this.objPublico = objPublico;
	}

	public String getGrp() {
		return grp;
	}

	public void setGrp(String grp) {
		this.grp = grp;
	}

	public Integer getSemaforoRojo() {
		return semaforoRojo;
	}

	public void setSemaforoRojo(Integer semaforoRojo) {
		this.semaforoRojo = semaforoRojo;
	}

	public Integer getSemaforoAmarillo() {
		return semaforoAmarillo;
	}

	public void setSemaforoAmarillo(Integer semaforoAmarillo) {
		this.semaforoAmarillo = semaforoAmarillo;
	}

	public Set<AreasTags> getAreasTematicas() {
		return areasTematicas;
	}

	public void setAreasTematicas(Set<AreasTags> areasTematicas) {
		this.areasTematicas = areasTematicas;
	}

	public Set<Areas> getAreasRestringidas() {
		return areasRestringidas;
	}

	public void setAreasRestringidas(Set<Areas> areasRestringidas) {
		this.areasRestringidas = areasRestringidas;
	}

	public List<Interesados> getInteresados() {
		return interesados;
	}

	public void setInteresados(List<Interesados> interesados) {
		this.interesados = interesados;
	}

	public List<Documentos> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documentos> documentos) {
		this.documentos = documentos;
	}

	public List<Riesgos> getRiesgos() {
		return riesgos;
	}

	public void setRiesgos(List<Riesgos> riesgos) {
		this.riesgos = riesgos;
	}

	public Set<Proyectos> getProyectosSet() {
		return proyectosSet;
	}

	public void setProyectosSet(Set<Proyectos> proyectosSet) {
		this.proyectosSet = proyectosSet;
	}

	public SsUsuario getUsrPmofedFk() {
		return usrPmofedFk;
	}

	public void setUsrPmofedFk(SsUsuario usrPmofedFk) {
		this.usrPmofedFk = usrPmofedFk;
	}

	public SsUsuario getUsrSponsorFk() {
		return usrSponsorFk;
	}

	public void setUsrSponsorFk(SsUsuario usrSponsorFk) {
		this.usrSponsorFk = usrSponsorFk;
	}

	public SsUsuario getUsrAdjuntoFk() {
		return usrAdjuntoFk;
	}

	public void setUsrAdjuntoFk(SsUsuario usrAdjuntoFk) {
		this.usrAdjuntoFk = usrAdjuntoFk;
	}

	public SsUsuario getUsrGerenteFk() {
		return usrGerenteFk;
	}

	public void setUsrGerenteFk(SsUsuario usrGerenteFk) {
		this.usrGerenteFk = usrGerenteFk;
	}

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	public Estados getEstadoPendiente() {
		return estadoPendiente;
	}

	public void setEstadoPendiente(Estados estadoPendiente) {
		this.estadoPendiente = estadoPendiente;
	}

	public boolean isEstadoPendiente(Integer estadoPk) {
		return estadoPendiente != null && estadoPk != null ? estadoPendiente.isEstado(estadoPk) : false;
	}

	public Organismos getOrgFk() {
		return orgFk;
	}

	public void setOrgFk(Organismos orgFk) {
		this.orgFk = orgFk;
	}

	public Areas getAreaFk() {
		return areaFk;
	}

	public void setAreaFk(Areas areaFk) {
		this.areaFk = areaFk;
	}

	public Programas getProgFk() {
		return progFk;
	}

	public void setProgFk(Programas progFk) {
		this.progFk = progFk;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public String getSituacionActual() {
		return situacionActual;
	}

	public void setSituacionActual(String situacionActual) {
		this.situacionActual = situacionActual;
	}

	public String getFactorImpacto() {
		return factorImpacto;
	}

	public void setFactorImpacto(String factorImpacto) {
		this.factorImpacto = factorImpacto;
	}

	public String getFechaUltimaSitAct() {
		return fechaUltimaSitAct;
	}

	public void setFechaUltimaSitAct(String fechaUltimaSitAct) {
		this.fechaUltimaSitAct = fechaUltimaSitAct;
	}

	public String getLeccionAprendida() {
		return leccionAprendida;
	}

	public void setLeccionAprendida(String leccionAprendida) {
		this.leccionAprendida = leccionAprendida;
	}

	public Cronogramas getCroFk() {
		return croFk;
	}

	public Presupuesto getPreFk() {
		return preFk;
	}

	public void setPreFk(Presupuesto preFk) {
		this.preFk = preFk;
	}

	public void setCroFk(Cronogramas croFk) {
		this.croFk = croFk;
	}

	public List<TipoDocumentoInstancia> getTipoDocumentoInstancias() {
		return tipoDocumentoInstancias;
	}

	public void setTipoDocumentoInstancias(List<TipoDocumentoInstancia> tipoDocumentoInstancias) {
		this.tipoDocumentoInstancias = tipoDocumentoInstancias;
	}

	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public boolean isEstado(Integer estadoPk) {
		return this.estado != null ? this.estado.isEstado(estadoPk) : false;
	}

	public boolean isPrograma() {
		return this.tipoFicha.equals(TipoFichaEnum.PROGRAMA.id);
	}

	public boolean isProyecto() {
		return this.tipoFicha.equals(TipoFichaEnum.PROYECTO.id);
	}

	public void cambioEstado(Estados estado) {
		this.setEstado(estado);
		this.setEstadoPendiente(null);
//        this.setSolAprobacion(null);
	}

	public boolean hasPk() {
		return this.fichaFk != null && this.fichaFk > 0;
	}

	public ProgIndices getProgIndices() {
		return progIndices;
	}

	public void setProgIndices(ProgIndices progIndices) {
		this.progIndices = progIndices;
	}

	public ProyIndices getProyIndices() {
		return proyIndices;
	}

	public void setProyIndices(ProyIndices proyIndices) {
		this.proyIndices = proyIndices;
	}

	public Documentos getResumenEjecutivo() {
		return resumenEjecutivo;
	}

	public void setResumenEjecutivo(Documentos resumenEjecutivo) {
		this.resumenEjecutivo = resumenEjecutivo;
	}

	public Integer getProyProgVersion() {
		return proyProgVersion;
	}

	public void setProyProgVersion(Integer proyProgVersion) {
		this.proyProgVersion = proyProgVersion;
	}

	public String getFichaFkTipo() {
		return this.tipoFicha + "-" + this.fichaFk;
	}

	public List<Participantes> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participantes> participantes) {
		this.participantes = participantes;
	}

	public Boolean getPublicable() {
		return publicable;
	}

	public void setPublicable(Boolean publicable) {
		this.publicable = publicable;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public ProyOtrosDatos getOtrosDatos() {
		return otrosDatos;
	}

	public void setOtrosDatos(ProyOtrosDatos otrosDatos) {
		this.otrosDatos = otrosDatos;
	}

	public List<MediaProyectos> getListMediaProy() {
		return listMediaProy;
	}

	public void setListMediaProy(List<MediaProyectos> listMediaProy) {
		this.listMediaProy = listMediaProy;
	}

	public String getLatlngLatAux() {
		return latlngLatAux;
	}

	public void setLatlngLatAux(String latlngLatAux) {
		this.latlngLatAux = latlngLatAux;
	}

	public String getLatlngLngAux() {
		return latlngLngAux;
	}

	public void setLatlngLngAux(String latlngLngAux) {
		this.latlngLngAux = latlngLngAux;
	}

	public String getDeptoAux() {
		return deptoAux;
	}

	public void setDeptoAux(String deptoAux) {
		this.deptoAux = deptoAux;
	}

	public Boolean getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(Boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public Boolean getExportado() {
		return exportado;
	}

	public void setExportado(Boolean exportado) {
		this.exportado = exportado;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	/**
	 * @return the objetivoEstrategico
	 */
	public ObjetivoEstrategico getObjetivoEstrategico() {
		return objetivoEstrategico;
	}

	/**
	 * @param objetivoEstrategico the objetivoEstrategico to set
	 */
	public void setObjetivoEstrategico(ObjetivoEstrategico objetivoEstrategico) {
		this.objetivoEstrategico = objetivoEstrategico;
	}

	/**
	 * @return the calidadList
	 */
	public List<Calidad> getCalidadList() {
		return calidadList;
	}

	/**
	 * @param calidadList the calidadList to set
	 */
	public void setCalidadList(List<Calidad> calidadList) {
		this.calidadList = calidadList;
	}

	/**
	 * @return the todosLosMedia
	 */
	public Boolean getTodosLosMedia() {
		return todosLosMedia;
	}

	/**
	 * @param todosLosMedia the todosLosMedia to set
	 */
	public void setTodosLosMedia(Boolean todosLosMedia) {
		this.todosLosMedia = todosLosMedia;
	}

	public List<LatlngProyectos> getLatLngProyList() {
		return latLngProyList;
	}

	public void setLatLngProyList(List<LatlngProyectos> latLngProyList) {
		this.latLngProyList = latLngProyList;
	}

	public Date getFechaActPub() {
		return fechaActPub;
	}

	public void setFechaActPub(Date fechaActPub) {
		this.fechaActPub = fechaActPub;
	}

}
