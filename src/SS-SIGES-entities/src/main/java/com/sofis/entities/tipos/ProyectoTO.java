package com.sofis.entities.tipos;

import java.io.Serializable;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class ProyectoTO implements Serializable{

    private Integer proyPk;
    private String proyDescripcion;
    private String proyObjetivo;
    /**
     * Se cambia y pasa a ser Beneficio/Beneficiario.
     */
    private String proyObjPublico;
    private String proyFactorImpacto;
    private String proySituacionActual;
    private String proyNombre;

    public ProyectoTO() {
    }

    public ProyectoTO(Integer proyPk, String proyDescripcion, String proyNombre) {
        this.proyPk = proyPk;
        this.proyDescripcion = proyDescripcion;
        this.proyNombre = proyNombre;
    }

    public Integer getProyPk() {
        return proyPk;
    }

    public void setProyPk(Integer proyPk) {
        this.proyPk = proyPk;
    }

    public String getProyDescripcion() {
        return proyDescripcion;
    }

    public void setProyDescripcion(String proyDescripcion) {
        this.proyDescripcion = proyDescripcion;
    }

    public String getProyObjetivo() {
        return proyObjetivo;
    }

    public void setProyObjetivo(String proyObjetivo) {
        this.proyObjetivo = proyObjetivo;
    }

    public String getProyObjPublico() {
        return proyObjPublico;
    }

    public void setProyObjPublico(String proyObjPublico) {
        this.proyObjPublico = proyObjPublico;
    }

    public String getProyFactorImpacto() {
	return proyFactorImpacto;
    }

    public void setProyFactorImpacto(String proyFactorImpacto) {
	this.proyFactorImpacto = proyFactorImpacto;
    }

    public String getProySituacionActual() {
        return proySituacionActual;
    }

    public void setProySituacionActual(String proySituacionActual) {
        this.proySituacionActual = proySituacionActual;
    }

    public String getProyNombre() {
        return proyNombre;
    }

    public void setProyNombre(String proyNombre) {
        this.proyNombre = proyNombre;
    }
}
