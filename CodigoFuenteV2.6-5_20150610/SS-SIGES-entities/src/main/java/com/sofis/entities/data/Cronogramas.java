package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "cronogramas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cronogramas.findAll", query = "SELECT c FROM Cronogramas c"),
    @NamedQuery(name = "Cronogramas.findByCroPk", query = "SELECT c FROM Cronogramas c WHERE c.croPk = :croPk"),
    @NamedQuery(name = "Cronogramas.findByCroEntSeleccionado", query = "SELECT c FROM Cronogramas c WHERE c.croEntSeleccionado = :croEntSeleccionado"),
    @NamedQuery(name = "Cronogramas.findByCroEntBorrados", query = "SELECT c FROM Cronogramas c WHERE c.croEntBorrados = :croEntBorrados"),
    @NamedQuery(name = "Cronogramas.findByCroResources", query = "SELECT c FROM Cronogramas c WHERE c.croResources = :croResources"),
    @NamedQuery(name = "Cronogramas.findByCroPermisoEscritura", query = "SELECT c FROM Cronogramas c WHERE c.croPermisoEscritura = :croPermisoEscritura"),
    @NamedQuery(name = "Cronogramas.findByCroPermisoEscrituraPadre", query = "SELECT c FROM Cronogramas c WHERE c.croPermisoEscrituraPadre = :croPermisoEscrituraPadre")})
public class Cronogramas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cro_pk")
    private Integer croPk;
    @Column(name = "cro_ent_seleccionado")
    private Integer croEntSeleccionado;
    @Column(name = "cro_ent_borrados")
    private String croEntBorrados;
    @Column(name = "cro_resources")
    private String croResources;
    @Column(name = "cro_permiso_escritura")
    private Boolean croPermisoEscritura;
    @Column(name = "cro_permiso_escritura_padre")
    private Boolean croPermisoEscrituraPadre;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "entCroFk", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private Set<Entregables> entregablesSet;
    @OneToOne(mappedBy = "proyCroFk", fetch = FetchType.LAZY)
    private Proyectos proyecto;

    public Cronogramas() {
    }

    public Cronogramas(Integer croPk) {
        this.croPk = croPk;
    }

    public Integer getCroPk() {
        return croPk;
    }

    public void setCroPk(Integer croPk) {
        this.croPk = croPk;
    }

    public Integer getCroEntSeleccionado() {
        return croEntSeleccionado != null ? croEntSeleccionado : 0;
    }

    public void setCroEntSeleccionado(Integer croEntSeleccionado) {
        this.croEntSeleccionado = croEntSeleccionado;
    }

    public String getCroEntBorrados() {
        return croEntBorrados;
    }

    public void setCroEntBorrados(String croEntBorrados) {
        this.croEntBorrados = croEntBorrados;
    }

    public String getCroResources() {
        return croResources;
    }

    public void setCroResources(String croResources) {
        this.croResources = croResources;
    }

    public Boolean getCroPermisoEscritura() {
        return croPermisoEscritura != null ? croPermisoEscritura : false;
    }

    public void setCroPermisoEscritura(Boolean croPermisoEscritura) {
        this.croPermisoEscritura = croPermisoEscritura;
    }

    public Boolean getCroPermisoEscrituraPadre() {
        return croPermisoEscrituraPadre != null ? croPermisoEscrituraPadre : false;
    }

    public void setCroPermisoEscrituraPadre(Boolean croPermisoEscrituraPadre) {
        this.croPermisoEscrituraPadre = croPermisoEscrituraPadre;
    }

    public Set<Entregables> getEntregablesSet() {
        return entregablesSet;
    }

    public void setEntregablesSet(Set<Entregables> entregablesSet) {
        this.entregablesSet = entregablesSet;
    }

    public Proyectos getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyectos proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (croPk != null ? croPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cronogramas)) {
            return false;
        }
        Cronogramas other = (Cronogramas) object;
        if ((this.croPk == null && other.croPk != null) || (this.croPk != null && !this.croPk.equals(other.croPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Cronogramas[ croPk=" + croPk + " ]";
    }

    public void toSystemOut() {
        System.out.println("-- Cronograma --");
        System.out.println("Pk:" + this.croPk);
        System.out.println("seleccionado:" + this.croEntSeleccionado);
        System.out.println("escritura:" + this.croPermisoEscritura);

        for (Entregables e : entregablesSet) {
            e.toSystemOut();
        }
    }
}
