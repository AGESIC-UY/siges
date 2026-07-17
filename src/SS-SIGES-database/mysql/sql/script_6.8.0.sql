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




