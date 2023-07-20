package com.sofis.business.utils;

import com.sofis.entities.data.Estados;
import com.sofis.entities.tipos.PagoTO;
import java.util.List;

public abstract class PagoUtils {

	public static Double calcularImportePlanificado(List<PagoTO> pagos) {

		Double result = 0D;

		for (PagoTO pago : pagos) {
			if (pago.getImportePlanificado() != null) {
				result += pago.getImportePlanificado();
			}
		}

		return result;
	}

	public static Double calcularImporteReal(List<PagoTO> pagos) {

		Double result = 0D;

		for (PagoTO pago : pagos) {
			if (pago.getImporteReal() != null) {
				result += pago.getImporteReal();
			}
		}

		return result;
	}

	public static Double calcularImporteSaldo(List<PagoTO> pagos) {

		Double result = 0D;

		for (PagoTO pago : pagos) {
			result += calcularSaldo(pago);
		}

		return result;
	}

	public static Double calcularSaldo(PagoTO pago) {

		Double auxImportePlanificado = (pago.getImportePlanificado() != null) ? pago.getImportePlanificado() : 0d;
		Double auxImporteReal = (pago.getImporteReal() != null) ? pago.getImporteReal() : 0d;

		return (auxImportePlanificado - auxImporteReal);
	}

	public static boolean pagoPuedeCambiarAprobacion(boolean pagoConfirmado, Integer idEstadoProyecto,
			boolean usuarioEsPMOF, boolean usuarioEsPMOT, boolean usuarioEsGerente, boolean usuarioEsAdjunto,
			boolean usuarioApruebaPagos, boolean adjuntoModificaPresupuesto) {

		boolean proyectoEnEjecucion = idEstadoProyecto.equals(Estados.ESTADOS.EJECUCION.estado_id);
		boolean usuarioEsPMyApruebaPagos = (adjuntoModificaPresupuesto && usuarioEsAdjunto) || usuarioEsGerente;

		return proyectoEnEjecucion
				&& (pagoConfirmado || usuarioEsPMyApruebaPagos || usuarioApruebaPagos)
				&& (!pagoConfirmado || usuarioEsPMOF || usuarioEsPMOT);
	}


	public static boolean pagoEsEditable(boolean pagoConfirmado, Integer idEstadoProyecto, boolean proyectoEnReplanificacionPresupuesto,
			boolean usuarioEsGerente, boolean usuarioEsAdjunto, boolean adjuntoModificaPresupuesto) {

		boolean proyectoPendiente = idEstadoProyecto.equals(Estados.ESTADOS.PENDIENTE.estado_id)
				|| idEstadoProyecto.equals(Estados.ESTADOS.PENDIENTE_PMOF.estado_id)
				|| idEstadoProyecto.equals(Estados.ESTADOS.PENDIENTE_PMOT.estado_id);

		boolean proyectoEnPlanificacion = idEstadoProyecto.equals(Estados.ESTADOS.PLANIFICACION.estado_id);
		boolean proyectoFinalizado = idEstadoProyecto.equals(Estados.ESTADOS.FINALIZADO.estado_id);

		boolean usuarioEsPMyApruebaPagos = (adjuntoModificaPresupuesto && usuarioEsAdjunto) || usuarioEsGerente;

		return !pagoConfirmado
				&& usuarioEsPMyApruebaPagos
				&& !proyectoPendiente
				&& !proyectoFinalizado
				&& (!proyectoEnPlanificacion || proyectoEnReplanificacionPresupuesto);
	}
}
