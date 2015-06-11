/*
 * To change this template, choose Tools | Templates
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
@Table(name = "prog_indices_pre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProgIndicesPre.findAll", query = "SELECT p FROM ProgIndicesPre p"),
    @NamedQuery(name = "ProgIndicesPre.findByProgindprePk", query = "SELECT p FROM ProgIndicesPre p WHERE p.progindprePk = :progindprePk"),
    @NamedQuery(name = "ProgIndicesPre.findByProgindpreProgindFk", query = "SELECT p FROM ProgIndicesPre p WHERE p.progindpreProgindFk = :progindpreProgindFk"),
    @NamedQuery(name = "ProgIndicesPre.findByProgindpreMonFk", query = "SELECT p FROM ProgIndicesPre p WHERE p.progindpreMonFk = :progindpreMonFk"),
    @NamedQuery(name = "ProgIndicesPre.findByProgindpreTotal", query = "SELECT p FROM ProgIndicesPre p WHERE p.progindpreTotal = :progindpreTotal"),
    @NamedQuery(name = "ProgIndicesPre.findByProgindpreEstPre", query = "SELECT p FROM ProgIndicesPre p WHERE p.progindpreEstPre = :progindpreEstPre")})
public class ProgIndicesPre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "progindpre_pk")
    private Integer progindprePk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "progindpre_progind_fk")
    private int progindpreProgindFk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "progindpre_mon_fk")
    private int progindpreMonFk;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "progindpre_total")
    private Double progindpreTotal;
    @Column(name = "progindpre_est_pre")
    private Integer progindpreEstPre;

    public ProgIndicesPre() {
    }

    public ProgIndicesPre(Integer progindprePk) {
        this.progindprePk = progindprePk;
    }

    public ProgIndicesPre(Integer progindprePk, int progindpreProgindFk, int progindpreMonFk) {
        this.progindprePk = progindprePk;
        this.progindpreProgindFk = progindpreProgindFk;
        this.progindpreMonFk = progindpreMonFk;
    }

    public Integer getProgindprePk() {
        return progindprePk;
    }

    public void setProgindprePk(Integer progindprePk) {
        this.progindprePk = progindprePk;
    }

    public int getProgindpreProgindFk() {
        return progindpreProgindFk;
    }

    public void setProgindpreProgindFk(int progindpreProgindFk) {
        this.progindpreProgindFk = progindpreProgindFk;
    }

    public int getProgindpreMonFk() {
        return progindpreMonFk;
    }

    public void setProgindpreMonFk(int progindpreMonFk) {
        this.progindpreMonFk = progindpreMonFk;
    }

    public Double getProgindpreTotal() {
        return progindpreTotal;
    }

    public void setProgindpreTotal(Double progindpreTotal) {
        this.progindpreTotal = progindpreTotal;
    }

    public Integer getProgindpreEstPre() {
        return progindpreEstPre;
    }

    public void setProgindpreEstPre(Integer progindpreEstPre) {
        this.progindpreEstPre = progindpreEstPre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (progindprePk != null ? progindprePk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgIndicesPre)) {
            return false;
        }
        ProgIndicesPre other = (ProgIndicesPre) object;
        if ((this.progindprePk == null && other.progindprePk != null) || (this.progindprePk != null && !this.progindprePk.equals(other.progindprePk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ProgIndicesPre[ progindprePk=" + progindprePk + " ]";
    }
    
}
