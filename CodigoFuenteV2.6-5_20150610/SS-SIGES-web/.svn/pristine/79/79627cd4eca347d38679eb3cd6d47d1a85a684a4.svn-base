package com.sofis.web.delegates;

import com.sofis.business.ejbs.ReporteCronoAlcanceBean;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FiltroReporteTO;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class ReporteCronoAlcanceDelegate {
    
    @Inject
    private ReporteCronoAlcanceBean reporteCronoAlcanceBean;

    public byte[] generarReportePlanillaPorFiltro(Integer orgPk, FiltroReporteTO filtro, SsUsuario usuario) {
        return reporteCronoAlcanceBean.generarReportePlanillaPorFiltro(orgPk, filtro, usuario);
    }
}
