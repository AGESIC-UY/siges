package com.sofis.entities.tipos;

import java.io.Serializable;

public class ResultadoTO implements Serializable, Cloneable {

   
    private Integer proyectoPk;
    private String observacion;

    public Integer getProyectoPk() {
        return proyectoPk;
    }

    public void setProyectoPk(Integer proyectoPk) {
        this.proyectoPk = proyectoPk;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

   

    public ResultadoTO() {
    }

    


}
