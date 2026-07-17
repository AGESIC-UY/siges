package com.sofis.generico.utils.generalutils;

/**
 *
 * @author Usuario
 */
public class BooleanUtils {

    public static boolean sonBooleanIguales(Boolean s1, Boolean s2) {
        if (s1 != null) {
            if (s2 != null) {
                return s1.equals(s2);
            } else {
                return false;
            }
        } else {
            return s2 == null;
        }
    }
}
