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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Entity
@Table(name = "proy_publica_hist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProyPublicaHist.findAll", query = "SELECT p FROM ProyPublicaHist p"),
    @NamedQuery(name = "ProyPublicaHist.findByProyPublicaPk", query = "SELECT p FROM ProyPublicaHist p WHERE p.proyPublicaPk = :proyPublicaPk"),
    @NamedQuery(name = "ProyPublicaHist.findByProyPublicaFecha", query = "SELECT p FROM ProyPublicaHist p WHERE p.proyPublicaFecha = :proyPublicaFecha")})
public class ProyPublicaHist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proy_publica_pk")
    private Integer proyPublicaPk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proy_publica_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date proyPublicaFecha;

    @JoinColumn(name = "proy_publica_proy_fk", referencedColumnName = "proy_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private Proyectos proyectoFk;

    @JoinColumn(name = "proy_publica_usu_fk", referencedColumnName = "usu_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private SsUsuario usuarioFk;

    public ProyPublicaHist() {
    }

    public ProyPublicaHist(Integer proyPublicaPk) {
        this.proyPublicaPk = proyPublicaPk;
    }

    public ProyPublicaHist(Integer proyPublicaPk, Date proyPublicaFecha) {
        this.proyPublicaPk = proyPublicaPk;
        this.proyPublicaFecha = proyPublicaFecha;
    }

    public ProyPublicaHist(Integer proyPublicaPk, Date proyPublicaFecha, Proyectos proyectoFk, SsUsuario usuarioFk) {
        this.proyPublicaPk = proyPublicaPk;
        this.proyPublicaFecha = proyPublicaFecha;
        this.proyectoFk = proyectoFk;
        this.usuarioFk = usuarioFk;
    }

    public Integer getProyPublicaPk() {
        return proyPublicaPk;
    }

    public void setProyPublicaPk(Integer proyPublicaPk) {
        this.proyPublicaPk = proyPublicaPk;
    }

    public Date getProyPublicaFecha() {
        return proyPublicaFecha;
    }

    public void setProyPublicaFecha(Date proyPublicaFecha) {
        this.proyPublicaFecha = proyPublicaFecha;
    }

    public Proyectos getProyectoFk() {
        return proyectoFk;
    }

    public void setProyectoFk(Proyectos proyectoFk) {
        this.proyectoFk = proyectoFk;
    }

    public SsUsuario getUsuarioFk() {
        return usuarioFk;
    }

    public void setUsuarioFk(SsUsuario usuarioFk) {
        this.usuarioFk = usuarioFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proyPublicaPk != null ? proyPublicaPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof ProyPublicaHist)) {
            return false;
        }
        ProyPublicaHist other = (ProyPublicaHist) object;
        if ((this.proyPublicaPk == null && other.proyPublicaPk != null) || (this.proyPublicaPk != null && !this.proyPublicaPk.equals(other.proyPublicaPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ProyPublicaHist[ proyPublicaPk=" + proyPublicaPk + " ]";
    }

}
