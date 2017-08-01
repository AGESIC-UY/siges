package com.sofis.business.ejbs;

import javax.ejb.Remote;

/**
 *
 * @author Usuario
 */
@Remote
public interface DatosUsuarioRemote {

    public String getCodigoUsuario();

    public void setCodigoUsuario(String codigoUsuario);

    public String getOrigen();

    public void setOrigen(String origen);

}
