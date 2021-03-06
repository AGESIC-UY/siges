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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ss_localidades")
@NamedQueries({@NamedQuery(name = "Localidad.obtenerPorCodigo", query = "SELECT l FROM Localidad l where l.locCodigo LIKE :loc_codigo")})
public class Localidad implements Serializable {
    @OneToMany(mappedBy = "domLocId")
    private Collection<Domicilio> domicilioCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "loc_id")
    private Integer locId;
    @Column(name = "loc_codigo")
    private String locCodigo;
    @Column(name = "loc_nombre")
    private String locNombre;
    @JoinColumn(name = "loc_depto_id", referencedColumnName = "depto_id")
    @ManyToOne
    private Departamento locDeptoId;
   
    //Audit
    @Column(name="loc_ult_usuario")
    @AtributoUltimoUsuario
    private String locUltUsuario;
    @Column(name="loc_ult_mod")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date locUltMod;
    @Column(name="loc_ult_origen")
    @AtributoUltimaOrigen
    private String locUltOrigen;
    @Column(name="loc_version")
    @Version
    private Integer locVersion;
    
    public Localidad() {
    }

    // <editor-fold defaultstate="collapsed" desc="getter-setter">
    public Localidad(Integer locId) {
        this.locId = locId;
    }

    public Integer getLocId() {
        return locId;
    }

    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    public String getLocCodigo() {
        return locCodigo;
    }

    public void setLocCodigo(String locCodigo) {
        this.locCodigo = locCodigo;
    }

    public String getLocNombre() {
        return locNombre;
    }

    public void setLocNombre(String locNombre) {
        this.locNombre = locNombre;
    }

    public Departamento getLocDeptoId() {
        return locDeptoId;
    }

    public void setLocDeptoId(Departamento locDeptoId) {
        this.locDeptoId = locDeptoId;
    }

    public Integer getLocVersion() {
        return locVersion;
    }

    public void setLocVersion(Integer locVersion) {
        this.locVersion = locVersion;
    }

    public String getLocUltUsuario() {
        return locUltUsuario;
    }

    public void setLocUltUsuario(String locUltUsuario) {
        this.locUltUsuario = locUltUsuario;
    }

    public Date getLocUltMod() {
        return locUltMod;
    }

    public void setLocUltMod(Date locUltMod) {
        this.locUltMod = locUltMod;
    }

    public String getLocUltOrigen() {
        return locUltOrigen;
    }

    public void setLocUltOrigen(String locUltOrigen) {
        this.locUltOrigen = locUltOrigen;
    }
    // </editor-fold>
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locId != null ? locId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Localidad)) {
            return false;
        }
        Localidad other = (Localidad) object;
        if ((this.locId == null && other.locId != null) || (this.locId != null && !this.locId.equals(other.locId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.model.entities.Localidades[locId=" + locId + "]";
    }
    
    @PrePersist
    @PreUpdate
    public void preGrabar()  {
       this.locUltMod = new Date();
    }  

    @XmlTransient
    public Collection<Domicilio> getDomicilioCollection() {
        return domicilioCollection;
    }

    public void setDomicilioCollection(Collection<Domicilio> domicilioCollection) {
        this.domicilioCollection = domicilioCollection;
    }
    
}

