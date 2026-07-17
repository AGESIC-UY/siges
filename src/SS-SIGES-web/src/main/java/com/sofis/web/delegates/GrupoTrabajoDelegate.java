package com.sofis.web.delegates;

import com.sofis.business.ejbs.GrupoTrabajoBean;
import com.sofis.entities.data.GrupoTrabajo;
import com.sofis.exceptions.GeneralException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class GrupoTrabajoDelegate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private GrupoTrabajoBean cnfBean;

    
    public List<GrupoTrabajo>  listarPorOrg( Integer orgPk) throws GeneralException {
        return cnfBean.listarPorOrg(orgPk);
    }

    public List<GrupoTrabajo> busquedaComponenteProductoFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
       return cnfBean.busquedaComponenteProductoFiltro(orgPk, mapFiltro, elementoOrdenacion, ascendente);
    }

    

    public void eliminarGrupoTrabajo(Integer componenteProductoPk) {
        cnfBean.eliminarGrupoTrabajo(componenteProductoPk);
    }

    public GrupoTrabajo obtenerGrupoTrabajoPk(Integer componenteProductoPk) {
        return cnfBean.obtenerGrupoTrabajoPk(componenteProductoPk);
    }

    public GrupoTrabajo guardarGrupoTrabajo(GrupoTrabajo cgrupoTrabajoEnEdicion) {
       return cnfBean.guardarGrupoTrabajo(cgrupoTrabajoEnEdicion);
    }
    
}
