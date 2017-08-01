/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sofis.sofisform.to;

import java.io.Serializable;
import org.jdom.Element;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class ValidationTO implements Serializable{

    private String class_ = "";

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public Element toXML() {
        Element toReturn = new Element("validation");
        toReturn.setAttribute("class", this.getClass_());
        return toReturn;
    }
    



}
