/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.entities.tipos;

import com.sofis.entities.data.Organismos;
import java.io.Serializable;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class FiltroObjectivoEstategicoTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Organismos organismo;
    private Integer orgPk;
    private String nombre;

    /**
     * @return the organismo
     */
    public Organismos getOrganismo() {
        return organismo;
    }

    /**
     * @param organismo the organismo to set
     */
    public void setOrganismo(Organismos organismo) {
        this.organismo = organismo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the orgPk
     */
    public Integer getOrgPk() {
        return orgPk;
    }

    /**
     * @param orgPk the orgPk to set
     */
    public void setOrgPk(Integer orgPk) {
        this.orgPk = orgPk;
    }

}
