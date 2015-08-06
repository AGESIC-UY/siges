package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sofis
 */
@Entity
@Table(name = "plantilla_entregables")
@XmlRootElement

public class PlantillaEntregables implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "p_entregables_id")
    private Integer pentregablesId;
    @Column(name = "p_entregables_numero")
    private Integer pentregablesNumero;
    @Size(max = 1000)
    @Column(name = "p_entregables_nombre")
    private String pentregablesNombre;
    @Column(name = "p_entregable_nivel")
    private Integer pentregableNivel;
    @Column(name = "p_entregable_esfuerzo")
    private Integer pentregableEsfuerzo;
    @Column(name = "p_entregable_duracion")
    private Integer pentregableDuracion;
    @JoinColumn(name = "p_entregable_p_cro_fk", referencedColumnName = "p_crono_pk")
    @ManyToOne
    private PlantillaCronograma pentregablePCroFk;
    @JoinColumn(name = "p_entregable_ant_fk", referencedColumnName = "p_entregables_id")
    @ManyToOne
    private PlantillaEntregables pentregableAntFk;
    
    @Transient
    private Boolean nuevo = false;
    
    //numero logico del antecesor
    @Transient
    private Integer pentregablesNumeroAnt;
    

    public PlantillaEntregables() {
        
    }

    public Integer getPentregablesNumeroAnt() {
        return pentregablesNumeroAnt;
    }

    public void setPentregablesNumeroAnt(Integer pentregablesNumeroAnt) {
        this.pentregablesNumeroAnt = pentregablesNumeroAnt;
    }

    public Boolean getNuevo() {
        return nuevo;
    }

    public void setNuevo(Boolean nuevo) {
        this.nuevo = nuevo;
    }

    public Integer getPentregablesNumero() {
        return pentregablesNumero;
    }

    public void setPentregablesNumero(Integer pentregablesNumero) {
        this.pentregablesNumero = pentregablesNumero;
    }

    public Integer getPentregablesId() {
        return pentregablesId;
    }

    public void setPentregablesId(Integer pentregablesId) {
        this.pentregablesId = pentregablesId;
    }

    public String getPentregablesNombre() {
        return pentregablesNombre;
    }

    public void setPentregablesNombre(String pentregablesNombre) {
        this.pentregablesNombre = pentregablesNombre;
    }

    public Integer getPentregableNivel() {
        return pentregableNivel;
    }

    public void setPentregableNivel(Integer pentregableNivel) {
        this.pentregableNivel = pentregableNivel;
    }

    public Integer getPentregableEsfuerzo() {
        return pentregableEsfuerzo;
    }

    public void setPentregableEsfuerzo(Integer pentregableEsfuerzo) {
        this.pentregableEsfuerzo = pentregableEsfuerzo;
    }

    public Integer getPentregableDuracion() {
        return pentregableDuracion;
    }

    public void setPentregableDuracion(Integer pentregableDuracion) {
        this.pentregableDuracion = pentregableDuracion;
    }

    public PlantillaCronograma getPentregablePCroFk() {
        return pentregablePCroFk;
    }

    public void setPentregablePCroFk(PlantillaCronograma pentregablePCroFk) {
        this.pentregablePCroFk = pentregablePCroFk;
    }

    public PlantillaEntregables getPentregableAntFk() {
        return pentregableAntFk;
    }

    public void setPentregableAntFk(PlantillaEntregables pentregableAntFk) {
        this.pentregableAntFk = pentregableAntFk;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pentregablesId != null ? pentregablesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlantillaEntregables)) {
            return false;
        }
        PlantillaEntregables other = (PlantillaEntregables) object;
        if ((this.pentregablesId == null && other.pentregablesId != null) || (this.pentregablesId != null && !this.pentregablesId.equals(other.pentregablesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.PlantillaEntregables[ pEntregablesId=" + pentregablesId + " ]";
    }
    
}
