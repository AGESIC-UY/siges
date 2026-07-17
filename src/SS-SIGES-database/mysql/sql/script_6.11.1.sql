ALTER TABLE ss_usuario ADD usu_puede_exportar boolean DEFAULT false ;
ALTER TABLE aud_ss_usuario ADD usu_puede_exportar boolean DEFAULT false ;

ALTER TABLE ss_usuario
DROP COLUMN usu_puede_exportar;

ALTER TABLE aud_ss_usuario
DROP COLUMN usu_puede_exportar;

