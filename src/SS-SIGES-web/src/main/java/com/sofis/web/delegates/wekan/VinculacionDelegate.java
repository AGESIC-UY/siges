package com.sofis.web.delegates.wekan;

import com.sofis.business.ejbs.wekan.VinculacionBean;
import com.sofis.entities.tipos.EntregableTO;
import com.sofis.entities.tipos.wekan.EntregableTarjetaTO;
import com.sofis.entities.tipos.wekan.VinculacionTO;
import java.util.List;
import javax.inject.Inject;

public class VinculacionDelegate {

	@Inject
	private VinculacionBean vinculacionBean;

	public VinculacionTO obtenerPorIdCronograma(Integer idCronograma) {

		return vinculacionBean.obtenerPorIdCronograma(idCronograma);
	}

	public VinculacionTO vincularTablero(Integer idCronograma, String urlTablero, String usuarioTablero, String contraseniaTablero) {

		return vinculacionBean.vincularTablero(idCronograma, urlTablero, usuarioTablero, contraseniaTablero);
	}

        public void desvincularTablero(Integer idCronograma) {

		vinculacionBean.desvincularTablero(idCronograma);
	}
        
        public void restaurarVinculacion(Integer idCronograma) {

		vinculacionBean.restaurarVinculacion(idCronograma);
	}
        
	public List<EntregableTarjetaTO> obtenerVinculacionEntregables(Integer idCronograma) {

		return vinculacionBean.obtenerVinculacionEntregables(idCronograma);
	}

	public void vincularEntregables(Integer idCronograma, List<EntregableTO> entregables) {
		
		vinculacionBean.vincularEntregables(idCronograma, entregables);
	}
        
        public void desvincularEntregable(Integer idEntregable){
            vinculacionBean.eliminarVinculacionEntregable(idEntregable);
        }

}
