/*
 *  Clase desarrollada por Sofis Solutions
 *  
 */
package com.sofis.sofisform.to;


import java.io.Serializable;
import org.jdom.Element;

/**


 * Clase desarrollada por Sofis Solutions
 * @author Sofis Solutions
 */
public class SelectItemTO extends BindingComponentTO implements Serializable{

    public enum CardinalType {

        SIMPLE("simple"),
        MULTIPLE("multiple");
        private String type;

        private CardinalType(String type) {
            this.type = type;
        }
    }

    public enum SelectItemType {

        onecheckbox("onecheckbox"),
        manycheckbox("manycheckbox"),
        onemenu("onemenu"),
        manymenu("manymenu"),
        oneradio("oneradio"),
        onelistbox("onelistbox"),
        manycombomenu("manycombomenu"),
        manylistboxmenu("manylistboxmenu");
        private String type;

        private SelectItemType(String type) {
            this.type = type;
        }
    }
    /**
     * @deprecated
     */
     CardinalType cardinal;

    //el tipo de selectItem
    SelectItemType type;

    //el id y el label de los items que se ponene en los combos o en los manymenu
    String itemPropertyId;
    String itemPropertyLabel;

    //las propiedades para poner el valor vacion, generalmente para los combos
    String emptyKey;
    String emptyValue;

    //las propiedades para el SelecItem de tipo ManyListBoxMenu o ManyComboMenu
    String manymenuAddLabelKey;
    String manymenuDeleteLabelKey;
    String manymenuAddImg;
    String manymenuDeleteImg;
    String manymenuCreatorBean = "";
    String manymenuCreatorMethod = "";

    //en caso de querer cargar los valores desde el bean y no desde la persistencia.
    private String selectItemBean;
    private String selectItemMethod;


    //la propiedad por la cual se quiere ordenar y si es una ordenacion
    //ascendente o descendente
    String orderBy;
    boolean asc;

    //el criterio para obtener los elementos del selectItem
    CriteriaTO criteria;
    //El style que se le puede setear en un manycombomenu o manylistboxmenu al combo o listbox donde se seleccionan los componentes
    String  selectorStyle = "";

    public String getSelectorStyle() {
        return selectorStyle;
    }

    public void setSelectorStyle(String selectorStyle) {
        this.selectorStyle = selectorStyle;
    }


    public String getManymenuCreatorBean() {
        return manymenuCreatorBean;
    }

    public void setManymenuCreatorBean(String manymenuCreatorBean) {
        this.manymenuCreatorBean = manymenuCreatorBean;
    }

    public String getManymenuCreatorMethod() {
        return manymenuCreatorMethod;
    }

