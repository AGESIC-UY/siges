package com.sofis.web.mb;

import com.icesoft.faces.context.Resource;
import com.icesoft.faces.context.effects.JavascriptContext;
import com.sofis.business.properties.ConfigApp;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.utils.NumbersUtils;
import com.sofis.business.utils.OrganiIntProveUtils;
import com.sofis.business.utils.ParticipantesUtils;
import com.sofis.business.utils.ProductosUtils;
import com.sofis.business.utils.TipoDocumentoInstanciaUtils;
import com.sofis.business.validations.EntregablesValidacion;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.codigueras.EstadoPublicacionCodigos;
import com.sofis.entities.codigueras.EstadosCodigos;
import com.sofis.entities.codigueras.SsRolCodigos;
import com.sofis.entities.codigueras.TiposMediaCodigos;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.constantes.ConstantesLogica;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.Calidad;
import com.sofis.entities.data.CategoriaProyectos;
import com.sofis.entities.data.CausalCompra;
import com.sofis.entities.data.CentroCosto;
import com.sofis.entities.data.ComponenteProducto;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Departamentos;
import com.sofis.entities.data.Devengado;
import com.sofis.entities.data.DocFile;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.EstadosPublicacion;
import com.sofis.entities.data.Etapa;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.FuenteProcedimientoCompra;
import com.sofis.entities.data.IdentificadorGrpErp;
import com.sofis.entities.data.Interesados;
import com.sofis.entities.data.LatlngProyectos;
import com.sofis.entities.data.MediaProyectos;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.ObjetivoEstrategico;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.data.Participantes;
import com.sofis.entities.data.Personas;
import com.sofis.entities.data.PlantillaCronograma;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.entities.data.ProdMes;
import com.sofis.entities.data.Productos;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.ProyIndices;
import com.sofis.entities.data.ProyIndicesPre;
import com.sofis.entities.data.ProyOtrosDatos;
import com.sofis.entities.data.ProyReplanificacion;
import com.sofis.entities.data.ProySitactHistorico;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.Riesgos;
import com.sofis.entities.data.RolesInteresados;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.data.TipoAdquisicion;
import com.sofis.entities.data.TipoDocumentoInstancia;
import com.sofis.entities.data.TiposMedia;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.entities.enums.TipoRegistroCompra;
import com.sofis.entities.tipos.AdqPagosTO;
import com.sofis.entities.tipos.ComboItemTO;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.tipos.FiltroCentroCostoTO;
import com.sofis.entities.tipos.FiltroIdentificadorGrpErpTO;
import com.sofis.entities.tipos.FiltroObjectivoEstategicoTO;
import com.sofis.entities.tipos.FiltroTipoAdquisicionTO;
import com.sofis.entities.tipos.MonedaImporteResumenTO;
import com.sofis.entities.tipos.MonedaImporteTO;
import com.sofis.entities.tipos.TablaDinamicaPresupuestoTO;
import com.sofis.entities.utils.FichaUtils;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.entities.validations.FichaAdquisicionValidacion;
import com.sofis.entities.validations.FichaDocumentosValidacion;
import com.sofis.entities.validations.FichaInteresadosValidacion;
import com.sofis.entities.validations.FichaPagoValidacion;
import com.sofis.entities.validations.FichaPresupuestoValidacion;
import com.sofis.entities.validations.FichaRiesgosValidacion;
import com.sofis.entities.validations.FichaValidacion;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.EmailValidator;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.AdquisicionDelegate;
import com.sofis.web.delegates.AreaTematicaDelegate;
import com.sofis.web.delegates.AreasDelegate;
import com.sofis.web.delegates.CategoriaProyectosDelegate;
import com.sofis.web.delegates.CausalCompraDelegate;
import com.sofis.web.delegates.CentroCostoDelegate;
import com.sofis.web.delegates.ComponenteProductoDelegate;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.CronogramaDelegate;
import com.sofis.web.delegates.DevengadoDelegate;
import com.sofis.web.delegates.DocumentosDelegate;
import com.sofis.web.delegates.EntregablesDelegate;
import com.sofis.web.delegates.EstadosDelegate;
import com.sofis.web.delegates.EtapaDelegate;
import com.sofis.web.delegates.FuenteFinanciamientoDelegate;
import com.sofis.web.delegates.FuenteProcedimientoCompraDelegate;
import com.sofis.web.delegates.IdentificadorGrpErpDelegate;
import com.sofis.web.delegates.InteresadosDelegate;
import com.sofis.web.delegates.LatlngDelegate;
import com.sofis.web.delegates.MediaProyectosDelegate;
import com.sofis.web.delegates.MonedaDelegate;
import com.sofis.web.delegates.ObjetivoEstrategicoDelegate;
import com.sofis.web.delegates.OrganiIntProveDelegate;
import com.sofis.web.delegates.PagosDelegate;
import com.sofis.web.delegates.ParticipantesDelegate;
import com.sofis.web.delegates.PersonasDelegate;
import com.sofis.web.delegates.PlantillaCronogramaDelegate;
import com.sofis.web.delegates.PresupuestoDelegate;
import com.sofis.web.delegates.ProcedimientoCompraDelegate;
import com.sofis.web.delegates.ProductosDelegate;
import com.sofis.web.delegates.ProgProyDelegate;
import com.sofis.web.delegates.ProgramasDelegate;
import com.sofis.web.delegates.ProgramasProyectosDelegate;
import com.sofis.web.delegates.ProyPublicaHistDelegate;
import com.sofis.web.delegates.ProyReplanificacionDelegate;
import com.sofis.web.delegates.ProySitActHistoricoDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.delegates.RiesgosDelegate;
import com.sofis.web.delegates.RolesInteresadosDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.delegates.TipoAdquisicionDelegate;
import com.sofis.web.delegates.TipoDocumentoInstanciaDelegate;
import com.sofis.web.delegates.TiposMediaDelegate;
import com.sofis.web.enums.FieldAttributeEnum;
import com.sofis.web.genericos.constantes.ConstantesNavegacion;
import com.sofis.web.genericos.constantes.ConstantesPresentacion;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.EntregablesCargaPorFomulario;
import com.sofis.web.utils.FilaRiesgosLimite;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisCombo;
import com.sofis.web.utils.SofisComboG;
import com.sofis.web.utils.SofisResource;
import com.sofis.web.utils.WebUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.icefaces.ace.component.autocompleteentry.AutoCompleteEntry;
import org.icefaces.ace.component.datatable.DataTable;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;
import org.icefaces.ace.json.JSONArray;
import org.icefaces.ace.json.JSONException;
import org.icefaces.ace.json.JSONObject;
import org.icefaces.ace.model.tree.NodeState;
import org.icefaces.ace.model.tree.NodeStateMap;
import org.icefaces.util.JavaScriptRunner;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "fichaMB")
@ViewScoped
public class FichaMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(FichaMB.class.getName());
    private static final String FICHA_MSG_ID = "fichaMsg";
    private static final String PROD_MSG_ID = "productoMsg";
    private static final String PROD_FORM_MSG_ID = "productosFormMsg";
    private static final String DEV_MSG_ID = "devengadoMsg";
    private static final String DOC_MSG_ID = "ficha:btnAgregar";
    private static final String PLANTILLA_CRO_MSG_ID = "plantCroPopupMsg";
    private static final String INTERESADOS_MSG_ID = "interesadosMsg";
    private static final String PRESUPUESTO_FORM_MSG_ID = "formPreMsg";
    private static String GROUP_NAME = "everyone";
    private static final String USUARIO_POPUP_MSG = "usuarioPopupMsg";
    static final String REPLANIFICACION_POPUP_MSG = "replanificacionPopupMsg";
    private static String div = "";

    //Inject
    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;

    @ManagedProperty("#{aplicacionMB}")
    private AplicacionMB aplicacionMB;
    @Inject
    private ProgramasDelegate programaDelegate;
    @Inject
    private ProyectosDelegate proyectoDelegate;
    @Inject
    private ProgProyDelegate progProyDelegate;
    @Inject
    private AreasDelegate areasDelegate;
    @Inject
    private AreaTematicaDelegate areaTematicaDelegate;
    @Inject
    private PersonasDelegate personasDelegate;
    @Inject
    private OrganiIntProveDelegate organiIntProveDelegate;
    @Inject
    private RolesInteresadosDelegate rolesInteresadosDelegate;
    @Inject
    private InteresadosDelegate interesadosDelegate;
    @Inject
    private DocumentosDelegate documentosDelegate;
    @Inject
    private TipoDocumentoInstanciaDelegate tipoDocumentoInstanciaDelegate;
    @Inject
    private RiesgosDelegate riesgosDelegate;
    @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;
    @Inject
    private ConfiguracionDelegate configuracionDelegate;
    @Inject
    private CronogramaDelegate cronogramaDelegate;
    @Inject
    private EntregablesDelegate entregablesDelegate;
    @Inject
    private MonedaDelegate monedaDelegate;
    @Inject
    private FuenteFinanciamientoDelegate fuenteFinanciamientoDelegate;
    @Inject
    private ComponenteProductoDelegate componenteProductoDelegate;
    @Inject
    private PresupuestoDelegate presupuestoDelegate;
    @Inject
    private AdquisicionDelegate adquisicionDelegate;
    @Inject
    private PagosDelegate pagosDelegate;
    @Inject
    private ProySitActHistoricoDelegate proySitActHistoricoDelegate;
    @Inject
    private ProductosDelegate productosDelegate;
    @Inject
    private PlantillaCronogramaDelegate plantillaCronogramaDelegate;
    @Inject
    private ProyReplanificacionDelegate proyReplanificacionDelegate;
    @Inject
    private EstadosDelegate estadosDelegate;
    @Inject
    private ParticipantesDelegate participantesDelegate;
    @Inject
    private DevengadoDelegate devengadoDelegate;
    @Inject
    private EtapaDelegate etapaDelegate;
    @Inject
    private TiposMediaDelegate tiposMediaDelegate;
    @Inject
    private MediaProyectosDelegate mediaProyectosDelegate;
    @Inject
    private CategoriaProyectosDelegate categoriaProyectosDelegate;
    @Inject
    private ProyPublicaHistDelegate proyPublicaHistDelegate;
    @Inject
    private ProgramasProyectosDelegate programasProyectosDelegate;
    @Inject
    private ObjetivoEstrategicoDelegate objetivoEstrategicoDelegate;

    @Inject
    private ProcedimientoCompraDelegate procedimiComponenteProductoDelegate;
    @Inject
    private IdentificadorGrpErpDelegate identificadorGrpErpDelegate;

    @Inject
    private LatlngDelegate latlngDelegate;
    @Inject
    private TipoAdquisicionDelegate tipoAdquisicionDelegate;
    @Inject
    private CentroCostoDelegate centroCostoDelegate;
    @Inject
    private CausalCompraDelegate causalCompraDelegate;
    @Inject
    private FuenteProcedimientoCompraDelegate fuenteProcedimientoCompraDelegate;

    // Variables
    //indica si se accede desde el reporte de mis tareas.
    private boolean misTareas = false;
    private boolean frameMax = false;
    private Configuracion confAdjPre = null;
    private Configuracion confRiesgoTiempoLimiteAmarillos = null;
    private Configuracion confRiesgoTiempoLimiteRojo = null;
    private FichaTO fichaTO;
    private String areaOrganizacion;
    private HashMap<String, Boolean> permisosEdicion = new HashMap<String, Boolean>();
    private List<Personas> autoCompletePersonasList = new ArrayList<Personas>();
    private boolean mostrarPanel = true;
    private List<Areas> listaAreas;
    private List<AreasTags> listaAreasTags;
    private List<Programas> listaProgramas;
    private List<Double> listaEstDoc;
    private SofisCombo listaOrganizacionCombo = new SofisCombo();
    private SofisCombo listaProveedoresCombo = new SofisCombo();
    private SofisCombo listaClienteCombo = new SofisCombo();
    private SofisComboG<OrganiIntProve> listaProveedoresPagoCombo = new SofisComboG();
    private List<MutableTreeNode> listaAreasTagsTreeNode = new ArrayList<MutableTreeNode>();
    private NodeStateMap areasRestringidasStateMap;
    private NodeStateMap areasTematicasStateMap = new NodeStateMap();
    private List<MutableTreeNode> listaAreasTreeNode = new ArrayList<MutableTreeNode>();
    // Rendered Popups
    private Boolean renderPopupLectura = false;
    private Boolean renderPopupAreaTematica = false;
    private Boolean renderPopupMetodologia = false;
    private Boolean renderPopupInteresados = false;
    private Boolean renderPopupRiesgo = false;
    private Boolean renderPopupDocumentos = false;
    private Boolean renderPopupHistoricoSitAct = false;
    private Boolean renderPopupOtrosDatos = false;
    private Boolean renderPopupMapaUbicacion = false;
    private Boolean renderPopupDocumentoHistorico = false;
    private Boolean renderPopupLocalizacion = false;
    /**
     * Gestiona si se debe mostrar los panels ampliados para cada bloque
     * resumen. 0=Documentos, 1=Interesados, 2=Riesgos, 3=Cronograma,
     * 4=Presupuesto, 5=Productos, 6=Participantes, 7=Calidad, 8=Multimedia.
     */
    private Boolean mostrar[] = {false, false, false, false, false, false, false, false, false, false};
    private Long selectedMostrar = null;
    private List emptyList = new ArrayList();
    private AutoCompleteEntry autoCompletePersonasComponent;
    private DataTable docsTable;
    private List<FilaRiesgosLimite> limiteGestionRiesgos = new ArrayList<FilaRiesgosLimite>();
    //-- Ficha 
    private SofisCombo listaAreasOrganismoCombo = new SofisCombo();
    private SofisCombo listaProgramasCombo = new SofisCombo();
    private SofisCombo listaSponsorCombo = new SofisCombo();
    private SofisCombo listaAdjuntoCombo = new SofisCombo();
    private SofisCombo listaGerenteCombo = new SofisCombo();
    private SofisCombo listaPmoFederadaCombo = new SofisCombo();
    private SofisCombo listaEtapaPubCombo = new SofisCombo();
    private List<Entregables> listaEntregables;
    private SofisCombo listaEntregablesCombo = new SofisCombo();
    private SofisComboG<TipoRegistroCompra> listaTipoRegistroCompraCombo = new SofisComboG();
    private SofisComboG<TipoAdquisicion> listaTipoAdquisicionCombo = new SofisComboG();
    private SofisComboG<CentroCosto> listaCentroCostoCombo = new SofisComboG();
    private SofisComboG<CausalCompra> listaCausalCompraCombo = new SofisComboG();
    private SofisComboG<Pagos> listaDocPagoCombo = new SofisComboG();
    private SofisComboG<ObjetivoEstrategico> listaObjetivosEstrategicosCombo = new SofisComboG<>();
    private List<ProySitactHistorico> historicoSitAct;
    //-- Documentos
    private Documentos documento = new Documentos();
    private Documentos documentoPopup = new Documentos();
    private Double indiceEstado;
    private String indiceMetodologiaColor;
    private List<Documentos> listaDocumentosResumen;
    private List<TipoDocumentoInstancia> listaTipoDocInstResumen;
    private List<Documentos> listaDocumentosFrame;
    private List<TipoDocumentoInstancia> listaTipoDocumento;
    private List<SelectItem> listaEstDocCombo = new ArrayList<SelectItem>();
    private File upFileDoc;
    private Boolean docsFormDataExpanded = false;
    private Resource fileResource;
    private SofisCombo listaTipoDocCombo = new SofisCombo();
    private Map<Integer, SofisResource> downloadFile = new HashMap<Integer, SofisResource>();
    private Map<Integer, SofisResource> docHistDownloadFile = new HashMap<Integer, SofisResource>();
    //-- Interesados
    private Interesados interesado = new Interesados();
    private List<Interesados> listaInteresadosResumen;
    private SofisCombo listaRolesInteresadosCombo = new SofisCombo();
    private List<RolesInteresados> listaRolesInteresados;
    private Boolean intFormDataExpanded = false;
    //-- Riesgos
    private Riesgos riesgo = new Riesgos();
    private Riesgos riskPopup = new Riesgos();
    private Double exposicionRiesgo;
    private String exposicionRiesgoColor;
    private Date riesgoActualizacion;
    private String riesgoActualizacionColor;
    private Integer riesgosCantAltos;
    private List<Riesgos> listaRiesgosResumen;
    private Boolean riskFormDataExpanded = false;
    private List<ComboItemTO> listaRiskProbabilidad;
    private List<Integer> listaRiskImpacto;
    private SofisCombo listaRiskProbabilidadCombo = new SofisCombo();
    private SofisCombo listaRiskImpactoCombo = new SofisCombo();
    private SofisComboG<Entregables> listaRiskEntregablesCombo = new SofisComboG();
    private SofisComboG<Entregables> listaIntEntregablesCombo = new SofisComboG();
    //-- Cronograma
    private Cronogramas cronograma = new Cronogramas();
    private String dataCron = "";
    private int[] indiceAvanceFinalizado;
    private int[] indiceAvanceParcial;
    private List<Entregables> listaEntregablesResumen;
    //-- Presupuesto
    private Boolean preFormDataExpanded = false;
    private Integer largoMaximoIdAdquisicion = 0;

    //1-Presupuesto, 2-Adquisiocion, 3-Pagos
    private int formPresupuestoRendered = 0;
    private List<Moneda> listaMonedas;
    private SofisCombo listaMonedaCombo = new SofisCombo();
    private SofisCombo listaMonedaPreCombo = new SofisCombo();
    private List<FuenteFinanciamiento> listaFuentes;
    private SofisCombo listaFuentesCombo = new SofisCombo();
    private SofisCombo listaFuentesPreCombo = new SofisCombo();
    private List<ComponenteProducto> listaComponenteProducto;
    private SofisCombo listaComponenteProductoCombo = new SofisCombo();
    private Presupuesto presupuesto = new Presupuesto();
    private Presupuesto preFicha = new Presupuesto();
    private Adquisicion adquisicion = new Adquisicion();
    private Pagos pagos = new Pagos();
    private DataTable pagoTable;
    private List<AdqPagosTO> listaAdqPagosFrame;
    private List<Moneda> monedasPresupuesto;
    private List<TablaDinamicaPresupuestoTO> presupuestoResumen;
    private String fechaUltimaSitAct;

    private List<ProcedimientoCompra> listaProcedimientoCompra;
    private SofisCombo listaProcedimientoCompraCombo = new SofisCombo();
    private List<IdentificadorGrpErp> listaIdentificadorGrpErp;
    private SofisComboG<IdentificadorGrpErp> listaIdentificadorGrpErpCombo = new SofisComboG();

    private List<SsUsuario> listaUsuariosAdqCompartida;
    private List<SelectItem> listaUsuariosAdqCompartidaCombo;

    private Integer ssUsuarioCompartidaId;

    // Devengado
    private List<Adquisicion> listAdqDev = new ArrayList<Adquisicion>();
    private Integer anioDev;
    private boolean editDev = false;
    private SofisPopupUI renderAdqDevPopup = new SofisPopupUI();
    private List<Adquisicion> listAdq = new ArrayList<Adquisicion>();
    private SofisCombo listAdqCombo = new SofisCombo();
    // Productos
    private boolean prodFormDataExpanded;
    private Map<String, Integer> mapProdRango;
    private Productos producto = new Productos();
    private List<Entregables> entregablesListProd;
    private List<Productos> productosList;
    private SofisCombo listaEntProdCombo = new SofisCombo();
    private Map<Integer, Boolean> editarProdMap = new HashMap<Integer, Boolean>();
    private List<Productos> listaProductosResumen;
    private Integer limiteAmarilloProd;
    private Integer limiteRojoProd;
    //Ficha Replanificacion Popup
    private ProyReplanificacion replanificacion;
    private Boolean renderPopupReplanificacion = false;
    //Copiar Proyecto
    private Boolean renderPopupCopiaProy = false;
    private Boolean renderPopupNotificacion = false;
    private String nombreProyCopia;
    private Date fechaComienzoProyCopia;

    //-- Participantes
    private List<Participantes> listaParticipantes = new ArrayList<Participantes>();
    private List<Participantes> listaParticipantesResumen = new ArrayList<Participantes>();
    private List<MonedaImporteResumenTO> listaParticipanteResumenMonedaConsolidado = new ArrayList<MonedaImporteResumenTO>();
    private Boolean partFormDataExpanded = false;
    private List<SsUsuario> listaUsuarios = new ArrayList<SsUsuario>();
    // Plantilla Cro Popup
    private boolean renderPopupPlantillaCro = false;
    private PlantillaCronograma plantillaCro;
    private List<PlantillaCronograma> plantillaCroList;
    private SofisCombo plantillaCroListCombo = new SofisCombo();
    private Date plantillaFechaInicio;
    // Otros Datos
    private Boolean multiFormDataExpanded = false;
    private MediaProyectos mediaProy = new MediaProyectos();
    private SofisComboG<TiposMedia> listaTipoMediaCombo = new SofisComboG<TiposMedia>();
    private TiposMedia tipoMediaSelected;
    private int[] indiceAvanceProd;
    private int[] indiceAvanceTiempo;
    private int multiCantFotos;
    private Date multiFechaActFotos;
    private int multiCantVideos;
    private Date multiFechaActVideos;
    private int multiCantCamaras;
    private Date multiFechaActCamaras;
    //Otros Datos Popup
    private SofisComboG<CategoriaProyectos> listaCategoriaCombo;
    private SofisComboG<CategoriaProyectos> listaCategoriaSecCombo;
    private SofisComboG<OrganiIntProve> listaInstEjecCombo;
    private SofisComboG<OrganiIntProve> listaContratistaCombo;
    private SofisComboG<Entregables> listaInicioProdCombo;
    private List<String> listaCategoriaSecundaria = new ArrayList<String>();
    
    private int pagoEliminar;
    private int adqEliminar;
    private int pagoConfirmar;

    private List<SelectItem> metodologiaTipoDocumentoRequeridoDesde;

    // Localizacion
    private Boolean localizacionFormDataExpanded = false;
    private LatlngProyectos latlngProyectosAux;
    private DataTable latLngProyListDataTable;

    //Label Objetivo Estrategico
    private Configuracion confLabelObjEst;
    private String labelObjEstValue;

    //parche: Borrar
    private boolean primeraCargarGantt = true;
    private boolean recienCreado = false;
    private FuenteFinanciamiento fuenteFinanciamientoSelected;
    private ProcedimientoCompra procedimientoCompraSelected;
    /*
        *   19-09-18 Nico: Voy a poner este Mapa para poder asociar los coordinadores
        *           a los entregables, y así hacer el render de los botones
        *           para editar y eliminar documentos.
     */
    private Map<Integer, Boolean> auxAsocCoordEnt = new HashMap<Integer, Boolean>();

    /*
        *   20-03-18 Nico: Agrego esta variable booleana para saber si voy a editar
        *           un Pago o voy a darlo de alta.
     */
    boolean edicionPago;

    /*
        *   06-06-2018 Nico: Se agrega esta varibale para poder cargar el template desde el 
        *        programa cuando se consulta el histórico desde la pantalla expandida y luego se le da guardar.
     */
    boolean alAbrirEnTodaLaPantalla = false;

    /*
        *       Se agrega esta variable para conocer la última replanificación al momento
        *   de analizar el "fieldAttribute".
     */
    private ProyReplanificacion ultimaReplan;

    private Boolean devengadoMsgs;

    private Boolean aprobPMOF;

    private Boolean aprobReplanPMOF;
    private Boolean gerentesAsignanAreasTematicas;

//	public FichaMB() {
//	}
    @PostConstruct
    public void init() {

        /*
            *   31-05-2018 Nico: Se sacan las variables que se inicializan del constructor y se pasan al PostConstruct
         */
        logger.finest("-- CREA FichaMB");
        productosList = new ArrayList<Productos>();
        anioDev = new GregorianCalendar().get(Calendar.YEAR);

        metodologiaTipoDocumentoRequeridoDesde = new ArrayList<SelectItem>();
        getMetodologiaTipoDocumentoRequeridoDesde().add(new SelectItem(0, Labels.getValue("estado_NoExigido")));
        getMetodologiaTipoDocumentoRequeridoDesde().add(new SelectItem(2, Labels.getValue("estado_Inicio")));
        getMetodologiaTipoDocumentoRequeridoDesde().add(new SelectItem(3, Labels.getValue("estado_Planificacion")));
        getMetodologiaTipoDocumentoRequeridoDesde().add(new SelectItem(4, Labels.getValue("estado_Ejecucion")));
        getMetodologiaTipoDocumentoRequeridoDesde().add(new SelectItem(5, Labels.getValue("estado_Finalizado")));

        //las banderas
        renderPopupLectura = false;

        /*
                *   20-03-18 Nico: Variables de algunos chequeos
         */
        edicionPago = false;

        inicioMB.cargarOrganismoSeleccionado();

        if (inicioMB.getOrganismo() == null) {
            return;
        }
        Integer orgPk = inicioMB.getOrganismo().getOrgPk();
        if (orgPk == null) {
            return;
        }
        limiteAmarilloProd = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_AMARILLO, orgPk).getCnfValor());
        limiteRojoProd = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_ROJO, orgPk).getCnfValor());

        //Se cargan los valores necesarios al fichaTO.
        fichaTO = new FichaTO();

        //Inicializa el objeto para el alta de interesados
        interesado = new Interesados();
        interesado.setIntPersonaFk(new Personas());
        interesado.setIntRolintFk(new RolesInteresados());
        String programaProyectoId = (String) getFlashContext(ConstantesPresentacion.PROG_PROY_ID);
        if (programaProyectoId == null) {
            /**
             * Busco el parámetro en el request
             */
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRequest();
            programaProyectoId = (String) request
                    .getParameter(ConstantesPresentacion.PROG_PROY_ID);

            programaProyectoId = inicioMB.decryptParam(programaProyectoId);

            if (programaProyectoId == null && request != null) {
                programaProyectoId = (String) request.getSession().getAttribute(ConstantesPresentacion.PROG_PROY_ID);

                programaProyectoId = inicioMB.decryptParam(programaProyectoId);
            }

        }
        cargarCombosFicha();

        if (!StringsUtils.isEmpty(programaProyectoId)) {
            try {
                editarFichaAction(programaProyectoId);
            } catch (Exception ex) {
                try {
                    logger.log(Level.SEVERE, "error al cargar la ficha con el id: " + programaProyectoId, ex);
                    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                    ec.redirect("/SS-SIGES-web/paginasPrivadas/paginaInicioCliente.jsf");
                    return;
                } catch (IOException ex1) {
                    Logger.getLogger(FichaMB.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            if (!fichaTO.getActivo() || fichaTO.getOrgFk().getOrgPk() != inicioMB.getOrganismo().getOrgPk()) {
                try {
                    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                    ec.redirect("/SS-SIGES-web/paginasPrivadas/paginaInicioCliente.jsf");
                } catch (IOException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }

        } else {
            SsUsuario usuario = inicioMB.getUsuario();
            Organismos org = inicioMB.getOrganismo();
            fichaTO.setTipoFicha(TipoFichaEnum.PROYECTO.getValue());
            fichaTO.setPeso(riesgosCantAltos);
            listaGerenteCombo.setSelected(usuario.getUsuId());

            /**
             * 12-10-17 Bruno: agregadas las localizaciones
             */
            fichaTO.setLatLngProyList(new ArrayList<LatlngProyectos>());

            Areas usuArea = usuario.getUsuArea(org.getOrgPk());
            if (usuArea != null && usuArea.getAreaHabilitada()) {
                listaAreasOrganismoCombo.setSelected(usuArea.getAreaPk());
            }

            confRiesgoTiempoLimiteAmarillos = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_AMARILLO, inicioMB.getOrganismo().getOrgPk());
            if (confRiesgoTiempoLimiteAmarillos != null) {
                fichaTO.setSemaforoAmarillo(Integer.valueOf(confRiesgoTiempoLimiteAmarillos.getCnfValor()));
            }

            confRiesgoTiempoLimiteRojo = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_ROJO, inicioMB.getOrganismo().getOrgPk());
            if (confRiesgoTiempoLimiteRojo != null) {
                fichaTO.setSemaforoRojo(Integer.valueOf(confRiesgoTiempoLimiteRojo.getCnfValor()));
            }
        }

        // Se toma la configuración para ingresar el valor de la etiqueta de "Objetivo Estratégico"
        confLabelObjEst = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.LABEL_OBJ_ESTRE, inicioMB.getOrganismo().getOrgPk());

        if (confLabelObjEst == null) {
            labelObjEstValue = "Problema";
        } else {
            labelObjEstValue = confLabelObjEst.getCnfValor();
        }

        //listaUsuarios = ssUsuarioDelegate.busquedaUsuFiltro(inicioMB.getOrganismo(), null, null, null, null, true);
        listaUsuarios = aplicacionMB.obtenerTodosPorOrganismoActivos(orgPk);

        /*
                *   Se agrega esto para conocer la última replanificación
         */
        if (fichaTO.getFichaFk() != null) {
            ultimaReplan = proyReplanificacionDelegate.obtenerUltimaSolicitud(fichaTO.getFichaFk());
        } else {
            ultimaReplan = null;
        }

        confAdjPre = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.ADJUNTO_MODIFICA_PRESUPUESTO, inicioMB.getOrganismo().getOrgPk());
        frameMax = false;
        inicioMB.setContainerMax(false);
        cargarAreasTematicas();
        cerrarFormCollapsable();

        devengadoMsgs = false;

        /*
                *       Se chequea la configuración para conocer si el PMOF es el encargado de aprobar las solicitudes de cambio de fase.
                *   Se hace acá para no sobrecargar la operación "fieldAttribute".
         */
        Configuracion cnfAprobPMOF = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.APROBACION_PMOF, orgPk);

        if (cnfAprobPMOF != null && cnfAprobPMOF.getCnfValor().equals("true")) {
            aprobPMOF = Boolean.TRUE;
        } else {
            aprobPMOF = Boolean.FALSE;
        }

        Configuracion cnfAprobReplanPMOF = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.APROBACION_REPLANIFICACION_PMOF, orgPk);

        if (cnfAprobReplanPMOF != null && cnfAprobReplanPMOF.getCnfValor().equals("true")) {
            aprobReplanPMOF = Boolean.TRUE;
        } else {
            aprobReplanPMOF = Boolean.FALSE;
        }

        this.checkDatosEntregablesCronograma();

        gerentesAsignanAreasTematicas = configuracionDelegate.obtenerCnfValorPorCodigo(
            ConfiguracionCodigos.GERENTES_ASIGNAN_AREAS_TEMATICAS, orgPk).equals("true");
        
        largoMaximoIdAdquisicion = Integer
            .valueOf(configuracionDelegate.obtenerCnfValorPorCodigo(ConfiguracionCodigos.LARGO_MAXIMO_ID_ADQUISICION, inicioMB.getOrganismo().getOrgPk()));
    }

    @PreDestroy
    public void finish() {
        inicioMB.setContainerMax(false);
    }

    /**
     * Carga los valores de los combos utilizados en la pagina.
     */
    private void cargarCombosFicha() {
        logger.finest("-- FichaMB - CargarCombos --");
        Integer orgPk = inicioMB.getOrganismo().getOrgPk();

        listaAreas = areasDelegate.obtenerAreasPorOrganismo(orgPk, true);
//        listaAreas = aplicacionMB.obtenerAreasPorOrganismo(orgPk);
        if (listaAreas != null && !listaAreas.isEmpty()) {
            listaAreasOrganismoCombo = new SofisCombo((List) listaAreas, "areaNombre");
            listaAreasOrganismoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
            Areas usuArea = inicioMB.getUsuario().getUsuArea(orgPk);
            // 9-12-2016 selecciona el área del usuario en caso de estar habilitada
            if (usuArea != null && listaAreas.contains(usuArea)) {
                listaAreasOrganismoCombo.setSelected(usuArea.getAreaPk());
            }
        }

//		listaProgramas = programaDelegate.obtenerProgIniciadoPorOrg(orgPk);
        listaProgramas = programaDelegate.obtenerProgComboPorOrg(orgPk, true);
        if (listaProgramas != null && !listaProgramas.isEmpty()) {
            listaProgramasCombo = new SofisCombo((List) listaProgramas, "nombreComboFicha");
            listaProgramasCombo.addEmptyItem(Labels.getValue("comboProgramasFichaEmpty"));
        }

        //la lista de usuarios con rol Director son los que se pueden seleccionar como sponsor.
        //String[] ordenUsuarios = new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"};
        //boolean[] ascUsuarios = new boolean[]{true, true, true, true};
        //List<SsUsuario> listaSponsor = ssUsuarioDelegate.obtenerUsuariosPorRol(SsRolCodigos.DIRECTOR, orgPk, ordenUsuarios, ascUsuarios);
        List<SsUsuario> listaSponsor = aplicacionMB.obtenerUsuariosPorRolActivos(SsRolCodigos.DIRECTOR, orgPk);
        if (listaSponsor != null && !listaSponsor.isEmpty()) {
            listaSponsorCombo = new SofisCombo((List) listaSponsor, "usuNombreApellido");
            listaSponsorCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        //la lista de usuarios de la organizacion son los que se puede selecionar como adjunto.
        List<SsUsuario> listaAdjunto = aplicacionMB.obtenerTodosPorOrganismoActivos(orgPk);//ssUsuarioDelegate.obtenerTodosPorOrganismo(orgPk);
        if (listaAdjunto != null && !listaAdjunto.isEmpty()) {
            listaAdjuntoCombo = new SofisCombo((List) listaAdjunto, "usuNombreApellido");
            listaAdjuntoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        //la lista de los usuarios de la organizacion, son los que se pueden seleccionar como gerente
        List<SsUsuario> listaGerente = aplicacionMB.obtenerTodosPorOrganismoActivos(orgPk);//ssUsuarioDelegate.obtenerTodosPorOrganismo(orgPk);
        if (listaGerente != null && !listaGerente.isEmpty()) {
            listaGerenteCombo = new SofisCombo((List) listaGerente, "usuNombreApellido");
            listaGerenteCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        //la lista de usuarios con rol PMO Federeda
        //List<SsUsuario> listaPmoFederada = ssUsuarioDelegate.obtenerUsuariosPorRol(new String[]{SsRolCodigos.PMO_FEDERADA, SsRolCodigos.PMO_TRANSVERSAL}, orgPk, new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"}, new boolean[]{true, true, true, true});
        List<SsUsuario> listaPmoFederada = aplicacionMB.obtenerUsuariosPorRolActivos(new String[]{SsRolCodigos.PMO_FEDERADA, SsRolCodigos.PMO_TRANSVERSAL}, orgPk);
        listaPmoFederada = SsUsuariosUtils.sortByNombreApellido(listaPmoFederada);
        if (listaPmoFederada != null && !listaPmoFederada.isEmpty()) {
            listaPmoFederadaCombo = new SofisCombo((List) listaPmoFederada, "usuNombreApellido");
            listaPmoFederadaCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        //TODO pasarlas al managedBean
        listaMonedas = monedaDelegate.obtenerMonedas();
        if (listaMonedas != null && !listaMonedas.isEmpty()) {
            listaMonedaPreCombo = new SofisCombo((List) listaMonedas, "monSigno");
            listaMonedaPreCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        Map<String, Object> filtroFuentes = new HashMap<>();
        filtroFuentes.put("habilitada", true);

        listaFuentes = fuenteFinanciamientoDelegate.busquedaFuenteFiltro(orgPk, filtroFuentes, "fueNombre", 1);

        if (listaFuentes != null && !listaFuentes.isEmpty()) {
            listaFuentesPreCombo = new SofisCombo((List) listaFuentes, "fueNombre");
            listaFuentesPreCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        listaComponenteProducto = componenteProductoDelegate.obtenerComponentesProductosPorOrgId(orgPk);
        if (listaComponenteProducto != null && !listaComponenteProducto.isEmpty()) {
            listaComponenteProductoCombo = new SofisCombo((List) listaComponenteProducto, "comNombre");
            listaComponenteProductoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        List<Etapa> listEtapas = etapaDelegate.obtenerEtapaPorOrgId(orgPk);
        if (listEtapas != null) {
            listaEtapaPubCombo = new SofisCombo((List) listEtapas, "etaNombre");
            listaEtapaPubCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        FiltroObjectivoEstategicoTO filtroObjEstTO = new FiltroObjectivoEstategicoTO();
        filtroObjEstTO.setOrganismo(inicioMB.getOrganismo());
        filtroObjEstTO.setHabilitado(true);

        List<ObjetivoEstrategico> listaObjetivosEstrategicos = objetivoEstrategicoDelegate.obtenerPorFiltro(filtroObjEstTO);
        if (listaObjetivosEstrategicos != null) {
            listaObjetivosEstrategicosCombo = new SofisComboG<>(listaObjetivosEstrategicos, "objEstNombre");
            listaObjetivosEstrategicosCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        //Carga los procedimientos de compra
        listaProcedimientoCompra = procedimiComponenteProductoDelegate.obtenerProcedimientosComprasPorOrgId(orgPk);
        if (listaProcedimientoCompra != null && !listaProcedimientoCompra.isEmpty()) {
            listaProcedimientoCompraCombo = new SofisCombo((List) listaProcedimientoCompra, "procCompNombre");
            listaProcedimientoCompraCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        //Carga los identificadores GRP/ERP
        final FiltroIdentificadorGrpErpTO filtroIdentificadorGrpErpTO = new FiltroIdentificadorGrpErpTO();
        filtroIdentificadorGrpErpTO.setOrganismo(this.getInicioMB().getOrganismo());
        filtroIdentificadorGrpErpTO.setHabilitado(true);
        listaIdentificadorGrpErp = identificadorGrpErpDelegate.obtenerPorFiltro(filtroIdentificadorGrpErpTO);
        if (listaIdentificadorGrpErp != null && !listaIdentificadorGrpErp.isEmpty()) {
            listaIdentificadorGrpErpCombo = new SofisComboG((List) listaIdentificadorGrpErp, "idGrpErpNombre");
            listaIdentificadorGrpErpCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        //la lista de usuarios que no son ni Editores ni Usuarios Externos
        listaUsuariosAdqCompartida = aplicacionMB.obtenerUsuariosPorRolActivos(new String[]{SsRolCodigos.PMO_FEDERADA, SsRolCodigos.PMO_TRANSVERSAL, SsRolCodigos.PMO_FEDERADA, SsRolCodigos.DIRECTOR, SsRolCodigos.USUARIO_COMUN}, orgPk);
        if (listaUsuariosAdqCompartida != null && !listaUsuariosAdqCompartida.isEmpty()) {
            listaUsuariosAdqCompartidaCombo = new ArrayList<>();
            for (SsUsuario u : listaUsuariosAdqCompartida) {
                Areas a = u.getUsuArea(orgPk);
                listaUsuariosAdqCompartidaCombo.add(new SelectItem(u.hashCode(), (a != null ? a.getAreaNombre() : "SIN ÁREA") + " - " + u.getUsuNombreApellido()));
            }
            Collections.sort(listaUsuariosAdqCompartidaCombo, AplicacionMB.COMBO_COMPARTOR);
            listaUsuariosAdqCompartidaCombo.add(0, new SelectItem(-1, Labels.getValue("comboEmptyItem")));
        }

        List<TipoRegistroCompra> tipoRegistroCompra = Arrays.asList(TipoRegistroCompra.values());
        listaTipoRegistroCompraCombo = new SofisComboG(tipoRegistroCompra, "text");
        listaTipoRegistroCompraCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        listaTipoRegistroCompraCombo.setSelectedT(TipoRegistroCompra.COMPRA_ORIGINAL); // default COMPRA_ORIGINAL

        // cargar lista tipo adquisiciones
        FiltroTipoAdquisicionTO filtroAdquisicion = new FiltroTipoAdquisicionTO();
        filtroAdquisicion.setHabilitado(Boolean.TRUE);
        filtroAdquisicion.setOrganismo(this.getInicioMB().getOrganismo());
        List<TipoAdquisicion> tipoAdquisiciones = tipoAdquisicionDelegate.obtenerPorFiltro(filtroAdquisicion);
        listaTipoAdquisicionCombo = new SofisComboG<>(tipoAdquisiciones, "tipAdqNombre");
        listaTipoAdquisicionCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

        // cargo lista centro costo
        FiltroCentroCostoTO filtroCentroCosto = new FiltroCentroCostoTO();
        filtroCentroCosto.setHabilitado(Boolean.TRUE);
        filtroCentroCosto.setOrganismo(this.getInicioMB().getOrganismo());
        List<CentroCosto> centros = centroCostoDelegate.obtenerPorFiltro(filtroCentroCosto);
        listaCentroCostoCombo = new SofisComboG<>(centros, "cenCosNombre");
        listaCentroCostoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
    }
    
    private void actualizarComboCausales() {
        // Cargo lista vacía
        List<CausalCompra> causales = new ArrayList<>();
        listaCausalCompraCombo = new SofisComboG<>(causales, "cauComNombre");
        listaCausalCompraCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

        // obtengo causales en base a la tabla N-N, si tiene fuente y procedimiento de compra
        if (fuenteFinanciamientoSelected != null && procedimientoCompraSelected != null) {
            FuenteProcedimientoCompra fuenteProcedimientoCompra = fuenteProcedimientoCompraDelegate.obtenerPorFuenteProcedimientoCompra(fuenteFinanciamientoSelected, procedimientoCompraSelected);
            if (fuenteProcedimientoCompra != null) {
                causales = fuenteProcedimientoCompra.getFueProComCausalesCompra();
                listaCausalCompraCombo = new SofisComboG<>(causales, "cauComNombre");
                listaCausalCompraCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
            }
        }

    }

    private void cargarOtrosDatos() {
        Set<Entregables> setEntAvance = null;
        ProyOtrosDatos pod = fichaTO.getOtrosDatos();
        if (fichaTO.getCroFk() != null) {
            if (pod != null && pod.getProyOtrEntFk() != null) {
                List<Entregables> entHijos = EntregablesUtils.obtenerHijos(new ArrayList<>(fichaTO.getCroFk().getEntregablesSet()), pod.getProyOtrEntFk(), Boolean.TRUE);
                setEntAvance = new HashSet<>(entHijos);
            } else {
                setEntAvance = fichaTO.getCroFk().getEntregablesSet();
            }
        }
        indiceAvanceProd = cronogramaDelegate.calcularAvanceCronoParcial(setEntAvance);
        Integer porcAvance = proyectoDelegate.porcentajeAvanceEnTiempoLBase(setEntAvance);
        indiceAvanceTiempo = porcAvance != null ? new int[]{porcAvance, (100 - porcAvance), 0} : null;
    }

    public void cargarResumenes() {
        logger.fine("Cargar los datos necesarios para los Resumenes de la ficha.");
        cargarResumenDocumentos();
        cargarResumenCronograma();
        cargarResumenRiesgos();
        cargarResumenInteresados();
        cargarResumenPresupuesto();
        cargarResumenParticipantes();
        cargarResumenProductos();
        cargarFrameMultimedia(false);
        cargarOtrosDatos();

    }

    public void cargarDocFiles() {
        if (fichaTO != null) {
            String dir = configuracionDelegate.obtenerCnfValorPorCodigo(
                    ConfiguracionCodigos.DOCUMENTOS_DIR,
                    null);
            dir += "/" + fichaTO.getOrgFk().getOrgPk();

            if (fichaTO.getResumenEjecutivo() != null) {
                Integer docPk = fichaTO.getResumenEjecutivo().getDocsPk();
                DocFile df = documentosDelegate.obtenerDocFilePorDocId(docPk);

                if (df != null) {
                    downloadFile.put(docPk, new SofisResource(df, dir));
                }
            }
            if (fichaTO.getDocumentos() != null) {
                List<DocFile> listDf = documentosDelegate.obtenerDocFilePorDocId(fichaTO.getDocumentos());
                if (listDf != null && !listDf.isEmpty()) {
                    for (DocFile df : listDf) {
                        if (df != null) {
                            downloadFile.put(df.getDocfileDocFk().getDocsPk(), new SofisResource(df, dir));
                        }
                    }
                }
            }
        }
    }

    private void cargarResumenDocumentos() {
        if (fichaTO.hasPk()) {
            List<Documentos> listDoc = documentosDelegate.obtenerDocumentosOrderByFecha(fichaTO.getFichaFk(), fichaTO.getTipoFicha());
            fichaTO.setDocumentos(listDoc);

            if (fichaTO.isPrograma()) {
                if (fichaTO.getProgIndices() != null) {
                    indiceEstado = NumbersUtils.redondearDecimales(fichaTO.getProgIndices().getProgindMetodologiaEstado());
                }

            } else if (fichaTO.isProyecto()) {
                if (fichaTO.getProyIndices() != null) {
                    indiceEstado = NumbersUtils.redondearDecimales(fichaTO.getProyIndices().getProyindMetodologiaEstado());
                }

            }

            if (indiceEstado != null && indiceEstado.isNaN()) {
                indiceEstado = null;
            }
            indiceMetodologiaColor = documentosDelegate.calcularIndiceEstadoMetodologiaColor(indiceEstado, inicioMB.getOrganismo().getOrgPk(), null);

            listaTipoDocInstResumen = tipoDocumentoInstanciaDelegate.obtenerTipoDocInstResumen(fichaTO.getFichaFk(), fichaTO.getTipoFicha(), fichaTO.getEstado(), 5);
            documentosDelegate.obtenerEstadosColorTipoDocInst(listaTipoDocInstResumen, fichaTO.getEstado());
        }
    }

    private void cargarResumenInteresados() {
        if (fichaTO.hasPk()) {
            listaInteresadosResumen = interesadosDelegate.obtenerIntersadosResumen(fichaTO.getFichaFk(), fichaTO.getTipoFicha(), 5);
        }
    }

    public void cargarResumenProductos() {
        if (fichaTO.hasPk()) {
            productosIndiceCantYPorc(anioDev, true);

            listaProductosResumen = productosDelegate.obtenerResumenAtrasados(fichaTO.getFichaFk(), 5);
            listaProductosResumen = ProductosUtils.sortByEntProdNombre(listaProductosResumen, true);
        }
    }

    public void cargarResumenRiesgos() {
        if (fichaTO.hasPk()) {
            if (fichaTO.isProyecto()) {
                riesgoActualizacion = null;
                riesgoActualizacionColor = ConstantesEstandares.COLOR_TRANSPARENT;
                exposicionRiesgo = null;
                exposicionRiesgoColor = ConstantesEstandares.COLOR_TRANSPARENT;
                riesgosCantAltos = null;

                if (fichaTO.getProyIndices() != null) {
                    riesgoActualizacion = fichaTO.getProyIndices().getProyindRiesgoUltact();
                    riesgoActualizacionColor = riesgosDelegate.obtenerColorUltimaActualizacion(riesgoActualizacion, inicioMB.getOrganismo().getOrgPk());
                    exposicionRiesgo = NumbersUtils.redondearDecimales(fichaTO.getProyIndices().getProyindRiesgoExpo());
                    exposicionRiesgoColor = riesgosDelegate.obtenerExposicionRiesgoColor(exposicionRiesgo, inicioMB.getOrganismo().getOrgPk());
                    riesgosCantAltos = fichaTO.getProyIndices().getProyindRiesgoAlto();
                }
            }

            listaRiesgosResumen = riesgosDelegate.obtenerExposicionResumen(fichaTO.getFichaFk(), 5);
            riesgosDelegate.obtenerExposicionRiesgoColor(listaRiesgosResumen, inicioMB.getOrganismo().getOrgPk());
        }
    }

    public Double getPrespuestoTablaDinamicaValor(TablaDinamicaPresupuestoTO t, Integer monPk) {
        if (fichaTO.getPreFk() != null) {
            if (t.getCodigo() == 1) {
//                return presupuestoDelegate.obtenerTotalPorMoneda(fichaTO.getPreFk().getPrePk(), new Moneda(monPk));
                Set<ProyIndicesPre> pres = fichaTO.getProyIndices().getProyIndPreSet();
                if (pres != null) {
                    for (ProyIndicesPre p : pres) {
                        if (p.getProyindpreMonFk() == monPk.intValue()) {
                            return p.getProyindpreTotal();
                        }
                    }
                }
            }
            if (t.getCodigo() == 2) {
                //ToDo: materializar
                return presupuestoDelegate.obtenerTotalPorMonedaAnio(fichaTO.getPreFk().getPrePk(), new Moneda(monPk), new Integer(t.getTitle()));
            }
            if (t.getCodigo() == 3) {
                //ToDo: materializar
                //return presupuestoDelegate.obtenerPVPorMoneda(fichaTO.getPreFk().getPrePk(), new Moneda(monPk));
                Set<ProyIndicesPre> pres = fichaTO.getProyIndices().getProyIndPreSet();
                if (pres != null) {
                    for (ProyIndicesPre p : pres) {
                        if (p.getProyindpreMonFk() == monPk.intValue()) {
                            return p.getProyindprePV();
                        }
                    }
                }
            }
            if (t.getCodigo() == 4) {
                //ToDo: materializar
                //return presupuestoDelegate.obtenerACPorMoneda(fichaTO.getPreFk().getPrePk(), new Moneda(monPk));
                Set<ProyIndicesPre> pres = fichaTO.getProyIndices().getProyIndPreSet();
                if (pres != null) {
                    for (ProyIndicesPre p : pres) {
                        if (p.getProyindpreMonFk() == monPk.intValue()) {
                            return p.getProyindpreAC();
                        }
                    }
                }
            }
        }
        return null;
    }

    public String getPrespuestoTablaDinamicaColor(TablaDinamicaPresupuestoTO t, Integer monPk) {
        if (fichaTO.getPreFk() != null) {
            if (t != null && t.getCodigo() == 4) {
                //return presupuestoDelegate.obtenerColorAC(fichaTO.getPreFk().getPrePk(), monPk, inicioMB.getOrganismo().getOrgPk(), null, null);
                Set<ProyIndicesPre> pres = fichaTO.getProyIndices().getProyIndPreSet();
                if (pres != null) {
                    for (ProyIndicesPre p : pres) {
                        if (p.getProyindpreMonFk() == monPk.intValue()) {
                            return programasProyectosDelegate.obtenerColorEstadoAcFromCodigo(p.getProyindpreEstPre());
                        }
                    }
                }
            }
        }
        return "";
    }

    public void cargarResumenPresupuesto() {
        if (fichaTO.getPreFk() != null) {
            monedasPresupuesto = presupuestoDelegate.obtenerMonedasPresupuesto(fichaTO.getPreFk().getPrePk(), null);
            presupuestoResumen = new ArrayList<TablaDinamicaPresupuestoTO>();

            TablaDinamicaPresupuestoTO t = new TablaDinamicaPresupuestoTO();
            t.setTitle(Labels.getValue("presupuesto_resumen_total"));
            t.setCodigo(1);
            presupuestoResumen.add(t);

            Date d = new Date();
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(d);

            Integer year = cal.get(Calendar.YEAR);

            t = new TablaDinamicaPresupuestoTO();
            t.setTitle("" + year);
            t.setCodigo(2);
            presupuestoResumen.add(t);

            t = new TablaDinamicaPresupuestoTO();
            t.setTitle(Labels.getValue("presupuesto_resumen_pv"));
            t.setCodigo(3);
            presupuestoResumen.add(t);

            t = new TablaDinamicaPresupuestoTO();
            t.setTitle(Labels.getValue("presupuesto_resumen_ac"));
            t.setCodigo(4);
            presupuestoResumen.add(t);
        }
    }

    public void cargarResumenParticipantes() {
        listaParticipantes = participantesDelegate.obtenerParticipantesConHoraYGasto(fichaTO.getFichaFk());

        // Revisar esta-
        //listaParticipanteResumenMonedaConsolidado = new ArrayList<MonedaImporteResumenTO>();
        double listaParticipanteHorasPendientes = 0d;
        double listaParticipanteHorasAprobadas = 0d;

        Map<Moneda, MonedaImporteResumenTO> monImpor = new HashMap<Moneda, MonedaImporteResumenTO>();

        int count = 0;
        int size = 8;
        listaParticipantesResumen.clear();
        if (CollectionsUtils.isNotEmpty(listaParticipantes)) {
            List<Participantes> listaPart = new ArrayList<Participantes>(listaParticipantes);
            listaPart = ParticipantesUtils.sortByHorasPendientes(listaPart, false);
            for (Participantes part : listaPart) {
                if (count >= size) {
                    break;
                }

                //verifica que tenga gasto
                String gast = part.getGastosPendientesTxt();
                if ((part.getHorasPendientes() != null && part.getHorasPendientes() > 0)
                        || (gast != null && !gast.trim().equalsIgnoreCase(""))) {
                    listaParticipantesResumen.add(part);
                }

                if (part.getHorasPendientes() != null) {
                    listaParticipanteHorasPendientes += part.getHorasPendientes();
                }
                if (part.getHorasAprobadas() != null) {
                    listaParticipanteHorasAprobadas += part.getHorasAprobadas();
                }

                for (MonedaImporteTO m : part.getGastosAprobados()) {
                    if (monImpor.get(m.getMoneda()) == null) {
                        monImpor.put(m.getMoneda(), new MonedaImporteResumenTO(m.getMoneda(), 0d, 0d));
                    }
                    MonedaImporteResumenTO monImporteApro = monImpor.get(m.getMoneda());
                    monImporteApro.addImporteApro(m.getImporte());
                }

                for (MonedaImporteTO m : part.getGastosPendientes()) {
                    if (monImpor.get(m.getMoneda()) == null) {
                        monImpor.put(m.getMoneda(), new MonedaImporteResumenTO(m.getMoneda(), 0d, 0d));
                    }
                    MonedaImporteResumenTO monImportePend = monImpor.get(m.getMoneda());
                    monImportePend.addImportePend(m.getImporte());
                }

                if (monImpor != null) {
                    Set<Moneda> keys = monImpor.keySet();
                    Map<Moneda, MonedaImporteResumenTO> monImporDatos = new HashMap<Moneda, MonedaImporteResumenTO>();
                    for (Moneda key : keys) {
                        MonedaImporteResumenTO mir = monImpor.get(key);
                        if (!mir.getImporteApro().equals(0D) && !mir.getImportePend().equals(0D)) {
                            monImporDatos.put(key, mir);
                        }
                    }
                    monImpor = monImporDatos;
                }

                listaParticipanteResumenMonedaConsolidado = new ArrayList<MonedaImporteResumenTO>();
                listaParticipanteResumenMonedaConsolidado.add(new MonedaImporteResumenTO(null, listaParticipanteHorasAprobadas, listaParticipanteHorasPendientes));
                listaParticipanteResumenMonedaConsolidado.addAll(monImpor.values());
            }
        }
    }

    private void setNodosForAreaRestringida() {
        for (Areas areas : listaAreas) {
            if (areas.getAreaPadre() == null || areas.getAreaPadre().getAreaPk() == null) {
                DefaultMutableTreeNode nodoPadre = new DefaultMutableTreeNode();
                nodoPadre.setAllowsChildren(true);
                nodoPadre.setUserObject(areas);
                nodoPadre = setNodosHijosAreasRestringidas(nodoPadre, areas);

                setAreasRestStateMap(areas, nodoPadre);
                listaAreasTreeNode.add(nodoPadre);
            }
        }
    }

    private DefaultMutableTreeNode setNodosHijosAreasRestringidas(DefaultMutableTreeNode nodoPadre, Areas areasPadre) {
        if (nodoPadre != null && areasPadre != null) {
            for (Areas areas : listaAreas) {
                if (areas.getAreaPk() != null
                        && areas.getAreaPadre() != null
                        && areas.getAreaPadre().getAreaPk().equals(areasPadre.getAreaPk())) {
                    DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode();
                    nodoHijo.setAllowsChildren(true);
                    nodoHijo.setUserObject(areas);
                    setNodosHijosAreasRestringidas(nodoHijo, areas);

                    setAreasRestStateMap(areas, nodoHijo);
                    nodoPadre.add(nodoHijo);
                }
            }
        }
        return nodoPadre;
    }

    private void setAreasRestStateMap(Areas area, DefaultMutableTreeNode nodo) {
        NodeState ns = new NodeState();
        if (fichaTO.getAreasRestringidas() != null && !fichaTO.getAreasRestringidas().isEmpty()
                && fichaTO.getAreasRestringidas().contains(area)) {
            ns.setSelected(true);
        }
        if (areasRestringidasStateMap == null) {
            areasRestringidasStateMap = new NodeStateMap();
        }
        areasRestringidasStateMap.put(nodo, ns);
    }

    public String recargarFicha() {
        if (fichaTO.getFichaFkTipo().equalsIgnoreCase(TipoFichaEnum.PROYECTO.name())) {
            Integer proyPk = fichaTO.getFichaFk();
            proyectoDelegate.controlarEntregables(proyPk, false);
            proyectoDelegate.controlarProdAcumulados(proyPk, true);
            proyectoDelegate.guardarIndicadoresSimple(proyPk, false, true, fichaTO.getOrgFk().getOrgPk(), null, true);
        } else if (fichaTO.getFichaFkTipo().equalsIgnoreCase(TipoFichaEnum.PROGRAMA.name())) {
            Integer progPk = fichaTO.getFichaFk();
            programaDelegate.guardarIndicadoresSimple(progPk, fichaTO.getOrgFk().getOrgPk());
            programaDelegate.actualizarProgramaPorProyectos(progPk, inicioMB.getUsuario(), "web");
        }

        return inicioMB.irEditarProgramaProyecto(fichaTO.getFichaFkTipo(), false);
    }

    /**
     * Actualiza el Programa o Proyecto en la fichaTO. Si el objeto aportado es
     * null, lo obtiene de base según el id y tipo contenido en el fichaTO.
     *
     * @param progProy
     */
    public void actualizarFichaTO(Object progProy) {
        try {
            if (progProy == null) {
                progProy = progProyDelegate.obtenerProgProyPorId(fichaTO);
            }

            if (progProy != null) {
                if (progProy instanceof Programas) {
                    programaToFichaTO((Programas) progProy);
                } else if (progProy instanceof Proyectos) {
                    proyectoToFichaTO((Proyectos) progProy);
                }
            }
        } catch (GeneralException ge) {
            logger.log(Level.SEVERE, null, ge);

            /*
                       *  18-06-2018 Inspección de código.
             */
            //JSFUtils.agregarMsg(FICHA_MSG_ID, "error_ficha_actualizar", null);
            for (String iterStr : ge.getErrores()) {
                JSFUtils.agregarMsgError(FICHA_MSG_ID, Labels.getValue(iterStr), null);
            }

        }
    }

    /**
     * Guarda la ficha sin solicitar el cambio de estado
     *
     * @return
     */
    public String guardarFichaAction() {
        /*
                *   25-04-2018   Nico: Agrego el control para cambiar el valor de la variable "recienCreado", porque cuando se creaba un proyeto nuevo y se 
                *           ingresaba un cronograma desde un plantilla en el mismo momento que se crea, el gantt se mostraba mal.
         */
        if (fichaTO.getFichaFk() == null) {
            recienCreado = true;
        }

        Programas prog = fichaTO.getProgFk();

        setCombosToFichaTO();
        setAreasRestringidasToFichaTO();
        setAreasTematicasToFichaTO();
        if (fichaTO.getOrgFk() == null) {
            fichaTO.setOrgFk(inicioMB.getOrganismo());
        }
        Object obj = null;

        try {
                      
            fichaTO.setFechaAct(obtenerFechaAct());
            FichaValidacion.validarTexto(fichaTO);      
            
            FichaValidacion.validar(fichaTO, inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
            obj = progProyDelegate.guardarProgProy(fichaTO, inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
            if (obj != null) {
                JSFUtils.agregarMsg(FICHA_MSG_ID, "info_ficha_guardada", null);
            }

            if (prog != null && prog != fichaTO.getProgFk()) {
                programaDelegate.actualizarProgramaPorProyectos(prog.getProgPk(), inicioMB.getUsuario(), "web");
            }

        } catch (BusinessException | TechnicalException w) {
            logger.log(Level.SEVERE, Labels.getMessage(w), w);

            /*
                         *  18-06-2018 Inspección de código.
             */
            //JSFUtils.agregarMsgs(FICHA_MSG_ID, w.getErrores());
            for (String iterStr : w.getErrores()) {
                String mensaje = Labels.getValue(iterStr).startsWith("?") ? iterStr : Labels.getValue(iterStr);
                JSFUtils.agregarMsgError(FICHA_MSG_ID, mensaje, null);
            }

            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);

            /*
                         *  18-06-2018 Inspección de código.
             */
            //JSFUtils.agregarMsg(FICHA_MSG_ID, "error_ficha_guardar", null);
            JSFUtils.agregarMsgError(FICHA_MSG_ID, Labels.getValue("error_ficha_guardar"), null);

            return null;
        }

        actualizarFichaTO(obj);
        cerrarPopupAreaTematica();
        cerrarPopupLectura();
        listaAreasTreeNode = null;
        areasRestringidasStateMap = null;
        return null;
    }

    public String eliminarFichaAction() {
        return eliminarFichaAction(this.fichaTO);
    }

    public String eliminarFichaAction(FichaTO fichaTO) {

        try {
            Object progProy = progProyDelegate.eliminarProgProy(fichaTO, inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
            JSFUtils.removerMensages();
            if (progProy instanceof Programas) {
                JSFUtils.agregarMsg(FICHA_MSG_ID, "info_ficha_eliminada", null);
            } else if (progProy instanceof Proyectos) {
                if (!((Proyectos) progProy).getActivo()) {
                    JSFUtils.agregarMsg(FICHA_MSG_ID, "info_ficha_eliminada", null);
                } else {
                    JSFUtils.agregarMsg(FICHA_MSG_ID, "info_ficha_pend_cancelar", null);
                }
            }
            actualizarFichaTO(progProy);

        } catch (GeneralException ge) {
            logger.log(Level.SEVERE, null, ge);
            /*
                        *  18-06-2018 Inspección de código.
             */

            //JSFUtils.agregarMsg(FICHA_MSG_ID, ge.getMessage(), null);
            for (String iterStr : ge.getErrores()) {
                JSFUtils.agregarMsgError(FICHA_MSG_ID, Labels.getValue(iterStr), null);
            }

        }

        return null;
    }

    public String retrocederEstadoFichaAction() {
        if (fichaTO.isProyecto()) {
            if (fichaTO.getEstado().isEstado(Estados.ESTADOS.EJECUCION.estado_id)) {
                if (SsUsuariosUtils.isUsuarioPMOF(fichaTO, inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk())) {
                    replanificacion = new ProyReplanificacion();
                    replanificacion.setProyectoFk(new Proyectos(fichaTO.getFichaFk()));
                    replanificacion.setProyreplanActivo(true);
                    replanificacion.setProyreplanHistorial(Boolean.TRUE);

                    renderPopupReplanificacion = true;
                } else if (inicioMB.getUsuario().isUsuarioPMOT(inicioMB.getOrganismo().getOrgPk())) {
                    if (fichaTO.getEstadoPendiente() != null
                            && fichaTO.getEstadoPendiente().isEstado(Estados.ESTADOS.PLANIFICACION.estado_id)) {
                        replanificacion = proyReplanificacionDelegate.obtenerUltimaSolicitud(fichaTO.getFichaFk());
                        renderPopupReplanificacion = true;
                    } else {
                        replanificacion = new ProyReplanificacion();
                        replanificacion.setProyectoFk(new Proyectos(fichaTO.getFichaFk()));
                        replanificacion.setProyreplanActivo(true);
                        replanificacion.setProyreplanHistorial(Boolean.TRUE);

                        renderPopupReplanificacion = true;
                    }
                } else {
                    /*
                                        *  18-06-2018 Inspección de código.
                     */

                    //JSFUtils.agregarMsg(FICHA_MSG_ID, "error_modificar_estado", null);
                    JSFUtils.agregarMsgError(FICHA_MSG_ID, Labels.getValue("error_modificar_estado"), null);
                }
            } else {
                this.guardarRetrocederEstado();
            }
        }
        return null;
    }

    public String guardarRetrocederEstado() {
        try {
            if (replanificacion != null && StringsUtils.isEmpty(replanificacion.getProyreplanDesc())) {
                /*
                            *  18-06-2018 Inspección de código.
                 */
                JSFUtils.agregarMsgError(REPLANIFICACION_POPUP_MSG, Labels.getValue("error_ficha_msg_retroceder_descripcion_obligatorio"), null);
                return null;
            } else {
                if (fichaTO.getEstadoPendiente() != null
                        && fichaTO.getEstadoPendiente().isEstado(Estados.ESTADOS.PLANIFICACION.estado_id)) {
                    replanificacion.setProyreplanActivo(Boolean.FALSE);
                }

                progProyDelegate.guardarRetrocederEstado(fichaTO.getFichaFk(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk(), replanificacion);
                renderPopupReplanificacion = false;
                JSFUtils.agregarMsg(FICHA_MSG_ID, "ficha_msg_retroceder_estado", null);

                /*
                            *   Actualizo la última replanificación para consultar en el "fieldAttribute"
                 */
                ultimaReplan = proyReplanificacionDelegate.obtenerUltimaSolicitud(fichaTO.getFichaFk());
            }
        } catch (GeneralException ge) {
            logger.log(Level.SEVERE, null, ge);
            /*
                        *  18-06-2018 Inspección de código.
             */

            //JSFUtils.agregarMsgs(REPLANIFICACION_POPUP_MSG, ge.getErrores());
            for (String iterStr : ge.getErrores()) {
                JSFUtils.agregarMsgError(REPLANIFICACION_POPUP_MSG, Labels.getValue(iterStr), null);
            }

        }

        actualizarFichaTO(null);
        cargarResumenes();
        cargarFrameDocumentos();
        cerrarFormCollapsable();
        return null;
    }

    public String cancelarReplanificacion() {
        try {

            if (replanificacion.getProyreplanPk() != null) {
                replanificacion.setProyreplanActivo(false);
                proyectoDelegate.guardarProyectoRechazarCambioEstado(fichaTO.getFichaFk(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk(), replanificacion);
                JSFUtils.agregarMsg(FICHA_MSG_ID, "ficha_msg_replanificacion_descartada", null);
            }
            renderPopupReplanificacion = false;

        } catch (GeneralException ge) {
            /*
                *  18-06-2018 Inspección de código.
             */
            for (String iterStr : ge.getErrores()) {
                JSFUtils.agregarMsgError(FICHA_MSG_ID, Labels.getValue(iterStr), null);
            }

        }

        actualizarFichaTO(null);
        cargarResumenes();
        cargarFrameDocumentos();
        cerrarFormCollapsable();
        return null;
    }

    public String cerrarPopupReplanificacion() {
        renderPopupReplanificacion = false;
        return null;
    }

    public String cerrarPopupNotificacion() {
        renderPopupNotificacion = false;
        return null;
    }

    /**
     * Si tiene solicitud de aprobacion cambia el estado, si no, marca la
     * solicitud de aprobacion. Luego persiste el Programa o Proyecto.
     *
     * @return
     */
    public String guardarAprobarFichaAction() {
        setCombosToFichaTO();
        if (fichaTO.getOrgFk() == null) {
            fichaTO.setOrgFk(inicioMB.getOrganismo());
        }

        guardarAprobacion();

        return null;
    }

    private void guardarAprobacion() {
        setCombosToFichaTO();
        setAreasRestringidasToFichaTO();
        setAreasTematicasToFichaTO();
        removerDocumentosDummy(fichaTO);

        try {
            FichaValidacion.validar(fichaTO, inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
            Object obj = progProyDelegate.guardarAprobacion(fichaTO, inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
            JSFUtils.agregarMsg(FICHA_MSG_ID, "info_ficha_aprobacion_guardada", null);

            actualizarFichaTO(obj);
            cargarResumenes();
            cerrarFormCollapsable();
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            /*
                        *  18-06-2018 Inspección de código.
             */

            //JSFUtils.agregarMsgs(FICHA_MSG_ID, ex.getErrores());
            for (String iterStr : ex.getErrores()) {
                JSFUtils.agregarMsgError(FICHA_MSG_ID, Labels.getValue(iterStr), null);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            /*
                        *  18-06-2018 Inspección de código.
             */
            //JSFUtils.agregarMsg(FICHA_MSG_ID, "error_aprobacion_fase", null);
            JSFUtils.agregarMsgError(FICHA_MSG_ID, Labels.getValue("error_aprobacion_fase"), null);
        }
    }

    private void removerDocumentosDummy(FichaTO fichaTO) {
        if (fichaTO != null && fichaTO.getDocumentos() != null && !fichaTO.getDocumentos().isEmpty()) {
            List<Documentos> listDocs = new ArrayList<Documentos>();
            for (Documentos doc : fichaTO.getDocumentos()) {
                if (!doc.getDocsPk().equals(-1)
                        || !doc.getDocsNombre().trim().equals(Labels.getValue("ficha_doc_pendiente").trim())) {
                    listDocs.add(doc);
                }
            }
            fichaTO.setDocumentos(listDocs);
        }
    }

    /**
     * Carga en el Transfer Object los objetos seleccionados en los combos.
     */
    private void setCombosToFichaTO() {
        fichaTO.setAreaFk((Areas) listaAreasOrganismoCombo.getSelectedObject());
        fichaTO.setUsrGerenteFk((SsUsuario) listaGerenteCombo.getSelectedObject());
        fichaTO.setUsrAdjuntoFk((SsUsuario) listaAdjuntoCombo.getSelectedObject());
        fichaTO.setUsrSponsorFk((SsUsuario) listaSponsorCombo.getSelectedObject());
        fichaTO.setUsrPmofedFk((SsUsuario) listaPmoFederadaCombo.getSelectedObject());
        fichaTO.setProgFk((Programas) listaProgramasCombo.getSelectedObject());

        Moneda moneda = (Moneda) listaMonedaPreCombo.getSelectedObject();
        FuenteFinanciamiento fuente = (FuenteFinanciamiento) listaFuentesPreCombo.getSelectedObject();
        preFicha.setPreBase(preFicha.getPreBase() != null && preFicha.getPreBase() <= 0 ? null : preFicha.getPreBase());
        preFicha.setPreMoneda(moneda);
        preFicha.setFuenteFinanciamiento(fuente);
        fichaTO.setPreFk(preFicha);

        if (fichaTO.getOtrosDatos() == null) {
            fichaTO.setOtrosDatos(new ProyOtrosDatos());
        }
        fichaTO.getOtrosDatos().setProyOtrEtaFk((Etapa) listaEtapaPubCombo.getSelectedObject());
        fichaTO.setObjetivoEstrategico(listaObjetivosEstrategicosCombo.getSelectedT());
    }

    public String cancelar() {
        return ConstantesNavegacion.IR_A_INICIO;
    }

    /**
     * Carga en fichaTO los datos a modificar del Programas o Proyectos.
     */
    public void editarFichaAction(String programaProyectoId) {
        logger.finest("-- FichaMB - EditarFicha --");
        TipoFichaEnum tipoFichaEditar = FichaUtils.obtenerTipoFicha(programaProyectoId);
        Integer idEditar = FichaUtils.obtenerProyProgId(programaProyectoId);
        if (tipoFichaEditar.isPrograma()) {
            Programas prog = programaDelegate.obtenerProgPorId(idEditar);
            logger.log(Level.FINEST, "-- PROGRAMA ({0} - {1}) --", new Object[]{idEditar, prog});
            if (prog == null) {
                throw new BusinessException("No se a encontrado el programa " + programaProyectoId);
            }
            programaToFichaTO(prog);

        } else if (tipoFichaEditar.isProyecto()) {
            Proyectos proy = proyectoDelegate.obtenerProyPorId(idEditar);
            logger.log(Level.FINEST, "-- PROYECTO ({0} - {1}) --", new Object[]{idEditar, proy});
            if (proy == null) {
                throw new BusinessException("No se a encontrado el proyecto " + programaProyectoId);
            }
            proyectoToFichaTO(proy);
        }

        //gerente
        if (fichaTO.getUsrGerenteFk() != null) {
            addItemSofisCombo(listaGerenteCombo, fichaTO.getUsrGerenteFk(), fichaTO.getUsrGerenteFk().getUsuId(), true);
        }
        //adjunto

        if (fichaTO.getUsrAdjuntoFk() != null) {
            addItemSofisCombo(listaAdjuntoCombo, fichaTO.getUsrAdjuntoFk(), fichaTO.getUsrAdjuntoFk().getUsuId(), true);
        }
        //sponsor

        if (fichaTO.getUsrSponsorFk() != null) {
            addItemSofisCombo(listaSponsorCombo, fichaTO.getUsrSponsorFk(), fichaTO.getUsrSponsorFk().getUsuId(), true);
        }
        //pmof

        if (fichaTO.getUsrPmofedFk() != null) {
            addItemSofisCombo(listaPmoFederadaCombo, fichaTO.getUsrPmofedFk(), fichaTO.getUsrPmofedFk().getUsuId(), true);
        }
        //objetivoEstrategico

        if (fichaTO.getObjetivoEstrategico() != null) {
            addItemSofisCombo(listaObjetivosEstrategicosCombo, fichaTO.getObjetivoEstrategico(), fichaTO.getObjetivoEstrategico().getObjEstPk(), true);
        }

        cargarResumenes();
    }

    public void onload() {
        //si viene desde mis tareas, se despliega el gannt ampliado
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String misTareasNav = (String) request.getSession().getAttribute(ConstantesPresentacion.MIS_TAREAS);
        if (misTareasNav != null && misTareasNav.equalsIgnoreCase("true")) {
            request.getSession().removeAttribute(ConstantesPresentacion.MIS_TAREAS);
            misTareas = true;
            this.maximizarFrame(3l, null);
        }
    }

    /**
     * Dado el SofisCombo controla que el valor exista, si no agrega el objeto
     * en el combo. Se usa cuando una entidad está dada de baja pero debe de
     * seguir apareciendo en el combo si es la que está seleccionada.
     *
     * @param listaCombo
     * @param objectToAdd
     * @param value
     */
    private void addItemSofisCombo(SofisCombo listaCombo, Object objectToAdd, Integer value, boolean asc) {
        synchronized (this.aplicacionMB.usuariosMUTEX) {

            List<SelectItem> items = listaCombo.getItems();
            boolean existe = false;
            for (SelectItem item : items) {
                if (((Integer) item.getValue()).equals(value)) {
                    existe = true;
                }
            }
            if (!existe) {

                //List<SsUsuario> listaGerente = aplicacionMB.obtenerTodosPorOrganismoActivos(inicioMB.getOrganismoSeleccionado());//ssUsuarioDelegate.obtenerTodosPorOrganismo(orgPk);

                listaCombo.add(objectToAdd);

                //listaGerente = aplicacionMB.obtenerTodosPorOrganismoActivos(inicioMB.getOrganismoSeleccionado());//ssUsuarioDelegate.obtenerTodosPorOrganismo(orgPk);

                List<SelectItem> list = listaCombo.getItems();

                if (CollectionsUtils.isNotEmpty(list)) {
                    final boolean ascOrder = asc;
                    Collections.sort(list, new Comparator<SelectItem>() {
                        @Override
                        public int compare(SelectItem item1, SelectItem item2) {
                            if (item1 != null && item1.getLabel() != null && item2 != null && item2.getLabel() != null) {
                                if (ascOrder) {
                                    return item1.getLabel().toUpperCase().compareTo(item2.getLabel().toUpperCase());
                                } else {
                                    return item2.getLabel().toUpperCase().compareTo(item1.getLabel().toUpperCase());
                                }
                            }
                            return 0;
                        }
                    });
                }
            }
        }
    }

    private <T> void addItemSofisCombo(SofisComboG<T> listaCombo, T objectToAdd, Integer value, boolean asc) {

        List<SelectItem> items = listaCombo.getItems();
        boolean existe = false;
        for (SelectItem item : items) {
            if (((Integer) item.getValue()).equals(value)) {
                existe = true;
            }
        }
        if (!existe) {
            listaCombo.add(objectToAdd);

            List<SelectItem> list = listaCombo.getItems();

            if (CollectionsUtils.isNotEmpty(list)) {
                final boolean ascOrder = asc;
                Collections.sort(list, new Comparator<SelectItem>() {
                    @Override
                    public int compare(SelectItem item1, SelectItem item2) {
                        if (item1 != null && item1.getLabel() != null && item2 != null && item2.getLabel() != null) {
                            if (ascOrder) {
                                return item1.getLabel().toUpperCase().compareTo(item2.getLabel().toUpperCase());
                            } else {
                                return item2.getLabel().toUpperCase().compareTo(item1.getLabel().toUpperCase());
                            }
                        }
                        return 0;
                    }
                });
            }
        }
    }

    public String solCambioEstadoStr() {
        if (fichaTO.getEstadoPendiente() != null) {
            return String.format(Labels.getValue("ficha_msg_cambio_estado"), estadosDelegate.estadoStr(fichaTO.getEstadoPendiente().getEstPk(), inicioMB.getOrganismoSeleccionado()));
        }
        return "";
    }

    public String lecturaPopup() {
        try {
            renderPopupLectura = true;

            if (listaAreasTreeNode == null || listaAreasTreeNode.isEmpty()) {
                listaAreasTreeNode = new ArrayList<MutableTreeNode>();

                //listaAreas = areasDelegate.obtenerAreasPorOrganismo(inicioMB.getOrganismo().getOrgPk());
                listaAreas = aplicacionMB.obtenerAreasPorOrganismo(inicioMB.getOrganismo().getOrgPk());
                if (listaAreas != null && !listaAreas.isEmpty()) {
                    listaAreas.remove(fichaTO.getAreaFk());
                    setNodosForAreaRestringida();

                }
            }
        } catch (GeneralException ex) {
            Logger.getLogger(FichaMB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String areaTematicaPopup() {
        logger.fine("areaTematicaPopup.");
        try {
            renderPopupAreaTematica = true;
            cargarAreasTematicas();
        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void cargarAreasTematicas() {
        listaAreasTagsTreeNode = new ArrayList<MutableTreeNode>();

        listaAreasTags = areaTematicaDelegate.obtenerAreasTematicasPorOrganizacion(inicioMB.getOrganismo().getOrgPk());
        if (listaAreasTags != null && !listaAreasTags.isEmpty()) {
            Map<String, Object> mapAreasTag = WebUtils.setNodosForAreaTematica(listaAreasTags, listaAreasTagsTreeNode, fichaTO.getAreasTematicas(), areasTematicasStateMap);
            listaAreasTagsTreeNode = (List<MutableTreeNode>) mapAreasTag.get(WebUtils.LISTA_AREAS_TAG_TREE_NODE);
            areasTematicasStateMap = (NodeStateMap) mapAreasTag.get(WebUtils.AREAS_TEMATICAS_STATE_MAP);

        }
    }

    public String metodologiaPopup() {
        try {
            renderPopupMetodologia = true;
            fichaTO.setTipoDocumentoInstancias(TipoDocumentoInstanciaUtils.sortByDocumentosNombre(fichaTO.getTipoDocumentoInstancias(), false));

        } catch (GeneralException ex) {
            Logger.getLogger(FichaMB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Modifica varialbes que indican en la pÃƒÂ¡gina si se debe maximizar o
     * minimizar algun frame de la ficha.
     *
     * @param frame Indica el frame que se modifica.
     * @param maximizar
     * @return String
     */
    public String maximizarFrame(Long frame, Boolean maximizar) {
        if (maximizar != null) {
            frameMax = maximizar;
        } else {
            frameMax = !frameMax;
        }
        inicioMB.setContainerMax(frameMax);

        if (frame != null) {
            if (frameMax && !mostrar[frame.intValue()]) {
                this.alAbrirEnTodaLaPantalla = true;
                miMostrar(frame);
            } else if (frame.equals(3L)) {
                cargarFrameCronograma(true);
            }
        }

        return null;
    }

    public String miMostrar(Long number) {
        logger.log(Level.FINEST, "Mostrar:{0}", number);
        boolean valorAnt = mostrar[number.intValue()];

        if (valorAnt) {
            /*si se tenia un panel seleccionado, entonces se esta cerrando el mismo*/
            selectedMostrar = null;
        } else {
            selectedMostrar = number;
        }
        for (int i = 0; i < mostrar.length; i++) {
            mostrar[i] = false;
        }
        mostrar[number.intValue()] = !valorAnt;

        /**
         * 0=Documentos, 1=Interesados, 2=Riesgos, 3=Cronograma, 4=Presupuesto,
         * 5=Productos, 6=Participantes, 7=Calidad, 8=Multimedia,
         * 9=Localizaciones
         */
        if (selectedMostrar == null) {
            actualizarFichaTO(null);
            cargarResumenes();
            cerrarFormCollapsable();
            cerrarFormPreCollapsable();
            maximizarFrame(null, false);
        } else if (number == 0) {
            cargarFrameDocumentos();
        } else if (number == 1) {
            cargarFrameInteresados();
        } else if (number == 2) {
            generarMatrizRiesgos();
            cargarFrameRiesgos();
        } else if (number == 3) {
            cargarFrameCronograma(true);
        } else if (number == 4) {
            cargarFramePresupuestos(true);
        } else if (number == 5) {
            maximizarFrame(number, false);
            cargarFrameProductos();
        } else if (number == 6) {
        } else if (number == 7) {
        } else if (number == 8) {
            cargarFrameMultimedia(true);
        } else if (number == 9) {
            cargarFrameLocalizaciones();
        }

        return null;
    }

    public void cerrarFormCollapsable() {
        docsFormDataExpanded = false;
        intFormDataExpanded = false;
        riskFormDataExpanded = false;
        preFormDataExpanded = false;
        prodFormDataExpanded = false;
        partFormDataExpanded = false;
        multiFormDataExpanded = false;
        tipoMediaSelected = null;
        localizacionFormDataExpanded = false;
        limpiarPresupuesto();
        limpiarAdquisicion();
        limpiarRiesgo();
        limpiarInteresado();
        limpiarMedia();
        limpiarProducto();
        limpiarDocumento();
        limpiarPago();
        limpiarLocalizacion();

    }

    public String cerrarFormIntCollapsable() {
        intFormDataExpanded = false;
        limpiarInteresado();
        JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), "limpiarCorreoAutocompletar();");
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="localizacion">}
    public void limpiarLocalizacion() {
        latlngProyectosAux = null;
        JSFUtils.removerMensages();
    }

    public void cargarFrameLocalizaciones() {

    }

    public void abrirLocalizacionAction() {
        Proyectos proy = new Proyectos(fichaTO.getFichaFk());
        latlngProyectosAux = new LatlngProyectos();
        latlngProyectosAux.setProyecto(proy);
        latlngProyectosAux.setLatlangDepFk(new Departamentos());
        latlngProyectosAux.setLatlngLat(BigDecimal.ZERO);
        latlngProyectosAux.setLatlngLng(BigDecimal.ZERO);
        String latitudElem = "ficha:locLatAux_input";
        String longitudElem = "ficha:locLngAux_input";
        String deptoElem = "ficha:deptoAux_input";
        String mapElem = "localizaciones-map-canvas";
        String llamada = "initialize2('";
        llamada += mapElem;
        llamada += "', '";
        llamada += latitudElem;
        llamada += "', '";
        llamada += longitudElem;
        llamada += "', '";
        llamada += deptoElem;
        llamada += "', ";
        llamada += latlngProyectosAux.getLatlngLat();
        llamada += ", ";
        llamada += latlngProyectosAux.getLatlngLng();
        llamada += ", ";
        llamada += "";
        llamada += "' , '";
        llamada += " ) ;";
        JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), llamada);
        renderPopupLocalizacion = true;
    }

    public void editarLocalizacionAction(LatlngProyectos latlngProyectos) {
        latlngProyectosAux = latlngProyectos;
        String latitudElem = "ficha:locLatAux_input";
        String longitudElem = "ficha:locLngAux_input";
        String deptoElem = "ficha:deptoAux_input";
        String mapElem = "localizaciones-map-canvas";
        String llamada = "initialize2('";
        llamada += mapElem;
        llamada += "', '";
        llamada += latitudElem;
        llamada += "', '";
        llamada += longitudElem;
        llamada += "', '";
        llamada += deptoElem;
        llamada += "', ";
        llamada += latlngProyectosAux.getLatlngLat();
        llamada += ", ";
        llamada += latlngProyectosAux.getLatlngLng();
        llamada += ", ";
        llamada += "";
        llamada += "' , '";
        llamada += " ) ;";
        JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), llamada);
        renderPopupLocalizacion = true;
    }

    public void guardarLocalizacionAction() {

        try {
            latlngProyectosAux.setLatlangDepFk(
                    new Departamentos(
                            deptoToCodificacionINE(latlngProyectosAux.getLatlangDepFk().getDepNombre())
                    )
            );
            latlngDelegate.guardar(latlngProyectosAux);
            actualizarFichaTO(null);
//			JSFUtils.agregarMsg("info__actualizado");
            cargarFrameLocalizaciones();
            renderPopupLocalizacion = false;
        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            JSFUtils.agregarMsg("mapaLocalizacionMsg", "error_agregar_localizacion", null);
            JSFUtils.agregarMsgs("mapaLocalizacionMsg", ex.getErrores());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            JSFUtils.agregarMsg("mapaLocalizacionMsg", "error_agregar_localizacion", null);
        }
    }

    public void eliminarLocalizacionAction(LatlngProyectos latlngProyectos) {
        try {
            latlngDelegate.eliminar(latlngProyectos);
            actualizarFichaTO(null);
            cargarFrameLocalizaciones();
            renderPopupLocalizacion = false;
        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            JSFUtils.agregarMsgs(DOC_MSG_ID, ex.getErrores());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarPopupLocalizacion() {
        renderPopupLocalizacion = false;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="riesgos">
    public String abrirRiesgoAction() {
        limpiarRiesgo();
        riskFormDataExpanded = true;
        return null;
    }

    public String editarRiesgoAction(Integer riskPk) {
        riesgo = riesgosDelegate.obtenerRiesgoPorId(riskPk);

        listaRiskImpactoCombo.setSelected(riesgo.getRiskImpacto());
        listaRiskProbabilidadCombo.setSelected(riesgo.getRiskProbabilidad());
        listaRiskEntregablesCombo.setSelectedT(riesgo.getRiskEntregable());

        riskFormDataExpanded = true;

        return null;
    }

    public String eliminarRiesgoAction(Integer riskPk) {
        try {
            riesgosDelegate.eliminarRiesgo(riskPk, fichaTO.getFichaFk(), fichaTO.getTipoFicha(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());

        } catch (GeneralException ge) {
            logger.log(Level.SEVERE, null, ge);
            JSFUtils.agregarMsg(
                    "error_eliminar_riesgo");
        }

        actualizarFichaTO(null);
        cargarFrameRiesgos();
        cargarResumenRiesgos();
        limpiarRiesgo();
        cerrarFormCollapsable();

        return null;
    }

    public String superarRiesgoAction(Integer riskPk) {
        if (riskPk != null) {
            Riesgos riesgo = riesgosDelegate.superarRiesgo(riskPk, inicioMB.getUsuario().getUsuId(), inicioMB.getOrganismo().getOrgPk());

            actualizarFichaTO(null);
            cargarFrameRiesgos();
            cargarResumenRiesgos();
        }

        return null;
    }

    public String riesgoPopup(Integer riskPk) {
        try {
            renderPopupRiesgo = true;
            riskPopup = riesgosDelegate.obtenerRiesgoPorId(riskPk);
            riskPopup.setExposicionColor(riesgosDelegate.obtenerExposicionRiesgoColor(riskPopup.getExposicion(), inicioMB.getOrganismo().getOrgPk()));
            riskPopup.setFechaLimiteColor(riesgosDelegate.obtenerFechaLimiteRiesgoColor(riskPopup.getRiskFechaLimite(), inicioMB.getOrganismo().getOrgPk()));

        } catch (GeneralException ex) {
            Logger.getLogger(FichaMB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void cerrarPopupRiesgo() {
        renderPopupRiesgo = false;
    }

    public void cerrarPopupSitActHistorico() {
        renderPopupHistoricoSitAct = false;
    }

    public String documentosPopup(Integer docPk) {
        try {
            renderPopupDocumentos = true;
            documentoPopup = documentosDelegate.obtenerDocumentosPorId(docPk);
            documentoPopup.setDocFile(documentosDelegate.obtenerDocFilePorDocId(docPk));

        } catch (GeneralException ex) {
            Logger.getLogger(FichaMB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void cerrarPopupDocumentos() {
        renderPopupDocumentos = false;
        documentoPopup = new Documentos();
    }

    public String verCopiaProyAction() {
        renderPopupCopiaProy = true;
        return null;
    }

    public String notificacionPopupAction() {
        renderPopupNotificacion = true;
        return null;
    }

    public void cerrarRenderPopupCopiaProy() {
        renderPopupCopiaProy = false;
        nombreProyCopia = null;
        fechaComienzoProyCopia = null;
    }

    public String agregarMediaAction() {
        logger.fine("Agregar Multimedia a la Ficha.");
        try {
            TiposMedia tm = listaTipoMediaCombo.getSelectedT();
            mediaProy.setMediaTipoFk(tm);
            mediaProy.setMediaProyFk(new Proyectos(fichaTO.getFichaFk()));

            try {
                mediaProy = mediaProyectosDelegate.guardar(mediaProy, inicioMB.getUsuario().getUsuId(), inicioMB.getOrganismo().getOrgPk());
                JSFUtils.agregarMsg("", "info_media_guardado", null);

                actualizarFichaTO(null);
                limpiarMedia();
                cerrarFormCollapsable();

                cargarFrameMultimedia(true);

            } catch (BusinessException | TechnicalException w) {

                /*
                                *  18-06-2018 Inspección de código.
                 */
                //List<String> errores = w.getErrores();
                //JSFUtils.agregarMsgs("multiForm", errores);
                for (String iterStr : w.getErrores()) {
                    JSFUtils.agregarMsgError("multiForm", Labels.getValue(iterStr), null);
                }

            }
        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            /*
                        *  18-06-2018 Inspección de código.
             */

            //JSFUtils.agregarMsg("multiForm", "error_guardar_riesgo", null);
            for (String iterStr : ex.getErrores()) {
                JSFUtils.agregarMsgError("multiForm", Labels.getValue(iterStr), null);
            }

        }
        return null;
    }

    public void abrirMediaAction() {
        limpiarMedia();
        multiFormDataExpanded = true;
    }

    public String editarMediaAction(Integer mediaPk) {
        if (mediaPk != null) {
            multiFormDataExpanded = true;
            mediaProy = mediaProyectosDelegate.obtenerPorId(mediaPk);
            if (mediaProy != null) {
                if (mediaProy.getMediaTipoFk() != null) {
                    listaTipoMediaCombo.setSelectedT(mediaProy.getMediaTipoFk());
                    tipoMediaSelected = listaTipoMediaCombo.getSelectedT();
                }
                if (mediaProy.getMediaTipoFk().isTipoMediaCod(TiposMediaCodigos.IMG)) {
                    mediaProy = mediaProyectosDelegate.cargarArchivo(mediaProy, inicioMB.getOrganismo().getOrgPk());
                }
            }

        }
        return null;
    }

    public String eliminarMultimediaAction(Integer mediaPk) {
        if (mediaPk != null) {
            String link = null;
            MediaProyectos mp = mediaProyectosDelegate.obtenerPorId(mediaPk);
            if (mp.getMediaTipoFk().isTipoMediaCod(TiposMediaCodigos.IMG)) {
                link = mp.getMediaLink();
            }

            try {
                mediaProyectosDelegate.eliminar(mediaPk);
                if (link != null) {
                    File f = new File(link);
                    if (f.exists()) {
                        f.delete();
                    }
                }
            } catch (BusinessException be) {

            } catch (Exception e) {

            }
            //Todo perfomance no cargar de nuevo todos los bytes
            cargarFrameMultimedia(true);

        }
        return null;
    }

    public String agregarRiesgoAction() {
        logger.fine("Agregar Riesgo a la Ficha.");
        try {
            ComboItemTO comboItemTO = (ComboItemTO) listaRiskProbabilidadCombo.getSelectedObject();
            riesgo.setRiskProbabilidad(comboItemTO != null ? (Integer) comboItemTO.getItemObject() : null);
            riesgo.setRiskImpacto((Integer) listaRiskImpactoCombo.getSelectedObject());
            riesgo.setRiskEntregable(listaRiskEntregablesCombo.getSelectedT());
            riesgo.setRiskProyFk(new Proyectos(fichaTO.getFichaFk()));

            try {
                FichaRiesgosValidacion.validar(riesgo);
                if (fichaTO.getRiesgos() == null) {
                    fichaTO.setRiesgos(new ArrayList<Riesgos>());
                }
                riesgo = riesgosDelegate.guardar(riesgo, inicioMB.getOrganismo().getOrgPk());
                JSFUtils.agregarMsg("", "info_riesgo_guardado", null);

                riesgo.setExposicionColor(riesgosDelegate.obtenerExposicionRiesgoColor(riesgo.getExposicion(), inicioMB.getOrganismo().getOrgPk()));

                actualizarFichaTO(null);
                limpiarRiesgo();
                cargarFrameRiesgos();
                cerrarFormCollapsable();

            } catch (BusinessException | TechnicalException w) {
                /*
                                *  18-06-2018 Inspección de código.
                 */

                //List<String> errores = w.getErrores();
                //JSFUtils.agregarMsgs("riesgosMsg", errores);                                
                for (String iterStr : w.getErrores()) {
                    JSFUtils.agregarMsgError("riesgosMsg", Labels.getValue(iterStr), null);
                }
            }
        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            /*
                         *  18-06-2018 Inspección de código.
             */

            //JSFUtils.agregarMsg("riesgosMsg", "error_guardar_riesgo", null);
            for (String iterStr : ex.getErrores()) {
                JSFUtils.agregarMsgError("riesgosMsg", Labels.getValue(iterStr), null);
            }

        }
        return null;
    }

    public String limpiarMedia() {
        try {
            mediaProy = new MediaProyectos();
            listaTipoMediaCombo.setSelected(-1);
            tipoMediaSelected = null;

        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String limpiarRiesgo() {
        try {
            riesgo = new Riesgos();
            listaRiskImpactoCombo.setSelected(-1);
            listaRiskProbabilidadCombo.setSelected(-1);
            listaRiskEntregablesCombo.setSelected(-1);

        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void generarMatrizRiesgos() {
        logger.fine("Generar Matriz de Riesgos.");
        if (fichaTO == null || fichaTO.getFichaFk() == null) {
            return;
        }
        try {

            List<Riesgos> listaRiesgos = fichaTO.getRiesgos();
            HashMap<String, Integer> riesgosCantidad = new HashMap<>();
            riesgosCantidad.put("2-20", 0);
            riesgosCantidad.put("2-40", 0);
            riesgosCantidad.put("2-60", 0);
            riesgosCantidad.put("2-80", 0);
            riesgosCantidad.put("2-100", 0);
            riesgosCantidad.put("4-20", 0);
            riesgosCantidad.put("4-40", 0);
            riesgosCantidad.put("4-60", 0);
            riesgosCantidad.put("4-80", 0);
            riesgosCantidad.put("4-100", 0);
            riesgosCantidad.put("6-20", 0);
            riesgosCantidad.put("6-40", 0);
            riesgosCantidad.put("6-60", 0);
            riesgosCantidad.put("6-80", 0);
            riesgosCantidad.put("6-100", 0);
            riesgosCantidad.put("8-20", 0);
            riesgosCantidad.put("8-40", 0);
            riesgosCantidad.put("8-60", 0);
            riesgosCantidad.put("8-80", 0);
            riesgosCantidad.put("8-100", 0);
            riesgosCantidad.put("10-20", 0);
            riesgosCantidad.put("10-40", 0);
            riesgosCantidad.put("10-60", 0);
            riesgosCantidad.put("10-80", 0);
            riesgosCantidad.put("10-100", 0);

            for (Riesgos r : listaRiesgos) {
                String key = r.getRiskImpacto() + "-" + r.getRiskProbabilidad();
                if (riesgosCantidad.containsKey(key) && !r.isSuperado()) {
                    riesgosCantidad.put(key, riesgosCantidad.get(key) + 1);
                }
            }

            limiteGestionRiesgos = new ArrayList<>();
            FilaRiesgosLimite fila1 = new FilaRiesgosLimite(10, riesgosCantidad.get("10-20"), riesgosCantidad.get("10-40"), riesgosCantidad.get("10-60"), riesgosCantidad.get("10-80"), riesgosCantidad.get("10-100"), 2d, 4d, 6d, 8d, 10d);
            FilaRiesgosLimite fila2 = new FilaRiesgosLimite(8, riesgosCantidad.get("8-20"), riesgosCantidad.get("8-40"), riesgosCantidad.get("8-60"), riesgosCantidad.get("8-80"), riesgosCantidad.get("8-100"), 1.6d, 3.2d, 4.8d, 6.4d, 8d);
            FilaRiesgosLimite fila3 = new FilaRiesgosLimite(6, riesgosCantidad.get("6-20"), riesgosCantidad.get("6-40"), riesgosCantidad.get("6-60"), riesgosCantidad.get("6-80"), riesgosCantidad.get("6-100"), 1.2d, 2.4d, 3.6d, 4.8d, 6d);
            FilaRiesgosLimite fila4 = new FilaRiesgosLimite(4, riesgosCantidad.get("4-20"), riesgosCantidad.get("4-40"), riesgosCantidad.get("4-60"), riesgosCantidad.get("4-80"), riesgosCantidad.get("4-100"), 0.8d, 1.6d, 2.4d, 3.2d, 4d);
            FilaRiesgosLimite fila5 = new FilaRiesgosLimite(2, riesgosCantidad.get("2-20"), riesgosCantidad.get("2-40"), riesgosCantidad.get("2-60"), riesgosCantidad.get("2-80"), riesgosCantidad.get("2-100"), 0.4d, 0.8d, 1.2d, 1.6d, 2d);
            limiteGestionRiesgos.add(fila1);
            limiteGestionRiesgos.add(fila2);
            limiteGestionRiesgos.add(fila3);
            limiteGestionRiesgos.add(fila4);
            limiteGestionRiesgos.add(fila5);

        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="interesados">
    /**
     * Agrega el interesado y lo une a la ficha
     *
     * @return
     */
    public String agregarInteresadoAction() {
        logger.fine("Agregar Interesados a la Ficha.");

        try {

            interesado.setIntOrgaFk((OrganiIntProve) listaOrganizacionCombo.getSelectedObject());
            interesado.setIntRolintFk((RolesInteresados) listaRolesInteresadosCombo.getSelectedObject());
            interesado.setIntEntregable(listaIntEntregablesCombo.getSelectedT());
            interesado.getIntPersonaFk().setPersOrgaFk(interesado.getIntOrgaFk());
            interesado.getIntPersonaFk().setPersRolFk(interesado.getIntRolintFk() != null ? interesado.getIntRolintFk().getRolintPk() : null);
            interesado.getIntPersonaFk().setPersRol(interesado.getIntRolintFk());
            Personas perInt = personasDelegate.guardar(interesado.getIntPersonaFk());
            interesado.setIntPersonaFk(perInt);

            FichaInteresadosValidacion.validar(interesado);

            if (fichaTO.getInteresados() == null) {
                fichaTO.setInteresados(new ArrayList<Interesados>());
            }

            Object progProy = interesadosDelegate.guardarInteresados(interesado, fichaTO.getFichaFk(), fichaTO.getTipoFicha(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
            actualizarFichaTO(progProy);

            if (interesado.getIntOrgaFk() != null) {
                autoCompletePersonasList = personasDelegate.obtenerPersonas(interesado.getIntOrgaFk().getOrgaPk());
            }

//			cargarFrameInteresados();
            cerrarFormIntCollapsable();
            limpiarInteresado();
            JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), "limpiarCorreoAutocompletar();");
            //Al cerrar el colapsable no deja borrar al campo mail.
            //cerrarFormCollapsable();

        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, null, ex);
            List<String> errores = ex.getErrores();

            JSFUtils.agregarMsgs(INTERESADOS_MSG_ID, errores);
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, null, ex);
            JSFUtils.agregarMsg(INTERESADOS_MSG_ID,
                    "error_agregar_interesado", null);
            JSFUtils.agregarMsgs(INTERESADOS_MSG_ID, ex.getErrores());
        }
        return null;
    }

    public void abrirInteresadosAction() {
        limpiarInteresado();
        intFormDataExpanded = true;
    
        List<OrganiIntProve> listaOrganizacion = aplicacionMB.obtenerOrganiIntOrganizaciones(inicioMB.getOrganismo().getOrgPk());
        listaOrganizacion = OrganiIntProveUtils.filtrarHabilitadosYOrdenarPorNombre(listaOrganizacion, null);

        listaOrganizacionCombo = new SofisCombo((List) listaOrganizacion, "orgaNombre");
        listaOrganizacionCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));    
    }

    public String editarInteresadoAction(Integer intPk) {

        interesado = interesadosDelegate.obtenerInteresadosPorId(intPk);
        JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), "document.getElementById('ficha:autoCompleteInteresadoCorreo_input').value = '" + interesado.getIntPersonaFk().getPersMail() + "'");
        
        List<OrganiIntProve> listaOrganizacion = aplicacionMB.obtenerOrganiIntOrganizaciones(inicioMB.getOrganismo().getOrgPk());
        listaOrganizacion = OrganiIntProveUtils.filtrarHabilitadosYOrdenarPorNombre(listaOrganizacion, interesado.getIntOrgaFk());

        listaOrganizacionCombo = new SofisCombo((List) listaOrganizacion, "orgaNombre");
        listaOrganizacionCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
    
        listaOrganizacionCombo.setSelected(interesado.getIntOrgaFk().getOrgaPk());

        listaRolesInteresados = rolesInteresadosDelegate.obtenerRolPorOrganizacionId(interesado.getIntOrgaFk().getOrgaPk());
        if (listaRolesInteresados != null && !listaRolesInteresados.isEmpty()) {
            listaRolesInteresadosCombo = new SofisCombo((List) listaRolesInteresados, "rolintNombre");
            listaRolesInteresadosCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }
        listaRolesInteresadosCombo.setSelected(interesado.getIntRolintFk().getRolintPk());
        listaIntEntregablesCombo.setSelectedT(interesado.getIntEntregable());

        intFormDataExpanded = true;

        return null;
    }

    public String eliminarInteresadoAction(Integer intPk) {
        try {
            interesadosDelegate.delete(intPk, fichaTO.getFichaFk(), fichaTO.getTipoFicha(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());

            List<Interesados> listInt = interesadosDelegate.obtenerIntersadosPorFichaPk(fichaTO.getFichaFk(), fichaTO.getTipoFicha());
            fichaTO.setInteresados(listInt);
        } catch (GeneralException ge) {
            logger.log(Level.SEVERE, ge.getMessage(), ge);
            JSFUtils.agregarMsg("error_eliminar_interesado");
            inicioMB.abrirPopupMensajes();
        }
        cargarFrameInteresados();
        limpiarInteresado();
        cerrarFormCollapsable();

        return null;
    }

    public void cargarInteresadosRolSelectAction(ValueChangeEvent event) {
        PhaseId phaseId = event.getPhaseId();
        if (phaseId.equals(PhaseId.ANY_PHASE)) {
            event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
            event.queue();
        } else if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES)) {
            if (listaOrganizacionCombo.getSelectedObject() != null) {
                Integer orgaId = ((OrganiIntProve) listaOrganizacionCombo.getSelectedObject()).getOrgaPk();
                autoCompletePersonasList = personasDelegate.obtenerPersonas(orgaId);
            }
        }
    }

    public String limpiarInteresado() {
        try {
            String confValue = configuracionDelegate.obtenerCnfValorPorCodigo(ConfiguracionCodigos.SHOW_MODULO_INTERESADOS, inicioMB.getOrganismo().getOrgPk());
            if (confValue.equals("true")) {
                interesado = new Interesados();
                interesado.setIntPersonaFk(new Personas());
                interesado.setIntRolintFk(new RolesInteresados());
                listaOrganizacionCombo.setSelected(-1);
                listaRolesInteresadosCombo.setSelected(-1);
                listaIntEntregablesCombo.setSelected(-1);
//                autoCompletePersonasComponent = null;
                autoCompletePersonasList = new ArrayList<>();
//                JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), "document.getElementById('ficha:autoCompleteInteresadoCorreo_input').value = ''");
                JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), "limpiarCorreoAutocompletar();");
            }

        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String interesadosPopup() {
        try {
            limpiarInteresado();
            return miMostrar(new Long(1));

        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * cuando el usuario selecciona alguna opcion de la lista
     *
     * @param event
     */
    public void autoCompleteInteresadoCorreoValueChangeListener(ValueChangeEvent event) {
        PhaseId phaseId = event.getPhaseId();

        if (phaseId.equals(PhaseId.ANY_PHASE)) {
            event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
            event.queue();
        } else if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES)) {
            procesarEmailInteresado((String) event.getNewValue(), (String) event.getOldValue());
        }
    }

    private void procesarEmailInteresado(String correoSeleccionado, String correoSeleccionadoOld) {

        boolean encontre = false;
        if (correoSeleccionadoOld != null && correoSeleccionado != null && correoSeleccionado.equalsIgnoreCase(correoSeleccionadoOld)) {
            return;
        }
        if (correoSeleccionado != null && !correoSeleccionado.equalsIgnoreCase("")) {
            //se verifica si existe una persona con este correo, si existe entonces se asocia el interesado a esta persona
            //la persona existe
            for (Personas p : autoCompletePersonasList) {
                if (p.getPersMail().equalsIgnoreCase(correoSeleccionado)) {
                    Personas pSelected = (Personas) p.clone();
                    logger.log(Level.FINEST, "Se asocia a la persona {0}", pSelected.getPersNombre());
                    interesado.setIntPersonaFk(pSelected);
                    encontre = true;
                    break;
                } else {
                    logger.finest("EMAIL NO  ENCONTRADO");
                }
            }
        }
        if (!encontre) {
            //logger.finest("NO Se asocia a la persona ");
            //interesado.setIntPersonaFk(new Personas());
        }
    }

    public String cerrarPopupInteresados() {
        try {
            renderPopupInteresados = false;

        } catch (GeneralException ex) {
            Logger.getLogger(FichaMB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="documentos">
    public void cargarFrameDocumentos() {
        if (fichaTO.hasPk()) {
            List<Documentos> listDocumentos = documentosDelegate.obtenerDocumentosOrderByFecha(fichaTO.getFichaFk(), fichaTO.getTipoFicha());
//                        if(listDocumentos != null){
            listDocumentos = documentosDelegate.cargarArchivosDocumentos(listDocumentos);
//                        }else{
//                            listDocumentos = new ArrayList<Documentos>();
//                        }
//                        
            fichaTO.setDocumentos(listDocumentos);

            SsUsuario usuario = inicioMB.getUsuario();

            if (fichaTO.getDocumentos() == null) {
                fichaTO.setDocumentos(new ArrayList<Documentos>());
            }

            boolean esParticipante = false;
            if (fichaTO.getParticipantes() != null) {
                for (Participantes p : fichaTO.getParticipantes()) {
                    if (p.getPartUsuarioFk().equals(inicioMB.getUsuario())) {
                        esParticipante = true;
                        break;
                    }
                }
            }

            if (SsUsuariosUtils.isUsuarioComun(fichaTO, inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk())) {
                List<Documentos> docNoPrivados = new ArrayList<>();
                for (Documentos d : fichaTO.getDocumentos()) {
                    if (!d.getDocsPrivado()) {
                        docNoPrivados.add(d);
                    }
                }
                setListaDocumentosFrame(docNoPrivados);
            } else {
                setListaDocumentosFrame(fichaTO.getDocumentos());
            }

            /*
                        *   19-03-08 Nico: En la siguiente iteración voy a recorrer los entregables asociados
                        *           al cronograma, y así poder conocer cuales son los documentos a los que 
                        *           el usuario loggeado puede editar y eliminar.
             */
            Cronogramas cro = fichaTO.getCroFk();

            if (cro != null) {
                if (cro.getEntregablesSet() != null) {
                    /*
                                    *   19-06-2018 Nico: Se agrega esta condición de "isEstadoFinalizado" para que cuando se recorra se fije que si esta finalizado el proyecto
                                    *               se ponga en false el valor. De esta manera no se pisa el valor obtenido en setRenderAttribute.
                     */
                    boolean isEstadoFinalizado = fichaTO.isEstado(Estados.ESTADOS.FINALIZADO.estado_id);

                    List<Entregables> entregables = new ArrayList<>(cro.getEntregablesSet());
                    for (Entregables iterEnt : entregables) {
                        if (!isEstadoFinalizado && ((iterEnt.getCoordinadorUsuFk() != null) && (iterEnt.getCoordinadorUsuFk().getUsuId().equals(usuario.getUsuId())))) {
                            auxAsocCoordEnt.put(iterEnt.getEntId(), true);
                        } else {
                            auxAsocCoordEnt.put(iterEnt.getEntId(), false);
                        }
                    }
                }
            }

            /**
             * Filtrar los documentos en los que es participante
             */
            if (esParticipante) {
//				System.out.println("#########################################");
//				System.out.println("SOY PARTICIPANTE");
//				System.out.println("#########################################");

            }

            //se obtienen los tipos de documentos que no tiene la ficha para
            //desplegarlos igualmente en el listado de documentos.
            List<TipoDocumentoInstancia> tiposNoContenidos = tipoDocumentoInstanciaDelegate.obtenerTipoDocsNoContenidosPorProyectoId(fichaTO.getFichaFk(), fichaTO.getOrgFk().getOrgPk());
            for (TipoDocumentoInstancia t : tiposNoContenidos) {
                if (t.getTipodocExigidoDesde() != null && t.getTipodocExigidoDesde() != 0) {
                    Documentos dumy = new Documentos();
                    dumy.setDocsPk(-1);
                    dumy.setDocsNombre(Labels.getValue("ficha_doc_pendiente"));
                    dumy.setDocsTipo(t);

                    if (t.getTipodocExigidoDesde() >= fichaTO.getEstado().getEstPk()) {
                        dumy.setDocsEstadoColor(ConstantesEstandares.COLOR_TRANSPARENT);
                    } else {
                        dumy.setDocsEstado(0d);
                        dumy.setDocsEstadoColor(ConstantesEstandares.SEMAFORO_ROJO);
                    }
                    getListaDocumentosFrame().add(dumy);
                }
            }

            listaTipoDocumento = tipoDocumentoInstanciaDelegate.obtenerTipoDocsInstanciaPorProgProyId(fichaTO.getFichaFk(), fichaTO.getTipoFicha(), fichaTO.getOrgFk().getOrgPk());
            if (listaTipoDocumento != null && !listaTipoDocumento.isEmpty()) {
                listaTipoDocCombo = new SofisCombo((List) listaTipoDocumento, "tipodocInstTipoDocFk.tipodocNombre");
                listaTipoDocCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
            }

            /*
                        *   19-03-18 Nico: Para saber si el usuario esta asociado a algún entregable, primero me fijo si es Gerente
                        *           o Adjunto, luego si no lo es, le paso el Mapa que tiene la información de a cual entregable 
                        *           esta asociado el usuario.
             */
            boolean isGerente = SsUsuariosUtils.isUsuarioGerenteFicha(fichaTO, usuario);
            boolean isAdjunto = SsUsuariosUtils.isUsuarioAdjuntoFicha(fichaTO, usuario);
            boolean isGerenteOAdjunto = isGerente || isAdjunto;

            if (fichaTO.getCroFk() != null && fichaTO.getCroFk().getEntregablesSet() != null) {
                listaEntregables = new ArrayList<>(fichaTO.getCroFk().getEntregablesSet());
                if (isGerenteOAdjunto) {
                    listaEntregables = EntregablesUtils.cargarCamposCombos(listaEntregables);
                    listaEntregables = EntregablesUtils.entregablesSinPadres(listaEntregables);
                } else {
                    listaEntregables = EntregablesUtils.cargarCamposCombos(listaEntregables, auxAsocCoordEnt);
                    listaEntregables = EntregablesUtils.entregablesSinPadres(listaEntregables, auxAsocCoordEnt);
                }
            }

            if (listaEntregables != null && !listaEntregables.isEmpty()) {
                listaEntregablesCombo = new SofisCombo((List) listaEntregables, "fechaNivelNombreCombo");
                listaEntregablesCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
            }

            List<Pagos> listaPagos = pagosDelegate.obtenerPagosPorFichaId(fichaTO.getFichaFk(), fichaTO.getTipoFicha());
            if (listaPagos != null && !listaPagos.isEmpty()) {
                listaDocPagoCombo = new SofisComboG(listaPagos, "textoCombo");
                listaDocPagoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
            }

            listaEstDoc = documentosDelegate.obtenerEstados();
            listaEstDocCombo = new ArrayList();
            SelectItem sItem;
            if (listaEstDoc != null && !listaEstDoc.isEmpty()) {
                for (Double d : listaEstDoc) {
                    sItem = new SelectItem(d, d.toString());
                    listaEstDocCombo.add(sItem);
                }
            }

            if (fichaTO.isPrograma()) {
                indiceEstado = NumbersUtils.redondearDecimales(documentosDelegate.calcularIndiceEstadoMetodologiaPrograma(fichaTO.getProyectosSet()));
            } else if (fichaTO.isProyecto()) {
                indiceEstado = NumbersUtils.redondearDecimales(documentosDelegate.calcularIndiceEstadoMetodologiaProyecto(fichaTO.getDocumentos(), fichaTO.getFichaFk(), inicioMB.getOrganismo().getOrgPk(), fichaTO.getEstado()));
            }
            if (indiceEstado != null && indiceEstado.isNaN()) {
                indiceEstado = null;
            }
            indiceMetodologiaColor = documentosDelegate.calcularIndiceEstadoMetodologiaColor(indiceEstado, inicioMB.getOrganismo().getOrgPk(), null);
        }
        agruparDocumentosVista();
    }

    /*
        *   19-03-18 Nico: Creo esta función, aparte de "fieldRendered" para que en "fieldRendered"
        *           vea si es Adjunto o Gerente, y acá se fije si es coordinador de ese entregable.
     */
    public boolean isCoordinadordeEntregable(Documentos paramDocumento) {
        if (paramDocumento != null) {
            Entregables docEnt = paramDocumento.getDocsEntregable();

            if (docEnt != null) {
                if (docEnt.getEntId() != null) {
                    return auxAsocCoordEnt.get(docEnt.getEntId());
                }
            }
        }
        return false;
    }

    public Long getDiaHoraServer() {
        Date d = new Date();
        return d.getTime();
    }

    public void setDiaHoraServer(Long l) {
    }

    public void cargarFrameInteresados() {

        listaRolesInteresados = rolesInteresadosDelegate.obtenerRolPorOrganizacionId(inicioMB.getOrganismoSeleccionado());
        if (listaRolesInteresados != null && !listaRolesInteresados.isEmpty()) {
            listaRolesInteresadosCombo = new SofisCombo((List) listaRolesInteresados, "rolintNombre");
            listaRolesInteresadosCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        List<Entregables> listaIntEntregable = null;
        if (fichaTO.getCroFk() != null && fichaTO.getCroFk().getEntregablesSet() != null) {
            listaIntEntregable = new ArrayList<>(fichaTO.getCroFk().getEntregablesSet());
            listaIntEntregable = EntregablesUtils.cargarCamposCombos(listaIntEntregable);
        }
        if (listaIntEntregable != null) {
            listaIntEntregablesCombo = new SofisComboG<>(listaIntEntregable, "fechaNivelNombreCombo");
            listaIntEntregablesCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        for (Interesados i : fichaTO.getInteresados()) {
            i.getIntPersonaFk().setPersRol(rolesInteresadosDelegate.obtenerRolesInteresadosPorId(i.getIntPersonaFk().getPersRolFk()));
        }

    }

    public void cargarResumenCronograma() {
        if (fichaTO.getCroFk() != null) {
            if (fichaTO.getProyIndices() != null && fichaTO.getProyIndices().getProyindAvanceFinAzul() != null) {
                indiceAvanceFinalizado = fichaTO.getProyIndices().getProyindAvanceFinal();
                indiceAvanceParcial = fichaTO.getProyIndices().getProyindAvanceParcial();
            } else {
                indiceAvanceFinalizado = cronogramaDelegate.calcularAvanceCronoFinalizado(fichaTO.getCroFk().getEntregablesSet());
                indiceAvanceParcial = cronogramaDelegate.calcularAvanceCronoParcial(fichaTO.getCroFk().getEntregablesSet());
                if (fichaTO.getProyIndices() == null) {
                    fichaTO.setProyIndices(new ProyIndices());
                }
                fichaTO.getProyIndices().setProyindAvanceFinal(indiceAvanceFinalizado);
                fichaTO.getProyIndices().setProyindAvanceParcial(indiceAvanceParcial);
            }
        }

        listaEntregablesResumen = cronogramaDelegate.obtenerResumenCronograma(fichaTO.getFichaFk(), 5);
    }

    /**
     * Cargar los datos necesarios para el Frame Cronograma.
     *
     * @param actualizar Si actualiza los datos de la ficha.
     */
    public void cargarFrameCronograma(boolean actualizar) {
        if (actualizar) {
            actualizarFichaTO(null);
        }

        /**
         * Para que abra siempre el módulo en el gantt
         */
        cargaDesdeArchivo = false;
        cargaPorFormulario = false;

        Cronogramas cro = fichaTO.getCroFk();
        if (cro == null) {
            cro = new Cronogramas();
            cro.setCroEntSeleccionado(0);
            cro.setCroPermisoEscritura(true);
            cro.setCroPermisoEscrituraPadre(true);
            cro.setEntregablesSet(new HashSet<Entregables>());
            cro = cronogramaDelegate.guardar(cro);
            fichaTO.setCroFk(cro);
        }

        indiceAvanceFinalizado = cronogramaDelegate.calcularAvanceCronoFinalizado(fichaTO.getCroFk().getEntregablesSet());
        indiceAvanceParcial = cronogramaDelegate.calcularAvanceCronoParcial(fichaTO.getCroFk().getEntregablesSet());

        StringBuffer dataCrono = new StringBuffer("");
        if (cro != null) {
            Integer orgPk = inicioMB.getOrganismo().getOrgPk();
            SsUsuario usuario = inicioMB.getUsuario();

            dataCrono = dataCrono.append("{\"tasks\":[");
            if (cro.getEntregablesSet() != null) {
                boolean primero = true;
                List<Entregables> entregables = new ArrayList<>(cro.getEntregablesSet());
                entregables = EntregablesUtils.cargarCamposCombos(entregables);

                for (Entregables ent : entregables) {
                    StringBuffer task = new StringBuffer("");

                    if (primero) {
                        primero = false;
                    } else {
                        task = task.append(",");
                    }

                    boolean tieneProductos = productosDelegate.tieneProdporEnt(ent.getEntPk());
                    boolean tieneDependencias = entregablesDelegate.tieneDependencias(ent.getEntPk());

                    task = task.append("{\"" + ConstantesPresentacion.TASK_PK + "\":")
                            .append(StringsUtils.toString(ent.getEntPk()))
                            .append(",\"" + ConstantesPresentacion.TASK_ID + "\":")
                            .append(StringsUtils.toString(ent.getEntId()))
                            .append(",\"" + ConstantesPresentacion.TASK_NAME + "\":\"")
                            .append(StringsUtils.toCleanString(ent.getEntNombre()))
                            .append("\",\"" + ConstantesPresentacion.TASK_CODE + "\":\"")
                            .append(StringsUtils.toString(ent.getEntCodigo()))
                            .append("\",\"" + ConstantesPresentacion.TASK_DESCRIPTION + "\":\"")
                            .append(StringsUtils.toCleanString(ent.getEntDescripcion()))
                            .append("\",\"" + ConstantesPresentacion.TASK_LEVEL + "\":")
                            .append(StringsUtils.toString(ent.getEntNivel()))
                            .append(",\"" + ConstantesPresentacion.TASK_STATUS + "\":\"")
                            .append(StringsUtils.toString(ent.getEntStatus()))
                            .append("\",\"" + ConstantesPresentacion.TASK_ESTADO + "\":")
                            .append(StringsUtils.toString(fichaTO.getEstado().getEstadoCronograma()))
                            .append(",\"" + ConstantesPresentacion.TASK_START + "\":")
                            .append(StringsUtils.toString(DatesUtils.corregirGMT(ent.getEntInicio())))
                            .append(",\"" + ConstantesPresentacion.TASK_DURATION + "\":")
                            .append(StringsUtils.toString(ent.getEntDuracion() != null ? ent.getEntDuracion() : DatesUtils.diasEntreFechas(ent.getEntInicioDate(), ent.getEntFinDate())))
                            .append(",\"" + ConstantesPresentacion.TASK_END + "\":")
                            .append(StringsUtils.toString(DatesUtils.corregirGMT(ent.getEntFin())))
                            .append(",\"" + ConstantesPresentacion.TASK_START_IS_MILESTONE + "\":")
                            .append(StringsUtils.toString(ent.getEntInicioEsHito()))
                            .append(",\"" + ConstantesPresentacion.TASK_END_IS_MILESTONE + "\":")
                            .append(StringsUtils.toString(ent.getEntFinEsHito()))
                            .append(",\"" + ConstantesPresentacion.TASK_COLLAPSED + "\":")
                            .append(StringsUtils.toString(ent.getEntCollapsed()))
                            .append(",\"" + ConstantesPresentacion.TASK_ASSIGS + "\":")
                            .append("[]")
                            .append(",\"" + ConstantesPresentacion.TASK_RELEVANTE + "\":")
                            .append(ent.getEntRelevante())
                            .append(",\"" + ConstantesPresentacion.TASK_CAN_DELETE + "\":")
                            .append(StringsUtils.toString(!tieneDependencias && !tieneProductos))
                            .append(",\"" + ConstantesPresentacion.TASK_COORDINADOR + "\":");
                    if (ent.getCoordinadorUsuFk() != null) {
                        task = task.append(StringsUtils.toString(ent.getCoordinadorUsuFk().getUsuId()));
                    } else {
                        task = task.append("-1");
                    }
                    if (!StringsUtils.isEmpty(ent.getEntHorasEstimadas())) {
                        task = task.append(",\"" + ConstantesPresentacion.TASK_HORAS_ESTIMADAS + "\":\"")
                                .append(StringsUtils.toString(ent.getEntHorasEstimadas()))
                                .append("\"");
                    }
                    task = task.append(",\"" + ConstantesPresentacion.TASK_ESFUERZO + "\":")
                            .append(StringsUtils.toString(ent.getEntEsfuerzo() != null ? ent.getEntEsfuerzo() : 0))
                            .append(",\"" + ConstantesPresentacion.TASK_START_LINEA_BASE + "\":")
                            .append(StringsUtils.toString(ent.getEntInicioLineaBase() != null ? ent.getEntInicioLineaBase() : 0))
                            .append(",\"" + ConstantesPresentacion.TASK_DURACION_LINEA_BASE + "\":")
                            .append(StringsUtils.toString(ent.getEntDuracionLineaBase() != null
                                    ? ent.getEntDuracionLineaBase()
                                    : ent.getEntInicioLineaBase() != null && ent.getEntFinLineaBase() != null
                                    ? DatesUtils.diasEntreFechas(ent.getEntInicioLineaBaseDate(), ent.getEntFinLineaBaseDate())
                                    : 0))
                            .append(",\"" + ConstantesPresentacion.TASK_END_LINEA_BASE + "\":")
                            .append(StringsUtils.toString(ent.getEntFinLineaBase() != null ? ent.getEntFinLineaBase() : 0))
                            .append(",\"" + ConstantesPresentacion.TASK_INICIO_PROYECTO + "\":")
                            .append(ent.getEntInicioProyecto())
                            .append(",\"" + ConstantesPresentacion.TASK_FIN_PROYECTO + "\":")
                            .append(ent.getEntFinProyecto());

                    if (ent.getEntPredecesorFk() != null) {
                        task = task.append(",\"" + ConstantesPresentacion.TASK_DEPENDS + "\":\"")
                                .append(StringsUtils.toString(ent.getEntPredecesorFk()))
                                .append("\"");
                    }

                    if (tieneProductos) {
                        task = task.append(",\"" + ConstantesPresentacion.TASK_TIENE_PRODUCTOS + "\":")
                                .append(StringsUtils.toString(tieneProductos));
                    }

                    task = task.append(",\"" + ConstantesPresentacion.TASK_PROGRESS + "\":")
                            .append(StringsUtils.toString(ent.getEntProgreso()))
                            .append("}");
                    dataCrono = dataCrono.append(task);
                }
            }

            boolean isUsuPermitido = SsUsuariosUtils.isUsuarioGerenteOAdjuntoFicha(fichaTO, usuario)
                    || isCoordAlgunEntregable();
            boolean isUsuPMO = SsUsuariosUtils.isUsuarioPMO(fichaTO, usuario, orgPk);
            boolean moreRows = ((fichaTO.isEstado(Estados.ESTADOS.INICIO.estado_id)
                    || fichaTO.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id))
                    && isUsuPermitido);

            Configuracion cnfSetPeriodoGantt = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PROYECTO_GANTT_PERIODO, inicioMB.getOrganismoSeleccionado());
            boolean setPeriodoGantt = cnfSetPeriodoGantt != null && "true".equals(cnfSetPeriodoGantt.getCnfValor()) ? true : false;

            dataCrono = dataCrono.append("],\"" + ConstantesPresentacion.CRONO_SELECTED_ROW + "\":")
                    .append(StringsUtils.toString(cro.getCroEntSeleccionado()))
                    .append(",\"" + ConstantesPresentacion.CRONO_DELETED_TASK_ID + "\":")
                    .append("[]")
                    .append(",\"" + ConstantesPresentacion.CRONO_RESOURCES + "\":")
                    .append("[]")
                    .append(",\"" + ConstantesPresentacion.CRONO_CAN_WRITE + "\":")
                    //		    .append(StringsUtils.toString(cro.getCroPermisoEscritura() && (isUsuPermitido || isUsuPMO)))
                    .append(StringsUtils.toString(cro.getCroPermisoEscritura() && (isUsuPermitido)))
                    .append(",\"" + ConstantesPresentacion.CRONO_CAN_WRITE_PARENT + "\":")
                    .append(StringsUtils.toString(cro.getCroPermisoEscrituraPadre()))
                    .append(",\"" + ConstantesPresentacion.FICHA_PK + "\":")
                    .append(StringsUtils.toString(fichaTO.getFichaFk()))
                    .append(",\"" + ConstantesPresentacion.FICHA_TIPO + "\":")
                    .append(StringsUtils.toString(fichaTO.getTipoFicha()))
                    .append(",\"" + ConstantesPresentacion.CRONO_MORE_ROWS + "\":")
                    .append(StringsUtils.toString(moreRows))
                    .append(",\"" + ConstantesPresentacion.FICHA_ESTADO + "\":")
                    .append(StringsUtils.toString(fichaTO.getEstado().getEstPk()))
                    .append(",\"" + ConstantesPresentacion.CRONO_ESFUERZO_TOTAL + "\":")
                    .append(cronogramaDelegate.esfuerzoTotal(cro))
                    .append(",\"" + ConstantesPresentacion.CRONO_HORAS_TOTAL + "\":\"")
                    .append(cronogramaDelegate.horasTotales(cro)).append("\"")
                    .append(",\"" + ConstantesPresentacion.CRONO_ROWS_GANTT + "\":\"")
                    .append(100).append("\"")
                    .append(",\"" + ConstantesPresentacion.CRONO_SET_PERIODO_ENTREGABLE + "\":")
                    .append(StringsUtils.toString(setPeriodoGantt));
            if (cro.getCroPk() != null) {
                dataCrono = dataCrono.append(",\"" + ConstantesPresentacion.CRONO_PK + "\":")
                        .append(StringsUtils.toString(cro.getCroPk()));
            }

            String usuArray = ssUsuarioDelegate.obtenerTodosPorOrgCronograma(orgPk);
            dataCrono = dataCrono.append(",\"coordinadores\":");
            if (!StringsUtils.isEmpty(usuArray)) {
                dataCrono = dataCrono.append(usuArray);
            } else {
                dataCrono = dataCrono.append("[]");
            }

            boolean isPM = SsUsuariosUtils.isUsuarioGerenteOAdjuntoFicha(fichaTO, usuario);
            dataCrono = dataCrono.append(",\"usuarioId\":")
                    .append(usuario.getUsuId().toString())
                    .append(",\"isPM\":")
                    .append(StringsUtils.toString(isPM));

            boolean isPmoFederadaFicha = false;
            try {
                isPmoFederadaFicha = usuario.getUsuId().equals(fichaTO.getUsrPmofedFk().getUsuId());
            } catch (Exception w) {
                w.printStackTrace();
            }
            boolean isPMO = SsUsuariosUtils.isUsuarioPMO(fichaTO, usuario, orgPk) || isPmoFederadaFicha;
            dataCrono = dataCrono.append(",\"isPMO\":")
                    .append(StringsUtils.toString(isPMO));
            dataCrono = dataCrono.append("}");
        }

        if (actualizar) {
            JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), "configGannt();");
        }

        JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), "loadGanttValues(" + dataCrono + ");");
    }

    public void subirArchivoDocAction(FileEntryEvent e) {

        JSFUtils.removerMensages();
        logger.info("[subirArchivoAction] Subiendo archivo...");
        if (e != null && e.getComponent() != null) {
            FileEntry fe = (FileEntry) e.getComponent();
            FileEntryResults results = fe.getResults();
            File archivoS = results.getFiles() != null ? results.getFiles().get(0).getFile() : null;
            if (archivoS == null) {
                JSFUtils.agregarMsg(fe.getClientId(), "error_archivo_subido_fail", null);
            }
            try {
                String mimeType = new MimetypesFileTypeMap().getContentType(archivoS);
                logger.log(Level.FINEST, "Mime Type:", mimeType);
                if (mimeType != null) {
                    //FIXME FD boolean esValido = archivoHechos.isAValidMimeType(mimeType);
                    boolean esValido = true;
                    if (!esValido) {
                        JSFUtils.agregarMsg("error_archivo_no_permitido");
                        return;
                    }
                } else {
                    JSFUtils.agregarMsg("error_archivo_no_permitido");
                    return;
                }
                logger.info("[subirArchivoAction] Subiendo archivo finalizado");
                upFileDoc = archivoS;
            } catch (Exception e2) {
                JSFUtils.agregarMsg("error_subir_archivo");
                logger.log(Level.SEVERE, e2.getMessage(), e2);
            }
        }
    }

    public void subirArchivoMediaAction(FileEntryEvent e) {
        JSFUtils.removerMensages();
        logger.info("[subirArchivoAction] Subiendo archivo...");
        if (e != null && e.getComponent() != null) {
            FileEntry fe = (FileEntry) e.getComponent();
            FileEntryResults results = fe.getResults();
            File archivoS = results.getFiles() != null ? results.getFiles().get(0).getFile() : null;
            if (archivoS == null) {
                //JSFUtils.agregarMsg(fe.getClientId(), "error_archivo_subido_fail", null);
                JSFUtils.agregarMsgError("multiForm", Labels.getValue("error_archivo_no_permitido"), null);
            } else {
                try {
                    String mimeType = new MimetypesFileTypeMap().getContentType(archivoS);
                    logger.log(Level.FINEST, "Mime Type:", mimeType);
                    if (mimeType != null) {
                        boolean esValido = false;
                        if (mimeType.matches(configuracionDelegate.obtenerCnfValorPorCodigo("MEDIA_MIMETYPE_REGEX", null))) {
                            esValido = true;
                        }
                        if (!esValido) {
                            JSFUtils.agregarMsgError("multiForm", Labels.getValue("error_archivo_no_permitido"), null);
                            return;
                        }
                    } else {
                        JSFUtils.agregarMsgError("multiForm", Labels.getValue("error_archivo_no_permitido"), null);
                        return;
                    }
                    logger.info("[subirArchivoAction] Subiendo archivo finalizado");

                    mediaProy.setMediaBytes(FileUtils.readFileToByteArray(archivoS));
                } catch (Exception e2) {
                    //JSFUtils.agregarMsg("multiForm", Labels.getValue("error_subir_archivo"), null);
                    JSFUtils.agregarMsgError("multiForm", Labels.getValue("error_archivo_no_permitido"), null);
                    logger.log(Level.SEVERE, e2.getMessage(), e2);
                }
            }
        }
    }

    public String abrirDocumentoAction() {
        limpiarDocumento();
        docsFormDataExpanded = true;
        return null;
    }

    public String editarDocumentoAction(Integer docPk) {

        documento = documentosDelegate.obtenerDocumentosPorId(docPk);
        listaTipoDocCombo.setSelected(documento.getDocsTipo().getTipodocInstPk());
        if (documento.getDocsEntregable() != null) {
            listaEntregablesCombo.setSelected(documento.getDocsEntregable().getEntPk());
        }
        if (documento.getDocsPagoFk() != null) {
            listaDocPagoCombo.setSelectedT(documento.getDocsPagoFk());
        } else {
            listaDocPagoCombo.setSelected(-1);
        }
        docsFormDataExpanded = true;

        return null;
    }

    public void verHistoricoDocumentoAction(Integer docPk) {
        documento = documentosDelegate.obtenerDocumentosPorId(docPk);
        List<DocFile> docFiles = documentosDelegate.obtenerHistoricoDocFile(documento);
        documento.setDocFileHistorico(docFiles);
        docHistDownloadFile.clear();
        String dir = configuracionDelegate.obtenerCnfValorPorCodigo(
                ConfiguracionCodigos.DOCUMENTOS_DIR,
                null);
        dir += "/" + fichaTO.getOrgFk().getOrgPk();
        for (DocFile df : docFiles) {
            docHistDownloadFile.put(df.getDocfileVersion(), new SofisResource(df, dir));
        }
        renderPopupDocumentoHistorico = true;

    }

    public void cerrarPopupDocumentoHistorico() {
        documento.setDocFileHistorico(null);
        renderPopupDocumentoHistorico = false;
    }

    public String eliminarDocumentoAction(Integer docPk) {
        try {
            documentosDelegate.eliminarDocumento(docPk, fichaTO.getFichaFk(), fichaTO.getTipoFicha(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());

            Documentos doc = documentosDelegate.obtenerDocumentosPorId(docPk);
            if (doc == null) {
                doc = new Documentos(docPk);
                fichaTO.getDocumentos().remove(doc);
            }
        } catch (BusinessException be) {
            logger.log(Level.SEVERE, null, be);
            JSFUtils.agregarMsg(DOC_MSG_ID, "error_eliminar_documento", null);
        }

        cargarFrameDocumentos();
        limpiarDocumento();
        cerrarFormCollapsable();

        return null;
    }

    public String agregarDocumentoConArchivoAction(FileEntryEvent e) {
        subirArchivoDocAction(e);
        return agregarDocumentoAction();
    }

    public String agregarDocumentoAction() {

        logger.fine("Agregando Documento a la ficha.");
        JSFUtils.removerMensages();
        documento.setDocsEntregable(null);
        if (listaEntregablesCombo.getSelectedObject() != null) {
            Entregables ent = (Entregables) listaEntregablesCombo.getSelectedObject();
            if (ent != null && ent.getEntPk() != null && ent.getEntPk() != -1) {
                documento.setDocsEntregable(ent);
            }
        }

        if (listaTipoDocCombo.getSelectedObject() != null) {
            documento.setDocsTipo((TipoDocumentoInstancia) listaTipoDocCombo.getSelectedObject());
        }

        Pagos pago = listaDocPagoCombo.getSelectedT();
        if (pagos != null) {
            documento.setDocsPagoFk(pago);
        }

        if (documento.getDocsEstado() != null && documento.getDocsEstado().doubleValue() == -1d) {
            documento.setDocsEstado(null);
        }

        FileInputStream fileInputStream = null;

        try {

            if (upFileDoc != null) {

                try {
                    fileInputStream = new FileInputStream(upFileDoc);
                    documento.setDocFile(documentosDelegate.obtenerDocFilePorDocId(documento.getDocsPk()));
                    if (documento.getDocFile() == null) {
                        documento.setDocFile(new DocFile());
                    }
                    documento.getDocFile().setDocfileFileStream(fileInputStream);
                    documento.getDocFile().setDocfileNombre(upFileDoc.getName());
                    documento.getDocFile().setDocfileDocFk(documento);

                } catch (IOException ex) {
                    logger.log(Level.SEVERE, null, ex);
                    JSFUtils.agregarMsg(DOC_MSG_ID, "error_docs_archivo", null);
                    TechnicalException te = new TechnicalException(ex);
                    te.addError(ex.getMessage());
                    throw te;
                }
            } else if (documento.getDocsPk() == null) {
                JSFUtils.agregarMsg(DOC_MSG_ID, "error_agregar_documento_doc_file_null", null);
                return null;
            }

            try {
                FichaDocumentosValidacion.validar(documento);

                Object progProy = documentosDelegate.guardarDocumentos(documento, fichaTO.getFichaFk(), fichaTO.getTipoFicha(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
                JSFUtils.agregarMsg(DOC_MSG_ID, "info_documento_agregado", null);

                actualizarFichaTO(progProy);
                cargarFrameDocumentos();
                limpiarDocumento();
                cerrarFormCollapsable();

            } catch (BusinessException w) {
                logger.log(Level.SEVERE, null, w);
                JSFUtils.agregarMsg(DOC_MSG_ID, "error_agregar_documento", null);
                JSFUtils.agregarMsgs(DOC_MSG_ID, w.getErrores());
            }

        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            JSFUtils.agregarMsg(DOC_MSG_ID, "error_agregar_documento", null);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();

                } catch (IOException ex) {
                    Logger.getLogger(FichaMB.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return null;
    }

    public String guardarDocumentoPopupAction() {
        logger.fine("Guardar Documento del Popup.");

        boolean isPMO = inicioMB.isUsuarioOrgaPMOT()
                || SsUsuariosUtils.isUsuarioPMOF(fichaTO, inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
        if (isPMO) {
            documentoPopup.setDocsAprobado(Boolean.TRUE);
        }

        try {

            FichaDocumentosValidacion.validar(documentoPopup);
            if (documentoPopup.getDocsEstado() != null && documentoPopup.getDocsEstado().doubleValue() == -1d) {
                documentoPopup.setDocsEstado(null);
            }
            Object progProy = documentosDelegate.guardarDocumentos(documentoPopup, fichaTO.getFichaFk(), fichaTO.getTipoFicha(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());

            actualizarFichaTO(progProy);
            JSFUtils.agregarMsg("info_est_doc_actualizado");
            cargarFrameDocumentos();
            renderPopupDocumentos = false;

        } catch (BusinessException | TechnicalException w) {
            List<String> errores = w.getErrores();
            JSFUtils.agregarMsgs(DOC_MSG_ID, errores);

        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            List<String> errores = ex.getErrores();
            JSFUtils.agregarMsgs(DOC_MSG_ID, errores);
        }
        return null;
    }

    /**
     * Solución realizada para que al actualizar datos en la tabla de documentos
     * éstos se vuelvan a ordenar por tipo.
     */
    public void agruparDocumentosVista() {
        //parche para solucionar el group by al dar de alta un nuevo elemento
        JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), "$(\"#ficha\\\\:docTable\\\\:docTableColTipNo\").click();");
        JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), "$(\"#ficha\\\\:docTable\\\\:docTableColTipNo\").click();");
    }

    /**
     * Limpia los valores del formulario del Documento.
     *
     * @return
     */
    public String limpiarDocumento() {
        try {
            documento = new Documentos();
            documento.setDocsPrivado(false);
            documento.setDocsEntregable(new Entregables());
            documento.setDocsTipo(new TipoDocumentoInstancia());
            upFileDoc = null;
            listaTipoDocCombo.setSelectedObject(null);
            listaEntregablesCombo.setSelectedObject(null);
            JSFUtils.removerMensages();

        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="areaTematica">
    public String cerrarPopupAreaTematica() {
        try {
            renderPopupAreaTematica = false;

        } catch (GeneralException ex) {
            Logger.getLogger(FichaMB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="metodologia">
    public String cerrarPopupMetodologia() {
        try {
            renderPopupMetodologia = false;

        } catch (GeneralException ex) {
            Logger.getLogger(FichaMB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String guardarMetodologiaProyecto() {
        //guardamos la metodologia

        List<TipoDocumentoInstancia> toUpdate = getFichaTO().getTipoDocumentoInstancias();
        try {
            getFichaTO().setTipoDocumentoInstancias(tipoDocumentoInstanciaDelegate.guardarTiposDocsIntancia(toUpdate));
            proyectoDelegate.guardarIndicadoresSimple(fichaTO.getFichaFk(), true, false, fichaTO.getOrgFk().getOrgPk(), null, true);
            JSFUtils.agregarMsg("info_metodologia_guardada");

            actualizarFichaTO(null);
            cargarResumenDocumentos();
            cargarFrameDocumentos();
            renderPopupMetodologia = false;
        } catch (GeneralException ge) {
            JSFUtils.agregarMsg(ge.getMessage());
        }

        return null;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="lectura">
    public String cerrarPopupLectura() {
        try {
            renderPopupLectura = false;

        } catch (GeneralException ex) {
            Logger.getLogger(FichaMB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List getAreasRestringidasSelected() {
        if (areasRestringidasStateMap == null) {
            return Collections.emptyList();
        }
        return areasRestringidasStateMap.getSelected();
    }

    public List getAreasTematicasSelected() {
        if (areasTematicasStateMap == null) {
            return Collections.emptyList();
        }
        return areasTematicasStateMap.getSelected();
    }

    public boolean areasTematicasStateMapHasValues() {
        return areasTematicasStateMap != null && !areasTematicasStateMap.isEmpty();
    }

    public boolean areasTematicasHasValues() {
        if (areasTematicasStateMap == null || areasTematicasStateMap.isEmpty()) {
            return fichaTO.getAreasTematicas() != null ? !fichaTO.getAreasTematicas().isEmpty() : false;
        }
        return !getAreasTematicasSelected().isEmpty();
    }

    public boolean areasRestringidasStateMapHasValues() {
        return areasRestringidasStateMap != null && !areasRestringidasStateMap.isEmpty();
    }

    public boolean areasRestringidasHasValues() {
        if (areasRestringidasStateMap == null) {
            return fichaTO.getAreasRestringidas() != null ? !fichaTO.getAreasRestringidas().isEmpty() : false;
        }
        return !getAreasRestringidasSelected().isEmpty();
    }

    // </editor-fold>
    /**
     * Carga los datos de Programas en el fichaTO. Tambien se cargan los valores
     * seleccionados en los combos.
     *
     * @param prog
     */
    private void programaToFichaTO(Programas prog) {
        if (prog != null) {
            fichaTO = new FichaTO();
            fichaTO.setFichaFk(prog.getProgPk());
            fichaTO.setTipoFicha(TipoFichaEnum.PROGRAMA.id);
            fichaTO.setOrgFk(prog.getProgOrgFk());
            fichaTO.setUltimaModificacion(prog.getProgUltMod());
            fichaTO.setFechaCrea(prog.getProgFechaCrea());
//            if (prog.getProgIndices() != null) {
//                fichaTO.setFechaAct(prog.getProgIndices().getProyActualizacion());
//            }

            fichaTO.setFechaAct(prog.getProgFechaAct());
            fichaTO.setActivo(prog.getActivo());

            fichaTO.setNombre(prog.getProgNombre());
            fichaTO.setDescripcion(prog.getProgDescripcion());
            fichaTO.setObjetivo(prog.getProgObjetivo());
            fichaTO.setObjPublico(prog.getProgObjPublico());
            fichaTO.setFactorImpacto(prog.getProgFactorImpacto());
            fichaTO.setSemaforoAmarillo(prog.getProgSemaforoAmarillo());
            fichaTO.setSemaforoRojo(prog.getProgSemaforoRojo());
            fichaTO.setGrp(prog.getProgGrp());
            fichaTO.setEstado(prog.getProgEstFk());
            fichaTO.setEstadoPendiente(prog.getProgEstPendienteFk());

            fichaTO.setUsrAdjuntoFk(prog.getProgUsrAdjuntoFk());
            fichaTO.setUsrGerenteFk(prog.getProgUsrGerenteFk());
            fichaTO.setUsrPmofedFk(prog.getProgUsrPmofedFk());
            fichaTO.setUsrSponsorFk(prog.getProgUsrSponsorFk());

            fichaTO.setCroFk(prog.getProgCroFk());
            fichaTO.setPreFk(prog.getProgPreFk());

            fichaTO.setProgIndices(prog.getProgIndices());
            fichaTO.setProyProgVersion(prog.getProgVersion());
            fichaTO.setObjetivoEstrategico(prog.getObjetivoEstrategico());

            if (fichaTO.hasPk()) {
                fichaTO.setTipoDocumentoInstancias(tipoDocumentoInstanciaDelegate.obtenerTipoDocsInstanciaPorProgProyId(fichaTO.getFichaFk(), fichaTO.getTipoFicha(), fichaTO.getOrgFk().getOrgPk()));
            }

            if (fichaTO.hasPk()) {
                //Interesados es Lazy 
                List<Interesados> interesadosList = interesadosDelegate.obtenerIntersadosPorFichaPk(fichaTO.getFichaFk(), fichaTO.getTipoFicha());
                if (interesadosList != null) {
                    fichaTO.setInteresados(interesadosList);
                }
            }

            if (fichaTO.hasPk()) {
                //Documentos es Lazy 
                List<Documentos> documentosList = documentosDelegate.obtenerDocumentosPorFichaPk(fichaTO.getFichaFk(), fichaTO.getTipoFicha());
                if (documentosList != null) {
                    fichaTO.setDocumentos(documentosList);
                }
            } else {
                fichaTO.setDocumentos(new ArrayList<Documentos>());
            }

            /**
             * 9-12-2016
             */
            if (prog.getProgAreaFk() != null) {
                if (prog.getProgAreaFk().getAreaHabilitada()) {
                    listaAreasOrganismoCombo.setSelected(prog.getProgAreaFk().getAreaPk());
                } else {
                    if (!listaAreas.contains(prog.getProgAreaFk())) {
                        listaAreas.add(prog.getProgAreaFk());
                    }
                    Collections.sort(listaAreas, new Comparator<Areas>() {
                        @Override
                        public int compare(Areas o1, Areas o2) {
                            return o1.getAreaNombre().compareTo(o2.getAreaNombre());
                        }
                    });
                    listaAreasOrganismoCombo = new SofisCombo((List) listaAreas, "areaNombre");
                    listaAreasOrganismoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
                    listaAreasOrganismoCombo.setSelected(prog.getProgAreaFk().getAreaPk());
                }
            }

            if (prog.getProgUsrSponsorFk() != null) {
                listaSponsorCombo.setSelected(prog.getProgUsrSponsorFk().getUsuId());
            }
            if (prog.getProgUsrAdjuntoFk() != null) {
                listaAdjuntoCombo.setSelected(prog.getProgUsrAdjuntoFk().getUsuId());
            }
            if (prog.getProgUsrGerenteFk() != null) {
                listaGerenteCombo.setSelected(prog.getProgUsrGerenteFk().getUsuId());
            }
            if (prog.getProgUsrPmofedFk() != null) {
                listaPmoFederadaCombo.setSelected(prog.getProgUsrPmofedFk().getUsuId());
            }
            if (prog.getProgPk() != null) {
                listaProgramasCombo.setSelected(prog.getProgPk());
            }

            if (prog.getObjetivoEstrategico() != null) {
                listaObjetivosEstrategicosCombo.setSelectedT(prog.getObjetivoEstrategico());
            }

//            if (prog.getProgPreFk() != null) {
//                presupuesto = prog.getProgPreFk();
//                if (presupuesto.getPreMoneda() != null) {
//                    listaMonedaCombo.setSelected(presupuesto.getPreMoneda().getMonPk());
//                }
//                if (presupuesto.getFuenteFinanciamiento() != null) {
//                    listaFuentesCombo.setSelected(presupuesto.getFuenteFinanciamiento().getFuePk());
//                }
//            }
            if (prog.getProgPreFk() != null) {
                preFicha = prog.getProgPreFk();
                if (preFicha.getPreMoneda() != null) {
                    listaMonedaPreCombo.setSelected(preFicha.getPreMoneda().getMonPk());
                }
                if (preFicha.getFuenteFinanciamiento() != null) {

                    addItemSofisCombo(listaFuentesPreCombo, preFicha.getFuenteFinanciamiento(), 
                            preFicha.getFuenteFinanciamiento().getFuePk(), true);
                    
                    listaFuentesPreCombo.setSelected(preFicha.getFuenteFinanciamiento().getFuePk());
                }
            }

            if (fichaTO.hasPk()) {
                //Areas Restringidas es Lazy 
                List<Areas> areasRestList = areasDelegate.obtenerAreasRestringidasPorFichaPk(fichaTO.getFichaFk(), fichaTO.getTipoFicha());
                if (areasRestList != null) {
                    fichaTO.setAreasRestringidas(new HashSet<Areas>(areasRestList));
                }
            }

            if (fichaTO.hasPk()) {
                //Areas Tematicas es Lazy 
                List<AreasTags> areasTemaList = areaTematicaDelegate.obtenerAreasTematicasPorFichaPk(fichaTO.getFichaFk(), fichaTO.getTipoFicha());
                if (areasTemaList != null) {
                    fichaTO.setAreasTematicas(new HashSet<AreasTags>(areasTemaList));
                }
            }

            try {
                fichaTO.setResumenEjecutivo(documentosDelegate.obtenerResumenEjecutivo(prog.getProgPk(), TipoFichaEnum.PROGRAMA));
                documentosDelegate.cargarArchivosDocumentos(fichaTO.getResumenEjecutivo());
            } catch (Exception e) {
                e.printStackTrace();
            }

            cargarDocFiles();
            fichaTOOriginal(prog);
        }
    }

    /**
     * Carga los datos de Proyectos en el fichaTO. Tambien se cargan los valores
     * seleccionados en los combos.
     *
     * @param proy
     */
    public void proyectoToFichaTO(Proyectos proy) {
        if (proy != null) {
            fichaTO = new FichaTO();
            fichaTO.setFichaFk(proy.getProyPk());
            fichaTO.setTipoFicha(TipoFichaEnum.PROYECTO.id);
            fichaTO.setOrgFk(proy.getProyOrgFk());
            fichaTO.setActivo(proy.getActivo());
            fichaTO.setFechaCrea(proy.getProyFechaCrea());
            fichaTO.setFechaAct(proy.getProyFechaAct());
            fichaTO.setFechaActColor(proyectoDelegate.obtenerUltimaActualizacionColor(proy.getProyEstFk(), proy.getProyFechaAct(), proy.getProySemaforoAmarillo(), proy.getProySemaforoRojo()));
            fichaTO.setAreaFk(proy.getProyAreaFk());

            fichaTO.setNombre(proy.getProyNombre());
            fichaTO.setDescripcion(proy.getProyDescripcion());
            fichaTO.setObjetivo(proy.getProyObjetivo());
            fichaTO.setObjPublico(proy.getProyObjPublico());
            fichaTO.setFactorImpacto(proy.getProyFactorImpacto());
            fichaTO.setProgFk(proy.getProyProgFk());
            fichaTO.setPeso(proy.getProyPeso());
            fichaTO.setSituacionActual(proy.getProySituacionActual());
            fichaTO.setSemaforoAmarillo(proy.getProySemaforoAmarillo());
            fichaTO.setSemaforoRojo(proy.getProySemaforoRojo());
            fichaTO.setGrp(proy.getProyGrp());
            fichaTO.setEstado(proy.getProyEstFk());
            fichaTO.setEstadoPendiente(proy.getProyEstPendienteFk());
            fichaTO.setUsrAdjuntoFk(proy.getProyUsrAdjuntoFk());
            fichaTO.setUsrGerenteFk(proy.getProyUsrGerenteFk());
            fichaTO.setUsrPmofedFk(proy.getProyUsrPmofedFk());
            fichaTO.setUsrSponsorFk(proy.getProyUsrSponsorFk());

            fichaTO.setCroFk(proy.getProyCroFk());
            fichaTO.setPreFk(proy.getProyPreFk());

            fichaTO.setProyIndices(proy.getProyIndices());
            fichaTO.setProyProgVersion(proy.getProyVersion());

            fichaTO.setFechaActPub(proy.getProyFechaActPub());

            if (fichaTO.hasPk()) {
                //Documentos es Lazy 
                List<Documentos> documentosList = documentosDelegate.obtenerDocumentosPorFichaPk(fichaTO.getFichaFk(), fichaTO.getTipoFicha());
                if (documentosList != null) {
                    fichaTO.setDocumentos(documentosList);
                }
            } else {
                fichaTO.setDocumentos(new ArrayList<Documentos>());
            }

            if (fichaTO.hasPk()) {
                fichaTO.setTipoDocumentoInstancias(tipoDocumentoInstanciaDelegate.obtenerTipoDocsInstanciaPorProgProyId(fichaTO.getFichaFk(), fichaTO.getTipoFicha(), fichaTO.getOrgFk().getOrgPk()));
            }

            if (fichaTO.hasPk()) {
                //Interesados es Lazy 
                List<Interesados> interesadosList = interesadosDelegate.obtenerIntersadosPorFichaPk(fichaTO.getFichaFk(), fichaTO.getTipoFicha());
                if (interesadosList != null) {
                    fichaTO.setInteresados(interesadosList);
                }
            }

            if (fichaTO.hasPk()) {
                //Riesgos es Lazy 
                List<Riesgos> riesgosList = riesgosDelegate.obtenerRiesgosPorProyecto(fichaTO.getFichaFk());
                if (riesgosList != null) {
                    fichaTO.setRiesgos(riesgosList);
                }
            }

            /**
             * 9-12-2016
             */
            if (proy.getProyAreaFk() != null) {
                if (proy.getProyAreaFk().getAreaHabilitada()) {
                    listaAreasOrganismoCombo.setSelected(proy.getProyAreaFk().getAreaPk());
                } else {
                    if (!listaAreas.contains(proy.getProyAreaFk())) {
                        listaAreas.add(proy.getProyAreaFk());
                    }
                    Collections.sort(listaAreas, new Comparator<Areas>() {
                        @Override
                        public int compare(Areas o1, Areas o2) {
                            return o1.getAreaNombre().compareTo(o2.getAreaNombre());
                        }
                    });
                    listaAreasOrganismoCombo = new SofisCombo((List) listaAreas, "areaNombre");
                    listaAreasOrganismoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
                    listaAreasOrganismoCombo.setSelected(proy.getProyAreaFk().getAreaPk());
                }
            }
            if (proy.getProyUsrSponsorFk() != null) {
                listaSponsorCombo.setSelected(proy.getProyUsrSponsorFk().getUsuId());
            }
            if (proy.getProyUsrAdjuntoFk() != null) {
                listaAdjuntoCombo.setSelected(proy.getProyUsrAdjuntoFk().getUsuId());
            }
            if (proy.getProyUsrGerenteFk() != null) {
                listaGerenteCombo.setSelected(proy.getProyUsrGerenteFk().getUsuId());
            }
            if (proy.getProyUsrPmofedFk() != null) {
                listaPmoFederadaCombo.setSelected(proy.getProyUsrPmofedFk().getUsuId());
            }
            if (proy.getProyOtrosDatos() != null && proy.getProyOtrosDatos().getProyOtrEtaFk() != null) {
                listaEtapaPubCombo.setSelectedObject(proy.getProyOtrosDatos().getProyOtrEtaFk());
            }
            if (proy.getProyPk() != null && proy.getProyProgFk() != null) {
                if (!listaProgramas.contains(proy.getProyProgFk())) {
                    listaProgramas.add(proy.getProyProgFk());
                    Collections.sort(listaProgramas, new Comparator() {
                        @Override
                        public int compare(Object o1, Object o2) {
                            Programas p1 = (Programas) o1;
                            Programas p2 = (Programas) o2;
                            return p1.getProgNombre().compareTo(p2.getProgNombre());
                        }
                    });
                    listaProgramasCombo = new SofisCombo((List) listaProgramas, "nombreComboFicha");
                    listaProgramasCombo.addEmptyItem(Labels.getValue("comboProgramasFichaEmpty"));
                }
                listaProgramasCombo.setSelected(proy.getProyProgFk().getProgPk());
            }

//            if (proy.getProyPreFk() != null) {
//                presupuesto = proy.getProyPreFk();
//                if (presupuesto.getPreMoneda() != null) {
//                    listaMonedaCombo.setSelected(presupuesto.getPreMoneda().getMonPk());
//                }
//                if (presupuesto.getFuenteFinanciamiento() != null) {
//                    listaFuentesCombo.setSelected(presupuesto.getFuenteFinanciamiento().getFuePk());
//                }
//            }
            if (proy.getProyPreFk() != null) {
                preFicha = proy.getProyPreFk();
                if (preFicha.getPreMoneda() != null) {
                    listaMonedaPreCombo.setSelected(preFicha.getPreMoneda().getMonPk());
                }
                if (preFicha.getFuenteFinanciamiento() != null) {
                    
                    addItemSofisCombo(listaFuentesPreCombo, preFicha.getFuenteFinanciamiento(), 
                        preFicha.getFuenteFinanciamiento().getFuePk(), true);
                    
                    listaFuentesPreCombo.setSelected(preFicha.getFuenteFinanciamiento().getFuePk());
                }
            }

            if (fichaTO.hasPk()) {
                //Areas Restringidas es Lazy 
                List<Areas> areasRestList = areasDelegate.obtenerAreasRestringidasPorFichaPk(fichaTO.getFichaFk(), fichaTO.getTipoFicha());
                if (areasRestList != null) {
                    fichaTO.setAreasRestringidas(new HashSet<Areas>(areasRestList));
                }
            }

            if (fichaTO.hasPk()) {
                //Areas Tematicas es Lazy 
                List<AreasTags> areasTemaList = areaTematicaDelegate.obtenerAreasTematicasPorFichaPk(fichaTO.getFichaFk(), fichaTO.getTipoFicha());
                if (areasTemaList != null) {
                    fichaTO.setAreasTematicas(new HashSet<AreasTags>(areasTemaList));
                }
            }

            try {
                fichaTO.setResumenEjecutivo(documentosDelegate.obtenerResumenEjecutivo(proy.getProyPk(), TipoFichaEnum.PROYECTO));
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }

            fichaTO.setFechaUltimaSitAct(DatesUtils.toStringFormat(proySitActHistoricoDelegate.obtenerUltimaFechaSitAct(proy.getProyPk()), ConstantesEstandares.CALENDAR_PATTERN));
            fechaUltimaSitAct = !StringsUtils.isEmpty(fichaTO.getFechaUltimaSitAct()) ? "(" + fichaTO.getFechaUltimaSitAct() + ")" : fichaTO.getFechaUltimaSitAct();

            if (fichaTO.hasPk()) {
                List<Participantes> participantesList = participantesDelegate.obtenerParticipantesPorFichaPk(fichaTO.getFichaFk());
                if (participantesList != null) {
                    fichaTO.setParticipantes(participantesList);
                }
            }

            //Otros Datos
            fichaTO.setPublicable(proy.getProyPublicable());
            fichaTO.setOtrosDatos(proy.getProyOtrosDatos());
//			fichaTO.setLatlngProy(proy.getProyLatlngFk() != null ? proy.getProyLatlngFk() : new LatlngProyectos());
            /**
             * 12-10-17 Bruno: agregadas localizaciones
             */
            fichaTO.setLatLngProyList(new ArrayList<LatlngProyectos>(proy.getLatLngProyList()));

            // Objetivos Estrategicos
            fichaTO.setObjetivoEstrategico(proy.getObjetivoEstrategico());
            if (proy.getObjetivoEstrategico() != null) {
                listaObjetivosEstrategicosCombo.setSelectedT(proy.getObjetivoEstrategico());
            }

            cargarDocFiles();
            fichaTOOriginal(proy);
        }
    }

    /**
     * Carga en fichaTO algunos datos originales para saber que se modificó.
     *
     * @param p
     */
    private void fichaTOOriginal(Object p) {
        if (p != null) {
            FichaTO fichaOrigTO = new FichaTO();

            if (p instanceof Programas) {
                Programas prog = (Programas) p;

                fichaOrigTO.setFichaFk(prog.getProgPk());
                fichaOrigTO.setTipoFicha(TipoFichaEnum.PROGRAMA.id);
                fichaOrigTO.setEstado(prog.getProgEstFk());

            } else if (p instanceof Proyectos) {
                Proyectos proy = (Proyectos) p;

                fichaOrigTO.setFichaFk(proy.getProyPk());
                fichaOrigTO.setTipoFicha(TipoFichaEnum.PROYECTO.id);
                fichaOrigTO.setSituacionActual(proy.getProySituacionActual());
                fichaOrigTO.setEstado(proy.getProyEstFk());
            }

            this.fichaTO.setFichaOriginal(fichaOrigTO);
        }
    }

    private void cargarFrameRiesgos() {
        if (fichaTO.getFichaFk() != null && fichaTO.isProyecto()) {
            //carga los riegos de base
            fichaTO.setRiesgos(riesgosDelegate.obtenerRiesgosPorProyecto(fichaTO.getFichaFk()));
            cargarResumenRiesgos();
            generarMatrizRiesgos();
            //TODO cambiar el lugar donde se cargan estos valores.
            listaRiskProbabilidad = new ArrayList<>();
            listaRiskProbabilidad.add(new ComboItemTO(20, "20%"));
            listaRiskProbabilidad.add(new ComboItemTO(40, "40%"));
            listaRiskProbabilidad.add(new ComboItemTO(60, "60%"));
            listaRiskProbabilidad.add(new ComboItemTO(80, "80%"));
            listaRiskProbabilidad.add(new ComboItemTO(100, "100%"));
            if (listaRiskProbabilidadCombo.getAllObjects().isEmpty()
                    && listaRiskProbabilidad != null && !listaRiskProbabilidad.isEmpty()) {
                listaRiskProbabilidadCombo = new SofisCombo((List) listaRiskProbabilidad, "itemNombre");
                listaRiskProbabilidadCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
            }

            listaRiskImpacto = new ArrayList<>();
            listaRiskImpacto.add(2);
            listaRiskImpacto.add(4);
            listaRiskImpacto.add(6);
            listaRiskImpacto.add(8);
            listaRiskImpacto.add(10);
            if (listaRiskImpacto != null && !listaRiskImpacto.isEmpty()) {
                listaRiskImpactoCombo = new SofisCombo((List) listaRiskImpacto);
                listaRiskImpactoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
            }

            List<Entregables> listaRiskEntregable = null;
            if (fichaTO.getCroFk() != null && fichaTO.getCroFk().getEntregablesSet() != null) {
                listaRiskEntregable = new ArrayList<>(fichaTO.getCroFk().getEntregablesSet());
                listaRiskEntregable = EntregablesUtils.cargarCamposCombos(listaRiskEntregable);
            }
            if (listaRiskEntregable != null) {
                listaRiskEntregablesCombo = new SofisComboG<>(listaRiskEntregable, "fechaNivelNombreCombo");
                listaRiskEntregablesCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
            }

            //TODO analizar performance con multiples riesgos
            for (Riesgos r : fichaTO.getRiesgos()) {
                r.setExposicionColor(riesgosDelegate.obtenerExposicionRiesgoColor(r.getExposicion(), inicioMB.getOrganismo().getOrgPk()));
                r.setFechaLimiteColor(riesgosDelegate.obtenerFechaLimiteRiesgoColor(r.getRiskFechaLimite(), inicioMB.getOrganismo().getOrgPk()));
            }

        }
    }

    private void cargarFrameMultimedia(boolean cargarBytes) {
        if (fichaTO.getFichaFk() != null && fichaTO.isProyecto()) {
            List<TiposMedia> listTM = tiposMediaDelegate.obtenerTodos();
            if (listTM != null) {
                listaTipoMediaCombo = new SofisComboG<>(listTM, "tipNombre");
                listaTipoMediaCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
            }
        }

        if (fichaTO.isProyecto()) {
            List<MediaProyectos> listMP = mediaProyectosDelegate.obtenerPorProyId(fichaTO.getFichaFk()); //este trae los datos
            if (cargarBytes) {
                listMP = mediaProyectosDelegate.cargarArchivos(listMP, inicioMB.getOrganismo().getOrgPk());//este carga los bytes
            }

            fichaTO.setListMediaProy(listMP);

            multiCantVideos = 0;
            multiCantFotos = 0;
            multiCantCamaras = 0;
            if (listMP != null) {
                for (MediaProyectos mp : listMP) {
                    if (mp.getMediaTipoFk().isTipoMediaCod(TiposMediaCodigos.VIDEO)) {
                        multiCantVideos++;
                    } else if (mp.getMediaTipoFk().isTipoMediaCod(TiposMediaCodigos.IMG)) {
                        multiCantFotos++;
                    } else if (mp.getMediaTipoFk().isTipoMediaCod(TiposMediaCodigos.CAM)) {
                        multiCantCamaras++;
                    }
                }
            }
        }
    }

    public String agregarPresupuestoAction() {
        logger.fine("Agregar Presupuesto a la Ficha.");
        Moneda moneda = (Moneda) listaMonedaCombo.getSelectedObject();
        presupuesto.setPreMoneda(moneda);
        FuenteFinanciamiento fuente = (FuenteFinanciamiento) listaFuentesCombo.getSelectedObject();
        presupuesto.setFuenteFinanciamiento(fuente);

        try {
            FichaPresupuestoValidacion.validar(presupuesto);

            Object progProy = presupuestoDelegate.guardarPresupuesto(presupuesto, fichaTO.getFichaFk(), fichaTO.getTipoFicha(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
            JSFUtils.agregarMsg("", "info_presupuesto_guardado", null);
            actualizarFichaTO(progProy);

            limpiarPresupuesto();
            cargarFramePresupuestos(false);
            cerrarFormPreCollapsable();

        } catch (BusinessException | TechnicalException w) {
            /*
                    *  18-06-2018 Inspección de código.
             */

            //JSFUtils.agregarMsg(PRESUPUESTO_FORM_MSG_ID, "error_agregar_presupuesto", null);
            //JSFUtils.agregarMsgs(PRESUPUESTO_FORM_MSG_ID, w.getErrores());
            for (String iterStr : w.getErrores()) {
                JSFUtils.agregarMsgError(PRESUPUESTO_FORM_MSG_ID, Labels.getValue(iterStr), null);
            }

        }
        return null;
    }

    public String agregarAdquisicionAction() {
        /*
            *       2018-09-18 RQ-6 Release 5.3.6 : Como se piden todos los OrganiIntProvee para los Pagos, se cambia para las Adquisiciones
            *   solo se asocien a un proveedor.
         */

        //OrganiIntProve orga = (OrganiIntProve) listaOrganizacionCombo.getSelectedObject();
        OrganiIntProve orga = (OrganiIntProve) listaProveedoresCombo.getSelectedObject();
        adquisicion.setAdqProvOrga(orga);
        FuenteFinanciamiento fuente = (FuenteFinanciamiento) listaFuentesCombo.getSelectedObject();
        ComponenteProducto componenteProducto = (ComponenteProducto) listaComponenteProductoCombo.getSelectedObject();
        ProcedimientoCompra procedimientoCompra = (ProcedimientoCompra) listaProcedimientoCompraCombo.getSelectedObject();
        adquisicion.setAdqFuente(fuente);
        adquisicion.setAdqComponenteProducto(componenteProducto);
        adquisicion.setAdqProcedimientoCompra(procedimientoCompra);
        adquisicion.setAdqTipoRegistro(listaTipoRegistroCompraCombo.getSelectedT());
        adquisicion.setAdqTipoAdquisicion(listaTipoAdquisicionCombo.getSelectedT());
        adquisicion.setAdqCentroCosto(listaCentroCostoCombo.getSelectedT());
        adquisicion.setAdqCausalCompra(listaCausalCompraCombo.getSelectedT());
        adquisicion.setAdqIdGrpErpFk(listaIdentificadorGrpErpCombo.getSelectedT());

        if (!adquisicion.getAdqCompartida()) {
            adquisicion.setSsUsuarioCompartida(null);
        } else {
            if (ssUsuarioCompartidaId != null) {
                adquisicion.setSsUsuarioCompartida(ssUsuarioDelegate.obtenerSsUsuarioPorId(ssUsuarioCompartidaId));
            }
        }

        Moneda moneda = (Moneda) listaMonedaCombo.getSelectedObject();
        adquisicion.setAdqMoneda(moneda);
        if (fichaTO.getPreFk() != null) {
            adquisicion.setAdqPreFk(fichaTO.getPreFk());
        }

        try {
            Integer orgPk = inicioMB.getOrganismo().getOrgPk();
            Boolean exigeProveedorEnCompra = false;
            Configuracion cnfExigeProveedorEnCompra = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PROVEEDOR_ES_EXIGIDO_EN_COMPRA, orgPk);
            if (cnfExigeProveedorEnCompra != null && cnfExigeProveedorEnCompra.getCnfValor().equalsIgnoreCase("true")) {
                exigeProveedorEnCompra = true;
            }

            FichaAdquisicionValidacion.validar(adquisicion, exigeProveedorEnCompra, existeFuenteProcedimientoCompra(), camposExigidosEnAdquisicion(), largoMaximoIdAdquisicion);
            adquisicion = adquisicionDelegate.guardarAdquisicion(adquisicion, fichaTO.getFichaFk(), fichaTO.getTipoFicha(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
            JSFUtils.agregarMsg("formAdqMsg", "info_adquisicion_guardada", null);

            actualizarFichaTO(null);
            limpiarAdquisicion();
            cargarFramePresupuestos(false);
            cerrarFormCollapsable();
            formPresupuestoRendered = 0;

        } catch (BusinessException | TechnicalException w) {
            /*
                    *  18-06-2018 Inspección de código.
             */

            //List<String> errores = w.getErrores();
            //JSFUtils.agregarMsgs("formAdqMsg", errores);
            for (String iterStr : w.getErrores()) {
                JSFUtils.agregarMsgError("formAdqMsg", Labels.getValue(iterStr), null);
            }

        }
        return null;
    }

    public void adquisicionCompartidaValueChange(ValueChangeEvent ev) {
        this.adquisicion.setAdqCompartida((Boolean) ev.getNewValue());
    }

    public void adquisicionArrastreValueChange(ValueChangeEvent ev) {
        this.adquisicion.setAdqArrastre((Boolean) ev.getNewValue());
    }

    public String agregarPagoAction() {
        Entregables ent = (Entregables) listaEntregablesCombo.getSelectedObject();
        OrganiIntProve orga = (OrganiIntProve) listaOrganizacionCombo.getSelectedObject();
        OrganiIntProve prov = listaProveedoresPagoCombo.getSelectedT();
        
        pagos.setEntregables(ent);
        pagos.setPagContrOrganizacionFk(orga);
        pagos.setPagProveedorFk(prov);

        if (orga == null) {
            pagos.setPagContrPorcentaje(null);
        }
        try {
            Integer orgPk = inicioMB.getOrganismo().getOrgPk();
            Boolean exigeProveedorEnPago = false;
            Configuracion cnfExigeProveedorEnPago = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PROVEEDOR_ES_EXIGIDO_EN_PAGO, orgPk);
            if (cnfExigeProveedorEnPago != null && cnfExigeProveedorEnPago.getCnfValor().equalsIgnoreCase("true")) {
                exigeProveedorEnPago = true;
            }
            
            Boolean exigeClienteEnPago = false;
            Configuracion cnfExigeClienteEnPago = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.CLIENTE_ES_EXIGIDO_EN_PAGO, orgPk);
            if (cnfExigeClienteEnPago != null && cnfExigeClienteEnPago.getCnfValor().equalsIgnoreCase("true")) {
                exigeClienteEnPago = true;
            }

            boolean esEstadoEjecucion = fichaTO.getEstado().isEstado(Estados.ESTADOS.EJECUCION.estado_id)
                    || fichaTO.getEstado().isEstado(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOF.estado_id)
                    || fichaTO.getEstado().isEstado(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOT.estado_id);
            
            FichaPagoValidacion.validar(pagos, esEstadoEjecucion, exigeProveedorEnPago, exigeClienteEnPago);            
            
            if (esEstadoEjecucion && !edicionPago) {
                pagos.setPagFechaPlanificada(new Date());
                pagos.setPagImportePlanificado((double) 0);
            }
            Integer progPk = fichaTO.getProgFk() != null ? fichaTO.getProgFk().getProgPk() : null;
            Integer proyPk = fichaTO.getTipoFicha().equals(TipoFichaEnum.PROYECTO.id) ? fichaTO.getFichaFk() : null;
            pagosDelegate.guardarPago(pagos, proyPk, progPk, inicioMB.getOrganismo().getOrgPk());
            JSFUtils.agregarMsg("formPagoMsg", "info_pago_guardada", null);

            /*
                *   20-03-18 Nico: Después de ingresado el Pago, si esta en edición lo cambio a false.
             */
            if (edicionPago) {
                edicionPago = false;
            }
            actualizarFichaTO(null);
            limpiarAdquisicion();
            cargarFramePresupuestos(false);
            cerrarFormCollapsable();
            formPresupuestoRendered = 0;

        } catch (BusinessException be) {
            logger.log(Level.SEVERE, be.getMessage(), be);
            
            for (String iterStr : be.getErrores()) {
                JSFUtils.agregarMsgError("formPagoMsg", Labels.getValue(iterStr), null);
            }

        }

        return null;
    }

    public void pagosGastoChange(AjaxBehaviorEvent ev) {
        if (this.pagos.getPagGasto() != null) {
            if (this.pagos.getPagGasto() < 0) {
                this.pagos.setPagGasto(0);
            }
            if (this.pagos.getPagGasto() > 100) {
                this.pagos.setPagGasto(100);
            }
        } else {
            this.pagos.setPagGasto(0);
        }
        this.pagos.setPagInversion(100 - this.pagos.getPagGasto());
    }

    public void copiarPagoAction(Integer pagPk) {
        try {
            Pagos pago = pagosDelegate.obtenerPagosPorId(pagPk);
            Pagos pagoCopia = pago.clone();
            Integer progPk = fichaTO.getProgFk() != null ? fichaTO.getProgFk().getProgPk() : null;
            Integer proyPk = fichaTO.getTipoFicha().equals(TipoFichaEnum.PROYECTO.id) ? fichaTO.getFichaFk() : null;

            /*
                        *   16-07-2018 Nico: Se chequea en este punto si se esta en fase de planificación o ejecución para poder aplicar el cambio relacionado
                        *       a copiar solamente la fecha planificada más un mes, en caso de estar en fase de planificación, o copiar solamente la fecha real 
                        *       más un mes, en caso de estar en fase de ejecución.
             */
            if (!fichaTO.getEstado().isEstado(Estados.ESTADOS.EJECUCION.estado_id)) {
                pagoCopia.setPagFechaReal(null);
                pagoCopia.setPagImporteReal(null);

                //Corro un mes la fecha planificada
                GregorianCalendar calAux = new GregorianCalendar();
                calAux.setTime(pagoCopia.getPagFechaPlanificada());
                calAux.add(Calendar.MONTH, 1);

                pagoCopia.setPagFechaPlanificada(calAux.getTime());
            } else {
                // Pongo el monto planificado en 0 y la fecha panificada igual a la real
                GregorianCalendar calAux = new GregorianCalendar();
                calAux.setTime(pagoCopia.getPagFechaReal());
                calAux.add(Calendar.MONTH, 1);

                pagoCopia.setPagFechaPlanificada(calAux.getTime());
                pagoCopia.setPagImportePlanificado(0d);

                pagoCopia.setPagFechaReal(calAux.getTime());
            }

            pagosDelegate.guardarPago(pagoCopia, proyPk, progPk, inicioMB.getOrganismo().getOrgPk());
            JSFUtils.agregarMsg("formPagoMsg", "info_pago_guardada", null);

            actualizarFichaTO(null);
            limpiarAdquisicion();
            cargarFramePresupuestos(false);
            cerrarFormCollapsable();
            formPresupuestoRendered = 0;
        } catch (GeneralException ge) {
            /*
                    *  18-06-2018 Inspección de código.
             */

            //JSFUtils.agregarMsgs("", ge.getErrores());
            for (String iterStr : ge.getErrores()) {
                JSFUtils.agregarMsgError("", Labels.getValue(iterStr), null);
            }

            inicioMB.abrirPopupMensajes();
        }
    }

    public String verFormPresupuestoAction(Integer prePk) {
        if (prePk != null) {
            presupuesto = presupuestoDelegate.obtenerPresupuestoPorId(prePk);
            if (presupuesto.getPreMoneda() != null) {
                listaMonedaCombo.setSelected(presupuesto.getPreMoneda().getMonPk());
            }
            if (presupuesto.getFuenteFinanciamiento() != null) {
                listaFuentesCombo.setSelected(presupuesto.getFuenteFinanciamiento().getFuePk());
            }
        }
        formPresupuestoRendered = 1;
        return null;
    }

    public String verFormAdquisicionAction(Integer adqPk) {

        cargarComboFuentes();

        if (adqPk != null) {
            adquisicion = adquisicionDelegate.obtenerAdquisicionPorId(adqPk);
            procedimientoCompraSelected = adquisicion.getAdqProcedimientoCompra(); // utilizada en actualizarComboCausales()
            fuenteFinanciamientoSelected = adquisicion.getAdqFuente(); // utilizada en actualizarComboCausales()
            actualizarComboCausales();

            listaMonedaCombo.setSelected(adquisicion.getAdqMoneda().getMonPk());

            addItemSofisCombo(listaFuentesCombo, adquisicion.getAdqFuente(), adquisicion.getAdqFuente().getFuePk(), true);
               
            listaFuentesCombo.setSelected(adquisicion.getAdqFuente().getFuePk());

            if (adquisicion.getAdqProcedimientoCompra() != null) {
                /*
                            * 06-04-18 Nico: Se controla si esta en la lista el procedimiento compra de la adquisicion, sino esta
                            *           se agrega.
                 */

                if (listaProcedimientoCompraCombo.getObjectById(adquisicion.getAdqProcedimientoCompra().getProcCompPk()) == null) {
                    listaProcedimientoCompraCombo.add(adquisicion.getAdqProcedimientoCompra());
                }

                listaProcedimientoCompraCombo.setSelected(adquisicion.getAdqProcedimientoCompra().getProcCompPk());
            }

            SsUsuario auxUsuCompartida = adquisicion.getSsUsuarioCompartida();

            if (auxUsuCompartida != null) {
                ssUsuarioCompartidaId = adquisicion.getSsUsuarioCompartida().hashCode();
            }

            if (adquisicion.getAdqComponenteProducto() != null) {
                listaComponenteProductoCombo.setSelected(adquisicion.getAdqComponenteProducto().getComPk());
            } else {
                listaComponenteProductoCombo.setSelected(-1);
            }

            if (adquisicion.getAdqTipoRegistro() != null) {
                listaTipoRegistroCompraCombo.setSelectedT(adquisicion.getAdqTipoRegistro());
            } // no se hace un set -1 en combo porque al cerrar popup se limpia con otro action

            if (adquisicion.getAdqTipoAdquisicion() != null) {
                listaTipoAdquisicionCombo.setSelectedT(adquisicion.getAdqTipoAdquisicion());
            }
            if (adquisicion.getAdqCentroCosto() != null) {
                listaCentroCostoCombo.setSelectedT(adquisicion.getAdqCentroCosto());
            }
            if (adquisicion.getAdqCausalCompra() != null) {
                listaCausalCompraCombo.setSelectedT(adquisicion.getAdqCausalCompra());
            }
            if (adquisicion.getAdqIdGrpErpFk() != null) {
                listaIdentificadorGrpErpCombo.setSelectedT(adquisicion.getAdqIdGrpErpFk());
            }

        } else { // si es null, es nueva adquisición
            listaTipoRegistroCompraCombo.setSelectedT(TipoRegistroCompra.COMPRA_ORIGINAL); // seteo default

        }

        cargarComboProveedores();
        cargarComboClientes();

        renderedFormAdquisicionAction("editarAdquisicion");

        return null;
    }

    private void cargarComboFuentes() {
        
        Map<String, Object> filtroFuentes = new HashMap<>();
        filtroFuentes.put("habilitada", true);

        listaFuentes = fuenteFinanciamientoDelegate.busquedaFuenteFiltro(inicioMB.getOrganismo().getOrgPk(), filtroFuentes, "fueNombre", 1);
       
        if (listaFuentes != null && !listaFuentes.isEmpty()) {
            listaFuentesCombo = new SofisCombo((List) listaFuentes, "fueNombre");
            listaFuentesCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }
    }
    
    /**
     * *
     * Cargar el combo para actualizar la lista de proveedores.
     */
    private void cargarComboProveedores() {

        // Obtengo la lista de Proveedores
        List<OrganiIntProve> listaProveedores = new ArrayList<>(organiIntProveDelegate
                .obtenerOrganiIntProvePorOrgPk(inicioMB.getOrganismo().getOrgPk(), true));

        OrganiIntProve organiSelected = null;
        if (adquisicion.getAdqProvOrga() != null) {

            // Obtengo el OrganiIntProve de la fk
            organiSelected = organiIntProveDelegate.obtenerOrganiIntProvePorId(adquisicion.getAdqProvOrga().getOrgaPk());

            if (organiSelected != null && !listaProveedores.contains(organiSelected)) {

                // Si no estaba en la lista lo agrego
                listaProveedores.add(organiSelected);
            }
        }

        // Ordeno la lista y vuelvo a crear el SofisCombo
        listaProveedores = OrganiIntProveUtils.filtrarHabilitadosYOrdenarPorNombre(listaProveedores, organiSelected);
        
        listaProveedoresCombo = new SofisCombo((List) listaProveedores, "orgaNombre");
        listaProveedoresCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

        if (organiSelected != null) {
            
            listaProveedoresCombo.setSelected(organiSelected.getOrgaPk());
        }
    }

    /**
     * *
     * Cargar el combo para actualizar la lista de proveedores.
     */
    private void cargarComboClientes() {

        List<OrganiIntProve> listaCliente = new ArrayList<>(organiIntProveDelegate
                .obtenerOrganiIntProvePorOrgPk(inicioMB.getOrganismo().getOrgPk(), false));

        OrganiIntProve organiSelected = null;
        if (adquisicion.getAdqProvOrga() != null) {

            organiSelected = organiIntProveDelegate.obtenerOrganiIntProvePorId(adquisicion.getAdqProvOrga().getOrgaPk());

            if (organiSelected != null && !listaCliente.contains(organiSelected)) {

                listaCliente.add(organiSelected);
            }
        }

        listaCliente = OrganiIntProveUtils.filtrarHabilitadosYOrdenarPorNombre(listaCliente, organiSelected);
        
        listaClienteCombo = new SofisCombo((List) listaCliente, "orgaNombre");
        listaClienteCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

        if (organiSelected != null) {
            
            listaClienteCombo.setSelected(organiSelected.getOrgaPk());
        }
    }    
    
    /**
     * *
     * Cargar el combo para actualizar la lista de proveedores de un pago.
     */
    private void cargarComboProveedoresPago() {

        // Obtengo la lista de Proveedores
        List<OrganiIntProve> listaProveedores = new ArrayList<>(organiIntProveDelegate
                .obtenerOrganiIntProvePorOrgPk(inicioMB.getOrganismo().getOrgPk(), true));

        OrganiIntProve organiSelected = null;
        if (pagos.getPagProveedorFk() != null) {

            // Obtengo el OrganiIntProve de la fk
            organiSelected = organiIntProveDelegate.obtenerOrganiIntProvePorId(pagos.getPagProveedorFk().getOrgaPk());

            if (organiSelected != null && !listaProveedores.contains(organiSelected)) {

                // Si no estaba en la lista lo agrego
                listaProveedores.add(organiSelected);
            }
        }

        // Ordeno la lista y vuelvo a crear el SofisCombo
        listaProveedores = OrganiIntProveUtils.filtrarHabilitadosYOrdenarPorNombre(listaProveedores, organiSelected);
        
        // Actualizo los Proveedores del pago
        listaProveedoresPagoCombo = new SofisComboG(listaProveedores, "orgaNombre");
        listaProveedoresPagoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        
        if (organiSelected != null) {
            
            listaProveedoresPagoCombo.setSelected(organiSelected.getOrgaPk());
        }
    }

    public void confirmarPago(Integer pagPk) {
        pagoConfirmar = pagPk;
    }
    
    public String confirmarPago() {
        Pagos pago = pagosDelegate.obtenerPagosPorId(pagoConfirmar);
        if (pago != null) {
            if (pago.isPagConfirmado()) {
                if (SsUsuariosUtils.isUsuarioPMO(fichaTO, inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk())) {
                    pago.setPagConfirmar(Boolean.FALSE);
                }
            } else {
                pago.setPagConfirmar(Boolean.TRUE);
            }

            Integer progPk = fichaTO.getProgFk() != null ? fichaTO.getProgFk().getProgPk() : null;
            Integer proyPk = fichaTO.getTipoFicha().equals(TipoFichaEnum.PROYECTO.id) ? fichaTO.getFichaFk() : null;
            try {
                pagosDelegate.guardarPago(pago, proyPk, progPk, inicioMB.getOrganismo().getOrgPk());
                actualizarFichaTO(null);
                cargarFramePresupuestos(false);
            } catch (BusinessException be) {
                logger.log(Level.SEVERE, be.getMessage(), be);
                /*
                                 *  18-06-2018 Inspección de código.
                 */

                //JSFUtils.agregarMsgs(FICHA_MSG_ID, be.getErrores());
                for (String iterStr : be.getErrores()) {
                    JSFUtils.agregarMsgError(FICHA_MSG_ID, Labels.getValue(iterStr), null);
                }

                inicioMB.setRenderPopupMensajes(true);
            }
        }
        return null;
    }

    public String verFormPagoAction(Integer pagPk, Integer adqPk) {

        cargarPago(pagPk, adqPk);
        
        formPresupuestoRendered = 3;
        setFocus("comboEntregablesPag");
        return null;
    }

    public String verFormPagoReadOnlyAction(Integer pagPk, Integer adqPk) {

        cargarPago(pagPk, adqPk);
        pagosGastoChange(null);
        
        if (pagos.getEntregables() != null) {
            EntregablesUtils.cargarCamposEntregable(pagos.getEntregables());
        }

        formPresupuestoRendered = 4;
        return null;
    }

    private void cargarPago(Integer pagPk, Integer adqPk) {
        if (pagPk != null && pagPk != 0) {
            pagos = pagosDelegate.obtenerPagosPorId(pagPk);
            if (pagos.getEntregables() != null) {
                listaEntregablesCombo.setSelected(pagos.getEntregables().getEntPk());
            }

            cargarComboProveedoresPago();
            cargarComboOrganizaciones();

            edicionPago = true;
        } else {
            Adquisicion adq = adquisicionDelegate.obtenerAdquisicionPorId(adqPk);
            pagos = new Pagos();
            pagos.setPagAdqFk(adq);
            pagos.setEntregables(new Entregables());

            cargarComboProveedoresPago();

            // asocio al nuevo pago el proveedor de la adquisición
            OrganiIntProve prov = adq.getAdqProvOrga();
            if (prov != null) {
                listaProveedoresPagoCombo.setSelectedT(prov);
            }
            // ACA VA LO DE AGREGAR GASTO Y CLIENTE DE UNA ADQUISICIÓN                       
            Pagos pagoFinal = adquisicionDelegate.obtenerUltimoPago(adqPk);

            if ((pagoFinal == null) || (pagoFinal.getPagGasto() == null)) {
                this.pagos.setPagGasto(0);
                pagosGastoChange(null);

                cargarComboOrganizaciones(null);

            } else {
                this.pagos.setPagGasto(pagoFinal.getPagGasto());
                
                cargarComboOrganizaciones((pagoFinal.getPagContrOrganizacionFk() != null) 
                        ? pagoFinal.getPagContrOrganizacionFk() : null);
            }

            pagosGastoChange(null);

            edicionPago = false;
        }
    }

    /**
     * *
     * Carga el combo para actualizar las organizaciones que no son proveedores.
     */
    private void cargarComboOrganizaciones() {
                
        OrganiIntProve organiSelected = null;
        if (pagos.getPagContrOrganizacionFk() != null) {

            organiSelected = organiIntProveDelegate.obtenerOrganiIntProvePorId(pagos.getPagContrOrganizacionFk().getOrgaPk());
        }

        cargarComboOrganizaciones(organiSelected);
    }

    private void cargarComboOrganizaciones(OrganiIntProve organiSelected) {

        List<OrganiIntProve> listaOrganizacion = new ArrayList<>(organiIntProveDelegate
                .obtenerOrganiIntProvePorOrgPk(inicioMB.getOrganismo().getOrgPk(), false));

        if (organiSelected != null && !listaOrganizacion.contains(organiSelected)) {

            listaOrganizacion.add(organiSelected);
        }
        
        listaOrganizacion = OrganiIntProveUtils.filtrarHabilitadosYOrdenarPorNombre(listaOrganizacion, organiSelected);
        
        listaOrganizacionCombo = new SofisCombo((List) listaOrganizacion, "orgaNombre");
        listaOrganizacionCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

        if (organiSelected != null) {
            
            listaOrganizacionCombo.setSelected(organiSelected.getOrgaPk());
        }
    }
    
    public void eliminarAdquisicionAction(Integer adqPk) {
        adqEliminar = adqPk;
    }
    
    public String eliminarAdquisicionAction() {
        logger.log(Level.FINE, "Eliminar Adquisición.");
        try {
            adquisicionDelegate.eliminarAdquisicion(adqEliminar, fichaTO.getFichaFk(), fichaTO.getOrgFk().getOrgPk());
            actualizarFichaTO(null);
            cargarFramePresupuestos(false);

        } catch (BusinessException be) {
            /*
                     *  18-06-2018 Inspección de código.
             */

            //JSFUtils.agregarMsgs("", be.getErrores());
            for (String iterStr : be.getErrores()) {
                JSFUtils.agregarMsgError("", Labels.getValue(iterStr), null);
            }

            inicioMB.abrirPopupMensajes();
        }
        return null;
    }
    
    public void eliminarPagoAction(Integer pagPk) {
        pagoEliminar = pagPk;
    }

    public String eliminarPagoAction() {
        try {
            pagosDelegate.eliminarPagoActInd(pagoEliminar, fichaTO.getFichaFk(), fichaTO.getOrgFk().getOrgPk());
            actualizarFichaTO(null);
            cargarFramePresupuestos(false);
        } catch (GeneralException ge) {
            JSFUtils.agregarMsgsErrores("", Labels.getValues(ge.getErrores()));
            JSFUtils.agregarMsgError("", "Verifique si hay documentos asociados al pago que se desea eliminar", null);
            inicioMB.abrirPopupMensajes();
        }

        return null;
    }

    public void limpiarPresupuesto() {
        presupuesto = new Presupuesto();
        listaMonedaCombo.setSelected(-1);
        listaFuentesCombo.setSelected(-1);
        listaComponenteProductoCombo.setSelected(-1);
    }

    public void limpiarAdquisicion() {
        /*
            * 16-04-18 Nico: Me fijo si el procedimiento de compra que queda esta habilitado o no, asi lo saco de la lista.
         */
        if ((adquisicion != null) && (adquisicion.getAdqProcedimientoCompra() != null) && (!(adquisicion.getAdqProcedimientoCompra().getProcCompHabilitado()))) {
            listaProcedimientoCompraCombo.remove(adquisicion.getAdqProcedimientoCompra());
        }

        adquisicion = new Adquisicion();
        listaOrganizacionCombo.setSelected(-1);
        listaProveedoresCombo.setSelected(-1);
        listaFuentesCombo.setSelected(-1);
        listaMonedaCombo.setSelected(-1);
        listaComponenteProductoCombo.setSelected(-1);
        listaProcedimientoCompraCombo.setSelected(-1);
        listaTipoRegistroCompraCombo.setSelected(-1);
        listaTipoAdquisicionCombo.setSelected(-1);
        listaCentroCostoCombo.setSelected(-1);
        listaCausalCompraCombo.setSelected(-1);
        listaIdentificadorGrpErpCombo.setSelected(-1);
        ssUsuarioCompartidaId = -1;
        procedimientoCompraSelected = null;
        fuenteFinanciamientoSelected = null;

    }

    public void limpiarPago() {
        pagos = new Pagos();
        listaEntregablesCombo.setSelected(-1);
        listaProveedoresPagoCombo.setSelected(-1);
    }

    public String renderedFormPresupuestoAction() {
        formPresupuestoRendered = 1;
        return null;
    }

    public String renderedFormAdquisicionAction(String renderStr) {
        boolean isRenderAdq = renderStr.equalsIgnoreCase("editarAdquisicion") || renderStr.equalsIgnoreCase("agregarAdquisicion");
        if (isRenderAdq
                && (renderStr.equalsIgnoreCase("editarAdquisicion") && this.fieldRendered("editarAdquisicion")
                || (renderStr.equalsIgnoreCase("agregarAdquisicion") && this.fieldRendered("agregarAdquisicion")
                && (fichaTO.isEstado(Estados.ESTADOS.INICIO.estado_id) || fichaTO.isEstado(Estados.ESTADOS.FINALIZADO.estado_id))))) {
            formPresupuestoRendered = 2;
        }
        return null;
    }

    public String renderedFormPagoAction() {
        formPresupuestoRendered = 3;
        return null;
    }

    public void cerrarFormPreCollapsable() {
        formPresupuestoRendered = 0;
        limpiarPresupuesto();
        limpiarAdquisicion();
        limpiarRiesgo();
        limpiarInteresado();
        limpiarMedia();
        limpiarProducto();
        limpiarDocumento();
        limpiarPago();
    }

    public void cargarFramePresupuestos(boolean recargarCombos) {
        if (fichaTO.hasPk()) {
            if (recargarCombos) {

                listaMonedas = monedaDelegate.obtenerMonedas();
                if (listaMonedas != null && !listaMonedas.isEmpty()) {
                    listaMonedaCombo = new SofisCombo((List) listaMonedas, "monSigno");
                    listaMonedaCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
                }

                //List<OrganiIntProve> listaOrganizacion = organiIntProveDelegate.obtenerOrganiIntProvePorOrgPk(inicioMB.getOrganismo().getOrgPk(), Boolean.TRUE);
                /*
                        *       2018-09-18 RQ-6 Release 5.3.6 : Se piden todos los OrganiIntProveedores, no solo los que son Proveedores para los Pagos.
                        *   En el caso de las Adquisiciones, se mantienen los Proveedores.
                 */
                List<OrganiIntProve> listaProveedores = aplicacionMB.obtenerOrganiIntProveedores(inicioMB.getOrganismo().getOrgPk());
                if (listaProveedores != null && !listaProveedores.isEmpty()) {
                    listaProveedores = OrganiIntProveUtils.sortByNombre(listaProveedores);
                    listaProveedoresCombo = new SofisCombo((List) listaProveedores, "orgaNombre");
                    listaProveedoresCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
                    // para los del pago
                    listaProveedoresPagoCombo = new SofisComboG(listaProveedores, "orgaNombre");
                    listaProveedoresPagoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
                }

                List<OrganiIntProve> listaCliente = aplicacionMB.obtenerOrganiIntNoProveedores(inicioMB.getOrganismo().getOrgPk());//organiIntProveDelegate.obtenerOrganiIntProvePorOrgPk(inicioMB.getOrganismo().getOrgPk(), false);
                if (listaCliente != null && !listaCliente.isEmpty()) {
                    listaCliente = OrganiIntProveUtils.sortByNombre(listaCliente);
                    listaClienteCombo = new SofisCombo((List) listaCliente, "orgaNombre");
                    listaClienteCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
                }

                List<OrganiIntProve> listaOrganizacion = organiIntProveDelegate.obtenerOrganiIntProvePorOrgPk(inicioMB.getOrganismo().getOrgPk(), false);
                if (listaOrganizacion != null && !listaOrganizacion.isEmpty()) {
                    listaOrganizacion = OrganiIntProveUtils.sortByNombre(listaOrganizacion);
                    listaOrganizacionCombo = new SofisCombo((List) listaOrganizacion, "orgaNombre");
                    listaOrganizacionCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
                }

                listaComponenteProducto = componenteProductoDelegate.obtenerComponentesProductosPorOrgId(inicioMB.getOrganismo().getOrgPk());
                if (listaComponenteProducto != null && !listaComponenteProducto.isEmpty()) {
                    listaComponenteProductoCombo = new SofisCombo((List) listaComponenteProducto, "comNombre");
                    listaComponenteProductoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
                }

                if (fichaTO.getCroFk() != null && fichaTO.getCroFk().getEntregablesSet() != null) {
                    listaEntregables = new ArrayList<>(fichaTO.getCroFk().getEntregablesSet());
                    listaEntregables = EntregablesUtils.cargarCamposCombos(listaEntregables);
                }

                if (listaEntregables != null && !listaEntregables.isEmpty()) {
                    listaEntregablesCombo = new SofisCombo((List) listaEntregables, "fechaNivelNombreCombo");
                    listaEntregablesCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
                }

                if (listaIdentificadorGrpErp != null && !listaIdentificadorGrpErp.isEmpty()) {
                    listaIdentificadorGrpErpCombo = new SofisComboG((List) listaIdentificadorGrpErp, "idGrpErpNombre");
                    listaIdentificadorGrpErpCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
                }
            }

            if (fichaTO.getPreFk() != null && fichaTO.getPreFk().getPrePk() != null) {
                listaAdqPagosFrame = adquisicionDelegate.obtenerAdquisicionPagosList(fichaTO.getPreFk().getPrePk());
            }

            if ((fichaTO.getPreFk() == null || fichaTO.getPreFk().getPrePk() == null)
                    && fieldRendered("agregarPresupuesto")) {
                formPresupuestoRendered = 1;
                preFormDataExpanded = true;
            } else if (fichaTO.getPreFk() != null && listaAdqPagosFrame.isEmpty()) {
//				renderedFormAdquisicionAction("agregarAdquisicion");
                preFormDataExpanded = true;
            }

            if (fichaTO.getTipoFicha().equals(TipoFichaEnum.PROYECTO.id)) {
                listAdqDev = adquisicionDelegate.obtenerAdqDevPorProy(fichaTO.getFichaFk());
                listAdqDev = cargarDevMesAux(listAdqDev);
            }
        }
    }

    public void cargarFrameProductos() {

        producto.setProdPeso(1);
        productosList = productosDelegate.obtenerProdPorProyPk(fichaTO.getFichaFk());
        for (Productos prod : productosList) {
            editarProdMap.put(prod.getProdPk(), false);
            prod = productosDelegate.cargarProdMesAuxiliar(prod, null);
        }

        boolean isGerente = SsUsuariosUtils.isUsuarioGerenteOAdjuntoFicha(fichaTO, inicioMB.getUsuario());
        if (isGerente) {
            if (fichaTO.getCroFk() != null && fichaTO.getCroFk().getEntregablesSet() != null) {
                entregablesListProd = new ArrayList<>(fichaTO.getCroFk().getEntregablesSet());
                entregablesListProd = EntregablesUtils.entregablesSinPadres(entregablesListProd);
                entregablesListProd = EntregablesUtils.entregablesSinHitos(entregablesListProd);
            }
        } else if (isCoordAlgunEntregable()) {
            entregablesListProd = entregablesDelegate.obtenerEntPorCoord(fichaTO.getFichaFk(), inicioMB.getUsuario().getUsuId(), Boolean.FALSE);
        }

        if (entregablesListProd != null && !entregablesListProd.isEmpty()) {
            entregablesListProd = EntregablesUtils.cargarCamposCombos(entregablesListProd);
            listaEntProdCombo = new SofisCombo((List) entregablesListProd, "fechaNivelNombreCombo");
            listaEntProdCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

    }

    public String guardarProducto(Productos prod) {
        if (prod != null) {
            try {
                prod = productosDelegate.guardarProducto(prod, true);
                if (prod != null) {
                    JSFUtils.agregarMsg("productosMsg", "info_producto_guardado", null);
                    editarProducto(prod);
                    cargarFrameCronograma(true);
                    cargarResumenCronograma();
                }
            } catch (BusinessException be) {
                logger.log(Level.SEVERE, be.getMessage());
                JSFUtils.agregarMsgError(PROD_MSG_ID, Labels.getValue(be.getMessage()), null);
                //JSFUtils.agregarMsgs(PROD_MSG_ID, be.getErrores());
                inicioMB.setRenderPopupMensajes(true);
                return null;
            }
        }
        return null;
    }

    public String calcularAcumuladoProd(Productos prod) {
        prod = productosDelegate.calcularAcumulados(prod);
        return null;
    }

    public String retrocederAnioProdMes(Productos prod) {
        prod.setAnio(prod.getAnio() - 1);
        prod = productosDelegate.cargarProdMesAuxiliar(prod, prod.getAnio());

        return null;
    }

    public String avanzarAnioProdMes(Productos prod) {
        prod.setAnio(prod.getAnio() + 1);
        prod = productosDelegate.cargarProdMesAuxiliar(prod, prod.getAnio());
        return null;
    }

    public String getProdMesAcuRealColor(ProdMes prodMes) {
        return productosDelegate.prodMesAcuRealColor(prodMes, inicioMB.getOrganismo().getOrgPk(), limiteAmarilloProd, limiteRojoProd);
    }

    public String abrirProductoAction() {
        limpiarProducto();
        prodFormDataExpanded = true;
        return null;
    }

    public String editarProducto(Productos prod) {
        if (!editarProdMap.containsKey(prod.getProdPk())
                || editarProdMap.get(prod.getProdPk())) {
            editarProdMap.put(prod.getProdPk(), false);
        } else {
            editarProdMap.put(prod.getProdPk(), true);
        }

        return null;
    }

    public String eliminarProducto(Productos prod) {
        try {
            productosDelegate.eliminarProducto(prod.getProdPk());
            JSFUtils.agregarMsg("productosMsg", "info_producto_eliminado", null);
            productosList.remove(prod);
            cargarFrameCronograma(false);
            cargarResumenCronograma();
        } catch (BusinessException be) {
            /*
                     *  18-06-2018 Inspección de código.
             */

            //JSFUtils.agregarMsgs("productosMsg", be.getErrores());
            for (String iterStr : be.getErrores()) {
                JSFUtils.agregarMsgError("productosMsg", Labels.getValue(iterStr), null);
            }

        }
        return null;
    }

    public void limpiarProducto() {
        producto = new Productos();
        producto.setProdPeso(1);
        listaEntProdCombo.setSelected(-1);
    }

    public String verHistoricoSitActAction() {
        renderPopupHistoricoSitAct = true;
        try {
            historicoSitAct = proySitActHistoricoDelegate.obtenerHistoricoSitActTodos(fichaTO.getFichaFk());

        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, null, ex.getMessage());
        }
        return null;
    }

    private void setAreasRestringidasToFichaTO() {
        if (areasRestringidasStateMap != null) {
            List lista = areasRestringidasStateMap.getSelected();
            if (lista != null && !lista.isEmpty()) {
                fichaTO.setAreasRestringidas(new HashSet<Areas>());
            } else {
                fichaTO.setAreasRestringidas(null);
            }
            for (Object object : lista) {
                if (object instanceof DefaultMutableTreeNode) {
                    DefaultMutableTreeNode d = (DefaultMutableTreeNode) object;
                    Areas a = (Areas) d.getUserObject();
                    if (!a.equals(fichaTO.getAreaFk())) {
                        fichaTO.getAreasRestringidas().add(a);
                    }
                }
            }
        }
    }

    /**
     * Retorna las Areas Tematicas seleccionadas.
     *
     * @return Set de AreasTags.
     */
    private Set<AreasTags> getAreasTagsSelected() {
        if (areasTematicasStateMap != null) {
            List lista = areasTematicasStateMap.getSelected();
            Set<AreasTags> result = new HashSet<AreasTags>();

            for (Object object : lista) {
                if (object instanceof DefaultMutableTreeNode) {
                    DefaultMutableTreeNode d = (DefaultMutableTreeNode) object;
                    AreasTags at = (AreasTags) d.getUserObject();
                    result.add(at);
                }
            }
            return result;
        }
        return null;
    }

    private void setAreasTematicasToFichaTO() {
        fichaTO.setAreasTematicas(getAreasTagsSelected());
    }

    public String copiarProyecto() {
        try {
            Proyectos proy = proyectoDelegate.guardarCopiaProyecto(fichaTO.getFichaFk(), nombreProyCopia, fechaComienzoProyCopia, inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
            if (proy != null) {
                JSFUtils.agregarMsg(FICHA_MSG_ID, "info_ficha_copiada", null);
                actualizarFichaTO(proy);
                cargarResumenes();
                cerrarFormCollapsable();
            } else {
                JSFUtils.agregarMsg(FICHA_MSG_ID, "error_ficha_copiar", null);
                inicioMB.abrirPopupMensajes();
            }
            cerrarRenderPopupCopiaProy();
        } catch (BusinessException be) {
            logger.log(Level.SEVERE, be.getMessage(), be);
            /*
                         *  18-06-2018 Inspección de código.
             */

            //JSFUtils.agregarMsgs(FICHA_MSG_ID, be.getErrores());
            for (String iterStr : be.getErrores()) {
                JSFUtils.agregarMsgError(FICHA_MSG_ID, Labels.getValue(iterStr), null);
            }

            inicioMB.abrirPopupMensajes();
        }

        return null;
    }

    //metodos de visualización
    public boolean fieldDisabled(String fieldName, Object param) {
        return fieldAttribute(fieldName, FieldAttributeEnum.DISABLED, param);
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

    /**
     * Retorna un boolean si el fieldName aportado debe ser desplegado en
     * pantalla.
     *
     * @param fieldName
     * @return boolean
     */
    public boolean fieldRendered(String fieldName) {
        return fieldAttribute(fieldName, FieldAttributeEnum.RENDERED);
    }

    public boolean fieldRendered(String fieldName, Object param) {
        return fieldAttribute(fieldName, FieldAttributeEnum.RENDERED, param);
    }

    private boolean fieldAttribute(String fieldName, FieldAttributeEnum field) {
        return fieldAttribute(fieldName, field, null);
    }

    private boolean fieldAttribute(String fieldName, FieldAttributeEnum field, Object param) {
        
        //Validación de campos
        if(fichaTO.getDescripcion() == null || fichaTO.getDescripcion().equals("")){
            fichaTO.setDescripcion("<p></p>");
        }
        if(fichaTO.getObjetivo()== null || fichaTO.getObjetivo().equals("")){
            fichaTO.setObjetivo("<p></p>");
        }
        if(fichaTO.getObjPublico()== null || fichaTO.getObjPublico().equals("")){
            fichaTO.setObjPublico("<p></p>");
        }
        if(fichaTO.getFactorImpacto()== null || fichaTO.getFactorImpacto().equals("")){
            fichaTO.setFactorImpacto("<p></p>");
        }
        if(fichaTO.getSituacionActual()== null || fichaTO.getSituacionActual().equals("")){
            fichaTO.setSituacionActual("<p></p>");
        }

        boolean checkDisabled = field == FieldAttributeEnum.DISABLED;
        boolean checkRendered = field == FieldAttributeEnum.RENDERED;

        Boolean disabled = null;
        Boolean rendered = null;

        Integer orgPk = inicioMB.getOrganismo().getOrgPk();
        SsUsuario usu = inicioMB.getUsuario();

        boolean isDirector = usu.isUsuarioDirector(orgPk);
        boolean isUsuComun = SsUsuariosUtils.isUsuarioComun(fichaTO, usu, orgPk);
        boolean isSponsor = SsUsuariosUtils.isUsuarioSponsorFicha(fichaTO, usu);
        boolean isGerente = SsUsuariosUtils.isUsuarioGerenteFicha(fichaTO, usu);
        boolean isAdjunto = SsUsuariosUtils.isUsuarioAdjuntoFicha(fichaTO, usu);
        boolean isGerenteOAdjunto = isGerente || isAdjunto;

        boolean isAdjModPre = confAdjPre != null && Boolean.parseBoolean(confAdjPre.getCnfValor());
        boolean isGerenteOAdjuntoPre = (isAdjModPre ? isGerenteOAdjunto : isGerente);

        boolean isPMOT = inicioMB.isUsuarioOrgaPMOT();
        boolean isPMOF = SsUsuariosUtils.isUsuarioPMOF(fichaTO, usu, orgPk);
        boolean isEditor = inicioMB.isUsuarioOrgaEditor();
        boolean usuAprobFact = usu.isUsuAprobFact();
        boolean isProg = fichaTO.isPrograma();
        boolean isProy = fichaTO.isProyecto();

        boolean hasEstado = fichaTO.getEstado() != null;
        boolean isAlta = !hasEstado;

        boolean isEstadoPendientes = hasEstado && fichaTO.getEstado().isPendientes();
        boolean isEstadoPendientePMOF = hasEstado && fichaTO.getEstado().isEstado(Estados.ESTADOS.PENDIENTE_PMOF.estado_id);
        boolean isEstadoPendientePMOT = hasEstado && fichaTO.getEstado().isEstado(Estados.ESTADOS.PENDIENTE_PMOT.estado_id);
        boolean isEstadoInicio = fichaTO.isEstado(Estados.ESTADOS.INICIO.estado_id);
        boolean isEstadoPlanificacion = fichaTO.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id);
        boolean isEstadoEjecucion = fichaTO.isEstado(Estados.ESTADOS.EJECUCION.estado_id);
        boolean isEstadoFinalizado = fichaTO.isEstado(Estados.ESTADOS.FINALIZADO.estado_id);
        boolean isEstadoPendCerrar = fichaTO.isEstadoPendiente(Estados.ESTADOS.SOLICITUD_CANCELAR_PMOT.estado_id);

        boolean isEstadoPendReplanif = fichaTO.getEstadoPendiente() != null && fichaTO.getEstadoPendiente().isEstado(Estados.ESTADOS.PLANIFICACION.estado_id) && isEstadoEjecucion;

        boolean isEstadoSolCerrarHaciaPMOF = fichaTO.isEstadoPendiente(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOF.estado_id);
        boolean isEstadoSolCerrarHaciaPMOT = fichaTO.isEstadoPendiente(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOT.estado_id);

        boolean isActivo = fichaTO.getActivo() == null || fichaTO.getActivo();

        boolean deshabilitar = !isActivo
                || (!isAlta && isUsuComun)
                || (isEstadoPendientes && isGerenteOAdjunto && !(isPMOT || isPMOF)
                || (!isAlta && isDirector && !isSponsor && !isGerente && !isAdjunto && !isPMOF));

        boolean deshabilitarFinalizado = deshabilitar || isEstadoFinalizado;

        boolean isProgPaso1_1 = isProg && isAlta;
        boolean isProyPaso1_1 = isProy && isAlta;
        boolean isSolAprobacion = fichaTO.getEstadoPendiente() != null
                && estadosDelegate.isOrdenProcesoMenor(fichaTO.getEstado(), fichaTO.getEstadoPendiente());

        /*
                * 16-03-2018 Nico: Se agrega el atributo "isPrograma" para saber si la Ficha es un programa
         */
        boolean isPrograma = fichaTO.isPrograma();

        /*
                * 16-03-2018 Nico: Se agrega un chequeo para poder desactivar agegar modificar y eliminar documento 
                *       si el usuario no es gerente o adjunto del proyecto, o no es coordinador del documento.
         */
        if (fieldName.equals("btnAgregarDocumento")) {
            if (isEstadoFinalizado || !(isGerente) && !(isAdjunto)) {
                rendered = false;
            }
        }

        // Si es null es porque todavía no se ha replanificado.
        boolean habilitadoReplan = ultimaReplan == null ? true : ultimaReplan.isProyreplanPermitEditar();

//        if (isDirector) {
        if (fieldName.equalsIgnoreCase("AprobarFicha")) {
            //el boton aprobar no aplica si es un alta
            if (deshabilitarFinalizado || isAlta
                    || (isSolAprobacion && isGerenteOAdjunto && !(isPMOT || isPMOF))
                    || (isEstadoPendientes && isGerenteOAdjunto && !(isPMOT || isPMOF))
                    || (isEstadoPendientePMOF && !isPMOF)
                    || (isEstadoPendientePMOT && !isPMOT)
                    || ((isPMOF && !aprobPMOF) && (isEstadoInicio || isEstadoPlanificacion) && !isPMOT && !isGerenteOAdjunto)
                    || !(isGerenteOAdjunto || isPMOT || isPMOF)
                    || (isProg && (isEstadoInicio || isEstadoPlanificacion || isEstadoEjecucion))
                    || (isEstadoSolCerrarHaciaPMOF && !(isPMOF || isPMOT) || isEstadoSolCerrarHaciaPMOT && !isPMOT) // En el caso de que se envie una solicitud de cierre, no se puede retroceder.
                    || isEstadoPendCerrar
                    || (isEstadoPendReplanif) // Se saca el botón para avanzar cuando se tiene una solicitud de replanificación.
                    || this.selectedMostrar != null) {
                rendered = false;
            }
            
            // No se debería mostrar en los programas si progEstFk es pendiente 
            // pero progEstPendienteFk es nulo, porque el que está pendiente es uno de sus proyectos
            if (fichaTO.isPrograma() && fichaTO.getEstado() != null && fichaTO.getEstado().isPendientes() && fichaTO.getEstadoPendiente() == null) {
                rendered = false;
            }
        }
//        }

        if (fieldName.equalsIgnoreCase("retrocederEstadoFicha")) {
            if (deshabilitar
                    || isAlta
                    || (isEstadoPendientes || isEstadoInicio) // || isEstadoFinalizado)
                    || !((isPMOT) || (isPMOF && aprobPMOF) || (isPMOF && !aprobPMOF && isEstadoEjecucion)) // A esta condición se agrega la variable "aprobPMOF" para poder diferenciar el caso en que PMOF tiene permiso para pasar de fase
                    || (isEstadoSolCerrarHaciaPMOF && !(isPMOF || isPMOT) || isEstadoSolCerrarHaciaPMOT && !isPMOT) // En el caso de que se envie una solicitud de cierre, no se puede retroceder.
                    || (!(isPMOT) && isEstadoPendReplanif) // Se controla que solamente el PMOT puede ver las solicitudes de replanificación
                    || this.selectedMostrar != null
                    || isProg) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("GuardarFicha")) {
            if (deshabilitarFinalizado || isEstadoFinalizado
                    || (isEstadoPendientePMOF && !isPMOF)
                    || (isEstadoPendientePMOT && !isPMOT)
                    || this.selectedMostrar != null) {
                rendered = false;
            }
        }

        /*
                * 16-03-2018 Nico: Se chequea el valor de "isPrograma" para sacar el botón de Eliminar
         */
        if (fieldName.equalsIgnoreCase("EliminarFicha")) {
            if (deshabilitar
                    || isAlta || isEstadoFinalizado
                    || (isProg && !isPMOT)
                    || (isProy && !(isPMOT || isPMOF))
                    || this.selectedMostrar != null
                    || isPrograma) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("copiarProyecto")) {
            if (isProg || isAlta || !isPMOT) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("moverProyecto")) {
            if (!Boolean.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.HABILITAR_MOVER_PROYECTO, null).getCnfValor())
                    || isProg || isAlta || !isPMOT
                    || !(isEstadoInicio || isEstadoPlanificacion || isEstadoEjecucion || isEstadoFinalizado)) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("notificaciones")) {
            if (isProg || isAlta) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("notificacionesEditar")) {
            if (deshabilitarFinalizado || isProg || isAlta || !(isPMOT || isPMOF)) {
                disabled = true;
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("Nombre")) {
            if (deshabilitarFinalizado || (!isAlta && !isPMOT)) {
                disabled = true;
            }
        }

        //el tipo de ficha solo en el alta habilitado
        if (fieldName.equalsIgnoreCase("tipoFicha")) {
            if (!isAlta) {
                disabled = true;
            }
        }

        if (fieldName.equalsIgnoreCase("Descripcion")) {
            if (deshabilitarFinalizado || !isAlta && (isEstadoEjecucion || !isGerenteOAdjunto)) {
                disabled = true;
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("Objetivo")) {
            if (deshabilitarFinalizado || !isAlta && (isEstadoEjecucion || !isGerenteOAdjunto)) {
                disabled = true;
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("PubObjetivo")) {
            if (deshabilitarFinalizado || !isAlta && (isEstadoEjecucion || !isGerenteOAdjunto)) {
                disabled = true;
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("FactorImpacto")) {
            if (deshabilitarFinalizado || !isAlta && (isEstadoEjecucion || !isGerenteOAdjunto)) {
                disabled = true;
                rendered = false;
            }
        }

        //campo gerente lo modifica la PMOT o la PMF
        if (fieldName.equalsIgnoreCase("SitActual")) {
            if (deshabilitarFinalizado || isProg || isEstadoPendientes || !(isActivo && isGerenteOAdjunto)) {
                disabled = true;
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("editarOtrosDatos")) {
            if (deshabilitarFinalizado || (!isAlta && !(isGerenteOAdjunto || isEditor))) {
                disabled = true;
            }
        }
        if (fieldName.equalsIgnoreCase("guardarOtrosDatos")) {
            if (deshabilitarFinalizado || (!isAlta && !(isGerenteOAdjunto || isEditor))) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("mapaUbicacion")) {
            if (deshabilitarFinalizado || (!isAlta && !(isGerenteOAdjunto || isEditor))) {
                disabled = true;
            }
        }

        if (fieldName.equalsIgnoreCase("fichaPublicable")) {
            if (deshabilitarFinalizado || (!isAlta && !(isGerenteOAdjunto || isEditor))) {
                disabled = true;
            }
        }

        if (fieldName.equalsIgnoreCase("EtapaPublicacion")) {
            boolean publicable = false;
            if (param != null && param instanceof Boolean) {
                publicable = param != null ? (Boolean) param : false;
            }
            if (publicable && (deshabilitarFinalizado || (!isAlta && !(isGerenteOAdjunto || isEditor)))) {
                disabled = true;
            }
        }

        //campo siempre modificable
        if (fieldName.equalsIgnoreCase("Area")) {
            //lo ingresa el PM en el alta o lo modifica el PMOT
            if (deshabilitarFinalizado || !(isAlta || isPMOT)) {
                disabled = true;
            }
        }

        if (fieldName.equalsIgnoreCase("Programas")
                || fieldName.equalsIgnoreCase("Peso")) {
            if (isProg) {
                rendered = false;
            }
            if (deshabilitarFinalizado || (isPMOF && !isPMOT) || (((isGerenteOAdjunto || isSponsor) && !isPMOT) && !isAlta)) {
                disabled = true;
            }
        }

        //campo gerente lo modifica la PMOT o la PMF
        if (fieldName.equalsIgnoreCase("Gerente")) {
            if (deshabilitarFinalizado
                    || (isAlta && !isPMOT)
                    || !(isPMOT || isPMOF)) {
                disabled = true;
            }
        }

        if (fieldName.equalsIgnoreCase("Adjunto")) {
            if (deshabilitarFinalizado
                    || (!isAlta && (isGerenteOAdjunto && !(isPMOT || isPMOF)))
                    || (!isAlta && !(isPMOT || isPMOF))) {
                disabled = true;
            }
        }

        if (fieldName.equalsIgnoreCase("Sponsor")) {
            //lo ingresa el PM en el alta o lo modifica el PMOT
            if (deshabilitarFinalizado
                    || (!isAlta && ((isGerenteOAdjunto || isSponsor) && !(isPMOT || isPMOF)))) {
                disabled = true;
            }
        }

        //campo pmo federada solo para el PMOT
        if (fieldName.equalsIgnoreCase("PMOFederada")) {
            if (deshabilitar || !(isPMOT || isPMOF)) {
                disabled = true;
                if (isAlta) {
                    rendered = false;
                }
            }
        }

        if (fieldName.equalsIgnoreCase("FichaPresupuesto")) {
            if (deshabilitarFinalizado
                    || isEstadoEjecucion
                    || isAlta
                    || (!isAlta && ((isGerenteOAdjunto || isSponsor) && !(isPMOT || isPMOF)))) {
                disabled = true;
            }

        }

        //mapa ubicacion
        if (fieldName.equalsIgnoreCase("mapaUbicacion")) {
            if ((isAlta && !isPMOT) || isEstadoFinalizado
                    || !(isGerenteOAdjunto || isPMOT || isPMOF)) {
                disabled = true;
            }
        }

        //campo AreaTematica  para el PMOT o el PMOF
        if (fieldName.equalsIgnoreCase("AreaTematica")) {
//            if ((isAlta && !isPMOT) || isEstadoFinalizado
//                    || !(isGerenteOAdjunto || isPMOT || isPMOF)) {
            if ((isAlta && !isPMOT) || isEstadoFinalizado) {
//                disabled = true;
            }
        }

        if (fieldName.equalsIgnoreCase("AreaTematicaTree")) {
            
            if (deshabilitarFinalizado 
                    || !(isPMOT || isPMOF || (isGerenteOAdjunto && gerentesAsignanAreasTematicas))) {
                
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("linksReportes")) {
            if (isAlta) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("resumenEjecutivo")) {
            if (param != null && param instanceof Documentos
                    && ((Documentos) param).getDocsPrivado()
                    && !(isGerenteOAdjunto || isPMOF || isPMOT)) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("PermisosLectura")) {
            if ((isAlta && !isPMOT) || isEstadoFinalizado
                    || (!isAlta && !(isGerenteOAdjunto || isPMOF || isPMOT))) {
                disabled = true;
            }
        }

        if (fieldName.equalsIgnoreCase("PermisosLecturaTree")) {
            if (deshabilitarFinalizado || !(isPMOT || isPMOF)) {
                rendered = false;
            }
        }

        //campo metodologia para el PMOT o el PMOF
        if (fieldName.equalsIgnoreCase("Metodologia")) {
            if (deshabilitarFinalizado || isAlta) {
                disabled = true;
            }
        }

        if (fieldName.equalsIgnoreCase("MetodologiaPopupForm")) {
            if (deshabilitarFinalizado || !(isPMOT || isPMOF)) {
                disabled = true;
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("SemaforoAmarillo")
                || fieldName.equalsIgnoreCase("SemaforoRojo")) {
            if (deshabilitarFinalizado || isAlta || isEstadoFinalizado || ((isGerenteOAdjunto || isSponsor) && !isPMOT)) {
                disabled = true;
            }
        }

        if (fieldName.equalsIgnoreCase("GRP")) {
            if (deshabilitarFinalizado || !(isPMOF || isPMOT)) {
                disabled = true;
            }
        }

        //los paneles no se renderizan si esta el PM dando la alta inicial
        if (fieldName.equalsIgnoreCase("panelInteresados")
                || fieldName.equalsIgnoreCase("panelDocumentos")
                || fieldName.equalsIgnoreCase("panelProductos")
                || fieldName.equalsIgnoreCase("panelPresupuesto")
                || fieldName.equalsIgnoreCase("panelGannt")
                || fieldName.equalsIgnoreCase("panelRiesgos")
                || fieldName.equalsIgnoreCase("panelCalidad")
                || fieldName.equalsIgnoreCase("panelMultimedia")
                || fieldName.equalsIgnoreCase("panelLocalizaciones")) {
            if (!isActivo || (isEstadoPendientes && isGerenteOAdjunto && !(isPMOT || isPMOF))
                    || isProyPaso1_1 || isProgPaso1_1) {
                rendered = false;
            }
        }

        //el programa no tiene estos datos por lo tanto no se renderizan
        if (isProg) {
            if (fieldName.equalsIgnoreCase("SitActual")
                    || fieldName.equalsIgnoreCase("panelRiesgos")
                    || fieldName.equalsIgnoreCase("panelCalidad")
                    || fieldName.equalsIgnoreCase("panelPresupuesto")
                    || fieldName.equalsIgnoreCase("panelGannt")
                    || fieldName.equalsIgnoreCase("panelProductos")
                    || fieldName.equalsIgnoreCase("panelMultimedia")
                    || fieldName.equalsIgnoreCase("panelLocalizaciones")
                    || fieldName.equalsIgnoreCase("panelDocumentos")
                    || fieldName.equalsIgnoreCase("panelInteresados")) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("agregarMultimedia")) {
            if (deshabilitarFinalizado || !(isGerenteOAdjunto || isEditor)) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("editarMultimedia")) {
            if (isEstadoFinalizado || !(isGerenteOAdjunto || isEditor)) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("eliminarMultimedia")) {
            if (isEstadoFinalizado || !(isGerenteOAdjunto)) {
                rendered = false;
            }
        }

        /*
                *  19-03-18 Nico: Acá agrego que si "isGerenteOAdjunto" lo deje editar 
                *           cuando aparezca "estadoDocumento"
         */
        if (fieldName.equalsIgnoreCase("estadoDocumento")) {
            if (!(isPMOF || isPMOT || isGerenteOAdjunto)) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("superarRiesgo")) {
            if (deshabilitarFinalizado || (!isGerenteOAdjunto && !isEstadoFinalizado)) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("agregarDocumento")
                || fieldName.equalsIgnoreCase("editarDocumento")
                || fieldName.equalsIgnoreCase("eliminarDocumento")) {
            if (deshabilitarFinalizado || !isGerenteOAdjunto) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("guardarDocPopup")) {
            if (!(isPMOT || isPMOF)) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("agregarRiesgo")
                || fieldName.equalsIgnoreCase("editarRiesgo")
                || fieldName.equalsIgnoreCase("eliminarRiesgo")) {
            if (isEstadoFinalizado || deshabilitarFinalizado || (!isGerenteOAdjunto && !isEstadoFinalizado)) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("agregarCalidad")
                || fieldName.equalsIgnoreCase("editarCalidad")
                || fieldName.equalsIgnoreCase("eliminarCalidad")) {
            if (deshabilitarFinalizado || !(isGerenteOAdjunto && (isEstadoInicio || isEstadoPlanificacion))) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("agregarInteresado")
                || fieldName.equalsIgnoreCase("editarInteresado")
                || fieldName.equalsIgnoreCase("eliminarInteresado")) {
            if (deshabilitarFinalizado || (!isGerenteOAdjunto && !isEstadoFinalizado)) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("agregarPresupuesto")
                || fieldName.equalsIgnoreCase("editarPresupuesto")
                || fieldName.equalsIgnoreCase("eliminarPresupuesto")) {
            if (deshabilitarFinalizado || !(isPMOT || isPMOF) || isEstadoEjecucion) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("agregarAdquisicion")) {
            /**
             * /15-03-2018 Nico: Se permite agregar Adquisiciones en estado de
             * Ejecución
             */
            //if (deshabilitarFinalizado || !isGerenteOAdjuntoPre || isEstadoEjecucion) {
            if (deshabilitarFinalizado || !isGerenteOAdjuntoPre || (isEstadoPlanificacion && !habilitadoReplan)) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("editarAdquisicion")) {
            if (deshabilitarFinalizado || !isGerenteOAdjuntoPre || (isEstadoEjecucion && !isGerenteOAdjuntoPre) || (isEstadoPlanificacion && !habilitadoReplan)) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("eliminarAdquisicion")) {
            if (deshabilitarFinalizado || !isGerenteOAdjuntoPre || isEstadoEjecucion || (isEstadoPlanificacion && !habilitadoReplan)) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("agregarPago")) {
            /**
             * /25-09-2017 (Bruno): se agrega el requerimiento que permite
             * agregar pagos en ejecución
             */
            //if (deshabilitarFinalizado || !isGerenteOAdjuntoPre || isEstadoEjecucion) {
            if (deshabilitarFinalizado || !isGerenteOAdjuntoPre || (isEstadoPlanificacion && !habilitadoReplan)) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("editarPago")) {

			Boolean confirmado = (param instanceof Boolean) && ((Boolean) param);
            
			if (confirmado || ((deshabilitarFinalizado && !usuAprobFact)
                    || !isGerenteOAdjuntoPre
                    || (isEstadoPlanificacion && !habilitadoReplan))) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("duplicarPago")) {

			if ((deshabilitarFinalizado && !usuAprobFact)
                    || !isGerenteOAdjuntoPre
                    || (isEstadoPlanificacion && !habilitadoReplan)) {
                rendered = false;
            }
        }
		
        if (fieldName.equalsIgnoreCase("eliminarPago")) {
            /**
             * 25-09-2017 (Bruno): se agrega el requerimiento que permite
             * agregar pagos en ejecución
             */
            //if (deshabilitarFinalizado || !isGerenteOAdjuntoPre || (isEstadoEjecucion)) {
            if (deshabilitarFinalizado || !isGerenteOAdjuntoPre || (isEstadoPlanificacion && !habilitadoReplan)) {
                rendered = false;
            }
            
            if (param != null) {
                // En fase de “Ejecución” sólo debe aparecer el botón “eliminar pago” en aquellos pagos que tengan importe planificado = 0
                Double importePlanificado = pagosDelegate.getImportePlanificado((Integer)param);
                if (fichaTO.getEstado().getEstCodigo().equals(EstadosCodigos.EJECUCION) && importePlanificado != 0.0) {
                    rendered = false;
                }
            }
        }

        if (fieldName.equalsIgnoreCase("confirmarPagoCol")) {
            if (!(isGerenteOAdjuntoPre || isPMOF || isPMOT || usuAprobFact)) {
//                      rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("confirmarPagoBtn")) {
            if (param instanceof Boolean) {
                boolean confirmado = (boolean) param;
                if (confirmado && !(isPMOF || isPMOT)) {
                    rendered = false;
                }
                if ((!confirmado && !(isGerenteOAdjuntoPre || usuAprobFact)) || !isEstadoEjecucion) {
                    rendered = false;
                }
            }
        }

        if (fieldName.equalsIgnoreCase("prePagosImpPlan")
                || fieldName.equalsIgnoreCase("prePagosFechaPlan")
                || fieldName.equalsIgnoreCase("prePagosEntregables")) {
            /**
             * /25-09-2017 (Bruno): se agrega el requerimiento que permite
             * agregar pagos en ejecución
             */
            if (fieldName.equalsIgnoreCase("prePagosEntregables")) {
                if (deshabilitarFinalizado) {
                    disabled = true;
                }
            } else {
                if (deshabilitarFinalizado || isEstadoEjecucion) {
                    disabled = true;
                }
            }
        }

        if (fieldName.equalsIgnoreCase("prePagosFechaReal")
                || fieldName.equalsIgnoreCase("prePagosImpReal")) {
            disabled = (deshabilitarFinalizado && !usuAprobFact)
                    || isEstadoPlanificacion || isEstadoInicio;
        }

        if (fieldName.equalsIgnoreCase("preAdqNombre")
                || fieldName.equalsIgnoreCase("preAdqMoneda")
                || fieldName.equalsIgnoreCase("preAdqFuente")
                || fieldName.equalsIgnoreCase("preAdqIdGrpErp")) {
            if (deshabilitarFinalizado || isEstadoEjecucion) {
                /**
                 * /15-03-2018 Nico: Se dejan como activados los campos para
                 * editar una Adquisicion en el Estado Ejecución del Proyecto
                 */
                disabled = false;
            }
        }

        if (fieldName.equalsIgnoreCase("agregarDevengado")
                || fieldName.equalsIgnoreCase("eliminarDevengado")) {
            if (deshabilitarFinalizado || isEstadoEjecucion || !isGerenteOAdjuntoPre || (isEstadoPlanificacion && !habilitadoReplan)) {
                rendered = false;
            }
        }
        if (fieldName.equalsIgnoreCase("editarDevengado")) {
            if (deshabilitarFinalizado || !isGerenteOAdjuntoPre || (isEstadoPlanificacion && !habilitadoReplan)) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("agregarProducto")) {
            boolean isCoordAlgunEnt = param != null && param instanceof Boolean ? (Boolean) param : false;
            if (!isActivo || !((isGerenteOAdjunto || isCoordAlgunEnt) && (isEstadoInicio || isEstadoPlanificacion))) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("editarProducto")) {
            boolean isCoordEnt = param != null && param instanceof Boolean ? (Boolean) param : false;
//            if (!isActivo || (!isGerenteOAdjunto || !isCoordEnt)) {
            if (isEstadoFinalizado || !(isActivo && (isGerenteOAdjunto || isCoordEnt))) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("eliminarProducto")) {
            boolean isCoordEnt = param != null && param instanceof Boolean ? (Boolean) param : false;
            if (isEstadoFinalizado || !((isActivo && !isEstadoEjecucion) && (isGerenteOAdjunto || isCoordEnt))) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("replanDesc")) {
            if (!(isPMOF || (isPMOT && fichaTO.getEstadoPendiente() == null))) {
                disabled = true;
            }
        }

        if (fieldName.equalsIgnoreCase("reporteCroHist")) {
            if (!(isPMOF || isPMOT || isGerenteOAdjunto)) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("cronogramaMaximizar")) {
//            if (!(isPMOF || isPMOT || isGerenteOAdjunto)) {
//                rendered = false;
//            }
        }

        if (fieldName.equalsIgnoreCase("presupuestoMaximizar")) {
//            if (!(isPMOF || isPMOT || isGerenteOAdjunto)) {
//                rendered = false;
//            }
        }

        if (fieldName.equalsIgnoreCase("agregarParticipante")
                || fieldName.equalsIgnoreCase("editarParticipante")
                || fieldName.equalsIgnoreCase("eliminarParticipante")) {
            if (deshabilitarFinalizado || !isGerenteOAdjunto) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("partLinkAprobarTodas")
                || fieldName.equalsIgnoreCase("partEliminarHoras")
                || fieldName.equalsIgnoreCase("partGenerarHoras")
                || fieldName.equalsIgnoreCase("partGuardar")) {
            if (!isGerenteOAdjunto) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("partCheckAprobado")) {
            if (!isGerenteOAdjunto) {
                disabled = true;
            }
        }

        if (disabled == null && deshabilitarFinalizado && !(fieldName.equals("retrocederEstadoFicha"))) {
            disabled = true;
        }

//        if(isDirector && !hasEstado && !isSponsor && !isGerente && !isAdjunto && !isPMOF){
//            disabled = true;
//            if(fieldName.equalsIgnoreCase("GuardarFicha")){
//                rendered = false;
//            }
//        }
        //campo siempre modificable
        if (fieldName.equalsIgnoreCase("ObjetivoEstrategico")) {
            //lo ingresa el PM en el alta o lo modifica el PMOT
            if (deshabilitarFinalizado || !(isAlta || isPMOT)) {
                disabled = true;
            }
        }

        if (fieldName.equalsIgnoreCase("agregarLocalizacion")) {
            if (isEstadoFinalizado || !isGerenteOAdjunto) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("editarLocalizacion")) {
            if (isEstadoFinalizado || !isGerenteOAdjunto) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("eliminarLocalizacion")) {
            if (isEstadoFinalizado || !isGerenteOAdjunto) {
                rendered = false;
            }
        }

        if (fieldName.equalsIgnoreCase("preAdqCausal")) {
            rendered = existeFuenteProcedimientoCompra();
            disabled = !isPMOF && !isPMOT && !isGerenteOAdjunto;
        }

        if (fieldName.equalsIgnoreCase("preAdqProveedor")) {
            rendered = true;
            disabled = !isPMOF && !isPMOT && !isGerenteOAdjunto;
        }
        
        if (fieldName.equalsIgnoreCase("preAdqCliente")) {
            rendered = true;
            disabled = !isPMOF && !isPMOT && !isGerenteOAdjunto;
        }        

        if (checkDisabled) {
            return disabled != null ? disabled : false;
        } else if (checkRendered) {
            return rendered != null ? rendered : true;
        } else {
            return false;
        }
    }

    private boolean existeFuenteProcedimientoCompra() {
        Boolean existeFuenteProcedimientoCompra = false;
        if (fuenteFinanciamientoSelected != null && procedimientoCompraSelected != null) {
            existeFuenteProcedimientoCompra = fuenteProcedimientoCompraDelegate.fuenteProcedimientoCompraEstaEnTabla(fuenteFinanciamientoSelected, procedimientoCompraSelected);
        }
        return existeFuenteProcedimientoCompra;
    }

    public boolean isGerenteOAdjuntoFicha() {
        return SsUsuariosUtils.isUsuarioGerenteOAdjuntoFicha(fichaTO, inicioMB.getUsuario());
    }

    public boolean isCoordAlgunEntregable() {
        if (fichaTO.getCroFk() != null
                && CollectionsUtils.isNotEmpty(fichaTO.getCroFk().getEntregablesSet())) {
            Set<Entregables> entSet = fichaTO.getCroFk().getEntregablesSet();

            boolean isEstadoFinalizado = fichaTO.isEstado(Estados.ESTADOS.FINALIZADO.estado_id);

            if (!isEstadoFinalizado) {
                for (Entregables ent : entSet) {
                    if (ent.getCoordinadorUsuFk() != null
                            && ent.getCoordinadorUsuFk().getUsuId().equals(inicioMB.getUsuario().getUsuId())) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }

        return false;
    }

    public boolean isCoordinadorEntregable(Entregables ent) {
        if (ent != null && ent.getCoordinadorUsuFk() != null
                && ent.getCoordinadorUsuFk().getUsuId().equals(inicioMB.getUsuario().getUsuId())) {
            return true;
        }
        return false;
    }

    /**
     * Retorna el nombre de la imagen del estado.
     *
     * @return String
     */
    public String getImgEstado() {
        if (fichaTO.getEstado() == null) {
            return "fase-1.png";
        } else {
            return fichaTO.getEstado().getImgEstado();
        }
    }

    public String getLabelBtnEstAnterior() {
        boolean isPMOF = SsUsuariosUtils.isUsuarioPMOF(fichaTO, inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
        boolean isPMOT = SsUsuariosUtils.isUsuarioPMOT(inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());

        boolean isEstadoSolCerrarHaciaPMOF = fichaTO.isEstadoPendiente(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOF.estado_id);
        boolean isEstadoSolCerrarHaciaPMOT = fichaTO.isEstadoPendiente(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOT.estado_id);

        boolean isEstadoEjecucion = fichaTO.isEstado(Estados.ESTADOS.EJECUCION.estado_id);

        String result = "";
        if (fichaTO.getEstado() != null
                && fichaTO.getEstado().isEstado(Estados.ESTADOS.EJECUCION.estado_id)) {

            /*
                *       Al siguiente if se le agrega la condición para chequear si esta activada la configuración de aprobación de PMOF, y en caso de que sea PMOT
                *   se chequea si es o no PMOF del proyecto.
             */
            if (isPMOT || (isPMOF && aprobPMOF) || (isPMOT && (isEstadoSolCerrarHaciaPMOT || isEstadoSolCerrarHaciaPMOF))) {

                if (fichaTO.getEstadoPendiente() != null && !(isEstadoSolCerrarHaciaPMOT || isEstadoSolCerrarHaciaPMOF)) {
                    result = StringsUtils.concat(result, Labels.getValue("estado_aprobar"), " ");
                }
            } else {
                result = StringsUtils.concat(result, Labels.getValue("estado_solicitar"), " ");
            }
            return StringsUtils.concat(result, Labels.getValue("estado_replanificacion"));
        } else {
            return StringsUtils.concat(result, Labels.getValue("estado_" + (fichaTO.getEstado().getEstPk() - 1)));
        }
    }

    /**
     * Retorna el texto a mostrar en el boton de aprobacion.
     *
     * @return String
     */
    public String getLabelBtnAprobar() {

        boolean isPM = SsUsuariosUtils.isUsuarioGerenteOAdjuntoFicha(fichaTO, inicioMB.getUsuario());

        boolean isPMOF = SsUsuariosUtils.isUsuarioPMOF(fichaTO, inicioMB.getUsuario(),
                inicioMB.getOrganismo().getOrgPk());
                
        boolean isPMOT = inicioMB.isUsuarioOrgaPMOT();

        boolean isEstado = fichaTO.getEstado() != null;

        boolean isPendientePMOT = isEstado
                && fichaTO.getEstado().getEstPk().equals(Estados.ESTADOS.PENDIENTE_PMOT.estado_id);
                
        boolean isPendientePMOF = isEstado
                && fichaTO.getEstado().getEstPk().equals(Estados.ESTADOS.PENDIENTE_PMOF.estado_id);

        boolean isEstadoSolCerrarHaciaPMOF = fichaTO
                .isEstadoPendiente(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOF.estado_id);

        boolean isEstadoSolCerrarHaciaPMOT = fichaTO
                .isEstadoPendiente(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOT.estado_id);

        boolean isEstadoEjecucion = fichaTO.isEstado(Estados.ESTADOS.EJECUCION.estado_id);

        if (isPendientePMOF || isPendientePMOT) {

            return Labels.getValue("estado_aprobar");
        } 

        StringBuffer label = new StringBuffer();
       
        // Estado de solicitud de cierre y aprueban PMOT o PMOF (segun configuracion)
        if ((isEstadoSolCerrarHaciaPMOT && isPMOT)
                || (isEstadoSolCerrarHaciaPMOF && (isPMOT || (isPMOF && aprobPMOF)))) {
            label.append(Labels.getValue("estado_aprobar")).append(" ");
        }
        // PMOT no realiza solicitudes, 
        // PMOF debe solicitar (a menos que la config diga lo contrario)
        // PM debe solicitar
        else if (!isPMOT 
                && ((isPMOF && !aprobPMOF) || (!isPMOF && isPM))) {

            label.append(Labels.getValue("estado_solicitar")).append(" ");
        } 

        if (fichaTO.isEstado(Estados.ESTADOS.INICIO.estado_id)) {
            label.append(Labels.getValue("estado_" + Estados.ESTADOS.PLANIFICACION.estado_id));

        } else if (fichaTO.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id)) {
            label.append(Labels.getValue("estado_" + Estados.ESTADOS.EJECUCION.estado_id));

        } else if (fichaTO.isEstado(Estados.ESTADOS.EJECUCION.estado_id)) {
            label.append(Labels.getValue("estado_cierre"));
        }

        return label.toString();
    }

    public Resource fileResource(Documentos doc) {
        if (doc != null) {
            DocFile df = documentosDelegate.obtenerDocFilePorDocId(doc.getDocsPk());
            String dir = configuracionDelegate.obtenerCnfValorPorCodigo(
                    ConfiguracionCodigos.DOCUMENTOS_DIR,
                    null);
            dir += "/" + fichaTO.getOrgFk().getOrgPk();
            return new SofisResource(df, dir);
        }
        return null;
    }

    public String guardarCronograma() {

        if (dataCron == null || !StringsUtils.isEmpty(dataCron)) {

            try {

                JSONObject json = new JSONObject(dataCron);
                Cronogramas cro = jsonToCronograma(json);

                cro.setCroPermisoEscrituraPadre(false);

                Integer countLevel0 = 0;
                for (Entregables e : cro.getEntregablesSet()) {
                    if (e.getEntNivel() == 0) {
                        countLevel0++;
                    }
                }

                if (countLevel0 > 1) {
                    JSFUtils.agregarMsgError("ganttForm", Labels.getValue("error_cro_guardar_ent_level_0_mult"), null);
                    cargarFrameCronograma(false);
                    return null;
                }

                Entregables e;
                Object[] eSet = cro.getEntregablesSet().toArray();
                for (int i = 0; i < cro.getEntregablesSet().size(); ++i) {
                    e = (Entregables) eSet[i];
                    if (i < cro.getEntregablesSet().size() - 2) {
                        if (e.getEntFinEsHito()) {
                            for (Entregables e2 : cro.getEntregablesSet()) {
                                if (e2.getEntId() == e.getEntId() + 1) {
                                    if (e2.getEntNivel() > e.getEntNivel()) {
                                        JSFUtils.agregarMsgError("ganttForm", Labels.getValue("error_cro_guardar_ent_hito_padre"), null);
                                        cargarFrameCronograma(false);
                                        return null;
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

                cro.setEntregablesSet(new HashSet<>(EntregablesUtils.ajustarFechas(cro.getEntregablesSet())));

                Object progProy = cronogramaDelegate.guardarCronograma(cro, fichaTO.getFichaFk(), fichaTO.getTipoFicha(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());

                if (progProy == null) {
                    JSFUtils.agregarMsgError("ganttForm", "error_cro_guardar", null);
                } else {
                    actualizarFichaTO(progProy);

                    if (this.alAbrirEnTodaLaPantalla && inicioMB.isAlHacerConsultaHistorico()) {
                        cargarFrameCronograma(true);

                        inicioMB.setAlHacerConsultaHistorico(false);
                        this.alAbrirEnTodaLaPantalla = false;
                    } else {
                        cargarFrameCronograma(false);
                    }

                    JSFUtils.agregarMsg("ganttForm", "info_cronograma_guardado", null);
                }

                JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "setUnchangeGantt();");

            } catch (JSONException ex) {
                logger.log(Level.SEVERE, null, ex);
                JSFUtils.agregarMsgError("ganttForm", "error_cro_guardar", null);
            } catch (BusinessException be) {
                for (String err : be.getErrores()) {
                    if (err.startsWith("error_entregables")) {
                        JSFUtils.agregarMsgError("ganttForm", Labels.getValue(err), null);
                    } else {
                        JSFUtils.agregarMsgError("ganttForm", err, null);
                    }
                }
                cargarFrameCronograma(false);
            }
        }
        return null;
    }

    /**
     * Convierte un objeto JSONObject en un Cronogramas
     *
     * @param j
     * @return Un objeto Cronogramas.
     */
    private Cronogramas jsonToCronograma(JSONObject j) {
        if (j != null) {
            try {
                Cronogramas c = new Cronogramas();
                if (j.has(ConstantesPresentacion.CRONO_PK)) {
                    c.setCroPk(j.getInt(ConstantesPresentacion.CRONO_PK));
                } else {
                }
                if (j.has(ConstantesPresentacion.CRONO_SELECTED_ROW)) {
                    c.setCroEntSeleccionado(j.getInt(ConstantesPresentacion.CRONO_SELECTED_ROW));
                }
                if (j.has(ConstantesPresentacion.CRONO_CAN_WRITE)) {
                    c.setCroPermisoEscritura(true);
                }
                if (j.has(ConstantesPresentacion.CRONO_CAN_WRITE_PARENT)) {
//                    c.setCroPermisoEscrituraPadre(j.getBoolean(ConstantesPresentacion.CRONO_CAN_WRITE_PARENT));
                    c.setCroPermisoEscrituraPadre(true);
                }
                if (j.has(ConstantesPresentacion.CRONO_DELETED_TASK_ID)) {
                    c.setCroEntBorrados(j.getString(ConstantesPresentacion.CRONO_DELETED_TASK_ID));
                }

                c.setEntregablesSet(jsonToEntregables(j));
//                c.setEntregablesToDeleteSet(jsonToEntregablesDeleted(j));
                return c;

            } catch (Exception w) {
                logger.log(Level.SEVERE, w.getMessage(), w);
                JSFUtils.agregarMsg("ganttForm", "error_cro_guardar", null);
            }
        }

        return null;
    }

//        /**
//     * Convierte el atributo task del JSONObject en un set de Entregables.
//     *
//     * @param j
//     * @return Set de Entregables
//     */
//    private Set<Entregables> jsonToEntregablesDeleted(JSONObject j) throws Exception {
//        if (j != null) {
//            try {
//                Set<Entregables> setEnt = new HashSet<>();
//                JSONArray tasks = j.getJSONArray(ConstantesPresentacion.TASKS_DELETED);
//
//                for (int i = 0; i < tasks.length(); i++) {
//                    JSONObject task = tasks.getJSONObject(i);
//                    Entregables e = new Entregables();
//
//                    if (task.has(ConstantesPresentacion.CRONO_PK)) {
//                        Cronogramas c = new Cronogramas(j.getInt(ConstantesPresentacion.CRONO_PK));
//                        e.setEntCroFk(c);
//                    }
////                    if (task.get(TASK_ID) instanceof Integer) {
////                        e.setEntId(task.getInt(TASK_ID));
////                    }
//                    e.setEntId(i + 1);
////                    e.setEntAssigs(null);
//                    if (task.has(ConstantesPresentacion.TASK_CODE)) {
//                        e.setEntCodigo(task.getString(ConstantesPresentacion.TASK_CODE));
//                    }
//                    if (task.has(ConstantesPresentacion.TASK_COORDINADOR)) {
//                        Object coordId = task.get(ConstantesPresentacion.TASK_COORDINADOR);
//                        if (coordId instanceof Integer) {
//                            e.setCoordinadorUsuFk(new SsUsuario(task.getInt(ConstantesPresentacion.TASK_COORDINADOR)));
//                        }
//                    }
//                    if (task.has(ConstantesPresentacion.TASK_COLLAPSED)) {
//                        e.setEntCollapsed(task.getBoolean(ConstantesPresentacion.TASK_COLLAPSED));
//                    }
//                    if (task.has(ConstantesPresentacion.TASK_DESCRIPTION)) {
//                        e.setEntDescripcion(task.getString(ConstantesPresentacion.TASK_DESCRIPTION));
//                    }
//                    if (task.has(ConstantesPresentacion.TASK_PARENT)) {
//                        e.setEntParent(task.getBoolean(ConstantesPresentacion.TASK_PARENT));
//                    }
//                    if (task.has(ConstantesPresentacion.TASK_DURATION)) {
//                        e.setEntDuracion(task.getInt(ConstantesPresentacion.TASK_DURATION));
//                    }
//                    if (task.has(ConstantesPresentacion.TASK_DURACION_LINEA_BASE)) {
//                        e.setEntDuracionLineaBase(task.getInt(ConstantesPresentacion.TASK_DURACION_LINEA_BASE));
//                    }
//                    e.setEntEsfuerzo(task.has(ConstantesPresentacion.TASK_ESFUERZO) ? task.getInt(ConstantesPresentacion.TASK_ESFUERZO) : 0);
//                    if (task.has(ConstantesPresentacion.TASK_END)) {
//                        Long l = task.getLong(ConstantesPresentacion.TASK_END);
//                        e.setEntFin(l != null && l > 0 ? l : null);
//                    }
//                    if (task.has(ConstantesPresentacion.TASK_END_IS_MILESTONE)) {
//                        e.setEntFinEsHito(task.getBoolean(ConstantesPresentacion.TASK_END_IS_MILESTONE));
//                    }
//                    if (task.has(ConstantesPresentacion.TASK_END_LINEA_BASE)) {
//                        Long l = task.getLong(ConstantesPresentacion.TASK_END_LINEA_BASE);
//                        e.setEntFinLineaBase(l != null && l > 0 ? l : null);
//                    }
//                    if (task.has(ConstantesPresentacion.TASK_START)) {
//                        Long l = task.getLong(ConstantesPresentacion.TASK_START);
//                        e.setEntInicio(l != null && l > 0 ? l : null);
//                    }
//                    if (task.has(ConstantesPresentacion.TASK_START_IS_MILESTONE)) {
//                        e.setEntInicioEsHito(task.getBoolean(ConstantesPresentacion.TASK_START_IS_MILESTONE));
//                    }
//                    if (task.has(ConstantesPresentacion.TASK_START_LINEA_BASE)) {
//                        Long l = task.getLong(ConstantesPresentacion.TASK_START_LINEA_BASE);
//                        e.setEntInicioLineaBase(l != null && l > 0 ? l : null);
//                    }
//                    if (task.has(ConstantesPresentacion.TASK_HORAS_ESTIMADAS)) {
//                        String he = task.getString(ConstantesPresentacion.TASK_HORAS_ESTIMADAS);
//                        e.setEntHorasEstimadas(he);
//                    }
//                    e.setEntNivel(task.has(ConstantesPresentacion.TASK_LEVEL) ? task.getInt(ConstantesPresentacion.TASK_LEVEL) : 0);
//                    if (task.has(ConstantesPresentacion.TASK_NAME)) {
//                        e.setEntNombre(task.getString(ConstantesPresentacion.TASK_NAME));
//                    }
//                    if (task.has(ConstantesPresentacion.TASK_PK)) {
//                        e.setEntPk(task.getInt(ConstantesPresentacion.TASK_PK));
//                    }
//
//                    if (task.has(ConstantesPresentacion.TASK_DEPENDS)) {
//                        String[] pred = task.getString(ConstantesPresentacion.TASK_DEPENDS).split(":");
//                        try {
//                            e.setEntPredecesorFk(task.getString(ConstantesPresentacion.TASK_DEPENDS));
//                            e.setEntPredecesorDias(pred.length >= 2 ? Integer.valueOf(pred[1]) : 0);
//                        } catch (NumberFormatException numberFormatException) {
//                            numberFormatException.printStackTrace();
//                        }
//                    }
//                    if (task.has(ConstantesPresentacion.TASK_PROGRESS)) {
//                        e.setEntProgreso(task.getInt(ConstantesPresentacion.TASK_PROGRESS));
//                    }
//
//                    if (task.has(ConstantesPresentacion.TASK_STATUS)) {
//                        e.setEntStatus(task.getString(ConstantesPresentacion.TASK_STATUS));
//                    }
//
//                    if (task.has(ConstantesPresentacion.TASK_RELEVANTE)) {
//                        e.setEntRelevante(task.getBoolean(ConstantesPresentacion.TASK_RELEVANTE));
//                    }
//
//                    setEnt.add(e);
//                }
//                return setEnt;
//
//            } catch (Exception w) {
//                logger.log(Level.SEVERE, "Error al convertir jsonToEntregables");
//                throw w;
//            }
//        }
//
//        return null;
//    }
    /**
     * Convierte el atributo task del JSONObject en un set de Entregables.
     *
     * @param j
     * @return Set de Entregables
     */
    private Set<Entregables> jsonToEntregables(JSONObject j) throws Exception {
        if (j != null) {
            try {
                Set<Entregables> setEnt = new HashSet<Entregables>();
                JSONArray tasks = j.getJSONArray(ConstantesPresentacion.TASKS);

                for (int i = 0; i < tasks.length(); i++) {
                    JSONObject task = tasks.getJSONObject(i);
                    Entregables e = new Entregables();

                    if (task.has(ConstantesPresentacion.CRONO_PK)) {
                        Cronogramas c = new Cronogramas(j.getInt(ConstantesPresentacion.CRONO_PK));
                        e.setEntCroFk(c);
                    }
//                    if (task.get(TASK_ID) instanceof Integer) {
//                        e.setEntId(task.getInt(TASK_ID));
//                    }
                    e.setEntId(i + 1);
//                    e.setEntAssigs(null);
                    if (task.has(ConstantesPresentacion.TASK_CODE)) {
                        e.setEntCodigo(task.getString(ConstantesPresentacion.TASK_CODE));
                    }
                    if (task.has(ConstantesPresentacion.TASK_COORDINADOR)) {
                        Object coordId = task.get(ConstantesPresentacion.TASK_COORDINADOR);
                        if (coordId instanceof Integer) {
                            e.setCoordinadorUsuFk(new SsUsuario(task.getInt(ConstantesPresentacion.TASK_COORDINADOR)));
                        }
                    }
                    if (task.has(ConstantesPresentacion.TASK_COLLAPSED)) {
                        e.setEntCollapsed(task.getBoolean(ConstantesPresentacion.TASK_COLLAPSED));
                    }
                    if (task.has(ConstantesPresentacion.TASK_DESCRIPTION)) {
                        e.setEntDescripcion(task.getString(ConstantesPresentacion.TASK_DESCRIPTION));
                    }
                    if (task.has(ConstantesPresentacion.TASK_PARENT)) {
                        e.setEntParent(task.getBoolean(ConstantesPresentacion.TASK_PARENT));
                    }
                    if (task.has(ConstantesPresentacion.TASK_DURATION)) {
                        e.setEntDuracion(task.getInt(ConstantesPresentacion.TASK_DURATION));
                    }
                    if (task.has(ConstantesPresentacion.TASK_DURACION_LINEA_BASE)) {
                        e.setEntDuracionLineaBase(task.getInt(ConstantesPresentacion.TASK_DURACION_LINEA_BASE));
                    }
                    e.setEntEsfuerzo(task.has(ConstantesPresentacion.TASK_ESFUERZO) ? task.getInt(ConstantesPresentacion.TASK_ESFUERZO) : 0);
                    if (task.has(ConstantesPresentacion.TASK_END)) {
                        Long l = task.getLong(ConstantesPresentacion.TASK_END);
                        e.setEntFin(l != null && l > 0 ? l : null);
                    }
                    if (task.has(ConstantesPresentacion.TASK_END_LINEA_BASE)) {
                        Long l = task.getLong(ConstantesPresentacion.TASK_END_LINEA_BASE);
                        e.setEntFinLineaBase(l != null && l > 0 ? l : null);
                    }
                    if (task.has(ConstantesPresentacion.TASK_START)) {
                        Long l = task.getLong(ConstantesPresentacion.TASK_START);
                        e.setEntInicio(l != null && l > 0 ? l : null);
                    }
                    if (task.has(ConstantesPresentacion.TASK_START_LINEA_BASE)) {
                        Long l = task.getLong(ConstantesPresentacion.TASK_START_LINEA_BASE);
                        e.setEntInicioLineaBase(l != null && l > 0 ? l : null);
                    }
                    if (task.has(ConstantesPresentacion.TASK_HORAS_ESTIMADAS)) {
                        String he = task.getString(ConstantesPresentacion.TASK_HORAS_ESTIMADAS);
                        e.setEntHorasEstimadas(he);
                    }
                    e.setEntNivel(task.has(ConstantesPresentacion.TASK_LEVEL) ? task.getInt(ConstantesPresentacion.TASK_LEVEL) : 0);
                    if (task.has(ConstantesPresentacion.TASK_NAME)) {
                        e.setEntNombre(task.getString(ConstantesPresentacion.TASK_NAME));
                    }
                    if (task.has(ConstantesPresentacion.TASK_PK)) {
                        e.setEntPk(task.getInt(ConstantesPresentacion.TASK_PK));
                    }

                    if (task.has(ConstantesPresentacion.TASK_DEPENDS)) {
                        String[] pred = task.getString(ConstantesPresentacion.TASK_DEPENDS).split(":");
                        try {
                            e.setEntPredecesorFk(task.getString(ConstantesPresentacion.TASK_DEPENDS));
                            e.setEntPredecesorDias(pred.length >= 2 ? Integer.valueOf(pred[1]) : 0);
                        } catch (NumberFormatException numberFormatException) {
                            numberFormatException.printStackTrace();
                        }
                    }
                    if (task.has(ConstantesPresentacion.TASK_PROGRESS)) {
                        e.setEntProgreso(task.getInt(ConstantesPresentacion.TASK_PROGRESS));
                    }

                    if (task.has(ConstantesPresentacion.TASK_STATUS)) {
                        e.setEntStatus(task.getString(ConstantesPresentacion.TASK_STATUS));
                    }

                    if (task.has(ConstantesPresentacion.TASK_RELEVANTE)) {
                        e.setEntRelevante(task.getBoolean(ConstantesPresentacion.TASK_RELEVANTE));
                    }

                    if (task.has(ConstantesPresentacion.TASK_INICIO_PROYECTO)) {
                        e.setEntInicioProyecto(task.getBoolean(ConstantesPresentacion.TASK_INICIO_PROYECTO));
                    }

                    if (task.has(ConstantesPresentacion.TASK_FIN_PROYECTO)) {
                        e.setEntFinProyecto(task.getBoolean(ConstantesPresentacion.TASK_FIN_PROYECTO));
                    }
                    if (task.has(ConstantesPresentacion.TASK_END_IS_MILESTONE)) {
                        e.setEntFinEsHito(task.getBoolean(ConstantesPresentacion.TASK_END_IS_MILESTONE));
                        /**
                         * [BRUNO] 30-05-17: seteo el inicio igual al fin a
                         * prepo cuando es hito.
                         */
                        if (e.getEntFinEsHito()) {
                            e.setEntInicio(e.getEntFin());
                            e.setEntInicioLineaBase(e.getEntFinLineaBase());
                        }
                    }
                    if (task.has(ConstantesPresentacion.TASK_START_IS_MILESTONE)) {
                        e.setEntInicioEsHito(task.getBoolean(ConstantesPresentacion.TASK_START_IS_MILESTONE));

                    }

                    setEnt.add(e);
                }

                /**
                 * [BRUNO] 30-05-17: TODO corrijo fechas de los entregables 1-
                 * Si la fecha de inicio es mayor a la de fin 2- Si la fecha de
                 * fin del que depende es mayor a la fecha de inicio
                 */
                return setEnt;

            } catch (Exception w) {
                logger.log(Level.SEVERE, "Error al convertir jsonToEntregables");
                throw w;
            }
        }

        return null;
    }

    public String agregarProductoAction() {
        Entregables ent = (Entregables) listaEntProdCombo.getSelectedObject();
        producto.setProdEntregableFk(ent);

        try {
            producto = productosDelegate.guardarProducto(producto, true);
        } catch (BusinessException be) {
            JSFUtils.agregarMsgs(PROD_FORM_MSG_ID, be.getErrores());
            return null;
        }

        actualizarFichaTO(null);
        limpiarProducto();
        cargarFrameProductos();
        cerrarFormCollapsable();

        return null;
    }

    public String mesToStr(Integer mes, Boolean abreviado) {
        return WebUtils.mesToStr(mes, abreviado);
    }

    public String estadoMediaToStr(Integer cod) {
        return WebUtils.estadoMediaToStr(cod);
    }

    public void setFocus(String elementId) {
        JavascriptContext.applicationFocus(FacesContext.getCurrentInstance(), elementId);
    }

    public void progSetPeso(ValueChangeEvent event) {
        PhaseId phaseId = event.getPhaseId();
        if (phaseId.equals(PhaseId.ANY_PHASE)) {
            event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
            event.queue();
        } else if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES)) {
            if (listaProgramasCombo.getSelected() > 0 && fichaTO.getPeso() < 2) {
                fichaTO.setPeso(2);
            }
        }
    }

    public Date obtenerFechaAct() {

        Date d1 = this.fichaTO.getFechaAct();
        Date d2 = this.riesgoActualizacion;
        Date d3 = this.multiFechaActCamaras;
        Date d4 = this.multiFechaActFotos;
        Date d5 = this.multiFechaActVideos;
        Date ret = null;
        if (d1 != null) {
            ret = d1;
            if (d2 != null && DatesUtils.esMayor(d2, ret)) {
                ret = d2;
            }
            if (d3 != null && DatesUtils.esMayor(d3, ret)) {
                ret = d3;
            }
            if (d4 != null && DatesUtils.esMayor(d4, ret)) {
                ret = d4;
            }
            if (d5 != null && DatesUtils.esMayor(d5, ret)) {
                ret = d5;
            }
        }
        return ret;
    }

    // <editor-fold defaultstate="collapsed" desc="getter-setter">
    public Double getIndiceEstado() {
        return indiceEstado;
    }

    public void setIndiceEstado(Double indiceEstado) {
        this.indiceEstado = indiceEstado;
    }

    public String getIndiceMetodologiaColor() {
        return indiceMetodologiaColor;
    }

    public void setIndiceMetodologiaColor(String indiceMetodologiaColor) {
        this.indiceMetodologiaColor = indiceMetodologiaColor;
    }

    public DataTable getDocsTable() {
        return docsTable;
    }

    public void setDocsTable(DataTable docsTable) {
        this.docsTable = docsTable;
    }

    public List<Personas> getAutoCompletePersonasList() {
        return autoCompletePersonasList;
    }

    public AutoCompleteEntry getAutoCompletePersonasComponent() {
        return autoCompletePersonasComponent;
    }

    public void setAutoCompletePersonasComponent(AutoCompleteEntry autoCompletePersonasComponent) {
        this.autoCompletePersonasComponent = autoCompletePersonasComponent;
    }

    public void setAutoCompletePersonasList(List<Personas> autoCompletePersonasList) {
        this.autoCompletePersonasList = autoCompletePersonasList;
    }

    public Long getSelectedMostrar() {
        return selectedMostrar;
    }

    public void setSelectedMostrar(Long selectedMostrar) {
        this.selectedMostrar = selectedMostrar;
    }

    public Boolean[] getMostrar() {
        return mostrar;
    }

    public void setMostrar(Boolean[] mostrar) {
        this.mostrar = mostrar;
    }

    public Boolean getRenderPopupInteresados() {
        return renderPopupInteresados;
    }

    public void setRenderPopupInteresados(Boolean renderPopupInteresados) {
        this.renderPopupInteresados = renderPopupInteresados;
    }

    public NodeStateMap getAreasRestringidasStateMap() {
        return areasRestringidasStateMap;
    }

    public void setAreasRestringidasStateMap(NodeStateMap areasRestringidasStateMap) {
        this.areasRestringidasStateMap = areasRestringidasStateMap;
    }

    public NodeStateMap getAreasTematicasStateMap() {
        return areasTematicasStateMap;
    }

    public void setAreasTematicasStateMap(NodeStateMap areasTematicasStateMap) {
        this.areasTematicasStateMap = areasTematicasStateMap;
    }

    public Boolean getRenderPopupLectura() {
        return renderPopupLectura;
    }

    public Boolean getRenderPopupAreaTematica() {
        return renderPopupAreaTematica;
    }

    public void setRenderPopupAreaTematica(Boolean renderPopupAreaTematica) {
        this.renderPopupAreaTematica = renderPopupAreaTematica;
    }

    public Boolean getRenderPopupMetodologia() {
        return renderPopupMetodologia;
    }

    public void setRenderPopupMetodologia(Boolean renderPopupMetodologia) {
        this.renderPopupMetodologia = renderPopupMetodologia;
    }

    public void setRenderPopupLectura(Boolean renderPopupLectura) {
        this.renderPopupLectura = renderPopupLectura;
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public InicioMB getInicioMB() {
        return inicioMB;
    }

    public List<AreasTags> getListaAreasTags() {
        return listaAreasTags;
    }

    public void setListaAreasTags(List<AreasTags> listaAreasTags) {
        this.listaAreasTags = listaAreasTags;
    }

    public List<Programas> getListaProgramas() {
        return listaProgramas;
    }

    public void setListaProgramas(List<Programas> listaProgramas) {
        this.listaProgramas = listaProgramas;
    }

    public List<RolesInteresados> getListaRolesInteresados() {
        return listaRolesInteresados;
    }

    public void setListaRolesInteresados(List<RolesInteresados> listaRolesInteresados) {
        this.listaRolesInteresados = listaRolesInteresados;
    }

    public List<TipoDocumentoInstancia> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<TipoDocumentoInstancia> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    public List<Entregables> getListaEntregables() {
        return listaEntregables;
    }

    public void setListaEntregables(List<Entregables> listaEntregable) {
        this.listaEntregables = listaEntregable;
    }

    public List<Double> getListaEstDoc() {
        return listaEstDoc;
    }

    public void setListaEstDoc(List<Double> listaEstDoc) {
        this.listaEstDoc = listaEstDoc;
    }

    public List<ComboItemTO> getListaRiskProbabilidad() {
        return listaRiskProbabilidad;
    }

    public void setListaRiskProbabilidad(List<ComboItemTO> listaRiskProbabilidad) {
        this.listaRiskProbabilidad = listaRiskProbabilidad;
    }

    public List<Integer> getListaRiskImpacto() {
        return listaRiskImpacto;
    }

    public void setListaRiskImpacto(List<Integer> listaRiskImpacto) {
        this.listaRiskImpacto = listaRiskImpacto;
    }

    public SofisCombo getListaRiskImpactoCombo() {
        return listaRiskImpactoCombo;
    }

    public void setListaRiskImpactoCombo(SofisCombo listaRiskImpactoCombo) {
        this.listaRiskImpactoCombo = listaRiskImpactoCombo;
    }

    public SofisComboG<Entregables> getListaRiskEntregablesCombo() {
        return listaRiskEntregablesCombo;
    }

    public void setListaRiskEntregablesCombo(SofisComboG<Entregables> listaRiskEntregablesCombo) {
        this.listaRiskEntregablesCombo = listaRiskEntregablesCombo;
    }

    public SofisComboG<Entregables> getListaIntEntregablesCombo() {
        return listaIntEntregablesCombo;
    }

    public void setListaIntEntregablesCombo(SofisComboG<Entregables> listaIntEntregablesCombo) {
        this.listaIntEntregablesCombo = listaIntEntregablesCombo;
    }

    public List<Documentos> getListaDocumentosResumen() {
        return listaDocumentosResumen;
    }

    public void setListaDocumentosResumen(List<Documentos> listaDocumentosResumen) {
        this.listaDocumentosResumen = listaDocumentosResumen;
    }

    public List<TipoDocumentoInstancia> getListaTipoDocInstResumen() {
        return listaTipoDocInstResumen;
    }

    public void setListaTipoDocInstResumen(List<TipoDocumentoInstancia> listaTipoDocInstResumen) {
        this.listaTipoDocInstResumen = listaTipoDocInstResumen;
    }

    public List<Documentos> getListaDocumentosFrame() {
        return listaDocumentosFrame;
    }

    public void setListaDocumentosFrame(List<Documentos> listaDocumentosFrame) {
        this.listaDocumentosFrame = listaDocumentosFrame;
    }

    public List<Interesados> getListaInteresadosResumen() {
        return listaInteresadosResumen;
    }

    public void setListaInteresadosResumen(List<Interesados> listaInteresadosResumen) {
        this.listaInteresadosResumen = listaInteresadosResumen;
    }

    public List<Riesgos> getListaRiesgosResumen() {
        return listaRiesgosResumen;
    }

    public void setListaRiesgosResumen(List<Riesgos> listaRiesgosResumen) {
        this.listaRiesgosResumen = listaRiesgosResumen;
    }

    public SofisCombo getListaAreasOrganismoCombo() {
        return listaAreasOrganismoCombo;
    }

    public void setListaAreasOrganismoCombo(SofisCombo listaAreasOrganismoCombo) {
        this.listaAreasOrganismoCombo = listaAreasOrganismoCombo;
    }

    public SofisCombo getListaProgramasCombo() {
        return listaProgramasCombo;
    }

    public void setListaProgramasCombo(SofisCombo listaProgramasCombo) {
        this.listaProgramasCombo = listaProgramasCombo;
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

    public SofisCombo getListaPmoFederadaCombo() {
        return listaPmoFederadaCombo;
    }

    public void setListaPmoFederadaCombo(SofisCombo listaPmoFederadaCombo) {
        this.listaPmoFederadaCombo = listaPmoFederadaCombo;
    }

    public SofisCombo getListaEtapaPubCombo() {
        return listaEtapaPubCombo;
    }

    public void setListaEtapaPubCombo(SofisCombo listaEtapaPubCombo) {
        this.listaEtapaPubCombo = listaEtapaPubCombo;
    }

    public SofisCombo getListaOrganizacionCombo() {
        return listaOrganizacionCombo;
    }

    public void setListaOrganizacionCombo(SofisCombo listaOrganizacionCombo) {
        this.listaOrganizacionCombo = listaOrganizacionCombo;
    }

    public SofisCombo getListaRolesInteresadosCombo() {
        return listaRolesInteresadosCombo;
    }

    public void setListaRolesInteresadosCombo(SofisCombo listaRolesInteresadosCombo) {
        this.listaRolesInteresadosCombo = listaRolesInteresadosCombo;
    }

    public SofisCombo getListaTipoDocCombo() {
        return listaTipoDocCombo;
    }

    public void setListaTipoDocCombo(SofisCombo listaTipoDocCombo) {
        this.listaTipoDocCombo = listaTipoDocCombo;
    }

    public Map<Integer, SofisResource> getDownloadFile() {
        return downloadFile;
    }

    public void setDownloadFile(Map<Integer, SofisResource> downloadFile) {
        this.downloadFile = downloadFile;
    }

    public SofisCombo getListaEntregablesCombo() {
        return listaEntregablesCombo;
    }

    public void setListaEntregablesCombo(SofisCombo listaEntregableCombo) {
        this.listaEntregablesCombo = listaEntregableCombo;
    }

    public SofisComboG<Pagos> getListaDocPagoCombo() {
        return listaDocPagoCombo;
    }

    public void setListaDocPagoCombo(SofisComboG<Pagos> listaDocPagoCombo) {
        this.listaDocPagoCombo = listaDocPagoCombo;
    }

    public List<ProySitactHistorico> getHistoricoSitAct() {
        return historicoSitAct;
    }

    public void setHistoricoSitAct(List<ProySitactHistorico> historicoSitAct) {
        this.historicoSitAct = historicoSitAct;
    }

    public SofisCombo getListaRiskProbabilidadCombo() {
        return listaRiskProbabilidadCombo;
    }

    public void setListaRiskProbabilidadCombo(SofisCombo listaRiskProbabilidadCombo) {
        this.listaRiskProbabilidadCombo = listaRiskProbabilidadCombo;
    }

    public List<MutableTreeNode> getListaAreasTagsTreeNode() {
        return listaAreasTagsTreeNode;
    }

    public void setListaAreasTagsTreeNode(List<MutableTreeNode> listaAreasTagsTreeNode) {
        this.listaAreasTagsTreeNode = listaAreasTagsTreeNode;
    }

    public List<MutableTreeNode> getListaAreasTreeNode() {
        return listaAreasTreeNode;
    }

    public void setListaAreasTreeNode(List<MutableTreeNode> listaAreasTreeNode) {
        this.listaAreasTreeNode = listaAreasTreeNode;
    }

    public List<Areas> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(List<Areas> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public boolean isMostrarPanel() {
        return mostrarPanel;
    }

    public void setMostrarPanel(boolean mostrarPanel) {
        this.mostrarPanel = mostrarPanel;
    }

    public HashMap<String, Boolean> getPermisosEdicion() {
        return permisosEdicion;
    }

    public void setPermisosEdicion(HashMap<String, Boolean> permisosEdicion) {
        this.permisosEdicion = permisosEdicion;
    }

    public FichaTO getFichaTO() {
        return fichaTO;
    }

    public void setFichaTO(FichaTO fichaTO) {
        this.fichaTO = fichaTO;
    }

    public String getAreaOrganizacion() {
        return areaOrganizacion;
    }

    public void setAreaOrganizacion(String areaOrganizacion) {
        this.areaOrganizacion = areaOrganizacion;
    }

    public List getEmptyList() {
        return emptyList;
    }

    public void setEmptyList(List emptyList) {
        this.emptyList = emptyList;
    }

    public Interesados getInteresado() {
        return interesado;
    }

    public void setInteresado(Interesados interesado) {
        this.interesado = interesado;
    }

    public Documentos getDocumento() {
        return documento;
    }

    public void setDocumento(Documentos documento) {
        this.documento = documento;
    }

    public File getUpFileDoc() {
        return upFileDoc;
    }

    public void setUpFileDoc(File upFileDoc) {
        this.upFileDoc = upFileDoc;
    }

    public Riesgos getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(Riesgos riesgo) {
        this.riesgo = riesgo;
    }

    public List<FilaRiesgosLimite> getLimiteGestionRiesgos() {
        return limiteGestionRiesgos;
    }

    public void setLimiteGestionRiesgos(List<FilaRiesgosLimite> limiteGestionRiesgos) {
        this.limiteGestionRiesgos = limiteGestionRiesgos;
    }

    public Date getRiesgoActualizacion() {
        return riesgoActualizacion;
    }

    public void setRiesgoActualizacion(Date riesgoActualizacion) {
        this.riesgoActualizacion = riesgoActualizacion;
    }

    public String getRiesgoActualizacionColor() {
        return riesgoActualizacionColor;
    }

    public Integer getRiesgosCantAltos() {
        return riesgosCantAltos;
    }

    public void setRiesgosCantAltos(Integer riesgosCantAltos) {
        this.riesgosCantAltos = riesgosCantAltos;
    }

    public Double getExposicionRiesgo() {
        return exposicionRiesgo;
    }

    public void setExposicionRiesgo(Double exposicionRiesgo) {
        this.exposicionRiesgo = exposicionRiesgo;
    }

    public String getExposicionRiesgoColor() {
        return exposicionRiesgoColor;
    }

    public void setExposicionRiesgoColor(String exposicionRiesgoColor) {
        this.exposicionRiesgoColor = exposicionRiesgoColor;
    }

    public void setRiesgoActualizacionColor(String riesgoActualizacionColor) {
        this.riesgoActualizacionColor = riesgoActualizacionColor;
    }

    public Boolean getRiskFormDataExpanded() {
        return riskFormDataExpanded;
    }

    public void setRiskFormDataExpanded(Boolean riskFormDataExpanded) {
        this.riskFormDataExpanded = riskFormDataExpanded;
    }

    public Boolean getPreFormDataExpanded() {
        return preFormDataExpanded;
    }

    public void setPreFormDataExpanded(Boolean preFormDataExpanded) {
        this.preFormDataExpanded = preFormDataExpanded;
    }

    public int getFormPreRendered() {
        return formPresupuestoRendered;
    }

    public void setFormPreRendered(int formPreRendered) {
        this.formPresupuestoRendered = formPreRendered;
    }

    public List<Moneda> getListaMonedas() {
        return listaMonedas;
    }

    public void setListaMonedas(List<Moneda> listaMonedas) {
        this.listaMonedas = listaMonedas;
    }

    public SofisCombo getListaMonedaCombo() {
        return listaMonedaCombo;
    }

    public void setListaMonedaCombo(SofisCombo listaMonedaCombo) {
        this.listaMonedaCombo = listaMonedaCombo;
    }

    public SofisCombo getListaMonedaPreCombo() {
        return listaMonedaPreCombo;
    }

    public void setListaMonedaPreCombo(SofisCombo listaMonedaPreCombo) {
        this.listaMonedaPreCombo = listaMonedaPreCombo;
    }

    public SofisCombo getListaFuentesPreCombo() {
        return listaFuentesPreCombo;
    }

    public void setListaFuentesPreCombo(SofisCombo listaFuentesPreCombo) {
        this.listaFuentesPreCombo = listaFuentesPreCombo;
    }

    public Presupuesto getPreFicha() {
        return preFicha;
    }

    public void setPreFicha(Presupuesto preFicha) {
        this.preFicha = preFicha;
    }

    public List<FuenteFinanciamiento> getListaFuentes() {
        return listaFuentes;
    }

    public void setListaFuentes(List<FuenteFinanciamiento> listaFuentes) {
        this.listaFuentes = listaFuentes;
    }

    public SofisCombo getListaFuentesCombo() {
        return listaFuentesCombo;
    }

    public void setListaFuentesCombo(SofisCombo listaFuentesCombo) {
        this.listaFuentesCombo = listaFuentesCombo;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Adquisicion getAdquisicion() {
        return adquisicion;
    }

    public void setAdquisicion(Adquisicion adquisicion) {
        this.adquisicion = adquisicion;
    }

    public Pagos getPagos() {
        return pagos;
    }

    public void setPagos(Pagos pagos) {
        this.pagos = pagos;
    }

    public List<AdqPagosTO> getListaAdqPagosFrame() {
        return listaAdqPagosFrame;
    }

    public void setListaAdqPagosFrame(List<AdqPagosTO> listaAdqPagosFrame) {
        this.listaAdqPagosFrame = listaAdqPagosFrame;
    }

    public DataTable getPagoTable() {
        return pagoTable;
    }

    public void setPagoTable(DataTable pagoTable) {
        this.pagoTable = pagoTable;
    }

    public Boolean getIntFormDataExpanded() {
        return intFormDataExpanded;
    }

    public void setIntFormDataExpanded(Boolean intFormDataExpanded) {
        this.intFormDataExpanded = intFormDataExpanded;
    }

    public Boolean getDocsFormDataExpanded() {
        return docsFormDataExpanded;
    }

    public void setDocsFormDataExpanded(Boolean docsFormDataExpanded) {
        this.docsFormDataExpanded = docsFormDataExpanded;
    }

    public Boolean getRenderPopupRiesgo() {
        return renderPopupRiesgo;
    }

    public void setRenderPopupRiesgo(Boolean renderPopupRiesgo) {
        this.renderPopupRiesgo = renderPopupRiesgo;
    }

    public Riesgos getRiskPopup() {
        return riskPopup;
    }

    public void setRiskPopup(Riesgos riesgoPopup) {
        this.riskPopup = riesgoPopup;
    }

    public Boolean getRenderPopupDocumentos() {
        return renderPopupDocumentos;
    }

    public void setRenderPopupDocumentos(Boolean renderPopupDocumentos) {
        this.renderPopupDocumentos = renderPopupDocumentos;
    }

    public Boolean getRenderPopupHistoricoSitAct() {
        return renderPopupHistoricoSitAct;
    }

    public void setRenderPopupHistoricoSitAct(Boolean renderPopupHistoricoSitAct) {
        this.renderPopupHistoricoSitAct = renderPopupHistoricoSitAct;
    }

    public Boolean getRenderPopupOtrosDatos() {
        return renderPopupOtrosDatos;
    }

    public void setRenderPopupOtrosDatos(Boolean renderPopupOtrosDatos) {
        this.renderPopupOtrosDatos = renderPopupOtrosDatos;
    }

    public Documentos getDocumentoPopup() {
        return documentoPopup;
    }

    public void setDocumentoPopup(Documentos documentoPopup) {
        this.documentoPopup = documentoPopup;
    }

    public List<SelectItem> getListaEstDocCombo() {
        return listaEstDocCombo;
    }

    public void setListaEstDocCombo(List<SelectItem> listaEstDocCombo) {
        this.listaEstDocCombo = listaEstDocCombo;
    }

    public List<Moneda> getMonedasPresupuesto() {
        return monedasPresupuesto;
    }

    public void setMonedasPresupuesto(List<Moneda> monedasPresupuesto) {
        this.monedasPresupuesto = monedasPresupuesto;
    }

    public List<TablaDinamicaPresupuestoTO> getPresupuestoResumen() {
        return presupuestoResumen;
    }

    public void setPresupuestoResumen(List<TablaDinamicaPresupuestoTO> presupuestoResumen) {
        this.presupuestoResumen = presupuestoResumen;
    }

    public String getFechaUltimaSitAct() {
        return fechaUltimaSitAct;
    }

    public void setFechaUltimaSitAct(String fechaUltimaSitAct) {
        this.fechaUltimaSitAct = fechaUltimaSitAct;
    }

    public List<Adquisicion> getListAdqDev() {
        return listAdqDev;
    }

    public void setListAdqDev(List<Adquisicion> listAdqDev) {
        this.listAdqDev = listAdqDev;
    }

    public Integer getAnioDev() {
        return anioDev;
    }

    public void setAnioDev(Integer anioDev) {
        this.anioDev = anioDev;
    }

    public boolean isEditDev() {
        return editDev;
    }

    public void setEditDev(boolean editDev) {
        this.editDev = editDev;
    }

    public SofisPopupUI getRenderAdqDevPopup() {
        return renderAdqDevPopup;
    }

    public void setRenderAdqDevPopup(SofisPopupUI renderAdqDevPopup) {
        this.renderAdqDevPopup = renderAdqDevPopup;
    }

    public List<Adquisicion> getListAdq() {
        return listAdq;
    }

    public void setListAdq(List<Adquisicion> listAdq) {
        this.listAdq = listAdq;
    }

    public SofisCombo getListAdqCombo() {
        return listAdqCombo;
    }

    public void setListAdqCombo(SofisCombo listAdqCombo) {
        this.listAdqCombo = listAdqCombo;
    }

    public static String getGROUP_NAME() {
        return GROUP_NAME;
    }

    public static void setGROUP_NAME(String GROUP_NAME) {
        FichaMB.GROUP_NAME = GROUP_NAME;
    }

    public int getFormPresupuestoRendered() {
        return formPresupuestoRendered;
    }

    public void setFormPresupuestoRendered(int formPresupuestoRendered) {
        this.formPresupuestoRendered = formPresupuestoRendered;
    }

    public boolean isProdFormDataExpanded() {
        return prodFormDataExpanded;
    }

    public void setProdFormDataExpanded(boolean productosFormDataExpanded) {
        this.prodFormDataExpanded = productosFormDataExpanded;
    }

    public Map<String, Integer> getMapProdRango() {
        return mapProdRango;
    }

    public void setMapProdRango(Map<String, Integer> mapProdRango) {
        this.mapProdRango = mapProdRango;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Boolean getRenderPopupMapaUbicacion() {
        return renderPopupMapaUbicacion;
    }

    public void setRenderPopupMapaUbicacion(Boolean renderPopupMapaUbicacion) {
        this.renderPopupMapaUbicacion = renderPopupMapaUbicacion;
    }

    public List<Entregables> getEntregablesListProd() {
        return entregablesListProd;
    }

    public void setEntregablesListProd(List<Entregables> entregablesListProd) {
        this.entregablesListProd = entregablesListProd;
    }

    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    public SofisCombo getListaEntProdCombo() {
        return listaEntProdCombo;
    }

    public void setListaEntProdCombo(SofisCombo listaEntProdCombo) {
        this.listaEntProdCombo = listaEntProdCombo;
    }

    public Map<Integer, Boolean> getEditarProdMap() {
        return editarProdMap;
    }

    public void setEditarProdMap(Map<Integer, Boolean> editarProdMap) {
        this.editarProdMap = editarProdMap;
    }

    public List<Productos> getListaProductosResumen() {
        return listaProductosResumen;
    }

    public void setListaProductosResumen(List<Productos> listaProductosResumen) {
        this.listaProductosResumen = listaProductosResumen;
    }

    public ProyReplanificacion getReplanificacion() {
        return replanificacion;
    }

    public void setReplanificacion(ProyReplanificacion replanificacion) {
        this.replanificacion = replanificacion;
    }

    public Boolean getRenderPopupReplanificacion() {
        return renderPopupReplanificacion;
    }

    public void setRenderPopupReplanificacion(Boolean renderPopupReplanificacion) {
        this.renderPopupReplanificacion = renderPopupReplanificacion;
    }

    public Boolean getRenderPopupCopiaProy() {
        return renderPopupCopiaProy;
    }

    public void setRenderPopupCopiaProy(Boolean renderPopupCopiaProy) {
        this.renderPopupCopiaProy = renderPopupCopiaProy;
    }

    public Boolean getRenderPopupNotificacion() {
        return renderPopupNotificacion;
    }

    public void setRenderPopupNotificacion(Boolean renderPopupNotificacion) {
        this.renderPopupNotificacion = renderPopupNotificacion;
    }

    public String getNombreProyCopia() {
        return nombreProyCopia;
    }

    public void setNombreProyCopia(String nombreProyCopia) {
        this.nombreProyCopia = nombreProyCopia;
    }

    public Date getFechaComienzoProyCopia() {
        return fechaComienzoProyCopia;
    }

    public void setFechaComienzoProyCopia(Date fechaComienzoProyCopia) {
        this.fechaComienzoProyCopia = fechaComienzoProyCopia;
    }

    public void setFileResource(Resource fileResource) {
        this.fileResource = fileResource;
    }

    public String getDataCron() {
        return dataCron;
    }

    public void setDataCron(String dataCron) {
        this.dataCron = dataCron;
    }

    public int[] getIndiceAvanceFinalizado() {
        return indiceAvanceFinalizado;
    }

    public void setIndiceAvanceFinalizado(int[] indiceAvanceFinalizado) {
        this.indiceAvanceFinalizado = indiceAvanceFinalizado;
    }

    public int[] getIndiceAvanceParcial() {
        return indiceAvanceParcial;
    }

    public void setIndiceAvanceParcial(int[] indiceAvanceParcial) {
        this.indiceAvanceParcial = indiceAvanceParcial;
    }

    public List<Entregables> getListaEntregablesResumen() {
        return listaEntregablesResumen;
    }

    public void setListaEntregablesResumen(List<Entregables> listaEntregablesResumen) {
        this.listaEntregablesResumen = listaEntregablesResumen;
    }

    public Cronogramas getCronograma() {
        return cronograma;
    }

    public void setCronograma(Cronogramas cronograma) {
        this.cronograma = cronograma;
    }

    // </editor-fold>
    public String getLabelObjEstValue() {
        return labelObjEstValue;
    }

    public void setLabelObjEstValue(String labelObjEstValue) {
        this.labelObjEstValue = labelObjEstValue;
    }

    public List<Participantes> getListaParticipantes() {
        return listaParticipantes;
    }

    public void setListaParticipantes(List<Participantes> listaParticipantes) {
        this.listaParticipantes = listaParticipantes;
    }

    public List<Participantes> getListaParticipantesResumen() {
        return listaParticipantesResumen;
    }

    public void setListaParticipantesResumen(List<Participantes> listaParticipantesResumen) {
        this.listaParticipantesResumen = listaParticipantesResumen;
    }

    public Boolean getPartFormDataExpanded() {
        return partFormDataExpanded;
    }

    public void setPartFormDataExpanded(Boolean partFormDataExpanded) {
        this.partFormDataExpanded = partFormDataExpanded;
    }

    public List<SsUsuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public boolean isRenderPopupPlantillaCro() {
        return renderPopupPlantillaCro;
    }

    public void setRenderPopupPlantillaCro(boolean renderPopupPlantillaCro) {
        this.renderPopupPlantillaCro = renderPopupPlantillaCro;
    }

    public PlantillaCronograma getPlantillaCro() {
        return plantillaCro;
    }

    public void setPlantillaCro(PlantillaCronograma plantillaCro) {
        this.plantillaCro = plantillaCro;
    }

    public List<PlantillaCronograma> getPlantillaCroList() {
        return plantillaCroList;
    }

    public void setPlantillaCroList(List<PlantillaCronograma> plantillaCroList) {
        this.plantillaCroList = plantillaCroList;
    }

    public SofisCombo getPlantillaCroListCombo() {
        return plantillaCroListCombo;
    }

    public void setPlantillaCroListCombo(SofisCombo plantillaCroListCombo) {
        this.plantillaCroListCombo = plantillaCroListCombo;
    }

    public Date getPlantillaFechaInicio() {
        return plantillaFechaInicio;
    }

    public void setPlantillaFechaInicio(Date plantillaFechaInicio) {
        this.plantillaFechaInicio = plantillaFechaInicio;
    }

    public Boolean getMultiFormDataExpanded() {
        return multiFormDataExpanded;
    }

    public void setMultiFormDataExpanded(Boolean multiFormDataExpanded) {
        this.multiFormDataExpanded = multiFormDataExpanded;
    }

    public MediaProyectos getMediaProy() {
        return mediaProy;
    }

    public void setMediaProy(MediaProyectos mediaProy) {
        this.mediaProy = mediaProy;
    }

    public SofisComboG<TiposMedia> getListaTipoMediaCombo() {
        return listaTipoMediaCombo;
    }

    public void setListaTipoMediaCombo(SofisComboG<TiposMedia> listaTipoMediaCombo) {
        this.listaTipoMediaCombo = listaTipoMediaCombo;
    }

    public TiposMedia getTipoMediaSelected() {
        return tipoMediaSelected;
    }

    public void setTipoMediaSelected(TiposMedia tipoMediaSelected) {
        this.tipoMediaSelected = tipoMediaSelected;
    }

    public int[] getIndiceAvanceProd() {
        return indiceAvanceProd;
    }

    public void setIndiceAvanceProd(int[] indiceAvanceProd) {
        this.indiceAvanceProd = indiceAvanceProd;
    }

    public int[] getIndiceAvanceTiempo() {
        return indiceAvanceTiempo;
    }

    public void setIndiceAvanceTiempo(int[] indiceAvanceTiempo) {
        this.indiceAvanceTiempo = indiceAvanceTiempo;
    }

    public int getMultiCantFotos() {
        return multiCantFotos;
    }

    public void setMultiCantFotos(int multiCantFotos) {
        this.multiCantFotos = multiCantFotos;
    }

    public Date getMultiFechaActFotos() {
        return multiFechaActFotos;
    }

    public void setMultiFechaActFotos(Date multiFechaActFotos) {
        this.multiFechaActFotos = multiFechaActFotos;
    }

    public int getMultiCantVideos() {
        return multiCantVideos;
    }

    public void setMultiCantVideos(int multiCantVideos) {
        this.multiCantVideos = multiCantVideos;
    }

    public Date getMultiFechaActVideos() {
        return multiFechaActVideos;
    }

    public void setMultiFechaActVideos(Date multiFechaActVideos) {
        this.multiFechaActVideos = multiFechaActVideos;
    }

    public int getMultiCantCamaras() {
        return multiCantCamaras;
    }

    public void setMultiCantCamaras(int multiCantCamaras) {
        this.multiCantCamaras = multiCantCamaras;
    }

    public Date getMultiFechaActCamaras() {
        return multiFechaActCamaras;
    }

    public void setMultiFechaActCamaras(Date multiFechaActCamaras) {
        this.multiFechaActCamaras = multiFechaActCamaras;
    }

    public SofisComboG<CategoriaProyectos> getListaCategoriaCombo() {
        return listaCategoriaCombo;
    }

    public void setListaCategoriaCombo(SofisComboG<CategoriaProyectos> listaCategoriaCombo) {
        this.listaCategoriaCombo = listaCategoriaCombo;
    }

    public SofisComboG<CategoriaProyectos> getListaCategoriaSecCombo() {
        return listaCategoriaSecCombo;
    }

    public void setListaCategoriaSecCombo(SofisComboG<CategoriaProyectos> listaCategoriaSecCombo) {
        this.listaCategoriaSecCombo = listaCategoriaSecCombo;
    }

    public SofisComboG<OrganiIntProve> getListaInstEjecCombo() {
        return listaInstEjecCombo;
    }

    public void setListaInstEjecCombo(SofisComboG<OrganiIntProve> listaInstEjecCombo) {
        this.listaInstEjecCombo = listaInstEjecCombo;
    }

    public SofisComboG<OrganiIntProve> getListaContratistaCombo() {
        return listaContratistaCombo;
    }

    public void setListaContratistaCombo(SofisComboG<OrganiIntProve> listaContratistaCombo) {
        this.listaContratistaCombo = listaContratistaCombo;
    }

    public SofisComboG<Entregables> getListaInicioProdCombo() {
        return listaInicioProdCombo;
    }

    public void setListaInicioProdCombo(SofisComboG<Entregables> listaInicioProdCombo) {
        this.listaInicioProdCombo = listaInicioProdCombo;
    }

    public List<String> getListaCategoriaSecundaria() {
        return listaCategoriaSecundaria;
    }

    public void setListaCategoriaSecundaria(List<String> listaCategoriaSecundaria) {
        this.listaCategoriaSecundaria = listaCategoriaSecundaria;
    }

    public boolean getFrameMax() {
        return isFrameMax();
    }

    public boolean isFrameMax() {
        return frameMax;
    }

    public void setFrameMax(boolean frameMax) {
        this.frameMax = frameMax;
    }

    public int getPagoEliminar() {
        return pagoEliminar;
    }

    public void setPagoEliminar(int pagoEliminar) {
        this.pagoEliminar = pagoEliminar;
    }

    public int getPagoConfirmar() {
        return pagoConfirmar;
    }

    public void setPagoConfirmar(int pagoConfirmar) {
        this.pagoConfirmar = pagoConfirmar;
    }

    public int getAdqEliminar() {
        return adqEliminar;
    }

    public void setAdqEliminar(int adqEliminar) {
        this.adqEliminar = adqEliminar;
    }

    public String plantillaCroPopup() {
        renderPopupPlantillaCro = true;
        plantillaCroList = plantillaCronogramaDelegate.buscarPorFiltro(null, inicioMB.getOrganismo().getOrgPk());
        if (CollectionsUtils.isNotEmpty(plantillaCroList)) {
            plantillaCroListCombo = new SofisCombo((List) plantillaCroList, "pcronoNombre");
            plantillaCroListCombo.setSelectedObject(plantillaCroList.get(0));
        }
        return null;
    }

    public void cerrarPopupMapaUbicacion() {
        renderPopupMapaUbicacion = false;

    }

    public void guardarMapaUbicacion() {
        renderPopupMapaUbicacion = false;
    }

    public void cerrarPlantillaCroPopup() {
        renderPopupPlantillaCro = false;
        plantillaCro = null;
        plantillaFechaInicio = null;

        /*
            *   07-06-2018 Nico: Se agrega este control para poder cargar el template desde el 
            *        programa cuando se abre por primera vez el popup de plantilla cronograma y 
            *        luego se le da cerrar.
         */
        if (primeraCargarGantt) {
            cargarFrameCronograma(true);
            primeraCargarGantt = false;
        }

    }

    public String generarCroDesdePlantilla() {
        plantillaCro = (PlantillaCronograma) plantillaCroListCombo.getSelectedObject();
        if (plantillaCro == null) {
            JSFUtils.agregarMsgError("ganttForm", MensajesNegocio.ERROR_CRO_PLANTILLA_OBTENER, null);
            return null;
        }
        try {

            Object progProy = plantillaCronogramaDelegate.generarCroDesdePlantilla(fichaTO.getFichaFk(), plantillaCro, plantillaFechaInicio, inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());

            if (progProy == null) {
                JSFUtils.agregarMsg("ganttForm", "error_cro_guardar", null);
            } else {
                actualizarFichaTO(progProy);

                if (primeraCargarGantt) {
                    if (recienCreado) {
                        cargarFrameCronograma(false);
                    } else {
                        cargarFrameCronograma(true);
                    }
                } else {
                    cargarFrameCronograma(false);
                }
                primeraCargarGantt = false;

                renderPopupPlantillaCro = false;
                JSFUtils.agregarMsg("ganttForm", "info_cronograma_guardado", null);
            }

        } catch (BusinessException be) {
            logger.log(Level.SEVERE, null, be);
            //JSFUtils.agregarMsgs(PLANTILLA_CRO_MSG_ID, be.getErrores());

            /*
                        *   14-06-2018 Nico: Se cambia la forma de tomar los mensajes de errores para tomar el último, dado que es el más
                        *           ilustrativo, y además para no generar una gran cantidad de mensajes en la pantalla.
             */
            JSFUtils.agregarMsgError(PLANTILLA_CRO_MSG_ID, Labels.getValue(be.getErrores().get(be.getErrores().size() - 1)), null);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            JSFUtils.agregarMsgError("ganttForm", MensajesNegocio.ERROR_CRO_PLANTILLA_OBTENER, null);
        }

        return null;
    }

    /**
     * Redirige al servidor de GRP según la url.
     *
     * @return
     */
    public String linkToGRP() {
        String grpUrl = ConfigApp.getValue("grp_url");
        if (!StringsUtils.isEmpty(grpUrl)) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(grpUrl);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
                JSFUtils.agregarMsg(FICHA_MSG_ID, "error_ficha_link_grp", null);
            }
        }
        return null;
    }

    public String editarDevengado() {
        if (editDev) {
            editDev = false;
            if (fichaTO.getTipoFicha().equals(TipoFichaEnum.PROYECTO.id)) {
                listAdqDev = adquisicionDelegate.obtenerAdqDevPorProy(fichaTO.getFichaFk());
                listAdqDev = cargarDevMesAux(listAdqDev);
            }
        } else {
            editDev = true;
        }

        listAdqDev = cargarDevMesAux(listAdqDev);
        devengadoMsgs = true;

        return null;
    }

    public String guardarDevengado() {
        try {
            Integer fichaFk = fichaTO.getFichaFk();
            Integer tipoFicha = fichaTO.getTipoFicha();
            SsUsuario usu = inicioMB.getUsuario();
            Integer orgPk = inicioMB.getOrganismo().getOrgPk();

            for (Adquisicion adq : listAdqDev) {
                for (Devengado dev : adq.getListDevAux()) {
                    if (dev.getDevPk() == null
                            && !(dev.getDevPlan() == null && dev.getDevReal() == null)
                            && ((dev.getDevPlan() != null && dev.getDevPlan() > 0)
                            || (dev.getDevReal() != null && dev.getDevReal() > 0))) {
                        adq.getDevengadoList().add(dev);
                    }
                }
                adq = adquisicionDelegate.guardarAdquisicion(adq, fichaFk, tipoFicha, usu, orgPk);
            }

            JSFUtils.agregarMsg("presupuestoListMsg", "info_devengado_guardado", null);
            editDev = false;
            listAdqDev = cargarDevMesAux(null);

            inicioMB.setRenderPopupMensajes(Boolean.TRUE);

            devengadoMsgs = false;

        } catch (BusinessException be) {
            logger.log(Level.SEVERE, be.getMessage());

            for (String iterStr : be.getErrores()) {
                JSFUtils.agregarMsgError("", Labels.getValue(iterStr), null);
            }
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        }
        return null;
    }

    private List<Adquisicion> cargarDevMesAux(List<Adquisicion> listAdq) {
        if (listAdq == null) {
            listAdq = adquisicionDelegate.obtenerAdqDevPorProy(fichaTO.getFichaFk());
        }
        if (CollectionsUtils.isNotEmpty(listAdq)) {
            int year = anioDev != null && anioDev > 0 ? anioDev : new GregorianCalendar().get(Calendar.YEAR);

            for (Adquisicion adq : listAdq) {
                Devengado[] devMesArr = new Devengado[12];
                for (Devengado d : adq.getDevengadoList()) {
                    if (d.getDevAnio() == year && (d.getDevMes() >= 1 && d.getDevMes() <= 12)) {
                        d = editarMesDev(d);
                        devMesArr[d.getDevMes() - 1] = d;
                    }
                }
                for (int i = 0; i < devMesArr.length; i++) {
                    if (devMesArr[i] == null) {
                        Devengado dev = new Devengado();
                        dev.setDevAdqFk(adq);
                        dev.setDevAnio((short) year);
                        dev.setDevMes((short) (i + 1));
                        dev.setDevPlan(null);
                        dev.setDevReal(null);
                        dev = editarMesDev(dev);
                        devMesArr[i] = dev;
                    }
                }
                adq.setListDevAux(Arrays.asList(devMesArr));
            }
        }
        return listAdq;
    }

    private Devengado editarMesDev(Devengado d) {
        if (editDev) {
            boolean isGerente = SsUsuariosUtils.isUsuarioGerenteOAdjuntoFicha(fichaTO, inicioMB.getUsuario());
            boolean isPMOT = inicioMB.isUsuarioOrgaPMOT();
            boolean isPMOF = SsUsuariosUtils.isUsuarioPMOF(fichaTO, inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
            Calendar cal = new GregorianCalendar();
            boolean isInicioOPlan = fichaTO.getEstado().isEstado(Estados.ESTADOS.INICIO.estado_id)
                    || fichaTO.getEstado().isEstado(Estados.ESTADOS.PLANIFICACION.estado_id);
            boolean isEjecucion = fichaTO.getEstado().isEstado(Estados.ESTADOS.EJECUCION.estado_id);

            if (isGerente
                    && (d.getDevAnio() > cal.get(Calendar.YEAR)
                    || (d.getDevAnio() == cal.get(Calendar.YEAR) && (d.getDevMes() >= cal.get(Calendar.MONTH) + 1
                    || d.getDevMes() + 1 == cal.get(Calendar.MONTH) + 1))
                    || (d.getDevAnio() < cal.get(Calendar.YEAR) && d.getDevMes() == 12))) {
                if (isInicioOPlan) {
                    d.setEditarPlan(true);
                } else if (isEjecucion) {
                    d.setEditarReal(true);
                }
            } else if ((isPMOT || isPMOF)
                    && (d.getDevAnio() < cal.get(Calendar.YEAR)
                    || (d.getDevAnio() == cal.get(Calendar.YEAR) && d.getDevMes() < cal.get(Calendar.MONTH) + 1))) {
                if (isInicioOPlan) {
                    d.setEditarPlan(true);
                } else if (isEjecucion) {
                    d.setEditarReal(true);
                }
            }
        } else {
            d.setEditarPlan(false);
            d.setEditarReal(false);
        }
        return d;
    }

    public String retrocederAnioDevengado() {
        agregarDevNuevos();
        anioDev = anioDev - 1;
        listAdqDev = cargarDevMesAux(listAdqDev);

        return null;
    }

    public String avanzarAnioDevengado() {
        agregarDevNuevos();
        anioDev = anioDev + 1;
        listAdqDev = cargarDevMesAux(listAdqDev);

        return null;
    }

    /**
     * Busca los nuevos devengados del año actual y los copia en la lista
     * original. Usado cuando se pasa de un año a otro.
     */
    private void agregarDevNuevos() {
        List<Devengado> listTmpDev = new ArrayList<Devengado>();
        for (Adquisicion adq : listAdqDev) {
            for (Devengado dev : adq.getListDevAux()) {
                if (dev.getDevPk() == null
                        && ((dev.getDevPlan() != null && dev.getDevPlan() > 0)
                        || (dev.getDevReal() != null && dev.getDevReal() > 0))) {
                    listTmpDev.add(dev);
                }
            }
            for (Devengado devNew : listTmpDev) {
                boolean cargado = false;
                for (Devengado dev : adq.getDevengadoList()) {
                    if (devNew.getDevMes() == dev.getDevMes()
                            && devNew.getDevAnio() == dev.getDevAnio()) {
                        cargado = true;
                    }
                }
                if (!cargado) {
                    adq.getDevengadoList().add(devNew);
                }
            }
        }
    }

    public String agregarAdqDevPopup() {
        renderAdqDevPopup.abrir();
        listAdq = adquisicionDelegate.obtenerAdquisicionPorProy(fichaTO.getFichaFk());
        if (listAdq != null) {
            List<Adquisicion> listAdqAux = new ArrayList<Adquisicion>();
            for (Adquisicion adq : listAdq) {
                if (!listAdqDev.contains(adq)) {
                    listAdqAux.add(adq);
                }
            }
            listAdqCombo = new SofisCombo((List) listAdqAux, "adqNombre");
        }
        return null;
    }

    public String guardarAdqDev() {
        Adquisicion adq = (Adquisicion) listAdqCombo.getSelectedObject();
        if (adq != null) {
            Devengado dev = new Devengado();
            dev.setDevAdqFk(adq);
            Calendar cal = new GregorianCalendar();
            dev.setDevMes((short) cal.get(Calendar.MONTH));
            dev.setDevAnio((short) cal.get(Calendar.YEAR));
            adq.getDevengadoList().add(dev);
        }
        listAdqDev.add(adq);
        listAdqDev = cargarDevMesAux(listAdqDev);
        renderAdqDevPopup.cerrar();

        return null;
    }

    public String cancelarAdqDevPopup() {
        renderAdqDevPopup.cerrar();
        return null;
    }

    public String eliminarAdqDev(Adquisicion adq) {
        if (adq != null) {
            try {
                devengadoDelegate.eliminarDevPorAdqPk(adq.getAdqPk());
            } catch (BusinessException be) {
                logger.log(Level.SEVERE, be.getMessage(), be);
                JSFUtils.agregarMsgs(DEV_MSG_ID, be.getErrores());
            }
            listAdqDev = cargarDevMesAux(null);
        }
        return null;
    }

    public List<MonedaImporteResumenTO> getListaParticipanteResumenMonedaConsolidado() {
        return listaParticipanteResumenMonedaConsolidado;
    }

    public void setListaParticipanteResumenMonedaConsolidado(List<MonedaImporteResumenTO> listaParticipanteResumenMonedaConsolidado) {
        this.listaParticipanteResumenMonedaConsolidado = listaParticipanteResumenMonedaConsolidado;
    }

    public boolean editarRealProd(ProdMes prodMes, Entregables ent) {
        Calendar today = new GregorianCalendar();
        today.add(Calendar.MONTH, -1);

        Calendar calMes = new GregorianCalendar();
        calMes.set(Calendar.YEAR, prodMes.getProdmesAnio());
        calMes.set(Calendar.MONTH, prodMes.getProdmesMes() - 1);
        calMes.set(Calendar.DAY_OF_MONTH, 1);

        GregorianCalendar cEntInicioDate = new GregorianCalendar();
        cEntInicioDate.setTime(ent.getEntInicioDate());
        cEntInicioDate.add(Calendar.MONTH, -1);

        boolean esEntreFechas1 = DatesUtils.esEntreFechas(calMes.getTime(), cEntInicioDate.getTime(), ent.getEntFinDate(), "yyyyMM");
        boolean esEntreFechas2 = DatesUtils.esEntreFechas(calMes.getTime(), today.getTime(), ent.getEntFinDate(), "yyyyMM");

        return esEntreFechas1 && esEntreFechas2;
    }

    public String preFechaRealColor(Date fechaR, Boolean confirmado) {
        if (confirmado == null || !confirmado) {
            Calendar cal5 = new GregorianCalendar();
            cal5.add(Calendar.DATE, 5);
            Calendar calNow = new GregorianCalendar();

            if (DatesUtils.esMayor(calNow.getTime(), fechaR)) {
                return "#FF0000"; //Rojo
            } else if (DatesUtils.esMayor(cal5.getTime(), fechaR) || DatesUtils.fechasIguales(cal5.getTime(), fechaR)) {
                return "#FDC634"; //Amarillo
            }
        }
        return "";
    }

    public String otrosDatosPopupAction() {
        renderPopupOtrosDatos = true;
        Integer orgPk = inicioMB.getOrganismo().getOrgPk();

        ProyOtrosDatos pod = fichaTO.getOtrosDatos();
        if (pod == null) {
            pod = new ProyOtrosDatos();
            fichaTO.setOtrosDatos(pod);
        }

        List<CategoriaProyectos> listCPPrimarias = categoriaProyectosDelegate.obtenerPrimarias(orgPk);
        if (listCPPrimarias != null) {
            listaCategoriaCombo = new SofisComboG<>(listCPPrimarias, "catProyNombre");
            listaCategoriaCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
            if (pod.getProyOtrCatFk() != null) {
                listaCategoriaCombo.setSelectedT(pod.getProyOtrCatFk());
            }
        }
        List<CategoriaProyectos> listCPSecundarias = categoriaProyectosDelegate.obtenerSecundarias(orgPk);
        if (listCPSecundarias != null) {
            listaCategoriaSecCombo = new SofisComboG<>(listCPSecundarias, "catProyNombre");
        }

        if (pod.getProyOtrosCatSecundarias() != null) {
            listaCategoriaSecundaria.clear();
            for (CategoriaProyectos cat : pod.getProyOtrosCatSecundarias()) {
                listaCategoriaSecundaria.add(cat.getCatProyPk().toString());
            }
        }

        List<OrganiIntProve> listInstEjec = aplicacionMB.obtenerOrganiIntNoProveedores(orgPk);
        
        listInstEjec = OrganiIntProveUtils.filtrarHabilitadosYOrdenarPorNombre(listInstEjec, pod.getProyOtrInsEjeFk());
        listaInstEjecCombo = new SofisComboG<>(listInstEjec, "orgaNombre");
        listaInstEjecCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
       
        if (pod.getProyOtrInsEjeFk() != null) {
            listaInstEjecCombo.setSelectedT(pod.getProyOtrInsEjeFk());
        }

        List<OrganiIntProve> listContra = aplicacionMB.obtenerOrganiIntProveedores(orgPk);
        listContra = OrganiIntProveUtils.filtrarHabilitadosYOrdenarPorNombre(listContra, pod.getProyOtrContFk());
        
        listContra = OrganiIntProveUtils.sortByNombre(listContra);
        listaContratistaCombo = new SofisComboG<>(listContra, "orgaNombre");
        listaContratistaCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        
        if (pod.getProyOtrContFk() != null) {
            listaContratistaCombo.setSelectedT(pod.getProyOtrContFk());
        }

        if (fichaTO.isProyecto()) {
            List<Entregables> listEnt = entregablesDelegate.obtenerEntPorProyPk(fichaTO.getFichaFk());

            if (listEnt == null) {
                listEnt = new ArrayList<>();
            }
            listEnt = EntregablesUtils.cargarCamposCombos(listEnt);
            listaInicioProdCombo = new SofisComboG<>(listEnt, "fechaNivelNombreCombo");
            listaInicioProdCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
            if (pod != null && pod.getProyOtrEntFk() != null) {
                listaInicioProdCombo.setSelectedT(pod.getProyOtrEntFk());
            }
        }

        return null;
    }

    public String cerrarPopupOtrosDatos() {
        renderPopupOtrosDatos = false;
        return null;
    }

    public void replanificacionGenerarLineaBaseChange(ValueChangeEvent ev) {
        replanificacion.setProyreplanGenerarLineaBase((Boolean) ev.getNewValue());
        if (!replanificacion.isProyreplanGenerarLineaBase()) {
            replanificacion.setProyreplanHistorial(false);
            replanificacion.setProyreplanDesc("");
            replanificacion.setProyreplanGenerarPresupuesto(false);
            replanificacion.setProyreplanGenerarProducto(false);
            replanificacion.setProyreplanPermitEditar(false);
            replanificacion.setProyreplanDesc("");
        }
    }

    public void replanificacionProyreplanHistorialChange(ValueChangeEvent ev) {
        replanificacion.setProyreplanHistorial((Boolean) ev.getNewValue());
        if (!replanificacion.getProyreplanHistorial()) {
            replanificacion.setProyreplanDesc("");
        }
    }

    public String guardarOtrosDatos() {
        CategoriaProyectos cp = listaCategoriaCombo.getSelectedT();
        OrganiIntProve instEjec = listaInstEjecCombo.getSelectedT();
        OrganiIntProve contratista = listaContratistaCombo.getSelectedT();
        Entregables ent = listaInicioProdCombo.getSelectedT();

        ProyOtrosDatos pod = fichaTO.getOtrosDatos();
        if (pod == null) {
            pod = new ProyOtrosDatos();
        }
        pod.setProyOtrCatFk(cp);
        pod.setProyOtrInsEjeFk(instEjec);
        pod.setProyOtrContFk(contratista);
        pod.setProyOtrEntFk(ent);
        if (ent != null) {
            pod.setProyOtrPlazo(ent.getEntDuracion());
        } else {
            pod.setProyOtrPlazo(null);
        }

        if (listaCategoriaSecundaria != null) {
            if (pod.getProyOtrosCatSecundarias() == null) {
                pod.setProyOtrosCatSecundarias(new ArrayList<CategoriaProyectos>());
            }
            pod.getProyOtrosCatSecundarias().clear();
            for (String cat : listaCategoriaSecundaria) {
                CategoriaProyectos cpSec = categoriaProyectosDelegate.obtenerPorId(Integer.valueOf(cat));
                pod.getProyOtrosCatSecundarias().add(cpSec);
            }
        }

        if (fichaTO.getFichaFk() != null) {
            guardarFichaAction();
        }

        cargarOtrosDatos();
        cerrarPopupOtrosDatos();
        cerrarFormCollapsable();
        return null;
    }

    public String estadoPublicacionStr() {
        if (fichaTO.getOtrosDatos() != null
                && fichaTO.getOtrosDatos().getProyOtrEstPubFk() != null) {
            EstadosPublicacion ep = fichaTO.getOtrosDatos().getProyOtrEstPubFk();
            String estado = Labels.getValue("estpub_" + ep.getEstPubCodigo().toLowerCase());
            Date ultima = null;
            StringBuilder sb = new StringBuilder();
            sb.append(estado);
            if (ep.getEstPubCodigo().equalsIgnoreCase(EstadoPublicacionCodigos.PUBLICADO)) {
                ultima = proyPublicaHistDelegate.obtenerUltimaFecha(fichaTO.getFichaFk());
                if (ultima != null) {
                    sb.append(" ").append(DatesUtils.toStringFormat(ultima, "dd/MM/yyyy HH:mm:ss"));
                }
            }
            return sb.toString();
        }
        return null;
    }

    public String estadoPublicacionPendientesStr() {
        String estado = Labels.getValue("estpub_pendientes");
        if (!StringsUtils.isEmpty(camposPendientesPublicacion())) {
            return estado + ": " + camposPendientesPublicacion();
        }
        return null;
    }

    private String camposPendientesPublicacion() {
        if (fichaTO != null) {
            StringBuilder sb = new StringBuilder();

            if (StringsUtils.isEmpty(fichaTO.getNombre())) {
                sb.append(sb.length() > 0 ? ", " : "");
                sb.append(Labels.getValue("nombreProgProy"));
            }
            if (StringsUtils.isEmpty(fichaTO.getDescripcion())) {
                sb.append(sb.length() > 0 ? ", " : "");
                sb.append(Labels.getValue("descFicha"));
            }
            if (StringsUtils.isEmpty(fichaTO.getObjetivo())) {
                sb.append(sb.length() > 0 ? ", " : "");
                sb.append(Labels.getValue("objFicha"));
            }
            if (StringsUtils.isEmpty(fichaTO.getObjPublico())) {
                sb.append(sb.length() > 0 ? ", " : "");
                sb.append(Labels.getValue("objPublicoFicha"));
            }

            if (fichaTO.getLatLngProyList() == null || fichaTO.getLatLngProyList().isEmpty()) {
                sb.append(sb.length() > 0 ? ", " : "");
                sb.append(Labels.getValue("ficha_ubicacion"));
            }

            /**
             * 15-12-2016 FIX: Si se seleccionaba "Categoría" no verificaba
             * "Estado del Producto"
             */
            ProyOtrosDatos proyOtros = fichaTO.getOtrosDatos();
            if (proyOtros != null) {
                if (proyOtros.getProyOtrCatFk() == null) {
                    sb.append(sb.length() > 0 ? ", " : "");
                    sb.append(Labels.getValue("ficha_otros_datos_cat_principal"));
                }
                if (proyOtros.getProyOtrEtaFk() == null || proyOtros.getProyOtrEtaFk().getEtaPk() == null) {
                    sb.append(sb.length() > 0 ? ", " : "");
                    sb.append(Labels.getValue("ficha_estado_producto"));
                }
            }

            return sb.toString();
        }
        return "";
    }

    public String resumenPendientesPublicar() {
        return "Hay fotos pendientes de publicar en el visualizador.";
    }

    public void tipoMediaValueChangeListener(ValueChangeEvent event) {
        PhaseId phaseId = event.getPhaseId();

        if (phaseId.equals(PhaseId.ANY_PHASE)) {
            event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
            event.queue();
        } else if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES)) {
            tipoMediaSelected = listaTipoMediaCombo.getSelectedT();
        }
    }

    public Integer deptoToCodificacionINE(String deptoGoogle) {
        deptoGoogle = StringsUtils.removeTildes(deptoGoogle);
        if (deptoGoogle.toLowerCase().contains("montevideo")) {
            return 1;
        }
        if (deptoGoogle.toLowerCase().contains("artigas")) {
            return 2;
        }
        if (deptoGoogle.toLowerCase().contains("canelones")) {
            return 3;
        }
        if (deptoGoogle.toLowerCase().contains("cerro largo")) {
            return 4;
        }
        if (deptoGoogle.toLowerCase().contains("colonia")) {
            return 5;
        }
        if (deptoGoogle.toLowerCase().contains("durazno")) {
            return 6;
        }
        if (deptoGoogle.toLowerCase().contains("flores")) {
            return 7;
        }
        if (deptoGoogle.toLowerCase().contains("florida")) {
            return 8;
        }
        if (deptoGoogle.toLowerCase().contains("lavalleja")) {
            return 9;
        }
        if (deptoGoogle.toLowerCase().contains("maldonado")) {
            return 10;
        }
        if (deptoGoogle.toLowerCase().contains("paysandu")) {
            return 11;
        }
        if (deptoGoogle.toLowerCase().contains("rio negro")) {
            return 12;
        }
        if (deptoGoogle.toLowerCase().contains("rivera")) {
            return 13;
        }
        if (deptoGoogle.toLowerCase().contains("rocha")) {
            return 14;
        }
        if (deptoGoogle.toLowerCase().contains("salto")) {
            return 15;
        }
        if (deptoGoogle.toLowerCase().contains("san jose")) {
            return 16;
        }
        if (deptoGoogle.toLowerCase().contains("soriano")) {
            return 17;
        }
        if (deptoGoogle.toLowerCase().contains("tacuarembo")) {
            return 18;
        }
        if (deptoGoogle.toLowerCase().contains("treinta y tres")) {
            return 19;
        }
        return 0;
    }

    /**
     * 1-Azul, 2-Verde, 3-Amarillo, 4-Rojo.
     *
     * @param ind
     * @return
     */
    public String productosIndiceColor(Integer ind) {
        if (ind != null) {
            String result = null;
            switch (ind) {
                case 1:
                    result = ConstantesEstandares.SEMAFORO_AZUL;
                    break;
                case 2:
                    result = ConstantesEstandares.SEMAFORO_VERDE;
                    break;
                case 3:
                    result = ConstantesEstandares.SEMAFORO_AMARILLO;
                    break;
                case 4:
                    result = ConstantesEstandares.SEMAFORO_ROJO;
                    break;
            }

            return result;
        }
        return null;
    }

    /**
     * 1-Azul, 2-Verde, 3-Amarillo, 4-Rojo.
     *
     * @param ind
     * @param actualizar
     * @return String
     */
    public String productosIndiceCantYPorc(Integer ind) {
        return productosIndiceCantYPorc(ind, false);
    }

    public String productosIndiceCantYPorc(Integer ind, Boolean actualizar) {
        if (ind != null) {
            int cant = 0;
            String desc = null;
            int porc = 0;

            if (mapProdRango == null || (actualizar != null && actualizar)) {
                Integer orgPk = inicioMB.getOrganismo().getOrgPk();
                limiteAmarilloProd = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_AMARILLO, orgPk).getCnfValor());
                limiteRojoProd = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_ROJO, orgPk).getCnfValor());

                mapProdRango = productosDelegate.cantProdPorRango(fichaTO.getFichaFk(), limiteAmarilloProd, limiteRojoProd, orgPk);
            }
            Integer cantRango;
            Integer porcRango;

            switch (ind) {
                case 1:
                    desc = Labels.getValue("prod_resumen_finalizado");
                    cantRango = mapProdRango.get(ConstantesLogica.PROD_CANT_AZUL);
                    cant = cantRango != null ? cantRango : 0;
                    porcRango = mapProdRango.get(ConstantesLogica.PROD_PORC_AZUL);
                    porc = porcRango != null ? porcRango : 0;
                    break;
                case 2:
                    desc = Labels.getValue("prod_resumen_correctos");
                    cantRango = mapProdRango.get(ConstantesLogica.PROD_CANT_VERDE);
                    cant = cantRango != null ? cantRango : 0;
                    porcRango = mapProdRango.get(ConstantesLogica.PROD_PORC_VERDE);
                    porc = porcRango != null ? porcRango : 0;
                    break;
                case 3:
                    desc = Labels.getValue("prod_resumen_leve_atraso");
                    cantRango = mapProdRango.get(ConstantesLogica.PROD_CANT_AMARILLO);
                    cant = cantRango != null ? cantRango : 0;
                    porcRango = mapProdRango.get(ConstantesLogica.PROD_PORC_AMARILLO);
                    porc = porcRango != null ? porcRango : 0;
                    break;
                case 4:
                    desc = Labels.getValue("prod_resumen_atrasado");
                    cantRango = mapProdRango.get(ConstantesLogica.PROD_CANT_ROJO);
                    cant = cantRango != null ? cantRango : 0;
                    porcRango = mapProdRango.get(ConstantesLogica.PROD_PORC_ROJO);
                    porc = porcRango != null ? porcRango : 0;
                    break;
            }

            /**
             * 14-12-2016: MEJORA En la ficha de proyecto, en el recuadro de
             * producto siembre debería aparecer un semáforo para cada color y
             * mostrar la cantidad de productos y en porcentaje para cada caso.
             */
            return StringsUtils.concat(String.valueOf(cant), " - ", desc, " (", String.valueOf(porc), "%)");
        }
        return null;
    }

    public String otrosDatCatSecundariaStr() {
        if (fichaTO != null && fichaTO.getOtrosDatos() != null && fichaTO.getOtrosDatos().getProyOtrosCatSecundarias() != null) {
            StringBuilder result = new StringBuilder();
            boolean primero = true;
            for (CategoriaProyectos cat : fichaTO.getOtrosDatos().getProyOtrosCatSecundarias()) {
                result.append(primero ? "" : ", ").append(cat.getCatProyNombre().trim());
                primero = false;
            }
            return result.append(!primero ? "." : "").toString();
        }
        return null;
    }

    public String recortarTexto(String txt, int largo) {
        return StringsUtils.recortarTexto(txt, largo);
    }

    public boolean isMisTareas() {
        return misTareas;
    }

    public void setMisTareas(boolean misTareas) {
        this.misTareas = misTareas;
    }

    /**
     * @return the metodologiaTipoDocumentoRequeridoDesde
     */
    public List<SelectItem> getMetodologiaTipoDocumentoRequeridoDesde() {
        return metodologiaTipoDocumentoRequeridoDesde;
    }

    /**
     * @param metodologiaTipoDocumentoRequeridoDesde the
     * metodologiaTipoDocumentoRequeridoDesde to set
     */
    public void setMetodologiaTipoDocumentoRequeridoDesde(List<SelectItem> metodologiaTipoDocumentoRequeridoDesde) {
        this.metodologiaTipoDocumentoRequeridoDesde = metodologiaTipoDocumentoRequeridoDesde;
    }

    /**
     * @return the listaObjetivosEstrategicosCombo
     */
    public SofisComboG<ObjetivoEstrategico> getListaObjetivosEstrategicosCombo() {
        return listaObjetivosEstrategicosCombo;
    }

    /**
     * @param listaObjetivosEstrategicosCombo the
     * listaObjetivosEstrategicosCombo to set
     */
    public void setListaObjetivosEstrategicosCombo(SofisComboG<ObjetivoEstrategico> listaObjetivosEstrategicosCombo) {
        this.listaObjetivosEstrategicosCombo = listaObjetivosEstrategicosCombo;
    }

    /**
     *
     * @return
     */
    public List<ComponenteProducto> getListaComponenteProducto() {
        return listaComponenteProducto;
    }

    /**
     *
     * @param listaComponenteProducto
     */
    public void setListaComponenteProducto(List<ComponenteProducto> listaComponenteProducto) {
        this.listaComponenteProducto = listaComponenteProducto;
    }

    /**
     *
     * @return
     */
    public SofisCombo getListaComponenteProductoCombo() {
        return listaComponenteProductoCombo;
    }

    /**
     *
     * @param listaComponenteProductoCombo
     */
    public void setListaComponenteProductoCombo(SofisCombo listaComponenteProductoCombo) {
        this.listaComponenteProductoCombo = listaComponenteProductoCombo;
    }

    /**
     * @return the renderPopupDocumentoHistorico
     */
    public Boolean getRenderPopupDocumentoHistorico() {
        return renderPopupDocumentoHistorico;
    }

    /**
     * @param renderPopupDocumentoHistorico the renderPopupDocumentoHistorico to
     * set
     */
    public void setRenderPopupDocumentoHistorico(Boolean renderPopupDocumentoHistorico) {
        this.renderPopupDocumentoHistorico = renderPopupDocumentoHistorico;
    }

    /**
     * @return the docHistDownloadFile
     */
    public Map<Integer, SofisResource> getDocHistDownloadFile() {
        return docHistDownloadFile;
    }

    /**
     * @param docHistDownloadFile the docHistDownloadFile to set
     */
    public void setDocHistDownloadFile(Map<Integer, SofisResource> docHistDownloadFile) {
        this.docHistDownloadFile = docHistDownloadFile;
    }

    private Object getFlashContext(String attName) {
        return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(attName);
    }

    public void setAplicacionMB(AplicacionMB aplicacionMB) {
        this.aplicacionMB = aplicacionMB;
    }

    // ======================================================================================================================
    // Carga masiva de tareas del Gantt desde archivo
    private boolean cargaDesdeArchivo = false;

    public boolean getCargaDesdeArchivo() {
        return cargaDesdeArchivo;
    }

    public boolean habilitarCargaDesdeArchivo() {
        /* 15-03-2018 Nico: Dejo comentada esta línea para no mostrar el campo de cargar datos desde archivo 
         */
        //return fichaTO.isEstado(Estados.ESTADOS.INICIO.estado_id) || fichaTO.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id);
        return false;
    }

    public void mostrarCargaDesdeArchivo() {

        if (TipoFichaEnum.PROGRAMA.getValue() == fichaTO.getTipoFicha()) {
            JSFUtils.agregarMsgError("ganttForm", "Esta funcionalidad solo está disponible para proyectos.", null);
            return;
        }

        Cronogramas cro = fichaTO.getCroFk();
        if (cro == null) {
            return;
        }

        //Solo se permite cargar datos de un archivo si sus entregables no tienen avance (avance mayor a 0) y no estén asociados a productos, 
        //interesados, colaboradores, calidad, riesgos o pagos
        for (Entregables entregable : cro.getEntregablesSet()) {
            if ((entregable.getEntProgreso() != null && entregable.getEntProgreso() > 0)) {
                JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque al menos un entregable tiene avance mayor a cero.", null);
                return;
            }
        }

        //Solo se permite cargar datos de un archivo si el proyecto no tiene interesados registrados
        if (fichaTO.getInteresados() != null) {
            for (Interesados interesado : fichaTO.getInteresados()) {
                if (interesado.getIntEntregable() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque al menos un entregable está asociado a un interesado.", null);
                    return;
                }
            }
        }

        //Solo se permite cargar datos de un archivo si el proyecto no tiene participantes registrados
        if (fichaTO.getParticipantes() != null) {
            for (Participantes participante : fichaTO.getParticipantes()) {
                if (participante.getPartEntregablesFk() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque al menos un entregable está asociado a un participante.", null);
                    return;
                }
            }
        }

        //Solo se permite cargar datos de un archivo si el proyecto no tiene registros de calidad
        if (fichaTO.getCalidadList() != null) {
            for (Calidad calidad : fichaTO.getCalidadList()) {
                if (calidad.getCalEntFk() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque al menos un entregable está asociado a una calidad.", null);
                    return;
                }
            }
        }

        //Solo se permite cargar datos de un archivo si el proyecto no tiene riesgos registrados
        if (fichaTO.getRiesgos() != null) {
            for (Riesgos riesgo : fichaTO.getRiesgos()) {
                if (riesgo.getRiskEntregable() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque al menos un entregable está asociado a un riesgo.", null);
                    return;
                }
            }
        }

        //Solo se permite cargar datos de un archivo si el proyecto no tiene documentos registrados
        if (fichaTO.getDocumentos() != null) {
            for (Documentos documento : fichaTO.getDocumentos()) {
                if (documento.getDocsEntregable() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque al menos un entregable está asociado a un documento.", null);
                    return;
                } else {
                    if (documento.getDocsPagoFk() != null && documento.getDocsPagoFk().getEntregables() != null) {
                        JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque al menos un entregable está asociado a un pago.", null);
                        return;
                    }
                }
            }
        }

        //Solo se permite cargar datos de un archivo si el proyecto no tiene productos registrados
        Proyectos proyecto = proyectoDelegate.obtenerProyPorId(fichaTO.getFichaFk());
        if (proyecto != null) {
            List<Productos> productos = productosDelegate.obtenerProdPorProyPk(proyecto.getProyPk());
            if (productos != null) {
                for (Productos producto : productos) {
                    if (producto.getProdEntregableFk() != null) {
                        JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque al menos un entregable está asociado a un producto.", null);
                        return;
                    }
                }
            }
        }

        //Los pagos están siempre asociados a un entregable, por lo que si existe un pago no se pueden eliminar los entregables actuales
        List<AdqPagosTO> adqPagos = adquisicionDelegate.obtenerAdquisicionPagosList(fichaTO.getPreFk().getPrePk());
        if (adqPagos != null) {
            for (AdqPagosTO adqPago : adqPagos) {
                if (adqPago.getTipo() == 2) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque hay al menos un pago asociado a entregables.", null);
                    return;
                }
            }
        }

        //Si llegó hasta acá es porque pasó todas las validaciones
        cargaDesdeArchivo = true;
    }

    public void cancelarCargaDesdeArchivo() {
        JSFUtils.removerMensages();
        cargarFrameCronograma(true);
        cargaDesdeArchivo = false;
    }

    private File planillaCargarEntregables;

    public void subirPlanillaCargaEntregablesAction(FileEntryEvent evento) {
        JSFUtils.removerMensages();
        try {
            planillaCargarEntregables = null;
            FileEntry fileEntry = (FileEntry) evento.getComponent();
            FileEntryResults results = fileEntry.getResults();
            planillaCargarEntregables = results.getFiles().get(0).getFile();
        } catch (Exception e2) {
            JSFUtils.agregarMsg("error_subir_archivo");
            logger.log(Level.SEVERE, e2.getMessage(), e2);
        }
    }

    public void importarEntregablesDesdeArchivo() {
        JSFUtils.removerMensages();
        if (planillaCargarEntregables == null) {
            JSFUtils.agregarMsgError("fileEntryPlanilla", "No ha seleccionado una planilla", null);
            return;
        }
        try {
            HSSFSheet hoja;
            try {
                //Abrir el archivo
                InputStream stream = new FileInputStream(planillaCargarEntregables);
                //Abrir el libro
                HSSFWorkbook libro = new HSSFWorkbook(stream);
                //Obtener la hoja 1
                hoja = libro.getSheetAt(0);
            } catch (Exception ex) {
                throw new BusinessException("No se puede acceder a la planilla indicada o la misma no tiene el formato correcto");
            }
            HSSFRow fila;
            //Iterar por las filas 
            Iterator rows = hoja.rowIterator();
            int nroFila = 0;
            List<Entregables> entregables = new LinkedList();
            boolean procesamientoTerminado = false;
            while (!procesamientoTerminado && rows.hasNext()) {
                nroFila++;
                fila = (HSSFRow) rows.next();
                if (nroFila == 1) {
                    //Saltear la primer fila (los encabezados)
                    continue;
                }
                //Si no hay valor en la columna 0 se asume que se terminaron los datos
                if (fila.getCell(0) == null || fila.getCell(0).toString().trim().isEmpty()) {
                    procesamientoTerminado = true;
                    continue;
                }
                Entregables entregable = new Entregables();
                //Columna 0: número (identificador del entregable)
                try {
                    entregable.setEntId((Double.valueOf(fila.getCell(0).toString().trim())).intValue());
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'número' en la fila " + nroFila + " no es correcto");
                }
                //Columna 1: nombre
                try {
                    entregable.setEntNombre(fila.getCell(1).toString().trim());
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'nombre' en la fila " + nroFila + " no es correcto");
                }
                //Columna 2: nivel
                try {
                    entregable.setEntNivel((Double.valueOf(fila.getCell(2).toString().trim())).intValue());
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'nivel' en la fila " + nroFila + " no es correcto");
                }
                //Columna 3: esfuerzo
                try {
                    entregable.setEntEsfuerzo((Double.valueOf(fila.getCell(3).toString().trim())).intValue());
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'esfuerzo' en la fila " + nroFila + " no es correcto");
                }
                //Columna 4: avance
                try {
                    entregable.setEntProgreso((Double.valueOf(fila.getCell(4).toString().trim())).intValue());
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'avance' en la fila " + nroFila + " no es correcto", ex);
                }
                //Columna 5: fecha de inicio
                try {
                    //A la fecha obtenida de la planilla se le suma 12 horas para que quede al mediodía
                    entregable.setEntInicio(fila.getCell(5).getDateCellValue().getTime() + 12 * 60 * 60 * 1000);
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'fecha de inicio' en la fila " + nroFila + " no es correcto");
                }
                //Columna 6: fecha de fin
                try {
                    //A la fecha obtenida de la planilla se le suma 12 horas para que quede al mediodía
                    entregable.setEntFin(fila.getCell(6).getDateCellValue().getTime() + 12 * 60 * 60 * 1000);
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'fecha de fin' en la fila " + nroFila + " no es correcto");
                }
                //Columna 7: fecha de inicio de línea base (no es requerida)
                try {
                    if (fila.getCell(7) != null && !fila.getCell(7).toString().trim().isEmpty()) {
                        //A la fecha obtenida de la planilla se le suma 12 horas para que quede al mediodía
                        entregable.setEntInicioLineaBase(fila.getCell(7).getDateCellValue().getTime() + 12 * 60 * 60 * 1000);
                    }
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'fecha de inicio de línea base' en la fila " + nroFila + " no es correcto");
                }
                //Columna 8: fecha de fin de línea base (no es requerida)
                try {
                    if (fila.getCell(8) != null && !fila.getCell(8).toString().trim().isEmpty()) {
                        //A la fecha obtenida de la planilla se le suma 12 horas para que quede al mediodía
                        entregable.setEntFinLineaBase(fila.getCell(8).getDateCellValue().getTime() + 12 * 60 * 60 * 1000);
                    }
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'fecha de fin de línea base' en la fila " + nroFila + " no es correcto");
                }
                //Columna 9: es hito
                try {
                    entregable.setEntInicioEsHito(Boolean.FALSE);
                    String value = fila.getCell(9).toString().trim();
                    if (value == null || !("SI".equalsIgnoreCase(value) || "NO".equalsIgnoreCase(value))) {
                        throw new BusinessException("El campo 'es hito' en la fila " + nroFila + " no es correcto");
                    }
                    entregable.setEntFinEsHito(fila.getCell(9).toString().equalsIgnoreCase("SI"));
                } catch (BusinessException ex) {
                    throw ex;
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'es hito' en la fila " + nroFila + " no es correcto");
                }
                //Columna 10: predecesores
                try {
                    if (fila.getCell(10) != null && !fila.getCell(10).toString().trim().isEmpty()) {
                        //Es necesario convertirla a String porque sino cuando hay un solo dígito lo toma como número y lo devuelve con decimales
                        fila.getCell(10).setCellType(HSSFCell.CELL_TYPE_STRING);
                        String value = fila.getCell(10).toString().trim();
                        entregable.setEntPredecesorFk(value);
                        entregable.setEntPredecesorDias(0);
                        //Si tiene predecesores configurados debe cumplir el patrón
                        if (entregable.getEntPredecesorFk() != null && !entregable.getEntPredecesorFk().isEmpty()
                                && !entregable.getEntPredecesorFk().matches(EntregablesValidacion.DEPENDENCIA_REGEX)) {
                            throw new BusinessException("El campo 'dependencia' en la fila " + nroFila + " no es correcto");
                        }
                    }
                } catch (BusinessException ex) {
                    throw ex;
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'dependencia' en la fila " + nroFila + " no es correcto");
                }
                //Columna 11: es clave PMO
                try {
                    String value = fila.getCell(11).toString().trim();
                    if (value == null || !("SI".equalsIgnoreCase(value) || "NO".equalsIgnoreCase(value))) {
                        throw new BusinessException("El campo 'es clave PMO' en la fila " + nroFila + " no es correcto");
                    }
                    entregable.setEntRelevante(value.equalsIgnoreCase("SI"));
                } catch (BusinessException ex) {
                    throw ex;
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'es clave PMO' en la fila " + nroFila + " no es correcto");
                }
                //Columna 12: coordinador
                String coordinador;
                try {
                    if (fila.getCell(12) != null && !fila.getCell(12).toString().trim().isEmpty()) {
                        coordinador = fila.getCell(12).toString();
                        //Si no es vacío debe ser una dirección de correo electrónico
                        if (!coordinador.trim().isEmpty() && !EmailValidator.validateEmail(coordinador)) {
                            throw new BusinessException("El campo 'coordinador' en la fila " + nroFila + " no es correcto");
                        }
                        if (coordinador.length() > 50) {
                            throw new BusinessException("El campo 'coordinador' en la fila " + nroFila + " tiene más de 50 caracteres.");
                        }
                        entregable.setCoordinadorUsuFk(ssUsuarioDelegate.obtenerSsUsuarioPorMail(coordinador));
                    }
                } catch (BusinessException ex) {
                    throw ex;
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'coordinador' en la fila " + nroFila + " no es correcto");
                }
                //Columna 13: observaciones
                try {
                    if (fila.getCell(13) != null && !fila.getCell(13).toString().trim().isEmpty()) {
                        entregable.setEntDescripcion(fila.getCell(13).toString());
                    }
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'observaciones' en la fila " + nroFila + " no es correcto");
                }
                //Otros campos que no vienen en la planilla
                entregable.setEntStatus("STATUS_ACTIVE");
                entregable.setEntCollapsed(Boolean.FALSE);
                entregable.setEntInicioProyecto(Boolean.FALSE);
                entregable.setEntFinProyecto(Boolean.FALSE);
                //Si es un hito se pone como fecha de inicio la misma que la fecha de fin
                if (entregable.getEntFinEsHito()) {
                    entregable.setEntInicio(entregable.getEntFin());
                }
                entregables.add(entregable);
                //Queda pendiente lo siguiente porque dependen de otros entregables (hay que hacerlo después de cargar todos los entregables):
                //1-Ajustar las fechas de inicio y fin, y las fechas de inicio y fin de la línea base
                //2-Completar los campos entParent, entDuracion, entDuracionLineaBase
            }
            //Ajustar las fechas de los entregables en base a los hijos y las dependencias
            entregables = EntregablesUtils.ajustarFechas(entregables);
            //Validar los datos y las fechas de los entregables
            EntregablesValidacion.validarEntregables(entregables);
            //Si llegó hasta acá se importaron y validaron todos los entregables
            fichaTO.getCroFk().getEntregablesSet().clear();
            for (Entregables entregable : entregables) {
                entregable.setEntCroFk(fichaTO.getCroFk());
                fichaTO.getCroFk().getEntregablesSet().add(entregable);
            }
            //Guardar el cronograma con todos los entregables
            Object progProy = cronogramaDelegate.guardarCronograma(fichaTO.getCroFk(), fichaTO.getFichaFk(), fichaTO.getTipoFicha(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
            if (progProy == null) {
                JSFUtils.agregarMsgError("ganttForm", "error_cro_guardar", null);
            } else {
                actualizarFichaTO(progProy);
                cargarFrameCronograma(true);
                JSFUtils.agregarMsg("ganttForm", "info_cronograma_guardado", null);
            }
            //Volver a la vista normal del cronograma
            cargaDesdeArchivo = false;
        } catch (BusinessException bEx) {
            logger.log(Level.SEVERE, bEx.getMessage());
            JSFUtils.agregarMsgError("fileEntryPlanilla", bEx.getMessage(), null);
        }
    }

    // ======================================================================================================================
    // Carga masiva de tareas del Gantt
    private boolean cargaPorFormulario = false;

    public boolean getCargaPorFormulario() {
        return cargaPorFormulario;
    }

    public boolean habilitarCargaPorFormulario() {
        return fichaTO.isEstado(Estados.ESTADOS.INICIO.estado_id) || fichaTO.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id);
    }

    /*
        * 13-03-18 Nico: Agregue "habilitarCargaDesdePlantilla" para poder desactivar el botón cuando no este en estado
        *           "Planificando" o "Inicial".
     */
    public boolean habilitarCargaDesdePlantilla() {
        return fichaTO.isEstado(Estados.ESTADOS.INICIO.estado_id) || fichaTO.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id);
    }

    public void mostrarCargaPorFormulario() {

        if (TipoFichaEnum.PROGRAMA.getValue() == fichaTO.getTipoFicha()) {
            JSFUtils.agregarMsgError("ganttForm", "Esta funcionalidad solo está disponible para proyectos.", null);
            return;
        }

        Cronogramas cro = fichaTO.getCroFk();
        if (cro == null) {
            return;
        }

        //Solo se permite cargar datos por formulario si sus entregables no tienen avance (avance mayor a 0) y no estén asociados a productos, 
        //interesados, colaboradores, calidad, riesgos o pagos
        for (Entregables entregable : cro.getEntregablesSet()) {
            if ((entregable.getEntProgreso() != null && entregable.getEntProgreso() > 0)) {
                JSFUtils.agregarMsgError("ganttForm", "No se permite la carga por formulario porque al menos un entregable tiene avance mayor a cero.", null);
                return;
            }
        }

        //Solo se permite cargar datos por formulario si el proyecto no tiene interesados registrados
        if (fichaTO.getInteresados() != null) {
            for (Interesados interesado : fichaTO.getInteresados()) {
                if (interesado.getIntEntregable() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga por formulario porque al menos un entregable está asociado a un interesado.", null);
                    return;
                }
            }
        }

        //Solo se permite cargar datos por formulario si el proyecto no tiene participantes registrados
        if (fichaTO.getParticipantes() != null) {
            for (Participantes participante : fichaTO.getParticipantes()) {
                if (participante.getPartEntregablesFk() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga por formulario porque al menos un entregable está asociado a un participante.", null);
                    return;
                }
            }
        }

        //Solo se permite cargar datos por formulario si el proyecto no tiene registros de calidad
        if (fichaTO.getCalidadList() != null) {
            for (Calidad calidad : fichaTO.getCalidadList()) {
                if (calidad.getCalEntFk() != null) {
                    JSFUtils.agregarMsgError(null, "No se permite la carga por formulario porque al menos un entregable está asociado a una calidad.", null);
                    return;
                }
            }
        }

        //Solo se permite cargar datos por formulario si el proyecto no tiene riesgos registrados
        if (fichaTO.getRiesgos() != null) {
            for (Riesgos riesgo : fichaTO.getRiesgos()) {
                if (riesgo.getRiskEntregable() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga por formulario porque al menos un entregable está asociado a un riesgo.", null);
                    return;
                }
            }
        }

        //Solo se permite cargar datos por formulario si el proyecto no tiene documentos registrados
        if (fichaTO.getDocumentos() != null) {
            for (Documentos documento : fichaTO.getDocumentos()) {
                if (documento.getDocsEntregable() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga por formulario porque al menos un entregable está asociado a un documento.", null);
                    return;
                } else {
                    if (documento.getDocsPagoFk() != null && documento.getDocsPagoFk().getEntregables() != null) {
                        JSFUtils.agregarMsgError("ganttForm", "No se permite la carga por formulario porque al menos un entregable está asociado a un pago.", null);
                        return;
                    }
                }
            }
        }

        //Solo se permite cargar datos por formulario si el proyecto no tiene productos registrados
        Proyectos proyecto = proyectoDelegate.obtenerProyPorId(fichaTO.getFichaFk());
        if (proyecto != null) {
            List<Productos> productos = productosDelegate.obtenerProdPorProyPk(proyecto.getProyPk());
            if (productos != null) {
                for (Productos producto : productos) {
                    if (producto.getProdEntregableFk() != null) {
                        JSFUtils.agregarMsgError("ganttForm", "No se permite la carga por formulario porque al menos un entregable está asociado a un producto.", null);
                        return;
                    }
                }
            }
        }

        //Los pagos están siempre asociados a un entregable, por lo que si existe un pago no se pueden eliminar los entregables actuales
        List<AdqPagosTO> adqPagos = adquisicionDelegate.obtenerAdquisicionPagosList(fichaTO.getPreFk().getPrePk());
        if (adqPagos != null) {
            for (AdqPagosTO adqPago : adqPagos) {
                if (adqPago.getTipo() == 2) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga por formulario porque hay al menos un pago asociado a entregables.", null);
                    return;
                }
            }
        }

        if (cro == null) {
            return;
        }
        entregablesCargaPorFormulario = new LinkedList();
        if (cro.getEntregablesSet() != null && !cro.getEntregablesSet().isEmpty()) {
            List<Entregables> entregables = EntregablesUtils.sortById(new LinkedList<>(cro.getEntregablesSet()));
            for (Entregables entregable : entregables) {
                EntregablesCargaPorFomulario entregableCargaFormulario = new EntregablesCargaPorFomulario();
                entregableCargaFormulario.setEsHito(entregable.getEntFinEsHito());
                entregableCargaFormulario.setEsfuerzo(entregable.getEntEsfuerzo() != null ? entregable.getEntEsfuerzo().toString() : "");
                entregableCargaFormulario.setFin(entregable.getEntFinDate());
                entregableCargaFormulario.setInicio(entregable.getEntInicioDate());
                entregableCargaFormulario.setNivel(entregable.getEntNivel() != null ? entregable.getEntNivel().toString() : "");
                entregableCargaFormulario.setNombre(entregable.getEntNombre() != null ? entregable.getEntNombre() : "");
                entregableCargaFormulario.setPredecesores(entregable.getEntPredecesorFk() != null ? entregable.getEntPredecesorFk() : "");
                entregableCargaFormulario.setProgreso(entregable.getEntProgreso() != null ? entregable.getEntProgreso().toString() : "");
                //Determinar si el entregable puede ser eliminado (luego, además, hay que verificar cada vez que ningun otro entregable dependa de él)
                boolean tieneProductos = productosDelegate.tieneProdporEnt(entregable.getEntPk());
                boolean tieneDependencias = entregablesDelegate.tieneDependencias(entregable.getEntPk());
                entregableCargaFormulario.setEliminable(!tieneDependencias && !tieneProductos);
                entregablesCargaPorFormulario.add(entregableCargaFormulario);
            }
        } else {
            entregablesCargaPorFormulario.add(new EntregablesCargaPorFomulario());
        }

        cargaPorFormulario = true;
    }

    public void cancelarCargaPorFormulario() {
        cargarFrameCronograma(true);
        cargaPorFormulario = false;
    }

    List<EntregablesCargaPorFomulario> entregablesCargaPorFormulario = new LinkedList();

    public List<EntregablesCargaPorFomulario> getEntregablesCargaPorFormulario() {
        return entregablesCargaPorFormulario;
    }

    //Añade un entregable en la posición siguiente a la indicada
    public String agregarEntregableCargaPorFormulario(EntregablesCargaPorFomulario entregable) {
        //Los entregables se indexan a partir de 1 pero la lista en Java a partir de 0, por eso se suma 1
        Integer entId = entregablesCargaPorFormulario.indexOf(entregable) + 1;
        entregablesCargaPorFormulario.add(entId, new EntregablesCargaPorFomulario());
        //Ajustar todos los entregables: dado que todos los entregables preexistentes que tienen EntId mayor al actual aumentan su EntId en 1
        //hay que ajustar las dependencias
        for (EntregablesCargaPorFomulario entregableCargaFormulario : entregablesCargaPorFormulario) {
            if (entregableCargaFormulario.getPredecesoresLista() != null) {
                StringBuilder sDependencias = new StringBuilder("");
                for (EntregablesCargaPorFomulario.Predecesor predecesor : entregableCargaFormulario.getPredecesoresLista()) {
                    //La coma, si ya hay un predecesor incluido
                    if (sDependencias.length() > 0) {
                        sDependencias.append(",");
                    }
                    //Si el id de la dependencia es mayor al eliminado, se aumenta en 1 sino se mantiene como está
                    if (predecesor.getId() > entId) {
                        sDependencias.append(predecesor.getId() + 1);
                    } else {
                        sDependencias.append(predecesor.getId());
                    }
                    //Si hay desplazamiento en días se incluye
                    if (predecesor.getDias() != null) {
                        sDependencias.append(":").append(predecesor.getDias());
                    }
                }
                entregableCargaFormulario.setPredecesores(sDependencias.toString());
            }
        }
        return null;
    }

    //Añade un entregable al final
    public void agregarEntregableCargaPorFormulario() {
        entregablesCargaPorFormulario.add(new EntregablesCargaPorFomulario());
    }

    //Quita un entregable en la posición indicada
    public String quitarEntregableCargaPorFormulario(EntregablesCargaPorFomulario entregable) {
        //Primero verificar si el entregable puede ser eliminado por sí mismo
        if (!entregable.isEliminable()) {
            JSFUtils.agregarMsgError("ficha:tblCargaPorFormulario", "No es posible eliminar el entregable. Es un entregable padre o es usado en reg. de horas, productos, pagos, colaboradores, riesgos, calidad o interesados.", null);
            return null;
        }
        //Los entregables se indexan a partir de 1 pero la lista en Java a partir de 0, por eso se suma 1
        int entId = entregablesCargaPorFormulario.indexOf(entregable) + 1;
        //Si pasó las validaciones se puede quitar el entregable, ajustando los demás entregables
        //Dado que todos los entregables preexistentes que tienen EntId mayor al actual disminuyen su EntId en 1 hay que ajustar las dependencias
        for (EntregablesCargaPorFomulario entregableCargaFormulario : entregablesCargaPorFormulario) {
            if (entregableCargaFormulario.getPredecesoresLista() != null) {
                StringBuilder sDependencias = new StringBuilder("");
                for (EntregablesCargaPorFomulario.Predecesor predecesor : entregableCargaFormulario.getPredecesoresLista()) {
                    //Si el id de la dependencia es igual al eliminado, se ignora (se quita la dependencia)
                    if (predecesor.getId() == entId) {
                        continue;
                    }          //La coma, si ya hay un predecesor incluido
                    if (sDependencias.length() > 0) {
                        sDependencias.append(",");
                    }
                    //Si el id de la dependencia es mayor al eliminado, se reduce en 1 sino se mantiene como está
                    if (predecesor.getId() > entId) {
                        sDependencias.append(predecesor.getId() - 1);
                    } else {
                        sDependencias.append(predecesor.getId());
                    }
                    //Si hay desplazamiento en días se incluye
                    if (predecesor.getDias() != null) {
                        sDependencias.append(":").append(predecesor.getDias());
                    }
                }
                //Volver a poner las depedencias (vuelve a calcular la lista)
                entregableCargaFormulario.setPredecesores(sDependencias.toString());
            }
        }
        //La eliminación hay que hacerla después de ajustar las dependencias para no modificar los identificadores antes de terminar los cálculos
        entregablesCargaPorFormulario.remove(entregable);
        return null;
    }

    public void importarEntregablesPorFormulario() {
        JSFUtils.removerMensages();
        try {
            List<Entregables> entregables = new LinkedList();
            int nroFila = 0;
            for (EntregablesCargaPorFomulario entregableCargaPorFormulario : entregablesCargaPorFormulario) {
                nroFila++;
                Entregables entregable = new Entregables();
                entregable.setEntId(nroFila);
                entregable.setEntNombre(entregableCargaPorFormulario.getNombre());
                try {
                    entregable.setEntNivel((Double.valueOf(entregableCargaPorFormulario.getNivel())).intValue());
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'nivel' en la fila " + nroFila + " no es correcto");
                }
                try {
                    entregable.setEntEsfuerzo((Double.valueOf(entregableCargaPorFormulario.getEsfuerzo())).intValue());
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'esfuerzo' en la fila " + nroFila + " no es correcto");
                }
                try {
                    entregable.setEntProgreso((Double.valueOf(entregableCargaPorFormulario.getProgreso())).intValue());
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'progreso' en la fila " + nroFila + " no es correcto", ex);
                }
                try {
                    //A la fecha obtenida de la planilla se le suma 12 horas para que quede al mediodía
                    entregable.setEntInicio(entregableCargaPorFormulario.getInicio().getTime() + 12 * 60 * 60 * 1000);
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'fecha de inicio' en la fila " + nroFila + " no es correcto");
                }
                try {
                    //A la fecha obtenida de la planilla se le suma 12 horas para que quede al mediodía
                    entregable.setEntFin(entregableCargaPorFormulario.getFin().getTime() + 12 * 60 * 60 * 1000);
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'fecha de fin' en la fila " + nroFila + " no es correcto");
                }
                //Columna 9: es hito
                try {
                    entregable.setEntInicioEsHito(Boolean.FALSE);
                    entregable.setEntFinEsHito(entregableCargaPorFormulario.getEsHito());
                } catch (Exception ex) {
                    throw new BusinessException("El campo 'es hito' en la fila " + nroFila + " no es correcto");
                }
                //Columna 10: predecesores
                try {
                    if (entregableCargaPorFormulario.getPredecesores() != null && !entregableCargaPorFormulario.getPredecesores().trim().isEmpty()) {
                        entregable.setEntPredecesorFk(entregableCargaPorFormulario.getPredecesores());
                        entregable.setEntPredecesorDias(0);
                    }
                    if (entregable.getEntPredecesorFk() != null && !entregable.getEntPredecesorFk().isEmpty()
                            && !entregable.getEntPredecesorFk().matches(EntregablesValidacion.DEPENDENCIA_REGEX)) {
                        throw new BusinessException("El campo 'dependencia' en la fila " + nroFila + " no es correcto");
                    }

                } catch (Exception ex) {
                    throw new BusinessException("El campo 'predecesores' en la fila " + nroFila + " no es correcto");
                }
                //Columna 12: coordinador
                entregable.setCoordinadorUsuFk(inicioMB.getUsuario());
                //Otros campos que no se toman del formulario
                entregable.setEntStatus("STATUS_ACTIVE");
                entregable.setEntCollapsed(Boolean.FALSE);
                entregable.setEntInicioProyecto(Boolean.FALSE);
                entregable.setEntFinProyecto(Boolean.FALSE);
                //Si es un hito se pone como fecha de inicio la misma que la fecha de fin
                if (entregable.getEntFinEsHito()) {
                    entregable.setEntInicio(entregable.getEntFin());
                }
                entregables.add(entregable);
                //Queda pendiente lo siguiente porque dependen de otros entregables (hay que hacerlo después de cargar todos los entregables):
                //1-Ajustar las fechas de inicio y fin, y las fechas de inicio y fin de la línea base
                //2-Completar los campos entParent, entDuracion, entDuracionLineaBase
            }
            //Ajustar las fechas de los entregables en base a los hijos y las dependencias
            entregables = EntregablesUtils.ajustarFechas(entregables);
            //Validar los datos y las fechas de los entregables
            EntregablesValidacion.validarEntregables(entregables);
            //Si llegó hasta acá se importaron y validaron todos los entregables
            fichaTO.getCroFk().getEntregablesSet().clear();
            for (Entregables entregable : entregables) {
                entregable.setEntCroFk(fichaTO.getCroFk());
                fichaTO.getCroFk().getEntregablesSet().add(entregable);
            }
            //Guardar el cronograma con todos los entregables
            Object progProy = cronogramaDelegate.guardarCronograma(fichaTO.getCroFk(), fichaTO.getFichaFk(), fichaTO.getTipoFicha(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
            if (progProy == null) {
                JSFUtils.agregarMsgError("ganttForm", "error_cro_guardar", null);
            } else {
                actualizarFichaTO(progProy);
                cargarFrameCronograma(true);
                JSFUtils.agregarMsg("ganttForm", "info_cronograma_guardado", null);
                //Volver a la vista normal del cronograma
                cargaPorFormulario = false;
            }

        } catch (BusinessException bEx) {
            logger.log(Level.SEVERE, Labels.getMessage(bEx));
            JSFUtils.agregarMsgError("tblCargaPorFormulario", Labels.getMessage(bEx), null);
        }
    }

    // ======================================================================================================================
    // Carga masiva de tareas del Gantt desde archivo
    private boolean cargaPresupuestoDesdeArchivo = false;

    public boolean getCargaPresupuestoDesdeArchivo() {
        return cargaPresupuestoDesdeArchivo;
    }

    public boolean habilitarCargaPresupuestoDesdeArchivo() {
        /* 15-03-2018 Nico: Dejo comentada esta línea para no mostrar el campo de cargar datos desde archivo 
         */
        //return fichaTO.isEstado(Estados.ESTADOS.INICIO.estado_id) || fichaTO.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id);
        return false;
    }

    public void mostrarCargaPresupuestoDesdeArchivo() {

        if (TipoFichaEnum.PROGRAMA.getValue() == fichaTO.getTipoFicha()) {
            JSFUtils.agregarMsgError("btnHabilitarCargaPresupuestoDesdeArchivo", "Esta funcionalidad solo está disponible para proyectos.", null);
            return;
        }

        //Solo se permite cargar datos de un archivo si no hay registros de devengados
        for (Adquisicion adquisicion : listAdqDev) {
            if (adquisicion.getDevengadoList() != null && !adquisicion.getDevengadoList().isEmpty()) {
                JSFUtils.agregarMsgError("btnHabilitarCargaPresupuestoDesdeArchivo", "No se permite la carga desde un archivo porque existe al menos un registro devengado.", null);
                return;
            }
        }

        //Solo se permite cargar datos de un archivo si no existe ningún pago confirmado
        for (AdqPagosTO adqPago : listaAdqPagosFrame) {
            if (adqPago.getTipo() == 2 && adqPago.getConfirmado() != null && adqPago.getConfirmado().booleanValue()) {
                JSFUtils.agregarMsgError("btnHabilitarCargaPresupuestoDesdeArchivo", "No se permite la carga desde un archivo porque hay al menos un pago confirmado.", null);
                return;
            }
        }

        //Si llegó hasta acá es porque pasó todas las validaciones
        cargaPresupuestoDesdeArchivo = true;
    }

    public void cancelarCargaPresupuestoDesdeArchivo() {
        cargaPresupuestoDesdeArchivo = false;
    }

    private File planillaPresupuestoDesdeArchivo;

    public void subirPlanillaCargaPresupuestoDesdeArchivoAction(FileEntryEvent evento) {
        JSFUtils.removerMensages();
        try {
            planillaPresupuestoDesdeArchivo = null;
            FileEntry fileEntry = (FileEntry) evento.getComponent();
            FileEntryResults results = fileEntry.getResults();
            planillaPresupuestoDesdeArchivo = results.getFiles().get(0).getFile();
        } catch (Exception e2) {
            JSFUtils.agregarMsg("error_subir_archivo");
            logger.log(Level.SEVERE, e2.getMessage(), e2);
        }
    }

    @SuppressWarnings("LocalVariableHidesMemberVariable")
    public String cargarPresupuestoDesdeArchivo() {
        JSFUtils.removerMensages();
        if (planillaPresupuestoDesdeArchivo == null) {
            JSFUtils.agregarMsgError("fileEntryPlanilla", "No ha seleccionado una planilla", null);
            return null;
        }
        try {
            HSSFSheet hoja;
            try {
                //Abrir el archivo
                InputStream stream = new FileInputStream(planillaPresupuestoDesdeArchivo);
                //Abrir el libro
                HSSFWorkbook libro = new HSSFWorkbook(stream);
                //Obtener la hoja 1
                hoja = libro.getSheetAt(0);
            } catch (Exception ex) {
                throw new BusinessException("No se puede acceder a la planilla indicada o la misma no tiene el formato correcto");
            }
            HSSFRow fila;
            //Iterar por las filas 
            Iterator rows = hoja.rowIterator();
            int nroFila = 0;

            //Armar un mapa de entregables por EntId
            Map<Integer, Entregables> entregables = new HashMap<Integer, Entregables>();
            for (Entregables entregable : entregablesDelegate.obtenerEntPorProyPk(fichaTO.getFichaFk())) {
                entregables.put(entregable.getEntId(), entregable);
            }
            //Armar un mapa de monedas por símbolo
            Map<String, Moneda> monedas = new HashMap<String, Moneda>();
            for (Moneda moneda : monedaDelegate.obtenerMonedas()) {
                monedas.put(moneda.getMonSigno(), moneda);
            }

            List<Adquisicion> adquisiciones = new LinkedList<Adquisicion>();
            Adquisicion adquisicion = null;

            boolean procesamientoTerminado = false;

            while (!procesamientoTerminado && rows.hasNext()) {

                nroFila++;
                fila = (HSSFRow) rows.next();
                if (nroFila == 1) {
                    //Saltear la primer fila (el encabezado)
                    continue;
                }

//				//Si no hay valor en la columna 0 se asume que se terminaron los datos
//				if (fila.getCell(0) == null || fila.getCell(0).toString().trim().isEmpty()) {
//					procesamientoTerminado = true;
//					continue;
//				}
                /**
                 * Si la fila está vacía se asume que se terminaron los datos.
                 */
                if ((fila.getCell(0) == null || fila.getCell(0).toString().trim().isEmpty())
                        && (fila.getCell(1) == null || fila.getCell(1).toString().trim().isEmpty())
                        && (fila.getCell(2) == null || fila.getCell(2).toString().trim().isEmpty())
                        && (fila.getCell(3) == null || fila.getCell(3).toString().trim().isEmpty())
                        && (fila.getCell(4) == null || fila.getCell(4).toString().trim().isEmpty())
                        && (fila.getCell(5) == null || fila.getCell(5).toString().trim().isEmpty())
                        && (fila.getCell(6) == null || fila.getCell(6).toString().trim().isEmpty())
                        && (fila.getCell(7) == null || fila.getCell(7).toString().trim().isEmpty())) {
                    procesamientoTerminado = true;
                    continue;
                }

                String tipo = fila.getCell(0) != null ? fila.getCell(0).toString() : "";
                switch (tipo) {
                    case "G":
                        adquisicion = obtenerAdquisicion(fila, nroFila, monedas);
                        adquisicion.setPagosSet(new HashSet<Pagos>());
                        adquisiciones.add(adquisicion);
                        break;
                    case "P":
                        if (adquisicion == null) {
                            throw new BusinessException("Se ha encontrado un pago sin una adquisición asociada");
                        }
                        Pagos pago = obtenerPago(fila, nroFila, entregables);
                        pago.setPagAdqFk(adquisicion);
                        adquisicion.getPagosSet().add(pago);
                        break;
                    default:
                        throw new BusinessException("Tipo ingresado en el archivo no es válido. Sólo son válidos \"G\" y \"P\"");
                }

            }

            //Validar las adquisiciones  y sus respectivos pagos
            validarAdquisiciones(adquisiciones);

            //Si pasó todas las validaciones se eliminan las adquisiciones actuales y se persisten las adquisiciones y los pagos
            for (AdqPagosTO adqPago : listaAdqPagosFrame) {
                if (adqPago.getTipo() == 1) {
                    adquisicionDelegate.eliminarAdquisicion(adqPago.getAdqPk(), fichaTO.getFichaFk(), inicioMB.getOrganismo().getOrgPk());
                }
            }
            for (Adquisicion adquisicion1 : adquisiciones) {
                adquisicion1 = adquisicionDelegate.guardarAdquisicion(adquisicion1, fichaTO.getFichaFk(), fichaTO.getTipoFicha(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
                for (Pagos pago : adquisicion1.getPagosSet()) {
                    Integer progPk = fichaTO.getProgFk() != null ? fichaTO.getProgFk().getProgPk() : null;
                    Integer proyPk = fichaTO.getTipoFicha().equals(TipoFichaEnum.PROYECTO.id) ? fichaTO.getFichaFk() : null;
                    pagosDelegate.guardarPago(pago, proyPk, progPk, inicioMB.getOrganismo().getOrgPk());
                }
            }

            //Volver a la vista normal del cronograma
            cargarFramePresupuestos(false);
            cargaPresupuestoDesdeArchivo = false;
        } catch (BusinessException bEx) {
            logger.log(Level.SEVERE, Labels.getMessage(bEx));
            JSFUtils.agregarMsgError("fileEntryCargaPresupuestoDesdeArchivo", Labels.getMessage(bEx), null);
        }
        return null;
    }

    @SuppressWarnings("LocalVariableHidesMemberVariable")
    private Adquisicion obtenerAdquisicion(HSSFRow fila, int nroFila, Map<String, Moneda> monedas) throws BusinessException {
        Adquisicion adquisicion = new Adquisicion();
        adquisicion.setAdqPreFk(fichaTO.getPreFk());
        //Columna 1: gasto
        try {
            adquisicion.setAdqNombre(fila.getCell(1).toString());
        } catch (Exception ex) {
            throw new BusinessException("El campo 'gasto' en la fila " + nroFila + " no es correcto", ex);
        }
        //Columna 3: proveedor
        try {
            if (fila.getCell(3) != null && !fila.getCell(3).toString().trim().isEmpty()) {
                fila.getCell(3).setCellType(HSSFCell.CELL_TYPE_STRING);
                Integer proveedorPk = Integer.valueOf(fila.getCell(3).toString());
                OrganiIntProve proveedor = organiIntProveDelegate.obtenerOrganiIntProvePorId(proveedorPk);
                if (proveedor == null || !proveedor.getOrgaOrgFk().equals(inicioMB.getOrganismo())) {
                    throw new BusinessException("No se encuentra el proveedor especificado");
                }
                adquisicion.setAdqProvOrga(proveedor);
            }
        } catch (Exception ex) {
            throw new BusinessException("El campo 'proveedor' en la fila " + nroFila + " no es correcto", ex);
        }
        //Columna 4: fuente de financiamiento
        try {
            if (fila.getCell(4) != null && !fila.getCell(4).toString().trim().isEmpty()) {
                fila.getCell(4).setCellType(HSSFCell.CELL_TYPE_STRING);
                Integer fuenteFinanciamientoPk = Integer.valueOf(fila.getCell(4).toString());
                FuenteFinanciamiento fuenteFinanciamiento = fuenteFinanciamientoDelegate.obtenerFuentePorPk(fuenteFinanciamientoPk);
                if (fuenteFinanciamiento == null || !fuenteFinanciamiento.getFueOrgFk().equals(inicioMB.getOrganismo())) {
                    throw new BusinessException("No se encuentra la fuente de financiamiento especificada");
                }
                adquisicion.setAdqFuente(fuenteFinanciamiento);
            }
        } catch (Exception ex) {
            throw new BusinessException("El campo 'fuente de financiamiento' en la fila " + nroFila + " no es correcto", ex);
        }
        //Columna 5: moneda
        try {
            if (fila.getCell(5) != null && !fila.getCell(5).toString().trim().isEmpty()) {
                String monedaSigno = fila.getCell(5).toString().trim();
                if (!monedas.containsKey(monedaSigno)) {
                    throw new BusinessException("No se encuentra la moneda especificada");
                }
                adquisicion.setAdqMoneda(monedas.get(monedaSigno));
            }
        } catch (Exception ex) {
            throw new BusinessException("El campo 'moneda' en la fila " + nroFila + " no es correcto", ex);
        }
        //Columna 6: procedimiento de compra

        /*
                *   24-05-2018 Nico: Se comenta la parte de manejo de String de los Procedimientos de Compra ya que ahora
                *           se maneja como una entidad nueva.
         */
//		try {
//			adquisicion.setAdqProcCompra(fila.getCell(6).toString());
//		} catch (Exception ex) {
//			throw new BusinessException("El campo 'gasto' en la fila " + nroFila + " no es correcto", ex);
//		}
        //Columna 7: procedimiento de compra GRP
        try {
            final FiltroIdentificadorGrpErpTO filtroIdentificadorGrpErpTO = new FiltroIdentificadorGrpErpTO();
            filtroIdentificadorGrpErpTO.setNombre(fila.getCell(7).toString());
            filtroIdentificadorGrpErpTO.setOrganismo(this.getInicioMB().getOrganismo());

            adquisicion.setAdqIdGrpErpFk(this.identificadorGrpErpDelegate.obtenerPorFiltro(filtroIdentificadorGrpErpTO).get(0));
        } catch (Exception ex) {
            throw new BusinessException(
                    String.format(
                            "No se pudo obtener el valor del campo '%s' en la fila %d.",
                            Labels.getValue("adquisicion_identificador_grp"), nroFila
                    ),
                    ex
            );
        }
        return adquisicion;
    }

    private Pagos obtenerPago(HSSFRow fila, int nroFila, Map<Integer, Entregables> entregables) throws BusinessException {
        Pagos pago = new Pagos();
        String value;
        //Columna 2: entregable
        try {
            fila.getCell(2).setCellType(HSSFCell.CELL_TYPE_STRING);
            Integer entregableId = Integer.valueOf(fila.getCell(2).toString());
            //El identificador no es el campo EntPk sino el campo EntId
            if (!entregables.containsKey(entregableId)) {
                throw new BusinessException("No se encuentra el entregable especificado");
            }
            pago.setEntregables(entregables.get(entregableId));
        } catch (Exception ex) {
            throw new BusinessException("El campo 'entregable' en la fila " + nroFila + " no es correcto", ex);
        }
        //Columna 8: fecha planificada
        try {
            pago.setPagFechaPlanificada(fila.getCell(8).getDateCellValue());
        } catch (Exception ex) {
            throw new BusinessException("El campo 'fecha planificada' en la fila " + nroFila + " no es correcto", ex);
        }
        //Columna 9: importe planificado
        try {
            pago.setPagImportePlanificado(Double.valueOf(fila.getCell(9).toString()));
        } catch (Exception ex) {
            throw new BusinessException("El campo 'importe planificado' en la fila " + nroFila + " no es correcto", ex);
        }
        //Columna 10: fecha real
        try {
            if (fila.getCell(10) != null && !fila.getCell(10).toString().trim().isEmpty()) {
                pago.setPagFechaReal(fila.getCell(10).getDateCellValue());
            }
        } catch (Exception ex) {
            throw new BusinessException("El campo 'fecha real' en la fila " + nroFila + " no es correcto", ex);
        }
        //Columna 11: importe real
        try {
            if (fila.getCell(11) != null && !fila.getCell(11).toString().trim().isEmpty()) {
                pago.setPagImporteReal(Double.valueOf(fila.getCell(11).toString()));
            }
        } catch (Exception ex) {
            throw new BusinessException("El campo 'importe real' en la fila " + nroFila + " no es correcto", ex);
        }
        //Columna 9: confirmado
        try {
            value = fila.getCell(12).toString().trim();
            if (value == null || !("SI".equalsIgnoreCase(value) || "NO".equalsIgnoreCase(value))) {
                throw new BusinessException("El campo 'confirmado' en la fila " + nroFila + " no es correcto");
            }
            pago.setPagConfirmar(fila.getCell(12).toString().equalsIgnoreCase("SI"));
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException("El campo 'confirmado' en la fila " + nroFila + " no es correcto", ex);
        }
        if (fichaTO.getEstado().isEstado(Estados.ESTADOS.EJECUCION.estado_id)) {
            pago.setPagFechaPlanificada(pago.getPagFechaReal());
            pago.setPagImportePlanificado(0.0);
        }
        return pago;
    }

    /**
     * Valida las adquisiciones y los pagos asociados
     *
     * @param adquisiciones
     */
    @SuppressWarnings("LocalVariableHidesMemberVariable")
    private void validarAdquisiciones(List<Adquisicion> adquisiciones) throws BusinessException {
        if (adquisiciones == null) {
            return;
        }
        for (Adquisicion adquisicion : adquisiciones) {
            //El nombre es requerido
            if (adquisicion.getAdqNombre() == null) {
                throw new BusinessException("El nombre es requerido");
            }
            //El nombre debe tener largo mayor a 1 y menor a 20
            if (adquisicion.getAdqNombre().trim().isEmpty() || adquisicion.getAdqNombre().trim().length() > 100) {
                throw new BusinessException("El nombre debe tener entre 1 y 100 caracteres");
            }
            if (adquisicion.getAdqFuente() == null) {
                throw new BusinessException("La fuente de financiamiento es requerida");
            }
            if (adquisicion.getAdqMoneda() == null) {
                throw new BusinessException("La moneda es requerida");
            }
            for (Pagos pago : adquisicion.getPagosSet()) {
                //El entregable es requerido
                if (pago.getEntregables() == null) {
                    throw new BusinessException("El entregable es requerido");
                }
            }
            //El procedimiento de compra es requerido

            /*
                        *   24-05-2018 Nico: Se comenta la parte de manejo de String de los Procedimientos de Compra ya que ahora
                        *           se maneja como una entidad nueva.
             */
//			if (adquisicion.getAdqProcCompra() == null) {
//				throw new BusinessException("El procedimiento de compra es requerido");
//			}
//			//El nombre debe tener largo mayor a 1 y menor a 20
//			if (adquisicion.getAdqProcCompra().trim().isEmpty() || adquisicion.getAdqProcCompra().trim().length() > 20) {
//				throw new BusinessException("El procedimiento de compra debe tener entre 1 y 20 caracteres");
//			}
            if (adquisicion.getAdqProcedimientoCompra() == null) {
                throw new BusinessException("El procedimiento de compra es requerido");
            }
        }
    }

    // Esta operación es utilizada para poder cheaquer se existen inconsistencias en el Cronograma
    private void checkDatosEntregablesCronograma() {
        if (this.fichaTO.getCroFk() != null
                && this.fichaTO.getCroFk().getEntregablesSet() != null
                && !this.fichaTO.getCroFk().getEntregablesSet().isEmpty()) {

            //hash auxiliar para controlar la dependecias circulares
            Map<Integer, Boolean> visitados = new HashMap<Integer, Boolean>();

            // Variable utilizada para poder corroborar si existe algún entregable de nivel 0
            boolean cronoNivelesCorrecto = false;

            for (Entregables iterEnt : this.fichaTO.getCroFk().getEntregablesSet()) {
                // utilizo el for para poder cargar el hash auxiliar para controlar dependencias circulares
                visitados.put(iterEnt.getEntId(), Boolean.FALSE);

                // Si existe un entregable de nivel 0, el cronograma no presenta problemas, por lo que la variable queda en "true"
                if (iterEnt.getEntNivel() == 0) {
                    cronoNivelesCorrecto = true;
                }
            }

            if (!cronoNivelesCorrecto) {
                // Corrijo los niveles de los entregables en el Cronograma
                for (Entregables iterEnt : this.fichaTO.getCroFk().getEntregablesSet()) {
                    iterEnt.setEntNivel(iterEnt.getEntNivel() - 1);
                }
            }

//            // Para controlar las dependencias circulares utilizo el hash auxiliar creado
//            for (Entregables iterEnt : this.fichaTO.getCroFk().getEntregablesSet()) {
//                // Busco un entregable con dependencias y que no haya sido visitado
//                if (iterEnt.getEntPredecesorFk() != null && iterEnt.getEntPredecesorFk() != ""
//                        && (!visitados.get(iterEnt.getEntId()))) {
//                    checkCronogramaDependenciasCirculares(iterEnt, this.fichaTO.getCroFk().getEntregablesSet(), visitados);
//                }
//            }
//
//            cronogramaDelegate.guardarDespuesDeCheck(this.fichaTO.getCroFk());
        }
    }

//    private void checkCronogramaDependenciasCirculares(Entregables ent, Set<Entregables> entEnCrono, Map<Integer, Boolean> visitados) {
//        // Si no fue visitado, lo marco como visitado y busco el entregable en el set
//        if (!visitados.get(ent.getEntId())) {
//            visitados.remove(ent.getEntId());
//            visitados.put(ent.getEntId(), Boolean.TRUE);
//
//            Entregables entToVisit = null;
//
//            for (Entregables iterEnt : entEnCrono) {
//                if (iterEnt.getEntPredecesorFk() != null && iterEnt.getEntId() != null
//                        && ent.getEntPredecesorFk().equals(iterEnt.getEntId().toString())) {
//                    entToVisit = iterEnt;
//                    break;
//                }
//            }
//
//            if (entToVisit != null) {
//                checkCronogramaDependenciasCirculares(entToVisit, entEnCrono, visitados);
//            }
//
//        } else {
//            // En este caso el entregable fue visitado, por lo que lo debo marcar como visitado y borrar el valor de "getEntPredecesorFk"
//            ent.setEntPredecesorFk(null);
//        }
//    }
    public void cambioFuente(ValueChangeEvent evt) {
        Integer idFuente = (Integer) evt.getNewValue();
        fuenteFinanciamientoSelected = fuenteFinanciamientoDelegate.obtenerFuentePorPk(idFuente);
        actualizarComboCausales();
    }

    public void cambioProcedimientoCompra(ValueChangeEvent evt) {
        Integer idProcedimientoCompra = (Integer) evt.getNewValue();
        procedimientoCompraSelected = procedimiComponenteProductoDelegate.obtenerProcedimientoCompraPorPk(idProcedimientoCompra);
        actualizarComboCausales();
    }

    public Boolean getLocalizacionFormDataExpanded() {
        return localizacionFormDataExpanded;
    }

    public void setLocalizacionFormDataExpanded(Boolean localizacionFormDataExpanded) {
        this.localizacionFormDataExpanded = localizacionFormDataExpanded;
    }

    public LatlngProyectos getLatlngProyectosAux() {
        return latlngProyectosAux;
    }

    public void setLatlngProyectosAux(LatlngProyectos latlngProyectosAux) {
        this.latlngProyectosAux = latlngProyectosAux;
    }

    public Boolean getRenderPopupLocalizacion() {
        return renderPopupLocalizacion;
    }

    public void setRenderPopupLocalizacion(Boolean renderPopupLocalizacion) {
        this.renderPopupLocalizacion = renderPopupLocalizacion;
    }

    public DataTable getLatLngProyListDataTable() {
        return latLngProyListDataTable;
    }

    public void setLatLngProyListDataTable(DataTable latLngProyListDataTable) {
        this.latLngProyListDataTable = latLngProyListDataTable;
    }

    public SofisCombo getListaProcedimientoCompraCombo() {
        return listaProcedimientoCompraCombo;
    }

    public void setListaProcedimientoCompraCombo(SofisCombo listaProcedimientoCompraCombo) {
        this.listaProcedimientoCompraCombo = listaProcedimientoCompraCombo;
    }

    public SofisComboG getListaIdentificadorGrpErpCombo() {
        return listaIdentificadorGrpErpCombo;
    }

    public void setListaIdentificadorGrpErpCombo(SofisComboG listaIdentificadorGrpErpCombo) {
        this.listaIdentificadorGrpErpCombo = listaIdentificadorGrpErpCombo;
    }

    public List<SelectItem> getListaUsuariosAdqCompartidaCombo() {
        return listaUsuariosAdqCompartidaCombo;
    }

    public void setListaUsuariosAdqCompartidaCombo(List<SelectItem> listaUsuariosAdqCompartidaCombo) {
        this.listaUsuariosAdqCompartidaCombo = listaUsuariosAdqCompartidaCombo;
    }

    public List<SsUsuario> getListaUsuariosAdqCompartida() {
        return listaUsuariosAdqCompartida;
    }

    public void setListaUsuariosAdqCompartida(List<SsUsuario> listaUsuariosAdqCompartida) {
        this.listaUsuariosAdqCompartida = listaUsuariosAdqCompartida;
    }

    public Integer getSsUsuarioCompartidaId() {
        return ssUsuarioCompartidaId;
    }

    public void setSsUsuarioCompartidaId(Integer ssUsuarioCompartidaId) {
        this.ssUsuarioCompartidaId = ssUsuarioCompartidaId;
    }

    public Boolean getDevengadoMsgs() {
        return devengadoMsgs;
    }

    public void setDevengadoMsgs(Boolean devengadoMsgs) {
        this.devengadoMsgs = devengadoMsgs;
    }

    public SofisCombo getListaProveedoresCombo() {
        return listaProveedoresCombo;
    }

    public void setListaProveedoresCombo(SofisCombo listaProveedoresCombo) {
        this.listaProveedoresCombo = listaProveedoresCombo;
    }

    public SofisComboG<TipoRegistroCompra> getListaTipoRegistroCompraCombo() {
        return listaTipoRegistroCompraCombo;
    }

    public void setListaTipoRegistroCompraCombo(SofisComboG<TipoRegistroCompra> listaTipoRegistroCompraCombo) {
        this.listaTipoRegistroCompraCombo = listaTipoRegistroCompraCombo;
    }

    public SofisComboG<OrganiIntProve> getListaProveedoresPagoCombo() {
        return listaProveedoresPagoCombo;
    }

    public void setListaProveedoresPagoCombo(SofisComboG<OrganiIntProve> listaProveedoresPagoCombo) {
        this.listaProveedoresPagoCombo = listaProveedoresPagoCombo;
    }

    public SofisComboG<TipoAdquisicion> getListaTipoAdquisicionCombo() {
        return listaTipoAdquisicionCombo;
    }

    public void setListaTipoAdquisicionCombo(SofisComboG<TipoAdquisicion> listaTipoAdquisicionCombo) {
        this.listaTipoAdquisicionCombo = listaTipoAdquisicionCombo;
    }

    public SofisComboG<CentroCosto> getListaCentroCostoCombo() {
        return listaCentroCostoCombo;
    }

    public void setListaCentroCostoCombo(SofisComboG<CentroCosto> listaCentroCostoCombo) {
        this.listaCentroCostoCombo = listaCentroCostoCombo;
    }

    public SofisComboG<CausalCompra> getListaCausalCompraCombo() {
        return listaCausalCompraCombo;
    }

    public void setListaCausalCompraCombo(SofisComboG<CausalCompra> listaCausalCompraCombo) {
        this.listaCausalCompraCombo = listaCausalCompraCombo;
    }

	public Integer getLargoMaximoIdAdquisicion() {
		return largoMaximoIdAdquisicion;
	}

	public void setLargoMaximoIdAdquisicion(Integer largoMaximoIdAdquisicion) {
		this.largoMaximoIdAdquisicion = largoMaximoIdAdquisicion;
	}
	
    public Boolean camposExigidosEnAdquisicion() {
        return configuracionDelegate.obtenerCnfPorCodigoYOrg(
                ConfiguracionCodigos.CAMPOS_SON_EXIGIDOS_EN_ADQUISICION,
                this.getInicioMB().getOrganismo().getOrgPk()
        ).getCnfValor().equalsIgnoreCase("true");
    }
 
    public boolean mostrarModulo(String modulo) {
        String codigo = "";
        switch (modulo) {
            case "Riesgos":
                codigo = ConfiguracionCodigos.SHOW_MODULO_RIESGOS;
                break;
                
            case "Productos":
                codigo = ConfiguracionCodigos.SHOW_MODULO_PRODUCTOS;
                break;
            
            case "Presupuesto":
                codigo = ConfiguracionCodigos.SHOW_MODULO_PRESUPUESTO;
                break;
            
            case "Documentos":
                codigo = ConfiguracionCodigos.SHOW_MODULO_DOCUMENTOS;
                break;
            
            case "Interesados":
                codigo = ConfiguracionCodigos.SHOW_MODULO_INTERESADOS;
                break;
            
            case "Colaboradores":
                codigo = ConfiguracionCodigos.SHOW_MODULO_COLABORADORES;
                break;
            
            case "Localizaciones":
                codigo = ConfiguracionCodigos.SHOW_MODULO_LOCALIZACIONES;
                break;
            
            case "Calidad":
                codigo = ConfiguracionCodigos.SHOW_MODULO_CALIDAD;
                break;
            
            case "Multimedia":
                codigo = ConfiguracionCodigos.SHOW_MODULO_MULTIMEDIA;
                break;            
        }
        
        if (fichaTO.getOrgFk() != null) {
            return configuracionDelegate.obtenerCnfPorCodigoYOrg(codigo, fichaTO.getOrgFk().getOrgPk()).getCnfValor().equals("true");
        }
        
        // El caso de crear una nueva ficha
        return true;
    }
}
