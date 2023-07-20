/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.entities.data;

import java.io.Serializable;
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Entity
@Table(name = "adquisiciones_rangos")
public class AdquisicionRango implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "adr_pk")
    private Integer adrPk;

    @JoinColumn(name = "adr_organizacion_fk", referencedColumnName = "org_pk")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Organismos adrOrganismo;

    @JoinColumn(name = "adr_area_fk", referencedColumnName = "area_pk")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Areas adrArea;

    @JoinColumn(name = "adr_division_fk", referencedColumnName = "obj_est_pk")
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private ObjetivoEstrategico adrDivision;

    @Column(name = "adr_desde")
    private Integer adrDesde;

    @Column(name = "adr_hasta")
    private Integer adrHasta;

    @Column(name = "adr_ultimo")
    private Integer adrUltimo;

    @Column(name = "adr_chequear")
    private Boolean adrChequear;

    @PreUpdate
    public void preUpdate() {
        //nothing
    }

    @PrePersist
    public void prePersist() {
        //nothing
    }

    public AdquisicionRango() {
    }

    public AdquisicionRango(Integer adrPk) {
        this.adrPk = adrPk;
    }

    public Integer getAdrPk() {
        return adrPk;
    }

    public void setAdrPk(Integer adrPk) {
        this.adrPk = adrPk;
    }

    public Organismos getAdrOrganismo() {
        return adrOrganismo;
    }

    public void setAdrOrganismo(Organismos adrOrganismo) {
        this.adrOrganismo = adrOrganismo;
    }

    public Areas getAdrArea() {
        return adrArea;
    }

    public void setAdrArea(Areas adrArea) {
        this.adrArea = adrArea;
    }

    public ObjetivoEstrategico getAdrDivision() {
        return adrDivision;
    }

    public void setAdrDivision(ObjetivoEstrategico adrDivision) {
        this.adrDivision = adrDivision;
    }

    public Integer getAdrDesde() {
        return adrDesde;
    }

    public void setAdrDesde(Integer adrDesde) {
        this.adrDesde = adrDesde;
    }

    public Integer getAdrHasta() {
        return adrHasta;
    }

    public void setAdrHasta(Integer adrHasta) {
        this.adrHasta = adrHasta;
    }

    public Integer getAdrUltimo() {
        return adrUltimo;
    }

    public void setAdrUltimo(Integer adrUltimo) {
        this.adrUltimo = adrUltimo;
    }

    public Boolean getAdrChequear() {
        return adrChequear;
    }

    public void setAdrChequear(Boolean adrChequear) {
        this.adrChequear = adrChequear;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adrPk != null ? adrPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdquisicionRango)) {
            return false;
        }
        AdquisicionRango other = (AdquisicionRango) object;
        if ((this.adrPk == null && other.adrPk != null) || (this.adrPk != null && !this.adrPk.equals(other.adrPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ObjectivoEstrategico[ objEstPk=" + adrPk + " ]";
    }

}
