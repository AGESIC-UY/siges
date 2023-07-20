package com.sofis.web.delegates;

import com.sofis.business.ejbs.ProcedimientoCompraInfoBean;
import com.sofis.entities.data.ProcedimientoCompraInfo;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class ProcedimientoCompraInfoDelegate {

    @Inject
    private ProcedimientoCompraInfoBean procedimientoCompraInfoBean;

    public void cargaMasiva(List<ProcedimientoCompraInfo> listaProcedimientoCompraInfo) {

        for (ProcedimientoCompraInfo pci : listaProcedimientoCompraInfo) {
            procedimientoCompraInfoBean.guardarProcedimientoCompraInfo(pci);
        }
    }

    public List<ProcedimientoCompraInfo> busquedaProcedimientoCompraFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        return procedimientoCompraInfoBean.busquedaProcedimientoCompraFiltro(orgPk, mapFiltro, elementoOrdenacion, ascendente);
    }
    
    public ProcedimientoCompraInfo obtenerProcedimientoCompraPorPk(Integer componenteProductoPk) {
		return procedimientoCompraInfoBean.obtenerProcedimientoCompraPorPk(componenteProductoPk);
	}
}
