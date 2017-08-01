package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "organi_int_prove")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrganiIntProve.findAll", query = "SELECT o FROM OrganiIntProve o")})
public class OrganiIntProve implements Serializable {
    
    public static final int NOMBRE_LENGHT = 50;
    public static final int RAZON_SOCIAL_LENGHT = 50;
    public static final int RUT_LENGHT = 45;
    public static final int MAIL_LENGHT = 45;
    public static final int TELEFONO_LENGHT = 45;
    public static final int WEB_LENGHT = 45;
    public static final int DIRECCION_LENGHT = 100;
    public static final int AMBITO_LENGHT = 45;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "orga_pk")
    private Integer orgaPk;
    @Column(name = "orga_nombre")
    private String orgaNombre;
    @Column(name = "orga_proveedor")
    private Boolean orgaProveedor;
    @Column(name = "orga_razon_social")
    private String orgaRazonSocial;
    @Column(name = "orga_rut")
    private String orgaRut;
    @Column(name = "orga_mail")
    private String orgaMail;
    @Column(name = "orga_telefono")
    private String orgaTelefono;
    @Column(name = "orga_web")
    private String orgaWeb;
    @Column(name = "orga_direccion")
    private String orgaDireccion;
    @Column(name = "orga_ambito")
    private String orgaAmbito;
    
    @JoinColumn(name = "orga_amb_fk", referencedColumnName = "amb_pk")
    @ManyToOne(optional = true)
    private Ambito orgaAmbFk;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaorgintprovOrgaFk")
    @Fetch(FetchMode.SELECT)
    private Set<AreaOrganiIntProve> areaOrganiIntProveSet;
    @JoinColumn(name = "orga_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos orgaOrgFk;
    
    public OrganiIntProve() {
    }

    public OrganiIntProve(Integer orgaPk) {
        this.orgaPk = orgaPk;
    }

    public Integer getOrgaPk() {
        return orgaPk;
    }

    public void setOrgaPk(Integer orgaPk) {
        this.orgaPk = orgaPk;
    }

    public String getOrgaNombre() {
        return orgaNombre;
    }

    public void setOrgaNombre(String orgaNombre) {
        this.orgaNombre = orgaNombre;
    }

    public Boolean getOrgaProveedor() {
        return orgaProveedor;
    }

    public void setOrgaProveedor(Boolean orgaProveedor) {
        this.orgaProveedor = orgaProveedor;
    }

    public String getOrgaRazonSocial() {
        return orgaRazonSocial;
    }

    public void setOrgaRazonSocial(String orgaRazonSocial) {
        this.orgaRazonSocial = orgaRazonSocial;
    }

    public String getOrgaRut() {
        return orgaRut;
    }

    public void setOrgaRut(String orgaRut) {
        this.orgaRut = orgaRut;
    }

    public String getOrgaMail() {
        return orgaMail;
    }

    public void setOrgaMail(String orgaMail) {
        this.orgaMail = orgaMail;
    }

    public String getOrgaTelefono() {
        return orgaTelefono;
    }

    public void setOrgaTelefono(String orgaTelefono) {
        this.orgaTelefono = orgaTelefono;
    }

    public String getOrgaWeb() {
        return orgaWeb;
    }

    public void setOrgaWeb(String orgaWeb) {
        this.orgaWeb = orgaWeb;
    }

    public String getOrgaDireccion() {
        return orgaDireccion;
    }

    public void setOrgaDireccion(String orgaDireccion) {
        this.orgaDireccion = orgaDireccion;
    }

    public String getOrgaAmbito() {
        return orgaAmbito;
    }

    public void setOrgaAmbito(String orgaAmbito) {
        this.orgaAmbito = orgaAmbito;
    }

    public Ambito getOrgaAmbFk() {
        return orgaAmbFk;
    }

    public void setOrgaAmbFk(Ambito orgaAmbFk) {
        this.orgaAmbFk = orgaAmbFk;
    }

    @XmlTransient
    public Set<AreaOrganiIntProve> getAreaOrganiIntProveSet() {
        return areaOrganiIntProveSet;
    }

    public void setAreaOrganiIntProveSet(Set<AreaOrganiIntProve> areaOrganiIntProveSet) {
        this.areaOrganiIntProveSet = areaOrganiIntProveSet;
    }

    public Organismos getOrgaOrgFk() {
        return orgaOrgFk;
    }

    public void setOrgaOrgFk(Organismos orgaOrgFk) {
        this.orgaOrgFk = orgaOrgFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orgaPk != null ? orgaPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OrganiIntProve)) {
            return false;
        }
        OrganiIntProve other = (OrganiIntProve) object;
        if ((this.orgaPk == null && other.orgaPk != null) || (this.orgaPk != null && !this.orgaPk.equals(other.orgaPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.OrganiIntProve[ orgaPk=" + orgaPk + " ]";
    }
    
}
