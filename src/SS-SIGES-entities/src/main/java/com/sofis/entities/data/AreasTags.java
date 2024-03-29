package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "areas_tags")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "AreasTags.findAll", query = "SELECT a FROM AreasTags a"),
	@NamedQuery(name = "AreasTags.findHijas", query = "SELECT a FROM AreasTags a WHERE areatagPadreFk.arastagPk = :idPadre")
})
public class AreasTags implements Serializable {

	public static final int NOMBRE_LENGHT = 45;
	public static final int TEMATICA_LENGHT = 45;

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "arastag_pk")
	private Integer arastagPk;

	@Column(name = "areatag_tematica")
	private String areatagTematica;

	@Column(name = "areatag_excluyente")
	private Boolean areatagExcluyente;

	@Column(name = "areatag_nombre")
	private String areatagNombre;

	@Column(name = "areatag_categoria")
	private String areatagCategoria;

	@JoinColumn(name = "areatag_padre_fk", referencedColumnName = "arastag_pk")
	@ManyToOne(optional = true)
	private AreasTags areatagPadreFk;

	@JoinColumn(name = "areatag_org_fk", referencedColumnName = "org_pk")
	@ManyToOne(optional = false)
	private Organismos areatagOrgFk;

	@Column(name = "areatag_habilitada")
	private Boolean habilitada;

	public AreasTags() {
	}

	public AreasTags(Integer arastagPk) {
		this.arastagPk = arastagPk;
	}

	public Integer getArastagPk() {
		return arastagPk;
	}

	public void setArastagPk(Integer arastagPk) {
		this.arastagPk = arastagPk;
	}

	public String getAreatagTematica() {
		return areatagTematica;
	}

	public void setAreatagTematica(String areatagTematica) {
		this.areatagTematica = areatagTematica;
	}

	public Boolean getAreatagExcluyente() {
		return areatagExcluyente;
	}

	public void setAreatagExcluyente(Boolean areatagExcluyente) {
		this.areatagExcluyente = areatagExcluyente;
	}

	public String getAreatagNombre() {
		return areatagNombre;
	}

	public void setAreatagNombre(String areatagNombre) {
		this.areatagNombre = areatagNombre;
	}

	public String getAreatagCategoria() {
		return areatagCategoria;
	}

	public void setAreatagCategoria(String areatagCategoria) {
		this.areatagCategoria = areatagCategoria;
	}

	public AreasTags getAreatagPadreFk() {
		return areatagPadreFk;
	}

	public void setAreatagPadreFk(AreasTags areatagPadreFk) {
		this.areatagPadreFk = areatagPadreFk;
	}

	public Organismos getAreatagOrgFk() {
		return areatagOrgFk;
	}

	public void setAreatagOrgFk(Organismos areatagOrgFk) {
		this.areatagOrgFk = areatagOrgFk;
	}

	public Boolean getHabilitada() {
		return habilitada;
	}

	public void setHabilitada(Boolean habilitada) {
		this.habilitada = habilitada;
	}

	public String getAreatagPadresStr() {
		if (areatagPadreFk != null) {
			String s = "";
			AreasTags at = this;
			while (at != null && at.areatagPadreFk != null) {
				s = at.areatagPadreFk.getAreatagNombre() + (s.isEmpty() ? "" : "->") + s;
				at = at.areatagPadreFk;
			}
			return s;
		}
		return null;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (arastagPk != null ? arastagPk.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AreasTags)) {
			return false;
		}
		AreasTags other = (AreasTags) object;
		if ((this.arastagPk == null && other.arastagPk != null) || (this.arastagPk != null && !this.arastagPk.equals(other.arastagPk))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sofis.entities.data.AreasTags[ arastagPk=" + arastagPk + " ]";
	}
}
