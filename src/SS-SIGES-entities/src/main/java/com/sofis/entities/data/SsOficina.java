package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

/**
 *
 * @author Usuario
 */
@Entity
@Audited
@Table(name = "ss_oficina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsOficina.findAll", query = "SELECT s FROM SsOficina s"),
    @NamedQuery(name = "SsOficina.findByOfiId", query = "SELECT s FROM SsOficina s WHERE s.ofiId = :ofiId"),
    @NamedQuery(name = "SsOficina.findByOfiFechaCreacion", query = "SELECT s FROM SsOficina s WHERE s.ofiFechaCreacion = :ofiFechaCreacion"),
    @NamedQuery(name = "SsOficina.findByOfiNombre", query = "SELECT s FROM SsOficina s WHERE s.ofiNombre = :ofiNombre"),
    @NamedQuery(name = "SsOficina.findByOfiOrigen", query = "SELECT s FROM SsOficina s WHERE s.ofiOrigen = :ofiOrigen"),
    @NamedQuery(name = "SsOficina.findByOfiUsuario", query = "SELECT s FROM SsOficina s WHERE s.ofiUsuario = :ofiUsuario"),
    @NamedQuery(name = "SsOficina.findByOfiVersion", query = "SELECT s FROM SsOficina s WHERE s.ofiVersion = :ofiVersion")})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SsOficina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ofi_id", nullable = false)
    private Integer ofiId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ofi_fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ofiFechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ofi_nombre", nullable = false, length = 255)
    private String ofiNombre;

    @Column(name = "ofi_activo")
    private Boolean ofiActivo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ofi_origen", nullable = false, length = 255)
    private String ofiOrigen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ofi_usuario", nullable = false)
    private int ofiUsuario;
    @Column(name = "ofi_version")
    private Integer ofiVersion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuOfiRolesOficina")
    private Collection<SsUsuOfiRoles> ssUsuOfiRolesCollection;

    public SsOficina(Integer ofiId) {
        this.ofiId = ofiId;
    }

    public SsOficina(Integer ofiId, Date ofiFechaCreacion, String ofiNombre, String ofiOrigen, int ofiUsuario) {
        this.ofiId = ofiId;
        this.ofiFechaCreacion = ofiFechaCreacion;
        this.ofiNombre = ofiNombre;
        this.ofiOrigen = ofiOrigen;
        this.ofiUsuario = ofiUsuario;
    }

    public Integer getOfiId() {
        return ofiId;
    }

    public void setOfiId(Integer ofiId) {
        this.ofiId = ofiId;
    }

    public Date getOfiFechaCreacion() {
        return ofiFechaCreacion;
    }

    public void setOfiFechaCreacion(Date ofiFechaCreacion) {
        this.ofiFechaCreacion = ofiFechaCreacion;
    }

    public String getOfiNombre() {
        return ofiNombre;
    }

    public void setOfiNombre(String ofiNombre) {
        this.ofiNombre = ofiNombre;
    }

    public Boolean getOfiActivo() {
        return ofiActivo;
    }

    public boolean isOfiActivo() {
        return ofiActivo != null ? ofiActivo : false;
    }

    public void setOfiActivo(Boolean ofiActivo) {
        this.ofiActivo = ofiActivo;
    }

    public String getOfiOrigen() {
        return ofiOrigen;
    }

    public void setOfiOrigen(String ofiOrigen) {
        this.ofiOrigen = ofiOrigen;
    }

    public int getOfiUsuario() {
        return ofiUsuario;
    }

    public void setOfiUsuario(int ofiUsuario) {
        this.ofiUsuario = ofiUsuario;
    }

    public Integer getOfiVersion() {
        return ofiVersion;
    }

    public void setOfiVersion(Integer ofiVersion) {
        this.ofiVersion = ofiVersion;
    }

    @XmlTransient
    public Collection<SsUsuOfiRoles> getSsUsuOfiRolesCollection() {
        return ssUsuOfiRolesCollection;
    }

    public void setSsUsuOfiRolesCollection(Collection<SsUsuOfiRoles> ssUsuOfiRolesCollection) {
        this.ssUsuOfiRolesCollection = ssUsuOfiRolesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ofiId != null ? ofiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SsOficina)) {
            return false;
        }
        SsOficina other = (SsOficina) object;
        if ((this.ofiId == null && other.ofiId != null) || (this.ofiId != null && !this.ofiId.equals(other.ofiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.gestemp.entities.SsOficina[ ofiId=" + ofiId + " ]";
    }
    
}
