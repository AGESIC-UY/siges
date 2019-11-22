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
import javax.persistence.OneToMany;
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
@Table(name = "causales_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CausalCompra.findAll", query = "SELECT o FROM CausalCompra o")
    ,
    @NamedQuery(name = "CausalCompra.findByFueProComPk", query = "SELECT o FROM CausalCompra o WHERE o.cauComPk = :cauComPk")
    ,
    @NamedQuery(name = "CausalCompra.findByFueProComNombre", query = "SELECT o FROM CausalCompra o WHERE o.cauComNombre = :cauComNombre")
    ,
    @NamedQuery(name = "CausalCompra.findByFueProComDescripcion", query = "SELECT o FROM CausalCompra o WHERE o.cauComDescripcion = :cauComDescripcion")
})
public class CausalCompra implements Serializable {

    public static final int NOMBRE_LENGHT = 100;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cau_com_pk")
    private Integer cauComPk;
    @Size(max = 100)
    @Column(name = "cau_com_nombre")
    private String cauComNombre;
    @Size(max = 300)
    @Column(name = "cau_com_descripcion")
    private String cauComDescripcion;
    @Column(name = "cau_com_habilitado")
    private Boolean cauComHabilitado;
    @ManyToOne(optional = false)
    @JoinColumn(name = "cau_com_org_fk", referencedColumnName = "org_pk")
    private Organismos cauComOrgFk;

    @PreUpdate
    public void preUpdate() {
        this.cauComNombre = StringsUtils.normalizarString(this.cauComNombre);
        this.cauComDescripcion = StringsUtils.normalizarString(this.cauComDescripcion);
    }

    @PrePersist
    public void prePersist() {
        this.cauComNombre = StringsUtils.normalizarString(this.cauComNombre);
        this.cauComDescripcion = StringsUtils.normalizarString(this.cauComDescripcion);
    }

    public CausalCompra() {
    }

    public CausalCompra(Integer cauComPk) {
        this.cauComPk = cauComPk;
    }

    public Integer getCauComPk() {
        return cauComPk;
    }

    public void setCauComPk(Integer cauComPk) {
        this.cauComPk = cauComPk;
    }

    public String getCauComNombre() {
        return cauComNombre;
    }

    public void setCauComNombre(String cauComNombre) {
        this.cauComNombre = cauComNombre;
    }

    public String getCauComDescripcion() {
        return cauComDescripcion;
    }

    public void setCauComDescripcion(String cauComDescripcion) {
        this.cauComDescripcion = cauComDescripcion;
    }

    public Boolean getCauComHabilitado() {
        return cauComHabilitado;
    }

    public void setCauComHabilitado(Boolean cauComHabilitado) {
        this.cauComHabilitado = cauComHabilitado;
    }

    public Organismos getCauComOrgFk() {
        return cauComOrgFk;
    }

    public void setCauComOrgFk(Organismos orgFk) {
        this.cauComOrgFk = orgFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cauComPk != null ? cauComPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CausalCompra)) {
            return false;
        }
        CausalCompra other = (CausalCompra) object;
        if ((this.cauComPk == null && other.cauComPk != null) || (this.cauComPk != null && !this.cauComPk.equals(other.cauComPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.CausalCompra[ cauComPk=" + cauComPk + " ]";
    }

}
