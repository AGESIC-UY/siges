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

    public List<CategoriaProyectos> obtenerTodas() {
        return categoriaProyectosBean.obtenerTodas();
    }
}
