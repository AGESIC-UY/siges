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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "notificacion_envio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotificacionEnvio.findAll", query = "SELECT n FROM NotificacionEnvio n"),
    @NamedQuery(name = "NotificacionEnvio.findByNotenvPk", query = "SELECT n FROM NotificacionEnvio n WHERE n.notenvPk = :notenvPk"),
    @NamedQuery(name = "NotificacionEnvio.findByNotenvFecha", query = "SELECT n FROM NotificacionEnvio n WHERE n.notenvFecha = :notenvFecha"),
    @NamedQuery(name = "NotificacionEnvio.findByNotenvProyFk", query = "SELECT n FROM NotificacionEnvio n WHERE n.notenvProyFk = :notenvProyFk"),
    @NamedQuery(name = "NotificacionEnvio.findByNotenvNotCod", query = "SELECT n FROM NotificacionEnvio n WHERE n.notenvNotCod = :notenvNotCod")})
public class NotificacionEnvio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "notenv_pk")
    private Integer notenvPk;
    @Basic(optional = false)
    @Column(name = "notenv_fecha")
    @Temporal(TemporalType.DATE)
    private Date notenvFecha;
    @Basic(optional = false)
    @Column(name = "notenv_proy_fk")
    private int notenvProyFk;
    @Basic(optional = false)
    @Column(name = "notenv_not_cod")
    private String notenvNotCod;

    public NotificacionEnvio() {
    }

    public NotificacionEnvio(Integer notenvPk) {
        this.notenvPk = notenvPk;
    }

    public NotificacionEnvio(Integer notenvPk, Date notenvFecha, int notenvProyFk, String notenvNotCod) {
        this.notenvPk = notenvPk;
        this.notenvFecha = notenvFecha;
        this.notenvProyFk = notenvProyFk;
        this.notenvNotCod = notenvNotCod;
    }

    public Integer getNotenvPk() {
        return notenvPk;
    }

    public void setNotenvPk(Integer notenvPk) {
        this.notenvPk = notenvPk;
    }

    public Date getNotenvFecha() {
        return notenvFecha;
    }

    public void setNotenvFecha(Date notenvFecha) {
        this.notenvFecha = notenvFecha;
    }

    public int getNotenvProyFk() {
        return notenvProyFk;
    }

    public void setNotenvProyFk(int notenvProyFk) {
        this.notenvProyFk = notenvProyFk;
    }

    public String getNotenvNotCod() {
        return notenvNotCod;
    }

    public void setNotenvNotCod(String notenvNotCod) {
        this.notenvNotCod = notenvNotCod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notenvPk != null ? notenvPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof NotificacionEnvio)) {
            return false;
        }
        NotificacionEnvio other = (NotificacionEnvio) object;
        if ((this.notenvPk == null && other.notenvPk != null) || (this.notenvPk != null && !this.notenvPk.equals(other.notenvPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.NotificacionEnvio[ notenvPk=" + notenvPk + " ]";
    }
    
}
