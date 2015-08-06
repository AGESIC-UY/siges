package com.sofis.entities.tipos;

import com.sofis.entities.data.Moneda;

/**
 *
 * @author Usuario
 */
public class MonedaImporteResumenTO {

    private Moneda moneda;
    private Double importeApro;
    private Double importePend;

    public MonedaImporteResumenTO(Moneda moneda, Double importeApro, Double importePend) {
        this.moneda = moneda;
        this.importeApro = importeApro;
        this.importePend = importePend;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Double getImporteApro() {
        return importeApro;
    }

    public void setImporteApro(Double importeApro) {
        this.importeApro = importeApro;
    }

    public Double getImportePend() {
        return importePend;
    }

    public void setImportePend(Double importePend) {
        this.importePend = importePend;
    }

    public void addImporteApro(Double importe) {
        if (importe != null && this.importeApro != null) {
            this.importeApro = this.importeApro + importe;
        }

    }

    public void addImportePend(Double importe) {
        if (importe != null && this.importePend != null) {
            this.importePend = this.importePend + importe;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (moneda != null) {
            return moneda.getMonPk().equals(((Moneda) obj).getMonPk());
        } else {
            return super.equals(obj);
        }
    }
}
