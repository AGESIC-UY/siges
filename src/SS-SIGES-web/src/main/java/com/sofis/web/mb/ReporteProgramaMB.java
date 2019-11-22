package com.sofis.web.mb;

import com.sofis.business.utils.EntregablesUtils;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.Programas;
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
import com.sofis.web.delegates.ProgramasDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.properties.Labels;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
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
    private static final Logger logger = Logger.getLogger(ReporteProgramaMB.class.getName());
    private static final String REPORTE_PROG_PK = "reporteProgPk";
    private static final int INICIO_REPORTE_GANTT = 5;
    private static final int FIN_REPORTE_GANTT = 665;
    private static final int MARGEN_INICIO = 303;

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private ProgramasDelegate programasDelegate;
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

    private Integer progPk;
    private String progNombre;
    private boolean apaisado = false;
    /**
     * 0 - Avance Parcial, 1 - Avance Finalizado
     */
    private Integer tipoAvance;
    /**
     * 0 - Alcance Finalizado, 1 - Alcance Parcial
     */
    private Integer tipoAlcance;
    private List<ReporteTaskTO> listaTasks;
    private List<ReporteAcumuladoTO> listaAcumuladosPresupuesto;
    private List<ReporteAcumuladoTO> listaAcumuladosAlcance;
    private Integer anio;

    public ReporteProgramaMB() {
    }

    public void setInicioMB(InicioMB inicioMB) {
	this.inicioMB = inicioMB;
    }

    public Integer getProgPk() {
	return progPk;
    }

    public void setProgPk(Integer progPk) {
	this.progPk = progPk;
    }

    public String getProgNombre() {
	return progNombre;
    }

    public void setProgNombre(String progNombre) {
	this.progNombre = progNombre;
    }

    public boolean isApaisado() {
	return apaisado;
    }

    public void setApaisado(boolean apaisado) {
	this.apaisado = apaisado;
    }

    public Integer getTipoAvance() {
	return tipoAvance;
    }

    public void setTipoAvance(Integer tipoAvance) {
	this.tipoAvance = tipoAvance;
    }

    public Integer getTipoAlcance() {
	return tipoAlcance;
    }

    public void setTipoAlcance(Integer tipoAlcance) {
	this.tipoAlcance = tipoAlcance;
    }

    public List<ReporteTaskTO> getListaTasks() {
	return listaTasks;
    }

    public void setListaTasks(List<ReporteTaskTO> listaTasks) {
	this.listaTasks = listaTasks;
    }

    public List<ReporteAcumuladoTO> getListaAcumuladosPresupuesto() {
	return listaAcumuladosPresupuesto;
    }

    public void setListaAcumuladosPresupuesto(List<ReporteAcumuladoTO> listaAcumuladosPresupuesto) {
	this.listaAcumuladosPresupuesto = listaAcumuladosPresupuesto;
    }

    public List<ReporteAcumuladoTO> getListaAcumuladosAlcance() {
	return listaAcumuladosAlcance;
    }

    public void setListaAcumuladosAlcance(List<ReporteAcumuladoTO> listaAcumuladosAlcance) {
	this.listaAcumuladosAlcance = listaAcumuladosAlcance;
    }

    public Integer getAnio() {
	return anio;
    }

    public void setAnio(Integer anio) {
	this.anio = anio;
    }

    @PostConstruct
    public void init() {

        /*
        *   31-05-2018 Nico: Se sacan las variables que se inicializan del constructor y se pasan al PostConstruct
        */          
        
	this.listaTasks = new ArrayList<ReporteTaskTO>();
	this.listaAcumuladosAlcance = new ArrayList<ReporteAcumuladoTO>();
	this.listaAcumuladosPresupuesto = new ArrayList<ReporteAcumuladoTO>();        
        
	tipoAvance = 0;
	tipoAlcance = 0;
	
	progPk = (Integer) getFlashContext(REPORTE_PROG_PK);
	if (progPk == null) {
	    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    progPk = (Integer) request.getSession().getAttribute(REPORTE_PROG_PK);
	    request.getSession().removeAttribute(REPORTE_PROG_PK);
	}

	apaisado = true;
	cargarReporte();
    }

    private void cargarReporte() {
	if (progPk != null) {
	    Programas prog = programasDelegate.obtenerProgPorId(progPk);
	    progNombre = prog != null ? prog.getProgNombre() : null;
	    List<Proyectos> proyList = new ArrayList<>(proyectoDelegate.obtenerProyPorProgId(progPk, Boolean.TRUE));

	    Calendar cal = new GregorianCalendar();
	    anio = cal.get(Calendar.YEAR);
	    Integer ultimoAnio = obtenerUltimoAnio(listaTasks);
	    if (ultimoAnio != null && ultimoAnio < anio) {
		anio = ultimoAnio;
	    }

	    Set<Entregables> entSet = null;
	    Map<String, Date> fechas = null;
	    int[] indiceAvanceFinalizado = null;
	    int[] indiceAvanceParcial = null;
	    Integer duracion = null;
	    Integer duracionLineaBase = null;

	    // Cronograma
	    for (Proyectos proy : proyList) {
		if (proy.getProyCroFk() != null) {
		    entSet = proy.getProyCroFk().getEntregablesSet();

		    fechas = entregablesDelegate.obtenerPrimeraUltimaFecha(entSet);
		    Date croPrimera = fechas.get("primera");
		    Date croUltima = fechas.get("ultima");

		    Date croPrimeraLineaBase = fechas.get("primeraLB");
		    Date croUltimaLineaBase = fechas.get("ultimaLB");

		    indiceAvanceFinalizado = cronogramaDelegate.calcularAvanceCronoFinalizado(entSet);
		    indiceAvanceParcial = cronogramaDelegate.calcularAvanceCronoParcial(entSet);

		    ReporteTaskTO task = new ReporteTaskTO();
		    task.setProyDesc(proy.getProyNombre());
		    task.setInicio(croPrimera);
		    task.setFin(croUltima);
		    duracion = DatesUtils.diasEntreFechas(croPrimera, croUltima);
		    task.setDuracion(duracion != null ? duracion : 0);

		    task.setInicioLineaBase(croPrimeraLineaBase);
		    task.setFinLineaBase(croUltimaLineaBase);
		    duracionLineaBase = DatesUtils.diasEntreFechas(croPrimeraLineaBase, croUltimaLineaBase);
		    task.setDuracionLineaBase(duracionLineaBase != null ? duracionLineaBase : 0);

		    ReporteTaskGrafTO taskGrafFinalizado = new ReporteTaskGrafTO();
		    taskGrafFinalizado.setAzul(indiceAvanceFinalizado[0]);
		    taskGrafFinalizado.setVerde(indiceAvanceFinalizado[1]);
		    taskGrafFinalizado.setRojo(indiceAvanceFinalizado[2]);
		    task.setGrafFinalizado(taskGrafFinalizado);

		    ReporteTaskGrafTO taskGrafParcial = new ReporteTaskGrafTO();
		    taskGrafParcial.setAzul(indiceAvanceParcial[0]);
		    taskGrafParcial.setVerde(indiceAvanceParcial[1]);
		    taskGrafParcial.setRojo(indiceAvanceParcial[2]);
		    task.setGrafParcial(taskGrafParcial);

		    listaTasks.add(task);
		}
	    }

	    cargarTablaAcumulados();
	}
    }

    private void cargarTablaAcumulados() {
	if (progPk != null) {
	    Integer orgPk = inicioMB.getOrganismo().getOrgPk();

	    //Cronograma-Alcance
	    listaAcumuladosAlcance.clear();

	    ReporteAcumuladoTO acuAlcance = new ReporteAcumuladoTO();
	    acuAlcance.setTitulo(Labels.getValue("reporte_proy_alc_titulo_alc"));
	    acuAlcance.setDescPlan(Labels.getValue("reporte_proy_alc_title_plan"));
	    acuAlcance.setTitlePlan(Labels.getValue("reporte_proy_alc_resumen_plan"));
	    acuAlcance.setDescReal(Labels.getValue("reporte_proy_alc_title_real"));
	    acuAlcance.setTitleReal(Labels.getValue("reporte_proy_alc_resumen_real"));
	    acuAlcance.setDescProyectado(Labels.getValue("reporte_proy_alc_title_proy"));
	    acuAlcance.setTitleProyectado(Labels.getValue("reporte_proy_alc_resumen_proy"));
	    acuAlcance.setTieneProyectado(true);

	    List<Entregables> entregables = entregablesDelegate.obtenerEntPorProgPk(progPk, Boolean.TRUE);
	    List<ReporteAcumuladoMesTO> croAlcanceAcu = entregablesDelegate.obtenerAcumuladoCroPorMes(entregables, orgPk);
	    acuAlcance.setMeses(croAlcanceAcu);

	    listaAcumuladosAlcance.add(acuAlcance);

	    // Presupuesto
	    listaAcumuladosPresupuesto.clear();
	    List<Moneda> monedasPresupuesto = presupuestoDelegate.obtenerMonedasPresupuestoPrograma(progPk, Boolean.TRUE);

	    Integer limiteAmarilloPre = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO, orgPk).getCnfValor());
	    Integer limiteRojoPre = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO, orgPk).getCnfValor());

	    Calendar calNow = new GregorianCalendar();

	    for (Moneda moneda : monedasPresupuesto) {
		ReporteAcumuladoTO acuPre = new ReporteAcumuladoTO();
		acuPre.setTitulo(Labels.getValue("reporte_proy_pre_titulo"));
		acuPre.setMoneda(moneda.getMonSigno());
		acuPre.setDescPlan(Labels.getValue("presupuesto_resumen_pv"));
		acuPre.setTitlePlan(Labels.getValue("presupuesto_title_pv"));
		acuPre.setDescReal(Labels.getValue("presupuesto_resumen_ac"));
		acuPre.setTitleReal(Labels.getValue("presupuesto_title_ac"));
		acuPre.setDescProyectado(Labels.getValue("presupuesto_resumen_pr"));
		acuPre.setTitleProyectado(Labels.getValue("presupuesto_title_pr"));
		acuPre.setTieneProyectado(true);

		Date primeraFecha = presupuestoDelegate.obtenerPrimeraFechaPreProg(progPk, moneda.getMonPk());
		Date ultimaFecha = presupuestoDelegate.obtenerUltimaFechaPreProg(progPk, moneda.getMonPk());

		if (primeraFecha != null && ultimaFecha != null) {
		    Calendar primeraFechaCal = new GregorianCalendar();
		    primeraFechaCal.setTime(primeraFecha);
		    Calendar ultimaFechaCal = new GregorianCalendar();
		    ultimaFechaCal.setTime(ultimaFecha);
		    Calendar calAux = primeraFechaCal;

		    Double pvMes = 0d;
		    Double acMes = 0d;
		    Double prMes = 0d;
		    Double prAtrasadoMes = 0d;
		    Double ultimoRealConf = 0D;
		    Boolean proyNegativo;
		    int anioP;
		    int mesP;

		    while (calAux.get(Calendar.YEAR) < ultimaFechaCal.get(Calendar.YEAR)
			    || (calAux.get(Calendar.YEAR) == ultimaFechaCal.get(Calendar.YEAR)
			    && calAux.get(Calendar.MONTH) <= ultimaFechaCal.get(Calendar.MONTH))) {
			anioP = calAux.get(Calendar.YEAR);
			mesP = (calAux.get(Calendar.MONTH) + 1);

			pvMes += presupuestoDelegate.obtenerPVPorMonedaProg(progPk, moneda, anioP, mesP);
			if (calAux.get(Calendar.YEAR) < calNow.get(Calendar.YEAR)
				|| (calAux.get(Calendar.YEAR) == calNow.get(Calendar.YEAR) && calAux.get(Calendar.MONTH) <= calNow.get(Calendar.MONTH))) {
			    acMes += presupuestoDelegate.obtenerACPorMonedaProg(progPk, moneda, anioP, mesP);
			    proyNegativo = presupuestoDelegate.obtenerPRAtrasadoProg(progPk, moneda, anioP, mesP);
			} else {
			    acMes = 0D;
			    proyNegativo = false;
			}

			prMes += presupuestoDelegate.obtenerPRPorMonedaProg(progPk, moneda.getMonPk(), anioP, mesP, false);
			prAtrasadoMes += presupuestoDelegate.obtenerPRPorMonedaProg(progPk, moneda.getMonPk(), anioP, mesP, true);

			if (calAux.get(Calendar.YEAR) > calNow.get(Calendar.YEAR)
				|| (calAux.get(Calendar.YEAR) == calNow.get(Calendar.YEAR)
				&& calAux.get(Calendar.MONTH) > calNow.get(Calendar.MONTH))) {
			    prMes += ultimoRealConf;
			    ultimoRealConf = 0D;
			} else {
			    ultimoRealConf = acMes;
			}

			String colorR = presupuestoDelegate.obtenerColorACMensualProg(progPk, moneda.getMonPk(), orgPk, pvMes, acMes, prMes, prAtrasadoMes, anioP, mesP, limiteAmarilloPre, limiteRojoPre);
			acuPre.setMes((short) anioP, (short) mesP, null, pvMes, acMes, prMes, prAtrasadoMes, proyNegativo, colorR);

			calAux.add(Calendar.MONTH, 1);
		    }
		}

		listaAcumuladosPresupuesto.add(acuPre);
	    }
	}
    }

    public int calcularLeftDia() {
	return EntregablesUtils.calcularLeftDia(anio, MARGEN_INICIO, INICIO_REPORTE_GANTT, FIN_REPORTE_GANTT);
    }

    public int calcularLeftEntByDate(Date inicio) {
	return EntregablesUtils.calcularLeftEntByDate(inicio, anio, INICIO_REPORTE_GANTT, FIN_REPORTE_GANTT);
    }

    public int calcularWitdhEntByDate(Date inicio, Date fin, Integer duracion) {
	return EntregablesUtils.calcularWitdhEntByDate(inicio, fin, duracion, anio, INICIO_REPORTE_GANTT, FIN_REPORTE_GANTT);
    }

    public boolean displayDayRef() {
	Calendar cal = new GregorianCalendar();
	return anio.equals(cal.get(Calendar.YEAR));
    }

    public boolean displayEnt(ReporteTaskTO task) {
	return DatesUtils.isAnioEntreFechas(task.getInicio(), task.getFin(), anio);
    }

    public boolean displayEntLineaBase(ReporteTaskTO task) {
	return DatesUtils.isAnioEntreFechas(task.getInicioLineaBase(), task.getFinLineaBase(), anio);
    }

    public boolean contieneAnioMenor() {
	if (CollectionsUtils.isNotEmpty(listaTasks)) {
	    Calendar cal = new GregorianCalendar();
	    for (ReporteTaskTO task : listaTasks) {
		if (task.getInicio() != null) {
		    cal.setTime(task.getInicio());
		    if (cal.get(Calendar.YEAR) < anio) {
			return true;
		    }
		}
	    }
	}
	if (CollectionsUtils.isNotEmpty(listaAcumuladosPresupuesto)) {
	    for (ReporteAcumuladoTO acu : listaAcumuladosPresupuesto) {
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
		if (task.getFin() != null) {
		    cal.setTime(task.getFin());
		    if (cal.get(Calendar.YEAR) > anio) {
			return true;
		    }
		}
	    }
	}
	if (CollectionsUtils.isNotEmpty(listaAcumuladosPresupuesto)) {
	    for (ReporteAcumuladoTO acu : listaAcumuladosPresupuesto) {
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

    public String getAcumuladoMesAnioColorFinalizado(ReporteAcumuladoTO acu, int mes) {
	return getAcumuladoMesAnioColor(acu, mes, 0);
    }

    public String getAcumuladoMesAnioColorParcial(ReporteAcumuladoTO acu, int mes) {
	return getAcumuladoMesAnioColor(acu, mes, 1);
    }

    /**
     *
     * @param acu
     * @param mes
     * @param tipoAlcance 0-Finalizado, 1-Parcial
     * @return
     */
    public String getAcumuladoMesAnioColor(ReporteAcumuladoTO acu, int mes, int tipoAlcance) {
	ReporteAcumuladoMesTO acuMes = getAcumuladoMesAnio(acu, mes);

	if (acuMes != null) {
	    String colorReal = (tipoAlcance == 1 ? acuMes.getColorRealParcial() : acuMes.getColorRealFinalizado());
	    return colorReal;
	}
	return ConstantesEstandares.COLOR_TRANSPARENT;
    }
    
    public String recargarAvance() {
	if (tipoAvance == null || tipoAvance == 0) {
	    tipoAvance = 1;
	} else {
	    tipoAvance = 0;
	}

	return null;
    }
    
    public String recargarAlcance() {
	if (tipoAlcance == null || tipoAlcance == 0) {
	    tipoAlcance = 1;
	} else {
	    tipoAlcance = 0;
	}

	return null;
    }

    private Object getFlashContext(String attName) {
	return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(attName);
    }

    public Date fechaReporte() {
	return new Date();
    }
}
