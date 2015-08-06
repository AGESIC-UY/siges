package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "proy_sitact_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProySitactHistorico.findAll", query = "SELECT p FROM ProySitactHistorico p"),
    @NamedQuery(name = "ProySitactHistorico.findByProySitactHistPk", query = "SELECT p FROM ProySitactHistorico p WHERE p.proySitactHistPk = :proySitactHistPk"),
    @NamedQuery(name = "ProySitactHistorico.findByProySitactFecha", query = "SELECT p FROM ProySitactHistorico p WHERE p.proySitactFecha = :proySitactFecha"),
    @NamedQuery(name = "ProySitactHistorico.findByProySitactDesc", query = "SELECT p FROM ProySitactHistorico p WHERE p.proySitactDesc = :proySitactDesc")})
public class ProySitactHistorico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proy_sitact_hist_pk")
    private Integer proySitactHistPk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proy_sitact_fecha")
    @Temporal(TemporalType.DATE)
    private Date proySitactFecha;
    @Column(name = "proy_sitact_desc")
    private String proySitactDesc;
    @JoinColumn(name = "proy_sitact_proy_fk", referencedColumnName = "proy_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private Proyectos proyectoFk;
    @JoinColumn(name = "proy_sitact_usu_fk", referencedColumnName = "usu_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private SsUsuario proySitactUsuario;

    public ProySitactHistorico() {
    }

    public ProySitactHistorico(Integer proySitactHistPk) {
        this.proySitactHistPk = proySitactHistPk;
    }

    public ProySitactHistorico(Integer proySitactHistPk, Date proySitactFecha) {
        this.proySitactHistPk = proySitactHistPk;
        this.proySitactFecha = proySitactFecha;
    }

    public Integer getProySitactHistPk() {
        return proySitactHistPk;
    }

    public void setProySitactHistPk(Integer proySitactHistPk) {
        this.proySitactHistPk = proySitactHistPk;
    }

    public Date getProySitactFecha() {
        return proySitactFecha;
    }

    public void setProySitactFecha(Date proySitactFecha) {
        this.proySitactFecha = proySitactFecha;
    }

    public String getProySitactDesc() {
        return proySitactDesc;
    }

    public void setProySitactDesc(String proySitactDesc) {
        this.proySitactDesc = proySitactDesc;
    }

    public Proyectos getProyectoFk() {
        return proyectoFk;
    }

    public void setProyectoFk(Proyectos proyectoFk) {
        this.proyectoFk = proyectoFk;
    }

    public SsUsuario getProySitactUsuario() {
        return proySitactUsuario;
    }

    public void setProySitactUsuario(SsUsuario proySitactUsuario) {
        this.proySitactUsuario = proySitactUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proySitactHistPk != null ? proySitactHistPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProySitactHistorico)) {
            return false;
        }
        ProySitactHistorico other = (ProySitactHistorico) object;
        if ((this.proySitactHistPk == null && other.proySitactHistPk != null) || (this.proySitactHistPk != null && !this.proySitactHistPk.equals(other.proySitactHistPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ProySitactHistorico[ proySitactHistPk=" + proySitactHistPk + " ]";
    }
}
