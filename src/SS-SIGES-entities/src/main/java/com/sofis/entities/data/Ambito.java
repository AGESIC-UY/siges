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
@Table(name = "ambito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ambito.findAll", query = "SELECT a FROM Ambito a"),
    @NamedQuery(name = "Ambito.findByAmbPk", query = "SELECT a FROM Ambito a WHERE a.ambPk = :ambPk"),
    @NamedQuery(name = "Ambito.findByAmbNombre", query = "SELECT a FROM Ambito a WHERE a.ambNombre = :ambNombre")})
public class Ambito implements Serializable {
    
    public static final int NOMBRE_LENGHT = 100;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "amb_pk")
    private Integer ambPk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amb_nombre")
    private String ambNombre;
    @JoinColumn(name = "amb_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos ambOrgFk;

    public Ambito() {
    }

    public Ambito(Integer ambPk) {
        this.ambPk = ambPk;
    }

    public Ambito(Integer ambPk, String ambNombre) {
        this.ambPk = ambPk;
        this.ambNombre = ambNombre;
    }

    public Integer getAmbPk() {
        return ambPk;
    }

    public void setAmbPk(Integer ambPk) {
        this.ambPk = ambPk;
    }

    public String getAmbNombre() {
        return ambNombre;
    }

    public void setAmbNombre(String ambNombre) {
        this.ambNombre = ambNombre;
    }

    public Organismos getAmbOrgFk() {
        return ambOrgFk;
    }

    public void setAmbOrgFk(Organismos ambOrgFk) {
        this.ambOrgFk = ambOrgFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ambPk != null ? ambPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Ambito)) {
            return false;
        }
        Ambito other = (Ambito) object;
        if ((this.ambPk == null && other.ambPk != null) || (this.ambPk != null && !this.ambPk.equals(other.ambPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Ambito[ ambPk=" + ambPk + " ]";
    }

}
