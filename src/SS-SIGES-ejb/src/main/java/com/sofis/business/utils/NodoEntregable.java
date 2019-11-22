/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.business.utils;

import com.sofis.entities.data.Entregables;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author spio
 */
public class NodoEntregable {
  
  private final Entregables entregable;
  private final NodoEntregable padre;
  private final List<NodoEntregable> hijos = new ArrayList<>();

  public NodoEntregable(Entregables entregable, NodoEntregable padre) {
    this.entregable = entregable;
    this.padre = padre;
  }

  public Entregables getEntregable() {
    return entregable;
  }

  public List<NodoEntregable> getHijos() {
    return hijos;
  }

  public NodoEntregable getPadre() {
    return padre;
  }

  DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("{\"nodo\":");
    sb.append(entregable.getEntId().toString());
    sb.append(", \"nivel\": ");
    sb.append(entregable.getEntNivel().toString());
    sb.append(", \"inicio\": \"");
    sb.append(entregable.getEntInicio()==null?"xx-xx-xxxx":df.format(entregable.getEntInicioDate()));
    sb.append("\", \"fin\": \"");
    sb.append(entregable.getEntFin()==null?"xx-xx-xxxx":df.format(entregable.getEntFinDate()));
    sb.append("\", \"iniciolb\": \"");
    sb.append(entregable.getEntInicioLineaBase()==null?"xx-xx-xxxx":df.format(entregable.getEntInicioLineaBase()));
    sb.append("\", \"finlb\": \"");
    sb.append(entregable.getEntFinLineaBase()==null?"xx-xx-xxxx":df.format(entregable.getEntFinLineaBase()));


    sb.append("\", \"hijos\": [");
    boolean primero = true;
    for(NodoEntregable hijo : hijos) {
      if(!primero) {
        sb.append(",");
      }else {
        primero = false;
      }
      sb.append(hijo.toString());
    }
    sb.append("]");
    sb.append("}");
    return sb.toString();
  }

  //Para la detección de ciclos en el grafo
  
  private boolean marcaTemporal = false;
  private boolean marcaPermanente = false;

  public boolean isMarcaTemporal() {
    return marcaTemporal;
  }

  public void setMarcaTemporal(boolean marcaTemporal) {
    this.marcaTemporal = marcaTemporal;
  }

  public boolean isMarcaPermanente() {
    return marcaPermanente;
  }

  public void setMarcaPermanente(boolean marcaPermanente) {
    this.marcaPermanente = marcaPermanente;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final NodoEntregable other = (NodoEntregable) obj;
    if(!Objects.equals(this.entregable.getEntId(), other.entregable.getEntId())) {
      return false;
    }
    return true;
  }
  
  
  
}
