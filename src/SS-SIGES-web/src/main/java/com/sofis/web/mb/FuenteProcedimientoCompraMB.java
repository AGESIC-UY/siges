package com.sofis.web.mb;

import com.sofis.business.properties.LabelsEJB;
import com.sofis.entities.data.CausalCompra;
import com.sofis.entities.data.FuenteProcedimientoCompra;
import com.sofis.entities.tipos.FiltroCausalCompraTO;
import com.sofis.entities.tipos.FiltroFuenteProcedimientoCompraTO;
import com.sofis.entities.validations.FuenteProcedimientoCompraValidacion;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.web.delegates.CausalCompraDelegate;
import com.sofis.web.delegates.FuenteProcedimientoCompraDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisComboG;
import java.io.Serializable;
import java.util.ArrayList;
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
@ManagedBean(name = "fuenteProcedimientoCompraMB")
@ViewScoped
public class FuenteProcedimientoCompraMB implements Serializable {

    private static final Logger logger = Logger.getLogger(FuenteProcedimientoCompraMB.class.getName());

    private List<FuenteProcedimientoCompra> fuentesProcedimientoCompra;
    private FuenteProcedimientoCompra fuenteProcedimientoCompra;
    private FiltroFuenteProcedimientoCompraTO filtro;
    private List<SofisComboG<CausalCompra>> listaCausalesCompraSelected = new ArrayList<>();
    private Boolean renderABMPopup;
    private Boolean renderResultado;

