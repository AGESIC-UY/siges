package com.sofis.web.delegates;

import com.sofis.business.ejbs.AreaTematicaBean;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.SsUsuario;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class AreaTematicaDelegate {
    
    @Inject
    AreaTematicaBean areaTematicaBean;
    
    public List<AreasTags> obtenerAreasTematicasPorOrganizacion(int organismoId) {
        return areaTematicaBean.obtenerAreasTematicasPorOrg(organismoId);
    }

    public List<AreasTags> obtenerAreasTematicasPorFichaPk(int fichaPk, int tipoFicha) {
        return areaTematicaBean.obtenerAreasTematicasPorFichaPk(fichaPk, tipoFicha);
    }

    public List<AreasTags> busquedaAreaTemFiltro(Integer orgPk, String filtroNombre, String elementoOrdenacion, int ascendente) {
        return areaTematicaBean.busquedaAreaTemFiltro(orgPk, filtroNombre, elementoOrdenacion, ascendente);
    }

    public AreasTags obtenerAreaTemPorPk(Integer atPk) {
        return areaTematicaBean.obtenerAreaTemPorPk(atPk);
    }

    public void eliminarAreaTematica(Integer atPk) {
        areaTematicaBean.eliminarAreaTematica(atPk);
    }

    public AreasTags guardarAreaTematica(AreasTags areaTemEnEdicion) {
        return areaTematicaBean.guardar(areaTemEnEdicion);
    }
}
