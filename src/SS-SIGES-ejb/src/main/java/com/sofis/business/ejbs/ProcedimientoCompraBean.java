package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.ProcedimientoCompraValidacion;
import com.sofis.data.daos.ProcedimientoCompraDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.ProcedimientoCompra;
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
import java.util.Iterator;
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
@Stateless(name = "ProcedimientoCompraBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class ProcedimientoCompraBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ProcedimientoCompraBean.class.getName());
    @Inject
    private DatosUsuario du;
    
    @Inject
    private AdquisicionBean adquisicionBean;

    //private String usuario;
    //private String origen;
    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    /**
     * Obtener una lista de Fuentes de Financiamiento deacuero al organismo
     * aportado.
     *
     * @param orgId
     * @return Lista ProcedimientoCompra
     * @throws GeneralException
     */
    public List<ProcedimientoCompra> obtenerProcedimientosComprasHabilitadosPorOrgId(Integer orgId) throws GeneralException {
        ProcedimientoCompraDAO dao = new ProcedimientoCompraDAO(em);
        try {
//            List<ProcedimientoCompra> result = fueDao.findByOneProperty(ProcedimientoCompra.class, "fueOrgFk.orgPk", orgId);
            List<ProcedimientoCompra> result = dao.obtenerHabilitadosPorOrganismo(orgId);
            return result;

        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_PROCEDIMIENTO_COMPRA_OBTENER);
            throw be;
        }
    }

    public ProcedimientoCompra guardarProcedimientoCompra(ProcedimientoCompra procedimientoCompraEnEdicion) {
        if (procedimientoCompraEnEdicion != null) {
            ProcedimientoCompraValidacion.validar(procedimientoCompraEnEdicion);
            validarDuplicado(procedimientoCompraEnEdicion);

            ProcedimientoCompraDAO dao = new ProcedimientoCompraDAO(em);
            try {
                ProcedimientoCompra procedimientoCompraActualizado = dao.update(procedimientoCompraEnEdicion, du.getCodigoUsuario(), du.getOrigen());
                if(procedimientoCompraEnEdicion.getProcAdquisicionId()!=null){
                    adquisicionBean.actualizarPorCargaMasiva(procedimientoCompraEnEdicion.getProcAdquisicionId(),procedimientoCompraActualizado.getProcCompPk(),procedimientoCompraActualizado.getProcCompOrgFk().getOrgPk());
                }
                return procedimientoCompraActualizado;
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_PROCEDIMIENTO_COMPRA_GUARDAR);
                throw be;
            }
        }
        return null;
    }

    public ProcedimientoCompra obtenerProcedimientoCompraPorPk(Integer procComPk) {
        ProcedimientoCompraDAO dao = new ProcedimientoCompraDAO(em);

        try {
            ProcedimientoCompra procedimientoCompra = dao.findById(ProcedimientoCompra.class, procComPk);
            return procedimientoCompra;
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_PROCEDIMIENTO_COMPRA_OBTENER);
            throw be;
        }
    }

    public List<ProcedimientoCompra> obtenerProcedimientosComprasPorOrgId(Integer orgId) {

        Map<String, Object> mapFiltro = new HashMap<>();
        mapFiltro.put("habilitado", true);

        return busquedaProcedimientoCompraFiltro(orgId, mapFiltro, "procCompNombre", 1);

    }

    public List<ProcedimientoCompra> busquedaProcedimientoCompraFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        if (orgPk != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "procCompOrgFk.orgPk", orgPk);

            criterios.add(criterioOrg);

            String nombre = mapFiltro != null ? (String) mapFiltro.get("nombre") : null;
            if (!StringsUtils.isEmpty(nombre)) {
                CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("procCompNombre", nombre);
                criterios.add(criterio);
            }

            if ((mapFiltro != null) && mapFiltro.containsKey("habilitado")) {
                CriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS,
                        "procCompHabilitado", (Boolean) mapFiltro.get("habilitado"));

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

            ProcedimientoCompraDAO dao = new ProcedimientoCompraDAO(em);

            try {
                return dao.findEntityByCriteria(ProcedimientoCompra.class, condicion, orderBy, asc, null, null);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException(ex);
                be.addError(MensajesNegocio.ERROR_PROCEDIMIENTO_COMPRA_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public void eliminarProcedimientoCompra(Integer procedimientoCompraPk) {
        if (procedimientoCompraPk != null) {
            ProcedimientoCompraDAO dao = new ProcedimientoCompraDAO(em);
            try {
                ProcedimientoCompra f = obtenerProcedimientoCompraPorPk(procedimientoCompraPk);
                dao.delete(f);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);

                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_PROCEDIMIENTO_COMPRA_ELIMINAR);
                if (ex.getCause() instanceof javax.persistence.PersistenceException
                        && ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                    be.addError(MensajesNegocio.ERROR_PROCEDIMIENTO_COMPRA_CONST_VIOLATION);
                }
                throw be;
            }
        }
    }

    private void validarDuplicado(ProcedimientoCompra procedimientoCompra) {
        Map<String, Object> filtroMap = new HashMap<>();
        filtroMap.put("nombre", procedimientoCompra.getProcCompNombre());
        List<ProcedimientoCompra> list = busquedaProcedimientoCompraFiltro(procedimientoCompra.getProcCompOrgFk().getOrgPk(), filtroMap, null, 0);
        if (CollectionsUtils.isNotEmpty(list)) {
            for (ProcedimientoCompra f : list) {
                if (!f.getProcCompPk().equals(procedimientoCompra.getProcCompPk())
                        && f.getProcCompNombre().equals(procedimientoCompra.getProcCompNombre())) {
                    BusinessException be = new BusinessException();
                    be.addError(MensajesNegocio.ERROR_PROCEDIMIENTO_COMPRA_NOMBRE_DUPLICADO);
                    throw be;
                }
            }
        }
    }

    public ProcedimientoCompra obtenerProcedimientoCompraPorNombre(String procCompNom, Integer orgPk) {
        ProcedimientoCompraDAO dao = new ProcedimientoCompraDAO(em);

        try {
            ProcedimientoCompra procedimientoCompra = dao.obtenerPorNombreYOrg(procCompNom, orgPk);
            return procedimientoCompra;
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_PROCEDIMIENTO_COMPRA_OBTENER);
            throw be;
        }

    }

    public ProcedimientoCompra actualizarProcedimientoCompra(ProcedimientoCompra procedimientoCompraEnEdicion) {
        if (procedimientoCompraEnEdicion != null) {

            ProcedimientoCompraDAO dao = new ProcedimientoCompraDAO(em);

            try {
                ProcedimientoCompra procCompOriginal = dao.obtenerPorIdYOrg(procedimientoCompraEnEdicion.getProcCompPk(), procedimientoCompraEnEdicion.getProcCompOrgFk().getOrgPk());

                procCompOriginal.setProcCompNombre(procedimientoCompraEnEdicion.getProcCompNombre());
                procCompOriginal.setProcCompHabilitado(procedimientoCompraEnEdicion.getProcCompHabilitado());
                procCompOriginal.setProcCompDescripcion(procedimientoCompraEnEdicion.getProcCompDescripcion());

                return dao.update(procCompOriginal, du.getCodigoUsuario(), du.getOrigen());
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_PROCEDIMIENTO_COMPRA_GUARDAR);
                throw be;
            }
        }
        return null;
    }

    public void cargaMasiva(List<ProcedimientoCompra> datos, Integer orgPk) {
        ProcedimientoCompraDAO dao = new ProcedimientoCompraDAO(em);

        try {
            /*
            *       Lo que se hace en este metodo es ver si existe un procedimiento de compra con el nombre que se pasa,
            *   si ya existe en el sistema y se le pasa el ID, entonces se actiualizan los datos, en caso de que el nombre
            *   exista en el sistema pero no se le haya pasado el ID, se da de alta en el sistema.
             */

            //List<ProcedimientoCompra> datosEnBD = dao.findByOneProperty(ProcedimientoCompra.class, "procCompOrgFk.orgPk", orgPk);
            List<ProcedimientoCompra> datosEnBD = new ArrayList<ProcedimientoCompra>();
            Map<String, Object> filtroMap = new HashMap<>();
            for (ProcedimientoCompra datosRow : datos) {
                try {
                    filtroMap.put("nombre", datosRow.getProcCompNombre());
                    List<ProcedimientoCompra> list = busquedaProcedimientoCompraFiltro(datosRow.getProcCompOrgFk().getOrgPk(), filtroMap, null, 0);
                    if (CollectionsUtils.isNotEmpty(list)) {
                        datosRow.setProcCompPk(list.get(0).getProcCompPk()) ;
                    }

                    guardarProcedimientoCompra(datosRow);
                } catch (Exception exec) {
                    logger.log(Level.WARNING, "Se encontró un código repetido al momento de la carga\n");
                    if (datosRow.getProcCompPk() != null) {
                        datosEnBD.add(datosRow);
                    }
                }
            }

            for (ProcedimientoCompra datosRestEnBD : datosEnBD) {
//                datosRestEnBD.setProcCompHabilitado(Boolean.FALSE);
                actualizarProcedimientoCompra(datosRestEnBD);
            }
        } catch (Exception exec) {
            logger.log(Level.SEVERE, exec.getMessage(), exec);
        }

    }

}
