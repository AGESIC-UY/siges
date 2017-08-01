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
public class InputDateLabelTO extends InputDateTO implements Serializable{

     private String labelKey;

    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

    @Override
    public Element toXML() {
        Element toReturn = new Element("inputdatelabel");
        toReturn = this.toXMLMetadata(toReturn);
        if (this.getLabelKey() == null){
            toReturn.setAttribute("labelKey", "");
        }else{
            toReturn.setAttribute("labelKey", getLabelKey());
        }

        
        return toReturn;
    }



}
