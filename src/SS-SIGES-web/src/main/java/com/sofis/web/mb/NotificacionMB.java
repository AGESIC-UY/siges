package com.sofis.web.mb;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Notificacion;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.NotificacionDelegate;
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
@ManagedBean(name = "notificacionMB")
@ViewScoped
public class NotificacionMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(NotificacionMB.class.getName());
    private static final String BUSQUEDA_MSG = "busquedaMsg";
    private static final String POPUP_MSG = "popupMsg";
    private static final String NOTIF_COD = "notCod";
    private static final String NOTIF_DESC = "notDesc";
    private static final String NOTIF_MSG = "notMsg";
    
    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private SofisPopupUI renderPopupEdicion;
    @Inject
    private NotificacionDelegate notificacionDelegate;
    
    // Variables
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = NOTIF_COD;
    // 0=descendente, 1=ascendente.
    private int ascendente = 1;
    private String filtroCodigo;
    private String filtroDesc;
    private String filtroMsg;
    private List<Notificacion> listaResultado;
    private Notificacion notifEnEdicion;

    public NotificacionMB() {
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

    public String getFiltroCodigo() {
        return filtroCodigo;
    }

    public void setFiltroCodigo(String filtroCodigo) {
        this.filtroCodigo = filtroCodigo;
    }

    public String getFiltroDesc() {
        return filtroDesc;
    }

    public void setFiltroDesc(String filtroDesc) {
        this.filtroDesc = filtroDesc;
    }

    public String getFiltroMsg() {
        return filtroMsg;
    }

    public void setFiltroMsg(String filtroMsg) {
        this.filtroMsg = filtroMsg;
    }

    public List<Notificacion> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<Notificacion> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public Notificacion getNotifEnEdicion() {
        return notifEnEdicion;
    }

    public void setNotifEnEdicion(Notificacion notifEnEdicion) {
        this.notifEnEdicion = notifEnEdicion;
    }

    @PostConstruct
    public void init() {
        
        /*
        *   30-05-2018 Nico: Se sacan las variables que se inicializan del constructor y se pasan al PostConstruct
        */        
        
        filtroCodigo = "";
        filtroDesc = "";
        filtroMsg = "";
        listaResultado = new ArrayList<Notificacion>();
        notifEnEdicion = new Notificacion();        
        //Validación de campos
        if(notifEnEdicion.getNotMsg()== null || notifEnEdicion.getNotMsg().equals("")){
            notifEnEdicion.setNotMsg("<p></p>");
        }
        inicioMB.cargarOrganismoSeleccionado();

        buscar();
    }

    /**
     * Action agregar.
     *
     * @return
     */
    public String agregar() {
        notifEnEdicion = new Notificacion();

        renderPopupEdicion.abrir();
        return null;
    }

    /**
     * Action eliminar un area tematica.
     *
     * @return
     */
    public String eliminar(Integer notifPk) {
        if (notifPk != null) {
            try {
                notificacionDelegate.eliminarNotificacion(notifPk);
                for (Notificacion n : listaResultado) {
                    if (n.getNotPk().equals(notifPk)) {
                        listaResultado.remove(n);
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
        mapFiltro.put(NOTIF_COD, filtroCodigo);
        mapFiltro.put(NOTIF_DESC, filtroDesc);
        mapFiltro.put(NOTIF_MSG, filtroMsg);

        listaResultado = notificacionDelegate.busquedaNotifFiltro(inicioMB.getOrganismo().getOrgPk(), mapFiltro, elementoOrdenacion, ascendente);

        return null;
    }

    public String editar(Integer notifPk) {
        try {
            notifEnEdicion = notificacionDelegate.obtenerNotifPorPk(notifPk);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, null, ex);
            JSFUtils.agregarMsgs(POPUP_MSG, ex.getErrores());
        }

        renderPopupEdicion.abrir();
        return null;
    }

    public String guardar() {

        notifEnEdicion.setNotOrgFk(inicioMB.getOrganismo());

        try {
            notifEnEdicion = notificacionDelegate.guardarNotificacion(notifEnEdicion);

            if (notifEnEdicion != null) {
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
        filtroCodigo = null;
        filtroDesc = null;
        filtroMsg = null;
        listaResultado = null;
        elementoOrdenacion = NOTIF_COD;
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
