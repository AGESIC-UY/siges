-- Desde acá en adelante es v3.2-0 (pasó a ser la 4.0)

CREATE TABLE siges.proy_publica_hist (
  proy_publica_pk serial NOT NULL,
  proy_publica_fecha timestamp NOT NULL,
  proy_publica_proy_fk integer NOT NULL,
  proy_publica_usu_fk integer NOT NULL,
  PRIMARY KEY (proy_publica_pk),
  CONSTRAINT proy_publica_proy_fk
    FOREIGN KEY (proy_publica_proy_fk)
    REFERENCES siges.proyectos (proy_pk)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT proy_publica_usu_fk
    FOREIGN KEY (proy_publica_usu_fk)
    REFERENCES siges.ss_usuario (usu_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX proy_publica_proy_fk_idx
  ON siges.proy_publica_hist
  USING btree
  (proy_publica_proy_fk);

  CREATE INDEX proy_publica_usu_fk_idx
  ON siges.proy_publica_hist
  USING btree
  (proy_publica_usu_fk);

ALTER TABLE siges.organismos 
ADD COLUMN org_token character varying(100) NULL;

CREATE INDEX org_token_idx
  ON siges.organismos
  USING btree
  (org_token);

-- ALTER TABLE siges.ss_usuario CONVERT TO CHARACTER SET utf8 COLLATE utf8_spanish_ci;

alter table siges.proy_indices_pre add proyindpre_ac double precision;
alter table siges.proy_indices_pre add proyindpre_pv double precision;

-- Desde acá en adelante es v4.1-0

ALTER TABLE siges.entregables 
ADD COLUMN ent_parent boolean NULL DEFAULT NULL;

CREATE INDEX ent_parent_idx
  ON siges.entregables 
  USING btree
  (ent_parent);

-- Desde acá en adelante es v4.2-0

ALTER TABLE siges.ss_usuario 
ADD COLUMN usu_token character varying(100) NULL DEFAULT NULL,
ADD COLUMN usu_token_act timestamp without time zone NULL DEFAULT NULL;

CREATE INDEX usu_token_idx
  ON siges.ss_usuario 
  USING btree
  (usu_token);

ALTER TABLE siges.aud_ss_usuario 
ADD COLUMN usu_token character varying(100) NULL DEFAULT NULL,
ADD COLUMN usu_token_act timestamp without time zone NULL DEFAULT NULL;

CREATE INDEX usu_token_idx
  ON siges.aud_ss_usuario 
  USING btree
  (usu_token);

-- Se realiza para quitar la ruta al archivo ya que se saca de la configuración.
UPDATE siges.media_proyectos SET media_link = REPLACE(media_link, '/srv/siges/jboss-as-7.1.1.Final/media_files/', '') where media_pk>0;
UPDATE siges.media_proyectos SET media_link = REPLACE(media_link, '/siges/media_files/', '') where media_pk>0;

-- Desde acá en adelante es v4.2-1

ALTER TABLE siges.presupuesto 
ALTER COLUMN pre_base TYPE DECIMAL(15,2);

-- Desde acá en adelante es v4.2-3

ALTER TABLE siges.ss_configuraciones 
ALTER COLUMN cnf_valor TYPE character varying(255) ;

-- ALTER TABLE siges.ss_usuario ALTER COLUMN usu_cuenta_bloqueada TYPE smallint USING CASE WHEN usu_cuenta_bloqueada THEN 1 ELSE 0 END;

-- Desde acá en adelante es v4.4-0
ALTER TABLE siges.proy_indices_pre 
ADD COLUMN proyindpre_anio double precision;

-- Desde acá en adelante es v4.4-1
CREATE TABLE siges.image (
  image_pk serial NOT NULL,
  image_name character varying(45) NOT NULL,
  image_desc character varying(255) DEFAULT NULL,
  image_ext character varying(20) NOT NULL,
  image_blob bytea NOT NULL,
  image_tipo integer DEFAULT NULL,
  image_orden integer DEFAULT NULL,
  PRIMARY KEY (image_pk)
);

ALTER TABLE siges.categoria_proyectos 
ADD COLUMN cat_tipo integer NOT NULL DEFAULT 0,
ADD COLUMN cat_icono integer NULL DEFAULT NULL,
ADD COLUMN cat_icono_marker integer NULL DEFAULT NULL ;

UPDATE siges.categoria_proyectos SET cat_proy_codigo='TE_SALUD', cat_tipo='1' WHERE cat_proy_pk='1';
UPDATE siges.categoria_proyectos SET cat_proy_codigo='TE_EDUCATIVA', cat_tipo='1' WHERE cat_proy_pk='2';
UPDATE siges.categoria_proyectos SET cat_proy_codigo='TE_VIVIENDA', cat_tipo='1' WHERE cat_proy_pk='3';
UPDATE siges.categoria_proyectos SET cat_proy_codigo='TE_TERRESTRE', cat_tipo='1' WHERE cat_proy_pk='4';
UPDATE siges.categoria_proyectos SET cat_proy_codigo='TE_MARITIMO', cat_tipo='1' WHERE cat_proy_pk='5';
UPDATE siges.categoria_proyectos SET cat_proy_codigo='TE_AEREO', cat_tipo='1' WHERE cat_proy_pk='6';
UPDATE siges.categoria_proyectos SET cat_proy_codigo='TE_URBANA', cat_tipo='1' WHERE cat_proy_pk='7';
UPDATE siges.categoria_proyectos SET cat_proy_codigo='TE_SOCIAL', cat_tipo='1' WHERE cat_proy_pk='8';
UPDATE siges.categoria_proyectos SET cat_proy_codigo='TE_SEGURIDAD', cat_tipo='1' WHERE cat_proy_pk='9';
UPDATE siges.categoria_proyectos SET cat_proy_codigo='TE_ENERGETICA', cat_tipo='1' WHERE cat_proy_pk='10';
UPDATE siges.categoria_proyectos SET cat_proy_codigo='TE_TECNOLOGIA', cat_tipo='1' WHERE cat_proy_pk='11';
UPDATE siges.categoria_proyectos SET cat_proy_codigo='TE_SANIDAD', cat_tipo='1' WHERE cat_proy_pk='12';
UPDATE siges.categoria_proyectos SET cat_proy_codigo='TE_VARIOS', cat_tipo='1' WHERE cat_proy_pk='13';

INSERT INTO siges.categoria_proyectos (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_SALUD', 'Salud', '1', '2');
INSERT INTO siges.categoria_proyectos (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_EDUCACION', 'Educativa', '1', '2');
INSERT INTO siges.categoria_proyectos (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_VIVIENDA', 'Viviendas', '1', '2');
INSERT INTO siges.categoria_proyectos (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_TERRESTRE', 'Transporte Terrestre', '1', '2');
INSERT INTO siges.categoria_proyectos (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_URBANA', 'Urbana', '1', '2');
INSERT INTO siges.categoria_proyectos (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_SOCIAL', 'Social', '1', '2');
INSERT INTO siges.categoria_proyectos (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_SEGURIDAD', 'Seguridad', '1', '2');
INSERT INTO siges.categoria_proyectos (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_SANIDAD', 'Obras Sanitarias', '1', '2');
INSERT INTO siges.categoria_proyectos (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_VARIOS', 'Varios', '1', '2');
INSERT INTO siges.categoria_proyectos (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_MARITIMO', 'Transporte Maritimo', '1', '2');
INSERT INTO siges.categoria_proyectos (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_AEREO', 'Transporte Aereo', '1', '2');
INSERT INTO siges.categoria_proyectos (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_ENERGETICA', 'Energetica', '1', '2');
INSERT INTO siges.categoria_proyectos (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_TECNOLOGIA', 'Tecnologica', '1', '2');

UPDATE siges.proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='CA_SALUD') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='TE_SALUD');
UPDATE siges.proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='CA_EDUCACION') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='TE_VARIOS');
UPDATE siges.proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='CA_VIVIENDA') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='TE_VIVIENDA');
UPDATE siges.proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='CA_TERRESTRE') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='TE_TERRESTRE');
UPDATE siges.proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='CA_URBANA') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='TE_URBANA');
UPDATE siges.proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='CA_SOCIAL') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='TE_SOCIAL');
UPDATE siges.proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='CA_SEGURIDAD') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='TE_SEGURIDAD');
UPDATE siges.proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='CA_SANIDAD') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='TE_SANIDAD');
UPDATE siges.proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='CA_VARIOS') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='TE_VARIOS');
UPDATE siges.proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='CA_MARITIMO') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='TE_MARITIMO');
UPDATE siges.proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='CA_AEREO') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='TE_AEREO');
UPDATE siges.proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='CA_ENERGETICA') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='TE_ENERGETICA');
UPDATE siges.proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='CA_TECNOLOGIA') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from siges.categoria_proyectos where cat_proy_codigo='TE_TECNOLOGIA');

