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

