package com.sofis.entities.tipos;

import com.sofis.entities.data.Moneda;

/**
 *
 * @author Usuario
 */
public class MonedaImporteTO {

    private Moneda moneda;
    private Double importe;

    public MonedaImporteTO() {
    }

    public MonedaImporteTO(Moneda moneda, Double importe) {
        this.moneda = moneda;
        this.importe = importe;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }
    
    public void addImporte(Double importe) {
        if (importe != null && this.importe != null){
            this.importe = this.importe + importe;
        }
        
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (moneda != null){
            return moneda.getMonPk().equals(((Moneda)obj).getMonPk());
        }else{
           return super.equals(obj);
        }
        
    }
    
    
}