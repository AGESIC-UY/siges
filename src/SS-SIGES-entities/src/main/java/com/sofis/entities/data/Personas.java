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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personas.findAll", query = "SELECT p FROM Personas p")})
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Personas implements Serializable {
    
    public static final int NOMBRE_LENGHT = 45;
    public static final int TELEFONO_LENGHT = 45;
    public static final int CARGO_LENGHT = 45;
    public static final int MAIL_LENGHT = 45;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pers_pk")
    private Integer persPk;
    @Size(max = 45)
    @Column(name = "pers_nombre")
    private String persNombre;
    @Size(max = 45)
    @Column(name = "pers_telefono")
    private String persTelefono;
    @Size(max = 45)
    @Column(name = "pers_cargo")
    private String persCargo;
    @Size(max = 45)
    @Column(name = "pers_mail")
    private String persMail;
    @Column(name = "pers_rol_fk")
    private Integer persRolFk;
    @JoinColumn(name = "pers_orga_fk", referencedColumnName = "orga_pk")
    @ManyToOne(optional = false)
    private OrganiIntProve persOrgaFk;
    @Transient
    private RolesInteresados persRol;
    @Transient
    private Boolean seleccionada = false;

    public Personas() {
    }

    public Personas(Integer persPk) {
        this.persPk = persPk;
    }

    public Integer getPersPk() {
        return persPk;
    }

    public void setPersPk(Integer persPk) {
        this.persPk = persPk;
    }

    public String getPersNombre() {
        return persNombre;
    }

    public void setPersNombre(String persNombre) {
        this.persNombre = persNombre;
    }

    public String getPersTelefono() {
        return persTelefono;
    }

    public void setPersTelefono(String persTelefono) {
        this.persTelefono = persTelefono;
    }

    public String getPersCargo() {
        return persCargo;
    }

    public void setPersCargo(String persCargo) {
        this.persCargo = persCargo;
    }

    public String getPersMail() {
        return persMail;
    }

    public void setPersMail(String persMail) {
        this.persMail = persMail;
    }

    public Integer getPersRolFk() {
        return persRolFk;
    }

    public void setPersRolFk(Integer persRolFk) {
        this.persRolFk = persRolFk;
    }

    public OrganiIntProve getPersOrgaFk() {
        return persOrgaFk;
    }

    public void setPersOrgaFk(OrganiIntProve persOrgaFk) {
        this.persOrgaFk = persOrgaFk;
    }

    public RolesInteresados getPersRol() {
        return persRol;
    }

    public void setPersRol(RolesInteresados persRol) {
        this.persRol = persRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (persPk != null ? persPk.hashCode() : 0);
        return hash;
    }

    @Override
    public Object clone() {
        Personas p = new Personas();
        p.setPersPk(this.persPk);
        p.setPersCargo(this.persCargo);
        p.setPersMail(this.persMail);
        p.setPersNombre(this.persNombre);
        p.setPersTelefono(this.persTelefono);
        p.setPersRolFk(this.persRolFk);
        p.setPersOrgaFk(this.getPersOrgaFk());
        return p;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Personas)) {
            return false;
        }
        Personas other = (Personas) object;
        if ((this.persPk == null && other.persPk != null) || (this.persPk != null && !this.persPk.equals(other.persPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Personas[ persPk=" + persPk + " ]";
    }

    public Boolean getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(Boolean seleccionada) {
        this.seleccionada = seleccionada;
    }
}