/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.utils;

import javax.faces.model.SelectItem;

/**
 *
 * @author bruno
 */
public class SofisComboItem extends SelectItem{

    public SofisComboItem(Object value, String label) {
        super(value, label);
    }
    
    @Override
    public int hashCode() {
        return (Integer) this.getValue();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    
}
