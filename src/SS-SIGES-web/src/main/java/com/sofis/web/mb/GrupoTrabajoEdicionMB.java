package com.sofis.web.mb;

import com.sofis.entities.codigueras.SsRolCodigos;
import com.sofis.entities.data.ComponenteProducto;
import com.sofis.entities.data.GrupoTrabajo;
import com.sofis.entities.data.GrupoTrabajoOperacionDetalle;
import com.sofis.entities.data.GrupoTrabajoUsuario;
import com.sofis.entities.data.OperacionDetalle;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.SsRol;
import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.ComponenteProductoDelegate;
import com.sofis.web.delegates.GrupoTrabajoDelegate;
import com.sofis.web.delegates.GrupoTrabajoOperacionDetalleDelegate;
import com.sofis.web.delegates.GrupoTrabajoUsuarioDelegate;
import com.sofis.web.delegates.OperacionDetalleDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.genericos.constantes.ConstantesNavegacion;
import com.sofis.web.genericos.constantes.ConstantesPresentacion;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisCombo;
import java.io.IOException;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "grupoTrabajoEdicionMB")
@ViewScoped
public class GrupoTrabajoEdicionMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(GrupoTrabajoEdicionMB.class.getName());
    //private static final String BUSQUEDA_MSG = "busquedaMsg";
    private static final String POPUP_MSG = "popupMsg";
    private static final String AREAS_NOMBRE = "gtbNombre";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;

    @ManagedProperty("#{aplicacionMB}")
    private AplicacionMB aplicacionMB;

    @Inject
    private SofisPopupUI renderPopupEdicion;
    @Inject
    private GrupoTrabajoDelegate grupoTrabajoDelegate;

    @Inject
    private OperacionDetalleDelegate operacionDetalleDelegate;

    @Inject
    private GrupoTrabajoUsuarioDelegate grupoTrabajoUsuarioDelegate;

    @Inject
    private GrupoTrabajoOperacionDetalleDelegate grupoTrabajoOperacionDetalleDelegate;

    @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;

    // Variables
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = AREAS_NOMBRE;

    // 0=descendente, 1=ascendente.
    private int ascendente = 1;
    private String filtroNombre;
    private String filtroHabilitado = "";
    private String filtroMail = "";
    private List<GrupoTrabajo> listaResultado;
    private GrupoTrabajo cgrupoTrabajoEnEdicion;
    private SofisCombo listaGerenteCombo = new SofisCombo();

    List<SelectItem> oneWaySourceList, oneWayDestList, usuarioListOrigen, usuarioListDestino;
    private boolean mostrarAcciones, mostrarUsuarios, mostrarMensajeAccion, mostrarMensajeUsuario, mostratMensajeGuardadoGrupo;

    Map<Integer, String> mapSeleccionados = new HashMap<Integer, String>();

    Map<Integer, String> mapUsuarioSeleccionado = new HashMap<Integer, String>();

    public GrupoTrabajoEdicionMB() {

    }

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

    public List<GrupoTrabajo> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<GrupoTrabajo> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public GrupoTrabajo getCgrupoTrabajoEnEdicion() {
        return cgrupoTrabajoEnEdicion;
    }

    public void setCgrupoTrabajoEnEdicion(GrupoTrabajo cgrupoTrabajoEnEdicion) {
        this.cgrupoTrabajoEnEdicion = cgrupoTrabajoEnEdicion;
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    @PostConstruct
    public void init() {
        /*
        *   30-05-2018 Nico: Se sacan las variables que se inicializan del constructor y se pasan al PostConstruct
         */

        try {
            filtroNombre = "";
            listaResultado = new ArrayList<GrupoTrabajo>();
            cgrupoTrabajoEnEdicion = new GrupoTrabajo();

            inicioMB.cargarOrganismoSeleccionado();

            //  buscar();
            mostrarAcciones = false;
            String programaProyectoId = (String) getFlashContext(ConstantesPresentacion.GRUPO_ID);
            if (programaProyectoId == null) {
                /**
                 * Busco el parámetro en el request
                 */
                HttpServletRequest request = (HttpServletRequest) FacesContext
                        .getCurrentInstance()
                        .getExternalContext()
                        .getRequest();
                programaProyectoId = (String) request
                        .getParameter(ConstantesPresentacion.GRUPO_ID);

                programaProyectoId = inicioMB.decryptParam(programaProyectoId);

                if (programaProyectoId == null && request != null) {
                    programaProyectoId = (String) request.getSession().getAttribute(ConstantesPresentacion.GRUPO_ID);

                    programaProyectoId = inicioMB.decryptParam(programaProyectoId);
                }

            }

            editar(new Integer(programaProyectoId));

            cargarCombosFiltro();
        } catch (Exception exception) {
            try {
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect("/SS-SIGES-web/paginasPrivadas/paginaInicioCliente.jsf");
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
            }

        }

    }

    private Object getFlashContext(String attName) {
        return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(attName);
    }

    /**
     * Action agregar.
     *
     * @return
     */
    public String agregar() {
        cgrupoTrabajoEnEdicion = new GrupoTrabajo();

        renderPopupEdicion.abrir();
        return null;
    }

    /**
     * Action eliminar un componente-producto.
     *
     * @return
     */
    public String eliminar(Integer componenteProductoPk) {
        if (componenteProductoPk != null) {
            try {
                grupoTrabajoDelegate.eliminarGrupoTrabajo(componenteProductoPk);
                for (GrupoTrabajo f : listaResultado) {
                    if (f.getGtbPk().equals(componenteProductoPk)) {
                        listaResultado.remove(f);
                        break;
                    }
                }
            } catch (BusinessException e) {
                logger.log(Level.SEVERE, null, e);
                //JSFUtils.agregarMsgs(BUSQUEDA_MSG, e.getErrores());
                /*
                *   15-06-2018 Nico: Se agrega este for para poder mostrar en el front todos los mensajes
                *           enviados desde la lógica.
                 */

                for (String iterStr : e.getErrores()) {
                    JSFUtils.agregarMsgError("", Labels.getValue(iterStr), null);
                }

                inicioMB.setRenderPopupMensajes(Boolean.TRUE);
            }
        }
        return null;
    }

    /**
     * Action Buscar.
     *
     * @return
     */
    public String buscar() {
        Map<String, Object> mapFiltro = new HashMap<String, Object>();
        mapFiltro.put("nombre", filtroNombre);

        if (!filtroHabilitado.isEmpty()) {
            mapFiltro.put("habilitada", filtroHabilitado.equals("1"));
        }

        if (this.listaGerenteCombo.getSelected() > 0) {

            listaResultado = grupoTrabajoUsuarioDelegate.listarPorOrgYUsuarioyFiltro(inicioMB.getOrganismo().getOrgPk(), listaGerenteCombo.getSelected(), mapFiltro);

        } else {

            listaResultado = grupoTrabajoDelegate.busquedaComponenteProductoFiltro(inicioMB.getOrganismo().getOrgPk(), mapFiltro, elementoOrdenacion, ascendente);

        }

        return null;
    }

    public String editar(Integer componenteProductoPk) {
        Organismos org = inicioMB.getOrganismo();
        try {
            cgrupoTrabajoEnEdicion = grupoTrabajoDelegate.obtenerGrupoTrabajoPk(componenteProductoPk);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, null, ex);
            //JSFUtils.agregarMsgs(POPUP_MSG, ex.getErrores());
            /*
            *   15-06-2018 Nico: Se agrega este for para poder mostrar en el front todos los mensajes
            *           enviados desde la lógica.
             */

            for (String iterStr : ex.getErrores()) {
                JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);
            }

        }

        renderPopupEdicion.abrir();
        return null;
    }

    public String guardar() {

        cgrupoTrabajoEnEdicion.setGtbOrgFk(inicioMB.getOrganismo());

        try {
            grupoTrabajoOperacionDetalleDelegate.eliminarGrupo(cgrupoTrabajoEnEdicion.getGtbPk());

            for (SelectItem si : oneWayDestList) {

                grupoTrabajoOperacionDetalleDelegate.insertarGrupo(cgrupoTrabajoEnEdicion.getGtbPk(), (Integer) si.getValue());

            }
            mostrarMensajeAccion = true;

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
        return null;
    }

    public String guardarUsuarios() {

        cgrupoTrabajoEnEdicion.setGtbOrgFk(inicioMB.getOrganismo());

        try {
            grupoTrabajoUsuarioDelegate.eliminarGrupo(cgrupoTrabajoEnEdicion.getGtbPk());
            Integer usuarioPk = 0;

            ArrayList<Integer> usuarios = new ArrayList<Integer>();

            for (SelectItem si : usuarioListDestino) {
                usuarioPk = (Integer) si.getValue();
                usuarios.add(usuarioPk);

            }

            Set<Integer> hashSet = new HashSet<Integer>(usuarios);
            usuarios.clear();
            usuarios.addAll(hashSet);

            for (Integer usuPk : usuarios) {
                grupoTrabajoUsuarioDelegate.insertarGrupo(cgrupoTrabajoEnEdicion.getGtbPk(), usuPk);
            }
            cargarUsuarioDestino();
            mostrarMensajeUsuario = true;

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
        return null;

    }

    public void cancelar() {
        renderPopupEdicion.cerrar();
    }

    public String regresar() {
        return ConstantesNavegacion.IR_CONFIGURACION_GRUPO_TRABAJO;
    }

    /**
     * Action limpiar formulario de busqueda.
     *
     * @return
     */
    public String limpiar() {
        filtroNombre = null;
        listaResultado = null;
        elementoOrdenacion = AREAS_NOMBRE;
        ascendente = 1;

        listaGerenteCombo.setSelected(-1);

        return null;
    }

    public void componenteProductoChange(ComponenteProducto componenteProducto) {

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

    public SofisCombo getListaGerenteCombo() {
        return listaGerenteCombo;
    }

    public void setListaGerenteCombo(SofisCombo listaGerenteCombo) {
        this.listaGerenteCombo = listaGerenteCombo;
    }

    public AplicacionMB getAplicacionMB() {
        return aplicacionMB;
    }

    public void setAplicacionMB(AplicacionMB aplicacionMB) {
        this.aplicacionMB = aplicacionMB;
    }

    public String getFiltroHabilitado() {
        return filtroHabilitado;
    }

    public void setFiltroHabilitado(String filtroHabilitado) {
        this.filtroHabilitado = filtroHabilitado;
    }

    private void cargarCombosFiltro() {
        Integer orgPk = inicioMB.getOrganismo().getOrgPk();
        List<SsUsuario> listaGerente = aplicacionMB.obtenerUsuariosPorRolActivos(SsRolCodigos.USUARIO_COMUN, orgPk);
        List<SsUsuario> listaPmof = aplicacionMB.obtenerUsuariosPorRolActivos(SsRolCodigos.PMO_FEDERADA, orgPk);
        listaGerente.addAll(listaPmof);

        Collections.sort(listaGerente, new Comparator() {
            @Override
            public int compare(Object softDrinkOne, Object softDrinkTwo) {
                //use instance        mostrarAcciones = !mostrarAcciones;
                return ((SsUsuario) softDrinkOne).getNombreApellido()
                        .compareTo(((SsUsuario) softDrinkTwo).getNombreApellido());
            }
        });
        if (listaGerente != null && !listaGerente.isEmpty()) {
            listaGerenteCombo = new SofisCombo((List) listaGerente, "usuNombreApellido");
            listaGerenteCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }
        oneWaySourceList = new ArrayList();
        oneWayDestList = new ArrayList();
        usuarioListDestino = new ArrayList();
        usuarioListOrigen = new ArrayList();

        Map<String, String> mapAux = new HashMap<String, String>();
        List<OperacionDetalle> listarPorOrg = operacionDetalleDelegate.listarPorOrg(orgPk);

        Collections.sort(listarPorOrg, new Comparator() {
            @Override
            public int compare(Object softDrinkOne, Object softDrinkTwo) {
                //use instanceof to verify the references are indeed of the type in question
                return ((OperacionDetalle) softDrinkOne).getOpdDescripcion()
                        .compareTo(((OperacionDetalle) softDrinkTwo).getOpdDescripcion());
            }
        });

        List<OperacionDetalle> listaorigen = new ArrayList<>();

        for (GrupoTrabajoOperacionDetalle gtod : cgrupoTrabajoEnEdicion.getGrupoTrabajoOperacionDetalle()) {

            listaorigen.add(gtod.getGtoOperacionFk());

            mapAux.put(gtod.getGtoOperacionFk().getOpdCodigo(), gtod.getGtoOperacionFk().getOpdDescripcion());
        }

        Collections.sort(listaorigen, new Comparator() {
            @Override
            public int compare(Object softDrinkOne, Object softDrinkTwo) {
                //use instanceof to verify the references are indeed of the type in question
                return ((OperacionDetalle) softDrinkOne).getOpdDescripcion()
                        .compareTo(((OperacionDetalle) softDrinkTwo).getOpdDescripcion());
            }
        });

        for (OperacionDetalle od : listaorigen) {

            oneWayDestList.add(new SelectItem(od.getOpdPk(), od.getOpdDescripcion()));

        }

        for (OperacionDetalle od : listarPorOrg) {

            if (mapAux.get(od.getOpdCodigo()) == null) {
                oneWaySourceList.add(new SelectItem(od.getOpdPk(), od.getOpdDescripcion()));

            }

        }

        cargarUsuarioDestino();

        buscarUsuarios();

    }

    public String seleccionarTodasAcciones() {

        Integer orgPk = inicioMB.getOrganismo().getOrgPk();

        oneWaySourceList = new ArrayList();
        oneWayDestList = new ArrayList();

        List<OperacionDetalle> listarPorOrg = operacionDetalleDelegate.listarPorOrg(orgPk);

        Collections.sort(listarPorOrg, new Comparator() {
            @Override
            public int compare(Object softDrinkOne, Object softDrinkTwo) {
                //use instanceof to verify the references are indeed of the type in question
                return ((OperacionDetalle) softDrinkOne).getOpdDescripcion()
                        .compareTo(((OperacionDetalle) softDrinkTwo).getOpdDescripcion());
            }
        });

        for (OperacionDetalle od : listarPorOrg) {

            oneWayDestList.add(new SelectItem(od.getOpdPk(), od.getOpdDescripcion()));

        }

        return null;

    }

    public String quitarTodasAcciones() {

        Integer orgPk = inicioMB.getOrganismo().getOrgPk();

        oneWaySourceList = new ArrayList();
        oneWayDestList = new ArrayList();

        List<OperacionDetalle> listarPorOrg = operacionDetalleDelegate.listarPorOrg(orgPk);

        for (OperacionDetalle od : listarPorOrg) {

            oneWaySourceList.add(new SelectItem(od.getOpdPk(), od.getOpdDescripcion()));

        }

        return null;

    }

    public String quitarTodosUsuarios() {
        usuarioListDestino = new ArrayList();
        mapUsuarioSeleccionado = new HashMap<>();
        buscarUsuarios();
        return null;
    }

    public String agregarTodosUsuarios() {
        //  usuarioListDestino = new ArrayList();

        for (SelectItem se : usuarioListOrigen) {
            usuarioListDestino.add(se);
        }

        usuarioListOrigen = new ArrayList<>();

        return null;
    }

    public String guardarSoloGrupo() {

        cgrupoTrabajoEnEdicion.setGtbOrgFk(inicioMB.getOrganismo());

        try {
            cgrupoTrabajoEnEdicion = grupoTrabajoDelegate.guardarGrupoTrabajo(cgrupoTrabajoEnEdicion);

            if (cgrupoTrabajoEnEdicion != null) {
                renderPopupEdicion.cerrar();
                buscar();
            }
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

        mostratMensajeGuardadoGrupo = true;
        return null;
    }

    public boolean isMostrarAcciones() {
        return mostrarAcciones;
    }

    public void setMostrarAcciones(boolean mostrarAcciones) {
        this.mostrarAcciones = mostrarAcciones;
    }

    public boolean isMostrarUsuarios() {
        return mostrarUsuarios;
    }

    public void setMostrarUsuarios(boolean mostrarUsuarios) {
        this.mostrarUsuarios = mostrarUsuarios;
    }

    public void mostrarAccionAction() {

        mostrarMensajeAccion = false;
        mostrarMensajeUsuario = false;
        mostratMensajeGuardadoGrupo = false;
        mostrarAcciones = !mostrarAcciones;
    }

    public void mostrarUsuarioAction() {
        mostrarMensajeAccion = false;
        mostrarMensajeUsuario = false;
        mostratMensajeGuardadoGrupo = false;
        mostrarUsuarios = !mostrarUsuarios;
    }

    public void cargarUsuarios() {

        SsRol ssRol = new SsRol(2);

        List<SsUsuario> listaUsuariosPMOF = ssUsuarioDelegate.busquedaUsuFiltro(inicioMB.getOrganismo(), inicioMB.getUsuario(), "", filtroNombre, "", true, ssRol, "usuCorreoElectronico", "Ascendente");

        ssRol = new SsRol(4);
        List<SsUsuario> listaUsuariosComun = ssUsuarioDelegate.busquedaUsuFiltro(inicioMB.getOrganismo(), inicioMB.getUsuario(), "", filtroNombre, "", true, ssRol, "usuCorreoElectronico", "Ascendente");

        listaUsuariosPMOF.addAll(listaUsuariosComun);

        Collections.sort(listaUsuariosPMOF, new Comparator() {
            @Override
            public int compare(Object softDrinkOne, Object softDrinkTwo) {
                //use instanceof to verify the references are indeed of the type in question
                return ((SsUsuario) softDrinkOne).getNombreApellido()
                        .compareTo(((SsUsuario) softDrinkTwo).getNombreApellido());
            }
        });

        usuarioListOrigen = new ArrayList();
        ArrayList<Integer> usuarios = new ArrayList<Integer>();

        for (SelectItem si : usuarioListDestino) {

            usuarios.add((Integer) si.getValue());

        }
        for (SsUsuario usu : listaUsuariosPMOF) {
            if (!usuarios.contains(usu.getUsuId())) {
                usuarioListOrigen.add(new SelectItem(usu.getUsuId(), usu.getNombreApellido()));

            }

        }

        cargarUsuarioDestino();

    }

    public List<SelectItem> getOneWaySourceList() {
        return oneWaySourceList;
    }

    public void setOneWaySourceList(List<SelectItem> oneWaySourceList) {
        this.oneWaySourceList = oneWaySourceList;
    }

    public List<SelectItem> getOneWayDestList() {
        return oneWayDestList;
    }

    public void setOneWayDestList(List<SelectItem> oneWayDestList) {
        this.oneWayDestList = oneWayDestList;
    }

    public List<SelectItem> getUsuarioListOrigen() {
        return usuarioListOrigen;
    }

    public void setUsuarioListOrigen(List<SelectItem> usuarioListOrigen) {
        this.usuarioListOrigen = usuarioListOrigen;
    }

    public List<SelectItem> getUsuarioListDestino() {
        return usuarioListDestino;
    }

    public void setUsuarioListDestino(List<SelectItem> usuarioListDestino) {
        this.usuarioListDestino = usuarioListDestino;
    }

    public String getFiltroMail() {
        return filtroMail;
    }

    public void setFiltroMail(String filtroMail) {
        this.filtroMail = filtroMail;
    }

    public boolean isMostrarMensajeAccion() {
        return mostrarMensajeAccion;
    }

    public void setMostrarMensajeAccion(boolean mostrarMensajeAccion) {
        this.mostrarMensajeAccion = mostrarMensajeAccion;
    }

    public boolean isMostrarMensajeUsuario() {
        return mostrarMensajeUsuario;
    }

    public void setMostrarMensajeUsuario(boolean mostrarMensajeUsuario) {
        this.mostrarMensajeUsuario = mostrarMensajeUsuario;
    }

    public boolean isMostratMensajeGuardadoGrupo() {
        return mostratMensajeGuardadoGrupo;
    }

    public void setMostratMensajeGuardadoGrupo(boolean mostratMensajeGuardadoGrupo) {
        this.mostratMensajeGuardadoGrupo = mostratMensajeGuardadoGrupo;
    }

    private void buscarUsuarios() {
        SsRol ssRol = new SsRol(2);

        List<SsUsuario> listaUsuariosPMOF = ssUsuarioDelegate.busquedaUsuFiltro(inicioMB.getOrganismo(), inicioMB.getUsuario(), "", "", "", true, ssRol, "usuCorreoElectronico", "Ascendente");

        ssRol = new SsRol(4);
        List<SsUsuario> listaUsuariosComun = ssUsuarioDelegate.busquedaUsuFiltro(inicioMB.getOrganismo(), inicioMB.getUsuario(), "", "", "", true, ssRol, "usuCorreoElectronico", "Ascendente");

        listaUsuariosPMOF.addAll(listaUsuariosComun);

        Collections.sort(listaUsuariosPMOF, new Comparator() {
            @Override
            public int compare(Object softDrinkOne, Object softDrinkTwo) {
                //use instanceof to verify the references are indeed of the type in question
                return ((SsUsuario) softDrinkOne).getNombreApellido()
                        .compareTo(((SsUsuario) softDrinkTwo).getNombreApellido());
            }
        });

        ArrayList<Integer> usuarios = new ArrayList<Integer>();

        for (SelectItem si : usuarioListDestino) {

            usuarios.add((Integer) si.getValue());

        }

        for (SsUsuario us : listaUsuariosPMOF) {

            if (!usuarios.contains(us.getUsuId())) {
                if (mapUsuarioSeleccionado.get(us.getUsuId()) == null) {
                    usuarioListOrigen.add(new SelectItem(us.getUsuId(), us.getNombreApellido()));
                }

            }

        }

    }

    private void cargarUsuarioDestino() {
        cgrupoTrabajoEnEdicion = grupoTrabajoDelegate.obtenerGrupoTrabajoPk(cgrupoTrabajoEnEdicion.getGtbPk());
        List<SsUsuario> listaUsuarios = new ArrayList<>();
        usuarioListDestino.clear();
        for (GrupoTrabajoUsuario gtu : cgrupoTrabajoEnEdicion.getGrupoTrabajoUsuario()) {

            listaUsuarios.add(gtu.getGtuUsuarioFk());
            mapUsuarioSeleccionado.put(gtu.getGtuUsuarioFk().getUsuId(), gtu.getGtuUsuarioFk().getNombreApellido());

        }

        Collections.sort(listaUsuarios, new Comparator() {
            @Override
            public int compare(Object softDrinkOne, Object softDrinkTwo) {
                //use instance        mostrarAcciones = !mostrarAcciones;
                return ((SsUsuario) softDrinkOne).getNombreApellido()
                        .compareTo(((SsUsuario) softDrinkTwo).getNombreApellido());
            }
        });

        for (SsUsuario us : listaUsuarios) {

            usuarioListDestino.add(new SelectItem(us.getUsuId(), us.getNombreApellido()));

        }

    }

}
