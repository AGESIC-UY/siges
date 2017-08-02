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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "devengado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Devengado.findAll", query = "SELECT d FROM Devengado d"),
    @NamedQuery(name = "Devengado.findByDevPk", query = "SELECT d FROM Devengado d WHERE d.devPk = :devPk"),
    @NamedQuery(name = "Devengado.findByDevAdqFk", query = "SELECT d FROM Devengado d WHERE d.devAdqFk = :devAdqFk"),
    @NamedQuery(name = "Devengado.findByDevMes", query = "SELECT d FROM Devengado d WHERE d.devMes = :devMes"),
    @NamedQuery(name = "Devengado.findByDevAnio", query = "SELECT d FROM Devengado d WHERE d.devAnio = :devAnio"),
    @NamedQuery(name = "Devengado.findByDevPlan", query = "SELECT d FROM Devengado d WHERE d.devPlan = :devPlan"),
    @NamedQuery(name = "Devengado.findByDevReal", query = "SELECT d FROM Devengado d WHERE d.devReal = :devReal")})
public class Devengado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dev_pk")
    private Integer devPk;
    @JoinColumn(name = "dev_adq_fk", referencedColumnName = "adq_pk")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Adquisicion devAdqFk;
    @Basic(optional = false)
    @Column(name = "dev_mes")
    private short devMes;
    @Basic(optional = false)
    @Column(name = "dev_anio")
    private short devAnio;
    @Column(name = "dev_plan")
    private Double devPlan;
    @Column(name = "dev_real")
    private Double devReal;
    
    @Transient
    private Boolean editarPlan;
    @Transient
    private Boolean editarReal;

    public Devengado() {
    }

    public Devengado(Integer devPk) {
        this.devPk = devPk;
    }

    public Integer getDevPk() {
        return devPk;
    }

    public void setDevPk(Integer devPk) {
        this.devPk = devPk;
    }

    public Adquisicion getDevAdqFk() {
        return devAdqFk;
    }

    public void setDevAdqFk(Adquisicion devAdqFk) {
        this.devAdqFk = devAdqFk;
    }

    public short getDevMes() {
        return devMes;
    }

    public void setDevMes(short devMes) {
        this.devMes = devMes;
    }

    public short getDevAnio() {
        return devAnio;
    }

    public void setDevAnio(short devAnio) {
        this.devAnio = devAnio;
    }

    public Double getDevPlan() {
        return devPlan;
    }

    public void setDevPlan(Double devPlan) {
        this.devPlan = devPlan;
    }

    public Double getDevReal() {
        return devReal;
    }

    public void setDevReal(Double devReal) {
        this.devReal = devReal;
    }

    public Boolean getEditarPlan() {
        return editarPlan;
    }

    public void setEditarPlan(Boolean editarPlan) {
        this.editarPlan = editarPlan;
    }

    public Boolean getEditarReal() {
        return editarReal;
    }

    public void setEditarReal(Boolean editarReal) {
        this.editarReal = editarReal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (devPk != null ? devPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Devengado)) {
            return false;
        }
        Devengado other = (Devengado) object;
        if ((this.devPk == null && other.devPk != null) || (this.devPk != null && !this.devPk.equals(other.devPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Devengado[ devPk=" + devPk + " ]";
    }
}
