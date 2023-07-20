package com.sofis.web.mb;

import com.sofis.entities.data.Areas;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.EstadosPublicacion;
import com.sofis.entities.data.MediaProyectos;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.tipos.FiltroExpVisuaTO;
import com.sofis.exceptions.GeneralException;
import com.sofis.web.delegates.AreasDelegate;
import com.sofis.web.delegates.EstadosPublicacionDelegate;
import com.sofis.web.delegates.MediaProyectosDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.enums.FieldAttributeEnum;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisComboG;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import org.omnifaces.util.Ajax;

@ManagedBean(name = "exportarVisualizadorMB")
@ViewScoped
public class ExportarVisualizadorMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ExportarVisualizadorMB.class.getName());

	@ManagedProperty("#{inicioMB}")
	private InicioMB inicioMB;

	@Inject
	private EstadosPublicacionDelegate estadosPublicacionDelegate;

	@Inject
	private ProyectosDelegate proyectosDelegate;

	@Inject
	private AreasDelegate areasDelegate;
        
        @Inject
	private MediaProyectosDelegate mediaProyDelegate;

	private FiltroExpVisuaTO filtro = new FiltroExpVisuaTO();
	private SofisComboG<EstadosPublicacion> listaEstPublicacionCombo;
	private SofisComboG<Areas> listaAreasCombo;
	private List<FichaTO> listFichaTO;

	private Integer countProy;
	private Integer totalProy;
	private Integer totalProySucces;
	private Integer totalProyError;
	private Integer progressProy;

	private Thread exportarProyectosThread;

	private Boolean fullContent = false;
	private Boolean todoSeleccionado = false;
	private Boolean proySinImagenPrin = false;

    	@PostConstruct
	public void init() {
		cargarFiltroBusqueda();
	}

	public boolean fieldRendered(String fieldName) {
		//dependiendo del usuario, estado etc es si esta habilitado o no
		return fieldAttribute(fieldName, FieldAttributeEnum.RENDERED);
	}

	private boolean fieldAttribute(String fieldName, FieldAttributeEnum field) {

		boolean checkDisabled = field == FieldAttributeEnum.DISABLED;
		boolean checkRendered = field == FieldAttributeEnum.RENDERED;

		boolean disabled = false;
		boolean rendered = true;
		boolean isEditor = inicioMB.isUsuarioOrgaEditor();

		if (fieldName.equalsIgnoreCase("btnExportarVisualizador")) {
			if (!isEditor) {
				rendered = false;
			}
		}

		if (checkDisabled) {
			return disabled;
		} else if (checkRendered) {
			return rendered;
		} else {
			return false;
		}
	}

	public String buscar() {
		countProy = null;
		totalProy = null;
		progressProy = null;

		List<Estados> listEstados = new ArrayList<>();
		listEstados.add(new Estados(Estados.ESTADOS.INICIO.estado_id));
		listEstados.add(new Estados(Estados.ESTADOS.PLANIFICACION.estado_id));
		listEstados.add(new Estados(Estados.ESTADOS.EJECUCION.estado_id));
		listEstados.add(new Estados(Estados.ESTADOS.FINALIZADO.estado_id));

		filtro.setEstados(listEstados);
		filtro.setOrgPk(inicioMB.getOrganismo().getOrgPk());

		EstadosPublicacion estPub = listaEstPublicacionCombo.getSelectedT();
		filtro.setEstadoPublicacion(estPub);

		Areas areas = listaAreasCombo.getSelectedT();
		filtro.setArea(areas);

		listFichaTO = proyectosDelegate.buscarPorFiltro(filtro);
		
		todoSeleccionado = false;

		return null;
	}

	public String limpiarFiltro() {
		filtro = new FiltroExpVisuaTO();
		listaEstPublicacionCombo.setSelected(-1);
		listaAreasCombo.setSelected(-1);
		listFichaTO = null;

		countProy = null;
		totalProy = null;
		progressProy = null;

		return null;
	}

	public String exportar() {

		Integer orgPk = inicioMB.getOrganismo().getOrgPk();
		SsUsuario usuario = inicioMB.getUsuario();
		LOGGER.log(Level.INFO, "Exportando Proyectos... (Org. {0})", orgPk);
		if (listFichaTO != null) {
			totalProy = 0;
			countProy = 0;
			totalProySucces = 0;
			totalProyError = 0;
			progressProy = 0;
                        proySinImagenPrin = false;

			for (FichaTO ficha : listFichaTO) {
				if (ficha.getSeleccionado() != null && ficha.getSeleccionado().equals(Boolean.TRUE)) {
					totalProy++;
				}
			}
			if (totalProy != 0) {
				for (FichaTO ficha : listFichaTO) {
					if (ficha.getSeleccionado() != null && ficha.getSeleccionado().equals(Boolean.TRUE)) {
						try {
                                                    // Valido existencia de imagen principal    
                                                    tieneImagenPrincipalYPublicable(ficha);

                                                    LOGGER.log(Level.INFO, "Exportando Proyecto ({0})", ficha.getProyProgId());
                                                    Proyectos proy = proyectosDelegate.exportarVisualizador(ficha.getFichaFk(), usuario, fullContent);
                                                    totalProySucces++;

                                                    //Actualizar la fecha en la ficha.
                                                    ficha.setExportado(Boolean.TRUE);
                                                    ficha.setFechaPublicacion(proy.getUltimaPublicacion() != null ? proy.getUltimaPublicacion().getProyPublicaFecha() : null);

						} catch (GeneralException ge) {
                                                    LOGGER.log(Level.WARNING, null, ge);
                                                    totalProyError++;
                                                    ficha.setExportado(Boolean.FALSE);
                                                    String desc = "";
                                                    if (ge.getErrores() != null) {
                                                            for (String s : ge.getErrores()) {
                                                                    desc += (Labels.containsKey(s) ? Labels.getValue(s) : s) + " ";
                                                            }
                                                    }
                                                    LOGGER.log(Level.INFO, "ERROR Exportando Proyecto: {0}", desc);
                                                    ficha.setErrorDesc(desc);
						}
						countProy++;
					}
					progressProy = countProy * 100 / totalProy;
				}
			} else {

				JSFUtils.agregarMsg(null, Labels.getValue("error_exportar_no_select_proyect"), null);
			}
		}

		return null;
	}

	public String cancelarExportar() {
		if (exportarProyectosThread != null && exportarProyectosThread.isAlive()) {
			exportarProyectosThread.interrupt();
		}
		return null;
	}

	private void cargarFiltroBusqueda() {
		List<EstadosPublicacion> listEP = estadosPublicacionDelegate.obtenerTodos();
		if (listEP != null) {
			listaEstPublicacionCombo = new SofisComboG<>(listEP, "estPubNombre");
		}

		List<Areas> listArea = areasDelegate.obtenerAreasPorOrganismo(inicioMB.getOrganismo().getOrgPk(), false);
		if (listArea != null) {
			listaAreasCombo = new SofisComboG<>(listArea, "areaNombre");
			listaAreasCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
		}
	}

	public void seleccionarTodo(ValueChangeEvent event) {

		Boolean seleccionado = (Boolean) event.getNewValue();
		for (FichaTO fichaTO : listFichaTO) {

			if (fichaTO.getPublicable()) {

				fichaTO.setSeleccionado(seleccionado);
			}
		}
	}

	public void seleccionarProyecto(ValueChangeEvent event) {

		boolean seleccionado = (Boolean) event.getNewValue();
		int noSeleccionados = 0;

		for (FichaTO fichaTO : listFichaTO) {

			if (!fichaTO.getSeleccionado()) {
				noSeleccionados++;

				if (noSeleccionados > 1) {
					break;
				}
			}
		}

		// la variable del proyecto seleccionado aun no fue actualizada
		todoSeleccionado = (noSeleccionados <= 1) && seleccionado;

		Ajax.updateAll();
	}
        
        public void tieneImagenPrincipalYPublicable(FichaTO ficha) {
            Boolean tieneImgPrin = false;
            List<MediaProyectos> mp = mediaProyDelegate.obtenerPorProyId(ficha.getFichaFk());       

            if (mp != null)
                for (MediaProyectos mediaProy :mp)
                    tieneImgPrin = tieneImgPrin || (mediaProy.getMediaPrincipal() && mediaProy.esMediaPublicable());    
            if (!tieneImgPrin){
                proySinImagenPrin = true;
                GeneralException ge = new GeneralException("El proyecto '"+ficha.getNombre()+"' no tiene una imagen principal publicable.");
                ge.addError("El proyecto '"+ficha.getNombre()+"' no tiene una imagen principal publicable.");
                throw ge;
            }
	}
        
	public Boolean getFullContent() {
		return fullContent;
	}

	public void setFullContent(Boolean fullContent) {
		this.fullContent = fullContent;
	}

	public void setInicioMB(InicioMB inicioMB) {
		this.inicioMB = inicioMB;
	}

	public FiltroExpVisuaTO getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroExpVisuaTO filtro) {
		this.filtro = filtro;
	}

	public SofisComboG<EstadosPublicacion> getListaEstPublicacionCombo() {
		return listaEstPublicacionCombo;
	}

	public void setListaEstPublicacionCombo(SofisComboG<EstadosPublicacion> listaEstPublicacionCombo) {
		this.listaEstPublicacionCombo = listaEstPublicacionCombo;
	}

	public SofisComboG<Areas> getListaAreasCombo() {
		return listaAreasCombo;
	}

	public void setListaAreasCombo(SofisComboG<Areas> listaAreasCombo) {
		this.listaAreasCombo = listaAreasCombo;
	}

	public List<FichaTO> getListFichaTO() {
		return listFichaTO;
	}

	public void setListFichaTO(List<FichaTO> listFichaTO) {
		this.listFichaTO = listFichaTO;
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

	public Integer getTotalProySucces() {
		return totalProySucces;
	}

	public void setTotalProySucces(Integer totalProySucces) {
		this.totalProySucces = totalProySucces;
	}

	public Integer getTotalProyError() {
		return totalProyError;
	}

	public void setTotalProyError(Integer totalProyError) {
		this.totalProyError = totalProyError;
	}

	public Integer getProgressProy() {
		return progressProy;
	}

	public void setProgressProy(Integer progressProy) {
		this.progressProy = progressProy;
	}

	public Thread getExportarProyectosThread() {
		return exportarProyectosThread;
	}

	public void setExportarProyectosThread(Thread exportarProyectosThread) {
		this.exportarProyectosThread = exportarProyectosThread;
	}

	public Boolean getTodoSeleccionado() {
		return todoSeleccionado;
	}

	public void setTodoSeleccionado(Boolean todoSeleccionado) {
		this.todoSeleccionado = todoSeleccionado;
	}
        public Boolean getProySinImagenPrin() {
                return proySinImagenPrin;
            }

            public void setProySinImagenPrin(Boolean proySinImagenPrin) {
                this.proySinImagenPrin = proySinImagenPrin;
            }
}
