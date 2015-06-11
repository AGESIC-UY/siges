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
@Table(name = "area_conocimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AreaConocimiento.findAll", query = "SELECT a FROM AreaConocimiento a"),
    @NamedQuery(name = "AreaConocimiento.findByConPk", query = "SELECT a FROM AreaConocimiento a WHERE a.conPk = :conPk"),
    @NamedQuery(name = "AreaConocimiento.findByConNombre", query = "SELECT a FROM AreaConocimiento a WHERE a.conNombre = :conNombre")})
public class AreaConocimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "con_pk")
    private Integer conPk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "con_nombre")
    private String conNombre;
    @JoinColumn(name = "con_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos conOrganismo;
    @JoinColumn(name = "con_padre_fk", referencedColumnName = "con_pk", nullable = true)
    @ManyToOne(optional = true)
    private AreaConocimiento areaConPadreFk;

    public AreaConocimiento() {
    }

    public AreaConocimiento(Integer conPk) {
        this.conPk = conPk;
    }

    public AreaConocimiento(Integer conPk, String conNombre) {
        this.conPk = conPk;
        this.conNombre = conNombre;
    }

    public Integer getConPk() {
        return conPk;
    }

    public void setConPk(Integer conPk) {
        this.conPk = conPk;
    }

    public String getConNombre() {
        return conNombre;
    }

    public void setConNombre(String conNombre) {
        this.conNombre = conNombre;
    }

    public Organismos getConOrganismo() {
        return conOrganismo;
    }

    public void setConOrganismo(Organismos conOrganismo) {
        this.conOrganismo = conOrganismo;
    }

    public AreaConocimiento getAreaConPadreFk() {
        return areaConPadreFk;
    }

    public void setAreaConPadreFk(AreaConocimiento areaConPadreFk) {
        this.areaConPadreFk = areaConPadreFk;
    }

    public String getAreasPadresStr() {
        if (areaConPadreFk != null) {
            String s = "";
            AreaConocimiento ac = this;
            while (ac != null && ac.areaConPadreFk != null) {
                s = ac.areaConPadreFk.getConNombre() + (s.isEmpty() ? "" : "->") + s;
                ac = ac.areaConPadreFk;
            }
            return s;
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conPk != null ? conPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreaConocimiento)) {
            return false;
        }
        AreaConocimiento other = (AreaConocimiento) object;
        if ((this.conPk == null && other.conPk != null)
                || (this.conPk != null && !this.conPk.equals(other.conPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.AreaConocimiento[ conPk=" + conPk + " ]";
    }
}