    @Inject
    private FuenteProcedimientoCompraDelegate fuenteProcedimientoCompraDelegate;
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
        filtro = new FiltroFuenteProcedimientoCompraTO();
        filtro.setFuente("");
        filtro.setProcedimientoCompra("");
        filtro.setHabilitado(Boolean.TRUE);
        filtro.setOrganismo(this.getInicioMB().getOrganismo());
    }

    public void buscar() {
        fuentesProcedimientoCompra = fuenteProcedimientoCompraDelegate.obtenerPorFiltro(filtro);
        renderResultado = true;
    }

    public void guardar() {
        try {
            fuenteProcedimientoCompra.setFueProComCausalesCompra(convertirSofisComboListACausalCompra(listaCausalesCompraSelected));
            FuenteProcedimientoCompraValidacion.validar(fuenteProcedimientoCompra);
            // convertir los SofisComboG a lista
            fuenteProcedimientoCompraDelegate.guardar(fuenteProcedimientoCompra);
            this.cerrarPopup();
            this.buscar();
            JSFUtils.agregarMsgInfo("", LabelsEJB.getValue("fue_proc_comp_form_success"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (BusinessException ex) {
            logger.log(Level.FINE, ex.getMessage(), ex);
            logger.log(Level.INFO, ex.getMessage());
            for (String iterStr : ex.getErrores()) {
                JSFUtils.agregarMsgError("formFueProCom", Labels.getValue(iterStr), null);
            }
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("formFueProCom", LabelsEJB.getValue("fue_proc_comp_form_error_tecnico"), null);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("formFueProCom", LabelsEJB.getValue("ERR_GRAL"), null);
        }
    }

    public void limpiar() {
        crearFiltro();
        fuentesProcedimientoCompra = null;
        renderResultado = false;
    }

    public void agregar() {
        this.fuenteProcedimientoCompra = new FuenteProcedimientoCompra();
        this.fuenteProcedimientoCompra.setFueProComHabilitado(Boolean.TRUE);
        this.fuenteProcedimientoCompra.setFueProComOrgFk(this.getInicioMB().getOrganismo());
        cargarCausales();
        this.renderABMPopup = true;
    }

    public void editar(Integer cenCos) {
        this.fuenteProcedimientoCompra = fuenteProcedimientoCompraDelegate.obtenerPorId(cenCos);
        cargarCausales();
        this.renderABMPopup = true;
    }

    public void eliminar(Integer cenCos) {
        try {
            fuenteProcedimientoCompraDelegate.eliminar(cenCos);
            this.buscar();
            JSFUtils.agregarMsgInfo("", LabelsEJB.getValue("fue_proc_comp_delete_success"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (BusinessException ex) {
            logger.log(Level.FINE, ex.getMessage(), ex);
            logger.log(Level.INFO, ex.getMessage());
            JSFUtils.agregarMsgError("", LabelsEJB.getValue("fue_proc_comp_delete_error_constraint"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("", LabelsEJB.getValue("fue_proc_comp_delete_error_tecnico"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("", LabelsEJB.getValue("ERR_GRAL"), null);
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        }
    }

    public void cerrarPopup() {
        this.fuenteProcedimientoCompra = null;
        this.renderABMPopup = false;
    }

    private void cargarCausales() {
        // cargo lista centro costo
        listaCausalesCompraSelected = new ArrayList<>();
        List<CausalCompra> causalesCompra = this.obtenerCausales();

        // cargo los causales actuales en el SofisComboG con lista de los causales compra en el sistema (causalesCompra)
        if (fuenteProcedimientoCompra != null) {
            if (fuenteProcedimientoCompra.getFueProComPk() == null) {
                // creo un causal cuando agrega (agregar)
                SofisComboG<CausalCompra> cauCombo = new SofisComboG<>(causalesCompra, "cauComNombre");
                cauCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
                listaCausalesCompraSelected.add(cauCombo);
            } else { // utilizo los ya existentes en la entidad si los tiene (editar)
                List<CausalCompra> causalesActuales = fuenteProcedimientoCompra.getFueProComCausalesCompra();
                if (causalesActuales != null) {
                    for (CausalCompra cauCom : causalesActuales) {
                        SofisComboG<CausalCompra> cauCombo = new SofisComboG<>(causalesCompra, "cauComNombre");
                        cauCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
                        cauCombo.setSelectedT(cauCom);
                        listaCausalesCompraSelected.add(cauCombo);
                    }
                }
            }
        }
    }

    public String addRowCausalesCompra() {
        SofisComboG<CausalCompra> cauCombo = new SofisComboG<>(this.obtenerCausales(), "cauComNombre");
        cauCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        listaCausalesCompraSelected.add(cauCombo);
        return null;
    }
    
    public String eliminarPropAction(int i) {
        listaCausalesCompraSelected.remove(i);
        return null;
    }

    private List<CausalCompra> obtenerCausales() {
        FiltroCausalCompraTO filtroCausalCompra = new FiltroCausalCompraTO();
        filtroCausalCompra.setHabilitado(Boolean.TRUE);
        filtroCausalCompra.setOrganismo(this.getInicioMB().getOrganismo());
        return causalCompraDelegate.obtenerPorFiltro(filtroCausalCompra);
    }
    
    private List<CausalCompra> convertirSofisComboListACausalCompra(List<SofisComboG<CausalCompra>> comboList) {
        List<CausalCompra> listaTipoEst = new ArrayList<>();
        if (comboList == null || comboList.size() == 0) {
            return listaTipoEst;
        }
        for (SofisComboG<CausalCompra> combo : comboList) {
            if (combo.getSelectedT() instanceof CausalCompra) {
                listaTipoEst.add((CausalCompra) combo.getSelectedT());
            }
        }
        return listaTipoEst;
    }

    public void fuenteProcedimientoCompraFiltroValueChange(ValueChangeEvent ev) {
        this.filtro.setHabilitado((Boolean) ev.getNewValue());
    }

    //<editor-fold defaultstate="collapsed" desc="getters-setters">
    public List<FuenteProcedimientoCompra> getFuentesProcedimientoCompra() {
        return fuentesProcedimientoCompra;
    }

    public void setFuentesProcedimientoCompra(List<FuenteProcedimientoCompra> fuentesProcedimientoCompra) {
        this.fuentesProcedimientoCompra = fuentesProcedimientoCompra;
    }

    public FuenteProcedimientoCompra getFuenteProcedimientoCompra() {
        return fuenteProcedimientoCompra;
    }

    public void setFuenteProcedimientoCompra(FuenteProcedimientoCompra fuenteProcedimientoCompra) {
        this.fuenteProcedimientoCompra = fuenteProcedimientoCompra;
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
    public FiltroFuenteProcedimientoCompraTO getFiltro() {
        return filtro;
    }

    /**
     * @param filtro the filtro to set
     */
    public void setFiltro(FiltroFuenteProcedimientoCompraTO filtro) {
        this.filtro = filtro;
    }

    public List<SofisComboG<CausalCompra>> getListaCausalesCompraSelected() {
        return listaCausalesCompraSelected;
    }

    public void setListaCausalesCompraSelected(List<SofisComboG<CausalCompra>> listaCausalesCompraSelected) {
        this.listaCausalesCompraSelected = listaCausalesCompraSelected;
    }

}
//</editor-fold>
