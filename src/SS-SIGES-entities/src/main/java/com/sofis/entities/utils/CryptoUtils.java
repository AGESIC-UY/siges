/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.entities.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author bruno
 */
public class CryptoUtils {

	private static final Logger logger = Logger.getLogger(CryptoUtils.class.getName());

	public static String encrypt(SecretKey key, byte[] initVector, String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encrypted = cipher.doFinal(value.getBytes());
			return encode(encrypted);
		} catch (UnsupportedEncodingException
			| InvalidAlgorithmParameterException
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

	public static String decrypt(SecretKey key, byte[] initVector, String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] original = cipher.doFinal((decode(encrypted)).getBytes());
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
	}

}
