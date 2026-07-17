-- =============================================================================
-- SIGES - Script consolidado de actualización de base de datos
-- Origen:  6.7.1
-- Destino: 6.17.5
--
-- Uso: ejecutar sobre una base de datos SIGES existente en versión 6.7.1.
--      Realizar backup completo antes de ejecutar.
--
-- La versión 6.17.5 no incluye cambios de base de datos (solo aplicación).
-- Este script aplica todos los cambios incrementales entre 6.7.1 y 6.17.1e.
--
-- Generado para el Catálogo de Software Público - AGESIC
-- =============================================================================


-- -----------------------------------------------------------------------------
-- Versión 6.8.0
-- Archivo origen: script_6.8.0.sql
-- -----------------------------------------------------------------------------

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('FECHAS_COMPRA_REQUERIDO', 'Indica si son requeridas las fechas en adquisición', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk)
Select 'FECHAS_COMPRA_REQUERIDO','Indica si son requeridas las fechas en adquisición','false',0 ,org_pk from organismos;

CREATE TABLE grupos_trabajo (
	gtb_pk BIGINT auto_increment NOT NULL,
	gtb_nombre varchar(100) NULL,
	gtb_organismo_fk INTEGER NULL,
	gtb_habilitado boolean NULL,
	CONSTRAINT grupo_trabajo_PK PRIMARY KEY (gtb_pk),
	CONSTRAINT grupo_trabajo_FK_1 FOREIGN KEY (gtb_organismo_fk) REFERENCES organismos(org_pk)
);

CREATE TABLE grupos_trabajo_usuarios (
	gtu_pk BIGINT auto_increment NOT NULL,
	gtu_grupo_fk BIGINT NULL,
	gtu_usuario_fk INTEGER NULL,
	CONSTRAINT grupo_trabajo_PK_2 PRIMARY KEY (gtu_pk),
	CONSTRAINT grupo_trabajo_FK_2 FOREIGN KEY (gtu_grupo_fk) REFERENCES grupos_trabajo(gtb_pk),
	CONSTRAINT grupo_trabajo_FK_3 FOREIGN KEY (gtu_usuario_fk) REFERENCES ss_usuario(usu_id)
	
);

CREATE TABLE operacion_detalle (
	opd_pk BIGINT auto_increment NOT NULL,
	opd_codigo varchar(100) NULL,
	opd_descripcion varchar(200) NULL,
	CONSTRAINT operacion_detalle_PK PRIMARY KEY (opd_pk)
);


CREATE TABLE grupo_trabajo_operacion_detalle (
	gto_pk BIGINT auto_increment NOT NULL,
	gto_grupo_fk BIGINT NULL,
	gto_operacion_fk BIGINT NULL,
	CONSTRAINT grupo_trabajo_operacion_detalle_PK PRIMARY KEY (gto_pk),
	CONSTRAINT grupo_trabajo_operacion_detalle_FK FOREIGN KEY (gto_grupo_fk) REFERENCES grupos_trabajo(gtb_pk),
	CONSTRAINT grupo_trabajo_operacion_detalle_FK_1 FOREIGN KEY (gto_operacion_fk) REFERENCES operacion_detalle(opd_pk)
);

INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('AMB_INT', 'Ambitos - Interesados ');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('ARE_CON', 'Areas de Conocimiento');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('ARE_ORG', 'Areas de Organismo');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('ARE_TEM', 'Areas Tematicas');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('CAU_COM', 'Causal de compra');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('CEN_COS', 'Centro de costo');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('COM_PRO', 'Componente Producto');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('DOC', 'Documentos');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('FUE_FIN', 'Fuente de financiamento');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('FUE_PRO', 'Fuente y Procedimiento de compra');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('IDE_GRP', 'Identificado GRP/ERP');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('MON', 'Monedas');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('NOT', 'Notificaciones');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('OBJ_EST', 'Objetivos Estrategicos');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('ORG', 'Organizaciones');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('PLA_MAI', 'Plantilla de mails');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('PRO_COM', 'Procedimientos de compra');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('RIE', 'Riesgos');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('ROL_INT', 'Roles de interesados');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('TEM_CAL', 'Temas de calidad');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('TIP_ADQ', 'Tipos de adquisicion');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('TIP_DOC', 'Tipos de documentos');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('TIP_GAS', 'Tipos de gastos');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('TIP_RIE', 'Tipos de riesgos');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('PER', 'Personas');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('PLA_CRO', 'Planilla de Cronograma');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('PROY', 'Proyectos');
INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('PROG', 'Programas');






