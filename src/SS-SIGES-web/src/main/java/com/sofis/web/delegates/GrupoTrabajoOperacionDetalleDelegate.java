package com.sofis.web.delegates;

import com.sofis.business.ejbs.GrupoTrabajoOperacionDetalleBean;
import com.sofis.exceptions.GeneralException;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class GrupoTrabajoOperacionDetalleDelegate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private GrupoTrabajoOperacionDetalleBean cnfBean;

    
    public void  eliminarGrupo( Integer grupoPk) throws GeneralException {
         cnfBean.eliminarGrupo(grupoPk);
    }

    public void insertarGrupo(Integer gtbPk, Integer integer) {
       cnfBean.inserTarGrupo(gtbPk, integer);
    }

    
}
