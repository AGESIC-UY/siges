package com.sofis.web.delegates;

import com.sofis.business.ejbs.ReportePresupuestoBean;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FiltroReporteTO;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class ReportePresupuestoDelegate {

    @Inject
    private ReportePresupuestoBean reportePresupuestoBean;

    public byte[] generarReportePlanillaPorFiltro(Integer orgPk, FiltroReporteTO filtro, SsUsuario usuario) {
        return reportePresupuestoBean.generarReportePlanillaPorFiltro(orgPk, filtro, usuario);
    }
}
