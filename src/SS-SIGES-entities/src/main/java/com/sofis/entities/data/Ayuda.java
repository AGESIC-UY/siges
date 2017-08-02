/*
 * 
 * 
 */
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
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
@Table(name = "ss_ayuda", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ayu_codigo"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ayuda.findAll", query = "SELECT s FROM Ayuda s"),
    @NamedQuery(name = "Ayuda.findByAyuId", query = "SELECT s FROM Ayuda s WHERE s.ayuId = :ayuId"),
    @NamedQuery(name = "Ayuda.findByAyuCodigo", query = "SELECT s FROM Ayuda s WHERE s.ayuCodigo = :ayuCodigo"),
    @NamedQuery(name = "Ayuda.findByAyuUltModFecha", query = "SELECT s FROM Ayuda s WHERE s.ayuUltModFecha = :ayuUltModFecha"),
    @NamedQuery(name = "Ayuda.findByAyuUltModUsuario", query = "SELECT s FROM Ayuda s WHERE s.ayuUltModUsuario = :ayuUltModUsuario"),
    @NamedQuery(name = "Ayuda.findByAyuUltModOrigen", query = "SELECT s FROM Ayuda s WHERE s.ayuUltModOrigen = :ayuUltModOrigen"),
    @NamedQuery(name = "Ayuda.findByAyuVersion", query = "SELECT s FROM Ayuda s WHERE s.ayuVersion = :ayuVersion")})
public class Ayuda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ayu_id", nullable = false)
    private Integer ayuId;
    @Size(max = 45)
    @Column(name = "ayu_codigo", length = 45)
    private String ayuCodigo;
    @Size(max = 65535)
    @Column(name = "ayu_texto", length = 65535)
    private String ayuTexto;
    @Column(name = "ayu_ult_mod_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date ayuUltModFecha;
    @Size(max = 45)
    @Column(name = "ayu_ult_mod_usuario", length = 45)
    @AtributoUltimoUsuario
    private String ayuUltModUsuario;
    @Size(max = 45)
    @Column(name = "ayu_ult_mod_origen", length = 45)
    @AtributoUltimaOrigen
    private String ayuUltModOrigen;
    @Column(name = "ayu_version")
    @Version
    private Integer ayuVersion;

    public Ayuda() {
    }

    public Ayuda(Integer ayuId) {
        this.ayuId = ayuId;
    }

    public Integer getAyuId() {
        return ayuId;
    }

    public void setAyuId(Integer ayuId) {
        this.ayuId = ayuId;
    }

    public String getAyuCodigo() {
        return ayuCodigo;
    }

    public void setAyuCodigo(String ayuCodigo) {
        this.ayuCodigo = ayuCodigo;
    }

    public String getAyuTexto() {
        return ayuTexto;
    }

    public void setAyuTexto(String ayuTexto) {
        this.ayuTexto = ayuTexto;
    }

    public Date getAyuUltModFecha() {
        return ayuUltModFecha;
    }

    public void setAyuUltModFecha(Date ayuUltModFecha) {
        this.ayuUltModFecha = ayuUltModFecha;
    }

    public String getAyuUltModUsuario() {
        return ayuUltModUsuario;
    }

    public void setAyuUltModUsuario(String ayuUltModUsuario) {
        this.ayuUltModUsuario = ayuUltModUsuario;
    }

    public String getAyuUltModOrigen() {
        return ayuUltModOrigen;
    }

    public void setAyuUltModOrigen(String ayuUltModOrigen) {
        this.ayuUltModOrigen = ayuUltModOrigen;
    }

    public Integer getAyuVersion() {
        return ayuVersion;
    }

    public void setAyuVersion(Integer ayuVersion) {
        this.ayuVersion = ayuVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ayuId != null ? ayuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Ayuda)) {
            return false;
        }
        Ayuda other = (Ayuda) object;
        if ((this.ayuId == null && other.ayuId != null) || (this.ayuId != null && !this.ayuId.equals(other.ayuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.gestemp.entities.Ayuda[ ayuId=" + ayuId + " ]";
    }
    
    @PrePersist
    @PreUpdate
    public void inicializacion() {
        this.ayuUltModFecha=new Date();
    }
    
}
