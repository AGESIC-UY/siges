package com.sofis.web.mb;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.AreaConocimiento;
import com.sofis.entities.data.Organismos;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.AreasConocimientoDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisCombo;
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
@ManagedBean(name = "areaConocimientoMB")
@ViewScoped
public class AreaConocimientoMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(AreaConocimientoMB.class.getName());
    //private static final String BUSQUEDA_MSG = "busquedaMsg";
    private static final String POPUP_MSG = "popupMsg";
    private static final String AREAS_NOMBRE = "conNombre";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private AreasConocimientoDelegate areasConocimientoDelegate;

    // Variables
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = AREAS_NOMBRE;
    // 0=descendente, 1=ascendente.
    private int ascendente = 1;
    private String filtroNombre;
    private List<AreaConocimiento> listaResultado;
    private AreaConocimiento areaEnEdicion;
    private List<AreaConocimiento> listAreas;
    private SofisPopupUI renderPopupEdicion;
    private SofisCombo listaAreasCombo;
    private SofisCombo listaAreasPopupCombo;

    public AreaConocimientoMB() {
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
        listaResultado = new ArrayList<AreaConocimiento>();
        listAreas = new ArrayList<AreaConocimiento>();
        renderPopupEdicion = new SofisPopupUI();
        listaAreasCombo = new SofisCombo();
        listaAreasPopupCombo = new SofisCombo();        
        
        inicioMB.cargarOrganismoSeleccionado();

        Organismos org = inicioMB.getOrganismo();
        listAreas = areasConocimientoDelegate.obtenerSoloPadres(org.getOrgPk());
        if (listAreas != null) {
            listaAreasCombo = new SofisCombo((List) listAreas, AREAS_NOMBRE);
            listaAreasCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        buscar();
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

    public List<AreaConocimiento> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<AreaConocimiento> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public AreaConocimiento getAreaEnEdicion() {
        return areaEnEdicion;
    }

    public void setAreaEnEdicion(AreaConocimiento areaEnEdicion) {
        this.areaEnEdicion = areaEnEdicion;
    }

    public List<AreaConocimiento> getListAreas() {
        return listAreas;
    }

    public void setListAreas(List<AreaConocimiento> listAreas) {
        this.listAreas = listAreas;
    }

    public SofisCombo getListaAreasCombo() {
        return listaAreasCombo;
    }

    public void setListaAreasCombo(SofisCombo listaAreasCombo) {
        this.listaAreasCombo = listaAreasCombo;
    }

    public SofisCombo getListaAreasPopupCombo() {
        return listaAreasPopupCombo;
    }

    public void setListaAreasPopupCombo(SofisCombo listaAreasPopupCombo) {
        this.listaAreasPopupCombo = listaAreasPopupCombo;
    }

    /**
     * Action agregar.
     *
     * @return
     */
    public String agregar() {
        areaEnEdicion = new AreaConocimiento();

        Organismos org = inicioMB.getOrganismo();

        listAreas = areasConocimientoDelegate.busquedaAreaFiltro(org.getOrgPk(), null, elementoOrdenacion, ascendente);
        if (listAreas != null) {
            listaAreasPopupCombo = new SofisCombo((List) listAreas, AREAS_NOMBRE);
            listaAreasPopupCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        renderPopupEdicion.abrir();
        return null;
    }

    /**
     * Action eliminar un area.
     *
     * @return
     */
    public String eliminar(Integer aPk) {
        if (aPk != null) {
            try {
                areasConocimientoDelegate.eliminarArea(aPk);
                for (AreaConocimiento a : listaResultado) {
                    if (a.getConPk().equals(aPk)) {
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
        Map<String, Object> mapFiltro = new HashMap<>();
        mapFiltro.put("nombre", filtroNombre);

        if (listaAreasCombo.getSelected() > 0) {
            mapFiltro.put("padre", (AreaConocimiento) listaAreasCombo.getSelectedObject());
        }

        listaResultado = areasConocimientoDelegate.busquedaAreaFiltro(inicioMB.getOrganismo().getOrgPk(), mapFiltro, elementoOrdenacion, ascendente);

        return null;
    }

    public String editar(Integer aPk) {
        Organismos org = inicioMB.getOrganismo();
        try {
            areaEnEdicion = areasConocimientoDelegate.obtenerAreaPorPk(aPk);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, null, ex);
            
            /*
            *  18-06-2018 Inspección de código.
            */

            //JSFUtils.agregarMsgs(POPUP_MSG, ex.getErrores());

            for(String iterStr : ex.getErrores()){
                JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);                
            }

            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
            
            
        }

        listAreas = areasConocimientoDelegate.busquedaAreaFiltro(inicioMB.getOrganismo().getOrgPk(), null, elementoOrdenacion, ascendente);
        quitarAreaDeLista(listAreas, areaEnEdicion);
        if (listAreas != null) {
            listaAreasPopupCombo = new SofisCombo((List) listAreas, AREAS_NOMBRE);
            listaAreasPopupCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }
        listaAreasPopupCombo.setSelectedObject(areaEnEdicion.getAreaConPadreFk());

        renderPopupEdicion.abrir();
        return null;
    }

    public String guardar() {
        AreaConocimiento areasSelected = (AreaConocimiento) listaAreasPopupCombo.getSelectedObject();

        areaEnEdicion.setConOrganismo(inicioMB.getOrganismo());
        areaEnEdicion.setAreaConPadreFk(areasSelected);

        try {
            areaEnEdicion = areasConocimientoDelegate.guardarArea(areaEnEdicion);

            if (areaEnEdicion != null) {
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
        elementoOrdenacion = AREAS_NOMBRE;
        ascendente = 1;
        listaAreasPopupCombo.setSelected(-1);

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

    private void quitarAreaDeLista(List<AreaConocimiento> listAreas, AreaConocimiento areaEnEdicion) {
        if (CollectionsUtils.isNotEmpty(listAreas) && areaEnEdicion != null) {
            for (AreaConocimiento at : listAreas) {
                if (at.getConPk().equals(areaEnEdicion.getConPk())) {
                    listAreas.remove(at);
                    break;
                }
            }
        }
    }
}
