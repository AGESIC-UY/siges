package com.sofis.web.delegates;

import com.sofis.business.ejbs.BusquedaFiltroBean;
import com.sofis.entities.data.BusquedaFiltro;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FiltroInicioTO;
import com.sofis.exceptions.GeneralException;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class BusquedaFiltroDelegate {

    @Inject
    BusquedaFiltroBean busquedaFiltroBean;

    public BusquedaFiltro guardar(FiltroInicioTO filtro, SsUsuario usuario, Organismos organismo) throws GeneralException {
        return busquedaFiltroBean.guardar(filtro, usuario, organismo);
    }

    public BusquedaFiltro obtenerFiltroPorUsuOrg(SsUsuario usuario, Organismos organismo) {
        return busquedaFiltroBean.obtenerFiltroPorUsuOrg(usuario, organismo);
    }

    public FiltroInicioTO obtenerFiltroInicio(SsUsuario usuario, Organismos organismo) {
        return busquedaFiltroBean.obtenerFiltroInicio(usuario, organismo);
    }
}
