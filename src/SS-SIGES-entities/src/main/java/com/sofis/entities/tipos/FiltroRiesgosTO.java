package com.sofis.entities.tipos;

import java.util.Arrays;
import java.util.Objects;

public class FiltroRiesgosTO {

    private Integer id;
    private String nombreProyecto;
    private Integer codigo;
    private Integer[] estadosProyecto;
    private Integer orgPk;

    private Integer idOrganismo;

    private Integer idGerente;
    
    private Integer idAdjunto;
    
    private Integer idPmof;
    
    private Integer idSponsor;
    
    private Integer[] estados;
    
    private Integer idArea;
    
    private String nombreRiesgo;
    
    private Integer tipoRiesgoPk;
    
    private int pageNumber;
    
    
    private Boolean activo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Integer getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(Integer idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public Integer getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(Integer idGerente) {
        this.idGerente = idGerente;
    }

    public Integer[] getEstados() {
        return estados;
    }

    public void setEstados(Integer[] estados) {
        this.estados = estados;
    }

    public Integer getIdAdjunto() {
        return idAdjunto;
    }

    public void setIdAdjunto(Integer idAdjunto) {
        this.idAdjunto = idAdjunto;
    }

    public Integer getIdPmof() {
        return idPmof;
    }

    public void setIdPmof(Integer idPmof) {
        this.idPmof = idPmof;
    }

    public Integer getIdSponsor() {
        return idSponsor;
    }

    public void setIdSponsor(Integer idSponsor) {
        this.idSponsor = idSponsor;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getOrgPk() {
        return orgPk;
    }

    public void setOrgPk(Integer orgPk) {
        this.orgPk = orgPk;
    }

    public Integer[] getEstadosProyecto() {
        return estadosProyecto;
    }

    public void setEstadosProyecto(Integer[] estadosProyecto) {
        this.estadosProyecto = estadosProyecto;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombreRiesgo() {
        return nombreRiesgo;
    }

    public void setNombreRiesgo(String nombreRiesgo) {
        this.nombreRiesgo = nombreRiesgo;
    }

    public Integer getTipoRiesgoPk() {
        return tipoRiesgoPk;
    }

    public void setTipoRiesgoPk(Integer tipoRiesgoPk) {
        this.tipoRiesgoPk = tipoRiesgoPk;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.nombreProyecto);
        hash = 67 * hash + Objects.hashCode(this.codigo);
        hash = 67 * hash + Arrays.deepHashCode(this.estadosProyecto);
        hash = 67 * hash + Objects.hashCode(this.orgPk);
        hash = 67 * hash + Objects.hashCode(this.idOrganismo);
        hash = 67 * hash + Objects.hashCode(this.idGerente);
        hash = 67 * hash + Objects.hashCode(this.idAdjunto);
        hash = 67 * hash + Objects.hashCode(this.idPmof);
        hash = 67 * hash + Objects.hashCode(this.idSponsor);
        hash = 67 * hash + Arrays.deepHashCode(this.estados);
        hash = 67 * hash + Objects.hashCode(this.idArea);
        hash = 67 * hash + Objects.hashCode(this.nombreRiesgo);
        hash = 67 * hash + this.pageNumber;
        hash = 67 * hash + Objects.hashCode(this.activo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FiltroRiesgosTO other = (FiltroRiesgosTO) obj;
        if (this.pageNumber != other.pageNumber) {
            return false;
        }
        if (!Objects.equals(this.nombreProyecto, other.nombreProyecto)) {
            return false;
        }
        if (!Objects.equals(this.nombreRiesgo, other.nombreRiesgo)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Arrays.deepEquals(this.estadosProyecto, other.estadosProyecto)) {
            return false;
        }
        if (!Objects.equals(this.orgPk, other.orgPk)) {
            return false;
        }
        if (!Objects.equals(this.idOrganismo, other.idOrganismo)) {
            return false;
        }
        if (!Objects.equals(this.idGerente, other.idGerente)) {
            return false;
        }
        if (!Objects.equals(this.idAdjunto, other.idAdjunto)) {
            return false;
        }
        if (!Objects.equals(this.idPmof, other.idPmof)) {
            return false;
        }
        if (!Objects.equals(this.idSponsor, other.idSponsor)) {
            return false;
        }
        if (!Arrays.deepEquals(this.estados, other.estados)) {
            return false;
        }
        if (!Objects.equals(this.idArea, other.idArea)) {
            return false;
        }
        if (!Objects.equals(this.activo, other.activo)) {
            return false;
        }
        return true;
    }


}
