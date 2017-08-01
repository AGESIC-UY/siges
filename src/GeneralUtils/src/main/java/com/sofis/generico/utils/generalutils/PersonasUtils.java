/*
 * 
 * 
 */
package com.sofis.generico.utils.generalutils;

/**
 *
 * @author Usuario
 */
public class PersonasUtils {
    
       public static int calcularDigitoCedulaIdentidadUruguaya(String ci) {
        ci = ci.replaceAll("[^\\d]", "");
        if (ci.length() > 8) {
            ci = ci.substring(0, 8);
        }
        //resto 1 xq el último dígito es el verficador
        int ciLen = ci.length()-1;
        int s = 0;
        int[] v = new int[]{2, 9, 8, 7, 6, 3, 4};
        for (int i = v.length - ciLen; i < v.length; i++) {
            int a = v[i];
            int b = Character.digit(ci.charAt(i - (v.length - ciLen)), 10);
            s = (s + (a * b) % 10);
        }
        s = (10 - (s % 10)) % 10;
        return s;
    }

       public static boolean validarCI(String ci) {
           boolean respuesta = false;
           try {
           if (ci!=null && (ci.length()==7 || ci.length()==8)) {
               String ultimoDigito=ci.substring(ci.length()-1);
                 int chkDigit =calcularDigitoCedulaIdentidadUruguaya(ci);
               respuesta= Integer.parseInt(ultimoDigito)==chkDigit;
               
           }
           } catch (Exception ex) {
               //devuelve false
           }
           return respuesta;
       }
    
}
