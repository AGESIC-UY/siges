package com.sofis.web.delegates;

import com.sofis.business.ejbs.TemasCalidadBean;
import com.sofis.entities.data.TemasCalidad;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class TemasCalidadDelegate {
    
    @Inject
    private TemasCalidadBean temasCalidadBean;
    
    public TemasCalidad obtenerPorId(Integer tcaPk) throws GeneralException {
        return temasCalidadBean.obtenerPorId(tcaPk);
    }
    
    public List<TemasCalidad> obtenerPorOrg(Integer orgPk) throws GeneralException {
        return temasCalidadBean.obtenerPorOrg(orgPk);
    }

    public void eliminar(Integer aPk) {
        temasCalidadBean.eliminar(aPk);
    }

    public List<TemasCalidad> busquedaPorFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        return temasCalidadBean.busquedaPorFiltro(orgPk, mapFiltro, elementoOrdenacion, ascendente);
    }

    public TemasCalidad guardar(TemasCalidad tca) {
        return temasCalidadBean.guardarTca(tca);
    }
}
