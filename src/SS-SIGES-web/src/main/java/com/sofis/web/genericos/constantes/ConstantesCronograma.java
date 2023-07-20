package com.sofis.web.genericos.constantes;

public abstract class ConstantesCronograma {

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
	public static final String CRONO_COORDINADORES = "coordinadores";
	public static final String CRONO_USUARIO_ID = "usuarioId";
	public static final String CRONO_IS_PM = "isPM";
	public static final String CRONO_IS_PMO = "isPMO";
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
	public static final String TASK_ES_REFERIDO_DESDE_OTRO_PROYECTO = "esReferidoDesdeOtroProyecto";

	// estado en el que se encuentra el proyecto
	// 0 = inicio, 1 = planificación, 2 = ejecución, 3 = finalizado
	public static final String TASK_ESTADO = "estadoTask";

	// Periodo del entregable
	public static final String TASK_INICIO_PROYECTO = "inicioProyecto";
	public static final String TASK_FIN_PROYECTO = "finProyecto";
	public static final String CRONO_SET_PERIODO_ENTREGABLE = "periodoEntregable";
	
	public static final String PROYECTO_ID = "id";
	public static final String PROYECTO_NOMBRE = "nombre";
        
        public static final String GERENTEOADJUNTO = "isGerenteOAdjunto";
        public static final String CRONO_IS_PMOditor = "isPMOEditor";

}
