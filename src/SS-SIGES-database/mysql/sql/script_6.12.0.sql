ALTER TABLE ss_usuario ADD usu_fin_bloqueo datetime DEFAULT NULL;
ALTER TABLE aud_ss_usuario ADD usu_fin_bloqueo datetime DEFAULT NULL;

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('MENSAJE_ERROR_LOGEO', 'Indica mensaje de error de logeo', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk)
Select 'MENSAJE_ERROR_LOGEO','Indica mensaje de error de logeo','false',0 ,org_pk from organismos;


ALTER TABLE ss_usuario ADD usu_fin_hash_token datetime DEFAULT NULL;
ALTER TABLE aud_ss_usuario ADD usu_fin_hash_token datetime DEFAULT NULL;
ALTER TABLE ss_usuario ADD usu_hash_token varchar(100) DEFAULT NULL;
ALTER TABLE aud_ss_usuario ADD usu_hash_token varchar(100) DEFAULT NULL;


UPDATE mails_template
SET mail_tmp_mensaje='<p> 	Estimado #NOMBRE#, para cambiar su contrase&ntilde;a en SIGES haga clic en el enlace</p> 
<p> 	<a href="#URL_SISTEMA#">Cambiar contrase&ntilde;a</a></p>
<p> 	Este link vence en breves minutos, en caso de demoras, deberá repetir el proceso de reseteo de clave</p>
'
WHERE mail_tmp_cod='MAIL_CAMBIO_CONTRASENIA';


UPDATE mails_template_defecto
SET mensaje='<p> 	Estimado #NOMBRE#, para cambiar su contrase&ntilde;a en SIGES haga clic en el enlace</p> 
<p> 	<a href="#URL_SISTEMA#">Cambiar contrase&ntilde;a</a></p>
<p> 	Este link vence en breves minutos, en caso de demoras, deberá repetir el proceso de reseteo de clave</p>
'
WHERE  codigo='MAIL_CAMBIO_CONTRASENIA';

