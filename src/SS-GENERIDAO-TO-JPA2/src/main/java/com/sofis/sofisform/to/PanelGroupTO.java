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
public class PanelGroupTO extends ContainerComponentTO implements Serializable{

    @Override
    public Element toXML() {
        Element toReturn = new Element("panelgroup");
        toReturn = super.toXMLMetadata(toReturn);
        return toReturn;
    }

}
