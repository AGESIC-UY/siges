package com.sofis.business.ejbs;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class StartupBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(StartupBean.class.getName());

	private static final String MUTEX_ID = "STARTUP";

	@Inject
	private MutexBean mutexBean;

	@EJB
	private MailsTemplateBean mailsTemplateBean;

	@EJB
	private MailTemplateDefectoBean mailTemplateDefectoBean;

	@EJB
	private ConfiguracionBean configuracionBean;

	@EJB
	private ConfiguracionDefectoBean configuracionDefectoBean;

	@EJB
	private NotificacionDefectoBean notificacionDefectoBean;

	@EJB
	private NotificacionBean notificacionBean;

	@EJB
	private SsRolBean ssRolBean;

	@EJB
	private EstadosPublicacionBean estadosPublicacionBean;

	@EJB
	private EtapaBean etapaBean;

	@EJB
	private TiposMediaBean tiposMediaBean;

	@EJB
	private EstadosBean estadosBean;

	@EJB
	private TipoLeccionBean tipoLeccionBean;

	@PostConstruct
	public void init() {
		int lock = -1;
		try {
			LOGGER.info("Intentando obtener el lock " + MUTEX_ID);
			lock = mutexBean.obtenerLock(MUTEX_ID);
			LOGGER.log(Level.INFO, "Respuesta obtenida: {0}", lock);

			if (lock != 0) {
				LOGGER.info("No se pudo obtener el lock, no se ejecuta el procedimiento");

				return;
			}

			LOGGER.log(Level.INFO, "-------------> StartupBean init. START");
			controlarDatosFaltantes();
			LOGGER.log(Level.INFO, "-------------> StartupBean init. END");

		} catch (Exception ex) {

			LOGGER.log(Level.SEVERE, "Exepcion durante startup", ex);

			throw ex;
		} finally {
			if (lock == 0) {
				LOGGER.info("Intentando liberar el lock " + MUTEX_ID);
				lock = mutexBean.liberarLock(MUTEX_ID);
				LOGGER.log(Level.INFO, "Liberacion de lock, resp: {0}", lock);
			}
		}
	}

	public void controlarDatosFaltantes() {

		LOGGER.log(Level.INFO, "Controlar Mail Templates por Defecto faltantes");
		mailTemplateDefectoBean.controlarFaltantes();

		LOGGER.log(Level.INFO, "Controlar Mail Templates de Organismos faltantes");
		mailsTemplateBean.controlarFaltantes();

		LOGGER.log(Level.INFO, "Controlar Configuraciones por Defecto faltantes");
		configuracionDefectoBean.controlarConfiguracionDefecto();

		LOGGER.log(Level.INFO, "Controlar Configuraciones faltantes");
		configuracionBean.controlarCnfFaltantes();

		LOGGER.log(Level.INFO, "Controlar Notificaciones por Defecto faltantes");
		notificacionDefectoBean.controlarFaltantes();

		LOGGER.log(Level.INFO, "Controlar Notificaciones de Organismos faltantes");
		notificacionBean.controlarFaltantes();

		LOGGER.log(Level.INFO, "Controlar Roles faltantes");
		ssRolBean.controlarRolesFaltantes();

		LOGGER.log(Level.INFO, "Controlar Estados Publicacion faltantes");
		estadosPublicacionBean.controlarEstPubFaltantes();

		LOGGER.log(Level.INFO, "Controlar Etapas faltantes");
		etapaBean.controlarFaltantes();

		LOGGER.log(Level.INFO, "Controlar Tipos Multimedia faltantes");
		tiposMediaBean.controlarTiposMediaFaltantes();

		LOGGER.log(Level.INFO, "Controlar Estados faltantes");
		estadosBean.controlarEstadosFaltantes();

		LOGGER.log(Level.INFO, "Controlar Tipos de Leccion faltantes");
		tipoLeccionBean.controlarTipoLeccionFaltantes();
	}

}
