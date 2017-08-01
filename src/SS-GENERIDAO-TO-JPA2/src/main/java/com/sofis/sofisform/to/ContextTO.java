/*
 *  Clase desarrollada por Sofis Solutions
 *  
 */

package com.sofis.sofisform.to;

import java.io.Serializable;
import org.jdom.Element;

/**
 * Clase desarrollada por Sofis Solutions
 * var="usuario" type="SIMPLE" bean="" objectname=""
 * @author Sofis Solutions
 */
public class ContextTO extends ContainerComponentTO implements Serializable{

     public enum ContextType{
        SIMPLE("SIMPLE"),
        COLLECTION("COLLECTION");

        private final String type;
        ContextType(String c) {
            this.type = c;
        }
    }

    private String var;
    private ContextType type;
    private String bean;
    private String methodname;

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

  

    public ContextType getType() {
        return type;
    }

    public void setType(ContextType type) {
        this.type = type;
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
