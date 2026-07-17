package com.sofis.web.delegates;

import com.sofis.business.ejbs.AdquisicionLogBean;
import com.sofis.entities.data.LogAdquisicion;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class AdquisicionInfoDelegate {

    @Inject
    private AdquisicionLogBean adquisicionInfoBean;

    public List<LogAdquisicion> busquedaLogAdquisicionFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        return adquisicionInfoBean.busquedaLogAdquisicionFiltro(orgPk, mapFiltro, elementoOrdenacion, ascendente);
    }

    public LogAdquisicion obtenerLogAdquisicionPorPk(Integer componenteProductoPk) {
        return adquisicionInfoBean.obtenerLogAdquisicionPorPk(componenteProductoPk);
    }

    public LogAdquisicion guardarLogAdquisicion(LogAdquisicion entidad) {
        return adquisicionInfoBean.guardarLogAdquisicion(entidad);
    }
}
