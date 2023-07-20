package com.sofis.web.mb;

import com.sofis.entities.data.MailTemplateDefecto;
import com.sofis.entities.tipos.FiltroMailTemplateDefectoTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.MailTemplateDefectoDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
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

@ManagedBean(name = "mailTemplateDefectoMB")
@ViewScoped
public class MailTemplateDefectoMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(MailTemplateDefectoMB.class.getName());

	private static final String POPUP_MSG = "popupMsg";
	private static final String MAIL_CODIGO = "codigo";
	private static final String MAIL_ASUNTO = "asunto";
	private static final String MAIL_MENSAJE = "mensaje";

	private String cantElementosPorPagina = "25";
	private String elementoOrdenacion = MAIL_CODIGO;

	// 0=descendente, 1=ascendente.
	private int ascendente = 1;

	private String filtroCodigo;
	private List<MailTemplateDefecto> listaResultado;
	private MailTemplateDefecto templateEnEdicion;

	@ManagedProperty("#{inicioMB}")
	private InicioMB inicioMB;

	@Inject
	private SofisPopupUI renderPopupEdicion;

	@Inject
	private MailTemplateDefectoDelegate mailsTemplateDelegate;

	@PostConstruct
	public void init() {
		filtroCodigo = "";
		listaResultado = new ArrayList<>();
		
		templateEnEdicion = new MailTemplateDefecto();
		templateEnEdicion.setMensaje("<p></p>");

		inicioMB.cargarOrganismoSeleccionado();

		buscar();
	}

	public String agregar() {
		templateEnEdicion = new MailTemplateDefecto();

		renderPopupEdicion.abrir();
		return null;
	}

	public String eliminar(Integer id) {
		if (id != null) {
			try {
				mailsTemplateDelegate.eliminar(id);
				
				buscar();
				
			} catch (BusinessException e) {
				LOGGER.log(Level.SEVERE, null, e);
				JSFUtils.agregarMsgs("", e.getErrores());
				inicioMB.setRenderPopupMensajes(Boolean.TRUE);
			}
		}
		return null;
	}

	public String buscar() {
		FiltroMailTemplateDefectoTO filtro = new FiltroMailTemplateDefectoTO();
		filtro.setCodigo(filtroCodigo);
		
		listaResultado = mailsTemplateDelegate.buscar(filtro, elementoOrdenacion, ascendente);

		return null;
	}

	public String editar(Integer id) {
		try {
			templateEnEdicion = mailsTemplateDelegate.obtenerPorId(id);
		} catch (BusinessException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			JSFUtils.agregarMsgs(POPUP_MSG, ex.getErrores());
		}

		renderPopupEdicion.abrir();
		return null;
	}

	public String guardar() {
		
		try {
			templateEnEdicion = mailsTemplateDelegate.guardar(templateEnEdicion);

			if (templateEnEdicion != null) {
				renderPopupEdicion.cerrar();
				buscar();
			}
		} catch (BusinessException be) {
			LOGGER.log(Level.SEVERE, be.getMessage(), be);

			for (String iterStr : be.getErrores()) {
				JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);
			}

		}
		return null;
	}

	public void cancelar() {
		renderPopupEdicion.cerrar();
	}

	public String limpiar() {
		filtroCodigo = null;
		listaResultado = null;
		elementoOrdenacion = MAIL_CODIGO;
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

	public String getFiltroCodigo() {
		return filtroCodigo;
	}

	public void setFiltroCodigo(String filtroCodigo) {
		this.filtroCodigo = filtroCodigo;
	}

	public List<MailTemplateDefecto> getListaResultado() {
		return listaResultado;
	}

	public void setListaResultado(List<MailTemplateDefecto> listaResultado) {
		this.listaResultado = listaResultado;
	}

	public MailTemplateDefecto getTemplateEnEdicion() {
		return templateEnEdicion;
	}

	public void setTemplateEnEdicion(MailTemplateDefecto templateEnEdicion) {
		this.templateEnEdicion = templateEnEdicion;
	}

	public void setInicioMB(InicioMB inicioMB) {
		this.inicioMB = inicioMB;
	}

}
