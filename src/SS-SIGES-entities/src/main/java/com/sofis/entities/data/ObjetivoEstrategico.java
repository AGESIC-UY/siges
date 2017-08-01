/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.entities.data;

import com.sofis.generico.utils.generalutils.StringsUtils;
import java.io.Serializable;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Entity
@Table( name = "objetivos_estrategicos",
        uniqueConstraints = {
            @UniqueConstraint(name = "ObjetivoEstrategico.objEstNombreObjEstOrgFk", columnNames = {"obj_est_org_fk", "obj_est_nombre"})
        })
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "ObjetivoEstrategico.findAll", query = "SELECT o FROM ObjetivoEstrategico o"),
    @NamedQuery(name = "ObjetivoEstrategico.findByObjEstPk", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.objEstPk = :objEstPk"),
    @NamedQuery(name = "ObjetivoEstrategico.findByObjEstNombre", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.objEstNombre = :objEstNombre"),
    @NamedQuery(name = "ObjetivoEstrategico.findByObjEstDescripcion", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.objEstDescripcion = :objEstDescripcion"),
    @NamedQuery(name = "ObjetivoEstrategico.findByObjEstOrgFk", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.objEstOrgFk = :objEstOrgFk"),
    @NamedQuery(name = "ObjetivoEstrategico.findByObjEstOrgFkOrgPk", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.objEstOrgFk.orgPk = :orgPk"),
    @NamedQuery(name = "ObjetivoEstrategico.findByObjEstOrgFkYObjEstNombre", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.objEstOrgFk = :objEstOrgFk AND o.objEstNombre = :objEstNombre")
})
public class ObjetivoEstrategico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "obj_est_pk")
    private Integer objEstPk;
    @Size(max = 100)
    @Column(name = "obj_est_nombre")
    private String objEstNombre;
    @Size(max = 300)
    @Column(name = "obj_est_descripcion")
    private String objEstDescripcion;
    @JoinColumn(name = "obj_est_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Organismos objEstOrgFk;
    
    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objetivoEstrategico")
    @Fetch(FetchMode.SELECT)
    private Set<Proyectos> proyectos;
    
    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objetivoEstrategico")
    @Fetch(FetchMode.SELECT)
    private Set<Programas> programas;
    
    
    @PreUpdate
    public void preUpdate(){
        this.objEstNombre = StringsUtils.normalizarString(this.objEstNombre);
        this.objEstDescripcion = StringsUtils.normalizarString(this.objEstDescripcion);
    }
    
    @PrePersist
    public void prePersist(){
        this.objEstNombre = StringsUtils.normalizarString(this.objEstNombre);
        this.objEstDescripcion = StringsUtils.normalizarString(this.objEstDescripcion);
    }

    public ObjetivoEstrategico() {
    }

    public ObjetivoEstrategico(Integer objEstPk) {
        this.objEstPk = objEstPk;
    }

    public Integer getObjEstPk() {
        return objEstPk;
    }

    public void setObjEstPk(Integer objEstPk) {
        this.objEstPk = objEstPk;
    }

    public String getObjEstNombre() {
        return objEstNombre;
    }

    public void setObjEstNombre(String objEstNombre) {
        this.objEstNombre = objEstNombre;
    }

    public String getObjEstDescripcion() {
        return objEstDescripcion;
    }

    public void setObjEstDescripcion(String objEstDescripcion) {
        this.objEstDescripcion = objEstDescripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (objEstPk != null ? objEstPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjetivoEstrategico)) {
            return false;
        }
        ObjetivoEstrategico other = (ObjetivoEstrategico) object;
        if ((this.objEstPk == null && other.objEstPk != null) || (this.objEstPk != null && !this.objEstPk.equals(other.objEstPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ObjectivoEstrategico[ objEstPk=" + objEstPk + " ]";
    }

    /**
     * @return the objEstOrgFk
     */
    public Organismos getObjEstOrgFk() {
        return objEstOrgFk;
    }

    /**
     * @param objEstOrgFk the objEstOrgFk to set
     */
    public void setObjEstOrgFk(Organismos objEstOrgFk) {
        this.objEstOrgFk = objEstOrgFk;
    }

    /**
     * @return the proyectos
     */
    public Set<Proyectos> getProyectos() {
        return proyectos;
    }

    /**
     * @param proyectos the proyectos to set
     */
    public void setProyectos(Set<Proyectos> proyectos) {
        this.proyectos = proyectos;
    }

    /**
     * @return the programas
     */
    public Set<Programas> getProgramas() {
        return programas;
    }

    /**
     * @param programas the programas to set
     */
    public void setProgramas(Set<Programas> programas) {
        this.programas = programas;
    }
    
}
