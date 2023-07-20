/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Entity
@Table(name = "tipo_riesgos")
public class TipoRiesgo implements Serializable {
    
       public static final int NOMBRE_LENGHT = 100;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "trs_pk")
    private Integer trsPk;


    @Column(name = "trs_nombre")
    private String trsNombre;

    @Column(name = "trs_habilitado")
    private Boolean trsHabilitado;
    
    @JoinColumn(name = "trs_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos trsOrgFk;

    @PreUpdate
    public void preUpdate() {
        //nothing
    }

    @PrePersist
    public void prePersist() {
        //nothing
    }

    public TipoRiesgo() {
    }

    public Integer getTrsPk() {
        return trsPk;
    }

    public void setTrsPk(Integer trsPk) {
        this.trsPk = trsPk;
    }

    public String getTrsNombre() {
        return trsNombre;
    }

    public void setTrsNombre(String trsNombre) {
        this.trsNombre = trsNombre;
    }

    public Boolean getTrsHabilitado() {
        return trsHabilitado;
    }

    public void setTrsHabilitado(Boolean trsHabilitado) {
        this.trsHabilitado = trsHabilitado;
    }

    public Organismos getTrsOrgFk() {
        return trsOrgFk;
    }

    public void setTrsOrgFk(Organismos trsOrgFk) {
        this.trsOrgFk = trsOrgFk;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trsPk != null ? trsPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRiesgo)) {
            return false;
        }
        TipoRiesgo other = (TipoRiesgo) object;
        if ((this.trsPk == null && other.trsPk != null) || (this.trsPk != null && !this.trsPk.equals(other.trsPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ObjectivoEstrategico[ objEstPk=" + trsPk + " ]";
    }

}
