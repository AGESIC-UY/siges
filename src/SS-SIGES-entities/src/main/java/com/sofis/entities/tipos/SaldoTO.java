package com.sofis.entities.tipos;

public class SaldoTO {

	private MonedaTO moneda;
	private Double importe;

	public SaldoTO() {
	}

	public SaldoTO(MonedaTO moneda) {
		this.moneda = moneda;
		this.importe = 0D;
	}

	public MonedaTO getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaTO moneda) {
		this.moneda = moneda;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

}
