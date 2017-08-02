ALTER TABLE `adquisicion` 
DROP FOREIGN KEY `adq_prov_orga`;
ALTER TABLE `adquisicion` 
ADD INDEX `adq_prov_orga_idx` (`adq_prov_orga_fk` ASC),
DROP INDEX `adq_prov_orga_idx` ;
ALTER TABLE `adquisicion` 
ADD CONSTRAINT `adq_prov_orga`
  FOREIGN KEY (`adq_prov_orga_fk`)
  REFERENCES `organi_int_prove` (`orga_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `ss_usuario` 
CHANGE COLUMN `usu_cod` `usu_cod` VARCHAR(255) NOT NULL ,
CHANGE COLUMN `usu_correo_electronico` `usu_correo_electronico` VARCHAR(255) NOT NULL ;


INSERT INTO `ss_rol` (`rol_cod`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('ADMINIS', 'ADMINIS', 'SIGES_WEB', '0', '1');
INSERT INTO `ss_rol` (`rol_cod`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('ADMIN_TDO', 'ADMIN_TDO', 'SIGES_WEB', '0', '1');
INSERT INTO `ss_rol` (`rol_cod`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('ADMIN_ERR', 'ADMIN_ERR', 'SIGES_WEB', '0', '1');
INSERT INTO `ss_rol` (`rol_cod`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('ADM_CONF', 'ADM_CONF', 'SIGES_WEB', '0', '1');
INSERT INTO `ss_rol` (`rol_cod`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('CONF_ADD', 'CONF_ADD', 'SIGES_WEB', '0', '1');
INSERT INTO `ss_rol` (`rol_cod`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('CONF_EDIT', 'CONF_EDIT', 'SIGES_WEB', '0', '1');
INSERT INTO `ss_rol` (`rol_cod`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('CONF_HIST', 'CONF_HIST', 'SIGES_WEB', '0', '1');
INSERT INTO `ss_rol` (`rol_cod`, `rol_descripcion`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('MIGRACION', 'Acceso a la migración', 'MIGRACION', 'SIGES_WEB', '0', '1');
usu(`rol_cod`, `rol_descripcion`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('MIGRA_CALC_INDICA', 'Acceso a los callculos de la migración', 'MIGRA_CALC_INDICA', 'SIGES_WEB', '0', '1');


ALTER TABLE `organismos` 
CHANGE COLUMN `org_logo` `org_logo_nombre` VARCHAR(45) NULL DEFAULT NULL ;
ALTER TABLE `organismos` 
ADD COLUMN `org_logo` BLOB NULL DEFAULT NULL AFTER `org_direccion`;

-- Hasta acá copiado en Siges 14/5/2014.
4
CREATE TABLE `ambito` (
  `amb_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `amb_nombre` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`amb_pk`));


ALTER TABLE `tipo_documento` 
ADD COLUMN `tipodoc_resum_ejecutivo` TINYINT(1) NULL DEFAULT NULL AFTER `tipodoc_org_fk`;

UPDATE `tipo_documento` SET `tipodoc_resum_ejecutivo`='1' WHERE `tipdoc_pk`='6';
UPDATE `tipo_documento` SET `tipodoc_resum_ejecutivo`='0' WHERE `tipodoc_resum_ejecutivo` is null and tipdoc_pk>0;

-- Hasta acá copiado en Siges 29/5/2014.

update programas p set p.prog_activo=1 where p.prog_pk > 0 and p.prog_activo is null;
update proyectos p set p.proy_activo=1 where p.proy_pk > 0 and p.proy_activo is null;

ALTER TABLE `ss_usuario` 
ADD COLUMN `usu_ult_usuario` VARCHAR(255) NULL DEFAULT NULL AFTER `usu_vigente`,
ADD COLUMN `usu_ult_mod` DATETIME NULL DEFAULT NULL AFTER `usu_ult_usuario`,
ADD COLUMN `usu_ult_origen` VARCHAR(255) NULL DEFAULT NULL AFTER `usu_ult_mod`;

