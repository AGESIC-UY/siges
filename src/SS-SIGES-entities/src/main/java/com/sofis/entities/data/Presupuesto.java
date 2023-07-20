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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "presupuesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Presupuesto.findAll", query = "SELECT p FROM Presupuesto p"),
    @NamedQuery(name = "Presupuesto.findByPrePk", query = "SELECT p FROM Presupuesto p WHERE p.prePk = :prePk"),
    @NamedQuery(name = "Presupuesto.findByPreBase", query = "SELECT p FROM Presupuesto p WHERE p.preBase = :preBase"),
    @NamedQuery(name = "Presupuesto.findByPreMoneda", query = "SELECT p FROM Presupuesto p WHERE p.preMoneda = :preMoneda")})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Presupuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pre_pk")
    private Integer prePk;
    @Column(name = "pre_base")
    private Double preBase;
    @JoinColumn(name = "pre_moneda", referencedColumnName = "mon_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private Moneda preMoneda;
    
    @JoinColumn(name = "pre_fuente_organi_fk", referencedColumnName = "fue_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private FuenteFinanciamiento fuenteFinanciamiento;
    
    @Column(name = "pre_ocultar_pagos_confirmados")
    private Boolean preOcultarPagosConfirmados;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adqPreFk", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private Set<Adquisicion> adquisicionSet;
    
    @OneToOne(mappedBy = "proyPreFk", fetch = FetchType.LAZY)
    private Proyectos proyecto;

    public Presupuesto(Integer prePk) {
        this.prePk = prePk;
    }

    public Integer getPrePk() {
        return prePk;
    }

    public void setPrePk(Integer prePk) {
        this.prePk = prePk;
    }

    public Double getPreBase() {
        return preBase;
    }

    public void setPreBase(Double preBase) {
        this.preBase = preBase;
    }

    public Moneda getPreMoneda() {
        return preMoneda;
    }

    public void setPreMoneda(Moneda preMoneda) {
        this.preMoneda = preMoneda;
    }

    public FuenteFinanciamiento getFuenteFinanciamiento() {
        return fuenteFinanciamiento;
    }

    public void setFuenteFinanciamiento(FuenteFinanciamiento fuenteFinanciamiento) {
        this.fuenteFinanciamiento = fuenteFinanciamiento;
    }

    @XmlTransient
    public Set<Adquisicion> getAdquisicionSet() {
        return adquisicionSet;
    }

    public void setAdquisicionSet(Set<Adquisicion> adquisicionSet) {
        this.adquisicionSet = adquisicionSet;
    }

    public Proyectos getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyectos proyecto) {
        this.proyecto = proyecto;
    }

    public Boolean getPreOcultarPagosConfirmados() {
        return preOcultarPagosConfirmados;
    }

    public void setPreOcultarPagosConfirmados(Boolean preOcultarPagosConfirmados) {
        this.preOcultarPagosConfirmados = preOcultarPagosConfirmados;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prePk != null ? prePk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Presupuesto)) {
            return false;
        }
        Presupuesto other = (Presupuesto) object;
        if ((this.prePk == null && other.prePk != null) || (this.prePk != null && !this.prePk.equals(other.prePk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Presupuesto[ prePk=" + prePk + " ]";
    }
}
