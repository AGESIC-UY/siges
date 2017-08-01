/*
 *  Clase desarrollada por Sofis Solutions
 *  
 */

package com.sofis.sofisform.to;

import java.io.Serializable;
import org.jdom.Element;

/**
 * Clase desarrollada por Sofis Solutions
 * @author Sofis Solutions
 */
public  class BindingComponentTO extends ContainerComponentTO implements Serializable{

    private String entityvar;
    private String property;
    private String propertyClass;
    private String propertyClassWeb;
    private String propertyId;
    private String defaultModelName;
    private String defaultModelNameProperty;
    private String valueChangeListenerBean;
    private String valueChangeListenerMethod;

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }


    public String getPropertyClassWeb() {
        return propertyClassWeb;
    }

    public void setPropertyClassWeb(String propertyClassWeb) {
        this.propertyClassWeb = propertyClassWeb;
    }


     
    public String getDefaultModelName() {
        return defaultModelName;
    }

    public void setDefaultModelName(String defaultModelName) {
        this.defaultModelName = defaultModelName;
    }

    public String getDefaultModelNameProperty() {
        return defaultModelNameProperty;
    }

    public void setDefaultModelNameProperty(String defaultModelNameProperty) {
        this.defaultModelNameProperty = defaultModelNameProperty;
    }

    public String getEntityvar() {
        return entityvar;
    }

    public void setEntityvar(String entityvar) {
        this.entityvar = entityvar;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getPropertyClass() {
        return propertyClass;
    }

    public void setPropertyClass(String propertyClass) {
        this.propertyClass = propertyClass;
    }

     public String getValueChangeListenerBean() {
        return valueChangeListenerBean;
    }

    public void setValueChangeListenerBean(String valueChangeListenerBean) {
        this.valueChangeListenerBean = valueChangeListenerBean;
    }

    public String getValueChangeListenerMethod() {
        return valueChangeListenerMethod;
    }

    public void setValueChangeListenerMethod(String valueChangeListenerMethod) {
        this.valueChangeListenerMethod = valueChangeListenerMethod;
    }



    @Override
    public Element toXMLMetadata(Element toReturn) {
        toReturn= super.toXMLMetadata(toReturn);
        if (this.getEntityvar() == null){
            toReturn.setAttribute("entityvar", "");
        }else{
            toReturn.setAttribute("entityvar", this.getEntityvar());
        }
        if (this.getProperty() == null){
            toReturn.setAttribute("property", "");
        }else{
            toReturn.setAttribute("property", this.getProperty());
        }
        if (this.getPropertyClass() == null){
            toReturn.setAttribute("propertyClass", "");
        }else{
            toReturn.setAttribute("propertyClass", this.getPropertyClass());
        }
        if (this.getPropertyClassWeb() == null){
            toReturn.setAttribute("propertyClassWeb", "");
        }else{
            toReturn.setAttribute("propertyClassWeb", this.getPropertyClassWeb());
        }
        if (this.getPropertyId() == null){
            toReturn.setAttribute("propertyId", "");
        }else{
            toReturn.setAttribute("propertyId", this.getPropertyId());
        }

        if (this.getValueChangeListenerBean() == null){
            toReturn.setAttribute("valueChangeListenerBean", "");
        }else{
            toReturn.setAttribute("valueChangeListenerBean", this.getValueChangeListenerBean());
        }

        if (this.getValueChangeListenerMethod() == null){
            toReturn.setAttribute("valueChangeListenerMethod", "");
        }else{
            toReturn.setAttribute("valueChangeListenerMethod", this.getValueChangeListenerMethod());
        }
        return toReturn;
    }

    @Override
    public  Element toXML(){
        Element e = new Element("bindingcomponent");
        return e;
    }

}
