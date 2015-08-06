package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.TipoGastoValidacion;
import com.sofis.data.daos.TipoGastoDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.TipoGasto;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.List;
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
 *
 * @author Usuario
 */
@Named
@Stateless(name = "TipoGastoBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class TipoGastoBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    @Inject
    private DatosUsuario du;

    public void eliminarTipoGasto(Integer tgPk) {
        if (tgPk != null) {
            TipoGastoDAO dao = new TipoGastoDAO(em);
            try {
                TipoGasto tg = obtenerTipoGastoPorPk(tgPk);
                dao.delete(tg);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);

                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_TIPO_GASTO_ELIMINAR);
                if (ex.getCause() instanceof javax.persistence.PersistenceException
                        && ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                    be.addError(MensajesNegocio.ERROR_TIPO_GASTO_CONST_VIOLATION);
                }
                throw be;
            }
        }
    }

    public List<TipoGasto> busquedaTipoGastoFiltro(Integer orgPk, String filtroNombre, String elementoOrdenacion, int ascendente) {
        if (orgPk != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "tipogasOrgFk.orgPk", orgPk);
            criterios.add(criterioOrg);

            if (!StringsUtils.isEmpty(filtroNombre)) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.CONTAINS, "tipogasNombre", filtroNombre);
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

            TipoGastoDAO dao = new TipoGastoDAO(em);
            try {
                return dao.findEntityByCriteria(TipoGasto.class, condicion, orderBy, asc, null, null);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_TIPO_GASTO_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public TipoGasto obtenerTipoGastoPorPk(Integer tgPk) {
        if (tgPk != null) {
            TipoGastoDAO dao = new TipoGastoDAO(em);
            try {
                return dao.findById(TipoGasto.class, tgPk);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_TIPO_GASTO_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public TipoGasto guardarTipoGasto(TipoGasto tg) {
        if (tg != null) {
            TipoGastoValidacion.validar(tg);
            validarDuplicado(tg);

            TipoGastoDAO dao = new TipoGastoDAO(em);
            try {
                return dao.update(tg, du.getCodigoUsuario(), du.getOrigen());
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(ex.getMessage());
                throw be;
            }
        }
        return null;
    }

    private void validarDuplicado(TipoGasto tg) {
        List<TipoGasto> list = obtenerTipoGastoPorNombre(tg.getTipogasNombre(), tg.getTipogasOrgFk().getOrgPk());
        if (CollectionsUtils.isNotEmpty(list)) {
            for (TipoGasto tipoGasto : list) {
                if (!tipoGasto.getTipogasPk().equals(tg.getTipogasPk())
                        && tipoGasto.getTipogasNombre().equals(tg.getTipogasNombre())) {
                    BusinessException be = new BusinessException();
                    be.addError(MensajesNegocio.ERROR_TIPO_GASTO_NOMBRE_DUPLICADO);
                    throw be;
                }
            }
        }
    }

    private List<TipoGasto> obtenerTipoGastoPorNombre(String tipoGastoNombre, Integer orgPk) {
        if (orgPk != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "tipogasOrgFk.orgPk", orgPk);
            criterios.add(criterioOrg);

            MatchCriteriaTO criterioNombre = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "tipogasNombre", tipoGastoNombre);
            criterios.add(criterioNombre);

            CriteriaTO criteria = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));

            TipoGastoDAO dao = new TipoGastoDAO(em);

            try {
                return dao.findEntityByCriteria(TipoGasto.class, criteria, new String[]{}, new boolean[]{}, null, null);

            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException te = new BusinessException();
                te.addError(MensajesNegocio.ERROR_TIPO_GASTO_OBTENER);
                throw te;
            }
        }
        return null;
    }

    public List<TipoGasto> obtenerTipoGastoPorOrg(Integer orgPk) {
        if (orgPk != null) {
            TipoGastoDAO dao = new TipoGastoDAO(em);
            try {
                return dao.findByOneProperty(TipoGasto.class, "tipogasOrgFk.orgPk", orgPk, "tipogasNombre");
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_TIPO_GASTO_OBTENER);
                throw be;
            }
        }
        return null;
    }
}
