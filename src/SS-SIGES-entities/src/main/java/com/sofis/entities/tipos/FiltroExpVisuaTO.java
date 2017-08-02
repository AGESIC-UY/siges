package com.sofis.entities.tipos;

import com.sofis.entities.data.Areas;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.EstadosPublicacion;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class FiltroExpVisuaTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private EstadosPublicacion estadoPublicacion;
    private Date fechaDesde;
    private Date fechaHasta;
    private Boolean actualizados;
    private Integer orgPk;
    private Areas area;
    private List<Estados> estados;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadosPublicacion getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(EstadosPublicacion estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Boolean getActualizados() {
        return actualizados;
    }

    public void setActualizados(Boolean actualizados) {
        this.actualizados = actualizados;
    }

    public Integer getOrgPk() {
        return orgPk;
    }

    public void setOrgPk(Integer orgPk) {
        this.orgPk = orgPk;
    }

    public Areas getArea() {
        return area;
    }

    public void setArea(Areas area) {
        this.area = area;
    }

    public List<Estados> getEstados() {
        return estados;
    }

    public void setEstados(List<Estados> estados) {
        this.estados = estados;
    }
}
