/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.sofisform.to;

import java.io.Serializable;
import java.util.List;
import org.jdom.Element;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class SetTO extends ComponentTO implements Serializable {

    public enum Value {

        TODAY("TODAY"),
        USER("USER"),
        STRING("STRING"),
        BEAN("BEAN");
        private final String value;
        Value(String c) {
            this.value = c;
        }
    }

    public enum On {

        START("START"),
        UPDATE("UPDATE"),
        CREATE("CREATE"),
        DELETE("DELETE");
        private final String on;

        On(String c) {
            this.on = c;
        }
    }
   
    private String var;
    private String property;
    private String propertyClass;
    private Value value;
    private List<On> on;
    private String stringValue;
    private String beanName;
    private String beanMethod;
    private String expressionLanguage;

    public String getExpressionLanguage() {
        return expressionLanguage;
    }

    public void setExpressionLanguage(String expressionLanguage) {
        this.expressionLanguage = expressionLanguage;
    }

    

    public List<On> getOn() {
        return on;
    }

    public void setOn(List<On> on) {
        this.on = on;
    }

    public String getBeanMethod() {
        return beanMethod;
    }

    public void setBeanMethod(String beanMethod) {
        this.beanMethod = beanMethod;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getPropertyClass() {
        return propertyClass;
    }

    public void setPropertyClass(String propertyClass) {
        this.propertyClass = propertyClass;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

     @Override
    public Element toXML() {
        Element toReturn = new Element("set");

        toReturn.setAttribute("var", this.getVar() + "");
        toReturn.setAttribute("property", this.getProperty() + "");
        toReturn.setAttribute("propertyClass", this.getPropertyClass() + "");
        if (this.getValue() != null) {
            toReturn.setAttribute("value", this.getValue().name() + "");
        } else {
            toReturn.setAttribute("value", "");
        }

        String onS = "";
        if (this.getOn() != null) {
            for (Object onelement : this.getOn()) {
                On onelement1 = (On)onelement;
                if (!onS.equalsIgnoreCase("")) {
                    onS = onS + "," + onelement1.name();
                } else {
                    onS = onelement1.name();
                }

            }
            toReturn.setAttribute("on", onS);
        }else{
            toReturn.setAttribute("on", "");
        }



        return toReturn;
    }

}
