package com.sofis.web.mb;

import com.sofis.business.properties.LabelsEJB;
import com.sofis.entities.data.CausalCompra;
import com.sofis.entities.tipos.FiltroCausalCompraTO;
import com.sofis.entities.validations.CausalCompraValidacion;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.web.delegates.CausalCompraDelegate;
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
@ManagedBean(name = "causalCompraMB")
@ViewScoped
public class CausalCompraMB implements Serializable {

    private static final Logger logger = Logger.getLogger(CausalCompraMB.class.getName());

    private List<CausalCompra> causalesCompra;
    private CausalCompra causalCompra;
    private FiltroCausalCompraTO filtro;

    private Boolean renderABMPopup;
    private Boolean renderResultado;

    @Inject
    private CausalCompraDelegate causalCompraDelegate;

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;

    @PostConstruct
    public void init() {
        renderABMPopup = false;
        crearFiltro();
        buscar();
    }

    public void crearFiltro() {
        filtro = new FiltroCausalCompraTO();
        filtro.setNombre("");
        filtro.setDescripcion("");
        filtro.setHabilitado(Boolean.TRUE);
        filtro.setOrganismo(this.getInicioMB().getOrganismo());
    }

    public void buscar() {
        causalesCompra = causalCompraDelegate.obtenerPorFiltro(filtro);
        renderResultado = true;
    }

    public void guardar() {
        try {
            CausalCompraValidacion.validar(causalCompra);

            causalCompraDelegate.guardar(causalCompra);
            this.cerrarPopup();
            this.buscar();
            JSFUtils.agregarMsgInfo("", Labels.getValue("cau_com_form_success"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (BusinessException ex) {
            logger.log(Level.FINE, ex.getMessage(), ex);
            logger.log(Level.INFO, ex.getMessage());
            for (String iterStr : ex.getErrores()) {
                JSFUtils.agregarMsgError("formCauCom", Labels.getValue(iterStr), null);
            }
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("formCauCom", Labels.getValue("cau_com_form_error_tecnico"), null);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("formCauCom", Labels.getValue("ERR_GRAL"), null);
        }
    }

    public void limpiar() {
        crearFiltro();
        causalesCompra = null;
        renderResultado = false;
    }

    public void agregar() {
        this.causalCompra = new CausalCompra();
        this.causalCompra.setCauComHabilitado(Boolean.TRUE);
        this.causalCompra.setCauComOrgFk(this.getInicioMB().getOrganismo());
        this.renderABMPopup = true;
    }

    public void editar(Integer cenCos) {
        this.causalCompra = causalCompraDelegate.obtenerPorId(cenCos);
        this.renderABMPopup = true;
    }

    public void eliminar(Integer cenCos) {
        try {
            causalCompraDelegate.eliminar(cenCos);
            this.buscar();
            JSFUtils.agregarMsgInfo("", Labels.getValue("cau_com_delete_success"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (BusinessException ex) {
            logger.log(Level.FINE, ex.getMessage(), ex);
            logger.log(Level.INFO, ex.getMessage());
            JSFUtils.agregarMsgError("", Labels.getValue("cau_com_delete_error_constraint"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("", Labels.getValue("cau_com_form_error_tecnico"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("", Labels.getValue("ERR_GRAL"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        }
    }

    public void cerrarPopup() {
        this.causalCompra = null;
        this.renderABMPopup = false;
    }

    public void causalCompraFiltroValueChange(ValueChangeEvent ev) {
        this.filtro.setHabilitado((Boolean) ev.getNewValue());
    }

    //<editor-fold defaultstate="collapsed" desc="getters-setters">
    public List<CausalCompra> getCausalesCompra() {
        return causalesCompra;
    }

    public void setCausalesCompra(List<CausalCompra> causalesCompra) {
        this.causalesCompra = causalesCompra;
    }

    public CausalCompra getCausalCompra() {
        return causalCompra;
    }

    public void setCausalCompra(CausalCompra causalCompra) {
        this.causalCompra = causalCompra;
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
    public FiltroCausalCompraTO getFiltro() {
        return filtro;
    }

    /**
     * @param filtro the filtro to set
     */
    public void setFiltro(FiltroCausalCompraTO filtro) {
        this.filtro = filtro;
    }

}
//</editor-fold>
