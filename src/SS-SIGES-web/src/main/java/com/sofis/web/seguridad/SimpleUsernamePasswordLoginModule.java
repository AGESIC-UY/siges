package com.sofis.web.seguridad;

import com.sofis.business.ejbs.ConfiguracionBean;
import com.sofis.business.utils.Utils;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.TokenSegundoFactor;
import com.sofis.web.utils.EJBUtils;
import java.security.Principal;
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

    private SigesPrincipal identity = null;


    private static final Logger logger = Logger.getLogger(SimpleUsernamePasswordLoginModule.class.getName());

    public SimpleUsernamePasswordLoginModule() {
        logger.log(Level.INFO, "Constructor.");
    }



    @SuppressWarnings("rawtypes")
    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options) {
        logger.log(Level.INFO, "initialize.");
        super.initialize(subject, callbackHandler, sharedState, options);


    }

    /**
     * (required) The UsernamePasswordLoginModule modules compares the result of
     * this method with the actual password.
     */
    @Override
    protected String getUsersPassword() throws LoginException {
        String password = super.getUsername();
        password = password.toUpperCase();
        return password;
    }

    /**
     * (optional) Override if you want to change how the password are compared
     * or if you need to perform some conversion on them.
     */
    @Override
    protected boolean validatePassword(String inputPassword, String userEmail) {
        ConfiguracionBean configuracionBean = EJBUtils.getConfiguracionBean();

        String authLdapEnable = configuracionBean.obtenerCnfValorPorCodigo("AUTH_LDAP_ENABLE", null);
        boolean ldapMode = false;
        if (authLdapEnable != null && "true".equals(authLdapEnable)) {
            ldapMode = true;
            SsUsuario usu = EJBUtils.getUsuarioLocal().obtenerSsUsuarioPorLDAPUser(userEmail);
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

                if (lDAPUtils.validateLogin(userEmail, inputPassword)) {
                    return true;
                }
            }

        }

        SsUsuario usu = EJBUtils.getUsuarioLocal().obtenerSsUsuarioPorMail(userEmail);

        if (ldapMode && (usu == null || !usu.isAdministrador())) {
            return false;
        }
        boolean bloqueado = false;

        if (usu == null) {
            actualizarMensajeErrorLogeo(configuracionBean, "El usuario o la clave ingresada no es correcta.");
        } else {

            bloqueado = EJBUtils.getUsuarioLocal().usuarioBloqueado(usu.getUsuId());
            if (bloqueado) {
                actualizarMensajeErrorLogeo(configuracionBean, "El usuario ha sido bloqueado temporalmente, vuelva a intentar en unos minutos");
                return false;
            }

        }

        if (usu != null && usu.getUsuPassword() != null && inputPassword != null && !usu.getUsuPassword().isEmpty() && !inputPassword.isEmpty()) {
            String pass = Utils.md5(inputPassword);
            if (pass.equals(usu.getUsuPassword())) {
                EJBUtils.getUsuarioLocal().resetearIntentosFallidos(usu.getUsuId());
                return true;
            } else {
                actualizarMensajeErrorLogeo(configuracionBean, "El usuario o la clave ingresada no es correcta.");
                EJBUtils.getUsuarioLocal().gestionarIntentosFallidos(usu.getUsuId());
            }
        }
        return false;

    }

    /**
     * Usa la primera configuración MENSAJE_ERROR_LOGEO de cualquier organismo
     * (menor orgPk), sin depender del organismo 1.
     */
    private void actualizarMensajeErrorLogeo(ConfiguracionBean configuracionBean, String mensaje) {
        Configuracion configMensajeLogeo = configuracionBean.obtenerCnfPorCodigoPrimerOrganismo("MENSAJE_ERROR_LOGEO");
        if (configMensajeLogeo != null) {
            configMensajeLogeo.setCnfValor(mensaje);
            configuracionBean.guardar(configMensajeLogeo);
        } else {
            logger.log(Level.WARNING, "No existe la configuraci\u00f3n MENSAJE_ERROR_LOGEO para ning\u00fan organismo.");
        }
    }

    @Override
    protected Principal getIdentity() {

        if(this.identity != null) {
            //Ya se había creado el identity local con el doble factor
            logger.log(Level.INFO, "getIdentity 1 Retornando this.identity previo");
            return this.identity;
        }

        if(this.loginOk) {
            //No se ha creado el identity local con el doble factor pero el login está ok, se crea ahora
            Configuracion confCod = EJBUtils.getConfiguracionBean().obtenerCnfPorCodigoYOrg("DOBLE_FACTOR_ACTIVO", null);
            if(!confCod.getCnfValor().equals("true")) {

                SsUsuario usuario = EJBUtils.getUsuarioLocal().obtenerSsUsuarioPorMail(super.getIdentity().getName());
                EJBUtils.getUsuarioLocal().actualizarFechaLogin(usuario.getUsuId());
                this.identity = new SigesPrincipal(super.getIdentity(), null);
            } else {
                TokenSegundoFactor token = EJBUtils.getUsuarioLocal().crearTokenSegundoFactor(super.getIdentity().getName());
                this.identity = new SigesPrincipal(super.getIdentity(), token);
            }
            logger.log(Level.INFO, "getIdentity 1 Retornando this.identity nuevo");
            return this.identity;
        }

        //No se ha creado el identity local con el doble factor y el login NO está ok, se retorna el básico
        logger.log(Level.INFO, "getIdentity 1 Retornando super.identity");
        return super.getIdentity();
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
