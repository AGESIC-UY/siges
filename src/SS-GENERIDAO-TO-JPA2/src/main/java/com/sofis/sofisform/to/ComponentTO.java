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
public abstract class ComponentTO implements Serializable{
    //la clave del componente
    private String key = "";
    //true implica que por defecto es visible
    private boolean defaultvisible = true;
    //true implica que por defecto se renderiza
    private boolean defaultrendered = true;
    //los paramentros globales que se pueden setear al componente
    //como el style, styleClass, depende del tipo de componente cuales son estos paramentros
    private HashMap<String,String> paramValues = new HashMap();
    //Es la expression para indicar si la accion puede ser visible o no
    private String visibleExpression;
    //Es la expression para indicar si la accion debe de ser renderizada o no
    private String renderedExpression;
    //Converter class para setear al componente
    private String converterClass = "";
    //los paramentros globales que se pueden setear al converter
    private HashMap<String,String> paramValuesConverter = new HashMap();


    public String getName(){
        return this.getClass().getSimpleName();
    }

    public HashMap<String, String> getParamValuesConverter() {
        return paramValuesConverter;
    }

    public void setParamValuesConverter(HashMap<String, String> paramValuesConverter) {
        this.paramValuesConverter = paramValuesConverter;
    }

    public String getConverterClass() {
        return converterClass;
    }

    public void setConverterClass(String converterClass) {
        this.converterClass = converterClass;
    }
    
    public String getRenderedExpression() {
        return renderedExpression;
    }

    public void setRenderedExpression(String renderedExpression) {
        this.renderedExpression = renderedExpression;
    }

    public String getVisibleExpression() {
        return visibleExpression;
    }

    public void setVisibleExpression(String visibleExpression) {
        this.visibleExpression = visibleExpression;
    }





    public HashMap<String, String> getParamValues() {
        return paramValues;
    }

    public void setParamValues(HashMap<String, String> paramValues) {
        this.paramValues = paramValues;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isDefaultrendered() {
        return defaultrendered;
    }

    public void setDefaultrendered(boolean defaultrendered) {
        this.defaultrendered = defaultrendered;
    }

    public boolean isDefaultvisible() {
        return defaultvisible;
    }

    public void setDefaultvisible(boolean defaultvisible) {
        this.defaultvisible = defaultvisible;
    }

    public abstract org.jdom.Element toXML();

    public Element toXMLMetadata(Element toReturn){
        toReturn.setAttribute("defaultrendered", this.isDefaultrendered() +"");
        toReturn.setAttribute("defaultvisible", this.isDefaultvisible() +"");
        toReturn.setAttribute("key", this.getKey() + "");
        
        
        toReturn.setAttribute("converterClass", this.getConverterClass());
        List<Element> paramValuesElements = this.paramValuesToXML();
        toReturn.getChildren().addAll(paramValuesElements);
        List<Element> paramValuesConverterElements = this.paramValuesConverterToXML();
        toReturn.getChildren().addAll(paramValuesConverterElements);
        return toReturn;
    }


    private List<Element> paramValuesToXML(){
        List<Element> paramValuesList = new ArrayList();
        if (this.getParamValues() != null && this.getParamValues().size() > 0){
            for (String keyParam : this.getParamValues().keySet()){
                String value = this.getParamValues().get(keyParam);
                 Element paramValueE = new Element("paramvalue");
                 paramValueE.setAttribute("param", keyParam);
                 paramValueE.setAttribute("value", value);
                 paramValuesList.add(paramValueE);
            }
        }
        return paramValuesList;
    }

    private List<Element> paramValuesConverterToXML(){
        List<Element> paramValuesList = new ArrayList();
        if (this.getParamValuesConverter() != null && this.getParamValuesConverter().size() > 0){
            for (String keyParam : this.getParamValuesConverter().keySet()){
                String value = this.getParamValuesConverter().get(keyParam);
                 Element paramValueE = new Element("paramvalueconverter");
                 paramValueE.setAttribute("param", keyParam);
                 paramValueE.setAttribute("value", value);
                 paramValuesList.add(paramValueE);
            }
        }
        return paramValuesList;
    }

}
