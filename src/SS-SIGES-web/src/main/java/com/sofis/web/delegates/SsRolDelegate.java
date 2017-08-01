package com.sofis.web.delegates;

import com.sofis.business.ejbs.SsRolBean;
import com.sofis.entities.data.SsRol;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class SsRolDelegate {
    
    @Inject
    private SsRolBean ssRolBean;
    
    public List<SsRol> obtenerRolesUsuarios(){
        return ssRolBean.obtenerRolesUsuarios();
    }
    
    public List<SsRol> obtenerRoles(){
        return ssRolBean.obtenerRoles();
    }

    public SsRol obtenerRolesPorCod(String cod) {
        return ssRolBean.obtenerRolPorCod(cod);
    }
}
