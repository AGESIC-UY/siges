package com.sofis.web.seguridad;

import com.sofis.business.ejbs.ConfiguracionBean;
import com.sofis.business.utils.Utils;
import com.sofis.entities.data.SsUsuario;
import com.sofis.web.utils.EJBUtils;
import java.security.acl.Group;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

/**
 *
 * @author Usuario
 */
public class SimpleUsernamePasswordLoginModule extends UsernamePasswordLoginModule {

	private static final Logger logger = Logger.getLogger(SimpleUsernamePasswordLoginModule.class.getName());

	@SuppressWarnings("rawtypes")
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map sharedState,
			Map options) {
		// We could read options passed via <module-option> in standalone.xml if there were any here
		// For an example see http://docs.redhat.com/docs/en-US/JBoss_Enterprise_Application_Platform/5/html/Security_Guide/sect-Custom_LoginModule_Example.html

		// We could also f.ex. lookup a data source in JNDI
		// For an example see http://www.docjar.com/html/api/org/jboss/security/auth/spi/DatabaseServerLoginModule.java.html
		super.initialize(subject, callbackHandler, sharedState, options);
		//logger.info("SimpleUsernamePasswordLoginModule.initialize(...)");
	}

	/**
	 * (required) The UsernamePasswordLoginModule modules compares the result of
	 * this method with the actual password.
	 */
	@Override
	protected String getUsersPassword() throws LoginException {
		//logger.info("SimpleUsernamePasswordLoginModule.getUsersPassword(...)");
		//System.out.format("MyLoginModule: authenticating user '%s'\n", getUsername());
		// Lets pretend we got the password from somewhere and that it's, by a chance, same as the username
		String password = super.getUsername();
		// Let's also pretend that we haven't got it in plain text but encrypted
		// (the encryption being very simple, namely capitalization)
		password = password.toUpperCase();

		return password;
	}

	/**
	 * (optional) Override if you want to change how the password are compared
	 * or if you need to perform some conversion on them.
	 */
	@Override
	protected boolean validatePassword(String inputPassword, String expectedPassword) {
		//logger.info("SimpleUsernamePasswordLoginModule.validatePassword(...)");
		// Let's encrypt the password typed by the user in the same way as the stored password
		// so that they can be compared for equality.

		ConfiguracionBean configuracionBean = EJBUtils.getConfiguracionBean();

		String authLdapEnable = configuracionBean.obtenerCnfValorPorCodigo("AUTH_LDAP_ENABLE", null);
		boolean ldapMode = false;
		if (authLdapEnable != null && "true".equals(authLdapEnable)) {
			ldapMode = true;
			SsUsuario usu = EJBUtils.getUsuarioLocal().obtenerSsUsuarioPorLDAPUser(expectedPassword);
			if (usu != null) {

				String authLdapSearchName = configuracionBean.obtenerCnfValorPorCodigo("AUTH_LDAP_SEARCH_NAME", null);
				String authLdapSearchFilter = configuracionBean.obtenerCnfValorPorCodigo("AUTH_LDAP_SEARCH_FILTER", null);
				String authLdapInitialCtxFactory = configuracionBean.obtenerCnfValorPorCodigo("AUTH_LDAP_SEARCH_INITIAL_CTX_FACTORY", null);
				String authLdapURL = configuracionBean.obtenerCnfValorPorCodigo("AUTH_LDAP_SEARCH_URL", null);
				String authLdapSecurityPrincipal = configuracionBean.obtenerCnfValorPorCodigo("AUTH_LDAP_SECURITY_PRINCIPAL", null);
				String authLdapSecurityCredentials = configuracionBean.obtenerCnfValorPorCodigo("AUTH_LDAP_SECURITY_CREDENTIAL", null);
				String authLdapUserLoginFilter = configuracionBean.obtenerCnfValorPorCodigo("AUTH_LDAP_SEARCH_USER_LOGIN_FILTER", null);

				LDAPUtils lDAPUtils = new LDAPUtils(authLdapSearchName,
						authLdapSearchFilter,
						authLdapInitialCtxFactory,
						authLdapURL,
						authLdapSecurityPrincipal,
						authLdapSecurityCredentials,
						authLdapUserLoginFilter);

				if (lDAPUtils.validateLogin(expectedPassword, inputPassword)) {
					return true;
				}
			}

		}

		SsUsuario usu = EJBUtils.getUsuarioLocal().obtenerSsUsuarioPorMail(expectedPassword);

		if (ldapMode && (usu == null || !usu.isAdministrador())) {
			logger.log(Level.INFO, "Usuario ''{0}'' no se encuentra.", expectedPassword);
			return false;
		}

		if (usu == null) {
			logger.log(Level.INFO, "Usuario ''{0}'' no se encuentra.", expectedPassword);
		} else {
			logger.log(Level.INFO, "Validando usuario ''{0}''.", expectedPassword);
		}

		if (usu != null && usu.getUsuPassword() != null && inputPassword != null
				&& !usu.getUsuPassword().isEmpty() && !inputPassword.isEmpty()) {
			String pass = Utils.md5(inputPassword);
			if (pass.equals(usu.getUsuPassword())) {
				return true;
			}
		}
		return false;

	}

	/**
	 * (required) The groups of the user, there must be at least one group
	 * called "Roles" (though it likely can be empty) containing the roles the
	 * user has.
	 */
	@Override
	protected Group[] getRoleSets() throws LoginException {
		SimpleGroup group = new SimpleGroup("Roles");
		try {
			group.addMember(new SimplePrincipal("usuario_autenticado"));
		} catch (Exception e) {
			throw new LoginException("Failed to create group member for " + group);
		}
		return new Group[]{group};
	}
}
