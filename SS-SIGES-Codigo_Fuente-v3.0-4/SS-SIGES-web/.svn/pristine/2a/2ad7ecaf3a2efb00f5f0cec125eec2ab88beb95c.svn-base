package com.sofis.web.delegates;

import com.sofis.business.ejbs.DatosUsuarioRemote;
import com.sofis.web.utils.EJBUtils;

/**
 *
 * @author Usuario
 */
public class DatosUsuarioDelegate {

    private DatosUsuarioRemote dur;

    public DatosUsuarioDelegate() {
        dur = EJBUtils.getDatosUsuario();
    }

    public String getCodigoUsuario() {
        return dur.getCodigoUsuario();
    }

    public void setCodigoUsuario(String codigoUsuario) {
        dur.setCodigoUsuario(codigoUsuario);
    }

    public String getOrigen() {
        return dur.getOrigen();
    }

    public void setOrigen(String origen) {
        dur.setOrigen(origen);
    }
}
