package com.sofis.web.delegates;

import com.sofis.business.ejbs.EntregablesBean;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FiltroMisTareasTO;
import com.sofis.entities.tipos.MisTareasTO;
import com.sofis.entities.tipos.ReporteAcumuladoMesTO;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class EntregablesDelegate {

    @Inject
    EntregablesBean entregablesBean;

    public boolean tieneDependencias(Integer entPk) {
        return entregablesBean.tieneDependencias(entPk);
    }

    public List<Entregables> obtenerEntPorCoord(Integer proyPk, Integer coord) {
        return entregablesBean.obtenerEntPorCoord(proyPk, coord);
    }

    public Date obtenerPrimeraFecha(Collection<Entregables> entSet) {
        return entregablesBean.obtenerPrimeraFecha(entSet);
    }

    public Date obtenerUltimaFecha(Collection<Entregables> entSet) {
        return entregablesBean.obtenerUltimaFecha(entSet);
    }

    public Entregables actualizarAvance(Integer proyPk, Integer entPk, Integer avance, SsUsuario usuario) {
        return entregablesBean.actualizarAvance(proyPk, entPk, avance, usuario);
    }
    
    public List<Entregables> obtenerEntPorProyPk(Integer proyPk){
        return entregablesBean.obtenerEntPorProyPk(proyPk);
    }
    
    public Map<String, ReporteAcumuladoMesTO> obtenerAcumuladoMapMes(List<Entregables> listEnt) {
        return entregablesBean.obtenerAcumuladoMapMes(listEnt);
    }
    
    public List<ReporteAcumuladoMesTO> obtenerAcumuladoCroPorMes(List<Entregables> listEnt) {
        return entregablesBean.obtenerAcumuladoCroPorMes(listEnt);
    }
    
    public List<MisTareasTO> obtenerMisTareasPorFiltro(FiltroMisTareasTO filtro, Integer orgPk) {
        return entregablesBean.obtenerMisTareasPorFiltro(filtro, orgPk);
    }
    
    public Double obtenerEsfuerzoTotal(Integer proyPk){
        return entregablesBean.obtenerEsfuerzoTotal(proyPk);
    }

    public Double obtenerEsfuerzoTerminado(Integer proyPk) {
        return entregablesBean.obtenerEsfuerzoTerminado(proyPk);
    }
}
