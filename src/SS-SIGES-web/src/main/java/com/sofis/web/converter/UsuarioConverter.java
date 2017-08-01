/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.converter;

import com.sofis.entities.data.SsUsuario;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.delegates.SsUsuarioDelegate;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Usuario
 */
public class UsuarioConverter implements Converter {

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
            valueInt = Integer.valueOf((String) value);
        }
        
        if (valueInt != null) {
            SsUsuarioDelegate usuarioDelegate = new SsUsuarioDelegate();
            SsUsuario u = usuarioDelegate.obtenerSsUsuarioPorId((Integer) value);
            if (u != null) {
                return u.getUsuNombreApellido();
            }
        }
        return "";

    }
}
