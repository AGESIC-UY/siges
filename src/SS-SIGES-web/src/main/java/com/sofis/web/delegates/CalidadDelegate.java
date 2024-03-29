package com.sofis.web.delegates;

import com.sofis.business.ejbs.CalidadBean;
import com.sofis.entities.data.Calidad;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FiltroCalidadTO;
import java.util.List;
import javax.inject.Inject;

public class CalidadDelegate {

    @Inject
    private CalidadBean calidadBean;

    public Calidad obtenerPorId(Integer calId) {
        return calidadBean.obtenerPorId(calId);
    }

    public List<Calidad> obtenerPorProyId(Integer proyId) {
        return calidadBean.obtenerPorProyId(proyId);
    }
    
    public List<Calidad> obtenerResumenCalidad(Integer proyPk, int size) {
        return calidadBean.obtenerPendientesCalidad(proyPk, size);
    }

    public List<Calidad> buscarPorFiltro(FiltroCalidadTO filtro, Integer orgPk) {
        return calidadBean.buscarPorFiltro(filtro, orgPk);
    }
    
	public Double calcularIndiceCalidad(Integer proyId, Integer orgPk) {
        return calidadBean.calcularIndiceCalidad(proyId, orgPk);
    }

	public String calcularIndiceCalidadColor(Double ind, Integer orgPk) {
        return calidadBean.calcularIndiceCalidadColor(ind, orgPk);
    }

    public Calidad guardar(Calidad cal, Integer orgPk, SsUsuario usuario) {
        return calidadBean.guardarCal(cal, orgPk, usuario);
    }

    public List<Calidad> guardar(List<Calidad> list, Integer orgPk, SsUsuario usuario) {
        return calidadBean.guardarCal(list, orgPk, usuario);
    }

    public void eliminar(Integer calPk) {
        calidadBean.eliminar(calPk);
    }

    public String tipoCalidadStr(Integer tipo, Integer orgPk) {
        return calidadBean.tipoCalidadStr(tipo, orgPk);
    }
    
    public String valorColorTabla(String cod) {
        return calidadBean.valorColorTabla(cod);
    }
}
