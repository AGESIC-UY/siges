package com.sofis.entities.data;

import com.sofis.entities.tipos.MonedaImporteTO;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "participantes")
@XmlRootElement
public class Participantes implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "part_pk")
	private Integer partPk;
	@JoinColumn(name = "part_usu_fk", referencedColumnName = "usu_id")
	@ManyToOne(optional = false)
	private SsUsuario partUsuarioFk;
	@JoinColumn(name = "part_proy_fk", referencedColumnName = "proy_pk")
	@ManyToOne(optional = false)
	private Proyectos partProyectoFk;
	@JoinColumn(name = "part_ent_fk", referencedColumnName = "ent_pk")
	@ManyToOne(optional = true)
	private Entregables partEntregablesFk;
	@Column(name = "part_horas_plan")
	private Double partHorasPlan;
	@Column(name = "part_activo")
	private Boolean partActivo;
	@Transient
	private Double horasAprobadas;
	@Transient
	private Double horasPendientes;
	@Transient
	private MonedaImporteTO[] gastosAprobados;
	@Transient
	private MonedaImporteTO[] gastosPendientes;

	public Participantes() {
		partActivo = true;
	}

	public Integer getPartPk() {
		return partPk;
	}

	public void setPartPk(Integer partPk) {
		this.partPk = partPk;
	}

	public Proyectos getPartProyectoFk() {
		return partProyectoFk;
	}

	public void setPartProyectoFk(Proyectos partProyectoFk) {
		this.partProyectoFk = partProyectoFk;
	}

	public Entregables getPartEntregablesFk() {
		return partEntregablesFk;
	}

	public void setPartEntregablesFk(Entregables partEntregablesFk) {
		this.partEntregablesFk = partEntregablesFk;
	}

	public Double getPartHorasPlan() {
		return partHorasPlan;
	}

	public void setPartHorasPlan(Double partHorasPlan) {
		this.partHorasPlan = partHorasPlan;
	}

	public Boolean getPartActivo() {
		return partActivo;
	}

	@Transient
	private boolean participanteActivo;

	public Boolean getParticipanteActivo() {
		return partActivo != null ? partActivo : false;
	}

	public void setParticipanteActivo(boolean participanteActivo) {
		setPartActivo(participanteActivo);
	}

	public void setParticipanteActivo(Boolean participanteActivo) {
		setPartActivo(participanteActivo);
	}

	public boolean isParticipanteActivo() {
		return partActivo != null ? partActivo : false;
	}

	public void setPartActivo(Boolean partActivo) {
		this.partActivo = partActivo;
	}

	public Double getHorasPendientes() {
		return horasPendientes;
	}

	public void setHorasPendientes(Double horasPendientes) {
		this.horasPendientes = horasPendientes;
	}

	public Double getHorasAprobadas() {
		return horasAprobadas;
	}

	public void setHorasAprobadas(Double horasAprobadas) {
		this.horasAprobadas = horasAprobadas;
	}

	public SsUsuario getPartUsuarioFk() {
		return partUsuarioFk;
	}

	public void setPartUsuarioFk(SsUsuario partUsuarioFk) {
		this.partUsuarioFk = partUsuarioFk;
	}

	public MonedaImporteTO[] getGastosAprobados() {
		return gastosAprobados;
	}

	public String getGastosAprobadosTxt() {
		if (gastosAprobados != null && gastosAprobados.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < gastosAprobados.length; i++) {
				if (gastosAprobados[i].getImporte() != null) {
					if (i > 0) {
						sb.append("<br/>");
					}
					sb.append(gastosAprobados[i].getMoneda().getMonSigno())
						.append(" ")
						.append(gastosAprobados[i].getImporte());
				}
			}
			return sb.toString();
		}
		return "";
	}

	public void setGastosAprobados(MonedaImporteTO[] gastosAprobados) {
		this.gastosAprobados = gastosAprobados;
	}

	public MonedaImporteTO[] getGastosPendientes() {
		return gastosPendientes;
	}

	public String getGastosPendientesTxt() {
		if (gastosPendientes != null && gastosPendientes.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < gastosPendientes.length; i++) {
				if (gastosPendientes[i].getImporte() != null) {
					if (i > 0) {
						sb.append("<br/>");
					}
					sb.append(gastosPendientes[i].getMoneda().getMonSigno())
						.append(" ")
						.append(gastosPendientes[i].getImporte());
				}
			}
			return sb.toString();
		}
		return "";
	}

	public void setGastosPendientes(MonedaImporteTO[] gastosPendientes) {
		this.gastosPendientes = gastosPendientes;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (partPk != null ? partPk.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Participantes)) {
			return false;
		}
		Participantes other = (Participantes) object;
		if ((this.partPk == null && other.partPk != null) || (this.partPk != null && !this.partPk.equals(other.partPk))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sofis.entities.data.Participantes[ partPk=" + partPk + " ]";
	}

}
