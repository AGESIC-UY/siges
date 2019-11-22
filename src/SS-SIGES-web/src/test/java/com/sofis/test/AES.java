package com.sofis.test;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class AES {

	static String IV = "AAAAAAAAAAABAAAA";
	static String plaintext = "test text 123\0\0\0";
	/*Note null padding*/
	static String encryptionKey = "0123456789abcdef";

	public static void main(String[] args) {
		try {
			

			System.out.println("==Java==");
			System.out.println("plain:   " + plaintext);
			System.out.println("plain.length:   " + plaintext.length());

			byte[] cipher = encrypt(plaintext, encryptionKey);

			System.out.println("cipher:  "+ encode(cipher));

			String decrypted = decrypt(decode(encode(cipher)), encryptionKey);

			System.out.println("decrypt: " + decrypted);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] encrypt(String plainText, String encryptionKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		return cipher.doFinal(plainText.getBytes("UTF-8"));
	}

	public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		return new String(cipher.doFinal(cipherText), "UTF-8");
	}

	public static String encode(byte[] value) throws Exception {
		return DatatypeConverter.printBase64Binary(value);
	}

	public static String encode(String value) throws Exception {
		return DatatypeConverter.printBase64Binary(value.getBytes(StandardCharsets.UTF_8)); // use "utf-8" if java 6
	}

	public static byte[] decode(String value) throws Exception {
		byte[] decodedValue = DatatypeConverter.parseBase64Binary(value);
		return decodedValue;
	}

}
