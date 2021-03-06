package com.sofis.agesic.siges.migrador;
import java.lang.invoke.MethodHandles;

import com.sofis.agesic.siges.migrador.IComunicacion.Tipo;
import com.sofis.agesic.siges.migrador.excepciones.MigracionException;
import com.sofis.agesic.siges.migrador.utiles.UtilesJDBC;
import com.sofis.agesic.siges.migrador.utiles.UtilesVarios;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author spio
 */
public class Migrador2 extends Thread {

    IComunicacion iCom;
    String viejoHost;
    String viejoPort;
    String viejoBase;
    String viejoUser;
    String viejoPass;
    String nuevoHost;
    String nuevoPort;
    String nuevoBase;
    String nuevoUser;
    String nuevoPass;
    String nombreOrganismo;
    String directorioArchivos;
    boolean limpiar;
    boolean sinProductos;

    public Migrador2(IComunicacion ic, String viejoHost, String viejoPort, String viejoBase,
            String viejoUser, String viejoPass, String nuevoHost, String nuevoPort, String nuevoBase,
            String nuevoUser, String nuevoPass, String nombreOrganismo, String directorioArchivos,
            boolean limpiar, boolean sinProductos) {
        iCom = ic;

        this.viejoHost = viejoHost;
        this.viejoPort = viejoPort;
        this.viejoBase = viejoBase;
        this.viejoUser = viejoUser;
        this.viejoPass = viejoPass;

        this.nuevoHost = nuevoHost;
        this.nuevoPort = nuevoPort;
        this.nuevoBase = nuevoBase;
        this.nuevoUser = nuevoUser;
        this.nuevoPass = nuevoPass;

        this.nombreOrganismo = nombreOrganismo;
        this.directorioArchivos = directorioArchivos;
        this.limpiar = limpiar;
        this.sinProductos = sinProductos;
        if (!this.directorioArchivos.endsWith(File.separator)) {
            this.directorioArchivos += File.separator;
        }
        log(Tipo.MENSAJE, "Sin Productos:" + this.sinProductos);
    }

    @Override
    public void run() {
        try {
            long t1 = (new java.util.Date()).getTime();
            if (limpiar) {
                limpiar();
            }
            migrar();
            long t2 = (new java.util.Date()).getTime();
            log(Tipo.MENSAJE, "Finalizado en " + (t2 - t1) + " milisegundos");
        } catch (Exception ex) {
            log(Tipo.MENSAJE, "Error: " + ex.getMessage());
        }
    }

