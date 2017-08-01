/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sofis.sofisform.validation;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public interface SofisFormValidationI {
    
    
    class ValidationObject{
        String summary;
        String detail;

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
        
        
    }

    public List<ValidationObject> validate(HashMap objetosCollection,Object...objects);

}
