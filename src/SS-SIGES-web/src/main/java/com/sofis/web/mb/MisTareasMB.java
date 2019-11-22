package com.sofis.web.mb;

import com.icesoft.faces.context.effects.JavascriptContext;
import com.sofis.business.utils.ComboItemTOUtils;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.ProdMes;
import com.sofis.entities.data.Productos;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.ComboItemTO;
import com.sofis.entities.tipos.FiltroMisTareasTO;
import com.sofis.entities.tipos.MisTareasProgProyTO;
import com.sofis.entities.tipos.MisTareasTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.EntregablesDelegate;
import com.sofis.web.delegates.ProductosDelegate;
import com.sofis.web.delegates.ProgramasDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.genericos.constantes.ConstantesPresentacion;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisComboG;
import com.sofis.web.utils.WebUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "misTareasMB")
@ViewScoped
public class MisTareasMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MisTareasMB.class.getName());
	private static final int INICIO_REPORTE_GANTT = 5;
	private static final int FIN_REPORTE_GANTT = 665;
	private static final int MARGEN_INICIO = 303;
	private static final String MIS_TAREAS_MSG = "misTareasMsg";

	@ManagedProperty("#{inicioMB}")
	private InicioMB inicioMB;

	@Inject
	private ProgramasDelegate programasDelegate;
	@Inject
	private EntregablesDelegate entregablesDelegate;
	@Inject
	private SsUsuarioDelegate ssUsuarioDelegate;
	@Inject
	private SofisPopupUI renderPopupEdicion;
	@Inject
	private ProductosDelegate productosDelegate;
	@Inject
	private ConfiguracionDelegate configuracionDelegate;
        @Inject
        private ProyectosDelegate proyectosDelegate;

	private SofisComboG<Programas> programaCombo = new SofisComboG<>();
	private SofisComboG<SsUsuario> usuarioCombo = new SofisComboG<>();
	private SofisComboG<SelectItem> anioCombo = new SofisComboG<>();
	private SofisComboG<ComboItemTO> avanceCombo;
	private FiltroMisTareasTO filtro = new FiltroMisTareasTO();
	private List<MisTareasProgProyTO> misTareasGroupProgProy = new ArrayList<MisTareasProgProyTO>();
	private int primerAnio;
	private int ultimoAnio;
	private List<SsUsuario> listaUsuCoord;
	private MisTareasTO tareaEnEdicion = new MisTareasTO();
	private Boolean isCoordBusqueda = null;
	private Boolean tarea;
	private Boolean producto;
	private Integer limiteAmarilloProd;
	private Integer limiteRojoProd;
	private Boolean hayProductos;
	private Map<Integer, Boolean> editarProdMap = new HashMap<>();
        
        /*
        *       Se agrega esta lista para poder tener un iterador por los meses en
        *   la página.
        */
        private List<Integer> listMesesInt = new ArrayList<Integer>();

	public MisTareasMB() {
	}

	public SofisPopupUI getRenderPopupEdicion() {
		return renderPopupEdicion;
	}

	public void setRenderPopupEdicion(SofisPopupUI renderPopupEdicion) {
		this.renderPopupEdicion = renderPopupEdicion;
	}

	public void setInicioMB(InicioMB inicioMB) {
		this.inicioMB = inicioMB;
	}

	public SofisComboG<SelectItem> getAnioCombo() {
		return anioCombo;
	}

	public void setAnioCombo(SofisComboG<SelectItem> anioCombo) {
		this.anioCombo = anioCombo;
	}

	public SofisComboG<ComboItemTO> getAvanceCombo() {
		return avanceCombo;
	}

	public void setAvanceCombo(SofisComboG<ComboItemTO> avanceCombo) {
		this.avanceCombo = avanceCombo;
	}

	public FiltroMisTareasTO getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroMisTareasTO filtro) {
		this.filtro = filtro;
	}
