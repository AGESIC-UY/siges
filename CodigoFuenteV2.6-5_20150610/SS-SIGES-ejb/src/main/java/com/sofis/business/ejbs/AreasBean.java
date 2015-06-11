package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.AreaValidacion;
import com.sofis.data.daos.AreasDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.SsUsuario;
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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
@Stateless(name = "AreasBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class AreasBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    @Inject
    private DatosUsuario du;

    public Areas guardar(Areas area) {
        if (area != null) {
            AreaValidacion.validar(area);
            validarDuplicado(area);

            AreasDAO dao = new AreasDAO(em);
            try {
                return dao.update(area, du.getCodigoUsuario(), du.getOrigen());
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_AREAS_GUARDAR);
                throw be;
            }
        }
        return null;
    }

    public Areas obtenerAreasPorPk(Integer areaId) throws GeneralException {
        AreasDAO areasDao = new AreasDAO(em);

        try {
            Areas area = areasDao.findById(Areas.class, areaId);
            return area;
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException te = new TechnicalException();
            te.addError(MensajesNegocio.ERROR_AREAS_OBTENER);
            throw te;
        }
    }

    public List<Areas> obtenerAreasPorIdOrganismo(int orgPk) throws GeneralException {
        AreasDAO areasDao = new AreasDAO(em);

        try {
            List<Areas> resultado = areasDao.findEntityByCriteria(Areas.class,
                    CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS,
                            "areaOrgFk.orgPk", orgPk), new String[]{"areaNombre"}, new boolean[]{true}, null, null);
            return resultado;

        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException te = new TechnicalException();
            te.addError(ex.getMessage());
            throw te;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Areas> obtenerAreasRestringidasPorFichaPk(Integer fichaPf, Integer tipoFicha) {
        AreasDAO areasDao = new AreasDAO(em);
        try {
            List<Areas> resultado = areasDao.obtenerAreasRestringidasPorFichaPk(fichaPf, tipoFicha);
            return resultado;
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            TechnicalException te = new TechnicalException();
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public List<Areas> busquedaAreaFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        if (orgPk != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "areaOrgFk.orgPk", orgPk);
            criterios.add(criterioOrg);

            String nombre = mapFiltro != null ? (String) mapFiltro.get("nombre") : null;
            if (!StringsUtils.isEmpty(nombre)) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.CONTAINS, "areaNombre", nombre);
                criterios.add(criterio);
            }

            SsUsuario director = mapFiltro != null ? (SsUsuario) mapFiltro.get("director") : null;
            if (director != null) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.EQUALS, "areaDirectorPk.usuId", director.getUsuId());
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
                asc = new boolean[]{(ascendente == 1 ? true : false)};
            }

            AreasDAO dao = new AreasDAO(em);

            try {
                return dao.findEntityByCriteria(Areas.class, condicion, orderBy, asc, null, null);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_AREAS_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public void eliminarArea(Integer aPk) {
        if (aPk != null) {
            AreasDAO dao = new AreasDAO(em);
            try {
                Areas a = obtenerAreasPorPk(aPk);
                dao.delete(a);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);

                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_AREAS_ELIMINAR);
                if (ex.getCause() instanceof javax.persistence.PersistenceException
                        && ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                    be.addError(MensajesNegocio.ERROR_AREAS_CONST_VIOLATION);
                }
                throw be;
            }
        }
    }

    private void validarDuplicado(Areas area) {
        List<Areas> list = obtenerAreasPorNombre(area.getAreaNombre(), area.getAreaOrgFk().getOrgPk());
        if (CollectionsUtils.isNotEmpty(list)) {
            for (Areas a : list) {
                if (!a.getAreaPk().equals(area.getAreaPk())
                        && a.getAreaNombre().equals(area.getAreaNombre())) {
                    BusinessException be = new BusinessException();
                    be.addError(MensajesNegocio.ERROR_AREAS_NOMBRE_DUPLICADO);
                    throw be;
                }
            }
        }
    }

    private List<Areas> obtenerAreasPorNombre(String areaNombre, Integer orgPk) {
        if (orgPk != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "areaOrgFk.orgPk", orgPk);
            criterios.add(criterioOrg);

            MatchCriteriaTO criterioNombre = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "areaNombre", areaNombre);
            criterios.add(criterioNombre);

            CriteriaTO criteria = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));

            AreasDAO areasDao = new AreasDAO(em);

            try {
                return areasDao.findEntityByCriteria(Areas.class, criteria, new String[]{}, new boolean[]{}, null, null);

            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException te = new BusinessException();
                te.addError(MensajesNegocio.ERROR_AREAS_OBTENER);
                throw te;
            }
        }
        return null;
    }
}
