package com.sofis.business.properties;

import java.util.ResourceBundle;

/**
 *
 * @author Usuario
 */
public class ConfigApp {

    private static final String CONFIG = "com.sofis.generico.ejb.config.config";

    private static ResourceBundle bundle = ResourceBundle.getBundle(CONFIG);

    public static String getValue(String key) {
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        }
        return null;
    }

    public static boolean containsKey(String key) {
        return bundle.containsKey(key);
    }
}
