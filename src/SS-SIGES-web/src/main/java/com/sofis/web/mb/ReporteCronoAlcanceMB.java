package com.sofis.web.mb;

import com.icesoft.faces.context.effects.JavascriptContext;
import com.sofis.business.utils.OrganiIntProveUtils;
import com.sofis.entities.codigueras.SsRolCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FiltroReporteTO;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.web.delegates.AreaTematicaDelegate;
import com.sofis.web.delegates.AreasDelegate;
import com.sofis.web.delegates.FuenteFinanciamientoDelegate;
import com.sofis.web.delegates.OrganiIntProveDelegate;
import com.sofis.web.delegates.PresupuestoDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.delegates.ReporteCronoAlcanceDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisComboG;
import com.sofis.web.utils.WebUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
@ManagedBean(name = "reporteCronoAlcanceMB")
@ViewScoped
public class ReporteCronoAlcanceMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final String REPORTE_CRONO_MSG = "reporteCronoMsg";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @ManagedProperty("#{aplicacionMB}")
    private AplicacionMB aplicacionMB;

    @Inject
    private AreasDelegate areasDelegate;
    @Inject
    private AreaTematicaDelegate areaTematicaDelegate;
    @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;
    @Inject
    private OrganiIntProveDelegate organiIntProveDelegate;
    @Inject
    private FuenteFinanciamientoDelegate fuenteFinanciamientoDelegate;
    @Inject
    private ProyectosDelegate proyectoDelegate;
    @Inject
    private ReporteCronoAlcanceDelegate reporteCronoAlcanceDelegate;
    @Inject
    private PresupuestoDelegate presupuestoDelegate;

    // Variables
    //1-Moneda, 2-Adquisición
    private List<SelectItem> listaEstados;
    //Adquisiciones, Horas Colaboradores, Gastos o Devengamiento.
    private SofisComboG<Areas> areaCombo;
    private SofisComboG<SsUsuario> gerenteAdjuntoCombo;
    private SofisComboG<SsUsuario> sponsorCombo;
    private SofisComboG<SsUsuario> pmofCombo;
    private SofisComboG<SelectItem> anioCombo;
    private SofisComboG<AreasTags> areaTematicaCombo;
    private SofisComboG<OrganiIntProve> proveedorCombo;
    private SofisComboG<FuenteFinanciamiento> fuenteFinancCombo;
    private FiltroReporteTO filtro = new FiltroReporteTO();
    //Area Tematica
    private Boolean renderPopupAreaTematica = false;
    private List<MutableTreeNode> listaAreasTagsTreeNode = new ArrayList<>();
    private NodeStateMap areasTematicasStateMap = null;
    private Set<AreasTags> areasTematicas;

    public ReporteCronoAlcanceMB() {
    }

    public void setAplicacionMB(AplicacionMB aplicacionMB) {
        this.aplicacionMB = aplicacionMB;
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public SofisComboG<Areas> getAreaCombo() {
        return areaCombo;
    }

    public void setAreaCombo(SofisComboG<Areas> areaCombo) {
        this.areaCombo = areaCombo;
    }

    public SofisComboG<SsUsuario> getGerenteAdjuntoCombo() {
        return gerenteAdjuntoCombo;
    }

    public void setGerenteAdjuntoCombo(SofisComboG<SsUsuario> gerenteAdjuntoCombo) {
        this.gerenteAdjuntoCombo = gerenteAdjuntoCombo;
    }

    public SofisComboG<SsUsuario> getSponsorCombo() {
        return sponsorCombo;
    }

    public void setSponsorCombo(SofisComboG<SsUsuario> sponsorCombo) {
        this.sponsorCombo = sponsorCombo;
    }

    public SofisComboG<SsUsuario> getPmofCombo() {
        return pmofCombo;
    }

    public void setPmofCombo(SofisComboG<SsUsuario> pmofCombo) {
        this.pmofCombo = pmofCombo;
    }

    public SofisComboG<SelectItem> getAnioCombo() {
        return anioCombo;
    }

    public void setAnioCombo(SofisComboG<SelectItem> anioCombo) {
        this.anioCombo = anioCombo;
    }

    public List<SelectItem> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<SelectItem> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public SofisComboG<AreasTags> getAreaTematicaCombo() {
        return areaTematicaCombo;
    }

    public void setAreaTematicaCombo(SofisComboG<AreasTags> areaTematicaCombo) {
        this.areaTematicaCombo = areaTematicaCombo;
    }

    public SofisComboG<OrganiIntProve> getProveedorCombo() {
        return proveedorCombo;
    }

    public void setProveedorCombo(SofisComboG<OrganiIntProve> proveedorCombo) {
        this.proveedorCombo = proveedorCombo;
    }

    public SofisComboG<FuenteFinanciamiento> getFuenteFinancCombo() {
        return fuenteFinancCombo;
    }

    public void setFuenteFinancCombo(SofisComboG<FuenteFinanciamiento> fuenteFinancCombo) {
        this.fuenteFinancCombo = fuenteFinancCombo;
    }

    public FiltroReporteTO getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroReporteTO filtro) {
        this.filtro = filtro;
    }

    public Boolean getRenderPopupAreaTematica() {
        return renderPopupAreaTematica;
    }

    public void setRenderPopupAreaTematica(Boolean renderPopupAreaTematica) {
        this.renderPopupAreaTematica = renderPopupAreaTematica;
    }

    public List<MutableTreeNode> getListaAreasTagsTreeNode() {
        return listaAreasTagsTreeNode;
    }

    public void setListaAreasTagsTreeNode(List<MutableTreeNode> listaAreasTagsTreeNode) {
        this.listaAreasTagsTreeNode = listaAreasTagsTreeNode;
    }

    public NodeStateMap getAreasTematicasStateMap() {
        return areasTematicasStateMap;
    }

    public void setAreasTematicasStateMap(NodeStateMap areasTematicasStateMap) {
        this.areasTematicasStateMap = areasTematicasStateMap;
    }

    public Set<AreasTags> getAreasTematicas() {
        return areasTematicas;
    }

    public void setAreasTematicas(Set<AreasTags> areasTematicas) {
        this.areasTematicas = areasTematicas;
    }

    @PostConstruct
    public void init() {
        inicioMB.cargarOrganismoSeleccionado();

        cargarCombosFiltro();
    }

    private void cargarCombosFiltro() {
        Integer orgPk = inicioMB.getOrganismo().getOrgPk();

        listaEstados = new ArrayList<>();
        listaEstados.add(new SelectItem(Estados.ESTADOS.INICIO.estado_id, Labels.getValue("estado_Inicio")));
        listaEstados.add(new SelectItem(Estados.ESTADOS.PLANIFICACION.estado_id, Labels.getValue("estado_Planificacion")));
        listaEstados.add(new SelectItem(Estados.ESTADOS.EJECUCION.estado_id, Labels.getValue("estado_Ejecucion")));
        listaEstados.add(new SelectItem(Estados.ESTADOS.FINALIZADO.estado_id, Labels.getValue("estado_Finalizado")));

        List<Areas> listaAreas = areasDelegate.obtenerAreasPorOrganismo(orgPk, false);
//        List<Areas> listaAreas = aplicacionMB.obtenerAreasPorOrganismo(orgPk);
        if (listaAreas != null) {
            areaCombo = new SofisComboG(listaAreas, "areaNombre");
            areaCombo.addEmptyItem(Labels.getValue("comboTodas"));
        }

        //la lista de los usuarios de la organizacion, son los que se pueden seleccionar como gerente o adjunto.
        //List<SsUsuario> listaGerente = ssUsuarioDelegate.obtenerTodosPorOrganismo(orgPk);
        List<SsUsuario> listaGerente = new ArrayList(aplicacionMB.obtenerTodosPorOrganismoActivos(orgPk));
        List<SsUsuario> listaInactivos = ssUsuarioDelegate.obtenerInactivosPorOrganismo(orgPk);
        listaGerente.addAll(listaInactivos);
        listaGerente = SsUsuariosUtils.sortByNombreApellido(listaGerente);
        if (listaGerente != null) {
            gerenteAdjuntoCombo = new SofisComboG(listaGerente, "usuNombreApellido");
            gerenteAdjuntoCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }

        //la lista de usuarios con rol Director son los que se pueden seleccionar como sponsor.
        String[] ordenUsuarios = new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"};
        boolean[] ascUsuarios = new boolean[]{true, true, true, true};
        List<SsUsuario> listaSponsor = ssUsuarioDelegate.obtenerUsuariosPorRol(SsRolCodigos.DIRECTOR, orgPk, ordenUsuarios, ascUsuarios);
        if (listaSponsor != null) {
            sponsorCombo = new SofisComboG(listaSponsor, "usuNombreApellido");
            sponsorCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }

        //la lista de usuarios con rol PMO Federeda
        String[] rolCodArr = new String[]{SsRolCodigos.PMO_FEDERADA, SsRolCodigos.PMO_TRANSVERSAL};
        List<SsUsuario> listaPmoFederada = ssUsuarioDelegate.obtenerUsuariosPorRol(rolCodArr, orgPk, ordenUsuarios, ascUsuarios);
        listaPmoFederada = SsUsuariosUtils.sortByNombreApellido(listaPmoFederada);
        if (listaPmoFederada != null) {
            pmofCombo = new SofisComboG((List) listaPmoFederada, "usuNombreApellido");
            pmofCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }

//        Calendar cal = new GregorianCalendar();
//        Calendar calPrimera = new GregorianCalendar();
//        calPrimera.setTime(proyectoDelegate.obtenerPrimeraFecha());
//        int primerAnio = calPrimera != null && calPrimera.get(Calendar.YEAR) < cal.get(Calendar.YEAR) ? calPrimera.get(Calendar.YEAR) : cal.get(Calendar.YEAR);
//        Calendar calUltima = new GregorianCalendar();
//        calUltima.setTime(proyectoDelegate.obtenerUltimaFecha());
//        int ultimoAnio = calUltima != null && calUltima.get(Calendar.YEAR) > cal.get(Calendar.YEAR) ? calUltima.get(Calendar.YEAR) : cal.get(Calendar.YEAR);

        Calendar cal = new GregorianCalendar();
        Calendar calPrimera = null;
        Date primeraDate = presupuestoDelegate.obtenerPrimeraFecha();
        if (primeraDate != null) {
            calPrimera = new GregorianCalendar();
            calPrimera.setTime(primeraDate);
        }
        int primerAnio = calPrimera != null && calPrimera.get(Calendar.YEAR) < cal.get(Calendar.YEAR) ? calPrimera.get(Calendar.YEAR) : cal.get(Calendar.YEAR);

        Calendar calUltima = null;
        Date ultimaDate = presupuestoDelegate.obtenerUltimaFecha();
        if (ultimaDate != null) {
            calUltima = new GregorianCalendar();
            calUltima.setTime(ultimaDate);
        }
        int ultimoAnio = calUltima != null && calUltima.get(Calendar.YEAR) > cal.get(Calendar.YEAR) ? calUltima.get(Calendar.YEAR) : cal.get(Calendar.YEAR);

        List<SelectItem> listaAnios = new ArrayList<>();
        SelectItem itemActual = null;
        for (int i = primerAnio; i <= ultimoAnio; i++) {
            SelectItem item = new SelectItem(i, "" + i);
            listaAnios.add(item);
            if (i == cal.get(Calendar.YEAR)) {
                itemActual = item;
            }
        }

        anioCombo = new SofisComboG(listaAnios, "label");
        anioCombo.setSelectedT(itemActual);

        List<OrganiIntProve> listaOrganizacion = organiIntProveDelegate.obtenerOrganiIntProvePorOrgPk(orgPk, true);
        listaOrganizacion = OrganiIntProveUtils.sortByNombre(listaOrganizacion);
        if (listaOrganizacion != null) {
            proveedorCombo = new SofisComboG(listaOrganizacion, "orgaNombre");
            proveedorCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }

        List<FuenteFinanciamiento> listaFuente = fuenteFinanciamientoDelegate.obtenerFuentesPorOrgId(orgPk);
        if (listaFuente != null) {
            fuenteFinancCombo = new SofisComboG((List) listaFuente, "fueNombre");
            fuenteFinancCombo.addEmptyItem(Labels.getValue("comboTodas"));
        }
    }

    public String areaTematicaPopup(Boolean renderPopup) {
        logger.fine("areaTematicaPopup.");
        try {
            renderPopupAreaTematica = renderPopup != null ? renderPopup : true;
            List<AreasTags> listaAreasTags = areaTematicaDelegate.obtenerAreasTematicasPorOrganizacion(inicioMB.getOrganismo().getOrgPk());
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

    public boolean areasTematicasHasValues() {
        if (areasTematicasStateMap == null) {
            return areasTematicas != null ? !areasTematicas.isEmpty() : false;
        }
        return !getAreasTematicasSelected().isEmpty();
    }

    public List getAreasTematicasSelected() {
        if (areasTematicasStateMap == null) {
            return Collections.emptyList();
        }
        return areasTematicasStateMap.getSelected();
    }

    public String limpiarFiltroAction() {
        filtro = new FiltroReporteTO();
        cargarCombosFiltro();
        return null;
    }

    public String exportarAlcanceAction() {
        byte[] planilla = null;

        setAreasTematicasToFiltro();
        cargarCombosSeleccionados();

        try {
            planilla = reporteCronoAlcanceDelegate.generarReportePlanillaPorFiltro(inicioMB.getOrganismo().getOrgPk(), filtro, inicioMB.getUsuario());
        } catch (BusinessException be) {
            logger.log(Level.WARNING, null, be);
            JSFUtils.agregarMsgs(REPORTE_CRONO_MSG, be.getErrores());
        }

        if (planilla != null && planilla.length > 0) {
            Calendar cal = new GregorianCalendar();
            String horaString = String.format("%d_%d_%d_%d_%d_%d", cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
            String fileName = "Reporte_Cronograma_Alcance_Hitos" + horaString + ".xls";
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
                session.setAttribute("bytes", planilla);
                session.setAttribute("fileName", fileName);
                HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String servlet = req.getContextPath() + "/servlet/descargar?sesId=" + session.getId();
                JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "window.open('" + servlet + "','Descargar');");
            }
        } else {
            JSFUtils.agregarMsgWarn("", "Sin datos para el reporte.", null);
        }

        return null;
    }

    private void cargarCombosSeleccionados() {
        if (areaCombo.getSelectedT() != null) {
            filtro.setArea(areaCombo.getSelectedT());
        }

        if (gerenteAdjuntoCombo.getSelectedT() != null) {
            filtro.setGerenteAdjunto(gerenteAdjuntoCombo.getSelectedT());
        }
        if (sponsorCombo.getSelectedT() != null) {
            filtro.setSponsor(sponsorCombo.getSelectedT());
        }
        if (pmofCombo.getSelectedT() != null) {
            filtro.setPmof(pmofCombo.getSelectedT());
        }
        if (anioCombo.getSelectedT() != null) {
            Integer anio = (Integer) anioCombo.getSelectedT().getValue();
            filtro.setAnio(anio);
            filtro.setAnioCronograma(anio);
            filtro.setAnioBaseCronograma(anio);
        }
        if (proveedorCombo.getSelectedT() != null) {
            filtro.setProveedor(proveedorCombo.getSelectedT());
        }
        if (fuenteFinancCombo.getSelectedT() != null) {
            filtro.setFuenteFinanc(fuenteFinancCombo.getSelectedT());
        }
    }

    public void setAreasTematicasToFiltro() {
        if (areasTematicasStateMap != null) {
            List lista = areasTematicasStateMap.getSelected();
            if (lista != null && !lista.isEmpty()) {
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

    public boolean areasTematicasStateMapHasValues() {
        return areasTematicasStateMap != null && !areasTematicasStateMap.isEmpty();
    }

    public String cerrarPopupAreaTematica() {
        renderPopupAreaTematica = false;
        return null;
    }

    //spio
    public String exportarCronogramaAction() {
        byte[] planilla = null;

        setAreasTematicasToFiltro();
        cargarCombosSeleccionados();

        try {
            planilla = reporteCronoAlcanceDelegate.exportarCronogramaAction(inicioMB.getOrganismo().getOrgPk(), filtro, inicioMB.getUsuario());
        } catch (BusinessException be) {
            logger.log(Level.WARNING, null, be);
            JSFUtils.agregarMsgs(REPORTE_CRONO_MSG, be.getErrores());
        }

        if (planilla != null && planilla.length > 0) {
            Calendar cal = new GregorianCalendar();
            String horaString = String.format("%d_%d_%d_%d_%d_%d", cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
            String fileName = "Reporte_Cronograma_Alcance_Hitos" + horaString + ".xls";
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
                session.setAttribute("bytes", planilla);
                session.setAttribute("fileName", fileName);
                HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String servlet = req.getContextPath() + "/servlet/descargar?sesId=" + session.getId();
                JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "window.open('" + servlet + "','Descargar');");
            }
        } else {
            JSFUtils.agregarMsgWarn("", "Sin datos para el reporte.", null);
        }

        return null;
    }


}
