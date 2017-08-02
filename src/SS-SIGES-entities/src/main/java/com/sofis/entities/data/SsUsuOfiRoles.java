package com.sofis.entities.data;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 *
 * @author Usuario
 */
@Entity
@Audited
@Table(name = "ss_usu_ofi_roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsUsuOfiRoles.findAll", query = "SELECT s FROM SsUsuOfiRoles s"),
    @NamedQuery(name = "SsUsuOfiRoles.findByUsuOfiRolesId", query = "SELECT s FROM SsUsuOfiRoles s WHERE s.usuOfiRolesId = :usuOfiRolesId"),
    @NamedQuery(name = "SsUsuOfiRoles.findByUsuOfiRolesOrigen", query = "SELECT s FROM SsUsuOfiRoles s WHERE s.usuOfiRolesOrigen = :usuOfiRolesOrigen"),
    @NamedQuery(name = "SsUsuOfiRoles.findByUsuOfiRolesUserCode", query = "SELECT s FROM SsUsuOfiRoles s WHERE s.usuOfiRolesUserCode = :usuOfiRolesUserCode")})
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SsUsuOfiRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usu_ofi_roles_id", nullable = false)
    private Integer usuOfiRolesId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "usu_ofi_roles_origen", length = 255)
    private String usuOfiRolesOrigen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usu_ofi_roles_user_code")
    private int usuOfiRolesUserCode;
    @JoinColumn(name = "usu_ofi_roles_rol", referencedColumnName = "rol_id")
    @ManyToOne(optional = false)
    private SsRol usuOfiRolesRol;
    @JoinColumn(name = "usu_ofi_roles_usuario", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private SsUsuario usuOfiRolesUsuario;
    @JoinColumn(name = "usu_ofi_roles_oficina", referencedColumnName = "ofi_id")
    @ManyToOne(optional = true)
    private SsOficina usuOfiRolesOficina;
    @JoinColumn(name = "usu_ofi_roles_usu_area", referencedColumnName = "area_pk")
    @OneToOne(optional = true)
    @NotAudited
    private Areas usuOfiRolesUsuArea;
    @Column(name = "usu_ofi_roles_activo")
    private Boolean usuOfiRolesActivo;

    public SsUsuOfiRoles() {
        this.usuOfiRolesActivo = true;
    }

    public SsUsuOfiRoles(Integer usuOfiRolesId) {
        this.usuOfiRolesId = usuOfiRolesId;
        this.usuOfiRolesActivo = true;
    }

    public SsUsuOfiRoles(Integer usuOfiRolesId, String usuOfiRolesOrigen, int usuOfiRolesUserCode) {
        this.usuOfiRolesId = usuOfiRolesId;
        this.usuOfiRolesOrigen = usuOfiRolesOrigen;
        this.usuOfiRolesUserCode = usuOfiRolesUserCode;
        this.usuOfiRolesActivo = true;
    }

    public SsUsuOfiRoles(String usuOfiRolesOrigen, int usuOfiRolesUserCode, SsRol usuOfiRolesRol, SsUsuario usuOfiRolesUsuario, SsOficina usuOfiRolesOficina) {
        this.usuOfiRolesOrigen = usuOfiRolesOrigen;
        this.usuOfiRolesUserCode = usuOfiRolesUserCode;
        this.usuOfiRolesRol = usuOfiRolesRol;
        this.usuOfiRolesUsuario = usuOfiRolesUsuario;
        this.usuOfiRolesOficina = usuOfiRolesOficina;
        this.usuOfiRolesActivo = true;
    }

    public Integer getUsuOfiRolesId() {
        return usuOfiRolesId;
    }

    public void setUsuOfiRolesId(Integer usuOfiRolesId) {
        this.usuOfiRolesId = usuOfiRolesId;
    }

    public String getUsuOfiRolesOrigen() {
        return usuOfiRolesOrigen;
    }

    public void setUsuOfiRolesOrigen(String usuOfiRolesOrigen) {
        this.usuOfiRolesOrigen = usuOfiRolesOrigen;
    }

    public int getUsuOfiRolesUserCode() {
        return usuOfiRolesUserCode;
    }

    public void setUsuOfiRolesUserCode(int usuOfiRolesUserCode) {
        this.usuOfiRolesUserCode = usuOfiRolesUserCode;
    }

    public SsRol getUsuOfiRolesRol() {
        return usuOfiRolesRol;
    }

    public void setUsuOfiRolesRol(SsRol usuOfiRolesRol) {
        this.usuOfiRolesRol = usuOfiRolesRol;
    }

    public SsUsuario getUsuOfiRolesUsuario() {
        return usuOfiRolesUsuario;
    }

    public void setUsuOfiRolesUsuario(SsUsuario usuOfiRolesUsuario) {
        this.usuOfiRolesUsuario = usuOfiRolesUsuario;
    }

    public SsOficina getUsuOfiRolesOficina() {
        return usuOfiRolesOficina;
    }

    public void setUsuOfiRolesOficina(SsOficina usuOfiRolesOficina) {
        this.usuOfiRolesOficina = usuOfiRolesOficina;
    }

    public Areas getUsuOfiRolesUsuArea() {
        return usuOfiRolesUsuArea;
    }

    public void setUsuOfiRolesUsuArea(Areas usuOfiRolesUsuArea) {
        this.usuOfiRolesUsuArea = usuOfiRolesUsuArea;
    }

    public Boolean getUsuOfiRolesActivo() {
        return usuOfiRolesActivo;
    }

    public void setUsuOfiRolesActivo(Boolean usuOfiRolesActivo) {
        this.usuOfiRolesActivo = usuOfiRolesActivo;
    }

    public boolean isUsuOfiRolesActivo() {
        return usuOfiRolesActivo != null ? usuOfiRolesActivo : false;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuOfiRolesId != null ? usuOfiRolesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SsUsuOfiRoles)) {
            return false;
        }
        SsUsuOfiRoles other = (SsUsuOfiRoles) object;
        if ((this.usuOfiRolesId == null && other.usuOfiRolesId != null) || (this.usuOfiRolesId != null && !this.usuOfiRolesId.equals(other.usuOfiRolesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.gestemp.entities.SsUsuOfiRoles[ usuOfiRolesId=" + usuOfiRolesId + " ]";
    }
}
