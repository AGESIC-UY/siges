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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "organismos")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Organismos.findAll", query = "SELECT o FROM Organismos o ORDER BY o.orgNombre"),
    @NamedQuery(name = "Organismos.findAllActivos", query = "SELECT o FROM Organismos o WHERE o.orgActivo = :activo ORDER BY o.orgNombre"),
    @NamedQuery(name = "Organismos.findByToken", query = "SELECT o FROM Organismos o WHERE o.orgActivo = TRUE AND o.orgToken = :token"),
    @NamedQuery(name = "Organismos.findByCronogramaPk", query = "SELECT p.proyOrgFk FROM Proyectos p WHERE p.proyCroFk.croPk = :cronogramaPk"),
    @NamedQuery(name = "Organismos.findByProyectoPk", query = "SELECT p.proyOrgFk FROM Proyectos p WHERE p.proyPk = :proyectoPk"),
})
public class Organismos implements Serializable {
    
    private static final long serialVersionUID = 1L;
	
    public static final int NOMBRE_LENGHT = 45;
    public static final int LOGO_NOMBRE_LENGHT = 45;
    public static final int DIRECCION_LENGHT = 45;
    public static final int TOKEN_LENGHT = 100;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_pk")
    private Integer orgPk;
	
    @Column(name = "org_nombre")
    private String orgNombre;
    
	@Column(name = "org_logo_nombre")
    private String orgLogoNombre;
    
	@Column(name = "org_direccion")
    private String orgDireccion;

    @Column(name = "org_activo")
    private Boolean orgActivo;
    
    @Transient
    private byte[] orgLogo;

    @Column(name = "org_token")
    private String orgToken;

    @Column(name = "org_activo_siges_ind")
    private Boolean activoSigesIndicadores;

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

	public Boolean getActivoSigesIndicadores() {
		return activoSigesIndicadores;
	}

	public void setActivoSigesIndicadores(Boolean activoSigesIndicadores) {
		this.activoSigesIndicadores = activoSigesIndicadores;
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

}
