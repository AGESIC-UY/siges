package com.sofis.entities.tipos;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class TablaDinamicaPresupuestoTO implements Serializable{

    private String title;
    private Integer codigo;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}