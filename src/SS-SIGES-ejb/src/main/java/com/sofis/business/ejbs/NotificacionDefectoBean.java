package com.sofis.business.ejbs;

import com.sofis.business.properties.LabelsEJB;
import com.sofis.business.validations.NotificacionDefectoValidacion;
import com.sofis.data.daos.NotificacionDefectoDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesNotificaciones;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.NotificacionDefecto;
import com.sofis.entities.tipos.FiltroNotificacionDefectoTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
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
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless(name = "notificacionDefectoBean")
@LocalBean
public class NotificacionDefectoBean {

	private static final Logger LOGGER = Logger.getLogger(NotificacionDefectoBean.class.getName());

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	public NotificacionDefecto guardar(NotificacionDefecto notif) {
		if (notif != null) {
			NotificacionDefectoValidacion.validar(notif);
			validarDuplicado(notif);

			NotificacionDefectoDAO dao = new NotificacionDefectoDAO(em);
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

	public List<NotificacionDefecto> obtenerTodas() {
		NotificacionDefectoDAO dao = new NotificacionDefectoDAO(em);
		try {
			return dao.findAll();

		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, null, ex);

			BusinessException be = new BusinessException(ex);
			be.addError(MensajesNegocio.ERROR_NOTIFICACION_OBTENER);

			throw be;
		}
	}

	public NotificacionDefecto obtenerPorId(Integer notifPk) {
		NotificacionDefectoDAO dao = new NotificacionDefectoDAO(em);

		try {
			return dao.findById(NotificacionDefecto.class, notifPk);
		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			TechnicalException te = new TechnicalException(ex);
			te.addError(MensajesNegocio.ERROR_NOTIFICACION_OBTENER);
			throw te;
		}
	}

	public List<NotificacionDefecto> buscar(FiltroNotificacionDefectoTO filtro, String elementoOrdenacion, int ascendente) {

		List<CriteriaTO> criterios = new ArrayList<>();

		if (!StringsUtils.isEmpty(filtro.getCodigo())) {
			MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "codigo", filtro.getCodigo());
			criterios.add(criterio);
		}

		if (!StringsUtils.isEmpty(filtro.getDescripcion())) {
			CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("descripcion", filtro.getDescripcion());
			criterios.add(criterio);
		}

		if (!StringsUtils.isEmpty(filtro.getMensaje())) {
			CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("mensaj", filtro.getMensaje());
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

		NotificacionDefectoDAO dao = new NotificacionDefectoDAO(em);

		try {
			return dao.findEntityByCriteria(NotificacionDefecto.class, condicion, orderBy, asc, null, null);

		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_NOTIFICACION_OBTENER);

			throw be;
		}
	}

