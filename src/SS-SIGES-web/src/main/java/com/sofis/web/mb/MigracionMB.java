package com.sofis.web.mb;

import com.sofis.business.ejbs.NotificacionEnvioBean;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.SsUsuario;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.IndicadoresDelegate;
import com.sofis.web.delegates.OrganismoDelegate;
import com.sofis.web.delegates.ProgramasDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.genericos.constantes.ConstantesNavegacion;
import com.sofis.web.listener.SessionCounterListener;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "migracionMB")
@ViewScoped
public class MigracionMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(MigracionMB.class.getName());

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private ProgramasDelegate programaDelegate;
    @Inject
    private ProyectosDelegate proyectoDelegate;
    @Inject
    private ConfiguracionDelegate configuracionDelegate;
    @Inject
    private OrganismoDelegate organismoDelegate;
    @Inject
    private IndicadoresDelegate indicadoresDelegate;
    @Inject
    private NotificacionEnvioBean notificacionEnvBean;

    private Boolean segundoPlano;
    private Boolean resetLineaBase;
    private Integer countProy;
    private Integer totalProy;
    private Integer countProg;
    private Integer totalProg;
    private Integer progressProy;
    private Integer progressProg;
//    private static String GROUP_NAME = "everyone";
//    private PortableRenderer portableRenderer;
    private Thread calcularProyectosThread;

    public MigracionMB() {
//        PushRenderer.addCurrentView(GROUP_NAME);
//        portableRenderer = PushRenderer.getPortableRenderer();
    }

    public String salir() {
        if (calcularProyectosThread != null && calcularProyectosThread.isAlive()) {
            calcularProyectosThread.interrupt();
        }
        return ConstantesNavegacion.IR_A_INICIO;
    }

    public String calcularTodosOrg() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                indicadoresDelegate.recalcularIndicadores(null, true);
            }
        });
        thread.start();

        JSFUtils.agregarMsgInfo(Labels.getValue("migracion_calc_ind_org"));
        return ConstantesNavegacion.IR_A_INICIO;
    }

    public String calcular() {
        controlarDatosFaltantes();
        Integer orgPk = inicioMB.getOrganismo().getOrgPk();
//        SsUsuario usuario = inicioMB.getUsuario();
//        calcularProyectosThread = new MigracionThread(orgPk, usuario);
//        calcularProyectosThread.start();
		indicadoresDelegate.recalcularIndicadores(orgPk, true);
        if (segundoPlano != null && segundoPlano) {
            return ConstantesNavegacion.IR_A_INICIO;
        }
        return null;
    }
    
    public String probarMails(){
        
        notificacionEnvBean.enviarNotificaciones();;
        
        return null;
    }
    

    public Integer getCountProy() {
        return countProy;
    }

    public void setCountProy(Integer countProy) {
        this.countProy = countProy;
    }

    public Integer getTotalProy() {
        return totalProy;
    }

    public void setTotalProy(Integer totalProy) {
        this.totalProy = totalProy;
    }

    public Integer getCountProg() {
        return countProg;
    }

    public void setCountProg(Integer countProg) {
        this.countProg = countProg;
    }

    public Integer getTotalProg() {
        return totalProg;
    }

    public void setTotalProg(Integer totalProg) {
        this.totalProg = totalProg;
    }

    public Integer getProgressProy() {
        return progressProy;
    }

    public void setProgressProy(Integer progressProy) {
        this.progressProy = progressProy;
    }

    public Integer getProgressProg() {
        return progressProg;
    }

    public void setProgressProg(Integer progressProg) {
        this.progressProg = progressProg;
    }

    public Boolean getSegundoPlano() {
        return segundoPlano;
    }

    public void setSegundoPlano(Boolean segundoPlano) {
        this.segundoPlano = segundoPlano;
    }

    public Boolean getResetLineaBase() {
        return resetLineaBase;
    }

    public void setResetLineaBase(Boolean resetLineaBase) {
        this.resetLineaBase = resetLineaBase;
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    private class MigracionThread extends Thread {

        private Integer orgPk;
        SsUsuario usuario;

        public MigracionThread(Integer orgPk, SsUsuario usuario) {
            this.orgPk = orgPk;
            this.usuario = usuario;
        }

        @Override
        public void run() {
            logger.log(Level.INFO, "Buscando Proyectos activos... Org:{0}", orgPk);
            final List<Integer> idsProy = proyectoDelegate.obtenerIdsProyPorOrg(orgPk, Boolean.TRUE);
            totalProy = idsProy.size();
            logger.log(Level.INFO, "Total proyectos:{0}", totalProy);
            countProy = 0;

            String[] codigos = new String[]{
                ConfiguracionCodigos.ESTADO_INICIO_LIMITE_AMARILLO,
                ConfiguracionCodigos.ESTADO_INICIO_LIMITE_ROJO,
                ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_AMARILLO,
                ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_ROJO};
            Map<String, Configuracion> confs = configuracionDelegate.obtenerCnfPorCodigoYOrg(orgPk, codigos);

            for (Integer proyPk : idsProy) {

                proyectoDelegate.controlarEntregables(proyPk, resetLineaBase);
                proyectoDelegate.controlarProdAcumulados(proyPk, false);
                proyectoDelegate.guardarIndicadoresSimple(proyPk, false, true, orgPk, confs, false);
                countProy++;
                progressProy = countProy * 100 / totalProy;
                logger.log(Level.INFO, "Proyecto calculcar({0}) Ind: {1}/{2} ({3}%)", new Object[]{proyPk, countProy, totalProy, progressProy});
//                portableRenderer.render(GROUP_NAME);

            }

            logger.log(Level.INFO, "Buscando Programas...{0}", orgPk);
            List<Programas> programas = programaDelegate.obtenerTodosPorOrg(orgPk);
            totalProg = programas.size();
            logger.log(Level.INFO, "Total programas:{0}", totalProg);
            countProg = 0;

            for (Programas prog : programas) {
                Integer progPk = prog.getProgPk();
                programaDelegate.guardarIndicadoresSimple(progPk, orgPk);
                programaDelegate.actualizarProgramaPorProyectos(progPk, usuario, "web");
                countProg++;
                progressProg = countProg * 100 / totalProg;
                logger.log(Level.INFO, "Programa calculcar Ind({0}): {1}/{2} ({3}%)", new Object[]{progPk, countProg, totalProg, progressProg});
//                portableRenderer.render(GROUP_NAME);
            }

            
//            Utils.executeGarbageCollector();
        }
    }

    public Integer cantidadSessions() {
        return SessionCounterListener.getTotalActiveSession();
    }

    private void controlarDatosFaltantes() {
        organismoDelegate.controlarDatosFaltantes();
    }
}
