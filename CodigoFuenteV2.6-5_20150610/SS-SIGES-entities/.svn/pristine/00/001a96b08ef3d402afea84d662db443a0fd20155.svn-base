package com.sofis.entities.data;

import com.sofis.entities.annotations.AtributoUltimaModificacion;
import com.sofis.entities.annotations.AtributoUltimaOrigen;
import com.sofis.entities.annotations.AtributoUltimoUsuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.envers.Audited;

/**
 *
 * @author Usuario
 */
@Entity
@Audited
@Table(name = "pge_configuraciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PgeConfiguraciones.findAll", query = "SELECT p FROM PgeConfiguraciones p"),
    @NamedQuery(name = "PgeConfiguraciones.findByCnfId", query = "SELECT p FROM PgeConfiguraciones p WHERE p.cnfId = :cnfId"),
    @NamedQuery(name = "PgeConfiguraciones.findByCnfClave", query = "SELECT p FROM PgeConfiguraciones p WHERE p.cnfClave = :cnfClave"),
    @NamedQuery(name = "PgeConfiguraciones.findByCnfCreaFecha", query = "SELECT p FROM PgeConfiguraciones p WHERE p.cnfCreaFecha = :cnfCreaFecha"),
    @NamedQuery(name = "PgeConfiguraciones.findByCnfCreaOrigen", query = "SELECT p FROM PgeConfiguraciones p WHERE p.cnfCreaOrigen = :cnfCreaOrigen"),
    @NamedQuery(name = "PgeConfiguraciones.findByCnfCreaUsu", query = "SELECT p FROM PgeConfiguraciones p WHERE p.cnfCreaUsu = :cnfCreaUsu"),
    @NamedQuery(name = "PgeConfiguraciones.findByCnfUltmodFecha", query = "SELECT p FROM PgeConfiguraciones p WHERE p.cnfUltmodFecha = :cnfUltmodFecha"),
    @NamedQuery(name = "PgeConfiguraciones.findByCnfUltmodOrigen", query = "SELECT p FROM PgeConfiguraciones p WHERE p.cnfUltmodOrigen = :cnfUltmodOrigen"),
    @NamedQuery(name = "PgeConfiguraciones.findByCnfUltmodUsu", query = "SELECT p FROM PgeConfiguraciones p WHERE p.cnfUltmodUsu = :cnfUltmodUsu"),
    @NamedQuery(name = "PgeConfiguraciones.findByCnfValor", query = "SELECT p FROM PgeConfiguraciones p WHERE p.cnfValor = :cnfValor"),
    @NamedQuery(name = "PgeConfiguraciones.findByCnfVersion", query = "SELECT p FROM PgeConfiguraciones p WHERE p.cnfVersion = :cnfVersion")})
public class PgeConfiguraciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cnf_id", nullable = false)
    private Integer cnfId;
    @Size(max = 255)
    @Column(name = "cnf_clave", length = 255)
    private String cnfClave;
    @Column(name = "cnf_crea_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cnfCreaFecha;
    @Column(name = "cnf_crea_origen")
    private Integer cnfCreaOrigen;
    @Size(max = 255)
    @Column(name = "cnf_crea_usu", length = 255)
    private String cnfCreaUsu;
    @Column(name = "cnf_ultmod_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date cnfUltmodFecha;
    @Column(name = "cnf_ultmod_origen")
    @AtributoUltimaOrigen
    private Integer cnfUltmodOrigen;
    @Size(max = 255)
    @Column(name = "cnf_ultmod_usu", length = 255)
    @AtributoUltimoUsuario
    private String cnfUltmodUsu;
    @Size(max = 255)
    @Column(name = "cnf_valor", length = 255)
    private String cnfValor;
    @Column(name = "cnf_version")
    @Version
    private Integer cnfVersion;

    public PgeConfiguraciones() {
    }

    public PgeConfiguraciones(Integer cnfId) {
        this.cnfId = cnfId;
    }

    public Integer getCnfId() {
        return cnfId;
    }

    public void setCnfId(Integer cnfId) {
        this.cnfId = cnfId;
    }

    public String getCnfClave() {
        return cnfClave;
    }

    public void setCnfClave(String cnfClave) {
        this.cnfClave = cnfClave;
    }

    public Date getCnfCreaFecha() {
        return cnfCreaFecha;
    }

    public void setCnfCreaFecha(Date cnfCreaFecha) {
        this.cnfCreaFecha = cnfCreaFecha;
    }

    public Integer getCnfCreaOrigen() {
        return cnfCreaOrigen;
    }

    public void setCnfCreaOrigen(Integer cnfCreaOrigen) {
        this.cnfCreaOrigen = cnfCreaOrigen;
    }

    public String getCnfCreaUsu() {
        return cnfCreaUsu;
    }

    public void setCnfCreaUsu(String cnfCreaUsu) {
        this.cnfCreaUsu = cnfCreaUsu;
    }

    public Date getCnfUltmodFecha() {
        return cnfUltmodFecha;
    }

    public void setCnfUltmodFecha(Date cnfUltmodFecha) {
        this.cnfUltmodFecha = cnfUltmodFecha;
    }

    public Integer getCnfUltmodOrigen() {
        return cnfUltmodOrigen;
    }

    public void setCnfUltmodOrigen(Integer cnfUltmodOrigen) {
        this.cnfUltmodOrigen = cnfUltmodOrigen;
    }

    public String getCnfUltmodUsu() {
        return cnfUltmodUsu;
    }

    public void setCnfUltmodUsu(String cnfUltmodUsu) {
        this.cnfUltmodUsu = cnfUltmodUsu;
    }

    public String getCnfValor() {
        return cnfValor;
    }

    public void setCnfValor(String cnfValor) {
        this.cnfValor = cnfValor;
    }

    public Integer getCnfVersion() {
        return cnfVersion;
    }

    public void setCnfVersion(Integer cnfVersion) {
        this.cnfVersion = cnfVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cnfId != null ? cnfId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PgeConfiguraciones)) {
            return false;
        }
        PgeConfiguraciones other = (PgeConfiguraciones) object;
        if ((this.cnfId == null && other.cnfId != null) || (this.cnfId != null && !this.cnfId.equals(other.cnfId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.gestemp.entities.PgeConfiguraciones[ cnfId=" + cnfId + " ]";
    }
}
