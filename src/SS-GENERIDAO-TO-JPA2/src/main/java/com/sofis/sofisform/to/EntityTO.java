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
 * <entity var="curso" class="Curso" classweb="Curso" type="SIMPLE"/>|
 */
public class EntityTO extends ContainerComponentTO implements Serializable{

    public enum EntityType{
        SIMPLE("SIMPLE"),
        COLLECTION("COLLECTION");

        private final String type;
        EntityType(String c) {
            this.type = c;
        }
    }



    private String var;
    private String class_;
    private String classweb;
    private EntityType type;
    private String propertyId;

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

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
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
        Element toReturn  = new Element("entity");
        toReturn = super.toXMLMetadata(toReturn);
        toReturn.setAttribute("class", this.getClass_() +"");
        toReturn.setAttribute("classweb", this.getClassweb() +"");
        toReturn.setAttribute("propertyId", this.getPropertyId() +"");
        toReturn.setAttribute("var", this.getVar() +"");
        if (this.getType() != null){
            toReturn.setAttribute("type", this.getType().name());
        }else{
            toReturn.setAttribute("type", "");
        }
        
        return toReturn;
    }
   
}
