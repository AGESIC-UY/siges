package com.sofis.web.mb;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.AreasDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
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
@ManagedBean(name = "areasMB")
@ViewScoped
public class AreasMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final String BUSQUEDA_MSG = "busquedaMsg";
    private static final String POPUP_MSG = "popupMsg";
    private static final String AREAS_NOMBRE = "areaNombre";
    private static final String USU_NOMBRE = "nombreApellido";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private SofisPopupUI renderPopupEdicion;
    @Inject
    private AreasDelegate areasDelegate;
    @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;

    // Variables
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = AREAS_NOMBRE;
    // 0=descendente, 1=ascendente.
    private int ascendente = 1;
    private String filtroNombre;
    private SsUsuario filtroDirector;
    private List<Areas> listaResultado;
    private Areas areaEnEdicion;
    private List<Areas> listAreas;
    private List<SsUsuario> listDirector;
    private SofisCombo listaAreasCombo;
    private SofisCombo listaDirectorCombo;
    private SofisCombo listaDirectorPopupCombo;

    public AreasMB() {
        filtroNombre = "";
        listaResultado = new ArrayList<>();
        areaEnEdicion = new Areas();
        listAreas = new ArrayList<>();
        listDirector = new ArrayList<>();
        listaAreasCombo = new SofisCombo();
        listaDirectorCombo = new SofisCombo();
        listaDirectorPopupCombo = new SofisCombo();
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    @PostConstruct
    public void init() {
        inicioMB.cargarOrganismoSeleccionado();

        listDirector = ssUsuarioDelegate.obtenerTodosPorOrganismo(inicioMB.getOrganismo().getOrgPk());
        if (listDirector != null) {
            listaDirectorCombo = new SofisCombo((List) listDirector, USU_NOMBRE);
            listaDirectorCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
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

    public SsUsuario getFiltroDirector() {
        return filtroDirector;
    }

    public void setFiltroDirector(SsUsuario filtroDirector) {
        this.filtroDirector = filtroDirector;
    }

    public List<Areas> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<Areas> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public Areas getAreaEnEdicion() {
        return areaEnEdicion;
    }

    public void setAreaEnEdicion(Areas areaEnEdicion) {
        this.areaEnEdicion = areaEnEdicion;
    }

    public List<Areas> getListAreas() {
        return listAreas;
    }

    public void setListAreas(List<Areas> listAreas) {
        this.listAreas = listAreas;
    }

    public List<SsUsuario> getListDirector() {
        return listDirector;
    }

    public void setListDirector(List<SsUsuario> listDirector) {
        this.listDirector = listDirector;
    }

    public SofisCombo getListaAreasCombo() {
        return listaAreasCombo;
    }

    public void setListaAreasCombo(SofisCombo listaAreasCombo) {
        this.listaAreasCombo = listaAreasCombo;
    }

    public SofisCombo getListaDirectorCombo() {
        return listaDirectorCombo;
    }

    public void setListaDirectorCombo(SofisCombo listaDirectorCombo) {
        this.listaDirectorCombo = listaDirectorCombo;
    }

    public SofisCombo getListaDirectorPopupCombo() {
        return listaDirectorPopupCombo;
    }

    public void setListaDirectorPopupCombo(SofisCombo listaDirectorPopupCombo) {
        this.listaDirectorPopupCombo = listaDirectorPopupCombo;
    }

    /**
     * Action agregar.
     *
     * @return
     */
    public String agregar() {
        areaEnEdicion = new Areas();

        Organismos org = inicioMB.getOrganismo();

        listAreas = areasDelegate.busquedaAreaFiltro(org.getOrgPk(), null, elementoOrdenacion, ascendente);
        if (listaResultado != null) {
            listaAreasCombo = new SofisCombo((List) listAreas, AREAS_NOMBRE);
            listaAreasCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        listDirector = ssUsuarioDelegate.obtenerTodosPorOrganismo(org.getOrgPk());
        if (listDirector != null) {
            listaDirectorCombo = new SofisCombo((List) listDirector, USU_NOMBRE);
            listaDirectorCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
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
                areasDelegate.eliminarArea(aPk);
                for (Areas a : listaResultado) {
                    if (a.getAreaPk().equals(aPk)) {
                        listaResultado.remove(a);
                        break;
                    }
                }
            } catch (BusinessException e) {
                logger.log(Level.SEVERE, null, e);
                JSFUtils.agregarMsgs(BUSQUEDA_MSG, e.getErrores());
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
        filtroDirector = (SsUsuario) listaDirectorCombo.getSelectedObject();

        Map<String, Object> mapFiltro = new HashMap<>();
        mapFiltro.put("nombre", filtroNombre);
        mapFiltro.put("director", filtroDirector);
        listaResultado = areasDelegate.busquedaAreaFiltro(inicioMB.getOrganismo().getOrgPk(), mapFiltro, elementoOrdenacion, ascendente);

        return null;
    }

    public String editar(Integer aPk) {
        Organismos org = inicioMB.getOrganismo();
        try {
            areaEnEdicion = areasDelegate.obtenerAreaPorPk(aPk);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, null, ex);
            JSFUtils.agregarMsgs(POPUP_MSG, ex.getErrores());
        }

        listDirector = ssUsuarioDelegate.obtenerTodosPorOrganismo(org.getOrgPk());
        if (listDirector != null) {
            listaDirectorPopupCombo = new SofisCombo((List) listDirector, USU_NOMBRE);
            listaDirectorPopupCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }
        listaDirectorPopupCombo.setSelectedObject(areaEnEdicion.getAreaDirectorPk());

        renderPopupEdicion.abrir();
        return null;
    }

    public String guardar() {
        Areas areasSelected = (Areas) listaAreasCombo.getSelectedObject();
        SsUsuario directorSelected = (SsUsuario) listaDirectorPopupCombo.getSelectedObject();

        areaEnEdicion.setAreaOrgFk(inicioMB.getOrganismo());
        areaEnEdicion.setAreaPadre(areasSelected);
        areaEnEdicion.setAreaDirectorPk(directorSelected);

        try {
            areaEnEdicion = areasDelegate.guardarArea(areaEnEdicion);

            if (areaEnEdicion != null) {
                renderPopupEdicion.cerrar();
                buscar();
            }
        } catch (BusinessException be) {
            logger.log(Level.SEVERE, be.getMessage(), be);
            JSFUtils.agregarMsgs(BUSQUEDA_MSG, be.getErrores());
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
        listaDirectorCombo.setSelected(-1);
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

    private void quitarAreaDeLista(List<Areas> listAreas, Areas areaEnEdicion) {
        if (CollectionsUtils.isNotEmpty(listAreas) && areaEnEdicion != null) {
            for (Areas at : listAreas) {
                if (at.getAreaPk().equals(areaEnEdicion.getAreaPk())) {
                    listAreas.remove(at);
                    break;
                }
            }
        }
    }
}
