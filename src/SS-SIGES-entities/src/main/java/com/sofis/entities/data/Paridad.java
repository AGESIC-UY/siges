/*
 * 
 * 
 */
package com.sofis.entities.data;

import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.entities.annotations.AtributoUltimaOrigen;
import com.sofis.entities.annotations.AtributoUltimoUsuario;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Version;
import javax.validation.constraints.Size;
import org.hibernate.envers.Audited;

/**
 *
 * @author Usuario
 */
@Entity
@Audited
@Table(name = "ss_paridades")
@NamedQueries({@NamedQuery(name = "Paridad.obtenerPorCodigo", query = "SELECT p FROM Paridad p where p.parCodigo LIKE :parCodigo")})
public class Paridad implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "par_id")
    private Short parId;

    @Size(max = 9)
    @Column(name = "par_codigo")
    private String parCodigo;
    @Size(max=45)
    @Column(name="par_descripcion")
    private String parDescripcion;
    @Column(name="par_ult_mod_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date parUltModFecha;
    @Column(name="par_version")
    @Version
    private Integer parVersion;
      @Size(max=45)
    @Column(name="par_ult_mod_usuario")
      @AtributoUltimoUsuario
    private String parUltModUsuario;
       @Size(max=45)
    @Column(name="par_ult_mod_origen")
       @AtributoUltimaOrigen
    private String parUltModOrigen;
     
    
    public Short getParId() {
        return parId;
    }

    public void setParId(Short parId) {
        this.parId = parId;
    }

    public String getParCodigo() {
        return parCodigo;
    }

    public void setParCodigo(String parCodigo) {
        this.parCodigo = parCodigo;
    }
        
    public Short getId() {
        return parId;
    }

    public void setId(Short id) {
        this.parId = id;
    }

    public String getParDescripcion() {
        return parDescripcion;
    }

    public void setParDescripcion(String parDescripcion) {
        this.parDescripcion = parDescripcion;
    }

    public Date getParUltModFecha() {
        return parUltModFecha;
    }

    public void setParUltModFecha(Date parUltModFecha) {
        this.parUltModFecha = parUltModFecha;
    }

    public Integer getParVersion() {
        return parVersion;
    }

    public void setParVersion(Integer parVersion) {
        this.parVersion = parVersion;
    }

    public String getParUltModUsuario() {
        return parUltModUsuario;
    }

    public void setParUltModUsuario(String parUltModUsuario) {
        this.parUltModUsuario = parUltModUsuario;
    }

    public String getParUltModOrigen() {
        return parUltModOrigen;
    }

    public void setParUltModOrigen(String parUltModOrigen) {
        this.parUltModOrigen = parUltModOrigen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parId != null ? parId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Paridad)) {
            return false;
        }
        Paridad other = (Paridad) object;
        if ((this.parId == null && other.parId != null) || (this.parId != null && !this.parId.equals(other.parId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.gestemp.entities.Paridad[ id=" + parId + " ]";
    }
    
    @PrePersist
    @PreUpdate
    public void inicializar() {
        this.parUltModFecha=new Date();
        this.parCodigo=this.parCodigo!=null?StringsUtils.normalizarStringMayuscula(this.parCodigo):null;
        this.parDescripcion=this.parDescripcion!=null?StringsUtils.normalizarStringMayuscula(this.parDescripcion):null;
    }
    
}
