/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.entities.tipos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class MisTareasProgProyTO implements Serializable{
    
    private Integer progPk;
    private String progNombre;
    private Integer proyPk;
    private String proyNombre;
    private List<MisTareasTO> tareas;
    private Boolean tieneProductos;
    
    public String getKey(){
        return "" + (progPk != null ? progPk : "_") +"|"+ (progNombre != null ? progNombre : "_") +"|"+ proyPk +"|"+ proyNombre;
    }
    

    /**
     * @return the progPk
     */
    public Integer getProgPk() {
        return progPk;
    }

    /**
     * @param progPk the progPk to set
     */
    public void setProgPk(Integer progPk) {
        this.progPk = progPk;
    }

    /**
     * @return the progNombre
     */
    public String getProgNombre() {
        return progNombre;
    }

    /**
     * @param progNombre the progNombre to set
     */
    public void setProgNombre(String progNombre) {
        this.progNombre = progNombre;
    }

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
     * @return the tareas
     */
    public List<MisTareasTO> getTareas() {
        return tareas;
    }

    /**
     * @param tareas the tareas to set
     */
    public void setTareas(List<MisTareasTO> tareas) {
        this.tareas = tareas;
    }

    /**
     * @return the tieneProductos
     */
    public Boolean getTieneProductos() {
        return tieneProductos;
    }

    /**
     * @param tieneProductos the tieneProductos to set
     */
    public void setTieneProductos(Boolean tieneProductos) {
        this.tieneProductos = tieneProductos;
    }
    
}
