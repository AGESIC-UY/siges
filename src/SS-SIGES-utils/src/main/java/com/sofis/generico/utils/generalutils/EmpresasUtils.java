/*
 * 
 * 
 */
package com.sofis.generico.utils.generalutils;

/**
 *
 * @author Usuario
 */
public class EmpresasUtils {

    /**
     * Esta operación permite validar un número de RUT
     *
     * @param rut String con el numero de rut
     * @return true si es correcto, false en caso contrario
     */
    public static boolean validarRUT(String rut) {
        try {
            if (rut.trim().length() >= 11) {
                int len = rut.trim().length();
                String sub = rut.trim().substring(0, 11);
                ////System.out.println("sub = " + sub);

                int[] v = {2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4};
                int i, sum = 0;
                for (i = 0; i < sub.length(); i++) {
                    sum += Character.digit(sub.charAt(len - 2 - i), 10)
                            * v[i];
                }

                int resto = sum % 11;

                int probablecd = 11 - resto;
                ////System.out.println("probablecd = " + probablecd);
                int dv = -1;
                if (rut.length() >= 12) {
                    dv = Character.digit(rut.charAt(11), 10);
                }
                ////System.out.println("dv = " + dv);
                if (probablecd < 10 && dv != probablecd) {
                    return false;
                }
                if (probablecd == 11 && dv != 0) {
                    return false;
                }
                if (probablecd == 10 && dv > 0) {
                    return false;
                }
                int c = Integer.parseInt(rut.substring(0, 2));
                 if (c < 1 || c > 21) {
                    return false;
                }
                int c2 = Integer.parseInt(rut.substring(2, 8));
                 if (c2 == 0) {
                    return false;
                }
                int c3 = Integer.parseInt(rut.substring(8, 10));
                 if (c3 != 0) {
                    return false;
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        //System.out.println("verificar: " + EmpresasUtils.validarRUT("215119370019"));
    }
}
