package com.sofis.entities.data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.OrderBy;

/**
 *
 * @author sofis
 */
@Entity
@Table(name = "plantilla_cronograma")
@XmlRootElement
public class PlantillaCronograma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "p_crono_pk")
    private Integer pcronoPk;
    @Size(max = 845)
    @Column(name = "p_crono_nombre")
    private String pcronoNombre;
    @OneToMany(mappedBy = "pentregablePCroFk", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PlantillaEntregables> plantillaEntregablesSet;
    @JoinColumn(name = "p_crono_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos orgFk;
    @Column(name = "activo")
    private Boolean activo;

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public PlantillaCronograma() {
    }

    public Integer getPcronoPk() {
        return pcronoPk;
    }

    public void setPcronoPk(Integer pcronoPk) {
        this.pcronoPk = pcronoPk;
    }

    public String getPcronoNombre() {
        return pcronoNombre;
    }

    public void setPcronoNombre(String pcronoNombre) {
        this.pcronoNombre = pcronoNombre;
    }

    public List<PlantillaEntregables> getPlantillaEntregablesSet() {
        return plantillaEntregablesSet;
    }

    public void setPlantillaEntregablesSet(List<PlantillaEntregables> plantillaEntregablesSet) {
        this.plantillaEntregablesSet = plantillaEntregablesSet;
    }

    public Organismos getOrgFk() {
        return orgFk;
    }

    public void setOrgFk(Organismos orgFk) {
        this.orgFk = orgFk;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlantillaCronograma)) {
            return false;
        }
        PlantillaCronograma other = (PlantillaCronograma) object;
        if ((this.pcronoPk == null && other.pcronoPk != null) || (this.pcronoPk != null && !this.pcronoPk.equals(other.pcronoPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.PlantillaCronograma[ pCronoPk=" + pcronoPk + " ]";
    }
}
