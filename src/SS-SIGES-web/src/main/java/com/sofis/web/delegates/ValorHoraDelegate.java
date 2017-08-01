package com.sofis.web.delegates;

import com.sofis.business.ejbs.ValorHoraBean;
import com.sofis.entities.data.ValorHora;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class ValorHoraDelegate {

    @Inject
    private ValorHoraBean valorHoraBean;

    public List<ValorHora> obtenerValorHoraPorUsu(Integer usuId, Integer orgPk) {
        return valorHoraBean.obtenerValorHoraPorUsu(usuId, orgPk);
    }

    public ValorHora guardarValHora(ValorHora valHoraEnEdicion) {
        return valorHoraBean.guardarValHora(valHoraEnEdicion);
    }
    
    public ValorHora obtenerValorHoraPorAnio(Integer usuId, Integer orgPk, Integer anio) {
        return valorHoraBean.obtenerValorHoraPorAnio(usuId, orgPk, anio);
    }
    
    public List<ValorHora> obtenerValorHoraPorUsu(Integer usuId, Integer orgPk, Integer anio) {
        return valorHoraBean.obtenerValorHoraPorUsu(usuId, orgPk, anio);
    }
}