//
//    public List<MisTareasTO> getMisTareas() {
//        return misTareas;
//    }
//
//    public void setMisTareas(List<MisTareasTO> misTareas) {
//        this.misTareas = misTareas;
//    }

	public int getPrimerAnio() {
		return primerAnio;
	}

	public void setPrimerAnio(int primerAnio) {
		this.primerAnio = primerAnio;
	}

	public int getUltimoAnio() {
		return ultimoAnio;
	}

	public void setUltimoAnio(int ultimoAnio) {
		this.ultimoAnio = ultimoAnio;
	}

	public List<SsUsuario> getListaUsuCoord() {
		return listaUsuCoord;
	}

	public void setListaUsuCoord(List<SsUsuario> listaUsuCoord) {
		this.listaUsuCoord = listaUsuCoord;
	}

	public MisTareasTO getTareaEnEdicion() {
		return tareaEnEdicion;
	}

	public void setTareaEnEdicion(MisTareasTO tareaEnEdicion) {
		this.tareaEnEdicion = tareaEnEdicion;
	}

	public Boolean getIsCoordBusqueda() {
		return isCoordBusqueda;
	}

	public void setIsCoordBusqueda(Boolean isCoordBusqueda) {
		this.isCoordBusqueda = isCoordBusqueda;
	}

	public SofisComboG<Programas> getProgramaCombo() {
		return programaCombo;
	}

	public void setProgramaCombo(SofisComboG<Programas> programaCombo) {
		this.programaCombo = programaCombo;
	}

	public SofisComboG<SsUsuario> getUsuarioCombo() {
		return usuarioCombo;
	}

	public void setUsuarioCombo(SofisComboG<SsUsuario> usuarioCombo) {
		this.usuarioCombo = usuarioCombo;
	}

    public List<Integer> getListMesesInt() {
        return listMesesInt;
    }

    public void setListMesesInt(List<Integer> listMesesInt) {
        this.listMesesInt = listMesesInt;
    }

        
        
	@PostConstruct
	public void init() {
		this.tarea = true;
		this.producto = true;
		inicioMB.cargarOrganismoSeleccionado();
		limiteAmarilloProd = Integer.valueOf(configuracionDelegate
				.obtenerCnfPorCodigoYOrg(
						ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_AMARILLO,
						inicioMB.getOrganismoSeleccionado()).getCnfValor());
		limiteRojoProd = Integer.valueOf(configuracionDelegate
				.obtenerCnfPorCodigoYOrg(
						ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_ROJO,
						inicioMB.getOrganismoSeleccionado()).getCnfValor());
		
                for(int i=1 ; i<13 ; i++){
                    listMesesInt.add(i);
                }
                
                cargarCombosFiltro();
		buscarTareasAction();
                
                //filtro.setTareaFinalizada(Boolean.FALSE);
                
	}

	public boolean esUsuarioExterno() {
		SsUsuario u = inicioMB.getUsuario();
		Integer orgPk = inicioMB.getOrganismoSeleccionado();
		return u.isUsuarioCargaHoras(orgPk)
				&& !u.isAdministrador()
				&& !u.isUsuarioComun(orgPk)
				&& !u.isUsuarioDirector(orgPk)
				&& !u.isUsuarioPMOF(orgPk)
				&& !u.isUsuarioPMOT(orgPk);
	}

	public String mesAbreviadoStr(Integer mes) {
		if (mes != null) {
			return Labels.getValue("date_mes_abreviado_" + mes);
		}
		return null;
	}

	public int calcularLeftDia() {
		return EntregablesUtils.calcularLeftDia(filtro.getAnio(), MARGEN_INICIO, INICIO_REPORTE_GANTT, FIN_REPORTE_GANTT);
	}

	public boolean displayDayRef() {
		if (filtro.getAnio() != null) {
			Calendar cal = new GregorianCalendar();
			return filtro.getAnio().equals(cal.get(Calendar.YEAR));
		}
		return false;
	}

	/**
	 * @return the misTareasGroupProgProy
	 */
	public List<MisTareasProgProyTO> getMisTareasGroupProgProy() {
		return misTareasGroupProgProy;
	}

	/**
	 * @param misTareasGroupProgProy the misTareasGroupProgProy to set
	 */
	public void setMisTareasGroupProgProy(List<MisTareasProgProyTO> misTareasGroupProgProy) {
		this.misTareasGroupProgProy = misTareasGroupProgProy;
	}

	/**
	 * @return the tarea
	 */
	public Boolean getTarea() {
		return tarea;
	}

	/**
	 * @param tarea the tarea to set
	 */
	public void setTarea(Boolean tarea) {
		this.tarea = tarea;
	}

	/**
	 * @return the producto
	 */
	public Boolean getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Boolean producto) {
		this.producto = producto;
	}

	public String mesToStr(Integer mes, Boolean abreviado) {
		return WebUtils.mesToStr(mes, abreviado);
	}

	/**
	 * @return the hayProductos
	 */
	public Boolean getHayProductos() {
		return hayProductos;
	}

	/**
	 * @param hayProductos the hayProductos to set
	 */
	public void setHayProductos(Boolean hayProductos) {
		this.hayProductos = hayProductos;
	}

	/**
	 * @return the editarProdMap
	 */
	public Map<Integer, Boolean> getEditarProdMap() {
		return editarProdMap;
	}

	/**
	 * @param editarProdMap the editarProdMap to set
	 */
	public void setEditarProdMap(Map<Integer, Boolean> editarProdMap) {
		this.editarProdMap = editarProdMap;
	}

	public boolean editarRealProd(ProdMes prodMes, Entregables ent) {
		Calendar today = new GregorianCalendar();
		today.add(Calendar.MONTH, -1);

		Calendar calMes = new GregorianCalendar();
		calMes.set(Calendar.YEAR, prodMes.getProdmesAnio());
		calMes.set(Calendar.MONTH, prodMes.getProdmesMes() - 1);
		calMes.set(Calendar.DAY_OF_MONTH, 1);

		GregorianCalendar cEntInicioDate = new GregorianCalendar();
		cEntInicioDate.setTime(ent.getEntInicioDate());
		cEntInicioDate.add(Calendar.MONTH, -1);

		boolean esEntreFechas1 = DatesUtils.esEntreFechas(calMes.getTime(), cEntInicioDate.getTime(), ent.getEntFinDate(), "yyyyMM");
		boolean esEntreFechas2 = DatesUtils.esEntreFechas(calMes.getTime(), today.getTime(), ent.getEntFinDate(), "yyyyMM");

		//System.out.println("calMes.get(Calendar.YEAR)" +calMes.get(Calendar.YEAR));
		//System.out.println("esEntreFechas2 " +esEntreFechas2 + calMes.getTime() + ":"+ today.getTime() + ":" + ent.getEntFinDate());
		return esEntreFechas1 && esEntreFechas2;
	}

	private void cargarCombosFiltro() {

		Calendar cal = new GregorianCalendar();
		Calendar calPrimera = new GregorianCalendar();
		Date dPrimera = entregablesDelegate.obtenerPrimeraFecha(inicioMB.getOrganismo().getOrgPk());
		calPrimera.setTime(dPrimera != null ? dPrimera : new Date());
		primerAnio = calPrimera.get(Calendar.YEAR) < cal.get(Calendar.YEAR) ? calPrimera.get(Calendar.YEAR) : cal.get(Calendar.YEAR);

		Calendar calUltima = new GregorianCalendar();
		Date dUltima = entregablesDelegate.obtenerUltimaFecha(inicioMB.getOrganismo().getOrgPk());
		calUltima.setTime(dUltima != null ? dUltima : new Date());
		ultimoAnio = calUltima.get(Calendar.YEAR) > cal.get(Calendar.YEAR) ? calUltima.get(Calendar.YEAR) : cal.get(Calendar.YEAR);

		cargarAniosCombo();

		List<Programas> listaProgramas = programasDelegate.obtenerProgComboPorOrg(inicioMB.getOrganismo().getOrgPk());
		if (listaProgramas != null && !listaProgramas.isEmpty()) {
			programaCombo = new SofisComboG(listaProgramas, "progNombre");
			programaCombo.addEmptyItem(Labels.getValue("comboTodos"));
		}

		listaUsuCoord = ssUsuarioDelegate.obtenerSsUsuariosCoordEnt(inicioMB.getOrganismo().getOrgPk());
		cargarUsuCoordCombo();
	}

	public void usuarioComboValueChange(ValueChangeEvent ev) {
		misTareasGroupProgProy = null;
	}

	public String buscarTareasAction() {
		cargarCombosSeleccionados();

                editarProdMap.clear();
                if (this.misTareasGroupProgProy != null) {
                    this.misTareasGroupProgProy.clear();
                }

                misTareasGroupProgProy = entregablesDelegate
				.obtenerMisTareasPorFiltroGroupProyecto(filtro,
						inicioMB.getOrganismo().getOrgPk(), producto,
						Calendar.getInstance().get(Calendar.YEAR));
		if (misTareasGroupProgProy != null) {
			for (MisTareasProgProyTO mtppTO : misTareasGroupProgProy) {
				double esfuerzoTotal = 0;
				for (MisTareasTO misTarea : mtppTO.getTareas()) {
					esfuerzoTotal += misTarea.getEntEsfuerzo();
				}
				for (MisTareasTO misTarea : mtppTO.getTareas()) {
					misTarea.setEntEsfuerzoPorcentaje(esfuerzoTotal > 0 ? misTarea.getEntEsfuerzo() * 100 / esfuerzoTotal : 0d);
				}
			}
		}
		hayProductos = false;
		for (MisTareasProgProyTO mtppTO : misTareasGroupProgProy) {
			if (mtppTO.getTieneProductos()) {
				hayProductos = true;
				for (MisTareasTO misTarea : mtppTO.getTareas()) {
					if (misTarea.getProductos() != null) {
						for (Productos prod : misTarea.getProductos()) {
							editarProdMap.put(prod.getProdPk(), false);
						}
					}
				}
			}
		}

		isCoordBusqueda = filtro != null && filtro.getUsuCoordPk() != null && inicioMB.getUsuario().getUsuId().equals(filtro.getUsuCoordPk());
		return null;
	}
        
	public String limpiarFiltroAction() {
		programaCombo.setSelected(-1);
		usuarioCombo.setSelected(-1);
		filtro = new FiltroMisTareasTO();
//        misTareas = null;
		misTareasGroupProgProy = null;
		cargarAniosCombo();
		cargarUsuCoordCombo();
		return null;
	}

	private void cargarCombosSeleccionados() {
		Integer progPk = programaCombo.getSelected();
		if (progPk != null && progPk > 0) {
			filtro.setProgPk(progPk);
		} else {
			filtro.setProgPk(null);
		}

		Integer usuPk = usuarioCombo.getSelected();
		if (usuPk != null && usuPk > 0) {
			filtro.setUsuCoordPk(usuPk);
		} else {
			filtro.setUsuCoordPk(null);
		}

		Integer anio = (Integer) anioCombo.getSelectedT().getValue();
		if (anio != null && anio > 0) {
			filtro.setAnio(anio);
		}
	}

	public String editarTarea(MisTareasTO tarea) {
		if (tarea != null) {
			tareaEnEdicion = tarea;

			List<ComboItemTO> listaAvance = new ArrayList<>();
			ComboItemTO selected = null;
			for (int i = 0; i <= 100; i = i + 10) {
				if (selected == null || i == tarea.getEntProgreso()) {
					selected = new ComboItemTO(i, String.valueOf(i));
					listaAvance.add(selected);
				} else {
					listaAvance.add(new ComboItemTO(i, String.valueOf(i)));
				}
			}
			if (tarea.getEntProgreso() != null && (tarea.getEntProgreso() % 10 != 0)) {
				selected = new ComboItemTO(tarea.getEntProgreso(), String.valueOf(tarea.getEntProgreso()));
				listaAvance.add(selected);
			}

			listaAvance = ComboItemTOUtils.sortByObjIfInteger(listaAvance, true);
			avanceCombo = new SofisComboG(listaAvance, "itemNombre");
			avanceCombo.setSelectedT(selected);

			renderPopupEdicion.abrir();
		}

		return null;
	}

	public String guardar(MisTareasTO ent) {
		//ComboItemTO comboItemSel = avanceCombo.getSelectedT() != null ? (ComboItemTO) avanceCombo.getSelectedT() : null;
		//tareaEnEdicion.setEntProgreso((Integer) comboItemSel.getItemObject());

		try {

                    //  for (MisTareasTO ent : misTareas) {
                    //     entregablesDelegate.actualizarAvance(ent.getProyPk(), ent.getEntPk(), ent.getEntProgreso(), inicioMB.getUsuario());
                    //}
                    
                    boolean esHito = entregablesDelegate.entregableEsHito(ent.getEntPk());
                    if (esHito && ent.getEntProgreso() != 0 && ent.getEntProgreso() != 100) {
                        JSFUtils.agregarMsgError(ConstantesPresentacion.MESSAGE_ID_POPUP, Labels.getValue("error_rep_hito_progreso"), null);
                        
                    } else if(ent.getEntProgreso() >= 0 && ent.getEntProgreso() <= 100){
                        entregablesDelegate.actualizarAvance(ent.getProyPk(), ent.getEntPk(), ent.getEntProgreso(), inicioMB.getUsuario());
                        
                        JSFUtils.agregarMsg(ConstantesPresentacion.MESSAGE_ID_POPUP, Labels.getValue("rep_tareas_avance_modif"), null);

                    }else{
                        JSFUtils.agregarMsgError(ConstantesPresentacion.MESSAGE_ID_POPUP, Labels.getValue("error_rep_tareas_avance_cotas"), null);
                    }
                    
                    inicioMB.setRenderPopupMensajes(Boolean.TRUE);

                /*
                    if (ent != null) {
                        renderPopupEdicion.cerrar();
                        for (MisTareasTO tarea : misTareas) {
                            if (tarea.equals(tareaEnEdicion)) {
                                tarea = tareaEnEdicion;
                                break;
                            }  
                        }
                        JSFUtils.agregarMsg("msjPopup", Labels.getValue("rep_tareas_avance_modif"), null);
                    }
                */

		} catch (BusinessException be) {
			logger.log(Level.SEVERE, be.getMessage(), be);

                        for(String iterStr : be.getErrores()){
                            JSFUtils.agregarMsgError(MIS_TAREAS_MSG, Labels.getValue(iterStr), null);                
                        }                        
                        inicioMB.setRenderPopupMensajes(Boolean.TRUE);  
		}
		return null;
	}

	public void irGannt(MisTareasTO ent) {
		//muestra el gannt
		String s = "2-" + ent.getProyPk();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().setAttribute(ConstantesPresentacion.PROG_PROY_ID, s);
		request.getSession().setAttribute(ConstantesPresentacion.MIS_TAREAS, "true");
		String url = StringsUtils.concat("window.open('", request.getContextPath(), "/paginasPrivadas/ficha.jsf", "','');");
		JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), url);
	}

	public String getProdMesAcuRealColor(ProdMes prodMes) {
		return productosDelegate.prodMesAcuRealColor(prodMes, inicioMB.getOrganismo().getOrgPk(), limiteAmarilloProd, limiteRojoProd);
	}

	public void cancelar() {
		//renderPopupEdicion.cerrar();
		buscarTareasAction();
	}

	public boolean entAtrasado(Date entFin, Date entFinLineaBase) {
		return EntregablesUtils.entAtrasado(entFin, entFinLineaBase);
	}

	public int calcularLeftEntByDate(Date inicio) {
		return EntregablesUtils.calcularLeftEntByDate(inicio, filtro.getAnio(), INICIO_REPORTE_GANTT, FIN_REPORTE_GANTT);
	}

	public int calcularWitdhEntByDate(Date inicio, Date fin, Integer duracion) {
		return EntregablesUtils.calcularWitdhEntByDate(inicio, fin, duracion, filtro.getAnio(), INICIO_REPORTE_GANTT, FIN_REPORTE_GANTT);
	}

	public boolean displayEntLineaBase(MisTareasTO tarea, Integer tareaId) {
		if (tarea != null) {
			return DatesUtils.isAnioEntreFechas(tarea.getEntInicioLineaBaseDate(), tarea.getEntFinLineaBaseDate(), filtro.getAnio());
		}
		return false;
	}

	private void cargarAniosCombo() {
		Calendar cal = new GregorianCalendar();
		List<SelectItem> listaAnios = new ArrayList<SelectItem>();
		SelectItem itemActual = null;
		for (int i = primerAnio; i <= ultimoAnio; i++) {
			SelectItem item = new SelectItem(i, "" + i);
			listaAnios.add(item);
			if (i == cal.get(Calendar.YEAR)) {
				itemActual = item;
			}
		}
		anioCombo = new SofisComboG(listaAnios, "label");
		anioCombo.setSelectedT(itemActual);
	}

	private void cargarUsuCoordCombo() {
		if (listaUsuCoord != null) {
			SsUsuario usu = inicioMB.getUsuario();
			if (!listaUsuCoord.contains(usu)) {
				listaUsuCoord.add(usu);
			}
			usuarioCombo = new SofisComboG<>(listaUsuCoord, "nombreApellido");
			usuarioCombo.setSelectedT(usu);
		}
	}

	public String calcularAcumuladoProd(Productos prod) {
		prod = productosDelegate.calcularAcumulados(prod);
		return null;
	}

	public String retrocederAnioProdMes(Productos prod) {
		prod.setAnio(prod.getAnio() - 1);
		prod = productosDelegate.cargarProdMesAuxiliar(prod, prod.getAnio());

		return null;
	}

	public String avanzarAnioProdMes(Productos prod) {
		prod.setAnio(prod.getAnio() + 1);
		prod = productosDelegate.cargarProdMesAuxiliar(prod, prod.getAnio());
		return null;
	}

	public String guardarProducto(Productos prod) {
		if (prod != null) {
			try {
				prod = productosDelegate.guardarProducto(prod, true);
				if (prod != null) {
                                    editarProducto(prod);
                                    JSFUtils.agregarMsg(ConstantesPresentacion.MESSAGE_ID_POPUP, "info_producto_guardado", null);                                    
                                    inicioMB.setRenderPopupMensajes(Boolean.TRUE);
				}
			} catch (BusinessException be) {
                            logger.log(Level.SEVERE, be.getMessage());
                            JSFUtils.agregarMsgs(ConstantesPresentacion.MESSAGE_ID_POPUP, be.getErrores());
                            inicioMB.setRenderPopupMensajes(Boolean.TRUE);

                            return null;
			}
		}
		return null;
	}

	public String editarProducto(Productos prod) {
		if (!editarProdMap.containsKey(prod.getProdPk())
				|| editarProdMap.get(prod.getProdPk())) {
			editarProdMap.put(prod.getProdPk(), false);
		} else {
			editarProdMap.put(prod.getProdPk(), true);
		}

		return null;
	}

	public String eliminarProducto(Productos prod) {
		try {
			productosDelegate.eliminarProducto(prod.getProdPk());
			JSFUtils.removerMensages();
			JSFUtils.agregarMsg(ConstantesPresentacion.MESSAGE_ID_POPUP, "info_producto_eliminado", null);
			inicioMB.setRenderPopupMensajes(Boolean.TRUE);
                        buscarTareasAction();
		} catch (BusinessException be) {
                        /*
                        *  18-06-2018 Inspección de código.
                        */

                        //JSFUtils.agregarMsgs(ConstantesPresentacion.MESSAGE_ID_POPUP, be.getErrores());

                        for(String iterStr : be.getErrores()){
                            JSFUtils.agregarMsgError(ConstantesPresentacion.MESSAGE_ID_POPUP, Labels.getValue(iterStr), null);                
                        }                          
			inicioMB.setRenderPopupMensajes(Boolean.TRUE);
		}
		return null;
	}

    /***
     * Devuelve si se debería mostrar cierto campo dependiendo del usuario y el estado del proyecto
     * @param proyPk Id del proyecto
     * @param field Campo a mosrtar
     * @return true or false
     */
    public Boolean renderField(Integer proyPk, String field) {
        Boolean isUsuario = Objects.equals(usuarioCombo.getSelected(), inicioMB.getUsuario().getUsuId());

        if (isUsuario) {
            
            if (field.equals("")) {
                return true;
            }

            // Obtengo el proyecto para sacar el estado
            Proyectos proyecto = proyectosDelegate.obtenerProyPorId(proyPk);

            // El botón de eliminar no se muestra si está en estado de Ejecución o Finalizado
            if (field.equals("Eliminar")) {
                if (proyecto.getProyEstFk().isEstado(Estados.ESTADOS.EJECUCION.estado_id)) return false;
                
                if (proyecto.getProyEstFk().isEstado(Estados.ESTADOS.FINALIZADO.estado_id)) return false;
            }
            
            // El botón de editar no se muestra si está en estado Finalizado
            if (field.equals("Editar")) {
                if (proyecto.getProyEstFk().isEstado(Estados.ESTADOS.FINALIZADO.estado_id)) return false;
            }

            // Los otros estados
            return true;
        }

        // Si no es el mismo usuario, no muestra los botones
        return false;
    }
}
