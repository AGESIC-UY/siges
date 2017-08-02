package com.sofis.generico.utils.generalutils;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class CollectionsUtils {

    /**
     * Retorna true si es nulo o vacío.
     *
     * @param col
     * @return
     */
    public static boolean isEmpty(Collection<?> col) {
        return col == null || col.isEmpty();
    }

    /**
     * Retorna true si no es nulo ni vacío.
     *
     * @param col
     * @return
     */
    public static boolean isNotEmpty(Collection<?> col) {
        return !isEmpty(col);
    }
}
