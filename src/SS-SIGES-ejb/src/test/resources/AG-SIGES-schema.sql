-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: siges
-- ------------------------------------------------------
-- Server version	5.6.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary table structure for view `REVINFO`
--

DROP TABLE IF EXISTS `REVINFO`;
/*!50001 DROP VIEW IF EXISTS `REVINFO`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `REVINFO` AS SELECT 
 1 AS `REV`,
 1 AS `REVTSTMP`,
 1 AS `version`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `REVINFO2`
--

DROP TABLE IF EXISTS `REVINFO2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REVINFO2` (
  `REV` tinyint(4) NOT NULL,
  `REVTSTMP` tinyint(4) NOT NULL,
  `version` tinyint(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `adquisicion`
--

DROP TABLE IF EXISTS `adquisicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adquisicion` (
  `adq_pk` int(11) NOT NULL AUTO_INCREMENT,
  `adq_pre_fk` int(11) NOT NULL,
  `adq_nombre` varchar(300) NOT NULL,
  `adq_prov_orga_fk` int(11) DEFAULT NULL,
  `adq_fuente_fk` int(11) DEFAULT NULL,
  `adq_moneda_fk` int(11) DEFAULT NULL,
  `adq_proc_compra` varchar(20) DEFAULT NULL,
  `adq_proc_compra_grp` varchar(20) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `adq_componente_producto_fk` int(11) DEFAULT NULL,
  `adq_procedimiento_compra_fk` int(11) DEFAULT NULL,
  `adq_compartida` bit(1) NOT NULL DEFAULT b'0',
  `adq_compartida_usuario_fk` int(11) DEFAULT NULL,
  `adq_tipo_registro` varchar(40) DEFAULT NULL,
  `adq_arrastre` bit(1) DEFAULT b'0',
  `adq_fecha_estimada_inicio_compra` date DEFAULT NULL,
  `adq_fecha_esperada_inicio_ejecucion` date DEFAULT NULL,
  `adq_tipo_adquisicion_fk` int(11) DEFAULT NULL,
  `adq_id_adquisicion` varchar(255) DEFAULT NULL,
  `adq_centro_costo_fk` int(11) DEFAULT NULL,
  `adq_causal_compra_fk` int(11) DEFAULT NULL,
  `adq_proc_compra_bk` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`adq_pk`),
  KEY `adq_pre_fk_idx` (`adq_pre_fk`),
  KEY `adq_fuente_idx` (`adq_fuente_fk`),
  KEY `adq_moneda_idx` (`adq_moneda_fk`),
  KEY `adq_prov_orga_idx` (`adq_prov_orga_fk`),
  KEY `adq_comp_prod_fk_idx` (`adq_componente_producto_fk`),
  KEY `adq_proc_comp_fk_idx` (`adq_procedimiento_compra_fk`),
  KEY `adquisicion_adq_compartida_usuario_fk_IDX` (`adq_compartida_usuario_fk`),
  KEY `fk_adquisicion_tipo_adquisicion_idx` (`adq_tipo_adquisicion_fk`),
  KEY `fk_adquisicion_centro_costo_idx` (`adq_centro_costo_fk`),
  KEY `fk_adquisicion_causal_idx` (`adq_causal_compra_fk`),
  CONSTRAINT `adq_comp_prod_fk` FOREIGN KEY (`adq_componente_producto_fk`) REFERENCES `componente_producto` (`com_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `adq_fuente` FOREIGN KEY (`adq_fuente_fk`) REFERENCES `fuente_financiamiento` (`fue_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `adq_moneda` FOREIGN KEY (`adq_moneda_fk`) REFERENCES `moneda` (`mon_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `adq_pre_fk` FOREIGN KEY (`adq_pre_fk`) REFERENCES `presupuesto` (`pre_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `adq_proc_comp_fk` FOREIGN KEY (`adq_procedimiento_compra_fk`) REFERENCES `procedimiento_compra` (`proc_comp_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `adq_prov_orga` FOREIGN KEY (`adq_prov_orga_fk`) REFERENCES `organi_int_prove` (`orga_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `adquisicion_ss_usuario_FK` FOREIGN KEY (`adq_compartida_usuario_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_adquisicion_causal` FOREIGN KEY (`adq_causal_compra_fk`) REFERENCES `causales_compra` (`cau_com_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_adquisicion_centro_costo` FOREIGN KEY (`adq_centro_costo_fk`) REFERENCES `centros_costo` (`cen_cos_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_adquisicion_tipo_adquisicion` FOREIGN KEY (`adq_tipo_adquisicion_fk`) REFERENCES `tipos_adquisicion` (`tip_adq_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3891 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ambito`
--

DROP TABLE IF EXISTS `ambito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ambito` (
  `amb_pk` int(11) NOT NULL AUTO_INCREMENT,
  `amb_nombre` varchar(100) NOT NULL,
  `amb_org_fk` int(11) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`amb_pk`),
  KEY `FK_n5tl58dqv18cs790jbmejxom2` (`amb_org_fk`),
  CONSTRAINT `FK_n5tl58dqv18cs790jbmejxom2` FOREIGN KEY (`amb_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `area_conocimiento`
--

DROP TABLE IF EXISTS `area_conocimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area_conocimiento` (
  `con_pk` int(11) NOT NULL AUTO_INCREMENT,
  `con_nombre` varchar(150) NOT NULL,
  `con_org_fk` int(11) NOT NULL,
  `con_padre_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`con_pk`),
  KEY `con_org_fk_idx` (`con_org_fk`),
  KEY `con_padre_fk_idx` (`con_padre_fk`),
  CONSTRAINT `con_org_fk` FOREIGN KEY (`con_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `con_padre_fk` FOREIGN KEY (`con_padre_fk`) REFERENCES `area_conocimiento` (`con_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1409 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `area_organi_int_prove`
--

DROP TABLE IF EXISTS `area_organi_int_prove`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area_organi_int_prove` (
  `areaorgintprov_pk` int(11) NOT NULL AUTO_INCREMENT,
  `areaorgintprov_orga_fk` int(11) NOT NULL,
  `areaorgintprov_org_fk` int(11) NOT NULL,
  `areaorgintprov_nombre` varchar(40) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`areaorgintprov_pk`),
  KEY `fk_AREA_ORGANI_INT_PROVE_ORGANISMOS1_idx` (`areaorgintprov_org_fk`),
  KEY `fk_AREA_ORGANI_INT_PROVE_ORGANI_INT_PROVE1_idx` (`areaorgintprov_orga_fk`),
  CONSTRAINT `fk_AREA_ORGANI_INT_PROVE_ORGANISMOS1` FOREIGN KEY (`areaorgintprov_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_AREA_ORGANI_INT_PROVE_ORGANI_INT_PROVE1` FOREIGN KEY (`areaorgintprov_orga_fk`) REFERENCES `organi_int_prove` (`orga_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `areas`
--

DROP TABLE IF EXISTS `areas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `areas` (
  `area_pk` int(11) NOT NULL AUTO_INCREMENT,
  `area_org_fk` int(11) NOT NULL,
  `area_padre` int(11) DEFAULT NULL,
  `area_nombre` varchar(250) DEFAULT NULL,
  `area_abreviacion` varchar(45) DEFAULT NULL,
  `area_director` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `area_activo` tinyint(4) DEFAULT '1',
  `area_habilitada` tinyint(3) NOT NULL DEFAULT '1',
  PRIMARY KEY (`area_pk`),
  KEY `fk_AREAS_ORGANISMOS1_idx` (`area_org_fk`),
  KEY `fk_AREAS_AREAS1_idx` (`area_padre`),
  KEY `area_activo_idx` (`area_activo`),
  KEY `FK_j151q3d1wiqgx10w9n92hwx5j` (`area_director`),
  CONSTRAINT `FK_j151q3d1wiqgx10w9n92hwx5j` FOREIGN KEY (`area_director`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_AREAS_AREAS1` FOREIGN KEY (`area_padre`) REFERENCES `areas` (`area_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_AREAS_ORGANISMOS1` FOREIGN KEY (`area_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=254 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `areas_tags`
--

DROP TABLE IF EXISTS `areas_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `areas_tags` (
  `arastag_pk` int(11) NOT NULL AUTO_INCREMENT,
  `areatag_org_fk` int(11) NOT NULL,
  `areatag_padre_fk` int(11) DEFAULT NULL,
  `areatag_tematica` varchar(45) DEFAULT NULL,
  `areatag_excluyente` tinyint(1) DEFAULT NULL,
  `areatag_nombre` varchar(45) DEFAULT NULL,
  `areatag_categoria` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`arastag_pk`),
  KEY `fk_AREAS_TAGS_ORGANISMOS1_idx` (`areatag_org_fk`),
  KEY `fk_AREAS_TAGS_AREAS_TAGS1_idx` (`areatag_padre_fk`),
  CONSTRAINT `fk_AREAS_TAGS_AREAS_TAGS1` FOREIGN KEY (`areatag_padre_fk`) REFERENCES `areas_tags` (`arastag_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_AREAS_TAGS_ORGANISMOS1` FOREIGN KEY (`areatag_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=951 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_doc_file`
--

DROP TABLE IF EXISTS `aud_doc_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_doc_file` (
  `docfile_pk` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(3) DEFAULT NULL,
  `docfile_nombre` varchar(255) DEFAULT NULL,
  `docfile_path` varchar(255) DEFAULT NULL,
  `docfile_ult_mod` datetime(6) DEFAULT NULL,
  `docfile_ult_origen` varchar(255) DEFAULT NULL,
  `docfile_ult_usuario` varchar(255) DEFAULT NULL,
  `docfile_version` int(11) DEFAULT NULL,
  `docfile_doc_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`docfile_pk`,`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_pge_configuraciones`
--

DROP TABLE IF EXISTS `aud_pge_configuraciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_pge_configuraciones` (
  `cnf_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `cnf_clave` varchar(255) DEFAULT NULL,
  `cnf_crea_fecha` datetime DEFAULT NULL,
  `cnf_crea_origen` int(11) DEFAULT NULL,
  `cnf_crea_usu` varchar(255) DEFAULT NULL,
  `cnf_ultmod_fecha` datetime DEFAULT NULL,
  `cnf_ultmod_origen` int(11) DEFAULT NULL,
  `cnf_ultmod_usu` varchar(255) DEFAULT NULL,
  `cnf_valor` varchar(255) DEFAULT NULL,
  `cnf_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`cnf_id`,`REV`),
  KEY `FK69423495DF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_programas`
--

DROP TABLE IF EXISTS `aud_programas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_programas` (
  `prog_pk` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `prog_org_fk` int(11) DEFAULT NULL,
  `prog_area_fk` int(11) DEFAULT NULL,
  `prog_sol_aceptacion` tinyint(1) DEFAULT NULL,
  `prog_nombre` varchar(45) DEFAULT NULL,
  `prog_objetivo` varchar(256) DEFAULT NULL,
  `prog_obj_publico` varchar(256) DEFAULT NULL,
  `prog_grp` int(11) DEFAULT NULL,
  `prog_semaforo_verde` int(11) DEFAULT NULL,
  `prog_semaforo_amarillo` int(11) DEFAULT NULL,
  `prog_version` int(11) DEFAULT NULL,
  `prog_ult_usuario` varchar(45) DEFAULT NULL,
  `prog_ult_mod` varchar(45) DEFAULT NULL,
  `prog_ult_origen` date DEFAULT NULL,
  PRIMARY KEY (`prog_pk`,`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_programas_proyectos`
--

DROP TABLE IF EXISTS `aud_programas_proyectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_programas_proyectos` (
  `id` varchar(13) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `areaNombre` varchar(255) DEFAULT NULL,
  `areaPk` int(11) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `estadoNombre` varchar(255) DEFAULT NULL,
  `estadoPendiente` int(11) DEFAULT NULL,
  `fechaCrea` date DEFAULT NULL,
  `fichaFk` int(11) DEFAULT NULL,
  `gerente` int(11) DEFAULT NULL,
  `gerentePrimerApellido` varchar(255) DEFAULT NULL,
  `gerentePrimerNombre` varchar(255) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `organismo` int(11) DEFAULT NULL,
  `pmoFederada` int(11) DEFAULT NULL,
  `tipoFicha` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK36A8E0B6DF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_ayuda`
--

DROP TABLE IF EXISTS `aud_ss_ayuda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_ayuda` (
  `ayu_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `ayu_codigo` varchar(45) DEFAULT NULL,
  `ayu_texto` longtext,
  `ayu_ult_mod_fecha` datetime DEFAULT NULL,
  `ayu_ult_mod_origen` varchar(45) DEFAULT NULL,
  `ayu_ult_mod_usuario` varchar(45) DEFAULT NULL,
  `ayu_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`ayu_id`,`REV`),
  KEY `FK7E2A928ADF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_categoper`
--

DROP TABLE IF EXISTS `aud_ss_categoper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_categoper` (
  `cat_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `cat_descripcion` longtext,
  `cat_nombre` varchar(255) DEFAULT NULL,
  `cat_origen` varchar(255) DEFAULT NULL,
  `cat_user_code` int(11) DEFAULT NULL,
  `cat_version` int(11) DEFAULT NULL,
  `cat_vigente` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`cat_id`,`REV`),
  KEY `FKA2EE3756DF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_configuraciones`
--

DROP TABLE IF EXISTS `aud_ss_configuraciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_configuraciones` (
  `id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `cnf_codigo` varchar(255) DEFAULT NULL,
  `cnf_descripcion` varchar(255) DEFAULT NULL,
  `cnf_html` tinyint(1) DEFAULT NULL,
  `cnf_protegido` tinyint(1) DEFAULT NULL,
  `cnf_ult_mod` datetime DEFAULT NULL,
  `cnf_ult_origen` varchar(255) DEFAULT NULL,
  `cnf_ult_usuario` varchar(255) DEFAULT NULL,
  `cnf_valor` longtext,
  `cnf_version` int(11) DEFAULT NULL,
  `cnf_org_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK1E264BA5DF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_departamentos`
--

DROP TABLE IF EXISTS `aud_ss_departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_departamentos` (
  `depto_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `depto_codigo` varchar(255) DEFAULT NULL,
  `depto_nombre` varchar(255) DEFAULT NULL,
  `depto_ult_mod` datetime DEFAULT NULL,
  `err_ult_origen` varchar(255) DEFAULT NULL,
  `depto_ult_usuario` varchar(255) DEFAULT NULL,
  `depto_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`depto_id`,`REV`),
  KEY `FKCA442AFFDF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_domicilios`
--

DROP TABLE IF EXISTS `aud_ss_domicilios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_domicilios` (
  `dom_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `dom_apto` varchar(50) DEFAULT NULL,
  `dom_letra` varchar(255) DEFAULT NULL,
  `dom_calle` varchar(150) DEFAULT NULL,
  `dom_codigo_postal` varchar(5) DEFAULT NULL,
  `dom_depto_alt` varchar(255) DEFAULT NULL,
  `dom_descripcion` longtext,
  `dom_inmueble_nombre` varchar(100) DEFAULT NULL,
  `dom_kilometro` varchar(9) DEFAULT NULL,
  `dom_manzana` varchar(5) DEFAULT NULL,
  `dom_numero_puerta` varchar(5) DEFAULT NULL,
  `dom_oficina` varchar(255) DEFAULT NULL,
  `dom_ruta` varchar(5) DEFAULT NULL,
  `dom_solar` varchar(5) DEFAULT NULL,
  `dom_ult_mod` datetime DEFAULT NULL,
  `dom_ult_origen` varchar(255) DEFAULT NULL,
  `dom_ult_usuario` varchar(255) DEFAULT NULL,
  `dom_version` int(11) DEFAULT NULL,
  `dom_depto_id` int(11) DEFAULT NULL,
  `dom_loc_id` int(11) DEFAULT NULL,
  `dom_pai_id` int(11) DEFAULT NULL,
  `dom_par_id` smallint(6) DEFAULT NULL,
  `dom_tec_id` int(11) DEFAULT NULL,
  `dom_tvi_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`dom_id`,`REV`),
  KEY `FKC027379EDF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_errores`
--

DROP TABLE IF EXISTS `aud_ss_errores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_errores` (
  `id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `err_codigo` varchar(255) DEFAULT NULL,
  `err_descripcion` varchar(255) DEFAULT NULL,
  `err_ult_mod` datetime DEFAULT NULL,
  `err_ult_origen` varchar(255) DEFAULT NULL,
  `err_ult_usuario` varchar(255) DEFAULT NULL,
  `err_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK65521EC6DF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_localidades`
--

DROP TABLE IF EXISTS `aud_ss_localidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_localidades` (
  `loc_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `loc_codigo` varchar(255) DEFAULT NULL,
  `loc_nombre` varchar(255) DEFAULT NULL,
  `loc_ult_mod` datetime DEFAULT NULL,
  `loc_ult_origen` varchar(255) DEFAULT NULL,
  `loc_ult_usuario` varchar(255) DEFAULT NULL,
  `loc_version` int(11) DEFAULT NULL,
  `loc_depto_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`loc_id`,`REV`),
  KEY `FKBDD320E7DF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_noticias`
--

DROP TABLE IF EXISTS `aud_ss_noticias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_noticias` (
  `not_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `not_ampliar` varchar(255) DEFAULT NULL,
  `not_codigo` varchar(45) DEFAULT NULL,
  `not_contenido` longtext,
  `not_desde` datetime DEFAULT NULL,
  `not_hasta` datetime DEFAULT NULL,
  `not_imagen` varchar(255) DEFAULT NULL,
  `not_titulo` varchar(255) DEFAULT NULL,
  `not_ult_mod_fecha` datetime DEFAULT NULL,
  `not_ult_mod_origen` varchar(45) DEFAULT NULL,
  `not_ult_mod_usuario` varchar(45) DEFAULT NULL,
  `not_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`not_id`,`REV`),
  KEY `FK5037FBFEDF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_oficina`
--

DROP TABLE IF EXISTS `aud_ss_oficina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_oficina` (
  `ofi_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `ofi_fecha_creacion` datetime DEFAULT NULL,
  `ofi_nombre` varchar(255) DEFAULT NULL,
  `ofi_origen` varchar(255) DEFAULT NULL,
  `ofi_usuario` int(11) DEFAULT NULL,
  `ofi_version` int(11) DEFAULT NULL,
  `ofi_activo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ofi_id`,`REV`),
  KEY `FK6151DF1BDF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_operacion`
--

DROP TABLE IF EXISTS `aud_ss_operacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_operacion` (
  `ope_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `ope_codigo` varchar(255) DEFAULT NULL,
  `ope_descripcion` longtext,
  `ope_nombre` varchar(255) DEFAULT NULL,
  `ope_origen` varchar(255) DEFAULT NULL,
  `ope_tipocampo` varchar(255) DEFAULT NULL,
  `ope_user_code` int(11) DEFAULT NULL,
  `ope_version` int(11) DEFAULT NULL,
  `ope_vigente` tinyint(1) DEFAULT NULL,
  `ope_categoria_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ope_id`,`REV`),
  KEY `FKA8AC7928DF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_paises`
--

DROP TABLE IF EXISTS `aud_ss_paises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_paises` (
  `pai_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `pai_codigo2` varchar(255) DEFAULT NULL,
  `pai_codigo3` varchar(255) DEFAULT NULL,
  `pai_comun` tinyint(1) DEFAULT NULL,
  `pai_habilitado` tinyint(1) DEFAULT NULL,
  `pai_nombre` varchar(255) DEFAULT NULL,
  `pai_ult_mod` datetime DEFAULT NULL,
  `pai_ult_origen` varchar(255) DEFAULT NULL,
  `pai_ult_usuario` varchar(255) DEFAULT NULL,
  `pai_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`pai_id`,`REV`),
  KEY `FK5F6900B9DF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_paridades`
--

DROP TABLE IF EXISTS `aud_ss_paridades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_paridades` (
  `par_id` smallint(6) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `par_codigo` varchar(9) DEFAULT NULL,
  `par_descripcion` varchar(45) DEFAULT NULL,
  `par_ult_mod_fecha` datetime DEFAULT NULL,
  `par_ult_mod_origen` varchar(45) DEFAULT NULL,
  `par_ult_mod_usuario` varchar(45) DEFAULT NULL,
  `par_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`par_id`,`REV`),
  KEY `FKC73DF59DDF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_personas`
--

DROP TABLE IF EXISTS `aud_ss_personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_personas` (
  `per_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `per_fec_nac` datetime DEFAULT NULL,
  `per_nro_doc` varchar(45) DEFAULT NULL,
  `per_primer_apellido` varchar(100) DEFAULT NULL,
  `per_primer_nombre` varchar(100) DEFAULT NULL,
  `per_segundo_apellido` varchar(100) DEFAULT NULL,
  `per_segundo_nombre` varchar(100) DEFAULT NULL,
  `per_ult_mod_fecha` datetime DEFAULT NULL,
  `per_ult_mod_origen` varchar(45) DEFAULT NULL,
  `per_ult_mod_usuario` varchar(45) DEFAULT NULL,
  `per_version` int(11) DEFAULT NULL,
  `per_pais_doc` int(11) DEFAULT NULL,
  `per_tipo_doc` int(11) DEFAULT NULL,
  PRIMARY KEY (`per_id`,`REV`),
  KEY `FKC24C637DF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_rel_rol_operacion`
--

DROP TABLE IF EXISTS `aud_ss_rel_rol_operacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_rel_rol_operacion` (
  `rel_rol_operacion_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `rel_rol_operacion_editable` tinyint(1) DEFAULT NULL,
  `rel_rol_operacion_origen` varchar(255) DEFAULT NULL,
  `rel_rol_operacion_user_code` int(11) DEFAULT NULL,
  `rel_rol_operacion_visible` tinyint(1) DEFAULT NULL,
  `rel_rol_operacion_operacion_id` int(11) DEFAULT NULL,
  `rel_rol_operacion_rol_id` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`rel_rol_operacion_id`,`REV`),
  KEY `FKCE17192DF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_rol`
--

DROP TABLE IF EXISTS `aud_ss_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_rol` (
  `rol_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `rol_cod` varchar(255) DEFAULT NULL,
  `rol_descripcion` longtext,
  `rol_nombre` varchar(255) DEFAULT NULL,
  `rol_origen` varchar(255) DEFAULT NULL,
  `rol_user_code` int(11) DEFAULT NULL,
  `rol_version` int(11) DEFAULT NULL,
  `rol_vigente` tinyint(1) DEFAULT NULL,
  `rol_tipo_usuario` tinyint(1) DEFAULT NULL,
  `rol_label` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`rol_id`,`REV`),
  KEY `FK533EE3DFDF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_telefonos`
--

DROP TABLE IF EXISTS `aud_ss_telefonos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_telefonos` (
  `tel_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `tel_numero` varchar(25) DEFAULT NULL,
  `tel_observaciones` varchar(255) DEFAULT NULL,
  `tel_prefijo` varchar(10) DEFAULT NULL,
  `tel_ult_mod` datetime DEFAULT NULL,
  `tel_ult_origen` varchar(45) DEFAULT NULL,
  `tel_ult_usuario` varchar(45) DEFAULT NULL,
  `tel_version` int(11) DEFAULT NULL,
  `tel_tiptel_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`tel_id`,`REV`),
  KEY `FK73BE520FDF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_tipos_documento_persona`
--

DROP TABLE IF EXISTS `aud_ss_tipos_documento_persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_tipos_documento_persona` (
  `tipdocper_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `tipdocper_codigo` varchar(255) DEFAULT NULL,
  `tipdocper_descripcion` varchar(255) DEFAULT NULL,
  `tipdocper_habilitado` tinyint(1) DEFAULT NULL,
  `tipdocper_ult_mod` datetime DEFAULT NULL,
  `tipdocper_ult_origen` varchar(255) DEFAULT NULL,
  `tipdocper_ult_usuario` varchar(255) DEFAULT NULL,
  `tipdocper_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`tipdocper_id`,`REV`),
  KEY `FK82B382B1DF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_tipos_entrada_colectiva`
--

DROP TABLE IF EXISTS `aud_ss_tipos_entrada_colectiva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_tipos_entrada_colectiva` (
  `tec_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `tec_codigo` varchar(255) DEFAULT NULL,
  `tec_descripcion` varchar(255) DEFAULT NULL,
  `tec_ult_mod` datetime DEFAULT NULL,
  `tec_ult_origen` varchar(255) DEFAULT NULL,
  `tec_ult_usuario` varchar(255) DEFAULT NULL,
  `tec_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`tec_id`,`REV`),
  KEY `FK2ABC90A6DF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_tipos_telefono`
--

DROP TABLE IF EXISTS `aud_ss_tipos_telefono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_tipos_telefono` (
  `tipTel_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `tipTel_codigo` varchar(255) DEFAULT NULL,
  `tipTel_descripcion` varchar(255) DEFAULT NULL,
  `tipTel_habilitado` tinyint(1) DEFAULT NULL,
  `tipTel_ult_mod` datetime DEFAULT NULL,
  `tipTel_ult_origen` varchar(255) DEFAULT NULL,
  `tipTel_ult_usuario` varchar(255) DEFAULT NULL,
  `tipTel_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`tipTel_id`,`REV`),
  KEY `FK5F493B64DF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_tipos_vialidad`
--

DROP TABLE IF EXISTS `aud_ss_tipos_vialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_tipos_vialidad` (
  `tvi_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `tvi_abreviacion` varchar(255) DEFAULT NULL,
  `tvi_codigo` varchar(255) DEFAULT NULL,
  `tvi_descripcion` varchar(255) DEFAULT NULL,
  `tvi_ult_mod` datetime DEFAULT NULL,
  `tvi_ult_origen` varchar(255) DEFAULT NULL,
  `tvi_ult_usuario` varchar(255) DEFAULT NULL,
  `tvi_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`tvi_id`,`REV`),
  KEY `FKF0430A0CDF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_usu_ofi_roles`
--

DROP TABLE IF EXISTS `aud_ss_usu_ofi_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_usu_ofi_roles` (
  `usu_ofi_roles_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `usu_ofi_roles_origen` varchar(255) DEFAULT NULL,
  `usu_ofi_roles_user_code` int(11) DEFAULT NULL,
  `usu_ofi_roles_oficina` int(11) DEFAULT NULL,
  `usu_ofi_roles_rol` int(11) DEFAULT NULL,
  `usu_ofi_roles_usuario` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `usu_ofi_roles_activo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`usu_ofi_roles_id`,`REV`),
  KEY `FK317B0718DF74E053` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aud_ss_usuario`
--

DROP TABLE IF EXISTS `aud_ss_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aud_ss_usuario` (
  `usu_id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `usu_administrador` tinyint(1) DEFAULT NULL,
  `usu_cambio_estado_desc` longtext,
  `usu_cod` varchar(255) DEFAULT NULL,
  `usu_correo_electronico` varchar(255) DEFAULT NULL,
  `usu_cuenta_bloqueada` smallint(6) DEFAULT NULL,
  `usu_descripcion` longtext,
  `usu_direccion` longtext,
  `usu_fecha_password` datetime DEFAULT NULL,
  `usu_fecha_uuid` datetime DEFAULT NULL,
  `usu_foto` longblob,
  `usu_intentos_fallidos` int(11) DEFAULT NULL,
  `usu_nro_doc` varchar(255) DEFAULT NULL,
  `usu_oficina_por_defecto` int(11) DEFAULT NULL,
  `usu_origen` longtext,
  `usu_password` varchar(255) DEFAULT NULL,
  `usu_primer_apellido` varchar(255) DEFAULT NULL,
  `usu_primer_nombre` varchar(255) DEFAULT NULL,
  `usu_registrado` tinyint(1) DEFAULT NULL,
  `usu_segundo_apellido` varchar(255) DEFAULT NULL,
  `usu_segundo_nombre` varchar(255) DEFAULT NULL,
  `usu_telefono` varchar(255) DEFAULT NULL,
  `usu_user_code` int(11) DEFAULT NULL,
  `usu_uuid_des` varchar(255) DEFAULT NULL,
  `usu_version` int(11) DEFAULT NULL,
  `usu_vigente` tinyint(1) DEFAULT NULL,
  `usu_aprob_facturas` tinyint(1) DEFAULT NULL,
  `usu_ult_usuario` varchar(255) DEFAULT NULL,
  `usu_ult_mod` datetime DEFAULT NULL,
  `usu_ult_origen` varchar(255) DEFAULT NULL,
  `usu_area_org` int(11) DEFAULT NULL,
  `usu_token` varchar(100) DEFAULT NULL,
  `usu_token_act` datetime DEFAULT NULL,
  `usu_celular` varchar(255) DEFAULT NULL,
  `usu_ldap_user` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`usu_id`,`REV`),
  KEY `FKB58E953EDF74E053` (`REV`),
  KEY `usu_area_org_idx` (`usu_area_org`),
  KEY `usu_token_idx` (`usu_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `busq_filtro`
--

DROP TABLE IF EXISTS `busq_filtro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `busq_filtro` (
  `busq_filtro_pk` int(11) NOT NULL AUTO_INCREMENT,
  `busq_filtro_usu_fk` int(11) NOT NULL,
  `busq_filtro_org_fk` int(11) NOT NULL,
  `busq_filtro_xml` mediumtext NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`busq_filtro_pk`),
  KEY `FK_4b0pq8qh2f6u7ei11lh0atbf8` (`busq_filtro_usu_fk`),
  KEY `FK_a726jrgroi6p90dip38n70ftp` (`busq_filtro_org_fk`),
  CONSTRAINT `FK_4b0pq8qh2f6u7ei11lh0atbf8` FOREIGN KEY (`busq_filtro_usu_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_a726jrgroi6p90dip38n70ftp` FOREIGN KEY (`busq_filtro_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=377 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `calidad`
--

DROP TABLE IF EXISTS `calidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calidad` (
  `cal_pk` int(11) NOT NULL AUTO_INCREMENT,
  `cal_peso` int(11) NOT NULL,
  `cal_vca_fk` int(11) NOT NULL,
  `cal_actualizacion` date NOT NULL,
  `cal_tipo` int(11) NOT NULL,
  `cal_ent_fk` int(11) DEFAULT NULL,
  `cal_prod_fk` int(11) DEFAULT NULL,
  `cal_tca_fk` int(11) DEFAULT NULL,
  `cal_proy_fk` int(11) NOT NULL,
  PRIMARY KEY (`cal_pk`),
  KEY `cal_ent_fk_idx` (`cal_ent_fk`),
  KEY `cal_prod_fk_idx` (`cal_prod_fk`),
  KEY `cal_tca_fk_idx` (`cal_tca_fk`),
  KEY `cal_valor_idx` (`cal_vca_fk`),
  KEY `cal_proy_fk_idx` (`cal_proy_fk`),
  KEY `cal_tipo` (`cal_tipo`),
  KEY `cal_actualizacion_idx` (`cal_actualizacion`),
  CONSTRAINT `cal_ent_fk` FOREIGN KEY (`cal_ent_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cal_prod_fk` FOREIGN KEY (`cal_prod_fk`) REFERENCES `productos` (`prod_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cal_proy_fk` FOREIGN KEY (`cal_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cal_tca_fk` FOREIGN KEY (`cal_tca_fk`) REFERENCES `temas_calidad` (`tca_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cal_vca_fk` FOREIGN KEY (`cal_vca_fk`) REFERENCES `valor_calidad_codigos` (`vca_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categoria_proyectos`
--

DROP TABLE IF EXISTS `categoria_proyectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria_proyectos` (
  `cat_proy_pk` int(11) NOT NULL AUTO_INCREMENT,
  `cat_proy_codigo` varchar(45) DEFAULT NULL,
  `cat_proy_nombre` varchar(145) NOT NULL,
  `cat_proy_activo` tinyint(1) NOT NULL DEFAULT '1',
  `cat_tipo` int(11) NOT NULL DEFAULT '0',
  `cat_icono` int(11) DEFAULT NULL,
  `cat_icono_marker` int(11) DEFAULT NULL,
  `cat_org_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`cat_proy_pk`),
  KEY `cat_proy_activo_idx` (`cat_proy_activo`),
  KEY `cat_org_fk_idx` (`cat_org_fk`),
  KEY `cat_tipo_idx` (`cat_tipo`),
  KEY `FK_hb3weyc8xvdrbp62k3u1halnb` (`cat_icono_marker`),
  KEY `FK_hh3lr9l8qt7isgvniyif3w6tw` (`cat_icono`),
  CONSTRAINT `FK_4t53ltja1415bq3d23c3kdjl1` FOREIGN KEY (`cat_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_hb3weyc8xvdrbp62k3u1halnb` FOREIGN KEY (`cat_icono_marker`) REFERENCES `image` (`image_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_hh3lr9l8qt7isgvniyif3w6tw` FOREIGN KEY (`cat_icono`) REFERENCES `image` (`image_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=709 DEFAULT CHARSET=latin1 COMMENT='Categoria de los Proyectos. Principalmente para usar con el Visualizador.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categoria_proyectos_BORRAR`
--

DROP TABLE IF EXISTS `categoria_proyectos_BORRAR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria_proyectos_BORRAR` (
  `cat_proy_pk` int(11) NOT NULL DEFAULT '0',
  `cat_proy_codigo` varchar(45) NOT NULL,
  `cat_proy_nombre` varchar(145) NOT NULL,
  `cat_proy_activo` tinyint(1) NOT NULL DEFAULT '1',
  `cat_tipo` int(11) NOT NULL DEFAULT '0',
  `cat_icono` int(11) DEFAULT NULL,
  `cat_icono_marker` int(11) DEFAULT NULL,
  `cat_org_fk` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `causales_compra`
--

DROP TABLE IF EXISTS `causales_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `causales_compra` (
  `cau_com_pk` int(11) NOT NULL AUTO_INCREMENT,
  `cau_com_nombre` varchar(100) NOT NULL,
  `cau_com_descripcion` varchar(300) NOT NULL,
  `cau_com_habilitado` tinyint(3) NOT NULL DEFAULT '1',
  `cau_com_org_fk` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cau_com_pk`),
  KEY `fk_cau_com_org` (`cau_com_org_fk`),
  CONSTRAINT `causales_compra_ibfk_1` FOREIGN KEY (`cau_com_org_fk`) REFERENCES `organismos` (`org_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `centros_costo`
--

DROP TABLE IF EXISTS `centros_costo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `centros_costo` (
  `cen_cos_pk` int(11) NOT NULL AUTO_INCREMENT,
  `cen_cos_nombre` varchar(100) NOT NULL,
  `cen_cos_descripcion` varchar(300) NOT NULL,
  `cen_cos_habilitado` tinyint(3) NOT NULL DEFAULT '1',
  `cen_cos_org_fk` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cen_cos_pk`),
  KEY `fk_cen_cos_org` (`cen_cos_org_fk`),
  CONSTRAINT `centros_costo_ibfk_1` FOREIGN KEY (`cen_cos_org_fk`) REFERENCES `organismos` (`org_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `componente_producto`
--

DROP TABLE IF EXISTS `componente_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `componente_producto` (
  `com_pk` int(11) NOT NULL AUTO_INCREMENT,
  `com_nombre` varchar(300) DEFAULT NULL,
  `com_org_fk` int(11) DEFAULT NULL,
  `com_version` int(11) DEFAULT NULL,
  `com_descripcion` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`com_pk`),
  KEY `fk_componente_producto_1_idx` (`com_org_fk`),
  CONSTRAINT `componente_producto_organismos_FK` FOREIGN KEY (`com_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cronogramas`
--

DROP TABLE IF EXISTS `cronogramas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cronogramas` (
  `cro_pk` int(11) NOT NULL AUTO_INCREMENT,
  `cro_ent_seleccionado` int(11) DEFAULT NULL,
  `cro_ent_borrados` varchar(45) DEFAULT NULL,
  `cro_resources` varchar(45) DEFAULT NULL,
  `cro_permiso_escritura` tinyint(1) DEFAULT NULL,
  `cro_permiso_escritura_padre` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`cro_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=5359 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `departamentos`
--

DROP TABLE IF EXISTS `departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departamentos` (
  `dep_pk` int(11) NOT NULL,
  `dep_nombre` varchar(145) DEFAULT NULL,
  `dep_lat` double DEFAULT NULL,
  `dep_lng` double DEFAULT NULL,
  `dep_zoom` int(11) DEFAULT NULL,
  PRIMARY KEY (`dep_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `devengado`
--

DROP TABLE IF EXISTS `devengado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `devengado` (
  `dev_pk` int(11) NOT NULL AUTO_INCREMENT,
  `dev_adq_fk` int(11) NOT NULL,
  `dev_mes` smallint(6) NOT NULL,
  `dev_anio` smallint(6) NOT NULL,
  `dev_plan` decimal(11,2) DEFAULT NULL,
  `dev_real` decimal(11,2) DEFAULT NULL,
  PRIMARY KEY (`dev_pk`),
  KEY `dev_mes_idx` (`dev_mes`),
  KEY `dev_anio_idx` (`dev_anio`),
  KEY `FK_9c8og633e1waprs81i6ayorba` (`dev_adq_fk`),
  CONSTRAINT `FK_9c8og633e1waprs81i6ayorba` FOREIGN KEY (`dev_adq_fk`) REFERENCES `adquisicion` (`adq_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=757 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `doc_file`
--

DROP TABLE IF EXISTS `doc_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doc_file` (
  `docfile_pk` int(11) NOT NULL AUTO_INCREMENT,
  `docfile_nombre` varchar(256) NOT NULL,
  `docfile_doc_fk` int(11) NOT NULL,
  `docfile_path` varchar(255) DEFAULT NULL,
  `docfile_ult_mod` datetime(6) DEFAULT NULL,
  `docfile_ult_origen` varchar(255) DEFAULT NULL,
  `docfile_ult_usuario` varchar(255) DEFAULT NULL,
  `docfile_version` int(11) DEFAULT '0',
  PRIMARY KEY (`docfile_pk`),
  UNIQUE KEY `UK_n76rhuste8gi3p3jq7m91j7iq` (`docfile_doc_fk`),
  KEY `docfile_doc_fk` (`docfile_doc_fk`),
  CONSTRAINT `FK_n76rhuste8gi3p3jq7m91j7iq` FOREIGN KEY (`docfile_doc_fk`) REFERENCES `documentos` (`docs_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11105 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `documentos`
--

DROP TABLE IF EXISTS `documentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documentos` (
  `docs_pk` int(11) NOT NULL AUTO_INCREMENT,
  `docs_nombre` varchar(100) DEFAULT NULL,
  `docs_fecha` datetime(6) DEFAULT NULL,
  `docs_privado` tinyint(1) DEFAULT NULL,
  `docs_estado` double DEFAULT NULL,
  `docs_entregable_fk` int(11) DEFAULT NULL,
  `docs_tipodoc_fk` int(11) NOT NULL,
  `docs_docfile_pk` int(11) DEFAULT NULL,
  `docs_aprobado` tinyint(1) DEFAULT NULL,
  `docs_pago_fk` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `docs_publicable` bit(1) NOT NULL DEFAULT b'0',
  `docs_ult_pub` datetime(6) DEFAULT NULL,
  `docs_pub_fecha` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`docs_pk`),
  KEY `entregable_fk_idx` (`docs_entregable_fk`),
  KEY `tipo_doc_fk_idx` (`docs_tipodoc_fk`),
  KEY `docs_pago_fk_idx` (`docs_pago_fk`),
  KEY `docs_docfile_fk_idx` (`docs_docfile_pk`),
  CONSTRAINT `docs_docfile_fk` FOREIGN KEY (`docs_docfile_pk`) REFERENCES `doc_file` (`docfile_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `docs_entregable_fk` FOREIGN KEY (`docs_entregable_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `docs_pago_fk` FOREIGN KEY (`docs_pago_fk`) REFERENCES `pagos` (`pag_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tipo_doc_fk` FOREIGN KEY (`docs_tipodoc_fk`) REFERENCES `tipo_documento_instancia` (`tipodoc_inst_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11616 DEFAULT CHARSET=utf8 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ent_hist_linea_base`
--

DROP TABLE IF EXISTS `ent_hist_linea_base`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ent_hist_linea_base` (
  `enthist_pk` int(11) NOT NULL AUTO_INCREMENT,
  `enthist_ent_fk` int(11) NOT NULL,
  `enthist_fecha` date NOT NULL,
  `enthist_inicio_linea_base` bigint(20) NOT NULL,
  `enthist_fin_linea_base` bigint(20) DEFAULT NULL,
  `enthist_duracion` int(11) DEFAULT NULL,
  `enthist_replan_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`enthist_pk`),
  KEY `enthist_ent_fk_idx` (`enthist_ent_fk`),
  KEY `enthist_replan_fk_idx` (`enthist_replan_fk`),
  CONSTRAINT `enthist_ent_fk` FOREIGN KEY (`enthist_ent_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `enthist_replan_fk` FOREIGN KEY (`enthist_replan_fk`) REFERENCES `proy_replanificacion` (`proyreplan_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44278 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entregables`
--

DROP TABLE IF EXISTS `entregables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entregables` (
  `ent_pk` int(11) NOT NULL AUTO_INCREMENT,
  `ent_cro_fk` int(11) NOT NULL,
  `ent_id` int(11) NOT NULL,
  `ent_nombre` varchar(1000) DEFAULT NULL,
  `ent_codigo` varchar(256) DEFAULT NULL,
  `ent_nivel` int(11) DEFAULT NULL,
  `ent_status` varchar(256) DEFAULT NULL,
  `ent_parent` tinyint(1) DEFAULT NULL,
  `ent_inicio` bigint(20) DEFAULT NULL,
  `ent_duracion` int(11) DEFAULT NULL,
  `ent_fin` bigint(20) DEFAULT NULL,
  `ent_horas_estimadas` varchar(15) DEFAULT NULL,
  `ent_inicio_es_hito` tinyint(1) DEFAULT NULL,
  `ent_fin_es_hito` tinyint(1) DEFAULT NULL,
  `ent_collapsed` tinyint(1) DEFAULT NULL,
  `ent_assigs` varchar(256) DEFAULT NULL,
  `ent_coord_usu_fk` int(11) DEFAULT NULL,
  `ent_esfuerzo` int(11) DEFAULT NULL,
  `ent_inicio_linea_base` bigint(20) DEFAULT NULL,
  `ent_duracion_linea_base` int(11) DEFAULT NULL,
  `ent_fin_linea_base` bigint(20) DEFAULT NULL,
  `ent_predecesor_fk` varchar(2000) DEFAULT NULL,
  `ent_predecesor_dias` int(11) DEFAULT NULL,
  `ent_descripcion` varchar(1000) DEFAULT NULL,
  `ent_progreso` int(11) DEFAULT NULL,
  `ent_relevante` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `ent_inicio_periodo` bit(1) NOT NULL DEFAULT b'0',
  `ent_fin_periodo` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ent_pk`),
  KEY `gantt_task_gantt_fk_idx` (`ent_cro_fk`),
  KEY `ent_coord_usu_fk_idx` (`ent_coord_usu_fk`),
  KEY `ent_inicio_idx` (`ent_inicio`),
  KEY `ent_fin_idx` (`ent_fin`),
  KEY `ent_progreso_idx` (`ent_progreso`),
  KEY `ent_parent_idx` (`ent_parent`),
  CONSTRAINT `ent_coord_usu_fk` FOREIGN KEY (`ent_coord_usu_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ent_cro_fk` FOREIGN KEY (`ent_cro_fk`) REFERENCES `cronogramas` (`cro_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44198 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entregables_apagon_20160113`
--

DROP TABLE IF EXISTS `entregables_apagon_20160113`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entregables_apagon_20160113` (
  `ent_pk` int(11) NOT NULL DEFAULT '0',
  `ent_cro_fk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estados` (
  `est_pk` int(11) NOT NULL AUTO_INCREMENT,
  `est_codigo` varchar(150) NOT NULL,
  `est_nombre` varchar(45) DEFAULT NULL,
  `est_label` varchar(150) NOT NULL,
  `est_orden_proceso` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`est_pk`),
  KEY `est_codigo` (`est_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estados_publicacion`
--

DROP TABLE IF EXISTS `estados_publicacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estados_publicacion` (
  `est_pub_pk` int(11) NOT NULL AUTO_INCREMENT,
  `est_pub_codigo` varchar(45) DEFAULT NULL,
  `est_pub_nombre` varchar(145) DEFAULT NULL,
  PRIMARY KEY (`est_pub_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COMMENT='Estados de la publicación de un proyecto en el visualizador.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `etapa`
--

DROP TABLE IF EXISTS `etapa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `etapa` (
  `eta_pk` int(11) NOT NULL AUTO_INCREMENT,
  `eta_org_fk` int(11) NOT NULL,
  `eta_codigo` varchar(45) NOT NULL,
  `eta_nombre` varchar(100) NOT NULL,
  `eta_label` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`eta_pk`),
  KEY `eta_org_fk_idx` (`eta_org_fk`),
  KEY `eta_org_fk_cod_idx` (`eta_org_fk`,`eta_codigo`),
  CONSTRAINT `eta_org_fk` FOREIGN KEY (`eta_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=latin1 COMMENT='Son los estados del proyecto que se van a exportar al visualizador.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fue_pro_com_cau_com`
--

DROP TABLE IF EXISTS `fue_pro_com_cau_com`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fue_pro_com_cau_com` (
  `fue_pro_com_pk` int(11) NOT NULL,
  `cau_com_pk` int(11) NOT NULL,
  KEY `fuente_proc_compra_key` (`fue_pro_com_pk`),
  KEY `causal_compra_key` (`cau_com_pk`),
  CONSTRAINT `causal_compras_key` FOREIGN KEY (`cau_com_pk`) REFERENCES `causales_compra` (`cau_com_pk`),
  CONSTRAINT `fuente_proc_compras_key` FOREIGN KEY (`fue_pro_com_pk`) REFERENCES `fuentes_procedimiento_compra` (`fue_pro_com_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fuente_financiamiento`
--

DROP TABLE IF EXISTS `fuente_financiamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fuente_financiamiento` (
  `fue_pk` int(11) NOT NULL AUTO_INCREMENT,
  `fue_nombre` varchar(300) NOT NULL,
  `fue_org_fk` int(11) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`fue_pk`),
  KEY `fue_org_fk_idx` (`fue_org_fk`),
  CONSTRAINT `fue_org_fk` FOREIGN KEY (`fue_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fuentes_procedimiento_compra`
--

DROP TABLE IF EXISTS `fuentes_procedimiento_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fuentes_procedimiento_compra` (
  `fue_pro_com_pk` int(11) NOT NULL AUTO_INCREMENT,
  `fue_pro_com_fuente` varchar(100) NOT NULL,
  `fue_pro_com_procedimiento_compra` varchar(100) NOT NULL,
  `fue_pro_com_habilitado` tinyint(3) NOT NULL DEFAULT '1',
  `fue_pro_com_org_fk` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`fue_pro_com_pk`),
  KEY `fk_fue_pro_com_org` (`fue_pro_com_org_fk`),
  CONSTRAINT `fuentes_procedimiento_compra_ibfk_1` FOREIGN KEY (`fue_pro_com_org_fk`) REFERENCES `organismos` (`org_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gastos`
--

DROP TABLE IF EXISTS `gastos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gastos` (
  `gas_pk` int(11) NOT NULL AUTO_INCREMENT,
  `gas_proy_fk` int(11) NOT NULL,
  `gas_tipo_fk` int(11) NOT NULL,
  `gas_usu_fk` int(11) NOT NULL,
  `gas_mon_fk` int(11) NOT NULL,
  `gas_importe` decimal(11,2) NOT NULL,
  `gas_fecha` date NOT NULL,
  `gas_obs` varchar(4000) NOT NULL,
  `gas_aprobado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`gas_pk`),
  KEY `gas_proy_fk_idx` (`gas_proy_fk`),
  KEY `gas_tipo_fk_idx` (`gas_tipo_fk`),
  KEY `gas_usu_fk_idx` (`gas_usu_fk`),
  KEY `gas_mon_fk_idx` (`gas_mon_fk`),
  KEY `gas_fecha_idx` (`gas_fecha`),
  KEY `gas_aprobado_idx` (`gas_aprobado`),
  CONSTRAINT `gas_mon_fk` FOREIGN KEY (`gas_mon_fk`) REFERENCES `moneda` (`mon_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gas_proy_fk` FOREIGN KEY (`gas_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gas_tipo_fk` FOREIGN KEY (`gas_tipo_fk`) REFERENCES `tipo_gasto` (`tipogas_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gas_usu_fk` FOREIGN KEY (`gas_usu_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL,
  `version` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `identificadores_grp_erp`
--

DROP TABLE IF EXISTS `identificadores_grp_erp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `identificadores_grp_erp` (
  `id_grp_erp_pk` int(11) NOT NULL AUTO_INCREMENT,
  `id_grp_erp_nombre` varchar(100) NOT NULL,
  `id_grp_erp_descripcion` varchar(300) NOT NULL,
  `id_grp_erp_habilitado` tinyint(3) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_grp_erp_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `image_pk` int(10) NOT NULL AUTO_INCREMENT,
  `image_name` varchar(45) NOT NULL,
  `image_desc` varchar(255) DEFAULT NULL,
  `image_ext` varchar(20) NOT NULL,
  `image_blob` mediumblob NOT NULL,
  `image_tipo` int(5) DEFAULT NULL,
  `image_orden` int(5) DEFAULT NULL,
  PRIMARY KEY (`image_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interesados`
--

DROP TABLE IF EXISTS `interesados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interesados` (
  `int_pk` int(11) NOT NULL AUTO_INCREMENT,
  `int_rolint_fk` int(11) NOT NULL,
  `int_observaciones` varchar(4000) DEFAULT NULL,
  `int_pers_fk` int(11) DEFAULT NULL,
  `int_orga_fk` int(11) DEFAULT NULL,
  `int_ent_fk` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`int_pk`),
  KEY `fk_INTERESADOS_ROLES_INTERESADOS1_idx` (`int_rolint_fk`),
  KEY `fk_INTERESADOS_PERSONAS1_idx` (`int_pers_fk`),
  KEY `fk_INTERESADOS_ORGANIZACION1_idx` (`int_orga_fk`),
  KEY `int_ent_fk_idx` (`int_ent_fk`),
  CONSTRAINT `fk_INTERESADOS_ORGANIZACION1` FOREIGN KEY (`int_orga_fk`) REFERENCES `organi_int_prove` (`orga_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_INTERESADOS_PERSONAS1` FOREIGN KEY (`int_pers_fk`) REFERENCES `personas` (`pers_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_INTERESADOS_ROLES_INTERESADOS1` FOREIGN KEY (`int_rolint_fk`) REFERENCES `roles_interesados` (`rolint_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `int_ent_fk` FOREIGN KEY (`int_ent_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=430 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `latlng_proyectos`
--

DROP TABLE IF EXISTS `latlng_proyectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `latlng_proyectos` (
  `latlng_pk` int(11) NOT NULL AUTO_INCREMENT COMMENT 'La clave primaria de la tabla',
  `latlng_lat` decimal(19,15) DEFAULT NULL COMMENT 'la latitud del punto',
  `latlng_lng` decimal(19,15) DEFAULT NULL COMMENT 'la longitud del punto',
  `latlng_proy_fk` int(11) DEFAULT NULL COMMENT 'EL proyecto al cual pertenece este punto.',
  `latlang_dep_fk` int(11) DEFAULT NULL,
  `latlang_loc_fk` int(11) DEFAULT NULL,
  `latlang_codigopostal` int(11) DEFAULT NULL,
  `latlang_barrio` varchar(245) DEFAULT NULL,
  `latlang_loc` varchar(245) DEFAULT NULL,
  PRIMARY KEY (`latlng_pk`),
  KEY `latlng_proy_fk_idx` (`latlng_proy_fk`),
  KEY `latlang_dep_fk_idx` (`latlang_dep_fk`),
  CONSTRAINT `latlang_dep_fk` FOREIGN KEY (`latlang_dep_fk`) REFERENCES `departamentos` (`dep_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `latlng_proy_fk` FOREIGN KEY (`latlng_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3436 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lecapr_areacon`
--

DROP TABLE IF EXISTS `lecapr_areacon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lecapr_areacon` (
  `lecaprcon_con_fk` int(11) NOT NULL,
  `lecaprcon_lecapr_fk` int(11) NOT NULL,
  PRIMARY KEY (`lecaprcon_con_fk`,`lecaprcon_lecapr_fk`),
  KEY `lecaprcon_lecapr_fk_idx` (`lecaprcon_lecapr_fk`),
  CONSTRAINT `lecaprcon_con_fk` FOREIGN KEY (`lecaprcon_con_fk`) REFERENCES `area_conocimiento` (`con_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecaprcon_lecapr_fk` FOREIGN KEY (`lecaprcon_lecapr_fk`) REFERENCES `lecc_aprendidas` (`lecapr_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lecc_aprendidas`
--

DROP TABLE IF EXISTS `lecc_aprendidas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lecc_aprendidas` (
  `lecapr_pk` int(11) NOT NULL AUTO_INCREMENT,
  `lecapr_proy_fk` int(11) DEFAULT NULL,
  `lecapr_tipo_fk` int(11) NOT NULL,
  `lecapr_usr_fk` int(11) NOT NULL,
  `lecapr_org_fk` int(11) NOT NULL,
  `lecapr_fecha` date NOT NULL,
  `lecapr_desc` varchar(3000) NOT NULL,
  `lecapr_activo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`lecapr_pk`),
  KEY `lecapr_proy_fk_idx` (`lecapr_proy_fk`),
  KEY `lecapr_tipo_fk_idx` (`lecapr_tipo_fk`),
  KEY `lecapr_usr_fk_idx` (`lecapr_usr_fk`),
  KEY `lecapr_org_fk_idx` (`lecapr_org_fk`),
  KEY `lecapr_fecha_idx` (`lecapr_fecha`),
  KEY `lecapr_activo_idx` (`lecapr_activo`),
  CONSTRAINT `lecapr_org_fk` FOREIGN KEY (`lecapr_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecapr_proy_fk` FOREIGN KEY (`lecapr_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecapr_tipo_fk` FOREIGN KEY (`lecapr_tipo_fk`) REFERENCES `tipo_leccion` (`tipolec_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecapr_usr_fk` FOREIGN KEY (`lecapr_usr_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=920 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lineabase_historico`
--

DROP TABLE IF EXISTS `lineabase_historico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lineabase_historico` (
  `lineabase_pk` int(11) NOT NULL AUTO_INCREMENT,
  `lineabase_entFk` int(11) NOT NULL,
  `lineabase_fecha` date NOT NULL,
  `lineabase_inicio` bigint(20) NOT NULL,
  `lineabase_duracion` int(11) DEFAULT NULL,
  `lineabase_fin` bigint(20) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`lineabase_pk`),
  KEY `entre_lineabase_entFk_idx` (`lineabase_entFk`)
) ENGINE=InnoDB AUTO_INCREMENT=445 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mails_template`
--

DROP TABLE IF EXISTS `mails_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mails_template` (
  `mail_tmp_pk` int(11) NOT NULL AUTO_INCREMENT,
  `mail_tmp_cod` varchar(45) NOT NULL,
  `mail_tmp_org_fk` int(11) NOT NULL,
  `mail_tmp_asunto` varchar(200) NOT NULL,
  `mail_tmp_mensaje` varchar(5000) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`mail_tmp_pk`),
  KEY `mail_tmp_org_fk_idx` (`mail_tmp_org_fk`),
  CONSTRAINT `mail_tmp_org_fk` FOREIGN KEY (`mail_tmp_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `media_proyectos`
--

DROP TABLE IF EXISTS `media_proyectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media_proyectos` (
  `media_pk` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Clave primaria del media',
  `media_tipo_fk` int(11) DEFAULT NULL,
  `media_link` varchar(545) DEFAULT NULL COMMENT 'El link al media, en caso de imagenes es el link al folder donde se encuentra, en youtube la url del video, y en camaras web la url de la camara',
  `media_estado` int(11) DEFAULT NULL COMMENT 'EL estado del media puede ser \nPENDIENTE REVISION, PUBLICADO, RECHAZADO',
  `media_proy_fk` int(11) DEFAULT NULL,
  `media_principal` tinyint(1) DEFAULT NULL COMMENT 'en caso de ser una imagen es la que se utlizará para la vista rapida y para la seccion de destacados',
  `media_orden` int(11) DEFAULT NULL COMMENT 'EL orde de aparición del media en la galeria que lo esta desplegando.',
  `media_usr_pub_fk` int(11) DEFAULT NULL COMMENT 'EL código del usuario que publicó el media',
  `media_pub_fecha` datetime DEFAULT NULL COMMENT 'Fecha y hora de publicación',
  `media_usr_mod_fk` int(11) DEFAULT NULL COMMENT 'EL usuario que creo el media',
  `media_mod_fecha` datetime DEFAULT NULL COMMENT 'La fecha de creación del media',
  `media_usr_rech_fk` int(11) DEFAULT NULL COMMENT 'Usuario que rechaza el media',
  `media_rech_fecha` datetime DEFAULT NULL COMMENT 'fecha de rehazo del medio',
  `media_comentario` varchar(2000) DEFAULT NULL,
  `media_contenttype` varchar(200) DEFAULT NULL,
  `media_publicable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`media_pk`),
  KEY `media_proy_fk_idx` (`media_proy_fk`),
  KEY `media_tipo_fk_idx` (`media_tipo_fk`),
  KEY `media_usr_pub_fk_idx` (`media_usr_pub_fk`),
  KEY `media_usr_mod_fk_idx` (`media_usr_mod_fk`),
  KEY `media_usr_rech_fk_idx` (`media_usr_rech_fk`),
  CONSTRAINT `media_proy_fk` FOREIGN KEY (`media_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `media_tipo_fk` FOREIGN KEY (`media_tipo_fk`) REFERENCES `tipos_media` (`tip_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `media_usr_mod_fk` FOREIGN KEY (`media_usr_mod_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `media_usr_pub_fk` FOREIGN KEY (`media_usr_pub_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `media_usr_rech_fk` FOREIGN KEY (`media_usr_rech_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4806 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `moneda`
--

DROP TABLE IF EXISTS `moneda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moneda` (
  `mon_pk` int(11) NOT NULL AUTO_INCREMENT,
  `mon_nombre` varchar(100) NOT NULL,
  `mon_signo` varchar(50) DEFAULT NULL,
  `mon_cod_pais` varchar(10) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`mon_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notificacion`
--

DROP TABLE IF EXISTS `notificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notificacion` (
  `not_pk` int(11) NOT NULL AUTO_INCREMENT,
  `not_org_fk` int(11) NOT NULL,
  `not_cod` varchar(45) NOT NULL,
  `not_desc` varchar(245) NOT NULL,
  `not_valor` int(11) DEFAULT NULL,
  `not_gerente_adjunto` tinyint(4) DEFAULT NULL,
  `not_pmof` tinyint(4) DEFAULT NULL,
  `not_pmot` tinyint(4) DEFAULT NULL,
  `not_sponsor` tinyint(4) DEFAULT NULL,
  `not_msg` varchar(5000) NOT NULL,
  PRIMARY KEY (`not_pk`),
  KEY `notificacion_not_org_fk_IDX` (`not_org_fk`),
  CONSTRAINT `notificacion_organismos_FK` FOREIGN KEY (`not_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=464 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notificacion_envio`
--

DROP TABLE IF EXISTS `notificacion_envio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notificacion_envio` (
  `notenv_pk` int(11) NOT NULL AUTO_INCREMENT,
  `notenv_fecha` date NOT NULL,
  `notenv_proy_fk` int(11) NOT NULL,
  `notenv_not_cod` varchar(45) NOT NULL,
  PRIMARY KEY (`notenv_pk`),
  KEY `notenv_fecha_idx` (`notenv_fecha`),
  KEY `notificacion_envio_proyectos_FK` (`notenv_proy_fk`),
  CONSTRAINT `notificacion_envio_proyectos_FK` FOREIGN KEY (`notenv_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=53873 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notificacion_instancia`
--

DROP TABLE IF EXISTS `notificacion_instancia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notificacion_instancia` (
  `notinst_pk` int(11) NOT NULL AUTO_INCREMENT,
  `notinst_not_fk` int(11) NOT NULL,
  `notinst_proy_fk` int(11) NOT NULL,
  `notinst_gerente_adjunto` tinyint(4) DEFAULT NULL,
  `notinst_pmof` tinyint(4) DEFAULT NULL,
  `notinst_pmot` tinyint(4) DEFAULT NULL,
  `notinst_sponsor` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`notinst_pk`),
  KEY `notinst_not_fk_idx` (`notinst_not_fk`),
  KEY `notinst_proy_fk_idx` (`notinst_proy_fk`),
  CONSTRAINT `notinst_not_fk` FOREIGN KEY (`notinst_not_fk`) REFERENCES `notificacion` (`not_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `notinst_proy_fk` FOREIGN KEY (`notinst_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=43864 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `objetivos_estrategicos`
--

DROP TABLE IF EXISTS `objetivos_estrategicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `objetivos_estrategicos` (
  `obj_est_pk` int(11) NOT NULL AUTO_INCREMENT,
  `obj_est_nombre` varchar(100) DEFAULT NULL,
  `obj_est_descripcion` varchar(300) DEFAULT NULL,
  `obj_est_org_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`obj_est_pk`),
  UNIQUE KEY `obj_est_org_fk_nombre` (`obj_est_org_fk`,`obj_est_nombre`),
  KEY `obj_est_org_fk_idx` (`obj_est_org_fk`),
  CONSTRAINT `obj_est_org_fk` FOREIGN KEY (`obj_est_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `organi_int_prove`
--

DROP TABLE IF EXISTS `organi_int_prove`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organi_int_prove` (
  `orga_pk` int(11) NOT NULL AUTO_INCREMENT,
  `orga_nombre` varchar(50) DEFAULT NULL,
  `orga_proveedor` tinyint(1) DEFAULT NULL,
  `orga_razon_social` varchar(50) DEFAULT NULL,
  `orga_rut` varchar(45) DEFAULT NULL,
  `orga_mail` varchar(45) DEFAULT NULL,
  `orga_telefono` varchar(45) DEFAULT NULL,
  `orga_web` varchar(45) DEFAULT NULL,
  `orga_direccion` varchar(100) DEFAULT NULL,
  `orga_ambito` varchar(45) DEFAULT NULL,
  `orga_org_fk` int(11) NOT NULL,
  `orga_amb_fk` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`orga_pk`),
  KEY `orga_org_fk_idx` (`orga_org_fk`),
  KEY `orga_amb_fk_idx` (`orga_amb_fk`),
  KEY `orga_proveedor_idx` (`orga_proveedor`),
  CONSTRAINT `orga_amb_fk` FOREIGN KEY (`orga_amb_fk`) REFERENCES `ambito` (`amb_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `orga_org_fk` FOREIGN KEY (`orga_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2055 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `organismos`
--

DROP TABLE IF EXISTS `organismos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organismos` (
  `org_pk` int(11) NOT NULL AUTO_INCREMENT,
  `org_nombre` varchar(45) DEFAULT NULL,
  `org_logo_nombre` varchar(45) DEFAULT NULL,
  `org_direccion` varchar(45) DEFAULT NULL,
  `org_logo` mediumblob,
  `org_activo` tinyint(1) NOT NULL DEFAULT '1',
  `org_token` varchar(100) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`org_pk`),
  KEY `org_token_idx` (`org_token`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagos` (
  `pag_pk` int(11) NOT NULL AUTO_INCREMENT,
  `pag_adq_fk` int(11) NOT NULL,
  `pag_ent_fk` int(11) DEFAULT NULL,
  `pag_observacion` varchar(3000) DEFAULT NULL,
  `pag_fecha_planificada` date NOT NULL,
  `pag_importe_planificado` decimal(11,2) NOT NULL,
  `pag_fecha_real` date DEFAULT NULL,
  `pag_importe_real` decimal(11,2) DEFAULT NULL,
  `pag_txt_referencia` varchar(20) DEFAULT NULL,
  `pag_confirmar` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `pag_gasto` smallint(6) NOT NULL DEFAULT '0',
  `pag_inversion` smallint(6) NOT NULL DEFAULT '0',
  `pag_contr_organizacion_fk` int(11) DEFAULT NULL,
  `pag_contr_porcentaje` smallint(6) DEFAULT NULL,
  `pag_proveedor_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`pag_pk`),
  KEY `pag_ent_fk_idx` (`pag_ent_fk`),
  KEY `pag_adq_fk_idx` (`pag_adq_fk`),
  KEY `pag_fecha_planificada_idx` (`pag_fecha_planificada`),
  KEY `pag_fecha_real_idx` (`pag_fecha_real`),
  KEY `pag_confirmar_idx` (`pag_confirmar`),
  KEY `pagos_organi_int_prove_FK` (`pag_contr_organizacion_fk`),
  KEY `pagos_proveedor_idx` (`pag_proveedor_fk`),
  CONSTRAINT `pag_adq_fk` FOREIGN KEY (`pag_adq_fk`) REFERENCES `adquisicion` (`adq_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pag_ent_fk` FOREIGN KEY (`pag_ent_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pagos_organi_int_prove_FK` FOREIGN KEY (`pag_contr_organizacion_fk`) REFERENCES `organi_int_prove` (`orga_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pagos_proveedor` FOREIGN KEY (`pag_proveedor_fk`) REFERENCES `organi_int_prove` (`orga_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13562 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `participantes`
--

DROP TABLE IF EXISTS `participantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participantes` (
  `part_pk` int(11) NOT NULL AUTO_INCREMENT,
  `part_usu_fk` int(11) NOT NULL,
  `part_proy_fk` int(11) NOT NULL,
  `part_ent_fk` int(11) DEFAULT NULL,
  `part_horas_plan` decimal(11,2) DEFAULT NULL,
  `part_activo` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`part_pk`),
  UNIQUE KEY `part_usu_fk` (`part_usu_fk`,`part_proy_fk`),
  KEY `fk_participantes_proyectos1` (`part_proy_fk`),
  KEY `part_ent_fk_idx` (`part_ent_fk`),
  KEY `part_activo_idx` (`part_activo`),
  CONSTRAINT `fk_participantes_proyectos1` FOREIGN KEY (`part_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_participantes_usuarios1` FOREIGN KEY (`part_usu_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `part_ent_fk` FOREIGN KEY (`part_ent_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personas` (
  `pers_pk` int(11) NOT NULL AUTO_INCREMENT,
  `pers_rol_fk` int(11) NOT NULL,
  `pers_orga_fk` int(11) NOT NULL,
  `pers_nombre` varchar(45) NOT NULL,
  `pers_telefono` varchar(45) DEFAULT NULL,
  `pers_cargo` varchar(45) DEFAULT NULL,
  `pers_mail` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`pers_pk`),
  KEY `fk_PERSONAS_ROLES_USUARIOS1_idx` (`pers_rol_fk`),
  KEY `FK1A6A26477E1BCA41` (`pers_orga_fk`),
  CONSTRAINT `FK1A6A26477E1BCA41` FOREIGN KEY (`pers_orga_fk`) REFERENCES `organi_int_prove` (`orga_pk`),
  CONSTRAINT `personas_roles_interesados_FK` FOREIGN KEY (`pers_rol_fk`) REFERENCES `roles_interesados` (`rolint_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pge_configuraciones`
--

DROP TABLE IF EXISTS `pge_configuraciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pge_configuraciones` (
  `cnf_id` int(11) NOT NULL AUTO_INCREMENT,
  `cnf_clave` varchar(255) DEFAULT NULL,
  `cnf_crea_fecha` datetime DEFAULT NULL,
  `cnf_crea_origen` int(11) DEFAULT NULL,
  `cnf_crea_usu` varchar(255) DEFAULT NULL,
  `cnf_ultmod_fecha` datetime DEFAULT NULL,
  `cnf_ultmod_origen` int(11) DEFAULT NULL,
  `cnf_ultmod_usu` varchar(255) DEFAULT NULL,
  `cnf_valor` varchar(255) DEFAULT NULL,
  `cnf_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`cnf_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pge_invocaciones`
--

DROP TABLE IF EXISTS `pge_invocaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pge_invocaciones` (
  `inv_id` int(11) NOT NULL AUTO_INCREMENT,
  `inv_crea_usu` varchar(255) DEFAULT NULL,
  `inv_env_fecha` datetime DEFAULT NULL,
  `inv_env_mensaje` varchar(255) DEFAULT NULL,
  `inv_env_ok` tinyint(1) DEFAULT NULL,
  `inv_operacion` varchar(255) DEFAULT NULL,
  `inv_rec_fecha` datetime DEFAULT NULL,
  `inv_rec_mensaje` varchar(255) DEFAULT NULL,
  `inv_rec_ok` tinyint(1) DEFAULT NULL,
  `inv_servicio` varchar(255) DEFAULT NULL,
  `inv_url` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`inv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plantilla_cronograma`
--

DROP TABLE IF EXISTS `plantilla_cronograma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plantilla_cronograma` (
  `p_crono_pk` int(11) NOT NULL AUTO_INCREMENT,
  `p_crono_nombre` varchar(845) DEFAULT NULL,
  `p_crono_org_fk` int(11) DEFAULT NULL,
  `activo` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`p_crono_pk`),
  KEY `plantilla_cronograma_activo_IDX` (`activo`),
  KEY `plantilla_cronograma_organismos_FK` (`p_crono_org_fk`),
  CONSTRAINT `plantilla_cronograma_organismos_FK` FOREIGN KEY (`p_crono_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plantilla_entregables`
--

DROP TABLE IF EXISTS `plantilla_entregables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plantilla_entregables` (
  `p_entregables_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_entregables_nombre` varchar(1000) DEFAULT NULL,
  `p_entregable_nivel` int(11) DEFAULT NULL,
  `p_entregable_esfuerzo` int(11) DEFAULT NULL,
  `p_entregable_duracion` int(11) DEFAULT NULL,
  `p_entregable_ant_fk` int(11) DEFAULT NULL,
  `p_entregable_p_cro_fk` int(11) DEFAULT NULL,
  `p_entregables_numero` int(11) DEFAULT NULL,
  `p_entregables_dependencia` varchar(45) DEFAULT NULL,
  `p_entregables_es_hito` bit(1) DEFAULT NULL,
  PRIMARY KEY (`p_entregables_id`),
  KEY `plantilla_cro_idx` (`p_entregable_p_cro_fk`),
  KEY `entre_ante_idx` (`p_entregable_ant_fk`),
  CONSTRAINT `entre_ante` FOREIGN KEY (`p_entregable_ant_fk`) REFERENCES `plantilla_entregables` (`p_entregables_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `plantilla_cro` FOREIGN KEY (`p_entregable_p_cro_fk`) REFERENCES `plantilla_cronograma` (`p_crono_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=590 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `presupuesto`
--

DROP TABLE IF EXISTS `presupuesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presupuesto` (
  `pre_pk` int(11) NOT NULL AUTO_INCREMENT,
  `pre_base` decimal(15,2) DEFAULT NULL,
  `pre_moneda` int(11) DEFAULT NULL,
  `pre_fuente_organi_fk` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`pre_pk`),
  KEY `pre_fuente_organi_idx` (`pre_fuente_organi_fk`),
  KEY `pre_moneda_idx` (`pre_moneda`),
  CONSTRAINT `pre_fuente_organi` FOREIGN KEY (`pre_fuente_organi_fk`) REFERENCES `fuente_financiamiento` (`fue_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pre_moneda` FOREIGN KEY (`pre_moneda`) REFERENCES `moneda` (`mon_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4396 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `procedimiento_compra`
--

DROP TABLE IF EXISTS `procedimiento_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procedimiento_compra` (
  `proc_comp_pk` int(11) NOT NULL AUTO_INCREMENT,
  `proc_comp_nombre` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `proc_comp_org_fk` int(11) DEFAULT NULL,
  `proc_comp_version` int(11) DEFAULT NULL,
  `proc_comp_descripcion` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `proc_comp_habilitado` bit(1) DEFAULT b'1',
  PRIMARY KEY (`proc_comp_pk`),
  KEY `fk_procedimiento_compra_1_idx` (`proc_comp_org_fk`),
  KEY `procedimiento_compra_proc_comp_habilitado_IDX` (`proc_comp_habilitado`),
  CONSTRAINT `procedimiento_compra_organismos_FK` FOREIGN KEY (`proc_comp_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1499 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prod_mes`
--

DROP TABLE IF EXISTS `prod_mes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prod_mes` (
  `prodmes_pk` int(11) NOT NULL AUTO_INCREMENT,
  `prodmes_prod_fk` int(11) NOT NULL,
  `prodmes_mes` smallint(6) NOT NULL,
  `prodmes_anio` smallint(6) NOT NULL,
  `prodmes_plan` decimal(11,2) NOT NULL,
  `prodmes_real` decimal(11,2) DEFAULT NULL,
  `prodmes_acu_plan` decimal(11,2) NOT NULL,
  `prodmes_acu_real` decimal(11,2) DEFAULT NULL,
  PRIMARY KEY (`prodmes_pk`),
  KEY `prodmes_prod_fk_idx` (`prodmes_prod_fk`),
  KEY `prodmes_mes_idx` (`prodmes_mes`),
  KEY `prodmes_anio_idx` (`prodmes_anio`),
  CONSTRAINT `prodmes_prod_fk` FOREIGN KEY (`prodmes_prod_fk`) REFERENCES `productos` (`prod_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16858 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `prod_pk` int(11) NOT NULL AUTO_INCREMENT,
  `prod_peso` int(11) NOT NULL,
  `prod_und_medida` varchar(45) NOT NULL,
  `prod_ent_fk` int(11) NOT NULL,
  `prod_fecha_crea` date NOT NULL,
  `prod_ult_mod` date DEFAULT NULL,
  `prod_acumulado` tinyint(1) DEFAULT NULL,
  `prod_desc` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`prod_pk`),
  KEY `prod_ent_fk_idx` (`prod_ent_fk`),
  CONSTRAINT `prod_ent_fk` FOREIGN KEY (`prod_ent_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1983 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prog_docs`
--

DROP TABLE IF EXISTS `prog_docs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prog_docs` (
  `progdocs_prog_pk` int(11) NOT NULL,
  `progdocs_doc_pk` int(11) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`progdocs_prog_pk`,`progdocs_doc_pk`),
  KEY `fk_Prog_docs_DOCUMENTOS1_idx` (`progdocs_doc_pk`),
  CONSTRAINT `fk_Prog_docs_DOCUMENTOS1` FOREIGN KEY (`progdocs_doc_pk`) REFERENCES `documentos` (`docs_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prog_docs_obl`
--

DROP TABLE IF EXISTS `prog_docs_obl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prog_docs_obl` (
  `progdocsobl_docs_pk` int(11) NOT NULL,
  `progdocsobl_prog_pk` int(11) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`progdocsobl_docs_pk`,`progdocsobl_prog_pk`),
  KEY `fk_DOCS_OBL_PROGRAMAS1_idx` (`progdocsobl_prog_pk`),
  CONSTRAINT `fk_DOCS_OBL_DOCUMENTOS1` FOREIGN KEY (`progdocsobl_docs_pk`) REFERENCES `documentos` (`docs_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DOCS_OBL_PROGRAMAS1` FOREIGN KEY (`progdocsobl_prog_pk`) REFERENCES `programas` (`prog_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prog_indices`
--

DROP TABLE IF EXISTS `prog_indices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prog_indices` (
  `progind_pk` int(11) NOT NULL AUTO_INCREMENT,
  `progind_riesgo_expo` double(11,2) DEFAULT NULL,
  `progind_riesgo_ultact` date DEFAULT NULL,
  `progind_riesgo_alto` int(11) DEFAULT NULL,
  `progind_metodo_estado` double(11,2) DEFAULT NULL,
  `progind_metodo_sin_aprobar` tinyint(1) DEFAULT NULL,
  `progind_periodo_inicio` date DEFAULT NULL,
  `progind_periodo_fin` date DEFAULT NULL,
  `progind_proy_actualizacion` date DEFAULT NULL,
  `progind_cal_ind` double(11,2) DEFAULT NULL,
  `progind_cal_pend` int(11) DEFAULT NULL,
  `progind_avance_par_azul` int(11) DEFAULT NULL,
  `progind_avance_par_verde` int(11) DEFAULT NULL,
  `progind_anvance_par_rojo` int(11) DEFAULT NULL,
  `progind_avance_fin_azul` int(11) DEFAULT NULL,
  `progind_avance_fin_verde` int(11) DEFAULT NULL,
  `progind_anvance_fin_rojo` int(11) DEFAULT NULL,
  `progind_fecha_act` datetime DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `progind_fecha_act_color` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`progind_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=492 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prog_indices_pre`
--

DROP TABLE IF EXISTS `prog_indices_pre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prog_indices_pre` (
  `progindpre_pk` int(11) NOT NULL AUTO_INCREMENT,
  `progindpre_progind_fk` int(11) NOT NULL,
  `progindpre_mon_fk` int(11) NOT NULL,
  `progindpre_total` double(11,2) DEFAULT NULL,
  `progindpre_est_pre` int(11) DEFAULT NULL,
  `progindpre_anio` double(11,2) DEFAULT NULL,
  `progindpre_ac` double(11,2) DEFAULT NULL,
  `progindpre_pv` double(11,2) DEFAULT NULL,
  PRIMARY KEY (`progindpre_pk`),
  KEY `prog_indices_pre_moneda_FK` (`progindpre_mon_fk`),
  KEY `prog_indices_pre_prog_indices_FK` (`progindpre_progind_fk`),
  CONSTRAINT `prog_indices_pre_moneda_FK` FOREIGN KEY (`progindpre_mon_fk`) REFERENCES `moneda` (`mon_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prog_indices_pre_prog_indices_FK` FOREIGN KEY (`progindpre_progind_fk`) REFERENCES `prog_indices` (`progind_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=267 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prog_int`
--

DROP TABLE IF EXISTS `prog_int`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prog_int` (
  `progint_prog_pk` int(11) NOT NULL,
  `progint_int_pk` int(11) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`progint_prog_pk`,`progint_int_pk`),
  KEY `fk_PROG_INT_INTERESADOS1_idx` (`progint_int_pk`),
  CONSTRAINT `fk_PROG_INT_INTERESADOS1` FOREIGN KEY (`progint_int_pk`) REFERENCES `interesados` (`int_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROG_INT_PROGRAMAS1` FOREIGN KEY (`progint_prog_pk`) REFERENCES `programas` (`prog_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prog_lectura_area`
--

DROP TABLE IF EXISTS `prog_lectura_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prog_lectura_area` (
  `proglectarea_prog_pk` int(11) NOT NULL,
  `proglectarea_area_pk` int(11) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`proglectarea_prog_pk`,`proglectarea_area_pk`),
  KEY `fk_PROG_LECTURA_AREA_AREAS1_idx` (`proglectarea_area_pk`),
  CONSTRAINT `fk_PROG_LECTURA_AREA_AREAS1` FOREIGN KEY (`proglectarea_area_pk`) REFERENCES `areas` (`area_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROG_LECTURA_AREA_PROGRAMAS1` FOREIGN KEY (`proglectarea_prog_pk`) REFERENCES `programas` (`prog_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prog_pre`
--

DROP TABLE IF EXISTS `prog_pre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prog_pre` (
  `progpre_prog_fk` int(11) NOT NULL,
  `progpre_pre_fk` int(11) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`progpre_prog_fk`),
  KEY `progpre_pre_fk_idx` (`progpre_pre_fk`),
  CONSTRAINT `progpre_pre_fk` FOREIGN KEY (`progpre_pre_fk`) REFERENCES `presupuesto` (`pre_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `progpre_prog_fk` FOREIGN KEY (`progpre_prog_fk`) REFERENCES `programas` (`prog_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prog_tags`
--

DROP TABLE IF EXISTS `prog_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prog_tags` (
  `progtag_prog_pk` int(11) NOT NULL,
  `progtag_area_pk` int(11) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`progtag_prog_pk`,`progtag_area_pk`),
  KEY `fk_PROG_TAGS_AREAS_TAGS1_idx` (`progtag_area_pk`),
  CONSTRAINT `fk_PROG_TAGS_AREAS_TAGS1` FOREIGN KEY (`progtag_area_pk`) REFERENCES `areas_tags` (`arastag_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROG_TAGS_PROGRAMAS1` FOREIGN KEY (`progtag_prog_pk`) REFERENCES `programas` (`prog_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `programas`
--

DROP TABLE IF EXISTS `programas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `programas` (
  `prog_pk` int(11) NOT NULL AUTO_INCREMENT,
  `prog_org_fk` int(11) NOT NULL,
  `prog_area_fk` int(11) NOT NULL,
  `prog_est_fk` int(11) NOT NULL,
  `prog_est_pendiente_fk` int(11) DEFAULT NULL,
  `prog_sol_aceptacion` tinyint(1) DEFAULT NULL,
  `prog_usr_gerente_fk` int(11) NOT NULL,
  `prog_usr_adjunto_fk` int(11) NOT NULL,
  `prog_usr_sponsor_fk` int(11) NOT NULL,
  `prog_usr_pmofed_fk` int(11) DEFAULT NULL,
  `prog_cro_fk` int(11) DEFAULT NULL,
  `prog_pre_fk` int(11) DEFAULT NULL,
  `prog_progindices_fk` int(11) DEFAULT NULL,
  `prog_nombre` varchar(100) DEFAULT NULL,
  `prog_descripcion` varchar(4000) DEFAULT NULL,
  `prog_objetivo` varchar(4000) DEFAULT NULL,
  `prog_obj_publico` varchar(4000) DEFAULT NULL,
  `prog_grp` varchar(45) DEFAULT NULL,
  `prog_semaforo_amarillo` int(11) DEFAULT NULL,
  `prog_semaforo_rojo` int(11) DEFAULT NULL,
  `prog_activo` bit(1) NOT NULL DEFAULT b'1',
  `prog_fecha_crea` date DEFAULT NULL,
  `prog_fecha_act` date NOT NULL,
  `prog_version` int(11) DEFAULT NULL,
  `prog_ult_usuario` varchar(45) DEFAULT NULL,
  `prog_ult_mod` varchar(45) DEFAULT NULL,
  `prog_ult_origen` date DEFAULT NULL,
  `prog_id_migrado` int(11) DEFAULT NULL,
  `prog_obj_est_fk` int(11) DEFAULT NULL,
  `prog_factor_impacto` text,
  `prog_habilitado` bit(1) DEFAULT b'1',
  PRIMARY KEY (`prog_pk`),
  KEY `fk_PROGRAMAS_AREAS1_idx` (`prog_area_fk`),
  KEY `fk_PROGRAMAS_ORGANISMOS1_idx` (`prog_org_fk`),
  KEY `fk_PROGRAMAS_ESTADOS1_idx` (`prog_est_fk`),
  KEY `fk_PROGRAMAS_USUARIOS1_idx` (`prog_usr_gerente_fk`),
  KEY `FKC64199B67D2164DD` (`prog_usr_sponsor_fk`),
  KEY `FKC64199B688211D04` (`prog_usr_pmofed_fk`),
  KEY `FKC64199B6BF64D85C` (`prog_usr_adjunto_fk`),
  KEY `FKC64199B6FE031549` (`prog_usr_gerente_fk`),
  KEY `prog_cro_fk_idx` (`prog_cro_fk`),
  KEY `prog_pre_fk_idx` (`prog_pre_fk`),
  KEY `prog_progindices_fk_idx` (`prog_progindices_fk`),
  KEY `prog_est_pendiente_fk_idx` (`prog_est_pendiente_fk`),
  KEY `prog_nombre_idx` (`prog_nombre`),
  KEY `prog_activo_idx` (`prog_activo`),
  KEY `prog_fecha_crea_idx` (`prog_fecha_crea`),
  KEY `prog_fecha_act_idx` (`prog_fecha_act`),
  KEY `prog_obj_est_fk_idx` (`prog_obj_est_fk`),
  CONSTRAINT `fk_PROGRAMAS_AREAS1` FOREIGN KEY (`prog_area_fk`) REFERENCES `areas` (`area_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROGRAMAS_ESTADOS1` FOREIGN KEY (`prog_est_fk`) REFERENCES `estados` (`est_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROGRAMAS_ORGANISMOS1` FOREIGN KEY (`prog_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROGRAMAS_USUARIOS1` FOREIGN KEY (`prog_usr_gerente_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROGRAMAS_USUARIOS2` FOREIGN KEY (`prog_usr_adjunto_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROGRAMAS_USUARIOS3` FOREIGN KEY (`prog_usr_sponsor_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROGRAMAS_USUARIOS4` FOREIGN KEY (`prog_usr_pmofed_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prog_cro_fk` FOREIGN KEY (`prog_cro_fk`) REFERENCES `cronogramas` (`cro_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prog_est_pendiente_fk` FOREIGN KEY (`prog_est_pendiente_fk`) REFERENCES `estados` (`est_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prog_obj_est_fk` FOREIGN KEY (`prog_obj_est_fk`) REFERENCES `objetivos_estrategicos` (`obj_est_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prog_pre_fk` FOREIGN KEY (`prog_pre_fk`) REFERENCES `prog_pre` (`progpre_prog_fk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prog_progindices_fk` FOREIGN KEY (`prog_progindices_fk`) REFERENCES `prog_indices` (`progind_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=483 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary table structure for view `programas_proyectos`
--

DROP TABLE IF EXISTS `programas_proyectos`;
/*!50001 DROP VIEW IF EXISTS `programas_proyectos`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `programas_proyectos` AS SELECT 
 1 AS `id`,
 1 AS `fichaFk`,
 1 AS `tipoFicha`,
 1 AS `fechaCrea`,
 1 AS `activo`,
 1 AS `organismo`,
 1 AS `nombre`,
 1 AS `estado`,
 1 AS `estadoNombre`,
 1 AS `estadoPendiente`,
 1 AS `areaPk`,
 1 AS `areaNombre`,
 1 AS `solAceptacion`,
 1 AS `gerente`,
 1 AS `gerentePrimerApellido`,
 1 AS `gerentePrimerNombre`,
 1 AS `pmoFederada`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `proy_docs`
--

DROP TABLE IF EXISTS `proy_docs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proy_docs` (
  `proydoc_proy_pk` int(11) NOT NULL,
  `proydoc_doc_pk` int(11) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`proydoc_proy_pk`,`proydoc_doc_pk`),
  KEY `fk_Proy_docs_PROYECTOS1_idx` (`proydoc_doc_pk`),
  CONSTRAINT `fk_Proy_docs_DOCUMENTOS1` FOREIGN KEY (`proydoc_doc_pk`) REFERENCES `documentos` (`docs_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proy_indices`
--

DROP TABLE IF EXISTS `proy_indices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proy_indices` (
  `proyind_pk` int(11) NOT NULL AUTO_INCREMENT,
  `proyind_riesgo_expo` double(11,2) DEFAULT NULL,
  `proyind_riesgo_ultact` date DEFAULT NULL,
  `proyind_riesgo_alto` int(11) DEFAULT NULL,
  `proyind_metodo_estado` double(11,2) DEFAULT NULL,
  `proyind_metodo_sin_aprobar` tinyint(1) DEFAULT NULL,
  `proyind_periodo_inicio` date DEFAULT NULL,
  `proyind_periodo_fin` date DEFAULT NULL,
  `proyind_porc_peso_total` double(11,2) DEFAULT NULL,
  `proyind_cal_ind` double(11,2) DEFAULT NULL,
  `proyind_cal_pend` int(11) DEFAULT NULL,
  `proyind_fase_color` int(11) DEFAULT NULL,
  `proyind_avance_par_azul` int(11) DEFAULT NULL,
  `proyind_avance_par_verde` int(11) DEFAULT NULL,
  `proyind_anvance_par_rojo` int(11) DEFAULT NULL,
  `proyind_avance_fin_azul` int(11) DEFAULT NULL,
  `proyind_avance_fin_verde` int(11) DEFAULT NULL,
  `proyind_anvance_fin_rojo` int(11) DEFAULT NULL,
  `proyind_fecha_act` datetime DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `proyind_periodo_inicio_ent_fk` int(11) DEFAULT NULL,
  `proyind_periodo_fin_ent_fk` int(11) DEFAULT NULL,
  `proyind_fecha_act_color` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`proyind_pk`),
  KEY `proyind_periodo_inicio_idx` (`proyind_periodo_inicio`),
  KEY `proyind_periodo_fin_idx` (`proyind_periodo_fin`),
  KEY `proyind_periodo_fin_ent_fk_idx` (`proyind_periodo_fin_ent_fk`),
  KEY `proyind_periodo_inicio_ent_fk_idx` (`proyind_periodo_inicio_ent_fk`),
  CONSTRAINT `proyind_periodo_fin_ent_fk` FOREIGN KEY (`proyind_periodo_fin_ent_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proyind_periodo_inicio_ent_fk` FOREIGN KEY (`proyind_periodo_inicio_ent_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5518 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proy_indices_pre`
--

DROP TABLE IF EXISTS `proy_indices_pre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proy_indices_pre` (
  `proyindpre_pk` int(11) NOT NULL AUTO_INCREMENT,
  `proyindpre_proyind_fk` int(11) NOT NULL,
  `proyindpre_mon_fk` int(11) NOT NULL,
  `proyindpre_total` double(11,2) DEFAULT NULL,
  `proyindpre_est_pre` int(11) DEFAULT NULL,
  `proyindpre_ac` double(11,2) DEFAULT NULL,
  `proyindpre_pv` double(11,2) DEFAULT NULL,
  `proyindpre_anio` double(11,2) DEFAULT NULL,
  PRIMARY KEY (`proyindpre_pk`),
  KEY `proy_indices_pre_moneda_FK` (`proyindpre_mon_fk`),
  KEY `proy_indices_pre_proy_indices_FK` (`proyindpre_proyind_fk`),
  CONSTRAINT `proy_indices_pre_moneda_FK` FOREIGN KEY (`proyindpre_mon_fk`) REFERENCES `moneda` (`mon_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1307 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proy_int`
--

DROP TABLE IF EXISTS `proy_int`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proy_int` (
  `proyint_proy_pk` int(11) NOT NULL,
  `proyint_int_pk` int(11) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`proyint_proy_pk`,`proyint_int_pk`),
  KEY `fk_PROY_INT_INTERESADOS1_idx` (`proyint_int_pk`),
  CONSTRAINT `fk_PROY_INT_INTERESADOS1` FOREIGN KEY (`proyint_int_pk`) REFERENCES `interesados` (`int_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROY_INT_PROYECTOS1` FOREIGN KEY (`proyint_proy_pk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proy_lectura_area`
--

DROP TABLE IF EXISTS `proy_lectura_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proy_lectura_area` (
  `proglectarea_area_pk` int(11) NOT NULL,
  `proglectarea_proy_pk` int(11) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`proglectarea_area_pk`,`proglectarea_proy_pk`),
  KEY `fk_PROG_LECTURA_AREA_AREAS1_idx` (`proglectarea_area_pk`),
  KEY `fk_PROY_LECTURA_AREA_PROYECTOS1_idx` (`proglectarea_proy_pk`),
  CONSTRAINT `fk_PROG_LECTURA_AREA_AREAS10` FOREIGN KEY (`proglectarea_area_pk`) REFERENCES `areas` (`area_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROY_LECTURA_AREA_PROYECTOS1` FOREIGN KEY (`proglectarea_proy_pk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proy_otros_cat_secundarias`
--

DROP TABLE IF EXISTS `proy_otros_cat_secundarias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proy_otros_cat_secundarias` (
  `proy_cat_proy_otros_fk` int(11) NOT NULL COMMENT 'ID del Proyecto.',
  `proy_cat_cat_proy_fk` int(11) NOT NULL COMMENT 'ID de la categoria de proyectos.',
  PRIMARY KEY (`proy_cat_proy_otros_fk`,`proy_cat_cat_proy_fk`),
  KEY `proy_cat_cat_proy_fk_idx` (`proy_cat_cat_proy_fk`),
  CONSTRAINT `proy_cat_cat_proy_fk` FOREIGN KEY (`proy_cat_cat_proy_fk`) REFERENCES `categoria_proyectos` (`cat_proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_cat_proy_otros_fk` FOREIGN KEY (`proy_cat_proy_otros_fk`) REFERENCES `proy_otros_datos` (`proy_otr_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Relación entre un proyecto y una o varias categorias secundarias.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proy_otros_datos`
--

DROP TABLE IF EXISTS `proy_otros_datos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proy_otros_datos` (
  `proy_otr_pk` int(11) NOT NULL AUTO_INCREMENT,
  `proy_otr_eta_fk` int(11) DEFAULT NULL COMMENT 'Estado (etapa)',
  `proy_otr_cont_fk` int(11) DEFAULT NULL COMMENT 'Contratista Principal',
  `proy_otr_ins_eje_fk` int(11) DEFAULT NULL COMMENT 'Institución Ejecutora',
  `proy_otr_ent_fk` int(11) DEFAULT NULL COMMENT 'Inicio construcción del Producto. Asociado a un Entregable.',
  `proy_otr_origen` varchar(1000) DEFAULT NULL COMMENT 'Origen Principal de los Recursos.',
  `proy_otr_plazo` int(11) DEFAULT NULL COMMENT 'Plazo estimado de obra en días.',
  `proy_otr_observaciones` varchar(4000) DEFAULT NULL COMMENT 'Observaciones.',
  `proy_otr_cat_fk` int(11) DEFAULT NULL COMMENT 'Categoría Principal.',
  `proy_otr_est_pub_fk` int(11) DEFAULT NULL COMMENT 'Estado de Publicación.',
  PRIMARY KEY (`proy_otr_pk`),
  KEY `proy_otr_cont_fk_idx` (`proy_otr_cont_fk`),
  KEY `proy_otr_ins_eje_fk_idx` (`proy_otr_ins_eje_fk`),
  KEY `proy_otr_ent_fk_idx` (`proy_otr_ent_fk`),
  KEY `proy_otr_cat_fk_idx` (`proy_otr_cat_fk`),
  KEY `proy_otr_est_pub_fk_idx` (`proy_otr_est_pub_fk`),
  KEY `proy_otr_eta_fk_idx` (`proy_otr_eta_fk`),
  CONSTRAINT `proy_otr_cat_fk` FOREIGN KEY (`proy_otr_cat_fk`) REFERENCES `categoria_proyectos` (`cat_proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_otr_cont_fk` FOREIGN KEY (`proy_otr_cont_fk`) REFERENCES `organi_int_prove` (`orga_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_otr_ent_fk` FOREIGN KEY (`proy_otr_ent_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_otr_est_pub_fk` FOREIGN KEY (`proy_otr_est_pub_fk`) REFERENCES `estados_publicacion` (`est_pub_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_otr_eta_fk` FOREIGN KEY (`proy_otr_eta_fk`) REFERENCES `etapa` (`eta_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_otr_ins_eje_fk` FOREIGN KEY (`proy_otr_ins_eje_fk`) REFERENCES `organi_int_prove` (`orga_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3667 DEFAULT CHARSET=latin1 COMMENT='Datos del proyecto principalmente para usarse en el visualizador';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proy_pre`
--

DROP TABLE IF EXISTS `proy_pre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proy_pre` (
  `proypre_proy_fk` int(11) NOT NULL,
  `proypre_pre_fk` int(11) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`proypre_proy_fk`),
  KEY `proypre_pre_fk_idx` (`proypre_pre_fk`),
  CONSTRAINT `proypre_pre_fk` FOREIGN KEY (`proypre_pre_fk`) REFERENCES `presupuesto` (`pre_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proypre_proy_fk` FOREIGN KEY (`proypre_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proy_publica_hist`
--

DROP TABLE IF EXISTS `proy_publica_hist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proy_publica_hist` (
  `proy_publica_pk` int(11) NOT NULL AUTO_INCREMENT,
  `proy_publica_fecha` datetime NOT NULL,
  `proy_publica_proy_fk` int(11) NOT NULL,
  `proy_publica_usu_fk` int(11) NOT NULL,
  PRIMARY KEY (`proy_publica_pk`),
  KEY `proy_publica_proy_fk_idx` (`proy_publica_proy_fk`),
  KEY `proy_publica_usu_fk_idx` (`proy_publica_usu_fk`),
  CONSTRAINT `proy_publica_proy_fk` FOREIGN KEY (`proy_publica_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_publica_usu_fk` FOREIGN KEY (`proy_publica_usu_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12708 DEFAULT CHARSET=latin1 COMMENT='Registro de las veces que se publicÃ³ el proyecto en el Visualizador.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proy_replanificacion`
--

DROP TABLE IF EXISTS `proy_replanificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proy_replanificacion` (
  `proyreplan_pk` int(11) NOT NULL AUTO_INCREMENT,
  `proyreplan_proy_fk` int(11) NOT NULL,
  `proyreplan_fecha` date NOT NULL,
  `proyreplan_desc` varchar(5000) NOT NULL,
  `proyreplan_historial` tinyint(1) NOT NULL,
  `proyreplan_activo` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `proyreplan_generar_linea_base` bit(1) DEFAULT NULL,
  `proyreplan_generar_presupuesto` tinyint(1) NOT NULL DEFAULT '0',
  `proyreplan_generar_producto` tinyint(1) NOT NULL DEFAULT '0',
  `proyreplan_permit_editar` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`proyreplan_pk`),
  KEY `proyreplan_proy_fk_idx` (`proyreplan_proy_fk`),
  KEY `proyreplan_fecha_idx` (`proyreplan_fecha`),
  KEY `proyreplan_activo_idx` (`proyreplan_activo`),
  CONSTRAINT `proyreplan_proy_fk` FOREIGN KEY (`proyreplan_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4199 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proy_sitact_historico`
--

DROP TABLE IF EXISTS `proy_sitact_historico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proy_sitact_historico` (
  `proy_sitact_hist_pk` int(11) NOT NULL AUTO_INCREMENT,
  `proy_sitact_fecha` date NOT NULL,
  `proy_sitact_desc` text,
  `proy_sitact_proy_fk` int(11) NOT NULL,
  `proy_sitact_usu_fk` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`proy_sitact_hist_pk`),
  KEY `proy_sitact_proy_fk_idx` (`proy_sitact_proy_fk`),
  KEY `proy_sitact_historico_proy_sitact_usu_fk_IDX` (`proy_sitact_usu_fk`),
  CONSTRAINT `proy_sitact_historico_ss_usuario_FK` FOREIGN KEY (`proy_sitact_usu_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_sitact_proy_fk` FOREIGN KEY (`proy_sitact_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=33978 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proy_tags`
--

DROP TABLE IF EXISTS `proy_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proy_tags` (
  `proytag_proy_pk` int(11) NOT NULL,
  `proytag_area_pk` int(11) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`proytag_proy_pk`,`proytag_area_pk`),
  KEY `fk_PROY_TAGS_PROYECTOS1_idx` (`proytag_area_pk`),
  CONSTRAINT `fk_PROY_TAGS_AREAS_TAGS1` FOREIGN KEY (`proytag_area_pk`) REFERENCES `areas_tags` (`arastag_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROY_TAGS_PROYECTOS1` FOREIGN KEY (`proytag_proy_pk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proyectos`
--

DROP TABLE IF EXISTS `proyectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proyectos` (
  `proy_pk` int(11) NOT NULL AUTO_INCREMENT,
  `proy_org_fk` int(11) NOT NULL,
  `proy_est_fk` int(11) NOT NULL,
  `proy_est_pendiente_fk` int(11) DEFAULT NULL,
  `proy_sol_aceptacion` tinyint(1) DEFAULT NULL,
  `proy_area_fk` int(11) NOT NULL,
  `proy_usr_adjunto_fk` int(11) NOT NULL,
  `proy_usr_sponsor_fk` int(11) NOT NULL,
  `proy_usr_gerente_fk` int(11) NOT NULL,
  `proy_usr_pmofed_fk` int(11) DEFAULT NULL,
  `proy_prog_fk` int(11) DEFAULT NULL,
  `proy_risk_fk` int(11) DEFAULT NULL,
  `proy_cro_fk` int(11) DEFAULT NULL,
  `proy_pre_fk` int(11) DEFAULT NULL,
  `proy_proyindices_fk` int(11) DEFAULT NULL,
  `proy_peso` int(11) DEFAULT NULL,
  `proy_descripcion` text,
  `proy_objetivo` text,
  `proy_obj_publico` text,
  `proy_situacion_actual` text,
  `proy_leccion_aprendida` varchar(256) DEFAULT NULL,
  `proy_nombre` varchar(100) DEFAULT NULL,
  `proy_grp` varchar(45) DEFAULT NULL,
  `proy_semaforo_amarillo` int(11) DEFAULT NULL,
  `proy_semaforo_rojo` int(11) DEFAULT NULL,
  `proy_activo` tinyint(1) DEFAULT NULL,
  `proy_fecha_crea` datetime(6) DEFAULT NULL,
  `proy_fecha_act` datetime(6) DEFAULT NULL,
  `proy_ult_usuario` int(11) DEFAULT NULL,
  `proy_ult_mod` datetime(6) DEFAULT NULL,
  `proy_ult_origen` varchar(45) DEFAULT NULL,
  `proy_version` int(11) DEFAULT NULL,
  `proy_fecha_est_act` datetime(6) DEFAULT NULL,
  `proy_id_migrado` int(11) DEFAULT NULL,
  `proy_publicable` tinyint(1) NOT NULL DEFAULT '1',
  `proy_otr_dat_fk` int(11) DEFAULT NULL,
  `proy_latlng_fk` int(11) DEFAULT NULL,
  `proy_obj_est_fk` int(11) DEFAULT NULL,
  `proy_factor_impacto` text,
  `proy_fecha_act_pub` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`proy_pk`),
  KEY `fk_PROYECTOS_CRONOGRAMAS1_idx` (`proy_cro_fk`),
  KEY `fk_PROYECTOS_RIESGOS1_idx` (`proy_risk_fk`),
  KEY `fk_PROYECTOS_ESTADOS1_idx` (`proy_est_fk`),
  KEY `fk_PROYECTOS_ORGANISMOS1_idx` (`proy_org_fk`),
  KEY `fk_PROYECTOS_AREAS1_idx` (`proy_area_fk`),
  KEY `fk_PROYECTOS_PROGRAMAS1_idx` (`proy_prog_fk`),
  KEY `fk_PROYECTOS_USUARIOS4_idx` (`proy_usr_pmofed_fk`),
  KEY `FKE442A80EDA44AEF7` (`proy_usr_gerente_fk`),
  KEY `FKE442A80ED98E9596` (`proy_usr_pmofed_fk`),
  KEY `FKE442A80E5962FE8B` (`proy_usr_sponsor_fk`),
  KEY `FKE442A80E9BA6720A` (`proy_usr_adjunto_fk`),
  KEY `proy_pre_fk_idx` (`proy_pre_fk`),
  KEY `proy_nombre_idx` (`proy_nombre`),
  KEY `proy_proyindices_fk_idx` (`proy_proyindices_fk`),
  KEY `proy_est_pendiente_fk_idx` (`proy_est_pendiente_fk`),
  KEY `proy_activo_idx` (`proy_activo`),
  KEY `proy_fecha_crea_idx` (`proy_fecha_crea`),
  KEY `proy_fecha_act_idx` (`proy_fecha_act`),
  KEY `proy_publicable_idx` (`proy_publicable`),
  KEY `proy_otr_dat_fk_idx` (`proy_otr_dat_fk`),
  KEY `proy_latlng_fk_idx` (`proy_latlng_fk`),
  KEY `proy_obj_est_fk_idx` (`proy_obj_est_fk`),
  CONSTRAINT `fk_PROYECTOS_AREAS1` FOREIGN KEY (`proy_area_fk`) REFERENCES `areas` (`area_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_CRONOGRAMA` FOREIGN KEY (`proy_cro_fk`) REFERENCES `cronogramas` (`cro_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_ESTADOS1` FOREIGN KEY (`proy_est_fk`) REFERENCES `estados` (`est_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_ORGANISMOS1` FOREIGN KEY (`proy_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_PROGRAMAS1` FOREIGN KEY (`proy_prog_fk`) REFERENCES `programas` (`prog_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_RIESGOS1` FOREIGN KEY (`proy_risk_fk`) REFERENCES `riesgos` (`risk_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_USUARIOS1` FOREIGN KEY (`proy_usr_adjunto_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_USUARIOS2` FOREIGN KEY (`proy_usr_sponsor_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_USUARIOS4` FOREIGN KEY (`proy_usr_pmofed_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_USUARIOS5` FOREIGN KEY (`proy_usr_gerente_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_est_pendiente_fk` FOREIGN KEY (`proy_est_pendiente_fk`) REFERENCES `estados` (`est_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_obj_est_fk` FOREIGN KEY (`proy_obj_est_fk`) REFERENCES `objetivos_estrategicos` (`obj_est_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_otr_dat_fk` FOREIGN KEY (`proy_otr_dat_fk`) REFERENCES `proy_otros_datos` (`proy_otr_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_pre_fk` FOREIGN KEY (`proy_pre_fk`) REFERENCES `presupuesto` (`pre_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_proyindices_fk` FOREIGN KEY (`proy_proyindices_fk`) REFERENCES `proy_indices` (`proyind_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4525 DEFAULT CHARSET=utf8 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proyectos_BORRAR`
--

DROP TABLE IF EXISTS `proyectos_BORRAR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proyectos_BORRAR` (
  `proy_pk` int(11) NOT NULL DEFAULT '0',
  `proy_org_fk` int(11) NOT NULL,
  `proy_est_fk` int(11) NOT NULL,
  `proy_est_pendiente_fk` int(11) DEFAULT NULL,
  `proy_sol_aceptacion` tinyint(1) DEFAULT NULL,
  `proy_area_fk` int(11) NOT NULL,
  `proy_usr_adjunto_fk` int(11) NOT NULL,
  `proy_usr_sponsor_fk` int(11) NOT NULL,
  `proy_usr_gerente_fk` int(11) NOT NULL,
  `proy_usr_pmofed_fk` int(11) DEFAULT NULL,
  `proy_prog_fk` int(11) DEFAULT NULL,
  `proy_risk_fk` int(11) DEFAULT NULL,
  `proy_cro_fk` int(11) DEFAULT NULL,
  `proy_pre_fk` int(11) DEFAULT NULL,
  `proy_proyindices_fk` int(11) DEFAULT NULL,
  `proy_peso` int(11) DEFAULT NULL,
  `proy_descripcion` varchar(4000) CHARACTER SET utf8 DEFAULT NULL,
  `proy_objetivo` varchar(4000) CHARACTER SET utf8 DEFAULT NULL,
  `proy_obj_publico` varchar(4000) CHARACTER SET utf8 DEFAULT NULL,
  `proy_situacion_actual` varchar(4000) CHARACTER SET utf8 DEFAULT NULL,
  `proy_leccion_aprendida` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `proy_nombre` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `proy_grp` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `proy_semaforo_amarillo` int(11) DEFAULT NULL,
  `proy_semaforo_rojo` int(11) DEFAULT NULL,
  `proy_activo` tinyint(1) DEFAULT NULL,
  `proy_fecha_crea` date DEFAULT NULL,
  `proy_fecha_act` date NOT NULL,
  `proy_ult_usuario` int(11) DEFAULT NULL,
  `proy_ult_mod` date DEFAULT NULL,
  `proy_ult_origen` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `proy_version` int(11) DEFAULT NULL,
  `proy_fecha_est_act` date DEFAULT NULL,
  `proy_id_migrado` int(11) DEFAULT NULL,
  `proy_publicable` tinyint(1) NOT NULL DEFAULT '1',
  `proy_otr_dat_fk` int(11) DEFAULT NULL,
  `proy_latlng_fk` int(11) DEFAULT NULL,
  `proy_obj_est_fk` int(11) DEFAULT NULL,
  `proy_factor_impacto` text CHARACTER SET utf8
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `registros_horas`
--

DROP TABLE IF EXISTS `registros_horas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registros_horas` (
  `rh_pk` int(11) NOT NULL AUTO_INCREMENT,
  `rh_usu_fk` int(11) NOT NULL,
  `rh_proy_fk` int(11) NOT NULL,
  `rh_ent_fk` int(11) NOT NULL,
  `rh_fecha` date NOT NULL,
  `rh_horas` decimal(11,2) NOT NULL,
  `rh_comentario` varchar(4000) DEFAULT NULL,
  `rh_aprobado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`rh_pk`),
  KEY `fk_registrohoras_usuarios1` (`rh_usu_fk`),
  KEY `fk_registrohoras_proyectos1` (`rh_proy_fk`),
  KEY `fk_registrohoras_entregables1` (`rh_ent_fk`),
  KEY `rh_fecha_idx` (`rh_fecha`),
  KEY `rh_aprobado_idx` (`rh_aprobado`),
  CONSTRAINT `fk_registrohoras_entregables1` FOREIGN KEY (`rh_ent_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_registrohoras_proyectos1` FOREIGN KEY (`rh_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_registrohoras_usuarios1` FOREIGN KEY (`rh_usu_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `revinfo`
--

DROP TABLE IF EXISTS `revinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `revinfo` (
  `REV` int(11) NOT NULL AUTO_INCREMENT,
  `REVTSTMP` bigint(20) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`REV`)
) ENGINE=InnoDB AUTO_INCREMENT=8036 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `riesgos`
--

DROP TABLE IF EXISTS `riesgos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `riesgos` (
  `risk_pk` int(11) NOT NULL AUTO_INCREMENT,
  `risk_proy_fk` int(11) NOT NULL,
  `risk_nombre` varchar(3000) NOT NULL,
  `risk_fecha_actu` date DEFAULT NULL,
  `risk_probabilidad` int(11) DEFAULT NULL,
  `risk_impacto` int(11) DEFAULT NULL,
  `risk_ent_fk` int(11) DEFAULT NULL,
  `risk_fecha_limite` date DEFAULT NULL,
  `risk_efecto` varchar(3000) DEFAULT NULL,
  `risk_estategia` varchar(3000) DEFAULT NULL,
  `risk_disparador` varchar(3000) DEFAULT NULL,
  `risk_contingencia` varchar(3000) DEFAULT NULL,
  `risk_superado` tinyint(1) DEFAULT NULL,
  `risk_fecha_superado` date DEFAULT NULL,
  `risk_usuario_superado_fk` int(11) DEFAULT NULL,
  `risk_exposicion` double DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`risk_pk`),
  KEY `risk_proy_fk_idx` (`risk_proy_fk`),
  KEY `risk_usuario_superado_fk_fk_idx` (`risk_usuario_superado_fk`),
  KEY `risk_superado_idx` (`risk_superado`),
  KEY `risk_ent_fk_idx` (`risk_ent_fk`),
  KEY `risk_fecha_actu_idx` (`risk_fecha_actu`),
  KEY `risk_fecha_limite_idx` (`risk_fecha_limite`),
  KEY `risk_fecha_superado_idx` (`risk_fecha_superado`),
  CONSTRAINT `riesgos_proyectos_FK` FOREIGN KEY (`risk_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `risk_ent_fk` FOREIGN KEY (`risk_ent_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `risk_usuario_superado_fk_fk` FOREIGN KEY (`risk_usuario_superado_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3403 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles_interesados`
--

DROP TABLE IF EXISTS `roles_interesados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_interesados` (
  `rolint_pk` int(11) NOT NULL AUTO_INCREMENT,
  `rolint_org_fk` int(11) NOT NULL,
  `rolint_nombre` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`rolint_pk`),
  KEY `fk_ROLES_INTERESADOS_ORGANISMOS1_idx` (`rolint_org_fk`),
  CONSTRAINT `fk_ROLES_INTERESADOS_ORGANISMOS1` FOREIGN KEY (`rolint_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles_usuarios`
--

DROP TABLE IF EXISTS `roles_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_usuarios` (
  `rol_pk` int(11) NOT NULL,
  `rol_nombre` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`rol_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_ayuda`
--

DROP TABLE IF EXISTS `ss_ayuda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_ayuda` (
  `ayu_id` int(11) NOT NULL AUTO_INCREMENT,
  `ayu_codigo` varchar(45) DEFAULT NULL,
  `ayu_texto` longtext,
  `ayu_ult_mod_fecha` datetime DEFAULT NULL,
  `ayu_ult_mod_origen` varchar(45) DEFAULT NULL,
  `ayu_ult_mod_usuario` varchar(45) DEFAULT NULL,
  `ayu_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`ayu_id`),
  UNIQUE KEY `ayu_codigo` (`ayu_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_categoper`
--

DROP TABLE IF EXISTS `ss_categoper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_categoper` (
  `cat_id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_descripcion` longtext,
  `cat_nombre` varchar(255) NOT NULL,
  `cat_origen` varchar(255) NOT NULL,
  `cat_user_code` int(11) NOT NULL,
  `cat_version` int(11) DEFAULT NULL,
  `cat_vigente` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_configuraciones`
--

DROP TABLE IF EXISTS `ss_configuraciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_configuraciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cnf_org_fk` int(11) DEFAULT NULL,
  `cnf_codigo` varchar(145) DEFAULT NULL,
  `cnf_descripcion` varchar(245) DEFAULT NULL,
  `cnf_valor` varchar(1000) DEFAULT NULL,
  `cnf_protegido` tinyint(1) DEFAULT NULL,
  `cnf_html` tinyint(1) DEFAULT NULL,
  `cnf_ult_usuario` varchar(45) DEFAULT NULL,
  `cnf_ult_mod` varchar(45) DEFAULT NULL,
  `cnf_ult_origen` date DEFAULT NULL,
  `cnf_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cnf_org_fk` (`cnf_org_fk`),
  KEY `cnf_codigo` (`cnf_codigo`),
  CONSTRAINT `ss_configuraciones_organismos_FK` FOREIGN KEY (`cnf_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1558 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_departamentos`
--

DROP TABLE IF EXISTS `ss_departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_departamentos` (
  `depto_id` int(11) NOT NULL AUTO_INCREMENT,
  `depto_codigo` varchar(255) DEFAULT NULL,
  `depto_nombre` varchar(255) DEFAULT NULL,
  `depto_ult_mod` datetime DEFAULT NULL,
  `err_ult_origen` varchar(255) DEFAULT NULL,
  `depto_ult_usuario` varchar(255) DEFAULT NULL,
  `depto_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`depto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_domicilios`
--

DROP TABLE IF EXISTS `ss_domicilios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_domicilios` (
  `dom_id` int(11) NOT NULL AUTO_INCREMENT,
  `dom_apto` varchar(50) DEFAULT NULL,
  `dom_letra` varchar(255) DEFAULT NULL,
  `dom_calle` varchar(150) DEFAULT NULL,
  `dom_codigo_postal` varchar(5) DEFAULT NULL,
  `dom_depto_alt` varchar(255) DEFAULT NULL,
  `dom_descripcion` longtext,
  `dom_inmueble_nombre` varchar(100) DEFAULT NULL,
  `dom_kilometro` varchar(9) DEFAULT NULL,
  `dom_manzana` varchar(5) DEFAULT NULL,
  `dom_numero_puerta` varchar(5) DEFAULT NULL,
  `dom_oficina` varchar(255) DEFAULT NULL,
  `dom_ruta` varchar(5) DEFAULT NULL,
  `dom_solar` varchar(5) DEFAULT NULL,
  `dom_ult_mod` datetime DEFAULT NULL,
  `dom_ult_origen` varchar(255) DEFAULT NULL,
  `dom_ult_usuario` varchar(255) DEFAULT NULL,
  `dom_version` int(11) DEFAULT NULL,
  `dom_depto_id` int(11) DEFAULT NULL,
  `dom_loc_id` int(11) DEFAULT NULL,
  `dom_pai_id` int(11) DEFAULT NULL,
  `dom_par_id` smallint(6) DEFAULT NULL,
  `dom_tec_id` int(11) DEFAULT NULL,
  `dom_tvi_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`dom_id`),
  KEY `FK2D1C5EEDFCA9657E` (`dom_tec_id`),
  KEY `FK2D1C5EED2F892058` (`dom_pai_id`),
  KEY `FK2D1C5EEDFBC55737` (`dom_par_id`),
  KEY `FK2D1C5EED567EC7E` (`dom_tvi_id`),
  KEY `FK2D1C5EEDC8B0CD82` (`dom_loc_id`),
  KEY `FK2D1C5EED2FF8B8CF` (`dom_depto_id`),
  CONSTRAINT `FK2D1C5EED2F892058` FOREIGN KEY (`dom_pai_id`) REFERENCES `ss_paises` (`pai_id`),
  CONSTRAINT `FK2D1C5EED2FF8B8CF` FOREIGN KEY (`dom_depto_id`) REFERENCES `ss_departamentos` (`depto_id`),
  CONSTRAINT `FK2D1C5EED567EC7E` FOREIGN KEY (`dom_tvi_id`) REFERENCES `ss_tipos_vialidad` (`tvi_id`),
  CONSTRAINT `FK2D1C5EEDC8B0CD82` FOREIGN KEY (`dom_loc_id`) REFERENCES `ss_localidades` (`loc_id`),
  CONSTRAINT `FK2D1C5EEDFBC55737` FOREIGN KEY (`dom_par_id`) REFERENCES `ss_paridades` (`par_id`),
  CONSTRAINT `FK2D1C5EEDFCA9657E` FOREIGN KEY (`dom_tec_id`) REFERENCES `ss_tipos_entrada_colectiva` (`tec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_errores`
--

DROP TABLE IF EXISTS `ss_errores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_errores` (
  `id` int(11) NOT NULL,
  `err_codigo` varchar(255) DEFAULT NULL,
  `err_descripcion` varchar(255) DEFAULT NULL,
  `err_ult_mod` datetime DEFAULT NULL,
  `err_ult_origen` varchar(255) DEFAULT NULL,
  `err_ult_usuario` varchar(255) DEFAULT NULL,
  `err_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_localidades`
--

DROP TABLE IF EXISTS `ss_localidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_localidades` (
  `loc_id` int(11) NOT NULL AUTO_INCREMENT,
  `loc_codigo` varchar(255) DEFAULT NULL,
  `loc_nombre` varchar(255) DEFAULT NULL,
  `loc_ult_mod` datetime DEFAULT NULL,
  `loc_ult_origen` varchar(255) DEFAULT NULL,
  `loc_ult_usuario` varchar(255) DEFAULT NULL,
  `loc_version` int(11) DEFAULT NULL,
  `loc_depto_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`loc_id`),
  KEY `FKEF82E378E2A77891` (`loc_depto_id`),
  CONSTRAINT `FKEF82E378E2A77891` FOREIGN KEY (`loc_depto_id`) REFERENCES `ss_departamentos` (`depto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_noticias`
--

DROP TABLE IF EXISTS `ss_noticias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_noticias` (
  `not_id` int(11) NOT NULL AUTO_INCREMENT,
  `not_ampliar` varchar(255) DEFAULT NULL,
  `not_codigo` varchar(45) DEFAULT NULL,
  `not_contenido` longtext,
  `not_desde` datetime DEFAULT NULL,
  `not_hasta` datetime DEFAULT NULL,
  `not_imagen` varchar(255) DEFAULT NULL,
  `not_titulo` varchar(255) DEFAULT NULL,
  `not_ult_mod_fecha` datetime DEFAULT NULL,
  `not_ult_mod_origen` varchar(45) DEFAULT NULL,
  `not_ult_mod_usuario` varchar(45) DEFAULT NULL,
  `not_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`not_id`),
  UNIQUE KEY `not_codigo` (`not_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary table structure for view `ss_oficina`
--

DROP TABLE IF EXISTS `ss_oficina`;
/*!50001 DROP VIEW IF EXISTS `ss_oficina`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `ss_oficina` AS SELECT 
 1 AS `ofi_id`,
 1 AS `ofi_nombre`,
 1 AS `ofi_activo`,
 1 AS `ofi_fecha_creacion`,
 1 AS `ofi_origen`,
 1 AS `ofi_usuario`,
 1 AS `ofi_version`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `ss_operacion`
--

DROP TABLE IF EXISTS `ss_operacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_operacion` (
  `ope_id` int(11) NOT NULL AUTO_INCREMENT,
  `ope_codigo` varchar(255) NOT NULL,
  `ope_descripcion` longtext NOT NULL,
  `ope_nombre` varchar(255) NOT NULL,
  `ope_origen` varchar(255) NOT NULL,
  `ope_tipocampo` varchar(255) NOT NULL,
  `ope_user_code` int(11) NOT NULL,
  `ope_version` int(11) DEFAULT NULL,
  `ope_vigente` tinyint(1) NOT NULL,
  `ope_categoria_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ope_id`),
  KEY `FK61DDABF9269E90AA` (`ope_categoria_id`),
  CONSTRAINT `FK61DDABF9269E90AA` FOREIGN KEY (`ope_categoria_id`) REFERENCES `ss_categoper` (`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_paises`
--

DROP TABLE IF EXISTS `ss_paises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_paises` (
  `pai_id` int(11) NOT NULL AUTO_INCREMENT,
  `pai_codigo2` varchar(255) DEFAULT NULL,
  `pai_codigo3` varchar(255) DEFAULT NULL,
  `pai_comun` tinyint(1) DEFAULT NULL,
  `pai_habilitado` tinyint(1) DEFAULT NULL,
  `pai_nombre` varchar(255) DEFAULT NULL,
  `pai_ult_mod` datetime DEFAULT NULL,
  `pai_ult_origen` varchar(255) DEFAULT NULL,
  `pai_ult_usuario` varchar(255) DEFAULT NULL,
  `pai_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`pai_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_paridades`
--

DROP TABLE IF EXISTS `ss_paridades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_paridades` (
  `par_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `par_codigo` varchar(9) DEFAULT NULL,
  `par_descripcion` varchar(45) DEFAULT NULL,
  `par_ult_mod_fecha` datetime DEFAULT NULL,
  `par_ult_mod_origen` varchar(45) DEFAULT NULL,
  `par_ult_mod_usuario` varchar(45) DEFAULT NULL,
  `par_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`par_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_personas`
--

DROP TABLE IF EXISTS `ss_personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_personas` (
  `per_id` int(11) NOT NULL AUTO_INCREMENT,
  `per_fec_nac` datetime DEFAULT NULL,
  `per_nro_doc` varchar(45) DEFAULT NULL,
  `per_primer_apellido` varchar(100) NOT NULL,
  `per_primer_nombre` varchar(100) DEFAULT NULL,
  `per_segundo_apellido` varchar(100) NOT NULL,
  `per_segundo_nombre` varchar(100) DEFAULT NULL,
  `per_ult_mod_fecha` datetime DEFAULT NULL,
  `per_ult_mod_origen` varchar(45) DEFAULT NULL,
  `per_ult_mod_usuario` varchar(45) DEFAULT NULL,
  `per_version` int(11) DEFAULT NULL,
  `per_pais_doc` int(11) DEFAULT NULL,
  `per_tipo_doc` int(11) DEFAULT NULL,
  PRIMARY KEY (`per_id`),
  KEY `FKBF897546F9B07F4F` (`per_pais_doc`),
  KEY `FKBF89754648010DF9` (`per_tipo_doc`),
  CONSTRAINT `FKBF89754648010DF9` FOREIGN KEY (`per_tipo_doc`) REFERENCES `ss_tipos_documento_persona` (`tipdocper_id`),
  CONSTRAINT `FKBF897546F9B07F4F` FOREIGN KEY (`per_pais_doc`) REFERENCES `ss_paises` (`pai_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_rel_rol_operacion`
--

DROP TABLE IF EXISTS `ss_rel_rol_operacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_rel_rol_operacion` (
  `rel_rol_operacion_id` int(11) NOT NULL AUTO_INCREMENT,
  `rel_rol_operacion_editable` tinyint(1) NOT NULL,
  `rel_rol_operacion_origen` varchar(255) NOT NULL,
  `rel_rol_operacion_user_code` int(11) NOT NULL,
  `rel_rol_operacion_visible` tinyint(1) NOT NULL,
  `rel_rol_operacion_operacion_id` int(11) NOT NULL,
  `rel_rol_operacion_rol_id` int(11) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`rel_rol_operacion_id`),
  KEY `FK6E9F4363BDC3F659` (`rel_rol_operacion_operacion_id`),
  KEY `FK6E9F4363C25B4A39` (`rel_rol_operacion_rol_id`),
  CONSTRAINT `FK6E9F4363BDC3F659` FOREIGN KEY (`rel_rol_operacion_operacion_id`) REFERENCES `ss_operacion` (`ope_id`),
  CONSTRAINT `FK6E9F4363C25B4A39` FOREIGN KEY (`rel_rol_operacion_rol_id`) REFERENCES `ss_rol` (`rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_rol`
--

DROP TABLE IF EXISTS `ss_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_rol` (
  `rol_id` int(11) NOT NULL AUTO_INCREMENT,
  `rol_cod` varchar(255) NOT NULL,
  `rol_descripcion` longtext,
  `rol_nombre` varchar(255) NOT NULL,
  `rol_label` varchar(150) NOT NULL,
  `rol_origen` varchar(255) NOT NULL,
  `rol_user_code` int(11) NOT NULL,
  `rol_version` int(11) DEFAULT NULL,
  `rol_vigente` tinyint(1) NOT NULL,
  `rol_tipo_usuario` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`rol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_telefonos`
--

DROP TABLE IF EXISTS `ss_telefonos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_telefonos` (
  `tel_id` int(11) NOT NULL AUTO_INCREMENT,
  `tel_numero` varchar(25) DEFAULT NULL,
  `tel_observaciones` varchar(255) DEFAULT NULL,
  `tel_prefijo` varchar(10) DEFAULT NULL,
  `tel_ult_mod` datetime DEFAULT NULL,
  `tel_ult_origen` varchar(45) DEFAULT NULL,
  `tel_ult_usuario` varchar(45) DEFAULT NULL,
  `tel_version` int(11) DEFAULT NULL,
  `tel_tiptel_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`tel_id`),
  KEY `FK2CEF84E0641CB6FC` (`tel_tiptel_id`),
  CONSTRAINT `FK2CEF84E0641CB6FC` FOREIGN KEY (`tel_tiptel_id`) REFERENCES `ss_tipos_telefono` (`tipTel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_tipos_documento_persona`
--

DROP TABLE IF EXISTS `ss_tipos_documento_persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_tipos_documento_persona` (
  `tipdocper_id` int(11) NOT NULL AUTO_INCREMENT,
  `tipdocper_codigo` varchar(255) DEFAULT NULL,
  `tipdocper_descripcion` varchar(255) DEFAULT NULL,
  `tipdocper_habilitado` tinyint(1) DEFAULT NULL,
  `tipdocper_ult_mod` datetime DEFAULT NULL,
  `tipdocper_ult_origen` varchar(255) DEFAULT NULL,
  `tipdocper_ult_usuario` varchar(255) DEFAULT NULL,
  `tipdocper_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`tipdocper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_tipos_entrada_colectiva`
--

DROP TABLE IF EXISTS `ss_tipos_entrada_colectiva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_tipos_entrada_colectiva` (
  `tec_id` int(11) NOT NULL AUTO_INCREMENT,
  `tec_codigo` varchar(255) DEFAULT NULL,
  `tec_descripcion` varchar(255) DEFAULT NULL,
  `tec_ult_mod` datetime DEFAULT NULL,
  `tec_ult_origen` varchar(255) DEFAULT NULL,
  `tec_ult_usuario` varchar(255) DEFAULT NULL,
  `tec_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`tec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_tipos_telefono`
--

DROP TABLE IF EXISTS `ss_tipos_telefono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_tipos_telefono` (
  `tipTel_id` int(11) NOT NULL AUTO_INCREMENT,
  `tipTel_codigo` varchar(255) DEFAULT NULL,
  `tipTel_descripcion` varchar(255) DEFAULT NULL,
  `tipTel_habilitado` tinyint(1) DEFAULT NULL,
  `tipTel_ult_mod` datetime DEFAULT NULL,
  `tipTel_ult_origen` varchar(255) DEFAULT NULL,
  `tipTel_ult_usuario` varchar(255) DEFAULT NULL,
  `tipTel_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`tipTel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_tipos_vialidad`
--

DROP TABLE IF EXISTS `ss_tipos_vialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_tipos_vialidad` (
  `tvi_id` int(11) NOT NULL AUTO_INCREMENT,
  `tvi_abreviacion` varchar(255) DEFAULT NULL,
  `tvi_codigo` varchar(255) DEFAULT NULL,
  `tvi_descripcion` varchar(255) DEFAULT NULL,
  `tvi_ult_mod` datetime DEFAULT NULL,
  `tvi_ult_origen` varchar(255) DEFAULT NULL,
  `tvi_ult_usuario` varchar(255) DEFAULT NULL,
  `tvi_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`tvi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_usu_ofi_roles`
--

DROP TABLE IF EXISTS `ss_usu_ofi_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_usu_ofi_roles` (
  `usu_ofi_roles_id` int(11) NOT NULL AUTO_INCREMENT,
  `usu_ofi_roles_origen` varchar(255) NOT NULL,
  `usu_ofi_roles_user_code` int(11) NOT NULL,
  `usu_ofi_roles_oficina` int(11) DEFAULT NULL,
  `usu_ofi_roles_rol` int(11) NOT NULL,
  `usu_ofi_roles_usuario` int(11) NOT NULL,
  `usu_ofi_roles_usu_area` int(11) DEFAULT NULL,
  `usu_ofi_roles_activo` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`usu_ofi_roles_id`),
  KEY `FKB64469698D342B69` (`usu_ofi_roles_rol`),
  KEY `FKB64469698353C0A7` (`usu_ofi_roles_usuario`),
  KEY `usu_ofi_roles_usu_area_idx` (`usu_ofi_roles_usu_area`),
  CONSTRAINT `FKB64469698353C0A7` FOREIGN KEY (`usu_ofi_roles_usuario`) REFERENCES `ss_usuario` (`usu_id`),
  CONSTRAINT `FKB64469698D342B69` FOREIGN KEY (`usu_ofi_roles_rol`) REFERENCES `ss_rol` (`rol_id`),
  CONSTRAINT `usu_ofi_roles_usu_area` FOREIGN KEY (`usu_ofi_roles_usu_area`) REFERENCES `areas` (`area_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2825 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_usuario`
--

DROP TABLE IF EXISTS `ss_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_usuario` (
  `usu_id` int(11) NOT NULL AUTO_INCREMENT,
  `usu_administrador` tinyint(1) DEFAULT NULL,
  `usu_cambio_estado_desc` longtext COLLATE utf8_spanish_ci,
  `usu_cod` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `usu_correo_electronico` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `usu_cuenta_bloqueada` smallint(6) DEFAULT NULL,
  `usu_descripcion` longtext COLLATE utf8_spanish_ci,
  `usu_direccion` longtext COLLATE utf8_spanish_ci,
  `usu_fecha_password` datetime DEFAULT NULL,
  `usu_fecha_uuid` datetime DEFAULT NULL,
  `usu_foto` longblob,
  `usu_intentos_fallidos` int(11) DEFAULT NULL,
  `usu_nro_doc` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `usu_oficina_por_defecto` int(11) DEFAULT NULL,
  `usu_origen` longtext COLLATE utf8_spanish_ci,
  `usu_password` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `usu_primer_apellido` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `usu_primer_nombre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `usu_registrado` tinyint(1) DEFAULT NULL,
  `usu_segundo_apellido` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `usu_segundo_nombre` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `usu_telefono` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `usu_celular` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `usu_user_code` int(11) DEFAULT NULL,
  `usu_uuid_des` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `usu_version` int(11) DEFAULT NULL,
  `usu_vigente` tinyint(1) NOT NULL,
  `usu_aprob_facturas` tinyint(1) DEFAULT NULL,
  `usu_ult_usuario` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `usu_ult_mod` datetime DEFAULT NULL,
  `usu_ult_origen` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `usu_area_org` int(11) DEFAULT NULL,
  `usu_token` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `usu_token_act` datetime DEFAULT NULL,
  `usu_ldap_user` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`usu_id`),
  UNIQUE KEY `usu_ldap_user_UNIQUE` (`usu_ldap_user`),
  KEY `usu_cod_index` (`usu_cod`),
  KEY `usu_area_org_idx` (`usu_area_org`),
  KEY `usu_correo_electronico_idx` (`usu_correo_electronico`),
  KEY `usu_token_idx` (`usu_token`),
  CONSTRAINT `usu_area_org` FOREIGN KEY (`usu_area_org`) REFERENCES `areas` (`area_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1077 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ss_usuarios_datos`
--

DROP TABLE IF EXISTS `ss_usuarios_datos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_usuarios_datos` (
  `ss_usu_dat_pk` int(11) NOT NULL AUTO_INCREMENT,
  `ss_usu_dat_area_fk` int(11) NOT NULL,
  `ss_usu_dat_usu_fk` int(11) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`ss_usu_dat_pk`),
  KEY `ss_usuarios_datos_ss_usu_dat_area_fk_IDX` (`ss_usu_dat_area_fk`),
  KEY `ss_usuarios_datos_ss_usu_dat_usu_fk_IDX` (`ss_usu_dat_usu_fk`),
  CONSTRAINT `ss_usuarios_datos_areas_FK` FOREIGN KEY (`ss_usu_dat_area_fk`) REFERENCES `areas` (`area_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ss_usuarios_datos_ss_usuario_FK` FOREIGN KEY (`ss_usu_dat_usu_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temas_calidad`
--

DROP TABLE IF EXISTS `temas_calidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temas_calidad` (
  `tca_pk` int(11) NOT NULL AUTO_INCREMENT,
  `tca_nombre` varchar(100) NOT NULL,
  `tca_org_fk` int(11) NOT NULL,
  PRIMARY KEY (`tca_pk`),
  KEY `tca_org_fk_idx` (`tca_org_fk`),
  CONSTRAINT `tca_org_fk` FOREIGN KEY (`tca_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipo_documento`
--

DROP TABLE IF EXISTS `tipo_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_documento` (
  `tipdoc_pk` int(11) NOT NULL AUTO_INCREMENT,
  `tipodoc_nombre` varchar(145) NOT NULL,
  `tipodoc_exigido_desde` int(11) DEFAULT NULL,
  `tipodoc_peso` int(11) DEFAULT NULL,
  `tipodoc_org_fk` int(11) DEFAULT NULL,
  `tipodoc_resum_ejecutivo` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`tipdoc_pk`),
  KEY `tipodoc_org_fk_idx` (`tipodoc_org_fk`),
  CONSTRAINT `tipodoc_org_fk` FOREIGN KEY (`tipodoc_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipo_documento_instancia`
--

DROP TABLE IF EXISTS `tipo_documento_instancia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_documento_instancia` (
  `tipodoc_inst_pk` int(11) NOT NULL AUTO_INCREMENT,
  `tipodoc_inst_tipodoc_fk` int(11) NOT NULL,
  `tipodoc_inst_exigido_desde` int(11) DEFAULT NULL,
  `tipodoc_inst_peso` int(11) DEFAULT NULL,
  `tipodoc_inst_proy_fk` int(11) DEFAULT NULL,
  `tipodoc_inst_prog_fk` int(11) DEFAULT NULL,
  `tipodoc_inst_resum_ejecutivo` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`tipodoc_inst_pk`),
  KEY `tipodoc_inst_tipodoc_fk_idx` (`tipodoc_inst_tipodoc_fk`),
  KEY `tipo_documento_instancia_estados_FK` (`tipodoc_inst_exigido_desde`),
  KEY `tipo_documento_instancia_programas_FK` (`tipodoc_inst_prog_fk`),
  KEY `tipo_documento_instancia_proyectos_FK` (`tipodoc_inst_proy_fk`),
  CONSTRAINT `tipo_documento_instancia_estados_FK` FOREIGN KEY (`tipodoc_inst_exigido_desde`) REFERENCES `estados` (`est_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tipo_documento_instancia_programas_FK` FOREIGN KEY (`tipodoc_inst_prog_fk`) REFERENCES `programas` (`prog_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tipo_documento_instancia_proyectos_FK` FOREIGN KEY (`tipodoc_inst_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tipodoc_inst_tipodoc_fk` FOREIGN KEY (`tipodoc_inst_tipodoc_fk`) REFERENCES `tipo_documento` (`tipdoc_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=60714 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipo_gasto`
--

DROP TABLE IF EXISTS `tipo_gasto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_gasto` (
  `tipogas_pk` int(11) NOT NULL AUTO_INCREMENT,
  `tipogas_org_fk` int(11) DEFAULT NULL,
  `tipogas_nombre` varchar(150) NOT NULL,
  PRIMARY KEY (`tipogas_pk`),
  KEY `tipogas_org_fk_idx` (`tipogas_org_fk`),
  CONSTRAINT `tipogas_org_fk` FOREIGN KEY (`tipogas_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipo_leccion`
--

DROP TABLE IF EXISTS `tipo_leccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_leccion` (
  `tipolec_pk` int(11) NOT NULL AUTO_INCREMENT,
  `tipolec_codigo` varchar(45) NOT NULL,
  `tipolec_nombre` varchar(150) NOT NULL,
  PRIMARY KEY (`tipolec_pk`),
  KEY `tipolec_codigo` (`tipolec_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipos_adquisicion`
--

DROP TABLE IF EXISTS `tipos_adquisicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_adquisicion` (
  `tip_adq_pk` int(11) NOT NULL AUTO_INCREMENT,
  `tip_adq_nombre` varchar(100) NOT NULL,
  `tip_adq_descripcion` varchar(300) NOT NULL,
  `tip_adq_habilitado` tinyint(3) NOT NULL DEFAULT '1',
  `tip_adq_org_fk` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`tip_adq_pk`),
  KEY `fk_tip_adq_org` (`tip_adq_org_fk`),
  CONSTRAINT `tipos_adquisicion_ibfk_1` FOREIGN KEY (`tip_adq_org_fk`) REFERENCES `organismos` (`org_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipos_media`
--

DROP TABLE IF EXISTS `tipos_media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_media` (
  `tip_pk` int(11) NOT NULL AUTO_INCREMENT,
  `tip_cod` varchar(45) DEFAULT NULL,
  `tip_nombre` varchar(245) DEFAULT NULL,
  PRIMARY KEY (`tip_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `valor_calidad_codigos`
--

DROP TABLE IF EXISTS `valor_calidad_codigos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `valor_calidad_codigos` (
  `vca_pk` int(11) NOT NULL AUTO_INCREMENT,
  `vca_codigo` varchar(45) NOT NULL,
  `vca_nombre` varchar(100) NOT NULL,
  `vca_habilitado` tinyint(1) NOT NULL,
  PRIMARY KEY (`vca_pk`),
  KEY `vca_codigo` (`vca_codigo`),
  KEY `vca_habilitado_idx` (`vca_habilitado`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `valor_hora`
--

DROP TABLE IF EXISTS `valor_hora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `valor_hora` (
  `val_hor_pk` int(11) NOT NULL AUTO_INCREMENT,
  `val_hor_usu_fk` int(11) NOT NULL,
  `val_hor_org_fk` int(11) NOT NULL,
  `val_hor_mon_fk` int(11) NOT NULL,
  `val_hor_valor` decimal(11,2) NOT NULL,
  `val_hor_anio` int(11) NOT NULL,
  PRIMARY KEY (`val_hor_pk`),
  KEY `val_hor_usu_fk_idx` (`val_hor_usu_fk`),
  KEY `val_hor_org_fk_idx` (`val_hor_org_fk`),
  KEY `val_hor_mon_fk_idx` (`val_hor_mon_fk`),
  KEY `val_hor_anio_idx` (`val_hor_anio`),
  CONSTRAINT `val_hor_mon_fk` FOREIGN KEY (`val_hor_mon_fk`) REFERENCES `moneda` (`mon_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `val_hor_org_fk` FOREIGN KEY (`val_hor_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `val_hor_usu_fk` FOREIGN KEY (`val_hor_usu_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'siges'
--

--
-- Final view structure for view `REVINFO`
--

/*!50001 DROP VIEW IF EXISTS `REVINFO`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `REVINFO` AS select `revinfo`.`REV` AS `REV`,`revinfo`.`REVTSTMP` AS `REVTSTMP`,`revinfo`.`version` AS `version` from `revinfo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `programas_proyectos`
--

/*!50001 DROP VIEW IF EXISTS `programas_proyectos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `programas_proyectos` AS select concat('1-',`programas`.`prog_pk`) AS `id`,`programas`.`prog_pk` AS `fichaFk`,1 AS `tipoFicha`,`programas`.`prog_fecha_crea` AS `fechaCrea`,`programas`.`prog_activo` AS `activo`,`programas`.`prog_org_fk` AS `organismo`,`programas`.`prog_nombre` AS `nombre`,`estados`.`est_pk` AS `estado`,`estados`.`est_nombre` AS `estadoNombre`,`programas`.`prog_est_pendiente_fk` AS `estadoPendiente`,`areas`.`area_pk` AS `areaPk`,`areas`.`area_nombre` AS `areaNombre`,`programas`.`prog_sol_aceptacion` AS `solAceptacion`,`programas`.`prog_usr_gerente_fk` AS `gerente`,`ss_usuario`.`usu_primer_apellido` AS `gerentePrimerApellido`,`ss_usuario`.`usu_primer_nombre` AS `gerentePrimerNombre`,`programas`.`prog_usr_pmofed_fk` AS `pmoFederada` from (((`programas` join `estados` on((`programas`.`prog_est_fk` = `estados`.`est_pk`))) join `areas` on((`programas`.`prog_area_fk` = `areas`.`area_pk`))) join `ss_usuario` on((`programas`.`prog_usr_gerente_fk` = `ss_usuario`.`usu_id`))) union select concat('2-',`proyectos`.`proy_pk`) AS `id`,`proyectos`.`proy_pk` AS `fichaFk`,2 AS `tipoFicha`,`proyectos`.`proy_fecha_crea` AS `fechaCrea`,`proyectos`.`proy_activo` AS `activo`,`proyectos`.`proy_org_fk` AS `organismo`,`proyectos`.`proy_nombre` AS `nombre`,`estados`.`est_pk` AS `estado`,`estados`.`est_nombre` AS `estadoNombre`,`proyectos`.`proy_est_pendiente_fk` AS `estadoPendiente`,`areas`.`area_pk` AS `areaPk`,`areas`.`area_nombre` AS `areaNombre`,`proyectos`.`proy_sol_aceptacion` AS `solAceptacion`,`proyectos`.`proy_usr_gerente_fk` AS `gerente`,`ss_usuario`.`usu_primer_apellido` AS `gerentePrimerApellido`,`ss_usuario`.`usu_primer_nombre` AS `gerentePrimerNombre`,`proyectos`.`proy_usr_pmofed_fk` AS `pmoFederada` from (((`proyectos` join `estados` on((`proyectos`.`proy_est_fk` = `estados`.`est_pk`))) join `areas` on((`proyectos`.`proy_area_fk` = `areas`.`area_pk`))) join `ss_usuario` on((`proyectos`.`proy_usr_gerente_fk` = `ss_usuario`.`usu_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `ss_oficina`
--

/*!50001 DROP VIEW IF EXISTS `ss_oficina`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ss_oficina` AS select `organismos`.`org_pk` AS `ofi_id`,`organismos`.`org_nombre` AS `ofi_nombre`,`organismos`.`org_activo` AS `ofi_activo`,now() AS `ofi_fecha_creacion`,'' AS `ofi_origen`,1 AS `ofi_usuario`,1 AS `ofi_version` from `organismos` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-15 14:05:12
