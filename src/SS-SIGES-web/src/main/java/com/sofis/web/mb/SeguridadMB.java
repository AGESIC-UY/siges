package com.sofis.web.mb;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Configuracion;
import com.sofis.web.delegates.ConfiguracionDelegate;
import java.io.Serializable;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author SSGenerador
 */
@ManagedBean(name = "seguridadMB")
@SessionScoped
public class SeguridadMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SeguridadMB.class.getName());
	private Boolean conControlDeAcceso = false;
	private ResourceBundle permisosPaginas = null;

	@ManagedProperty("#{inicioMB}")
	private InicioMB inicioMB;

	@Inject
	private ConfiguracionDelegate configuracionDelegate;

	/**
	 * Creates a new instance of SeguridadMB
	 */
	public SeguridadMB() {
	}

	public void setInicioMB(InicioMB inicioMB) {
		this.inicioMB = inicioMB;
	}

	private void reset() {
	}

	@PostConstruct
	public void init() {
		permisosPaginas = ResourceBundle.getBundle("com.sofis.generico.web.mensajes.permisospaginas");
	}

	public boolean getConControlDeAcceso() {
		if (conControlDeAcceso == null) {
			try {
				Configuracion conf = configuracionDelegate.obtenerCnfPorCodigoYOrg("CON_CONTROL_ACCESO", inicioMB.getOrganismo().getOrgPk());
				conControlDeAcceso = Boolean.valueOf(conf.getCnfValor());
			} catch (Exception ex) {
				conControlDeAcceso = false;
			}
		}
		return conControlDeAcceso.booleanValue();
	}

	public boolean getAccesoAutorizado() {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		String path = ctx.getRequestServletPath();
		//Quitar la extension
		path = path.substring(0, path.lastIndexOf('.'));
		//Quedarse solo con la ultima parte
		path = path.substring(path.lastIndexOf('/') + 1);
		String sPermisosRequeridos = null;
		if (permisosPaginas.containsKey(path)) {
			sPermisosRequeridos = permisosPaginas.getString(path);
		}
		if (sPermisosRequeridos == null || sPermisosRequeridos.trim().isEmpty()) {
			return false;
		}
		if (sPermisosRequeridos.contains("*")) {
			return true;
		}
		String[] permisosRequeridos = sPermisosRequeridos.split(",");
		HashMap<String, Boolean> permisos = inicioMB.getPermisos();

		for (String permisoRequerido : permisosRequeridos) {
			if (permisos.containsKey(permisoRequerido)) {
				return permisos.get(permisoRequerido) != null && permisos.get(permisoRequerido).booleanValue();
			}
		}

		return false;
	}
}
