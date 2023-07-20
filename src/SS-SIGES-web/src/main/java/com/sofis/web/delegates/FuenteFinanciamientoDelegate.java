package com.sofis.web.delegates;

import com.sofis.business.ejbs.FuenteFinanciamientoBean;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class FuenteFinanciamientoDelegate {

    @Inject
    private FuenteFinanciamientoBean fuenteFinanciamientoBean;

    public List<FuenteFinanciamiento> obtenerFuentesPorOrgId(Integer orgId) throws GeneralException {
        return fuenteFinanciamientoBean.obtenerFuentesPorOrgId(orgId);
    }

    public void eliminarFuente(Integer fuentePk) {
        fuenteFinanciamientoBean.eliminarFuente(fuentePk);
    }

    public List<FuenteFinanciamiento> busquedaFuenteFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        return fuenteFinanciamientoBean.busquedaFuenteFiltro(orgPk, mapFiltro, elementoOrdenacion, ascendente);
    }

    public FuenteFinanciamiento obtenerFuentePorPk(Integer fuentePk) {
        return fuenteFinanciamientoBean.obtenerFuentePorPk(fuentePk);
    }

    public FuenteFinanciamiento guardarFuente(FuenteFinanciamiento fuenteEnEdicion) {
        return fuenteFinanciamientoBean.guardarFuente(fuenteEnEdicion);
    }
	
    public List<FuenteFinanciamiento> obtenerFuentesHabilitadasPorOrgId(Integer orgId) {
        return fuenteFinanciamientoBean.obtenerFuentesHabilitadasPorOrgId(orgId);
    }

}
