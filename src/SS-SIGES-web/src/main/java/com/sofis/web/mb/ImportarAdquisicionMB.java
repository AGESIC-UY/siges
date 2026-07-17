package com.sofis.web.mb;

import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.AdquisicionRango;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.IdentificadorGrpErp;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.ProyReplanificacion;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.TipoAdquisicion;
import com.sofis.entities.enums.TipoRegistroCompra;
import com.sofis.entities.tipos.AdquisicionTO;
import com.sofis.entities.tipos.CronogramaTO;
import com.sofis.entities.tipos.EntregableTO;
import com.sofis.entities.tipos.ErrorImportTO;
import com.sofis.entities.tipos.FiltroIdentificadorGrpErpTO;
import com.sofis.entities.tipos.FiltroTipoAdquisicionTO;
import com.sofis.entities.tipos.FuenteFinanciamientoTO;
import com.sofis.entities.tipos.MonedaTO;
import com.sofis.entities.tipos.PagoTO;
import com.sofis.entities.tipos.ProcedimientoCompraTO;
import com.sofis.entities.tipos.ProyectoTO;
import com.sofis.entities.tipos.ResultadoTO;
import com.sofis.entities.tipos.TipoAdquisicionTO;
import com.sofis.entities.utils.FuenteFinanciamientoTOUtils;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.AdquisicionDelegate;
import com.sofis.web.delegates.AdquisicionRangoDelegate;
import com.sofis.web.delegates.CronogramaDelegate;
import com.sofis.web.delegates.FuenteFinanciamientoDelegate;
import com.sofis.web.delegates.MisAdquisicionesDelegate;
import com.sofis.web.delegates.MonedaDelegate;
import com.sofis.web.delegates.PagosDelegate;
import com.sofis.web.delegates.ProcedimientoCompraDelegate;
import com.sofis.web.delegates.ProcedimientoCompraInfoDelegate;
import com.sofis.web.delegates.ProyReplanificacionDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.delegates.TipoAdquisicionDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.RepeatPaginator;
import com.sofis.web.utils.SofisCombo;
import com.sofis.web.utils.SofisComboG;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;
import org.mozilla.universalchardet.ReaderFactory;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "importarAdquisicionMB")
@ViewScoped
public class ImportarAdquisicionMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ImportarAdquisicionMB.class.getName());
    //private static final String BUSQUEDA_MSG = "busquedaMsg";
    private static final String POPUP_MSG = "popupMsg";
    // private static final String POPUP_MSG_MASIVA = "popupMsgMasiva";

    private static final String AREAS_NOMBRE = "procCompNombre";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private SofisPopupUI renderPopupEdicion;
    @Inject
    private ProcedimientoCompraDelegate procedimientoCompraDelegate;

    @Inject
    private ProcedimientoCompraInfoDelegate procedimientoCompraInfoDelegate;

    @Inject
    private AdquisicionDelegate adquisiconDelegate;
    @Inject
    private MisAdquisicionesDelegate misAdquisicionesDelegate;
    @Inject
    private CronogramaDelegate cronogramaDelegate;
    //agregado par acarga masiva
    @Inject
    private SofisPopupUI renderPopupCargaArchivo;
    @Inject
    private ProyectosDelegate proyectoDelegate;
    @Inject
    private AdquisicionDelegate adquisicionDelegate;

    @Inject
    private FuenteFinanciamientoDelegate fuenteFinanciamientoDelegate;
    @Inject
    private MonedaDelegate monedaDelegate;

    @Inject
    private TipoAdquisicionDelegate tipoAdquisicionDelegate;
    @Inject
    private PagosDelegate pagosDelegate;

    @Inject
    private ProyReplanificacionDelegate proyReplanificacionDelegate;

    // Variables
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = AREAS_NOMBRE;
    // 0=descendente, 1=ascendente.
    private int ascendente = 1;
    private String filtroNombre;

    private ProcedimientoCompra procedimientoCompraEnEdicion;

    private File exelCargaMasiva;

    private boolean errorEnTipoArchivo = false;
    boolean isFirefox = false;

    private boolean datosCargados = false;
    private List<AdquisicionTO> listaImportada;
    private RepeatPaginator paginator;
    private Map<Integer, Boolean> proyectosExpandidos;
    private Map<Integer, Map<Integer, Boolean>> adquisicionesExpandidas;
    private AdquisicionTO adquisicionEnEdicion;
    private AdquisicionTO adquisicionEnVisualizacion;
    private Map<Integer, ProyectoTO> proyectosMap;
    private Integer ultimaPkProyecto = 0;
    private SofisComboG<FuenteFinanciamientoTO> fuenteFinanciamientoAdquisicionCombo;
    private List<FuenteFinanciamientoTO> fuentesFinanciamientoHabilitadas;
    private SofisComboG<TipoRegistroCompra> listaTipoRegistroCompraCombo = new SofisComboG();
    private SofisComboG<TipoAdquisicion> listaTipoAdquisicionCombo = new SofisComboG();
    private SofisComboG<IdentificadorGrpErp> listaIdentificadorGrpErpCombo = new SofisComboG();
    private SofisCombo listaProcedimientoCompraCombo = new SofisCombo();
    private SofisCombo listaFuentesCombo = new SofisCombo();
    private List<Moneda> listaMonedas;
    private SofisCombo listaMonedaCombo = new SofisCombo();
    private List<ErrorImportTO> listaErrores;
    private PagoTO pagoEnEdicion;
    private boolean confirmado = false;
    private boolean datosGuardado = false;
    private List<ResultadoTO> listaResultado;
    @Inject
    private AdquisicionRangoDelegate adqRangoDelegate;

    public ImportarAdquisicionMB() {
    }

    // agregado para carga masiva
    public File getExelCargaMasiva() {
        return exelCargaMasiva;
    }

    public void setExelCargaMasiva(File exelCargaMasiva) {
        this.exelCargaMasiva = exelCargaMasiva;
    }

    public SofisPopupUI getRenderPopupCargaArchivo() {
        return renderPopupCargaArchivo;
    }

    public void setRenderPopupCargaArchivo(SofisPopupUI renderPopupCargaArchivo) {
        this.renderPopupCargaArchivo = renderPopupCargaArchivo;
    }
    // fin agregado para carga masiva

    public SofisPopupUI getRenderPopupEdicion() {
        return renderPopupEdicion;
    }

    public void setRenderPopupEdicion(SofisPopupUI renderPopupEdicion) {
        this.renderPopupEdicion = renderPopupEdicion;
    }

    public String getCantElementosPorPagina() {
        return cantElementosPorPagina;
    }

    public void setCantElementosPorPagina(String cantElementosPorPagina) {
        this.cantElementosPorPagina = cantElementosPorPagina;
    }

    public String getElementoOrdenacion() {
        return elementoOrdenacion;
    }

    public void setElementoOrdenacion(String elementoOrdenacion) {
        this.elementoOrdenacion = elementoOrdenacion;
    }

    public int getAscendente() {
        return ascendente;
    }

    public void setAscendente(int ascendente) {
        this.ascendente = ascendente;
    }

    public String getFiltroNombre() {
        return filtroNombre;
    }

    public void setFiltroNombre(String filtroNombre) {
        this.filtroNombre = filtroNombre;
    }

    public ProcedimientoCompra getProcedimientoCompraEnEdicion() {
        return procedimientoCompraEnEdicion;
    }

    public void setFuenteEnEdicion(ProcedimientoCompra procedimientoCompraEnEdicion) {
        this.procedimientoCompraEnEdicion = procedimientoCompraEnEdicion;
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    @PostConstruct
    public void init() {

        /*
            *   31-05-2018 Nico: Se sacan las variables que se inicializan del constructor y se pasan al PostConstruct
         */
        try {
            filtroNombre = "";
            listaImportada = new ArrayList();
            proyectosExpandidos = new HashMap<>();
            adquisicionesExpandidas = new HashMap<>();
            proyectosMap = new HashMap<>();
            listaErrores = new ArrayList<>();
            buscar();
            String browser = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getHeader("User-Agent");
            isFirefox = browser.contains("Firefox");
            fuentesFinanciamientoHabilitadas = new ArrayList<>();
            for (FuenteFinanciamiento ff : fuenteFinanciamientoDelegate.obtenerFuentesHabilitadasPorOrgId(inicioMB.getOrganismoSeleccionado())) {
                fuentesFinanciamientoHabilitadas.add(new FuenteFinanciamientoTO(ff.getFuePk(), ff.getFueNombre()));
            }

            List<TipoRegistroCompra> tipoRegistroCompra = Arrays.asList(TipoRegistroCompra.values());
            listaTipoRegistroCompraCombo = new SofisComboG(tipoRegistroCompra, "text");
            listaTipoRegistroCompraCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
            listaTipoRegistroCompraCombo.setSelectedT(TipoRegistroCompra.COMPRA_ORIGINAL); // default COMPRA_ORIGINAL
            FiltroTipoAdquisicionTO filtroAdquisicion = new FiltroTipoAdquisicionTO();
            filtroAdquisicion.setHabilitado(Boolean.TRUE);
            filtroAdquisicion.setOrganismo(inicioMB.getOrganismo());
            List<TipoAdquisicion> tipoAdquisiciones = tipoAdquisicionDelegate.obtenerPorFiltro(filtroAdquisicion);
            listaTipoAdquisicionCombo = new SofisComboG<>(tipoAdquisiciones, "tipAdqNombre");
            listaTipoAdquisicionCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

            listaMonedas = monedaDelegate.obtenerMonedas();
            if (listaMonedas != null && !listaMonedas.isEmpty()) {
                listaMonedaCombo = new SofisCombo((List) listaMonedas, "monSigno");
                listaMonedaCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
            }
            datosCargados = false;
        } catch (Exception e) {
        }

    }

    /**
     * Action agregar.
     *
     * @return
     */
    public String agregar() {
        procedimientoCompraEnEdicion = new ProcedimientoCompra();
        renderPopupEdicion.abrir();
        return null;
    }

    public void expandirProyecto(ProyectoTO proyecto) {

        proyectosExpandidos.put(proyecto.getId(), true);
    }

    public void colapsarProyecto(ProyectoTO proyecto) {

        proyectosExpandidos.put(proyecto.getId(), false);
    }

    public void expandirAdquisicion(AdquisicionTO adquisicion, ProyectoTO proyecto) {

        adquisicionesExpandidas.get(proyecto.getId()).put(adquisicion.getId(), true);
    }

    public void colapsarAdquisicion(AdquisicionTO adquisicion, ProyectoTO proyecto) {

        adquisicionesExpandidas.get(proyecto.getId()).put(adquisicion.getId(), false);
    }

    public void verAdquisicion(AdquisicionTO adquisicion) {

        adquisicionEnVisualizacion = misAdquisicionesDelegate.obtenerAdquisicion(adquisicion.getId());
    }

    //Acá va lo operación de carga masiva
    public String agregarArchivo() {
        renderPopupCargaArchivo.abrir();
        return null;
    }

    public String guardarImportacionEnBase() {
        confirmado = false;
        listaResultado = new ArrayList<>();
        for (Object o : paginator.getModel()) {
            ProyectoTO p = (ProyectoTO) o;

            Proyectos proyectoBase = proyectoDelegate.obtenerProyPorId(p.getId());
            Presupuesto presupuesto = proyectoBase.getProyPreFk();
            ResultadoTO resultadoOk = new ResultadoTO();

            resultadoOk.setProyectoPk(proyectoBase.getProyPk());
            Integer cantidadAdquisicionesOk = 0;

            for (AdquisicionTO ad : p.getAdquisiciones()) {
                if (ad.getDetalleError().isEmpty()) {
                    cantidadAdquisicionesOk++;
                    Adquisicion adquisicionNueva = new Adquisicion();
                    adquisicionNueva.setAdqFechaEsperadaInicioEjecucion(ad.getFechaEsperadaInicioEjecucion());
                    adquisicionNueva.setAdqFechaEstimadaInicioCompra(ad.getFechaEstimadaInicioCompra());
                    adquisicionNueva.setAdqCompartida(false);
                    Integer idAdquisicion = ad.getIdAdquisicion();
                    Integer idAdquisicionSugerido = idAdquisicion;
                    AdquisicionRango adquisicionRangoSeleccionado = null;
                    if (idAdquisicion.equals(0)) {
                        try {
                            if (proyectoBase.getProyAreaFk() != null && proyectoBase.getObjetivoEstrategico() != null) {
                                List<AdquisicionRango> listarPorOrganismoAreaDivision = adqRangoDelegate.listarPorOrganismoAreaDivision(proyectoBase.getProyOrgFk().getOrgPk(), proyectoBase.getProyAreaFk().getAreaPk(), proyectoBase.getObjetivoEstrategico().getObjEstPk());
                                if (!listarPorOrganismoAreaDivision.isEmpty()) {
                                    adquisicionRangoSeleccionado = listarPorOrganismoAreaDivision.get(0);
                                    idAdquisicionSugerido = listarPorOrganismoAreaDivision.get(0).getAdrUltimo();
                                    adquisicionNueva.setAdqIdAdquisicion(idAdquisicionSugerido);
                                } else {
                                    List<AdquisicionRango> listarPorOrganismoYArea = adqRangoDelegate.listarPorOrganismoYArea(proyectoBase.getProyOrgFk().getOrgPk(), proyectoBase.getProyAreaFk().getAreaPk());

                                    if (!listarPorOrganismoYArea.isEmpty()) {
                                        adquisicionRangoSeleccionado = listarPorOrganismoYArea.get(0);
                                        idAdquisicionSugerido = listarPorOrganismoYArea.get(0).getAdrUltimo();
                                        adquisicionNueva.setAdqIdAdquisicion(idAdquisicionSugerido);
                                    }
                                }
                            } else {
                                List<AdquisicionRango> listarPorOrganismoYArea = adqRangoDelegate.listarPorOrganismoYArea(proyectoBase.getProyOrgFk().getOrgPk(), proyectoBase.getProyAreaFk().getAreaPk());

                                if (!listarPorOrganismoYArea.isEmpty()) {
                                    adquisicionRangoSeleccionado = listarPorOrganismoYArea.get(0);
                                    idAdquisicionSugerido = listarPorOrganismoYArea.get(0).getAdrUltimo();
                                    adquisicionNueva.setAdqIdAdquisicion(idAdquisicionSugerido);
                                }
                            }

                            if (adquisicionRangoSeleccionado != null) {

                                adquisicionRangoSeleccionado.setAdrChequear(true);

                                adqRangoDelegate.guardar(adquisicionRangoSeleccionado);

                            }
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ImportarAdquisicionMB.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        adquisicionNueva.setAdqIdAdquisicion(ad.getIdAdquisicion());
                    }

                    adquisicionNueva.setAdqFuente(new FuenteFinanciamiento(ad.getFuenteFinanciamiento().getId()));
                    adquisicionNueva.setAdqMoneda(new Moneda(ad.getMoneda().getId()));
                    adquisicionNueva.setAdqTipoAdquisicion(new TipoAdquisicion(ad.getTipo().getId()));
                    adquisicionNueva.setAdqNombre(ad.getNombre());
                    adquisicionNueva.setAdqPreFk(presupuesto);
                    adquisicionNueva.setAdqTipoRegistro(ad.getTipoRegistro());
                    adquisicionNueva.setAdqProcedimientoCompra(new ProcedimientoCompra(ad.getProcedimientoCompra().getId()));
                    Adquisicion adquisicionGuardada = adquisicionDelegate.guardarAdquisicion(adquisicionNueva, p.getId(), 2, inicioMB.getUsuario(), inicioMB.getOrganismoSeleccionado());
                    Integer cantidadPagosErroneos = 0;
                    for (PagoTO pg : ad.getPagos()) {

                        if (pg.getFechaPlanificada() != null && pg.getImportePlanificado() != null) {
                            Pagos pagoNuevo = new Pagos();
                            CronogramaTO obtenerCronogramaCompletoPorIdProyecto = cronogramaDelegate.obtenerCronogramaCompletoPorIdProyecto(proyectoBase.getProyPk());
                            pagoNuevo.setPagImportePlanificado(pg.getImportePlanificado());
                            pagoNuevo.setPagFechaPlanificada(pg.getFechaPlanificada());
                            pagoNuevo.setPagAdqFk(adquisicionGuardada);
                            List<EntregableTO> listantregables = obtenerCronogramaCompletoPorIdProyecto.getEntregables();
                            if (!listantregables.isEmpty()) {
                                Entregables entregable = new Entregables(listantregables.get(0).getId());
                                pagoNuevo.setEntregables(entregable);
                                pagoNuevo.setPagGasto(0);
                                pagoNuevo.setPagInversion(0);
                                pagosDelegate.guardarPago(pagoNuevo, p.getId(), null, inicioMB.getOrganismoSeleccionado());

                            }
                        } else {
                            cantidadPagosErroneos++;
                        }

                    }
                    if (cantidadPagosErroneos > 0) {
                        ResultadoTO resultadoError = new ResultadoTO();
                        resultadoError.setProyectoPk(p.getId());
                        resultadoError.setObservacion("En la adquisición " + ad.getNombre() + " no se pudieron cargar " + cantidadPagosErroneos + " pagos.");
                        listaResultado.add(resultadoError);
                    }

                } else {
                    ResultadoTO resultadoError = new ResultadoTO();
                    resultadoError.setProyectoPk(p.getId());
                    resultadoError.setObservacion("La adquisición " + ad.getNombre() + " no se pudo cargar, detalle del error: " + ad.getDetalleError());
                    listaResultado.add(resultadoError);
                }

            }
            if (cantidadAdquisicionesOk > 0) {
                resultadoOk.setObservacion("Se cargaron " + cantidadAdquisicionesOk + " adquisiciones correctamente.");
            } else {
                resultadoOk.setObservacion("No se cargó ninguna adquisición.");
            }

            listaResultado.add(resultadoOk);
        }
        paginator.getModel().clear();
        datosCargados = false;
        JSFUtils.agregarMsg("popupMsgMasiva", "importacion_masiva_exitosa", null);
        datosGuardado = true;
        return null;
    }

    //Acá termina la operación de carga masiva
    /**
     * Action eliminar un componente-producto.
     *
     * @return
     */
    public String eliminar(Integer procedimientoCompraPk) {
        return null;
    }

    public void expandirColapsarProyectos() {

        boolean expandir = !proyectosExpandidos.values().contains(true);

        for (Integer idProyecto : proyectosExpandidos.keySet()) {
            proyectosExpandidos.put(idProyecto, expandir);
        }
    }

    /**
     * Action Buscar.
     *
     * @return
     */
    public String buscar() {
        Map<String, Object> mapFiltro = new HashMap<String, Object>();
        mapFiltro.put("nombre", filtroNombre);
        return null;
    }

    public String editar(Integer procedimientoCompraPk) {
        Organismos org = inicioMB.getOrganismo();
        try {
            procedimientoCompraEnEdicion = procedimientoCompraDelegate.obtenerProcedimientoCompraPorPk(procedimientoCompraPk);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, null, ex);
            /*
                *  18-06-2018 Inspección de código.
             */

            //JSFUtils.agregarMsgs(POPUP_MSG, ex.getErrores());
            for (String iterStr : ex.getErrores()) {
                JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);
            }
        }

        renderPopupEdicion.abrir();
        return null;
    }

    public String guardar() {
        logger.log(Level.INFO, "1");
        procedimientoCompraEnEdicion.setProcCompOrgFk(inicioMB.getOrganismo());

        try {
            logger.log(Level.INFO, "2");

            procedimientoCompraEnEdicion = procedimientoCompraDelegate.guardarProcedimientoCompra(procedimientoCompraEnEdicion);

            if (procedimientoCompraEnEdicion != null) {
                logger.log(Level.INFO, "3");
                renderPopupEdicion.cerrar();
                buscar();
            } else {
                logger.log(Level.INFO, "3.1");
            }
        } catch (BusinessException be) {
            logger.log(Level.SEVERE, be.getMessage(), be);

            logger.log(Level.INFO, "4");

            /*
                *  18-06-2018 Inspección de código.
             */
            //JSFUtils.agregarMsgs(BUSQUEDA_MSG, be.getErrores());
            for (String iterStr : be.getErrores()) {
                JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);
            }
            logger.log(Level.INFO, "5");
        }
        return null;
    }

    public void subirArchivo(FileEntryEvent event) {
        JSFUtils.removerMensages();
        exelCargaMasiva = null;
        errorEnTipoArchivo = false;
        logger.info("[subirArchivoAction] Subiendo archivo...");

        if (event != null && event.getComponent() != null) {
            FileEntry archEntry = (FileEntry) event.getComponent();
            FileEntryResults results = archEntry.getResults();

            File archivo = results.getFiles() != null ? results.getFiles().get(0).getFile() : null;
            if (archivo == null) {
                /*
                    *  18-06-2018 Inspección de código.
                 */
                //JSFUtils.agregarMsg("popupMsgMasiva", "error_archivo_subido_fail", null);
                JSFUtils.agregarMsgError("popupMsgMasiva", Labels.getValue("error_archivo_subido_fail_carga_masiva"), null);
                return;
            }
            try {
                if (archivo.getName().contains(".csv")) {
                    Path path = archivo.toPath();
                    String mimeType = Files.probeContentType(path);
                    URLConnection connection = archivo.toURL().openConnection();
                    mimeType = connection.getContentType();
                    logger.log(Level.INFO, mimeType);

                    if (mimeType != null) {

                        if (mimeType.contains("csv")) {
                            exelCargaMasiva = archivo;
                        } else if (mimeType.contains("unknown")) {
                            CSVParser csvParser = null;
                            try {
                                csvParser = new CSVParser(ReaderFactory.createBufferedReader(archivo), CSVFormat.DEFAULT);
                                if (csvParser != null && csvParser.getRecords().size() > 1) {
                                    exelCargaMasiva = archivo;

                                } else {
                                    if (!isFirefox) {
                                        JSFUtils.agregarMsgError("popupMsgMasiva", Labels.getValue("error_upload_file_carga_masiva_tipo_archivo"), null);

                                    }
                                    errorEnTipoArchivo = true;
                                }
                            } catch (IOException iOException) {
                                if (!isFirefox) {
                                    JSFUtils.agregarMsgError("popupMsgMasiva", Labels.getValue("error_upload_file_carga_masiva_tipo_archivo"), null);

                                }
                                errorEnTipoArchivo = true;
                            }

                        } else {
                            errorEnTipoArchivo = true;
                            if (!isFirefox) {
                                JSFUtils.agregarMsgError("popupMsgMasiva", Labels.getValue("error_upload_file_carga_masiva_tipo_archivo"), null);

                            }
                        }

                    } else {
                        errorEnTipoArchivo = true;
                        if (!isFirefox) {
                            JSFUtils.agregarMsgError("popupMsgMasiva", Labels.getValue("error_upload_file_carga_masiva_tipo_archivo"), null);

                        }
                    }

                    if (!errorEnTipoArchivo) {
                        JSFUtils.agregarMsg("popupMsgMasiva", "upload_file_carga_masiva_archivo_cargado", null);
                    }

                } else {
                    /*
                        *  18-06-2018 Inspección de código.
                     */
                    //JSFUtils.agregarMsg("popupMsgMasiva", "error_upload_file_carga_masiva_tipo_archivo", null);
                    JSFUtils.agregarMsgError("popupMsgMasiva", Labels.getValue("error_upload_file_carga_masiva_tipo_archivo"), null);
                }
            } catch (Exception exec) {
                logger.log(Level.SEVERE, exec.getMessage(), exec);
                /*
                    *  18-06-2018 Inspección de código.
                 */
                //JSFUtils.agregarMsg("popupMsgMasiva", "error_subir_archivo", null);
                JSFUtils.agregarMsgError("popupMsgMasiva", Labels.getValue("error_subir_archivo"), null);
            }

        }
    }

    public String cargaMasiva() {
        logger.info("cargaMasiva");
        datosCargados = true;
        proyectosMap.clear();
        listaErrores.clear();
        if (exelCargaMasiva != null) {
            try {

                CSVParser csvParser = new CSVParser(ReaderFactory.createBufferedReader(exelCargaMasiva), CSVFormat.EXCEL.withDelimiter(';'));
                boolean cambiarDelimitador = false;

                for (CSVRecord csvRecord : csvParser) {
                    if ((csvRecord.get(0) != null)) {
                        if (csvRecord.size() < 2) {
                            cambiarDelimitador = true;
                            break;

                        } else {
                            break;
                        }
                    }
                }
                
                if (cambiarDelimitador){
                    csvParser = new CSVParser(ReaderFactory.createBufferedReader(exelCargaMasiva), CSVFormat.EXCEL.withDelimiter(','));
                }

                boolean primeraLinea = false;
                boolean descartarHastaProximaAdquisicion = false;

                boolean esAdquisicion = false;
                boolean esPago = false;
                boolean proyectoSinEntregables = false;
                AdquisicionTO adquisicionActual = null;
                Integer numAdquisicion = 0;
                Integer numLinea = 0;
                Integer numPago = 0;
                for (CSVRecord csvRecord : csvParser) {
                    numLinea++;
                    esAdquisicion = false;
                    esPago = false;
                    proyectoSinEntregables = false;
                    try {

                        if ((csvRecord.get(0) != null)) {
                            if (csvRecord.get(0).startsWith("A") || csvRecord.get(0).startsWith("P")) {
                                if (!primeraLinea) {
                                    if (csvRecord.get(0).startsWith("P")) {
                                        descartarHastaProximaAdquisicion = true;
                                    }
                                    primeraLinea = true;

                                }

                                esAdquisicion = csvRecord.get(0).startsWith("A");
                                esPago = csvRecord.get(0).startsWith("P");

                                if (descartarHastaProximaAdquisicion && esAdquisicion) {
                                    descartarHastaProximaAdquisicion = false;
                                }

                                if (!descartarHastaProximaAdquisicion) {

                                    if (esAdquisicion && adquisicionActual != null) {
                                        //2 adquisiciones juntas
                                        //guardar adquisicionActual
                                        adquisicionActual = new AdquisicionTO();
                                        numAdquisicion++;
                                        adquisicionActual.setId(numAdquisicion);
                                    } else if (esAdquisicion && adquisicionActual == null) {
                                        //nueva adquisicion
                                        adquisicionActual = new AdquisicionTO();
                                        numAdquisicion++;
                                        adquisicionActual.setId(numAdquisicion);

                                    } else if (esPago) {
                                        numPago++;
                                        PagoTO pagoTO = new PagoTO();
                                        pagoTO.setLineaError(numLinea);
                                        pagoTO.setId(numPago);

                                        ProyectoTO proyecto = proyectosMap.get(ultimaPkProyecto);
                                        CronogramaTO obtenerCronogramaCompletoPorIdProyecto = cronogramaDelegate.obtenerCronogramaCompletoPorIdProyecto(ultimaPkProyecto);

                                        List<EntregableTO> listantregables = obtenerCronogramaCompletoPorIdProyecto.getEntregables();

                                        if (listantregables.isEmpty()) {
                                            guardarError("El Proyecto: (" + proyecto.getId() + ") " + proyecto.getNombre() + " no tiene entregables, no es posible cargar pagos", numLinea);
                                            proyectoSinEntregables = true;
                                        } else {
                                            try {
                                                SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
                                                //SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
                                                Date fechaFromCSV = fromUser.parse(csvRecord.get(1));
                                                // String format = myFormat.format(fechaFromCSV);
                                                //Date fechaFinal = myFormat.parse(format);

                                                pagoTO.setFechaPlanificada(fechaFromCSV);
                                            } catch (Exception exception) {
                                                pagoTO.setTieneErrorImportacion(true);
                                                proyecto.setErrorImportando(true);

                                                guardarError("En Proyecto: (" + proyecto.getId() + ") " + proyecto.getNombre() + ", la adquisicion " + adquisicionActual.getNombre() + " tiene mal Fecha Planificada en un pago", numLinea);

                                            }
                                            try {
                                                pagoTO.setImportePlanificado(Double.valueOf(csvRecord.get(2)));
                                            } catch (Exception exception) {
                                                pagoTO.setTieneErrorImportacion(true);
                                                proyecto.setErrorImportando(true);
                                                guardarError("En Proyecto: (" + proyecto.getId() + ") " + proyecto.getNombre() + ", la adquisicion " + adquisicionActual.getNombre() + "   tiene mal importe en un pago", numLinea);

                                            }
                                            proyectosMap.put(ultimaPkProyecto, proyecto);

                                            adquisicionActual.getPagos().add(pagoTO);
                                        }

                                    }

                                    if (esAdquisicion) {
                                        Proyectos proy = null;
                                        Integer proyectoPk = 0;
                                        try {
                                            proyectoPk = Integer.valueOf(csvRecord.get(10));
                                            proy = proyectoDelegate.obtenerProyPorId(proyectoPk);
                                        } catch (Exception exception) {

                                        }

                                        if (proy != null) {

                                            if (proy.getProyEstFk().isEstado(3) || proy.getProyEstFk().isEstado(2)) {
                                                ProyReplanificacion obtenerUltimaSolicitud = proyReplanificacionDelegate.obtenerUltimaSolicitud(proyectoPk);
                                                boolean habilitadoReplan = obtenerUltimaSolicitud == null ? true : obtenerUltimaSolicitud.isProyreplanPermitEditar();

                                                if (habilitadoReplan) {
                                                    if ((inicioMB.getUsuario().getUsuId().equals(proy.getProyUsrGerenteFk().getUsuId())
                                                            || inicioMB.getUsuario().getUsuId().equals(proy.getProyUsrAdjuntoFk().getUsuId())
                                                            || inicioMB.getUsuario().getUsuId().equals(proy.getProyUsrPmofedFk().getUsuId()))) {
                                                        adquisicionActual.setLineaError(numLinea);
                                                        ProyectoTO prTO = new ProyectoTO();
                                                        prTO.setId(proy.getProyPk());
                                                        prTO.setNombre(proy.getProyNombre());

                                                        if (proyectosMap.get(prTO.getId()) != null) {
                                                            prTO = proyectosMap.get(prTO.getId());
                                                        }
                                                        //Nombre Adquisicion
                                                        adquisicionActual.setNombre(csvRecord.get(1));

                                                        //Nombre Fuente Financiamento
                                                        Map<String, Object> filtroAux = null;
                                                        try {
                                                            String nombreFuente = csvRecord.get(2);
                                                            filtroAux = new HashMap();
                                                            filtroAux.put("nombre", nombreFuente);
                                                            List<FuenteFinanciamiento> listaFuentes = fuenteFinanciamientoDelegate.busquedaFuenteFiltro(inicioMB.getOrganismoSeleccionado(), filtroAux, "fuePk", 0);

                                                            if (listaFuentes.isEmpty()) {
                                                                prTO.setErrorImportando(true);
                                                                adquisicionActual.setDetalleError("Fuente Financiamiento");
                                                                guardarError("En Proyecto: (" + prTO.getId() + ") " + prTO.getNombre() + ", la adquisicion " + adquisicionActual.getNombre() + " tiene mal Fuente Financiamiento", numLinea);
                                                            } else {
                                                                FuenteFinanciamientoTO fuenteFinanciamientoTO = new FuenteFinanciamientoTO(!listaFuentes.isEmpty() ? listaFuentes.get(0).getFuePk() : 0, !listaFuentes.isEmpty() ? listaFuentes.get(0).getFueNombre() : "S/D");
                                                                adquisicionActual.setFuenteFinanciamiento(fuenteFinanciamientoTO);

                                                            }
                                                        } catch (Exception e) {

                                                            guardarError("En Proyecto: (" + prTO.getId() + ") " + prTO.getNombre() + ", la adquisicion " + adquisicionActual.getNombre() + " tiene mal Fuente Financiamiento", numLinea);
                                                        }

                                                        //Moneda
                                                        try {
                                                            String signoMoneda = csvRecord.get(3);
                                                            filtroAux.put("monSigno", signoMoneda);
                                                            List<Moneda> listaMonedas = monedaDelegate.busquedaMonedaFiltro(filtroAux, "monPk", 0, 1);

                                                            Moneda monedaAux = null;
                                                            for (Moneda m : listaMonedas) {

                                                                if (m.getMonSigno().equalsIgnoreCase(signoMoneda)) {
                                                                    monedaAux = m;
                                                                    break;
                                                                }

                                                            }
                                                            MonedaTO monedaTO = new MonedaTO(monedaAux != null ? monedaAux.getMonPk() : 0, monedaAux != null ? monedaAux.getMonNombre() : "S/D");
                                                            monedaTO.setSigno(signoMoneda);
                                                            adquisicionActual.setMoneda(monedaTO);

                                                        } catch (Exception e) {
                                                            prTO.setErrorImportando(true);

                                                            if (adquisicionActual.getDetalleError().isEmpty()) {
                                                                adquisicionActual.setDetalleError("Moneda");
                                                            } else {
                                                                adquisicionActual.setDetalleError(adquisicionActual.getDetalleError() + ", Moneda");
                                                            }

                                                            guardarError("En Proyecto: (" + prTO.getId() + ") " + prTO.getNombre() + ", la adquisicion " + adquisicionActual.getNombre() + " tiene mal Moneda", numLinea);
                                                        }

                                                        //Tipo Registro Compra
                                                        try {
                                                            List<TipoRegistroCompra> listaTipoRegistro = Arrays.asList(TipoRegistroCompra.values());
                                                            String tipoRegistro = csvRecord.get(4);
                                                            boolean encontroTipoRegistro = false;
                                                            for (TipoRegistroCompra trc : listaTipoRegistro) {

                                                                if (trc.getText().equalsIgnoreCase(tipoRegistro)) {
                                                                    adquisicionActual.setTipoRegistro(trc);
                                                                    encontroTipoRegistro = true;
                                                                    break;
                                                                }

                                                            }

                                                            if (!encontroTipoRegistro) {
                                                                prTO.setErrorImportando(true);

                                                                if (adquisicionActual.getDetalleError().isEmpty()) {
                                                                    adquisicionActual.setDetalleError("Tipo Registro Compra");
                                                                    guardarError("En Proyecto: (" + prTO.getId() + ") " + prTO.getNombre() + ", la adquisicion " + adquisicionActual.getNombre() + " tiene mal Tipo Registro Compra", numLinea);
                                                                } else {
                                                                    adquisicionActual.setDetalleError(adquisicionActual.getDetalleError() + ", Tipo Registro Compra");
                                                                    guardarError("En Proyecto: (" + prTO.getId() + ") " + prTO.getNombre() + ", la adquisicion " + adquisicionActual.getNombre() + " tiene mal Tipo Registro Compra", numLinea);
                                                                }
                                                            }
                                                        } catch (Exception e) {

                                                            guardarError("En Proyecto: (" + prTO.getId() + ") " + prTO.getNombre() + ", la adquisicion " + adquisicionActual.getNombre() + " tiene mal Tipo Registro Compra", numLinea);
                                                        }

                                                        //IdAdquisicion
                                                        try {
                                                            adquisicionActual.setIdAdquisicion(Integer.valueOf(csvRecord.get(5)));
                                                        } catch (Exception exception) {
                                                            prTO.setErrorImportando(true);

                                                            if (adquisicionActual.getDetalleError().isEmpty()) {
                                                                adquisicionActual.setDetalleError("IdAdquisicion");
                                                            } else {
                                                                adquisicionActual.setDetalleError(adquisicionActual.getDetalleError() + ", IdAdquisicion");
                                                            }
                                                            guardarError("En Proyecto: (" + prTO.getId() + ") " + prTO.getNombre() + ", la adquisicion " + adquisicionActual.getNombre() + " tiene mal IdAdquisicion", numLinea);

                                                        }

                                                        //Fecha Estimada Inicio Compra
                                                        Date fechaFromCSV = null;
                                                        SimpleDateFormat fromUser = null;
                                                        String format = null;
                                                        SimpleDateFormat myFormat = null;
                                                        Date fechaFinal = null;
                                                        try {
                                                            fromUser = new SimpleDateFormat("dd/MM/yyyy");
                                                            myFormat = new SimpleDateFormat("yyyy-MM-dd");

                                                            fechaFromCSV = fromUser.parse(csvRecord.get(6));
                                                            //  format = myFormat.format(fechaFromCSV);
                                                            //fechaFinal = myFormat.parse(format);
                                                            adquisicionActual.setFechaEstimadaInicioCompra(fechaFromCSV);
                                                        } catch (Exception parseException) {
                                                            prTO.setErrorImportando(true);

                                                            if (adquisicionActual.getDetalleError().isEmpty()) {
                                                                adquisicionActual.setDetalleError("Fecha Estimada Inicio Compra");
                                                            } else {
                                                                adquisicionActual.setDetalleError(adquisicionActual.getDetalleError() + ", Fecha Estimada Inicio Compra");
                                                            }
                                                            guardarError("En Proyecto: (" + prTO.getId() + ") " + prTO.getNombre() + ", la adquisicion " + adquisicionActual.getNombre() + " tiene mal Fecha Estimada Inicio Compra", numLinea);

                                                        }

                                                        //Fecha Estimada Inicio Ejecución
                                                        try {
                                                            fechaFromCSV = fromUser.parse(csvRecord.get(7));
                                                            //format = myFormat.format(fechaFromCSV);
                                                            // fechaFinal = myFormat.parse(format);
                                                            adquisicionActual.setFechaEsperadaInicioEjecucion(fechaFromCSV);
                                                        } catch (Exception exception) {
                                                            prTO.setErrorImportando(true);

                                                            if (adquisicionActual.getDetalleError().isEmpty()) {
                                                                adquisicionActual.setDetalleError("Fecha Estimada Inicio Ejecución");
                                                            } else {
                                                                adquisicionActual.setDetalleError(adquisicionActual.getDetalleError() + ", Fecha Estimada Inicio Ejecución");
                                                            }

                                                            guardarError("En Proyecto: (" + prTO.getId() + ") " + prTO.getNombre() + ", la adquisicion " + adquisicionActual.getNombre() + " tiene mal Fecha Estimada Inicio Ejecución", numLinea);

                                                        }

                                                        //Tipo Adquisicion
                                                        try {
                                                            String tipoAdquisicionNombre = csvRecord.get(8);
                                                            FiltroTipoAdquisicionTO filtro = new FiltroTipoAdquisicionTO();
                                                            filtro.setOrganismo(inicioMB.getOrganismo());
                                                            filtro.setNombre(tipoAdquisicionNombre);
                                                            List<TipoAdquisicion> listaTipoAdquisicion = tipoAdquisicionDelegate.obtenerPorFiltro(filtro);

                                                            TipoAdquisicionTO tipoAdquisicionTO = new TipoAdquisicionTO(!listaTipoAdquisicion.isEmpty() ? listaTipoAdquisicion.get(0).getTipAdqPk() : 0, !listaTipoAdquisicion.isEmpty() ? listaTipoAdquisicion.get(0).getTipAdqNombre() : "S/D");

                                                            adquisicionActual.setTipo(tipoAdquisicionTO);

                                                            if (listaTipoAdquisicion.isEmpty()) {
                                                                prTO.setErrorImportando(true);

                                                                if (adquisicionActual.getDetalleError().isEmpty()) {
                                                                    adquisicionActual.setDetalleError("Tipo Adquisición");
                                                                } else {
                                                                    adquisicionActual.setDetalleError(adquisicionActual.getDetalleError() + ", Tipo Adquisición");
                                                                }
                                                                guardarError("En Proyecto: (" + prTO.getId() + ") " + prTO.getNombre() + ", la adquisicion " + adquisicionActual.getNombre() + " tiene mal Tipo Adquisición", numLinea);

                                                            }
                                                        } catch (Exception e) {
                                                            guardarError("En Proyecto: (" + prTO.getId() + ") " + prTO.getNombre() + ", la adquisicion " + adquisicionActual.getNombre() + " tiene mal Tipo Adquisición", numLinea);
                                                        }

                                                        try {
                                                            String signoMoneda = csvRecord.get(9).trim();
                                                            Map<String, Object> filtroFuentes = new HashMap<>();
                                                            filtroFuentes.put("procCompNombre", signoMoneda);

                                                            List<ProcedimientoCompra> listaProcedimientoCompra = procedimientoCompraDelegate.busquedaProcedimientoCompraFiltro(inicioMB.getOrganismoSeleccionado(), filtroFuentes, "procCompNombre", 1);
                                                            if (listaProcedimientoCompra != null && !listaProcedimientoCompra.isEmpty()) {
                                                                listaProcedimientoCompraCombo = new SofisCombo((List) listaProcedimientoCompra, "procCompNombre");
                                                                listaProcedimientoCompraCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
                                                            }

                                                            ProcedimientoCompra procedimientoAux = null;
                                                            for (ProcedimientoCompra ff : listaProcedimientoCompra) {

                                                                if (ff.getProcCompNombre().toUpperCase().equalsIgnoreCase(signoMoneda.toUpperCase())) {
                                                                    procedimientoAux = ff;
                                                                    break;
                                                                }

                                                            }
                                                            ProcedimientoCompraTO procedimientoTO = new ProcedimientoCompraTO(procedimientoAux != null ? procedimientoAux.getProcCompPk() : 0, procedimientoAux != null ? procedimientoAux.getProcCompNombre() : "S/D");

                                                            if (procedimientoAux == null) {
                                                                throw new Exception();
                                                            }

                                                            adquisicionActual.setProcedimientoCompra(procedimientoTO);

                                                        } catch (Exception e) {
                                                            prTO.setErrorImportando(true);

                                                            if (adquisicionActual.getDetalleError().isEmpty()) {
                                                                adquisicionActual.setDetalleError("Procedimiento de compra");
                                                            } else {
                                                                adquisicionActual.setDetalleError(adquisicionActual.getDetalleError() + ", Procedimiento de compra");
                                                            }

                                                            guardarError("En Proyecto: (" + prTO.getId() + ") " + prTO.getNombre() + ", la adquisicion " + adquisicionActual.getNombre() + " tiene mal Procedimiento de compra", numLinea);
                                                        }

                                                        prTO.getAdquisiciones().add(adquisicionActual);
                                                        proyectosMap.put(prTO.getId(), prTO);
                                                        ultimaPkProyecto = prTO.getId();

                                                    } else {
                                                        guardarError("Usuario no pertenece a proyecto (" + proy.getProyPk() + ") " + proy.getProyNombre(), numLinea);
                                                        descartarHastaProximaAdquisicion = true;
                                                    }
                                                } else {
                                                    guardarError("Proyecto (" + proy.getProyPk() + ") " + proy.getProyNombre() + " en Re Planificación sin habiitación de presupuesto", numLinea);
                                                    descartarHastaProximaAdquisicion = true;
                                                }

                                            } else {
                                                guardarError("Proyecto (" + proy.getProyPk() + ") " + proy.getProyNombre() + " no se encuentra en fase Inicio o Planificacion", numLinea);
                                                descartarHastaProximaAdquisicion = true;
                                            }

                                        } else {
                                            guardarError("No se encontró Proyecto", numLinea);
                                            descartarHastaProximaAdquisicion = true;
                                        }

                                    } else if (esPago && !proyectoSinEntregables) {

                                        ProyectoTO proyecto = proyectosMap.get(ultimaPkProyecto);

                                        if (!proyecto.getAdquisiciones().isEmpty()) {
                                            int indexOfLastElement = proyecto.getAdquisiciones().size() - 1;
                                            proyecto.getAdquisiciones().remove(indexOfLastElement);

                                        }

                                        proyecto.getAdquisiciones().add(adquisicionActual);
                                        proyectosMap.put(ultimaPkProyecto, proyecto);
                                    }

                                }
                            }

                        }
                    } catch (Exception exec) {
                        exec.printStackTrace();
                    }
                }
                List<ProyectoTO> lista = new ArrayList();

                for (Map.Entry<Integer, ProyectoTO> entry : proyectosMap.entrySet()) {
                    lista.add(entry.getValue());
                }

                for (ProyectoTO proyecto : lista) {
                    proyectosExpandidos.put(proyecto.getId(), false);

                    Map<Integer, Boolean> adquisicionesExpandidasProyecto = new HashMap<>();

                    for (AdquisicionTO adquisicion : proyecto.getAdquisiciones()) {
                        adquisicionesExpandidasProyecto.put(adquisicion.getId(), true);
                    }

                    adquisicionesExpandidas.put(proyecto.getId(), adquisicionesExpandidasProyecto);
                }

                paginator = new RepeatPaginator(lista);

                JSFUtils.agregarMsg(null, "upload_file_carga_masiva_exitosa", null);
                renderPopupCargaArchivo.cerrar();
            } catch (Exception exec) {
                //Este catch me lo pidio para cuando crea el FileInputStream
                logger.log(Level.SEVERE, exec.getMessage(), exec);
                JSFUtils.agregarMsgError("popupMsgMasiva", Labels.getValue("error_carga_masiva_proc_comp"), null);
            }
        } else {
            /*
                *  18-06-2018 Inspección de código.
             */
            //JSFUtils.agregarMsg("popupMsgMasiva", "error_upload_file_carga_masiva", null);

            if (isFirefox) {

                if (errorEnTipoArchivo) {
                    JSFUtils.agregarMsgError("popupMsgMasiva", Labels.getValue("error_upload_file_carga_masiva_tipo_archivo"), null);
                    errorEnTipoArchivo = false;
                } else {
                    JSFUtils.agregarMsgError("popupMsgMasiva", Labels.getValue("error_upload_file_carga_masiva"), null);
                }

            } else {
                JSFUtils.agregarMsgError("popupMsgMasiva", Labels.getValue("error_upload_file_carga_masiva"), null);
            }

        }
        datosCargados = true;
        confirmado = false;
        datosGuardado = false;

        return null;
    }

    public List<PagoTO> obtenerPagosVisibles(AdquisicionTO adquisicion) {

        return adquisicion.getPagos();
    }

    public void expandirColapsarAdquisiciones(ProyectoTO proyecto) {

        Map<Integer, Boolean> adquisicionesExpandidasProyecto = adquisicionesExpandidas.get(proyecto.getId());

        boolean expandir = !adquisicionesExpandidasProyecto.values().contains(true);

        for (Integer idAdquisicion : adquisicionesExpandidasProyecto.keySet()) {
            adquisicionesExpandidasProyecto.put(idAdquisicion, expandir);
        }
    }

    public void actualizarHabilitado(ProcedimientoCompra procCompParam) {
        try {
            procCompParam.setProcCompHabilitado(!(procCompParam.getProcCompHabilitado()));
            procedimientoCompraDelegate.guardarProcedimientoCompra(procCompParam);
        } catch (BusinessException be) {
            logger.log(Level.SEVERE, be.getMessage(), be);

            /*
                *  18-06-2018 Inspección de código.
             */
            //JSFUtils.agregarMsgs(BUSQUEDA_MSG, be.getErrores());
            for (String iterStr : be.getErrores()) {
                JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);
            }
        }
    }

    public void cancelar() {
        if (renderPopupEdicion.isRenderPopup()) {
            renderPopupEdicion.cerrar();
        }
        if (renderPopupCargaArchivo.isRenderPopup()) {
            this.exelCargaMasiva = null;
            renderPopupCargaArchivo.cerrar();
        }
    }

    /**
     * Action limpiar formulario de busqueda.
     *
     * @return
     */
    public String limpiar() {
        filtroNombre = null;
        elementoOrdenacion = AREAS_NOMBRE;
        ascendente = 1;

        return null;
    }

    public void cambiarCantPaginas(ValueChangeEvent evt) {
        buscar();
    }

    public void cambiarCriterioOrdenacion(ValueChangeEvent evt) {
        elementoOrdenacion = evt.getNewValue().toString();
        buscar();
    }

    public void cambiarAscendenteOrdenacion(ValueChangeEvent evt) {
        ascendente = Integer.valueOf(evt.getNewValue().toString());
        buscar();
    }

    public void editarAdquisicion(AdquisicionTO adquisicion) {

        try {
            adquisicionEnEdicion = (AdquisicionTO) adquisicion.clone();
        } catch (CloneNotSupportedException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        FuenteFinanciamientoTO fuenteFinanciamiento = cargarComboFuenteFinanciamientoAdquisicion(adquisicion);

        listaTipoRegistroCompraCombo.setSelectedT(adquisicion.getTipoRegistro());

        if (adquisicion.getTipo() != null) {
            TipoAdquisicion ta = tipoAdquisicionDelegate.obtenerPorId(adquisicion.getTipo().getId());
            listaTipoAdquisicionCombo.setSelectedT(ta);
        }

        if (adquisicion.getMoneda() != null) {
            listaMonedaCombo.getItems();
            listaMonedaCombo.setSelected(adquisicion.getMoneda().getId());
        }

        if (adquisicion.getProcedimientoCompra() != null) {
            listaProcedimientoCompraCombo.setSelected(adquisicion.getProcedimientoCompra().getId());
        }

    }

    public boolean isDatosCargados() {
        return datosCargados;
    }

    public void setDatosCargados(boolean datosCargados) {
        this.datosCargados = datosCargados;
    }

    public boolean isIsFirefox() {
        return isFirefox;
    }

    public void setIsFirefox(boolean isFirefox) {
        this.isFirefox = isFirefox;
    }

    public List<AdquisicionTO> getListaImportada() {
        return listaImportada;
    }

    public void setListaImportada(List<AdquisicionTO> listaImportada) {
        this.listaImportada = listaImportada;
    }

    public RepeatPaginator getPaginator() {
        return paginator;
    }

    public void setPaginator(RepeatPaginator paginator) {
        this.paginator = paginator;
    }

    public Map<Integer, Boolean> getProyectosExpandidos() {
        return proyectosExpandidos;
    }

    public void setProyectosExpandidos(Map<Integer, Boolean> proyectosExpandidos) {
        this.proyectosExpandidos = proyectosExpandidos;
    }

    public AdquisicionTO getAdquisicionEnEdicion() {
        return adquisicionEnEdicion;
    }

    public void setAdquisicionEnEdicion(AdquisicionTO adquisicionEnEdicion) {
        this.adquisicionEnEdicion = adquisicionEnEdicion;
    }

    public AdquisicionTO getAdquisicionEnVisualizacion() {
        return adquisicionEnVisualizacion;
    }

    public void setAdquisicionEnVisualizacion(AdquisicionTO adquisicionEnVisualizacion) {
        this.adquisicionEnVisualizacion = adquisicionEnVisualizacion;
    }

    public Map<Integer, Map<Integer, Boolean>> getAdquisicionesExpandidas() {
        return adquisicionesExpandidas;
    }

    public void setAdquisicionesExpandidas(Map<Integer, Map<Integer, Boolean>> adquisicionesExpandidas) {
        this.adquisicionesExpandidas = adquisicionesExpandidas;
    }

    private FuenteFinanciamientoTO cargarComboFuenteFinanciamientoAdquisicion(AdquisicionTO adquisicion) {
        List<FuenteFinanciamientoTO> fuentesFinanciamiento = new ArrayList<>(fuentesFinanciamientoHabilitadas);
        FuenteFinanciamientoTO fuenteFinanciamiento = adquisicion.getFuenteFinanciamiento();

        if (fuenteFinanciamiento != null && !fuentesFinanciamiento.contains(fuenteFinanciamiento)) {

            fuentesFinanciamiento.add(fuenteFinanciamiento);
        }

        FuenteFinanciamientoTOUtils.sortByNombre(fuentesFinanciamiento);
        fuenteFinanciamientoAdquisicionCombo = new SofisComboG<>(fuentesFinanciamiento, "nombre");
        fuenteFinanciamientoAdquisicionCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

        if (fuenteFinanciamiento != null) {
            fuenteFinanciamientoAdquisicionCombo.setSelectedT(fuenteFinanciamiento);
        } else {
            fuenteFinanciamientoAdquisicionCombo.setSelected(-1);
        }

        return fuenteFinanciamiento;
    }

    public void cambioFuenteFinanciamiento(ValueChangeEvent ev) {

        adquisicionEnEdicion.setCausalCompra(null);

        FuenteFinanciamientoTO fuenteFinanciamiento = null;
        Integer id = (Integer) ev.getNewValue();

        for (FuenteFinanciamientoTO ff : fuentesFinanciamientoHabilitadas) {

            if (ff.getId().equals(id)) {
                fuenteFinanciamiento = ff;
                break;
            }
        }

    }

    public void cancelarEdicionAdquisicion(AdquisicionTO adquisicion) {

        adquisicionEnEdicion = null;
    }

    public void guardarAdquisicion(AdquisicionTO adquisicion, ProyectoTO proyecto) {

        if (fuenteFinanciamientoAdquisicionCombo.getSelectedT() != null) {
            FuenteFinanciamientoTO fuenteFinanciamientoTO = new FuenteFinanciamientoTO(fuenteFinanciamientoAdquisicionCombo.getSelected(), fuenteFinanciamientoAdquisicionCombo.getSelectedT().getNombre());

            adquisicionEnEdicion.setFuenteFinanciamiento(fuenteFinanciamientoTO);
        }

        Moneda m = (Moneda) listaMonedaCombo.getSelectedObject();
        if (m != null) {
            MonedaTO monedaTO = new MonedaTO(listaMonedaCombo.getSelected(), m.getMonNombre());
            monedaTO.setSigno(m.getMonSigno());
            adquisicionEnEdicion.setMoneda(monedaTO);
        }

        TipoAdquisicion tipoAdquisicion = listaTipoAdquisicionCombo.getSelectedT();
        if (tipoAdquisicion != null) {
            TipoAdquisicionTO tipoAdquisicionTO = new TipoAdquisicionTO(tipoAdquisicion.getTipAdqPk(), tipoAdquisicion.getTipAdqNombre());
            adquisicionEnEdicion.setTipo(tipoAdquisicionTO);
        }

        adquisicionEnEdicion.setTipoRegistro(listaTipoRegistroCompraCombo.getSelectedT());
        ProcedimientoCompra pc = (ProcedimientoCompra) listaProcedimientoCompraCombo.getSelectedObject();

        if (pc != null) {
            adquisicionEnEdicion.setProcedimientoCompra(new ProcedimientoCompraTO(pc.getProcCompPk(), pc.getProcCompNombre()));
        }

        if (validarObligatorios(adquisicionEnEdicion)) {
            adquisicionEnEdicion.setDetalleError("");
            Collections.replaceAll(proyecto.getAdquisiciones(), adquisicion, adquisicionEnEdicion);

            ProyectoTO proyectoAux = proyecto;
            boolean proyectoSinErrores = true;
            for (AdquisicionTO ad : proyectoAux.getAdquisiciones()) {

                if (!proyectoSinErrores) {
                    break;
                }
                if (!ad.getDetalleError().isEmpty()) {
                    proyectoSinErrores = false;
                }
                for (PagoTO pg : ad.getPagos()) {
                    if (pg.getTieneErrorImportacion() != null && pg.getTieneErrorImportacion()) {
                        proyectoSinErrores = false;
                    }
                }

            }

            ErrorImportTO error = new ErrorImportTO();
            List<ErrorImportTO> listaErroresAux = new ArrayList();

            for (ErrorImportTO er : listaErrores) {
                if (!er.getLinea().equals(adquisicionEnEdicion.getLineaError())) {
                    listaErroresAux.add(er);
                }
            }
            listaErrores.clear();
            listaErrores.addAll(listaErroresAux);

            if (proyectoSinErrores) {
                List<Object> listaModelo = (List<Object>) paginator.getModel();

                for (Object o : listaModelo) {
                    ProyectoTO p = (ProyectoTO) o;
                    if (p.getId().equals(proyectoAux.getId())) {
                        p.setErrorImportando(false);
                        break;
                    }

                }

                paginator.changeModel(listaModelo);
            }
        }
        listaMonedaCombo.setSelected(-1);
        listaTipoAdquisicionCombo.setSelected(-1);
        listaTipoRegistroCompraCombo.setSelected(-1);
        listaProcedimientoCompraCombo.setSelected(-1);
        adquisicionEnEdicion = null;

    }

    public SofisComboG<FuenteFinanciamientoTO> getFuenteFinanciamientoAdquisicionCombo() {
        return fuenteFinanciamientoAdquisicionCombo;
    }

    public void setFuenteFinanciamientoAdquisicionCombo(SofisComboG<FuenteFinanciamientoTO> fuenteFinanciamientoAdquisicionCombo) {
        this.fuenteFinanciamientoAdquisicionCombo = fuenteFinanciamientoAdquisicionCombo;
    }

    public SofisComboG<TipoRegistroCompra> getListaTipoRegistroCompraCombo() {
        return listaTipoRegistroCompraCombo;
    }

    public void setListaTipoRegistroCompraCombo(SofisComboG<TipoRegistroCompra> listaTipoRegistroCompraCombo) {
        this.listaTipoRegistroCompraCombo = listaTipoRegistroCompraCombo;
    }

    public SofisComboG<TipoAdquisicion> getListaTipoAdquisicionCombo() {
        return listaTipoAdquisicionCombo;
    }

    public void setListaTipoAdquisicionCombo(SofisComboG<TipoAdquisicion> listaTipoAdquisicionCombo) {
        this.listaTipoAdquisicionCombo = listaTipoAdquisicionCombo;
    }

    public SofisCombo getListaMonedaCombo() {
        return listaMonedaCombo;
    }

    public void setListaMonedaCombo(SofisCombo listaMonedaCombo) {
        this.listaMonedaCombo = listaMonedaCombo;
    }

    private boolean validarObligatorios(AdquisicionTO adquisicionEnEdicion) {
        if (adquisicionEnEdicion.getFuenteFinanciamiento() == null) {
            return false;
        } else if (adquisicionEnEdicion.getFuenteFinanciamiento().getId() < 1) {
            return false;
        }

        if (adquisicionEnEdicion.getMoneda() == null) {
            return false;
        }
        if (adquisicionEnEdicion.getTipo() == null) {
            return false;
        }
        if (adquisicionEnEdicion.getTipoRegistro() == null) {
            return false;
        }

        if (adquisicionEnEdicion.getFechaEsperadaInicioEjecucion() == null) {
            return false;
        }
        if (adquisicionEnEdicion.getFechaEstimadaInicioCompra() == null) {
            return false;
        }

        if (adquisicionEnEdicion.getProcedimientoCompra() == null) {
            return false;
        }
        return true;
    }

    public void editarPago(PagoTO pago) {

        try {
            pagoEnEdicion = (PagoTO) pago.clone();
        } catch (CloneNotSupportedException ex) {

        }

    }

    public void guardarPago(PagoTO pago, AdquisicionTO adquisicion, ProyectoTO proyecto) {

        try {

            if (pagoEnEdicion.getFechaPlanificada() != null && pagoEnEdicion.getImportePlanificado() != null) {
                pagoEnEdicion.setTieneErrorImportacion(false);
                Collections.replaceAll(adquisicion.getPagos(), pago, pagoEnEdicion);
                List<ErrorImportTO> listaErroresAux = new ArrayList();

                for (ErrorImportTO er : listaErrores) {
                    if (!er.getLinea().equals(pagoEnEdicion.getLineaError())) {
                        listaErroresAux.add(er);
                    }
                }
                listaErrores.clear();
                listaErrores.addAll(listaErroresAux);
                ProyectoTO proyectoAux = proyecto;
                boolean proyectoSinErrores = true;
                for (AdquisicionTO ad : proyectoAux.getAdquisiciones()) {

                    if (!proyectoSinErrores) {
                        break;
                    }

                    if (!ad.getDetalleError().isEmpty()) {
                        proyectoSinErrores = false;
                    }

                    for (PagoTO pg : ad.getPagos()) {
                        if (pg.getTieneErrorImportacion() != null && pg.getTieneErrorImportacion()) {
                            proyectoSinErrores = false;
                        }

                    }

                }

                if (proyectoSinErrores) {
                    List<Object> listaModelo = (List<Object>) paginator.getModel();

                    for (Object o : listaModelo) {
                        ProyectoTO p = (ProyectoTO) o;
                        if (p.getId().equals(proyectoAux.getId())) {
                            p.setErrorImportando(false);
                            break;
                        }

                    }

                    paginator.changeModel(listaModelo);
                }

            }

            pagoEnEdicion = null;

        } catch (BusinessException | TechnicalException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void guardarError(String msj, Integer numLinea) {
        ErrorImportTO error = new ErrorImportTO();
        error.setLinea(numLinea);
        error.setObservacion(msj);
        listaErrores.add(error);
    }

    public List<ErrorImportTO> getListaErrores() {
        return listaErrores;
    }

    public void setListaErrores(List<ErrorImportTO> listaErrores) {
        this.listaErrores = listaErrores;
    }

    public PagoTO getPagoEnEdicion() {
        return pagoEnEdicion;
    }

    public void setPagoEnEdicion(PagoTO pagoEnEdicion) {
        this.pagoEnEdicion = pagoEnEdicion;
    }

    public void cancelarEdicionPago(PagoTO pago) {

        pagoEnEdicion = null;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public boolean isDatosGuardado() {
        return datosGuardado;
    }

    public void setDatosGuardado(boolean datosGuardado) {
        this.datosGuardado = datosGuardado;
    }

    public List<ResultadoTO> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<ResultadoTO> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public SofisCombo getListaProcedimientoCompraCombo() {
        return listaProcedimientoCompraCombo;
    }

    public void setListaProcedimientoCompraCombo(SofisCombo listaProcedimientoCompraCombo) {
        this.listaProcedimientoCompraCombo = listaProcedimientoCompraCombo;
    }

}
