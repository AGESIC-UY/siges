package com.sofis.business.interceptors;

import com.sofis.business.ejbs.DatosUsuario;
import com.sofis.business.interceptors.annotations.SofisLog;
import com.sofis.entities.constantes.ConstantesEstandares;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

@SofisLog
public class LoggedInterceptor implements Serializable {

    @Resource
    private javax.ejb.SessionContext ctx;
    @Inject
    private DatosUsuario datUsuario;

     private static final Logger logger =
            Logger.getLogger(ConstantesEstandares.LOGGER);
    
    public LoggedInterceptor() {
    }

    @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext)
            throws Exception {

        //logger.log(Level.FINEST,"Usuario: "+ctx.getCallerPrincipal().getName()+ " - Método: {0} EN LA CLASE: {1}", new Object[]{invocationContext.getMethod().getName(), invocationContext.getMethod().getDeclaringClass().getName()});
        //datUsuario.setCodigoUsuario(ctx.getCallerPrincipal().getName());
        return invocationContext.proceed();
    }
}
