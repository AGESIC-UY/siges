package com.sofis.business.ejbs;

import com.sofis.business.validations.AdquisicionValidacion;
import com.sofis.data.daos.AdquisicionDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Devengado;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.AdqPagosTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "AdquisicionBean")
@LocalBean
public class AdquisicionBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    @Inject
    private DatosUsuario du;
    @EJB
    private PresupuestoBean presupuestoBean;
    @EJB
    private PagosBean pagosBean;
    @EJB
    private DevengadoBean devengadoBean;
    @EJB
    private ProyectosBean proyectosBean;

    //private String usuario;
    //private String origen;

    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    private static final Logger logger = Logger.getLogger(AdquisicionBean.class.getName());

    private Adquisicion guardar(Adquisicion adquisicion, Integer orgPk) throws GeneralException {
        AdquisicionValidacion.validar(adquisicion, orgPk);
        AdquisicionDAO preDao = new AdquisicionDAO(em);

        try {
            adquisicion = preDao.update(adquisicion, du.getCodigoUsuario(),du.getOrigen());

        } catch (TechnicalException te) {
            logger.log(Level.SEVERE, te.getMessage(), te);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ADQISICION_GUARDAR);
            throw be;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ADQISICION_GUARDAR);
            throw be;
        }

        return adquisicion;
    }

    public Adquisicion guardarAdquisicion(Adquisicion adquisicion, Integer fichaFk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) throws GeneralException {
        if (adquisicion.getAdqPreFk() == null) {
            Presupuesto pre = new Presupuesto();
            pre = (Presupuesto) presupuestoBean.guardarPresupuesto(pre, fichaFk, tipoFicha, usuario, orgPk, true);
            adquisicion.setAdqPreFk(pre);
        }

        if (CollectionsUtils.isNotEmpty(adquisicion.getDevengadoList())) {
            List<Devengado> devList = new ArrayList<>();
            for (Devengado dev : adquisicion.getDevengadoList()) {
                if (!(dev.getDevPlan() == null && dev.getDevReal() == null)) {
                    devList.add(dev);
                }
            }
            adquisicion.setDevengadoList(devList);
        }

        return guardar(adquisicion, orgPk);
    }

    public List<AdqPagosTO> obtenerAdquisicionPagosList(Integer presupuestoId) {
        AdquisicionDAO adqDao = new AdquisicionDAO(em);
        try {
            return adqDao.obtenerAdquisicionPagosList(presupuestoId);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    public Adquisicion obtenerAdquisicionPorId(Integer adqPk) {
        AdquisicionDAO adqDao = new AdquisicionDAO(em);
        Adquisicion adq = null;
        try {
            adq = adqDao.findById(Adquisicion.class, adqPk);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
        return adq;
    }

    public List<Adquisicion> obtenerAdquisicionPorPre(Integer prePk, Integer monPk) {
        AdquisicionDAO dao = new AdquisicionDAO(em);
        try {
            return dao.obtenerAdquisicionPorPre(prePk, monPk);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    public List<Adquisicion> obtenerAdquisicionPorPre(Integer prePk) {
        AdquisicionDAO dao = new AdquisicionDAO(em);
        try {
            return dao.obtenerAdquisicionPorPre(prePk);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    public List<Adquisicion> obtenerAdquisicionPorPreProg(Integer progPk) {
        AdquisicionDAO dao = new AdquisicionDAO(em);
        try {
            return dao.obtenerAdquisicionPorPreProg(progPk);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    public void eliminarAdquisicion(Integer adqPk, Integer proyPk, Integer orgPk) {
        AdquisicionDAO adqDao = new AdquisicionDAO(em);

        if (!tienePagosAprobados(adqPk)) {
            try {
                Adquisicion a = adqDao.findById(Adquisicion.class, adqPk);
                adqDao.delete(a, du.getCodigoUsuario(),du.getOrigen());
                proyectosBean.guardarIndicadores(proyPk, orgPk);
                System.out.println("ELIMINAR ADQUISICION: "+ adqPk);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_ADQISICION_ELIMINAR);
                throw be;
            }
        } else {
            logger.log(Level.INFO, MensajesNegocio.ERROR_ADQISICION_ELIMINAR_PAGO_CONF);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ADQISICION_ELIMINAR_PAGO_CONF);
            throw be;
        }
    }

    public Set<Adquisicion> copiarProyAdquisicion(Set<Adquisicion> adquisicionSet, Presupuesto pre, Set<Entregables> entSet, int desfasajeDias) {
        if (adquisicionSet != null && pre != null) {
            Set<Adquisicion> result = new HashSet<>();

            Iterator<Adquisicion> iterator = adquisicionSet.iterator();
            while (iterator.hasNext()) {
                Adquisicion adq = iterator.next();

                Adquisicion nvaAdq = new Adquisicion();
                nvaAdq.setAdqFuente(adq.getAdqFuente());
                nvaAdq.setAdqMoneda(adq.getAdqMoneda());
                nvaAdq.setAdqNombre(adq.getAdqNombre());
                nvaAdq.setAdqPreFk(pre);
//                nvaAdq.setAdqProcCompra(adq.getAdqProcCompra());
                nvaAdq.setAdqProcedimientoCompra(adq.getAdqProcedimientoCompra());

                nvaAdq.setAdqIdGrpErpFk(null);
                nvaAdq.setAdqProvOrga(adq.getAdqProvOrga());
                nvaAdq.setAdqCompartida(adq.getAdqCompartida());
                nvaAdq.setSsUsuarioCompartida(adq.getSsUsuarioCompartida());
                
                nvaAdq.setPagosSet(pagosBean.copiarProyPagos(adq.getPagosSet(), nvaAdq, entSet, desfasajeDias));
                nvaAdq.setDevengadoList(devengadoBean.copiarProyDevengado(adq.getDevengadoList(), nvaAdq, desfasajeDias));
                
                result.add(nvaAdq);
            }
            return result;
        }
        return null;
    }

    public List<Adquisicion> obtenerAdquisicionPorProy(Integer proyPk) {
        if (proyPk != null) {
            AdquisicionDAO dao = new AdquisicionDAO(em);
            return dao.obtenerAdquisicionPorProy(proyPk);
        }
        return null;
    }

    /**
     * Retorna las adquisiciones que tienen devengados.
     *
     * @param proyPk
     * @return
     */
    public List<Adquisicion> obtenerAdqDevPorProy(Integer proyPk) {
        if (proyPk != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            MatchCriteriaTO criterioProy = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "adqPreFk.proyecto.proyPk", proyPk);
            criterios.add(criterioProy);

            MatchCriteriaTO criterioDev = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.NOT_EMPTY, "devengadoList", 1);
            criterios.add(criterioDev);

            CriteriaTO condicion = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));

            String[] orderBy = {};
            boolean[] asc = {};

            AdquisicionDAO dao = new AdquisicionDAO(em);

            try {
                return dao.findEntityByCriteria(Adquisicion.class, condicion, orderBy, asc, null, null);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_ADQISICION_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public boolean tienePagosAprobados(Integer adqPk) {
        if (adqPk != null) {
            MatchCriteriaTO criterioAdq = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "adqPk", adqPk);

            MatchCriteriaTO criterioPagAprob = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "pagosSet.pagConfirmar", Boolean.TRUE);

            CriteriaTO criteria = CriteriaTOUtils.createANDTO(criterioAdq, criterioPagAprob);

            AdquisicionDAO dao = new AdquisicionDAO(em);

            String[] orderBy = {};
            boolean[] asc = {};
            try {
                List<Adquisicion> listAdq = dao.findEntityByCriteria(Adquisicion.class, criteria, orderBy, asc, null, null);
                if (listAdq != null && !listAdq.isEmpty()) {
                    return true;
                }
            } catch (DAOGeneralException ex) {
                Logger.getLogger(AdquisicionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public Double costoEjecutado(Integer adqPk) {
        AdquisicionDAO dao = new AdquisicionDAO(em);
        return dao.obtenerACPorMoneda(adqPk);
    }

    public Double costoTotal(Integer adqPk) {
        AdquisicionDAO dao = new AdquisicionDAO(em);
        return dao.costoTotal(adqPk);
    }

    public Pagos obtenerUltimoPago(Integer adqPk){
        AdquisicionDAO adqDAO = new AdquisicionDAO(em);
        return adqDAO.obtenerUltimoPago(adqPk);
    }
}
