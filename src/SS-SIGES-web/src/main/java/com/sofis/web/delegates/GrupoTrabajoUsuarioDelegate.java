package com.sofis.web.delegates;

import com.sofis.business.ejbs.GrupoTrabajoUsuarioBean;
import com.sofis.entities.data.GrupoTrabajo;
import com.sofis.entities.data.GrupoTrabajoUsuario;
import com.sofis.exceptions.GeneralException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class GrupoTrabajoUsuarioDelegate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private GrupoTrabajoUsuarioBean cnfBean;

    
    public List<GrupoTrabajoUsuario>  listarPorOrgYUsuario( Integer orgPk,Integer usuaPk) throws GeneralException {
        return cnfBean.listarPorOrgYUsuario(orgPk, usuaPk);
    }

    public List<GrupoTrabajo> listarPorOrgYUsuarioyFiltro(int orgaPk, int usuarioPk, Map<String, Object> mapFiltro) {
        return cnfBean.listarPorOrgYUsuario(orgaPk, usuarioPk, mapFiltro);
    }
    
    public void eliminarGrupo(Integer grupoPk){
        
        cnfBean.eliminarGrupo(grupoPk);
        
    }
    
    public void insertarGrupo(Integer gtbPk, Integer integer) {
       cnfBean.inserTarGrupo(gtbPk, integer);
    }
    
}
