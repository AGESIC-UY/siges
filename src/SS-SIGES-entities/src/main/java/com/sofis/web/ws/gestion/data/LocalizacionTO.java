/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.ws.gestion.data;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@XmlRootElement(name = "localizacion")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class LocalizacionTO {
    @XmlElement(name = "latlngLat", required = true)
    private BigDecimal latlngLat;
    @XmlElement(name = "latlngLng", required = true)
    private BigDecimal latlngLng;
    @XmlElement(name = "latlangLoc", required = false)
    private String latlangLoc;
    @XmlElement(name = "latlangBarrio", required = false)
    private String latlangBarrio;
    @XmlElement(name = "latlangDepFk", required = false)
    private Integer latlangDepFk;

    public LocalizacionTO(){
    }
    
    public BigDecimal getLatlngLat() {
        return latlngLat;
    }

    public void setLatlngLat(BigDecimal latlngLat) {
        this.latlngLat = latlngLat;
    }

    public BigDecimal getLatlngLng() {
        return latlngLng;
    }

    public void setLatlngLng(BigDecimal latlngLng) {
        this.latlngLng = latlngLng;
    }

    public String getLatlangLoc() {
        return latlangLoc;
    }

    public void setLatlangLoc(String latlangLoc) {
        this.latlangLoc = latlangLoc;
    }

    public String getLatlangBarrio() {
        return latlangBarrio;
    }

    public void setLatlangBarrio(String latlangBarrio) {
        this.latlangBarrio = latlangBarrio;
    }

    public Integer getLatlangDepFk() {
        return latlangDepFk;
    }

    public void setLatlangDepFk(Integer latlangDepFk) {
        this.latlangDepFk = latlangDepFk;
    }

    
}
