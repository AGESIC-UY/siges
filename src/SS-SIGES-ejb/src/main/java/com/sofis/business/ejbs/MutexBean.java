package com.sofis.business.ejbs;

import com.sofis.entities.constantes.ConstanteApp;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang.StringUtils;

@Named
@Stateless(name = "MutexBean")
@LocalBean
public class MutexBean {

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	private static final Logger LOGGER = Logger.getLogger(MutexBean.class.getName());

	private static final String MYSQL = "mysql";
	private static final String POSTGRES = "postgres";

	public static int OK = 0;
	public static int ERROR_BASEDEDATOS_NO_RECONOCIDA = 1;
	public static int ERROR_BASEDEDATOS_NO_SOPORTADA = 2;
	public static int ERROR_LOCK_NO_OBTENIDO = 3;

	public int obtenerLock(String idLock) {

		String baseDeDatos = determinarBaseDeDatos();

		if (baseDeDatos == null) {
			return ERROR_BASEDEDATOS_NO_RECONOCIDA;
		}

		boolean lockOk;

		switch (baseDeDatos) {
			case MYSQL:
				lockOk = obtenerLockMySQL(idLock);
				break;

			case POSTGRES:
				lockOk = obtenerLockPostgres(idLock);
				break;

			default:
				return ERROR_BASEDEDATOS_NO_SOPORTADA;
		}

		return lockOk ? OK : ERROR_LOCK_NO_OBTENIDO;
	}

	public int liberarLock(String idLock) {

		String baseDeDatos = determinarBaseDeDatos();
		if (baseDeDatos == null) {
			return ERROR_BASEDEDATOS_NO_RECONOCIDA;
		}

		boolean lockOk;

		switch (baseDeDatos) {
			case MYSQL:
				lockOk = liberarLockMySQL(idLock);
				break;

			case POSTGRES:
				lockOk = liberarLockPostgres(idLock);
				break;

			default:
				return ERROR_BASEDEDATOS_NO_SOPORTADA;
		}

		return lockOk ? OK : ERROR_LOCK_NO_OBTENIDO;
	}

	private String determinarBaseDeDatos() {

		EntityManagerFactory emf = em.getEntityManagerFactory();
		String hibernateDialect = (String) emf.getProperties().get("hibernate.dialect");

		if (StringUtils.isBlank(hibernateDialect)) {
			return null;
		}

		if (hibernateDialect.toLowerCase().contains(MYSQL)) {
			return MYSQL;

		} else if (hibernateDialect.toLowerCase().contains(POSTGRES)) {
			return POSTGRES;

		} else {
			return "otra";
		}
	}

	private boolean obtenerLockMySQL(String idLock) {

		boolean result;
		try {
			String id = (String) em.createNativeQuery("SELECT * from mutex_lock where id = '" + idLock + "' FOR UPDATE")
					.setHint("javax.persistence.query.timeout", 5000)
					.getSingleResult();
		
			LOGGER.log(Level.FINE, "[connID:{0}] [idLock:{1}] Lock obtenido", 
					new Object[]{getConnectionId(), id});

			result = true;
			
		} catch (Exception ex) {
			
			LOGGER.log(Level.WARNING, "[idLock:{0}] Lock no obtenido: {1}", 
					new Object[]{idLock, ex.getMessage()});

			result = false;
		}
		
		return result;	
	}

	private boolean liberarLockMySQL(String idLock) {

		em.createNativeQuery("UPDATE mutex_lock SET id ='" + idLock + "' where id = '" + idLock + "'").executeUpdate();

		LOGGER.log(Level.FINE, "[connID:{0}] [idLock:{1}] Lock liberado", new Object[]{getConnectionId(), idLock});

		return true;
	}

	private boolean obtenerLockPostgres(String idLock) {

		return (boolean) em.createNativeQuery("SELECT pg_try_advisory_lock(" + idLock + ")").getSingleResult();
	}

	private boolean liberarLockPostgres(String idLock) {

		return (boolean) em.createNativeQuery("SELECT pg_advisory_unlock(" + idLock + ")").getSingleResult();
	}

	private String getConnectionId() {

		return em.createNativeQuery("select connection_id()").getSingleResult().toString();
	}
}
