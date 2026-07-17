package com.sofis.web.mb;

import com.sofis.entities.data.LogAdquisicion;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.AdquisicionDelegate;
import com.sofis.web.delegates.AdquisicionInfoDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import java.io.File;
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
@ManagedBean(name = "adquisicionInfoMB")
@ViewScoped
public class AdquisicionInfoMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(AdquisicionInfoMB.class.getName());
    //private static final String BUSQUEDA_MSG = "busquedaMsg";
    private static final String POPUP_MSG = "popupMsg";
    // private static final String POPUP_MSG_MASIVA = "popupMsgMasiva";

    private static final String AREAS_NOMBRE = "pciNombre";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private SofisPopupUI renderPopupEdicion;

    @Inject
    private AdquisicionInfoDelegate adquisicionInfoDelegate;

    @Inject
    private AdquisicionDelegate adquisiconDelegate;
    //agregado par acarga masiva
    @Inject
    private SofisPopupUI renderPopupCargaArchivo;

    // Variables
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = AREAS_NOMBRE;
    // 0=descendente, 1=ascendente.
    private int ascendente = 1;
    private String filtroNombre;
    private String filtroNombreProyecto;
    private Integer filtroCodigoProyecto;
    private Integer filtroIdAdquisicion;
    private List<LogAdquisicion> listaResultado;
    private LogAdquisicion adquisicionInfoEnEdicion;

    private File exelCargaMasiva;

    public AdquisicionInfoMB() {
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


    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public String getFiltroNombreProyecto() {
        return filtroNombreProyecto;
    }

    public void setFiltroNombreProyecto(String filtroNombreProyecto) {
        this.filtroNombreProyecto = filtroNombreProyecto;
    }

    public Integer getFiltroCodigoProyecto() {
        return filtroCodigoProyecto;
    }

    public void setFiltroCodigoProyecto(Integer filtroCodigoProyecto) {
        this.filtroCodigoProyecto = filtroCodigoProyecto;
    }

    public Integer getFiltroIdAdquisicion() {
        return filtroIdAdquisicion;
    }

    public void setFiltroIdAdquisicion(Integer filtroIdAdquisicion) {
        this.filtroIdAdquisicion = filtroIdAdquisicion;
    }

    public List<LogAdquisicion> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<LogAdquisicion> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public LogAdquisicion getAdquisicionInfoEnEdicion() {
        return adquisicionInfoEnEdicion;
    }

    public void setAdquisicionInfoEnEdicion(LogAdquisicion adquisicionInfoEnEdicion) {
        this.adquisicionInfoEnEdicion = adquisicionInfoEnEdicion;
    }
    
    

    @PostConstruct
    public void init() {

        /*
            *   31-05-2018 Nico: Se sacan las variables que se inicializan del constructor y se pasan al PostConstruct
         */
        filtroNombre = "";
        listaResultado = new ArrayList<LogAdquisicion>();
        adquisicionInfoEnEdicion = new LogAdquisicion();

        inicioMB.cargarOrganismoSeleccionado();
        elementoOrdenacion = "ladPk";
        ascendente = 0;
        buscar();
    }

    /**
     * Action agregar.
     *
     * @return
     */
    //Acá va lo operación de carga masiva
    public String agregarArchivo() {
        renderPopupCargaArchivo.abrir();
        return null;
    }

    /**
     * Action Buscar.
     *
     * @return
     */
    public String buscar() {
        Map<String, Object> mapFiltro = new HashMap<String, Object>();
        mapFiltro.put("nombreAdquisicion", filtroNombre);
        mapFiltro.put("nombreProyecto", filtroNombreProyecto);
        mapFiltro.put("codigoProyecto", filtroCodigoProyecto);
        mapFiltro.put("idAdquisicion", filtroIdAdquisicion);
        listaResultado = adquisicionInfoDelegate.busquedaLogAdquisicionFiltro(inicioMB.getOrganismo().getOrgPk(), mapFiltro, elementoOrdenacion, ascendente);
        return null;
    }

    public String editar(Integer procedimientoCompraPk) {
        try {
            adquisicionInfoEnEdicion = adquisicionInfoDelegate.obtenerLogAdquisicionPorPk(procedimientoCompraPk);
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
        listaResultado = null;
        elementoOrdenacion = "ladPk";
        ascendente = 1;
        filtroCodigoProyecto = 0;
        filtroIdAdquisicion = 0;
        filtroNombreProyecto = null;

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
