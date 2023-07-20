package com.sofis.web.delegates;

import com.sofis.business.ejbs.NotificacionInstanciaBean;
import com.sofis.entities.data.NotificacionInstancia;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class NotificacionInstanciaDelegate {

    @Inject
    NotificacionInstanciaBean notificacionInstanciaBean;

    public List<NotificacionInstancia> guardarNotificacionInstancia(List<NotificacionInstancia> notifInstList) {
        return notificacionInstanciaBean.guardarNotificacionInstancia(notifInstList);
    }

    public List<NotificacionInstancia> obtenerNotificacionInstPorProyId(Integer proyPk, Integer orgId) {
        return notificacionInstanciaBean.obtenerNotificacionInstPorProyId(proyPk, orgId);
    }

    public void guardarListaNotificacionInstancia(List<NotificacionInstancia> notifInstList) {
        notificacionInstanciaBean.guardarListaNotificacionInstancia(notifInstList);
    }
}
