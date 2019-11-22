package com.sofis.web.delegates;

import com.sofis.business.ejbs.ProcedimientoCompraBean;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class ProcedimientoCompraDelegate {

	//@Inject
	//private FuenteFinanciamientoBean fuenteFinanciamientoBean;
	@Inject
	private ProcedimientoCompraBean componenteProductoBean;

	public List<ProcedimientoCompra> obtenerProcedimientosComprasPorOrgId(Integer orgId) throws GeneralException {
		return componenteProductoBean.obtenerProcedimientosComprasPorOrgId(orgId);
	}

	public void eliminarProcedimientoCompra(Integer componenteProductoPk) {
		componenteProductoBean.eliminarProcedimientoCompra(componenteProductoPk);
	}

	public List<ProcedimientoCompra> busquedaProcedimientoCompraFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
		return componenteProductoBean.busquedaProcedimientoCompraFiltro(orgPk, mapFiltro, elementoOrdenacion, ascendente);
	}

	public ProcedimientoCompra obtenerProcedimientoCompraPorPk(Integer componenteProductoPk) {
		return componenteProductoBean.obtenerProcedimientoCompraPorPk(componenteProductoPk);
	}

	public ProcedimientoCompra guardarProcedimientoCompra(ProcedimientoCompra componenteProducto) {
		return componenteProductoBean.guardarProcedimientoCompra(componenteProducto);
	}
        
        public void cargaMasiva(List<ProcedimientoCompra> datos, Integer orgPk){
                componenteProductoBean.cargaMasiva(datos, orgPk);
        }
}
