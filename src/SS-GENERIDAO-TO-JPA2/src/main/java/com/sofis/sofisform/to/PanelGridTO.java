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
public class PanelGridTO extends ContainerComponentTO implements Serializable{

    private int columns;

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    @Override
    public Element toXML() {
        Element toReturn = new Element("panelgrid");
        toReturn = super.toXMLMetadata(toReturn);
        toReturn.setAttribute("columns", this.getColumns() +"");
        return toReturn;
    }



}
