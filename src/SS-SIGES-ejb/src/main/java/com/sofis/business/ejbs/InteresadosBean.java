package com.sofis.business.ejbs;

import com.sofis.business.validations.InteresadosValidacion;
import com.sofis.data.daos.InteresadosDao;
import com.sofis.entities.constantes.ConstanteApp;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "InteresadosBean")
@LocalBean
public class InteresadosBean {

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;
        private static final Logger logger = Logger.getLogger(InteresadosBean.class.getName());

	@Inject
	private DatosUsuario du;
	@Inject
	private ProgramasBean programasBean;
	@Inject
	private ProyectosBean proyectosBean;

	//private String usuario;
	//private String origen;
	@PostConstruct
	public void init() {
		//usuario = du.getCodigoUsuario();
		//origen = du.getOrigen();
	}

	public Interesados guardar(Interesados interesados) throws GeneralException {

		InteresadosValidacion.validar(interesados);
		InteresadosDao interesadosDao = new InteresadosDao(em);

		try {
			interesados = interesadosDao.update(interesados, du.getCodigoUsuario(), du.getOrigen());

		} catch (BusinessException be) {
			//Si es de tipo negocio envía la misma excepción
			throw be;
		} catch (Exception ex) {
			logger.logp(Level.SEVERE, InteresadosBean.class.getName(), "guardar", ex.getMessage(), ex);
			TechnicalException ge = new TechnicalException(ex);
			ge.addError(ex.getMessage());
			throw ge;
		}

		return interesados;
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
			prog = programasBean.guardarPrograma(prog, usuario, orgPk);
			return prog;

		} else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
			Proyectos proy = proyectosBean.obtenerProyPorId(fichaFk);
			if (proy.getInteresadosList().contains(interesado)) {
				proy.getInteresadosList().remove(interesado);
			}
			proy.getInteresadosList().add(interesado);
			proy = proyectosBean.guardarProyecto(proy, usuario, orgPk);
			return proy;
		}

		return null;
	}

	public Interesados obtenerInteresadosPorId(Integer id) {
		InteresadosDao interesadosDao = new InteresadosDao(em);
		try {
			return interesadosDao.findById(Interesados.class, id);
		} catch (DAOGeneralException ex) {
			logger.logp(Level.SEVERE, InteresadosBean.class.getName(), "obtenerInteresadosPorId", ex.getMessage(), ex);
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

	public void delete(Integer intPk, Integer fichaPk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) throws GeneralException {
		logger.fine("Borrar un interesado(" + intPk + ").");
		InteresadosDao dao = new InteresadosDao(em);
		try {

			Interesados interesado = this.obtenerInteresadosPorId(intPk);
			interesado.setIntPersonaFk(null);
			/**
			 * 03-11-17 se agrega para desasociar el interesado al entregable porque sino los interesados no aparecen en el módulo 
			 * pero siguen asociados por base de datos al cronograma, lo cuál no permite 
			 * cargar de forma masiva el cronograma o borrar el entregable asociado al interesado
			 */
			interesado.setIntEntregable(null);
			interesado = dao.update(interesado, this.du.getCodigoUsuario(), du.getOrigen());

			if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
				Programas p = programasBean.obtenerProgPorId(fichaPk);
				p.getInteresadosList().remove(interesado);
				programasBean.guardarPrograma(p, usuario, orgPk);
			} else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
				Proyectos p = proyectosBean.obtenerProyPorId(fichaPk);
				p.getInteresadosList().remove(interesado);
				proyectosBean.guardarProyecto(p, usuario, orgPk);
			}

		} catch (BusinessException | DAOGeneralException ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			BusinessException be = new BusinessException();
			be.addError(ex.getMessage());
			throw be;
		}
	}

	public List<Interesados> obtenerIntersadosPorFichaPk(Integer fichaPk, Integer tipoFicha) {
		InteresadosDao dao = new InteresadosDao(em);
		return dao.obtenerIntersadosPorFichaPk(fichaPk, tipoFicha);
	}

	public List<Interesados> copiarProyInteresados(List<Interesados> interesadosList) {
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
				result.add(nvoInt);
			}
			return result;
		}
		return null;
	}
}
