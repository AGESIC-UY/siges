package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.MailTemplateDefectoValidacion;
import com.sofis.data.daos.MailTemplateDefectoDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.MailTemplateDefecto;
import com.sofis.entities.tipos.FiltroMailTemplateDefectoTO;
import com.sofis.exceptions.BusinessException;
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
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless(name = "MailTemplateDefectoBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class MailTemplateDefectoBean {

	private static final Logger LOGGER = Logger.getLogger(MailTemplateDefectoBean.class.getName());

	public static String MAIL_SOL_APROBACION = "MAIL_SOL_APROBACION";
	public static String MAIL_CAMBIO_ESTADO = "MAIL_CAMBIO_ESTADO";
	public static String MAIL_CAMBIO_CONTRASENIA = "MAIL_CAMBIO_CONTRASENIA";
	public static String MAIL_PROG_PROY_PENDIENTE = "MAIL_PROG_PROY_PENDIENTE";
	public static String MAIL_NVO_USUARIO = "MAIL_NVO_USUARIO";

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	public MailTemplateDefecto guardar(MailTemplateDefecto mt) {
		if (mt != null) {
			MailTemplateDefectoValidacion.validar(mt);
			validarDuplicado(mt);

			MailTemplateDefectoDAO dao = new MailTemplateDefectoDAO(em);
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

	public List<MailTemplateDefecto> obtenerTodos() {

		MailTemplateDefectoDAO dao = new MailTemplateDefectoDAO(em);
		try {

			return dao.findAll();

		} catch (DAOGeneralException ex) {

			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			BusinessException be = new BusinessException();
			be.addError(ex.getMessage());

			throw be;
		}
	}

	public void controlarFaltantes() {
		List<MailTemplateDefecto> mailTemplatesDefectoExistentes = obtenerTodos();

		Map<String, MailTemplateDefecto> mailTemplateDefectoMap = new HashMap<>();

		for (MailTemplateDefecto mt : mailTemplatesDefectoExistentes) {
			mailTemplateDefectoMap.put(mt.getCodigo(), mt);
		}

		MailTemplateDefecto[] mailTemplatesDefectoAplicacion = new MailTemplateDefecto[]{
			new MailTemplateDefecto("MAIL_SOL_APROBACION", "Solicitud de Aprobación", "<h2>Solicitud de Aprobación</h2><p>Se generó una solicitud de cambio de estado para el #TIPO_PROG_PROY# \"#NOMBRE_PROG_PROY#\".</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>"),
			new MailTemplateDefecto("MAIL_CAMBIO_CONTRASENIA", "Cambio de contraseña en SIGES", "Estimado #NOMBRE#, se ha cambiado su contraseña en SIGES por #CONTRASENIA#"),
			new MailTemplateDefecto("MAIL_CAMBIO_ESTADO", "Programa / Proyecto cambió de fase.", "<h2>Cambio de Fase</h2><p>El #TIPO_PROG_PROY# #ID_PROG_PROY# \"#NOMBRE_PROG_PROY#\" cambió de fase a #FASE_PROG_PROY#.</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>"),
			new MailTemplateDefecto("MAIL_PROG_PROY_PENDIENTE", "Pendiente de aprobación.", "<h2>Pendiente de aprobación</h2><p>El #TIPO_PROG_PROY# #ID_PROG_PROY# \"#NOMBRE_PROG_PROY#\" esta pendiente de aprobación.</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>"),
			new MailTemplateDefecto("MAIL_NVO_USUARIO", "Usuario SIGES.", "<h2>Usuario creado</h2><p>Se ha creado el usuario #USU_MAIL# cuya clave es #USU_PASSWORD#, para ingresar al sistema de SIGES.</p><p>#ORGANISMO_NOMBRE#<br />#ORGANISMO_DIRECCION#</p>")
		};

		for (MailTemplateDefecto mtd : mailTemplatesDefectoAplicacion) {
			if (!mailTemplateDefectoMap.containsKey(mtd.getCodigo())) {

				MailTemplateDefecto mailTemplateDefecto = new MailTemplateDefecto(mtd.getCodigo(), mtd.getAsunto(), mtd.getMensaje());
				guardar(mailTemplateDefecto);

				LOGGER.log(Level.INFO, "Se agrega el mail template por defecto {0}", mailTemplateDefecto.getCodigo());
			}
		}
	}

	public MailTemplateDefecto obtenerPorId(Integer id) {
		MailTemplateDefectoDAO dao = new MailTemplateDefectoDAO(em);

		try {
			return dao.findById(MailTemplateDefecto.class, id);
		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_OBTENER);
			throw be;
		}
	}

	public List<MailTemplateDefecto> buscar(FiltroMailTemplateDefectoTO filtro, String elementoOrdenacion, int ascendente) throws BusinessException {

		List<CriteriaTO> criterios = new ArrayList<>();

		if (filtro.getId() != null) {
			MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "id", filtro.getId());
			criterios.add(criterio);
		}
		if (!StringsUtils.isEmpty(filtro.getCodigo())) {
			MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "codigo", filtro.getCodigo());
			criterios.add(criterio);
		}

		if (!StringsUtils.isEmpty(filtro.getAsunto())) {
			CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("asunto", filtro.getAsunto());
			criterios.add(criterio);
		}

		if (!StringsUtils.isEmpty(filtro.getMensaje())) {
			CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("mensaje", filtro.getMensaje());
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

		MailTemplateDefectoDAO dao = new MailTemplateDefectoDAO(em);

		try {
			return dao.findEntityByCriteria(MailTemplateDefecto.class, condicion, orderBy, asc, null, null);
			
		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_OBTENER);
			throw be;
		}
	}

	public void eliminar(Integer mailPk) {
		if (mailPk != null) {
			MailTemplateDefectoDAO dao = new MailTemplateDefectoDAO(em);
			try {
				MailTemplateDefecto mail = obtenerPorId(mailPk);
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

	private void validarDuplicado(MailTemplateDefecto mail) throws BusinessException {

		List<CriteriaTO> criterios = new ArrayList<>();

		criterios.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "codigo", mail.getCodigo()));
		criterios.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NOT_EQUALS, "id", mail.getId()));

		CriteriaTO condicion = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));

		MailTemplateDefectoDAO dao = new MailTemplateDefectoDAO(em);

		try {
			Long count = dao.countByCriteria(MailTemplateDefecto.class, condicion, null, null);

			if (count > 0) {
				BusinessException be = new BusinessException();
				be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_COD_DUPLICADO);

				throw be;
			}
		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_MAIL_TEMPL_OBTENER);
			throw be;
		}
	}
}
