package com.sofis.web.delegates;

import com.sofis.business.ejbs.NotificacionBean;
import com.sofis.entities.data.Notificacion;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class NotificacionDelegate {

    @Inject
    NotificacionBean notificacionBean;

    public void eliminarNotificacion(Integer notifPk) {
        notificacionBean.eliminarNotificacion(notifPk);
    }

    public List<Notificacion> busquedaNotifFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        return notificacionBean.busquedaNotifFiltro(orgPk, mapFiltro, elementoOrdenacion, ascendente);
    }

    public Notificacion obtenerNotifPorPk(Integer notifPk) {
        return notificacionBean.obtenerNotifPorPk(notifPk);
    }

    public Notificacion guardarNotificacion(Notificacion notifEnEdicion) {
        return notificacionBean.guardar(notifEnEdicion);
    }
}
