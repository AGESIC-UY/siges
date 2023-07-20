package com.sofis.web.delegates;

import com.sofis.business.ejbs.OrganismoBean;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.tipos.IdNombreTO;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class OrganismoDelegate {

	@Inject
	private OrganismoBean organismoBean;

	public Organismos obtenerOrgPorId(Integer id, Boolean conLogo) throws GeneralException {
		return organismoBean.obtenerOrgPorId(id, conLogo);
	}

	public boolean existenOrganismos() throws GeneralException {
		return organismoBean.existenOrganismos();
	}

	public Organismos guardarOrgnanismo(Organismos org) {
		return organismoBean.guardarOrgnanismo(org);
	}

	public List<Organismos> obtenerTodos() {
		return organismoBean.obtenerTodos();
	}

	public List<Organismos> obtenerTodosActivos() {
		return organismoBean.obtenerTodosActivos();
	}

	public Organismos guardarOrgnanismo(Organismos org, Map<String, Object> mapCopiar) {
		return organismoBean.guardarOrgnanismo(org, mapCopiar);
	}

	public List<IdNombreTO> obtenerOrgIdNombre(Integer usuId) {
		return organismoBean.obtenerOrgIdNombre(usuId);
	}

	public Organismos obtenerOrgPorToken(String token) throws GeneralException {
		return organismoBean.obtenerOrgPorToken(token);
	}

}
