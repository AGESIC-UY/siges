package com.sofis.web.delegates;

import com.sofis.business.ejbs.MediaProyectosBean;
import com.sofis.entities.data.MediaProyectos;
import com.sofis.entities.data.SsUsuario;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class MediaProyectosDelegate {

    @Inject
    private MediaProyectosBean mediaProyectosBean;

    public MediaProyectos guardar(MediaProyectos mediaProy, SsUsuario usuario, Integer orgPk) {
        return mediaProyectosBean.guardarMP(mediaProy, usuario, orgPk);
    }

    public List<MediaProyectos> obtenerPorProyId(Integer proyPk) {
        return mediaProyectosBean.obtenerPorProyId(proyPk);
    }

    public MediaProyectos obtenerPorId(Integer mediaPk) {
        return mediaProyectosBean.obtenerPorId(mediaPk);
    }

    public void eliminar(Integer mediaPk) {
        mediaProyectosBean.eliminar(mediaPk);
    }

    public List<MediaProyectos> cargarArchivos(List<MediaProyectos> listMP) {
        return mediaProyectosBean.cargarArchivos(listMP);
    }

    public MediaProyectos cargarArchivo(MediaProyectos mediaProy) {
        return mediaProyectosBean.cargarArchivo(mediaProy);
    }
}
