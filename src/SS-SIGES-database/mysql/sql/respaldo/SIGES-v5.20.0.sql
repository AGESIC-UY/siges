CREATE TABLE `ss_configuraciones_defecto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(145) DEFAULT NULL,
  `descripcion` varchar(245) DEFAULT NULL,
  `valor` varchar(1000) DEFAULT NULL,
  `es_html` tinyint(1) DEFAULT NULL,
  `ultima_modificacion` date DEFAULT NULL,
  `usuario_modificacion` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cnf_defecto_codigo` (`codigo`)
);

UPDATE ss_configuraciones_defecto set valor = 'true' WHERE codigo = 'APROBACION_PMOF';
UPDATE ss_configuraciones_defecto set valor = 'true' WHERE codigo = 'APROBACION_REPLANIFICACION_PMOF';
UPDATE ss_configuraciones_defecto set valor = 'true' WHERE codigo = 'ENVIO_NOTIFICACIONES';
UPDATE ss_configuraciones_defecto set valor = 'true' WHERE codigo = 'ADJUNTO_MODIFICA_PRESUPUESTO';
UPDATE ss_configuraciones_defecto set valor = 'siges@mtaagesic.gub.uy' WHERE codigo = 'MAIL_FROM';
UPDATE ss_configuraciones_defecto set valor = 'true' WHERE codigo = 'FILTRO_INICIO_POR_AREAS';

CREATE TABLE `mails_template_defecto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(45) NOT NULL,
  `asunto` varchar(200) NOT NULL,
  `mensaje` varchar(5000) NOT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
);

CREATE TABLE `notificacion_defecto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(45) NOT NULL,
  `descripcion` varchar(245) NOT NULL,
  `asunto` varchar(255) DEFAULT NULL,
  `mensaje` varchar(5000) NOT NULL,
  `gerente_adjunto` tinyint(4) DEFAULT NULL,
  `pmof` tinyint(4) DEFAULT NULL,
  `pmot` tinyint(4) DEFAULT NULL,
  `sponsor` tinyint(4) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
);

ALTER TABLE proyectos ADD COLUMN `fecha_cambio_activacion` date DEFAULT NULL;
ALTER TABLE proyectos ADD COLUMN `usuario_cambio_activacion` varchar(255);