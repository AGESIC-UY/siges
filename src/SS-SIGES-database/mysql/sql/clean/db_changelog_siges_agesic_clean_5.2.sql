--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: db.changelog_siges_clean_5.2.xml
--  Ran at: 28/05/18 10:44
--  Against: root@172.28.0.1@jdbc:mysql://localhost:3307/para_clean_Siges_v5_2
--  Liquibase version: 3.5.3
--  *********************************************************************

--  Create Database Lock Table
CREATE TABLE DATABASECHANGELOGLOCK (ID INT NOT NULL, LOCKED BIT(1) NOT NULL, LOCKGRANTED datetime NULL, LOCKEDBY VARCHAR(255) NULL, CONSTRAINT PK_DATABASECHANGELOGLOCK PRIMARY KEY (ID));

--  Initialize Database Lock Table
DELETE FROM DATABASECHANGELOGLOCK;

INSERT INTO DATABASECHANGELOGLOCK (ID, LOCKED) VALUES (1, 0);

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = 'sofis (locahost)', LOCKGRANTED = '2018-05-28 10:44:11.740' WHERE ID = 1 AND LOCKED = 0;

--  Create Database Change Log Table
CREATE TABLE DATABASECHANGELOG (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED datetime NOT NULL, ORDEREXECUTED INT NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35) NULL, DESCRIPTION VARCHAR(255) NULL, COMMENTS VARCHAR(255) NULL, TAG VARCHAR(255) NULL, LIQUIBASE VARCHAR(20) NULL, CONTEXTS VARCHAR(255) NULL, LABELS VARCHAR(255) NULL, DEPLOYMENT_ID VARCHAR(10) NULL);

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-1::sofis
CREATE TABLE REVINFO (REV INT AUTO_INCREMENT NOT NULL, REVTSTMP BIGINT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_REVINFO PRIMARY KEY (REV));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-1', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 1, '7:55187f4ddbdd4fa8e6d7db3aded50858', 'createTable tableName=REVINFO', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-2::sofis
CREATE TABLE REVINFO2 (REV TINYINT(3) NOT NULL, REVTSTMP TINYINT(3) NOT NULL, version TINYINT(3) NOT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-2', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 2, '7:2778a021f188dfdb28b0fda92ced9445', 'createTable tableName=REVINFO2', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-3::sofis
CREATE TABLE adquisicion (adq_pk INT AUTO_INCREMENT NOT NULL, adq_pre_fk INT NOT NULL, adq_nombre VARCHAR(300) NOT NULL, adq_prov_orga_fk INT NULL, adq_fuente_fk INT NULL, adq_moneda_fk INT NULL, adq_proc_compra VARCHAR(20) NULL, adq_proc_compra_grp VARCHAR(20) NULL, version INT DEFAULT 0 NULL, adq_componente_producto_fk INT NULL, adq_procedimiento_compra_fk INT NULL, adq_compartida BIT(1) DEFAULT 0 NOT NULL, adq_compartida_usuario_fk INT NULL, CONSTRAINT PK_ADQUISICION PRIMARY KEY (adq_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-3', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 3, '7:f3bf76e0c8ada092afa44a61a55afb3f', 'createTable tableName=adquisicion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-4::sofis
CREATE TABLE ambito (amb_pk INT AUTO_INCREMENT NOT NULL, amb_nombre VARCHAR(100) NOT NULL, amb_org_fk INT NOT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_AMBITO PRIMARY KEY (amb_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-4', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 4, '7:cdc9352e9d5e1146912ccf1cdba9cd69', 'createTable tableName=ambito', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-5::sofis
CREATE TABLE area_conocimiento (con_pk INT AUTO_INCREMENT NOT NULL, con_nombre VARCHAR(150) NOT NULL, con_org_fk INT NOT NULL, con_padre_fk INT NULL, CONSTRAINT PK_AREA_CONOCIMIENTO PRIMARY KEY (con_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-5', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 5, '7:8cf6f7305c3afb4f5d3ef6332c01c915', 'createTable tableName=area_conocimiento', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-6::sofis
CREATE TABLE area_organi_int_prove (areaorgintprov_pk INT AUTO_INCREMENT NOT NULL, areaorgintprov_orga_fk INT NOT NULL, areaorgintprov_org_fk INT NOT NULL, areaorgintprov_nombre VARCHAR(40) NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_AREA_ORGANI_INT_PROVE PRIMARY KEY (areaorgintprov_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-6', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 6, '7:c2be714715dd3b42732828f84e473ec6', 'createTable tableName=area_organi_int_prove', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-7::sofis
CREATE TABLE areas (area_pk INT AUTO_INCREMENT NOT NULL, area_org_fk INT NOT NULL, area_padre INT NULL, area_nombre VARCHAR(250) NULL, area_abreviacion VARCHAR(45) NULL, area_director INT NULL, version INT DEFAULT 0 NULL, area_activo TINYINT(3) DEFAULT 1 NULL, area_habilitada TINYINT(3) DEFAULT 1 NOT NULL, CONSTRAINT PK_AREAS PRIMARY KEY (area_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-7', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 7, '7:c0a9bf9813fce7ba4c1a44ba2f840c8a', 'createTable tableName=areas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-8::sofis
CREATE TABLE areas_tags (arastag_pk INT AUTO_INCREMENT NOT NULL, areatag_org_fk INT NOT NULL, areatag_padre_fk INT NULL, areatag_tematica VARCHAR(45) NULL, areatag_excluyente BIT NULL, areatag_nombre VARCHAR(45) NULL, areatag_categoria VARCHAR(45) NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_AREAS_TAGS PRIMARY KEY (arastag_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-8', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 8, '7:cc286fecccb29ce675b815037111b8ed', 'createTable tableName=areas_tags', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-9::sofis
CREATE TABLE aud_doc_file (docfile_pk INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, docfile_nombre VARCHAR(255) NULL, docfile_path VARCHAR(255) NULL, docfile_ult_mod DATETIME NULL, docfile_ult_origen VARCHAR(255) NULL, docfile_ult_usuario VARCHAR(255) NULL, docfile_version INT NULL, docfile_doc_fk INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-9', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 9, '7:278f0c1d51524840c5dff07c5ace912a', 'createTable tableName=aud_doc_file', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-10::sofis
CREATE TABLE aud_pge_configuraciones (cnf_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, cnf_clave VARCHAR(255) NULL, cnf_crea_fecha DATETIME NULL, cnf_crea_origen INT NULL, cnf_crea_usu VARCHAR(255) NULL, cnf_ultmod_fecha DATETIME NULL, cnf_ultmod_origen INT NULL, cnf_ultmod_usu VARCHAR(255) NULL, cnf_valor VARCHAR(255) NULL, cnf_version INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-10', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 10, '7:4a37bb9f32c097016f7eddb0e5e97a39', 'createTable tableName=aud_pge_configuraciones', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-11::sofis
CREATE TABLE aud_programas (prog_pk INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, prog_org_fk INT NULL, prog_area_fk INT NULL, prog_sol_aceptacion BIT NULL, prog_nombre VARCHAR(45) NULL, prog_objetivo VARCHAR(256) NULL, prog_obj_publico VARCHAR(256) NULL, prog_grp INT NULL, prog_semaforo_verde INT NULL, prog_semaforo_amarillo INT NULL, prog_version INT NULL, prog_ult_usuario VARCHAR(45) NULL, prog_ult_mod VARCHAR(45) NULL, prog_ult_origen date NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-11', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 11, '7:de467f516dbf2b8d55b077e363a720c8', 'createTable tableName=aud_programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-12::sofis
CREATE TABLE aud_programas_proyectos (id VARCHAR(13) NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, activo BIT NULL, areaNombre VARCHAR(255) NULL, areaPk INT NULL, estado INT NULL, estadoNombre VARCHAR(255) NULL, estadoPendiente INT NULL, fechaCrea date NULL, fichaFk INT NULL, gerente INT NULL, gerentePrimerApellido VARCHAR(255) NULL, gerentePrimerNombre VARCHAR(255) NULL, nombre VARCHAR(45) NULL, organismo INT NULL, pmoFederada INT NULL, tipoFicha BIGINT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-12', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 12, '7:5dc17e012d1cb46f7f1a1375e32deb75', 'createTable tableName=aud_programas_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-13::sofis
CREATE TABLE aud_ss_ayuda (ayu_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, ayu_codigo VARCHAR(45) NULL, ayu_texto LONGTEXT NULL, ayu_ult_mod_fecha DATETIME NULL, ayu_ult_mod_origen VARCHAR(45) NULL, ayu_ult_mod_usuario VARCHAR(45) NULL, ayu_version INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-13', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 13, '7:fb268c0b57341914b7f301a08a494924', 'createTable tableName=aud_ss_ayuda', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-14::sofis
CREATE TABLE aud_ss_categoper (cat_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, cat_descripcion LONGTEXT NULL, cat_nombre VARCHAR(255) NULL, cat_origen VARCHAR(255) NULL, cat_user_code INT NULL, cat_version INT NULL, cat_vigente BIT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-14', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 14, '7:7ed2fbc870e10c2eab14d22c4d4f368a', 'createTable tableName=aud_ss_categoper', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-15::sofis
CREATE TABLE aud_ss_configuraciones (id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, cnf_codigo VARCHAR(255) NULL, cnf_descripcion VARCHAR(255) NULL, cnf_html BIT NULL, cnf_protegido BIT NULL, cnf_ult_mod DATETIME NULL, cnf_ult_origen VARCHAR(255) NULL, cnf_ult_usuario VARCHAR(255) NULL, cnf_valor LONGTEXT NULL, cnf_version INT NULL, cnf_org_fk INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-15', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 15, '7:087f5503f8a964badb790f8ab918a737', 'createTable tableName=aud_ss_configuraciones', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-16::sofis
CREATE TABLE aud_ss_departamentos (depto_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, depto_codigo VARCHAR(255) NULL, depto_nombre VARCHAR(255) NULL, depto_ult_mod DATETIME NULL, err_ult_origen VARCHAR(255) NULL, depto_ult_usuario VARCHAR(255) NULL, depto_version INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-16', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 16, '7:7200654b111f29092e711ac6f9edbae4', 'createTable tableName=aud_ss_departamentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-17::sofis
CREATE TABLE aud_ss_domicilios (dom_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, dom_apto VARCHAR(50) NULL, dom_letra VARCHAR(255) NULL, dom_calle VARCHAR(150) NULL, dom_codigo_postal VARCHAR(5) NULL, dom_depto_alt VARCHAR(255) NULL, dom_descripcion LONGTEXT NULL, dom_inmueble_nombre VARCHAR(100) NULL, dom_kilometro VARCHAR(9) NULL, dom_manzana VARCHAR(5) NULL, dom_numero_puerta VARCHAR(5) NULL, dom_oficina VARCHAR(255) NULL, dom_ruta VARCHAR(5) NULL, dom_solar VARCHAR(5) NULL, dom_ult_mod DATETIME NULL, dom_ult_origen VARCHAR(255) NULL, dom_ult_usuario VARCHAR(255) NULL, dom_version INT NULL, dom_depto_id INT NULL, dom_loc_id INT NULL, dom_pai_id INT NULL, dom_par_id SMALLINT NULL, dom_tec_id INT NULL, dom_tvi_id INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-17', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 17, '7:f43b713d67021da766454142c6e7902a', 'createTable tableName=aud_ss_domicilios', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-18::sofis
CREATE TABLE aud_ss_errores (id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, err_codigo VARCHAR(255) NULL, err_descripcion VARCHAR(255) NULL, err_ult_mod DATETIME NULL, err_ult_origen VARCHAR(255) NULL, err_ult_usuario VARCHAR(255) NULL, err_version INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-18', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 18, '7:5b2011087c909636fab9ebc62edf280f', 'createTable tableName=aud_ss_errores', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-19::sofis
CREATE TABLE aud_ss_localidades (loc_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, loc_codigo VARCHAR(255) NULL, loc_nombre VARCHAR(255) NULL, loc_ult_mod DATETIME NULL, loc_ult_origen VARCHAR(255) NULL, loc_ult_usuario VARCHAR(255) NULL, loc_version INT NULL, loc_depto_id INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-19', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 19, '7:37918d4ffe499af60c05564457883d08', 'createTable tableName=aud_ss_localidades', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-20::sofis
CREATE TABLE aud_ss_noticias (not_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, not_ampliar VARCHAR(255) NULL, not_codigo VARCHAR(45) NULL, not_contenido LONGTEXT NULL, not_desde DATETIME NULL, not_hasta DATETIME NULL, not_imagen VARCHAR(255) NULL, not_titulo VARCHAR(255) NULL, not_ult_mod_fecha DATETIME NULL, not_ult_mod_origen VARCHAR(45) NULL, not_ult_mod_usuario VARCHAR(45) NULL, not_version INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-20', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 20, '7:577ae524be84bd1a9029b7616421e42c', 'createTable tableName=aud_ss_noticias', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-21::sofis
CREATE TABLE aud_ss_oficina (ofi_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, ofi_fecha_creacion DATETIME NULL, ofi_nombre VARCHAR(255) NULL, ofi_origen VARCHAR(255) NULL, ofi_usuario INT NULL, ofi_version INT NULL, ofi_activo BIT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-21', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 21, '7:dbcc1c17ba627f719ecef4a84ca3bcdc', 'createTable tableName=aud_ss_oficina', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-22::sofis
CREATE TABLE aud_ss_operacion (ope_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, ope_codigo VARCHAR(255) NULL, ope_descripcion LONGTEXT NULL, ope_nombre VARCHAR(255) NULL, ope_origen VARCHAR(255) NULL, ope_tipocampo VARCHAR(255) NULL, ope_user_code INT NULL, ope_version INT NULL, ope_vigente BIT NULL, ope_categoria_id INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-22', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 22, '7:04529d56971e6e0777da54d159251556', 'createTable tableName=aud_ss_operacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-23::sofis
CREATE TABLE aud_ss_paises (pai_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, pai_codigo2 VARCHAR(255) NULL, pai_codigo3 VARCHAR(255) NULL, pai_comun BIT NULL, pai_habilitado BIT NULL, pai_nombre VARCHAR(255) NULL, pai_ult_mod DATETIME NULL, pai_ult_origen VARCHAR(255) NULL, pai_ult_usuario VARCHAR(255) NULL, pai_version INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-23', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 23, '7:06f83ea5ca6e92a902b54e27efc2d8cc', 'createTable tableName=aud_ss_paises', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-24::sofis
CREATE TABLE aud_ss_paridades (par_id SMALLINT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, par_codigo VARCHAR(9) NULL, par_descripcion VARCHAR(45) NULL, par_ult_mod_fecha DATETIME NULL, par_ult_mod_origen VARCHAR(45) NULL, par_ult_mod_usuario VARCHAR(45) NULL, par_version INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-24', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 24, '7:5be6376bb247b59975fc8915da97cd5d', 'createTable tableName=aud_ss_paridades', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-25::sofis
CREATE TABLE aud_ss_personas (per_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, per_fec_nac DATETIME NULL, per_nro_doc VARCHAR(45) NULL, per_primer_apellido VARCHAR(100) NULL, per_primer_nombre VARCHAR(100) NULL, per_segundo_apellido VARCHAR(100) NULL, per_segundo_nombre VARCHAR(100) NULL, per_ult_mod_fecha DATETIME NULL, per_ult_mod_origen VARCHAR(45) NULL, per_ult_mod_usuario VARCHAR(45) NULL, per_version INT NULL, per_pais_doc INT NULL, per_tipo_doc INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-25', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 25, '7:af7867cbf5918d8f187e52d250f2925d', 'createTable tableName=aud_ss_personas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-26::sofis
CREATE TABLE aud_ss_rel_rol_operacion (rel_rol_operacion_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, rel_rol_operacion_editable BIT NULL, rel_rol_operacion_origen VARCHAR(255) NULL, rel_rol_operacion_user_code INT NULL, rel_rol_operacion_visible BIT NULL, rel_rol_operacion_operacion_id INT NULL, rel_rol_operacion_rol_id INT NULL, version INT DEFAULT 0 NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-26', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 26, '7:9c0297908957b6c9b22f9d055edda05b', 'createTable tableName=aud_ss_rel_rol_operacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-27::sofis
CREATE TABLE aud_ss_rol (rol_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, rol_cod VARCHAR(255) NULL, rol_descripcion LONGTEXT NULL, rol_nombre VARCHAR(255) NULL, rol_origen VARCHAR(255) NULL, rol_user_code INT NULL, rol_version INT NULL, rol_vigente BIT NULL, rol_tipo_usuario BIT NULL, rol_label VARCHAR(150) NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-27', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 27, '7:fead066b2d1825478d5aa8d566f7a32a', 'createTable tableName=aud_ss_rol', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-28::sofis
CREATE TABLE aud_ss_telefonos (tel_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, tel_numero VARCHAR(25) NULL, tel_observaciones VARCHAR(255) NULL, tel_prefijo VARCHAR(10) NULL, tel_ult_mod DATETIME NULL, tel_ult_origen VARCHAR(45) NULL, tel_ult_usuario VARCHAR(45) NULL, tel_version INT NULL, tel_tiptel_id INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-28', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 28, '7:6f0186b9e31ad2f0ce82cc3683a426fe', 'createTable tableName=aud_ss_telefonos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-29::sofis
CREATE TABLE aud_ss_tipos_documento_persona (tipdocper_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, tipdocper_codigo VARCHAR(255) NULL, tipdocper_descripcion VARCHAR(255) NULL, tipdocper_habilitado BIT NULL, tipdocper_ult_mod DATETIME NULL, tipdocper_ult_origen VARCHAR(255) NULL, tipdocper_ult_usuario VARCHAR(255) NULL, tipdocper_version INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-29', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 29, '7:379ea3690fce7e6d8be4dd0a2ead556f', 'createTable tableName=aud_ss_tipos_documento_persona', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-30::sofis
CREATE TABLE aud_ss_tipos_entrada_colectiva (tec_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, tec_codigo VARCHAR(255) NULL, tec_descripcion VARCHAR(255) NULL, tec_ult_mod DATETIME NULL, tec_ult_origen VARCHAR(255) NULL, tec_ult_usuario VARCHAR(255) NULL, tec_version INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-30', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 30, '7:845ec7f8824a8cfe183249ec70c5efd1', 'createTable tableName=aud_ss_tipos_entrada_colectiva', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-31::sofis
CREATE TABLE aud_ss_tipos_telefono (tipTel_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, tipTel_codigo VARCHAR(255) NULL, tipTel_descripcion VARCHAR(255) NULL, tipTel_habilitado BIT NULL, tipTel_ult_mod DATETIME NULL, tipTel_ult_origen VARCHAR(255) NULL, tipTel_ult_usuario VARCHAR(255) NULL, tipTel_version INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-31', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 31, '7:1aa8f0a72350b1b494146efed4d31903', 'createTable tableName=aud_ss_tipos_telefono', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-32::sofis
CREATE TABLE aud_ss_tipos_vialidad (tvi_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, tvi_abreviacion VARCHAR(255) NULL, tvi_codigo VARCHAR(255) NULL, tvi_descripcion VARCHAR(255) NULL, tvi_ult_mod DATETIME NULL, tvi_ult_origen VARCHAR(255) NULL, tvi_ult_usuario VARCHAR(255) NULL, tvi_version INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-32', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 32, '7:72139ee9f68a25b8fcb06b4f8784ebb6', 'createTable tableName=aud_ss_tipos_vialidad', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-33::sofis
CREATE TABLE aud_ss_usu_ofi_roles (usu_ofi_roles_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, usu_ofi_roles_origen VARCHAR(255) NULL, usu_ofi_roles_user_code INT NULL, usu_ofi_roles_oficina INT NULL, usu_ofi_roles_rol INT NULL, usu_ofi_roles_usuario INT NULL, version INT DEFAULT 0 NULL, usu_ofi_roles_activo BIT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-33', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 33, '7:97ac57003e778ccd8365d091864daaf1', 'createTable tableName=aud_ss_usu_ofi_roles', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-34::sofis
CREATE TABLE aud_ss_usuario (usu_id INT NOT NULL, REV INT NOT NULL, REVTYPE TINYINT(3) NULL, usu_administrador BIT NULL, usu_cambio_estado_desc LONGTEXT NULL, usu_cod VARCHAR(255) NULL, usu_correo_electronico VARCHAR(255) NULL, usu_cuenta_bloqueada SMALLINT NULL, usu_descripcion LONGTEXT NULL, usu_direccion LONGTEXT NULL, usu_fecha_password DATETIME NULL, usu_fecha_uuid DATETIME NULL, usu_foto LONGBLOB NULL, usu_intentos_fallidos INT NULL, usu_nro_doc VARCHAR(255) NULL, usu_oficina_por_defecto INT NULL, usu_origen LONGTEXT NULL, usu_password VARCHAR(255) NULL, usu_primer_apellido VARCHAR(255) NULL, usu_primer_nombre VARCHAR(255) NULL, usu_registrado BIT NULL, usu_segundo_apellido VARCHAR(255) NULL, usu_segundo_nombre VARCHAR(255) NULL, usu_telefono VARCHAR(255) NULL, usu_user_code INT NULL, usu_uuid_des VARCHAR(255) NULL, usu_version INT NULL, usu_vigente BIT NULL, usu_aprob_facturas BIT NULL, usu_ult_usuario VARCHAR(255) NULL, usu_ult_mod DATETIME NULL, usu_ult_origen VARCHAR(255) NULL, usu_area_org INT NULL, usu_token VARCHAR(100) NULL, usu_token_act DATETIME NULL, usu_celular VARCHAR(255) NULL, usu_ldap_user VARCHAR(45) NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-34', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 34, '7:62f49fbfd68e980fe0ea8d8cd268e1ba', 'createTable tableName=aud_ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-35::sofis
CREATE TABLE busq_filtro (busq_filtro_pk INT AUTO_INCREMENT NOT NULL, busq_filtro_usu_fk INT NOT NULL, busq_filtro_org_fk INT NOT NULL, busq_filtro_xml MEDIUMTEXT NOT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_BUSQ_FILTRO PRIMARY KEY (busq_filtro_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-35', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 35, '7:c2b7563b3d20e253e475d5d6becd72bc', 'createTable tableName=busq_filtro', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-36::sofis
CREATE TABLE calidad (cal_pk INT AUTO_INCREMENT NOT NULL, cal_peso INT NOT NULL, cal_vca_fk INT NOT NULL, cal_actualizacion date NOT NULL, cal_tipo INT NOT NULL, cal_ent_fk INT NULL, cal_prod_fk INT NULL, cal_tca_fk INT NULL, cal_proy_fk INT NOT NULL, CONSTRAINT PK_CALIDAD PRIMARY KEY (cal_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-36', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 36, '7:0184d42fea1eec91b118fb12ff4859e4', 'createTable tableName=calidad', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-37::sofis
CREATE TABLE categoria_proyectos (cat_proy_pk INT AUTO_INCREMENT NOT NULL, cat_proy_codigo VARCHAR(45) NOT NULL, cat_proy_nombre VARCHAR(145) NOT NULL, cat_proy_activo BIT DEFAULT 1 NOT NULL, cat_tipo INT DEFAULT 0 NOT NULL, cat_icono INT NULL, cat_icono_marker INT NULL, cat_org_fk INT NULL, CONSTRAINT PK_CATEGORIA_PROYECTOS PRIMARY KEY (cat_proy_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-37', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 37, '7:ee7f68f22e6cb9843356ba197fbbf735', 'createTable tableName=categoria_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-38::sofis
CREATE TABLE componente_producto (com_pk INT AUTO_INCREMENT NOT NULL, com_nombre VARCHAR(300) NULL, com_org_fk INT NULL, com_version INT NULL, CONSTRAINT PK_COMPONENTE_PRODUCTO PRIMARY KEY (com_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-38', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 38, '7:7b7df25544f62c5210b0ffac32af95a1', 'createTable tableName=componente_producto', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-39::sofis
CREATE TABLE cronogramas (cro_pk INT AUTO_INCREMENT NOT NULL, cro_ent_seleccionado INT NULL, cro_ent_borrados VARCHAR(45) NULL, cro_resources VARCHAR(45) NULL, cro_permiso_escritura BIT NULL, cro_permiso_escritura_padre BIT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_CRONOGRAMAS PRIMARY KEY (cro_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-39', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 39, '7:8520c547441a7653f777a7c34bb9ef51', 'createTable tableName=cronogramas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-40::sofis
CREATE TABLE departamentos (dep_pk INT NOT NULL, dep_nombre VARCHAR(145) NULL, dep_lat DOUBLE NULL, dep_lng DOUBLE NULL, dep_zoom INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-40', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 40, '7:3bd8e8ebbe97549e1caec0f9bf1a3e19', 'createTable tableName=departamentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-41::sofis
CREATE TABLE devengado (dev_pk INT AUTO_INCREMENT NOT NULL, dev_adq_fk INT NOT NULL, dev_mes SMALLINT NOT NULL, dev_anio SMALLINT NOT NULL, dev_plan DECIMAL(11, 2) NULL, dev_real DECIMAL(11, 2) NULL, CONSTRAINT PK_DEVENGADO PRIMARY KEY (dev_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-41', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 41, '7:bdf67a882fa97504ce2a3314770e2b2e', 'createTable tableName=devengado', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-42::sofis
CREATE TABLE doc_file (docfile_pk INT AUTO_INCREMENT NOT NULL, docfile_nombre VARCHAR(256) NOT NULL, docfile_doc_fk INT NOT NULL, docfile_path VARCHAR(255) NULL, docfile_ult_mod DATETIME NULL, docfile_ult_origen VARCHAR(255) NULL, docfile_ult_usuario VARCHAR(255) NULL, docfile_version INT DEFAULT 0 NULL, CONSTRAINT PK_DOC_FILE PRIMARY KEY (docfile_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-42', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 42, '7:a1bcb8e590fda8a81451260824fa4506', 'createTable tableName=doc_file', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-43::sofis
CREATE TABLE documentos (docs_pk INT AUTO_INCREMENT NOT NULL, docs_nombre VARCHAR(100) NULL, docs_fecha DATETIME NULL, docs_privado BIT NULL, docs_estado DOUBLE NULL, docs_entregable_fk INT NULL, docs_tipodoc_fk INT NOT NULL, docs_docfile_pk INT NULL, docs_aprobado BIT NULL, docs_pago_fk INT NULL, version INT DEFAULT 0 NULL, docs_publicable BIT(1) DEFAULT 0 NOT NULL, docs_ult_pub DATETIME NULL, docs_pub_fecha DATETIME NULL, CONSTRAINT PK_DOCUMENTOS PRIMARY KEY (docs_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-43', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 43, '7:c2502a65df809eabc170e243f62714ce', 'createTable tableName=documentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-44::sofis
CREATE TABLE ent_hist_linea_base (enthist_pk INT AUTO_INCREMENT NOT NULL, enthist_ent_fk INT NOT NULL, enthist_fecha date NOT NULL, enthist_inicio_linea_base BIGINT NOT NULL, enthist_fin_linea_base BIGINT NULL, enthist_duracion INT NULL, enthist_replan_fk INT NULL, CONSTRAINT PK_ENT_HIST_LINEA_BASE PRIMARY KEY (enthist_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-44', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 44, '7:cfd5bbe469e85fb23b5ed5d813ed9259', 'createTable tableName=ent_hist_linea_base', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-45::sofis
CREATE TABLE entregables (ent_pk INT AUTO_INCREMENT NOT NULL, ent_cro_fk INT NOT NULL, ent_id INT NOT NULL, ent_nombre VARCHAR(1000) NULL, ent_codigo VARCHAR(256) NULL, ent_nivel INT NULL, ent_status VARCHAR(256) NULL, ent_parent BIT NULL, ent_inicio BIGINT NULL, ent_duracion INT NULL, ent_fin BIGINT NULL, ent_horas_estimadas VARCHAR(15) NULL, ent_inicio_es_hito BIT NULL, ent_fin_es_hito BIT NULL, ent_collapsed BIT NULL, ent_assigs VARCHAR(256) NULL, ent_coord_usu_fk INT NULL, ent_esfuerzo INT NULL, ent_inicio_linea_base BIGINT NULL, ent_duracion_linea_base INT NULL, ent_fin_linea_base BIGINT NULL, ent_predecesor_fk VARCHAR(2000) NULL, ent_predecesor_dias INT NULL, ent_descripcion VARCHAR(1000) NULL, ent_progreso INT NULL, ent_relevante BIT NULL, version INT DEFAULT 0 NULL, ent_inicio_periodo BIT(1) DEFAULT 0 NOT NULL, ent_fin_periodo BIT(1) DEFAULT 0 NOT NULL, CONSTRAINT PK_ENTREGABLES PRIMARY KEY (ent_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-45', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 45, '7:834f6a4d7cffdec1f696d878b75ca2a8', 'createTable tableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-46::sofis
CREATE TABLE entregables_apagon_20160113 (ent_pk INT DEFAULT 0 NOT NULL, ent_cro_fk INT NOT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-46', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 46, '7:c85578c35586dfe45b3abcfd7c8eab01', 'createTable tableName=entregables_apagon_20160113', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-47::sofis
CREATE TABLE estados (est_pk INT AUTO_INCREMENT NOT NULL, est_codigo VARCHAR(150) NOT NULL, est_nombre VARCHAR(45) NULL, est_label VARCHAR(150) NOT NULL, est_orden_proceso INT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_ESTADOS PRIMARY KEY (est_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-47', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 47, '7:db25c540d86d38e57093449755fd5ff2', 'createTable tableName=estados', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-48::sofis
CREATE TABLE estados_publicacion (est_pub_pk INT AUTO_INCREMENT NOT NULL, est_pub_codigo VARCHAR(45) NULL, est_pub_nombre VARCHAR(145) NULL, CONSTRAINT PK_ESTADOS_PUBLICACION PRIMARY KEY (est_pub_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-48', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 48, '7:21fd1ab5b6e46a458f7577702cb0f1c2', 'createTable tableName=estados_publicacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-49::sofis
CREATE TABLE etapa (eta_pk INT AUTO_INCREMENT NOT NULL, eta_org_fk INT NOT NULL, eta_codigo VARCHAR(45) NOT NULL, eta_nombre VARCHAR(100) NOT NULL, eta_label VARCHAR(100) NULL, CONSTRAINT PK_ETAPA PRIMARY KEY (eta_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-49', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 49, '7:ba66717154da7a35f5db5c0ac4d4f17e', 'createTable tableName=etapa', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-50::sofis
CREATE TABLE fuente_financiamiento (fue_pk INT AUTO_INCREMENT NOT NULL, fue_nombre VARCHAR(300) NOT NULL, fue_org_fk INT NOT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_FUENTE_FINANCIAMIENTO PRIMARY KEY (fue_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-50', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 50, '7:c76338cdf1f72ed50f592d31a0aca1cb', 'createTable tableName=fuente_financiamiento', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-51::sofis
CREATE TABLE gastos (gas_pk INT AUTO_INCREMENT NOT NULL, gas_proy_fk INT NOT NULL, gas_tipo_fk INT NOT NULL, gas_usu_fk INT NOT NULL, gas_mon_fk INT NOT NULL, gas_importe DECIMAL(11, 2) NOT NULL, gas_fecha date NOT NULL, gas_obs VARCHAR(4000) NOT NULL, gas_aprobado BIT NULL, CONSTRAINT PK_GASTOS PRIMARY KEY (gas_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-51', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 51, '7:0d60ba79dfb972ce50ee7985d5a8a356', 'createTable tableName=gastos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-52::sofis
CREATE TABLE hibernate_sequence (next_val BIGINT NULL, version INT DEFAULT 0 NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-52', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 52, '7:502d216f1b581c6cc87f6aa7aed00243', 'createTable tableName=hibernate_sequence', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-53::sofis
CREATE TABLE image (image_pk INT AUTO_INCREMENT NOT NULL, image_name VARCHAR(45) NOT NULL, image_desc VARCHAR(255) NULL, image_ext VARCHAR(20) NOT NULL, image_blob MEDIUMBLOB NOT NULL, image_tipo INT NULL, image_orden INT NULL, CONSTRAINT PK_IMAGE PRIMARY KEY (image_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-53', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 53, '7:4df34a505e39a6e0f7dc79a131c06a9e', 'createTable tableName=image', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-54::sofis
CREATE TABLE interesados (int_pk INT AUTO_INCREMENT NOT NULL, int_rolint_fk INT NOT NULL, int_observaciones VARCHAR(4000) NULL, int_pers_fk INT NULL, int_orga_fk INT NULL, int_ent_fk INT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_INTERESADOS PRIMARY KEY (int_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-54', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 54, '7:483c1210571515f05f48910a7bfb26e0', 'createTable tableName=interesados', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-55::sofis
CREATE TABLE latlng_proyectos (latlng_pk INT AUTO_INCREMENT NOT NULL, latlng_lat DECIMAL(19, 15) NULL COMMENT 'la latitud del punto', latlng_lng DECIMAL(19, 15) NULL COMMENT 'la longitud del punto', latlng_proy_fk INT NULL COMMENT 'EL proyecto al cual pertenece este punto.', latlang_dep_fk INT NULL, latlang_loc_fk INT NULL, latlang_codigopostal INT NULL, latlang_barrio VARCHAR(245) NULL, latlang_loc VARCHAR(245) NULL, CONSTRAINT PK_LATLNG_PROYECTOS PRIMARY KEY (latlng_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-55', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 55, '7:e7355d078e2829636b3be884a4a2c1a2', 'createTable tableName=latlng_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-56::sofis
CREATE TABLE lecapr_areacon (lecaprcon_con_fk INT NOT NULL, lecaprcon_lecapr_fk INT NOT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-56', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 56, '7:3cc32c801e6200460a65863da84cfe4a', 'createTable tableName=lecapr_areacon', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-57::sofis
CREATE TABLE lecc_aprendidas (lecapr_pk INT AUTO_INCREMENT NOT NULL, lecapr_proy_fk INT NULL, lecapr_tipo_fk INT NOT NULL, lecapr_usr_fk INT NOT NULL, lecapr_org_fk INT NOT NULL, lecapr_fecha date NOT NULL, lecapr_desc VARCHAR(3000) NOT NULL, lecapr_activo BIT NULL, CONSTRAINT PK_LECC_APRENDIDAS PRIMARY KEY (lecapr_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-57', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 57, '7:3ed636c4ca1e7f78879a0622a465d91e', 'createTable tableName=lecc_aprendidas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-58::sofis
CREATE TABLE lineabase_historico (lineabase_pk INT AUTO_INCREMENT NOT NULL, lineabase_entFk INT NOT NULL, lineabase_fecha date NOT NULL, lineabase_inicio BIGINT NOT NULL, lineabase_duracion INT NULL, lineabase_fin BIGINT NOT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_LINEABASE_HISTORICO PRIMARY KEY (lineabase_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-58', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 58, '7:f8c57a8e24baeb1c7dca5affb489c93c', 'createTable tableName=lineabase_historico', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-59::sofis
CREATE TABLE mails_template (mail_tmp_pk INT AUTO_INCREMENT NOT NULL, mail_tmp_cod VARCHAR(45) NOT NULL, mail_tmp_org_fk INT NOT NULL, mail_tmp_asunto VARCHAR(200) NOT NULL, mail_tmp_mensaje VARCHAR(5000) NOT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_MAILS_TEMPLATE PRIMARY KEY (mail_tmp_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-59', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 59, '7:3aac5893be90c270fd6ce7151942d0ec', 'createTable tableName=mails_template', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-60::sofis
CREATE TABLE media_proyectos (media_pk INT AUTO_INCREMENT NOT NULL, media_tipo_fk INT NULL COMMENT 'Tipo de media, los tipos definidos son:
Imagens, link a videos youtube, links a webcam', media_link VARCHAR(545) NULL COMMENT 'El link al media, en caso de imagenes es el link al folder donde se encuentra, en youtube la url del video, y en camaras web la url de la camara', media_estado INT NULL COMMENT 'EL estado del media puede ser 
PENDIENTE REVISION, PUBLICADO, RECHAZADO', media_proy_fk INT NULL, media_principal BIT NULL COMMENT 'en caso de ser una imagen es la que se utlizar', media_orden INT NULL COMMENT 'EL orde de aparici', media_usr_pub_fk INT NULL COMMENT 'EL c', media_pub_fecha DATETIME NULL COMMENT 'Fecha y hora de publicaci', media_usr_mod_fk INT NULL COMMENT 'EL usuario que creo el media', media_mod_fecha DATETIME NULL COMMENT 'La fecha de creaci', media_usr_rech_fk INT NULL COMMENT 'Usuario que rechaza el media', media_rech_fecha DATETIME NULL COMMENT 'fecha de rehazo del medio', media_comentario VARCHAR(2000) NULL, media_contenttype VARCHAR(200) NULL, media_publicable BIT NULL, CONSTRAINT PK_MEDIA_PROYECTOS PRIMARY KEY (media_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-60', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 60, '7:c0f494577199b4c094914d5cbe29e05d', 'createTable tableName=media_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-61::sofis
CREATE TABLE moneda (mon_pk INT AUTO_INCREMENT NOT NULL, mon_nombre VARCHAR(100) NOT NULL, mon_signo VARCHAR(50) NULL, mon_cod_pais VARCHAR(10) NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_MONEDA PRIMARY KEY (mon_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-61', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 61, '7:80bab21900c978c5a2d9cc72de10e609', 'createTable tableName=moneda', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-62::sofis
CREATE TABLE notificacion (not_pk INT AUTO_INCREMENT NOT NULL, not_org_fk INT NOT NULL, not_cod VARCHAR(45) NOT NULL, not_desc VARCHAR(245) NOT NULL, not_valor INT NULL, not_gerente_adjunto TINYINT(3) NULL, not_pmof TINYINT(3) NULL, not_pmot TINYINT(3) NULL, not_sponsor TINYINT(3) NULL, not_msg VARCHAR(5000) NOT NULL, CONSTRAINT PK_NOTIFICACION PRIMARY KEY (not_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-62', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 62, '7:2475adbdd75f1cea1924d36db35496b6', 'createTable tableName=notificacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-63::sofis
CREATE TABLE notificacion_envio (notenv_pk INT AUTO_INCREMENT NOT NULL, notenv_fecha date NOT NULL, notenv_proy_fk INT NOT NULL, notenv_not_cod VARCHAR(45) NOT NULL, CONSTRAINT PK_NOTIFICACION_ENVIO PRIMARY KEY (notenv_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-63', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 63, '7:d567f2c8b46f84c70420db65841e275e', 'createTable tableName=notificacion_envio', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-64::sofis
CREATE TABLE notificacion_instancia (notinst_pk INT AUTO_INCREMENT NOT NULL, notinst_not_fk INT NOT NULL, notinst_proy_fk INT NOT NULL, notinst_gerente_adjunto TINYINT(3) NULL, notinst_pmof TINYINT(3) NULL, notinst_pmot TINYINT(3) NULL, notinst_sponsor TINYINT(3) NULL, CONSTRAINT PK_NOTIFICACION_INSTANCIA PRIMARY KEY (notinst_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-64', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 64, '7:fe1604230f245213c1342c7897e5ab73', 'createTable tableName=notificacion_instancia', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-65::sofis
CREATE TABLE objetivos_estrategicos (obj_est_pk INT AUTO_INCREMENT NOT NULL, obj_est_nombre VARCHAR(100) NULL, obj_est_descripcion VARCHAR(300) NULL, obj_est_org_fk INT NULL, CONSTRAINT PK_OBJETIVOS_ESTRATEGICOS PRIMARY KEY (obj_est_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-65', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 65, '7:9e5bd29c173a35fcba9a95b86c87fd81', 'createTable tableName=objetivos_estrategicos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-66::sofis
CREATE TABLE organi_int_prove (orga_pk INT AUTO_INCREMENT NOT NULL, orga_nombre VARCHAR(50) NULL, orga_proveedor BIT NULL, orga_razon_social VARCHAR(50) NULL, orga_rut VARCHAR(45) NULL, orga_mail VARCHAR(45) NULL, orga_telefono VARCHAR(45) NULL, orga_web VARCHAR(45) NULL, orga_direccion VARCHAR(100) NULL, orga_ambito VARCHAR(45) NULL, orga_org_fk INT NOT NULL, orga_amb_fk INT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_ORGANI_INT_PROVE PRIMARY KEY (orga_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-66', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 66, '7:426376ee5457568602443a1ab7786730', 'createTable tableName=organi_int_prove', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-67::sofis
CREATE TABLE organismos (org_pk INT AUTO_INCREMENT NOT NULL, org_nombre VARCHAR(45) NULL, org_logo_nombre VARCHAR(45) NULL, org_direccion VARCHAR(45) NULL, org_logo MEDIUMBLOB NULL, org_activo BIT DEFAULT 1 NOT NULL, org_token VARCHAR(100) NOT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_ORGANISMOS PRIMARY KEY (org_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-67', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 67, '7:c511234cb56f47c928c4a0053d24c844', 'createTable tableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-68::sofis
CREATE TABLE pagos (pag_pk INT AUTO_INCREMENT NOT NULL, pag_adq_fk INT NOT NULL, pag_ent_fk INT NULL, pag_observacion VARCHAR(3000) NULL, pag_fecha_planificada date NOT NULL, pag_importe_planificado DECIMAL(11, 2) NOT NULL, pag_fecha_real date NULL, pag_importe_real DECIMAL(11, 2) NULL, pag_txt_referencia VARCHAR(20) NULL, pag_confirmar BIT NULL, version INT DEFAULT 0 NULL, pag_gasto SMALLINT DEFAULT 0 NOT NULL, pag_inversion SMALLINT DEFAULT 0 NOT NULL, pag_contr_organizacion_fk INT NULL, pag_contr_porcentaje SMALLINT NULL, CONSTRAINT PK_PAGOS PRIMARY KEY (pag_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-68', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 68, '7:e29ead4faad6c874ca18fc3e1fe3f15b', 'createTable tableName=pagos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-69::sofis
CREATE TABLE participantes (part_pk INT AUTO_INCREMENT NOT NULL, part_usu_fk INT NOT NULL, part_proy_fk INT NOT NULL, part_ent_fk INT NULL, part_horas_plan DECIMAL(11, 2) NULL, part_activo TINYINT(3) DEFAULT 1 NOT NULL, CONSTRAINT PK_PARTICIPANTES PRIMARY KEY (part_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-69', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 69, '7:371311af5a04904396b8aceed2910630', 'createTable tableName=participantes', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-70::sofis
CREATE TABLE personas (pers_pk INT AUTO_INCREMENT NOT NULL, pers_rol_fk INT NOT NULL, pers_orga_fk INT NOT NULL, pers_nombre VARCHAR(45) NOT NULL, pers_telefono VARCHAR(45) NULL, pers_cargo VARCHAR(45) NULL, pers_mail VARCHAR(45) NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_PERSONAS PRIMARY KEY (pers_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-70', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 70, '7:003909bf61b0204d4e0627e6c7fcdeae', 'createTable tableName=personas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-71::sofis
CREATE TABLE pge_configuraciones (cnf_id INT AUTO_INCREMENT NOT NULL, cnf_clave VARCHAR(255) NULL, cnf_crea_fecha DATETIME NULL, cnf_crea_origen INT NULL, cnf_crea_usu VARCHAR(255) NULL, cnf_ultmod_fecha DATETIME NULL, cnf_ultmod_origen INT NULL, cnf_ultmod_usu VARCHAR(255) NULL, cnf_valor VARCHAR(255) NULL, cnf_version INT NULL, CONSTRAINT PK_PGE_CONFIGURACIONES PRIMARY KEY (cnf_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-71', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 71, '7:4ef2e109457d8a94e2f294de368a124b', 'createTable tableName=pge_configuraciones', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-72::sofis
CREATE TABLE pge_invocaciones (inv_id INT AUTO_INCREMENT NOT NULL, inv_crea_usu VARCHAR(255) NULL, inv_env_fecha DATETIME NULL, inv_env_mensaje VARCHAR(255) NULL, inv_env_ok BIT NULL, inv_operacion VARCHAR(255) NULL, inv_rec_fecha DATETIME NULL, inv_rec_mensaje VARCHAR(255) NULL, inv_rec_ok BIT NULL, inv_servicio VARCHAR(255) NULL, inv_url VARCHAR(255) NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_PGE_INVOCACIONES PRIMARY KEY (inv_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-72', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 72, '7:2ca84d7f0143a3e586092ab37c47a938', 'createTable tableName=pge_invocaciones', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-73::sofis
CREATE TABLE plantilla_cronograma (p_crono_pk INT AUTO_INCREMENT NOT NULL, p_crono_nombre VARCHAR(845) NULL, p_crono_org_fk INT NULL, activo TINYINT(3) NULL, CONSTRAINT PK_PLANTILLA_CRONOGRAMA PRIMARY KEY (p_crono_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-73', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 73, '7:7e0bd0c73c5b604b67e38399b25a9ef9', 'createTable tableName=plantilla_cronograma', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-74::sofis
CREATE TABLE plantilla_entregables (p_entregables_id INT AUTO_INCREMENT NOT NULL, p_entregables_nombre VARCHAR(1000) NULL, p_entregable_nivel INT NULL, p_entregable_esfuerzo INT NULL, p_entregable_duracion INT NULL, p_entregable_ant_fk INT NULL, p_entregable_p_cro_fk INT NULL, p_entregables_numero INT NULL, p_entregables_dependencia VARCHAR(45) NULL, p_entregables_es_hito BIT(1) NULL, CONSTRAINT PK_PLANTILLA_ENTREGABLES PRIMARY KEY (p_entregables_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-74', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 74, '7:fff0c120275de901aa66400d79241314', 'createTable tableName=plantilla_entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-75::sofis
CREATE TABLE presupuesto (pre_pk INT AUTO_INCREMENT NOT NULL, pre_base DECIMAL(15, 2) NULL, pre_moneda INT NULL, pre_fuente_organi_fk INT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_PRESUPUESTO PRIMARY KEY (pre_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-75', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 75, '7:3a65f933156f079ef4a12833ef9caedb', 'createTable tableName=presupuesto', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-76::sofis
CREATE TABLE procedimiento_compra (proc_comp_pk INT AUTO_INCREMENT NOT NULL, proc_comp_nombre VARCHAR(45) NULL, proc_comp_org_fk INT NULL, proc_comp_version INT NULL, proc_comp_descripcion VARCHAR(200) NULL, proc_comp_habilitado BIT DEFAULT 1 NULL, CONSTRAINT PK_PROCEDIMIENTO_COMPRA PRIMARY KEY (proc_comp_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-76', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 76, '7:b2adfc18a8b674eb0b96e1654d2f0927', 'createTable tableName=procedimiento_compra', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-77::sofis
CREATE TABLE prod_mes (prodmes_pk INT AUTO_INCREMENT NOT NULL, prodmes_prod_fk INT NOT NULL, prodmes_mes SMALLINT NOT NULL, prodmes_anio SMALLINT NOT NULL, prodmes_plan DECIMAL(11, 2) NOT NULL, prodmes_real DECIMAL(11, 2) NULL, prodmes_acu_plan DECIMAL(11, 2) NOT NULL, prodmes_acu_real DECIMAL(11, 2) NULL, CONSTRAINT PK_PROD_MES PRIMARY KEY (prodmes_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-77', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 77, '7:2d8d43af783327e2c02958440149f016', 'createTable tableName=prod_mes', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-78::sofis
CREATE TABLE productos (prod_pk INT AUTO_INCREMENT NOT NULL, prod_peso INT NOT NULL, prod_und_medida VARCHAR(45) NOT NULL, prod_ent_fk INT NOT NULL, prod_fecha_crea date NOT NULL, prod_ult_mod date NULL, prod_acumulado BIT NULL, prod_desc VARCHAR(2000) NULL, CONSTRAINT PK_PRODUCTOS PRIMARY KEY (prod_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-78', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 78, '7:6dbdd0d70c454fee67a1d664768329de', 'createTable tableName=productos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-79::sofis
CREATE TABLE prog_docs (progdocs_prog_pk INT NOT NULL, progdocs_doc_pk INT NOT NULL, version INT DEFAULT 0 NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-79', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 79, '7:fd4310677e55331b3166d1bd2bb60904', 'createTable tableName=prog_docs', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-80::sofis
CREATE TABLE prog_docs_obl (progdocsobl_docs_pk INT NOT NULL, progdocsobl_prog_pk INT NOT NULL, version INT DEFAULT 0 NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-80', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 80, '7:851585577b42c0c94748a8661b4a7b5b', 'createTable tableName=prog_docs_obl', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-81::sofis
CREATE TABLE prog_indices (progind_pk INT AUTO_INCREMENT NOT NULL, progind_riesgo_expo DOUBLE(11, 2) NULL, progind_riesgo_ultact date NULL, progind_riesgo_alto INT NULL, progind_metodo_estado DOUBLE(11, 2) NULL, progind_metodo_sin_aprobar BIT NULL, progind_periodo_inicio date NULL, progind_periodo_fin date NULL, progind_proy_actualizacion date NULL, progind_cal_ind DOUBLE(11, 2) NULL, progind_cal_pend INT NULL, progind_avance_par_azul INT NULL, progind_avance_par_verde INT NULL, progind_anvance_par_rojo INT NULL, progind_avance_fin_azul INT NULL, progind_avance_fin_verde INT NULL, progind_anvance_fin_rojo INT NULL, progind_fecha_act DATETIME NULL, version INT DEFAULT 0 NULL, progind_fecha_act_color INT DEFAULT 0 NOT NULL, CONSTRAINT PK_PROG_INDICES PRIMARY KEY (progind_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-81', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 81, '7:efd573f90eb4f7763b4ef96bb10d0053', 'createTable tableName=prog_indices', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-82::sofis
CREATE TABLE prog_indices_pre (progindpre_pk INT AUTO_INCREMENT NOT NULL, progindpre_progind_fk INT NOT NULL, progindpre_mon_fk INT NOT NULL, progindpre_total DOUBLE(11, 2) NULL, progindpre_est_pre INT NULL, progindpre_anio DOUBLE(11, 2) NULL, progindpre_ac DOUBLE(11, 2) NULL, progindpre_pv DOUBLE(11, 2) NULL, CONSTRAINT PK_PROG_INDICES_PRE PRIMARY KEY (progindpre_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-82', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 82, '7:71003287aa9a297a46df56307bc4df1f', 'createTable tableName=prog_indices_pre', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-83::sofis
CREATE TABLE prog_int (progint_prog_pk INT NOT NULL, progint_int_pk INT NOT NULL, version INT DEFAULT 0 NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-83', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 83, '7:4fe8e9d834f8d261ee9bab634cf9d7ab', 'createTable tableName=prog_int', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-84::sofis
CREATE TABLE prog_lectura_area (proglectarea_prog_pk INT NOT NULL, proglectarea_area_pk INT NOT NULL, version INT DEFAULT 0 NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-84', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 84, '7:9e1430e50dffbd6849e740317b74f284', 'createTable tableName=prog_lectura_area', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-85::sofis
CREATE TABLE prog_pre (progpre_prog_fk INT NOT NULL, progpre_pre_fk INT NOT NULL, version INT DEFAULT 0 NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-85', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 85, '7:6437640915503b71b89ce5e9210ca05f', 'createTable tableName=prog_pre', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-86::sofis
CREATE TABLE prog_tags (progtag_prog_pk INT NOT NULL, progtag_area_pk INT NOT NULL, version INT DEFAULT 0 NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-86', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 86, '7:b876413d1d88ae9e692f84030d9a60bb', 'createTable tableName=prog_tags', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-87::sofis
CREATE TABLE programas (prog_pk INT AUTO_INCREMENT NOT NULL, prog_org_fk INT NOT NULL, prog_area_fk INT NOT NULL, prog_est_fk INT NOT NULL, prog_est_pendiente_fk INT NULL, prog_sol_aceptacion BIT NULL, prog_usr_gerente_fk INT NOT NULL, prog_usr_adjunto_fk INT NOT NULL, prog_usr_sponsor_fk INT NOT NULL, prog_usr_pmofed_fk INT NULL, prog_cro_fk INT NULL, prog_pre_fk INT NULL, prog_progindices_fk INT NULL, prog_nombre VARCHAR(100) NULL, prog_descripcion VARCHAR(4000) NULL, prog_objetivo VARCHAR(4000) NULL, prog_obj_publico VARCHAR(4000) NULL, prog_grp VARCHAR(45) NULL, prog_semaforo_amarillo INT NULL, prog_semaforo_rojo INT NULL, prog_activo BIT(1) DEFAULT 1 NOT NULL, prog_fecha_crea date NULL, prog_fecha_act date NOT NULL, prog_version INT NULL, prog_ult_usuario VARCHAR(45) NULL, prog_ult_mod VARCHAR(45) NULL, prog_ult_origen date NULL, prog_id_migrado INT NULL, prog_obj_est_fk INT NULL, prog_factor_impacto TEXT NULL, prog_habilitado BIT(1) DEFAULT 1 NULL, CONSTRAINT PK_PROGRAMAS PRIMARY KEY (prog_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-87', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 87, '7:dbe3f1cab9fea389c44aff7751ea0283', 'createTable tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-88::sofis
CREATE TABLE proy_docs (proydoc_proy_pk INT NOT NULL, proydoc_doc_pk INT NOT NULL, version INT DEFAULT 0 NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-88', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 88, '7:83bfa91683b0132cdbd611216939e651', 'createTable tableName=proy_docs', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-89::sofis
CREATE TABLE proy_indices (proyind_pk INT AUTO_INCREMENT NOT NULL, proyind_riesgo_expo DOUBLE(11, 2) NULL, proyind_riesgo_ultact date NULL, proyind_riesgo_alto INT NULL, proyind_metodo_estado DOUBLE(11, 2) NULL, proyind_metodo_sin_aprobar BIT NULL, proyind_periodo_inicio date NULL, proyind_periodo_fin date NULL, proyind_porc_peso_total DOUBLE(11, 2) NULL, proyind_cal_ind DOUBLE(11, 2) NULL, proyind_cal_pend INT NULL, proyind_fase_color INT NULL, proyind_avance_par_azul INT NULL, proyind_avance_par_verde INT NULL, proyind_anvance_par_rojo INT NULL, proyind_avance_fin_azul INT NULL, proyind_avance_fin_verde INT NULL, proyind_anvance_fin_rojo INT NULL, proyind_fecha_act DATETIME NULL, version INT DEFAULT 0 NULL, proyind_periodo_inicio_ent_fk INT NULL, proyind_periodo_fin_ent_fk INT NULL, proyind_fecha_act_color INT DEFAULT 0 NOT NULL, CONSTRAINT PK_PROY_INDICES PRIMARY KEY (proyind_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-89', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 89, '7:d99a6b440d3f342f045197dda5e5336c', 'createTable tableName=proy_indices', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-90::sofis
CREATE TABLE proy_indices_pre (proyindpre_pk INT AUTO_INCREMENT NOT NULL, proyindpre_proyind_fk INT NOT NULL, proyindpre_mon_fk INT NOT NULL, proyindpre_total DOUBLE(11, 2) NULL, proyindpre_est_pre INT NULL, proyindpre_ac DOUBLE(11, 2) NULL, proyindpre_pv DOUBLE(11, 2) NULL, proyindpre_anio DOUBLE(11, 2) NULL, CONSTRAINT PK_PROY_INDICES_PRE PRIMARY KEY (proyindpre_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-90', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 90, '7:39e42399d96b5145adad852093f7714e', 'createTable tableName=proy_indices_pre', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-91::sofis
CREATE TABLE proy_int (proyint_proy_pk INT NOT NULL, proyint_int_pk INT NOT NULL, version INT DEFAULT 0 NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-91', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 91, '7:ae00abcf3adf47acee80d54fbc7a0e79', 'createTable tableName=proy_int', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-92::sofis
CREATE TABLE proy_lectura_area (proglectarea_area_pk INT NOT NULL, proglectarea_proy_pk INT NOT NULL, version INT DEFAULT 0 NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-92', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 92, '7:bc537ec3031ddc5f9c74e31ed00a67f1', 'createTable tableName=proy_lectura_area', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-93::sofis
CREATE TABLE proy_otros_cat_secundarias (proy_cat_proy_otros_fk INT NOT NULL COMMENT 'ID del Proyecto.', proy_cat_cat_proy_fk INT NOT NULL COMMENT 'ID de la categoria de proyectos.');

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-93', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 93, '7:cbfeeeec2e55b4fe0d6c830c74b16bb7', 'createTable tableName=proy_otros_cat_secundarias', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-94::sofis
CREATE TABLE proy_otros_datos (proy_otr_pk INT AUTO_INCREMENT NOT NULL, proy_otr_eta_fk INT NULL COMMENT 'Estado (etapa)', proy_otr_cont_fk INT NULL COMMENT 'Contratista Principal', proy_otr_ins_eje_fk INT NULL COMMENT 'Instituci', proy_otr_ent_fk INT NULL COMMENT 'Inicio construcci', proy_otr_origen VARCHAR(1000) NULL COMMENT 'Origen Principal de los Recursos.', proy_otr_plazo INT NULL COMMENT 'Plazo estimado de obra en d', proy_otr_observaciones VARCHAR(4000) NULL COMMENT 'Observaciones.', proy_otr_cat_fk INT NULL COMMENT 'Categor', proy_otr_est_pub_fk INT NULL COMMENT 'Estado de Publicaci', CONSTRAINT PK_PROY_OTROS_DATOS PRIMARY KEY (proy_otr_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-94', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 94, '7:d3eaae478ea080b5e5092141fe0e4413', 'createTable tableName=proy_otros_datos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-95::sofis
CREATE TABLE proy_pre (proypre_proy_fk INT NOT NULL, proypre_pre_fk INT NOT NULL, version INT DEFAULT 0 NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-95', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 95, '7:e3670e070c5e8287c29e961614811e68', 'createTable tableName=proy_pre', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-96::sofis
CREATE TABLE proy_publica_hist (proy_publica_pk INT AUTO_INCREMENT NOT NULL, proy_publica_fecha DATETIME NOT NULL, proy_publica_proy_fk INT NOT NULL, proy_publica_usu_fk INT NOT NULL, CONSTRAINT PK_PROY_PUBLICA_HIST PRIMARY KEY (proy_publica_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-96', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 96, '7:f077c91017fb6b68566fda57d0f54cb0', 'createTable tableName=proy_publica_hist', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-97::sofis
CREATE TABLE proy_replanificacion (proyreplan_pk INT AUTO_INCREMENT NOT NULL, proyreplan_proy_fk INT NOT NULL, proyreplan_fecha date NOT NULL, proyreplan_desc VARCHAR(5000) NOT NULL, proyreplan_historial BIT NOT NULL, proyreplan_activo BIT NULL, version INT DEFAULT 0 NULL, proyreplan_generar_linea_base BIT(1) NULL, proyreplan_generar_producto BIT DEFAULT 0 NOT NULL, proyreplan_generar_presupuesto BIT DEFAULT 0 NOT NULL, CONSTRAINT PK_PROY_REPLANIFICACION PRIMARY KEY (proyreplan_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-97', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 97, '7:66da138801b1b2212d826c9968d6fdf3', 'createTable tableName=proy_replanificacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-98::sofis
CREATE TABLE proy_sitact_historico (proy_sitact_hist_pk INT AUTO_INCREMENT NOT NULL, proy_sitact_fecha date NOT NULL, proy_sitact_desc VARCHAR(4000) NULL, proy_sitact_proy_fk INT NOT NULL, proy_sitact_usu_fk INT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_PROY_SITACT_HISTORICO PRIMARY KEY (proy_sitact_hist_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-98', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 98, '7:8ec878ec83795264cf3fcca3519ebffa', 'createTable tableName=proy_sitact_historico', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-99::sofis
CREATE TABLE proy_tags (proytag_proy_pk INT NOT NULL, proytag_area_pk INT NOT NULL, version INT DEFAULT 0 NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-99', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 99, '7:1305978230561d937b8ca0e8093ed49e', 'createTable tableName=proy_tags', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-100::sofis
CREATE TABLE proyectos (proy_pk INT AUTO_INCREMENT NOT NULL, proy_org_fk INT NOT NULL, proy_est_fk INT NOT NULL, proy_est_pendiente_fk INT NULL, proy_sol_aceptacion BIT NULL, proy_area_fk INT NOT NULL, proy_usr_adjunto_fk INT NOT NULL, proy_usr_sponsor_fk INT NOT NULL, proy_usr_gerente_fk INT NOT NULL, proy_usr_pmofed_fk INT NULL, proy_prog_fk INT NULL, proy_risk_fk INT NULL, proy_cro_fk INT NULL, proy_pre_fk INT NULL, proy_proyindices_fk INT NULL, proy_peso INT NULL, proy_descripcion TEXT NULL, proy_objetivo TEXT NULL, proy_obj_publico TEXT NULL, proy_situacion_actual TEXT NULL, proy_leccion_aprendida VARCHAR(256) NULL, proy_nombre VARCHAR(100) NULL, proy_grp VARCHAR(45) NULL, proy_semaforo_amarillo INT NULL, proy_semaforo_rojo INT NULL, proy_activo BIT NULL, proy_fecha_crea DATETIME NULL, proy_fecha_act DATETIME NULL, proy_ult_usuario INT NULL, proy_ult_mod DATETIME NULL, proy_ult_origen VARCHAR(45) NULL, proy_version INT NULL, proy_fecha_est_act DATETIME NULL, proy_id_migrado INT NULL, proy_publicable BIT DEFAULT 1 NOT NULL, proy_otr_dat_fk INT NULL, proy_latlng_fk INT NULL, proy_obj_est_fk INT NULL, proy_factor_impacto TEXT NULL, proy_fecha_act_pub DATETIME NULL, CONSTRAINT PK_PROYECTOS PRIMARY KEY (proy_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-100', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 100, '7:481644e38dc46ec32c9172a452a045c0', 'createTable tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-101::sofis
CREATE TABLE registros_horas (rh_pk INT AUTO_INCREMENT NOT NULL, rh_usu_fk INT NOT NULL, rh_proy_fk INT NOT NULL, rh_ent_fk INT NOT NULL, rh_fecha date NOT NULL, rh_horas DECIMAL(11, 2) NOT NULL, rh_comentario VARCHAR(4000) NULL, rh_aprobado TINYINT(3) NULL, CONSTRAINT PK_REGISTROS_HORAS PRIMARY KEY (rh_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-101', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 101, '7:315dcdcd29e76084f0a62a5846210ee1', 'createTable tableName=registros_horas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-102::sofis
CREATE TABLE riesgos (risk_pk INT AUTO_INCREMENT NOT NULL, risk_proy_fk INT NOT NULL, risk_nombre VARCHAR(3000) NOT NULL, risk_fecha_actu date NULL, risk_probabilidad INT NULL, risk_impacto INT NULL, risk_ent_fk INT NULL, risk_fecha_limite date NULL, risk_efecto VARCHAR(3000) NULL, risk_estategia VARCHAR(3000) NULL, risk_disparador VARCHAR(3000) NULL, risk_contingencia VARCHAR(3000) NULL, risk_superado BIT NULL, risk_fecha_superado date NULL, risk_usuario_superado_fk INT NULL, risk_exposicion DOUBLE NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_RIESGOS PRIMARY KEY (risk_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-102', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 102, '7:4e02ffae167b33a4c98142c823c7d96d', 'createTable tableName=riesgos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-103::sofis
CREATE TABLE roles_interesados (rolint_pk INT AUTO_INCREMENT NOT NULL, rolint_org_fk INT NOT NULL, rolint_nombre VARCHAR(45) NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_ROLES_INTERESADOS PRIMARY KEY (rolint_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-103', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 103, '7:a65230c560e884a19207005558fcc003', 'createTable tableName=roles_interesados', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-104::sofis
CREATE TABLE roles_usuarios (rol_pk INT NOT NULL, rol_nombre VARCHAR(45) NULL, version INT DEFAULT 0 NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-104', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 104, '7:1f77e206d2747837a7774fd2a6015b4f', 'createTable tableName=roles_usuarios', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-105::sofis
CREATE TABLE ss_ayuda (ayu_id INT AUTO_INCREMENT NOT NULL, ayu_codigo VARCHAR(45) NULL, ayu_texto LONGTEXT NULL, ayu_ult_mod_fecha DATETIME NULL, ayu_ult_mod_origen VARCHAR(45) NULL, ayu_ult_mod_usuario VARCHAR(45) NULL, ayu_version INT NULL, CONSTRAINT PK_SS_AYUDA PRIMARY KEY (ayu_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-105', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 105, '7:294ee2b30a773e9a813d7b99007af4f7', 'createTable tableName=ss_ayuda', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-106::sofis
CREATE TABLE ss_categoper (cat_id INT AUTO_INCREMENT NOT NULL, cat_descripcion LONGTEXT NULL, cat_nombre VARCHAR(255) NOT NULL, cat_origen VARCHAR(255) NOT NULL, cat_user_code INT NOT NULL, cat_version INT NULL, cat_vigente BIT NULL, CONSTRAINT PK_SS_CATEGOPER PRIMARY KEY (cat_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-106', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 106, '7:f9e843712efa88e4b9c1c210cdf8636a', 'createTable tableName=ss_categoper', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-107::sofis
CREATE TABLE ss_configuraciones (id INT AUTO_INCREMENT NOT NULL, cnf_org_fk INT NULL, cnf_codigo VARCHAR(145) NULL, cnf_descripcion VARCHAR(245) NULL, cnf_valor VARCHAR(1000) NULL, cnf_protegido BIT NULL, cnf_html BIT NULL, cnf_ult_usuario VARCHAR(45) NULL, cnf_ult_mod VARCHAR(45) NULL, cnf_ult_origen date NULL, cnf_version INT NULL, CONSTRAINT PK_SS_CONFIGURACIONES PRIMARY KEY (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-107', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 107, '7:0a3a78c28f15fceb67e151b388b04058', 'createTable tableName=ss_configuraciones', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-108::sofis
CREATE TABLE ss_departamentos (depto_id INT AUTO_INCREMENT NOT NULL, depto_codigo VARCHAR(255) NULL, depto_nombre VARCHAR(255) NULL, depto_ult_mod DATETIME NULL, err_ult_origen VARCHAR(255) NULL, depto_ult_usuario VARCHAR(255) NULL, depto_version INT NULL, CONSTRAINT PK_SS_DEPARTAMENTOS PRIMARY KEY (depto_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-108', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 108, '7:522811fafe51c91fa1cec5ab8ab02be3', 'createTable tableName=ss_departamentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-109::sofis
CREATE TABLE ss_domicilios (dom_id INT AUTO_INCREMENT NOT NULL, dom_apto VARCHAR(50) NULL, dom_letra VARCHAR(255) NULL, dom_calle VARCHAR(150) NULL, dom_codigo_postal VARCHAR(5) NULL, dom_depto_alt VARCHAR(255) NULL, dom_descripcion LONGTEXT NULL, dom_inmueble_nombre VARCHAR(100) NULL, dom_kilometro VARCHAR(9) NULL, dom_manzana VARCHAR(5) NULL, dom_numero_puerta VARCHAR(5) NULL, dom_oficina VARCHAR(255) NULL, dom_ruta VARCHAR(5) NULL, dom_solar VARCHAR(5) NULL, dom_ult_mod DATETIME NULL, dom_ult_origen VARCHAR(255) NULL, dom_ult_usuario VARCHAR(255) NULL, dom_version INT NULL, dom_depto_id INT NULL, dom_loc_id INT NULL, dom_pai_id INT NULL, dom_par_id SMALLINT NULL, dom_tec_id INT NULL, dom_tvi_id INT NULL, CONSTRAINT PK_SS_DOMICILIOS PRIMARY KEY (dom_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-109', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 109, '7:a02c18c803eb30aecabdc89b20345a1c', 'createTable tableName=ss_domicilios', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-110::sofis
CREATE TABLE ss_errores (id INT NOT NULL, err_codigo VARCHAR(255) NULL, err_descripcion VARCHAR(255) NULL, err_ult_mod DATETIME NULL, err_ult_origen VARCHAR(255) NULL, err_ult_usuario VARCHAR(255) NULL, err_version INT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-110', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 110, '7:e14c1b4ff86fc8cbd0ce5960a85e8c72', 'createTable tableName=ss_errores', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-111::sofis
CREATE TABLE ss_localidades (loc_id INT AUTO_INCREMENT NOT NULL, loc_codigo VARCHAR(255) NULL, loc_nombre VARCHAR(255) NULL, loc_ult_mod DATETIME NULL, loc_ult_origen VARCHAR(255) NULL, loc_ult_usuario VARCHAR(255) NULL, loc_version INT NULL, loc_depto_id INT NULL, CONSTRAINT PK_SS_LOCALIDADES PRIMARY KEY (loc_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-111', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 111, '7:62680614f88a9b98b2cb43815b389cf6', 'createTable tableName=ss_localidades', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-112::sofis
CREATE TABLE ss_noticias (not_id INT AUTO_INCREMENT NOT NULL, not_ampliar VARCHAR(255) NULL, not_codigo VARCHAR(45) NULL, not_contenido LONGTEXT NULL, not_desde DATETIME NULL, not_hasta DATETIME NULL, not_imagen VARCHAR(255) NULL, not_titulo VARCHAR(255) NULL, not_ult_mod_fecha DATETIME NULL, not_ult_mod_origen VARCHAR(45) NULL, not_ult_mod_usuario VARCHAR(45) NULL, not_version INT NULL, CONSTRAINT PK_SS_NOTICIAS PRIMARY KEY (not_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-112', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 112, '7:d97a1622731e7e67b28ecbe4e6245c97', 'createTable tableName=ss_noticias', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-113::sofis
CREATE TABLE ss_operacion (ope_id INT AUTO_INCREMENT NOT NULL, ope_codigo VARCHAR(255) NOT NULL, ope_descripcion LONGTEXT NOT NULL, ope_nombre VARCHAR(255) NOT NULL, ope_origen VARCHAR(255) NOT NULL, ope_tipocampo VARCHAR(255) NOT NULL, ope_user_code INT NOT NULL, ope_version INT NULL, ope_vigente BIT NOT NULL, ope_categoria_id INT NULL, CONSTRAINT PK_SS_OPERACION PRIMARY KEY (ope_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-113', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 113, '7:d854a2fec61504657b773afc6b47758f', 'createTable tableName=ss_operacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-114::sofis
CREATE TABLE ss_paises (pai_id INT AUTO_INCREMENT NOT NULL, pai_codigo2 VARCHAR(255) NULL, pai_codigo3 VARCHAR(255) NULL, pai_comun BIT NULL, pai_habilitado BIT NULL, pai_nombre VARCHAR(255) NULL, pai_ult_mod DATETIME NULL, pai_ult_origen VARCHAR(255) NULL, pai_ult_usuario VARCHAR(255) NULL, pai_version INT NULL, CONSTRAINT PK_SS_PAISES PRIMARY KEY (pai_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-114', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 114, '7:fbeee737f1f5aa1d1acee55484298eea', 'createTable tableName=ss_paises', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-115::sofis
CREATE TABLE ss_paridades (par_id SMALLINT AUTO_INCREMENT NOT NULL, par_codigo VARCHAR(9) NULL, par_descripcion VARCHAR(45) NULL, par_ult_mod_fecha DATETIME NULL, par_ult_mod_origen VARCHAR(45) NULL, par_ult_mod_usuario VARCHAR(45) NULL, par_version INT NULL, CONSTRAINT PK_SS_PARIDADES PRIMARY KEY (par_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-115', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 115, '7:4b87efda65c230dd52c57e7e2d06415c', 'createTable tableName=ss_paridades', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-116::sofis
CREATE TABLE ss_personas (per_id INT AUTO_INCREMENT NOT NULL, per_fec_nac DATETIME NULL, per_nro_doc VARCHAR(45) NULL, per_primer_apellido VARCHAR(100) NOT NULL, per_primer_nombre VARCHAR(100) NULL, per_segundo_apellido VARCHAR(100) NOT NULL, per_segundo_nombre VARCHAR(100) NULL, per_ult_mod_fecha DATETIME NULL, per_ult_mod_origen VARCHAR(45) NULL, per_ult_mod_usuario VARCHAR(45) NULL, per_version INT NULL, per_pais_doc INT NULL, per_tipo_doc INT NULL, CONSTRAINT PK_SS_PERSONAS PRIMARY KEY (per_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-116', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 116, '7:dd3c0fc342427cb4e006a65a4036af39', 'createTable tableName=ss_personas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-117::sofis
CREATE TABLE ss_rel_rol_operacion (rel_rol_operacion_id INT AUTO_INCREMENT NOT NULL, rel_rol_operacion_editable BIT NOT NULL, rel_rol_operacion_origen VARCHAR(255) NOT NULL, rel_rol_operacion_user_code INT NOT NULL, rel_rol_operacion_visible BIT NOT NULL, rel_rol_operacion_operacion_id INT NOT NULL, rel_rol_operacion_rol_id INT NOT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_SS_REL_ROL_OPERACION PRIMARY KEY (rel_rol_operacion_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-117', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 117, '7:365f4a099e992f69feca0c1ed72df95a', 'createTable tableName=ss_rel_rol_operacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-118::sofis
CREATE TABLE ss_rol (rol_id INT AUTO_INCREMENT NOT NULL, rol_cod VARCHAR(255) NOT NULL, rol_descripcion LONGTEXT NULL, rol_nombre VARCHAR(255) NOT NULL, rol_label VARCHAR(150) NOT NULL, rol_origen VARCHAR(255) NOT NULL, rol_user_code INT NOT NULL, rol_version INT NULL, rol_vigente BIT NOT NULL, rol_tipo_usuario BIT NULL, CONSTRAINT PK_SS_ROL PRIMARY KEY (rol_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-118', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 118, '7:f5852b722d4557de3333499bd235ab22', 'createTable tableName=ss_rol', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-119::sofis
CREATE TABLE ss_telefonos (tel_id INT AUTO_INCREMENT NOT NULL, tel_numero VARCHAR(25) NULL, tel_observaciones VARCHAR(255) NULL, tel_prefijo VARCHAR(10) NULL, tel_ult_mod DATETIME NULL, tel_ult_origen VARCHAR(45) NULL, tel_ult_usuario VARCHAR(45) NULL, tel_version INT NULL, tel_tiptel_id INT NULL, CONSTRAINT PK_SS_TELEFONOS PRIMARY KEY (tel_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-119', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 119, '7:b93b5c7063e8d9f58fff4e425f03c89a', 'createTable tableName=ss_telefonos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-120::sofis
CREATE TABLE ss_tipos_documento_persona (tipdocper_id INT AUTO_INCREMENT NOT NULL, tipdocper_codigo VARCHAR(255) NULL, tipdocper_descripcion VARCHAR(255) NULL, tipdocper_habilitado BIT NULL, tipdocper_ult_mod DATETIME NULL, tipdocper_ult_origen VARCHAR(255) NULL, tipdocper_ult_usuario VARCHAR(255) NULL, tipdocper_version INT NULL, CONSTRAINT PK_SS_TIPOS_DOCUMENTO_PERSONA PRIMARY KEY (tipdocper_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-120', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 120, '7:b332110ef2d4f719e12ebe26fa4fd3b5', 'createTable tableName=ss_tipos_documento_persona', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-121::sofis
CREATE TABLE ss_tipos_entrada_colectiva (tec_id INT AUTO_INCREMENT NOT NULL, tec_codigo VARCHAR(255) NULL, tec_descripcion VARCHAR(255) NULL, tec_ult_mod DATETIME NULL, tec_ult_origen VARCHAR(255) NULL, tec_ult_usuario VARCHAR(255) NULL, tec_version INT NULL, CONSTRAINT PK_SS_TIPOS_ENTRADA_COLECTIVA PRIMARY KEY (tec_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-121', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 121, '7:0e3730f146dca4d747934842700f646f', 'createTable tableName=ss_tipos_entrada_colectiva', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-122::sofis
CREATE TABLE ss_tipos_telefono (tipTel_id INT AUTO_INCREMENT NOT NULL, tipTel_codigo VARCHAR(255) NULL, tipTel_descripcion VARCHAR(255) NULL, tipTel_habilitado BIT NULL, tipTel_ult_mod DATETIME NULL, tipTel_ult_origen VARCHAR(255) NULL, tipTel_ult_usuario VARCHAR(255) NULL, tipTel_version INT NULL, CONSTRAINT PK_SS_TIPOS_TELEFONO PRIMARY KEY (tipTel_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-122', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 122, '7:d40d1acdde43c14144afaad18adadd0a', 'createTable tableName=ss_tipos_telefono', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-123::sofis
CREATE TABLE ss_tipos_vialidad (tvi_id INT AUTO_INCREMENT NOT NULL, tvi_abreviacion VARCHAR(255) NULL, tvi_codigo VARCHAR(255) NULL, tvi_descripcion VARCHAR(255) NULL, tvi_ult_mod DATETIME NULL, tvi_ult_origen VARCHAR(255) NULL, tvi_ult_usuario VARCHAR(255) NULL, tvi_version INT NULL, CONSTRAINT PK_SS_TIPOS_VIALIDAD PRIMARY KEY (tvi_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-123', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 123, '7:6e973c02f7a5b87b734ca8696539d8b1', 'createTable tableName=ss_tipos_vialidad', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-124::sofis
CREATE TABLE ss_usu_ofi_roles (usu_ofi_roles_id INT AUTO_INCREMENT NOT NULL, usu_ofi_roles_origen VARCHAR(255) NOT NULL, usu_ofi_roles_user_code INT NOT NULL, usu_ofi_roles_oficina INT NULL, usu_ofi_roles_rol INT NOT NULL, usu_ofi_roles_usuario INT NOT NULL, usu_ofi_roles_usu_area INT NULL, usu_ofi_roles_activo BIT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_SS_USU_OFI_ROLES PRIMARY KEY (usu_ofi_roles_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-124', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 124, '7:babd39eade1f70fc2a869635ff651a59', 'createTable tableName=ss_usu_ofi_roles', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-125::sofis
CREATE TABLE ss_usuario (usu_id INT AUTO_INCREMENT NOT NULL, usu_administrador BIT NULL, usu_cambio_estado_desc LONGTEXT NULL, usu_cod VARCHAR(255) NOT NULL, usu_correo_electronico VARCHAR(255) NOT NULL, usu_cuenta_bloqueada SMALLINT NULL, usu_descripcion LONGTEXT NULL, usu_direccion LONGTEXT NULL, usu_fecha_password DATETIME NULL, usu_fecha_uuid DATETIME NULL, usu_foto LONGBLOB NULL, usu_intentos_fallidos INT NULL, usu_nro_doc VARCHAR(255) NULL, usu_oficina_por_defecto INT NULL, usu_origen LONGTEXT NULL, usu_password VARCHAR(255) NULL, usu_primer_apellido VARCHAR(255) NOT NULL, usu_primer_nombre VARCHAR(255) NOT NULL, usu_registrado BIT NULL, usu_segundo_apellido VARCHAR(255) NULL, usu_segundo_nombre VARCHAR(255) NULL, usu_telefono VARCHAR(255) NULL, usu_celular VARCHAR(255) NULL, usu_user_code INT NULL, usu_uuid_des VARCHAR(255) NULL, usu_version INT NULL, usu_vigente BIT NOT NULL, usu_aprob_facturas BIT NULL, usu_ult_usuario VARCHAR(255) NULL, usu_ult_mod DATETIME NULL, usu_ult_origen VARCHAR(255) NULL, usu_area_org INT NULL, usu_token VARCHAR(100) NULL, usu_token_act DATETIME NULL, usu_ldap_user VARCHAR(45) NULL, CONSTRAINT PK_SS_USUARIO PRIMARY KEY (usu_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-125', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 125, '7:6f911ad9e10817b6633ff3b3b931d09f', 'createTable tableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-126::sofis
CREATE TABLE ss_usuarios_datos (ss_usu_dat_pk INT AUTO_INCREMENT NOT NULL, ss_usu_dat_area_fk INT NOT NULL, ss_usu_dat_usu_fk INT NOT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_SS_USUARIOS_DATOS PRIMARY KEY (ss_usu_dat_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-126', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 126, '7:efbf084055df22976e9400d5b0330d99', 'createTable tableName=ss_usuarios_datos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-127::sofis
CREATE TABLE temas_calidad (tca_pk INT AUTO_INCREMENT NOT NULL, tca_nombre VARCHAR(100) NOT NULL, tca_org_fk INT NOT NULL, CONSTRAINT PK_TEMAS_CALIDAD PRIMARY KEY (tca_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-127', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 127, '7:695f7717e3ccc01d8b9acf5ed55b61b7', 'createTable tableName=temas_calidad', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-128::sofis
CREATE TABLE tipo_documento (tipdoc_pk INT AUTO_INCREMENT NOT NULL, tipodoc_nombre VARCHAR(145) NOT NULL, tipodoc_exigido_desde INT NULL, tipodoc_peso INT NULL, tipodoc_org_fk INT NULL, tipodoc_resum_ejecutivo BIT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_TIPO_DOCUMENTO PRIMARY KEY (tipdoc_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-128', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 128, '7:3b431939e7a848f4b210dc1dccd9fa78', 'createTable tableName=tipo_documento', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-129::sofis
CREATE TABLE tipo_documento_instancia (tipodoc_inst_pk INT AUTO_INCREMENT NOT NULL, tipodoc_inst_tipodoc_fk INT NOT NULL, tipodoc_inst_exigido_desde INT NULL, tipodoc_inst_peso INT NULL, tipodoc_inst_proy_fk INT NULL, tipodoc_inst_prog_fk INT NULL, tipodoc_inst_resum_ejecutivo BIT NULL, version INT DEFAULT 0 NULL, CONSTRAINT PK_TIPO_DOCUMENTO_INSTANCIA PRIMARY KEY (tipodoc_inst_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-129', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 129, '7:278e37c540efa9ecb0efc117b3e48d69', 'createTable tableName=tipo_documento_instancia', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-130::sofis
CREATE TABLE tipo_gasto (tipogas_pk INT AUTO_INCREMENT NOT NULL, tipogas_org_fk INT NULL, tipogas_nombre VARCHAR(150) NOT NULL, CONSTRAINT PK_TIPO_GASTO PRIMARY KEY (tipogas_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-130', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 130, '7:d5bbb6c2d6bf4c2ece4e3c599a414c20', 'createTable tableName=tipo_gasto', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-131::sofis
CREATE TABLE tipo_leccion (tipolec_pk INT AUTO_INCREMENT NOT NULL, tipolec_codigo VARCHAR(45) NOT NULL, tipolec_nombre VARCHAR(150) NOT NULL, CONSTRAINT PK_TIPO_LECCION PRIMARY KEY (tipolec_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-131', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 131, '7:08d214629cbdb39809d8e4eb082b1b78', 'createTable tableName=tipo_leccion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-132::sofis
CREATE TABLE tipos_media (tip_pk INT AUTO_INCREMENT NOT NULL, tip_cod VARCHAR(45) NULL, tip_nombre VARCHAR(245) NULL, CONSTRAINT PK_TIPOS_MEDIA PRIMARY KEY (tip_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-132', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 132, '7:d22498141ad0ee2c88d71a4fb398e576', 'createTable tableName=tipos_media', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-133::sofis
CREATE TABLE valor_calidad_codigos (vca_pk INT AUTO_INCREMENT NOT NULL, vca_codigo VARCHAR(45) NOT NULL, vca_nombre VARCHAR(100) NOT NULL, vca_habilitado BIT NOT NULL, CONSTRAINT PK_VALOR_CALIDAD_CODIGOS PRIMARY KEY (vca_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-133', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 133, '7:bbc512b82302fb523a280d0948139aa7', 'createTable tableName=valor_calidad_codigos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-134::sofis
CREATE TABLE valor_hora (val_hor_pk INT AUTO_INCREMENT NOT NULL, val_hor_usu_fk INT NOT NULL, val_hor_org_fk INT NOT NULL, val_hor_mon_fk INT NOT NULL, val_hor_valor DECIMAL(11, 2) NOT NULL, val_hor_anio INT NOT NULL, CONSTRAINT PK_VALOR_HORA PRIMARY KEY (val_hor_pk));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-134', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 134, '7:6ff6fe23cb388af8706098a51ee827b6', 'createTable tableName=valor_hora', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-135::sofis
ALTER TABLE aud_doc_file ADD PRIMARY KEY (docfile_pk, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-135', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 135, '7:696ee89457311992c5f638036b0779e8', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_doc_file', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-136::sofis
ALTER TABLE aud_pge_configuraciones ADD PRIMARY KEY (cnf_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-136', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 136, '7:7cce67b5c4b935eefa5363e420de2fd7', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_pge_configuraciones', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-137::sofis
ALTER TABLE aud_programas ADD PRIMARY KEY (prog_pk, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-137', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 137, '7:63f6f279c2f740e3e0b4365b77923128', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-138::sofis
ALTER TABLE aud_programas_proyectos ADD PRIMARY KEY (id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-138', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 138, '7:2d2eea8fdfc5dc721ce472e73c0bf962', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_programas_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-139::sofis
ALTER TABLE aud_ss_ayuda ADD PRIMARY KEY (ayu_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-139', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 139, '7:eb211cc9a90c7aa565f9affc24fab54b', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_ayuda', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-140::sofis
ALTER TABLE aud_ss_categoper ADD PRIMARY KEY (cat_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-140', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 140, '7:b1d0ed7818d141846d7894878b1baed3', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_categoper', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-141::sofis
ALTER TABLE aud_ss_configuraciones ADD PRIMARY KEY (id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-141', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 141, '7:021e1df0546d20c93e9dab101e03008a', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_configuraciones', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-142::sofis
ALTER TABLE aud_ss_departamentos ADD PRIMARY KEY (depto_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-142', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 142, '7:de95e1dfb0a81e1989ac1a89eb93e61c', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_departamentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-143::sofis
ALTER TABLE aud_ss_domicilios ADD PRIMARY KEY (dom_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-143', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 143, '7:a89fd8f0ad8f1224477a0d4f6801e51a', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_domicilios', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-144::sofis
ALTER TABLE aud_ss_errores ADD PRIMARY KEY (id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-144', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 144, '7:e82c53094c75e0a2a5ed1b33b9a38038', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_errores', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-145::sofis
ALTER TABLE aud_ss_localidades ADD PRIMARY KEY (loc_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-145', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 145, '7:63c228c63c111325b9738b0216839a94', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_localidades', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-146::sofis
ALTER TABLE aud_ss_noticias ADD PRIMARY KEY (not_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-146', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 146, '7:0537589a63a38d72ba18662e6ce61770', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_noticias', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-147::sofis
ALTER TABLE aud_ss_oficina ADD PRIMARY KEY (ofi_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-147', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 147, '7:fa7348ab4c25a993ab3497b62a948494', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_oficina', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-148::sofis
ALTER TABLE aud_ss_operacion ADD PRIMARY KEY (ope_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-148', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 148, '7:13dc9825735989a484e5ed54f5a31794', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_operacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-149::sofis
ALTER TABLE aud_ss_paises ADD PRIMARY KEY (pai_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-149', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 149, '7:53fc1e8b8bd640d9b954a3fec7967e65', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_paises', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-150::sofis
ALTER TABLE aud_ss_paridades ADD PRIMARY KEY (par_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-150', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 150, '7:e950528167127555ccabc40cf661ec4f', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_paridades', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-151::sofis
ALTER TABLE aud_ss_personas ADD PRIMARY KEY (per_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-151', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 151, '7:8fd3166bbd3c28e2bbef576c6eec3604', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_personas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-152::sofis
ALTER TABLE aud_ss_rel_rol_operacion ADD PRIMARY KEY (rel_rol_operacion_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-152', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 152, '7:c58a4959581f3f591ad013b0660c03ea', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_rel_rol_operacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-153::sofis
ALTER TABLE aud_ss_rol ADD PRIMARY KEY (rol_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-153', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 153, '7:adbda3a3bd37a88f857ed11f8d2f07cb', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_rol', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-154::sofis
ALTER TABLE aud_ss_telefonos ADD PRIMARY KEY (tel_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-154', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 154, '7:832bd4332ecbf937705d62705bcfb98c', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_telefonos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-155::sofis
ALTER TABLE aud_ss_tipos_documento_persona ADD PRIMARY KEY (tipdocper_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-155', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 155, '7:a60171bf2caaa87910fbd56b56d55ee5', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_tipos_documento_persona', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-156::sofis
ALTER TABLE aud_ss_tipos_entrada_colectiva ADD PRIMARY KEY (tec_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-156', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 156, '7:214c12ae85a72c98a49330c8def49b54', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_tipos_entrada_colectiva', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-157::sofis
ALTER TABLE aud_ss_tipos_telefono ADD PRIMARY KEY (tipTel_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-157', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 157, '7:102264e5e655b8c5573b819e6faeb86a', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_tipos_telefono', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-158::sofis
ALTER TABLE aud_ss_tipos_vialidad ADD PRIMARY KEY (tvi_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-158', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 158, '7:6238829854cf3e7650b14e21a8744853', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_tipos_vialidad', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-159::sofis
ALTER TABLE aud_ss_usu_ofi_roles ADD PRIMARY KEY (usu_ofi_roles_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-159', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 159, '7:e7634f08fd7539581aa31f51d9763350', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_usu_ofi_roles', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-160::sofis
ALTER TABLE aud_ss_usuario ADD PRIMARY KEY (usu_id, REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-160', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 160, '7:77811c7ceaa53e9af592a9a2792fcbec', 'addPrimaryKey constraintName=PRIMARY, tableName=aud_ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-161::sofis
ALTER TABLE departamentos ADD PRIMARY KEY (dep_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-161', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 161, '7:b40ead0f45eb8db2c0680a6d7ac467c3', 'addPrimaryKey constraintName=PRIMARY, tableName=departamentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-162::sofis
ALTER TABLE lecapr_areacon ADD PRIMARY KEY (lecaprcon_con_fk, lecaprcon_lecapr_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-162', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 162, '7:332343b44db1c4b44381a88df7d81548', 'addPrimaryKey constraintName=PRIMARY, tableName=lecapr_areacon', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-163::sofis
ALTER TABLE prog_docs ADD PRIMARY KEY (progdocs_prog_pk, progdocs_doc_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-163', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 163, '7:3012b17856308e7b548bccb35f9cd4e4', 'addPrimaryKey constraintName=PRIMARY, tableName=prog_docs', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-164::sofis
ALTER TABLE prog_docs_obl ADD PRIMARY KEY (progdocsobl_docs_pk, progdocsobl_prog_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-164', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 164, '7:f16a0a65757acdbab95e0cae9ee06f99', 'addPrimaryKey constraintName=PRIMARY, tableName=prog_docs_obl', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-165::sofis
ALTER TABLE prog_int ADD PRIMARY KEY (progint_prog_pk, progint_int_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-165', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 165, '7:2ea7a6c83c5cff0d2ef304549214fdbd', 'addPrimaryKey constraintName=PRIMARY, tableName=prog_int', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-166::sofis
ALTER TABLE prog_lectura_area ADD PRIMARY KEY (proglectarea_prog_pk, proglectarea_area_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-166', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 166, '7:76a7aac1efd5c36d1e4381f9115a5a6f', 'addPrimaryKey constraintName=PRIMARY, tableName=prog_lectura_area', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-167::sofis
ALTER TABLE prog_pre ADD PRIMARY KEY (progpre_prog_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-167', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 167, '7:5e2b57656ef7ee1f655fd33ee028ca10', 'addPrimaryKey constraintName=PRIMARY, tableName=prog_pre', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-168::sofis
ALTER TABLE prog_tags ADD PRIMARY KEY (progtag_prog_pk, progtag_area_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-168', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 168, '7:412b12ac33f37f5ece157c32bd423c2e', 'addPrimaryKey constraintName=PRIMARY, tableName=prog_tags', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-169::sofis
ALTER TABLE proy_docs ADD PRIMARY KEY (proydoc_proy_pk, proydoc_doc_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-169', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 169, '7:c196fe2e5f550b16448426678eb10464', 'addPrimaryKey constraintName=PRIMARY, tableName=proy_docs', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-170::sofis
ALTER TABLE proy_int ADD PRIMARY KEY (proyint_proy_pk, proyint_int_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-170', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 170, '7:be488eb91425ecc8a17fd950321b51b0', 'addPrimaryKey constraintName=PRIMARY, tableName=proy_int', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-171::sofis
ALTER TABLE proy_lectura_area ADD PRIMARY KEY (proglectarea_area_pk, proglectarea_proy_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-171', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 171, '7:52a5e548f1e76280935005108ea2a434', 'addPrimaryKey constraintName=PRIMARY, tableName=proy_lectura_area', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-172::sofis
ALTER TABLE proy_otros_cat_secundarias ADD PRIMARY KEY (proy_cat_proy_otros_fk, proy_cat_cat_proy_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-172', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 172, '7:6f6fb04558c89a4f1cbaecc70ff99a92', 'addPrimaryKey constraintName=PRIMARY, tableName=proy_otros_cat_secundarias', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-173::sofis
ALTER TABLE proy_pre ADD PRIMARY KEY (proypre_proy_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-173', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 173, '7:00e703ebb66be59fda1d082f9f1a012e', 'addPrimaryKey constraintName=PRIMARY, tableName=proy_pre', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-174::sofis
ALTER TABLE proy_tags ADD PRIMARY KEY (proytag_proy_pk, proytag_area_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-174', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 174, '7:42a8099b9429f69c2d6483966fb64ace', 'addPrimaryKey constraintName=PRIMARY, tableName=proy_tags', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-175::sofis
ALTER TABLE roles_usuarios ADD PRIMARY KEY (rol_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-175', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 175, '7:12f8ba163bf7d647428509fe2dee1fe2', 'addPrimaryKey constraintName=PRIMARY, tableName=roles_usuarios', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-176::sofis
ALTER TABLE ss_errores ADD PRIMARY KEY (id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-176', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 176, '7:4e639db41be473e2ffdc106fdb4e87b2', 'addPrimaryKey constraintName=PRIMARY, tableName=ss_errores', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-177::sofis
ALTER TABLE doc_file ADD CONSTRAINT UK_n76rhuste8gi3p3jq7m91j7iq UNIQUE (docfile_doc_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-177', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 177, '7:d989c535ace44fc86d253d929736393c', 'addUniqueConstraint constraintName=UK_n76rhuste8gi3p3jq7m91j7iq, tableName=doc_file', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-178::sofis
ALTER TABLE ss_ayuda ADD CONSTRAINT ayu_codigo UNIQUE (ayu_codigo);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-178', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 178, '7:cb50de6b435f63357e7f3b4db306d4fe', 'addUniqueConstraint constraintName=ayu_codigo, tableName=ss_ayuda', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-179::sofis
ALTER TABLE ss_noticias ADD CONSTRAINT not_codigo UNIQUE (not_codigo);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-179', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 179, '7:590c21433151587752c06023f7d4e2d0', 'addUniqueConstraint constraintName=not_codigo, tableName=ss_noticias', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-180::sofis
ALTER TABLE objetivos_estrategicos ADD CONSTRAINT obj_est_org_fk_nombre UNIQUE (obj_est_org_fk, obj_est_nombre);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-180', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 180, '7:2e36f8549e991fd198fd2e972e56d130', 'addUniqueConstraint constraintName=obj_est_org_fk_nombre, tableName=objetivos_estrategicos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-181::sofis
ALTER TABLE participantes ADD CONSTRAINT part_usu_fk UNIQUE (part_usu_fk, part_proy_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-181', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 181, '7:b6d9ba1295ffd19c669166d7c00e2635', 'addUniqueConstraint constraintName=part_usu_fk, tableName=participantes', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-182::sofis
ALTER TABLE ss_usuario ADD CONSTRAINT usu_ldap_user_UNIQUE UNIQUE (usu_ldap_user);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-182', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 182, '7:bf1eaf26927752775b74936637142d8d', 'addUniqueConstraint constraintName=usu_ldap_user_UNIQUE, tableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-183::sofis
CREATE INDEX FK1A6A26477E1BCA41 ON personas(pers_orga_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-183', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 183, '7:1592a51ce3fabd7173e21cd8d32335b9', 'createIndex indexName=FK1A6A26477E1BCA41, tableName=personas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-184::sofis
CREATE INDEX FK1E264BA5DF74E053 ON aud_ss_configuraciones(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-184', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 184, '7:51a111e328803ec6f549b229bd6ba35f', 'createIndex indexName=FK1E264BA5DF74E053, tableName=aud_ss_configuraciones', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-185::sofis
CREATE INDEX FK2ABC90A6DF74E053 ON aud_ss_tipos_entrada_colectiva(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-185', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 185, '7:04e137430db1b900c129dd6332b0573e', 'createIndex indexName=FK2ABC90A6DF74E053, tableName=aud_ss_tipos_entrada_colectiva', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-186::sofis
CREATE INDEX FK2CEF84E0641CB6FC ON ss_telefonos(tel_tiptel_id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-186', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 186, '7:8b09e5d64e35abf1de855e5a64e2a221', 'createIndex indexName=FK2CEF84E0641CB6FC, tableName=ss_telefonos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-187::sofis
CREATE INDEX FK2D1C5EED2F892058 ON ss_domicilios(dom_pai_id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-187', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 187, '7:e6dba8dfc8871790cad5b2669a9a4a3f', 'createIndex indexName=FK2D1C5EED2F892058, tableName=ss_domicilios', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-188::sofis
CREATE INDEX FK2D1C5EED2FF8B8CF ON ss_domicilios(dom_depto_id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-188', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 188, '7:6243a1b2ee770d6d44c6e00862d4d428', 'createIndex indexName=FK2D1C5EED2FF8B8CF, tableName=ss_domicilios', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-189::sofis
CREATE INDEX FK2D1C5EED567EC7E ON ss_domicilios(dom_tvi_id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-189', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 189, '7:92104ffb644775bb48d3afd99b61acc0', 'createIndex indexName=FK2D1C5EED567EC7E, tableName=ss_domicilios', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-190::sofis
CREATE INDEX FK2D1C5EEDC8B0CD82 ON ss_domicilios(dom_loc_id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-190', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 190, '7:25fd8a695787301968cab7fdecf50d6c', 'createIndex indexName=FK2D1C5EEDC8B0CD82, tableName=ss_domicilios', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-191::sofis
CREATE INDEX FK2D1C5EEDFBC55737 ON ss_domicilios(dom_par_id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-191', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 191, '7:38e16572cab54c94b0c28ed1e1670939', 'createIndex indexName=FK2D1C5EEDFBC55737, tableName=ss_domicilios', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-192::sofis
CREATE INDEX FK2D1C5EEDFCA9657E ON ss_domicilios(dom_tec_id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-192', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 192, '7:ee994fe44fd928e9de3c01e1b7daa1d7', 'createIndex indexName=FK2D1C5EEDFCA9657E, tableName=ss_domicilios', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-193::sofis
CREATE INDEX FK317B0718DF74E053 ON aud_ss_usu_ofi_roles(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-193', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 193, '7:5f8fc2d9633595f10900e2bf1a664c1b', 'createIndex indexName=FK317B0718DF74E053, tableName=aud_ss_usu_ofi_roles', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-194::sofis
CREATE INDEX FK36A8E0B6DF74E053 ON aud_programas_proyectos(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-194', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 194, '7:9fc78d09b2eadc280a94fc00b0009a70', 'createIndex indexName=FK36A8E0B6DF74E053, tableName=aud_programas_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-195::sofis
CREATE INDEX FK5037FBFEDF74E053 ON aud_ss_noticias(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-195', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 195, '7:c9c0496cb7714fc36afb50d3ae58621e', 'createIndex indexName=FK5037FBFEDF74E053, tableName=aud_ss_noticias', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-196::sofis
CREATE INDEX FK533EE3DFDF74E053 ON aud_ss_rol(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-196', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 196, '7:53f14353302291ede0cc521cc390557c', 'createIndex indexName=FK533EE3DFDF74E053, tableName=aud_ss_rol', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-197::sofis
CREATE INDEX FK5F493B64DF74E053 ON aud_ss_tipos_telefono(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-197', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 197, '7:6d407db6f947e4031e0e46a732a37a01', 'createIndex indexName=FK5F493B64DF74E053, tableName=aud_ss_tipos_telefono', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-198::sofis
CREATE INDEX FK5F6900B9DF74E053 ON aud_ss_paises(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-198', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 198, '7:bc01c1eb60af56458abdf87ff14bbfd6', 'createIndex indexName=FK5F6900B9DF74E053, tableName=aud_ss_paises', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-199::sofis
CREATE INDEX FK6151DF1BDF74E053 ON aud_ss_oficina(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-199', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 199, '7:4d6e6afa17a79c0ef0d7af71b30b646a', 'createIndex indexName=FK6151DF1BDF74E053, tableName=aud_ss_oficina', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-200::sofis
CREATE INDEX FK61DDABF9269E90AA ON ss_operacion(ope_categoria_id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-200', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 200, '7:7f46a513a6589202251997467fe5e87d', 'createIndex indexName=FK61DDABF9269E90AA, tableName=ss_operacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-201::sofis
CREATE INDEX FK65521EC6DF74E053 ON aud_ss_errores(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-201', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 201, '7:5ff6dc8b4bc047ef9ca70b7aebaf6373', 'createIndex indexName=FK65521EC6DF74E053, tableName=aud_ss_errores', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-202::sofis
CREATE INDEX FK69423495DF74E053 ON aud_pge_configuraciones(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-202', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 202, '7:36a08bdb41595abca21d4b3de8ca000e', 'createIndex indexName=FK69423495DF74E053, tableName=aud_pge_configuraciones', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-203::sofis
CREATE INDEX FK6E9F4363BDC3F659 ON ss_rel_rol_operacion(rel_rol_operacion_operacion_id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-203', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 203, '7:038b5b4178364dbd5812ba3895ab372c', 'createIndex indexName=FK6E9F4363BDC3F659, tableName=ss_rel_rol_operacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-204::sofis
CREATE INDEX FK6E9F4363C25B4A39 ON ss_rel_rol_operacion(rel_rol_operacion_rol_id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-204', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 204, '7:42c7c78869ad16a377027545c58b4cae', 'createIndex indexName=FK6E9F4363C25B4A39, tableName=ss_rel_rol_operacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-205::sofis
CREATE INDEX FK73BE520FDF74E053 ON aud_ss_telefonos(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-205', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 205, '7:4213fe9cf0682b14062a9d82f5ad7777', 'createIndex indexName=FK73BE520FDF74E053, tableName=aud_ss_telefonos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-206::sofis
CREATE INDEX FK7E2A928ADF74E053 ON aud_ss_ayuda(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-206', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 206, '7:5931daa70d9b02175dbc7a9858f7028e', 'createIndex indexName=FK7E2A928ADF74E053, tableName=aud_ss_ayuda', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-207::sofis
CREATE INDEX FK82B382B1DF74E053 ON aud_ss_tipos_documento_persona(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-207', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 207, '7:7de7b8a999b35dd8da9b90269a1f4c79', 'createIndex indexName=FK82B382B1DF74E053, tableName=aud_ss_tipos_documento_persona', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-208::sofis
CREATE INDEX FKA2EE3756DF74E053 ON aud_ss_categoper(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-208', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 208, '7:3e799cfd4a06369836115cd1a8f6f5b2', 'createIndex indexName=FKA2EE3756DF74E053, tableName=aud_ss_categoper', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-209::sofis
CREATE INDEX FKA8AC7928DF74E053 ON aud_ss_operacion(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-209', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 209, '7:9bbf9457134162ee44f8907ea7692694', 'createIndex indexName=FKA8AC7928DF74E053, tableName=aud_ss_operacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-210::sofis
CREATE INDEX FKB58E953EDF74E053 ON aud_ss_usuario(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-210', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 210, '7:3d0b27ac6184a50f603ff2a21722c182', 'createIndex indexName=FKB58E953EDF74E053, tableName=aud_ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-211::sofis
CREATE INDEX FKB64469698353C0A7 ON ss_usu_ofi_roles(usu_ofi_roles_usuario);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-211', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 211, '7:a45e82a984cae86e13b3057268ce5090', 'createIndex indexName=FKB64469698353C0A7, tableName=ss_usu_ofi_roles', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-212::sofis
CREATE INDEX FKB64469698D342B69 ON ss_usu_ofi_roles(usu_ofi_roles_rol);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-212', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 212, '7:fd3238021306f284d054958a558ff96e', 'createIndex indexName=FKB64469698D342B69, tableName=ss_usu_ofi_roles', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-213::sofis
CREATE INDEX FKBDD320E7DF74E053 ON aud_ss_localidades(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-213', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 213, '7:6031f2ce111319b6959bde7a99095030', 'createIndex indexName=FKBDD320E7DF74E053, tableName=aud_ss_localidades', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-214::sofis
CREATE INDEX FKBF89754648010DF9 ON ss_personas(per_tipo_doc);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-214', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 214, '7:8dadb37799be7d634d04eeff4aca5e8c', 'createIndex indexName=FKBF89754648010DF9, tableName=ss_personas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-215::sofis
CREATE INDEX FKBF897546F9B07F4F ON ss_personas(per_pais_doc);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-215', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 215, '7:4565177775d4eec4ee615e7cfa184c81', 'createIndex indexName=FKBF897546F9B07F4F, tableName=ss_personas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-216::sofis
CREATE INDEX FKC027379EDF74E053 ON aud_ss_domicilios(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-216', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 216, '7:0a0eaf2870952c68f15f167df61ab713', 'createIndex indexName=FKC027379EDF74E053, tableName=aud_ss_domicilios', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-217::sofis
CREATE INDEX FKC24C637DF74E053 ON aud_ss_personas(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-217', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 217, '7:ef5f2c5f25f13bf094396dcab4705640', 'createIndex indexName=FKC24C637DF74E053, tableName=aud_ss_personas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-218::sofis
CREATE INDEX FKC64199B67D2164DD ON programas(prog_usr_sponsor_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-218', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 218, '7:a69779c7abb39bcd59d93f50b9e40e64', 'createIndex indexName=FKC64199B67D2164DD, tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-219::sofis
CREATE INDEX FKC64199B688211D04 ON programas(prog_usr_pmofed_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-219', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 219, '7:736200cb18910d8de5b189dc80315d23', 'createIndex indexName=FKC64199B688211D04, tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-220::sofis
CREATE INDEX FKC64199B6BF64D85C ON programas(prog_usr_adjunto_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-220', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 220, '7:411b58f47b5d0c39833176cf4d46807f', 'createIndex indexName=FKC64199B6BF64D85C, tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-221::sofis
CREATE INDEX FKC73DF59DDF74E053 ON aud_ss_paridades(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-221', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 221, '7:3fffc933e17d2c4a01ca16b9c38d6bf0', 'createIndex indexName=FKC73DF59DDF74E053, tableName=aud_ss_paridades', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-222::sofis
CREATE INDEX FKCA442AFFDF74E053 ON aud_ss_departamentos(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-222', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 222, '7:c88ad05b71c2f48610631080de9e9814', 'createIndex indexName=FKCA442AFFDF74E053, tableName=aud_ss_departamentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-223::sofis
CREATE INDEX FKCE17192DF74E053 ON aud_ss_rel_rol_operacion(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-223', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 223, '7:193f88c27d283d21b484a6dbc7ce8c1b', 'createIndex indexName=FKCE17192DF74E053, tableName=aud_ss_rel_rol_operacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-224::sofis
CREATE INDEX FKE442A80E5962FE8B ON proyectos(proy_usr_sponsor_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-224', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 224, '7:944bbecf43ae9ea38f107c69c63309a3', 'createIndex indexName=FKE442A80E5962FE8B, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-225::sofis
CREATE INDEX FKE442A80E9BA6720A ON proyectos(proy_usr_adjunto_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-225', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 225, '7:abec490b302aee18f592c0a47fffa05c', 'createIndex indexName=FKE442A80E9BA6720A, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-226::sofis
CREATE INDEX FKE442A80EDA44AEF7 ON proyectos(proy_usr_gerente_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-226', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 226, '7:236f6c4d15ac7d5fd2431a9f4fba49e2', 'createIndex indexName=FKE442A80EDA44AEF7, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-227::sofis
CREATE INDEX FKEF82E378E2A77891 ON ss_localidades(loc_depto_id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-227', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 227, '7:ba28eed42e004326aa78ac7b7e8accf3', 'createIndex indexName=FKEF82E378E2A77891, tableName=ss_localidades', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-228::sofis
CREATE INDEX FKF0430A0CDF74E053 ON aud_ss_tipos_vialidad(REV);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-228', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 228, '7:dee62730d98cea3d098331e471a4ce3f', 'createIndex indexName=FKF0430A0CDF74E053, tableName=aud_ss_tipos_vialidad', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-229::sofis
CREATE INDEX FK_4b0pq8qh2f6u7ei11lh0atbf8 ON busq_filtro(busq_filtro_usu_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-229', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 229, '7:e770e9d8d497e47e58371450edd8aeaa', 'createIndex indexName=FK_4b0pq8qh2f6u7ei11lh0atbf8, tableName=busq_filtro', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-230::sofis
CREATE INDEX FK_9c8og633e1waprs81i6ayorba ON devengado(dev_adq_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-230', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 230, '7:1049c2803a91dc48bd64ce4a92f39630', 'createIndex indexName=FK_9c8og633e1waprs81i6ayorba, tableName=devengado', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-231::sofis
CREATE INDEX FK_a726jrgroi6p90dip38n70ftp ON busq_filtro(busq_filtro_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-231', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 231, '7:cd8b306ae359edefbac315f47d1bb0d4', 'createIndex indexName=FK_a726jrgroi6p90dip38n70ftp, tableName=busq_filtro', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-232::sofis
CREATE INDEX FK_hb3weyc8xvdrbp62k3u1halnb ON categoria_proyectos(cat_icono_marker);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-232', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 232, '7:efa1eaeb8edfa09728620613bd15fb9e', 'createIndex indexName=FK_hb3weyc8xvdrbp62k3u1halnb, tableName=categoria_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-233::sofis
CREATE INDEX FK_hh3lr9l8qt7isgvniyif3w6tw ON categoria_proyectos(cat_icono);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-233', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 233, '7:ed223c417c1e8d033df69a9f6cc1f431', 'createIndex indexName=FK_hh3lr9l8qt7isgvniyif3w6tw, tableName=categoria_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-234::sofis
CREATE INDEX FK_j151q3d1wiqgx10w9n92hwx5j ON areas(area_director);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-234', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 234, '7:8647ee1632f119c77717f89ef42359c1', 'createIndex indexName=FK_j151q3d1wiqgx10w9n92hwx5j, tableName=areas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-235::sofis
CREATE INDEX FK_n5tl58dqv18cs790jbmejxom2 ON ambito(amb_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-235', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 235, '7:21cecd8ec7c54d476d0d02de54c6b9ef', 'createIndex indexName=FK_n5tl58dqv18cs790jbmejxom2, tableName=ambito', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-236::sofis
CREATE INDEX adq_comp_prod_fk_idx ON adquisicion(adq_componente_producto_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-236', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 236, '7:c926679968447bc1977e9c2e360e06b5', 'createIndex indexName=adq_comp_prod_fk_idx, tableName=adquisicion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-237::sofis
CREATE INDEX adq_fuente_idx ON adquisicion(adq_fuente_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-237', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 237, '7:c60ecf26a51996955f2097322c1fe61a', 'createIndex indexName=adq_fuente_idx, tableName=adquisicion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-238::sofis
CREATE INDEX adq_moneda_idx ON adquisicion(adq_moneda_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-238', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 238, '7:3606f51313b4913c29daf13a0e31670f', 'createIndex indexName=adq_moneda_idx, tableName=adquisicion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-239::sofis
CREATE INDEX adq_pre_fk_idx ON adquisicion(adq_pre_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-239', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 239, '7:055bc48f39f529f0a5e9553ffef207ee', 'createIndex indexName=adq_pre_fk_idx, tableName=adquisicion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-240::sofis
CREATE INDEX adq_proc_comp_fk ON adquisicion(adq_procedimiento_compra_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-240', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 240, '7:3411bd6484d2d063f56de27c33609228', 'createIndex indexName=adq_proc_comp_fk, tableName=adquisicion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-241::sofis
CREATE INDEX adq_prov_orga_idx ON adquisicion(adq_prov_orga_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-241', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 241, '7:b0f369713d408cc3c44db97383e85579', 'createIndex indexName=adq_prov_orga_idx, tableName=adquisicion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-242::sofis
CREATE INDEX adquisicion_ss_usuario_FK ON adquisicion(adq_compartida_usuario_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-242', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 242, '7:e699607c58e09ad7d0ac534e079040a9', 'createIndex indexName=adquisicion_ss_usuario_FK, tableName=adquisicion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-243::sofis
CREATE INDEX area_activo_idx ON areas(area_activo);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-243', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 243, '7:8b54254a619f5520b98e0241849dbde9', 'createIndex indexName=area_activo_idx, tableName=areas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-244::sofis
CREATE INDEX cal_actualizacion_idx ON calidad(cal_actualizacion);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-244', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 244, '7:7b68eef048ab78ae75d0ed3fd4b4ec00', 'createIndex indexName=cal_actualizacion_idx, tableName=calidad', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-245::sofis
CREATE INDEX cal_ent_fk_idx ON calidad(cal_ent_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-245', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 245, '7:a44416460c9260fbcf28a09d943e6271', 'createIndex indexName=cal_ent_fk_idx, tableName=calidad', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-246::sofis
CREATE INDEX cal_prod_fk_idx ON calidad(cal_prod_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-246', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 246, '7:497e343c0d5caec4c0a96a001f8bec5a', 'createIndex indexName=cal_prod_fk_idx, tableName=calidad', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-247::sofis
CREATE INDEX cal_proy_fk_idx ON calidad(cal_proy_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-247', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 247, '7:c92d5938faaf13cfdbd3df9ac1d0f4b1', 'createIndex indexName=cal_proy_fk_idx, tableName=calidad', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-248::sofis
CREATE INDEX cal_tca_fk_idx ON calidad(cal_tca_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-248', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 248, '7:a71db3afe385ebd01e4b704480ac247e', 'createIndex indexName=cal_tca_fk_idx, tableName=calidad', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-249::sofis
CREATE INDEX cal_tipo ON calidad(cal_tipo);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-249', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 249, '7:f3644578d2a58358ee28be15f84ee9bf', 'createIndex indexName=cal_tipo, tableName=calidad', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-250::sofis
CREATE INDEX cal_valor_idx ON calidad(cal_vca_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-250', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 250, '7:dbd007761385bb6eb2852831f97c0562', 'createIndex indexName=cal_valor_idx, tableName=calidad', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-251::sofis
CREATE INDEX cat_org_fk_idx ON categoria_proyectos(cat_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-251', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 251, '7:0b02321c5db5ba3b2fc616701b53c97d', 'createIndex indexName=cat_org_fk_idx, tableName=categoria_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-252::sofis
CREATE INDEX cat_proy_activo_idx ON categoria_proyectos(cat_proy_activo);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-252', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 252, '7:59cf51f0b5920f09516970cf635089e6', 'createIndex indexName=cat_proy_activo_idx, tableName=categoria_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-253::sofis
CREATE INDEX cat_tipo_idx ON categoria_proyectos(cat_tipo);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-253', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 253, '7:0970acabd5319d4a81137ee30ba40906', 'createIndex indexName=cat_tipo_idx, tableName=categoria_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-254::sofis
CREATE INDEX cnf_codigo ON ss_configuraciones(cnf_codigo);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-254', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 254, '7:ccc0d7de3713a31e9f09a0e559e6e58a', 'createIndex indexName=cnf_codigo, tableName=ss_configuraciones', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-255::sofis
CREATE INDEX cnf_org_fk ON ss_configuraciones(cnf_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-255', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 255, '7:975df7d4574535daf61bbae75be0815b', 'createIndex indexName=cnf_org_fk, tableName=ss_configuraciones', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-256::sofis
CREATE INDEX con_org_fk_idx ON area_conocimiento(con_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-256', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 256, '7:3ba7151c16d03777fe47d564bb3d916e', 'createIndex indexName=con_org_fk_idx, tableName=area_conocimiento', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-257::sofis
CREATE INDEX con_padre_fk_idx ON area_conocimiento(con_padre_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-257', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 257, '7:8b11faa9179f9b6eca28ca5d5ee5aa66', 'createIndex indexName=con_padre_fk_idx, tableName=area_conocimiento', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-258::sofis
CREATE INDEX dev_anio_idx ON devengado(dev_anio);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-258', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 258, '7:c210677b621503501bf6dc341b59bf1b', 'createIndex indexName=dev_anio_idx, tableName=devengado', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-259::sofis
CREATE INDEX dev_mes_idx ON devengado(dev_mes);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-259', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 259, '7:4d0ffc6405afdb1627e059a629ca5c4b', 'createIndex indexName=dev_mes_idx, tableName=devengado', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-260::sofis
CREATE INDEX docs_docfile_fk_idx ON documentos(docs_docfile_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-260', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 260, '7:da7325b17945c27069d52deb8676571d', 'createIndex indexName=docs_docfile_fk_idx, tableName=documentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-261::sofis
CREATE INDEX docs_pago_fk_idx ON documentos(docs_pago_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-261', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 261, '7:ff8644c8b6d28baeca78679641600ff7', 'createIndex indexName=docs_pago_fk_idx, tableName=documentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-262::sofis
CREATE INDEX ent_coord_usu_fk_idx ON entregables(ent_coord_usu_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-262', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 262, '7:655ddabfcb13e14ebb86e4261c037aa8', 'createIndex indexName=ent_coord_usu_fk_idx, tableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-263::sofis
CREATE INDEX ent_fin_idx ON entregables(ent_fin);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-263', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 263, '7:4933b024e93e82088a29cff8a7f1c864', 'createIndex indexName=ent_fin_idx, tableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-264::sofis
CREATE INDEX ent_inicio_idx ON entregables(ent_inicio);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-264', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 264, '7:3ab936652892dc31710cf6d9576b1e99', 'createIndex indexName=ent_inicio_idx, tableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-265::sofis
CREATE INDEX ent_parent_idx ON entregables(ent_parent);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-265', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 265, '7:9ee65765210b620628e153a7a7053499', 'createIndex indexName=ent_parent_idx, tableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-266::sofis
CREATE INDEX ent_progreso_idx ON entregables(ent_progreso);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-266', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 266, '7:7c070762f825e02e634281610d01b47e', 'createIndex indexName=ent_progreso_idx, tableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-267::sofis
CREATE INDEX enthist_ent_fk_idx ON ent_hist_linea_base(enthist_ent_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-267', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 267, '7:54da4a6e6fcfcec64f635e2f331308d2', 'createIndex indexName=enthist_ent_fk_idx, tableName=ent_hist_linea_base', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-268::sofis
CREATE INDEX enthist_replan_fk_idx ON ent_hist_linea_base(enthist_replan_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-268', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 268, '7:80e21b3b9d869d572c5a6db353dd72e8', 'createIndex indexName=enthist_replan_fk_idx, tableName=ent_hist_linea_base', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-269::sofis
CREATE INDEX entre_ante_idx ON plantilla_entregables(p_entregable_ant_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-269', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 269, '7:ffa8c7c8f5cb0733b19c47d08176a5ad', 'createIndex indexName=entre_ante_idx, tableName=plantilla_entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-270::sofis
CREATE INDEX entre_lineabase_entFk_idx ON lineabase_historico(lineabase_entFk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-270', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 270, '7:2ce69c3304e4b9c7fdd4d42dab9e772f', 'createIndex indexName=entre_lineabase_entFk_idx, tableName=lineabase_historico', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-271::sofis
CREATE INDEX entregable_fk_idx ON documentos(docs_entregable_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-271', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 271, '7:dc50f830fbb02ead345a88e130bfacdc', 'createIndex indexName=entregable_fk_idx, tableName=documentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-272::sofis
CREATE INDEX est_codigo ON estados(est_codigo);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-272', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 272, '7:cf5b1a6aa9da725c702c44a7b1799bee', 'createIndex indexName=est_codigo, tableName=estados', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-273::sofis
CREATE INDEX eta_org_fk_cod_idx ON etapa(eta_org_fk, eta_codigo);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-273', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 273, '7:551543684a1eb4fd1c43ef149e084caf', 'createIndex indexName=eta_org_fk_cod_idx, tableName=etapa', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-274::sofis
CREATE INDEX eta_org_fk_idx ON etapa(eta_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-274', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 274, '7:d091774938d32a2fdd65dc72c35f69e1', 'createIndex indexName=eta_org_fk_idx, tableName=etapa', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-275::sofis
CREATE INDEX fk_AREAS_AREAS1_idx ON areas(area_padre);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-275', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 275, '7:18d51c3a8a388ca06f831a65dd010c6a', 'createIndex indexName=fk_AREAS_AREAS1_idx, tableName=areas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-276::sofis
CREATE INDEX fk_AREAS_ORGANISMOS1_idx ON areas(area_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-276', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 276, '7:6f0e338e42fd7c543af791c2e825e3ae', 'createIndex indexName=fk_AREAS_ORGANISMOS1_idx, tableName=areas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-277::sofis
CREATE INDEX fk_AREAS_TAGS_AREAS_TAGS1_idx ON areas_tags(areatag_padre_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-277', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 277, '7:98be8c55530cfd0e25109bb4825d23b0', 'createIndex indexName=fk_AREAS_TAGS_AREAS_TAGS1_idx, tableName=areas_tags', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-278::sofis
CREATE INDEX fk_AREAS_TAGS_ORGANISMOS1_idx ON areas_tags(areatag_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-278', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 278, '7:dc4f964676f52bfb6a04a25286788ddf', 'createIndex indexName=fk_AREAS_TAGS_ORGANISMOS1_idx, tableName=areas_tags', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-279::sofis
CREATE INDEX fk_AREA_ORGANI_INT_PROVE_ORGANISMOS1_idx ON area_organi_int_prove(areaorgintprov_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-279', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 279, '7:48dad423b73cc2dc3939b29392028dca', 'createIndex indexName=fk_AREA_ORGANI_INT_PROVE_ORGANISMOS1_idx, tableName=area_organi_int_prove', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-280::sofis
CREATE INDEX fk_AREA_ORGANI_INT_PROVE_ORGANI_INT_PROVE1_idx ON area_organi_int_prove(areaorgintprov_orga_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-280', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 280, '7:0cbfca870cd49a22afa50bdf2c1bff61', 'createIndex indexName=fk_AREA_ORGANI_INT_PROVE_ORGANI_INT_PROVE1_idx, tableName=area_organi_int_prove', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-281::sofis
CREATE INDEX fk_DOCS_OBL_PROGRAMAS1_idx ON prog_docs_obl(progdocsobl_prog_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-281', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 281, '7:52b6132152d82c8b98ad861f7c9516f1', 'createIndex indexName=fk_DOCS_OBL_PROGRAMAS1_idx, tableName=prog_docs_obl', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-282::sofis
CREATE INDEX fk_INTERESADOS_ORGANIZACION1_idx ON interesados(int_orga_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-282', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 282, '7:3f572e039504c786963ca04936050370', 'createIndex indexName=fk_INTERESADOS_ORGANIZACION1_idx, tableName=interesados', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-283::sofis
CREATE INDEX fk_INTERESADOS_PERSONAS1_idx ON interesados(int_pers_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-283', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 283, '7:f48f0631fc5a9d04ad08c83ca65c2819', 'createIndex indexName=fk_INTERESADOS_PERSONAS1_idx, tableName=interesados', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-284::sofis
CREATE INDEX fk_INTERESADOS_ROLES_INTERESADOS1_idx ON interesados(int_rolint_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-284', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 284, '7:0b3fd98a604e3d44460328c353679f94', 'createIndex indexName=fk_INTERESADOS_ROLES_INTERESADOS1_idx, tableName=interesados', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-285::sofis
CREATE INDEX fk_PERSONAS_ROLES_USUARIOS1_idx ON personas(pers_rol_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-285', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 285, '7:7a437a2fb8d7a80b6a56c65e6613fbaf', 'createIndex indexName=fk_PERSONAS_ROLES_USUARIOS1_idx, tableName=personas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-286::sofis
CREATE INDEX fk_PROGRAMAS_AREAS1_idx ON programas(prog_area_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-286', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 286, '7:75b74a687d33a71a9b002e0a644b0c3b', 'createIndex indexName=fk_PROGRAMAS_AREAS1_idx, tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-287::sofis
CREATE INDEX fk_PROGRAMAS_ESTADOS1_idx ON programas(prog_est_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-287', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 287, '7:bb5fb629ed907c29d9790f528974ef15', 'createIndex indexName=fk_PROGRAMAS_ESTADOS1_idx, tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-288::sofis
CREATE INDEX fk_PROGRAMAS_ORGANISMOS1_idx ON programas(prog_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-288', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 288, '7:8d90bfa3e048c9ec5679029d5cab634f', 'createIndex indexName=fk_PROGRAMAS_ORGANISMOS1_idx, tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-289::sofis
CREATE INDEX fk_PROGRAMAS_USUARIOS1_idx ON programas(prog_usr_gerente_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-289', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 289, '7:8fdd4e00e41563c36642dac0641adcbb', 'createIndex indexName=fk_PROGRAMAS_USUARIOS1_idx, tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-290::sofis
CREATE INDEX fk_PROG_INT_INTERESADOS1_idx ON prog_int(progint_int_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-290', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 290, '7:9f073156e3fdefa05ac7c8abd2f792d4', 'createIndex indexName=fk_PROG_INT_INTERESADOS1_idx, tableName=prog_int', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-291::sofis
CREATE INDEX fk_PROG_LECTURA_AREA_AREAS1_idx ON prog_lectura_area(proglectarea_area_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-291', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 291, '7:c0201e537fe166f6ab87d82de1564c2a', 'createIndex indexName=fk_PROG_LECTURA_AREA_AREAS1_idx, tableName=prog_lectura_area', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-292::sofis
CREATE INDEX fk_PROG_LECTURA_AREA_AREAS1_idx ON proy_lectura_area(proglectarea_area_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-292', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 292, '7:f27c577e86b979f36d188d598e97fb2a', 'createIndex indexName=fk_PROG_LECTURA_AREA_AREAS1_idx, tableName=proy_lectura_area', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-293::sofis
CREATE INDEX fk_PROG_TAGS_AREAS_TAGS1_idx ON prog_tags(progtag_area_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-293', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 293, '7:302eab9e98ec5965c1a58cc63fcf62d1', 'createIndex indexName=fk_PROG_TAGS_AREAS_TAGS1_idx, tableName=prog_tags', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-294::sofis
CREATE INDEX fk_PROYECTOS_AREAS1_idx ON proyectos(proy_area_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-294', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 294, '7:95992845e5972b84a97daf437c76a790', 'createIndex indexName=fk_PROYECTOS_AREAS1_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-295::sofis
CREATE INDEX fk_PROYECTOS_CRONOGRAMAS1_idx ON proyectos(proy_cro_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-295', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 295, '7:9affe887e1ff25db8277f83403b1534b', 'createIndex indexName=fk_PROYECTOS_CRONOGRAMAS1_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-296::sofis
CREATE INDEX fk_PROYECTOS_ESTADOS1_idx ON proyectos(proy_est_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-296', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 296, '7:cf71ec472b2e29bd819820205d95b6ce', 'createIndex indexName=fk_PROYECTOS_ESTADOS1_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-297::sofis
CREATE INDEX fk_PROYECTOS_ORGANISMOS1_idx ON proyectos(proy_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-297', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 297, '7:f96251dd094fbb84751f8c7571884693', 'createIndex indexName=fk_PROYECTOS_ORGANISMOS1_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-298::sofis
CREATE INDEX fk_PROYECTOS_PROGRAMAS1_idx ON proyectos(proy_prog_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-298', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 298, '7:9970e4d2d01998ab7669ce9b1b308ecc', 'createIndex indexName=fk_PROYECTOS_PROGRAMAS1_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-299::sofis
CREATE INDEX fk_PROYECTOS_RIESGOS1_idx ON proyectos(proy_risk_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-299', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 299, '7:f0cd08610cb1a71d8fa9d2260b1ecda8', 'createIndex indexName=fk_PROYECTOS_RIESGOS1_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-300::sofis
CREATE INDEX fk_PROYECTOS_USUARIOS4_idx ON proyectos(proy_usr_pmofed_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-300', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 300, '7:6adaf8382182f1efa6b0771d4342f122', 'createIndex indexName=fk_PROYECTOS_USUARIOS4_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-301::sofis
CREATE INDEX fk_PROY_INT_INTERESADOS1_idx ON proy_int(proyint_int_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-301', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 301, '7:0d5eb19f7b23164239d1f70d3b3490ae', 'createIndex indexName=fk_PROY_INT_INTERESADOS1_idx, tableName=proy_int', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-302::sofis
CREATE INDEX fk_PROY_LECTURA_AREA_PROYECTOS1_idx ON proy_lectura_area(proglectarea_proy_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-302', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 302, '7:91bb2ab88f3f83556d95432813c12366', 'createIndex indexName=fk_PROY_LECTURA_AREA_PROYECTOS1_idx, tableName=proy_lectura_area', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-303::sofis
CREATE INDEX fk_PROY_TAGS_PROYECTOS1_idx ON proy_tags(proytag_area_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-303', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 303, '7:d6ed26974463d19a0acab73cdb4006d0', 'createIndex indexName=fk_PROY_TAGS_PROYECTOS1_idx, tableName=proy_tags', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-304::sofis
CREATE INDEX fk_Prog_docs_DOCUMENTOS1_idx ON prog_docs(progdocs_doc_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-304', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 304, '7:55e980752e8bba3384a72c8ef71ce224', 'createIndex indexName=fk_Prog_docs_DOCUMENTOS1_idx, tableName=prog_docs', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-305::sofis
CREATE INDEX fk_Proy_docs_PROYECTOS1_idx ON proy_docs(proydoc_doc_pk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-305', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 305, '7:eb42ba7c1532bcafe0b9b33543b59597', 'createIndex indexName=fk_Proy_docs_PROYECTOS1_idx, tableName=proy_docs', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-306::sofis
CREATE INDEX fk_ROLES_INTERESADOS_ORGANISMOS1_idx ON roles_interesados(rolint_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-306', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 306, '7:8b8a0277695145d934f2d48ee46f3a9b', 'createIndex indexName=fk_ROLES_INTERESADOS_ORGANISMOS1_idx, tableName=roles_interesados', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-307::sofis
CREATE INDEX fk_componente_producto_1_idx ON componente_producto(com_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-307', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 307, '7:ba233704b4a236a6ca2e0cf365dcd227', 'createIndex indexName=fk_componente_producto_1_idx, tableName=componente_producto', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-308::sofis
CREATE INDEX fk_participantes_proyectos1 ON participantes(part_proy_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-308', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 308, '7:49c860564ee4c3147fcc4a1c3457ba73', 'createIndex indexName=fk_participantes_proyectos1, tableName=participantes', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-309::sofis
CREATE INDEX fk_procedimiento_compra_1_idx ON procedimiento_compra(proc_comp_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-309', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 309, '7:9efec277f825be3d74cdb8245579eb16', 'createIndex indexName=fk_procedimiento_compra_1_idx, tableName=procedimiento_compra', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-310::sofis
CREATE INDEX fk_registrohoras_entregables1 ON registros_horas(rh_ent_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-310', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 310, '7:7a7bccae6674ca1545418ea509b52a52', 'createIndex indexName=fk_registrohoras_entregables1, tableName=registros_horas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-311::sofis
CREATE INDEX fk_registrohoras_proyectos1 ON registros_horas(rh_proy_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-311', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 311, '7:8b0a07d1f3ba3893c3f4f00e1a12048f', 'createIndex indexName=fk_registrohoras_proyectos1, tableName=registros_horas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-312::sofis
CREATE INDEX fk_registrohoras_usuarios1 ON registros_horas(rh_usu_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-312', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 312, '7:1c221ae6941a8bfd4ee6a53245a2ed57', 'createIndex indexName=fk_registrohoras_usuarios1, tableName=registros_horas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-313::sofis
CREATE INDEX fue_org_fk_idx ON fuente_financiamiento(fue_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-313', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 313, '7:cda9ffc747be9c5ba8723cadbab40d3a', 'createIndex indexName=fue_org_fk_idx, tableName=fuente_financiamiento', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-314::sofis
CREATE INDEX gantt_task_gantt_fk_idx ON entregables(ent_cro_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-314', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 314, '7:3fb0f2cc9f28ff735d1711ecd9db3514', 'createIndex indexName=gantt_task_gantt_fk_idx, tableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-315::sofis
CREATE INDEX gas_aprobado_idx ON gastos(gas_aprobado);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-315', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 315, '7:b0626be66bd69593eef056da6b12b610', 'createIndex indexName=gas_aprobado_idx, tableName=gastos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-316::sofis
CREATE INDEX gas_fecha_idx ON gastos(gas_fecha);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-316', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 316, '7:d2e050e4995b1b7709f5f55f1d359f4f', 'createIndex indexName=gas_fecha_idx, tableName=gastos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-317::sofis
CREATE INDEX gas_mon_fk_idx ON gastos(gas_mon_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-317', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 317, '7:fc8cf887c9239f10722e2101da94e9af', 'createIndex indexName=gas_mon_fk_idx, tableName=gastos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-318::sofis
CREATE INDEX gas_proy_fk_idx ON gastos(gas_proy_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-318', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 318, '7:b4813c8376c9d4ac4de50beccce4f33f', 'createIndex indexName=gas_proy_fk_idx, tableName=gastos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-319::sofis
CREATE INDEX gas_tipo_fk_idx ON gastos(gas_tipo_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-319', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 319, '7:64722668ced12bebb63de77ea2708238', 'createIndex indexName=gas_tipo_fk_idx, tableName=gastos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-320::sofis
CREATE INDEX gas_usu_fk_idx ON gastos(gas_usu_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-320', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 320, '7:da42ce3d0dc74fc22b8bd8456e7fce78', 'createIndex indexName=gas_usu_fk_idx, tableName=gastos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-321::sofis
CREATE INDEX int_ent_fk_idx ON interesados(int_ent_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-321', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 321, '7:4fd09823e84303e90f55e816eef78d65', 'createIndex indexName=int_ent_fk_idx, tableName=interesados', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-322::sofis
CREATE INDEX latlang_dep_fk_idx ON latlng_proyectos(latlang_dep_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-322', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 322, '7:2971568b7dc0d03eed66848df3024aed', 'createIndex indexName=latlang_dep_fk_idx, tableName=latlng_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-323::sofis
CREATE INDEX latlng_proy_fk_idx ON latlng_proyectos(latlng_proy_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-323', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 323, '7:8c858972bd26db777d7647f874d2cde9', 'createIndex indexName=latlng_proy_fk_idx, tableName=latlng_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-324::sofis
CREATE INDEX lecapr_activo_idx ON lecc_aprendidas(lecapr_activo);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-324', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 324, '7:90f096734cd8e2bdce8b6e4742233eb3', 'createIndex indexName=lecapr_activo_idx, tableName=lecc_aprendidas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-325::sofis
CREATE INDEX lecapr_fecha_idx ON lecc_aprendidas(lecapr_fecha);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-325', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 325, '7:31124e689e8c9d724a6a48393700af5e', 'createIndex indexName=lecapr_fecha_idx, tableName=lecc_aprendidas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-326::sofis
CREATE INDEX lecapr_org_fk_idx ON lecc_aprendidas(lecapr_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-326', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 326, '7:35dfec12c2214e46804e9a9e127a2789', 'createIndex indexName=lecapr_org_fk_idx, tableName=lecc_aprendidas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-327::sofis
CREATE INDEX lecapr_proy_fk_idx ON lecc_aprendidas(lecapr_proy_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-327', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 327, '7:77af201aa87f01cc95837784464d3698', 'createIndex indexName=lecapr_proy_fk_idx, tableName=lecc_aprendidas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-328::sofis
CREATE INDEX lecapr_tipo_fk_idx ON lecc_aprendidas(lecapr_tipo_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-328', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 328, '7:4b4012c40f0c1bd4349d950f4b0b781c', 'createIndex indexName=lecapr_tipo_fk_idx, tableName=lecc_aprendidas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-329::sofis
CREATE INDEX lecapr_usr_fk_idx ON lecc_aprendidas(lecapr_usr_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-329', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 329, '7:e3eb71e38c05419d6c26e43615c6cb69', 'createIndex indexName=lecapr_usr_fk_idx, tableName=lecc_aprendidas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-330::sofis
CREATE INDEX lecaprcon_lecapr_fk_idx ON lecapr_areacon(lecaprcon_lecapr_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-330', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 330, '7:46ec31357deae4440793ecba19750693', 'createIndex indexName=lecaprcon_lecapr_fk_idx, tableName=lecapr_areacon', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-331::sofis
CREATE INDEX mail_tmp_org_fk_idx ON mails_template(mail_tmp_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-331', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 331, '7:4a88d1c90f4835a030850174599f7b71', 'createIndex indexName=mail_tmp_org_fk_idx, tableName=mails_template', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-332::sofis
CREATE INDEX media_proy_fk_idx ON media_proyectos(media_proy_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-332', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 332, '7:1014400718cc7607bcfffc4078f7447f', 'createIndex indexName=media_proy_fk_idx, tableName=media_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-333::sofis
CREATE INDEX media_tipo_fk_idx ON media_proyectos(media_tipo_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-333', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 333, '7:c699fd9355533b049f5b29767e6d71b1', 'createIndex indexName=media_tipo_fk_idx, tableName=media_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-334::sofis
CREATE INDEX media_usr_mod_fk_idx ON media_proyectos(media_usr_mod_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-334', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 334, '7:3b9b516968fba3ef032ecd96f658a263', 'createIndex indexName=media_usr_mod_fk_idx, tableName=media_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-335::sofis
CREATE INDEX media_usr_pub_fk_idx ON media_proyectos(media_usr_pub_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-335', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 335, '7:7b03cca2da7a2d73ea6f0af8fe4066a7', 'createIndex indexName=media_usr_pub_fk_idx, tableName=media_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-336::sofis
CREATE INDEX media_usr_rech_fk_idx ON media_proyectos(media_usr_rech_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-336', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 336, '7:054b9bca021e6a33424b86ce1bc79f4f', 'createIndex indexName=media_usr_rech_fk_idx, tableName=media_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-337::sofis
CREATE INDEX notenv_fecha_idx ON notificacion_envio(notenv_fecha);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-337', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 337, '7:9b1dc1d56592302ebb086dbde3b14317', 'createIndex indexName=notenv_fecha_idx, tableName=notificacion_envio', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-338::sofis
CREATE INDEX notinst_not_fk_idx ON notificacion_instancia(notinst_not_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-338', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 338, '7:a7a070846a6d32f9ec470c4dc19512dd', 'createIndex indexName=notinst_not_fk_idx, tableName=notificacion_instancia', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-339::sofis
CREATE INDEX notinst_proy_fk_idx ON notificacion_instancia(notinst_proy_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-339', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 339, '7:c34512d329703d56a226956ffe5ff35a', 'createIndex indexName=notinst_proy_fk_idx, tableName=notificacion_instancia', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-340::sofis
CREATE INDEX obj_est_org_fk_idx ON objetivos_estrategicos(obj_est_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-340', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 340, '7:1d4c948963906edbb2474db58ac1c0f3', 'createIndex indexName=obj_est_org_fk_idx, tableName=objetivos_estrategicos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-341::sofis
CREATE INDEX org_token_idx ON organismos(org_token);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-341', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 341, '7:c4163004cd4ca33f0bbac278fc5eb522', 'createIndex indexName=org_token_idx, tableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-342::sofis
CREATE INDEX orga_amb_fk_idx ON organi_int_prove(orga_amb_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-342', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 342, '7:3555920aba11540e660cf071c850e727', 'createIndex indexName=orga_amb_fk_idx, tableName=organi_int_prove', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-343::sofis
CREATE INDEX orga_org_fk_idx ON organi_int_prove(orga_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-343', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 343, '7:d8fec181e07882c29fded1a017a71134', 'createIndex indexName=orga_org_fk_idx, tableName=organi_int_prove', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-344::sofis
CREATE INDEX orga_proveedor_idx ON organi_int_prove(orga_proveedor);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-344', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 344, '7:9bc8b560740a5180b1a4262c720f7454', 'createIndex indexName=orga_proveedor_idx, tableName=organi_int_prove', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-345::sofis
CREATE INDEX pag_adq_fk_idx ON pagos(pag_adq_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-345', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 345, '7:5a69381c517123a5ab36a727591df417', 'createIndex indexName=pag_adq_fk_idx, tableName=pagos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-346::sofis
CREATE INDEX pag_confirmar_idx ON pagos(pag_confirmar);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-346', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 346, '7:73b741dc442210dba655151c164c8b98', 'createIndex indexName=pag_confirmar_idx, tableName=pagos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-347::sofis
CREATE INDEX pag_ent_fk_idx ON pagos(pag_ent_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-347', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 347, '7:2ad4e8b964d2229dfeea9f249a06eeef', 'createIndex indexName=pag_ent_fk_idx, tableName=pagos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-348::sofis
CREATE INDEX pag_fecha_planificada_idx ON pagos(pag_fecha_planificada);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-348', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 348, '7:58e10a471979455ac54b6993a387180d', 'createIndex indexName=pag_fecha_planificada_idx, tableName=pagos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-349::sofis
CREATE INDEX pag_fecha_real_idx ON pagos(pag_fecha_real);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-349', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 349, '7:b27d11eb29ab7d05827d0b7a844f486f', 'createIndex indexName=pag_fecha_real_idx, tableName=pagos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-350::sofis
CREATE INDEX pagos_organi_int_prove_FK ON pagos(pag_contr_organizacion_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-350', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 350, '7:91290d24ef592324b48a007526efff11', 'createIndex indexName=pagos_organi_int_prove_FK, tableName=pagos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-351::sofis
CREATE INDEX part_activo_idx ON participantes(part_activo);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-351', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 351, '7:2df9cf349d2cda2a72fac01c2b052320', 'createIndex indexName=part_activo_idx, tableName=participantes', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-352::sofis
CREATE INDEX part_ent_fk_idx ON participantes(part_ent_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-352', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 352, '7:31d9051b61f17af935babc689f59b4e1', 'createIndex indexName=part_ent_fk_idx, tableName=participantes', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-353::sofis
CREATE INDEX plantilla_cro_idx ON plantilla_entregables(p_entregable_p_cro_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-353', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 353, '7:6416cf46ce61fa17d7b793f78f9813ce', 'createIndex indexName=plantilla_cro_idx, tableName=plantilla_entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-354::sofis
CREATE INDEX pre_fuente_organi_idx ON presupuesto(pre_fuente_organi_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-354', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 354, '7:5575791423c7e50c3cc30079a569bbc5', 'createIndex indexName=pre_fuente_organi_idx, tableName=presupuesto', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-355::sofis
CREATE INDEX pre_moneda_idx ON presupuesto(pre_moneda);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-355', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 355, '7:3d50c645ff9e2982ed2cf52c6253dd71', 'createIndex indexName=pre_moneda_idx, tableName=presupuesto', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-356::sofis
CREATE INDEX prod_ent_fk_idx ON productos(prod_ent_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-356', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 356, '7:72bb2225bab2e185369a6de076e3bd9e', 'createIndex indexName=prod_ent_fk_idx, tableName=productos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-357::sofis
CREATE INDEX prodmes_anio_idx ON prod_mes(prodmes_anio);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-357', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 357, '7:b02ed382748f4a50c97734e883fef62c', 'createIndex indexName=prodmes_anio_idx, tableName=prod_mes', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-358::sofis
CREATE INDEX prodmes_mes_idx ON prod_mes(prodmes_mes);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-358', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 358, '7:5297d3d8b3a9e0e22430c91fee700628', 'createIndex indexName=prodmes_mes_idx, tableName=prod_mes', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-359::sofis
CREATE INDEX prodmes_prod_fk_idx ON prod_mes(prodmes_prod_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-359', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 359, '7:c70b0635be871c9361e987162472b836', 'createIndex indexName=prodmes_prod_fk_idx, tableName=prod_mes', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-360::sofis
CREATE INDEX prog_activo_idx ON programas(prog_activo);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-360', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 360, '7:8fc418eb1996de769d9bc3af5f80d22c', 'createIndex indexName=prog_activo_idx, tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-361::sofis
CREATE INDEX prog_cro_fk_idx ON programas(prog_cro_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-361', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 361, '7:5362732b26f01992036c65da99acbaf3', 'createIndex indexName=prog_cro_fk_idx, tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-362::sofis
CREATE INDEX prog_est_pendiente_fk_idx ON programas(prog_est_pendiente_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-362', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 362, '7:dc8d0df6c747a1c341d786e41aa001b8', 'createIndex indexName=prog_est_pendiente_fk_idx, tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-363::sofis
CREATE INDEX prog_fecha_act_idx ON programas(prog_fecha_act);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-363', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 363, '7:fb79c44820f9a9062765f7b88bee8d27', 'createIndex indexName=prog_fecha_act_idx, tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-364::sofis
CREATE INDEX prog_fecha_crea_idx ON programas(prog_fecha_crea);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-364', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 364, '7:bfe49dd743cd4b2cf1b7813c2047939a', 'createIndex indexName=prog_fecha_crea_idx, tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-365::sofis
CREATE INDEX prog_nombre_idx ON programas(prog_nombre);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-365', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 365, '7:65cbc4fe391736886060acac61ea7012', 'createIndex indexName=prog_nombre_idx, tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-366::sofis
CREATE INDEX prog_obj_est_fk_idx ON programas(prog_obj_est_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-366', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 366, '7:1e330ffb5135ecfd9464d380095165d9', 'createIndex indexName=prog_obj_est_fk_idx, tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-367::sofis
CREATE INDEX prog_pre_fk_idx ON programas(prog_pre_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-367', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 367, '7:747921ff15bed30e80659ba311775e5c', 'createIndex indexName=prog_pre_fk_idx, tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-368::sofis
CREATE INDEX prog_progindices_fk_idx ON programas(prog_progindices_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-368', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 368, '7:b4568d47923b08945be4a25286aac34c', 'createIndex indexName=prog_progindices_fk_idx, tableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-369::sofis
CREATE INDEX progpre_pre_fk_idx ON prog_pre(progpre_pre_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-369', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 369, '7:f1ade92109d24df14eed83074cc0beb5', 'createIndex indexName=progpre_pre_fk_idx, tableName=prog_pre', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-370::sofis
CREATE INDEX proy_activo_idx ON proyectos(proy_activo);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-370', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 370, '7:49a12bbe829d78c568a7616a08d25bbc', 'createIndex indexName=proy_activo_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-371::sofis
CREATE INDEX proy_cat_cat_proy_fk_idx ON proy_otros_cat_secundarias(proy_cat_cat_proy_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-371', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 371, '7:ac8fdff929e173c2ec4f3c3037eaeb6b', 'createIndex indexName=proy_cat_cat_proy_fk_idx, tableName=proy_otros_cat_secundarias', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-372::sofis
CREATE INDEX proy_est_pendiente_fk_idx ON proyectos(proy_est_pendiente_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-372', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 372, '7:b5c24dad9acbab281054f697c3f0f309', 'createIndex indexName=proy_est_pendiente_fk_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-373::sofis
CREATE INDEX proy_fecha_act_idx ON proyectos(proy_fecha_act);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-373', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 373, '7:088494412e9c43113851e0bcfc7dba7f', 'createIndex indexName=proy_fecha_act_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-374::sofis
CREATE INDEX proy_fecha_crea_idx ON proyectos(proy_fecha_crea);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-374', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 374, '7:2a9d3b7f556dfee9c07027eb5757d54a', 'createIndex indexName=proy_fecha_crea_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-375::sofis
CREATE INDEX proy_latlng_fk_idx ON proyectos(proy_latlng_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-375', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 375, '7:72fb06a3d891bc396ae5849e2d27de78', 'createIndex indexName=proy_latlng_fk_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-376::sofis
CREATE INDEX proy_nombre_idx ON proyectos(proy_nombre);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-376', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 376, '7:b030dc0890f07eb34bfdad8fad7fd25b', 'createIndex indexName=proy_nombre_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-377::sofis
CREATE INDEX proy_obj_est_fk_idx ON proyectos(proy_obj_est_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-377', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 377, '7:6f78deecd8539a7a358c57d8095aa08c', 'createIndex indexName=proy_obj_est_fk_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-378::sofis
CREATE INDEX proy_otr_cat_fk_idx ON proy_otros_datos(proy_otr_cat_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-378', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 378, '7:24f0c1ff1c49c0d1a65b5cb57a0bd4d7', 'createIndex indexName=proy_otr_cat_fk_idx, tableName=proy_otros_datos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-379::sofis
CREATE INDEX proy_otr_cont_fk_idx ON proy_otros_datos(proy_otr_cont_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-379', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 379, '7:1e73ff9df015c626b7884af575396a47', 'createIndex indexName=proy_otr_cont_fk_idx, tableName=proy_otros_datos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-380::sofis
CREATE INDEX proy_otr_dat_fk_idx ON proyectos(proy_otr_dat_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-380', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 380, '7:f4232fc691c817df33f0faf035dbca7d', 'createIndex indexName=proy_otr_dat_fk_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-381::sofis
CREATE INDEX proy_otr_ent_fk_idx ON proy_otros_datos(proy_otr_ent_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-381', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 381, '7:af86c693f825feb77e2b3a38e7cef636', 'createIndex indexName=proy_otr_ent_fk_idx, tableName=proy_otros_datos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-382::sofis
CREATE INDEX proy_otr_est_pub_fk_idx ON proy_otros_datos(proy_otr_est_pub_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-382', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 382, '7:9ed04cfb93b18d40d781b80980026e13', 'createIndex indexName=proy_otr_est_pub_fk_idx, tableName=proy_otros_datos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-383::sofis
CREATE INDEX proy_otr_eta_fk_idx ON proy_otros_datos(proy_otr_eta_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-383', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 383, '7:14c07cacf183ecdaf8f9e4602f5b91c2', 'createIndex indexName=proy_otr_eta_fk_idx, tableName=proy_otros_datos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-384::sofis
CREATE INDEX proy_otr_ins_eje_fk_idx ON proy_otros_datos(proy_otr_ins_eje_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-384', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 384, '7:28e24a5c6b7a7aefd2f964ade309f5a4', 'createIndex indexName=proy_otr_ins_eje_fk_idx, tableName=proy_otros_datos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-385::sofis
CREATE INDEX proy_pre_fk_idx ON proyectos(proy_pre_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-385', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 385, '7:ca32d471e85f4d18a32f4cd4c5e39cf6', 'createIndex indexName=proy_pre_fk_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-386::sofis
CREATE INDEX proy_proyindices_fk_idx ON proyectos(proy_proyindices_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-386', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 386, '7:59b51d5786344aeccb518a0d67b97f5d', 'createIndex indexName=proy_proyindices_fk_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-387::sofis
CREATE INDEX proy_publica_proy_fk_idx ON proy_publica_hist(proy_publica_proy_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-387', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 387, '7:2e9ce43e962c53ec498e70a3273cc321', 'createIndex indexName=proy_publica_proy_fk_idx, tableName=proy_publica_hist', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-388::sofis
CREATE INDEX proy_publica_usu_fk_idx ON proy_publica_hist(proy_publica_usu_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-388', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 388, '7:9396842d056db9184e2d711e815f8684', 'createIndex indexName=proy_publica_usu_fk_idx, tableName=proy_publica_hist', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-389::sofis
CREATE INDEX proy_publicable_idx ON proyectos(proy_publicable);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-389', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 389, '7:59ac442f73397eba8c3c5612df4f4a34', 'createIndex indexName=proy_publicable_idx, tableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-390::sofis
CREATE INDEX proy_sitact_proy_fk_idx ON proy_sitact_historico(proy_sitact_proy_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-390', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 390, '7:4d891261caf1d4e2e5477ac6088eadc6', 'createIndex indexName=proy_sitact_proy_fk_idx, tableName=proy_sitact_historico', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-391::sofis
CREATE INDEX proyind_periodo_fin_ent_fk_idx ON proy_indices(proyind_periodo_fin_ent_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-391', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 391, '7:6fd885e282a341601e6e4905126664cb', 'createIndex indexName=proyind_periodo_fin_ent_fk_idx, tableName=proy_indices', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-392::sofis
CREATE INDEX proyind_periodo_fin_idx ON proy_indices(proyind_periodo_fin);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-392', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 392, '7:b0fbfdbad9e94929b1face3e329c0ae6', 'createIndex indexName=proyind_periodo_fin_idx, tableName=proy_indices', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-393::sofis
CREATE INDEX proyind_periodo_inicio_ent_fk_idx ON proy_indices(proyind_periodo_inicio_ent_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-393', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 393, '7:c1b8910cbd2ff4635381cbf4d8b4def7', 'createIndex indexName=proyind_periodo_inicio_ent_fk_idx, tableName=proy_indices', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-394::sofis
CREATE INDEX proyind_periodo_inicio_idx ON proy_indices(proyind_periodo_inicio);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-394', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 394, '7:b795e7fafb5f72636c98505094f6858a', 'createIndex indexName=proyind_periodo_inicio_idx, tableName=proy_indices', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-395::sofis
CREATE INDEX proypre_pre_fk_idx ON proy_pre(proypre_pre_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-395', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 395, '7:b9a48296cc30071b502f17d4261dfc48', 'createIndex indexName=proypre_pre_fk_idx, tableName=proy_pre', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-396::sofis
CREATE INDEX proyreplan_activo_idx ON proy_replanificacion(proyreplan_activo);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-396', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 396, '7:a6e62c05610a2e9325237518a0f96e81', 'createIndex indexName=proyreplan_activo_idx, tableName=proy_replanificacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-397::sofis
CREATE INDEX proyreplan_fecha_idx ON proy_replanificacion(proyreplan_fecha);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-397', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 397, '7:39da2833d35173d1de78551f4e03c552', 'createIndex indexName=proyreplan_fecha_idx, tableName=proy_replanificacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-398::sofis
CREATE INDEX proyreplan_proy_fk_idx ON proy_replanificacion(proyreplan_proy_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-398', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 398, '7:6260504c1d850def72f45ede0e4c84bd', 'createIndex indexName=proyreplan_proy_fk_idx, tableName=proy_replanificacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-399::sofis
CREATE INDEX rh_aprobado_idx ON registros_horas(rh_aprobado);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-399', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 399, '7:e1c3e82246df66a4a3a877fd93eeb357', 'createIndex indexName=rh_aprobado_idx, tableName=registros_horas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-400::sofis
CREATE INDEX rh_fecha_idx ON registros_horas(rh_fecha);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-400', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 400, '7:332287229021397dcc8dc4b740c4fa3f', 'createIndex indexName=rh_fecha_idx, tableName=registros_horas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-401::sofis
CREATE INDEX risk_ent_fk_idx ON riesgos(risk_ent_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-401', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 401, '7:b5aae64bf4926d6315f6482e93246200', 'createIndex indexName=risk_ent_fk_idx, tableName=riesgos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-402::sofis
CREATE INDEX risk_fecha_actu_idx ON riesgos(risk_fecha_actu);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-402', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 402, '7:c487bdd51490fca4fbafa829e6dd98eb', 'createIndex indexName=risk_fecha_actu_idx, tableName=riesgos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-403::sofis
CREATE INDEX risk_fecha_limite_idx ON riesgos(risk_fecha_limite);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-403', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 403, '7:f444b2db54cfd12fa8c360ee075c1632', 'createIndex indexName=risk_fecha_limite_idx, tableName=riesgos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-404::sofis
CREATE INDEX risk_fecha_superado_idx ON riesgos(risk_fecha_superado);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-404', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 404, '7:6def5a15f708720e81042be508636718', 'createIndex indexName=risk_fecha_superado_idx, tableName=riesgos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-405::sofis
CREATE INDEX risk_proy_fk_idx ON riesgos(risk_proy_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-405', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 405, '7:361902893e870555b3041d56453d6519', 'createIndex indexName=risk_proy_fk_idx, tableName=riesgos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-406::sofis
CREATE INDEX risk_superado_idx ON riesgos(risk_superado);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-406', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 406, '7:2e7bb14779ff38ce1331f538084979f5', 'createIndex indexName=risk_superado_idx, tableName=riesgos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-407::sofis
CREATE INDEX risk_usuario_superado_fk_fk_idx ON riesgos(risk_usuario_superado_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-407', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 407, '7:1652efbabcf3af2c5889298422b20f66', 'createIndex indexName=risk_usuario_superado_fk_fk_idx, tableName=riesgos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-408::sofis
CREATE INDEX tca_org_fk_idx ON temas_calidad(tca_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-408', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 408, '7:b174bb2a85159f3f8870cc549b595307', 'createIndex indexName=tca_org_fk_idx, tableName=temas_calidad', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-409::sofis
CREATE INDEX tipo_doc_fk_idx ON documentos(docs_tipodoc_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-409', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 409, '7:a48fa79388faeb06244380d607bb3d22', 'createIndex indexName=tipo_doc_fk_idx, tableName=documentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-410::sofis
CREATE INDEX tipodoc_inst_tipodoc_fk_idx ON tipo_documento_instancia(tipodoc_inst_tipodoc_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-410', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 410, '7:c50b2522e68e1f032ae353dc16757dc6', 'createIndex indexName=tipodoc_inst_tipodoc_fk_idx, tableName=tipo_documento_instancia', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-411::sofis
CREATE INDEX tipodoc_org_fk_idx ON tipo_documento(tipodoc_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-411', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 411, '7:3533ddd014a375af379d9cd7204da602', 'createIndex indexName=tipodoc_org_fk_idx, tableName=tipo_documento', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-412::sofis
CREATE INDEX tipogas_org_fk_idx ON tipo_gasto(tipogas_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-412', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 412, '7:1349dc908a49f69a9fcab535c7632ae3', 'createIndex indexName=tipogas_org_fk_idx, tableName=tipo_gasto', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-413::sofis
CREATE INDEX tipolec_codigo ON tipo_leccion(tipolec_codigo);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-413', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 413, '7:70b62ad6bafef768d3fa0167f1dc0e2f', 'createIndex indexName=tipolec_codigo, tableName=tipo_leccion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-414::sofis
CREATE INDEX usu_area_org_idx ON aud_ss_usuario(usu_area_org);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-414', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 414, '7:539605fc2afee3073ea5da60754f815e', 'createIndex indexName=usu_area_org_idx, tableName=aud_ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-415::sofis
CREATE INDEX usu_area_org_idx ON ss_usuario(usu_area_org);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-415', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 415, '7:06b10d38df567bc5c39fc9a2d5035e3d', 'createIndex indexName=usu_area_org_idx, tableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-416::sofis
CREATE INDEX usu_cod_index ON ss_usuario(usu_cod);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-416', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 416, '7:0bdc76ee88b3bf513c815d9db38c7276', 'createIndex indexName=usu_cod_index, tableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-417::sofis
CREATE INDEX usu_correo_electronico_idx ON ss_usuario(usu_correo_electronico);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-417', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 417, '7:5a72113b392918f76ec58326149ee2c3', 'createIndex indexName=usu_correo_electronico_idx, tableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-418::sofis
CREATE INDEX usu_ofi_roles_usu_area_idx ON ss_usu_ofi_roles(usu_ofi_roles_usu_area);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-418', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 418, '7:db4a00811f5ca970609a7cb73b57389d', 'createIndex indexName=usu_ofi_roles_usu_area_idx, tableName=ss_usu_ofi_roles', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-419::sofis
CREATE INDEX usu_token_idx ON aud_ss_usuario(usu_token);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-419', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 419, '7:0709eb9d4a77f1191f3362eef1ea51d0', 'createIndex indexName=usu_token_idx, tableName=aud_ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-420::sofis
CREATE INDEX usu_token_idx ON ss_usuario(usu_token);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-420', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 420, '7:8ec665e2a2c48e9efe9703752c801a97', 'createIndex indexName=usu_token_idx, tableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-421::sofis
CREATE INDEX val_hor_anio_idx ON valor_hora(val_hor_anio);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-421', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 421, '7:277e4afdc93bf1417fd6916d0b5da7f8', 'createIndex indexName=val_hor_anio_idx, tableName=valor_hora', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-422::sofis
CREATE INDEX val_hor_mon_fk_idx ON valor_hora(val_hor_mon_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-422', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 422, '7:9fde0fa3451200360b9ba13e6dab3c24', 'createIndex indexName=val_hor_mon_fk_idx, tableName=valor_hora', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-423::sofis
CREATE INDEX val_hor_org_fk_idx ON valor_hora(val_hor_org_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-423', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 423, '7:99514378dc7e5c4ccbef8074ca3ecf62', 'createIndex indexName=val_hor_org_fk_idx, tableName=valor_hora', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-424::sofis
CREATE INDEX val_hor_usu_fk_idx ON valor_hora(val_hor_usu_fk);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-424', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 424, '7:47407a4fc2e4275bc7ae6b304291a44e', 'createIndex indexName=val_hor_usu_fk_idx, tableName=valor_hora', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-425::sofis
CREATE INDEX vca_codigo ON valor_calidad_codigos(vca_codigo);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-425', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 425, '7:d9fabec5b02ec2ecc7d5f82bf2675193', 'createIndex indexName=vca_codigo, tableName=valor_calidad_codigos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-426::sofis
CREATE INDEX vca_habilitado_idx ON valor_calidad_codigos(vca_habilitado);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-426', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 426, '7:054c72ad7d5864c918c100693ae0a471', 'createIndex indexName=vca_habilitado_idx, tableName=valor_calidad_codigos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-427::sofis
ALTER TABLE personas ADD CONSTRAINT FK1A6A26477E1BCA41 FOREIGN KEY (pers_orga_fk) REFERENCES organi_int_prove (orga_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-427', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 427, '7:8a3b71cd460f3cc576c07fe8637b8044', 'addForeignKeyConstraint baseTableName=personas, constraintName=FK1A6A26477E1BCA41, referencedTableName=organi_int_prove', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-428::sofis
ALTER TABLE ss_telefonos ADD CONSTRAINT FK2CEF84E0641CB6FC FOREIGN KEY (tel_tiptel_id) REFERENCES ss_tipos_telefono (tipTel_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-428', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 428, '7:ac168c989ebf0bb4dc7fdb2ae1d722e9', 'addForeignKeyConstraint baseTableName=ss_telefonos, constraintName=FK2CEF84E0641CB6FC, referencedTableName=ss_tipos_telefono', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-429::sofis
ALTER TABLE ss_domicilios ADD CONSTRAINT FK2D1C5EED2F892058 FOREIGN KEY (dom_pai_id) REFERENCES ss_paises (pai_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-429', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 429, '7:9cafb8073944ed83f19ee18cef4478ed', 'addForeignKeyConstraint baseTableName=ss_domicilios, constraintName=FK2D1C5EED2F892058, referencedTableName=ss_paises', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-430::sofis
ALTER TABLE ss_domicilios ADD CONSTRAINT FK2D1C5EED2FF8B8CF FOREIGN KEY (dom_depto_id) REFERENCES ss_departamentos (depto_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-430', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 430, '7:c03a79826511173df7a0debe1caedef1', 'addForeignKeyConstraint baseTableName=ss_domicilios, constraintName=FK2D1C5EED2FF8B8CF, referencedTableName=ss_departamentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-431::sofis
ALTER TABLE ss_domicilios ADD CONSTRAINT FK2D1C5EED567EC7E FOREIGN KEY (dom_tvi_id) REFERENCES ss_tipos_vialidad (tvi_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-431', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 431, '7:98a87ad54fe8e107274ce56ac49d5097', 'addForeignKeyConstraint baseTableName=ss_domicilios, constraintName=FK2D1C5EED567EC7E, referencedTableName=ss_tipos_vialidad', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-432::sofis
ALTER TABLE ss_domicilios ADD CONSTRAINT FK2D1C5EEDC8B0CD82 FOREIGN KEY (dom_loc_id) REFERENCES ss_localidades (loc_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-432', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 432, '7:71de3a14712da35778bd94dfa7e5dd68', 'addForeignKeyConstraint baseTableName=ss_domicilios, constraintName=FK2D1C5EEDC8B0CD82, referencedTableName=ss_localidades', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-433::sofis
ALTER TABLE ss_domicilios ADD CONSTRAINT FK2D1C5EEDFBC55737 FOREIGN KEY (dom_par_id) REFERENCES ss_paridades (par_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-433', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 433, '7:8a9c16c7cceeec197a7d909849e2605a', 'addForeignKeyConstraint baseTableName=ss_domicilios, constraintName=FK2D1C5EEDFBC55737, referencedTableName=ss_paridades', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-434::sofis
ALTER TABLE ss_domicilios ADD CONSTRAINT FK2D1C5EEDFCA9657E FOREIGN KEY (dom_tec_id) REFERENCES ss_tipos_entrada_colectiva (tec_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-434', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 434, '7:452830840b4d3e976fb531ea34f93e69', 'addForeignKeyConstraint baseTableName=ss_domicilios, constraintName=FK2D1C5EEDFCA9657E, referencedTableName=ss_tipos_entrada_colectiva', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-435::sofis
ALTER TABLE ss_operacion ADD CONSTRAINT FK61DDABF9269E90AA FOREIGN KEY (ope_categoria_id) REFERENCES ss_categoper (cat_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-435', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 435, '7:4e6a3bc5bf76153688c77e2ddcc1168d', 'addForeignKeyConstraint baseTableName=ss_operacion, constraintName=FK61DDABF9269E90AA, referencedTableName=ss_categoper', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-436::sofis
ALTER TABLE ss_rel_rol_operacion ADD CONSTRAINT FK6E9F4363BDC3F659 FOREIGN KEY (rel_rol_operacion_operacion_id) REFERENCES ss_operacion (ope_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-436', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 436, '7:dbb5b7eab1f38e2f6d9912c273e4c9ed', 'addForeignKeyConstraint baseTableName=ss_rel_rol_operacion, constraintName=FK6E9F4363BDC3F659, referencedTableName=ss_operacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-437::sofis
ALTER TABLE ss_rel_rol_operacion ADD CONSTRAINT FK6E9F4363C25B4A39 FOREIGN KEY (rel_rol_operacion_rol_id) REFERENCES ss_rol (rol_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-437', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 437, '7:e43c33ec280aaa3f1d4e36e121a11993', 'addForeignKeyConstraint baseTableName=ss_rel_rol_operacion, constraintName=FK6E9F4363C25B4A39, referencedTableName=ss_rol', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-438::sofis
ALTER TABLE ss_usu_ofi_roles ADD CONSTRAINT FKB64469698353C0A7 FOREIGN KEY (usu_ofi_roles_usuario) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-438', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 438, '7:562caec3d8bc0c22fac9b999831dd990', 'addForeignKeyConstraint baseTableName=ss_usu_ofi_roles, constraintName=FKB64469698353C0A7, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-439::sofis
ALTER TABLE ss_usu_ofi_roles ADD CONSTRAINT FKB64469698D342B69 FOREIGN KEY (usu_ofi_roles_rol) REFERENCES ss_rol (rol_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-439', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 439, '7:cfbd80c174a8ce07096c23bee212d2eb', 'addForeignKeyConstraint baseTableName=ss_usu_ofi_roles, constraintName=FKB64469698D342B69, referencedTableName=ss_rol', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-440::sofis
ALTER TABLE ss_personas ADD CONSTRAINT FKBF89754648010DF9 FOREIGN KEY (per_tipo_doc) REFERENCES ss_tipos_documento_persona (tipdocper_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-440', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 440, '7:a076879fbcf5b1c770d087682bdf53c7', 'addForeignKeyConstraint baseTableName=ss_personas, constraintName=FKBF89754648010DF9, referencedTableName=ss_tipos_documento_persona', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-441::sofis
ALTER TABLE ss_personas ADD CONSTRAINT FKBF897546F9B07F4F FOREIGN KEY (per_pais_doc) REFERENCES ss_paises (pai_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-441', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 441, '7:4963495d628b7e501953f8760ad57084', 'addForeignKeyConstraint baseTableName=ss_personas, constraintName=FKBF897546F9B07F4F, referencedTableName=ss_paises', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-442::sofis
ALTER TABLE ss_localidades ADD CONSTRAINT FKEF82E378E2A77891 FOREIGN KEY (loc_depto_id) REFERENCES ss_departamentos (depto_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-442', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 442, '7:c2b104e4e15cc0446a2a0a4f61fc6900', 'addForeignKeyConstraint baseTableName=ss_localidades, constraintName=FKEF82E378E2A77891, referencedTableName=ss_departamentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-443::sofis
ALTER TABLE busq_filtro ADD CONSTRAINT FK_4b0pq8qh2f6u7ei11lh0atbf8 FOREIGN KEY (busq_filtro_usu_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-443', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 443, '7:48c80610994bc4b4737b62887d1b5327', 'addForeignKeyConstraint baseTableName=busq_filtro, constraintName=FK_4b0pq8qh2f6u7ei11lh0atbf8, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-444::sofis
ALTER TABLE categoria_proyectos ADD CONSTRAINT FK_4t53ltja1415bq3d23c3kdjl1 FOREIGN KEY (cat_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-444', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 444, '7:bec56f5651265abdd0bfa1fdebfb5baf', 'addForeignKeyConstraint baseTableName=categoria_proyectos, constraintName=FK_4t53ltja1415bq3d23c3kdjl1, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-445::sofis
ALTER TABLE devengado ADD CONSTRAINT FK_9c8og633e1waprs81i6ayorba FOREIGN KEY (dev_adq_fk) REFERENCES adquisicion (adq_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-445', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 445, '7:6631ee153f9dad2162939cc7e5775258', 'addForeignKeyConstraint baseTableName=devengado, constraintName=FK_9c8og633e1waprs81i6ayorba, referencedTableName=adquisicion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-446::sofis
ALTER TABLE busq_filtro ADD CONSTRAINT FK_a726jrgroi6p90dip38n70ftp FOREIGN KEY (busq_filtro_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-446', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 446, '7:6e85040d5e14cf2d8d871ec3c80e52c9', 'addForeignKeyConstraint baseTableName=busq_filtro, constraintName=FK_a726jrgroi6p90dip38n70ftp, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-447::sofis
ALTER TABLE categoria_proyectos ADD CONSTRAINT FK_hb3weyc8xvdrbp62k3u1halnb FOREIGN KEY (cat_icono_marker) REFERENCES image (image_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-447', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 447, '7:2be7e931a6b418a320e7a1d30731ae61', 'addForeignKeyConstraint baseTableName=categoria_proyectos, constraintName=FK_hb3weyc8xvdrbp62k3u1halnb, referencedTableName=image', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-448::sofis
ALTER TABLE categoria_proyectos ADD CONSTRAINT FK_hh3lr9l8qt7isgvniyif3w6tw FOREIGN KEY (cat_icono) REFERENCES image (image_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-448', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 448, '7:699e54cf833228bf3be0ae122a567ec3', 'addForeignKeyConstraint baseTableName=categoria_proyectos, constraintName=FK_hh3lr9l8qt7isgvniyif3w6tw, referencedTableName=image', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-449::sofis
ALTER TABLE areas ADD CONSTRAINT FK_j151q3d1wiqgx10w9n92hwx5j FOREIGN KEY (area_director) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-449', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 449, '7:13fa8f51122a67fd15058a6c0519fcb5', 'addForeignKeyConstraint baseTableName=areas, constraintName=FK_j151q3d1wiqgx10w9n92hwx5j, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-450::sofis
ALTER TABLE ambito ADD CONSTRAINT FK_n5tl58dqv18cs790jbmejxom2 FOREIGN KEY (amb_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-450', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 450, '7:2d950369bd403626d5a066a1993c344f', 'addForeignKeyConstraint baseTableName=ambito, constraintName=FK_n5tl58dqv18cs790jbmejxom2, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-451::sofis
ALTER TABLE doc_file ADD CONSTRAINT FK_n76rhuste8gi3p3jq7m91j7iq FOREIGN KEY (docfile_doc_fk) REFERENCES documentos (docs_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-451', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 451, '7:50ee00d92de06f8e5007b21a57144962', 'addForeignKeyConstraint baseTableName=doc_file, constraintName=FK_n76rhuste8gi3p3jq7m91j7iq, referencedTableName=documentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-452::sofis
ALTER TABLE adquisicion ADD CONSTRAINT adq_comp_prod_fk FOREIGN KEY (adq_componente_producto_fk) REFERENCES componente_producto (com_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-452', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 452, '7:055571def2f70f9d4a54ed015b895e04', 'addForeignKeyConstraint baseTableName=adquisicion, constraintName=adq_comp_prod_fk, referencedTableName=componente_producto', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-453::sofis
ALTER TABLE adquisicion ADD CONSTRAINT adq_fuente FOREIGN KEY (adq_fuente_fk) REFERENCES fuente_financiamiento (fue_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-453', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 453, '7:6fc65b26eee2c01a97975e1a5e09748a', 'addForeignKeyConstraint baseTableName=adquisicion, constraintName=adq_fuente, referencedTableName=fuente_financiamiento', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-454::sofis
ALTER TABLE adquisicion ADD CONSTRAINT adq_moneda FOREIGN KEY (adq_moneda_fk) REFERENCES moneda (mon_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-454', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 454, '7:710a9fd878cd69783bb65a9dd646e270', 'addForeignKeyConstraint baseTableName=adquisicion, constraintName=adq_moneda, referencedTableName=moneda', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-455::sofis
ALTER TABLE adquisicion ADD CONSTRAINT adq_pre_fk FOREIGN KEY (adq_pre_fk) REFERENCES presupuesto (pre_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-455', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 455, '7:ca28c5986eb228b90457932839e7763e', 'addForeignKeyConstraint baseTableName=adquisicion, constraintName=adq_pre_fk, referencedTableName=presupuesto', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-456::sofis
ALTER TABLE adquisicion ADD CONSTRAINT adq_proc_comp_fk FOREIGN KEY (adq_procedimiento_compra_fk) REFERENCES procedimiento_compra (proc_comp_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-456', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 456, '7:cfa96ba9ddf925f5fe507d013212cac3', 'addForeignKeyConstraint baseTableName=adquisicion, constraintName=adq_proc_comp_fk, referencedTableName=procedimiento_compra', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-457::sofis
ALTER TABLE adquisicion ADD CONSTRAINT adq_prov_orga FOREIGN KEY (adq_prov_orga_fk) REFERENCES organi_int_prove (orga_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-457', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 457, '7:a87c94b1d9e7b7a8916a22d0879b4619', 'addForeignKeyConstraint baseTableName=adquisicion, constraintName=adq_prov_orga, referencedTableName=organi_int_prove', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-458::sofis
ALTER TABLE adquisicion ADD CONSTRAINT adquisicion_ss_usuario_FK FOREIGN KEY (adq_compartida_usuario_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-458', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 458, '7:29758c6f162d1f34ce4c960cd619c7da', 'addForeignKeyConstraint baseTableName=adquisicion, constraintName=adquisicion_ss_usuario_FK, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-459::sofis
ALTER TABLE calidad ADD CONSTRAINT cal_ent_fk FOREIGN KEY (cal_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-459', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 459, '7:b45d6d9f5064a122cc5cbce3693177e7', 'addForeignKeyConstraint baseTableName=calidad, constraintName=cal_ent_fk, referencedTableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-460::sofis
ALTER TABLE calidad ADD CONSTRAINT cal_prod_fk FOREIGN KEY (cal_prod_fk) REFERENCES productos (prod_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-460', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 460, '7:5995618b5d7d10fdce89a922f96fb491', 'addForeignKeyConstraint baseTableName=calidad, constraintName=cal_prod_fk, referencedTableName=productos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-461::sofis
ALTER TABLE calidad ADD CONSTRAINT cal_proy_fk FOREIGN KEY (cal_proy_fk) REFERENCES proyectos (proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-461', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 461, '7:ee520f85b02e5a12710c47b6f03af2e2', 'addForeignKeyConstraint baseTableName=calidad, constraintName=cal_proy_fk, referencedTableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-462::sofis
ALTER TABLE calidad ADD CONSTRAINT cal_tca_fk FOREIGN KEY (cal_tca_fk) REFERENCES temas_calidad (tca_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-462', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 462, '7:c438ed38cad4aaecfd19a27ed3f383e8', 'addForeignKeyConstraint baseTableName=calidad, constraintName=cal_tca_fk, referencedTableName=temas_calidad', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-463::sofis
ALTER TABLE calidad ADD CONSTRAINT cal_vca_fk FOREIGN KEY (cal_vca_fk) REFERENCES valor_calidad_codigos (vca_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-463', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 463, '7:bb6e82930fc72e8bca6df994b171b13d', 'addForeignKeyConstraint baseTableName=calidad, constraintName=cal_vca_fk, referencedTableName=valor_calidad_codigos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-464::sofis
ALTER TABLE area_conocimiento ADD CONSTRAINT con_org_fk FOREIGN KEY (con_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-464', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 464, '7:8408f22d574889b54f30d7245b6b8470', 'addForeignKeyConstraint baseTableName=area_conocimiento, constraintName=con_org_fk, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-465::sofis
ALTER TABLE area_conocimiento ADD CONSTRAINT con_padre_fk FOREIGN KEY (con_padre_fk) REFERENCES area_conocimiento (con_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-465', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 465, '7:cf9616f0f95020fcf1cb1ee5ba07d4ac', 'addForeignKeyConstraint baseTableName=area_conocimiento, constraintName=con_padre_fk, referencedTableName=area_conocimiento', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-466::sofis
ALTER TABLE documentos ADD CONSTRAINT docs_docfile_fk FOREIGN KEY (docs_docfile_pk) REFERENCES doc_file (docfile_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-466', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 466, '7:468915315e75ba6f5569ffe1f2fbdeed', 'addForeignKeyConstraint baseTableName=documentos, constraintName=docs_docfile_fk, referencedTableName=doc_file', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-467::sofis
ALTER TABLE documentos ADD CONSTRAINT docs_entregable_fk FOREIGN KEY (docs_entregable_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-467', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 467, '7:9f37e74a6b117776595e0da3c73fbb72', 'addForeignKeyConstraint baseTableName=documentos, constraintName=docs_entregable_fk, referencedTableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-468::sofis
ALTER TABLE documentos ADD CONSTRAINT docs_pago_fk FOREIGN KEY (docs_pago_fk) REFERENCES pagos (pag_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-468', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 468, '7:f8f7d5bdf1b660fb0f41c9bb7c436f83', 'addForeignKeyConstraint baseTableName=documentos, constraintName=docs_pago_fk, referencedTableName=pagos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-469::sofis
ALTER TABLE entregables ADD CONSTRAINT ent_coord_usu_fk FOREIGN KEY (ent_coord_usu_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-469', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 469, '7:d4d8858e3d74b86fdabf3b130a46bab0', 'addForeignKeyConstraint baseTableName=entregables, constraintName=ent_coord_usu_fk, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-470::sofis
ALTER TABLE entregables ADD CONSTRAINT ent_cro_fk FOREIGN KEY (ent_cro_fk) REFERENCES cronogramas (cro_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-470', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 470, '7:67a8e11f76b523e351d68bee8b7e712d', 'addForeignKeyConstraint baseTableName=entregables, constraintName=ent_cro_fk, referencedTableName=cronogramas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-471::sofis
ALTER TABLE ent_hist_linea_base ADD CONSTRAINT enthist_ent_fk FOREIGN KEY (enthist_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-471', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 471, '7:d900ab993ba80f1334628d89a13dc4ae', 'addForeignKeyConstraint baseTableName=ent_hist_linea_base, constraintName=enthist_ent_fk, referencedTableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-472::sofis
ALTER TABLE ent_hist_linea_base ADD CONSTRAINT enthist_replan_fk FOREIGN KEY (enthist_replan_fk) REFERENCES proy_replanificacion (proyreplan_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-472', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 472, '7:1f8ab8b4abd1f588e7e15d6df549f1ee', 'addForeignKeyConstraint baseTableName=ent_hist_linea_base, constraintName=enthist_replan_fk, referencedTableName=proy_replanificacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-473::sofis
ALTER TABLE plantilla_entregables ADD CONSTRAINT entre_ante FOREIGN KEY (p_entregable_ant_fk) REFERENCES plantilla_entregables (p_entregables_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-473', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 473, '7:183b94e253e3837af8dfafe45e27b9ca', 'addForeignKeyConstraint baseTableName=plantilla_entregables, constraintName=entre_ante, referencedTableName=plantilla_entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-474::sofis
ALTER TABLE etapa ADD CONSTRAINT eta_org_fk FOREIGN KEY (eta_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-474', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 474, '7:d0335c775cb70c6a223feacea1650d7e', 'addForeignKeyConstraint baseTableName=etapa, constraintName=eta_org_fk, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-475::sofis
ALTER TABLE areas ADD CONSTRAINT fk_AREAS_AREAS1 FOREIGN KEY (area_padre) REFERENCES areas (area_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-475', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 475, '7:d4b80dc778e261ecb4b3c370492c545c', 'addForeignKeyConstraint baseTableName=areas, constraintName=fk_AREAS_AREAS1, referencedTableName=areas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-476::sofis
ALTER TABLE areas ADD CONSTRAINT fk_AREAS_ORGANISMOS1 FOREIGN KEY (area_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-476', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 476, '7:5c4e673731e726a5b690561d69ab4233', 'addForeignKeyConstraint baseTableName=areas, constraintName=fk_AREAS_ORGANISMOS1, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-477::sofis
ALTER TABLE areas_tags ADD CONSTRAINT fk_AREAS_TAGS_AREAS_TAGS1 FOREIGN KEY (areatag_padre_fk) REFERENCES areas_tags (arastag_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-477', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 477, '7:f02a446032ce4f7d05b9c15994c42c1a', 'addForeignKeyConstraint baseTableName=areas_tags, constraintName=fk_AREAS_TAGS_AREAS_TAGS1, referencedTableName=areas_tags', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-478::sofis
ALTER TABLE areas_tags ADD CONSTRAINT fk_AREAS_TAGS_ORGANISMOS1 FOREIGN KEY (areatag_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-478', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 478, '7:46f10600229a1354d1b1b592cbc7b1da', 'addForeignKeyConstraint baseTableName=areas_tags, constraintName=fk_AREAS_TAGS_ORGANISMOS1, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-479::sofis
ALTER TABLE area_organi_int_prove ADD CONSTRAINT fk_AREA_ORGANI_INT_PROVE_ORGANISMOS1 FOREIGN KEY (areaorgintprov_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-479', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 479, '7:d6fa63cb4fc10feb496c20dc0e4e5e44', 'addForeignKeyConstraint baseTableName=area_organi_int_prove, constraintName=fk_AREA_ORGANI_INT_PROVE_ORGANISMOS1, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-480::sofis
ALTER TABLE area_organi_int_prove ADD CONSTRAINT fk_AREA_ORGANI_INT_PROVE_ORGANI_INT_PROVE1 FOREIGN KEY (areaorgintprov_orga_fk) REFERENCES organi_int_prove (orga_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-480', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 480, '7:d880a9f3912d4dbd41cd5d0bb518eadc', 'addForeignKeyConstraint baseTableName=area_organi_int_prove, constraintName=fk_AREA_ORGANI_INT_PROVE_ORGANI_INT_PROVE1, referencedTableName=organi_int_prove', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-481::sofis
ALTER TABLE prog_docs_obl ADD CONSTRAINT fk_DOCS_OBL_DOCUMENTOS1 FOREIGN KEY (progdocsobl_docs_pk) REFERENCES documentos (docs_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-481', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 481, '7:9e58546eac937f6dba164f81d138ab60', 'addForeignKeyConstraint baseTableName=prog_docs_obl, constraintName=fk_DOCS_OBL_DOCUMENTOS1, referencedTableName=documentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-482::sofis
ALTER TABLE prog_docs_obl ADD CONSTRAINT fk_DOCS_OBL_PROGRAMAS1 FOREIGN KEY (progdocsobl_prog_pk) REFERENCES programas (prog_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-482', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 482, '7:ee1a7806b00b1043b7502d1857411e0d', 'addForeignKeyConstraint baseTableName=prog_docs_obl, constraintName=fk_DOCS_OBL_PROGRAMAS1, referencedTableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-483::sofis
ALTER TABLE interesados ADD CONSTRAINT fk_INTERESADOS_ORGANIZACION1 FOREIGN KEY (int_orga_fk) REFERENCES organi_int_prove (orga_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-483', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 483, '7:4c4a3a0583ef64a4a254ab7c62515b22', 'addForeignKeyConstraint baseTableName=interesados, constraintName=fk_INTERESADOS_ORGANIZACION1, referencedTableName=organi_int_prove', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-484::sofis
ALTER TABLE interesados ADD CONSTRAINT fk_INTERESADOS_PERSONAS1 FOREIGN KEY (int_pers_fk) REFERENCES personas (pers_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-484', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 484, '7:0c5135527701cff3944e0af7917e6940', 'addForeignKeyConstraint baseTableName=interesados, constraintName=fk_INTERESADOS_PERSONAS1, referencedTableName=personas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-485::sofis
ALTER TABLE interesados ADD CONSTRAINT fk_INTERESADOS_ROLES_INTERESADOS1 FOREIGN KEY (int_rolint_fk) REFERENCES roles_interesados (rolint_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-485', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 485, '7:4402646f4439cf2409c8677ce32cd96c', 'addForeignKeyConstraint baseTableName=interesados, constraintName=fk_INTERESADOS_ROLES_INTERESADOS1, referencedTableName=roles_interesados', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-486::sofis
ALTER TABLE programas ADD CONSTRAINT fk_PROGRAMAS_AREAS1 FOREIGN KEY (prog_area_fk) REFERENCES areas (area_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-486', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 486, '7:793296d4544f12a4f25e9de8c8b1e20e', 'addForeignKeyConstraint baseTableName=programas, constraintName=fk_PROGRAMAS_AREAS1, referencedTableName=areas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-487::sofis
ALTER TABLE programas ADD CONSTRAINT fk_PROGRAMAS_ESTADOS1 FOREIGN KEY (prog_est_fk) REFERENCES estados (est_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-487', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 487, '7:ae938efccf4d44f534df41c1a5f04586', 'addForeignKeyConstraint baseTableName=programas, constraintName=fk_PROGRAMAS_ESTADOS1, referencedTableName=estados', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-488::sofis
ALTER TABLE programas ADD CONSTRAINT fk_PROGRAMAS_ORGANISMOS1 FOREIGN KEY (prog_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-488', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 488, '7:0f64fcf676414fc4a3e62e41131dacfb', 'addForeignKeyConstraint baseTableName=programas, constraintName=fk_PROGRAMAS_ORGANISMOS1, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-489::sofis
ALTER TABLE programas ADD CONSTRAINT fk_PROGRAMAS_USUARIOS1 FOREIGN KEY (prog_usr_gerente_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-489', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 489, '7:ce040857ead166e12d6e54fd448d25dd', 'addForeignKeyConstraint baseTableName=programas, constraintName=fk_PROGRAMAS_USUARIOS1, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-490::sofis
ALTER TABLE programas ADD CONSTRAINT fk_PROGRAMAS_USUARIOS2 FOREIGN KEY (prog_usr_adjunto_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-490', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 490, '7:8ee5253927aec29c8333e0e8cdb85236', 'addForeignKeyConstraint baseTableName=programas, constraintName=fk_PROGRAMAS_USUARIOS2, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-491::sofis
ALTER TABLE programas ADD CONSTRAINT fk_PROGRAMAS_USUARIOS3 FOREIGN KEY (prog_usr_sponsor_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-491', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 491, '7:a9d3a17d8218ac0443a4b14bd4c7c990', 'addForeignKeyConstraint baseTableName=programas, constraintName=fk_PROGRAMAS_USUARIOS3, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-492::sofis
ALTER TABLE programas ADD CONSTRAINT fk_PROGRAMAS_USUARIOS4 FOREIGN KEY (prog_usr_pmofed_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-492', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 492, '7:f41e93ac097eedec950dbeb508d328f6', 'addForeignKeyConstraint baseTableName=programas, constraintName=fk_PROGRAMAS_USUARIOS4, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-493::sofis
ALTER TABLE prog_int ADD CONSTRAINT fk_PROG_INT_INTERESADOS1 FOREIGN KEY (progint_int_pk) REFERENCES interesados (int_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-493', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 493, '7:6ab929368ea96ab9270ad87a22c26afa', 'addForeignKeyConstraint baseTableName=prog_int, constraintName=fk_PROG_INT_INTERESADOS1, referencedTableName=interesados', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-494::sofis
ALTER TABLE prog_int ADD CONSTRAINT fk_PROG_INT_PROGRAMAS1 FOREIGN KEY (progint_prog_pk) REFERENCES programas (prog_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-494', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 494, '7:97a8d112be1859a450428bef998edc0e', 'addForeignKeyConstraint baseTableName=prog_int, constraintName=fk_PROG_INT_PROGRAMAS1, referencedTableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-495::sofis
ALTER TABLE prog_lectura_area ADD CONSTRAINT fk_PROG_LECTURA_AREA_AREAS1 FOREIGN KEY (proglectarea_area_pk) REFERENCES areas (area_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-495', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 495, '7:6819d878ec2e5628f1bff0272d47ec54', 'addForeignKeyConstraint baseTableName=prog_lectura_area, constraintName=fk_PROG_LECTURA_AREA_AREAS1, referencedTableName=areas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-496::sofis
ALTER TABLE proy_lectura_area ADD CONSTRAINT fk_PROG_LECTURA_AREA_AREAS10 FOREIGN KEY (proglectarea_area_pk) REFERENCES areas (area_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-496', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 496, '7:a9e1a091ccadde488aac35aa3ff057f1', 'addForeignKeyConstraint baseTableName=proy_lectura_area, constraintName=fk_PROG_LECTURA_AREA_AREAS10, referencedTableName=areas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-497::sofis
ALTER TABLE prog_lectura_area ADD CONSTRAINT fk_PROG_LECTURA_AREA_PROGRAMAS1 FOREIGN KEY (proglectarea_prog_pk) REFERENCES programas (prog_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-497', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 497, '7:f5722f56c4ce6665e5b34a5ad8e2c17d', 'addForeignKeyConstraint baseTableName=prog_lectura_area, constraintName=fk_PROG_LECTURA_AREA_PROGRAMAS1, referencedTableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-498::sofis
ALTER TABLE prog_tags ADD CONSTRAINT fk_PROG_TAGS_AREAS_TAGS1 FOREIGN KEY (progtag_area_pk) REFERENCES areas_tags (arastag_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-498', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 498, '7:4734b35906d0d6bb26af9201d766630b', 'addForeignKeyConstraint baseTableName=prog_tags, constraintName=fk_PROG_TAGS_AREAS_TAGS1, referencedTableName=areas_tags', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-499::sofis
ALTER TABLE prog_tags ADD CONSTRAINT fk_PROG_TAGS_PROGRAMAS1 FOREIGN KEY (progtag_prog_pk) REFERENCES programas (prog_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-499', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 499, '7:2cc2508f0b93dd53dd85f622b3f39c43', 'addForeignKeyConstraint baseTableName=prog_tags, constraintName=fk_PROG_TAGS_PROGRAMAS1, referencedTableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-500::sofis
ALTER TABLE proyectos ADD CONSTRAINT fk_PROYECTOS_AREAS1 FOREIGN KEY (proy_area_fk) REFERENCES areas (area_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-500', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 500, '7:1f603431b18c80dd8f18e51a96f2b152', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=fk_PROYECTOS_AREAS1, referencedTableName=areas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-501::sofis
ALTER TABLE proyectos ADD CONSTRAINT fk_PROYECTOS_CRONOGRAMA FOREIGN KEY (proy_cro_fk) REFERENCES cronogramas (cro_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-501', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 501, '7:14ee5fe2b366dc7964fce9b7cc4c6dd3', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=fk_PROYECTOS_CRONOGRAMA, referencedTableName=cronogramas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-502::sofis
ALTER TABLE proyectos ADD CONSTRAINT fk_PROYECTOS_ESTADOS1 FOREIGN KEY (proy_est_fk) REFERENCES estados (est_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-502', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 502, '7:0a59c964e668a893c4a3a45c22c2a58c', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=fk_PROYECTOS_ESTADOS1, referencedTableName=estados', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-503::sofis
ALTER TABLE proyectos ADD CONSTRAINT fk_PROYECTOS_ORGANISMOS1 FOREIGN KEY (proy_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-503', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 503, '7:faa84928ba14eaf11efee624a1192929', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=fk_PROYECTOS_ORGANISMOS1, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-504::sofis
ALTER TABLE proyectos ADD CONSTRAINT fk_PROYECTOS_PROGRAMAS1 FOREIGN KEY (proy_prog_fk) REFERENCES programas (prog_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-504', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 504, '7:de96885ac35f4005dd144ec1774dfb8e', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=fk_PROYECTOS_PROGRAMAS1, referencedTableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-505::sofis
ALTER TABLE proyectos ADD CONSTRAINT fk_PROYECTOS_RIESGOS1 FOREIGN KEY (proy_risk_fk) REFERENCES riesgos (risk_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-505', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 505, '7:41621209e30006cc7b56c3d38f2aa195', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=fk_PROYECTOS_RIESGOS1, referencedTableName=riesgos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-506::sofis
ALTER TABLE proyectos ADD CONSTRAINT fk_PROYECTOS_USUARIOS1 FOREIGN KEY (proy_usr_adjunto_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-506', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 506, '7:2deedd472b880e0a4611187cd3c6dd46', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=fk_PROYECTOS_USUARIOS1, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-507::sofis
ALTER TABLE proyectos ADD CONSTRAINT fk_PROYECTOS_USUARIOS2 FOREIGN KEY (proy_usr_sponsor_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-507', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 507, '7:70415d34d2ecf6ac3aa65a705f209e90', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=fk_PROYECTOS_USUARIOS2, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-508::sofis
ALTER TABLE proyectos ADD CONSTRAINT fk_PROYECTOS_USUARIOS4 FOREIGN KEY (proy_usr_pmofed_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-508', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 508, '7:aa220b3dec491ac081199531479d360b', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=fk_PROYECTOS_USUARIOS4, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-509::sofis
ALTER TABLE proy_int ADD CONSTRAINT fk_PROY_INT_INTERESADOS1 FOREIGN KEY (proyint_int_pk) REFERENCES interesados (int_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-509', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 509, '7:0ddb0f80feadb37ec3528cb5153e96f7', 'addForeignKeyConstraint baseTableName=proy_int, constraintName=fk_PROY_INT_INTERESADOS1, referencedTableName=interesados', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-510::sofis
ALTER TABLE proy_int ADD CONSTRAINT fk_PROY_INT_PROYECTOS1 FOREIGN KEY (proyint_proy_pk) REFERENCES proyectos (proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-510', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 510, '7:3b00e0bfe5f6559505180942f074d8b1', 'addForeignKeyConstraint baseTableName=proy_int, constraintName=fk_PROY_INT_PROYECTOS1, referencedTableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-511::sofis
ALTER TABLE proy_lectura_area ADD CONSTRAINT fk_PROY_LECTURA_AREA_PROYECTOS1 FOREIGN KEY (proglectarea_proy_pk) REFERENCES proyectos (proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-511', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 511, '7:f1cd83808fc252362b6f71891c50c8e7', 'addForeignKeyConstraint baseTableName=proy_lectura_area, constraintName=fk_PROY_LECTURA_AREA_PROYECTOS1, referencedTableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-512::sofis
ALTER TABLE proy_tags ADD CONSTRAINT fk_PROY_TAGS_AREAS_TAGS1 FOREIGN KEY (proytag_area_pk) REFERENCES areas_tags (arastag_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-512', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 512, '7:56aea5761e69e46c6fb8bcf9ec760d29', 'addForeignKeyConstraint baseTableName=proy_tags, constraintName=fk_PROY_TAGS_AREAS_TAGS1, referencedTableName=areas_tags', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-513::sofis
ALTER TABLE proy_tags ADD CONSTRAINT fk_PROY_TAGS_PROYECTOS1 FOREIGN KEY (proytag_proy_pk) REFERENCES proyectos (proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-513', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 513, '7:106d2351355e81f8b5f7ad5495359d90', 'addForeignKeyConstraint baseTableName=proy_tags, constraintName=fk_PROY_TAGS_PROYECTOS1, referencedTableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-514::sofis
ALTER TABLE prog_docs ADD CONSTRAINT fk_Prog_docs_DOCUMENTOS1 FOREIGN KEY (progdocs_doc_pk) REFERENCES documentos (docs_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-514', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 514, '7:a37a5c2a35ce0e8c6c10d9afc9f2c0ad', 'addForeignKeyConstraint baseTableName=prog_docs, constraintName=fk_Prog_docs_DOCUMENTOS1, referencedTableName=documentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-515::sofis
ALTER TABLE proy_docs ADD CONSTRAINT fk_Proy_docs_DOCUMENTOS1 FOREIGN KEY (proydoc_doc_pk) REFERENCES documentos (docs_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-515', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 515, '7:097eec4406f994256957ba8ebf0be77a', 'addForeignKeyConstraint baseTableName=proy_docs, constraintName=fk_Proy_docs_DOCUMENTOS1, referencedTableName=documentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-516::sofis
ALTER TABLE roles_interesados ADD CONSTRAINT fk_ROLES_INTERESADOS_ORGANISMOS1 FOREIGN KEY (rolint_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-516', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 516, '7:504cde614fb3dc07a6c9adfdccd7590d', 'addForeignKeyConstraint baseTableName=roles_interesados, constraintName=fk_ROLES_INTERESADOS_ORGANISMOS1, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-517::sofis
ALTER TABLE participantes ADD CONSTRAINT fk_participantes_proyectos1 FOREIGN KEY (part_proy_fk) REFERENCES proyectos (proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-517', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 517, '7:604a56836868e8a47b713726d46b173a', 'addForeignKeyConstraint baseTableName=participantes, constraintName=fk_participantes_proyectos1, referencedTableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-518::sofis
ALTER TABLE participantes ADD CONSTRAINT fk_participantes_usuarios1 FOREIGN KEY (part_usu_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-518', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 518, '7:16edd437673d7b2669c48453bd26ca97', 'addForeignKeyConstraint baseTableName=participantes, constraintName=fk_participantes_usuarios1, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-519::sofis
ALTER TABLE registros_horas ADD CONSTRAINT fk_registrohoras_entregables1 FOREIGN KEY (rh_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-519', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 519, '7:9e3dcd42a006ef4d021b482901c5fd67', 'addForeignKeyConstraint baseTableName=registros_horas, constraintName=fk_registrohoras_entregables1, referencedTableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-520::sofis
ALTER TABLE registros_horas ADD CONSTRAINT fk_registrohoras_proyectos1 FOREIGN KEY (rh_proy_fk) REFERENCES proyectos (proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-520', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 520, '7:ae0f86ed1dd7081d98d0f853eecdbc83', 'addForeignKeyConstraint baseTableName=registros_horas, constraintName=fk_registrohoras_proyectos1, referencedTableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-521::sofis
ALTER TABLE registros_horas ADD CONSTRAINT fk_registrohoras_usuarios1 FOREIGN KEY (rh_usu_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-521', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 521, '7:25cd8b0fb2991549b4573e0b39ebbdc8', 'addForeignKeyConstraint baseTableName=registros_horas, constraintName=fk_registrohoras_usuarios1, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-522::sofis
ALTER TABLE fuente_financiamiento ADD CONSTRAINT fue_org_fk FOREIGN KEY (fue_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-522', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 522, '7:6cd653f26d917e7070b253a53943de6a', 'addForeignKeyConstraint baseTableName=fuente_financiamiento, constraintName=fue_org_fk, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-523::sofis
ALTER TABLE gastos ADD CONSTRAINT gas_mon_fk FOREIGN KEY (gas_mon_fk) REFERENCES moneda (mon_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-523', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 523, '7:4e0a133aa52280d74d19a36f13b7b1c8', 'addForeignKeyConstraint baseTableName=gastos, constraintName=gas_mon_fk, referencedTableName=moneda', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-524::sofis
ALTER TABLE gastos ADD CONSTRAINT gas_proy_fk FOREIGN KEY (gas_proy_fk) REFERENCES proyectos (proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-524', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 524, '7:7578d5c21268d4f091fbf0a777dc8700', 'addForeignKeyConstraint baseTableName=gastos, constraintName=gas_proy_fk, referencedTableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-525::sofis
ALTER TABLE gastos ADD CONSTRAINT gas_tipo_fk FOREIGN KEY (gas_tipo_fk) REFERENCES tipo_gasto (tipogas_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-525', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 525, '7:917d4c831bd1396e3ef7d50f2b26517e', 'addForeignKeyConstraint baseTableName=gastos, constraintName=gas_tipo_fk, referencedTableName=tipo_gasto', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-526::sofis
ALTER TABLE gastos ADD CONSTRAINT gas_usu_fk FOREIGN KEY (gas_usu_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-526', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 526, '7:fd754415244ad2409cddfbe1b7d5f11e', 'addForeignKeyConstraint baseTableName=gastos, constraintName=gas_usu_fk, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-527::sofis
ALTER TABLE interesados ADD CONSTRAINT int_ent_fk FOREIGN KEY (int_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-527', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 527, '7:17320f0b22642ffcde1ecd22311d5cda', 'addForeignKeyConstraint baseTableName=interesados, constraintName=int_ent_fk, referencedTableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-528::sofis
ALTER TABLE latlng_proyectos ADD CONSTRAINT latlang_dep_fk FOREIGN KEY (latlang_dep_fk) REFERENCES departamentos (dep_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-528', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 528, '7:d7206f0a1d4606d73e751d878154f578', 'addForeignKeyConstraint baseTableName=latlng_proyectos, constraintName=latlang_dep_fk, referencedTableName=departamentos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-529::sofis
ALTER TABLE latlng_proyectos ADD CONSTRAINT latlng_proy_fk FOREIGN KEY (latlng_proy_fk) REFERENCES proyectos (proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-529', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 529, '7:fa08d9c2a932eda79669d2fba7b166f3', 'addForeignKeyConstraint baseTableName=latlng_proyectos, constraintName=latlng_proy_fk, referencedTableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-530::sofis
ALTER TABLE lecc_aprendidas ADD CONSTRAINT lecapr_org_fk FOREIGN KEY (lecapr_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-530', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 530, '7:99edeb17c06a90e69f213ac3c2854cbb', 'addForeignKeyConstraint baseTableName=lecc_aprendidas, constraintName=lecapr_org_fk, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-531::sofis
ALTER TABLE lecc_aprendidas ADD CONSTRAINT lecapr_proy_fk FOREIGN KEY (lecapr_proy_fk) REFERENCES proyectos (proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-531', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 531, '7:f915849261352e90afa24a6327b973bc', 'addForeignKeyConstraint baseTableName=lecc_aprendidas, constraintName=lecapr_proy_fk, referencedTableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-532::sofis
ALTER TABLE lecc_aprendidas ADD CONSTRAINT lecapr_tipo_fk FOREIGN KEY (lecapr_tipo_fk) REFERENCES tipo_leccion (tipolec_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-532', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 532, '7:42bb8f8958afc15d30517ba0169b65da', 'addForeignKeyConstraint baseTableName=lecc_aprendidas, constraintName=lecapr_tipo_fk, referencedTableName=tipo_leccion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-533::sofis
ALTER TABLE lecc_aprendidas ADD CONSTRAINT lecapr_usr_fk FOREIGN KEY (lecapr_usr_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-533', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 533, '7:586d80064a0fe5bab1a708021e94c972', 'addForeignKeyConstraint baseTableName=lecc_aprendidas, constraintName=lecapr_usr_fk, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-534::sofis
ALTER TABLE lecapr_areacon ADD CONSTRAINT lecaprcon_con_fk FOREIGN KEY (lecaprcon_con_fk) REFERENCES area_conocimiento (con_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-534', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 534, '7:c78f95be692917834641dc871f993ffe', 'addForeignKeyConstraint baseTableName=lecapr_areacon, constraintName=lecaprcon_con_fk, referencedTableName=area_conocimiento', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-535::sofis
ALTER TABLE lecapr_areacon ADD CONSTRAINT lecaprcon_lecapr_fk FOREIGN KEY (lecaprcon_lecapr_fk) REFERENCES lecc_aprendidas (lecapr_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-535', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 535, '7:fb32ff2bd2a6c52bda85659b6a5005d9', 'addForeignKeyConstraint baseTableName=lecapr_areacon, constraintName=lecaprcon_lecapr_fk, referencedTableName=lecc_aprendidas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-536::sofis
ALTER TABLE mails_template ADD CONSTRAINT mail_tmp_org_fk FOREIGN KEY (mail_tmp_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-536', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 536, '7:ad0a2cf7adf50d57c085dd5f915ff36d', 'addForeignKeyConstraint baseTableName=mails_template, constraintName=mail_tmp_org_fk, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-537::sofis
ALTER TABLE media_proyectos ADD CONSTRAINT media_proy_fk FOREIGN KEY (media_proy_fk) REFERENCES proyectos (proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-537', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 537, '7:35846966f8e8ed69c4ff70846f1295f4', 'addForeignKeyConstraint baseTableName=media_proyectos, constraintName=media_proy_fk, referencedTableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-538::sofis
ALTER TABLE media_proyectos ADD CONSTRAINT media_tipo_fk FOREIGN KEY (media_tipo_fk) REFERENCES tipos_media (tip_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-538', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 538, '7:a93e1242be85298e744ea459939e3f74', 'addForeignKeyConstraint baseTableName=media_proyectos, constraintName=media_tipo_fk, referencedTableName=tipos_media', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-539::sofis
ALTER TABLE media_proyectos ADD CONSTRAINT media_usr_mod_fk FOREIGN KEY (media_usr_mod_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-539', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 539, '7:13df243299044cde976eb6d5bb86a400', 'addForeignKeyConstraint baseTableName=media_proyectos, constraintName=media_usr_mod_fk, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-540::sofis
ALTER TABLE media_proyectos ADD CONSTRAINT media_usr_pub_fk FOREIGN KEY (media_usr_pub_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-540', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 540, '7:f749611e2af6fb6b26a5dad043798a6f', 'addForeignKeyConstraint baseTableName=media_proyectos, constraintName=media_usr_pub_fk, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-541::sofis
ALTER TABLE media_proyectos ADD CONSTRAINT media_usr_rech_fk FOREIGN KEY (media_usr_rech_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-541', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 541, '7:8c49c1432d2ca3eec2fe7488169c8db4', 'addForeignKeyConstraint baseTableName=media_proyectos, constraintName=media_usr_rech_fk, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-542::sofis
ALTER TABLE notificacion_instancia ADD CONSTRAINT notinst_not_fk FOREIGN KEY (notinst_not_fk) REFERENCES notificacion (not_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-542', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 542, '7:d4f961af77dc7af9da5b6eb0bc5fed1d', 'addForeignKeyConstraint baseTableName=notificacion_instancia, constraintName=notinst_not_fk, referencedTableName=notificacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-543::sofis
ALTER TABLE notificacion_instancia ADD CONSTRAINT notinst_proy_fk FOREIGN KEY (notinst_proy_fk) REFERENCES proyectos (proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-543', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 543, '7:73b6e48f6aed315a6115ef69626a7fc2', 'addForeignKeyConstraint baseTableName=notificacion_instancia, constraintName=notinst_proy_fk, referencedTableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-544::sofis
ALTER TABLE objetivos_estrategicos ADD CONSTRAINT obj_est_org_fk FOREIGN KEY (obj_est_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-544', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 544, '7:51b26d5c840825095acc0c72b6bc003d', 'addForeignKeyConstraint baseTableName=objetivos_estrategicos, constraintName=obj_est_org_fk, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-545::sofis
ALTER TABLE organi_int_prove ADD CONSTRAINT orga_amb_fk FOREIGN KEY (orga_amb_fk) REFERENCES ambito (amb_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-545', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 545, '7:9a865f62788a0aa6fea90f4dd6d3a3de', 'addForeignKeyConstraint baseTableName=organi_int_prove, constraintName=orga_amb_fk, referencedTableName=ambito', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-546::sofis
ALTER TABLE organi_int_prove ADD CONSTRAINT orga_org_fk FOREIGN KEY (orga_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-546', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 546, '7:6094c552dd448e87c5f66ea20d084c4c', 'addForeignKeyConstraint baseTableName=organi_int_prove, constraintName=orga_org_fk, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-547::sofis
ALTER TABLE pagos ADD CONSTRAINT pag_adq_fk FOREIGN KEY (pag_adq_fk) REFERENCES adquisicion (adq_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-547', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 547, '7:74e077e069e0aa8b400cc2da5fb2484e', 'addForeignKeyConstraint baseTableName=pagos, constraintName=pag_adq_fk, referencedTableName=adquisicion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-548::sofis
ALTER TABLE pagos ADD CONSTRAINT pag_ent_fk FOREIGN KEY (pag_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-548', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 548, '7:2612f9f1025ff4f760c4e5cdcdb4abfe', 'addForeignKeyConstraint baseTableName=pagos, constraintName=pag_ent_fk, referencedTableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-549::sofis
ALTER TABLE pagos ADD CONSTRAINT pagos_organi_int_prove_FK FOREIGN KEY (pag_contr_organizacion_fk) REFERENCES organi_int_prove (orga_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-549', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 549, '7:2dece0f025d578c94c82a7e16f2455d7', 'addForeignKeyConstraint baseTableName=pagos, constraintName=pagos_organi_int_prove_FK, referencedTableName=organi_int_prove', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-550::sofis
ALTER TABLE participantes ADD CONSTRAINT part_ent_fk FOREIGN KEY (part_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-550', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 550, '7:fe8b52f668dbe140fddfd8f8b96b8ba4', 'addForeignKeyConstraint baseTableName=participantes, constraintName=part_ent_fk, referencedTableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-551::sofis
ALTER TABLE plantilla_entregables ADD CONSTRAINT plantilla_cro FOREIGN KEY (p_entregable_p_cro_fk) REFERENCES plantilla_cronograma (p_crono_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-551', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 551, '7:b97217676fa97ac2d82e85ea50d266b3', 'addForeignKeyConstraint baseTableName=plantilla_entregables, constraintName=plantilla_cro, referencedTableName=plantilla_cronograma', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-552::sofis
ALTER TABLE presupuesto ADD CONSTRAINT pre_fuente_organi FOREIGN KEY (pre_fuente_organi_fk) REFERENCES fuente_financiamiento (fue_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-552', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 552, '7:38a193c725ab953f0b10c20e81196287', 'addForeignKeyConstraint baseTableName=presupuesto, constraintName=pre_fuente_organi, referencedTableName=fuente_financiamiento', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-553::sofis
ALTER TABLE presupuesto ADD CONSTRAINT pre_moneda FOREIGN KEY (pre_moneda) REFERENCES moneda (mon_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-553', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 553, '7:93dbf469f7d8ac694db33d53ce96947d', 'addForeignKeyConstraint baseTableName=presupuesto, constraintName=pre_moneda, referencedTableName=moneda', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-554::sofis
ALTER TABLE productos ADD CONSTRAINT prod_ent_fk FOREIGN KEY (prod_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-554', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 554, '7:f275bd8255fca96e690bc1f97b586d6c', 'addForeignKeyConstraint baseTableName=productos, constraintName=prod_ent_fk, referencedTableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-555::sofis
ALTER TABLE prod_mes ADD CONSTRAINT prodmes_prod_fk FOREIGN KEY (prodmes_prod_fk) REFERENCES productos (prod_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-555', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 555, '7:44ed40bc61c543e077cf89d49cf4acfb', 'addForeignKeyConstraint baseTableName=prod_mes, constraintName=prodmes_prod_fk, referencedTableName=productos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-556::sofis
ALTER TABLE programas ADD CONSTRAINT prog_cro_fk FOREIGN KEY (prog_cro_fk) REFERENCES cronogramas (cro_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-556', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 556, '7:db159f840962ad7cddb7b7a27bb61a41', 'addForeignKeyConstraint baseTableName=programas, constraintName=prog_cro_fk, referencedTableName=cronogramas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-557::sofis
ALTER TABLE programas ADD CONSTRAINT prog_est_pendiente_fk FOREIGN KEY (prog_est_pendiente_fk) REFERENCES estados (est_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-557', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 557, '7:ce0d09c0203b54663e69ad443e0c6169', 'addForeignKeyConstraint baseTableName=programas, constraintName=prog_est_pendiente_fk, referencedTableName=estados', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-558::sofis
ALTER TABLE programas ADD CONSTRAINT prog_obj_est_fk FOREIGN KEY (prog_obj_est_fk) REFERENCES objetivos_estrategicos (obj_est_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-558', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 558, '7:df1310b665d241d11bcd3506520f86ca', 'addForeignKeyConstraint baseTableName=programas, constraintName=prog_obj_est_fk, referencedTableName=objetivos_estrategicos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-559::sofis
ALTER TABLE programas ADD CONSTRAINT prog_pre_fk FOREIGN KEY (prog_pre_fk) REFERENCES prog_pre (progpre_prog_fk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-559', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 559, '7:7d5a7c418987580c6c4e40dfdd70f2e4', 'addForeignKeyConstraint baseTableName=programas, constraintName=prog_pre_fk, referencedTableName=prog_pre', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-560::sofis
ALTER TABLE programas ADD CONSTRAINT prog_progindices_fk FOREIGN KEY (prog_progindices_fk) REFERENCES prog_indices (progind_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-560', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 560, '7:297c3dd564303f77d2766eac9d51b058', 'addForeignKeyConstraint baseTableName=programas, constraintName=prog_progindices_fk, referencedTableName=prog_indices', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-561::sofis
ALTER TABLE prog_pre ADD CONSTRAINT progpre_pre_fk FOREIGN KEY (progpre_pre_fk) REFERENCES presupuesto (pre_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-561', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 561, '7:8e4bc18c95e2ceb30a6ae37892df5105', 'addForeignKeyConstraint baseTableName=prog_pre, constraintName=progpre_pre_fk, referencedTableName=presupuesto', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-562::sofis
ALTER TABLE prog_pre ADD CONSTRAINT progpre_prog_fk FOREIGN KEY (progpre_prog_fk) REFERENCES programas (prog_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-562', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 562, '7:3736ed524bfbc81273c8f8db88916554', 'addForeignKeyConstraint baseTableName=prog_pre, constraintName=progpre_prog_fk, referencedTableName=programas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-563::sofis
ALTER TABLE proy_otros_cat_secundarias ADD CONSTRAINT proy_cat_cat_proy_fk FOREIGN KEY (proy_cat_cat_proy_fk) REFERENCES categoria_proyectos (cat_proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-563', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 563, '7:1ad73d04d693de98178bc02e89692881', 'addForeignKeyConstraint baseTableName=proy_otros_cat_secundarias, constraintName=proy_cat_cat_proy_fk, referencedTableName=categoria_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-564::sofis
ALTER TABLE proy_otros_cat_secundarias ADD CONSTRAINT proy_cat_proy_otros_fk FOREIGN KEY (proy_cat_proy_otros_fk) REFERENCES proy_otros_datos (proy_otr_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-564', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 564, '7:2787a7d695d561d7c25073832c64f28e', 'addForeignKeyConstraint baseTableName=proy_otros_cat_secundarias, constraintName=proy_cat_proy_otros_fk, referencedTableName=proy_otros_datos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-565::sofis
ALTER TABLE proyectos ADD CONSTRAINT proy_est_pendiente_fk FOREIGN KEY (proy_est_pendiente_fk) REFERENCES estados (est_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-565', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 565, '7:06b726621c77137c9fb964c449d85580', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proy_est_pendiente_fk, referencedTableName=estados', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-566::sofis
ALTER TABLE proyectos ADD CONSTRAINT proy_obj_est_fk FOREIGN KEY (proy_obj_est_fk) REFERENCES objetivos_estrategicos (obj_est_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-566', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 566, '7:e09a88c2515c58f2effa5cade36c6ee7', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proy_obj_est_fk, referencedTableName=objetivos_estrategicos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-567::sofis
ALTER TABLE proy_otros_datos ADD CONSTRAINT proy_otr_cat_fk FOREIGN KEY (proy_otr_cat_fk) REFERENCES categoria_proyectos (cat_proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-567', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 567, '7:c5db6b0ca2f933ea2efe98d822bf27ce', 'addForeignKeyConstraint baseTableName=proy_otros_datos, constraintName=proy_otr_cat_fk, referencedTableName=categoria_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-568::sofis
ALTER TABLE proy_otros_datos ADD CONSTRAINT proy_otr_cont_fk FOREIGN KEY (proy_otr_cont_fk) REFERENCES organi_int_prove (orga_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-568', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 568, '7:dbf974e6c1ddcb88db73b083eea08b4c', 'addForeignKeyConstraint baseTableName=proy_otros_datos, constraintName=proy_otr_cont_fk, referencedTableName=organi_int_prove', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-569::sofis
ALTER TABLE proyectos ADD CONSTRAINT proy_otr_dat_fk FOREIGN KEY (proy_otr_dat_fk) REFERENCES proy_otros_datos (proy_otr_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-569', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 569, '7:4fdfe089642eed05d00e383ed211c881', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proy_otr_dat_fk, referencedTableName=proy_otros_datos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-570::sofis
ALTER TABLE proy_otros_datos ADD CONSTRAINT proy_otr_ent_fk FOREIGN KEY (proy_otr_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-570', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 570, '7:b190fef3a64b101805a87daaa5547374', 'addForeignKeyConstraint baseTableName=proy_otros_datos, constraintName=proy_otr_ent_fk, referencedTableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-571::sofis
ALTER TABLE proy_otros_datos ADD CONSTRAINT proy_otr_est_pub_fk FOREIGN KEY (proy_otr_est_pub_fk) REFERENCES estados_publicacion (est_pub_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-571', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 571, '7:7158dda58d92296d1bd6a7609b5741ad', 'addForeignKeyConstraint baseTableName=proy_otros_datos, constraintName=proy_otr_est_pub_fk, referencedTableName=estados_publicacion', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-572::sofis
ALTER TABLE proy_otros_datos ADD CONSTRAINT proy_otr_eta_fk FOREIGN KEY (proy_otr_eta_fk) REFERENCES etapa (eta_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-572', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 572, '7:f289286292496d37fe2fd0e1a87c79e3', 'addForeignKeyConstraint baseTableName=proy_otros_datos, constraintName=proy_otr_eta_fk, referencedTableName=etapa', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-573::sofis
ALTER TABLE proy_otros_datos ADD CONSTRAINT proy_otr_ins_eje_fk FOREIGN KEY (proy_otr_ins_eje_fk) REFERENCES organi_int_prove (orga_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-573', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 573, '7:22f7368d18e8f5bb0abc9a6f2d359f02', 'addForeignKeyConstraint baseTableName=proy_otros_datos, constraintName=proy_otr_ins_eje_fk, referencedTableName=organi_int_prove', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-574::sofis
ALTER TABLE proyectos ADD CONSTRAINT proy_pre_fk FOREIGN KEY (proy_pre_fk) REFERENCES presupuesto (pre_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-574', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 574, '7:9681221c32a45d49a8a23e202e18fe23', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proy_pre_fk, referencedTableName=presupuesto', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-575::sofis
ALTER TABLE proyectos ADD CONSTRAINT proy_proyindices_fk FOREIGN KEY (proy_proyindices_fk) REFERENCES proy_indices (proyind_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-575', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 575, '7:446c36cfb9874122d3d24f18486af1c3', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proy_proyindices_fk, referencedTableName=proy_indices', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-576::sofis
ALTER TABLE proy_publica_hist ADD CONSTRAINT proy_publica_proy_fk FOREIGN KEY (proy_publica_proy_fk) REFERENCES proyectos (proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-576', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 576, '7:2bf7f1cf2fd39f0c139c0b4f8004a98d', 'addForeignKeyConstraint baseTableName=proy_publica_hist, constraintName=proy_publica_proy_fk, referencedTableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-577::sofis
ALTER TABLE proy_publica_hist ADD CONSTRAINT proy_publica_usu_fk FOREIGN KEY (proy_publica_usu_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-577', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 577, '7:ff58f34a2faf8497780cbc711975a0f7', 'addForeignKeyConstraint baseTableName=proy_publica_hist, constraintName=proy_publica_usu_fk, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-578::sofis
ALTER TABLE proy_sitact_historico ADD CONSTRAINT proy_sitact_proy_fk FOREIGN KEY (proy_sitact_proy_fk) REFERENCES proyectos (proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-578', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 578, '7:5f4ffa35762b15fe27ad4e86106b3d7d', 'addForeignKeyConstraint baseTableName=proy_sitact_historico, constraintName=proy_sitact_proy_fk, referencedTableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-579::sofis
ALTER TABLE proy_indices ADD CONSTRAINT proyind_periodo_fin_ent_fk FOREIGN KEY (proyind_periodo_fin_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-579', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 579, '7:d0a3164ab1c2116fc22d41114a8270f2', 'addForeignKeyConstraint baseTableName=proy_indices, constraintName=proyind_periodo_fin_ent_fk, referencedTableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-580::sofis
ALTER TABLE proy_indices ADD CONSTRAINT proyind_periodo_inicio_ent_fk FOREIGN KEY (proyind_periodo_inicio_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-580', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 580, '7:0070daf68ce41f847fcb8cfc26742061', 'addForeignKeyConstraint baseTableName=proy_indices, constraintName=proyind_periodo_inicio_ent_fk, referencedTableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-581::sofis
ALTER TABLE proy_pre ADD CONSTRAINT proypre_pre_fk FOREIGN KEY (proypre_pre_fk) REFERENCES presupuesto (pre_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-581', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 581, '7:21bd705a9bf498b405de1a42f6d27983', 'addForeignKeyConstraint baseTableName=proy_pre, constraintName=proypre_pre_fk, referencedTableName=presupuesto', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-582::sofis
ALTER TABLE proy_pre ADD CONSTRAINT proypre_proy_fk FOREIGN KEY (proypre_proy_fk) REFERENCES proyectos (proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-582', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 582, '7:9df7f8d64816757e0d18126570e879ee', 'addForeignKeyConstraint baseTableName=proy_pre, constraintName=proypre_proy_fk, referencedTableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-583::sofis
ALTER TABLE proy_replanificacion ADD CONSTRAINT proyreplan_proy_fk FOREIGN KEY (proyreplan_proy_fk) REFERENCES proyectos (proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-583', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 583, '7:4f3890749ead40b41b0e62e7dc5eae88', 'addForeignKeyConstraint baseTableName=proy_replanificacion, constraintName=proyreplan_proy_fk, referencedTableName=proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-584::sofis
ALTER TABLE riesgos ADD CONSTRAINT risk_ent_fk FOREIGN KEY (risk_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-584', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 584, '7:e734ff6f9627cb4785baba22d2d4a0f1', 'addForeignKeyConstraint baseTableName=riesgos, constraintName=risk_ent_fk, referencedTableName=entregables', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-585::sofis
ALTER TABLE riesgos ADD CONSTRAINT risk_usuario_superado_fk_fk FOREIGN KEY (risk_usuario_superado_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-585', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 585, '7:b54339cab58962d476efd14fc59bdb1f', 'addForeignKeyConstraint baseTableName=riesgos, constraintName=risk_usuario_superado_fk_fk, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-586::sofis
ALTER TABLE temas_calidad ADD CONSTRAINT tca_org_fk FOREIGN KEY (tca_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-586', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 586, '7:98d7352e9a44615beaef77451bce33c1', 'addForeignKeyConstraint baseTableName=temas_calidad, constraintName=tca_org_fk, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-587::sofis
ALTER TABLE documentos ADD CONSTRAINT tipo_doc_fk FOREIGN KEY (docs_tipodoc_fk) REFERENCES tipo_documento_instancia (tipodoc_inst_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-587', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 587, '7:9a136c4c14c5c0c167a1652ba159b02b', 'addForeignKeyConstraint baseTableName=documentos, constraintName=tipo_doc_fk, referencedTableName=tipo_documento_instancia', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-588::sofis
ALTER TABLE tipo_documento_instancia ADD CONSTRAINT tipodoc_inst_tipodoc_fk FOREIGN KEY (tipodoc_inst_tipodoc_fk) REFERENCES tipo_documento (tipdoc_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-588', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 588, '7:8e41dd1a9bbf8ec03df21a6e30bfd803', 'addForeignKeyConstraint baseTableName=tipo_documento_instancia, constraintName=tipodoc_inst_tipodoc_fk, referencedTableName=tipo_documento', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-589::sofis
ALTER TABLE tipo_documento ADD CONSTRAINT tipodoc_org_fk FOREIGN KEY (tipodoc_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-589', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 589, '7:edd0afe5b802555e71b201398d68f8fd', 'addForeignKeyConstraint baseTableName=tipo_documento, constraintName=tipodoc_org_fk, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-590::sofis
ALTER TABLE tipo_gasto ADD CONSTRAINT tipogas_org_fk FOREIGN KEY (tipogas_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-590', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 590, '7:6148c9045e7f99093c028b8207660a21', 'addForeignKeyConstraint baseTableName=tipo_gasto, constraintName=tipogas_org_fk, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-591::sofis
ALTER TABLE ss_usuario ADD CONSTRAINT usu_area_org FOREIGN KEY (usu_area_org) REFERENCES areas (area_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-591', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 591, '7:1e7b4d53c3809dc3e12c8e6dc333acc9', 'addForeignKeyConstraint baseTableName=ss_usuario, constraintName=usu_area_org, referencedTableName=areas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-592::sofis
ALTER TABLE ss_usu_ofi_roles ADD CONSTRAINT usu_ofi_roles_usu_area FOREIGN KEY (usu_ofi_roles_usu_area) REFERENCES areas (area_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-592', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 592, '7:9863dd5811b628b6be8144ed97056146', 'addForeignKeyConstraint baseTableName=ss_usu_ofi_roles, constraintName=usu_ofi_roles_usu_area, referencedTableName=areas', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-593::sofis
ALTER TABLE valor_hora ADD CONSTRAINT val_hor_mon_fk FOREIGN KEY (val_hor_mon_fk) REFERENCES moneda (mon_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-593', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 593, '7:eedf40cbf7d417e46968cfac760d8b1a', 'addForeignKeyConstraint baseTableName=valor_hora, constraintName=val_hor_mon_fk, referencedTableName=moneda', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-594::sofis
ALTER TABLE valor_hora ADD CONSTRAINT val_hor_org_fk FOREIGN KEY (val_hor_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-594', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 594, '7:b2346f04394d61c02becc41a28f0836b', 'addForeignKeyConstraint baseTableName=valor_hora, constraintName=val_hor_org_fk, referencedTableName=organismos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-595::sofis
ALTER TABLE valor_hora ADD CONSTRAINT val_hor_usu_fk FOREIGN KEY (val_hor_usu_fk) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-595', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 595, '7:35a1d70232dd8a89daa345c5f10092bd', 'addForeignKeyConstraint baseTableName=valor_hora, constraintName=val_hor_usu_fk, referencedTableName=ss_usuario', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-596::sofis
CREATE VIEW programas_proyectos AS select concat('1-',`siges_agesic_testing_agesic_20171108`.`programas`.`prog_pk`) AS `id`,`siges_agesic_testing_agesic_20171108`.`programas`.`prog_pk` AS `fichaFk`,1 AS `tipoFicha`,`siges_agesic_testing_agesic_20171108`.`programas`.`prog_fecha_crea` AS `fechaCrea`,`siges_agesic_testing_agesic_20171108`.`programas`.`prog_activo` AS `activo`,`siges_agesic_testing_agesic_20171108`.`programas`.`prog_org_fk` AS `organismo`,`siges_agesic_testing_agesic_20171108`.`programas`.`prog_nombre` AS `nombre`,`siges_agesic_testing_agesic_20171108`.`estados`.`est_pk` AS `estado`,`siges_agesic_testing_agesic_20171108`.`estados`.`est_nombre` AS `estadoNombre`,`siges_agesic_testing_agesic_20171108`.`programas`.`prog_est_pendiente_fk` AS `estadoPendiente`,`siges_agesic_testing_agesic_20171108`.`areas`.`area_pk` AS `areaPk`,`siges_agesic_testing_agesic_20171108`.`areas`.`area_nombre` AS `areaNombre`,`siges_agesic_testing_agesic_20171108`.`programas`.`prog_sol_aceptacion` AS `solAceptacion`,`siges_agesic_testing_agesic_20171108`.`programas`.`prog_usr_gerente_fk` AS `gerente`,`siges_agesic_testing_agesic_20171108`.`ss_usuario`.`usu_primer_apellido` AS `gerentePrimerApellido`,`siges_agesic_testing_agesic_20171108`.`ss_usuario`.`usu_primer_nombre` AS `gerentePrimerNombre`,`siges_agesic_testing_agesic_20171108`.`programas`.`prog_usr_pmofed_fk` AS `pmoFederada` from (((`siges_agesic_testing_agesic_20171108`.`programas` join `siges_agesic_testing_agesic_20171108`.`estados` on((`siges_agesic_testing_agesic_20171108`.`programas`.`prog_est_fk` = `siges_agesic_testing_agesic_20171108`.`estados`.`est_pk`))) join `siges_agesic_testing_agesic_20171108`.`areas` on((`siges_agesic_testing_agesic_20171108`.`programas`.`prog_area_fk` = `siges_agesic_testing_agesic_20171108`.`areas`.`area_pk`))) join `siges_agesic_testing_agesic_20171108`.`ss_usuario` on((`siges_agesic_testing_agesic_20171108`.`programas`.`prog_usr_gerente_fk` = `siges_agesic_testing_agesic_20171108`.`ss_usuario`.`usu_id`))) union select concat('2-',`siges_agesic_testing_agesic_20171108`.`proyectos`.`proy_pk`) AS `id`,`siges_agesic_testing_agesic_20171108`.`proyectos`.`proy_pk` AS `fichaFk`,2 AS `tipoFicha`,`siges_agesic_testing_agesic_20171108`.`proyectos`.`proy_fecha_crea` AS `fechaCrea`,`siges_agesic_testing_agesic_20171108`.`proyectos`.`proy_activo` AS `activo`,`siges_agesic_testing_agesic_20171108`.`proyectos`.`proy_org_fk` AS `organismo`,`siges_agesic_testing_agesic_20171108`.`proyectos`.`proy_nombre` AS `nombre`,`siges_agesic_testing_agesic_20171108`.`estados`.`est_pk` AS `estado`,`siges_agesic_testing_agesic_20171108`.`estados`.`est_nombre` AS `estadoNombre`,`siges_agesic_testing_agesic_20171108`.`proyectos`.`proy_est_pendiente_fk` AS `estadoPendiente`,`siges_agesic_testing_agesic_20171108`.`areas`.`area_pk` AS `areaPk`,`siges_agesic_testing_agesic_20171108`.`areas`.`area_nombre` AS `areaNombre`,`siges_agesic_testing_agesic_20171108`.`proyectos`.`proy_sol_aceptacion` AS `solAceptacion`,`siges_agesic_testing_agesic_20171108`.`proyectos`.`proy_usr_gerente_fk` AS `gerente`,`siges_agesic_testing_agesic_20171108`.`ss_usuario`.`usu_primer_apellido` AS `gerentePrimerApellido`,`siges_agesic_testing_agesic_20171108`.`ss_usuario`.`usu_primer_nombre` AS `gerentePrimerNombre`,`siges_agesic_testing_agesic_20171108`.`proyectos`.`proy_usr_pmofed_fk` AS `pmoFederada` from (((`siges_agesic_testing_agesic_20171108`.`proyectos` join `siges_agesic_testing_agesic_20171108`.`estados` on((`siges_agesic_testing_agesic_20171108`.`proyectos`.`proy_est_fk` = `siges_agesic_testing_agesic_20171108`.`estados`.`est_pk`))) join `siges_agesic_testing_agesic_20171108`.`areas` on((`siges_agesic_testing_agesic_20171108`.`proyectos`.`proy_area_fk` = `siges_agesic_testing_agesic_20171108`.`areas`.`area_pk`))) join `siges_agesic_testing_agesic_20171108`.`ss_usuario` on((`siges_agesic_testing_agesic_20171108`.`proyectos`.`proy_usr_gerente_fk` = `siges_agesic_testing_agesic_20171108`.`ss_usuario`.`usu_id`)));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-596', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 596, '7:3949532b865e903abe43739d225bfb21', 'createView viewName=programas_proyectos', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Changeset db.changelog_siges_clean_5.2.xml::1527510267430-597::sofis
CREATE VIEW ss_oficina AS select `siges_agesic_testing_agesic_20171108`.`organismos`.`org_pk` AS `ofi_id`,`siges_agesic_testing_agesic_20171108`.`organismos`.`org_nombre` AS `ofi_nombre`,`siges_agesic_testing_agesic_20171108`.`organismos`.`org_activo` AS `ofi_activo`,now() AS `ofi_fecha_creacion`,'' AS `ofi_origen`,1 AS `ofi_usuario`,1 AS `ofi_version` from `siges_agesic_testing_agesic_20171108`.`organismos`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1527510267430-597', 'sofis', 'db.changelog_siges_clean_5.2.xml', NOW(), 597, '7:76847f04eddb267375d732cb3d4ec7e6', 'createView viewName=ss_oficina', '', 'EXECUTED', NULL, NULL, '3.5.3', '7515052596');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

