package com.sofis.web.delegates;

import com.sofis.business.ejbs.AreasConocimientoBean;
import com.sofis.entities.data.AreaConocimiento;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class AreasConocimientoDelegate {

    @Inject
    AreasConocimientoBean areasConocimientoBean;

    public List<AreaConocimiento> obtenerAreaConPorOrg(Integer orgPk) {
        return areasConocimientoBean.obtenerAreaConPorOrg(orgPk);
    }

    public List<AreaConocimiento> obtenerAreasConPorLeccPk(Integer leccAprPk) {
        return areasConocimientoBean.obtenerAreasConPorLeccPk(leccAprPk);
    }

    public List<AreaConocimiento> busquedaAreaFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        return areasConocimientoBean.busquedaAreaFiltro(orgPk, mapFiltro, elementoOrdenacion, ascendente);
    }

    public void eliminarArea(Integer aPk) {
        areasConocimientoBean.eliminarArea(aPk);
    }

    public AreaConocimiento obtenerAreaPorPk(Integer aPk) {
        return areasConocimientoBean.obtenerAreasPorPk(aPk);
    }

    public AreaConocimiento guardarArea(AreaConocimiento areaEnEdicion) {
        return areasConocimientoBean.guardar(areaEnEdicion);
    }
    
    public List<AreaConocimiento> obtenerSoloPadres(Integer orgPk) {
        return areasConocimientoBean.obtenerSoloPadres(orgPk);
    }
}
