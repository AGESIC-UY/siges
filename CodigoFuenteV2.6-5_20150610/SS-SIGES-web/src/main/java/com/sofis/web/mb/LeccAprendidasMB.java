package com.sofis.web.mb;

import com.icesoft.faces.component.tree.Tree;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.AreaConocimiento;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.LeccionesAprendidas;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.TipoLeccion;
import com.sofis.entities.tipos.FiltroLeccAprTO;
import com.sofis.entities.tipos.LeccAprendidasTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.web.delegates.AreaTematicaDelegate;
import com.sofis.web.delegates.AreasConocimientoDelegate;
import com.sofis.web.delegates.LeccAprDelegate;
import com.sofis.web.delegates.OrganiIntProveDelegate;
import com.sofis.web.delegates.ProgramasDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.delegates.TipoLeccionDelegate;
import com.sofis.web.enums.FieldAttributeEnum;
import com.sofis.web.genericos.constantes.ConstantesPresentacion;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisCombo;
import com.sofis.web.utils.WebUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import org.icefaces.ace.component.autocompleteentry.AutoCompleteEntry;
import org.icefaces.ace.model.tree.NodeStateMap;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "LeccAprendidasMB")
@ViewScoped
public class LeccAprendidasMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private final static String LECC_APRENDIDAS_MSG = "frmCargo:leccAprendidasResultadoBusqueda";
    private final static String LECC_APRENDIDAS_FORM_MSG = "frmCargo:btnGuardar";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private ProgramasDelegate programasDelegate;
    @Inject
    private ProyectosDelegate proyectosDelegate;
    @Inject
    private LeccAprDelegate leccAprDelegate;
    @Inject
    private TipoLeccionDelegate tipoLeccionDelegate;
    @Inject
    private AreaTematicaDelegate areaTematicaDelegate;
    @Inject
    private OrganiIntProveDelegate organiIntProveDelegate;
    @Inject
    private AreasConocimientoDelegate areasConocimientoDelegate;

    //Variables
    private int cantElementosPorPagina = 20;
    private boolean filtroRender;
    private FiltroLeccAprTO filtro;
    private List<TipoLeccion> listaTipoLecc;
    private List<OrganiIntProve> listaOrganizacion;
    private List<OrganiIntProve> listaOrgProveedores;
    private List<Programas> listaProgramas;
    private SofisCombo listaTipoLeccCombo;
    private SofisCombo listaTipoLeccComboForm;
    private SofisCombo listaOrganizacionCombo;
    private SofisCombo listaOrgaProvCombo;
    private SofisCombo listaProgramasCombo;
    private SofisCombo listaProyectosCombo;
    private SofisCombo listaProyectosComboForm;
    private List<LeccAprendidasTO> leccAprendidasResultado;
    private Boolean renderPopupLeccion = false;
    private LeccionesAprendidas leccAprePopup;
    // Form Alta/Modificacion
    private Boolean leccApreFormDataExpanded;
    private LeccionesAprendidas leccAprendida;
    private List<Proyectos> autoCompleteProyectosList;
    private AutoCompleteEntry autoCompleteProyectosComponent;
    // Areas Conocimiento Filtro
    private boolean renderPopupAreaConFiltro;
    private List<AreaConocimiento> listaAreasConFiltro;
    private List<MutableTreeNode> listaAreasConTreeNodeFiltro;
    private Set<AreaConocimiento> areasConFiltro;
    private NodeStateMap areasConStateMapFiltro;
    // Areas Conocimiento Form
    private boolean renderPopupAreaCon;
    private List<AreaConocimiento> listaAreasCon;
    private List<MutableTreeNode> listaAreasConTreeNode;
    private Set<AreaConocimiento> areasCon;
    private NodeStateMap areasConStateMap;
    // Areas Tematicas
    private boolean renderPopupAreaTematica;
    private List<AreasTags> listaAreasTags;
    private List<MutableTreeNode> listaAreasTagsTreeNode;
    private Set<AreasTags> areasTematicas;
    private NodeStateMap areasTematicasStateMap;
    //Popup Message
    private Boolean renderPopupMessage = false;
    private Tree treeAreasCon;

    public Tree getTreeAreasCon() {
        return treeAreasCon;
    }

    public void setTreeAreasCon(Tree treeAreasCon) {
        this.treeAreasCon = treeAreasCon;
    }

    public LeccAprendidasMB() {
        filtroRender = false;
        filtro = new FiltroLeccAprTO();
        listaTipoLecc = new ArrayList<>();
        leccApreFormDataExpanded = false;
        leccAprendidasResultado = new ArrayList<>();
        leccAprendida = new LeccionesAprendidas();

        listaOrganizacion = new ArrayList<>();
        listaProgramas = new ArrayList<>();
        listaTipoLeccCombo = new SofisCombo();
        listaTipoLeccComboForm = new SofisCombo();
        listaOrganizacionCombo = new SofisCombo();
        listaOrgaProvCombo = new SofisCombo();
        listaProgramasCombo = new SofisCombo();
        listaProyectosCombo = new SofisCombo();
        listaProyectosComboForm = new SofisCombo();

        autoCompleteProyectosList = new ArrayList<>();
        autoCompleteProyectosComponent = new AutoCompleteEntry();

        renderPopupAreaCon = false;
        listaAreasCon = new ArrayList<>();
        listaAreasConTreeNode = new ArrayList<>();
        areasCon = new HashSet<>();

        renderPopupAreaConFiltro = false;
        listaAreasConFiltro = new ArrayList<>();
        listaAreasConTreeNodeFiltro = new ArrayList<>();
        areasConFiltro = new HashSet<>();

        renderPopupAreaTematica = false;
        listaAreasTags = new ArrayList<>();
        listaAreasTagsTreeNode = new ArrayList<>();
        areasTematicas = new HashSet<>();

    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public int getCantElementosPorPagina() {
        return cantElementosPorPagina;
    }

    public void setCantElementosPorPagina(int cantElementosPorPagina) {
        this.cantElementosPorPagina = cantElementosPorPagina;
    }

    public boolean isFiltroRender() {
        return filtroRender;
    }

    public void setFiltroRender(boolean filtroRender) {
        this.filtroRender = filtroRender;
    }

    public FiltroLeccAprTO getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroLeccAprTO filtro) {
        this.filtro = filtro;
    }

    public List<TipoLeccion> getListaTipoLecc() {
        return listaTipoLecc;
    }

    public void setListaTipoLecc(List<TipoLeccion> listaTipoLecc) {
        this.listaTipoLecc = listaTipoLecc;
    }

    public SofisCombo getListaTipoLeccCombo() {
        return listaTipoLeccCombo;
    }

    public void setListaTipoLeccCombo(SofisCombo listaTipoLeccCombo) {
        this.listaTipoLeccCombo = listaTipoLeccCombo;
    }

    public boolean isRenderPopupAreaCon() {
        return renderPopupAreaCon;
    }

    public void setRenderPopupAreaCon(boolean renderPopupAreaCon) {
        this.renderPopupAreaCon = renderPopupAreaCon;
    }

    public boolean isRenderPopupAreaTematica() {
        return renderPopupAreaTematica;
    }

    public void setRenderPopupAreaTematica(boolean renderPopupAreaTematica) {
        this.renderPopupAreaTematica = renderPopupAreaTematica;
    }

    public List<OrganiIntProve> getListaOrganizacion() {
        return listaOrganizacion;
    }

    public void setListaOrganizacion(List<OrganiIntProve> listaOrganizacion) {
        this.listaOrganizacion = listaOrganizacion;
    }

    public SofisCombo getListaOrganizacionCombo() {
        return listaOrganizacionCombo;
    }

    public void setListaOrganizacionCombo(SofisCombo listaOrganizacionCombo) {
        this.listaOrganizacionCombo = listaOrganizacionCombo;
    }

    public SofisCombo getListaOrgaProvCombo() {
        return listaOrgaProvCombo;
    }

    public void setListaOrgaProvCombo(SofisCombo listaOrgaProvCombo) {
        this.listaOrgaProvCombo = listaOrgaProvCombo;
    }

    public List<AreaConocimiento> getListaAreasCon() {
        return listaAreasCon;
    }

    public void setListaAreasCon(List<AreaConocimiento> listaAreasCon) {
        this.listaAreasCon = listaAreasCon;
    }

    public List<MutableTreeNode> getListaAreasConTreeNode() {
        return listaAreasConTreeNode;
    }

    public void setListaAreasConTreeNode(List<MutableTreeNode> listaAreasConTreeNode) {
        this.listaAreasConTreeNode = listaAreasConTreeNode;
    }

    public Set<AreaConocimiento> getAreasCon() {
        return areasCon;
    }

    public void setAreasCon(Set<AreaConocimiento> areasCon) {
        this.areasCon = areasCon;
    }

    public NodeStateMap getAreasConStateMap() {
        return areasConStateMap;
    }

    public void setAreasConStateMap(NodeStateMap areasConStateMap) {
        this.areasConStateMap = areasConStateMap;
    }

    public List<AreasTags> getListaAreasTags() {
        return listaAreasTags;
    }

    public void setListaAreasTags(List<AreasTags> listaAreasTags) {
        this.listaAreasTags = listaAreasTags;
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

    public List<Programas> getListaProgramas() {
        return listaProgramas;
    }

    public void setListaProgramas(List<Programas> listaProgramas) {
        this.listaProgramas = listaProgramas;
    }

    public SofisCombo getListaProgramasCombo() {
        return listaProgramasCombo;
    }

    public void setListaProgramasCombo(SofisCombo listaProgramasCombo) {
        this.listaProgramasCombo = listaProgramasCombo;
    }

    public List<OrganiIntProve> getListaOrgProveedores() {
        return listaOrgProveedores;
    }

    public void setListaOrgProveedores(List<OrganiIntProve> listaOrgProveedores) {
        this.listaOrgProveedores = listaOrgProveedores;
    }

    public SofisCombo getListaProyectosCombo() {
        return listaProyectosCombo;
    }

    public void setListaProyectosCombo(SofisCombo listaProyectosCombo) {
        this.listaProyectosCombo = listaProyectosCombo;
    }

    public List<LeccAprendidasTO> getLeccAprendidasResultado() {
        return leccAprendidasResultado;
    }

    public void setLeccAprendidasResultado(List<LeccAprendidasTO> leccAprendidasResultado) {
        this.leccAprendidasResultado = leccAprendidasResultado;
    }

    public Boolean getLeccApreFormDataExpanded() {
        return leccApreFormDataExpanded;
    }

    public void setLeccApreFormDataExpanded(Boolean leccApreFormDataExpanded) {
        this.leccApreFormDataExpanded = leccApreFormDataExpanded;
    }

    public LeccionesAprendidas getLeccAprendida() {
        return leccAprendida;
    }

    public void setLeccAprendida(LeccionesAprendidas leccAprendida) {
        this.leccAprendida = leccAprendida;
    }

    public List<Proyectos> getAutoCompleteProyectosList() {
        return autoCompleteProyectosList;
    }

    public void setAutoCompleteProyectosList(List<Proyectos> autoCompleteProyectosList) {
        this.autoCompleteProyectosList = autoCompleteProyectosList;
    }

    public AutoCompleteEntry getAutoCompleteProyectosComponent() {
        return autoCompleteProyectosComponent;
    }

    public void setAutoCompleteProyectosComponent(AutoCompleteEntry autoCompleteProyectosComponent) {
        this.autoCompleteProyectosComponent = autoCompleteProyectosComponent;
    }

    public boolean isRenderPopupAreaConFiltro() {
        return renderPopupAreaConFiltro;
    }

    public void setRenderPopupAreaConFiltro(boolean renderPopupAreaConFiltro) {
        this.renderPopupAreaConFiltro = renderPopupAreaConFiltro;
    }

    public List<AreaConocimiento> getListaAreasConFiltro() {
        return listaAreasConFiltro;
    }

    public void setListaAreasConFiltro(List<AreaConocimiento> listaAreasConFiltro) {
        this.listaAreasConFiltro = listaAreasConFiltro;
    }

    public List<MutableTreeNode> getListaAreasConTreeNodeFiltro() {
        return listaAreasConTreeNodeFiltro;
    }

    public void setListaAreasConTreeNodeFiltro(List<MutableTreeNode> listaAreasConTreeNodeFiltro) {
        this.listaAreasConTreeNodeFiltro = listaAreasConTreeNodeFiltro;
    }

    public Set<AreaConocimiento> getAreasConFiltro() {
        return areasConFiltro;
    }

    public void setAreasConFiltro(Set<AreaConocimiento> areasConFiltro) {
        this.areasConFiltro = areasConFiltro;
    }

    public NodeStateMap getAreasConStateMapFiltro() {
        return areasConStateMapFiltro;
    }

    public void setAreasConStateMapFiltro(NodeStateMap areasConStateMapFiltro) {
        this.areasConStateMapFiltro = areasConStateMapFiltro;
    }

    public SofisCombo getListaTipoLeccComboForm() {
        return listaTipoLeccComboForm;
    }

    public void setListaTipoLeccComboForm(SofisCombo listaTipoLeccComboForm) {
        this.listaTipoLeccComboForm = listaTipoLeccComboForm;
    }

    public SofisCombo getListaProyectosComboForm() {
        return listaProyectosComboForm;
    }

    public void setListaProyectosComboForm(SofisCombo listaProyectosComboForm) {
        this.listaProyectosComboForm = listaProyectosComboForm;
    }

    public Boolean getRenderPopupMessage() {
        return renderPopupMessage;
    }

    public void setRenderPopupMessage(Boolean renderPopupMessage) {
        this.renderPopupMessage = renderPopupMessage;
    }

    public Boolean getRenderPopupLeccion() {
        return renderPopupLeccion;
    }

    public void setRenderPopupLeccion(Boolean renderPopupLeccion) {
        this.renderPopupLeccion = renderPopupLeccion;
    }

    public LeccionesAprendidas getLeccAprePopup() {
        return leccAprePopup;
    }

    public void setLeccAprePopup(LeccionesAprendidas leccAprePopup) {
        this.leccAprePopup = leccAprePopup;
    }

    @PostConstruct
    public void init() {
        inicioMB.cargarOrganismoSeleccionado();

        cargarCombosFiltro();
        buscarAction();
        cargarFormLecciones();
    }

    public void renderizarFiltro() {
        if (filtroRender) {
            filtroRender = false;
        } else {
            filtroRender = true;
        }
    }

    public boolean areasTematicasHasValues() {
        if (areasTematicasStateMap == null) {
            return areasTematicas != null ? areasTematicas.size() > 0 : false;
        }
        return getAreasTematicasSelected().size() > 0;
    }

    public List getAreasTematicasSelected() {
        if (areasTematicasStateMap == null) {
            return Collections.emptyList();
        }
        return areasTematicasStateMap.getSelected();
    }

    public String areaTematicaPopup() {
        logger.fine("areaTematicaPopup.");
        try {
            renderPopupAreaTematica = true;

            if (listaAreasTagsTreeNode == null || listaAreasTagsTreeNode.isEmpty()) {
                listaAreasTagsTreeNode = new ArrayList();

                listaAreasTags = areaTematicaDelegate.obtenerAreasTematicasPorOrganizacion(inicioMB.getOrganismo().getOrgPk());
                if (listaAreasTags != null && !listaAreasTags.isEmpty()) {
                    Map<String, Object> mapAreaTag = WebUtils.setNodosForAreaTematica(listaAreasTags, listaAreasTagsTreeNode, areasTematicas, areasTematicasStateMap);
                    listaAreasTagsTreeNode = (List<MutableTreeNode>) mapAreaTag.get(WebUtils.LISTA_AREAS_TAG_TREE_NODE);
                    areasTematicasStateMap = (NodeStateMap) mapAreaTag.get(WebUtils.AREAS_TEMATICAS_STATE_MAP);
                }
            }
        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    public boolean areasConHasValues() {
        if (areasConStateMap == null) {
            return areasCon != null ? areasCon.size() > 0 : false;
        }
        return getAreasConSelected().size() > 0;
    }

    public List getAreasConSelected() {
        if (areasConStateMap == null) {
            return Collections.emptyList();
        }
        return areasConStateMap.getSelected();
    }

    public String areaConPopup() {
        logger.fine("areaConPopup.");
        try {
            renderPopupAreaCon = true;

            listaAreasConTreeNode = new ArrayList();

            listaAreasCon = areasConocimientoDelegate.obtenerAreaConPorOrg(inicioMB.getOrganismo().getOrgPk());
            if (listaAreasCon != null && !listaAreasCon.isEmpty()) {
                Map<String, Object> mapAreaCon = WebUtils.setNodosForAreaConocimiento(listaAreasCon, listaAreasConTreeNode, areasCon, areasConStateMap);
                listaAreasConTreeNode = (List<MutableTreeNode>) mapAreaCon.get(WebUtils.LISTA_AREAS_CON_TREE_NODE);
                areasConStateMap = (NodeStateMap) mapAreaCon.get(WebUtils.AREAS_CONOCIMIENTO_STATE_MAP);
            }
        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    public boolean areasConHasValuesFiltro() {
        if (areasConStateMapFiltro == null) {
            return areasConFiltro != null ? areasConFiltro.size() > 0 : false;
        }
        return getAreasConSelectedFiltro().size() > 0;
    }

    public List getAreasConSelectedFiltro() {
        if (areasConStateMapFiltro == null) {
            return Collections.emptyList();
        }
        return areasConStateMapFiltro.getSelected();
    }

    public String areaConPopupFiltro() {
        logger.fine("areaConPopupFiltro.");
        try {
            renderPopupAreaConFiltro = true;

            if (listaAreasConTreeNodeFiltro == null || listaAreasConTreeNodeFiltro.isEmpty()) {
                listaAreasConTreeNodeFiltro = new ArrayList();

                listaAreasConFiltro = areasConocimientoDelegate.obtenerAreaConPorOrg(inicioMB.getOrganismo().getOrgPk());
                if (listaAreasConFiltro != null && !listaAreasConFiltro.isEmpty()) {
                    Map<String, Object> mapAreaCon = WebUtils.setNodosForAreaConocimiento(listaAreasConFiltro, listaAreasConTreeNodeFiltro, areasConFiltro, areasConStateMapFiltro);
                    listaAreasConTreeNodeFiltro = (List<MutableTreeNode>) mapAreaCon.get(WebUtils.LISTA_AREAS_CON_TREE_NODE);
                    areasConStateMapFiltro = (NodeStateMap) mapAreaCon.get(WebUtils.AREAS_CONOCIMIENTO_STATE_MAP);
                }
            }
        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    public String agregarLeccApreAction() {
        leccAprendida.setLecaprOrgFk(inicioMB.getOrganismo());
        leccAprendida.setLecaprProyFk((Proyectos) listaProyectosComboForm.getSelectedObject());
        leccAprendida.setLecaprTipoFk((TipoLeccion) listaTipoLeccComboForm.getSelectedObject());
        leccAprendida.setUsuarioFk(inicioMB.getUsuario());

        setAreasConToLeccApre();

        try {
            leccAprendida = leccAprDelegate.guardarLeccion(leccAprendida);
            if (leccAprendida != null) {
                leccApreFormDataExpanded = false;
                renderPopupMessage = false;
                JSFUtils.agregarMsg(LECC_APRENDIDAS_MSG, "info_leccApre_guardada", null);
                leccAprendidasResultado = leccAprDelegate.buscarLeccionesPorFiltro(filtro, inicioMB.getOrganismo().getOrgPk());
                agregarCamposFormateados();
                limpiarLeccApre();
            }

        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            List<String> errores = ex.getErrores();
            JSFUtils.agregarMsgs(LECC_APRENDIDAS_FORM_MSG, errores);
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsg(LECC_APRENDIDAS_FORM_MSG, MensajesNegocio.ERROR_LECC_APRENDIDAS_GUARDAR, null);
        }

        return null;
    }

    public String limpiarLeccApre() {
        this.leccAprendida = new LeccionesAprendidas();
        listaTipoLeccComboForm.setSelected(-1);
        listaProyectosComboForm.setSelected(-1);
        areasCon = null;
        areasConStateMap = null;
        return null;
    }

    public void cerrarFormCollapsable() {
        limpiarLeccApre();
        leccApreFormDataExpanded = false;
    }

    public String buscarAction() {
        if (leccAprendidasResultado != null) {
            leccAprendidasResultado.clear();
        }

        setAreasTematicasToFiltro();
        setAreasConocimientoToFiltro();
        obtenerCombosSeleccionadosFiltro();
        setSelectedCombosFiltro();

        final Integer orgPk = inicioMB.getOrganismo().getOrgPk();

        leccAprendidasResultado = leccAprDelegate.buscarLeccionesPorFiltro(filtro, orgPk);
        agregarCamposFormateados();

        return null;
    }

    public void obtenerCombosSeleccionadosFiltro() {
        if (filtro != null) {
            if (listaTipoLeccCombo.getSelectedObject() != null) {
                filtro.setTipo((TipoLeccion) listaTipoLeccCombo.getSelectedObject());
            } else {
                filtro.setTipo(null);
            }

            if (listaProgramasCombo.getSelectedObject() != null) {
                Programas prog = (Programas) listaProgramasCombo.getSelectedObject();
                filtro.setProgPk(prog.getProgPk());
                filtro.setProgNombre(prog.getProgNombre());
            } else {
                filtro.setProgPk(null);
                filtro.setProgNombre(null);
            }

            if (listaProyectosCombo.getSelectedObject() != null) {
                Proyectos proy = (Proyectos) listaProyectosCombo.getSelectedObject();
                filtro.setProyPk(proy.getProyPk());
                filtro.setProyNombre(proy.getProyNombre());
            } else {
                filtro.setProyPk(null);
                filtro.setProyNombre(null);
            }

            if (listaOrganizacionCombo.getSelectedObject() != null) {
                filtro.setInteresado((OrganiIntProve) listaOrganizacionCombo.getSelectedObject());
            } else {
                filtro.setInteresado(null);
            }

            if (listaOrgaProvCombo.getSelectedObject() != null) {
                filtro.setProveedor((OrganiIntProve) listaOrgaProvCombo.getSelectedObject());
            } else {
                filtro.setProveedor(null);
            }
        }
    }

    public void setSelectedCombosFiltro() {
        if (filtro.getTipo() != null) {
            listaTipoLeccCombo.setSelected(filtro.getTipo().getTipolecPk());
        }

        if (filtro.getProgPk() != null) {
            listaProgramasCombo.setSelected(filtro.getProgPk());
        }

        if (filtro.getProyPk() != null) {
            listaProyectosCombo.setSelected(filtro.getProyPk());
        }

        if (filtro.getInteresado() != null) {
            listaOrganizacionCombo.setSelected(filtro.getInteresado().getOrgaPk());
        }

        if (filtro.getProveedor() != null) {
            listaOrgaProvCombo.setSelected(filtro.getProveedor().getOrgaPk());
        }
    }

    public String limpiarFiltro() {
        filtro = new FiltroLeccAprTO();
        listaTipoLeccCombo.setSelected(-1);
        listaOrganizacionCombo.setSelected(-1);
        listaOrgaProvCombo.setSelected(-1);
        listaProgramasCombo.setSelected(-1);
        listaProyectosCombo.setSelected(-1);
        areasTematicas = null;
        areasTematicasStateMap = null;
        areasConFiltro = null;
        areasConStateMapFiltro = null;
        listaAreasConTreeNodeFiltro = null;
        return null;
    }

    /**
     * cuando el usuario selecciona alguna opcion de la lista
     *
     * @param event
     */
    public void autoCompleteProyectosValueChangeListener(ValueChangeEvent event) {
        PhaseId phaseId = event.getPhaseId();

        if (phaseId.equals(PhaseId.ANY_PHASE)) {
            event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
            event.queue();
        } else if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES)) {
            procesarProyectosAutoCompl((String) event.getNewValue(), (String) event.getOldValue());
        }
    }

    private void procesarProyectosAutoCompl(String proySeleccionado, String proySeleccionadoOld) {

        boolean encontre = false;
        if (proySeleccionadoOld != null && proySeleccionado != null
                && proySeleccionado.equalsIgnoreCase(proySeleccionadoOld)) {
            return;
        }
        if (proySeleccionado != null && !proySeleccionado.equalsIgnoreCase("")) {
            for (Proyectos proy : autoCompleteProyectosList) {
                if (proy.getProyPkNombre().equalsIgnoreCase(proySeleccionado)) {
                    Proyectos proySelected = (Proyectos) proy.clone();
                    logger.log(Level.FINEST, "Se asocia al Proyecto {0}", proySelected.getProyPkNombre());
                    leccAprendida.setLecaprProyFk(proySelected);
                    encontre = true;
                    break;
                } else {
                    logger.finest("PROYECTO NO ENCONTRADO");
                }
            }
        }
        if (!encontre) {
            //logger.finest("NO Se asocia a la persona ");
            //interesado.setIntPersonaFk(new Personas());
        }
    }

    private void cargarCombosFiltro() {

        listaTipoLecc = tipoLeccionDelegate.obtenerTodos();
        if (listaTipoLecc != null && !listaTipoLecc.isEmpty()) {
            listaTipoLeccCombo = new SofisCombo((List) listaTipoLecc, "tipolecNombre");
            listaTipoLeccCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }

        listaOrganizacion = organiIntProveDelegate.obtenerInteresados(inicioMB.getOrganismo().getOrgPk());
        if (listaOrganizacion != null && !listaOrganizacion.isEmpty()) {
            listaOrganizacionCombo = new SofisCombo((List) listaOrganizacion, "orgaNombre");
            listaOrganizacionCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }

        listaOrgProveedores = organiIntProveDelegate.obtenerProveedores(inicioMB.getOrganismo().getOrgPk());
        if (listaOrgProveedores != null && !listaOrgProveedores.isEmpty()) {
            listaOrgaProvCombo = new SofisCombo((List) listaOrgProveedores, "orgaNombre");
            listaOrgaProvCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }

        listaProgramas = programasDelegate.obtenerProgComboPorOrg(inicioMB.getOrganismo().getOrgPk());
        if (listaProgramas != null && !listaProgramas.isEmpty()) {
            listaProgramasCombo = new SofisCombo((List) listaProgramas, "progNombre");
            listaProgramasCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }

        autoCompleteProyectosList = proyectosDelegate.obtenerProyComboPorOrg(inicioMB.getOrganismo().getOrgPk());
        if (autoCompleteProyectosList != null && !autoCompleteProyectosList.isEmpty()) {
            listaProyectosCombo = new SofisCombo((List) autoCompleteProyectosList, "proyNombre");
            listaProyectosCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }
    }

    private void cargarFormLecciones() {
        autoCompleteProyectosList = proyectosDelegate.obtenerActivos(inicioMB.getOrganismo().getOrgPk());

        if (autoCompleteProyectosList != null && !autoCompleteProyectosList.isEmpty()) {
            listaProyectosComboForm = new SofisCombo((List) autoCompleteProyectosList, "proyNombre");
            listaProyectosComboForm.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        listaTipoLecc = tipoLeccionDelegate.obtenerTodos();
        if (listaTipoLecc != null && !listaTipoLecc.isEmpty()) {
            listaTipoLeccComboForm = new SofisCombo((List) listaTipoLecc, "tipolecNombre");
            listaTipoLeccComboForm.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }
    }

    public String eliminarLeccionAction(Integer leccAprPk) {
        try {
            LeccionesAprendidas leccApre = leccAprDelegate.inactivarLeccionPorPk(leccAprPk);
            if (!leccApre.isActivo()) {
                JSFUtils.agregarMsgInfo(ConstantesPresentacion.MESSAGE_ID_POPUP, Labels.getValue("info_leccApre_eliminada"), null);
                inicioMB.setRenderPopupMensajes(Boolean.TRUE);
                leccAprendidasResultado = leccAprDelegate.buscarLeccionesPorFiltro(filtro, inicioMB.getOrganismo().getOrgPk());
                agregarCamposFormateados();
            }
        } catch (BusinessException be) {
            logger.log(Level.SEVERE, be.getMessage());
            JSFUtils.agregarMsgInfo(LECC_APRENDIDAS_FORM_MSG, Labels.getValue("error_lecc_aprendidas_baja"), null);
        }
        return null;
    }

    public String editarLeccApreAction(Integer leccAprPk) {
        limpiarLeccApre();
        leccAprendida = leccAprDelegate.obtenerLeccionPorPk(leccAprPk);

        if (leccAprendida != null) {
            if (leccAprendida.getLecaprProyFk() != null) {
                listaProyectosComboForm.setSelectedObject(leccAprendida.getLecaprProyFk());
            }
            if (leccAprendida.getLecaprTipoFk() != null) {
                listaTipoLeccComboForm.setSelectedObject(leccAprendida.getLecaprTipoFk());
            }

            areasCon = new HashSet<>(leccAprendida.getAreaConocimientosSet());
            leccApreFormDataExpanded = true;
        } else {
            JSFUtils.agregarMsgError(LECC_APRENDIDAS_FORM_MSG, Labels.getValue("error_lecc_aprendidas_null"), null);
        }

        return null;
    }

    public String cerrarPopupAreaTematica() {
        renderPopupAreaTematica = false;
        return null;
    }

    public String cerrarPopupAreaConocimiento() {
        renderPopupAreaCon = false;
        return null;
    }

    public String cerrarPopupAreaConocimientoFiltro() {
        renderPopupAreaConFiltro = false;
        return null;
    }

    private void setAreasConToLeccApre() {
        if (areasConStateMap != null) {
            List lista = areasConStateMap.getSelected();

            if (lista != null && lista.size() > 0) {
                leccAprendida.setAreaConocimientosSet(new HashSet<AreaConocimiento>());
                for (Object object : lista) {
                    if (object instanceof DefaultMutableTreeNode) {
                        DefaultMutableTreeNode d = (DefaultMutableTreeNode) object;
                        AreaConocimiento ac = (AreaConocimiento) d.getUserObject();
                        leccAprendida.getAreaConocimientosSet().add(ac);
                    }
                }
            } else {
                leccAprendida.setAreaConocimientosSet(null);
            }
        }
    }

    private void setAreasConocimientoToFiltro() {
        if (areasConStateMapFiltro != null) {
            List lista = areasConStateMapFiltro.getSelected();
            if (lista != null && lista.size() > 0) {
                filtro.setAreasConocimiento(new ArrayList<AreaConocimiento>());
                for (Object object : lista) {
                    if (object instanceof DefaultMutableTreeNode) {
                        DefaultMutableTreeNode d = (DefaultMutableTreeNode) object;
                        AreaConocimiento ac = (AreaConocimiento) d.getUserObject();
                        filtro.getAreasConocimiento().add(ac);
                    }
                }
            } else {
                filtro.setAreasConocimiento(null);
            }
        }
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

    public String leccionPopup(Integer leccPk) {
        try {
            renderPopupLeccion = true;
            leccAprePopup = leccAprDelegate.obtenerLeccionPorPk(leccPk);

        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void cerrarPopupLeccion() {
        renderPopupLeccion = false;
    }

    public String getSubString(String str, int largo) {
        if (str.length() > largo) {
            return str.substring(0, largo).concat("...");
        }
        return str;
    }

    //metodos de visualización
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

    private boolean fieldAttribute(String fieldName, FieldAttributeEnum field) {

        boolean checkDisabled = field == FieldAttributeEnum.DISABLED;
        boolean checkRendered = field == FieldAttributeEnum.RENDERED;

        boolean disabled = false;
        boolean rendered = true;

        boolean isPMOT = inicioMB.isUsuarioOrgaPMOT();
        boolean isPMOF = inicioMB.isUsuarioOrgaPMOF();

        if (fieldName.equalsIgnoreCase("agregarLeccApre")
                || fieldName.equalsIgnoreCase("editarLeccion")
                || fieldName.equalsIgnoreCase("eliminarLeccion")) {
            if (!(isPMOF || isPMOT)) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("AreaTematicaTree")) {
            if (!(isPMOF || isPMOT)) {
//                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("AreaConocimientoTree")) {
            if (!(isPMOF || isPMOT)) {
//                rendered = false;
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

    public String getLecaprTipoStr(Integer tipoLeccPk) {
        if (tipoLeccPk != null) {
            return Labels.getValue("tipo_leccion_" + tipoLeccPk);
        }
        return "";
    }

    private void agregarCamposFormateados() {
        if (CollectionsUtils.isNotEmpty(leccAprendidasResultado)) {
            for (LeccAprendidasTO leccApre : leccAprendidasResultado) {
                if (leccApre.getLecaprTipoFk() != null) {
                    String str = Labels.getValue("tipo_leccion_" + leccApre.getLecaprTipoFk());
                    leccApre.setLecaprTipoStr(str);
                }
            }
        }
    }
}