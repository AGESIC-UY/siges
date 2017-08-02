/*
 *  Clase desarrollada por Sofis Solutions
 *  
 */

package com.sofis.sofisform.to;

import java.io.Serializable;

/**
 * Clase desarrollada por Sofis Solutions
 * @author Sofis Solutions
 */
public class SelectItemActionTO implements Serializable{
  public enum SelectItemActionType{
        DISPLAY("display"),
        UPDATE("update");

        String type;

        private SelectItemActionType(String type) {
            this.type = type;
        }
    }

  SelectItemActionType type;

    public SelectItemActionType getType() {
        return type;
    }

    public void setType(SelectItemActionType type) {
        this.type = type;
    }

  
}
