DELETE from grupo_trabajo_operacion_detalle
where gto_operacion_fk IN (
	SELECT opd_pk
	From operacion_detalle 
	WHERE opd_codigo = 'MON'
);

DELETE FROM operacion_detalle
WHERE opd_codigo ='MON';