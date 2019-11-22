package com.sofis.web.delegates;

import com.sofis.business.ejbs.EntregablesBean;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FiltroMisTareasTO;
import com.sofis.entities.tipos.MisTareasProgProyTO;
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

    public List<Entregables> obtenerEntPorCoord(Integer proyPk, Integer coord, Boolean conHitos) {
        return entregablesBean.obtenerEntPorCoord(proyPk, coord, conHitos);
    }

    public Map<String, Date> obtenerPrimeraUltimaFecha(Collection<Entregables> entSet) {
        return entregablesBean.obtenerPrimeraUltimaFecha(entSet);
    }
    
    public Date obtenerPrimeraFecha(Collection<Entregables> entSet, Boolean usarPeridoEntregable) {
        return entregablesBean.obtenerPrimeraFecha(entSet, usarPeridoEntregable);
    }
    
    public Date obtenerPrimeraFecha(Integer orgPk){
        return entregablesBean.obtenerPrimeraFecha(orgPk);
    }

    public Date obtenerUltimaFecha(Collection<Entregables> entSet, Boolean usarPeridoEntregable) {
        return entregablesBean.obtenerUltimaFecha(entSet, usarPeridoEntregable);
    }
    
    public Date obtenerUltimaFecha(Integer orgPk){
        return entregablesBean.obtenerUltimaFecha(orgPk);
    }

    public Entregables actualizarAvance(Integer proyPk, Integer entPk, Integer avance, SsUsuario usuario) {
        return entregablesBean.actualizarAvance(proyPk, entPk, avance, usuario);
    }

    public List<Entregables> obtenerEntPorProyPk(Integer proyPk) {
        return entregablesBean.obtenerEntPorProyPk(proyPk);
    }

    public List<Entregables> obtenerEntPorProgPk(Integer progPk, Boolean proyActivo) {
        return entregablesBean.obtenerEntPorProgPk(progPk, proyActivo);
    }

    public Map<String, ReporteAcumuladoMesTO> obtenerAcumuladoMapMes(List<Entregables> listEnt) {
        return entregablesBean.obtenerAcumuladoMapMes(listEnt);
    }

    public List<ReporteAcumuladoMesTO> obtenerAcumuladoCroPorMes(List<Entregables> listEnt, Integer orgPk) {
        return entregablesBean.obtenerAcumuladoCroPorMes(listEnt, orgPk);
    }

    public List<MisTareasTO> obtenerMisTareasPorFiltro(FiltroMisTareasTO filtro, Integer orgPk) {
        return entregablesBean.obtenerMisTareasPorFiltro(filtro, orgPk);
    }
    
    public List<MisTareasProgProyTO> obtenerMisTareasPorFiltroGroupProyecto(FiltroMisTareasTO filtro, Integer orgPk, boolean conProductos, int anio) {
        return entregablesBean.obtenerMisTareasPorFiltroGroupProyecto(filtro, orgPk, conProductos, anio);
    }
    
    public Double obtenerEsfuerzoTotal(Integer proyPk) {
        return entregablesBean.obtenerEsfuerzoTotal(proyPk);
    }

    public Double obtenerEsfuerzoTerminado(Integer proyPk) {
        return entregablesBean.obtenerEsfuerzoTerminado(proyPk);
    }

    public boolean mesTieneEntClave(Integer mes, Integer anio, Integer proyPk, Integer tipo) {
        return entregablesBean.mesTieneEntClave(mes, anio, proyPk, tipo);
    }

    public boolean entregableTieneClave(Integer entPk, Integer tipo) {
        return entregablesBean.entregableTieneClave(entPk, tipo);
    }
    
    public boolean entregableEsHito(Integer entPk) {
        return entregablesBean.entregableEsHito(entPk);
    }
}
