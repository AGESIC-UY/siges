package com.sofis.web.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

import com.sofis.web.delegates.EtiquetaDelegate;
import java.util.HashMap;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "labels")
@SessionScoped
public class EtiquetaMB extends HashMap {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{inicioMB}")
	private InicioMB inicioMB;

	@Inject
	private EtiquetaDelegate etiquetaDelegate;

	@Override
	public Object get(Object key) {

		if (!(key instanceof String)) {
			return null;
		}

		return etiquetaDelegate.obtenerEtiqueta((String) key, inicioMB.getOrganismoSeleccionado());
	}

	public void setInicioMB(InicioMB inicioMB) {
		
		this.inicioMB = inicioMB;
	}

}
