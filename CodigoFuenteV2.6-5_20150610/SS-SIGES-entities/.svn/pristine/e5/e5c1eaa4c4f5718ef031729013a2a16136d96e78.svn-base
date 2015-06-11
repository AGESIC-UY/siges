package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "lecc_aprendidas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeccionesAprendidas.findAll", query = "SELECT l FROM LeccionesAprendidas l"),
    @NamedQuery(name = "LeccionesAprendidas.findByLecaprPk", query = "SELECT l FROM LeccionesAprendidas l WHERE l.lecaprPk = :lecaprPk"),
    @NamedQuery(name = "LeccionesAprendidas.findByLecaprFecha", query = "SELECT l FROM LeccionesAprendidas l WHERE l.lecaprFecha = :lecaprFecha"),
    @NamedQuery(name = "LeccionesAprendidas.findByLecaprDesc", query = "SELECT l FROM LeccionesAprendidas l WHERE l.lecaprDesc = :lecaprDesc")})
public class LeccionesAprendidas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lecapr_pk")
    private Integer lecaprPk;
    @JoinColumn(name = "lecapr_proy_fk", referencedColumnName = "proy_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private Proyectos lecaprProyFk;
    @JoinColumn(name = "lecapr_usr_fk", referencedColumnName = "usu_id")
    @ManyToOne(optional = true)
    private SsUsuario usuarioFk;
    @JoinColumn(name = "lecapr_tipo_fk", referencedColumnName = "tipolec_pk")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TipoLeccion lecaprTipoFk;
    @JoinColumn(name = "lecapr_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Organismos lecaprOrgFk;
    @Basic(optional = false)
    @Column(name = "lecapr_fecha")
    @Temporal(TemporalType.DATE)
    private Date lecaprFecha;
    @Basic(optional = false)
    @Column(name = "lecapr_desc")
    private String lecaprDesc;
    @JoinTable(name = "lecapr_areacon", joinColumns = {
        @JoinColumn(name = "lecaprcon_lecapr_fk", referencedColumnName = "lecapr_pk")}, inverseJoinColumns = {
        @JoinColumn(name = "lecaprcon_con_fk", referencedColumnName = "con_pk")})
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AreaConocimiento> areaConocimientosSet;
    @Column(name = "lecapr_activo")
    private Boolean activo;

    public LeccionesAprendidas() {
    }

    public LeccionesAprendidas(Integer lecaprPk) {
        this.lecaprPk = lecaprPk;
    }

    public LeccionesAprendidas(Integer lecaprPk, Date lecaprFecha, String lecaprDesc) {
        this.lecaprPk = lecaprPk;
        this.lecaprFecha = lecaprFecha;
        this.lecaprDesc = lecaprDesc;
    }

    public Integer getLecaprPk() {
        return lecaprPk;
    }

    public void setLecaprPk(Integer lecaprPk) {
        this.lecaprPk = lecaprPk;
    }

    public Proyectos getLecaprProyFk() {
        return lecaprProyFk;
    }

    public void setLecaprProyFk(Proyectos lecaprProyFk) {
        this.lecaprProyFk = lecaprProyFk;
    }
    
    public String getLecaprProyFkTipo() {
        return lecaprProyFk != null ? "2-".concat(lecaprProyFk.getProyPk().toString()) : null;
    }

    public SsUsuario getUsuarioFk() {
        return usuarioFk;
    }

    public void setUsuarioFk(SsUsuario usuarioFk) {
        this.usuarioFk = usuarioFk;
    }

    public Date getLecaprFecha() {
        return lecaprFecha;
    }

    public void setLecaprFecha(Date lecaprFecha) {
        this.lecaprFecha = lecaprFecha;
    }

    public String getLecaprDesc() {
        return lecaprDesc;
    }

    public void setLecaprDesc(String lecaprDesc) {
        this.lecaprDesc = lecaprDesc;
    }

    public TipoLeccion getLecaprTipoFk() {
        return lecaprTipoFk;
    }

    public void setLecaprTipoFk(TipoLeccion lecaprTipoFk) {
        this.lecaprTipoFk = lecaprTipoFk;
    }

    public Organismos getLecaprOrgFk() {
        return lecaprOrgFk;
    }

    public void setLecaprOrgFk(Organismos lecaprOrgFk) {
        this.lecaprOrgFk = lecaprOrgFk;
    }

    public Set<AreaConocimiento> getAreaConocimientosSet() {
        return areaConocimientosSet;
    }

    public void setAreaConocimientosSet(Set<AreaConocimiento> areaConocimientosSet) {
        this.areaConocimientosSet = areaConocimientosSet;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo != null ? activo : true;
    }
    
    public String getAreaConocimientoStr() {
        if (areaConocimientosSet != null && !areaConocimientosSet.isEmpty()) {
            StringBuffer result = new StringBuffer("");
            for (AreaConocimiento areaCon : areaConocimientosSet) {
                result = result.append(result.toString().isEmpty() ? "" : ", ").append(areaCon.getConNombre());
            }
            return result.append(".").toString();
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lecaprPk != null ? lecaprPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeccionesAprendidas)) {
            return false;
        }
        LeccionesAprendidas other = (LeccionesAprendidas) object;
        if ((this.lecaprPk == null && other.lecaprPk != null)
                || (this.lecaprPk != null && !this.lecaprPk.equals(other.lecaprPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.LeccionesAprendidas[ lecaprPk=" + lecaprPk + " ]";
    }
    
}
