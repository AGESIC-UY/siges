package com.sofis.web.delegates;

import com.sofis.business.ejbs.CategoriaProyectosBean;
import com.sofis.entities.data.CategoriaProyectos;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class CategoriaProyectosDelegate {
    
    @Inject
    private CategoriaProyectosBean categoriaProyectosBean;

    public List<CategoriaProyectos> obtenerPrimarias(Integer orgPk) {
        return categoriaProyectosBean.obtenerPrimarias(orgPk);
    }
    
    public List<CategoriaProyectos> obtenerSecundarias(Integer orgPk) {
        return categoriaProyectosBean.obtenerSecundarias(orgPk);
    }

    public CategoriaProyectos obtenerPorId(Integer catPk) {
        return categoriaProyectosBean.obtenerPorId(catPk);
    }
}
