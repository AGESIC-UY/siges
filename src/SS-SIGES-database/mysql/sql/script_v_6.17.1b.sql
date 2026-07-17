UPDATE ss_usuario
SET usu_correo_electronico = 'siges2@agesic.gub.uy'
WHERE usu_correo_electronico = 'siges@agesic.gub.uy';

UPDATE ss_usuario
SET usu_correo_electronico = 'siges@agesic.gub.uy'
WHERE usu_correo_electronico = 'admin';

INSERT INTO ss_configuraciones (
    cnf_org_fk,
    cnf_codigo,
    cnf_descripcion,
    cnf_valor,
    cnf_protegido,
    cnf_html,
    cnf_ult_usuario,
    cnf_ult_mod,
    cnf_ult_origen,
    cnf_version
) VALUES (
    NULL,
    'DOBLE_FACTOR_SEGUNDOS',
    'Indica la duracion del codigo de doble factor en segundos',
    '180',
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    1
);

INSERT INTO ss_configuraciones (
    cnf_org_fk,
    cnf_codigo,
    cnf_descripcion,
    cnf_valor,
    cnf_protegido,
    cnf_html,
    cnf_ult_usuario,
    cnf_ult_mod,
    cnf_ult_origen,
    cnf_version
) VALUES (
    NULL,
    'DOBLE_FACTOR_ACTIVO',
    'Indica si el doble factor va a estar activo',
    'true',
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    1
);
