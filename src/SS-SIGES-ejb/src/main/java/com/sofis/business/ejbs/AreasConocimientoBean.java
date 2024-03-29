package com.sofis.business.ejbs;

import com.sofis.business.validations.AreaConocimientoValidacion;
import com.sofis.data.daos.AreaConocimientoDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.AreaConocimiento;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
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
@Stateless(name = "AreasConocimientoBean")
@LocalBean
public class AreasConocimientoBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(AreasConocimientoBean.class.getName());
    @Inject
    private DatosUsuario du;
    
    //private String usuario;
    //private String origen;
    
    @PostConstruct
    public void init(){
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }
    

    public List<AreaConocimiento> obtenerAreaConPorOrg(Integer orgPk) {
	AreaConocimientoDAO dao = new AreaConocimientoDAO(em);
	try {
	    return dao.findByOneProperty(AreaConocimiento.class, "conOrganismo.orgPk", orgPk);
	} catch (DAOGeneralException ex) {
	    logger.log(Level.SEVERE, ex.getMessage(), ex);
	}
	return null;
    }

    public AreaConocimiento guardar(AreaConocimiento area) {
	if (area != null) {
	    AreaConocimientoValidacion.validar(area);
	    validarDuplicado(area);

	    AreaConocimientoDAO dao = new AreaConocimientoDAO(em);
	    try {
		return dao.update(area, du.getCodigoUsuario(),du.getOrigen());
	    } catch (DAOGeneralException ex) {
		logger.log(Level.SEVERE, ex.getMessage(), ex);
		BusinessException be = new BusinessException();
		be.addError(MensajesNegocio.ERROR_AREAS_GUARDAR);
		throw be;
	    }
	}
	return null;
    }

    public List<AreaConocimiento> obtenerAreasConPorLeccPk(Integer leccAprPk) {
	AreaConocimientoDAO dao = new AreaConocimientoDAO(em);
	return dao.obtenerAreasConPorLeccPk(leccAprPk);
    }

    public List<AreaConocimiento> busquedaAreaFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
	if (orgPk != null) {
	    List<CriteriaTO> criterios = new ArrayList<>();

	    MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
		    MatchCriteriaTO.types.EQUALS, "conOrganismo.orgPk", orgPk);
	    criterios.add(criterioOrg);

	    String nombre = mapFiltro != null ? (String) mapFiltro.get("nombre") : null;
	    if (!StringsUtils.isEmpty(nombre)) {
//		MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "conNombre", nombre);
		CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("conNombre", nombre);
		criterios.add(criterio);
	    }

	    AreaConocimiento padre = mapFiltro != null ? (AreaConocimiento) mapFiltro.get("padre") : null;
	    if (padre != null) {
		MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
			MatchCriteriaTO.types.EQUALS, "areaConPadreFk.conPk", padre.getConPk());
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

	    AreaConocimientoDAO dao = new AreaConocimientoDAO(em);

	    try {
		return dao.findEntityByCriteria(AreaConocimiento.class, condicion, orderBy, asc, null, null);
	    } catch (DAOGeneralException ex) {
		logger.log(Level.SEVERE, null, ex);
		BusinessException be = new BusinessException();
		be.addError(MensajesNegocio.ERROR_AREAS_CONOC_OBTENER);
		throw be;
	    }
	}
	return null;
    }

    public void eliminarArea(Integer aPk) {
	if (aPk != null) {
	    AreaConocimientoDAO dao = new AreaConocimientoDAO(em);
	    try {
		AreaConocimiento a = obtenerAreasPorPk(aPk);
		dao.delete(a);
	    } catch (DAOGeneralException ex) {
		logger.log(Level.SEVERE, null, ex);
		BusinessException be = new BusinessException();
		be.addError(MensajesNegocio.ERROR_AREAS_CONOC_ELIMINAR);
		if (ex.getCause() instanceof javax.persistence.PersistenceException
			&& ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
		    be.addError(MensajesNegocio.ERROR_AREAS_CONOC_CONST_VIOLATION);
		}
		throw be;
	    }
	}
    }

    private void validarDuplicado(AreaConocimiento area) {
	Map<String, Object> filtroMap = new HashMap<>();
	filtroMap.put("nombre", area.getConNombre());
	List<AreaConocimiento> list = busquedaAreaFiltro(area.getConOrganismo().getOrgPk(), filtroMap, null, 0);
	if (CollectionsUtils.isNotEmpty(list)) {
	    for (AreaConocimiento a : list) {
		if (!a.getConPk().equals(area.getConPk())
			&& a.getConNombre().equals(area.getConNombre())) {
		    BusinessException be = new BusinessException();
		    be.addError(MensajesNegocio.ERROR_AREAS_CONOC_NOMBRE_DUPLICADO);
		    throw be;
		}
	    }
	}
    }

    public AreaConocimiento obtenerAreasPorPk(Integer areaId) throws GeneralException {
	AreaConocimientoDAO dao = new AreaConocimientoDAO(em);

	try {
	    return dao.findById(AreaConocimiento.class, areaId);
	} catch (DAOGeneralException ex) {
	    logger.log(Level.SEVERE, null, ex);
	    TechnicalException te = new TechnicalException(ex);
	    te.addError(MensajesNegocio.ERROR_AREAS_CONOC_OBTENER);
	    throw te;
	}
    }

    public List<AreaConocimiento> obtenerAreasPorNombre(String areaNombre, Integer orgPk) {
	if (orgPk != null) {
	    List<CriteriaTO> criterios = new ArrayList<>();

	    MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
		    MatchCriteriaTO.types.EQUALS, "conOrganismo.orgPk", orgPk);
	    criterios.add(criterioOrg);

//	    MatchCriteriaTO criterioNombre = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "conNombre", areaNombre);
	    CriteriaTO criterioNombre = DAOUtils.createMatchCriteriaTOString("conNombre", areaNombre);
	    criterios.add(criterioNombre);

	    CriteriaTO criteria = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));

	    AreaConocimientoDAO dao = new AreaConocimientoDAO(em);

	    try {
		return dao.findEntityByCriteria(AreaConocimiento.class, criteria, new String[]{}, new boolean[]{}, null, null);

	    } catch (DAOGeneralException ex) {
		logger.log(Level.SEVERE, null, ex);
		BusinessException te = new BusinessException();
		te.addError(MensajesNegocio.ERROR_AREAS_CONOC_OBTENER);
		throw te;
	    }
	}
	return null;
    }

    public List<AreaConocimiento> obtenerSoloPadres(Integer orgPk) {
	AreaConocimientoDAO dao = new AreaConocimientoDAO(em);
	return dao.obtenerSoloPadres(orgPk);
    }
}
