#---- -- #!/usr/bin/sh
#!/usr/bin/env bash

properties="extraccion_datos_organismo_multi.properties";

if [ -z "$properties" ]; then
	echo "Debe ingresar un archivo properties con los siguientes parámetros:";
	echo "- organismos: Identificadores de los organismos que desean extraer separados por ',' . Ejemplo: 1,2,3";
	echo "- db_user: usuario de la base de datos ";
	echo "- db_pass: contraseña de la base de datos";
	echo "- db_name: nombre de la base de datos";
	echo "- db_host: hostname o ip de la base de datos";
	echo "- db_port: puerto de la base de datos";
	echo "- output_dir: directorio donde se crear el archivo .dump";
	exit;
fi

properties="$(dirname $properties)/$(basename $properties)";

. $properties;
organismos="($organismos)";

now=`date +%Y-%m-%d_%H:%M:%S`
cmd="mysqldump --lock-all-tables --user=$db_user -p$db_pass --no-create-db --host=$db_host --port=$db_port";

output_file="${db_name}_org_${organismos}_${now}.dump";
output="$output_dir/$output_file";

# extraer nivel 0

$cmd --where="organismos.org_pk IN $organismos" $db_name organismos --result-file=/tmp/01_organismos.sql &> /dev/null; echo "[01] completado: organismos";

# extraer nivel 1

$cmd --where="areas.area_org_fk IN $organismos" $db_name areas --result-file=/tmp/02_areas.sql &> /dev/null; echo "[02] completado: areas";
$cmd --where="areas_tags.areatag_org_fk IN $organismos" $db_name areas_tags --result-file=/tmp/03_areas_tags.sql &> /dev/null; echo "[03] completado: areas_tags";
$cmd --where="area_conocimiento.con_org_fk IN $organismos" $db_name area_conocimiento --result-file=/tmp/04_area_conocimiento.sql &> /dev/null; echo "[04] completado: area_conocimiento";
$cmd --where="area_organi_int_prove.areaorgintprov_org_fk IN $organismos" $db_name area_organi_int_prove --result-file=/tmp/05_area_organi_int_prove.sql &> /dev/null; echo "[05] completado: area_organi_int_prove";
$cmd --where="etapa.eta_org_fk IN $organismos" $db_name etapa --result-file=/tmp/06_etapa.sql &> /dev/null; echo "[06] completado: etapa";
$cmd --where="fuente_financiamiento.fue_org_fk IN $organismos" $db_name fuente_financiamiento --result-file=/tmp/07_fuente_financiamiento.sql &> /dev/null; echo "[07] completado: fuente_financiamiento";
$cmd --where="lecc_aprendidas.lecapr_org_fk IN $organismos" $db_name lecc_aprendidas --result-file=/tmp/08_lecc_aprendidas.sql &> /dev/null; echo "[08] completado: lecc_aprendidas";
$cmd --where="mails_template.mail_tmp_org_fk IN $organismos" $db_name mails_template --result-file=/tmp/09_mails_template.sql &> /dev/null; echo "[09] completado: mails_template";
$cmd --where="organi_int_prove.orga_org_fk IN $organismos" $db_name organi_int_prove --result-file=/tmp/10_organi_int_prove.sql &> /dev/null; echo "[10] completado: organi_int_prove";
$cmd --where="programas.prog_org_fk IN $organismos" $db_name programas --result-file=/tmp/11_programas.sql &> /dev/null; echo "[11] completado: programas";
$cmd --where="proyectos.proy_org_fk IN $organismos" $db_name proyectos --result-file=/tmp/12_proyectos.sql &> /dev/null; echo "[12] completado: proyectos";
$cmd --where="roles_interesados.rolint_org_fk IN $organismos" $db_name roles_interesados --result-file=/tmp/13_roles_interesados.sql &> /dev/null; echo "[13] completado: roles_interesados";
$cmd --where="temas_calidad.tca_org_fk IN $organismos" $db_name temas_calidad --result-file=/tmp/14_temas_calidad.sql &> /dev/null; echo "[14] completado: temas_calidad";
$cmd --where="tipo_documento.tipodoc_org_fk IN $organismos" $db_name tipo_documento --result-file=/tmp/15_tipo_documento.sql &> /dev/null; echo "[15] completado: tipo_documento";
$cmd --where="tipo_gasto.tipogas_org_fk IN $organismos" $db_name tipo_gasto --result-file=/tmp/16_tipo_gasto.sql &> /dev/null; echo "[16] completado: tipo_gasto";
$cmd --where="valor_hora.val_hor_org_fk IN $organismos" $db_name valor_hora --result-file=/tmp/17_valor_hora.sql &> /dev/null; echo "[17] completado: valor_hora";



