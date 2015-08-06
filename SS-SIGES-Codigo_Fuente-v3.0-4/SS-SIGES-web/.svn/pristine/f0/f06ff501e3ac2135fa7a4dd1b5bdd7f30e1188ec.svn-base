package com.sofis.web.mb;

import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.entities.data.Ayuda;
import com.sofis.exceptions.GeneralException;
import com.sofis.persistence.dao.reference.EntityReference;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import com.sofis.web.genericos.constantes.ConstantesPresentacion;
import com.sofis.web.utils.EntityReferenceDataProvider;
import com.sofis.web.utils.LazyLoadingList;
import com.sofis.web.utils.ProcesarMensajes;
import com.sofis.web.utils.SofisCombo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import com.sofis.web.delegates.ConsultaHistoricoDelegate;
import com.sofis.web.delegates.AyudaDelegate;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "ayudaMB")
@ViewScoped
public class AyudaMB implements Serializable {

    @Inject
    private ConsultaHistoricoDelegate<Ayuda> histDelegate;
    @Inject
    private AyudaDelegate natDelegate;

    private String bCodigo;
    private String bDescripcion;
    private SofisCombo comboCategoria = new SofisCombo();
    private List<EntityReference<Integer>> listaResultado = new ArrayList();
    private Boolean renderResultado = false;
    private Ayuda ayuEnEdicion = new Ayuda();
    private Boolean renderPopupEdicion = false;
    private List<Ayuda> listaHitorial = new ArrayList();
    private Boolean renderPopupHistorial = false;
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = "ayuCodigo";
    private String ascendente = "Ascendente";
    private Boolean popupAyuda = Boolean.FALSE;
    private String codigoAyuda = "";
    private String contenidoAyuda = "";

    /**
     * Creates a new instance of AyudaMB
     */
    public AyudaMB() {
    }

    /**
     * Creates a new instance of AyudaMB
     */
    private void reset() {
        bCodigo = "";
        bDescripcion = "";
        listaResultado = new ArrayList();
        renderResultado = false;
    }

    // <editor-fold defaultstate="collapsed" desc="eventos">
    public String buscar() {
        renderResultado = true;
        List<CriteriaTO> criterios = new ArrayList<CriteriaTO>();

        if (!StringsUtils.isEmpty(bCodigo)) {
            MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.CONTAINS, "ayuCodigo",
                    bCodigo.toUpperCase().trim());
            criterios.add(criterio);
        }

