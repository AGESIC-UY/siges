package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.MailsTemplateValidacion;
import com.sofis.data.daos.MailsTemplateDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.MailTemplateDefecto;
import com.sofis.entities.data.MailsTemplate;
import com.sofis.entities.data.Organismos;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.AND_TO;
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
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless(name = "MailsTemplateBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class MailsTemplateBean {

	private static final Logger LOGGER = Logger.getLogger(MailsTemplateBean.class.getName());

	public static String MAIL_SOL_APROBACION = "MAIL_SOL_APROBACION";
	public static String MAIL_SOL_REPLANIFICACION = "MAIL_SOL_REPLANIFICACION";
	public static String MAIL_CAMBIO_ESTADO = "MAIL_CAMBIO_ESTADO";
	public static String MAIL_CAMBIO_CONTRASENIA = "MAIL_CAMBIO_CONTRASENIA";
	public static String MAIL_PROG_PROY_PENDIENTE = "MAIL_PROG_PROY_PENDIENTE";
	public static String MAIL_NVO_USUARIO = "MAIL_NVO_USUARIO";

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	@Inject
	private OrganismoBean organismoBean;

	@Inject
	private MailTemplateDefectoBean mailTemplateDefectoBean;

	public MailsTemplate guardar(MailsTemplate mt) {
		if (mt != null) {
			MailsTemplateValidacion.validar(mt);
			validarDuplicado(mt);

			MailsTemplateDAO dao = new MailsTemplateDAO(em);
			try {
				return dao.update(mt);
			} catch (DAOGeneralException ex) {
				LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
				BusinessException be = new BusinessException();
				be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_GUARDAR);
				throw be;
			}
		}
		return null;
	}

	public MailsTemplate obtenerMailTmpPorCodigo(String cod, Integer orgPk) throws BusinessException {
		if (!StringsUtils.isEmpty(cod)) {
			MailsTemplateDAO dao = new MailsTemplateDAO(em);
			List<MailsTemplate> listResult;
			try {

				MatchCriteriaTO codigoMatch = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "mailTmpCod", cod);
				MatchCriteriaTO orgMatch;
				if (orgPk != null) {
					orgMatch = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "mailTmpOrgFk.orgPk", orgPk);
				} else {
					orgMatch = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NULL, "mailTmpOrgFk.orgPk", 1);
				}

				CriteriaTO criteria = new AND_TO(codigoMatch, orgMatch);

				listResult = dao.findEntityByCriteria(MailsTemplate.class, criteria, new String[]{}, new boolean[]{}, null, null);

			} catch (DAOGeneralException ex) {
				Logger.getLogger(MailsTemplateBean.class.getName()).log(Level.SEVERE, null, ex);
				BusinessException be = new BusinessException();
				be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_OBTENER);
				throw be;
			}

			return (MailsTemplate) DAOUtils.obtenerSingleResult(listResult);
		}
		return null;
	}

	public List<MailsTemplate> obtenerTodosPorOrg(Integer orgPk) {
		MailsTemplateDAO dao = new MailsTemplateDAO(em);
		try {
			CriteriaTO criteriaOrg;
			if (orgPk != null) {
				criteriaOrg = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "mailTmpOrgFk.orgPk", orgPk);
			} else {
				criteriaOrg = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NULL, "mailTmpOrgFk.orgPk", 1);
			}
			CriteriaTO criteria = criteriaOrg;

			List<MailsTemplate> resultado = dao.findEntityByCriteria(MailsTemplate.class, criteria, new String[]{}, new boolean[]{}, null, null);
			return resultado;
		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			BusinessException be = new BusinessException();
			be.addError(ex.getMessage());
			throw be;
		}
	}

	public void controlarFaltantes() {
		List<Organismos> organismos = organismoBean.obtenerTodos();
		List<MailTemplateDefecto> mailTemplatesDefecto = mailTemplateDefectoBean.obtenerTodos();

		for (Organismos org : organismos) {
			controlarFaltantesOrganismo(org, mailTemplatesDefecto);
		}
	}

	public void controlarFaltantesOrganismo(Organismos org) {

		List<MailTemplateDefecto> mailTemplatesDefecto = mailTemplateDefectoBean.obtenerTodos();
		controlarFaltantesOrganismo(org, mailTemplatesDefecto);
	}

	public void controlarFaltantesOrganismo(Organismos org, List<MailTemplateDefecto> mailTemplatesDefecto) {

		List<MailsTemplate> mtList = obtenerTodosPorOrg(org.getOrgPk());
		Map<String, MailsTemplate> mtMap = new HashMap<>();

		for (MailsTemplate mt : mtList) {
			mtMap.put(mt.getMailTmpCod(), mt);
		}

		for (MailTemplateDefecto mtd : mailTemplatesDefecto) {
			if (!mtMap.containsKey(mtd.getCodigo())) {

				MailsTemplate mt = new MailsTemplate(mtd.getCodigo(), mtd.getAsunto(), mtd.getMensaje(), org);
				guardar(mt);

				LOGGER.log(Level.INFO, "Se agrega el mail template {0} para el org {1}", new Object[]{mt.getMailTmpCod(), org.getOrgPk()});
			}
		}
	}

	public MailsTemplate obtenerMailPorPk(Integer mailPk) {
		MailsTemplateDAO dao = new MailsTemplateDAO(em);

		try {
			return dao.findById(MailsTemplate.class, mailPk);
		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_OBTENER);
			throw be;
		}
	}

	public List<MailsTemplate> busquedaMailFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) throws BusinessException {
		List<CriteriaTO> criterios = new ArrayList<>();

		MatchCriteriaTO criterioOrg;
		if (orgPk != null) {
			criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
					MatchCriteriaTO.types.EQUALS, "mailTmpOrgFk.orgPk", orgPk);
		} else {
			criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
					MatchCriteriaTO.types.NULL, "mailTmpOrgFk.orgPk", 1);
		}
		criterios.add(criterioOrg);

		String cod = mapFiltro != null ? (String) mapFiltro.get("cod") : null;
		if (!StringsUtils.isEmpty(cod)) {
			MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
					MatchCriteriaTO.types.CONTAINS, "mailTmpCod", cod);
			criterios.add(criterio);
		}

		String asunto = mapFiltro != null ? (String) mapFiltro.get("asunto") : null;
		if (!StringsUtils.isEmpty(asunto)) {
			CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("mailTmpAsunto", asunto);
			criterios.add(criterio);
		}

		String mensaje = mapFiltro != null ? (String) mapFiltro.get("mensaje") : null;
		if (!StringsUtils.isEmpty(mensaje)) {
			CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("mailTmpMensaje", mensaje);
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

		MailsTemplateDAO dao = new MailsTemplateDAO(em);

		try {
			return dao.findEntityByCriteria(MailsTemplate.class, condicion, orderBy, asc, null, null);
		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_OBTENER);
			throw be;
		}
	}

	public void eliminarMail(Integer mailPk) {
		if (mailPk != null) {
			MailsTemplateDAO dao = new MailsTemplateDAO(em);
			try {
				MailsTemplate mail = obtenerMailPorPk(mailPk);
				dao.delete(mail);
			} catch (DAOGeneralException ex) {
				LOGGER.log(Level.SEVERE, null, ex);

				BusinessException be = new BusinessException();
				be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_ELIMINAR);
				if (ex.getCause() instanceof javax.persistence.PersistenceException
						&& ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_CONST_VIOLATION);
				}
				throw be;
			}
		}
	}

	private void validarDuplicado(MailsTemplate mail) throws BusinessException {
		if (mail != null) {
			Map<String, Object> filtroMap = new HashMap<>();
			filtroMap.put("cod", mail.getMailTmpCod());
			Integer orgPk = mail.getMailTmpOrgFk() != null ? mail.getMailTmpOrgFk().getOrgPk() : null;
			List<MailsTemplate> list = busquedaMailFiltro(orgPk, filtroMap, null, 0);

			if (CollectionsUtils.isNotEmpty(list)) {
				for (MailsTemplate m : list) {
					if (!m.getMailTmpPk().equals(mail.getMailTmpPk())
							&& m.getMailTmpCod().equals(mail.getMailTmpCod())) {
						BusinessException be = new BusinessException();
						be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_COD_DUPLICADO);
						throw be;
					}
				}
			}
		}
	}
}
