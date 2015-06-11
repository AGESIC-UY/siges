package com.sofis.web.mb;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.Organismos;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.MonedaDelegate;
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
@ManagedBean(name = "monedaMB")
@ViewScoped
public class MonedaMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final String BUSQUEDA_MSG = "busquedaMsg";
    private static final String POPUP_MSG = "popupMsg";
    private static final String MONEDA_NOMBRE = "monNombre";
    private static final String MONEDA_SIGNO = "monSigno";
    private static final String MONEDA_COD_PAIS = "monCodPais";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private SofisPopupUI renderPopupEdicion;
    @Inject
    private MonedaDelegate monedaDelegate;

    // Variables
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = MONEDA_NOMBRE;
    // 0=descendente, 1=ascendente.
    private int ascendente = 1;
    private String filtroNombre;
    private List<Moneda> listaResultado;
    private Moneda monedaEnEdicion;

    public MonedaMB() {
        filtroNombre = "";
        listaResultado = new ArrayList<>();
        monedaEnEdicion = new Moneda();
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
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

    public List<Moneda> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<Moneda> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public Moneda getMonedaEnEdicion() {
        return monedaEnEdicion;
    }

    public void setMonedaEnEdicion(Moneda monedaEnEdicion) {
        this.monedaEnEdicion = monedaEnEdicion;
    }

    @PostConstruct
    public void init() {
        inicioMB.cargarOrganismoSeleccionado();

        buscar();
    }

    /**
     * Action agregar.
     *
     * @return
     */
    public String agregar() {
        monedaEnEdicion = new Moneda();

        renderPopupEdicion.abrir();
        return null;
    }

    /**
     * Action eliminar un area tematica.
     *
     * @return
     */
    public String eliminar(Integer monedaPk) {
        if (monedaPk != null) {
            try {
                monedaDelegate.eliminarMoneda(monedaPk);
                for (Moneda m : listaResultado) {
                    if (m.getMonPk().equals(monedaPk)) {
                        listaResultado.remove(m);
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
        Map<String, Object> mapFiltro = new HashMap<>();
        mapFiltro.put(MONEDA_NOMBRE, filtroNombre);
        listaResultado = monedaDelegate.busquedaMonedaFiltro(mapFiltro, elementoOrdenacion, ascendente, 1);

        return null;
    }

    public String editar(Integer aPk) {
        Organismos org = inicioMB.getOrganismo();
        try {
            monedaEnEdicion = monedaDelegate.obtenerMonedaPorId(aPk);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, null, ex);
            JSFUtils.agregarMsgs(POPUP_MSG, ex.getErrores());
        }

        renderPopupEdicion.abrir();
        return null;
    }

    public String guardar() {
        try {
            monedaEnEdicion = monedaDelegate.guardarMoneda(monedaEnEdicion);

            if (monedaEnEdicion != null) {
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
        listaResultado = null;
        elementoOrdenacion = MONEDA_NOMBRE;
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