# extraer nivel 2

$cmd --where="adquisicion.adq_pk IN (SELECT l2.adq_pk FROM fuente_financiamiento as l1 INNER JOIN adquisicion as l2 ON (l1.fue_pk = l2.adq_fuente_fk) WHERE l1.fue_org_fk IN $organismos)" $db_name adquisicion --result-file=/tmp/18_adquisicion.sql &> /dev/null; echo "[18] completado: adquisicion";
$cmd --where="calidad.cal_pk IN (SELECT l2.cal_pk FROM proyectos as l1 INNER JOIN calidad as l2 ON (l1.proy_pk = l2.cal_proy_fk) WHERE l1.proy_org_fk IN $organismos)" $db_name calidad --result-file=/tmp/19_calidad.sql &> /dev/null; echo "[19] completado: calidad";
$cmd --where="gastos.gas_pk IN (SELECT l2.gas_pk FROM proyectos as l1 INNER JOIN gastos as l2 ON (l1.proy_pk = l2.gas_proy_fk) WHERE l1.proy_org_fk IN $organismos)" $db_name gastos --result-file=/tmp/20_gastos.sql &> /dev/null; echo "[20] completado: gastos";
$cmd --where="interesados.int_pk IN (SELECT l2.int_pk FROM organi_int_prove as l1 INNER JOIN interesados as l2 ON (l1.orga_pk = l2.int_orga_fk) WHERE l1.orga_org_fk IN $organismos)" $db_name interesados --result-file=/tmp/21_interesados.sql &> /dev/null; echo "[21] completado: interesados";
$cmd --where="latlng_proyectos.latlng_proy_fk IS NULL OR latlng_proyectos.latlng_pk IN (SELECT l2.latlng_pk FROM proyectos as l1 INNER JOIN latlng_proyectos as l2 ON (l1.proy_pk = l2.latlng_proy_fk OR l2.latlng_proy_fk = NULL) WHERE l1.proy_org_fk IN $organismos)" $db_name latlng_proyectos --result-file=/tmp/22_latlng_proyectos.sql &> /dev/null; echo "[22] completado: latlng_proyectos";
$cmd --where="lecapr_areacon.lecaprcon_con_fk IN (SELECT l2.lecaprcon_con_fk FROM area_conocimiento as l1 INNER JOIN lecapr_areacon as l2 ON (l1.con_pk = l2.lecaprcon_con_fk) WHERE l1.con_org_fk IN $organismos)" $db_name lecapr_areacon --result-file=/tmp/23_lecapr_areacon.sql &> /dev/null; echo "[23] completado: lecapr_areacon";
$cmd --where="media_proyectos.media_pk IN (SELECT l2.media_pk FROM proyectos as l1 INNER JOIN media_proyectos as l2 ON (l1.proy_pk = l2.media_proy_fk) WHERE l1.proy_org_fk IN $organismos)" $db_name media_proyectos --result-file=/tmp/24_media_proyectos.sql &> /dev/null; echo "[24] completado: media_proyectos";
$cmd --where="notificacion_instancia.notinst_pk IN (SELECT l2.notinst_pk FROM proyectos as l1 INNER JOIN notificacion_instancia as l2 ON (l1.proy_pk = l2.notinst_proy_fk) WHERE l1.proy_org_fk IN $organismos)" $db_name notificacion_instancia --result-file=/tmp/25_notificacion_instancia.sql &> /dev/null; echo "[25] completado: notificacion_instancia";
$cmd --where="participantes.part_pk IN (SELECT l2.part_pk FROM proyectos as l1 INNER JOIN participantes as l2 ON (l1.proy_pk = l2.part_proy_fk) WHERE l1.proy_org_fk IN $organismos)" $db_name participantes --result-file=/tmp/26_participantes.sql &> /dev/null; echo "[26] completado: participantes";
$cmd --where="personas.pers_pk IN (SELECT l2.pers_pk FROM organi_int_prove as l1 INNER JOIN personas as l2 ON (l1.orga_pk = l2.pers_orga_fk) WHERE l1.orga_org_fk IN $organismos)" $db_name personas --result-file=/tmp/27_personas.sql &> /dev/null; echo "[27] completado: personas";
$cmd --where="presupuesto.pre_pk IN (SELECT l2.pre_pk FROM proyectos as l1 INNER JOIN presupuesto as l2 ON (l1.proy_pre_fk = l2.pre_pk) WHERE l1.proy_org_fk IN $organismos)" $db_name presupuesto --result-file=/tmp/28_presupuesto.sql &> /dev/null; echo "[28] completado: presupuesto";
$cmd --where="prog_docs.progdocs_prog_pk IN (SELECT l2.progdocs_prog_pk FROM programas as l1 INNER JOIN prog_docs as l2 ON (l1.prog_pk = l2.progdocs_prog_pk) WHERE l1.prog_org_fk IN $organismos)" $db_name prog_docs --result-file=/tmp/29_prog_docs.sql &> /dev/null; echo "[29] completado: prog_docs";
$cmd --where="prog_docs_obl.progdocsobl_docs_pk IN (SELECT l2.progdocsobl_docs_pk FROM programas as l1 INNER JOIN prog_docs_obl as l2 ON (l1.prog_pk = l2.progdocsobl_prog_pk) WHERE l1.prog_org_fk IN $organismos)" $db_name prog_docs_obl --result-file=/tmp/30_prog_docs_obl.sql &> /dev/null; echo "[30] completado: prog_docs_obl";
$cmd --where="prog_int.progint_prog_pk IN (SELECT l2.progint_prog_pk FROM programas as l1 INNER JOIN prog_int as l2 ON (l1.prog_pk = l2.progint_prog_pk) WHERE l1.prog_org_fk IN $organismos)" $db_name prog_int --result-file=/tmp/31_prog_int.sql &> /dev/null; echo "[31] completado: prog_int";
$cmd --where="prog_lectura_area.proglectarea_prog_pk IN (SELECT l2.proglectarea_prog_pk FROM areas as l1 INNER JOIN prog_lectura_area as l2 ON (l1.area_pk = l2.proglectarea_area_pk) WHERE l1.area_org_fk IN $organismos)" $db_name prog_lectura_area --result-file=/tmp/32_prog_lectura_area.sql &> /dev/null; echo "[32] completado: prog_lectura_area";
$cmd --where="prog_pre.progpre_prog_fk IN (SELECT l2.progpre_prog_fk FROM programas as l1 INNER JOIN prog_pre as l2 ON (l1.prog_pk = l2.progpre_prog_fk) WHERE l1.prog_org_fk IN $organismos)" $db_name prog_pre --result-file=/tmp/33_prog_pre.sql &> /dev/null; echo "[33] completado: prog_pre";
$cmd --where="prog_tags.progtag_prog_pk IN (SELECT l2.progtag_prog_pk FROM areas_tags as l1 INNER JOIN prog_tags as l2 ON (l1.arastag_pk = l2.progtag_area_pk) WHERE l1.areatag_org_fk IN $organismos)" $db_name prog_tags --result-file=/tmp/34_prog_tags.sql &> /dev/null; echo "[34] completado: prog_tags";
$cmd --where="proy_docs.proydoc_proy_pk IN (SELECT l2.proydoc_proy_pk FROM proyectos as l1 INNER JOIN proy_docs as l2 ON (l1.proy_pk = l2.proydoc_proy_pk) WHERE l1.proy_org_fk IN $organismos)" $db_name proy_docs --result-file=/tmp/35_proy_docs.sql &> /dev/null; echo "[35] completado: proy_docs";
$cmd --where="proy_int.proyint_proy_pk IN (SELECT l2.proyint_proy_pk FROM proyectos as l1 INNER JOIN proy_int as l2 ON (l1.proy_pk = l2.proyint_proy_pk) WHERE l1.proy_org_fk IN $organismos)" $db_name proy_int --result-file=/tmp/36_proy_int.sql &> /dev/null; echo "[36] completado: proy_int";
$cmd --where="proy_lectura_area.proglectarea_area_pk IN (SELECT l2.proglectarea_area_pk FROM areas as l1 INNER JOIN proy_lectura_area as l2 ON (l1.area_pk = l2.proglectarea_area_pk) WHERE l1.area_org_fk IN $organismos)" $db_name proy_lectura_area --result-file=/tmp/37_proy_lectura_area.sql &> /dev/null; echo "[37] completado: proy_lectura_area";
$cmd --where="proy_otros_datos.proy_otr_pk IN (SELECT l2.proy_otr_pk FROM proyectos as l1 INNER JOIN proy_otros_datos as l2 ON (l1.proy_otr_dat_fk = l2.proy_otr_pk) WHERE l1.proy_org_fk IN $organismos)" $db_name proy_otros_datos --result-file=/tmp/38_proy_otros_datos.sql &> /dev/null; echo "[38] completado: proy_otros_datos";
$cmd --where="proy_pre.proypre_proy_fk IN (SELECT l2.proypre_proy_fk FROM proyectos as l1 INNER JOIN proy_pre as l2 ON (l1.proy_pk = l2.proypre_proy_fk) WHERE l1.proy_org_fk IN $organismos)" $db_name proy_pre --result-file=/tmp/39_proy_pre.sql &> /dev/null; echo "[39] completado: proy_pre";
$cmd --where="proy_publica_hist.proy_publica_pk IN (SELECT l2.proy_publica_pk FROM proyectos as l1 INNER JOIN proy_publica_hist as l2 ON (l1.proy_pk = l2.proy_publica_proy_fk) WHERE l1.proy_org_fk IN $organismos)" $db_name proy_publica_hist --result-file=/tmp/40_proy_publica_hist.sql &> /dev/null; echo "[40] completado: proy_publica_hist";
$cmd --where="proy_replanificacion.proyreplan_pk IN (SELECT l2.proyreplan_pk FROM proyectos as l1 INNER JOIN proy_replanificacion as l2 ON (l1.proy_pk = l2.proyreplan_proy_fk) WHERE l1.proy_org_fk IN $organismos)" $db_name proy_replanificacion --result-file=/tmp/41_proy_replanificacion.sql &> /dev/null; echo "[41] completado: proy_replanificacion";
$cmd --where="proy_sitact_historico.proy_sitact_hist_pk IN (SELECT l2.proy_sitact_hist_pk FROM proyectos as l1 INNER JOIN proy_sitact_historico as l2 ON (l1.proy_pk = l2.proy_sitact_proy_fk) WHERE l1.proy_org_fk IN $organismos)" $db_name proy_sitact_historico --result-file=/tmp/42_proy_sitact_historico.sql &> /dev/null; echo "[42] completado: proy_sitact_historico";
$cmd --where="proy_tags.proytag_proy_pk IN (SELECT l2.proytag_proy_pk FROM areas_tags as l1 INNER JOIN proy_tags as l2 ON (l1.arastag_pk = l2.proytag_area_pk) WHERE l1.areatag_org_fk IN $organismos)" $db_name proy_tags --result-file=/tmp/43_proy_tags.sql &> /dev/null; echo "[43] completado: proy_tags";
$cmd --where="registros_horas.rh_pk IN (SELECT l2.rh_pk FROM proyectos as l1 INNER JOIN registros_horas as l2 ON (l1.proy_pk = l2.rh_proy_fk) WHERE l1.proy_org_fk IN $organismos)" $db_name registros_horas --result-file=/tmp/44_registros_horas.sql &> /dev/null; echo "[44] completado: registros_horas";
$cmd --where="riesgos.risk_pk IN (SELECT l2.risk_pk FROM proyectos as l1 INNER JOIN riesgos as l2 ON (l1.proy_pk = l2.risk_proy_fk) WHERE l1.proy_org_fk IN $organismos)" $db_name riesgos --result-file=/tmp/45_riesgos.sql &> /dev/null; echo "[45] completado: riesgos";
$cmd --where="ss_personas.per_id IN (SELECT l2.per_id FROM tipo_documento as l1 INNER JOIN ss_personas as l2 ON (l1.tipdoc_pk = l2.per_tipo_doc) WHERE l1.tipodoc_org_fk IN $organismos)" $db_name ss_personas --result-file=/tmp/46_ss_personas.sql &> /dev/null; echo "[46] completado: ss_personas";
$cmd --where="ss_usuario.usu_id IN (SELECT l2.usu_id FROM ss_usu_ofi_roles as l1 INNER JOIN ss_usuario as l2 ON (l1.usu_ofi_roles_usuario = l2.usu_id) WHERE l1.usu_ofi_roles_oficina IN $organismos)" $db_name ss_usuario --result-file=/tmp/47_ss_usuario.sql &> /dev/null; echo "[47] completado: ss_usuario";
$cmd --where="ss_usu_ofi_roles.usu_ofi_roles_id IN (SELECT l2.usu_ofi_roles_id FROM areas as l1 INNER JOIN ss_usu_ofi_roles as l2 ON (l1.area_pk = l2.usu_ofi_roles_usu_area) WHERE l1.area_org_fk IN $organismos)" $db_name ss_usu_ofi_roles --result-file=/tmp/48_ss_usu_ofi_roles.sql &> /dev/null; echo "[48] completado: ss_usu_ofi_roles";
$cmd --where="tipo_documento_instancia.tipodoc_inst_pk IN (SELECT l2.tipodoc_inst_pk FROM tipo_documento as l1 INNER JOIN tipo_documento_instancia as l2 ON (l1.tipdoc_pk = l2.tipodoc_inst_tipodoc_fk) WHERE l1.tipodoc_org_fk IN $organismos)" $db_name tipo_documento_instancia --result-file=/tmp/49_tipo_documento_instancia.sql &> /dev/null; echo "[49] completado: tipo_documento_instancia";


