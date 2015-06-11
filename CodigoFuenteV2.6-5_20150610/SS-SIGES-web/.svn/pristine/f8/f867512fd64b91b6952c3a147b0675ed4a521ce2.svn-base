package com.sofis.web.delegates;

import com.sofis.business.ejbs.LeccionesAprendidasBean;
import com.sofis.entities.data.AreaConocimiento;
import com.sofis.entities.data.LeccionesAprendidas;
import com.sofis.entities.data.TipoLeccion;
import com.sofis.entities.tipos.FiltroLeccAprTO;
import com.sofis.entities.tipos.LeccAprendidasTO;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class LeccAprDelegate {
    
    @Inject
    LeccionesAprendidasBean leccionesAprendidasBean;
    
    public List<TipoLeccion> obtenerTipoLecciones(){
        return leccionesAprendidasBean.obtenerTipoLecciones();
    }
    
    public List<LeccAprendidasTO> buscarLeccionesPorFiltro(FiltroLeccAprTO filtro, Integer orgPk) {
        return leccionesAprendidasBean.buscarLeccionesPorFiltro(filtro, orgPk);
    }
    
    public LeccionesAprendidas obtenerLeccionPorPk(Integer leccAprPk) {
        return leccionesAprendidasBean.obtenerLeccionPorPk(leccAprPk);
    }

    public LeccionesAprendidas guardarLeccion(LeccionesAprendidas leccAprendida) {
        return leccionesAprendidasBean.guardarLeccion(leccAprendida);
    }
    
    public LeccAprendidasTO convertLeccAprendidasTO(LeccionesAprendidas leccApr) {
        return leccionesAprendidasBean.convertLeccAprendidasTO(leccApr);
    }

    public LeccionesAprendidas inactivarLeccionPorPk(Integer leccAprPk) {
        return leccionesAprendidasBean.inactivarLeccionPorPk(leccAprPk);
    }

}
