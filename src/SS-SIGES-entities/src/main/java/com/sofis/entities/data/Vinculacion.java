package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "wekan_vinculacion")
@NamedQueries({
	@NamedQuery(name = "Vinculacion.obtenerPorIdCronograma", query = "SELECT v FROM Vinculacion v WHERE v.cronograma.croPk = :idCronograma"),
	@NamedQuery(name = "Vinculacion.obtenerCantidadVinculacionesPorIdTablero", query = "SELECT v FROM Vinculacion v WHERE v.tablero.id = :id")
})
public class Vinculacion implements Serializable {

    @EmbeddedId
    protected VinculacionPK idVinculacion = new VinculacionPK();
	
	@ManyToOne
	@MapsId("idTablero")
	@JoinColumn(name = "tablero_fk", referencedColumnName = "id")
	@AttributeOverride(name="tablero_fk", column=@Column(name="tablero_fk"))
	private Tablero tablero;
	
	@ManyToOne
	@MapsId("idCronograma")
	@JoinColumn(name = "cronograma_fk", referencedColumnName = "cro_pk")
	@AttributeOverride(name="cronograma_fk", column=@Column(name="cronograma_fk"))
	private Cronogramas cronograma;

	@Temporal(TIMESTAMP)
	@Column(name = "fecha_alta")
	private Date fechaAlta;
	
	@Column(name = "usuario_alta")
	private String usuarioAlta;

	public VinculacionPK getIdVinculacion() {
		return idVinculacion;
	}

	public void setIdVinculacion(VinculacionPK idVinculacion) {
		this.idVinculacion = idVinculacion;
	}
	
	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public Cronogramas getCronograma() {
		return cronograma;
	}

	public void setCronograma(Cronogramas cronograma) {
		this.cronograma = cronograma;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 37 * hash + Objects.hashCode(this.idVinculacion);
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
		final Vinculacion other = (Vinculacion) obj;
		if (!Objects.equals(this.idVinculacion, other.idVinculacion)) {
			return false;
		}
		return true;
	}

}
