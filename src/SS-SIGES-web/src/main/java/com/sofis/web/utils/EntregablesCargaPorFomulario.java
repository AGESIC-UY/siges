/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.utils;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author spio
 */
public class EntregablesCargaPorFomulario {
  
  private String nombre;
  private String nivel;
  private String esfuerzo;
  private String progreso;
  private Date inicio;
  private Date fin;
  private Boolean esHito;
  private String predecesores;
  //Indica si se puede eliminar (tiene productos o dependencias; no debe incluir que otros entregables lo tienen como prodecesor)
  private boolean eliminable = true;
  
  //Mantiene predecesores para poder ajustarlos si se añaden entregables antes
  //Transient (se calcula cuando se setea el campo predecesores)
  private List<Predecesor> predecesoresLista = new LinkedList<>();

  public EntregablesCargaPorFomulario() {
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNivel() {
    return nivel;
  }

  public void setNivel(String nivel) {
    this.nivel = nivel;
  }

  public String getEsfuerzo() {
    return esfuerzo;
  }

  public void setEsfuerzo(String esfuerzo) {
    this.esfuerzo = esfuerzo;
  }

  public String getProgreso() {
    return progreso;
  }

  public void setProgreso(String progreso) {
    this.progreso = progreso;
  }

  public Date getInicio() {
    return inicio;
  }

  public void setInicio(Date inicio) {
    this.inicio = inicio;
  }

  public Date getFin() {
    return fin;
  }

  public void setFin(Date fin) {
    this.fin = fin;
  }

  public Boolean getEsHito() {
    return esHito;
  }

  public void setEsHito(Boolean esHito) {
    this.esHito = esHito;
  }

  public String getPredecesores() {
    return predecesores;
  }

  public void setPredecesores(String predecesores) {
    this.predecesores = predecesores;
    determinarPredecesores();
  }

  public boolean isEliminable() {
    return eliminable;
  }

  public void setEliminable(boolean eliminable) {
    this.eliminable = eliminable;
  }
  
  private void determinarPredecesores() {
    predecesoresLista = new LinkedList();
    if(this.predecesores!=null) {
      String[] sPredecesores = this.predecesores.split(",");
      for(String sPredecesor : sPredecesores) {
        try{
          String[] sPredecesor1 = sPredecesor.split(":");
          predecesoresLista.add(new Predecesor(Integer.valueOf(sPredecesor1[0].trim()), sPredecesor1.length>1?Integer.valueOf(sPredecesor1[1].trim()):null));
        }catch(Exception ex) {
          //Se ignora
        }
      }
      Collections.sort(predecesoresLista);
    }
  }

  public List<Predecesor> getPredecesoresLista() {
    return predecesoresLista;
  }
  
  
  public class Predecesor implements Comparable<Predecesor> {
    Integer id;
    Integer dias;

    public Predecesor(Integer id, Integer dias) {
      this.id = id;
      this.dias = dias;
    }

    public Integer getId() {
      return id;
    }

    public Integer getDias() {
      return dias;
    }

    @Override
    public int compareTo(Predecesor o) {
      return this.id.compareTo(o.id);
    }
    
  }
  
}
