package com.sofis.web.delegates;

import com.sofis.business.ejbs.TipoGastoBean;
import com.sofis.entities.data.TipoGasto;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class TipoGastoDelegate {
    
    @Inject
    TipoGastoBean tipoGastoBean;

    public void eliminarTipoGasto(Integer tgPk) {
        tipoGastoBean.eliminarTipoGasto(tgPk);
    }

    public List<TipoGasto> busquedaTipoGastoFiltro(Integer orgPk, String filtroNombre, String elementoOrdenacion, int ascendente) {
        return tipoGastoBean.busquedaTipoGastoFiltro(orgPk, filtroNombre, elementoOrdenacion, ascendente);
    }

    public TipoGasto obtenerTipoGastoPorPk(Integer tgPk) {
        return tipoGastoBean.obtenerTipoGastoPorPk(tgPk);
    }

    public TipoGasto guardarTipoGasto(TipoGasto tipoGastoEnEdicion) {
        return tipoGastoBean.guardarTipoGasto(tipoGastoEnEdicion);
    }

    public List<TipoGasto> obtenerTipoGastoPorOrg(Integer orgPk) {
        return tipoGastoBean.obtenerTipoGastoPorOrg(orgPk);
    }
}
