ALTER TABLE `adquisicion` 
ADD COLUMN `adq_tipo_registro` VARCHAR(40) NULL AFTER `adq_compartida_usuario_fk`;

ALTER TABLE `pagos` 
ADD COLUMN `pag_proveedor_fk` INT(11) NULL AFTER `pag_contr_porcentaje`,
ADD INDEX `pagos_proveedor_idx` (`pag_proveedor_fk` ASC);

ALTER TABLE `pagos` 
ADD CONSTRAINT `pagos_proveedor`
  FOREIGN KEY (`pag_proveedor_fk`)
  REFERENCES `organi_int_prove` (`orga_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `adquisicion` 
ADD COLUMN `adq_arrastre` BIT(1) NULL DEFAULT b'0' AFTER `adq_tipo_registro`;

ALTER TABLE `adquisicion` 
ADD COLUMN `adq_fecha_estimada_inicio_compra` DATE NULL AFTER `adq_arrastre`;

ALTER TABLE `adquisicion` 
ADD COLUMN `adq_fecha_esperada_inicio_ejecucion` DATE NULL DEFAULT NULL AFTER `adq_fecha_estimada_inicio_compra`;

CREATE TABLE `tipos_adquisicion` (
  `tip_adq_pk` int(11) NOT NULL AUTO_INCREMENT,
  `tip_adq_nombre` varchar(100) NOT NULL,
  `tip_adq_descripcion` varchar(300) NOT NULL,
  `tip_adq_habilitado` TINYINT(3) NOT NULL DEFAULT '1',
  PRIMARY KEY (`tip_adq_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `adquisicion` 
ADD COLUMN `adq_tipo_adquisicion_fk` INT(11) NULL DEFAULT NULL AFTER `adq_fecha_esperada_inicio_ejecucion`;

ALTER TABLE `adquisicion` 
ADD INDEX `fk_adquisicion_tipo_adquisicion_idx` (`adq_tipo_adquisicion_fk` ASC);
ALTER TABLE `adquisicion` 
ADD CONSTRAINT `fk_adquisicion_tipo_adquisicion`
  FOREIGN KEY (`adq_tipo_adquisicion_fk`)
  REFERENCES `tipos_adquisicion` (`tip_adq_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

INSERT INTO `tipos_adquisicion` (`tip_adq_nombre`, `tip_adq_descripcion`, `tip_adq_habilitado`) VALUES ('Bienes', '', '1');
INSERT INTO `tipos_adquisicion` (`tip_adq_nombre`, `tip_adq_descripcion`, `tip_adq_habilitado`) VALUES ('Servicios distintos de consultoría', '', '1');
INSERT INTO `tipos_adquisicion` (`tip_adq_nombre`, `tip_adq_descripcion`, `tip_adq_habilitado`) VALUES ('Servicios de consultoría', '', '1');

ALTER TABLE `adquisicion` 
ADD COLUMN `adq_id_adquisicion` VARCHAR(255) NULL DEFAULT NULL AFTER `adq_tipo_adquisicion_fk`;

CREATE TABLE `centros_costo` (
  `cen_cos_pk` int(11) NOT NULL AUTO_INCREMENT,
  `cen_cos_nombre` varchar(100) NOT NULL,
  `cen_cos_descripcion` varchar(300) NOT NULL,
  `cen_cos_habilitado` TINYINT(3) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cen_cos_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `adquisicion` 
ADD COLUMN `adq_centro_costo_fk` INT(11) NULL DEFAULT NULL AFTER `adq_id_adquisicion`;

ALTER TABLE `adquisicion` 
ADD INDEX `fk_adquisicion_centro_costo_idx` (`adq_centro_costo_fk` ASC);
ALTER TABLE `adquisicion` 
ADD CONSTRAINT `fk_adquisicion_centro_costo`
  FOREIGN KEY (`adq_centro_costo_fk`)
  REFERENCES `centros_costo` (`cen_cos_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `fuentes_procedimiento_compra` (
  `fue_pro_com_pk` int(11) NOT NULL AUTO_INCREMENT,
  `fue_pro_com_fuente` varchar(100) NOT NULL,
  `fue_pro_com_procedimiento_compra` varchar(100) NOT NULL,
  `fue_pro_com_habilitado` TINYINT(3) NOT NULL DEFAULT '1',
  PRIMARY KEY (`fue_pro_com_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `causales_compra` (
  `cau_com_pk` int(11) NOT NULL AUTO_INCREMENT,
  `cau_com_nombre` varchar(100) NOT NULL,
  `cau_com_descripcion` varchar(300) NOT NULL,
  `cau_com_habilitado` TINYINT(3) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cau_com_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `fue_pro_com_cau_com` (
`fue_pro_com_pk` int(11) NOT NULL,
`cau_com_pk` int(11) NOT NULL,
KEY `fuente_proc_compra_key` (`fue_pro_com_pk`),
KEY `causal_compra_key` (`cau_com_pk`),
CONSTRAINT `fuente_proc_compras_key` FOREIGN KEY (`fue_pro_com_pk`) REFERENCES `fuentes_procedimiento_compra` (`fue_pro_com_pk`),
CONSTRAINT `causal_compras_key` FOREIGN KEY (`cau_com_pk`) REFERENCES `causales_compra` (`cau_com_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `adquisicion` 
ADD COLUMN `adq_causal_compra_fk` INT(11) NULL DEFAULT NULL AFTER `adq_centro_costo_fk`;

ALTER TABLE `adquisicion` 
ADD INDEX `fk_adquisicion_causal_idx` (`adq_causal_compra_fk` ASC);
ALTER TABLE `adquisicion` 
ADD CONSTRAINT `fk_adquisicion_causal`
  FOREIGN KEY (`adq_causal_compra_fk`)
  REFERENCES `causales_compra` (`cau_com_pk`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

