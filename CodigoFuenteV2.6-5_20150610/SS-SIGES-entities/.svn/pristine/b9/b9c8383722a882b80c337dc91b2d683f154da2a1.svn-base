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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "organismos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organismos.findAll", query = "SELECT o FROM Organismos o")})
public class Organismos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_pk")
    private Integer orgPk;
    @Column(name = "org_nombre")
    private String orgNombre;
    @Column(name = "org_logo_nombre")
    private String orgLogoNombre;
    @Column(name = "org_logo")
    private byte[] orgLogo;
    @Column(name = "org_direccion")
    private String orgDireccion;

    public Organismos() {
    }

    public Organismos(Integer orgPk) {
        this.orgPk = orgPk;
    }

    public Organismos(Integer orgPk, String nombre) {
        this.orgPk = orgPk;
        this.orgNombre = nombre;
    }

    public Integer getOrgPk() {
        return orgPk;
    }

    public void setOrgPk(Integer orgPk) {
        this.orgPk = orgPk;
    }

    public String getOrgNombre() {
        return orgNombre;
    }

    public void setOrgNombre(String orgNombre) {
        this.orgNombre = orgNombre;
    }

    public String getOrgLogoNombre() {
        return orgLogoNombre;
    }

    public void setOrgLogoNombre(String orgLogoNombre) {
        this.orgLogoNombre = orgLogoNombre;
    }

    public byte[] getOrgLogo() {
        return orgLogo;
    }

    public void setOrgLogo(byte[] orgLogo) {
        this.orgLogo = orgLogo;
    }

    public String getOrgDireccion() {
        return orgDireccion;
    }

    public void setOrgDireccion(String orgDireccion) {
        this.orgDireccion = orgDireccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orgPk != null ? orgPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Organismos)) {
            return false;
        }
        Organismos other = (Organismos) object;
        if ((this.orgPk == null && other.orgPk != null) || (this.orgPk != null && !this.orgPk.equals(other.orgPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Organismos[ orgPk=" + orgPk + " ]";
    }

    public Organismos newCopy() {
        Organismos o = new Organismos();
        o.setOrgPk(orgPk);
        o.setOrgNombre(orgNombre);
        o.setOrgDireccion(orgDireccion);
        o.setOrgLogo(orgLogo);
        o.setOrgLogoNombre(orgLogoNombre);
        return o;
    }
}
