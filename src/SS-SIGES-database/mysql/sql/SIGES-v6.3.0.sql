INSERT INTO `siges`.`mails_template_defecto`
(`id`,`codigo`,`asunto`,`mensaje`,`version`) VALUES
(null,'MAIL_SOL_REPLANIFICACION','Solicitud de Replanificación','<h2> 	Solicitud de Replanificación</h2> <p> 	Se generó una solicitud de replanificación para el #TIPO_PROG_PROY# &quot;#NOMBRE_PROG_PROY#&quot;.</p> <div> 	#ORGANISMO_NOMBRE#</div> <div> 	#ORGANISMO_DIRECCION#</div> <div> 	#URL_SISTEMA#</div>',1);


INSERT INTO siges.mails_template (mail_tmp_pk, mail_tmp_cod, mail_tmp_org_fk, mail_tmp_asunto, mail_tmp_mensaje, version)
SELECT null, mld.codigo as mail_tmp_cod, mail_tmp_org_fk, mld.asunto as mail_tmp_asunto, mld.mensaje as mail_tmp_mensaje, 0 as version
FROM (SELECT distinct mail_tmp_org_fk FROM siges.mails_template) ml 
jOIN (SELECT * FROM siges.mails_template_defecto WHERE codigo = 'MAIL_SOL_REPLANIFICACION') mld

