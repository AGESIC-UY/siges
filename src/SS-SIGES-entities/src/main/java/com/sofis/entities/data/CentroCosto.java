/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "centros_costo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CentroCosto.findAll", query = "SELECT o FROM CentroCosto o")
    ,
    @NamedQuery(name = "CentroCosto.findByCenCosPk", query = "SELECT o FROM CentroCosto o WHERE o.cenCosPk = :cenCosPk")
    ,
    @NamedQuery(name = "CentroCosto.findByCenCosNombre", query = "SELECT o FROM CentroCosto o WHERE o.cenCosNombre = :cenCosNombre")
    ,
    @NamedQuery(name = "CentroCosto.findByCenCosDescripcion", query = "SELECT o FROM CentroCosto o WHERE o.cenCosDescripcion = :cenCosDescripcion")
})
public class CentroCosto implements Serializable {

    public static final int NOMBRE_LENGHT = 100;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cen_cos_pk")
    private Integer cenCosPk;
    @Size(max = 100)
    @Column(name = "cen_cos_nombre")
    private String cenCosNombre;
    @Size(max = 300)
    @Column(name = "cen_cos_descripcion")
    private String cenCosDescripcion;
    @Column(name = "cen_cos_habilitado")
    private Boolean cenCosHabilitado;
    
    @JoinColumn(name = "cen_cos_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos cenCosOrgFk;

    @PreUpdate
    public void preUpdate() {
        this.cenCosNombre = StringsUtils.normalizarString(this.cenCosNombre);
        this.cenCosDescripcion = StringsUtils.normalizarString(this.cenCosDescripcion);
    }

    @PrePersist
    public void prePersist() {
        this.cenCosNombre = StringsUtils.normalizarString(this.cenCosNombre);
        this.cenCosDescripcion = StringsUtils.normalizarString(this.cenCosDescripcion);
    }

    public CentroCosto() {
    }

    public CentroCosto(Integer cenCosPk) {
        this.cenCosPk = cenCosPk;
    }

    public Integer getCenCosPk() {
        return cenCosPk;
    }

    public void setCenCosPk(Integer cenCosPk) {
        this.cenCosPk = cenCosPk;
    }

    public String getCenCosNombre() {
        return cenCosNombre;
    }

    public void setCenCosNombre(String cenCosNombre) {
        this.cenCosNombre = cenCosNombre;
    }

    public String getCenCosDescripcion() {
        return cenCosDescripcion;
    }

    public void setCenCosDescripcion(String cenCosDescripcion) {
        this.cenCosDescripcion = cenCosDescripcion;
    }

    public Boolean getCenCosHabilitado() {
        return cenCosHabilitado;
    }

    public void setCenCosHabilitado(Boolean cenCosHabilitado) {
        this.cenCosHabilitado = cenCosHabilitado;
    }

    public Organismos getCenCosOrgFk() {
        return cenCosOrgFk;
    }

    public void setCenCosOrgFk(Organismos cenCosOrgFk) {
        this.cenCosOrgFk = cenCosOrgFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cenCosPk != null ? cenCosPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CentroCosto)) {
            return false;
        }
        CentroCosto other = (CentroCosto) object;
        if ((this.cenCosPk == null && other.cenCosPk != null) || (this.cenCosPk != null && !this.cenCosPk.equals(other.cenCosPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.CentroCosto[ cenCosPk=" + cenCosPk + " ]";
    }

}
