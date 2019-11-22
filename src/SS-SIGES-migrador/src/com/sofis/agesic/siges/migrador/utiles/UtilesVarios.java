package com.sofis.agesic.siges.migrador.utiles;

import java.lang.invoke.MethodHandles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author spio
 */
public class UtilesVarios {

	public static boolean esVacio(String s) {
		return s == null || s.trim().isEmpty();
	}

	public static String tratarStrings(String s1, String s2) {
		String ret;
		if (esVacio(s1)) {
			ret = s2;
		} else {
			ret = s1;
		}
		ret = ret.replace("'", "\\'");
		return ret;
	}
	private static Date fechaNula = null;

	public static Date siNulo(Date d1) {
		if (d1 != null) {
			return d1;
		}
		if (fechaNula == null) {
			GregorianCalendar cal = new GregorianCalendar(1001, 01, 01);
			fechaNula = new Date(cal.getTime().getTime());
		}
		return fechaNula;
	}

	public static Float siNulo(Float f1, Float f2) {
		if (f1 != null) {
			return f1;
		}
		return f2;
	}

	public static String convertDateToString(Date d) {
		if (d == null) {
			return "NULL";
		}
		Calendar fecha = new GregorianCalendar();
		fecha.setTime(d);
		int anio = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		String sDate = (anio < 10 ? "000" : anio < 100 ? "00" : anio < 1000 ? "0" : "") + anio + (mes < 10 ? "0" : "") + mes + (dia < 10 ? "0" : "") + dia;
		return "STR_TO_DATE('" + sDate + "','%Y%m%d')";
	}

	public static Long convertDateToLong(Date d, boolean inicio) {
		if (d == null) {
			return null;
		}
		if (inicio) {
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(d);
			c.set(Calendar.HOUR, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.AM_PM, 0);
			c.set(Calendar.MILLISECOND, 0);
			return c.getTimeInMillis();
		} else {
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(d);
			c.set(Calendar.HOUR, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			c.set(Calendar.AM_PM, 0);
			c.set(Calendar.MILLISECOND, 0);
			return c.getTimeInMillis();
		}

	}

	public static byte[] cargarArchivo(String ruta) {
		FileInputStream fileInputStream = null;
		try {

			File file = new File(ruta);
			if (!file.exists()) {
				return null;
			}
			byte[] bFile = new byte[(int) file.length()];
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);

			return bFile;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException ex) {
					Logger.getLogger(UtilesVarios.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
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

	public static String areaAbreviacion(String area) {
		if (area != null) {
			String result = "";
			String[] splitArea = area.split(" ");
			for (int i = 0; i < splitArea.length; i++) {
				if (splitArea[i].length() >= 3) {
					result = result.concat(!result.equals("") ? " " : "").concat(splitArea[i].substring(0, 3));
				}
			}
			return result;
		}
		return "";
	}
}
