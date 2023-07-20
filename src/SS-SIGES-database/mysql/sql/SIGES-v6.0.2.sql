ALTER TABLE entregables ADD COLUMN ent_es_referencia TINYINT(1);

UPDATE entregables SET ent_es_referencia = 0;


ALTER TABLE entregables ADD COLUMN ent_referido INT(11);
ALTER TABLE entregables ADD CONSTRAINT `entregable_referencia_fk` FOREIGN KEY (`ent_referido`) REFERENCES `entregables` (`ent_pk`);

