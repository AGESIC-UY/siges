package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "departamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamentos.findAll", query = "SELECT d FROM Departamentos d"),
    @NamedQuery(name = "Departamentos.findByDepPk", query = "SELECT d FROM Departamentos d WHERE d.depPk = :depPk"),
    @NamedQuery(name = "Departamentos.findByDepNombre", query = "SELECT d FROM Departamentos d WHERE d.depNombre = :depNombre"),
    @NamedQuery(name = "Departamentos.findByDepLat", query = "SELECT d FROM Departamentos d WHERE d.depLat = :depLat"),
    @NamedQuery(name = "Departamentos.findByDepLng", query = "SELECT d FROM Departamentos d WHERE d.depLng = :depLng"),
    @NamedQuery(name = "Departamentos.findByDepZoom", query = "SELECT d FROM Departamentos d WHERE d.depZoom = :depZoom")})
public class Departamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "dep_pk")
    private Integer depPk;
    @Column(name = "dep_nombre")
    private String depNombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dep_lat")
    private Double depLat;
    @Column(name = "dep_lng")
    private Double depLng;
    @Column(name = "dep_zoom")
    private Integer depZoom;

    public Departamentos() {
    }

    public Departamentos(Integer depPk) {
        this.depPk = depPk;
    }

    public Integer getDepPk() {
        return depPk;
    }

    public void setDepPk(Integer depPk) {
        this.depPk = depPk;
    }

    public String getDepNombre() {
        return depNombre;
    }

    public void setDepNombre(String depNombre) {
        this.depNombre = depNombre;
    }

    public Double getDepLat() {
        return depLat;
    }

    public void setDepLat(Double depLat) {
        this.depLat = depLat;
    }

    public Double getDepLng() {
        return depLng;
    }

    public void setDepLng(Double depLng) {
        this.depLng = depLng;
    }

    public Integer getDepZoom() {
        return depZoom;
    }

    public void setDepZoom(Integer depZoom) {
        this.depZoom = depZoom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depPk != null ? depPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Departamentos)) {
            return false;
        }
        Departamentos other = (Departamentos) object;
        if ((this.depPk == null && other.depPk != null) || (this.depPk != null && !this.depPk.equals(other.depPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Departamentos[ depPk=" + depPk + " ]";
    }

}
