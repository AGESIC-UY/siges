/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.sofisform.to;

import com.sofis.utils.StringHelper;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import org.jdom.Element;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class MatchCriteriaTO extends ValueCriteriaTO implements Serializable{

    public enum types {

        EQUALS("MatchCriteria.EQUALS"),
        START_WITH("MatchCriteria.START_WITH"),
        END_WITH("MatchCriteria.END_WITH"),
        CONTAINS("MatchCriteria.CONTAINS"),
        NOT_EQUALS("MatchCriteria.NOT_EQUALS"),
        LESS("MatchCriteria.LESS"),
        GREATER("MatchCriteria.GREATER"),
        GREATEREQUAL("MatchCriteria.GREATEREQUAL"),
        LESSEQUAL("MatchCriteria.LESSEQUAL"),
        SELECTOR("MatchCriteria.SELECTOR"),
        EMPTY("MatchCriteria.EMPTY"),
        NOT_EMPTY("MatchCriteria.NOT_EMPTY"),
        NULL("MatchCriteria.NULL"),
        NOT_MEMBER_OF("MatchCriteria.NOT_MEMBER_OF"),
        MEMBER_OF("MatchCriteria.MEMBER_OF"),
        NOT_NULL("MatchCriteria.NOT_NULL"),
        SUBQUERY("MatchCriteria.SUBQUERY");
        private String type;

        private types(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
    private String matchType = "";
    //la lista de selector List para el caso de tipo SELECTOR
    private String selectorList = "";

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    /* Retorna true si propiedadA es una coleccion, siendo property de la forma propeidad1...propiedadN....propiedadA.propiedadB
     * @param clase la clase que tiene propiedadA
     * @param property propeidad1...propiedadN....propiedadA.propiedadB
     * @return
     */
    private HashMap isCollection(String property) {
        //la parte que es la coleccion
        //ejemplo personaId.identificacionColecion
        String collectionProp = new String();
        //la parte despues de la coleccion
        //ejemplo pais.nombre
        String collectionPropProp = new String();
        int d = property.indexOf("Collection.");
        if (d >= 0) {
            collectionProp = property.substring(0, d) + "Collection";
            collectionPropProp = property.substring(d + 11, property.length());
            HashMap r = new HashMap();
            r.put(collectionProp, collectionPropProp);
            return r;
        }
        d = property.indexOf("List.");
        if (d >= 0) {
            collectionProp = property.substring(0, d) + "List";
            collectionPropProp = property.substring(d + 5, property.length());
            HashMap r = new HashMap();
            r.put(collectionProp, collectionPropProp);
            return r;
        }

        d = property.indexOf("Set.");
        if (d >= 0) {
            collectionProp = property.substring(0, d) + "Set";
            collectionPropProp = property.substring(d + 4, property.length());
            HashMap r = new HashMap();
            r.put(collectionProp, collectionPropProp);
            return r;
        }

        return null;
    }

    public String getProperty() {
        if (this.getComponent() != null) {
            return this.getComponent().getProperty();
        } else {
            return null;
        }

    }

    @Override
    public String getStringQuery(HashMap col, HashMap leftJoin) {

        String index = (String) col.get(this);
        //nos quedamos con el indice por defecto en caso de que el mismo sea null y no se tenga un left join generado
        String property = this.getProperty();
        if ((this.getComponent()) instanceof SelectItemTO) {
            if (((SelectItemTO) this.getComponent()).getItemPropertyId() != null && !((SelectItemTO) this.getComponent()).getItemPropertyId().equalsIgnoreCase("")) {
                property = property + "." + ((SelectItemTO) this.getComponent()).getItemPropertyId();
            }
        }
        if (index == null) {
            if (property.lastIndexOf('.') >= 0) {
                String toCheck = property.substring(0, property.lastIndexOf('.'));
                Integer leftJoinIndex = (Integer) leftJoin.get(toCheck);
                if (leftJoinIndex != null) {
                    index = "lp" + leftJoinIndex;
                    property = property.substring(property.lastIndexOf('.') + 1, property.length());
                }
            }
            if (index == null) {
                index = (String) col.get(null);
            }
        }
        if (this.getValue() != null && !this.getValue().toString().equalsIgnoreCase("")) {
            Object value = this.getValue();
          
            HashMap iscol = isCollection(property);
            if (iscol != null) {
                property = (String) iscol.values().iterator().next();
            }


            String toReturn = "";
            String dataBase = null;
            try {
                ResourceBundle bundle = ResourceBundle.getBundle("sofisform");
                dataBase = bundle.getString("dataBase");
            } catch (Exception e) {
                dataBase = "";
            }


            if ((value instanceof Date) && matchType.equals(MatchCriteriaTO.types.EQUALS.CONTAINS.type)) {
                //dependiendo del motor de base de datos es la function a utilizar
                if (dataBase != null) {
                    if (dataBase.equalsIgnoreCase("MYSQL")) {
                        toReturn = "( " + "date(" + index + "." + property + ") " + matchTypeToQuery();
                    }
                    if (dataBase.equalsIgnoreCase("ORACLE")) {
                        toReturn = "( " + "trunc(" + index + "." + property + ") = ";
                    }
                    if (dataBase.equalsIgnoreCase("POSTGRESQL")) {
                        toReturn = "( " + "date_trunc('hour' ," + index + "." + property + ") " + matchTypeToQuery();
                    }
                    if (dataBase.equalsIgnoreCase("SQLSERVER")) {

                        toReturn = "( " + "dateadd(dd ,0,datediff(dd,0," + index + "." + property + ")) = ";
                    }

                } else {
                    toReturn = "( " + " (" + index + "." + property + ") " + matchTypeToQuery();
                }

            } else {
                if (value instanceof String && dataBase.equalsIgnoreCase("POSTGRESQL")) {
                    String functionSinAcento = "";
                    try {
                        ResourceBundle bundle = ResourceBundle.getBundle("sofisform");
                        functionSinAcento = bundle.getString("postgresSinAcentoFunction");
                    } catch (Exception e) {
                    }
                    if (!functionSinAcento.equalsIgnoreCase("")) {

                        String valueS = (String) value;
                        valueS = valueS.toUpperCase();
                        valueS = StringHelper.replaceTildes(valueS);
                        this.setValue(valueS);
                        toReturn = "( " + functionSinAcento + "(" + index + "." + property + ") " + matchTypeToQuery();
                    } else {
                        toReturn = "( " + index + "." + property + " " + matchTypeToQuery();
                    }

                } else {
                    toReturn = "( " + index + "." + property + " " + matchTypeToQuery();
                }
            }

            if (matchType.equals(MatchCriteriaTO.types.EMPTY.type) || matchType.equals(MatchCriteriaTO.types.NOT_EMPTY.type) || matchType.equals(MatchCriteriaTO.types.NULL.type) || matchType.equals(MatchCriteriaTO.types.NOT_NULL.type)) {
                //si es EMPTY o NOT EMPTY o NULL o NOT NULL no se debe de agregar el parametro es decir el EMPTY :parametro
                toReturn = toReturn + ")";
            } else if (matchType.equals(MatchCriteriaTO.types.MEMBER_OF.type) || matchType.equals(MatchCriteriaTO.types.NOT_MEMBER_OF.type)){
                toReturn = "( :" + this.getComponent().getKey() +  matchTypeToQuery() + index + "." + property + " " +  ")";
            }else if (matchType.equals(MatchCriteriaTO.types.SUBQUERY.type)){
                toReturn = toReturn + " " + super.getValue().toString() + ")";
            }else{
                toReturn = toReturn + ":" + this.getComponent().getKey() + ")";
            }

            return toReturn;

        } else {
            return null;
        }

    }

    private String matchTypeToQuery() {

        String matchType = "";
        if (this.matchType.equalsIgnoreCase(MatchCriteriaTO.types.SELECTOR.type)) {
            matchType = this.getMatchCriteriaSelector();
        } else {
            matchType = this.matchType;
        }


        if (matchType.equals(MatchCriteriaTO.types.EQUALS.type)) {
            return " = ";
        }
        if (matchType.equals(MatchCriteriaTO.types.NOT_EQUALS.type)) {
            return " <> ";
        }
        if (matchType.equals(MatchCriteriaTO.types.CONTAINS.type)) {
            return " like ";
        }
        if (matchType.equals(MatchCriteriaTO.types.START_WITH.type)) {
            return " like ";
        }
        if (matchType.equals(MatchCriteriaTO.types.END_WITH.type)) {
            return " like ";
        }
        if (matchType.equals(MatchCriteriaTO.types.LESS.type)) {
            return " < ";
        }

        if (matchType.equals(MatchCriteriaTO.types.LESSEQUAL.type)) {
            return " <= ";
        }
        if (matchType.equals(MatchCriteriaTO.types.GREATER.type)) {
            return " > ";
        }
        if (matchType.equals(MatchCriteriaTO.types.GREATEREQUAL.type)) {
            return " >= ";
        }
        if (matchType.equals(MatchCriteriaTO.types.EMPTY.type)) {
            return " IS EMPTY ";
        }
        if (matchType.equals(MatchCriteriaTO.types.NOT_EMPTY.type)) {
            return " IS NOT EMPTY ";
        }
        if (matchType.equals(MatchCriteriaTO.types.NULL.type)) {
            return " IS null ";
        }
        if (matchType.equals(MatchCriteriaTO.types.NOT_NULL.type)) {
            return " IS NOT null ";
        }
        if (matchType.equals(MatchCriteriaTO.types.MEMBER_OF.type)) {
            return " MEMBER OF ";
        }
        if (matchType.equals(MatchCriteriaTO.types.NOT_MEMBER_OF.type)) {
            return "  NOT MEMBER OF ";
        }
        if (matchType.equals(MatchCriteriaTO.types.SUBQUERY.type)) {
            return " ";
        }
        

        return null;
    }

    public String getSelectorList() {
        return selectorList;
    }

    public void setSelectorList(String selectorList) {
        this.selectorList = selectorList;
    }

    @Override
    public Element toXML() {
        Element toReturn = new Element("matchCriteria");
        toReturn.setAttribute("matchType", this.getMatchType() + "");

        if (this.getComponent() != null && (this.getComponent() instanceof MatchCriteriaBindingComponentTO)) {
            if (this.getComponent().getProperty() != null) {
                toReturn.setAttribute("property", this.getComponent().getProperty() + "");
            } else {
                toReturn.setAttribute("property", "");
            }
            if (this.getComponent().getPropertyClassWeb() != null) {
                toReturn.setAttribute("propertyClassWeb", this.getComponent().getPropertyClassWeb() + "");
            } else {
                toReturn.setAttribute("propertyClassWeb", "");
            }
            if (this.getComponent().getPropertyClass() != null) {
                toReturn.setAttribute("propertyClass", this.getComponent().getPropertyClass() + "");
            } else {
                toReturn.setAttribute("propertyClass", "");
            }
            if (this.getComponent().getKey() != null) {
                toReturn.setAttribute("key", this.getComponent().getKey() + "");
            } else {
                toReturn.setAttribute("key", "");
            }
        } else if (this.getComponent() != null) {
            toReturn.setAttribute("componentKey", this.getComponent().getKey() + "");
        }

        if (this.getValue() != null) {
            toReturn.setAttribute("value", this.getValue() + "");
        }
        if (this.getSelectorList() != null) {
            toReturn.setAttribute("selectorList", this.getSelectorList() + "");
        }


        return toReturn;

    }
}
