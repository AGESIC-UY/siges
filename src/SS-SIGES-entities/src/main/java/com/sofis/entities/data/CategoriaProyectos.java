package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Categoría de los Proyectos. Principalmente para usar con el Visualizador.
 *
 * @author Usuario
 */
@Entity
@Table(name = "categoria_proyectos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaProyectos.findAll", query = "SELECT c FROM CategoriaProyectos c"),
    @NamedQuery(name = "CategoriaProyectos.findByCatProyPk", query = "SELECT c FROM CategoriaProyectos c WHERE c.catProyPk = :catProyPk"),
    @NamedQuery(name = "CategoriaProyectos.findByCatProyCodigo", query = "SELECT c FROM CategoriaProyectos c WHERE c.catProyCodigo = :catProyCodigo"),
    @NamedQuery(name = "CategoriaProyectos.findByCatProyNombre", query = "SELECT c FROM CategoriaProyectos c WHERE c.catProyNombre = :catProyNombre"),
    @NamedQuery(name = "CategoriaProyectos.findByCatProyActivo", query = "SELECT c FROM CategoriaProyectos c WHERE c.catProyActivo = :catProyActivo")})
public class CategoriaProyectos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cat_proy_pk")
    private Integer catProyPk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cat_proy_codigo")
    private String catProyCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cat_proy_nombre")
    private String catProyNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cat_proy_activo")
    private boolean catProyActivo;
    
    @Column(name = "cat_tipo")
    private Integer catTipo; //1: tema, 2:cat
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cat_icono", referencedColumnName = "image_pk")
    private Image catIcono;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cat_icono_marker", referencedColumnName = "image_pk")
    private Image catIconoMarker;
    
    @JoinColumn(name = "cat_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = true)
    private Organismos catOrgFk;
    
    public CategoriaProyectos() {
    }

    public CategoriaProyectos(Integer catProyPk) {
        this.catProyPk = catProyPk;
    }

    public CategoriaProyectos(Integer catProyPk, String catProyCodigo, String catProyNombre, boolean catProyActivo) {
        this.catProyPk = catProyPk;
        this.catProyCodigo = catProyCodigo;
        this.catProyNombre = catProyNombre;
        this.catProyActivo = catProyActivo;
    }

    public Integer getCatProyPk() {
        return catProyPk;
    }

    public void setCatProyPk(Integer catProyPk) {
        this.catProyPk = catProyPk;
    }

    public String getCatProyCodigo() {
        return catProyCodigo;
    }

    public void setCatProyCodigo(String catProyCodigo) {
        this.catProyCodigo = catProyCodigo;
    }

    public String getCatProyNombre() {
        return catProyNombre;
    }

    public void setCatProyNombre(String catProyNombre) {
        this.catProyNombre = catProyNombre;
    }

    public boolean getCatProyActivo() {
        return catProyActivo;
    }

    public void setCatProyActivo(boolean catProyActivo) {
        this.catProyActivo = catProyActivo;
    }

    public Integer getCatTipo() {
        return catTipo;
    }

    public void setCatTipo(Integer catTipo) {
        this.catTipo = catTipo;
    }

    public Image getCatIcono() {
        return catIcono;
    }

    public void setCatIcono(Image catIcono) {
        this.catIcono = catIcono;
    }

    public Image getCatIconoMarker() {
        return catIconoMarker;
    }

    public void setCatIconoMarker(Image catIconoMarker) {
        this.catIconoMarker = catIconoMarker;
    }

    public Organismos getCatOrgFk() {
        return catOrgFk;
    }

    public void setCatOrgFk(Organismos catOrgFk) {
        this.catOrgFk = catOrgFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catProyPk != null ? catProyPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof CategoriaProyectos)) {
            return false;
        }
        CategoriaProyectos other = (CategoriaProyectos) object;
        if ((this.catProyPk == null && other.catProyPk != null) || (this.catProyPk != null && !this.catProyPk.equals(other.catProyPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.CategoriaProyectos[ catProyPk=" + catProyPk + " ]";
    }
}
