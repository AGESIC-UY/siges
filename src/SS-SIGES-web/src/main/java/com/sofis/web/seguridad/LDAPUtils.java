package com.sofis.web.seguridad;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 *
 * @author bruno
 */
public class LDAPUtils {

    private String searchName;
    private String searchFilter;
    private String initialCtxFactory;
    private String url;
    private String secPrincipal;
    private String secCredentials;
    private String userLoginFilter;
    private Hashtable env;

    public LDAPUtils(String searchName, String searchFilter, String initialCtxFactory,
            String url, String secPrincipal, String secCredentials, String userLoginFilter) {

        this.searchName = searchName;
        this.searchFilter = searchFilter;
        this.initialCtxFactory = initialCtxFactory;
        this.url = url;
        this.secPrincipal = secPrincipal;
        this.secCredentials = secCredentials;
        this.userLoginFilter = userLoginFilter;

    }

    public Boolean validateLogin(String userName, String userPassword) {

        Logger.getLogger(LDAPUtils.class.getName()).log(Level.INFO, "USER_CONTROLE_CREATE");

        DirContext ctx = null;
        DirContext ctx2 = null;

        try {

//            searchFilter = "(uid=:user)";
//            searchName = "dc=sofis";
//            initialCtxFactory = "com.sun.jndi.ldap.LdapCtxFactory";
//            url = "ldap://localhost:389";
//            secPrincipal = "cn=admin,dc=sofis";
//            secCredentials = "sofis2uy";
//            userLoginFilter = "uid=:user,ou=users,dc=sofis";

            env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, initialCtxFactory);
            env.put(Context.PROVIDER_URL, url);
            env.put(Context.SECURITY_PRINCIPAL, secPrincipal);
            env.put(Context.SECURITY_CREDENTIALS, secCredentials);

            ctx = new InitialDirContext(env);

            NamingEnumeration results = null;

            String name = searchName;
            String filter = searchFilter.replaceAll(":user", userName);

            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search(name, filter, controls);

            if (results.hasMore()) {
				env.put(Context.SECURITY_PRINCIPAL, userLoginFilter.replaceAll(":user", userName));
                env.put(Context.SECURITY_CREDENTIALS, userPassword);
                ctx2 = new InitialDirContext(env);
                return true;
            }

        } catch (NamingException ex) {
            Logger.getLogger(LDAPUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LDAPUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (NamingException ex) {
                    Logger.getLogger(LDAPUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ctx2 != null) {
                try {
                    ctx2.close();
                } catch (NamingException ex) {
                    Logger.getLogger(LDAPUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return false;

    }

}
