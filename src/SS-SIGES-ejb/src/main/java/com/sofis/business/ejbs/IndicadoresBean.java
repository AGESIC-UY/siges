package com.sofis.business.ejbs;

import com.sofis.business.utils.EJBUtils;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.SsUsuario;
import com.sofis.generico.utils.generalutils.DatesUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Named
@Stateless(name = "indicadoresBean")
@LocalBean
public class IndicadoresBean {

    private static final Logger logger = Logger.getLogger(IndicadoresBean.class.getName());
//    private static final String HORA_EJECUCION = "0,2";
    private static final String HORA_EJECUCION = "0";
    private static final String MINUTOS_EJECUCION = "5";
    private static final long TRANSACTION_TIMEOUT_MINUTES = 240;

    private static Semaphore MUTEX = new Semaphore(1, true);

    @Inject
    private OrganismoBean organismoBean;
    @Inject
    private ProyectosBean proyectosBean;
    @Inject
    private ProgramasBean programasBean;
    @Inject
    private ConfiguracionBean configuracionBean;
    @Inject
    private ProyIndicesBean proyIndicesBean;
    @Inject
    private DatosUsuario du;
    @Inject
    private SsUsuarioBean usuarioBean;

    private Integer countProy;
    private Integer totalProy;
    private Integer countProg;
    private Integer totalProg;
    private Integer progressProy;
    private Integer progressProg;

    private static final Object MUTEX_TIMER = new Object();

    //private String usuario;
    //private String origen;
    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    @Schedule(hour = HORA_EJECUCION, minute = MINUTOS_EJECUCION, info = "Schedule recalcular indicadores Siges.")
//    @TransactionTimeout(value = TRANSACTION_TIMEOUT_MINUTES, unit = TimeUnit.MINUTES)
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void scheduleRecalcularIndicadores() {
        synchronized (MUTEX_TIMER) {
            recalcularIndicadores(null, null);
        }
    }

//    @TransactionTimeout(value = TRANSACTION_TIMEOUT_MINUTES, unit = TimeUnit.MINUTES)
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void recalcularIndicadores(Integer orgPk, Boolean forzar) {

        SsUsuario usuario = null;
        String codigo = null;
        String origen = null;
        try {
            MUTEX.acquire();
            codigo = du.getCodigoUsuario();
            if (codigo != null) {
                usuario = usuarioBean.obtenerSsUsuarioPorCodigo(codigo);
            }
            origen = origen;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            MUTEX.release();
        }

        List<Organismos> listOrg = new ArrayList<>();
        if (orgPk != null) {
            listOrg.add(organismoBean.obtenerOrgPorId(orgPk, false));
        } else {
            listOrg = organismoBean.obtenerTodos();
        }

        for (Organismos org : listOrg) {
            
            /**
             * PARA DEBUG
             */
//            if(org.getOrgPk() != 6){
//                continue;
//            }
            
            logger.log(Level.INFO, "-- Recalculando Indices Organismo: {0}", org.getOrgNombre());
            Integer orgaPk = org.getOrgPk();

            logger.log(Level.INFO, "Buscando Proyectos...{0}", orgaPk);
            List<Integer> idsProy;
            
            String incluirFinalizados = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.INCLUIR_CALCULAR_FINALIZADOS, orgPk);
            
            if (incluirFinalizados.equals("true")){
                idsProy = proyectosBean.obtenerIdsProyPorOrg(orgaPk, true);
            } else {
                idsProy = proyectosBean.obtenerIdsProyPorOrgNoFinalizado(orgaPk);
            }
            totalProy = idsProy.size();
            logger.log(Level.INFO, "Total proyectos:{0}", totalProy);
            countProy = 0;

            String[] codigos = new String[]{
                ConfiguracionCodigos.ESTADO_INICIO_LIMITE_AMARILLO,
                ConfiguracionCodigos.ESTADO_INICIO_LIMITE_ROJO,
                ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_AMARILLO,
                ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_ROJO};
            Map<String, Configuracion> confs = configuracionBean.obtenerCnfPorCodigoYOrg(orgPk, codigos);

            for (Integer proyPk : idsProy) {
                try {

                    Date ultima = forzar != null && forzar ? null : proyIndicesBean.ultimaActualizacion(proyPk);
                    if (ultima == null || DatesUtils.esMayor(new Date(), ultima)) {

                        proyectosBean.controlarEntregables(proyPk, false);
                        proyectosBean.flush();
                        proyectosBean.controlarProdAcumulados(proyPk, false);
                        proyectosBean.flush();
                        proyectosBean.guardarIndicadoresSimple(proyPk, false, true, orgaPk, confs, false);
                        proyectosBean.flush();
                        countProy++;
                        progressProy = countProy * 100 / totalProy;
                        logger.log(Level.INFO, "Proyecto ({0}) calculcar Ind: {1}/{2} ({3}%)", new Object[]{proyPk, countProy, totalProy, progressProy});

                    } else {
                        countProy++;
                        logger.log(Level.INFO, "Proyecto ({0}) ya fue procesado el d\u00eda de hoy.", proyPk);
                    }

                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Proyecto ({0}) calculcar Ind: {1}/{2} ({3}%) [ERROR]", new Object[]{proyPk, countProy, totalProy, progressProy});
                    Logger.getLogger(IndicadoresBean.class.getName()).log(Level.FINEST, null, ex);
                }
            }

            logger.log(Level.INFO, "Buscando Programas...{0}", orgaPk);
            List<Integer> programasIds = programasBean.obtenerTodosIdsPorOrg(orgaPk);
            totalProg = programasIds.size();
            logger.log(Level.INFO, "Total programas:{0}", totalProg);
            countProg = 0;

            for (Integer progPk : programasIds) {
                try {

//                    Integer progPk = prog.getProgPk();
                    
                    programasBean.actualizarProgramaPorProyectos(progPk, usuario, du.getOrigen());
                    programasBean.flush();
                    programasBean.guardarIndicadoresSimple(progPk, orgaPk);
                    programasBean.flush();
                    countProg++;
                    progressProg = countProg * 100 / totalProg;
                    logger.log(Level.INFO, "Programa calculcar Ind({0}): {1}/{2} ({3}%)", new Object[]{progPk, countProg, totalProg, progressProg});
//
                } catch (Exception ex) {
                    logger.log(Level.INFO, "Programa calculcar Ind({0}): {1}/{2} ({3}%) [ERROR]", new Object[]{progPk, countProg, totalProg, progressProg});
                    Logger.getLogger(IndicadoresBean.class.getName()).log(Level.FINEST, null, ex);
                }
            }

            try {
                System.gc();
                logger.log(Level.INFO, "System.gc() [" + org.getOrgPk() + "]");
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "ERROR AL EJECUTAR GC LUEGO DEL SCHEDULE", ex);
            }

        }

//        Utils.executeGarbageCollector();
        try {
            System.gc();
            logger.log(Level.INFO, "FINALIZADO EL SCHEDULE: System.gc()");
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "ERROR AL EJECUTAR GC LUEGO DEL SCHEDULE", ex);
        }
    }
}