# extraer nivel 3

$cmd  --where="busq_filtro.busq_filtro_pk IN (SELECT l3.busq_filtro_pk FROM areas as l1 INNER JOIN ss_usuario as l2 ON (l1.area_pk = l2.usu_area_org) INNER JOIN busq_filtro as l3 ON (l2.usu_id = l3.busq_filtro_usu_fk) WHERE l1.area_org_fk IN $organismos)" $db_name busq_filtro --result-file=/tmp/50_busq_filtro.sql &> /dev/null; echo "[50] completado: busq_filtro";
$cmd  --where="devengado.dev_pk IN (SELECT l3.dev_pk FROM fuente_financiamiento as l1 INNER JOIN adquisicion as l2 ON (l1.fue_pk = l2.adq_fuente_fk) INNER JOIN devengado as l3 ON (l2.adq_pk = l3.dev_adq_fk) WHERE l1.fue_org_fk IN $organismos)" $db_name devengado --result-file=/tmp/51_devengado.sql &> /dev/null; echo "[51] completado: devengado";
$cmd  --where="documentos.docs_pk IN (SELECT l3.docs_pk FROM tipo_documento as l1 INNER JOIN tipo_documento_instancia as l2 ON (l1.tipdoc_pk = l2.tipodoc_inst_tipodoc_fk) INNER JOIN documentos as l3 ON (l2.tipodoc_inst_pk = l3.docs_tipodoc_fk) WHERE l1.tipodoc_org_fk IN $organismos)" $db_name documentos --result-file=/tmp/52_documentos.sql &> /dev/null; echo "[52] completado: documentos";
$cmd  --where="entregables.ent_pk IN (SELECT l3.ent_pk FROM proyectos as l1 INNER JOIN cronogramas as l2 ON (l1.proy_cro_fk = l2.cro_pk) INNER JOIN entregables as l3 ON (l2.cro_pk = l3.ent_cro_fk) WHERE l1.proy_org_fk IN $organismos)" $db_name entregables --result-file=/tmp/53_entregables.sql &> /dev/null; echo "[53] completado: entregables";
$cmd  --where="ent_hist_linea_base.enthist_pk IN (SELECT l3.enthist_pk FROM proyectos as l1 INNER JOIN proy_replanificacion as l2 ON (l1.proy_pk = l2.proyreplan_proy_fk) INNER JOIN ent_hist_linea_base as l3 ON (l2.proyreplan_pk = l3.enthist_replan_fk) WHERE l1.proy_org_fk IN $organismos)" $db_name ent_hist_linea_base --result-file=/tmp/54_ent_hist_linea_base.sql &> /dev/null; echo "[54] completado: ent_hist_linea_base";
$cmd  --where="pagos.pag_pk IN (SELECT l3.pag_pk FROM fuente_financiamiento as l1 INNER JOIN adquisicion as l2 ON (l1.fue_pk = l2.adq_fuente_fk) INNER JOIN pagos as l3 ON (l2.adq_pk = l3.pag_adq_fk) WHERE l1.fue_org_fk IN $organismos)" $db_name pagos --result-file=/tmp/55_pagos.sql &> /dev/null; echo "[55] completado: pagos";
$cmd  --where="proy_otros_cat_secundarias.proy_cat_proy_otros_fk IN (SELECT l3.proy_cat_proy_otros_fk FROM organi_int_prove as l1 INNER JOIN proy_otros_datos as l2 ON (l1.orga_pk = l2.proy_otr_cont_fk) INNER JOIN proy_otros_cat_secundarias as l3 ON (l2.proy_otr_pk = l3.proy_cat_proy_otros_fk) WHERE l1.orga_org_fk IN $organismos)" $db_name proy_otros_cat_secundarias --result-file=/tmp/56_proy_otros_cat_secundarias.sql &> /dev/null; echo "[56] completado: proy_otros_cat_secundarias";


