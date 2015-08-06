package com.sofis.web.delegates;

import com.sofis.business.ejbs.PlantillaCronogramaBean;
import com.sofis.entities.data.PlantillaCronograma;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FiltroPlantillaCronogramaTO;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author sofis
 */
public class PlantillaCronogramaDelegate {

    @Inject
    PlantillaCronogramaBean plantillaCronogramaBean;

    public void eliminarPlantillaCronograma(Integer pCronPk) {
        plantillaCronogramaBean.eliminarPlantillaCronograma(pCronPk);
    }

    public List<PlantillaCronograma> buscarPorFiltro(FiltroPlantillaCronogramaTO filtro, Integer orgPk) {
        return plantillaCronogramaBean.buscarPorFiltro(filtro, orgPk);
    }

    public PlantillaCronograma guardarPlantillaCronograma(PlantillaCronograma plantillaCronograma) {
        return plantillaCronogramaBean.guardarPlantillaCronograma(plantillaCronograma);
    }

    public PlantillaCronograma obtenerPlantillaCronogramaPorPk(Integer pk) {
        return plantillaCronogramaBean.obtenerPlantillaCronogramaPorPk(pk);
    }

    public void generarCroDesdePlantilla(Integer proyPk, PlantillaCronograma plantillaCro, Date plantillaFechaInicio, SsUsuario usuario, Integer orgPk) {
        plantillaCronogramaBean.generarCroDesdePlantilla(proyPk, plantillaCro, plantillaFechaInicio, usuario, orgPk);
    }
}
