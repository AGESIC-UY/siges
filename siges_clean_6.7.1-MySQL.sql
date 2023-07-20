-- MySQL dump 10.13  Distrib 5.6.45, for Linux (x86_64)
--
-- Host: localhost    Database: siges_clean
-- ------------------------------------------------------
-- Server version	5.6.45

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
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

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
  `adq_id_grp_erp_fk` int(11) DEFAULT NULL,
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
  KEY `fk_adq_id_grp_erp` (`adq_id_grp_erp_fk`),
  CONSTRAINT `adq_comp_prod_fk` FOREIGN KEY (`adq_componente_producto_fk`) REFERENCES `componente_producto` (`com_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `adq_fuente` FOREIGN KEY (`adq_fuente_fk`) REFERENCES `fuente_financiamiento` (`fue_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `adq_moneda` FOREIGN KEY (`adq_moneda_fk`) REFERENCES `moneda` (`mon_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `adq_pre_fk` FOREIGN KEY (`adq_pre_fk`) REFERENCES `presupuesto` (`pre_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `adq_proc_comp_fk` FOREIGN KEY (`adq_procedimiento_compra_fk`) REFERENCES `procedimiento_compra` (`proc_comp_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `adq_prov_orga` FOREIGN KEY (`adq_prov_orga_fk`) REFERENCES `organi_int_prove` (`orga_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `adquisicion_ibfk_1` FOREIGN KEY (`adq_id_grp_erp_fk`) REFERENCES `identificadores_grp_erp` (`id_grp_erp_pk`),
  CONSTRAINT `adquisicion_ss_usuario_FK` FOREIGN KEY (`adq_compartida_usuario_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_adquisicion_causal` FOREIGN KEY (`adq_causal_compra_fk`) REFERENCES `causales_compra` (`cau_com_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_adquisicion_centro_costo` FOREIGN KEY (`adq_centro_costo_fk`) REFERENCES `centros_costo` (`cen_cos_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_adquisicion_tipo_adquisicion` FOREIGN KEY (`adq_tipo_adquisicion_fk`) REFERENCES `tipos_adquisicion` (`tip_adq_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adquisicion`
--

LOCK TABLES `adquisicion` WRITE;
/*!40000 ALTER TABLE `adquisicion` DISABLE KEYS */;
/*!40000 ALTER TABLE `adquisicion` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ambito`
--

LOCK TABLES `ambito` WRITE;
/*!40000 ALTER TABLE `ambito` DISABLE KEYS */;
/*!40000 ALTER TABLE `ambito` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area_conocimiento`
--

LOCK TABLES `area_conocimiento` WRITE;
/*!40000 ALTER TABLE `area_conocimiento` DISABLE KEYS */;
INSERT INTO `area_conocimiento` VALUES (1,'PMI - Áreas de Conocimiento',47,NULL),(3,'Alcance',47,1),(4,'Plazos',47,1),(5,'Costos',47,1),(6,'Riesgos',47,1),(7,'Calidad',47,1),(8,'Comunicaciones',47,1),(9,'Recursos Humanos',47,1),(10,'Adquisiciones',47,1),(11,'Interesados',47,1),(12,'Integración',47,1);
/*!40000 ALTER TABLE `area_conocimiento` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `area_organi_int_prove`
--

LOCK TABLES `area_organi_int_prove` WRITE;
/*!40000 ALTER TABLE `area_organi_int_prove` DISABLE KEYS */;
/*!40000 ALTER TABLE `area_organi_int_prove` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=354 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areas`
--

LOCK TABLES `areas` WRITE;
/*!40000 ALTER TABLE `areas` DISABLE KEYS */;
INSERT INTO `areas` VALUES (350,45,NULL,'Dirección General','DG',NULL,0,1,1),(352,47,NULL,'OrganismoPorDefecto','PR',NULL,0,1,1),(353,47,NULL,'Secretaría General','SG',NULL,0,1,1);
/*!40000 ALTER TABLE `areas` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areas_tags`
--

LOCK TABLES `areas_tags` WRITE;
/*!40000 ALTER TABLE `areas_tags` DISABLE KEYS */;
INSERT INTO `areas_tags` VALUES (64,47,NULL,'Innovación',NULL,'Innovación','Innovación',0),(65,47,NULL,'Ubicación Geográfica',NULL,'Ubicación Geográfica','Ubicación Geográfica',0),(66,47,NULL,'Organismo que Participa',NULL,'Organismo que Participa','Organismo que Participa',0),(67,47,NULL,'Tecnología',NULL,'Tecnología','Tecnología',0),(68,47,NULL,'Diseño',NULL,'Diseño','Diseño',0),(69,47,NULL,'PGE',NULL,'PGE','PGE',0),(70,47,NULL,'Seguridad',NULL,'Seguridad','Seguridad',0),(71,47,64,'Razón',NULL,'Razón','Razón',0),(72,47,64,'Tipo',NULL,'Tipo','Tipo',0),(73,47,64,'Originalidad',NULL,'Originalidad','Originalidad',0),(74,47,65,'Interior',NULL,'Interior','Interior',0),(75,47,65,'Montevideo.',1,'Montevideo.','Montevideo',0),(76,47,65,'Uruguay',NULL,'Uruguay','Uruguay',0),(77,47,65,'América Latina y el Caribe',NULL,'América Latina y el Caribe','América Latina y el Caribe',0),(78,47,65,'Mundial',NULL,'Mundial','Mundial',0),(79,47,66,'Administración Central.',1,'Administración Central.','Administración Central',0),(80,47,66,'Organismo Descentralizado',NULL,'Organismo Descentralizado','Organismo Descentralizado',0),(81,47,66,'Intendencia',NULL,'Intendencia','Intendencia',0),(82,47,66,'Organismo Multilateral',NULL,'Organismo Multilateral','Organismo Multilateral',0),(83,47,66,'Organización Privada',NULL,'Organización Privada','Organización Privada',0),(84,47,66,'ONG',NULL,'ONG','ONG',0),(85,47,66,'Ente Autónomo',NULL,'Ente Autónomo','Ente Autónomo',0),(86,47,66,'Poder Judicial',NULL,'Poder Judicial','Poder Judicial',0),(87,47,66,'Poder Legislativo',NULL,'Poder Legislativo','Poder Legislativo',0),(88,47,66,'Universidad',NULL,'Universidad','Universidad',0),(89,47,66,'Múltiple organismo',NULL,'Múltiple organismo','Múltiple organismo',0),(90,47,67,'Java',NULL,'Java','Java',0),(91,47,67,'Python',NULL,'Python','Python',0),(92,47,67,'C / C++',NULL,'C / C++','C / C++',0),(93,47,67,'Portales Virtuales',NULL,'Portales Virtuales','Portales Virtuales',0),(94,47,68,'Responsiva',NULL,'Responsiva','Responsiva',0),(95,47,68,'Usabilidad',NULL,'Usabilidad','Usabilidad',0),(96,47,69,'Control de Acceso',NULL,'Control de Acceso','Control de Acceso',0),(97,47,69,'Metadatos',NULL,'Metadatos','Metadatos',0),(98,47,69,'Cloud',NULL,'Cloud','Cloud',0),(99,47,69,'Publica Servicios en PGE',NULL,'Publica Servicios en PGE','Publica Servicios en PGE',0),(100,47,69,'Consume Servicios de PGE',NULL,'Consume Servicios de PGE','Consume Servicios de PGE',0),(101,47,69,'Middleware',NULL,'Middleware','Middleware',0),(102,47,69,'Publica Datos Abiertos',NULL,'Publica Datos Abiertos','Publica Datos Abiertos',0),(103,47,70,'SSL / HTTPS',NULL,'SSL / HTTPS','SSL / HTTPS',0),(104,47,70,'Firma Electrónica Avanzada',NULL,'Firma Electrónica Avanzada','Firma Electrónica Avanzada',0),(105,47,70,'Single Sign On',NULL,'Single Sign On','Single Sign On',0),(106,47,70,'Identificación Electrónica',NULL,'Identificación Electrónica','Identificación Electrónica',0),(107,47,70,'Políticas - normas',NULL,'Políticas - normas','Políticas - normas',0),(108,47,70,'Monitoreo / Incidentes',NULL,'Monitoreo / Incidentes','Monitoreo / Incidentes',0);
/*!40000 ALTER TABLE `areas_tags` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_doc_file`
--

LOCK TABLES `aud_doc_file` WRITE;
/*!40000 ALTER TABLE `aud_doc_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_doc_file` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_pge_configuraciones`
--

LOCK TABLES `aud_pge_configuraciones` WRITE;
/*!40000 ALTER TABLE `aud_pge_configuraciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_pge_configuraciones` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_programas`
--

LOCK TABLES `aud_programas` WRITE;
/*!40000 ALTER TABLE `aud_programas` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_programas` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_programas_proyectos`
--

LOCK TABLES `aud_programas_proyectos` WRITE;
/*!40000 ALTER TABLE `aud_programas_proyectos` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_programas_proyectos` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_ayuda`
--

LOCK TABLES `aud_ss_ayuda` WRITE;
/*!40000 ALTER TABLE `aud_ss_ayuda` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_ayuda` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_categoper`
--

LOCK TABLES `aud_ss_categoper` WRITE;
/*!40000 ALTER TABLE `aud_ss_categoper` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_categoper` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_configuraciones`
--

LOCK TABLES `aud_ss_configuraciones` WRITE;
/*!40000 ALTER TABLE `aud_ss_configuraciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_configuraciones` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_departamentos`
--

LOCK TABLES `aud_ss_departamentos` WRITE;
/*!40000 ALTER TABLE `aud_ss_departamentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_departamentos` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_domicilios`
--

LOCK TABLES `aud_ss_domicilios` WRITE;
/*!40000 ALTER TABLE `aud_ss_domicilios` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_domicilios` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_errores`
--

LOCK TABLES `aud_ss_errores` WRITE;
/*!40000 ALTER TABLE `aud_ss_errores` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_errores` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_localidades`
--

LOCK TABLES `aud_ss_localidades` WRITE;
/*!40000 ALTER TABLE `aud_ss_localidades` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_localidades` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_noticias`
--

LOCK TABLES `aud_ss_noticias` WRITE;
/*!40000 ALTER TABLE `aud_ss_noticias` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_noticias` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_oficina`
--

LOCK TABLES `aud_ss_oficina` WRITE;
/*!40000 ALTER TABLE `aud_ss_oficina` DISABLE KEYS */;
INSERT INTO `aud_ss_oficina` VALUES (46,2,1,'2019-11-21 14:54:30','OrganismoPorDefecto','',1,1,1),(47,4,1,NULL,NULL,NULL,0,NULL,NULL),(47,5,1,'2019-11-22 09:31:28','OrganismoPorDefecto','',1,1,0),(47,7,1,NULL,NULL,NULL,0,NULL,NULL),(47,9,1,NULL,NULL,NULL,0,NULL,NULL),(47,12,1,NULL,NULL,NULL,0,NULL,NULL),(47,14,1,NULL,NULL,NULL,0,NULL,NULL);
/*!40000 ALTER TABLE `aud_ss_oficina` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_operacion`
--

LOCK TABLES `aud_ss_operacion` WRITE;
/*!40000 ALTER TABLE `aud_ss_operacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_operacion` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_paises`
--

LOCK TABLES `aud_ss_paises` WRITE;
/*!40000 ALTER TABLE `aud_ss_paises` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_paises` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_paridades`
--

LOCK TABLES `aud_ss_paridades` WRITE;
/*!40000 ALTER TABLE `aud_ss_paridades` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_paridades` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_personas`
--

LOCK TABLES `aud_ss_personas` WRITE;
/*!40000 ALTER TABLE `aud_ss_personas` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_personas` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_rel_rol_operacion`
--

LOCK TABLES `aud_ss_rel_rol_operacion` WRITE;
/*!40000 ALTER TABLE `aud_ss_rel_rol_operacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_rel_rol_operacion` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_rol`
--

LOCK TABLES `aud_ss_rol` WRITE;
/*!40000 ALTER TABLE `aud_ss_rol` DISABLE KEYS */;
INSERT INTO `aud_ss_rol` VALUES (93,14,1,'DIR',NULL,'Director','SIGES_WEB',0,NULL,1,1,'rol_dir'),(97,7,1,'PMOF',NULL,'PMO Federada','SIGES_WEB',0,NULL,1,1,'rol_pmof'),(97,9,1,'PMOF',NULL,'PMO Federada','SIGES_WEB',0,NULL,1,1,'rol_pmof'),(98,2,1,'PMOT',NULL,'PMO Transversal','SIGES_WEB',0,NULL,1,1,'rol_pmot'),(98,4,1,'PMOT',NULL,'PMO Transversal','SIGES_WEB',0,NULL,1,1,'rol_pmot'),(98,5,1,'PMOT',NULL,'PMO Transversal','SIGES_WEB',0,NULL,1,1,'rol_pmot'),(100,12,1,'USU',NULL,'Usuario','SIGES_WEB',0,NULL,1,1,'rol_usu');
/*!40000 ALTER TABLE `aud_ss_rol` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_telefonos`
--

LOCK TABLES `aud_ss_telefonos` WRITE;
/*!40000 ALTER TABLE `aud_ss_telefonos` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_telefonos` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_tipos_documento_persona`
--

LOCK TABLES `aud_ss_tipos_documento_persona` WRITE;
/*!40000 ALTER TABLE `aud_ss_tipos_documento_persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_tipos_documento_persona` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_tipos_entrada_colectiva`
--

LOCK TABLES `aud_ss_tipos_entrada_colectiva` WRITE;
/*!40000 ALTER TABLE `aud_ss_tipos_entrada_colectiva` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_tipos_entrada_colectiva` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_tipos_telefono`
--

LOCK TABLES `aud_ss_tipos_telefono` WRITE;
/*!40000 ALTER TABLE `aud_ss_tipos_telefono` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_tipos_telefono` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_tipos_vialidad`
--

LOCK TABLES `aud_ss_tipos_vialidad` WRITE;
/*!40000 ALTER TABLE `aud_ss_tipos_vialidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_tipos_vialidad` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_usu_ofi_roles`
--

LOCK TABLES `aud_ss_usu_ofi_roles` WRITE;
/*!40000 ALTER TABLE `aud_ss_usu_ofi_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_usu_ofi_roles` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `aud_ss_usuario`
--

LOCK TABLES `aud_ss_usuario` WRITE;
/*!40000 ALTER TABLE `aud_ss_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `aud_ss_usuario` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `busq_filtro`
--

LOCK TABLES `busq_filtro` WRITE;
/*!40000 ALTER TABLE `busq_filtro` DISABLE KEYS */;
INSERT INTO `busq_filtro` VALUES (1,1286,47,'<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<filtroInicioTO>\n    <nivel>1</nivel>\n    <porArea>false</porArea>\n    <nombre></nombre>\n    <interesadoNombre></interesadoNombre>\n    <estados xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">2</estados>\n    <estados xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">3</estados>\n    <estados xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">4</estados>\n    <estados xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">1</estados>\n    <anioDesde>0</anioDesde>\n    <anioHasta>0</anioHasta>\n    <gradoRiesgo xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">1</gradoRiesgo>\n    <gradoRiesgo xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">2</gradoRiesgo>\n    <gradoRiesgo xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">3</gradoRiesgo>\n    <cantidadRiesgosAltos>0</cantidadRiesgosAltos>\n    <indicadorAvance xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">1</indicadorAvance>\n    <indicadorAvance xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">2</indicadorAvance>\n    <activo>true</activo>\n    <codigo>0</codigo>\n    <orderBy>2</orderBy>\n    <configuracion>\n        <entry>\n            <key>CALIDAD_LIMITE_AMARILLO</key>\n            <value>\n                <cnfCodigo>CALIDAD_LIMITE_AMARILLO</cnfCodigo>\n                <cnfDescripcion>Semaforo limite amarillo calidad</cnfDescripcion>\n                <cnfOrgFk>\n                    <orgPk>47</orgPk>\n                    <orgNombre>OrganismoPorDefecto</orgNombre>\n                    <orgDireccion>Torre Ejecutiva</orgDireccion>\n                    <orgActivo>true</orgActivo>\n                    <orgToken>visua</orgToken>\n                </cnfOrgFk>\n                <cnfUltMod>2019-11-21T16:20:43-02:00</cnfUltMod>\n                <cnfValor>70</cnfValor>\n                <cnfVersion>1</cnfVersion>\n                <id>2790</id>\n            </value>\n        </entry>\n        <entry>\n            <key>RIESGO_INDICE_LIMITE_AMARILLO</key>\n            <value>\n                <cnfCodigo>RIESGO_INDICE_LIMITE_AMARILLO</cnfCodigo>\n                <cnfDescripcion></cnfDescripcion>\n                <cnfOrgFk>\n                    <orgPk>47</orgPk>\n                    <orgNombre>OrganismoPorDefecto</orgNombre>\n                    <orgDireccion>Torre Ejecutiva</orgDireccion>\n                    <orgActivo>true</orgActivo>\n                    <orgToken>visua</orgToken>\n                </cnfOrgFk>\n                <cnfUltMod>2019-11-21T16:20:43-02:00</cnfUltMod>\n                <cnfValor>1.2</cnfValor>\n                <cnfVersion>1</cnfVersion>\n                <id>2767</id>\n            </value>\n        </entry>\n        <entry>\n            <key>RIESGO_INDICE_LIMITE_ROJO</key>\n            <value>\n                <cnfCodigo>RIESGO_INDICE_LIMITE_ROJO</cnfCodigo>\n                <cnfDescripcion></cnfDescripcion>\n                <cnfOrgFk>\n                    <orgPk>47</orgPk>\n                    <orgNombre>OrganismoPorDefecto</orgNombre>\n                    <orgDireccion>Torre Ejecutiva</orgDireccion>\n                    <orgActivo>true</orgActivo>\n                    <orgToken>visua</orgToken>\n                </cnfOrgFk>\n                <cnfUltMod>2019-11-21T16:20:43-02:00</cnfUltMod>\n                <cnfValor>4</cnfValor>\n                <cnfVersion>1</cnfVersion>\n                <id>2768</id>\n            </value>\n        </entry>\n        <entry>\n            <key>CALIDAD_LIMITE_ROJO</key>\n            <value>\n                <cnfCodigo>CALIDAD_LIMITE_ROJO</cnfCodigo>\n                <cnfDescripcion>Semaforo limite rojo calidad</cnfDescripcion>\n                <cnfOrgFk>\n                    <orgPk>47</orgPk>\n                    <orgNombre>OrganismoPorDefecto</orgNombre>\n                    <orgDireccion>Torre Ejecutiva</orgDireccion>\n                    <orgActivo>true</orgActivo>\n                    <orgToken>visua</orgToken>\n                </cnfOrgFk>\n                <cnfUltMod>2019-11-21T16:20:43-02:00</cnfUltMod>\n                <cnfValor>30</cnfValor>\n                <cnfVersion>1</cnfVersion>\n                <id>2791</id>\n            </value>\n        </entry>\n    </configuracion>\n</filtroInicioTO>\n',0);
/*!40000 ALTER TABLE `busq_filtro` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calidad`
--

LOCK TABLES `calidad` WRITE;
/*!40000 ALTER TABLE `calidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `calidad` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Categoria de los Proyectos. Principalmente para usar con el Visualizador.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_proyectos`
--

LOCK TABLES `categoria_proyectos` WRITE;
/*!40000 ALTER TABLE `categoria_proyectos` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoria_proyectos` ENABLE KEYS */;
UNLOCK TABLES;

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
  `cau_com_org_fk` int(11) NOT NULL,
  PRIMARY KEY (`cau_com_pk`),
  KEY `fk_cau_com_org` (`cau_com_org_fk`),
  CONSTRAINT `causales_compra_ibfk_1` FOREIGN KEY (`cau_com_org_fk`) REFERENCES `organismos` (`org_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `causales_compra`
--

LOCK TABLES `causales_compra` WRITE;
/*!40000 ALTER TABLE `causales_compra` DISABLE KEYS */;
INSERT INTO `causales_compra` VALUES (1,'BID-Continuación del servicio','',1,47),(2,'BID-Estandarización de equipo','',1,47),(3,'RRGG-Equipo patentado o marca registrada','',1,47),(4,'RRGG-Organismo público','',1,47);
/*!40000 ALTER TABLE `causales_compra` ENABLE KEYS */;
UNLOCK TABLES;

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
  `cen_cos_org_fk` int(11) NOT NULL,
  PRIMARY KEY (`cen_cos_pk`),
  KEY `fk_cen_cos_org` (`cen_cos_org_fk`),
  CONSTRAINT `centros_costo_ibfk_1` FOREIGN KEY (`cen_cos_org_fk`) REFERENCES `organismos` (`org_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centros_costo`
--

LOCK TABLES `centros_costo` WRITE;
/*!40000 ALTER TABLE `centros_costo` DISABLE KEYS */;
INSERT INTO `centros_costo` VALUES (1,'Comunidades Digitales','',1,47),(2,'Datacenter','',1,47),(3,'Difusión de Políticas','',1,47),(4,'Calidad de Software','',1,47),(5,'Comunicación','',1,47),(6,'Gestión de Datos','',1,47),(7,'Investigación','',1,47);
/*!40000 ALTER TABLE `centros_costo` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `componente_producto`
--

LOCK TABLES `componente_producto` WRITE;
/*!40000 ALTER TABLE `componente_producto` DISABLE KEYS */;
INSERT INTO `componente_producto` VALUES (1,'BID2677 04.01',47,NULL,'Adquisición de imágenes'),(2,'BID2677 04.02',47,NULL,'Control de imágenes'),(3,'BID2677 04.03',47,NULL,'Cartografía'),(4,'BID2677 04.04',47,NULL,'Control de cartografía'),(5,'BID2677 04.05',47,NULL,'Almacenamiento'),(6,'BID2677 04.06',47,NULL,'Geoplataforma'),(7,'BID2677 04.07',47,NULL,'Gestión');
/*!40000 ALTER TABLE `componente_producto` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cronogramas`
--

LOCK TABLES `cronogramas` WRITE;
/*!40000 ALTER TABLE `cronogramas` DISABLE KEYS */;
INSERT INTO `cronogramas` VALUES (1,0,NULL,NULL,1,1,0),(2,0,NULL,NULL,1,1,0),(3,6,NULL,NULL,1,0,0);
/*!40000 ALTER TABLE `cronogramas` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `departamentos`
--

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devengado`
--

LOCK TABLES `devengado` WRITE;
/*!40000 ALTER TABLE `devengado` DISABLE KEYS */;
/*!40000 ALTER TABLE `devengado` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doc_file`
--

LOCK TABLES `doc_file` WRITE;
/*!40000 ALTER TABLE `doc_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `doc_file` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentos`
--

LOCK TABLES `documentos` WRITE;
/*!40000 ALTER TABLE `documentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `documentos` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ent_hist_linea_base`
--

LOCK TABLES `ent_hist_linea_base` WRITE;
/*!40000 ALTER TABLE `ent_hist_linea_base` DISABLE KEYS */;
/*!40000 ALTER TABLE `ent_hist_linea_base` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entregables`
--

LOCK TABLES `entregables` WRITE;
/*!40000 ALTER TABLE `entregables` DISABLE KEYS */;
INSERT INTO `entregables` VALUES (1,3,7,'Tarea 4',NULL,1,'STATUS_ACTIVE',0,1579186800000,16,1580482800000,'',0,0,0,NULL,1289,10,NULL,0,NULL,'6',0,'',100,0,0,'\0','\0'),(2,3,6,'Tarea 3',NULL,1,'STATUS_ACTIVE',0,1577890800000,15,1579100400000,'',0,0,0,NULL,1289,15,NULL,0,NULL,'',0,'',0,0,0,'\0','\0'),(3,3,5,'Fase 2','',1,'STATUS_ACTIVE',0,1577890800000,31,1580482800000,NULL,0,0,0,NULL,1289,1,NULL,0,NULL,'2',0,'',100,0,0,'\0','\0'),(4,3,4,'Tarea 2','',2,'STATUS_ACTIVE',0,1574434800000,40,1577804400000,NULL,0,0,0,NULL,1289,30,NULL,0,NULL,'',0,'',0,0,0,'\0','\0'),(5,3,3,'Tarea 1','',2,'STATUS_ACTIVE',0,1574434800000,13,1575471600000,NULL,0,0,0,NULL,1289,10,NULL,0,NULL,'',0,'',0,0,0,'\0','\0'),(6,3,2,'Fase 1','',1,'STATUS_ACTIVE',1,1574434800000,40,1577804400000,NULL,0,0,0,NULL,1289,0,NULL,0,NULL,'',0,'',0,0,0,'\0','\0'),(7,3,1,'Herramientas de monitoreo del Plan Nacional de Eficiencia Energética','',0,'STATUS_ACTIVE',1,1574434800000,71,1580482800000,NULL,0,0,0,NULL,1289,0,NULL,0,NULL,'',0,'',0,0,0,'\0','\0');
/*!40000 ALTER TABLE `entregables` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados`
--

LOCK TABLES `estados` WRITE;
/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` VALUES (0,'NO_EXIGIDO','No Exigido','',NULL,0),(1,'PENDIENTE','Pendiente','',1,0),(2,'INICIO','Inicio','',2,0),(3,'PLANIFICACION','Planificacion','',3,0),(4,'EJECUCION','Ejecucion','',4,0),(5,'FINALIZADO','Finalizado','',5,0),(11,'PENDIENTE_PMOT','Pendiente PMO T.','',-1,0),(12,'PENDIENTE_PMOF','Pendiente PMO F.','',0,0),(41,'SOLICITUD_FINALIZADO_PMOF','Solicitud Finalizado PMO F.','',NULL,0),(42,'SOLICITUD_FINALIZADO_PMOT','Solicitud Finalizado PMO T.','',NULL,0),(61,'SOLICITUD_CANCELAR_PMOT','Solicitud Cancelar PMO T.','',NULL,0);
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COMMENT='Estados de la publicaci�n de un proyecto en el visualizador.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados_publicacion`
--

LOCK TABLES `estados_publicacion` WRITE;
/*!40000 ALTER TABLE `estados_publicacion` DISABLE KEYS */;
INSERT INTO `estados_publicacion` VALUES (1,'NO_ES_PARA_PUBLICAR','No es para publicar'),(2,'PENDIENTE_CARGAR','Pendiente de cargar datos'),(3,'PENDIENTE_PUBLICAR','Pendiente de publicar'),(4,'PUBLICADO','Publicado');
/*!40000 ALTER TABLE `estados_publicacion` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=latin1 COMMENT='Son los estados del proyecto que se van a exportar al visualizador.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etapa`
--

LOCK TABLES `etapa` WRITE;
/*!40000 ALTER TABLE `etapa` DISABLE KEYS */;
INSERT INTO `etapa` VALUES (177,45,'PROYECTADO','Proyectado',NULL),(178,45,'EN_ADJUDICACION','En adjudicación',NULL),(179,45,'EN_EJECUCION','En Ejecución',NULL),(180,45,'FINALIZADO','Finalizado',NULL),(185,47,'PROYECTADO','Proyectado',NULL),(186,47,'EN_ADJUDICACION','En adjudicación',NULL),(187,47,'EN_EJECUCION','En Ejecución',NULL),(188,47,'FINALIZADO','Finalizado',NULL);
/*!40000 ALTER TABLE `etapa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etiqueta`
--

DROP TABLE IF EXISTS `etiqueta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `etiqueta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eti_org_fk` int(11) NOT NULL,
  `eti_codigo` varchar(145) NOT NULL,
  `eti_valor` varchar(1000) NOT NULL,
  `eti_version` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `eti_org_unique` (`eti_org_fk`,`eti_codigo`),
  CONSTRAINT `etiqueta_organismo_FK` FOREIGN KEY (`eti_org_fk`) REFERENCES `organismos` (`org_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etiqueta`
--

LOCK TABLES `etiqueta` WRITE;
/*!40000 ALTER TABLE `etiqueta` DISABLE KEYS */;
/*!40000 ALTER TABLE `etiqueta` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `fue_pro_com_cau_com`
--

LOCK TABLES `fue_pro_com_cau_com` WRITE;
/*!40000 ALTER TABLE `fue_pro_com_cau_com` DISABLE KEYS */;
/*!40000 ALTER TABLE `fue_pro_com_cau_com` ENABLE KEYS */;
UNLOCK TABLES;

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
  `fue_habilitada` tinyint(3) NOT NULL,
  PRIMARY KEY (`fue_pk`),
  KEY `fue_org_fk_idx` (`fue_org_fk`),
  CONSTRAINT `fue_org_fk` FOREIGN KEY (`fue_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuente_financiamiento`
--

LOCK TABLES `fuente_financiamiento` WRITE;
/*!40000 ALTER TABLE `fuente_financiamiento` DISABLE KEYS */;
INSERT INTO `fuente_financiamiento` VALUES (1,'BID',47,0,1),(2,'N/A - desarrollo interno',47,0,1),(3,'PNUD',47,0,1),(4,'RRGG',47,0,1);
/*!40000 ALTER TABLE `fuente_financiamiento` ENABLE KEYS */;
UNLOCK TABLES;

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
  `fue_pro_com_org_fk` int(11) NOT NULL,
  PRIMARY KEY (`fue_pro_com_pk`),
  KEY `fk_fue_pro_com_org` (`fue_pro_com_org_fk`),
  CONSTRAINT `fuentes_procedimiento_compra_ibfk_1` FOREIGN KEY (`fue_pro_com_org_fk`) REFERENCES `organismos` (`org_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuentes_procedimiento_compra`
--

LOCK TABLES `fuentes_procedimiento_compra` WRITE;
/*!40000 ALTER TABLE `fuentes_procedimiento_compra` DISABLE KEYS */;
INSERT INTO `fuentes_procedimiento_compra` VALUES (1,'BID','CD',1,47),(2,'BM','CD',1,47),(3,'RRGG','CDE',1,47);
/*!40000 ALTER TABLE `fuentes_procedimiento_compra` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gastos`
--

LOCK TABLES `gastos` WRITE;
/*!40000 ALTER TABLE `gastos` DISABLE KEYS */;
/*!40000 ALTER TABLE `gastos` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1,0);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

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
  `id_grp_erp_org_fk` int(11) NOT NULL,
  PRIMARY KEY (`id_grp_erp_pk`),
  KEY `fk_id_grp_erp_org` (`id_grp_erp_org_fk`),
  CONSTRAINT `identificadores_grp_erp_ibfk_1` FOREIGN KEY (`id_grp_erp_org_fk`) REFERENCES `organismos` (`org_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identificadores_grp_erp`
--

LOCK TABLES `identificadores_grp_erp` WRITE;
/*!40000 ALTER TABLE `identificadores_grp_erp` DISABLE KEYS */;
INSERT INTO `identificadores_grp_erp` VALUES (1,'Número de compra SAP','sap',1,47);
/*!40000 ALTER TABLE `identificadores_grp_erp` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interesados`
--

LOCK TABLES `interesados` WRITE;
/*!40000 ALTER TABLE `interesados` DISABLE KEYS */;
/*!40000 ALTER TABLE `interesados` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `latlng_proyectos`
--

LOCK TABLES `latlng_proyectos` WRITE;
/*!40000 ALTER TABLE `latlng_proyectos` DISABLE KEYS */;
/*!40000 ALTER TABLE `latlng_proyectos` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `lecapr_areacon`
--

LOCK TABLES `lecapr_areacon` WRITE;
/*!40000 ALTER TABLE `lecapr_areacon` DISABLE KEYS */;
/*!40000 ALTER TABLE `lecapr_areacon` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecc_aprendidas`
--

LOCK TABLES `lecc_aprendidas` WRITE;
/*!40000 ALTER TABLE `lecc_aprendidas` DISABLE KEYS */;
/*!40000 ALTER TABLE `lecc_aprendidas` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineabase_historico`
--

LOCK TABLES `lineabase_historico` WRITE;
/*!40000 ALTER TABLE `lineabase_historico` DISABLE KEYS */;
/*!40000 ALTER TABLE `lineabase_historico` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=242 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mails_template`
--

LOCK TABLES `mails_template` WRITE;
/*!40000 ALTER TABLE `mails_template` DISABLE KEYS */;
INSERT INTO `mails_template` VALUES (227,'MAIL_SOL_APROBACION',45,'Solicitud de Aprobación','<h2>Solicitud de Aprobación</h2><p>Se generó una solicitud de cambio de estado para el #TIPO_PROG_PROY# \"#NOMBRE_PROG_PROY#\".</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>',0),(228,'MAIL_CAMBIO_CONTRASENIA',45,'Cambio de contraseña en SIGES','Estimado #NOMBRE#, se ha cambiado su contraseña en SIGES por #CONTRASENIA#',0),(229,'MAIL_CAMBIO_ESTADO',45,'Programa / Proyecto cambió de fase.','<h2>Cambio de Fase</h2><p>El #TIPO_PROG_PROY# #ID_PROG_PROY# \"#NOMBRE_PROG_PROY#\" cambió de fase a #FASE_PROG_PROY#.</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>',0),(230,'MAIL_PROG_PROY_PENDIENTE',45,'Pendiente de aprobación.','<h2>Pendiente de aprobación</h2><p>El #TIPO_PROG_PROY# #ID_PROG_PROY# \"#NOMBRE_PROG_PROY#\" esta pendiente de aprobación.</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>',0),(231,'MAIL_NVO_USUARIO',45,'Usuario SIGES.','<h2>Usuario creado</h2><p>Se ha creado el usuario #USU_MAIL# cuya clave es #USU_PASSWORD#, para ingresar al sistema de SIGES.</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>',0),(237,'MAIL_SOL_APROBACION',47,'Solicitud de Aprobación','<h2>Solicitud de Aprobación</h2><p>Se generó una solicitud de cambio de estado para el #TIPO_PROG_PROY# \"#NOMBRE_PROG_PROY#\".</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>',0),(238,'MAIL_CAMBIO_CONTRASENIA',47,'Cambio de contraseña en SIGES','Estimado #NOMBRE#, se ha cambiado su contraseña en SIGES por #CONTRASENIA#',0),(239,'MAIL_CAMBIO_ESTADO',47,'Programa / Proyecto cambió de fase.','<h2>Cambio de Fase</h2><p>El #TIPO_PROG_PROY# #ID_PROG_PROY# \"#NOMBRE_PROG_PROY#\" cambió de fase a #FASE_PROG_PROY#.</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>',0),(240,'MAIL_PROG_PROY_PENDIENTE',47,'Pendiente de aprobación.','<h2>Pendiente de aprobación</h2><p>El #TIPO_PROG_PROY# #ID_PROG_PROY# \"#NOMBRE_PROG_PROY#\" esta pendiente de aprobación.</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>',0),(241,'MAIL_NVO_USUARIO',47,'Usuario SIGES.','<h2>Usuario creado</h2><p>Se ha creado el usuario #USU_MAIL# cuya clave es #USU_PASSWORD#, para ingresar al sistema de SIGES.</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>',0);
/*!40000 ALTER TABLE `mails_template` ENABLE KEYS */;
UNLOCK TABLES;

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
  `media_principal` tinyint(1) DEFAULT NULL COMMENT 'en caso de ser una imagen es la que se utlizar� para la vista rapida y para la seccion de destacados',
  `media_orden` int(11) DEFAULT NULL COMMENT 'EL orde de aparici�n del media en la galeria que lo esta desplegando.',
  `media_usr_pub_fk` int(11) DEFAULT NULL COMMENT 'EL c�digo del usuario que public� el media',
  `media_pub_fecha` datetime DEFAULT NULL COMMENT 'Fecha y hora de publicaci�n',
  `media_usr_mod_fk` int(11) DEFAULT NULL COMMENT 'EL usuario que creo el media',
  `media_mod_fecha` datetime DEFAULT NULL COMMENT 'La fecha de creaci�n del media',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_proyectos`
--

LOCK TABLES `media_proyectos` WRITE;
/*!40000 ALTER TABLE `media_proyectos` DISABLE KEYS */;
/*!40000 ALTER TABLE `media_proyectos` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `moneda`
--

LOCK TABLES `moneda` WRITE;
/*!40000 ALTER TABLE `moneda` DISABLE KEYS */;
INSERT INTO `moneda` VALUES (1,'Pesos','$',NULL,0),(2,'Dólares','U$S',NULL,0),(3,'Euros','€','',0),(4,'Unidad Reajustable','UR','UR',0),(5,'Unidades Indexadas','UI','UI',0);
/*!40000 ALTER TABLE `moneda` ENABLE KEYS */;
UNLOCK TABLES;

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
  UNIQUE KEY `not_unique_key` (`not_org_fk`,`not_cod`),
  KEY `notificacion_not_org_fk_IDX` (`not_org_fk`),
  CONSTRAINT `notificacion_organismos_FK` FOREIGN KEY (`not_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=660 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificacion`
--

LOCK TABLES `notificacion` WRITE;
/*!40000 ALTER TABLE `notificacion` DISABLE KEYS */;
INSERT INTO `notificacion` VALUES (618,45,'Riesgos1','Se pasa a amarillo el semáforo de actualización de riesgos.',NULL,1,0,0,0,'Para el proyecto #NOMBRE_PROYECTO# existen riesgos en estado de atención.'),(619,45,'Riesgos2','Se registra un nuevo riesgo y este cae en la zona roja.',NULL,1,0,0,0,'Para el proyecto #NOMBRE_PROYECTO# existen riesgos en estado de alerta.'),(620,45,'inicio','Hace mucho tiempo que está en inicio.',NULL,1,0,0,0,'Hace varios días que el proyecto #NOMBRE_PROYECTO# se mantiene en inicio.'),(621,45,'Planificacion','Hace mucho tiempo que está en planificación.',NULL,1,0,0,0,'Hace varios días que el proyecto #NOMBRE_PROYECTO# se mantiene en planificación.'),(622,45,'Actualizacion1','Semáforo de actualización del proyecto pasa a estar en amarillo.',NULL,1,0,0,0,'Atención! El proyecto #NOMBRE_PROYECTO# está desactualizado.'),(623,45,'Actualizacion2','Semáforo de actualización del proyecto pasa a estar en rojo.',NULL,1,0,0,0,'Alerta! El proyecto #NOMBRE_PROYECTO# está desactualizado.'),(624,45,'Presupuesto1','Cuando la fecha de pago se venció y no ha sido confirmada la factura.',NULL,1,0,0,0,'Tiene pagos vencidos para el proyecto #NOMBRE_PROYECTO#.'),(625,45,'Presupuesto2','Durante los últimos 5 días hábiles del mes, solamente para los que tienen cargado devengado. Actualicen el devengado dado que cuando comience el siguiente mes no se podrá tocar el actual.',NULL,1,0,0,0,'Actualizar el devengado para el proyecto #NOMBRE_PROYECTO#. No podrá modificarlo una vez iniciado el proximo mes.'),(626,45,'Presupuesto3','Cuando un entregable está al 100% y tiene un pago asociado cuya factura no está confirmada. A los 5 días, notificación para conseguir la factura.',NULL,1,0,0,0,'Un entregable finalizado del proyecto #NOMBRE_PROYECTO# tiene asociado una factura que no ha sido confirmada.'),(627,45,'Presupuesto4','Cuando un entregable está al 100% y tiene un pago asociado cuya factura no está confirmada. A los 10 días, notificación para conseguir la factura.',NULL,1,0,0,0,'Un entregable finalizado del proyecto #NOMBRE_PROYECTO# tiene asociado una factura que no ha sido confirmada.'),(628,45,'Presupuesto5','Cuando la nueva fecha proyectada de la confirmación de una factura es durante la última quincena del año o al año siguiente.',NULL,1,0,0,0,'La fecha proyectada de la confirmacion de una factura para el proyecto #NOMBRE_PROYECTO# será durante la última quincena del año o al año siguiente.'),(629,45,'Cronograma1','Cuando un entregable no está al 100% de avance y tiene fecha de fin anterior al día de hoy. Solamente en caso que el proyecto está desactualizado.',NULL,1,0,0,0,'Un entregable del proyecto #NOMBRE_PROYECTO# está atrasado o desactualizado.'),(630,45,'CambioFase1','Existen una solicitud pendiente del proyecto #NOMBRE_PROYECTO#.',NULL,1,0,0,0,'Existen una solicitud pendiente del proyecto #NOMBRE_PROYECTO#.'),(631,45,'EliminacionProy1','Se ha eliminado el proyecto #NOMBRE_PROYECTO#.',NULL,1,0,0,0,'Se ha eliminado el proyecto #NOMBRE_PROYECTO#.'),(646,47,'Riesgos1','Se pasa a amarillo el semáforo de actualización de riesgos.',NULL,1,0,0,0,'Para el proyecto #NOMBRE_PROYECTO# existen riesgos en estado de atención.'),(647,47,'Riesgos2','Se registra un nuevo riesgo y este cae en la zona roja.',NULL,1,0,0,0,'Para el proyecto #NOMBRE_PROYECTO# existen riesgos en estado de alerta.'),(648,47,'inicio','Hace mucho tiempo que está en inicio.',NULL,1,0,0,0,'Hace varios días que el proyecto #NOMBRE_PROYECTO# se mantiene en inicio.'),(649,47,'Planificacion','Hace mucho tiempo que está en planificación.',NULL,1,0,0,0,'Hace varios días que el proyecto #NOMBRE_PROYECTO# se mantiene en planificación.'),(650,47,'Actualizacion1','Semáforo de actualización del proyecto pasa a estar en amarillo.',NULL,1,0,0,0,'Atención! El proyecto #NOMBRE_PROYECTO# está desactualizado.'),(651,47,'Actualizacion2','Semáforo de actualización del proyecto pasa a estar en rojo.',NULL,1,0,0,0,'Alerta! El proyecto #NOMBRE_PROYECTO# está desactualizado.'),(652,47,'Presupuesto1','Cuando la fecha de pago se venció y no ha sido confirmada la factura.',NULL,1,0,0,0,'Tiene pagos vencidos para el proyecto #NOMBRE_PROYECTO#.'),(653,47,'Presupuesto2','Durante los últimos 5 días hábiles del mes, solamente para los que tienen cargado devengado. Actualicen el devengado dado que cuando comience el siguiente mes no se podrá tocar el actual.',NULL,1,0,0,0,'Actualizar el devengado para el proyecto #NOMBRE_PROYECTO#. No podrá modificarlo una vez iniciado el proximo mes.'),(654,47,'Presupuesto3','Cuando un entregable está al 100% y tiene un pago asociado cuya factura no está confirmada. A los 5 días, notificación para conseguir la factura.',NULL,1,0,0,0,'Un entregable finalizado del proyecto #NOMBRE_PROYECTO# tiene asociado una factura que no ha sido confirmada.'),(655,47,'Presupuesto4','Cuando un entregable está al 100% y tiene un pago asociado cuya factura no está confirmada. A los 10 días, notificación para conseguir la factura.',NULL,1,0,0,0,'Un entregable finalizado del proyecto #NOMBRE_PROYECTO# tiene asociado una factura que no ha sido confirmada.'),(656,47,'Presupuesto5','Cuando la nueva fecha proyectada de la confirmación de una factura es durante la última quincena del año o al año siguiente.',NULL,1,0,0,0,'La fecha proyectada de la confirmacion de una factura para el proyecto #NOMBRE_PROYECTO# será durante la última quincena del año o al año siguiente.'),(657,47,'Cronograma1','Cuando un entregable no está al 100% de avance y tiene fecha de fin anterior al día de hoy. Solamente en caso que el proyecto está desactualizado.',NULL,1,0,0,0,'Un entregable del proyecto #NOMBRE_PROYECTO# está atrasado o desactualizado.'),(658,47,'CambioFase1','Existen una solicitud pendiente del proyecto #NOMBRE_PROYECTO#.',NULL,1,0,0,0,'Existen una solicitud pendiente del proyecto #NOMBRE_PROYECTO#.'),(659,47,'EliminacionProy1','Se ha eliminado el proyecto #NOMBRE_PROYECTO#.',NULL,1,0,0,0,'Se ha eliminado el proyecto #NOMBRE_PROYECTO#.');
/*!40000 ALTER TABLE `notificacion` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificacion_envio`
--

LOCK TABLES `notificacion_envio` WRITE;
/*!40000 ALTER TABLE `notificacion_envio` DISABLE KEYS */;
/*!40000 ALTER TABLE `notificacion_envio` ENABLE KEYS */;
UNLOCK TABLES;

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
  UNIQUE KEY `notinst_unique_key` (`notinst_proy_fk`,`notinst_not_fk`),
  KEY `notinst_not_fk_idx` (`notinst_not_fk`),
  KEY `notinst_proy_fk_idx` (`notinst_proy_fk`),
  CONSTRAINT `notinst_not_fk` FOREIGN KEY (`notinst_not_fk`) REFERENCES `notificacion` (`not_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `notinst_proy_fk` FOREIGN KEY (`notinst_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificacion_instancia`
--

LOCK TABLES `notificacion_instancia` WRITE;
/*!40000 ALTER TABLE `notificacion_instancia` DISABLE KEYS */;
/*!40000 ALTER TABLE `notificacion_instancia` ENABLE KEYS */;
UNLOCK TABLES;

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
  `obj_est_habilitado` tinyint(3) NOT NULL,
  PRIMARY KEY (`obj_est_pk`),
  UNIQUE KEY `obj_est_org_fk_nombre` (`obj_est_org_fk`,`obj_est_nombre`),
  KEY `obj_est_org_fk_idx` (`obj_est_org_fk`),
  CONSTRAINT `obj_est_org_fk` FOREIGN KEY (`obj_est_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objetivos_estrategicos`
--

LOCK TABLES `objetivos_estrategicos` WRITE;
/*!40000 ALTER TABLE `objetivos_estrategicos` DISABLE KEYS */;
INSERT INTO `objetivos_estrategicos` VALUES (9,'Salud Integral','',47,1),(10,'Rectoría','',47,1),(11,'Descentralización y territorialización','',47,1),(12,'Estrategia de comunicación','',47,1),(13,'Generación de conocimiento y capacitación','',47,1),(14,'Sistemas de información nacionales e internacionales','',47,1),(15,'Seguimiento y evaluación científica','',47,1),(16,'Integración de Derechos Humanos','',47,1);
/*!40000 ALTER TABLE `objetivos_estrategicos` ENABLE KEYS */;
UNLOCK TABLES;

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
  `orga_habilitado` tinyint(3) NOT NULL,
  PRIMARY KEY (`orga_pk`),
  KEY `orga_org_fk_idx` (`orga_org_fk`),
  KEY `orga_amb_fk_idx` (`orga_amb_fk`),
  KEY `orga_proveedor_idx` (`orga_proveedor`),
  CONSTRAINT `orga_amb_fk` FOREIGN KEY (`orga_amb_fk`) REFERENCES `ambito` (`amb_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `orga_org_fk` FOREIGN KEY (`orga_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organi_int_prove`
--

LOCK TABLES `organi_int_prove` WRITE;
/*!40000 ALTER TABLE `organi_int_prove` DISABLE KEYS */;
INSERT INTO `organi_int_prove` VALUES (5,'Donaciones',1,'Donaciones','Donaciones','','',NULL,'',NULL,47,NULL,0,1),(6,'Proveedores Plaza M/N',1,'','','','','','',NULL,47,NULL,0,1),(7,'Proveedores Plaza M/E',1,'','','','','','',NULL,47,NULL,0,1),(8,'Proveedores Exterior',1,'','','','','','',NULL,47,NULL,0,1),(9,'MGAP',0,'Ministerio de Ganadería, Agricultura y Pesca','','','2410 4155 - 58','http://www.mgap.gub.uy',' Constituyente 1476 ',NULL,47,NULL,0,1),(10,'MSP',0,'Ministerio de Salud Pública','','','','','',NULL,47,NULL,0,1),(11,'Prestadores integrales del SNIS',0,'Prestadores integrales del SNIS','','','','','',NULL,47,NULL,0,1);
/*!40000 ALTER TABLE `organi_int_prove` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organismos`
--

LOCK TABLES `organismos` WRITE;
/*!40000 ALTER TABLE `organismos` DISABLE KEYS */;
INSERT INTO `organismos` VALUES (45,'Administración SIGES','logo-siges.png','Torre Ejecutiva Sur, Liniers 1324 piso 4','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0�\0\0\0I\0\0\0�-S\0\0\0	pHYs\0\0\0\0\0��\0\0\0tEXtSoftware\0Adobe ImageReadyq�e<\0\0�IDATx��]���85�&w��e�Bs�=A�Мv���B;�!�}�Jl����lI�z�ϏƲ�*�~�$T���t�\0\0�O�\0\0�\0\0d\0\0d\0�\r\0@6\0\0@6\0\0�\0\0\0�\0\0d\0�\r\0\0�\r\0@6\0\0@6\0\0�\0\0d\0\0d\0�\r\0\0�\r\0@6\0���M����\\}L�Q��TGCG�������Rg�>�,�~\Z�n�����GAm�{�U�ʧ�T��i\n�\0 9و2BCm�Ku�Tes32vM�\\M�s��ц?Ki�C�vѩ��Q���~����}�����*SU^�r]��8u����:�Ar����}��le���B�X?S�;9�Y�L��\r��A�z����t���>ت��OZ�%�-�܎��|v�l�ܧT�$~�oT,����é�|V�ȶ����h�Q���Aՙ��]��,ӄN�W�<�T��PZ�$�30Ƴ���qdN\"˲B�ԝ_ۥk!bai+w���}_�>��Hg~�q6��}z��9�8�B�����hӭp��ߜMث��e���3!r��jC�&�Zɢȋ0�3ԃ��bs����p�s�E&���2���:��E��Ig��+2J-gA��LB�<���۳��%��&��W���C]Ѽ�<Pf�>�_X^{�1�cf�����Z�iG;�ts��ٴ���<�xjW��ι-n 1d�x�kP�V �Ԏ�����0�:�;Z��9AS�/�l���lR�P�!�n(t^<D\"�w���[�����Ƹ��@R�&��6CE�9���#��ʃ�.،�x�y�)ɶ� ��b3}�U>9XY�(Ҵx�o�۬���#���\r���^#I������f�V�̫B�����JZ`i<�geH���(�	Ae��3N��Nt�,eVK��E�ƧB�L�����\nr��K���?ha�I�dk��-�yQ� ��h)�G��z��#2�=!뚯�X��Z��1A�Щԫ�D��NT7��w�����CZ�r!ɣ^K������A�<	�\"ے�*����D���}B#�7�q��S�7Yӏ��@��0=x��uc���Nm{\\\\�1�<Ed�yTӓ��kn8�K�*�3|\"C�Z�\r�$\\O$T7��z=\r��YYi)�>7�	�|�Tr�>m\\}3DtՍ/��Vv�pJ��㰭8\r��������b�>&�����^|�,e��f�)�X)�T�>�F:���[����`k�ʱ�y5�t{g}9� ��58�49\\�I݆�mA�o<�󖇸�Sgk�xme�!��\Z��!T.�u]�m�p��ǌ�`���H�6���������9����93�0�\"�ts�G���*�����+�l�Ku��o+�Htp�d�߂�k�#F7\0H��-�x�!e@������o��(�ӡ�Ͳ��zG~,qF���^7�\"�Vef1J=ǚ�*_\n��!ߨm8�Q{�͒�f&�z\\߰|\'�\r�z�h�3�Q��h��ٗ��4\'1�z�����ι��tY����¬9�_j)��1�Z>���Q7[.�+[���lkC�o�������!sֶ+k�qN��[�J�E�<��I5m�leKJ6��d3î��?��� \\��ю�cU��1�[u�a����[2K�i�\"���%�69�Qg�yd.�:��|s�lʸ�ƶu,*�=n�[�}��� $�7ת���\"�OqYcsb�&��E���y��+�;�u��/n),��>s��mD>��\r#��@!\"\\�����:\0W��+ٚ�1�c2t����������o�ba��|t�m���J��囇E+��l[{|��\0ѭ3\'�Q�&=���1$c��+$E��Yw�pt����;�nC�o�\nEW�g���	�|�I*��:\'��9�^���׺9\r8dk�~�j��K���r��c�m�$Ik��]��g��(�Pe��sq�$��|T+G�a�svӁI��z�R�\"��Wp��>yX&��i�Y�z�vG9��-��<&�z\\���r�:OI��ԫn>:��lx��.��b(9d^�9��A6�`u����!<�l�_����P[Uv���%c��~����,R�\\�a#�w\n���僒\rkG,��	�xw-�x�u�>�M\"Ԙ���G�w����A���[\"@͌�̉r�6\Z�\\lC�ݞ����Q�\"�����bq��,�����Q��e��� �?�%��{#���\0@�s6\0\0@6\0\0�\0\0\0�\0\0d\0�\r\0\0�\r\0@6\0\0@6\0\0�\0\0d\0\0d\0�\r\0\0�\r\0@6\0\0�\0\0\0�\0\0d\0\0d\0�\r\0@6\0\0z¿\0{\nH����\0\0\0\0IEND�B`�',1,'1234',0),(47,'OrganismoPorDefecto',NULL,'Torre Ejecutiva',NULL,1,'visua',0);
/*!40000 ALTER TABLE `organismos` ENABLE KEYS */;
UNLOCK TABLES;

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
  `pag_txt_referencia` varchar(50) DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos`
--

LOCK TABLES `pagos` WRITE;
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participantes`
--

LOCK TABLES `participantes` WRITE;
/*!40000 ALTER TABLE `participantes` DISABLE KEYS */;
/*!40000 ALTER TABLE `participantes` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `pge_configuraciones`
--

LOCK TABLES `pge_configuraciones` WRITE;
/*!40000 ALTER TABLE `pge_configuraciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `pge_configuraciones` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `pge_invocaciones`
--

LOCK TABLES `pge_invocaciones` WRITE;
/*!40000 ALTER TABLE `pge_invocaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `pge_invocaciones` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plantilla_cronograma`
--

LOCK TABLES `plantilla_cronograma` WRITE;
/*!40000 ALTER TABLE `plantilla_cronograma` DISABLE KEYS */;
INSERT INTO `plantilla_cronograma` VALUES (1,'Cronograma de Obra',47,NULL);
/*!40000 ALTER TABLE `plantilla_cronograma` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plantilla_entregables`
--

LOCK TABLES `plantilla_entregables` WRITE;
/*!40000 ALTER TABLE `plantilla_entregables` DISABLE KEYS */;
INSERT INTO `plantilla_entregables` VALUES (1,'Acta de inicio',0,0,1,NULL,1,1,'','\0'),(2,'Saneamiento (metros terminados)',1,0,1,NULL,1,2,'','\0'),(3,'Vialidad (metros terminados)',1,0,1,NULL,1,3,'','\0'),(4,'Conexiones saneamiento funcionando',1,0,1,NULL,1,4,'2','\0'),(5,'Conexiones agua funcionando',1,0,1,NULL,1,5,'','\0'),(6,'Conexiones electricidad funcionando',1,0,1,NULL,1,6,'','\0'),(7,'Viviendas',1,0,1,NULL,1,7,'','\0'),(8,'Compras de vivienda usada entregadas',1,0,1,NULL,1,8,'7','\0'),(9,'Canastas de materiales con obra terminada',1,0,1,NULL,1,9,'7','\0'),(10,'Mitigación ambiental terminada',1,0,1,NULL,1,10,'','\0'),(11,'Equipamientos terminados',1,0,1,NULL,1,11,'','\0'),(12,'Espacios públicos terminados',1,0,1,NULL,1,12,'','\0'),(13,'Recepción provisoria (si es en partes, la última)',1,0,1,NULL,1,13,'',''),(14,'Realojos (vivienda nueva) entregados',1,0,1,NULL,1,14,'7','\0');
/*!40000 ALTER TABLE `plantilla_entregables` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto`
--

LOCK TABLES `presupuesto` WRITE;
/*!40000 ALTER TABLE `presupuesto` DISABLE KEYS */;
INSERT INTO `presupuesto` VALUES (1,NULL,NULL,NULL,0),(2,134000.00,2,1,0);
/*!40000 ALTER TABLE `presupuesto` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedimiento_compra`
--

LOCK TABLES `procedimiento_compra` WRITE;
/*!40000 ALTER TABLE `procedimiento_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `procedimiento_compra` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prod_mes`
--

LOCK TABLES `prod_mes` WRITE;
/*!40000 ALTER TABLE `prod_mes` DISABLE KEYS */;
INSERT INTO `prod_mes` VALUES (1,1,1,2020,0.00,0.00,0.00,0.00),(2,2,1,2020,0.00,0.00,0.00,0.00);
/*!40000 ALTER TABLE `prod_mes` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,1,'un.',1,'2019-11-22',NULL,NULL,'Documento integrador de Herramientas'),(2,2,'un.',3,'2019-11-22',NULL,NULL,'Canales de comunicación establecidos');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `prog_docs`
--

LOCK TABLES `prog_docs` WRITE;
/*!40000 ALTER TABLE `prog_docs` DISABLE KEYS */;
/*!40000 ALTER TABLE `prog_docs` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `prog_docs_obl`
--

LOCK TABLES `prog_docs_obl` WRITE;
/*!40000 ALTER TABLE `prog_docs_obl` DISABLE KEYS */;
/*!40000 ALTER TABLE `prog_docs_obl` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prog_indices`
--

LOCK TABLES `prog_indices` WRITE;
/*!40000 ALTER TABLE `prog_indices` DISABLE KEYS */;
/*!40000 ALTER TABLE `prog_indices` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prog_indices_pre`
--

LOCK TABLES `prog_indices_pre` WRITE;
/*!40000 ALTER TABLE `prog_indices_pre` DISABLE KEYS */;
/*!40000 ALTER TABLE `prog_indices_pre` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `prog_int`
--

LOCK TABLES `prog_int` WRITE;
/*!40000 ALTER TABLE `prog_int` DISABLE KEYS */;
/*!40000 ALTER TABLE `prog_int` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `prog_lectura_area`
--

LOCK TABLES `prog_lectura_area` WRITE;
/*!40000 ALTER TABLE `prog_lectura_area` DISABLE KEYS */;
/*!40000 ALTER TABLE `prog_lectura_area` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `prog_pre`
--

LOCK TABLES `prog_pre` WRITE;
/*!40000 ALTER TABLE `prog_pre` DISABLE KEYS */;
/*!40000 ALTER TABLE `prog_pre` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `prog_tags`
--

LOCK TABLES `prog_tags` WRITE;
/*!40000 ALTER TABLE `prog_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `prog_tags` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programas`
--

LOCK TABLES `programas` WRITE;
/*!40000 ALTER TABLE `programas` DISABLE KEYS */;
/*!40000 ALTER TABLE `programas` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `proy_docs`
--

LOCK TABLES `proy_docs` WRITE;
/*!40000 ALTER TABLE `proy_docs` DISABLE KEYS */;
/*!40000 ALTER TABLE `proy_docs` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proy_indices`
--

LOCK TABLES `proy_indices` WRITE;
/*!40000 ALTER TABLE `proy_indices` DISABLE KEYS */;
INSERT INTO `proy_indices` VALUES (1,NULL,NULL,0,NULL,0,'2019-11-22','2019-11-22',0.00,NULL,NULL,0,0,0,0,0,0,0,'2019-11-22 00:00:00',0,NULL,NULL,1),(2,NULL,NULL,0,NULL,0,'2019-11-22','2020-01-31',0.00,NULL,NULL,0,17,83,0,17,83,0,'2019-11-22 00:00:00',0,NULL,NULL,1);
/*!40000 ALTER TABLE `proy_indices` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proy_indices_pre`
--

LOCK TABLES `proy_indices_pre` WRITE;
/*!40000 ALTER TABLE `proy_indices_pre` DISABLE KEYS */;
/*!40000 ALTER TABLE `proy_indices_pre` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `proy_int`
--

LOCK TABLES `proy_int` WRITE;
/*!40000 ALTER TABLE `proy_int` DISABLE KEYS */;
/*!40000 ALTER TABLE `proy_int` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `proy_lectura_area`
--

LOCK TABLES `proy_lectura_area` WRITE;
/*!40000 ALTER TABLE `proy_lectura_area` DISABLE KEYS */;
/*!40000 ALTER TABLE `proy_lectura_area` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Relaci�n entre un proyecto y una o varias categorias secundarias.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proy_otros_cat_secundarias`
--

LOCK TABLES `proy_otros_cat_secundarias` WRITE;
/*!40000 ALTER TABLE `proy_otros_cat_secundarias` DISABLE KEYS */;
/*!40000 ALTER TABLE `proy_otros_cat_secundarias` ENABLE KEYS */;
UNLOCK TABLES;

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
  `proy_otr_ins_eje_fk` int(11) DEFAULT NULL COMMENT 'Instituci�n Ejecutora',
  `proy_otr_ent_fk` int(11) DEFAULT NULL COMMENT 'Inicio construcci�n del Producto. Asociado a un Entregable.',
  `proy_otr_origen` varchar(1000) DEFAULT NULL COMMENT 'Origen Principal de los Recursos.',
  `proy_otr_plazo` int(11) DEFAULT NULL COMMENT 'Plazo estimado de obra en d�as.',
  `proy_otr_observaciones` varchar(4000) DEFAULT NULL COMMENT 'Observaciones.',
  `proy_otr_cat_fk` int(11) DEFAULT NULL COMMENT 'Categor�a Principal.',
  `proy_otr_est_pub_fk` int(11) DEFAULT NULL COMMENT 'Estado de Publicaci�n.',
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COMMENT='Datos del proyecto principalmente para usarse en el visualizador';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proy_otros_datos`
--

LOCK TABLES `proy_otros_datos` WRITE;
/*!40000 ALTER TABLE `proy_otros_datos` DISABLE KEYS */;
INSERT INTO `proy_otros_datos` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(2,185,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `proy_otros_datos` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `proy_pre`
--

LOCK TABLES `proy_pre` WRITE;
/*!40000 ALTER TABLE `proy_pre` DISABLE KEYS */;
/*!40000 ALTER TABLE `proy_pre` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Registro de las veces que se publicó el proyecto en el Visualizador.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proy_publica_hist`
--

LOCK TABLES `proy_publica_hist` WRITE;
/*!40000 ALTER TABLE `proy_publica_hist` DISABLE KEYS */;
/*!40000 ALTER TABLE `proy_publica_hist` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proy_replanificacion`
--

LOCK TABLES `proy_replanificacion` WRITE;
/*!40000 ALTER TABLE `proy_replanificacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `proy_replanificacion` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proy_sitact_historico`
--

LOCK TABLES `proy_sitact_historico` WRITE;
/*!40000 ALTER TABLE `proy_sitact_historico` DISABLE KEYS */;
INSERT INTO `proy_sitact_historico` VALUES (1,'2019-11-22','<p> Meta 1: Se publicaron los<span>&nbsp;indicadores sobre la evolución de la eficiencia energética en diferentes sectores de actividad en formato de datos abiertos</span></p>',2,1289,0),(2,'2019-11-22','<p> - Se actualiza Drupal a nueva versión</p> \n<p> - Hoy se decide subir MRREE</p>',2,1289,0);
/*!40000 ALTER TABLE `proy_sitact_historico` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `proy_tags`
--

LOCK TABLES `proy_tags` WRITE;
/*!40000 ALTER TABLE `proy_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `proy_tags` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyectos`
--

LOCK TABLES `proyectos` WRITE;
/*!40000 ALTER TABLE `proyectos` DISABLE KEYS */;
INSERT INTO `proyectos` VALUES (2,47,2,NULL,NULL,352,1289,1290,1289,1288,NULL,NULL,3,2,2,0,'<p> <span>Existen 63 sitios diferentes en la Administración Central, con estructura heterogénea que dificultan el acceso homogeinizado a contenidos, incrementando costos operativos y generando una experiencia de usuario confusa. Se propone disponibilizar un <em><strong>Portal del Estado Único, promoviendo la armonía en los mensajes y el vínculo cercano del Estado con las personas.</strong></em></span></p>','<p> <span>Disponer de un Portal del Estado único para comunicar, informar y acercarse a las personas; con una imagen de gobierno unificada, con una comunicación integrada, y centralizando la infraestructura tecnológica.</span></p> \n<p> <span>Que esté integrado por todas las unidades y programas de OrganismoPorDefecto de la República y cada uno de los Ministerios.</span></p> \n<p> <br> <span><strong><em>Contribuye a:</em></strong><br> - Potenciar la Comunicación de Gobierno<br> - Facilitar la interacción de las personas con el Gobierno<br> - Racionalizar recursos y costos</span></p>','<ul> \n <li> Publicar indicadores sobre la evolución de la eficiencia energética en diferentes sectores de actividad en formato de datos abiertos.</li> \n <li> Desarrollar y publicación de un mapa nacional donde se georeferencien los distintos proyectos de eficiencia energética, los instrumentos de promoción que utilizaron y su localización en el territorio nacional.</li> \n</ul>','<p> - Se actualiza Drupal a nueva versión</p> \n<p> - Hoy se decide subir MRREE</p>',NULL,'Portal Único del Estado',NULL,10,20,1,'2019-11-22 15:42:08.000000','2019-11-22 16:39:40.000000',NULL,NULL,NULL,NULL,NULL,NULL,0,2,NULL,12,'<p> <span><em><strong>El Estado</strong></em>: Incorporando una estrategia de comunicación y una vía tecnológica que le permita organizar y homogeinizar su comunicación con la ciudadanía.</span></p> \n<p> <span><em><strong>El ciudadano:</strong></em> Que contará con una herramienta tecnológica moderna y eficiente.</span></p>','2019-11-22 16:39:40.000000');
/*!40000 ALTER TABLE `proyectos` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registros_horas`
--

LOCK TABLES `registros_horas` WRITE;
/*!40000 ALTER TABLE `registros_horas` DISABLE KEYS */;
/*!40000 ALTER TABLE `registros_horas` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revinfo`
--

LOCK TABLES `revinfo` WRITE;
/*!40000 ALTER TABLE `revinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `revinfo` ENABLE KEYS */;
UNLOCK TABLES;

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
  `risk_observaciones` varchar(400) DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `riesgos`
--

LOCK TABLES `riesgos` WRITE;
/*!40000 ALTER TABLE `riesgos` DISABLE KEYS */;
/*!40000 ALTER TABLE `riesgos` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_interesados`
--

LOCK TABLES `roles_interesados` WRITE;
/*!40000 ALTER TABLE `roles_interesados` DISABLE KEYS */;
INSERT INTO `roles_interesados` VALUES (2,47,'Analista',0),(3,47,'Comercial',0),(4,47,'Consultor',0),(5,47,'Coordinador',0);
/*!40000 ALTER TABLE `roles_interesados` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `roles_usuarios`
--

LOCK TABLES `roles_usuarios` WRITE;
/*!40000 ALTER TABLE `roles_usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_ayuda`
--

LOCK TABLES `ss_ayuda` WRITE;
/*!40000 ALTER TABLE `ss_ayuda` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_ayuda` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_categoper`
--

LOCK TABLES `ss_categoper` WRITE;
/*!40000 ALTER TABLE `ss_categoper` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_categoper` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=2823 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_configuraciones`
--

LOCK TABLES `ss_configuraciones` WRITE;
/*!40000 ALTER TABLE `ss_configuraciones` DISABLE KEYS */;
INSERT INTO `ss_configuraciones` VALUES (2638,NULL,'AUTH_LDAP_ENABLE','true/false: habilitar configuración de período de proyecto en entregables','false',NULL,NULL,NULL,'2019-11-20 15:56:02',NULL,1),(2639,NULL,'AUTH_LDAP_SEARCH_NAME','true/false: habilitar configuración de período de proyecto en entregables','false',NULL,NULL,NULL,'2019-11-20 15:56:02',NULL,1),(2640,NULL,'AUTH_LDAP_SEARCH_FILTER','true/false: habilitar configuración de período de proyecto en entregables','false',NULL,NULL,NULL,'2019-11-20 15:56:02',NULL,1),(2641,NULL,'AUTH_LDAP_SEARCH_INITIAL_CTX_FACTORY','Clase java que construye el initial context','com.sun.jndi.ldap.LdapCtxFactory',NULL,NULL,NULL,'2019-11-20 15:56:02',NULL,1),(2642,NULL,'AUTH_LDAP_SEARCH_URL','true/false: habilitar configuración de período de proyecto en entregables','false',NULL,NULL,NULL,'2019-11-20 15:56:02',NULL,1),(2643,NULL,'AUTH_LDAP_SECURITY_PRINCIPAL','true/false: habilitar configuración de período de proyecto en entregables','false',NULL,NULL,NULL,'2019-11-20 15:56:02',NULL,1),(2644,NULL,'AUTH_LDAP_SECURITY_CREDENTIAL','true/false: habilitar configuración de período de proyecto en entregables','false',NULL,NULL,NULL,'2019-11-20 15:56:02',NULL,1),(2645,NULL,'AUTH_LDAP_SEARCH_USER_LOGIN_FILTER','true/false: habilitar configuración de período de proyecto en entregables','false',NULL,NULL,NULL,'2019-11-20 15:56:02',NULL,1),(2646,NULL,'DOCUMENTOS_DIR','Directorio donde se almacenarán los documentos','/srv/siges/docs',NULL,NULL,NULL,'2019-11-20 15:56:02',NULL,1),(2647,NULL,'NOMBRE_ARCHIVO_MANUAL','Nombre del archivo correspondiente al manual de usuario','MANUAL',NULL,NULL,NULL,'2019-11-20 15:56:02',NULL,1),(2648,NULL,'TAMANIO_MAX_ARCHIVO_MANUAL_USUARIO','Tamaño máximo del archivo de manual de usuario en bytes','10485760',NULL,NULL,NULL,'2019-11-20 15:56:02',NULL,1),(2649,NULL,'MEDIA_MIMETYPE_REGEX','Regex que valida el mimetype de los media que se suben','image\\/(jpeg|png)',NULL,NULL,NULL,'2019-11-20 15:56:02',NULL,1),(2650,NULL,'DEBUG','Modo depuración','false',NULL,NULL,NULL,'2019-11-20 15:56:02',NULL,1),(2651,NULL,'DEBUG_DESTINO','Email destino en modo depuración','prueba@prueba.com',NULL,NULL,NULL,'2019-11-20 15:56:02',NULL,1),(2652,NULL,'INCLUIR_CALCULAR_FINALIZADOS','Incluir lor pryectos finalizdos en el cáclulo de indicadores','false',NULL,NULL,NULL,'2019-11-20 15:56:02',NULL,1),(2653,NULL,'URL_SISTEMA','URL del Sistema','https://siges.agesic.gub.uy',NULL,NULL,NULL,'2019-11-20 15:56:02',NULL,1),(2654,45,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2019-11-20 16:41:04',NULL,1),(2655,45,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2019-11-20 16:41:04',NULL,1),(2656,45,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2019-11-20 16:41:04',NULL,1),(2657,45,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2019-11-20 16:41:05',NULL,1),(2658,45,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2019-11-20 16:41:05',NULL,1),(2659,45,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2019-11-20 16:41:05',NULL,1),(2660,45,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2019-11-20 16:41:05',NULL,1),(2661,45,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2019-11-20 16:41:05',NULL,1),(2662,45,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','512000',NULL,NULL,NULL,'2019-11-20 16:41:05',NULL,1),(2663,45,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2019-11-20 16:41:05',NULL,1),(2664,45,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,NULL,NULL,'2019-11-20 16:41:05',NULL,1),(2665,45,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2019-11-20 16:41:05',NULL,1),(2666,45,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2019-11-20 16:41:06',NULL,1),(2667,45,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2668,45,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2669,45,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2670,45,'CON_CORREO','Si se envía correo o no','true',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2671,45,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2672,45,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2673,45,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2674,45,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2675,45,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2676,45,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2677,45,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2678,45,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2679,45,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2680,45,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2681,45,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/srv/siges/media_files/',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2682,45,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://127.0.0.1:8080/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2683,45,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://127.0.0.1:8080/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2684,45,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2685,45,'PROYECTO_GANTT_PERIODO','true/false: habilitar configuración de período de proyecto en entregables','false',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2686,45,'TOOLTIP_OBJETIVO_ESTRATEGICO','Tooltip de objetivo estratégico','Objetivo Estratégico',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2687,45,'LABEL_OBJ_ESTRE','Esta configuracion es utilizada para definir nuevas etiquetas de objetivos estrategicos','Objetivo Estratégico',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2688,45,'MAIL_FROM','Dirección desde donde se envían los mails','mail@agesic.gub.uy',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2689,45,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2019-11-20 16:41:08',NULL,1),(2690,45,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2019-11-20 16:41:09',NULL,1),(2691,45,'MAIL_ENCODING','Encoding de los mails a enviar','UTF-8',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2692,45,'VISUALIZADOR.PUBLICARSERVICIO_VERSION','Versión de la que se exportan los proyectos al visualizador','2',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2693,45,'APROBACION_PMOF','Esta configuración es utilizada para que el PMO Federada cambiar de fase en los proyectos sin enviar solicitudes.','false',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2694,45,'APROBACION_REPLANIFICACION_PMOF','Esta configuración es utilizada para que el PMO Federada pueda cambiar del estado de Ejecución a Planificación sin realizar una solicitud.','false',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2695,45,'PROVEEDOR_ES_EXIGIDO_EN_PAGO','Esta configuración es utilizada para que el PMO Federada deba elegir un proveedor en el pago.','false',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2696,45,'PROVEEDOR_ES_EXIGIDO_EN_COMPRA','Esta configuración es utilizada para que el PMO Federada deba elegir un proveedor en la compra.','false',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2697,45,'CLIENTE_ES_EXIGIDO_EN_PAGO','Esta configuración es utilizada para que se deba elegir un cliente en el pago al momento de aprobarlo.','false',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2698,45,'SHOW_MODULO_RIESGOS','Mostrar el modulo de riesgos','true',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2699,45,'SHOW_MODULO_PRODUCTOS','Mostrar el modulo de productos','true',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2700,45,'SHOW_MODULO_PRESUPUESTO','Mostrar el modulo de presupuesto','true',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2701,45,'SHOW_MODULO_DOCUMENTOS','Mostrar el modulo de documentos','true',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2702,45,'SHOW_MODULO_INTERESADOS','Mostrar el modulo de interesados','true',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2703,45,'SHOW_MODULO_COLABORADORES','Mostrar el modulo de colaboradores','true',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2704,45,'SHOW_MODULO_LOCALIZACIONES','Mostrar el modulo de localizaciones','true',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2705,45,'SHOW_MODULO_CALIDAD','Mostrar el modulo de calidad','true',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2706,45,'SHOW_MODULO_MULTIMEDIA','Mostrar el modulo de multimedia','true',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2707,45,'CAMPOS_SON_EXIGIDOS_EN_ADQUISICION','Esta configuración es utilizada para que los campos \"ID de adquisición\", \"Tipo de adquisición\", y \"Centro de costo\" sean requeridos al crear/modificar una adquisición.','true',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2708,45,'GERENTES_ASIGNAN_AREAS_TEMATICAS','Habilita a Gerente y Adjunto a asignar áreas temáticas a los proyectos en los cuales se encuentran asignados.','false',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2709,45,'LARGO_MAXIMO_ID_ADQUISICION','Define el largo máximo del valor del campo \"Id. de Adquisición\" en Adquisición.','5',NULL,NULL,NULL,'2019-11-20 16:41:10',NULL,1),(2766,47,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2019-11-21 16:20:42',NULL,1),(2767,47,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2768,47,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2769,47,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2770,47,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2771,47,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2772,47,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2773,47,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2774,47,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','512000',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2775,47,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2776,47,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2777,47,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2778,47,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2779,47,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2780,47,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2781,47,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2782,47,'CON_CORREO','Si se envía correo o no','true',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2783,47,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2784,47,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2785,47,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2786,47,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2787,47,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2788,47,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2789,47,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2790,47,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2791,47,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2792,47,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2793,47,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/srv/siges/media_files/',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2794,47,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://127.0.0.1:8080/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2795,47,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://127.0.0.1:8080/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2796,47,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2797,47,'PROYECTO_GANTT_PERIODO','true/false: habilitar configuración de período de proyecto en entregables','false',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2798,47,'TOOLTIP_OBJETIVO_ESTRATEGICO','Tooltip de objetivo estratégico','Objetivo Estratégico',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2799,47,'LABEL_OBJ_ESTRE','Esta configuracion es utilizada para definir nuevas etiquetas de objetivos estrategicos','Objetivo Estratégico',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2800,47,'MAIL_FROM','Dirección desde donde se envían los mails','mail@agesic.gub.uy',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2801,47,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2802,47,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2803,47,'MAIL_ENCODING','Encoding de los mails a enviar','UTF-8',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2804,47,'VISUALIZADOR.PUBLICARSERVICIO_VERSION','Versión de la que se exportan los proyectos al visualizador','2',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2805,47,'APROBACION_PMOF','Esta configuración es utilizada para que el PMO Federada cambiar de fase en los proyectos sin enviar solicitudes.','false',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2806,47,'APROBACION_REPLANIFICACION_PMOF','Esta configuración es utilizada para que el PMO Federada pueda cambiar del estado de Ejecución a Planificación sin realizar una solicitud.','false',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2807,47,'PROVEEDOR_ES_EXIGIDO_EN_PAGO','Esta configuración es utilizada para que el PMO Federada deba elegir un proveedor en el pago.','false',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2808,47,'PROVEEDOR_ES_EXIGIDO_EN_COMPRA','Esta configuración es utilizada para que el PMO Federada deba elegir un proveedor en la compra.','false',NULL,NULL,NULL,'2019-11-21 16:20:43',NULL,1),(2809,47,'CLIENTE_ES_EXIGIDO_EN_PAGO','Esta configuración es utilizada para que se deba elegir un cliente en el pago al momento de aprobarlo.','false',NULL,NULL,NULL,'2019-11-21 16:20:44',NULL,1),(2810,47,'SHOW_MODULO_RIESGOS','Mostrar el modulo de riesgos','true',NULL,NULL,NULL,'2019-11-21 16:20:44',NULL,1),(2811,47,'SHOW_MODULO_PRODUCTOS','Mostrar el modulo de productos','true',NULL,NULL,NULL,'2019-11-21 16:20:44',NULL,1),(2812,47,'SHOW_MODULO_PRESUPUESTO','Mostrar el modulo de presupuesto','true',NULL,NULL,NULL,'2019-11-21 16:20:44',NULL,1),(2813,47,'SHOW_MODULO_DOCUMENTOS','Mostrar el modulo de documentos','true',NULL,NULL,NULL,'2019-11-21 16:20:44',NULL,1),(2814,47,'SHOW_MODULO_INTERESADOS','Mostrar el modulo de interesados','true',NULL,NULL,NULL,'2019-11-21 16:20:44',NULL,1),(2815,47,'SHOW_MODULO_COLABORADORES','Mostrar el modulo de colaboradores','true',NULL,NULL,NULL,'2019-11-21 16:20:44',NULL,1),(2816,47,'SHOW_MODULO_LOCALIZACIONES','Mostrar el modulo de localizaciones','true',NULL,NULL,NULL,'2019-11-21 16:20:44',NULL,1),(2817,47,'SHOW_MODULO_CALIDAD','Mostrar el modulo de calidad','true',NULL,NULL,NULL,'2019-11-21 16:20:44',NULL,1),(2818,47,'SHOW_MODULO_MULTIMEDIA','Mostrar el modulo de multimedia','true',NULL,NULL,NULL,'2019-11-21 16:20:44',NULL,1),(2819,47,'CAMPOS_SON_EXIGIDOS_EN_ADQUISICION','Esta configuración es utilizada para que los campos \"ID de adquisición\", \"Tipo de adquisición\", y \"Centro de costo\" sean requeridos al crear/modificar una adquisición.','true',NULL,NULL,NULL,'2019-11-21 16:20:44',NULL,1),(2820,47,'GERENTES_ASIGNAN_AREAS_TEMATICAS','Habilita a Gerente y Adjunto a asignar áreas temáticas a los proyectos en los cuales se encuentran asignados.','false',NULL,NULL,NULL,'2019-11-21 16:20:44',NULL,1),(2821,47,'LARGO_MAXIMO_ID_ADQUISICION','Define el largo máximo del valor del campo \"Id. de Adquisición\" en Adquisición.','5',NULL,NULL,NULL,'2019-11-21 16:20:44',NULL,1),(2822,NULL,'HABILITAR_MOVER_PROYECTO','Habilita la operación \"Mover proyecto\" en la ficha de los proyectos.','false',NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `ss_configuraciones` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_departamentos`
--

LOCK TABLES `ss_departamentos` WRITE;
/*!40000 ALTER TABLE `ss_departamentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_departamentos` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_domicilios`
--

LOCK TABLES `ss_domicilios` WRITE;
/*!40000 ALTER TABLE `ss_domicilios` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_domicilios` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_errores`
--

LOCK TABLES `ss_errores` WRITE;
/*!40000 ALTER TABLE `ss_errores` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_errores` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_localidades`
--

LOCK TABLES `ss_localidades` WRITE;
/*!40000 ALTER TABLE `ss_localidades` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_localidades` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_noticias`
--

LOCK TABLES `ss_noticias` WRITE;
/*!40000 ALTER TABLE `ss_noticias` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_noticias` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_operacion`
--

LOCK TABLES `ss_operacion` WRITE;
/*!40000 ALTER TABLE `ss_operacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_operacion` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_paises`
--

LOCK TABLES `ss_paises` WRITE;
/*!40000 ALTER TABLE `ss_paises` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_paises` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_paridades`
--

LOCK TABLES `ss_paridades` WRITE;
/*!40000 ALTER TABLE `ss_paridades` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_paridades` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_personas`
--

LOCK TABLES `ss_personas` WRITE;
/*!40000 ALTER TABLE `ss_personas` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_personas` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_rel_rol_operacion`
--

LOCK TABLES `ss_rel_rol_operacion` WRITE;
/*!40000 ALTER TABLE `ss_rel_rol_operacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_rel_rol_operacion` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_rol`
--

LOCK TABLES `ss_rol` WRITE;
/*!40000 ALTER TABLE `ss_rol` DISABLE KEYS */;
INSERT INTO `ss_rol` VALUES (91,'ADMINISTRADOR',NULL,'Usuario Administrador de la aplicacion','rol_administrador','SIGES_WEB',0,NULL,1,0),(92,'ADMIN_USU',NULL,'Administración de los Usuarios','rol_admin_usu','SIGES_WEB',0,NULL,1,0),(93,'DIR',NULL,'Director','rol_dir','SIGES_WEB',0,NULL,1,1),(94,'EDITOR_GRAL',NULL,'Editor General','rol_editor_gral','SIGES_WEB',0,NULL,1,0),(95,'MIGRACION',NULL,'Acceso a la migración','rol_migracion','SIGES_WEB',0,NULL,1,0),(96,'MIGRA_CALC_INDICA',NULL,'Acceso a los callculos de la migración','rol_migra_calc_indica','SIGES_WEB',0,NULL,1,0),(97,'PMOF',NULL,'PMO Federada','rol_pmof','SIGES_WEB',0,NULL,1,1),(98,'PMOT',NULL,'PMO Transversal','rol_pmot','SIGES_WEB',0,NULL,1,1),(99,'REGISTRO_HORAS',NULL,'Externo (solo carga de horas)','rol_registro_horas','SIGES_WEB',0,NULL,1,0),(100,'USU',NULL,'Usuario','rol_usu','SIGES_WEB',0,NULL,1,1);
/*!40000 ALTER TABLE `ss_rol` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_telefonos`
--

LOCK TABLES `ss_telefonos` WRITE;
/*!40000 ALTER TABLE `ss_telefonos` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_telefonos` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_tipos_documento_persona`
--

LOCK TABLES `ss_tipos_documento_persona` WRITE;
/*!40000 ALTER TABLE `ss_tipos_documento_persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_tipos_documento_persona` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_tipos_entrada_colectiva`
--

LOCK TABLES `ss_tipos_entrada_colectiva` WRITE;
/*!40000 ALTER TABLE `ss_tipos_entrada_colectiva` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_tipos_entrada_colectiva` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_tipos_telefono`
--

LOCK TABLES `ss_tipos_telefono` WRITE;
/*!40000 ALTER TABLE `ss_tipos_telefono` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_tipos_telefono` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_tipos_vialidad`
--

LOCK TABLES `ss_tipos_vialidad` WRITE;
/*!40000 ALTER TABLE `ss_tipos_vialidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_tipos_vialidad` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3460 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_usu_ofi_roles`
--

LOCK TABLES `ss_usu_ofi_roles` WRITE;
/*!40000 ALTER TABLE `ss_usu_ofi_roles` DISABLE KEYS */;
INSERT INTO `ss_usu_ofi_roles` VALUES (3449,'MIGRACION',2,NULL,91,1281,350,1,0),(3455,'OrganismoPorDefecto',0,47,98,1286,352,1,0),(3457,'OrganismoPorDefecto',0,47,97,1288,352,1,0),(3458,'OrganismoPorDefecto',0,47,100,1289,352,1,0),(3459,'OrganismoPorDefecto',0,47,93,1290,352,1,0);
/*!40000 ALTER TABLE `ss_usu_ofi_roles` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=1291 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_usuario`
--

LOCK TABLES `ss_usuario` WRITE;
/*!40000 ALTER TABLE `ss_usuario` DISABLE KEYS */;
INSERT INTO `ss_usuario` VALUES (1281,1,NULL,'admin','admin',0,NULL,NULL,'2017-07-31 09:21:32',NULL,NULL,NULL,NULL,NULL,NULL,'21232f297a57a5a743894a0e4a801fc3','Admin','Usuario',NULL,NULL,NULL,NULL,NULL,0,NULL,3,1,NULL,'admin','2017-07-31 09:21:32',NULL,350,NULL,NULL,NULL),(1286,NULL,NULL,'','usuario.pmot@siges.com',0,'',NULL,'2019-11-22 11:08:14',NULL,NULL,NULL,NULL,47,NULL,'30e22097b4e4271e06c04e56b1d0ad6f','PMOT','Usuario',NULL,NULL,NULL,'','',0,NULL,5,1,0,'admin','2019-11-22 11:34:28',NULL,NULL,NULL,NULL,NULL),(1288,NULL,NULL,'','usuario.pmof@siges.com',0,'',NULL,'2019-11-22 11:41:02',NULL,NULL,NULL,NULL,47,NULL,'30e22097b4e4271e06c04e56b1d0ad6f','PMOF','Usuario',NULL,NULL,NULL,'','',0,NULL,2,1,0,'usuario.pmof@siges.com','2019-11-22 11:41:02',NULL,NULL,NULL,NULL,NULL),(1289,NULL,NULL,'','usuario@siges.com',0,'',NULL,'2019-11-22 11:43:43',NULL,NULL,NULL,NULL,47,NULL,'30e22097b4e4271e06c04e56b1d0ad6f','Común','Usuario',NULL,NULL,NULL,'','',0,NULL,2,1,0,'usuario@siges.com','2019-11-22 11:43:43',NULL,NULL,NULL,NULL,NULL),(1290,NULL,NULL,'','usuario.director@siges.com',0,'',NULL,'2019-11-22 11:45:18',NULL,NULL,NULL,NULL,47,NULL,'30e22097b4e4271e06c04e56b1d0ad6f','Director','Usuario',NULL,NULL,NULL,'','',0,NULL,2,1,0,'usuario.director@siges.com','2019-11-22 11:45:18',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ss_usuario` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ss_usuarios_datos`
--

LOCK TABLES `ss_usuarios_datos` WRITE;
/*!40000 ALTER TABLE `ss_usuarios_datos` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_usuarios_datos` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temas_calidad`
--

LOCK TABLES `temas_calidad` WRITE;
/*!40000 ALTER TABLE `temas_calidad` DISABLE KEYS */;
INSERT INTO `temas_calidad` VALUES (1,'Performance',47),(2,'Accesibilidad',47),(3,'Usabilidad',47),(4,'Seguridad',47),(5,'Definición de los requisitos',47),(12,'Conformidad por parte de los usuarios con el producto desarrollado',47),(13,'Cumplimiento con todos los requerimientos establecidos',47),(14,'Realización en tiempo estipulado',47),(15,'Cumple requisitos técnicos',47);
/*!40000 ALTER TABLE `temas_calidad` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_documento`
--

LOCK TABLES `tipo_documento` WRITE;
/*!40000 ALTER TABLE `tipo_documento` DISABLE KEYS */;
INSERT INTO `tipo_documento` VALUES (11,'Riesgos',0,10,47,0,0),(12,'Cambios',0,8,47,0,0),(13,'Incidentes',0,8,47,0,0),(14,'Lecciones Aprendidas',0,5,47,0,0),(15,'Gestión de Cambios',0,5,47,0,0),(16,'Informe de Avance',5,5,47,0,0),(17,'Interesados',0,5,47,0,0),(18,'Comunicaciones',0,5,47,0,0),(19,'Presupuesto',0,8,47,0,0),(20,'Resumen Ejecutivo',2,13,47,1,0);
/*!40000 ALTER TABLE `tipo_documento` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_documento_instancia`
--

LOCK TABLES `tipo_documento_instancia` WRITE;
/*!40000 ALTER TABLE `tipo_documento_instancia` DISABLE KEYS */;
INSERT INTO `tipo_documento_instancia` VALUES (8,11,0,10,2,NULL,NULL,0),(9,12,0,8,2,NULL,NULL,0),(10,13,0,8,2,NULL,NULL,0),(11,14,0,5,2,NULL,NULL,0),(12,15,0,5,2,NULL,NULL,0),(13,16,5,5,2,NULL,NULL,0),(14,17,0,5,2,NULL,NULL,0),(15,18,0,5,2,NULL,NULL,0),(16,19,0,8,2,NULL,NULL,0),(17,20,2,13,2,NULL,NULL,0);
/*!40000 ALTER TABLE `tipo_documento_instancia` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_gasto`
--

LOCK TABLES `tipo_gasto` WRITE;
/*!40000 ALTER TABLE `tipo_gasto` DISABLE KEYS */;
INSERT INTO `tipo_gasto` VALUES (1,47,'Transporte'),(2,47,'Almuerzo'),(3,47,'Impresiones'),(5,47,'Papelería'),(6,47,'Publicidad'),(7,47,'Servicios de terceros'),(8,47,'Consumos'),(9,47,'Reparación y mantenimiento');
/*!40000 ALTER TABLE `tipo_gasto` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_leccion`
--

LOCK TABLES `tipo_leccion` WRITE;
/*!40000 ALTER TABLE `tipo_leccion` DISABLE KEYS */;
INSERT INTO `tipo_leccion` VALUES (13,'A_EVITAR','A evitar'),(14,'A_REPETIR','A repetir');
/*!40000 ALTER TABLE `tipo_leccion` ENABLE KEYS */;
UNLOCK TABLES;

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
  `tip_adq_org_fk` int(11) NOT NULL,
  PRIMARY KEY (`tip_adq_pk`),
  KEY `fk_tip_adq_org` (`tip_adq_org_fk`),
  CONSTRAINT `tipos_adquisicion_ibfk_1` FOREIGN KEY (`tip_adq_org_fk`) REFERENCES `organismos` (`org_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_adquisicion`
--

LOCK TABLES `tipos_adquisicion` WRITE;
/*!40000 ALTER TABLE `tipos_adquisicion` DISABLE KEYS */;
INSERT INTO `tipos_adquisicion` VALUES (1,'Bienes','',1,47),(2,'Servicios de consultoría','',1,47),(3,'Compra Directa Descentralizada','',1,47),(4,'Licitación Pública Internacional','',1,47),(5,'Compra Directa por Excepción','',1,47);
/*!40000 ALTER TABLE `tipos_adquisicion` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_media`
--

LOCK TABLES `tipos_media` WRITE;
/*!40000 ALTER TABLE `tipos_media` DISABLE KEYS */;
INSERT INTO `tipos_media` VALUES (22,'IMG','Imagen'),(23,'VIDEO','Video'),(24,'CAM','Cámara');
/*!40000 ALTER TABLE `tipos_media` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valor_calidad_codigos`
--

LOCK TABLES `valor_calidad_codigos` WRITE;
/*!40000 ALTER TABLE `valor_calidad_codigos` DISABLE KEYS */;
/*!40000 ALTER TABLE `valor_calidad_codigos` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valor_hora`
--

LOCK TABLES `valor_hora` WRITE;
/*!40000 ALTER TABLE `valor_hora` DISABLE KEYS */;
/*!40000 ALTER TABLE `valor_hora` ENABLE KEYS */;
UNLOCK TABLES;

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
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
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
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
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
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
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

-- Dump completed on 2020-07-16 18:30:00


-- VERSIONES desde 5.11 hasta 5.16.8

drop table if exists mutex_lock;

CREATE TABLE mutex_lock (
  `id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));
  
 insert into mutex_lock values('INDICADORES');
 insert into mutex_lock values('ENVIO_NOTIFICACION');
 insert into mutex_lock values('LIMPIAR_TOKENS');


UPDATE ss_configuraciones 
SET cnf_valor = 'file:///srv/siges/jboss-as-7.1.1.Final/PGE/PublicarProyecto.wsdl' 
WHERE cnf_codigo = 'VISUALIZADOR.PUBLICARSERVICIO_WSDL';

DELETE from ss_configuraciones WHERE cnf_codigo = 'TOOLTIP_OBJETIVO_ESTRATEGICO';
UPDATE ss_configuraciones SET cnf_codigo = 'LABEL_OBJETIVO_ESTRATEGICO' WHERE cnf_codigo = 'LABEL_OBJ_ESTRE';

DROP TABLE if exists calculo_indicadores_agendado;

CREATE TABLE `calculo_indicadores_agendado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `programa` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `programa_fk` FOREIGN KEY (`programa`) REFERENCES `programas` (`prog_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO mutex_lock VALUES ('INDICADORES_PROGRAMA');

ALTER TABLE roles_interesados ADD COLUMN rolint_habilitado tinyint(3);
UPDATE roles_interesados SET rolint_habilitado = 1;

ALTER TABLE interesados ADD COLUMN int_tipo varchar(15);
UPDATE interesados SET int_tipo = 'EXTERNO';

ALTER TABLE interesados ADD COLUMN int_usuario_fk int(11);
ALTER TABLE interesados ADD CONSTRAINT fk_int_usuario FOREIGN KEY (int_usuario_fk) REFERENCES ss_usuario (usu_id);


ALTER TABLE notificacion ADD COLUMN not_asunto varchar(255);
UPDATE notificacion set not_asunto = 'Notificación';



drop table if exists wekan_actividad;
drop table if exists wekan_tarjeta;
drop table if exists wekan_vinculacion;
drop table if exists wekan_tablero;

CREATE TABLE `wekan_tablero` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url_servidor` varchar(255) NOT NULL,
  `id_tablero` varchar(63) NOT NULL UNIQUE,
  `url_tablero` varchar(255) NOT NULL UNIQUE,
  `token` varchar(63),
  `id_campo_avance` varchar(63),
  `id_campo_proyecto` varchar(63),
  `id_integracion` varchar(63),
  `id_lista` varchar(63),
  `id_carril` varchar(63),
  PRIMARY KEY (`id`),
  KEY `wekan_tablero_id_idx` (`id`)
);

CREATE TABLE `wekan_vinculacion` (
  `tablero_fk` int(11) NOT NULL,
  `cronograma_fk` int(11) NOT NULL UNIQUE,
  `fecha_alta` DATETIME NOT NULL,
  `usuario_alta` varchar(255) NOT NULL,
  PRIMARY KEY (`tablero_fk`, `cronograma_fk`),
  KEY `wekan_vinculacion_tablero_fk_idx` (`tablero_fk`),
  KEY `wekan_vinculacion_cronograma_fk_idx` (`cronograma_fk`),
  CONSTRAINT `wekan_vinculacion_tablero_fk` FOREIGN KEY (`tablero_fk`) REFERENCES `wekan_tablero` (`id`),
  CONSTRAINT `wekan_vinculacion_cronograma_fk` FOREIGN KEY (`cronograma_fk`) REFERENCES `cronogramas` (`cro_pk`)
);

CREATE TABLE `wekan_tarjeta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_tarjeta` varchar(63) NOT NULL,
  `id_lista` varchar(63) NOT NULL,
  `entregable_fk` int(11) NOT NULL,
  `tablero_fk` int(11) NOT NULL,
  `cronograma_fk` int(11) NOT NULL,
  `fecha_alta` DATE NOT NULL,  
  PRIMARY KEY (`id`),
  KEY `wekan_tarjeta_id_idx` (`id`),
  KEY `wekan_tarjeta_id_tarjeta_idx` (`id_tarjeta`),
  CONSTRAINT `wekan_tarjeta_entregable_fk` FOREIGN KEY (`entregable_fk`) REFERENCES `entregables` (`ent_pk`),
  CONSTRAINT `wekan_tarjeta_tablero_fk` FOREIGN KEY (`tablero_fk`) REFERENCES `wekan_tablero` (`id`),
  CONSTRAINT `wekan_tarjeta_cronograma_fk` FOREIGN KEY (`cronograma_fk`) REFERENCES `cronogramas` (`cro_pk`)
);

CREATE TABLE `wekan_actividad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tarjeta_fk` int(11),
  `origen` varchar(31),
  `id_actividad` varchar(63),
  `descripcion` varchar(63),
  `id_usuario` varchar(63),
  `usuario` varchar(127),
  `actualizacion` varchar(127) NOT NULL,
  `valor_anterior` varchar(127),
  `valor_nuevo` varchar(127),
  `fecha_creacion` DATETIME NOT NULL,  
  `fecha_aplicacion_cambio` DATETIME,
  PRIMARY KEY (`id`),
  KEY `wekan_actividad_id_idx` (`id`),
  CONSTRAINT `wekan_actividad_tarjeta_fk` FOREIGN KEY (`tarjeta_fk`) REFERENCES `wekan_tarjeta` (`id`)
);

-- VERSION 5.17.2

DELETE FROM ss_configuraciones WHERE cnf_codigo IN ('SHOW_MODULO_WEKAN', 'WEKAN_ADMIN_CONTRASENIA', 'WEKAN_ADMIN_USUARIO');

INSERT INTO mutex_lock (id) VALUES ('STARTUP');

-- VERSION 5.19.0
ALTER TABLE areas_tags ADD COLUMN areatag_habilitada tinyint(1);
UPDATE areas_tags SET areatag_habilitada = 1;

ALTER TABLE organismos ADD COLUMN org_activo_siges_ind TINYint(1) DEFAULT 0;


-- VERSION 5.20.0

drop table if exists ss_configuraciones_defecto;

CREATE TABLE `ss_configuraciones_defecto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(145) DEFAULT NULL,
  `descripcion` varchar(245) DEFAULT NULL,
  `valor` varchar(1000) DEFAULT NULL,
  `es_html` tinyint(1) DEFAULT NULL,
  `ultima_modificacion` date DEFAULT NULL,
  `usuario_modificacion` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cnf_defecto_codigo` (`codigo`)
);

UPDATE ss_configuraciones_defecto set valor = 'true' WHERE codigo = 'APROBACION_PMOF';
UPDATE ss_configuraciones_defecto set valor = 'true' WHERE codigo = 'APROBACION_REPLANIFICACION_PMOF';
UPDATE ss_configuraciones_defecto set valor = 'true' WHERE codigo = 'ENVIO_NOTIFICACIONES';
UPDATE ss_configuraciones_defecto set valor = 'true' WHERE codigo = 'ADJUNTO_MODIFICA_PRESUPUESTO';
UPDATE ss_configuraciones_defecto set valor = 'siges@mtaagesic.gub.uy' WHERE codigo = 'MAIL_FROM';
UPDATE ss_configuraciones_defecto set valor = 'true' WHERE codigo = 'FILTRO_INICIO_POR_AREAS';

drop table if exists mails_template_defecto;

CREATE TABLE `mails_template_defecto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(45) NOT NULL,
  `asunto` varchar(200) NOT NULL,
  `mensaje` varchar(5000) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
);

drop table if exists notificacion_defecto;

CREATE TABLE `notificacion_defecto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(45) NOT NULL,
  `descripcion` varchar(245) NOT NULL,
  `asunto` varchar(255) DEFAULT NULL,
  `mensaje` varchar(5000) NOT NULL,
  `gerente_adjunto` tinyint(4) DEFAULT NULL,
  `pmof` tinyint(4) DEFAULT NULL,
  `pmot` tinyint(4) DEFAULT NULL,
  `sponsor` tinyint(4) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
);

ALTER TABLE proyectos ADD COLUMN `fecha_cambio_activacion` date DEFAULT NULL;
ALTER TABLE proyectos ADD COLUMN `usuario_cambio_activacion` varchar(255);

-- VERSION 5.22.0

ALTER TABLE adquisicion ADD COLUMN orden int(4);

set @row_number := 0;

Update
  adquisicion as adq
  inner join (
  SELECT 
      @row_number:=CASE
          WHEN @adq_no = adq_pre_fk 
        THEN @row_number + 1
          ELSE 1
      END AS num,
      @adq_no:=adq_pre_fk adq_pre_fk,
      a.adq_pk 
  FROM
      adquisicion a
  ORDER BY adq_pre_fk, adq_pk
  ) as A on adq.adq_pk = A.adq_pk
set adq.orden = A.num;

-- VERSION 6.1.0
ALTER TABLE presupuesto ADD pre_ocultar_pagos_confirmados BOOLEAN DEFAULT false;

-- VERSION 6.3.0
INSERT INTO `mails_template_defecto`
(`id`,`codigo`,`asunto`,`mensaje`,`version`) VALUES
(null,'MAIL_SOL_REPLANIFICACION','Solicitud de Replanificación','<h2>   Solicitud de Replanificación</h2> <p>   Se generó una solicitud de replanificación para el #TIPO_PROG_PROY# &quot;#NOMBRE_PROG_PROY#&quot;.</p> <div>   #ORGANISMO_NOMBRE#</div> <div>  #ORGANISMO_DIRECCION#</div> <div>   #URL_SISTEMA#</div>',1);


INSERT INTO mails_template (mail_tmp_pk, mail_tmp_cod, mail_tmp_org_fk, mail_tmp_asunto, mail_tmp_mensaje, version)
SELECT null, mld.codigo as mail_tmp_cod, mail_tmp_org_fk, mld.asunto as mail_tmp_asunto, mld.mensaje as mail_tmp_mensaje, 0 as version
FROM (SELECT distinct mail_tmp_org_fk FROM mails_template) ml 
jOIN (SELECT * FROM mails_template_defecto WHERE codigo = 'MAIL_SOL_REPLANIFICACION') mld;

-- VERSION 6.4.0

ALTER TABLE entregables ADD COLUMN ent_es_referencia tinyint(1);

UPDATE entregables SET ent_es_referencia = 0;


ALTER TABLE entregables ADD COLUMN ent_referido int(11);
ALTER TABLE entregables ADD CONSTRAINT `entregable_referencia_fk` FOREIGN KEY (`ent_referido`) REFERENCES `entregables` (`ent_pk`);

-- Version 6.4.1
INSERT INTO ss_configuraciones_defecto VALUES(null,'PERMITIR_REFERENCIAR_ENTREGABLES','Mostrar botón entregables con referencia','false',null,now(),'anonymous',0);

-- Version 6..4.6
ALTER TABLE componente_producto ADD com_habilitada TINYINT DEFAULT 1 NOT NULL;

-- Version 6.4.7

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('PAGO_FILTRO_PROCEDIMIENTO_COMPRA', 'Evita que se apruebe un pago si el procedimiento de compre indicado en la adquisición posee alguna de estas cadenas de texto. Las cadenas a filtrar se deben separar con ";" (sin comillas)', '', 0, now(), 'anonymous', 0);



INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk)
Select 'PAGO_FILTRO_PROCEDIMIENTO_COMPRA','Evita que se apruebe un pago si el procedimiento de compre indicado en la adquisición posee alguna de estas cadenas de texto. Las cadenas a filtrar se deben separar con ";" (sin comillas)','',0 ,org_pk from organismos;

UPDATE ss_configuraciones
SET  cnf_descripcion='Cantidad de días sin actualización del proyecto/riesgo para que la fecha "Actualización" figure en color amarillo'
WHERE cnf_codigo='RIESGO_TIEMPO_LIMITE_AMARILLO';

UPDATE ss_configuraciones
SET  cnf_descripcion='Cantidad de días sin actualización del proyecto/riesgo para que la fecha "Actualización" figure en color rojo'
WHERE cnf_codigo='RIESGO_TIEMPO_LIMITE_ROJO';

UPDATE ss_configuraciones_defecto
SET descripcion='Cantidad de días sin actualización del proyecto/riesgo para que la fecha "Actualización" figure en color amarillo'
WHERE  codigo='RIESGO_TIEMPO_LIMITE_AMARILLO';


UPDATE ss_configuraciones_defecto
SET descripcion='Cantidad de días sin actualización del proyecto/riesgo para que la fecha "Actualización" figure en color rojo'
WHERE  codigo='RIESGO_TIEMPO_LIMITE_ROJO';

ALTER TABLE organi_int_prove CHANGE COLUMN orga_razon_social orga_razon_social VARCHAR(250) NULL DEFAULT NULL ;

-- Version 6.4.8

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('CENTRO_DE_COSTO_EXIGIDO', 'Esta configuración es utilizada para que el campo "Centro de costo" sea requerido al crear/modificar una adquisición.', 'false', 0, now(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk)
Select 'CENTRO_DE_COSTO_EXIGIDO','Esta configuración es utilizada para que el campo "Centro de costo" sea requerido al crear/modificar una adquisición.','false',0 ,org_pk from organismos;

UPDATE ss_configuraciones
SET  cnf_descripcion='Esta configuración es utilizada para que los campos "ID de adquisición", "Tipo de adquisición"  sean requeridos al crear/modificar una adquisición.'
WHERE cnf_codigo='CAMPOS_SON_EXIGIDOS_EN_ADQUISICION';

UPDATE ss_configuraciones_defecto
SET descripcion='Esta configuración es utilizada para que los campos "ID de adquisición", "Tipo de adquisición"  sean requeridos al crear/modificar una adquisición.'
WHERE  codigo='CAMPOS_SON_EXIGIDOS_EN_ADQUISICION';

-- Version 6.4.11
INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('PMOT_OMITIR_APROBACION_INICIAL', 'Esta configuración es utilizada para que esuario pueda crear un proyecto, cargar la ficha base, seleccionar un PMO F directamente', 'false', 0, now(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk)
Select 'PMOT_OMITIR_APROBACION_INICIAL','Esta configuración es utilizada para que esuario pueda crear un proyecto, cargar la ficha base, seleccionar un PMO F directamente','false',0 ,org_pk from organismos;

-- Version 6.4.12
UPDATE ss_configuraciones
SET cnf_descripcion='Esta configuración es utilizada para que el usuario pueda crear un proyecto, cargar la ficha base, seleccionar un PMO F directamente'
WHERE cnf_codigo='PMOT_OMITIR_APROBACION_INICIAL';

UPDATE ss_configuraciones_defecto
SET descripcion='Esta configuración es utilizada para que el usuario pueda crear un proyecto, cargar la ficha base, seleccionar un PMO F directamente'
WHERE codigo='PMOT_OMITIR_APROBACION_INICIAL';

-- Version 6.4.16

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('PMOF_GESTIONA_SUS_PROYECTOS', 'Esta configuración permite que él PMO F de un proyecto, también pueda tener los mismos privilegios que el gerente y adjunto', 'false', 0, now(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk)
Select 'PMOF_GESTIONA_SUS_PROYECTOS','Esta configuración permite que él PMO F de un proyecto, también pueda tener los mismos privilegios que el gerente y adjunto','false',0 ,org_pk from organismos;

-- Version 6.4.23

drop table if exists adquisiciones_rangos;

CREATE TABLE adquisiciones_rangos (
  adr_pk BIGINT auto_increment not NULL,
  adr_organizacion_fk INT NULL,
  adr_area_fk int NULL,
  adr_division_fk int NULL,
  adr_desde int NULL,
  adr_hasta INT NULL,
  adr_ultimo INT NULL,
  CONSTRAINT adquisiciones_rangos_PK PRIMARY KEY (adr_pk),
  CONSTRAINT adquisiciones_rangos_FK FOREIGN KEY (adr_organizacion_fk) REFERENCES organismos(org_pk),
  CONSTRAINT adquisiciones_rangos_FK_1 FOREIGN KEY (adr_area_fk) REFERENCES areas(area_pk),
  CONSTRAINT adquisiciones_rangos_FK_2 FOREIGN KEY (adr_division_fk) REFERENCES objetivos_estrategicos(obj_est_pk)
);



INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('TIPO_ADQUISICION_REQUERIDO', 'Indica si el tipo adquisicion es requerido', 'false', 0, now(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk)
Select 'TIPO_ADQUISICION_REQUERIDO','Indica si el tipo adquisicion es requerido','false',0 ,org_pk from organismos;


INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('ID_ADQUISICION_REQUERIDO', 'Indica si el id adquisición es requerido', 'false', 0, now(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk)
Select 'ID_ADQUISICION_REQUERIDO','Indica si el id adquisición es requerido','false',0 ,org_pk from organismos;

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('ACTIVAR_RANGOS_ID_ADQUISICION', 'Activar Rango de adquisición', 'false', 0, now(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk)
Select 'ACTIVAR_RANGOS_ID_ADQUISICION','Activar Rango de adquisición','false',0 ,org_pk from organismos;

ALTER TABLE adquisiciones_rangos ADD adr_chequear boolean DEFAULT false NULL;



-- Version 6.4.24

drop table if exists adquisiciones_rangos_aux;

CREATE TABLE adquisiciones_rangos_aux (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ;

INSERT INTO adquisiciones_rangos_aux (id) VALUES(1);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(10);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(11);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(12);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(13);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(14);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(15);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(16);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(17);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(18);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(19);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(20);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(21);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(22);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(23);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(24);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(25);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(26);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(27);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(28);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(29);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(30);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(31);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(32);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(33);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(34);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(35);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(36);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(37);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(38);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(39);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(40);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(41);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(42);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(43);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(44);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(45);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(46);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(47);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(48);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(49);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(50);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(51);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(52);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(53);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(54);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(55);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(56);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(57);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(58);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(59);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(60);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(61);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(62);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(63);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(64);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(65);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(66);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(67);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(68);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(69);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(70);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(71);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(72);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(73);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(74);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(75);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(76);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(77);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(78);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(79);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(80);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(81);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(82);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(83);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(84);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(85);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(86);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(87);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(88);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(89);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(90);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(91);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(92);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(93);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(94);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(95);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(96);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(97);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(98);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(99);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(100);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(101);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(102);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(103);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(104);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(105);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(106);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(107);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(108);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(109);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(110);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(111);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(112);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(113);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(114);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(115);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(116);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(117);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(118);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(119);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(120);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(121);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(122);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(123);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(124);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(125);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(126);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(127);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(128);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(129);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(130);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(131);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(132);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(133);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(134);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(135);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(136);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(137);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(138);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(139);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(140);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(141);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(142);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(143);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(144);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(145);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(146);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(147);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(148);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(149);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(150);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(151);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(152);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(153);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(154);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(155);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(156);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(157);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(158);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(159);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(160);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(161);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(162);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(163);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(164);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(165);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(166);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(167);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(168);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(169);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(170);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(171);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(172);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(173);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(174);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(175);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(176);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(177);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(178);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(179);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(180);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(181);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(182);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(183);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(184);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(185);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(186);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(187);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(188);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(189);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(190);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(191);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(192);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(193);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(194);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(195);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(196);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(197);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(198);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(199);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(200);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(201);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(202);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(203);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(204);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(205);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(206);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(207);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(208);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(209);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(210);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(211);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(212);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(213);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(214);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(215);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(216);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(217);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(218);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(219);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(220);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(221);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(222);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(223);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(224);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(225);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(226);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(227);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(228);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(229);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(230);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(231);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(232);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(233);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(234);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(235);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(236);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(237);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(238);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(239);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(240);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(241);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(242);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(243);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(244);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(245);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(246);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(247);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(248);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(249);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(250);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(251);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(252);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(253);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(254);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(255);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(256);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(257);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(258);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(259);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(260);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(261);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(262);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(263);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(264);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(265);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(266);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(267);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(268);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(269);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(270);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(271);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(272);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(273);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(274);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(275);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(276);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(277);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(278);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(279);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(280);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(281);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(282);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(283);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(284);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(285);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(286);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(287);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(288);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(289);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(290);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(291);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(292);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(293);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(294);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(295);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(296);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(297);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(298);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(299);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(300);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(301);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(302);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(303);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(304);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(305);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(306);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(307);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(308);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(309);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(310);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(311);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(312);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(313);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(314);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(315);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(316);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(317);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(318);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(319);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(320);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(321);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(322);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(323);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(324);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(325);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(326);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(327);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(328);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(329);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(330);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(331);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(332);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(333);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(334);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(335);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(336);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(337);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(338);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(339);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(340);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(341);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(342);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(343);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(344);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(345);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(346);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(347);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(348);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(349);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(350);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(351);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(352);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(353);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(354);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(355);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(356);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(357);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(358);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(359);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(360);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(361);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(362);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(363);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(364);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(365);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(366);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(367);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(368);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(369);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(370);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(371);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(372);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(373);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(374);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(375);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(376);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(377);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(378);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(379);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(380);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(381);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(382);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(383);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(384);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(385);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(386);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(387);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(388);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(389);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(390);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(391);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(392);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(393);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(394);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(395);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(396);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(397);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(398);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(399);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(400);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(401);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(402);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(403);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(404);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(405);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(406);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(407);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(408);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(409);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(410);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(411);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(412);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(413);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(414);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(415);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(416);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(417);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(418);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(419);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(420);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(421);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(422);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(423);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(424);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(425);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(426);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(427);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(428);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(429);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(430);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(431);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(432);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(433);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(434);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(435);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(436);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(437);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(438);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(439);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(440);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(441);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(442);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(443);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(444);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(445);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(446);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(447);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(448);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(449);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(450);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(451);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(452);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(453);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(454);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(455);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(456);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(457);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(458);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(459);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(460);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(461);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(462);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(463);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(464);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(465);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(466);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(467);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(468);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(469);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(470);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(471);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(472);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(473);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(474);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(475);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(476);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(477);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(478);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(479);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(480);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(481);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(482);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(483);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(484);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(485);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(486);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(487);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(488);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(489);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(490);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(491);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(492);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(493);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(494);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(495);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(496);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(497);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(498);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(499);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(500);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(501);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(502);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(503);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(504);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(505);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(506);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(507);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(508);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(509);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(510);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(511);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(512);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(513);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(514);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(515);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(516);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(517);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(518);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(519);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(520);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(521);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(522);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(523);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(524);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(525);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(526);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(527);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(528);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(529);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(530);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(531);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(532);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(533);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(534);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(535);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(536);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(537);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(538);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(539);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(540);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(541);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(542);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(543);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(544);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(545);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(546);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(547);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(548);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(549);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(550);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(551);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(552);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(553);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(554);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(555);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(556);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(557);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(558);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(559);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(560);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(561);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(562);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(563);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(564);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(565);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(566);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(567);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(568);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(569);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(570);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(571);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(572);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(573);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(574);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(575);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(576);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(577);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(578);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(579);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(580);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(581);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(582);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(583);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(584);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(585);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(586);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(587);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(588);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(589);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(590);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(591);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(592);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(593);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(594);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(595);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(596);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(597);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(598);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(599);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(600);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(601);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(602);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(603);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(604);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(605);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(606);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(607);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(608);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(609);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(610);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(611);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(612);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(613);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(614);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(615);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(616);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(617);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(618);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(619);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(620);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(621);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(622);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(623);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(624);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(625);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(626);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(627);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(628);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(629);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(630);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(631);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(632);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(633);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(634);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(635);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(636);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(637);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(638);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(639);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(640);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(641);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(642);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(643);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(644);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(645);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(646);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(647);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(648);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(649);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(650);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(651);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(652);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(653);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(654);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(655);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(656);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(657);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(658);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(659);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(660);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(661);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(662);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(663);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(664);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(665);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(666);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(667);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(668);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(669);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(670);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(671);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(672);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(673);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(674);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(675);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(676);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(677);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(678);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(679);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(680);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(681);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(682);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(683);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(684);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(685);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(686);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(687);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(688);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(689);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(690);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(691);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(692);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(693);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(694);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(695);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(696);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(697);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(698);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(699);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(700);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(701);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(702);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(703);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(704);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(705);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(706);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(707);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(708);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(709);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(710);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(711);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(712);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(713);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(714);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(715);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(716);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(717);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(718);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(719);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(720);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(721);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(722);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(723);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(724);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(725);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(726);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(727);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(728);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(729);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(730);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(731);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(732);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(733);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(734);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(735);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(736);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(737);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(738);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(739);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(740);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(741);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(742);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(743);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(744);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(745);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(746);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(747);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(748);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(749);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(750);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(751);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(752);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(753);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(754);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(755);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(756);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(757);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(758);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(759);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(760);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(761);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(762);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(763);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(764);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(765);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(766);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(767);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(768);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(769);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(770);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(771);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(772);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(773);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(774);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(775);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(776);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(777);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(778);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(779);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(780);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(781);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(782);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(783);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(784);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(785);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(786);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(787);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(788);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(789);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(790);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(791);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(792);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(793);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(794);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(795);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(796);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(797);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(798);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(799);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(800);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(801);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(802);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(803);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(804);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(805);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(806);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(807);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(808);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(809);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(810);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(811);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(812);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(813);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(814);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(815);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(816);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(817);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(818);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(819);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(820);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(821);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(822);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(823);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(824);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(825);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(826);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(827);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(828);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(829);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(830);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(831);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(832);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(833);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(834);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(835);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(836);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(837);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(838);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(839);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(840);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(841);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(842);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(843);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(844);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(845);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(846);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(847);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(848);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(849);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(850);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(851);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(852);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(853);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(854);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(855);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(856);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(857);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(858);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(859);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(860);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(861);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(862);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(863);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(864);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(865);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(866);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(867);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(868);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(869);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(870);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(871);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(872);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(873);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(874);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(875);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(876);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(877);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(878);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(879);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(880);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(881);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(882);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(883);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(884);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(885);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(886);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(887);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(888);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(889);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(890);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(891);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(892);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(893);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(894);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(895);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(896);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(897);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(898);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(899);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(900);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(901);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(902);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(903);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(904);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(905);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(906);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(907);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(908);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(909);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(910);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(911);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(912);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(913);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(914);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(915);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(916);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(917);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(918);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(919);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(920);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(921);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(922);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(923);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(924);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(925);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(926);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(927);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(928);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(929);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(930);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(931);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(932);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(933);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(934);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(935);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(936);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(937);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(938);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(939);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(940);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(941);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(942);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(943);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(944);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(945);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(946);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(947);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(948);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(949);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(950);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(951);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(952);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(953);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(954);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(955);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(956);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(957);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(958);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(959);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(960);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(961);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(962);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(963);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(964);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(965);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(966);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(967);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(968);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(969);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(970);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(971);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(972);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(973);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(974);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(975);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(976);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(977);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(978);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(979);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(980);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(981);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(982);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(983);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(984);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(985);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(986);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(987);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(988);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(989);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(990);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(991);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(992);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(993);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(994);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(995);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(996);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(997);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(998);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(999);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1000);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1001);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1002);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1003);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1004);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1005);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1006);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1007);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1008);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1009);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1010);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1011);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1012);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1013);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1014);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1015);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1016);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1017);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1018);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1019);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1020);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1021);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1022);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1023);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1024);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1025);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1026);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1027);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1028);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1029);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1030);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1031);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1032);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1033);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1034);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1035);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1036);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1037);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1038);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1039);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1040);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1041);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1042);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1043);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1044);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1045);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1046);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1047);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1048);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1049);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1050);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1051);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1052);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1053);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1054);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1055);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1056);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1057);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1058);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1059);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1060);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1061);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1062);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1063);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1064);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1065);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1066);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1067);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1068);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1069);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1070);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1071);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1072);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1073);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1074);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1075);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1076);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1077);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1078);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1079);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1080);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1081);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1082);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1083);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1084);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1085);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1086);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1087);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1088);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1089);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1090);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1091);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1092);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1093);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1094);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1095);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1096);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1097);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1098);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1099);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1100);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1101);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1102);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1103);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1104);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1105);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1106);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1107);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1108);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1109);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1110);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1111);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1112);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1113);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1114);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1115);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1116);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1117);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1118);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1119);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1120);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1121);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1122);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1123);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1124);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1125);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1126);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1127);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1128);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1129);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1130);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1131);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1132);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1133);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1134);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1135);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1136);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1137);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1138);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1139);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1140);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1141);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1142);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1143);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1144);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1145);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1146);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1147);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1148);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1149);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1150);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1151);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1152);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1153);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1154);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1155);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1156);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1157);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1158);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1159);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1160);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1161);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1162);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1163);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1164);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1165);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1166);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1167);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1168);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1169);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1170);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1171);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1172);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1173);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1174);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1175);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1176);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1177);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1178);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1179);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1180);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1181);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1182);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1183);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1184);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1185);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1186);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1187);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1188);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1189);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1190);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1191);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1192);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1193);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1194);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1195);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1196);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1197);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1198);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1199);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1200);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1201);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1202);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1203);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1204);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1205);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1206);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1207);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1208);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1209);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1210);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1211);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1212);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1213);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1214);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1215);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1216);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1217);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1218);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1219);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1220);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1221);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1222);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1223);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1224);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1225);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1226);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1227);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1228);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1229);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1230);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1231);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1232);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1233);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1234);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1235);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1236);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1237);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1238);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1239);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1240);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1241);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1242);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1243);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1244);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1245);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1246);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1247);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1248);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1249);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1250);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1251);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1252);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1253);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1254);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1255);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1256);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1257);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1258);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1259);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1260);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1261);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1262);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1263);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1264);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1265);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1266);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1267);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1268);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1269);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1270);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1271);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1272);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1273);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1274);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1275);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1276);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1277);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1278);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1279);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1280);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1281);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1282);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1283);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1284);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1285);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1286);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1287);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1288);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1289);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1290);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1291);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1292);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1293);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1294);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1295);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1296);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1297);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1298);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1299);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1300);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1301);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1302);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1303);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1304);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1305);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1306);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1307);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1308);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1309);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1310);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1311);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1312);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1313);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1314);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1315);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1316);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1317);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1318);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1319);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1320);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1321);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1322);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1323);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1324);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1325);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1326);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1327);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1328);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1329);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1330);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1331);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1332);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1333);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1334);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1335);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1336);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1337);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1338);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1339);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1340);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1341);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1342);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1343);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1344);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1345);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1346);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1347);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1348);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1349);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1350);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1351);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1352);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1353);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1354);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1355);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1356);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1357);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1358);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1359);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1360);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1361);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1362);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1363);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1364);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1365);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1366);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1367);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1368);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1369);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1370);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1371);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1372);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1373);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1374);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1375);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1376);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1377);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1378);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1379);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1380);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1381);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1382);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1383);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1384);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1385);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1386);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1387);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1388);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1389);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1390);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1391);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1392);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1393);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1394);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1395);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1396);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1397);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1398);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1399);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1400);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1401);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1402);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1403);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1404);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1405);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1406);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1407);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1408);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1409);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1410);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1411);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1412);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1413);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1414);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1415);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1416);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1417);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1418);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1419);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1420);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1421);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1422);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1423);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1424);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1425);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1426);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1427);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1428);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1429);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1430);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1431);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1432);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1433);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1434);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1435);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1436);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1437);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1438);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1439);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1440);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1441);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1442);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1443);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1444);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1445);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1446);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1447);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1448);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1449);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1450);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1451);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1452);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1453);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1454);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1455);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1456);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1457);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1458);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1459);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1460);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1461);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1462);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1463);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1464);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1465);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1466);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1467);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1468);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1469);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1470);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1471);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1472);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1473);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1474);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1475);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1476);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1477);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1478);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1479);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1480);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1481);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1482);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1483);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1484);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1485);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1486);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1487);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1488);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1489);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1490);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1491);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1492);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1493);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1494);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1495);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1496);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1497);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1498);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1499);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1500);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1501);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1502);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1503);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1504);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1505);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1506);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1507);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1508);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1509);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1510);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1511);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1512);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1513);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1514);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1515);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1516);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1517);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1518);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1519);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1520);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1521);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1522);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1523);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1524);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1525);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1526);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1527);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1528);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1529);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1530);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1531);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1532);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1533);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1534);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1535);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1536);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1537);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1538);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1539);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1540);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1541);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1542);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1543);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1544);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1545);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1546);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1547);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1548);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1549);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1550);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1551);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1552);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1553);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1554);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1555);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1556);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1557);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1558);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1559);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1560);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1561);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1562);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1563);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1564);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1565);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1566);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1567);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1568);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1569);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1570);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1571);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1572);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1573);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1574);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1575);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1576);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1577);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1578);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1579);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1580);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1581);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1582);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1583);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1584);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1585);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1586);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1587);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1588);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1589);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1590);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1591);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1592);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1593);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1594);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1595);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1596);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1597);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1598);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1599);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1600);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1601);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1602);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1603);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1604);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1605);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1606);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1607);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1608);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1609);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1610);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1611);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1612);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1613);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1614);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1615);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1616);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1617);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1618);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1619);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1620);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1621);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1622);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1623);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1624);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1625);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1626);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1627);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1628);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1629);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1630);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1631);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1632);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1633);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1634);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1635);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1636);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1637);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1638);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1639);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1640);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1641);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1642);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1643);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1644);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1645);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1646);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1647);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1648);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1649);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1650);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1651);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1652);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1653);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1654);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1655);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1656);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1657);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1658);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1659);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1660);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1661);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1662);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1663);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1664);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1665);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1666);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1667);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1668);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1669);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1670);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1671);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1672);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1673);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1674);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1675);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1676);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1677);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1678);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1679);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1680);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1681);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1682);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1683);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1684);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1685);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1686);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1687);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1688);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1689);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1690);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1691);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1692);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1693);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1694);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1695);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1696);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1697);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1698);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1699);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1700);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1701);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1702);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1703);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1704);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1705);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1706);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1707);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1708);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1709);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1710);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1711);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1712);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1713);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1714);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1715);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1716);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1717);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1718);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1719);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1720);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1721);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1722);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1723);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1724);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1725);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1726);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1727);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1728);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1729);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1730);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1731);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1732);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1733);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1734);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1735);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1736);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1737);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1738);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1739);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1740);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1741);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1742);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1743);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1744);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1745);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1746);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1747);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1748);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1749);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1750);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1751);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1752);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1753);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1754);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1755);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1756);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1757);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1758);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1759);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1760);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1761);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1762);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1763);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1764);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1765);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1766);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1767);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1768);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1769);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1770);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1771);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1772);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1773);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1774);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1775);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1776);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1777);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1778);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1779);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1780);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1781);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1782);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1783);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1784);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1785);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1786);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1787);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1788);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1789);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1790);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1791);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1792);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1793);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1794);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1795);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1796);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1797);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1798);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1799);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1800);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1801);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1802);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1803);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1804);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1805);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1806);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1807);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1808);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1809);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1810);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1811);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1812);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1813);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1814);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1815);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1816);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1817);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1818);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1819);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1820);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1821);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1822);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1823);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1824);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1825);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1826);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1827);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1828);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1829);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1830);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1831);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1832);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1833);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1834);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1835);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1836);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1837);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1838);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1839);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1840);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1841);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1842);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1843);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1844);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1845);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1846);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1847);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1848);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1849);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1850);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1851);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1852);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1853);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1854);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1855);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1856);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1857);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1858);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1859);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1860);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1861);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1862);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1863);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1864);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1865);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1866);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1867);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1868);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1869);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1870);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1871);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1872);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1873);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1874);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1875);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1876);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1877);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1878);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1879);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1880);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1881);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1882);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1883);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1884);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1885);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1886);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1887);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1888);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1889);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1890);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1891);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1892);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1893);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1894);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1895);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1896);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1897);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1898);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1899);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1900);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1901);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1902);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1903);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1904);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1905);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1906);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1907);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1908);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1909);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1910);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1911);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1912);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1913);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1914);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1915);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1916);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1917);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1918);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1919);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1920);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1921);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1922);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1923);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1924);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1925);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1926);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1927);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1928);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1929);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1930);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1931);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1932);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1933);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1934);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1935);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1936);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1937);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1938);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1939);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1940);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1941);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1942);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1943);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1944);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1945);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1946);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1947);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1948);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1949);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1950);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1951);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1952);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1953);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1954);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1955);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1956);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1957);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1958);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1959);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1960);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1961);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1962);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1963);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1964);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1965);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1966);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1967);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1968);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1969);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1970);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1971);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1972);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1973);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1974);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1975);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1976);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1977);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1978);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1979);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1980);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1981);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1982);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1983);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1984);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1985);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1986);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1987);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1988);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1989);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1990);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1991);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1992);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1993);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1994);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1995);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1996);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1997);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1998);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(1999);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2000);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2001);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2002);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2003);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2004);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2005);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2006);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2007);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2008);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2009);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2010);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2011);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2012);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2013);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2014);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2015);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2016);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2017);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2018);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2019);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2020);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2021);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2022);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2023);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2024);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2025);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2026);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2027);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2028);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2029);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2030);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2031);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2032);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2033);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2034);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2035);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2036);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2037);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2038);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2039);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2040);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2041);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2042);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2043);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2044);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2045);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2046);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2047);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2048);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2049);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2050);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2051);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2052);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2053);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2054);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2055);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2056);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2057);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2058);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2059);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2060);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2061);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2062);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2063);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2064);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2065);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2066);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2067);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2068);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2069);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2070);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2071);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2072);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2073);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2074);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2075);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2076);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2077);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2078);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2079);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2080);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2081);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2082);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2083);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2084);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2085);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2086);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2087);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2088);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2089);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2090);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2091);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2092);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2093);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2094);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2095);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2096);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2097);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2098);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2099);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2100);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2101);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2102);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2103);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2104);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2105);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2106);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2107);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2108);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2109);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2110);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2111);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2112);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2113);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2114);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2115);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2116);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2117);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2118);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2119);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2120);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2121);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2122);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2123);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2124);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2125);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2126);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2127);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2128);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2129);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2130);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2131);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2132);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2133);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2134);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2135);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2136);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2137);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2138);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2139);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2140);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2141);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2142);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2143);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2144);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2145);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2146);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2147);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2148);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2149);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2150);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2151);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2152);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2153);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2154);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2155);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2156);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2157);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2158);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2159);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2160);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2161);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2162);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2163);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2164);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2165);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2166);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2167);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2168);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2169);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2170);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2171);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2172);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2173);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2174);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2175);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2176);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2177);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2178);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2179);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2180);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2181);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2182);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2183);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2184);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2185);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2186);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2187);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2188);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2189);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2190);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2191);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2192);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2193);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2194);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2195);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2196);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2197);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2198);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2199);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2200);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2201);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2202);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2203);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2204);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2205);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2206);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2207);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2208);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2209);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2210);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2211);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2212);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2213);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2214);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2215);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2216);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2217);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2218);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2219);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2220);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2221);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2222);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2223);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2224);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2225);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2226);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2227);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2228);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2229);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2230);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2231);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2232);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2233);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2234);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2235);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2236);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2237);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2238);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2239);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2240);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2241);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2242);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2243);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2244);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2245);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2246);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2247);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2248);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2249);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2250);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2251);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2252);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2253);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2254);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2255);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2256);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2257);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2258);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2259);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2260);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2261);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2262);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2263);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2264);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2265);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2266);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2267);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2268);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2269);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2270);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2271);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2272);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2273);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2274);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2275);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2276);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2277);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2278);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2279);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2280);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2281);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2282);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2283);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2284);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2285);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2286);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2287);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2288);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2289);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2290);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2291);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2292);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2293);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2294);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2295);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2296);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2297);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2298);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2299);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2300);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2301);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2302);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2303);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2304);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2305);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2306);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2307);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2308);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2309);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2310);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2311);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2312);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2313);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2314);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2315);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2316);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2317);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2318);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2319);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2320);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2321);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2322);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2323);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2324);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2325);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2326);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2327);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2328);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2329);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2330);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2331);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2332);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2333);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2334);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2335);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2336);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2337);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2338);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2339);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2340);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2341);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2342);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2343);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2344);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2345);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2346);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2347);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2348);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2349);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2350);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2351);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2352);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2353);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2354);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2355);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2356);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2357);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2358);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2359);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2360);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2361);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2362);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2363);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2364);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2365);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2366);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2367);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2368);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2369);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2370);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2371);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2372);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2373);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2374);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2375);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2376);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2377);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2378);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2379);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2380);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2381);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2382);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2383);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2384);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2385);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2386);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2387);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2388);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2389);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2390);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2391);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2392);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2393);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2394);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2395);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2396);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2397);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2398);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2399);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2400);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2401);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2402);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2403);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2404);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2405);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2406);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2407);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2408);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2409);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2410);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2411);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2412);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2413);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2414);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2415);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2416);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2417);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2418);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2419);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2420);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2421);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2422);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2423);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2424);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2425);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2426);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2427);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2428);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2429);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2430);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2431);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2432);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2433);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2434);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2435);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2436);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2437);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2438);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2439);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2440);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2441);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2442);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2443);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2444);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2445);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2446);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2447);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2448);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2449);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2450);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2451);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2452);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2453);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2454);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2455);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2456);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2457);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2458);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2459);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2460);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2461);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2462);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2463);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2464);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2465);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2466);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2467);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2468);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2469);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2470);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2471);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2472);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2473);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2474);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2475);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2476);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2477);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2478);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2479);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2480);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2481);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2482);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2483);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2484);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2485);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2486);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2487);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2488);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2489);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2490);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2491);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2492);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2493);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2494);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2495);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2496);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2497);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2498);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2499);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2500);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2501);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2502);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2503);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2504);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2505);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2506);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2507);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2508);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2509);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2510);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2511);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2512);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2513);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2514);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2515);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2516);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2517);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2518);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2519);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2520);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2521);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2522);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2523);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2524);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2525);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2526);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2527);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2528);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2529);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2530);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2531);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2532);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2533);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2534);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2535);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2536);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2537);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2538);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2539);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2540);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2541);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2542);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2543);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2544);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2545);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2546);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2547);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2548);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2549);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2550);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2551);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2552);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2553);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2554);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2555);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2556);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2557);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2558);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2559);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2560);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2561);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2562);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2563);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2564);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2565);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2566);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2567);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2568);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2569);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2570);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2571);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2572);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2573);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2574);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2575);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2576);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2577);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2578);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2579);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2580);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2581);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2582);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2583);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2584);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2585);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2586);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2587);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2588);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2589);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2590);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2591);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2592);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2593);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2594);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2595);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2596);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2597);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2598);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2599);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2600);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2601);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2602);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2603);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2604);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2605);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2606);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2607);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2608);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2609);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2610);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2611);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2612);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2613);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2614);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2615);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2616);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2617);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2618);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2619);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2620);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2621);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2622);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2623);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2624);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2625);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2626);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2627);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2628);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2629);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2630);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2631);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2632);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2633);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2634);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2635);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2636);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2637);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2638);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2639);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2640);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2641);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2642);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2643);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2644);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2645);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2646);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2647);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2648);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2649);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2650);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2651);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2652);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2653);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2654);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2655);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2656);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2657);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2658);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2659);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2660);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2661);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2662);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2663);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2664);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2665);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2666);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2667);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2668);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2669);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2670);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2671);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2672);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2673);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2674);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2675);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2676);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2677);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2678);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2679);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2680);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2681);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2682);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2683);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2684);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2685);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2686);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2687);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2688);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2689);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2690);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2691);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2692);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2693);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2694);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2695);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2696);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2697);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2698);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2699);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2700);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2701);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2702);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2703);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2704);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2705);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2706);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2707);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2708);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2709);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2710);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2711);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2712);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2713);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2714);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2715);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2716);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2717);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2718);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2719);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2720);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2721);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2722);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2723);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2724);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2725);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2726);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2727);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2728);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2729);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2730);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2731);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2732);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2733);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2734);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2735);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2736);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2737);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2738);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2739);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2740);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2741);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2742);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2743);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2744);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2745);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2746);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2747);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2748);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2749);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2750);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2751);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2752);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2753);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2754);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2755);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2756);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2757);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2758);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2759);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2760);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2761);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2762);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2763);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2764);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2765);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2766);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2767);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2768);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2769);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2770);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2771);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2772);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2773);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2774);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2775);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2776);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2777);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2778);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2779);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2780);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2781);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2782);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2783);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2784);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2785);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2786);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2787);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2788);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2789);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2790);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2791);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2792);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2793);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2794);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2795);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2796);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2797);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2798);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2799);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2800);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2801);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2802);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2803);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2804);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2805);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2806);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2807);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2808);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2809);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2810);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2811);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2812);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2813);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2814);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2815);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2816);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2817);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2818);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2819);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2820);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2821);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2822);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2823);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2824);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2825);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2826);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2827);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2828);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2829);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2830);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2831);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2832);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2833);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2834);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2835);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2836);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2837);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2838);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2839);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2840);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2841);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2842);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2843);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2844);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2845);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2846);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2847);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2848);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2849);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2850);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2851);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2852);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2853);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2854);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2855);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2856);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2857);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2858);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2859);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2860);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2861);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2862);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2863);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2864);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2865);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2866);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2867);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2868);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2869);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2870);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2871);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2872);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2873);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2874);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2875);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2876);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2877);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2878);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2879);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2880);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2881);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2882);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2883);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2884);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2885);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2886);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2887);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2888);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2889);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2890);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2891);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2892);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2893);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2894);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2895);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2896);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2897);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2898);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2899);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2900);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2901);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2902);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2903);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2904);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2905);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2906);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2907);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2908);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2909);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2910);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2911);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2912);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2913);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2914);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2915);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2916);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2917);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2918);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2919);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2920);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2921);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2922);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2923);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2924);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2925);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2926);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2927);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2928);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2929);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2930);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2931);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2932);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2933);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2934);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2935);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2936);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2937);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2938);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2939);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2940);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2941);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2942);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2943);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2944);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2945);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2946);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2947);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2948);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2949);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2950);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2951);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2952);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2953);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2954);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2955);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2956);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2957);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2958);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2959);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2960);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2961);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2962);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2963);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2964);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2965);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2966);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2967);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2968);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2969);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2970);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2971);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2972);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2973);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2974);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2975);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2976);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2977);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2978);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2979);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2980);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2981);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2982);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2983);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2984);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2985);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2986);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2987);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2988);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2989);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2990);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2991);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2992);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2993);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2994);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2995);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2996);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2997);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2998);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(2999);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3000);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3001);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3002);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3003);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3004);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3005);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3006);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3007);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3008);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3009);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3010);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3011);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3012);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3013);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3014);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3015);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3016);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3017);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3018);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3019);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3020);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3021);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3022);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3023);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3024);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3025);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3026);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3027);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3028);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3029);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3030);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3031);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3032);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3033);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3034);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3035);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3036);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3037);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3038);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3039);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3040);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3041);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3042);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3043);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3044);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3045);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3046);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3047);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3048);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3049);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3050);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3051);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3052);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3053);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3054);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3055);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3056);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3057);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3058);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3059);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3060);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3061);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3062);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3063);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3064);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3065);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3066);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3067);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3068);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3069);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3070);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3071);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3072);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3073);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3074);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3075);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3076);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3077);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3078);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3079);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3080);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3081);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3082);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3083);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3084);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3085);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3086);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3087);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3088);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3089);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3090);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3091);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3092);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3093);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3094);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3095);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3096);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3097);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3098);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3099);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3100);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3101);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3102);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3103);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3104);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3105);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3106);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3107);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3108);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3109);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3110);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3111);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3112);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3113);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3114);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3115);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3116);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3117);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3118);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3119);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3120);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3121);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3122);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3123);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3124);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3125);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3126);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3127);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3128);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3129);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3130);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3131);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3132);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3133);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3134);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3135);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3136);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3137);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3138);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3139);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3140);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3141);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3142);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3143);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3144);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3145);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3146);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3147);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3148);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3149);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3150);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3151);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3152);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3153);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3154);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3155);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3156);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3157);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3158);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3159);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3160);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3161);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3162);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3163);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3164);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3165);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3166);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3167);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3168);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3169);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3170);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3171);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3172);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3173);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3174);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3175);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3176);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3177);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3178);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3179);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3180);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3181);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3182);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3183);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3184);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3185);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3186);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3187);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3188);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3189);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3190);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3191);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3192);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3193);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3194);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3195);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3196);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3197);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3198);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3199);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3200);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3201);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3202);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3203);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3204);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3205);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3206);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3207);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3208);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3209);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3210);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3211);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3212);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3213);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3214);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3215);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3216);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3217);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3218);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3219);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3220);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3221);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3222);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3223);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3224);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3225);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3226);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3227);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3228);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3229);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3230);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3231);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3232);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3233);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3234);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3235);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3236);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3237);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3238);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3239);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3240);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3241);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3242);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3243);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3244);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3245);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3246);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3247);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3248);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3249);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3250);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3251);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3252);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3253);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3254);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3255);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3256);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3257);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3258);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3259);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3260);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3261);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3262);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3263);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3264);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3265);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3266);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3267);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3268);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3269);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3270);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3271);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3272);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3273);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3274);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3275);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3276);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3277);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3278);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3279);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3280);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3281);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3282);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3283);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3284);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3285);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3286);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3287);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3288);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3289);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3290);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3291);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3292);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3293);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3294);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3295);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3296);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3297);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3298);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3299);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3300);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3301);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3302);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3303);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3304);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3305);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3306);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3307);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3308);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3309);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3310);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3311);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3312);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3313);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3314);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3315);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3316);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3317);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3318);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3319);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3320);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3321);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3322);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3323);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3324);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3325);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3326);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3327);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3328);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3329);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3330);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3331);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3332);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3333);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3334);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3335);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3336);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3337);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3338);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3339);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3340);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3341);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3342);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3343);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3344);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3345);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3346);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3347);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3348);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3349);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3350);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3351);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3352);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3353);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3354);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3355);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3356);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3357);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3358);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3359);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3360);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3361);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3362);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3363);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3364);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3365);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3366);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3367);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3368);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3369);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3370);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3371);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3372);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3373);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3374);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3375);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3376);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3377);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3378);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3379);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3380);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3381);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3382);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3383);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3384);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3385);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3386);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3387);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3388);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3389);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3390);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3391);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3392);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3393);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3394);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3395);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3396);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3397);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3398);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3399);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3400);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3401);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3402);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3403);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3404);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3405);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3406);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3407);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3408);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3409);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3410);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3411);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3412);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3413);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3414);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3415);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3416);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3417);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3418);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3419);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3420);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3421);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3422);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3423);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3424);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3425);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3426);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3427);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3428);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3429);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3430);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3431);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3432);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3433);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3434);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3435);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3436);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3437);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3438);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3439);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3440);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3441);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3442);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3443);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3444);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3445);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3446);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3447);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3448);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3449);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3450);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3451);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3452);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3453);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3454);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3455);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3456);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3457);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3458);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3459);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3460);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3461);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3462);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3463);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3464);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3465);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3466);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3467);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3468);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3469);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3470);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3471);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3472);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3473);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3474);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3475);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3476);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3477);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3478);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3479);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3480);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3481);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3482);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3483);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3484);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3485);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3486);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3487);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3488);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3489);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3490);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3491);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3492);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3493);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3494);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3495);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3496);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3497);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3498);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3499);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3500);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3501);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3502);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3503);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3504);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3505);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3506);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3507);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3508);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3509);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3510);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3511);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3512);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3513);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3514);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3515);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3516);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3517);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3518);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3519);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3520);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3521);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3522);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3523);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3524);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3525);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3526);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3527);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3528);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3529);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3530);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3531);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3532);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3533);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3534);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3535);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3536);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3537);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3538);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3539);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3540);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3541);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3542);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3543);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3544);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3545);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3546);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3547);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3548);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3549);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3550);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3551);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3552);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3553);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3554);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3555);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3556);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3557);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3558);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3559);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3560);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3561);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3562);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3563);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3564);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3565);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3566);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3567);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3568);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3569);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3570);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3571);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3572);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3573);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3574);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3575);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3576);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3577);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3578);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3579);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3580);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3581);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3582);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3583);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3584);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3585);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3586);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3587);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3588);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3589);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3590);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3591);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3592);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3593);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3594);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3595);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3596);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3597);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3598);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3599);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3600);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3601);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3602);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3603);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3604);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3605);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3606);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3607);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3608);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3609);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3610);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3611);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3612);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3613);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3614);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3615);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3616);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3617);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3618);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3619);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3620);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3621);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3622);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3623);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3624);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3625);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3626);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3627);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3628);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3629);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3630);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3631);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3632);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3633);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3634);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3635);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3636);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3637);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3638);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3639);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3640);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3641);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3642);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3643);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3644);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3645);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3646);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3647);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3648);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3649);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3650);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3651);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3652);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3653);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3654);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3655);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3656);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3657);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3658);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3659);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3660);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3661);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3662);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3663);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3664);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3665);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3666);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3667);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3668);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3669);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3670);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3671);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3672);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3673);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3674);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3675);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3676);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3677);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3678);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3679);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3680);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3681);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3682);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3683);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3684);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3685);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3686);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3687);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3688);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3689);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3690);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3691);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3692);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3693);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3694);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3695);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3696);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3697);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3698);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3699);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3700);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3701);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3702);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3703);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3704);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3705);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3706);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3707);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3708);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3709);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3710);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3711);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3712);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3713);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3714);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3715);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3716);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3717);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3718);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3719);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3720);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3721);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3722);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3723);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3724);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3725);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3726);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3727);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3728);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3729);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3730);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3731);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3732);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3733);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3734);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3735);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3736);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3737);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3738);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3739);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3740);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3741);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3742);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3743);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3744);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3745);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3746);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3747);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3748);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3749);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3750);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3751);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3752);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3753);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3754);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3755);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3756);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3757);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3758);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3759);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3760);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3761);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3762);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3763);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3764);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3765);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3766);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3767);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3768);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3769);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3770);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3771);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3772);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3773);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3774);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3775);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3776);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3777);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3778);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3779);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3780);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3781);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3782);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3783);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3784);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3785);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3786);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3787);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3788);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3789);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3790);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3791);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3792);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3793);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3794);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3795);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3796);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3797);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3798);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3799);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3800);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3801);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3802);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3803);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3804);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3805);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3806);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3807);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3808);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3809);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3810);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3811);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3812);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3813);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3814);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3815);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3816);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3817);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3818);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3819);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3820);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3821);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3822);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3823);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3824);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3825);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3826);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3827);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3828);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3829);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3830);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3831);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3832);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3833);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3834);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3835);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3836);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3837);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3838);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3839);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3840);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3841);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3842);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3843);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3844);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3845);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3846);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3847);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3848);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3849);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3850);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3851);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3852);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3853);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3854);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3855);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3856);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3857);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3858);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3859);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3860);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3861);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3862);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3863);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3864);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3865);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3866);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3867);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3868);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3869);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3870);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3871);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3872);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3873);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3874);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3875);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3876);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3877);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3878);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3879);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3880);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3881);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3882);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3883);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3884);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3885);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3886);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3887);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3888);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3889);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3890);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3891);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3892);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3893);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3894);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3895);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3896);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3897);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3898);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3899);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3900);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3901);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3902);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3903);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3904);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3905);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3906);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3907);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3908);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3909);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3910);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3911);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3912);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3913);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3914);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3915);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3916);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3917);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3918);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3919);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3920);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3921);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3922);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3923);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3924);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3925);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3926);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3927);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3928);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3929);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3930);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3931);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3932);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3933);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3934);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3935);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3936);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3937);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3938);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3939);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3940);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3941);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3942);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3943);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3944);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3945);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3946);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3947);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3948);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3949);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3950);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3951);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3952);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3953);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3954);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3955);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3956);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3957);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3958);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3959);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3960);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3961);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3962);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3963);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3964);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3965);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3966);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3967);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3968);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3969);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3970);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3971);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3972);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3973);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3974);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3975);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3976);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3977);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3978);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3979);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3980);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3981);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3982);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3983);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3984);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3985);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3986);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3987);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3988);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3989);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3990);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3991);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3992);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3993);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3994);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3995);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3996);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3997);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3998);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(3999);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4000);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4001);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4002);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4003);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4004);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4005);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4006);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4007);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4008);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4009);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4010);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4011);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4012);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4013);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4014);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4015);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4016);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4017);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4018);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4019);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4020);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4021);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4022);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4023);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4024);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4025);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4026);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4027);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4028);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4029);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4030);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4031);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4032);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4033);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4034);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4035);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4036);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4037);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4038);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4039);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4040);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4041);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4042);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4043);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4044);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4045);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4046);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4047);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4048);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4049);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4050);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4051);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4052);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4053);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4054);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4055);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4056);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4057);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4058);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4059);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4060);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4061);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4062);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4063);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4064);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4065);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4066);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4067);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4068);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4069);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4070);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4071);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4072);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4073);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4074);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4075);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4076);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4077);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4078);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4079);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4080);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4081);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4082);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4083);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4084);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4085);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4086);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4087);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4088);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4089);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4090);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4091);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4092);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4093);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4094);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4095);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4096);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4097);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4098);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4099);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4100);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4101);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4102);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4103);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4104);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4105);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4106);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4107);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4108);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4109);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4110);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4111);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4112);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4113);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4114);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4115);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4116);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4117);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4118);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4119);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4120);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4121);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4122);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4123);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4124);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4125);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4126);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4127);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4128);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4129);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4130);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4131);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4132);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4133);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4134);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4135);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4136);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4137);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4138);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4139);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4140);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4141);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4142);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4143);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4144);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4145);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4146);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4147);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4148);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4149);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4150);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4151);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4152);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4153);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4154);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4155);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4156);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4157);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4158);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4159);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4160);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4161);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4162);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4163);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4164);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4165);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4166);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4167);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4168);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4169);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4170);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4171);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4172);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4173);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4174);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4175);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4176);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4177);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4178);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4179);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4180);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4181);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4182);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4183);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4184);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4185);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4186);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4187);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4188);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4189);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4190);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4191);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4192);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4193);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4194);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4195);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4196);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4197);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4198);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4199);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4200);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4201);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4202);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4203);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4204);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4205);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4206);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4207);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4208);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4209);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4210);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4211);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4212);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4213);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4214);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4215);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4216);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4217);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4218);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4219);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4220);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4221);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4222);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4223);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4224);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4225);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4226);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4227);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4228);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4229);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4230);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4231);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4232);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4233);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4234);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4235);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4236);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4237);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4238);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4239);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4240);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4241);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4242);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4243);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4244);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4245);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4246);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4247);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4248);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4249);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4250);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4251);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4252);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4253);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4254);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4255);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4256);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4257);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4258);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4259);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4260);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4261);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4262);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4263);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4264);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4265);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4266);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4267);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4268);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4269);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4270);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4271);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4272);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4273);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4274);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4275);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4276);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4277);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4278);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4279);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4280);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4281);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4282);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4283);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4284);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4285);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4286);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4287);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4288);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4289);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4290);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4291);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4292);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4293);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4294);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4295);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4296);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4297);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4298);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4299);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4300);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4301);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4302);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4303);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4304);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4305);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4306);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4307);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4308);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4309);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4310);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4311);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4312);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4313);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4314);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4315);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4316);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4317);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4318);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4319);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4320);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4321);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4322);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4323);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4324);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4325);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4326);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4327);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4328);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4329);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4330);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4331);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4332);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4333);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4334);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4335);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4336);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4337);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4338);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4339);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4340);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4341);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4342);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4343);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4344);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4345);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4346);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4347);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4348);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4349);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4350);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4351);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4352);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4353);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4354);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4355);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4356);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4357);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4358);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4359);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4360);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4361);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4362);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4363);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4364);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4365);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4366);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4367);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4368);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4369);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4370);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4371);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4372);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4373);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4374);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4375);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4376);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4377);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4378);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4379);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4380);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4381);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4382);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4383);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4384);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4385);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4386);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4387);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4388);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4389);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4390);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4391);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4392);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4393);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4394);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4395);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4396);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4397);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4398);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4399);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4400);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4401);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4402);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4403);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4404);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4405);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4406);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4407);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4408);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4409);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4410);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4411);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4412);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4413);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4414);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4415);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4416);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4417);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4418);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4419);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4420);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4421);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4422);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4423);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4424);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4425);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4426);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4427);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4428);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4429);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4430);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4431);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4432);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4433);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4434);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4435);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4436);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4437);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4438);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4439);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4440);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4441);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4442);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4443);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4444);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4445);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4446);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4447);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4448);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4449);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4450);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4451);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4452);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4453);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4454);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4455);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4456);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4457);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4458);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4459);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4460);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4461);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4462);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4463);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4464);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4465);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4466);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4467);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4468);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4469);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4470);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4471);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4472);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4473);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4474);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4475);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4476);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4477);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4478);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4479);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4480);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4481);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4482);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4483);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4484);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4485);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4486);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4487);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4488);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4489);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4490);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4491);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4492);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4493);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4494);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4495);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4496);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4497);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4498);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4499);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4500);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4501);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4502);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4503);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4504);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4505);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4506);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4507);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4508);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4509);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4510);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4511);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4512);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4513);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4514);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4515);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4516);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4517);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4518);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4519);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4520);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4521);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4522);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4523);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4524);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4525);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4526);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4527);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4528);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4529);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4530);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4531);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4532);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4533);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4534);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4535);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4536);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4537);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4538);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4539);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4540);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4541);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4542);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4543);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4544);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4545);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4546);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4547);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4548);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4549);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4550);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4551);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4552);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4553);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4554);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4555);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4556);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4557);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4558);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4559);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4560);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4561);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4562);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4563);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4564);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4565);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4566);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4567);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4568);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4569);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4570);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4571);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4572);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4573);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4574);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4575);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4576);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4577);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4578);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4579);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4580);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4581);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4582);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4583);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4584);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4585);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4586);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4587);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4588);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4589);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4590);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4591);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4592);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4593);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4594);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4595);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4596);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4597);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4598);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4599);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4600);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4601);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4602);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4603);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4604);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4605);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4606);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4607);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4608);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4609);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4610);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4611);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4612);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4613);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4614);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4615);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4616);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4617);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4618);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4619);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4620);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4621);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4622);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4623);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4624);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4625);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4626);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4627);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4628);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4629);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4630);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4631);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4632);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4633);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4634);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4635);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4636);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4637);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4638);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4639);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4640);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4641);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4642);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4643);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4644);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4645);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4646);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4647);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4648);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4649);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4650);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4651);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4652);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4653);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4654);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4655);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4656);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4657);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4658);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4659);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4660);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4661);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4662);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4663);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4664);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4665);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4666);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4667);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4668);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4669);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4670);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4671);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4672);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4673);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4674);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4675);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4676);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4677);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4678);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4679);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4680);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4681);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4682);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4683);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4684);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4685);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4686);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4687);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4688);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4689);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4690);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4691);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4692);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4693);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4694);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4695);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4696);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4697);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4698);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4699);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4700);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4701);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4702);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4703);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4704);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4705);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4706);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4707);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4708);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4709);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4710);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4711);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4712);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4713);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4714);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4715);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4716);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4717);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4718);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4719);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4720);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4721);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4722);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4723);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4724);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4725);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4726);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4727);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4728);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4729);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4730);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4731);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4732);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4733);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4734);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4735);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4736);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4737);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4738);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4739);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4740);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4741);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4742);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4743);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4744);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4745);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4746);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4747);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4748);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4749);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4750);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4751);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4752);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4753);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4754);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4755);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4756);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4757);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4758);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4759);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4760);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4761);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4762);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4763);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4764);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4765);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4766);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4767);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4768);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4769);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4770);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4771);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4772);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4773);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4774);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4775);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4776);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4777);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4778);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4779);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4780);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4781);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4782);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4783);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4784);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4785);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4786);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4787);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4788);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4789);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4790);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4791);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4792);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4793);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4794);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4795);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4796);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4797);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4798);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4799);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4800);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4801);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4802);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4803);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4804);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4805);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4806);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4807);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4808);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4809);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4810);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4811);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4812);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4813);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4814);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4815);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4816);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4817);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4818);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4819);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4820);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4821);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4822);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4823);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4824);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4825);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4826);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4827);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4828);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4829);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4830);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4831);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4832);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4833);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4834);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4835);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4836);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4837);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4838);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4839);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4840);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4841);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4842);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4843);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4844);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4845);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4846);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4847);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4848);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4849);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4850);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4851);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4852);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4853);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4854);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4855);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4856);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4857);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4858);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4859);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4860);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4861);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4862);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4863);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4864);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4865);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4866);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4867);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4868);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4869);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4870);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4871);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4872);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4873);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4874);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4875);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4876);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4877);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4878);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4879);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4880);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4881);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4882);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4883);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4884);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4885);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4886);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4887);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4888);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4889);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4890);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4891);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4892);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4893);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4894);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4895);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4896);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4897);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4898);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4899);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4900);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4901);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4902);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4903);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4904);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4905);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4906);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4907);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4908);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4909);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4910);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4911);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4912);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4913);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4914);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4915);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4916);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4917);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4918);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4919);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4920);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4921);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4922);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4923);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4924);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4925);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4926);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4927);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4928);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4929);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4930);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4931);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4932);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4933);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4934);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4935);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4936);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4937);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4938);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4939);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4940);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4941);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4942);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4943);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4944);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4945);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4946);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4947);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4948);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4949);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4950);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4951);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4952);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4953);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4954);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4955);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4956);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4957);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4958);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4959);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4960);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4961);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4962);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4963);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4964);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4965);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4966);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4967);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4968);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4969);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4970);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4971);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4972);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4973);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4974);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4975);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4976);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4977);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4978);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4979);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4980);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4981);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4982);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4983);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4984);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4985);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4986);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4987);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4988);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4989);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4990);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4991);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4992);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4993);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4994);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4995);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4996);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4997);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4998);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(4999);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5000);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5001);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5002);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5003);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5004);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5005);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5006);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5007);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5008);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5009);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5010);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5011);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5012);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5013);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5014);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5015);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5016);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5017);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5018);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5019);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5020);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5021);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5022);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5023);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5024);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5025);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5026);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5027);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5028);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5029);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5030);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5031);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5032);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5033);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5034);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5035);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5036);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5037);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5038);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5039);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5040);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5041);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5042);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5043);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5044);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5045);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5046);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5047);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5048);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5049);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5050);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5051);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5052);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5053);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5054);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5055);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5056);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5057);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5058);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5059);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5060);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5061);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5062);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5063);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5064);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5065);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5066);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5067);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5068);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5069);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5070);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5071);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5072);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5073);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5074);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5075);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5076);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5077);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5078);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5079);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5080);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5081);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5082);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5083);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5084);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5085);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5086);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5087);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5088);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5089);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5090);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5091);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5092);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5093);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5094);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5095);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5096);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5097);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5098);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5099);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5100);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5101);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5102);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5103);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5104);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5105);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5106);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5107);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5108);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5109);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5110);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5111);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5112);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5113);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5114);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5115);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5116);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5117);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5118);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5119);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5120);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5121);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5122);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5123);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5124);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5125);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5126);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5127);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5128);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5129);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5130);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5131);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5132);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5133);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5134);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5135);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5136);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5137);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5138);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5139);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5140);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5141);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5142);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5143);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5144);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5145);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5146);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5147);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5148);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5149);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5150);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5151);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5152);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5153);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5154);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5155);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5156);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5157);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5158);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5159);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5160);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5161);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5162);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5163);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5164);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5165);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5166);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5167);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5168);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5169);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5170);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5171);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5172);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5173);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5174);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5175);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5176);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5177);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5178);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5179);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5180);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5181);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5182);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5183);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5184);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5185);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5186);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5187);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5188);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5189);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5190);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5191);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5192);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5193);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5194);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5195);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5196);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5197);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5198);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5199);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5200);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5201);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5202);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5203);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5204);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5205);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5206);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5207);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5208);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5209);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5210);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5211);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5212);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5213);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5214);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5215);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5216);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5217);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5218);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5219);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5220);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5221);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5222);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5223);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5224);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5225);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5226);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5227);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5228);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5229);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5230);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5231);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5232);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5233);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5234);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5235);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5236);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5237);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5238);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5239);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5240);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5241);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5242);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5243);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5244);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5245);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5246);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5247);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5248);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5249);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5250);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5251);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5252);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5253);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5254);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5255);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5256);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5257);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5258);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5259);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5260);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5261);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5262);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5263);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5264);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5265);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5266);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5267);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5268);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5269);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5270);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5271);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5272);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5273);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5274);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5275);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5276);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5277);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5278);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5279);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5280);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5281);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5282);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5283);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5284);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5285);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5286);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5287);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5288);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5289);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5290);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5291);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5292);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5293);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5294);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5295);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5296);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5297);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5298);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5299);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5300);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5301);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5302);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5303);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5304);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5305);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5306);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5307);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5308);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5309);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5310);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5311);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5312);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5313);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5314);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5315);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5316);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5317);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5318);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5319);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5320);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5321);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5322);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5323);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5324);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5325);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5326);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5327);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5328);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5329);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5330);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5331);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5332);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5333);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5334);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5335);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5336);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5337);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5338);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5339);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5340);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5341);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5342);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5343);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5344);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5345);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5346);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5347);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5348);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5349);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5350);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5351);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5352);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5353);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5354);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5355);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5356);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5357);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5358);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5359);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5360);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5361);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5362);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5363);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5364);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5365);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5366);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5367);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5368);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5369);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5370);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5371);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5372);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5373);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5374);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5375);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5376);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5377);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5378);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5379);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5380);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5381);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5382);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5383);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5384);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5385);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5386);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5387);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5388);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5389);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5390);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5391);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5392);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5393);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5394);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5395);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5396);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5397);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5398);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5399);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5400);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5401);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5402);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5403);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5404);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5405);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5406);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5407);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5408);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5409);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5410);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5411);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5412);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5413);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5414);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5415);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5416);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5417);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5418);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5419);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5420);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5421);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5422);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5423);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5424);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5425);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5426);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5427);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5428);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5429);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5430);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5431);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5432);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5433);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5434);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5435);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5436);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5437);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5438);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5439);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5440);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5441);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5442);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5443);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5444);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5445);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5446);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5447);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5448);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5449);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5450);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5451);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5452);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5453);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5454);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5455);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5456);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5457);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5458);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5459);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5460);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5461);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5462);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5463);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5464);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5465);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5466);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5467);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5468);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5469);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5470);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5471);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5472);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5473);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5474);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5475);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5476);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5477);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5478);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5479);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5480);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5481);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5482);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5483);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5484);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5485);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5486);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5487);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5488);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5489);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5490);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5491);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5492);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5493);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5494);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5495);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5496);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5497);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5498);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5499);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5500);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5501);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5502);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5503);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5504);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5505);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5506);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5507);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5508);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5509);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5510);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5511);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5512);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5513);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5514);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5515);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5516);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5517);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5518);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5519);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5520);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5521);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5522);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5523);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5524);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5525);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5526);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5527);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5528);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5529);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5530);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5531);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5532);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5533);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5534);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5535);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5536);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5537);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5538);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5539);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5540);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5541);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5542);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5543);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5544);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5545);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5546);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5547);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5548);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5549);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5550);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5551);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5552);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5553);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5554);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5555);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5556);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5557);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5558);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5559);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5560);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5561);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5562);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5563);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5564);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5565);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5566);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5567);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5568);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5569);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5570);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5571);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5572);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5573);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5574);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5575);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5576);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5577);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5578);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5579);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5580);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5581);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5582);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5583);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5584);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5585);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5586);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5587);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5588);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5589);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5590);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5591);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5592);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5593);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5594);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5595);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5596);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5597);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5598);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5599);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5600);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5601);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5602);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5603);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5604);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5605);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5606);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5607);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5608);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5609);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5610);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5611);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5612);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5613);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5614);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5615);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5616);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5617);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5618);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5619);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5620);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5621);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5622);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5623);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5624);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5625);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5626);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5627);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5628);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5629);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5630);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5631);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5632);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5633);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5634);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5635);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5636);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5637);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5638);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5639);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5640);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5641);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5642);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5643);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5644);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5645);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5646);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5647);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5648);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5649);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5650);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5651);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5652);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5653);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5654);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5655);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5656);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5657);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5658);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5659);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5660);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5661);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5662);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5663);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5664);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5665);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5666);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5667);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5668);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5669);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5670);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5671);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5672);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5673);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5674);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5675);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5676);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5677);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5678);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5679);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5680);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5681);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5682);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5683);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5684);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5685);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5686);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5687);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5688);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5689);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5690);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5691);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5692);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5693);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5694);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5695);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5696);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5697);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5698);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5699);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5700);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5701);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5702);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5703);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5704);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5705);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5706);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5707);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5708);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5709);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5710);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5711);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5712);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5713);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5714);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5715);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5716);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5717);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5718);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5719);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5720);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5721);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5722);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5723);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5724);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5725);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5726);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5727);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5728);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5729);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5730);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5731);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5732);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5733);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5734);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5735);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5736);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5737);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5738);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5739);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5740);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5741);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5742);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5743);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5744);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5745);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5746);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5747);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5748);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5749);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5750);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5751);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5752);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5753);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5754);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5755);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5756);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5757);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5758);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5759);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5760);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5761);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5762);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5763);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5764);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5765);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5766);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5767);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5768);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5769);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5770);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5771);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5772);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5773);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5774);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5775);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5776);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5777);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5778);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5779);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5780);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5781);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5782);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5783);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5784);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5785);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5786);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5787);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5788);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5789);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5790);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5791);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5792);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5793);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5794);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5795);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5796);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5797);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5798);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5799);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5800);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5801);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5802);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5803);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5804);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5805);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5806);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5807);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5808);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5809);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5810);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5811);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5812);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5813);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5814);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5815);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5816);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5817);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5818);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5819);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5820);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5821);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5822);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5823);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5824);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5825);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5826);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5827);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5828);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5829);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5830);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5831);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5832);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5833);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5834);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5835);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5836);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5837);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5838);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5839);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5840);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5841);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5842);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5843);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5844);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5845);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5846);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5847);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5848);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5849);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5850);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5851);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5852);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5853);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5854);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5855);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5856);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5857);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5858);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5859);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5860);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5861);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5862);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5863);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5864);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5865);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5866);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5867);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5868);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5869);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5870);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5871);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5872);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5873);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5874);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5875);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5876);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5877);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5878);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5879);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5880);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5881);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5882);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5883);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5884);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5885);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5886);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5887);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5888);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5889);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5890);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5891);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5892);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5893);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5894);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5895);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5896);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5897);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5898);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5899);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5900);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5901);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5902);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5903);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5904);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5905);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5906);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5907);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5908);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5909);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5910);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5911);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5912);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5913);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5914);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5915);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5916);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5917);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5918);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5919);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5920);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5921);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5922);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5923);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5924);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5925);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5926);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5927);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5928);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5929);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5930);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5931);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5932);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5933);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5934);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5935);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5936);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5937);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5938);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5939);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5940);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5941);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5942);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5943);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5944);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5945);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5946);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5947);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5948);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5949);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5950);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5951);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5952);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5953);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5954);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5955);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5956);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5957);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5958);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5959);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5960);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5961);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5962);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5963);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5964);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5965);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5966);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5967);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5968);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5969);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5970);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5971);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5972);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5973);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5974);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5975);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5976);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5977);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5978);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5979);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5980);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5981);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5982);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5983);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5984);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5985);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5986);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5987);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5988);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5989);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5990);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5991);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5992);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5993);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5994);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5995);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5996);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5997);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5998);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(5999);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6000);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6001);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6002);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6003);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6004);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6005);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6006);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6007);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6008);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6009);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6010);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6011);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6012);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6013);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6014);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6015);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6016);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6017);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6018);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6019);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6020);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6021);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6022);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6023);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6024);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6025);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6026);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6027);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6028);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6029);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6030);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6031);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6032);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6033);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6034);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6035);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6036);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6037);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6038);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6039);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6040);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6041);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6042);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6043);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6044);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6045);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6046);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6047);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6048);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6049);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6050);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6051);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6052);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6053);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6054);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6055);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6056);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6057);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6058);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6059);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6060);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6061);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6062);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6063);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6064);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6065);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6066);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6067);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6068);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6069);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6070);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6071);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6072);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6073);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6074);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6075);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6076);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6077);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6078);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6079);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6080);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6081);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6082);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6083);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6084);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6085);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6086);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6087);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6088);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6089);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6090);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6091);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6092);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6093);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6094);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6095);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6096);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6097);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6098);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6099);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6100);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6101);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6102);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6103);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6104);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6105);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6106);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6107);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6108);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6109);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6110);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6111);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6112);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6113);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6114);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6115);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6116);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6117);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6118);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6119);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6120);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6121);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6122);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6123);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6124);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6125);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6126);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6127);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6128);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6129);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6130);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6131);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6132);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6133);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6134);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6135);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6136);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6137);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6138);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6139);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6140);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6141);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6142);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6143);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6144);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6145);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6146);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6147);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6148);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6149);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6150);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6151);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6152);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6153);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6154);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6155);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6156);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6157);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6158);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6159);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6160);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6161);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6162);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6163);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6164);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6165);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6166);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6167);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6168);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6169);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6170);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6171);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6172);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6173);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6174);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6175);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6176);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6177);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6178);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6179);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6180);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6181);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6182);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6183);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6184);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6185);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6186);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6187);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6188);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6189);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6190);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6191);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6192);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6193);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6194);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6195);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6196);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6197);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6198);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6199);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6200);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6201);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6202);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6203);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6204);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6205);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6206);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6207);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6208);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6209);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6210);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6211);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6212);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6213);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6214);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6215);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6216);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6217);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6218);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6219);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6220);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6221);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6222);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6223);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6224);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6225);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6226);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6227);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6228);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6229);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6230);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6231);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6232);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6233);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6234);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6235);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6236);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6237);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6238);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6239);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6240);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6241);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6242);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6243);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6244);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6245);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6246);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6247);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6248);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6249);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6250);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6251);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6252);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6253);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6254);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6255);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6256);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6257);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6258);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6259);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6260);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6261);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6262);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6263);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6264);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6265);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6266);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6267);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6268);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6269);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6270);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6271);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6272);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6273);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6274);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6275);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6276);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6277);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6278);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6279);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6280);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6281);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6282);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6283);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6284);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6285);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6286);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6287);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6288);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6289);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6290);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6291);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6292);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6293);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6294);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6295);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6296);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6297);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6298);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6299);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6300);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6301);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6302);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6303);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6304);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6305);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6306);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6307);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6308);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6309);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6310);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6311);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6312);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6313);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6314);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6315);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6316);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6317);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6318);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6319);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6320);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6321);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6322);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6323);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6324);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6325);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6326);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6327);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6328);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6329);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6330);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6331);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6332);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6333);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6334);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6335);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6336);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6337);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6338);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6339);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6340);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6341);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6342);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6343);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6344);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6345);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6346);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6347);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6348);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6349);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6350);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6351);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6352);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6353);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6354);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6355);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6356);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6357);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6358);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6359);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6360);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6361);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6362);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6363);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6364);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6365);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6366);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6367);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6368);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6369);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6370);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6371);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6372);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6373);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6374);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6375);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6376);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6377);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6378);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6379);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6380);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6381);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6382);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6383);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6384);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6385);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6386);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6387);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6388);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6389);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6390);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6391);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6392);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6393);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6394);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6395);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6396);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6397);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6398);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6399);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6400);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6401);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6402);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6403);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6404);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6405);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6406);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6407);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6408);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6409);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6410);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6411);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6412);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6413);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6414);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6415);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6416);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6417);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6418);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6419);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6420);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6421);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6422);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6423);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6424);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6425);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6426);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6427);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6428);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6429);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6430);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6431);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6432);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6433);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6434);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6435);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6436);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6437);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6438);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6439);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6440);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6441);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6442);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6443);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6444);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6445);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6446);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6447);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6448);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6449);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6450);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6451);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6452);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6453);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6454);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6455);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6456);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6457);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6458);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6459);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6460);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6461);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6462);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6463);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6464);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6465);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6466);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6467);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6468);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6469);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6470);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6471);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6472);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6473);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6474);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6475);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6476);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6477);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6478);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6479);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6480);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6481);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6482);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6483);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6484);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6485);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6486);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6487);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6488);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6489);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6490);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6491);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6492);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6493);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6494);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6495);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6496);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6497);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6498);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6499);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6500);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6501);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6502);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6503);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6504);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6505);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6506);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6507);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6508);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6509);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6510);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6511);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6512);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6513);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6514);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6515);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6516);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6517);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6518);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6519);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6520);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6521);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6522);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6523);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6524);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6525);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6526);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6527);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6528);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6529);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6530);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6531);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6532);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6533);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6534);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6535);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6536);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6537);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6538);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6539);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6540);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6541);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6542);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6543);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6544);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6545);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6546);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6547);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6548);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6549);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6550);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6551);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6552);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6553);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6554);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6555);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6556);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6557);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6558);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6559);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6560);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6561);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6562);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6563);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6564);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6565);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6566);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6567);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6568);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6569);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6570);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6571);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6572);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6573);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6574);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6575);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6576);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6577);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6578);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6579);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6580);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6581);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6582);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6583);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6584);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6585);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6586);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6587);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6588);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6589);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6590);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6591);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6592);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6593);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6594);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6595);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6596);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6597);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6598);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6599);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6600);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6601);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6602);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6603);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6604);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6605);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6606);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6607);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6608);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6609);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6610);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6611);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6612);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6613);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6614);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6615);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6616);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6617);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6618);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6619);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6620);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6621);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6622);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6623);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6624);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6625);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6626);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6627);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6628);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6629);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6630);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6631);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6632);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6633);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6634);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6635);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6636);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6637);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6638);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6639);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6640);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6641);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6642);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6643);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6644);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6645);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6646);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6647);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6648);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6649);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6650);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6651);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6652);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6653);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6654);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6655);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6656);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6657);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6658);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6659);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6660);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6661);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6662);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6663);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6664);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6665);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6666);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6667);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6668);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6669);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6670);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6671);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6672);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6673);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6674);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6675);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6676);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6677);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6678);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6679);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6680);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6681);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6682);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6683);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6684);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6685);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6686);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6687);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6688);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6689);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6690);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6691);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6692);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6693);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6694);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6695);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6696);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6697);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6698);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6699);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6700);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6701);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6702);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6703);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6704);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6705);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6706);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6707);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6708);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6709);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6710);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6711);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6712);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6713);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6714);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6715);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6716);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6717);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6718);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6719);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6720);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6721);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6722);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6723);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6724);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6725);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6726);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6727);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6728);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6729);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6730);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6731);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6732);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6733);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6734);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6735);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6736);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6737);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6738);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6739);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6740);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6741);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6742);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6743);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6744);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6745);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6746);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6747);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6748);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6749);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6750);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6751);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6752);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6753);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6754);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6755);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6756);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6757);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6758);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6759);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6760);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6761);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6762);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6763);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6764);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6765);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6766);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6767);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6768);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6769);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6770);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6771);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6772);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6773);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6774);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6775);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6776);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6777);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6778);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6779);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6780);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6781);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6782);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6783);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6784);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6785);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6786);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6787);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6788);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6789);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6790);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6791);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6792);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6793);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6794);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6795);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6796);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6797);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6798);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6799);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6800);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6801);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6802);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6803);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6804);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6805);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6806);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6807);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6808);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6809);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6810);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6811);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6812);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6813);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6814);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6815);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6816);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6817);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6818);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6819);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6820);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6821);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6822);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6823);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6824);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6825);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6826);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6827);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6828);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6829);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6830);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6831);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6832);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6833);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6834);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6835);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6836);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6837);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6838);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6839);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6840);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6841);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6842);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6843);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6844);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6845);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6846);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6847);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6848);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6849);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6850);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6851);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6852);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6853);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6854);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6855);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6856);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6857);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6858);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6859);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6860);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6861);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6862);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6863);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6864);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6865);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6866);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6867);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6868);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6869);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6870);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6871);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6872);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6873);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6874);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6875);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6876);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6877);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6878);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6879);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6880);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6881);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6882);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6883);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6884);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6885);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6886);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6887);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6888);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6889);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6890);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6891);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6892);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6893);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6894);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6895);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6896);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6897);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6898);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6899);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6900);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6901);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6902);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6903);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6904);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6905);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6906);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6907);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6908);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6909);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6910);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6911);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6912);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6913);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6914);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6915);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6916);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6917);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6918);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6919);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6920);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6921);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6922);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6923);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6924);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6925);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6926);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6927);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6928);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6929);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6930);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6931);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6932);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6933);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6934);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6935);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6936);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6937);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6938);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6939);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6940);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6941);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6942);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6943);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6944);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6945);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6946);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6947);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6948);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6949);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6950);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6951);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6952);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6953);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6954);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6955);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6956);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6957);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6958);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6959);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6960);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6961);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6962);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6963);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6964);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6965);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6966);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6967);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6968);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6969);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6970);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6971);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6972);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6973);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6974);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6975);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6976);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6977);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6978);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6979);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6980);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6981);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6982);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6983);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6984);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6985);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6986);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6987);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6988);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6989);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6990);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6991);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6992);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6993);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6994);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6995);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6996);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6997);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6998);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(6999);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7000);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7001);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7002);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7003);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7004);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7005);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7006);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7007);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7008);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7009);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7010);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7011);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7012);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7013);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7014);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7015);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7016);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7017);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7018);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7019);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7020);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7021);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7022);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7023);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7024);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7025);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7026);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7027);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7028);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7029);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7030);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7031);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7032);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7033);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7034);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7035);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7036);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7037);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7038);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7039);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7040);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7041);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7042);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7043);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7044);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7045);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7046);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7047);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7048);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7049);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7050);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7051);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7052);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7053);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7054);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7055);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7056);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7057);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7058);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7059);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7060);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7061);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7062);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7063);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7064);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7065);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7066);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7067);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7068);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7069);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7070);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7071);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7072);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7073);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7074);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7075);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7076);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7077);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7078);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7079);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7080);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7081);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7082);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7083);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7084);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7085);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7086);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7087);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7088);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7089);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7090);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7091);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7092);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7093);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7094);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7095);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7096);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7097);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7098);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7099);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7100);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7101);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7102);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7103);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7104);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7105);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7106);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7107);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7108);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7109);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7110);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7111);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7112);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7113);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7114);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7115);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7116);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7117);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7118);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7119);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7120);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7121);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7122);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7123);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7124);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7125);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7126);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7127);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7128);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7129);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7130);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7131);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7132);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7133);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7134);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7135);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7136);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7137);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7138);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7139);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7140);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7141);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7142);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7143);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7144);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7145);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7146);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7147);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7148);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7149);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7150);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7151);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7152);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7153);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7154);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7155);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7156);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7157);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7158);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7159);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7160);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7161);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7162);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7163);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7164);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7165);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7166);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7167);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7168);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7169);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7170);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7171);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7172);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7173);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7174);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7175);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7176);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7177);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7178);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7179);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7180);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7181);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7182);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7183);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7184);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7185);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7186);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7187);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7188);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7189);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7190);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7191);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7192);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7193);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7194);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7195);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7196);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7197);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7198);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7199);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7200);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7201);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7202);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7203);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7204);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7205);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7206);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7207);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7208);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7209);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7210);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7211);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7212);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7213);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7214);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7215);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7216);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7217);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7218);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7219);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7220);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7221);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7222);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7223);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7224);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7225);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7226);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7227);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7228);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7229);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7230);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7231);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7232);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7233);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7234);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7235);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7236);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7237);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7238);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7239);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7240);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7241);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7242);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7243);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7244);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7245);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7246);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7247);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7248);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7249);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7250);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7251);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7252);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7253);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7254);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7255);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7256);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7257);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7258);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7259);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7260);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7261);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7262);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7263);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7264);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7265);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7266);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7267);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7268);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7269);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7270);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7271);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7272);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7273);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7274);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7275);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7276);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7277);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7278);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7279);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7280);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7281);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7282);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7283);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7284);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7285);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7286);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7287);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7288);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7289);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7290);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7291);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7292);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7293);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7294);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7295);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7296);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7297);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7298);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7299);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7300);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7301);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7302);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7303);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7304);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7305);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7306);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7307);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7308);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7309);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7310);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7311);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7312);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7313);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7314);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7315);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7316);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7317);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7318);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7319);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7320);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7321);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7322);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7323);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7324);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7325);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7326);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7327);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7328);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7329);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7330);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7331);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7332);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7333);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7334);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7335);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7336);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7337);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7338);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7339);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7340);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7341);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7342);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7343);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7344);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7345);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7346);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7347);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7348);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7349);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7350);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7351);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7352);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7353);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7354);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7355);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7356);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7357);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7358);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7359);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7360);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7361);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7362);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7363);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7364);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7365);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7366);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7367);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7368);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7369);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7370);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7371);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7372);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7373);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7374);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7375);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7376);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7377);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7378);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7379);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7380);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7381);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7382);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7383);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7384);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7385);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7386);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7387);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7388);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7389);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7390);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7391);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7392);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7393);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7394);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7395);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7396);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7397);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7398);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7399);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7400);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7401);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7402);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7403);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7404);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7405);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7406);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7407);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7408);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7409);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7410);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7411);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7412);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7413);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7414);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7415);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7416);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7417);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7418);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7419);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7420);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7421);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7422);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7423);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7424);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7425);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7426);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7427);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7428);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7429);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7430);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7431);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7432);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7433);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7434);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7435);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7436);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7437);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7438);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7439);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7440);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7441);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7442);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7443);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7444);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7445);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7446);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7447);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7448);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7449);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7450);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7451);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7452);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7453);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7454);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7455);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7456);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7457);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7458);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7459);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7460);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7461);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7462);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7463);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7464);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7465);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7466);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7467);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7468);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7469);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7470);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7471);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7472);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7473);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7474);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7475);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7476);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7477);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7478);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7479);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7480);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7481);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7482);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7483);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7484);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7485);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7486);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7487);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7488);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7489);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7490);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7491);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7492);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7493);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7494);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7495);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7496);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7497);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7498);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7499);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7500);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7501);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7502);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7503);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7504);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7505);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7506);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7507);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7508);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7509);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7510);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7511);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7512);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7513);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7514);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7515);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7516);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7517);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7518);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7519);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7520);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7521);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7522);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7523);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7524);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7525);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7526);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7527);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7528);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7529);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7530);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7531);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7532);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7533);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7534);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7535);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7536);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7537);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7538);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7539);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7540);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7541);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7542);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7543);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7544);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7545);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7546);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7547);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7548);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7549);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7550);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7551);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7552);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7553);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7554);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7555);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7556);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7557);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7558);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7559);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7560);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7561);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7562);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7563);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7564);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7565);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7566);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7567);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7568);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7569);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7570);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7571);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7572);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7573);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7574);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7575);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7576);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7577);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7578);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7579);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7580);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7581);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7582);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7583);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7584);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7585);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7586);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7587);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7588);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7589);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7590);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7591);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7592);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7593);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7594);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7595);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7596);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7597);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7598);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7599);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7600);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7601);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7602);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7603);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7604);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7605);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7606);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7607);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7608);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7609);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7610);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7611);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7612);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7613);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7614);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7615);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7616);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7617);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7618);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7619);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7620);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7621);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7622);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7623);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7624);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7625);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7626);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7627);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7628);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7629);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7630);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7631);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7632);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7633);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7634);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7635);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7636);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7637);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7638);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7639);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7640);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7641);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7642);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7643);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7644);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7645);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7646);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7647);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7648);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7649);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7650);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7651);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7652);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7653);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7654);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7655);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7656);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7657);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7658);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7659);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7660);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7661);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7662);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7663);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7664);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7665);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7666);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7667);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7668);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7669);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7670);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7671);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7672);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7673);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7674);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7675);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7676);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7677);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7678);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7679);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7680);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7681);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7682);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7683);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7684);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7685);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7686);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7687);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7688);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7689);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7690);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7691);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7692);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7693);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7694);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7695);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7696);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7697);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7698);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7699);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7700);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7701);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7702);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7703);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7704);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7705);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7706);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7707);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7708);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7709);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7710);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7711);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7712);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7713);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7714);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7715);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7716);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7717);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7718);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7719);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7720);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7721);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7722);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7723);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7724);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7725);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7726);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7727);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7728);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7729);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7730);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7731);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7732);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7733);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7734);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7735);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7736);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7737);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7738);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7739);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7740);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7741);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7742);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7743);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7744);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7745);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7746);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7747);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7748);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7749);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7750);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7751);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7752);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7753);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7754);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7755);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7756);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7757);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7758);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7759);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7760);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7761);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7762);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7763);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7764);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7765);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7766);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7767);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7768);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7769);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7770);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7771);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7772);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7773);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7774);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7775);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7776);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7777);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7778);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7779);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7780);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7781);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7782);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7783);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7784);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7785);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7786);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7787);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7788);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7789);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7790);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7791);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7792);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7793);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7794);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7795);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7796);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7797);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7798);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7799);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7800);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7801);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7802);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7803);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7804);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7805);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7806);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7807);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7808);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7809);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7810);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7811);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7812);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7813);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7814);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7815);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7816);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7817);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7818);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7819);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7820);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7821);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7822);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7823);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7824);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7825);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7826);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7827);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7828);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7829);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7830);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7831);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7832);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7833);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7834);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7835);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7836);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7837);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7838);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7839);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7840);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7841);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7842);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7843);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7844);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7845);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7846);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7847);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7848);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7849);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7850);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7851);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7852);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7853);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7854);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7855);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7856);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7857);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7858);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7859);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7860);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7861);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7862);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7863);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7864);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7865);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7866);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7867);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7868);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7869);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7870);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7871);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7872);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7873);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7874);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7875);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7876);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7877);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7878);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7879);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7880);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7881);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7882);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7883);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7884);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7885);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7886);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7887);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7888);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7889);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7890);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7891);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7892);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7893);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7894);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7895);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7896);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7897);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7898);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7899);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7900);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7901);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7902);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7903);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7904);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7905);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7906);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7907);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7908);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7909);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7910);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7911);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7912);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7913);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7914);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7915);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7916);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7917);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7918);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7919);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7920);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7921);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7922);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7923);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7924);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7925);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7926);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7927);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7928);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7929);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7930);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7931);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7932);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7933);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7934);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7935);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7936);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7937);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7938);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7939);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7940);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7941);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7942);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7943);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7944);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7945);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7946);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7947);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7948);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7949);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7950);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7951);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7952);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7953);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7954);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7955);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7956);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7957);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7958);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7959);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7960);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7961);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7962);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7963);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7964);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7965);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7966);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7967);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7968);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7969);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7970);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7971);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7972);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7973);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7974);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7975);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7976);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7977);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7978);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7979);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7980);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7981);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7982);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7983);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7984);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7985);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7986);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7987);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7988);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7989);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7990);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7991);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7992);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7993);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7994);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7995);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7996);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7997);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7998);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(7999);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8000);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8001);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8002);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8003);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8004);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8005);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8006);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8007);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8008);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8009);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8010);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8011);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8012);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8013);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8014);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8015);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8016);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8017);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8018);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8019);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8020);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8021);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8022);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8023);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8024);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8025);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8026);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8027);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8028);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8029);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8030);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8031);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8032);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8033);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8034);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8035);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8036);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8037);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8038);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8039);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8040);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8041);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8042);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8043);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8044);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8045);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8046);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8047);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8048);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8049);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8050);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8051);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8052);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8053);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8054);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8055);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8056);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8057);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8058);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8059);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8060);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8061);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8062);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8063);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8064);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8065);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8066);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8067);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8068);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8069);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8070);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8071);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8072);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8073);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8074);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8075);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8076);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8077);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8078);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8079);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8080);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8081);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8082);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8083);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8084);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8085);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8086);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8087);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8088);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8089);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8090);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8091);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8092);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8093);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8094);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8095);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8096);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8097);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8098);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8099);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8100);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8101);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8102);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8103);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8104);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8105);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8106);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8107);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8108);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8109);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8110);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8111);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8112);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8113);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8114);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8115);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8116);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8117);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8118);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8119);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8120);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8121);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8122);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8123);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8124);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8125);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8126);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8127);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8128);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8129);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8130);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8131);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8132);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8133);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8134);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8135);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8136);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8137);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8138);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8139);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8140);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8141);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8142);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8143);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8144);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8145);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8146);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8147);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8148);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8149);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8150);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8151);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8152);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8153);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8154);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8155);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8156);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8157);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8158);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8159);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8160);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8161);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8162);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8163);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8164);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8165);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8166);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8167);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8168);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8169);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8170);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8171);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8172);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8173);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8174);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8175);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8176);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8177);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8178);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8179);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8180);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8181);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8182);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8183);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8184);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8185);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8186);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8187);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8188);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8189);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8190);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8191);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8192);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8193);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8194);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8195);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8196);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8197);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8198);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8199);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8200);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8201);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8202);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8203);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8204);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8205);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8206);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8207);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8208);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8209);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8210);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8211);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8212);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8213);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8214);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8215);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8216);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8217);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8218);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8219);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8220);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8221);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8222);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8223);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8224);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8225);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8226);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8227);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8228);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8229);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8230);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8231);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8232);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8233);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8234);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8235);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8236);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8237);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8238);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8239);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8240);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8241);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8242);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8243);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8244);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8245);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8246);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8247);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8248);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8249);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8250);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8251);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8252);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8253);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8254);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8255);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8256);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8257);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8258);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8259);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8260);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8261);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8262);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8263);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8264);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8265);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8266);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8267);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8268);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8269);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8270);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8271);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8272);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8273);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8274);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8275);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8276);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8277);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8278);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8279);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8280);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8281);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8282);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8283);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8284);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8285);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8286);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8287);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8288);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8289);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8290);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8291);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8292);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8293);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8294);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8295);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8296);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8297);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8298);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8299);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8300);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8301);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8302);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8303);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8304);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8305);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8306);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8307);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8308);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8309);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8310);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8311);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8312);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8313);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8314);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8315);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8316);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8317);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8318);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8319);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8320);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8321);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8322);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8323);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8324);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8325);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8326);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8327);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8328);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8329);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8330);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8331);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8332);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8333);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8334);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8335);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8336);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8337);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8338);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8339);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8340);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8341);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8342);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8343);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8344);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8345);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8346);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8347);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8348);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8349);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8350);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8351);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8352);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8353);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8354);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8355);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8356);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8357);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8358);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8359);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8360);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8361);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8362);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8363);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8364);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8365);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8366);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8367);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8368);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8369);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8370);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8371);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8372);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8373);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8374);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8375);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8376);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8377);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8378);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8379);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8380);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8381);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8382);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8383);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8384);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8385);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8386);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8387);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8388);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8389);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8390);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8391);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8392);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8393);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8394);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8395);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8396);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8397);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8398);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8399);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8400);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8401);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8402);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8403);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8404);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8405);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8406);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8407);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8408);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8409);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8410);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8411);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8412);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8413);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8414);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8415);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8416);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8417);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8418);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8419);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8420);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8421);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8422);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8423);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8424);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8425);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8426);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8427);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8428);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8429);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8430);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8431);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8432);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8433);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8434);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8435);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8436);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8437);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8438);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8439);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8440);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8441);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8442);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8443);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8444);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8445);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8446);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8447);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8448);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8449);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8450);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8451);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8452);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8453);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8454);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8455);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8456);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8457);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8458);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8459);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8460);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8461);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8462);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8463);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8464);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8465);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8466);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8467);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8468);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8469);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8470);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8471);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8472);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8473);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8474);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8475);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8476);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8477);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8478);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8479);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8480);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8481);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8482);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8483);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8484);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8485);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8486);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8487);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8488);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8489);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8490);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8491);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8492);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8493);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8494);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8495);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8496);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8497);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8498);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8499);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8500);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8501);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8502);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8503);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8504);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8505);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8506);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8507);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8508);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8509);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8510);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8511);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8512);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8513);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8514);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8515);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8516);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8517);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8518);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8519);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8520);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8521);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8522);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8523);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8524);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8525);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8526);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8527);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8528);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8529);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8530);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8531);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8532);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8533);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8534);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8535);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8536);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8537);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8538);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8539);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8540);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8541);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8542);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8543);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8544);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8545);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8546);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8547);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8548);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8549);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8550);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8551);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8552);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8553);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8554);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8555);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8556);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8557);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8558);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8559);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8560);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8561);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8562);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8563);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8564);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8565);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8566);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8567);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8568);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8569);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8570);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8571);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8572);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8573);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8574);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8575);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8576);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8577);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8578);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8579);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8580);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8581);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8582);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8583);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8584);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8585);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8586);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8587);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8588);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8589);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8590);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8591);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8592);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8593);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8594);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8595);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8596);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8597);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8598);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8599);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8600);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8601);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8602);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8603);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8604);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8605);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8606);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8607);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8608);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8609);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8610);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8611);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8612);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8613);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8614);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8615);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8616);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8617);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8618);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8619);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8620);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8621);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8622);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8623);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8624);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8625);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8626);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8627);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8628);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8629);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8630);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8631);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8632);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8633);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8634);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8635);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8636);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8637);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8638);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8639);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8640);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8641);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8642);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8643);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8644);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8645);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8646);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8647);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8648);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8649);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8650);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8651);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8652);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8653);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8654);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8655);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8656);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8657);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8658);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8659);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8660);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8661);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8662);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8663);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8664);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8665);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8666);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8667);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8668);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8669);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8670);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8671);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8672);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8673);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8674);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8675);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8676);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8677);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8678);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8679);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8680);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8681);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8682);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8683);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8684);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8685);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8686);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8687);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8688);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8689);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8690);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8691);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8692);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8693);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8694);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8695);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8696);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8697);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8698);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8699);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8700);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8701);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8702);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8703);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8704);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8705);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8706);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8707);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8708);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8709);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8710);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8711);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8712);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8713);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8714);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8715);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8716);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8717);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8718);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8719);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8720);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8721);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8722);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8723);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8724);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8725);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8726);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8727);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8728);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8729);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8730);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8731);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8732);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8733);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8734);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8735);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8736);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8737);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8738);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8739);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8740);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8741);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8742);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8743);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8744);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8745);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8746);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8747);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8748);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8749);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8750);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8751);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8752);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8753);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8754);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8755);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8756);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8757);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8758);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8759);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8760);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8761);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8762);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8763);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8764);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8765);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8766);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8767);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8768);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8769);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8770);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8771);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8772);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8773);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8774);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8775);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8776);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8777);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8778);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8779);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8780);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8781);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8782);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8783);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8784);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8785);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8786);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8787);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8788);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8789);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8790);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8791);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8792);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8793);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8794);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8795);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8796);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8797);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8798);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8799);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8800);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8801);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8802);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8803);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8804);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8805);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8806);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8807);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8808);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8809);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8810);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8811);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8812);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8813);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8814);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8815);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8816);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8817);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8818);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8819);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8820);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8821);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8822);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8823);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8824);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8825);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8826);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8827);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8828);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8829);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8830);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8831);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8832);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8833);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8834);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8835);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8836);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8837);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8838);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8839);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8840);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8841);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8842);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8843);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8844);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8845);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8846);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8847);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8848);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8849);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8850);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8851);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8852);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8853);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8854);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8855);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8856);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8857);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8858);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8859);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8860);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8861);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8862);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8863);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8864);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8865);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8866);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8867);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8868);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8869);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8870);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8871);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8872);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8873);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8874);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8875);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8876);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8877);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8878);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8879);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8880);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8881);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8882);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8883);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8884);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8885);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8886);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8887);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8888);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8889);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8890);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8891);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8892);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8893);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8894);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8895);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8896);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8897);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8898);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8899);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8900);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8901);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8902);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8903);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8904);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8905);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8906);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8907);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8908);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8909);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8910);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8911);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8912);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8913);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8914);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8915);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8916);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8917);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8918);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8919);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8920);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8921);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8922);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8923);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8924);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8925);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8926);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8927);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8928);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8929);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8930);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8931);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8932);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8933);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8934);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8935);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8936);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8937);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8938);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8939);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8940);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8941);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8942);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8943);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8944);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8945);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8946);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8947);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8948);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8949);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8950);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8951);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8952);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8953);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8954);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8955);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8956);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8957);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8958);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8959);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8960);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8961);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8962);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8963);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8964);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8965);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8966);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8967);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8968);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8969);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8970);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8971);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8972);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8973);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8974);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8975);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8976);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8977);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8978);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8979);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8980);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8981);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8982);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8983);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8984);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8985);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8986);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8987);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8988);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8989);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8990);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8991);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8992);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8993);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8994);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8995);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8996);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8997);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8998);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(8999);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9000);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9001);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9002);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9003);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9004);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9005);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9006);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9007);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9008);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9009);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9010);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9011);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9012);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9013);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9014);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9015);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9016);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9017);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9018);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9019);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9020);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9021);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9022);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9023);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9024);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9025);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9026);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9027);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9028);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9029);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9030);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9031);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9032);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9033);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9034);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9035);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9036);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9037);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9038);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9039);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9040);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9041);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9042);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9043);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9044);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9045);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9046);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9047);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9048);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9049);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9050);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9051);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9052);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9053);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9054);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9055);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9056);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9057);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9058);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9059);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9060);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9061);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9062);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9063);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9064);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9065);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9066);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9067);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9068);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9069);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9070);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9071);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9072);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9073);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9074);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9075);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9076);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9077);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9078);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9079);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9080);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9081);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9082);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9083);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9084);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9085);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9086);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9087);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9088);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9089);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9090);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9091);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9092);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9093);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9094);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9095);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9096);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9097);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9098);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9099);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9100);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9101);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9102);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9103);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9104);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9105);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9106);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9107);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9108);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9109);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9110);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9111);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9112);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9113);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9114);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9115);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9116);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9117);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9118);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9119);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9120);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9121);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9122);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9123);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9124);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9125);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9126);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9127);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9128);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9129);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9130);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9131);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9132);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9133);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9134);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9135);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9136);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9137);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9138);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9139);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9140);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9141);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9142);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9143);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9144);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9145);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9146);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9147);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9148);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9149);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9150);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9151);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9152);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9153);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9154);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9155);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9156);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9157);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9158);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9159);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9160);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9161);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9162);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9163);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9164);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9165);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9166);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9167);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9168);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9169);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9170);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9171);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9172);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9173);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9174);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9175);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9176);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9177);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9178);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9179);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9180);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9181);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9182);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9183);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9184);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9185);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9186);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9187);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9188);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9189);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9190);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9191);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9192);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9193);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9194);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9195);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9196);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9197);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9198);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9199);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9200);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9201);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9202);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9203);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9204);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9205);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9206);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9207);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9208);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9209);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9210);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9211);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9212);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9213);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9214);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9215);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9216);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9217);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9218);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9219);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9220);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9221);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9222);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9223);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9224);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9225);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9226);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9227);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9228);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9229);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9230);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9231);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9232);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9233);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9234);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9235);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9236);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9237);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9238);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9239);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9240);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9241);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9242);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9243);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9244);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9245);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9246);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9247);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9248);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9249);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9250);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9251);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9252);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9253);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9254);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9255);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9256);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9257);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9258);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9259);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9260);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9261);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9262);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9263);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9264);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9265);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9266);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9267);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9268);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9269);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9270);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9271);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9272);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9273);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9274);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9275);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9276);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9277);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9278);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9279);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9280);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9281);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9282);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9283);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9284);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9285);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9286);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9287);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9288);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9289);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9290);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9291);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9292);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9293);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9294);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9295);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9296);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9297);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9298);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9299);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9300);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9301);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9302);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9303);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9304);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9305);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9306);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9307);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9308);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9309);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9310);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9311);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9312);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9313);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9314);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9315);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9316);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9317);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9318);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9319);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9320);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9321);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9322);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9323);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9324);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9325);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9326);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9327);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9328);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9329);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9330);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9331);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9332);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9333);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9334);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9335);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9336);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9337);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9338);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9339);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9340);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9341);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9342);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9343);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9344);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9345);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9346);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9347);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9348);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9349);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9350);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9351);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9352);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9353);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9354);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9355);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9356);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9357);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9358);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9359);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9360);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9361);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9362);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9363);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9364);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9365);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9366);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9367);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9368);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9369);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9370);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9371);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9372);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9373);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9374);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9375);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9376);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9377);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9378);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9379);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9380);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9381);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9382);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9383);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9384);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9385);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9386);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9387);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9388);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9389);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9390);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9391);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9392);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9393);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9394);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9395);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9396);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9397);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9398);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9399);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9400);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9401);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9402);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9403);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9404);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9405);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9406);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9407);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9408);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9409);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9410);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9411);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9412);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9413);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9414);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9415);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9416);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9417);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9418);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9419);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9420);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9421);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9422);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9423);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9424);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9425);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9426);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9427);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9428);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9429);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9430);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9431);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9432);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9433);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9434);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9435);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9436);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9437);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9438);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9439);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9440);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9441);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9442);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9443);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9444);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9445);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9446);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9447);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9448);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9449);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9450);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9451);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9452);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9453);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9454);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9455);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9456);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9457);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9458);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9459);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9460);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9461);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9462);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9463);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9464);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9465);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9466);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9467);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9468);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9469);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9470);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9471);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9472);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9473);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9474);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9475);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9476);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9477);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9478);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9479);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9480);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9481);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9482);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9483);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9484);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9485);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9486);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9487);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9488);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9489);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9490);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9491);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9492);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9493);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9494);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9495);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9496);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9497);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9498);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9499);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9500);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9501);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9502);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9503);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9504);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9505);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9506);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9507);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9508);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9509);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9510);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9511);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9512);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9513);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9514);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9515);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9516);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9517);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9518);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9519);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9520);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9521);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9522);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9523);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9524);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9525);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9526);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9527);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9528);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9529);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9530);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9531);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9532);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9533);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9534);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9535);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9536);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9537);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9538);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9539);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9540);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9541);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9542);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9543);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9544);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9545);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9546);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9547);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9548);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9549);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9550);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9551);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9552);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9553);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9554);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9555);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9556);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9557);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9558);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9559);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9560);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9561);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9562);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9563);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9564);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9565);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9566);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9567);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9568);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9569);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9570);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9571);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9572);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9573);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9574);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9575);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9576);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9577);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9578);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9579);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9580);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9581);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9582);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9583);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9584);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9585);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9586);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9587);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9588);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9589);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9590);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9591);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9592);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9593);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9594);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9595);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9596);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9597);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9598);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9599);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9600);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9601);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9602);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9603);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9604);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9605);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9606);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9607);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9608);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9609);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9610);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9611);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9612);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9613);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9614);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9615);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9616);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9617);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9618);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9619);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9620);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9621);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9622);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9623);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9624);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9625);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9626);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9627);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9628);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9629);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9630);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9631);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9632);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9633);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9634);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9635);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9636);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9637);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9638);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9639);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9640);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9641);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9642);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9643);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9644);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9645);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9646);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9647);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9648);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9649);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9650);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9651);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9652);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9653);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9654);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9655);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9656);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9657);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9658);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9659);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9660);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9661);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9662);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9663);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9664);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9665);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9666);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9667);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9668);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9669);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9670);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9671);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9672);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9673);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9674);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9675);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9676);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9677);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9678);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9679);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9680);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9681);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9682);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9683);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9684);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9685);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9686);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9687);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9688);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9689);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9690);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9691);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9692);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9693);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9694);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9695);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9696);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9697);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9698);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9699);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9700);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9701);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9702);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9703);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9704);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9705);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9706);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9707);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9708);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9709);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9710);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9711);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9712);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9713);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9714);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9715);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9716);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9717);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9718);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9719);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9720);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9721);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9722);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9723);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9724);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9725);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9726);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9727);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9728);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9729);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9730);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9731);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9732);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9733);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9734);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9735);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9736);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9737);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9738);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9739);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9740);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9741);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9742);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9743);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9744);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9745);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9746);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9747);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9748);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9749);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9750);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9751);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9752);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9753);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9754);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9755);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9756);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9757);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9758);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9759);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9760);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9761);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9762);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9763);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9764);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9765);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9766);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9767);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9768);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9769);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9770);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9771);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9772);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9773);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9774);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9775);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9776);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9777);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9778);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9779);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9780);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9781);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9782);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9783);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9784);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9785);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9786);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9787);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9788);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9789);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9790);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9791);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9792);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9793);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9794);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9795);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9796);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9797);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9798);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9799);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9800);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9801);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9802);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9803);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9804);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9805);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9806);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9807);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9808);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9809);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9810);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9811);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9812);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9813);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9814);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9815);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9816);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9817);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9818);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9819);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9820);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9821);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9822);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9823);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9824);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9825);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9826);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9827);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9828);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9829);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9830);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9831);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9832);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9833);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9834);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9835);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9836);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9837);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9838);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9839);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9840);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9841);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9842);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9843);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9844);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9845);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9846);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9847);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9848);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9849);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9850);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9851);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9852);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9853);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9854);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9855);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9856);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9857);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9858);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9859);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9860);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9861);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9862);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9863);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9864);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9865);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9866);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9867);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9868);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9869);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9870);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9871);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9872);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9873);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9874);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9875);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9876);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9877);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9878);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9879);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9880);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9881);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9882);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9883);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9884);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9885);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9886);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9887);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9888);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9889);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9890);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9891);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9892);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9893);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9894);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9895);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9896);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9897);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9898);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9899);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9900);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9901);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9902);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9903);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9904);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9905);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9906);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9907);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9908);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9909);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9910);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9911);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9912);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9913);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9914);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9915);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9916);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9917);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9918);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9919);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9920);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9921);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9922);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9923);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9924);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9925);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9926);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9927);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9928);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9929);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9930);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9931);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9932);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9933);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9934);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9935);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9936);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9937);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9938);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9939);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9940);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9941);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9942);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9943);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9944);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9945);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9946);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9947);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9948);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9949);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9950);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9951);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9952);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9953);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9954);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9955);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9956);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9957);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9958);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9959);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9960);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9961);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9962);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9963);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9964);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9965);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9966);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9967);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9968);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9969);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9970);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9971);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9972);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9973);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9974);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9975);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9976);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9977);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9978);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9979);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9980);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9981);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9982);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9983);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9984);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9985);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9986);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9987);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9988);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9989);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9990);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9991);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9992);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9993);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9994);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9995);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9996);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9997);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9998);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(9999);
INSERT INTO adquisiciones_rangos_aux (id) VALUES(10000);


