/*
 * 
 * 
 */
package com.sofis.entities.data;


import com.sofis.entities.annotations.AtributoUltimaModificacion;
import com.sofis.entities.annotations.AtributoUltimaOrigen;
import com.sofis.entities.annotations.AtributoUltimoUsuario;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.envers.Audited;

/**
 *
 * @author Usuario
 */
@Entity
@Audited
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD) 
@Table(name = "ss_tipos_entrada_colectiva")
@NamedQueries({@NamedQuery(name = "TipoEntradaColectiva.obtenerPorDescripcion", query = "SELECT t FROM TipoEntradaColectiva t where t.tecDescripcion LIKE :tecDescripcion"),
              @NamedQuery(name = "TipoEntradaColectiva.obtenerPorCodigo", query = "SELECT t FROM TipoEntradaColectiva t where t.tecCodigo LIKE :tecCodigo")})
public class TipoEntradaColectiva implements Serializable{
   
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tec_id")
    private Integer tecId;
    @Column(name = "tec_codigo")
    private String tecCodigo;
    @Column(name = "tec_descripcion")
    private String tecDescripcion;
    
    //Audit
    @Column(name="tec_ult_usuario")
    @AtributoUltimoUsuario
    private String tecUltUsuario;
    @Column(name="tec_ult_mod")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date tecUltMod;
    @Column(name="tec_ult_origen")
    @AtributoUltimaOrigen
    private String tecUltOrigen;
    @Column(name="tec_version")
    @Version
    private Integer tecVersion;
    
    public TipoEntradaColectiva(){
        
    }

    // <editor-fold defaultstate="collapsed" desc="getter-setter">
    public Integer getTecId() {
        return tecId;
    }

    public void setTecId(Integer tecId) {
        this.tecId = tecId;
    }

    public String getTecCodigo() {
        return tecCodigo;
    }

    public void setTecCodigo(String tecCodigo) {
        this.tecCodigo = tecCodigo;
    }

    public String getTecDescripcion() {
        return tecDescripcion;
    }

    public String getTecUltUsuario() {
        return tecUltUsuario;
    }

    public void setTecUltUsuario(String tecUltUsuario) {
        this.tecUltUsuario = tecUltUsuario;
    }

    public Date getTecUltMod() {
        return tecUltMod;
    }

    public void setTecUltMod(Date tecUltMod) {
        this.tecUltMod = tecUltMod;
    }

    public String getTecUltOrigen() {
        return tecUltOrigen;
    }

    public void setTecUltOrigen(String tecUltOrigen) {
        this.tecUltOrigen = tecUltOrigen;
    }

    public Integer getTecVersion() {
        return tecVersion;
    }

    public void setTecVersion(Integer tecVersion) {
        this.tecVersion = tecVersion;
    }

    public void setTecDescripcion(String tecDescripcion) {
        this.tecDescripcion = tecDescripcion;
    }
    // </editor-fold>
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tecId != null ? tecId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoEntradaColectiva)) {
            return false;
        }
        TipoEntradaColectiva other = (TipoEntradaColectiva) object;
        if ((this.tecId == null && other.tecId != null) || (this.tecId != null && !this.tecId.equals(other.tecId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.ssgenericentities.entity.TipoEntradaColectiva[tecId=" + tecId + "]";
    }

    @PrePersist
    @PreUpdate
    public void preGrabar()  {
       this.tecUltMod = new Date();
    }  

    
}

