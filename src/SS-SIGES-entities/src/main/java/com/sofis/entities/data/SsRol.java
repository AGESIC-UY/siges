package com.sofis.entities.data;

import com.sofis.generico.utils.generalutils.StringsUtils;
import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Usuario
 */
@Entity
@Audited
@Table(name = "ss_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsRol.findAll", query = "SELECT s FROM SsRol s"),
    @NamedQuery(name = "SsRol.findByRolId", query = "SELECT s FROM SsRol s WHERE s.rolId = :rolId"),
    @NamedQuery(name = "SsRol.findByRolCod", query = "SELECT s FROM SsRol s WHERE s.rolCod = :rolCod"),
    @NamedQuery(name = "SsRol.findByRolNombre", query = "SELECT s FROM SsRol s WHERE s.rolNombre = :rolNombre"),
    @NamedQuery(name = "SsRol.findByRolOrigen", query = "SELECT s FROM SsRol s WHERE s.rolOrigen = :rolOrigen"),
    @NamedQuery(name = "SsRol.findByRolUserCode", query = "SELECT s FROM SsRol s WHERE s.rolUserCode = :rolUserCode"),
    @NamedQuery(name = "SsRol.findByRolVersion", query = "SELECT s FROM SsRol s WHERE s.rolVersion = :rolVersion"),
    @NamedQuery(name = "SsRol.findByRolVigente", query = "SELECT s FROM SsRol s WHERE s.rolVigente = :rolVigente")})
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SsRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rol_id", nullable = false)
    private Integer rolId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "rol_cod", nullable = false, length = 255)
    private String rolCod;
    @Size(max = 2147483647)
    @Column(name = "rol_descripcion", length = 2147483647)
    private String rolDescripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "rol_nombre", nullable = false, length = 255)
    private String rolNombre;
    @Basic(optional = false)
    @Column(name = "rol_label")
    private String rolLabel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "rol_origen", nullable = false, length = 255)
    private String rolOrigen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rol_user_code", nullable = false)
    private int rolUserCode;
    @Column(name = "rol_version")
    private Integer rolVersion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rol_vigente", nullable = false)
    private boolean rolVigente;
    @Basic(optional = true)
    @Column(name = "rol_tipo_usuario")
    private Boolean rolTipoUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuOfiRolesRol")
    private Collection<SsUsuOfiRoles> ssUsuOfiRolesCollection;

    public SsRol() {
    }

    public SsRol(Integer rolId) {
        this.rolId = rolId;
    }

    public SsRol(Integer rolId, String rolCod, String rolNombre, String rolLabel, String rolOrigen, int rolUserCode, boolean rolVigente, boolean rolTipoUsuario) {
        this.rolId = rolId;
        this.rolCod = rolCod;
        this.rolNombre = rolNombre;
        this.rolLabel = rolLabel;
        this.rolOrigen = rolOrigen;
        this.rolUserCode = rolUserCode;
        this.rolVigente = rolVigente;
        this.rolTipoUsuario = rolTipoUsuario;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public String getRolCod() {
        return rolCod;
    }

    public void setRolCod(String rolCod) {
        this.rolCod = rolCod;
    }

    public String getRolDescripcion() {
        return rolDescripcion;
    }

    public void setRolDescripcion(String rolDescripcion) {
        this.rolDescripcion = rolDescripcion;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String getRolOrigen() {
        return rolOrigen;
    }

    public String getRolLabel() {
        return rolLabel;
    }

    public void setRolLabel(String rolLabel) {
        this.rolLabel = rolLabel;
    }

    public void setRolOrigen(String rolOrigen) {
        this.rolOrigen = rolOrigen;
    }

    public int getRolUserCode() {
        return rolUserCode;
    }

    public void setRolUserCode(int rolUserCode) {
        this.rolUserCode = rolUserCode;
    }

    public Integer getRolVersion() {
        return rolVersion;
    }

    public void setRolVersion(Integer rolVersion) {
        this.rolVersion = rolVersion;
    }

    public boolean getRolVigente() {
        return rolVigente;
    }

    public void setRolVigente(boolean rolVigente) {
        this.rolVigente = rolVigente;
    }

    public Boolean getRolTipoUsuario() {
        return rolTipoUsuario;
    }

    public void setRolTipoUsuario(Boolean rolTipoUsuario) {
        this.rolTipoUsuario = rolTipoUsuario;
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
        hash += (rolId != null ? rolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SsRol)) {
            return false;
        }
        SsRol other = (SsRol) object;
        if ((this.rolId == null && other.rolId != null) || (this.rolId != null && !this.rolId.equals(other.rolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.gestemp.entities.SsRol[ rolId=" + rolId + " ]";
    }

    public boolean isRolCod(String cod) {
        return StringsUtils.isEmpty(cod) ? false : this.rolCod.equals(cod);
    }

    public boolean isRolTipoUsuario() {
        return rolTipoUsuario != null ? rolTipoUsuario : false;
    }
}