DELETE FROM ss_configuraciones WHERE cnf_codigo ='CAMPOS_SON_EXIGIDOS_EN_ADQUISICION' ;
DELETE FROM ss_configuraciones_defecto  WHERE codigo ='CAMPOS_SON_EXIGIDOS_EN_ADQUISICION' ;

-- Version 6.5.0

drop table if exists tipo_riesgos;
CREATE TABLE `tipo_riesgos` (
  `trs_pk` bigint(20) NOT NULL AUTO_INCREMENT,
  `trs_nombre` varchar(100) DEFAULT NULL,
  `trs_habilitado` tinyint(4) DEFAULT NULL,
  `trs_org_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`trs_pk`),
    CONSTRAINT `tipo_riesgos_FK` FOREIGN KEY (`trs_org_fk`) REFERENCES `organismos` (`org_pk`)
) ;


ALTER TABLE riesgos ADD risk_tipo_riesgo_fk BIGINT default NULL;
ALTER TABLE riesgos ADD CONSTRAINT riesgos_FK_1 FOREIGN KEY (risk_tipo_riesgo_fk) REFERENCES tipo_riesgos(trs_pk);



-- Version 6.7.0

INSERT INTO notificacion_defecto
(codigo, descripcion, asunto, mensaje, gerente_adjunto, pmof, pmot, sponsor, version)
VALUES('Presupuesto6', 'Cuando la fecha estimada de inicio de compra está próxima a cumplirse.', 'Próxima Compra a Iniciar', 'El Siguiente proyecto tiene al menos una Adquisición que tiene "fecha estimado de inicio de compra" próxima: #NOMBRE_PROYECTO#. Por favor iniciar la solicitud de compra o ajustar la fecha estimada de inicio en las adquisiciones del proyecto.', 1, 1, 0, 0, 0);

INSERT INTO notificacion
(not_cod, not_desc,  not_gerente_adjunto, not_pmof, not_pmot, not_sponsor, not_msg, not_asunto,not_org_fk)
Select 'Presupuesto6','Cuando la fecha estimada de inicio de compra está próxima a cumplirse.',1,1,0,0,'El Siguiente proyecto tiene al menos una Adquisición que tiene "fecha estimado de inicio de compra" próxima: #NOMBRE_PROYECTO#. Por favor iniciar la solicitud de compra o ajustar la fecha estimada de inicio en las adquisiciones del proyecto.','Próxima Compra a Iniciar' ,org_pk from organismos;

INSERT INTO notificacion_instancia
(notinst_not_fk, notinst_proy_fk, notinst_gerente_adjunto, notinst_pmof, notinst_pmot, notinst_sponsor)
select (SELECT not_pk FROM notificacion where not_cod='Presupuesto6' and not_org_fk=p.proy_org_fk), p.proy_pk, 1, 1, 0, 0 from proyectos p;


INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('PRIMERA_NOTIFICACION_INICIO_COMPRA', 'Cantidad de dias previos del primer aviso que la fecha estimada de inicio de compra está próxima a cumplirse.', '7', 0, now(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk)
Select 'PRIMERA_NOTIFICACION_INICIO_COMPRA','Cantidad de dias previos del primer aviso que la fecha estimada de inicio de compra está próxima a cumplirse.','7',0 ,org_pk from organismos;

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('SEGUNDA_NOTIFICACION_INICIO_COMPRA', 'Cantidad de dias previos del segundo aviso que la fecha estimada de inicio de compra está próxima a cumplirse.', '0', 0, now(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk)
Select 'SEGUNDA_NOTIFICACION_INICIO_COMPRA','Cantidad de dias previos del segundo aviso que la fecha estimada de inicio de compra está próxima a cumplirse.','0',0 ,org_pk from organismos;


UPDATE notificacion_defecto
SET  mensaje='<p> El Siguiente proyecto tiene al menos una Adquisición que tiene "fecha estimado de inicio de compra" próxima: #NOMBRE_PROYECTO#.</p>  <p> Por favor iniciar la solicitud de compra o ajustar la fecha estimada de inicio en las adquisiciones del proyecto.</p>
<div> #ID_ADQUISICION#</div>
<div>   #URL_PROYECTO#</div> <div>  &nbsp;</div> <div>  #ORGANISMO_NOMBRE#</div>'
WHERE codigo='Presupuesto6';


UPDATE notificacion
SET not_msg='<p> El Siguiente proyecto tiene al menos una Adquisición que tiene "fecha estimado de inicio de compra" próxima: #NOMBRE_PROYECTO#.</p>  <p> Por favor iniciar la solicitud de compra o ajustar la fecha estimada de inicio en las adquisiciones del proyecto.</p>
<div> #ID_ADQUISICION#</div>
<div>   #URL_PROYECTO#</div> <div>  &nbsp;</div> <div>  #ORGANISMO_NOMBRE#</div>'
WHERE not_cod='Presupuesto6';

drop table if exists procedimiento_compra_info;

CREATE TABLE procedimiento_compra_info (
  pci_pk BIGINT auto_increment NOT NULL,
  pci_nombre varchar(100) NULL,
  pci_descripcion varchar(200) NULL,
  pci_adquisicionId INTEGER NULL,
  pci_proyecto_fk INTEGER NULL,
  pci_adquisicion_fk INTEGER NULL,
  pci_organismo_fk INTEGER NULL,
  pci_fecha DATETIME NULL,
  CONSTRAINT procedimiento_compra_info_PK PRIMARY KEY (pci_pk),
  CONSTRAINT procedimiento_compra_info_FK FOREIGN KEY (pci_adquisicion_fk) REFERENCES adquisicion(adq_pk),
  CONSTRAINT procedimiento_compra_info_FK_1 FOREIGN KEY (pci_organismo_fk) REFERENCES organismos(org_pk)
);


-- Version 6.7.1

UPDATE notificacion
SET  not_desc='El siguiente proyecto tiene al menos una Adquisición con "fecha estimado de inicio de compra" próxima a vencer: #NOMBRE_PROYECTO#. Por favor iniciar la solicitud de compra o ajustar la fecha estimada de inicio en las adquisiciones del proyecto.'
WHERE not_cod = 'Presupuesto6' ;


UPDATE notificacion_defecto
SET descripcion='El siguiente proyecto tiene al menos una Adquisición con "fecha estimado de inicio de compra" próxima a vencer: #NOMBRE_PROYECTO#. Por favor iniciar la solicitud de compra o ajustar la fecha estimada de inicio en las adquisiciones del proyecto.'
WHERE codigo='Presupuesto6';








