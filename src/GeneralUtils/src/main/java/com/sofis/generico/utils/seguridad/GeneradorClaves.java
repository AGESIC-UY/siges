/*
 * 
 * 
 */
package com.sofis.generico.utils.seguridad;

import java.util.Random;

/**
 *
 * @author Usuario
 */
public class GeneradorClaves {

    protected static Random r = new Random();
    protected static char[] goodChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '@'};

    public static String getNext(int largo) /*    */ {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < largo; i++) {
            sb.append(goodChar[r.nextInt(goodChar.length)]);
        }
        return sb.toString();
    }
}
