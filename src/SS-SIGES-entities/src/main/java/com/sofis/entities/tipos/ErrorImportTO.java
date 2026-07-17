package com.sofis.entities.tipos;

import java.io.Serializable;

public class ErrorImportTO implements Serializable, Cloneable {

   
    private Integer linea;
    private String observacion;

    public Integer getLinea() {
        return linea;
    }

    public void setLinea(Integer linea) {
        this.linea = linea;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

   

    public ErrorImportTO() {
    }

    


}
