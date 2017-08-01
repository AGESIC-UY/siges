/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.mb;

import com.sofis.business.properties.LabelsEJB;
import com.sofis.entities.data.ObjetivoEstrategico;
import com.sofis.entities.tipos.FiltroObjectivoEstategicoTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.web.delegates.ObjetivoEstrategicoDelegate;
import com.sofis.web.utils.JSFUtils;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@ManagedBean(name = "objetivoEstrategicoMB")
@ViewScoped
public class ObjetivoEstrategicoMB implements Serializable{

    private static final Logger logger = Logger.getLogger(ObjetivoEstrategicoMB.class.getName());

    private List<ObjetivoEstrategico> objetivosEstrategicos;
    private ObjetivoEstrategico obj;
    private FiltroObjectivoEstategicoTO filtro;

    private Boolean renderABMPopup;
    private Boolean renderResultado;

    @Inject
    private ObjetivoEstrategicoDelegate objetivoEstrategicoDelegate;

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;

    @PostConstruct
    public void init() {
        renderABMPopup = false;
        crearFiltro();
        buscar();
    }

    public void crearFiltro() {
        filtro = new FiltroObjectivoEstategicoTO();
        filtro.setOrganismo(inicioMB.getOrganismo());
        filtro.setNombre("");
    }

    public void buscar() {
        objetivosEstrategicos = objetivoEstrategicoDelegate.obtenerPorFiltro(filtro);
        renderResultado = true;
    }

    public void guardar() {
        try {
            objetivoEstrategicoDelegate.guardar(obj);
            this.cerrarPopup();
            this.buscar();
            JSFUtils.agregarMsgInfo(LabelsEJB.getValue("obj_form_success"));
        } catch (BusinessException ex) {
            logger.log(Level.FINE, ex.getMessage(), ex);
            logger.log(Level.INFO, ex.getMessage());
            JSFUtils.agregarMsgError("objEstPopupMsg", LabelsEJB.getValue("obj_form_error_constraint"), null);
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("objEstPopupMsg", LabelsEJB.getValue("obj_form_error_tecnico"), null);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError("objEstPopupMsg", LabelsEJB.getValue("ERR_GRAL"), null);
        }
    }

    public void limpiar() {
        crearFiltro();
        objetivosEstrategicos = null;
        renderResultado = false;
    }

    public void agregar() {
        this.obj = new ObjetivoEstrategico();
        obj.setObjEstOrgFk(inicioMB.getOrganismo());
        this.renderABMPopup = true;
    }

    public void editar(Integer objEstPk) {
        this.obj = objetivoEstrategicoDelegate.obtenerPorId(objEstPk);
        this.renderABMPopup = true;
    }

    public void eliminar(Integer objEstPk) {
        try {
            objetivoEstrategicoDelegate.eliminar(objEstPk);
            this.buscar();
            JSFUtils.agregarMsgInfo(LabelsEJB.getValue("obj_delete_success"));
        } catch (BusinessException ex) {
            logger.log(Level.FINE, ex.getMessage(), ex);
            logger.log(Level.INFO, ex.getMessage());
            JSFUtils.agregarMsgError(LabelsEJB.getValue("obj_delete_error_constraint"));
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError(LabelsEJB.getValue("obj_delete_error_tecnico"));
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgError(LabelsEJB.getValue("ERR_GRAL"));
        }
    }

    public void cerrarPopup() {
        this.obj = null;
        this.renderABMPopup = false;
    }

    //<editor-fold defaultstate="collapsed" desc="getters-setters">
    /**
     * @return the objetivosEstrategicos
     */
    public List<ObjetivoEstrategico> getObjetivosEstrategicos() {
        return objetivosEstrategicos;
    }

    /**
     * @param objetivosEstrategicos the objetivosEstrategicos to set
     */
    public void setObjetivosEstrategicos(List<ObjetivoEstrategico> objetivosEstrategicos) {
        this.objetivosEstrategicos = objetivosEstrategicos;
    }

    /**
     * @return the obj
     */
    public ObjetivoEstrategico getObj() {
        return obj;
    }

    /**
     * @param obj the obj to set
     */
    public void setObj(ObjetivoEstrategico obj) {
        this.obj = obj;
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
    public FiltroObjectivoEstategicoTO getFiltro() {
        return filtro;
    }

    /**
     * @param filtro the filtro to set
     */
    public void setFiltro(FiltroObjectivoEstategicoTO filtro) {
        this.filtro = filtro;
    }

    /**
     * @return the objetivoEstrategicoDelegate
     */
    public ObjetivoEstrategicoDelegate getObjetivoEstrategicoDelegate() {
        return objetivoEstrategicoDelegate;
    }

    /**
     * @param objetivoEstrategicoDelegate the objetivoEstrategicoDelegate to set
     */
    public void setObjetivoEstrategicoDelegate(ObjetivoEstrategicoDelegate objetivoEstrategicoDelegate) {
        this.objetivoEstrategicoDelegate = objetivoEstrategicoDelegate;
    }
}
//</editor-fold>
