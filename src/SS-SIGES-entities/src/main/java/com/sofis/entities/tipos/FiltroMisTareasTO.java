package com.sofis.entities.tipos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@XmlRootElement
public class FiltroMisTareasTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer progPk;
    private String proyNombre;
    private Integer usuCoordPk;
    private Boolean tareaFinalizada;
    private Integer anio;
    private Boolean conProductos;

    public FiltroMisTareasTO() {
    }

    public Integer getProgPk() {
        return progPk;
    }

    public void setProgPk(Integer progPk) {
        this.progPk = progPk;
    }

    public String getProyNombre() {
        return proyNombre;
    }

    public void setProyNombre(String proyNombre) {
        this.proyNombre = proyNombre;
    }

    public Integer getUsuCoordPk() {
        return usuCoordPk;
    }

    public void setUsuCoordPk(Integer usuCoordPk) {
        this.usuCoordPk = usuCoordPk;
    }

    public Boolean getTareaFinalizada() {
        return tareaFinalizada;
    }

    public void setTareaFinalizada(Boolean tareaFinalizada) {
        this.tareaFinalizada = tareaFinalizada;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Boolean getConProductos() {
        return conProductos;
    }

    public void setConProductos(Boolean conProductos) {
        this.conProductos = conProductos;
    }
    
}
