package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "fuente_financiamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FuenteFinanciamiento.findAll", query = "SELECT f FROM FuenteFinanciamiento f"),
    @NamedQuery(name = "FuenteFinanciamiento.findByFuePk", query = "SELECT f FROM FuenteFinanciamiento f WHERE f.fuePk = :fuePk"),
    @NamedQuery(name = "FuenteFinanciamiento.findByFueNombre", query = "SELECT f FROM FuenteFinanciamiento f WHERE f.fueNombre = :fueNombre"),
    @NamedQuery(name = "FuenteFinanciamiento.findByOrgPk", query = "SELECT f FROM FuenteFinanciamiento f WHERE f.fueOrgFk.orgPk = :orgPk ORDER BY f.fueNombre"),
})
public class FuenteFinanciamiento implements Serializable {
    
    public static final int NOMBRE_LENGHT = 300;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fue_pk")
    private Integer fuePk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fue_nombre")
    private String fueNombre;
    @JoinColumn(name = "fue_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos fueOrgFk;

    @Column(name = "fue_habilitada")
    private Boolean fueHabilitada;

    public FuenteFinanciamiento() {
    }

    public FuenteFinanciamiento(Integer fuePk) {
        this.fuePk = fuePk;
    }

    public FuenteFinanciamiento(Integer fuePk, String fueNombre) {
        this.fuePk = fuePk;
        this.fueNombre = fueNombre;
    }

    public Integer getFuePk() {
        return fuePk;
    }

    public void setFuePk(Integer fuePk) {
        this.fuePk = fuePk;
    }

    public String getFueNombre() {
        return fueNombre;
    }

    public void setFueNombre(String fueNombre) {
        this.fueNombre = fueNombre;
    }

    public Organismos getFueOrgFk() {
        return fueOrgFk;
    }

    public void setFueOrgFk(Organismos fueOrgFk) {
        this.fueOrgFk = fueOrgFk;
    }

    public Boolean getFueHabilitada() {
        return fueHabilitada;
    }

    public void setFueHabilitada(Boolean fueHabilitada) {
        this.fueHabilitada = fueHabilitada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fuePk != null ? fuePk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof FuenteFinanciamiento)) {
            return false;
        }
        FuenteFinanciamiento other = (FuenteFinanciamiento) object;
        if ((this.fuePk == null && other.fuePk != null) || (this.fuePk != null && !this.fuePk.equals(other.fuePk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.FuenteFinanciamiento[ fuePk=" + fuePk + " ]";
    }

}
