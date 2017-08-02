package com.sofis.entities.codigueras;

/**
 *
 * @author Usuario
 */
public class EstadosCodigos {

    public static final String NO_EXIGIDO = "NO_EXIGIDO"; //0
    public static final String PENDIENTE = "PENDIENTE"; //1 - No se usa
    public static final String PENDIENTE_PMOT = "PENDIENTE_PMOT"; //11 - Cuando el PM solicita aprobacion al PMOT
    public static final String PENDIENTE_PMOF = "PENDIENTE_PMOF"; //12 - Cuando el PMOT solicita aprobacion al PMOF
    public static final String INICIO = "INICIO"; //2
    public static final String PLANIFICACION = "PLANIFICACION"; //3
    public static final String EJECUCION = "EJECUCION"; //4
    public static final String FINALIZADO = "FINALIZADO"; //5
    public static final String SOLICITUD_FINALIZADO_PMOF = "SOLICITUD_FINALIZADO_PMOF"; //41 - Cuando el PM solicita al PMOF Finalizar
    public static final String SOLICITUD_FINALIZADO_PMOT = "SOLICITUD_FINALIZADO_PMOT"; //42 - Cuando el PMOF solicita al PMOT Finalizar
    public static final String SOLICITUD_CANCELAR_PMOT = "SOLICITUD_CANCELAR_PMOT"; //61 - Cuando el PMOF solicita Cancelar(borrado logico)
}
