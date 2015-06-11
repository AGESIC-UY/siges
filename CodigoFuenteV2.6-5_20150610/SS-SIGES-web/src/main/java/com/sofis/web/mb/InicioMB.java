package com.sofis.web.mb;

import com.icesoft.faces.context.effects.JavascriptContext;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.business.ejbs.DatosUsuario;
import com.sofis.entities.codigueras.SsRolCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Ambito;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.RolesInteresados;
import com.sofis.entities.data.SsUsuOfiRoles;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.NivelEnum;
import com.sofis.entities.tipos.FiltroInicioItem;
import com.sofis.entities.tipos.FiltroInicioTO;
import com.sofis.entities.utils.FichaUtils;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.exceptions.GeneralException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.delegates.AmbitoDelegate;
import com.sofis.web.delegates.AreaTematicaDelegate;
import com.sofis.web.delegates.AreasDelegate;
import com.sofis.web.delegates.BusquedaFiltroDelegate;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.FuenteFinanciamientoDelegate;
import com.sofis.web.delegates.OrganiIntProveDelegate;
import com.sofis.web.delegates.OrganismoDelegate;
import com.sofis.web.delegates.PresupuestoDelegate;
import com.sofis.web.delegates.RolesInteresadosDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.delegates.ValorCalidadCodigosDelegate;
import com.sofis.web.enums.FieldAttributeEnum;
import com.sofis.web.genericos.constantes.ConstantesNavegacion;
import com.sofis.web.genericos.constantes.ConstantesPresentacion;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.SofisCombo;
import com.sofis.web.utils.WebUtils;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import org.icefaces.ace.model.tree.NodeStateMap;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "inicioMB")
@SessionScoped
public class InicioMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    @Inject
    private DatosUsuario datUsuario;
    @Inject
    private AreasDelegate areasDelegate;
    @Inject
    private AreaTematicaDelegate areaTematicaDelegate;
    @Inject
    private OrganismoDelegate organismoDelegate;
    @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;
    @Inject
    private OrganiIntProveDelegate organiIntProveDelegate;
    @Inject
    private RolesInteresadosDelegate rolesInteresadosDelegate;
    @Inject
    private PresupuestoDelegate presupuestoDelegate;
    @Inject
    private AmbitoDelegate ambitoDelegate;
    @Inject
    private BusquedaFiltroDelegate busquedaFiltroDelegate;
    @Inject
    private FuenteFinanciamientoDelegate fuenteFinanciamientoDelegate;
    @Inject
    private ConfiguracionDelegate configuracionDelegate;
    @Inject
    private ValorCalidadCodigosDelegate valorCalidadCodigosDelegate;

    //Variables
    private String nombreUsuario = "";
    private SofisCombo organismosUsuario;
    private boolean containerMax = false;
    /**
     * Usuario Logueado en el sistema.
     */
    private SsUsuario usuario;
    /**
     * Organismo seleccionado para trabajar
     */
    private Organismos organismo;
    private List<SelectItem> listaEstadosItem;
    List<SelectItem> listaGradoRiesgoItems;
    private SofisCombo listaOrganizacionCombo = new SofisCombo();
    private SofisCombo listaAreasOrganismoCombo = new SofisCombo();
    private SofisCombo listaSponsorCombo = new SofisCombo();
    private SofisCombo listaAdjuntoCombo = new SofisCombo();
    private SofisCombo listaGerenteCombo = new SofisCombo();
    private SofisCombo listaPmoFederadaCombo = new SofisCombo();
    private SofisCombo listaNivelItemsCombo = new SofisCombo();
    private SofisCombo listaIntRolCombo = new SofisCombo();
    private SofisCombo listaIntAmbitoCombo = new SofisCombo();
    private SofisCombo listaOrgaProvCombo = new SofisCombo();
    private SofisCombo listaFuentesCombo = new SofisCombo();
    private SofisCombo listaCalIndiceCombo = new SofisCombo();
    //el filtro de la pagina de Inicio
    private FiltroInicioTO filtro = new FiltroInicioTO();
    private Boolean filtroRender;
    private Boolean renderPopupAreaTematica = false;
    private boolean renderPopupMensajes = false;
    private List<MutableTreeNode> listaAreasTagsTreeNode = new ArrayList<>();
    private Set<AreasTags> areasTematicas;
    private NodeStateMap areasTematicasStateMap;
    private String calendarPattern = ConstantesEstandares.CALENDAR_PATTERN;
    private String calendarTimeZone = ConstantesEstandares.CALENDAR_TIME_ZONE;

    /**
     * Creates a new instance of InicioMB
     */
    public InicioMB() {
    }

    public void clear() {
        usuario = null;
    }

    public SofisCombo getOrganismosUsuario() {
        return organismosUsuario;
    }

    public void setOrganismosUsuario(SofisCombo organismosUsuario) {
        this.organismosUsuario = organismosUsuario;
    }

    public boolean isContainerMax() {
        return containerMax;
    }

    public void setContainerMax(boolean containerMax) {
        this.containerMax = containerMax;
    }

    public SsUsuario getUsuario() {
        datosPrincipal();
        return usuario;
    }

    public void setUsuario(SsUsuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario() {
        datosPrincipal();
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Organismos getOrganismo() {
        datosPrincipal();
        return organismo;
    }

    public void setOrganismo(Organismos organismo) {
        this.organismo = organismo;
    }

    public Boolean getFiltroRender() {
        return filtroRender;
    }

    public void setFiltroRender(Boolean filtroRender) {
        this.filtroRender = filtroRender;
    }

    public String getCalendarPattern() {
        return calendarPattern;
    }

    public void setCalendarPattern(String calendarPattern) {
        this.calendarPattern = calendarPattern;
    }

    public String getCalendarTimeZone() {
        return calendarTimeZone;
    }

    public void setCalendarTimeZone(String calendarTimeZone) {
        this.calendarTimeZone = calendarTimeZone;
    }

    public List<SelectItem> getListaEstadosItem() {
        return listaEstadosItem;
    }

    public void setListaEstadosItem(List<SelectItem> listaEstadosItem) {
        this.listaEstadosItem = listaEstadosItem;
    }

    public List<SelectItem> getListaGradoRiesgoItems() {
        return listaGradoRiesgoItems;
    }

    public void setListaGradoRiesgoItems(List<SelectItem> listaGradoRiesgoItems) {
        this.listaGradoRiesgoItems = listaGradoRiesgoItems;
    }

    public SofisCombo getListaOrganizacionCombo() {
        return listaOrganizacionCombo;
    }

    public void setListaOrganizacionCombo(SofisCombo listaOrganizacionCombo) {
        this.listaOrganizacionCombo = listaOrganizacionCombo;
    }

    public SofisCombo getListaAreasOrganismoCombo() {
        return listaAreasOrganismoCombo;
    }

    public void setListaAreasOrganismoCombo(SofisCombo listaAreasOrganismoCombo) {
        this.listaAreasOrganismoCombo = listaAreasOrganismoCombo;
    }

    public SofisCombo getListaSponsorCombo() {
        return listaSponsorCombo;
    }

    public void setListaSponsorCombo(SofisCombo listaSponsorCombo) {
        this.listaSponsorCombo = listaSponsorCombo;
    }

    public SofisCombo getListaAdjuntoCombo() {
        return listaAdjuntoCombo;
    }

    public void setListaAdjuntoCombo(SofisCombo listaAdjuntoCombo) {
        this.listaAdjuntoCombo = listaAdjuntoCombo;
    }

    public SofisCombo getListaGerenteCombo() {
        return listaGerenteCombo;
    }

    public void setListaGerenteCombo(SofisCombo listaGerenteCombo) {
        this.listaGerenteCombo = listaGerenteCombo;
    }

    public SofisCombo getListaNivelItemsCombo() {
        return listaNivelItemsCombo;
    }

    public void setListaNivelItemsCombo(SofisCombo listaNivelItemsCombo) {
        this.listaNivelItemsCombo = listaNivelItemsCombo;
    }

    public SofisCombo getListaPmoFederadaCombo() {
        return listaPmoFederadaCombo;
    }

    public SofisCombo getListaIntRolCombo() {
        return listaIntRolCombo;
    }

    public void setListaIntRolCombo(SofisCombo listaIntRolCombo) {
        this.listaIntRolCombo = listaIntRolCombo;
    }

    public SofisCombo getListaIntAmbitoCombo() {
        return listaIntAmbitoCombo;
    }

    public void setListaIntAmbitoCombo(SofisCombo listaIntAmbitoCombo) {
        this.listaIntAmbitoCombo = listaIntAmbitoCombo;
    }

    public SofisCombo getListaOrgaProvCombo() {
        return listaOrgaProvCombo;
    }

    public void setListaOrgaProvCombo(SofisCombo listaOrgaProvCombo) {
        this.listaOrgaProvCombo = listaOrgaProvCombo;
    }

    public SofisCombo getListaFuentesCombo() {
        return listaFuentesCombo;
    }

    public void setListaFuentesCombo(SofisCombo listaFuentesCombo) {
        this.listaFuentesCombo = listaFuentesCombo;
    }

    public SofisCombo getListaCalIndiceCombo() {
        return listaCalIndiceCombo;
    }

    public void setListaCalIndiceCombo(SofisCombo listaCalIndiceCombo) {
        this.listaCalIndiceCombo = listaCalIndiceCombo;
    }

    public void setListaPmoFederadaCombo(SofisCombo listaPmoFederadaCombo) {
        this.listaPmoFederadaCombo = listaPmoFederadaCombo;
    }

    public Boolean getRenderPopupAreaTematica() {
        return renderPopupAreaTematica;
    }

    public void setRenderPopupAreaTematica(Boolean renderPopupAreaTematica) {
        this.renderPopupAreaTematica = renderPopupAreaTematica;
    }

    public boolean getRenderPopupMensajes() {
        return renderPopupMensajes;
    }

    public void setRenderPopupMensajes(boolean renderPopupMensajes) {
        this.renderPopupMensajes = renderPopupMensajes;
    }

    public List<MutableTreeNode> getListaAreasTagsTreeNode() {
        return listaAreasTagsTreeNode;
    }

    public void setListaAreasTagsTreeNode(List<MutableTreeNode> listaAreasTagsTreeNode) {
        this.listaAreasTagsTreeNode = listaAreasTagsTreeNode;
    }

    public Set<AreasTags> getAreasTematicas() {
        return areasTematicas;
    }

    public void setAreasTematicas(Set<AreasTags> areasTematicas) {
        this.areasTematicas = areasTematicas;
    }

    public NodeStateMap getAreasTematicasStateMap() {
        return areasTematicasStateMap;
    }

    public void setAreasTematicasStateMap(NodeStateMap areasTematicasStateMap) {
        this.areasTematicasStateMap = areasTematicasStateMap;
    }

    public FiltroInicioTO getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroInicioTO filtro) {
        this.filtro = filtro;
    }

    @PostConstruct
    public void init() {
        datosPrincipal();
        redirectUsuarioPage();
    }

    public void inicializarFiltro() {
        filtro = busquedaFiltroDelegate.obtenerFiltroInicio(usuario, organismo);

        if (filtro != null) {
            setSelectedCombosFiltro();
        } else {
            filtroPorDefecto();
        }

        Configuracion confCalAmarillo = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.CALIDAD_LIMITE_AMARILLO, organismo.getOrgPk());
        Configuracion confCalRojo = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.CALIDAD_LIMITE_ROJO, organismo.getOrgPk());
        if (filtro.getConfiguracion() == null) {
            filtro.setConfiguracion(new HashMap<String, Configuracion>());
        }
        if (!filtro.getConfiguracion().containsKey(confCalRojo.getCnfCodigo())) {
            filtro.getConfiguracion().put(confCalRojo.getCnfCodigo(), confCalRojo);
        }
        if (!filtro.getConfiguracion().containsKey(confCalAmarillo.getCnfCodigo())) {
            filtro.getConfiguracion().put(confCalAmarillo.getCnfCodigo(), confCalAmarillo);
        }
    }

    public boolean areasTematicasHasValues() {
        if (areasTematicasStateMap == null) {
            return areasTematicas != null ? areasTematicas.size() > 0 : false;
        }
        return getAreasTematicasSelected().size() > 0;
    }

    public String cancelarAcciones() {
        return ConstantesNavegacion.IR_A_INICIO;
    }

    public String logout() {
        logger.info("-- Logout --");
//        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return ConstantesNavegacion.IR_A_INICIO;
    }

    public String redirigirInicio(String codigo) {
        return codigo;
    }

    public Integer getOrganismoSeleccionado() {
        if (organismosUsuario != null) {
            return organismosUsuario.getSelected();
        } else {
            return null;
        }
    }

    public String irEditarProgramaProyecto(Integer fichaPk, Integer tipoFicha, boolean nuevaVentana) {
        String str = StringsUtils.concat(fichaPk.toString(), "-", tipoFicha.toString());
        return irEditarProgramaProyecto(str, nuevaVentana);
    }

    public String irEditarProgramaProyecto(String s, boolean nuevaVentana) {

        if (nuevaVentana) {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.getSession().setAttribute(ConstantesPresentacion.PROG_PROY_ID, s);
            String url = StringsUtils.concat("window.open('", request.getContextPath(), "/paginasPrivadas/ficha.xhtml", "','');");
            JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), url);
            return null;
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put(ConstantesPresentacion.PROG_PROY_ID, s);
            return ConstantesNavegacion.IR_A_EDITAR_FICHA;
        }
    }

    public String irReporteProyecto(Integer proyPk, boolean nuevaVentana) {

        if (nuevaVentana) {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.getSession().setAttribute("reporteProyPk", proyPk);
            request.getSession().setAttribute("reporteTipo", "proyecto");
            String url = StringsUtils.concat("window.open('", request.getContextPath(), "/paginasPrivadas/reporteProyecto.xhtml", "','');");
            JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), url);
            return null;
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reporteProyPk", proyPk);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reporteTipo", "proyecto");
            return ConstantesNavegacion.IR_A_REPORTE_PROYECTO;
        }
    }

    public String irReporteProyectoTablas(Integer proyPk, boolean nuevaVentana) {

        if (nuevaVentana) {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.getSession().setAttribute("reporteProyPk", proyPk);
            request.getSession().setAttribute("reporteTipo", "proyectoTablas");
            String url = StringsUtils.concat("window.open('", request.getContextPath(), "/paginasPrivadas/reporteProyectoTablas.xhtml", "','');");
            JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), url);
            return null;
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reporteProyPk", proyPk);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reporteTipo", "proyectoTablas");
            return ConstantesNavegacion.IR_A_REPORTE_PROYECTO_TABLAS;
        }
    }

    public String irReportePrograma(Integer progPk, boolean nuevaVentana) {

        if (nuevaVentana) {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.getSession().setAttribute("reporteProgPk", progPk);
            String url = StringsUtils.concat("window.open('", request.getContextPath(), "/paginasPrivadas/reportePrograma.xhtml", "','');");
            JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), url);
            return null;
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reporteProgPk", progPk);
            return ConstantesNavegacion.IR_A_REPORTE_PROGRAMA;
        }
    }

    public String irReporteCronograma(Integer proyPk, boolean nuevaVentana) {

        if (nuevaVentana) {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.getSession().setAttribute("reporteProyPk", proyPk);
            request.getSession().setAttribute("reporteTipo", "cronograma");
            String url = StringsUtils.concat("window.open('", request.getContextPath(), "/paginasPrivadas/reporteCronograma.xhtml", "','');");
            JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), url);
            return null;
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reporteProyPk", proyPk);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reporteTipo", "cronograma");
            return ConstantesNavegacion.IR_A_REPORTE_CRONOGRAMA;
        }
    }

    public String prespuestoTablaDinamicaColor(Integer fichaFk, Integer monPk) {
        return presupuestoDelegate.obtenerColorAC(fichaFk, monPk, getOrganismo().getOrgPk(), null, null);
    }

    public List getAreasTematicasSelected() {
        if (areasTematicasStateMap == null) {
            return Collections.emptyList();
        }
        return areasTematicasStateMap.getSelected();
    }

    public void renderizarFiltro() {
        filtroRender = filtroRender == null || filtroRender == false;
    }

    /**
     * Retorna un booolean si el fieldName aportado debe estar deshabilitado
     * para usar.
     *
     * @param fieldName
     * @return
     */
    public boolean fieldDisabled(String fieldName) {
        //dependiendo del usuario, estado etc es si esta habilitado o no
        return fieldAttribute(fieldName, FieldAttributeEnum.DISABLED);
    }

    public boolean fieldDisabled(String fieldName, FiltroInicioItem item) {
        //dependiendo del usuario, estado etc es si esta habilitado o no
        return fieldAttribute(fieldName, FieldAttributeEnum.DISABLED, item);
    }

    /**
     * Retorna un booolean si el fieldName aportado debe ser desplegado en
     * pantalla.
     *
     * @param fieldName
     * @return
     */
    public boolean fieldRendered(String fieldName) {
        //dependiendo del usuario, estado etc es si esta habilitado o no
        return fieldAttribute(fieldName, FieldAttributeEnum.RENDERED);
    }

    public boolean fieldRendered(String fieldName, FiltroInicioItem item) {
        //dependiendo del usuario, estado etc es si esta habilitado o no
        return fieldAttribute(fieldName, FieldAttributeEnum.RENDERED, item);
    }

    private boolean fieldAttribute(String fieldName, FieldAttributeEnum field) {
        return fieldAttribute(fieldName, field, null);
    }

    private boolean fieldAttribute(String fieldName, FieldAttributeEnum field, FiltroInicioItem item) {

        boolean checkDisabled = field == FieldAttributeEnum.DISABLED;
        boolean checkRendered = field == FieldAttributeEnum.RENDERED;

        boolean disabled = false;
        boolean rendered = true;

        boolean isPMOT = isUsuarioOrgaPMOT();
        boolean isPMFicha = item != null
                && (usuario.getUsuId().equals(item.getResponsableId())
                || usuario.getUsuId().equals(item.getAdjuntoId()));
        boolean isPMOFFicha = item != null && usuario.getUsuId().equals(item.getPmofId());
        boolean isSponsorFicha = item != null && usuario.getUsuId().equals(item.getSponsorId());

        boolean isProg = FichaUtils.isPrograma(item);
        boolean isProy = FichaUtils.isProyecto(item);

        boolean hasEstado = item != null && item.getEstado() != null;

        boolean isEstadoPendientes = hasEstado && item.getEstado().isPendientes();
        boolean isEstadoInicio = hasEstado && item.getEstado().isEstado(Estados.ESTADOS.INICIO.estado_id);
        boolean isEstadoPlanificacion = hasEstado && item.getEstado().isEstado(Estados.ESTADOS.PLANIFICACION.estado_id);
        boolean isEstadoEjecucion = hasEstado && item.getEstado().isEstado(Estados.ESTADOS.EJECUCION.estado_id);
        boolean isEstadoFinalizado = hasEstado && item.getEstado().isEstado(Estados.ESTADOS.FINALIZADO.estado_id);

        if (fieldName.equalsIgnoreCase("btnEditarProgProy")
                || fieldName.equalsIgnoreCase("btnEliminarProgProy")
                || fieldName.equalsIgnoreCase("btnAprobarProgProy")) {
            if (!(usuario.isUsuarioPMOT(this.organismo.getOrgPk()) || usuario.isUsuarioPMOF(this.organismo.getOrgPk()))) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("AprobarFicha")) {
            if (isProy && (isEstadoFinalizado
                    || (!(isPMOT
                    || (isPMFicha && (isEstadoInicio || isEstadoPlanificacion || isEstadoEjecucion)))
                    || ((isPMOFFicha && !isPMOT) && isEstadoEjecucion)))) {
                rendered = false;
            }
            if (isProg) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("retrocederEstadoFicha")) {
            if (isProy && (!(isPMOT || ((isPMOFFicha && !isPMOT) && isEstadoEjecucion))
                    || (isEstadoPendientes || isEstadoInicio))) {
                rendered = false;
            }
            if (isProg) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("replanDesc")) {
            if (!(isPMOFFicha || (isPMOT && (item == null || item.getEstadoPendiente() == null)))) {
                disabled = true;
            }
        }

        if (fieldName.equalsIgnoreCase("semaforoFase")) {
            if (!(isPMFicha || isPMOFFicha || isPMOT || isSponsorFicha)) {
                rendered = false;
            }
        }

        if (checkDisabled) {
            return disabled;
        } else if (checkRendered) {
            return rendered;
        } else {
            return false;
        }
    }

    /**
     * Si el usuario no tiene salvado el filtro, se inicializa con los valores
     * por defecto
     */
    public void filtroPorDefecto() {
        filtro = new FiltroInicioTO();

        List<Object> estadosDefecto = new ArrayList<>();
        estadosDefecto.add(Estados.ESTADOS.INICIO.estado_id);
        estadosDefecto.add(Estados.ESTADOS.PLANIFICACION.estado_id);
        estadosDefecto.add(Estados.ESTADOS.EJECUCION.estado_id);
        estadosDefecto.add(-100);
        filtro.setEstados(estadosDefecto);

        List<Object> riesgosDefecto = new ArrayList<>();
        riesgosDefecto.add("1");
        riesgosDefecto.add("2");
        riesgosDefecto.add("3");
        filtro.setGradoRiesgo(riesgosDefecto);

        List<Object> indicadorAvanceDefecto = new ArrayList<>();
        indicadorAvanceDefecto.add(1);
        indicadorAvanceDefecto.add(2);
        filtro.setIndicadorAvance(indicadorAvanceDefecto);

        listaPmoFederadaCombo.setSelected(-1);
        listaNivelItemsCombo.setSelected(1);
        listaSponsorCombo.setSelected(-1);
        listaAreasOrganismoCombo.setSelected(-1);
        listaGerenteCombo.setSelected(-1);
        listaOrganizacionCombo.setSelected(-1);
        listaIntRolCombo.setSelected(-1);
        listaIntAmbitoCombo.setSelected(-1);
        listaIntRolCombo.setSelected(-1);
        listaOrgaProvCombo.setSelected(-1);
        listaFuentesCombo.setSelected(-1);
        listaCalIndiceCombo.setSelected(-1);
        areasTematicas = null;
        areasTematicasStateMap = null;

        Configuracion confPorArea = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.FILTRO_INICIO_POR_AREAS, getOrganismo().getOrgPk());
        if (confPorArea != null) {
            filtro.setPorArea(new Boolean(confPorArea.getCnfValor()));
        }
    }

    private void cargarCombosFiltro() {
        Integer orgPk = getOrganismo().getOrgPk();

        List<Areas> listaAreas = areasDelegate.obtenerAreasPorOrganismo(orgPk);
        if (listaAreas != null) {
            listaAreasOrganismoCombo = new SofisCombo((List) listaAreas, "areaNombre");
            listaAreasOrganismoCombo.addEmptyItem(Labels.getValue("comboTodas"));
        }

        //la lista de usuarios con rol Director son los que se pueden seleccionar como sponsor.
        String[] ordenUsuarios = new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"};
        boolean[] ascUsuarios = new boolean[]{true, true, true, true};
        List<SsUsuario> listaSponsor = ssUsuarioDelegate.obtenerUsuariosPorRol(SsRolCodigos.DIRECTOR, orgPk, ordenUsuarios, ascUsuarios);
        if (listaSponsor != null) {
            listaSponsorCombo = new SofisCombo((List) listaSponsor, "usuNombreApellido");
            listaSponsorCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }

        //la lista de usuarios de la organizacion son los que se puede selecionar como adjunto.
        List<SsUsuario> listaAdjunto = ssUsuarioDelegate.obtenerTodosPorOrganismo(orgPk);
        if (listaAdjunto != null) {
            listaAdjuntoCombo = new SofisCombo((List) listaAdjunto, "usuNombreApellido");
            listaAdjuntoCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }

        //la lista de los usuarios de la organizacion, son los que se pueden seleccionar como gerente
        List<SsUsuario> listaGerente = ssUsuarioDelegate.obtenerTodosPorOrganismo(orgPk);
        if (listaGerente != null) {
            listaGerenteCombo = new SofisCombo((List) listaGerente, "usuNombreApellido");
            listaGerenteCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }

        //la lista de usuarios con rol PMO Federeda
        String[] rolCodArr = new String[]{SsRolCodigos.PMO_FEDERADA, SsRolCodigos.PMO_TRANSVERSAL};
        String[] ordenPmof = new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"};
        boolean[] ascending = new boolean[]{true, true, true, true};
        List<SsUsuario> listaPmoFederada = ssUsuarioDelegate.obtenerUsuariosPorRol(rolCodArr, orgPk, ordenPmof, ascending);
        listaPmoFederada = SsUsuariosUtils.sortByNombreApellido(listaPmoFederada);
        if (listaPmoFederada != null) {
            listaPmoFederadaCombo = new SofisCombo((List) listaPmoFederada, "usuNombreApellido");
            listaPmoFederadaCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }

        List<Ambito> listaAmbito = ambitoDelegate.obtenerAmbitoPorOrg(orgPk);
        if (listaAmbito != null) {
            listaIntAmbitoCombo = new SofisCombo((List) listaAmbito, "ambNombre");
            listaIntAmbitoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        //Lista para los combos de Interesados
        List<OrganiIntProve> listaOrganizacion = organiIntProveDelegate.obtenerOrganiIntProvePorOrgPk(orgPk, null);
        if (listaOrganizacion != null) {
            listaOrganizacionCombo = new SofisCombo((List) listaOrganizacion, "orgaNombre");
            listaOrganizacionCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

            listaOrgaProvCombo = new SofisCombo((List) listaOrganizacion, "orgaNombre");
            listaOrgaProvCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        List<RolesInteresados> listaRolesInteresados = rolesInteresadosDelegate.obtenerRolPorOrganizacionId(this.getOrganismoSeleccionado());
        if (listaRolesInteresados != null) {
            listaIntRolCombo = new SofisCombo((List) listaRolesInteresados, "rolintNombre");
            listaIntRolCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        List<FuenteFinanciamiento> listaFuente = fuenteFinanciamientoDelegate.obtenerFuentesPorOrgId(orgPk);
        if (listaFuente != null) {
            listaFuentesCombo = new SofisCombo((List) listaFuente, "fueNombre");
            listaFuentesCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        listaEstadosItem = new ArrayList<>();
        listaEstadosItem.add(new SelectItem(Estados.ESTADOS.INICIO.estado_id, Labels.getValue("estado_Inicio")));
        listaEstadosItem.add(new SelectItem(Estados.ESTADOS.PLANIFICACION.estado_id, Labels.getValue("estado_Planificacion")));
        listaEstadosItem.add(new SelectItem(Estados.ESTADOS.EJECUCION.estado_id, Labels.getValue("estado_Ejecucion")));
        listaEstadosItem.add(new SelectItem(Estados.ESTADOS.FINALIZADO.estado_id, Labels.getValue("estado_Finalizado")));

        List<NivelEnum> listaNivelItems = new ArrayList<>();
        listaNivelItems.add(new NivelEnum(1, Labels.getValue("filtro_nivel_1")));
        listaNivelItems.add(new NivelEnum(2, Labels.getValue("filtro_nivel_1_2")));
        listaNivelItemsCombo = new SofisCombo((List) listaNivelItems, "label");

        listaGradoRiesgoItems = new ArrayList<>();
        listaGradoRiesgoItems.add(new SelectItem(1, Labels.getValue("semaforo_bajo")));
        listaGradoRiesgoItems.add(new SelectItem(2, Labels.getValue("semaforo_medio")));
        listaGradoRiesgoItems.add(new SelectItem(3, Labels.getValue("semaforo_alto")));

        List<SelectItem> listaCalidadIndice = new ArrayList<>();
        listaCalidadIndice.add(new SelectItem(1, Labels.getValue("semaforo_alto")));
        listaCalidadIndice.add(new SelectItem(2, Labels.getValue("semaforo_medio")));
        listaCalidadIndice.add(new SelectItem(3, Labels.getValue("semaforo_bajo")));
        if (listaCalIndiceCombo.getAllObjects().isEmpty()) {
            listaCalIndiceCombo = new SofisCombo((List) listaCalidadIndice, "label");
            listaCalIndiceCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }

    }

    /**
     * Se cargan en el filtro los combos seleccionados para la busqueda.
     */
    public void obtenerCombosSeleccionados() {
        if (filtro != null) {
            filtro.setAreasOrganizacion((Areas) listaAreasOrganismoCombo.getSelectedObject());

            if (listaSponsorCombo.getSelectedObject() != null) {
                filtro.setSponsor(((SsUsuario) listaSponsorCombo.getSelectedObject()).getUsuId());
            } else {
                filtro.setSponsor(null);
            }

            if (listaGerenteCombo.getSelectedObject() != null) {
                filtro.setGerenteOAdjunto(((SsUsuario) listaGerenteCombo.getSelectedObject()).getUsuId());
            } else {
                filtro.setGerenteOAdjunto(null);
            }

            if (listaPmoFederadaCombo.getSelectedObject() != null) {
                filtro.setPmoFederada(((SsUsuario) listaPmoFederadaCombo.getSelectedObject()).getUsuId());
            } else {
                filtro.setPmoFederada(null);
            }

            if (listaNivelItemsCombo.getSelectedObject() != null) {
                filtro.setNivel(((NivelEnum) listaNivelItemsCombo.getSelectedObject()).getId());
            } else {
                filtro.setNivel(null);
            }

            if (listaOrganizacionCombo.getSelectedObject() != null) {
                filtro.setInteresadoOrganizacion((OrganiIntProve) listaOrganizacionCombo.getSelectedObject());
            } else {
                filtro.setInteresadoOrganizacion(null);
            }

            if (listaIntRolCombo.getSelectedObject() != null) {
                filtro.setInteresadoRol(((RolesInteresados) listaIntRolCombo.getSelectedObject()).getRolintPk());
            } else {
                filtro.setInteresadoRol(null);
            }

            if (listaIntAmbitoCombo.getSelectedObject() != null) {
                filtro.setInteresadoAmbitoOrganizacion((Ambito) listaIntAmbitoCombo.getSelectedObject());
            } else {
                filtro.setInteresadoAmbitoOrganizacion(null);
            }

            if (areasTematicasStateMap != null && areasTematicasStateMap.getSelected() != null) {
                setAreasTematicasToFiltro();
            } else {
                filtro.setAreasTematicas(null);
            }

            if (listaOrgaProvCombo.getSelectedObject() != null) {
                filtro.setOrgaProveedor((OrganiIntProve) listaOrgaProvCombo.getSelectedObject());
            } else {
                filtro.setOrgaProveedor(null);
            }

            if (listaFuentesCombo.getSelectedObject() != null) {
                filtro.setFuenteFinanciamiento((FuenteFinanciamiento) listaFuentesCombo.getSelectedObject());
            } else {
                filtro.setFuenteFinanciamiento(null);
            }

            if (listaCalIndiceCombo.getSelectedObject() != null) {
                Integer ind = (Integer) ((SelectItem) listaCalIndiceCombo.getSelectedObject()).getValue();
                filtro.setCalidadIndice(ind);
            } else {
                filtro.setCalidadIndice(null);
            }
        }
    }

    /**
     * Marcar los combos con los valores definidos.
     */
    public void setSelectedCombosFiltro() {
        if (filtro.getSponsor() != null) {
            listaSponsorCombo.setSelected(filtro.getSponsor());
        }
        if (filtro.getGerenteOAdjunto() != null) {
            listaGerenteCombo.setSelected(filtro.getGerenteOAdjunto());
        }
        if (filtro.getPmoFederada() != null) {
            listaPmoFederadaCombo.setSelected(filtro.getPmoFederada());
        }
        if (filtro.getNivel() != null) {
            listaNivelItemsCombo.setSelected(filtro.getNivel());
        }
        if (filtro.getAreasOrganizacion() != null) {
            listaAreasOrganismoCombo.setSelected(filtro.getAreasOrganizacion().getAreaPk());
        }
        if (filtro.getInteresadoOrganizacion() != null) {
            listaOrganizacionCombo.setSelected(filtro.getInteresadoOrganizacion().getOrgaPk());
        }
        if (filtro.getInteresadoAmbitoOrganizacion() != null) {
            listaIntAmbitoCombo.setSelected(filtro.getInteresadoAmbitoOrganizacion().getAmbPk());
        }
        if (filtro.getOrgaProveedor() != null) {
            listaOrgaProvCombo.setSelected(filtro.getOrgaProveedor().getOrgaPk());
        }
        if (filtro.getFuenteFinanciamiento() != null) {
            listaFuentesCombo.setSelected(filtro.getFuenteFinanciamiento().getFuePk());
        }
        if (filtro.getCalidadIndice() != null) {
            listaCalIndiceCombo.setSelected(filtro.getCalidadIndice());
        }
        if (CollectionsUtils.isNotEmpty(filtro.getAreasTematicas())) {
            areasTematicas = new HashSet<>(filtro.getAreasTematicas());
            areaTematicaPopup(Boolean.FALSE);
        }
    }

    public void cargarInteresadosRolSelectAction(ValueChangeEvent event) {
        PhaseId phaseId = event.getPhaseId();
        if (phaseId.equals(PhaseId.ANY_PHASE)) {
            event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
            event.queue();
        } else if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES)) {
//            if (listaOrganizacionCombo.getSelectedObject() != null) {
//                Integer orgaId = ((OrganiIntProve) listaOrganizacionCombo.getSelectedObject()).getOrgaPk();
//                listaRolesInteresados = rolesInteresadosDelegate.obtenerRolPorOrganizacionId(this.getOrganismoSeleccionado());
//                if (listaRolesInteresados != null && !listaRolesInteresados.isEmpty()) {
//                    listaIntRolCombo = new SofisCombo((List) listaRolesInteresados, "rolintNombre");
//                    listaIntRolCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
//                }
//            }
        }
    }

    public String areaTematicaPopup(Boolean renderPopup) {
        logger.fine("areaTematicaPopup.");
        try {
            renderPopupAreaTematica = renderPopup != null ? renderPopup : true;
            List<AreasTags> listaAreasTags = areaTematicaDelegate.obtenerAreasTematicasPorOrganizacion(getOrganismo().getOrgPk());
            if (listaAreasTags != null && !listaAreasTags.isEmpty() && areasTematicasStateMap == null) {
                listaAreasTagsTreeNode = new ArrayList<>();
                Map<String, Object> mapAreaTag = WebUtils.setNodosForAreaTematica(listaAreasTags, listaAreasTagsTreeNode, areasTematicas, areasTematicasStateMap);
                listaAreasTagsTreeNode = (List<MutableTreeNode>) mapAreaTag.get(WebUtils.LISTA_AREAS_TAG_TREE_NODE);
                areasTematicasStateMap = (NodeStateMap) mapAreaTag.get(WebUtils.AREAS_TEMATICAS_STATE_MAP);
            }
        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    public void setAreasTematicasToFiltro() {
        if (areasTematicasStateMap != null) {
            List lista = areasTematicasStateMap.getSelected();
            if (lista != null && lista.size() > 0) {
                filtro.setAreasTematicas(new ArrayList<AreasTags>());
                for (Object object : lista) {
                    if (object instanceof DefaultMutableTreeNode) {
                        DefaultMutableTreeNode d = (DefaultMutableTreeNode) object;
                        AreasTags at = (AreasTags) d.getUserObject();
                        filtro.getAreasTematicas().add(at);
                    }
                }
            } else {
                filtro.setAreasTematicas(null);
            }
        }
    }

    public String cerrarPopupAreaTematica() {
        try {
            renderPopupAreaTematica = false;
        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    public String abrirPopupMensajes() {
        renderPopupMensajes = true;
        return null;
    }

    public String cerrarPopupMensajes() {
        renderPopupMensajes = false;
        return null;
    }

    public String datosPrincipal() {
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

        if (principal == null) {
            usuario = null;
        }
        if (usuario != null && !principal.getName().equalsIgnoreCase(usuario.getUsuCorreoElectronico())) {
            usuario = null;
        }

        if (principal != null && usuario == null) {
            //Inicializa el usuario
            datUsuario.setCodigoUsuario(principal.getName());
            datUsuario.setOrigen("SIGES_WEB");
            nombreUsuario = datUsuario.getCodigoUsuario();

            usuario = ssUsuarioDelegate.obtenerUsuarioPorMail(datUsuario.getCodigoUsuario());
            if (usuario == null) {
                usuario = ssUsuarioDelegate.obtenerUsuarioPorCodigo(datUsuario.getCodigoUsuario());
            }

            if (usuario != null) {
                nombreUsuario = usuario.getUsuNombreApellido();
                cargarComboOrganismos();
                //Inicializa los combos de la busqueda
                if (this.organismo != null) {
                    cargarCombosFiltro();
                    inicializarFiltro();
                }

                ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).setAttribute("datUsuario", datUsuario);
            } else {
                organismosUsuario = new SofisCombo(new ArrayList(), "orgNombre");
            }
        } else {
            return ConstantesNavegacion.IR_A_INICIO;
        }

        return null;
    }

    private void cargarComboOrganismos() {
        if (usuario != null) {
            if (usuario.isAdministrador()) {
                List<Organismos> todosOrganismos = organismoDelegate.obtenerTodos();
                organismosUsuario = new SofisCombo((List)todosOrganismos, "orgNombre");
                if (todosOrganismos != null && !todosOrganismos.isEmpty()) {
                    this.organismo = todosOrganismos.get(0);
                    organismosUsuario.setSelected(this.organismo.getOrgPk());
                }
            } else {
                //Inicializa los organismos del usuario
                Set<SsUsuOfiRoles> oficinasRoles = usuario.getSsUsuOfiRolesCollectionActivos();

                // Cargar Organismo
                HashMap<Integer, Organismos> listaOrganismosUsuario = new HashMap<>();
                if (oficinasRoles != null) {
                    for (SsUsuOfiRoles ofRoles : oficinasRoles) {
                        if (ofRoles.getUsuOfiRolesOficina() != null) {
                            listaOrganismosUsuario.put(ofRoles.getUsuOfiRolesOficina().getOfiId(), new Organismos(ofRoles.getUsuOfiRolesOficina().getOfiId(), ofRoles.getUsuOfiRolesOficina().getOfiNombre()));
                        }
                    }
                }

                if (!listaOrganismosUsuario.isEmpty()) {
                    organismosUsuario = new SofisCombo(new ArrayList(listaOrganismosUsuario.values()), "orgNombre");
                    if (this.organismo == null) {
                        Organismos orgDefecto = organismoDelegate.obtenerOrgPorId(usuario.getUsuOficinaPorDefecto());
                        List<Integer> orgListId = new ArrayList<>();
                        for (SsUsuOfiRoles ssUsuOfiRoles : oficinasRoles) {
                            orgListId.add(ssUsuOfiRoles.getUsuOfiRolesOficina().getOfiId());
                        }
                        if (orgDefecto != null && orgListId.contains(orgDefecto.getOrgPk())) {
                            this.organismo = orgDefecto;
                        } else {
                            for (Map.Entry<Integer, Organismos> e : listaOrganismosUsuario.entrySet()) {
                                if (e.getValue() != null) {
                                    this.organismo = e.getValue();
                                    break;
                                }
                            }
                        }
                    }

                    organismosUsuario.setSelected(this.organismo.getOrgPk());
                } else {
                    organismosUsuario = new SofisCombo(new ArrayList(), "orgNombre");
                    //throw new BusinessException("No se encuentran organismos definidos");
                }
            }
        }
    }

    public void cargarOrganismoSeleccionado() {
        if (getOrganismoSeleccionado() != null) {
            setOrganismo(organismoDelegate.obtenerOrgPorId(getOrganismoSeleccionado()));
        }

        datosPrincipal();
    }

    public void organismoChange(ValueChangeEvent event) {
        PhaseId phaseId = event.getPhaseId();
        if (phaseId.equals(PhaseId.ANY_PHASE)) {
            event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
            event.queue();
        } else if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES)) {
            Organismos org = (Organismos) this.organismosUsuario.getSelectedObject();
            if (org != null) {
                this.organismo = organismoDelegate.obtenerOrgPorId(org.getOrgPk());
                cargarCombosFiltro();
            }
            inicializarFiltro();
            //TODO: Se tendria que llamar a un inicializar al haber cambiado el organismo.
        }
    }

    public boolean isUsuarioOrgaPMO() {
        return isUsuarioOrgaPMOF() || isUsuarioOrgaPMOT();
    }

    /**
     * Valida si el usuario logueado tiene el rol de PMOT.
     *
     * @return true si es PMOT.
     */
    public boolean isUsuarioOrgaPMOT() {
        return getUsuario().isUsuarioPMOT(this.organismo.getOrgPk());
    }

    public boolean isUsuarioOrgaPMOF() {
        return getUsuario().isUsuarioPMOF(this.organismo.getOrgPk());
    }

    public boolean isUsuarioAdmin() {
        return getUsuario().isAdministrador();
    }

    private void redirectUsuarioPage() {
        if (usuario != null && organismo != null) {
            if (usuario.isRol(SsRolCodigos.REGISTRO_HORAS, organismo.getOrgPk())
                    && !usuario.isRol(SsRolCodigos.USUARIO_COMUN, organismo.getOrgPk())) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("registroHoras.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(InicioMB.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (usuario.isRol(SsRolCodigos.ADMINISTRADOR, organismo.getOrgPk())) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("organismo.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(InicioMB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Retorna true si el string aportado coincide con la página en la que está.
     *
     * @param s
     * @return boolean
     */
    public boolean isPath(String s) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String path = ctx.getRequestServletPath();
        //Quitar la extension
        path = path.substring(0, path.lastIndexOf('.'));
        //Quedarse solo con la ultima parte
        path = path.substring(path.lastIndexOf('/') + 1);
        return s.equals(path);
    }
}
