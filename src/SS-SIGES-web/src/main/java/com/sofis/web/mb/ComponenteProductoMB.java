package com.sofis.web.mb;

import com.sofis.entities.data.ComponenteProducto;
import com.sofis.entities.data.Organismos;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.ComponenteProductoDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
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
@ManagedBean(name = "componenteProductoMB")
@ViewScoped
public class ComponenteProductoMB implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ComponenteProductoMB.class.getName());
    //private static final String BUSQUEDA_MSG = "busquedaMsg";
    private static final String POPUP_MSG = "popupMsg";
    private static final String AREAS_NOMBRE = "comNombre";
    
    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private SofisPopupUI renderPopupEdicion;
    @Inject
    private ComponenteProductoDelegate componenteProductoDelegate;

    // Variables
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = AREAS_NOMBRE;
    // 0=descendente, 1=ascendente.
    private int ascendente = 1;
    private String filtroNombre;
    private List<ComponenteProducto> listaResultado;
    private ComponenteProducto componenteProductoEnEdicion;
    
    public ComponenteProductoMB() {
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
    
    public List<ComponenteProducto> getListaResultado() {
        return listaResultado;
    }
    
    public void setListaResultado(List<ComponenteProducto> listaResultado) {
        this.listaResultado = listaResultado;
    }
    
    public ComponenteProducto getComponenteProductoEnEdicion() {
        return componenteProductoEnEdicion;
    }
    
    public void setFuenteEnEdicion(ComponenteProducto componenteProductoEnEdicion) {
        this.componenteProductoEnEdicion = componenteProductoEnEdicion;
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
        listaResultado = new ArrayList<ComponenteProducto>();
        componenteProductoEnEdicion = new ComponenteProducto();
        
        inicioMB.cargarOrganismoSeleccionado();
        
        buscar();
    }

    /**
     * Action agregar.
     *
     * @return
     */
    public String agregar() {
        componenteProductoEnEdicion = new ComponenteProducto();
        
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
                componenteProductoDelegate.eliminarComponenteProducto(componenteProductoPk);
                for (ComponenteProducto f : listaResultado) {
                    if (f.getComPk().equals(componenteProductoPk)) {
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
        listaResultado = componenteProductoDelegate.busquedaComponenteProductoFiltro(inicioMB.getOrganismo().getOrgPk(), mapFiltro, elementoOrdenacion, ascendente);
        
        return null;
    }
    
    public String editar(Integer componenteProductoPk) {
        Organismos org = inicioMB.getOrganismo();
        try {
            componenteProductoEnEdicion = componenteProductoDelegate.obtenerComponenteProductoPorPk(componenteProductoPk);
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

        /*
        *  Se analiza el largo del nombre de la componente producto
         */
        if (componenteProductoEnEdicion.getComPk() == null) {
            componenteProductoEnEdicion.setComHabilitada(Boolean.TRUE);
        }
        
        if (componenteProductoEnEdicion.getComNombre().length() >= 100) {
            JSFUtils.agregarMsgError("popupMsg", Labels.getValue("error_componente_producto_largo_nombre"), null);
        } else {
            componenteProductoEnEdicion.setComOrgFk(inicioMB.getOrganismo());
            
            try {
                componenteProductoEnEdicion = componenteProductoDelegate.guardarComponenteProducto(componenteProductoEnEdicion);
                
                if (componenteProductoEnEdicion != null) {
                    renderPopupEdicion.cerrar();
                    buscar();
                }
            } catch (BusinessException be) {
                logger.log(Level.SEVERE, be.getMessage(), be);
                //JSFUtils.agregarMsgs(BUSQUEDA_MSG, be.getErrores());
                /*
                *   15-06-2018 Nico: Se agrega este for para poder mostrar en el front todos los mensajes
                *           enviados desde la lógica.
                 */
                
                for (String iterStr : be.getErrores()) {
                    JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);
                }
                
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
        
        return null;
    }
    
    public void componenteProductoChange(ComponenteProducto componenteProducto) {
        
        try {
            componenteProducto.setComHabilitada(!componenteProducto.getComHabilitada());
            componenteProductoDelegate.guardarComponenteProducto(componenteProducto);
            
            buscar();
            
            JSFUtils.agregarMsgInfo(Labels.getValue("general_form_success"));
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
            
        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            
            for (String iterStr : ex.getErrores()) {
                JSFUtils.agregarMsgError("", Labels.getValue(iterStr), null);
            }
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        }
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
}
