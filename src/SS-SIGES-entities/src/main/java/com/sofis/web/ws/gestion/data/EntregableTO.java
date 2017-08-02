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
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class EntregableTO {

    @XmlElement (name = "entId", required = true)
    private Integer entId;
    @XmlElement (name = "esHito", required = true)
    private Boolean esHito;
    @XmlElement (name = "entNombre", required = true)
    private String entNombre;
    @XmlElement (name = "entNivel", required = true)
    private Integer entNivel;
    @XmlElement (name = "entStatus", required = true)
    private String entStatus;
    @XmlElement (name = "entParent", required = true)
    private Boolean entParent; //se puede calcular
    @XmlElement (name = "entInicio", required = true)
    private Long entInicio;
    @XmlElement (name = "entFin", required = true)
    private Long entFin;
    @XmlElement (name = "entHorasEstimadas", required = false)
    private Integer entHorasEstimadas;
    @XmlElement (name = "entEsfuerzo", required = true)
    private Integer entEsfuerzo;
    @XmlElement (name = "entProgreso", required = true)
    private Integer entProgreso;
    @XmlElement (name = "entInicioLineaBase", required = false)
    private Long entInicioLineaBase;
    @XmlElement (name = "entFinLineaBase", required = false)
    private Long entFinLineaBase;
    @XmlElement (name = "entParentFk", required = false)
    private Integer entParentFk; //se puede calcular
    @XmlElement (name = "inicioPeriodo", required = true)
    private Boolean inicioPeriodo;
    @XmlElement (name = "finPeriodo", required = true)
    private Boolean finPeriodo;
    @XmlElement (name = "entPredecesorFk", required = false)
    private String entPredecesorFk;
    @XmlElement (name = "entPredecesorDias", required = false)
    private Integer entPredecesorDias;
    @XmlElement(name="attrs")
    private List<AttrTO> attrs;
    
    
    //<editor-fold defaultstate="collapsed" desc="getters-setters"> 
    
    /**
     * @return the entId
     */
    public Integer getEntId() {
        return entId;
    }

    /**
     * @param entId the entId to set
     */
    public void setEntId(Integer entId) {
        this.entId = entId;
    }

    /**
     * @return the esHito
     */
    public Boolean getEsHito() {
        return esHito;
    }

    /**
     * @param esHito the esHito to set
     */
    public void setEsHito(Boolean esHito) {
        this.esHito = esHito;
    }

    /**
     * @return the entNombre
     */
    public String getEntNombre() {
        return entNombre;
    }

    /**
     * @param entNombre the entNombre to set
     */
    public void setEntNombre(String entNombre) {
        this.entNombre = entNombre;
    }

    /**
     * @return the entNivel
     */
    public Integer getEntNivel() {
        return entNivel;
    }

    /**
     * @param entNivel the entNivel to set
     */
    public void setEntNivel(Integer entNivel) {
        this.entNivel = entNivel;
    }

    /**
     * @return the entStatus
     */
    public String getEntStatus() {
        return entStatus;
    }

    /**
     * @param entStatus the entStatus to set
     */
    public void setEntStatus(String entStatus) {
        this.entStatus = entStatus;
    }

    /**
     * @return the entParent
     */
    public Boolean getEntParent() {
        return entParent;
    }

    /**
     * @param entParent the entParent to set
     */
    public void setEntParent(Boolean entParent) {
        this.entParent = entParent;
    }

    /**
     * @return the entInicio
     */
    public Long getEntInicio() {
        return entInicio;
    }

    /**
     * @param entInicio the entInicio to set
     */
    public void setEntInicio(Long entInicio) {
        this.entInicio = entInicio;
    }

    /**
     * @return the entFin
     */
    public Long getEntFin() {
        return entFin;
    }

    /**
     * @param entFin the entFin to set
     */
    public void setEntFin(Long entFin) {
        this.entFin = entFin;
    }

    /**
     * @return the entHorasEstimadas
     */
    public Integer getEntHorasEstimadas() {
        return entHorasEstimadas;
    }

    /**
     * @param entHorasEstimadas the entHorasEstimadas to set
     */
    public void setEntHorasEstimadas(Integer entHorasEstimadas) {
        this.entHorasEstimadas = entHorasEstimadas;
    }

    /**
     * @return the entEsfuerzo
     */
    public Integer getEntEsfuerzo() {
        return entEsfuerzo;
    }

    /**
     * @param entEsfuerzo the entEsfuerzo to set
     */
    public void setEntEsfuerzo(Integer entEsfuerzo) {
        this.entEsfuerzo = entEsfuerzo;
    }

    /**
     * @return the entProgreso
     */
    public Integer getEntProgreso() {
        return entProgreso;
    }

    /**
     * @param entProgreso the entProgreso to set
     */
    public void setEntProgreso(Integer entProgreso) {
        this.entProgreso = entProgreso;
    }

    /**
     * @return the entInicioLineaBase
     */
    public Long getEntInicioLineaBase() {
        return entInicioLineaBase;
    }

    /**
     * @param entInicioLineaBase the entInicioLineaBase to set
     */
    public void setEntInicioLineaBase(Long entInicioLineaBase) {
        this.entInicioLineaBase = entInicioLineaBase;
    }

    /**
     * @return the entFinLineaBase
     */
    public Long getEntFinLineaBase() {
        return entFinLineaBase;
    }

    /**
     * @param entFinLineaBase the entFinLineaBase to set
     */
    public void setEntFinLineaBase(Long entFinLineaBase) {
        this.entFinLineaBase = entFinLineaBase;
    }

    /**
     * @return the entParentFk
     */
    public Integer getEntParentFk() {
        return entParentFk;
    }

    /**
     * @param entParentFk the entParentFk to set
     */
    public void setEntParentFk(Integer entParentFk) {
        this.entParentFk = entParentFk;
    }

    /**
     * @return the inicioPeriodo
     */
    public Boolean getInicioPeriodo() {
        return inicioPeriodo;
    }

    /**
     * @param inicioPeriodo the inicioPeriodo to set
     */
    public void setInicioPeriodo(Boolean inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    /**
     * @return the finPeriodo
     */
    public Boolean getFinPeriodo() {
        return finPeriodo;
    }

    /**
     * @param finPeriodo the finPeriodo to set
     */
    public void setFinPeriodo(Boolean finPeriodo) {
        this.finPeriodo = finPeriodo;
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
     * @return the entPredecesorFk
     */
    public String getEntPredecesorFk() {
        return entPredecesorFk;
    }

    /**
     * @param entPredecesorFk the entPredecesorFk to set
     */
    public void setEntPredecesorFk(String entPredecesorFk) {
        this.entPredecesorFk = entPredecesorFk;
    }

    /**
     * @return the entPredecesorDias
     */
    public Integer getEntPredecesorDias() {
        return entPredecesorDias;
    }

    /**
     * @param entPredecesorDias the entPredecesorDias to set
     */
    public void setEntPredecesorDias(Integer entPredecesorDias) {
        this.entPredecesorDias = entPredecesorDias;
    }
    
}
//</editor-fold>