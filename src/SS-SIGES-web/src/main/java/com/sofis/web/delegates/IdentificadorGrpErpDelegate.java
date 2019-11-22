package com.sofis.web.delegates;

import com.sofis.business.ejbs.IdentificadorGrpErpBean;
import com.sofis.entities.data.IdentificadorGrpErp;
import com.sofis.entities.tipos.FiltroIdentificadorGrpErpTO;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class IdentificadorGrpErpDelegate {
    
    @Inject
    private IdentificadorGrpErpBean bean;
    
    public IdentificadorGrpErp obtenerPorId(Integer objEstPk){
       return bean.obtenerPorId(objEstPk);
    }
    
    public IdentificadorGrpErp guardar(IdentificadorGrpErp objEst){
        return bean.guardar(objEst);
    }
    
    public List<IdentificadorGrpErp> obtenerPorFiltro(FiltroIdentificadorGrpErpTO filtro){
        return bean.obtenerPorFiltro(filtro);
    }
    
    public void eliminar(Integer objEstPk){
        bean.eliminar(objEstPk);
    }
    
}