        if (!StringsUtils.isEmpty(bDescripcion)) {
            MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.CONTAINS, "ayuTexto",
                    bDescripcion.toUpperCase().trim());
            criterios.add(criterio);
        }
        CriteriaTO condicion = null;
        if (!criterios.isEmpty()) {
            if (criterios.size() == 1) {
                condicion = criterios.get(0);
            } else {
                condicion = CriteriaTOUtils.createANDTO(criterios
                        .toArray(new CriteriaTO[0]));
            }
        } else {
            // condicion dummy para que el count by criteria funcione
            condicion = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.NOT_NULL, "ayuId", 1);
        }
        String[] propiedades = {"ayuId", "ayuCodigo", "ayuTexto"};
        String className = Ayuda.class.getName();
        String[] orderBy = {elementoOrdenacion};
        boolean[] asc = {"Ascendente".equals(ascendente)};

        EntityReferenceDataProvider cd = new EntityReferenceDataProvider(
                propiedades, className, condicion, orderBy, asc);
        listaResultado = new LazyLoadingList(cd, ConstantesPresentacion.CANTIDAD_PAGINACION, ConstantesPresentacion.PAGINAS_BUFFERED, false);
        return null;
    }

    public String limpiar() {
        reset();
        return null;
    }

    public String agregar() {
        ayuEnEdicion = new Ayuda();
        renderPopupEdicion = true;
        return null;
    }

    public String editar(Integer id) {
        try {
            ayuEnEdicion = natDelegate.obtenerAyudaPorId(id);
            renderPopupEdicion = true;
        } catch (GeneralException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String consultarHistorial(Integer id) {
        try {
            listaHitorial = histDelegate.obtenerHistorialPorId(Ayuda.class, id, "ayuVersion");
            renderPopupHistorial = true;
        } catch (GeneralException ex) {
            ex.printStackTrace();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String cerrarPopupHistorial() {
        renderPopupHistorial = false;
        return null;
    }

    public String guardar() {
        try {
            natDelegate.guardar(ayuEnEdicion);
            renderPopupEdicion = false;
            buscar();
        } catch (GeneralException ex) {
            for (FacesMessage s : ProcesarMensajes.obtenerMensajes(ex.getErrores())) {
                FacesContext.getCurrentInstance().addMessage("", s);
            }
        } catch (Exception ex) {

            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));

        }
        return null;
    }

    public String cancelar() {
//        reset();
        renderPopupEdicion = false;
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
        ascendente = evt.getNewValue().toString();
        buscar();
    }

    public String activarAyuda(String codigo) {
        Ayuda contenido = natDelegate.obtenerAyudaPorCodigo(codigo);
        if (contenido != null) {
            codigoAyuda = codigo;
            contenidoAyuda = contenido.getAyuTexto();
            popupAyuda = Boolean.TRUE;
        }
        return null;
    }

    public String cerrarAyuda() {
        popupAyuda = Boolean.FALSE;
        return null;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="getter-setter">
    public Ayuda getAyuEnEdicion() {
        return ayuEnEdicion;
    }

    public void setAyuEnEdicion(Ayuda ayuEnEdicion) {
        this.ayuEnEdicion = ayuEnEdicion;
    }

    public String getAscendente() {
        return ascendente;
    }

    public void setAscendente(String ascendente) {
        this.ascendente = ascendente;
    }

    public String getElementoOrdenacion() {
        return elementoOrdenacion;
    }

    public void setElementoOrdenacion(String elementoOrdenacion) {
        this.elementoOrdenacion = elementoOrdenacion;
    }

    public String getbCodigo() {
        return bCodigo;
    }

    public void setbCodigo(String bCodigo) {
        this.bCodigo = bCodigo;
    }

    public String getbDescripcion() {
        return bDescripcion;
    }

    public void setbDescripcion(String bDescripcion) {
        this.bDescripcion = bDescripcion;
    }

    public List<EntityReference<Integer>> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<EntityReference<Integer>> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public Boolean getRenderResultado() {
        return renderResultado;
    }

    public void setRenderResultado(Boolean renderResultado) {
        this.renderResultado = renderResultado;
    }

    public Boolean getRenderPopupEdicion() {
        return renderPopupEdicion;
    }

    public void setRenderPopupEdicion(Boolean renderPopupEdicion) {
        this.renderPopupEdicion = renderPopupEdicion;
    }

    public List<Ayuda> getListaHitorial() {
        return listaHitorial;
    }

    public void setListaHitorial(List<Ayuda> listaHitorial) {
        this.listaHitorial = listaHitorial;
    }

    public Boolean getRenderPopupHistorial() {
        return renderPopupHistorial;
    }

    public void setRenderPopupHistorial(Boolean renderPopupHistorial) {
        this.renderPopupHistorial = renderPopupHistorial;
    }

    public String getCantElementosPorPagina() {
        return cantElementosPorPagina;
    }

    public void setCantElementosPorPagina(String cantElementosPorPagina) {
        this.cantElementosPorPagina = cantElementosPorPagina;
    }

    // </editor-fold>
    public Boolean getPopupAyuda() {
        return popupAyuda;
    }

    public void setPopupAyuda(Boolean popupAyuda) {
        this.popupAyuda = popupAyuda;
    }

    public String getCodigoAyuda() {
        return codigoAyuda;
    }

    public void setCodigoAyuda(String codigoAyuda) {
        this.codigoAyuda = codigoAyuda;
    }

    public String getContenidoAyuda() {
        return contenidoAyuda;
    }

    public void setContenidoAyuda(String contenidoAyuda) {
        this.contenidoAyuda = contenidoAyuda;
    }
}
