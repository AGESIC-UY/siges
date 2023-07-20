package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "entregables")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Entregables.findAll", query = "SELECT e FROM Entregables e"),
    @NamedQuery(name = "Entregables.findByEntPk", query = "SELECT e FROM Entregables e WHERE e.entPk = :entPk"),
    @NamedQuery(name = "Entregables.findByEntId", query = "SELECT e FROM Entregables e WHERE e.entId = :entId"),
    @NamedQuery(name = "Entregables.findByEntNombre", query = "SELECT e FROM Entregables e WHERE e.entNombre = :entNombre"),
    @NamedQuery(name = "Entregables.findByEntCodigo", query = "SELECT e FROM Entregables e WHERE e.entCodigo = :entCodigo"),
    @NamedQuery(name = "Entregables.findByEntNivel", query = "SELECT e FROM Entregables e WHERE e.entNivel = :entNivel"),
    @NamedQuery(name = "Entregables.findByEntStatus", query = "SELECT e FROM Entregables e WHERE e.entStatus = :entStatus"),
    @NamedQuery(name = "Entregables.findByEntInicio", query = "SELECT e FROM Entregables e WHERE e.entInicio = :entInicio"),
    @NamedQuery(name = "Entregables.findByEntDuracion", query = "SELECT e FROM Entregables e WHERE e.entDuracion = :entDuracion"),
    @NamedQuery(name = "Entregables.findByEntFin", query = "SELECT e FROM Entregables e WHERE e.entFin = :entFin"),
    @NamedQuery(name = "Entregables.findByEntInicioEsHito", query = "SELECT e FROM Entregables e WHERE e.entInicioEsHito = :entInicioEsHito"),
    @NamedQuery(name = "Entregables.findByEntFinEsHito", query = "SELECT e FROM Entregables e WHERE e.entFinEsHito = :entFinEsHito"),
    @NamedQuery(name = "Entregables.findByEntCollapsed", query = "SELECT e FROM Entregables e WHERE e.entCollapsed = :entCollapsed"),
    @NamedQuery(name = "Entregables.findByEntAssigs", query = "SELECT e FROM Entregables e WHERE e.entAssigs = :entAssigs"),
    @NamedQuery(name = "Entregables.findByEntEsfuerzo", query = "SELECT e FROM Entregables e WHERE e.entEsfuerzo = :entEsfuerzo"),
    @NamedQuery(name = "Entregables.findByEntInicioLineaBase", query = "SELECT e FROM Entregables e WHERE e.entInicioLineaBase = :entInicioLineaBase"),
    @NamedQuery(name = "Entregables.findByEntDuracionLineaBase", query = "SELECT e FROM Entregables e WHERE e.entDuracionLineaBase = :entDuracionLineaBase"),
    @NamedQuery(name = "Entregables.findByEntFinLineaBase", query = "SELECT e FROM Entregables e WHERE e.entFinLineaBase = :entFinLineaBase"),
    @NamedQuery(name = "Entregables.findByEntDescripcion", query = "SELECT e FROM Entregables e WHERE e.entDescripcion = :entDescripcion"),
    @NamedQuery(name = "Entregables.findByEntProgreso", query = "SELECT e FROM Entregables e WHERE e.entProgreso = :entProgreso"),
    @NamedQuery(name = "Entregables.findEntregablesReferenciandoPorId", query = "SELECT e FROM Entregables e WHERE e.referido.entPk = :idEntregable")
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Entregables implements Serializable {

	public static final int NOMBRE_LENGHT = 1000;
	public static final int DESCRIPCION_LENGHT = 1000;

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Documentos.class.getName());

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ent_pk")
	private Integer entPk;
	@JoinColumn(name = "ent_cro_fk", referencedColumnName = "cro_pk")
	@ManyToOne(fetch = FetchType.EAGER)
	private Cronogramas entCroFk;
	@Basic(optional = false)
	@Column(name = "ent_id")
	private Integer entId;
	@Basic(optional = false)
	@Column(name = "ent_nombre")
	private String entNombre;
	@Column(name = "ent_codigo")
	private String entCodigo;
	@Column(name = "ent_nivel")
	private Integer entNivel;
	//STATUS_ACTIVE, STATUS_DONE, STATUS_FAILED, STATUS_SUSPENDED, STATUS_UNDEFINED
	@Column(name = "ent_status")
	private String entStatus;
	@Column(name = "ent_parent")
	private Boolean entParent;
	@Column(name = "ent_inicio")
	private Long entInicio;
	@Basic(optional = false)
	@Column(name = "ent_duracion")
	private Integer entDuracion;
	@Column(name = "ent_fin")
	private Long entFin;
	@Column(name = "ent_horas_estimadas")
	private String entHorasEstimadas;
	@Column(name = "ent_inicio_es_hito")
	private Boolean entInicioEsHito;
	@Column(name = "ent_fin_es_hito")
	private Boolean entFinEsHito;
	@Column(name = "ent_collapsed")
	private Boolean entCollapsed;
	@Column(name = "ent_assigs")
	private String entAssigs;
	@JoinColumn(name = "ent_coord_usu_fk", referencedColumnName = "usu_id")
	@ManyToOne(optional = true)
	private SsUsuario coordinadorUsuFk;
	@Column(name = "ent_esfuerzo")
	private Integer entEsfuerzo;
	@Column(name = "ent_inicio_linea_base")
	private Long entInicioLineaBase;
	@Column(name = "ent_duracion_linea_base")
	private Integer entDuracionLineaBase;
	@Column(name = "ent_fin_linea_base")
	private Long entFinLineaBase;
	@Column(name = "ent_descripcion")
	private String entDescripcion;
	@Column(name = "ent_progreso")
	private Integer entProgreso;
	@Column(name = "ent_predecesor_fk")
	private String entPredecesorFk;
	/**
	 * Cantidad de días despues del predecesor.
	 */
	@Column(name = "ent_predecesor_dias")
	private Integer entPredecesorDias;

	@Column(name = "ent_relevante")
	private Boolean entRelevante;
        
        @Column(name = "ent_es_referencia")
	private Boolean esReferencia;

	@ManyToOne
	@JoinColumn(name = "ent_referido", referencedColumnName = "ent_pk")
	private Entregables referido;


	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "prodEntregableFk")
	@Fetch(FetchMode.SELECT)
	private Set<Productos> entProductosSet;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "riskEntregable")
