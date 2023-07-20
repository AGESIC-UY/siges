package com.sofis.business.utils;

import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Estados;
import com.sofis.entities.tipos.AdquisicionTO;
import com.sofis.entities.tipos.CausalCompraTO;
import com.sofis.entities.tipos.FuenteFinanciamientoTO;
import com.sofis.entities.tipos.MonedaTO;
import com.sofis.entities.tipos.OrganizacionTO;

public class AdquisicionUtils {

	public static AdquisicionTO convert(Adquisicion adquisicion) {

		AdquisicionTO result = new AdquisicionTO();
		result.setId(adquisicion.getAdqPk());
		result.setNombre(adquisicion.getAdqNombre());
		result.setIdAdquisicion(adquisicion.getAdqIdAdquisicion());

		if (adquisicion.getAdqProvOrga() != null) {
			result.setProveedor(new OrganizacionTO(adquisicion.getAdqProvOrga().getOrgaPk(), adquisicion.getAdqProvOrga().getOrgaNombre()));
		}
		if (adquisicion.getAdqFuente() != null) {
			result.setFuenteFinanciamiento(new FuenteFinanciamientoTO(adquisicion.getAdqFuente().getFuePk(), adquisicion.getAdqFuente().getFueNombre()));
		}
		if (adquisicion.getAdqCausalCompra() != null) {
			result.setCausalCompra(new CausalCompraTO(adquisicion.getAdqCausalCompra().getCauComPk(), adquisicion.getAdqCausalCompra().getCauComNombre()));
		}

		if (adquisicion.getAdqMoneda() != null) {

			MonedaTO moneda = new MonedaTO(adquisicion.getAdqMoneda().getMonPk(), adquisicion.getAdqMoneda().getMonNombre());
			moneda.setSigno(adquisicion.getAdqMoneda().getMonSigno());
			result.setMoneda(moneda);
		}

		result.setImportePlanificado(adquisicion.getImportePlanificado());
		result.setImporteReal(adquisicion.getImporteReal());
		result.setSaldo(adquisicion.getImporteSaldo());

		return result;
	}

	public static boolean adquisicionEsEditable(Integer idEstadoProyecto, boolean proyectoEnReplanificacionPresupuesto,
			boolean usuarioEsGerente, boolean usuarioEsAdjunto, boolean adjuntoModificaPresupuesto) {

		boolean proyectoPendiente = idEstadoProyecto.equals(Estados.ESTADOS.PENDIENTE.estado_id)
				|| idEstadoProyecto.equals(Estados.ESTADOS.PENDIENTE_PMOF.estado_id)
				|| idEstadoProyecto.equals(Estados.ESTADOS.PENDIENTE_PMOT.estado_id);

		boolean proyectoEnPlanificacion = idEstadoProyecto.equals(Estados.ESTADOS.PLANIFICACION.estado_id);
		boolean proyectoFinalizado = idEstadoProyecto.equals(Estados.ESTADOS.FINALIZADO.estado_id);

		boolean usuarioEsPMyApruebaPagos = (adjuntoModificaPresupuesto && usuarioEsAdjunto) || usuarioEsGerente;

		return usuarioEsPMyApruebaPagos
				&& !proyectoPendiente
				&& !proyectoFinalizado
				&& (!proyectoEnPlanificacion || proyectoEnReplanificacionPresupuesto);
	}
}
