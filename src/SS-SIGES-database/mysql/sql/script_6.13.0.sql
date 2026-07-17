INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_0_4', 'Indica color 0.4', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_0_8', 'Indica color 0.8', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_1_2', 'Indica color 1.2', 'false', 0, CURDATE(), 'anonymous', 0);


INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_1_6', 'Indica color 1,6', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_2_0', 'Indica color 2.0', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_2_4', 'Indica color 2.4', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_3_2', 'Indica color 3.2', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_3_6', 'Indica color 3.6', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_4_0', 'Indica color 4.0', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_4_8', 'Indica color 4.8', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_6_0', 'Indica color 6.0', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_6_4', 'Indica color 6.4', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_8_0', 'Indica color 8.0', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('COLOR_10_0', 'Indica color 10.0', 'false', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_0_4','Indica color para 0.4','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_0_8','Indica color para 0.8','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_1_2','Indica color para 1.2','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_1_6','Indica color para 1.6','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_2_0','Indica color para 2.0','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_2_4','Indica color para 2.4','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_3_2','Indica color para 3.2','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_3_6','Indica color para 3.6','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_4_0','Indica color para 4.0','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_4_8','Indica color para 4.8','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_6_0','Indica color para 6.0','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_6_4','Indica color para 6.4','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_8_0','Indica color para 8.0','false',0 ,org_pk from organismos;
INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'COLOR_10_0','Indica color para 10.0','false',0 ,org_pk from organismos;

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 0.4
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_0_4';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 0.4 
inner join ss_configuraciones sc2 on
sc2.cnf_org_fk =cc.cnf_org_fk
and sc2.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.cnf_valor +"0.0" >  0.4
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_0_4';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 0.4
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_0_4';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 0.8
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_0_8';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 0.8 
inner join ss_configuraciones sc2 on
sc2.cnf_org_fk =cc.cnf_org_fk
and sc2.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.cnf_valor +"0.0" >  0.8
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_0_8';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 0.8
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_0_8';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 1.2
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_1_2';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 1.2 
inner join ss_configuraciones sc2 on
sc2.cnf_org_fk =cc.cnf_org_fk
and sc2.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.cnf_valor +"0.0" >  1.2
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_1_2';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 1.2
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_1_2';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 1.6
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_1_6';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 1.6 
inner join ss_configuraciones sc6 on
sc6.cnf_org_fk =cc.cnf_org_fk
and sc6.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc6.cnf_valor +"0.0" >=  1.6
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_1_6';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 1.6
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_1_6';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 2.0
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_2_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 2.0 
inner join ss_configuraciones sc0 on
sc0.cnf_org_fk =cc.cnf_org_fk
and sc0.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.cnf_valor +"0.0" >=  2.0
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_2_0';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 2.0
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_2_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 2.4
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_2_4';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 2.4 
inner join ss_configuraciones sc4 on
sc4.cnf_org_fk =cc.cnf_org_fk
and sc4.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc4.cnf_valor +"0.0" >=  2.4
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_2_4';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 2.4
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_2_4';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 3.2
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_3_2';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 3.2 
inner join ss_configuraciones sc2 on
sc2.cnf_org_fk =cc.cnf_org_fk
and sc2.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.cnf_valor +"0.0" >  3.2
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_3_2';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 3.2
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_3_2';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 3.6
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_3_6';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 3.6 
inner join ss_configuraciones sc6 on
sc6.cnf_org_fk =cc.cnf_org_fk
and sc6.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc6.cnf_valor +"0.0" >=  3.6
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_3_6';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 3.6
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_3_6';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 4.0
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_4_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 4.0 
inner join ss_configuraciones sc0 on
sc0.cnf_org_fk =cc.cnf_org_fk
and sc0.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.cnf_valor +"0.0" >=  4.0
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_4_0';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 4.0
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_4_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 4.8
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_4_8';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 4.8 
inner join ss_configuraciones sc8 on
sc8.cnf_org_fk =cc.cnf_org_fk
and sc8.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc8.cnf_valor +"0.0" >=  4.8
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_4_8';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 4.8
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_4_8';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 6.0
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_6_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 6.0 
inner join ss_configuraciones sc0 on
sc0.cnf_org_fk =cc.cnf_org_fk
and sc0.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.cnf_valor +"0.0" >=  6.0
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_6_0';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 6.0
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_6_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 6.4
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_6_4';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 6.4 
inner join ss_configuraciones sc4 on
sc4.cnf_org_fk =cc.cnf_org_fk
and sc4.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc4.cnf_valor +"0.0" >=  6.4
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_6_4';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 6.4
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_6_4';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 8.0
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_8_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 8.0 
inner join ss_configuraciones sc0 on
sc0.cnf_org_fk =cc.cnf_org_fk
and sc0.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.cnf_valor +"0.0" >=  8.0
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_8_0';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 8.0
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_8_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" >= 10.0
set cc.cnf_valor ='Verde'
where cc.cnf_codigo ='COLOR_10_0';

update  ss_configuraciones cc 
inner join ss_configuraciones sc on
sc.cnf_org_fk =cc.cnf_org_fk
and sc.cnf_codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.cnf_valor +"0.0" < 10.0 
inner join ss_configuraciones sc0 on
sc0.cnf_org_fk =cc.cnf_org_fk
and sc0.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.cnf_valor +"0.0" >=  10.0
set cc.cnf_valor ='Amarillo'
where cc.cnf_codigo ='COLOR_10_0';


update  ss_configuraciones cc 
inner join ss_configuraciones sc on sc.cnf_org_fk =cc.cnf_org_fk
and  sc.cnf_codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.cnf_valor +"0.0" <= 10.0
set cc.cnf_valor ='Rojo'
where cc.cnf_codigo ='COLOR_10_0';

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('RIESGO_TITULO_EJE_X', 'Titulo de eje x para grafico de riesgo', 'Probabilidad', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'RIESGO_TITULO_EJE_X','Titulo de eje x para grafico de riesgo','Probabilidad',0 ,org_pk from organismos;

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('RIESGO_TITULO_EJE_Y', 'Titulo de eje y para grafico de riesgo', 'Impacto', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'RIESGO_TITULO_EJE_Y','Titulo de eje y para grafico de riesgo','Impacto',0 ,org_pk from organismos;



INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('RIESGO_VALORES_EJE_X', 'Valores del eje x para riesgo ', '20%,40%,60%,80%,100%', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'RIESGO_VALORES_EJE_X','Valores del eje x para riesgo','20%,40%,60%,80%,100%',0 ,org_pk from organismos;

INSERT INTO ss_configuraciones_defecto
(codigo, descripcion, valor, es_html, ultima_modificacion, usuario_modificacion, version)
VALUES('RIESGO_VALORES_EJE_Y', 'Valores del eje y para riesgo', '2,4,6,8,10', 0, CURDATE(), 'anonymous', 0);

INSERT INTO ss_configuraciones (cnf_codigo, cnf_descripcion, cnf_valor,cnf_version, cnf_org_fk) Select 'RIESGO_VALORES_EJE_Y','Valores del eje y para riesgo','2,4,6,8,10',0 ,org_pk from organismos;

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 0.4
set cc.valor ='Verde'
where cc.codigo ='COLOR_0_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 0.4 
inner join ss_configuraciones_defecto sc2 on sc2.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.valor +"0.0" >  0.4
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_0_4';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 0.4
set cc.valor ='Rojo'
where cc.codigo ='COLOR_0_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 0.8
set cc.valor ='Verde'
where cc.codigo ='COLOR_0_8';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 0.8 
inner join ss_configuraciones_defecto sc2 on sc2.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.valor +"0.0" >  0.8
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_0_8';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 0.8
set cc.valor ='Rojo'
where cc.codigo ='COLOR_0_8';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 1.2
set cc.valor ='Verde'
where cc.codigo ='COLOR_1_2';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 1.2 
inner join ss_configuraciones_defecto sc2 on  sc2.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.valor +"0.0" >  1.2
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_1_2';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 1.2
set cc.valor ='Rojo'
where cc.codigo ='COLOR_1_2';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 1.6
set cc.valor ='Verde'
where cc.codigo ='COLOR_1_6';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 1.6 
inner join ss_configuraciones_defecto sc6 on  sc6.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc6.valor +"0.0" >=  1.6
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_1_6';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 1.6
set cc.valor ='Rojo'
where cc.codigo ='COLOR_1_6';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 2.0
set cc.valor ='Verde'
where cc.codigo ='COLOR_2_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 2.0 
inner join ss_configuraciones_defecto sc0 on sc0.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.valor +"0.0" >=  2.0
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_2_0';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 2.0
set cc.valor ='Rojo'
where cc.codigo ='COLOR_2_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 2.4
set cc.valor ='Verde'
where cc.codigo ='COLOR_2_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 2.4 
inner join ss_configuraciones_defecto sc4 on sc4.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc4.valor +"0.0" >=  2.4
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_2_4';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 2.4
set cc.valor ='Rojo'
where cc.codigo ='COLOR_2_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 3.2
set cc.valor ='Verde'
where cc.codigo ='COLOR_3_2';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 3.2 
inner join ss_configuraciones_defecto sc2 on sc2.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc2.valor +"0.0" >  3.2
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_3_2';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 3.2
set cc.valor ='Rojo'
where cc.codigo ='COLOR_3_2';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 3.6
set cc.valor ='Verde'
where cc.codigo ='COLOR_3_6';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 3.6 
inner join ss_configuraciones_defecto sc6 on sc6.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc6.valor +"0.0" >=  3.6
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_3_6';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 3.6
set cc.valor ='Rojo'
where cc.codigo ='COLOR_3_6';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 4.0
set cc.valor ='Verde'
where cc.codigo ='COLOR_4_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 4.0 
inner join ss_configuraciones_defecto sc0 on sc0.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.valor +"0.0" >=  4.0
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_4_0';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 4.0
set cc.valor ='Rojo'
where cc.codigo ='COLOR_4_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 4.8
set cc.valor ='Verde'
where cc.codigo ='COLOR_4_8';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 4.8 
inner join ss_configuraciones_defecto sc8 on
 sc8.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc8.valor +"0.0" >=  4.8
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_4_8';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 4.8
set cc.valor ='Rojo'
where cc.codigo ='COLOR_4_8';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 6.0
set cc.valor ='Verde'
where cc.codigo ='COLOR_6_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 6.0 
inner join ss_configuraciones_defecto sc0 on sc0.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.valor +"0.0" >=  6.0
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_6_0';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 6.0
set cc.valor ='Rojo'
where cc.codigo ='COLOR_6_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 6.4
set cc.valor ='Verde'
where cc.codigo ='COLOR_6_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 6.4 
inner join ss_configuraciones_defecto sc4 on
sc4.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc4.valor +"0.0" >=  6.4
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_6_4';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 6.4
set cc.valor ='Rojo'
where cc.codigo ='COLOR_6_4';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 8.0
set cc.valor ='Verde'
where cc.codigo ='COLOR_8_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 8.0 
inner join ss_configuraciones_defecto sc0 on  sc0.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.valor +"0.0" >=  8.0
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_8_0';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 8.0
set cc.valor ='Rojo'
where cc.codigo ='COLOR_8_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" >= 10.0
set cc.valor ='Verde'
where cc.codigo ='COLOR_10_0';

update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on
 sc.codigo ='RIESGO_INDICE_LIMITE_AMARILLO' and sc.valor +"0.0" < 10.0 
inner join ss_configuraciones_defecto sc0 on sc0.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc0.valor +"0.0" >=  10.0
set cc.valor ='Amarillo'
where cc.codigo ='COLOR_10_0';


update  ss_configuraciones_defecto cc 
inner join ss_configuraciones_defecto sc on   sc.codigo ='RIESGO_INDICE_LIMITE_ROJO' and sc.valor +"0.0" <= 10.0
set cc.valor ='Rojo'
where cc.codigo ='COLOR_10_0';











