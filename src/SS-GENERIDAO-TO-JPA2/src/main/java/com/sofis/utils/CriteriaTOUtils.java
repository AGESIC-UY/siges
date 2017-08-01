/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.utils;

import com.sofis.sofisform.to.AND_TO;
import com.sofis.sofisform.to.BindingComponentTO;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.sofisform.to.OR_TO;
import java.util.Date;
import java.util.HashMap;
import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Clase con utilidades para la creación del criteriaTO
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class CriteriaTOUtils {

    //auxiliar para que los nombres no se repitan en el bindingComponent
    private static long sequence = 0;

    /**
     * Crea un match criteria TO
     * @param type el tipo de criteria
     * @param property la propiedad a la cual se le va a aplicar
     * @param value el valor seteado para la proiedad
     * @return
     */
    public static MatchCriteriaTO createMatchCriteriaTO(MatchCriteriaTO.types type, String property, Object value) {
        MatchCriteriaTO matchCriteriaTO = new MatchCriteriaTO();
        matchCriteriaTO.setMatchType(type.getType());
        matchCriteriaTO.setValue(value);
        BindingComponentTO bindingComponentTO = new BindingComponentTO();
        bindingComponentTO.setProperty(property);
        Date d = new Date();
        bindingComponentTO.setKey("prop_" + property.replaceAll("\\.", "_") + "_" + sequence);
        matchCriteriaTO.setComponent(bindingComponentTO);

        if (sequence == Long.MAX_VALUE) {
            sequence = 0;
        }
        sequence++;

        return matchCriteriaTO;
    }

    /**
     * Crea un AND_TO a partir de la coleccion de criterias criteria1 and criteria2 and ..... and criteriaN
     * @param criterias
     * @return
     */
    public static AND_TO createANDTO(CriteriaTO... criterias) {

        if (criterias.length == 0 || criterias.length == 1) {
            return null;
        }
        AND_TO toReturn = new AND_TO(criterias[0], criterias[1]);
        int index = 0;
        for (CriteriaTO criteria : criterias) {
            if (index == 0 || index == 1) {
                index++;
                continue;
            }
            createANDTORecursive(toReturn, criteria);
            index++;
        }
        return toReturn;
    }

    private static void createANDTORecursive(AND_TO and_to, CriteriaTO criteria) {
        CriteriaTO currentCriteria1 = and_to.getCriteria1();
        if (currentCriteria1 instanceof AND_TO) {
            AND_TO currentCriteriaAnd1 = (AND_TO) currentCriteria1;
            createANDTORecursive(currentCriteriaAnd1, criteria);
        } else {
            AND_TO newAndTO = new AND_TO(criteria, currentCriteria1);
            and_to.setCriteria1(newAndTO);
            return;
        }
    }
    
     /**
     * Crea un AND_TO a partir de la coleccion de criterias criteria1 and criteria2 and ..... and criteriaN
     * @param criterias
     * @return
     */
    public static OR_TO createORTO(CriteriaTO... criterias) {

        if (criterias.length == 0 || criterias.length == 1) {
            return null;
        }
        OR_TO toReturn = new OR_TO(criterias[0], criterias[1]);
        int index = 0;
        for (CriteriaTO criteria : criterias) {
            if (index == 0 || index == 1) {
                index++;
                continue;
            }
            createORTORecursive(toReturn, criteria);
            index++;
        }
        return toReturn;
    }

    private static void createORTORecursive(OR_TO and_to, CriteriaTO criteria) {
        CriteriaTO currentCriteria1 = and_to.getCriteria1();
        if (currentCriteria1 instanceof OR_TO) {
            OR_TO currentCriteriaAnd1 = (OR_TO) currentCriteria1;
            createORTORecursive(currentCriteriaAnd1, criteria);
        } else {
            OR_TO newAndTO = new OR_TO(criteria, currentCriteria1);
            and_to.setCriteria1(newAndTO);
            return;
        }
    }

    /**
     * Testing
     * @param args
     */
    public static void main(String[] args) {
        MatchCriteriaTO matchCriteriaTOMes1 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "pedMes1", "");
        MatchCriteriaTO matchCriteriaTOMes2 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "pedMes2", "");
        MatchCriteriaTO matchCriteriaTOMes3 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "pedMes3", "");
        MatchCriteriaTO matchCriteriaTOMes4 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "pedMes4", "");
        MatchCriteriaTO matchCriteriaTOMes5 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "pedMes5", "");
        MatchCriteriaTO matchCriteriaTOMes6 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "pedMes6", "");
        MatchCriteriaTO matchCriteriaTOMes7 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "pedMes7", "");

        OR_TO andTO = CriteriaTOUtils.createORTO(matchCriteriaTOMes1, matchCriteriaTOMes2, matchCriteriaTOMes3, matchCriteriaTOMes4, matchCriteriaTOMes5, matchCriteriaTOMes6, matchCriteriaTOMes7);
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        Document a = new Document(andTO.toXML());
        String xmlString = outputter.outputString(a);
        System.out.println(xmlString);
    }
}
