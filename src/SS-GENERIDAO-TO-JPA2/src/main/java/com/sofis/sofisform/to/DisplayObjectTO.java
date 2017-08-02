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
 *
 *  <displayObject var="displayT" bean="" beanMethod="" class=""/>
 */
public class DisplayObjectTO extends ContainerComponentTO implements Serializable{
   
    private String var;
    private String class_;
    private String classweb;
    private String propertyId;
    private String bean;
    private String beanMethod;

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public String getBeanMethod() {
        return beanMethod;
    }

    public void setBeanMethod(String beanMethod) {
        this.beanMethod = beanMethod;
    }



    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getClassweb() {
        return classweb;
    }

    public void setClassweb(String classweb) {
        this.classweb = classweb;
    }


    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    @Override
    public Element toXML() {
        Element toReturn = new Element("displayObject");
        toReturn = super.toXMLMetadata(toReturn);
        toReturn.setAttribute("bean",this.getBean() +"");
        toReturn.setAttribute("beanMethod",this.getBeanMethod() +"");
        toReturn.setAttribute("var",this.getVar() +"");
        toReturn.setAttribute("class", this.getClass_() +"");
        toReturn.setAttribute("classweb", this.getClassweb() +"");
        toReturn.setAttribute("propertyId",this.getPropertyId() +"");
        return toReturn;
    }

    
   
}
