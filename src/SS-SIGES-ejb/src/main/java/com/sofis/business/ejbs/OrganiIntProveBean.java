package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.OrganiIntProveValidacion;
import com.sofis.data.daos.OrganiIntProveDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.sofisform.to.OR_TO;
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

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "OrganiIntProveBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class OrganiIntProveBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final String ORGA_NOMBRE = "orgaNombre";

    public OrganiIntProve guardar(OrganiIntProve orga) {
	OrganiIntProveValidacion.validar(orga);
	validarDuplicado(orga);

	OrganiIntProveDAO dao = new OrganiIntProveDAO(em);
	try {
	    return dao.update(orga);
	} catch (DAOGeneralException ex) {
	    logger.log(Level.SEVERE, null, ex);
	    BusinessException be = new BusinessException();
	    be.addError(MensajesNegocio.ERROR_ORGANIZACION_GUARDAR);
	    throw be;
	}
    }

    /**
     * Devuelve el elemento OrganiIntProve por el ID
     *
     * @param id
     * @return
     * @throws GeneralException
     */
    public OrganiIntProve obtenerOrganiIntProvePorId(Integer id) throws GeneralException {
	OrganiIntProveDAO organiIntProveDAO = new OrganiIntProveDAO(em);
	try {
	    return organiIntProveDAO.findById(OrganiIntProve.class, id);
	} catch (DAOGeneralException ex) {
	    TechnicalException te = new TechnicalException(ex);
	    te.addError(ex.getMessage());
	    throw te;
	}
    }

    public List<OrganiIntProve> obtenerOrganiIntProvePorOrgPk(Integer orgPk, Boolean proveedor) throws GeneralException {
	OrganiIntProveDAO organiIntProveDAO = new OrganiIntProveDAO(em);
	try {
	    CriteriaTO criteriaOrg = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "orgaOrgFk.orgPk", orgPk);

	    CriteriaTO criteriaProv;
	    CriteriaTO criteria;
	    if (proveedor != null) {
		if (proveedor) {
		    criteriaProv = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "orgaProveedor", proveedor);
		} else {
		    CriteriaTO criteriaProv1 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "orgaProveedor", proveedor);
		    CriteriaTO criteriaProv2 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NULL, "orgaProveedor", 1);
		    criteriaProv = CriteriaTOUtils.createORTO(criteriaProv1, criteriaProv2);
		}
		criteria = CriteriaTOUtils.createANDTO(criteriaOrg, criteriaProv);
	    } else {
		criteria = criteriaOrg;
	    }

	    String[] orderBy = {};
	    boolean[] ascending = {};

	    return organiIntProveDAO.findEntityByCriteria(OrganiIntProve.class, criteria, orderBy, ascending, null, null);
	} catch (DAOGeneralException ex) {
	    logger.log(Level.SEVERE, ex.getMessage(), ex);
	    BusinessException be = new BusinessException();
	    be.addError(MensajesNegocio.ERROR_ORGANIZACION_OBTENER);
	    throw be;
	}
    }

    public List<OrganiIntProve> obtenerOrgani(Integer orgPk) {
	OrganiIntProveDAO organiIntProveDAO = new OrganiIntProveDAO(em);
	try {
	    CriteriaTO criteriaOrga = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "orgaOrgFk.orgPk", orgPk);
	    CriteriaTO criteriaProvNull = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NULL, "orgaProveedor", 1);
	    CriteriaTO criteriaProv = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "orgaProveedor", Boolean.FALSE);
	    OR_TO orProv = new OR_TO(criteriaProvNull, criteriaProv);
	    CriteriaTO criteria = CriteriaTOUtils.createANDTO(criteriaOrga, orProv);

	    return organiIntProveDAO.findEntityByCriteria(OrganiIntProve.class, criteria, new String[]{"orgaNombre"}, new boolean[]{true}, null, null);
	} catch (Exception ex) {
	    TechnicalException te = new TechnicalException(ex);
	    te.addError(ex.getMessage());
	    throw te;
	}
    }

    public List<OrganiIntProve> obtenerProveedores(Integer orgPk) {
	OrganiIntProveDAO organiIntProveDAO = new OrganiIntProveDAO(em);
	try {
	    CriteriaTO criteriaOrga = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "orgaOrgFk.orgPk", orgPk);
	    CriteriaTO criteriaProv = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "orgaProveedor", Boolean.TRUE);
	    CriteriaTO criteria = CriteriaTOUtils.createANDTO(criteriaOrga, criteriaProv);

	    return organiIntProveDAO.findEntityByCriteria(OrganiIntProve.class, criteria, new String[]{"orgaNombre"}, new boolean[]{true}, null, null);
	} catch (Exception ex) {
	    TechnicalException te = new TechnicalException(ex);
	    te.addError(ex.getMessage());
	    throw te;
	}
    }

    public List<OrganiIntProve> obtenerInteresados(Integer orgPk) {
	OrganiIntProveDAO organiIntProveDAO = new OrganiIntProveDAO(em);
	try {
	    CriteriaTO criteriaOrga = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "orgaOrgFk.orgPk", orgPk);
	    CriteriaTO criteriaProvNull = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NULL, "orgaProveedor", 1);
	    CriteriaTO criteriaProvFalse = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "orgaProveedor", Boolean.FALSE);
	    OR_TO or_to = new OR_TO(criteriaProvNull, criteriaProvFalse);
	    CriteriaTO criteria = CriteriaTOUtils.createANDTO(criteriaOrga, or_to);

	    return organiIntProveDAO.findEntityByCriteria(OrganiIntProve.class, criteria, new String[]{"orgaNombre"}, new boolean[]{true}, null, null);
	} catch (Exception ex) {
	    TechnicalException te = new TechnicalException(ex);
	    te.addError(ex.getMessage());
	    throw te;
	}
    }

    public void eliminarOrga(Integer orgaPk) {
	if (orgaPk != null) {
	    OrganiIntProveDAO dao = new OrganiIntProveDAO(em);
	    try {
		OrganiIntProve orga = obtenerOrganiIntProvePorId(orgaPk);
		dao.delete(orga);
	    } catch (DAOGeneralException ex) {
		logger.log(Level.SEVERE, null, ex);

		BusinessException be = new BusinessException();
		be.addError(MensajesNegocio.ERROR_ORGANIZACION_ELIMINAR);
		if (ex.getCause() instanceof javax.persistence.PersistenceException
			&& ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
		    be.addError(MensajesNegocio.ERROR_ORGANIZACION_CONST_VIOLATION);
		}
		throw be;
	    }
	}
    }

    /**
     * Busqueda de Oranización por filtro.
     *
     * @param orgPk
     * @param mapFiltro
     * @param elementoOrdenacion
     * @param ascendente
     * @return OrganiIntProve
     */
    public List<OrganiIntProve> busquedaOrgaFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
	if (orgPk != null) {
	    List<CriteriaTO> criterios = new ArrayList<>();

	    MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
		    MatchCriteriaTO.types.EQUALS, "orgaOrgFk.orgPk", orgPk);
	    criterios.add(criterioOrg);

	    String nombre = mapFiltro != null ? (String) mapFiltro.get("orgaNombre") : null;
	    if (!StringsUtils.isEmpty(nombre)) {
                /**
                 * BRUNO 09-05-2017: revienta al copia organismo. Está mal armado el criterio en DAOUtils.
                 */
		MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "orgaNombre", nombre);
