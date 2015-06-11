package com.sofis.business.ejbs;

import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.business.properties.LabelsEJB;
import com.sofis.data.daos.EstadosDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Proyectos;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Stateless(name = "EstadosBean")
@LocalBean
public class EstadosBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    @EJB
    private ProyectosBean proyectosBean;
    @Inject
    private ConfiguracionBean configuracionBean;

    public Estados obtenerEstadosPorId(Integer estPk) throws GeneralException {
        if (estPk != null) {
            EstadosDAO estadosDao = new EstadosDAO(em);
            try {
                Estados resultado = estadosDao.findById(Estados.class, estPk);
                return resultado;

            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                TechnicalException te = new TechnicalException();
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
            TechnicalException te = new TechnicalException();
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
            double porcAvance = diasTranscurridos * 100 / difDias;

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

    public String estadoStr(Integer estPk) {
        if (estPk != null) {
            return LabelsEJB.getValue("estado_" + estPk);
        }
        return "";
    }

    public String estadoStr(Estados est) {
        if (est != null) {
            if (!StringsUtils.isEmpty(est.getEstLabel()) && est.getEstLabel().trim().length() > 0) {
                return LabelsEJB.getValue(est.getEstLabel());
            }else if(!StringsUtils.isEmpty(est.getEstCodigo()) && est.getEstCodigo().trim().length() > 0){
                return LabelsEJB.getValue("estado_" + est.getEstCodigo());
            }
            return LabelsEJB.getValue("estado_" + est.getEstPk());
        }
        return "";
    }
}
