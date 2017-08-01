package com.sofis.entities.tipos;

import java.io.Serializable;

/**
 * Objeto utilizado para cargar combos con valores sencillos.
 *
 * @author Usuario
 */
public class ComboItemTO implements Serializable {

    private Object itemObject;
    private String itemNombre;

    public ComboItemTO(Object itemObject, String itemNombre) {
        this.itemObject = itemObject;
        this.itemNombre = itemNombre;
    }
    
    public Object getItemObject() {
        return itemObject;
    }

    public void setItemObject(Object itemObject) {
        this.itemObject = itemObject;
    }

    public String getItemNombre() {
        return itemNombre;
    }

    public void setItemNombre(String itemNombre) {
        this.itemNombre = itemNombre;
    }

    @Override
    public int hashCode() {
        if (itemObject != null) {
            return itemObject.hashCode();
        }
        return 0;
    }
}
