/*
 * 
 * 
 */
package com.sofis.entities.data;

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
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "pge_invocaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PgeInvocaciones.findAll", query = "SELECT p FROM PgeInvocaciones p"),
    @NamedQuery(name = "PgeInvocaciones.findByInvId", query = "SELECT p FROM PgeInvocaciones p WHERE p.invId = :invId"),
    @NamedQuery(name = "PgeInvocaciones.findByInvCreaUsu", query = "SELECT p FROM PgeInvocaciones p WHERE p.invCreaUsu = :invCreaUsu"),
    @NamedQuery(name = "PgeInvocaciones.findByInvEnvFecha", query = "SELECT p FROM PgeInvocaciones p WHERE p.invEnvFecha = :invEnvFecha"),
    @NamedQuery(name = "PgeInvocaciones.findByInvEnvMensaje", query = "SELECT p FROM PgeInvocaciones p WHERE p.invEnvMensaje = :invEnvMensaje"),
    @NamedQuery(name = "PgeInvocaciones.findByInvEnvOk", query = "SELECT p FROM PgeInvocaciones p WHERE p.invEnvOk = :invEnvOk"),
    @NamedQuery(name = "PgeInvocaciones.findByInvOperacion", query = "SELECT p FROM PgeInvocaciones p WHERE p.invOperacion = :invOperacion"),
    @NamedQuery(name = "PgeInvocaciones.findByInvRecFecha", query = "SELECT p FROM PgeInvocaciones p WHERE p.invRecFecha = :invRecFecha"),
    @NamedQuery(name = "PgeInvocaciones.findByInvRecMensaje", query = "SELECT p FROM PgeInvocaciones p WHERE p.invRecMensaje = :invRecMensaje"),
    @NamedQuery(name = "PgeInvocaciones.findByInvRecOk", query = "SELECT p FROM PgeInvocaciones p WHERE p.invRecOk = :invRecOk"),
    @NamedQuery(name = "PgeInvocaciones.findByInvServicio", query = "SELECT p FROM PgeInvocaciones p WHERE p.invServicio = :invServicio"),
    @NamedQuery(name = "PgeInvocaciones.findByInvUrl", query = "SELECT p FROM PgeInvocaciones p WHERE p.invUrl = :invUrl")})
public class PgeInvocaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "inv_id", nullable = false)
    private Integer invId;
    @Size(max = 255)
    @Column(name = "inv_crea_usu", length = 255)
    private String invCreaUsu;
    @Column(name = "inv_env_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invEnvFecha;
    @Size(max = 4000)
    @Column(name = "inv_env_mensaje", length = 4000)
    private String invEnvMensaje;
    @Column(name = "inv_env_ok")
    private Boolean invEnvOk;
    @Size(max = 255)
    @Column(name = "inv_operacion", length = 255)
    private String invOperacion;
    @Column(name = "inv_rec_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invRecFecha;
    @Size(max = 4000)
    @Column(name = "inv_rec_mensaje", length = 4000)
    private String invRecMensaje;
    @Column(name = "inv_rec_ok")
    private Boolean invRecOk;
    @Size(max = 255)
    @Column(name = "inv_servicio", length = 255)
    private String invServicio;
    @Size(max = 255)
    @Column(name = "inv_url", length = 255)
    private String invUrl;

    public PgeInvocaciones() {
    }

    public PgeInvocaciones(Integer invId) {
        this.invId = invId;
    }

    public Integer getInvId() {
        return invId;
    }

    public void setInvId(Integer invId) {
        this.invId = invId;
    }

    public String getInvCreaUsu() {
        return invCreaUsu;
    }

    public void setInvCreaUsu(String invCreaUsu) {
        this.invCreaUsu = invCreaUsu;
    }

    public Date getInvEnvFecha() {
        return invEnvFecha;
    }

    public void setInvEnvFecha(Date invEnvFecha) {
        this.invEnvFecha = invEnvFecha;
    }

    public String getInvEnvMensaje() {
        return invEnvMensaje;
    }

    public void setInvEnvMensaje(String invEnvMensaje) {
        this.invEnvMensaje = invEnvMensaje;
    }

    public Boolean getInvEnvOk() {
        return invEnvOk;
    }

    public void setInvEnvOk(Boolean invEnvOk) {
        this.invEnvOk = invEnvOk;
    }

    public String getInvOperacion() {
        return invOperacion;
    }

    public void setInvOperacion(String invOperacion) {
        this.invOperacion = invOperacion;
    }

    public Date getInvRecFecha() {
        return invRecFecha;
    }

    public void setInvRecFecha(Date invRecFecha) {
        this.invRecFecha = invRecFecha;
    }

    public String getInvRecMensaje() {
        return invRecMensaje;
    }

    public void setInvRecMensaje(String invRecMensaje) {
        this.invRecMensaje = invRecMensaje;
    }

    public Boolean getInvRecOk() {
        return invRecOk;
    }

    public void setInvRecOk(Boolean invRecOk) {
        this.invRecOk = invRecOk;
    }

    public String getInvServicio() {
        return invServicio;
    }

    public void setInvServicio(String invServicio) {
        this.invServicio = invServicio;
    }

    public String getInvUrl() {
        return invUrl;
    }

    public void setInvUrl(String invUrl) {
        this.invUrl = invUrl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invId != null ? invId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PgeInvocaciones)) {
            return false;
        }
        PgeInvocaciones other = (PgeInvocaciones) object;
        if ((this.invId == null && other.invId != null) || (this.invId != null && !this.invId.equals(other.invId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.gestemp.entities.PgeInvocaciones[ invId=" + invId + " ]";
    }
    
}