# extraer nivel 4

$cmd  --where="doc_file.docfile_pk IN (SELECT l4.docfile_pk FROM tipo_documento as l1 INNER JOIN tipo_documento_instancia as l2 ON (l1.tipdoc_pk = l2.tipodoc_inst_tipodoc_fk) INNER JOIN documentos as l3 ON (l2.tipodoc_inst_pk = l3.docs_tipodoc_fk) INNER JOIN doc_file as l4 ON (l3.docs_pk = l4.docfile_doc_fk) WHERE l1.tipodoc_org_fk IN $organismos)" $db_name doc_file --result-file=/tmp/57_doc_file.sql &> /dev/null; echo "[57] completado: doc_file";
$cmd  --where="lineabase_historico.lineabase_pk IN (SELECT l4.lineabase_pk FROM areas as l1 INNER JOIN ss_usuario as l2 ON (l1.area_pk = l2.usu_area_org) INNER JOIN entregables as l3 ON (l2.usu_id = l3.ent_coord_usu_fk) INNER JOIN lineabase_historico as l4 ON (l3.ent_pk = l4.lineabase_entFk) WHERE l1.area_org_fk IN $organismos)" $db_name lineabase_historico --result-file=/tmp/58_lineabase_historico.sql &> /dev/null; echo "[58] completado: lineabase_historico";
$cmd  --where="productos.prod_pk IN (SELECT l4.prod_pk FROM areas as l1 INNER JOIN ss_usuario as l2 ON (l1.area_pk = l2.usu_area_org) INNER JOIN entregables as l3 ON (l2.usu_id = l3.ent_coord_usu_fk) INNER JOIN productos as l4 ON (l3.ent_pk = l4.prod_ent_fk) WHERE l1.area_org_fk IN $organismos)" $db_name productos --result-file=/tmp/59_productos.sql &> /dev/null; echo "[59] completado: productos";


