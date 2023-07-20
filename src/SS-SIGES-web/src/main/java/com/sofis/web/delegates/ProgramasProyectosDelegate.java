 package com.sofis.web.delegates;

import com.sofis.business.ejbs.DataTestingBean;
import com.sofis.business.ejbs.ProgramasProyectosBean;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.tipos.ItemInicioTO;
import com.sofis.entities.tipos.ResultadoInicioTO;
import com.sofis.entities.tipos.FiltroInicioTO;
import com.sofis.exceptions.GeneralException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class ProgramasProyectosDelegate {

    @Inject
    private ProgramasProyectosBean programasProyectosBean;

    public List<FichaTO> obtenerProyProgPendientes(SsUsuario usuario, Integer orgPk) throws GeneralException {
        return programasProyectosBean.obtenerProyProgPendientes(usuario, orgPk);
    }

    public ItemInicioTO obtenerIndicadoresMaterializados(ItemInicioTO item, Integer orgPk, Map<String, String> config, List<Moneda> monedas) {
        return programasProyectosBean.cargarIndicadoresMaterializados(item, orgPk, null, config, monedas);
    }

    public ResultadoInicioTO obtenerSegundoNivel(Integer organismoId, Integer areaId, SsUsuario usuario, FiltroInicioTO filtro) throws GeneralException {
        return programasProyectosBean.obtenerSegundoNivel(organismoId, areaId, usuario, filtro);
    }

    public ItemInicioTO crearFiltroInicioItem(Object o) {
        return programasProyectosBean.crearFiltroInicioItem(o);
    }
    
    public String obtenerColorEstadoAcFromCodigo(Integer estadoAc) {
      return programasProyectosBean.obtenerColorEstadoAcFromCodigo(estadoAc);
    }
	
	public List<ResultadoInicioTO> obtenerPrimerNivel(FiltroInicioTO filtro) {
        return programasProyectosBean.obtenerPrimerNivel(filtro);
	}
	
	public ResultadoInicioTO obtenerSegundoNivel(FiltroInicioTO filtro, ItemInicioTO item) {
        return programasProyectosBean.obtenerSegundoNivel(filtro, item);
	}

         public Long obtenerCantidadProyectosPorAreaYOrganismo(Integer orgPk, Integer areaId) throws DAOGeneralException {
             return programasProyectosBean.obtenerCantidadProyectosPorAreaYOrganismo(orgPk,areaId);
         }
}
