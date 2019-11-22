package com.sofis.business.timer;

import com.sofis.business.ejbs.NotificacionEnvioBean;
import com.sofis.business.properties.ConfigApp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author MG
 */
@Named
@Stateless(name = "TimerScheduler")
@LocalBean
public class TimerSchedulerBean implements ITimerScheduler {

    private static final Logger logger = Logger.getLogger(TimerSchedulerBean.class.getName());
    /**
     * Injección del TimerService
     */
    @Resource
    TimerService timerService;
    @Inject
    NotificacionEnvioBean notificacionEnvioBean;
    /**
     * Hora de ejecución: 23 horas
     */
    private static int START_HOUR = 0;
    /**
     * Minutos de ejecución: 0 minutos
     */
    private static int START_MINUTES = 0;
    /**
     * Segundos de ejecución: 00
     */
    private static int START_SECONDS = 0;
    /**
     * Intervalo de la ejecución: 3600 = 1 hora, 86400 = 1 día.
     */
//    private static final int INTERVAL_IN_SECONDS = 86400; 
    private static int INTERVAL_IN_MINUTES = 60;

    public TimerSchedulerBean() {
        String hora = ConfigApp.getValue("timer_schedule_hora_inicio");
        String minutos = ConfigApp.getValue("timer_schedule_minutos_inicio");
        String intervalo = ConfigApp.getValue("timer_schedule_intervalo_minutos");
        
        try {
            if (hora != null) {
                START_HOUR = Integer.valueOf(hora);
            }
            if (minutos != null) {
                START_MINUTES = Integer.valueOf(minutos);
            }
            if (intervalo != null) {
                INTERVAL_IN_MINUTES = Integer.valueOf(intervalo);
            }
        } catch (NumberFormatException nfe) {
            logger.log(Level.CONFIG, "Error al obtener datos para el Timer Schedule.", nfe);
        }
    }

    /**
     *
     * Levanta el servicio *
     */
    @Override
    public void startUpTimer() {
        logger.info("startUpTimer - alarm scheduler service is active.");

        shutDownTimer();

        Calendar initialExpiration = Calendar.getInstance();
        initialExpiration.set(Calendar.HOUR_OF_DAY, START_HOUR);
        initialExpiration.set(Calendar.MINUTE, START_MINUTES);
        initialExpiration.set(Calendar.SECOND, START_SECONDS);

        long intervalDuration = new Integer(INTERVAL_IN_MINUTES).longValue() * 1000 * 60;

        logger.info("startUpTimer - create new timer service at \"" + initialExpiration.getTime() + "\", with \"" + intervalDuration + "\" interval in milis.");

        timerService.createTimer(initialExpiration.getTime(), intervalDuration, null);
    }

    /**
     *
     * Para el servicio *
     */
    @Override
    public void shutDownTimer() {
        Collection<Timer> timers = timerService.getTimers();

        logger.info("shutDownTimer - existing timers? " + timers);

        if (timers != null) {
            for (Iterator iterator = timers.iterator(); iterator.hasNext();) {
                Timer t = (Timer) iterator.next();
                t.cancel();
                logger.info("shutDownTimer - timer \"" + t + "\" canceled.");
            }
        }
    }

    /**
     *
     * método callback que se invocará al terminar el intervalo definido
     *
     */
    @Timeout
    public void execute(Timer timer) {
        logger.info("Timer Scheduler executing - " + timer.getInfo());

        // TODO: implementar la lógica del proceso de alarmas.
        logger.info("Timer enviar notificaciones");
        notificacionEnvioBean.enviarNotificaciones();
        logger.info("Timer Fin enviar notificaciones");
    }
}
