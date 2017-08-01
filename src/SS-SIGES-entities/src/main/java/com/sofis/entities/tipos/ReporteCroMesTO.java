package com.sofis.entities.tipos;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class ReporteCroMesTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private double alcance;

    public ReporteCroMesTO() {
    }
    
    public ReporteCroMesTO(double alcance) {
        this.alcance = alcance;
    }

    public double getAlcance() {
        return alcance;
    }

    public void setAlcance(double alcance) {
        this.alcance = alcance;
    }
}
