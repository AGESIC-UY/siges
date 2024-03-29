package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.AmbitoValidacion;
import com.sofis.data.daos.AmbitoDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Ambito;
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
import javax.annotation.Resource;
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
@Stateless(name = "AmbitoBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class AmbitoBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(AmbitoBean.class.getName());
    @Inject
    private DatosUsuario du;
    @Resource
    private javax.ejb.SessionContext ctx;

    //private String usuario;
    //private String origen;

    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    public Ambito obtenerAmbitoPorId(Integer ambitoPk) throws GeneralException {
        AmbitoDAO dao = new AmbitoDAO(em);
        try {
            Ambito ambito = dao.findById(Ambito.class, ambitoPk);
            return ambito;

        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_AMBITO_OBTENER);
            throw be;
        }
    }

    public List<Ambito> obtenerAmbitoPorOrg(Integer orgPk) throws GeneralException {
        AmbitoDAO dao = new AmbitoDAO(em);
        try {
            List<Ambito> result = dao.findByOneProperty(Ambito.class, "ambOrgFk.orgPk", orgPk, "ambNombre");
            return result;

        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_AMBITO_OBTENER);
            throw be;
        }
    }

    public Ambito guardarAmbito(Ambito ambito) {
        if (ambito != null) {
            AmbitoValidacion.validar(ambito);
            validarDuplicado(ambito);

            AmbitoDAO dao = new AmbitoDAO(em);
            try {
                return dao.update(ambito, du.getCodigoUsuario(),du.getOrigen());
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_AMBITO_GUARDAR);
                throw be;
            }
        }
        return null;
    }

    public List<Ambito> busquedaAmbitoFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        if (orgPk != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "ambOrgFk.orgPk", orgPk);
            criterios.add(criterioOrg);

            String nombre = mapFiltro != null ? (String) mapFiltro.get("ambNombre") : null;
            if (!StringsUtils.isEmpty(nombre)) {
//		MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "ambNombre", nombre);
                CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("ambNombre", nombre);
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

            AmbitoDAO dao = new AmbitoDAO(em);

            try {
                return dao.findEntityByCriteria(Ambito.class, condicion, orderBy, asc, null, null);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_AMBITO_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public void eliminarAmbito(Integer aPk) {
        if (aPk != null) {
            AmbitoDAO dao = new AmbitoDAO(em);
            try {
                Ambito a = obtenerAmbitoPorId(aPk);
                dao.delete(a);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);

                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_AMBITO_ELIMINAR);
                if (ex.getCause() instanceof javax.persistence.PersistenceException
                        && ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                    be.addError(MensajesNegocio.ERROR_AMBITO_CONST_VIOLATION);
                }
                throw be;
            }
        }
    }

    private void validarDuplicado(Ambito ambito) {
        Map<String, Object> filtroMap = new HashMap<>();
        filtroMap.put("ambNombre", ambito.getAmbNombre());
        List<Ambito> list = busquedaAmbitoFiltro(ambito.getAmbOrgFk().getOrgPk(), filtroMap, null, 0);
        if (CollectionsUtils.isNotEmpty(list)) {
            for (Ambito a : list) {
                if (!a.getAmbPk().equals(ambito.getAmbPk())
                        && a.getAmbNombre().equals(ambito.getAmbNombre())) {
                    BusinessException be = new BusinessException();
                    be.addError(MensajesNegocio.ERROR_AMBITO_NOMBRE_DUPLICADO);
                    throw be;
                }
            }
        }
    }
}