    private void limpiar() throws Exception {
        Connection nuevoCon1 = UtilesJDBC.abrirConexionMySQL(nuevoHost, nuevoPort, nuevoBase, nuevoUser, nuevoPass);
        Statement nuevoSta1 = nuevoCon1.createStatement();
        log(Tipo.MENSAJE, "Limpiando datos...");
        nuevoSta1.executeUpdate("DELETE FROM pagos");
        nuevoSta1.executeUpdate("ALTER TABLE pagos AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM adquisicion");
        nuevoSta1.executeUpdate("ALTER TABLE adquisicion AUTO_INCREMENT = 1");

        try {
            nuevoSta1.executeUpdate("DELETE FROM participantes");
            nuevoSta1.executeUpdate("ALTER TABLE participantes AUTO_INCREMENT = 1");
            nuevoSta1.executeUpdate("DELETE FROM registros_horas");
            nuevoSta1.executeUpdate("ALTER TABLE registros_horas AUTO_INCREMENT = 1");
        } catch (SQLException sQLException) {
        }

        nuevoSta1.executeUpdate("DELETE FROM area_organi_int_prove");
        nuevoSta1.executeUpdate("ALTER TABLE area_organi_int_prove AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM prog_lectura_area");
        nuevoSta1.executeUpdate("ALTER TABLE prog_lectura_area AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM proy_lectura_area");
        nuevoSta1.executeUpdate("ALTER TABLE proy_lectura_area AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM ss_usuarios_datos");
        nuevoSta1.executeUpdate("ALTER TABLE ss_usuarios_datos AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM prog_tags");
        nuevoSta1.executeUpdate("DELETE FROM proy_tags");
        nuevoSta1.executeUpdate("UPDATE areas_tags SET areatag_padre_fk = NULL");
        nuevoSta1.executeUpdate("DELETE FROM areas_tags");
        nuevoSta1.executeUpdate("ALTER TABLE areas_tags AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM prog_docs");
        nuevoSta1.executeUpdate("DELETE FROM prog_docs_obl");
        nuevoSta1.executeUpdate("DELETE FROM proy_docs");
        nuevoSta1.executeUpdate("DELETE FROM documentos");
        nuevoSta1.executeUpdate("ALTER TABLE documentos AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM doc_file");
        nuevoSta1.executeUpdate("ALTER TABLE doc_file AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM prod_mes");
        nuevoSta1.executeUpdate("ALTER TABLE prod_mes AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM productos");
        nuevoSta1.executeUpdate("ALTER TABLE productos AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM tipo_documento_instancia");
        nuevoSta1.executeUpdate("ALTER TABLE tipo_documento_instancia AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM tipo_documento");
        nuevoSta1.executeUpdate("ALTER TABLE tipo_documento AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM ent_hist_linea_base");
        nuevoSta1.executeUpdate("ALTER TABLE ent_hist_linea_base AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM entregables");
        nuevoSta1.executeUpdate("ALTER TABLE entregables AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM prog_int");
        nuevoSta1.executeUpdate("DELETE FROM proy_int");
        nuevoSta1.executeUpdate("DELETE FROM interesados");
        nuevoSta1.executeUpdate("ALTER TABLE interesados AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM roles_interesados");
        nuevoSta1.executeUpdate("DELETE FROM personas");
        nuevoSta1.executeUpdate("ALTER TABLE personas AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM organi_int_prove");
        nuevoSta1.executeUpdate("DELETE FROM prog_pre");
        nuevoSta1.executeUpdate("DELETE FROM proy_pre");
        nuevoSta1.executeUpdate("DELETE FROM riesgos");
        nuevoSta1.executeUpdate("ALTER TABLE riesgos AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM proy_sitact_historico");
        nuevoSta1.executeUpdate("ALTER TABLE proy_sitact_historico AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM proyectos");
        nuevoSta1.executeUpdate("ALTER TABLE proyectos AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM programas");
        nuevoSta1.executeUpdate("ALTER TABLE programas AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM presupuesto");
        nuevoSta1.executeUpdate("ALTER TABLE presupuesto AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM cronogramas");
        nuevoSta1.executeUpdate("ALTER TABLE cronogramas AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM prog_indices");
        nuevoSta1.executeUpdate("DELETE FROM proy_indices");
        nuevoSta1.executeUpdate("DELETE FROM moneda");
        nuevoSta1.executeUpdate("ALTER TABLE moneda AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM fuente_financiamiento");
        nuevoSta1.executeUpdate("ALTER TABLE fuente_financiamiento AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM ss_usu_ofi_roles");
        nuevoSta1.executeUpdate("ALTER TABLE ss_usu_ofi_roles AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM ss_usuario");
        nuevoSta1.executeUpdate("ALTER TABLE ss_usuario AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("UPDATE areas SET area_padre = NULL");
        nuevoSta1.executeUpdate("DELETE FROM areas");
        nuevoSta1.executeUpdate("ALTER TABLE areas AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM mails_template");
        nuevoSta1.executeUpdate("ALTER TABLE mails_template AUTO_INCREMENT = 1");
        nuevoSta1.executeUpdate("DELETE FROM organismos");
        nuevoSta1.executeUpdate("ALTER TABLE organismos AUTO_INCREMENT = 1");

        UtilesJDBC.cerrarConexionMySQL(nuevoCon1);
    }

    private void migrar() throws MigracionException {

        log(Tipo.MENSAJE, "Iniciando migración...");

        //Datos necesarios a traves de todo el proceso (se van cargando a medida que se requieren)
        int idOrganismo;

        //Mapa para almacenar correlaciones (clave = <tabla>_<id>, valor=<nuevo_id>)
        Map<String, Integer> mapeos = new HashMap();

        try {
            //Abrir las conexiones
            log(Tipo.MENSAJE, "Abriendo conexiones...");
            String sqls = UtilesJDBC.getSQLServerConnectionString(viejoHost, viejoPort, viejoBase);
            String sqlm = UtilesJDBC.getMySQLConnectionString(nuevoHost, nuevoPort, nuevoBase);
            log(Tipo.MENSAJE, "Cadena de conexión a SQLServer: [" + sqls + "]");
            log(Tipo.MENSAJE, "Cadena de conexión a MySQL: [" + sqlm + "]");

            Connection viejoCon1 = UtilesJDBC.abrirConexionSQLServer(viejoHost, viejoPort, viejoBase, viejoUser, viejoPass);
            Connection nuevoCon1 = UtilesJDBC.abrirConexionMySQL(nuevoHost, nuevoPort, nuevoBase, nuevoUser, nuevoPass);

            log(Tipo.MENSAJE, "Migrando Organismo...");
            idOrganismo = migrarOrganismo(nuevoCon1, nombreOrganismo);

            log(Tipo.MENSAJE, "Migrando Areas...");
            migrarAreas(viejoCon1, nuevoCon1, idOrganismo, mapeos);

            log(Tipo.MENSAJE, "Migrando Usuarios...");
            migrarUsuarios(viejoCon1, nuevoCon1, idOrganismo, mapeos);

            log(Tipo.MENSAJE, "Migrando Monedas...");
            migrarMonedas(viejoCon1, nuevoCon1, mapeos);

            log(Tipo.MENSAJE, "Migrando Fuentes de financiamiento...");
            migrarFuentesFinanciamiento(viejoCon1, nuevoCon1, idOrganismo, mapeos);

            log(Tipo.MENSAJE, "Migrando Proyectos y programas...");
            migrarProyectosProgramas(viejoCon1, nuevoCon1, idOrganismo, mapeos);

            log(Tipo.MENSAJE, "Migrando Historico Sit. Actual...");
            migrarSitActualHistorico(viejoCon1, nuevoCon1, idOrganismo, mapeos);

            log(Tipo.MENSAJE, "Migrando Empresas proveedoras...");
            migrarEmpresasProveedoras(viejoCon1, nuevoCon1, idOrganismo, mapeos);

            log(Tipo.MENSAJE, "Migrando Adquisiciones...");
            migrarAdquisiciones(viejoCon1, nuevoCon1, mapeos);

            log(Tipo.MENSAJE, "Migrando Entregables...");
            migrarEntregables(viejoCon1, nuevoCon1, mapeos);

            log(Tipo.MENSAJE, "Migrando Tipos de documentos...");
            migrarTiposDocumentos(viejoCon1, nuevoCon1, idOrganismo, mapeos);

            log(Tipo.MENSAJE, "Migrando Instancias de tipos de documentos...");
            migrarInstanciasTiposDocumentos(viejoCon1, nuevoCon1, mapeos);

            log(Tipo.MENSAJE, "Migrando Documentos...");
            migrarDocumentos(viejoCon1, nuevoCon1, directorioArchivos, mapeos);

            log(Tipo.MENSAJE, "Migrando Pagos...");
            migrarPagos(viejoCon1, nuevoCon1, mapeos);

            if (!sinProductos) {
                log(Tipo.MENSAJE, "Migrando Productos...");
                migrarProductos(viejoCon1, nuevoCon1, mapeos);
            }

            //Cerrar las conexiones
            log(Tipo.MENSAJE, "Cerrando conexiones...");
            UtilesJDBC.cerrarConexionSQLServer(viejoCon1);
            UtilesJDBC.cerrarConexionMySQL(nuevoCon1);
        } catch (MigracionException mEx) {
            mEx.printStackTrace();
            throw mEx;
        }

    }

    private int migrarOrganismo(Connection nuevoCon1, String nombreOrganismo) throws MigracionException {

        if (UtilesVarios.esVacio(nombreOrganismo)) {
            throw new MigracionException("El nombre del organismo no puede ser vacío");
        }

        try {
            Statement nuevoSta1 = nuevoCon1.createStatement();
            ResultSet nuevoRes1;

            //Obtener el id del organismo, o crearlo si no existe
            String sql = "SELECT org_pk FROM organismos WHERE org_nombre='" + nombreOrganismo + "'";
            log(Tipo.SENTENCIASQL, sql);
            nuevoRes1 = nuevoSta1.executeQuery(sql);

            int idOrganismo;
            if (nuevoRes1.next()) {
                //El organismo existe, obtener el id
                idOrganismo = nuevoRes1.getInt("org_pk");
            } else {
                //El organismo no existe, crearlo
                sql = "INSERT INTO organismos (org_nombre) VALUES ('" + nombreOrganismo + "')";
                log(Tipo.SENTENCIASQL, sql);
                nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                nuevoRes1 = nuevoSta1.getGeneratedKeys();
                nuevoRes1.next();
                idOrganismo = nuevoRes1.getInt(1);
                nuevoRes1.close();
            }
            nuevoRes1.close();
            return idOrganismo;
        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private void migrarAreas(Connection viejoCon1, Connection nuevoCon1, int idOrganismo, Map<String, Integer> mapeos) throws MigracionException {
        try {
            Statement viejoSta1 = viejoCon1.createStatement();
            Statement nuevoSta1 = nuevoCon1.createStatement();

            //Migrar las areas (tabla T02_cd_entidade)
            String sql = "SELECT T02_cd_entidade, nm_entidade FROM T02_Entidade ORDER BY T02_cd_entidade";
            log(Tipo.SENTENCIASQL, sql);
            ResultSet viejoRes1 = viejoSta1.executeQuery(sql);

            Integer idAreaViejo;
            String nombreArea;
            Integer idAreaNuevo;
            while (viejoRes1.next()) {
                idAreaViejo = viejoRes1.getInt("T02_cd_entidade");
                nombreArea = viejoRes1.getString("nm_entidade");
                sql = "INSERT INTO areas (area_org_fk, area_nombre, area_abreviacion)"
                        + " VALUES (" + idOrganismo + ",'" + UtilesVarios.tratarStrings(nombreArea, "")
                        + "','" + UtilesVarios.areaAbreviacion(nombreArea) + "')";
                log(Tipo.SENTENCIASQL, sql);
                nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
                nuevoRes1.next();
                idAreaNuevo = nuevoRes1.getInt(1);
                nuevoRes1.close();
                mapeos.put("T02_Entidade_" + idAreaViejo, idAreaNuevo);
                log(Tipo.MAPEO, "T02_Entidade_" + idAreaViejo + " -> " + idAreaNuevo);
            }
            viejoRes1.close();
        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private void migrarUsuarios(Connection viejoCon1, Connection nuevoCon1, int idOrganismo, Map<String, Integer> mapeos) throws MigracionException {
        try {
            Statement viejoSta1 = viejoCon1.createStatement();
            Statement nuevoSta1 = nuevoCon1.createStatement();

            //Migrar los usuarios (tabla T01_Usuario)
            String sql = "SELECT T01_cd_usuario, T02_cd_entidade, nm_nome, pw_senha, nm_email, nm_telefone, "
                    + "nu_celular, habilitado, dt_data, fl_admin FROM T01_Usuario ORDER BY T01_cd_usuario";
            log(Tipo.SENTENCIASQL, sql);
            ResultSet viejoRes1 = viejoSta1.executeQuery(sql);

            Integer idAreaViejo;
            Integer idAreaNuevo;
            String idUsuarioViejo;
            String nombreUsuario;
            String contrasenia;
            String correoElectronico;
            String telefono;
            String celular;
            String habilitado;
            Date fecha;
            String admin;
            Integer idUsuarioNuevo;

            while (viejoRes1.next()) {
                idUsuarioViejo = viejoRes1.getString("T01_cd_usuario");
                idAreaViejo = viejoRes1.getInt("T02_cd_entidade");
                nombreUsuario = viejoRes1.getString("nm_nome");
                contrasenia = viejoRes1.getString("pw_senha");
                correoElectronico = viejoRes1.getString("nm_email");
                telefono = viejoRes1.getString("nm_telefone");
                celular = viejoRes1.getString("nu_celular");
                habilitado = viejoRes1.getString("habilitado");
                fecha = viejoRes1.getDate("dt_data");
                admin = viejoRes1.getString("fl_admin");

                idAreaNuevo = mapeos.get("T02_Entidade_" + idAreaViejo);
                telefono = !UtilesVarios.esVacio(telefono) ? telefono : celular;

                sql = "SELECT usu_id FROM ss_usuario where usu_correo_electronico='" + correoElectronico.trim() + "'";
                ResultSet nuevoRes = nuevoSta1.executeQuery(sql);
                if (nuevoRes.next()) {
                    idUsuarioNuevo = nuevoRes.getInt(1);
                    nuevoRes.close();
                } else {
                    idUsuarioNuevo = null;
                }

                if (idUsuarioNuevo == null) {
                    sql = "INSERT INTO ss_usuario (usu_cod, usu_correo_electronico, usu_intentos_fallidos, "
                            + "usu_oficina_por_defecto, usu_password, usu_primer_nombre, usu_telefono, "
                            + "usu_version, usu_vigente, usu_fecha_password, usu_nro_doc, usu_origen, "
                            + "usu_primer_apellido, usu_user_code, usu_area_org) "
                            + "VALUES ('" + idUsuarioViejo + "', '" + UtilesVarios.tratarStrings(correoElectronico.trim(), "")
                            + "', 0, " + idOrganismo + "," + "'" + UtilesVarios.tratarStrings(contrasenia, "")
                            + "','" + UtilesVarios.tratarStrings(nombreUsuario, "") + "','"
                            + UtilesVarios.tratarStrings(telefono, "") + "',1 , " + habilitado + ","
                            + UtilesVarios.convertDateToString(UtilesVarios.siNulo(fecha)) + ", '','MIGRACION', '', 0"
                            + ", " + idAreaNuevo + ")";
                    log(Tipo.SENTENCIASQL, sql);
                    nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                    ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
                    nuevoRes1.next();
                    idUsuarioNuevo = nuevoRes1.getInt(1);
                    nuevoRes1.close();
                }

                mapeos.put("T01_Usuario_" + idUsuarioViejo, idUsuarioNuevo);
                log(Tipo.MAPEO, "T01_Usuario_" + idUsuarioViejo + " -> " + idUsuarioNuevo);

                sql = "INSERT INTO ss_usu_ofi_roles (usu_ofi_roles_origen, usu_ofi_roles_user_code, "
                        + "usu_ofi_roles_oficina, usu_ofi_roles_rol, usu_ofi_roles_usuario, usu_ofi_roles_activo)"
                        + " SELECT 'MIGRACION', " + idUsuarioNuevo + ", " + idOrganismo + ", rol_id, "
                        + idUsuarioNuevo + ", 1 FROM ss_rol "
                        + "WHERE rol_cod='" + ("S".equals(admin) ? "PMOT" : "USU") + "'";
                log(Tipo.SENTENCIASQL, sql);
                nuevoSta1.executeUpdate(sql);

            }

            //Se agregan dos usuarios genericos de tipo PMOT y Director.
            crearUsuariosNecesarios(nuevoCon1, idOrganismo);
        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private void migrarMonedas(Connection viejoCon1, Connection nuevoCon1, Map<String, Integer> mapeos) throws MigracionException {
        try {
            Statement viejoSta1 = viejoCon1.createStatement();
            Statement nuevoSta1 = nuevoCon1.createStatement();
            //Migrar las monedas
            String sql = "SELECT t35_cd_moneda, nm_nome, simbolo FROM siget35_moneda";
            log(Tipo.SENTENCIASQL, sql);
            ResultSet viejoRes1 = viejoSta1.executeQuery(sql);

            Integer idMonedaViejo;
            Integer idMonedaNuevo;
            String nombreMoneda;
            String simboloMoneda;
            while (viejoRes1.next()) {
                idMonedaViejo = viejoRes1.getInt("t35_cd_moneda");
                nombreMoneda = viejoRes1.getString("nm_nome");
                simboloMoneda = viejoRes1.getString("simbolo");

                sql = "SELECT mon_pk FROM moneda where mon_signo='" + simboloMoneda + "'";
                ResultSet nuevoRes = nuevoSta1.executeQuery(sql);;
                if (nuevoRes.next()) {
                    idMonedaNuevo = nuevoRes.getInt(1);
                    nuevoRes.close();
                } else {
                    idMonedaNuevo = null;
                }

                if (idMonedaNuevo == null) {
                    sql = "INSERT INTO moneda (mon_nombre, mon_signo) VALUES ('" + UtilesVarios.tratarStrings(nombreMoneda, "")
                            + "','" + UtilesVarios.tratarStrings(simboloMoneda, "") + "')";
                    log(Tipo.SENTENCIASQL, sql);
                    nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                    ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
                    nuevoRes1.next();
                    idMonedaNuevo = nuevoRes1.getInt(1);
                    nuevoRes1.close();
                }

                mapeos.put("SIGET35_MONEDA_" + idMonedaViejo, idMonedaNuevo);
                log(Tipo.MAPEO, "SIGET35_MONEDA_" + idMonedaViejo + " -> " + idMonedaNuevo);
            }
        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private void migrarFuentesFinanciamiento(Connection viejoCon1, Connection nuevoCon1, int idOrganismo, Map<String, Integer> mapeos) throws MigracionException {
        try {
            Statement viejoSta1 = viejoCon1.createStatement();
            Statement nuevoSta1 = nuevoCon1.createStatement();
            //Migrar las fuentes de financiamiento
            String sql = "SELECT t34_cd_fuente, nm_nome FROM siget34_fuente";
            log(Tipo.SENTENCIASQL, sql);
            ResultSet viejoRes1 = viejoSta1.executeQuery(sql);

            Integer idFuenteViejo;
            Integer idFuenteNuevo;
            String nombreFuente;
            while (viejoRes1.next()) {
                idFuenteViejo = viejoRes1.getInt("t34_cd_fuente");
                nombreFuente = viejoRes1.getString("nm_nome");
                sql = "INSERT INTO fuente_financiamiento (fue_nombre, fue_org_fk) VALUES ('"
                        + UtilesVarios.tratarStrings(nombreFuente, "") + "'," + idOrganismo + ")";
                log(Tipo.SENTENCIASQL, sql);
                nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
                nuevoRes1.next();
                idFuenteNuevo = nuevoRes1.getInt(1);
                nuevoRes1.close();
                mapeos.put("SIGET34_FUENTE_" + idFuenteViejo, idFuenteNuevo);
                log(Tipo.MAPEO, "SIGET34_FUENTE_" + idFuenteViejo + " -> " + idFuenteNuevo);
            }
        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private void migrarProyectosProgramas(Connection viejoCon1, Connection nuevoCon1, int idOrganismo, Map<String, Integer> mapeos) throws MigracionException {
        try {
            Statement viejoSta1 = viejoCon1.createStatement();
            Statement nuevoSta1 = nuevoCon1.createStatement();
            //Migrar los programas y los proyectos
            String sql = "SELECT t03_cd_projeto, t01_cd_entidade_secretaria, nm_projeto, t02_cd_usuario_gerente, "
                    + "t02_cd_usuario_responsavel, ds_situacao, "
                    + "t02_cd_usuario_titular, t02_cd_usuario_sec, act_amarillo, act_rojo,"
                    + "ds_objetivo, ds_publico, dt_cadastro, dt_atualizado, peso, p.es_programa, pp.id_programa, p.fl_fase "
                    + "FROM SIGET03_PROJETO p LEFT JOIN SIGET42_PROY_PROG pp ON p.T03_CD_PROJETO = pp.id_proyecto "
                    + "WHERE activo='S' "
                    + "ORDER BY p.es_programa DESC, t03_cd_projeto ASC ";
            log(Tipo.SENTENCIASQL, sql);
            ResultSet viejoRes1 = viejoSta1.executeQuery(sql);

            Integer idProjetoViejo;
            Integer idProyectoNuevo;
            Integer idProgramaViejo;
            Integer idProgramaNuevo;
            Integer idAreaViejo;
            Integer idAreaNuevo;
            String nombreProyecto;
            String usuarioGerente;
            String usuarioResponsable;
            String situacionAct;
            String usuarioTitular;
            String usuarioSecretaria;
            String objetivo;
            String publico;
            Integer amarillo;
            Integer rojo;
            Integer peso;
            Date fechaCreado;
            Date fechaActualizado;
            Integer esPrograma;
            String fase;
            String estado;

            Integer idUsuarioGerente;
            Integer idUsuarioAdjunto;
            Integer idUsuarioSponsor;
            Integer idUsuarioPMOFed;
            Integer idCronograma;
            Integer idPresupuesto;

            Integer idUsuarioNulo = null;
            while (viejoRes1.next()) {
                esPrograma = viejoRes1.getInt("es_programa");
                idProjetoViejo = viejoRes1.getInt("t03_cd_projeto");
                idAreaViejo = viejoRes1.getInt("t01_cd_entidade_secretaria");
                nombreProyecto = viejoRes1.getString("nm_projeto");
                usuarioGerente = viejoRes1.getString("t02_cd_usuario_gerente");
                usuarioResponsable = viejoRes1.getString("t02_cd_usuario_responsavel");
                situacionAct = viejoRes1.getString("ds_situacao");
                usuarioTitular = viejoRes1.getString("t02_cd_usuario_titular");
                usuarioSecretaria = viejoRes1.getString("t02_cd_usuario_sec");
                objetivo = viejoRes1.getString("ds_objetivo");
                publico = viejoRes1.getString("ds_publico");
                amarillo = viejoRes1.getInt("act_amarillo");
                rojo = viejoRes1.getInt("act_rojo");
                peso = viejoRes1.getInt("peso");
                fechaCreado = viejoRes1.getDate("dt_cadastro");
                fechaActualizado = viejoRes1.getDate("dt_atualizado");
                idProgramaViejo = viejoRes1.getInt("id_programa");
                fase = viejoRes1.getString("fl_fase").trim();

                idAreaNuevo = mapeos.get("T02_Entidade_" + idAreaViejo);

                if (usuarioGerente != null) {
                    idUsuarioGerente = mapeos.get("T01_Usuario_" + usuarioGerente);
                } else {
                    if (idUsuarioNulo == null) {
                        idUsuarioNulo = crearUsuarioDummy(nuevoCon1);
                    }
                    idUsuarioGerente = idUsuarioNulo;
                }
                if (usuarioSecretaria != null) {
                    idUsuarioAdjunto = mapeos.get("T01_Usuario_" + usuarioSecretaria);
                } else {
                    if (idUsuarioNulo == null) {
                        idUsuarioNulo = crearUsuarioDummy(nuevoCon1);
                    }
                    idUsuarioAdjunto = idUsuarioNulo;
                }

                idUsuarioSponsor = null;
                sql = "SELECT usu_ofi_roles_usuario"
                        + " FROM ss_usu_ofi_roles where usu_ofi_roles_rol="
                        + "(SELECT rol_id FROM ss_rol where rol_cod='DIR');";
                log(Tipo.SENTENCIASQL, sql);
                ResultSet nuevoResSponsor = nuevoSta1.executeQuery(sql);
                if (nuevoResSponsor.next()) {
                    idUsuarioSponsor = nuevoResSponsor.getInt("usu_ofi_roles_usuario");
                }

                idUsuarioPMOFed = null;
                sql = "SELECT usu_ofi_roles_usuario"
                        + " FROM ss_usu_ofi_roles where usu_ofi_roles_rol="
                        + "(SELECT rol_id FROM ss_rol where rol_cod='PMOF');";
                log(Tipo.SENTENCIASQL, sql);
                ResultSet nuevoResPMOF = nuevoSta1.executeQuery(sql);
                if (nuevoResPMOF.next()) {
                    idUsuarioPMOFed = nuevoResPMOF.getInt("usu_ofi_roles_usuario");
                }

                if (fechaActualizado == null) {
                    fechaActualizado = fechaCreado != null ? fechaCreado : new Date(0);
                }
                String SIN_INGRESAR = "Sin ingresar.";

                if ("ES".equals(fase)) {
                    estado = "Inicio";
                } else if ("PA".equals(fase)) {
                    estado = "Planificacion";
                } else if ("EX".equals(fase)) {
                    estado = "Ejecucion";
                    objetivo = !UtilesVarios.esVacio(objetivo) ? objetivo : SIN_INGRESAR;
                    publico = !UtilesVarios.esVacio(publico) ? publico : SIN_INGRESAR;
                } else if ("CO".equals(fase)) {
                    estado = "Finalizado";
                    objetivo = !UtilesVarios.esVacio(objetivo) ? objetivo : SIN_INGRESAR;
                    publico = !UtilesVarios.esVacio(publico) ? publico : SIN_INGRESAR;
                } else {
                    estado = "Pendiente";
                }

                if (esPrograma.intValue() == 1) {
                    sql = "INSERT INTO programas (prog_org_fk, prog_area_fk, prog_est_fk, prog_usr_gerente_fk, "
                            + "prog_usr_adjunto_fk, prog_usr_sponsor_fk, prog_usr_pmofed_fk, prog_nombre, "
                            + "prog_objetivo, prog_obj_publico, prog_semaforo_amarillo, prog_semaforo_rojo, "
                            + "prog_fecha_act, prog_activo, prog_version, prog_id_migrado)"
                            + " SELECT " + idOrganismo + ", " + idAreaNuevo + ", est_pk, " + idUsuarioGerente + ", "
                            + idUsuarioAdjunto + ", " + idUsuarioSponsor + ", " + idUsuarioPMOFed + ", '"
                            + UtilesVarios.tratarStrings(nombreProyecto, "") + "', '" + UtilesVarios.tratarStrings(objetivo, "") + "', '"
                            + UtilesVarios.tratarStrings(publico, "") + "', " + amarillo + ", " + rojo + ", "
                            + UtilesVarios.convertDateToString(UtilesVarios.siNulo(fechaActualizado)) + ", 1, 1, "
                            + idProjetoViejo + " FROM estados e WHERE e.est_nombre = '" + estado + "'";
                } else {
                    //Crear un cronograma para el proyecto
                    sql = "INSERT INTO cronogramas (cro_permiso_escritura, cro_permiso_escritura_padre, cro_ent_seleccionado) VALUES (1, 1, 0)";
                    log(Tipo.SENTENCIASQL, sql);
                    nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                    ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
                    nuevoRes1.next();
                    idCronograma = nuevoRes1.getInt(1);
                    nuevoRes1.close();

                    //Crear un presupuesto para el proyecto
                    sql = "INSERT INTO presupuesto (pre_base) VALUES (0)";
                    log(Tipo.SENTENCIASQL, sql);
                    nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                    nuevoRes1 = nuevoSta1.getGeneratedKeys();
                    nuevoRes1.next();
                    idPresupuesto = nuevoRes1.getInt(1);
                    nuevoRes1.close();

                    idProgramaNuevo = mapeos.get("SIGET03_PROJETO_" + idProgramaViejo);
                    sql = "INSERT INTO proyectos (proy_org_fk, proy_prog_fk, proy_area_fk, proy_est_fk, proy_usr_gerente_fk, "
                            + "proy_usr_adjunto_fk, proy_usr_sponsor_fk, proy_usr_pmofed_fk, proy_nombre, "
                            + "proy_objetivo, proy_obj_publico, proy_semaforo_amarillo, proy_semaforo_rojo, "
                            + "proy_fecha_act, proy_activo, proy_version, proy_peso, proy_cro_fk, proy_pre_fk, proy_id_migrado, proy_situacion_actual)"
                            + " SELECT " + idOrganismo + ", " + idProgramaNuevo + ", " + idAreaNuevo + ",  est_pk, "
                            + idUsuarioGerente + ", " + idUsuarioAdjunto + ", " + idUsuarioSponsor + ", "
                            + idUsuarioPMOFed + ", '" + UtilesVarios.tratarStrings(nombreProyecto, "") + "', '"
                            + UtilesVarios.tratarStrings(objetivo, "") + "', '"
                            + UtilesVarios.tratarStrings(publico, "") + "', " + amarillo + ", " + rojo + ", "
                            + UtilesVarios.convertDateToString(UtilesVarios.siNulo(fechaActualizado)) + ", 1, 1, "
                            + peso + "," + idCronograma + ", " + idPresupuesto + ", " + idProjetoViejo 
                            + ", '" + UtilesVarios.tratarStrings(situacionAct, "")
                            + "' FROM estados e WHERE e.est_nombre = '" + estado + "'";
                }
                log(Tipo.SENTENCIASQL, sql);
                nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
                nuevoRes1.next();
                idProyectoNuevo = nuevoRes1.getInt(1);
                nuevoRes1.close();
                mapeos.put("SIGET03_PROJETO_" + idProjetoViejo, idProyectoNuevo);
                log(Tipo.MAPEO, "SIGET03_PROJETO_" + idProjetoViejo + " -> " + idProyectoNuevo);
            }
        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private void migrarSitActualHistorico(Connection viejoCon1, Connection nuevoCon1, int idOrganismo, Map<String, Integer> mapeos) throws MigracionException {
        try {
            Statement viejoSta1 = viejoCon1.createStatement();
            Statement nuevoSta1 = nuevoCon1.createStatement();

            String sql = "SELECT T17_seq, T03_cd_projeto, T02_cd_usuario, ds_sit_ant, dt_data"
                    + " FROM SIGET17_SITUACAOPROJETO"
                    + " ORDER BY T03_cd_projeto, dt_data";
            log(Tipo.SENTENCIASQL, sql);
            ResultSet viejoRes1 = viejoSta1.executeQuery(sql);

            Integer idProyectoNuevo;
            Integer idProyectoViejo;
            Integer usuario;
            String sitActual;
            Date fecha;
            Integer idSitActHistViejo;
            Integer idSitActHistNuevo;

            while (viejoRes1.next()) {
                idSitActHistViejo = viejoRes1.getInt("T17_seq");
                sitActual = viejoRes1.getString("ds_sit_ant");
                fecha = viejoRes1.getDate("dt_data");
                String usuCod = viejoRes1.getString("T02_cd_usuario");
                usuario = obtenerUsuNuevoPorCodigo(nuevoCon1, usuCod);
                idProyectoViejo = viejoRes1.getInt("t03_cd_projeto");

                idProyectoNuevo = mapeos.get("SIGET03_PROJETO_" + idProyectoViejo);

                if (idProyectoNuevo != null) {
                    sql = "INSERT INTO proy_sitact_historico("
                            + "proy_sitact_fecha,proy_sitact_desc,proy_sitact_proy_fk,proy_sitact_usu_fk, version)"
                            + "VALUES(" + UtilesVarios.convertDateToString(UtilesVarios.siNulo(fecha))
                            + ",'" + UtilesVarios.tratarStrings(sitActual, "") + "',"
                            + idProyectoNuevo + "," + usuario + ",0)";

                    log(Tipo.SENTENCIASQL, sql);
                    nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                    ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
                    nuevoRes1.next();
                    idSitActHistNuevo = nuevoRes1.getInt(1);
                    nuevoRes1.close();
                    mapeos.put("IGET17_SITUACAOPROJETO_" + idSitActHistViejo, idSitActHistNuevo);
                    log(Tipo.MAPEO, "IGET17_SITUACAOPROJETO_" + idSitActHistViejo + " -> " + idSitActHistNuevo);
                }
            }

            //para cada projecto se trae la situacion actual
            sql = "SELECT p.t03_cd_projeto, p.ds_situacao, p.dt_situacao, T02_cd_usuario_gerente "
                    + "FROM SIGET03_PROJETO p WHERE p.es_programa=0 AND p.activo='S' AND p.ds_situacao IS NOT NULL";
            log(Tipo.SENTENCIASQL, sql);
            viejoRes1 = viejoSta1.executeQuery(sql);
            while (viejoRes1.next()) {
                idProyectoViejo = viejoRes1.getInt("t03_cd_projeto");
                sitActual = viejoRes1.getString("ds_situacao");
                fecha = viejoRes1.getDate("dt_situacao");

                //idSitActHistViejo = viejoRes1.getInt("T17_seq");
                String usuCod = viejoRes1.getString("T02_cd_usuario_gerente");
                usuario = obtenerUsuNuevoPorCodigo(nuevoCon1, usuCod);

                idProyectoNuevo = mapeos.get("SIGET03_PROJETO_" + idProyectoViejo);

                if (idProyectoNuevo != null) {
                    sql = "INSERT INTO proy_sitact_historico("
                            + "proy_sitact_fecha,proy_sitact_desc,proy_sitact_proy_fk,proy_sitact_usu_fk, version)"
                            + "VALUES(" + UtilesVarios.convertDateToString(UtilesVarios.siNulo(fecha))
                            + ",'" + UtilesVarios.tratarStrings(sitActual, "") + "',"
                            + idProyectoNuevo + "," + usuario + ",0)";

                    log(Tipo.SENTENCIASQL, sql);
                    nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                    ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
                    nuevoRes1.next();
                    idSitActHistNuevo = nuevoRes1.getInt(1);
                    nuevoRes1.close();
                }
            }
        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private void migrarEmpresasProveedoras(Connection viejoCon1, Connection nuevoCon1, Integer idOrganismo, Map<String, Integer> mapeos) throws MigracionException {
        try {
            Statement viejoSta1 = viejoCon1.createStatement();
            Statement nuevoSta1 = nuevoCon1.createStatement();
            //Migrar las empresas proveedoras
            String sql = "SELECT t37_cd_empresa_adjudicada, descripcion, telefono, direccion, mail, razon_social,"
                    + "rut FROM siget37_empresa_adjudicada";
            log(Tipo.SENTENCIASQL, sql);
            ResultSet viejoRes1 = viejoSta1.executeQuery(sql);

            Integer idEmpresaViejo;
            Integer idEmpresaNuevo;
            String nombreEmpresa;
            String telefonoEmpresa;
            String direccionEmpresa;
            String mailEmpresa;
            String razonSocialEmpresa;
            String rutEmpresa;
            while (viejoRes1.next()) {
                idEmpresaViejo = viejoRes1.getInt("t37_cd_empresa_adjudicada");
                nombreEmpresa = viejoRes1.getString("descripcion");
                telefonoEmpresa = viejoRes1.getString("telefono");
                direccionEmpresa = viejoRes1.getString("direccion");
                mailEmpresa = viejoRes1.getString("mail");
                razonSocialEmpresa = viejoRes1.getString("razon_social");
                rutEmpresa = viejoRes1.getString("rut");
                sql = "INSERT INTO organi_int_prove (orga_nombre, orga_proveedor, orga_razon_social, orga_rut,"
                        + "orga_mail, orga_telefono, orga_direccion, orga_org_fk) VALUES ('" + nombreEmpresa + "', 1,"
                        + "'" + UtilesVarios.tratarStrings(razonSocialEmpresa, "") + "', '"
                        + UtilesVarios.tratarStrings(rutEmpresa, "") + "', '" + UtilesVarios.tratarStrings(mailEmpresa, "") + "', '"
                        + UtilesVarios.tratarStrings(telefonoEmpresa, "") + "', '" + UtilesVarios.tratarStrings(direccionEmpresa, "") + "',"
                        + idOrganismo + ")";
                log(Tipo.SENTENCIASQL, sql);
                nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
                nuevoRes1.next();
                idEmpresaNuevo = nuevoRes1.getInt(1);
                nuevoRes1.close();
                mapeos.put("SIGET37_EMPRESA_ADJUDICADA_" + idEmpresaViejo, idEmpresaNuevo);
                log(Tipo.MAPEO, "SIGET37_EMPRESA_ADJUDICADA_" + idEmpresaViejo + " -> " + idEmpresaNuevo);
            }
        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private void migrarAdquisiciones(Connection viejoCon1, Connection nuevoCon1, Map<String, Integer> mapeos) throws MigracionException {
        try {
            Statement viejoSta1 = viejoCon1.createStatement();
            Statement nuevoSta1 = nuevoCon1.createStatement();
            //Migrar las adquisiciones
            String sql = "SELECT t32_cd_gasto, t03_cd_projeto, nm_descripcion, t34_cd_fuente, t35_cd_moneda, "
                    + "t37_cd_empresa_adjudicada"
                    + " FROM siget32_gastos"
                    + " ORDER BY T03_CD_PROJETO, T32_CD_GASTO";

            log(Tipo.SENTENCIASQL, sql);

            ResultSet viejoRes1 = viejoSta1.executeQuery(sql);
            Integer idGastoViejo;
            Integer idAdquisicionNuevo;
            Integer idProyectoViejo;
            Integer idProyectoNuevo;
            String descripcionGasto;
            Integer idFuenteViejo;
            Integer idFuenteNuevo;
            Integer idMonedaViejo;
            Integer idMonedaNuevo;
            Integer idEmpresaViejo;
            Integer idEmpresaNuevo;
            while (viejoRes1.next()) {
                idGastoViejo = viejoRes1.getInt("t32_cd_gasto");
                idProyectoViejo = viejoRes1.getInt("t03_cd_projeto");
                idFuenteViejo = viejoRes1.getInt("t34_cd_fuente");
                idMonedaViejo = viejoRes1.getInt("t35_cd_moneda");
                idEmpresaViejo = viejoRes1.getInt("t37_cd_empresa_adjudicada");
                descripcionGasto = viejoRes1.getString("nm_descripcion");

                idProyectoNuevo = mapeos.get("SIGET03_PROJETO_" + idProyectoViejo);
                //Si el id de proyecto nuevo es null, es porque no existe el proyecto o no se migro por no estar activo
                if (idProyectoNuevo != null) {
                    idEmpresaNuevo = mapeos.get("SIGET37_EMPRESA_ADJUDICADA_" + idEmpresaViejo);
                    idFuenteNuevo = mapeos.get("SIGET34_FUENTE_" + idFuenteViejo);
                    idMonedaNuevo = mapeos.get("SIGET35_MONEDA_" + idMonedaViejo);

                    sql = "INSERT INTO adquisicion (adq_pre_fk, adq_nombre, adq_prov_orga_fk, adq_fuente_fk,"
                            + "adq_moneda_fk) SELECT proy_pre_fk, '" + UtilesVarios.tratarStrings(descripcionGasto, "")
                            + "', " + idEmpresaNuevo + ", " + idFuenteNuevo + ", " + idMonedaNuevo
                            + " FROM proyectos WHERE proy_pk=" + idProyectoNuevo;
                    log(Tipo.SENTENCIASQL, sql);
                    nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                    ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
                    nuevoRes1.next();
                    idAdquisicionNuevo = nuevoRes1.getInt(1);
                    nuevoRes1.close();
                    mapeos.put("SIGET32_GASTOS_" + idGastoViejo, idAdquisicionNuevo);
                    log(Tipo.MAPEO, "SIGET32_GASTOS_" + idGastoViejo + " -> " + idAdquisicionNuevo);
                }
            }
        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private void migrarEntregables(Connection viejoCon1, Connection nuevoCon1, Map<String, Integer> mapeos) throws MigracionException {
        try {
            Statement viejoSta1 = viejoCon1.createStatement();
            Statement nuevoSta1 = nuevoCon1.createStatement();

            String sql = "SELECT e.T03_cd_projeto, e.T10_cd_etapa, f.T14_CD_FISICO,"
                    + " m.T08_CD_MARCO, e.nu_ordem, m.DS_MARCO, f.nm_subetapa,"
                    + " e.nm_etapa, m.DT_META, m.DT_REALIZADO,"
                    + " m.FL_STATUS, f.DT_INICIOP, f.DT_INICIOR, m.DS_COMENTARIO,"
                    + " m.NU_ESFORCO, e.t02_cd_usuario"
                    + " FROM SIGET10_Etapa AS e INNER JOIN"
                    + " SIGET14_FISICO AS f ON e.T10_cd_etapa = f.T10_CD_Etapa INNER JOIN"
                    + " SIGET08_MARCOS AS m ON f.T14_CD_FISICO = m.T14_CD_FISICO AND e.fl_ativa = 'S' AND m.FL_ATIVA = 'S'"
                    + " ORDER BY e.T03_cd_projeto, e.nu_ordem, m.DT_META";

            log(Tipo.SENTENCIASQL, sql);
            ResultSet viejoRes1 = viejoSta1.executeQuery(sql);

            Integer idEtapaViejo;
            Integer idFisicoViejo;
            Integer idMarcoViejo;
            Integer idEntregableNuevo;
            Integer idProyectoViejo;
            Integer idProyectoNuevo;
            String nombreMarco;
            String descripcionMarco;
            String nombreFisico;
            String nombreEtapa;
            Integer isPrograma;
            Date fechaFinPlanif;
            Date fechaFinReal;
            Date fechaInicioPlanif;
            Date fechaInicioReal;
            String status;
            String coordinador;
            Integer esfuerzo;

            Integer idProyectoViejoActual = null;
            idProyectoNuevo = null;
            int entId = 0;

            Integer lastIdEtapaViejo = 0;
            Integer lastIdFisicoViejo = 0;
            Integer idEtapaNuevo = null;
            Integer idFisicoNuevo = null;
            Long fechaInicioPlanifPadre = null;
            Long fechaInicioRealPadre = null;
            Long fechaFinPlanifPadre = null;
            Long fechaFinRealPadre = null;

            Long fechaInicioPlanifEtapa = null;
            Long fechaInicioRealEtapa = null;
            Long fechaFinPlanifEtapa = null;
            Long fechaFinRealEtapa = null;

            Long fechaInicioPlanifFisico = null;
            Long fechaInicioRealFisico = null;
            Long fechaFinPlanifFisico = null;
            Long fechaFinRealFisico = null;
            int croPkLast = -1;

            while (viejoRes1.next()) {
                idProyectoViejo = viejoRes1.getInt("t03_cd_projeto");

                //Obtengo el proyecto para conocer la fase y si es programa.
                String sqlFase = "SELECT fl_fase, es_programa, NM_PROJETO"
                        + " FROM SIGET03_PROJETO"
                        + " WHERE t03_cd_projeto=" + idProyectoViejo.toString();
                log(Tipo.SENTENCIASQL, sqlFase);
                Statement faseSta1 = viejoCon1.createStatement();
                ResultSet faseRes1 = faseSta1.executeQuery(sqlFase);
                faseRes1.next();
                String fase = faseRes1.getString("fl_fase").trim();
                String nombreProjeto = faseRes1.getString("NM_PROJETO").trim();
                isPrograma = faseRes1.getInt("es_programa");

                idEtapaViejo = viejoRes1.getInt("t10_cd_etapa");
                idFisicoViejo = viejoRes1.getInt("T14_CD_FISICO");
                idMarcoViejo = viejoRes1.getInt("t08_cd_marco");
                nombreMarco = viejoRes1.getString("ds_marco");
                descripcionMarco = viejoRes1.getString("ds_comentario");
                nombreFisico = viejoRes1.getString("nm_subetapa");
                nombreEtapa = viejoRes1.getString("nm_etapa");
                esfuerzo = viejoRes1.getInt("nu_esforco");
                status = viejoRes1.getString("fl_status");
                coordinador = viejoRes1.getString("t02_cd_usuario");

                Date inicio = viejoRes1.getDate("dt_inicior") != null ? viejoRes1.getDate("dt_inicior") : viejoRes1.getDate("dt_iniciop");
                fechaInicioPlanif = "EX".equalsIgnoreCase(fase) || "CO".equalsIgnoreCase(fase) ? inicio : null;
                fechaInicioReal = inicio;
                Date fin = viejoRes1.getDate("dt_realizado") != null ? viejoRes1.getDate("dt_realizado") : viejoRes1.getDate("dt_meta");
                fechaFinPlanif = "EX".equalsIgnoreCase(fase) || "CO".equalsIgnoreCase(fase) ? fin : null;
                fechaFinReal = fin;

                if (!(idProyectoViejoActual != null && idProyectoViejoActual.intValue() == idProyectoViejo.intValue())) {
                    idProyectoNuevo = mapeos.get("SIGET03_PROJETO_" + idProyectoViejo);
                    idProyectoViejoActual = idProyectoViejo;
                }

                //Si el id de proyecto nuevo es null, es porque no existe el proyecto o no se migro por no estar activo
                if (idProyectoNuevo != null) {

                    if (isPrograma != null && isPrograma.equals(1)) {
                        sql = "select prog_cro_fk from programas where prog_pk=" + idProyectoNuevo;
                    } else {
                        sql = "select proy_cro_fk from proyectos where proy_pk=" + idProyectoNuevo;
                    }

                    log(Tipo.SENTENCIASQL, sql);
                    ResultSet croRes1 = nuevoSta1.executeQuery(sql);
                    croRes1.next();
                    Integer croPk = null;
                    try {
                        croPk = croRes1.getInt("proy_cro_fk");
                    } catch (SQLException ex) {
                        log(Tipo.MENSAJE, "Proyecto '" + idProyectoNuevo + "' sin cronograma.");
                    }

                    if (croPk != null) {

                        if (croPk != croPkLast) {
                            entId = 0;
                            croPkLast = croPk;

                            if (croPkLast > -1) {
                                updateEntregable(nuevoCon1, idEtapaNuevo,
                                        fechaInicioPlanifPadre, fechaFinPlanifPadre,
                                        fechaInicioRealPadre, fechaFinRealPadre, 0);
                            }

                            fechaInicioPlanifPadre = null;
                            fechaInicioRealPadre = null;
                            fechaFinPlanifPadre = null;
                            fechaFinRealPadre = null;

                            entId++;
                            idEtapaNuevo = insertEntregable(nuevoCon1, croPk, entId, nombreProjeto,
                                    UtilesVarios.convertDateToLong(fechaInicioPlanif, true),
                                    UtilesVarios.convertDateToLong(fechaFinPlanif, false),
                                    UtilesVarios.convertDateToLong(fechaInicioReal, true),
                                    UtilesVarios.convertDateToLong(fechaFinReal, false),
                                    "", 0, 0, 0, obtenerUsuNuevoPorCodigo(nuevoCon1, coordinador));
                        }

                        if (!idEtapaViejo.equals(lastIdEtapaViejo)) {
                            if (lastIdEtapaViejo > 0) {
                                updateEntregable(nuevoCon1, idEtapaNuevo,
                                        fechaInicioPlanifEtapa, fechaFinPlanifEtapa,
                                        fechaInicioRealEtapa, fechaFinRealEtapa, 0);
                            }

                            fechaInicioPlanifEtapa = null;
                            fechaInicioRealEtapa = null;
                            fechaFinPlanifEtapa = null;
                            fechaFinRealEtapa = null;

                            lastIdEtapaViejo = idEtapaViejo;
                            entId++;
                            idEtapaNuevo = insertEntregable(nuevoCon1, croPk, entId, nombreEtapa,
                                    UtilesVarios.convertDateToLong(fechaInicioPlanif, true),
                                    UtilesVarios.convertDateToLong(fechaFinPlanif, false),
                                    UtilesVarios.convertDateToLong(fechaInicioReal, true),
                                    UtilesVarios.convertDateToLong(fechaFinReal, false),
                                    "", 0, 0, 1, obtenerUsuNuevoPorCodigo(nuevoCon1, coordinador));

                            mapeos.put("t10_cd_etapa" + idEtapaViejo, idEtapaNuevo);
                            log(Tipo.MAPEO, "t10_cd_etapa" + idEtapaViejo + " -> " + idEtapaNuevo);
                        }

                        entId++;
                        idEntregableNuevo = insertEntregable(nuevoCon1, croPk, entId, nombreMarco,
                                UtilesVarios.convertDateToLong(fechaInicioPlanif, true),
                                UtilesVarios.convertDateToLong(fechaFinPlanif, false),
                                UtilesVarios.convertDateToLong(fechaInicioReal, true),
                                UtilesVarios.convertDateToLong(fechaFinReal, false),
                                descripcionMarco, ("B".equals(status) ? 100 : 0),
                                esfuerzo, 2, obtenerUsuNuevoPorCodigo(nuevoCon1, coordinador));

//                        mapeos.put("t10_cd_etapa" + idEtapaViejo, idEntregableNuevo);
//                        log(Tipo.MAPEO, "t10_cd_etapa" + idEtapaViejo + " -> " + idEntregableNuevo);
                        Long fechaInicioPlanifLong = UtilesVarios.convertDateToLong(fechaInicioPlanif, true);
                        Long fechaInicioRealLong = UtilesVarios.convertDateToLong(fechaInicioReal, true);
                        Long fechaFinPlanifLong = UtilesVarios.convertDateToLong(fechaFinPlanif, false);
                        Long fechaFinRealLong = UtilesVarios.convertDateToLong(fechaFinReal, false);

                        fechaInicioPlanifPadre = fechaInicioPlanifPadre == null || (fechaInicioPlanifLong != null && fechaInicioPlanifLong < fechaInicioPlanifPadre)
                                ? fechaInicioPlanifLong : fechaInicioPlanifPadre;
                        fechaInicioRealPadre = fechaInicioRealPadre == null || (fechaInicioRealLong != null && fechaInicioRealLong < fechaInicioRealPadre)
                                ? fechaInicioRealLong : fechaInicioRealPadre;
                        fechaFinPlanifPadre = fechaFinPlanifPadre == null || (fechaFinPlanifLong != null && fechaFinPlanifLong > fechaFinPlanifPadre)
                                ? fechaFinPlanifLong : fechaFinPlanifPadre;
                        fechaFinRealPadre = fechaFinRealPadre == null || (fechaFinRealLong != null && fechaFinRealLong > fechaFinRealPadre)
                                ? fechaFinRealLong : fechaFinRealPadre;

                        fechaInicioPlanifEtapa = fechaInicioPlanifEtapa == null || (fechaInicioPlanifLong != null && fechaInicioPlanifLong < fechaInicioPlanifEtapa)
                                ? fechaInicioPlanifLong : fechaInicioPlanifEtapa;
                        fechaInicioRealEtapa = fechaInicioRealEtapa == null || (fechaInicioRealLong != null && fechaInicioRealLong < fechaInicioRealEtapa)
                                ? fechaInicioRealLong : fechaInicioRealEtapa;
                        fechaFinPlanifEtapa = fechaFinPlanifEtapa == null || (fechaFinPlanifLong != null && fechaFinPlanifLong > fechaFinPlanifEtapa)
                                ? fechaFinPlanifLong : fechaFinPlanifEtapa;
                        fechaFinRealEtapa = fechaFinRealEtapa == null || (fechaFinRealLong != null && fechaFinRealLong > fechaFinRealEtapa)
                                ? fechaFinRealLong : fechaFinRealEtapa;

                        fechaInicioPlanifFisico = fechaInicioPlanifFisico == null || (fechaInicioPlanifLong != null && fechaInicioPlanifLong < fechaInicioPlanifFisico)
                                ? fechaInicioPlanifLong : fechaInicioPlanifFisico;
                        fechaInicioRealFisico = fechaInicioRealFisico == null || (fechaInicioRealLong != null && fechaInicioRealLong < fechaInicioRealFisico)
                                ? fechaInicioRealLong : fechaInicioRealFisico;
                        fechaFinPlanifFisico = fechaFinPlanifFisico == null || (fechaFinPlanifLong != null && fechaFinPlanifLong > fechaFinPlanifFisico)
                                ? fechaFinPlanifLong : fechaFinPlanifFisico;
                        fechaFinRealFisico = fechaFinRealFisico == null || (fechaFinRealLong != null && fechaFinRealLong > fechaFinRealFisico)
                                ? fechaFinRealLong : fechaFinRealFisico;

                        if (idEntregableNuevo > 0) {
                            mapeos.put("SIGET08_MARCOS_" + idMarcoViejo, idEntregableNuevo);
                            log(Tipo.MAPEO, "SIGET08_MARCOS_" + idMarcoViejo + " -> " + idEntregableNuevo);
                        }
                    }
                }
            }

        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private int insertEntregable(Connection nuevoCon1, Integer croFk, int entId, String entNombre,
            Long entIniLineaBase, Long entFinLineaBase, Long entInicio, Long entFin,
            String entDesc, int entProgreso, int entEsfuerzo,
            int entNivel, int coordinador) throws MigracionException {

        entIniLineaBase = entIniLineaBase != null ? entIniLineaBase : 0;
        entFinLineaBase = entFinLineaBase != null ? entFinLineaBase : 0;

        Integer entDuracionLineaBase = 0;
        if (entFinLineaBase != 0 && entFinLineaBase == entIniLineaBase) {
            entDuracionLineaBase = 0;
        } else {
            entDuracionLineaBase = entIniLineaBase != null && entIniLineaBase > 0 && entFinLineaBase != null && entFinLineaBase > 0 ? Math.round((entFinLineaBase - entIniLineaBase) / 86400000) : 0;
        }

        entInicio = entInicio != null ? entInicio : 0;
        entFin = entFin != null ? entFin : 0;

        Integer entDuracion = 0;
        if (entFin != 0 && entFin == entInicio) {
            entDuracion = 0;
        } else {
            entDuracion = entInicio != null && entInicio > 0 && entFin != null && entFin > 0 ? Math.round((entFin - entInicio) / 86400000) : 0;
        }
        try {
            Statement nuevoSta1 = nuevoCon1.createStatement();

            String sql = "INSERT INTO entregables "
                    + "(ent_cro_fk, ent_id, ent_nombre, ent_inicio_linea_base, "
                    + "ent_fin_linea_base, ent_inicio, ent_fin, ent_descripcion, ent_progreso, "
                    + "ent_inicio_es_hito, ent_fin_es_hito, ent_status, ent_duracion, ent_codigo, "
                    + "ent_collapsed, ent_esfuerzo, ent_duracion_linea_base, ent_nivel, ent_coord_usu_fk) "
                    + " VALUES(" + croFk + ", " + entId + ", '" + UtilesVarios.tratarStrings(entNombre, "")
                    + "', " + entIniLineaBase + ", " + entFinLineaBase + ", "
                    + entInicio + ", " + entFin + ", '"
                    + UtilesVarios.tratarStrings(entDesc, "") + "', " + entProgreso
                    + ", 0, 0, 'STATUS_ACTIVE'," + entDuracion
                    + ", '', 0, " + entEsfuerzo + ", " + entDuracionLineaBase
                    + ", " + entNivel + ", " + coordinador + ")";

            log(Tipo.SENTENCIASQL, sql);
            nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
            nuevoRes1.next();
            return nuevoRes1.getInt(1);

        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private void updateEntregable(Connection nuevoCon1, Integer entPk,
            Long entIniLineaBase, Long entFinLineaBase, Long entInicio, Long entFin,
            int entProgreso) throws MigracionException {

        entIniLineaBase = entIniLineaBase != null ? entIniLineaBase : 0;
        entFinLineaBase = entFinLineaBase != null ? entFinLineaBase : 0;
        Integer entDuracionLineaBase = entIniLineaBase != null && entIniLineaBase > 0 && entFinLineaBase != null && entFinLineaBase > 0 ? Math.round((entFinLineaBase - entIniLineaBase) / 86400000) : 0;

        entInicio = entInicio != null ? entInicio : 0;
        entFin = entFin != null ? entFin : 0;
        Integer entDuracion = entInicio != null && entInicio > 0 && entFin != null && entFin > 0 ? Math.round((entFin - entInicio) / 86400000) : 0;

        try {
            Statement nuevoSta1 = nuevoCon1.createStatement();

            String sql = "UPDATE entregables "
                    + "SET ent_inicio_linea_base=" + entIniLineaBase + ", "
                    + "ent_fin_linea_base=" + entFinLineaBase + ", "
                    + "ent_inicio=" + entInicio + ", "
                    + "ent_fin=" + entFin + ", "
                    + "ent_progreso=" + entProgreso + ", "
                    + "ent_duracion=" + entDuracion + ", "
                    + "ent_duracion_linea_base=" + entDuracionLineaBase
                    + " WHERE ent_pk=" + entPk;

            log(Tipo.SENTENCIASQL, sql);
            nuevoSta1.executeUpdate(sql);

        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private Integer obtenerUsuNuevoPorCodigo(Connection nuevoCon1, String codigo) {
        if (codigo != null && !codigo.trim().isEmpty()) {
            try {
                Statement nuevoSta1 = nuevoCon1.createStatement();
                String sql = "SELECT usu_id FROM ss_usuario where usu_cod='" + codigo.trim() + "';";
                log(Tipo.SENTENCIASQL, sql);
                ResultSet nuevoRes1 = nuevoSta1.executeQuery(sql);
                nuevoRes1.next();
                return nuevoRes1.getInt("usu_id");
            } catch (SQLException ex) {
                Logger.getLogger(Migrador2.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return null;
    }

    private void migrarPagos(Connection viejoCon1, Connection nuevoCon1, Map<String, Integer> mapeos) throws MigracionException {
        try {
            Statement viejoSta1 = viejoCon1.createStatement();
            Statement nuevoSta1 = nuevoCon1.createStatement();
            //Migrar los pagos
            String sql = "SELECT t33_cd_pago, t32_cd_gasto, t08_cd_marco, fecha_planificada, monto_planificado,"
                    + "fecha_pagado, monto_pagado, nm_descripcion FROM siget33_pagos";
            log(Tipo.SENTENCIASQL, sql);
            ResultSet viejoRes1 = viejoSta1.executeQuery(sql);

            Integer idPagoViejo;
            Integer idPagoNuevo;
            Integer idGastoViejo;
            Integer idAdquisicionNuevo;
            Integer idMarcoViejo;
            Integer idEntregableNuevo;
            String descripcionPago;
            Date fechaPlanif;
            Float montoPlanif;
            Date fechaPagado;
            Float montoPagado;

            while (viejoRes1.next()) {
                idPagoViejo = viejoRes1.getInt("t33_cd_pago");
                idMarcoViejo = viejoRes1.getInt("t08_cd_marco");
                idGastoViejo = viejoRes1.getInt("t32_cd_gasto");
                descripcionPago = viejoRes1.getString("nm_descripcion");
                fechaPlanif = viejoRes1.getDate("fecha_planificada");
                fechaPagado = viejoRes1.getDate("fecha_pagado");
                montoPlanif = viejoRes1.getFloat("monto_planificado");
                montoPagado = viejoRes1.getFloat("monto_pagado");

                idAdquisicionNuevo = mapeos.get("SIGET32_GASTOS_" + idGastoViejo);
                idEntregableNuevo = mapeos.get("SIGET08_MARCOS_" + idMarcoViejo);
                //Si no se encuentra la adquisicion o el entregable, se trata de un proyecto que no esta activo
                //o de un programa, en cualquier caso no se migra el pago
                if (idAdquisicionNuevo != null && idEntregableNuevo != null) {
                    sql = "INSERT INTO pagos (pag_adq_fk, pag_ent_fk, pag_observacion, pag_fecha_planificada,"
                            + "pag_importe_planificado, pag_fecha_real, pag_importe_real) "
                            + "VALUES(" + idAdquisicionNuevo + ", " + idEntregableNuevo + ", '"
                            + UtilesVarios.tratarStrings(descripcionPago, "") + "', "
                            + UtilesVarios.convertDateToString(UtilesVarios.siNulo(fechaPlanif)) + ","
                            + UtilesVarios.siNulo(montoPlanif, 0f) + ", "
                            + UtilesVarios.convertDateToString(fechaPagado) + "," + montoPagado + ")";
                    log(Tipo.SENTENCIASQL, sql);
                    nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                    ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
                    nuevoRes1.next();
                    idPagoNuevo = nuevoRes1.getInt(1);
                    nuevoRes1.close();
                    mapeos.put("SIGET33_PAGOS_" + idPagoViejo, idPagoNuevo);
                    log(Tipo.MAPEO, "SIGET33_PAGOS_" + idPagoViejo + " -> " + idPagoNuevo);
                }
            }
        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private void migrarTiposDocumentos(Connection viejoCon1, Connection nuevoCon1, int idOrganismo, Map<String, Integer> mapeos) throws MigracionException {
        try {
            Statement viejoSta1 = viejoCon1.createStatement();
            Statement nuevoSta1 = nuevoCon1.createStatement();
            //Migrar los tipos de documentos
            String sql = "SELECT t30_cd_documento, nm_descripcion, exigible_inicio, exigible_planificacion,"
                    + "exigible_ejecucion, exigible_fin, peso FROM siget30_documentos_proyecto";
            log(Tipo.SENTENCIASQL, sql);
            ResultSet viejoRes1 = viejoSta1.executeQuery(sql);

            Integer idTipoDocumentoViejo;
            Integer idTipoDocumentoNuevo;
            Integer exigibleInicio;
            Integer exigiblePlanificacion;
            Integer exigibleEjecucion;
            Integer exigibleFin;
            String estadoExigible;
            Integer peso;
            String tipoDocumento;

            while (viejoRes1.next()) {
                idTipoDocumentoViejo = viejoRes1.getInt("t30_cd_documento");
                exigibleInicio = viejoRes1.getInt("exigible_inicio");
                exigiblePlanificacion = viejoRes1.getInt("exigible_planificacion");
                exigibleEjecucion = viejoRes1.getInt("exigible_ejecucion");
                exigibleFin = viejoRes1.getInt("exigible_fin");
                peso = viejoRes1.getInt("peso");
                tipoDocumento = viejoRes1.getString("nm_descripcion");

                Integer menorMarcado = -1;
                if (exigibleFin.intValue() == 1) {
                    menorMarcado = 4;
                }
                if (exigibleEjecucion.intValue() == 1) {
                    menorMarcado = 3;
                }
                if (exigiblePlanificacion.intValue() == 1) {
                    menorMarcado = 2;
                }
                if (exigibleInicio.intValue() == 1) {
                    menorMarcado = 1;
                }

                //satella llamda Pablo 18-08-2014
                if (menorMarcado.intValue() == 1) {
                    estadoExigible = "Inicio";
                } else if (menorMarcado.intValue() == 2) {
                    estadoExigible = "Inicio";
                } else if (menorMarcado.intValue() == 3) {
                    estadoExigible = "Planificacion";
                } else if (menorMarcado.intValue() == 4) {
                    estadoExigible = "Ejecucion";
                } else {
                    estadoExigible = "No Exigido";
                }

                sql = "INSERT INTO tipo_documento (tipodoc_org_fk, tipodoc_nombre, tipodoc_exigido_desde, tipodoc_peso) "
                        + "SELECT " + idOrganismo + ", '" + UtilesVarios.tratarStrings(tipoDocumento, "")
                        + "', est_pk, " + peso + " FROM estados WHERE est_nombre='" + estadoExigible + "'";
                log(Tipo.SENTENCIASQL, sql);
                nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
                nuevoRes1.next();
                idTipoDocumentoNuevo = nuevoRes1.getInt(1);
                nuevoRes1.close();
                mapeos.put("SIGET30_DOCUMENTOS_PROYECTOS_" + idTipoDocumentoViejo, idTipoDocumentoNuevo);
                log(Tipo.MAPEO, "SIGET30_DOCUMENTOS_PROYECTOS_" + idTipoDocumentoViejo + " -> " + idTipoDocumentoNuevo);
            }
        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private void migrarInstanciasTiposDocumentos(Connection viejoCon1, Connection nuevoCon1, Map<String, Integer> mapeos) throws MigracionException {
        try {
            Statement viejoSta1 = viejoCon1.createStatement();
            Statement nuevoSta1 = nuevoCon1.createStatement();
            //Migrar las instancias de tipos de documentos
            String sql = "SELECT t38_cd_proydocs, inicio, planificacion, ejecucion, fin, pd.peso,"
                    + "pd.t03_cd_projeto, t30_cd_documento, es_programa, nota"
                    + " FROM siget38_proyecto_documentos pd"
                    + " JOIN siget03_projeto p"
                    + " ON p.t03_cd_projeto = pd.t03_cd_projeto"
                    + " WHERE p.activo='S'";
            log(Tipo.SENTENCIASQL, sql);
            ResultSet viejoRes1 = viejoSta1.executeQuery(sql);

            Integer idProyDocumentoViejo;
            Integer idProyDocumentoNuevo;
            Integer exigibleInicio;
            Integer exigiblePlanificacion;
            Integer exigibleEjecucion;
            Integer exigibleFin;
            String estadoExigible;
            Integer peso;
            Integer idTipoDocumentoViejo;
            Integer idTipoDocumentoNuevo;
            Integer idProyectoViejo;
            Integer idProyectoNuevo;
            Integer esPrograma;
            Double estado;

            while (viejoRes1.next()) {
                idProyDocumentoViejo = viejoRes1.getInt("t38_cd_proydocs");
                exigibleInicio = viejoRes1.getInt("inicio");
                exigiblePlanificacion = viejoRes1.getInt("planificacion");
                exigibleEjecucion = viejoRes1.getInt("ejecucion");
                exigibleFin = viejoRes1.getInt("fin");
                peso = viejoRes1.getInt("peso");
                idTipoDocumentoViejo = viejoRes1.getInt("t30_cd_documento");
                idProyectoViejo = viejoRes1.getInt("t03_cd_projeto");
                esPrograma = viejoRes1.getInt("es_programa");
                estado = viejoRes1.getDouble("nota");

                Integer menorMarcado = -1;
                if (exigibleFin.intValue() == 1) {
                    menorMarcado = 4;
                }
                if (exigibleEjecucion.intValue() == 1) {
                    menorMarcado = 3;
                }
                if (exigiblePlanificacion.intValue() == 1) {
                    menorMarcado = 2;
                }
                if (exigibleInicio.intValue() == 1) {
                    menorMarcado = 1;
                }

                //satella llamda Pablo 18-08-2014
                if (menorMarcado.intValue() == 1) {
                    estadoExigible = "Inicio";
                } else if (menorMarcado.intValue() == 2) {
                    estadoExigible = "Inicio";
                } else if (menorMarcado.intValue() == 3) {
                    estadoExigible = "Planificacion";
                } else if (menorMarcado.intValue() == 4) {
                    estadoExigible = "Ejecucion";
                } else {
                    estadoExigible = "No Exigido";
                }

                idTipoDocumentoNuevo = mapeos.get("SIGET30_DOCUMENTOS_PROYECTOS_" + idTipoDocumentoViejo);

                if (idTipoDocumentoNuevo != null) {
                    idProyectoNuevo = mapeos.get("SIGET03_PROJETO_" + idProyectoViejo);

                    sql = "SELECT tipodoc_inst_pk"
                            + " FROM tipo_documento_instancia"
                            + " WHERE tipodoc_inst_tipodoc_fk = " + idTipoDocumentoNuevo
                            + " AND tipodoc_inst_proy_fk " + (esPrograma.intValue() == 1 ? "IS NULL" : "=" + idProyectoNuevo)
                            + " AND tipodoc_inst_prog_fk " + (esPrograma.intValue() == 0 ? "IS NULL" : "=" + idProyectoNuevo);
                    log(Tipo.SENTENCIASQL, sql);
                    ResultSet existeRes1 = nuevoSta1.executeQuery(sql);

                    existeRes1.next();
                    int id = 0;
                    try {
                        id = existeRes1.getInt("tipodoc_inst_pk");
                    } catch (SQLException sQLException) {
                    }
                    if (id == 0) {
                        sql = "INSERT INTO tipo_documento_instancia (tipodoc_inst_tipodoc_fk, tipodoc_inst_exigido_desde, "
                                + "tipodoc_inst_peso, tipodoc_inst_proy_fk, tipodoc_inst_prog_fk) "
                                + "SELECT " + idTipoDocumentoNuevo + ", est_pk, " + peso + ", "
                                + (esPrograma.intValue() == 1 ? "NULL" : idProyectoNuevo) + ", "
                                + (esPrograma.intValue() == 0 ? "NULL" : idProyectoNuevo)
                                + " FROM estados WHERE est_nombre='" + estadoExigible + "'";
                        log(Tipo.SENTENCIASQL, sql);
                        nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                        ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
                        nuevoRes1.next();
                        idProyDocumentoNuevo = nuevoRes1.getInt(1);
                        nuevoRes1.close();
                        mapeos.put("SIGET38_PROYECTOS_DOCUMENTOS_" + idProyDocumentoViejo, idProyDocumentoNuevo);
                        log(Tipo.MAPEO, "SIGET38_PROYECTOS_DOCUMENTOS_" + idProyDocumentoViejo + " -> " + idProyDocumentoNuevo);
                    }
                }
            }
        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private void migrarDocumentos(Connection viejoCon1, Connection nuevoCon1, String rutaArchivos, Map<String, Integer> mapeos) throws MigracionException {
        try {
            Statement viejoSta1 = viejoCon1.createStatement();
            Statement viejoSta2 = viejoCon1.createStatement();
            Statement nuevoSta1 = nuevoCon1.createStatement();

            Integer idDocumentoViejo;
            Integer idDocumentoNuevo;
            Integer idArchivoNuevo;
            Integer idTipoDocumentoViejo;
            Integer idTipoDocumentoNuevo;
            String nombreDocumento;
            String nombreArchivo;
            Date fechaArchivo;
            Integer idProyectoViejo;
            Integer idProyectoNuevo;
            Integer exigibleInicio;
            Integer exigiblePlanificacion;
            Integer exigibleEjecucion;
            Integer exigibleFin;
            String estadoExigible;
            Integer peso;
            Integer esPrograma;
            Integer idProyDocumentoNuevo;
            byte[] bytes;

            @SuppressWarnings("unchecked")
            Map<Integer, Object> docsPend = new HashMap();
            //Obtener la lista de documentos que aun no fueron aprobados
            String sql = "select t15_cd_doc FROM siget15_documentos reg, "
                    + " siget30_documentos_proyecto cat, siget38_proyecto_documentos doc, "
                    + " siget03_projeto pro where reg.t30_cd_documento = cat.t30_cd_documento "
                    + " and reg.t03_cd_projeto = doc.t03_cd_projeto "
                    + " and reg.t30_cd_documento = doc.t30_cd_documento "
                    + " and doc.dt_registro < reg.dt_cadastro "
                    + " and pro.t03_cd_projeto = reg.t03_cd_projeto";
            log(Tipo.SENTENCIASQL, sql);
            ResultSet viejoRes1 = viejoSta1.executeQuery(sql);
            while (viejoRes1.next()) {
                idDocumentoViejo = viejoRes1.getInt("t15_cd_doc");
                docsPend.put(idDocumentoViejo, idDocumentoViejo);
            }
            viejoRes1.close();

            //Migrar los documentos
            sql = "SELECT t15_cd_doc, nm_doc, nm_arquivo, d.t30_cd_documento, d.dt_cadastro, "
                    + " nm_descripcion, exigible_inicio, exigible_planificacion, exigible_ejecucion,"
                    + " exigible_fin, dp.peso, p.t03_cd_projeto, p.es_programa"
                    + " FROM siget15_documentos d"
                    + " JOIN siget03_projeto p ON p.t03_cd_projeto = d.t03_cd_projeto "
                    + " JOIN siget30_documentos_proyecto dp ON dp.t30_cd_documento=d.t30_cd_documento "
                    + " WHERE p.activo='S' AND p.es_programa=0";
            log(Tipo.SENTENCIASQL, sql);
            viejoRes1 = viejoSta1.executeQuery(sql);

            String sqlInsertDocFile = "INSERT INTO doc_file (docfile_nombre, docfile_file, docfile_doc_fk) VALUES (?,?,?)";
            PreparedStatement pSt = nuevoCon1.prepareStatement(sqlInsertDocFile);

            while (viejoRes1.next()) {
                idDocumentoViejo = viejoRes1.getInt("t15_cd_doc");
                nombreDocumento = viejoRes1.getString("nm_doc");
                nombreArchivo = viejoRes1.getString("nm_arquivo");
                idTipoDocumentoViejo = viejoRes1.getInt("t30_cd_documento");
                fechaArchivo = viejoRes1.getDate("dt_cadastro");
                exigibleInicio = viejoRes1.getInt("exigible_inicio");
                exigiblePlanificacion = viejoRes1.getInt("exigible_planificacion");
                exigibleEjecucion = viejoRes1.getInt("exigible_ejecucion");
                exigibleFin = viejoRes1.getInt("exigible_fin");
                peso = viejoRes1.getInt("peso");
                esPrograma = viejoRes1.getInt("es_programa");
                idProyectoViejo = viejoRes1.getInt("t03_cd_projeto");

                idTipoDocumentoNuevo = mapeos.get("SIGET30_DOCUMENTOS_PROYECTOS_" + idTipoDocumentoViejo);

                if (idTipoDocumentoNuevo != null) {
                    ResultSet nuevoRes1;
                    //Crear una instancia del tipo de documento para contener este documento
                    /*if (exigibleInicio.intValue() == 1) {
                     estadoExigible = "Inicio";
                     } else if (exigiblePlanificacion.intValue() == 1) {
                     estadoExigible = "Planificacion";
                     } else if (exigibleEjecucion.intValue() == 1) {
                     estadoExigible = "Ejecucion";
                     } else if (exigibleFin.intValue() == 1) {
                     estadoExigible = "Finalizado";
                     } else {
                     estadoExigible = "No Exigido";
                     }*/

                    Integer menorMarcado = -1;
                    if (exigibleFin.intValue() == 1) {
                        menorMarcado = 4;
                    }
                    if (exigibleEjecucion.intValue() == 1) {
                        menorMarcado = 3;
                    }
                    if (exigiblePlanificacion.intValue() == 1) {
                        menorMarcado = 2;
                    }
                    if (exigibleInicio.intValue() == 1) {
                        menorMarcado = 1;
                    }

                    //satella llamda Pablo 18-08-2014
                    if (menorMarcado.intValue() == 1) {
                        estadoExigible = "Inicio";
                    } else if (menorMarcado.intValue() == 2) {
                        estadoExigible = "Inicio";
                    } else if (menorMarcado.intValue() == 3) {
                        estadoExigible = "Planificacion";
                    } else if (menorMarcado.intValue() == 4) {
                        estadoExigible = "Ejecucion";
                    } else {
                        estadoExigible = "No Exigido";
                    }

                    idProyectoNuevo = mapeos.get("SIGET03_PROJETO_" + idProyectoViejo);

                    sql = "SELECT tipodoc_inst_pk"
                            + " FROM tipo_documento_instancia"
                            + " WHERE tipodoc_inst_tipodoc_fk = " + idTipoDocumentoNuevo
                            + " AND tipodoc_inst_proy_fk " + (esPrograma.intValue() == 1 ? "IS NULL" : "=" + idProyectoNuevo)
                            + " AND tipodoc_inst_prog_fk " + (esPrograma.intValue() == 0 ? "IS NULL" : "=" + idProyectoNuevo);
                    log(Tipo.SENTENCIASQL, sql);
                    ResultSet existeRes1 = nuevoSta1.executeQuery(sql);
                    existeRes1.next();

                    int id = 0;
                    try {
                        id = existeRes1.getInt("tipodoc_inst_pk");
                    } catch (SQLException sQLException) {
                    }
//                    log(Tipo.MENSAJE, "id:" + id);
                    if (id == 0) {
                        sql = "INSERT INTO tipo_documento_instancia (tipodoc_inst_tipodoc_fk, tipodoc_inst_exigido_desde, "
                                + "tipodoc_inst_peso, tipodoc_inst_proy_fk, tipodoc_inst_prog_fk) "
                                + "SELECT " + idTipoDocumentoNuevo + ", est_pk, " + peso + ", "
                                + (esPrograma.intValue() == 1 ? "NULL" : idProyectoNuevo) + ", "
                                + (esPrograma.intValue() == 0 ? "NULL" : idProyectoNuevo)
                                + " FROM estados WHERE est_nombre='" + estadoExigible + "'";

                        log(Tipo.SENTENCIASQL, sql);
                        nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                        nuevoRes1 = nuevoSta1.getGeneratedKeys();
                        nuevoRes1.next();
                        idProyDocumentoNuevo = nuevoRes1.getInt(1);
                        nuevoRes1.close();
                    } else {
                        idProyDocumentoNuevo = id;
                    }

                    sql = "SELECT nota"
                            + " FROM siget38_proyecto_documentos pd"
                            + " WHERE T03_CD_PROJETO = " + idProyectoViejo
                            + " AND t30_cd_documento = " + idTipoDocumentoViejo;
                    log(Tipo.SENTENCIASQL, sql);
                    ResultSet notaRes1 = viejoSta2.executeQuery(sql);
                    Double estado = null;
                    if (notaRes1 != null) {
                        boolean next = notaRes1.next();
                        if (next) {
                            estado = notaRes1.getDouble("nota");
                        }
                    }

                    Integer docAprobado = 1;
                    if (docsPend.containsKey(idDocumentoViejo)) {
                        docAprobado = 0;
                    }

                    //Insertar el documento
                    sql = "INSERT INTO documentos (docs_nombre, docs_fecha, docs_tipodoc_fk, docs_estado, docs_aprobado) "
                            + "VALUES ('" + UtilesVarios.tratarStrings(nombreDocumento, "") + "', "
                            + UtilesVarios.convertDateToString(UtilesVarios.siNulo(fechaArchivo)) + ", "
                            + idProyDocumentoNuevo + ", " + (estado != null ? estado : 0) + "," + docAprobado + ")";
                    log(Tipo.SENTENCIASQL, sql);
                    nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                    nuevoRes1 = nuevoSta1.getGeneratedKeys();
                    nuevoRes1.next();
                    idDocumentoNuevo = nuevoRes1.getInt(1);
                    nuevoRes1.close();

                    if (esPrograma.equals(1)) {
                        sql = "INSERT INTO prog_docs (progdocs_prog_pk, progdocs_doc_pk) VALUES(" + idProyectoNuevo + " , " + idDocumentoNuevo + ")";
                    } else {
                        sql = "INSERT INTO proy_docs (proydoc_proy_pk, proydoc_doc_pk) VALUES(" + idProyectoNuevo + " , " + idDocumentoNuevo + ")";
                    }
                    nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                    mapeos.put("SIGET15_DOCUMENTOS_" + idDocumentoViejo, idDocumentoNuevo);
                    log(Tipo.MAPEO, "SIGET15_DOCUMENTOS_" + idDocumentoViejo + " -> " + idDocumentoNuevo);

                    //Actualizar el archivo con los bytes
                    bytes = UtilesVarios.cargarArchivo(rutaArchivos + nombreArchivo);
                    if (bytes != null) {
                        pSt.setString(1, nombreArchivo);
                        pSt.setBytes(2, bytes);
                        pSt.setInt(3, idDocumentoNuevo);
                        try {
                            pSt.executeUpdate();
                        } catch (SQLException sqlEx) {
                            log(Tipo.MENSAJE, "Error Guardar Archivo Doc: (" + idDocumentoNuevo + ")" + UtilesVarios.tratarStrings(nombreDocumento, "") + "\"" + nombreArchivo + "\"");
                            log(Tipo.MENSAJE, sqlEx.getMessage());
                        }
                    }
                }
            }
            pSt.close();
        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private void migrarProductos(Connection viejoCon1, Connection nuevoCon1, Map<String, Integer> mapeos) throws MigracionException {
        try {
            Statement viejoSta1 = viejoCon1.createStatement();
            Statement nuevoSta1 = nuevoCon1.createStatement();
            //Migrar los Productos
            String sql = "SELECT T23_CD_PRODUTO, T10_CD_ETAPA, DS_PRODUTO, NU_ANO,"
                    + " NM_MEDIDA, NU_PREVISTO, NU_REALIZADO, DT_CADASTRO, DT_ALTERADO,"
                    + " vl_jan_real, vl_fev_real, vl_mar_real, vl_abr_real,"
                    + " vl_mai_real, vl_jun_real, vl_jul_real, vl_ago_real,"
                    + " vl_set_real, vl_out_real, vl_nov_real, vl_dez_real,"
                    + " vl_jan, vl_fev, vl_mar, vl_abr, vl_mai, vl_jun, vl_jul,"
                    + " vl_ago, vl_set, vl_out, vl_nov, vl_dez, nu_produto, ACUMULADO"
                    + " FROM SIGET23_PRODUTO"
                    + " ORDER BY T10_CD_ETAPA, T23_CD_PRODUTO, NU_ANO";
            log(Tipo.SENTENCIASQL, sql);
            ResultSet viejoRes1 = viejoSta1.executeQuery(sql);

            Integer idProductoViejo;
            Integer idProductoNuevo;
            String productoDesc;
            Integer peso;
            String unidadMedida;
            Integer entregableFk;
            Integer mes;
            Integer anio;
            Date fechaCreado;
            Date fechaModificado;
            Integer nu_produto;
            Double plan;
            Double real;
            Boolean acumulado;
            String[] mesStr = new String[]{"jan", "fev", "mar", "abr", "mai", "jun", "jul", "ago", "set", "out", "nov", "dez"};
            Map<String, Integer> etapaProduto = new HashMap<String, Integer>();

            while (viejoRes1.next()) {

                idProductoViejo = viejoRes1.getInt("T23_CD_PRODUTO");
                entregableFk = viejoRes1.getInt("T10_CD_ETAPA");
                productoDesc = viejoRes1.getString("DS_PRODUTO");
                anio = viejoRes1.getInt("NU_ANO");
                unidadMedida = viejoRes1.getString("NM_MEDIDA");
//                peso = viejoRes1.getInt("peso");
                peso = null;
                fechaCreado = viejoRes1.getDate("DT_CADASTRO");
                fechaModificado = viejoRes1.getDate("DT_ALTERADO");
                nu_produto = viejoRes1.getInt("nu_produto");
                acumulado = viejoRes1.getBoolean("ACUMULADO");

                Integer idEntregableNuevo = mapeos.get("t10_cd_etapa" + entregableFk);

                if (idEntregableNuevo != null) {
                    if (!etapaProduto.containsKey(entregableFk + "_" + nu_produto)) {
                        ResultSet nuevoRes1;
                        sql = "INSERT INTO productos "
                                + "(prod_peso, prod_und_medida, prod_ent_fk,"
                                + " prod_fecha_crea, prod_ult_mod, prod_acumulado,"
                                + " prod_desc)"
                                + "VALUES"
                                + "(" + (peso != null ? peso : 1) + ",'"
                                + unidadMedida + "'," + idEntregableNuevo
                                + ",'" + fechaCreado + "','" + fechaModificado + "',"
                                + acumulado + ", '" + productoDesc + "');";

                        log(Tipo.SENTENCIASQL, sql);
                        nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                        nuevoRes1 = nuevoSta1.getGeneratedKeys();
                        nuevoRes1.next();
                        idProductoNuevo = nuevoRes1.getInt(1);
                        nuevoRes1.close();

                        etapaProduto.put(entregableFk + "_" + nu_produto, idProductoNuevo);

                        mapeos.put("SIGET23_PRODUTO_" + idProductoViejo, idProductoNuevo);
                    } else {
                        idProductoNuevo = etapaProduto.get(entregableFk + "_" + nu_produto);
                    }

                    for (int i = 0; i < mesStr.length; i++) {
                        mes = i + 1;
                        plan = viejoRes1.getDouble("vl_" + mesStr[i]);
                        real = viejoRes1.getDouble("vl_" + mesStr[i] + "_real");

                        if ((plan != null && plan > 0) || (real != null && real > 0)) {
                            sql = "INSERT INTO prod_mes"
                                    + "(prodmes_prod_fk, prodmes_mes,"
                                    + " prodmes_anio, prodmes_plan, prodmes_real, "
                                    + " prodmes_acu_plan, prodmes_acu_real)"
                                    + "VALUES"
                                    + "(" + idProductoNuevo + "," + mes + ","
                                    + anio + "," + plan + "," + real
                                    + ", 0, 0);";
                            log(Tipo.SENTENCIASQL, sql);
                            nuevoSta1.execute(sql);
                        }
                    }
                }
            }

            // Recorrer los productos que son acumulados y pasarlos a no acumulados ------------
            log(Tipo.MENSAJE, "Actualizando productos acumulados.");
            sql = "SELECT prod_pk FROM productos WHERE prod_acumulado = 1";
            ResultSet nuevoRes = nuevoSta1.executeQuery(sql);

            boolean primero;
            BigDecimal planAnterior;
            BigDecimal realAnterior;
            BigDecimal planMes;
            BigDecimal realMes;
            BigDecimal planNvo;
            BigDecimal realNvo;

            Statement nuevoSta2 = nuevoCon1.createStatement();
            Statement nuevoSta3 = nuevoCon1.createStatement();

            while (nuevoRes.next()) {
                int prodId = nuevoRes.getInt(1);
                sql = "SELECT * FROM prod_mes WHERE prodmes_prod_fk = " + prodId
                        + " ORDER BY prodmes_prod_fk ASC, prodmes_anio ASC, prodmes_mes ASC";
                log(Tipo.MENSAJE, "Obteniendo los meses para el producto: " + prodId);
                ResultSet nuevoRes2 = nuevoSta2.executeQuery(sql);
                primero = true;
                planAnterior = new BigDecimal(0);
                realAnterior = new BigDecimal(0);

                while (nuevoRes2.next()) {
                    Integer prodmesPk = nuevoRes2.getInt("prodmes_pk");
                    planMes = nuevoRes2.getBigDecimal("prodmes_plan");
                    planMes = planMes == null ? new BigDecimal(0) : planMes;
                    realMes = nuevoRes2.getBigDecimal("prodmes_real");
                    realMes = realMes == null ? new BigDecimal(0) : realMes;

                    if (!primero) {
                        planNvo = planMes.subtract(planAnterior);
                        realNvo = realMes.subtract(realAnterior);

                        sql = "UPDATE prod_mes"
                                + " SET prodmes_plan = " + planNvo
                                + ", prodmes_real = " + realNvo
                                + ", prodmes_acu_plan = " + planMes
                                + ", prodmes_acu_real = " + realMes
                                + " WHERE prodmes_pk = " + prodmesPk;
                        nuevoSta3.executeUpdate(sql);

                    } else {
                        primero = false;
                        sql = "UPDATE prod_mes"
                                + " SET prodmes_acu_plan = " + planMes
                                + ", prodmes_acu_real = " + realMes
                                + " WHERE prodmes_pk = " + prodmesPk;
                        nuevoSta3.executeUpdate(sql);
                    }
                    planAnterior = planMes;
                    realAnterior = realMes;
                }
                nuevoRes2.close();
            }
            nuevoRes.close();

            // Actualizo los no acumulados -------
            log(Tipo.MENSAJE, "Actualizando productos no acumulados.");
            sql = "SELECT prod_pk FROM productos WHERE prod_acumulado = 0 OR prod_acumulado IS NULL";
            Statement nuevoSta4 = nuevoCon1.createStatement();
            ResultSet nuevoRes3 = nuevoSta4.executeQuery(sql);

            BigDecimal planAcu;
            BigDecimal realAcu;

            Statement nuevoSta5 = nuevoCon1.createStatement();
            Statement nuevoSta6 = nuevoCon1.createStatement();

            while (nuevoRes3.next()) {
                int prodId = nuevoRes3.getInt(1);
                sql = "SELECT * FROM prod_mes WHERE prodmes_prod_fk = " + prodId
                        + " ORDER BY prodmes_prod_fk ASC, prodmes_anio ASC, prodmes_mes ASC";
                log(Tipo.MENSAJE, "Obteniendo los meses para el producto: " + prodId);
                ResultSet nuevoRes2 = nuevoSta5.executeQuery(sql);
                primero = true;
                planAcu = new BigDecimal(0);
                realAcu = new BigDecimal(0);

                while (nuevoRes2.next()) {
                    Integer prodmesPk = nuevoRes2.getInt("prodmes_pk");
                    planMes = nuevoRes2.getBigDecimal("prodmes_plan");
                    planMes = planMes == null ? new BigDecimal(0) : planMes;
                    realMes = nuevoRes2.getBigDecimal("prodmes_real");
                    realMes = realMes == null ? new BigDecimal(0) : realMes;

                    planAcu = planAcu.add(planMes);
                    realAcu = realAcu.add(realMes);

                    sql = "UPDATE prod_mes"
                            + " SET prodmes_acu_plan = " + planAcu
                            + ", prodmes_acu_real = " + realAcu
                            + " WHERE prodmes_pk = " + prodmesPk;
                    nuevoSta6.executeUpdate(sql);
                }
            }

        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private void log(Tipo t, String msg) {
        System.out.println("[" + t + "] " + msg);
        if (this.iCom != null && t != null) {
            iCom.mostrarMensaje(t, msg);
        }
    }

    private int crearUsuarioDummy(Connection nuevoCon1) throws MigracionException {
        try {
            Statement nuevoSta1 = nuevoCon1.createStatement();
            String sql = "INSERT INTO ss_usuario (usu_cod, usu_correo_electronico, usu_intentos_fallidos, "
                    + "usu_oficina_por_defecto, usu_password, usu_primer_nombre, usu_telefono, "
                    + "usu_version, usu_vigente, usu_fecha_password, usu_nro_doc, usu_origen, "
                    + "usu_primer_apellido, usu_user_code) VALUES ('migracion', '', 0, 0, '', 'migracion','', 1, 0, "
                    + UtilesVarios.convertDateToString(UtilesVarios.siNulo(null)) + ", '','MIGRACION', '', 0)";
            log(Tipo.SENTENCIASQL, sql);
            nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
            nuevoRes1.next();
            int idUsuarioNull = nuevoRes1.getInt(1);
            nuevoRes1.close();
            return idUsuarioNull;
        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    private void crearUsuariosNecesarios(Connection nuevoCon1, Integer idOrganismo) throws MigracionException {

        try {
            Statement nuevoSta1 = nuevoCon1.createStatement();

            String sqlAdmin = "SELECT usu_id FROM ss_usuario where usu_correo_electronico='admin'";
            ResultSet nuevoResAdmin = nuevoSta1.executeQuery(sqlAdmin);
            int idUsuarioAdmin = 0;
            if (!nuevoResAdmin.next()) {
                sqlAdmin = "INSERT INTO ss_usuario (usu_cod, usu_correo_electronico, usu_intentos_fallidos, "
                        + "usu_password, usu_primer_nombre, usu_primer_apellido, usu_telefono, "
                        + "usu_version, usu_vigente, usu_fecha_password, usu_nro_doc, usu_origen, "
                        + "usu_user_code)"
                        + "VALUES ('admin', 'admin', 0, '" + UtilesVarios.md5("admin") + "',"
                        + "'Usuario','Admin','',1 , 1,"
                        + UtilesVarios.convertDateToString(new Date(new java.util.Date().getTime())) + ", '','MIGRACION', 0)";
                log(Tipo.SENTENCIASQL, sqlAdmin);
                nuevoSta1.executeUpdate(sqlAdmin, Statement.RETURN_GENERATED_KEYS);
                ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
                nuevoRes1.next();
                idUsuarioAdmin = nuevoRes1.getInt(1);
                nuevoRes1.close();
            } else {
                idUsuarioAdmin = nuevoResAdmin.getInt(1);
            }

            sqlAdmin = "SELECT usu_ofi_roles_usuario FROM ss_usu_ofi_roles where usu_ofi_roles_usuario=" + idUsuarioAdmin;
            nuevoResAdmin = nuevoSta1.executeQuery(sqlAdmin);
            if (!nuevoResAdmin.next()) {
                sqlAdmin = "INSERT INTO ss_usu_ofi_roles (usu_ofi_roles_origen, usu_ofi_roles_user_code, "
                        + "usu_ofi_roles_rol, usu_ofi_roles_usuario, usu_ofi_roles_activo) "
                        + "VALUES ('MIGRACION', " + idUsuarioAdmin + ", "
                        + "(SELECT rol_id  FROM ss_rol WHERE rol_cod='ADMINISTRADOR'), "
                        + idUsuarioAdmin + ", 1)";

                log(Tipo.SENTENCIASQL, sqlAdmin);
                nuevoSta1.executeUpdate(sqlAdmin);

            }

            List<Map> listUser = new ArrayList();
            Map<String, Object> dataUser;

            String sql = "SELECT usu_id FROM ss_usuario where usu_correo_electronico='pmot'";
            ResultSet nuevoRes = nuevoSta1.executeQuery(sql);
            if (!nuevoRes.next()) {
                dataUser = new HashMap<String, Object>();
                dataUser.put("mail", "pmot");
                dataUser.put("pass", UtilesVarios.md5("pmot"));
                dataUser.put("nombreUsuario", "Usuario");
                dataUser.put("apellidoUsuario", "PMOT");
                dataUser.put("rolCod", "PMOT");
                listUser.add(dataUser);
            } else {
                int idUsuarioNuevo = nuevoRes.getInt(1);
                sql = "INSERT INTO ss_usu_ofi_roles (usu_ofi_roles_origen, usu_ofi_roles_user_code, "
                        + "usu_ofi_roles_oficina, usu_ofi_roles_rol, usu_ofi_roles_usuario, usu_ofi_roles_activo) "
                        + "VALUES ('MIGRACION', " + idUsuarioNuevo + ", " + idOrganismo + ", "
                        + "(SELECT rol_id  FROM ss_rol WHERE rol_cod='PMOT'), "
                        + idUsuarioNuevo + ", 1)";

                log(Tipo.SENTENCIASQL, sql);
                nuevoSta1.executeUpdate(sql);
            }

            sql = "SELECT usu_id FROM ss_usuario where usu_correo_electronico='pmof'";
            nuevoRes = nuevoSta1.executeQuery(sql);
            if (!nuevoRes.next()) {
                dataUser = new HashMap<String, Object>();
                dataUser.put("mail", "pmof");
                dataUser.put("pass", UtilesVarios.md5("pmof"));
                dataUser.put("nombreUsuario", "Usuario");
                dataUser.put("apellidoUsuario", "PMOF");
                dataUser.put("rolCod", "PMOF");
                listUser.add(dataUser);
            } else {
                int idUsuarioNuevo = nuevoRes.getInt(1);
                sql = "INSERT INTO ss_usu_ofi_roles (usu_ofi_roles_origen, usu_ofi_roles_user_code, "
                        + "usu_ofi_roles_oficina, usu_ofi_roles_rol, usu_ofi_roles_usuario, usu_ofi_roles_activo) "
                        + "VALUES ('MIGRACION', " + idUsuarioNuevo + ", " + idOrganismo + ", "
                        + "(SELECT rol_id  FROM ss_rol WHERE rol_cod='PMOF'), "
                        + idUsuarioNuevo + ", 1)";

                log(Tipo.SENTENCIASQL, sql);
                nuevoSta1.executeUpdate(sql);
            }

            sql = "SELECT usu_id FROM ss_usuario where usu_correo_electronico='director'";
            nuevoRes = nuevoSta1.executeQuery(sql);
            if (!nuevoRes.next()) {
                dataUser = new HashMap<String, Object>();
                dataUser.put("mail", "director");
                dataUser.put("pass", UtilesVarios.md5("director"));
                dataUser.put("nombreUsuario", "Usuario");
                dataUser.put("apellidoUsuario", "Director");
                dataUser.put("rolCod", "DIR");
                listUser.add(dataUser);
            } else {
                int idUsuarioNuevo = nuevoRes.getInt(1);
                sql = "INSERT INTO ss_usu_ofi_roles (usu_ofi_roles_origen, usu_ofi_roles_user_code, "
                        + "usu_ofi_roles_oficina, usu_ofi_roles_rol, usu_ofi_roles_usuario, usu_ofi_roles_activo) "
                        + "VALUES ('MIGRACION', " + idUsuarioNuevo + ", " + idOrganismo + ", "
                        + "(SELECT rol_id  FROM ss_rol WHERE rol_cod='DIR'), "
                        + idUsuarioNuevo + ", 1)";

                log(Tipo.SENTENCIASQL, sql);
                nuevoSta1.executeUpdate(sql);
            }

            nuevoRes.close();

            java.util.Date date = new java.util.Date();

            for (Map mapUser : listUser) {
                sql = "INSERT INTO ss_usuario (usu_cod, usu_correo_electronico, usu_intentos_fallidos, "
                        + "usu_oficina_por_defecto, usu_password, usu_primer_nombre, usu_primer_apellido, usu_telefono, "
                        + "usu_version, usu_vigente, usu_fecha_password, usu_nro_doc, usu_origen, "
                        + "usu_user_code)"
                        + "VALUES ('" + mapUser.get("mail") + "', '" + mapUser.get("mail")
                        + "', 0, " + idOrganismo + ", '" + mapUser.get("pass") + "',"
                        + "'" + mapUser.get("nombreUsuario") + "','" + mapUser.get("apellidoUsuario")
                        + "','',1 , 1,"
                        + UtilesVarios.convertDateToString(new Date(date.getTime())) + ", '','MIGRACION', 0)";
                log(Tipo.SENTENCIASQL, sql);
                nuevoSta1.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet nuevoRes1 = nuevoSta1.getGeneratedKeys();
                nuevoRes1.next();
                int idUsuarioNuevo = nuevoRes1.getInt(1);
                nuevoRes1.close();

                sql = "INSERT INTO ss_usu_ofi_roles (usu_ofi_roles_origen, usu_ofi_roles_user_code, "
                        + "usu_ofi_roles_oficina, usu_ofi_roles_rol, usu_ofi_roles_usuario, usu_ofi_roles_activo) "
                        + "VALUES ('MIGRACION', " + idUsuarioNuevo + ", " + idOrganismo + ", "
                        + "(SELECT rol_id  FROM ss_rol WHERE rol_cod='" + mapUser.get("rolCod") + "'), "
                        + idUsuarioNuevo + ", 1)";

                log(Tipo.SENTENCIASQL, sql);
                nuevoSta1.executeUpdate(sql);
            }

        } catch (SQLException sqlEx) {
            throw new MigracionException("Se ha producido un error en la migración", sqlEx);
        }
    }

    public static void main(String[] args) {

        Long entInicio = 93599999l;
        Long entFin = 1408654598750l;

        GregorianCalendar c1 = new GregorianCalendar();
        c1.setTimeInMillis(entInicio);
        System.out.println("INICIO " + c1.getTime());
        c1.setTimeInMillis(entFin);
        System.out.println("FIN " + c1.getTime());

        System.out.println(entFin - entInicio);

        Integer entDuracion = entInicio != null && entInicio > 0 && entFin != null && entFin > 0 ? Math.round((entFin * 1.0f - entInicio) / 86400000.0f) : 0;
        System.out.println("entDuracion " + entDuracion);
        //System.out.println((entFin * 1.0d - entInicio) / 86400000.0d);

        GregorianCalendar c = new GregorianCalendar();
        //20/06/2014
        c.set(Calendar.DATE, 10);
        c.set(Calendar.MONTH, 4);
        c.set(Calendar.YEAR, 2014);
        c.set(Calendar.HOUR, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.AM_PM, 0);
        c.set(Calendar.MILLISECOND, 0);
        System.out.println(c.getTimeInMillis());

    }
}
