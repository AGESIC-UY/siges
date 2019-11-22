package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.RolesInteresadosValidacion;
import com.sofis.data.daos.RolesInteresadosDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.RolesInteresados;
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
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "RolesInteresadosBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class RolesInteresadosBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(RolesInteresadosBean.class.getName());
    @Inject
    private DatosUsuario du;

    //private String usuario;
    //private String origen;

    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    /**
     * Devuelve el elemento RolesInteresados por el ID
     *
     * @param rolIntId
     * @return
     * @throws GeneralException
     */
    public RolesInteresados obtenerRolesInteresadosPorId(Integer rolIntId) throws GeneralException {
        RolesInteresadosDAO rolesInteresadosDAO = new RolesInteresadosDAO(em);
        try {
            return rolesInteresadosDAO.findById(RolesInteresados.class, rolIntId);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }

    }

    /**
     * Devuelve todos los elementos de tipo RolesInteresados
     *
     * @return
     * @throws GeneralException
     */
    public List<RolesInteresados> obtenerTodos() throws GeneralException {
        RolesInteresadosDAO rolesInteresadosDAO = new RolesInteresadosDAO(em);
        try {
            return rolesInteresadosDAO.findAll(RolesInteresados.class);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public List<RolesInteresados> obtenerRolPorOrgPk(Integer orgPk) {
        RolesInteresadosDAO rolesInteresadosDAO = new RolesInteresadosDAO(em);
        try {
//            return rolesInteresadosDAO.findByOneProperty(RolesInteresados.class, "rolintOrgFk.orgPk", orgPk);
            return rolesInteresadosDAO.findByOrg(orgPk);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public RolesInteresados guardarRol(RolesInteresados rolEnEdicion) {
        if (rolEnEdicion != null) {
            RolesInteresadosValidacion.validar(rolEnEdicion);
            validarDuplicado(rolEnEdicion);

            RolesInteresadosDAO dao = new RolesInteresadosDAO(em);
            try {
                return dao.update(rolEnEdicion, du.getCodigoUsuario(),du.getOrigen());
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_ROL_INT_GUARDAR);
                throw be;
            }
        }
        return null;
    }

    public List<RolesInteresados> busquedaRolFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        if (orgPk != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "rolintOrgFk.orgPk", orgPk);
            criterios.add(criterioOrg);

            String nombre = mapFiltro != null ? (String) mapFiltro.get("nombre") : null;
            if (!StringsUtils.isEmpty(nombre)) {
//		MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "rolintNombre", nombre);
                CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("rolintNombre", nombre);
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

            RolesInteresadosDAO dao = new RolesInteresadosDAO(em);

            try {
                return dao.findEntityByCriteria(RolesInteresados.class, condicion, orderBy, asc, null, null);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_ROL_INT_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public void eliminarRol(Integer rolPk) {
        if (rolPk != null) {
            RolesInteresadosDAO dao = new RolesInteresadosDAO(em);
            try {
                RolesInteresados rol = obtenerRolesInteresadosPorId(rolPk);
                dao.delete(rol);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);

                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_ROL_INT_ELIMINAR);
                if (ex.getCause() instanceof javax.persistence.PersistenceException
                        && ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                    be.addError(MensajesNegocio.ERROR_ROL_INT_CONST_VIOLATION);
                }
                throw be;
            }
        }
    }

    private void validarDuplicado(RolesInteresados rol) {
        Map<String, Object> filtroMap = new HashMap<>();
        filtroMap.put("nombre", rol.getRolintNombre());
        List<RolesInteresados> list = busquedaRolFiltro(rol.getRolintOrgFk().getOrgPk(), filtroMap, null, 0);
        if (CollectionsUtils.isNotEmpty(list)) {
            for (RolesInteresados r : list) {
                if (!r.getRolintPk().equals(rol.getRolintPk())
                        && r.getRolintNombre().equals(rol.getRolintNombre())) {
                    BusinessException be = new BusinessException();
                    be.addError(MensajesNegocio.ERROR_ROL_INT_NOMBRE_DUPLICADO);
                    throw be;
                }
            }
        }
    }
}
