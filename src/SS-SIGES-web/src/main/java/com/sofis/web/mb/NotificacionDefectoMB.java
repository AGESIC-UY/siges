package com.sofis.web.mb;

import com.sofis.entities.data.Notificacion;
import com.sofis.entities.data.NotificacionDefecto;
import com.sofis.entities.tipos.FiltroNotificacionDefectoTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.NotificacionDefectoDelegate;
import com.sofis.web.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

@ManagedBean(name = "notificacionDefectoMB")
@ViewScoped
public class NotificacionDefectoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(NotificacionDefectoMB.class.getName());

	private static final String BUSQUEDA_MSG = "busquedaMsg";
	private static final String POPUP_MSG = "popupMsg";
	private static final String NOTIF_ASUNTO = "asunto";
	private static final String NOTIF_COD = "codigo";
	private static final String NOTIF_DESC = "descripcion";
	private static final String NOTIF_MSG = "mensaje";

	@ManagedProperty("#{inicioMB}")
	private InicioMB inicioMB;

	@Inject
	private SofisPopupUI renderPopupEdicion;

	@Inject
	private NotificacionDefectoDelegate notificacionDefectoDelegate;

	private String cantElementosPorPagina = "25";
	private String elementoOrdenacion = NOTIF_COD;

	// 0=descendente, 1=ascendente.
	private int ascendente = 1;

	private String filtroCodigo;
	private String filtroAsunto;
	private String filtroDescripcion;
	private String filtroMensaje;

	private List<NotificacionDefecto> listaResultado;
	private NotificacionDefecto notifEnEdicion;

	@PostConstruct
	public void init() {

		filtroCodigo = "";
		filtroDescripcion = "";
		filtroMensaje = "";
		listaResultado = new ArrayList<>();

		buscar();
	}

	public String agregar() {

		notifEnEdicion = new NotificacionDefecto();
		notifEnEdicion.setMensaje("<p></p>");

		renderPopupEdicion.abrir();

		return null;
	}

	public String eliminar(Integer notifPk) {

		try {
			notificacionDefectoDelegate.eliminar(notifPk);

			buscar();
		} catch (BusinessException e) {
			LOGGER.log(Level.SEVERE, null, e);
			JSFUtils.agregarMsgs(BUSQUEDA_MSG, e.getErrores());
			inicioMB.setRenderPopupMensajes(Boolean.TRUE);
		}

		return null;
	}

	public String buscar() {

		Map<String, Object> mapFiltro = new HashMap<>();
		mapFiltro.put(NOTIF_COD, filtroCodigo);
		mapFiltro.put(NOTIF_ASUNTO, filtroAsunto);
		mapFiltro.put(NOTIF_DESC, filtroDescripcion);
		mapFiltro.put(NOTIF_MSG, filtroMensaje);

		FiltroNotificacionDefectoTO filtro = new FiltroNotificacionDefectoTO();
		filtro.setCodigo(filtroCodigo);
		filtro.setAsunto(filtroAsunto);
		filtro.setDescripcion(filtroDescripcion);
		filtro.setMensaje(filtroMensaje);

		listaResultado = notificacionDefectoDelegate.buscar(filtro, elementoOrdenacion, ascendente);

		return null;
	}

	public String editar(Integer notifPk) {

		try {
			notifEnEdicion = notificacionDefectoDelegate.obtenerPorId(notifPk);
		} catch (BusinessException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			JSFUtils.agregarMsgs(POPUP_MSG, ex.getErrores());
		}

		renderPopupEdicion.abrir();

		return null;
	}

	public String guardar() {

		try {
			notifEnEdicion = notificacionDefectoDelegate.guardar(notifEnEdicion);

			if (notifEnEdicion != null) {
				renderPopupEdicion.cerrar();
				buscar();
			}
		} catch (BusinessException be) {
			LOGGER.log(Level.SEVERE, be.getMessage(), be);
			JSFUtils.agregarMsgs(BUSQUEDA_MSG, be.getErrores());
		}
		return null;
	}

	public void cancelar() {

		renderPopupEdicion.cerrar();
	}

	public String limpiar() {

		filtroCodigo = null;
		filtroAsunto = null;
		filtroDescripcion = null;
		filtroMensaje = null;
		listaResultado = null;
		elementoOrdenacion = NOTIF_COD;
		ascendente = 1;

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

		ascendente = Integer.valueOf(evt.getNewValue().toString());
		buscar();
	}

	public void setInicioMB(InicioMB inicioMB) {
		this.inicioMB = inicioMB;
	}

	public SofisPopupUI getRenderPopupEdicion() {
		return renderPopupEdicion;
	}

	public void setRenderPopupEdicion(SofisPopupUI renderPopupEdicion) {
		this.renderPopupEdicion = renderPopupEdicion;
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

	public int getAscendente() {
		return ascendente;
	}

	public void setAscendente(int ascendente) {
		this.ascendente = ascendente;
	}

	public String getFiltroAsunto() {
		return filtroAsunto;
	}

	public void setFiltroAsunto(String filtroAsunto) {
		this.filtroAsunto = filtroAsunto;
	}

	public String getFiltroCodigo() {
		return filtroCodigo;
	}

	public void setFiltroCodigo(String filtroCodigo) {
		this.filtroCodigo = filtroCodigo;
	}

	public String getFiltroDescripcion() {
		return filtroDescripcion;
	}

	public void setFiltroDescripcion(String filtroDescripcion) {
		this.filtroDescripcion = filtroDescripcion;
	}

	public String getFiltroMensaje() {
		return filtroMensaje;
	}

	public void setFiltroMensaje(String filtroMensaje) {
		this.filtroMensaje = filtroMensaje;
	}

	public List<NotificacionDefecto> getListaResultado() {
		return listaResultado;
	}

	public void setListaResultado(List<NotificacionDefecto> listaResultado) {
		this.listaResultado = listaResultado;
	}

	public NotificacionDefecto getNotifEnEdicion() {
		return notifEnEdicion;
	}

	public void setNotifEnEdicion(NotificacionDefecto notifEnEdicion) {
		this.notifEnEdicion = notifEnEdicion;
	}

}
