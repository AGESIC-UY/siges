package com.sofis.web.mb;

import com.sofis.entities.data.AreasTags;
import com.sofis.entities.tipos.FiltroAreaTematicaTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.AreaTematicaDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisCombo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import org.icefaces.util.JavaScriptRunner;

@ManagedBean(name = "areaTematicaMB")
@ViewScoped
public class AreaTematicaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(AreaTematicaMB.class.getName());

	private static final String POPUP_MSG = "popupMsg";
	private static final String AREAS_TAGS_NOMBRE = "areatagNombre";

	@ManagedProperty("#{inicioMB}")
	private InicioMB inicioMB;

	@Inject
	private AreaTematicaDelegate areaTematicaDelegate;

	private SofisPopupUI popupEdicion;

	private String cantElementosPorPagina = "25";
	private String elementoOrdenacion = AREAS_TAGS_NOMBRE;

	private boolean ascendente = true;
	private String filtroNombre;
	private List<AreasTags> listaResultado;
	private AreasTags areaTemEnEdicion;
	private SofisCombo listaAreasTagsCombo;

	private Boolean habilitacionOriginal;

	@PostConstruct
	public void init() {

		popupEdicion = new SofisPopupUI();

		filtroNombre = "";
		listaResultado = new ArrayList<>();
		areaTemEnEdicion = new AreasTags();
		listaAreasTagsCombo = new SofisCombo();

		inicioMB.cargarOrganismoSeleccionado();

		buscar();
	}

	public String agregar() {
		areaTemEnEdicion = new AreasTags();
		areaTemEnEdicion.setHabilitada(true);

		actualizarListaSeleccionables();

		popupEdicion.abrir();

		return null;
	}

	public String eliminar(Integer atPk) {
		if (atPk != null) {
			try {
				areaTematicaDelegate.eliminarAreaTematica(atPk);

				for (AreasTags at : listaResultado) {
					if (at.getArastagPk().equals(atPk)) {
						listaResultado.remove(at);
						break;
					}
				}

			} catch (BusinessException e) {
				LOGGER.log(Level.SEVERE, null, e);

				for (String iterStr : e.getErrores()) {
					JSFUtils.agregarMsgError("", Labels.getValue(iterStr), null);
				}
				inicioMB.setRenderPopupMensajes(Boolean.TRUE);
			}
		}

		return null;
	}

	public String buscar() {

		FiltroAreaTematicaTO filtro = new FiltroAreaTematicaTO();
		filtro.setIdOrganismo(inicioMB.getOrganismo().getOrgPk());
		filtro.setNombre(filtroNombre);

		listaResultado = areaTematicaDelegate.buscar(filtro, elementoOrdenacion, ascendente);

		return null;
	}

	public String editar(Integer atPk) {
		try {
			areaTemEnEdicion = areaTematicaDelegate.obtenerAreaTemPorPk(atPk);
			habilitacionOriginal = areaTemEnEdicion.getHabilitada();

			actualizarListaSeleccionables();

			popupEdicion.abrir();

		} catch (BusinessException ex) {
			LOGGER.log(Level.SEVERE, null, ex);

			for (String iterStr : ex.getErrores()) {
				JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);
			}

		}
		return null;
	}

	private void actualizarListaSeleccionables() {

		FiltroAreaTematicaTO filtro = new FiltroAreaTematicaTO();
		filtro.setIdOrganismo(inicioMB.getOrganismo().getOrgPk());
		filtro.setHabilitada(true);

		List<AreasTags> listAreasTags = areaTematicaDelegate.buscar(filtro, elementoOrdenacion);
		
		if ((areaTemEnEdicion.getAreatagPadreFk() != null) && !listAreasTags.contains(areaTemEnEdicion.getAreatagPadreFk())) {
			
			listAreasTags.add(areaTemEnEdicion.getAreatagPadreFk());
		}

		ListIterator<AreasTags> iter = listAreasTags.listIterator();
		while (iter.hasNext()) {
			if (iter.next().getArastagPk().equals(areaTemEnEdicion.getArastagPk())) {
				iter.remove();
				break;
			}
		}

		listaAreasTagsCombo = new SofisCombo((List) listAreasTags, AREAS_TAGS_NOMBRE);
		listaAreasTagsCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

		listaAreasTagsCombo.setSelectedObject(areaTemEnEdicion.getAreatagPadreFk());
	}

	public String guardar() {

		if (areaTemEnEdicion.getArastagPk() == null || areaTemEnEdicion.getHabilitada().equals(habilitacionOriginal)) {

			return confirmarGuardar();
		}

		JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), "popupConfirmar.show();");

		return null;

	}

	public String confirmarGuardar() {
		AreasTags areasTagsSelected = (AreasTags) listaAreasTagsCombo.getSelectedObject();

		areaTemEnEdicion.setAreatagOrgFk(inicioMB.getOrganismo());
		areaTemEnEdicion.setAreatagPadreFk(areasTagsSelected);

		try {
			areaTemEnEdicion = areaTematicaDelegate.guardarAreaTematica(areaTemEnEdicion);

			popupEdicion.cerrar();

			buscar();

		} catch (BusinessException be) {
			LOGGER.log(Level.SEVERE, be.getMessage(), be);

			for (String iterStr : be.getErrores()) {
				JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);
			}

		}
		return null;
	}

	public void cancelar() {
		popupEdicion.cerrar();
	}

	public String limpiar() {
		filtroNombre = null;
		listaResultado = null;
		elementoOrdenacion = AREAS_TAGS_NOMBRE;
		ascendente = true;

		return null;
	}

	public void cambiarCantPaginas(ValueChangeEvent evt) {
		buscar();
	}

	public void cambiarCriterioOrdenacion(ValueChangeEvent evt) {
		elementoOrdenacion = evt.getNewValue().toString();
		buscar();
	}

	public void cambiarAscendenteOrdenacion(ValueChangeEvent evt) {
		ascendente = (Boolean) evt.getNewValue();

		buscar();
	}

	public void setInicioMB(InicioMB inicioMB) {
		this.inicioMB = inicioMB;
	}

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	public List<AreasTags> getListaResultado() {
		return listaResultado;
	}

	public void setListaResultado(List<AreasTags> listaResultado) {
		this.listaResultado = listaResultado;
	}

	public AreasTags getAreaTemEnEdicion() {
		return areaTemEnEdicion;
	}

	public void setAreaTemEnEdicion(AreasTags areaTemEnEdicion) {
		this.areaTemEnEdicion = areaTemEnEdicion;
	}

	public SofisPopupUI getPopupEdicion() {
		return popupEdicion;
	}

	public void setPopupEdicion(SofisPopupUI popupEdicion) {
		this.popupEdicion = popupEdicion;
	}

	public SofisCombo getListaAreasTagsCombo() {
		return listaAreasTagsCombo;
	}

	public void setListaAreasTagsCombo(SofisCombo listaAreasTagsCombo) {
		this.listaAreasTagsCombo = listaAreasTagsCombo;
	}

	public String getCantElementosPorPagina() {
		return cantElementosPorPagina;
	}

	public void setCantElementosPorPagina(String cantElementosPorPagina) {
		this.cantElementosPorPagina = cantElementosPorPagina;
	}

	public String getElementoOrdenacion() {
		return elementoOrdenacion;
	}

	public void setElementoOrdenacion(String elementoOrdenacion) {
		this.elementoOrdenacion = elementoOrdenacion;
	}

	public boolean isAscendente() {
		return ascendente;
	}

	public void setAscendente(boolean ascendente) {
		this.ascendente = ascendente;
	}

}
