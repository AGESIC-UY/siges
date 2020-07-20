package com.sofis.business.ejbs;

import com.sofis.business.properties.LabelsEJB;
import com.sofis.data.daos.EstadosDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.codigueras.EstadosCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Proyectos;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "EstadosBean")
@LocalBean
public class EstadosBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(EstadosBean.class.getName());

    @EJB
    private ProyectosBean proyectosBean;
    @Inject
    private ConfiguracionBean configuracionBean;

    public Estados guardar(Estados est) {
        EstadosDAO dao = new EstadosDAO(em);

        try {
            est = dao.update(est);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ESTADO_GUARDAR);
            throw be;
        }

        return est;
    }

    public Estados obtenerEstadosPorId(Integer estPk) throws GeneralException {
        if (estPk != null) {
            EstadosDAO dao = new EstadosDAO(em);
            try {
                Estados resultado = dao.findById(Estados.class, estPk);
                return resultado;

            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                TechnicalException te = new TechnicalException(ex);
                te.addError(ex.getMessage());
                throw te;
            }
        }

        return null;
    }

    public Estados obtenerEstadosPorCodigo(String cod) throws GeneralException {
        if (!StringsUtils.isEmpty(cod)) {
            EstadosDAO dao = new EstadosDAO(em);
            try {
                List<Estados> resultList = dao.findByOneProperty(Estados.class, "estCodigo", cod);
                return (Estados) DAOUtils.obtenerSingleResult(resultList);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                TechnicalException te = new TechnicalException(ex);
                te.addError(ex.getMessage());
                throw te;
            }
        }

        return null;
    }

    public List<Estados> obtenerEstadosRequridosDoc(List<Integer> listEstPk) throws GeneralException {
        try {
            List<Estados> resultado = new ArrayList<>();

            for (Integer estPk : listEstPk) {
                Estados est = this.obtenerEstadosPorId(estPk);
                if (est != null) {
                    resultado.add(est);
                }
            }

            return resultado;

        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    /**
     * Retorna true si e1 es menor que e2 en cuanto al Orde de Proceso. Si el
     * Orden de uno de ellos es null, retorna false.
     *
     * @param e1
     * @param e2
     * @return
     */
    public boolean isOrdenProcesoMenor(Estados e1, Estados e2) {
        if (e1 != null && e2 != null
                && e1.getEstOrdenProceso() != null
                && e2.getEstOrdenProceso() != null) {
            return e1.getEstOrdenProceso() < e2.getEstOrdenProceso();
        } else {
            return false;
        }
    }

    public String obtenerEstadoColor(Integer fichaFk) {
        Proyectos p = proyectosBean.obtenerProyPorId(fichaFk);
        return obtenerEstadoColor(p.getProyEstFk(), p.getProyIndices().getProyindPeriodoInicio(), p.getProyIndices().getProyindPeriodoFin(), null, null, null, null, p.getProyOrgFk().getOrgPk());
    }

    /**
     * Retorna el color para el semaforo de estado(fase) según el estado en que
     * esté, el período y la fecha del día.
     *
     * @param est
     * @param fechaDesde
     * @param fechaHasta
     * @param semaforoInicioAmarillo
     * @param semaforoInicioRojo
     * @param semaforoPlanAmarillo
     * @param semaforoPlanRojo
     * @return
     */
    public String obtenerEstadoColor(Estados est, Date fechaDesde, Date fechaHasta, Integer semaforoInicioAmarillo, Integer semaforoInicioRojo, Integer semaforoPlanAmarillo, Integer semaforoPlanRojo, Integer orgPk) {
        if (est != null
                && (est.isEstado(Estados.ESTADOS.INICIO.estado_id)
                || est.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id))
                && fechaDesde != null && fechaHasta != null
                && DatesUtils.esMayor(fechaHasta, fechaDesde)) {
            Calendar cal = new GregorianCalendar();
            Calendar calDesde = new GregorianCalendar();
            calDesde.setTime(fechaDesde);
            Calendar calHasta = new GregorianCalendar();
            calHasta.setTime(fechaHasta);

            long difDias = (calHasta.getTimeInMillis() - calDesde.getTimeInMillis()) / (24 * 60 * 60 * 1000);
            long diasTranscurridos = (cal.getTimeInMillis() - calDesde.getTimeInMillis()) / (24 * 60 * 60 * 1000);
            
            /*
            *   09-05-2018 Nico: Cambio la asignación porque existe un caso en que divide entre cero
            */
            
            double porcAvance = (difDias > 0) ? diasTranscurridos * 100 / difDias : 0;

            Integer semaforoAmarillo = null;
            Integer semaforoRojo = null;

            if (est.isEstado(Estados.ESTADOS.INICIO.estado_id)) {
                if (semaforoInicioAmarillo == null || semaforoInicioRojo == null) {
                    Configuracion confAmarillo = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.ESTADO_INICIO_LIMITE_AMARILLO, orgPk);
                    Configuracion confRojo = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.ESTADO_INICIO_LIMITE_ROJO, orgPk);
                    semaforoAmarillo = confAmarillo != null ? Integer.valueOf(confAmarillo.getCnfValor()) : null;
                    semaforoRojo = confRojo != null ? Integer.valueOf(confRojo.getCnfValor()) : null;
                } else {
                    semaforoAmarillo = semaforoInicioAmarillo;
                    semaforoRojo = semaforoInicioRojo;
                }
            } else if (est.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id)) {
                if (semaforoPlanAmarillo == null || semaforoPlanRojo == null) {
                    Configuracion confAmarillo = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_AMARILLO, orgPk);
                    Configuracion confRojo = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_ROJO, orgPk);
                    semaforoAmarillo = confAmarillo != null ? Integer.valueOf(confAmarillo.getCnfValor()) : null;
                    semaforoRojo = confRojo != null ? Integer.valueOf(confRojo.getCnfValor()) : null;
                } else {
                    semaforoAmarillo = semaforoPlanAmarillo;
                    semaforoRojo = semaforoPlanRojo;
                }
            }

            if (semaforoAmarillo != null && semaforoRojo != null) {
                if (porcAvance >= semaforoAmarillo && porcAvance < semaforoRojo) {
                    return ConstantesEstandares.SEMAFORO_AMARILLO;
                } else if (porcAvance >= semaforoRojo) {
                    return ConstantesEstandares.SEMAFORO_ROJO;
                }
            }
        }
        return ConstantesEstandares.COLOR_TRANSPARENT;
    }

    public List<Estados> obtenerEstadosCombo() {
        List<Estados> list = new ArrayList<>();
        int[] estadosId = new int[]{0, 2, 3, 4, 5};
        for (int i = 0; i < estadosId.length; i++) {
            Estados est = obtenerEstadosPorId(estadosId[i]);
            if (est != null) {
                list.add(est);
            }
        }
        return list;
    }

    public String estadoStr(Integer estPk, Integer orgPK) {
        if (estPk != null) {
            return LabelsEJB.getValue("estado_" + estPk, orgPK);
        }
        return "";
    }

    public String estadoStr(Estados est, Integer orgPK) {
        if (est != null) {
            if (!StringsUtils.isEmpty(est.getEstLabel()) && est.getEstLabel().trim().length() > 0) {
                return LabelsEJB.getValue(est.getEstLabel(), orgPK);
            } else if (!StringsUtils.isEmpty(est.getEstCodigo()) && est.getEstCodigo().trim().length() > 0) {
                return LabelsEJB.getValue("estado_" + est.getEstCodigo(), orgPK);
            }
            return LabelsEJB.getValue("estado_" + est.getEstPk(), orgPK);
        }
        return "";
    }

    public List<Estados> obtenerEstados() {
        EstadosDAO dao = new EstadosDAO(em);
        try {
            return dao.findAll(Estados.class, "estCodigo");
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void controlarEstadosCodigos() {
        List<Estados> estadosList = obtenerEstados();
        for (Estados estado : estadosList) {
            if (StringsUtils.isEmpty(estado.getEstCodigo())) {
                switch (estado.getEstPk()) {
                    case 0:
                        estado.setEstCodigo(EstadosCodigos.NO_EXIGIDO);
                        break;
                    case 1:
                        estado.setEstCodigo(EstadosCodigos.PENDIENTE);
                        break;
                    case 2:
                        estado.setEstCodigo(EstadosCodigos.INICIO);
                        break;
                    case 3:
                        estado.setEstCodigo(EstadosCodigos.PLANIFICACION);
                        break;
                    case 4:
                        estado.setEstCodigo(EstadosCodigos.EJECUCION);
                        break;
                    case 5:
                        estado.setEstCodigo(EstadosCodigos.FINALIZADO);
                        break;
                    case 11:
                        estado.setEstCodigo(EstadosCodigos.PENDIENTE_PMOT);
                        break;
                    case 12:
                        estado.setEstCodigo(EstadosCodigos.PENDIENTE_PMOF);
                        break;
                    case 41:
                        estado.setEstCodigo(EstadosCodigos.SOLICITUD_FINALIZADO_PMOF);
                        break;
                    case 42:
                        estado.setEstCodigo(EstadosCodigos.SOLICITUD_FINALIZADO_PMOT);
                        break;
                    case 61:
                        estado.setEstCodigo(EstadosCodigos.SOLICITUD_CANCELAR_PMOT);
                        break;
                    default:
                        logger.log(Level.WARNING, "El estado id=" + estado.getEstPk() + " no tiene código y no se reconoce para agregarle uno.");
                        break;
                }
                logger.log(Level.INFO, "Se agrega el código:" + estado.getEstCodigo() + " al Estado " + estado.getEstPk());
                guardar(estado);
            }
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void controlarEstadosLabels() {
        List<Estados> estadosList = obtenerEstados();
        for (Estados estado : estadosList) {
            if (StringsUtils.isEmpty(estado.getEstLabel())
                    && !StringsUtils.isEmpty(estado.getEstCodigo())) {
                switch (estado.getEstCodigo()) {
                    case EstadosCodigos.NO_EXIGIDO:
                        estado.setEstLabel("estado_" + EstadosCodigos.NO_EXIGIDO);
                        break;
                    case EstadosCodigos.PENDIENTE:
                        estado.setEstLabel("estado_" + EstadosCodigos.PENDIENTE);
                        break;
                    case EstadosCodigos.INICIO:
                        estado.setEstLabel("estado_" + EstadosCodigos.INICIO);
                        break;
                    case EstadosCodigos.PLANIFICACION:
                        estado.setEstLabel("estado_" + EstadosCodigos.PLANIFICACION);
                        break;
                    case EstadosCodigos.EJECUCION:
                        estado.setEstLabel("estado_" + EstadosCodigos.EJECUCION);
                        break;
                    case EstadosCodigos.FINALIZADO:
                        estado.setEstLabel("estado_" + EstadosCodigos.FINALIZADO);
                        break;
                    case EstadosCodigos.PENDIENTE_PMOT:
                        estado.setEstLabel("estado_" + EstadosCodigos.PENDIENTE_PMOT);
                        break;
                    case EstadosCodigos.PENDIENTE_PMOF:
                        estado.setEstLabel("estado_" + EstadosCodigos.PENDIENTE_PMOF);
                        break;
                    case EstadosCodigos.SOLICITUD_FINALIZADO_PMOF:
                        estado.setEstLabel("estado_" + EstadosCodigos.SOLICITUD_FINALIZADO_PMOF);
                        break;
                    case EstadosCodigos.SOLICITUD_FINALIZADO_PMOT:
                        estado.setEstLabel("estado_" + EstadosCodigos.SOLICITUD_FINALIZADO_PMOT);
                        break;
                    case EstadosCodigos.SOLICITUD_CANCELAR_PMOT:
                        estado.setEstLabel("estado_" + EstadosCodigos.SOLICITUD_CANCELAR_PMOT);
                        break;
                    default:
                        logger.log(Level.WARNING, "El estado id=" + estado.getEstPk() + " no tiene código y no se reconoce para agregarle uno.");
                        break;
                }
                logger.log(Level.INFO, "Se agrega el código:" + estado.getEstCodigo() + " al Estado " + estado.getEstPk());
                guardar(estado);
            }
        }
    }

    public void controlarEstadosFaltantes() {
        controlarEstadosCodigos();
        controlarEstadosLabels();

        List<Estados> estadosList = obtenerEstados();
        Map<String, Estados> estadosMap = new HashMap<>();
        if (estadosList != null) {
            for (Estados estado : estadosList) {
                estadosMap.put(estado.getEstCodigo(), estado);
            }
        }

        Estados[] estadoArr = new Estados[]{
            new Estados(null, EstadosCodigos.NO_EXIGIDO, "No Exigido", "estado_NoExigido", null),
            new Estados(null, EstadosCodigos.PENDIENTE, "Pendiente", "estado_Pendiente", 1),
            new Estados(null, EstadosCodigos.INICIO, "Inicio", "estado_Inicio", 2),
            new Estados(null, EstadosCodigos.PLANIFICACION, "Planificacion", "estado_Planificacion", 3),
            new Estados(null, EstadosCodigos.EJECUCION, "Ejecucion", "estado_Ejecucion", 4),
            new Estados(null, EstadosCodigos.FINALIZADO, "Finalizado", "estado_Finalizado", 5),
            new Estados(null, EstadosCodigos.PENDIENTE_PMOT, "Pendiente PMO T.", "estado_pendiente_pmot", null),
            new Estados(null, EstadosCodigos.PENDIENTE_PMOF, "Pendiente PMO F.", "estado_pendiente_pmof", null),
            new Estados(null, EstadosCodigos.SOLICITUD_FINALIZADO_PMOF, "Solicitud Finalizado PMO F.", "estado_sol_finalizado_pmof", null),
            new Estados(null, EstadosCodigos.SOLICITUD_FINALIZADO_PMOT, "Solicitud Finalizado PMO T.", "estado_sol_finalizado_pmot", null),
            new Estados(null, EstadosCodigos.SOLICITUD_CANCELAR_PMOT, "Solicitud Cancelar PMO T.", "estado_solicitud_cierre", null)
        };

        for (Estados estado : estadoArr) {
            if (!estadosMap.containsKey(estado.getEstCodigo())) {
                guardar(estado);
                logger.log(Level.INFO, StringsUtils.concat("Se agregó el estado '", estado.getEstCodigo()), "'");
            }
        }
    }
}
