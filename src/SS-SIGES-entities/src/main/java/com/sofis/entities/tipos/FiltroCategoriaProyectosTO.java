package com.sofis.entities.tipos;

import java.io.Serializable;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class FiltroCategoriaProyectosTO implements Serializable{
    
    private Boolean activos;
    private Integer tipo;
    private Integer orgPk;

    public Boolean getActivos() {
        return activos;
    }

    public void setActivos(Boolean activos) {
        this.activos = activos;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getOrgPk() {
        return orgPk;
    }

    public void setOrgPk(Integer orgPk) {
        this.orgPk = orgPk;
    }
}
