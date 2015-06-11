package com.sofis.web.mb;

import com.sofis.business.utils.Utils;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.SsUsuario;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.MailsTemplateDelegate;
import com.sofis.web.delegates.ProgramasDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.genericos.constantes.ConstantesNavegacion;
import com.sofis.web.listener.SessionCounterListener;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.icefaces.application.PortableRenderer;
import org.icefaces.application.PushRenderer;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "migracionMB")
@ViewScoped
public class MigracionMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstantesEstandares.LOGGER);

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private ProgramasDelegate programaDelegate;
    @Inject
    private ProyectosDelegate proyectoDelegate;
    @Inject
    private ConfiguracionDelegate configuracionDelegate;
    @Inject
    private MailsTemplateDelegate mailsTemplateDelegate;

    private Boolean segundoPlano;
    private Boolean resetLineaBase;
    private Integer countProy;
    private Integer totalProy;
    private Integer countProg;
    private Integer totalProg;
    private Integer progressProy;
    private Integer progressProg;
    private static String GROUP_NAME = "everyone";
    private PortableRenderer portableRenderer;
    private Thread calcularProyectosThread;

    public MigracionMB() {
        PushRenderer.addCurrentView(GROUP_NAME);
        portableRenderer = PushRenderer.getPortableRenderer();
    }

    public String salir() {
        if (calcularProyectosThread != null && calcularProyectosThread.isAlive()) {
            calcularProyectosThread.interrupt();
        }
        return ConstantesNavegacion.IR_A_INICIO;
    }

    public String calcular() {

        controlarDatosFaltantes();

        Integer orgPk = inicioMB.getOrganismo().getOrgPk();
        SsUsuario usuario = inicioMB.getUsuario();
        calcularProyectosThread = new MigracionThread(orgPk, usuario);
        calcularProyectosThread.start();

        if (segundoPlano != null && segundoPlano) {
            return ConstantesNavegacion.IR_A_INICIO;
        }
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
            logger.log(Level.INFO, "Buscando Proyectos..." + orgPk);
            final List<Integer> idsProy = proyectoDelegate.obtenerIdsProyPorOrg(orgPk);
            totalProy = idsProy.size();
            logger.log(Level.INFO, "Total proyectos:" + totalProy);
            countProy = 0;

            for (Integer proyPk : idsProy) {
                proyectoDelegate.controlarEntregables(proyPk, resetLineaBase);
                proyectoDelegate.controlarProdAcumulados(proyPk);
                proyectoDelegate.guardarIndicadoresSimple(proyPk, false, true, orgPk);
                countProy++;
                progressProy = countProy * 100 / totalProy;
                logger.log(Level.INFO, "Proyecto calculcar Ind: " + countProy + "/" + totalProy + " (" + progressProy + "%)");
                portableRenderer.render(GROUP_NAME);
            }

            logger.log(Level.INFO, "Buscando Programas..." + orgPk);
            List<Programas> programas = programaDelegate.obtenerTodosPorOrg(orgPk);
            totalProg = programas.size();
            logger.log(Level.INFO, "Total programas:" + totalProg);
            countProg = 0;

            for (Programas prog : programas) {
                programaDelegate.guardarIndicadoresSimple(prog.getProgPk(), orgPk);
                programaDelegate.actualizarProgramaPorProyectos(prog.getProgPk(), usuario);
                countProg++;
                progressProg = countProg * 100 / totalProg;
                logger.log(Level.INFO, "Programa calculcar Ind: " + countProg + "/" + totalProg + " (" + progressProg + "%)");
                portableRenderer.render(GROUP_NAME);
            }

            Utils.executeGarbageCollector();
        }
    }

    public Integer cantidadSessions(){
        return SessionCounterListener.getTotalActiveSession();
    }
    
    private void controlarDatosFaltantes() {
        //mails_template
        mailsTemplateDelegate.controlarMailTmpFaltantes();

        //ss_configuraciones
        configuracionDelegate.controlarCnfFaltantes();

        //Estados
//        0	No Exigido	
//        1	Pendiente	1
//        2	Inicio	2
//        3	Planificacion	3
//        4	Ejecucion	4
//        5	Finalizado	5
//        11	Pendiente PMO T.	
//        12	Pendiente PMO F.	
//        41	Solicitud Finalizado PMO F.	
//        42	Solicitud Finalizado PMO T.	
//        61	Solicitud Cancelar PMO T.	
        //ss_rol
//        0	ADMINISTRADOR	Usuario Administrador de la aplicacion	Usuario Administrador
//        1	PMOT	PMO Transversal	PMO Transversal
//        2	PMOF	PMO Federada	PMO Federada
//        3	DIR	Director	Director
//        4	USU	Usuario	Usuario
//        16	USU_HORAS	Externo (solo carga de horas)	Externo (solo carga de horas)
        //tipo_leccion
//        3	A evitar
//        4	A repetir
    }
}
