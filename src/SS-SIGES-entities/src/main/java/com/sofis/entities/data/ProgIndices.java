package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "prog_indices")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProgIndices.findAll", query = "SELECT p FROM ProgIndices p"),
    @NamedQuery(name = "ProgIndices.findByProgindPk", query = "SELECT p FROM ProgIndices p WHERE p.progindPk = :progindPk"),
    @NamedQuery(name = "ProgIndices.findByProgindRiesgoExpo", query = "SELECT p FROM ProgIndices p WHERE p.progindRiesgoExpo = :progindRiesgoExpo"),
    @NamedQuery(name = "ProgIndices.findByProgindRiesgoUltact", query = "SELECT p FROM ProgIndices p WHERE p.progindRiesgoUltact = :progindRiesgoUltact"),
    @NamedQuery(name = "ProgIndices.findByProgindRiesgoAlto", query = "SELECT p FROM ProgIndices p WHERE p.progindRiesgoAlto = :progindRiesgoAlto")})
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProgIndices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "progind_pk")
    private Integer progindPk;
    @Column(name = "progind_riesgo_expo")
    private Double progindRiesgoExpo;
    @Column(name = "progind_riesgo_ultact")
    @Temporal(TemporalType.DATE)
    private Date progindRiesgoUltact;
    @Column(name = "progind_riesgo_alto")
    private Integer progindRiesgoAlto;
    @Column(name = "progind_metodo_estado")
    private Double progindMetodologiaEstado;
    @Column(name = "progind_metodo_sin_aprobar")
    private Boolean progindMetodologiaSinAprobar;
    @Column(name = "progind_periodo_inicio")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date progindPeriodoInicio;
    @Column(name = "progind_periodo_fin")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date progindPeriodoFin;
    @Column(name = "progind_proy_actualizacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date proyActualizacion;

    @Column(name = "progind_cal_ind")
    private Double progindCalInd;
    @Column(name = "progind_cal_pend")
    private Integer progindCalPend;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "progindpreProgindFk", fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private Set<ProgIndicesPre> progIndPreSet;

    //Calculados diarios que se materializan
    //Avance
    @Column(name = "progind_avance_par_azul")
    private Integer progindAvanceParAzul;
    @Column(name = "progind_avance_par_verde")
    private Integer progindAvanceParVerde;
    @Column(name = "progind_anvance_par_rojo")
    private Integer progindAvanceParRojo;
    @Column(name = "progind_avance_fin_azul")
    private Integer progindAvanceFinAzul;
    @Column(name = "progind_avance_fin_verde")
    private Integer progindAvanceFinVerde;
    @Column(name = "progind_anvance_fin_rojo")
    private Integer progindAvanceFinRojo;
    
    @Column(name = "progind_fecha_act")
    private Date progindFechaAct;

    public ProgIndices() {
    }

    public ProgIndices(Integer progindPk) {
        this.progindPk = progindPk;
    }

    public Integer getProgindPk() {
        return progindPk;
    }

    public void setProgindPk(Integer progindPk) {
        this.progindPk = progindPk;
    }

    public Double getProgindRiesgoExpo() {
        return progindRiesgoExpo;
    }

    public void setProgindRiesgoExpo(Double progindRiesgoExpo) {
        this.progindRiesgoExpo = progindRiesgoExpo;
    }

    public Date getProgindRiesgoUltact() {
        return progindRiesgoUltact;
    }

    public void setProgindRiesgoUltact(Date progindRiesgoUltact) {
        this.progindRiesgoUltact = progindRiesgoUltact;
    }

    public Integer getProgindRiesgoAlto() {
        return progindRiesgoAlto;
    }

    public void setProgindRiesgoAlto(Integer progindRiesgoAlto) {
        this.progindRiesgoAlto = progindRiesgoAlto;
    }

    public Double getProgindMetodologiaEstado() {
        return progindMetodologiaEstado;
    }

    public void setProgindMetodologiaEstado(Double progindMetodologiaEstado) {
        this.progindMetodologiaEstado = progindMetodologiaEstado;
    }

    public Boolean getProgindMetodologiaSinAprobar() {
        return progindMetodologiaSinAprobar;
    }

    public void setProgindMetodologiaSinAprobar(Boolean progindMetodologiaSinAprobar) {
        this.progindMetodologiaSinAprobar = progindMetodologiaSinAprobar;
    }

    public Date getProgindPeriodoInicio() {
        return progindPeriodoInicio;
    }

    public void setProgindPeriodoInicio(Date progindPeriodoInicio) {
        this.progindPeriodoInicio = progindPeriodoInicio;
    }

    public Date getProgindPeriodoFin() {
        return progindPeriodoFin;
    }

    public void setProgindPeriodoFin(Date progindPeriodoFin) {
        this.progindPeriodoFin = progindPeriodoFin;
    }

    public Date getProyActualizacion() {
        return proyActualizacion;
    }

    public void setProyActualizacion(Date proyActualizacion) {
        this.proyActualizacion = proyActualizacion;
    }

    public Double getProgindCalInd() {
        return progindCalInd;
    }

    public void setProgindCalInd(Double progindCalInd) {
        this.progindCalInd = progindCalInd;
    }

    public Integer getProgindCalPend() {
        return progindCalPend;
    }

    public void setProgindCalPend(Integer progindCalPend) {
        this.progindCalPend = progindCalPend;
    }

    public Set<ProgIndicesPre> getProgIndPreSet() {
        return progIndPreSet;
    }

    public void setProgIndPreSet(Set<ProgIndicesPre> progIndPreSet) {
        this.progIndPreSet = progIndPreSet;
    }

    public Integer getProgindAvanceParAzul() {
        return progindAvanceParAzul;
    }

    public void setProgindAvanceParAzul(Integer progindAvanceParAzul) {
        this.progindAvanceParAzul = progindAvanceParAzul;
    }

    public Integer getProgindAvanceParVerde() {
        return progindAvanceParVerde;
    }

    public void setProgindAvanceParVerde(Integer progindAvanceParVerde) {
        this.progindAvanceParVerde = progindAvanceParVerde;
    }

    public Integer getProgindAvanceParRojo() {
        return progindAvanceParRojo;
    }

    public void setProgindAvanceParRojo(Integer progindAvanceParRojo) {
        this.progindAvanceParRojo = progindAvanceParRojo;
    }
    
    public Integer getProgindAvanceFinAzul() {
        return progindAvanceFinAzul;
    }

    public void setProgindAvanceFinAzul(Integer progindAvanceFinAzul) {
        this.progindAvanceFinAzul = progindAvanceFinAzul;
    }

    public Integer getProgindAvanceFinVerde() {
        return progindAvanceFinVerde;
    }

    public void setProgindAvanceFinVerde(Integer progindAvanceFinVerde) {
        this.progindAvanceFinVerde = progindAvanceFinVerde;
    }

    public Integer getProgindAvanceFinRojo() {
        return progindAvanceFinRojo;
    }

    public void setProgindAvanceFinRojo(Integer progindAvanceFinRojo) {
        this.progindAvanceFinRojo = progindAvanceFinRojo;
    }

    public Date getProgindFechaAct() {
        return progindFechaAct;
    }

    public void setProgindFechaAct(Date progindFechaAct) {
        this.progindFechaAct = progindFechaAct;
    }

    public int[] getProgindAvanceParcial() {
        int azul = progindAvanceParAzul != null ? progindAvanceParAzul : 0;
        int verde = progindAvanceParVerde != null ? progindAvanceParVerde : 0;
        int rojo = progindAvanceParRojo != null ? progindAvanceParRojo : 0;
        return new int[]{azul, verde, rojo};
    }

    public int[] getProgindAvanceFinal() {
        int azul = progindAvanceFinAzul != null ? progindAvanceFinAzul : 0;
        int verde = progindAvanceFinVerde != null ? progindAvanceFinVerde : 0;
        int rojo = progindAvanceFinRojo != null ? progindAvanceFinRojo : 0;
        return new int[]{azul, verde, rojo};
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (progindPk != null ? progindPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof ProgIndices)) {
            return false;
        }
        ProgIndices other = (ProgIndices) object;
        if ((this.progindPk == null && other.progindPk != null) || (this.progindPk != null && !this.progindPk.equals(other.progindPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ProgIndices[ progindPk=" + progindPk + " ]";
    }
}
