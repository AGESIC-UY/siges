package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VinculacionPK implements Serializable {

	@Column(name = "tablero_fk")
	private long idTablero;

	@Column(name = "cronograma_fk")
	private long idCronograma;

	public VinculacionPK() {
	}

	public VinculacionPK(long idTablero, long idCronograma) {
		this.idTablero = idTablero;
		this.idCronograma = idCronograma;
	}

	public long getIdTablero() {
		return idTablero;
	}

	public void setIdTablero(long idTablero) {
		this.idTablero = idTablero;
	}

	public long getIdCronograma() {
		return idCronograma;
	}

	public void setIdCronograma(long idCronograma) {
		this.idCronograma = idCronograma;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + (int) (this.idTablero ^ (this.idTablero >>> 32));
		hash = 37 * hash + (int) (this.idCronograma ^ (this.idCronograma >>> 32));
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final VinculacionPK other = (VinculacionPK) obj;
		if (this.idTablero != other.idTablero) {
			return false;
		}
		if (this.idCronograma != other.idCronograma) {
			return false;
		}
		return true;
	}

}
