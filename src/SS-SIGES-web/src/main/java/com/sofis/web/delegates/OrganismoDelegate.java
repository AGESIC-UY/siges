package com.sofis.web.delegates;

import com.sofis.business.ejbs.OrganismoBean;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.tipos.IdNombreTO;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class OrganismoDelegate {

    @Inject
    private OrganismoBean orgBean;

    public Organismos obtenerOrgPorId(Integer id, Boolean conLogo) throws GeneralException {
        return orgBean.obtenerOrgPorId(id, conLogo);
    }

    public boolean existenOrganismos() throws GeneralException {
        return orgBean.existenOrganismos();
    }

    public Organismos guardarOrgnanismo(Organismos org) {
        return orgBean.guardarOrgnanismo(org);
    }

    public List<Organismos> obtenerTodos() {
        return orgBean.obtenerTodos();
    }

    public List<Organismos> obtenerTodosActivos() {
        return orgBean.obtenerTodosActivos();
    }

    public void controlarDatosFaltantes() {
        orgBean.controlarDatosFaltantes();
    }

    public Organismos guardarOrgnanismo(Organismos org, Map<String, Object> mapCopiar) {
        return orgBean.guardarOrgnanismo(org, mapCopiar);
    }

    public List<IdNombreTO> obtenerOrgIdNombre(Integer usuId) {
        return orgBean.obtenerOrgIdNombre(usuId);
    }
    
    public Organismos obtenerOrgPorToken(String token) throws GeneralException {
        return orgBean.obtenerOrgPorToken(token);
    }
    
}
