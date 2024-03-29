package com.sofis.business.ejbs;

import com.sofis.business.validations.NotificacionValidacion;
import com.sofis.data.daos.NotificacionDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.MailTemplateDefecto;
import com.sofis.entities.data.MailsTemplate;
import com.sofis.entities.data.Notificacion;
import com.sofis.entities.data.NotificacionDefecto;
import com.sofis.entities.data.Organismos;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.HashMap;
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
@Stateless(name = "notificacionBean")
@LocalBean
public class NotificacionBean {

	private static final Logger LOGGER = Logger.getLogger(NotificacionBean.class.getName());

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	@Inject
	private OrganismoBean organismoBean;

	@Inject
	private NotificacionDefectoBean notificacionDefectoBean;

	public Notificacion guardar(Notificacion notif) {
		if (notif != null) {
			NotificacionValidacion.validar(notif);
			validarDuplicado(notif);

			NotificacionDAO dao = new NotificacionDAO(em);
			try {
				return dao.update(notif);
			} catch (DAOGeneralException ex) {
				LOGGER.log(Level.SEVERE, null, ex);
				BusinessException be = new BusinessException();
				be.addError(MensajesNegocio.ERROR_NOTIFICACION_GUARDAR);
				throw be;
			}
		}
		return null;
	}

	public void controlarFaltantes() {

		List<Organismos> organismos = organismoBean.obtenerTodos();
		List<NotificacionDefecto> notificacionesDefecto = notificacionDefectoBean.obtenerTodas();

		for (Organismos org : organismos) {
			controlarFaltantesOrganismo(org, notificacionesDefecto);
		}

	}

	public void controlarFaltantesOrganismo(Organismos org) {

		List<NotificacionDefecto> notificacionesDefecto = notificacionDefectoBean.obtenerTodas();

		controlarFaltantesOrganismo(org, notificacionesDefecto);
	}

	private void controlarFaltantesOrganismo(Organismos org, List<NotificacionDefecto> notificacionesDefecto) {

		List<Notificacion> notifList = obtenerTodosPorOrg(org.getOrgPk());
		Map<String, Notificacion> notifMap = new HashMap<>();

		for (Notificacion notif : notifList) {
			notifMap.put(notif.getNotCod(), notif);
		}

		for (NotificacionDefecto nd : notificacionesDefecto) {
			if (!notifMap.containsKey(nd.getCodigo())) {

				Notificacion notificacion = new Notificacion(org, nd.getCodigo(), nd.getAsunto(),
						nd.getDescripcion(), null, nd.getGerenteAdjunto(), nd.getPmof(), nd.getPmot(), nd.getSponsor(), nd.getMensaje());

				guardar(notificacion);

				LOGGER.log(Level.INFO, "Se agrega la notificacion {0} para el org {1}", new Object[]{notificacion.getNotCod(), org.getOrgPk()});
			}
		}
	}

	public List<Notificacion> obtenerTodosPorOrg(Integer orgPk) {
		NotificacionDAO dao = new NotificacionDAO(em);
		try {
			return dao.findByOneProperty(Notificacion.class, "notOrgFk.orgPk", orgPk);

		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			BusinessException be = new BusinessException(ex);
//            be.setEx(ex);
			be.addError(MensajesNegocio.ERROR_NOTIFICACION_OBTENER);
			throw be;
		}
	}

	public Notificacion obtenerNotifPorPk(Integer notifPk) {
		NotificacionDAO dao = new NotificacionDAO(em);

		try {
			return dao.findById(Notificacion.class, notifPk);
		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			TechnicalException te = new TechnicalException(ex);
			te.addError(MensajesNegocio.ERROR_NOTIFICACION_OBTENER);
			throw te;
		}
	}

	public List<Notificacion> busquedaNotifFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
		if (orgPk != null) {
			List<CriteriaTO> criterios = new ArrayList<>();

			MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
					MatchCriteriaTO.types.EQUALS, "notOrgFk.orgPk", orgPk);
			criterios.add(criterioOrg);

			String codigo = mapFiltro != null ? (String) mapFiltro.get("notCod") : null;
			if (!StringsUtils.isEmpty(codigo)) {
				MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
						MatchCriteriaTO.types.CONTAINS, "notCod", codigo);
				criterios.add(criterio);
			}

			String desc = mapFiltro != null ? (String) mapFiltro.get("notDesc") : null;
			if (!StringsUtils.isEmpty(desc)) {
//		MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "notDesc", desc);
				CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("notDesc", desc);
				criterios.add(criterio);
			}

			String msg = mapFiltro != null ? (String) mapFiltro.get("notMsg") : null;
			if (!StringsUtils.isEmpty(msg)) {
//		MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "notMsg", msg);
				CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("notMsg", msg);
				criterios.add(criterio);
			}

			CriteriaTO condicion;
			if (criterios.size() == 1) {
				condicion = criterios.get(0);
			} else {
				condicion = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));
			}

			String[] orderBy = {};
			boolean[] asc = {};
			if (!StringsUtils.isEmpty(elementoOrdenacion)) {
				orderBy = new String[]{elementoOrdenacion};
				asc = new boolean[]{(ascendente == 1)};
			}

			NotificacionDAO dao = new NotificacionDAO(em);

			try {
				return dao.findEntityByCriteria(Notificacion.class, condicion, orderBy, asc, null, null);
			} catch (DAOGeneralException ex) {
				LOGGER.log(Level.SEVERE, null, ex);
				BusinessException be = new BusinessException();
				be.addError(MensajesNegocio.ERROR_NOTIFICACION_OBTENER);
				throw be;
			}
		}
		return null;
	}

	public void eliminarNotificacion(Integer notifPk) {
		if (notifPk != null) {
			NotificacionDAO dao = new NotificacionDAO(em);
			try {
				Notificacion n = obtenerNotifPorPk(notifPk);
				dao.delete(n);
			} catch (DAOGeneralException ex) {
				LOGGER.log(Level.SEVERE, null, ex);

				BusinessException be = new BusinessException();
				be.addError(MensajesNegocio.ERROR_NOTIFICACION_ELIMINAR);
				if (ex.getCause() instanceof javax.persistence.PersistenceException
						&& ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					be.addError(MensajesNegocio.ERROR_NOTIFICACION_CONST_VIOLATION);
				}
				throw be;
			}
		}
	}

	private void validarDuplicado(Notificacion notif) {
		List<Notificacion> list = obtenerTodosPorOrg(notif.getNotOrgFk().getOrgPk());
		if (CollectionsUtils.isNotEmpty(list)) {
			for (Notificacion n : list) {
				if (!n.getNotPk().equals(notif.getNotPk())
						&& n.getNotCod().equals(notif.getNotCod())) {
					BusinessException be = new BusinessException();
					be.addError(MensajesNegocio.ERROR_NOTIFICACION_CODIGO_DUPLICADO);
					throw be;
				}
			}
		}
	}
}
