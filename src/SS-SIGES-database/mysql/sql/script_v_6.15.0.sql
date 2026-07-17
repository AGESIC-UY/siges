UPDATE operacion_detalle
SET  opd_descripcion='Gestión de Adquisiciones'
WHERE opd_codigo='ADQ_GES';

INSERT INTO siges.operacion_detalle
( opd_codigo, opd_descripcion)
VALUES( 'INFO_ADQ', 'Adquisición Log');

