package com.sofis.web.mb;

import com.sofis.business.utils.EntregablesUtils;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Devengado;
import com.sofis.entities.data.EntHistLineaBase;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.ProdMes;
import com.sofis.entities.data.Productos;
import com.sofis.entities.data.ProyReplanificacion;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.tipos.CostoDetalleReporteTO;
import com.sofis.entities.tipos.CostoReporteTO;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.tipos.ReporteAcumuladoMesTO;
import com.sofis.entities.tipos.ReporteAcumuladoTO;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.web.delegates.AdquisicionDelegate;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.DevengadoDelegate;
import com.sofis.web.delegates.EntHistLineaBaseDelegate;
import com.sofis.web.delegates.EntregablesDelegate;
import com.sofis.web.delegates.EstadosDelegate;
import com.sofis.web.delegates.GastosDelegate;
import com.sofis.web.delegates.PresupuestoDelegate;
import com.sofis.web.delegates.ProductosDelegate;
import com.sofis.web.delegates.ProyReplanificacionDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.delegates.RegistrosHorasDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.WebUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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
@ManagedBean(name = "reporteProyectoMB")
@ViewScoped
public class ReporteProyectoMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final String REPORTE_PROY_PK = "reporteProyPk";
    private static final String REPORTE_TIPO = "reporteTipo";
    private static final int INICIO_REPORTE_GANTT = 5;
    private static final int FIN_REPORTE_GANTT = 665;
    private static final int MARGEN_INICIO = 303;

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @ManagedProperty("#{fichaMB}")
    private FichaMB fichaMB;

    @Inject
    private ProyectosDelegate proyectoDelegate;
    @Inject
    private ProductosDelegate productosDelegate;
    @Inject
    private PresupuestoDelegate presupuestoDelegate;
    @Inject
    private ConfiguracionDelegate configuracionDelegate;
    @Inject
    private EntHistLineaBaseDelegate entHistLineaBaseDelegate;
    @Inject
    private ProyReplanificacionDelegate proyReplanificacionDelegate;
    @Inject
    private AdquisicionDelegate adquisicionDelegate;
    @Inject
    private EstadosDelegate estadosDelegate;
    @Inject
    private RegistrosHorasDelegate registrosHorasDelegate;
    @Inject
    private GastosDelegate gastosDelegate;
    @Inject
    private DevengadoDelegate devengadoDelegate;
    @Inject
    private EntregablesDelegate entregablesDelegate;

    // Variables
    private List<Entregables> listaEntregables;
    private List<ReporteAcumuladoTO> listaAcumuladosAlcance;
    private List<ReporteAcumuladoTO> listaAcumuladosProductos;
    private List<ReporteAcumuladoTO> listaAcumuladosPresupuesto;
    private List<CostoReporteTO> listaCostos;
    private List<Moneda> listaCostosMonedas;
    private Integer proyPk;
