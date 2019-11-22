/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.converter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author sofis3
 */
public class SanitizerAceRichTextConverter implements Converter{
    private static String div = "<div id=\"sconnect-is-installed\" style=\"display: none;\">(.*)</div>";
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value){        
        if(value instanceof String && value != null){
            value = String.valueOf(value).replaceAll(div, "").trim();

        }
        if(value == null || value.equals("")){
            value= "<p></p>";
        }
        return (String) value;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {      
        if(value instanceof String && value != null){
            value = String.valueOf(value).replaceAll(div, "").trim();
        }
        if(value == null || value.equals("")){
            value= "<p></p>";
        }
        return value;
    }
    
}