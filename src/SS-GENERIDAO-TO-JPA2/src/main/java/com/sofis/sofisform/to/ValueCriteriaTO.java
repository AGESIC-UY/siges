/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sofis.sofisform.to;

import java.util.HashMap;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public abstract class ValueCriteriaTO extends CriteriaTO {

    private Object value;
    private String matchCriteriaSelector;
    private BindingComponentTO component;
    

     public BindingComponentTO getComponent() {
        return component;
    }

    public void setComponent(BindingComponentTO component) {
        this.component = component;
    }

    public String getMatchCriteriaSelector() {
        return matchCriteriaSelector;
    }

    public void setMatchCriteriaSelector(String matchCriteriaSelector) {
        this.matchCriteriaSelector = matchCriteriaSelector;
    }
    


    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

     @Override
    public HashMap<String, ValueCriteriaTO> getValueCriteriaTO() {
        HashMap result = new HashMap();
        result.put(component.getKey(),this);
        return result;
    }
}
