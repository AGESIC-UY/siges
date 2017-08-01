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
@Table(name = "temas_calidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TemasCalidad.findAll", query = "SELECT t FROM TemasCalidad t"),
    @NamedQuery(name = "TemasCalidad.findByTcaPk", query = "SELECT t FROM TemasCalidad t WHERE t.tcaPk = :tcaPk"),
    @NamedQuery(name = "TemasCalidad.findByTcaNombre", query = "SELECT t FROM TemasCalidad t WHERE t.tcaNombre = :tcaNombre")})
public class TemasCalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tca_pk")
    private Integer tcaPk;
    @Basic(optional = false)
    @Column(name = "tca_nombre")
    private String tcaNombre;
    @JoinColumn(name = "tca_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos tcaOrgFk;

    public TemasCalidad() {
    }

    public TemasCalidad(Integer tcaPk) {
        this.tcaPk = tcaPk;
    }

    public TemasCalidad(Integer tcaPk, String tcaNombre) {
        this.tcaPk = tcaPk;
        this.tcaNombre = tcaNombre;
    }

    public Integer getTcaPk() {
        return tcaPk;
    }

    public void setTcaPk(Integer tcaPk) {
        this.tcaPk = tcaPk;
    }

    public String getTcaNombre() {
        return tcaNombre;
    }

    public void setTcaNombre(String tcaNombre) {
        this.tcaNombre = tcaNombre;
    }

    public Organismos getTcaOrgFk() {
        return tcaOrgFk;
    }

    public void setTcaOrgFk(Organismos tcaOrgFk) {
        this.tcaOrgFk = tcaOrgFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tcaPk != null ? tcaPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof TemasCalidad)) {
            return false;
        }
        TemasCalidad other = (TemasCalidad) object;
        if ((this.tcaPk == null && other.tcaPk != null) || (this.tcaPk != null && !this.tcaPk.equals(other.tcaPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.TemasCalidad[ tcaPk=" + tcaPk + " ]";
    }
    
}
