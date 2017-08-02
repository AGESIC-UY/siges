/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.sofisform.to;

import java.io.Serializable;
import java.util.HashMap;
import org.jdom.Document;
import org.jdom.Element;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class SofisFormSearchTO implements Serializable {

    //los formularios de busqueda
    HashMap<String, FormSearchTO> formsSearch;
    //los select items que debe de inicializar se carga en el metodo buildSofisFormSearch
    HashMap<String, SelectItemTO> selectItems;

    public HashMap<String, SelectItemTO> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(HashMap<String, SelectItemTO> selectItems) {
        this.selectItems = selectItems;
    }

    public HashMap<String, FormSearchTO> getFormsSearch() {
        return formsSearch;
    }

    public void setFormsSearch(HashMap<String, FormSearchTO> formsSearch) {
        this.formsSearch = formsSearch;
    }

    public void addFormSearch(FormSearchTO f) {
        if (formsSearch == null) {
            formsSearch = new HashMap<String, FormSearchTO>();
        }
        formsSearch.put(f.getKey(), f);
    }

    public void addSelectItem(SelectItemTO f) {
        if (selectItems == null) {
            selectItems = new HashMap<String, SelectItemTO>();
        }
        selectItems.put(f.getKey(), f);
    }

    public Document toXML() {
        Element sofisform = new Element("sofisform");
        Document xmld = new Document(sofisform);
        for (FormSearchTO entidad : formsSearch.values()) {
            Element elemnetForm = entidad.toXML();
            sofisform.getChildren().add(elemnetForm);
        }
        return xmld;
    }
}
