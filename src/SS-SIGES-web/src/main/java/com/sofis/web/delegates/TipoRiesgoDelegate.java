package com.sofis.web.delegates;

import com.sofis.business.ejbs.TipoRiesgoBean;
import com.sofis.entities.data.Ambito;
import com.sofis.entities.data.TipoRiesgo;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class TipoRiesgoDelegate {
    
    @Inject
    private TipoRiesgoBean tipoRiesgoBean;
    
    public TipoRiesgo obtenerTipoRiesgoPorId(Integer ambitoPk) throws GeneralException {
        return tipoRiesgoBean.obtenerTipoRiesgoPorId(ambitoPk);
    }
    
    public List<TipoRiesgo> obtenerTipoRiesgoPorOrg(Integer orgPk) throws GeneralException {
        return tipoRiesgoBean.obtenerTipoRiesgoPorOrg(orgPk);
    }
    
     public List<TipoRiesgo> busquedaActivos(Integer orgPk) throws GeneralException {
        return tipoRiesgoBean.busquedaActivos(orgPk);
    }

    public void eliminarTipoRiesgo(Integer aPk) {
        tipoRiesgoBean.eliminarTipoRiesgo(aPk);
    }

    public List<TipoRiesgo> busquedaTipoRiesgoFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        return tipoRiesgoBean.busquedaTipoRiesgoFiltro(orgPk, mapFiltro, elementoOrdenacion, ascendente);
    }

    public TipoRiesgo guardarTipoRiesgo(TipoRiesgo ambitoEnEdicion) {
        return tipoRiesgoBean.guardarTipoRiesgo(ambitoEnEdicion);
    }
}
