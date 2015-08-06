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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Son los estados del proyecto que se van a exportar al visualizador.
 *
 * @author Usuario
 */
@Entity
@Table(name = "etapa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etapa.findAll", query = "SELECT e FROM Etapa e"),
    @NamedQuery(name = "Etapa.findByEtaPk", query = "SELECT e FROM Etapa e WHERE e.etaPk = :etaPk"),
    @NamedQuery(name = "Etapa.findByEtaCodigo", query = "SELECT e FROM Etapa e WHERE e.etaCodigo = :etaCodigo"),
    @NamedQuery(name = "Etapa.findByEtaNombre", query = "SELECT e FROM Etapa e WHERE e.etaNombre = :etaNombre"),
    @NamedQuery(name = "Etapa.findByEtaLabel", query = "SELECT e FROM Etapa e WHERE e.etaLabel = :etaLabel")})
public class Etapa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "eta_pk")
    private Integer etaPk;
    @Basic(optional = false)
    @Column(name = "eta_codigo")
    private String etaCodigo;
    @Basic(optional = false)
    @Column(name = "eta_nombre")
    private String etaNombre;
    @Basic(optional = true)
    @Column(name = "eta_label")
    private String etaLabel;
    
    @JoinColumn(name = "eta_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos etaOrgFk;

    public Etapa() {
    }

    public Etapa(Integer etaPk, String etaCodigo, String etaNombre, String etaLabel, Organismos etaOrgFk) {
        this.etaPk = etaPk;
        this.etaCodigo = etaCodigo;
        this.etaNombre = etaNombre;
        this.etaLabel = etaLabel;
        this.etaOrgFk = etaOrgFk;
    }

    public Etapa(Integer etaPk) {
        this.etaPk = etaPk;
    }

    public Etapa(Integer etaPk, String etaCodigo, String etaNombre, String etaLabel) {
        this.etaPk = etaPk;
        this.etaCodigo = etaCodigo;
        this.etaNombre = etaNombre;
        this.etaLabel = etaLabel;
    }

    public Integer getEtaPk() {
        return etaPk;
    }

    public void setEtaPk(Integer etaPk) {
        this.etaPk = etaPk;
    }

    public String getEtaCodigo() {
        return etaCodigo;
    }

    public void setEtaCodigo(String etaCodigo) {
        this.etaCodigo = etaCodigo;
    }

    public String getEtaNombre() {
        return etaNombre;
    }

    public void setEtaNombre(String etaNombre) {
        this.etaNombre = etaNombre;
    }

    public String getEtaLabel() {
        return etaLabel;
    }

    public void setEtaLabel(String etaLabel) {
        this.etaLabel = etaLabel;
    }

    public Organismos getEtaOrgFk() {
        return etaOrgFk;
    }

    public void setEtaOrgFk(Organismos etaOrgFk) {
        this.etaOrgFk = etaOrgFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (etaPk != null ? etaPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etapa)) {
            return false;
        }
        Etapa other = (Etapa) object;
        if ((this.etaPk == null && other.etaPk != null) || (this.etaPk != null && !this.etaPk.equals(other.etaPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Etapa[ etaPk=" + etaPk + " ]";
    }
}
