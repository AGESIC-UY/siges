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
@Table(name = "ss_noticias", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"not_codigo"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Noticia.findAll", query = "SELECT s FROM Noticia s"),
    @NamedQuery(name = "Noticia.findByNotId", query = "SELECT s FROM Noticia s WHERE s.notId = :notId"),
    @NamedQuery(name = "Noticia.findByNotCodigo", query = "SELECT s FROM Noticia s WHERE s.notCodigo = :notCodigo"),
    @NamedQuery(name = "Noticia.findByNotTitulo", query = "SELECT s FROM Noticia s WHERE s.notTitulo = :notTitulo"),
    @NamedQuery(name = "Noticia.findByNotAmpliar", query = "SELECT s FROM Noticia s WHERE s.notAmpliar = :notAmpliar"),
    @NamedQuery(name = "Noticia.findByNotUltModFecha", query = "SELECT s FROM Noticia s WHERE s.notUltModFecha = :notUltModFecha"),
    @NamedQuery(name = "Noticia.findByNotUltModUsuario", query = "SELECT s FROM Noticia s WHERE s.notUltModUsuario = :notUltModUsuario"),
    @NamedQuery(name = "Noticia.findByNotUltModOrigen", query = "SELECT s FROM Noticia s WHERE s.notUltModOrigen = :notUltModOrigen"),
    @NamedQuery(name = "Noticia.findByNotVersion", query = "SELECT s FROM Noticia s WHERE s.notVersion = :notVersion"),
    @NamedQuery(name = "Noticia.findByNotDesde", query = "SELECT s FROM Noticia s WHERE s.notDesde = :notDesde"),
    @NamedQuery(name = "Noticia.findByNotHasta", query = "SELECT s FROM Noticia s WHERE s.notHasta = :notHasta"),
    @NamedQuery(name = "Noticia.findByNotImagen", query = "SELECT s FROM Noticia s WHERE s.notImagen = :notImagen")})
public class Noticia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "not_id", nullable = false)
    private Integer notId;
    @Size(max = 45)
    @Column(name = "not_codigo", length = 45)
    private String notCodigo;
    @Size(max = 65535)
    @Column(name = "not_contenido", length = 65535)
    private String notContenido;
    @Size(max = 255)
    @Column(name = "not_titulo", length = 255)
    private String notTitulo;
    @Size(max = 255)
    @Column(name = "not_ampliar", length = 255)
    private String notAmpliar;
    @Column(name = "not_ult_mod_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date notUltModFecha;
    @Size(max = 45)
    @Column(name = "not_ult_mod_usuario", length = 45)
    @AtributoUltimoUsuario
    private String notUltModUsuario;
    @Size(max = 45)
    @Column(name = "not_ult_mod_origen", length = 45)
    @AtributoUltimaOrigen
    private String notUltModOrigen;
    @Column(name = "not_version")
    @Version
    private Integer notVersion;
    @Column(name = "not_desde")
    @Temporal(TemporalType.TIMESTAMP)
    private Date notDesde;
    @Column(name = "not_hasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date notHasta;
    @Size(max = 255)
    @Column(name = "not_imagen", length = 255)
    private String notImagen;

    public Noticia() {
    }

    public Noticia(Integer notId) {
        this.notId = notId;
    }

    public Integer getNotId() {
        return notId;
    }

    public void setNotId(Integer notId) {
        this.notId = notId;
    }

    public String getNotCodigo() {
        return notCodigo;
    }

    public void setNotCodigo(String notCodigo) {
        this.notCodigo = notCodigo;
    }

    public String getNotContenido() {
        return notContenido;
    }

    public void setNotContenido(String notContenido) {
        this.notContenido = notContenido;
    }

    public String getNotTitulo() {
        return notTitulo;
    }

    public void setNotTitulo(String notTitulo) {
        this.notTitulo = notTitulo;
    }

    public String getNotAmpliar() {
        return notAmpliar;
    }

    public void setNotAmpliar(String notAmpliar) {
        this.notAmpliar = notAmpliar;
    }

    public Date getNotUltModFecha() {
        return notUltModFecha;
    }

    public void setNotUltModFecha(Date notUltModFecha) {
        this.notUltModFecha = notUltModFecha;
    }

    public String getNotUltModUsuario() {
        return notUltModUsuario;
    }

    public void setNotUltModUsuario(String notUltModUsuario) {
        this.notUltModUsuario = notUltModUsuario;
    }

    public String getNotUltModOrigen() {
        return notUltModOrigen;
    }

    public void setNotUltModOrigen(String notUltModOrigen) {
        this.notUltModOrigen = notUltModOrigen;
    }

    public Integer getNotVersion() {
        return notVersion;
    }

    public void setNotVersion(Integer notVersion) {
        this.notVersion = notVersion;
    }

    public Date getNotDesde() {
        return notDesde;
    }

    public void setNotDesde(Date notDesde) {
        this.notDesde = notDesde;
    }

    public Date getNotHasta() {
        return notHasta;
    }

    public void setNotHasta(Date notHasta) {
        this.notHasta = notHasta;
    }

    public String getNotImagen() {
        return notImagen;
    }

    public void setNotImagen(String notImagen) {
        this.notImagen = notImagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notId != null ? notId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Noticia)) {
            return false;
        }
        Noticia other = (Noticia) object;
        if ((this.notId == null && other.notId != null) || (this.notId != null && !this.notId.equals(other.notId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.gestemp.entities.Noticia[ notId=" + notId + " ]";
    }
    
    @PrePersist
    @PreUpdate
    public void inicializar() {
        this.notUltModFecha=new Date();
    }
    
}
