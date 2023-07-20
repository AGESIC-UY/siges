package com.sofis.web.delegates;

import com.sofis.business.ejbs.LatlngBean;
import com.sofis.entities.data.LatlngProyectos;
import java.io.Serializable;
import javax.inject.Inject;
import org.agesic.siges.visualizador.web.ws.LatlngProyectoImp;

public class LatlngDelegate implements Serializable {

	@Inject
	private LatlngBean latlngBean;

	public LatlngProyectoImp crearLatlngProyecto(LatlngProyectos latlngProyectos) {
		return latlngBean.crearLatlngProyecto(latlngProyectos);
	}

	public LatlngProyectos guardar(LatlngProyectos latlngProyectos) {
		return latlngBean.guardar(latlngProyectos);
	}
	
	public void eliminar(LatlngProyectos latlngProyectos){
		latlngBean.eliminar(latlngProyectos);
	}

}
