package com.sofis.web.mb;

import com.sofis.business.utils.CalidadUtils;
import com.sofis.business.utils.ComboItemTOUtils;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.utils.GastosUtils;
import com.sofis.business.utils.ProyectosUtils;
import com.sofis.business.utils.RegistroHorasUtils;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Calidad;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Gastos;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.Participantes;
import com.sofis.entities.data.ProdMes;
import com.sofis.entities.data.Productos;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.RegistrosHoras;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.data.TipoGasto;
import com.sofis.entities.data.ValorCalidadCodigos;
import com.sofis.entities.enums.TipoCalidadEnum;
import com.sofis.entities.tipos.ComboItemTO;
import com.sofis.entities.tipos.FiltroCalidadTO;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.web.delegates.CalidadDelegate;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.EntregablesDelegate;
import com.sofis.web.delegates.GastosDelegate;
import com.sofis.web.delegates.MonedaDelegate;
import com.sofis.web.delegates.ParticipantesDelegate;
import com.sofis.web.delegates.ProductosDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.delegates.RegistrosHorasDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.delegates.TipoGastoDelegate;
import com.sofis.web.delegates.ValorCalidadCodigosDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisCombo;
import com.sofis.web.utils.SofisComboG;
import com.sofis.web.utils.WebUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "registroHorasMB")
@ViewScoped
public class RegistroHorasMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(RegistroHorasMB.class.getName());
    private static final String REGISTRO_FORM_MSG = "registroFormMsg";
    private static final String MSG_CALIDAD = "calidadMsg";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private ProyectosDelegate proyectoDelegate;
    @Inject
    private RegistrosHorasDelegate registrosHorasDelegate;
    @Inject
    private ParticipantesDelegate participantesDelegate;
    @Inject
    private EntregablesDelegate entregablesDelegate;
    @Inject
    private MonedaDelegate monedaDelegate;
    @Inject
    private TipoGastoDelegate tipoGastoDelegate;
    @Inject
    private GastosDelegate gastosDelegate;
    @Inject
    private ProductosDelegate productosDelegate;
    @Inject
    private CalidadDelegate calidadDelegate;
    @Inject
    private ValorCalidadCodigosDelegate valorCalidadCodigosDelegate;
    @Inject
    private ConfiguracionDelegate configuracionDelegate;
     @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;

    //Atributos
    private int cantElementosPorPagina = 25;
    private Integer proyPkSelected = 0;
    private RegistrosHoras registroHoras;
    private Gastos gasto;
    private List<RegistrosHoras> registroHorasListado;
    private List<Gastos> registroGastosListado;
    private Proyectos proyCalidad;
    private SofisComboG<ComboItemTO> listaValorCalidadCombo;
    private SofisComboG<ComboItemTO> listaTipoCalidadCombo;
    private SofisComboG<ComboItemTO> listaValorTablaCalidadCombo;
    private List<Calidad> listCalidad;
    private Integer orgPk;
    //Formulario
    private List<Proyectos> listaProyectos;
    private List<Proyectos> listaProyectosCalidad;
    private List<Entregables> listaEntregables;
    private SofisComboG<Entregables> listaEntregablesFormCombo;
    // 1-Hora, 2-Gasto, 3-Calidad
    private Integer tipoRegistro;
    private List<TipoGasto> listaTipoGasto;
    private SofisCombo listaTipoGastoCombo;
    private List<Moneda> listaMoneda;
    private SofisCombo listaMonedaCombo;
    private boolean avanceDisabled;
    //Para el filtro
    private Integer filtroProyPk;
    private Date filtroFechaDesde;
    private Date filtroFechaHasta;
    private List<Proyectos> listaProyectosFiltro;
    private List<Entregables> listaEntregablesFiltro;
    private SofisCombo listaEntregablesCombo;
    private List<SelectItem> listaAprobItems;
    private SofisCombo listaAprobCombo;
    private List<ComboItemTO> listaAvanceItems;
    private SofisComboG<ComboItemTO> listaAvanceCombo;
    private SofisCombo listaFiltroTipoGastoCombo;
    private Participantes participante;
    //Productos en carga de horas
    private List<Productos> productosList;
    private Integer anioProd;
    private Integer limiteAmarillo;
    private Integer limiteRojo;

    private Boolean renderedRegistrarHoras = true;
    private Boolean verHistorico = false;
    private SofisComboG<SsUsuario> usuarioCombo;

    public RegistroHorasMB() {
    }

    public Boolean getVerHistorico() {
        return verHistorico;
    }

    public void setVerHistorico(Boolean verHistorico) {
        this.verHistorico = verHistorico;
    }

    public Boolean getRenderedRegistrarHoras() {
        return renderedRegistrarHoras;
    }

    public void setRenderedRegistrarHoras(Boolean renderedRegistrarHoras) {
        this.renderedRegistrarHoras = renderedRegistrarHoras;
    }

    public int getCantElementosPorPagina() {
        return cantElementosPorPagina;
    }

    public void setCantElementosPorPagina(int cantElementosPorPagina) {
        this.cantElementosPorPagina = cantElementosPorPagina;
    }

    public Integer getProyPkSelected() {
        return proyPkSelected;
    }

    public void setProyPkSelected(Integer proyPkSelected) {
        this.proyPkSelected = proyPkSelected;
    }

    public RegistrosHoras getRegistroHoras() {
        return registroHoras;
    }

    public Gastos getGasto() {
        return gasto;
    }

    public void setGasto(Gastos gasto) {
        this.gasto = gasto;
    }

    public List<Entregables> getListaEntregables() {
        return listaEntregables;
    }

    public List<Proyectos> getListaProyectos() {
        return listaProyectos;
    }

    public List<Proyectos> getListaProyectosCalidad() {
        return listaProyectosCalidad;
    }

    public void setListaProyectosCalidad(List<Proyectos> listaProyectosCalidad) {
        this.listaProyectosCalidad = listaProyectosCalidad;
    }

    public List<RegistrosHoras> getRegistroHorasListado() {
        return registroHorasListado;
    }

    public List<Gastos> getRegistroGastosListado() {
        return registroGastosListado;
    }

    public void setRegistroGastosListado(List<Gastos> registroGastosListado) {
        this.registroGastosListado = registroGastosListado;
    }

    public Proyectos getProyCalidad() {
        return proyCalidad;
    }

    public void setProyCalidad(Proyectos proyCalidad) {
        this.proyCalidad = proyCalidad;
    }

    public SofisComboG<ComboItemTO> getListaValorCalidadCombo() {
        return listaValorCalidadCombo;
    }

    public void setListaValorCalidadCombo(SofisComboG<ComboItemTO> listaValorCalidadCombo) {
        this.listaValorCalidadCombo = listaValorCalidadCombo;
    }

    public SofisComboG<ComboItemTO> getListaTipoCalidadCombo() {
        return listaTipoCalidadCombo;
    }

    public void setListaTipoCalidadCombo(SofisComboG<ComboItemTO> listaTipoCalidadCombo) {
        this.listaTipoCalidadCombo = listaTipoCalidadCombo;
    }

    public SofisComboG<ComboItemTO> getListaValorTablaCalidadCombo() {
        return listaValorTablaCalidadCombo;
    }

    public void setListaValorTablaCalidadCombo(SofisComboG<ComboItemTO> listaValorTablaCalidadCombo) {
        this.listaValorTablaCalidadCombo = listaValorTablaCalidadCombo;
    }

    public List<Calidad> getListCalidad() {
        return listCalidad;
    }

    public void setListCalidad(List<Calidad> listCalidad) {
        this.listCalidad = listCalidad;
    }

    public Integer getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(Integer tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public List<TipoGasto> getListaTipoGasto() {
        return listaTipoGasto;
    }

    public void setListaTipoGasto(List<TipoGasto> listaTipoGasto) {
        this.listaTipoGasto = listaTipoGasto;
    }

    public SofisCombo getListaTipoGastoCombo() {
        return listaTipoGastoCombo;
    }

    public void setListaTipoGastoCombo(SofisCombo listaTipoGastoCombo) {
        this.listaTipoGastoCombo = listaTipoGastoCombo;
    }

    public List<ComboItemTO> getListaAvanceItems() {
        return listaAvanceItems;
    }

    public void setListaAvanceItems(List<ComboItemTO> listaAvanceItems) {
        this.listaAvanceItems = listaAvanceItems;
    }

    public Integer getFiltroProyPk() {
        return filtroProyPk;
    }

    public void setFiltroProyPk(Integer filtroProyPk) {
        this.filtroProyPk = filtroProyPk;
    }

    public Date getFiltroFechaDesde() {
        return filtroFechaDesde;
    }

    public void setFiltroFechaDesde(Date filtroFechaDesde) {
        this.filtroFechaDesde = filtroFechaDesde;
    }

    public Date getFiltroFechaHasta() {
        return filtroFechaHasta;
    }

    public void setFiltroFechaHasta(Date filtroFechaHasta) {
        this.filtroFechaHasta = filtroFechaHasta;
    }

    public List<Proyectos> getListaProyectosFiltro() {
        return listaProyectosFiltro;
    }

    public List<Entregables> getListaEntregablesFiltro() {
        return listaEntregablesFiltro;
    }

    public SofisCombo getListaEntregablesCombo() {
        return listaEntregablesCombo;
    }

    public void setListaEntregablesCombo(SofisCombo listaEntregablesCombo) {
        this.listaEntregablesCombo = listaEntregablesCombo;
    }

    public SofisComboG<Entregables> getListaEntregablesFormCombo() {
        return listaEntregablesFormCombo;
    }

    public void setListaEntregablesFormCombo(SofisComboG<Entregables> listaEntregablesFormCombo) {
        this.listaEntregablesFormCombo = listaEntregablesFormCombo;
    }

    public List<SelectItem> getListaAprobItems() {
        return listaAprobItems;
    }

    public void setListaAprobItems(List<SelectItem> listaAprobItems) {
        this.listaAprobItems = listaAprobItems;
    }

    public SofisCombo getListaAprobCombo() {
        return listaAprobCombo;
    }

    public void setListaAprobCombo(SofisCombo listaAprobCombo) {
        this.listaAprobCombo = listaAprobCombo;
    }

    public SofisComboG<ComboItemTO> getListaAvanceCombo() {
        return listaAvanceCombo;
    }

    public void setListaAvanceCombo(SofisComboG<ComboItemTO> listaAvanceCombo) {
        this.listaAvanceCombo = listaAvanceCombo;
    }

    public SofisCombo getListaFiltroTipoGastoCombo() {
        return listaFiltroTipoGastoCombo;
    }

    public void setListaFiltroTipoGastoCombo(SofisCombo listaFiltroTipoGastoCombo) {
        this.listaFiltroTipoGastoCombo = listaFiltroTipoGastoCombo;
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public void setProyectoDelegate(ProyectosDelegate proyectoDelegate) {
        this.proyectoDelegate = proyectoDelegate;
    }

    public void setRegistrosHorasDelegate(RegistrosHorasDelegate registrosHorasDelegate) {
        this.registrosHorasDelegate = registrosHorasDelegate;
    }

    public List<Moneda> getListaMoneda() {
        return listaMoneda;
    }

    public void setListaMoneda(List<Moneda> listaMoneda) {
        this.listaMoneda = listaMoneda;
    }

    public SofisCombo getListaMonedaCombo() {
        return listaMonedaCombo;
    }

    public void setListaMonedaCombo(SofisCombo listaMonedaCombo) {
        this.listaMonedaCombo = listaMonedaCombo;
    }

    public boolean isAvanceDisabled() {
        return avanceDisabled;
    }

    public void setAvanceDisabled(boolean avanceDisabled) {
        this.avanceDisabled = avanceDisabled;
    }

    public Participantes getParticipante() {
        return participante;
    }

    public void setParticipante(Participantes participante) {
        this.participante = participante;
    }

    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    public Integer getAnioProd() {
        return anioProd;
    }

    public void setAnioProd(Integer anioProd) {
        this.anioProd = anioProd;
    }

    @PostConstruct
    public void init() {
        /*
        *   31-05-2018 Nico: Se sacan las variables que se inicializan del constructor y se pasan al PostConstruct
        */        
        
        tipoRegistro = 1;
        gasto = new Gastos();
        listaFiltroTipoGastoCombo = new SofisCombo();
        verHistorico = false;
        proyCalidad = new Proyectos();
        productosList = new ArrayList<Productos>();
        listCalidad = new ArrayList<Calidad>();        
        
        inicioMB.cargarOrganismoSeleccionado();

        orgPk = inicioMB.getOrganismo().getOrgPk();
        limiteAmarillo = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_AMARILLO, orgPk).getCnfValor());
        limiteRojo = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_ROJO, orgPk).getCnfValor());

        verHistorico = false;
        inicializarRegistroHoras();
        inicializarRegistroGastos();
        inicializarRegistroCalidad();
        inicializarFiltro();
