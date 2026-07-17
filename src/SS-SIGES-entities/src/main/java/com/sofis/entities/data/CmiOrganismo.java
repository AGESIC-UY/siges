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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "cmi_organismos")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "CmiOrganismo.findAll", query = "SELECT c FROM CmiOrganismo c ORDER BY c.orgNombre"),
    @NamedQuery(name = "CmiOrganismo.findActivos", query = "SELECT c FROM CmiOrganismo c WHERE c.orgActivo = TRUE ORDER BY c.orgNombre"),
    @NamedQuery(name = "CmiOrganismo.findByPk", query = "SELECT c FROM CmiOrganismo c WHERE c.orgPk = :orgPk")
})
public class CmiOrganismo implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes de longitud
    public static final int ORGANISMOS_LENGTH = 20;
    public static final int NOMBRE_LENGTH = 50;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_pk")
    private Integer orgPk;

    @Column(name = "org_organismos", length = ORGANISMOS_LENGTH)
    private String orgOrganismos;

    @Column(name = "org_nombre", length = NOMBRE_LENGTH)
    private String orgNombre;

    @Column(name = "org_cant_orgs")
    private Integer orgCantOrgs;

    @Column(name = "org_virtual")
    private Boolean orgVirtual;

    @Column(name = "org_activo")
    private Boolean orgActivo;

    public CmiOrganismo() {
    }

    public CmiOrganismo(Integer orgPk) {
        this.orgPk = orgPk;
    }

    // Getters y setters

    public Integer getOrgPk() {
        return orgPk;
    }

    public void setOrgPk(Integer orgPk) {
        this.orgPk = orgPk;
    }

    public String getOrgOrganismos() {
        return orgOrganismos;
    }

    public void setOrgOrganismos(String orgOrganismos) {
        this.orgOrganismos = orgOrganismos;
    }

    public String getOrgNombre() {
        return orgNombre;
    }

    public void setOrgNombre(String orgNombre) {
        this.orgNombre = orgNombre;
    }

    public Integer getOrgCantOrgs() {
        return orgCantOrgs;
    }

    public void setOrgCantOrgs(Integer orgCantOrgs) {
        this.orgCantOrgs = orgCantOrgs;
    }

    public Boolean getOrgVirtual() {
        return orgVirtual;
    }

    public void setOrgVirtual(Boolean orgVirtual) {
        this.orgVirtual = orgVirtual;
    }

    public Boolean getOrgActivo() {
        return orgActivo;
    }

    public void setOrgActivo(Boolean orgActivo) {
        this.orgActivo = orgActivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orgPk != null ? orgPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CmiOrganismo)) {
            return false;
        }
        CmiOrganismo other = (CmiOrganismo) object;
        if ((this.orgPk == null && other.orgPk != null) || 
            (this.orgPk != null && !this.orgPk.equals(other.orgPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CmiOrganismo{" + "orgPk=" + orgPk + ", orgOrganismos=" + orgOrganismos + ", orgNombre=" + orgNombre + ", orgCantOrgs=" + orgCantOrgs + ", orgVirtual=" + orgVirtual + ", orgActivo=" + orgActivo + '}';
    }

    
}
