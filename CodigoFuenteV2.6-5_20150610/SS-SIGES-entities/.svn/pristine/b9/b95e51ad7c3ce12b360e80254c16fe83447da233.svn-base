package com.sofis.entities.data;

import com.sofis.entities.annotations.AtributoUltimaModificacion;
import com.sofis.entities.annotations.AtributoUltimoUsuario;
import com.sofis.generico.utils.generalutils.StringsUtils;
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
@Table(name = "ss_errores")
@NamedQueries({
    @NamedQuery(name = "Error.obtenerPorDescripcion", query = "SELECT e FROM Error e where e.errDescripcion LIKE :errDescripcion"),
    @NamedQuery(name = "Error.obtenerPorCodigo", query = "SELECT e FROM Error e where e.errCodigo LIKE :errCodigo")})
public class Error implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "err_codigo")
    private String errCodigo;
    @Column(name = "err_descripcion")
    private String errDescripcion;
    //Audit
    @Column(name = "err_ult_usuario")
    @AtributoUltimoUsuario
    private String errUltUsuario;
    @Column(name = "err_ult_mod")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date errUltMod;
    @Column(name = "err_ult_origen")
    private String errUltOrigen;
    @Column(name = "err_version")
    @Version
    private Integer errVersion;

    // <editor-fold defaultstate="collapsed" desc="getter-setter">
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getErrCodigo() {
        return errCodigo;
    }

    public void setErrCodigo(String errCodigo) {
        this.errCodigo = errCodigo;
    }

    public String getErrDescripcion() {
        return errDescripcion;
    }

    public void setErrDescripcion(String errDescripcion) {
        this.errDescripcion = errDescripcion;
    }

    public String getErrUltUsuario() {
        return errUltUsuario;
    }

    public void setErrUltUsuario(String errUltUsuario) {
        this.errUltUsuario = errUltUsuario;
    }

    public Date getErrUltMod() {
        return errUltMod;
    }

    public void setErrUltMod(Date errUltMod) {
        this.errUltMod = errUltMod;
    }

    public String getErrUltOrigen() {
        return errUltOrigen;
    }

    public void setErrUltOrigen(String errUltOrigen) {
        this.errUltOrigen = errUltOrigen;
    }

    public Integer getErrVersion() {
        return errVersion;
    }

    public void setErrVersion(Integer errVersion) {
        this.errVersion = errVersion;
    }
    // </editor-fold>

    @PrePersist
    @PreUpdate
    public void preGrabar() {
        this.errUltMod = new Date();
        this.errCodigo = StringsUtils.normalizarStringMayuscula(errCodigo);
        this.errDescripcion = StringsUtils.normalizarString(errDescripcion);
    }
}
