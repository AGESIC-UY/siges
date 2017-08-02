/*
 *  Clase desarrollada por Sofis Solutions
 *  
 */

package com.sofis.sofisform.to;

import java.io.Serializable;
import org.jdom.Element;

/**
 * Clase desarrollada por Sofis Solutions
 *                                     <inputtextarealabel key =""
                                                    entityvar="personaFisica"
                                                    property="apellido"
                                                    propertyClass="String.class"
                                                    labelkey="Apellido"/>

 * @author Sofis Solutions
 */
public class InputTextAreaLabelTO extends BindingComponentTO implements Serializable{

    private String labelKey;
    private String cols;

    public String getCols() {
        return cols;
    }

    public void setCols(String cols) {
        this.cols = cols;
    }

    

    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

    @Override
    public Element toXML() {
        Element toReturn = new Element("inputtextarealabel");
        toReturn = super.toXMLMetadata(toReturn);
        if (this.getCols() == null){
            toReturn.setAttribute("cols", "");
        }else{
            toReturn.setAttribute("cols", this.getCols());
        }

        if (this.getLabelKey() == null){
            toReturn.setAttribute("labelKey", "");
        }else{
            toReturn.setAttribute("labelKey", this.getLabelKey());
        }
        
        return toReturn;
    }
    



}
