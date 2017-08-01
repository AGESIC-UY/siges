package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "moneda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moneda.findAll", query = "SELECT m FROM Moneda m"),
    @NamedQuery(name = "Moneda.findByMonPk", query = "SELECT m FROM Moneda m WHERE m.monPk = :monPk"),
    @NamedQuery(name = "Moneda.findByMonNombre", query = "SELECT m FROM Moneda m WHERE m.monNombre = :monNombre"),
    @NamedQuery(name = "Moneda.findByMonSigno", query = "SELECT m FROM Moneda m WHERE m.monSigno = :monSigno"),
    @NamedQuery(name = "Moneda.findByMonCodPais", query = "SELECT m FROM Moneda m WHERE m.monCodPais = :monCodPais")})
public class Moneda implements Serializable {
    
    public static final int NOMBRE_LENGHT = 100;
    public static final int SIGNO_LENGHT = 50;
    public static final int COD_PAIS_LENGHT = 10;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mon_pk")
    private Integer monPk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mon_nombre")
    private String monNombre;
    @Column(name = "mon_signo")
    private String monSigno;
    @Column(name = "mon_cod_pais")
    private String monCodPais;

    public Moneda() {
    }

    public Moneda(Integer monPk) {
        this.monPk = monPk;
    }

    public Moneda(Integer monPk, String monNombre) {
        this.monPk = monPk;
        this.monNombre = monNombre;
    }

    public Moneda(Integer monPk, String monNombre, String monSigno, String monCodPais) {
        this.monPk = monPk;
        this.monNombre = monNombre;
        this.monSigno = monSigno;
        this.monCodPais = monCodPais;
    }

    public Integer getMonPk() {
        return monPk;
    }

    public void setMonPk(Integer monPk) {
        this.monPk = monPk;
    }

    public String getMonNombre() {
        return monNombre;
    }

    public void setMonNombre(String monNombre) {
        this.monNombre = monNombre;
    }

    public String getMonSigno() {
        return monSigno;
    }

    public void setMonSigno(String monSigno) {
        this.monSigno = monSigno;
    }

    public String getMonCodPais() {
        return monCodPais;
    }

    public void setMonCodPais(String monCodPais) {
        this.monCodPais = monCodPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monPk != null ? monPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Moneda)) {
            return false;
        }
        Moneda other = (Moneda) object;
        if ((this.monPk == null && other.monPk != null) || (this.monPk != null && !this.monPk.equals(other.monPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Moneda[ monPk=" + monPk + " ]";
    }

}
