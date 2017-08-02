/*
 *  Clase desarrollada por Sofis Solutions
 *  
 */

package com.sofis.sofisform.to;

import java.io.Serializable;
import org.jdom.Element;

/**
 *
 * Clase desarrollada por Sofis Solutions
 *
 *  <inputdate key =""
 *      entityvar="personaFisica"
 *      property="fechaInde"
 *      renderAsPopup = "true"
 *      dateFormat="dd/MM/yyyy"
 *      locale =""
 *      timeZone = "America/Montevideo"/>
 * @author Sofis Solutions
 */
public class InputDateTO extends BindingComponentTO implements Serializable{

    public enum DateType{
        DATE("date"),
        TIME("time"),
        BOTH("both");

        private String dateType;

        private DateType(String dateType) {
            this.dateType = dateType;
        }
    }

    private boolean renderAsPopup;
    private String dateFormat;
    private String timeZone;
    private String locale;
    private DateType type;
    private String pattern;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public DateType getType() {
        return type;
    }

    public void setType(DateType type) {
        this.type = type;
    }

   

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public boolean isRenderAsPopup() {
        return renderAsPopup;
    }

    public void setRenderAsPopup(boolean renderAsPopup) {
        this.renderAsPopup = renderAsPopup;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public Element toXML() {
        Element toReturn = new Element("inputdate");
        toReturn = this.toXMLMetadata(toReturn);
        return toReturn;
    }

    @Override
    public Element toXMLMetadata(Element toReturn) {
        toReturn =  super.toXMLMetadata(toReturn);
        toReturn.setAttribute("renderAsPopup", this.isRenderAsPopup() + "");
        if (this.getDateFormat() == null){
            toReturn.setAttribute("dateFormat", "");
        }else{
            toReturn.setAttribute("dateFormat", this.getDateFormat());
        }

        if (this.getLocale() == null){
            toReturn.setAttribute("locale", "");
        }else{
            toReturn.setAttribute("locale", this.getLocale());
        }
        if (this.getTimeZone() == null){
            toReturn.setAttribute("timeZone", "");
        }else{
            toReturn.setAttribute("timeZone", this.getTimeZone());
        }
        if (this.getPattern() == null){
            toReturn.setAttribute("pattern","");
        }else{
            toReturn.setAttribute("pattern", this.getPattern());
        }
        if (this.getDateFormat()== null){
            toReturn.setAttribute("dateFormat", "");
        }else{
            toReturn.setAttribute("dateFormat", this.getDateFormat());
        }
        

        return toReturn;
    }





}
