package com.sofis.web.mb;

import com.icesoft.faces.context.effects.JavascriptContext;
import com.sofis.business.ejbs.ProyReplanificacionBean;
import com.sofis.business.utils.NumbersUtils;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Ambito;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.ProgIndicesPre;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.ProyIndicesPre;
import com.sofis.entities.data.ProyReplanificacion;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.RolesInteresados;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.ColoresCodigosEnum;
import com.sofis.entities.enums.NivelEnum;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.tipos.FiltroInicioItem;
import com.sofis.entities.tipos.FiltroInicioResultadoTO;
import com.sofis.entities.tipos.FiltroInicioTO;
import com.sofis.entities.utils.FichaUtils;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.exceptions.GeneralException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.delegates.AreasDelegate;
import com.sofis.web.delegates.BusquedaFiltroDelegate;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.MonedaDelegate;
import com.sofis.web.delegates.PresupuestoDelegate;
import com.sofis.web.delegates.ProgProyDelegate;
import com.sofis.web.delegates.ProgramasDelegate;
import com.sofis.web.delegates.ProgramasProyectosDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.delegates.TipoDocumentoInstanciaDelegate;
import com.sofis.web.genericos.constantes.ConstantesPresentacion;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisComboItem;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.tree.DefaultMutableTreeNode;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.icefaces.ace.event.ExpansionChangeEvent;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "ProgramasProyectosMB")
@ViewScoped
public class ProgramasProyectosMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @ManagedProperty("#{aplicacionMB}")
    private AplicacionMB aplicacionMB;

    @Inject
    private ProgramasDelegate programasDelegate;
    @Inject
    private ProyectosDelegate proyectosDelegate;
    @Inject
    private ProgramasProyectosDelegate programasProyectosDelegate;
    @Inject
    private ProgProyDelegate progProyDelegate;
    @Inject
    private BusquedaFiltroDelegate busquedaFiltroDelegate;
    @Inject
    private AreasDelegate areasDelegate;
    @Inject
    private ConfiguracionDelegate configuracionDelegate;
    @Inject
    private ProyReplanificacionBean proyReplanificacionBean;
    @Inject
    private MonedaDelegate monedaDelegate;
    @Inject
    private TipoDocumentoInstanciaDelegate tipoDocumentoInstanciaDelegate;
    @Inject
    private PresupuestoDelegate presupuestoDelegate;

    private List<FichaTO> pendientes = new ArrayList<>();
    private List<FiltroInicioResultadoTO> filtroInicioResultado = new ArrayList<>();
    private String cantElementosPorPagina = "25";
    private OrganiIntProve organizacion;
    private Estados estado;
    private Integer tipoFicha;
    private Integer presupuesto;
    private Areas area;
    private Integer reportes;
    private static String GROUP_NAME = "everyone";