ALTER TABLE siges.categoria_proyectos 
ADD COLUMN cat_org_fk integer NULL DEFAULT NULL;

CREATE INDEX cat_org_fk_idx
  ON siges.categoria_proyectos 
  USING btree
  (cat_org_fk);

-- Desde acá en adelante es v4.4-2

ALTER TABLE siges.mails_template ALTER COLUMN mail_tmp_asunto character varying(200) NOT NULL ;

update siges.cronogramas set cro_permiso_escritura = 1 where cro_permiso_escritura=0 and cro_pk > 0;

ALTER TABLE siges.prog_indices_pre 
ADD COLUMN progindpre_anio double precision NULL DEFAULT NULL,
ADD COLUMN progindpre_ac double precision NULL DEFAULT NULL,
ADD COLUMN progindpre_pv double precision NULL DEFAULT NULL;

CREATE INDEX cat_tipo_idx
  ON siges.categoria_proyectos 
  USING btree
  (cat_tipo);

-- Desde acá en adelante es v4.4-3

ALTER TABLE siges.areas
ADD COLUMN area_activo boolean NULL DEFAULT NULL;

CREATE INDEX area_activo_idx
  ON siges.areas 
  USING btree
  (area_activo);

-- Desde acá en adelante es v4.4-6

ALTER TABLE proy_replanificacion ADD proyreplan_generar_linea_base BOOLEAN DEFAULT 0 NOT NULL;
ALTER TABLE aud_ss_configuraciones DROP FOREIGN KEY FK1E264BA5DF74E053;
ALTER TABLE aud_ss_tipos_entrada_colectiva DROP FOREIGN KEY FK2ABC90A6DF74E053;
ALTER TABLE aud_ss_usu_ofi_roles DROP FOREIGN KEY FK317B0718DF74E053;
ALTER TABLE aud_programas_proyectos DROP FOREIGN KEY FK36A8E0B6DF74E053;
ALTER TABLE aud_ss_noticias DROP FOREIGN KEY FK5037FBFEDF74E053;
ALTER TABLE aud_ss_rol DROP FOREIGN KEY FK533EE3DFDF74E053;
ALTER TABLE aud_ss_tipos_telefono DROP FOREIGN KEY FK5F493B64DF74E053;
ALTER TABLE aud_ss_paises DROP FOREIGN KEY FK5F6900B9DF74E053;
ALTER TABLE aud_ss_oficina DROP FOREIGN KEY FK6151DF1BDF74E053;
ALTER TABLE aud_ss_errores DROP FOREIGN KEY FK65521EC6DF74E053;
ALTER TABLE aud_pge_configuraciones DROP FOREIGN KEY FK69423495DF74E053;
ALTER TABLE aud_ss_telefonos DROP FOREIGN KEY FK73BE520FDF74E053;
ALTER TABLE aud_ss_ayuda DROP FOREIGN KEY FK7E2A928ADF74E053;
ALTER TABLE aud_ss_tipos_documento_persona DROP FOREIGN KEY FK82B382B1DF74E053;
ALTER TABLE aud_ss_categoper DROP FOREIGN KEY FKA2EE3756DF74E053;
ALTER TABLE aud_ss_operacion DROP FOREIGN KEY FKA8AC7928DF74E053;
ALTER TABLE aud_ss_usuario DROP FOREIGN KEY FKB58E953EDF74E053;
ALTER TABLE aud_ss_localidades DROP FOREIGN KEY FKBDD320E7DF74E053;
ALTER TABLE aud_ss_domicilios DROP FOREIGN KEY FKC027379EDF74E053;
ALTER TABLE aud_ss_personas DROP FOREIGN KEY FKC24C637DF74E053;
ALTER TABLE aud_ss_paridades DROP FOREIGN KEY FKC73DF59DDF74E053;
ALTER TABLE aud_ss_departamentos DROP FOREIGN KEY FKCA442AFFDF74E053;
ALTER TABLE aud_ss_rel_rol_operacion DROP FOREIGN KEY FKCE17192DF74E053;
ALTER TABLE aud_ss_tipos_vialidad DROP FOREIGN KEY FKF0430A0CDF74E053;

