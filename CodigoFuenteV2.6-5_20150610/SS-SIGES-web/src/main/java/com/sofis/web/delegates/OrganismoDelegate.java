package com.sofis.web.delegates;

import com.sofis.business.ejbs.OrganismoBean;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Organismos;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
public class OrganismoDelegate {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    @Inject
    private OrganismoBean orgBean;

    public Organismos obtenerOrgPorId(Integer id) throws GeneralException {
        return orgBean.obtenerOrgPorId(id);
    }
    
    public boolean existenOrganismos() throws GeneralException {
        return orgBean.existenOrganismos();
    }
    
    public Organismos guardarOrgnanismo(Organismos org){
        return orgBean.guardarOrgnanismo(org);
    }

    public List<Organismos> obtenerTodos() {
        return orgBean.obtenerTodos();
    }
    
    public void controlarDatosFaltantes() {
        orgBean.controlarDatosFaltantes();
    }

    public Organismos guardarOrgnanismo(Organismos org, Map<String, Object> mapCopiar) {
        return orgBean.guardarOrgnanismo(org, mapCopiar);
    }
}
