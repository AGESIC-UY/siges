package com.sofis.web.delegates;

import com.sofis.business.ejbs.InteresadosBean;
import com.sofis.entities.data.Interesados;
import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class InteresadosDelegate {

    @Inject
    private InteresadosBean interesadosBean;

    public Interesados guardar(Interesados interesados) throws GeneralException {
        return interesadosBean.guardar(interesados);
    }

    public Interesados obtenerInteresadosPorId(Integer id) throws GeneralException {
        return interesadosBean.obtenerInteresadosPorId(id);
    }

    public List<Interesados> obtenerIntersadosResumen(Integer proyPk, Integer tipoFicha, int size) {
        return interesadosBean.obtenerIntersadosResumen(proyPk, tipoFicha, size);
    }

    public List<Interesados> obtenerIntersadosPorFichaPk(Integer fichaPk, Integer tipoFicha) {
        return interesadosBean.obtenerIntersadosPorFichaPk(fichaPk, tipoFicha);
    }

    public Object delete(Integer intPk, Integer fichaPk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) throws GeneralException {
        return interesadosBean.eliminar(intPk, fichaPk, tipoFicha, usuario, orgPk);
    }

    public Object guardarInteresados(Interesados interesado, Integer fichaPk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) throws GeneralException {
        return interesadosBean.guardarInteresados(interesado, fichaPk, tipoFicha, usuario, orgPk);
    }
}
