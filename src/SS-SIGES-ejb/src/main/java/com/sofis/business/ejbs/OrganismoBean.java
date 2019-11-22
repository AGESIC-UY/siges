package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.OrganismosValidacion;
import com.sofis.data.daos.OrganismoDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Ambito;
import com.sofis.entities.data.AreaConocimiento;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.RolesInteresados;
import com.sofis.entities.data.TipoDocumento;
import com.sofis.entities.tipos.IdNombreTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "OrganismoBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class OrganismoBean {

	private static final String HORA_EJECUCION = "08";
	private static final String MINUTOS_EJECUCION = "00";

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;
	@Inject
	private ConsultaHistorico<Programas> ch;
	@Inject
	private DatosUsuario du;
	@EJB
	private MailsTemplateBean mailsTemplateBean;
	@EJB
	private ConfiguracionBean configuracionBean;
	@EJB
	private NotificacionBean notificacionBean;
	@EJB
	private AmbitoBean ambitoBean;
	@EJB
	private AreasConocimientoBean areasConocimientoBean;
	@EJB
	private AreaTematicaBean areaTematicaBean;
	@EJB
	private FuenteFinanciamientoBean fuenteFinanciamientoBean;
	@EJB
	private OrganiIntProveBean organiIntProveBean;
	@EJB
	private RolesInteresadosBean rolesInteresadosBean;
	@EJB
	private TipoDocumentoBean tipoDocumentoBean;
	@EJB
	private SsRolBean ssRolBean;
	@EJB
	private AreasBean areasBean;
	@EJB
	private EstadosPublicacionBean estadosPublicacionBean;
	@EJB
	private EtapaBean etapaBean;
	@EJB
	private TiposMediaBean tiposMediaBean;
	@EJB
	private EstadosBean estadosBean;
	@EJB
	private TipoLeccionBean tipoLeccionBean;

        private static final Logger logger = Logger.getLogger(OrganismoBean.class.getName());

	//private String usuario;
	//private String origen;
	@PostConstruct
	public void init() {
		//usuario = du.getCodigoUsuario();
		//origen = du.getOrigen();
	}

	public Organismos obtenerOrgPorId(Integer id, boolean conLogo) throws GeneralException {
		if (id != null) {
			OrganismoDAO orgDao = new OrganismoDAO(em);
			try {
				Organismos org = orgDao.findById(Organismos.class, id);
				if (org != null && conLogo) {
					org.setOrgLogo(obtenerLogoOrg(org.getOrgPk()));
				}
				return org;
			} catch (DAOGeneralException ex) {
				logger.log(Level.SEVERE, ex.getMessage(), ex);
				TechnicalException te = new TechnicalException(ex);
				te.addError(ex.getMessage());
				throw te;
			}
		}
		return null;
	}

	/**
	 * @see OrganismoDAO#obtenerOrgIdNombre(java.lang.Integer)
	 * @param usuId
	 * @return Lista de IdNombreTO con el ID y nombre de los organismos.
	 */
	public List<IdNombreTO> obtenerOrgIdNombre(Integer usuId) {
		if (usuId != null) {
			OrganismoDAO dao = new OrganismoDAO(em);
			return dao.obtenerOrgIdNombre(usuId);
		}
		return null;
	}

	private Organismos guardar(Organismos org) {
		OrganismosValidacion.validar(org);
		validarDuplicado(org);
		OrganismoDAO dao = new OrganismoDAO(em);
		boolean esNuevo = (org.getOrgPk() == null);
		byte[] logo = org.getOrgLogo();
		try {
			org = dao.update(org, du.getCodigoUsuario(), du.getOrigen());
			if (logo != null) {
				guardarLogoOrg(org.getOrgPk(), logo);
			}

			if (esNuevo) {
				this.controlarDatosFaltantes();
			}
		} catch (DAOGeneralException ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return org;
	}

	public Organismos guardarOrgnanismo(Organismos org) {
		return guardar(org);
	}

	public boolean existenOrganismos() {
		OrganismoDAO dao = new OrganismoDAO(em);
		List<Organismos> list = null;
		try {
			list = dao.findAll(Organismos.class);
		} catch (DAOGeneralException ex) {
			Logger.getLogger(OrganismoBean.class.getName()).log(Level.SEVERE, null, ex);
		}

		return list != null && !list.isEmpty();
	}

	public List<Organismos> obtenerTodos() {
		OrganismoDAO dao = new OrganismoDAO(em);
		List<Organismos> result = null;
		try {
//            result = dao.findAll(Organismos.class, "orgNombre");
			result = dao.obtenerTodos();
		} catch (DAOGeneralException ex) {
			Logger.getLogger(OrganismoBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	public List<Organismos> obtenerTodosActivos() {
		OrganismoDAO dao = new OrganismoDAO(em);
		try {
//            return dao.findByOneProperty(Organismos.class, "orgActivo", Boolean.TRUE, "orgNombre");
			return dao.obtenerTodosActivos();
		} catch (DAOGeneralException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public byte[] obtenerLogoOrg(Integer orgPk) {
		OrganismoDAO dao = new OrganismoDAO(em);
		return dao.obtenerLogoOrg(orgPk);
	}

	public void guardarLogoOrg(Integer orgPk, byte[] logo) {
		OrganismoDAO dao = new OrganismoDAO(em);
		dao.guardarLogoOrg(orgPk, logo);
	}

	public void controlarDatosFaltantes() {
		logger.log(Level.INFO, "CONTROLAR MAIL TMP FALTANTES");
		//mails_template
		mailsTemplateBean.controlarMailTmpFaltantes();

		logger.log(Level.INFO, "CONTROLAR CONFIGURACIONES FALTANTES");
		//ss_configuraciones
		configuracionBean.controlarCnfFaltantes();

		logger.log(Level.INFO, "CONTROLAR NOTIFICACIONES FALTANTES");
		//notificacion
		notificacionBean.controlarNotifFaltantes();

		logger.log(Level.INFO, "CONTROLAR ROLES FALTANTES");
		//Roles
		ssRolBean.controlarRolesFaltantes();

		logger.log(Level.INFO, "CONTROLAR ESTADOS PUBLICACION FALTANTES");
		//Estados Publicacion
		estadosPublicacionBean.controlarEstPubFaltantes();

		logger.log(Level.INFO, "CONTROLAR ETAPAS FALTANTES");
		//Etapas
		etapaBean.controlarEtapasFaltantes();

		logger.log(Level.INFO, "CONTROLAR TIPOS MULTIMEDIA FALTANTES");
		//Tipos Multimedia
		tiposMediaBean.controlarTiposMediaFaltantes();

		logger.log(Level.INFO, "CONTROLAR ESTADOS FALTANTES");
		//Estados
		estadosBean.controlarEstadosFaltantes();

		logger.log(Level.INFO, "CONTROLAR TIPO LECCION FALTANTES");
		//Tipo Leccion(tipo_leccion)
		tipoLeccionBean.controlarTipoLeccionFaltantes();
	}

	/**
	 * Guardar un organismo realizado una copia de determinados parametros como:
	 * Ambito, Áreas Temáticas, Fuentes de financiamineto, etc.
	 *
	 * @param org
	 * @param mapCopiar
	 * @return
	 */
	public Organismos guardarOrgnanismo(Organismos org, Map<String, Object> mapCopiar) {
		org = guardar(org);

		if (org != null && mapCopiar != null) {
			Organismos orgCopiar = (Organismos) mapCopiar.get("organismo");
			if (orgCopiar != null) {
				boolean ambito = (boolean) mapCopiar.get("copiarAmbito");
				if (ambito) {
					List<Ambito> listAmbito = ambitoBean.obtenerAmbitoPorOrg(orgCopiar.getOrgPk());
					for (Ambito amb : listAmbito) {
						Ambito newAmbito = new Ambito();
						newAmbito.setAmbNombre(amb.getAmbNombre());
						newAmbito.setAmbOrgFk(org);
						ambitoBean.guardarAmbito(newAmbito);
					}
				}

				Boolean areasCon = (Boolean) mapCopiar.get("copiarAreasConocimiento");
				if (areasCon != null && areasCon) {
					List<AreaConocimiento> listAreaCon = areasConocimientoBean.obtenerAreaConPorOrg(orgCopiar.getOrgPk());
					List<Object[]> listPadres = new ArrayList<>();
					for (AreaConocimiento area : listAreaCon) {
						AreaConocimiento newArea = new AreaConocimiento();
						newArea.setConNombre(area.getConNombre());
						newArea.setAreaConPadreFk(area.getAreaConPadreFk());
						newArea.setConOrganismo(org);
						newArea = areasConocimientoBean.guardar(newArea);
						if (area.getAreaConPadreFk() != null) {
							listPadres.add(new Object[]{newArea.getConPk(), area.getAreaConPadreFk().getConNombre()});
						}
					}
					for (Object[] arr : listPadres) {
						AreaConocimiento area = areasConocimientoBean.obtenerAreasPorPk((Integer) arr[0]);
						List<AreaConocimiento> listAreaPadre = areasConocimientoBean.obtenerAreasPorNombre((String) arr[1], area.getConOrganismo().getOrgPk());
						if (CollectionsUtils.isNotEmpty(listAreaPadre)) {
							area.setAreaConPadreFk(listAreaPadre.get(0));
							areasConocimientoBean.guardar(area);
						}
					}
				}

				Boolean areasTem = (Boolean) mapCopiar.get("copiarAreasTematicas");
				if (areasTem != null && areasTem) {
					List<AreasTags> listATem = areaTematicaBean.obtenerAreasTematicasPorOrg(orgCopiar.getOrgPk());
					List<Object[]> listPadres = new ArrayList<>();
					for (AreasTags area : listATem) {
						AreasTags newArea = new AreasTags();
						newArea.setAreatagCategoria(area.getAreatagCategoria());
						newArea.setAreatagExcluyente(area.getAreatagExcluyente());
						newArea.setAreatagNombre(area.getAreatagNombre());
						newArea.setAreatagTematica(area.getAreatagTematica());
						newArea.setAreatagOrgFk(org);
						newArea = areaTematicaBean.guardar(newArea);
						if (area.getAreatagPadreFk() != null) {
							listPadres.add(new Object[]{newArea.getArastagPk(), area.getAreatagPadreFk().getAreatagNombre()});
						}
					}
					for (Object[] arr : listPadres) {
						AreasTags area = areaTematicaBean.obtenerAreaTemPorPk((Integer) arr[0]);
						List<AreasTags> listAreaPadre = areaTematicaBean.obtenerAreasPorNombre((String) arr[1], area.getAreatagOrgFk().getOrgPk());
						if (CollectionsUtils.isNotEmpty(listAreaPadre)) {
							area.setAreatagPadreFk(listAreaPadre.get(0));
							areaTematicaBean.guardar(area);
						}
					}
				}

				Boolean fuenteFin = (Boolean) mapCopiar.get("copiarFuenteFinanciamiento");
				if (fuenteFin != null && fuenteFin) {
					List<FuenteFinanciamiento> listFuentes = fuenteFinanciamientoBean.obtenerFuentesPorOrgId(orgCopiar.getOrgPk());
					for (FuenteFinanciamiento fuente : listFuentes) {
						FuenteFinanciamiento newFuente = new FuenteFinanciamiento();
						newFuente.setFueNombre(fuente.getFueNombre());
						newFuente.setFueOrgFk(org);
						fuenteFinanciamientoBean.guardarFuente(newFuente);
					}
				}

				Boolean orga = (Boolean) mapCopiar.get("copiarOrganizaciones");
				if (orga != null && orga) {
					List<OrganiIntProve> listAmbito = organiIntProveBean.obtenerOrganiIntProvePorOrgPk(orgCopiar.getOrgPk(), null);
					for (OrganiIntProve organi : listAmbito) {
						OrganiIntProve newOrgani = new OrganiIntProve();
						newOrgani.setOrgaAmbito(organi.getOrgaAmbito());
						newOrgani.setOrgaAmbFk(organi.getOrgaAmbFk());
						newOrgani.setOrgaDireccion(organi.getOrgaDireccion());
						newOrgani.setOrgaMail(organi.getOrgaMail());
						newOrgani.setOrgaNombre(organi.getOrgaNombre());
						newOrgani.setOrgaProveedor(organi.getOrgaProveedor());
						newOrgani.setOrgaRazonSocial(organi.getOrgaRazonSocial());
						newOrgani.setOrgaRut(organi.getOrgaRut());
						newOrgani.setOrgaTelefono(organi.getOrgaTelefono());
						newOrgani.setOrgaWeb(organi.getOrgaWeb());
						newOrgani.setOrgaOrgFk(org);
						organiIntProveBean.guardar(newOrgani);
					}
				}

				Boolean rolInt = (Boolean) mapCopiar.get("copiarRolInteresados");
				if (rolInt != null && rolInt) {
					List<RolesInteresados> listRolInt = rolesInteresadosBean.obtenerRolPorOrgPk(orgCopiar.getOrgPk());
					for (RolesInteresados rolesInt : listRolInt) {
						RolesInteresados newRolInt = new RolesInteresados();
						newRolInt.setRolintNombre(rolesInt.getRolintNombre());
						newRolInt.setRolintOrgFk(org);
						rolesInteresadosBean.guardarRol(newRolInt);
					}
				}

				Boolean tiposDoc = (Boolean) mapCopiar.get("copiarTiposDocumentos");
				if (tiposDoc != null && tiposDoc) {
					List<TipoDocumento> listTipoDoc = tipoDocumentoBean.obtenerTipoDocPorOrgPk(orgCopiar.getOrgPk());
					for (TipoDocumento tipoDoc : listTipoDoc) {
						TipoDocumento newAmbito = new TipoDocumento();
						newAmbito.setTipodocNombre(tipoDoc.getTipodocNombre());
						newAmbito.setTipodocExigidoDesde(tipoDoc.getTipodocExigidoDesde());
						newAmbito.setTipodocPeso(tipoDoc.getTipodocPeso());
						newAmbito.setTipodocResumenEjecutivo(tipoDoc.getTipodocResumenEjecutivo());
						newAmbito.setTipoDocOrgFk(org);
						tipoDocumentoBean.guardar(newAmbito);
					}
				}
			}
		}

		if (org != null && org.getOrgPk() != null) {
			List<Areas> areas = areasBean.obtenerAreasPorIdOrganismo(org.getOrgPk(), false);
			if (CollectionsUtils.isEmpty(areas)) {
				Areas area = new Areas();
				area.setAreaOrgFk(org);
				area.setAreaNombre(org.getOrgNombre());
				areasBean.guardar(area);
			}
		}

		return org;
	}

	private List<Organismos> obtenerPorNombre(String nombre) {
		if (!StringsUtils.isEmpty(nombre)) {
			OrganismoDAO dao = new OrganismoDAO(em);
			try {
				return dao.findByOneProperty(Organismos.class, "orgNombre", nombre);
			} catch (DAOGeneralException ex) {
				logger.log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

	private void validarDuplicado(Organismos org) {
		logger.log(Level.INFO, "*** validarDuplicado ***");
		List<Organismos> list = obtenerPorNombre(org.getOrgNombre());
		logger.log(Level.INFO, "org nombre:" + org.getOrgNombre());
		logger.log(Level.INFO, "list:" + (list != null ? list.size() : "null"));
		if (CollectionsUtils.isNotEmpty(list)) {
			for (Organismos o : list) {
				logger.log(Level.INFO, "for o:" + o.getOrgPk() + "-" + o.getOrgNombre());
				logger.log(Level.INFO, "for org:" + org.getOrgPk() + "-" + org.getOrgNombre());
				if (!o.getOrgPk().equals(org.getOrgPk())) {
					BusinessException be = new BusinessException();
					be.addError(MensajesNegocio.ERROR_ORGANISMO_NOMBRE_DUPLICADO);
					throw be;
				}
			}
		}
	}

	public Organismos obtenerOrgPorToken(String token) throws GeneralException {

		try {
			OrganismoDAO organismoDAO = new OrganismoDAO(em);
			return organismoDAO.obtenerPorToken(token);
		} catch (DAOGeneralException ex) {
			throw new TechnicalException(ex);
		}

	}

}
