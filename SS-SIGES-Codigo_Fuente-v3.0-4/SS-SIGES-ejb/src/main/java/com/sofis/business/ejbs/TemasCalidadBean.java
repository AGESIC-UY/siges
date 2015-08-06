package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.TemasCalidadValidacion;
import com.sofis.data.daos.TemasCalidadDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEntities;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.TemasCalidad;
import com.sofis.exceptions.BusinessException;
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
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB para los Temas de Calidad.
 *
 * @author mgarcia
 */
@Named
@Stateless(name = "TemasCalidadBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class TemasCalidadBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    @Inject
    private DatosUsuario du;

    /**
     * Obteber un tema de calidad según el Id.
     *
     * @param tcaPk
     * @return TemasCalidad
     */
    public TemasCalidad obtenerPorId(Integer tcaPk) {
        TemasCalidadDAO dao = new TemasCalidadDAO(em);
        try {
            TemasCalidad tca = dao.findById(TemasCalidad.class, tcaPk);
            return tca;

        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_TEMAS_CALIDAD_OBTENER);
            throw be;
        }
    }

    /**
     * Obtener un tema de calidad según el organismo aportado.
     *
     * @param orgPk
     * @return List
     */
    public List<TemasCalidad> obtenerPorOrg(Integer orgPk) {
        TemasCalidadDAO dao = new TemasCalidadDAO(em);
        try {
            List<TemasCalidad> result = dao.findByOneProperty(TemasCalidad.class, ConstantesEntities.TCA_ATT_ORGANISMO + ".orgPk", orgPk, ConstantesEntities.TCA_ATT_NOMBRE);
            return result;

        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_TEMAS_CALIDAD_OBTENER);
            throw be;
        }
    }

    /**
     * Eliminar un tema de calidad
     *
     * @param tcaPk
     */
    public void eliminar(Integer tcaPk) {
        if (tcaPk != null) {
            TemasCalidadDAO dao = new TemasCalidadDAO(em);
            try {
                TemasCalidad tca = obtenerPorId(tcaPk);
                dao.delete(tca);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_TEMAS_CALIDAD_ELIMINAR);
                if (ex.getCause() instanceof javax.persistence.PersistenceException
                        && ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                    be.addError(MensajesNegocio.ERROR_TEMAS_CALIDAD_CONST_VIOLATION);
                }
                throw be;
            }
        }
    }

    /**
     * Busqueda por filtro.
     *
     * @param orgPk
     * @param mapFiltro
     * @param elementoOrdenacion
     * @param ascendente
     * @return List
     */
    public List<TemasCalidad> busquedaPorFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        if (orgPk != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, (ConstantesEntities.TCA_ATT_ORGANISMO + "." + ConstantesEntities.ORG_ATT_PK), orgPk);
            criterios.add(criterioOrg);

            if (mapFiltro != null) {
                String nombre = (String) mapFiltro.get(ConstantesEntities.TCA_ATT_NOMBRE);
                if (!StringsUtils.isEmpty(nombre)) {
                    MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                            MatchCriteriaTO.types.CONTAINS, ConstantesEntities.TCA_ATT_NOMBRE, nombre);
                    criterios.add(criterio);
                }
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

            TemasCalidadDAO dao = new TemasCalidadDAO(em);

            try {
                return dao.findEntityByCriteria(TemasCalidad.class, condicion, orderBy, asc, null, null);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_TEMAS_CALIDAD_OBTENER);
                throw be;
            }
        }
        return null;
    }

    /**
     * Persiste o actualiza el tema de calidad.
     *
     * @param tca
     * @return TemasCalidad
     */
    public TemasCalidad guardar(TemasCalidad tca) {
        if (tca != null) {
            validarDuplicado(tca);
            TemasCalidadValidacion.validar(tca);

            TemasCalidadDAO dao = new TemasCalidadDAO(em);
            try {
                return dao.update(tca, du.getCodigoUsuario(), du.getOrigen());
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_TEMAS_CALIDAD_GUARDAR);
                throw be;
            }
        }
        return null;
    }

    public TemasCalidad guardarTca(TemasCalidad tca) {
        tca.setTcaNombre(tca.getTcaNombre().trim());

        return guardar(tca);
    }

    /**
     * Valida si el nombre ya no fue ingresado.
     *
     * @param tca
     */
    private void validarDuplicado(TemasCalidad tca) {
        Map<String, Object> filtroMap = new HashMap<>();
        filtroMap.put(ConstantesEntities.TCA_ATT_NOMBRE, tca.getTcaNombre());
        List<TemasCalidad> list = busquedaPorFiltro(tca.getTcaOrgFk().getOrgPk(), filtroMap, null, 0);
        if (CollectionsUtils.isNotEmpty(list)) {
            for (TemasCalidad a : list) {
                if (!a.getTcaPk().equals(tca.getTcaPk())
                        && a.getTcaNombre().equals(tca.getTcaNombre())) {
                    BusinessException be = new BusinessException();
                    be.addError(MensajesNegocio.ERROR_TEMAS_CALIDAD_NOMBRE_DUPLICADO);
                    throw be;
                }
            }
        }
    }
}