//		CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("orgaNombre", nombre);
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

	    OrganiIntProveDAO dao = new OrganiIntProveDAO(em);

	    try {
		return dao.findEntityByCriteria(OrganiIntProve.class, condicion, orderBy, asc, null, null);
	    } catch (DAOGeneralException ex) {
		logger.log(Level.SEVERE, null, ex);
		BusinessException be = new BusinessException();
		be.addError(MensajesNegocio.ERROR_ORGANIZACION_OBTENER);
		throw be;
	    }
	}
	return null;
    }

    private void validarDuplicado(OrganiIntProve orga) {
	Map<String, Object> filtroMap = new HashMap<>();
	filtroMap.put(ORGA_NOMBRE, orga.getOrgaNombre());
	List<OrganiIntProve> list = busquedaOrgaFiltro(orga.getOrgaOrgFk().getOrgPk(), filtroMap, null, 0);
	if (CollectionsUtils.isNotEmpty(list)) {
	    for (OrganiIntProve o : list) {
		if (!o.getOrgaPk().equals(orga.getOrgaPk())
			&& o.getOrgaNombre().equals(orga.getOrgaNombre())) {
		    BusinessException be = new BusinessException();
		    be.addError(MensajesNegocio.ERROR_ORGANIZACION_NOMBRE_DUPLICADO);
		    throw be;
		}
	    }
	}
    }
}
