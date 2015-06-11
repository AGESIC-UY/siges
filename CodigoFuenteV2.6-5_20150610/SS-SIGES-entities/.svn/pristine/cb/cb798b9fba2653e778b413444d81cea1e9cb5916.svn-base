package com.sofis.entities.data;

import java.io.Serializable;
import java.math.BigDecimal;
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
 *
 * @author Usuario
 */
@Entity
@Table(name = "valor_hora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValorHora.findAll", query = "SELECT v FROM ValorHora v"),
    @NamedQuery(name = "ValorHora.findByValHorPk", query = "SELECT v FROM ValorHora v WHERE v.valHorPk = :valHorPk"),
    @NamedQuery(name = "ValorHora.findByValHorValor", query = "SELECT v FROM ValorHora v WHERE v.valHorValor = :valHorValor"),
    @NamedQuery(name = "ValorHora.findByValHorAnio", query = "SELECT v FROM ValorHora v WHERE v.valHorAnio = :valHorAnio")})
public class ValorHora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "val_hor_pk")
    private Integer valHorPk;
    @JoinColumn(name = "val_hor_usu_fk", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private SsUsuario valHorUsuarioFk;
    @JoinColumn(name = "val_hor_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos valHorOrganismosFk;
    @JoinColumn(name = "val_hor_mon_fk", referencedColumnName = "mon_pk")
    @ManyToOne(optional = false)
    private Moneda valHorMonedaFk;
    @Basic(optional = false)
    @Column(name = "val_hor_valor")
    private BigDecimal valHorValor;
    @Basic(optional = false)
    @Column(name = "val_hor_anio")
    private Integer valHorAnio;

    public ValorHora() {
    }

    public ValorHora(SsUsuario valHorUsuarioFk, Organismos valHorOrganismosFk) {
        this.valHorUsuarioFk = valHorUsuarioFk;
        this.valHorOrganismosFk = valHorOrganismosFk;
    }

    public ValorHora(Integer valHorPk) {
        this.valHorPk = valHorPk;
    }

    public ValorHora(Integer valHorPk, BigDecimal valHorValor, int valHorAnio) {
        this.valHorPk = valHorPk;
        this.valHorValor = valHorValor;
        this.valHorAnio = valHorAnio;
    }

    public Integer getValHorPk() {
        return valHorPk;
    }

    public void setValHorPk(Integer valHorPk) {
        this.valHorPk = valHorPk;
    }

    public SsUsuario getValHorUsuarioFk() {
        return valHorUsuarioFk;
    }

    public void setValHorUsuarioFk(SsUsuario valHorUsuarioFk) {
        this.valHorUsuarioFk = valHorUsuarioFk;
    }

    public Organismos getValHorOrganismosFk() {
        return valHorOrganismosFk;
    }

    public void setValHorOrganismosFk(Organismos valHorOrganismosFk) {
        this.valHorOrganismosFk = valHorOrganismosFk;
    }

    public Moneda getValHorMonedaFk() {
        return valHorMonedaFk;
    }

    public void setValHorMonedaFk(Moneda valHorMonedaFk) {
        this.valHorMonedaFk = valHorMonedaFk;
    }

    public BigDecimal getValHorValor() {
        return valHorValor;
    }

    public void setValHorValor(BigDecimal valHorValor) {
        this.valHorValor = valHorValor;
    }

    public Integer getValHorAnio() {
        return valHorAnio;
    }

    public void setValHorAnio(Integer valHorAnio) {
        this.valHorAnio = valHorAnio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (valHorPk != null ? valHorPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValorHora)) {
            return false;
        }
        ValorHora other = (ValorHora) object;
        if ((this.valHorPk == null && other.valHorPk != null) || (this.valHorPk != null && !this.valHorPk.equals(other.valHorPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ValorHora[ valHorPk=" + valHorPk + " ]";
    }
    
}
