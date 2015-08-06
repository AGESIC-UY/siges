package com.sofis.business.ejbs;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.inject.Named;

/**
 *
 * @author Usuario
 */
@Named
@Stateful(name = "DatosUsuario")
@LocalBean
public class DatosUsuario implements DatosUsuarioRemote {

    @Resource
    private javax.ejb.SessionContext ctx;
    private String codigoUsuario = null;
    private String origen = null;

    public DatosUsuario() {
    }

    @Override
    public String getCodigoUsuario() {
        codigoUsuario = ctx.getCallerPrincipal().getName();
        return codigoUsuario;
    }

    @Override
    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    @Override
    public String getOrigen() {
        return origen;
    }

    @Override
    public void setOrigen(String origen) {
        this.origen = origen;
    }
}
