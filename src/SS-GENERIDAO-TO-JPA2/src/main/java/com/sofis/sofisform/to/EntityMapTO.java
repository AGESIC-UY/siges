/*
 *  Clase desarrollada por Sofis Solutions
 *  
 */

package com.sofis.sofisform.to;

import java.io.Serializable;
import org.jdom.Element;

/**
 * Clase desarrollada por Sofis Solutions
 * //<entitymap var="personaFisica" toentityvar="colono" property="personaFisica"/>
 * @author Sofis Solutions
 */
public class EntityMapTO extends ComponentTO implements Serializable{

    public String var;
    public String toentityvar;
    public String property;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getToentityvar() {
        return toentityvar;
    }

    public void setToentityvar(String toentityvar) {
        this.toentityvar = toentityvar;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    public Element toXML() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

}
