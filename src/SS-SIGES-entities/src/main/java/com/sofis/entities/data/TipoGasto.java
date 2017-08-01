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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tipo_gasto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoGasto.findAll", query = "SELECT t FROM TipoGasto t"),
    @NamedQuery(name = "TipoGasto.findByTipogasPk", query = "SELECT t FROM TipoGasto t WHERE t.tipogasPk = :tipogasPk"),
    @NamedQuery(name = "TipoGasto.findByTipogasNombre", query = "SELECT t FROM TipoGasto t WHERE t.tipogasNombre = :tipogasNombre")})
public class TipoGasto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipogas_pk")
    private Integer tipogasPk;
    @JoinColumn(name = "tipogas_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos tipogasOrgFk;
    @Basic(optional = false)
    @Column(name = "tipogas_nombre")
    private String tipogasNombre;

    public TipoGasto() {
    }

    public TipoGasto(Integer tipogasPk) {
        this.tipogasPk = tipogasPk;
    }

    public TipoGasto(Integer tipogasPk, String tipogasNombre) {
        this.tipogasPk = tipogasPk;
        this.tipogasNombre = tipogasNombre;
    }

    public Integer getTipogasPk() {
        return tipogasPk;
    }

    public void setTipogasPk(Integer tipogasPk) {
        this.tipogasPk = tipogasPk;
    }

    public Organismos getTipogasOrgFk() {
        return tipogasOrgFk;
    }

    public void setTipogasOrgFk(Organismos tipogasOrgFk) {
        this.tipogasOrgFk = tipogasOrgFk;
    }

    public String getTipogasNombre() {
        return tipogasNombre;
    }

    public void setTipogasNombre(String tipogasDesc) {
        this.tipogasNombre = tipogasDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipogasPk != null ? tipogasPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof TipoGasto)) {
            return false;
        }
        TipoGasto other = (TipoGasto) object;
        if ((this.tipogasPk == null && other.tipogasPk != null) || (this.tipogasPk != null && !this.tipogasPk.equals(other.tipogasPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.TipoGasto[ tipogasPk=" + tipogasPk + " ]";
    }
}
