///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sofis.test;
//
//import static com.sofis.test.Main.decode;
//import static com.sofis.test.Main.decryptParam;
//import static com.sofis.test.Main.encode;
//import static com.sofis.test.Main.encryptParam;
//import static com.sofis.test.Main.generateKey;
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidKeyException;
//import java.security.Key;
//import java.security.KeyFactory;
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.security.spec.InvalidKeySpecException;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Date;
//import java.util.Random;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.SecretKey;
//import javax.xml.bind.DatatypeConverter;
//
///**
// *
// * @author bruno
// */
//public class Main2 {
//
//	private static final Logger logger = Logger.getLogger(Main.class.getName());
//
//	/**
//	 * @param args the command line arguments
//	 */
//	public static void main(String[] args) {
//
//		try {
//			byte[][] keys = generateKeyPair();
////			byte[] initVector = generateInitVector();
//
//			String param = "2-3236";
//			String param2 = encode(RSAEncrypt(param.getBytes(),keys[0]));
//			String param3 = new RSADecrypt(decode(param2),keys[1]);
//
//			System.out.println("param:	" + param);
//			System.out.println("param2:	" + param2);
//			System.out.println("param3:	" + param3);
//
//		} catch (Exception ex) {
//			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//		}
//	}
//
//	/**
//	 * Genera un par de claves publico/privada bajo el algoritmo RSA de 2048
//	 * bits y las almacena en la base de datos en formato Base64. Devuelve la
//	 * clave pública en primer lugar y la privada luego..
//	 *
//	 */
//	public static byte[][] generateKeyPair() {
//		try {
//			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
//			kpg.initialize(2048);
//			KeyPair keyPair = kpg.genKeyPair();
//			byte[] pub = keyPair.getPublic().getEncoded();
//			byte[] pri = keyPair.getPrivate().getEncoded();
//			byte[][] ret = new byte[2][];
//			ret[0] = pub;
//			ret[1] = pri;
//
//			return ret;
//		} catch (Exception ex) {
//		}
//		return null;
//	}
//
//	/* */
//	public static String base64Encode(byte[] bytes) {
//		try {
//			if (bytes == null) {
//				return null;
//			}
//			return encode(bytes);
//		} catch (Exception ex) {
//			Logger.getLogger(Main2.class.getName()).log(Level.SEVERE, null, ex);
//		}
//		return null;
//	}
//
//	public static byte[] base64Decode(String string) {
//		try {
//			if (string == null) {
//				return null;
//			}
//			return decode(string);
//		} catch (Exception ex) {
//			Logger.getLogger(Main2.class.getName()).log(Level.SEVERE, null, ex);
//		}
//		return null;
//	}
//
//	public static byte[] generateSalt() {
//		int length = 16;
//		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
//		StringBuilder sb = new StringBuilder();
//		Random random = new Random();
//		for (int i = 0; i < length; i++) {
//			char c = chars[random.nextInt(chars.length)];
//			sb.append(c);
//		}
//		return sb.toString().getBytes();
//	}
//
//	public static byte[] RSAEncrypt(final byte[] plain, final byte[] privKeyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException,
//		InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
//		//Esto es necesario porque Java8 exige que se use correctamente el padding (antes alcanzaba con poner "RSA")
//		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//		Key privKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privKeyBytes));
//		cipher.init(Cipher.ENCRYPT_MODE, privKey);
//		byte[] encryptedBytes = cipher.doFinal(plain);
//		return encryptedBytes;
//	}
//
//	public static byte[] RSADecrypt(final byte[] encryptedBytes, final byte[] pubKeyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException,
//		InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
//
//		Key pubKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(pubKeyBytes));
//
//		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//		cipher.init(Cipher.DECRYPT_MODE, pubKey);
//		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
//		return decryptedBytes;
//	}
//
//	/**
//	 * Este método que determina la firma que se debe usar para el servicio de
//	 * envío de marcas
//	 *
//	 * @param tabletSN
//	 * @param timestamp
//	 * @param serviceName
//	 * @param data
//	 * @param privKey
//	 * @return
//	 * @throws CeibalAsistenciaException
//	 */
//	public static String createSignature(String tabletSN, String timestamp, String serviceName, String data, byte[] privKey) throws Exception {
//		//BASE64(RSA-clave-privada(SALT + SHA2(Nro serie + timestamp + nombreWS + campos de datos)))
//		try {
//			//Armar el texto a firmar
//			String text = tabletSN + timestamp + serviceName;
//			if (data != null) {
//				text = text + data;
//			}
//			//Calcular la huella del texto
//			MessageDigest digest = MessageDigest.getInstance("SHA-256");
//			digest.update(text.getBytes());
//			byte[] hash = digest.digest();
//
//			//Obtener un salt
//			byte[] salt = generateSalt();
//
//			//Concatenar el salt con el hash
//			byte[] saltHash = new byte[salt.length + hash.length];
//			System.arraycopy(salt, 0, saltHash, 0, salt.length);
//			System.arraycopy(hash, 0, saltHash, salt.length, hash.length);
//
//			//Encriptar el salt+hash para obtener la firma
//			byte[] signature = RSAEncrypt(saltHash, privKey);
//
//			//Convertir la firma a base 64
//			String signatureB64 = base64Encode(signature);
//
//			return signatureB64;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return null;
//
//	}
//
//	public static final String getBase64Md5(String text) {
//		try {
//			MessageDigest digest = MessageDigest.getInstance("MD5");
//			digest.update(text.getBytes());
//			byte[] hash = digest.digest();
//			return encode(hash);
//		} catch (Exception ex) {
//			return null;
//		}
//	}
//
//	public static final String getBase64Sha256(String text) {
//		try {
//			MessageDigest digester = MessageDigest.getInstance("SHA-256");
//			return encode(digester.digest(text.getBytes()));
//		} catch (Exception ex) {
//			return null;
//		}
//	}
//
//	public static String encode(byte[] value) throws Exception {
//		return DatatypeConverter.printBase64Binary(value);
//	}
//
//	public static String encode(String value) throws Exception {
//		return DatatypeConverter.printBase64Binary(value.getBytes(StandardCharsets.UTF_8)); // use "utf-8" if java 6
//	}
//
//	public static byte[] decode(String value) throws Exception {
//		byte[] decodedValue = DatatypeConverter.parseBase64Binary(value);
//		return decodedValue;
//	}
//
//}
