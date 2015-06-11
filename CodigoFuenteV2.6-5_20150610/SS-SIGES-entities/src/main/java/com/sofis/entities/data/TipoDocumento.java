package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tipo_documento")
@XmlRootElement
public class TipoDocumento implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipdoc_pk")
    private Integer tipdocPk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipodoc_nombre")
    private String tipodocNombre;
    @Column(name = "tipodoc_exigido_desde")
    private Integer tipodocExigidoDesde;
    @Column(name = "tipodoc_peso")
    private Integer tipodocPeso;
    @JoinColumn(name = "tipodoc_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos tipoDocOrgFk;
    
    @Transient
    private String exigidoDesdeStr;
    
    @Column(name = "tipodoc_resum_ejecutivo")
    private Boolean tipodocResumenEjecutivo;

    public TipoDocumento() {
    }

    public TipoDocumento(Integer tipdocPk) {
        this.tipdocPk = tipdocPk;
    }

    public TipoDocumento(Integer tipdocPk, String tipodocNombre) {
        this.tipdocPk = tipdocPk;
        this.tipodocNombre = tipodocNombre;
    }

    public Integer getTipdocPk() {
        return tipdocPk;
    }

    public Organismos getTipoDocOrgFk() {
        return tipoDocOrgFk;
    }

    public void setTipoDocOrgFk(Organismos tipoDocOrgFk) {
        this.tipoDocOrgFk = tipoDocOrgFk;
    }

    public String getExigidoDesdeStr() {
        return exigidoDesdeStr;
    }

    public void setExigidoDesdeStr(String exigidoDesdeStr) {
        this.exigidoDesdeStr = exigidoDesdeStr;
    }

    public void setTipdocPk(Integer tipdocPk) {
        this.tipdocPk = tipdocPk;
    }

    public String getTipodocNombre() {
        return tipodocNombre;
    }

    public void setTipodocNombre(String tipodocNombre) {
        this.tipodocNombre = tipodocNombre;
    }

  

    public Integer getTipodocPeso() {
        return tipodocPeso;
    }

    public void setTipodocPeso(Integer tipodocPeso) {
        this.tipodocPeso = tipodocPeso;
    }

    public Boolean getTipodocResumenEjecutivo() {
        return tipodocResumenEjecutivo;
    }

    public void setTipodocResumenEjecutivo(Boolean tipodocResumenEjecutivo) {
        this.tipodocResumenEjecutivo = tipodocResumenEjecutivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipdocPk != null ? tipdocPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.tipdocPk == null && other.tipdocPk != null) || (this.tipdocPk != null && !this.tipdocPk.equals(other.tipdocPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.TipoDocumento[ tipdocPk=" + tipdocPk + " ]";
    }

    public Integer getTipodocExigidoDesde() {
        return tipodocExigidoDesde;
    }

    public void setTipodocExigidoDesde(Integer tipodocExigidoDesde) {
        this.tipodocExigidoDesde = tipodocExigidoDesde;
    }
    
}