	public void eliminar(Integer notifPk) {

		if (notifPk != null) {
			NotificacionDefectoDAO dao = new NotificacionDefectoDAO(em);
			try {
				NotificacionDefecto n = obtenerPorId(notifPk);
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

	public void controlarFaltantes() {

		List<NotificacionDefecto> notificacionesDefectoExistentes = obtenerTodas();

		Map<String, NotificacionDefecto> notificacionesDefectoMap = new HashMap<>();

		for (NotificacionDefecto notificacion : notificacionesDefectoExistentes) {
			notificacionesDefectoMap.put(notificacion.getCodigo(), notificacion);
		}

		NotificacionDefecto[] notificacionesDefectoAplicacion = new NotificacionDefecto[]{
			new NotificacionDefecto(ConstantesNotificaciones.NOT_COD_RIESGOS_1, ConstantesNotificaciones.ASUNTO_DEFECTO, LabelsEJB.getValue("notif_cod_riesgos_1_desc"), true, false, false, false, LabelsEJB.getValue("notif_cod_riesgos_1_mail")),
			new NotificacionDefecto(ConstantesNotificaciones.NOT_COD_RIESGOS_2, ConstantesNotificaciones.ASUNTO_DEFECTO, LabelsEJB.getValue("notif_cod_riesgos_2_desc"), true, false, false, false, LabelsEJB.getValue("notif_cod_riesgos_2_mail")),
			new NotificacionDefecto(ConstantesNotificaciones.NOT_COD_INICIO, ConstantesNotificaciones.ASUNTO_DEFECTO, LabelsEJB.getValue("notif_cod_inicio_desc"), true, false, false, false, LabelsEJB.getValue("notif_cod_inicio_mail")),
			new NotificacionDefecto(ConstantesNotificaciones.NOT_COD_PLANIFICACION, ConstantesNotificaciones.ASUNTO_DEFECTO, LabelsEJB.getValue("notif_cod_planificacion_desc"), true, false, false, false, LabelsEJB.getValue("notif_cod_planificacion_mail")),
			new NotificacionDefecto(ConstantesNotificaciones.NOT_COD_ACTUALIZACION_1, ConstantesNotificaciones.ASUNTO_DEFECTO, LabelsEJB.getValue("notif_cod_actualizacion_1_desc"), true, false, false, false, LabelsEJB.getValue("notif_cod_actualizacion_1_mail")),
			new NotificacionDefecto(ConstantesNotificaciones.NOT_COD_ACTUALIZACION_2, ConstantesNotificaciones.ASUNTO_DEFECTO, LabelsEJB.getValue("notif_cod_actualizacion_2_desc"), true, false, false, false, LabelsEJB.getValue("notif_cod_actualizacion_2_mail")),
			new NotificacionDefecto(ConstantesNotificaciones.NOT_COD_PRESUPUESTO_1, ConstantesNotificaciones.ASUNTO_DEFECTO, LabelsEJB.getValue("notif_cod_presupuesto_1_desc"), true, false, false, false, LabelsEJB.getValue("notif_cod_presupuesto_1_mail")),
			new NotificacionDefecto(ConstantesNotificaciones.NOT_COD_PRESUPUESTO_2, ConstantesNotificaciones.ASUNTO_DEFECTO, LabelsEJB.getValue("notif_cod_presupuesto_2_desc"), true, false, false, false, LabelsEJB.getValue("notif_cod_presupuesto_2_mail")),
			new NotificacionDefecto(ConstantesNotificaciones.NOT_COD_PRESUPUESTO_3, ConstantesNotificaciones.ASUNTO_DEFECTO, LabelsEJB.getValue("notif_cod_presupuesto_3_desc"), true, false, false, false, LabelsEJB.getValue("notif_cod_presupuesto_3_mail")),
			new NotificacionDefecto(ConstantesNotificaciones.NOT_COD_PRESUPUESTO_4, ConstantesNotificaciones.ASUNTO_DEFECTO, LabelsEJB.getValue("notif_cod_presupuesto_4_desc"), true, false, false, false, LabelsEJB.getValue("notif_cod_presupuesto_4_mail")),
			new NotificacionDefecto(ConstantesNotificaciones.NOT_COD_PRESUPUESTO_5, ConstantesNotificaciones.ASUNTO_DEFECTO, LabelsEJB.getValue("notif_cod_presupuesto_5_desc"), true, false, false, false, LabelsEJB.getValue("notif_cod_presupuesto_5_mail")),
			new NotificacionDefecto(ConstantesNotificaciones.NOT_COD_PRESUPUESTO_6, ConstantesNotificaciones.ASUNTO_PROXIMA_COMPRA_INICIAR, LabelsEJB.getValue("notif_cod_presupuesto_6_desc"), true, true, false, false, LabelsEJB.getValue("notif_cod_presupuesto_6_mail")),
                        new NotificacionDefecto(ConstantesNotificaciones.NOT_COD_CRONOGRAMA_1, ConstantesNotificaciones.ASUNTO_DEFECTO, LabelsEJB.getValue("notif_cod_cronograma_1_desc"), true, false, false, false, LabelsEJB.getValue("notif_cod_cronograma_1_mail")),
			new NotificacionDefecto(ConstantesNotificaciones.NOT_COD_CAMBIO_FASE_PROY_1, ConstantesNotificaciones.ASUNTO_DEFECTO, LabelsEJB.getValue("notif_cod_cambio_de_fase_1_mail"), true, false, false, false, LabelsEJB.getValue("notif_cod_cambio_de_fase_1_mail")),
			new NotificacionDefecto(ConstantesNotificaciones.NOT_COD_ELIMINACION_PROY_1, ConstantesNotificaciones.ASUNTO_DEFECTO, LabelsEJB.getValue("notif_cod_eliminar_1_mail"), true, false, false, false, LabelsEJB.getValue("notif_cod_eliminar_1_mail"))
		};

		for (NotificacionDefecto nd : notificacionesDefectoAplicacion) {
			if (!notificacionesDefectoMap.containsKey(nd.getCodigo())) {

				NotificacionDefecto notificacionDefecto = new NotificacionDefecto(nd.getCodigo(), nd.getAsunto(),
						nd.getDescripcion(), nd.getGerenteAdjunto(), nd.getPmof(), nd.getPmot(), nd.getSponsor(), nd.getMensaje());

				guardar(notificacionDefecto);

				LOGGER.log(Level.INFO, "Se agrega la notificacion por defecto {0}", nd.getCodigo());
			}
		}
	}

	private void validarDuplicado(NotificacionDefecto notif) {

		List<CriteriaTO> criterios = new ArrayList<>();

		criterios.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "codigo", notif.getCodigo()));
		criterios.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NOT_EQUALS, "id", notif.getId()));

		CriteriaTO condicion = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));

		NotificacionDefectoDAO dao = new NotificacionDefectoDAO(em);

		try {
			Long count = dao.countByCriteria(NotificacionDefecto.class, condicion, null, null);

			if (count > 0) {
				BusinessException be = new BusinessException();
				be.addError(MensajesNegocio.ERROR_NOTIFICACION_CODIGO_DUPLICADO);

				throw be;
			}
		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_NOTIFICACION_OBTENER);

			throw be;
		}
	}
}