# extraer nivel 5

$cmd  --where="prod_mes.prodmes_pk IN (SELECT l5.prodmes_pk FROM areas as l1 INNER JOIN ss_usuario as l2 ON (l1.area_pk = l2.usu_area_org) INNER JOIN entregables as l3 ON (l2.usu_id = l3.ent_coord_usu_fk) INNER JOIN productos as l4 ON (l3.ent_pk = l4.prod_ent_fk) INNER JOIN prod_mes as l5 ON (l4.prod_pk = l5.prodmes_prod_fk) WHERE l1.area_org_fk IN $organismos)" $db_name prod_mes --result-file=/tmp/60_prod_mes.sql &> /dev/null; echo "[60] completado: prod_mes";


# resto de las tablas

$cmd --where="amb_org_fk IN $organismos" $db_name ambito --result-file=/tmp/61_ambito.sql &> /dev/null; echo "[61] completado: ambito";
$cmd --where="cat_org_fk IN $organismos" $db_name categoria_proyectos --result-file=/tmp/62_categoria_proyectos.sql &> /dev/null; echo "[62] completado: categoria_proyectos";
$cmd --where="not_org_fk IN $organismos" $db_name notificacion --result-file=/tmp/63_notificacion.sql &> /dev/null; echo "[63] completado: notificacion";
$cmd --where="p_crono_org_fk IN $organismos" $db_name plantilla_cronograma --result-file=/tmp/64_plantilla_cronograma.sql &> /dev/null; echo "[64] completado: plantilla_cronograma";
$cmd --where="p_entregables_id IN (SELECT l2.p_entregables_id FROM plantilla_cronograma as l1 INNER JOIN plantilla_entregables as l2 ON (l2.p_entregable_p_cro_fk = l1.p_crono_pk) WHERE l1.p_crono_org_fk IN $organismos)" $db_name plantilla_entregables --result-file=/tmp/65_plantilla_entregables.sql &> /dev/null; echo "[65] completado: plantilla_entregables";

