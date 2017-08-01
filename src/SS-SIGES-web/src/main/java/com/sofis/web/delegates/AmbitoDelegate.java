package com.sofis.web.delegates;

import com.sofis.business.ejbs.AmbitoBean;
import com.sofis.entities.data.Ambito;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class AmbitoDelegate {
    
    @Inject
    private AmbitoBean ambitoBean;
    
    public Ambito obtenerAmbitoPorId(Integer ambitoPk) throws GeneralException {
        return ambitoBean.obtenerAmbitoPorId(ambitoPk);
    }
    
    public List<Ambito> obtenerAmbitoPorOrg(Integer orgPk) throws GeneralException {
        return ambitoBean.obtenerAmbitoPorOrg(orgPk);
    }

    public void eliminarAmbito(Integer aPk) {
        ambitoBean.eliminarAmbito(aPk);
    }

    public List<Ambito> busquedaAmbitoFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        return ambitoBean.busquedaAmbitoFiltro(orgPk, mapFiltro, elementoOrdenacion, ascendente);
    }

    public Ambito guardarAmbito(Ambito ambitoEnEdicion) {
        return ambitoBean.guardarAmbito(ambitoEnEdicion);
    }
}
