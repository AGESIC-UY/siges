package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.AreaTematicaValidacion;
import com.sofis.data.daos.AreasTagsDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.tipos.FiltroAreaTematicaTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless(name = "AreaTematicaBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class AreaTematicaBean {

	private static final Logger LOGGER = Logger.getLogger(AreaTematicaBean.class.getName());

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	@Inject
	private DatosUsuario du;

	public AreasTags guardar(AreasTags areaTematica) {
		if (areaTematica == null) {
			return null;
		}

		areaTematica.setAreatagExcluyente(true);
		areaTematica.setAreatagTematica(areaTematica.getAreatagNombre());

		if (areaTematica.getHabilitada() == null) {
			areaTematica.setHabilitada(false);
		}

		AreaTematicaValidacion.validar(areaTematica);
		validarDuplicado(areaTematica);

		AreasTagsDAO dao = new AreasTagsDAO(em);
		try {
			Boolean habilitacionAnterior = null;
			if (areaTematica.getArastagPk() != null) {
				AreasTags at = dao.findById(AreasTags.class, areaTematica.getArastagPk());

				if (at != null) {
					habilitacionAnterior = at.getHabilitada();
				}
			}

			areaTematica = dao.update(areaTematica, du.getCodigoUsuario(), du.getOrigen());
			
			if (habilitacionAnterior != null && !habilitacionAnterior.equals(areaTematica.getHabilitada())) {

				if (areaTematica.getHabilitada()) {
					habilitarPadres(areaTematica);
				} else {
					deshabilitarHijas(areaTematica);
				}
			}

			return areaTematica;

		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);

			BusinessException be = new BusinessException();
			be.addError(ex.getMessage());
			throw be;
		}
	}

	public AreasTags obtenerAreaTemPorPk(Integer atPk) {
		if (atPk != null) {
			AreasTagsDAO dao = new AreasTagsDAO(em);
			try {
				return dao.findById(AreasTags.class, atPk);
			} catch (DAOGeneralException ex) {
				LOGGER.log(Level.SEVERE, null, ex);
				BusinessException be = new BusinessException();
				be.addError(MensajesNegocio.ERROR_AREAS_TEMATICAS_OBTENER);
				throw be;
			}
		}
		return null;
	}

	public List<AreasTags> buscar(FiltroAreaTematicaTO filtro, String elementoOrdenacion, boolean ascendente) {

		AreasTagsDAO areaTematicaDAO = new AreasTagsDAO(em);
		try {
			return areaTematicaDAO.buscar(filtro, elementoOrdenacion, ascendente);

		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, null, ex);

			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_AREAS_TEMATICAS_OBTENER);
			throw be;
		}
	}

	public List<AreasTags> buscar(FiltroAreaTematicaTO filtro) {

		return buscar(filtro, null, true);
	}

	public List<AreasTags> buscar(FiltroAreaTematicaTO filtro, String elementoOrdenacion) {

		return buscar(filtro, elementoOrdenacion, true);
	}

	public void eliminarAreaTematica(Integer atPk) {
		if (atPk != null) {
			AreasTagsDAO dao = new AreasTagsDAO(em);
			try {
				AreasTags a = obtenerAreaTemPorPk(atPk);
				dao.delete(a);
			} catch (DAOGeneralException ex) {
				LOGGER.log(Level.SEVERE, null, ex);

				BusinessException be = new BusinessException();
				be.addError(MensajesNegocio.ERROR_AREAS_TEMATICAS_ELIMINAR);
				if (ex.getCause() instanceof javax.persistence.PersistenceException
						&& ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					be.addError(MensajesNegocio.ERROR_AREAS_TEMATICAS_CONST_VIOLATION);
				}
				throw be;
			}
		}
	}

	public List<AreasTags> obtenerPorOrganismo(int orgPk) {

		FiltroAreaTematicaTO filtro = new FiltroAreaTematicaTO();
		filtro.setIdOrganismo(orgPk);

		return buscar(filtro);
	}

	public List<AreasTags> obtenerHabilitadasPorOrganismo(int orgPk) {

		FiltroAreaTematicaTO filtro = new FiltroAreaTematicaTO();
		filtro.setIdOrganismo(orgPk);
		filtro.setHabilitada(true);

		return buscar(filtro);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<AreasTags> obtenerAreasTematicasPorFichaPk(Integer fichaPf, Integer tipoFicha) {
		AreasTagsDAO dao = new AreasTagsDAO(em);
		try {
			List<AreasTags> resultado = dao.obtenerAreasTematicasPorFichaPk(fichaPf, tipoFicha);
			return resultado;
		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage());
			TechnicalException te = new TechnicalException(ex);
			te.addError(ex.getMessage());
			throw te;
		}
	}

	private void validarDuplicado(AreasTags at) {
		List<AreasTags> list = obtenerAreasPorNombre(at.getAreatagNombre(), at.getAreatagOrgFk().getOrgPk());
		if (CollectionsUtils.isNotEmpty(list)) {
			for (AreasTags areasTags : list) {
				if (!areasTags.getArastagPk().equals(at.getArastagPk())
						&& (areasTags.getAreatagPadreFk() != null && at.getAreatagPadreFk() != null
						&& areasTags.getAreatagPadreFk().getArastagPk().equals(at.getAreatagPadreFk().getArastagPk()))
						&& areasTags.getAreatagNombre().equals(at.getAreatagNombre())) {

					BusinessException be = new BusinessException();
					be.addError(MensajesNegocio.ERROR_AREAS_TEMATICAS_NOMBRE_DUPLICADO);
					throw be;
				}
			}
		}
	}

	public List<AreasTags> obtenerAreasPorNombre(String areaNombre, Integer orgPk) {
		if (orgPk != null) {
			List<CriteriaTO> criterios = new ArrayList<>();

			MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
					MatchCriteriaTO.types.EQUALS, "areatagOrgFk.orgPk", orgPk);
			criterios.add(criterioOrg);

			MatchCriteriaTO criterioNombre = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "areatagNombre", areaNombre);
			criterios.add(criterioNombre);

			CriteriaTO criteria = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));

			AreasTagsDAO areasDao = new AreasTagsDAO(em);

			try {
				return areasDao.findEntityByCriteria(AreasTags.class, criteria, new String[]{"areatagNombre"}, new boolean[]{true}, null, null);

			} catch (DAOGeneralException ex) {
				LOGGER.log(Level.SEVERE, null, ex);

				BusinessException te = new BusinessException();
				te.addError(MensajesNegocio.ERROR_AREAS_TEMATICAS_OBTENER);
				throw te;
			}
		}
		return null;
	}

	public List<AreasTags> obtenerPadres(List<AreasTags> hijas) {

		List<AreasTags> resultado = new ArrayList<>();
		for (AreasTags hija : hijas) {

			AreasTags padre = hija;
			while (padre != null) {
				if (!resultado.contains(padre)) {
					resultado.add(padre);
				}
				padre = padre.getAreatagPadreFk();
			}
		}

		return resultado;
	}

	private void habilitarPadres(AreasTags areaTematica) {

		AreasTags at = areaTematica.getAreatagPadreFk();
		while (at != null) {
			at.setHabilitada(true);

			at = at.getAreatagPadreFk();
		}
	}

	private void deshabilitarHijas(AreasTags areaTematica) {

		AreasTagsDAO areaTematicaDao = new AreasTagsDAO(em);

		List<AreasTags> areasTematicas = areaTematicaDao.obtenerHijas(areaTematica.getArastagPk());

		ListIterator<AreasTags> iter = areasTematicas.listIterator();
		while (iter.hasNext()) {
			AreasTags at = iter.next();
			
			at.setHabilitada(false);

			deshabilitarHijas(at);
		}
	}

}
