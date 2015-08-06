package com.sofis.entities.codigueras;

/**
 *
 * @author Usuario
 */
public class EstadosCodigos {

    public static final String NO_EXIGIDO = "NO_EXIGIDO";
    public static final String PENDIENTE = "PENDIENTE"; //No se usa
    public static final String PENDIENTE_PMOT = "PENDIENTE_PMOT"; //Cuando el PM solicita aprobacion al PMOT
    public static final String PENDIENTE_PMOF = "PENDIENTE_PMOF"; //Cuando el PMOT solicita aprobacion al PMOF
    public static final String INICIO = "INICIO";
    public static final String PLANIFICACION = "PLANIFICACION";
    public static final String EJECUCION = "EJECUCION";
    public static final String FINALIZADO = "FINALIZADO";
    public static final String SOLICITUD_FINALIZADO_PMOF = "SOLICITUD_FINALIZADO_PMOF"; //Cuando el PM solicita al PMOF Finalizar
    public static final String SOLICITUD_FINALIZADO_PMOT = "SOLICITUD_FINALIZADO_PMOT"; //Cuando el PMOF solicita al PMOT Finalizar
    public static final String SOLICITUD_CANCELAR_PMOT = "SOLICITUD_CANCELAR_PMOT"; //Cuando el PMOF solicita Cancelar(borrado logico)
}
