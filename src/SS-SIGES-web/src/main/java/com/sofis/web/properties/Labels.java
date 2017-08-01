package com.sofis.web.properties;

import com.sofis.business.properties.LabelsEJB;
import java.util.ResourceBundle;

/**
 *
 * @author Usuario
 */
public class Labels{

    public static String getValue(String key) {
        return LabelsEJB.getValue(key);
    }
    
    public static boolean containsKey(String key){
        return LabelsEJB.containsKey(key);
    }
}