//    private PortableRenderer portableRenderer;
//    private long TIEMPO_ACTUALIZACION = 3000;
    private List<Moneda> monedasUsadas = new ArrayList<>();
    // Replanificación
    private ProyReplanificacion replanificacion;
    private Boolean renderPopupReplanificacion = false;
    private Map<String, String> configs;

    //Filtro al cual corresponde el listado actual
    FiltroInicioTO filtroActual = null;

    public ProgramasProyectosMB() {
        // PushRenderer.addCurrentView(GROUP_NAME);
        // portableRenderer = PushRenderer.getPortableRenderer();
    }

    public OrganiIntProve getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(OrganiIntProve organizacion) {
        this.organizacion = organizacion;
    }

    public void setAplicacionMB(AplicacionMB aplicacionMB) {
        this.aplicacionMB = aplicacionMB;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public Integer getTipoFicha() {
        return tipoFicha;
    }

    public void setTipoFicha(Integer tipoFicha) {
        this.tipoFicha = tipoFicha;
    }

    public Integer getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Integer presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Areas getArea() {
        return area;
    }

    public void setArea(Areas area) {
        this.area = area;
    }

    public Integer getReportes() {
        return reportes;
    }

    public void setReportes(Integer reportes) {
        this.reportes = reportes;
    }

    public List<FichaTO> getPendientes() {
        return pendientes;
    }

    public void setPendientes(List<FichaTO> pendientes) {
        this.pendientes = pendientes;
    }

    public String getCantElementosPorPagina() {
        return cantElementosPorPagina;
    }

    public void setCantElementosPorPagina(String cantElementosPorPagina) {
        this.cantElementosPorPagina = cantElementosPorPagina;
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public List<FiltroInicioResultadoTO> getFiltroInicioResultado() {
        return filtroInicioResultado;
    }

    public void setFiltroInicioResultado(List<FiltroInicioResultadoTO> filtroInicioResultado) {
        this.filtroInicioResultado = filtroInicioResultado;
    }

    public List<Moneda> getMonedasUsadas() {
        return monedasUsadas;
    }

    public void setMonedasUsadas(List<Moneda> monedasUsadas) {
        this.monedasUsadas = monedasUsadas;
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

    @PostConstruct
    public void init() {
        configs = cargarConfigLimites(inicioMB.getOrganismo().getOrgPk());
        findPendientes();
        buscarAction();

    }

    public void findPendientes() {
        pendientes.clear();
        if (inicioMB.getUsuario() != null && inicioMB.getOrganismo() != null) {
            pendientes.addAll(programasProyectosDelegate.obtenerProyProgPendientes(inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk()));
        }
    }

    public void programaExpand(ExpansionChangeEvent event) {
        PhaseId phaseId = event.getPhaseId();
        if (phaseId.equals(PhaseId.ANY_PHASE)) {
            event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
            event.queue();
        } else if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES)) {
            if (event.isExpanded()) {
                final Integer orgPk = inicioMB.getOrganismo().getOrgPk();
//                final Map<String, String> configs = cargarConfigLimites(orgPk);

                FiltroInicioItem item = (FiltroInicioItem) event.getRowData();
                for (Map.Entry<FiltroInicioItem, List> item1 : item.getInicioResultado().getPrimerNivel()) {
                    if (item1.getKey().getFichaFk().equals(item.getFichaFk()) && item1.getKey().getTipoFicha().equals(item.getTipoFicha())) {
                        inicioMB.getFiltro().setProgPk(item.getFichaFk());
                        inicioMB.getFiltro().setActivo(Boolean.TRUE);
                        final FiltroInicioResultadoTO resultado = programasProyectosDelegate.obtenerSegundoNivel(inicioMB.getOrganismo().getOrgPk(), item.getAreaId(), inicioMB.getUsuario(), inicioMB.getFiltro());

                        inicioMB.getFiltro().setProgPk(null);
                        if (resultado != null && resultado.getPrimerNivel() != null && !resultado.getPrimerNivel().isEmpty()) {
                            item1.setValue(resultado.getPrimerNivel());
                        }

                        for (Entry e : resultado.getPrimerNivel()) {
                            FiltroInicioItem fii = (FiltroInicioItem) e.getKey();
                            //TODO solo valido para llamada local al ejb, si es remote no funciona
                            programasProyectosDelegate.obtenerIndicadoresMaterializados(fii, orgPk, configs);
                        }

//                        Thread obtenerIndicadoresThread = new Thread(new Runnable() {
//                            @Override
//                            public void run() {
////                                long tiempo = new Date().getTime();
//                                for (Entry e : resultado.getPrimerNivel()) {
//                                    FiltroInicioItem item = (FiltroInicioItem) e.getKey();
//                                    //TODO solo valido para llamada local al ejb, si es remote no funciona
//                                    programasProyectosDelegate.obtenerIndicadoresMaterializados(item, orgPk, configs);
////                                    programasProyectosDelegate.calcularIndicadores(item, orgPk, configs);
//                                }
////                                logger.log(Level.INFO, "Tiempo obtener Ind Materializados:" + (new Date().getTime() - tiempo));
//                                //COMENTADO AHORA
//                                //portableRenderer.render(GROUP_NAME);
//                            }
//                        });
//                        obtenerIndicadoresThread.start();
                        break;
                    }
                }
            }
        }
    }

    public Map<String, String> cargarConfigLimites(Integer orgPk) {
        Map<String, String> configs = new HashMap<>();
        configs.put(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO, configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO, orgPk).getCnfValor());
        configs.put(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_ROJO, configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_ROJO, orgPk).getCnfValor());
        configs.put(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_AMARILLO, configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_AMARILLO, orgPk).getCnfValor());
        configs.put(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_ROJO, configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_ROJO, orgPk).getCnfValor());
        configs.put(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_AMARILLO, configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_AMARILLO, orgPk).getCnfValor());
        configs.put(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_ROJO, configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_ROJO, orgPk).getCnfValor());
        configs.put(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO, configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO, orgPk).getCnfValor());
        configs.put(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO, configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO, orgPk).getCnfValor());
        configs.put(ConfiguracionCodigos.ESTADO_INICIO_LIMITE_AMARILLO, configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.ESTADO_INICIO_LIMITE_AMARILLO, orgPk).getCnfValor());
        configs.put(ConfiguracionCodigos.ESTADO_INICIO_LIMITE_ROJO, configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.ESTADO_INICIO_LIMITE_ROJO, orgPk).getCnfValor());
        configs.put(ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_AMARILLO, configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_AMARILLO, orgPk).getCnfValor());
        configs.put(ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_ROJO, configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_ROJO, orgPk).getCnfValor());

        return configs;
    }

    public void buscarAction() {
        if (inicioMB.getOrganismo() != null) {

            filtroActual = inicioMB.getFiltro();

            filtroInicioResultado.clear();

            inicioMB.setAreasTematicasToFiltro();
            inicioMB.obtenerCombosSeleccionados();
            inicioMB.setSelectedCombosFiltro();

            final Integer orgPk = inicioMB.getOrganismo().getOrgPk();
            final SsUsuario usuario = inicioMB.getUsuario();

            if (usuario == null) {
                return;
            }
            //para cada area comienzo a cargar los datos
            //si el filtro tiene una area seteada entonces solo es para esa area
            //si el filtro no tiene area seteada es para todas las areas
            Integer areaOrganizacion = inicioMB.getFiltro().getAreasOrganizacion() != null ? inicioMB.getFiltro().getAreasOrganizacion().getAreaPk() : null;
            final List<Areas> areas;

            if (areaOrganizacion != null && areaOrganizacion > 0) {
                areas = new ArrayList();
                areas.add(new Areas(areaOrganizacion));
            } else if (inicioMB.getFiltro().getPorArea() != null && inicioMB.getFiltro().getPorArea().equals(Boolean.TRUE)) {
                areas = areasDelegate.obtenerAreasPorOrganismo(orgPk, false);
//                areas = aplicacionMB.obtenerAreasPorOrganismo(orgPk);
            } else {
                areas = null;
            }

            final FiltroInicioTO filtro = inicioMB.getFiltro();
            filtro.setActivo(Boolean.TRUE);
            Configuracion confAmarillo = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_AMARILLO, orgPk);
            Configuracion confRojo = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_ROJO, orgPk);

            if (areas != null) {
                for (Areas ar : areas) {
                    final FiltroInicioResultadoTO resultado = programasProyectosDelegate.obtenerPrimerNivel(orgPk, ar, usuario, filtro, confAmarillo, confRojo);
                    if (resultado.getPrimerNivel() != null && !resultado.getPrimerNivel().isEmpty()) {
                        filtroInicioResultado.add(resultado);
                    }
                }
            } else {
                final FiltroInicioResultadoTO resultado = programasProyectosDelegate.obtenerPrimerNivel(orgPk, null, usuario, filtro, confAmarillo, confRojo);
                filtroInicioResultado.add(resultado);
            }
