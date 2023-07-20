package com.sofis.web.delegates;

import com.sofis.business.ejbs.FuenteProcedimientoCompraBean;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.FuenteProcedimientoCompra;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.entities.tipos.FiltroFuenteProcedimientoCompraTO;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class FuenteProcedimientoCompraDelegate {

	@Inject
	private FuenteProcedimientoCompraBean bean;

	public FuenteProcedimientoCompra obtenerPorId(Integer objEstPk) {
		return bean.obtenerPorId(objEstPk);
	}

	public FuenteProcedimientoCompra guardar(FuenteProcedimientoCompra objEst) {
		return bean.guardar(objEst);
	}

	public List<FuenteProcedimientoCompra> obtenerPorFiltro(FiltroFuenteProcedimientoCompraTO filtro) {
		return bean.obtenerPorFiltro(filtro);
	}

	public void eliminar(Integer fuenteProcId) {
		bean.eliminar(fuenteProcId);
	}

	public Boolean fuenteProcedimientoCompraEstaEnTabla(FuenteFinanciamiento fuente, ProcedimientoCompra procedimientoCompra) {
		return bean.fuenteProcedimientoCompraEstaEnTabla(fuente, procedimientoCompra);
	}

	public FuenteProcedimientoCompra obtenerPorFuenteProcedimientoCompra(FuenteFinanciamiento fuente, ProcedimientoCompra procedimientoCompra) {
		return bean.obtenerPorFuenteProcedimientoCompra(fuente, procedimientoCompra);
	}

	public FuenteProcedimientoCompra obtenerPorFuenteProcedimientoCompra(String nombreFuenteFinanciamiento,
			String nombreProcedimientoCompra, Integer orgPk) {
		return bean.obtenerPorFuenteProcedimientoCompra(nombreFuenteFinanciamiento, nombreProcedimientoCompra, orgPk);
	}
}
