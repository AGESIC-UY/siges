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
@XmlRootElement(name = "proyecto")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProyectoTO {

    @XmlElement(name = "proyPk", required = false)
    private Integer proyPk;
    @XmlElement(name = "proyNombre", required = true)
    private String proyNombre;
    @XmlElement(name = "proyDescripcion", required = true)
    private String proyDescripcion;
    @XmlElement(name = "proyFactorImpacto", required = false)
    private String proyFactorImpacto;
    @XmlElement(name = "proyObjetivo", required = true)
    private String proyObjetivo;
    @XmlElement(name = "proyObjPublico", required = true)
    private String proyObjPublico;
    @XmlElement(name = "proySituacionActual", required = true)
    private String proySituacionActual;
    @XmlElement(name = "proyAreaFk", required = true)
    private Integer proyAreaFk;
    @XmlElement(name = "proyUsrPmofedFk", required = true)
    private Integer proyUsrPmofedFk;
    @XmlElement(name = "proyUsrGerenteFk", required = true)
    private Integer proyUsrGerenteFk;
    @XmlElement(name = "proyUsrSponsorFk", required = true)
    private Integer proyUsrSponsorFk;
    @XmlElement(name = "proyUsrAdjuntoFk", required = true)
    private Integer proyUsrAdjuntoFk;
    @XmlElement(name = "presupuesto", required = false)
    private PresupuestoTO presupuesto;
    @XmlElement(name = "cronograma", required = false)
    private CronogramaTO cronograma;

    @XmlElement(name = "attrs")
    private List<AttrTO> attrs;

    //<editor-fold defaultstate="collapsed" desc="getters-setters">
    
    
    /**
     * @return the proyPk
     */
    public Integer getProyPk() {
        return proyPk;
    }

    /**
     * @param proyPk the proyPk to set
     */
    public void setProyPk(Integer proyPk) {
        this.proyPk = proyPk;
    }

    /**
     * @return the proyNombre
     */
    public String getProyNombre() {
        return proyNombre;
    }

    /**
     * @param proyNombre the proyNombre to set
     */
    public void setProyNombre(String proyNombre) {
        this.proyNombre = proyNombre;
    }

    /**
     * @return the proyDescripcion
     */
    public String getProyDescripcion() {
        return proyDescripcion;
    }

    /**
     * @param proyDescripcion the proyDescripcion to set
     */
    public void setProyDescripcion(String proyDescripcion) {
        this.proyDescripcion = proyDescripcion;
    }

    /**
     * @return the proyObjetivo
     */
    public String getProyObjetivo() {
        return proyObjetivo;
    }

    /**
     * @param proyObjetivo the proyObjetivo to set
     */
    public void setProyObjetivo(String proyObjetivo) {
        this.proyObjetivo = proyObjetivo;
    }

    /**
     * @return the proyObjPublico
     */
    public String getProyObjPublico() {
        return proyObjPublico;
    }

    /**
     * @param proyObjPublico the proyObjPublico to set
     */
    public void setProyObjPublico(String proyObjPublico) {
        this.proyObjPublico = proyObjPublico;
    }

    /**
     * @return the proySituacionActual
     */
    public String getProySituacionActual() {
        return proySituacionActual;
    }

    /**
     * @param proySituacionActual the proySituacionActual to set
     */
    public void setProySituacionActual(String proySituacionActual) {
        this.proySituacionActual = proySituacionActual;
    }

    /**
     * @return the presupuesto
     */
    public PresupuestoTO getPresupuesto() {
        return presupuesto;
    }

    /**
     * @param presupuesto the presupuesto to set
     */
    public void setPresupuesto(PresupuestoTO presupuesto) {
        this.presupuesto = presupuesto;
    }

    /**
     * @return the cronograma
     */
    public CronogramaTO getCronograma() {
        return cronograma;
    }

    /**
     * @param cronograma the cronograma to set
     */
    public void setCronograma(CronogramaTO cronograma) {
        this.cronograma = cronograma;
    }

    /**
     * @return the proyFactorImpacto
     */
    public String getProyFactorImpacto() {
        return proyFactorImpacto;
    }

    /**
     * @param proyFactorImpacto the proyFactorImpacto to set
     */
    public void setProyFactorImpacto(String proyFactorImpacto) {
        this.proyFactorImpacto = proyFactorImpacto;
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
     * @return the areaFk
     */
    public Integer getAreaFk() {
        return proyAreaFk;
    }

    /**
     * @param areaFk the areaFk to set
     */
    public void setAreaFk(Integer areaFk) {
        this.proyAreaFk = areaFk;
    }

    /**
     * @return the proyUsrPmofedFk
     */
    public Integer getProyUsrPmofedFk() {
        return proyUsrPmofedFk;
    }

    /**
     * @param proyUsrPmofedFk the proyUsrPmofedFk to set
     */
    public void setProyUsrPmofedFk(Integer proyUsrPmofedFk) {
        this.proyUsrPmofedFk = proyUsrPmofedFk;
    }

    /**
     * @return the proyUsrGerenteFk
     */
    public Integer getProyUsrGerenteFk() {
        return proyUsrGerenteFk;
    }

    /**
     * @param proyUsrGerenteFk the proyUsrGerenteFk to set
     */
    public void setProyUsrGerenteFk(Integer proyUsrGerenteFk) {
        this.proyUsrGerenteFk = proyUsrGerenteFk;
    }

    /**
     * @return the proyUsrSponsorFk
     */
    public Integer getProyUsrSponsorFk() {
        return proyUsrSponsorFk;
    }

    /**
     * @param proyUsrSponsorFk the proyUsrSponsorFk to set
     */
    public void setProyUsrSponsorFk(Integer proyUsrSponsorFk) {
        this.proyUsrSponsorFk = proyUsrSponsorFk;
    }

    /**
     * @return the proyUsrAdjuntoFk
     */
    public Integer getProyUsrAdjuntoFk() {
        return proyUsrAdjuntoFk;
    }

    /**
     * @param proyUsrAdjuntoFk the proyUsrAdjuntoFk to set
     */
    public void setProyUsrAdjuntoFk(Integer proyUsrAdjuntoFk) {
        this.proyUsrAdjuntoFk = proyUsrAdjuntoFk;
    }

}
//</editor-fold>
