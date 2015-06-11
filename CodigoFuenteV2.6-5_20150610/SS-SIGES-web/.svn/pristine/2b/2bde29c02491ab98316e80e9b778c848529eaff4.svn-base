package com.sofis.web.mb;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.TipoGasto;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.TipoGastoDelegate;
import com.sofis.web.utils.JSFUtils;
import java.util.List;
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
@ManagedBean(name = "tipoGastoMB")
@ViewScoped
public class TipoGastoMB {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final String BUSQUEDA_MSG = "busquedaMsg";
    private static final String POPUP_MSG = "popupMsg";
    private static final String TIPO_GASTO_NOMBRE = "tipogasNombre";
    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private SofisPopupUI renderPopupEdicion;
    @Inject
    private TipoGastoDelegate tipoGastoDelegate;
    // Variables
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = TIPO_GASTO_NOMBRE;
    // 0=descendente, 1=ascendente.
    private int ascendente = 1;
    private String filtroNombre;
    private List<TipoGasto> listaResultado;
    private TipoGasto tipoGastoEnEdicion;

    public TipoGastoMB() {
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    @PostConstruct
    public void init() {
        inicioMB.cargarOrganismoSeleccionado();

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

    public List<TipoGasto> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<TipoGasto> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public TipoGasto getTipoGastoEnEdicion() {
        return tipoGastoEnEdicion;
    }

    public void setTipoGastoEnEdicion(TipoGasto tipoGastoEnEdicion) {
        this.tipoGastoEnEdicion = tipoGastoEnEdicion;
    }

    /**
     * Action agregar.
     *
     * @return
     */
    public String agregar() {
        tipoGastoEnEdicion = new TipoGasto();

        renderPopupEdicion.abrir();
        return null;
    }

    /**
     * Action eliminar un area tematica.
     *
     * @return
     */
    public String eliminar(Integer tgPk) {
        if (tgPk != null) {
            try {
                tipoGastoDelegate.eliminarTipoGasto(tgPk);
                for (TipoGasto tg : listaResultado) {
                    if (tg.getTipogasPk().equals(tgPk)) {
                        listaResultado.remove(tg);
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
        listaResultado = tipoGastoDelegate.busquedaTipoGastoFiltro(inicioMB.getOrganismo().getOrgPk(), filtroNombre, elementoOrdenacion, ascendente);

        return null;
    }

    public String editar(Integer tgPk) {
        try {
            tipoGastoEnEdicion = tipoGastoDelegate.obtenerTipoGastoPorPk(tgPk);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, null, ex);
            JSFUtils.agregarMsgs(POPUP_MSG, ex.getErrores());
        }

        renderPopupEdicion.abrir();
        return null;
    }

    public String guardar() {
        tipoGastoEnEdicion.setTipogasOrgFk(inicioMB.getOrganismo());

        try {
            tipoGastoEnEdicion = tipoGastoDelegate.guardarTipoGasto(tipoGastoEnEdicion);

            if (tipoGastoEnEdicion != null) {
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
        elementoOrdenacion = TIPO_GASTO_NOMBRE;
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
