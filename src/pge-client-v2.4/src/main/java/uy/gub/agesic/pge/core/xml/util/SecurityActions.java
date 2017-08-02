package uy.gub.agesic.pge.core.xml.util;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

/**
 * 
 * @author fsierra
 * 
 */
class SecurityActions {
	/**
	 * Get context classloader.
	 * 
	 * @return the current context classloader
	 */
	static ClassLoader getContextClassLoader() {
		SecurityManager sm = System.getSecurityManager();
		if (sm == null) {
			return Thread.currentThread().getContextClassLoader();
		} else {
			return AccessController
					.doPrivileged(new PrivilegedAction<ClassLoader>() {
						public ClassLoader run() {
							return Thread.currentThread()
									.getContextClassLoader();
						}
					});
		}
	}

	/**
	 * Load a class using the provided classloader
	 * 
	 * @param name
	 * @return
	 * @throws PrivilegedActionException
	 */
	static Class<?> loadClass(final ClassLoader cl, final String name)
			throws PrivilegedActionException, ClassNotFoundException {
		SecurityManager sm = System.getSecurityManager();
		if (sm == null) {
			return cl.loadClass(name);
		} else {
			return AccessController
					.doPrivileged(new PrivilegedExceptionAction<Class<?>>() {
						public Class<?> run() throws PrivilegedActionException {
							try {
								return cl.loadClass(name);
							} catch (Exception e) {
								throw new PrivilegedActionException(e);
							}
						}
					});
		}
	}

	/**
	 * Return the current value of the specified system property
	 * 
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	static String getSystemProperty(final String name, final String defaultValue) {
		PrivilegedAction<String> action = new PrivilegedAction<String>() {
			public String run() {
				return System.getProperty(name, defaultValue);
			}
		};
		return AccessController.doPrivileged(action);
	}
}