INSERT INTO `ss_rol` (`rol_cod`, `rol_descripcion`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('ADMIN_USU', 'Administración de los Usuarios', 'ADMIN_USU', 'SIGES_WEB', '0', '1');

ALTER TABLE `aud_ss_usuario` 
ADD COLUMN `usu_ult_usuario` VARCHAR(255) NULL DEFAULT NULL AFTER `usu_vigente`,
ADD COLUMN `usu_ult_mod` DATETIME NULL DEFAULT NULL AFTER `usu_ult_usuario`,
ADD COLUMN `usu_ult_origen` VARCHAR(255) NULL DEFAULT NULL AFTER `usu_ult_mod`;

ALTER TABLE `ss_usuario` 
ADD COLUMN `usu_celular` VARCHAR(255) NULL DEFAULT NULL AFTER `usu_telefono`;

ALTER TABLE `aud_ss_usuario` 
ADD COLUMN `usu_celular` VARCHAR(255) NULL DEFAULT NULL AFTER `usu_ult_origen`;

update ss_usuario set usu_version=1 where usu_version is null and usu_id>0;

ALTER TABLE `ss_rol` 
ADD COLUMN `rol_tipo_usuario` TINYINT(1) NULL DEFAULT NULL AFTER `rol_vigente`;

ALTER TABLE `aud_ss_rol` 
ADD COLUMN `rol_tipo_usuario` TINYINT(1) NULL DEFAULT NULL AFTER `rol_vigente`;

UPDATE `ss_rol` SET `rol_tipo_usuario`='1' WHERE `rol_id`='1';
UPDATE `ss_rol` SET `rol_tipo_usuario`='1' WHERE `rol_id`='2';
UPDATE `ss_rol` SET `rol_tipo_usuario`='1' WHERE `rol_id`='3';
UPDATE `ss_rol` SET `rol_tipo_usuario`='1' WHERE `rol_id`='4';

ALTER TABLE `ss_usuario` 
CHANGE COLUMN `usu_nro_doc` `usu_nro_doc` VARCHAR(255) NULL DEFAULT NULL ;

INSERT INTO `ss_rol` (`rol_cod`, `rol_descripcion`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`) VALUES ('ADMIN_USU', 'Administración de los Usuarios', 'ADMIN_USU', 'SIGES_WEB', '0', '1');
INSERT INTO `ss_usu_ofi_roles` (`usu_ofi_roles_origen`, `usu_ofi_roles_user_code`, `usu_ofi_roles_oficina`, `usu_ofi_roles_rol`, `usu_ofi_roles_usuario`) VALUES ('SIGES_WEB', '3', '1', '15', '3');

-- Hasta acá copiado en Siges 4/6/2014.

ALTER TABLE `proyectos` 
ADD COLUMN `proy_fecha_est_act` DATE NULL DEFAULT NULL AFTER `proy_version`;

update proyectos set proy_fecha_est_act=proy_fecha_act where proy_fecha_est_act is null and proy_pk>0;

INSERT INTO `ss_rol` (`rol_cod`, `rol_descripcion`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`, `rol_tipo_usuario`) VALUES ('USU_HORAS', 'Externo (solo carga de horas)', 'Externo (solo carga de horas)', 'SIGES_WEB', '0', '1', '1');

ALTER TABLE `ss_usuario` 
CHANGE COLUMN `usu_fecha_password` `usu_fecha_password` DATETIME NULL DEFAULT NULL ,
CHANGE COLUMN `usu_origen` `usu_origen` LONGTEXT NULL DEFAULT NULL ,
CHANGE COLUMN `usu_user_code` `usu_user_code` INT(11) NULL DEFAULT NULL ;

ALTER TABLE `ss_usuario` 
ADD COLUMN `usu_area_org` INT(11) NULL DEFAULT NULL AFTER `usu_ult_origen`,
ADD INDEX `usu_area_org_idx` (`usu_area_org` ASC);
ALTER TABLE `ss_usuario` 
ADD CONSTRAINT `usu_area_org`
  FOREIGN KEY (`usu_area_org`)
  REFERENCES `areas` (`area_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `aud_ss_usuario` 
ADD COLUMN `usu_area_org` INT(11) NULL DEFAULT NULL AFTER `usu_ult_origen`,
ADD INDEX `usu_area_org_idx` (`usu_area_org` ASC);

-- Hasta acá copiado en Siges 6/6/2014.

ALTER TABLE `programas` 
ADD COLUMN `prog_fecha_crea` DATE NULL AFTER `prog_activo`;

ALTER TABLE `proyectos` 
ADD COLUMN `proy_fecha_crea` DATE NULL AFTER `proy_activo`;

update proyectos set proy_fecha_crea=proy_fecha_act where proy_fecha_crea is null and proy_pk>0;
update programas set prog_fecha_crea=prog_fecha_act where prog_fecha_crea is null and prog_pk>0;

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


INSERT INTO `ss_configuraciones` (`cnf_codigo`, `cnf_descripcion`, `cnf_valor`, `cnf_ult_mod`, `cnf_version`) 
VALUES ('TAMANIO_MAX_LOGO_ORGANISMO', 'Tamaño maximo en bytes del logo del Organismo', '262144', '2014-06-11', '0');

INSERT INTO `ss_rol` (`rol_cod`, `rol_descripcion`, `rol_nombre`, `rol_origen`, `rol_user_code`, `rol_vigente`, `rol_tipo_usuario`) VALUES ('ADMINISTRADOR', 'Usuario Administrador de la aplicacion', 'Usuario Administrador', 'SIGES_WEB', '0', '1', '0');
UPDATE `ss_rol` SET `rol_id`='0' WHERE `rol_cod`='ADMINISTRADOR' AND `rol_id`>0;

INSERT INTO `ss_usuario` (`usu_administrador`, `usu_cod`, `usu_correo_electronico`, `usu_cuenta_bloqueada`, `usu_fecha_password`, `usu_password`, `usu_primer_apellido`, `usu_primer_nombre`, `usu_user_code`, `usu_version`, `usu_vigente`) 
VALUES ('1', 'admin', 'admin', '0', '2014-06-06 12:29:19', '21232f297a57a5a743894a0e4a801fc3', 'Administrador', 'Usuario', '0', '0', '1');
INSERT INTO `ss_usu_ofi_roles` (`usu_ofi_roles_origen`, `usu_ofi_roles_user_code`, `usu_ofi_roles_oficina`, `usu_ofi_roles_rol`, `usu_ofi_roles_usuario`) 
VALUES ('SIGES_WEB', (SELECT usu_id FROM ss_usuario where usu_correo_electronico='admin'), null, '0', (SELECT usu_id FROM ss_usuario where usu_correo_electronico='admin'));

-- INSERT INTO `organismos` (`org_pk`, `org_nombre`) VALUES ('0', 'SIGES');
-- UPDATE `organismos` SET `org_pk`='3' WHERE `org_nombre`='SIGES' and `org_pk`>0;

ALTER TABLE `ss_usu_ofi_roles` 
CHANGE COLUMN `usu_ofi_roles_oficina` `usu_ofi_roles_oficina` INT(11) NULL ;

-- Hasta acá copiado en Siges 17/6/2014.

ALTER TABLE `doc_file` 
ADD COLUMN `docfile_doc_fk` INT(11) NOT NULL AFTER `docfile_nombre`;

update doc_file set docfile_doc_fk=(SELECT docs_pk FROM documentos where docs_docfile_pk=doc_file.docfile_pk) where doc_file.docfile_pk>0;

INSERT INTO `ss_configuraciones` (`cnf_codigo`, `cnf_descripcion`, `cnf_valor`, `cnf_ult_mod`, `cnf_version`) VALUES ('MAIL_FROM', 'Dirección desde donde se envían los mails', 'sofis.pruebas2@gmail.com', '2014-06-25', '0');
INSERT INTO `ss_configuraciones` (`cnf_codigo`, `cnf_descripcion`, `cnf_valor`, `cnf_ult_mod`, `cnf_version`) VALUES ('MAIL_ENCODING', 'Encoding de los mails a enviar', 'utf8', '2014-06-25', '0');
INSERT INTO `ss_configuraciones` (`cnf_codigo`, `cnf_descripcion`, `cnf_valor`, `cnf_ult_mod`, `cnf_version`) VALUES ('CON_CORREO', 'Si se envía correo o no', 'true', '2014-06-25', '0');


CREATE TABLE `mails_template` (
  `mail_tmp_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `mail_tmp_cod` VARCHAR(45) NOT NULL,
  `mail_tmp_org_fk` INT(11) NOT NULL,
  `mail_tmp_asunto` VARCHAR(45) NOT NULL,
  `mail_tmp_mensaje` VARCHAR(5000) NOT NULL,
  PRIMARY KEY (`mail_tmp_pk`));

ALTER TABLE `mails_template` 
ADD INDEX `mail_tmp_org_fk_idx` (`mail_tmp_org_fk` ASC);
ALTER TABLE `mails_template` 
ADD CONSTRAINT `mail_tmp_org_fk`
  FOREIGN KEY (`mail_tmp_org_fk`)
  REFERENCES `organismos` (`org_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

INSERT INTO `mails_template` (`mail_tmp_cod`, `mail_tmp_org_fk`, `mail_tmp_asunto`, `mail_tmp_mensaje`) VALUES ('MAIL_SOL_APROBACION', '1', 'Solicitud de Aprobación', '<h2>Solicitud de Aprobación</h2><p>Se generó una solicitud de cambio de estado para el #TIPO_PROG_PROY# "#NOMBRE_PROG_PROY#".</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>');

CREATE TABLE `proy_replanificacion` (
  `proyreplan_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `proyreplan_proy_fk` INT(11) NOT NULL,
  `proyreplan_fecha` DATE NOT NULL,
  `proyreplan_desc` VARCHAR(2000) NOT NULL,
  `proyreplan_historial` TINYINT(1) NOT NULL,
  PRIMARY KEY (`proyreplan_pk`),
  INDEX `proyreplan_proy_fk_idx` (`proyreplan_proy_fk` ASC),
  CONSTRAINT `proyreplan_proy_fk`
    FOREIGN KEY (`proyreplan_proy_fk`)
    REFERENCES `proyectos` (`proy_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


update proyectos set proy_est_pendiente_fk=(proy_est_fk-1) where proy_sol_aceptacion=0 and proy_pk>0;
update proyectos set proy_est_pendiente_fk=(proy_est_fk+1) where proy_sol_aceptacion=1 and proy_pk>0;

ALTER TABLE `entregables` 
ADD COLUMN `ent_horas_estimadas` DECIMAL(11,2) NULL DEFAULT NULL AFTER `ent_fin`;

-- Hasta acá copiado en Siges 4/7/2014.

ALTER TABLE `prog_indices` 
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


INSERT INTO `ss_configuraciones` (`id`,`cnf_codigo`,`cnf_descripcion`,`cnf_valor`,`cnf_protegido`,`cnf_html`,`cnf_ult_usuario`,`cnf_ult_mod`,`cnf_ult_origen`,`cnf_version`) 
VALUES (20, 'CON_CONTROL_ACCESO', 'Si se usa el control de acceso de Agesic o no', 'false', NULL, NULL, NULL, now(), NULL, NULL);


ALTER TABLE `proy_indices` 
ADD COLUMN `proyind_porc_peso_total` DOUBLE(11,2) NULL DEFAULT NULL AFTER `proyind_periodo_fin`;

-- Hasta acá copiado en Siges 8/7/2014.

INSERT INTO mails_template (mail_tmp_cod, mail_tmp_org_fk,mail_tmp_asunto,mail_tmp_mensaje)
VALUES ('MAIL_CAMBIO_CONTRASENIA', 1, 'Cambio de contraseña en SIGES', 'Estimado #NOMBRE#, se ha cambiado su contraseña en SIGES por #CONTRASENIA#');

ALTER TABLE proy_sitact_historico ADD COLUMN version INTEGER ;
UPDATE proy_sitact_historico SET version=0 where version IS NULL and proy_sitact_hist_pk>0;
ALTER TABLE riesgos ADD COLUMN version INTEGER ;
UPDATE riesgos SET version=0 where version IS NULL and risk_pk>0;
ALTER TABLE proy_replanificacion ADD COLUMN version INTEGER ;
UPDATE proy_replanificacion SET version=0 where version IS NULL and proyreplan_pk>0;


CREATE TABLE `productos` (
  `prod_pk` INT(11) NOT NULL,
  `prod_peso` INT(11) NOT NULL,
  `prod_und_medida` VARCHAR(45) NOT NULL,
  `prod_ent_fk` INT(11) NOT NULL,
  PRIMARY KEY (`prod_pk`),
  INDEX `prod_ent_fk_idx` (`prod_ent_fk` ASC),
  CONSTRAINT `prod_ent_fk`
    FOREIGN KEY (`prod_ent_fk`)
    REFERENCES `entregables` (`ent_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `prod_mes` (
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
    REFERENCES `productos` (`prod_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


ALTER TABLE `doc_file` 
ADD INDEX `docfile_doc_fk` (`docfile_doc_fk` ASC);
ALTER TABLE `ss_usuario` 
ADD INDEX `usu_correo_electronico_idx` (`usu_correo_electronico` ASC);

-- Hasta acá copiado en Siges 15/7/2014.

CREATE TABLE `tipo_leccion` (
  `tipolec_pk` INT NOT NULL AUTO_INCREMENT,
  `tipolec_nombre` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`tipolec_pk`));

CREATE TABLE `area_conocimiento` (
  `con_pk` INT NOT NULL AUTO_INCREMENT,
  `con_nombre` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`con_pk`));

CREATE TABLE `lecc_aprendidas` (
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
    REFERENCES `proyectos` (`proy_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `lecapr_tipo_fk`
    FOREIGN KEY (`lecapr_tipo_fk`)
    REFERENCES `tipo_leccion` (`tipolec_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `lecapr_usr_fk`
    FOREIGN KEY (`lecapr_usr_fk`)
    REFERENCES `ss_usuario` (`usu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `lecapr_areacon` (
  `lecaprcon_con_fk` INT(11) NOT NULL,
  `lecaprcon_lecapr_fk` INT(11) NOT NULL,
  PRIMARY KEY (`lecaprcon_con_fk`, `lecaprcon_lecapr_fk`),
  INDEX `lecaprcon_lecapr_fk_idx` (`lecaprcon_lecapr_fk` ASC),
  CONSTRAINT `lecaprcon_con_fk`
    FOREIGN KEY (`lecaprcon_con_fk`)
    REFERENCES `area_conocimiento` (`con_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `lecaprcon_lecapr_fk`
    FOREIGN KEY (`lecaprcon_lecapr_fk`)
    REFERENCES `lecc_aprendidas` (`lecapr_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `programas` 
ADD COLUMN `prog_id_migrado` INT(11) NULL AFTER `prog_ult_origen`;

ALTER TABLE `proyectos` 
ADD COLUMN `proy_id_migrado` INT(11) NULL AFTER `proy_fecha_est_act`;

ALTER TABLE `productos` 
ADD COLUMN `prod_fecha_crea` DATE NOT NULL AFTER `prod_ent_fk`,
ADD COLUMN `prod_ult_mod` DATE NULL DEFAULT NULL AFTER `prod_fecha_crea`;

ALTER TABLE `prod_mes` 
CHANGE COLUMN `prodmes_plan` `prodmes_plan` DECIMAL(11,2) NOT NULL ,
CHANGE COLUMN `prodmes_real` `prodmes_real` DECIMAL(11,2) NULL ,
CHANGE COLUMN `prodmes_acu_plan` `prodmes_acu_plan` DECIMAL(11,2) NOT NULL ,
CHANGE COLUMN `prodmes_acu_real` `prodmes_acu_real` DECIMAL(11,2) NULL ;

ALTER TABLE `prod_mes` 
DROP FOREIGN KEY `prodmes_prod_fk`;

ALTER TABLE `productos` 
CHANGE COLUMN `prod_pk` `prod_pk` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `prod_mes` 
ADD CONSTRAINT `prodmes_prod_fk`
  FOREIGN KEY (`prodmes_prod_fk`)
  REFERENCES `productos` (`prod_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `prod_mes` 
CHANGE COLUMN `prodmes_pk` `prodmes_pk` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `entregables` 
CHANGE COLUMN `ent_horas_estimadas` `ent_horas_estimadas` VARCHAR(15) NULL DEFAULT NULL ;

-- Hasta acá copiado en Siges 17/7/2014.

ALTER TABLE `documentos` 
ADD COLUMN `docs_aprobado` TINYINT(1) NULL DEFAULT NULL AFTER `docs_docfile_pk`;

ALTER TABLE `productos` 
ADD COLUMN `prod_acumulado` TINYINT(1) NULL DEFAULT NULL AFTER `prod_ult_mod`;

ALTER TABLE `productos` 
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

INSERT INTO `tipo_leccion` (`tipolec_nombre`) VALUES ('A evitar');
INSERT INTO `tipo_leccion` (`tipolec_nombre`) VALUES ('A repetir');

-- Hasta acá copiado en Siges 31/7/2014.

ALTER TABLE `lecc_aprendidas` 
DROP FOREIGN KEY `lecapr_proy_fk`;
ALTER TABLE `lecc_aprendidas` 
CHANGE COLUMN `lecapr_proy_fk` `lecapr_proy_fk` INT(11) NULL ;
ALTER TABLE `lecc_aprendidas` 
ADD CONSTRAINT `lecapr_proy_fk`
  FOREIGN KEY (`lecapr_proy_fk`)
  REFERENCES `proyectos` (`proy_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `lecc_aprendidas` 
ADD COLUMN `lecapr_activo` TINYINT(1) NULL DEFAULT NULL AFTER `lecapr_desc`;

-- Hasta acá copiado en Siges 4/8/2014.

ALTER TABLE `adquisicion` 
ADD COLUMN `adq_proc_compra` VARCHAR(20) NULL DEFAULT NULL AFTER `adq_moneda_fk`,
ADD COLUMN `adq_proc_compra_grp` VARCHAR(20) NULL DEFAULT NULL AFTER `adq_proc_compra`;

ALTER TABLE `pagos` 
ADD COLUMN `pag_txt_referencia` VARCHAR(20) NULL DEFAULT NULL AFTER `pag_importe_real`,
ADD COLUMN `pag_cinfirmar` TINYINT(1) NULL DEFAULT NULL AFTER `pag_txt_referencia`;
ALTER TABLE `pagos` 
CHANGE COLUMN `pag_cinfirmar` `pag_confirmar` TINYINT(1) NULL DEFAULT NULL ;


-- Hasta acá copiado en Siges 6/8/2014.

ALTER TABLE `proy_sitact_historico` 
ADD COLUMN `proy_sitact_usu_fk` INT(11) NULL AFTER `proy_sitact_proy_fk`;

ALTER TABLE `proy_sitact_historico` 
CHANGE COLUMN `proy_sitact_desc` `proy_sitact_desc` VARCHAR(4000) NULL DEFAULT NULL ;

ALTER TABLE `proyectos` 
CHANGE COLUMN `proy_situacion_actual` `proy_situacion_actual` VARCHAR(4000) NULL DEFAULT NULL ;

-- Hasta acá copiado en Siges 8/8/2014.

INSERT INTO `estados` (`est_pk`, `est_nombre`) VALUES ('0', 'No Exigido');
UPDATE `estados` SET `est_pk`='0' WHERE `est_nombre`='No Exigido' and est_pk<1000;

-- Hasta acá copiado en Siges 11/8/2014.

ALTER TABLE `proy_replanificacion` 
ADD COLUMN `proyreplan_activo` TINYINT(1) NULL DEFAULT NULL AFTER `proyreplan_historial`;
update proy_replanificacion set proyreplan_activo=0 where proyreplan_activo is null and proyreplan_pk>0;

-- Hasta acá copiado en Siges 14/8/2014.

INSERT INTO `ss_configuraciones` (`cnf_codigo`, `cnf_descripcion`, `cnf_valor`, `cnf_ult_mod`, `cnf_version`) VALUES ('PRODUCTO_INDICE_LIMITE_AMARILLO', 'Limite semaforo amarillo para Productos', '10', '2014-08-14', '0');
INSERT INTO `ss_configuraciones` (`cnf_codigo`, `cnf_descripcion`, `cnf_valor`, `cnf_ult_mod`, `cnf_version`) VALUES ('PRODUCTO_INDICE_LIMITE_ROJO', 'Limite semaforo rojo para Productos', '20', '2014-08-14', '0');

CREATE TABLE `ent_hist_linea_base` (
  `enthist_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `enthist_ent_fk` INT(11) NOT NULL,
  `enthist_inicio_linea_base` BIGINT(20) NOT NULL,
  `enthist_fin_linea_base` BIGINT(20) NULL,
  PRIMARY KEY (`enthist_pk`));

ALTER TABLE `ent_hist_linea_base` 
ADD INDEX `enthist_ent_fk_idx` (`enthist_ent_fk` ASC);
ALTER TABLE `ent_hist_linea_base` 
ADD CONSTRAINT `enthist_ent_fk`
  FOREIGN KEY (`enthist_ent_fk`)
  REFERENCES `entregables` (`ent_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ent_hist_linea_base` 
ADD COLUMN `enthist_fecha` DATE NOT NULL AFTER `enthist_fin_linea_base`;

-- Hasta acá copiado en Siges 18/8/2014.

DELETE FROM `ss_configuraciones` WHERE cnf_codigo='CON_CORREO' and cnf_valor='1' and id>0;

update proyectos set proy_objetivo='Sin ingresar.' where (proy_est_fk=4 or proy_est_fk=5) and (trim(proy_objetivo) ='' or proy_objetivo is null);
update proyectos set proy_obj_publico='Sin ingresar.' where (proy_est_fk=4 or proy_est_fk=5) and (trim(proy_obj_publico) ='' or proy_obj_publico is null);

ALTER TABLE `ss_configuraciones` 
ADD COLUMN `cnf_org_fk` INT(11) NULL DEFAULT NULL AFTER `id`;

update ss_configuraciones set cnf_org_fk=1 where cnf_org_fk is null and id >0;


-- Hasta acá copiado en Siges 20/8/2014.

ALTER TABLE `aud_ss_configuraciones` 
ADD COLUMN `cnf_org_fk` INT(11) NULL AFTER `cnf_version`;

update aud_ss_configuraciones set cnf_org_fk=1 where cnf_org_fk is null and id >0;

-- Para cambiar el tamaño del campo dende se almacenan los filtros.
ALTER TABLE `busq_filtro` 
CHANGE COLUMN `busq_filtro_xml` `busq_filtro_xml` TEXT NOT NULL ;

-- Plantillas de mails.
INSERT INTO `mails_template` (`mail_tmp_cod`, `mail_tmp_org_fk`, `mail_tmp_asunto`, `mail_tmp_mensaje`) VALUES ('MAIL_CAMBIO_ESTADO', '1', 'Programa / Proyecto cambió de fase.', '<h2>Cambio de Fase</h2><p>El #TIPO_PROG_PROY# #ID_PROG_PROY# "#NOMBRE_PROG_PROY#" cambió de fase a #FASE_PROG_PROY#.</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>');
INSERT INTO `mails_template` (`mail_tmp_cod`, `mail_tmp_org_fk`, `mail_tmp_asunto`, `mail_tmp_mensaje`) VALUES ('MAIL_PROG_PROY_PENDIENTE', '1', 'Pendiente de aprobación.', '<h2>Pendiente de aprobación</h2><p>El #TIPO_PROG_PROY# #ID_PROG_PROY# "#NOMBRE_PROG_PROY#" esta pendiente de aprobación.</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>');
INSERT INTO `mails_template` (`mail_tmp_cod`, `mail_tmp_org_fk`, `mail_tmp_asunto`, `mail_tmp_mensaje`) VALUES ('MAIL_NVO_USUARIO', '1', 'Usuario SIGES.', '<h2>Usuario creado</h2><p>Se ha creado el usuario #USU_MAIL# cuya clave es #USU_PASSWORD#, para ingresar al sistema de SIGES.</p><p>Sistema SIGES.</p>');

ALTER TABLE `organi_int_prove` 
ADD COLUMN `orga_org_fk` INT(11) NOT NULL AFTER `orga_ambito`;

update organi_int_prove set orga_org_fk=(SELECT org_pk FROM organismos order by org_pk limit 1) where orga_org_fk is null or orga_org_fk=0 and orga_pk>0;

ALTER TABLE `organi_int_prove` 
ADD INDEX `orga_org_fk_idx` (`orga_org_fk` ASC);
ALTER TABLE `organi_int_prove` 
ADD CONSTRAINT `orga_org_fk`
  FOREIGN KEY (`orga_org_fk`)
  REFERENCES `organismos` (`org_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

-- Hasta acá en Siges v1.4-20140903

ALTER TABLE `ss_usu_ofi_roles` 
ADD COLUMN `usu_ofi_roles_usu_area` INT(11) NULL DEFAULT NULL AFTER `usu_ofi_roles_usuario`;

ALTER TABLE `ss_usu_ofi_roles` 
ADD INDEX `usu_ofi_roles_usu_area_idx` (`usu_ofi_roles_usu_area` ASC);
ALTER TABLE `ss_usu_ofi_roles` 
ADD CONSTRAINT `usu_ofi_roles_usu_area`
  FOREIGN KEY (`usu_ofi_roles_usu_area`)
  REFERENCES `areas` (`area_pk`)
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

CREATE TABLE `notificacion` (
  `not_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `not_desc` VARCHAR(245) NOT NULL,
  `not_valor` INT(11) NULL,
  `not_gerente_adjunto` TINYINT NULL DEFAULT NULL,
  `not_pmof` TINYINT NULL DEFAULT NULL,
  `not_pmot` TINYINT NULL DEFAULT NULL,
  `not_sponsor` TINYINT NULL DEFAULT NULL,
  `not_msg` VARCHAR(5000) NOT NULL,
  PRIMARY KEY (`not_pk`));

CREATE TABLE `notificacion_instancia` (
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
    REFERENCES `notificacion` (`not_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `notinst_proy_fk`
    FOREIGN KEY (`notinst_proy_fk`)
    REFERENCES `proyectos` (`proy_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `proy_replanificacion` 
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

ALTER TABLE `notificacion` 
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

ALTER TABLE `ent_hist_linea_base` 
CHANGE COLUMN `enthist_fecha` `enthist_fecha` DATE NOT NULL AFTER `enthist_ent_fk`,
ADD COLUMN `enthist_duracion` INT(11) NULL AFTER `enthist_fin_linea_base`;

-- Hasta acá en Siges v1.5-20140922

ALTER TABLE `notificacion` 
ADD COLUMN `not_cod` VARCHAR(45) NOT NULL AFTER `not_org_fk`;

CREATE TABLE `notificacion_envio` (
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

ALTER TABLE `gastos` 
ADD COLUMN `gas_aprobado` TINYINT(1) NULL DEFAULT NULL AFTER `gas_obs`;

ALTER TABLE `registros_horas` 
CHANGE COLUMN `rh_horas` `rh_horas` DECIMAL(11,2) NOT NULL ;

-- Hasta acá en Siges v2.0-20141104

CREATE TABLE `devengado` (
  `dev_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `dev_adq_fk` INT(11) NOT NULL,
  `dev_mes` SMALLINT(6) NOT NULL,
  `dev_anio` SMALLINT(6) NOT NULL,
  `dev_plan` DECIMAL(11,2) NULL DEFAULT NULL,
  `dev_real` DECIMAL(11,2) NULL DEFAULT NULL,
  PRIMARY KEY (`dev_pk`));

-- Hasta acá en Siges v2.0-20141128

delete from notificacion_instancia where notinst_pk > 0;

ALTER TABLE `ss_rol` 
ADD COLUMN `rol_label` VARCHAR(150) NOT NULL AFTER `rol_nombre`;

ALTER TABLE `aud_ss_rol`  ADD COLUMN `rol_label` VARCHAR(150) NULL;

-- Hasta acá en Siges v2.0-20150203

ALTER TABLE `organi_int_prove` 
ADD COLUMN `orga_amb_fk` INT(11) NULL DEFAULT NULL AFTER `orga_org_fk`,
ADD INDEX `orga_amb_fk_idx` (`orga_amb_fk` ASC);
ALTER TABLE `organi_int_prove`
ADD CONSTRAINT `orga_amb_fk`
  FOREIGN KEY (`orga_amb_fk`)
  REFERENCES `ambito` (`amb_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

-- Hasta acá en Siges v2.1-20150323

CREATE TABLE `temas_calidad` (
  `tca_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `tca_nombre` VARCHAR(100) NOT NULL,
  `tca_org_fk` INT(11) NOT NULL,
  PRIMARY KEY (`tca_pk`),
  INDEX `tca_org_fk_idx` (`tca_org_fk` ASC),
  CONSTRAINT `tca_org_fk`
    FOREIGN KEY (`tca_org_fk`)
    REFERENCES `organismos` (`org_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `calidad` (
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
    REFERENCES `entregables` (`ent_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cal_prod_fk`
    FOREIGN KEY (`cal_prod_fk`)
    REFERENCES `productos` (`prod_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cal_tca_fk`
    FOREIGN KEY (`cal_tca_fk`)
    REFERENCES `temas_calidad` (`tca_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `valor_calidad_codigos` (
  `vca_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `vca_codigo` VARCHAR(45) NOT NULL,
  `vca_nombre` VARCHAR(100) NOT NULL,
  `vca_habilitado` TINYINT(1) NOT NULL,
  PRIMARY KEY (`vca_pk`),
  INDEX `vca_codigo` (`vca_codigo` ASC));

INSERT INTO `valor_calidad_codigos` (`vca_codigo`, `vca_nombre`, `vca_habilitado`) VALUES ('baja', '0', '1');
INSERT INTO `valor_calidad_codigos` (`vca_codigo`, `vca_nombre`, `vca_habilitado`) VALUES ('media', '0.5', '1');
INSERT INTO `valor_calidad_codigos` (`vca_codigo`, `vca_nombre`, `vca_habilitado`) VALUES ('alta', '1', '1');
INSERT INTO `valor_calidad_codigos` (`vca_codigo`, `vca_nombre`, `vca_habilitado`) VALUES ('no_corresponde', 'No Corresponde', '1');
INSERT INTO `valor_calidad_codigos` (`vca_codigo`, `vca_nombre`, `vca_habilitado`) VALUES ('pendiente', 'Pendiente de Cargar', '1');

ALTER TABLE `calidad` 
CHANGE COLUMN `cal_valor` `cal_valor` INT(11) NOT NULL ,
ADD INDEX `cal_valor_idx` (`cal_valor` ASC);
ALTER TABLE `calidad` 
ADD CONSTRAINT `cal_valor`
  FOREIGN KEY (`cal_valor`)
  REFERENCES `valor_calidad_codigos` (`vca_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ss_configuraciones` 
ADD INDEX `cnf_org_fk` (`cnf_org_fk` ASC),
ADD INDEX `cnf_codigo` (`cnf_codigo` ASC);

ALTER TABLE `calidad` 
DROP FOREIGN KEY `cal_valor`;
ALTER TABLE `calidad` 
CHANGE COLUMN `cal_valor` `cal_vca_fk` INT(11) NOT NULL ;
ALTER TABLE `calidad` 
ADD CONSTRAINT `cal_vca_fk`
  FOREIGN KEY (`cal_vca_fk`)
  REFERENCES `valor_calidad_codigos` (`vca_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `calidad` 
ADD COLUMN `cal_proy_fk` INT(11) NOT NULL AFTER `cal_tca_fk`,
ADD INDEX `cal_proy_fk_idx` (`cal_proy_fk` ASC);
ALTER TABLE `calidad` 
ADD CONSTRAINT `cal_proy_fk`
  FOREIGN KEY (`cal_proy_fk`)
  REFERENCES `proyectos` (`proy_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `calidad` 
ADD COLUMN `cal_tipo` INT(11) NOT NULL AFTER `cal_actualizacion`,
ADD INDEX `cal_tipo` (`cal_tipo` ASC);

ALTER TABLE `prog_indices` 
ADD COLUMN `progind_cal_ind` DOUBLE(11,2) NULL DEFAULT NULL AFTER `progind_proy_actualizacion`,
ADD COLUMN `progind_cal_pend` INT(11) NULL DEFAULT NULL AFTER `progind_cal_ind`;

ALTER TABLE `proy_indices` 
ADD COLUMN `proyind_cal_ind` DOUBLE(11,2) NULL DEFAULT NULL AFTER `proyind_porc_peso_total`,
ADD COLUMN `proyind_cal_pend` INT(11) NULL DEFAULT NULL AFTER `proyind_cal_ind`;

ALTER TABLE `ss_usuario` 
ADD COLUMN `usu_aprob_facturas` TINYINT(1) NULL DEFAULT NULL AFTER `usu_vigente`;

ALTER TABLE `aud_ss_usuario` 
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

ALTER TABLE `riesgos` 
ADD COLUMN `risk_ent_fk` INT(11) NULL DEFAULT NULL AFTER `risk_impacto`,
ADD INDEX `risk_ent_fk_idx` (`risk_ent_fk` ASC);
ALTER TABLE `riesgos` 
ADD CONSTRAINT `risk_ent_fk`
  FOREIGN KEY (`risk_ent_fk`)
  REFERENCES `entregables` (`ent_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `interesados` 
ADD COLUMN `int_ent_fk` INT(11) NULL DEFAULT NULL AFTER `int_orga_fk`,
ADD INDEX `int_ent_fk_idx` (`int_ent_fk` ASC);
ALTER TABLE `interesados` 
ADD CONSTRAINT `int_ent_fk`
  FOREIGN KEY (`int_ent_fk`)
  REFERENCES `entregables` (`ent_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `documentos` 
ADD COLUMN `docs_pago_fk` INT(11) NULL DEFAULT NULL AFTER `docs_aprobado`,
ADD INDEX `docs_pago_fk_idx` (`docs_pago_fk` ASC);
ALTER TABLE `documentos` 
ADD CONSTRAINT `docs_pago_fk`
  FOREIGN KEY (`docs_pago_fk`)
  REFERENCES `pagos` (`pag_pk`)
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

-- Desde acá en adelante es v2.6-6

ALTER TABLE `organismos` 
ADD COLUMN `org_activo` TINYINT(1) NOT NULL DEFAULT 1 AFTER `org_logo`;

CREATE 
     OR REPLACE ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `ss_oficina` AS
    select 
        `organismos`.`org_pk` AS `ofi_id`,
        `organismos`.`org_nombre` AS `ofi_nombre`,
	`organismos`.`org_activo` AS `ofi_activo`,
        now() AS `ofi_fecha_creacion`,
        '' AS `ofi_origen`,
        1 AS `ofi_usuario`,
        1 AS `ofi_version`

    from
        `organismos`;

-- Corrección para la v2.6
ALTER TABLE `aud_ss_oficina` 
ADD COLUMN `ofi_activo` TINYINT(1) NULL DEFAULT NULL AFTER `ofi_version`;

-- Desde acá en adelante es v3.0

CREATE TABLE `etapa` (
  `eta_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `eta_org_fk` INT(11) NOT NULL,
  `eta_codigo` VARCHAR(45) NOT NULL,
  `eta_nombre` VARCHAR(100) NOT NULL,
  `eta_label` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`eta_pk`),
  INDEX `eta_org_fk_idx` (`eta_org_fk` ASC),
  CONSTRAINT `eta_org_fk`
    FOREIGN KEY (`eta_org_fk`)
    REFERENCES `organismos` (`org_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `etapa` 
COMMENT = 'Son los estados del proyecto que se van a exportar al visualizador.';

ALTER TABLE `etapa` 
CHANGE COLUMN `eta_label` `eta_label` VARCHAR(100) NULL ;

CREATE TABLE `categoria_proyectos` (
  `cat_proy_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `cat_proy_org_fk` INT(11) NOT NULL,
  `cat_proy_codigo` VARCHAR(45) NOT NULL,
  `cat_proy_nombre` VARCHAR(145) NOT NULL,
  `cat_proy_activo` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`cat_proy_pk`),
  INDEX `cat_proy_org_fk_idx` (`cat_proy_org_fk` ASC),
  INDEX `cat_proy_activo_idx` (`cat_proy_activo` ASC),
  CONSTRAINT `cat_proy_org_fk`
    FOREIGN KEY (`cat_proy_org_fk`)
    REFERENCES `organismos` (`org_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Categoría de los Proyectos. Principalmente para usar con el Visualizador.';

CREATE TABLE `estados_publicacion` (
  `est_pub_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `est_pub_codigo` VARCHAR(45) NULL,
  `est_pub_nombre` VARCHAR(145) NULL,
  PRIMARY KEY (`est_pub_pk`))
COMMENT = 'Estados de la publicación de un proyecto en el visualizador.';

CREATE TABLE `proy_otros_datos` (
  `proy_otr_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `proy_otr_proy_fk` INT(11) NOT NULL COMMENT 'Proyecto al que está asociado.',
  `proy_otr_cont_fk` INT(11) NULL COMMENT 'Contratista Principal',
  `proy_otr_ins_eje_fk` INT(11) NULL COMMENT 'Institución Ejecutora',
  `proy_otr_ent_fk` INT(11) NULL COMMENT 'Inicio construcción del Producto. Asociado a un Entregable.',
  `proy_otr_origen` VARCHAR(1000) NULL COMMENT 'Origen Principal de los Recursos.',
  `proy_otr_plazo` INT(11) NULL COMMENT 'Plazo estimado de obra en días.',
  `proy_otr_observaciones` VARCHAR(4000) NULL COMMENT 'Observaciones.',
  `proy_otr_cat_fk` INT(11) NULL,
  PRIMARY KEY (`proy_otr_pk`),
  INDEX `proy_otr_cont_fk_idx` (`proy_otr_cont_fk` ASC),
  INDEX `proy_otr_proy_fk_idx` (`proy_otr_proy_fk` ASC),
  INDEX `proy_otr_ins_eje_fk_idx` (`proy_otr_ins_eje_fk` ASC),
  INDEX `proy_otr_ent_fk_idx` (`proy_otr_ent_fk` ASC),
  CONSTRAINT `proy_otr_cont_fk`
    FOREIGN KEY (`proy_otr_cont_fk`)
    REFERENCES `organi_int_prove` (`orga_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `proy_otr_proy_fk`
    FOREIGN KEY (`proy_otr_proy_fk`)
    REFERENCES `proyectos` (`proy_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `proy_otr_ins_eje_fk`
    FOREIGN KEY (`proy_otr_ins_eje_fk`)
    REFERENCES `organi_int_prove` (`orga_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `proy_otr_ent_fk`
    FOREIGN KEY (`proy_otr_ent_fk`)
    REFERENCES `entregables` (`ent_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Datos del proyecto principalmente para usarse en el visualizador';

ALTER TABLE `proy_otros_datos` 
CHANGE COLUMN `proy_otr_cat_fk` `proy_otr_cat_fk` INT(11) NULL DEFAULT NULL COMMENT 'Categoría Principal.' ,
ADD COLUMN `proy_otr_est_pub_fk` INT(11) NULL COMMENT 'Estado de Publicación.' AFTER `proy_otr_cat_fk`,
ADD INDEX `proy_otr_cat_fk_idx` (`proy_otr_cat_fk` ASC),
ADD INDEX `proy_otr_est_pub_fk_idx` (`proy_otr_est_pub_fk` ASC);
ALTER TABLE `proy_otros_datos` 
ADD CONSTRAINT `proy_otr_cat_fk`
  FOREIGN KEY (`proy_otr_cat_fk`)
  REFERENCES `categoria_proyectos` (`cat_proy_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `proy_otr_est_pub_fk`
  FOREIGN KEY (`proy_otr_est_pub_fk`)
  REFERENCES `estados_publicacion` (`est_pub_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `proy_otros_cat_secundarias` (
  `proy_cat_proy_fk` INT(11) NOT NULL COMMENT 'ID del Proyecto.',
  `proy_cat_cat_proy_fk` INT(11) NOT NULL COMMENT 'ID de la categoria de proyectos.',
  PRIMARY KEY (`proy_cat_proy_fk`, `proy_cat_cat_proy_fk`),
  INDEX `proy_cat_cat_proy_fk_idx` (`proy_cat_cat_proy_fk` ASC),
  CONSTRAINT `proy_cat_proy_fk`
    FOREIGN KEY (`proy_cat_proy_fk`)
    REFERENCES `proyectos` (`proy_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `proy_cat_cat_proy_fk`
    FOREIGN KEY (`proy_cat_cat_proy_fk`)
    REFERENCES `categoria_proyectos` (`cat_proy_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Relación entre un proyecto y una o varias categorias secundarias.';

ALTER TABLE `proyectos` 
ADD COLUMN `proy_descripcion` VARCHAR(4000) NULL DEFAULT NULL AFTER `proy_peso`;

ALTER TABLE `proy_otros_datos` 
ADD COLUMN `proy_otr_eta_fk` INT(11) NULL COMMENT 'Estado (etapa)' AFTER `proy_otr_proy_fk`,
ADD INDEX `proy_otr_eta_fk_idx` (`proy_otr_eta_fk` ASC);
ALTER TABLE `proy_otros_datos` 
ADD CONSTRAINT `proy_otr_eta_fk`
  FOREIGN KEY (`proy_otr_eta_fk`)
  REFERENCES `etapa` (`eta_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `proy_otros_cat_secundarias` 
DROP FOREIGN KEY `proy_cat_cat_proy_fk`,
DROP FOREIGN KEY `proy_cat_proy_fk`;
ALTER TABLE `proy_otros_cat_secundarias` 
CHANGE COLUMN `proy_cat_proy_fk` `proy_cat_proy_otros_fk` INT(11) NOT NULL COMMENT 'ID del Proyecto.' ;
ALTER TABLE `proy_otros_cat_secundarias` 
ADD CONSTRAINT `proy_cat_proy_otros_fk`
  FOREIGN KEY (`proy_cat_proy_otros_fk`)
  REFERENCES `proy_otros_datos` (`proy_otr_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `proy_cat_cat_proy_fk`
  FOREIGN KEY (`proy_cat_cat_proy_fk`)
  REFERENCES `categoria_proyectos` (`cat_proy_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `proyectos` 
ADD COLUMN `proy_publicable` TINYINT(1) NOT NULL DEFAULT 1 AFTER `proy_id_migrado`,
ADD INDEX `proy_publicable_idx` (`proy_publicable` ASC);

ALTER TABLE `programas` 
ADD COLUMN `prog_descripcion` VARCHAR(4000) NULL DEFAULT NULL AFTER `prog_nombre`;

CREATE TABLE `tipos_media` (
  `tip_pk` int(11) NOT NULL AUTO_INCREMENT,
  `tip_cod` varchar(45) DEFAULT NULL,
  `tip_nombre` varchar(245) DEFAULT NULL,
  PRIMARY KEY (`tip_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `media_proyectos` (
  `media_pk` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Clave primaria del media',
  `media_tipo_fk` int(11) DEFAULT NULL COMMENT 'Tipo de media, los tipos definidos son:\nImagens, link a videos youtube, links a webcam',
  `media_link` varchar(545) DEFAULT NULL COMMENT 'El link al media, en caso de imagenes es el link al folder donde se encuentra, en youtube la url del video, y en camaras web la url de la camara',
  `media_estado` int(11) DEFAULT NULL COMMENT 'EL estado del media puede ser \nPENDIENTE REVISION, PUBLICADO, RECHAZADO',
  `media_proy_fk` int(11) DEFAULT NULL,
  `media_principal` tinyint(1) DEFAULT NULL COMMENT 'en caso de ser una imagen es la que se utlizará para la vista rapida y para la seccion de destacados',
  `media_orden` int(11) DEFAULT NULL COMMENT 'EL orde de aparición del media en la galeria que lo esta desplegando.',
  `media_usr_pub` varchar(100) DEFAULT NULL COMMENT 'EL código del usuario que publicó el media',
  `media_pub_fecha` datetime DEFAULT NULL COMMENT 'Fecha y hora de publicación',
  `media_usr_mod` varchar(100) DEFAULT NULL COMMENT 'EL usuario que creo el media',
  `media_mod_fecha` datetime DEFAULT NULL COMMENT 'La fecha de creación del media',
  `media_usr_rech` varchar(100) DEFAULT NULL COMMENT 'Usuario que rechaza el media',
  `media_rech_fecha` datetime DEFAULT NULL COMMENT 'fecha de rehazo del medio',
  `media_comentario` varchar(2000) DEFAULT NULL,
  `media_contenttype` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`media_pk`),
  KEY `media_proy_fk_idx` (`media_proy_fk`),
  KEY `media_tipo_fk_idx` (`media_tipo_fk`),
  CONSTRAINT `media_proy_fk` FOREIGN KEY (`media_proy_fk`) REFERENCES `proyectos` (`proy_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `media_tipo_fk` FOREIGN KEY (`media_tipo_fk`) REFERENCES `tipos_media` (`tip_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `departamentos` (
  `dep_pk` int(11) NOT NULL,
  `dep_nombre` varchar(145) DEFAULT NULL,
  `dep_lat` double DEFAULT NULL,
  `dep_lng` double DEFAULT NULL,
  `dep_zoom` int(11) DEFAULT NULL,
  PRIMARY KEY (`dep_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

UPDATE `ss_rol` SET `rol_nombre`='Editor General', `rol_tipo_usuario`='1' WHERE `rol_cod`='EDITOR_GRAL' and rol_id>0;

ALTER TABLE `media_proyectos` 
ADD COLUMN `media_publicable` TINYINT(1) NULL DEFAULT NULL AFTER `media_contenttype`;

ALTER TABLE `ss_configuraciones` 
CHANGE COLUMN `cnf_codigo` `cnf_codigo` VARCHAR(145) NULL DEFAULT NULL ,
CHANGE COLUMN `cnf_descripcion` `cnf_descripcion` VARCHAR(245) NULL DEFAULT NULL ;

ALTER TABLE `media_proyectos` 
CHANGE COLUMN `media_usr_pub` `media_usr_pub_fk` INT(11) NULL DEFAULT NULL COMMENT 'EL código del usuario que publicó el media' ,
CHANGE COLUMN `media_usr_mod` `media_usr_mod_fk` INT(11) NULL DEFAULT NULL COMMENT 'EL usuario que creo el media' ,
CHANGE COLUMN `media_usr_rech` `media_usr_rech_fk` INT(11) NULL DEFAULT NULL COMMENT 'Usuario que rechaza el media' ,
ADD INDEX `media_usr_pub_fk_idx` (`media_usr_pub_fk` ASC),
ADD INDEX `media_usr_mod_fk_idx` (`media_usr_mod_fk` ASC),
ADD INDEX `media_usr_rech_fk_idx` (`media_usr_rech_fk` ASC);
ALTER TABLE `media_proyectos` 
ADD CONSTRAINT `media_usr_pub_fk`
  FOREIGN KEY (`media_usr_pub_fk`)
  REFERENCES `ss_usuario` (`usu_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `media_usr_mod_fk`
  FOREIGN KEY (`media_usr_mod_fk`)
  REFERENCES `ss_usuario` (`usu_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `media_usr_rech_fk`
  FOREIGN KEY (`media_usr_rech_fk`)
  REFERENCES `ss_usuario` (`usu_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `proy_otros_datos` 
DROP FOREIGN KEY `proy_otr_proy_fk`;
ALTER TABLE `proy_otros_datos` 
DROP COLUMN `proy_otr_proy_fk`,
DROP INDEX `proy_otr_proy_fk_idx` ;

ALTER TABLE `proyectos` 
ADD COLUMN `proy_otr_dat_fk` INT(11) NULL DEFAULT NULL AFTER `proy_publicable`,
ADD INDEX `proy_otr_dat_fk_idx` (`proy_otr_dat_fk` ASC);
ALTER TABLE `proyectos` 
ADD CONSTRAINT `proy_otr_dat_fk`
  FOREIGN KEY (`proy_otr_dat_fk`)
  REFERENCES `proy_otros_datos` (`proy_otr_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

ALTER TABLE `proyectos` 
ADD COLUMN `proy_latlng_fk` INT(11) NULL DEFAULT NULL AFTER `proy_otr_dat_fk`,
ADD INDEX `proy_latlng_fk_idx` (`proy_latlng_fk` ASC);
ALTER TABLE `proyectos` 
ADD CONSTRAINT `proy_latlng_fk`
  FOREIGN KEY (`proy_latlng_fk`)
  REFERENCES `latlng_proyectos` (`latlng_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `categoria_proyectos` 
DROP FOREIGN KEY `cat_proy_org_fk`;
ALTER TABLE `categoria_proyectos` 
DROP COLUMN `cat_proy_org_fk`,
DROP INDEX `cat_proy_org_fk_idx` ;

INSERT INTO `categoria_proyectos` (`cat_proy_pk`, `cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`) VALUES ('1', 'Salud', 'Salud', '1');
INSERT INTO `categoria_proyectos` (`cat_proy_pk`, `cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`) VALUES ('2', 'Educativa', 'Educativa', '1');
INSERT INTO `categoria_proyectos` (`cat_proy_pk`, `cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`) VALUES ('3', 'Viviendas', 'Viviendas', '1');
INSERT INTO `categoria_proyectos` (`cat_proy_pk`, `cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`) VALUES ('4', 'Transporte Terrestre', 'Transporte Terrestre', '1');
INSERT INTO `categoria_proyectos` (`cat_proy_pk`, `cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`) VALUES ('5', 'Transporte Maritimo', 'Transporte Maritimo', '1');
INSERT INTO `categoria_proyectos` (`cat_proy_pk`, `cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`) VALUES ('6', 'Transporte Aereo', 'Transporte Aereo', '1');
INSERT INTO `categoria_proyectos` (`cat_proy_pk`, `cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`) VALUES ('7', 'Urbana', 'Urbana', '1');
INSERT INTO `categoria_proyectos` (`cat_proy_pk`, `cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`) VALUES ('8', 'Social', 'Social', '1');
INSERT INTO `categoria_proyectos` (`cat_proy_pk`, `cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`) VALUES ('9', 'Seguridad', 'Seguridad', '1');
INSERT INTO `categoria_proyectos` (`cat_proy_pk`, `cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`) VALUES ('10', 'Energetica', 'Energetica', '1');
INSERT INTO `categoria_proyectos` (`cat_proy_pk`, `cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`) VALUES ('11', 'Tecnologica', 'Tecnologica', '1');
INSERT INTO `categoria_proyectos` (`cat_proy_pk`, `cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`) VALUES ('12', 'Obras Sanitarias', 'Obras Sanitarias', '1');
INSERT INTO `categoria_proyectos` (`cat_proy_pk`, `cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`) VALUES ('13', 'Varios', 'Varios', '1');

-- mapa google
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('1', 'MONTEVIDEO'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('2', 'ARTIGAS');
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('3', 'CANELONES'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('4', 'CERRO LARGO'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('5', 'COLONIA'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('6', 'DURAZNO'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('7', 'FLORES'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('8', 'FLORIDA'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('9', 'LAVALLEJA'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('10', 'MALDONADO'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('11', 'PAYSANDU'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('12', 'RIO NEGRO'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('13', 'RIVERA'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('14', 'ROCHA'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('15', 'SALTO'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('16', 'SAN JOSE'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('17', 'SORIANO'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('18', 'TACUAREMBO'); 
INSERT INTO `departamentos` (`dep_pk`, `dep_nombre`) VALUES ('19', 'TREINTA Y TRES');

-- Desde acá en adelante es v3.0-7

DELETE FROM proy_docs
WHERE proydoc_doc_pk in (SELECT docs_pk FROM documentos where docs_nombre like '%[PENDIENTE]%')
and proydoc_proy_pk>0;

DELETE FROM prog_docs
WHERE progdocs_doc_pk in (SELECT docs_pk FROM documentos where docs_nombre like '%[PENDIENTE]%')
and progdocs_prog_pk>0;

DELETE FROM documentos
WHERE docs_nombre like '%[PENDIENTE]%'
and docs_pk>0;


-- Desde acá en adelante es v3.1-0

ALTER TABLE `tipo_leccion` 
ADD COLUMN `tipolec_codigo` VARCHAR(45) NOT NULL AFTER `tipolec_pk`;

ALTER TABLE `estados` 
ADD INDEX `est_codigo` (`est_codigo` ASC);

ALTER TABLE `tipo_leccion` 
ADD INDEX `tipolec_codigo` (`tipolec_codigo` ASC);

CREATE TABLE `proy_indices_pre` (
  `proyindpre_pk` int(11) NOT NULL AUTO_INCREMENT,
  `proyindpre_proyind_fk` int(11) NOT NULL,
  `proyindpre_mon_fk` int(11) NOT NULL,
  `proyindpre_total` double(11,2) DEFAULT NULL,
  `proyindpre_est_pre` int(11) DEFAULT NULL,
  PRIMARY KEY (`proyindpre_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `prog_indices` 
ADD COLUMN `progind_avance_par_azul` INT(11) NULL DEFAULT NULL AFTER `progind_cal_pend`,
ADD COLUMN `progind_avance_par_verde` INT(11) NULL DEFAULT NULL AFTER `progind_avance_par_azul`,
ADD COLUMN `progind_anvance_par_rojo` INT(11) NULL DEFAULT NULL AFTER `progind_avance_par_verde`,
ADD COLUMN `progind_avance_fin_azul` INT(11) NULL DEFAULT NULL AFTER `progind_anvance_par_rojo`,
ADD COLUMN `progind_avance_fin_verde` INT(11) NULL DEFAULT NULL AFTER `progind_avance_fin_azul`,
ADD COLUMN `progind_anvance_fin_rojo` INT(11) NULL DEFAULT NULL AFTER `progind_avance_fin_verde`;

ALTER TABLE `proy_indices` 
ADD COLUMN `proyind_fase_color` VARCHAR(7) NULL DEFAULT NULL AFTER `proyind_cal_pend`,
ADD COLUMN `proyind_avance_par_azul` INT(11) NULL DEFAULT NULL AFTER `proyind_fase_color`,
ADD COLUMN `proyind_avance_par_verde` INT(11) NULL DEFAULT NULL AFTER `proyind_avance_par_azul`,
ADD COLUMN `proyind_anvance_par_rojo` INT(11) NULL DEFAULT NULL AFTER `proyind_avance_par_verde`,
ADD COLUMN `proyind_avance_fin_azul` INT(11) NULL DEFAULT NULL AFTER `proyind_anvance_par_rojo`,
ADD COLUMN `proyind_avance_fin_verde` INT(11) NULL DEFAULT NULL AFTER `proyind_avance_fin_azul`,
ADD COLUMN `proyind_anvance_fin_rojo` INT(11) NULL DEFAULT NULL AFTER `proyind_avance_fin_verde`;

ALTER TABLE `proy_indices` 
DROP COLUMN `proyind_fase_color`;

ALTER TABLE `proy_indices` 
ADD COLUMN `proyind_fase_color` INT(11) NULL DEFAULT NULL AFTER `proyind_cal_pend`;

update productos set prod_desc='Producto' where prod_desc like '' and prod_pk>0;

ALTER TABLE `proy_indices` 
ADD COLUMN `proyind_fecha_act` DATETIME NULL DEFAULT NULL AFTER `proyind_anvance_fin_rojo`;

ALTER TABLE `prog_indices` 
ADD COLUMN `progind_fecha_act` DATETIME NULL DEFAULT NULL AFTER `progind_anvance_fin_rojo`;

-- Desde acá en adelante es v3.2-0 (pasó a ser la 4.0)

CREATE TABLE `proy_publica_hist` (
  `proy_publica_pk` INT(11) NOT NULL AUTO_INCREMENT,
  `proy_publica_fecha` DATETIME NOT NULL,
  `proy_publica_proy_fk` INT(11) NOT NULL,
  `proy_publica_usu_fk` INT(11) NOT NULL,
  PRIMARY KEY (`proy_publica_pk`),
  INDEX `proy_publica_proy_fk_idx` (`proy_publica_proy_fk` ASC),
  INDEX `proy_publica_usu_fk_idx` (`proy_publica_usu_fk` ASC),
  CONSTRAINT `proy_publica_proy_fk`
    FOREIGN KEY (`proy_publica_proy_fk`)
    REFERENCES `proyectos` (`proy_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `proy_publica_usu_fk`
    FOREIGN KEY (`proy_publica_usu_fk`)
    REFERENCES `ss_usuario` (`usu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Registro de las veces que se publicó el proyecto en el Visualizador.';

ALTER TABLE `organismos` 
ADD COLUMN `org_token` VARCHAR(100) NOT NULL AFTER `org_activo`,
ADD INDEX `org_token_idx` (`org_token` ASC);

ALTER TABLE ss_usuario CONVERT TO CHARACTER SET utf8 COLLATE utf8_spanish_ci;

alter table proy_indices_pre add proyindpre_ac double(11,2);
alter table proy_indices_pre add proyindpre_pv double(11,2);

-- Desde acá en adelante es v4.1-0

ALTER TABLE `entregables` 
ADD COLUMN `ent_parent` TINYINT(1) NULL DEFAULT NULL AFTER `ent_status`;
ALTER TABLE `entregables` 
ADD INDEX `ent_parent_idx` (`ent_parent` ASC);

-- Desde acá en adelante es v4.2-0

ALTER TABLE `ss_usuario` 
ADD COLUMN `usu_token` VARCHAR(100) NULL DEFAULT NULL AFTER `usu_area_org`,
ADD COLUMN `usu_token_act` DATETIME NULL DEFAULT NULL AFTER `usu_token`,
ADD INDEX `usu_token_idx` (`usu_token` ASC);

ALTER TABLE `aud_ss_usuario` 
ADD COLUMN `usu_token` VARCHAR(100) NULL DEFAULT NULL AFTER `usu_area_org`,
ADD COLUMN `usu_token_act` DATETIME NULL DEFAULT NULL AFTER `usu_token`,
ADD INDEX `usu_token_idx` (`usu_token` ASC);

-- Se realiza para quitar la ruta al archivo ya que se saca de la configuración.
UPDATE media_proyectos SET media_link = REPLACE(media_link, '/srv/siges/jboss-as-7.1.1.Final/media_files/', '') where media_pk>0;
UPDATE media_proyectos SET media_link = REPLACE(media_link, '/siges/media_files/', '') where media_pk>0;

-- Desde acá en adelante es v4.2-1

ALTER TABLE `presupuesto` 
CHANGE COLUMN `pre_base` `pre_base` DECIMAL(15,2) NULL DEFAULT NULL ;

-- Desde acá en adelante es v4.2-3

ALTER TABLE `ss_configuraciones` 
CHANGE COLUMN `cnf_valor` `cnf_valor` VARCHAR(255) NULL DEFAULT NULL ;


-- Desde acá en adelante es v4.2-4 ---POR ERROR DE APAGON NO SE DEBE EJECUTAR SIEMPRE

CREATE table  entregables_apagon_20160113 as (select e.ent_pk,e.ent_cro_fk
from entregables e, 
entregables e2
where
e.ent_parent = 1 and
e.ent_predecesor_fk is not null 
and e.ent_predecesor_fk <> ''
and  trim(CONVERT(e2.ent_id, char(50) )) = trim(e.ent_predecesor_fk)
and e.ent_cro_fk = e2.ent_cro_fk
and DATE_FORMAT(FROM_UNIXTIME(e2.ent_fin / 1000), "%Y-%m-%d") <> DATE_FORMAT(date_add(FROM_UNIXTIME(e.ent_inicio / 1000), interval 1 day),"%Y-%m-%d")
order by e.ent_cro_fk, e.ent_id);
update entregables set  ent_predecesor_fk = null where ent_pk > 0 and ent_pk IN (select ent_pk from entregables_apagon_20160113);


--- Para consumo plataforma (se eliminan los valores de configuracion) v 4.2-5 y luego se configuran a MANO para cada organismo.
delete from ss_configuraciones where cnf_codigo = 'STS_URL';
delete from ss_configuraciones where cnf_codigo = 'STS_POLITICA';
delete from ss_configuraciones where cnf_codigo = 'STS_EMISOR';
delete from ss_configuraciones where cnf_codigo = 'KEYSTORE_ORG_PATH';
delete from ss_configuraciones where cnf_codigo = 'KEYSTORE_ORG_PASS';
delete from ss_configuraciones where cnf_codigo = 'KEYSTORE_ORG_ALIAS';
delete from ss_configuraciones where cnf_codigo = 'KEYSTORE_SSL_PATH';
delete from ss_configuraciones where cnf_codigo = 'KEYSTORE_SSL_PASS';
delete from ss_configuraciones where cnf_codigo = 'KEYSTORE_SSL_ALIAS';
delete from ss_configuraciones where cnf_codigo = 'TRUSTSTORE_SSL_PATH';
delete from ss_configuraciones where cnf_codigo = 'TRUSTSTORE_SSL_PASS';
delete from ss_configuraciones where cnf_codigo = 'VISUALIZADOR.PUBLICARSERVICIO_URLLOGICA';
delete from ss_configuraciones where cnf_codigo = 'VISUALIZADOR.PUBLICARSERVICIO_SOAPACTION';
delete from ss_configuraciones where cnf_codigo = 'VISUALIZADOR.PUBLICARSERVICIO_NAMESPACE';
delete from ss_configuraciones where cnf_codigo = 'VISUALIZADOR.PUBLICARSERVICIO_SERVICENAME';
delete from ss_configuraciones where cnf_codigo = 'VISUALIZADOR.PUBLICARSERVICIO_WSDL';
delete from ss_configuraciones where cnf_codigo = 'VISUALIZADOR.PUBLICARSERVICIO_ROL';

-- Desde acá en adelante es v4.4-0
ALTER TABLE `proy_indices_pre` 
ADD COLUMN `proyindpre_anio` DOUBLE(11,2) NULL DEFAULT NULL AFTER `proyindpre_pv`;

-- Desde acá en adelante es v4.4-1
CREATE TABLE `image` (
  `image_pk` int(10) NOT NULL AUTO_INCREMENT,
  `image_name` varchar(45) NOT NULL,
  `image_desc` varchar(255) DEFAULT NULL,
  `image_ext` varchar(20) NOT NULL,
  `image_blob` mediumblob NOT NULL,
  `image_tipo` int(5) DEFAULT NULL,
  `image_orden` int(5) DEFAULT NULL,
  PRIMARY KEY (`image_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

ALTER TABLE `categoria_proyectos` 
ADD COLUMN `cat_tipo` INT(11) NOT NULL DEFAULT 0 AFTER `cat_proy_activo`,
ADD COLUMN `cat_icono` INT(11) NULL DEFAULT NULL AFTER `cat_tipo`,
ADD COLUMN `cat_icono_marker` INT(11) NULL DEFAULT NULL AFTER `cat_icono`, 
COMMENT = 'Categoria de los Proyectos. Principalmente para usar con el Visualizador.' ;

UPDATE `categoria_proyectos` SET `cat_proy_codigo`='TE_SALUD', `cat_tipo`='1' WHERE `cat_proy_pk`='1';
UPDATE `categoria_proyectos` SET `cat_proy_codigo`='TE_EDUCATIVA', `cat_tipo`='1' WHERE `cat_proy_pk`='2';
UPDATE `categoria_proyectos` SET `cat_proy_codigo`='TE_VIVIENDA', `cat_tipo`='1' WHERE `cat_proy_pk`='3';
UPDATE `categoria_proyectos` SET `cat_proy_codigo`='TE_TERRESTRE', `cat_tipo`='1' WHERE `cat_proy_pk`='4';
UPDATE `categoria_proyectos` SET `cat_proy_codigo`='TE_URBANA', `cat_tipo`='1' WHERE `cat_proy_pk`='7';
UPDATE `categoria_proyectos` SET `cat_proy_codigo`='TE_SOCIAL', `cat_tipo`='1' WHERE `cat_proy_pk`='8';
UPDATE `categoria_proyectos` SET `cat_proy_codigo`='TE_SEGURIDAD', `cat_tipo`='1' WHERE `cat_proy_pk`='9';
UPDATE `categoria_proyectos` SET `cat_proy_codigo`='TE_SANIDAD', `cat_tipo`='1' WHERE `cat_proy_pk`='12';
UPDATE `categoria_proyectos` SET `cat_proy_codigo`='TE_VARIOS', `cat_tipo`='1' WHERE `cat_proy_pk`='13';
INSERT INTO `categoria_proyectos` (`cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`, `cat_tipo`) VALUES ('CA_SALUD', 'Salud', '1', '2');
INSERT INTO `categoria_proyectos` (`cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`, `cat_tipo`) VALUES ('CA_EDUCACION', 'Educativa', '1', '2');
INSERT INTO `categoria_proyectos` (`cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`, `cat_tipo`) VALUES ('CA_VIVIENDA', 'Viviendas', '1', '2');
INSERT INTO `categoria_proyectos` (`cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`, `cat_tipo`) VALUES ('CA_TERRESTRE', 'Transporte Terrestre', '1', '2');
INSERT INTO `categoria_proyectos` (`cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`, `cat_tipo`) VALUES ('CA_URBANA', 'Urbana', '1', '2');
INSERT INTO `categoria_proyectos` (`cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`, `cat_tipo`) VALUES ('CA_SOCIAL', 'Social', '1', '2');
INSERT INTO `categoria_proyectos` (`cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`, `cat_tipo`) VALUES ('CA_SEGURIDAD', 'Seguridad', '1', '2');
INSERT INTO `categoria_proyectos` (`cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`, `cat_tipo`) VALUES ('CA_SANIDAD', 'Obras Sanitarias', '1', '2');
INSERT INTO `categoria_proyectos` (`cat_proy_codigo`, `cat_proy_nombre`, `cat_proy_activo`, `cat_tipo`) VALUES ('CA_VARIOS', 'Varios', '1', '2');

UPDATE proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = 14 WHERE proy_cat_cat_proy_fk = 1;
UPDATE proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = 15 WHERE proy_cat_cat_proy_fk = 2;
UPDATE proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = 16 WHERE proy_cat_cat_proy_fk = 3;
UPDATE proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = 17 WHERE proy_cat_cat_proy_fk = 4;
UPDATE proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = 18 WHERE proy_cat_cat_proy_fk = 7;
UPDATE proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = 19 WHERE proy_cat_cat_proy_fk = 8;
UPDATE proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = 20 WHERE proy_cat_cat_proy_fk = 9;
UPDATE proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = 21 WHERE proy_cat_cat_proy_fk = 12;
UPDATE proy_otros_cat_secundarias SET proy_cat_cat_proy_fk = 22 WHERE proy_cat_cat_proy_fk = 13;

ALTER TABLE `categoria_proyectos` 
ADD COLUMN `cat_org_fk` INT(11) NULL DEFAULT NULL AFTER `cat_icono_marker`;

ALTER TABLE `categoria_proyectos` 
ADD INDEX `cat_org_fk_idx` (`cat_org_fk` ASC);


-- Desde acá en adelante es v4.4-2

ALTER TABLE `mails_template` 
CHANGE COLUMN `mail_tmp_asunto` `mail_tmp_asunto` VARCHAR(200) NOT NULL ;

update cronogramas set cro_permiso_escritura=1 where cro_permiso_escritura=0 and cro_pk>0;

ALTER TABLE `prog_indices_pre` 
ADD COLUMN `progindpre_anio` DOUBLE(11,2) NULL DEFAULT NULL AFTER `progindpre_est_pre`,
ADD COLUMN `progindpre_ac` DOUBLE(11,2) NULL DEFAULT NULL AFTER `progindpre_anio`,
ADD COLUMN `progindpre_pv` DOUBLE(11,2) NULL DEFAULT NULL AFTER `progindpre_ac`;

ALTER TABLE `categoria_proyectos` 
ADD INDEX `cat_tipo_idx` (`cat_tipo` ASC);


-- Desde acá en adelante es v4.4-3

ALTER TABLE areas
ADD COLUMN area_activo TINYINT NULL DEFAULT 1,
ADD INDEX area_activo_idx (area_activo ASC);


-- Desde acá en adelante es v4.4-6

ALTER TABLE proy_replanificacion ADD proyreplan_generar_linea_base BIT(1) DEFAULT 0 NOT NULL;
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







ALTER TABLE proy_replanificacion MODIFY proyreplan_generar_linea_base BIT(1);

