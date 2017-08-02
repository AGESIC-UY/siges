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
  PRIMARY KEY (`adq_pk`),
  KEY `adq_pre_fk_idx` (`adq_pre_fk`),
  KEY `adq_fuente_idx` (`adq_fuente_fk`),
  KEY `adq_moneda_idx` (`adq_moneda_fk`),
  KEY `adq_prov_orga_idx` (`adq_prov_orga_fk`),
  CONSTRAINT `adq_fuente` FOREIGN KEY (`adq_fuente_fk`) REFERENCES `fuente_financiamiento` (`fue_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `adq_moneda` FOREIGN KEY (`adq_moneda_fk`) REFERENCES `moneda` (`mon_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `adq_pre_fk` FOREIGN KEY (`adq_pre_fk`) REFERENCES `presupuesto` (`pre_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `adq_prov_orga` FOREIGN KEY (`adq_prov_orga_fk`) REFERENCES `organi_int_prove` (`orga_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1656 DEFAULT CHARSET=utf8;
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
  PRIMARY KEY (`amb_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=1133 DEFAULT CHARSET=utf8;
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
  `area_habilitada` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`area_pk`),
  KEY `fk_AREAS_ORGANISMOS1_idx` (`area_org_fk`),
  KEY `fk_AREAS_AREAS1_idx` (`area_padre`),
  KEY `area_activo_idx` (`area_activo`),
  CONSTRAINT `fk_AREAS_AREAS1` FOREIGN KEY (`area_padre`) REFERENCES `areas` (`area_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_AREAS_ORGANISMOS1` FOREIGN KEY (`area_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=533 DEFAULT CHARSET=utf8;
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
  PRIMARY KEY (`cnf_id`,`REV`)
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
  PRIMARY KEY (`id`,`REV`)
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
  PRIMARY KEY (`ayu_id`,`REV`)
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
  PRIMARY KEY (`cat_id`,`REV`)
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
  PRIMARY KEY (`id`,`REV`)
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
  PRIMARY KEY (`depto_id`,`REV`)
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
  PRIMARY KEY (`dom_id`,`REV`)
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
  PRIMARY KEY (`id`,`REV`)
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
  PRIMARY KEY (`loc_id`,`REV`)
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
  PRIMARY KEY (`not_id`,`REV`)
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
  PRIMARY KEY (`ofi_id`,`REV`)
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
  PRIMARY KEY (`ope_id`,`REV`)
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
  PRIMARY KEY (`pai_id`,`REV`)
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
  PRIMARY KEY (`par_id`,`REV`)
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
  PRIMARY KEY (`per_id`,`REV`)
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
  PRIMARY KEY (`rel_rol_operacion_id`,`REV`)
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
  PRIMARY KEY (`rol_id`,`REV`)
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
  PRIMARY KEY (`tel_id`,`REV`)
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
  PRIMARY KEY (`tipdocper_id`,`REV`)
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
  PRIMARY KEY (`tec_id`,`REV`)
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
  PRIMARY KEY (`tipTel_id`,`REV`)
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
  PRIMARY KEY (`tvi_id`,`REV`)
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
  `usu_ofi_roles_activo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`usu_ofi_roles_id`,`REV`)
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
  PRIMARY KEY (`usu_id`,`REV`),
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
  PRIMARY KEY (`busq_filtro_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categoria_proyectos`
--

DROP TABLE IF EXISTS `categoria_proyectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria_proyectos` (
  `cat_proy_pk` int(11) NOT NULL AUTO_INCREMENT,
  `cat_proy_codigo` varchar(45) NOT NULL,
  `cat_proy_nombre` varchar(145) NOT NULL,
  `cat_proy_activo` tinyint(1) NOT NULL DEFAULT '1',
  `cat_tipo` int(11) NOT NULL DEFAULT '0',
  `cat_icono` int(11) DEFAULT NULL,
  `cat_icono_marker` int(11) DEFAULT NULL,
  `cat_org_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`cat_proy_pk`),
  KEY `cat_proy_activo_idx` (`cat_proy_activo`),
  KEY `cat_org_fk_idx` (`cat_org_fk`),
  KEY `cat_tipo_idx` (`cat_tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=501 DEFAULT CHARSET=latin1 COMMENT='Categoria de los Proyectos. Principalmente para usar con el Visualizador.';
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
) ENGINE=InnoDB AUTO_INCREMENT=2542 DEFAULT CHARSET=utf8;
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
  KEY `dev_anio_idx` (`dev_anio`)
) ENGINE=InnoDB AUTO_INCREMENT=496 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `doc_file`
--

DROP TABLE IF EXISTS `doc_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doc_file` (
  `docfile_pk` int(11) NOT NULL AUTO_INCREMENT,
  `docfile_file` longblob,
  `docfile_nombre` varchar(256) NOT NULL,
  `docfile_doc_fk` int(11) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`docfile_pk`),
  KEY `docfile_doc_fk` (`docfile_doc_fk`)
) ENGINE=InnoDB AUTO_INCREMENT=7710 DEFAULT CHARSET=utf8;
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
  `docs_fecha` date NOT NULL,
  `docs_privado` tinyint(1) DEFAULT NULL,
  `docs_estado` double DEFAULT NULL,
  `docs_entregable_fk` int(11) DEFAULT NULL,
  `docs_tipodoc_fk` int(11) NOT NULL,
  `docs_docfile_pk` int(11) DEFAULT NULL,
  `docs_aprobado` tinyint(1) DEFAULT NULL,
  `docs_pago_fk` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`docs_pk`),
  KEY `entregable_fk_idx` (`docs_entregable_fk`),
  KEY `tipo_doc_fk_idx` (`docs_tipodoc_fk`),
  KEY `docs_pago_fk_idx` (`docs_pago_fk`),
  CONSTRAINT `docs_entregable_fk` FOREIGN KEY (`docs_entregable_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `docs_pago_fk` FOREIGN KEY (`docs_pago_fk`) REFERENCES `pagos` (`pag_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tipo_doc_fk` FOREIGN KEY (`docs_tipodoc_fk`) REFERENCES `tipo_documento_instancia` (`tipodoc_inst_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8170 DEFAULT CHARSET=utf8 COMMENT='	';
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
  CONSTRAINT `enthist_ent_fk` FOREIGN KEY (`enthist_ent_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=33429 DEFAULT CHARSET=utf8;
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
  PRIMARY KEY (`ent_pk`),
  KEY `gantt_task_gantt_fk_idx` (`ent_cro_fk`),
  KEY `ent_coord_usu_fk_idx` (`ent_coord_usu_fk`),
  KEY `ent_inicio_idx` (`ent_inicio`),
  KEY `ent_fin_idx` (`ent_fin`),
  KEY `ent_progreso_idx` (`ent_progreso`),
  KEY `ent_parent_idx` (`ent_parent`),
  CONSTRAINT `ent_coord_usu_fk` FOREIGN KEY (`ent_coord_usu_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ent_cro_fk` FOREIGN KEY (`ent_cro_fk`) REFERENCES `cronogramas` (`cro_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28279 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COMMENT='Estados de la publicaci\F3n de un proyecto en el visualizador.';
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
  CONSTRAINT `eta_org_fk` FOREIGN KEY (`eta_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=latin1 COMMENT='Son los estados del proyecto que se van a exportar al visualizador.';
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
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;
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
) ENGINE=InnoDB AUTO_INCREMENT=290 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=1450 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=740 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `media_proyectos`
--

DROP TABLE IF EXISTS `media_proyectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media_proyectos` (
  `media_pk` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Clave primaria del media',
  `media_tipo_fk` int(11) DEFAULT NULL COMMENT 'Tipo de media, los tipos definidos son:\nImagens, link a videos youtube, links a webcam',
  `media_link` varchar(545) DEFAULT NULL COMMENT 'El link al media, en caso de imagenes es el link al folder donde se encuentra, en youtube la url del video, y en camaras web la url de la camara',
  `media_estado` int(11) DEFAULT NULL COMMENT 'EL estado del media puede ser \nPENDIENTE REVISION, PUBLICADO, RECHAZADO',
  `media_proy_fk` int(11) DEFAULT NULL,
  `media_principal` tinyint(1) DEFAULT NULL COMMENT 'en caso de ser una imagen es la que se utlizar\E1 para la vista rapida y para la seccion de destacados',
  `media_orden` int(11) DEFAULT NULL COMMENT 'EL orde de aparici\F3n del media en la galeria que lo esta desplegando.',
  `media_usr_pub_fk` int(11) DEFAULT NULL COMMENT 'EL c\F3digo del usuario que public\F3 el media',
  `media_pub_fecha` datetime DEFAULT NULL COMMENT 'Fecha y hora de publicaci\F3n',
  `media_usr_mod_fk` int(11) DEFAULT NULL COMMENT 'EL usuario que creo el media',
  `media_mod_fecha` datetime DEFAULT NULL COMMENT 'La fecha de creaci\F3n del media',
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
) ENGINE=InnoDB AUTO_INCREMENT=1792 DEFAULT CHARSET=utf8;
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
  PRIMARY KEY (`not_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=278 DEFAULT CHARSET=latin1;
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
  KEY `notenv_fecha_idx` (`notenv_fecha`)
) ENGINE=InnoDB AUTO_INCREMENT=10697 DEFAULT CHARSET=latin1;
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
) ENGINE=InnoDB AUTO_INCREMENT=8629 DEFAULT CHARSET=latin1;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=1072 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
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
  PRIMARY KEY (`pag_pk`),
  KEY `pag_ent_fk_idx` (`pag_ent_fk`),
  KEY `pag_adq_fk_idx` (`pag_adq_fk`),
  KEY `pag_fecha_planificada_idx` (`pag_fecha_planificada`),
  KEY `pag_fecha_real_idx` (`pag_fecha_real`),
  KEY `pag_confirmar_idx` (`pag_confirmar`),
  CONSTRAINT `pag_adq_fk` FOREIGN KEY (`pag_adq_fk`) REFERENCES `adquisicion` (`adq_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pag_ent_fk` FOREIGN KEY (`pag_ent_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4475 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=latin1;
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
  CONSTRAINT `FK1A6A26477E1BCA41` FOREIGN KEY (`pers_orga_fk`) REFERENCES `organi_int_prove` (`orga_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8;
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
  PRIMARY KEY (`p_crono_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
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
  PRIMARY KEY (`p_entregables_id`),
  KEY `plantilla_cro_idx` (`p_entregable_p_cro_fk`),
  KEY `entre_ante_idx` (`p_entregable_ant_fk`),
  CONSTRAINT `entre_ante` FOREIGN KEY (`p_entregable_ant_fk`) REFERENCES `plantilla_entregables` (`p_entregables_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `plantilla_cro` FOREIGN KEY (`p_entregable_p_cro_fk`) REFERENCES `plantilla_cronograma` (`p_crono_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=542 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=2220 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=9197 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=1343 DEFAULT CHARSET=utf8;
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
  PRIMARY KEY (`progind_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=274 DEFAULT CHARSET=utf8;
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
  PRIMARY KEY (`progindpre_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8;
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
  `prog_activo` tinyint(1) DEFAULT NULL,
  `prog_fecha_crea` date DEFAULT NULL,
  `prog_fecha_act` date NOT NULL,
  `prog_version` int(11) DEFAULT NULL,
  `prog_ult_usuario` varchar(45) DEFAULT NULL,
  `prog_ult_mod` varchar(45) DEFAULT NULL,
  `prog_ult_origen` date DEFAULT NULL,
  `prog_id_migrado` int(11) DEFAULT NULL,
  `prog_obj_est_fk` int(11) DEFAULT NULL,
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
  CONSTRAINT `prog_obj_est_fk` FOREIGN KEY (`prog_obj_est_fk`) REFERENCES `objetivos_estrategicos` (`obj_est_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROGRAMAS_AREAS1` FOREIGN KEY (`prog_area_fk`) REFERENCES `areas` (`area_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROGRAMAS_ESTADOS1` FOREIGN KEY (`prog_est_fk`) REFERENCES `estados` (`est_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROGRAMAS_ORGANISMOS1` FOREIGN KEY (`prog_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROGRAMAS_USUARIOS1` FOREIGN KEY (`prog_usr_gerente_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROGRAMAS_USUARIOS2` FOREIGN KEY (`prog_usr_adjunto_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROGRAMAS_USUARIOS3` FOREIGN KEY (`prog_usr_sponsor_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROGRAMAS_USUARIOS4` FOREIGN KEY (`prog_usr_pmofed_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prog_cro_fk` FOREIGN KEY (`prog_cro_fk`) REFERENCES `cronogramas` (`cro_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prog_est_pendiente_fk` FOREIGN KEY (`prog_est_pendiente_fk`) REFERENCES `estados` (`est_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prog_pre_fk` FOREIGN KEY (`prog_pre_fk`) REFERENCES `prog_pre` (`progpre_prog_fk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prog_progindices_fk` FOREIGN KEY (`prog_progindices_fk`) REFERENCES `prog_indices` (`progind_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=265 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary table structure for view `programas_proyectos`
--

DROP TABLE IF EXISTS `programas_proyectos`;
/*!50001 DROP VIEW IF EXISTS `programas_proyectos`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `programas_proyectos` (
  `id` tinyint NOT NULL,
  `fichaFk` tinyint NOT NULL,
  `tipoFicha` tinyint NOT NULL,
  `fechaCrea` tinyint NOT NULL,
  `activo` tinyint NOT NULL,
  `organismo` tinyint NOT NULL,
  `nombre` tinyint NOT NULL,
  `estado` tinyint NOT NULL,
  `estadoNombre` tinyint NOT NULL,
  `estadoPendiente` tinyint NOT NULL,
  `areaPk` tinyint NOT NULL,
  `areaNombre` tinyint NOT NULL,
  `solAceptacion` tinyint NOT NULL,
  `gerente` tinyint NOT NULL,
  `gerentePrimerApellido` tinyint NOT NULL,
  `gerentePrimerNombre` tinyint NOT NULL,
  `pmoFederada` tinyint NOT NULL
) ENGINE=MyISAM */;
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
  PRIMARY KEY (`proyind_pk`),
  KEY `proyind_periodo_inicio_idx` (`proyind_periodo_inicio`),
  KEY `proyind_periodo_fin_idx` (`proyind_periodo_fin`)
) ENGINE=InnoDB AUTO_INCREMENT=3355 DEFAULT CHARSET=utf8;
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
  PRIMARY KEY (`proyindpre_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=561 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Relaci\F3n entre un proyecto y una o varias categorias secundarias.';
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
  `proy_otr_ins_eje_fk` int(11) DEFAULT NULL COMMENT 'Instituci\F3n Ejecutora',
  `proy_otr_ent_fk` int(11) DEFAULT NULL COMMENT 'Inicio construcci\F3n del Producto. Asociado a un Entregable.',
  `proy_otr_origen` varchar(1000) DEFAULT NULL COMMENT 'Origen Principal de los Recursos.',
  `proy_otr_plazo` int(11) DEFAULT NULL COMMENT 'Plazo estimado de obra en d\EDas.',
  `proy_otr_observaciones` varchar(4000) DEFAULT NULL COMMENT 'Observaciones.',
  `proy_otr_cat_fk` int(11) DEFAULT NULL COMMENT 'Categor\EDa Principal.',
  `proy_otr_est_pub_fk` int(11) DEFAULT NULL COMMENT 'Estado de Publicaci\F3n.',
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
) ENGINE=InnoDB AUTO_INCREMENT=1471 DEFAULT CHARSET=latin1 COMMENT='Datos del proyecto principalmente para usarse en el visualizador';
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
) ENGINE=InnoDB AUTO_INCREMENT=1658 DEFAULT CHARSET=latin1 COMMENT='Registro de las veces que se publicó el proyecto en el Visualizador.';
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
  `proyreplan_generar_linea_base` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`proyreplan_pk`),
  KEY `proyreplan_proy_fk_idx` (`proyreplan_proy_fk`),
  KEY `proyreplan_fecha_idx` (`proyreplan_fecha`),
  KEY `proyreplan_activo_idx` (`proyreplan_activo`),
  CONSTRAINT `proyreplan_proy_fk` FOREIGN KEY (`proyreplan_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1242 DEFAULT CHARSET=utf8;
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
  `proy_sitact_desc` varchar(4000) DEFAULT NULL,
  `proy_sitact_proy_fk` int(11) NOT NULL,
  `proy_sitact_usu_fk` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`proy_sitact_hist_pk`),
  KEY `proy_sitact_proy_fk_idx` (`proy_sitact_proy_fk`),
  CONSTRAINT `proy_sitact_proy_fk` FOREIGN KEY (`proy_sitact_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18546 DEFAULT CHARSET=utf8;
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
  `proy_descripcion` varchar(4000) DEFAULT NULL,
  `proy_objetivo` varchar(4000) DEFAULT NULL,
  `proy_obj_publico` varchar(4000) DEFAULT NULL,
  `proy_situacion_actual` varchar(4000) DEFAULT NULL,
  `proy_leccion_aprendida` varchar(256) DEFAULT NULL,
  `proy_nombre` varchar(100) DEFAULT NULL,
  `proy_grp` varchar(45) DEFAULT NULL,
  `proy_semaforo_amarillo` int(11) DEFAULT NULL,
  `proy_semaforo_rojo` int(11) DEFAULT NULL,
  `proy_activo` tinyint(1) DEFAULT NULL,
  `proy_fecha_crea` date DEFAULT NULL,
  `proy_fecha_act` date NOT NULL,
  `proy_ult_usuario` int(11) DEFAULT NULL,
  `proy_ult_mod` date DEFAULT NULL,
  `proy_ult_origen` varchar(45) DEFAULT NULL,
  `proy_version` int(11) DEFAULT NULL,
  `proy_fecha_est_act` date DEFAULT NULL,
  `proy_id_migrado` int(11) DEFAULT NULL,
  `proy_publicable` tinyint(1) NOT NULL DEFAULT '1',
  `proy_otr_dat_fk` int(11) DEFAULT NULL,
  `proy_latlng_fk` int(11) DEFAULT NULL,
  `proy_obj_est_fk` int(11) DEFAULT NULL,
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
  CONSTRAINT `proy_obj_est_fk` FOREIGN KEY (`proy_obj_est_fk`) REFERENCES `objetivos_estrategicos` (`obj_est_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_AREAS1` FOREIGN KEY (`proy_area_fk`) REFERENCES `areas` (`area_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_CRONOGRAMA` FOREIGN KEY (`proy_cro_fk`) REFERENCES `cronogramas` (`cro_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_ESTADOS1` FOREIGN KEY (`proy_est_fk`) REFERENCES `estados` (`est_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_ORGANISMOS1` FOREIGN KEY (`proy_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_PROGRAMAS1` FOREIGN KEY (`proy_prog_fk`) REFERENCES `programas` (`prog_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_RIESGOS1` FOREIGN KEY (`proy_risk_fk`) REFERENCES `riesgos` (`risk_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_USUARIOS1` FOREIGN KEY (`proy_usr_adjunto_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_USUARIOS2` FOREIGN KEY (`proy_usr_sponsor_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTOS_USUARIOS4` FOREIGN KEY (`proy_usr_pmofed_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_est_pendiente_fk` FOREIGN KEY (`proy_est_pendiente_fk`) REFERENCES `estados` (`est_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_latlng_fk` FOREIGN KEY (`proy_latlng_fk`) REFERENCES `latlng_proyectos` (`latlng_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_otr_dat_fk` FOREIGN KEY (`proy_otr_dat_fk`) REFERENCES `proy_otros_datos` (`proy_otr_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_pre_fk` FOREIGN KEY (`proy_pre_fk`) REFERENCES `presupuesto` (`pre_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proy_proyindices_fk` FOREIGN KEY (`proy_proyindices_fk`) REFERENCES `proy_indices` (`proyind_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2365 DEFAULT CHARSET=utf8 COMMENT='	';
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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
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
) ENGINE=MyISAM AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;
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
  CONSTRAINT `risk_ent_fk` FOREIGN KEY (`risk_ent_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `risk_usuario_superado_fk_fk` FOREIGN KEY (`risk_usuario_superado_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2049 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;
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
  KEY `cnf_codigo` (`cnf_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=873 DEFAULT CHARSET=utf8;
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
/*!50001 CREATE TABLE `ss_oficina` (
  `ofi_id` tinyint NOT NULL,
  `ofi_nombre` tinyint NOT NULL,
  `ofi_activo` tinyint NOT NULL,
  `ofi_fecha_creacion` tinyint NOT NULL,
  `ofi_origen` tinyint NOT NULL,
  `ofi_usuario` tinyint NOT NULL,
  `ofi_version` tinyint NOT NULL
) ENGINE=MyISAM */;
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
) ENGINE=InnoDB AUTO_INCREMENT=1465 DEFAULT CHARSET=utf8;
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
  PRIMARY KEY (`usu_id`),
  KEY `usu_cod_index` (`usu_cod`),
  KEY `usu_area_org_idx` (`usu_area_org`),
  KEY `usu_correo_electronico_idx` (`usu_correo_electronico`),
  KEY `usu_token_idx` (`usu_token`),
  CONSTRAINT `usu_area_org` FOREIGN KEY (`usu_area_org`) REFERENCES `areas` (`area_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=708 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
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
  PRIMARY KEY (`ss_usu_dat_pk`)
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
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;
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
  CONSTRAINT `tipodoc_inst_tipodoc_fk` FOREIGN KEY (`tipodoc_inst_tipodoc_fk`) REFERENCES `tipo_documento` (`tipdoc_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28110 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'siges_agesic'
--

--
-- Dumping routines for database 'siges_agesic'
--

--
-- Final view structure for view `programas_proyectos`
--

/*!50001 DROP TABLE IF EXISTS `programas_proyectos`*/;
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

/*!50001 DROP TABLE IF EXISTS `ss_oficina`*/;
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
-- Dumping data for table `categoria_proyectos`
--

LOCK TABLES `categoria_proyectos` WRITE;
/*!40000 ALTER TABLE `categoria_proyectos` DISABLE KEYS */;
INSERT INTO `categoria_proyectos` VALUES (1,'TE_SALUD','Salud',1,1,NULL,NULL,NULL),(2,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,NULL),(3,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,NULL),(4,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,NULL),(7,'TE_URBANA','Urbana',1,1,NULL,NULL,NULL),(8,'TE_SOCIAL','Social',1,1,NULL,NULL,NULL),(9,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,NULL),(12,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,NULL),(13,'TE_VARIOS','Varios',1,1,NULL,NULL,NULL),(14,'CA_SALUD','Salud',1,2,NULL,NULL,NULL),(15,'CA_EDUCACION','Educativa',1,2,NULL,NULL,NULL),(16,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,NULL),(17,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,NULL),(18,'CA_URBANA','Urbana',1,2,NULL,NULL,NULL),(19,'CA_SOCIAL','Social',1,2,NULL,NULL,NULL),(20,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,NULL),(21,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,NULL),(22,'CA_VARIOS','Varios',1,2,NULL,NULL,NULL),(23,'TE_SALUD','Salud',1,1,NULL,NULL,1),(24,'TE_EDUCATIVA','Cultura y Educación',0,1,NULL,NULL,1),(25,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,1),(26,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,1),(27,'TE_URBANA','Urbana',1,1,NULL,NULL,1),(28,'TE_SOCIAL','Social',1,1,NULL,NULL,1),(29,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,1),(30,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,1),(31,'TE_VARIOS','Varios',1,1,NULL,NULL,1),(32,'CA_SALUD','Salud',0,2,NULL,NULL,1),(33,'CA_EDUCACION','Educación',0,2,NULL,NULL,1),(34,'CA_VIVIENDA','Viviendas',0,2,NULL,NULL,1),(35,'CA_TERRESTRE','Transporte Terrestre',0,2,NULL,NULL,1),(36,'CA_URBANA','Urbana',0,2,NULL,NULL,1),(37,'CA_SOCIAL','Social',0,2,NULL,NULL,1),(38,'CA_SEGURIDAD','Seguridad',0,2,NULL,NULL,1),(39,'CA_SANIDAD','Obras Sanitarias',0,2,NULL,NULL,1),(40,'CA_VARIOS','Varios',0,2,NULL,NULL,1),(41,'TE_SALUD','Salud',1,1,NULL,NULL,2),(42,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,2),(43,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,2),(44,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,2),(45,'TE_URBANA','Urbana',1,1,NULL,NULL,2),(46,'TE_SOCIAL','Social',1,1,NULL,NULL,2),(47,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,2),(48,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,2),(49,'TE_VARIOS','Varios',1,1,NULL,NULL,2),(50,'CA_SALUD','Salud',1,2,NULL,NULL,2),(51,'CA_EDUCACION','Educativa',1,2,NULL,NULL,2),(52,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,2),(53,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,2),(54,'CA_URBANA','Urbana',1,2,NULL,NULL,2),(55,'CA_SOCIAL','Social',1,2,NULL,NULL,2),(56,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,2),(57,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,2),(58,'CA_VARIOS','Varios',1,2,NULL,NULL,2),(59,'TE_SALUD','Salud',1,1,NULL,NULL,3),(60,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,3),(61,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,3),(62,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,3),(63,'TE_URBANA','Urbana',1,1,NULL,NULL,3),(64,'TE_SOCIAL','Social',1,1,NULL,NULL,3),(65,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,3),(66,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,3),(67,'TE_VARIOS','Varios',1,1,NULL,NULL,3),(68,'CA_SALUD','Salud',1,2,NULL,NULL,3),(69,'CA_EDUCACION','Educativa',1,2,NULL,NULL,3),(70,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,3),(71,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,3),(72,'CA_URBANA','Urbana',1,2,NULL,NULL,3),(73,'CA_SOCIAL','Social',1,2,NULL,NULL,3),(74,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,3),(75,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,3),(76,'CA_VARIOS','Varios',1,2,NULL,NULL,3),(77,'TE_SALUD','Salud',1,1,NULL,NULL,4),(78,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,4),(79,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,4),(80,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,4),(81,'TE_URBANA','Urbana',1,1,NULL,NULL,4),(82,'TE_SOCIAL','Social',1,1,NULL,NULL,4),(83,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,4),(84,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,4),(85,'TE_VARIOS','Varios',1,1,NULL,NULL,4),(86,'CA_SALUD','Salud',1,2,NULL,NULL,4),(87,'CA_EDUCACION','Educativa',1,2,NULL,NULL,4),(88,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,4),(89,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,4),(90,'CA_URBANA','Urbana',1,2,NULL,NULL,4),(91,'CA_SOCIAL','Social',1,2,NULL,NULL,4),(92,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,4),(93,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,4),(94,'CA_VARIOS','Varios',1,2,NULL,NULL,4),(95,'TE_SALUD','Salud',1,1,NULL,NULL,5),(96,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,5),(97,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,5),(98,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,5),(99,'TE_URBANA','Urbana',1,1,NULL,NULL,5),(100,'TE_SOCIAL','Social',1,1,NULL,NULL,5),(101,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,5),(102,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,5),(103,'TE_VARIOS','Varios',1,1,NULL,NULL,5),(104,'CA_SALUD','Salud',1,2,NULL,NULL,5),(105,'CA_EDUCACION','Educativa',1,2,NULL,NULL,5),(106,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,5),(107,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,5),(108,'CA_URBANA','Urbana',1,2,NULL,NULL,5),(109,'CA_SOCIAL','Social',1,2,NULL,NULL,5),(110,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,5),(111,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,5),(112,'CA_VARIOS','Varios',1,2,NULL,NULL,5),(113,'TE_SALUD','Salud y Cuidados',1,1,NULL,NULL,6),(114,'TE_EDUCATIVA','Cultura y Educación',1,1,NULL,NULL,6),(115,'TE_VIVIENDA','Viviendas',0,1,NULL,NULL,6),(116,'TE_TERRESTRE','Transporte Terrestre',0,1,NULL,NULL,6),(117,'TE_URBANA','Urbana',0,1,NULL,NULL,6),(118,'TE_SOCIAL','Social',0,1,NULL,NULL,6),(119,'TE_SEGURIDAD','Seguridad',0,1,NULL,NULL,6),(120,'TE_SANIDAD','Obras Sanitarias',0,1,NULL,NULL,6),(121,'TE_VARIOS','Varios',0,1,NULL,NULL,6),(122,'CA_SALUD','Salud',0,2,NULL,NULL,6),(123,'CA_EDUCACION','Educación',0,2,NULL,NULL,6),(124,'CA_VIVIENDA','Viviendas',0,2,NULL,NULL,6),(125,'CA_TERRESTRE','Transporte Terrestre',0,2,NULL,NULL,6),(126,'CA_URBANA','Urbana',0,2,NULL,NULL,6),(127,'CA_SOCIAL','Social',0,2,NULL,NULL,6),(128,'CA_SEGURIDAD','Seguridad',0,2,NULL,NULL,6),(129,'CA_SANIDAD','Obras Sanitarias',0,2,NULL,NULL,6),(130,'CA_VARIOS','Varios',0,2,NULL,NULL,6),(131,'TE_SALUD','Salud',1,1,NULL,NULL,7),(132,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,7),(133,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,7),(134,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,7),(135,'TE_URBANA','Urbana',1,1,NULL,NULL,7),(136,'TE_SOCIAL','Social',1,1,NULL,NULL,7),(137,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,7),(138,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,7),(139,'TE_VARIOS','Varios',1,1,NULL,NULL,7),(140,'CA_SALUD','Salud',1,2,NULL,NULL,7),(141,'CA_EDUCACION','Educativa',1,2,NULL,NULL,7),(142,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,7),(143,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,7),(144,'CA_URBANA','Urbana',1,2,NULL,NULL,7),(145,'CA_SOCIAL','Social',1,2,NULL,NULL,7),(146,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,7),(147,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,7),(148,'CA_VARIOS','Varios',1,2,NULL,NULL,7),(149,'TE_SALUD','Salud',1,1,NULL,NULL,8),(150,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,8),(151,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,8),(152,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,8),(153,'TE_URBANA','Urbana',1,1,NULL,NULL,8),(154,'TE_SOCIAL','Social',1,1,NULL,NULL,8),(155,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,8),(156,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,8),(157,'TE_VARIOS','Varios',1,1,NULL,NULL,8),(158,'CA_SALUD','Salud',1,2,NULL,NULL,8),(159,'CA_EDUCACION','Educativa',1,2,NULL,NULL,8),(160,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,8),(161,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,8),(162,'CA_URBANA','Urbana',1,2,NULL,NULL,8),(163,'CA_SOCIAL','Social',1,2,NULL,NULL,8),(164,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,8),(165,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,8),(166,'CA_VARIOS','Varios',1,2,NULL,NULL,8),(167,'TE_SALUD','Salud',1,1,NULL,NULL,9),(168,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,9),(169,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,9),(170,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,9),(171,'TE_URBANA','Urbana',1,1,NULL,NULL,9),(172,'TE_SOCIAL','Social',1,1,NULL,NULL,9),(173,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,9),(174,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,9),(175,'TE_VARIOS','Varios',1,1,NULL,NULL,9),(176,'CA_SALUD','Salud',1,2,NULL,NULL,9),(177,'CA_EDUCACION','Educativa',1,2,NULL,NULL,9),(178,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,9),(179,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,9),(180,'CA_URBANA','Urbana',1,2,NULL,NULL,9),(181,'CA_SOCIAL','Social',1,2,NULL,NULL,9),(182,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,9),(183,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,9),(184,'CA_VARIOS','Varios',1,2,NULL,NULL,9),(185,'TE_SALUD','Salud',1,1,NULL,NULL,10),(186,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,10),(187,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,10),(188,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,10),(189,'TE_URBANA','Urbana',1,1,NULL,NULL,10),(190,'TE_SOCIAL','Social',1,1,NULL,NULL,10),(191,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,10),(192,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,10),(193,'TE_VARIOS','Varios',1,1,NULL,NULL,10),(194,'CA_SALUD','Salud',1,2,NULL,NULL,10),(195,'CA_EDUCACION','Educativa',1,2,NULL,NULL,10),(196,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,10),(197,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,10),(198,'CA_URBANA','Urbana',1,2,NULL,NULL,10),(199,'CA_SOCIAL','Social',1,2,NULL,NULL,10),(200,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,10),(201,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,10),(202,'CA_VARIOS','Varios',1,2,NULL,NULL,10),(203,'TE_SALUD','Salud',1,1,NULL,NULL,11),(204,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,11),(205,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,11),(206,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,11),(207,'TE_URBANA','Urbana',1,1,NULL,NULL,11),(208,'TE_SOCIAL','Social',1,1,NULL,NULL,11),(209,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,11),(210,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,11),(211,'TE_VARIOS','Varios',1,1,NULL,NULL,11),(212,'CA_SALUD','Salud',1,2,NULL,NULL,11),(213,'CA_EDUCACION','Educativa',1,2,NULL,NULL,11),(214,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,11),(215,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,11),(216,'CA_URBANA','Urbana',1,2,NULL,NULL,11),(217,'CA_SOCIAL','Social',1,2,NULL,NULL,11),(218,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,11),(219,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,11),(220,'CA_VARIOS','Varios',1,2,NULL,NULL,11),(221,'TE_SALUD','Salud',1,1,NULL,NULL,12),(222,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,12),(223,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,12),(224,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,12),(225,'TE_URBANA','Urbana',1,1,NULL,NULL,12),(226,'TE_SOCIAL','Social',1,1,NULL,NULL,12),(227,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,12),(228,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,12),(229,'TE_VARIOS','Varios',1,1,NULL,NULL,12),(230,'CA_SALUD','Salud',1,2,NULL,NULL,12),(231,'CA_EDUCACION','Educativa',1,2,NULL,NULL,12),(232,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,12),(233,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,12),(234,'CA_URBANA','Urbana',1,2,NULL,NULL,12),(235,'CA_SOCIAL','Social',1,2,NULL,NULL,12),(236,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,12),(237,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,12),(238,'CA_VARIOS','Varios',1,2,NULL,NULL,12),(239,'TE_SALUD','Salud',1,1,NULL,NULL,13),(240,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,13),(241,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,13),(242,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,13),(243,'TE_URBANA','Urbana',1,1,NULL,NULL,13),(244,'TE_SOCIAL','Social',1,1,NULL,NULL,13),(245,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,13),(246,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,13),(247,'TE_VARIOS','Varios',1,1,NULL,NULL,13),(248,'CA_SALUD','Salud',1,2,NULL,NULL,13),(249,'CA_EDUCACION','Educativa',1,2,NULL,NULL,13),(250,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,13),(251,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,13),(252,'CA_URBANA','Urbana',1,2,NULL,NULL,13),(253,'CA_SOCIAL','Social',1,2,NULL,NULL,13),(254,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,13),(255,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,13),(256,'CA_VARIOS','Varios',1,2,NULL,NULL,13),(257,'TE_SALUD','Salud',1,1,NULL,NULL,14),(258,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,14),(259,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,14),(260,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,14),(261,'TE_URBANA','Urbana',1,1,NULL,NULL,14),(262,'TE_SOCIAL','Social',1,1,NULL,NULL,14),(263,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,14),(264,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,14),(265,'TE_VARIOS','Varios',1,1,NULL,NULL,14),(266,'CA_SALUD','Salud',1,2,NULL,NULL,14),(267,'CA_EDUCACION','Educativa',1,2,NULL,NULL,14),(268,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,14),(269,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,14),(270,'CA_URBANA','Urbana',1,2,NULL,NULL,14),(271,'CA_SOCIAL','Social',1,2,NULL,NULL,14),(272,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,14),(273,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,14),(274,'CA_VARIOS','Varios',1,2,NULL,NULL,14),(275,'TE_SALUD','Salud',1,1,NULL,NULL,15),(276,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,15),(277,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,15),(278,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,15),(279,'TE_URBANA','Urbana',1,1,NULL,NULL,15),(280,'TE_SOCIAL','Social',1,1,NULL,NULL,15),(281,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,15),(282,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,15),(283,'TE_VARIOS','Varios',1,1,NULL,NULL,15),(284,'CA_SALUD','Salud',1,2,NULL,NULL,15),(285,'CA_EDUCACION','Educativa',1,2,NULL,NULL,15),(286,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,15),(287,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,15),(288,'CA_URBANA','Urbana',1,2,NULL,NULL,15),(289,'CA_SOCIAL','Social',1,2,NULL,NULL,15),(290,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,15),(291,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,15),(292,'CA_VARIOS','Varios',1,2,NULL,NULL,15),(293,'TE_SALUD','Salud y Cuidados',1,1,NULL,NULL,16),(294,'TE_EDUCATIVA','Cultura y Educación',1,1,NULL,NULL,16),(295,'TE_VIVIENDA','Viviendas',0,1,NULL,NULL,16),(296,'TE_TERRESTRE','Transporte Terrestre',0,1,NULL,NULL,16),(297,'TE_URBANA','Urbana',0,1,NULL,NULL,16),(298,'TE_SOCIAL','Social',0,1,NULL,NULL,16),(299,'TE_SEGURIDAD','Seguridad',0,1,NULL,NULL,16),(300,'TE_SANIDAD','Obras Sanitarias',0,1,NULL,NULL,16),(301,'TE_VARIOS','Varios',0,1,NULL,NULL,16),(302,'CA_SALUD','Salud',0,2,NULL,NULL,16),(303,'CA_EDUCACION','Educación',0,2,NULL,NULL,16),(304,'CA_VIVIENDA','Viviendas',0,2,NULL,NULL,16),(305,'CA_TERRESTRE','Transporte Terrestre',0,2,NULL,NULL,16),(306,'CA_URBANA','Urbana',0,2,NULL,NULL,16),(307,'CA_SOCIAL','Social',0,2,NULL,NULL,16),(308,'CA_SEGURIDAD','Seguridad',0,2,NULL,NULL,16),(309,'CA_SANIDAD','Obras Sanitarias',0,2,NULL,NULL,16),(310,'CA_VARIOS','Varios',0,2,NULL,NULL,16),(311,'APPS','Aplicaciones Móviles',0,1,NULL,NULL,1),(312,'CA_COLAB_CIUDADANA','Colaboración Ciudadana',0,2,NULL,NULL,1),(313,'COMPRAS_PUB','Compras Públicas',0,1,NULL,NULL,1),(314,'ECONOMIA','Economía',0,1,NULL,NULL,1),(315,'EMPRESAS','Empresas',0,1,NULL,NULL,1),(316,'CA_ENERGIA','Energética',0,2,NULL,NULL,1),(317,'TE_GOB_LOCALES','Estadísticas',0,1,NULL,NULL,1),(318,'CIUDADANIA','Fortalecimiento de la Ciudadanía',0,1,NULL,NULL,1),(319,'GEST_INST','Gestión Institucional',0,1,NULL,NULL,1),(320,'POL_SOC','Inclusión',0,1,NULL,NULL,1),(321,'INMUEBLES','Inmuebles',0,1,NULL,NULL,1),(322,'PAR_ABIERTO','Parlamento abierto',0,1,NULL,NULL,1),(323,'CA_PARTICIPACION','Participación ciudadana',0,2,NULL,NULL,1),(324,'CA_RENDICION','Rendición de Cuentas',0,2,NULL,NULL,1),(325,'SOFT_PUB','Software Público',0,1,NULL,NULL,1),(326,'CA_TECNO','Tecnológica',0,2,NULL,NULL,1),(327,'TRAMITES','Trámites',0,1,NULL,NULL,1),(328,'CA_TRANSPARENCIA','Transparencia',0,2,NULL,NULL,1),(329,'CA_AEREO','Transporte Áereo',0,2,NULL,NULL,1),(330,'CA_MARITIMO','Transporte Marítimo',0,2,NULL,NULL,1),(331,'TE_SALUD','Salud',1,1,NULL,NULL,17),(332,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,17),(333,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,17),(334,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,17),(335,'TE_URBANA','Urbana',1,1,NULL,NULL,17),(336,'TE_SOCIAL','Social',1,1,NULL,NULL,17),(337,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,17),(338,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,17),(339,'TE_VARIOS','Varios',1,1,NULL,NULL,17),(340,'CA_SALUD','Salud',1,2,NULL,NULL,17),(341,'CA_EDUCACION','Educativa',1,2,NULL,NULL,17),(342,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,17),(343,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,17),(344,'CA_URBANA','Urbana',1,2,NULL,NULL,17),(345,'CA_SOCIAL','Social',1,2,NULL,NULL,17),(346,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,17),(347,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,17),(348,'CA_VARIOS','Varios',1,2,NULL,NULL,17),(349,'TE_SALUD','Salud',1,1,NULL,NULL,19),(350,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,19),(351,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,19),(352,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,19),(353,'TE_URBANA','Urbana',1,1,NULL,NULL,19),(354,'TE_SOCIAL','Social',1,1,NULL,NULL,19),(355,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,19),(356,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,19),(357,'TE_VARIOS','Varios',1,1,NULL,NULL,19),(358,'CA_SALUD','Salud',1,2,NULL,NULL,19),(359,'CA_EDUCACION','Educativa',1,2,NULL,NULL,19),(360,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,19),(361,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,19),(362,'CA_URBANA','Urbana',1,2,NULL,NULL,19),(363,'CA_SOCIAL','Social',1,2,NULL,NULL,19),(364,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,19),(365,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,19),(366,'CA_VARIOS','Varios',1,2,NULL,NULL,19),(367,'TE_SALUD','Salud',1,1,NULL,NULL,18),(368,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,18),(369,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,18),(370,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,18),(371,'TE_URBANA','Urbana',1,1,NULL,NULL,18),(372,'TE_SOCIAL','Social',1,1,NULL,NULL,18),(373,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,18),(374,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,18),(375,'TE_VARIOS','Varios',1,1,NULL,NULL,18),(376,'CA_SALUD','Salud',1,2,NULL,NULL,18),(377,'CA_EDUCACION','Educativa',1,2,NULL,NULL,18),(378,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,18),(379,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,18),(380,'CA_URBANA','Urbana',1,2,NULL,NULL,18),(381,'CA_SOCIAL','Social',1,2,NULL,NULL,18),(382,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,18),(383,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,18),(384,'CA_VARIOS','Varios',1,2,NULL,NULL,18),(385,'TE_EDUCACION','Educativa',0,1,NULL,NULL,6),(386,'TE_ENERGIA','Energética',0,1,NULL,NULL,6),(387,'TE_TECNO','Tecnológica',0,1,NULL,NULL,6),(388,'TE_AEREO','Transporte Áereo',0,1,NULL,NULL,6),(389,'TE_MARITIMO','Transporte Marítimo',0,1,NULL,NULL,6),(390,'TE_SALUD','Salud',1,1,NULL,NULL,20),(391,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,20),(392,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,20),(393,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,20),(394,'TE_URBANA','Urbana',1,1,NULL,NULL,20),(395,'TE_SOCIAL','Social',1,1,NULL,NULL,20),(396,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,20),(397,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,20),(398,'TE_VARIOS','Varios',1,1,NULL,NULL,20),(399,'CA_SALUD','Salud',1,2,NULL,NULL,20),(400,'CA_EDUCACION','Educativa',1,2,NULL,NULL,20),(401,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,20),(402,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,20),(403,'CA_URBANA','Urbana',1,2,NULL,NULL,20),(404,'CA_SOCIAL','Social',1,2,NULL,NULL,20),(405,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,20),(406,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,20),(407,'CA_VARIOS','Varios',1,2,NULL,NULL,20),(408,'TE_SALUD','Salud',1,1,NULL,NULL,21),(409,'TE_EDUCATIVA','Educativa',1,1,NULL,NULL,21),(410,'TE_VIVIENDA','Viviendas',1,1,NULL,NULL,21),(411,'TE_TERRESTRE','Transporte Terrestre',1,1,NULL,NULL,21),(412,'TE_URBANA','Urbana',1,1,NULL,NULL,21),(413,'TE_SOCIAL','Social',1,1,NULL,NULL,21),(414,'TE_SEGURIDAD','Seguridad',1,1,NULL,NULL,21),(415,'TE_SANIDAD','Obras Sanitarias',1,1,NULL,NULL,21),(416,'TE_VARIOS','Varios',1,1,NULL,NULL,21),(417,'CA_SALUD','Salud',1,2,NULL,NULL,21),(418,'CA_EDUCACION','Educativa',1,2,NULL,NULL,21),(419,'CA_VIVIENDA','Viviendas',1,2,NULL,NULL,21),(420,'CA_TERRESTRE','Transporte Terrestre',1,2,NULL,NULL,21),(421,'CA_URBANA','Urbana',1,2,NULL,NULL,21),(422,'CA_SOCIAL','Social',1,2,NULL,NULL,21),(423,'CA_SEGURIDAD','Seguridad',1,2,NULL,NULL,21),(424,'CA_SANIDAD','Obras Sanitarias',1,2,NULL,NULL,21),(425,'CA_VARIOS','Varios',1,2,NULL,NULL,21),(426,'Cecilia','Cecilia',0,2,NULL,NULL,6),(427,'Cecilia','Cecilia',1,2,NULL,NULL,1),(428,'TE_EDUCACION','Educativa',1,1,NULL,NULL,1),(429,'TE_ENERGIA','Energética',1,1,NULL,NULL,1),(430,'TE_TECNO','Tecnológica',1,1,NULL,NULL,1),(431,'TE_AEREO','Transporte Áereo',1,1,NULL,NULL,1),(432,'TE_MARITIMO','Transporte Marítimo',1,1,NULL,NULL,1),(433,'aaaa','aaaa',1,1,NULL,NULL,1),(434,'dsa12312','dsasd',1,2,NULL,NULL,1),(435,'aaaa','aaaa',0,1,NULL,NULL,6),(436,'dsa12312','dsasd',0,2,NULL,NULL,6),(437,'ACERCA_CIUDA','Acercamiento a la Ciudadanía',1,1,NULL,NULL,6),(438,'APPS','Aplicaciones Móviles',1,1,NULL,NULL,6),(439,'CA_COLAB_CIUDADANA','Colaboración Ciudadana',1,2,NULL,NULL,6),(440,'COMPRAS_PUB','Compras Públicas',1,1,NULL,NULL,6),(441,'DESA_SOCIAL','Desarrollo Social',1,1,NULL,NULL,6),(442,'ECONOMIA','Economía',1,1,NULL,NULL,6),(443,'EMPRESAS','Empresas, Industria y Energía',1,1,NULL,NULL,6),(444,'CA_ENERGIA','Energética',0,2,NULL,NULL,6),(445,'TE_GOB_LOCALES','Estadísticas',1,1,NULL,NULL,6),(446,'FIN_PUB','Finanzas Públicas',1,1,NULL,NULL,6),(447,'CIUDADANIA','Fortalecimiento de la Ciudadanía',1,1,NULL,NULL,6),(448,'GEST_INST','Gestión Institucional',1,1,NULL,NULL,6),(449,'GOB_DEP','Gobiernos Departamentales',1,1,NULL,NULL,6),(450,'POL_SOC','Inclusión',1,1,NULL,NULL,6),(451,'INMUEBLES','Inmuebles',1,1,NULL,NULL,6),(452,'INN_TEC','Innovación Tecnológica',1,2,NULL,NULL,6),(453,'JUS_ANTICO','Justicia, Anti-Corrupución',1,1,NULL,NULL,6),(454,'MEDIO_AMBIENTE','Medio Ambiente',1,1,NULL,NULL,6),(455,'MON_DDHH','Monitoreo de Derechos Humanos',1,1,NULL,NULL,6),(456,'PAR_ABIERTO','Parlamento abierto',1,1,NULL,NULL,6),(457,'CA_PARTICIPACION','Participación ciudadana',1,2,NULL,NULL,6),(458,'PART_CIU','Participación Ciudadana',1,1,NULL,NULL,6),(459,'CA_RENDICION','Rendición de Cuentas',1,2,NULL,NULL,6),(460,'SOFT_PUB','Software Público',1,1,NULL,NULL,6),(461,'CA_TECNO','Tecnológica',0,2,NULL,NULL,6),(462,'TRAMITES','Trámites',1,1,NULL,NULL,6),(463,'CA_TRANSPARENCIA','Transparencia',1,2,NULL,NULL,6),(464,'TRANSP_AIP_RENDCTAS','Transparencia, Acceso a la Información Pública y Rendición de Cuentas',1,1,NULL,NULL,6),(465,'CA_AEREO','Transporte Aereo',0,2,NULL,NULL,6),(466,'CA_MARITIMO','Transporte Marítimo',0,2,NULL,NULL,6),(467,'CATEGORIA NUEVA','CATEGORIA NUEVA',1,2,NULL,NULL,6),(468,'TEMA NUEVO','TEMA NUEVO',1,1,NULL,NULL,6),(469,'ACERCA_CIUDA','Acercamiento a la Ciudadanía',1,1,NULL,NULL,16),(470,'APPS','Aplicaciones Móviles',1,1,NULL,NULL,16),(471,'CATEGORIA NUEVA','CATEGORIA NUEVA',1,2,NULL,NULL,16),(472,'CA_COLAB_CIUDADANA','Colaboración Ciudadana',1,2,NULL,NULL,16),(473,'COMPRAS_PUB','Compras Públicas',1,1,NULL,NULL,16),(474,'DESA_SOCIAL','Desarrollo Social',1,1,NULL,NULL,16),(475,'ECONOMIA','Economía',1,1,NULL,NULL,16),(476,'EMPRESAS','Empresas, Industria y Energía',1,1,NULL,NULL,16),(477,'CA_ENERGIA','Energética',0,2,NULL,NULL,16),(478,'TE_GOB_LOCALES','Estadísticas',1,1,NULL,NULL,16),(479,'FIN_PUB','Finanzas Públicas',1,1,NULL,NULL,16),(480,'CIUDADANIA','Fortalecimiento de la Ciudadanía',1,1,NULL,NULL,16),(481,'GEST_INST','Gestión Institucional',1,1,NULL,NULL,16),(482,'GOB_DEP','Gobiernos Departamentales',1,1,NULL,NULL,16),(483,'POL_SOC','Inclusión',1,1,NULL,NULL,16),(484,'INMUEBLES','Inmuebles',1,1,NULL,NULL,16),(485,'INN_TEC','Innovación Tecnológica',1,2,NULL,NULL,16),(486,'JUS_ANTICO','Justicia, Anti-Corrupución',1,1,NULL,NULL,16),(487,'MEDIO_AMBIENTE','Medio Ambiente',1,1,NULL,NULL,16),(488,'MON_DDHH','Monitoreo de Derechos Humanos',1,1,NULL,NULL,16),(489,'PAR_ABIERTO','Parlamento abierto',1,1,NULL,NULL,16),(490,'CA_PARTICIPACION','Participación ciudadana',1,2,NULL,NULL,16),(491,'PART_CIU','Participación Ciudadana',1,1,NULL,NULL,16),(492,'CA_RENDICION','Rendición de Cuentas',1,2,NULL,NULL,16),(493,'SOFT_PUB','Software Público',1,1,NULL,NULL,16),(494,'CA_TECNO','Tecnológica',0,2,NULL,NULL,16),(495,'TEMA NUEVO','TEMA NUEVO',1,1,NULL,NULL,16),(496,'TRAMITES','Trámites',1,1,NULL,NULL,16),(497,'CA_TRANSPARENCIA','Transparencia',1,2,NULL,NULL,16),(498,'TRANSP_AIP_RENDCTAS','Transparencia, Acceso a la Información Pública y Rendición de Cuentas',1,1,NULL,NULL,16),(499,'CA_AEREO','Transporte Aereo',0,2,NULL,NULL,16),(500,'CA_MARITIMO','Transporte Marítimo',0,2,NULL,NULL,16);
/*!40000 ALTER TABLE `categoria_proyectos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'siges_agesic'
--

--
-- Dumping routines for database 'siges_agesic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


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
-- Dumping data for table `plantilla_cronograma`
--

LOCK TABLES `plantilla_cronograma` WRITE;
/*!40000 ALTER TABLE `plantilla_cronograma` DISABLE KEYS */;
INSERT INTO `plantilla_cronograma` VALUES (2,'Desarrollo en base a Procedimiento de Compra Solicitud de Propuestas',1,NULL),(3,'Electrificación rural',6,NULL),(4,'Arquitectura',6,NULL),(5,'Infraestructura',6,NULL),(6,'Procedimiento de Compra Solicitud de Propuestas',1,NULL),(7,'Procedimiento de Compra Compra Directa Por Excepción (entre mínimo para contadora y LPN)',1,NULL),(8,'Procedimiento de Compra Compra Directa (sin publicación)',1,NULL),(9,'Procedimiento de Compra Compra Directa (con publicación)',1,NULL),(10,'Procedimiento de Compra Licitación Pública',1,NULL),(11,'Procedimiento de Compra Licitación Abreviada',1,NULL),(12,'Procedimiento de Compra Solicitud de Propuestas',15,NULL),(13,'Procedimiento de Compra Compra Directa Por Excepción (entre mínimo para contadora y LPN)	',15,NULL),(14,'Procedimiento de Compra Compra Directa (sin publicación)',15,NULL),(15,'Procedimiento de Compra Compra Directa (con publicación)',15,NULL),(16,'Procedimiento de Compra Licitación Pública',15,NULL),(17,'Procedimiento de Compra Licitación Abreviada',15,NULL),(18,'Procedimiento de Compra Licitación Pública Internacional (monto menor a LP RRGG)',15,NULL),(19,'Procedimiento de Compra Licitación Pública Internacional (monto mayor a LP RRGG)',15,NULL),(20,'Procedimiento de Compra Licitación Pública Nacional',15,NULL),(21,'Procedimiento de Compra Compra Directa BID (sin revisión Contadora Delegada)',15,NULL),(22,'Procedimiento de Compra Comparación de Precios (con revisión Contadora Delegada)',15,NULL),(23,'Procedimiento de Compra Compra Directa BID (con revisión Contadora Delegada)',15,NULL),(24,'Procedimiento de Compra Comparación de Precios (sin revisión Contadora Delegada)',15,NULL),(25,'Procedimiento de Compra Licitación Pública Internacional (monto menor a LP RRGG)',1,NULL),(26,'Procedimiento de Compra Licitación Pública Internacional (monto mayor a LP RRGG)',1,NULL),(27,'Procedimiento de Compra Licitación Pública Nacional',1,NULL),(28,'Procedimiento de Compra Compra Directa BID (con revisión Contadora Delegada)',1,NULL),(29,'Procedimiento de Compra Comparación de Precios (con revisión Contadora Delegada)',1,NULL),(30,'Procedimiento de Compra Comparación de Precios (sin revisión Contadora Delegada)',1,NULL),(31,'Procedimiento de Compra Compra Directa BID (sin revisión Contadora Delegada)',1,NULL),(32,'Horacio',1,NULL);
/*!40000 ALTER TABLE `plantilla_cronograma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'siges_agesic'
--

--
-- Dumping routines for database 'siges_agesic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;



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
-- Dumping data for table `plantilla_entregables`
--

LOCK TABLES `plantilla_entregables` WRITE;
/*!40000 ALTER TABLE `plantilla_entregables` DISABLE KEYS */;
INSERT INTO `plantilla_entregables` VALUES (4,'Proyecto',1,0,0,NULL,2,1),(5,'Proceso Licitatorio',2,0,0,NULL,2,2),(6,'Relevamiento',3,30,60,NULL,2,3),(7,'Elaboración de TDR y Pliego ',3,30,120,6,2,4),(8,'Publicación del Pliego ',3,5,20,7,2,5),(9,'Evaluación Técnica de la Oferta ',3,30,35,8,2,6),(10,'Adjudicación de la Oferta',3,5,25,9,2,7),(11,'Ejecución del Proyecto',2,0,0,NULL,2,8),(12,'Plan de Trabajo',3,10,10,10,2,9),(13,'Análisis de Requerimientos ',3,20,35,12,2,10),(14,'Desarrollo de los Aplicativos e Implantación del sistema ',3,40,120,13,2,11),(15,'Transferencia de conocimientos ',3,20,30,14,2,12),(16,'Mantenimiento ',2,10,90,15,2,13),(17,'Proyecto de electrificación rural',0,0,0,NULL,3,1),(18,'Ingreso de documentación',1,0,1,NULL,3,2),(19,'Aprobación CIER',1,0,1,18,3,3),(20,'Firma de conformes',1,0,1,19,3,4),(21,'Depósito de garantía',1,10,1,20,3,5),(22,'Postación',1,20,1,21,3,6),(23,'Tendido',1,30,1,22,3,7),(24,'Corte',1,40,1,23,3,8),(25,'Inauguración',1,0,1,24,3,9),(26,'Proyecto',0,0,1,NULL,4,1),(27,'Implantación de obra',1,0,1,NULL,4,2),(28,'Replanteos',2,0,1,NULL,4,3),(29,'Implantaciones varias',2,0,1,NULL,4,4),(30,'Movimiento de tierra',1,0,1,NULL,4,5),(31,'Excavaciones manuales',2,0,1,NULL,4,6),(32,'Pozo en arcilla compacta 1 a 2 metros',3,0,1,NULL,4,7),(33,'Pozo en tosca semidura 2 a 4 metros',3,0,1,NULL,4,8),(34,'Carga en camión',3,0,1,NULL,4,9),(35,'Demoliciones manuales',2,0,1,NULL,4,10),(36,'Demolición manual de muros',3,0,1,NULL,4,11),(37,'Demolición manual de muros de H.A.',3,0,1,NULL,4,12),(38,'Demolición manual cimientos de H.A.',3,0,1,NULL,4,13),(39,'Demolición de contrapiso de cascote',3,0,1,NULL,4,14),(40,'Picado manual revoque mortero de cal',3,0,1,NULL,4,15),(41,'Picado de paredes de mampostería',3,0,1,NULL,4,16),(42,'Picado de revestimientos',3,0,1,NULL,4,17),(43,'Retiro de aberturas',3,0,1,NULL,4,18),(44,'Retiro de artefactos sanitarios',3,0,1,NULL,4,19),(45,'Retiro de cubierta de chapas',3,0,1,NULL,4,20),(46,'Cimentaciones',1,0,1,NULL,4,21),(47,'Muros de contención',2,0,1,NULL,4,22),(48,'Hormigón armado',3,0,1,NULL,4,23),(49,'Pantallas',2,0,1,NULL,4,24),(50,'Pantalla de hormigón armado',3,0,1,NULL,4,25),(51,'Cimientos',2,0,1,NULL,4,26),(52,'Dados de hormigón ciclópeo',3,0,1,NULL,4,27),(53,'Zapata corrida de hormigón armado',3,0,1,NULL,4,28),(54,'Patin de hormigón armado',3,0,1,NULL,4,29),(55,'Vigas de cimentación hormigón armado',3,0,1,NULL,4,30),(56,'Platea de hormigón armado',3,0,1,NULL,4,31),(57,'Pilotaje',2,0,1,NULL,4,32),(58,'Estructuras de hormigón armado',1,0,1,NULL,4,33),(59,'Pilares y vigas',2,0,1,NULL,4,34),(60,'Pilares y pantallas',3,0,1,NULL,4,35),(61,'Vigas y dinteles',3,0,1,NULL,4,36),(62,'Losas',2,0,1,NULL,4,37),(63,'Losas de escalera',3,0,1,NULL,4,38),(64,'Tanques de agua',2,0,1,NULL,4,39),(65,'Pavimentos de hormigón',2,0,1,NULL,4,40),(66,'Estructuras de acero',1,0,1,NULL,4,41),(67,'Cerchas de perfil de hierro',2,0,1,NULL,4,42),(68,'Estructuras de madera',1,0,1,NULL,4,43),(69,'Cerchas de madera',2,0,1,NULL,4,44),(70,'Mampostería',1,0,1,NULL,4,45),(71,'Mampostería de ladrillo',2,0,1,NULL,4,46),(72,'Muro de 15 cm sin revocar',3,0,1,NULL,4,47),(73,'Muro de 20 cm',3,0,1,NULL,4,48),(74,'Muro de 30 cm',3,0,1,NULL,4,49),(75,'Tabique de espejo de 8 cm',3,0,1,NULL,4,50),(76,'Mampostería de ticholos',2,0,1,NULL,4,51),(77,'Muro de 15 cm (ticholo 12x25x25)',3,0,1,NULL,4,52),(78,'Muro de 20 cm (ticholo 12x17x25)',3,0,1,NULL,4,53),(79,'Muro de 30 cm (ticholo 25x25x25)',3,0,1,NULL,4,54),(80,'Mampostería de bloques de hormigón vibrado',2,0,1,NULL,4,55),(81,'Muro de 12 cm (Block 12x19x39)',3,0,1,NULL,4,56),(82,'Muro de 19 cm (Block 19x19x39)',3,0,1,NULL,4,57),(83,'Varios',2,0,1,NULL,4,58),(84,'Colocación de aberturas',3,0,1,NULL,4,59),(85,'Terminación de mochetas',3,0,1,NULL,4,60),(86,'Mampostería de placas de yeso',2,0,1,NULL,4,61),(87,'Aislaciones humídicas y térmicas',2,0,1,NULL,4,62),(88,'Aislación humídica con emulsión asfáltica',3,0,1,NULL,4,63),(89,'Aislación humídica de arena y port. con hidrófugo',3,0,1,NULL,4,64),(90,'Aislación térmica con poliuretano proyectado',3,0,1,NULL,4,65),(91,'Revoques',1,0,1,NULL,4,66),(92,'Revoque de cielorraso',2,0,1,NULL,4,67),(93,'Revoque interior',2,0,1,NULL,4,68),(94,'Revoque exterior con hidrófugo',2,0,1,NULL,4,69),(95,'Revoque de portland lustrado',2,0,1,NULL,4,70),(96,'Contrapisos',1,0,1,NULL,4,71),(97,'Contrapiso común',2,0,1,NULL,4,72),(98,'Contrapiso sobre losa',2,0,1,NULL,4,73),(99,'Alisado de arena y portland',2,0,1,NULL,4,74),(100,'Acabados',1,0,1,NULL,4,75),(101,'Aplicación de mortero monocapa incoloro interior',2,0,1,NULL,4,76),(102,'Azulejos lisos de color',2,0,1,NULL,4,77),(103,'Paquetas de cerámica',2,0,1,NULL,4,78),(104,'Aplicación de mortero monocapa incoloro exterior',2,0,1,NULL,4,79),(105,'Balai',2,0,1,NULL,4,80),(106,'Acabados sin muros exteriores',2,0,1,NULL,4,81),(107,'Cielo raso machiembrado',2,0,1,NULL,4,82),(108,'Cielo raso acústico 60x60',2,0,1,NULL,4,83),(109,'Pisos y zocalos',1,0,1,NULL,4,84),(110,'Pavimentos',2,0,1,NULL,4,85),(111,'Baldosas vereda 20x20',3,0,1,NULL,4,86),(112,'Baldosas monolíticas 30x30',3,0,1,NULL,4,87),(113,'Monolítico hecho en sitio',3,0,1,NULL,4,88),(114,'Alisado de arena y portland rodillado',3,0,1,NULL,4,89),(115,'Parque de eucaliptus engrampado',3,0,1,NULL,4,90),(116,'Alfombra moquette valor promedio',3,0,1,NULL,4,91),(117,'Baldosa cerámica',3,0,1,NULL,4,92),(118,'Baldosa de porcelanato pulido',3,0,1,NULL,4,93),(119,'Piso flotante de madera',3,0,1,NULL,4,94),(120,'Pavimentos especiales',2,0,1,NULL,4,95),(121,'Piso de micro cemento',3,0,1,NULL,4,96),(122,'Piso de hormigón con endurecedor',3,0,1,NULL,4,97),(123,'Proyecto',0,0,0,NULL,5,1),(124,'Movimiento de tierra y bases',1,0,1,NULL,5,2),(125,'Mantenimiento ordinario básico (60 m3/km)',2,0,1,NULL,5,3),(126,'Mantenimiento extraordinario 5m de ancho de calzada (10cm de espesor)',2,0,1,NULL,5,4),(127,'Mantenimiento extraordinario 5m de ancho de calzada (15cm de espesor)',2,0,1,NULL,5,5),(128,'Excavación no clasificada a depósito',2,0,1,NULL,5,6),(129,'Perfilado (ancho 5m)',2,0,1,NULL,5,7),(130,'Sustitución de terreno de fundación',2,0,1,NULL,5,8),(131,'Terraplén con material no clasificado',2,0,1,NULL,5,9),(132,'Base granular CBR mayor 60',2,0,1,NULL,5,10),(133,'Base granular CBR mayor 40',2,0,1,NULL,5,11),(134,'Base granular CBR mayor 80',2,0,1,NULL,5,12),(135,'Escarficado, conformación y compactación de base',2,0,1,NULL,5,13),(136,'Limpieza de cunetas',2,0,1,NULL,5,14),(137,'Mantenimiento de faja',2,0,1,NULL,5,15),(138,'Pavimentación',1,0,1,NULL,5,16),(139,'Ejecución de pavimentación con mezcla en frío',2,0,1,NULL,5,17),(140,'Ejecución de tratamiento bituminoso de sellado',2,0,1,NULL,5,18),(141,'Ejecución riego imprimación',2,0,1,NULL,5,19),(142,'Ejecución tratamiento bituminoso simple o tipo \"A\"',2,0,1,NULL,5,20),(143,'Ejecución tratamiento bituminoso tipo \"B\"',2,0,1,NULL,5,21),(144,'Ejecución tratamiento bituminoso de sellado',2,0,1,NULL,5,22),(145,'Hormigón para pavimento',2,0,1,NULL,5,23),(146,'Mezcla asfáltica para carpeta de rodadura',2,0,1,NULL,5,24),(147,'Suministro cemento pórtland',2,0,1,NULL,5,25),(148,'Suministro cemento aditivos estabilizadores',2,0,1,NULL,5,26),(149,'Suministro, transporte y elaboración de cemento asfáltico',2,0,1,NULL,5,27),(150,'Suministro, transporte y elaboración de diluidos asfálticos',2,0,1,NULL,5,28),(151,'Suministro, transporte y elaboración de emulsiones asfálticas',2,0,1,NULL,5,29),(152,'Suministro, transporte y elaboración de agregados pétreos gruesos y medianos',2,0,1,NULL,5,30),(153,'Suministro y transporte de agregados pétreos finos',2,0,1,NULL,5,31),(154,'Obras de arte',1,0,1,NULL,5,32),(155,'Entradas particulares de 50 cm de diámetro',2,0,1,NULL,5,33),(156,'Entradas particulares de 60 cm de diámetro',2,0,1,NULL,5,34),(157,'Alcantarilla de 1 boca Tipo Z de 120 cm',2,0,1,NULL,5,35),(158,'Alcantarilla de 1 boca Tipo Z de 50 cm',2,0,1,NULL,5,36),(159,'Alcantarilla de 1 boca Tipo Z de 60 cm',2,0,1,NULL,5,37),(160,'Alcantarilla de 1 boca Tipo Z de 80 cm',2,0,1,NULL,5,38),(161,'Alcantarilla de 2 bocas Tipo Z de 100 cm',2,0,1,NULL,5,39),(162,'Alcantarilla de 2 bocas Tipo Z de 120 cm',2,0,1,NULL,5,40),(163,'Alcantarilla de 2 bocas Tipo Z de 50 cm',2,0,1,NULL,5,41),(164,'Alcantarilla de 2 bocas Tipo Z de 60 cm',2,0,1,NULL,5,42),(165,'Alcantarilla de 2 bocas Tipo Z de 80 cm',2,0,1,NULL,5,43),(166,'Alcantarilla de 3 bocas Tipo Z de 100 cm',2,0,1,NULL,5,44),(167,'Alcantarilla de 3 bocas Tipo Z de 120 cm',2,0,1,NULL,5,45),(168,'Alcantarilla de 3 bocas Tipo Z de 50 cm',2,0,1,NULL,5,46),(169,'Alcantarilla de 3 bocas Tipo Z de 60 cm',2,0,1,NULL,5,47),(170,'Alcantarilla de 3 bocas Tipo Z de 80 cm',2,0,1,NULL,5,48),(171,'Caños colocados diámetro 50 cm',2,0,1,NULL,5,49),(172,'Caños colocados diámetro 60 cm',2,0,1,NULL,5,50),(173,'Caños colocados diámetro 80 cm',2,0,1,NULL,5,51),(174,'Caños colocados diámetro 100 cm',2,0,1,NULL,5,52),(175,'Caños de plástico',2,0,1,NULL,5,53),(176,'Hormigón armado clase VII para alcantarillas',2,0,1,NULL,5,54),(177,'Hormigón armado para losas de badén',2,0,1,NULL,5,55),(178,'Hormigón sin armar',2,0,1,NULL,5,56),(179,'Hormigón armado para revestimiento de talud',2,0,1,NULL,5,57),(180,'Hormigón armado para cabezales y alas',2,0,1,NULL,5,58),(181,'Hormigón ciclópeo',2,0,1,NULL,5,59),(182,'Piezas prefabricadas de hormigón',2,0,1,NULL,5,60),(183,'Tosca cementada (100 kg/m3 compactado)',2,0,1,NULL,5,61),(184,'Tosca compactada',2,0,1,NULL,5,62),(185,'Seguridad vial',1,0,1,NULL,5,63),(186,'Demarcación horizontal',2,0,1,NULL,5,64),(187,'Señales reflectorizantes',2,0,1,NULL,5,65),(188,'Suministro y colocación de tachas reflectivas',2,0,1,NULL,5,66),(189,'Suministro y colocación de barandas metálicas',2,0,1,NULL,5,67),(190,'Suministro y colocación de flex beam',2,0,1,NULL,5,68),(191,'Varios',1,0,1,NULL,5,69),(192,'Desvío provisorio sumergible',2,0,1,NULL,5,70),(193,'Proyecto ejecutivo',2,0,1,NULL,5,71),(194,'Movilización',2,0,1,NULL,5,72),(195,'Transporte de material a granel',2,0,1,NULL,5,73),(196,'Controles de calidad externos',2,0,1,NULL,5,74),(197,'Zócalos',2,0,1,NULL,4,98),(198,'Zócalos de monolítico',3,0,1,NULL,4,99),(199,'Zócalos de madera',3,0,1,NULL,4,100),(200,'Zócalos sanitarios de monolítico',3,0,1,NULL,4,101),(201,'Varios',2,0,1,NULL,4,102),(202,'Colocación de escalones o umbrales',3,0,1,NULL,4,103),(203,'Azoteas y sobretechos',1,0,1,NULL,4,104),(204,'Contrapiso y alisado de arena y portland',2,0,1,NULL,4,105),(205,'Capa impermeabilizante',2,0,1,NULL,4,106),(206,'Membrana asfáltica 4 mm de aluminio',3,0,1,NULL,4,107),(207,'Aislante poliuretano proyectado',3,0,1,NULL,4,108),(208,'Colocación de film polietileno',3,0,1,NULL,4,109),(209,'Superficies de protección',2,0,1,NULL,4,110),(210,'Tejuelas de cerámica',3,0,1,NULL,4,111),(211,'Teja plana',3,0,1,NULL,4,112),(212,'Sobretecho de chapa sobre correas 2x2',2,0,1,NULL,4,113),(213,'Acondicionamiento exterior',1,0,1,NULL,4,114),(214,'Pavimentos exteriores',2,0,1,NULL,4,115),(215,'Césped en tepes',3,0,1,NULL,4,116),(216,'Balasto compactado',3,0,1,NULL,4,117),(217,'Piso en green block',3,0,1,NULL,4,118),(218,'Baldosa atérmica para vereda perimetral piscina',3,0,1,NULL,4,119),(219,'Piso tipo rectangular adoquin espesor 8cm',3,0,1,NULL,4,120),(220,'Piso hormigón estampado',3,0,1,NULL,4,121),(221,'Elementos de acondicionamiento exterior',2,0,1,NULL,4,122),(222,'Cordones de hormigón',3,0,1,NULL,4,123),(223,'Bancos de hormigón',3,0,1,NULL,4,124),(224,'Cerco con postes con tejido galvanizado',3,0,1,NULL,4,125),(225,'Suministro de especies vegetales',3,0,1,NULL,4,126),(226,'Cubiertas inclinadas',1,0,1,NULL,4,127),(227,'Cubiertas con estructura',2,0,1,NULL,4,128),(228,'Techo de chapa estructura hierro redondo',3,0,1,NULL,4,129),(229,'Cubiertas sin estructura',2,0,1,NULL,4,130),(230,'Cubierta teja colonial',3,0,1,NULL,4,131),(231,'Cubierta de chapa galvanizada',3,0,1,NULL,4,132),(232,'Acondicionamiento eléctrico',1,0,1,NULL,4,133),(233,'Instalación eléctrica',2,0,1,NULL,4,134),(234,'Canalización',3,0,1,NULL,4,135),(235,'Enhebrado',3,0,1,NULL,4,136),(236,'Puestas (luces y tomas)',3,0,1,NULL,4,137),(237,'Tableros',3,0,1,NULL,4,138),(238,'Datos, telefonía, CCTV, alarma',3,0,1,NULL,4,139),(239,'Artefactos de iluminación',3,0,1,NULL,4,140),(240,'Cámaras',3,0,1,NULL,4,141),(241,'Luminarias exteriores',3,0,1,NULL,4,142),(242,'Acondicionamiento sanitario',1,0,1,NULL,4,143),(243,'Instalación sanitaria',2,0,1,NULL,4,144),(244,'Sanitaria: abastecimiento',3,0,1,NULL,4,145),(245,'Sanitaria: desagues primaria',3,0,1,NULL,4,146),(246,'Sanitaria: desagues secundaria',3,0,1,NULL,4,147),(247,'Ensayos y pruebas hidráulicas',3,0,1,NULL,4,148),(248,'Cámaras de inspección',3,0,1,NULL,4,149),(249,'Depósito impermeable',3,0,1,NULL,4,150),(250,'Artefactos y grifería',3,0,1,NULL,4,151),(251,'Aparatos y accesorios de baños',2,0,1,NULL,4,152),(252,'Aparatos de loza c/IP con mochila',3,0,1,NULL,4,153),(253,'Juego de grifería estándar: lavatorio, bidé y ducha',3,0,1,NULL,4,154),(254,'Accesorios para baño con metal cromado',3,0,1,NULL,4,155),(255,'Mampara de baño: vidrio templado con colocación',3,0,1,NULL,4,156),(256,'Barras fijas y móviles baño discapacitados en acero inoxidable',3,0,1,NULL,4,157),(257,'Bacha en acero inoxidable con válvula',3,0,1,NULL,4,158),(258,'Pileta y grifería de cocina',2,0,1,NULL,4,159),(259,'Pileta doble de acero prof. 15cm',3,0,1,NULL,4,160),(260,'Mezcladora estandar para cocina (de pared)',3,0,1,NULL,4,161),(261,'Aberturas y equipamiento',1,0,1,NULL,4,162),(262,'Aberturas en perfil de hierro (simple contacto)',2,0,1,NULL,4,163),(263,'Aberturas en madera',2,0,1,NULL,4,164),(264,'Ventana batiente',3,0,1,NULL,4,165),(265,'Puerta ventana',3,0,1,NULL,4,166),(266,'Puerta interior',3,0,1,NULL,4,167),(267,'Puerta exterior',3,0,1,NULL,4,168),(268,'Aberturas en aluminio con accesorios y cristales comunes',2,0,1,NULL,4,169),(269,'Ventana corrediza',3,0,1,NULL,4,170),(270,'Puerta ventana corrediza',3,0,1,NULL,4,171),(271,'Serie 30',2,0,1,NULL,4,172),(272,'Ventana batiente',3,0,1,NULL,4,173),(273,'Puerta batiente',3,0,1,NULL,4,174),(274,'Ventana proyectante',3,0,1,NULL,4,175),(275,'Serie 50',2,0,1,NULL,4,176),(276,'Puerta batiente',3,0,1,NULL,4,177),(277,'Claraboyas de aluminio',2,0,1,NULL,4,178),(278,'Sistema de claraboyas con policarbonato',3,0,1,NULL,4,179),(279,'Sistemas curtain wall (aluminio y D.V.H.)',2,0,1,NULL,4,180),(280,'Sistema vidriado estructural con D.V.H. (6+12+6)',3,0,1,NULL,4,181),(281,'Cortina de enrollar',2,0,1,NULL,4,182),(282,'Cortina de enrollar completa PVC c/colocación',3,0,1,NULL,4,183),(283,'Equipamiento cocinas y baños',2,0,1,NULL,4,184),(284,'Mueble bajo',3,0,1,NULL,4,185),(285,'Cajoneras',3,0,1,NULL,4,186),(286,'Mueble alto',3,0,1,NULL,4,187),(287,'Pinturas',1,0,1,NULL,4,188),(288,'Aplicación de pinturas en mampostería fondo',2,0,1,NULL,4,189),(289,'Aplicación de fijador',3,0,1,NULL,4,190),(290,'Aplicación de imprimación',3,0,1,NULL,4,191),(291,'Aplicación de sellador pigmentado (semi-cubriente)',3,0,1,NULL,4,192),(292,'Enduído sobre revoque fino',3,0,1,NULL,4,193),(293,'Aplicación de pintura al agua',3,0,1,NULL,4,194),(294,'Cielo rasos antihongo',3,0,1,NULL,4,195),(295,'Aplicación de pintura impermeabilizante',3,0,1,NULL,4,196),(296,'Aplicación de pintura para exteriores',3,0,1,NULL,4,197),(297,'Aplicación de fondos y acabados sobre metales fondo',2,0,1,NULL,4,198),(298,'Aplicación de fondo antióxido sintetico',3,0,1,NULL,4,199),(299,'Terminación',2,0,1,NULL,4,200),(300,'Aplicación de esmalte sintético',3,0,1,NULL,4,201),(301,'Aplicación de fondos y acabados para madera',2,0,1,NULL,4,202),(302,'Aplicación de fondo blanco',3,0,1,NULL,4,203),(303,'Aplicación de esmalte sintetico',3,0,1,NULL,4,204),(304,'Aplicación de tinta de lustre',3,0,1,NULL,4,205),(305,'Aplicación de barniz',3,0,1,NULL,4,206),(306,'Impermeabilizantes',2,0,1,NULL,4,207),(307,'Membrana liq. impermeabilizante',3,0,1,NULL,4,208),(308,'Aplicación de impermeabilizante para piedras y ladrillos',3,0,1,NULL,4,209),(309,'Aplicación de pintura para piscinas',3,0,1,NULL,4,210),(310,'Vidrios',2,0,1,NULL,4,211),(311,'Espejos',2,0,1,NULL,4,212),(312,'Ascensores',1,0,1,NULL,4,213),(313,'Ascensor de 11 paradas',2,0,1,NULL,4,214),(314,'Proyecto',1,0,0,NULL,6,1),(315,'Proceso Licitatorio',2,0,0,NULL,6,2),(316,'Relevamiento',3,30,60,NULL,6,3),(317,'Elaboración de TDR y Pliego ',3,30,30,316,6,4),(318,'Publicación del Pliego ',3,5,20,317,6,5),(319,'Evaluación Técnica de la Oferta ',3,30,35,318,6,6),(320,'Adjudicación de la Oferta',3,5,25,319,6,7),(321,'Ejecución del Proyecto',2,100,285,320,6,8),(322,'Proyecto',1,0,0,NULL,7,1),(323,'Proceso Compra Directa',2,0,0,NULL,7,2),(324,'Elaboración TDR',3,30,15,NULL,7,3),(325,'Justificación Técnica',3,20,3,324,7,4),(326,'Solicitud de cotización',3,5,5,325,7,5),(327,'Adjudicación de la Oferta',3,5,25,326,7,6),(328,'Ejecución del Proyecto',2,100,285,327,7,7),(329,'Proyecto',1,0,0,NULL,8,1),(330,'Proceso Compra Directa',2,0,0,NULL,8,2),(331,'Relevamiento',3,30,60,NULL,8,3),(332,'Elaboración de TDR',3,30,4,331,8,4),(333,'Publicación de la Compra ',3,5,4,332,8,5),(334,'Evaluación Técnica de la Oferta ',3,15,2,333,8,6),(335,'Adjudicación de la Oferta',3,5,3,334,8,7),(336,'Ejecución del Proyecto',2,100,285,335,8,8),(337,'Proyecto',1,0,0,NULL,9,1),(338,'Proceso Compra Directa',2,0,0,NULL,9,2),(339,'Relevamiento',3,30,60,NULL,9,3),(340,'Elaboración de TDR',3,30,7,339,9,4),(341,'Publicación de la Compra ',3,5,7,340,9,5),(342,'Evaluación Técnica de la Oferta ',3,15,3,341,9,6),(343,'Adjudicación de la Oferta',3,5,4,342,9,7),(344,'Ejecución del Proyecto',2,100,285,343,9,8),(345,'Proyecto',1,0,0,NULL,10,1),(346,'Proceso Licitatorio',2,0,0,NULL,10,2),(347,'Relevamiento',3,30,60,NULL,10,3),(348,'Elaboración de TDR y Pliego ',3,30,30,347,10,4),(349,'Publicación del Pliego ',3,15,19,348,10,5),(350,'Evaluación Jurídica de la Oferta ',3,15,14,349,10,6),(351,'Evaluación Técnica de la Oferta ',3,15,20,349,10,7),(352,'Evaluación Económica de la Oferta ',3,15,9,349,10,8),(353,'Informe comisión asesora de adjudicaciones',3,15,12,352,10,9),(354,'Adjudicación de la Oferta',3,30,72,353,10,10),(355,'Ejecución del Proyecto',2,100,285,354,10,11),(356,'Proyecto',1,0,0,NULL,11,1),(357,'Proceso Licitatorio',2,0,0,NULL,11,2),(358,'Relevamiento',3,30,60,NULL,11,3),(359,'Elaboración de TDR y Pliego ',3,30,27,358,11,4),(360,'Publicación del Pliego ',3,15,19,359,11,5),(361,'Evaluación Jurídica de la Oferta ',3,15,4,360,11,6),(362,'Evaluación Técnica de la Oferta ',3,15,20,360,11,7),(363,'Evaluación Económica de la Oferta ',3,15,9,360,11,8),(364,'Informe comisión asesora de adjudicaciones',3,15,12,363,11,9),(365,'Adjudicación de la Oferta',3,30,49,364,11,10),(366,'Ejecución del Proyecto',2,100,285,365,11,11),(367,'Proyecto',1,0,0,NULL,12,1),(368,'Proceso Licitatorio',2,0,0,NULL,12,2),(369,'Relevamiento',3,30,60,NULL,12,3),(370,'Elaboración de TDR y Pliego ',3,30,30,369,12,4),(371,'Publicación del Pliego ',3,5,20,370,12,5),(372,'Evaluación Técnica de la Oferta ',3,30,35,371,12,6),(373,'Adjudicación de la Oferta',3,5,25,372,12,7),(374,'Ejecución del Proyecto',2,100,285,373,12,8),(375,'Proyecto',1,0,0,NULL,13,1),(376,'Proceso Compra Directa',2,0,0,NULL,13,2),(377,'Elaboración TDR',3,30,15,NULL,13,3),(378,'Justificación Técnica',3,20,3,377,13,4),(379,'Solicitud de cotización',3,5,5,378,13,5),(380,'Adjudicación de la Oferta',3,5,25,379,13,6),(381,'Ejecución del Proyecto',2,100,285,380,13,7),(382,'Proyecto',1,0,0,NULL,14,1),(383,'Proceso Compra Directa',2,0,0,NULL,14,2),(384,'Relevamiento',3,30,60,NULL,14,3),(385,'Elaboración de TDR',3,30,4,384,14,4),(386,'Publicación de la Compra ',3,5,4,385,14,5),(387,'Evaluación Técnica de la Oferta',3,15,2,386,14,6),(388,'Adjudicación de la Oferta',3,5,3,387,14,7),(389,'Ejecución del Proyecto',2,100,285,388,14,8),(390,'Proyecto',1,0,0,NULL,15,1),(391,'Proceso Compra Directa',2,0,0,NULL,15,2),(392,'Relevamiento',3,30,60,NULL,15,3),(393,'Elaboración de TDR',3,30,7,392,15,4),(394,'Publicación de la Compra ',3,5,7,393,15,5),(395,'Evaluación Técnica de la Oferta ',3,15,3,394,15,6),(396,'Adjudicación de la Oferta',3,5,4,395,15,7),(397,'Ejecución del Proyecto',2,100,285,396,15,8),(398,'Proyecto',1,0,0,NULL,16,1),(399,'Proceso Licitatorio',2,0,0,NULL,16,2),(400,'Relevamiento',3,30,60,NULL,16,3),(401,'Elaboración de TDR y Pliego ',3,30,30,400,16,4),(402,'Publicación del Pliego ',3,15,19,401,16,5),(403,'Evaluación Jurídica de la Oferta ',3,15,4,402,16,6),(404,'Evaluación Técnica de la Oferta ',3,15,20,402,16,7),(405,'Evaluación Económica de la Oferta ',3,15,9,402,16,8),(406,'Informe comisión asesora de adjudicaciones',3,15,12,405,16,9),(407,'Adjudicación de la Oferta',3,30,72,406,16,10),(408,'Ejecución del Proyecto',2,100,285,407,16,11),(409,'Proyecto',1,0,0,NULL,17,1),(410,'Proceso Licitatorio',2,0,0,NULL,17,2),(411,'Relevamiento',3,30,60,NULL,17,3),(412,'Elaboración de TDR y Pliego ',3,30,27,411,17,4),(413,'Publicación del Pliego ',3,15,19,412,17,5),(414,'Evaluación Jurídica de la Oferta ',3,15,4,413,17,6),(415,'Evaluación Técnica de la Oferta ',3,15,20,413,17,7),(416,'Evaluación Económica de la Oferta ',3,15,9,413,17,8),(417,'Informe comisión asesora de adjudicaciones',3,15,12,416,17,9),(418,'Adjudicación de la Oferta',3,30,49,417,17,10),(419,'Ejecución del Proyecto',2,100,285,418,17,11),(420,'Proyecto',1,0,0,NULL,18,1),(421,'Proceso Licitatorio',2,0,0,NULL,18,2),(422,'Relevamiento',3,30,60,NULL,18,3),(423,'Elaboración de TDR y Pliego ',3,30,25,422,18,4),(424,'Publicación del Pliego ',3,15,42,423,18,5),(425,'Evaluación Jurídica de la Oferta ',3,15,4,424,18,6),(426,'Evaluación Técnica de la Oferta ',3,15,20,424,18,7),(427,'Evaluación Económica de la Oferta ',3,15,9,424,18,8),(428,'Adjudicación de la Oferta',3,30,51,427,18,9),(429,'Ejecución del Proyecto',2,100,285,428,18,10),(430,'Proyecto',1,0,0,NULL,19,1),(431,'Proceso Licitatorio',2,0,0,NULL,19,2),(432,'Relevamiento',3,30,60,NULL,19,3),(433,'Elaboración de TDR y Pliego ',3,30,25,432,19,4),(434,'Publicación del Pliego ',3,15,42,433,19,5),(435,'Evaluación Jurídica de la Oferta ',3,15,4,434,19,6),(436,'Evaluación Técnica de la Oferta ',3,15,20,434,19,7),(437,'Evaluación Económica de la Oferta ',3,15,9,434,19,8),(438,'Adjudicación de la Oferta',3,30,85,437,19,9),(439,'Ejecución del Proyecto',2,100,285,438,19,10),(440,'Proyecto',1,0,0,NULL,20,1),(441,'Proceso Licitatorio',2,0,0,NULL,20,2),(442,'Relevamiento',3,30,60,NULL,20,3),(443,'Elaboración de TDR y Pliego ',3,30,25,442,20,4),(444,'Publicación del Pliego ',3,15,10,443,20,5),(445,'Evaluación Jurídica de la Oferta ',3,15,4,444,20,6),(446,'Evaluación Técnica de la Oferta ',3,15,20,444,20,7),(447,'Evaluación Económica de la Oferta ',3,15,9,444,20,8),(448,'Adjudicación de la Oferta',3,30,41,447,20,9),(449,'Ejecución del Proyecto',2,100,285,448,20,10),(450,'Proyecto',1,0,0,NULL,21,1),(451,'Proceso Compra Directa',2,0,0,NULL,21,2),(452,'Elaboración TDR',3,30,15,NULL,21,3),(453,'Justificación Técnica',3,20,3,452,21,4),(454,'Solicitud de No Objeción',3,5,5,453,21,5),(455,'Adjudicación de la Oferta',3,5,5,454,21,6),(456,'Ejecución del Proyecto',2,100,285,455,21,7),(457,'Proyecto',1,0,0,NULL,22,1),(458,'Proceso Comparación de Precios',2,0,0,NULL,22,2),(459,'Relevamiento',3,30,60,NULL,22,3),(460,'Elaboración de TDR',3,30,7,459,22,4),(461,'Publicación de la Compra ',3,5,7,460,22,5),(462,'Evaluación Técnica de la Oferta ',3,15,3,461,22,6),(463,'Adjudicación de la Oferta',3,5,30,462,22,7),(464,'Ejecución del Proyecto',2,100,285,463,22,8),(465,'Proyecto',1,0,0,NULL,23,1),(466,'Proceso Compra Directa',2,0,0,NULL,23,2),(467,'Elaboración TDR',3,30,15,NULL,23,3),(468,'Justificación Técnica',3,20,3,467,23,4),(469,'Solicitud de No Objeción',3,5,5,468,23,5),(470,'Adjudicación de la Oferta',3,5,25,469,23,6),(471,'Ejecución del Proyecto',2,100,285,470,23,7),(472,'Proyecto',1,0,0,NULL,24,1),(473,'Proceso Comparación de Precios',2,0,0,NULL,24,2),(474,'Relevamiento',3,30,60,NULL,24,3),(475,'Elaboración de TDR',3,30,7,474,24,4),(476,'Publicación de la Compra ',3,5,7,475,24,5),(477,'Evaluación Técnica de la Oferta ',3,15,3,476,24,6),(478,'Adjudicación de la Oferta',3,5,5,477,24,7),(479,'Ejecución del Proyecto',2,100,285,478,24,8),(480,'Proyecto',1,0,0,NULL,25,1),(481,'Proceso Licitatorio',2,0,0,NULL,25,2),(482,'Relevamiento',3,30,60,NULL,25,3),(483,'Elaboración de TDR y Pliego ',3,30,25,482,25,4),(484,'Publicación del Pliego ',3,15,42,483,25,5),(485,'Evaluación Jurídica de la Oferta ',3,15,4,484,25,6),(486,'Evaluación Técnica de la Oferta ',3,15,20,484,25,7),(487,'Evaluación Económica de la Oferta ',3,15,9,484,25,8),(488,'Adjudicación de la Oferta',3,30,51,487,25,9),(489,'Ejecución del Proyecto',2,100,285,488,25,10),(490,'Proyecto',1,0,0,NULL,26,1),(491,'Proceso Licitatorio',2,0,0,NULL,26,2),(492,'Relevamiento',3,30,60,NULL,26,3),(493,'Elaboración de TDR y Pliego ',3,30,25,492,26,4),(494,'Publicación del Pliego ',3,15,42,493,26,5),(495,'Evaluación Jurídica de la Oferta ',3,15,4,494,26,6),(496,'Evaluación Técnica de la Oferta ',3,15,20,494,26,7),(497,'Evaluación Económica de la Oferta ',3,15,9,494,26,8),(498,'Adjudicación de la Oferta',3,30,85,497,26,9),(499,'Ejecución del Proyecto',2,100,285,498,26,10),(500,'Proyecto',1,0,0,NULL,27,1),(501,'Proceso Licitatorio',2,0,0,NULL,27,2),(502,'Relevamiento',3,30,60,NULL,27,3),(503,'Elaboración de TDR y Pliego ',3,30,25,502,27,4),(504,'Publicación del Pliego ',3,15,10,503,27,5),(505,'Evaluación Jurídica de la Oferta ',3,15,4,504,27,6),(506,'Evaluación Técnica de la Oferta',3,15,20,504,27,7),(507,'Evaluación Económica de la Oferta ',3,15,9,504,27,8),(508,'Adjudicación de la Oferta',3,30,41,507,27,9),(509,'Ejecución del Proyecto',2,100,285,508,27,10),(510,'Proyecto',1,0,0,NULL,28,1),(511,'Proceso Compra Directa',2,0,0,NULL,28,2),(512,'Elaboración TDR',3,30,15,NULL,28,3),(513,'Justificación Técnica',3,20,3,512,28,4),(514,'Solicitud de No Objeción',3,5,5,513,28,5),(515,'Adjudicación de la Oferta',3,5,25,514,28,6),(516,'Ejecución del Proyecto',2,100,285,515,28,7),(517,'Proyecto',1,0,0,NULL,29,1),(518,'Proceso Comparación de Precios',2,0,0,NULL,29,2),(519,'Relevamiento',3,30,60,NULL,29,3),(520,'Elaboración de TDR',3,30,7,519,29,4),(521,'Publicación de la Compra ',3,5,7,520,29,5),(522,'Evaluación Técnica de la Oferta ',3,15,3,521,29,6),(523,'Adjudicación de la Oferta',3,5,30,522,29,7),(524,'Ejecución del Proyecto',2,100,285,523,29,8),(525,'Proyecto',1,0,0,NULL,30,1),(526,'Proceso Comparación de Precios',2,0,0,NULL,30,2),(527,'Relevamiento',3,30,60,NULL,30,3),(528,'Elaboración de TDR',3,30,7,527,30,4),(529,'Publicación de la Compra ',3,5,7,528,30,5),(530,'Evaluación Técnica de la Oferta ',3,15,3,529,30,6),(531,'Adjudicación de la Oferta',3,5,5,530,30,7),(532,'Ejecución del Proyecto',2,100,285,531,30,8),(533,'Proyecto',1,0,0,NULL,31,1),(534,'Proceso Compra Directa',2,0,0,NULL,31,2),(535,'Elaboración TDR',3,30,15,NULL,31,3),(536,'Justificación Técnica',3,20,3,535,31,4),(537,'Solicitud de No Objeción',3,5,5,536,31,5),(538,'Adjudicación de la Oferta',3,5,5,537,31,6),(539,'Ejecución del Proyecto',2,100,285,538,31,7),(540,'Relacionamiento con Organismos',0,0,0,NULL,32,1),(541,'1ero',1,0,0,NULL,32,2);
/*!40000 ALTER TABLE `plantilla_entregables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'siges_agesic'
--

--
-- Dumping routines for database 'siges_agesic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;



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
-- Dumping data for table `departamentos`
--

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` VALUES (1,'MONTEVIDEO',NULL,NULL,NULL),(2,'ARTIGAS',NULL,NULL,NULL),(3,'CANELONES',NULL,NULL,NULL),(4,'CERRO LARGO',NULL,NULL,NULL),(5,'COLONIA',NULL,NULL,NULL),(6,'DURAZNO',NULL,NULL,NULL),(7,'FLORES',NULL,NULL,NULL),(8,'FLORIDA',NULL,NULL,NULL),(9,'LAVALLEJA',NULL,NULL,NULL),(10,'MALDONADO',NULL,NULL,NULL),(11,'PAYSANDU',NULL,NULL,NULL),(12,'RIO NEGRO',NULL,NULL,NULL),(13,'RIVERA',NULL,NULL,NULL),(14,'ROCHA',NULL,NULL,NULL),(15,'SALTO',NULL,NULL,NULL),(16,'SAN JOSE',NULL,NULL,NULL),(17,'SORIANO',NULL,NULL,NULL),(18,'TACUAREMBO',NULL,NULL,NULL),(19,'TREINTA Y TRES',NULL,NULL,NULL);
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'siges_agesic'
--

--
-- Dumping routines for database 'siges_agesic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


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
-- Dumping data for table `estados`
--

LOCK TABLES `estados` WRITE;
/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` VALUES (0,'NO_EXIGIDO','No Exigido','',NULL,0),(1,'PENDIENTE','Pendiente','',1,0),(2,'INICIO','Inicio','',2,0),(3,'PLANIFICACION','Planificación','',3,0),(4,'EJECUCION','Ejecución','',4,0),(5,'FINALIZADO','Finalizado','',5,0),(11,'PENDIENTE_PMOT','Pendiente PMO T.','',NULL,0),(12,'PENDIENTE_PMOF','Pendiente PMO F.','',NULL,0),(41,'SOLICITUD_FINALIZADO_PMOF','Solicitud Finalizado PMO F.','',NULL,0),(42,'SOLICITUD_FINALIZADO_PMOT','Solicitud Finalizado PMO T.','',NULL,0),(61,'SOLICITUD_CANCELAR_PMOT','Solicitud Cancelar PMO T.','',NULL,0);
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'siges_agesic'
--

--
-- Dumping routines for database 'siges_agesic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


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
-- Dumping data for table `estados_publicacion`
--

LOCK TABLES `estados_publicacion` WRITE;
/*!40000 ALTER TABLE `estados_publicacion` DISABLE KEYS */;
INSERT INTO `estados_publicacion` VALUES (1,'NO_ES_PARA_PUBLICAR','No es para publicar'),(2,'PENDIENTE_CARGAR','Pendiente de cargar datos'),(3,'PENDIENTE_PUBLICAR','Pendiente de publicar'),(4,'PUBLICADO','Publicado');
/*!40000 ALTER TABLE `estados_publicacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'siges_agesic'
--

--
-- Dumping routines for database 'siges_agesic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


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
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'siges_agesic'
--

--
-- Dumping routines for database 'siges_agesic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


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
-- Dumping data for table `moneda`
--

LOCK TABLES `moneda` WRITE;
/*!40000 ALTER TABLE `moneda` DISABLE KEYS */;
INSERT INTO `moneda` VALUES (1,'Pesos','$',NULL,0),(2,'Dólares','U$S',NULL,0),(3,'Euros','€','',0),(4,'Unidad Reajustable','UR','',0),(5,'Unidades Indexadas','UI','UI',0);
/*!40000 ALTER TABLE `moneda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'siges_agesic'
--

--
-- Dumping routines for database 'siges_agesic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

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
-- Dumping data for table `pge_configuraciones`
--

LOCK TABLES `pge_configuraciones` WRITE;
/*!40000 ALTER TABLE `pge_configuraciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `pge_configuraciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'siges_agesic'
--

--
-- Dumping routines for database 'siges_agesic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;



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
-- Dumping data for table `ss_configuraciones`
--

LOCK TABLES `ss_configuraciones` WRITE;
/*!40000 ALTER TABLE `ss_configuraciones` DISABLE KEYS */;
INSERT INTO `ss_configuraciones` VALUES (4,1,'RIESGO_INDICE_LIMITE_AMARILLO',NULL,'0.8',NULL,NULL,NULL,'2014-03-12 15:59:03',NULL,9),(5,1,'RIESGO_INDICE_LIMITE_ROJO',NULL,'4.8',NULL,NULL,NULL,'2016-05-27 11:22:30.634',NULL,11),(6,1,'RIESGO_TIEMPO_LIMITE_AMARILLO',NULL,'10',NULL,NULL,NULL,'2014-05-12 18:05:27',NULL,1),(7,1,'RIESGO_TIEMPO_LIMITE_ROJO',NULL,'20',NULL,NULL,NULL,'2014-05-12 18:05:27',NULL,3),(8,1,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO',NULL,'30',NULL,NULL,NULL,'2014-03-11 16:28:06',NULL,0),(9,1,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO',NULL,'70',NULL,NULL,NULL,'2014-03-11 16:28:07',NULL,0),(10,1,'TAMANIO_MAX_ARCHIVO_DOCUMENTO',NULL,'10485760',NULL,NULL,NULL,'2014-03-20 14:14:11',NULL,0),(11,1,'COSTO_ACTUAL_LIMITE_AMARILLO',NULL,'15',NULL,NULL,NULL,'2014-03-20 14:14:11',NULL,0),(12,1,'COSTO_ACTUAL_LIMITE_ROJO',NULL,'30',NULL,NULL,NULL,'2014-03-20 14:14:11',NULL,0),(13,1,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño maximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2014-06-11',NULL,0),(14,1,'MAIL_FROM','Dirección desde donde se envían los mails','siges@agesic.gub.uy',NULL,0,NULL,'2014-10-01 16:35:26',NULL,1),(15,1,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2014-06-25',NULL,0),(17,1,'CON_CORREO','Si se envía correo o no','false',NULL,0,NULL,'2014-09-24 16:24:07',NULL,2),(20,1,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2014-07-10 15:17:14',NULL,NULL),(21,1,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2014-08-14',NULL,0),(22,1,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2014-08-14',NULL,0),(23,2,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(24,2,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(25,2,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(26,2,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(27,2,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(28,2,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(29,2,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(30,2,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(31,2,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,0,NULL,'2015-08-28 18:19:59.57',NULL,1),(32,2,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(33,2,'MAIL_FROM','Dirección desde donde se envían los mails','siges@agesic.gub.uy',NULL,0,NULL,'2014-10-01 16:37:59',NULL,2),(34,2,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(35,2,'CON_CORREO','Si se envía correo o no','false',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(36,2,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(37,2,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(38,2,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(39,3,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(40,3,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(41,3,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(42,3,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(43,3,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(44,3,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(45,3,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(46,3,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(47,3,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,0,NULL,'2015-08-28 18:20:13.99',NULL,1),(48,3,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(49,3,'MAIL_FROM','Dirección desde donde se envían los mails','siges@agesic.gub.uy',NULL,0,NULL,'2014-10-01 16:38:12',NULL,1),(50,3,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(51,3,'CON_CORREO','Si se envía correo o no','false',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(52,3,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(53,3,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(54,3,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(55,4,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(56,4,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(57,4,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(58,4,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(59,4,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(60,4,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(61,4,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(62,4,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(63,4,'COSTO_ACTUAL_LIMITE_ROJO','','25',NULL,0,NULL,'2015-08-31 15:55:58.863',NULL,2),(64,4,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(65,4,'MAIL_FROM','Dirección desde donde se envían los mails','siges@agesic.gub.uy',NULL,0,NULL,'2014-10-01 16:38:24',NULL,1),(66,4,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(67,4,'CON_CORREO','Si se envía correo o no','false',NULL,0,NULL,'2016-07-12 15:04:07.75',NULL,1),(68,4,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(69,4,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(70,4,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2014-09-25 15:47:11',NULL,0),(71,1,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','true',NULL,NULL,NULL,'2014-10-10 16:19:33',NULL,0),(72,2,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','true',NULL,NULL,NULL,'2014-10-10 16:19:33',NULL,0),(73,3,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','true',NULL,NULL,NULL,'2014-10-10 16:19:33',NULL,0),(74,4,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,0,NULL,'2014-10-20 12:48:31',NULL,1),(75,1,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2015-02-04 16:46:59',NULL,0),(76,1,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2015-02-04 16:46:59',NULL,0),(77,1,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2015-02-04 16:46:59',NULL,0),(78,1,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2015-02-04 16:46:59',NULL,0),(79,2,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2015-02-04 16:46:59',NULL,0),(80,2,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2015-02-04 16:46:59',NULL,0),(81,2,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2015-02-04 16:46:59',NULL,0),(82,2,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2015-02-04 16:46:59',NULL,0),(83,3,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2015-02-04 16:46:59',NULL,0),(84,3,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2015-02-04 16:46:59',NULL,0),(85,3,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2015-02-04 16:46:59',NULL,0),(86,3,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2015-02-04 16:46:59',NULL,0),(87,4,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2015-02-04 16:46:59',NULL,0),(88,4,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2015-02-04 16:46:59',NULL,0),(89,4,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2015-02-04 16:46:59',NULL,0),(90,4,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2015-02-04 16:46:59',NULL,0),(91,5,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(92,5,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(93,5,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(94,5,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(95,5,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(96,5,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(97,5,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(98,5,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(99,5,'COSTO_ACTUAL_LIMITE_ROJO','','10',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(100,5,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(101,5,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(102,5,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(103,5,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(104,5,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(105,5,'MAIL_FROM','Dirección desde donde se envían los mails','siges@agesic.gub.uy',NULL,0,NULL,'2016-06-08 14:22:01.615',NULL,1),(106,5,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(107,5,'CON_CORREO','Si se envía correo o no','false',NULL,0,NULL,'2015-03-11 15:40:14',NULL,1),(108,5,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(109,5,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(110,5,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(111,5,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2015-03-04 12:06:15',NULL,0),(112,6,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(113,6,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(114,6,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(115,6,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(116,6,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(117,6,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(118,6,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(119,6,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(120,6,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,0,NULL,'2015-08-28 18:20:41.805',NULL,1),(121,6,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(122,6,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(123,6,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(124,6,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(125,6,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(126,6,'MAIL_FROM','Dirección desde donde se envían los mails','siges@agesic.gub.uy',NULL,0,NULL,'2015-07-14 17:28:14.208',NULL,1),(127,6,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(128,6,'CON_CORREO','Si se envía correo o no','false',NULL,0,NULL,'2015-03-16 09:18:25',NULL,1),(129,6,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(130,6,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(131,6,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(132,6,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2015-03-16 09:12:12',NULL,0),(133,1,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(134,1,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','true',NULL,0,NULL,'2015-06-18 11:07:28',NULL,2),(135,1,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(136,1,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(137,1,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(138,2,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(139,2,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(140,2,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(141,2,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(142,2,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(143,4,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(144,4,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','true',NULL,0,NULL,'2015-09-22 12:57:15.135',NULL,2),(145,4,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(146,4,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(147,4,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(148,3,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(149,3,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(150,3,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(151,3,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(152,3,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(153,6,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(154,6,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','true',NULL,0,NULL,'2016-04-28 11:53:20.206',NULL,2),(155,6,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(156,6,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(157,6,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(158,5,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(159,5,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(160,5,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(161,5,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(162,5,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2015-05-29 16:28:30',NULL,1),(163,7,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(164,7,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(165,7,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(166,7,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(167,7,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(168,7,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(169,7,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(170,7,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(171,7,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(172,7,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,0,NULL,'2015-08-28 18:20:57.653',NULL,2),(173,7,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(174,7,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(175,7,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(176,7,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(177,7,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(178,7,'MAIL_FROM','Dirección desde donde se envían los mails','siges@agesic.gub.uy',NULL,0,NULL,'2015-06-02 18:40:59',NULL,2),(179,7,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(180,7,'CON_CORREO','Si se envía correo o no','false',NULL,0,NULL,'2015-06-02 18:40:50',NULL,2),(181,7,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(182,7,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(183,7,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(184,7,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(185,7,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(186,7,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(187,7,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(188,7,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2015-06-02 18:34:58',NULL,1),(189,8,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2015-06-12 12:04:35',NULL,1),(190,8,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2015-06-12 12:04:35',NULL,1),(191,8,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(192,8,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(193,8,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(194,8,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(195,8,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(196,8,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(197,8,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(198,8,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,0,NULL,'2015-08-28 18:21:20.764',NULL,2),(199,8,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(200,8,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(201,8,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(202,8,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(203,8,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(204,8,'MAIL_FROM','Dirección desde donde se envían los mails','siges@agesic.gub.uy',NULL,0,NULL,'2015-07-14 17:26:33.82',NULL,2),(205,8,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(206,8,'CON_CORREO','Si se envía correo o no','false',NULL,0,NULL,'2015-07-14 17:26:10.319',NULL,2),(207,8,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(208,8,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(209,8,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(210,8,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2015-06-12 12:04:36',NULL,1),(211,8,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2015-06-12 12:04:37',NULL,1),(212,8,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2015-06-12 12:04:37',NULL,1),(213,8,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2015-06-12 12:04:37',NULL,1),(214,8,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2015-06-12 12:04:37',NULL,1),(215,1,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','1048576',NULL,0,NULL,'2015-07-07 15:32:47.823',NULL,2),(216,1,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2015-06-26 14:50:04',NULL,1),(217,1,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/srv/siges/jboss-as-7.1.1.Final/media_files/',NULL,0,NULL,'2015-06-26 15:10:55',NULL,2),(218,8,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','1048576',NULL,0,NULL,'2015-07-07 15:34:03.861',NULL,2),(219,8,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2015-06-26 14:50:04',NULL,1),(220,8,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/srv/siges/jboss-as-7.1.1.Final/media_files/',NULL,0,NULL,'2015-07-14 17:26:21.651',NULL,2),(221,6,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','1048576',NULL,0,NULL,'2015-07-07 15:31:30.391',NULL,2),(222,6,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2015-06-26 14:50:04',NULL,1),(223,6,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/srv/siges/jboss-as-7.1.1.Final/media_files/',NULL,0,NULL,'2015-07-06 16:36:38.162',NULL,2),(224,2,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','1048576',NULL,0,NULL,'2015-07-07 15:33:00.837',NULL,2),(225,2,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2015-06-26 14:50:04',NULL,1),(226,2,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/siges/media_files/',NULL,NULL,NULL,'2015-06-26 14:50:04',NULL,1),(227,4,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','1048576',NULL,0,NULL,'2015-07-07 15:33:34.654',NULL,2),(228,4,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2015-06-26 14:50:04',NULL,1),(229,4,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/srv/siges/jboss-as-7.1.1.Final/media_files/',NULL,0,NULL,'2015-07-14 17:27:43.147',NULL,2),(230,3,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','1048576',NULL,0,NULL,'2015-07-07 15:33:14.182',NULL,2),(231,3,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2015-06-26 14:50:04',NULL,1),(232,3,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/siges/media_files/',NULL,NULL,NULL,'2015-06-26 14:50:04',NULL,1),(233,7,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','1048576',NULL,0,NULL,'2015-07-07 15:33:50.164',NULL,2),(234,7,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2015-06-26 14:50:04',NULL,1),(235,7,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/srv/siges/jboss-as-7.1.1.Final/media_files/',NULL,0,NULL,'2015-07-14 17:28:42.1',NULL,2),(236,5,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','10485760',NULL,NULL,NULL,'2015-06-26 14:50:04',NULL,1),(237,5,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2015-06-26 14:50:04',NULL,1),(238,5,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/siges/media_files/',NULL,NULL,NULL,'2015-06-26 14:50:04',NULL,1),(239,1,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2015-09-08 11:20:58.754',NULL,1),(240,1,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2015-09-08 11:20:58.769',NULL,1),(241,8,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2015-09-08 11:20:58.783',NULL,1),(242,8,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2015-09-08 11:20:58.794',NULL,1),(243,6,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2015-09-08 11:20:58.812',NULL,1),(244,6,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2015-09-08 11:20:58.827',NULL,1),(245,2,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2015-09-08 11:20:58.855',NULL,1),(246,2,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2015-09-08 11:20:58.868',NULL,1),(247,4,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2015-09-08 11:20:58.884',NULL,1),(248,4,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2015-09-08 11:20:58.896',NULL,1),(249,3,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2015-09-08 11:20:58.912',NULL,1),(250,3,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2015-09-08 11:20:58.924',NULL,1),(251,7,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2015-09-08 11:20:58.942',NULL,1),(252,7,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2015-09-08 11:20:58.954',NULL,1),(253,5,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2015-09-08 11:20:58.969',NULL,1),(254,5,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2015-09-08 11:20:58.986',NULL,1),(255,9,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2015-09-09 11:46:37.875',NULL,1),(256,9,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2015-09-09 11:46:37.892',NULL,1),(257,9,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2015-09-09 11:46:37.902',NULL,1),(258,9,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-09-09 11:46:37.911',NULL,1),(259,9,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-09-09 11:46:37.922',NULL,1),(260,9,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2015-09-09 11:46:37.933',NULL,1),(261,9,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2015-09-09 11:46:37.944',NULL,1),(262,9,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2015-09-09 11:46:37.953',NULL,1),(263,9,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','10485760',NULL,NULL,NULL,'2015-09-09 11:46:37.964',NULL,1),(264,9,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-09-09 11:46:37.976',NULL,1),(265,9,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-09-09 11:46:37.991',NULL,1),(266,9,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2015-09-09 11:46:38.002',NULL,1),(267,9,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2015-09-09 11:46:38.015',NULL,1),(268,9,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2015-09-09 11:46:38.028',NULL,1),(269,9,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2015-09-09 11:46:38.039',NULL,1),(270,9,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2015-09-09 11:46:38.05',NULL,1),(271,9,'MAIL_FROM','Dirección desde donde se envían los mails','siges@agesic.gub.uy',NULL,0,NULL,'2015-12-04 12:55:11.895',NULL,2),(272,9,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2015-09-09 11:46:38.072',NULL,1),(273,9,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2015-09-09 11:46:38.083',NULL,1),(274,9,'CON_CORREO','Si se envía correo o no','false',NULL,0,NULL,'2015-12-04 12:55:03.73',NULL,2),(275,9,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2015-09-09 11:46:38.103',NULL,1),(276,9,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2015-09-09 11:46:38.113',NULL,1),(277,9,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2015-09-09 11:46:38.124',NULL,1),(278,9,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2015-09-09 11:46:38.137',NULL,1),(279,9,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2015-09-09 11:46:38.148',NULL,1),(280,9,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2015-09-09 11:46:38.159',NULL,1),(281,9,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2015-09-09 11:46:38.175',NULL,1),(282,9,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2015-09-09 11:46:38.185',NULL,1),(283,9,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2015-09-09 11:46:38.195',NULL,1),(284,9,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2015-09-09 11:46:38.206',NULL,1),(285,9,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/siges/media_files/',NULL,NULL,NULL,'2015-09-09 11:46:38.217',NULL,1),(286,1,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2015-09-21 16:24:30.612',NULL,1),(287,8,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2015-09-21 16:24:30.675',NULL,1),(288,6,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2015-09-21 16:24:30.709',NULL,1),(289,2,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2015-09-21 16:24:30.753',NULL,1),(290,9,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2015-09-21 16:24:30.783',NULL,1),(291,4,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2015-09-21 16:24:30.823',NULL,1),(292,3,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2015-09-21 16:24:30.851',NULL,1),(293,7,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2015-09-21 16:24:30.88',NULL,1),(294,5,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2015-09-21 16:24:30.907',NULL,1),(295,10,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2015-09-21 18:17:33.644',NULL,1),(296,10,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2015-09-21 18:17:33.661',NULL,1),(297,10,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2015-09-21 18:17:33.675',NULL,1),(298,10,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-09-21 18:17:33.693',NULL,1),(299,10,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-09-21 18:17:33.709',NULL,1),(300,10,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2015-09-21 18:17:33.735',NULL,1),(301,10,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2015-09-21 18:17:33.757',NULL,1),(302,10,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2015-09-21 18:17:33.78',NULL,1),(303,10,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','10485760',NULL,NULL,NULL,'2015-09-21 18:17:33.803',NULL,1),(304,10,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-09-21 18:17:33.853',NULL,1),(305,10,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-09-21 18:17:33.873',NULL,1),(306,10,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2015-09-21 18:17:33.888',NULL,1),(307,10,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2015-09-21 18:17:33.901',NULL,1),(308,10,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2015-09-21 18:17:33.926',NULL,1),(309,10,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2015-09-21 18:17:33.951',NULL,1),(310,10,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2015-09-21 18:17:33.976',NULL,1),(311,10,'MAIL_FROM','Dirección desde donde se envían los mails','siges@agesic.gub.uy',NULL,0,NULL,'2015-12-04 12:55:41.327',NULL,2),(312,10,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2015-09-21 18:17:34.035',NULL,1),(313,10,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2015-09-21 18:17:34.053',NULL,1),(314,10,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2015-09-21 18:17:34.069',NULL,1),(315,10,'CON_CORREO','Si se envía correo o no','false',NULL,0,NULL,'2015-12-04 12:55:34.617',NULL,2),(316,10,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2015-09-21 18:17:34.139',NULL,1),(317,10,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2015-09-21 18:17:34.162',NULL,1),(318,10,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2015-09-21 18:17:34.229',NULL,1),(319,10,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2015-09-21 18:17:34.261',NULL,1),(320,10,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2015-09-21 18:17:34.281',NULL,1),(321,10,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2015-09-21 18:17:34.307',NULL,1),(322,10,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2015-09-21 18:17:34.325',NULL,1),(323,10,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2015-09-21 18:17:34.347',NULL,1),(324,10,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2015-09-21 18:17:34.365',NULL,1),(325,10,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2015-09-21 18:17:34.385',NULL,1),(326,10,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/siges/media_files/',NULL,NULL,NULL,'2015-09-21 18:17:34.395',NULL,1),(327,11,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2015-09-21 18:19:22.335',NULL,1),(328,11,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2015-09-21 18:19:22.349',NULL,1),(329,11,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2015-09-21 18:19:22.362',NULL,1),(330,11,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-09-21 18:19:22.373',NULL,1),(331,11,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-09-21 18:19:22.384',NULL,1),(332,11,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2015-09-21 18:19:22.395',NULL,1),(333,11,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2015-09-21 18:19:22.405',NULL,1),(334,11,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2015-09-21 18:19:22.414',NULL,1),(335,11,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','10485760',NULL,NULL,NULL,'2015-09-21 18:19:22.424',NULL,1),(336,11,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-09-21 18:19:22.434',NULL,1),(337,11,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-09-21 18:19:22.444',NULL,1),(338,11,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2015-09-21 18:19:22.454',NULL,1),(339,11,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2015-09-21 18:19:22.465',NULL,1),(340,11,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2015-09-21 18:19:22.476',NULL,1),(341,11,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2015-09-21 18:19:22.489',NULL,1),(342,11,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2015-09-21 18:19:22.498',NULL,1),(343,11,'MAIL_FROM','Dirección desde donde se envían los mails','siges@agesic.gub.uy',NULL,0,NULL,'2015-12-04 12:56:00.379',NULL,2),(344,11,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2015-09-21 18:19:22.52',NULL,1),(345,11,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2015-09-21 18:19:22.534',NULL,1),(346,11,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2015-09-21 18:19:22.543',NULL,1),(347,11,'CON_CORREO','Si se envía correo o no','false',NULL,0,NULL,'2015-12-04 12:55:54.42',NULL,2),(348,11,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2015-09-21 18:19:22.566',NULL,1),(349,11,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2015-09-21 18:19:22.576',NULL,1),(350,11,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2015-09-21 18:19:22.588',NULL,1),(351,11,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2015-09-21 18:19:22.601',NULL,1),(352,11,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2015-09-21 18:19:22.613',NULL,1),(353,11,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2015-09-21 18:19:22.624',NULL,1),(354,11,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2015-09-21 18:19:22.642',NULL,1),(355,11,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2015-09-21 18:19:22.653',NULL,1),(356,11,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2015-09-21 18:19:22.666',NULL,1),(357,11,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2015-09-21 18:19:22.676',NULL,1),(358,11,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/siges/media_files/',NULL,NULL,NULL,'2015-09-21 18:19:22.689',NULL,1),(359,12,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2015-09-21 18:19:53.17',NULL,1),(360,12,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2015-09-21 18:19:53.225',NULL,1),(361,12,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2015-09-21 18:19:53.242',NULL,1),(362,12,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-09-21 18:19:53.268',NULL,1),(363,12,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-09-21 18:19:53.355',NULL,1),(364,12,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2015-09-21 18:19:53.374',NULL,1),(365,12,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2015-09-21 18:19:53.44',NULL,1),(366,12,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2015-09-21 18:19:53.457',NULL,1),(367,12,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','10485760',NULL,NULL,NULL,'2015-09-21 18:19:53.469',NULL,1),(368,12,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-09-21 18:19:53.483',NULL,1),(369,12,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-09-21 18:19:53.501',NULL,1),(370,12,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2015-09-21 18:19:53.519',NULL,1),(371,12,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2015-09-21 18:19:53.536',NULL,1),(372,12,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2015-09-21 18:19:53.578',NULL,1),(373,12,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2015-09-21 18:19:53.604',NULL,1),(374,12,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2015-09-21 18:19:53.626',NULL,1),(375,12,'MAIL_FROM','Dirección desde donde se envían los mails','siges@agesic.gub.uy',NULL,0,NULL,'2015-12-04 12:56:16.725',NULL,2),(376,12,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2015-09-21 18:19:53.66',NULL,1),(377,12,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2015-09-21 18:19:53.674',NULL,1),(378,12,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2015-09-21 18:19:53.689',NULL,1),(379,12,'CON_CORREO','Si se envía correo o no','false',NULL,0,NULL,'2015-12-04 12:51:32.382',NULL,2),(380,12,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2015-09-21 18:19:53.718',NULL,1),(381,12,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2015-09-21 18:19:53.732',NULL,1),(382,12,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2015-09-21 18:19:53.747',NULL,1),(383,12,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2015-09-21 18:19:53.761',NULL,1),(384,12,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2015-09-21 18:19:53.774',NULL,1),(385,12,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2015-09-21 18:19:53.785',NULL,1),(386,12,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2015-09-21 18:19:53.8',NULL,1),(387,12,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2015-09-21 18:19:53.815',NULL,1),(388,12,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2015-09-21 18:19:53.833',NULL,1),(389,12,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2015-09-21 18:19:53.852',NULL,1),(390,12,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/siges/media_files/',NULL,NULL,NULL,'2015-09-21 18:19:53.869',NULL,1),(391,14,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2015-09-23 10:27:58.596',NULL,1),(392,14,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2015-09-23 10:27:58.611',NULL,1),(393,14,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2015-09-23 10:27:58.627',NULL,1),(394,14,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-09-23 10:27:58.65',NULL,1),(395,14,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-09-23 10:27:58.664',NULL,1),(396,14,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2015-09-23 10:27:58.676',NULL,1),(397,14,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2015-09-23 10:27:58.688',NULL,1),(398,14,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2015-09-23 10:27:58.702',NULL,1),(399,14,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','10485760',NULL,NULL,NULL,'2015-09-23 10:27:58.714',NULL,1),(400,14,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-09-23 10:27:58.727',NULL,1),(401,14,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-09-23 10:27:58.738',NULL,1),(402,14,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2015-09-23 10:27:58.75',NULL,1),(403,14,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2015-09-23 10:27:58.76',NULL,1),(404,14,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2015-09-23 10:27:58.773',NULL,1),(405,14,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2015-09-23 10:27:58.784',NULL,1),(406,14,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2015-09-23 10:27:58.809',NULL,1),(407,14,'MAIL_FROM','Dirección desde donde se envían los mails','mail@agesic.gub.uy',NULL,NULL,NULL,'2015-09-23 10:27:58.822',NULL,1),(408,14,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2015-09-23 10:27:58.84',NULL,1),(409,14,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2015-09-23 10:27:58.858',NULL,1),(410,14,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2015-09-23 10:27:58.87',NULL,1),(411,14,'CON_CORREO','Si se envía correo o no','false',NULL,NULL,NULL,'2015-09-23 10:27:58.887',NULL,1),(412,14,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2015-09-23 10:27:58.899',NULL,1),(413,14,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2015-09-23 10:27:58.912',NULL,1),(414,14,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2015-09-23 10:27:58.925',NULL,1),(415,14,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2015-09-23 10:27:58.939',NULL,1),(416,14,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2015-09-23 10:27:58.956',NULL,1),(417,14,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2015-09-23 10:27:58.973',NULL,1),(418,14,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2015-09-23 10:27:59.004',NULL,1),(419,14,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2015-09-23 10:27:59.026',NULL,1),(420,14,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2015-09-23 10:27:59.046',NULL,1),(421,14,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2015-09-23 10:27:59.062',NULL,1),(422,14,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/siges/media_files/',NULL,NULL,NULL,'2015-09-23 10:27:59.082',NULL,1),(423,13,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2015-09-23 10:27:59.097',NULL,1),(424,13,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2015-09-23 10:27:59.119',NULL,1),(425,13,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2015-09-23 10:27:59.138',NULL,1),(426,13,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-09-23 10:27:59.152',NULL,1),(427,13,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-09-23 10:27:59.168',NULL,1),(428,13,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2015-09-23 10:27:59.187',NULL,1),(429,13,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2015-09-23 10:27:59.201',NULL,1),(430,13,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2015-09-23 10:27:59.213',NULL,1),(431,13,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','10485760',NULL,NULL,NULL,'2015-09-23 10:27:59.229',NULL,1),(432,13,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-09-23 10:27:59.242',NULL,1),(433,13,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-09-23 10:27:59.254',NULL,1),(434,13,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2015-09-23 10:27:59.267',NULL,1),(435,13,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2015-09-23 10:27:59.279',NULL,1),(436,13,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2015-09-23 10:27:59.32',NULL,1),(437,13,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2015-09-23 10:27:59.339',NULL,1),(438,13,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2015-09-23 10:27:59.351',NULL,1),(439,13,'MAIL_FROM','Dirección desde donde se envían los mails','siges@agesic.gub.uy',NULL,0,NULL,'2015-09-23 12:21:02.055',NULL,2),(440,13,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2015-09-23 10:27:59.389',NULL,1),(441,13,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2015-09-23 10:27:59.4',NULL,1),(442,13,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2015-09-23 10:27:59.412',NULL,1),(443,13,'CON_CORREO','Si se envía correo o no','false',NULL,0,NULL,'2015-09-23 12:20:54.137',NULL,2),(444,13,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2015-09-23 10:27:59.44',NULL,1),(445,13,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2015-09-23 10:27:59.453',NULL,1),(446,13,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2015-09-23 10:27:59.648',NULL,1),(447,13,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2015-09-23 10:27:59.854',NULL,1),(448,13,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2015-09-23 10:27:59.91',NULL,1),(449,13,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','true',NULL,0,NULL,'2016-03-04 19:40:55.257',NULL,2),(450,13,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2015-09-23 10:27:59.938',NULL,1),(451,13,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2015-09-23 10:27:59.955',NULL,1),(452,13,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2015-09-23 10:27:59.967',NULL,1),(453,13,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2015-09-23 10:27:59.977',NULL,1),(454,13,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/siges/media_files/',NULL,NULL,NULL,'2015-09-23 10:27:59.988',NULL,1),(455,15,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2015-10-30 14:13:42.184',NULL,1),(456,15,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2015-10-30 14:13:42.209',NULL,1),(457,15,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2015-10-30 14:13:42.23',NULL,1),(458,15,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-10-30 14:13:42.252',NULL,1),(459,15,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-10-30 14:13:42.271',NULL,1),(460,15,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2015-10-30 14:13:42.285',NULL,1),(461,15,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2015-10-30 14:13:42.303',NULL,1),(462,15,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2015-10-30 14:13:42.319',NULL,1),(463,15,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','2 MB como tope','2097152',NULL,0,NULL,'2016-05-25 11:58:26.867',NULL,2),(464,15,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2015-10-30 14:13:42.348',NULL,1),(465,15,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,NULL,NULL,'2015-10-30 14:13:42.381',NULL,1),(466,15,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2015-10-30 14:13:42.397',NULL,1),(467,15,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2015-10-30 14:13:42.415',NULL,1),(468,15,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2015-10-30 14:13:42.428',NULL,1),(469,15,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2015-10-30 14:13:42.441',NULL,1),(470,15,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2015-10-30 14:13:42.464',NULL,1),(471,15,'MAIL_FROM','Dirección desde donde se envían los mails','siges@agesic.gub.uy',NULL,0,NULL,'2015-12-04 12:57:01.45',NULL,2),(472,15,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2015-10-30 14:13:42.508',NULL,1),(473,15,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2015-10-30 14:13:42.536',NULL,1),(474,15,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2015-10-30 14:13:42.556',NULL,1),(475,15,'CON_CORREO','Si se envía correo o no','false',NULL,0,NULL,'2015-12-04 12:56:56.669',NULL,2),(476,15,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2015-10-30 14:13:42.588',NULL,1),(477,15,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2015-10-30 14:13:42.616',NULL,1),(478,15,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2015-10-30 14:13:42.641',NULL,1),(479,15,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2015-10-30 14:13:42.657',NULL,1),(480,15,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2015-10-30 14:13:42.68',NULL,1),(481,15,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','true',NULL,0,NULL,'2015-12-08 10:01:32.702',NULL,2),(482,15,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','true',NULL,0,NULL,'2016-02-01 11:42:52.978',NULL,2),(483,15,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2015-10-30 14:13:42.728',NULL,1),(484,15,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2015-10-30 14:13:42.743',NULL,1),(485,15,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2015-10-30 14:13:42.757',NULL,1),(486,15,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/siges/media_files/',NULL,NULL,NULL,'2015-10-30 14:13:42.778',NULL,1),(517,6,'VISUALIZADOR.PUBLICARSERVICIO_URLLOGICA','PGE','http://servicios.pge.red.uy/agesic/PublicarProyecto',NULL,0,NULL,'2016-02-04 14:31:01.681',NULL,1),(518,6,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,0,NULL,'2016-06-20 11:10:41.802',NULL,2),(519,6,'VISUALIZADOR.PUBLICARSERVICIO_NAMESPACE','PGE','http://ws.web.visualizador.siges.agesic.org/',NULL,0,NULL,'2016-02-04 14:30:44.657',NULL,1),(520,4,'VISUALIZADOR.PUBLICARSERVICIO_SERVICENAME','','PublicarProyecto',NULL,0,NULL,'2016-02-04 14:26:03.618',NULL,0),(521,6,'VISUALIZADOR.PUBLICARSERVICIO_SERVICENAME','PGE','PublicarProyecto',NULL,0,NULL,'2016-02-04 14:30:53.454',NULL,1),(522,6,'VISUALIZADOR.PUBLICARSERVICIO_ROL','PGE','ou=siges,o=agesic',NULL,0,NULL,'2016-02-04 14:30:48.904',NULL,1),(523,6,'STS_URL','PGE','https://servicios.pge.red.uy:10001/TrustServer/SecurityTokenServiceProtected',NULL,0,NULL,'2016-02-04 14:30:30.175',NULL,1),(524,6,'STS_POLITICA','PGE','urn:std15',NULL,0,NULL,'2016-02-04 14:30:26.197',NULL,1),(525,6,'STS_EMISOR','PGE','Agesic',NULL,0,NULL,'2016-02-04 14:30:21.781',NULL,1),(526,6,'KEYSTORE_ORG_PASS','PGE','Clave_4ges!c-PD1',NULL,0,NULL,'2016-02-04 14:30:05.971',NULL,1),(527,6,'KEYSTORE_ORG_ALIAS','PGE','agesic-pdi (correo uruguayo - ca)',NULL,0,NULL,'2016-02-04 14:30:01.611',NULL,1),(528,6,'KEYSTORE_SSL_PASS','PGE','agesic',NULL,0,NULL,'2016-02-04 14:30:10.583',NULL,1),(529,6,'TRUSTSTORE_SSL_PASS','PGE','Clave_4ges!c-PD1',NULL,0,NULL,'2016-02-04 14:30:33.925',NULL,1),(530,6,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,0,NULL,'2016-02-04 16:45:41.836',NULL,1),(531,6,'KEYSTORE_ORG_PATH','PGE','/srv/siges/jboss-as-7.1.1.Final/PGE/organismo.keystore',NULL,0,NULL,'2016-02-04 14:37:26.6',NULL,0),(532,6,'KEYSTORE_SSL_PATH','PGE','/srv/siges/jboss-as-7.1.1.Final/PGE/vsiges_ssl.keystore',NULL,0,NULL,'2016-02-04 14:38:47.176',NULL,0),(533,6,'TRUSTSTORE_SSL_PATH','PGE','/srv/siges/jboss-as-7.1.1.Final/PGE/agesic.truststore',NULL,0,NULL,'2016-02-04 14:39:20.162',NULL,0),(534,16,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2016-03-16 09:43:25.002',NULL,1),(535,16,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2016-03-16 09:43:25.019',NULL,1),(536,16,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2016-03-16 09:43:25.036',NULL,1),(537,16,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2016-03-16 09:43:25.051',NULL,1),(538,16,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2016-03-16 09:43:25.065',NULL,1),(539,16,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2016-03-16 09:43:25.081',NULL,1),(540,16,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2016-03-16 09:43:25.095',NULL,1),(541,16,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2016-03-16 09:43:25.114',NULL,1),(542,16,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','512000',NULL,NULL,NULL,'2016-03-16 09:43:25.128',NULL,1),(543,16,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2016-03-16 09:43:25.14',NULL,1),(544,16,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,NULL,NULL,'2016-03-16 09:43:25.153',NULL,1),(545,16,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2016-03-16 09:43:25.166',NULL,1),(546,16,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2016-03-16 09:43:25.179',NULL,1),(547,16,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2016-03-16 09:43:25.192',NULL,1),(548,16,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2016-03-16 09:43:25.204',NULL,1),(549,16,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2016-03-16 09:43:25.217',NULL,1),(550,16,'MAIL_FROM','Dirección desde donde se envían los mails','mail@agesic.gub.uy',NULL,NULL,NULL,'2016-03-16 09:43:25.229',NULL,1),(551,16,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2016-03-16 09:43:25.242',NULL,1),(552,16,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2016-03-16 09:43:25.256',NULL,1),(553,16,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2016-03-16 09:43:25.278',NULL,1),(554,16,'CON_CORREO','Si se envía correo o no','false',NULL,NULL,NULL,'2016-03-16 09:43:25.291',NULL,1),(555,16,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2016-03-16 09:43:25.308',NULL,1),(556,16,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2016-03-16 09:43:25.324',NULL,1),(557,16,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2016-03-16 09:43:25.343',NULL,1),(558,16,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2016-03-16 09:43:25.37',NULL,1),(559,16,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2016-03-16 09:43:25.382',NULL,1),(560,16,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2016-03-16 09:43:25.395',NULL,1),(561,16,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2016-03-16 09:43:25.409',NULL,1),(562,16,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2016-03-16 09:43:25.423',NULL,1),(563,16,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2016-03-16 09:43:25.436',NULL,1),(564,16,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2016-03-16 09:43:25.453',NULL,1),(565,16,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/srv/siges/media_files/',NULL,NULL,NULL,'2016-03-16 09:43:25.466',NULL,1),(566,NULL,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-05-19 18:40:46.915',NULL,1),(567,1,'VISUALIZADOR.PUBLICARSERVICIO_NAMESPACE','PGE','http://ws.web.visualizador.siges.agesic.org/',NULL,0,NULL,'2016-05-23 16:34:55.729',NULL,0),(568,1,'VISUALIZADOR.PUBLICARSERVICIO_ROL','PGE','ou=siges,o=agesic',NULL,0,NULL,'2016-05-23 16:35:37.41',NULL,0),(569,1,'VISUALIZADOR.PUBLICARSERVICIO_SERVICENAME','PGE','PublicarProyecto',NULL,0,NULL,'2016-05-23 16:35:52.607',NULL,0),(570,1,'VISUALIZADOR.PUBLICARSERVICIO_URLLOGICA','PGE','http://servicios.pge.red.uy/agesic/PublicarProyecto/MiradorGobierno',NULL,0,NULL,'2016-05-23 16:38:21.203',NULL,1),(571,1,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,0,NULL,'2016-05-25 17:35:30.068',NULL,4),(572,1,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,0,NULL,'2016-05-23 16:50:30.664',NULL,1),(573,1,'STS_POLITICA','PGE','urn:std15',NULL,0,NULL,'2016-05-23 16:40:22.295',NULL,0),(574,1,'STS_EMISOR','PGE','Agesic',NULL,0,NULL,'2016-05-23 16:40:42.048',NULL,0),(575,1,'KEYSTORE_SSL_PATH','PGE','/srv/siges/jboss-as-7.1.1.Final/PGE/vsiges_ssl.keystore',NULL,0,NULL,'2016-05-23 16:41:20.123',NULL,0),(576,1,'KEYSTORE_SSL_PASS','PGE','agesic',NULL,0,NULL,'2016-05-23 16:41:32.79',NULL,0),(577,1,'KEYSTORE_ORG_PATH','PGE','/srv/siges/jboss-as-7.1.1.Final/PGE/organismo.keystore',NULL,0,NULL,'2016-05-23 16:41:45.4',NULL,0),(578,1,'KEYSTORE_ORG_PASS','PGE','Clave_4ges!c-PD1',NULL,0,NULL,'2016-05-23 16:42:02.011',NULL,0),(579,1,'KEYSTORE_ORG_ALIAS','PGE','agesic-pdi (correo uruguayo - ca)',NULL,0,NULL,'2016-05-23 16:42:21.004',NULL,0),(580,1,'STS_URL','PGE','https://servicios.pge.red.uy:10001/TrustServer/SecurityTokenServiceProtected',NULL,0,NULL,'2016-05-23 16:43:04.344',NULL,0),(581,1,'TRUSTSTORE_SSL_PASS','PGE','Clave_4ges!c-PD1',NULL,0,NULL,'2016-05-23 16:43:33.658',NULL,0),(582,1,'TRUSTSTORE_SSL_PATH','PGE','/srv/siges/jboss-as-7.1.1.Final/PGE/agesic.truststore',NULL,0,NULL,'2016-05-23 16:43:57.985',NULL,0),(583,1,'VISUALIZADOR_EXPORTACION_POR_PGE','PGE Usar PGE','false',NULL,0,NULL,'2016-05-25 17:35:57.836',NULL,1),(584,1,'VISUALIZADOR.PUBLICARSERVICIO_MTOM','Habilitar envío WS MTOM','true',NULL,0,NULL,'2016-05-25 12:22:39.978',NULL,1),(585,6,'VISUALIZADOR_EXPORTACION_POR_PGE','Si publica o no por PGE','false',NULL,0,NULL,'2016-06-20 11:05:17.508',NULL,0),(586,17,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2016-07-20 14:49:06.76',NULL,1),(587,17,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2016-07-20 14:49:06.768',NULL,1),(588,17,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2016-07-20 14:49:06.775',NULL,1),(589,17,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2016-07-20 14:49:06.783',NULL,1),(590,17,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2016-07-20 14:49:06.791',NULL,1),(591,17,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2016-07-20 14:49:06.798',NULL,1),(592,17,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2016-07-20 14:49:06.806',NULL,1),(593,17,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2016-07-20 14:49:06.816',NULL,1),(594,17,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','512000',NULL,NULL,NULL,'2016-07-20 14:49:06.823',NULL,1),(595,17,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2016-07-20 14:49:06.831',NULL,1),(596,17,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,NULL,NULL,'2016-07-20 14:49:06.839',NULL,1),(597,17,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2016-07-20 14:49:06.846',NULL,1),(598,17,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2016-07-20 14:49:06.854',NULL,1),(599,17,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2016-07-20 14:49:06.862',NULL,1),(600,17,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2016-07-20 14:49:06.87',NULL,1),(601,17,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2016-07-20 14:49:06.878',NULL,1),(602,17,'MAIL_FROM','Dirección desde donde se envían los mails','mail@agesic.gub.uy',NULL,NULL,NULL,'2016-07-20 14:49:06.885',NULL,1),(603,17,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2016-07-20 14:49:06.893',NULL,1),(604,17,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2016-07-20 14:49:06.9',NULL,1),(605,17,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2016-07-20 14:49:06.908',NULL,1),(606,17,'CON_CORREO','Si se envía correo o no','false',NULL,NULL,NULL,'2016-07-20 14:49:06.916',NULL,1),(607,17,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2016-07-20 14:49:06.923',NULL,1),(608,17,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2016-07-20 14:49:06.931',NULL,1),(609,17,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2016-07-20 14:49:06.938',NULL,1),(610,17,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2016-07-20 14:49:06.946',NULL,1),(611,17,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2016-07-20 14:49:06.954',NULL,1),(612,17,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2016-07-20 14:49:06.961',NULL,1),(613,17,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2016-07-20 14:49:06.969',NULL,1),(614,17,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2016-07-20 14:49:06.977',NULL,1),(615,17,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2016-07-20 14:49:06.984',NULL,1),(616,17,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2016-07-20 14:49:06.992',NULL,1),(617,17,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/srv/siges/media_files/',NULL,NULL,NULL,'2016-07-20 14:49:07',NULL,1),(618,18,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2016-07-29 18:03:18.377',NULL,1),(619,18,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2016-07-29 18:03:18.384',NULL,1),(620,18,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2016-07-29 18:03:18.391',NULL,1),(621,18,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2016-07-29 18:03:18.397',NULL,1),(622,18,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2016-07-29 18:03:18.404',NULL,1),(623,18,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2016-07-29 18:03:18.411',NULL,1),(624,18,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2016-07-29 18:03:18.417',NULL,1),(625,18,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2016-07-29 18:03:18.424',NULL,1),(626,18,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','512000',NULL,NULL,NULL,'2016-07-29 18:03:18.43',NULL,1),(627,18,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2016-07-29 18:03:18.437',NULL,1),(628,18,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,NULL,NULL,'2016-07-29 18:03:18.444',NULL,1),(629,18,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2016-07-29 18:03:18.452',NULL,1),(630,18,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2016-07-29 18:03:18.458',NULL,1),(631,18,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2016-07-29 18:03:18.465',NULL,1),(632,18,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2016-07-29 18:03:18.471',NULL,1),(633,18,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2016-07-29 18:03:18.478',NULL,1),(634,18,'MAIL_FROM','Dirección desde donde se envían los mails','mail@agesic.gub.uy',NULL,NULL,NULL,'2016-07-29 18:03:18.486',NULL,1),(635,18,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2016-07-29 18:03:18.492',NULL,1),(636,18,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2016-07-29 18:03:18.498',NULL,1),(637,18,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2016-07-29 18:03:18.505',NULL,1),(638,18,'CON_CORREO','Si se envía correo o no','false',NULL,NULL,NULL,'2016-07-29 18:03:18.512',NULL,1),(639,18,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2016-07-29 18:03:18.518',NULL,1),(640,18,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2016-07-29 18:03:18.525',NULL,1),(641,18,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2016-07-29 18:03:18.533',NULL,1),(642,18,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2016-07-29 18:03:18.54',NULL,1),(643,18,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2016-07-29 18:03:18.548',NULL,1),(644,18,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2016-07-29 18:03:18.555',NULL,1),(645,18,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2016-07-29 18:03:18.562',NULL,1),(646,18,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2016-07-29 18:03:18.569',NULL,1),(647,18,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2016-07-29 18:03:18.576',NULL,1),(648,18,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2016-07-29 18:03:18.583',NULL,1),(649,18,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/srv/siges/media_files/',NULL,NULL,NULL,'2016-07-29 18:03:18.59',NULL,1),(650,19,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2016-07-29 18:18:13.492',NULL,1),(651,19,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2016-07-29 18:18:13.499',NULL,1),(652,19,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2016-07-29 18:18:13.506',NULL,1),(653,19,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2016-07-29 18:18:13.512',NULL,1),(654,19,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2016-07-29 18:18:13.519',NULL,1),(655,19,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2016-07-29 18:18:13.526',NULL,1),(656,19,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2016-07-29 18:18:13.532',NULL,1),(657,19,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2016-07-29 18:18:13.539',NULL,1),(658,19,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','512000',NULL,NULL,NULL,'2016-07-29 18:18:13.545',NULL,1),(659,19,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2016-07-29 18:18:13.552',NULL,1),(660,19,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,NULL,NULL,'2016-07-29 18:18:13.559',NULL,1),(661,19,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2016-07-29 18:18:13.565',NULL,1),(662,19,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2016-07-29 18:18:13.572',NULL,1),(663,19,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2016-07-29 18:18:13.579',NULL,1),(664,19,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2016-07-29 18:18:13.585',NULL,1),(665,19,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2016-07-29 18:18:13.592',NULL,1),(666,19,'MAIL_FROM','Dirección desde donde se envían los mails','mail@agesic.gub.uy',NULL,NULL,NULL,'2016-07-29 18:18:13.598',NULL,1),(667,19,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2016-07-29 18:18:13.605',NULL,1),(668,19,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2016-07-29 18:18:13.612',NULL,1),(669,19,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2016-07-29 18:18:13.618',NULL,1),(670,19,'CON_CORREO','Si se envía correo o no','false',NULL,NULL,NULL,'2016-07-29 18:18:13.625',NULL,1),(671,19,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2016-07-29 18:18:13.632',NULL,1),(672,19,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2016-07-29 18:18:13.638',NULL,1),(673,19,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2016-07-29 18:18:13.645',NULL,1),(674,19,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2016-07-29 18:18:13.651',NULL,1),(675,19,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2016-07-29 18:18:13.662',NULL,1),(676,19,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2016-07-29 18:18:13.669',NULL,1),(677,19,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2016-07-29 18:18:13.675',NULL,1),(678,19,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2016-07-29 18:18:13.682',NULL,1),(679,19,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2016-07-29 18:18:13.688',NULL,1),(680,19,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2016-07-29 18:18:13.697',NULL,1),(681,19,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/srv/siges/media_files/',NULL,NULL,NULL,'2016-07-29 18:18:13.704',NULL,1),(682,20,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2016-09-07 14:52:35.638',NULL,1),(683,20,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2016-09-07 14:52:35.647',NULL,1),(684,20,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2016-09-07 14:52:35.655',NULL,1),(685,20,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2016-09-07 14:52:35.664',NULL,1),(686,20,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2016-09-07 14:52:35.672',NULL,1),(687,20,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2016-09-07 14:52:35.681',NULL,1),(688,20,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2016-09-07 14:52:35.691',NULL,1),(689,20,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2016-09-07 14:52:35.701',NULL,1),(690,20,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','512000',NULL,NULL,NULL,'2016-09-07 14:52:35.71',NULL,1),(691,20,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2016-09-07 14:52:35.719',NULL,1),(692,20,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,NULL,NULL,'2016-09-07 14:52:35.728',NULL,1),(693,20,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2016-09-07 14:52:35.736',NULL,1),(694,20,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2016-09-07 14:52:35.746',NULL,1),(695,20,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2016-09-07 14:52:35.755',NULL,1),(696,20,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2016-09-07 14:52:35.763',NULL,1),(697,20,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2016-09-07 14:52:35.772',NULL,1),(698,20,'MAIL_FROM','Dirección desde donde se envían los mails','siges@agesic.gub.uy',NULL,0,NULL,'2016-09-07 15:01:29.538',NULL,2),(699,20,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2016-09-07 14:52:35.79',NULL,1),(700,20,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2016-09-07 14:52:35.8',NULL,1),(701,20,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2016-09-07 14:52:35.809',NULL,1),(702,20,'CON_CORREO','Si se envía correo o no','false',NULL,NULL,NULL,'2016-09-07 14:52:35.82',NULL,1),(703,20,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2016-09-07 14:52:35.829',NULL,1),(704,20,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2016-09-07 14:52:35.838',NULL,1),(705,20,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2016-09-07 14:52:35.847',NULL,1),(706,20,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2016-09-07 14:52:35.858',NULL,1),(707,20,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2016-09-07 14:52:35.868',NULL,1),(708,20,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2016-09-07 14:52:35.877',NULL,1),(709,20,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2016-09-07 14:52:35.886',NULL,1),(710,20,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2016-09-07 14:52:35.896',NULL,1),(711,20,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2016-09-07 14:52:35.906',NULL,1),(712,20,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2016-09-07 14:52:35.917',NULL,1),(713,20,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/srv/siges/media_files/',NULL,NULL,NULL,'2016-09-07 14:52:35.926',NULL,1),(714,21,'FILTRO_INICIO_POR_AREAS','Agrupar resultado incio por areas','false',NULL,NULL,NULL,'2016-09-07 16:13:24.374',NULL,1),(715,21,'RIESGO_INDICE_LIMITE_AMARILLO','','1.2',NULL,NULL,NULL,'2016-09-07 16:13:24.381',NULL,1),(716,21,'RIESGO_INDICE_LIMITE_ROJO','','4',NULL,NULL,NULL,'2016-09-07 16:13:24.389',NULL,1),(717,21,'RIESGO_TIEMPO_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2016-09-07 16:13:24.397',NULL,1),(718,21,'RIESGO_TIEMPO_LIMITE_ROJO','','20',NULL,NULL,NULL,'2016-09-07 16:13:24.405',NULL,1),(719,21,'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO','','30',NULL,NULL,NULL,'2016-09-07 16:13:24.412',NULL,1),(720,21,'DOCUMENTO_PORCENTAJE_LIMITE_ROJO','','70',NULL,NULL,NULL,'2016-09-07 16:13:24.42',NULL,1),(721,21,'TAMANIO_MAX_ARCHIVO_DOCUMENTO','','10485760',NULL,NULL,NULL,'2016-09-07 16:13:24.428',NULL,1),(722,21,'TAMANIO_MAX_ARCHIVO_MULTIMEDIA','','512000',NULL,NULL,NULL,'2016-09-07 16:13:24.435',NULL,1),(723,21,'COSTO_ACTUAL_LIMITE_AMARILLO','','10',NULL,NULL,NULL,'2016-09-07 16:13:24.443',NULL,1),(724,21,'COSTO_ACTUAL_LIMITE_ROJO','','20',NULL,NULL,NULL,'2016-09-07 16:13:24.452',NULL,1),(725,21,'ESTADO_INICIO_LIMITE_AMARILLO','Semaforo estado Inicio amarillo','10',NULL,NULL,NULL,'2016-09-07 16:13:24.46',NULL,1),(726,21,'ESTADO_INICIO_LIMITE_ROJO','Semaforo estado Inicio rojo','15',NULL,NULL,NULL,'2016-09-07 16:13:24.468',NULL,1),(727,21,'ESTADO_PLANIFICACION_LIMITE_AMARILLO','Semaforo estado Planificacion amarillo','15',NULL,NULL,NULL,'2016-09-07 16:13:24.476',NULL,1),(728,21,'ESTADO_PLANIFICACION_LIMITE_ROJO','Semaforo estado Planificacion rojo','20',NULL,NULL,NULL,'2016-09-07 16:13:24.483',NULL,1),(729,21,'TAMANIO_MAX_LOGO_ORGANISMO','Tamaño máximo en bytes del logo del Organismo','262144',NULL,NULL,NULL,'2016-09-07 16:13:24.491',NULL,1),(730,21,'MAIL_FROM','Dirección desde donde se envían los mails','mail@agesic.gub.uy',NULL,NULL,NULL,'2016-09-07 16:13:24.502',NULL,1),(731,21,'MAIL_TLS','Configuración TLS en envío de mail','false',NULL,NULL,NULL,'2016-09-07 16:13:24.509',NULL,1),(732,21,'MAIL_DEBUG','Debug del envío de mail','false',NULL,NULL,NULL,'2016-09-07 16:13:24.517',NULL,1),(733,21,'MAIL_ENCODING','Encoding de los mails a enviar','utf8',NULL,NULL,NULL,'2016-09-07 16:13:24.525',NULL,1),(734,21,'CON_CORREO','Si se envía correo o no','false',NULL,NULL,NULL,'2016-09-07 16:13:24.534',NULL,1),(735,21,'CON_CONTROL_ACCESO','Si se usa el control de acceso de Agesic o no','false',NULL,NULL,NULL,'2016-09-07 16:13:24.543',NULL,1),(736,21,'PRODUCTO_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Productos','10',NULL,NULL,NULL,'2016-09-07 16:13:24.551',NULL,1),(737,21,'PRODUCTO_INDICE_LIMITE_ROJO','Limite semaforo rojo para Productos','20',NULL,NULL,NULL,'2016-09-07 16:13:24.56',NULL,1),(738,21,'ALCANCE_INDICE_LIMITE_AMARILLO','Limite semaforo amarillo para Alcance','90',NULL,NULL,NULL,'2016-09-07 16:13:24.569',NULL,1),(739,21,'ALCANCE_INDICE_LIMITE_ROJO','Limite semaforo rojo para Alcance','70',NULL,NULL,NULL,'2016-09-07 16:13:24.577',NULL,1),(740,21,'ADJUNTO_MODIFICA_PRESUPUESTO','Adjunto puede modificar presupuesto','false',NULL,NULL,NULL,'2016-09-07 16:13:24.585',NULL,1),(741,21,'ENVIO_NOTIFICACIONES','Determina si se envían notificaciones','false',NULL,NULL,NULL,'2016-09-07 16:13:24.594',NULL,1),(742,21,'CALIDAD_LIMITE_AMARILLO','Semaforo limite amarillo calidad','70',NULL,NULL,NULL,'2016-09-07 16:13:24.602',NULL,1),(743,21,'CALIDAD_LIMITE_ROJO','Semaforo limite rojo calidad','30',NULL,NULL,NULL,'2016-09-07 16:13:24.61',NULL,1),(744,21,'CALIDAD_GERENTE_MODIFICA','Permitir al Gerente modificar items calidad','false',NULL,NULL,NULL,'2016-09-07 16:13:24.619',NULL,1),(745,21,'FOLDER_MEDIA','Carpeta donde se almacenan los archivos Multimedia','/srv/siges/media_files/',NULL,NULL,NULL,'2016-09-07 16:13:24.627',NULL,1),(746,3,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(747,3,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(748,3,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(749,19,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(750,19,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(751,19,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(752,18,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(753,18,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(754,18,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(755,7,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(756,7,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(757,7,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(758,16,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(759,16,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(760,16,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(761,2,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(762,2,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(763,2,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(764,9,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(765,9,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(766,9,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(767,4,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(768,4,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(769,4,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(770,11,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:39',NULL,1),(771,11,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(772,11,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(773,12,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(774,12,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(775,12,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(776,14,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(777,14,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(778,14,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(779,13,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(780,13,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(781,13,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(782,17,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(783,17,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(784,17,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(785,20,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(786,20,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(787,20,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(788,8,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(789,8,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(790,8,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(791,21,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(792,21,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(793,21,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(794,10,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(795,10,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(796,10,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(797,15,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(798,15,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(799,15,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(800,5,'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION','PGE SOAP Action','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(801,5,'VISUALIZADOR.PUBLICARSERVICIO_WSDL','PGE WSDL','http://localhost:8380/SigesVisualizadorPrivado/PublicarProyecto?WSDL',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1),(802,5,'VISUALIZADOR_EXPORTACION_POR_PGE','Realizar la exportación al Visualizador por la PGE','false',NULL,NULL,NULL,'2016-09-16 15:34:40',NULL,1);
/*!40000 ALTER TABLE `ss_configuraciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'siges_agesic'
--

--
-- Dumping routines for database 'siges_agesic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


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
-- Dumping data for table `ss_rol`
--

LOCK TABLES `ss_rol` WRITE;
/*!40000 ALTER TABLE `ss_rol` DISABLE KEYS */;
INSERT INTO `ss_rol` VALUES (0,'ADMINISTRADOR','Usuario Administrador de la aplicacion','Usuario Administrador','','SIGES_WEB',0,NULL,1,0),(1,'PMOT','PMO Transversal','PMO Transversal','','SIGES_WEB',0,NULL,1,1),(2,'PMOF','PMO Federada','PMO Federada','','SIGES_WEB',0,NULL,1,1),(3,'DIR','Director','Director','','SIGES_WEB',0,NULL,1,1),(4,'USU','Usuario','Usuario','','SIGES_WEB',0,NULL,1,1),(5,'ADMINIS',NULL,'ADMINIS','','SIGES_WEB',0,NULL,1,NULL),(6,'ADMIN_TDO',NULL,'ADMIN_TDO','','SIGES_WEB',0,NULL,1,NULL),(7,'ADMIN_ERR',NULL,'ADMIN_ERR','','SIGES_WEB',0,NULL,1,NULL),(8,'ADM_CONF',NULL,'ADM_CONF','','SIGES_WEB',0,NULL,1,NULL),(9,'ADMIN_NUEVOFICHA',NULL,'ADMIN_NUEVOFICHA','','SIGES_WEB',0,NULL,1,NULL),(10,'CONF_ADD',NULL,'CONF_ADD','','SIGES_WEB',0,NULL,1,NULL),(11,'CONF_EDIT',NULL,'CONF_EDIT','','SIGES_WEB',0,NULL,1,NULL),(12,'CONF_HIST',NULL,'CONF_HIST','','SIGES_WEB',0,NULL,1,NULL),(13,'MIGRACION','Acceso a la migración','MIGRACION','','SIGES_WEB',0,NULL,1,NULL),(14,'MIGRA_CALC_INDICA','Acceso a los callculos de la migración','MIGRA_CALC_INDICA','','SIGES_WEB',0,NULL,1,NULL),(15,'ADMINIS',NULL,'ADMINIS','','SIGES_WEB',0,NULL,1,NULL),(16,'ADMIN_TDO',NULL,'ADMIN_TDO','','SIGES_WEB',0,NULL,1,NULL),(17,'ADMIN_ERR',NULL,'ADMIN_ERR','','SIGES_WEB',0,NULL,1,NULL),(18,'ADM_CONF',NULL,'ADM_CONF','','SIGES_WEB',0,NULL,1,NULL),(19,'CONF_ADD',NULL,'CONF_ADD','','SIGES_WEB',0,NULL,1,NULL),(20,'CONF_EDIT',NULL,'CONF_EDIT','','SIGES_WEB',0,NULL,1,NULL),(21,'CONF_HIST',NULL,'CONF_HIST','','SIGES_WEB',0,NULL,1,NULL),(22,'MIGRACION','Acceso a la migración','MIGRACION','','SIGES_WEB',0,NULL,1,NULL),(23,'MIGRA_CALC_INDICA','Acceso a los callculos de la migración','MIGRA_CALC_INDICA','','SIGES_WEB',0,NULL,1,NULL),(24,'ADMIN_USU','Administración de los Usuarios','ADMIN_USU','','SIGES_WEB',0,NULL,1,NULL),(25,'ADMIN_USU','Administración de los Usuarios','ADMIN_USU','','SIGES_WEB',0,NULL,1,NULL),(26,'USU_HORAS','Externo (solo carga de horas)','Externo (solo carga de horas)','','SIGES_WEB',0,NULL,1,0),(27,'ADMIN_USU','Administración de los Usuarios','ADMIN_USU','','SIGES_WEB',0,NULL,1,NULL),(28,'USU_HORAS','Externo (solo carga de horas)','Externo (solo carga de horas)','','SIGES_WEB',0,NULL,1,0),(29,'REGISTRO_HORAS','Externo (solo carga de horas)','Externo (solo carga de horas)','rol_registro_horas','SIGES_WEB',0,NULL,1,1),(30,'EDITOR_GRAL',NULL,'Editor General','rol_editor_gral','SIGES_WEB',0,NULL,1,1);
/*!40000 ALTER TABLE `ss_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'siges_agesic'
--

--
-- Dumping routines for database 'siges_agesic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


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
-- Dumping data for table `tipo_leccion`
--

LOCK TABLES `tipo_leccion` WRITE;
/*!40000 ALTER TABLE `tipo_leccion` DISABLE KEYS */;
INSERT INTO `tipo_leccion` VALUES (1,'A_EVITAR','Evitar'),(2,'A_REPETIR','Repetir');
/*!40000 ALTER TABLE `tipo_leccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'siges_agesic'
--

--
-- Dumping routines for database 'siges_agesic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;



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
-- Dumping data for table `tipos_media`
--

LOCK TABLES `tipos_media` WRITE;
/*!40000 ALTER TABLE `tipos_media` DISABLE KEYS */;
INSERT INTO `tipos_media` VALUES (1,'IMG','Imagen'),(2,'VIDEO','Video'),(3,'CAM','Cámara');
/*!40000 ALTER TABLE `tipos_media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'siges_agesic'
--

--
-- Dumping routines for database 'siges_agesic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;



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
-- Dumping data for table `valor_calidad_codigos`
--

LOCK TABLES `valor_calidad_codigos` WRITE;
/*!40000 ALTER TABLE `valor_calidad_codigos` DISABLE KEYS */;
INSERT INTO `valor_calidad_codigos` VALUES (1,'baja','0',1),(2,'media','0.5',1),(3,'alta','1',1),(4,'no_corresponde','No Corresponde',1),(5,'pendiente','Pendiente de Cargar',1);
/*!40000 ALTER TABLE `valor_calidad_codigos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'siges_agesic'
--

--
-- Dumping routines for database 'siges_agesic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;





-- BRUNO: ver si la tabla revinfo está en mayúscula o minúscula.
--  Changeset db.changelog_siges_4410.xml::1494002716492-37::bruno (generated)
-- RENAME revinfo to REVINFO;
--  Changeset db.changelog_siges_4410.xml::1494002716492-1::bruno (generated)
-- CREATE TABLE REVINFO (REV INT AUTO_INCREMENT NOT NULL, REVTSTMP BIGINT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_REVINFO PRIMARY KEY (REV));

--  Changeset db.changelog_siges_4410.xml::1494002716492-2::bruno (generated)
CREATE TABLE aud_doc_file (docfile_pk INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, docfile_nombre VARCHAR(255) NULL, docfile_path VARCHAR(255) NULL, docfile_ult_mod datetime(6) NULL, docfile_ult_origen VARCHAR(255) NULL, docfile_ult_usuario VARCHAR(255) NULL, docfile_version INT NULL, docfile_doc_fk INT NULL);

--  Changeset db.changelog_siges_4410.xml::1494002716492-4::bruno (generated)
ALTER TABLE doc_file ADD docfile_path VARCHAR(255) NULL;

--  Changeset db.changelog_siges_4410.xml::1494002716492-5::bruno (generated)
ALTER TABLE doc_file ADD docfile_ult_mod datetime(6) NULL;

--  Changeset db.changelog_siges_4410.xml::1494002716492-6::bruno (generated)
ALTER TABLE doc_file ADD docfile_ult_origen VARCHAR(255) NULL;

--  Changeset db.changelog_siges_4410.xml::1494002716492-7::bruno (generated)
ALTER TABLE doc_file ADD docfile_ult_usuario VARCHAR(255) NULL;

--  Changeset db.changelog_siges_4410.xml::1494002716492-8::bruno (generated)
ALTER TABLE doc_file ADD docfile_version INT DEFAULT 0 NULL;

--  Changeset db.changelog_siges_4410.xml::1494002716492-9::bruno (generated)
ALTER TABLE programas ADD prog_factor_impacto TEXT NULL;

--  Changeset db.changelog_siges_4410.xml::1494002716492-10::bruno (generated)
ALTER TABLE proyectos ADD proy_factor_impacto TEXT NULL;

--  Changeset db.changelog_siges_4410.xml::1494002716492-11::bruno (generated)
ALTER TABLE proy_indices ADD proyind_periodo_inicio_ent_fk INT NULL;

--  Changeset db.changelog_siges_4410.xml::1494002716492-12::bruno (generated)
ALTER TABLE proy_indices ADD proyind_periodo_fin_ent_fk INT NULL;

--  Changeset db.changelog_siges_4410.xml::1494002716492-13::bruno (generated)
ALTER TABLE entregables ADD ent_inicio_periodo BIT DEFAULT 0 NOT NULL;

--  Changeset db.changelog_siges_4410.xml::1494002716492-14::bruno (generated)
ALTER TABLE entregables ADD ent_fin_periodo BIT DEFAULT 0 NOT NULL;

--  Changeset db.changelog_siges_4410.xml::1494002716492-15::bruno (generated)
ALTER TABLE aud_doc_file ADD PRIMARY KEY (docfile_pk, REV);

--  Changeset db.changelog_siges_4410.xml::1494002716492-16::bruno (generated)
ALTER TABLE doc_file ADD CONSTRAINT UK_n76rhuste8gi3p3jq7m91j7iq UNIQUE (docfile_doc_fk);

--  Changeset db.changelog_siges_4410.xml::1494002716492-17::bruno (generated)
CREATE INDEX FK_4b0pq8qh2f6u7ei11lh0atbf8 ON busq_filtro(busq_filtro_usu_fk);

--  Changeset db.changelog_siges_4410.xml::1494002716492-18::bruno (generated)
CREATE INDEX FK_9c8og633e1waprs81i6ayorba ON devengado(dev_adq_fk);

--  Changeset db.changelog_siges_4410.xml::1494002716492-19::bruno (generated)
CREATE INDEX FK_a726jrgroi6p90dip38n70ftp ON busq_filtro(busq_filtro_org_fk);

--  Changeset db.changelog_siges_4410.xml::1494002716492-20::bruno (generated)
CREATE INDEX FK_hb3weyc8xvdrbp62k3u1halnb ON categoria_proyectos(cat_icono_marker);

--  Changeset db.changelog_siges_4410.xml::1494002716492-21::bruno (generated)
CREATE INDEX FK_hh3lr9l8qt7isgvniyif3w6tw ON categoria_proyectos(cat_icono);

--  Changeset db.changelog_siges_4410.xml::1494002716492-22::bruno (generated)
CREATE INDEX FK_j151q3d1wiqgx10w9n92hwx5j ON areas(area_director);

--  Changeset db.changelog_siges_4410.xml::1494002716492-23::bruno (generated)
CREATE INDEX FK_n5tl58dqv18cs790jbmejxom2 ON ambito(amb_org_fk);

--  Changeset db.changelog_siges_4410.xml::1494002716492-24::bruno (generated)
CREATE INDEX proyind_periodo_fin_ent_fk_idx ON proy_indices(proyind_periodo_fin_ent_fk);

--  Changeset db.changelog_siges_4410.xml::1494002716492-25::bruno (generated)
CREATE INDEX proyind_periodo_inicio_ent_fk_idx ON proy_indices(proyind_periodo_inicio_ent_fk);

--  Changeset db.changelog_siges_4410.xml::1494002716492-26::bruno (generated)
ALTER TABLE busq_filtro ADD CONSTRAINT FK_4b0pq8qh2f6u7ei11lh0atbf8 FOREIGN KEY (busq_filtro_usu_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--  Changeset db.changelog_siges_4410.xml::1494002716492-27::bruno (generated)
ALTER TABLE categoria_proyectos ADD CONSTRAINT FK_4t53ltja1415bq3d23c3kdjl1 FOREIGN KEY (cat_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

--  Changeset db.changelog_siges_4410.xml::1494002716492-28::bruno (generated)
ALTER TABLE devengado ADD CONSTRAINT FK_9c8og633e1waprs81i6ayorba FOREIGN KEY (dev_adq_fk) REFERENCES adquisicion (adq_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

--  Changeset db.changelog_siges_4410.xml::1494002716492-29::bruno (generated)
ALTER TABLE busq_filtro ADD CONSTRAINT FK_a726jrgroi6p90dip38n70ftp FOREIGN KEY (busq_filtro_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

--  Changeset db.changelog_siges_4410.xml::1494002716492-30::bruno (generated)
ALTER TABLE categoria_proyectos ADD CONSTRAINT FK_hb3weyc8xvdrbp62k3u1halnb FOREIGN KEY (cat_icono_marker) REFERENCES image (image_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

--  Changeset db.changelog_siges_4410.xml::1494002716492-31::bruno (generated)
ALTER TABLE categoria_proyectos ADD CONSTRAINT FK_hh3lr9l8qt7isgvniyif3w6tw FOREIGN KEY (cat_icono) REFERENCES image (image_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

--  Changeset db.changelog_siges_4410.xml::1494002716492-32::bruno (generated)
ALTER TABLE areas ADD CONSTRAINT FK_j151q3d1wiqgx10w9n92hwx5j FOREIGN KEY (area_director) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--  Changeset db.changelog_siges_4410.xml::1494002716492-33::bruno (generated)
ALTER TABLE ambito ADD CONSTRAINT FK_n5tl58dqv18cs790jbmejxom2 FOREIGN KEY (amb_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

--  Changeset db.changelog_siges_4410.xml::1494002716492-34::bruno (generated)
ALTER TABLE doc_file ADD CONSTRAINT FK_n76rhuste8gi3p3jq7m91j7iq FOREIGN KEY (docfile_doc_fk) REFERENCES documentos (docs_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

--  Changeset db.changelog_siges_4410.xml::1494002716492-35::bruno (generated)
ALTER TABLE proy_indices ADD CONSTRAINT proyind_periodo_fin_ent_fk FOREIGN KEY (proyind_periodo_fin_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

--  Changeset db.changelog_siges_4410.xml::1494002716492-36::bruno (generated)
ALTER TABLE proy_indices ADD CONSTRAINT proyind_periodo_inicio_ent_fk FOREIGN KEY (proyind_periodo_inicio_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

--  Changeset db.changelog_siges_4410.xml::1494002716492-38::bruno (generated)
-- ALTER TABLE doc_file DROP COLUMN version;

-- Especificar directorio donde se desean almacenar los documentos
INSERT INTO ss_configuraciones (cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_ult_origen, cnf_version) VALUES
(NULL, 'DOCUMENTOS_DIR', 'Directorio donde se almacenarán los documentos', '/srv/siges/docs', NULL, '0', NULL, now(), NULL, '0');


-- Luego de ejecutar la migración de documentos

-- Se recomienda hacer un respaldo de esta tabla antes de aplicar está acción.
ALTER TABLE doc_file DROP COLUMN docfile_file;
DELETE FROM aud_doc_file; -- limpia la auditoría de la migración



