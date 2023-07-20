package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.ConfiguracionValidacion;
import com.sofis.data.daos.ConfiguracionDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.ConfiguracionDefecto;
import com.sofis.entities.data.Organismos;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
@Stateless(name = "ConfiguracionBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class ConfiguracionBean {

	private static final Logger LOGGER = Logger.getLogger(ConfiguracionBean.class.getName());

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	@Inject
	private DatosUsuario du;

	@Inject
	private OrganismoBean organismoBean;

	@Inject
	private ConfiguracionDefectoBean configuracionDefectoBean;

	/**
	 * Este método guarda un elemento de tipo Configuracion. Se aplica para la
	 * creación de la entidad y para la modificación de una entidad existente.
	 *
	 * @param cnf
	 * @return Configuracion
	 * @throws GeneralException Devuelve los códigos de error de la validación
	 */
	public Configuracion guardar(Configuracion cnf) throws GeneralException {

		try {
			//Validar el elemento a guardar. Si no valida, se lanza una excepcion
			if (ConfiguracionValidacion.validar(cnf)) {
				ConfiguracionDAO cnfDao = new ConfiguracionDAO(em);
				if (cnf.getId() == null) {
					Integer orgPk = cnf.getCnfOrgFk() != null ? cnf.getCnfOrgFk().getOrgPk() : null;
					Configuracion cnfPersit = obtenerCnfPorCodigoYOrg(cnf.getCnfCodigo(), orgPk);
					if (cnfPersit != null) {
						BusinessException be = new BusinessException();
						be.addError(MensajesNegocio.ERROR_CONFIG_COD_DUPLICADO);
						throw be;
					}
					cnf = cnfDao.create(cnf, du.getCodigoUsuario(), du.getOrigen());

				} else {
					cnf = cnfDao.update(cnf, du.getCodigoUsuario(), du.getOrigen());
				}
			}
			return cnf;
		} catch (BusinessException be) {
			//Si es de tipo negocio envía la misma excepción
			LOGGER.log(Level.SEVERE, be.getMessage(), be);
			throw be;
		} catch (Exception ex) {
			//Las demás excepciones se consideran técnicas
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			TechnicalException ge = new TechnicalException(ex);
			ge.addError(ex.getMessage());
			throw ge;
		}

	}

	/**
	 * Devuelve el elemento configuracion por el ID
	 *
	 * @param id
	 * @return
	 * @throws GeneralException
	 */
	public Configuracion obtenerCnfPorId(Integer id) throws GeneralException {
		ConfiguracionDAO cnfDao = new ConfiguracionDAO(em);
		try {
			return cnfDao.findById(Configuracion.class, id);
		} catch (DAOGeneralException ex) {
			TechnicalException te = new TechnicalException(ex);
			te.addError(ex.getMessage());
			throw te;
		}
	}

	public Map<String, Configuracion> obtenerCnfPorCodigoYOrg(Integer orgPk, String... codigo) throws GeneralException {
		if (orgPk != null && codigo != null && codigo.length > 0) {
			ConfiguracionDAO dao = new ConfiguracionDAO(em);
			return dao.obtenerCnfPorCodigoYOrg(orgPk, codigo);
		}
		return null;
	}

	public Map<String, Configuracion> obtenerCnfPorCodigoYOrg(Integer orgPk, Map<String, Configuracion> confs, String... codigo) throws GeneralException {
		if (orgPk != null && codigo != null && codigo.length > 0) {
			if (confs == null) {
				confs = new HashMap<>();
			}
			ArrayList<String> codigosA = new ArrayList<>();
			for (String cod : codigo) {
				if (!confs.containsKey(cod)) {
					codigosA.add(cod);
				}
			}

			if (!codigosA.isEmpty()) {
				confs.putAll(this.obtenerCnfPorCodigoYOrg(orgPk,
						Arrays.copyOf(codigosA.toArray(), codigosA.size(), String[].class)));
			}

			return confs;
		}
		return null;
	}

	public String obtenerCnfValorPorCodigo(String codigo, Integer orgPk) {
		Configuracion conf = obtenerCnfPorCodigoYOrg(codigo, orgPk);
		return conf != null ? conf.getCnfValor() : "";
	}

	public Configuracion obtenerCnfPorCodigoYOrg(String codigo, Integer orgPk) throws GeneralException {
       //     LOGGER.info("BUSCANDO CONFIG "+ codigo);
		if (!StringsUtils.isEmpty(codigo)) {
			ConfiguracionDAO dao = new ConfiguracionDAO(em);
			try {
				CriteriaTO criteria;
				CriteriaTO criteriaCod = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "cnfCodigo", codigo);
				if (orgPk != null) {
					CriteriaTO criteriaOrg = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "cnfOrgFk.orgPk", orgPk);
					criteria = CriteriaTOUtils.createANDTO(criteriaOrg, criteriaCod);
				} else {
					CriteriaTO criteriaOrg = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NULL, "cnfOrgFk", 1);
					criteria = CriteriaTOUtils.createANDTO(criteriaOrg, criteriaCod);
				}

				List<Configuracion> resultado = dao.findEntityByCriteria(Configuracion.class, criteria, new String[]{}, new boolean[]{}, null, null);
				return (Configuracion) DAOUtils.obtenerSingleResult(resultado);
			} catch (DAOGeneralException ex) {
				LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
				TechnicalException te = new TechnicalException(ex);
				te.addError(ex.getMessage());
				throw te;
			}
		}
		return null;
	}

	/**
	 * Devuelve todos los elementos de tipo configuracion
	 *
	 * @return
	 * @throws GeneralException
	 */
	@Deprecated
	public List<Configuracion> obtenerTodos() throws GeneralException {
		ConfiguracionDAO cnfDao = new ConfiguracionDAO(em);
		try {
			return cnfDao.findAll(Configuracion.class);
		} catch (Exception ex) {
			TechnicalException te = new TechnicalException(ex);
			te.addError(ex.getMessage());
			throw te;
		}
	}

	/**
	 * Devuelve los elementos que satisfacen el criterio ingresado
	 *
	 * @param cto
	 * @param orderBy
	 * @param ascending
	 * @param startPosition
	 * @param cantidad
	 * @return
	 * @throws GeneralException
	 */
	public List<Configuracion> obtenerPorCriteria(CriteriaTO cto, String[] orderBy, boolean[] ascending, Long startPosition, Long cantidad) throws GeneralException {
		ConfiguracionDAO cnfDao = new ConfiguracionDAO(em);
		try {
			return cnfDao.findEntityByCriteria(Configuracion.class, cto, orderBy, ascending, startPosition, cantidad);
		} catch (Exception ex) {
			TechnicalException te = new TechnicalException(ex);
			te.addError(ex.getMessage());
			throw te;
		}
	}

	public List<Configuracion> obtenerTodosPorOrg(Integer orgPk) {
		ConfiguracionDAO cnfDao = new ConfiguracionDAO(em);
		try {
			if (orgPk != null) {
				return cnfDao.findByOneProperty(Configuracion.class, "cnfOrgFk.orgPk", orgPk);
			} else {
				return cnfDao.obtenerConfSinOrg();
			}
		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_CONFIG_OBTENER);
			throw be;
		}
	}

	public void controlarCnfFaltantes() {

		controlarConfiguracionGeneral();

		List<ConfiguracionDefecto> valoresDefecto = configuracionDefectoBean.obtenerTodas();

		List<Organismos> organismos = organismoBean.obtenerTodos();
		if (organismos != null) {
			for (Organismos org : organismos) {
				controlarConfiguracionOrganismo(org, valoresDefecto);
			}
		}
	}

	public void controlarConfiguracionOrganismo(Organismos org) {
	
		List<ConfiguracionDefecto> valoresDefecto = configuracionDefectoBean.obtenerTodas();
		controlarConfiguracionOrganismo(org, valoresDefecto);
	}
	
	private void controlarConfiguracionOrganismo(Organismos org, List<ConfiguracionDefecto> valoresDefecto) {

		List<Configuracion> confList = obtenerTodosPorOrg(org.getOrgPk());
		Map<String, Configuracion> configuracionesOrganismo = new HashMap<>();

		if (confList != null) {
			for (Configuracion conf : confList) {
				configuracionesOrganismo.put(conf.getCnfCodigo(), conf);
			}
		}

		for (ConfiguracionDefecto conf : valoresDefecto) {
			if (!configuracionesOrganismo.containsKey(conf.getCodigo())) {

				Configuracion configuracion = new Configuracion(conf.getCodigo(), conf.getDescripcion(), conf.getValor(), org, new Date(), 1);

				guardar(configuracion);

				LOGGER.log(Level.INFO, "Se agregó la configuración {0} para el org {1}", new Object[]{configuracion.getCnfCodigo(), org.getOrgPk()});
			}
		}

		if (!configuracionesOrganismo.containsKey(ConfiguracionCodigos.FOLDER_MEDIA)) {
			Configuracion conf = obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.FOLDER_MEDIA, null);

			//valor en base al organismo
			String valor = conf.getCnfValor() + org.getOrgPk();
			Configuracion configuracion = new Configuracion(conf.getCnfCodigo(), conf.getCnfDescripcion(), valor, org, new Date(), 1);

			guardar(configuracion);

			LOGGER.log(Level.INFO, "Se agregó la configuración {0} para el org {1}", new Object[]{configuracion.getCnfCodigo(), org.getOrgPk()});
		}

	}

	private void controlarConfiguracionGeneral() throws GeneralException {

		Configuracion[] confArrGeneral = new Configuracion[]{
			new Configuracion(ConfiguracionCodigos.AUTH_LDAP_ENABLE, "true/false: habilitar configuración de período de proyecto en entregables", "false", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.AUTH_LDAP_SEARCH_NAME, "true/false: habilitar configuración de período de proyecto en entregables", "false", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.AUTH_LDAP_SEARCH_FILTER, "true/false: habilitar configuración de período de proyecto en entregables", "false", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.AUTH_LDAP_SEARCH_INITIAL_CTX_FACTORY, "Clase java que construye el initial context", "com.sun.jndi.ldap.LdapCtxFactory", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.AUTH_LDAP_SEARCH_URL, "true/false: habilitar configuración de período de proyecto en entregables", "false", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.AUTH_LDAP_SECURITY_PRINCIPAL, "true/false: habilitar configuración de período de proyecto en entregables", "false", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.AUTH_LDAP_SECURITY_CREDENTIAL, "true/false: habilitar configuración de período de proyecto en entregables", "false", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.AUTH_LDAP_SEARCH_USER_LOGIN_FILTER, "true/false: habilitar configuración de período de proyecto en entregables", "false", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.DOCUMENTOS_DIR, "Directorio donde se almacenarán los documentos", "/srv/siges/docs", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.NOMBRE_ARCHIVO_MANUAL, "Nombre del archivo correspondiente al manual de usuario", "MANUAL", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_MANUAL_USUARIO, "Tamaño máximo del archivo de manual de usuario en bytes", "10485760", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.MEDIA_MIMETYPE_REGEX, "Regex que valida el mimetype de los media que se suben", "image\\/(jpeg|png)", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.DEBUG, "Modo depuración", "false", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.DEBUG_DESTINO, "Email destino en modo depuración", "prueba@prueba.com", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.INCLUIR_CALCULAR_FINALIZADOS, "Incluir lor pryectos finalizdos en el cáclulo de indicadores", "false", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.URL_SISTEMA, "URL del Sistema", "https://siges.agesic.gub.uy", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.HABILITAR_MOVER_PROYECTO, "true/false: habilitar la posibilidad de mover proyectos", "false", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.MATOMO_URL, "URL del servidor Matomo", "//localhost/matomo/", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.MATOMO_ID_SITIO, "Identificador de la aplicación SIGES en Matomo", "1", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.MATOMO_ID_DIMENSION_USUARIO, "Identificador de la dimensión 'Usuario' en Matomo", "1", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.MATOMO_ID_DIMENSION_ORGANISMO, "Identificador de la dimensión 'Organismo' en Matomo", "2", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.FOLDER_MEDIA, "Carpeta donde se almacenan los archivos Multimedia", "/srv/siges/media_files/", null, new Date(), 1),
			new Configuracion(ConfiguracionCodigos.WEKAN_SIGES_WEBHOOK, "URL utilizada por la integración en Wekan (debe ser bidireccional)", "rest/wekan/bidirectional-webhook", null, new Date(), 1),
			new Configuracion("MOSTRAR_BOTON_AVANCE_PADRES", "Mostrar botón 'avance padres'", "false", null, new Date(), 1)
		};

		List<Configuracion> confListGeneral = obtenerTodosPorOrg(null);
		Map<String, Configuracion> confMapPGE = new HashMap<>();
		if (confListGeneral != null) {
			for (Configuracion conf : confListGeneral) {
				confMapPGE.put(conf.getCnfCodigo(), conf);
			}
			for (Configuracion conf : confArrGeneral) {
				if (!confMapPGE.containsKey(conf.getCnfCodigo())) {
					guardar(conf);
					LOGGER.log(Level.INFO, "Se agregó la configuración general {0}", conf.getCnfCodigo());
				}
			}
		}
	}

}
