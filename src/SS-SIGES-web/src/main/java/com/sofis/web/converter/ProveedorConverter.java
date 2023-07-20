/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.converter;

import com.sofis.entities.data.OrganiIntProve;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.delegates.OrganiIntProveDelegate;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Usuario
 */
public class ProveedorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringsUtils.isEmpty(value)) {
            return null;
        }
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        Integer valueInt = null;
        if (value instanceof Integer) {
            valueInt = (Integer) value;
        } else if (value instanceof String) {
            System.out.println("intentando convertir " + value);
            valueInt = Integer.valueOf((String) value);
        }
        
        if (valueInt != null) {
            OrganiIntProveDelegate organiIntProveDelegate = new OrganiIntProveDelegate();
            OrganiIntProve u = organiIntProveDelegate.obtenerOrganiIntProvePorId((Integer) value);
            if (u != null) {
                return u.getOrgaNombre();
            }
        }
        return "";

    }
}
