package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "area_organi_int_prove")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AreaOrganiIntProve.findAll", query = "SELECT a FROM AreaOrganiIntProve a")})
public class AreaOrganiIntProve implements Serializable {
    
    public static final int NOMBRE_LENGHT = 40;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "areaorgintprov_pk")
    private Integer areaorgintprovPk;
    @Column(name = "areaorgintprov_nombre")
    private String areaorgintprovNombre;
    @JoinColumn(name = "areaorgintprov_orga_fk", referencedColumnName = "orga_pk")
    @ManyToOne(optional = false)
    private OrganiIntProve areaorgintprovOrgaFk;
    @JoinColumn(name = "areaorgintprov_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos areaorgintprovOrgFk;

    public AreaOrganiIntProve() {
    }

    public AreaOrganiIntProve(Integer areaorgintprovPk) {
        this.areaorgintprovPk = areaorgintprovPk;
    }

    public Integer getAreaorgintprovPk() {
        return areaorgintprovPk;
    }

    public void setAreaorgintprovPk(Integer areaorgintprovPk) {
        this.areaorgintprovPk = areaorgintprovPk;
    }

    public String getAreaorgintprovNombre() {
        return areaorgintprovNombre;
    }

    public void setAreaorgintprovNombre(String areaorgintprovNombre) {
        this.areaorgintprovNombre = areaorgintprovNombre;
    }

    /* public OrganiIntProve getAreaorgintprovOrgaFk() {
     return areaorgintprovOrgaFk;
     }

     public void setAreaorgintprovOrgaFk(OrganiIntProve areaorgintprovOrgaFk) {
     this.areaorgintprovOrgaFk = areaorgintprovOrgaFk;
     }*/
    public Organismos getAreaorgintprovOrgFk() {
        return areaorgintprovOrgFk;
    }

    public void setAreaorgintprovOrgFk(Organismos areaorgintprovOrgFk) {
        this.areaorgintprovOrgFk = areaorgintprovOrgFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (areaorgintprovPk != null ? areaorgintprovPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AreaOrganiIntProve)) {
            return false;
        }
        AreaOrganiIntProve other = (AreaOrganiIntProve) object;
        if ((this.areaorgintprovPk == null && other.areaorgintprovPk != null) 
                || (this.areaorgintprovPk != null && !this.areaorgintprovPk.equals(other.areaorgintprovPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.AreaOrganiIntProve[ areaorgintprovPk=" + areaorgintprovPk + " ]";
    }
}
