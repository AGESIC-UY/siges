package com.sofis.generico.utils.generalutils;

/**
 *
 * @author Usuario
 */
public class StringsUtils {

    public static boolean isEmpty(String s) {
        return (s == null) || (s.trim().length() == 0);
    }

    public static boolean sonStringIguales(String s1, String s2) {
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

    public static String normalizarString(String s) {
        if (s != null) {
            return s.replaceAll("[ ]+", " ").trim();
        }
        return null;
    }

    public static String normalizarStringMayuscula(String s) {
        if (s != null) {
            return normalizarString(s).toUpperCase();
        }
        return null;
    }

    public static String toString(Object o) {
        String s = o != null ? String.valueOf(o) : "";
        return toString(s);
    }

    public static String toString(String s) {
        return s != null ? s : "";
    }

    public static String toCleanString(String s) {
        if (s == null) {
            return "";
        }
        s = s.replaceAll("\"", " ")
                .replaceAll("\\\\", " ")
                .replaceAll("\n", " ")
                .replaceAll("\t", " ")
                .replaceAll("\r", " ");
        return s;
    }

    /**
     * Concatena con StringBuilder los String aportados.
     *
     * @param s
     * @return String
     */
    public static String concat(String... s) {
        StringBuilder result = new StringBuilder();

        for (String str : s) {
            if (str != null) {
                result.append(str);
            }
        }
        return result.toString();
    }

    public static String recortarTexto(String s, int largo) {
        if (s == null || s.length() <= largo) {
            return s;
        } else {
            largo = largo >= 3 ? largo - 3 : largo;
            return concat(s.substring(0, largo), (s.length() > 3 ? "..." : ""));
        }
    }

    public static String removeTildes(String input) {
        // Cadena de caracteres original a sustituir.
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String output = input;
        for (int i = 0; i < original.length(); i++) {
            // Reemplazamos los caracteres especiales.
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }//for i
        return output;
    }//remove1

    public static String removerTagsXml(String s, int largo) {
        if (!StringsUtils.isEmpty(s)) {
            StringBuilder result = new StringBuilder();
            String[] splitStr = s.split("");
            boolean isTag = false;
            for (int i = 0; i < splitStr.length; i++) {
                if (splitStr[i].equals("<")) {
                    isTag = true;
                }

                if (!isTag) {
                    result.append(splitStr[i]);
                }

                if (splitStr[i].equals(">")) {
                    isTag = false;
                }
            }
            return result.toString();
        }
        return s;
    }
}
