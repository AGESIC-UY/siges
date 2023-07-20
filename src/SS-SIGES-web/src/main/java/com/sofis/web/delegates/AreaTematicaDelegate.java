package com.sofis.web.delegates;

import com.sofis.business.ejbs.AreaTematicaBean;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.tipos.FiltroAreaTematicaTO;
import java.util.List;
import javax.inject.Inject;

public class AreaTematicaDelegate {
    
    @Inject
    private AreaTematicaBean areaTematicaBean;
    
    public List<AreasTags> obtenerPorOrganismo(int organismoId) {
        return areaTematicaBean.obtenerPorOrganismo(organismoId);
    }
    
    public List<AreasTags> obtenerHabilitadasPorOrganismo(int organismoId) {
        return areaTematicaBean.obtenerHabilitadasPorOrganismo(organismoId);
    }

    public List<AreasTags> obtenerAreasTematicasPorFichaPk(int fichaPk, int tipoFicha) {
        return areaTematicaBean.obtenerAreasTematicasPorFichaPk(fichaPk, tipoFicha);
    }

    public List<AreasTags> buscar(FiltroAreaTematicaTO filtro, String elementoOrdenacion, boolean ascendente) {
        return areaTematicaBean.buscar(filtro, elementoOrdenacion, ascendente);
    }
	
    public List<AreasTags> buscar(FiltroAreaTematicaTO filtro, String elementoOrdenacion) {
        return areaTematicaBean.buscar(filtro, elementoOrdenacion);
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
	
	public List<AreasTags> obtenerPadres(List<AreasTags> hijas) {
		return areaTematicaBean.obtenerPadres(hijas);
    }
}
