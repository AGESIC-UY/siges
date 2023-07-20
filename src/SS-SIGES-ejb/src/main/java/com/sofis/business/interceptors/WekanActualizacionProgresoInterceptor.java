package com.sofis.business.interceptors;

import com.sofis.business.ejbs.wekan.CambioSalienteWekanBean;
import com.sofis.data.daos.EntregablesDAO;
import com.sofis.data.daos.wekan.ActividadDAO;
import com.sofis.data.daos.wekan.TarjetaDAO;
import com.sofis.entities.constantes.ActualizacionWekan;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Actividad;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Tarjeta;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.sofis.business.interceptors.annotations.WekanActualizacionProgreso;
import com.sofis.business.utils.CronogramaUtils;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.utils.TableroUtils;
import com.sofis.business.utils.TarjetaUtils;
import com.sofis.business.utils.VinculacionUtils;
import com.sofis.entities.enums.OrigenActividad;
import com.sofis.entities.tipos.wekan.TarjetaTO;
import com.sofis.entities.tipos.wekan.VinculacionTO;

@Interceptor
@WekanActualizacionProgreso
public class WekanActualizacionProgresoInterceptor {

	private static final Logger LOGGER = Logger.getLogger(WekanActualizacionProgresoInterceptor.class.getName());

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	@Inject
	private CambioSalienteWekanBean cambioSalienteWekanBean;

	@AroundInvoke
	public Object comprobarActualizacionesEntregables(InvocationContext ctx) throws Exception {
		Object[] parameters = ctx.getParameters();

		Integer idEntregable = (Integer) parameters[1];

		EntregablesDAO entregableDAO = new EntregablesDAO(em);
		Entregables entregableAnterior = entregableDAO.findById(Entregables.class, idEntregable);

		Integer progresoAnterior = null;

		if (entregableAnterior != null) {
			progresoAnterior = entregableAnterior.getEntProgreso();
		}

		// ejecucion del metodo
		Entregables entregable = (Entregables) ctx.proceed();

		if (entregable == null || entregableAnterior == null) {

			return entregable;
		}

		if (entregable.getEntProgreso().equals(progresoAnterior)) {

			LOGGER.log(Level.FINE, "wekan_actualizacion_sin_cambios");

			return entregable;
		}

		procesarCambosEntregable(entregable, progresoAnterior);

		return entregable;
	}

	private void procesarCambosEntregable(Entregables entregable, Integer progresoAnterior) {
		TarjetaDAO tarjetaDAO = new TarjetaDAO(em);
		Tarjeta tarjeta = tarjetaDAO.obtenerPorIdEntregable(entregable.getEntPk());

		if (tarjeta == null) {
			LOGGER.log(Level.FINE, "wekan_actualizacion_entregable_no_vinculado");

			return;
		}

		crearActividadProgreso(entregable, progresoAnterior, tarjeta);

		VinculacionTO vinculacion = VinculacionUtils.convert(tarjeta.getVinculacion());
		vinculacion.setTablero(TableroUtils.convert(tarjeta.getVinculacion().getTablero()));
		vinculacion.setCronograma(CronogramaUtils.convert(tarjeta.getVinculacion().getCronograma()));

		TarjetaTO tarjetaTO = TarjetaUtils.convert(tarjeta);
		tarjetaTO.setVinculacion(vinculacion);
		tarjetaTO.setEntregable(EntregablesUtils.convert(entregable));

		cambioSalienteWekanBean.enviarCambio(tarjetaTO);
	}

	private void crearActividadProgreso(Entregables entregable, Integer progresoAnterior, Tarjeta tarjeta) {

		Actividad actividad = new Actividad();
		actividad.setTarjeta(tarjeta);
		
		actividad.setOrigen(OrigenActividad.SIGES);
		actividad.setActualizacion(ActualizacionWekan.PROGRESO_ENTREGABLE);
		actividad.setValorAnterior(progresoAnterior.toString());
		actividad.setValorNuevo(entregable.getEntProgreso().toString());
		actividad.setFechaCreacion(new Date());

		ActividadDAO actividadDAO = new ActividadDAO(em);
		try {
			actividadDAO.guardar(actividad);
			LOGGER.log(Level.FINE, "wekan_actividad_progreso_creada");

		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, "error_actividad_guardar", ex);
		}
	}
}