//        buscarSinFiltro();
        buscarConFiltro();
    }

    private void inicializarRegistroGastos() {
        SsUsuario usuario = inicioMB.getUsuario();
        listaProyectos = proyectoDelegate.obtenerPorUsuarioParticipanteActivo(usuario.getUsuId(), orgPk);

        gasto = new Gastos();
        gasto.setGasProyFk(new Proyectos(proyPkSelected));
        gasto.setGasFecha(new Date());

        listaTipoGasto = tipoGastoDelegate.obtenerTipoGastoPorOrg(orgPk);
        if (listaTipoGasto != null) {
            listaTipoGastoCombo = new SofisCombo((List) listaTipoGasto, "tipogasNombre");
            listaTipoGastoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        listaMoneda = monedaDelegate.obtenerMonedas();
        if (listaMoneda != null) {
            listaMonedaCombo = new SofisCombo((List) listaMoneda, "monSigno");
        }
    }

    private void inicializarRegistroHoras() {
        avanceDisabled = true;
        listaEntregablesFormCombo = new SofisComboG<Entregables>();

        //Obtener el usuario logueado
        SsUsuario usuario = inicioMB.getUsuario();
        //Determinar los proyectos en los que participa y está activo
        listaProyectos = proyectoDelegate.obtenerPorUsuarioParticipanteActivo(usuario.getUsuId(), orgPk);
        //Cargar los entregables del primer proyecto
        Integer proyId = null;
        if (listaProyectos != null && !listaProyectos.isEmpty()) {
            proyId = listaProyectos.get(0).getProyPk();
        }

        //Crear una nueva instancia de RegistroHoras
        registroHoras = new RegistrosHoras();
        registroHoras.setRhFecha(new Date());
        registroHoras.setRhUsuarioFk(usuario);
        registroHoras.setRhProyectoFk(new Proyectos(proyPkSelected));
        registroHoras.setRhEntregableFk(new Entregables(0));

        listaAvanceItems = new ArrayList<ComboItemTO>();
        for (int i = 0; i <= 100; i = i + 10) {
            listaAvanceItems.add(new ComboItemTO(i, String.valueOf(i)));
        }
        listaAvanceCombo = new SofisComboG(listaAvanceItems, "itemNombre");
        listaAvanceCombo.addEmptyItem("Sin cambios");

        if(proyId != null){
            cargarEntregablesProyecto(proyId);
        }
        
        List<SsUsuario> pmofs = ssUsuarioDelegate.obtenerTodosPorOrganismo( orgPk,null);
        pmofs = SsUsuariosUtils.sortByNombreApellido(pmofs);

        usuarioCombo = new SofisComboG<>(pmofs, "usuNombreApellido");
        usuarioCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
    }

    private void inicializarRegistroCalidad() {
        SsUsuario usuario = inicioMB.getUsuario();
        orgPk = inicioMB.getOrganismo().getOrgPk();
        proyCalidad = new Proyectos(proyPkSelected);

        listaProyectosCalidad = proyectoDelegate.obtenerPorUsuarioParticipanteActivo(usuario.getUsuId(), orgPk);
        Configuracion confGerenteModif = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.CALIDAD_GERENTE_MODIFICA, orgPk);
        Boolean gerenteModif = false;
        try {
            if (confGerenteModif != null) {
                gerenteModif = new Boolean(confGerenteModif.getCnfValor());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }

        if (gerenteModif) {
            List<Proyectos> listProyPM = proyectoDelegate.obtenerProyPorGerente(usuario.getUsuId(), orgPk);
            for (Proyectos proy : listProyPM) {
                if (!listaProyectosCalidad.contains(proy)) {
                    listaProyectosCalidad.add(proy);
                }
            }
        }

        listaProyectosCalidad = ProyectosUtils.sortByNombre(listaProyectosCalidad);

        List<ComboItemTO> listaValor = valorCalidadCodigosDelegate.obtenerTodosParaCombo();
        listaValor = ComboItemTOUtils.sortByTextoCombo(listaValor, true);
        if (listaValor != null) {
            listaValorCalidadCombo = new SofisComboG<ComboItemTO>(listaValor, "itemNombre");
            listaValorCalidadCombo.addEmptyItem(Labels.getValue("comboTodos"));

            listaValorTablaCalidadCombo = new SofisComboG<>(listaValor, "itemNombre");
        }

        List<ComboItemTO> listaTipoCal = new ArrayList<ComboItemTO>();
        listaTipoCal.add(new ComboItemTO(TipoCalidadEnum.GENERAL.ordinal(), Labels.getValue("tca_general")));
        listaTipoCal.add(new ComboItemTO(TipoCalidadEnum.ENTREGABLE.ordinal(), Labels.getValue("tca_entregable")));
        listaTipoCal.add(new ComboItemTO(TipoCalidadEnum.PRODUCTO.ordinal(), Labels.getValue("tca_producto")));
        listaTipoCalidadCombo = new SofisComboG<>(listaTipoCal, "itemNombre");
        listaTipoCalidadCombo.addEmptyItem(Labels.getValue("comboTodos"));
    }

    public void listarHistorico() {
        verHistorico = verHistorico == null || !verHistorico;
    }

    public void cambiarProyectoCalidad(ValueChangeEvent evt) {
        Integer proyPk = (Integer) evt.getNewValue();
        setProyectoSeleccionado(proyPk);
        listCalidad.clear();
    }

    public void cambiarProyectoGastos(ValueChangeEvent evt) {
        Integer proyPk = (Integer) evt.getNewValue();
        setProyectoSeleccionado(proyPk);
    }

    public void cambiarProyectoHoras(ValueChangeEvent evt) {
        Integer proyPk = (Integer) evt.getNewValue();
        setProyectoSeleccionado(proyPk);
        cargarEntregablesProyecto(proyPk);
        productosList = null;
    }

    private void setProyectoSeleccionado(Integer proyPk) {
//        proyPkSelected = proyPk;

//        inicializarRegistroHoras();
//        inicializarRegistroGastos();
//        inicializarRegistroCalidad();
    }

    private void cargarEntregablesProyecto(Integer proyId) {
        listaEntregables = new LinkedList<Entregables>();
        if (proyId != null && proyId > 0) {
            Proyectos proy = proyectoDelegate.obtenerProyPorId(proyId);
            if (proy != null && proy.getProyCroFk() != null && proy.getProyCroFk().getEntregablesSet() != null) {
                registroHoras.setRhProyectoFk(proy);
                listaEntregables.addAll(proy.getProyCroFk().getEntregablesSet());
                listaEntregables = EntregablesUtils.sortById(listaEntregables);
            }
        }

        Participantes part = participantesDelegate.obtenerParticipantesPorUsuId(proyId, inicioMB.getUsuario().getUsuId());
        if (part != null) {
            listaEntregables = EntregablesUtils.obtenerHijos(listaEntregables, part.getPartEntregablesFk(), Boolean.TRUE);
        }

        if (CollectionsUtils.isEmpty(listaEntregables)) {
            registroHoras.getRhEntregableFk().setEntPk(0);
        } else {
            listaEntregables = EntregablesUtils.cargarCamposCombos(listaEntregables);
        }

        listaEntregablesFormCombo = new SofisComboG(listaEntregables, "nivelNombreCombo");
        listaEntregablesFormCombo.addEmptyItem(Labels.getValue("comboTodos"));
        avanceDisabled = true;
        listaAvanceCombo.setSelected(-1);
    }

    public String registrarHoras() {
        try {
            Proyectos proySelect = null;
            if (tipoRegistro == 1) {
                //Registro de horas
                ComboItemTO avance = listaAvanceCombo.getSelectedT();
                Entregables entSelect = listaEntregablesFormCombo.getSelectedT();
                registroHoras.setRhEntregableFk(entSelect);
                proySelect = registroHoras.getRhProyectoFk();
                boolean limpiar = false;

                if (!(registroHoras.getRhHoras().equals(0f)
                        && avance != null)) {
                    registroHoras = registrosHorasDelegate.registrarHoras(registroHoras, inicioMB.getOrganismo().getOrgPk());
                    if (registroHoras != null) {
                        JSFUtils.agregarMsg(REGISTRO_FORM_MSG, "info_registroshoras_guardado", null);
                        limpiar = true;
                    }
                }
                if (!avanceDisabled
                        && entSelect != null
                        && entSelect.getEntProgreso() < 100
                        && !productosDelegate.tieneProdporEnt(entSelect.getEntPk())
                        && avance != null && avance.getItemObject() != null && registroHoras != null) {
                    try {
                        Entregables ent = entregablesDelegate.actualizarAvance(registroHoras.getRhProyectoFk().getProyPk(), registroHoras.getRhEntregableFk().getEntPk(), (Integer) avance.getItemObject(), inicioMB.getUsuario());
                        guardarProducto();
                        if (ent != null) {
                            JSFUtils.agregarMsg(REGISTRO_FORM_MSG, "info_registroshoras_avance_guardado", null);
                            limpiar = true;
                        }
                    } catch (BusinessException be) {
                        logger.log(Level.SEVERE, be.getMessage(), be);
                        
                        /*
                        *  19-06-2018 Inspección de código.
                        */

                        //JSFUtils.agregarMsgs(REGISTRO_FORM_MSG, be.getErrores());

                        for(String iterStr : be.getErrores()){
                            JSFUtils.agregarMsgError(REGISTRO_FORM_MSG, Labels.getValue(iterStr), null);                
                        }                         
                    }
                }
                if (CollectionsUtils.isNotEmpty(productosList)) {
                    try {
                        productosList = productosDelegate.guardarProducto(productosList, true);
                        if (productosList != null) {
                            JSFUtils.agregarMsg(REGISTRO_FORM_MSG, "info_producto_guardado", null);
                            limpiar = true;
                        }
                    } catch (BusinessException be) {
                        logger.log(Level.SEVERE, be.getMessage());
                        
                        /*
                        *  19-06-2018 Inspección de código.
                        */

                        //JSFUtils.agregarMsgs(REGISTRO_FORM_MSG, be.getErrores());

                        for(String iterStr : be.getErrores()){
                            JSFUtils.agregarMsgError(REGISTRO_FORM_MSG, Labels.getValue(iterStr), null);                
                        }                         
                    }
                }

                if (limpiar) {
                    limpiarForm();
                }

            } else if (tipoRegistro == 2) {
                //Registro de gasto
                TipoGasto tg = (TipoGasto) listaTipoGastoCombo.getSelectedObject();
                Moneda moneda = (Moneda) listaMonedaCombo.getSelectedObject();
                gasto.setGasTipoFk(tg);
                gasto.setGasMonFk(moneda);
                gasto.setGasUsuarioFk(inicioMB.getUsuario());
                proySelect = gasto.getGasProyFk();

                gasto = gastosDelegate.registrarGasto(gasto, inicioMB.getOrganismo().getOrgPk());
                if (gasto != null) {
                    JSFUtils.agregarMsg(REGISTRO_FORM_MSG, "info_registroshoras_gasto_guardado", null);
                    limpiarForm();
                }
            }
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            
            /*
            *  19-06-2018 Inspección de código.
            */

            //JSFUtils.agregarMsgs(REGISTRO_FORM_MSG, ex.getErrores());

            for(String iterStr : ex.getErrores()){
                JSFUtils.agregarMsgError(REGISTRO_FORM_MSG, Labels.getValue(iterStr), null);                
            }             
        }
        buscarConFiltro();
        inicializarRegistroHoras();

        return null;
    }

    private void inicializarFiltro() {
        filtroFechaDesde = null;
        filtroFechaHasta = null;
        inicializarFiltroProyectos();

        listaTipoGasto = tipoGastoDelegate.obtenerTipoGastoPorOrg(orgPk);
        if (listaTipoGasto != null) {
            listaFiltroTipoGastoCombo = new SofisCombo((List) listaTipoGasto, "tipogasNombre");
            listaFiltroTipoGastoCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }
    }

    private void inicializarFiltroProyectos() {
        listaProyectosFiltro = new LinkedList<>();
        listaProyectosFiltro.addAll(listaProyectos);
        listaProyectosFiltro.add(0, new Proyectos(0, Labels.getValue("comboTodos")));
        cargarEntregablesProyectoFiltro(0);
    }

    private void cargarEntregablesProyectoFiltro(Integer proyId) {
        listaEntregablesFiltro = new LinkedList<>();
        if (proyId != null && proyId.intValue() > 0) {
            Proyectos proy = proyectoDelegate.obtenerProyPorId(proyId);
            if (proy != null && proy.getProyCroFk() != null && proy.getProyCroFk().getEntregablesSet() != null) {
                listaEntregablesFiltro.addAll(proy.getProyCroFk().getEntregablesSet());
            }
        }

        listaEntregablesFiltro = EntregablesUtils.cargarCamposCombos(listaEntregablesFiltro);
        if (listaEntregablesFiltro != null) {
            listaEntregablesCombo = new SofisCombo((List) listaEntregablesFiltro, "nivelNombreCombo");
            listaEntregablesCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }

        listaAprobItems = new ArrayList<>();
        listaAprobItems.add(new SelectItem(1, Labels.getValue("revisionHoras_aprobado")));
        listaAprobItems.add(new SelectItem(0, Labels.getValue("revisionHoras_pendiente")));
        listaAprobCombo = new SofisCombo((List) listaAprobItems, "label");
        listaAprobCombo.addEmptyItem(Labels.getValue("comboTodos"));
    }

    public void cambiarProyectoFiltro(ValueChangeEvent event) {
        Integer proyId = (Integer) event.getNewValue();
        cargarEntregablesProyectoFiltro(proyId);
    }

    public String buscarConFiltro() {
        if (tipoRegistro == null || tipoRegistro == 1) {
            SsUsuario usuario = inicioMB.getUsuario();
            SelectItem aprob = (SelectItem) listaAprobCombo.getSelectedObject();
            Entregables entregable = (Entregables) listaEntregablesCombo.getSelectedObject();
            Integer usuarioPk = null;
            if(usuarioCombo.getSelectedT()!= null){
                usuarioPk=usuarioCombo.getSelectedT().getUsuId();
            }

            
            registroHorasListado = registrosHorasDelegate.obtenerRegistrosHoras(usuarioPk,
                    (filtroProyPk != null && filtroProyPk.intValue() > 0 ? filtroProyPk : null),
                    (entregable != null ? entregable.getEntPk() : null),
                    filtroFechaDesde, filtroFechaHasta, null, null,
                    (aprob != null ? (Integer) aprob.getValue() : null),
                    orgPk);
            RegistroHorasUtils.sortByFecha(registroHorasListado, false);

        } else if (tipoRegistro == 2) {
            SsUsuario usuario = inicioMB.getUsuario();
            TipoGasto tipoGasto = (TipoGasto) listaFiltroTipoGastoCombo.getSelectedObject();
            registroGastosListado = gastosDelegate.obtenerRegistrosGastos(null,
                    (filtroProyPk != null && filtroProyPk.intValue() > 0 ? filtroProyPk : null),
                    filtroFechaDesde, filtroFechaHasta, tipoGasto, null, null, null, orgPk);        
            GastosUtils.sortByFecha(registroGastosListado, true);
        }
        
        return null;
    }

    public String buscarSinFiltro() {
        filtroProyPk = null;
        filtroFechaDesde = null;
        filtroFechaHasta = null;
        listaFiltroTipoGastoCombo.setSelected(-1);
        return buscarConFiltro();
    }

    public void limpiarForm() {
        registroHoras.setRhFecha(new Date());
        registroHoras.setRhUsuarioFk(inicioMB.getUsuario());
        registroHoras.setRhProyectoFk(new Proyectos(0));
        registroHoras.setRhEntregableFk(new Entregables(0));

        gasto = new Gastos();
        gasto.setGasProyFk(new Proyectos());
        gasto.setGasFecha(new Date());

        productosList = null;
        anioProd = null;
    }

    public void limpiarFiltro() {
        //Atributos
        registroHorasListado = null;
        registroGastosListado = null;
        filtroProyPk = null;
        filtroFechaDesde = null;
        filtroFechaHasta = null;
        listaEntregablesCombo.setSelected(-1);
        listaAprobCombo.setSelected(-1);
        listaFiltroTipoGastoCombo.setSelected(-1);
        usuarioCombo.setSelected(-1);
    }

    public String changeTipoRegistro() {
        if (tipoRegistro.equals(2) || tipoRegistro == null) {
            tipoRegistro = 1;
        } else {
            tipoRegistro = 2;
        }
        return null;
    }

    public void entFormComboChangeListener(ValueChangeEvent event) {
        PhaseId phaseId = event.getPhaseId();
        listaEntregablesFormCombo.changeItem(event);

        if (phaseId.equals(PhaseId.ANY_PHASE)) {
            event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
            event.queue();
        } else if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES)) {
            renderedRegistrarHoras = true;
            Entregables entSelected = listaEntregablesFormCombo.getSelectedT();
            avanceDisabled = true;
            listaAvanceCombo = new SofisComboG(listaAvanceItems, "itemNombre");
            listaAvanceCombo.addEmptyItem("Sin cambios");

            if (entSelected.getEntProgreso() % 10 != 0) {
                ComboItemTO cITO = new ComboItemTO(entSelected.getEntProgreso(), entSelected.getEntProgreso().toString());
                listaAvanceCombo.add(cITO);
                listaAvanceCombo.setSelectedT(cITO);
            } else {
                for (ComboItemTO cItemTO : listaAvanceItems) {
                    if (cItemTO.getItemNombre().equals(entSelected.getEntProgreso().toString())) {
                        if (entSelected.getEntProgreso() % 10 == 0) {
                            listaAvanceCombo.setSelectedT(cItemTO);
                            break;
                        }
                    }
                }
            }

            if (entSelected != null && entSelected.getEntPk() != null) {
                boolean isGerente = SsUsuariosUtils.isUsuarioGerenteOAdjuntoFicha(registroHoras.getRhProyectoFk(), inicioMB.getUsuario());
                //no es gerente o no es el coordinador
                boolean isCoordinador = entSelected.getCoordinadorUsuFk() == null || entSelected.getCoordinadorUsuFk().getUsuId().equals(inicioMB.getUsuario().getUsuId());

                avanceDisabled = entSelected.esEntParent()
                        || !(isGerente || isCoordinador)
                        || entSelected.getEntProgreso() > 100
                        || productosDelegate.tieneProdporEnt(entSelected.getEntPk());

                cargarProductosEntregables(entSelected.getEntPk());
            }
        }
    }

    public String limpiarCalidadAction() {
        proyCalidad = new Proyectos();
        listaTipoCalidadCombo.setSelected(-1);
        listaValorCalidadCombo.setSelected(-1);
        return null;
    }

    public String buscarCalidadAction() {
        if (proyCalidad != null) {
            ComboItemTO ciTipoCalidad = listaTipoCalidadCombo.getSelectedT();
            ComboItemTO ciValorCalidad = listaValorCalidadCombo.getSelectedT();

            FiltroCalidadTO filtro = new FiltroCalidadTO();
            if (proyCalidad != null) {
                filtro.setProyPk(proyCalidad.getProyPk());
            }
            if (ciTipoCalidad != null) {
                filtro.setTipo((Integer) ciTipoCalidad.getItemObject());
            }
            if (ciValorCalidad != null) {
                filtro.setValor((Integer) ciValorCalidad.getItemObject());
            }

            listCalidad = calidadDelegate.buscarPorFiltro(filtro, inicioMB.getOrganismo().getOrgPk());
            listCalidad = CalidadUtils.sortByActualizacion(listCalidad, true);
            // Se recargan los valores de calidad para que no den problema los combos de la pantalla.
            for (Calidad cal : listCalidad) {
                ValorCalidadCodigos vca = valorCalidadCodigosDelegate.obtenerPorId(cal.getCalVcaFk().getVcaPk());
                cal.setCalVcaFk(vca);
            }
        }
        return null;
    }

    public String tipoCalidadStr(Integer tipo) {
        return calidadDelegate.tipoCalidadStr(tipo, inicioMB.getOrganismoSeleccionado());
    }

    public String valorColorTabla(String cod) {
        return calidadDelegate.valorColorTabla(cod);
    }

    public String guardarCalidadAction() {
        if (CollectionsUtils.isNotEmpty(listCalidad)) {
            try {

                listCalidad = calidadDelegate.guardar(listCalidad, inicioMB.getOrganismo().getOrgPk(), null);
                JSFUtils.agregarMsg(MSG_CALIDAD, MensajesNegocio.INFO_CALIDAD_GUARDADO, null);
                buscarCalidadAction();
            } catch (BusinessException be) {
                logger.log(Level.SEVERE, null, be);
                /*
                *  19-06-2018 Inspección de código.
                */

                //JSFUtils.agregarMsgs(MSG_CALIDAD, be.getErrores());

                for(String iterStr : be.getErrores()){
                    JSFUtils.agregarMsgError(MSG_CALIDAD, Labels.getValue(iterStr), null);                
                }                 
            }
        }
        return null;
    }

    public void changeValorTabla(ValueChangeEvent event) {
        PhaseId phaseId = event.getPhaseId();
        if (phaseId.equals(PhaseId.ANY_PHASE)) {
            event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
            event.queue();
        } else if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES)) {
            //Codigo del item a modificar.
            Integer calPk = (Integer) ((UIInput) event.getSource()).getAttributes().get("calPk");
            //Codigo seleccionado en el combo.
            Integer sel = (Integer) event.getNewValue();
            for (Calidad cal : listCalidad) {
                if (cal.getCalPk().equals(calPk)) {
                    //Se modifica el valor en el item.
                    ValorCalidadCodigos vca = valorCalidadCodigosDelegate.obtenerPorId(sel);
                    cal.setCalVcaFk(vca);
                    break;
                }
            }
        }
    }

    public String guardarProducto() {

        if (CollectionsUtils.isNotEmpty(productosList)) {
            try {
                productosList = productosDelegate.guardarProducto(productosList, true);
                if (CollectionsUtils.isNotEmpty(productosList)) {
                    JSFUtils.agregarMsg(REGISTRO_FORM_MSG, "info_producto_guardado", null);
                }
            } catch (BusinessException be) {
                logger.log(Level.SEVERE, be.getMessage());
                
                /*
                *  19-06-2018 Inspección de código.
                */

                //JSFUtils.agregarMsgs(REGISTRO_FORM_MSG, be.getErrores());

                for(String iterStr : be.getErrores()){
                    JSFUtils.agregarMsgError(REGISTRO_FORM_MSG, Labels.getValue(iterStr), null);                
                }                  

                //inicioMB.setRenderPopupMensajes(true);
                return null;
            }
        }
        return null;
    }

    public boolean contieneProdMesAnioMayor() {
        if (anioProd != null && CollectionsUtils.isNotEmpty(productosList)) {
            for (Productos prod : productosList) {
                for (ProdMes prodMes : prod.getProdMesList()) {
                    if (anioProd < prodMes.getProdmesAnio()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean contieneProdMesAnioMenor() {
        if (anioProd != null && CollectionsUtils.isNotEmpty(productosList)) {
            for (Productos prod : productosList) {
                for (ProdMes prodMes : prod.getProdMesList()) {
                    if (anioProd > prodMes.getProdmesAnio()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String retrocederAnioProdMes(Integer entPk) {
        if (contieneProdMesAnioMenor()) {
            anioProd = anioProd - 1;
            productosList = productosDelegate.obtenerProdPorEnt(entPk);
            for (Productos prod : productosList) {
                prod = productosDelegate.cargarProdMesAuxiliar(prod, anioProd);
            }
        }
        return null;
    }

    public String avanzarAnioProdMes(Integer entPk) {
        if (contieneProdMesAnioMayor()) {
            anioProd = anioProd + 1;
            productosList = productosDelegate.obtenerProdPorEnt(entPk);
            for (Productos prod : productosList) {
                prod = productosDelegate.cargarProdMesAuxiliar(prod, anioProd);
            }
        }
        return null;
    }

    public String mesToStr(Integer mes, Boolean abreviado) {
        return WebUtils.mesToStr(mes, abreviado);
    }

    public boolean proyHorasIsEstado(Integer val) {
        if (val != null && registroHoras != null && registroHoras.getRhProyectoFk() != null) {
            Estados est = registroHoras.getRhProyectoFk().getProyEstFk();
            return est != null && est.isEstado(val);
        }
        return false;
    }

    public String getProdMesAcuRealColor(ProdMes prodMes) {
        return productosDelegate.prodMesAcuRealColor(prodMes, inicioMB.getOrganismo().getOrgPk(), limiteAmarillo, limiteRojo);
    }

    private void cargarProductosEntregables(Integer entPk) {
        try {
            productosList = productosDelegate.obtenerProdPorEnt(entPk);
            if (CollectionsUtils.isNotEmpty(productosList)) {
                anioProd = new GregorianCalendar().get(Calendar.YEAR);
                productosList = productosDelegate.cargarProdMesAuxiliar(productosList, anioProd);

                if (registroHoras.getRhProyectoFk() != null) {
                    Estados est = registroHoras.getRhProyectoFk().getProyEstFk();
                    if (est != null && !est.isEstado(Estados.ESTADOS.EJECUCION.estado_id)) {
                        renderedRegistrarHoras = false;
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public boolean editarRealProd(ProdMes prodMes) {
        Calendar cal = new GregorianCalendar();
        return prodMes.getProdmesAnio() > cal.get(Calendar.YEAR)
                || (prodMes.getProdmesAnio() == cal.get(Calendar.YEAR)
                && (prodMes.getProdmesMes() >= cal.get(Calendar.MONTH) + 1
                || prodMes.getProdmesMes() == cal.get(Calendar.MONTH)));
    }

    public SofisComboG<SsUsuario> getUsuarioCombo() {
        return usuarioCombo;
    }

    public void setUsuarioCombo(SofisComboG<SsUsuario> usuarioCombo) {
        this.usuarioCombo = usuarioCombo;
    }
    
    
}