//    private Integer anio;
    private Integer anio = new GregorianCalendar().get(Calendar.YEAR);
    private boolean apaisado = false;
    private Presupuesto presupuesto;
    private List<ProyReplanificacion> histReplanificacion;
    private Integer tipoPre;
    private boolean cronoHistorico;
    List<Integer> aniosReporte;
    private boolean verHitos = true;
    private boolean verTareas = true;
    /**
     * 0 - Avance Finalizado, 1 - Avance Parcial
     */
    private Integer tipoAlcance;

    private Map<String, Integer> graficasTotales;

    public ReporteProyectoMB() {
	this.listaAcumuladosAlcance = new ArrayList<>();
	this.listaAcumuladosProductos = new ArrayList<>();
	this.listaAcumuladosPresupuesto = new ArrayList<>();
	cronoHistorico = false;

	graficasTotales = new HashMap<>();
    }

    public void setInicioMB(InicioMB inicioMB) {
	this.inicioMB = inicioMB;
    }

    public InicioMB getInicioMB() {
	return inicioMB;
    }

    public FichaMB getFichaMB() {
	return fichaMB;
    }

    public void setFichaMB(FichaMB fichaMB) {
	this.fichaMB = fichaMB;
    }

    public FichaTO getFichaTO() {
	return this.fichaMB.getFichaTO();
    }

    public List<Entregables> getListaEntregables() {
	return listaEntregables;
    }

    public void setListaEntregables(List<Entregables> listaEntregables) {
	this.listaEntregables = listaEntregables;
    }

    public List<ReporteAcumuladoTO> getListaAcumuladosAlcance() {
	return listaAcumuladosAlcance;
    }

    public void setListaAcumuladosAlcance(List<ReporteAcumuladoTO> listaAcumuladosAlcance) {
	this.listaAcumuladosAlcance = listaAcumuladosAlcance;
    }

    public List<ReporteAcumuladoTO> getListaAcumuladosProductos() {
	return listaAcumuladosProductos;
    }

    public void setListaAcumuladosProductos(List<ReporteAcumuladoTO> listaAcumuladosProductos) {
	this.listaAcumuladosProductos = listaAcumuladosProductos;
    }

    public List<ReporteAcumuladoTO> getListaAcumuladosPresupuesto() {
	return listaAcumuladosPresupuesto;
    }

    public void setListaAcumuladosPresupuesto(List<ReporteAcumuladoTO> listaAcumuladosPresupuesto) {
	this.listaAcumuladosPresupuesto = listaAcumuladosPresupuesto;
    }

    public List<CostoReporteTO> getListaCostos() {
	return listaCostos;
    }

    public void setListaCostos(List<CostoReporteTO> listaCostos) {
	this.listaCostos = listaCostos;
    }

    public List<Moneda> getListaCostosMonedas() {
	return listaCostosMonedas;
    }

    public void setListaCostosMonedas(List<Moneda> listaCostosMonedas) {
	this.listaCostosMonedas = listaCostosMonedas;
    }

    public Integer getAnio() {
	return anio;
    }

    public void setAnio(Integer anio) {
	this.anio = anio;
    }

    public boolean isApaisado() {
	return apaisado;
    }

    public void setApaisado(boolean apaisado) {
	this.apaisado = apaisado;
    }

    public Presupuesto getPresupuesto() {
	return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
	this.presupuesto = presupuesto;
    }

    public List<ProyReplanificacion> getHistReplanificacion() {
	return histReplanificacion;
    }

    public void setHistReplanificacion(List<ProyReplanificacion> histReplanificacion) {
	this.histReplanificacion = histReplanificacion;
    }

    public Integer getTipoPre() {
	return tipoPre;
    }

    public void setTipoPre(Integer tipoPre) {
	this.tipoPre = tipoPre;
    }

    public boolean isCronoHistorico() {
	return cronoHistorico;
    }

    public void setCronoHistorico(boolean cronoHistorico) {
	this.cronoHistorico = cronoHistorico;
    }

    public boolean isVerHitos() {
	return verHitos;
    }

    public void setVerHitos(boolean verHitos) {
	this.verHitos = verHitos;
    }

    public boolean isVerTareas() {
	return verTareas;
    }

    public void setVerTareas(boolean verTareas) {
	this.verTareas = verTareas;
    }

    public Integer getTipoAlcance() {
	return tipoAlcance;
    }

    public void setTipoAlcance(Integer tipoAlcance) {
	this.tipoAlcance = tipoAlcance;
    }

    public Map<String, Integer> getGraficasTotales() {
	return graficasTotales;
    }

    public void setGraficasTotales(Map<String, Integer> graficasTotales) {
	this.graficasTotales = graficasTotales;
    }

    public Date fechaReporte() {
	return new Date();
    }

    @PostConstruct
    public void init() {
	tipoAlcance = 0;

	proyPk = (Integer) WebUtils.getFlashContext(REPORTE_PROY_PK);
	if (proyPk == null) {
	    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    proyPk = (Integer) request.getSession().getAttribute(REPORTE_PROY_PK);
	    request.getSession().removeAttribute(REPORTE_PROY_PK);
	}

	if (proyPk != null) {
	    String reporteTipo = (String) WebUtils.getFlashContext(REPORTE_TIPO);
	    if (reporteTipo == null) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		reporteTipo = (String) request.getSession().getAttribute(REPORTE_TIPO);
		request.getSession().removeAttribute(REPORTE_TIPO);
	    }

	    cronoHistorico = false;
	    if (reporteTipo == null || reporteTipo.equalsIgnoreCase("proyecto")) {
		cargarReporte(proyPk);
	    } else if (reporteTipo.equalsIgnoreCase("proyectoTablas")) {
		apaisado = true;
		cargarReporte(proyPk);
	    } else if (reporteTipo.equalsIgnoreCase("cronograma")) {
		apaisado = true;
		cronoHistorico = true;
		cargarCronograma(proyPk);
	    }
	}
    }

    private void cargarReporte(int proyPk) {
	Proyectos proy = proyectoDelegate.obtenerProyPorId(proyPk);
	fichaMB.proyectoToFichaTO(proy);
	if (fichaMB.getFichaTO().getCroFk() != null && fichaMB.getFichaTO().getCroFk().getEntregablesSet() != null) {
	    listaEntregables = new ArrayList(fichaMB.getFichaTO().getCroFk().getEntregablesSet());
	}

	double esfuerzoTotal = 0;
	if (listaEntregables != null) {
	    for (Entregables ent : listaEntregables) {
		esfuerzoTotal += ent.getEntEsfuerzo();
	    }

	    List<EntHistLineaBase> entHistList = null;
	    Integer duracion = null;
	    Integer duracionLineaBase = null;
	    for (Entregables ent : listaEntregables) {
		entHistList = entHistLineaBaseDelegate.obtenerEntHistPorEntPk(ent.getEntPk());
		ent.setEntHistLBSet(new LinkedHashSet<>(entHistList));
		ent.setEntEsfuerzoPorcentaje(ent.getEntEsfuerzo() * 100 / esfuerzoTotal);

		duracion = DatesUtils.diasEntreFechas(ent.getEntInicioDate(), ent.getEntFinDate());
		ent.setEntDuracion(duracion != null ? duracion : 0);
		duracionLineaBase = DatesUtils.diasEntreFechas(ent.getEntInicioLineaBaseDate(), ent.getEntFinLineaBaseDate());
		ent.setEntDuracionLineaBase(duracionLineaBase != null ? duracionLineaBase : 0);
	    }

	    listaEntregables = EntregablesUtils.sortById(listaEntregables);

	    Calendar cal = new GregorianCalendar();
	    anio = cal.get(Calendar.YEAR);
	    Integer ultimoAnio = EntregablesUtils.obtenerUltimoAnio(listaEntregables);
	    if (ultimoAnio != null && ultimoAnio < anio) {
		anio = ultimoAnio;
	    }
	}

	fichaMB.cargarResumenRiesgos();
	fichaMB.cargarResumenPresupuesto();
	fichaMB.cargarResumenCronograma();

	cargarTablaAlcance(proyPk);
	cargarTablaAcumuladosProductos(proyPk);
	cargarTablaAcumuladosPresupuesto(proyPk);
    }

    private void cargarCronograma(int proyPk) {
	Proyectos proy = proyectoDelegate.obtenerProyPorId(proyPk);
	fichaMB.proyectoToFichaTO(proy);

	if (fichaMB.getFichaTO().getCroFk() != null
		&& fichaMB.getFichaTO().getCroFk().getEntregablesSet() != null) {

	    listaEntregables = new ArrayList(fichaMB.getFichaTO().getCroFk().getEntregablesSet());
	    //ordenamos los entregables por el numero
	    listaEntregables = EntregablesUtils.sortById(listaEntregables);

	    Calendar cal = new GregorianCalendar();
	    anio = cal.get(Calendar.YEAR);
	    Integer ultimoAnio = EntregablesUtils.obtenerUltimoAnio(listaEntregables);
	    if (ultimoAnio != null && ultimoAnio < anio) {
		anio = ultimoAnio;
	    }

	    for (Entregables ent : listaEntregables) {
		List<EntHistLineaBase> entHistList = entHistLineaBaseDelegate.obtenerEntHistPorEntPk(ent.getEntPk());
		ent.setEntHistLBSet(new LinkedHashSet<>(entHistList));
	    }
	}

	histReplanificacion = proyReplanificacionDelegate.obtenerReplanHistPorProyPk(proyPk);
    }

    public String estadoStr(Estados est) {
	return estadosDelegate.estadoStr(est.getEstPk());
    }

    public int calcularLeftDia() {
	return EntregablesUtils.calcularLeftDia(anio, MARGEN_INICIO, INICIO_REPORTE_GANTT, FIN_REPORTE_GANTT);
    }

    public int calcularLeftEntByDate(Date inicio) {
	return EntregablesUtils.calcularLeftEntByDate(inicio, anio, INICIO_REPORTE_GANTT, FIN_REPORTE_GANTT);
    }

    public int calcularWitdhEntByDate(Date inicio, Date fin, Integer duracion) {
	return EntregablesUtils.calcularWitdhEntByDate(inicio, fin, duracion, anio, INICIO_REPORTE_GANTT, FIN_REPORTE_GANTT, true);
    }

    private void addAniosReporte(int year) {
	if (!aniosReporte.contains(year)) {
	    aniosReporte.add(year);
	}
    }

    private void cargarAniosReporte() {
	aniosReporte = new ArrayList<>();

	if (CollectionsUtils.isNotEmpty(listaEntregables)) {
	    Calendar cal = new GregorianCalendar();
	    for (Entregables ent : listaEntregables) {
		cal.setTime(ent.getEntInicioDate());
		addAniosReporte(cal.get(Calendar.YEAR));

		cal.setTime(ent.getEntFinDate());
		addAniosReporte(cal.get(Calendar.YEAR));
	    }
	}

	List<Object> listasAcu = new ArrayList<>();
	listasAcu.add(listaAcumuladosAlcance);
	listasAcu.add(listaAcumuladosPresupuesto);
	listasAcu.add(listaAcumuladosProductos);

	List<ReporteAcumuladoTO> listAcu = null;

	for (Object obj : listasAcu) {
	    listAcu = (List<ReporteAcumuladoTO>) obj;
	    if (CollectionsUtils.isNotEmpty(listAcu)) {
		for (ReporteAcumuladoTO acu : listAcu) {
		    if (acu.getMeses() != null) {
			for (ReporteAcumuladoMesTO acuMes : acu.getMeses()) {
			    addAniosReporte(acuMes.getAnio());
			}
		    }
		}
	    }
	}
    }

    public boolean contieneAnioMenor() {
	if (aniosReporte == null) {
	    cargarAniosReporte();
	}
	return aniosReporte.contains(anio - 1);
    }

    public boolean contieneAnioMayor() {
	if (aniosReporte == null) {
	    cargarAniosReporte();
	}
	return aniosReporte.contains(anio + 1);
    }

    public String retrocederAnio() {
	anio--;
	return null;
    }

    public String avanzarAnio() {
	anio++;
	return null;
    }

    public boolean displayEnt(Entregables ent) {
	return DatesUtils.isAnioEntreFechas(ent.getEntInicioDate(), ent.getEntFinDate(), anio);
    }

    public boolean displayEntLineaBase(Entregables ent) {
	return DatesUtils.isAnioEntreFechas(ent.getEntInicioLineaBaseDate(), ent.getEntFinLineaBaseDate(), anio);
    }

    public boolean displayEntHist(EntHistLineaBase entHistLB) {
	return DatesUtils.isAnioEntreFechas(entHistLB.getEnthistInicioLineaBaseDate(), entHistLB.getEnthistFinLineaBaseDate(), anio);
    }

    public boolean entAtrasado(Entregables ent) {
	return EntregablesUtils.entAtrasado(ent.getEntFinDate(), ent.getEntFinLineaBaseDate());
    }

    //tipoPre 0-Por Moneda, 1-Por Adquisición
    public String recargarAcumulados() {
	if (tipoPre == null || tipoPre == 0) {
	    tipoPre = 1;
	} else {
	    tipoPre = 0;
	}

	cargarTablaAcumuladosProductos(getFichaTO().getFichaFk());
	cargarTablaAcumuladosPresupuesto(getFichaTO().getFichaFk());
	return null;
    }

    private void cargarTablaAcumuladosProductos(Integer proyPk) {
	if (proyPk != null) {
	    Integer orgPk = inicioMB.getOrganismo().getOrgPk();
	    listaAcumuladosProductos.clear();

	    // Productos
	    List<Productos> productos = productosDelegate.obtenerProdPorProyPk(proyPk);
	    Integer limiteAmarilloProd = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_AMARILLO, orgPk).getCnfValor());
	    Integer limiteRojoProd = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_ROJO, orgPk).getCnfValor());

	    if (productos != null) {
		for (Productos prod : productos) {
		    ReporteAcumuladoTO acumulado = new ReporteAcumuladoTO();
		    acumulado.setTitulo(Labels.getValue("reporte_proy_prod_titulo"));
		    acumulado.setNombre(prod.getProdDesc());
		    acumulado.setDescPlan(Labels.getValue("prod_resumen_plan"));
		    acumulado.setDescReal(Labels.getValue("prod_resumen_valor"));

		    if (CollectionsUtils.isNotEmpty(prod.getProdMesList())) {
			for (ProdMes prodMes : prod.getProdMesList()) {
			    String colorR = productosDelegate.prodMesAcuRealColor(prodMes.getProdmesPk(), orgPk, limiteAmarilloProd, limiteRojoProd);
			    acumulado.setMes(prodMes.getProdmesAnio(), prodMes.getProdmesMes(), prodMes.getProdmesPk(), prodMes.getProdmesAcuPlan(), prodMes.getProdmesAcuReal(), colorR);
			}
		    }

		    listaAcumuladosProductos.add(acumulado);
		}
	    }
	}
    }

    private void cargarTablaPresupuesto(Integer proyPk) {
	if (proyPk != null) {
	    Integer orgPk = inicioMB.getOrganismo().getOrgPk();
	    listaAcumuladosPresupuesto.clear();

	    // Presupuesto
	    presupuesto = presupuestoDelegate.obtenerPresupuestoPorProy(proyPk);

	    if (presupuesto != null) {
		Integer limiteAmarilloPre = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO, orgPk).getCnfValor());
		Integer limiteRojoPre = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO, orgPk).getCnfValor());

		Date inicio = presupuestoDelegate.obtenerPrimeraFechaPre(presupuesto);
		Date fin = presupuesto.getProyecto().getProyIndices().getProyindPeriodoFin();

		if (tipoPre != null && tipoPre == 1) {
		    //Presupuesto por AdquisiciÃ³n
		    List<Adquisicion> listAdq = null;
		    if (presupuesto != null) {
			listAdq = adquisicionDelegate.obtenerAdquisicionPorPre(presupuesto.getPrePk());
		    }
		    if (listAdq != null) {
			for (Adquisicion adq : listAdq) {
			    cargarAcumuladosPre(adq, inicio, fin, orgPk, limiteAmarilloPre, limiteRojoPre);
			}
		    }

		} else {
		    //Presupuesto por Moneda
		    List<Moneda> monedasPresupuesto = null;
		    if (presupuesto != null) {
			monedasPresupuesto = presupuestoDelegate.obtenerMonedasPresupuesto(presupuesto.getPrePk(), Boolean.TRUE);
		    }
		    if (monedasPresupuesto != null) {
			for (Moneda moneda : monedasPresupuesto) {
			    cargarAcumuladosPre(moneda, inicio, fin, orgPk, limiteAmarilloPre, limiteRojoPre);
			}
		    }
		}
	    }
	}
    }

    private void cargarAcumuladosPre(Object obj, Date inicio, Date fin, Integer orgPk, Integer limiteAmarilloPre, Integer limiteRojoPre) {
	if (obj != null) {
	    Adquisicion adq = null;
	    Moneda moneda = null;
	    int tipoObj = 0;
	    if (obj instanceof Adquisicion) {
		tipoObj = 1;
		adq = (Adquisicion) obj;
	    } else if (obj instanceof Moneda) {
		tipoObj = 2;
		moneda = (Moneda) obj;
	    } else {
		return;
	    }

	    ReporteAcumuladoTO acumulado = new ReporteAcumuladoTO();

	    if (tipoObj == 1 && adq != null) {
		acumulado.setTitulo(Labels.getValue("reporte_proy_pre_titulo_adq"));
		acumulado.setNombre(adq.getAdqNombre());
		acumulado.setMoneda(adq.getAdqMoneda().getMonSigno());
	    } else if (tipoObj == 2 && moneda != null) {
		acumulado.setTitulo(Labels.getValue("reporte_proy_pre_titulo_mon"));
		acumulado.setMoneda(moneda.getMonSigno());
	    }

	    acumulado.setDescPlan(Labels.getValue("presupuesto_resumen_pv"));
	    acumulado.setTitlePlan(Labels.getValue("presupuesto_title_pv"));
	    acumulado.setDescReal(Labels.getValue("presupuesto_resumen_ac"));
	    acumulado.setTitleReal(Labels.getValue("presupuesto_title_ac"));
	    acumulado.setDescProyectado(Labels.getValue("presupuesto_resumen_pr"));
	    acumulado.setTitleProyectado(Labels.getValue("presupuesto_title_pr"));
	    acumulado.setTieneProyectado(true);

	    if (inicio != null && fin != null) {
		Calendar calIni = new GregorianCalendar();
		calIni.setTime(inicio);
		Calendar calFin = new GregorianCalendar();
		calFin.setTime(fin);

		Calendar calAuxIni = calIni;

		if (tipoObj == 2) {
		    calAuxIni.set(Calendar.MONTH, 0);
		    calAuxIni.set(Calendar.DAY_OF_MONTH, 1);
		}

		Double pvMes = 0d;
		Double acMes = 0d;
		Double prMes = 0d;
		Double prAtrasadoMes = 0d;
		boolean prNegativo;
		Double ultimoRealConf = 0D;
		short anioP;
		short mesP;
		Calendar calNow = new GregorianCalendar();

		while (calAuxIni.get(Calendar.YEAR) <= calFin.get(Calendar.YEAR)) {
		    anioP = (short) calAuxIni.get(Calendar.YEAR);
		    mesP = (short) (calAuxIni.get(Calendar.MONTH) + 1);

		    Moneda mon = null;
		    if (tipoObj == 1 && adq != null) {
			mon = adq.getAdqMoneda();
		    } else if (tipoObj == 2 && moneda != null) {
			mon = moneda;
		    }

		    Date primeraFecha = presupuestoDelegate.obtenerPrimeraFechaPre(presupuesto.getPrePk(), mon.getMonPk());
		    Calendar primeraFechaCal = new GregorianCalendar();
		    Date ultimaFecha = presupuestoDelegate.obtenerUltimaFechaPre(presupuesto.getPrePk(), mon.getMonPk());
		    Calendar ultimaFechaCal = new GregorianCalendar();

		    if (primeraFecha != null && ultimaFecha != null) {
			primeraFechaCal.setTime(primeraFecha);
			ultimaFechaCal.setTime(ultimaFecha);

			if ((calAuxIni.get(Calendar.YEAR) > primeraFechaCal.get(Calendar.YEAR)
				|| (calAuxIni.get(Calendar.YEAR) == primeraFechaCal.get(Calendar.YEAR))
				&& calAuxIni.get(Calendar.MONTH) >= primeraFechaCal.get(Calendar.MONTH))
				&& (calAuxIni.get(Calendar.YEAR) < ultimaFechaCal.get(Calendar.YEAR)
				|| (calAuxIni.get(Calendar.YEAR) == ultimaFechaCal.get(Calendar.YEAR)
				&& calAuxIni.get(Calendar.MONTH) <= ultimaFechaCal.get(Calendar.MONTH)))) {

			    Integer adqPk = null;
			    if (tipoObj == 1 && adq != null) {
				adqPk = adq.getAdqPk();
			    }

			    Calendar acCal = new GregorianCalendar();
			    acCal.set(Calendar.YEAR, anioP);
			    acCal.set(Calendar.MONTH, mesP - 1);

			    pvMes += presupuestoDelegate.obtenerPVPorMoneda(presupuesto.getPrePk(), adqPk, null,mon, anioP, mesP);
			    if (acCal.get(Calendar.YEAR) < calNow.get(Calendar.YEAR)
				    || (acCal.get(Calendar.YEAR) == calNow.get(Calendar.YEAR) && acCal.get(Calendar.MONTH) <= calNow.get(Calendar.MONTH))) {
				acMes += presupuestoDelegate.obtenerACPorMoneda(presupuesto.getPrePk(), adqPk, null,mon, anioP, mesP);
				prNegativo = presupuestoDelegate.obtenerPRAtrasado(presupuesto.getPrePk(), adqPk, mon, anioP, mesP);
			    } else {
				acMes = 0D;
				prNegativo = false;
			    }
			    prMes += presupuestoDelegate.obtenerPRPorMoneda(presupuesto.getPrePk(), adqPk, null,mon, anioP, mesP, false);
			    prAtrasadoMes += presupuestoDelegate.obtenerPRPorMoneda(presupuesto.getPrePk(), adqPk, null,mon, anioP, mesP, true);

			    if (acCal.get(Calendar.YEAR) > calNow.get(Calendar.YEAR)
				    || (acCal.get(Calendar.YEAR) == calNow.get(Calendar.YEAR)
				    && acCal.get(Calendar.MONTH) > calNow.get(Calendar.MONTH))) {
				prMes += ultimoRealConf;
				ultimoRealConf = 0D;
			    } else {
				ultimoRealConf = acMes;
			    }

			    String colorR = presupuestoDelegate.obtenerColorACMensual(pvMes, acMes, prMes, prAtrasadoMes, orgPk, limiteAmarilloPre, limiteRojoPre, acCal);
			    acumulado.setMes(anioP, mesP, null, pvMes, acMes, prMes, prAtrasadoMes, prNegativo, colorR);
			}
		    }

		    calAuxIni.add(Calendar.MONTH, 1);
		}
	    }
	    listaAcumuladosPresupuesto.add(acumulado);
	}
    }

    private void cargarTablaGastos(Integer proyPk) {
	if (proyPk != null) {
	    Proyectos proy = proyectoDelegate.obtenerProyPorId(proyPk);

	    Date inicio = null;
	    Date fin = null;

	    if (proy != null) {
		inicio = proy.getProyIndices().getProyindPeriodoInicio();
		fin = proy.getProyIndices().getProyindPeriodoFin();
	    }

	    if (inicio != null && fin != null) {
		Calendar calIni = new GregorianCalendar();
		calIni.setTime(inicio);
		Calendar calFin = new GregorianCalendar();
		calFin.setTime(fin);
		Calendar calAux = new GregorianCalendar();

		short anioH = 0;
		short mesH = 0;
		Double gastosAprob;
		Double gastosPend;
		ReporteAcumuladoMesTO ultimoMes = null;

		List<Moneda> listMonedas = gastosDelegate.obtenerMonedasPorProy(proyPk);

		if (listMonedas != null) {
		    for (Moneda moneda : listMonedas) {
			gastosAprob = 0d;
			gastosPend = 0d;

			calAux.setTime(inicio);
			ReporteAcumuladoTO acumulado = new ReporteAcumuladoTO();
			acumulado.setTitulo(Labels.getValue("reporte_proy_gastos_titulo"));
			acumulado.setMoneda(moneda.getMonSigno());
			acumulado.setDescPlan(Labels.getValue("revisionHoras_aprobado"));
			acumulado.setDescReal(Labels.getValue("revisionHoras_pendiente"));

			while (calAux.get(Calendar.YEAR) <= calFin.get(Calendar.YEAR)) {
			    anioH = (short) calAux.get(Calendar.YEAR);
			    mesH = (short) (calAux.get(Calendar.MONTH) + 1);

			    if (calAux.get(Calendar.YEAR) == calIni.get(Calendar.YEAR)
				    && calAux.get(Calendar.MONTH) < calIni.get(Calendar.MONTH)) {
				// Por ahora nada
			    } else if ((calAux.get(Calendar.YEAR) > calIni.get(Calendar.YEAR)
				    || (calAux.get(Calendar.YEAR) == calIni.get(Calendar.YEAR)
				    && calAux.get(Calendar.MONTH) >= calIni.get(Calendar.MONTH)))
				    && (calAux.get(Calendar.YEAR) <= calFin.get(Calendar.YEAR))) {

				Double gAprob = gastosDelegate.obtenerImpTotalGastosPorProy(proyPk, moneda.getMonPk(), Integer.valueOf(mesH), Integer.valueOf(anioH), true);
				Double gPend = gastosDelegate.obtenerImpTotalGastosPorProy(proyPk, moneda.getMonPk(), Integer.valueOf(mesH), Integer.valueOf(anioH), false);

				gastosAprob += gAprob != null ? gAprob : 0d;
				gastosPend += gPend != null ? gPend : 0d;

				ReporteAcumuladoMesTO acuMes = new ReporteAcumuladoMesTO(anioH, mesH, null, gastosAprob, gastosPend, ConstantesEstandares.COLOR_TRANSPARENT);
				ultimoMes = acuMes;
				acumulado.getMeses().add(acuMes);

			    } else {
				ReporteAcumuladoMesTO acuMes = new ReporteAcumuladoMesTO(anioH, mesH, null, ultimoMes.getValorPlan(), ultimoMes.getValorRealFinalizado(), ConstantesEstandares.COLOR_TRANSPARENT);
				ultimoMes = acuMes;
				acumulado.getMeses().add(acuMes);

			    }
			    calAux.add(Calendar.MONTH, 1);
			}

			listaAcumuladosPresupuesto.add(acumulado);
		    }
		}
	    }
	}
    }

    private void cargarTablaImpHoras(Integer proyPk) {
	if (proyPk != null) {
	    Proyectos proy = proyectoDelegate.obtenerProyPorId(proyPk);

	    Date inicio = null;
	    Date fin = null;

	    if (proy != null) {
		inicio = proy.getProyIndices().getProyindPeriodoInicio();
		fin = proy.getProyIndices().getProyindPeriodoFin();
	    }

	    if (inicio != null && fin != null) {
		Calendar calIni = new GregorianCalendar();
		calIni.setTime(inicio);
		Calendar calFin = new GregorianCalendar();
		calFin.setTime(fin);
		Calendar calAux = new GregorianCalendar();

		short anioH = 0;
		short mesH = 0;
		Double horasAprob;
		Double horasPend;
		ReporteAcumuladoMesTO ultimoMes = null;

		List<Moneda> listMonedas = registrosHorasDelegate.obtenerMonedasPorProy(proyPk);

		if (listMonedas != null) {
		    for (Moneda moneda : listMonedas) {
			horasAprob = 0d;
			horasPend = 0d;
			calAux.setTime(inicio);
			ReporteAcumuladoTO acumulado = new ReporteAcumuladoTO();
			acumulado.setTitulo(Labels.getValue("reporte_proy_horas_titulo"));
			acumulado.setMoneda(moneda.getMonSigno());
			acumulado.setDescPlan(Labels.getValue("revisionHoras_aprobado"));
			acumulado.setDescReal(Labels.getValue("revisionHoras_pendiente"));

			while (calAux.get(Calendar.YEAR) <= calFin.get(Calendar.YEAR)) {
			    anioH = (short) calAux.get(Calendar.YEAR);
			    mesH = (short) (calAux.get(Calendar.MONTH) + 1);

			    if (calAux.get(Calendar.YEAR) == calIni.get(Calendar.YEAR)
				    && calAux.get(Calendar.MONTH) < calIni.get(Calendar.MONTH)) {
				// Por ahora nada
			    } else if ((calAux.get(Calendar.YEAR) > calIni.get(Calendar.YEAR)
				    || (calAux.get(Calendar.YEAR) == calIni.get(Calendar.YEAR)
				    && calAux.get(Calendar.MONTH) >= calIni.get(Calendar.MONTH)))
				    && (calAux.get(Calendar.YEAR) <= calFin.get(Calendar.YEAR))) {

				Double hAprob = registrosHorasDelegate.obtenerImporteTotalHsAprob(proyPk, moneda.getMonPk(), Integer.valueOf(mesH), Integer.valueOf(anioH), null);
				Double hPend = registrosHorasDelegate.obtenerImporteTotalHsPend(proyPk, moneda.getMonPk(), Integer.valueOf(mesH), Integer.valueOf(anioH), null);

				horasAprob += hAprob != null ? hAprob : 0d;
				horasPend += hPend != null ? hPend : 0d;

				ReporteAcumuladoMesTO acuMes = new ReporteAcumuladoMesTO(anioH, mesH, null, horasAprob, horasPend, ConstantesEstandares.COLOR_TRANSPARENT);
				ultimoMes = acuMes;
				acumulado.getMeses().add(acuMes);

			    } else {

				ReporteAcumuladoMesTO acuMes = new ReporteAcumuladoMesTO(anioH, mesH, null, ultimoMes.getValorPlan(), ultimoMes.getValorRealFinalizado(), ConstantesEstandares.COLOR_TRANSPARENT);
				ultimoMes = acuMes;
				acumulado.getMeses().add(acuMes);
			    }
			    calAux.add(Calendar.MONTH, 1);
			}

			listaAcumuladosPresupuesto.add(acumulado);
		    }
		}
	    }
	}
    }

    private void cargarTablaDevengado(Integer proyPk) {
	if (proyPk != null) {
	    Date inicio = null;
	    Date fin = null;
	    Calendar calFin = new GregorianCalendar();
	    Calendar calAux = new GregorianCalendar();
	    short anioDev = 0;
	    short mesDev = 0;

	    List<Adquisicion> listAdq = adquisicionDelegate.obtenerAdqDevPorProy(proyPk);

	    if (listAdq != null) {
		for (Adquisicion adq : listAdq) {
		    List<Devengado> listDev = adq.getDevengadoList();
		    if (CollectionsUtils.isNotEmpty(listDev)) {
			inicio = devengadoDelegate.obtenerPrimeraFecha(listDev, true);
			fin = devengadoDelegate.obtenerPrimeraFecha(listDev, false);
		    }

		    if (inicio != null && fin != null) {
			calFin.setTime(fin);
			calAux.setTime(inicio);

			ReporteAcumuladoTO acumulado = new ReporteAcumuladoTO();
			acumulado.setTitulo(Labels.getValue("reporte_proy_devengado_titulo"));
			acumulado.setNombre(adq.getAdqNombre());
			acumulado.setMoneda(adq.getAdqMoneda().getMonSigno());
			acumulado.setDescPlan(Labels.getValue("adquisicion_devengado_planificado"));
			acumulado.setDescReal(Labels.getValue("adquisicion_devengado_real"));

			while (calAux.get(Calendar.YEAR) < calFin.get(Calendar.YEAR)
				|| (calAux.get(Calendar.YEAR) == calFin.get(Calendar.YEAR)
				&& calAux.get(Calendar.MONTH) <= calFin.get(Calendar.MONTH))) {
			    anioDev = (short) calAux.get(Calendar.YEAR);
			    mesDev = (short) (calAux.get(Calendar.MONTH) + 1);
			    Devengado dev = devengadoDelegate.obtenerDevengado(adq.getAdqPk(), mesDev, anioDev);

			    if (dev != null) {
				acumulado.setMes(anioDev, mesDev, null, dev.getDevPlan(), dev.getDevReal(), ConstantesEstandares.COLOR_TRANSPARENT);
			    }
			    calAux.add(Calendar.MONTH, 1);
			}
			listaAcumuladosPresupuesto.add(acumulado);
		    }
		}
	    }
	}
    }

    private void cargarTablaAlcance(Integer proyPk) {
	if (proyPk != null) {
	    Integer orgPk = inicioMB.getOrganismo().getOrgPk();
	    listaAcumuladosAlcance.clear();

	    ReporteAcumuladoTO acumulado = new ReporteAcumuladoTO();
	    acumulado.setTitulo(Labels.getValue("reporte_proy_alc_titulo_alc"));
	    acumulado.setDescPlan(Labels.getValue("reporte_proy_alc_title_plan"));
	    acumulado.setTitlePlan(Labels.getValue("reporte_proy_alc_resumen_plan"));
	    acumulado.setDescReal(Labels.getValue("reporte_proy_alc_title_real"));
	    acumulado.setTitleReal(Labels.getValue("reporte_proy_alc_resumen_real"));
	    acumulado.setDescProyectado(Labels.getValue("reporte_proy_alc_title_proy"));
	    acumulado.setTitleProyectado(Labels.getValue("reporte_proy_alc_resumen_proy"));
	    acumulado.setTieneProyectado(true);

	    List<Entregables> entregables = entregablesDelegate.obtenerEntPorProyPk(proyPk);
	    List<ReporteAcumuladoMesTO> croAlcanceAcu = entregablesDelegate.obtenerAcumuladoCroPorMes(entregables, orgPk);
	    acumulado.setMeses(croAlcanceAcu);

	    listaAcumuladosAlcance.add(acumulado);
	}
    }

    private void cargarTablaCostos(Integer proyPk) {
	Presupuesto pre = presupuestoDelegate.obtenerPresupuestoPorProy(proyPk);
	listaCostosMonedas = presupuestoDelegate.obtenerMonedasPresupuesto(pre.getPrePk(), Boolean.TRUE);
	List<Moneda> monGastos = gastosDelegate.obtenerMonedasPorProy(proyPk);
	if (monGastos != null) {
	    for (Moneda monGasto : monGastos) {
		if (!listaCostosMonedas.contains(monGasto)) {
		    listaCostosMonedas.add(monGasto);
		}
	    }
	}

	listaCostos = new ArrayList<>();
	List<Adquisicion> listAdq = adquisicionDelegate.obtenerAdquisicionPorPre(pre.getPrePk());

	CostoReporteTO costoTotal = new CostoReporteTO();
	costoTotal.setDescripcion(Labels.getValue("reporte_proy_costo_total"));
	costoTotal.setTipo(2);

	// Carga las adquisiciones
	if (listAdq != null) {
	    for (Adquisicion adq : listAdq) {
		CostoReporteTO costoAdq = new CostoReporteTO();
		String fuente = adq.getAdqFuente() != null ? " (" + adq.getAdqFuente().getFueNombre() + ")" : "";
		costoAdq.setDescripcion(adq.getAdqNombre() + fuente);
		costoAdq.setTipo(1);

		CostoDetalleReporteTO detalle = new CostoDetalleReporteTO();
		detalle.setMoneda(adq.getAdqMoneda());
		detalle.setEjecutado(adquisicionDelegate.costoEjecutado(adq.getAdqPk()));
		detalle.setTotal(adquisicionDelegate.costoTotal(adq.getAdqPk()));
		costoAdq.getCostoMoneda().put(adq.getAdqMoneda().getMonPk(), detalle);

		listaCostos.add(costoAdq);
		costoTotal.addCostoMonedaTotal(adq.getAdqMoneda().getMonPk(), detalle.getEjecutado(), detalle.getTotal());
	    }
	}

	// Carga las horas
	CostoReporteTO costoHoras = new CostoReporteTO();
	costoHoras.setDescripcion(Labels.getValue("reporte_proy_costo_horas"));
	costoHoras.setTipo(1);
	if (listaCostosMonedas != null) {
	    for (Moneda mon : listaCostosMonedas) {
		CostoDetalleReporteTO detalleHoras = new CostoDetalleReporteTO();
		detalleHoras.setMoneda(mon);
		Double impAprob = registrosHorasDelegate.obtenerImporteTotalHs(proyPk, mon.getMonPk(), null, null, null, true);
		Double impHoras = registrosHorasDelegate.obtenerImporteTotalHs(proyPk, mon.getMonPk(), null, null, null, null);
		detalleHoras.setEjecutado(impAprob);
		detalleHoras.setTotal(impHoras);
		costoHoras.getCostoMoneda().put(mon.getMonPk(), detalleHoras);

		costoTotal.addCostoMonedaTotal(mon.getMonPk(), detalleHoras.getEjecutado(), detalleHoras.getTotal());
	    }
	}

	listaCostos.add(costoHoras);

	// Carga los gastos
	CostoReporteTO costoGasto = new CostoReporteTO();
	costoGasto.setDescripcion(Labels.getValue("reporte_proy_costo_gastos"));
	costoGasto.setTipo(1);

	if (listaCostosMonedas != null) {
	    for (Moneda mon : listaCostosMonedas) {
		CostoDetalleReporteTO detalleGasto = new CostoDetalleReporteTO();
		detalleGasto.setMoneda(mon);
		detalleGasto.setEjecutado(gastosDelegate.obtenerGastosPorProyYMon(proyPk, null, mon.getMonPk(), true));
		detalleGasto.setTotal(gastosDelegate.obtenerGastosPorProyYMon(proyPk, null, mon.getMonPk(), null));
		costoGasto.getCostoMoneda().put(mon.getMonPk(), detalleGasto);

		costoTotal.addCostoMonedaTotal(mon.getMonPk(), detalleGasto.getEjecutado(), detalleGasto.getTotal());
	    }
	}

	listaCostos.add(costoGasto);

	// Total
	listaCostos.add(costoTotal);

	// Carga el % ejecutado
	CostoReporteTO costoPorcEjecutado = new CostoReporteTO();
	costoPorcEjecutado.setDescripcion(Labels.getValue("reporte_proy_costo_porc_ejec"));
	costoPorcEjecutado.setTipo(3);
	if (listaCostosMonedas != null) {
	    for (Moneda mon : listaCostosMonedas) {
		CostoDetalleReporteTO detallePorcEjec = new CostoDetalleReporteTO();
		detallePorcEjec.setMoneda(mon);
		CostoDetalleReporteTO detalleTot = costoTotal.getCostoDetallePorMoneda(mon.getMonPk());
		double porcEjec = detalleTot.getEjecutado() != null && detalleTot.getEjecutado() > 0 && detalleTot.getTotal() != null && detalleTot.getTotal() > 0 ? detalleTot.getEjecutado() * 100 / detalleTot.getTotal() : 0;
		detallePorcEjec.setEjecutado(porcEjec);
		detallePorcEjec.setTotal(null);
		costoPorcEjecutado.getCostoMoneda().put(mon.getMonPk(), detallePorcEjec);
	    }
	}

	listaCostos.add(costoPorcEjecutado);

	graficasTotales.put("tiempo", proyectoDelegate.porcentajeAvanceEnTiempo(proyPk));

	Double esfTotal = entregablesDelegate.obtenerEsfuerzoTotal(proyPk);
	Double esfTerminado = entregablesDelegate.obtenerEsfuerzoTerminado(proyPk);
	int alcance = esfTotal != null && esfTerminado != null && esfTotal > 0 && esfTerminado > 0 ? (int) Math.round(esfTerminado * 100 / esfTotal) : 0;
	graficasTotales.put("alcance", alcance);

	if (listaCostosMonedas != null) {
	    for (Moneda mon : listaCostosMonedas) {
		CostoDetalleReporteTO detalleTot = costoTotal.getCostoDetallePorMoneda(mon.getMonPk());
		double porcEjec = detalleTot.getEjecutado() != null && detalleTot.getTotal() != null ? detalleTot.getEjecutado() * 100 / detalleTot.getTotal() : 0;
		graficasTotales.put(mon.getMonSigno(), (int) Math.round(porcEjec));
	    }
	}
    }

    public boolean displayDayRef() {
	Calendar cal = new GregorianCalendar();
	return anio.equals(cal.get(Calendar.YEAR));
    }

    private void cargarTablaAcumuladosPresupuesto(Integer proyPk) {
	cargarTablaPresupuesto(proyPk);
	cargarTablaGastos(proyPk);
	cargarTablaImpHoras(proyPk);
	cargarTablaDevengado(proyPk);

	cargarTablaCostos(proyPk);
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

    public String semaforosProductos() {
	Integer orgPk = inicioMB.getOrganismo().getOrgPk();

	Integer limiteAmarilloProd = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_AMARILLO, orgPk).getCnfValor());
	Integer limiteRojoProd = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_ROJO, orgPk).getCnfValor());

	StringBuilder result = new StringBuilder();
	result.append("<b>Productos:</b><br/>")
		.append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: " + ConstantesEstandares.SEMAFORO_AZUL + ";\" disabled=\"disabled\">")
		.append("Azul: Real es mayor o igual al total Planificado.")
		.append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: " + ConstantesEstandares.SEMAFORO_VERDE + ";\" disabled=\"disabled\">")
		.append(String.format("Verde: Real es menor al total Planificado y mayor a %s%%.", (100 - limiteAmarilloProd)))
		.append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: " + ConstantesEstandares.SEMAFORO_AMARILLO + ";\" disabled=\"disabled\">")
		.append(String.format("Amarillo: Real es menor o igual a %s%% y mayor a %s%%.", (100 - limiteAmarilloProd), (100 - limiteRojoProd)))
		.append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: " + ConstantesEstandares.SEMAFORO_ROJO + ";\" disabled=\"disabled\">")
		.append(String.format("Rojo: Real es menor a %s%%.<br/>", (100 - limiteRojoProd)));

	return result.toString();
    }

    public String verEntregablesChange() {
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

    /**
     * Retorna true si dado el mes y año existe algún entregable marcado como
     * clave.
     *
     * @param mes
     * @param tipo 0=Plan, 1=Real, 2=Proyectado
     * @return boolean
     */
    public boolean mesTieneEntClave(Integer mes, Integer tipo) {
	return entregablesDelegate.mesTieneEntClave(mes, anio, proyPk, tipo);
    }

    public boolean entregableTieneClave(Integer entPk, Integer tipo) {
	return entregablesDelegate.entregableTieneClave(entPk, tipo);
    }
}
