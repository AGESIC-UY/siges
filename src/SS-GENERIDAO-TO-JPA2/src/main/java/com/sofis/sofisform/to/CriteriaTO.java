/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sofis.sofisform.to;

import java.io.Serializable;
import java.util.HashMap;
import org.jdom.Element;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public abstract class CriteriaTO implements Serializable{

    public abstract String getStringQuery(HashMap col, HashMap leftJoin);
    public abstract HashMap<String,ValueCriteriaTO> getValueCriteriaTO();
    public abstract Element toXML();
    
   
}
