package com.sofis.web.delegates;

import com.sofis.business.ejbs.AreasBean;
import com.sofis.entities.data.Areas;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class AreasDelegate {

    @Inject
    AreasBean areasBean;

    public List<Areas> obtenerAreasPorOrganismo(int organismoId, Boolean soloHabilitadas) {
        return areasBean.obtenerAreasPorIdOrganismo(organismoId, soloHabilitadas);
    }

    public List<Areas> obtenerAreasRestringidasPorFichaPk(int fichaPk, int tipoFicha) {
        return areasBean.obtenerAreasRestringidasPorFichaPk(fichaPk, tipoFicha);
    }

    public List<Areas> busquedaAreaFiltro(Integer orgPk, Map<String,Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        return areasBean.busquedaAreaFiltro(orgPk, mapFiltro, elementoOrdenacion, ascendente);
    }

    public void eliminarArea(Integer aPk) {
        areasBean.eliminarArea(aPk);
    }

    public Areas obtenerAreaPorPk(Integer aPk) {
        return areasBean.obtenerAreasPorPk(aPk);
    }

    public Areas guardarArea(Areas areaEnEdicion) {
        return areasBean.guardar(areaEnEdicion);
    }
}