//    @Fetch(FetchMode.SELECT)
//    private Set<Riesgos> entRiskSet;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "enthistEntregableFk")
	@Fetch(FetchMode.SELECT)
	private Set<EntHistLineaBase> entHistLBSet;

	//02/11: por bug de Hibernate, esta dando error al guardar una ficha dos veces consecutivas cuando tiene documentos con entregables asociados
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "docsEntregable")
//    @Fetch(FetchMode.SELECT)
//    private Set<Documentos> entDocumentosSet;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "partEntregablesFk")
	@Fetch(FetchMode.SELECT)
	private Set<Participantes> entParticipantesSet;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "rhEntregableFk")
	@Fetch(FetchMode.SELECT)
	private Set<RegistrosHoras> entRegistrosHorasSet;

	@Column(name = "ent_inicio_periodo")
	private Boolean entInicioProyecto = false;
	@Column(name = "ent_fin_periodo")
	private Boolean entFinProyecto = false;

	@Transient
	private String nivelNombreCombo;
	@Transient
	private String fechaNivelNombreCombo;

	@Transient
	private String nivelStr;

	@Transient
	private Double entEsfuerzoPorcentaje;

	public Entregables(Integer entPk) {
		this.entPk = entPk;
	}

	public Entregables(Integer entPk, Integer entId, String entNombre, int entDuracion) {
		this.entPk = entPk;
		this.entId = entId;
		this.entNombre = entNombre;
		this.entDuracion = entDuracion;
	}

	public Integer getEntPk() {
		return entPk;
	}

	public void setEntPk(Integer entPk) {
		this.entPk = entPk;
	}

	public Cronogramas getEntCroFk() {
		return entCroFk;
	}

	public void setEntCroFk(Cronogramas entCroFk) {
		this.entCroFk = entCroFk;
	}

	public Integer getEntId() {
		return entId;
	}

	public void setEntId(Integer entId) {
		this.entId = entId;
	}

	public String getEntNombre() {
		return entNombre;
	}

	public void setEntNombre(String entNombre) {
		this.entNombre = entNombre;
	}

	public String getEntCodigo() {
		return entCodigo;
	}

	public void setEntCodigo(String entCodigo) {
		this.entCodigo = entCodigo;
	}

	public Integer getEntNivel() {
		return entNivel;
	}

	public void setEntNivel(Integer entNivel) {
		this.entNivel = entNivel;
	}

	public String getEntStatus() {
		return entStatus;
	}

	public void setEntStatus(String entStatus) {
		this.entStatus = entStatus;
	}

	public Boolean getEntParent() {
//        return entParent;
		return entParent != null ? entParent : false;
	}

	public void setEntParent(Boolean entParent) {
		this.entParent = entParent;
	}

	public boolean esEntParent() {
		return entParent != null ? entParent : false;
	}

	public Long getEntInicio() {
		return entInicio;
	}

	public Date getEntInicioDate() {
		return entInicio != null && entInicio > 0 ? new Date(entInicio) : null;
	}

	public void setEntInicio(Long entInicio) {
		this.entInicio = entInicio;
	}

	public Integer getEntDuracion() {
		return entDuracion;
	}

	public void setEntDuracion(Integer entDuracion) {
		this.entDuracion = entDuracion;
	}

	public Long getEntFin() {
		return entFin;
	}

	public Date getEntFinDate() {
		return entFin != null && entFin > 0 ? new Date(entFin) : null;
	}

	public void setEntFin(Long entFin) {
		this.entFin = entFin;
	}

	public String getEntHorasEstimadas() {
		return entHorasEstimadas;
	}

	public void setEntHorasEstimadas(String entHorasEstimadas) {
		this.entHorasEstimadas = entHorasEstimadas;
	}

	public Boolean getEntInicioEsHito() {
		return entInicioEsHito != null ? entInicioEsHito : false;
	}

	public void setEntInicioEsHito(Boolean entInicioEsHito) {
		this.entInicioEsHito = entInicioEsHito;
	}

	public Boolean getEntFinEsHito() {
		return entFinEsHito != null ? entFinEsHito : false;
	}

	public void setEntFinEsHito(Boolean entFinEsHito) {
		this.entFinEsHito = entFinEsHito;
	}

	public Boolean getEntCollapsed() {
		return entCollapsed != null ? entCollapsed : false;
	}

	public void setEntCollapsed(Boolean entCollapsed) {
		this.entCollapsed = entCollapsed;
	}

	public String getEntAssigs() {
		return entAssigs;
	}

	public void setEntAssigs(String entAssigs) {
		this.entAssigs = entAssigs;
	}

	public SsUsuario getCoordinadorUsuFk() {
		return coordinadorUsuFk;
	}

	public void setCoordinadorUsuFk(SsUsuario coordinadorUsuFk) {
		this.coordinadorUsuFk = coordinadorUsuFk;
	}

	public Integer getEntEsfuerzo() {
		if (entEsfuerzo != null) {
			return entEsfuerzo;
		}
		return 0;
	}

	public void setEntEsfuerzo(Integer entEsfuerzo) {
		this.entEsfuerzo = entEsfuerzo;
	}

	public Long getEntInicioLineaBase() {
		return entInicioLineaBase;
	}

	public Date getEntInicioLineaBaseDate() {
		return entInicioLineaBase != null && entInicioLineaBase > 0 ? new Date(entInicioLineaBase) : null;
	}

	public void setEntInicioLineaBase(Long entInicioLineaBase) {
		this.entInicioLineaBase = entInicioLineaBase;
	}

	public Integer getEntDuracionLineaBase() {
		return entDuracionLineaBase;
	}

	public void setEntDuracionLineaBase(Integer entDuracionLineaBase) {
		this.entDuracionLineaBase = entDuracionLineaBase;
	}

	public Long getEntFinLineaBase() {
		return entFinLineaBase;
	}

	public Date getEntFinLineaBaseDate() {
		return entFinLineaBase != null && entFinLineaBase > 0 ? new Date(entFinLineaBase) : null;
	}

	public void setEntFinLineaBase(Long entFinLineaBase) {
		this.entFinLineaBase = entFinLineaBase;
	}

	public String getEntDescripcion() {
		return entDescripcion;
	}

	public void setEntDescripcion(String entDescripcion) {
		this.entDescripcion = entDescripcion;
	}

	public Integer getEntProgreso() {
		if (entProgreso != null) {
			return entProgreso;
		}
		return 0;
	}

	public void setEntProgreso(Integer entProgreso) {
		this.entProgreso = entProgreso <= 100 ? entProgreso : 100;
	}

	public String getEntPredecesorFk() {
		return entPredecesorFk;
	}

	public void setEntPredecesorFk(String entPredecesorFk) {
		this.entPredecesorFk = entPredecesorFk;
	}

	public Integer getEntPredecesorDias() {
		return entPredecesorDias;
	}

	public void setEntPredecesorDias(Integer entPredecesorDias) {
		this.entPredecesorDias = entPredecesorDias;
	}

	public Boolean getEntRelevante() {
		return entRelevante != null ? entRelevante : false;
	}

	public void setEntRelevante(Boolean entRelevante) {
		this.entRelevante = entRelevante;
	}

	public Set<Productos> getEntProductosSet() {
		return entProductosSet;
	}

	public void setEntProductosSet(Set<Productos> entProductosSet) {
		this.entProductosSet = entProductosSet;
	}

	public Set<EntHistLineaBase> getEntHistLBSet() {
		return entHistLBSet;
	}

	public void setEntHistLBSet(Set<EntHistLineaBase> entHistLBSet) {
		this.entHistLBSet = entHistLBSet;
	}

	public Set<Participantes> getEntParticipantesSet() {
		return entParticipantesSet;
	}

	public void setEntParticipantesSet(Set<Participantes> entParticipantesSet) {
		this.entParticipantesSet = entParticipantesSet;
	}

	public Set<RegistrosHoras> getEntRegistrosHorasSet() {
		return entRegistrosHorasSet;
	}

	public void setEntRegistrosHorasSet(Set<RegistrosHoras> entRegistrosHorasSet) {
		this.entRegistrosHorasSet = entRegistrosHorasSet;
	}

	public String getNivelStr() {
		return nivelStr;
	}

	public void setNivelStr(String nivelStr) {
		this.nivelStr = nivelStr;
	}

	public String getNivelNombreCombo() {
		return nivelNombreCombo;
	}

	public void setNivelNombreCombo(String nivelNombreCombo) {
		this.nivelNombreCombo = nivelNombreCombo;
	}

	public String getFechaNivelNombreCombo() {
		return fechaNivelNombreCombo;
	}

	public void setFechaNivelNombreCombo(String fechaNivelNombreCombo) {
		this.fechaNivelNombreCombo = fechaNivelNombreCombo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (entPk != null ? entPk.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof Entregables)) {
			return false;
		}
		Entregables other = (Entregables) object;

		if (entPk == null || other.entPk == null) {
			return this == object;
		}

		if ((this.entPk == null && other.entPk == null)
				|| (this.entPk == null && other.entPk != null)
				|| (this.entPk != null && !this.entPk.equals(other.entPk))) {
			return false;
		}
		return true;
	}

	public Double getEntEsfuerzoPorcentaje() {
		return entEsfuerzoPorcentaje;
	}

	public void setEntEsfuerzoPorcentaje(Double entEsfuerzoPorcentaje) {
		this.entEsfuerzoPorcentaje = entEsfuerzoPorcentaje;
	}

	@Override
	public String toString() {
		return entId + " - " + entNombre;
	}

	public void toSystemOut() {
		logger.info("-- Entregable --");
		logger.log(Level.INFO, "Pk:{0}", this.entPk);
		logger.log(Level.INFO, "croFk:{0}", this.entCroFk);
		logger.log(Level.INFO, "nombre:{0}", this.entNombre);
		logger.log(Level.INFO, "código:{0}", this.entCodigo);
		logger.log(Level.INFO, "Desc:{0}", this.entDescripcion);
		logger.log(Level.INFO, "status:{0}", this.entStatus);
		logger.log(Level.INFO, "duración:{0}", this.entDuracion);
	}

	/**
	 * @return the entInicioProyecto
	 */
	public Boolean getEntInicioProyecto() {
		return entInicioProyecto;
	}

	/**
	 * @param entInicioProyecto the entInicioProyecto to set
	 */
	public void setEntInicioProyecto(Boolean entInicioProyecto) {
		this.entInicioProyecto = entInicioProyecto;
	}

	/**
	 * @return the entFinProyecto
	 */
	public Boolean getEntFinProyecto() {
		return entFinProyecto;
	}

	/**
	 * @param entFinProyecto the entFinProyecto to set
	 */
	public void setEntFinProyecto(Boolean entFinProyecto) {
		this.entFinProyecto = entFinProyecto;
	}

        public Boolean getEsReferencia() {
            return esReferencia;
        }

        public void setEsReferencia(Boolean esReferencia) {
            this.esReferencia = esReferencia;
        }

        public Entregables getReferido() {
            return referido;
        }

        public void setReferido(Entregables referido) {
            this.referido = referido;
        }
        
        
        
}