$cmd --where="cro_pk IN (SELECT l2.cro_pk FROM proyectos as l1 INNER JOIN cronogramas as l2 ON (l1.proy_cro_fk = l2.cro_pk) WHERE l1.proy_org_fk IN $organismos UNION DISTINCT SELECT l2.cro_pk FROM programas as l1 INNER JOIN cronogramas as l2 ON (l1.prog_cro_fk = l2.cro_pk) WHERE l1.prog_org_fk IN $organismos )" $db_name cronogramas --result-file=/tmp/66_cronogramas.sql &> /dev/null; echo "[66] completado: cronogramas";

$cmd $db_name departamentos --result-file=/tmp/67_departamentos.sql &> /dev/null; echo "[67] completado: departamentos";
$cmd $db_name estados --result-file=/tmp/68_estados.sql &> /dev/null; echo "[68] completado: estados";
$cmd $db_name estados_publicacion --result-file=/tmp/69_estados_publicacion.sql &> /dev/null; echo "[69] completado: estados_publicacion";
$cmd $db_name image --result-file=/tmp/70_image.sql &> /dev/null; echo "[70] completado: image";
$cmd $db_name moneda --result-file=/tmp/71_moneda.sql &> /dev/null; echo "[71] completado: moneda";
$cmd $db_name pge_configuraciones --result-file=/tmp/72_pge_configuraciones.sql &> /dev/null; echo "[72] completado: pge_configuraciones";
$cmd $db_name pge_invocaciones --result-file=/tmp/73_pge_invocaciones.sql &> /dev/null; echo "[73] completado: pge_invocaciones";

