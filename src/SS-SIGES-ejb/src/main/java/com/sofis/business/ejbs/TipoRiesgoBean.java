package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.TipoRiesgoValidacion;
import com.sofis.data.daos.TipoRiesgoDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.entities.data.TipoRiesgo;
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
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "TipoRiesgoBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class TipoRiesgoBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(TipoRiesgoBean.class.getName());
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

    public TipoRiesgo obtenerTipoRiesgoPorId(Integer ambitoPk) throws GeneralException {
        TipoRiesgoDAO dao = new TipoRiesgoDAO(em);
        try {
            TipoRiesgo ambito = dao.findById(TipoRiesgo.class, ambitoPk);
            return ambito;

        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_AMBITO_OBTENER);
            throw be;
        }
    }

    public List<TipoRiesgo> obtenerTipoRiesgoPorOrg(Integer orgPk) throws GeneralException {
        TipoRiesgoDAO dao = new TipoRiesgoDAO(em);
        try {
            List<TipoRiesgo> result = dao.findByOneProperty(TipoRiesgo.class, "trsOrgFk.orgPk", orgPk, "trsNombre");
            return result;

        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_AMBITO_OBTENER);
            throw be;
        }
    }

    public TipoRiesgo guardarTipoRiesgo(TipoRiesgo ambito) {
        if (ambito != null) {
            TipoRiesgoValidacion.validar(ambito);

            TipoRiesgoDAO dao = new TipoRiesgoDAO(em);
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
    
     public List<TipoRiesgo> busquedaActivos(Integer orgPk) {
         
          TipoRiesgoDAO dao = new TipoRiesgoDAO(em);
          return dao.busquedaActivos(orgPk);
       
         
     }

    public List<TipoRiesgo> busquedaTipoRiesgoFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        if (orgPk != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "trsOrgFk.orgPk", orgPk);
            criterios.add(criterioOrg);

            String nombre = mapFiltro != null ? (String) mapFiltro.get("trsNombre") : null;
            if (!StringsUtils.isEmpty(nombre)) {
//		MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "ambNombre", nombre);
                CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("trsNombre", nombre);
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

            TipoRiesgoDAO dao = new TipoRiesgoDAO(em);

            try {
                return dao.findEntityByCriteria(TipoRiesgo.class, condicion, orderBy, asc, null, null);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_AMBITO_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public void eliminarTipoRiesgo(Integer aPk) {
        if (aPk != null) {
            TipoRiesgoDAO dao = new TipoRiesgoDAO(em);
            try {
                TipoRiesgo a = obtenerTipoRiesgoPorId(aPk);
                dao.delete(a);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);

                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_TIPO_RIESGO_ELIMINAR);
                if (ex.getCause() instanceof javax.persistence.PersistenceException
                        && ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                    be.addError(MensajesNegocio.ERROR_TIPO_RIESGO_CONST_VIOLATION);
                }
                throw be;
            }
        }
    }

}
