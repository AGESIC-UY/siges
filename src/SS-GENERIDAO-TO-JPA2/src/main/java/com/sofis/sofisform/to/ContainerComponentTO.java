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
public abstract class ContainerComponentTO extends ComponentTO implements Serializable{

    List<ComponentTO> childs = new ArrayList();

    public List<ComponentTO> getChilds() {
        return childs;
    }

    public void setChilds(List<ComponentTO> childs) {
        this.childs = childs;
    }

    public void addChild(ComponentTO com) {
        if (childs == null) {
            childs = new ArrayList();
        }
        childs.add(com);
    }

    @Override
    public abstract Element toXML();

    @Override
    public Element toXMLMetadata(Element toReturn) {
        toReturn = super.toXMLMetadata(toReturn);
        List<Element> childsElements = new ArrayList();
        if (childs != null && childs.size() > 0) {
            for (ComponentTO child : childs) {
                if (child != null){
                    childsElements.add(child.toXML());
                }
                
            }
            toReturn.getChildren().addAll(childsElements);
        }
        return toReturn;
    }
}
