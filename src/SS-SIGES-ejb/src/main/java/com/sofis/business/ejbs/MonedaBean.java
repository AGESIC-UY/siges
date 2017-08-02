package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.MonedaValidacion;
import com.sofis.data.daos.MonedaDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Moneda;
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
@Stateless(name = "MonedaBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class MonedaBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
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
     * Obtener todas las monedas.
     *
     * @return Todas las monedas.
     */
    public List<Moneda> obtenerMonedas() {
        MonedaDAO monedaDao = new MonedaDAO(em);
        try {
            List<Moneda> resultado = monedaDao.findAll(Moneda.class, "monPk");
            return resultado;
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_MONEDA_OBTENER);
            throw be;
        }
    }

    public Moneda obtenerMonedaPorId(Integer monPk) {
        MonedaDAO monedaDao = new MonedaDAO(em);
        try {
            return monedaDao.findById(Moneda.class, monPk);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_MONEDA_OBTENER);
            throw be;
        }
    }

    public Moneda guardarMoneda(Moneda monedaEnEdicion) {
        if (monedaEnEdicion != null) {
            MonedaValidacion.validar(monedaEnEdicion);
            validarDuplicado(monedaEnEdicion);

            MonedaDAO dao = new MonedaDAO(em);
            try {
                return dao.update(monedaEnEdicion, du.getCodigoUsuario(),du.getOrigen());
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_MONEDA_GUARDAR);
                throw be;
            }
        }
        return null;
    }

    /**
     *
     * @param mapFiltro
     * @param elementoOrdenacion
     * @param ascendente
     * @param AND 0=or, 1=and
     * @return
     */
    public List<Moneda> busquedaMonedaFiltro(Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente, int AND) {
        List<CriteriaTO> criterios = new ArrayList<>();

        String nombre = mapFiltro != null ? (String) mapFiltro.get("monNombre") : null;
        if (!StringsUtils.isEmpty(nombre)) {
            MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.CONTAINS, "monNombre", nombre);
            criterios.add(criterio);
        }

        String signo = mapFiltro != null ? (String) mapFiltro.get("monSigno") : null;
        if (!StringsUtils.isEmpty(signo)) {
            MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.CONTAINS, "monSigno", signo);
            criterios.add(criterio);
        }

        CriteriaTO condicion;
        if (!criterios.isEmpty()) {
            if (criterios.size() == 1) {
                condicion = criterios.get(0);
            } else {
                if (AND == 1) {
                    condicion = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));
                } else {
                    condicion = CriteriaTOUtils.createORTO(criterios.toArray(new CriteriaTO[0]));
                }
            }
        } else {
            condicion = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.NOT_NULL, "monNombre", 1);
        }

        String[] orderBy = {};
        boolean[] asc = {};
        if (!StringsUtils.isEmpty(elementoOrdenacion)) {
            orderBy = new String[]{elementoOrdenacion};
            asc = new boolean[]{(ascendente == 1)};
        }

        MonedaDAO dao = new MonedaDAO(em);

        try {
            return dao.findEntityByCriteria(Moneda.class, condicion, orderBy, asc, null, null);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_MONEDA_OBTENER);
            throw be;
        }
    }

    public void eliminarMoneda(Integer monedaPk) {
        if (monedaPk != null) {
            MonedaDAO dao = new MonedaDAO(em);
            try {
                Moneda m = obtenerMonedaPorId(monedaPk);
                dao.delete(m);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);

                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_MONEDA_ELIMINAR);
                if (ex.getCause() instanceof javax.persistence.PersistenceException
                        && ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                    be.addError(MensajesNegocio.ERROR_MONEDA_CONST_VIOLATION);
                }
                throw be;
            }
        }
    }

    private void validarDuplicado(Moneda moneda) {
        Map<String, Object> filtroMap = new HashMap<>();
        filtroMap.put("monNombre", moneda.getMonNombre());
        filtroMap.put("monSigno", moneda.getMonSigno());
        List<Moneda> list = busquedaMonedaFiltro(filtroMap, null, 0, 0);
        if (CollectionsUtils.isNotEmpty(list)) {
            for (Moneda m : list) {
                if (!m.getMonPk().equals(moneda.getMonPk())
                        && (m.getMonNombre().equals(moneda.getMonNombre())
                        || m.getMonSigno().equals(moneda.getMonSigno()))) {
                    BusinessException be = new BusinessException();
                    be.addError(MensajesNegocio.ERROR_MONEDA_NOMBRE_DUPLICADO);
                    throw be;
                }
            }
        }
    }
}
