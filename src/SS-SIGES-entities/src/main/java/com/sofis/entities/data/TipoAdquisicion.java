package com.sofis.entities.data;

import com.sofis.generico.utils.generalutils.StringsUtils;
import java.io.Serializable;
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Entity
@Table(name = "tipos_adquisicion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAdquisicion.findAll", query = "SELECT o FROM TipoAdquisicion o")
    ,
    @NamedQuery(name = "TipoAdquisicion.findByTipAdqPk", query = "SELECT o FROM TipoAdquisicion o WHERE o.tipAdqPk = :tipAdqPk")
    ,
    @NamedQuery(name = "TipoAdquisicion.findByTipAdqNombre", query = "SELECT o FROM TipoAdquisicion o WHERE o.tipAdqNombre = :tipAdqNombre")
    ,
    @NamedQuery(name = "TipoAdquisicion.findByTipAdqDescripcion", query = "SELECT o FROM TipoAdquisicion o WHERE o.tipAdqDescripcion = :tipAdqDescripcion")
})
public class TipoAdquisicion implements Serializable {

    public static final int NOMBRE_LENGHT = 100;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tip_adq_pk")
    private Integer tipAdqPk;
    @Size(max = 100)
    @Column(name = "tip_adq_nombre")
    private String tipAdqNombre;
    @Size(max = 300)
    @Column(name = "tip_adq_descripcion")
    private String tipAdqDescripcion;
    @Column(name = "tip_adq_habilitado")
    private Boolean tipAdqHabilitado;
    @ManyToOne(optional = false)
    @JoinColumn(name = "tip_adq_org_fk", referencedColumnName = "org_pk")
    private Organismos tipAdqOrgFk;

    @PreUpdate
    public void preUpdate() {
        this.tipAdqNombre = StringsUtils.normalizarString(this.tipAdqNombre);
        this.tipAdqDescripcion = StringsUtils.normalizarString(this.tipAdqDescripcion);
    }

    @PrePersist
    public void prePersist() {
        this.tipAdqNombre = StringsUtils.normalizarString(this.tipAdqNombre);
        this.tipAdqDescripcion = StringsUtils.normalizarString(this.tipAdqDescripcion);
    }

    public TipoAdquisicion() {
    }

    public TipoAdquisicion(Integer tipAdqPk) {
        this.tipAdqPk = tipAdqPk;
    }

    public Integer getTipAdqPk() {
        return tipAdqPk;
    }

    public void setTipAdqPk(Integer tipAdqPk) {
        this.tipAdqPk = tipAdqPk;
    }

    public String getTipAdqNombre() {
        return tipAdqNombre;
    }

    public void setTipAdqNombre(String tipAdqNombre) {
        this.tipAdqNombre = tipAdqNombre;
    }

    public String getTipAdqDescripcion() {
        return tipAdqDescripcion;
    }

    public void setTipAdqDescripcion(String tipAdqDescripcion) {
        this.tipAdqDescripcion = tipAdqDescripcion;
    }

    public Boolean getTipAdqHabilitado() {
        return tipAdqHabilitado;
    }

    public void setTipAdqHabilitado(Boolean tipAdqHabilitado) {
        this.tipAdqHabilitado = tipAdqHabilitado;
    }

    public Organismos getTipAdqOrgFk() {
        return tipAdqOrgFk;
    }

    public void setTipAdqOrgFk(Organismos tipAdqOrgFk) {
        this.tipAdqOrgFk = tipAdqOrgFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipAdqPk != null ? tipAdqPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAdquisicion)) {
            return false;
        }
        TipoAdquisicion other = (TipoAdquisicion) object;
        if ((this.tipAdqPk == null && other.tipAdqPk != null) || (this.tipAdqPk != null && !this.tipAdqPk.equals(other.tipAdqPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.TipoAdquisicion[ tipAdqPk=" + tipAdqPk + " ]";
    }

}
