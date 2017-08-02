package com.sofis.web.delegates;

import com.sofis.business.ejbs.MediaProyectosBean;
import com.sofis.entities.data.MediaProyectos;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class MediaProyectosDelegate {

    @Inject
    private MediaProyectosBean mediaProyectosBean;

    public MediaProyectos guardar(MediaProyectos mediaProy, Integer usuId, Integer orgPk) {
        return mediaProyectosBean.guardarMP(mediaProy, usuId, orgPk);
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

    public List<MediaProyectos> cargarArchivos(List<MediaProyectos> listMP, Integer orgPk) {
        return mediaProyectosBean.cargarArchivos(listMP, orgPk);
    }

    public MediaProyectos cargarArchivo(MediaProyectos mediaProy, Integer orgPk) {
        return mediaProyectosBean.cargarArchivo(mediaProy, orgPk);
    }
    
    public MediaProyectos subirFoto(Integer proyPk, Integer usuId, byte[] mediaBytes, String comentario) {
        return mediaProyectosBean.subirFoto(proyPk, usuId, mediaBytes, comentario);
    }

    public MediaProyectos obtenerImgPrincipal(Integer proyPk) {
        return mediaProyectosBean.obtenerImgPrincipal(proyPk);
    }
    
    public boolean validarImageSize(byte[] mediaBytes, Integer orgPk) {
        return mediaProyectosBean.validarImageSize(mediaBytes, orgPk);
    }
}
