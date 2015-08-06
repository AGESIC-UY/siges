package com.sofis.web.delegates;

import com.sofis.business.ejbs.CalidadBean;
import com.sofis.entities.data.Calidad;
import com.sofis.entities.tipos.FiltroCalidadTO;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
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

    /**
     * Calcula el indice de calidad según el proyecto aportado.
     *
     * @param proyId
     * @return Double
     */
    public Double calcularIndiceCalidad(Integer proyId, Integer orgPk) {
        return calidadBean.calcularIndiceCalidad(proyId, orgPk);
    }

    /**
     * Retorna el color para el indice de calidad aportado.
     *
     * @param ind
     * @param orgPk
     * @return String
     */
    public String calcularIndiceCalidadColor(Double ind, Integer orgPk) {
        return calidadBean.calcularIndiceCalidadColor(ind, orgPk);
    }

    private double obtenerValorCalidad(Calidad cal) {
        return calidadBean.obtenerValorCalidad(cal);
    }

    public Calidad guardar(Calidad cal, Integer orgPk) {
        return calidadBean.guardarCal(cal, orgPk);
    }

    public List<Calidad> guardar(List<Calidad> list, Integer orgPk) {
        return calidadBean.guardarCal(list, orgPk);
    }

    public void eliminar(Integer calPk) {
        calidadBean.eliminar(calPk);
    }

    public String tipoCalidadStr(Integer tipo) {
        return calidadBean.tipoCalidadStr(tipo);
    }
    
    public String valorColorTabla(String cod) {
        return calidadBean.valorColorTabla(cod);
    }
}
