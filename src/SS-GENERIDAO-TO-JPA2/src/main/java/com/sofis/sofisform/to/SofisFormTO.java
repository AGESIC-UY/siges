/*
 *  Clase desarrollada por Sofis Solutions
 *  
 */

package com.sofis.sofisform.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Clase desarrollada por Sofis Solutions
 * @author Sofis Solutions
 */
public class SofisFormTO implements Serializable {

    //los formularios
    HashMap<String,FormTO> forms;
    //los select items que debe de inicializar
    HashMap<String,SelectItemTO> selectItems;

    public HashMap<String, SelectItemTO> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(HashMap<String, SelectItemTO> selectItems) {
        this.selectItems = selectItems;
    }

    public HashMap<String,FormTO> getForms() {
        return forms;
    }

    public void setForms(HashMap<String,FormTO> forms) {
        this.forms = forms;
    }

    public void addForm(FormTO f){
        if (forms == null){
            forms = new HashMap<String, FormTO>();
        }
        forms.put(f.getKey(),f);
    }

    public void addSelectItem(SelectItemTO f){
        if (selectItems == null){
            selectItems = new HashMap<String, SelectItemTO>();
        }
        selectItems.put(f.getKey(),f);
    }

    /**
     * Busca el formulario que tiene el entityVar de deste formulario
     * @param entityVar
     * @return
     */
    public FormTO getFormByEntityVar(String entityVar){
        for (FormTO form: this.getForms().values()){
            if (form.getModel().getEntities().containsKey(entityVar)){
                return form;
            }
        }
        return null;
    }

}
