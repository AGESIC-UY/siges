package com.sofis.business.ejbs;

import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.generico.utils.generalutils.DatesUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.ejb3.annotation.TransactionTimeout;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Named
@Stateless(name = "indicadoresBean")
@LocalBean
public class IndicadoresBean {

	private static final Logger LOGGER = Logger.getLogger(IndicadoresBean.class.getName());

	private static final String HORA_EJECUCION = "0";
	private static final String MINUTOS_EJECUCION = "5";
	private static final long TRANSACTION_TIMEOUT_MINUTES = 120;

	private static final Semaphore MUTEX = new Semaphore(1, true);

	private static final String MUTEX_ID = "INDICADORES";

	@Inject
	private OrganismoBean organismoBean;
	@Inject
	private ProyectosBean proyectosBean;
	@Inject
	private ProgramasBean programasBean;
	@Inject
	private ConfiguracionBean configuracionBean;
	@Inject
	private ProyIndicesBean proyIndicesBean;
	@Inject
	private DatosUsuario du;
	@Inject
	private SsUsuarioBean usuarioBean;
	@Inject
	private MutexBean mutexBean;

	private Integer countProy;
	private Integer totalProy;
	private Integer countProg;
	private Integer totalProg;
	private Integer progressProy;
	private Integer progressProg;

	@Schedule(hour = HORA_EJECUCION, minute = MINUTOS_EJECUCION, persistent = false, info = "calculo_indicadores")
	public void scheduleRecalcularIndicadores() {

		recalcularIndicadores(null, null);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@TransactionTimeout(value = TRANSACTION_TIMEOUT_MINUTES, unit = TimeUnit.MINUTES)
	public void recalcularIndicadores(Integer orgPk, Boolean forzar) {
		int lock = -1;
		try {
			LOGGER.info("Intentando obtener el lock " + MUTEX_ID);
			lock = mutexBean.obtenerLock(MUTEX_ID);
			LOGGER.log(Level.INFO, "Respuesta obtenida: {0}", lock);

			if (lock != 0) {
				LOGGER.info("No se pudo obtener el lock, no se ejecuta el procedimiento");

				return;
			}

			SsUsuario usuario = null;
			String codigo;
			try {
				MUTEX.acquire();
				codigo = du.getCodigoUsuario();
				if (codigo != null) {
					usuario = usuarioBean.obtenerSsUsuarioPorCodigo(codigo);
				}
			} catch (Exception ex) {
				LOGGER.log(Level.SEVERE, null, ex);
			} finally {
				MUTEX.release();
			}

			List<Organismos> listOrg = new ArrayList<>();
			if (orgPk != null) {
				listOrg.add(organismoBean.obtenerOrgPorId(orgPk, false));
			} else {
				listOrg = organismoBean.obtenerTodos();
			}

			for (Organismos org : listOrg) {

				LOGGER.log(Level.INFO, "-- Recalculando Indices Organismo: {0}", org.getOrgNombre());
				Integer orgaPk = org.getOrgPk();

				LOGGER.log(Level.INFO, "Buscando Proyectos...{0}", orgaPk);
				List<Integer> idsProy;

				Boolean incluirFinalizados = Boolean.valueOf(configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.INCLUIR_CALCULAR_FINALIZADOS, orgPk));

				if (incluirFinalizados) {
					idsProy = proyectosBean.obtenerIdsProyPorOrg(orgaPk, true);
				} else {
					idsProy = proyectosBean.obtenerIdsProyPorOrgNoFinalizado(orgaPk);
				}
				totalProy = idsProy.size();
				LOGGER.log(Level.INFO, "Total proyectos:{0}", totalProy);
				countProy = 0;

				String[] codigos = new String[]{
					ConfiguracionCodigos.ESTADO_INICIO_LIMITE_AMARILLO,
					ConfiguracionCodigos.ESTADO_INICIO_LIMITE_ROJO,
					ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_AMARILLO,
					ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_ROJO};

				Map<String, Configuracion> confs = configuracionBean.obtenerCnfPorCodigoYOrg(orgPk, codigos);

				for (Integer proyPk : idsProy) {
					try {

						Date ultima = forzar != null && forzar ? null : proyIndicesBean.ultimaActualizacion(proyPk);
						if (ultima == null || DatesUtils.esMayor(new Date(), ultima)) {

							proyectosBean.controlarEntregables(proyPk, false);
							proyectosBean.flush();
							//proyectosBean.controlarProdAcumulados(proyPk, false);
							//proyectosBean.flush();
							proyectosBean.guardarIndicadoresSimple(proyPk, false, true, orgaPk, confs, false, false);
							proyectosBean.flush();

							countProy++;
							progressProy = countProy * 100 / totalProy;

							LOGGER.log(Level.INFO, "Proyecto ({0}) calcular Ind: {1}/{2} ({3}%)", new Object[]{proyPk, countProy, totalProy, progressProy});

						} else {
							countProy++;
							LOGGER.log(Level.INFO, "Proyecto ({0}) ya fue procesado el dia de hoy.", proyPk);
						}

					} catch (Exception ex) {
						LOGGER.log(Level.SEVERE, "Proyecto ({0}) calcular Ind: {1}/{2} ({3}%) [ERROR]", new Object[]{proyPk, countProy, totalProy, progressProy});
						Logger.getLogger(IndicadoresBean.class.getName()).log(Level.FINEST, null, ex);
					}
				}

				LOGGER.log(Level.INFO, "Buscando Programas...{0}", orgaPk);
				List<Integer> programasIds = programasBean.obtenerTodosIdsPorOrg(orgaPk);
				totalProg = programasIds.size();
				LOGGER.log(Level.INFO, "Total programas:{0}", totalProg);
				countProg = 0;

				for (Integer progPk : programasIds) {
					try {
						programasBean.actualizarProgramaPorProyectos(progPk, usuario, du.getOrigen());
						programasBean.flush();
						programasBean.guardarIndicadoresSimple(progPk, orgaPk);
						programasBean.flush();
						countProg++;
						progressProg = countProg * 100 / totalProg;
						LOGGER.log(Level.INFO, "Programa calcular Ind({0}): {1}/{2} ({3}%)", new Object[]{progPk, countProg, totalProg, progressProg});

					} catch (Exception ex) {
						LOGGER.log(Level.INFO, "Programa calcular Ind({0}): {1}/{2} ({3}%) [ERROR]", new Object[]{progPk, countProg, totalProg, progressProg});
						Logger.getLogger(IndicadoresBean.class.getName()).log(Level.FINEST, null, ex);
					}
				}

				try {
					System.gc();
					LOGGER.log(Level.INFO, "System.gc() [{0}]", org.getOrgPk());
				} catch (Exception ex) {
					LOGGER.log(Level.SEVERE, "ERROR AL EJECUTAR GC LUEGO DEL SCHEDULE", ex);
				}

			}

			try {
				System.gc();
				LOGGER.log(Level.INFO, "FINALIZADO EL SCHEDULE: System.gc()");
			} catch (Exception ex) {
				LOGGER.log(Level.SEVERE, "ERROR AL EJECUTAR GC LUEGO DEL SCHEDULE", ex);
			}

		} finally {
			if (lock == 0) {
				LOGGER.info("Intentando liberar el lock " + MUTEX_ID);
				lock = mutexBean.liberarLock(MUTEX_ID);
				LOGGER.log(Level.INFO, "Liberacion de lock, resp: {0}", lock);
			}
		}
	}
}
