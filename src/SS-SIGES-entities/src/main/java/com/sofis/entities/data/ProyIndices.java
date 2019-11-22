package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "proy_indices")
@XmlRootElement
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProyIndices implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "proyind_pk")
	private Integer proyindPk;
	@Column(name = "proyind_riesgo_expo")
	private Double proyindRiesgoExpo;
	@Column(name = "proyind_riesgo_ultact")
	@Temporal(TemporalType.DATE)
	private Date proyindRiesgoUltact;
	@Column(name = "proyind_riesgo_alto")
	private Integer proyindRiesgoAlto;
	@Column(name = "proyind_metodo_estado")
	private Double proyindMetodologiaEstado;
	@Column(name = "proyind_metodo_sin_aprobar")
	private Boolean proyindMetodologiaSinAprobar;
	@Column(name = "proyind_periodo_inicio")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date proyindPeriodoInicio;
	@Column(name = "proyind_periodo_fin")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date proyindPeriodoFin;
	@Column(name = "proyind_porc_peso_total")
	private Double proyindPorcPesoTotal;

	@Column(name = "proyind_cal_ind")
	private Double proyindCalInd;
	@Column(name = "proyind_cal_pend")
	private Integer proyindCalPend;

	//Calculados diarios que se materializan
	//Fecha de procesado?
	//Fase(Color)
	@Column(name = "proyind_fase_color")
	private Integer proyindFaseColor;
	//Avance
	@Column(name = "proyind_avance_par_azul")
	private Integer proyindAvanceParAzul;
	@Column(name = "proyind_avance_par_verde")
	private Integer proyindAvanceParVerde;
	@Column(name = "proyind_anvance_par_rojo")
	private Integer proyindAvanceParRojo;
	@Column(name = "proyind_avance_fin_azul")
	private Integer proyindAvanceFinAzul;
	@Column(name = "proyind_avance_fin_verde")
	private Integer proyindAvanceFinVerde;
	@Column(name = "proyind_anvance_fin_rojo")
	private Integer proyindAvanceFinRojo;

	@Column(name = "proyind_fecha_act")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date proyindFechaAct;

	//Presupuesto
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "proyindpreProyindFk", fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(FetchMode.SELECT)
	private Set<ProyIndicesPre> proyIndPreSet;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proyind_periodo_inicio_ent_fk", referencedColumnName = "ent_pk", nullable = true)
	private Entregables proyindPeriodoInicioEntregable;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proyind_periodo_fin_ent_fk", referencedColumnName = "ent_pk", nullable = true)
	private Entregables proyindPeriodoFinEntregable;

	@Column(name = "proyind_fecha_act_color")
	private Integer proyindFechaActColor;

	public ProyIndices() {
	}

	public ProyIndices(Integer proyindPk) {
		this.proyindPk = proyindPk;
	}

	public Integer getProyindPk() {
		return proyindPk;
	}

	public void setProyindPk(Integer proyindPk) {
		this.proyindPk = proyindPk;
	}

	public Double getProyindRiesgoExpo() {
		return proyindRiesgoExpo;
	}

	public void setProyindRiesgoExpo(Double proyindRiesgoExpo) {
		this.proyindRiesgoExpo = proyindRiesgoExpo;
	}

	public Date getProyindRiesgoUltact() {
		return proyindRiesgoUltact;
	}

	public void setProyindRiesgoUltact(Date proyindRiesgoUltact) {
		this.proyindRiesgoUltact = proyindRiesgoUltact;
	}

	public Integer getProyindRiesgoAlto() {
		return proyindRiesgoAlto;
	}

	public void setProyindRiesgoAlto(Integer proyindRiesgoAlto) {
		this.proyindRiesgoAlto = proyindRiesgoAlto;
	}

	public Double getProyindMetodologiaEstado() {
		return proyindMetodologiaEstado;
	}

	public void setProyindMetodologiaEstado(Double proyindMetodologiaEstado) {
		this.proyindMetodologiaEstado = proyindMetodologiaEstado;
	}

	public Boolean getProyindMetodologiaSinAprobar() {
		return proyindMetodologiaSinAprobar;
	}

	public void setProyindMetodologiaSinAprobar(Boolean proyindMetodologiaSinAprobar) {
		this.proyindMetodologiaSinAprobar = proyindMetodologiaSinAprobar;
	}

	public Date getProyindPeriodoInicio() {
		return proyindPeriodoInicio;
	}

	public void setProyindPeriodoInicio(Date proyindPeriodoInicio) {
		this.proyindPeriodoInicio = proyindPeriodoInicio;
	}

	public Date getProyindPeriodoFin() {
		return proyindPeriodoFin;
	}

	public void setProyindPeriodoFin(Date proyindPeriodoFin) {
		this.proyindPeriodoFin = proyindPeriodoFin;
	}

	public Double getProyindPorcPesoTotal() {
		return proyindPorcPesoTotal;
	}

	public void setProyindPorcPesoTotal(Double proyindPorcPesoTotal) {
		this.proyindPorcPesoTotal = proyindPorcPesoTotal;
	}

	public Double getProyindCalInd() {
		return proyindCalInd;
	}

	public void setProyindCalInd(Double proyindCalInd) {
		this.proyindCalInd = proyindCalInd;
	}

	public Integer getProyindCalPend() {
		return proyindCalPend;
	}

	public void setProyindCalPend(Integer proyindCalPend) {
		this.proyindCalPend = proyindCalPend;
	}

	public Integer getProyindFaseColor() {
		return proyindFaseColor;
	}

	public void setProyindFaseColor(Integer proyindFaseColor) {
		this.proyindFaseColor = proyindFaseColor;
	}

	public Integer getProyindAvanceParAzul() {
		return proyindAvanceParAzul;
	}

	public void setProyindAvanceParAzul(Integer proyindAvanceParAzul) {
		this.proyindAvanceParAzul = proyindAvanceParAzul;
	}

	public Integer getProyindAvanceParVerde() {
		return proyindAvanceParVerde;
	}

	public void setProyindAvanceParVerde(Integer proyindAvanceParVerde) {
		this.proyindAvanceParVerde = proyindAvanceParVerde;
	}

	public Integer getProyindAvanceParRojo() {
		return proyindAvanceParRojo;
	}

	public void setProyindAvanceParRojo(Integer proyindAvanceParRojo) {
		this.proyindAvanceParRojo = proyindAvanceParRojo;
	}

	public Integer getProyindAvanceFinAzul() {
		return proyindAvanceFinAzul;
	}

	public void setProyindAvanceFinAzul(Integer proyindAvanceFinAzul) {
		this.proyindAvanceFinAzul = proyindAvanceFinAzul;
	}

	public Integer getProyindAvanceFinVerde() {
		return proyindAvanceFinVerde;
	}

	public void setProyindAvanceFinVerde(Integer proyindAvanceFinVerde) {
		this.proyindAvanceFinVerde = proyindAvanceFinVerde;
	}

	public Integer getProyindAvanceFinRojo() {
		return proyindAvanceFinRojo;
	}

	public void setProyindAvanceFinRojo(Integer proyindAvanceFinRojo) {
		this.proyindAvanceFinRojo = proyindAvanceFinRojo;
	}

	public Date getProyindFechaAct() {
		return proyindFechaAct;
	}

	public void setProyindFechaAct(Date proyindFechaAct) {
		this.proyindFechaAct = proyindFechaAct;
	}

	public int[] getProyindAvanceParcial() {
		int azul = proyindAvanceParAzul != null ? proyindAvanceParAzul : 0;
		int verde = proyindAvanceParVerde != null ? proyindAvanceParVerde : 0;
		int rojo = proyindAvanceParRojo != null ? proyindAvanceParRojo : 0;
		return new int[]{azul, verde, rojo};
	}

	public void setProyindAvanceParcial(int[] av) {
		if (av != null && av.length > 2) {
			proyindAvanceParAzul = av[0];
			proyindAvanceParVerde = av[1];
			proyindAvanceParRojo = av[2];
		}
	}

	public int[] getProyindAvanceFinal() {
		int azul = proyindAvanceFinAzul != null ? proyindAvanceFinAzul : 0;
		int verde = proyindAvanceFinVerde != null ? proyindAvanceFinVerde : 0;
		int rojo = proyindAvanceFinRojo != null ? proyindAvanceFinRojo : 0;
		return new int[]{azul, verde, rojo};
	}

	public void setProyindAvanceFinal(int[] av) {
		if (av != null && av.length > 2) {
			proyindAvanceFinAzul = av[0];
			proyindAvanceFinVerde = av[1];
			proyindAvanceFinRojo = av[2];
		}
	}

	public Set<ProyIndicesPre> getProyIndPreSet() {
		return proyIndPreSet;
	}

	public void setProyIndPreSet(Set<ProyIndicesPre> proyIndPreSet) {
		this.proyIndPreSet = proyIndPreSet;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (proyindPk != null ? proyindPk.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof ProyIndices)) {
			return false;
		}
		ProyIndices other = (ProyIndices) object;
		if ((this.proyindPk == null && other.proyindPk != null) || (this.proyindPk != null && !this.proyindPk.equals(other.proyindPk))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sofis.entities.data.ProyIndices[ proyindPk=" + proyindPk + " ]";
	}

	public Integer getProyindFechaActColor() {
		return proyindFechaActColor;
	}

	public void setProyindFechaActColor(Integer proyindFechaActColor) {
		this.proyindFechaActColor = proyindFechaActColor;
	}

	/**
	 * @return the proyindPeriodoInicioEntregable
	 */
	public Entregables getProyindPeriodoInicioEntregable() {
		return proyindPeriodoInicioEntregable;
	}

	/**
	 * @param proyindPeriodoInicioEntregable the proyindPeriodoInicioEntregable
	 * to set
	 */
	public void setProyindPeriodoInicioEntregable(Entregables proyindPeriodoInicioEntregable) {
		this.proyindPeriodoInicioEntregable = proyindPeriodoInicioEntregable;
	}

	/**
	 * @return the proyindPeriodoFinEntregable
	 */
	public Entregables getProyindPeriodoFinEntregable() {
		return proyindPeriodoFinEntregable;
	}

	/**
	 * @param proyindPeriodoFinEntregable the proyindPeriodoFinEntregable to set
	 */
	public void setProyindPeriodoFinEntregable(Entregables proyindPeriodoFinEntregable) {
		this.proyindPeriodoFinEntregable = proyindPeriodoFinEntregable;
	}

}
