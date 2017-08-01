package com.sofis.entities.tipos;

import java.io.Serializable;

/**
 * TO utilizado para representar la grafica de Gantt en el reporte.
 * @author MG
 */
public class ReporteTaskGrafTO implements Serializable{

    private int azul;
    private int verde;
    private int rojo;

    public ReporteTaskGrafTO() {
    }

    public ReporteTaskGrafTO(int azul, int verde, int rojo) {
        this.azul = azul;
        this.verde = verde;
        this.rojo = rojo;
    }

    public int getAzul() {
        return azul;
    }

    public void setAzul(int azul) {
        this.azul = azul;
    }

    public int getVerde() {
        return verde;
    }

    public void setVerde(int verde) {
        this.verde = verde;
    }

    public int getRojo() {
        return rojo;
    }

    public void setRojo(int rojo) {
        this.rojo = rojo;
    }
}
