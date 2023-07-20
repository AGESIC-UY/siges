package com.sofis.web.delegates.wekan;

import com.sofis.business.ejbs.wekan.ActividadBean;
import com.sofis.entities.tipos.wekan.ActividadTO;
import java.util.List;
import javax.inject.Inject;


public class ActividadDelegate {

	@Inject
	private ActividadBean actividadBean;

	public List<ActividadTO> obtenerActividadesCambiosEnDatosVinculacion(Long idTablero, Integer idCronograma) {
		
		return actividadBean.obtenerActividadesCambiosEnDatosVinculacion(idTablero, idCronograma);
	}

}