-- -----------------------------------------------------------------------------
-- Versión 6.9.2
-- Archivo origen: script_6.9.2.sql
-- -----------------------------------------------------------------------------

DELETE from grupo_trabajo_operacion_detalle
where gto_operacion_fk IN (
	SELECT opd_pk
	From operacion_detalle 
	WHERE opd_codigo = 'MON'
);

DELETE FROM operacion_detalle
WHERE opd_codigo ='MON';

-- -----------------------------------------------------------------------------
-- Versión 6.10.0
-- Archivo origen: script_6.10.0.sql
-- -----------------------------------------------------------------------------

INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('ADQ_GES', 'Adquisiciones gestión');


CREATE TABLE log_adquisiciones (
    lad_pk BIGINT auto_increment NOT NULL,
    lad_adquisicion_fk integer NULL,
    lad_fecha datetime DEFAULT NULL,
    lad_usuario varchar(100) DEFAULT NULL,
  lad_detalle varchar(250) DEFAULT NULL,
  CONSTRAINT log_adquisicion_PK PRIMARY KEY (lad_pk),
   CONSTRAINT log_adquisicion_adquisicion_FK FOREIGN KEY (lad_adquisicion_fk) REFERENCES adquisicion(adq_pk)
);







-- -----------------------------------------------------------------------------
-- Versión 6.11.0
-- Archivo origen: script_6.11.0.sql
-- -----------------------------------------------------------------------------

ALTER TABLE ss_usu_ofi_roles ADD usu_ofi_roles_puede_exportar boolean DEFAULT false ;
ALTER TABLE aud_ss_usu_ofi_roles ADD usu_ofi_roles_puede_exportar boolean DEFAULT false ;
UPDATE operacion_detalle
SET  opd_descripcion='Proveedores / Clientes'
WHERE opd_codigo='ORG';




-- -----------------------------------------------------------------------------
-- Versión 6.11.1
-- Archivo origen: script_6.11.1.sql
-- -----------------------------------------------------------------------------

ALTER TABLE ss_usuario ADD usu_puede_exportar boolean DEFAULT false ;
ALTER TABLE aud_ss_usuario ADD usu_puede_exportar boolean DEFAULT false ;

ALTER TABLE ss_usuario
DROP COLUMN usu_puede_exportar;

ALTER TABLE aud_ss_usuario
DROP COLUMN usu_puede_exportar;



-- -----------------------------------------------------------------------------
-- Versión 6.12.0
-- Archivo origen: script_6.12.0.sql
-- -----------------------------------------------------------------------------

ALTER TABLE ss_usuario ADD usu_fin_bloqueo datetime DEFAULT NULL;
ALTER TABLE aud_ss_usuario ADD usu_fin_bloqueo datetime DEFAULT NULL;

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('MENSAJE_ERROR_LOGEO', 'Indica mensaje de error de logeo', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk)
Select 'MENSAJE_ERROR_LOGEO','Indica mensaje de error de logeo','false',0 ,org_pk from organismos;


ALTER TABLE ss_usuario ADD usu_fin_hash_token datetime DEFAULT NULL;
ALTER TABLE aud_ss_usuario ADD usu_fin_hash_token datetime DEFAULT NULL;
ALTER TABLE ss_usuario ADD usu_hash_token varchar(100) DEFAULT NULL;
ALTER TABLE aud_ss_usuario ADD usu_hash_token varchar(100) DEFAULT NULL;


