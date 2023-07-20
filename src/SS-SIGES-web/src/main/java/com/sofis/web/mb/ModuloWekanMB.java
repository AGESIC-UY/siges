package com.sofis.web.mb;

import com.sofis.business.ejbs.wekan.TarjetaBean;
import com.sofis.business.ejbs.wekan.WekanBean;
import com.sofis.business.utils.IntegracionWekanUtils;
import com.sofis.entities.constantes.ActualizacionWekan;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.enums.OrigenActividad;
import com.sofis.entities.tipos.EntregableTO;
import com.sofis.entities.tipos.wekan.ActividadTO;
import com.sofis.entities.tipos.wekan.EntregableTarjetaTO;
import com.sofis.entities.tipos.wekan.TableroTO;
import com.sofis.entities.tipos.wekan.VinculacionTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.delegates.wekan.ActividadDelegate;
import com.sofis.web.delegates.wekan.VinculacionDelegate;
import com.sofis.web.genericos.constantes.ConstantesPresentacion;
import com.sofis.web.presentacion.tipos.Seleccionable;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ViewScoped
@ManagedBean(name = "wekanMB")
public class ModuloWekanMB {

	private static final Logger LOGGER = Logger.getLogger(ModuloWekanMB.class.getName());
	private static final String VINCULACION_MSG = "vinculacionMsg";

	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(ConstantesPresentacion.CALENDAR_PATTERN);

	private static final String CAMBIO_SIGES_ROW_STYLE_CLASS = "wekan-cambio-siges-row";
	private static final String CAMBIO_WEKAN_ROW_STYLE_CLASS = "wekan-cambio-wekan-row";

	private String urlTablero;
	private String usuarioTablero;
	private String contraseniaTablero;

	private VinculacionTO vinculacion;
	private TableroTO tablero;

	private Cronogramas cronograma;

	private boolean vinculandoEntregables;
	private List<EntregableTarjetaTO> entregables = new ArrayList<>();

	private final List<Seleccionable<EntregableTarjetaTO>> seleccionEntregables = new ArrayList<>();
	private List<ActividadTO> actividades;

	private boolean mostrarSoloEntregablesVinculables;

	@ManagedProperty("#{fichaMB}")
	private FichaMB fichaMB;

	@Inject
	private VinculacionDelegate vinculacionDelegate;

	@Inject
	private ActividadDelegate actividadDelegate;
        
        @Inject
	private TarjetaBean tarjetaBean;

        @Inject
	private WekanBean wekanBean;
        
	@PostConstruct
	public void init() {

		if (!fichaMB.getFichaTO().isProyecto() || fichaMB.getFichaTO().getCroFk() == null) {

			return;
		}

		cronograma = fichaMB.getFichaTO().getCroFk();
		vinculacion = vinculacionDelegate.obtenerPorIdCronograma(cronograma.getCroPk());

		if (vinculacion != null) {
			tablero = vinculacion.getTablero();

			actividades = actividadDelegate.obtenerActividadesCambiosEnDatosVinculacion(vinculacion.getTablero().getId(),
					vinculacion.getCronograma().getId());
		}
	}

	public void vincularTablero() {

		try {
			vinculacion = vinculacionDelegate.vincularTablero(cronograma.getCroPk(), urlTablero, usuarioTablero, contraseniaTablero);
			tablero = vinculacion.getTablero();
                        
                        JSFUtils.agregarMsgInfo(VINCULACION_MSG, Labels.getValue("wekan_info_tablero_vinculado_wekan"), null);
		
                } catch (BusinessException be) {

			for (String iterStr : be.getErrores()) {
				JSFUtils.agregarMsgError(VINCULACION_MSG, Labels.getValue(iterStr), null);
			}
		}
	}
        
        public void desvincularTablero() {
		try {
			vinculacionDelegate.desvincularTablero(cronograma.getCroPk());
			tablero = null;
                        
                        JSFUtils.agregarMsgInfo(VINCULACION_MSG, Labels.getValue("wekan_info_desvincular_tablero_wekan"), null);
                } catch (BusinessException be) {
			for (String iterStr : be.getErrores()) {
				JSFUtils.agregarMsgError(VINCULACION_MSG, Labels.getValue(iterStr), null);
			}
		}
	}
        
        public void restaurarVinculacion() {
		try {
			vinculacionDelegate.restaurarVinculacion(cronograma.getCroPk());
			//tablero = null;
                        JSFUtils.agregarMsgInfo(VINCULACION_MSG, Labels.getValue("wekan_info_restaurar_vinculacion_wekan"), null);
                        
                } catch (BusinessException be) {
			for (String iterStr : be.getErrores()) {
				JSFUtils.agregarMsgError(VINCULACION_MSG, Labels.getValue(iterStr), null);
			}
		}
	}

	public void mostrarVinculacionEntregables() {

		try {
			entregables = vinculacionDelegate.obtenerVinculacionEntregables(cronograma.getCroPk());

			seleccionEntregables.clear();

			for (EntregableTarjetaTO entregable : entregables) {

				seleccionEntregables.add(new Seleccionable<>(entregable));
			}

			vinculandoEntregables = true;                       
		} catch (BusinessException be) {

			for (String iterStr : be.getErrores()) {
				JSFUtils.agregarMsgError(VINCULACION_MSG, Labels.getValue(iterStr), null);
			}
		}
	}

