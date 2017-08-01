/*
 *  Clase desarrollada por Sofis Solutions
 *  
 */

package com.sofis.sofisform.to;

import java.io.Serializable;
import org.jdom.Element;

/**
 * Clase desarrollada por Sofis Solutions
 *                                     <inputtextarea key =""
                                                    entityvar="personaFisica"
                                                    property="apellido"
                                                    propertyClass="String.class"
                                                    labelkey="Apellido"/>

 * @author Sofis Solutions
 */
public class InputTextAreaTO extends BindingComponentTO implements Serializable{

    private String cols;

    public String getCols() {
        return cols;
    }

    public void setCols(String cols) {
        this.cols = cols;
    }


    @Override
    public Element toXML() {
        Element toReturn = new Element("inputtextarea");
        toReturn = super.toXMLMetadata(toReturn);
        if (this.getCols() == null){
            toReturn.setAttribute("cols", "");
        }else{
            toReturn.setAttribute("cols", this.getCols());
        }
        
        return toReturn;
    }

}