UPDATE mails_template
SET mail_tmp_mensaje='<p> 	Estimado #NOMBRE#, para cambiar su contrase&ntilde;a en SIGES haga clic en el enlace</p> 
<p> 	<a href="#URL_SISTEMA#">Cambiar contrase&ntilde;a</a></p>
<p> 	Este link vence en breves minutos, en caso de demoras, deberá repetir el proceso de reseteo de clave</p>
'
WHERE mail_tmp_cod='MAIL_CAMBIO_CONTRASENIA';


UPDATE mails_template_defecto
SET mensaje='<p> 	Estimado #NOMBRE#, para cambiar su contrase&ntilde;a en SIGES haga clic en el enlace</p> 
<p> 	<a href="#URL_SISTEMA#">Cambiar contrase&ntilde;a</a></p>
<p> 	Este link vence en breves minutos, en caso de demoras, deberá repetir el proceso de reseteo de clave</p>
'
WHERE  codigo='MAIL_CAMBIO_CONTRASENIA';



-- -----------------------------------------------------------------------------
-- Versión 6.13.0
-- Archivo origen: script_6.13.0.sql
-- -----------------------------------------------------------------------------

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_0_4', 'Indica color 0.4', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_0_8', 'Indica color 0.8', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_1_2', 'Indica color 1.2', 'false', 0, CURDATE(), 'anonymous', 0);


INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_1_6', 'Indica color 1,6', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_2_0', 'Indica color 2.0', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_2_4', 'Indica color 2.4', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_3_2', 'Indica color 3.2', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_3_6', 'Indica color 3.6', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_4_0', 'Indica color 4.0', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_4_8', 'Indica color 4.8', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_6_0', 'Indica color 6.0', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_6_4', 'Indica color 6.4', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_8_0', 'Indica color 8.0', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_10_0', 'Indica color 10.0', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_0_4','Indica color para 0.4','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_0_8','Indica color para 0.8','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_1_2','Indica color para 1.2','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_1_6','Indica color para 1.6','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_2_0','Indica color para 2.0','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_2_4','Indica color para 2.4','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_3_2','Indica color para 3.2','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_3_6','Indica color para 3.6','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_4_0','Indica color para 4.0','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_4_8','Indica color para 4.8','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_6_0','Indica color para 6.0','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_6_4','Indica color para 6.4','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_8_0','Indica color para 8.0','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_10_0','Indica color para 10.0','false',0 ,org_pk from organismos;

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 0.4
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_0_4';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 0.4 
inner join ss_configuraciones sc2 on
sc2.cnf_org_fk =cc.cnf_org_fk
and sc2.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.cnf_valor +"0.0" >  0.4
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_0_4';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 0.4
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_0_4';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 0.8
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_0_8';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 0.8 
inner join ss_configuraciones sc2 on
sc2.cnf_org_fk =cc.cnf_org_fk
and sc2.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.cnf_valor +"0.0" >  0.8
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_0_8';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 0.8
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_0_8';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 1.2
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_1_2';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 1.2 
inner join ss_configuraciones sc2 on
sc2.cnf_org_fk =cc.cnf_org_fk
and sc2.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.cnf_valor +"0.0" >  1.2
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_1_2';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 1.2
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_1_2';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 1.6
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_1_6';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 1.6 
inner join ss_configuraciones sc6 on
sc6.cnf_org_fk =cc.cnf_org_fk
and sc6.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc6.cnf_valor +"0.0" >=  1.6
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_1_6';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 1.6
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_1_6';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 2.0
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_2_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 2.0 
inner join ss_configuraciones sc0 on
sc0.cnf_org_fk =cc.cnf_org_fk
and sc0.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.cnf_valor +"0.0" >=  2.0
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_2_0';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 2.0
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_2_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 2.4
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_2_4';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 2.4 
inner join ss_configuraciones sc4 on
sc4.cnf_org_fk =cc.cnf_org_fk
and sc4.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc4.cnf_valor +"0.0" >=  2.4
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_2_4';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 2.4
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_2_4';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 3.2
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_3_2';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 3.2 
inner join ss_configuraciones sc2 on
sc2.cnf_org_fk =cc.cnf_org_fk
and sc2.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.cnf_valor +"0.0" >  3.2
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_3_2';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 3.2
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_3_2';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 3.6
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_3_6';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 3.6 
inner join ss_configuraciones sc6 on
sc6.cnf_org_fk =cc.cnf_org_fk
and sc6.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc6.cnf_valor +"0.0" >=  3.6
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_3_6';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 3.6
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_3_6';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 4.0
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_4_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 4.0 
inner join ss_configuraciones sc0 on
sc0.cnf_org_fk =cc.cnf_org_fk
and sc0.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.cnf_valor +"0.0" >=  4.0
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_4_0';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 4.0
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_4_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 4.8
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_4_8';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 4.8 
inner join ss_configuraciones sc8 on
sc8.cnf_org_fk =cc.cnf_org_fk
and sc8.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc8.cnf_valor +"0.0" >=  4.8
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_4_8';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 4.8
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_4_8';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 6.0
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_6_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 6.0 
inner join ss_configuraciones sc0 on
sc0.cnf_org_fk =cc.cnf_org_fk
and sc0.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.cnf_valor +"0.0" >=  6.0
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_6_0';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 6.0
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_6_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 6.4
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_6_4';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 6.4 
inner join ss_configuraciones sc4 on
sc4.cnf_org_fk =cc.cnf_org_fk
and sc4.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc4.cnf_valor +"0.0" >=  6.4
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_6_4';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 6.4
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_6_4';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 8.0
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_8_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 8.0 
inner join ss_configuraciones sc0 on
sc0.cnf_org_fk =cc.cnf_org_fk
and sc0.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.cnf_valor +"0.0" >=  8.0
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_8_0';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 8.0
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_8_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 10.0
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_10_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 10.0 
inner join ss_configuraciones sc0 on
sc0.cnf_org_fk =cc.cnf_org_fk
and sc0.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.cnf_valor +"0.0" >=  10.0
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_10_0';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 10.0
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_10_0';

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('RIESGO_TITULO_EJE_X', 'Titulo de eje x para grafico de riesgo', 'Probabilidad', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'RIESGO_TITULO_EJE_X','Titulo de eje x para grafico de riesgo','Probabilidad',0 ,org_pk from organismos;

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('RIESGO_TITULO_EJE_Y', 'Titulo de eje y para grafico de riesgo', 'Impacto', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'RIESGO_TITULO_EJE_Y','Titulo de eje y para grafico de riesgo','Impacto',0 ,org_pk from organismos;



INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('RIESGO_VALORES_EJE_X', 'Valores del eje x para riesgo ', '20%,40%,60%,80%,100%', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'RIESGO_VALORES_EJE_X','Valores del eje x para riesgo','20%,40%,60%,80%,100%',0 ,org_pk from organismos;

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('RIESGO_VALORES_EJE_Y', 'Valores del eje y para riesgo', '2,4,6,8,10', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'RIESGO_VALORES_EJE_Y','Valores del eje y para riesgo','2,4,6,8,10',0 ,org_pk from organismos;

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 0.4
set cc.valor ='Verde'
where cc.codigo ='COLOR_0_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 0.4 
inner join ss_configuraciones_defecto sc2 on sc2.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.valor +"0.0" >  0.4
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_0_4';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 0.4
set cc.valor ='Rojo'
where cc.codigo ='COLOR_0_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 0.8
set cc.valor ='Verde'
where cc.codigo ='COLOR_0_8';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 0.8 
inner join ss_configuraciones_defecto sc2 on sc2.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.valor +"0.0" >  0.8
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_0_8';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 0.8
set cc.valor ='Rojo'
where cc.codigo ='COLOR_0_8';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 1.2
set cc.valor ='Verde'
where cc.codigo ='COLOR_1_2';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 1.2 
inner join ss_configuraciones_defecto sc2 on  sc2.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.valor +"0.0" >  1.2
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_1_2';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 1.2
set cc.valor ='Rojo'
where cc.codigo ='COLOR_1_2';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 1.6
set cc.valor ='Verde'
where cc.codigo ='COLOR_1_6';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 1.6 
inner join ss_configuraciones_defecto sc6 on  sc6.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc6.valor +"0.0" >=  1.6
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_1_6';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 1.6
set cc.valor ='Rojo'
where cc.codigo ='COLOR_1_6';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 2.0
set cc.valor ='Verde'
where cc.codigo ='COLOR_2_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 2.0 
inner join ss_configuraciones_defecto sc0 on sc0.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.valor +"0.0" >=  2.0
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_2_0';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 2.0
set cc.valor ='Rojo'
where cc.codigo ='COLOR_2_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 2.4
set cc.valor ='Verde'
where cc.codigo ='COLOR_2_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 2.4 
inner join ss_configuraciones_defecto sc4 on sc4.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc4.valor +"0.0" >=  2.4
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_2_4';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 2.4
set cc.valor ='Rojo'
where cc.codigo ='COLOR_2_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 3.2
set cc.valor ='Verde'
where cc.codigo ='COLOR_3_2';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 3.2 
inner join ss_configuraciones_defecto sc2 on sc2.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.valor +"0.0" >  3.2
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_3_2';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 3.2
set cc.valor ='Rojo'
where cc.codigo ='COLOR_3_2';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 3.6
set cc.valor ='Verde'
where cc.codigo ='COLOR_3_6';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 3.6 
inner join ss_configuraciones_defecto sc6 on sc6.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc6.valor +"0.0" >=  3.6
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_3_6';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 3.6
set cc.valor ='Rojo'
where cc.codigo ='COLOR_3_6';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 4.0
set cc.valor ='Verde'
where cc.codigo ='COLOR_4_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 4.0 
inner join ss_configuraciones_defecto sc0 on sc0.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.valor +"0.0" >=  4.0
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_4_0';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 4.0
set cc.valor ='Rojo'
where cc.codigo ='COLOR_4_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 4.8
set cc.valor ='Verde'
where cc.codigo ='COLOR_4_8';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 4.8 
inner join ss_configuraciones_defecto sc8 on
 sc8.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc8.valor +"0.0" >=  4.8
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_4_8';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 4.8
set cc.valor ='Rojo'
where cc.codigo ='COLOR_4_8';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 6.0
set cc.valor ='Verde'
where cc.codigo ='COLOR_6_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 6.0 
inner join ss_configuraciones_defecto sc0 on sc0.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.valor +"0.0" >=  6.0
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_6_0';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 6.0
set cc.valor ='Rojo'
where cc.codigo ='COLOR_6_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 6.4
set cc.valor ='Verde'
where cc.codigo ='COLOR_6_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 6.4 
inner join ss_configuraciones_defecto sc4 on
sc4.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc4.valor +"0.0" >=  6.4
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_6_4';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 6.4
set cc.valor ='Rojo'
where cc.codigo ='COLOR_6_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 8.0
set cc.valor ='Verde'
where cc.codigo ='COLOR_8_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 8.0 
inner join ss_configuraciones_defecto sc0 on  sc0.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.valor +"0.0" >=  8.0
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_8_0';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 8.0
set cc.valor ='Rojo'
where cc.codigo ='COLOR_8_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 10.0
set cc.valor ='Verde'
where cc.codigo ='COLOR_10_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 10.0 
inner join ss_configuraciones_defecto sc0 on sc0.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.valor +"0.0" >=  10.0
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_10_0';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 10.0
set cc.valor ='Rojo'
where cc.codigo ='COLOR_10_0';













-- -----------------------------------------------------------------------------
-- Versión 6.13.1
-- Archivo origen: script_v_6.13.1.sql
-- -----------------------------------------------------------------------------

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 0.4
set cc.valor ='Verde'
where cc.codigo ='COLOR_0_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 0.4 
inner join ss_configuraciones_defecto sc2 on sc2.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.valor +"0.0" >  0.4
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_0_4';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 0.4
set cc.valor ='Rojo'
where cc.codigo ='COLOR_0_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 0.8
set cc.valor ='Verde'
where cc.codigo ='COLOR_0_8';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 0.8 
inner join ss_configuraciones_defecto sc2 on sc2.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.valor +"0.0" >  0.8
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_0_8';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 0.8
set cc.valor ='Rojo'
where cc.codigo ='COLOR_0_8';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 1.2
set cc.valor ='Verde'
where cc.codigo ='COLOR_1_2';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 1.2 
inner join ss_configuraciones_defecto sc2 on  sc2.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.valor +"0.0" >  1.2
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_1_2';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 1.2
set cc.valor ='Rojo'
where cc.codigo ='COLOR_1_2';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 1.6
set cc.valor ='Verde'
where cc.codigo ='COLOR_1_6';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 1.6 
inner join ss_configuraciones_defecto sc6 on  sc6.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc6.valor +"0.0" >=  1.6
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_1_6';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 1.6
set cc.valor ='Rojo'
where cc.codigo ='COLOR_1_6';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 2.0
set cc.valor ='Verde'
where cc.codigo ='COLOR_2_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 2.0 
inner join ss_configuraciones_defecto sc0 on sc0.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.valor +"0.0" >=  2.0
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_2_0';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 2.0
set cc.valor ='Rojo'
where cc.codigo ='COLOR_2_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 2.4
set cc.valor ='Verde'
where cc.codigo ='COLOR_2_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 2.4 
inner join ss_configuraciones_defecto sc4 on sc4.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc4.valor +"0.0" >=  2.4
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_2_4';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 2.4
set cc.valor ='Rojo'
where cc.codigo ='COLOR_2_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 3.2
set cc.valor ='Verde'
where cc.codigo ='COLOR_3_2';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 3.2 
inner join ss_configuraciones_defecto sc2 on sc2.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.valor +"0.0" >  3.2
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_3_2';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 3.2
set cc.valor ='Rojo'
where cc.codigo ='COLOR_3_2';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 3.6
set cc.valor ='Verde'
where cc.codigo ='COLOR_3_6';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 3.6 
inner join ss_configuraciones_defecto sc6 on sc6.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc6.valor +"0.0" >=  3.6
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_3_6';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 3.6
set cc.valor ='Rojo'
where cc.codigo ='COLOR_3_6';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 4.0
set cc.valor ='Verde'
where cc.codigo ='COLOR_4_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 4.0 
inner join ss_configuraciones_defecto sc0 on sc0.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.valor +"0.0" >=  4.0
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_4_0';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 4.0
set cc.valor ='Rojo'
where cc.codigo ='COLOR_4_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 4.8
set cc.valor ='Verde'
where cc.codigo ='COLOR_4_8';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 4.8 
inner join ss_configuraciones_defecto sc8 on
 sc8.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc8.valor +"0.0" >=  4.8
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_4_8';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 4.8
set cc.valor ='Rojo'
where cc.codigo ='COLOR_4_8';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 6.0
set cc.valor ='Verde'
where cc.codigo ='COLOR_6_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 6.0 
inner join ss_configuraciones_defecto sc0 on sc0.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.valor +"0.0" >=  6.0
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_6_0';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 6.0
set cc.valor ='Rojo'
where cc.codigo ='COLOR_6_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 6.4
set cc.valor ='Verde'
where cc.codigo ='COLOR_6_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 6.4 
inner join ss_configuraciones_defecto sc4 on
sc4.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc4.valor +"0.0" >=  6.4
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_6_4';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 6.4
set cc.valor ='Rojo'
where cc.codigo ='COLOR_6_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 8.0
set cc.valor ='Verde'
where cc.codigo ='COLOR_8_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 8.0 
inner join ss_configuraciones_defecto sc0 on  sc0.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.valor +"0.0" >=  8.0
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_8_0';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 8.0
set cc.valor ='Rojo'
where cc.codigo ='COLOR_8_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 10.0
set cc.valor ='Verde'
where cc.codigo ='COLOR_10_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 10.0 
inner join ss_configuraciones_defecto sc0 on sc0.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.valor +"0.0" >=  10.0
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_10_0';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 10.0
set cc.valor ='Rojo'
where cc.codigo ='COLOR_10_0';



-- -----------------------------------------------------------------------------
-- Versión 6.14.0
-- Archivo origen: script_v_6.14.0.sql
-- -----------------------------------------------------------------------------

INSERT INTO siges.operacion_detalle
( opd_codigo, opd_descripcion)
VALUES( 'IMP_ADQ', 'Importar Adquisiciones');



-- -----------------------------------------------------------------------------
-- Versión 6.15.0
-- Archivo origen: script_v_6.15.0.sql
-- -----------------------------------------------------------------------------

UPDATE operacion_detalle
SET  opd_descripcion='Gestión de Adquisiciones'
WHERE opd_codigo='ADQ_GES';

INSERT INTO siges.operacion_detalle
( opd_codigo, opd_descripcion)
VALUES( 'INFO_ADQ', 'Adquisición Log');



-- -----------------------------------------------------------------------------
-- Versión 6.15.4
-- Archivo origen: script_v_6.15.4.sql
-- -----------------------------------------------------------------------------

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES( 'CENTRO_DE_COSTO_LECTURA', 'Habilita editar Combo Centro de Costos', 'false', 0, current_date, 'admin', 0);

-- -----------------------------------------------------------------------------
-- Versión 6.16.4
-- Archivo origen: script_v_6.16.4.sql
-- -----------------------------------------------------------------------------

ALTER TABLE entregables
MODIFY COLUMN ent_descripcion VARCHAR(1500) DEFAULT NULL;

-- -----------------------------------------------------------------------------
-- Versión 6.17.1b
-- Archivo origen: script_v_6.17.1b.sql
-- -----------------------------------------------------------------------------

UPDATE ss_usuario
SET usu_correo_electronico = 'siges2@agesic.gub.uy'
WHERE usu_correo_electronico = 'siges@agesic.gub.uy';

UPDATE ss_usuario
SET usu_correo_electronico = 'siges@agesic.gub.uy'
WHERE usu_correo_electronico = 'admin';

INSERT INTO ss_configuraciones (
    cnf_org_fk,
    cnf_codigo,
    cnf_descripcion,
    cnf_valor,
    cnf_protegido,
    cnf_html,
    cnf_ult_usuario,
    cnf_ult_mod,
    cnf_ult_origen,
    cnf_version
) VALUES (
    NULL,
    'DOBLE_FACTOR_SEGUNDOS',
    'Indica la duracion del codigo de doble factor en segundos',
    '180',
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    1
);

INSERT INTO ss_configuraciones (
    cnf_org_fk,
    cnf_codigo,
    cnf_descripcion,
    cnf_valor,
    cnf_protegido,
    cnf_html,
    cnf_ult_usuario,
    cnf_ult_mod,
    cnf_ult_origen,
    cnf_version
) VALUES (
    NULL,
    'DOBLE_FACTOR_ACTIVO',
    'Indica si el doble factor va a estar activo',
    'true',
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    1
);


-- -----------------------------------------------------------------------------
-- Versión 6.17.1e
-- Archivo origen: script_v_6.17.1e.sql
-- -----------------------------------------------------------------------------

UPDATE ss_usu_ofi_roles SET usu_ofi_roles_oficina = 1 WHERE usu_ofi_roles_rol = 0
