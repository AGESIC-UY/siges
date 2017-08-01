/*
 * 
 * 
 */
package com.sofis.web.delegates;

import com.sofis.entities.data.Configuracion;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import javax.inject.Inject;
import com.sofis.business.ejbs.ConsultaHistorico;

/**
 *
 * @author Usuario
 */
public  class ConsultaHistoricoDelegate  <T>{
    @Inject
    ConsultaHistorico consBean;
    
    public List<Configuracion> obtenerConfiguracion(Integer id) throws GeneralException  {
        return consBean.obtenerConfiguracion(id);
    }
    
    public<T> List<T> obtenerHistorialPorId(Class clase, Integer id, String campoVersion) throws GeneralException {
        return consBean.obtenerHistorialPorId(clase, id, campoVersion);
    }
    
}
