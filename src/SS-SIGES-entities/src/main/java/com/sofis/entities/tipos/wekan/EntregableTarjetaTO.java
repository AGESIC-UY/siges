package com.sofis.entities.tipos.wekan;

import com.sofis.entities.tipos.EntregableTO;

public class EntregableTarjetaTO {

	private long id;

	private TarjetaTO tarjeta;
	private EntregableTO entregable;

	private TableroTO tablero;

	private boolean vinculable;
	private String razonNoVinculable;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TarjetaTO getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(TarjetaTO tarjeta) {
		this.tarjeta = tarjeta;
	}

	public EntregableTO getEntregable() {
		return entregable;
	}

	public void setEntregable(EntregableTO entregable) {
		this.entregable = entregable;
	}

	public TableroTO getTablero() {
		return tablero;
	}

	public void setTablero(TableroTO tablero) {
		this.tablero = tablero;
	}

	public boolean isVinculable() {
		return vinculable;
	}

	public void setVinculable(boolean vinculable) {
		this.vinculable = vinculable;
	}

	public String getRazonNoVinculable() {
		return razonNoVinculable;
	}

	public void setRazonNoVinculable(String razonNoVinculable) {
		this.razonNoVinculable = razonNoVinculable;
	}

}
