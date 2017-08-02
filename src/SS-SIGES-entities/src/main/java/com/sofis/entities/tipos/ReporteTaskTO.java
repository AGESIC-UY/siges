package com.sofis.entities.tipos;

import java.io.Serializable;
import java.util.Date;

/**
 * TO utilizado para representar una tarea en el reporte de Gantt.
 *
 * @author MG
 */
public class ReporteTaskTO implements Serializable{

    private String proyDesc;
    private Date inicio;
    private Date fin;
    private int duracion;
    private ReporteTaskGrafTO grafFinalizado;
    private ReporteTaskGrafTO grafParcial;
    private Date inicioLineaBase;
    private Date finLineaBase;
    private int duracionLineaBase;

    public ReporteTaskTO() {
    }

    public String getProyDesc() {
	return proyDesc;
    }

    public void setProyDesc(String proyDesc) {
	this.proyDesc = proyDesc;
    }

    public Date getInicio() {
	return inicio;
    }

    public void setInicio(Date inicio) {
	this.inicio = inicio;
    }

    public Date getFin() {
	return fin;
    }

    public void setFin(Date fin) {
	this.fin = fin;
    }

    public int getDuracion() {
	return duracion;
    }

    public void setDuracion(int duracion) {
	this.duracion = duracion;
    }

    public ReporteTaskGrafTO getGrafFinalizado() {
	return grafFinalizado;
    }

    public void setGrafFinalizado(ReporteTaskGrafTO grafFinalizado) {
	this.grafFinalizado = grafFinalizado;
    }

    public ReporteTaskGrafTO getGrafParcial() {
	return grafParcial;
    }

    public void setGrafParcial(ReporteTaskGrafTO grafParcial) {
	this.grafParcial = grafParcial;
    }

    public Date getInicioLineaBase() {
	return inicioLineaBase;
    }

    public void setInicioLineaBase(Date inicioLineaBase) {
	this.inicioLineaBase = inicioLineaBase;
    }

    public Date getFinLineaBase() {
	return finLineaBase;
    }

    public void setFinLineaBase(Date finLineaBase) {
	this.finLineaBase = finLineaBase;
    }

    public int getDuracionLineaBase() {
	return duracionLineaBase;
    }

    public void setDuracionLineaBase(int duracionLineaBase) {
	this.duracionLineaBase = duracionLineaBase;
    }

    /**
     *
     * @param i 0 - Avance Parcial, 1 - Avance Finalizado
     * @return
     */
    public ReporteTaskGrafTO grafica(Integer i) {
	return i != null && i == 1 ? grafFinalizado : grafParcial;
    }
}
