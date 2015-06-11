package com.sofis.web.mb;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.TipoDocumento;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.EstadosDelegate;
import com.sofis.web.delegates.TipoDocumentoDelegate;
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
@ManagedBean(name = "tipoDocumentoMB")
@ViewScoped
public class TipoDocumentoMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final String BUSQUEDA_MSG = "busquedaMsg";
    private static final String POPUP_MSG = "popupMsg";
    private static final String TIPO_DOC_NOMBRE = "tipodocNombre";
    private static final String TIPO_DOC_EXIGIDO_DESDE = "tipodocExigidoDesde";
    
    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private SofisPopupUI renderPopupEdicion;
    @Inject
    private TipoDocumentoDelegate tipoDocumentoDelegate;
    @Inject
    private EstadosDelegate estadosDelegate;
    // Variables
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = TIPO_DOC_NOMBRE;
    // 0=descendente, 1=ascendente.
    private int ascendente = 1;
    private String filtroNombre;
    private Integer filtroEstados;
    private List<TipoDocumento> listaResultado;
    private TipoDocumento tipoDocEnEdicion;
    private List<Estados> listEstados;

    public TipoDocumentoMB() {
        filtroNombre = "";
        listaResultado = new ArrayList<>();
        tipoDocEnEdicion = new TipoDocumento();
        listEstados = new ArrayList<>();
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

    public Integer getFiltroEstados() {
        return filtroEstados;
    }

    public void setFiltroEstados(Integer filtroEstados) {
        this.filtroEstados = filtroEstados;
    }

    public List<TipoDocumento> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<TipoDocumento> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public TipoDocumento getTipoDocEnEdicion() {
        return tipoDocEnEdicion;
    }

    public void setTipoDocEnEdicion(TipoDocumento tipoDocEnEdicion) {
        this.tipoDocEnEdicion = tipoDocEnEdicion;
    }

    public List<Estados> getListEstados() {
        return listEstados;
    }

    public void setListEstados(List<Estados> listEstados) {
        this.listEstados = listEstados;
    }

    @PostConstruct
    public void init() {
        inicioMB.cargarOrganismoSeleccionado();

        listEstados = estadosDelegate.obtenerEstadosCombo();

        buscar();
    }

    /**
     * Action agregar.
     *
     * @return
     */
    public String agregar() {
        tipoDocEnEdicion = new TipoDocumento();

        listEstados = estadosDelegate.obtenerEstadosCombo();

        renderPopupEdicion.abrir();
        return null;
    }

    /**
     * Action eliminar un area tematica.
     *
     * @return
     */
    public String eliminar(Integer tipoDocPk) {
        if (tipoDocPk != null) {
            try {
                tipoDocumentoDelegate.eliminarTipoDoc(tipoDocPk);
                buscar();
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
        mapFiltro.put(TIPO_DOC_NOMBRE, filtroNombre);
        if (filtroEstados != null) {
            mapFiltro.put(TIPO_DOC_EXIGIDO_DESDE, filtroEstados);
        }
        listaResultado = tipoDocumentoDelegate.busquedaTipoDocFiltro(mapFiltro, elementoOrdenacion, ascendente, inicioMB.getOrganismo().getOrgPk());
        for (TipoDocumento td : listaResultado) {
            td.setExigidoDesdeStr(estadosDelegate.estadoStr(td.getTipodocExigidoDesde()));
        }

        return null;
    }

    public String editar(Integer tipoDocPk) {
        try {
            tipoDocEnEdicion = tipoDocumentoDelegate.obtenerTipoDocPorId(tipoDocPk);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, null, ex);
            JSFUtils.agregarMsgs(POPUP_MSG, ex.getErrores());
        }

        listEstados = estadosDelegate.obtenerEstadosCombo();

        renderPopupEdicion.abrir();
        return null;
    }

    public String guardar() {
        tipoDocEnEdicion.setTipoDocOrgFk(inicioMB.getOrganismo());

        try {
            tipoDocEnEdicion = tipoDocumentoDelegate.guardarTipoDoc(tipoDocEnEdicion);

            if (tipoDocEnEdicion != null) {
                renderPopupEdicion.cerrar();
                filtroNombre = null;
                filtroEstados = null;
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
        filtroEstados = null;
        listaResultado = null;
        elementoOrdenacion = TIPO_DOC_NOMBRE;
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

    public String estadoStr(Integer estPk) {
        return estadosDelegate.estadoStr(estPk);
    }

}