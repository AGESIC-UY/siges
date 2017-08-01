package com.sofis.business.utils;

import com.sofis.business.properties.LabelsEJB;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Usuario
 */
public class Utils {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    /**
     * Returns the contents of the file in a byte array.
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
            throw new IOException("File is too large!");
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;

        try {
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
        } catch (IOException e) {
            throw e;
        } finally {
            is.close();
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }
        return bytes;
    }

    public static String generarContrasenia() {
        StringBuilder ret = new StringBuilder("");
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rand = new Random((new Date()).getTime());
        for (int i = 0; i < 8; i++) {
            ret.append(caracteres.charAt(rand.nextInt(caracteres.length())));
        }
        return ret.toString();
    }

    public static String md5(String txt) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(txt.getBytes("ISO-8859-1"));
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            return sb.toString();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String md5Base64(String txt) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(txt.getBytes("ISO-8859-1"));
            byte[] raw = md.digest();
            BASE64Encoder env = new BASE64Encoder();
            return env.encode(raw);
            // return new String(raw);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String md5Base64(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            byte[] raw = md.digest();
            BASE64Encoder env = new BASE64Encoder();
            return env.encode(raw);

            // return new String(raw);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo auxiliar que transforma un Integer en un Long
     *
     * @param value
     * @return
     */
    public static Long toLong(Integer value) {
        if (value == null) {
            return null;
        }
        Long hastaL = null;
        try {
            hastaL = new Long(value);
        } catch (Exception w) {
        }

        return hastaL;
    }

    /**
     * Metodo auxiliar que transforma un String que representa una clase en una
     * tipo Class
     *
     * @param className
     * @return
     * @throws DAOGeneralException
     */
    public static Class toClass(String className) {
        Class class_ = null;
        try {
            class_ = Class.forName(className);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return class_;
    }

    /**
     * Retorna la fecha del día
     *
     * @return
     */
    public static Date getToday() {
        return new Date();
    }

    /**
     * Compara dos codigos, sin distinguir las mayusculas y los espacios al
     * principio y al final
     *
     * @param codigo1
     * @param codigo2
     * @return
     */
    public static boolean codigosIguales(String codigo1, String codigo2) {
        if (codigo1 == null) {
            return (codigo2 == null);
        }
        String c1 = codigo1.trim().toLowerCase();
        String c2 = codigo2.trim().toLowerCase();
        return c1.equals(c2);
    }

    public static void executeGarbageCollector() {
        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
//        logger.log(Level.INFO, "Memoria libre antes de limpieza: " + (runtime.freeMemory() / 1000 / 1000) + "MB de " + (runtime.maxMemory() / 1000 / 1000) + "MB (Asignada: " + (runtime.totalMemory() / 1000 / 1000) + "MB)");
//        garbage.gc();
        System.gc();
//        logger.log(Level.INFO, "Memoria libre despues de limpieza: " + (runtime.freeMemory() / 1000 / 1000) + "MB de " + (runtime.maxMemory() / 1000 / 1000) + "MB (Asignada: " + (runtime.totalMemory() / 1000 / 1000) + "MB)");
        long liberada = freeMemory - runtime.freeMemory();
        logger.log(Level.INFO, "[GarbageCollector]Memoria liberada: " + (liberada / 1000 / 1000) + "MB de " + (runtime.maxMemory() / 1000 / 1000) + "MB (Asignada: " + (runtime.totalMemory() / 1000 / 1000) + "MB)");
    }

    public static String mensajeLargoCampo(String nombreCampo, int size) {
        String campo = LabelsEJB.containsKey(nombreCampo) ? LabelsEJB.getValue(nombreCampo) : nombreCampo;
        return String.format("[ERROR]" + LabelsEJB.getValue(MensajesNegocio.ERROR_CAMPO_SIZE), campo, String.valueOf(size));
    }
    
    public static String generarTokenUUID(){
        return UUID.randomUUID().toString();
    }
}
