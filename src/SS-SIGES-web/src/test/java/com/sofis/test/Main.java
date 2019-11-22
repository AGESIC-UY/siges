/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.test;

import com.sofis.entities.utils.CryptoUtils;
import com.sofis.web.mb.InicioMB;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author bruno
 */
public class Main {

	private static final Logger logger = Logger.getLogger(Main.class.getName());

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		try {
			SecretKey key = generateKey();
//			byte[] initVector = generateInitVector();

			String param = "2-3236";
//		String param2 = encryptParam(key, initVector, param);
//		String param3 = decryptParam(key, initVector, param2);
			String param2 = encode(encryptParam(key, param));
			String param3 = decryptParam(key, decode(param2).getBytes());

			System.out.println("param:	" + param);
			System.out.println("param2:	" + param2);
			System.out.println("param3:	" + param3);

		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static SecretKey generateKey() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			return keyGen.generateKey();
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(InicioMB.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public static byte[] generateInitVector() {
		SecureRandom rand = new SecureRandom();
		byte[] bytes = new byte[16];
		rand.nextBytes(bytes);
		return bytes;
	}

	public static byte[] encryptParam(SecretKey userkey, String param) {
		return encrypt(userkey, param);
	}

	public static String decryptParam(SecretKey userkey, byte[] param) {
		return decrypt(userkey, param);
	}

	public static byte[] encrypt(SecretKey key, String value) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, key, cipher.getParameters());
			byte[] encrypted = cipher.doFinal(value.getBytes());
			return encrypted;
		} catch (InvalidAlgorithmParameterException
			| InvalidKeyException
			| NoSuchAlgorithmException
			| BadPaddingException
			| IllegalBlockSizeException
			| NoSuchPaddingException ex) {
			logger.log(Level.SEVERE, null, ex);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		}

		return null;
	}

	public static String decrypt(SecretKey key, byte[] encrypted) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, key, cipher.getParameters());
			byte[] original = cipher.doFinal(encrypted);
			return new String(original);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		}

		return null;
	}

	public static String encode(byte[] value) throws Exception {
		return DatatypeConverter.printBase64Binary(value);
	}

	public static String encode(String value) throws Exception {
		return DatatypeConverter.printBase64Binary(value.getBytes(StandardCharsets.UTF_8)); // use "utf-8" if java 6
	}

	public static String decode(String value) throws Exception {
		byte[] decodedValue = DatatypeConverter.parseBase64Binary(value);
		return new String(decodedValue, StandardCharsets.UTF_8); // use "utf-8" if java 6
//		return decodedValue;
	}

}
