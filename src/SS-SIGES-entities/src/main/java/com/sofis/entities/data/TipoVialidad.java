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
@Table(name = "ss_tipos_vialidad")
@NamedQueries({@NamedQuery(name = "TipoVialidad.obtenerPorDescripcion", query = "SELECT t FROM TipoVialidad t where t.tviDescripcion LIKE :tviDescripcion"),
              @NamedQuery(name = "TipoVialidad.obtenerPorCodigo", query = "SELECT t FROM TipoVialidad t where t.tviCodigo LIKE :tviCodigo")})
public class TipoVialidad implements Serializable {
   
     
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tvi_id")
    private Integer tviId;
    @Column(name = "tvi_abreviacion")
    private String tviAbreviacion;
    @Column(name = "tvi_codigo")
    private String tviCodigo;
    @Column(name = "tvi_descripcion")
    private String tviDescripcion;
    
    //Audit
    @Column(name="tvi_ult_usuario")
    @AtributoUltimoUsuario
    private String tviUltUsuario;
    @Column(name="tvi_ult_mod")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date tviUltMod;
    @Column(name="tvi_ult_origen")
    @AtributoUltimaOrigen
    private String tviUltOrigen;
    @Column(name="tvi_version")
    @Version
    private Integer tviVersion;

    public TipoVialidad() {
    }

    // <editor-fold defaultstate="collapsed" desc="getter-setter">
    public Integer getTviId() {
        return tviId;
    }

    public void setTviId(Integer tviId) {
        this.tviId = tviId;
    }

    public String getTviCodigo() {
        return tviCodigo;
    }

    public void setTviCodigo(String tviCodigo) {
        this.tviCodigo = tviCodigo;
    }

    public String getTviDescripcion() {
        return tviDescripcion;
    }

    public void setTviDescripcion(String tviDescripcion) {
        this.tviDescripcion = tviDescripcion;
    }

    
    public String getTviAbreviacion() {
        return tviAbreviacion;
    }

    public void setTviAbreviacion(String tviAbreviacion) {
        this.tviAbreviacion = tviAbreviacion;
    }

    public String getTviUltUsuario() {
        return tviUltUsuario;
    }

    public void setTviUltUsuario(String tviUltUsuario) {
        this.tviUltUsuario = tviUltUsuario;
    }

    public Date getTviUltMod() {
        return tviUltMod;
    }

    public void setTviUltMod(Date tviUltMod) {
        this.tviUltMod = tviUltMod;
    }

    public String getTviUltOrigen() {
        return tviUltOrigen;
    }

    public void setTviUltOrigen(String tviUltOrigen) {
        this.tviUltOrigen = tviUltOrigen;
    }

    public Integer getTviVersion() {
        return tviVersion;
    }

    public void setTviVersion(Integer tviVersion) {
        this.tviVersion = tviVersion;
    }
    // </editor-fold>
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tviId != null ? tviId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoVialidad)) {
            return false;
        }
        TipoVialidad other = (TipoVialidad) object;
        if ((this.tviId == null && other.tviId != null) || (this.tviId != null && !this.tviId.equals(other.tviId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.model.entities.TipoVialidad[deptoId=" + tviId + "]";
    }

    
 
    
}

