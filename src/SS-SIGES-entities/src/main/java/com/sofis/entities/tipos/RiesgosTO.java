package com.sofis.entities.tipos;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RiesgosTO implements Serializable {

    private static final long serialVersionUID = 1L;

        private Integer proyPk;
	private Integer proyecto;
	private String nombreProyecto;
        private String nombreRiesgo;
        private String tipoRiesgo;
        private Date fechaAct;
        private Integer probabilidad;
        private Integer impacto;
        private Double exposicion;
        private String estado;
        private String responsable;
        private Date fechaLimite;
        private String observaciones;
        private String colorExposicion;
        private String colorFechaLimite;


    public Integer getProyecto() {
        return proyecto;
    }

    public void setProyecto(Integer proyecto) {
        this.proyecto = proyecto;
    }

    public String getColorExposicion() {
        return colorExposicion;
    }

    public void setColorExposicion(String colorExposicion) {
        this.colorExposicion = colorExposicion;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getNombreRiesgo() {
        return nombreRiesgo;
    }

    public void setNombreRiesgo(String nombreRiesgo) {
        this.nombreRiesgo = nombreRiesgo;
    }

    public String getTipoRiesgo() {
        return tipoRiesgo;
    }

    public void setTipoRiesgo(String tipoRiesgo) {
        this.tipoRiesgo = tipoRiesgo;
    }

    public String getColorFechaLimite() {
        return colorFechaLimite;
    }

    public void setColorFechaLimite(String colorFechaLimite) {
        this.colorFechaLimite = colorFechaLimite;
    }

    public Date getFechaAct() {
        return fechaAct;
    }

    public void setFechaAct(Date fechaAct) {
        this.fechaAct = fechaAct;
    }

    public Integer getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(Integer probabilidad) {
        this.probabilidad = probabilidad;
    }

    public Integer getImpacto() {
        return impacto;
    }

    public void setImpacto(Integer impacto) {
        this.impacto = impacto;
    }

    public Double getExposicion() {
        return exposicion;
    }

    public void setExposicion(Double exposicion) {
        this.exposicion = exposicion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getProyPk() {
        return proyPk;
    }

    public void setProyPk(Integer proyPk) {
        this.proyPk = proyPk;
    }
	
	
}
