package com.sofis.web.delegates;

import com.sofis.business.ejbs.ComponenteProductoBean;
import com.sofis.entities.data.ComponenteProducto;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class ComponenteProductoDelegate {

    //@Inject
    //private FuenteFinanciamientoBean fuenteFinanciamientoBean;
    @Inject
    private ComponenteProductoBean componenteProductoBean;

    public List<ComponenteProducto> obtenerComponentesProductosPorOrgId(Integer orgId) throws GeneralException {
        return componenteProductoBean.obtenerComponentesProductosPorOrgId(orgId);
    }

    public void eliminarComponenteProducto(Integer componenteProductoPk) {
        componenteProductoBean.eliminarComponenteProducto(componenteProductoPk);
    }

    public List<ComponenteProducto> busquedaComponenteProductoFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        return componenteProductoBean.busquedaComponenteProductoFiltro(orgPk, mapFiltro, elementoOrdenacion, ascendente);
    }

    public ComponenteProducto obtenerComponenteProductoPorPk(Integer componenteProductoPk) {
        return componenteProductoBean.obtenerComponenteProductoPorPk(componenteProductoPk);
    }

    public ComponenteProducto guardarComponenteProducto(ComponenteProducto componenteProducto) {
        return componenteProductoBean.guardarComponenteProducto(componenteProducto);
    }
}