    public void setManymenuCreatorMethod(String manymenuCreatorMethod) {
        this.manymenuCreatorMethod = manymenuCreatorMethod;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getManymenuAddImg() {
        return manymenuAddImg;
    }

    public void setManymenuAddImg(String manymenuAddImg) {
        this.manymenuAddImg = manymenuAddImg;
    }

    public String getManymenuDeleteImg() {
        return manymenuDeleteImg;
    }

    public void setManymenuDeleteImg(String manymenuDeleteImg) {
        this.manymenuDeleteImg = manymenuDeleteImg;
    }

    public String getManymenuAddLabelKey() {
        return manymenuAddLabelKey;
    }

    public void setManymenuAddLabelKey(String manymenuAddLabelKey) {
        this.manymenuAddLabelKey = manymenuAddLabelKey;
    }

    public String getManymenuDeleteLabelKey() {
        return manymenuDeleteLabelKey;
    }

    public void setManymenuDeleteLabelKey(String manymenuDeleteLabelKey) {
        this.manymenuDeleteLabelKey = manymenuDeleteLabelKey;
    }

    public String getSelectItemBean() {
        return selectItemBean;
    }

    public void setSelectItemBean(String selectItemBean) {
        this.selectItemBean = selectItemBean;
    }

    public String getSelectItemMethod() {
        return selectItemMethod;
    }

    public void setSelectItemMethod(String selectItemMethod) {
        this.selectItemMethod = selectItemMethod;
    }



    public CriteriaTO getCriteria() {
        return criteria;
    }

    public void setCriteria(CriteriaTO criteria) {
        this.criteria = criteria;
    }

    public CardinalType getCardinal() {
        return cardinal;
    }

    public void setCardinal(CardinalType cardinal) {
        this.cardinal = cardinal;
    }

    public String getEmptyKey() {
        return emptyKey;
    }

    public void setEmptyKey(String emptyKey) {
        this.emptyKey = emptyKey;
    }

    public String getEmptyValue() {
        return emptyValue;
    }

    public void setEmptyValue(String emptyValue) {
        this.emptyValue = emptyValue;
    }

    public SelectItemType getType() {
        return type;
    }

    public void setType(SelectItemType type) {
        this.type = type;
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

      @Override
    public Element toXML() {
        Element toReturn = new Element("selectitem");
        toReturn = super.toXMLMetadata(toReturn);

        if (this.getType() == null) {
            toReturn.setAttribute("type", "");
        } else {
            toReturn.setAttribute("type", this.getType().name());
        }
        if (this.getItemPropertyId() == null) {
            toReturn.setAttribute("itemPropertyId", "");
        } else {
            toReturn.setAttribute("itemPropertyId", this.getItemPropertyId());
        }
        if (this.getItemPropertyLabel() == null) {
            toReturn.setAttribute("itemPropertyLabel", "");
        } else {
            toReturn.setAttribute("itemPropertyLabel", this.getItemPropertyLabel());
        }

        if (this.getEmptyKey() == null) {
            toReturn.setAttribute("emptyKey", "");
        } else {
            toReturn.setAttribute("emptyKey", this.getEmptyKey());
        }
        if (this.getEmptyValue() == null) {
            toReturn.setAttribute("emptyValue", "");
        } else {
            toReturn.setAttribute("emptyValue", this.getEmptyValue());
        }
        if (this.getOrderBy() == null) {
            toReturn.setAttribute("orderBy", "");
        } else {
            toReturn.setAttribute("orderBy", this.getOrderBy());
        }
        toReturn.setAttribute("asc", this.isAsc() + "");
        if (this.getManymenuAddLabelKey() == null) {
            toReturn.setAttribute("manymenuAddLabelKey","");
        } else {
            toReturn.setAttribute("manymenuAddLabelKey", this.getManymenuAddLabelKey());
        }
        if (this.getManymenuDeleteLabelKey() == null) {
            toReturn.setAttribute("manymenuDeleteLabelKey", "");
        } else {
            toReturn.setAttribute("manymenuDeleteLabelKey", this.getManymenuDeleteLabelKey());
        }

        if (this.getManymenuAddImg() == null) {
            toReturn.setAttribute("manymenuAddImg", "");
        } else {
            toReturn.setAttribute("manymenuAddImg", this.getManymenuAddImg());
        }
        if (this.getManymenuDeleteImg() == null) {
            toReturn.setAttribute("manymenuDeleteImg", "");
        } else {
            toReturn.setAttribute("manymenuDeleteImg", this.getManymenuDeleteImg());
        }

        if (this.getManymenuCreatorBean() == null) {
            toReturn.setAttribute("manymenuCreatorBean","");
        } else {
            toReturn.setAttribute("manymenuCreatorBean", this.getManymenuCreatorBean());
        }

        if (this.getManymenuCreatorMethod() == null) {
            toReturn.setAttribute("manymenuCreatorMethod", "");
        } else {
            toReturn.setAttribute("manymenuCreatorMethod", this.getManymenuCreatorMethod());
        }
        
        if (this.getSelectItemBean() == null) {
            toReturn.setAttribute("selectItemBean", "");
        } else {
            toReturn.setAttribute("selectItemBean", this.getSelectItemBean());
        }

        if (this.getSelectItemMethod() == null) {
            toReturn.setAttribute("selectItemMethod", "");
        } else {
            toReturn.setAttribute("selectItemMethod", this.getSelectItemMethod());
        }
         if (this.getSelectorStyle() == null) {
            toReturn.setAttribute("selectorStyle", "");
        } else {
            toReturn.setAttribute("selectorStyle", this.getSelectorStyle());
        }

        if (this.getCriteria() != null) {
            Element xmlCriteria = this.getCriteria().toXML();
            toReturn.getChildren().add(xmlCriteria);
        }


        return toReturn;
    }


}
