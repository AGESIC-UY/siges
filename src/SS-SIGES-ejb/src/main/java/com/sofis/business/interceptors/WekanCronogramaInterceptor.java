package com.sofis.business.interceptors;

import com.sofis.business.ejbs.wekan.CambioSalienteWekanBean;
import com.sofis.business.interceptors.annotations.WekanCronograma;
import com.sofis.business.utils.ActividadUtils;
import com.sofis.business.utils.CronogramaUtils;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.utils.TableroUtils;
import com.sofis.business.utils.TarjetaUtils;
import com.sofis.business.utils.VinculacionUtils;
import com.sofis.data.daos.CronogramasDAO;
import com.sofis.data.daos.wekan.ActividadDAO;
import com.sofis.data.daos.wekan.TarjetaDAO;
import com.sofis.entities.constantes.ActualizacionWekan;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Actividad;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.Tarjeta;
import com.sofis.entities.enums.OrigenActividad;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.entities.tipos.EntregableTO;
import com.sofis.entities.tipos.wekan.ActividadTO;
import com.sofis.entities.tipos.wekan.TarjetaTO;
import com.sofis.entities.tipos.wekan.VinculacionTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Interceptor
@WekanCronograma
public class WekanCronogramaInterceptor {

	private static final Logger LOGGER = Logger.getLogger(WekanCronogramaInterceptor.class.getName());

	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	@Inject
	private CambioSalienteWekanBean cambioSalienteWekanBean;

	/*
	 * Comprueba los cambios de fecha de inicio, fecha de fina y progreso de cada entregable.
	 * Si detecta cambios, los envia a Wekan
	 */
	@AroundInvoke
	public Object comprobarActualizacionesEntregables(InvocationContext ctx) throws Exception {

		Object[] parameters = ctx.getParameters();

		Integer croPk = ((Cronogramas) parameters[0]).getCroPk();
		Integer tipoFicha = (Integer) parameters[2];

		if (croPk == null) {
			return ctx.proceed();
		}

		CronogramasDAO cronogramaDAO = new CronogramasDAO(em);
		Cronogramas cronograma = cronogramaDAO.findById(Cronogramas.class, croPk);

		// guarda valores antes del cambio
		List<EntregableTO> entregables = new ArrayList<>();
		for (Entregables e : cronograma.getEntregablesSet()) {

			entregables.add(EntregablesUtils.convert(e));
		}

		// ejecucion del metodo
		Object result = ctx.proceed();

		if (result == null) {
			return null;
		}

		cronograma = tipoFicha.equals(TipoFichaEnum.PROYECTO.id)
				? ((Proyectos) result).getProyCroFk() : ((Programas) result).getProgCroFk();

		procesarCambiosEntregables(cronograma, entregables);

		return result;
	}

	private void procesarCambiosEntregables(Cronogramas cronograma, List<EntregableTO> entregables) throws BusinessException {
		TarjetaDAO tarjetaDAO = new TarjetaDAO(em);
		List<TarjetaTO> actualizaciones = new ArrayList<>();

		for (Entregables entregable : cronograma.getEntregablesSet()) {
			EntregableTO entregableDataAnterior = null;

			for (EntregableTO eTO : entregables) {
				if (eTO.getId().equals(entregable.getEntPk())) {
					entregableDataAnterior = eTO;
					break;
				}
			}

			if (entregableDataAnterior == null
					|| (entregableDataAnterior.getProgreso().equals(entregable.getEntProgreso())
					&& DatesUtils.fechasIguales(entregableDataAnterior.getFechaInicio(), entregable.getEntInicioDate())
					&& DatesUtils.fechasIguales(entregableDataAnterior.getFechaFin(), entregable.getEntFinDate())
					&& entregableDataAnterior.getNombre().equals(entregable.getEntNombre()))) {

				// el entregable ya no existe o no tiene cambios para publicar
				LOGGER.log(Level.FINE, "wekan_actualizacion_sin_cambios");
				continue;
			}

			Tarjeta tarjeta = tarjetaDAO.obtenerPorIdEntregable(entregable.getEntPk());

			if (tarjeta == null) {

				LOGGER.log(Level.FINE, "wekan_actualizacion_entregable_no_vinculado");
				continue;
			}

			VinculacionTO vinculacion = VinculacionUtils.convert(tarjeta.getVinculacion());
			vinculacion.setTablero(TableroUtils.convert(tarjeta.getVinculacion().getTablero()));
			vinculacion.setCronograma(CronogramaUtils.convert(tarjeta.getVinculacion().getCronograma()));

			TarjetaTO tarjetaTO = TarjetaUtils.convert(tarjeta);
			tarjetaTO.setVinculacion(vinculacion);
			tarjetaTO.setEntregable(EntregablesUtils.convert(entregable));

			if (!entregableDataAnterior.getProgreso().equals(entregable.getEntProgreso())) {
				Actividad actividad = crearActividadProgreso(entregable, entregableDataAnterior, tarjeta);

				if (actividad != null) {
					tarjetaTO.getActividades().add(ActividadUtils.convert(actividad));
				}
			}

			if (!DatesUtils.fechasIguales(entregableDataAnterior.getFechaInicio(), entregable.getEntInicioDate())) {
				Actividad actividad = crearActividadFechaInicio(entregable, entregableDataAnterior, tarjeta);

				if (actividad != null) {
					tarjetaTO.getActividades().add(ActividadUtils.convert(actividad));
				}
			}

			if (!DatesUtils.fechasIguales(entregableDataAnterior.getFechaFin(), entregable.getEntFinDate())) {
				Actividad actividad = crearActividadFechaFin(entregable, entregableDataAnterior, tarjeta);

				if (actividad != null) {
					tarjetaTO.getActividades().add(ActividadUtils.convert(actividad));
				}
			}
                        if (!entregableDataAnterior.getNombre().equals(entregable.getEntNombre())) {
				Actividad actividad = crearActividadNombre(entregable, entregableDataAnterior, tarjeta);

				if (actividad != null) {
					tarjetaTO.getActividades().add(ActividadUtils.convert(actividad));
				}
			}
                        
			actualizaciones.add(tarjetaTO);
		}
                
		cambioSalienteWekanBean.enviarCambio(actualizaciones);
	}

