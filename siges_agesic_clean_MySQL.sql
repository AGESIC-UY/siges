-- MySQL dump 10.13  Distrib 5.6.45, for Linux (x86_64)
--
-- Host: localhost    Database: siges
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
INSERT INTO `DATABASECHANGELOG` VALUES ('1535738429301-2','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:32',2,'EXECUTED','7:d886d5ae5de4e9f89a9ae1a70133fda3','addColumn tableName=procedimiento_compra','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-3','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:32',3,'EXECUTED','7:7e50b9356284560fbdde9251a02999af','addColumn tableName=plantilla_entregables','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-4','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:32',4,'EXECUTED','7:8c12ca2a7732ac96137dc03714a0d6f8','addColumn tableName=plantilla_entregables','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-5','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:32',5,'EXECUTED','7:e581b9f979a87020d8cc7c07d5b1e282','addColumn tableName=documentos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-6','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:33',6,'EXECUTED','7:a16bf39f67e717894fb126070bbaf3cd','addColumn tableName=documentos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-7','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:33',7,'EXECUTED','7:c0102e6b85d8ce715b21657966574a89','addColumn tableName=documentos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-8','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:33',8,'EXECUTED','7:82f367ca18eeaf2b909758656001e676','addColumn tableName=prog_indices','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-9','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:33',9,'EXECUTED','7:3d2adcd81f2f529bd28e9009a2c3eba2','addColumn tableName=proy_indices','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-10','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:33',10,'EXECUTED','7:c7067c4984276ca95274391dc8cb5826','addColumn tableName=programas','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-11','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:34',11,'EXECUTED','7:b00c1c4927bceadcc9a3f3767ec271aa','addColumn tableName=proyectos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-12','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:34',12,'EXECUTED','7:bf1eaf26927752775b74936637142d8d','addUniqueConstraint constraintName=usu_ldap_user_UNIQUE, tableName=ss_usuario','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-13','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:34',13,'EXECUTED','7:da7325b17945c27069d52deb8676571d','createIndex indexName=docs_docfile_fk_idx, tableName=documentos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-14','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:34',14,'EXECUTED','7:80e21b3b9d869d572c5a6db353dd72e8','createIndex indexName=enthist_replan_fk_idx, tableName=ent_hist_linea_base','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-15','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:34',15,'EXECUTED','7:551543684a1eb4fd1c43ef149e084caf','createIndex indexName=eta_org_fk_cod_idx, tableName=etapa','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-16','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:34',16,'EXECUTED','7:1cd52696c9c0fbb761d9c751bc88a8b7','createIndex indexName=notificacion_envio_proyectos_FK, tableName=notificacion_envio','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-17','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:35',17,'EXECUTED','7:a44b99b852d546afc455a6e4b9abf793','createIndex indexName=notificacion_not_org_fk_IDX, tableName=notificacion','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-18','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:35',18,'EXECUTED','7:a930d8a59ef6798551a16a376198de1c','createIndex indexName=plantilla_cronograma_activo_IDX, tableName=plantilla_cronograma','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-19','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:35',19,'EXECUTED','7:537c6e5ae9ba6309d2415595795b2707','createIndex indexName=plantilla_cronograma_organismos_FK, tableName=plantilla_cronograma','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-20','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:35',20,'EXECUTED','7:20c9f0ef30cfe236cdbd79c9f6aa984a','createIndex indexName=procedimiento_compra_proc_comp_habilitado_IDX, tableName=procedimiento_compra','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-21','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:35',21,'EXECUTED','7:ffd6aa284373dea19af18c4c92a7976b','createIndex indexName=prog_indices_pre_moneda_FK, tableName=prog_indices_pre','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-22','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:35',22,'EXECUTED','7:7919403b4f674c644fa712f010d6875d','createIndex indexName=prog_indices_pre_prog_indices_FK, tableName=prog_indices_pre','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-23','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:35',23,'EXECUTED','7:4753440fd2d9169b6ca7170a8172d395','createIndex indexName=proy_indices_pre_moneda_FK, tableName=proy_indices_pre','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-24','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:35',24,'EXECUTED','7:769dec79866a11837df120025265452a','createIndex indexName=proy_indices_pre_proy_indices_FK, tableName=proy_indices_pre','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-25','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:35',25,'EXECUTED','7:1f2d93666fcdb9afb8c5d24b4113ab2b','createIndex indexName=proy_sitact_historico_proy_sitact_usu_fk_IDX, tableName=proy_sitact_historico','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-26','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:35',26,'EXECUTED','7:cb0211709c5dfccdd2c71898b17039cf','createIndex indexName=ss_usuarios_datos_ss_usu_dat_area_fk_IDX, tableName=ss_usuarios_datos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-27','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:35',27,'EXECUTED','7:5f7323f8f2d4b8d88450e86b16dba811','createIndex indexName=ss_usuarios_datos_ss_usu_dat_usu_fk_IDX, tableName=ss_usuarios_datos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-28','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:35',28,'EXECUTED','7:7773b8c63b0f2f924a999e60aebc9d10','createIndex indexName=tipo_documento_instancia_estados_FK, tableName=tipo_documento_instancia','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-29','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:35',29,'EXECUTED','7:44e1eaf824e37d0d52fa6e658ac4c4c2','createIndex indexName=tipo_documento_instancia_programas_FK, tableName=tipo_documento_instancia','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-30','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:35',30,'EXECUTED','7:a0b2df1718ddcd2a0dc9284369951290','createIndex indexName=tipo_documento_instancia_proyectos_FK, tableName=tipo_documento_instancia','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-31','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:35',31,'EXECUTED','7:16389220e003dd5dee3aa46a3150bbf9','addForeignKeyConstraint baseTableName=componente_producto, constraintName=componente_producto_organismos_FK, referencedTableName=organismos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-32','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:36',32,'EXECUTED','7:468915315e75ba6f5569ffe1f2fbdeed','addForeignKeyConstraint baseTableName=documentos, constraintName=docs_docfile_fk, referencedTableName=doc_file','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-33','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:37',33,'EXECUTED','7:1f8ab8b4abd1f588e7e15d6df549f1ee','addForeignKeyConstraint baseTableName=ent_hist_linea_base, constraintName=enthist_replan_fk, referencedTableName=proy_replanificacion','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-34','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:37',34,'EXECUTED','7:86e54501763bcaa9c6d93d331b4ef627','addForeignKeyConstraint baseTableName=proyectos, constraintName=fk_PROYECTOS_USUARIOS5, referencedTableName=ss_usuario','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-36','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:37',36,'EXECUTED','7:01aedac2a7322a5d202461cb52798230','addForeignKeyConstraint baseTableName=notificacion_envio, constraintName=notificacion_envio_proyectos_FK, referencedTableName=proyectos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-37','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:37',37,'EXECUTED','7:3c6cce04fdeb53009b00164ecf641393','addForeignKeyConstraint baseTableName=notificacion, constraintName=notificacion_organismos_FK, referencedTableName=organismos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-38','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:38',38,'EXECUTED','7:37ae54ffa34f36d91f182e31796009e8','addForeignKeyConstraint baseTableName=personas, constraintName=personas_roles_interesados_FK, referencedTableName=roles_interesados','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-39','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:38',39,'EXECUTED','7:ac81db0fc094b1885fa86bb16c6be870','addForeignKeyConstraint baseTableName=plantilla_cronograma, constraintName=plantilla_cronograma_organismos_FK, referencedTableName=organismos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-40','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:38',40,'EXECUTED','7:3bca11e0003a4b47d9e3abe7d7f16903','addForeignKeyConstraint baseTableName=procedimiento_compra, constraintName=procedimiento_compra_organismos_FK, referencedTableName=organismos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-41','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:38',41,'EXECUTED','7:3071220ea9fae5d303bdb212fa49afb8','addForeignKeyConstraint baseTableName=prog_indices_pre, constraintName=prog_indices_pre_moneda_FK, referencedTableName=moneda','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-42','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:38',42,'EXECUTED','7:bd8d4bccc6fad880b8bc6d43a5e43c1b','addForeignKeyConstraint baseTableName=prog_indices_pre, constraintName=prog_indices_pre_prog_indices_FK, referencedTableName=prog_indices','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-43','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:38',43,'EXECUTED','7:64294bb9ad30ca51396f93f6abdf44b4','addForeignKeyConstraint baseTableName=proy_indices_pre, constraintName=proy_indices_pre_moneda_FK, referencedTableName=moneda','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-44','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:39',44,'EXECUTED','7:bfc182375efc98e0b72c0369ee70dd89','addForeignKeyConstraint baseTableName=proy_sitact_historico, constraintName=proy_sitact_historico_ss_usuario_FK, referencedTableName=ss_usuario','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-45','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:39',45,'EXECUTED','7:20fac8a2bce4837eed73fc685631eb81','addForeignKeyConstraint baseTableName=riesgos, constraintName=riesgos_proyectos_FK, referencedTableName=proyectos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-46','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:39',46,'EXECUTED','7:6365f0e8e1664a9a7b5e39872b2ae02f','addForeignKeyConstraint baseTableName=ss_configuraciones, constraintName=ss_configuraciones_organismos_FK, referencedTableName=organismos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-47','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:39',47,'EXECUTED','7:48a2b22d23b536555d8659fcc8bce6b3','addForeignKeyConstraint baseTableName=ss_usuarios_datos, constraintName=ss_usuarios_datos_areas_FK, referencedTableName=areas','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-48','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:39',48,'EXECUTED','7:30d57f61cebab9aad33b1b1ccd374ebb','addForeignKeyConstraint baseTableName=ss_usuarios_datos, constraintName=ss_usuarios_datos_ss_usuario_FK, referencedTableName=ss_usuario','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-49','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:40',49,'EXECUTED','7:0b934e89028b85470b462680e8291acf','addForeignKeyConstraint baseTableName=tipo_documento_instancia, constraintName=tipo_documento_instancia_estados_FK, referencedTableName=estados','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-50','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:41',50,'EXECUTED','7:0475c768ae96fe069647caeac64f6315','addForeignKeyConstraint baseTableName=tipo_documento_instancia, constraintName=tipo_documento_instancia_programas_FK, referencedTableName=programas','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-51','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:42',51,'EXECUTED','7:932c3e86a8decd0b147b33b287e26a46','addForeignKeyConstraint baseTableName=tipo_documento_instancia, constraintName=tipo_documento_instancia_proyectos_FK, referencedTableName=proyectos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-52','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:42',52,'EXECUTED','7:dd0908eefd3a80bb50c79735390b9539','dropForeignKeyConstraint baseTableName=proyectos, constraintName=proy_latlng_fk','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-54','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:42',54,'EXECUTED','7:92270d159fabf6be5a6cd51684fd4d07','dropTable tableName=categoria_proyectos_BORRAR','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-55','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:42',55,'EXECUTED','7:dcb10a1b572643865dccf31d58e7e45f','dropTable tableName=proyectos_BORRAR','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-62','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:42',62,'EXECUTED','7:3e913ac4642ba30f7edd0e2c7aaaffc4','modifyDataType columnName=cat_proy_codigo, tableName=categoria_proyectos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-63','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:42',63,'EXECUTED','7:f33eae76288e14ed8e146918bd0f4835','modifyDataType columnName=com_nombre, tableName=componente_producto','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-64','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:43',64,'EXECUTED','7:da6efa6017949882582fd41608ced28d','modifyDataType columnName=docs_fecha, tableName=documentos; dropNotNullConstraint columnName=docs_fecha, tableName=documentos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-65','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:43',65,'EXECUTED','7:88506581d64a8811c07971d7368eee61','dropNotNullConstraint columnName=media_tipo_fk, tableName=media_proyectos; dropDefaultValue columnName=media_tipo_fk, tableName=media_proyectos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-66','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:43',66,'EXECUTED','7:11387a04945c6ae9d58d274f732e8e70','modifyDataType columnName=prog_activo, tableName=programas; addNotNullConstraint columnName=prog_activo, tableName=programas; addDefaultValue columnName=prog_activo, tableName=programas','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-67','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:44',67,'EXECUTED','7:7c76f3f257cf348402410516c5271f1b','modifyDataType columnName=proy_fecha_act, tableName=proyectos; dropNotNullConstraint columnName=proy_fecha_act, tableName=proyectos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-68','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:45',68,'EXECUTED','7:8d83a771dac618d8dd6208923d22daf6','modifyDataType columnName=proy_fecha_crea, tableName=proyectos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-69','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:46',69,'EXECUTED','7:8e5148c9e10b866db3848e4242f70b5e','modifyDataType columnName=proy_fecha_est_act, tableName=proyectos','',NULL,'3.5.3',NULL,NULL,'5738581767'),('1535738429301-70','sofis','db.changelog_siges_v5.2.18.sql','2018-09-05 18:19:47',70,'EXECUTED','7:89240595f110fd8791e8207b39eb2498','modifyDataType columnName=proy_ult_mod, tableName=proyectos','',NULL,'3.5.3',NULL,NULL,'5738581767');
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
INSERT INTO `areas` VALUES (350,45,NULL,'Dirección General','DG',NULL,0,1,1),(352,47,NULL,'Presidencia','PR',NULL,0,1,1),(353,47,NULL,'Secretaría General','SG',NULL,0,1,1);
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
INSERT INTO `aud_ss_oficina` VALUES (46,2,1,'2019-11-21 14:54:30','Presidencia','',1,1,1),(47,4,1,NULL,NULL,NULL,0,NULL,NULL),(47,5,1,'2019-11-22 09:31:28','Presidencia','',1,1,0),(47,7,1,NULL,NULL,NULL,0,NULL,NULL),(47,9,1,NULL,NULL,NULL,0,NULL,NULL),(47,12,1,NULL,NULL,NULL,0,NULL,NULL),(47,14,1,NULL,NULL,NULL,0,NULL,NULL);
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
INSERT INTO `aud_ss_usu_ofi_roles` VALUES (3453,2,0,'Presidencia',0,46,98,1285,0,1),(3453,3,1,'Presidencia',0,46,98,1285,0,0),(3454,4,0,'Presidencia',0,47,98,1286,0,1),(3454,5,2,NULL,NULL,NULL,NULL,NULL,0,NULL),(3455,5,0,'Presidencia',0,47,98,1286,0,1),(3455,8,1,'Presidencia',0,47,98,1286,0,0),(3455,10,1,'Presidencia',0,47,98,1286,0,1),(3456,7,0,'Presidencia',0,47,97,1287,0,1),(3457,9,0,'Presidencia',0,47,97,1288,0,1),(3458,12,0,'Presidencia',0,47,100,1289,0,1),(3459,14,0,'Presidencia',0,47,93,1290,0,1);
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
INSERT INTO `aud_ss_usuario` VALUES (1285,2,0,NULL,NULL,'','usuario.pmot@siges.com',0,'',NULL,'2019-11-21 15:25:00',NULL,NULL,NULL,NULL,46,NULL,'0d6d6f72dddb9e16b95ddfd8bb3a4d3d','PMOT','Usuario',NULL,NULL,NULL,'',0,NULL,0,1,0,'admin','2019-11-21 15:54:30',NULL,NULL,NULL,NULL,'',NULL),(1285,3,1,NULL,NULL,'','usuario.pmot@siges.com',0,'',NULL,'2019-11-21 15:25:00',NULL,NULL,NULL,NULL,46,NULL,'0d6d6f72dddb9e16b95ddfd8bb3a4d3d','PMOT','Usuario',NULL,NULL,NULL,'',0,NULL,1,1,0,'admin','2019-11-21 15:59:29',NULL,NULL,NULL,NULL,'',NULL),(1286,4,0,NULL,NULL,'','usuario.pmot@siges.com',0,'',NULL,'2019-11-21 16:21:12',NULL,NULL,NULL,NULL,47,NULL,'38c940333cf41a13eaa43511951a4737','PMOT','Usuario',NULL,NULL,NULL,'',0,NULL,0,1,0,'admin','2019-11-21 16:21:12',NULL,NULL,NULL,NULL,'',NULL),(1286,5,1,NULL,NULL,'','usuario.pmot@siges.com',0,'',NULL,'2019-11-21 16:21:12',NULL,NULL,NULL,NULL,47,NULL,'38c940333cf41a13eaa43511951a4737','PMOT','Usuario',NULL,NULL,NULL,'',0,NULL,1,1,0,'admin','2019-11-22 10:31:28',NULL,NULL,NULL,NULL,'',NULL),(1286,6,1,NULL,NULL,'','usuario.pmot@siges.com',0,'',NULL,'2019-11-22 11:08:14',NULL,NULL,NULL,NULL,47,NULL,'30e22097b4e4271e06c04e56b1d0ad6f','PMOT','Usuario',NULL,NULL,NULL,'',0,NULL,3,1,0,'usuario.pmot@siges.com','2019-11-22 11:08:14',NULL,NULL,NULL,NULL,'',NULL),(1286,8,1,NULL,NULL,'','usuario.pmot@siges.com',0,'',NULL,'2019-11-22 11:08:14',NULL,NULL,NULL,NULL,47,NULL,'30e22097b4e4271e06c04e56b1d0ad6f','PMOT','Usuario',NULL,NULL,NULL,'',0,NULL,4,1,0,'usuario.pmot@siges.com','2019-11-22 11:12:30',NULL,NULL,NULL,NULL,'',NULL),(1286,10,1,NULL,NULL,'','usuario.pmot@siges.com',0,'',NULL,'2019-11-22 11:08:14',NULL,NULL,NULL,NULL,47,NULL,'30e22097b4e4271e06c04e56b1d0ad6f','PMOT','Usuario',NULL,NULL,NULL,'',0,NULL,5,1,0,'admin','2019-11-22 11:34:28',NULL,NULL,NULL,NULL,'',NULL),(1287,7,0,NULL,NULL,'','usuario.pmof@siges.com',0,'',NULL,'2019-11-22 11:09:29',NULL,NULL,NULL,NULL,47,NULL,'6a5f8b1a29942bf2a0625f3f5291d128','PMOF','Usuario',NULL,NULL,NULL,'',0,NULL,0,1,0,'usuario.pmot@siges.com','2019-11-22 11:09:29',NULL,NULL,NULL,NULL,'',NULL),(1288,9,0,NULL,NULL,'','usuario.pmof@siges.com',0,'',NULL,'2019-11-22 11:34:06',NULL,NULL,NULL,NULL,47,NULL,'f123068cdef8e0a8bf1c2f4e9152efc9','PMOF','Usuario',NULL,NULL,NULL,'',0,NULL,0,1,0,'admin','2019-11-22 11:34:06',NULL,NULL,NULL,NULL,'',NULL),(1288,11,1,NULL,NULL,'','usuario.pmof@siges.com',0,'',NULL,'2019-11-22 11:41:02',NULL,NULL,NULL,NULL,47,NULL,'30e22097b4e4271e06c04e56b1d0ad6f','PMOF','Usuario',NULL,NULL,NULL,'',0,NULL,2,1,0,'usuario.pmof@siges.com','2019-11-22 11:41:02',NULL,NULL,NULL,NULL,'',NULL),(1289,12,0,NULL,NULL,'','usuario@siges.com',0,'',NULL,'2019-11-22 11:43:08',NULL,NULL,NULL,NULL,47,NULL,'305d1ceb92e1dc95233a92693ecae462','Común','Usuario',NULL,NULL,NULL,'',0,NULL,0,1,0,'usuario.pmot@siges.com','2019-11-22 11:43:08',NULL,NULL,NULL,NULL,'',NULL),(1289,13,1,NULL,NULL,'','usuario@siges.com',0,'',NULL,'2019-11-22 11:43:43',NULL,NULL,NULL,NULL,47,NULL,'30e22097b4e4271e06c04e56b1d0ad6f','Común','Usuario',NULL,NULL,NULL,'',0,NULL,2,1,0,'usuario@siges.com','2019-11-22 11:43:43',NULL,NULL,NULL,NULL,'',NULL),(1290,14,0,NULL,NULL,'','usuario.director@siges.com',0,'',NULL,'2019-11-22 11:44:35',NULL,NULL,NULL,NULL,47,NULL,'c7b442bedd4cbb0f3f748685fe851ead','Director','Usuario',NULL,NULL,NULL,'',0,NULL,0,1,0,'usuario.pmot@siges.com','2019-11-22 11:44:35',NULL,NULL,NULL,NULL,'',NULL),(1290,15,1,NULL,NULL,'','usuario.director@siges.com',0,'',NULL,'2019-11-22 11:45:18',NULL,NULL,NULL,NULL,47,NULL,'30e22097b4e4271e06c04e56b1d0ad6f','Director','Usuario',NULL,NULL,NULL,'',0,NULL,2,1,0,'usuario.director@siges.com','2019-11-22 11:45:18',NULL,NULL,NULL,NULL,'',NULL);
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
INSERT INTO `busq_filtro` VALUES (1,1286,47,'<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<filtroInicioTO>\n    <nivel>1</nivel>\n    <porArea>false</porArea>\n    <nombre></nombre>\n    <interesadoNombre></interesadoNombre>\n    <estados xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">2</estados>\n    <estados xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">3</estados>\n    <estados xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">4</estados>\n    <estados xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">1</estados>\n    <anioDesde>0</anioDesde>\n    <anioHasta>0</anioHasta>\n    <gradoRiesgo xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">1</gradoRiesgo>\n    <gradoRiesgo xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">2</gradoRiesgo>\n    <gradoRiesgo xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">3</gradoRiesgo>\n    <cantidadRiesgosAltos>0</cantidadRiesgosAltos>\n    <indicadorAvance xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">1</indicadorAvance>\n    <indicadorAvance xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"xs:string\">2</indicadorAvance>\n    <activo>true</activo>\n    <codigo>0</codigo>\n    <orderBy>2</orderBy>\n    <configuracion>\n        <entry>\n            <key>CALIDAD_LIMITE_AMARILLO</key>\n            <value>\n                <cnfCodigo>CALIDAD_LIMITE_AMARILLO</cnfCodigo>\n                <cnfDescripcion>Semaforo limite amarillo calidad</cnfDescripcion>\n                <cnfOrgFk>\n                    <orgPk>47</orgPk>\n                    <orgNombre>Presidencia</orgNombre>\n                    <orgDireccion>Torre Ejecutiva</orgDireccion>\n                    <orgActivo>true</orgActivo>\n                    <orgToken>visua</orgToken>\n                </cnfOrgFk>\n                <cnfUltMod>2019-11-21T16:20:43-02:00</cnfUltMod>\n                <cnfValor>70</cnfValor>\n                <cnfVersion>1</cnfVersion>\n                <id>2790</id>\n            </value>\n        </entry>\n        <entry>\n            <key>RIESGO_INDICE_LIMITE_AMARILLO</key>\n            <value>\n                <cnfCodigo>RIESGO_INDICE_LIMITE_AMARILLO</cnfCodigo>\n                <cnfDescripcion></cnfDescripcion>\n                <cnfOrgFk>\n                    <orgPk>47</orgPk>\n                    <orgNombre>Presidencia</orgNombre>\n                    <orgDireccion>Torre Ejecutiva</orgDireccion>\n                    <orgActivo>true</orgActivo>\n                    <orgToken>visua</orgToken>\n                </cnfOrgFk>\n                <cnfUltMod>2019-11-21T16:20:43-02:00</cnfUltMod>\n                <cnfValor>1.2</cnfValor>\n                <cnfVersion>1</cnfVersion>\n                <id>2767</id>\n            </value>\n        </entry>\n        <entry>\n            <key>RIESGO_INDICE_LIMITE_ROJO</key>\n            <value>\n                <cnfCodigo>RIESGO_INDICE_LIMITE_ROJO</cnfCodigo>\n                <cnfDescripcion></cnfDescripcion>\n                <cnfOrgFk>\n                    <orgPk>47</orgPk>\n                    <orgNombre>Presidencia</orgNombre>\n                    <orgDireccion>Torre Ejecutiva</orgDireccion>\n                    <orgActivo>true</orgActivo>\n                    <orgToken>visua</orgToken>\n                </cnfOrgFk>\n                <cnfUltMod>2019-11-21T16:20:43-02:00</cnfUltMod>\n                <cnfValor>4</cnfValor>\n                <cnfVersion>1</cnfVersion>\n                <id>2768</id>\n            </value>\n        </entry>\n        <entry>\n            <key>CALIDAD_LIMITE_ROJO</key>\n            <value>\n                <cnfCodigo>CALIDAD_LIMITE_ROJO</cnfCodigo>\n                <cnfDescripcion>Semaforo limite rojo calidad</cnfDescripcion>\n                <cnfOrgFk>\n                    <orgPk>47</orgPk>\n                    <orgNombre>Presidencia</orgNombre>\n                    <orgDireccion>Torre Ejecutiva</orgDireccion>\n                    <orgActivo>true</orgActivo>\n                    <orgToken>visua</orgToken>\n                </cnfOrgFk>\n                <cnfUltMod>2019-11-21T16:20:43-02:00</cnfUltMod>\n                <cnfValor>30</cnfValor>\n                <cnfVersion>1</cnfVersion>\n                <id>2791</id>\n            </value>\n        </entry>\n    </configuracion>\n</filtroInicioTO>\n',0);
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
INSERT INTO `organismos` VALUES (45,'Administración SIGES','logo-siges.png','Torre Ejecutiva Sur, Liniers 1324 piso 4','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0�\0\0\0I\0\0\0�-S\0\0\0	pHYs\0\0\0\0\0��\0\0\0tEXtSoftware\0Adobe ImageReadyq�e<\0\0�IDATx��]���85�&w��e�Bs�=A�Мv���B;�!�}�Jl����lI�z�ϏƲ�*�~�$T���t�\0\0�O�\0\0�\0\0d\0\0d\0�\r\0@6\0\0@6\0\0�\0\0\0�\0\0d\0�\r\0\0�\r\0@6\0\0@6\0\0�\0\0d\0\0d\0�\r\0\0�\r\0@6\0���M����\\}L�Q��TGCG�������Rg�>�,�~\Z�n�����GAm�{�U�ʧ�T��i\n�\0 9و2BCm�Ku�Tes32vM�\\M�s��ц?Ki�C�vѩ��Q���~����}�����*SU^�r]��8u����:�Ar����}��le���B�X?S�;9�Y�L��\r��A�z����t���>ت��OZ�%�-�܎��|v�l�ܧT�$~�oT,����é�|V�ȶ����h�Q���Aՙ��]��,ӄN�W�<�T��PZ�$�30Ƴ���qdN\"˲B�ԝ_ۥk!bai+w���}_�>��Hg~�q6��}z��9�8�B�����hӭp��ߜMث��e���3!r��jC�&�Zɢȋ0�3ԃ��bs����p�s�E&���2���:��E��Ig��+2J-gA��LB�<���۳��%��&��W���C]Ѽ�<Pf�>�_X^{�1�cf�����Z�iG;�ts��ٴ���<�xjW��ι-n 1d�x�kP�V �Ԏ�����0�:�;Z��9AS�/�l���lR�P�!�n(t^<D\"�w���[�����Ƹ��@R�&��6CE�9���#��ʃ�.،�x�y�)ɶ� ��b3}�U>9XY�(Ҵx�o�۬���#���\r���^#I������f�V�̫B�����JZ`i<�geH���(�	Ae��3N��Nt�,eVK��E�ƧB�L�����\nr��K���?ha�I�dk��-�yQ� ��h)�G��z��#2�=!뚯�X��Z��1A�Щԫ�D��NT7��w�����CZ�r!ɣ^K������A�<	�\"ے�*����D���}B#�7�q��S�7Yӏ��@��0=x��uc���Nm{\\\\�1�<Ed�yTӓ��kn8�K�*�3|\"C�Z�\r�$\\O$T7��z=\r��YYi)�>7�	�|�Tr�>m\\}3DtՍ/��Vv�pJ��㰭8\r��������b�>&�����^|�,e��f�)�X)�T�>�F:���[����`k�ʱ�y5�t{g}9� ��58�49\\�I݆�mA�o<�󖇸�Sgk�xme�!��\Z��!T.�u]�m�p��ǌ�`���H�6���������9����93�0�\"�ts�G���*�����+�l�Ku��o+�Htp�d�߂�k�#F7\0H��-�x�!e@������o��(�ӡ�Ͳ��zG~,qF���^7�\"�Vef1J=ǚ�*_\n��!ߨm8�Q{�͒�f&�z\\߰|\'�\r�z�h�3�Q��h��ٗ��4\'1�z�����ι��tY����¬9�_j)��1�Z>���Q7[.�+[���lkC�o�������!sֶ+k�qN��[�J�E�<��I5m�leKJ6��d3î��?��� \\��ю�cU��1�[u�a����[2K�i�\"���%�69�Qg�yd.�:��|s�lʸ�ƶu,*�=n�[�}��� $�7ת���\"�OqYcsb�&��E���y��+�;�u��/n),��>s��mD>��\r#��@!\"\\�����:\0W��+ٚ�1�c2t����������o�ba��|t�m���J��囇E+��l[{|��\0ѭ3\'�Q�&=���1$c��+$E��Yw�pt����;�nC�o�\nEW�g���	�|�I*��:\'��9�^���׺9\r8dk�~�j��K���r��c�m�$Ik��]��g��(�Pe��sq�$��|T+G�a�svӁI��z�R�\"��Wp��>yX&��i�Y�z�vG9��-��<&�z\\���r�:OI��ԫn>:��lx��.��b(9d^�9��A6�`u����!<�l�_����P[Uv���%c��~����,R�\\�a#�w\n���僒\rkG,��	�xw-�x�u�>�M\"Ԙ���G�w����A���[\"@͌�̉r�6\Z�\\lC�ݞ����Q�\"�����bq��,�����Q��e��� �?�%��{#���\0@�s6\0\0@6\0\0�\0\0\0�\0\0d\0�\r\0\0�\r\0@6\0\0@6\0\0�\0\0d\0\0d\0�\r\0\0�\r\0@6\0\0�\0\0\0�\0\0d\0\0d\0�\r\0@6\0\0z¿\0{\nH����\0\0\0\0IEND�B`�',1,'1234',0),(47,'Presidencia',NULL,'Torre Ejecutiva',NULL,1,'visua',0);
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
INSERT INTO `proyectos` VALUES (2,47,2,NULL,NULL,352,1289,1290,1289,1288,NULL,NULL,3,2,2,0,'<p> <span>Existen 63 sitios diferentes en la Administración Central, con estructura heterogénea que dificultan el acceso homogeinizado a contenidos, incrementando costos operativos y generando una experiencia de usuario confusa. Se propone disponibilizar un <em><strong>Portal del Estado Único, promoviendo la armonía en los mensajes y el vínculo cercano del Estado con las personas.</strong></em></span></p>','<p> <span>Disponer de un Portal del Estado único para comunicar, informar y acercarse a las personas; con una imagen de gobierno unificada, con una comunicación integrada, y centralizando la infraestructura tecnológica.</span></p> \n<p> <span>Que esté integrado por todas las unidades y programas de Presidencia de la República y cada uno de los Ministerios.</span></p> \n<p> <br> <span><strong><em>Contribuye a:</em></strong><br> - Potenciar la Comunicación de Gobierno<br> - Facilitar la interacción de las personas con el Gobierno<br> - Racionalizar recursos y costos</span></p>','<ul> \n <li> Publicar indicadores sobre la evolución de la eficiencia energética en diferentes sectores de actividad en formato de datos abiertos.</li> \n <li> Desarrollar y publicación de un mapa nacional donde se georeferencien los distintos proyectos de eficiencia energética, los instrumentos de promoción que utilizaron y su localización en el territorio nacional.</li> \n</ul>','<p> - Se actualiza Drupal a nueva versión</p> \n<p> - Hoy se decide subir MRREE</p>',NULL,'Portal Único del Estado',NULL,10,20,1,'2019-11-22 15:42:08.000000','2019-11-22 16:39:40.000000',NULL,NULL,NULL,NULL,NULL,NULL,0,2,NULL,12,'<p> <span><em><strong>El Estado</strong></em>: Incorporando una estrategia de comunicación y una vía tecnológica que le permita organizar y homogeinizar su comunicación con la ciudadanía.</span></p> \n<p> <span><em><strong>El ciudadano:</strong></em> Que contará con una herramienta tecnológica moderna y eficiente.</span></p>','2019-11-22 16:39:40.000000');
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
INSERT INTO `revinfo` VALUES (2,1574358870689,0),(3,1574359169247,0),(4,1574360472961,0),(5,1574425889061,0),(6,1574428094681,0),(7,1574428169794,0),(8,1574428350589,0),(9,1574429646783,0),(10,1574429668757,0),(11,1574430062757,0),(12,1574430189525,0),(13,1574430223081,0),(14,1574430276138,0),(15,1574430318352,0);
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
INSERT INTO `ss_usu_ofi_roles` VALUES (3449,'MIGRACION',2,NULL,91,1281,350,1,0),(3455,'Presidencia',0,47,98,1286,352,1,0),(3457,'Presidencia',0,47,97,1288,352,1,0),(3458,'Presidencia',0,47,100,1289,352,1,0),(3459,'Presidencia',0,47,93,1290,352,1,0);
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

-- Dump completed on 2019-11-22 18:56:59
