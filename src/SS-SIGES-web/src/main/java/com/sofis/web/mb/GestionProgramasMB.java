package com.sofis.web.mb;

import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Programas;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.web.delegates.ProgramasDelegate;
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

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "gestionProgramasMB")
@ViewScoped
public class GestionProgramasMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GestionProgramasMB.class.getName());

	private String codigo;
	private String nombre;
	private List<Programas> programas;
	private Boolean renderResultado;
	private String cantElementosPorPagina = "25";

	@ManagedProperty("#{inicioMB}")
	private InicioMB inicioMB;
	@ManagedProperty("#{aplicacionMB}")
	private AplicacionMB aplicacionMB;

	@Inject
	private ProgramasDelegate programasDelegate;

	@PostConstruct
	private void init() {
                programas = null;
		renderResultado = false;
		codigo = "";
		nombre = "";
                buscar();
	}

	public void limpiar() {
		programas = null;
		renderResultado = false;
		codigo = null;
		nombre = null;
	}

	public void buscar() {
		try {

			programas = programasDelegate.obtenerProgramas(codigo, nombre, inicioMB.getOrganismoSeleccionado());
			renderResultado = true;

		} catch (GeneralException ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

	public void cambiarCantPaginas(ValueChangeEvent evt) {
		buscar();
	}

	public void deshabilitar(Integer progPk) {

		try {
			programasDelegate.habilitarDeshabilitarPrograma(progPk, false, 
					inicioMB.getUsuario(), inicioMB.getOrganismoSeleccionado());
			buscar();
			JSFUtils.agregarMsg("", "listado_programa_msg_deshabilitado", null);
                        
                        
                } catch (TechnicalException ex) {
                        logger.logp(Level.INFO, "deshabilitar", ex.getMessage(), ex.toString());
                        JSFUtils.agregarMsgError("", Labels.getValue(MensajesNegocio.ERROR_PMOF_DESHABILITAR_PROGRAMA), null);

		} catch (GeneralException ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			JSFUtils.agregarMsgError("", "listado_programa_msg_error_general", null);
                        
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			JSFUtils.agregarMsgError("", "listado_programa_msg_error_general", null);
                }
                
                inicioMB.setRenderPopupMensajes(Boolean.TRUE);
	}

	public void habilitar(Integer progPk) {
		try {
			programasDelegate.habilitarDeshabilitarPrograma(progPk, true, 
					inicioMB.getUsuario(), inicioMB.getOrganismoSeleccionado());
			buscar();
			JSFUtils.agregarMsg("", "listado_programa_msg_habilitado", null);

                } catch (TechnicalException ex) {
                        logger.logp(Level.INFO, "habilitar", ex.getMessage(), ex.toString());
                        JSFUtils.agregarMsgError("", Labels.getValue(MensajesNegocio.ERROR_PMOF_DESHABILITAR_PROGRAMA), null);

		} catch (GeneralException ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			JSFUtils.agregarMsgError("", "listado_programa_msg_error_general", null);
                        
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
                        JSFUtils.agregarMsgError("", "listado_programa_msg_error_general", null);
		}
                inicioMB.setRenderPopupMensajes(Boolean.TRUE);
	}

	public void eliminar(Integer progPk) {
		try {

			programasDelegate.darBajaPrograma(progPk, inicioMB.getUsuario(), inicioMB.getOrganismoSeleccionado());
			buscar();
			JSFUtils.agregarMsg("", "info_ficha_eliminada", null);
                        inicioMB.setRenderPopupMensajes(Boolean.TRUE);
		} catch (BusinessException Bex) {
			logger.log(Level.SEVERE, Bex.getMessage(), Bex);
			JSFUtils.agregarMsgError("", Labels.getValue(Bex.getMessage()), null);
                        inicioMB.setRenderPopupMensajes(Boolean.TRUE);
		} catch (GeneralException ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			JSFUtils.agregarMsgError("", Labels.getValue(ex.getMessage()), null);
                        inicioMB.setRenderPopupMensajes(Boolean.TRUE);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			JSFUtils.agregarMsgError("", Labels.getValue("listado_programa_msg_error_general"), null);
                        inicioMB.setRenderPopupMensajes(Boolean.TRUE);
		}
	}

	//<editor-fold defaultstate="collapsed" desc="getters-setters">
	public InicioMB getInicioMB() {
		return inicioMB;
	}

	public void setInicioMB(InicioMB inicioMB) {
		this.inicioMB = inicioMB;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public AplicacionMB getAplicacionMB() {
		return aplicacionMB;
	}

	public void setAplicacionMB(AplicacionMB aplicacionMB) {
		this.aplicacionMB = aplicacionMB;
	}

	public List<Programas> getProgramas() {
		return programas;
	}

	public void setProgramas(List<Programas> programas) {
		this.programas = programas;
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
//</editor-fold>
