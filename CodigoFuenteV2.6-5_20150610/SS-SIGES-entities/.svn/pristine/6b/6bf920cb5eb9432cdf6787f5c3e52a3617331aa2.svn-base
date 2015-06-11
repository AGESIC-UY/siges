package com.sofis.entities.tipos;

import java.util.HashMap;
import java.util.Map;

/**
 * TO para contener los datos de costos a desplegar en el reporte de proyecto.
 *
 * @author Usuario
 */
public class CostoReporteTO {

    private String descripcion;
    /**
     * 1-detalle, 2-total, 3-porcentajes
     */
    private int tipo;
    private Map<Integer, CostoDetalleReporteTO> costoMoneda;

    public CostoReporteTO() {
        costoMoneda = new HashMap<>();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Map<Integer, CostoDetalleReporteTO> getCostoMoneda() {
        return costoMoneda;
    }

    public void setCostoMoneda(Map<Integer, CostoDetalleReporteTO> costoMoneda) {
        this.costoMoneda = costoMoneda;
    }

    public void addCostoMonedaTotal(Integer monPk, Double ejecutado, Double total) {
        CostoDetalleReporteTO detalleTotal;
        if (this.costoMoneda.containsKey(monPk)) {
            detalleTotal = this.costoMoneda.get(monPk);
        } else {
            detalleTotal = new CostoDetalleReporteTO();
        }

        Double ejec = null;
        if (ejecutado != null) {
            ejec = detalleTotal.getEjecutado() != null ? detalleTotal.getEjecutado() + ejecutado : ejecutado;
            detalleTotal.setEjecutado(ejec);
        }

        Double tot = null;
        if (total != null) {
            tot = detalleTotal.getTotal() != null ? detalleTotal.getTotal() + total : total;
            detalleTotal.setTotal(tot);
        }
        
        this.costoMoneda.put(monPk, detalleTotal);
    }

    public CostoDetalleReporteTO getCostoDetallePorMoneda(Integer monPk) {
        if (monPk != null && this.costoMoneda != null) {
            return this.costoMoneda.get(monPk);
        }
        return null;
    }
}
