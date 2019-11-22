/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.ws.gestion.data;

import com.sofis.entities.data.CategoriaProyectos;
import com.sofis.entities.data.Image;
import com.sofis.entities.data.Organismos;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@XmlRootElement(name = "categoriasProyecto")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategoriaProyectoTO {
    
    @XmlElement(name = "catProyPk", required = false)
    private Integer catProyPk;
    
    @XmlElement(name = "catProyCodigo", required = false)
    private String catProyCodigo;
    
    @XmlElement(name = "catProyNombre", required = false)
    private String catProyNombre;
    
    @XmlElement(name = "catProyActivo", required = false)
    private boolean catProyActivo;
    
    @XmlElement(name = "catTipo", required = false)
    private Integer catTipo;
    
    @XmlElement(name = "catIcono", required = false)
    private Image catIcono;
    
    @XmlElement(name = "catIconoMarker", required = false)
    private Image catIconoMarker;
    
    @XmlElement(name = "catOrgFk", required = false)
    private Organismos catOrgFk;

    public CategoriaProyectoTO() {
    }

    public CategoriaProyectoTO(Integer catProyPk, String catProyCodigo, String catProyNombre, boolean catProyActivo, Integer catTipo, Image catIcono, Image catIconoMarker, Organismos catOrgFk) {
        this.catProyPk = catProyPk;
        this.catProyCodigo = catProyCodigo;
        this.catProyNombre = catProyNombre;
        this.catProyActivo = catProyActivo;
        this.catTipo = catTipo;
        this.catIcono = catIcono;
        this.catIconoMarker = catIconoMarker;
        this.catOrgFk = catOrgFk;
    }
    
    public CategoriaProyectoTO(CategoriaProyectos paramCatProy) {
        this.catProyPk = paramCatProy.getCatProyPk();
        this.catProyCodigo = paramCatProy.getCatProyCodigo();
        this.catProyNombre = paramCatProy.getCatProyNombre();
        this.catProyActivo = paramCatProy.getCatProyActivo();
        this.catTipo = paramCatProy.getCatTipo();
        this.catIcono = paramCatProy.getCatIcono();
        this.catIconoMarker = paramCatProy.getCatIconoMarker();
        this.catOrgFk = paramCatProy.getCatOrgFk();
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

    public boolean isCatProyActivo() {
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
    
    
}
