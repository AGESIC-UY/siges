package com.sofis.entities.tipos;

import com.sofis.entities.data.Moneda;
import java.io.Serializable;

/**
 * Detalle de los costos a desplegar en el reporte de proyecto.
 * @author Usuario
 */
public class CostoDetalleReporteTO implements Serializable{

    private Moneda moneda;
    private Double ejecutado;
    private Double total;
    private Double porcentajeEjec;

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
    
    public Double getEjecutado() {
        return ejecutado;
    }

    public void setEjecutado(Double ejecutado) {
        this.ejecutado = ejecutado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPorcentajeEjec() {
        return porcentajeEjec;
    }

    public void setPorcentajeEjec(Double porcentajeEjec) {
        this.porcentajeEjec = porcentajeEjec;
    }
    
    
}
