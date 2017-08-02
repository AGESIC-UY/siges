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
public class CronogramaTO {

    @XmlElement (name = "croEntSeleccionado", required = false)
    private Integer croEntSeleccionado; //no se usa
    @XmlElement (name = "croPermisoEscritura", required = false)
    private Boolean croPermisoEscritura; //se usa?
    @XmlElement (name = "croPermisoEscrituraPadre", required = false)
    private Boolean croPermisoEscrituraPadre; //se usa?
    @XmlElement (name = "entregableSet", required = true)
    private List<EntregableTO> entregableSet;
    
    @XmlElement(name="attrs")
    private List<AttrTO> attrs;
    
    //<editor-fold defaultstate="collapsed" desc="getters-setters">
    
    /**
     * @return the CroEntSeleccionado
     */
    public Integer getCroEntSeleccionado() {
        return croEntSeleccionado;
    }

    /**
     * @param CroEntSeleccionado the CroEntSeleccionado to set
     */
    public void setCroEntSeleccionado(Integer CroEntSeleccionado) {
        this.croEntSeleccionado = CroEntSeleccionado;
    }

    /**
     * @return the entregableSet
     */
    public List<EntregableTO> getEntregableSet() {
        return entregableSet;
    }

    /**
     * @param entregableSet the entregableSet to set
     */
    public void setEntregableSet(List<EntregableTO> entregableSet) {
        this.entregableSet = entregableSet;
    }

    /**
     * @return the croPermisoEscritura
     */
    public Boolean getCroPermisoEscritura() {
        return croPermisoEscritura;
    }

    /**
     * @param croPermisoEscritura the croPermisoEscritura to set
     */
    public void setCroPermisoEscritura(Boolean croPermisoEscritura) {
        this.croPermisoEscritura = croPermisoEscritura;
    }

    /**
     * @return the croPermisoEscrituraPadre
     */
    public Boolean getCroPermisoEscrituraPadre() {
        return croPermisoEscrituraPadre;
    }

    /**
     * @param croPermisoEscrituraPadre the croPermisoEscrituraPadre to set
     */
    public void setCroPermisoEscrituraPadre(Boolean croPermisoEscrituraPadre) {
        this.croPermisoEscrituraPadre = croPermisoEscrituraPadre;
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
    
}
//</editor-fold>