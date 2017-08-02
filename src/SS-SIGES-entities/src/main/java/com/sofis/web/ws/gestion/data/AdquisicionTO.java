/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.ws.gestion.data;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@XmlRootElement(name = "adquisicion")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AdquisicionTO {

    @XmlElement(name = "adqNombre", required = true)
    private String adqNombre;
    @XmlElement(name = "adqMoneda", required = true)
    private Integer adqMoneda;
    @XmlElement(name = "adqFuente", required = true)
    private Integer adqFuente;
    @XmlElement(name = "adqProcCompra", required = false)
    private String adqProcCompra;
    @XmlElement(name = "adqProcCompraGrp", required = false)
    private String adqProcCompraGrp;
    @XmlElement(name = "pagosSet", required = false)
    private List<PagoTO> pagosSet;

    @XmlElement(name = "attrs")
    private List<AttrTO> attrs;

    //<editor-fold defaultstate="collapsed" desc="getters-setters"> 
    
    
    /**
     * @return the adqNombre
     */
    public String getAdqNombre() {
        return adqNombre;
    }

    /**
     * @param adqNombre the adqNombre to set
     */
    public void setAdqNombre(String adqNombre) {
        this.adqNombre = adqNombre;
    }

    /**
     * @return the adqProcCompra
     */
    public String getAdqProcCompra() {
        return adqProcCompra;
    }

    /**
     * @param adqProcCompra the adqProcCompra to set
     */
    public void setAdqProcCompra(String adqProcCompra) {
        this.adqProcCompra = adqProcCompra;
    }

    /**
     * @return the pagosSet
     */
    public List<PagoTO> getPagosSet() {
        return pagosSet;
    }

    /**
     * @param pagosSet the pagosSet to set
     */
    public void setPagosSet(List<PagoTO> pagosSet) {
        this.pagosSet = pagosSet;
    }

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
     * @return the adqMoneda
     */
    public Integer getAdqMoneda() {
        return adqMoneda;
    }

    /**
     * @param adqMoneda the adqMoneda to set
     */
    public void setAdqMoneda(Integer adqMoneda) {
        this.adqMoneda = adqMoneda;
    }

    /**
     * @return the adqFuente
     */
    public Integer getAdqFuente() {
        return adqFuente;
    }

    /**
     * @param adqFuente the adqFuente to set
     */
    public void setAdqFuente(Integer adqFuente) {
        this.adqFuente = adqFuente;
    }

    /**
     * @return the adqProcCompraGrp
     */
    public String getAdqProcCompraGrp() {
        return adqProcCompraGrp;
    }

    /**
     * @param adqProcCompraGrp the adqProcCompraGrp to set
     */
    public void setAdqProcCompraGrp(String adqProcCompraGrp) {
        this.adqProcCompraGrp = adqProcCompraGrp;
    }

}
//</editor-fold> 
