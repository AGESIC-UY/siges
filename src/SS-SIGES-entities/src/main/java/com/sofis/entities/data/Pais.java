/*
 * 
 * 
 */
package com.sofis.entities.data;


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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;


/**
 *
 * @author Usuario
 */

@Entity
@Audited
@Table(name = "ss_paises")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD) 
@NamedQueries({
@NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p")})
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pai_id")
    private Integer paiId;
    
    @Column(name = "pai_codigo2")
    private String paiCodigo2;
    @Column(name = "pai_codigo3")
    private String paiCodigo3;
    @Column(name = "pai_nombre")
    private String paiNombre;
    @Column(name = "pai_habilitado")
    private Boolean paiHabilitado;
    @Column(name = "pai_comun")
     private Boolean paiComun;
    
    //Auditoria
    @Column(name="pai_ult_usuario")
    @AtributoUltimoUsuario
    private String paiUltUsuario;
    @Column(name="pai_ult_mod")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date paiUltMod;
    @Column(name="pai_ult_origen")
    private String paiUltOrigen;
    @Column(name="pai_version")
    @Version
    private Integer paiVersion;

    public Pais() {
    }

    // <editor-fold defaultstate="collapsed" desc="getter-setter">
    public Pais(Integer paiId) {
        this.paiId = paiId;
    }

    public Integer getPaiId() {
        return paiId;
    }

    public void setPaiId(Integer paiId) {
        this.paiId = paiId;
    }

    public String getPaiCodigo2() {
        return paiCodigo2;
    }

    public void setPaiCodigo2(String paiCodigo2) {
        this.paiCodigo2 = paiCodigo2;
    }

    public String getPaiCodigo3() {
        return paiCodigo3;
    }

    public void setPaiCodigo3(String paiCodigo3) {
        this.paiCodigo3 = paiCodigo3;
    }

    public String getPaiNombre() {
        return paiNombre;
    }

    public void setPaiNombre(String paiNombre) {
        this.paiNombre = paiNombre;
    }

    public Boolean getPaiHabilitado() {
        return paiHabilitado;
    }

    public void setPaiHabilitado(Boolean paiHabilitado) {
        this.paiHabilitado = paiHabilitado;
    }

    public Boolean getPaiComun() {
        return paiComun;
    }

    public void setPaiComun(Boolean paiComun) {
        this.paiComun = paiComun;
    }

    public String getPaiUltUsuario() {
        return paiUltUsuario;
    }

    public void setPaiUltUsuario(String paiUltUsuario) {
        this.paiUltUsuario = paiUltUsuario;
    }

    public Date getPaiUltMod() {
        return paiUltMod;
    }

    public void setPaiUltMod(Date paiUltMod) {
        this.paiUltMod = paiUltMod;
    }

    public String getPaiUltOrigen() {
        return paiUltOrigen;
    }

    public void setPaiUltOrigen(String paiUltOrigen) {
        this.paiUltOrigen = paiUltOrigen;
    }

    public Integer getPaiVersion() {
        return paiVersion;
    }

    public void setPaiVersion(Integer paiVersion) {
        this.paiVersion = paiVersion;
    }
    // </editor-fold>
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paiId != null ? paiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.paiId == null && other.paiId != null) || (this.paiId != null && !this.paiId.equals(other.paiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.ssgenericentities.entity.Pais[ paiId=" + paiId + " ]";
    }

}
