package com.sofis.web.delegates;

import com.sofis.business.ejbs.NoticiaBean;
import com.sofis.entities.data.Noticia;
import com.sofis.exceptions.GeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class NoticiaDelegate {

    @Inject
    NoticiaBean ncmBean;

    public Noticia guardar(Noticia tdp) throws GeneralException {
        return ncmBean.guardar(tdp);
    }

    public Noticia obtenerNoticiaPorId(Integer id) throws GeneralException {
        return ncmBean.obtenerNoticiaPorId(id);
    }

    public Noticia obtenerNoticiaPorCodigo(String codigo) throws GeneralException {
        return ncmBean.obtenerNoticiaPorCodigo(codigo);
    }

    public List<Noticia> obtenerTodos() throws GeneralException {
        return ncmBean.obtenerTodos();
    }

    public List<Noticia> obtenerNoticiasVigentes() throws GeneralException {
        return ncmBean.obtenerNoticiasVigentes();
    }

    public List<Noticia> obtenerPorCriteria(CriteriaTO cto, String[] orderBy, boolean[] ascending, Long startPosition, Long cantidad) throws GeneralException {
        return ncmBean.obtenerPorCriteria(cto, orderBy, ascending, startPosition, cantidad);
    }
}
