package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tipo_leccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoLeccion.findAll", query = "SELECT t FROM TipoLeccion t"),
    @NamedQuery(name = "TipoLeccion.findByTipolecPk", query = "SELECT t FROM TipoLeccion t WHERE t.tipolecPk = :tipolecPk"),
    @NamedQuery(name = "TipoLeccion.findByTipolecNombre", query = "SELECT t FROM TipoLeccion t WHERE t.tipolecNombre = :tipolecNombre")})
public class TipoLeccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipolec_pk")
    private Integer tipolecPk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipolec_nombre")
    private String tipolecNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecaprTipoFk", fetch = FetchType.LAZY)
    private Set<LeccionesAprendidas> leccionesAprendidasSet;

    public TipoLeccion() {
    }

    public TipoLeccion(Integer tipolecPk) {
        this.tipolecPk = tipolecPk;
    }

    public TipoLeccion(Integer tipolecPk, String tipolecNombre) {
        this.tipolecPk = tipolecPk;
        this.tipolecNombre = tipolecNombre;
    }

    public Integer getTipolecPk() {
        return tipolecPk;
    }

    public void setTipolecPk(Integer tipolecPk) {
        this.tipolecPk = tipolecPk;
    }

    public String getTipolecNombre() {
        return tipolecNombre;
    }

    public void setTipolecNombre(String tipolecNombre) {
        this.tipolecNombre = tipolecNombre;
    }

    @XmlTransient
    public Set<LeccionesAprendidas> getLeccionesAprendidasSet() {
        return leccionesAprendidasSet;
    }

    public void setLeccionesAprendidasSet(Set<LeccionesAprendidas> leccionesAprendidasSet) {
        this.leccionesAprendidasSet = leccionesAprendidasSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipolecPk != null ? tipolecPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoLeccion)) {
            return false;
        }
        TipoLeccion other = (TipoLeccion) object;
        if ((this.tipolecPk == null && other.tipolecPk != null) 
                || (this.tipolecPk != null && !this.tipolecPk.equals(other.tipolecPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.TipoLeccion[ tipolecPk=" + tipolecPk + " ]";
    }
    
}
