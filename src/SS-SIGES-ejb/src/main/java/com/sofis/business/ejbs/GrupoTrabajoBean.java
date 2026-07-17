package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.GrupoTrabajoValidacion;
import com.sofis.data.daos.GrupoTrabajoDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.GrupoTrabajo;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
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
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless(name = "GrupoTrabajoBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class GrupoTrabajoBean {

    private static final Logger LOGGER = Logger.getLogger(GrupoTrabajoBean.class.getName());

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    @Inject
    private DatosUsuario du;

    public List<GrupoTrabajo> listarPorOrg(Integer orgPk) throws GeneralException {

        GrupoTrabajoDAO dao = new GrupoTrabajoDAO(em);
        try {
            CriteriaTO criteriaCod = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "gtbOrgFk.orgPk", orgPk);

            List<GrupoTrabajo> resultado = dao.findEntityByCriteria(GrupoTrabajo.class, criteriaCod, new String[]{}, new boolean[]{}, null, null);

            return resultado;

        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }

    }

    public List<GrupoTrabajo> busquedaComponenteProductoFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {

        if (orgPk != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "gtbOrgFk.orgPk", orgPk);
            criterios.add(criterioOrg);

            String nombre = mapFiltro != null ? (String) mapFiltro.get("nombre") : null;
            if (!StringsUtils.isEmpty(nombre)) {
//		MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "fueNombre", nombre);
                CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("gtbNombre", nombre);
                criterios.add(criterio);
            }

            if (mapFiltro.get("habilitada") != null) {
                criterios.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "gtbHabilitado", (Boolean) mapFiltro.get("habilitada")));
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

            GrupoTrabajoDAO dao = new GrupoTrabajoDAO(em);

            try {
                return dao.findEntityByCriteria(GrupoTrabajo.class, condicion, orderBy, asc, null, null);
            } catch (DAOGeneralException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_COMPONENTE_PRODUCTO_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public void eliminarGrupoTrabajo(Integer componenteProductoPk) {
        if (componenteProductoPk != null) {
            GrupoTrabajoDAO dao = new GrupoTrabajoDAO(em);
            try {
                GrupoTrabajo f = obtenerGrupoTrabajoPk(componenteProductoPk);
                dao.delete(f);
            } catch (DAOGeneralException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);

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

    public GrupoTrabajo obtenerGrupoTrabajoPk(Integer componenteProductoPk) {
        GrupoTrabajoDAO dao = new GrupoTrabajoDAO(em);

        try {
            GrupoTrabajo componenteProducto = dao.findById(GrupoTrabajo.class, componenteProductoPk);
//LOGGER.log(Level.INFO, "size "+componenteProducto.getGrupoTrabajoUsuario().size());
            return componenteProducto;
        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_COMPONENTE_PRODUCTO_OBTENER);
            throw be;
        }
    }

    public GrupoTrabajo guardarGrupoTrabajo(GrupoTrabajo cgrupoTrabajoEnEdicion) {
        if (cgrupoTrabajoEnEdicion != null) {
            GrupoTrabajoValidacion.validar(cgrupoTrabajoEnEdicion);

            GrupoTrabajoDAO dao = new GrupoTrabajoDAO(em);
            try {
                return dao.update(cgrupoTrabajoEnEdicion, du.getCodigoUsuario(), du.getOrigen());
            } catch (DAOGeneralException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_AMBITO_GUARDAR);
                throw be;
            }
        }
        return null;
    }

}
