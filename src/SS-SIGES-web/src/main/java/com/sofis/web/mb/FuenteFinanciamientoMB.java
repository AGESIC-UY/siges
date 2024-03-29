package com.sofis.web.mb;

import com.sofis.business.properties.LabelsEJB;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.Organismos;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.FuenteFinanciamientoDelegate;
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
@ManagedBean(name = "fuenteFinanciamientoMB")
@ViewScoped
public class FuenteFinanciamientoMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(FuenteFinanciamientoMB.class.getName());
    //private static final String BUSQUEDA_MSG = "busquedaMsg";
    private static final String POPUP_MSG = "popupMsg";
    private static final String AREAS_NOMBRE = "fueNombre";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private SofisPopupUI renderPopupEdicion;
    @Inject
    private FuenteFinanciamientoDelegate fuenteFinanciamientoDelegate;

    // Variables
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = AREAS_NOMBRE;
    // 0=descendente, 1=ascendente.
    private int ascendente = 1;
    private String filtroNombre;
    private List<FuenteFinanciamiento> listaResultado;
    private FuenteFinanciamiento fuenteEnEdicion;

    public FuenteFinanciamientoMB() {
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

    public List<FuenteFinanciamiento> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<FuenteFinanciamiento> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public FuenteFinanciamiento getFuenteEnEdicion() {
        return fuenteEnEdicion;
    }

    public void setFuenteEnEdicion(FuenteFinanciamiento fuenteEnEdicion) {
        this.fuenteEnEdicion = fuenteEnEdicion;
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
        listaResultado = new ArrayList<FuenteFinanciamiento>();
        fuenteEnEdicion = new FuenteFinanciamiento();
        
        inicioMB.cargarOrganismoSeleccionado();

        buscar();
    }

    /**
     * Action agregar.
     *
     * @return
     */
    public String agregar() {
        fuenteEnEdicion = new FuenteFinanciamiento();
        fuenteEnEdicion.setFueHabilitada(true);

        renderPopupEdicion.abrir();

        return null;
    }

    /**
     * Action eliminar un area tematica.
     *
     * @return
     */
    public String eliminar(Integer fuentePk) {
        if (fuentePk != null) {
            try {
                fuenteFinanciamientoDelegate.eliminarFuente(fuentePk);
                for (FuenteFinanciamiento f : listaResultado) {
                    if (f.getFuePk().equals(fuentePk)) {
                        listaResultado.remove(f);
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
        mapFiltro.put("nombre", filtroNombre);
        listaResultado = fuenteFinanciamientoDelegate.busquedaFuenteFiltro(inicioMB.getOrganismo().getOrgPk(), mapFiltro, elementoOrdenacion, ascendente);

        return null;
    }

    public String editar(Integer fuentePk) {

        try {
            fuenteEnEdicion = fuenteFinanciamientoDelegate.obtenerFuentePorPk(fuentePk);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, null, ex);
            JSFUtils.agregarMsgs(POPUP_MSG, ex.getErrores());
        }

        renderPopupEdicion.abrir();
        return null;
    }

    public String guardar() {
        fuenteEnEdicion.setFueOrgFk(inicioMB.getOrganismo());

        try {
            fuenteEnEdicion = fuenteFinanciamientoDelegate.guardarFuente(fuenteEnEdicion);

            if (fuenteEnEdicion != null) {
                renderPopupEdicion.cerrar();
                buscar();
            }
        } catch (BusinessException be) {
            logger.log(Level.SEVERE, be.getMessage(), be);
            JSFUtils.agregarMsgs(POPUP_MSG, be.getErrores());
        }
        return null;
    }

    public void fuenteHabilitadaChange(FuenteFinanciamiento fuenteFinanciamiento) {

        try {
            fuenteFinanciamiento.setFueHabilitada(!fuenteFinanciamiento.getFueHabilitada());
            fuenteFinanciamientoDelegate.guardarFuente(fuenteFinanciamiento);

            buscar();
            
            JSFUtils.agregarMsgInfo(Labels.getValue("general_form_success"));
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);

        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

            for(String iterStr : ex.getErrores()){
                JSFUtils.agregarMsgError("", Labels.getValue(iterStr), null);                
            }            
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        }
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
