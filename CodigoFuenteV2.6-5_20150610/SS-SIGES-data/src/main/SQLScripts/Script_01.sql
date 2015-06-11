ALTER TABLE `siges`.`adquisicion` 
DROP FOREIGN KEY `adq_prov_orga`;
ALTER TABLE `siges`.`adquisicion` 
ADD INDEX `adq_prov_orga_idx` (`adq_prov_orga_fk` ASC),
DROP INDEX `adq_prov_orga_idx` ;
ALTER TABLE `siges`.`adquisicion` 
ADD CONSTRAINT `adq_prov_orga`
  FOREIGN KEY (`adq_prov_orga_fk`)
  REFERENCES `siges`.`organi_int_prove` (`orga_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `siges`.`ss_usuario` 
CHANGE COLUMN `usu_cod` `usu_cod` VARCHAR(255) NOT NULL ,
CHANGE COLUMN `usu_correo_electronico` `usu_correo_electronico` VARCHAR(255) NOT NULL ;


INSERT INTO `siges`.`ss_rol` (`rol_cod`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('ADMINIS', 'ADMINIS', 'SIGES_WEB', '0', '1');
INSERT INTO `siges`.`ss_rol` (`rol_cod`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('ADMIN_TDO', 'ADMIN_TDO', 'SIGES_WEB', '0', '1');
INSERT INTO `siges`.`ss_rol` (`rol_cod`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('ADMIN_ERR', 'ADMIN_ERR', 'SIGES_WEB', '0', '1');
INSERT INTO `siges`.`ss_rol` (`rol_cod`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('ADM_CONF', 'ADM_CONF', 'SIGES_WEB', '0', '1');
INSERT INTO `siges`.`ss_rol` (`rol_cod`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('CONF_ADD', 'CONF_ADD', 'SIGES_WEB', '0', '1');
INSERT INTO `siges`.`ss_rol` (`rol_cod`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('CONF_EDIT', 'CONF_EDIT', 'SIGES_WEB', '0', '1');
INSERT INTO `siges`.`ss_rol` (`rol_cod`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('CONF_HIST', 'CONF_HIST', 'SIGES_WEB', '0', '1');
INSERT INTO `siges`.`ss_rol` (`rol_cod`, `rol_descripcion`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('MIGRACION', 'Acceso a la migración', 'MIGRACION', 'SIGES_WEB', '0', '1');
INSERT INTO `siges`.`ss_rol` (`rol_cod`, `rol_descripcion`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('MIGRA_CALC_INDICA', 'Acceso a los callculos de la migración', 'MIGRA_CALC_INDICA', 'SIGES_WEB', '0', '1');


ALTER TABLE `siges`.`organismos` 
CHANGE COLUMN `org_logo` `org_logo_nombre` VARCHAR(45) NULL DEFAULT NULL ;
ALTER TABLE `siges`.`organismos` 
ADD COLUMN `org_logo` BLOB NULL DEFAULT NULL AFTER `org_direccion`;

-- Hasta acá copiado en Siges 14/5/2014.

CREATE TABLE `siges`.`ambito` (
  `amb_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `amb_nombre` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`amb_pk`));


ALTER TABLE `siges`.`tipo_documento` 
ADD COLUMN `tipodoc_resum_ejecutivo` TINYINT(1) NULL DEFAULT NULL AFTER `tipodoc_org_fk`;

UPDATE `siges`.`tipo_documento` SET `tipodoc_resum_ejecutivo`='1' WHERE `tipdoc_pk`='6';
UPDATE `siges`.`tipo_documento` SET `tipodoc_resum_ejecutivo`='0' WHERE `tipodoc_resum_ejecutivo` is null and tipdoc_pk>0;

-- Hasta acá copiado en Siges 29/5/2014.

update siges.programas p set p.prog_activo=1 where p.prog_pk > 0 and p.prog_activo is null;
update siges.proyectos p set p.proy_activo=1 where p.proy_pk > 0 and p.proy_activo is null;

ALTER TABLE `siges`.`ss_usuario` 
ADD COLUMN `usu_ult_usuario` VARCHAR(255) NULL DEFAULT NULL AFTER `usu_vigente`,
ADD COLUMN `usu_ult_mod` DATETIME NULL DEFAULT NULL AFTER `usu_ult_usuario`,
ADD COLUMN `usu_ult_origen` VARCHAR(255) NULL DEFAULT NULL AFTER `usu_ult_mod`;

