package com.sofis.web.delegates;

import com.sofis.business.ejbs.ProyPublicaHistBean;
import com.sofis.entities.data.ProyPublicaHist;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class ProyPublicaHistDelegate {

    @Inject
    private ProyPublicaHistBean proyPublicaHistBean;

    public List<ProyPublicaHist> obtenerHistoricoTodos(Integer proyPk) {
        return proyPublicaHistBean.obtenerHistoricoTodos(proyPk);
    }

    public Date obtenerUltimaFecha(Integer proyPk) {
        return proyPublicaHistBean.obtenerUltimaFecha(proyPk);
    }
}
