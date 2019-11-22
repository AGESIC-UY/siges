package com.sofis.web.utils;

//import com.sofis.business.ejbs.ArchivoContenidoLocalLocal;
//import com.sofis.business.ejbs.DatosUsuarioRemote;
//import com.sofis.business.ejbs.EntityManagementBeanRemote;
import com.sofis.business.ejbs.ConfiguracionBean;
import com.sofis.business.ejbs.EntityManagementBean;
import com.sofis.business.ejbs.UsuarioLocal;
import com.sofis.exceptions.TechnicalException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Usuario
 */
public class EJBUtils {

	private static final Logger logger = Logger.getLogger(EJBUtils.class.getName());

	private static Object lookup(String beanName, String viewClassFullName, boolean stateful) throws NamingException {

		Context context = null;
		try {
			final String appName = "SS-SIGES-ear-5.9.1";
			final String moduleName = "SS-SIGES-ejb-5.9.1";
			final String distinctName = "";
			String looks = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassFullName;
			if (stateful) {
				looks += "?stateful";
			}
			Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			context = new InitialContext(jndiProperties);
			context.addToEnvironment("sistema", "SS-SIGES");
			return (Object) context.lookup(looks);
		} finally {
			if (context != null) {
				context.close();
			}
		}
	}

	private static Object lookupBean(Class clazz) throws NamingException {
		Context context = null;
		try {
			final String appName = "SS-SIGES-ear-5.9.1";
			final String moduleName = "SS-SIGES-ejb-5.9.1";
			String looks = "java:global/" + appName + "/" + moduleName + "/" + clazz.getSimpleName() + "!" + clazz.getName();
			Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			context = new InitialContext(jndiProperties);
			context.addToEnvironment("sistema", "SS-SIGES");
			return (Object) context.lookup(looks);

		} finally {
			if (context != null) {
				context.close();
			}
		}
	}

	public static EntityManagementBean getEntityManagement() throws TechnicalException {
		try {
			return (EntityManagementBean) lookupBean(EntityManagementBean.class);
		} catch (NamingException ex) {
			TechnicalException te = new TechnicalException(ex);
			te.addError(ex.getMessage());
			throw te;
		}
	}

//    public static DatosUsuarioRemote getDatosUsuario() throws TechnicalException {
//        try {
//            return (DatosUsuarioRemote) lookup("DatosUsuario", DatosUsuarioRemote.class.getName(), true);
//        } catch (NamingException ex) {
//            TechnicalException te = new TechnicalException(ex);
//            te.addError(ex.getMessage());
//            throw te;
//        }
//    }
//    public static ArchivoContenidoLocalLocal getArchivoContenidoLocal() throws TechnicalException {
//        try {
//            return (ArchivoContenidoLocalLocal) lookup("ArchivoContenidoLocal", ArchivoContenidoLocalLocal.class.getName(), false);
//        } catch (NamingException ex) {
//            TechnicalException te = new TechnicalException(ex);
//            te.addError(ex.getMessage());
//            throw te;
//        }
//    }
	public static UsuarioLocal getUsuarioLocal() throws TechnicalException {
		try {
			UsuarioLocal usuLoc = (UsuarioLocal) lookup("SsUsuarioBean", UsuarioLocal.class.getName(), false);
			return usuLoc;
		} catch (NamingException ex) {
			logger.log(Level.SEVERE, null, ex);
			TechnicalException te = new TechnicalException(ex);
			te.addError(ex.getMessage());
			throw te;
		}
	}

	public static ConfiguracionBean getConfiguracionBean() throws TechnicalException {
		try {

			ConfiguracionBean bean = (ConfiguracionBean) lookupBean(ConfiguracionBean.class);
			return bean;
		} catch (NamingException ex) {
			TechnicalException te = new TechnicalException(ex);
			te.addError(ex.getMessage());
			throw te;
		}
	}

}
