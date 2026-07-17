package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.ProcedimientoCompraDAO;
import com.sofis.data.daos.LogAdquisicionDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.entities.data.LogAdquisicion;
import com.sofis.exceptions.BusinessException;
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
@Stateless(name = "AdquisicionLogBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class AdquisicionLogBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(AdquisicionLogBean.class.getName());

    @Inject
    private DatosUsuario du;

    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    public LogAdquisicion guardarLogAdquisicion(LogAdquisicion procedimientoCompraEnEdicion) {

        try {
            LogAdquisicionDAO dao = new LogAdquisicionDAO(em);

            return dao.update(procedimientoCompraEnEdicion, du.getCodigoUsuario(), du.getOrigen());

        } catch (DAOGeneralException ex) {
            Logger.getLogger(AdquisicionLogBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public List<LogAdquisicion> busquedaLogAdquisicionFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {

        if (orgPk != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "ladAquisicionFk.adqPreFk.proyecto.proyOrgFk.orgPk", orgPk);

            criterios.add(criterioOrg);

            String nombre = mapFiltro != null ? (String) mapFiltro.get("nombreAdquisicion") : null;
            if (!StringsUtils.isEmpty(nombre)) {
                CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("ladAquisicionFk.adqNombre", nombre);
                criterios.add(criterio);
            }

            String nombreProyecto = mapFiltro != null ? (String) mapFiltro.get("nombreProyecto") : null;
            if (!StringsUtils.isEmpty(nombreProyecto)) {
                CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("ladAquisicionFk.adqPreFk.proyecto.proyNombre", nombreProyecto);
                criterios.add(criterio);
            }

            Integer codigoProyecto = mapFiltro != null ? (Integer) mapFiltro.get("codigoProyecto") : null;
            if (codigoProyecto != null && codigoProyecto > 0) {

                MatchCriteriaTO criterioP = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.EQUALS, "ladAquisicionFk.adqPreFk.proyecto.proyPk", codigoProyecto);

                criterios.add(criterioP);

            }

            Integer idAdquisicion = mapFiltro != null ? (Integer) mapFiltro.get("idAdquisicion") : null;
            if (idAdquisicion != null && idAdquisicion > 0) {

                MatchCriteriaTO criterioP = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.EQUALS, "ladAquisicionFk.adqIdAdquisicion", idAdquisicion);

                criterios.add(criterioP);

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

            LogAdquisicionDAO dao = new LogAdquisicionDAO(em);

            try {
                return dao.findEntityByCriteria(LogAdquisicion.class, condicion, orderBy, asc, null, null);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException(ex);
                be.addError(MensajesNegocio.ERROR_PROCEDIMIENTO_COMPRA_OBTENER);
                throw be;
            }
        }
        return null;

    }

    public LogAdquisicion obtenerLogAdquisicionPorPk(Integer procedimientoPk) {
        LogAdquisicionDAO dao = new LogAdquisicionDAO(em);

        try {
            LogAdquisicion procedimientoCompra = dao.findById(LogAdquisicion.class, procedimientoPk);
            return procedimientoCompra;
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_PROCEDIMIENTO_COMPRA_OBTENER);
            throw be;
        }
    }

}