	private Actividad crearActividadProgreso(Entregables entregable, EntregableTO entregableAnterior, Tarjeta tarjeta) {

		Actividad actividad = new Actividad();
		actividad.setTarjeta(tarjeta);

		actividad.setOrigen(OrigenActividad.SIGES);
		actividad.setActualizacion(ActualizacionWekan.PROGRESO_ENTREGABLE);
		actividad.setValorAnterior(entregableAnterior.getProgreso().toString());
		actividad.setValorNuevo(entregable.getEntProgreso().toString());
		actividad.setFechaCreacion(new Date());

		ActividadDAO actividadDAO = new ActividadDAO(em);
		try {
			actividad = actividadDAO.guardar(actividad);
			LOGGER.log(Level.FINE, "wekan_actividad_progreso_creada");

			return actividad;

		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, "error_actividad_guardar", ex);
		}

		return null;
	}

	private Actividad crearActividadFechaInicio(Entregables entregable, EntregableTO entregableAnterior, Tarjeta tarjeta) {

		Actividad actividad = new Actividad();
		actividad.setTarjeta(tarjeta);

		actividad.setOrigen(OrigenActividad.SIGES);
		actividad.setActualizacion(ActualizacionWekan.FECHA_INICIO_ENTREGABLE);
		actividad.setValorAnterior(DATE_FORMATTER.format(entregableAnterior.getFechaInicio()));
		actividad.setValorNuevo(DATE_FORMATTER.format(entregable.getEntInicioDate()));
		actividad.setFechaCreacion(new Date());

		ActividadDAO actividadDAO = new ActividadDAO(em);
		try {
			actividadDAO.guardar(actividad);
			LOGGER.log(Level.FINE, "wekan_actividad_fecha_inicio_creada");

			return actividad;
		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, "error_actividad_guardar", ex);
		}

		return null;
	}

	private Actividad crearActividadFechaFin(Entregables entregable, EntregableTO entregableAnterior, Tarjeta tarjeta) {
            LOGGER.info("ESTOY EN crearActividadFechaFin");
		Actividad actividad = new Actividad();
		actividad.setTarjeta(tarjeta);

		actividad.setOrigen(OrigenActividad.SIGES);
		actividad.setActualizacion(ActualizacionWekan.FECHA_FIN_ENTREGABLE);
		actividad.setValorAnterior(DATE_FORMATTER.format(entregableAnterior.getFechaFin()));
		actividad.setValorNuevo(DATE_FORMATTER.format(entregable.getEntFinDate()));
		actividad.setFechaCreacion(new Date());

		ActividadDAO actividadDAO = new ActividadDAO(em);
		try {
			actividadDAO.guardar(actividad);
			LOGGER.log(Level.FINE, "wekan_actividad_fecha_fin_creada");

			return actividad;

		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, "error_actividad_guardar", ex);
		}

		return null;
	}
        
        private Actividad crearActividadNombre(Entregables entregable, EntregableTO entregableAnterior, Tarjeta tarjeta) {

		Actividad actividad = new Actividad();
		actividad.setTarjeta(tarjeta);

		actividad.setOrigen(OrigenActividad.SIGES);
		actividad.setActualizacion(ActualizacionWekan.NOMBRE_ENTREGABLE);
		actividad.setValorAnterior(entregableAnterior.getNombre());
		actividad.setValorNuevo(entregable.getEntNombre());
		actividad.setFechaCreacion(new Date());

		ActividadDAO actividadDAO = new ActividadDAO(em);
		try {
			actividadDAO.guardar(actividad);
			LOGGER.log(Level.FINE, "wekan_actividad_nombre_creada");

			return actividad;

		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, "error_actividad_guardar", ex);
		}

		return null;
	}
}
