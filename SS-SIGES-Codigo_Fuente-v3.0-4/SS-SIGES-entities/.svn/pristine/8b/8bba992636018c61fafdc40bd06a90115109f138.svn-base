/*
 * 
 * 
 */
package com.sofis.entities.comparators;

import com.sofis.entities.data.Pais;
import java.util.Comparator;

/**
 *
 * @author Usuario
 */
public class PaisComparator implements Comparator {

    @Override
    public int compare(Object t, Object t1) {
        int respuesta = 0;
         if (t!=null) {
             if (t1!=null) {
                 if (t instanceof Pais) {
                     if (t1 instanceof Pais) {
                         Pais p = (Pais)t;
                         Pais p1= (Pais)t1;
                         if (p.getPaiNombre()!=null) {
                             respuesta = p.getPaiNombre().compareTo(p1.getPaiNombre());
                         } else {
                             respuesta  =-1;
                         }
                     } else {
                         respuesta = -1;
                     }
                 } else {
                     respuesta =1;
                 }
             } else {
                 respuesta = 1;
             }
         } else {
             respuesta = -1;
         }
         return respuesta;
    }
    
}
