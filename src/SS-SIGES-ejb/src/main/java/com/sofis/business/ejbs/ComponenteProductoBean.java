package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.ComponenteProductoValidacion;
import com.sofis.data.daos.ComponenteProductoDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.ComponenteProducto;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
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
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "ComponenteProductoBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class ComponenteProductoBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ComponenteProductoBean.class.getName());
    @Inject
    private DatosUsuario du;

    
    //private String usuario;
    //private String origen;
    
    @PostConstruct
    public void init(){
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }
    
    /**
     * Obtener una lista de Fuentes de Financiamiento deacuero al organismo
     * aportado.
     *
     * @param orgId
     * @return Lista ComponenteProducto
     * @throws GeneralException
     */
    public List<ComponenteProducto> obtenerComponentesProductosPorOrgId(Integer orgId) throws GeneralException {
	ComponenteProductoDAO dao = new ComponenteProductoDAO(em);
	try {
//            List<ComponenteProducto> result = fueDao.findByOneProperty(ComponenteProducto.class, "fueOrgFk.orgPk", orgId);
	    List<ComponenteProducto> result = dao.obtenerPorOrganismo(orgId);
	    return result;

	} catch (DAOGeneralException ex) {
	    logger.log(Level.SEVERE, ex.getMessage(), ex);
	    BusinessException be = new BusinessException();
	    be.addError(MensajesNegocio.ERROR_COMPONENTE_PRODUCTO_OBTENER);
	    throw be;
	}
    }

    public ComponenteProducto guardarComponenteProducto(ComponenteProducto componenteProductoEnEdicion) {
	if (componenteProductoEnEdicion != null) {
	    ComponenteProductoValidacion.validar(componenteProductoEnEdicion);
	    validarDuplicado(componenteProductoEnEdicion);

	    ComponenteProductoDAO dao = new ComponenteProductoDAO(em);
	    try {
		return dao.update(componenteProductoEnEdicion, du.getCodigoUsuario(),du.getOrigen());
	    } catch (DAOGeneralException ex) {
		logger.log(Level.SEVERE, ex.getMessage(), ex);
		BusinessException be = new BusinessException();
		be.addError(MensajesNegocio.ERROR_COMPONENTE_PRODUCTO_GUARDAR);
		throw be;
	    }
	}
	return null;
    }

    public ComponenteProducto obtenerComponenteProductoPorPk(Integer fuentePk) {
	ComponenteProductoDAO dao = new ComponenteProductoDAO(em);

	try {
	    ComponenteProducto componenteProducto = dao.findById(ComponenteProducto.class, fuentePk);
	    return componenteProducto;
	} catch (DAOGeneralException ex) {
	    logger.log(Level.SEVERE, ex.getMessage(), ex);
	    BusinessException be = new BusinessException();
	    be.addError(MensajesNegocio.ERROR_COMPONENTE_PRODUCTO_OBTENER);
	    throw be;
	}
    }

    public List<ComponenteProducto> busquedaComponenteProductoFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
	if (orgPk != null) {
	    List<CriteriaTO> criterios = new ArrayList<>();
            

	    MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
		    MatchCriteriaTO.types.EQUALS, "comOrgFk.orgPk", orgPk);
	    criterios.add(criterioOrg);

	    String nombre = mapFiltro != null ? (String) mapFiltro.get("nombre") : null;
	    if (!StringsUtils.isEmpty(nombre)) {
//		MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "fueNombre", nombre);
		CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("comNombre", nombre);
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

	    ComponenteProductoDAO dao = new ComponenteProductoDAO(em);

	    try {
		return dao.findEntityByCriteria(ComponenteProducto.class, condicion, orderBy, asc, null, null);
	    } catch (DAOGeneralException ex) {
		logger.log(Level.SEVERE, null, ex);
		BusinessException be = new BusinessException();
		be.addError(MensajesNegocio.ERROR_COMPONENTE_PRODUCTO_OBTENER);
		throw be;
	    }
	}
	return null;
    }

    public void eliminarComponenteProducto(Integer componenteProductoPk) {
	if (componenteProductoPk != null) {
	    ComponenteProductoDAO dao = new ComponenteProductoDAO(em);
	    try {
		ComponenteProducto f = obtenerComponenteProductoPorPk(componenteProductoPk);
		dao.delete(f);
	    } catch (DAOGeneralException ex) {
		logger.log(Level.SEVERE, null, ex);

		BusinessException be = new BusinessException();
		be.addError(MensajesNegocio.ERROR_COMPONENTE_PRODUCTO_ELIMINAR);
		if (ex.getCause() instanceof javax.persistence.PersistenceException
			&& ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
		    be.addError(MensajesNegocio.ERROR_COMPONENTE_PRODUCTO_CONST_VIOLATION);
		}
		throw be;
	    }
	}
    }

    private void validarDuplicado(ComponenteProducto componenteProducto) {
	Map<String, Object> filtroMap = new HashMap<>();
	filtroMap.put("nombre", componenteProducto.getComNombre());
	List<ComponenteProducto> list = busquedaComponenteProductoFiltro(componenteProducto.getComOrgFk().getOrgPk(), filtroMap, null, 0);
	if (CollectionsUtils.isNotEmpty(list)) {
	    for (ComponenteProducto f : list) {
		if (!f.getComPk().equals(componenteProducto.getComPk())
			&& f.getComNombre().equals(componenteProducto.getComNombre())) {
		    BusinessException be = new BusinessException();
		    be.addError(MensajesNegocio.ERROR_COMPONENTE_PRODUCTO_NOMBRE_DUPLICADO);
		    throw be;
		}
	    }
	}
    }
}
