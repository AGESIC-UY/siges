/*
 *  Clase desarrollada por Sofis Solutions
 *  
 */
package com.sofis.sofisform.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jdom.Element;

/**
 * Clase desarrollada por Sofis Solutions
 * @author Sofis Solutions
 */
public class FormTO extends ComponentTO implements Serializable{

    private String messagebundle;
    private ModelTO model;
    private List<ComponentTO> components;

    public String getMessagebundle() {
        return messagebundle;
    }

    public void setMessagebundle(String messagebundle) {
        this.messagebundle = messagebundle;
    }

    public List<ComponentTO> getComponents() {
        return components;
    }

    public void setComponents(List<ComponentTO> components) {
        this.components = components;
    }

    public ModelTO getModel() {
        return model;
    }

    public void setModel(ModelTO model) {
        this.model = model;
    }

    public void addComponent(ComponentTO f) {
        if (components == null) {
            components = new ArrayList<ComponentTO>();
        }
        if (f != null) {
            components.add(f);
        }
    }

    public List<SelectItemTO> getSelectItems() {
        List<SelectItemTO> toReturn = null;
        toReturn = getSelectItems(components);
        return toReturn;
    }

    public List<InputFileLabelTO> getInputFiles() {
        List<InputFileLabelTO> toReturn = null;
        toReturn = getInputFiles(components);
        return toReturn;
    }

    /**
     * Retorna las columnas que estan asociadas a un outputresource
     * @return
     */
    public HashMap<CollectionTO,List<ColumnTO>> getColumnsOutputResource() {
        HashMap toReturn = null;
        toReturn = getColumnsOutputResource(components);
        return toReturn;
    }
    
    
    public List<InputPopupTO> getInputPopups() {
        List<InputPopupTO> toReturn = null;
        toReturn = getInputPopup(components);
        return toReturn;
    }

     /**
     * Meotodo auxiliar
     * @param components
     * @return
     */
    private HashMap<CollectionTO,List<ColumnTO>> getColumnsOutputResource(List<ComponentTO> components) {
        HashMap<CollectionTO,List<ColumnTO>> toReturn = new HashMap();
        for (ComponentTO componentTO : components) {
            if (componentTO instanceof CollectionTO) {
                CollectionTO col = (CollectionTO)componentTO;
                List<ComponentTO> comp = col.getChilds();
                for(ComponentTO c : comp){
                    if (c instanceof ColumnTO){
                        ColumnTO column = (ColumnTO)c;
                        //es un link es una columna que despliega un link para descargar un archivo
                        if (column.isLink()){
                           if (!toReturn.containsKey(col)) {
                               toReturn.put(col,new ArrayList<ColumnTO>());
                           }
                           toReturn.get(col).add(column);
                        }
                    }
                }
            } else if (componentTO instanceof ContainerComponentTO) {
                ContainerComponentTO conta = (ContainerComponentTO) componentTO;
                HashMap toAdd = getColumnsOutputResource(conta.getChilds());
                if (toAdd != null && toAdd.size() > 0) {
                    toReturn.putAll(toAdd);
                }
            } else {
                continue;
            }
        }
        return toReturn;
    }


    public List<CollectionTO> getCollections() {
        List<CollectionTO> toReturn = null;
        toReturn = getCollection(components);
        return toReturn;
    }

    /**
     * Meotodo auxiliar
     * @param components
     * @return
     */
    private List<CollectionTO> getCollection(List<ComponentTO> components) {
        List<CollectionTO> toReturn = new ArrayList();
        for (ComponentTO componentTO : components) {
            if (componentTO instanceof CollectionTO) {
                toReturn.add((CollectionTO) componentTO);
            } else if (componentTO instanceof ContainerComponentTO) {
                ContainerComponentTO conta = (ContainerComponentTO) componentTO;
                List<CollectionTO> toAdd = getCollection(conta.getChilds());
                if (toAdd != null && toAdd.size() > 0) {
                    toReturn.addAll(toAdd);
                }
            } else {
                continue;
            }
        }
        return toReturn;
    }

    /**
     * Meotodo auxiliar
     * @param components
     * @return
     */
    private List<SelectItemTO> getSelectItems(List<ComponentTO> components) {
        List<SelectItemTO> toReturn = new ArrayList();
        for (ComponentTO componentTO : components) {
            if (componentTO instanceof SelectItemTO) {
                toReturn.add((SelectItemTO) componentTO);
            } else if (componentTO instanceof ContainerComponentTO) {
                ContainerComponentTO conta = (ContainerComponentTO) componentTO;
                List<SelectItemTO> toAdd = getSelectItems(conta.getChilds());
                if (toAdd != null && toAdd.size() > 0) {
                    toReturn.addAll(toAdd);
                }
            } else {
                continue;
            }
        }
        return toReturn;
    }


     /**
     * Meotodo auxiliar
     * @param components
     * @return
     */
    private List<InputPopupTO> getInputPopup(List<ComponentTO> components) {
        List<InputPopupTO> toReturn = new ArrayList();
        for (ComponentTO componentTO : components) {
            if (componentTO instanceof InputPopupTO) {
                toReturn.add((InputPopupTO) componentTO);
            } else if (componentTO instanceof ContainerComponentTO) {
                ContainerComponentTO conta = (ContainerComponentTO) componentTO;
                List<InputPopupTO> toAdd = getInputPopup(conta.getChilds());
                if (toAdd != null && toAdd.size() > 0) {
                    toReturn.addAll(toAdd);
                }
            } else {
                continue;
            }
        }
        return toReturn;
    }


    /**
     * Meotodo auxiliar
     * @param components
     * @return
     */
    private List<InputFileLabelTO> getInputFiles(List<ComponentTO> components) {
        List<InputFileLabelTO> toReturn = new ArrayList();
        for (ComponentTO componentTO : components) {
            if (componentTO instanceof InputFileLabelTO) {
                toReturn.add((InputFileLabelTO) componentTO);
            } else if (componentTO instanceof ContainerComponentTO) {
                ContainerComponentTO conta = (ContainerComponentTO) componentTO;
                List<InputFileLabelTO> toAdd = getInputFiles(conta.getChilds());
                if (toAdd != null && toAdd.size() > 0) {
                    toReturn.addAll(toAdd);
                }
            } else {
                continue;
            }
        }
        return toReturn;
    }

    @Override
    public Element toXML() {
        Element toReturn = new Element("form");
        toReturn.setAttribute("key", this.getKey() + "");
        toReturn.setAttribute("messageBundle", this.getMessagebundle() + "");
        if (this.getModel() != null) {
            Element modelElement = this.getModel().toXML();
            toReturn.getChildren().add(modelElement);
        }

        if (this.getComponents() != null) {
            Element componentesElement = new Element("components");
            for (ComponentTO comp : this.getComponents()) {
                componentesElement.getChildren().add(comp.toXML());
            }

            toReturn.getChildren().add(componentesElement);
        }

        return toReturn;
    }
}
