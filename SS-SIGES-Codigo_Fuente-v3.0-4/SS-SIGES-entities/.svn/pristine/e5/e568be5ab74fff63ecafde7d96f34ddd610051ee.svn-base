package com.sofis.entities.data;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "latlng_proyectos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LatlngProyectos.findAll", query = "SELECT l FROM LatlngProyectos l"),
    @NamedQuery(name = "LatlngProyectos.findByLatlngPk", query = "SELECT l FROM LatlngProyectos l WHERE l.latlngPk = :latlngPk"),
    @NamedQuery(name = "LatlngProyectos.findByLatlngLat", query = "SELECT l FROM LatlngProyectos l WHERE l.latlngLat = :latlngLat"),
    @NamedQuery(name = "LatlngProyectos.findByLatlngLng", query = "SELECT l FROM LatlngProyectos l WHERE l.latlngLng = :latlngLng"),
    @NamedQuery(name = "LatlngProyectos.findByLatlangLocFk", query = "SELECT l FROM LatlngProyectos l WHERE l.latlangLocFk = :latlangLocFk"),
    @NamedQuery(name = "LatlngProyectos.findByLatlangCodigopostal", query = "SELECT l FROM LatlngProyectos l WHERE l.latlangCodigopostal = :latlangCodigopostal"),
    @NamedQuery(name = "LatlngProyectos.findByLatlangBarrio", query = "SELECT l FROM LatlngProyectos l WHERE l.latlangBarrio = :latlangBarrio"),
    @NamedQuery(name = "LatlngProyectos.findByLatlangLoc", query = "SELECT l FROM LatlngProyectos l WHERE l.latlangLoc = :latlangLoc")})
public class LatlngProyectos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "latlng_pk")
    private Integer latlngPk;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latlng_lat")
    private BigDecimal latlngLat;
    @Column(name = "latlng_lng")
    private BigDecimal latlngLng;
    @Column(name = "latlang_loc_fk")
    private Integer latlangLocFk;
    @Column(name = "latlang_codigopostal")
    private Integer latlangCodigopostal;
    @Size(max = 245)
    @Column(name = "latlang_barrio")
    private String latlangBarrio;
    @Size(max = 245)
    @Column(name = "latlang_loc")
    private String latlangLoc;
    
    @JoinColumn(name = "latlang_dep_fk", referencedColumnName = "dep_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private Departamentos latlangDepFk;

    public LatlngProyectos() {
        latlngLat= new BigDecimal(0);
        latlngLng= new BigDecimal(0);
    }

    public LatlngProyectos(Integer latlngPk) {
        this.latlngPk = latlngPk;
    }

    public Integer getLatlngPk() {
        return latlngPk;
    }

    public void setLatlngPk(Integer latlngPk) {
        this.latlngPk = latlngPk;
    }

    public BigDecimal getLatlngLat() {
        return latlngLat;
    }

    public void setLatlngLat(BigDecimal latlngLat) {
        this.latlngLat = latlngLat;
    }

    public BigDecimal getLatlngLng() {
        return latlngLng;
    }

    public void setLatlngLng(BigDecimal latlngLng) {
        this.latlngLng = latlngLng;
    }

    public Integer getLatlangLocFk() {
        return latlangLocFk;
    }

    public void setLatlangLocFk(Integer latlangLocFk) {
        this.latlangLocFk = latlangLocFk;
    }

    public Integer getLatlangCodigopostal() {
        return latlangCodigopostal;
    }

    public void setLatlangCodigopostal(Integer latlangCodigopostal) {
        this.latlangCodigopostal = latlangCodigopostal;
    }

    public String getLatlangBarrio() {
        return latlangBarrio;
    }

    public void setLatlangBarrio(String latlangBarrio) {
        this.latlangBarrio = latlangBarrio;
    }

    public String getLatlangLoc() {
        return latlangLoc;
    }

    public void setLatlangLoc(String latlangLoc) {
        this.latlangLoc = latlangLoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (latlngPk != null ? latlngPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LatlngProyectos)) {
            return false;
        }
        LatlngProyectos other = (LatlngProyectos) object;
        if ((this.latlngPk == null && other.latlngPk != null) || (this.latlngPk != null && !this.latlngPk.equals(other.latlngPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.LatlngProyectos[ latlngPk=" + latlngPk + " ]";
    }

    public Departamentos getLatlangDepFk() {
        return latlangDepFk;
    }

    public void setLatlangDepFk(Departamentos latlangDepFk) {
        this.latlangDepFk = latlangDepFk;
    }

   

}
