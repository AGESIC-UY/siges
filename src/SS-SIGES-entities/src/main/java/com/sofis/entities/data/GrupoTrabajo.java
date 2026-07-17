package com.sofis.entities.data;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "grupos_trabajo")
@XmlRootElement
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GrupoTrabajo implements Serializable {

    public static final int NOMBRE_LENGHT = 100;
    public static final int PROC_COMPRA_LENGHT = 20;
    public static final int PROC_COMPRA_GRP_LENGHT = 20;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gtb_pk")
    private Integer gtbPk;

    @Basic(optional = false)
    @Column(name = "gtb_nombre")
    private String gtbNombre;

    @JoinColumn(name = "gtb_organismo_fk", referencedColumnName = "org_pk")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Organismos gtbOrgFk;

    @Column(name = "gtb_habilitado")
    private Boolean gtbHabilitado;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "gtoGrupoFk")
    @Fetch(FetchMode.SELECT)
    private Set<GrupoTrabajoOperacionDetalle> grupoTrabajoOperacionDetalle;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "gtuGrupoFk")
    @Fetch(FetchMode.SELECT)
    private Set<GrupoTrabajoUsuario> grupoTrabajoUsuario;

    public int hashCode() {
        int hash = 0;
        hash += (gtbPk != null ? gtbPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof GrupoTrabajo)) {
            return false;
        }
        GrupoTrabajo other = (GrupoTrabajo) object;
        if (gtbPk == null || other.gtbPk == null) {
            return this == object;
        }

        if ((this.gtbPk == null && other.gtbPk != null) || (this.gtbPk != null && !this.gtbPk.equals(other.gtbPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Adquisicion[ adqPk=" + gtbPk + " ]";
    }

    public Integer getGtbPk() {
        return gtbPk;
    }

    public void setGtbPk(Integer gtbPk) {
        this.gtbPk = gtbPk;
    }

    public String getGtbNombre() {
        return gtbNombre;
    }

    public void setGtbNombre(String gtbNombre) {
        this.gtbNombre = gtbNombre;
    }

    public Organismos getGtbOrgFk() {
        return gtbOrgFk;
    }

    public void setGtbOrgFk(Organismos gtbOrgFk) {
        this.gtbOrgFk = gtbOrgFk;
    }

    public Boolean getGtbHabilitado() {
        return gtbHabilitado;
    }

    public void setGtbHabilitado(Boolean gtbHabilitado) {
        this.gtbHabilitado = gtbHabilitado;
    }

    public Set<GrupoTrabajoOperacionDetalle> getGrupoTrabajoOperacionDetalle() {
        return grupoTrabajoOperacionDetalle;
    }

    public void setGrupoTrabajoOperacionDetalle(Set<GrupoTrabajoOperacionDetalle> grupoTrabajoOperacionDetalle) {
        this.grupoTrabajoOperacionDetalle = grupoTrabajoOperacionDetalle;
    }

    public Set<GrupoTrabajoUsuario> getGrupoTrabajoUsuario() {
        return grupoTrabajoUsuario;
    }

    public void setGrupoTrabajoUsuario(Set<GrupoTrabajoUsuario> grupoTrabajoUsuario) {
        this.grupoTrabajoUsuario = grupoTrabajoUsuario;
    }
    
    

}
