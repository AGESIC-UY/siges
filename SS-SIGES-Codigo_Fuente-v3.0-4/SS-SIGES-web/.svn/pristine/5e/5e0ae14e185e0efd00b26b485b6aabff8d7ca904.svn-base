package com.sofis.web.delegates;

import com.sofis.business.ejbs.ProyReplanificacionBean;
import com.sofis.entities.data.ProyReplanificacion;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class ProyReplanificacionDelegate {
    
    @Inject
    private ProyReplanificacionBean proyReplanificacionBean;
    
    public List<ProyReplanificacion> obtenerReplanPorProyPk(Integer proyPk){
        return proyReplanificacionBean.obtenerReplanPorProyPk(proyPk);
    }
    
    public List<ProyReplanificacion> obtenerReplanHistPorProyPk(Integer proyPk){
        return proyReplanificacionBean.obtenerReplanHistPorProyPk(proyPk);
    }
    
    public ProyReplanificacion obtenerUltimaSolicitud(Integer fichaFk) {
        return proyReplanificacionBean.obtenerUltimaSolicitud(fichaFk);
    }
}
