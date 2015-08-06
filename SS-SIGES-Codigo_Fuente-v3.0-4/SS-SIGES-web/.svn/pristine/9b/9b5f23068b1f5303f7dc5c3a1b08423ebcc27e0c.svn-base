package com.sofis.web.delegates;

import com.sofis.business.ejbs.EstadosPublicacionBean;
import com.sofis.entities.data.EstadosPublicacion;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class EstadosPublicacionDelegate {

    @Inject
    private EstadosPublicacionBean estadosPublicacionBean;

    public List<EstadosPublicacion> obtenerPorCodigo(String codigo) {
        return estadosPublicacionBean.obtenerPorCodigo(codigo);
    }
}
