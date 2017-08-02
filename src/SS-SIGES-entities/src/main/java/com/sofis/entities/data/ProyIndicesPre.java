package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Entity
@Table(name = "proy_indices_pre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProyIndicesPre.findAll", query = "SELECT p FROM ProyIndicesPre p"),
    @NamedQuery(name = "ProyIndicesPre.findByProyindprePk", query = "SELECT p FROM ProyIndicesPre p WHERE p.proyindprePk = :proyindprePk"),
    @NamedQuery(name = "ProyIndicesPre.findByProyindpreProyindFk", query = "SELECT p FROM ProyIndicesPre p WHERE p.proyindpreProyindFk = :proyindpreProyindFk"),
    @NamedQuery(name = "ProyIndicesPre.findByProyindpreMonFk", query = "SELECT p FROM ProyIndicesPre p WHERE p.proyindpreMonFk = :proyindpreMonFk"),
    @NamedQuery(name = "ProyIndicesPre.findByProyindpreTotal", query = "SELECT p FROM ProyIndicesPre p WHERE p.proyindpreTotal = :proyindpreTotal"),
    @NamedQuery(name = "ProyIndicesPre.findByProyindpreEstPre", query = "SELECT p FROM ProyIndicesPre p WHERE p.proyindpreEstPre = :proyindpreEstPre")})
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProyIndicesPre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proyindpre_pk")
    private Integer proyindprePk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proyindpre_proyind_fk")
    private int proyindpreProyindFk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proyindpre_mon_fk")
    private int proyindpreMonFk;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "proyindpre_total")
    private Double proyindpreTotal;
    @Column(name = "proyindpre_est_pre")
    private Integer proyindpreEstPre;
    @Column(name = "proyindpre_anio")
    private Double proyindpreAnio;
    @Column(name = "proyindpre_ac")
    private Double proyindpreAC;
    @Column(name = "proyindpre_pv")
    private Double proyindprePV;

    public ProyIndicesPre() {
    }

    public ProyIndicesPre(Integer proyindprePk) {
        this.proyindprePk = proyindprePk;
    }

    public ProyIndicesPre(Integer proyindprePk, int proyindpreProyindFk, int proyindpreMonFk) {
        this.proyindprePk = proyindprePk;
        this.proyindpreProyindFk = proyindpreProyindFk;
        this.proyindpreMonFk = proyindpreMonFk;
    }

    public Integer getProyindprePk() {
        return proyindprePk;
    }

    public void setProyindprePk(Integer proyindprePk) {
        this.proyindprePk = proyindprePk;
    }

    public int getProyindpreProyindFk() {
        return proyindpreProyindFk;
    }

    public void setProyindpreProyindFk(int proyindpreProyindFk) {
        this.proyindpreProyindFk = proyindpreProyindFk;
    }

    public int getProyindpreMonFk() {
        return proyindpreMonFk;
    }

    public void setProyindpreMonFk(int proyindpreMonFk) {
        this.proyindpreMonFk = proyindpreMonFk;
    }

    public Double getProyindpreTotal() {
        return proyindpreTotal;
    }

    public void setProyindpreTotal(Double proyindpreTotal) {
        this.proyindpreTotal = proyindpreTotal;
    }

    public Integer getProyindpreEstPre() {
        return proyindpreEstPre;
    }

    public void setProyindpreEstPre(Integer proyindpreEstPre) {
        this.proyindpreEstPre = proyindpreEstPre;
    }

    public Double getProyindpreAnio() {
        return proyindpreAnio;
    }

    public void setProyindpreAnio(Double proyindpreAnio) {
        this.proyindpreAnio = proyindpreAnio;
    }

    public Double getProyindpreAC() {
        return proyindpreAC;
    }

    public void setProyindpreAC(Double proyindpreAC) {
        this.proyindpreAC = proyindpreAC;
    }

    public Double getProyindprePV() {
        return proyindprePV;
    }

    public void setProyindprePV(Double proyindprePV) {
        this.proyindprePV = proyindprePV;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proyindprePk != null ? proyindprePk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof ProyIndicesPre)) {
            return false;
        }
        ProyIndicesPre other = (ProyIndicesPre) object;
        if ((this.proyindprePk == null && other.proyindprePk != null) || (this.proyindprePk != null && !this.proyindprePk.equals(other.proyindprePk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ProyIndicesPre[ proyindprePk=" + proyindprePk + " ]";
    }
}
