/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DM
 */
public class StringHelper {

    private static final Logger LOGGER = Logger.getLogger(StringHelper.class.getName());
    
    private static final String PLAIN_ASCII =
            "AaEeIiOoUu" // grave
            + "AaEeIiOoUuYy" // acute
            + "AaEeIiOoUuYy" // circumflex
            + "AaEeIiOoUuYy" // tilde
            + "AaEeIiOoUuYy" // umlaut
            + "Aa" // ring
            + "Cc" // cedilla
            ;
    private static final String UNICODE =
            "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9" + "\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD" + "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177" + "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177" + "\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u0178\u00FF" + "\u00C5\u00E5" + "\u00C7\u00E7";


    // remove accentued from a string and replace with ascii equivalent
    public static String convertNonAscii(String s) {
        StringBuffer sb = new StringBuffer();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int pos = UNICODE.indexOf(c);
            if (pos > -1) {
                sb.append(PLAIN_ASCII.charAt(pos));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String decode(String toDecodeString) {
//        System.out.println("StringHelper.decode(<" + toDecodeString + ">");
        String decodedString = null;

        try {
            decodedString = URLDecoder.decode(toDecodeString, "ISO-8859-1");
        } catch (UnsupportedEncodingException uee) {
            LOGGER.log(Level.SEVERE, uee.getMessage(), uee);
        }

        return decodedString;
    }

    public static String removeSpaces(String s) {
        if (s == null) {
            return null;
        }

        StringTokenizer st = new StringTokenizer(s, " ", false);
        String t = "";
        while (st.hasMoreElements()) {
            t += st.nextElement() + " ";
        }
        return t;
    }

    public static String removeComas(String s) {
        if (s == null) {
            return "";
        }

        s = s.replaceAll(",", " ");
        return s;
    }

    public static String replaceTabEndLine(String s) {


        s = s.replaceAll("\n", " ");
        s = s.replaceAll("\t", " ");
        s = s.replaceAll("\b", " ");
        s = s.replaceAll("\r", " ");
        s = s.trim();
        return s;
    }

    public static String replaceTildes(String strIni) {
        //Delete tilde characters
        if (strIni != null && !strIni.equalsIgnoreCase("")) {
            return convertNonAscii(strIni);
        } else {
            return "";
        }
    }

    /*********************************************************/

    public static String normalizarParaBusqueda(String s){
        String respuesta = null;
        if(s != null)
            respuesta = StringHelper.replaceTildes(StringHelper.removeSpaces(s)).toLowerCase();
        return respuesta;
    }
}
