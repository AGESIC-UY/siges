/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sofis.sofisform.to;

import java.io.Serializable;



/**
 *
 * @author Sofis
 */
public class AutoCompleteTO extends BindingComponentTO implements Serializable{

    private String maxRows;
    String itemPropertyId;
    String itemPropertyLabel;
    //si esta presneta esta propiedad la busqueda la realiza por este campo, ademas de los criterias
    String itemPropertySearchLabel;
    CriteriaTO criteria;
    String searchStyle;

    public String getItemPropertySearchLabel() {
        return itemPropertySearchLabel;
    }

    public void setItemPropertySearchLabel(String itemPropertySearchLabel) {
        this.itemPropertySearchLabel = itemPropertySearchLabel;
    }



    public String getSearchStyle() {
        return searchStyle;
    }

    public void setSearchStyle(String searchStyle) {
        this.searchStyle = searchStyle;
    }


    public CriteriaTO getCriteria() {
        return criteria;
    }

    public void setCriteria(CriteriaTO criteria) {
        this.criteria = criteria;
    }
    

    public String getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(String maxRows) {
        this.maxRows = maxRows;
    }

    public String getItemPropertyId() {
        return itemPropertyId;
    }

    public void setItemPropertyId(String itemPropertyId) {
        this.itemPropertyId = itemPropertyId;
    }

    public String getItemPropertyLabel() {
        return itemPropertyLabel;
    }

    public void setItemPropertyLabel(String itemPropertyLabel) {
        this.itemPropertyLabel = itemPropertyLabel;
    }

    

}
