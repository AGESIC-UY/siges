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


-- Inserta en la tabla de configuraciones el motor de base de datos que va a usar la aplicación.
-- insert into siges.ss_configuraciones(cnf_codigo,cnf_descripcion,cnf_valor, cnf_version) values('DATABASE_ENGINE','Motor de base de datos','postgresql',0);

-- Inserta en la tabla de configuraciones el nombre del schema a utilizar. Por defecto lo puse en 'siges', pero si es otro cambiar dicho nombre.
-- insert into siges.ss_configuraciones(cnf_codigo,cnf_descripcion,cnf_valor, cnf_version) values('DATABASE_SCHEMA','Schema de base de datos','siges',0);