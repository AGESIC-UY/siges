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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
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
    @NamedQuery(name = "Entregables.findByEntProgreso", query = "SELECT e FROM Entregables e WHERE e.entProgreso = :entProgreso")})
public class Entregables implements Serializable {

    private static final long serialVersionUID = 1L;
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "prodEntregableFk")
    @Fetch(FetchMode.SELECT)
    private Set<Productos> entProductosSet;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "enthistEntregableFk")
    @Fetch(FetchMode.SELECT)
    private Set<EntHistLineaBase> entHistLBSet;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "docsEntregable")
    @Fetch(FetchMode.SELECT)
    private Set<Documentos> entDocumentosSet;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "partEntregablesFk")
    @Fetch(FetchMode.SELECT)
    private Set<Participantes> entParticipantesSet;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "rhEntregableFk")
    @Fetch(FetchMode.SELECT)
    private Set<RegistrosHoras> entRegistrosHorasSet;

    @Transient
    private Boolean padre;
    @Transient
    private String nivelNombreCombo;
    @Transient
    private String fechaNivelNombreCombo;

    @Transient
    private String nivelStr;

    public Entregables() {
    }

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
        return entRelevante;
    }

    public boolean isEntRelevante() {
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

    public Set<Documentos> getEntDocumentosSet() {
        return entDocumentosSet;
    }

    public void setEntDocumentosSet(Set<Documentos> entDocumentosSet) {
        this.entDocumentosSet = entDocumentosSet;
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

    public Boolean getPadre() {
        return padre;
    }

    public void setPadre(Boolean padre) {
        this.padre = padre;
    }

    public boolean isPadre() {
        return padre != null ? padre : false;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
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

    @Override
    public String toString() {
        return "com.sofis.entities.data.Entregables[ entPk=" + entPk + " ]";
    }

    public void toSystemOut() {
        System.out.println("-- Entregable --");
        System.out.println("Pk:" + this.entPk);
        System.out.println("croFk:" + this.entCroFk);
        System.out.println("nombre:" + this.entNombre);
        System.out.println("codigo:" + this.entCodigo);
        System.out.println("Desc:" + this.entDescripcion);
        System.out.println("status:" + this.entStatus);
        System.out.println("duracion:" + this.entDuracion);
    }
}
