package com.sofis.business.ejbs;

import com.sofis.business.utils.NumbersUtils;
import com.sofis.business.validations.RiesgosValidaciones;
import com.sofis.data.daos.RiesgosDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.Riesgos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
@Stateless(name = "RiesgosBean")
@LocalBean
public class RiesgosBean {

    @Inject
    private DatosUsuario du;
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    @Inject
    private ProyectosBean proyectosBean;
    @Inject
    private ConfiguracionBean configuracionBean;
    @EJB
    private NotificacionEnvioBean notificacionEnvioBean;
    
    
    //private String usuario;
    //private String origen;
    
    @PostConstruct
    public void init(){
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }
    

    /**
     * Guardar un riesgo.
     *
     * @param riesgo
     * @param orgPk Id del Organismo. Si este es null no se actualizan los
     * indicadores.
     * @return Riesgos
     */
    public Riesgos guardar(Riesgos riesgo, Integer orgPk) {

        riesgo.setRiskFechaActualizacion(new Date());
        riesgo.loadExposicion();

        RiesgosValidaciones.validar(riesgo);
        RiesgosDAO dao = new RiesgosDAO(em);

        try {
            riesgo = dao.update(riesgo, du.getCodigoUsuario(),du.getOrigen());

            if (orgPk != null) {
                proyectosBean.guardarIndicadores(riesgo.getRiskProyFk().getProyPk(), orgPk);
                nuevoRiesgoAlto(riesgo.getRiskProyFk(), orgPk, null);
            }

        } catch (BusinessException | TechnicalException be) {
            logger.logp(Level.SEVERE, RiesgosBean.class.getName(), "guardar", be.getMessage(), be);
            throw be;
        } catch (Exception ex) {
            logger.logp(Level.SEVERE, RiesgosBean.class.getName(), "guardar", ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }

        return riesgo;
    }

    public void nuevoRiesgoAlto(Proyectos proy, Integer orgPk, Map<String, String> confMap) {
        notificacionEnvioBean.nuevoRiesgoAlto(proy, orgPk, confMap);
    }

    /**
     * Consulta si la notificación para dicho código y proyecto ya fue enviada.
     *
     * @param codNot
     * @param proyPk
     * @return boolean true si fue enviada.
     */
    public boolean fueNotificado(String codNot, Integer proyPk) {
        return notificacionEnvioBean.fueNotificado(codNot, proyPk);
    }

    public Riesgos obtenerRiesgosPorId(Integer id) {
        RiesgosDAO rDao = new RiesgosDAO(em);
        try {
            return rDao.findById(Riesgos.class, id);
        } catch (DAOGeneralException ex) {
            logger.logp(Level.SEVERE, RiesgosBean.class.getName(), "obtenerRiesgosPorId", ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    /**
     * Retorna una lista de Riesgos para utilizar en el resumen de la ficha.
     *
     * @param list Lista con los Riesgos a filtrar.
     * @param size Tamanio maximo a retornar.
     * @return Lista de Riesgos.
     */
    public List<Riesgos> obtenerExposicionResumen(Integer proyPk, int size) {
        RiesgosDAO dao = new RiesgosDAO(em);
        return dao.obtenerResumenRiesgos(proyPk, size);
    }

    public Date obtenerDateUltimaActualizacion(Integer fichaFk) {
        RiesgosDAO dao = new RiesgosDAO(em);
        return dao.obtenerDateUltimaActualizacion(fichaFk);
    }

    public String obtenerColorUltimaActualizacion(Date riesgoActualizacion, Integer orgPk, Map<String, String> configs) {
        if (riesgoActualizacion != null) {
            Integer tAmarillo;
            Integer tRojo;

            if (configs != null) {
                String sConfAmarillo = configs.get(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_AMARILLO);
                String sConfRojo = configs.get(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_ROJO);
                try {
                    tAmarillo = Integer.valueOf(sConfAmarillo);
                    tRojo = Integer.valueOf(sConfRojo);
                } catch (Exception ex) {
                    tAmarillo = 0;
                    tRojo = 0;
                }
            } else {
                Configuracion cConfAmarillo = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_AMARILLO, orgPk);
                Configuracion cConfRojo = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_ROJO, orgPk);
                try {
                    tAmarillo = Integer.valueOf(cConfAmarillo.getCnfValor());
                    tRojo = Integer.valueOf(cConfRojo.getCnfValor());
                } catch (Exception ex) {
                    tAmarillo = 0;
                    tRojo = 0;
                }
            }

            Calendar calRiesgoAct = Calendar.getInstance();
            calRiesgoAct.setTime(riesgoActualizacion);
            Calendar calAmarillo = Calendar.getInstance();
            calAmarillo.setTimeInMillis(calRiesgoAct.getTimeInMillis());
            calAmarillo.add(Calendar.DATE, tAmarillo);
            Calendar calRojo = Calendar.getInstance();
            calRojo.setTimeInMillis(calRiesgoAct.getTimeInMillis());
            calRojo.add(Calendar.DATE, tRojo);

            Calendar calNow = Calendar.getInstance();
            if (calNow.before(calAmarillo)) {
                return ConstantesEstandares.SEMAFORO_VERDE;
            }
            if (calNow.equals(calAmarillo) || (calNow.after(calAmarillo) && calNow.before(calRojo))) {
                return ConstantesEstandares.SEMAFORO_AMARILLO;
            }
            if (calNow.equals(calRojo) || calNow.after(calRojo)) {
                return ConstantesEstandares.SEMAFORO_ROJO;
            }
        }

        return ConstantesEstandares.COLOR_TRANSPARENT;
    }

    public Double obtenerExposicionRiesgoPrograma(Set<Proyectos> proyectos) {
        if (proyectos != null) {

            Double expo = 0d;
            int count = 0;
            for (Proyectos proy : proyectos) {
                boolean estado = proy.isEstado(Estados.ESTADOS.INICIO.estado_id)
                        || proy.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id)
                        || proy.isEstado(Estados.ESTADOS.EJECUCION.estado_id);
                if (proy.isActivo() && estado) {
                    List<Riesgos> listRiesgos = this.obtenerRiesgosNoSuperados(proy.getProyPk());
                    if (listRiesgos != null && !listRiesgos.isEmpty()) {
                        for (Riesgos riesgo : listRiesgos) {
                            if (riesgo.getExposicion() > 0) {
                                expo += riesgo.getExposicion() * proy.getProyPeso();
                                count += proy.getProyPeso();
                            }
                        }
                    }
                }
            }
            return count == 0 ? null : expo / count;
        }
        return null;
    }

    public Double obtenerExposicionRiesgo(Integer proyPk) {
        if (proyPk != null) {
            List<Riesgos> listRiesgos = this.obtenerRiesgosNoSuperados(proyPk);
            return obtenerExposicionRiesgo(listRiesgos);
        }
        return null;
    }

    public Double obtenerExposicionRiesgo(List<Riesgos> listRiesgos) {
        if (listRiesgos != null && !listRiesgos.isEmpty()) {
            Double expo = 0d;
            int count = 0;
            for (Riesgos riesgo : listRiesgos) {
                if (riesgo.getExposicion() > 0) {
                    expo += riesgo.getExposicion();
                    count++;
                }
            }
            return expo / count;
        }
        return null;
    }

    public Double obtenerExposicionRiesgoPrograma(Integer progPk) {

        RiesgosDAO dao = new RiesgosDAO(em);
        try {
            Double d = dao.obtenerExposicionRiesgoPrograma(progPk);
            if (d != null) {
                return d;
            } else {
                return null;
            }

        } catch (BusinessException ex) {
            logger.logp(Level.SEVERE, RiesgosBean.class.getName(), "obtenerRiesgosNoSuperados", ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public String obtenerFechaLimiteRiesgoColor(Date fechaLimite, Integer orgPk) {
        if (fechaLimite != null) {

            Configuracion confCantDias = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_FECHA_LIMITE_CANT_DIAS, orgPk);

            int cantDias = 5;
            if (confCantDias != null) {
            }
            Date nowDate = new Date();
            Calendar nowDatec = new GregorianCalendar();
            nowDatec.setTime(nowDate);
            nowDatec.add(Calendar.DAY_OF_MONTH, cantDias);

            if (fechaLimite.getTime() < nowDate.getTime()) {
                return ConstantesEstandares.SEMAFORO_ROJO;
            }

            if (fechaLimite.getTime() >= nowDate.getTime() && fechaLimite.getTime() <= nowDatec.getTimeInMillis()) {
                return ConstantesEstandares.SEMAFORO_AMARILLO;
            }

            if (fechaLimite.getTime() > nowDate.getTime()) {
                return ConstantesEstandares.SEMAFORO_VERDE;
            }
        }

        return ConstantesEstandares.COLOR_TRANSPARENT;
    }

    public String obtenerExposicionRiesgoColor(Double exposicionRiesgo, Integer orgPk, Map<String, String> configs) {
        if (exposicionRiesgo != null) {
            Double confAmarillo;
            Double confRojo;
            if (configs != null) {
                String sConfAmarillo = configs.get(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_AMARILLO);
                String sConfRojo = configs.get(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_ROJO);
                try {
                    confAmarillo = Double.valueOf(sConfAmarillo);
                    confRojo = Double.valueOf(sConfRojo);
                } catch (Exception ex) {
                    confAmarillo = 0d;
                    confRojo = 0d;
                }
            } else {
                Configuracion cConfAmarillo = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_AMARILLO, orgPk);
                Configuracion cConfRojo = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_ROJO, orgPk);
                try {
                    confAmarillo = Double.valueOf(cConfAmarillo.getCnfValor());
                    confRojo = Double.valueOf(cConfRojo.getCnfValor());
                } catch (Exception ex) {
                    confAmarillo = 0d;
                    confRojo = 0d;
                }
            }

            Double exposicionRiesgoRound = NumbersUtils.redondearDecimales(exposicionRiesgo);

            if (exposicionRiesgoRound <= confAmarillo) {
                return ConstantesEstandares.SEMAFORO_VERDE;
            }
            if (exposicionRiesgoRound > confAmarillo
                    && exposicionRiesgoRound < confRojo) {
                return ConstantesEstandares.SEMAFORO_AMARILLO;
            }
            if (exposicionRiesgoRound >= confRojo) {
                return ConstantesEstandares.SEMAFORO_ROJO;
            }
        }

        return ConstantesEstandares.COLOR_TRANSPARENT;
    }

    public void eliminarRiesgo(Integer riskPk, Integer fichaFk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) throws GeneralException {
        logger.log(Level.INFO, "Eliminar el Riesgo con id:{0}", riskPk);
        RiesgosDAO dao = new RiesgosDAO(em);
        try {
            Riesgos riesgo = this.obtenerRiesgosPorId(riskPk);
            dao.delete(riesgo);
            proyectosBean.guardarIndicadores(riesgo.getRiskProyFk().getProyPk(), orgPk);
            nuevoRiesgoAlto(riesgo.getRiskProyFk(), orgPk, null);

        } catch (BusinessException | DAOGeneralException ex) {
            logger.logp(Level.SEVERE, RiesgosBean.class.getName(), "delete", ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public Riesgos superarRiesgo(Integer riskPk, Integer usuId, Integer orgPk) {
        Riesgos r = obtenerRiesgosPorId(riskPk);
        r.setRiskSuperado(true);
        r.setRiskUsuarioSuperadoFk(usuId);
        r.setRiskFechaSuperado(new Date());
        r = this.guardar(r, orgPk);

        return r;
    }

    public List<Riesgos> obtenerRiesgosNoSuperados(Integer proyPk) {
        RiesgosDAO dao = new RiesgosDAO(em);
        try {
            return dao.obtenerRiesgosNoSuperados(proyPk);

        } catch (BusinessException ex) {
            logger.logp(Level.SEVERE, RiesgosBean.class.getName(), "obtenerRiesgosNoSuperados", ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public Integer obtenerCantRiesgosAltosPrograma(Set<Proyectos> proyectos, Integer orgPk) {
        if (proyectos != null) {
            int cant = 0;
            for (Proyectos proy : proyectos) {
                boolean estado = proy.isEstado(Estados.ESTADOS.INICIO.estado_id)
                        || proy.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id)
                        || proy.isEstado(Estados.ESTADOS.EJECUCION.estado_id);
                if (proy.isActivo() && estado) {
                    cant += obtenerCantRiesgosAltos(proy.getProyPk(), orgPk);
                }
            }
            return cant;
        }
        return null;
    }

    public Integer obtenerCantRiesgosAltosPrograma(Integer progPk, Integer orgPk) {
        RiesgosDAO dao = new RiesgosDAO(em);
        try {
            String confRojo = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_ROJO, orgPk);
            double semaforoRojo = Double.valueOf(confRojo);
            Long l = dao.obtenerCantRiesgosAltosPrograma(progPk, semaforoRojo);
            if (l != null) {
                return l.intValue();
            } else {
                return 0;
            }

        } catch (BusinessException ex) {
            logger.logp(Level.SEVERE, RiesgosBean.class.getName(), "obtenerCantRiesgosAltosPrograma", ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }

    }

    public Integer obtenerCantRiesgosAltos(Integer proyPk, Integer orgPk) {
        List<Riesgos> listRiesgos = obtenerRiesgosPorProyecto(proyPk);
        return obtenerCantRiesgosAltos(listRiesgos, orgPk);
    }

    public Integer obtenerCantRiesgosAltos(List<Riesgos> listRiesgos, Integer orgPk) {
        int cantAltos = 0;
        if (listRiesgos != null && !listRiesgos.isEmpty() && orgPk != null) {
            String confRojo = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_ROJO, orgPk);
            double semaforoRojo = Double.valueOf(confRojo);
            for (Riesgos r : listRiesgos) {
                if (!(r.getRiskSuperado() != null && r.getRiskSuperado())
                        && NumbersUtils.redondearDecimales(r.getExposicion()) >= semaforoRojo) {
                    cantAltos++;
                }
            }
        }
        return cantAltos;
    }

    public List<Riesgos> obtenerRiesgosPorProyecto(Integer proyPk) {
        RiesgosDAO dao = new RiesgosDAO(em);
        try {
            return dao.obtenerRiesgosPorProyecto(proyPk);

        } catch (BusinessException ex) {
            logger.logp(Level.SEVERE, RiesgosBean.class.getName(), "obtenerRiesgosPorProyecto", ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public Date obtenerDateUltimaActualizacionPrograma(Set<Proyectos> proyectos) {
        if (proyectos != null && !proyectos.isEmpty()) {
            Date d = null;
            for (Proyectos p : proyectos) {
                if (p.isActivo()) {
                    Date d2 = obtenerDateUltimaActualizacion(p.getProyPk());
                    if (DatesUtils.esMayor(d, d2)) {
                        d = d2;
                    }
                }
            }
            return d;
        }
        return null;
    }

    public Date obtenerDateUltimaActualizacionPrograma(Integer progPk) {
        RiesgosDAO dao = new RiesgosDAO(em);
        try {
            return dao.obtenerDateUltimaActualizacionPrograma(progPk);

        } catch (BusinessException ex) {
            logger.logp(Level.SEVERE, RiesgosBean.class.getName(), "obtenerDateUltimaActualizacionPrograma", ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public List<Riesgos> copiarProyRiesgos(List<Riesgos> listRiesgos, Proyectos proy, int desfasajeDias) {
        if (listRiesgos != null && proy != null) {
            List<Riesgos> result = new ArrayList<>();

            Iterator<Riesgos> iterator = listRiesgos.iterator();
            while (iterator.hasNext()) {
                Riesgos riesgo = iterator.next();
                Riesgos nvoRiesgo = new Riesgos();
                nvoRiesgo.setExposicion(riesgo.getExposicion());
                nvoRiesgo.setRiskContingencia(riesgo.getRiskContingencia());
                nvoRiesgo.setRiskDisparador(riesgo.getRiskDisparador());
                nvoRiesgo.setRiskEfecto(riesgo.getRiskEfecto());
                nvoRiesgo.setRiskEstrategia(riesgo.getRiskEstrategia());
                nvoRiesgo.setRiskFechaActualizacion(new Date());
                nvoRiesgo.setRiskImpacto(riesgo.getRiskImpacto());
                nvoRiesgo.setRiskNombre(riesgo.getRiskNombre());
                nvoRiesgo.setRiskProbabilidad(riesgo.getRiskProbabilidad());
                nvoRiesgo.setRiskProyFk(proy);
                nvoRiesgo.setRiskSuperado(Boolean.FALSE);
                nvoRiesgo.setRiskUsuarioSuperadoFk(null);

                if (riesgo.getRiskFechaLimite() != null) {
                    Date date = DatesUtils.incrementarDias(riesgo.getRiskFechaLimite(), desfasajeDias);
                    nvoRiesgo.setRiskFechaLimite(date);
                }

                result.add(nvoRiesgo);
            }
            return result;
        }
        return null;
    }

    public Date obtenerPrimeraFecha(Collection<Riesgos> riesgosSet) {
        Date result = null;
        if (CollectionsUtils.isNotEmpty(riesgosSet)) {
            for (Riesgos r : riesgosSet) {
                if (r.getRiskFechaActualizacion() != null
                        && (result == null || DatesUtils.esMayor(result, r.getRiskFechaActualizacion()))) {
                    result = r.getRiskFechaActualizacion();

                }
                if (r.getRiskFechaLimite() != null
                        && (result == null || DatesUtils.esMayor(result, r.getRiskFechaLimite()))) {
                    result = r.getRiskFechaLimite();
                }
            }
        }
        return result;
    }

    public Date obtenerUltimaFecha(Collection<Riesgos> riesgosSet) {
        Date result = null;
        if (CollectionsUtils.isNotEmpty(riesgosSet)) {
            for (Riesgos r : riesgosSet) {
                if (r.getRiskFechaActualizacion() != null
                        && (result == null || DatesUtils.esMayor(r.getRiskFechaActualizacion(), result))) {
                    result = r.getRiskFechaActualizacion();
                }
                if (r.getRiskFechaLimite() != null
                        && (result == null || DatesUtils.esMayor(r.getRiskFechaLimite(), result))) {
                    result = r.getRiskFechaLimite();
                }
            }
        }
        return result;
    }
    
    
   /**
     * Quita el entregable aportado a los riesgos que lo tengan asociado.
     *
     * @param entPk
     */
    public void quitarEntregable(Integer entPk) {
        RiesgosDAO dao = new RiesgosDAO(em);
        try {
            dao.quitarEntregable(entPk);
        } catch (GeneralException e) {
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_RIESGO);
            throw be;
        }
    } 
   
}