-- Desde acá en adelante es v4.4-7

CREATE TABLE objetivos_estrategicos (obj_est_pk INT AUTO_INCREMENT NOT NULL, obj_est_nombre VARCHAR(100) NULL, obj_est_descripcion VARCHAR(300) NULL, obj_est_org_fk INT NULL, CONSTRAINT PK_OBJETIVOS_ESTRATEGICOS PRIMARY KEY (obj_est_pk));
ALTER TABLE areas ADD area_habilitada TINYINT(3) DEFAULT 1 NOT NULL;
ALTER TABLE programas ADD prog_obj_est_fk INT NULL;
ALTER TABLE proyectos ADD proy_obj_est_fk INT NULL;
ALTER TABLE objetivos_estrategicos ADD CONSTRAINT obj_est_org_fk_nombre UNIQUE (obj_est_org_fk, obj_est_nombre);
CREATE INDEX obj_est_org_fk_idx ON objetivos_estrategicos(obj_est_org_fk);
CREATE INDEX prog_obj_est_fk_idx ON programas(prog_obj_est_fk);
CREATE INDEX proy_obj_est_fk_idx ON proyectos(proy_obj_est_fk);
ALTER TABLE objetivos_estrategicos ADD CONSTRAINT obj_est_org_fk FOREIGN KEY (obj_est_org_fk) REFERENCES organismos (org_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE programas ADD CONSTRAINT prog_obj_est_fk FOREIGN KEY (prog_obj_est_fk) REFERENCES objetivos_estrategicos (obj_est_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE proyectos ADD CONSTRAINT proy_obj_est_fk FOREIGN KEY (proy_obj_est_fk) REFERENCES objetivos_estrategicos (obj_est_pk) ON UPDATE NO ACTION ON DELETE NO ACTION;


-- Desde acá en adelante es v4.4-10

--  Changeset db.changelog_siges_4410.xml::1494002716492-2::bruno (generated)
CREATE TABLE aud_doc_file (docfile_pk INT NOT NULL, REV INT NOT NULL, REVTYPE SMALLINT NULL, docfile_nombre VARCHAR(255) NULL, docfile_path VARCHAR(255) NULL, docfile_ult_mod timestamp(6) NULL, docfile_ult_origen VARCHAR(255) NULL, docfile_ult_usuario VARCHAR(255) NULL, docfile_version INT NULL, docfile_doc_fk INT NULL);

--  Changeset db.changelog_siges_4410.xml::1494002716492-4::bruno (generated)
ALTER TABLE doc_file ADD docfile_path VARCHAR(255) NULL;

--  Changeset db.changelog_siges_4410.xml::1494002716492-5::bruno (generated)
ALTER TABLE doc_file ADD docfile_ult_mod timestamp without time zone NULL;

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
ALTER TABLE entregables ADD ent_inicio_periodo BOOLEAN DEFAULT FALSE NOT NULL;

--  Changeset db.changelog_siges_4410.xml::1494002716492-14::bruno (generated)
ALTER TABLE entregables ADD ent_fin_periodo BOOLEAN DEFAULT FALSE NOT NULL;

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

ALTER SEQUENCE ss_configuraciones_id_seq RESTART WITH 132;

-- Especificar directorio donde se desean almacenar los documentos
INSERT INTO ss_configuraciones (cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_ult_origen, cnf_version) VALUES
(NULL, 'DOCUMENTOS_DIR', 'Directorio donde se almacenarán los documentos', '/srv/siges/docs', NULL, '0', NULL, now(), NULL, '0');

-- Luego de ejecutar la migración de documentos

-- Se recomienda hacer un respaldo de esta tabla antes de aplicar está acción.
ALTER TABLE doc_file DROP COLUMN docfile_file;
DELETE FROM aud_doc_file; -- limpia la auditoría de la migración




