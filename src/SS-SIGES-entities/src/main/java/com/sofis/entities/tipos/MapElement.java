/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.entities.tipos;

import com.sofis.entities.data.Configuracion;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class MapElement {

    @XmlElement
    private String key;

    @XmlElement
    private Configuracion value;

    public MapElement() {
    }

    public MapElement(String key, Configuracion value) {
	this.key = key;
	this.value = value;
    }

    /**
     * @return the key
     */
    public String getKey() {
	return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
	this.key = key;
    }

    /**
     * @return the value
     */
    public Configuracion getValue() {
	return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Configuracion value) {
	this.value = value;
    }
}
