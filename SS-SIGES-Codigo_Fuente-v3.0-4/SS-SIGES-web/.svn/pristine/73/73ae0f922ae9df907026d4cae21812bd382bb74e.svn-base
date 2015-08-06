package com.sofis.web.seguridad;

import com.sofis.business.utils.Utils;
import com.sofis.entities.data.SsUsuario;
import com.sofis.web.utils.EJBUtils;
import java.security.acl.Group;
import java.util.Map;
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

    @SuppressWarnings("rawtypes")
    public void initialize(Subject subject, CallbackHandler callbackHandler,
            Map sharedState,
            Map options) {
        // We could read options passed via <module-option> in standalone.xml if there were any here
        // For an example see http://docs.redhat.com/docs/en-US/JBoss_Enterprise_Application_Platform/5/html/Security_Guide/sect-Custom_LoginModule_Example.html

        // We could also f.ex. lookup a data source in JNDI
        // For an example see http://www.docjar.com/html/api/org/jboss/security/auth/spi/DatabaseServerLoginModule.java.html
        super.initialize(subject, callbackHandler, sharedState, options);
        //System.out.println("SimpleUsernamePasswordLoginModule.initialize(...)");
    }

    /**
     * (required) The UsernamePasswordLoginModule modules compares the result of
     * this method with the actual password.
     */
    @Override
    protected String getUsersPassword() throws LoginException {
        //System.out.println("SimpleUsernamePasswordLoginModule.getUsersPassword(...)");
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
        //System.out.println("SimpleUsernamePasswordLoginModule.validatePassword(...)");
        // Let's encrypt the password typed by the user in the same way as the stored password
        // so that they can be compared for equality.
        SsUsuario usu = EJBUtils.getUsuarioLocal().obtenerSsUsuarioPorMail(expectedPassword);
//        System.out.println("usu:" + (usu != null ? usu.getUsuNombreApellido() : "null") +"-"+usu.getUsuVigente());
//        System.out.println("pass:" + inputPassword);
//        System.out.println("MD5:" + Utils.md5(inputPassword));

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
