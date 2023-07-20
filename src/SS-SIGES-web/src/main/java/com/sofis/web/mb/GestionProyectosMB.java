package com.sofis.web.mb;

import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.tipos.FiltroProyectoTO;
import com.sofis.entities.tipos.ProyectoTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

@ManagedBean(name = "gestionProyectosMB")
@ViewScoped
public class GestionProyectosMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(GestionProyectosMB.class.getName());

	@ManagedProperty("#{inicioMB}")
	private InicioMB inicioMB;

	@ManagedProperty("#{aplicacionMB}")
	private AplicacionMB aplicacionMB;

	@Inject
	private ProyectosDelegate proyectosDelegate;

	private List<ProyectoTO> proyectos;
	private Boolean renderResultado;
	private String cantElementosPorPagina = "25";

	private FiltroProyectoTO filtro;

	@PostConstruct
	public void init() {

		filtro = new FiltroProyectoTO();

		buscar();
	}

	public void limpiar() {
		proyectos = null;
		renderResultado = false;
	}

	public void buscar() {
		try {
			filtro.setIdOrganismo(inicioMB.getOrganismoSeleccionado());

			proyectos = proyectosDelegate.buscar(filtro);

			renderResultado = true;

		} catch (GeneralException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

	public void cambiarCantPaginas(ValueChangeEvent evt) {
		buscar();
	}

	public void cambiarEstadoActivacion(Integer idProyecto) {

		try {
			boolean estadoActual = proyectosDelegate.cambiarEstadoActivacion(idProyecto);

			String mensaje = estadoActual ? MensajesNegocio.PROYECTO_ACTIVADO : MensajesNegocio.PROYECTO_DESACTIVADO;
			JSFUtils.agregarMsg("", mensaje, null);

			inicioMB.setRenderPopupMensajes(Boolean.TRUE);
			
			buscar();

		} catch (BusinessException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);

			for (String iterStr : ex.getErrores()) {
				JSFUtils.agregarMsgError(null, Labels.getValue(iterStr), null);
			}

		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			JSFUtils.agregarMsgError("", MensajesNegocio.ERROR_GENERAL, null);
		}
	}

	public void irAEditarProyecto(Integer id) {

		inicioMB.irEditarProgramaProyecto(2, id, true);
	}

	public InicioMB getInicioMB() {
		return inicioMB;
	}

	public void setInicioMB(InicioMB inicioMB) {
		this.inicioMB = inicioMB;
	}

	public AplicacionMB getAplicacionMB() {
		return aplicacionMB;
	}

	public FiltroProyectoTO getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroProyectoTO filtro) {
		this.filtro = filtro;
	}

	public void setAplicacionMB(AplicacionMB aplicacionMB) {
		this.aplicacionMB = aplicacionMB;
	}

	public List<ProyectoTO> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<ProyectoTO> proyectos) {
		this.proyectos = proyectos;
	}

	public Boolean getRenderResultado() {
		return renderResultado;
	}

	public void setRenderResultado(Boolean renderResultado) {
		this.renderResultado = renderResultado;
	}

	public String getCantElementosPorPagina() {
		return cantElementosPorPagina;
	}

	public void setCantElementosPorPagina(String cantElementosPorPagina) {
		this.cantElementosPorPagina = cantElementosPorPagina;
	}

}
