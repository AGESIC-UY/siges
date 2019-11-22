package com.sofis.business.utils;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Usuario
 */
public class EJBUtils {

    public static Object lookup(String looks) throws NamingException {

        Context context = null;
        try {
            Hashtable jndiProperties = new Hashtable();
            jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            context = new InitialContext(jndiProperties);
            return (Object) context.lookup(looks);
        } finally {
            if (context != null) {
                context.close();
            }
        }
    }
    
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

    public static Object lookupBean(Class clazz) throws NamingException {
        Context context = null;
        try {
            final String appName = "SS-SIGES-ear-5.9.1";
            final String moduleName = "SS-SIGES-ejb-5.9.1";
            String looks = "java:global/" + appName + "/" + moduleName +"/" + clazz.getSimpleName() +"!"+ clazz.getName();
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

}
