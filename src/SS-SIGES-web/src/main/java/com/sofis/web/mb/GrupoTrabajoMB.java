package com.sofis.web.mb;

import com.sofis.entities.codigueras.SsRolCodigos;
import com.sofis.entities.data.ComponenteProducto;
import com.sofis.entities.data.GrupoTrabajo;
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
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisCombo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "grupoTrabajoMB")
@ViewScoped
public class GrupoTrabajoMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(GrupoTrabajoMB.class.getName());
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
    private List<GrupoTrabajo> listaResultado;
    private GrupoTrabajo cgrupoTrabajoEnEdicion;
    private SofisCombo listaGerenteCombo = new SofisCombo();

    public GrupoTrabajoMB() {

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

        filtroNombre = "";
        listaResultado = new ArrayList<GrupoTrabajo>();
        cgrupoTrabajoEnEdicion = new GrupoTrabajo();

        inicioMB.cargarOrganismoSeleccionado();
        cargarCombosFiltro();
        buscar();
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
                
                grupoTrabajoOperacionDetalleDelegate.eliminarGrupo(componenteProductoPk);
                grupoTrabajoUsuarioDelegate.eliminarGrupo(componenteProductoPk);
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
        return null;
    }

    public void cancelar() {
        renderPopupEdicion.cerrar();
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
        if (listaUsuariosPMOF != null && !listaUsuariosPMOF.isEmpty()) {
            listaGerenteCombo = new SofisCombo((List) listaUsuariosPMOF, "usuNombreApellido");
            listaGerenteCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

    }
}
