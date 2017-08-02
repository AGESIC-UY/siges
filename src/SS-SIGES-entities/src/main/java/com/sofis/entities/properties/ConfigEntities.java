package com.sofis.entities.properties;

import java.util.ResourceBundle;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class ConfigEntities {
    
    private static final String CONFIG = "com.sofis.generico.entities.configEntites";

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
