-- Fix en actualizar documentos
UPDATE tipo_documento SET tipodoc_resum_ejecutivo = 0 WHERE tipodoc_resum_ejecutivo IS NULL;

-- Nueva configuracion para areas tematicas
insert into ss_configuraciones(
	cnf_org_fk, cnf_codigo, cnf_descripcion, 
	cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_ult_origen, cnf_version)
SELECT
   o.org_pk, 'GERENTES_ASIGNAN_AREAS_TEMATICAS', 'Habilita a Gerente y Adjunto a asignar áreas temáticas a los proyectos en los cuales se encuentran asignados.', 
   'false', null, null, null, CURRENT_TIMESTAMP(), null, 1 
FROM
   organismos as o;

-- Cambio de tipo de dato de id de adqisicion
UPDATE adquisicion SET adq_id_adquisicion = '0' 
WHERE adq_id_adquisicion IS NULL OR adq_id_adquisicion = '';

ALTER Table adquisicion MODIFY COLUMN adq_id_adquisicion INT(10);

insert into ss_configuraciones(
	cnf_org_fk, cnf_codigo, cnf_descripcion, 
	cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_ult_origen, cnf_version)
SELECT
   o.org_pk, 'LARGO_MAXIMO_ID_ADQUISICION', 'Define el largo máximo del valor del campo \'Id. de Adquisición\' en Adquisición.', 
   '5', null, null, null, CURRENT_TIMESTAMP(), null, 1 
FROM
   organismos as o;

-- Campo habilitado en orga_int_prove
ALTER TABLE organi_int_prove ADD COLUMN orga_habilitado TINYINT(3) NOT NULL;
UPDATE organi_int_prove SET orga_habilitado = 1;

-- Campo habilitado en objetivos_estrategicos
ALTER TABLE objetivos_estrategicos ADD COLUMN obj_est_habilitado TINYINT(3) NOT NULL;
UPDATE objetivos_estrategicos SET obj_est_habilitado = 1;  

-- Campo habilitado en fuente_financiamiento
ALTER TABLE fuente_financiamiento ADD COLUMN fue_habilitada TINYINT(3) NOT NULL;
UPDATE fuente_financiamiento SET fue_habilitada = 1;
