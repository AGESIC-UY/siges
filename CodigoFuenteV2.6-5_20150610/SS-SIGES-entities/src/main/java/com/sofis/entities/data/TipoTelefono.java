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
@Table(name = "ss_tipos_telefono")
@NamedQueries({@NamedQuery(name = "TipoTelefono.obtenerPorDescripcion", query = "SELECT t FROM TipoTelefono t where t.tipTelDescripcion LIKE :tipTelDescripcion"),
              @NamedQuery(name = "TipoTelefono.obtenerPorCodigo", query = "SELECT t FROM TipoTelefono t where t.tipTelCodigo LIKE :tipTelCodigo")})
public class TipoTelefono implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipTel_id")
    private Integer tipTelId;
    @Column(name = "tipTel_descripcion")
    private String tipTelDescripcion;
    @Column(name = "tipTel_habilitado")
    private Boolean tipTelHabilitado;
    
    //Nuevo
    @Column(name = "tipTel_codigo")
    private String tipTelCodigo;
    
    //Audit
    @Column(name="tipTel_ult_usuario")
    @AtributoUltimoUsuario
    private String tipTelUltUsuario;
    @Column(name="tipTel_ult_mod")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date tipTelUltMod;
    @Column(name="tipTel_ult_origen")
    @AtributoUltimaOrigen
    private String tipTelUltOrigen;
    @Column(name="tipTel_version")
    @Version
    private Integer tipTelVersion;

    public TipoTelefono() {
    }

    // <editor-fold defaultstate="collapsed" desc="getter-setter">
    public TipoTelefono(Integer tipTelId) {
        this.tipTelId = tipTelId;
    }

    public Integer getTipTelId() {
        return tipTelId;
    }

    public void settipTelId(Integer tipTelId) {
        this.tipTelId = tipTelId;
    }

    public String getTipTelDescripcion() {
        return tipTelDescripcion;
    }

    public void settipTelDescripcion(String tipTelDescripcion) {
        this.tipTelDescripcion = tipTelDescripcion;
    }

    public Boolean getTipTelHabilitado() {
        return tipTelHabilitado;
    }

    public void settipTelHabilitado(Boolean tipTelHabilitado) {
        this.tipTelHabilitado = tipTelHabilitado;
    }

    public String getTipTelCodigo() {
        return tipTelCodigo;
    }

    public void settipTelCodigo(String tipTelCodigo) {
        this.tipTelCodigo = tipTelCodigo;
    }

    public String getTipTelUltUsuario() {
        return tipTelUltUsuario;
    }

    public void settipTelUltUsuario(String tipTelUltUsuario) {
        this.tipTelUltUsuario = tipTelUltUsuario;
    }

    public Date getTipTelUltMod() {
        return tipTelUltMod;
    }

    public void settipTelUltMod(Date tipTelUltMod) {
        this.tipTelUltMod = tipTelUltMod;
    }

    public String getTipTelUltOrigen() {
        return tipTelUltOrigen;
    }

    public void settipTelUltOrigen(String tipTelUltOrigen) {
        this.tipTelUltOrigen = tipTelUltOrigen;
    }

    public Integer getTipTelVersion() {
        return tipTelVersion;
    }

    public void settipTelVersion(Integer tipTelVersion) {
        this.tipTelVersion = tipTelVersion;
    }
    // </editor-fold>
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipTelId != null ? tipTelId.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoTelefono)) {
            return false;
        }
        TipoTelefono other = (TipoTelefono) object;
        if ((this.tipTelId == null && other.tipTelId != null) || (this.tipTelId != null && !this.tipTelId.equals(other.tipTelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.ssgenericentities.entity.TiposTelefono[tipTelId=" + tipTelId + "]";
    }
    
    @PrePersist
    @PreUpdate
    public void preGrabar()  {
       this.tipTelUltMod = new Date();
    }  

    
    
}

