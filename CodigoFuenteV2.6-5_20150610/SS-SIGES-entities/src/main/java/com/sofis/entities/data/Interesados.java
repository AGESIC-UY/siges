package com.sofis.entities.data;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "interesados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Interesados.findAll", query = "SELECT i FROM Interesados i"),
    @NamedQuery(name = "Interesados.findByIntPk", query = "SELECT i FROM Interesados i WHERE i.intPk = :intPk"),
    @NamedQuery(name = "Interesados.findByIntObservaciones", query = "SELECT i FROM Interesados i WHERE i.intObservaciones = :intObservaciones")})
public class Interesados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "int_pk")
    private Integer intPk;
    @Column(name = "int_observaciones")
    private String intObservaciones;
    @JoinColumn(name = "int_orga_fk", referencedColumnName = "orga_pk")
    @ManyToOne(optional = true)
    private OrganiIntProve intOrgaFk;
    @JoinColumn(name = "int_rolint_fk", referencedColumnName = "rolint_pk")
    @ManyToOne(optional = true)
    private RolesInteresados intRolintFk;
    @JoinColumn(name = "int_pers_fk", referencedColumnName = "pers_pk")
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Personas intPersonaFk;

    @JoinColumn(name = "int_ent_fk", referencedColumnName = "ent_pk", nullable = true)
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Entregables intEntregable;

    public Interesados() {
    }

    public Interesados(Integer intPk) {
        this.intPk = intPk;
    }

    public Integer getIntPk() {
        return intPk;
    }

    public void setIntPk(Integer intPk) {
        this.intPk = intPk;
    }

    public String getIntObservaciones() {
        return intObservaciones;
    }

    public void setIntObservaciones(String intObservaciones) {
        this.intObservaciones = intObservaciones;
    }

    public OrganiIntProve getIntOrgaFk() {
        return intOrgaFk;
    }

    public void setIntOrgaFk(OrganiIntProve intOrgaFk) {
        this.intOrgaFk = intOrgaFk;
    }

    public RolesInteresados getIntRolintFk() {
        return intRolintFk;
    }

    public void setIntRolintFk(RolesInteresados intRolintFk) {
        this.intRolintFk = intRolintFk;
    }

    public Personas getIntPersonaFk() {
        return intPersonaFk;
    }

    public void setIntPersonaFk(Personas intPersonaFk) {
        this.intPersonaFk = intPersonaFk;
    }

    public Entregables getIntEntregable() {
        return intEntregable;
    }

    public void setIntEntregable(Entregables intEntregable) {
        this.intEntregable = intEntregable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (intPk != null ? intPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Interesados)) {
            return false;
        }
        Interesados other = (Interesados) object;
        if ((this.intPk == null && other.intPk != null) || (this.intPk != null && !this.intPk.equals(other.intPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Interesados[ intPk=" + intPk + " ]";
    }

    public void toSystemOut() {
        System.out.println("-- Interesados --");
        System.out.println("Rol:" + this.intRolintFk != null ? this.intRolintFk.getRolintNombre() : "null");
        System.out.println("Orga:" + this.intOrgaFk != null ? this.intOrgaFk.getOrgaNombre() : "null");
        System.out.println("Persona null:" + this.intPersonaFk == null);
        System.out.println("Persona.n:" + this.intPersonaFk.getPersNombre());
        System.out.println("Persona.c:" + this.intPersonaFk.getPersCargo());
        System.out.println("Persona.t:" + this.intPersonaFk.getPersTelefono());

    }
}
