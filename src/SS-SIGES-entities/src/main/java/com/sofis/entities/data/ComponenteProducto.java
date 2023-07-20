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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "componente_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComponenteProducto.findAll", query = "SELECT c FROM ComponenteProducto c"),
    @NamedQuery(name = "ComponenteProducto.findByComPk", query = "SELECT c FROM ComponenteProducto c WHERE c.comPk = :comPk"),
    @NamedQuery(name = "ComponenteProducto.findByComNombre", query = "SELECT c FROM ComponenteProducto c WHERE c.comNombre = :comNombre"),
    @NamedQuery(name = "ComponenteProducto.findByOrgPk", query = "SELECT c FROM ComponenteProducto c WHERE c.comOrgFk.orgPk = :orgPk ORDER BY c.comNombre"),
})
public class ComponenteProducto implements Serializable {
    
    public static final int NOMBRE_LENGHT = 300;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "com_pk")
    private Integer comPk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "com_nombre")
    private String comNombre;
    
    @Column(name = "com_habilitada")
    private Boolean comHabilitada;
    
    @JoinColumn(name = "com_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos comOrgFk;

    public ComponenteProducto() {
    }

    public ComponenteProducto(Integer comPk) {
        this.comPk = comPk;
    }

    public ComponenteProducto(Integer comPk, String comNombre) {
        this.comPk = comPk;
        this.comNombre = comNombre;
    }

    public Integer getComPk() {
        return comPk;
    }

    public void setComPk(Integer comPk) {
        this.comPk = comPk;
    }

    public String getComNombre() {
        return comNombre;
    }

    public void setComNombre(String comNombre) {
        this.comNombre = comNombre;
    }

    public Boolean getComHabilitada() {
        return comHabilitada;
    }

    public void setComHabilitada(Boolean comHabilitada) {
        this.comHabilitada = comHabilitada;
    }
    
    public Organismos getComOrgFk() {
        return comOrgFk;
    }

    public void setComOrgFk(Organismos comOrgFk) {
        this.comOrgFk = comOrgFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comPk != null ? comPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof ComponenteProducto)) {
            return false;
        }
        ComponenteProducto other = (ComponenteProducto) object;
        if ((this.comPk == null && other.comPk != null) || (this.comPk != null && !this.comPk.equals(other.comPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ComponenteProducto[ comPk=" + comPk + " ]";
    }

}
