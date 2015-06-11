package com.sofis.web.delegates;

import com.sofis.business.ejbs.EntHistLineaBaseBean;
import com.sofis.entities.data.EntHistLineaBase;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class EntHistLineaBaseDelegate {
    
    @Inject
    private EntHistLineaBaseBean entHistLineaBaseBean;
    
    public List<EntHistLineaBase> obtenerEntHistPorEntPk(Integer entPk){
        return entHistLineaBaseBean.obtenerEntHistPorEntPk(entPk);
    }
    
    public List<EntHistLineaBase> obtenerEntHistPorProyPk(Integer proyPk) {
        return entHistLineaBaseBean.obtenerEntHistPorProyPk(proyPk);
    }
}
