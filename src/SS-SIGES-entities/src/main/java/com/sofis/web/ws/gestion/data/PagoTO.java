/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.ws.gestion.data;

import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@XmlRootElement(name = "pago")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class PagoTO {

    

    @XmlElement(name = "pagObservacion", required = false)
    private String pagObservacion;
    @XmlElement(name = "pagFechaPlanificada", required = true)
    private String pagFechaPlanificada;
    @XmlElement(name = "pagImportePlanificado", required = true)
    private Double pagImportePlanificado;
    @XmlElement(name = "pagFechaReal", required = true)
    private String pagFechaReal;
    @XmlElement(name = "pagImporteReal", required = true)
    private Double pagImporteReal;
    @XmlElement(name = "pagTxtReferencia", required = false)
    private String pagTxtReferencia;
    @XmlElement(name = "pagConfirmar", required = false)
    private Boolean pagConfirmar;
    
    @XmlElement(name="attrs")
    private List<AttrTO> attrs;
    

    //<editor-fold defaultstate="collapsed" desc="getters-setters"> 
    
    /**
     * @return the attrs
     */
    public List<AttrTO> getAttrs() {
        return attrs;
    }

    /**
     * @param attrs the attrs to set
     */
    public void setAttrs(List<AttrTO> attrs) {
        this.attrs = attrs;
    }
    
    
    /**
     * @return the pagObservacion
     */
    public String getPagObservacion() {
        return pagObservacion;
    }

    /**
     * @param pagObservacion the pagObservacion to set
     */
    public void setPagObservacion(String pagObservacion) {
        this.pagObservacion = pagObservacion;
    }

    /**
     * @return the pagFechaPanificada
     */
    public String getPagFechaPanificada() {
        return pagFechaPlanificada;
    }

    /**
     * @param pagFechaPanificada the pagFechaPanificada to set
     */
    public void setPagFechaPanificada(String pagFechaPanificada) {
        this.pagFechaPlanificada = pagFechaPanificada;
    }

    /**
     * @return the pagImportePlanificado
     */
    public Double getPagImportePlanificado() {
        return pagImportePlanificado;
    }

    /**
     * @param pagImportePlanificado the pagImportePlanificado to set
     */
    public void setPagImportePlanificado(Double pagImportePlanificado) {
        this.pagImportePlanificado = pagImportePlanificado;
    }

    /**
     * @return the pagFechaReal
     */
    public String getPagFechaReal() {
        return pagFechaReal;
    }

    /**
     * @param pagFechaReal the pagFechaReal to set
     */
    public void setPagFechaReal(String pagFechaReal) {
        this.pagFechaReal = pagFechaReal;
    }

    /**
     * @return the pagImporteReal
     */
    public Double getPagImporteReal() {
        return pagImporteReal;
    }

    /**
     * @param pagImporteReal the pagImporteReal to set
     */
    public void setPagImporteReal(Double pagImporteReal) {
        this.pagImporteReal = pagImporteReal;
    }

    /**
     * @return the pagTxtReferencia
     */
    public String getPagTxtReferencia() {
        return pagTxtReferencia;
    }

    /**
     * @param pagTxtReferencia the pagTxtReferencia to set
     */
    public void setPagTxtReferencia(String pagTxtReferencia) {
        this.pagTxtReferencia = pagTxtReferencia;
    }

    /**
     * @return the pagConfirmar
     */
    public Boolean getPagConfirmar() {
        return pagConfirmar;
    }

    /**
     * @param pagConfirmar the pagConfirmar to set
     */
    public void setPagConfirmar(Boolean pagConfirmar) {
        this.pagConfirmar = pagConfirmar;
    }

    /**
     * @return the pagFechaPlanificada
     */
    public String getPagFechaPlanificada() {
        return pagFechaPlanificada;
    }

    /**
     * @param pagFechaPlanificada the pagFechaPlanificada to set
     */
    public void setPagFechaPlanificada(String pagFechaPlanificada) {
        this.pagFechaPlanificada = pagFechaPlanificada;
    }

}
//</editor-fold> 
