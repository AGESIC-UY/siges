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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "areas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Areas.findAll", query = "SELECT a FROM Areas a")})
public class Areas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_pk")
    private Integer areaPk;
    @Column(name = "area_nombre")
    private String areaNombre;
    @Column(name = "area_abreviacion")
    private String areaAbreviacion;
    @JoinColumn(name = "area_director", referencedColumnName = "usu_id")
    @ManyToOne(optional = true)
    private SsUsuario areaDirectorPk;
    @JoinColumn(name = "area_padre", referencedColumnName = "area_pk")
    @ManyToOne(optional = true)
    private Areas areaPadre;
    @JoinColumn(name = "area_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos areaOrgFk;

    public Areas() {
    }

    public Areas(Integer areaPk) {
        this.areaPk = areaPk;
    }

    public Integer getAreaPk() {
        return areaPk;
    }

    public void setAreaPk(Integer areaPk) {
        this.areaPk = areaPk;
    }

    public String getAreaNombre() {
        return areaNombre;
    }

    public void setAreaNombre(String areaNombre) {
        this.areaNombre = areaNombre;
    }

    public String getAreaAbreviacion() {
        return areaAbreviacion;
    }

    public void setAreaAbreviacion(String areaAbreviacion) {
        this.areaAbreviacion = areaAbreviacion;
    }

    public SsUsuario getAreaDirectorPk() {
        return areaDirectorPk;
    }

    public void setAreaDirectorPk(SsUsuario areaDirectorPk) {
        this.areaDirectorPk = areaDirectorPk;
    }

    public Areas getAreaPadre() {
        return areaPadre;
    }

    public void setAreaPadre(Areas areaPadre) {
        this.areaPadre = areaPadre;
    }

    public Organismos getAreaOrgFk() {
        return areaOrgFk;
    }

    public void setAreaOrgFk(Organismos areaOrgFk) {
        this.areaOrgFk = areaOrgFk;
    }
    
    public String getAreaPadresStr() {
        if (areaPadre != null) {
            String s = "";
            Areas area = this;
            while (area != null && area.areaPadre != null) {
                s = area.areaPadre.getAreaNombre() + (s.isEmpty() ? "" : "->") + s;
                area = area.areaPadre;
            }
            return s;
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (areaPk != null ? areaPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Areas)) {
            return false;
        }
        Areas other = (Areas) object;
        if ((this.areaPk == null && other.areaPk != null) || (this.areaPk != null && !this.areaPk.equals(other.areaPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Areas[ areaPk=" + areaPk + " ]";
    }
    
}
