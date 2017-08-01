/*
 *  Clase desarrollada por Sofis Solutions
 *  
 */
package com.sofis.sofisform.to;

import java.io.Serializable;
import java.util.List;
import org.jdom.Element;

/**
 * Clase desarrollada por Sofis Solutions
 *  order="true"
propertyExpresion="#{currentRow['propertyMap']['nombre']} - #{currentRow['propertyMap']['codigo']}"
property=""
 *  key="BusquedaPaisNombre"
 * @author Sofis Solutions
 */
public class ColumnTO extends BindingComponentTO implements Serializable{

    /**
     * Sin uso en esta versión
     */
    private String propertyExpresion;
    /**
     * Si la columna se asocia al data exporter
     */
    private boolean includeInDataExporter = true;
    /**
     * Si se debe ordenar la tabla por esta columna
     */
    private boolean order;
    /**
     * En caso qeu la columna sea un date entonces se setea este elemento qeu define como se despliega el date en la tabla
     */
    InputDateTO componentDate;
    /**
     * En caso de ser una tabla editable, para cada columna se renderiza un componente
     */
    private List<ComponentTO> components;

    //si la propiedad es de tipo array of Bytes [B, se puede indicar si se quiere renderizar un link de descarga
    private boolean link = false;
    //si la propiedad es de tipo array of Bytes [B, se puede indicar si se quiere renderizar una imagen para previsualizar
    private boolean graphicImage = false;
    private String graphicImageWidth = "";
    private String graphicImageHeight = "";

    public boolean isGraphicImage() {
        return graphicImage;
    }

    public void setGraphicImage(boolean graphicImage) {
        this.graphicImage = graphicImage;
    }

    public String getGraphicImageHeight() {
        return graphicImageHeight;
    }

    public void setGraphicImageHeight(String graphicImageHeight) {
        this.graphicImageHeight = graphicImageHeight;
    }

    public String getGraphicImageWidth() {
        return graphicImageWidth;
    }

    public void setGraphicImageWidth(String graphicImageWidth) {
        this.graphicImageWidth = graphicImageWidth;
    }

    public boolean isLink() {
        return link;
    }

    public void setLink(boolean link) {
        this.link = link;
    }

    public boolean isIncludeInDataExporter() {
        return includeInDataExporter;
    }

    public void setIncludeInDataExporter(boolean includeInDataExporter) {
        this.includeInDataExporter = includeInDataExporter;
    }

    public List<ComponentTO> getComponents() {
        return components;
    }

    public void setComponents(List<ComponentTO> components) {
        this.components = components;
    }

    public InputDateTO getComponentDate() {
        return componentDate;
    }

    public void setComponentDate(InputDateTO componentDate) {
        this.componentDate = componentDate;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }

    public String getPropertyExpresion() {
        return propertyExpresion;
    }

    public void setPropertyExpresion(String propertyExpresion) {
        this.propertyExpresion = propertyExpresion;
    }

    @Override
    public Element toXML() {
        Element column = new Element("column");
        column = super.toXMLMetadata(column);
        column.setAttribute("order", this.isOrder() + "");
        column.setAttribute("includeInDataExporter", this.isIncludeInDataExporter() + "");

        if (this.getPropertyExpresion() == null) {
            column.setAttribute("propertyExpression", "");
        } else {
            column.setAttribute("propertyExpression", this.getPropertyExpresion());
        }

        //en caso de propiedad de la columna array of bytes
        column.setAttribute("graphicImage", this.isGraphicImage() + "");
        if (this.getGraphicImageWidth() == null) {
            column.setAttribute("graphicImageWidth", "");
        } else {
            column.setAttribute("graphicImageWidth", this.getGraphicImageWidth());
        }
        if (this.getGraphicImageHeight() == null) {
            column.setAttribute("graphicImageHeight", "");
        } else {
            column.setAttribute("graphicImageHeight", this.getGraphicImageHeight());
        }
        column.setAttribute("link", this.isLink() + "");

        //en caso propiedad de columna inputDate
        if (componentDate != null) {
            column.setAttribute("locale", this.getComponentDate().getLocale() + "");
            column.setAttribute("dateFormat", this.getComponentDate().getDateFormat() + "");
            column.setAttribute("timeZone", this.getComponentDate().getTimeZone() + "");
        }


        if (this.getComponents() != null && this.getComponents().size() > 0) {
            Element compo = new Element("components");
            for (ComponentTO cmp : components) {
                compo.getChildren().add(cmp.toXML());
            }

            column.getChildren().add(compo);
        }
        return column;
    }
}
