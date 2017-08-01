/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sofis.agesic.siges.migrador.excepciones;
import java.lang.invoke.MethodHandles;

/**
 *
 * @author spio
 */
public class MigracionException extends Exception
{

  public MigracionException(String message) {
    super(message);
  }
  
  public MigracionException(String message, Throwable cause) {
    super(message, cause);
  }
  
}
