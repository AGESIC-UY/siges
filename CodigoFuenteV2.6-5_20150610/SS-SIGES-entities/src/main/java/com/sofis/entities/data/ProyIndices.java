package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "proy_indices")
@XmlRootElement
public class ProyIndices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proyind_pk")
    private Integer proyindPk;
    @Column(name = "proyind_riesgo_expo")
    private Double proyindRiesgoExpo;
    @Column(name = "proyind_riesgo_ultact")
    @Temporal(TemporalType.DATE)
    private Date proyindRiesgoUltact;
    @Column(name = "proyind_riesgo_alto")
    private Integer proyindRiesgoAlto;
    @Column(name = "proyind_metodo_estado")
    private Double proyindMetodologiaEstado;
    @Column(name = "proyind_metodo_sin_aprobar")
    private Boolean proyindMetodologiaSinAprobar;
    @Column(name = "proyind_periodo_inicio")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date proyindPeriodoInicio;
    @Column(name = "proyind_periodo_fin")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date proyindPeriodoFin;
    @Column(name = "proyind_porc_peso_total")
    private Double proyindPorcPesoTotal;
    
    @Column(name = "proyind_cal_ind")
    private Double proyindCalInd;
    @Column(name = "proyind_cal_pend")
    private Integer proyindCalPend;

    public ProyIndices() {
    }

    public ProyIndices(Integer proyindPk) {
        this.proyindPk = proyindPk;
    }

    public Integer getProyindPk() {
        return proyindPk;
    }

    public void setProyindPk(Integer proyindPk) {
        this.proyindPk = proyindPk;
    }

    public Double getProyindRiesgoExpo() {
        return proyindRiesgoExpo;
    }

    public void setProyindRiesgoExpo(Double proyindRiesgoExpo) {
        this.proyindRiesgoExpo = proyindRiesgoExpo;
    }

    public Date getProyindRiesgoUltact() {
        return proyindRiesgoUltact;
    }

    public void setProyindRiesgoUltact(Date proyindRiesgoUltact) {
        this.proyindRiesgoUltact = proyindRiesgoUltact;
    }

    public Integer getProyindRiesgoAlto() {
        return proyindRiesgoAlto;
    }

    public void setProyindRiesgoAlto(Integer proyindRiesgoAlto) {
        this.proyindRiesgoAlto = proyindRiesgoAlto;
    }

    public Double getProyindMetodologiaEstado() {
        return proyindMetodologiaEstado;
    }

    public void setProyindMetodologiaEstado(Double proyindMetodologiaEstado) {
        this.proyindMetodologiaEstado = proyindMetodologiaEstado;
    }

    public Boolean getProyindMetodologiaSinAprobar() {
        return proyindMetodologiaSinAprobar;
    }

    public void setProyindMetodologiaSinAprobar(Boolean proyindMetodologiaSinAprobar) {
        this.proyindMetodologiaSinAprobar = proyindMetodologiaSinAprobar;
    }

    public Date getProyindPeriodoInicio() {
        return proyindPeriodoInicio;
    }

    public void setProyindPeriodoInicio(Date proyindPeriodoInicio) {
        this.proyindPeriodoInicio = proyindPeriodoInicio;
    }

    public Date getProyindPeriodoFin() {
        return proyindPeriodoFin;
    }

    public void setProyindPeriodoFin(Date proyindPeriodoFin) {
        this.proyindPeriodoFin = proyindPeriodoFin;
    }

    public Double getProyindPorcPesoTotal() {
        return proyindPorcPesoTotal;
    }

    public void setProyindPorcPesoTotal(Double proyindPorcPesoTotal) {
        this.proyindPorcPesoTotal = proyindPorcPesoTotal;
    }

    public Double getProyindCalInd() {
        return proyindCalInd;
    }

    public void setProyindCalInd(Double proyindCalInd) {
        this.proyindCalInd = proyindCalInd;
    }

    public Integer getProyindCalPend() {
        return proyindCalPend;
    }

    public void setProyindCalPend(Integer proyindCalPend) {
        this.proyindCalPend = proyindCalPend;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proyindPk != null ? proyindPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyIndices)) {
            return false;
        }
        ProyIndices other = (ProyIndices) object;
        if ((this.proyindPk == null && other.proyindPk != null) || (this.proyindPk != null && !this.proyindPk.equals(other.proyindPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ProyIndices[ proyindPk=" + proyindPk + " ]";
    }
}
