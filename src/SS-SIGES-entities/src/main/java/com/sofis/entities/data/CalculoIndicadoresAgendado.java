package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "calculo_indicadores_agendado")
@NamedQueries({
	@NamedQuery(name = "CalculoIndicadoresAgendado.findAll", query = "SELECT cia FROM CalculoIndicadoresAgendado cia"),
	@NamedQuery(name = "CalculoIndicadoresAgendado.existeConPrograma", query = "SELECT COUNT(cia) FROM CalculoIndicadoresAgendado cia WHERE cia.programa.progPk = :idPrograma")
})
public class CalculoIndicadoresAgendado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@JoinColumn(name = "programa", referencedColumnName = "prog_pk")
	@ManyToOne(fetch = FetchType.LAZY)
	private Programas programa;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setPrograma(Programas programa) {
		this.programa = programa;
	}

	public Programas getPrograma() {
		return programa;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 73 * hash + Objects.hashCode(this.id);
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
		final CalculoIndicadoresAgendado other = (CalculoIndicadoresAgendado) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

}
