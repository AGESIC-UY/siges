package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "prod_mes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProdMes.findAll", query = "SELECT p FROM ProdMes p"),
    @NamedQuery(name = "ProdMes.findByProdmesPk", query = "SELECT p FROM ProdMes p WHERE p.prodmesPk = :prodmesPk"),
    @NamedQuery(name = "ProdMes.findByProdmesMes", query = "SELECT p FROM ProdMes p WHERE p.prodmesMes = :prodmesMes"),
    @NamedQuery(name = "ProdMes.findByProdmesAnio", query = "SELECT p FROM ProdMes p WHERE p.prodmesAnio = :prodmesAnio"),
    @NamedQuery(name = "ProdMes.findByProdmesPlan", query = "SELECT p FROM ProdMes p WHERE p.prodmesPlan = :prodmesPlan"),
    @NamedQuery(name = "ProdMes.findByProdmesReal", query = "SELECT p FROM ProdMes p WHERE p.prodmesReal = :prodmesReal"),
    @NamedQuery(name = "ProdMes.findByProdmesAcuPlan", query = "SELECT p FROM ProdMes p WHERE p.prodmesAcuPlan = :prodmesAcuPlan"),
    @NamedQuery(name = "ProdMes.findByProdmesAcuReal", query = "SELECT p FROM ProdMes p WHERE p.prodmesAcuReal = :prodmesAcuReal")})
public class ProdMes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prodmes_pk")
    private Integer prodmesPk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prodmes_mes")
    private short prodmesMes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prodmes_anio")
    private short prodmesAnio;
    @Basic(optional = false)
    @Column(name = "prodmes_plan")
    private Double prodmesPlan;
    @Column(name = "prodmes_real")
    private Double prodmesReal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prodmes_acu_plan")
    private Double prodmesAcuPlan;
    @Column(name = "prodmes_acu_real")
    private Double prodmesAcuReal;
    @JoinColumn(name = "prodmes_prod_fk", referencedColumnName = "prod_pk")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Productos prodmesProdFk;

    public ProdMes() {
    }

    public ProdMes(Integer prodmesPk) {
        this.prodmesPk = prodmesPk;
    }

    public ProdMes(Integer prodmesPk, short prodmesMes, short prodmesAnio, Double prodmesPlan, Double prodmesAcuPlan) {
        this.prodmesPk = prodmesPk;
        this.prodmesMes = prodmesMes;
        this.prodmesAnio = prodmesAnio;
        this.prodmesPlan = prodmesPlan;
        this.prodmesAcuPlan = prodmesAcuPlan;
    }

    public Integer getProdmesPk() {
        return prodmesPk;
    }

    public void setProdmesPk(Integer prodmesPk) {
        this.prodmesPk = prodmesPk;
    }

    public short getProdmesMes() {
        return prodmesMes;
    }

    public void setProdmesMes(short prodmesMes) {
        this.prodmesMes = prodmesMes;
    }

    public short getProdmesAnio() {
        return prodmesAnio;
    }

    public void setProdmesAnio(short prodmesAnio) {
        this.prodmesAnio = prodmesAnio;
    }

    public Double getProdmesPlan() {
        return prodmesPlan;
    }

    public void setProdmesPlan(Double prodmesPlan) {
        this.prodmesPlan = prodmesPlan;
    }

    public Double getProdmesReal() {
        return prodmesReal;
    }

    public void setProdmesReal(Double prodmesReal) {
        this.prodmesReal = prodmesReal;
    }

    public Double getProdmesAcuPlan() {
        return prodmesAcuPlan;
    }

    public void setProdmesAcuPlan(Double prodmesAcuPlan) {
        this.prodmesAcuPlan = prodmesAcuPlan;
    }

    public Double getProdmesAcuReal() {
        return prodmesAcuReal;
    }

    public void setProdmesAcuReal(Double prodmesAcuReal) {
        this.prodmesAcuReal = prodmesAcuReal;
    }

    public Productos getProdmesProdFk() {
        return prodmesProdFk;
    }

    public void setProdmesProdFk(Productos prodmesProdFk) {
        this.prodmesProdFk = prodmesProdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prodmesPk != null ? prodmesPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof ProdMes)) {
            return false;
        }
        ProdMes other = (ProdMes) object;
        if ((this.prodmesPk == null && other.prodmesPk != null) 
                || (this.prodmesPk != null && !this.prodmesPk.equals(other.prodmesPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ProdMes[ prodmesPk=" + prodmesPk + " ]";
    }
}
