/*
 * 
 * 
 */
package com.sofis.entities.data;


import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.entities.annotations.AtributoUltimaModificacion;
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
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.envers.Audited;


/**
 *
 * @author Usuario
 */
@Entity
@Audited
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD) 
@Table(name = "ss_tipos_documento_persona")
@NamedQueries({@NamedQuery(name = "TipoDocumentoPersona.obtenerPorDescripcion", query = "SELECT t FROM TipoDocumentoPersona t where t.tipdocperDescripcion LIKE :tipdocperDescripcion"),
              @NamedQuery(name = "TipoDocumentoPersona.obtenerPorCodigo", query = "SELECT t FROM TipoDocumentoPersona t where t.tipdocperCodigo LIKE :tipdocperCodigo")})
public class TipoDocumentoPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipdocper_id")
    private Integer tipdocperId;
    @Column(name = "tipdocper_descripcion")
    private String tipdocperDescripcion;
    @Column(name = "tipdocper_habilitado")
    private Boolean tipdocperHabilitado;
    @Column(name = "tipdocper_codigo")
    private String tipdocperCodigo;
        
    //Audit
    @Column(name="tipdocper_ult_usuario")
    @AtributoUltimoUsuario
    private String tipdocperUltUsuario;
    @Column(name="tipdocper_ult_mod")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date tipdocperUltMod;
    @Column(name="tipdocper_ult_origen")
    private String tipdocperUltOrigen;
    @Column(name="tipdocper_version")
    @Version
    private Integer tipdocperVersion;

    public TipoDocumentoPersona() {
    }

    // <editor-fold defaultstate="collapsed" desc="getter-setter">
    public TipoDocumentoPersona(Integer tipdocperId) {
        this.tipdocperId = tipdocperId;
    }

    public Integer getTipdocperId() {
        return tipdocperId;
    }

    public void setTipdocperId(Integer tipdocperId) {
        this.tipdocperId = tipdocperId;
    }

    public String getTipdocperDescripcion() {
        return tipdocperDescripcion;
    }

    public void setTipdocperDescripcion(String tipdocperDescripcion) {
        this.tipdocperDescripcion = tipdocperDescripcion;
    }

    public Boolean getTipdocperHabilitado() {
        return tipdocperHabilitado;
    }

    public void setTipdocperHabilitado(Boolean tipdocperHabilitado) {
        this.tipdocperHabilitado = tipdocperHabilitado;
    }

    public Integer getTipdocperVersion() {
        return tipdocperVersion;
    }

    public void setTipdocperVersion(Integer tipdocperVersion) {
        this.tipdocperVersion = tipdocperVersion;
    }

    public String getTipdocperUltUsuario() {
        return tipdocperUltUsuario;
    }

    public void setTipdocperUltUsuario(String tipdocperUltUsuario) {
        this.tipdocperUltUsuario = tipdocperUltUsuario;
    }

    public Date getTipdocperUltMod() {
        return tipdocperUltMod;
    }

    public void setTipdocperUltMod(Date tipdocperUltMod) {
        this.tipdocperUltMod = tipdocperUltMod;
    }

    public String getTipdocperUltOrigen() {
        return tipdocperUltOrigen;
    }

    public void setTipdocperUltOrigen(String tipdocperUltOrigen) {
        this.tipdocperUltOrigen = tipdocperUltOrigen;
    }

    public String getTipdocperCodigo() {
        return tipdocperCodigo;
    }

    public void setTipdocperCodigo(String tipdocperCodigo) {
        this.tipdocperCodigo = tipdocperCodigo;
    }
    // </editor-fold>
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipdocperId != null ? tipdocperId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoDocumentoPersona)) {
            return false;
        }
        TipoDocumentoPersona other = (TipoDocumentoPersona) object;
        if ((this.tipdocperId == null && other.tipdocperId != null) || (this.tipdocperId != null && !this.tipdocperId.equals(other.tipdocperId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.ssgenericentities.entity.TiposDocumentoPersona[tipdocperId=" + tipdocperId + "]";
    }
    
    @PrePersist
    @PreUpdate
    public void preGrabar()  {
       this.tipdocperUltMod = new Date();
       this.tipdocperCodigo=StringsUtils.normalizarStringMayuscula(tipdocperCodigo);
       this.tipdocperDescripcion=StringsUtils.normalizarString(tipdocperDescripcion);
    }  
    
}

