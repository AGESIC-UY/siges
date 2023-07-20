package com.sofis.business.ejbs.wekan;

import com.sofis.business.utils.ActividadUtils;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.utils.TarjetaUtils;
import com.sofis.data.daos.wekan.ActividadDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesIntegracionWekan;
import com.sofis.entities.data.Actividad;
import com.sofis.entities.tipos.wekan.ActividadTO;
import com.sofis.entities.tipos.wekan.TarjetaTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ActividadBean {

	private static final Logger LOGGER = Logger.getLogger(ActividadBean.class.getName());

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	public List<ActividadTO> obtenerActividadesCambiosEnDatosVinculacion(Long idTablero, Integer idCronograma) {

		ActividadDAO actividadDAO = new ActividadDAO(em);

		List<Actividad> actividades = actividadDAO.obtenerActividadesCambiosEnDatosVinculacion(idTablero, idCronograma);

		List<ActividadTO> result = new ArrayList<>();
		for (Actividad actividad : actividades) {

			TarjetaTO tarjeta = TarjetaUtils.convert(actividad.getTarjeta());
			tarjeta.setEntregable(EntregablesUtils.convert(actividad.getTarjeta().getEntregable()));

			ActividadTO actividadTO = ActividadUtils.convert(actividad);
			actividadTO.setTarjeta(tarjeta);

			result.add(actividadTO);
		}

		return result;
	}

	void eliminarActividadTarjeta(long idTarjeta) {

		ActividadDAO actividadDAO = new ActividadDAO(em);

		try {
			List<Actividad> actividades = actividadDAO.obtenerPorIdTarjeta(idTarjeta);

			for (Actividad actividad : actividades) {
				
				actividadDAO.delete(actividad);
			}
			
		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, null, ex);

			BusinessException be = new BusinessException();
			be.addError(ConstantesIntegracionWekan.ERROR_ACTIVIDAD_ELIMINAR);

			throw be;
		}
	}

}
