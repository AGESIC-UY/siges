/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.sofisform.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jdom.Element;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class OR_TO extends ContainerCriteriaTO implements Serializable{

    private CriteriaTO criteria1;
    private CriteriaTO criteria2;

    public OR_TO() {
        this.criteria1 = null;
        this.criteria2 = null;
    }

    public OR_TO(CriteriaTO criteria1, CriteriaTO criteria2) {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
    }

    public CriteriaTO getCriteria1() {
        return criteria1;
    }

    public void setCriteria1(CriteriaTO criteria1) {
        this.criteria1 = criteria1;
    }

    public CriteriaTO getCriteria2() {
        return criteria2;
    }

    public void setCriteria2(CriteriaTO criteria2) {
        this.criteria2 = criteria2;
    }

    @Override
    public List<CriteriaTO> getCriterias() {
        List<CriteriaTO> toReturn = new ArrayList();
        toReturn.add(criteria1);
        toReturn.add(criteria2);
        return toReturn;

    }

    @Override
    public String getStringQuery(HashMap col, HashMap leftJoin) {
        String criteria1S = criteria1.getStringQuery(col, leftJoin);
        String criteria2S = criteria2.getStringQuery(col, leftJoin);
        if (criteria1S != null && criteria2S != null) {
            return "( (" + criteria1S + ") or (" + criteria2S + ") )";
        }

        if (criteria1S != null && criteria2S == null) {
            return "( (" + criteria1S + "))";
        }

        if (criteria2S != null && criteria1S == null) {
            return "( (" + criteria2S + "))";
        }

        return null;
    }

    @Override
    public HashMap<String, ValueCriteriaTO> getValueCriteriaTO() {
        HashMap result = new HashMap();
        result.putAll(criteria1.getValueCriteriaTO());
        result.putAll(criteria2.getValueCriteriaTO());
        return result;
    }

    @Override
    public Element toXML() {
        Element toReturn = new Element("OR");
        Element criteria1EE = this.criteria1.toXML();
        Element criteria2EE = this.criteria2.toXML();
        Element criteria1E = new Element("criteria1");
        Element criteria2E = new Element("criteria2");
        criteria1E.getChildren().add(criteria1EE);
        criteria2E.getChildren().add(criteria2EE);
        toReturn.getChildren().add(criteria1E);
        toReturn.getChildren().add(criteria2E);

        return toReturn;
    }
}
