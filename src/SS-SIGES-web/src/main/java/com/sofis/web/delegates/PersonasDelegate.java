package com.sofis.web.delegates;

import com.sofis.business.ejbs.PersonasBean;
import com.sofis.entities.data.Personas;
import com.sofis.exceptions.GeneralException;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class PersonasDelegate {

    @Inject
    PersonasBean personasBean;

    public List<Personas> obtenerPersonas(Integer orgaPk) {
        return personasBean.obtenerPersonas(orgaPk);
    }

    public Personas guardar(Personas p) {
        return personasBean.guardar(p);
    }

    public List<Personas> buscar(String bNombre, Integer bOrganismo, String orderBy, boolean asc) {
        return personasBean.buscar(bNombre, bOrganismo, orderBy, asc);
    }

    public Personas obtenerPersonaPorId(Integer persId) {
        return personasBean.obtenerPersonaPorId(persId);
    }

    public void eliminarPersona(Integer persId) throws GeneralException {
        personasBean.eliminarPersona(persId);
    }

    public void unificar(Personas personaUnificar, Collection<Personas> personasUnificar) throws GeneralException {
        personasBean.unificar(personaUnificar, personasUnificar);
    }
}
