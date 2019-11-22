package com.sofis.web.delegates;

import com.sofis.business.ejbs.CausalCompraBean;
import com.sofis.entities.data.CausalCompra;
import com.sofis.entities.tipos.FiltroCausalCompraTO;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class CausalCompraDelegate {
    
    @Inject
    private CausalCompraBean bean;
    
    public CausalCompra obtenerPorId(Integer objEstPk){
       return bean.obtenerPorId(objEstPk);
    }
    
    public CausalCompra guardar(CausalCompra objEst){
        return bean.guardar(objEst);
    }
    
    public List<CausalCompra> obtenerPorFiltro(FiltroCausalCompraTO filtro){
        return bean.obtenerPorFiltro(filtro);
    }
    
    public void eliminar(Integer cauComId){
        bean.eliminar(cauComId);
    }
    
}