INSERT INTO `siges`.`ss_rol` (`rol_cod`, `rol_descripcion`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('ADMIN_USU', 'Administración de los Usuarios', 'ADMIN_USU', 'SIGES_WEB', '0', '1');

ALTER TABLE `siges`.`aud_ss_usuario` 
ADD COLUMN `usu_ult_usuario` VARCHAR(255) NULL DEFAULT NULL AFTER `usu_vigente`,
ADD COLUMN `usu_ult_mod` DATETIME NULL DEFAULT NULL AFTER `usu_ult_usuario`,
ADD COLUMN `usu_ult_origen` VARCHAR(255) NULL DEFAULT NULL AFTER `usu_ult_mod`;

ALTER TABLE `siges`.`ss_usuario` 
ADD COLUMN `usu_celular` VARCHAR(255) NULL DEFAULT NULL AFTER `usu_telefono`;

ALTER TABLE `siges`.`aud_ss_usuario` 
ADD COLUMN `usu_celular` VARCHAR(255) NULL DEFAULT NULL AFTER `usu_ult_origen`;

update siges.ss_usuario set usu_version=1 where usu_version is null and usu_id>0;

ALTER TABLE `siges`.`ss_rol` 
ADD COLUMN `rol_tipo_usuario` TINYINT(1) NULL DEFAULT NULL AFTER `rol_vigente`;

ALTER TABLE `siges`.`aud_ss_rol` 
ADD COLUMN `rol_tipo_usuario` TINYINT(1) NULL DEFAULT NULL AFTER `rol_vigente`;

UPDATE `siges`.`ss_rol` SET `rol_tipo_usuario`='1' WHERE `rol_id`='1';
UPDATE `siges`.`ss_rol` SET `rol_tipo_usuario`='1' WHERE `rol_id`='2';
UPDATE `siges`.`ss_rol` SET `rol_tipo_usuario`='1' WHERE `rol_id`='3';
UPDATE `siges`.`ss_rol` SET `rol_tipo_usuario`='1' WHERE `rol_id`='4';

ALTER TABLE `siges`.`ss_usuario` 
CHANGE COLUMN `usu_nro_doc` `usu_nro_doc` VARCHAR(255) NULL DEFAULT NULL ;

INSERT INTO `siges`.`ss_rol` (`rol_cod`, `rol_descripcion`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('ADMIN_USU', 'Administración de los Usuarios', 'ADMIN_USU', 'SIGES_WEB', '0', '1');
INSERT INTO `siges`.`ss_usu_ofi_roles` (`usu_ofi_roles_origen`, `usu_ofi_roles_user_code`, `usu_ofi_roles_oficina`, `usu_ofi_roles_rol`, `usu_ofi_roles_usuario`) VALUES ('SIGES_WEB', '3', '1', '15', '3');

-- Hasta acá copiado en Siges 4/6/2014.

ALTER TABLE `siges`.`proyectos` 
ADD COLUMN `proy_fecha_est_act` DATE NULL DEFAULT NULL AFTER `proy_version`;

update proyectos set proy_fecha_est_act=proy_fecha_act where proy_fecha_est_act is null and proy_pk>0;

INSERT INTO `siges`.`ss_rol` (`rol_cod`, `rol_descripcion`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`, `rol_tipo_usuario`) VALUES ('USU_HORAS', 'Externo (solo carga de horas)', 'Externo (solo carga de horas)', 'SIGES_WEB', '0', '1', '1');

ALTER TABLE `siges`.`ss_usuario` 
CHANGE COLUMN `usu_fecha_password` `usu_fecha_password` DATETIME NULL DEFAULT NULL ,
CHANGE COLUMN `usu_origen` `usu_origen` LONGTEXT NULL DEFAULT NULL ,
CHANGE COLUMN `usu_user_code` `usu_user_code` INT(11) NULL DEFAULT NULL ;

ALTER TABLE `siges`.`ss_usuario` 
ADD COLUMN `usu_area_org` INT(11) NULL DEFAULT NULL AFTER `usu_ult_origen`,
ADD INDEX `usu_area_org_idx` (`usu_area_org` ASC);
ALTER TABLE `siges`.`ss_usuario` 
ADD CONSTRAINT `usu_area_org`
  FOREIGN KEY (`usu_area_org`)
  REFERENCES `siges`.`areas` (`area_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `siges`.`aud_ss_usuario` 
ADD COLUMN `usu_area_org` INT(11) NULL DEFAULT NULL AFTER `usu_ult_origen`,
ADD INDEX `usu_area_org_idx` (`usu_area_org` ASC);

-- Hasta acá copiado en Siges 6/6/2014.

ALTER TABLE `siges`.`programas` 
ADD COLUMN `prog_fecha_crea` DATE NULL AFTER `prog_activo`;

ALTER TABLE `siges`.`proyectos` 
ADD COLUMN `proy_fecha_crea` DATE NULL AFTER `proy_activo`;

update siges.proyectos set proy_fecha_crea=proy_fecha_act where proy_fecha_crea is null and proy_pk>0;
update siges.programas set prog_fecha_crea=prog_fecha_act where prog_fecha_crea is null and prog_pk>0;

USE `siges`;
CREATE 
     OR REPLACE ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `programas_proyectos` AS
    select 
        concat('1-', `programas`.`prog_pk`) AS `id`,
        `programas`.`prog_pk` AS `fichaFk`,
        1 AS `tipoFicha`,
	`programas`.`prog_fecha_crea` AS `fechaCrea`,
        `programas`.`prog_activo` AS `activo`,
        `programas`.`prog_org_fk` AS `organismo`,
        `programas`.`prog_nombre` AS `nombre`,
        `estados`.`est_pk` AS `estado`,
        `estados`.`est_nombre` AS `estadoNombre`,
        `areas`.`area_pk` AS `areaPk`,
        `areas`.`area_nombre` AS `areaNombre`,
        `programas`.`prog_sol_aceptacion` AS `solAceptacion`,
        `programas`.`prog_usr_gerente_fk` AS `gerente`,
        `ss_usuario`.`usu_primer_apellido` AS `gerentePrimerApellido`,
        `ss_usuario`.`usu_primer_nombre` AS `gerentePrimerNombre`,
        `programas`.`prog_usr_pmofed_fk` AS `pmoFederada`
    from
        (((`programas`
        join `estados` ON ((`programas`.`prog_est_fk` = `estados`.`est_pk`)))
        join `areas` ON ((`programas`.`prog_area_fk` = `areas`.`area_pk`)))
        join `ss_usuario` ON ((`programas`.`prog_usr_gerente_fk` = `ss_usuario`.`usu_id`))) 
    union select 
        concat('2-', `proyectos`.`proy_pk`) AS `id`,
        `proyectos`.`proy_pk` AS `fichaFk`,
        2 AS `tipoFicha`,
        `proyectos`.`proy_fecha_crea` AS `fechaCrea`,
        `proyectos`.`proy_activo` AS `activo`,
        `proyectos`.`proy_org_fk` AS `organismo`,
        `proyectos`.`proy_nombre` AS `nombre`,
        `estados`.`est_pk` AS `estado`,
        `estados`.`est_nombre` AS `estadoNombre`,
        `areas`.`area_pk` AS `areaPk`,
        `areas`.`area_nombre` AS `areaNombre`,
        `proyectos`.`proy_sol_aceptacion` AS `solAceptacion`,
        `proyectos`.`proy_usr_gerente_fk` AS `gerente`,
        `ss_usuario`.`usu_primer_apellido` AS `gerentePrimerApellido`,
        `ss_usuario`.`usu_primer_nombre` AS `gerentePrimerNombre`,
        `proyectos`.`proy_usr_pmofed_fk` AS `pmoFederada`
    from
        (((`proyectos`
        join `estados` ON ((`proyectos`.`proy_est_fk` = `estados`.`est_pk`)))
        join `areas` ON ((`proyectos`.`proy_area_fk` = `areas`.`area_pk`)))
        join `ss_usuario` ON ((`proyectos`.`proy_usr_gerente_fk` = `ss_usuario`.`usu_id`)));

-- Hasta acá copiado en Siges 12/6/2014.


INSERT INTO `siges`.`ss_configuraciones` (`cnf_codigo`, `cnf_descripcion`, `cnf_valor`, `cnf_ult_mod`, `cnf_version`) 
VALUES ('TAMANIO_MAX_LOGO_ORGANISMO', 'Tamaño maximo en bytes del logo del Organismo', '262144', '2014-06-11', '0');

INSERT INTO `siges`.`ss_rol` (`rol_cod`, `rol_descripcion`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`, `rol_tipo_usuario`) VALUES ('ADMINISTRADOR', 'Usuario Administrador de la aplicacion', 'Usuario Administrador', 'SIGES_WEB', '0', '1', '0');
UPDATE `siges`.`ss_rol` SET `rol_id`='0' WHERE `rol_cod`='ADMINISTRADOR' AND `rol_id`>0;

INSERT INTO `siges`.`ss_usuario` (`usu_administrador`, `usu_cod`, `usu_correo_electronico`, `usu_cuenta_bloqueada`, `usu_fecha_password`, `usu_password`, `usu_primer_apellido`, `usu_primer_nombre`, `usu_user_code`, `usu_version`, `usu_vigente`) 
VALUES ('1', 'admin', 'admin', '0', '2014-06-06 12:29:19', '21232f297a57a5a743894a0e4a801fc3', 'Administrador', 'Usuario', '0', '0', '1');
INSERT INTO `siges`.`ss_usu_ofi_roles` (`usu_ofi_roles_origen`, `usu_ofi_roles_user_code`, `usu_ofi_roles_oficina`, `usu_ofi_roles_rol`, `usu_ofi_roles_usuario`) 
VALUES ('SIGES_WEB', (SELECT usu_id FROM siges.ss_usuario where usu_correo_electronico='admin'), null, '0', (SELECT usu_id FROM siges.ss_usuario where usu_correo_electronico='admin'));

-- INSERT INTO `siges`.`organismos` (`org_pk`, `org_nombre`) VALUES ('0', 'SIGES');
-- UPDATE `siges`.`organismos` SET `org_pk`='3' WHERE `org_nombre`='SIGES' and `org_pk`>0;

ALTER TABLE `siges`.`ss_usu_ofi_roles` 
CHANGE COLUMN `usu_ofi_roles_oficina` `usu_ofi_roles_oficina` INT(11) NULL ;

-- Hasta acá copiado en Siges 17/6/2014.

ALTER TABLE `siges`.`doc_file` 
ADD COLUMN `docfile_doc_fk` INT(11) NOT NULL AFTER `docfile_nombre`;

update siges.doc_file set docfile_doc_fk=(SELECT docs_pk FROM siges.documentos where docs_docfile_pk=doc_file.docfile_pk) where siges.doc_file.docfile_pk>0;

INSERT INTO `siges`.`ss_configuraciones` (`cnf_codigo`, `cnf_descripcion`, `cnf_valor`, `cnf_ult_mod`, `cnf_version`) VALUES ('MAIL_FROM', 'Dirección desde donde se envían los mails', 'sofis.pruebas2@gmail.com', '2014-06-25', '0');
INSERT INTO `siges`.`ss_configuraciones` (`cnf_codigo`, `cnf_descripcion`, `cnf_valor`, `cnf_ult_mod`, `cnf_version`) VALUES ('MAIL_ENCODING', 'Encoding de los mails a enviar', 'utf8', '2014-06-25', '0');
INSERT INTO `siges`.`ss_configuraciones` (`cnf_codigo`, `cnf_descripcion`, `cnf_valor`, `cnf_ult_mod`, `cnf_version`) VALUES ('CON_CORREO', 'Si se envía correo o no', 'true', '2014-06-25', '0');


CREATE TABLE `siges`.`mails_template` (
  `mail_tmp_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `mail_tmp_cod` VARCHAR(45) NOT NULL,
  `mail_tmp_org_fk` INT(11) NOT NULL,
  `mail_tmp_asunto` VARCHAR(45) NOT NULL,
  `mail_tmp_mensaje` VARCHAR(5000) NOT NULL,
  PRIMARY KEY (`mail_tmp_pk`));

ALTER TABLE `siges`.`mails_template` 
ADD INDEX `mail_tmp_org_fk_idx` (`mail_tmp_org_fk` ASC);
ALTER TABLE `siges`.`mails_template` 
ADD CONSTRAINT `mail_tmp_org_fk`
  FOREIGN KEY (`mail_tmp_org_fk`)
  REFERENCES `siges`.`organismos` (`org_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

INSERT INTO `siges`.`mails_template` (`mail_tmp_cod`, `mail_tmp_org_fk`, `mail_tmp_asunto`, `mail_tmp_mensaje`) VALUES ('MAIL_SOL_APROBACION', '1', 'Solicitud de Aprobación', '<h2>Solicitud de Aprobación</h2><p>Se generó una solicitud de cambio de estado para el #TIPO_PROG_PROY# "#NOMBRE_PROG_PROY#".</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>');

CREATE TABLE `siges`.`proy_replanificacion` (
  `proyreplan_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `proyreplan_proy_fk` INT(11) NOT NULL,
  `proyreplan_fecha` DATE NOT NULL,
  `proyreplan_desc` VARCHAR(2000) NOT NULL,
  `proyreplan_historial` TINYINT(1) NOT NULL,
  PRIMARY KEY (`proyreplan_pk`),
  INDEX `proyreplan_proy_fk_idx` (`proyreplan_proy_fk` ASC),
  CONSTRAINT `proyreplan_proy_fk`
    FOREIGN KEY (`proyreplan_proy_fk`)
    REFERENCES `siges`.`proyectos` (`proy_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


update proyectos set proy_est_pendiente_fk=(proy_est_fk-1) where proy_sol_aceptacion=0 and proy_pk>0;
update proyectos set proy_est_pendiente_fk=(proy_est_fk+1) where proy_sol_aceptacion=1 and proy_pk>0;

ALTER TABLE `siges`.`entregables` 
ADD COLUMN `ent_horas_estimadas` DECIMAL(11,2) NULL DEFAULT NULL AFTER `ent_fin`;

-- Hasta acá copiado en Siges 4/7/2014.

ALTER TABLE `siges`.`prog_indices` 
ADD COLUMN `progind_proy_actualizacion` DATE NULL DEFAULT NULL AFTER `progind_periodo_fin`;

USE `siges`;
CREATE 
     OR REPLACE ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `programas_proyectos` AS
    select 
        concat('1-', `programas`.`prog_pk`) AS `id`,
        `programas`.`prog_pk` AS `fichaFk`,
        1 AS `tipoFicha`,
        `programas`.`prog_fecha_crea` AS `fechaCrea`,
        `programas`.`prog_activo` AS `activo`,
        `programas`.`prog_org_fk` AS `organismo`,
        `programas`.`prog_nombre` AS `nombre`,
        `estados`.`est_pk` AS `estado`,
        `estados`.`est_nombre` AS `estadoNombre`,
		`programas`.`prog_est_pendiente_fk` AS `estadoPendiente`,
        `areas`.`area_pk` AS `areaPk`,
        `areas`.`area_nombre` AS `areaNombre`,
        `programas`.`prog_sol_aceptacion` AS `solAceptacion`,
        `programas`.`prog_usr_gerente_fk` AS `gerente`,
        `ss_usuario`.`usu_primer_apellido` AS `gerentePrimerApellido`,
        `ss_usuario`.`usu_primer_nombre` AS `gerentePrimerNombre`,
        `programas`.`prog_usr_pmofed_fk` AS `pmoFederada`
    from
        (((`programas`
        join `estados` ON ((`programas`.`prog_est_fk` = `estados`.`est_pk`)))
        join `areas` ON ((`programas`.`prog_area_fk` = `areas`.`area_pk`)))
        join `ss_usuario` ON ((`programas`.`prog_usr_gerente_fk` = `ss_usuario`.`usu_id`))) 
    union select 
        concat('2-', `proyectos`.`proy_pk`) AS `id`,
        `proyectos`.`proy_pk` AS `fichaFk`,
        2 AS `tipoFicha`,
        `proyectos`.`proy_fecha_crea` AS `fechaCrea`,
        `proyectos`.`proy_activo` AS `activo`,
        `proyectos`.`proy_org_fk` AS `organismo`,
        `proyectos`.`proy_nombre` AS `nombre`,
        `estados`.`est_pk` AS `estado`,
        `estados`.`est_nombre` AS `estadoNombre`,
		`proyectos`.`proy_est_pendiente_fk` AS `estadoPendiente`,
        `areas`.`area_pk` AS `areaPk`,
        `areas`.`area_nombre` AS `areaNombre`,
        `proyectos`.`proy_sol_aceptacion` AS `solAceptacion`,
        `proyectos`.`proy_usr_gerente_fk` AS `gerente`,
        `ss_usuario`.`usu_primer_apellido` AS `gerentePrimerApellido`,
        `ss_usuario`.`usu_primer_nombre` AS `gerentePrimerNombre`,
        `proyectos`.`proy_usr_pmofed_fk` AS `pmoFederada`
    from
        (((`proyectos`
        join `estados` ON ((`proyectos`.`proy_est_fk` = `estados`.`est_pk`)))
        join `areas` ON ((`proyectos`.`proy_area_fk` = `areas`.`area_pk`)))
        join `ss_usuario` ON ((`proyectos`.`proy_usr_gerente_fk` = `ss_usuario`.`usu_id`)));


INSERT INTO `siges`.`ss_configuraciones` (`id`,`cnf_codigo`,`cnf_descripcion`,`cnf_valor`,`cnf_protegido`,`cnf_html`,`cnf_ult_usuario`,`cnf_ult_mod`,`cnf_ult_origen`,`cnf_version`) 
VALUES (20, 'CON_CONTROL_ACCESO', 'Si se usa el control de acceso de Agesic o no', 'false', NULL, NULL, NULL, now(), NULL, NULL);


ALTER TABLE `siges`.`proy_indices` 
ADD COLUMN `proyind_porc_peso_total` DOUBLE(11,2) NULL DEFAULT NULL AFTER `proyind_periodo_fin`;

-- Hasta acá copiado en Siges 8/7/2014.

INSERT INTO siges.mails_template (mail_tmp_cod, mail_tmp_org_fk,mail_tmp_asunto,mail_tmp_mensaje)
VALUES ('MAIL_CAMBIO_CONTRASENIA', 1, 'Cambio de contraseña en SIGES', 'Estimado #NOMBRE#, se ha cambiado su contraseña en SIGES por #CONTRASENIA#');

ALTER TABLE proy_sitact_historico ADD COLUMN version INTEGER ;
UPDATE proy_sitact_historico SET version=0 where version IS NULL and proy_sitact_hist_pk>0;
ALTER TABLE riesgos ADD COLUMN version INTEGER ;
UPDATE riesgos SET version=0 where version IS NULL and risk_pk>0;
ALTER TABLE proy_replanificacion ADD COLUMN version INTEGER ;
UPDATE proy_replanificacion SET version=0 where version IS NULL and proyreplan_pk>0;


CREATE TABLE `siges`.`productos` (
  `prod_pk` INT(11) NOT NULL,
  `prod_peso` INT(11) NOT NULL,
  `prod_und_medida` VARCHAR(45) NOT NULL,
  `prod_ent_fk` INT(11) NOT NULL,
  PRIMARY KEY (`prod_pk`),
  INDEX `prod_ent_fk_idx` (`prod_ent_fk` ASC),
  CONSTRAINT `prod_ent_fk`
    FOREIGN KEY (`prod_ent_fk`)
    REFERENCES `siges`.`entregables` (`ent_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `siges`.`prod_mes` (
  `prodmes_pk` INT(11) NOT NULL,
  `prodmes_prod_fk` INT(11) NOT NULL,
  `prodmes_mes` SMALLINT NOT NULL,
  `prodmes_anio` SMALLINT NOT NULL,
  `prodmes_plan` INT(11) NOT NULL,
  `prodmes_real` INT(11) NULL,
  `prodmes_acu_plan` INT(11) NOT NULL,
  `prodmes_acu_real` INT(11) NULL,
  PRIMARY KEY (`prodmes_pk`),
  INDEX `prodmes_prod_fk_idx` (`prodmes_prod_fk` ASC),
  CONSTRAINT `prodmes_prod_fk`
    FOREIGN KEY (`prodmes_prod_fk`)
    REFERENCES `siges`.`productos` (`prod_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


ALTER TABLE `siges`.`doc_file` 
ADD INDEX `docfile_doc_fk` (`docfile_doc_fk` ASC);
ALTER TABLE `siges`.`ss_usuario` 
ADD INDEX `usu_correo_electronico_idx` (`usu_correo_electronico` ASC);

-- Hasta acá copiado en Siges 15/7/2014.

CREATE TABLE `siges`.`tipo_leccion` (
  `tipolec_pk` INT NOT NULL AUTO_INCREMENT,
  `tipolec_nombre` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`tipolec_pk`));

CREATE TABLE `siges`.`area_conocimiento` (
  `con_pk` INT NOT NULL AUTO_INCREMENT,
  `con_nombre` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`con_pk`));

CREATE TABLE `siges`.`lecc_aprendidas` (
  `lecapr_pk` INT(11) NOT NULL,
  `lecapr_proy_fk` INT(11) NOT NULL,
  `lecapr_tipo_fk` INT(11) NOT NULL,
  `lecapr_usr_fk` INT(11) NOT NULL,
  `lecapr_fecha` DATE NOT NULL,
  `lecapr_desc` VARCHAR(3000) NOT NULL,
  PRIMARY KEY (`lecapr_pk`),
  INDEX `lecapr_proy_fk_idx` (`lecapr_proy_fk` ASC),
  INDEX `lecapr_tipo_fk_idx` (`lecapr_tipo_fk` ASC),
  INDEX `lecapr_usr_fk_idx` (`lecapr_usr_fk` ASC),
  CONSTRAINT `lecapr_proy_fk`
    FOREIGN KEY (`lecapr_proy_fk`)
    REFERENCES `siges`.`proyectos` (`proy_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `lecapr_tipo_fk`
    FOREIGN KEY (`lecapr_tipo_fk`)
    REFERENCES `siges`.`tipo_leccion` (`tipolec_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `lecapr_usr_fk`
    FOREIGN KEY (`lecapr_usr_fk`)
    REFERENCES `siges`.`ss_usuario` (`usu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `siges`.`lecapr_areacon` (
  `lecaprcon_con_fk` INT(11) NOT NULL,
  `lecaprcon_lecapr_fk` INT(11) NOT NULL,
  PRIMARY KEY (`lecaprcon_con_fk`, `lecaprcon_lecapr_fk`),
  INDEX `lecaprcon_lecapr_fk_idx` (`lecaprcon_lecapr_fk` ASC),
  CONSTRAINT `lecaprcon_con_fk`
    FOREIGN KEY (`lecaprcon_con_fk`)
    REFERENCES `siges`.`area_conocimiento` (`con_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `lecaprcon_lecapr_fk`
    FOREIGN KEY (`lecaprcon_lecapr_fk`)
    REFERENCES `siges`.`lecc_aprendidas` (`lecapr_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `siges`.`programas` 
ADD COLUMN `prog_id_migrado` INT(11) NULL AFTER `prog_ult_origen`;

ALTER TABLE `siges`.`proyectos` 
ADD COLUMN `proy_id_migrado` INT(11) NULL AFTER `proy_fecha_est_act`;

ALTER TABLE `siges`.`productos` 
ADD COLUMN `prod_fecha_crea` DATE NOT NULL AFTER `prod_ent_fk`,
ADD COLUMN `prod_ult_mod` DATE NULL DEFAULT NULL AFTER `prod_fecha_crea`;

ALTER TABLE `siges`.`prod_mes` 
CHANGE COLUMN `prodmes_plan` `prodmes_plan` DECIMAL(11,2) NOT NULL ,
CHANGE COLUMN `prodmes_real` `prodmes_real` DECIMAL(11,2) NULL ,
CHANGE COLUMN `prodmes_acu_plan` `prodmes_acu_plan` DECIMAL(11,2) NOT NULL ,
CHANGE COLUMN `prodmes_acu_real` `prodmes_acu_real` DECIMAL(11,2) NULL ;

ALTER TABLE `siges`.`prod_mes` 
DROP FOREIGN KEY `prodmes_prod_fk`;

ALTER TABLE `siges`.`productos` 
CHANGE COLUMN `prod_pk` `prod_pk` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `siges`.`prod_mes` 
ADD CONSTRAINT `prodmes_prod_fk`
  FOREIGN KEY (`prodmes_prod_fk`)
  REFERENCES `siges`.`productos` (`prod_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `siges`.`prod_mes` 
CHANGE COLUMN `prodmes_pk` `prodmes_pk` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `siges`.`entregables` 
CHANGE COLUMN `ent_horas_estimadas` `ent_horas_estimadas` VARCHAR(15) NULL DEFAULT NULL ;

-- Hasta acá copiado en Siges 17/7/2014.

ALTER TABLE `siges`.`documentos` 
ADD COLUMN `docs_aprobado` TINYINT(1) NULL DEFAULT NULL AFTER `docs_docfile_pk`;

ALTER TABLE `siges`.`productos` 
ADD COLUMN `prod_acumulado` TINYINT(1) NULL DEFAULT NULL AFTER `prod_ult_mod`;

ALTER TABLE `siges`.`productos` 
ADD COLUMN `prod_desc` VARCHAR(2000) NULL DEFAULT NULL AFTER `prod_acumulado`;

-- Hasta acá copiado en Siges 24/7/2014.
--- TAG VERSION 1.0 -----------------------------------------------------------------------

CREATE TABLE `prog_indices_pre` (
  `progindpre_pk` int(11) NOT NULL AUTO_INCREMENT,
  `progindpre_progind_fk` int(11) NOT NULL,
  `progindpre_mon_fk` int(11) NOT NULL,
  `progindpre_total` double(11,2) DEFAULT NULL,
  `progindpre_est_pre` int(11) DEFAULT NULL,
  PRIMARY KEY (`progindpre_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lecapr_areacon` ;
DROP TABLE IF EXISTS `lecc_aprendidas` ;
DROP TABLE IF EXISTS `area_conocimiento`;
DROP TABLE IF EXISTS `tipo_leccion`;

CREATE TABLE `tipo_leccion` (
  `tipolec_pk` int(11) NOT NULL AUTO_INCREMENT,
  `tipolec_nombre` varchar(150) NOT NULL,
  PRIMARY KEY (`tipolec_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


CREATE TABLE `area_conocimiento` (
  `con_pk` int(11) NOT NULL AUTO_INCREMENT,
  `con_nombre` varchar(150) NOT NULL,
  `con_org_fk` int(11) NOT NULL,
  `con_padre_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`con_pk`),
  KEY `con_org_fk_idx` (`con_org_fk`),
  KEY `con_padre_fk_idx` (`con_padre_fk`),
  CONSTRAINT `con_padre_fk` FOREIGN KEY (`con_padre_fk`) REFERENCES `area_conocimiento` (`con_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `con_org_fk` FOREIGN KEY (`con_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


CREATE TABLE `lecc_aprendidas` (
  `lecapr_pk` int(11) NOT NULL AUTO_INCREMENT,
  `lecapr_proy_fk` int(11) NOT NULL,
  `lecapr_tipo_fk` int(11) NOT NULL,
  `lecapr_usr_fk` int(11) NOT NULL,
  `lecapr_org_fk` int(11) NOT NULL,
  `lecapr_fecha` date NOT NULL,
  `lecapr_desc` varchar(3000) NOT NULL,
  PRIMARY KEY (`lecapr_pk`),
  KEY `lecapr_proy_fk_idx` (`lecapr_proy_fk`),
  KEY `lecapr_tipo_fk_idx` (`lecapr_tipo_fk`),
  KEY `lecapr_usr_fk_idx` (`lecapr_usr_fk`),
  KEY `lecapr_org_fk_idx` (`lecapr_org_fk`),
  CONSTRAINT `lecapr_org_fk` FOREIGN KEY (`lecapr_org_fk`) REFERENCES `organismos` (`org_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecapr_proy_fk` FOREIGN KEY (`lecapr_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecapr_tipo_fk` FOREIGN KEY (`lecapr_tipo_fk`) REFERENCES `tipo_leccion` (`tipolec_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecapr_usr_fk` FOREIGN KEY (`lecapr_usr_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


CREATE TABLE `lecapr_areacon` (
  `lecaprcon_con_fk` int(11) NOT NULL,
  `lecaprcon_lecapr_fk` int(11) NOT NULL,
  PRIMARY KEY (`lecaprcon_con_fk`,`lecaprcon_lecapr_fk`),
  KEY `lecaprcon_lecapr_fk_idx` (`lecaprcon_lecapr_fk`),
  CONSTRAINT `lecaprcon_lecapr_fk` FOREIGN KEY (`lecaprcon_lecapr_fk`) REFERENCES `lecc_aprendidas` (`lecapr_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecaprcon_con_fk` FOREIGN KEY (`lecaprcon_con_fk`) REFERENCES `area_conocimiento` (`con_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `siges`.`tipo_leccion` (`tipolec_nombre`) VALUES ('A evitar');
INSERT INTO `siges`.`tipo_leccion` (`tipolec_nombre`) VALUES ('A repetir');

-- Hasta acá copiado en Siges 31/7/2014.

ALTER TABLE `siges`.`lecc_aprendidas` 
DROP FOREIGN KEY `lecapr_proy_fk`;
ALTER TABLE `siges`.`lecc_aprendidas` 
CHANGE COLUMN `lecapr_proy_fk` `lecapr_proy_fk` INT(11) NULL ;
ALTER TABLE `siges`.`lecc_aprendidas` 
ADD CONSTRAINT `lecapr_proy_fk`
  FOREIGN KEY (`lecapr_proy_fk`)
  REFERENCES `siges`.`proyectos` (`proy_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `siges`.`lecc_aprendidas` 
ADD COLUMN `lecapr_activo` TINYINT(1) NULL DEFAULT NULL AFTER `lecapr_desc`;

-- Hasta acá copiado en Siges 4/8/2014.

ALTER TABLE `siges`.`adquisicion` 
ADD COLUMN `adq_proc_compra` VARCHAR(20) NULL DEFAULT NULL AFTER `adq_moneda_fk`,
ADD COLUMN `adq_proc_compra_grp` VARCHAR(20) NULL DEFAULT NULL AFTER `adq_proc_compra`;

ALTER TABLE `siges`.`pagos` 
ADD COLUMN `pag_txt_referencia` VARCHAR(20) NULL DEFAULT NULL AFTER `pag_importe_real`,
ADD COLUMN `pag_cinfirmar` TINYINT(1) NULL DEFAULT NULL AFTER `pag_txt_referencia`;
ALTER TABLE `siges`.`pagos` 
CHANGE COLUMN `pag_cinfirmar` `pag_confirmar` TINYINT(1) NULL DEFAULT NULL ;


-- Hasta acá copiado en Siges 6/8/2014.

ALTER TABLE `siges`.`proy_sitact_historico` 
ADD COLUMN `proy_sitact_usu_fk` INT(11) NULL AFTER `proy_sitact_proy_fk`;

ALTER TABLE `siges`.`proy_sitact_historico` 
CHANGE COLUMN `proy_sitact_desc` `proy_sitact_desc` VARCHAR(4000) NULL DEFAULT NULL ;

ALTER TABLE `siges`.`proyectos` 
CHANGE COLUMN `proy_situacion_actual` `proy_situacion_actual` VARCHAR(4000) NULL DEFAULT NULL ;

-- Hasta acá copiado en Siges 8/8/2014.

INSERT INTO `siges`.`estados` (`est_pk`, `est_nombre`) VALUES ('0', 'No Exigido');
UPDATE `siges`.`estados` SET `est_pk`='0' WHERE `est_nombre`='No Exigido' and est_pk<1000;

-- Hasta acá copiado en Siges 11/8/2014.

ALTER TABLE `siges`.`proy_replanificacion` 
ADD COLUMN `proyreplan_activo` TINYINT(1) NULL DEFAULT NULL AFTER `proyreplan_historial`;
update siges.proy_replanificacion set proyreplan_activo=0 where proyreplan_activo is null and proyreplan_pk>0;

-- Hasta acá copiado en Siges 14/8/2014.

INSERT INTO `siges`.`ss_configuraciones` (`cnf_codigo`, `cnf_descripcion`, `cnf_valor`, `cnf_ult_mod`, `cnf_version`) VALUES ('PRODUCTO_INDICE_LIMITE_AMARILLO', 'Limite semaforo amarillo para Productos', '10', '2014-08-14', '0');
INSERT INTO `siges`.`ss_configuraciones` (`cnf_codigo`, `cnf_descripcion`, `cnf_valor`, `cnf_ult_mod`, `cnf_version`) VALUES ('PRODUCTO_INDICE_LIMITE_ROJO', 'Limite semaforo rojo para Productos', '20', '2014-08-14', '0');

CREATE TABLE `siges`.`ent_hist_linea_base` (
  `enthist_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `enthist_ent_fk` INT(11) NOT NULL,
  `enthist_inicio_linea_base` BIGINT(20) NOT NULL,
  `enthist_fin_linea_base` BIGINT(20) NULL,
  PRIMARY KEY (`enthist_pk`));

ALTER TABLE `siges`.`ent_hist_linea_base` 
ADD INDEX `enthist_ent_fk_idx` (`enthist_ent_fk` ASC);
ALTER TABLE `siges`.`ent_hist_linea_base` 
ADD CONSTRAINT `enthist_ent_fk`
  FOREIGN KEY (`enthist_ent_fk`)
  REFERENCES `siges`.`entregables` (`ent_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `siges`.`ent_hist_linea_base` 
ADD COLUMN `enthist_fecha` DATE NOT NULL AFTER `enthist_fin_linea_base`;

-- Hasta acá copiado en Siges 18/8/2014.

DELETE FROM `siges`.`ss_configuraciones` WHERE cnf_codigo='CON_CORREO' and cnf_valor='1' and id>0;

update siges.proyectos set proy_objetivo='Sin ingresar.' where (proy_est_fk=4 or proy_est_fk=5) and (trim(proy_objetivo) ='' or proy_objetivo is null);
update siges.proyectos set proy_obj_publico='Sin ingresar.' where (proy_est_fk=4 or proy_est_fk=5) and (trim(proy_obj_publico) ='' or proy_obj_publico is null);

ALTER TABLE `siges`.`ss_configuraciones` 
ADD COLUMN `cnf_org_fk` INT(11) NULL DEFAULT NULL AFTER `id`;

update siges.ss_configuraciones set cnf_org_fk=1 where cnf_org_fk is null and id >0;


-- Hasta acá copiado en Siges 20/8/2014.

ALTER TABLE `siges`.`aud_ss_configuraciones` 
ADD COLUMN `cnf_org_fk` INT(11) NULL AFTER `cnf_version`;

update siges.aud_ss_configuraciones set cnf_org_fk=1 where cnf_org_fk is null and id >0;

-- Para cambiar el tamaño del campo dende se almacenan los filtros.
ALTER TABLE `siges`.`busq_filtro` 
CHANGE COLUMN `busq_filtro_xml` `busq_filtro_xml` TEXT NOT NULL ;

-- Plantillas de mails.
INSERT INTO `siges`.`mails_template` (`mail_tmp_cod`, `mail_tmp_org_fk`, `mail_tmp_asunto`, `mail_tmp_mensaje`) VALUES ('MAIL_CAMBIO_ESTADO', '1', 'Programa / Proyecto cambió de fase.', '<h2>Cambio de Fase</h2><p>El #TIPO_PROG_PROY# #ID_PROG_PROY# "#NOMBRE_PROG_PROY#" cambió de fase a #FASE_PROG_PROY#.</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>');
INSERT INTO `siges`.`mails_template` (`mail_tmp_cod`, `mail_tmp_org_fk`, `mail_tmp_asunto`, `mail_tmp_mensaje`) VALUES ('MAIL_PROG_PROY_PENDIENTE', '1', 'Pendiente de aprobación.', '<h2>Pendiente de aprobación</h2><p>El #TIPO_PROG_PROY# #ID_PROG_PROY# "#NOMBRE_PROG_PROY#" esta pendiente de aprobación.</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>');
INSERT INTO `siges`.`mails_template` (`mail_tmp_cod`, `mail_tmp_org_fk`, `mail_tmp_asunto`, `mail_tmp_mensaje`) VALUES ('MAIL_NVO_USUARIO', '1', 'Usuario SIGES.', '<h2>Usuario creado</h2><p>Se ha creado el usuario #USU_MAIL# cuya clave es #USU_PASSWORD#, para ingresar al sistema de SIGES.</p><p>Sistema SIGES.</p>');

ALTER TABLE `siges`.`organi_int_prove` 
ADD COLUMN `orga_org_fk` INT(11) NOT NULL AFTER `orga_ambito`;

update siges.organi_int_prove set orga_org_fk=(SELECT org_pk FROM siges.organismos order by org_pk limit 1) where orga_org_fk is null or orga_org_fk=0 and orga_pk>0;

ALTER TABLE `siges`.`organi_int_prove` 
ADD INDEX `orga_org_fk_idx` (`orga_org_fk` ASC);
ALTER TABLE `siges`.`organi_int_prove` 
ADD CONSTRAINT `orga_org_fk`
  FOREIGN KEY (`orga_org_fk`)
  REFERENCES `siges`.`organismos` (`org_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

-- Hasta acá en Siges v1.4-20140903

ALTER TABLE `siges`.`ss_usu_ofi_roles` 
ADD COLUMN `usu_ofi_roles_usu_area` INT(11) NULL DEFAULT NULL AFTER `usu_ofi_roles_usuario`;

ALTER TABLE `siges`.`ss_usu_ofi_roles` 
ADD INDEX `usu_ofi_roles_usu_area_idx` (`usu_ofi_roles_usu_area` ASC);
ALTER TABLE `siges`.`ss_usu_ofi_roles` 
ADD CONSTRAINT `usu_ofi_roles_usu_area`
  FOREIGN KEY (`usu_ofi_roles_usu_area`)
  REFERENCES `siges`.`areas` (`area_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

use siges;
update ss_usu_ofi_roles r set r.usu_ofi_roles_usu_area = (
SELECT u.usu_area_org FROM ss_usuario u 
where r.usu_ofi_roles_usuario=u.usu_id 
and r.usu_ofi_roles_oficina=1 
and u.usu_area_org is not null)
where r.usu_ofi_roles_id>0;

-- Hasta acá en Siges v1.4-20140904

CREATE TABLE `siges`.`notificacion` (
  `not_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `not_desc` VARCHAR(245) NOT NULL,
  `not_valor` INT(11) NULL,
  `not_gerente_adjunto` TINYINT NULL DEFAULT NULL,
  `not_pmof` TINYINT NULL DEFAULT NULL,
  `not_pmot` TINYINT NULL DEFAULT NULL,
  `not_sponsor` TINYINT NULL DEFAULT NULL,
  `not_msg` VARCHAR(5000) NOT NULL,
  PRIMARY KEY (`not_pk`));

CREATE TABLE `siges`.`notificacion_instancia` (
  `notinst_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `notinst_not_fk` INT(11) NOT NULL,
  `notinst_proy_fk` INT(11) NOT NULL,
  `notinst_gerente_adjunto` TINYINT NULL DEFAULT NULL,
  `notinst_pmof` TINYINT NULL DEFAULT NULL,
  `notinst_pmot` TINYINT NULL DEFAULT NULL,
  `notinst_sponsor` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`notinst_pk`),
  INDEX `notinst_not_fk_idx` (`notinst_not_fk` ASC),
  INDEX `notinst_proy_fk_idx` (`notinst_proy_fk` ASC),
  CONSTRAINT `notinst_not_fk`
    FOREIGN KEY (`notinst_not_fk`)
    REFERENCES `siges`.`notificacion` (`not_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `notinst_proy_fk`
    FOREIGN KEY (`notinst_proy_fk`)
    REFERENCES `siges`.`proyectos` (`proy_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `siges`.`proy_replanificacion` 
CHANGE COLUMN `proyreplan_desc` `proyreplan_desc` VARCHAR(5000) NOT NULL ;

-- Hasta acá en Siges v1.4-20140909

use siges;
CREATE TABLE participantes (
  part_pk INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  part_usu_fk INTEGER NOT NULL,
  part_proy_fk INTEGER NOT NULL,
  part_activo TINYINT NOT NULL DEFAULT 1,
  UNIQUE (part_usu_fk, part_proy_fk),
  CONSTRAINT `fk_participantes_usuarios1` FOREIGN KEY (`part_usu_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_participantes_proyectos1` FOREIGN KEY (`part_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE registros_horas (
  rh_pk INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  rh_usu_fk INTEGER NOT NULL,
  rh_proy_fk INTEGER NOT NULL,
  rh_ent_fk INTEGER NOT NULL,
  rh_fecha DATE NOT NULL,
  rh_horas FLOAT NOT NULL,
  rh_comentario VARCHAR(4000),
  rh_aprobado TINYINT DEFAULT NULL,
  CONSTRAINT `fk_registrohoras_usuarios1` FOREIGN KEY (`rh_usu_fk`) REFERENCES `ss_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_registrohoras_proyectos1` FOREIGN KEY (`rh_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_registrohoras_entregables1` FOREIGN KEY (`rh_ent_fk`) REFERENCES `entregables` (`ent_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

update tipo_documento_instancia tdi set tipodoc_inst_resum_ejecutivo=(
SELECT tipodoc_resum_ejecutivo 
FROM tipo_documento td
where tipodoc_resum_ejecutivo=1 
and tdi.tipodoc_inst_tipodoc_fk=td.tipdoc_pk
) where tdi.tipodoc_inst_pk>0;

ALTER TABLE `siges`.`notificacion` 
ADD COLUMN `not_org_fk` INT(11) NOT NULL AFTER `not_pk`;

-- Hasta acá en Siges v1.5-20140910

use siges;
update ss_usuario set usu_administrador=0 where usu_administrador is null and usu_id>0;

CREATE TABLE `plantilla_cronograma` (
  `p_crono_pk` int(11) NOT NULL AUTO_INCREMENT,
  `p_crono_nombre` varchar(845) DEFAULT NULL,
  `p_crono_org_fk` int(11) DEFAULT NULL,
  `activo` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`p_crono_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `plantilla_entregables` (
  `p_entregables_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_entregables_nombre` varchar(1000) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

ALTER TABLE `plantilla_entregables` 
ADD COLUMN `p_entregable_nivel` INT(11) NULL DEFAULT NULL AFTER `p_entregables_nombre`;

ALTER TABLE `siges`.`ent_hist_linea_base` 
CHANGE COLUMN `enthist_fecha` `enthist_fecha` DATE NOT NULL AFTER `enthist_ent_fk`,
ADD COLUMN `enthist_duracion` INT(11) NULL AFTER `enthist_fin_linea_base`;

-- Hasta acá en Siges v1.5-20140922

ALTER TABLE `siges`.`notificacion` 
ADD COLUMN `not_cod` VARCHAR(45) NOT NULL AFTER `not_org_fk`;

CREATE TABLE `siges`.`notificacion_envio` (
  `notenv_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `notenv_fecha` DATE NOT NULL,
  `notenv_proy_fk` INT(11) NOT NULL,
  `notenv_not_cod` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`notenv_pk`));

-- Hasta acá en Siges v1.5-20141003

use siges;

ALTER TABLE `ss_usu_ofi_roles` 
ADD COLUMN `usu_ofi_roles_activo` TINYINT(1) NULL DEFAULT NULL AFTER `usu_ofi_roles_usu_area`;
ALTER TABLE `aud_ss_usu_ofi_roles` 
ADD COLUMN `usu_ofi_roles_activo` TINYINT(1) NULL DEFAULT NULL;

update ss_usu_ofi_roles set usu_ofi_roles_activo=(select usu_vigente from ss_usuario where usu_id=usu_ofi_roles_usuario) where usu_ofi_roles_id>0;

-- Hasta acá en Siges v1.5-20141010

use siges;

ALTER TABLE `ent_hist_linea_base` 
ADD COLUMN `enthist_replan_fk` INT(11) NULL DEFAULT NULL AFTER `enthist_duracion`;

ALTER TABLE `ambito` 
ADD COLUMN `amb_org_fk` INT(11) NOT NULL AFTER `amb_nombre`;
update ambito set amb_org_fk=(SELECT org_pk FROM organismos ORDER BY org_pk ASC limit 1) where amb_pk>0;

ALTER TABLE `participantes` 
ADD COLUMN `part_horas_plan` DECIMAL(11,2) NULL DEFAULT NULL AFTER `part_proy_fk`;

CREATE TABLE `valor_hora` (
  `val_hor_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `val_hor_usu_fk` INT(11) NOT NULL,
  `val_hor_org_fk` INT(11) NOT NULL,
  `val_hor_mon_fk` INT(11) NOT NULL,
  `val_hor_valor` DECIMAL(11,2) NOT NULL,
  `val_hor_anio` INT(11) NOT NULL,
  PRIMARY KEY (`val_hor_pk`));

ALTER TABLE `valor_hora` 
ADD INDEX `val_hor_usu_fk_idx` (`val_hor_usu_fk` ASC),
ADD INDEX `val_hor_org_fk_idx` (`val_hor_org_fk` ASC),
ADD INDEX `val_hor_mon_fk_idx` (`val_hor_mon_fk` ASC);
ALTER TABLE `valor_hora` 
ADD CONSTRAINT `val_hor_usu_fk`
  FOREIGN KEY (`val_hor_usu_fk`)
  REFERENCES `ss_usuario` (`usu_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `val_hor_org_fk`
  FOREIGN KEY (`val_hor_org_fk`)
  REFERENCES `organismos` (`org_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `val_hor_mon_fk`
  FOREIGN KEY (`val_hor_mon_fk`)
  REFERENCES `moneda` (`mon_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `participantes` 
ADD COLUMN `part_ent_fk` INT(11) NULL AFTER `part_proy_fk`,
ADD INDEX `part_ent_fk_idx` (`part_ent_fk` ASC);
ALTER TABLE `participantes` 
ADD CONSTRAINT `part_ent_fk`
  FOREIGN KEY (`part_ent_fk`)
  REFERENCES `entregables` (`ent_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `tipo_gasto` (
  `tipogas_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `tipogas_nombre` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`tipogas_pk`));

CREATE TABLE `gastos` (
  `gas_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `gas_proy_fk` INT(11) NOT NULL,
  `gas_tipo_fk` INT(11) NOT NULL,
  `gas_usu_fk` INT(11) NOT NULL,
  `gas_mon_fk` INT(11) NOT NULL,
  `gas_importe` DECIMAL(11,2) NOT NULL,
  `gas_fecha` DATE NOT NULL,
  `gas_obs` VARCHAR(4000) NOT NULL,
  PRIMARY KEY (`gas_pk`),
  INDEX `gas_proy_fk_idx` (`gas_proy_fk` ASC),
  INDEX `gas_tipo_fk_idx` (`gas_tipo_fk` ASC),
  INDEX `gas_usu_fk_idx` (`gas_usu_fk` ASC),
  INDEX `gas_mon_fk_idx` (`gas_mon_fk` ASC),
  CONSTRAINT `gas_proy_fk`
    FOREIGN KEY (`gas_proy_fk`)
    REFERENCES `proyectos` (`proy_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `gas_tipo_fk`
    FOREIGN KEY (`gas_tipo_fk`)
    REFERENCES `tipo_gasto` (`tipogas_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `gas_usu_fk`
      FOREIGN KEY (`gas_usu_fk`)
      REFERENCES `ss_usuario` (`usu_id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  CONSTRAINT `gas_mon_fk`
    FOREIGN KEY (`gas_mon_fk`)
    REFERENCES `moneda` (`mon_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `tipo_gasto` 
ADD COLUMN `tipogas_org_fk` INT(11) NULL AFTER `tipogas_pk`,
ADD INDEX `tipogas_org_fk_idx` (`tipogas_org_fk` ASC);
ALTER TABLE `tipo_gasto` 
ADD CONSTRAINT `tipogas_org_fk`
  FOREIGN KEY (`tipogas_org_fk`)
  REFERENCES `organismos` (`org_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `siges`.`gastos` 
ADD COLUMN `gas_aprobado` TINYINT(1) NULL DEFAULT NULL AFTER `gas_obs`;

ALTER TABLE `siges`.`registros_horas` 
CHANGE COLUMN `rh_horas` `rh_horas` DECIMAL(11,2) NOT NULL ;

-- Hasta acá en Siges v2.0-20141104

CREATE TABLE `siges`.`devengado` (
  `dev_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `dev_adq_fk` INT(11) NOT NULL,
  `dev_mes` SMALLINT(6) NOT NULL,
  `dev_anio` SMALLINT(6) NOT NULL,
  `dev_plan` DECIMAL(11,2) NULL DEFAULT NULL,
  `dev_real` DECIMAL(11,2) NULL DEFAULT NULL,
  PRIMARY KEY (`dev_pk`));

-- Hasta acá en Siges v2.0-20141128

delete from siges.notificacion_instancia where notinst_pk > 0;

ALTER TABLE `siges`.`ss_rol` 
ADD COLUMN `rol_label` VARCHAR(150) NOT NULL AFTER `rol_nombre`;

ALTER TABLE `siges`.`aud_ss_rol`  ADD COLUMN `rol_label` VARCHAR(150) NULL;

-- Hasta acá en Siges v2.0-20150203

ALTER TABLE `siges`.`organi_int_prove` 
ADD COLUMN `orga_amb_fk` INT(11) NULL DEFAULT NULL AFTER `orga_org_fk`,
ADD INDEX `orga_amb_fk_idx` (`orga_amb_fk` ASC);
ALTER TABLE `siges`.`organi_int_prove`
ADD CONSTRAINT `orga_amb_fk`
  FOREIGN KEY (`orga_amb_fk`)
  REFERENCES `siges`.`ambito` (`amb_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

-- Hasta acá en Siges v2.1-20150323

CREATE TABLE `siges`.`temas_calidad` (
  `tca_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `tca_nombre` VARCHAR(100) NOT NULL,
  `tca_org_fk` INT(11) NOT NULL,
  PRIMARY KEY (`tca_pk`),
  INDEX `tca_org_fk_idx` (`tca_org_fk` ASC),
  CONSTRAINT `tca_org_fk`
    FOREIGN KEY (`tca_org_fk`)
    REFERENCES `siges`.`organismos` (`org_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `siges`.`calidad` (
  `cal_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `cal_peso` INT(11) NOT NULL,
  `cal_valor` DOUBLE NOT NULL,
  `cal_actualizacion` DATE NOT NULL,
  `cal_ent_fk` INT(11) NULL,
  `cal_prod_fk` INT(11) NULL,
  `cal_tca_fk` INT(11) NULL,
  PRIMARY KEY (`cal_pk`),
  INDEX `cal_ent_fk_idx` (`cal_ent_fk` ASC),
  INDEX `cal_prod_fk_idx` (`cal_prod_fk` ASC),
  INDEX `cal_tca_fk_idx` (`cal_tca_fk` ASC),
  CONSTRAINT `cal_ent_fk`
    FOREIGN KEY (`cal_ent_fk`)
    REFERENCES `siges`.`entregables` (`ent_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cal_prod_fk`
    FOREIGN KEY (`cal_prod_fk`)
    REFERENCES `siges`.`productos` (`prod_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cal_tca_fk`
    FOREIGN KEY (`cal_tca_fk`)
    REFERENCES `siges`.`temas_calidad` (`tca_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `siges`.`valor_calidad_codigos` (
  `vca_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `vca_codigo` VARCHAR(45) NOT NULL,
  `vca_nombre` VARCHAR(100) NOT NULL,
  `vca_habilitado` TINYINT(1) NOT NULL,
  PRIMARY KEY (`vca_pk`),
  INDEX `vca_codigo` (`vca_codigo` ASC));

INSERT INTO `siges`.`valor_calidad_codigos` (`vca_codigo`, `vca_nombre`, `vca_habilitado`) VALUES ('baja', '0', '1');
INSERT INTO `siges`.`valor_calidad_codigos` (`vca_codigo`, `vca_nombre`, `vca_habilitado`) VALUES ('media', '0.5', '1');
INSERT INTO `siges`.`valor_calidad_codigos` (`vca_codigo`, `vca_nombre`, `vca_habilitado`) VALUES ('alta', '1', '1');
INSERT INTO `siges`.`valor_calidad_codigos` (`vca_codigo`, `vca_nombre`, `vca_habilitado`) VALUES ('no_corresponde', 'No Corresponde', '1');
INSERT INTO `siges`.`valor_calidad_codigos` (`vca_codigo`, `vca_nombre`, `vca_habilitado`) VALUES ('pendiente', 'Pendiente de Cargar', '1');

ALTER TABLE `siges`.`calidad` 
CHANGE COLUMN `cal_valor` `cal_valor` INT(11) NOT NULL ,
ADD INDEX `cal_valor_idx` (`cal_valor` ASC);
ALTER TABLE `siges`.`calidad` 
ADD CONSTRAINT `cal_valor`
  FOREIGN KEY (`cal_valor`)
  REFERENCES `siges`.`valor_calidad_codigos` (`vca_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `siges`.`ss_configuraciones` 
ADD INDEX `cnf_org_fk` (`cnf_org_fk` ASC),
ADD INDEX `cnf_codigo` (`cnf_codigo` ASC);

ALTER TABLE `siges`.`calidad` 
DROP FOREIGN KEY `cal_valor`;
ALTER TABLE `siges`.`calidad` 
CHANGE COLUMN `cal_valor` `cal_vca_fk` INT(11) NOT NULL ;
ALTER TABLE `siges`.`calidad` 
ADD CONSTRAINT `cal_vca_fk`
  FOREIGN KEY (`cal_vca_fk`)
  REFERENCES `siges`.`valor_calidad_codigos` (`vca_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `siges`.`calidad` 
ADD COLUMN `cal_proy_fk` INT(11) NOT NULL AFTER `cal_tca_fk`,
ADD INDEX `cal_proy_fk_idx` (`cal_proy_fk` ASC);
ALTER TABLE `siges`.`calidad` 
ADD CONSTRAINT `cal_proy_fk`
  FOREIGN KEY (`cal_proy_fk`)
  REFERENCES `siges`.`proyectos` (`proy_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `siges`.`calidad` 
ADD COLUMN `cal_tipo` INT(11) NOT NULL AFTER `cal_actualizacion`,
ADD INDEX `cal_tipo` (`cal_tipo` ASC);

ALTER TABLE `siges`.`prog_indices` 
ADD COLUMN `progind_cal_ind` DOUBLE(11,2) NULL DEFAULT NULL AFTER `progind_proy_actualizacion`,
ADD COLUMN `progind_cal_pend` INT(11) NULL DEFAULT NULL AFTER `progind_cal_ind`;

ALTER TABLE `siges`.`proy_indices` 
ADD COLUMN `proyind_cal_ind` DOUBLE(11,2) NULL DEFAULT NULL AFTER `proyind_porc_peso_total`,
ADD COLUMN `proyind_cal_pend` INT(11) NULL DEFAULT NULL AFTER `proyind_cal_ind`;

ALTER TABLE `siges`.`ss_usuario` 
ADD COLUMN `usu_aprob_facturas` TINYINT(1) NULL DEFAULT NULL AFTER `usu_vigente`;

ALTER TABLE `siges`.`aud_ss_usuario` 
ADD COLUMN `usu_aprob_facturas` TINYINT(1) NULL DEFAULT NULL AFTER `usu_vigente`;

-- Hasta acá Desarrollo CND v2.2-20150409

-- Desde acá en adelante es v2.5-1

use siges;

ALTER TABLE `estados` 
ADD COLUMN `est_codigo` VARCHAR(150) NOT NULL AFTER `est_pk`,
ADD COLUMN `est_label` VARCHAR(150) NOT NULL AFTER `est_nombre`;

ALTER TABLE `ss_rol` 
ADD COLUMN `rol_label` VARCHAR(150) NOT NULL AFTER `rol_nombre`;

ALTER TABLE `aud_ss_rol` 
ADD COLUMN `rol_label` VARCHAR(150) NOT NULL AFTER `rol_nombre`;

ALTER TABLE `siges`.`riesgos` 
ADD COLUMN `risk_ent_fk` INT(11) NULL DEFAULT NULL AFTER `risk_impacto`,
ADD INDEX `risk_ent_fk_idx` (`risk_ent_fk` ASC);
ALTER TABLE `siges`.`riesgos` 
ADD CONSTRAINT `risk_ent_fk`
  FOREIGN KEY (`risk_ent_fk`)
  REFERENCES `siges`.`entregables` (`ent_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `siges`.`interesados` 
ADD COLUMN `int_ent_fk` INT(11) NULL DEFAULT NULL AFTER `int_orga_fk`,
ADD INDEX `int_ent_fk_idx` (`int_ent_fk` ASC);
ALTER TABLE `siges`.`interesados` 
ADD CONSTRAINT `int_ent_fk`
  FOREIGN KEY (`int_ent_fk`)
  REFERENCES `siges`.`entregables` (`ent_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `siges`.`documentos` 
ADD COLUMN `docs_pago_fk` INT(11) NULL DEFAULT NULL AFTER `docs_aprobado`,
ADD INDEX `docs_pago_fk_idx` (`docs_pago_fk` ASC);
ALTER TABLE `siges`.`documentos` 
ADD CONSTRAINT `docs_pago_fk`
  FOREIGN KEY (`docs_pago_fk`)
  REFERENCES `siges`.`pagos` (`pag_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
-- Hasta acá en Siges v2.5-1

update ss_rol set rol_tipo_usuario=0 where rol_cod='USU_HORAS' and rol_id>0;
update ss_rol set rol_tipo_usuario=1 where rol_cod='REGISTRO_HORAS' and rol_id>0;

update ss_usu_ofi_roles 
set usu_ofi_roles_rol=(SELECT rol_id FROM ss_rol where rol_cod='REGISTRO_HORAS')
where usu_ofi_roles_rol=(SELECT rol_id FROM ss_rol where rol_cod='USU_HORAS');

UPDATE `ss_rol` SET `rol_descripcion`='Externo (solo carga de horas)', `rol_nombre`='Externo (solo carga de horas)' 
WHERE `rol_cod`='REGISTRO_HORAS' and rol_id > 0;

-- Hasta acá en Siges v2.5-5

-- Desde acá en adelante es v2.6-1

ALTER TABLE `entregables` 
ADD COLUMN `ent_relevante` TINYINT(1) NULL DEFAULT NULL AFTER `ent_progreso`;

-- Desde acá en adelante es v2.6-5

ALTER TABLE `busq_filtro` 
CHANGE COLUMN `busq_filtro_xml` `busq_filtro_xml` MEDIUMTEXT NOT NULL ;

ALTER TABLE `organismos` 
CHANGE COLUMN `org_logo` `org_logo` MEDIUMBLOB NULL DEFAULT NULL ;

ALTER TABLE `pagos` 
ADD INDEX `pag_fecha_planificada_idx` (`pag_fecha_planificada` ASC),
ADD INDEX `pag_fecha_real_idx` (`pag_fecha_real` ASC),
ADD INDEX `pag_confirmar_idx` (`pag_confirmar` ASC);

ALTER TABLE `devengado` 
ADD INDEX `dev_mes_idx` (`dev_mes` ASC),
ADD INDEX `dev_anio_idx` (`dev_anio` ASC);

ALTER TABLE `registros_horas` 
ADD INDEX `rh_fecha_idx` (`rh_fecha` ASC),
ADD INDEX `rh_aprobado_idx` (`rh_aprobado` ASC);

ALTER TABLE `gastos` 
ADD INDEX `gas_fecha_idx` (`gas_fecha` ASC),
ADD INDEX `gas_aprobado_idx` (`gas_aprobado` ASC);

ALTER TABLE `calidad` 
ADD INDEX `cal_actualizacion_idx` (`cal_actualizacion` ASC);

ALTER TABLE `lecc_aprendidas` 
ADD INDEX `lecapr_fecha_idx` (`lecapr_fecha` ASC),
ADD INDEX `lecapr_activo_idx` (`lecapr_activo` ASC);

ALTER TABLE `entregables` 
ADD INDEX `ent_inicio_idx` (`ent_inicio` ASC),
ADD INDEX `ent_fin_idx` (`ent_fin` ASC),
ADD INDEX `ent_progreso_idx` (`ent_progreso` ASC);

ALTER TABLE `notificacion_envio` 
ADD INDEX `notenv_fecha_idx` (`notenv_fecha` ASC);

ALTER TABLE `organi_int_prove` 
ADD INDEX `orga_proveedor_idx` (`orga_proveedor` ASC);

ALTER TABLE `participantes` 
ADD INDEX `part_activo_idx` (`part_activo` ASC);

ALTER TABLE `prod_mes` 
ADD INDEX `prodmes_mes_idx` (`prodmes_mes` ASC),
ADD INDEX `prodmes_anio_idx` (`prodmes_anio` ASC);

ALTER TABLE `programas` 
ADD INDEX `prog_activo_idx` (`prog_activo` ASC),
ADD INDEX `prog_fecha_crea_idx` (`prog_fecha_crea` ASC),
ADD INDEX `prog_fecha_act_idx` (`prog_fecha_act` ASC);

ALTER TABLE `proy_indices` 
ADD INDEX `proyind_periodo_inicio_idx` (`proyind_periodo_inicio` ASC),
ADD INDEX `proyind_periodo_fin_idx` (`proyind_periodo_fin` ASC);

ALTER TABLE `proy_replanificacion` 
ADD INDEX `proyreplan_fecha_idx` (`proyreplan_fecha` ASC),
ADD INDEX `proyreplan_activo_idx` (`proyreplan_activo` ASC);

ALTER TABLE `proyectos` 
ADD INDEX `proy_activo_idx` (`proy_activo` ASC),
ADD INDEX `proy_fecha_crea_idx` (`proy_fecha_crea` ASC),
ADD INDEX `proy_fecha_act_idx` (`proy_fecha_act` ASC);

ALTER TABLE `riesgos` 
ADD INDEX `risk_fecha_actu_idx` (`risk_fecha_actu` ASC),
ADD INDEX `risk_fecha_limite_idx` (`risk_fecha_limite` ASC),
ADD INDEX `risk_fecha_superado_idx` (`risk_fecha_superado` ASC);

ALTER TABLE `valor_calidad_codigos` 
ADD INDEX `vca_habilitado_idx` (`vca_habilitado` ASC);

ALTER TABLE `valor_hora` 
ADD INDEX `val_hor_anio_idx` (`val_hor_anio` ASC);
