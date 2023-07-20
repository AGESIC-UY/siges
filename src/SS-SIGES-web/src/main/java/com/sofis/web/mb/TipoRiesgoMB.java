package com.sofis.web.mb;

import com.sofis.entities.data.TipoRiesgo;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.TipoRiesgoDelegate;
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
@ManagedBean(name = "tipoRiesgoMB")
@ViewScoped
public class TipoRiesgoMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(TipoRiesgoMB.class.getName());
    private static final String BUSQUEDA_MSG = "busquedaMsg";
    private static final String POPUP_MSG = "popupMsg";
    private static final String AMBITO_NOMBRE = "trsNombre";
    private static final String USU_NOMBRE = "nombreApellido";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private SofisPopupUI renderPopupEdicion;
    @Inject
    private TipoRiesgoDelegate tipoRiesgoDelegate;

    // Variables
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = AMBITO_NOMBRE;
    // 0=descendente, 1=ascendente.
    private int ascendente = 1;
    private String filtroNombre;
    private List<TipoRiesgo> listaResultado;
    private TipoRiesgo tipoRiegoEnEdicion;

    public TipoRiesgoMB() {
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

    public List<TipoRiesgo> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<TipoRiesgo> listaResultado) {
        this.listaResultado = listaResultado;
    }

    

    public TipoRiesgo getTipoRiegoEnEdicion() {
        return tipoRiegoEnEdicion;
    }

    public void setTipoRiegoEnEdicion(TipoRiesgo tipoRiegoEnEdicion) {
        this.tipoRiegoEnEdicion = tipoRiegoEnEdicion;
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
        listaResultado = new ArrayList<TipoRiesgo>();
        tipoRiegoEnEdicion = new TipoRiesgo();
        
        inicioMB.cargarOrganismoSeleccionado();

        buscar();
    }

    /**
     * Action agregar.
     *
     * @return
     */
    public String agregar() {
        tipoRiegoEnEdicion = new TipoRiesgo();

        renderPopupEdicion.abrir();
        return null;
    }

    /**
     * Action eliminar un Ambito.
     *
     * @return
     */
    public String eliminar(Integer aPk) {
        if (aPk != null) {
            try {
                tipoRiesgoDelegate.eliminarTipoRiesgo(aPk);
                for (TipoRiesgo a : listaResultado) {
                    if (a.getTrsPk().equals(aPk)) {
                        listaResultado.remove(a);
                        break;
                    }
                }
            } catch (BusinessException e) {
                logger.log(Level.SEVERE, null, e);
                
                /*
                *  18-06-2018 Inspección de código.
                */
                
                //JSFUtils.agregarMsgs(BUSQUEDA_MSG, e.getErrores());
                
                for(String iterStr : e.getErrores()){
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
        mapFiltro.put(AMBITO_NOMBRE, filtroNombre);
        listaResultado = tipoRiesgoDelegate.busquedaTipoRiesgoFiltro(inicioMB.getOrganismo().getOrgPk(), mapFiltro, elementoOrdenacion, ascendente);

        return null;
    }

    public String editar(Integer aPk) {
        try {
            tipoRiegoEnEdicion = tipoRiesgoDelegate.obtenerTipoRiesgoPorId(aPk);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, null, ex);
            JSFUtils.agregarMsgs(POPUP_MSG, ex.getErrores());
        }

        renderPopupEdicion.abrir();
        return null;
    }

    public String guardar() {
        tipoRiegoEnEdicion.setTrsOrgFk(inicioMB.getOrganismo());

        try {
            tipoRiegoEnEdicion = tipoRiesgoDelegate.guardarTipoRiesgo(tipoRiegoEnEdicion);

            if (tipoRiegoEnEdicion != null) {
                renderPopupEdicion.cerrar();
                buscar();
            }
        } catch (BusinessException be) {
            logger.log(Level.SEVERE, be.getMessage(), be);
            
            /*
            *  18-06-2018 Inspección de código.
            */

            //JSFUtils.agregarMsgs(BUSQUEDA_MSG, be.getErrores());
            
            
            for(String iterStr : be.getErrores()){
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
        elementoOrdenacion = AMBITO_NOMBRE;
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
}
