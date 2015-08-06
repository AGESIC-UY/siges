package com.sofis.entities.data;

import com.sofis.generico.utils.generalutils.StringsUtils;
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
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Size;
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
@Table(name = "ss_telefonos")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD) 
public class Telefono implements Serializable {
     
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tel_id", nullable = false)
    private Integer telId;
    @Size(max = 25)
    @Column(name = "tel_numero", length = 25)
    private String telNumero;
    @Column(name = "tel_ult_mod")
    @Temporal(TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date telUltMod;
    @Size(max = 45)
    @Column(name = "tel_ult_origen", length = 45)
    @AtributoUltimaOrigen
    private String telUltOrigen;
    @Size(max = 45)
    @Column(name = "tel_ult_usuario", length = 45)
    @AtributoUltimoUsuario
    private String telUltUsuario;
    @Column(name = "tel_version")
    @Version
    private Integer telVersion;
    @Size(max = 10)
    @Column(name = "tel_prefijo", length = 10)
    private String telPrefijo;
    @JoinColumn(name = "tel_tiptel_id", referencedColumnName = "tiptel_id")
    @ManyToOne
    private TipoTelefono telTiptelId;
    @Column(name="tel_observaciones")
    @Size(max=255)
    private String telObservaciones;

    public Telefono() {
    }

    public Telefono(Integer telId) {
        this.telId = telId;
    }

    public Integer getTelId() {
        return telId;
    }

    public void setTelId(Integer telId) {
        this.telId = telId;
    }

    public String getTelNumero() {
        return telNumero;
    }

    public void setTelNumero(String telNumero) {
        this.telNumero = telNumero;
    }

    public Date getTelUltMod() {
        return telUltMod;
    }

    public void setTelUltMod(Date telUltMod) {
        this.telUltMod = telUltMod;
    }

    public String getTelUltOrigen() {
        return telUltOrigen;
    }

    public void setTelUltOrigen(String telUltOrigen) {
        this.telUltOrigen = telUltOrigen;
    }

    public String getTelUltUsuario() {
        return telUltUsuario;
    }

    public void setTelUltUsuario(String telUltUsuario) {
        this.telUltUsuario = telUltUsuario;
    }

    public Integer getTelVersion() {
        return telVersion;
    }

    public void setTelVersion(Integer telVersion) {
        this.telVersion = telVersion;
    }

    public String getTelPrefijo() {
        return telPrefijo;
    }

    public void setTelPrefijo(String telPrefijo) {
        this.telPrefijo = telPrefijo;
    }

    public TipoTelefono getTelTiptelId() {
        return telTiptelId;
    }

    public void setTelTiptelId(TipoTelefono telTiptelId) {
        this.telTiptelId = telTiptelId;
    }

    public String getTelObservaciones() {
        return telObservaciones;
    }

    public void setTelObservaciones(String telObservaciones) {
        this.telObservaciones = telObservaciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (telId != null ? telId.hashCode() : 0);
        return hash;
    }

    
    public Telefono clonar() {
        Telefono respuesta = new Telefono();
        respuesta.setTelId(telId);
        respuesta.setTelNumero(telNumero);
        respuesta.setTelObservaciones(telObservaciones);
        respuesta.setTelPrefijo(telPrefijo);
        respuesta.setTelTiptelId(telTiptelId);
        respuesta.setTelUltMod(telUltMod);
        respuesta.setTelUltOrigen(telUltOrigen);
        respuesta.setTelUltUsuario(telUltUsuario);
        respuesta.setTelVersion(telVersion);
        return respuesta;
    }
    
    @Override
    public String toString() {
        return "com.sofis.gestemp.entities.SsTelefonos[ telId=" + telId + " ]";
    }

    @PrePersist
    @PreUpdate
    public void preGrabar() {
        this.telUltMod = new Date();
        normalizar();
    }
    
    private void normalizar() {
        this.telNumero=StringsUtils.normalizarString(telNumero);
        this.telPrefijo=StringsUtils.normalizarString(telPrefijo);
        this.telObservaciones=StringsUtils.normalizarString(telObservaciones);
    }
 
}
