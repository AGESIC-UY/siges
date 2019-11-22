package com.sofis.web.mb;

import com.sofis.business.properties.LabelsEJB;
import com.sofis.entities.data.CentroCosto;
import com.sofis.entities.tipos.FiltroCentroCostoTO;
import com.sofis.entities.validations.CentroCostoValidacion;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.web.delegates.CentroCostoDelegate;
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
@ManagedBean(name = "centroCostoMB")
@ViewScoped
public class CentroCostoMB implements Serializable {

    private static final Logger logger = Logger.getLogger(CentroCostoMB.class.getName());

    private List<CentroCosto> centrosCosto;
    private CentroCosto centroCosto;
    private FiltroCentroCostoTO filtro;

    private Boolean renderABMPopup;
    private Boolean renderResultado;

    @Inject
    private CentroCostoDelegate tipoAdquisicionDelegate;

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;

    @PostConstruct
    public void init() {
        renderABMPopup = false;
        crearFiltro();
        buscar();
    }

    public void crearFiltro() {
        filtro = new FiltroCentroCostoTO();
        filtro.setNombre("");
        filtro.setDescripcion("");
        filtro.setHabilitado(Boolean.TRUE);
        filtro.setOrganismo(this.getInicioMB().getOrganismo());
    }

    public void buscar() {
        centrosCosto = tipoAdquisicionDelegate.obtenerPorFiltro(filtro);
        renderResultado = true;
    }

    public void guardar() {
        try {
            CentroCostoValidacion.validar(centroCosto);

            tipoAdquisicionDelegate.guardar(centroCosto);
            this.cerrarPopup();
            this.buscar();
            JSFUtils.agregarMsgInfo("", LabelsEJB.getValue("cen_cos_form_success"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (BusinessException ex) {
            logger.log(Level.FINE, ex.getMessage(), ex);
            logger.log(Level.INFO, ex.getMessage());
            for (String iterStr : ex.getErrores()) {
                JSFUtils.agregarMsgError("formCenCos", Labels.getValue(iterStr), null);
            }
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("formCenCos", LabelsEJB.getValue("cen_cos_form_error_tecnico"), null);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("formCenCos", LabelsEJB.getValue("ERR_GRAL"), null);
        }
    }

    public void limpiar() {
        crearFiltro();
        centrosCosto = null;
        renderResultado = false;
    }

    public void agregar() {
        this.centroCosto = new CentroCosto();
        this.centroCosto.setCenCosOrgFk(this.getInicioMB().getOrganismo());
        this.centroCosto.setCenCosHabilitado(Boolean.TRUE);
        this.renderABMPopup = true;
    }

    public void editar(Integer cenCos) {
        this.centroCosto = tipoAdquisicionDelegate.obtenerPorId(cenCos);
        this.renderABMPopup = true;
    }

    public void eliminar(Integer cenCos) {
        try {
            tipoAdquisicionDelegate.eliminar(cenCos);
            this.buscar();
            JSFUtils.agregarMsgInfo("", LabelsEJB.getValue("cen_cos_delete_success"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (BusinessException ex) {
            logger.log(Level.FINE, ex.getMessage(), ex);
            logger.log(Level.INFO, ex.getMessage());
            JSFUtils.agregarMsgError("", LabelsEJB.getValue("cen_cos_delete_error_constraint"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("", LabelsEJB.getValue("cen_cos_delete_error_tecnico"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("", LabelsEJB.getValue("ERR_GRAL"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        }
    }

    public void cerrarPopup() {
        this.centroCosto = null;
        this.renderABMPopup = false;
    }

    public void centroCostoFiltroValueChange(ValueChangeEvent ev) {
        this.filtro.setHabilitado((Boolean) ev.getNewValue());
    }

    //<editor-fold defaultstate="collapsed" desc="getters-setters">
    public List<CentroCosto> getCentrosCosto() {
        return centrosCosto;
    }

    public void setCentrosCosto(List<CentroCosto> centrosCosto) {
        this.centrosCosto = centrosCosto;
    }

    public CentroCosto getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(CentroCosto centroCosto) {
        this.centroCosto = centroCosto;
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
    public FiltroCentroCostoTO getFiltro() {
        return filtro;
    }

    /**
     * @param filtro the filtro to set
     */
    public void setFiltro(FiltroCentroCostoTO filtro) {
        this.filtro = filtro;
    }

}
//</editor-fold>
