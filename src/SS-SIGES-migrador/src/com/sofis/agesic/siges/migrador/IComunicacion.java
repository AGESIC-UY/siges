/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.agesic.siges.migrador;
import java.lang.invoke.MethodHandles;

/**
 *
 * @author spio
 */
public interface IComunicacion {
  
  public static enum Tipo {MENSAJE, SENTENCIASQL, MAPEO};
  
  public void mostrarMensaje(Tipo t, String m);
  
}