$cmd $db_name prog_indices --result-file=/tmp/74_prog_indices.sql &> /dev/null; echo "[74] completado: prog_indices";
$cmd $db_name prog_indices_pre --result-file=/tmp/75_prog_indices_pre.sql &> /dev/null; echo "[75] completado: prog_indices_pre";
$cmd --where="organismo IN $organismos" $db_name programas_proyectos --result-file=/tmp/76_programas_proyectos.sql &> /dev/null; echo "[76] completado: programas_proyectos";
$cmd $db_name ss_configuraciones --result-file=/tmp/77_ss_configuraciones.sql &> /dev/null; echo "[77] completado: ss_configuraciones";
$cmd $db_name ss_rol --result-file=/tmp/78_ss_rol.sql &> /dev/null; echo "[78] completado: ss_rol";
$cmd $db_name tipo_leccion --result-file=/tmp/79_tipo_leccion.sql &> /dev/null; echo "[79] completado: tipo_leccion";
$cmd $db_name tipos_media --result-file=/tmp/80_tipos_media.sql &> /dev/null; echo "[80] completado: tipos_media";
$cmd $db_name valor_calidad_codigos --result-file=/tmp/81_valor_calidad_codigos.sql &> /dev/null; echo "[81] completado: valor_calidad_codigos";

