CREATE TABLE `wekan_tablero` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url_servidor` varchar(255) NOT NULL,
  `id_tablero` varchar(63) NOT NULL UNIQUE,
  `url_tablero` varchar(255) NOT NULL UNIQUE,
  `token` varchar(63),
  `id_campo_avance` varchar(63),
  `id_campo_proyecto` varchar(63),
  `id_integracion` varchar(63),
  `id_lista` varchar(63),
  `id_carril` varchar(63),
  PRIMARY KEY (`id`),
  KEY `wekan_tablero_id_idx` (`id`)
);

CREATE TABLE `wekan_vinculacion` (
  `tablero_fk` int(11) NOT NULL,
  `cronograma_fk` int(11) NOT NULL UNIQUE,
  `fecha_alta` DATETIME NOT NULL,
  `usuario_alta` varchar(255) NOT NULL,
  PRIMARY KEY (`tablero_fk`, `cronograma_fk`),
  KEY `wekan_vinculacion_tablero_fk_idx` (`tablero_fk`),
  KEY `wekan_vinculacion_cronograma_fk_idx` (`cronograma_fk`),
  CONSTRAINT `wekan_vinculacion_tablero_fk` FOREIGN KEY (`tablero_fk`) REFERENCES `wekan_tablero` (`id`),
  CONSTRAINT `wekan_vinculacion_cronograma_fk` FOREIGN KEY (`cronograma_fk`) REFERENCES `cronogramas` (`cro_pk`)
);

CREATE TABLE `wekan_tarjeta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_tarjeta` varchar(63) NOT NULL,
  `id_lista` varchar(63) NOT NULL,
  `entregable_fk` int(11) NOT NULL,
  `tablero_fk` int(11) NOT NULL,
  `cronograma_fk` int(11) NOT NULL,
  `fecha_alta` DATE NOT NULL,  
  PRIMARY KEY (`id`),
  KEY `wekan_tarjeta_id_idx` (`id`),
  KEY `wekan_tarjeta_id_tarjeta_idx` (`id_tarjeta`),
  CONSTRAINT `wekan_tarjeta_entregable_fk` FOREIGN KEY (`entregable_fk`) REFERENCES `entregables` (`ent_pk`),
  CONSTRAINT `wekan_tarjeta_tablero_fk` FOREIGN KEY (`tablero_fk`) REFERENCES `wekan_tablero` (`id`),
  CONSTRAINT `wekan_tarjeta_cronograma_fk` FOREIGN KEY (`cronograma_fk`) REFERENCES `cronogramas` (`cro_pk`)
);

CREATE TABLE `wekan_actividad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tarjeta_fk` int(11),
  `origen` varchar(31),
  `id_actividad` varchar(63),
  `descripcion` varchar(63),
  `id_usuario` varchar(63),
  `usuario` varchar(127),
  `actualizacion` varchar(127) NOT NULL,
  `valor_anterior` varchar(127),
  `valor_nuevo` varchar(127),
  `fecha_creacion` DATETIME NOT NULL,  
  `fecha_aplicacion_cambio` DATETIME,
  PRIMARY KEY (`id`),
  KEY `wekan_actividad_id_idx` (`id`),
  CONSTRAINT `wekan_actividad_tarjeta_fk` FOREIGN KEY (`tarjeta_fk`) REFERENCES `wekan_tarjeta` (`id`)
);
