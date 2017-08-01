/*
 * 
 * 
 */
package com.sofis.entities.comparators;

import com.sofis.entities.data.Entregables;
import java.util.Comparator;

/**
 *
 * @author Usuario
 */
public class EntregablesComparator implements Comparator {

    @Override
    public int compare(Object t1, Object t2) {
        if (!(t1 instanceof Entregables) || !(t2 instanceof Entregables)) {
            return 0;
        }
        Entregables e1 = (Entregables) t1;
        Entregables e2 = (Entregables) t2;

        if (e1.getEntNombre() == null) {
            if (e2.getEntNombre() == null) {
                return 0;
            } else {
                return -1;
            }
        } else {
            if (e2.getEntNombre() == null) {
                return 1;
            } else {
                return e1.getEntNombre().compareTo(e2.getEntNombre());
            }
        }
    }
}
