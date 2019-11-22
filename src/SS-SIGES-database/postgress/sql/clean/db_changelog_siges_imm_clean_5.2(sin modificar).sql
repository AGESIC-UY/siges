
-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: db.changelog_siges_imm_5.2.xml
-- Ran at: 29/05/18 11:34
-- Against: postgres@jdbc:postgresql://localhost:5433/para_clean_siges_v5_2
-- Liquibase version: 3.0.0-SNAPSHOT
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE siges.databasechangeloglock (ID INT NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITH TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT PK_DATABASECHANGELOGLOCK PRIMARY KEY (ID));

INSERT INTO siges.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
-- Create Database Change Log Table
CREATE TABLE siges.databasechangelog (ID VARCHAR(63) NOT NULL, AUTHOR VARCHAR(63) NOT NULL, FILENAME VARCHAR(200) NOT NULL, DATEEXECUTED TIMESTAMP WITH TIME ZONE NOT NULL, ORDEREXECUTED INT NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20), CONSTRAINT PK_DATABASECHANGELOG PRIMARY KEY (ID, AUTHOR, FILENAME));

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-1::sofis::(Checksum: 7:d28713595c5c4ae70f6b946a3d459b6c)
CREATE TABLE aud_ss_localidades (loc_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, loc_codigo VARCHAR(255), loc_nombre VARCHAR(255), loc_ult_mod TIMESTAMP WITH TIME ZONE, loc_ult_origen VARCHAR(255), loc_ult_usuario VARCHAR(255), loc_version INT4, loc_depto_id INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-1', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 1, '7:d28713595c5c4ae70f6b946a3d459b6c', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-2::sofis::(Checksum: 7:9b26c4b3487b9a92ca53c55c85ae116a)
CREATE TABLE organismos (org_pk SERIAL NOT NULL, org_nombre VARCHAR(45), org_logo_nombre VARCHAR(45), org_direccion VARCHAR(45), org_logo BYTEA, org_activo BOOL DEFAULT true NOT NULL, org_token VARCHAR(100), CONSTRAINT organismos_pkey PRIMARY KEY (org_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-2', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 2, '7:9b26c4b3487b9a92ca53c55c85ae116a', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-3::sofis::(Checksum: 7:ac0814104bb8b99e576d757f91a1941d)
CREATE TABLE notificacion_instancia (notinst_pk SERIAL NOT NULL, notinst_not_fk INT4 NOT NULL, notinst_proy_fk INT4 NOT NULL, notinst_gerente_adjunto BOOL, notinst_pmof BOOL, notinst_pmot BOOL, notinst_sponsor BOOL, CONSTRAINT notificacion_instancia_pkey PRIMARY KEY (notinst_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-3', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 3, '7:ac0814104bb8b99e576d757f91a1941d', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-4::sofis::(Checksum: 7:4ef0c48450c89a30715981cadff15346)
CREATE TABLE componente_producto (com_pk SERIAL NOT NULL, com_nombre VARCHAR(100) NOT NULL, com_org_fk INT4 NOT NULL, com_version INT4 NOT NULL, CONSTRAINT componente_producto_pkey PRIMARY KEY (com_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-4', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 4, '7:4ef0c48450c89a30715981cadff15346', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-5::sofis::(Checksum: 7:340331fd31a008d1783565f7df9812c1)
CREATE TABLE ss_usuario (usu_id SERIAL NOT NULL, usu_administrador BOOL, usu_cambio_estado_desc TEXT, usu_cod VARCHAR(255) NOT NULL, usu_correo_electronico VARCHAR(255) NOT NULL, usu_cuenta_bloqueada BOOL, usu_descripcion TEXT, usu_direccion TEXT, usu_fecha_password TIMESTAMP WITH TIME ZONE, usu_fecha_uuid TIMESTAMP WITH TIME ZONE, usu_foto BYTEA, usu_intentos_fallidos INT4, usu_nro_doc VARCHAR(255), usu_oficina_por_defecto INT4, usu_origen TEXT, usu_password VARCHAR(255), usu_primer_apellido VARCHAR(255) NOT NULL, usu_primer_nombre VARCHAR(255) NOT NULL, usu_registrado BOOL, usu_segundo_apellido VARCHAR(255), usu_segundo_nombre VARCHAR(255), usu_telefono VARCHAR(255), usu_celular VARCHAR(255), usu_user_code INT4, usu_uuid_des VARCHAR(255), usu_version INT4, usu_vigente BOOL NOT NULL, usu_aprob_facturas BOOL, usu_ult_usuario VARCHAR(255), usu_ult_mod TIMESTAMP WITH TIME ZONE, usu_ult_origen VARCHAR(255), usu_area_org INT4, usu_token VARCHAR(100) DEFAULT 'NULL::character varying', usu_token_act TIMESTAMP WITH TIME ZONE, usu_ldap_user VARCHAR(50), CONSTRAINT ss_usuario_pkey PRIMARY KEY (usu_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-5', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 5, '7:340331fd31a008d1783565f7df9812c1', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-6::sofis::(Checksum: 7:306a73ac807acc9d8d4179d851eac9bf)
CREATE TABLE ss_tipos_documento_persona (tipdocper_id SERIAL NOT NULL, tipdocper_codigo VARCHAR(255), tipdocper_descripcion VARCHAR(255), tipdocper_habilitado BOOL, tipdocper_ult_mod TIMESTAMP WITH TIME ZONE, tipdocper_ult_origen VARCHAR(255), tipdocper_ult_usuario VARCHAR(255), tipdocper_version INT4, CONSTRAINT ss_tipos_documento_persona_pkey PRIMARY KEY (tipdocper_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-6', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 6, '7:306a73ac807acc9d8d4179d851eac9bf', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-7::sofis::(Checksum: 7:654f18d9f4e8f471316bd8b4e821d868)
CREATE TABLE registros_horas (rh_pk SERIAL NOT NULL, rh_usu_fk INT4 NOT NULL, rh_proy_fk INT4 NOT NULL, rh_ent_fk INT4 NOT NULL, rh_fecha date NOT NULL, rh_horas numeric NOT NULL, rh_comentario VARCHAR(4000), rh_aprobado BOOL, CONSTRAINT registros_horas_pkey PRIMARY KEY (rh_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-7', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 7, '7:654f18d9f4e8f471316bd8b4e821d868', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-8::sofis::(Checksum: 7:7156a563dcff1d57af1876f4bbe951f7)
CREATE TABLE prod_mes (prodmes_pk SERIAL NOT NULL, prodmes_prod_fk INT4 NOT NULL, prodmes_mes INT2 NOT NULL, prodmes_anio INT2 NOT NULL, prodmes_plan numeric NOT NULL, prodmes_real numeric, prodmes_acu_plan numeric NOT NULL, prodmes_acu_real numeric, CONSTRAINT prod_mes_pkey PRIMARY KEY (prodmes_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-8', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 8, '7:7156a563dcff1d57af1876f4bbe951f7', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-9::sofis::(Checksum: 7:07f8431a0ab388b3b30cf535f25ff9c5)
CREATE TABLE participantes (part_pk SERIAL NOT NULL, part_usu_fk INT4 NOT NULL, part_proy_fk INT4 NOT NULL, part_ent_fk INT4, part_horas_plan numeric, part_activo BOOL DEFAULT true NOT NULL, CONSTRAINT participantes_pkey PRIMARY KEY (part_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-9', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 9, '7:07f8431a0ab388b3b30cf535f25ff9c5', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-10::sofis::(Checksum: 7:ae3d90ceb6c70153a492b5681351762a)
CREATE TABLE aud_ss_personas (per_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, per_fec_nac TIMESTAMP WITH TIME ZONE, per_nro_doc VARCHAR(45), per_primer_apellido VARCHAR(100), per_primer_nombre VARCHAR(100), per_segundo_apellido VARCHAR(100), per_segundo_nombre VARCHAR(100), per_ult_mod_fecha TIMESTAMP WITH TIME ZONE, per_ult_mod_origen VARCHAR(45), per_ult_mod_usuario VARCHAR(45), per_version INT4, per_pais_doc INT4, per_tipo_doc INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-10', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 10, '7:ae3d90ceb6c70153a492b5681351762a', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-11::sofis::(Checksum: 7:559069ecdc215c1329613f5da310d9e6)
CREATE TABLE aud_ss_usuario (usu_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, usu_administrador BOOL, usu_cambio_estado_desc TEXT, usu_cod VARCHAR(255), usu_correo_electronico VARCHAR(255), usu_cuenta_bloqueada BOOL, usu_descripcion TEXT, usu_direccion TEXT, usu_fecha_password TIMESTAMP WITH TIME ZONE, usu_fecha_uuid TIMESTAMP WITH TIME ZONE, usu_foto BYTEA, usu_intentos_fallidos INT4, usu_nro_doc VARCHAR(255), usu_oficina_por_defecto INT4, usu_origen TEXT, usu_password VARCHAR(255), usu_primer_apellido VARCHAR(255), usu_primer_nombre VARCHAR(255), usu_registrado BOOL, usu_segundo_apellido VARCHAR(255), usu_segundo_nombre VARCHAR(255), usu_telefono VARCHAR(255), usu_user_code INT4, usu_uuid_des VARCHAR(255), usu_version INT4, usu_vigente BOOL, usu_aprob_facturas BOOL, usu_ult_usuario VARCHAR(255), usu_ult_mod TIMESTAMP WITH TIME ZONE, usu_ult_origen VARCHAR(255), usu_area_org INT4, usu_celular VARCHAR(255), usu_token VARCHAR(100) DEFAULT 'NULL::character varying', usu_token_act TIMESTAMP WITH TIME ZONE, usu_ldap_user VARCHAR(50));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-11', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 11, '7:559069ecdc215c1329613f5da310d9e6', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-12::sofis::(Checksum: 7:846347a16b9f4df8a87aeb620963da96)
CREATE TABLE valor_hora (val_hor_pk SERIAL NOT NULL, val_hor_usu_fk INT4 NOT NULL, val_hor_org_fk INT4 NOT NULL, val_hor_mon_fk INT4 NOT NULL, val_hor_valor numeric NOT NULL, val_hor_anio INT4 NOT NULL, CONSTRAINT valor_hora_pkey PRIMARY KEY (val_hor_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-12', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 12, '7:846347a16b9f4df8a87aeb620963da96', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-13::sofis::(Checksum: 7:dd5c510efc1ecc05dc16ae11cf7a0431)
CREATE TABLE prog_int (progint_prog_pk INT4 NOT NULL, progint_int_pk INT4 NOT NULL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-13', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 13, '7:dd5c510efc1ecc05dc16ae11cf7a0431', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-14::sofis::(Checksum: 7:d5d61f099c76292d2155bbeb72217f04)
CREATE TABLE ss_noticias (not_id SERIAL NOT NULL, not_ampliar VARCHAR(255), not_codigo VARCHAR(45), not_contenido TEXT, not_desde TIMESTAMP WITH TIME ZONE, not_hasta TIMESTAMP WITH TIME ZONE, not_imagen VARCHAR(255), not_titulo VARCHAR(255), not_ult_mod_fecha TIMESTAMP WITH TIME ZONE, not_ult_mod_origen VARCHAR(45), not_ult_mod_usuario VARCHAR(45), not_version INT4, CONSTRAINT ss_noticias_pkey PRIMARY KEY (not_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-14', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 14, '7:d5d61f099c76292d2155bbeb72217f04', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-15::sofis::(Checksum: 7:cb4f593f021bc4d80467d6e5f5e95e96)
CREATE TABLE plantilla_entregables (p_entregables_id SERIAL NOT NULL, p_entregables_nombre VARCHAR(1000), p_entregable_nivel INT4, p_entregable_esfuerzo INT4, p_entregable_duracion INT4, p_entregable_ant_fk INT4, p_entregable_p_cro_fk INT4, p_entregables_numero INT4, CONSTRAINT plantilla_entregables_pkey PRIMARY KEY (p_entregables_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-15', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 15, '7:cb4f593f021bc4d80467d6e5f5e95e96', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-16::sofis::(Checksum: 7:6023bfb07c6f86776a67e36389a6ee12)
CREATE TABLE busq_filtro (busq_filtro_pk SERIAL NOT NULL, busq_filtro_usu_fk INT4 NOT NULL, busq_filtro_org_fk INT4 NOT NULL, busq_filtro_xml TEXT NOT NULL, CONSTRAINT busq_filtro_pkey PRIMARY KEY (busq_filtro_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-16', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 16, '7:6023bfb07c6f86776a67e36389a6ee12', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-17::sofis::(Checksum: 7:244523cdf6f0b0ebaeeb90419c582f24)
CREATE TABLE estados (est_pk SERIAL NOT NULL, est_codigo VARCHAR(150), est_nombre VARCHAR(45), est_label VARCHAR(150), est_orden_proceso INT4, CONSTRAINT estados_pkey PRIMARY KEY (est_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-17', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 17, '7:244523cdf6f0b0ebaeeb90419c582f24', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-18::sofis::(Checksum: 7:cb838a8228e91380031d05570ad54be6)
CREATE TABLE ss_rol (rol_id SERIAL NOT NULL, rol_cod VARCHAR(255) NOT NULL, rol_descripcion TEXT, rol_nombre VARCHAR(255) NOT NULL, rol_label VARCHAR(150), rol_origen VARCHAR(255), rol_user_code INT4, rol_version INT4, rol_vigente BOOL, rol_tipo_usuario BOOL, CONSTRAINT ss_rol_pkey PRIMARY KEY (rol_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-18', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 18, '7:cb838a8228e91380031d05570ad54be6', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-19::sofis::(Checksum: 7:ab7f19b78879803ca4016abcf5e72325)
CREATE TABLE proy_indices (proyind_pk SERIAL NOT NULL, proyind_riesgo_expo FLOAT8, proyind_riesgo_ultact date, proyind_riesgo_alto INT4, proyind_metodo_estado FLOAT8, proyind_metodo_sin_aprobar BOOL, proyind_periodo_inicio date, proyind_periodo_fin date, proyind_porc_peso_total FLOAT8, proyind_cal_ind FLOAT8, proyind_cal_pend INT4, proyind_fase_color INT4, proyind_avance_par_azul INT4, proyind_avance_par_verde INT4, proyind_anvance_par_rojo INT4, proyind_avance_fin_azul INT4, proyind_avance_fin_verde INT4, proyind_anvance_fin_rojo INT4, proyind_fecha_act TIMESTAMP WITH TIME ZONE, proyind_periodo_inicio_ent_fk INT4, proyind_periodo_fin_ent_fk INT4, proyind_fecha_act_color INT4 DEFAULT 0 NOT NULL, CONSTRAINT proy_indices_pkey PRIMARY KEY (proyind_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-19', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 19, '7:ab7f19b78879803ca4016abcf5e72325', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-20::sofis::(Checksum: 7:0984d75006bd88fa2deee61fdc5fd9d8)
CREATE TABLE ss_domicilios (dom_id SERIAL NOT NULL, dom_apto VARCHAR(50), dom_letra VARCHAR(255), dom_calle VARCHAR(150), dom_codigo_postal VARCHAR(5), dom_depto_alt VARCHAR(255), dom_descripcion TEXT, dom_inmueble_nombre VARCHAR(100), dom_kilometro VARCHAR(9), dom_manzana VARCHAR(5), dom_numero_puerta VARCHAR(5), dom_oficina VARCHAR(255), dom_ruta VARCHAR(5), dom_solar VARCHAR(5), dom_ult_mod TIMESTAMP WITH TIME ZONE, dom_ult_origen VARCHAR(255), dom_ult_usuario VARCHAR(255), dom_version INT4, dom_depto_id INT4, dom_loc_id INT4, dom_pai_id INT4, dom_par_id BOOL, dom_tec_id INT4, dom_tvi_id INT4, CONSTRAINT ss_domicilios_pkey PRIMARY KEY (dom_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-20', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 20, '7:0984d75006bd88fa2deee61fdc5fd9d8', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-21::sofis::(Checksum: 7:061321aeefb077731998fd7ffd72bfff)
CREATE TABLE image (image_pk SERIAL NOT NULL, image_name VARCHAR(45) NOT NULL, image_desc VARCHAR(255) DEFAULT 'NULL::character varying', image_ext VARCHAR(20) NOT NULL, image_blob BYTEA NOT NULL, image_tipo INT4, image_orden INT4, CONSTRAINT image_pkey PRIMARY KEY (image_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-21', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 21, '7:061321aeefb077731998fd7ffd72bfff', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-22::sofis::(Checksum: 7:ecd685fbd4099841b0453552e43f49f6)
CREATE TABLE aud_ss_rol (rol_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, rol_cod VARCHAR(255), rol_descripcion TEXT, rol_nombre VARCHAR(255), rol_label VARCHAR(150) NOT NULL, rol_origen VARCHAR(255), rol_user_code INT4, rol_version INT4, rol_vigente BOOL, rol_tipo_usuario BOOL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-22', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 22, '7:ecd685fbd4099841b0453552e43f49f6', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-23::sofis::(Checksum: 7:457d54f433bc118e8b52f375d01a894b)
CREATE TABLE aud_ss_noticias (not_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, not_ampliar VARCHAR(255), not_codigo VARCHAR(45), not_contenido TEXT, not_desde TIMESTAMP WITH TIME ZONE, not_hasta TIMESTAMP WITH TIME ZONE, not_imagen VARCHAR(255), not_titulo VARCHAR(255), not_ult_mod_fecha TIMESTAMP WITH TIME ZONE, not_ult_mod_origen VARCHAR(45), not_ult_mod_usuario VARCHAR(45), not_version INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-23', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 23, '7:457d54f433bc118e8b52f375d01a894b', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-24::sofis::(Checksum: 7:0cc5b43d64fd9732b5757fbb4a5c68ae)
CREATE TABLE adquisicion (adq_pk SERIAL NOT NULL, adq_pre_fk INT4 NOT NULL, adq_nombre VARCHAR(300) NOT NULL, adq_prov_orga_fk INT4, adq_fuente_fk INT4, adq_moneda_fk INT4, adq_proc_compra VARCHAR(20), adq_proc_compra_grp VARCHAR(20), adq_componente_producto_fk INT4, adq_procedimiento_compra_fk INT4, adq_compartida_usuario_fk INT4, adq_compartida BOOL DEFAULT false NOT NULL, CONSTRAINT adquisicion_pkey PRIMARY KEY (adq_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-24', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 24, '7:0cc5b43d64fd9732b5757fbb4a5c68ae', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-25::sofis::(Checksum: 7:84973fe2376dd826b76842a34dc4c1a2)
CREATE TABLE tipos_media (tip_pk SERIAL NOT NULL, tip_cod VARCHAR(45), tip_nombre VARCHAR(245), CONSTRAINT tipos_media_pkey PRIMARY KEY (tip_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-25', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 25, '7:84973fe2376dd826b76842a34dc4c1a2', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-26::sofis::(Checksum: 7:af1ef7e012047658af539029b34d4b04)
CREATE TABLE areas (area_pk SERIAL NOT NULL, area_org_fk INT4 NOT NULL, area_padre INT4, area_nombre VARCHAR(250), area_abreviacion VARCHAR(45), area_director INT4, area_activo BOOL DEFAULT true, area_habilitada BOOL DEFAULT true, CONSTRAINT areas_pkey PRIMARY KEY (area_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-26', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 26, '7:af1ef7e012047658af539029b34d4b04', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-27::sofis::(Checksum: 7:f64341c678c69b42401a82f52b70aa15)
CREATE TABLE aud_ss_paridades (par_id BOOL NOT NULL, rev INT4 NOT NULL, revtype INT4, par_codigo VARCHAR(9), par_descripcion VARCHAR(45), par_ult_mod_fecha TIMESTAMP WITH TIME ZONE, par_ult_mod_origen VARCHAR(45), par_ult_mod_usuario VARCHAR(45), par_version INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-27', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 27, '7:f64341c678c69b42401a82f52b70aa15', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-28::sofis::(Checksum: 7:d291468712ff73ea507c091d66121012)
CREATE TABLE aud_programas_proyectos (id VARCHAR(13) NOT NULL, rev INT4 NOT NULL, revtype INT4, activo BOOL, "areaNombre" VARCHAR(255), "areaPk" INT4, estado INT4, "estadoNombre" VARCHAR(255), "estadoPendiente" INT4, "fechaCrea" date, "fichaFk" INT4, gerente INT4, "gerentePrimerApellido" VARCHAR(255), "gerentePrimerNombre" VARCHAR(255), nombre VARCHAR(45), organismo INT4, "pmoFederada" INT4, "tipoFicha" INT8);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-28', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 28, '7:d291468712ff73ea507c091d66121012', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-29::sofis::(Checksum: 7:9a293855c5a50ff62952ee7983cbee97)
CREATE TABLE ss_departamentos (depto_id SERIAL NOT NULL, depto_codigo VARCHAR(255), depto_nombre VARCHAR(255), depto_ult_mod TIMESTAMP WITH TIME ZONE, err_ult_origen VARCHAR(255), depto_ult_usuario VARCHAR(255), depto_version INT4, CONSTRAINT ss_departamentos_pkey PRIMARY KEY (depto_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-29', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 29, '7:9a293855c5a50ff62952ee7983cbee97', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-30::sofis::(Checksum: 7:cd2adbae06eecdd6b51a7f131b5793c3)
CREATE TABLE lecc_aprendidas (lecapr_pk SERIAL NOT NULL, lecapr_proy_fk INT4, lecapr_tipo_fk INT4 NOT NULL, lecapr_usr_fk INT4 NOT NULL, lecapr_org_fk INT4 NOT NULL, lecapr_fecha date NOT NULL, lecapr_desc VARCHAR(3000) NOT NULL, lecapr_activo BOOL, CONSTRAINT lecc_aprendidas_pkey PRIMARY KEY (lecapr_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-30', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 30, '7:cd2adbae06eecdd6b51a7f131b5793c3', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-31::sofis::(Checksum: 7:f7b43dbfc2a4fd69cb8d102843514b33)
CREATE TABLE proy_replanificacion (proyreplan_pk SERIAL NOT NULL, proyreplan_proy_fk INT4 NOT NULL, proyreplan_fecha date NOT NULL, proyreplan_desc VARCHAR(5000) NOT NULL, proyreplan_historial BOOL NOT NULL, proyreplan_activo BOOL, "version" INT4, proyreplan_generar_linea_base BOOL DEFAULT false NOT NULL, CONSTRAINT proy_replanificacion_pkey PRIMARY KEY (proyreplan_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-31', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 31, '7:f7b43dbfc2a4fd69cb8d102843514b33', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-32::sofis::(Checksum: 7:5904ba10d0a0873b3e4ccf99f97e315e)
CREATE TABLE fuente_financiamiento (fue_pk SERIAL NOT NULL, fue_nombre VARCHAR(300) NOT NULL, fue_org_fk INT4 NOT NULL, CONSTRAINT fuente_financiamiento_pkey PRIMARY KEY (fue_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-32', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 32, '7:5904ba10d0a0873b3e4ccf99f97e315e', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-33::sofis::(Checksum: 7:673d77bfd79362ec5191e54151311d00)
CREATE TABLE etapa (eta_pk SERIAL NOT NULL, eta_org_fk INT4 NOT NULL, eta_codigo VARCHAR(45) NOT NULL, eta_nombre VARCHAR(100) NOT NULL, eta_label VARCHAR(100), CONSTRAINT etapa_pkey PRIMARY KEY (eta_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-33', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 33, '7:673d77bfd79362ec5191e54151311d00', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-34::sofis::(Checksum: 7:20dbe08935210fe1409e4b0ec9475d92)
CREATE TABLE proy_lectura_area (proglectarea_area_pk INT4 NOT NULL, proglectarea_proy_pk INT4 NOT NULL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-34', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 34, '7:20dbe08935210fe1409e4b0ec9475d92', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-35::sofis::(Checksum: 7:0cd8897b8ba7d0829a351d89d20587e4)
CREATE TABLE proy_tags (proytag_proy_pk INT4 NOT NULL, proytag_area_pk INT4 NOT NULL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-35', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 35, '7:0cd8897b8ba7d0829a351d89d20587e4', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-36::sofis::(Checksum: 7:d1bf212ec9434e3d83ba38eed567b7a3)
CREATE TABLE sql_executed (sql_pk SERIAL NOT NULL, sql_ver_mayor INT4 NOT NULL, sql_ver_menor INT4 NOT NULL, sql_build INT4 NOT NULL, sql_desc TEXT, sql_fecha date, sql_update INT4 NOT NULL, CONSTRAINT sql_executed_pkey PRIMARY KEY (sql_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-36', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 36, '7:d1bf212ec9434e3d83ba38eed567b7a3', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-37::sofis::(Checksum: 7:247b835b8768678f6388c30ac0cf6d88)
CREATE TABLE calidad (cal_pk SERIAL NOT NULL, cal_peso INT4 NOT NULL, cal_vca_fk INT4 NOT NULL, cal_actualizacion date NOT NULL, cal_tipo INT4 NOT NULL, cal_ent_fk INT4, cal_prod_fk INT4, cal_tca_fk INT4, cal_proy_fk INT4 NOT NULL, CONSTRAINT calidad_pkey PRIMARY KEY (cal_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-37', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 37, '7:247b835b8768678f6388c30ac0cf6d88', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-38::sofis::(Checksum: 7:49986bbc476b182e51b79fb067cf79f6)
CREATE TABLE pge_configuraciones (cnf_id SERIAL NOT NULL, cnf_clave VARCHAR(255), cnf_crea_fecha TIMESTAMP WITH TIME ZONE, cnf_crea_origen INT4, cnf_crea_usu VARCHAR(255), cnf_ultmod_fecha TIMESTAMP WITH TIME ZONE, cnf_ultmod_origen INT4, cnf_ultmod_usu VARCHAR(255), cnf_valor VARCHAR(255), cnf_version INT4, CONSTRAINT pge_configuraciones_pkey PRIMARY KEY (cnf_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-38', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 38, '7:49986bbc476b182e51b79fb067cf79f6', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-39::sofis::(Checksum: 7:cf006f4cb6f2a55535d717a700ad0bfe)
CREATE TABLE tipo_gasto (tipogas_pk SERIAL NOT NULL, tipogas_org_fk INT4, tipogas_nombre VARCHAR(150) NOT NULL, CONSTRAINT tipo_gasto_pkey PRIMARY KEY (tipogas_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-39', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 39, '7:cf006f4cb6f2a55535d717a700ad0bfe', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-40::sofis::(Checksum: 7:5a87adcea4214471c9aa4654a1fad052)
CREATE TABLE aud_ss_errores (id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, err_codigo VARCHAR(255), err_descripcion VARCHAR(255), err_ult_mod TIMESTAMP WITH TIME ZONE, err_ult_origen VARCHAR(255), err_ult_usuario VARCHAR(255), err_version INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-40', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 40, '7:5a87adcea4214471c9aa4654a1fad052', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-41::sofis::(Checksum: 7:698b42d3c2a2e306b0ba5650fb7be811)
CREATE TABLE pge_invocaciones (inv_id SERIAL NOT NULL, inv_crea_usu VARCHAR(255), inv_env_fecha TIMESTAMP WITH TIME ZONE, inv_env_mensaje VARCHAR(255), inv_env_ok BOOL, inv_operacion VARCHAR(255), inv_rec_fecha TIMESTAMP WITH TIME ZONE, inv_rec_mensaje VARCHAR(255), inv_rec_ok BOOL, inv_servicio VARCHAR(255), inv_url VARCHAR(255), CONSTRAINT pge_invocaciones_pkey PRIMARY KEY (inv_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-41', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 41, '7:698b42d3c2a2e306b0ba5650fb7be811', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-42::sofis::(Checksum: 7:1c6ba129419ff7c456976b0812f2e04f)
CREATE TABLE aud_ss_rel_rol_operacion (rel_rol_operacion_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, rel_rol_operacion_editable BOOL, rel_rol_operacion_origen VARCHAR(255), rel_rol_operacion_user_code INT4, rel_rol_operacion_visible BOOL, rel_rol_operacion_operacion_id INT4, rel_rol_operacion_rol_id INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-42', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 42, '7:1c6ba129419ff7c456976b0812f2e04f', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-43::sofis::(Checksum: 7:1359ee82797cd2993098839784309ae2)
CREATE TABLE ss_telefonos (tel_id SERIAL NOT NULL, tel_numero VARCHAR(25), tel_observaciones VARCHAR(255), tel_prefijo VARCHAR(10), tel_ult_mod TIMESTAMP WITH TIME ZONE, tel_ult_origen VARCHAR(45), tel_ult_usuario VARCHAR(45), tel_version INT4, tel_tiptel_id INT4, CONSTRAINT ss_telefonos_pkey PRIMARY KEY (tel_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-43', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 43, '7:1359ee82797cd2993098839784309ae2', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-44::sofis::(Checksum: 7:d9b8ed08391a933d045369e80e9bb08b)
CREATE TABLE prog_docs_obl (progdocsobl_docs_pk INT4 NOT NULL, progdocsobl_prog_pk INT4 NOT NULL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-44', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 44, '7:d9b8ed08391a933d045369e80e9bb08b', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-45::sofis::(Checksum: 7:52db65bd6152344cd3ffd6dac7d28334)
CREATE TABLE plantilla_cronograma (p_crono_pk SERIAL NOT NULL, p_crono_nombre VARCHAR(845), p_crono_org_fk INT4, activo BOOL, CONSTRAINT plantilla_cronograma_pkey PRIMARY KEY (p_crono_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-45', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 45, '7:52db65bd6152344cd3ffd6dac7d28334', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-46::sofis::(Checksum: 7:82f8bb9823a0386cda9356ad3ead272b)
CREATE TABLE ss_categoper (cat_id SERIAL NOT NULL, cat_descripcion TEXT, cat_nombre VARCHAR(255) NOT NULL, cat_origen VARCHAR(255) NOT NULL, cat_user_code INT4 NOT NULL, cat_version INT4, cat_vigente BOOL, CONSTRAINT ss_categoper_pkey PRIMARY KEY (cat_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-46', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 46, '7:82f8bb9823a0386cda9356ad3ead272b', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-47::sofis::(Checksum: 7:0e713744c651723ddd622406586c6321)
CREATE TABLE ss_tipos_vialidad (tvi_id SERIAL NOT NULL, tvi_abreviacion VARCHAR(255), tvi_codigo VARCHAR(255), tvi_descripcion VARCHAR(255), tvi_ult_mod TIMESTAMP WITH TIME ZONE, tvi_ult_origen VARCHAR(255), tvi_ult_usuario VARCHAR(255), tvi_version INT4, CONSTRAINT ss_tipos_vialidad_pkey PRIMARY KEY (tvi_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-47', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 47, '7:0e713744c651723ddd622406586c6321', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-48::sofis::(Checksum: 7:e38a178134fea0a505aa906307802de2)
CREATE TABLE documentos (docs_pk SERIAL NOT NULL, docs_nombre VARCHAR(100), docs_fecha date NOT NULL, docs_privado BOOL, docs_estado FLOAT8, docs_entregable_fk INT4, docs_tipodoc_fk INT4 NOT NULL, docs_docfile_pk INT4, docs_aprobado BOOL, docs_pago_fk INT4, docs_ult_pub date, docs_pub_fecha date, "version" INT4 DEFAULT 0, docs_sigesable BOOL DEFAULT false NOT NULL, CONSTRAINT documentos_pkey PRIMARY KEY (docs_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-48', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 48, '7:e38a178134fea0a505aa906307802de2', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-49::sofis::(Checksum: 7:bcfe326d8c71e43ed9980b00b4b3b769)
CREATE TABLE prog_indices_pre (progindpre_pk SERIAL NOT NULL, progindpre_progind_fk INT4 NOT NULL, progindpre_mon_fk INT4 NOT NULL, progindpre_total FLOAT8, progindpre_est_pre INT4, progindpre_anio FLOAT8, progindpre_ac FLOAT8, progindpre_pv FLOAT8, CONSTRAINT prog_indices_pre_pkey PRIMARY KEY (progindpre_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-49', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 49, '7:bcfe326d8c71e43ed9980b00b4b3b769', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-50::sofis::(Checksum: 7:99351245a7996e04e7f3fc3cd63dbb79)
CREATE TABLE tipo_leccion (tipolec_pk SERIAL NOT NULL, tipolec_codigo VARCHAR(45), tipolec_nombre VARCHAR(150), CONSTRAINT tipo_leccion_pkey PRIMARY KEY (tipolec_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-50', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 50, '7:99351245a7996e04e7f3fc3cd63dbb79', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-51::sofis::(Checksum: 7:7452c7f5a92f6301d478656576179c18)
CREATE TABLE prog_indices (progind_pk SERIAL NOT NULL, progind_riesgo_expo FLOAT8, progind_riesgo_ultact date, progind_riesgo_alto INT4, progind_metodo_estado FLOAT8, progind_metodo_sin_aprobar BOOL, progind_periodo_inicio date, progind_periodo_fin date, progind_proy_actualizacion date, progind_cal_ind FLOAT8, progind_cal_pend INT4, progind_avance_par_azul INT4, progind_avance_par_verde INT4, progind_anvance_par_rojo INT4, progind_avance_fin_azul INT4, progind_avance_fin_verde INT4, progind_anvance_fin_rojo INT4, progind_fecha_act TIMESTAMP WITH TIME ZONE, progind_fecha_act_color INT4 DEFAULT 0 NOT NULL, CONSTRAINT prog_indices_pkey PRIMARY KEY (progind_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-51', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 51, '7:7452c7f5a92f6301d478656576179c18', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-52::sofis::(Checksum: 7:c2719316ef079f098a37ef1e0b22eaf3)
CREATE TABLE aud_ss_paises (pai_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, pai_codigo2 VARCHAR(255), pai_codigo3 VARCHAR(255), pai_comun BOOL, pai_habilitado BOOL, pai_nombre VARCHAR(255), pai_ult_mod TIMESTAMP WITH TIME ZONE, pai_ult_origen VARCHAR(255), pai_ult_usuario VARCHAR(255), pai_version INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-52', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 52, '7:c2719316ef079f098a37ef1e0b22eaf3', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-53::sofis::(Checksum: 7:66514e1ab9d93e79042300e7ebc5bc55)
CREATE TABLE ss_tipos_entrada_colectiva (tec_id SERIAL NOT NULL, tec_codigo VARCHAR(255), tec_descripcion VARCHAR(255), tec_ult_mod TIMESTAMP WITH TIME ZONE, tec_ult_origen VARCHAR(255), tec_ult_usuario VARCHAR(255), tec_version INT4, CONSTRAINT ss_tipos_entrada_colectiva_pkey PRIMARY KEY (tec_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-53', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 53, '7:66514e1ab9d93e79042300e7ebc5bc55', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-54::sofis::(Checksum: 7:d0753c3539cd0baffbc55d402f61e0ff)
CREATE TABLE prog_pre (progpre_prog_fk INT4 NOT NULL, progpre_pre_fk INT4 NOT NULL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-54', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 54, '7:d0753c3539cd0baffbc55d402f61e0ff', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-55::sofis::(Checksum: 7:eed7fdbcd0714754cf233194d4adb3b3)
CREATE TABLE tipo_documento (tipdoc_pk SERIAL NOT NULL, tipodoc_nombre VARCHAR(145) NOT NULL, tipodoc_exigido_desde INT4, tipodoc_peso INT4, tipodoc_org_fk INT4, tipodoc_resum_ejecutivo BOOL, CONSTRAINT tipo_documento_pkey PRIMARY KEY (tipdoc_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-55', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 55, '7:eed7fdbcd0714754cf233194d4adb3b3', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-56::sofis::(Checksum: 7:bf3f123f8e3698dbe7b22555c481249f)
CREATE TABLE proy_docs (proydoc_proy_pk INT4 NOT NULL, proydoc_doc_pk INT4 NOT NULL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-56', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 56, '7:bf3f123f8e3698dbe7b22555c481249f', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-57::sofis::(Checksum: 7:7de1c0cb9e121f42e3c83560ce985d92)
CREATE TABLE proy_int (proyint_proy_pk INT4 NOT NULL, proyint_int_pk INT4 NOT NULL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-57', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 57, '7:7de1c0cb9e121f42e3c83560ce985d92', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-58::sofis::(Checksum: 7:b24fd25ee6c926d439747ac6d6a583e4)
CREATE TABLE productos (prod_pk SERIAL NOT NULL, prod_peso INT4 NOT NULL, prod_und_medida VARCHAR(45) NOT NULL, prod_ent_fk INT4 NOT NULL, prod_fecha_crea date NOT NULL, prod_ult_mod date, prod_acumulado BOOL, prod_desc VARCHAR(2000), CONSTRAINT productos_pkey PRIMARY KEY (prod_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-58', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 58, '7:b24fd25ee6c926d439747ac6d6a583e4', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-59::sofis::(Checksum: 7:37d0ed70dab3636725778a3b688e31d0)
CREATE TABLE proy_otros_datos (proy_otr_pk SERIAL NOT NULL, proy_otr_eta_fk INT4, proy_otr_cont_fk INT4, proy_otr_ins_eje_fk INT4, proy_otr_ent_fk INT4, proy_otr_origen VARCHAR(1000), proy_otr_plazo INT4, proy_otr_observaciones VARCHAR(4000), proy_otr_cat_fk INT4, proy_otr_est_pub_fk INT4, CONSTRAINT proy_otros_datos_pkey PRIMARY KEY (proy_otr_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-59', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 59, '7:37d0ed70dab3636725778a3b688e31d0', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-60::sofis::(Checksum: 7:4273bfbb648be3daed1e1f19c119d63c)
CREATE TABLE proyectos (proy_pk SERIAL NOT NULL, proy_org_fk INT4 NOT NULL, proy_est_fk INT4 NOT NULL, proy_est_pendiente_fk INT4, proy_sol_aceptacion BOOL, proy_area_fk INT4 NOT NULL, proy_usr_adjunto_fk INT4 NOT NULL, proy_usr_sponsor_fk INT4 NOT NULL, proy_usr_gerente_fk INT4 NOT NULL, proy_usr_pmofed_fk INT4, proy_prog_fk INT4, proy_risk_fk INT4, proy_cro_fk INT4, proy_pre_fk INT4, proy_proyindices_fk INT4, proy_peso INT4, proy_descripcion VARCHAR(4000), proy_objetivo VARCHAR(4000), proy_obj_sigeso VARCHAR(4000), proy_situacion_actual VARCHAR(4000), proy_pub_web BOOL, proy_leccion_aprendida VARCHAR(256), proy_nombre VARCHAR(100), proy_grp VARCHAR(45), proy_semaforo_amarillo INT4, proy_semaforo_rojo INT4, proy_activo BOOL, proy_fecha_crea date, proy_fecha_act date NOT NULL, proy_ult_mod date, proy_ult_origen VARCHAR(45), proy_version INT4, proy_fecha_est_act date, proy_id_migrado INT4, proy_sigesable BOOL DEFAULT true NOT NULL, proy_otr_dat_fk INT4, proy_latlng_fk INT4, proy_ult_usuario VARCHAR, proy_obj_est_fk INT4, proy_factor_impacto TEXT, proy_fecha_act_pub date, CONSTRAINT proyectos_pkey PRIMARY KEY (proy_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-60', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 60, '7:4273bfbb648be3daed1e1f19c119d63c', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-61::sofis::(Checksum: 7:67417665a4bd1d7fc0b749dafca902e4)
CREATE TABLE area_conocimiento (con_pk SERIAL NOT NULL, con_nombre VARCHAR(150) NOT NULL, con_org_fk INT4 NOT NULL, con_padre_fk INT4, CONSTRAINT area_conocimiento_pkey PRIMARY KEY (con_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-61', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 61, '7:67417665a4bd1d7fc0b749dafca902e4', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-62::sofis::(Checksum: 7:579c5e4bb9a5da7178bc15a3a72d75b4)
CREATE TABLE aud_doc_file (docfile_pk INT4 NOT NULL, rev INT4 NOT NULL, revtype INT2, docfile_nombre VARCHAR(255), docfile_path VARCHAR(255), docfile_ult_mod TIMESTAMP WITH TIME ZONE, docfile_ult_origen VARCHAR(255), docfile_ult_usuario VARCHAR(255), docfile_version INT4, docfile_doc_fk INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-62', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 62, '7:579c5e4bb9a5da7178bc15a3a72d75b4', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-63::sofis::(Checksum: 7:8a6d52aa53321bf4aa0d95223116b823)
CREATE TABLE aud_ss_tipos_documento_persona (tipdocper_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, tipdocper_codigo VARCHAR(255), tipdocper_descripcion VARCHAR(255), tipdocper_habilitado BOOL, tipdocper_ult_mod TIMESTAMP WITH TIME ZONE, tipdocper_ult_origen VARCHAR(255), tipdocper_ult_usuario VARCHAR(255), tipdocper_version INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-63', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 63, '7:8a6d52aa53321bf4aa0d95223116b823', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-64::sofis::(Checksum: 7:c8af5f6661603571681b3f33206c022c)
CREATE TABLE aud_ss_configuraciones (id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, cnf_codigo VARCHAR(255), cnf_descripcion VARCHAR(255), cnf_html BOOL, cnf_protegido BOOL, cnf_ult_mod TIMESTAMP WITH TIME ZONE, cnf_ult_origen VARCHAR(255), cnf_ult_usuario VARCHAR(255), cnf_valor TEXT, cnf_version INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-64', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 64, '7:c8af5f6661603571681b3f33206c022c', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-65::sofis::(Checksum: 7:68597c7fc84bb253bde2abcb1e32776c)
CREATE TABLE ss_usu_ofi_roles (usu_ofi_roles_id SERIAL NOT NULL, usu_ofi_roles_origen VARCHAR(255) NOT NULL, usu_ofi_roles_user_code INT4 NOT NULL, usu_ofi_roles_oficina INT4, usu_ofi_roles_rol INT4 NOT NULL, usu_ofi_roles_usuario INT4 NOT NULL, usu_ofi_roles_usu_area INT4, usu_ofi_roles_activo BOOL, CONSTRAINT ss_usu_ofi_roles_pkey PRIMARY KEY (usu_ofi_roles_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-65', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 65, '7:68597c7fc84bb253bde2abcb1e32776c', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-66::sofis::(Checksum: 7:46468d69ce42f6997df164580a3cbe0d)
CREATE TABLE gastos (gas_pk SERIAL NOT NULL, gas_proy_fk INT4 NOT NULL, gas_tipo_fk INT4 NOT NULL, gas_usu_fk INT4 NOT NULL, gas_mon_fk INT4 NOT NULL, gas_importe numeric NOT NULL, gas_fecha date NOT NULL, gas_obs VARCHAR(4000) NOT NULL, gas_aprobado BOOL, CONSTRAINT gastos_pkey PRIMARY KEY (gas_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-66', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 66, '7:46468d69ce42f6997df164580a3cbe0d', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-67::sofis::(Checksum: 7:554da6b381686440eb15a0892d5e36eb)
CREATE TABLE areas_tags (arastag_pk SERIAL NOT NULL, areatag_org_fk INT4 NOT NULL, areatag_padre_fk INT4, areatag_tematica VARCHAR(45), areatag_excluyente BOOL, areatag_nombre VARCHAR(45), areatag_categoria VARCHAR(45), CONSTRAINT areas_tags_pkey PRIMARY KEY (arastag_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-67', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 67, '7:554da6b381686440eb15a0892d5e36eb', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-68::sofis::(Checksum: 7:dc2d5e1d2a285258eec3f04ef52f53d5)
CREATE TABLE lecapr_areacon (lecaprcon_con_fk INT4 NOT NULL, lecaprcon_lecapr_fk INT4 NOT NULL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-68', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 68, '7:dc2d5e1d2a285258eec3f04ef52f53d5', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-69::sofis::(Checksum: 7:eea469975445f4b0adfc83b771bcf1ba)
CREATE TABLE moneda (mon_pk SERIAL NOT NULL, mon_nombre VARCHAR(100) NOT NULL, mon_signo VARCHAR(50), mon_cod_pais VARCHAR(10), CONSTRAINT moneda_pkey PRIMARY KEY (mon_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-69', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 69, '7:eea469975445f4b0adfc83b771bcf1ba', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-70::sofis::(Checksum: 7:1109712ddbcb2777f36dc265b99b3610)
CREATE TABLE aud_ss_departamentos (depto_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, depto_codigo VARCHAR(255), depto_nombre VARCHAR(255), depto_ult_mod TIMESTAMP WITH TIME ZONE, err_ult_origen VARCHAR(255), depto_ult_usuario VARCHAR(255), depto_version INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-70', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 70, '7:1109712ddbcb2777f36dc265b99b3610', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-71::sofis::(Checksum: 7:b94f01a400da28598de8280caa09b1f6)
CREATE TABLE roles_interesados (rolint_pk SERIAL NOT NULL, rolint_org_fk INT4 NOT NULL, rolint_nombre VARCHAR(45), CONSTRAINT roles_interesados_pkey PRIMARY KEY (rolint_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-71', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 71, '7:b94f01a400da28598de8280caa09b1f6', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-72::sofis::(Checksum: 7:1f6623cdad1ea97ae9874a80caf58613)
CREATE TABLE aud_ss_tipos_telefono ("tipTel_id" INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, "tipTel_codigo" VARCHAR(255), "tipTel_descripcion" VARCHAR(255), "tipTel_habilitado" BOOL, "tipTel_ult_mod" TIMESTAMP WITH TIME ZONE, "tipTel_ult_origen" VARCHAR(255), "tipTel_ult_usuario" VARCHAR(255), "tipTel_version" INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-72', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 72, '7:1f6623cdad1ea97ae9874a80caf58613', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-73::sofis::(Checksum: 7:85c5cebed3419dabd3ef0e1b38827f7b)
CREATE TABLE ss_ayuda (ayu_id SERIAL NOT NULL, ayu_codigo VARCHAR(45), ayu_texto TEXT, ayu_ult_mod_fecha TIMESTAMP WITH TIME ZONE, ayu_ult_mod_origen VARCHAR(45), ayu_ult_mod_usuario VARCHAR(45), ayu_version INT4, CONSTRAINT ss_ayuda_pkey PRIMARY KEY (ayu_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-73', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 73, '7:85c5cebed3419dabd3ef0e1b38827f7b', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-74::sofis::(Checksum: 7:26265b5cbd65bc98242d7a3675598410)
CREATE TABLE ambito (amb_pk SERIAL NOT NULL, amb_nombre VARCHAR(100) NOT NULL, amb_org_fk INT4 NOT NULL, CONSTRAINT ambito_pkey PRIMARY KEY (amb_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-74', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 74, '7:26265b5cbd65bc98242d7a3675598410', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-75::sofis::(Checksum: 7:c8ddd0e8ceef8fda9ac5fbd44498640e)
CREATE TABLE tipo_documento_instancia (tipodoc_inst_pk SERIAL NOT NULL, tipodoc_inst_tipodoc_fk INT4 NOT NULL, tipodoc_inst_exigido_desde INT4, tipodoc_inst_peso INT4, tipodoc_inst_proy_fk INT4, tipodoc_inst_prog_fk INT4, tipodoc_inst_resum_ejecutivo BOOL, CONSTRAINT tipo_documento_instancia_pkey PRIMARY KEY (tipodoc_inst_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-75', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 75, '7:c8ddd0e8ceef8fda9ac5fbd44498640e', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-76::sofis::(Checksum: 7:50a776eb310fe5ba568f8f16bf8fac43)
CREATE TABLE devengado (dev_pk SERIAL NOT NULL, dev_adq_fk INT4 NOT NULL, dev_mes INT2 NOT NULL, dev_anio INT2 NOT NULL, dev_plan numeric, dev_real numeric, CONSTRAINT devengado_pkey PRIMARY KEY (dev_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-76', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 76, '7:50a776eb310fe5ba568f8f16bf8fac43', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-77::sofis::(Checksum: 7:1185022e3d6fad5ee6ce88908d7010c5)
CREATE TABLE interesados (int_pk SERIAL NOT NULL, int_rolint_fk INT4 NOT NULL, int_observaciones VARCHAR(4000), int_pers_fk INT4, int_orga_fk INT4, int_ent_fk INT4, CONSTRAINT interesados_pkey PRIMARY KEY (int_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-77', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 77, '7:1185022e3d6fad5ee6ce88908d7010c5', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-78::sofis::(Checksum: 7:04b8489894a3b9dbce1ab70669863da0)
CREATE TABLE ss_rel_rol_operacion (rel_rol_operacion_id SERIAL NOT NULL, rel_rol_operacion_editable BOOL NOT NULL, rel_rol_operacion_origen VARCHAR(255) NOT NULL, rel_rol_operacion_user_code INT4 NOT NULL, rel_rol_operacion_visible BOOL NOT NULL, rel_rol_operacion_operacion_id INT4 NOT NULL, rel_rol_operacion_rol_id INT4 NOT NULL, CONSTRAINT ss_rel_rol_operacion_pkey PRIMARY KEY (rel_rol_operacion_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-78', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 78, '7:04b8489894a3b9dbce1ab70669863da0', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-79::sofis::(Checksum: 7:e8ae065f04f37c7b999f6972ec24f347)
CREATE TABLE ss_personas (per_id SERIAL NOT NULL, per_fec_nac TIMESTAMP WITH TIME ZONE, per_nro_doc VARCHAR(45), per_primer_apellido VARCHAR(100) NOT NULL, per_primer_nombre VARCHAR(100), per_segundo_apellido VARCHAR(100) NOT NULL, per_segundo_nombre VARCHAR(100), per_ult_mod_fecha TIMESTAMP WITH TIME ZONE, per_ult_mod_origen VARCHAR(45), per_ult_mod_usuario VARCHAR(45), per_version INT4, per_pais_doc INT4, per_tipo_doc INT4, CONSTRAINT ss_personas_pkey PRIMARY KEY (per_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-79', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 79, '7:e8ae065f04f37c7b999f6972ec24f347', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-80::sofis::(Checksum: 7:0f8dfd541d6c10d3a347a0077aa9a0d8)
CREATE TABLE proy_otros_cat_secundarias (proy_cat_proy_otros_fk INT4 NOT NULL, proy_cat_cat_proy_fk INT4 NOT NULL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-80', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 80, '7:0f8dfd541d6c10d3a347a0077aa9a0d8', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-81::sofis::(Checksum: 7:e1d2b068c54c7df494141c2f0b52c896)
CREATE TABLE categoria_proyectos (cat_proy_pk SERIAL NOT NULL, cat_proy_codigo VARCHAR(45) NOT NULL, cat_proy_nombre VARCHAR(145) NOT NULL, cat_proy_activo BOOL DEFAULT true NOT NULL, cat_tipo INT4 DEFAULT 0 NOT NULL, cat_icono INT4, cat_icono_marker INT4, cat_org_fk INT4, CONSTRAINT categoria_proyectos_pkey PRIMARY KEY (cat_proy_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-81', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 81, '7:e1d2b068c54c7df494141c2f0b52c896', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-82::sofis::(Checksum: 7:ca3a33379fcc7723b386d3c3d29761fa)
CREATE TABLE pagos (pag_pk SERIAL NOT NULL, pag_adq_fk INT4 NOT NULL, pag_ent_fk INT4, pag_observacion VARCHAR(3000), pag_fecha_planificada date NOT NULL, pag_importe_planificado numeric NOT NULL, pag_fecha_real date, pag_importe_real numeric, pag_txt_referencia VARCHAR(20), pag_confirmar BOOL, pag_gasto INT2 DEFAULT 0 NOT NULL, pag_inversion INT2 DEFAULT 0, pag_contr_organizacion_fk INT4, pag_contr_porcentaje INT2, CONSTRAINT pagos_pkey PRIMARY KEY (pag_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-82', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 82, '7:ca3a33379fcc7723b386d3c3d29761fa', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-83::sofis::(Checksum: 7:a07300d017279131456693a9905da218)
CREATE TABLE proy_sitact_historico (proy_sitact_hist_pk SERIAL NOT NULL, proy_sitact_fecha date NOT NULL, proy_sitact_desc VARCHAR(4000), proy_sitact_proy_fk INT4 NOT NULL, proy_sitact_usu_fk INT4, "version" INT4, CONSTRAINT proy_sitact_historico_pkey PRIMARY KEY (proy_sitact_hist_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-83', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 83, '7:a07300d017279131456693a9905da218', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-84::sofis::(Checksum: 7:5b600d1dd26b541c6bbe1cc28785f7a6)
CREATE TABLE estados_sigesacion (est_pub_pk SERIAL NOT NULL, est_pub_codigo VARCHAR(45), est_pub_nombre VARCHAR(145), CONSTRAINT estados_sigesacion_pkey PRIMARY KEY (est_pub_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-84', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 84, '7:5b600d1dd26b541c6bbe1cc28785f7a6', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-85::sofis::(Checksum: 7:d6b19273112bd35f8157d5fa9947a9b1)
CREATE TABLE prog_docs (progdocs_prog_pk INT4 NOT NULL, progdocs_doc_pk INT4 NOT NULL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-85', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 85, '7:d6b19273112bd35f8157d5fa9947a9b1', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-86::sofis::(Checksum: 7:09f64593d429b8a54e5ea360b032daa5)
CREATE TABLE ent_hist_linea_base (enthist_pk SERIAL NOT NULL, enthist_ent_fk INT4 NOT NULL, enthist_fecha date NOT NULL, enthist_inicio_linea_base INT8 NOT NULL, enthist_fin_linea_base INT8, enthist_duracion INT4, enthist_replan_fk INT4, CONSTRAINT ent_hist_linea_base_pkey PRIMARY KEY (enthist_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-86', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 86, '7:09f64593d429b8a54e5ea360b032daa5', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-87::sofis::(Checksum: 7:bd5363b56639de0cc952ef1c7536101c)
CREATE TABLE media_proyectos (media_pk SERIAL NOT NULL, media_tipo_fk INT4, media_link VARCHAR(545), media_estado INT4, media_proy_fk INT4, media_principal BOOL, media_orden INT4, media_usr_pub_fk INT4, media_pub_fecha TIMESTAMP WITH TIME ZONE, media_usr_mod_fk INT4, media_mod_fecha TIMESTAMP WITH TIME ZONE, media_usr_rech_fk INT4, media_rech_fecha TIMESTAMP WITH TIME ZONE, media_comentario VARCHAR(2000), media_contenttype VARCHAR(200), media_sigesable BOOL, CONSTRAINT media_proyectos_pkey PRIMARY KEY (media_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-87', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 87, '7:bd5363b56639de0cc952ef1c7536101c', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-88::sofis::(Checksum: 7:4defe52f6825a8a9e0ab453fe7d6d46d)
CREATE TABLE lineabase_historico (lineabase_pk SERIAL NOT NULL, "lineabase_entFk" INT4 NOT NULL, lineabase_fecha date NOT NULL, lineabase_inicio INT8 NOT NULL, lineabase_duracion INT4, lineabase_fin INT8 NOT NULL, CONSTRAINT lineabase_historico_pkey PRIMARY KEY (lineabase_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-88', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 88, '7:4defe52f6825a8a9e0ab453fe7d6d46d', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-89::sofis::(Checksum: 7:1fd653a2e0a552fc44bb788757fb65a4)
CREATE TABLE aud_ss_ayuda (ayu_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, ayu_codigo VARCHAR(45), ayu_texto TEXT, ayu_ult_mod_fecha TIMESTAMP WITH TIME ZONE, ayu_ult_mod_origen VARCHAR(45), ayu_ult_mod_usuario VARCHAR(45), ayu_version INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-89', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 89, '7:1fd653a2e0a552fc44bb788757fb65a4', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-90::sofis::(Checksum: 7:55473221ece5d41e34298b40f55c6bca)
CREATE TABLE cronogramas (cro_pk SERIAL NOT NULL, cro_ent_seleccionado INT4, cro_ent_borrados VARCHAR(45), cro_resources VARCHAR(45), cro_permiso_escritura BOOL, cro_permiso_escritura_padre BOOL, CONSTRAINT cronogramas_pkey PRIMARY KEY (cro_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-90', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 90, '7:55473221ece5d41e34298b40f55c6bca', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-91::sofis::(Checksum: 7:bc68224f16d5094e393bb1d4e4a0fe7d)
CREATE TABLE programas (prog_pk SERIAL NOT NULL, prog_org_fk INT4 NOT NULL, prog_area_fk INT4 NOT NULL, prog_est_fk INT4 NOT NULL, prog_est_pendiente_fk INT4, prog_sol_aceptacion BOOL, prog_usr_gerente_fk INT4 NOT NULL, prog_usr_adjunto_fk INT4 NOT NULL, prog_usr_sponsor_fk INT4 NOT NULL, prog_usr_pmofed_fk INT4, prog_cro_fk INT4, prog_pre_fk INT4, prog_progindices_fk INT4, prog_nombre VARCHAR(100), prog_descripcion VARCHAR(4000), prog_objetivo VARCHAR(4000), prog_obj_sigeso VARCHAR(4000), prog_pub_web BOOL, prog_grp VARCHAR(45), prog_semaforo_amarillo INT4, prog_semaforo_rojo INT4, prog_activo BOOL, prog_fecha_crea date, prog_fecha_act date NOT NULL, prog_version INT4, prog_ult_usuario VARCHAR(45), prog_id_migrado INT4, prog_ult_origen VARCHAR, prog_ult_mod date, prog_obj_est_fk INT4, prog_factor_impacto TEXT, prog_habilitado BOOL DEFAULT true NOT NULL, CONSTRAINT programas_pkey PRIMARY KEY (prog_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-91', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 91, '7:bc68224f16d5094e393bb1d4e4a0fe7d', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-92::sofis::(Checksum: 7:bf0bd67d43f8f389924dc992fbddaf68)
CREATE TABLE aud_ss_telefonos (tel_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, tel_numero VARCHAR(25), tel_observaciones VARCHAR(255), tel_prefijo VARCHAR(10), tel_ult_mod TIMESTAMP WITH TIME ZONE, tel_ult_origen VARCHAR(45), tel_ult_usuario VARCHAR(45), tel_version INT4, tel_tiptel_id INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-92', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 92, '7:bf0bd67d43f8f389924dc992fbddaf68', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-93::sofis::(Checksum: 7:c4d899c1954d956da6c2d593a8c0bc83)
CREATE TABLE proy_indices_pre (proyindpre_pk SERIAL NOT NULL, proyindpre_proyind_fk INT4 NOT NULL, proyindpre_mon_fk INT4 NOT NULL, proyindpre_total FLOAT8, proyindpre_est_pre INT4, proyindpre_ac FLOAT8, proyindpre_pv FLOAT8, proyindpre_anio FLOAT8, CONSTRAINT proy_indices_pre_pkey PRIMARY KEY (proyindpre_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-93', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 93, '7:c4d899c1954d956da6c2d593a8c0bc83', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-94::sofis::(Checksum: 7:72b991466b3f173dc8f71adc203250b3)
CREATE TABLE departamentos (dep_pk INT4 NOT NULL, dep_nombre VARCHAR(145), dep_lat FLOAT8, dep_lng FLOAT8, dep_zoom INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-94', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 94, '7:72b991466b3f173dc8f71adc203250b3', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-95::sofis::(Checksum: 7:882b805c237e817b80d3c2412ad5563d)
CREATE TABLE entregables (ent_pk SERIAL NOT NULL, ent_cro_fk INT4 NOT NULL, ent_id INT4 NOT NULL, ent_nombre VARCHAR(1000), ent_codigo VARCHAR(256), ent_nivel INT4, ent_status VARCHAR(256), ent_inicio INT8, ent_duracion INT4, ent_fin INT8, ent_horas_estimadas VARCHAR(15), ent_inicio_es_hito BOOL, ent_fin_es_hito BOOL, ent_collapsed BOOL, ent_assigs VARCHAR(256), ent_coord_usu_fk INT4, ent_esfuerzo INT4, ent_inicio_linea_base INT8 DEFAULT 0, ent_duracion_linea_base INT4, ent_fin_linea_base INT8 DEFAULT 0, ent_predecesor_fk VARCHAR(2000), ent_predecesor_dias INT4, ent_descripcion VARCHAR(1000), ent_progreso INT4, ent_relevante BOOL, ent_parent BOOL, ent_inicio_periodo BOOL DEFAULT false NOT NULL, ent_fin_periodo BOOL DEFAULT false NOT NULL, CONSTRAINT entregables_pkey PRIMARY KEY (ent_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-95', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 95, '7:882b805c237e817b80d3c2412ad5563d', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-96::sofis::(Checksum: 7:df27bccc698eddeae6a1bb8d6c1c30cf)
CREATE TABLE objetivos_estrategicos (obj_est_pk SERIAL NOT NULL, obj_est_nombre VARCHAR(100) NOT NULL, obj_est_descripcion VARCHAR(100) NOT NULL, obj_est_org_fk INT4 NOT NULL, CONSTRAINT objetivos_estrategicos_pkey PRIMARY KEY (obj_est_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-96', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 96, '7:df27bccc698eddeae6a1bb8d6c1c30cf', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-97::sofis::(Checksum: 7:110eba7082a9acad442c5515c591256d)
CREATE TABLE revinfo (rev SERIAL NOT NULL, revtstmp INT8, "version" INT4 DEFAULT 0, CONSTRAINT revinfo_pkey PRIMARY KEY (rev));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-97', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 97, '7:110eba7082a9acad442c5515c591256d', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-98::sofis::(Checksum: 7:75cc4e77f91dc66259bfb5d55ad919c0)
CREATE TABLE ss_paises (pai_id SERIAL NOT NULL, pai_codigo2 VARCHAR(255), pai_codigo3 VARCHAR(255), pai_comun BOOL, pai_habilitado BOOL, pai_nombre VARCHAR(255), pai_ult_mod TIMESTAMP WITH TIME ZONE, pai_ult_origen VARCHAR(255), pai_ult_usuario VARCHAR(255), pai_version INT4, CONSTRAINT ss_paises_pkey PRIMARY KEY (pai_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-98', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 98, '7:75cc4e77f91dc66259bfb5d55ad919c0', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-99::sofis::(Checksum: 7:2167a5217561dd3bf21ab5b561e83bfb)
CREATE TABLE aud_ss_usu_ofi_roles (usu_ofi_roles_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, usu_ofi_roles_origen VARCHAR(255), usu_ofi_roles_user_code INT4, usu_ofi_roles_oficina INT4, usu_ofi_roles_rol INT4, usu_ofi_roles_usuario INT4, usu_ofi_roles_activo BOOL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-99', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 99, '7:2167a5217561dd3bf21ab5b561e83bfb', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-100::sofis::(Checksum: 7:e8c24ed6010535415075418192664983)
CREATE TABLE roles_usuarios (rol_pk INT4 NOT NULL, rol_nombre VARCHAR(45));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-100', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 100, '7:e8c24ed6010535415075418192664983', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-101::sofis::(Checksum: 7:13dc4c7dd54a89ec7b5529404d07921e)
CREATE TABLE aud_ss_domicilios (dom_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, dom_apto VARCHAR(50), dom_letra VARCHAR(255), dom_calle VARCHAR(150), dom_codigo_postal VARCHAR(5), dom_depto_alt VARCHAR(255), dom_descripcion TEXT, dom_inmueble_nombre VARCHAR(100), dom_kilometro VARCHAR(9), dom_manzana VARCHAR(5), dom_numero_puerta VARCHAR(5), dom_oficina VARCHAR(255), dom_ruta VARCHAR(5), dom_solar VARCHAR(5), dom_ult_mod TIMESTAMP WITH TIME ZONE, dom_ult_origen VARCHAR(255), dom_ult_usuario VARCHAR(255), dom_version INT4, dom_depto_id INT4, dom_loc_id INT4, dom_pai_id INT4, dom_par_id BOOL, dom_tec_id INT4, dom_tvi_id INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-101', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 101, '7:13dc4c7dd54a89ec7b5529404d07921e', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-102::sofis::(Checksum: 7:08b0c14834b98da36d5071a153336aef)
CREATE TABLE notificacion (not_pk SERIAL NOT NULL, not_org_fk INT4 NOT NULL, not_cod VARCHAR(45) NOT NULL, not_desc VARCHAR(245) NOT NULL, not_valor INT4, not_gerente_adjunto BOOL, not_pmof BOOL, not_pmot BOOL, not_sponsor BOOL, not_msg VARCHAR(5000) NOT NULL, CONSTRAINT notificacion_pkey PRIMARY KEY (not_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-102', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 102, '7:08b0c14834b98da36d5071a153336aef', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-103::sofis::(Checksum: 7:de630f4448c145f7bf1c964ca0e550f1)
CREATE TABLE ss_tipos_telefono ("tipTel_id" SERIAL NOT NULL, "tipTel_codigo" VARCHAR(255), "tipTel_descripcion" VARCHAR(255), "tipTel_habilitado" BOOL, "tipTel_ult_mod" TIMESTAMP WITH TIME ZONE, "tipTel_ult_origen" VARCHAR(255), "tipTel_ult_usuario" VARCHAR(255), "tipTel_version" INT4, CONSTRAINT ss_tipos_telefono_pkey PRIMARY KEY ("tipTel_id"));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-103', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 103, '7:de630f4448c145f7bf1c964ca0e550f1', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-104::sofis::(Checksum: 7:eca51ab5adf938791497e77c4350fb78)
CREATE TABLE ss_operacion (ope_id SERIAL NOT NULL, ope_codigo VARCHAR(255) NOT NULL, ope_descripcion TEXT NOT NULL, ope_nombre VARCHAR(255) NOT NULL, ope_origen VARCHAR(255) NOT NULL, ope_tipocampo VARCHAR(255) NOT NULL, ope_user_code INT4 NOT NULL, ope_version INT4, ope_vigente BOOL NOT NULL, ope_categoria_id INT4, CONSTRAINT ss_operacion_pkey PRIMARY KEY (ope_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-104', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 104, '7:eca51ab5adf938791497e77c4350fb78', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-105::sofis::(Checksum: 7:4b3786244a8493d59e3db52b4104eb95)
CREATE TABLE aud_ss_oficina (ofi_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, ofi_fecha_creacion TIMESTAMP WITH TIME ZONE, ofi_nombre VARCHAR(255), ofi_origen VARCHAR(255), ofi_usuario INT4, ofi_version INT4, ofi_activo BOOL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-105', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 105, '7:4b3786244a8493d59e3db52b4104eb95', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-106::sofis::(Checksum: 7:7879248cb6d9847fb54c5c65bac879b7)
CREATE TABLE doc_file (docfile_pk SERIAL NOT NULL, docfile_file BYTEA, docfile_nombre VARCHAR(256) NOT NULL, docfile_doc_fk INT4 NOT NULL, docfile_path VARCHAR(255), docfile_ult_mod TIMESTAMP WITH TIME ZONE, docfile_ult_origen VARCHAR(255), docfile_ult_usuario VARCHAR(255), docfile_version INT4 DEFAULT 0, CONSTRAINT doc_file_pkey PRIMARY KEY (docfile_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-106', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 106, '7:7879248cb6d9847fb54c5c65bac879b7', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-107::sofis::(Checksum: 7:a558ed26b409fefb4095314a2ce26ecb)
CREATE TABLE ss_paridades (par_id BOOL NOT NULL, par_codigo VARCHAR(9), par_descripcion VARCHAR(45), par_ult_mod_fecha TIMESTAMP WITH TIME ZONE, par_ult_mod_origen VARCHAR(45), par_ult_mod_usuario VARCHAR(45), par_version INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-107', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 107, '7:a558ed26b409fefb4095314a2ce26ecb', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-108::sofis::(Checksum: 7:5098880361665025617a8b2a1a453165)
CREATE TABLE presupuesto (pre_pk SERIAL NOT NULL, pre_base numeric, pre_moneda INT4, pre_fuente_organi_fk INT4, CONSTRAINT presupuesto_pkey PRIMARY KEY (pre_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-108', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 108, '7:5098880361665025617a8b2a1a453165', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-109::sofis::(Checksum: 7:c4773aa1654ce06dc4291693aafecf12)
CREATE TABLE area_organi_int_prove (areaorgintprov_pk SERIAL NOT NULL, areaorgintprov_orga_fk INT4 NOT NULL, areaorgintprov_org_fk INT4 NOT NULL, areaorgintprov_nombre VARCHAR(40), CONSTRAINT area_organi_int_prove_pkey PRIMARY KEY (areaorgintprov_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-109', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 109, '7:c4773aa1654ce06dc4291693aafecf12', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-110::sofis::(Checksum: 7:d3b8f36795404ae230d1cdda5c1d1fb8)
CREATE TABLE ss_localidades (loc_id SERIAL NOT NULL, loc_codigo VARCHAR(255), loc_nombre VARCHAR(255), loc_ult_mod TIMESTAMP WITH TIME ZONE, loc_ult_origen VARCHAR(255), loc_ult_usuario VARCHAR(255), loc_version INT4, loc_depto_id INT4, CONSTRAINT ss_localidades_pkey PRIMARY KEY (loc_id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-110', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 110, '7:d3b8f36795404ae230d1cdda5c1d1fb8', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-111::sofis::(Checksum: 7:51bb422556b3b308f4ef758d37c6ba88)
CREATE TABLE riesgos (risk_pk SERIAL NOT NULL, risk_proy_fk INT4 NOT NULL, risk_nombre VARCHAR(3000) NOT NULL, risk_fecha_actu date, risk_probabilidad INT4, risk_impacto INT4, risk_ent_fk INT4, risk_fecha_limite date, risk_efecto VARCHAR(3000), risk_estategia VARCHAR(3000), risk_disparador VARCHAR(3000), risk_contingencia VARCHAR(3000), risk_superado BOOL, risk_fecha_superado date, risk_usuario_superado_fk INT4, risk_exposicion FLOAT8, "version" INT4, CONSTRAINT riesgos_pkey PRIMARY KEY (risk_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-111', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 111, '7:51bb422556b3b308f4ef758d37c6ba88', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-112::sofis::(Checksum: 7:feb2044504022933d5b4723f704088b1)
CREATE TABLE valor_calidad_codigos (vca_pk SERIAL NOT NULL, vca_codigo VARCHAR(45) NOT NULL, vca_nombre VARCHAR(100) NOT NULL, vca_habilitado BOOL NOT NULL, CONSTRAINT valor_calidad_codigos_pkey PRIMARY KEY (vca_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-112', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 112, '7:feb2044504022933d5b4723f704088b1', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-113::sofis::(Checksum: 7:c267d42694539b61c96d2d1acd94d530)
CREATE TABLE prog_lectura_area (proglectarea_prog_pk INT4 NOT NULL, proglectarea_area_pk INT4 NOT NULL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-113', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 113, '7:c267d42694539b61c96d2d1acd94d530', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-114::sofis::(Checksum: 7:b11bb7fab6de26f029c0d0501c841654)
CREATE TABLE temas_calidad (tca_pk SERIAL NOT NULL, tca_nombre VARCHAR(100) NOT NULL, tca_org_fk INT4 NOT NULL, CONSTRAINT temas_calidad_pkey PRIMARY KEY (tca_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-114', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 114, '7:b11bb7fab6de26f029c0d0501c841654', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-115::sofis::(Checksum: 7:b4c1f3066152221ca958e99b2b2eead5)
CREATE TABLE mails_template (mail_tmp_pk SERIAL NOT NULL, mail_tmp_cod VARCHAR(45) NOT NULL, mail_tmp_org_fk INT4 NOT NULL, mail_tmp_asunto VARCHAR(200) NOT NULL, mail_tmp_mensaje VARCHAR(5000) NOT NULL, CONSTRAINT mails_template_pkey PRIMARY KEY (mail_tmp_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-115', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 115, '7:b4c1f3066152221ca958e99b2b2eead5', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-116::sofis::(Checksum: 7:96ffb2c6329585e53d01fd02a97e65e2)
CREATE TABLE ss_errores (id INT4 NOT NULL, err_codigo VARCHAR(255), err_descripcion VARCHAR(255), err_ult_mod TIMESTAMP WITH TIME ZONE, err_ult_origen VARCHAR(255), err_ult_usuario VARCHAR(255), err_version INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-116', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 116, '7:96ffb2c6329585e53d01fd02a97e65e2', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-117::sofis::(Checksum: 7:d39b14a26b99038fdae5adab0f171417)
CREATE TABLE proy_pre (proypre_proy_fk INT4 NOT NULL, proypre_pre_fk INT4 NOT NULL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-117', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 117, '7:d39b14a26b99038fdae5adab0f171417', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-118::sofis::(Checksum: 7:84143718241ec2de9ce855008d152177)
CREATE TABLE aud_programas (prog_pk INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, prog_org_fk INT4, prog_area_fk INT4, prog_sol_aceptacion BOOL, prog_nombre VARCHAR(45), prog_objetivo VARCHAR(256), prog_obj_sigeso VARCHAR(256), prog_grp INT4, prog_semaforo_verde INT4, prog_semaforo_amarillo INT4, prog_version INT4, prog_ult_usuario VARCHAR(45), prog_ult_mod VARCHAR(45), prog_ult_origen date);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-118', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 118, '7:84143718241ec2de9ce855008d152177', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-119::sofis::(Checksum: 7:5422299b4aa734513c6beb2ef14d2f5e)
CREATE TABLE aud_ss_tipos_vialidad (tvi_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, tvi_abreviacion VARCHAR(255), tvi_codigo VARCHAR(255), tvi_descripcion VARCHAR(255), tvi_ult_mod TIMESTAMP WITH TIME ZONE, tvi_ult_origen VARCHAR(255), tvi_ult_usuario VARCHAR(255), tvi_version INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-119', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 119, '7:5422299b4aa734513c6beb2ef14d2f5e', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-120::sofis::(Checksum: 7:541484c274735a33328352ac8b2c71f3)
CREATE TABLE notificacion_envio (notenv_pk SERIAL NOT NULL, notenv_fecha date NOT NULL, notenv_proy_fk INT4 NOT NULL, notenv_not_cod VARCHAR(45) NOT NULL, CONSTRAINT notificacion_envio_pkey PRIMARY KEY (notenv_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-120', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 120, '7:541484c274735a33328352ac8b2c71f3', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-121::sofis::(Checksum: 7:c3e8165c5e428fa6bc03e8084920dc34)
CREATE TABLE ss_configuraciones (id SERIAL NOT NULL, cnf_org_fk INT4, cnf_codigo VARCHAR(145), cnf_descripcion VARCHAR(245), cnf_valor VARCHAR(255), cnf_protegido BOOL, cnf_html BOOL, cnf_ult_usuario VARCHAR(45), cnf_ult_mod VARCHAR(45), cnf_version INT4, cnf_ult_origen VARCHAR(50), CONSTRAINT ss_configuraciones_pkey PRIMARY KEY (id));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-121', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 121, '7:c3e8165c5e428fa6bc03e8084920dc34', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-122::sofis::(Checksum: 7:4eff925ebb457fc15ecb47517dcd997e)
CREATE TABLE aud_ss_categoper (cat_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, cat_descripcion TEXT, cat_nombre VARCHAR(255), cat_origen VARCHAR(255), cat_user_code INT4, cat_version INT4, cat_vigente BOOL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-122', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 122, '7:4eff925ebb457fc15ecb47517dcd997e', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-123::sofis::(Checksum: 7:470a696ba2d0408039a7c3ce4ca4bed2)
CREATE TABLE aud_ss_operacion (ope_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, ope_codigo VARCHAR(255), ope_descripcion TEXT, ope_nombre VARCHAR(255), ope_origen VARCHAR(255), ope_tipocampo VARCHAR(255), ope_user_code INT4, ope_version INT4, ope_vigente BOOL, ope_categoria_id INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-123', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 123, '7:470a696ba2d0408039a7c3ce4ca4bed2', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-124::sofis::(Checksum: 7:d3cd438d38df73c734d4f63568b4df9c)
CREATE TABLE latlng_proyectos (latlng_pk SERIAL NOT NULL, latlng_lat numeric, latlng_lng numeric, latlng_proy_fk INT4, latlang_dep_fk INT4, latlang_loc_fk INT4, latlang_codigopostal INT4, latlang_barrio VARCHAR(245), latlang_loc VARCHAR(245), CONSTRAINT latlng_proyectos_pkey PRIMARY KEY (latlng_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-124', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 124, '7:d3cd438d38df73c734d4f63568b4df9c', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-125::sofis::(Checksum: 7:991f834c3136d013b6a43d70c6109337)
CREATE TABLE aud_pge_configuraciones (cnf_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, cnf_clave VARCHAR(255), cnf_crea_fecha TIMESTAMP WITH TIME ZONE, cnf_crea_origen INT4, cnf_crea_usu VARCHAR(255), cnf_ultmod_fecha TIMESTAMP WITH TIME ZONE, cnf_ultmod_origen INT4, cnf_ultmod_usu VARCHAR(255), cnf_valor VARCHAR(255), cnf_version INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-125', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 125, '7:991f834c3136d013b6a43d70c6109337', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-126::sofis::(Checksum: 7:52463300fe2d6be8e0b85c6cc5e7e3e3)
CREATE TABLE personas (pers_pk SERIAL NOT NULL, pers_rol_fk INT4 NOT NULL, pers_orga_fk INT4 NOT NULL, pers_nombre VARCHAR(45) NOT NULL, pers_telefono VARCHAR(45), pers_cargo VARCHAR(45), pers_mail VARCHAR(45), CONSTRAINT personas_pkey PRIMARY KEY (pers_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-126', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 126, '7:52463300fe2d6be8e0b85c6cc5e7e3e3', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-127::sofis::(Checksum: 7:89622890608212c197c574f9df606390)
CREATE TABLE procedimiento_compra (proc_comp_pk SERIAL NOT NULL, proc_comp_nombre VARCHAR(45) DEFAULT 'NULL::character varying', proc_comp_org_fk INT4, proc_comp_version INT4, proc_comp_descripcion VARCHAR(200) DEFAULT 'NULL::character varying', proc_comp_habilitado BOOL DEFAULT true, CONSTRAINT procedimiento_compra_pkey PRIMARY KEY (proc_comp_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-127', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 127, '7:89622890608212c197c574f9df606390', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-128::sofis::(Checksum: 7:65e3f6ecd9d9e624cb7322e75d6cad5f)
CREATE TABLE proy_sigesa_hist (proy_sigesa_pk SERIAL NOT NULL, proy_sigesa_fecha TIMESTAMP WITH TIME ZONE NOT NULL, proy_sigesa_proy_fk INT4 NOT NULL, proy_sigesa_usu_fk INT4 NOT NULL, CONSTRAINT proy_sigesa_hist_pkey PRIMARY KEY (proy_sigesa_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-128', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 128, '7:65e3f6ecd9d9e624cb7322e75d6cad5f', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-129::sofis::(Checksum: 7:1b8f8927e4b68b1e34c2fce5e0760d75)
CREATE TABLE ss_usuarios_datos (ss_usu_dat_pk SERIAL NOT NULL, ss_usu_dat_area_fk INT4 NOT NULL, ss_usu_dat_usu_fk INT4 NOT NULL, CONSTRAINT ss_usuarios_datos_pkey PRIMARY KEY (ss_usu_dat_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-129', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 129, '7:1b8f8927e4b68b1e34c2fce5e0760d75', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-130::sofis::(Checksum: 7:a64151ddd0c12d0aeea1001b0507a797)
CREATE TABLE aud_ss_tipos_entrada_colectiva (tec_id INT4 NOT NULL, rev INT4 NOT NULL, revtype INT4, tec_codigo VARCHAR(255), tec_descripcion VARCHAR(255), tec_ult_mod TIMESTAMP WITH TIME ZONE, tec_ult_origen VARCHAR(255), tec_ult_usuario VARCHAR(255), tec_version INT4);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-130', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 130, '7:a64151ddd0c12d0aeea1001b0507a797', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-131::sofis::(Checksum: 7:7322a54ffc83bcf3202b6a7cea5fcadf)
CREATE TABLE organi_int_prove (orga_pk SERIAL NOT NULL, orga_nombre VARCHAR(50), orga_proveedor BOOL, orga_razon_social VARCHAR(50), orga_rut VARCHAR(45), orga_mail VARCHAR(45), orga_telefono VARCHAR(45), orga_web VARCHAR(45), orga_direccion VARCHAR(100), orga_ambito VARCHAR(45), orga_org_fk INT4 NOT NULL, orga_amb_fk INT4, CONSTRAINT organi_int_prove_pkey PRIMARY KEY (orga_pk));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-131', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 131, '7:7322a54ffc83bcf3202b6a7cea5fcadf', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-132::sofis::(Checksum: 7:c5c43b269ee5cbc2326f9ea93dc23df1)
CREATE TABLE prog_tags (progtag_prog_pk INT4 NOT NULL, progtag_area_pk INT4 NOT NULL);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-132', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 132, '7:c5c43b269ee5cbc2326f9ea93dc23df1', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-133::sofis::(Checksum: 7:9168558bd62dcfa73e35d0be055b6179)
ALTER TABLE aud_programas_proyectos ADD CONSTRAINT aud_programas_proyectos_pkey PRIMARY KEY (id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-133', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 133, '7:9168558bd62dcfa73e35d0be055b6179', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-134::sofis::(Checksum: 7:9be73573ce028d776f6aa6356da0381d)
ALTER TABLE aud_ss_tipos_telefono ADD CONSTRAINT aud_ss_tipos_telefono_pkey PRIMARY KEY ("tipTel_id", rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-134', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 134, '7:9be73573ce028d776f6aa6356da0381d', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-135::sofis::(Checksum: 7:2ba4ca9eaa80d3d0226904c9bba54559)
ALTER TABLE aud_ss_personas ADD CONSTRAINT aud_ss_personas_pkey PRIMARY KEY (per_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-135', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 135, '7:2ba4ca9eaa80d3d0226904c9bba54559', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-136::sofis::(Checksum: 7:de9398c42f8436994cd074c4e54a1e0f)
ALTER TABLE proy_otros_cat_secundarias ADD CONSTRAINT proy_otros_cat_secundarias_pkey PRIMARY KEY (proy_cat_proy_otros_fk, proy_cat_cat_proy_fk);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-136', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 136, '7:de9398c42f8436994cd074c4e54a1e0f', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-137::sofis::(Checksum: 7:c37e53d14c18dcf3154710ecd4238198)
ALTER TABLE aud_ss_noticias ADD CONSTRAINT aud_ss_noticias_pkey PRIMARY KEY (not_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-137', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 137, '7:c37e53d14c18dcf3154710ecd4238198', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-138::sofis::(Checksum: 7:9babc5c29dd381741bec34a3f10ff3ab)
ALTER TABLE aud_ss_tipos_entrada_colectiva ADD CONSTRAINT aud_ss_tipos_entrada_colectiva_pkey PRIMARY KEY (tec_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-138', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 138, '7:9babc5c29dd381741bec34a3f10ff3ab', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-139::sofis::(Checksum: 7:de7045db11ac183a8007e3f75ca7cd28)
ALTER TABLE aud_ss_ayuda ADD CONSTRAINT aud_ss_ayuda_pkey PRIMARY KEY (ayu_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-139', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 139, '7:de7045db11ac183a8007e3f75ca7cd28', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-140::sofis::(Checksum: 7:704d0ea6a1cb6fdd5eac046575f938af)
ALTER TABLE prog_lectura_area ADD CONSTRAINT prog_lectura_area_pkey PRIMARY KEY (proglectarea_prog_pk, proglectarea_area_pk);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-140', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 140, '7:704d0ea6a1cb6fdd5eac046575f938af', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-141::sofis::(Checksum: 7:c59d6ae38bf046e2239e4c17dbf710b1)
ALTER TABLE proy_pre ADD CONSTRAINT proy_pre_pkey PRIMARY KEY (proypre_proy_fk);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-141', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 141, '7:c59d6ae38bf046e2239e4c17dbf710b1', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-142::sofis::(Checksum: 7:23fcb59fa5c6bbf5bd1a662761f150c6)
ALTER TABLE proy_docs ADD CONSTRAINT proy_docs_pkey PRIMARY KEY (proydoc_proy_pk, proydoc_doc_pk);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-142', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 142, '7:23fcb59fa5c6bbf5bd1a662761f150c6', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-143::sofis::(Checksum: 7:d4005f9d50daa7c40dfb8911668eceaa)
ALTER TABLE aud_ss_paridades ADD CONSTRAINT aud_ss_paridades_pkey PRIMARY KEY (par_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-143', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 143, '7:d4005f9d50daa7c40dfb8911668eceaa', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-144::sofis::(Checksum: 7:67ee0bd4e9d3d47853241f91e021e3c1)
ALTER TABLE aud_ss_departamentos ADD CONSTRAINT aud_ss_departamentos_pkey PRIMARY KEY (depto_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-144', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 144, '7:67ee0bd4e9d3d47853241f91e021e3c1', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-145::sofis::(Checksum: 7:0617b7d0e85a9220b709b595094b4db7)
ALTER TABLE aud_ss_paises ADD CONSTRAINT aud_ss_paises_pkey PRIMARY KEY (pai_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-145', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 145, '7:0617b7d0e85a9220b709b595094b4db7', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-146::sofis::(Checksum: 7:4553e11421479c125d3239df9e734316)
ALTER TABLE aud_ss_operacion ADD CONSTRAINT aud_ss_operacion_pkey PRIMARY KEY (ope_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-146', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 146, '7:4553e11421479c125d3239df9e734316', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-147::sofis::(Checksum: 7:f180511d9a271ab29cd516c4637441ab)
ALTER TABLE prog_tags ADD CONSTRAINT prog_tags_pkey PRIMARY KEY (progtag_prog_pk, progtag_area_pk);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-147', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 147, '7:f180511d9a271ab29cd516c4637441ab', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-148::sofis::(Checksum: 7:50dde42d87af8a31ba11bf65fe76509e)
ALTER TABLE aud_pge_configuraciones ADD CONSTRAINT aud_pge_configuraciones_pkey PRIMARY KEY (cnf_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-148', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 148, '7:50dde42d87af8a31ba11bf65fe76509e', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-149::sofis::(Checksum: 7:5362a35a910ff71c2a92d4b2d0b31a41)
ALTER TABLE aud_ss_domicilios ADD CONSTRAINT aud_ss_domicilios_pkey PRIMARY KEY (dom_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-149', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 149, '7:5362a35a910ff71c2a92d4b2d0b31a41', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-150::sofis::(Checksum: 7:e7086b1e551dca2d361089e69376f69f)
ALTER TABLE aud_ss_usu_ofi_roles ADD CONSTRAINT aud_ss_usu_ofi_roles_pkey PRIMARY KEY (usu_ofi_roles_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-150', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 150, '7:e7086b1e551dca2d361089e69376f69f', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-151::sofis::(Checksum: 7:2f24132d714bfb17b6413f728f8baf01)
ALTER TABLE roles_usuarios ADD CONSTRAINT roles_usuarios_pkey PRIMARY KEY (rol_pk);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-151', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 151, '7:2f24132d714bfb17b6413f728f8baf01', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-152::sofis::(Checksum: 7:49d32bb09dba2ddeeaff1c8fc4e73d8c)
ALTER TABLE aud_ss_categoper ADD CONSTRAINT aud_ss_categoper_pkey PRIMARY KEY (cat_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-152', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 152, '7:49d32bb09dba2ddeeaff1c8fc4e73d8c', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-153::sofis::(Checksum: 7:152628d83a59073229688906a223d89f)
ALTER TABLE aud_ss_usuario ADD CONSTRAINT aud_ss_usuario_pkey PRIMARY KEY (usu_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-153', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 153, '7:152628d83a59073229688906a223d89f', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-154::sofis::(Checksum: 7:ad80b60810e9ffc0fc492b071f8b27ee)
ALTER TABLE aud_ss_tipos_vialidad ADD CONSTRAINT aud_ss_tipos_vialidad_pkey PRIMARY KEY (tvi_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-154', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 154, '7:ad80b60810e9ffc0fc492b071f8b27ee', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-155::sofis::(Checksum: 7:d4ab13fafb6151d57ba1aa0a1addaace)
ALTER TABLE aud_doc_file ADD CONSTRAINT aud_doc_file_pkey PRIMARY KEY (docfile_pk, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-155', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 155, '7:d4ab13fafb6151d57ba1aa0a1addaace', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-156::sofis::(Checksum: 7:3d29c32d4205b72e8b0957c102ee8d23)
ALTER TABLE aud_ss_rol ADD CONSTRAINT aud_ss_rol_pkey PRIMARY KEY (rol_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-156', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 156, '7:3d29c32d4205b72e8b0957c102ee8d23', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-157::sofis::(Checksum: 7:e87af281d60e20b2361cf6ffa13d318c)
ALTER TABLE lecapr_areacon ADD CONSTRAINT lecapr_areacon_pkey PRIMARY KEY (lecaprcon_con_fk, lecaprcon_lecapr_fk);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-157', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 157, '7:e87af281d60e20b2361cf6ffa13d318c', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-158::sofis::(Checksum: 7:1546e79b0d832ca0daad6579703aa2f0)
ALTER TABLE aud_ss_rel_rol_operacion ADD CONSTRAINT aud_ss_rel_rol_operacion_pkey PRIMARY KEY (rel_rol_operacion_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-158', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 158, '7:1546e79b0d832ca0daad6579703aa2f0', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-159::sofis::(Checksum: 7:580ea6e488d53244450f81b80ce3173f)
ALTER TABLE prog_docs_obl ADD CONSTRAINT prog_docs_obl_pkey PRIMARY KEY (progdocsobl_docs_pk, progdocsobl_prog_pk);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-159', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 159, '7:580ea6e488d53244450f81b80ce3173f', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-160::sofis::(Checksum: 7:5824c42700bf297d9bf63e63416f3adb)
ALTER TABLE aud_programas ADD CONSTRAINT aud_programas_pkey PRIMARY KEY (prog_pk, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-160', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 160, '7:5824c42700bf297d9bf63e63416f3adb', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-161::sofis::(Checksum: 7:84d2286c5884c1dd9e48b5950beb413f)
ALTER TABLE aud_ss_localidades ADD CONSTRAINT aud_ss_localidades_pkey PRIMARY KEY (loc_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-161', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 161, '7:84d2286c5884c1dd9e48b5950beb413f', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-162::sofis::(Checksum: 7:bccff6a44bf10100271400ad05fb4f74)
ALTER TABLE prog_int ADD CONSTRAINT prog_int_pkey PRIMARY KEY (progint_prog_pk, progint_int_pk);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-162', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 162, '7:bccff6a44bf10100271400ad05fb4f74', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-163::sofis::(Checksum: 7:9a30a5c09570e91ed84bbf12e515fde6)
ALTER TABLE proy_lectura_area ADD CONSTRAINT proy_lectura_area_pkey PRIMARY KEY (proglectarea_area_pk, proglectarea_proy_pk);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-163', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 163, '7:9a30a5c09570e91ed84bbf12e515fde6', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-164::sofis::(Checksum: 7:ef4fc3f510443676b6720ab0df368057)
ALTER TABLE prog_pre ADD CONSTRAINT prog_pre_pkey PRIMARY KEY (progpre_prog_fk);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-164', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 164, '7:ef4fc3f510443676b6720ab0df368057', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-165::sofis::(Checksum: 7:1d746eca55782fdde34c817c3ddf4a8b)
ALTER TABLE aud_ss_configuraciones ADD CONSTRAINT aud_ss_configuraciones_pkey PRIMARY KEY (id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-165', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 165, '7:1d746eca55782fdde34c817c3ddf4a8b', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-166::sofis::(Checksum: 7:0fca7b6444fe28f60af4eba5ddd4bf8a)
ALTER TABLE proy_tags ADD CONSTRAINT proy_tags_pkey PRIMARY KEY (proytag_proy_pk, proytag_area_pk);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-166', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 166, '7:0fca7b6444fe28f60af4eba5ddd4bf8a', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-167::sofis::(Checksum: 7:1bbe0e0c2da19131c013b3567e98528e)
ALTER TABLE ss_paridades ADD CONSTRAINT ss_paridades_pkey PRIMARY KEY (par_id);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-167', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 167, '7:1bbe0e0c2da19131c013b3567e98528e', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-168::sofis::(Checksum: 7:9936c98e7bbafe943c11675fa26edc45)
ALTER TABLE departamentos ADD CONSTRAINT departamentos_pkey PRIMARY KEY (dep_pk);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-168', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 168, '7:9936c98e7bbafe943c11675fa26edc45', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-169::sofis::(Checksum: 7:7584621fba585c1ca46275aeb9d63c49)
ALTER TABLE aud_ss_oficina ADD CONSTRAINT aud_ss_oficina_pkey PRIMARY KEY (ofi_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-169', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 169, '7:7584621fba585c1ca46275aeb9d63c49', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-170::sofis::(Checksum: 7:616dd8efd7ed8386fffe07558b22ead3)
ALTER TABLE prog_docs ADD CONSTRAINT prog_docs_pkey PRIMARY KEY (progdocs_prog_pk, progdocs_doc_pk);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-170', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 170, '7:616dd8efd7ed8386fffe07558b22ead3', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-171::sofis::(Checksum: 7:c1ee53cd8ea83c1746ee97f32cea3cbe)
ALTER TABLE ss_errores ADD CONSTRAINT ss_errores_pkey PRIMARY KEY (id);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-171', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 171, '7:c1ee53cd8ea83c1746ee97f32cea3cbe', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-172::sofis::(Checksum: 7:c330ed37b4a4f4d9b7c0804c53e08293)
ALTER TABLE proy_int ADD CONSTRAINT proy_int_pkey PRIMARY KEY (proyint_proy_pk, proyint_int_pk);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-172', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 172, '7:c330ed37b4a4f4d9b7c0804c53e08293', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-173::sofis::(Checksum: 7:dab85c48466b5614a99b6e866a7dd18f)
ALTER TABLE aud_ss_errores ADD CONSTRAINT aud_ss_errores_pkey PRIMARY KEY (id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-173', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 173, '7:dab85c48466b5614a99b6e866a7dd18f', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-174::sofis::(Checksum: 7:2d832d6f036db4403829ec01ab767268)
ALTER TABLE aud_ss_telefonos ADD CONSTRAINT aud_ss_telefonos_pkey PRIMARY KEY (tel_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-174', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 174, '7:2d832d6f036db4403829ec01ab767268', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-175::sofis::(Checksum: 7:01214e13f82bb938a6fcbbc27662886c)
ALTER TABLE aud_ss_tipos_documento_persona ADD CONSTRAINT aud_ss_tipos_documento_persona_pkey PRIMARY KEY (tipdocper_id, rev);

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-175', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 175, '7:01214e13f82bb938a6fcbbc27662886c', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-176::sofis::(Checksum: 7:6e09c9d9a82c9192ab147b03250100fb)
ALTER TABLE proy_lectura_area ADD CONSTRAINT proy_lectura_area_proglectarea_area_pk_fkey FOREIGN KEY (proglectarea_area_pk) REFERENCES areas (area_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-176', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 176, '7:6e09c9d9a82c9192ab147b03250100fb', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-177::sofis::(Checksum: 7:f579a7ffedb39867f0e57227766b1ac1)
ALTER TABLE proy_tags ADD CONSTRAINT proy_tags_proytag_area_pk_fkey FOREIGN KEY (proytag_area_pk) REFERENCES areas_tags (arastag_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-177', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 177, '7:f579a7ffedb39867f0e57227766b1ac1', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-178::sofis::(Checksum: 7:2307a79e46191939a7df5082bf8c2bde)
ALTER TABLE proy_otros_datos ADD CONSTRAINT proy_otros_datos_proy_otr_cat_fk_fkey FOREIGN KEY (proy_otr_cat_fk) REFERENCES categoria_proyectos (cat_proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-178', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 178, '7:2307a79e46191939a7df5082bf8c2bde', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-179::sofis::(Checksum: 7:47a274109d8196e0bdb1950725f9b0f9)
ALTER TABLE prog_int ADD CONSTRAINT prog_int_progint_int_pk_fkey FOREIGN KEY (progint_int_pk) REFERENCES interesados (int_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-179', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 179, '7:47a274109d8196e0bdb1950725f9b0f9', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-180::sofis::(Checksum: 7:6f9ea12aa7d83fa17f8fb5fb30a15fdd)
ALTER TABLE categoria_proyectos ADD CONSTRAINT fk_hb3weyc8xvdrbp62k3u1halnb FOREIGN KEY (cat_icono_marker) REFERENCES image (image_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-180', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 180, '7:6f9ea12aa7d83fa17f8fb5fb30a15fdd', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-181::sofis::(Checksum: 7:d876fc41d336eb142efdafbd62f9095e)
ALTER TABLE prog_indices_pre ADD CONSTRAINT prog_indices_pre_progindpre_progind_fk_fkey FOREIGN KEY (progindpre_progind_fk) REFERENCES prog_indices (progind_pk) ON UPDATE RESTRICT ON DELETE RESTRICT;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-181', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 181, '7:d876fc41d336eb142efdafbd62f9095e', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-182::sofis::(Checksum: 7:552d7607e2cac2204e17d038112900d7)
ALTER TABLE prog_docs_obl ADD CONSTRAINT prog_docs_obl_progdocsobl_docs_pk_fkey FOREIGN KEY (progdocsobl_docs_pk) REFERENCES documentos (docs_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-182', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 182, '7:552d7607e2cac2204e17d038112900d7', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-183::sofis::(Checksum: 7:004f8e2419ead750142d9d424ff48c9b)
ALTER TABLE lecapr_areacon ADD CONSTRAINT lecapr_areacon_lecaprcon_con_fk_fkey FOREIGN KEY (lecaprcon_con_fk) REFERENCES area_conocimiento (con_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-183', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 183, '7:004f8e2419ead750142d9d424ff48c9b', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-184::sofis::(Checksum: 7:2bed369d7cfdb253b796f547019a39d6)
ALTER TABLE areas_tags ADD CONSTRAINT areas_tags_areatag_padre_fk_fkey FOREIGN KEY (areatag_padre_fk) REFERENCES areas_tags (arastag_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-184', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 184, '7:2bed369d7cfdb253b796f547019a39d6', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-185::sofis::(Checksum: 7:365e5b2a7f93cdd6d5f05165f0fdfc47)
ALTER TABLE notificacion_instancia ADD CONSTRAINT notificacion_instancia_notinst_not_fk_fkey FOREIGN KEY (notinst_not_fk) REFERENCES notificacion (not_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-185', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 185, '7:365e5b2a7f93cdd6d5f05165f0fdfc47', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-186::sofis::(Checksum: 7:233efc716cb7283f77ca66fe2bb150b1)
ALTER TABLE proyectos ADD CONSTRAINT proyectos_proy_area_fk_fkey FOREIGN KEY (proy_area_fk) REFERENCES areas (area_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-186', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 186, '7:233efc716cb7283f77ca66fe2bb150b1', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-187::sofis::(Checksum: 7:d0a3164ab1c2116fc22d41114a8270f2)
ALTER TABLE proy_indices ADD CONSTRAINT proyind_periodo_fin_ent_fk FOREIGN KEY (proyind_periodo_fin_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-187', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 187, '7:d0a3164ab1c2116fc22d41114a8270f2', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-188::sofis::(Checksum: 7:2bf7f1cf2fd39f0c139c0b4f8004a98d)
ALTER TABLE proy_sigesa_hist ADD CONSTRAINT proy_sigesa_proy_fk FOREIGN KEY (proy_sigesa_proy_fk) REFERENCES proyectos (proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-188', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 188, '7:2bf7f1cf2fd39f0c139c0b4f8004a98d', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-189::sofis::(Checksum: 7:0bf2e894f6fb21e843cfce3af8d59521)
ALTER TABLE ss_operacion ADD CONSTRAINT ss_operacion_ope_categoria_id_fkey FOREIGN KEY (ope_categoria_id) REFERENCES ss_categoper (cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-189', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 189, '7:0bf2e894f6fb21e843cfce3af8d59521', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-190::sofis::(Checksum: 7:619fd6ccab003ea1c5eb0c3d9195d5d9)
ALTER TABLE proy_int ADD CONSTRAINT proy_int_proyint_int_pk_fkey FOREIGN KEY (proyint_int_pk) REFERENCES interesados (int_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-190', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 190, '7:619fd6ccab003ea1c5eb0c3d9195d5d9', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-191::sofis::(Checksum: 7:9451e271b14ef27490f6dc0e64202f7b)
ALTER TABLE prog_lectura_area ADD CONSTRAINT prog_lectura_area_proglectarea_area_pk_fkey FOREIGN KEY (proglectarea_area_pk) REFERENCES areas (area_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-191', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 191, '7:9451e271b14ef27490f6dc0e64202f7b', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-192::sofis::(Checksum: 7:083cc2c3447581ffbee9869a6eb2692d)
ALTER TABLE tipo_documento_instancia ADD CONSTRAINT tipo_documento_instancia_tipodoc_inst_tipodoc_fk_fkey FOREIGN KEY (tipodoc_inst_tipodoc_fk) REFERENCES tipo_documento (tipdoc_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-192', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 192, '7:083cc2c3447581ffbee9869a6eb2692d', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-193::sofis::(Checksum: 7:1bb97dae7b30e7f31a40d8fae6f76bd6)
ALTER TABLE tipo_documento ADD CONSTRAINT tipo_documento_tipodoc_org_fk_fkey FOREIGN KEY (tipodoc_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-193', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 193, '7:1bb97dae7b30e7f31a40d8fae6f76bd6', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-194::sofis::(Checksum: 7:199b0c504ce91872af37950c329e4514)
ALTER TABLE presupuesto ADD CONSTRAINT presupuesto_pre_fuente_organi_fk_fkey FOREIGN KEY (pre_fuente_organi_fk) REFERENCES fuente_financiamiento (fue_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-194', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 194, '7:199b0c504ce91872af37950c329e4514', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-195::sofis::(Checksum: 7:80dee1e61f7acc5e54007db980063474)
ALTER TABLE lineabase_historico ADD CONSTRAINT "lineabase_historico_lineabase_entFk_fkey" FOREIGN KEY ("lineabase_entFk") REFERENCES entregables (ent_pk) ON UPDATE RESTRICT ON DELETE RESTRICT;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-195', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 195, '7:80dee1e61f7acc5e54007db980063474', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-196::sofis::(Checksum: 7:bcc1f0b5cf2e8770ebc54db35d5cf7e7)
ALTER TABLE proy_pre ADD CONSTRAINT proy_pre_proypre_pre_fk_fkey FOREIGN KEY (proypre_pre_fk) REFERENCES presupuesto (pre_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-196', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 196, '7:bcc1f0b5cf2e8770ebc54db35d5cf7e7', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-197::sofis::(Checksum: 7:21ed4134db03688d09b053057d298a9d)
ALTER TABLE prog_tags ADD CONSTRAINT prog_tags_progtag_area_pk_fkey FOREIGN KEY (progtag_area_pk) REFERENCES areas_tags (arastag_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-197', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 197, '7:21ed4134db03688d09b053057d298a9d', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-198::sofis::(Checksum: 7:0be674d7715bf082d204b8424adcf3e0)
ALTER TABLE ss_rel_rol_operacion ADD CONSTRAINT ss_rel_rol_operacion_rel_rol_operacion_operacion_id_fkey FOREIGN KEY (rel_rol_operacion_operacion_id) REFERENCES ss_operacion (ope_id) ON UPDATE RESTRICT ON DELETE RESTRICT;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-198', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 198, '7:0be674d7715bf082d204b8424adcf3e0', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-199::sofis::(Checksum: 7:51b26d5c840825095acc0c72b6bc003d)
ALTER TABLE objetivos_estrategicos ADD CONSTRAINT obj_est_org_fk FOREIGN KEY (obj_est_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-199', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 199, '7:51b26d5c840825095acc0c72b6bc003d', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-200::sofis::(Checksum: 7:03d0b0538e0c18926391fcc8c403970d)
ALTER TABLE ss_localidades ADD CONSTRAINT ss_localidades_loc_depto_id_fkey FOREIGN KEY (loc_depto_id) REFERENCES ss_departamentos (depto_id) ON UPDATE RESTRICT ON DELETE RESTRICT;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-200', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 200, '7:03d0b0538e0c18926391fcc8c403970d', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-201::sofis::(Checksum: 7:239c4272c328f90d5c0a9fef7bfce4a2)
ALTER TABLE organi_int_prove ADD CONSTRAINT organi_int_prove_orga_amb_fk_fkey FOREIGN KEY (orga_amb_fk) REFERENCES ambito (amb_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-201', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 201, '7:239c4272c328f90d5c0a9fef7bfce4a2', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-202::sofis::(Checksum: 7:730ed10e664adb821243bfdaadbbc4ea)
ALTER TABLE roles_interesados ADD CONSTRAINT roles_interesados_rolint_org_fk_fkey FOREIGN KEY (rolint_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-202', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 202, '7:730ed10e664adb821243bfdaadbbc4ea', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-203::sofis::(Checksum: 7:9a49eba2ff2f89e55025e6a205952104)
ALTER TABLE areas ADD CONSTRAINT fk_j151q3d1wiqgx10w9n92hwx5j FOREIGN KEY (area_director) REFERENCES ss_usuario (usu_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-203', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 203, '7:9a49eba2ff2f89e55025e6a205952104', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-204::sofis::(Checksum: 7:b56bb8137fe74a337a9efc6fcd575c0f)
ALTER TABLE plantilla_entregables ADD CONSTRAINT plantilla_entregables_p_entregable_p_cro_fk_fkey FOREIGN KEY (p_entregable_p_cro_fk) REFERENCES plantilla_cronograma (p_crono_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-204', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 204, '7:b56bb8137fe74a337a9efc6fcd575c0f', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-205::sofis::(Checksum: 7:d0e123b3c9406f13f337420fcb6e89af)
ALTER TABLE ss_usuario ADD CONSTRAINT ss_usuario_usu_area_org_fkey FOREIGN KEY (usu_area_org) REFERENCES areas (area_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-205', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 205, '7:d0e123b3c9406f13f337420fcb6e89af', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-206::sofis::(Checksum: 7:b014856c181657d77d83b7b6b953bca9)
ALTER TABLE plantilla_cronograma ADD CONSTRAINT plantilla_cronograma_p_crono_org_fk_fkey FOREIGN KEY (p_crono_org_fk) REFERENCES organismos (org_pk) ON UPDATE RESTRICT ON DELETE RESTRICT;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-206', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 206, '7:b014856c181657d77d83b7b6b953bca9', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-207::sofis::(Checksum: 7:9caf57c3c6ccd742c9f49e200bb50943)
ALTER TABLE productos ADD CONSTRAINT productos_prod_ent_fk_fkey FOREIGN KEY (prod_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-207', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 207, '7:9caf57c3c6ccd742c9f49e200bb50943', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-208::sofis::(Checksum: 7:96f320a075144f92b503915bd72cfcc0)
ALTER TABLE riesgos ADD CONSTRAINT riesgos_risk_ent_fk_fkey FOREIGN KEY (risk_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-208', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 208, '7:96f320a075144f92b503915bd72cfcc0', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-209::sofis::(Checksum: 7:94f44fc8b51d6fbfaf2485cb3576b0be)
ALTER TABLE registros_horas ADD CONSTRAINT registros_horas_rh_ent_fk_fkey FOREIGN KEY (rh_ent_fk) REFERENCES entregables (ent_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-209', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 209, '7:94f44fc8b51d6fbfaf2485cb3576b0be', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-210::sofis::(Checksum: 7:c4afdf3273e044fc1e17ff4c8bfb7a2b)
ALTER TABLE proy_otros_cat_secundarias ADD CONSTRAINT proy_otros_cat_secundarias_proy_cat_cat_proy_fk_fkey FOREIGN KEY (proy_cat_cat_proy_fk) REFERENCES categoria_proyectos (cat_proy_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-210', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 210, '7:c4afdf3273e044fc1e17ff4c8bfb7a2b', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-211::sofis::(Checksum: 7:a4e382fddcbfea9ff6eb278b6b4f2874)
ALTER TABLE tipo_gasto ADD CONSTRAINT tipo_gasto_tipogas_org_fk_fkey FOREIGN KEY (tipogas_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-211', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 211, '7:a4e382fddcbfea9ff6eb278b6b4f2874', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-212::sofis::(Checksum: 7:882682e652a222d816b3c3581021a87b)
ALTER TABLE adquisicion ADD CONSTRAINT adquisicion_componente_producto_fk FOREIGN KEY (adq_componente_producto_fk) REFERENCES componente_producto (com_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-212', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 212, '7:882682e652a222d816b3c3581021a87b', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-213::sofis::(Checksum: 7:824a0d4837260f6d0d4f94baa4ca9281)
ALTER TABLE latlng_proyectos ADD CONSTRAINT latlng_proyectos_latlang_dep_fk_fkey FOREIGN KEY (latlang_dep_fk) REFERENCES departamentos (dep_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-213', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 213, '7:824a0d4837260f6d0d4f94baa4ca9281', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-214::sofis::(Checksum: 7:0b2cf89d29ceb25a03b5958980a7fd5a)
ALTER TABLE devengado ADD CONSTRAINT devengado_dev_adq_fk_fkey FOREIGN KEY (dev_adq_fk) REFERENCES adquisicion (adq_pk) ON UPDATE RESTRICT ON DELETE RESTRICT;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-214', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 214, '7:0b2cf89d29ceb25a03b5958980a7fd5a', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-215::sofis::(Checksum: 7:9de1639563a1205d87b5213fba9af9d6)
ALTER TABLE programas ADD CONSTRAINT programas_prog_area_fk_fkey FOREIGN KEY (prog_area_fk) REFERENCES areas (area_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-215', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 215, '7:9de1639563a1205d87b5213fba9af9d6', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-216::sofis::(Checksum: 7:bebad5925e771e31830a2b013c129081)
ALTER TABLE ambito ADD CONSTRAINT fk_n5tl58dqv18cs790jbmejxom2 FOREIGN KEY (amb_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-216', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 216, '7:bebad5925e771e31830a2b013c129081', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-217::sofis::(Checksum: 7:3ff9d3645e9ae2519ce3f2109e7176fd)
ALTER TABLE valor_hora ADD CONSTRAINT valor_hora_val_hor_mon_fk_fkey FOREIGN KEY (val_hor_mon_fk) REFERENCES moneda (mon_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-217', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 217, '7:3ff9d3645e9ae2519ce3f2109e7176fd', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-218::sofis::(Checksum: 7:48fcc3eeffaa6e71ba9db790623b7633)
ALTER TABLE doc_file ADD CONSTRAINT fk_n76rhuste8gi3p3jq7m91j7iq FOREIGN KEY (docfile_doc_fk) REFERENCES documentos (docs_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-218', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 218, '7:48fcc3eeffaa6e71ba9db790623b7633', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-219::sofis::(Checksum: 7:ac466153db5aa7334fd27421bd55356b)
ALTER TABLE busq_filtro ADD CONSTRAINT fk_a726jrgroi6p90dip38n70ftp FOREIGN KEY (busq_filtro_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-219', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 219, '7:ac466153db5aa7334fd27421bd55356b', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-220::sofis::(Checksum: 7:4a8071c7620f21a342fe7c2cb99dcfd1)
ALTER TABLE pagos ADD CONSTRAINT pagos_pag_adq_fk_fkey FOREIGN KEY (pag_adq_fk) REFERENCES adquisicion (adq_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-220', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 220, '7:4a8071c7620f21a342fe7c2cb99dcfd1', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-221::sofis::(Checksum: 7:212a705e99f1da4097cdd6a4803c03f2)
ALTER TABLE personas ADD CONSTRAINT personas_pers_orga_fk_fkey FOREIGN KEY (pers_orga_fk) REFERENCES organi_int_prove (orga_pk) ON UPDATE RESTRICT ON DELETE RESTRICT;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-221', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 221, '7:212a705e99f1da4097cdd6a4803c03f2', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-222::sofis::(Checksum: 7:4eb13726252a29ecd0cbba8a91aa6b45)
ALTER TABLE temas_calidad ADD CONSTRAINT temas_calidad_tca_org_fk_fkey FOREIGN KEY (tca_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-222', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 222, '7:4eb13726252a29ecd0cbba8a91aa6b45', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-223::sofis::(Checksum: 7:2f642c865bbb433cb770f45e730d5007)
CREATE VIEW ss_oficina AS SELECT organismos.org_pk AS ofi_id,
    organismos.org_nombre AS ofi_nombre,
    organismos.org_activo AS ofi_activo,
    now() AS ofi_fecha_creacion,
    '' AS ofi_origen,
    1 AS ofi_usuario,
    1 AS ofi_version
   FROM siges.organismos;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-223', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 223, '7:2f642c865bbb433cb770f45e730d5007', 'Create a new database view', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-224::sofis::(Checksum: 7:07dfc04ab5f50cd7d31f2b5124d68513)
CREATE VIEW programas_proyectos AS SELECT concat('1-', programas.prog_pk) AS id,
    programas.prog_pk AS fichafk,
    1 AS tipoficha,
    programas.prog_fecha_crea AS fechacrea,
    programas.prog_activo AS activo,
    programas.prog_org_fk AS organismo,
    programas.prog_nombre AS nombre,
    estados.est_pk AS estado,
    estados.est_nombre AS estadonombre,
    programas.prog_est_pendiente_fk AS estadopendiente,
    areas.area_pk AS areapk,
    areas.area_nombre AS areanombre,
    programas.prog_sol_aceptacion AS solaceptacion,
    programas.prog_usr_gerente_fk AS gerente,
    ss_usuario.usu_primer_apellido AS gerenteprimerapellido,
    ss_usuario.usu_primer_nombre AS gerenteprimernombre,
    programas.prog_usr_pmofed_fk AS pmofederada
   FROM (((siges.programas
     JOIN siges.estados ON ((programas.prog_est_fk = estados.est_pk)))
     JOIN siges.areas ON ((programas.prog_area_fk = areas.area_pk)))
     JOIN siges.ss_usuario ON ((programas.prog_usr_gerente_fk = ss_usuario.usu_id)))
UNION
 SELECT concat('2-', proyectos.proy_pk) AS id,
    proyectos.proy_pk AS fichafk,
    2 AS tipoficha,
    proyectos.proy_fecha_crea AS fechacrea,
    proyectos.proy_activo AS activo,
    proyectos.proy_org_fk AS organismo,
    proyectos.proy_nombre AS nombre,
    estados.est_pk AS estado,
    estados.est_nombre AS estadonombre,
    proyectos.proy_est_pendiente_fk AS estadopendiente,
    areas.area_pk AS areapk,
    areas.area_nombre AS areanombre,
    proyectos.proy_sol_aceptacion AS solaceptacion,
    proyectos.proy_usr_gerente_fk AS gerente,
    ss_usuario.usu_primer_apellido AS gerenteprimerapellido,
    ss_usuario.usu_primer_nombre AS gerenteprimernombre,
    proyectos.proy_usr_pmofed_fk AS pmofederada
   FROM (((siges.proyectos
     JOIN siges.estados ON ((proyectos.proy_est_fk = estados.est_pk)))
     JOIN siges.areas ON ((proyectos.proy_area_fk = areas.area_pk)))
     JOIN siges.ss_usuario ON ((proyectos.proy_usr_gerente_fk = ss_usuario.usu_id)));

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-224', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 224, '7:07dfc04ab5f50cd7d31f2b5124d68513', 'Create a new database view', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-225::sofis::(Checksum: 7:a77f7ced318c901a89b8730c067136ce)
CREATE SEQUENCE componente_producto_com_pk_seq;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-225', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 225, '7:a77f7ced318c901a89b8730c067136ce', 'Creates a new database sequence', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-226::sofis::(Checksum: 7:4d590c2f9452e49634d0c634e7cdb401)
CREATE SEQUENCE ss_paridades_par_id_seq;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-226', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 226, '7:4d590c2f9452e49634d0c634e7cdb401', 'Creates a new database sequence', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-227::sofis::(Checksum: 7:212167d2d53c02a46c0ebebbd79ef075)
CREATE SEQUENCE obj_est_pk_seq;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-227', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 227, '7:212167d2d53c02a46c0ebebbd79ef075', 'Creates a new database sequence', '', 'EXECUTED', '3.0.0-SNP');

-- Changeset db.changelog_siges_imm_5.2.xml::1527600056478-228::sofis::(Checksum: 7:afe897bea20bc08d1681087c94d0967d)
CREATE SEQUENCE hibernate_sequence;

INSERT INTO siges.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1527600056478-228', 'sofis', 'db.changelog_siges_imm_5.2.xml', NOW(), 228, '7:afe897bea20bc08d1681087c94d0967d', 'Creates a new database sequence', '', 'EXECUTED', '3.0.0-SNP');

