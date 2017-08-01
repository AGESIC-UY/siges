/*
 *  Clase desarrollada por Sofis Solutions
 *  
 */
package com.sofis.sofisform.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Element;

/**
 * Clase desarrollada por Sofis Solutions
 * @author Sofis Solutions
 */
public class CollectionTO extends BindingComponentTO implements Serializable{

    public enum CollectionType {

        TYPEOF("typeof");
        private String type;

        private CollectionType(String type) {
            this.type = type;
        }
    }
    /**
     * @deprecated
     */
    private CollectionType type;
    /**
     * @deprecated 
     */
    private ModelTO model;
    private List columnsAction = new ArrayList();
    private boolean columns;

    public boolean isColumns() {
        return columns;
    }

    public void setColumns(boolean columns) {
        this.columns = columns;
    }

    /**
     * @deprecated
     */
    public ModelTO getModel() {
        return model;
    }

    /**
     * @deprecated
     */
    public void setModel(ModelTO model) {
        this.model = model;
    }

    public List getColumnsAction() {
        return columnsAction;
    }

    public void setColumnsAction(List columnsAction) {
        this.columnsAction = columnsAction;
    }

    public CollectionType getType() {
        return type;
    }

    public void setType(CollectionType type) {
        this.type = type;
    }

    @Override
    public Element toXML() {
        Element toReturn = new Element("collection");
        toReturn = super.toXMLMetadata(toReturn);
        List<Element> columnsChildren = toReturn.getChildren("column");
        if (columnsChildren != null && columnsChildren.size() > 0) {
            Element columnsElements = new Element("columns");
             for (Element columnElement : columnsChildren){
                columnsElements.getChildren().add(columnElement.clone());
            }
             
            if (columnsAction != null && columnsAction.size() > 0) {
                Element columnsActionElement = new Element("columnactions");
                for (int i = 0; i < columnsAction.size(); i++) {
                    ComponentTO comp = (ComponentTO) columnsAction.get(i);
                        columnsActionElement.getChildren().add(comp.toXML());
                }
                columnsElements.getChildren().add(columnsActionElement);
            }
            toReturn.getChildren().add(columnsElements);
        }
        toReturn.removeChildren("column");
        return toReturn;
    }
}
