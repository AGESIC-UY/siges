package com.sofis.web.delegates;

import com.sofis.business.ejbs.NotificacionDefectoBean;
import com.sofis.entities.data.NotificacionDefecto;
import com.sofis.entities.tipos.FiltroNotificacionDefectoTO;
import java.util.List;
import javax.inject.Inject;

public class NotificacionDefectoDelegate {

	@Inject
	private NotificacionDefectoBean notificacionDefectoBean;

	public void eliminar(Integer notifPk) {
		notificacionDefectoBean.eliminar(notifPk);
	}

	public List<NotificacionDefecto> buscar(FiltroNotificacionDefectoTO filtro, String elementoOrdenacion, int ascendente) {
		return notificacionDefectoBean.buscar(filtro, elementoOrdenacion, ascendente);
	}

	public NotificacionDefecto obtenerPorId(Integer notifPk) {
		return notificacionDefectoBean.obtenerPorId(notifPk);
	}

	public NotificacionDefecto guardar(NotificacionDefecto notificacion) {
		return notificacionDefectoBean.guardar(notificacion);
	}
}
