package com.sofis.web.mb;

import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.tipos.ReporteAcumuladoMesTO;
import com.sofis.entities.tipos.ReporteAcumuladoTO;
import com.sofis.entities.tipos.ReporteTaskGrafTO;
import com.sofis.entities.tipos.ReporteTaskTO;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.CronogramaDelegate;
import com.sofis.web.delegates.EntregablesDelegate;
import com.sofis.web.delegates.PresupuestoDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.properties.Labels;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "reporteProgramaMB")
@ViewScoped
public class ReporteProgramaMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final String REPORTE_PROG_PK = "reporteProgPk";
    private static final int INICIO_REPORTE_GANTT = 5;
    private static final int FIN_REPORTE_GANTT = 665;

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private ProyectosDelegate proyectoDelegate;
    @Inject
    private PresupuestoDelegate presupuestoDelegate;
    @Inject
    private EntregablesDelegate entregablesDelegate;
    @Inject
    private ConfiguracionDelegate configuracionDelegate;
    @Inject
    private CronogramaDelegate cronogramaDelegate;

    private boolean apaisado = false;
    private List<ReporteTaskTO> listaTasks;
    private List<ReporteAcumuladoTO> listaAcumulados;
    private Integer anio;

    public ReporteProgramaMB() {
        this.listaTasks = new ArrayList<>();
        this.listaAcumulados = new ArrayList<>();
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public boolean isApaisado() {
        return apaisado;
    }

    public void setApaisado(boolean apaisado) {
        this.apaisado = apaisado;
    }

    public List<ReporteTaskTO> getListaTasks() {
        return listaTasks;
    }

    public void setListaTasks(List<ReporteTaskTO> listaTasks) {
        this.listaTasks = listaTasks;
    }

    public List<ReporteAcumuladoTO> getListaAcumulados() {
        return listaAcumulados;
    }

    public void setListaAcumulados(List<ReporteAcumuladoTO> listaAcumulados) {
        this.listaAcumulados = listaAcumulados;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    @PostConstruct
    public void init() {

        Integer reporteProgPk = (Integer) getFlashContext(REPORTE_PROG_PK);
        if (reporteProgPk == null) {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            reporteProgPk = (Integer) request.getSession().getAttribute(REPORTE_PROG_PK);
            request.getSession().removeAttribute(REPORTE_PROG_PK);
            apaisado = true;
        }

        cargarReporte(reporteProgPk);
    }

    private void cargarReporte(int progPk) {
        List<Proyectos> proyList = new ArrayList<>(proyectoDelegate.obtenerProyPorProgId(progPk));

        Calendar cal = new GregorianCalendar();
        anio = cal.get(Calendar.YEAR);
        Integer ultimoAnio = obtenerUltimoAnio(listaTasks);
        if (ultimoAnio != null && ultimoAnio < anio) {
            anio = ultimoAnio;
        }

        // Cronograma
        for (Proyectos proy : proyList) {
            if (proy.getProyCroFk() != null) {
                Set<Entregables> entSet = proy.getProyCroFk().getEntregablesSet();

                Date croPrimera = entregablesDelegate.obtenerPrimeraFecha(entSet);
                Date croUltima = entregablesDelegate.obtenerUltimaFecha(entSet);
                int[] indiceAvanceFinalizado = cronogramaDelegate.calcularAvanceCronoFinalizado(entSet);
                int[] indiceAvanceParcial = cronogramaDelegate.calcularAvanceCronoParcial(entSet);

                ReporteTaskTO task = new ReporteTaskTO();
                task.setProyDesc(proy.getProyNombre());
                task.setInicio(croPrimera);
                task.setFin(croUltima);

                ReporteTaskGrafTO taskGrafFinalizado = new ReporteTaskGrafTO();
                taskGrafFinalizado.setAzul(indiceAvanceFinalizado[0]);
                taskGrafFinalizado.setVerde(indiceAvanceFinalizado[1]);
                taskGrafFinalizado.setRojo(indiceAvanceFinalizado[2]);
                task.setGrafFinalizado(taskGrafFinalizado);

                ReporteTaskGrafTO taskGrafParcial = new ReporteTaskGrafTO();
                taskGrafParcial.setAzul(indiceAvanceParcial[0]);
                taskGrafParcial.setVerde(indiceAvanceParcial[1]);
                taskGrafParcial.setRojo(indiceAvanceParcial[2]);
                task.setGrafFinalizado(taskGrafParcial);

                listaTasks.add(task);
            }
        }

        cargarTablaAcumulados(progPk);

    }

    private void cargarTablaAcumulados(Integer progPk) {
        if (progPk != null) {
            Integer orgPk = inicioMB.getOrganismo().getOrgPk();

            // Presupuesto
            List<Moneda> monedasPresupuesto = presupuestoDelegate.obtenerMonedasPresupuestoPrograma(progPk);

            Integer limiteAmarilloPre = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO, orgPk).getCnfValor());
            Integer limiteRojoPre = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO, orgPk).getCnfValor());

            Date inicio = presupuestoDelegate.obtenerPrimeraFechaPreProg(progPk);
            Date fin = presupuestoDelegate.obtenerUltimaFechaPreProg(progPk);

            for (Moneda moneda : monedasPresupuesto) {
                ReporteAcumuladoTO acumulado = new ReporteAcumuladoTO();
                acumulado.setTitulo(Labels.getValue("reporte_proy_pre_titulo"));
                acumulado.setMoneda(moneda.getMonSigno());
                acumulado.setDescPlan(Labels.getValue("presupuesto_resumen_pv"));
                acumulado.setDescReal(Labels.getValue("presupuesto_resumen_ac"));

                if (inicio != null && fin != null) {
                    Calendar calIni = new GregorianCalendar();
                    calIni.setTime(inicio);
                    Calendar calFin = new GregorianCalendar();
                    calFin.setTime(fin);

                    Calendar calAux = calIni;

                    while (calAux.get(Calendar.YEAR) < calFin.get(Calendar.YEAR)
                            || (calAux.get(Calendar.YEAR) == calFin.get(Calendar.YEAR)
                            && calAux.get(Calendar.MONTH) <= calFin.get(Calendar.MONTH))) {
                        int anioP = calAux.get(Calendar.YEAR);
                        int mesP = (calAux.get(Calendar.MONTH) + 1);

                        Double pvMes = presupuestoDelegate.obtenerPVPorMonedaProg(progPk, moneda, anioP, mesP);
                        Double acMes = presupuestoDelegate.obtenerACPorMonedaProg(progPk, moneda, anioP, mesP);

                        String colorR = presupuestoDelegate.obtenerColorACMensualProg(progPk, moneda.getMonPk(), orgPk, pvMes, acMes, anioP, mesP, limiteAmarilloPre, limiteRojoPre);
                        if (pvMes == 0 && acMes == 0) {
                            pvMes = null;
                            acMes = null;
                        }

                        acumulado.setMes((short) anioP, (short) mesP, null, pvMes, acMes, colorR);

                        calAux.add(Calendar.MONTH, 1);
                    }
                }
                listaAcumulados.add(acumulado);
            }
        }
    }

    public int calcularLeftEntByDate(Date inicio) {
        return EntregablesUtils.calcularLeftEntByDate(inicio, anio, INICIO_REPORTE_GANTT, FIN_REPORTE_GANTT);
    }

    public int calcularWitdhEntByDate(Date inicio, Date fin, Integer duracion) {
        return EntregablesUtils.calcularWitdhEntByDate(inicio, fin, duracion, anio, INICIO_REPORTE_GANTT, FIN_REPORTE_GANTT);
    }

    public boolean displayEnt(ReporteTaskTO task) {
        return DatesUtils.isAnioEntreFechas(task.getInicio(), task.getFin(), anio);
    }

    public boolean contieneAnioMenor() {
        if (CollectionsUtils.isNotEmpty(listaTasks)) {
            Calendar cal = new GregorianCalendar();
            for (ReporteTaskTO task : listaTasks) {
                cal.setTime(task.getInicio());
                if (cal.get(Calendar.YEAR) < anio) {
                    return true;
                }
            }
        }
        if (CollectionsUtils.isNotEmpty(listaAcumulados)) {
            for (ReporteAcumuladoTO acu : listaAcumulados) {
                for (ReporteAcumuladoMesTO acuMes : acu.getMeses()) {
                    if (acuMes.getAnio() < anio) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean contieneAnioMayor() {
        if (CollectionsUtils.isNotEmpty(listaTasks)) {
            Calendar cal = new GregorianCalendar();
            for (ReporteTaskTO task : listaTasks) {
                cal.setTime(task.getFin());
                if (cal.get(Calendar.YEAR) > anio) {
                    return true;
                }
            }
        }
        if (CollectionsUtils.isNotEmpty(listaAcumulados)) {
            for (ReporteAcumuladoTO acu : listaAcumulados) {
                for (ReporteAcumuladoMesTO acuMes : acu.getMeses()) {
                    if (acuMes.getAnio() > anio) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String retrocederAnio() {
        anio--;
        return null;
    }

    public String avanzarAnio() {
        anio++;
        return null;
    }

    public static Integer obtenerUltimoAnio(List<ReporteTaskTO> taskList) {
        if (CollectionsUtils.isNotEmpty(taskList)) {
            Integer ultimo = null;
            Calendar cal = new GregorianCalendar();
            for (ReporteTaskTO task : taskList) {
                cal.setTime(task.getFin());
                if (ultimo == null || cal.get(Calendar.YEAR) > ultimo) {
                    ultimo = cal.get(Calendar.YEAR);
                }
            }
            return ultimo;
        }
        return null;
    }

    public ReporteAcumuladoMesTO getAcumuladoMesAnio(ReporteAcumuladoTO acu, int mes) {
        if (CollectionsUtils.isNotEmpty(acu.getMeses())) {
            for (ReporteAcumuladoMesTO acuMes : acu.getMeses()) {
                if (acuMes.getAnio() == this.anio && acuMes.getMes() == mes) {
                    return acuMes;
                }
            }
        }
        return null;
    }

    public String getAcumuladoMesAnioColor(ReporteAcumuladoTO acu, int mes) {
        ReporteAcumuladoMesTO acuMes = getAcumuladoMesAnio(acu, mes);
        return acuMes != null ? acuMes.getColorReal() : ConstantesEstandares.COLOR_TRANSPARENT;
    }

    private Object getFlashContext(String attName) {
        return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(attName);
    }

    public String semaforosPresupuesto() {
        Integer orgPk = inicioMB.getOrganismo().getOrgPk();

        Integer limiteAmarilloPre = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO, orgPk).getCnfValor());
        Integer limiteRojoPre = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO, orgPk).getCnfValor());

        StringBuffer result = new StringBuffer();
        result.append("<b>Presupuesto:</b><br/>")
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: " + ConstantesEstandares.SEMAFORO_VERDE + ";\" disabled=\"disabled\">")
                .append(String.format("Verde: Real está entre %s%% y %s%%.", (100 - limiteAmarilloPre), (100 + limiteAmarilloPre)))
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: " + ConstantesEstandares.SEMAFORO_AMARILLO + ";\" disabled=\"disabled\">")
                .append(String.format("Amarillo: Real está entre %s%% y %s%%, o entre %s%% y %s%%.", (100 - limiteRojoPre), (100 - limiteAmarilloPre), (100 + limiteAmarilloPre), (100 + limiteRojoPre)))
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: " + ConstantesEstandares.SEMAFORO_NARANJA + ";\" disabled=\"disabled\">")
                .append(String.format("Naranja: Real es menor a %s%%.", (100 - limiteRojoPre)))
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: " + ConstantesEstandares.SEMAFORO_ROJO + ";\" disabled=\"disabled\">")
                .append(String.format("Rojo: Real es mayor a %s%%.<br/>", (100 + limiteRojoPre)));

        return result.toString();
    }
}
