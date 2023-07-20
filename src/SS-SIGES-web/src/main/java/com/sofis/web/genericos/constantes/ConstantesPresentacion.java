package com.sofis.web.genericos.constantes;

import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.web.properties.Labels;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author Usuario
 */
public class ConstantesPresentacion {

    public static final String DEFECTO_COMBO = Labels.getValue("comboEmptyItem");
    public static final String DEFECTO_COMBO_CON_AGREGAR = Labels.getValue("comboSeleccioneItem");
    public static final Integer CANTIDAD_PAGINACION = 25;
    public static final Integer PAGINAS_BUFFERED = 2;
    public static final String IMPORTADO_OTRO = "OTR";
    public static final Integer MODO_ALTA = 1;
    public static final Integer MODO_EDICION = 2;
    // Ficha Cronograma
    public static final String FICHA_PK = "fichaPk";
    public static final String FICHA_TIPO = "fichaTipo";
    public static final String FICHA_ESTADO = "estado";
    public static final String CRONO_ESFUERZO_TOTAL = "esfuerzoTotal";
    public static final String CRONO_HORAS_TOTAL = "horasTotal";
    public static final String CRONO_ROWS_GANTT = "rowsGantt";
    public static final String CRONO_PK = "cronoPk";
    public static final String CRONO_SELECTED_ROW = "selectedRow";
    public static final String CRONO_DELETED_TASK_ID = "deletedTaskIds";
    public static final String CRONO_RESOURCES = "resources";
    public static final String CRONO_ROLES = "roles";
    public static final String CRONO_CAN_WRITE = "canWrite";
    public static final String CRONO_CAN_WRITE_PARENT = "canWriteOnParent";
    public static final String CRONO_MORE_ROWS = "moreRows";
    public static final String TASKS = "tasks";
    public static final String TASKS_DELETED = "deletedTaskData";
    public static final String TASK_PK = "taskPk";
    public static final String TASK_ID = "id";
    public static final String TASK_NAME = "name";
    public static final String TASK_DESCRIPTION = "description";
    public static final String TASK_PARENT = "parent";
    public static final String TASK_CODE = "code";
    public static final String TASK_LEVEL = "level";
    public static final String TASK_STATUS = "status";
    public static final String TASK_START = "start";
    public static final String TASK_DEPENDS = "depends";
    public static final String TASK_DURATION = "duration";
    public static final String TASK_DURACION_LINEA_BASE = "durationLineaBase";
    public static final String TASK_END = "end";
    public static final String TASK_START_IS_MILESTONE = "startIsMilestone";
    public static final String TASK_END_IS_MILESTONE = "endIsMilestone";
    public static final String TASK_COLLAPSED = "collapsed";
    public static final String TASK_ASSIGS = "assigs";
    public static final String TASK_RELEVANTE = "relevantePMO";
    public static final String TASK_COORDINADOR = "coordinador";
    public static final String TASK_ESFUERZO = "esfuerzo";
    public static final String TASK_START_LINEA_BASE = "startLineaBase";
    public static final String TASK_END_LINEA_BASE = "endLineaBase";
    public static final String TASK_PROGRESS = "progress";
    public static final String TASK_HORAS_ESTIMADAS = "horasEstimadas";
    public static final String TASK_CAN_DELETE = "canDelete";
    public static final String TASK_TIENE_PRODUCTOS = "tieneProd";
    public static final String TASK_TIENE_VINCULACION_WEKAN = "tieneVincWekan";
    public static final String TASK_ES_REFERENCIA = "esReferencia";
    public static final String TASK_REFERIDO = "referido";
    public static final String TASK_PROYECTO_REFERIDO = "proyectoReferido";

    
    /* 12-12-2016 para enviar el estado en el que se encuentra el proyecto */
    /* 0 = inicio, 1 = planificación, 2 = ejecución, 3 = finalizado */
    public static final String TASK_ESTADO = "estadoTask";
    
    /* Periodo del entregable 06-04-2017 */
    public static final String TASK_INICIO_PROYECTO = "inicioProyecto";
    public static final String TASK_FIN_PROYECTO = "finProyecto";
    public static final String CRONO_SET_PERIODO_ENTREGABLE = "periodoEntregable";
    
    //Ficha Productos
    public static final String PROD_CANT_AZUL = "PROD_CANT_AZUL";
    public static final String PROD_CANT_VERDE = "PROD_CANT_VERDE";
    public static final String PROD_CANT_AMARILLO = "PROD_CANT_AMARILLO";
    public static final String PROD_CANT_ROJO = "PROD_CANT_ROJO";
    public static final String PROD_PORC_AZUL = "PROD_PORC_AZUL";
    public static final String PROD_PORC_VERDE = "PROD_PORC_VERDE";
    public static final String PROD_PORC_AMARILLO = "PROD_PORC_AMARILLO";
    public static final String PROD_PORC_ROJO = "PROD_PORC_ROJO";
    
    public static final String SALTO_LINEA = "\n";
    public static final String SALTO_LINEA_CHAR_CODE = "&#13;";
    
    // MESSAGES ID
    public static final String MESSAGE_ID_POPUP = "msjPopup";
    
    public static final String PROG_PROY_ID = "programaProyectoId";
    
    public static final String MIS_TAREAS = "misTareasNav";
	
	public static final String REPORTE_PRESUPUESTO = "reporte-presupuesto";
	public static final String REPORTE_CRONOGRAMA = "reporte-cronograma";
	public static final String REPORTE_ALCANCE = "reporte-alcance";
	
    public static final String CALENDAR_PATTERN = "dd/MM/yyyy";
    public static final String CALENDAR_TIME_ZONE = TimeZone.getDefault().getID();

	public static final Locale LOCALE_SISTEMA = new Locale(ConstantesEstandares.CURRENT_LOCALE_LANGUAGE, ConstantesEstandares.CURRENT_LOCALE_COUNTRY);
}
