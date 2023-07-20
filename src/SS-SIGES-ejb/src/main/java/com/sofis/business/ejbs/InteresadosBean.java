package com.sofis.business.ejbs;

import com.sofis.business.validations.InteresadosValidacion;
import com.sofis.data.daos.InteresadosDao;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Interesados;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless(name = "InteresadosBean")
@LocalBean
public class InteresadosBean {

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	private static final Logger LOGGER = Logger.getLogger(InteresadosBean.class.getName());

	@Inject
	private DatosUsuario du;

	@Inject
	private ProgramasBean programasBean;

	@Inject
	private ProyectosBean proyectosBean;

	@Inject
	private SsUsuarioBean usuarioBean;

	public Interesados guardar(Interesados interesado) throws GeneralException {

		InteresadosValidacion.validar(interesado);
		InteresadosDao interesadosDao = new InteresadosDao(em);

		try {

			switch (interesado.getTipo()) {
				case EXTERNO:
					interesado.setUsuario(null);
					break;

				case INTERNO:
					SsUsuario usuario = usuarioBean.obtenerSsUsuarioPorId(interesado.getUsuario().getUsuId());

					interesado.getIntPersonaFk().setPersNombre(usuario.getNombreApellido());
					interesado.getIntPersonaFk().setPersMail(usuario.getUsuCorreoElectronico());

					interesado.setUsuario(usuario);
					break;
			}

			interesado = interesadosDao.update(interesado, du.getCodigoUsuario(), du.getOrigen());

		} catch (BusinessException be) {
			//Si es de tipo negocio envía la misma excepción
			throw be;

		} catch (Exception ex) {
			LOGGER.logp(Level.SEVERE, InteresadosBean.class.getName(), "guardar", ex.getMessage(), ex);
			TechnicalException ge = new TechnicalException(ex);
			ge.addError(ex.getMessage());
			throw ge;
		}

		return interesado;
	}

	/**
	 * Guarda el interesado asociado a un Programa o Proyecto.
	 *
	 * @param interesado
	 * @param fichaFk
	 * @param tipoFicha
	 * @return Objeto Programas o Proyectos dependiento del tipo de ficha
	 * aportado.
	 * @throws GeneralException
	 */
	public Object guardarInteresados(Interesados interesado, Integer fichaFk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) throws GeneralException {

		interesado = guardar(interesado);

		if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
			Programas prog = programasBean.obtenerProgPorId(fichaFk);

			if (prog.getInteresadosList().contains(interesado)) {
				prog.getInteresadosList().remove(interesado);
			}

			prog.getInteresadosList().add(interesado);
			prog = programasBean.guardarPrograma(prog, usuario, orgPk, false);

			return prog;

		} else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {

			Proyectos proyecto = proyectosBean.obtenerProyPorId(fichaFk);

			if (proyecto.getInteresadosList().contains(interesado)) {
				proyecto.getInteresadosList().remove(interesado);
			}

			proyecto.getInteresadosList().add(interesado);

			proyectosBean.actualizarFechaUltimaModificacion(proyecto);

			return proyecto;
		}

		return null;
	}

	public Interesados obtenerInteresadosPorId(Integer id) {
		InteresadosDao interesadosDao = new InteresadosDao(em);
		try {
			return interesadosDao.findById(Interesados.class, id);
		} catch (DAOGeneralException ex) {
			LOGGER.logp(Level.SEVERE, InteresadosBean.class.getName(), "obtenerInteresadosPorId", ex.getMessage(), ex);
			TechnicalException te = new TechnicalException(ex);
			te.addError(ex.getMessage());
			throw te;
		}
	}

	/**
	 * Retorna una lista de Interesados para utilizar en el resumen de la ficha.
	 *
	 * @param proyPk
	 * @param tipoFicha
	 * @param size
	 * @return
	 */
	public List<Interesados> obtenerIntersadosResumen(Integer proyPk, Integer tipoFicha, int size) {
		InteresadosDao dao = new InteresadosDao(em);
		return dao.obtenerResumenInteresados(proyPk, tipoFicha, size);
	}

	public Object eliminar(Integer intPk, Integer fichaPk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) throws GeneralException {

		InteresadosDao dao = new InteresadosDao(em);
		try {

			Interesados interesado = this.obtenerInteresadosPorId(intPk);
			interesado.setIntPersonaFk(null);

			interesado.setIntEntregable(null);
			interesado = dao.update(interesado, this.du.getCodigoUsuario(), du.getOrigen());

			if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {

				Programas programa = programasBean.obtenerProgPorId(fichaPk);
				programa.getInteresadosList().remove(interesado);

				programasBean.guardarPrograma(programa, usuario, orgPk, false);
				
				return programa;

			} else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {

				Proyectos proyecto = proyectosBean.obtenerProyPorId(fichaPk);
				proyecto.getInteresadosList().remove(interesado);

				proyectosBean.actualizarFechaUltimaModificacion(proyecto);
				
				return proyecto;
			}

		} catch (BusinessException | DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			
			BusinessException be = new BusinessException();
			be.addError(ex.getMessage());
			
			throw be;
		}
		
		return null;
	}

	public List<Interesados> obtenerIntersadosPorFichaPk(Integer fichaPk, Integer tipoFicha) {
		InteresadosDao dao = new InteresadosDao(em);
		return dao.obtenerIntersadosPorFichaPk(fichaPk, tipoFicha);
	}

	public List<Interesados> copiarProyInteresados(List<Interesados> interesadosList, Map<Integer, Entregables> entregablesMap) {
		if (interesadosList != null) {
			List<Interesados> result = new ArrayList<>();

			Iterator<Interesados> iterator = interesadosList.iterator();
			while (iterator.hasNext()) {
				Interesados interesado = iterator.next();
				Interesados nvoInt = new Interesados();
				nvoInt.setIntObservaciones(interesado.getIntObservaciones());
				nvoInt.setIntOrgaFk(interesado.getIntOrgaFk());
				nvoInt.setIntPersonaFk(interesado.getIntPersonaFk());
				nvoInt.setIntRolintFk(interesado.getIntRolintFk());

				nvoInt.setTipo(interesado.getTipo());
				nvoInt.setUsuario(interesado.getUsuario());

				if (interesado.getIntEntregable() != null) {
					nvoInt.setIntEntregable(entregablesMap.get(interesado.getIntEntregable().getEntPk()));
				}

				result.add(nvoInt);
			}

			return result;
		}

		return null;
	}
}
