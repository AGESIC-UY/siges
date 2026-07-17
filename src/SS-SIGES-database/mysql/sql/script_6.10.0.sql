INSERT INTO operacion_detalle (opd_codigo, opd_descripcion) VALUES('ADQ_GES', 'Adquisiciones gestión');


CREATE TABLE log_adquisiciones (
    lad_pk BIGINT auto_increment NOT NULL,
    lad_adquisicion_fk integer NULL,
    lad_fecha datetime DEFAULT NULL,
    lad_usuario varchar(100) DEFAULT NULL,
  lad_detalle varchar(250) DEFAULT NULL,
  CONSTRAINT log_adquisicion_PK PRIMARY KEY (lad_pk),
   CONSTRAINT log_adquisicion_adquisicion_FK FOREIGN KEY (lad_adquisicion_fk) REFERENCES adquisicion(adq_pk)
);





