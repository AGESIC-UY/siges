package com.sofis.web.delegates;

import com.sofis.business.ejbs.ProgramasBean;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class ProgramasDelegate {

	@Inject
	private ProgramasBean programasBean;

	public Programas guardarPrograma(Programas prog, SsUsuario usuario, Integer orgPk) throws GeneralException {
		return programasBean.guardarPrograma(prog, usuario, orgPk);
	}

	/**
	 * Retorna los Programas segun el Organismo aportado.
	 *
	 * @param id
	 * @return List<Programas>
	 * @throws GeneralException
	 */
	public List<Programas> obtenerProgPorOrganismo(int id) throws GeneralException {
		return programasBean.obtenerProgPorOrganismo(id);
	}

	public List<Programas> obtenerProgIniciadoPorOrg(int orgId) throws GeneralException {
		return programasBean.obtenerProgIniciadoPorOrg(orgId);
	}

	public List<Programas> obtenerProgComboPorOrg(int orgId, Boolean habilitado) throws GeneralException {
		return programasBean.obtenerProgComboPorOrg(orgId, habilitado);
	}

	public List<Programas> obtenerProgComboPorOrg(int orgId) throws GeneralException {
		return programasBean.obtenerProgComboPorOrg(orgId);
	}

	public Programas obtenerProgPorId(Integer id) throws GeneralException {
		return programasBean.obtenerProgPorId(id);
	}

	public Programas guardarPrograma(FichaTO fichaTO, SsUsuario usuario, Integer orgPk) throws GeneralException {
		return programasBean.guardarPrograma(fichaTO, usuario, orgPk);
	}

	public Programas guardarProgramaAprobacion(FichaTO fichaTO, SsUsuario usuario, Integer orgPk) throws GeneralException {
		return programasBean.guardarProgramaAprobacion(fichaTO, usuario, orgPk);
	}

	public Programas guardarProgramaAprobacion(Programas prog, SsUsuario usuario, Integer orgPk) throws GeneralException {
		return programasBean.guardarProgramaAprobacion(prog, usuario, orgPk);
	}

	public Programas darBajaPrograma(Integer progPk, SsUsuario usuario, Integer orgPk) throws GeneralException {
		return programasBean.darBajaPrograma(progPk, usuario, orgPk);
	}

	public List<Programas> obtenerTodosPorOrg(Integer orgPk) {
		return programasBean.obtenerTodosPorOrg(orgPk);
	}

	public void guardarIndicadoresSimple(Integer progPk, Integer orgPk) {
		programasBean.guardarIndicadoresSimple(progPk, orgPk);
	}

	public void actualizarEstadoPrograma(Proyectos proy, SsUsuario usuario, Integer orgPk) {
		programasBean.actualizarProgramaPorProyectos(proy, usuario, orgPk);
	}

	public void actualizarProgramaPorProyectos(Integer progPk, SsUsuario usuario, String origen) {
		programasBean.actualizarProgramaPorProyectosSimple(progPk, usuario, origen);
	}

	public List<Programas> obtenerProgramas(String codigo, String nombre, Integer orgPk) throws GeneralException {
		return programasBean.obtenerProgramas(codigo, nombre, orgPk);
	}

	public void habilitarDeshabilitarPrograma(Integer progPk, Boolean habilitado, SsUsuario u, Integer orgPk) throws GeneralException {
		programasBean.habilitarDeshabilitarPrograma(progPk, habilitado, u, orgPk);
	}

}
