package com.sofis.web.delegates;

import com.sofis.business.ejbs.GastosBean;
import com.sofis.entities.data.Gastos;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.TipoGasto;
import com.sofis.entities.tipos.MonedaImporteTO;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class GastosDelegate {
    
    @Inject
    GastosBean gastosBean;

    public Gastos registrarGasto(Gastos gasto, Integer orgPk) {
        return gastosBean.guardarGasto(gasto, orgPk);
    }

    public List<Gastos> obtenerRegistrosGastos(Integer usuId, Integer proyPk, Date filtroFechaDesde, Date filtroFechaHasta, TipoGasto tipoGasto, Long desde, Long cant, Integer aprobado) {
        return gastosBean.obtenerRegistrosGastos(usuId, proyPk, filtroFechaDesde, filtroFechaHasta, tipoGasto, desde, cant, aprobado);
    }

    public MonedaImporteTO[] obtenerGastosAbrobPorProyYMon(Integer proyPk, Integer usuId, Boolean aprobado) {
        return gastosBean.obtenerGastosPorProyYMon(proyPk, usuId, aprobado);
    }
    
    public Double obtenerGastosPorProyYMon(Integer proyPk, Integer usuId, Integer monPk, Boolean aprobado) {
        return gastosBean.obtenerGastosPorProyYMon(proyPk, usuId, monPk, aprobado);
    }
    
    public void guardarGastos(List<Gastos> revisionGastosListado, Integer orgPk) {
        gastosBean.guardarGastos(revisionGastosListado, orgPk);
    }

    public List<Moneda> obtenerMonedasPorProy(Integer proyPk) {
        return gastosBean.obtenerMonedasPorProy(proyPk);
    }

    public Double obtenerImpTotalGastosPorProy(Integer proyPk, Integer monPk, Integer mes, Integer anio, Boolean aprobado) {
        return gastosBean.obtenerImpTotalGastosPorProy(proyPk, monPk, mes, anio, aprobado);
    }

    public void eliminarGastos(Integer gastoPk) {
        gastosBean.eliminarGastos(gastoPk);
    }
}
