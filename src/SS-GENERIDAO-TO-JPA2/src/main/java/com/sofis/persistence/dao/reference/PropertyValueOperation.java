/*
 *  Clase desarrollada por Sofis Solutions
 *  
 */

package com.sofis.persistence.dao.reference;

/**
 * Clase desarrollada por Sofis Solutions
 * @author Sofis Solutions
 */
public class PropertyValueOperation {

    String property;
    Object value;
    String operation;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    

}
