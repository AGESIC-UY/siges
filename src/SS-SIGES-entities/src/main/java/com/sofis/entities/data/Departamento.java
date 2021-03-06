package com.sofis.entities.data;


import com.sofis.entities.annotations.AtributoUltimaModificacion;
import com.sofis.entities.annotations.AtributoUltimoUsuario;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
@Table(name = "ss_departamentos")
@NamedQueries({@NamedQuery(name = "Departamento.obtenerPorCodigo", query = "SELECT d FROM Departamento d where d.deptoCodigo LIKE :deptoCodigo")})
public class Departamento implements Serializable {
    @OneToMany(mappedBy = "locDeptoId")
    private Collection<Localidad> localidadCollection;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "depto_id")
    private Integer deptoId;
    @Column(name = "depto_codigo")
    private String deptoCodigo;
    @Column(name = "depto_nombre")
    private String deptoNombre;
     
    
    //Audit
    @Column(name="depto_ult_usuario")
    @AtributoUltimoUsuario
    private String deptoUltUsuario;
    @Column(name="depto_ult_mod")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date deptoUltMod;
    @Column(name="err_ult_origen")
    private String deptoUltOrigen;
    @Column(name="depto_version")
    @Version
    private Integer deptoVersion;
    
    public Departamento() {
    }

    // <editor-fold defaultstate="collapsed" desc="getter-setter">
    public Departamento(Integer deptoId) {
        this.deptoId = deptoId;
    }

    public Integer getDeptoId() {
        return deptoId;
    }

    public void setDeptoId(Integer deptoId) {
        this.deptoId = deptoId;
    }

    public String getDeptoCodigo() {
        return deptoCodigo;
    }

    public void setDeptoCodigo(String deptoCodigo) {
        this.deptoCodigo = deptoCodigo;
    }

    public String getDeptoNombre() {
        return deptoNombre;
    }

    public void setDeptoNombre(String deptoNombre) {
        this.deptoNombre = deptoNombre;
    }

   

    public Integer getDeptoVersion() {
        return deptoVersion;
    }

    public void setDeptoVersion(Integer deptoVersion) {
        this.deptoVersion = deptoVersion;
    }

    public String getDeptoUltUsuario() {
        return deptoUltUsuario;
    }

    public void setDeptoUltUsuario(String deptoUltUsuario) {
        this.deptoUltUsuario = deptoUltUsuario;
    }

    public Date getDeptoUltMod() {
        return deptoUltMod;
    }

    public void setDeptoUltMod(Date deptoUltMod) {
        this.deptoUltMod = deptoUltMod;
    }

    public String getDeptoUltOrigen() {
        return deptoUltOrigen;
    }

    public void setDeptoUltOrigen(String deptoUltOrigen) {
        this.deptoUltOrigen = deptoUltOrigen;
    }
    // </editor-fold>
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptoId != null ? deptoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.deptoId == null && other.deptoId != null) || (this.deptoId != null && !this.deptoId.equals(other.deptoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.model.entities.Departamentos[deptoId=" + deptoId + "]";
    }
    
    @PrePersist
    @PreUpdate
    public void preGrabar()  {
       this.deptoUltMod = new Date();
    }  

    @XmlTransient
    public Collection<Localidad> getLocalidadCollection() {
        return localidadCollection;
    }

    public void setLocalidadCollection(Collection<Localidad> localidadCollection) {
        this.localidadCollection = localidadCollection;
    }
}