	public String vincularEntregables() {
                
		List<EntregableTO> entregablesSeleccionados = new ArrayList<>();

		for (Seleccionable<EntregableTarjetaTO> e : seleccionEntregables) {

			if (e.isSeleccionado()) {

				entregablesSeleccionados.add(e.getObjeto().getEntregable());
			}
		}

		try {
			vinculacionDelegate.vincularEntregables(cronograma.getCroPk(), entregablesSeleccionados);

			mostrarVinculacionEntregables();

                        if (!entregablesSeleccionados.isEmpty()){
                            JSFUtils.agregarMsgInfo(VINCULACION_MSG, Labels.getValue("wekan_info_entregables_vinculados_wekan"), null);
                        }else{
                            JSFUtils.agregarMsgError(VINCULACION_MSG, Labels.getValue("wekan_error_entregables_vinculados_wekan"), null);
                        }
                        
		} catch (BusinessException be) {

			for (String iterStr : be.getErrores()) {
				JSFUtils.agregarMsgError(VINCULACION_MSG, Labels.getValue(iterStr), null);
			}
		}

		return null;
	}
        
        public String desvincularEntregable(Object s) {
                
		try {
                    EntregableTarjetaTO entregableTarjeta = ((Seleccionable<EntregableTarjetaTO>)s).getObjeto();
                    Integer idEntregable = entregableTarjeta.getEntregable().getId();
                    
                    vinculacionDelegate.desvincularEntregable(idEntregable);
                    
                    mostrarVinculacionEntregables();
                            
                    JSFUtils.agregarMsgInfo(VINCULACION_MSG, Labels.getValue("wekan_info_entregable_desvinculado_wekan"), null);
                    
                } catch (BusinessException be) {

                    JSFUtils.agregarMsgError(VINCULACION_MSG, Labels.getValue("wekan_error_entregable_desvinculado_wekan"), null);
		}

		return null;
	}

	public String cerrarVinculacionEntregables() {

		vinculandoEntregables = false;

		return null;
	}

	public String procesarValorActividad(String actualizacion, String valor) {
		String result = valor;
		if (ActualizacionWekan.FECHA_INICIO_ENTREGABLE.equals(actualizacion)
				|| ActualizacionWekan.FECHA_FIN_ENTREGABLE.equals(actualizacion)) {

			try {
				result = DATE_FORMATTER.format(IntegracionWekanUtils.DATE_FORMATTER.parse(valor));
			} catch (ParseException ex) {
				LOGGER.log(Level.SEVERE, "error_procesar_fecha", ex);
			}
		}

		return result;
	}

	public String procesarActualizacionActividad(String actualizacion) {

		String result = null;

		switch (actualizacion) {
			case ActualizacionWekan.FECHA_INICIO_ENTREGABLE:
				result = Labels.getValue("wekan_actividad_fecha_inicio");
				break;
			case ActualizacionWekan.FECHA_FIN_ENTREGABLE:
				result = Labels.getValue("wekan_actividad_fecha_fin");
				break;
                        case ActualizacionWekan.NOMBRE_ENTREGABLE:
				result = Labels.getValue("wekan_actividad_nombre");
				break;
			case ActualizacionWekan.PROGRESO_ENTREGABLE:
				result = Labels.getValue("wekan_actividad_progreso");
				break;
			case ActualizacionWekan.CAMBIO_LISTA_TARJETA:
				result = Labels.getValue("wekan_actividad_cambio_lista");
				break;
		}

		return result;
	}

	public String obtenerActividadesRowClasses(ActividadTO actividad) {

		String rowClasses = null;

		if (OrigenActividad.WEKAN.equals(actividad.getOrigen())) {
			rowClasses = CAMBIO_WEKAN_ROW_STYLE_CLASS;

		} else if (OrigenActividad.SIGES.equals(actividad.getOrigen())) {
			rowClasses = CAMBIO_SIGES_ROW_STYLE_CLASS;
		}

		return rowClasses;
	}

	public List<Seleccionable<EntregableTarjetaTO>> obtenerEntregablesVinculables() {

		List<Seleccionable<EntregableTarjetaTO>> result = new ArrayList<>();

		for (Seleccionable<EntregableTarjetaTO> et : seleccionEntregables) {

			if (et.getObjeto().isVinculable() || !mostrarSoloEntregablesVinculables) {
				result.add(et);
			}
		}

		return result;
	}

	public FichaMB getFichaMB() {
		return fichaMB;
	}

	public void setFichaMB(FichaMB fichaMB) {
		this.fichaMB = fichaMB;
	}

	public String getUrlTablero() {
		return urlTablero;
	}

	public void setUrlTablero(String urlTablero) {
		this.urlTablero = urlTablero;
	}

	public String getUsuarioTablero() {
		return usuarioTablero;
	}

	public void setUsuarioTablero(String usuarioTablero) {
		this.usuarioTablero = usuarioTablero;
	}

	public String getContraseniaTablero() {
		return contraseniaTablero;
	}

	public void setContraseniaTablero(String contraseniaTablero) {
		this.contraseniaTablero = contraseniaTablero;
	}

	public TableroTO getTablero() {
		return tablero;
	}

	public Cronogramas getCronograma() {
		return cronograma;
	}

	public boolean getVinculandoEntregables() {
		return vinculandoEntregables;
	}

	public void setVinculandoEntregables(boolean vinculandoEntregables) {
		this.vinculandoEntregables = vinculandoEntregables;
	}

	public List<EntregableTarjetaTO> getEntregables() {
		return entregables;
	}

	public void setEntregables(List<EntregableTarjetaTO> entregables) {
		this.entregables = entregables;
	}

	public List<Seleccionable<EntregableTarjetaTO>> getSeleccionEntregables() {
		return seleccionEntregables;
	}

	public List<ActividadTO> getActividades() {
		return actividades;
	}

	public Boolean getMostrarSoloEntregablesVinculables() {
		return mostrarSoloEntregablesVinculables;
	}

	public void setMostrarSoloEntregablesVinculables(Boolean mostrarSoloEntregablesVinculables) {
		this.mostrarSoloEntregablesVinculables = mostrarSoloEntregablesVinculables;
	} 
            
        
}
