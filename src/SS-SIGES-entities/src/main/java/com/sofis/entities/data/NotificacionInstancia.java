package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "notificacion_instancia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotificacionInstancia.findAll", query = "SELECT n FROM NotificacionInstancia n"),
    @NamedQuery(name = "NotificacionInstancia.findByNotinstPk", query = "SELECT n FROM NotificacionInstancia n WHERE n.notinstPk = :notinstPk"),
    @NamedQuery(name = "NotificacionInstancia.findByNotinstGerenteAdjunto", query = "SELECT n FROM NotificacionInstancia n WHERE n.notinstGerenteAdjunto = :notinstGerenteAdjunto"),
    @NamedQuery(name = "NotificacionInstancia.findByNotinstPmof", query = "SELECT n FROM NotificacionInstancia n WHERE n.notinstPmof = :notinstPmof"),
    @NamedQuery(name = "NotificacionInstancia.findByNotinstPmot", query = "SELECT n FROM NotificacionInstancia n WHERE n.notinstPmot = :notinstPmot"),
    @NamedQuery(name = "NotificacionInstancia.findByNotinstSponsor", query = "SELECT n FROM NotificacionInstancia n WHERE n.notinstSponsor = :notinstSponsor")})
public class NotificacionInstancia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "notinst_pk")
    private Integer notinstPk;
    @JoinColumn(name = "notinst_not_fk", referencedColumnName = "not_pk")
    @ManyToOne(optional = false)
    private Notificacion notinstNotFk;
    @JoinColumn(name = "notinst_proy_fk", referencedColumnName = "proy_pk")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private Proyectos notinstProyFk;
    @Column(name = "notinst_gerente_adjunto")
    private Boolean notinstGerenteAdjunto;
    @Column(name = "notinst_pmof")
    private Boolean notinstPmof;
    @Column(name = "notinst_pmot")
    private Boolean notinstPmot;
    @Column(name = "notinst_sponsor")
    private Boolean notinstSponsor;

    public NotificacionInstancia() {
    }

    public NotificacionInstancia(Integer notinstPk) {
        this.notinstPk = notinstPk;
    }

    public Integer getNotinstPk() {
        return notinstPk;
    }

    public void setNotinstPk(Integer notinstPk) {
        this.notinstPk = notinstPk;
    }

    public Boolean getNotinstGerenteAdjunto() {
        return notinstGerenteAdjunto;
    }

    public void setNotinstGerenteAdjunto(Boolean notinstGerenteAdjunto) {
        this.notinstGerenteAdjunto = notinstGerenteAdjunto;
    }

    public Boolean getNotinstPmof() {
        return notinstPmof;
    }

    public void setNotinstPmof(Boolean notinstPmof) {
        this.notinstPmof = notinstPmof;
    }

    public Boolean getNotinstPmot() {
        return notinstPmot;
    }

    public void setNotinstPmot(Boolean notinstPmot) {
        this.notinstPmot = notinstPmot;
    }

    public Boolean getNotinstSponsor() {
        return notinstSponsor;
    }

    public void setNotinstSponsor(Boolean notinstSponsor) {
        this.notinstSponsor = notinstSponsor;
    }

    public Notificacion getNotinstNotFk() {
        return notinstNotFk;
    }

    public void setNotinstNotFk(Notificacion notinstNotFk) {
        this.notinstNotFk = notinstNotFk;
    }

    public Proyectos getNotinstProyFk() {
        return notinstProyFk;
    }

    public void setNotinstProyFk(Proyectos notinstProyFk) {
        this.notinstProyFk = notinstProyFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notinstPk != null ? notinstPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof NotificacionInstancia)) {
            return false;
        }
        NotificacionInstancia other = (NotificacionInstancia) object;
        if ((this.notinstPk == null && other.notinstPk != null) || (this.notinstPk != null && !this.notinstPk.equals(other.notinstPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.NotificacionInstancia[ notinstPk=" + notinstPk + " ]";
    }
    
}
