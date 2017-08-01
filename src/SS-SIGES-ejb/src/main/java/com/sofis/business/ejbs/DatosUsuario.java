package com.sofis.business.ejbs;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "DatosUsuario")
@LocalBean
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class DatosUsuario {

    private static Logger logger = Logger.getLogger(DatosUsuario.class.getName());

    @Resource
    private javax.ejb.SessionContext ctx;
    
    private String origen = null;
    private static final Object USUARIO_MUTEX = new Object();
    private static final Object ORIGEN_MUTEX = new Object();

    @Lock(LockType.READ)
    @AccessTimeout(value = 60, unit = TimeUnit.SECONDS) //BRUNO 11-04-17
    public String getCodigoUsuario() {
        synchronized (USUARIO_MUTEX) {
            if (ctx.getCallerPrincipal() != null) {
                return ctx.getCallerPrincipal().getName();
            }
            return null;
        }
    }

    @Lock(LockType.READ)
    @AccessTimeout(value = 60, unit = TimeUnit.SECONDS) //BRUNO 11-04-17
    public String getOrigen() {
        synchronized (ORIGEN_MUTEX) {
            return origen;
        }
    }

    @Lock(LockType.WRITE)
    @AccessTimeout(value = 60, unit = TimeUnit.SECONDS) //BRUNO 11-04-17
    public void setOrigen(String origen) {
        synchronized (ORIGEN_MUTEX) {
            this.origen = origen;
        }
    }
}