$cmd --where="proyind_pk IN (SELECT l2.proyind_pk FROM proyectos as l1 INNER JOIN proy_indices AS l2 ON (l1.proy_proyindices_fk = l2.proyind_pk) WHERE l1.proy_org_fk IN $organismos)" $db_name proy_indices --result-file=/tmp/82_proy_indices.sql &> /dev/null; echo "[82] completado: proy_indices";
$cmd --where="proyindpre_pk IN (SELECT l3.proyindpre_pk FROM proyectos as l1 INNER JOIN proy_indices AS l2 ON (l1.proy_proyindices_fk = l2.proyind_pk) INNER JOIN proy_indices_pre as l3 ON (l3.proyindpre_proyind_fk = l2.proyind_pk) WHERE l1.proy_org_fk IN $organismos )" $db_name proy_indices_pre --result-file=/tmp/83_proy_indices_pre.sql &> /dev/null; echo "[83] completado: proy_indices_pre";

# Agregado para la versión 4.4.7
$cmd --where="objetivos_estrategicos.obj_est_org_fk IN $organismos" $db_name objetivos_estrategicos --result-file=/tmp/84_objetivos_estrategicos.sql &> /dev/null; echo "[84] completado: objetivos_estrategicos";

# Agregado para la versión 4.4.10
$cmd  --where="aud_doc_file.docfile_pk IN (SELECT l4.docfile_pk FROM tipo_documento as l1 INNER JOIN tipo_documento_instancia as l2 ON (l1.tipdoc_pk = l2.tipodoc_inst_tipodoc_fk) INNER JOIN documentos as l3 ON (l2.tipodoc_inst_pk = l3.docs_tipodoc_fk) INNER JOIN aud_doc_file as l4 ON (l3.docs_pk = l4.docfile_doc_fk) WHERE l1.tipodoc_org_fk IN $organismos)" $db_name aud_doc_file --result-file=/tmp/85_aud_doc_file.sql &> /dev/null; echo "[85] completado: aud_doc_file";

#Agregado para la versión 4.5.5
$cmd --where="procedimiento_compra.proc_comp_org_fk IN $organismos" $db_name procedimiento_compra --result-file=/tmp/86_procedimiento_compra.sql &> /dev/null; echo "[86] completado: procedimiento_compra";
$cmd --where="componente_producto.com_org_fk IN $organismos" $db_name componente_producto --result-file=/tmp/87_componente_producto.sql &> /dev/null; echo "[87] completado: componente_producto";

#Tabla faltante agregada 24-05-2018
$cmd $db_name notificacion_envio --result-file=/tmp/68_notificacion_envio.sql &> /dev/null; echo "[88] completado: notificacion_envio";

cat /tmp/*.sql > $output;
echo "Unir archivos en $output...";
rm -rf /tmp/*.sql;
echo "Borrar archivos temporales creados";
echo '' >> $output;
echo "CREATE ALGORITHM = UNDEFINED VIEW ss_oficina AS SELECT organismos.org_pk AS ofi_id, organismos.org_nombre AS ofi_nombre, organismos.org_activo AS ofi_activo, NOW() AS ofi_fecha_creacion, '' AS ofi_origen, 1 AS ofi_usuario, 1 AS ofi_version FROM organismos" >> $output;
echo "[OK] Generado archivo $output";
