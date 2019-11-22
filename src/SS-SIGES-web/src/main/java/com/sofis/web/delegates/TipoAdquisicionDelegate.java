package com.sofis.web.delegates;

import com.sofis.business.ejbs.TipoAdquisicionBean;
import com.sofis.entities.data.TipoAdquisicion;
import com.sofis.entities.tipos.FiltroTipoAdquisicionTO;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class TipoAdquisicionDelegate {
    
    @Inject
    private TipoAdquisicionBean bean;
    
    public TipoAdquisicion obtenerPorId(Integer objEstPk){
       return bean.obtenerPorId(objEstPk);
    }
    
    public TipoAdquisicion guardar(TipoAdquisicion objEst){
        return bean.guardar(objEst);
    }
    
    public List<TipoAdquisicion> obtenerPorFiltro(FiltroTipoAdquisicionTO filtro){
        return bean.obtenerPorFiltro(filtro);
    }
    
    public void eliminar(Integer objEstPk){
        bean.eliminar(objEstPk);
    }
    
}
