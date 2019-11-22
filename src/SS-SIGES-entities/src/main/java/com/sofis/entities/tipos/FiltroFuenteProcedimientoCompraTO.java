package com.sofis.entities.tipos;

import com.sofis.entities.data.Organismos;
import java.io.Serializable;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class FiltroFuenteProcedimientoCompraTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String fuente;
    private String procedimientoCompra;
    private Long largo;
    private Boolean habilitado;
    private Organismos organismo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getProcedimientoCompra() {
        return procedimientoCompra;
    }

    public void setProcedimientoCompra(String procedimientoCompra) {
        this.procedimientoCompra = procedimientoCompra;
    }

    public Long getLargo() {
        return largo;
    }

    public void setLargo(Long largo) {
        this.largo = largo;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Organismos getOrganismo() {
        return organismo;
    }

    public void setOrganismo(Organismos organismo) {
        this.organismo = organismo;
    }
}
