package com.sofis.web.mb;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Organismos;
import com.sofis.exceptions.GeneralException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.reference.EntityReference;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.ConsultaHistoricoDelegate;
import com.sofis.web.genericos.constantes.ConstantesPresentacion;
import com.sofis.web.utils.EntityReferenceDataProvider;
import com.sofis.web.utils.FilaRiesgosLimite;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.LazyLoadingList;
import com.sofis.web.utils.ProcesarMensajes;
import com.sofis.web.utils.SofisCombo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "configuracionMB")
@ViewScoped
public class ConfiguracionMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private ConfiguracionDelegate confDelegate;
    @Inject
    private ConsultaHistoricoDelegate histDelegate;

    private String bCodigo;
    private String bDescripcion;
    private Boolean renderResultado = false;
    private Configuracion confEnEdicion = new Configuracion();
    private Boolean renderPopupEdicion = false;
    private Boolean renderPopupHistorial = false;
    private String cantElementosPorPagina = "25";
    private List<EntityReference<Integer>> listaResultado = new ArrayList<>();
    private List<Configuracion> listaHitorial = new ArrayList<>();
    private SofisCombo comboCategoria = new SofisCombo();
    //la configuracion de riesgos
    private List<FilaRiesgosLimite> limiteGestionRiesgos;
    private Configuracion confRiesgoLimiteAmarillo = new Configuracion();
    private Configuracion confRiesgoLimiteRojo = new Configuracion();
    private Configuracion confRiesgoTiempoLimiteAmarillo = new Configuracion();
    private Configuracion confRiesgoTiempoLimiteRojo = new Configuracion();
    //Configuracion de Documentos
    private Configuracion confDocLimiteAmarillo = new Configuracion(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO);
    private Configuracion confDocLimiteRojo = new Configuracion(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_ROJO);
    private Configuracion tamanioMaxArchivoDocumento = new Configuracion(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_DOCUMENTO);
    private Configuracion tamanioMaxArchivoMultimedia = new Configuracion(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_MULTIMEDIA);

    /**
     * Creates a new instance of ConfiguracionMB
     */
    public ConfiguracionMB() {
    }

    public void setInicioMB(InicioMB inicioMB) {
	this.inicioMB = inicioMB;
    }

    @PostConstruct
    public void init() {
	inicioMB.cargarOrganismoSeleccionado();

	comboCategoria = new SofisCombo();
	comboCategoria.addEmptyItem(ConstantesPresentacion.DEFECTO_COMBO);
	comboCategoria.setSelected(0);

	//inicializa el limite de riesgos
	limiteGestionRiesgos = new ArrayList<>();
	FilaRiesgosLimite fila1 = new FilaRiesgosLimite(10, 2d, 4d, 6d, 8d, 10d);
	FilaRiesgosLimite fila2 = new FilaRiesgosLimite(8, 1.6d, 3.2d, 4.8d, 6.4d, 8d);
	FilaRiesgosLimite fila3 = new FilaRiesgosLimite(6, 1.2d, 2.4d, 3.6d, 4.8d, 6d);
	FilaRiesgosLimite fila4 = new FilaRiesgosLimite(4, 0.8d, 1.6d, 2.4d, 3.2d, 4d);
	FilaRiesgosLimite fila5 = new FilaRiesgosLimite(2, 0.4d, 0.8d, 1.2d, 1.6d, 2d);
	limiteGestionRiesgos.add(fila1);
	limiteGestionRiesgos.add(fila2);
	limiteGestionRiesgos.add(fila3);
	limiteGestionRiesgos.add(fila4);
	limiteGestionRiesgos.add(fila5);

	Integer orgPk = inicioMB.getOrganismo() != null ? inicioMB.getOrganismo().getOrgPk() : null;

	//carga el valor de los limites de riesgos
	confRiesgoLimiteAmarillo = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_AMARILLO, orgPk);
	if (confRiesgoLimiteAmarillo == null) {
	    confRiesgoLimiteAmarillo = new Configuracion(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_AMARILLO);
	}
	confRiesgoLimiteRojo = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_ROJO, orgPk);
	if (confRiesgoLimiteRojo == null) {
	    confRiesgoLimiteRojo = new Configuracion(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_ROJO);
	}
	confRiesgoTiempoLimiteAmarillo = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_AMARILLO, orgPk);
	if (confRiesgoTiempoLimiteAmarillo == null) {
	    confRiesgoTiempoLimiteAmarillo = new Configuracion(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_AMARILLO);
	}
	confRiesgoTiempoLimiteRojo = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_ROJO, orgPk);
	if (confRiesgoTiempoLimiteRojo == null) {
	    confRiesgoTiempoLimiteRojo = new Configuracion(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_ROJO);
	}

	//Carga los valores de porcentaje de documentos.
	confDocLimiteAmarillo = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO, orgPk);
	if (confDocLimiteAmarillo == null) {
	    confDocLimiteAmarillo = new Configuracion(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO);
	}

	confDocLimiteRojo = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_ROJO, orgPk);
	if (confDocLimiteRojo == null) {
	    confDocLimiteRojo = new Configuracion(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_ROJO);
	}

	tamanioMaxArchivoDocumento = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_DOCUMENTO, orgPk);
	if (tamanioMaxArchivoDocumento == null) {
	    tamanioMaxArchivoDocumento = new Configuracion(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_DOCUMENTO);
	}

	tamanioMaxArchivoMultimedia = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_MULTIMEDIA, orgPk);
	if (tamanioMaxArchivoMultimedia == null) {
	    tamanioMaxArchivoMultimedia = new Configuracion(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_MULTIMEDIA);
	}
    }

    private void reset() {
	bCodigo = "";
	bDescripcion = "";
	listaResultado = new ArrayList<>();
	renderResultado = false;
    }

    // <editor-fold defaultstate="collapsed" desc="eventos">
    public String buscar() {
	renderResultado = true;
	List<CriteriaTO> criterios = new ArrayList<>();

	MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
		MatchCriteriaTO.types.EQUALS, "cnfOrgFk.orgPk",
		inicioMB.getOrganismo().getOrgPk());
	criterios.add(criterioOrg);

	if (!StringsUtils.isEmpty(bCodigo)) {
	    MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
		    MatchCriteriaTO.types.CONTAINS, "cnfCodigo",
		    bCodigo.toUpperCase().trim());
	    criterios.add(criterio);
	}

	if (!StringsUtils.isEmpty(bDescripcion)) {
//	    MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "cnfDescripcion", bDescripcion.toUpperCase().trim());
	    CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("cnfDescripcion", bDescripcion.toUpperCase().trim());
	    criterios.add(criterio);
	}
	CriteriaTO condicion = null;
	if (!criterios.isEmpty()) {
	    if (criterios.size() == 1) {
		condicion = criterios.get(0);
	    } else {
		condicion = CriteriaTOUtils.createANDTO(criterios
			.toArray(new CriteriaTO[0]));
	    }
	} else {
	    // condicion dummy para que el count by criteria funcione
	    condicion = CriteriaTOUtils.createMatchCriteriaTO(
		    MatchCriteriaTO.types.NOT_NULL, "id", 1);
	}
	String[] propiedades = {"id", "cnfCodigo", "cnfValor", "cnfDescripcion", "cnfOrgFk.orgNombre"};
	String className = Configuracion.class.getName();
	String[] orderBy = {"cnfCodigo"};
	boolean[] asc = {true};

	EntityReferenceDataProvider cd = new EntityReferenceDataProvider(
		propiedades, className, condicion, orderBy, asc);
	listaResultado = new LazyLoadingList(cd, ConstantesPresentacion.CANTIDAD_PAGINACION, ConstantesPresentacion.PAGINAS_BUFFERED, false);
	return null;
    }

    public String limpiar() {
	reset();
	return null;
    }

    public String agregar() {
	confEnEdicion = new Configuracion();
	confEnEdicion.setCnfOrgFk(new Organismos());
	renderPopupEdicion = true;
	return null;
    }

    public String editar(Integer id) {
	try {
	    confEnEdicion = confDelegate.obtenerCnfPorId(id);
	    renderPopupEdicion = true;
	} catch (GeneralException ex) {
	    Logger.getLogger(ConfiguracionMB.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }

    public String consultarHistorial(Integer id) {
	try {
	    listaHitorial = histDelegate.obtenerConfiguracion(id);
	    renderPopupHistorial = true;
	} catch (GeneralException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(ConfiguracionMB.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }

    public Configuracion getConfRiesgoLimiteAmarillo() {
	return confRiesgoLimiteAmarillo;
    }

    public void setConfRiesgoLimiteAmarillo(Configuracion confRiesgoLimiteAmarillo) {
	this.confRiesgoLimiteAmarillo = confRiesgoLimiteAmarillo;
    }

    public Configuracion getConfRiesgoLimiteRojo() {
	return confRiesgoLimiteRojo;
    }

    public void setConfRiesgoLimiteRojo(Configuracion confRiesgoLimiteRojo) {
	this.confRiesgoLimiteRojo = confRiesgoLimiteRojo;
    }

    public Configuracion getConfRiesgoTiempoLimiteAmarillo() {
	return confRiesgoTiempoLimiteAmarillo;
    }

    public void setConfRiesgoTiempoLimiteAmarillo(Configuracion confRiesgoTiempoLimiteAmarillo) {
	this.confRiesgoTiempoLimiteAmarillo = confRiesgoTiempoLimiteAmarillo;
    }

    public Configuracion getConfRiesgoTiempoLimiteRojo() {
	return confRiesgoTiempoLimiteRojo;
    }

    public void setConfRiesgoTiempoLimiteRojo(Configuracion confRiesgoTiempoLimiteRojo) {
	this.confRiesgoTiempoLimiteRojo = confRiesgoTiempoLimiteRojo;
    }

    public Configuracion getConfDocLimiteAmarillo() {
	return confDocLimiteAmarillo;
    }

    public void setConfDocLimiteAmarillo(Configuracion confDocLimiteAmarillo) {
	this.confDocLimiteAmarillo = confDocLimiteAmarillo;
    }

    public Configuracion getConfDocLimiteRojo() {
	return confDocLimiteRojo;
    }

    public void setConfDocLimiteRojo(Configuracion confDocLimiteRojo) {
	this.confDocLimiteRojo = confDocLimiteRojo;
    }

    public Configuracion getTamanioMaxArchivoDocumento() {
	return tamanioMaxArchivoDocumento;
    }

    public void setTamanioMaxArchivoDocumento(Configuracion tamanioMaxArchivoDocumento) {
	this.tamanioMaxArchivoDocumento = tamanioMaxArchivoDocumento;
    }

    public Configuracion getTamanioMaxArchivoMultimedia() {
	return tamanioMaxArchivoMultimedia;
    }

    public void setTamanioMaxArchivoMultimedia(Configuracion tamanioMaxArchivoMultimedia) {
	this.tamanioMaxArchivoMultimedia = tamanioMaxArchivoMultimedia;
    }

    public String cerrarPopupHistorial() {
	renderPopupHistorial = false;
	return null;
    }

    public String guardarRiesgoLimite() {
	try {
	    Double dAmarillo = new Double(confRiesgoLimiteAmarillo.getCnfValor());
	    Double dRojo = new Double(confRiesgoLimiteRojo.getCnfValor());
	    if (dAmarillo >= dRojo) {
		JSFUtils.agregarMsgError("El limite Amarillo no puede ser mayor o igual al Rojo.");
		return null;
	    } else if (dRojo < dAmarillo) {
		JSFUtils.agregarMsgError("El limite Rojo no puede ser menor al Amarillo.");
		return null;
	    }

	    confRiesgoLimiteAmarillo = confDelegate.guardar(confRiesgoLimiteAmarillo);
	    confRiesgoLimiteRojo = confDelegate.guardar(confRiesgoLimiteRojo);
	    confRiesgoTiempoLimiteAmarillo = confDelegate.guardar(confRiesgoTiempoLimiteAmarillo);
	    confRiesgoTiempoLimiteRojo = confDelegate.guardar(confRiesgoTiempoLimiteRojo);
	} catch (GeneralException ex) {
	    for (FacesMessage s : ProcesarMensajes.obtenerMensajes(ex.getErrores())) {
		FacesContext.getCurrentInstance().addMessage("", s);
	    }
	} catch (Exception ex) {

	    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
	}
	return null;
    }

    public String guardarDocLimite() {
	try {
	    Integer iAmarillo = Integer.valueOf(confDocLimiteAmarillo.getCnfValor());
	    Integer iRojo = Integer.valueOf(confDocLimiteRojo.getCnfValor());
	    if (iAmarillo >= iRojo) {
		JSFUtils.agregarMsgError("El limite Amarillo no puede ser mayor o igual al Rojo.");
		return null;
	    } else if (iRojo < iAmarillo) {
		JSFUtils.agregarMsgError("El limite Rojo no puede ser menor al Amarillo.");
		return null;
	    }

	    confDocLimiteAmarillo = confDelegate.guardar(confDocLimiteAmarillo);
	    confDocLimiteRojo = confDelegate.guardar(confDocLimiteRojo);
	} catch (GeneralException ex) {
	    for (FacesMessage s : ProcesarMensajes.obtenerMensajes(ex.getErrores())) {
		FacesContext.getCurrentInstance().addMessage("", s);
	    }
	} catch (Exception ex) {
	    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
	}
	return null;
    }

    public String guardar() {
	try {
	    confDelegate.guardar(confEnEdicion);
	    renderPopupEdicion = false;
	    buscar();
	} catch (GeneralException ex) {
	    logger.log(Level.SEVERE, null, ex);
	    JSFUtils.agregarMsgs("", ex.getErrores());
	} catch (Exception ex) {
	    logger.log(Level.SEVERE, null, ex);
//            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
	}
	return null;
    }

    public String riesgoColor(Double valor) {
	try {
	    Double limiteAmarillo = Double.parseDouble(confRiesgoLimiteAmarillo.getCnfValor());
	    Double limiteRojo = Double.parseDouble(confRiesgoLimiteRojo.getCnfValor());

	    if (valor <= limiteAmarillo) {
		return ConstantesEstandares.SEMAFORO_VERDE;
	    }

	    if (valor > limiteAmarillo && valor < limiteRojo) {
		return ConstantesEstandares.SEMAFORO_AMARILLO;
	    }
	    if (valor >= limiteRojo) {
		return ConstantesEstandares.SEMAFORO_ROJO;
	    }

	    return ConstantesEstandares.COLOR_TRANSPARENT;
	} catch (Exception w) {
	    return ConstantesEstandares.COLOR_TRANSPARENT;
	}

    }

    public String cancelar() {
	//reset();
	renderPopupEdicion = false;
	return null;
    }

    public void cambiarCantPaginas(ValueChangeEvent evt) {
	buscar();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getter-setter">
    public String getCantElementosPorPagina() {
	return cantElementosPorPagina;
    }

    public void setCantElementosPorPagina(String cantElementosPorPagina) {
	this.cantElementosPorPagina = cantElementosPorPagina;
    }

    public List<Configuracion> getListaHitorial() {
	return listaHitorial;
    }

    public void setListaHitorial(List<Configuracion> listaHitorial) {
	this.listaHitorial = listaHitorial;
    }

    public Boolean getRenderPopupHistorial() {
	return renderPopupHistorial;
    }

    public void setRenderPopupHistorial(Boolean renderPopupHistorial) {
	this.renderPopupHistorial = renderPopupHistorial;
    }

    public Configuracion getConfEnEdicion() {
	return confEnEdicion;
    }

    public void setConfEnEdicion(Configuracion confEnEdicion) {
	this.confEnEdicion = confEnEdicion;
    }

    public Boolean getRenderPopupEdicion() {
	return renderPopupEdicion;
    }

    public List<FilaRiesgosLimite> getLimiteGestionRiesgos() {
	return limiteGestionRiesgos;
    }

    public void setLimiteGestionRiesgos(List<FilaRiesgosLimite> limiteGestionRiesgos) {
	this.limiteGestionRiesgos = limiteGestionRiesgos;
    }

    public void setRenderPopupEdicion(Boolean renderPopupEdicion) {
	this.renderPopupEdicion = renderPopupEdicion;
    }

    public String getbCodigo() {
	return bCodigo;
    }

    public void setbCodigo(String bCodigo) {
	this.bCodigo = bCodigo;
    }

    public String getbDescripcion() {
	return bDescripcion;
    }

    public void setbDescripcion(String bDescripcion) {
	this.bDescripcion = bDescripcion;
    }

    public List<EntityReference<Integer>> getListaResultado() {
	return listaResultado;
    }

    public void setListaResultado(List<EntityReference<Integer>> listaResultado) {
	this.listaResultado = listaResultado;
    }

    public SofisCombo getComboCategoria() {
	return comboCategoria;
    }

    public void setComboCategoria(SofisCombo comboCategoria) {
	this.comboCategoria = comboCategoria;
    }

    public Boolean getRenderResultado() {
	return renderResultado;
    }

    public void setRenderResultado(Boolean renderResultado) {
	this.renderResultado = renderResultado;
    }
    // </editor-fold>

    public String obtenerCnfValorPorCodigo(String codigo, Integer orgPk) {
	return confDelegate.obtenerCnfValorPorCodigo(codigo, orgPk);
    }
}
