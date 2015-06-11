package com.sofis.web.mb;

import com.sofis.business.ejbs.ProyReplanificacionBean;
import com.sofis.business.utils.NumbersUtils;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.ProgIndicesPre;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.ProyReplanificacion;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.data.TipoDocumentoInstancia;
import com.sofis.entities.enums.PresupuestoEstadoAcEnum;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.tipos.FiltroInicioItem;
import com.sofis.entities.tipos.FiltroInicioResultadoTO;
import com.sofis.entities.tipos.FiltroInicioTO;
import com.sofis.entities.utils.FichaUtils;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.exceptions.GeneralException;
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
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import org.icefaces.ace.event.ExpansionChangeEvent;
import org.icefaces.application.PortableRenderer;
import org.icefaces.application.PushRenderer;

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
    private int countFiltroThread;
    private String cantElementosPorPagina = "25";
    private OrganiIntProve organizacion;
    private Estados estado;
    private Integer tipoFicha;
    private Integer presupuesto;
    private Areas area;
    private Integer reportes;
    private static String GROUP_NAME = "everyone";
    private PortableRenderer portableRenderer;
    private long TIEMPO_ACTUALIZACION = 3000;
    private List<Moneda> monedasUsadas = new ArrayList<>();
    private boolean buscandoPorAreas = false;
    // Replanificación
    private ProyReplanificacion replanificacion;
    private Boolean renderPopupReplanificacion = false;
    private Map<String, String> configs;

    public ProgramasProyectosMB() {
        PushRenderer.addCurrentView(GROUP_NAME);
        portableRenderer = PushRenderer.getPortableRenderer();
    }

    public OrganiIntProve getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(OrganiIntProve organizacion) {
        this.organizacion = organizacion;
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

    public int getCountFiltroThread() {
        return countFiltroThread;
    }

    public void setCountFiltroThread(int countFiltroThread) {
        this.countFiltroThread = countFiltroThread;
    }

    public List<Moneda> getMonedasUsadas() {
        return monedasUsadas;
    }

    public void setMonedasUsadas(List<Moneda> monedasUsadas) {
        this.monedasUsadas = monedasUsadas;
    }

    public boolean isBuscandoPorAreas() {
        return buscandoPorAreas;
    }

    public void setBuscandoPorAreas(boolean buscandoPorAreas) {
        this.buscandoPorAreas = buscandoPorAreas;
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
                final Map<String, String> configs = cargarConfigLimites(orgPk);

                FiltroInicioItem item = (FiltroInicioItem) event.getRowData();
                for (Map.Entry<FiltroInicioItem, List> item1 : item.getInicioResultado().getPrimerNivel()) {
                    if (item1.getKey().getFichaFk().equals(item.getFichaFk()) && item1.getKey().getTipoFicha().equals(item.getTipoFicha())) {
                        inicioMB.getFiltro().setProgPk(item.getFichaFk());
                        inicioMB.getFiltro().setActivo(Boolean.TRUE);
                        final FiltroInicioResultadoTO resultado = programasProyectosDelegate.obtenerSegundoNivel(inicioMB.getOrganismo().getOrgPk(), item.getAreaId(), inicioMB.getUsuario(), inicioMB.getFiltro());
                        inicioMB.getFiltro().setProgPk(null);
                        if (resultado != null && resultado.getPrimerNivel() != null && resultado.getPrimerNivel().size() > 0) {
                            item1.setValue(resultado.getPrimerNivel());
                        }

                        Thread obtenerIndicadoresThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                for (Entry e : resultado.getPrimerNivel()) {
                                    FiltroInicioItem item = (FiltroInicioItem) e.getKey();
                                    //TODO solo valido para llamada local al ejb, si es remote no funciona
                                    programasProyectosDelegate.obtenerIndicadoresMaterializados(item, orgPk, configs);
                                    programasProyectosDelegate.calcularIndicadores(item, orgPk, configs);
                                }
                                portableRenderer.render(GROUP_NAME);
                            }
                        });
                        obtenerIndicadoresThread.start();

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
            buscandoPorAreas = true;
            filtroInicioResultado.clear();
            countFiltroThread = 0;

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
                areas = areasDelegate.obtenerAreasPorOrganismo(orgPk);
            } else {
                areas = null;
            }

            //para cada area que se debe buscarAction la info se tira un hilo.
            final FiltroInicioTO filtro = inicioMB.getFiltro();
            filtro.setActivo(Boolean.TRUE);
            Thread buscarPorFiltroThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    buscandoPorAreas = true;
                    int x = 0;
                    Configuracion confAmarillo = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_AMARILLO, orgPk);
                    Configuracion confRojo = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_ROJO, orgPk);

                    if (areas != null) {
                        for (Areas area : areas) {
                            final FiltroInicioResultadoTO resultado = programasProyectosDelegate.obtenerPrimerNivel(orgPk, area, usuario, filtro, confAmarillo, confRojo);
                            if (resultado.getPrimerNivel() != null && !resultado.getPrimerNivel().isEmpty()) {
                                filtroInicioResultado.add(resultado);
                                if (x == 0) {
                                    //renderiza el area.
                                    portableRenderer.render(GROUP_NAME);
                                }
                                x++;
                            }
                        }
                    } else {
                        final FiltroInicioResultadoTO resultado = programasProyectosDelegate.obtenerPrimerNivel(orgPk, null, usuario, filtro, confAmarillo, confRojo);
                        filtroInicioResultado.add(resultado);
                        //renderiza el area.
                        portableRenderer.render(GROUP_NAME);
                    }

                    buscandoPorAreas = false;

                    //despues que carga las areas comienza a cargar los indicadores
                    Thread obtenerIndicadoresThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            FiltroInicioItem item = null;

                            for (final FiltroInicioResultadoTO resultado : filtroInicioResultado) {
                                for (Entry e : resultado.getPrimerNivel()) {
                                    item = (FiltroInicioItem) e.getKey();
                                    //TODO solo valido para llamada local al ejb, si es remote no funciona
                                    programasProyectosDelegate.obtenerIndicadoresMaterializados(item, orgPk, configs);
                                    programasProyectosDelegate.calcularIndicadores(item, orgPk, configs);
                                }
                            }
                            portableRenderer.render(GROUP_NAME);
                        }
                    });
                    obtenerIndicadoresThread.start();

                    countFiltroThread--;
                }
            });
            buscarPorFiltroThread.start();
            countFiltroThread++;
        }
        buscandoPorAreas = false;
    }

    public void limpiarFiltro() {
        countFiltroThread = 0;
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

    public String guardarRetrocederEstado() {
        try {
            Proyectos proy = progProyDelegate.guardarRetrocederEstado(replanificacion.getProyectoFk().getProyPk(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk(), replanificacion);
            replanificacion.getItem().setEstado(proy.getProyEstFk());
            replanificacion.getItem().setEstadoPendiente(proy.getProyEstPendienteFk());
            replanificacion.getItem().setSolCambioFase(proy.getProyEstPendienteFk() != null ? true : false);
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
            result = result.append(Labels.getValue("crono_ind_avance_finalizado"))
                    .append(ConstantesPresentacion.SALTO_LINEA)
                    .append(Labels.getValue("crono_ind_azul")).append(": ")
                    .append(String.valueOf(item.getIndiceAvanceFinalizado()[0])).append("%")
                    .append(ConstantesPresentacion.SALTO_LINEA)
                    .append(Labels.getValue("crono_ind_verde")).append(": ")
                    .append(String.valueOf(item.getIndiceAvanceFinalizado()[1])).append("%")
                    .append(ConstantesPresentacion.SALTO_LINEA)
                    .append(Labels.getValue("crono_ind_rojo")).append(": ")
                    .append(String.valueOf(item.getIndiceAvanceFinalizado()[2])).append("%");
        }
        return result.toString();
    }

    public String inicioTooltipAvanceParcial(FiltroInicioItem item) {
        StringBuffer result = new StringBuffer();
        if (item != null) {
            result = result.append(Labels.getValue("crono_ind_avance_parcial"))
                    .append(ConstantesPresentacion.SALTO_LINEA)
                    .append(Labels.getValue("crono_ind_azul")).append(": ")
                    .append(String.valueOf(item.getIndiceAvanceParcial()[0])).append("%")
                    .append(ConstantesPresentacion.SALTO_LINEA)
                    .append(Labels.getValue("crono_ind_verde")).append(": ")
                    .append(String.valueOf(item.getIndiceAvanceParcial()[1])).append("%")
                    .append(ConstantesPresentacion.SALTO_LINEA)
                    .append(Labels.getValue("crono_ind_rojo")).append(": ")
                    .append(String.valueOf(item.getIndiceAvanceParcial()[2])).append("%");
        }
        return result.toString();
    }

    public String inicioTooltipMetodologia(FiltroInicioItem item) {
        StringBuffer result = new StringBuffer();
        if (item != null) {
            Double indiceEstado = null;
            if (FichaUtils.isPrograma(item)) {
                Programas prog = programasDelegate.obtenerProgPorId(item.getFichaFk());
                indiceEstado = prog.getProgIndices() != null && prog.getProgIndices().getProgindMetodologiaEstado() != null ? prog.getProgIndices().getProgindMetodologiaEstado() : null;
            } else if (FichaUtils.isProyecto(item)) {
                Proyectos proy = proyectosDelegate.obtenerProyPorId(item.getFichaFk());
                indiceEstado = proy.getProyIndices() != null ? proy.getProyIndices().getProyindMetodologiaEstado() : null;
            }

            if (indiceEstado == null) {
                indiceEstado = 0d;
            }
            result = result.append(Labels.getValue("tooltip_doc_total")).append(": ")
                    .append(indiceEstado.toString()).append("%");

            List<TipoDocumentoInstancia> listTdi = tipoDocumentoInstanciaDelegate.obtenerTipoDocInstResumen(item.getFichaFk(), item.getTipoFicha(), item.getEstado(), 5);
            if (listTdi != null) {
                for (TipoDocumentoInstancia tdi : listTdi) {
                    result = result.append(ConstantesPresentacion.SALTO_LINEA)
                            .append(tdi.getDocsEstado() != null ? tdi.getDocsEstado().toString() : String.valueOf(0d)).append(" ")
                            .append(tdi.getTipodocInstTipoDocFk() != null ? tdi.getTipodocInstTipoDocFk().getTipodocNombre() : "");
                }
            }

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

            Integer limiteAmarillo = Integer.valueOf(configs.get(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO));
            Integer limiteRojo = Integer.valueOf(configs.get(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO));

            if (FichaUtils.isPrograma(item)) {
                Set<ProgIndicesPre> progIndPreSet = item.getIndicesProg() != null && item.getIndicesProg().getProgIndPreSet() != null ? item.getIndicesProg().getProgIndPreSet() : null;
                if (progIndPreSet != null && !progIndPreSet.isEmpty()) {
                    for (ProgIndicesPre progIndicesPre : progIndPreSet) {
                        if (moneda.getMonPk().equals(progIndicesPre.getProgindpreMonFk())) {
                            estadoPre = progIndicesPre.getProgindpreEstPre();
                            total = progIndicesPre.getProgindpreTotal();

                            anio = presupuestoDelegate.obtenerTotalPorMonedaAnioProg(item.getFichaFk(), moneda, year);
                            PV = presupuestoDelegate.obtenerPVPorMonedaProg(item.getFichaFk(), moneda.getMonPk());
                            AC = presupuestoDelegate.obtenerACPorMonedaProg(item.getFichaFk(), moneda.getMonPk());
                        }
                    }
                }
            } else if (FichaUtils.isProyecto(item) && item.getPresupuesto() != null) {
                Integer prePk = item.getPresupuesto().getPrePk();
                total = presupuestoDelegate.obtenerTotalPorMoneda(prePk, moneda);
                anio = presupuestoDelegate.obtenerTotalPorMonedaAnio(prePk, moneda, year);
                PV = presupuestoDelegate.obtenerPVPorMoneda(prePk, moneda);
                AC = presupuestoDelegate.obtenerACPorMoneda(prePk, moneda);

                estadoPre = presupuestoDelegate.obtenerEstadoAC(prePk, monPk, inicioMB.getOrganismo().getOrgPk(), limiteAmarillo, limiteRojo);
            }

            if ((PV != null && PV != 0) || (AC != null && AC != 0)) {
                result = result.append(moneda.getMonSigno());
                if (estadoPre != 0) {
                    result = result.append(" - ")
                            .append(presupuestoEstadoACStr(estadoPre))
                            .append(ConstantesPresentacion.SALTO_LINEA)
                            .append(presupuestoDesviacionStr(estadoPre));
                }
                if (total != null) {
                    result = result.append(ConstantesPresentacion.SALTO_LINEA)
                            .append(Labels.getValue("presupuesto_resumen_total")).append(": ")
                            .append(NumbersUtils.formatImporte(total))
                            .append(ConstantesPresentacion.SALTO_LINEA)
                            .append(year.toString()).append(": ")
                            .append(NumbersUtils.formatImporte(anio))
                            .append(ConstantesPresentacion.SALTO_LINEA)
                            .append(Labels.getValue("presupuesto_resumen_pv")).append(": ")
                            .append(NumbersUtils.formatImporte(PV))
                            .append(ConstantesPresentacion.SALTO_LINEA)
                            .append(Labels.getValue("presupuesto_resumen_ac")).append(": ")
                            .append(NumbersUtils.formatImporte(AC));
                }
            }
        }
        return result.toString();
    }

    public String presupuestoEstadoACStr(int estadoPre) {
        if (estadoPre == PresupuestoEstadoAcEnum.VERDE.id) {
            return Labels.getValue("presupuesto_pv_verde");
        } else if (estadoPre == PresupuestoEstadoAcEnum.AMARILLO_MAS.id) {
            return Labels.getValue("presupuesto_pv_mas_amarillo");
        } else if (estadoPre == PresupuestoEstadoAcEnum.AMARILLO_MENOS.id) {
            return Labels.getValue("presupuesto_pv_menos_amarillo");
        } else if (estadoPre == PresupuestoEstadoAcEnum.NARANJA.id) {
            return Labels.getValue("presupuesto_pv_naranja");
        } else if (estadoPre == PresupuestoEstadoAcEnum.ROJO.id) {
            return Labels.getValue("presupuesto_pv_rojo");
        }
        return "";
    }

    public String presupuestoDesviacionStr(int estadoPre) {
        Integer limiteAmarillo = Integer.valueOf(configs.get(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO));
        Integer limiteRojo = Integer.valueOf(configs.get(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO));

        if (estadoPre == PresupuestoEstadoAcEnum.VERDE.id) {
            return String.format(Labels.getValue("presupuesto_menos_desviacion"), limiteAmarillo);
        } else if (estadoPre == PresupuestoEstadoAcEnum.AMARILLO_MAS.id) {
            return String.format(Labels.getValue("presupuesto_menos_desviacion"), limiteRojo);
        } else if (estadoPre == PresupuestoEstadoAcEnum.AMARILLO_MENOS.id) {
            return String.format(Labels.getValue("presupuesto_menos_desviacion"), limiteRojo);
        } else if (estadoPre == PresupuestoEstadoAcEnum.NARANJA.id) {
            return String.format(Labels.getValue("presupuesto_mas_desviacion"), limiteRojo);
        } else if (estadoPre == PresupuestoEstadoAcEnum.ROJO.id) {
            return String.format(Labels.getValue("presupuesto_mas_desviacion"), limiteRojo);
        }
        return "";
    }
}
