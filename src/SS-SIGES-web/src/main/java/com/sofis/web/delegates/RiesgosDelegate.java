package com.sofis.web.delegates;

import com.sofis.business.ejbs.RiesgosBean;
import com.sofis.entities.data.Riesgos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.GeneralException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class RiesgosDelegate {

    @Inject
    private RiesgosBean riesgosBean;

    public Riesgos guardar(Riesgos prog, Integer orgPk) throws GeneralException {
        return riesgosBean.guardar(prog, orgPk);
    }

    public Riesgos obtenerRiesgoPorId(Integer id) throws GeneralException {
        return riesgosBean.obtenerRiesgosPorId(id);
    }

    public List<Riesgos> obtenerRiesgosNoSuperados(Integer proyPk) {
        return riesgosBean.obtenerRiesgosNoSuperados(proyPk);
    }

    public List<Riesgos> obtenerExposicionResumen(Integer proyPk, int size) {
        return riesgosBean.obtenerExposicionResumen(proyPk, size);
    }

    public Date obtenerDateUltimaActualizacion(Integer fichaFk) {
        return riesgosBean.obtenerDateUltimaActualizacion(fichaFk);
    }

    public String obtenerColorUltimaActualizacion(Date riesgoActualizacion, Integer orgPk) {
        return riesgosBean.obtenerColorUltimaActualizacion(riesgoActualizacion, orgPk, null);
    }

    public Double obtenerExposicionRiesgo(Integer proyPk) {
        return riesgosBean.obtenerExposicionRiesgo(proyPk);
    }

    public Double obtenerExposicionRiesgo(List<Riesgos> listRiesgos) {
        return riesgosBean.obtenerExposicionRiesgo(listRiesgos);
    }

    public String obtenerExposicionRiesgoColor(Double exposicionRiesgo, Integer orgPk) {
        return riesgosBean.obtenerExposicionRiesgoColor(exposicionRiesgo, orgPk, null);
    }

    public String obtenerFechaLimiteRiesgoColor(Date fechaLimite, Integer orgPk) {
        return riesgosBean.obtenerFechaLimiteRiesgoColor(fechaLimite, orgPk);
    }

    public void obtenerExposicionRiesgoColor(List<Riesgos> setRiesgos, Integer orgPk) {
        if (setRiesgos != null) {
            for (Riesgos r : setRiesgos) {
                r.setExposicionColor(riesgosBean.obtenerExposicionRiesgoColor(r.getExposicion(), orgPk, null));
            }
        }
    }

    public List<Riesgos> obtenerRiesgosPorProyecto(Integer proyPk) {
        return riesgosBean.obtenerRiesgosPorProyecto(proyPk);
    }

    public void eliminarRiesgo(Integer riskPk, Integer fichaFk, Integer tipoFicha, SsUsuario usuario, Integer orgPk)throws GeneralException{
        riesgosBean.eliminarRiesgo(riskPk, fichaFk, tipoFicha, usuario, orgPk);
    }

    public Riesgos superarRiesgo(Integer riskPk, Integer usuId, Integer orgPk) {
        return riesgosBean.superarRiesgo(riskPk, usuId, orgPk);
    }

    public Integer obtenerCantRiesgosAltos(List<Riesgos> listRiesgos, Integer orgPk) {
        return riesgosBean.obtenerCantRiesgosAltos(listRiesgos, orgPk);
    }
   
    public Integer obtenerCantRiesgosAltos(Integer proyPk, Integer orgPk) {
        return riesgosBean.obtenerCantRiesgosAltos(proyPk, orgPk);
    }
}
