package com.sofis.web.delegates;

import com.sofis.business.ejbs.AdquisicionBean;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.AdqPagosTO;
import com.sofis.exceptions.GeneralException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class AdquisicionDelegate {

    @Inject
    private AdquisicionBean adquisicionBean;

    public Adquisicion guardarAdquisicion(Adquisicion adquisicion, Integer fichaFk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) throws GeneralException {
        return adquisicionBean.guardarAdquisicion(adquisicion, fichaFk, tipoFicha, usuario, orgPk);
    }

    public List<AdqPagosTO> obtenerAdquisicionPagosList(Integer presupuestoId) {
        return adquisicionBean.obtenerAdquisicionPagosList(presupuestoId);
    }

    public Adquisicion obtenerAdquisicionPorId(Integer adqPk) {
        return adquisicionBean.obtenerAdquisicionPorId(adqPk);
    }

    public List<Adquisicion> obtenerAdquisicionPorPre(Integer prePk) {
        return adquisicionBean.obtenerAdquisicionPorPre(prePk);
    }

    public List<Adquisicion> obtenerAdquisicionPorProy(Integer proyPk) {
        return adquisicionBean.obtenerAdquisicionPorProy(proyPk);
    }

    public List<Adquisicion> obtenerAdqDevPorProy(Integer proyPk) {
        return adquisicionBean.obtenerAdqDevPorProy(proyPk);
    }

    public void eliminarAdquisicion(Integer adqPk, Integer proyPk, Integer orgPk) {
        adquisicionBean.eliminarAdquisicion(adqPk, proyPk, orgPk);
    }

    public Double costoEjecutado(Integer adqPk) {
        return adquisicionBean.costoEjecutado(adqPk);
    }

    public Double costoTotal(Integer adqPk) {
        return adquisicionBean.costoTotal(adqPk);
    }

    public Pagos obtenerUltimoPago(Integer adqPk) {
        return adquisicionBean.obtenerUltimoPago(adqPk);
    }

    public Date obtenerFechaPrimerPago(Integer adqPk) {

        return adquisicionBean.obtenerFechaPrimerPago(adqPk);
    }

    public Adquisicion copiarAdquisicion(Adquisicion adquisicion, Date fechaPrimerPago, Double porcentajeImporte, Integer orgPk, Boolean copiarReferenciaEnPagos) {

        return adquisicionBean.copiarAdquisicion(adquisicion, fechaPrimerPago, porcentajeImporte, orgPk, copiarReferenciaEnPagos);
    }

    public void eliminarDevengado(Integer adqPk) {

        adquisicionBean.eliminarDevengado(adqPk);
    }

    public void bajarAdquisicion(Integer adqPk) {
        adquisicionBean.bajarAdquisicion(adqPk);
    }

    public void subirAdquisicion(Integer adqPk) {
        adquisicionBean.subirAdquisicion(adqPk);
    }

    public List<Adquisicion> obtenerProOrganisacionYIdAdquisicion(Integer orgaPk, Integer idAdquisicion) {
        return adquisicionBean.obtenerProOrganisacionYIdAdquisicion(orgaPk, idAdquisicion);
    }

}
