ALTER TABLE ss_usu_ofi_roles ADD usu_ofi_roles_puede_exportar boolean DEFAULT false ;
ALTER TABLE aud_ss_usu_ofi_roles ADD usu_ofi_roles_puede_exportar boolean DEFAULT false ;
UPDATE operacion_detalle
SET  opd_descripcion='Proveedores / Clientes'
WHERE opd_codigo='ORG';


