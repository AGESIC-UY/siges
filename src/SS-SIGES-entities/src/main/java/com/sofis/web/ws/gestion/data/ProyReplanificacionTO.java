/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.ws.gestion.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nico
 */
@XmlRootElement(name = "proyReplanificacion")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProyReplanificacionTO {
    @XmlElement(name = "proyreplanGenerarLineaBase", required = true)
    private Boolean proyreplanGenerarLineaBase;
    
//    @XmlElement(name = "proyreplanGenerarProducto", required = true)
//    private Boolean proyreplanGenerarProducto;
    @XmlElement(name = "proyreplanGenerarPresupuesto", required = true)
    private Boolean proyreplanGenerarPresupuesto;
    
    @XmlElement(name = "proyreplanPermitEditar", required = true)
    private boolean proyreplanPermitEditar;
    
    @XmlElement(name = "proyreplanHistorial", required = true)
    private Boolean proyreplanHistorial;
    @XmlElement(name = "proyDescripcion", required = true)
    private String proyreplanDesc;

    public Boolean getProyreplanGenerarLineaBase() {
        return proyreplanGenerarLineaBase;
    }

    public void setProyreplanGenerarLineaBase(Boolean proyreplanGenerarLineaBase) {
        this.proyreplanGenerarLineaBase = proyreplanGenerarLineaBase;
    }

    public Boolean getProyreplanHistorial() {
        return proyreplanHistorial;
    }

    public void setProyreplanHistorial(Boolean proyreplanHistorial) {
        this.proyreplanHistorial = proyreplanHistorial;
    }

    public String getProyreplanDesc() {
        return proyreplanDesc;
    }

    public void setProyreplanDesc(String proyreplanDesc) {
        this.proyreplanDesc = proyreplanDesc;
    }

//    public Boolean getProyreplanGenerarProducto() {
//        return proyreplanGenerarProducto;
//    }
//
//    public void setProyreplanGenerarProducto(Boolean proyreplanGenerarProducto) {
//        this.proyreplanGenerarProducto = proyreplanGenerarProducto;
//    }

    public Boolean getProyreplanGenerarPresupuesto() {
        return proyreplanGenerarPresupuesto;
    }

    public void setProyreplanGenerarPresupuesto(Boolean proyreplanGenerarPresupuesto) {
        this.proyreplanGenerarPresupuesto = proyreplanGenerarPresupuesto;
    }

    public boolean getProyreplanPermitEditar() {
        return proyreplanPermitEditar;
    }

    public void setProyreplanPermitEditar(boolean proyreplanPermitEditar) {
        this.proyreplanPermitEditar = proyreplanPermitEditar;
    }
    
    
    
    
}
