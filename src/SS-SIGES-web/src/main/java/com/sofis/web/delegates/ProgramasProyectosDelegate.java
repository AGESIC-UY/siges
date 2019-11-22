 package com.sofis.web.delegates;

import com.sofis.business.ejbs.DataTestingBean;
import com.sofis.business.ejbs.ProgramasProyectosBean;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.tipos.FiltroInicioItem;
import com.sofis.entities.tipos.FiltroInicioResultadoTO;
import com.sofis.entities.tipos.FiltroInicioTO;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class ProgramasProyectosDelegate {

    @Inject
    private ProgramasProyectosBean programasProyectosBean;
    @Inject
    private DataTestingBean dataTestingBean;

    public List<FichaTO> obtenerProyProgPendientes(SsUsuario usuario, Integer orgPk) throws GeneralException {
        return programasProyectosBean.obtenerProyProgPendientes(usuario, orgPk);
    }

    public FiltroInicioResultadoTO obtenerPrimerNivel(Integer organismoId, Areas area, SsUsuario usuario, FiltroInicioTO filtro, Configuracion confAmarillo, Configuracion confRojo) throws GeneralException {
        return programasProyectosBean.obtenerPrimerNivel(organismoId, area, usuario, filtro, confAmarillo, confRojo);
    }

    public FiltroInicioItem obtenerIndicadoresMaterializados(FiltroInicioItem item, Integer orgPk, Map<String, String> config, List<Moneda> monedas) {
        return programasProyectosBean.cargarIndicadoresMaterializados(item, orgPk, null, config, monedas);
    }

    public FiltroInicioResultadoTO obtenerSegundoNivel(Integer organismoId, Integer areaId, SsUsuario usuario, FiltroInicioTO filtro) throws GeneralException {
        return programasProyectosBean.obtenerSegundoNivel(organismoId, areaId, usuario, filtro);
    }

    public void generarDatosTesting(int organismoId, Integer areaId, SsUsuario usuario) {
        dataTestingBean.generarDatosTesting(organismoId, areaId, usuario);
    }

    public FiltroInicioItem crearFiltroInicioItem(Object o) {
        return programasProyectosBean.crearFiltroInicioItem(o);
    }
    
    public String obtenerColorEstadoAcFromCodigo(Integer estadoAc) {
      return programasProyectosBean.obtenerColorEstadoAcFromCodigo(estadoAc);
    }
}
