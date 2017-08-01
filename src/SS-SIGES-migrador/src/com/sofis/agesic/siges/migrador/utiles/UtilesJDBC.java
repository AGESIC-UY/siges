/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.agesic.siges.migrador.utiles;
import java.lang.invoke.MethodHandles;

import com.sofis.agesic.siges.migrador.excepciones.MigracionException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author spio
 */
public class UtilesJDBC {

  public static String getSQLServerConnectionString(String host, String port, String base) {
    String sgbUrl = "jdbc:jtds:sqlserver://" + host + ":" + port + "/" + base;
    return sgbUrl;
  }
  
  public static String getMySQLConnectionString(String host, String port, String base) {
    String sgbUrl = "jdbc:mysql://" + host + ":" + port + "/" + base;
    return sgbUrl;
  }
  
  public static Connection abrirConexionSQLServer(String host, String port, String base, String user, String pass) throws MigracionException {
    try {
      Class.forName("net.sourceforge.jtds.jdbc.Driver");
      String sgbUrl = getSQLServerConnectionString(host, port, base);
      Connection sgbCon = DriverManager.getConnection(sgbUrl, user, pass);
      return sgbCon;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new MigracionException("No se pudo conectar con el servidor MS SQLServer", ex);
    }
  }

  public static void cerrarConexionSQLServer(Connection con) throws MigracionException {
    try {
      if (con != null && !con.isClosed()) {
        con.close();
      }
    } catch (Exception ex) {
      throw new MigracionException("No se pudo cerrar la conexion con el servidor MS SQLServer", ex);
    }
  }

  public static Connection abrirConexionMySQL(String host, String port, String base, String user, String pass) throws MigracionException {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      String sgbUrl = getMySQLConnectionString(host, port, base);
      Connection sgbCon = DriverManager.getConnection(sgbUrl, user, pass);
      return sgbCon;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new MigracionException("No se pudo conectar con el servidor MySQL", ex);
    }
  }

  public static void cerrarConexionMySQL(Connection con) throws MigracionException {
    try {
      if (con != null && !con.isClosed()) {
        con.close();
      }
    } catch (Exception ex) {
      throw new MigracionException("No se pudo cerrar la conexion con el servidor MySQL", ex);
    }
  }
}
