package com.sofis.web.mb;

import com.sofis.business.properties.LabelsEJB;
import com.sofis.entities.data.IdentificadorGrpErp;
import com.sofis.entities.tipos.FiltroIdentificadorGrpErpTO;
import com.sofis.entities.validations.IdentificadorGrpErpValidacion;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.web.delegates.IdentificadorGrpErpDelegate;
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
@ManagedBean(name = "identificadorGrpErpMB")
@ViewScoped
public class IdentificadorGrpErpMB implements Serializable {

    private static final Logger logger = Logger.getLogger(IdentificadorGrpErpMB.class.getName());

    private List<IdentificadorGrpErp> identificadores;
    private IdentificadorGrpErp identificador;
    private FiltroIdentificadorGrpErpTO filtro;

    private Boolean renderABMPopup;
    private Boolean renderResultado;

    @Inject
    private IdentificadorGrpErpDelegate identificadorGrpErpDelegate;

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;

    @PostConstruct
    public void init() {
        renderABMPopup = false;
        crearFiltro();
        buscar();
    }

    public void crearFiltro() {
        filtro = new FiltroIdentificadorGrpErpTO();
        filtro.setNombre("");
        filtro.setDescripcion("");
        filtro.setHabilitado(Boolean.TRUE);
        filtro.setOrganismo(this.getInicioMB().getOrganismo());
    }

    public void buscar() {
        identificadores = identificadorGrpErpDelegate.obtenerPorFiltro(filtro);
        renderResultado = true;
    }

    public void guardar() {
        try {
            IdentificadorGrpErpValidacion.validar(identificador);

            identificadorGrpErpDelegate.guardar(identificador);
            this.cerrarPopup();
            this.buscar();
            JSFUtils.agregarMsgInfo("", LabelsEJB.getValue("id_grp_erp_form_success"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (BusinessException ex) {
            logger.log(Level.FINE, ex.getMessage(), ex);
            logger.log(Level.INFO, ex.getMessage());
            for (String iterStr : ex.getErrores()) {
                JSFUtils.agregarMsgError("formIdGrpErpPopupPanel", Labels.getValue(iterStr), null);
            }
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("formIdGrpErpPopupPanel", LabelsEJB.getValue("id_grp_erp_form_error_tecnico"), null);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("formIdGrpErpPopupPanel", LabelsEJB.getValue("ERR_GRAL"), null);
        }
    }

    public void limpiar() {
        crearFiltro();
        identificadores = null;
        renderResultado = false;
    }

    public void agregar() {
        this.identificador = new IdentificadorGrpErp();
        this.identificador.setIdGrpErpHabilitado(Boolean.TRUE);
        this.identificador.setIdGrpErpOrgFk(this.getInicioMB().getOrganismo());
        this.renderABMPopup = true;
    }

    public void editar(Integer id) {
        this.identificador = identificadorGrpErpDelegate.obtenerPorId(id);
        this.renderABMPopup = true;
    }

    public void eliminar(Integer id) {
        try {
            identificadorGrpErpDelegate.eliminar(id);
            this.buscar();
            JSFUtils.agregarMsgInfo("", LabelsEJB.getValue("id_grp_erp_delete_success"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (BusinessException ex) {
            logger.log(Level.FINE, ex.getMessage(), ex);
            logger.log(Level.INFO, ex.getMessage());
            JSFUtils.agregarMsgError("", LabelsEJB.getValue("id_grp_erp_delete_error_constraint"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("", LabelsEJB.getValue("id_grp_erp_form_error_tecnico"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("", LabelsEJB.getValue("ERR_GRAL"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        }
    }

    public void cerrarPopup() {
        this.identificador = null;
        this.renderABMPopup = false;
    }

    public void identificadorGrpErpFiltroValueChange(ValueChangeEvent ev) {
        this.filtro.setHabilitado((Boolean) ev.getNewValue());
    } 

    public List<IdentificadorGrpErp> getIdentificadores() {
        return identificadores;
    }

    public void setIdentificadores(List<IdentificadorGrpErp> identificadores) {
        this.identificadores = identificadores;
    }

    public IdentificadorGrpErp getIdentificador() {
        return identificador;
    }

    public void setIdentificador(IdentificadorGrpErp identificador) {
        this.identificador = identificador;
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
    public FiltroIdentificadorGrpErpTO getFiltro() {
        return filtro;
    }

    /**
     * @param filtro the filtro to set
     */
    public void setFiltro(FiltroIdentificadorGrpErpTO filtro) {
        this.filtro = filtro;
    }

}
//</editor-fold>
