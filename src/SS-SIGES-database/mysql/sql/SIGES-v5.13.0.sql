DELETE from ss_configuraciones WHERE cnf_codigo = 'TOOLTIP_OBJETIVO_ESTRATEGICO';
UPDATE ss_configuraciones SET cnf_codigo = 'LABEL_OBJETIVO_ESTRATEGICO' WHERE cnf_codigo = 'LABEL_OBJ_ESTRE';

CREATE TABLE `calculo_indicadores_agendado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `programa` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `programa_fk` FOREIGN KEY (`programa`) REFERENCES `programas` (`prog_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO mutex_lock VALUES ('INDICADORES_PROGRAMA');