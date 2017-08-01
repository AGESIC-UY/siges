package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "organismos")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Organismos.findAll", query = "SELECT o FROM Organismos o ORDER BY o.orgNombre"),
    @NamedQuery(name = "Organismos.findAllActivos", query = "SELECT o FROM Organismos o WHERE o.orgActivo = :activo ORDER BY o.orgNombre"),
    @NamedQuery(name = "Organismos.findByToken", query = "SELECT o FROM Organismos o WHERE o.orgActivo = TRUE AND o.orgToken = :token")
})
public class Organismos implements Serializable {
    
    public static final int NOMBRE_LENGHT = 45;
    public static final int LOGO_NOMBRE_LENGHT = 45;
    public static final int DIRECCION_LENGHT = 45;
    public static final int TOKEN_LENGHT = 100;

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
//    @Column(name = "org_logo")
//    private byte[] orgLogo;
    @Column(name = "org_direccion")
    private String orgDireccion;

    @Column(name = "org_activo")
    private Boolean orgActivo;
    
    
    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objEstOrgFk")
    @Fetch(FetchMode.SELECT)
    private Set<ObjetivoEstrategico> objetivoEstrategicos;
    
    @Transient
    private byte[] orgLogo;

    /**
     * Token que identifica los organismos de cada Siges en el Visualizador.
     */
    @Column(name = "org_token")
    private String orgToken;

    public Organismos() {
    }

    public Organismos(Integer orgPk) {
        this.orgPk = orgPk;
    }

    public Organismos(Integer orgPk, String orgNombre) {
        this.orgPk = orgPk;
        this.orgNombre = orgNombre;
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
    
    public Boolean getOrgActivo() {
        return orgActivo;
    }

    public void setOrgActivo(Boolean orgActivo) {
        this.orgActivo = orgActivo;
    }

    public String getOrgToken() {
        return orgToken;
    }

    public void setOrgToken(String orgToken) {
        this.orgToken = orgToken;
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
        return this.getClass().getName()+"[ orgPk=" + orgPk + " ]";
    }

    public Organismos newCopy() {
        Organismos o = new Organismos();
        o.setOrgPk(orgPk);
        o.setOrgNombre(orgNombre);
        o.setOrgDireccion(orgDireccion);
        o.setOrgToken(orgToken);
        o.setOrgLogo(orgLogo);
        o.setOrgLogoNombre(orgLogoNombre);
        o.setOrgActivo(orgActivo);
        return o;
    }

    /**
     * @return the objetivoEstrategicos
     */
    public Set<ObjetivoEstrategico> getObjetivoEstrategicos() {
        return objetivoEstrategicos;
    }

    /**
     * @param objetivoEstrategicos the objetivoEstrategicos to set
     */
    public void setObjetivoEstrategicos(Set<ObjetivoEstrategico> objetivoEstrategicos) {
        this.objetivoEstrategicos = objetivoEstrategicos;
    }
}