//            logger.log(Level.INFO, "Tiempo busqueda Prog/Proy:" + (new Date().getTime() - tiempoBusqueda)+"ms.");

            FiltroInicioItem item = null;

            for (final FiltroInicioResultadoTO resultado : filtroInicioResultado) {
                for (Entry e : resultado.getPrimerNivel()) {
                    item = (FiltroInicioItem) e.getKey();
                    //TODO solo valido para llamada local al ejb, si es remote no funciona
                    programasProyectosDelegate.obtenerIndicadoresMaterializados(item, orgPk, configs);
//                    programasProyectosDelegate.calcularIndicadores(item, orgPk, configs);
                }
            }
        }
    }

    public void limpiarFiltro() {
        inicioMB.filtroPorDefecto();
    }

    /**
     * Obtiene el filtro guardado y lo carga en el Filtro.
     */
    public void obtenerFiltroBusqueda() {
        inicioMB.setFiltro(busquedaFiltroDelegate.obtenerFiltroInicio(inicioMB.getUsuario(), inicioMB.getOrganismo()));
        inicioMB.setSelectedCombosFiltro();
    }

    /**
     * Guarda los criterios de busqueda del filtro.
     */
    public void guardarFiltro() {
        inicioMB.obtenerCombosSeleccionados();
        try {
            busquedaFiltroDelegate.guardar(inicioMB.getFiltro(), inicioMB.getUsuario(), inicioMB.getOrganismo());
            JSFUtils.agregarMsg("filtroBusquedaMsg", "info_filtro_guardado", null);
        } catch (GeneralException ge) {
            logger.log(Level.SEVERE, null, ge);
            JSFUtils.agregarMsg("filtroBusquedaMsg", "error_filtro_guardar", null);
        }
    }

    public String guardarAprobarFichaAction(FiltroInicioItem item) {
        logger.log(Level.FINE, "Guardar y aprobar ficha:{0}, tipo:{1}", new Object[]{item.getFichaFk(), item.getTipoFicha()});
        try {
            if (FichaUtils.isPrograma(item)) {
                Programas prog = programasDelegate.obtenerProgPorId(item.getFichaFk());
                guardarAprobacion(prog);

            } else if (FichaUtils.isProyecto(item)) {
                Proyectos proy = proyectosDelegate.obtenerProyPorId(item.getFichaFk());
                proy = (Proyectos) guardarAprobacion(proy);
                item.setEstado(proy.getProyEstFk());
                item.setEstadoPendiente(proy.getProyEstPendienteFk());
                item.setSolCambioFase(proy.isEstPendienteFk());
            }

        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            JSFUtils.agregarMsg(ConstantesPresentacion.MESSAGE_ID_POPUP, "warn_aprobacion_fallo", null);
            JSFUtils.agregarMsgs(ConstantesPresentacion.MESSAGE_ID_POPUP, ex.getErrores());
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        }

        return null;
    }

    private Object guardarAprobacion(Object progProy) throws GeneralException {
        progProy = progProyDelegate.guardarAprobacion(progProy, inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
        JSFUtils.agregarMsg(ConstantesPresentacion.MESSAGE_ID_POPUP, "info_ficha_aprobacion_guardada", null);
        inicioMB.setRenderPopupMensajes(Boolean.TRUE);

        return progProy;
    }

    public String retrocederEstadoFichaAction(FiltroInicioItem item) {
        if (FichaUtils.isProyecto(item)) {
            replanificacion = new ProyReplanificacion();
            replanificacion.setProyectoFk(new Proyectos(item.getFichaFk()));
            replanificacion.setProyreplanActivo(true);
            replanificacion.setItem(item);
            SsUsuario usuario = inicioMB.getUsuario();
            Integer orgPk = inicioMB.getOrganismo().getOrgPk();

            if (item.getEstado().isEstado(Estados.ESTADOS.EJECUCION.estado_id)) {
                if (SsUsuariosUtils.isUsuarioPMOF(item, usuario, orgPk)) {
                    renderPopupReplanificacion = true;
                } else if (usuario.isUsuarioPMOT(orgPk)) {
                    if (item.getEstadoPendiente() != null
                            && item.getEstadoPendiente().isEstado(Estados.ESTADOS.PLANIFICACION.estado_id)) {
                        replanificacion = proyReplanificacionBean.obtenerUltimaSolicitud(item.getFichaFk());
                        if (replanificacion == null) {
                            replanificacion = new ProyReplanificacion();
                        }
                        replanificacion.setItem(item);
                        replanificacion.setProyreplanActivo(true);
                        renderPopupReplanificacion = true;
                    } else {
                        replanificacion = new ProyReplanificacion();
                        replanificacion.setProyectoFk(new Proyectos(item.getFichaFk()));
                        replanificacion.setItem(item);
                        replanificacion.setProyreplanActivo(true);
                        renderPopupReplanificacion = true;
                    }
                } else {
                    JSFUtils.agregarMsg(ConstantesPresentacion.MESSAGE_ID_POPUP, "error_modificar_estado", null);
                    inicioMB.setRenderPopupMensajes(Boolean.TRUE);
                }
            } else {
                this.guardarRetrocederEstado();
//                buscarAction();
            }
        }
        return null;
    }

    public void replanificacionGenerarLineaBaseChange(ValueChangeEvent ev) {
//        System.out.println("CALL replanificacionGenerarLineaBaseChange");
        replanificacion.setProyreplanGenerarLineaBase((Boolean) ev.getNewValue());
        if (!replanificacion.isProyreplanGenerarLineaBase()) {
            replanificacion.setProyreplanHistorial(false);
            replanificacion.setProyreplanDesc("");
        }
    }

    public void replanificacionProyreplanHistorialChange(ValueChangeEvent ev) {
//        System.out.println("CALL replanificacionProyreplanHistorialChange");
        replanificacion.setProyreplanHistorial((Boolean) ev.getNewValue());
        if (!replanificacion.getProyreplanHistorial()) {
            replanificacion.setProyreplanDesc("");
        }
    }

    public String guardarRetrocederEstado() {
        try {

            if (replanificacion != null) {
                replanificacion.setProyreplanHistorial(replanificacion.isProyreplanGenerarLineaBase() && replanificacion.getProyreplanHistorial());
                replanificacion.setProyreplanDesc(replanificacion.getProyreplanHistorial() ? replanificacion.getProyreplanDesc() : "");
            }

            if (replanificacion != null && replanificacion.getProyreplanHistorial() && StringsUtils.isEmpty(replanificacion.getProyreplanDesc())) {
                JSFUtils.agregarMsg(FichaMB.REPLANIFICACION_POPUP_MSG, "error_ficha_msg_retroceder_descripcion_obligatorio", null);
                return null;
            }

            Proyectos proy = progProyDelegate.guardarRetrocederEstado(replanificacion.getProyectoFk().getProyPk(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk(), replanificacion);
            replanificacion.getItem().setEstado(proy.getProyEstFk());
            replanificacion.getItem().setEstadoPendiente(proy.getProyEstPendienteFk());
            replanificacion.getItem().setSolCambioFase(proy.getProyEstPendienteFk() != null);
            renderPopupReplanificacion = false;
            JSFUtils.agregarMsg(ConstantesPresentacion.MESSAGE_ID_POPUP, "ficha_msg_retroceder_estado", null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
//            buscarAction();
        } catch (GeneralException ge) {
            logger.log(Level.SEVERE, ge.getMessage());
            JSFUtils.agregarMsgs(ConstantesPresentacion.MESSAGE_ID_POPUP, ge.getErrores());
        }

        return null;
    }

    public String cancelarReplanificacion() {

        System.out.println("ProgramasProyectosMB.cancelarReplanificacion()");
        return null;
    }

    public String cerrarPopupReplanificacion() {
        renderPopupReplanificacion = false;
        return null;
    }

    public String eliminarPendienteAction(FichaTO fichaTO) {
        try {
            Object progProy = progProyDelegate.eliminarProgProy(fichaTO, inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());

            if ((progProy instanceof Programas && !((Programas) progProy).isActivo())
                    || (progProy instanceof Proyectos && !((Proyectos) progProy).isActivo())) {
                JSFUtils.agregarMsg(ConstantesPresentacion.MESSAGE_ID_POPUP, "info_ficha_eliminada", null);
                inicioMB.setRenderPopupMensajes(Boolean.TRUE);
                pendientes.remove(fichaTO);

            } else if ((progProy instanceof Programas && !((Programas) progProy).getProgEstFk().isEstado(Estados.ESTADOS.SOLICITUD_CANCELAR_PMOT.estado_id))
                    || (progProy instanceof Proyectos && !((Proyectos) progProy).getProyEstFk().isEstado(Estados.ESTADOS.SOLICITUD_CANCELAR_PMOT.estado_id))) {
                JSFUtils.agregarMsg(ConstantesPresentacion.MESSAGE_ID_POPUP, "info_ficha_pend_cancelar", null);
                inicioMB.setRenderPopupMensajes(Boolean.TRUE);
                pendientes.remove(fichaTO);
            }

        } catch (GeneralException ge) {
            logger.log(Level.SEVERE, ge.getMessage());
            JSFUtils.agregarMsg("pendientesMsg", ge.getMessage(), null);
        }

        return "";
    }

    public String inicioTooltipNombreFicha(FiltroInicioItem item) {
        StringBuffer result = new StringBuffer();
        if (item != null) {
            result.append(Labels.getValue("editarTooltip")).append(": ").append(item.getNombre());

            if (item.getPeso() != null) {
                result.append(ConstantesPresentacion.SALTO_LINEA)
                        .append(Labels.getValue(FichaUtils.isPrograma(item) ? "tooltip_ini_peso_total" : "tooltip_ini_peso"))
                        .append(": ")
                        .append(item.getPeso().toString());
            }

            if (FichaUtils.isProyecto(item)) {
                Double porcentajePeso = item.getIndicesProy() != null ? item.getIndicesProy().getProyindPorcPesoTotal() : null;
                if (porcentajePeso != null && porcentajePeso > 0) {
                    result = result.append(" (")
                            .append(NumbersUtils.formatDouble((porcentajePeso)))
                            .append("%)");
                }
            }
        }

        return result.toString();
    }

    public String inicioTooltipAvanceFinalizado(FiltroInicioItem item) {
        StringBuffer result = new StringBuffer();
        if (item != null) {
            int[] indAvance = item.getIndiceAvanceFinalizado();
            /**
             * 14-12-2016: Siempre se tiene que mostrar los tooltip por más que
             * alguno de sus colores sea 0.
             */
//            if (indAvance[0] != 0 && indAvance[1] != 0 && indAvance[2] != 0) {
            result = result.append(Labels.getValue("crono_ind_avance_finalizado"))
                    .append(ConstantesPresentacion.SALTO_LINEA)
                    .append(Labels.getValue("crono_ind_azul")).append(": ")
                    .append(String.valueOf(indAvance[0])).append("%")
                    .append(ConstantesPresentacion.SALTO_LINEA)
                    .append(Labels.getValue("crono_ind_verde")).append(": ")
                    .append(String.valueOf(indAvance[1])).append("%")
                    .append(ConstantesPresentacion.SALTO_LINEA)
                    .append(Labels.getValue("crono_ind_rojo")).append(": ")
                    .append(String.valueOf(indAvance[2])).append("%");
//            }
        }
        return result.toString();
    }

    public String inicioTooltipAvanceParcial(FiltroInicioItem item) {
        StringBuffer result = new StringBuffer();
        if (item != null) {
            int[] indAvance = item.getIndiceAvanceParcial();
            /**
             * 14-12-2016: Siempre se tiene que mostrar los tooltip por más que
             * alguno de sus colores sea 0.
             */
//            if (indAvance[0] != 0 && indAvance[1] != 0 && indAvance[2] != 0) {
            result = result.append(Labels.getValue("crono_ind_avance_parcial"))
                    .append(ConstantesPresentacion.SALTO_LINEA)
                    .append(Labels.getValue("crono_ind_azul")).append(": ")
                    .append(String.valueOf(indAvance[0])).append("%")
                    .append(ConstantesPresentacion.SALTO_LINEA)
                    .append(Labels.getValue("crono_ind_verde")).append(": ")
                    .append(String.valueOf(indAvance[1])).append("%")
                    .append(ConstantesPresentacion.SALTO_LINEA)
                    .append(Labels.getValue("crono_ind_rojo")).append(": ")
                    .append(String.valueOf(indAvance[2])).append("%");
//            }
        }
        return result.toString();
    }

    public String inicioTooltipMetodologia(FiltroInicioItem item) {
        StringBuffer result = new StringBuffer();
        if (item != null) {
            Double indiceEstado = null;
            if (FichaUtils.isPrograma(item)) {
                if (item.getIndicesProy() == null) {
                    Programas prog = programasDelegate.obtenerProgPorId(item.getFichaFk());
                    item.setIndicesProg(prog.getProgIndices());
                }
                indiceEstado = item.getIndicesProg() != null && item.getIndicesProg().getProgindMetodologiaEstado() != null ? item.getIndicesProg().getProgindMetodologiaEstado() : null;
            } else if (FichaUtils.isProyecto(item)) {
                if (item.getIndicesProy() == null) {
                    Proyectos proy = proyectosDelegate.obtenerProyPorId(item.getFichaFk());
                    item.setIndicesProy(proy.getProyIndices());
                }
                indiceEstado = item.getIndicesProy() != null ? item.getIndicesProy().getProyindMetodologiaEstado() : null;
            }

            if (indiceEstado == null) {
                indiceEstado = 0d;
            }
            result = result.append(Labels.getValue("tooltip_doc_total")).append(": ")
                    .append(indiceEstado.toString()).append("%");

//            List<TipoDocumentoInstancia> listTdi = tipoDocumentoInstanciaDelegate.obtenerTipoDocInstResumen(item.getFichaFk(), item.getTipoFicha(), item.getEstado(), 5);
//            if (listTdi != null) {
//                for (TipoDocumentoInstancia tdi : listTdi) {
//                    result = result.append(ConstantesPresentacion.SALTO_LINEA)
//                            .append(tdi.getDocsEstado() != null ? tdi.getDocsEstado().toString() : String.valueOf(0d)).append(" ")
//                            .append(tdi.getTipodocInstTipoDocFk() != null ? tdi.getTipodocInstTipoDocFk().getTipodocNombre() : "");
//                }
//            }
        }
        return result.toString();
    }

    public String inicioTooltipPresupuesto(FiltroInicioItem item, Integer monPk) {
        StringBuffer result = new StringBuffer();
        if (item != null && monPk != null) {
            Moneda moneda = monedaDelegate.obtenerMonedaPorId(monPk);

            Date d = new Date();
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(d);
            Integer year = cal.get(Calendar.YEAR);

            Double total = null;
            Double anio = null;
            Double PV = null;
            Double AC = null;
            int estadoPre = 0;

            if (FichaUtils.isPrograma(item)) {
                Set<ProgIndicesPre> progIndPreSet = item.getIndicesProg() != null && item.getIndicesProg().getProgIndPreSet() != null ? item.getIndicesProg().getProgIndPreSet() : null;
                if (progIndPreSet != null && !progIndPreSet.isEmpty()) {
                    for (ProgIndicesPre progIndicesPre : progIndPreSet) {
                        if (monPk.equals(progIndicesPre.getProgindpreMonFk())) {
                            estadoPre = progIndicesPre.getProgindpreEstPre();
                            total = progIndicesPre.getProgindpreTotal();
                            anio = progIndicesPre.getProgindpreAnio();
                            PV = progIndicesPre.getProgindprePV();
                            AC = progIndicesPre.getProgindpreAC();
                        }
                    }
                }
            } else if (FichaUtils.isProyecto(item) && item.getPresupuesto() != null) {
                if (item.getIndicesProy() != null) {
                    Set<ProyIndicesPre> setPre = item.getIndicesProy().getProyIndPreSet();
                    for (ProyIndicesPre preInd : setPre) {
                        if (preInd.getProyindpreMonFk() == monPk) {
                            total = preInd.getProyindpreTotal();
                            estadoPre = preInd.getProyindpreEstPre();

                            anio = preInd.getProyindpreAnio();
                            PV = preInd.getProyindprePV();
                            AC = preInd.getProyindpreAC();
                        }
                    }
                }
            }

            if (estadoPre != 0 || total != null) {
                result = result.append(moneda.getMonSigno());
            }
            if (estadoPre != 0) {
                result = result.append(" - ")
                        .append(presupuestoEstadoACStr(estadoPre))
                        .append(ConstantesPresentacion.SALTO_LINEA)
                        .append(presupuestoDesviacionStr(estadoPre));
            }
            if (total != null) {
                result = result.append(ConstantesPresentacion.SALTO_LINEA)
                        .append(Labels.getValue("presupuesto_resumen_total")).append(": ")
                        .append(NumbersUtils.formatImporte(total));

                if (anio != null) {
                    result = result.append(ConstantesPresentacion.SALTO_LINEA)
                            .append(year.toString()).append(": ")
                            .append(NumbersUtils.formatImporte(anio));
                }
                if (PV != null) {
                    result = result.append(ConstantesPresentacion.SALTO_LINEA)
                            .append(Labels.getValue("presupuesto_resumen_pv")).append(": ")
                            .append(NumbersUtils.formatImporte(PV));
                }
                if (AC != null) {
                    result = result.append(ConstantesPresentacion.SALTO_LINEA)
                            .append(Labels.getValue("presupuesto_resumen_ac")).append(": ")
                            .append(NumbersUtils.formatImporte(AC));
                }
            }
        }
        return result.toString();
    }

    public String presupuestoEstadoACStr(int estadoPre) {
        if (estadoPre == ColoresCodigosEnum.VERDE.id) {
            return Labels.getValue("presupuesto_pv_verde");
        } else if (estadoPre == ColoresCodigosEnum.AMARILLO.id) {
            return Labels.getValue("presupuesto_pv_mas_amarillo");
        } else if (estadoPre == ColoresCodigosEnum.AMARILLO_MAS.id) {
            return Labels.getValue("presupuesto_pv_mas_amarillo");
        } else if (estadoPre == ColoresCodigosEnum.AMARILLO_MENOS.id) {
            return Labels.getValue("presupuesto_pv_menos_amarillo");
        } else if (estadoPre == ColoresCodigosEnum.NARANJA.id) {
            return Labels.getValue("presupuesto_pv_naranja");
        } else if (estadoPre == ColoresCodigosEnum.ROJO.id) {
            return Labels.getValue("presupuesto_pv_rojo");
        }
        return "";
    }

    public String presupuestoDesviacionStr(int estadoPre) {
        Integer limiteAmarillo = Integer.valueOf(configs.get(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO));
        Integer limiteRojo = Integer.valueOf(configs.get(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO));

        if (estadoPre == ColoresCodigosEnum.VERDE.id) {
            return String.format(Labels.getValue("presupuesto_menos_desviacion"), limiteAmarillo);
        } else if (estadoPre == ColoresCodigosEnum.AMARILLO.id) {
            return String.format(Labels.getValue("presupuesto_menos_desviacion"), limiteRojo);
        } else if (estadoPre == ColoresCodigosEnum.AMARILLO_MAS.id) {
            return String.format(Labels.getValue("presupuesto_menos_desviacion"), limiteRojo);
        } else if (estadoPre == ColoresCodigosEnum.AMARILLO_MENOS.id) {
            return String.format(Labels.getValue("presupuesto_menos_desviacion"), limiteRojo);
        } else if (estadoPre == ColoresCodigosEnum.NARANJA.id) {
            return String.format(Labels.getValue("presupuesto_mas_desviacion"), limiteRojo);
        } else if (estadoPre == ColoresCodigosEnum.ROJO.id) {
            return String.format(Labels.getValue("presupuesto_mas_desviacion"), limiteRojo);
        }
        return "";
    }

    public String exportarAction() {

        HSSFWorkbook planilla = new HSSFWorkbook();
        HSSFSheet hoja = planilla.createSheet("planilla");

        HSSFPalette palette = planilla.getCustomPalette();
        palette.setColorAtIndex(HSSFColor.BLUE.index, (byte) 3, (byte) 94, (byte) 159);
        HSSFColor colorAzul = palette.getColor(HSSFColor.BLUE.index);

        //Fuente blanca negrita
        HSSFFont fontWhiteBold = planilla.createFont();
        fontWhiteBold.setColor(HSSFColor.WHITE.index);
        fontWhiteBold.setBold(true);
        //Fuente negra negrita
        HSSFFont fontBlackBold = planilla.createFont();
        fontBlackBold.setColor(HSSFColor.BLACK.index);
        fontBlackBold.setBold(true);

        //Celda de filtro
        HSSFCellStyle filtroStyle = planilla.createCellStyle();
        filtroStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        filtroStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //Celda de filtro
        HSSFCellStyle filtroTituloStyle = planilla.createCellStyle();
        filtroTituloStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        filtroTituloStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        filtroTituloStyle.setFont(fontBlackBold);
        //Celda de area
        HSSFCellStyle areaStyle = planilla.createCellStyle();
        areaStyle.setFillForegroundColor(colorAzul.getIndex());
        areaStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        areaStyle.setFont(fontWhiteBold);
        //Celda de titulo de columna
        HSSFCellStyle tituloStyle = planilla.createCellStyle();
        tituloStyle.setFont(fontBlackBold);
        tituloStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        int filaNro = -1;

        //==================== Resumen de filtros =================
        HSSFRow fila = null;
        HSSFCell celda = null;
        //Organismo
        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(0);
        celda.setCellStyle(filtroTituloStyle);
        Organismos orgSelected = (Organismos) inicioMB.getOrganismosUsuario().getSelectedObject();
        celda.setCellValue(orgSelected.getOrgNombre());

        //Filtros basicos (fila 1)
        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_link_nivel") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);

        NivelEnum nivel = (NivelEnum) inicioMB.getListaNivelItemsCombo().getObjectById(filtroActual.getNivel());
        celda.setCellValue(nivel != null ? nivel.getLabel() : "");

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_ProgProy") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        celda.setCellValue(filtroActual.getNombre());

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_Area") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        Areas area = filtroActual.getAreasOrganizacion();
        celda.setCellValue(area != null ? area.getAreaNombre() : Labels.getValue("inicio_col_todas"));

        //Filtros basicos (fila 2)
        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_Gerente") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        SsUsuario gerente = (SsUsuario) inicioMB.getListaGerenteCombo().getObjectById(filtroActual.getGerenteOAdjunto());
        celda.setCellValue(gerente != null ? gerente.getNombreApellido() : Labels.getValue("inicio_col_todos"));

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_Sponsor") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        SsUsuario sponsor = (SsUsuario) inicioMB.getListaSponsorCombo().getObjectById(filtroActual.getSponsor());
        celda.setCellValue(sponsor != null ? sponsor.getNombreApellido() : Labels.getValue("inicio_col_todos"));

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_PMOF") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        //SsUsuario pmof = (SsUsuario)inicioMB.getListaPmoFederadaCombo().getSelectedObject();
        SsUsuario pmof = (SsUsuario) inicioMB.getListaPmoFederadaCombo().getObjectById(filtroActual.getPmoFederada());
        celda.setCellValue(pmof != null ? pmof.getNombreApellido() : Labels.getValue("inicio_col_todos"));

        //Filtros basicos (fila 3)
        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_ano") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        celda.setCellValue((filtroActual.getAnioDesde() != null ? filtroActual.getAnioDesde() : "") + " - " + (filtroActual.getAnioHasta() != null ? filtroActual.getAnioHasta() : ""));

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_fases") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        StringBuilder sb = new StringBuilder("");
        if (!filtroActual.getEstados().isEmpty()) {
            for (SelectItem si : inicioMB.getListaEstadosItem()) {
                if (filtroActual.getEstados().contains(si.getValue().toString())) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(si.getLabel());
                }
            }
        }
        celda.setCellValue(sb.toString());

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_areastematicas") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        sb = new StringBuilder("");
        for (Object areaTematica0 : inicioMB.getAreasTematicasSelected()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) areaTematica0;
            AreasTags areaTematica = (AreasTags) node.getUserObject();
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(areaTematica.getAreatagNombre());
        }
        celda.setCellValue(sb.toString());

        //Riesgos
        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(0);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_riesgos") + ": ");

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_exposicion") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        sb = new StringBuilder("");
        if (!filtroActual.getGradoRiesgo().isEmpty()) {
            for (SelectItem si : inicioMB.getListaGradoRiesgoItems()) {
                if (filtroActual.getGradoRiesgo().contains(si.getValue().toString())) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(si.getLabel());
                }
            }
        }
        celda.setCellValue(sb.toString());

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_altos") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        celda.setCellValue(filtroActual.getCantidadRiesgosAltos() != null ? filtroActual.getCantidadRiesgosAltos().toString() : "");

        //Presupuesto
        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(0);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_presupuesto") + ": ");

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_proveedor") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        OrganiIntProve proveedor = filtroActual.getOrgaProveedor();
        celda.setCellValue(proveedor != null ? proveedor.getOrgaNombre() : "");

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_fuente") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        FuenteFinanciamiento fuente = filtroActual.getFuenteFinanciamiento();
        celda.setCellValue(fuente != null ? fuente.getFueNombre() : "");

        //Interesados
        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(0);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_interesados") + ": ");

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_organizacion") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        OrganiIntProve organizacion = filtroActual.getInteresadoOrganizacion();
        celda.setCellValue(organizacion != null ? organizacion.getOrgaNombre() : "");

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_rol") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        RolesInteresados rol = (RolesInteresados) inicioMB.getListaIntRolCombo().getObjectById(filtroActual.getInteresadoRol());
        celda.setCellValue(rol != null ? rol.getRolintNombre() : "");

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_ambito") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        Ambito ambito = filtroActual.getInteresadoAmbitoOrganizacion();
        celda.setCellValue(ambito != null ? ambito.getAmbNombre() : "");

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_nombre") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        celda.setCellValue(filtroActual.getInteresadoNombre());

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(0);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_mostrarareas") + ": ");

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_agrupararea") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
        Boolean porArea = filtroActual.getPorArea();
        celda.setCellValue(porArea != null && porArea.booleanValue() ? Labels.getValue("sí") : Labels.getValue("no"));

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(0);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_calidad") + ": ");

        fila = hoja.createRow(++filaNro);
        fila.setRowStyle(filtroStyle);
        celda = fila.createCell(1);
        celda.setCellStyle(filtroTituloStyle);
        celda.setCellValue(Labels.getValue("inicio_col_indice") + ": ");
        celda = fila.createCell(2);
        celda.setCellStyle(filtroStyle);
//	Object indice = (Object)inicioMB.getListaCalIndiceCombo().getObjectById(filtroActual.getCalidadIndice());
        SofisComboItem indice = ((SofisComboItem) inicioMB.getListaCalIndiceCombo().getSelectedObject());
        celda.setCellValue(indice != null ? indice.getLabel() : "Todos");

        // =========================== Datos ==========================
        fila = hoja.createRow(++filaNro);
        fila = hoja.createRow(++filaNro);

        int nroColumnas = 3;

        if (filtroInicioResultado != null) {

            //Cargar todas las monedas
            List<Moneda> monedas = monedaDelegate.obtenerMonedas();

            nroColumnas = 14 + monedas.size();

            for (FiltroInicioResultadoTO itemArea : filtroInicioResultado) {

                if (itemArea.getArea() != null) {
                    fila = hoja.createRow(++filaNro);
                    fila.setRowStyle(areaStyle);
                    celda = fila.createCell(0);
                    celda.setCellStyle(areaStyle);
                    celda.setCellValue(itemArea.getArea().getAreaNombre());
                }

                //Encabezados
                fila = hoja.createRow(++filaNro);
                int celdaNro = 0;
                celda = fila.createCell(celdaNro++);
                celda.setCellStyle(tituloStyle);
                celda.setCellValue(Labels.getValue("inicio_col_Area"));
                celda = fila.createCell(celdaNro++);
                celda.setCellStyle(tituloStyle);
                celda.setCellValue(Labels.getValue("inicio_col_idprograma"));
                celda = fila.createCell(celdaNro++);
                celda.setCellStyle(tituloStyle);
                celda.setCellValue(Labels.getValue("inicio_col_programa"));
                celda = fila.createCell(celdaNro++);
                celda.setCellStyle(tituloStyle);
                celda.setCellValue(Labels.getValue("inicio_col_idproyecto"));
                celda = fila.createCell(celdaNro++);
                celda.setCellStyle(tituloStyle);
                celda.setCellValue(Labels.getValue("inicio_col_proyecto"));
                celda = fila.createCell(celdaNro++);
                celda.setCellStyle(tituloStyle);
                celda.setCellValue(Labels.getValue("inicio_col_Estado"));
                celda = fila.createCell(celdaNro++);
                celda.setCellStyle(tituloStyle);
                celda.setCellValue(Labels.getValue("inicio_col_Gerente"));
                celda = fila.createCell(celdaNro++);
                celda.setCellStyle(tituloStyle);
                celda.setCellValue(Labels.getValue("inicio_col_actualizacion"));
                celda = fila.createCell(celdaNro++);
                celda.setCellStyle(tituloStyle);
                celda.setCellValue("");
                celda = fila.createCell(celdaNro++);
                celda.setCellStyle(tituloStyle);
                celda.setCellValue(Labels.getValue("inicio_col_inicio"));
                celda = fila.createCell(celdaNro++);
                celda.setCellStyle(tituloStyle);
                celda.setCellValue(Labels.getValue("inicio_col_fin"));
                celda = fila.createCell(celdaNro++);
                celda.setCellStyle(tituloStyle);
                celda.setCellValue(Labels.getValue("inicio_col_avancefinal"));
                celda = fila.createCell(celdaNro++);
                celda.setCellStyle(tituloStyle);
                celda.setCellValue(Labels.getValue("inicio_col_avanceparcial"));
                celda = fila.createCell(celdaNro++);
                celda.setCellStyle(tituloStyle);
                celda.setCellValue(Labels.getValue("inicio_col_riesgos"));
                for (Moneda moneda : monedas) {
                    celda = fila.createCell(celdaNro++);
                    celda.setCellStyle(tituloStyle);
                    celda.setCellValue(moneda.getMonSigno());
                }

                for (Map.Entry<FiltroInicioItem, List> itemsNivel1 : itemArea.getPrimerNivel()) {
                    FiltroInicioItem itemNivel1 = itemsNivel1.getKey();
                    List<Map.Entry<FiltroInicioItem, List>> itemsNivel2 = itemsNivel1.getValue();
                    fila = hoja.createRow(++filaNro);
                    generarLinea(fila, itemNivel1, monedas, palette);
                    if (itemsNivel2 != null && !itemsNivel2.isEmpty()) {
                        for (Map.Entry<FiltroInicioItem, List> itemNivel2 : itemsNivel2) {
                            if (itemNivel2.getKey().getFichaFk() != null) {
                                fila = hoja.createRow(++filaNro);
                                generarLinea(fila, itemNivel2.getKey(), monedas, palette);
                            }
                        }
                    }
                }

            }

        }

        for (int i = 0; i < nroColumnas; i++) {
            hoja.autoSizeColumn(i);
        }

        //Enviar el archivo a descarga mediante Javascript
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            planilla.write(bos);
            bytes = bos.toByteArray();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            JSFUtils.agregarMsgError("No se pudo generar la planilla");
            return null;
        }

        if (bytes != null && bytes.length > 0) {
            Calendar cal = new GregorianCalendar();
            String horaString = String.format("%d_%d_%d_%d_%d_%d", cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
            String fileName = "Planilla_" + horaString + ".xls";
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
                session.setAttribute("bytes", bytes);
                session.setAttribute("fileName", fileName);
                HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String servlet = req.getContextPath() + "/servlet/descargar?sesId=" + session.getId();
                JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "window.open('" + servlet + "','Descargar');");
            }
        } else {
            JSFUtils.agregarMsgWarn("No se ha generado la planilla");
        }

        return null;
    }

    private void generarLinea(HSSFRow fila, FiltroInicioItem item, List<Moneda> monedas, HSSFPalette palette) {
        int celdaNro = 0;

        HSSFCell celda = null;

        //Area
        celda = fila.createCell(celdaNro++);
        celda.setCellValue(item.getArea());
        //Programa id
        celda = fila.createCell(celdaNro++);
        celda.setCellValue(item.getTipoFicha().intValue() == 1 ? item.getFichaFk().toString() : "");
        //Programa nombre
        celda = fila.createCell(celdaNro++);
        celda.setCellValue(item.getTipoFicha().intValue() == 1 ? item.getNombre() : "");
        //Proyectco id
        celda = fila.createCell(celdaNro++);
        celda.setCellValue(item.getTipoFicha().intValue() == 2 ? item.getFichaFk().toString() : "");
        //Proyecto nombre
        celda = fila.createCell(celdaNro++);
        celda.setCellValue(item.getTipoFicha().intValue() == 2 ? item.getNombre() : "");
        //Estado
        celda = fila.createCell(celdaNro++);
        celda.setCellValue(item.getEstado() != null ? item.getEstado().getEstNombre() : "");
        //Gerente
        celda = fila.createCell(celdaNro++);
        celda.setCellValue(item.getResponsable());
        //Actualizacion
        celda = fila.createCell(celdaNro++);
        Color color = Color.decode(item.getActualizacionColor());
        HSSFColor cellColor = palette.findSimilarColor(color.getRed(), color.getGreen(), color.getBlue());
        HSSFCellStyle cellStyle = fila.getSheet().getWorkbook().createCellStyle();
        cellStyle.setFillForegroundColor(cellColor.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        celda.setCellStyle(cellStyle);
        celda.setCellValue(DatesUtils.toStringFormat(item.getActualizacion(), ConstantesEstandares.CALENDAR_PATTERN));
        celda = fila.createCell(celdaNro++);
        celda.setCellValue("Amarillo: " + item.getSemaforoAmarillo() + " días, Rojo: " + item.getSemaforoRojo() + " días.");
        //Fechas de inicio y fin
        celda = fila.createCell(celdaNro++);
        celda.setCellValue(DatesUtils.toStringFormat(item.getPeriodoDesde(), ConstantesEstandares.CALENDAR_PATTERN));
        celda = fila.createCell(celdaNro++);
        celda.setCellValue(DatesUtils.toStringFormat(item.getPeriodoHasta(), ConstantesEstandares.CALENDAR_PATTERN));
        //Avance finalizado
        celda = fila.createCell(celdaNro++);
        celda.setCellValue(inicioTooltipAvanceFinalizado(item));
        //Avance parcial
        celda = fila.createCell(celdaNro++);
        celda.setCellValue(inicioTooltipAvanceParcial(item));
        //Riesgos
        celda = fila.createCell(celdaNro++);
        String hexcolor = item.getRiesgoExposicionColor();
        if (hexcolor != null && !"transparent".equals(hexcolor)) {
            color = Color.decode(hexcolor);
            cellColor = palette.findSimilarColor(color.getRed(), color.getGreen(), color.getBlue());
            cellStyle = fila.getSheet().getWorkbook().createCellStyle();
            cellStyle.setFillForegroundColor(cellColor.getIndex());
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            celda.setCellStyle(cellStyle);
        }
        celda.setCellValue(item.getRiesgosAltosCantidad().toString());
        //Presupuesto
        for (Moneda moneda : monedas) {
            celda = fila.createCell(celdaNro++);
            if (item.getIndiceMonedaColor() != null && item.getIndiceMonedaColor().containsKey(moneda.getMonPk())) {
                hexcolor = item.getIndiceMonedaColor().get(moneda.getMonPk());
                if (!"transparent".equals(hexcolor)) {
                    color = Color.decode(hexcolor);
                    cellColor = palette.findSimilarColor(color.getRed(), color.getGreen(), color.getBlue());
                    cellStyle = fila.getSheet().getWorkbook().createCellStyle();
                    cellStyle.setFillForegroundColor(cellColor.getIndex());
                    cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
                    celda.setCellStyle(cellStyle);
                }
                celda.setCellValue(inicioTooltipPresupuesto(item, moneda.getMonPk()));
            } else {
                celda.setCellValue("");
            }
        }
    }

}
