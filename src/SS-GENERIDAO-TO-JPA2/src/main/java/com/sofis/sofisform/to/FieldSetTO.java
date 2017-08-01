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
 * <sofis:fieldset>
 *               <f:facet name="svGroupLabel">
 *                   <ice:selectBooleanCheckbox  />
 *               </f:facet>
 *  </sofis:fieldset>
 *
 * Clase desarrollada por Sofis Solutions
 * @author Sofis Solutions
 */
public class FieldSetTO extends ContainerComponentTO implements Serializable{

    List<ComponentTO> legendComponents = new ArrayList();

    public List<ComponentTO> getLegendComponents() {
        return legendComponents;
    }

    public void setLegendComponents(List<ComponentTO> legendComponents) {
        this.legendComponents = legendComponents;
    }

    @Override
    public Element toXML() {
        Element toReturn = new Element("fieldset");
        toReturn = super.toXMLMetadata(toReturn);
        Element legendE = new Element("legend");
        for (ComponentTO compo : legendComponents){
            legendE.getChildren().add(compo.toXML());
        }
        toReturn.getChildren().add(legendE);
        return toReturn;
    }


    
}
