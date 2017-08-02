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
public class InputRichTextLabelTO extends BindingComponentTO implements Serializable{

    private String labelKey;
    private String customConfigPath;


    

    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

    public String getCustomConfigPath() {
        return customConfigPath;
    }

    public void setCustomConfigPath(String customConfigPath) {
        this.customConfigPath = customConfigPath;
    }



    @Override
    public Element toXML() {
        Element toReturn = new Element("inputrichtextlabel");
        toReturn = super.toXMLMetadata(toReturn);
        if (this.getCustomConfigPath() == null){
            toReturn.setAttribute("customConfigPath", "");
        }else{
            toReturn.setAttribute("customConfigPath", this.getCustomConfigPath());
        }

        if (this.getLabelKey() == null){
            toReturn.setAttribute("labelKey", "");
        }else{
            toReturn.setAttribute("labelKey", this.getLabelKey());
        }
        
        return toReturn;
    }
    



}
