CREATE TABLE `etiqueta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eti_org_fk` int(11) NOT NULL,
  `eti_codigo` varchar(145) NOT NULL,
  `eti_valor` varchar(1000) NOT NULL,
  `eti_version` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `eti_org_unique` (`eti_org_fk`,`eti_codigo`),
  CONSTRAINT `etiqueta_organismo_FK` FOREIGN KEY (`eti_org_fk`) REFERENCES `organismos` (`org_pk`)
);
