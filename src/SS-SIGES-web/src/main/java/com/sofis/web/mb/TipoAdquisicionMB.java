package com.sofis.web.mb;

import com.sofis.entities.data.TipoAdquisicion;
import com.sofis.entities.tipos.FiltroTipoAdquisicionTO;
import com.sofis.entities.validations.TipoAdquisicionValidacion;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.web.delegates.TipoAdquisicionDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import java.io.Serializable;
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
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@ManagedBean(name = "tipoAdquisicionMB")
@ViewScoped
public class TipoAdquisicionMB implements Serializable {

    private static final Logger logger = Logger.getLogger(TipoAdquisicionMB.class.getName());

    private List<TipoAdquisicion> tiposAdquisicion;
    private TipoAdquisicion tipoAdquisicion;
    private FiltroTipoAdquisicionTO filtro;

    private Boolean renderABMPopup;
    private Boolean renderResultado;

    @Inject
    private TipoAdquisicionDelegate tipoAdquisicionDelegate;

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;

    @PostConstruct
    public void init() {
        renderABMPopup = false;
        crearFiltro();
        buscar();
    }

    public void crearFiltro() {
        filtro = new FiltroTipoAdquisicionTO();
        filtro.setNombre("");
        filtro.setDescripcion("");
        filtro.setHabilitado(Boolean.TRUE);
        filtro.setOrganismo(this.getInicioMB().getOrganismo());
    }

    public void buscar() {
        tiposAdquisicion = tipoAdquisicionDelegate.obtenerPorFiltro(filtro);
        renderResultado = true;
    }

    public void guardar() {
        try {
            TipoAdquisicionValidacion.validar(tipoAdquisicion);

            tipoAdquisicionDelegate.guardar(tipoAdquisicion);
            this.cerrarPopup();
            this.buscar();
            JSFUtils.agregarMsgInfo("", Labels.getValue("tip_adq_form_success"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (BusinessException ex) {
            logger.log(Level.FINE, ex.getMessage(), ex);
            logger.log(Level.INFO, ex.getMessage());
            for (String iterStr : ex.getErrores()) {
                JSFUtils.agregarMsgError("formTipAdq", Labels.getValue(iterStr), null);
            }
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("formTipAdq", Labels.getValue("tip_adq_form_error_tecnico"), null);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("formTipAdq", Labels.getValue("ERR_GRAL"), null);
        }
    }

    public void limpiar() {
        crearFiltro();
        tiposAdquisicion = null;
        renderResultado = false;
    }

    public void agregar() {
        this.tipoAdquisicion = new TipoAdquisicion();
        this.tipoAdquisicion.setTipAdqHabilitado(Boolean.TRUE);
        this.tipoAdquisicion.setTipAdqOrgFk(this.getInicioMB().getOrganismo());
        this.renderABMPopup = true;
    }

    public void editar(Integer objEstPk) {
        this.tipoAdquisicion = tipoAdquisicionDelegate.obtenerPorId(objEstPk);
        this.renderABMPopup = true;
    }

    public void eliminar(Integer objEstPk) {
        try {
            tipoAdquisicionDelegate.eliminar(objEstPk);
            this.buscar();
            JSFUtils.agregarMsgInfo("", Labels.getValue("tip_adq_delete_success"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (BusinessException ex) {
            logger.log(Level.FINE, ex.getMessage(), ex);
            logger.log(Level.INFO, ex.getMessage());
            JSFUtils.agregarMsgError("", Labels.getValue("tip_adq_delete_error_constraint"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("", Labels.getValue("tip_adq_delete_error_tecnico"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("", Labels.getValue("ERR_GRAL"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        }
    }

    public void cerrarPopup() {
        this.tipoAdquisicion = null;
        this.renderABMPopup = false;
    }

    public void tipoAdquisicionFiltroValueChange(ValueChangeEvent ev) {
        this.filtro.setHabilitado((Boolean) ev.getNewValue());
    }

    //<editor-fold defaultstate="collapsed" desc="getters-setters">
    /**
     * @return the tiposAdquisicion
     */
    public List<TipoAdquisicion> getObjetivosEstrategicos() {
        return tiposAdquisicion;
    }

    /**
     * @param tiposAdquisicion the tiposAdquisicion to set
     */
    public void setObjetivosEstrategicos(List<TipoAdquisicion> tiposAdquisicion) {
        this.tiposAdquisicion = tiposAdquisicion;
    }

    public List<TipoAdquisicion> getTiposAdquisicion() {
        return tiposAdquisicion;
    }

    public void setTiposAdquisicion(List<TipoAdquisicion> tiposAdquisicion) {
        this.tiposAdquisicion = tiposAdquisicion;
    }

    public TipoAdquisicion getTipoAdquisicion() {
        return tipoAdquisicion;
    }

    public void setTipoAdquisicion(TipoAdquisicion tipoAdquisicion) {
        this.tipoAdquisicion = tipoAdquisicion;
    }

    /**
     * @return the renderABMPopup
     */
    public Boolean getRenderABMPopup() {
        return renderABMPopup;
    }

    /**
     * @param renderABMPopup the renderABMPopup to set
     */
    public void setRenderABMPopup(Boolean renderABMPopup) {
        this.renderABMPopup = renderABMPopup;
    }

    /**
     * @return the inicioMB
     */
    public InicioMB getInicioMB() {
        return inicioMB;
    }

    /**
     * @param inicioMB the inicioMB to set
     */
    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    /**
     * @return the renderResultado
     */
    public Boolean getRenderResultado() {
        return renderResultado;
    }

    /**
     * @param renderResultado the renderResultado to set
     */
    public void setRenderResultado(Boolean renderResultado) {
        this.renderResultado = renderResultado;
    }

    /**
     * @return the filtro
     */
    public FiltroTipoAdquisicionTO getFiltro() {
        return filtro;
    }

    /**
     * @param filtro the filtro to set
     */
    public void setFiltro(FiltroTipoAdquisicionTO filtro) {
        this.filtro = filtro;
    }

}
//</editor-fold>
