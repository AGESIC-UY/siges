package com.sofis.web.delegates;

import com.sofis.business.ejbs.CronogramasBean;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.CronogramaTO;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class CronogramaDelegate {

    @Inject
    private CronogramasBean cronogramasBean;

    public Cronogramas guardar(Cronogramas cro) throws GeneralException {
		return cronogramasBean.guardar(cro);
    }
    
    public Cronogramas guardarDespuesDeCheck(Cronogramas cro) throws GeneralException {
        return cronogramasBean.guardarDespuesDeCheck(cro);
    }

    public Object guardarCronograma(Cronogramas cro, Integer fichaFk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) {
        return cronogramasBean.guardarCronograma(cro, fichaFk, tipoFicha, usuario, orgPk);
    }

    public int[] calcularAvanceCronoFinalizado(Set<Entregables> entregables) {
        return cronogramasBean.calcularAvanceCronoFinalizado(entregables);
    }

    public int[] calcularAvanceCronoParcial(Set<Entregables> entregables) {
        return cronogramasBean.calcularAvanceCronoParcial(entregables);
    }

    public List<Entregables> obtenerResumenCronograma(Integer proyPk, int size) {
        return cronogramasBean.obtenerResumenCronograma(proyPk, size);
    }

    public Integer esfuerzoTotal(Cronogramas cro) {
        return cronogramasBean.esfuerzoTotal(cro);
    }

    public String horasTotales(Cronogramas cro) {
        return cronogramasBean.horasTotales(cro);
    }
	
    public void calcularAvancePadresProyectos() {

            cronogramasBean.calcularAvancePadresProyectos();
    }

    public CronogramaTO obtenerCronogramaCompletoPorIdProyecto(Integer proyPk) {
            return cronogramasBean.obtenerCronogramaCompletoPorIdProyecto(proyPk);
    }

}
