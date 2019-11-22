package com.sofis.web.delegates;

import com.sofis.business.ejbs.CentroCostoBean;
import com.sofis.entities.data.CentroCosto;
import com.sofis.entities.tipos.FiltroCentroCostoTO;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class CentroCostoDelegate {
    
    @Inject
    private CentroCostoBean bean;
    
    public CentroCosto obtenerPorId(Integer objEstPk){
       return bean.obtenerPorId(objEstPk);
    }
    
    public CentroCosto guardar(CentroCosto objEst){
        return bean.guardar(objEst);
    }
    
    public List<CentroCosto> obtenerPorFiltro(FiltroCentroCostoTO filtro){
        return bean.obtenerPorFiltro(filtro);
    }
    
    public void eliminar(Integer objEstPk){
        bean.eliminar(objEstPk);
    }
    
}
