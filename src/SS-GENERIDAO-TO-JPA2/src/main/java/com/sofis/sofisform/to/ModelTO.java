/*
 *  Clase desarrollada por Sofis Solutions
 *  
 */

package com.sofis.sofisform.to;

import java.io.Serializable;
import java.util.HashMap;
import org.jdom.Element;

/**
 * Clase desarrollada por Sofis Solutions
 * @author Sofis Solutions
 */
public class ModelTO implements Serializable{


    private HashMap<String, EntityTO> entities = new HashMap();
    private HashMap<String, DisplayObjectTO> displayObject = new HashMap();
    private HashMap<String, SetTO> set = new HashMap();
    private HashMap<String, ContextTO> contexts = new HashMap();
    private ValidationTO validation = null;

    public ValidationTO getValidation() {
        return validation;
    }

    public void setValidation(ValidationTO validation) {
        this.validation = validation;
    }

    

    public HashMap<String, ContextTO> getContexts() {
        return contexts;
    }

    public void setContexts(HashMap<String, ContextTO> contexts) {
        this.contexts = contexts;
    }

    public HashMap<String, SetTO> getSet() {
        return set;
    }

    public void setSet(HashMap<String, SetTO> set) {
        this.set = set;
    }

    
    

    public HashMap<String, EntityTO> getEntities() {
        return entities;
    }

    public void setEntities(HashMap<String, EntityTO> entities) {
        this.entities = entities;
    }
    
     public void addEntity(EntityTO f){
        if (entities == null){
            entities = new HashMap<String, EntityTO>();
        }
        entities.put(f.getVar(),f);
    }

     public void addDispayObject(DisplayObjectTO f){
        if (displayObject == null){
            displayObject = new HashMap<String, DisplayObjectTO>();
        }
        displayObject.put(f.getVar(),f);
    }

      public void addSet(SetTO f){
        if (set == null){
            set = new HashMap<String, SetTO>();
        }
        set.put(f.getVar() + "-"+f.getProperty(),f);
    }

     public void addContext(ContextTO f){
        if (contexts == null){
            contexts = new HashMap<String, ContextTO>();
        }
        contexts.put(f.getVar(),f);
    }

    public HashMap<String, DisplayObjectTO> getDisplayObject() {
        return displayObject;
    }

    public void setDisplayObject(HashMap<String, DisplayObjectTO> displayObject) {
        this.displayObject = displayObject;
    }

    public Element toXML(){
        Element toReturn = new Element("model");
        for (EntityTO e: entities.values()){
            toReturn.getChildren().add(e.toXML());
        }
        for (DisplayObjectTO e: displayObject.values()){
            toReturn.getChildren().add(e.toXML());
        }
        for (SetTO e: set.values()){
            toReturn.getChildren().add(e.toXML());
        }
        if (validation != null){
            Element validationE = validation.toXML();
            toReturn.getChildren().add(validationE);
        }
        
        return toReturn;
    }

}
