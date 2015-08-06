package com.sofis.entities.data;

import com.sofis.entities.annotations.AtributoUltimaModificacion;
import com.sofis.entities.annotations.AtributoUltimoUsuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "proyectos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyectos.findAll", query = "SELECT p FROM Proyectos p"),
    @NamedQuery(name = "Proyectos.findByProyPk", query = "SELECT p FROM Proyectos p WHERE p.proyPk = :proyPk"),
    @NamedQuery(name = "Proyectos.findByProyPeso", query = "SELECT p FROM Proyectos p WHERE p.proyPeso = :proyPeso"),
    @NamedQuery(name = "Proyectos.findByProyObjetivo", query = "SELECT p FROM Proyectos p WHERE p.proyObjetivo = :proyObjetivo"),
    @NamedQuery(name = "Proyectos.findByProyObjPublico", query = "SELECT p FROM Proyectos p WHERE p.proyObjPublico = :proyObjPublico"),
    @NamedQuery(name = "Proyectos.findByProySituacionActual", query = "SELECT p FROM Proyectos p WHERE p.proySituacionActual = :proySituacionActual"),
    @NamedQuery(name = "Proyectos.findByProyNombre", query = "SELECT p FROM Proyectos p WHERE p.proyNombre = :proyNombre")})
public class Proyectos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proy_pk")
    private Integer proyPk;
    @Column(name = "proy_peso")
    private Integer proyPeso;
    @Column(name = "proy_descripcion")
    private String proyDescripcion;
    @Column(name = "proy_objetivo")
    private String proyObjetivo;
    /**
     * Se cambia y pasa a ser Beneficio/Beneficiario.
     */
    @Column(name = "proy_obj_publico")
    private String proyObjPublico;
    @Column(name = "proy_situacion_actual")
    private String proySituacionActual;
    @Column(name = "proy_nombre")
    private String proyNombre;
    @Column(name = "proy_grp")
    private String proyGrp;
    @Column(name = "proy_semaforo_amarillo")
    private Integer proySemaforoAmarillo;
    @Column(name = "proy_semaforo_rojo")
    private Integer proySemaforoRojo;

    @JoinTable(name = "proy_tags", joinColumns = {
        @JoinColumn(name = "proytag_proy_pk", referencedColumnName = "proy_pk")}, inverseJoinColumns = {
        @JoinColumn(name = "proytag_area_pk", referencedColumnName = "arastag_pk")})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private Set<AreasTags> areasTematicasSet;

    @JoinTable(name = "proy_lectura_area", joinColumns = {
        @JoinColumn(name = "proglectarea_proy_pk", referencedColumnName = "proy_pk")}, inverseJoinColumns = {
        @JoinColumn(name = "proglectarea_area_pk", referencedColumnName = "area_pk")})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private Set<Areas> areasRestringidasSet;

    @JoinTable(name = "proy_docs", joinColumns = {
        @JoinColumn(name = "proydoc_proy_pk", referencedColumnName = "proy_pk")}, inverseJoinColumns = {
        @JoinColumn(name = "proydoc_doc_pk", referencedColumnName = "docs_pk")})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private Set<Documentos> documentosSet;

    @JoinTable(name = "proy_int", joinColumns = {
        @JoinColumn(name = "proyint_proy_pk", referencedColumnName = "proy_pk")}, inverseJoinColumns = {
        @JoinColumn(name = "proyint_int_pk", referencedColumnName = "int_pk")})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<Interesados> interesadosList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "riskProyFk")
    @Fetch(FetchMode.SELECT)
    private List<Riesgos> riesgosList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "partProyectoFk")
    @Fetch(FetchMode.SELECT)
    private List<Participantes> participantesList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "calProyFk")
    @Fetch(FetchMode.SELECT)
    private List<Calidad> calidadList;

    @JoinColumn(name = "proy_pre_fk", referencedColumnName = "pre_pk")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Presupuesto proyPreFk;

    @JoinColumn(name = "proy_usr_pmofed_fk", referencedColumnName = "usu_id")
    @ManyToOne(optional = true)
    private SsUsuario proyUsrPmofedFk;

    @JoinColumn(name = "proy_usr_gerente_fk", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private SsUsuario proyUsrGerenteFk;

    @JoinColumn(name = "proy_usr_sponsor_fk", referencedColumnName = "usu_id")
    @ManyToOne(optional = true)
    private SsUsuario proyUsrSponsorFk;

    @JoinColumn(name = "proy_usr_adjunto_fk", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private SsUsuario proyUsrAdjuntoFk;

    @JoinColumn(name = "proy_prog_fk", referencedColumnName = "prog_pk")
    @ManyToOne(optional = true)
    private Programas proyProgFk;

    @JoinColumn(name = "proy_area_fk", referencedColumnName = "area_pk")
    @ManyToOne(optional = false)
    private Areas proyAreaFk;

    @JoinColumn(name = "proy_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos proyOrgFk;

    @JoinColumn(name = "proy_est_fk", referencedColumnName = "est_pk")
    @ManyToOne(optional = false)
    private Estados proyEstFk;

    @JoinColumn(name = "proy_est_pendiente_fk", referencedColumnName = "est_pk")
    @ManyToOne(optional = true)
    private Estados proyEstPendienteFk;

    @JoinColumn(name = "proy_cro_fk", referencedColumnName = "cro_pk")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Cronogramas proyCroFk;

    @JoinColumn(name = "proy_proyindices_fk", referencedColumnName = "proyind_pk")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private ProyIndices proyIndices;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "proyectoFk")
    @Fetch(FetchMode.SELECT)
    private Set<ProySitactHistorico> sitactHistoricos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "proyectoFk", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private Set<ProyReplanificacion> replanificaciones;

    @JoinColumn(name = "proy_otr_dat_fk", referencedColumnName = "proy_otr_pk")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ProyOtrosDatos proyOtrosDatos;

    @JoinColumn(name = "proy_latlng_fk", referencedColumnName = "latlng_pk")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private LatlngProyectos proyLatlngFk;

    /**
     * Marca al Proyecto como publicable para el Visualizador.
     */
    @Column(name = "proy_publicable")
    private Boolean proyPublicable;

    /**
     * Indica si el Programa estÃ¡ activo o eliminado logicamente.
     */
    @Column(name = "proy_activo")
    private Boolean activo;
    @Column(name = "proy_fecha_crea")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date proyFechaCrea;
    @Column(name = "proy_fecha_act")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date proyFechaAct;
    @Column(name = "proy_fecha_est_act")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date proyFechaEstadoAct;
    /**
     * Atributo para mantener una copia del original de este mismo Proyecto.
     * Utilizado al momento de comparar cambios al persistir.
     */
    @Transient
    private Proyectos proyectoOriginal;
    //Audit
    @Column(name = "proy_ult_usuario")
    @AtributoUltimoUsuario
    private String proyUltUsuario;
    @Column(name = "proy_ult_mod")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date proyUltMod;
    @Column(name = "proy_ult_origen")
    private String proyUltOrigen;
    @Column(name = "proy_version")
    // @Version
    private Integer proyVersion;

    public Proyectos() {
    }

    public Proyectos(Integer proyPk, String proyNombre) {
        this.proyPk = proyPk;
        this.proyNombre = proyNombre;
    }

    public Proyectos(Integer proyPk) {
        this.proyPk = proyPk;
    }

    public Integer getProyPk() {
        return proyPk;
    }

    public void setProyPk(Integer proyPk) {
        this.proyPk = proyPk;
    }

    public Integer getProyPeso() {
        return proyPeso;
    }

    public void setProyPeso(Integer proyPeso) {
        this.proyPeso = proyPeso;
    }

    public String getProyDescripcion() {
        return proyDescripcion;
    }

    public void setProyDescripcion(String proyDescripcion) {
        this.proyDescripcion = proyDescripcion;
    }

    public String getProyObjetivo() {
        return proyObjetivo;
    }

    public void setProyObjetivo(String proyObjetivo) {
        this.proyObjetivo = proyObjetivo;
    }

    public String getProyObjPublico() {
        return proyObjPublico;
    }

    public void setProyObjPublico(String proyObjPublico) {
        this.proyObjPublico = proyObjPublico;
    }

    public String getProySituacionActual() {
        return proySituacionActual;
    }

    public void setProySituacionActual(String proySituacionActual) {
        this.proySituacionActual = proySituacionActual;
    }

    public String getProyNombre() {
        return proyNombre;
    }

    public void setProyNombre(String proyNombre) {
        this.proyNombre = proyNombre;
    }

    public String getProyPkNombre() {
        return proyPk != null && proyPk > 0 ? proyPk.toString().concat(" - ").concat(proyNombre) : proyNombre;
    }

    public String getProyGrp() {
        return proyGrp;
    }

    public void setProyGrp(String proyGrp) {
        this.proyGrp = proyGrp;
    }

    public Integer getProySemaforoAmarillo() {
        return proySemaforoAmarillo;
    }

    public void setProySemaforoAmarillo(Integer proySemaforoAmarillo) {
        this.proySemaforoAmarillo = proySemaforoAmarillo;
    }

    public Integer getProySemaforoRojo() {
        return proySemaforoRojo;
    }

    public void setProySemaforoRojo(Integer proySemaforoRojo) {
        this.proySemaforoRojo = proySemaforoRojo;
    }

    public Set<AreasTags> getAreasTematicasSet() {
        return areasTematicasSet;
    }

    public void setAreasTematicasSet(Set<AreasTags> areasTematicasSet) {
        this.areasTematicasSet = areasTematicasSet;
    }

    public Set<Areas> getAreasRestringidasSet() {
        return areasRestringidasSet;
    }

    public void setAreasRestringidasSet(Set<Areas> areasRestringidasSet) {
        this.areasRestringidasSet = areasRestringidasSet;
    }

    public List<Interesados> getInteresadosList() {
        return interesadosList;
    }

    public void setInteresadosList(List<Interesados> interesadosList) {
        this.interesadosList = interesadosList;
    }

    public List<Riesgos> getRiesgosList() {
        return riesgosList;
    }

    public void setRiesgosList(List<Riesgos> riesgosList) {
        this.riesgosList = riesgosList;
    }

    public List<Participantes> getParticipantesList() {
        return participantesList;
    }

    public void setParticipantesList(List<Participantes> participantesList) {
        this.participantesList = participantesList;
    }

    public List<Calidad> getCalidadList() {
        return calidadList;
    }

    public void setCalidadList(List<Calidad> calidadList) {
        this.calidadList = calidadList;
    }

    public Presupuesto getProyPreFk() {
        return proyPreFk;
    }

    public void setProyPreFk(Presupuesto proyPreFk) {
        this.proyPreFk = proyPreFk;
    }

    public Set<Documentos> getDocumentosSet() {
        return documentosSet;
    }

    public void setDocumentosSet(Set<Documentos> documentosSet) {
        this.documentosSet = documentosSet;
    }

    public SsUsuario getProyUsrPmofedFk() {
        return proyUsrPmofedFk;
    }

    public void setProyUsrPmofedFk(SsUsuario proyUsrPmofedFk) {
        this.proyUsrPmofedFk = proyUsrPmofedFk;
    }

    public SsUsuario getProyUsrGerenteFk() {
        return proyUsrGerenteFk;
    }

    public void setProyUsrGerenteFk(SsUsuario proyUsrGerenteFk) {
        this.proyUsrGerenteFk = proyUsrGerenteFk;
    }

    public SsUsuario getProyUsrSponsorFk() {
        return proyUsrSponsorFk;
    }

    public void setProyUsrSponsorFk(SsUsuario proyUsrSponsorFk) {
        this.proyUsrSponsorFk = proyUsrSponsorFk;
    }

    public SsUsuario getProyUsrAdjuntoFk() {
        return proyUsrAdjuntoFk;
    }

    public void setProyUsrAdjuntoFk(SsUsuario proyUsrAdjuntoFk) {
        this.proyUsrAdjuntoFk = proyUsrAdjuntoFk;
    }

    public Programas getProyProgFk() {
        return proyProgFk;
    }

    public void setProyProgFk(Programas proyProgFk) {
        this.proyProgFk = proyProgFk;
    }

    public Areas getProyAreaFk() {
        return proyAreaFk;
    }

    public void setProyAreaFk(Areas proyAreaFk) {
        this.proyAreaFk = proyAreaFk;
    }

    public Organismos getProyOrgFk() {
        return proyOrgFk;
    }

    public void setProyOrgFk(Organismos proyOrgFk) {
        this.proyOrgFk = proyOrgFk;
    }

    public Estados getProyEstFk() {
        return proyEstFk;
    }

    public void setProyEstFk(Estados proyEstFk) {
        this.proyEstFk = proyEstFk;
    }

    public Estados getProyEstPendienteFk() {
        return proyEstPendienteFk;
    }

    public void setProyEstPendienteFk(Estados proyEstPendienteFk) {
        this.proyEstPendienteFk = proyEstPendienteFk;
    }

    public boolean isEstPendienteFk() {
        return this.proyEstPendienteFk != null ? true : false;
    }

    public Cronogramas getProyCroFk() {
        return proyCroFk;
    }

    public void setProyCroFk(Cronogramas proyCroFk) {
        this.proyCroFk = proyCroFk;
    }

    public ProyIndices getProyIndices() {
        return proyIndices;
    }

    public void setProyIndices(ProyIndices proyIndices) {
        this.proyIndices = proyIndices;
    }

    public Set<ProySitactHistorico> getSitactHistoricos() {
        return sitactHistoricos;
    }

    public void setSitactHistoricos(Set<ProySitactHistorico> sitactHistoricos) {
        this.sitactHistoricos = sitactHistoricos;
    }

    public Set<ProyReplanificacion> getReplanificaciones() {
        return replanificaciones;
    }

    public void setReplanificaciones(Set<ProyReplanificacion> replanificaciones) {
        this.replanificaciones = replanificaciones;
    }

    public ProyOtrosDatos getProyOtrosDatos() {
        return proyOtrosDatos;
    }

    public void setProyOtrosDatos(ProyOtrosDatos proyOtrosDatos) {
        this.proyOtrosDatos = proyOtrosDatos;
    }

    public LatlngProyectos getProyLatlngFk() {
        return proyLatlngFk;
    }

    public void setProyLatlngFk(LatlngProyectos proyLatlngFk) {
        this.proyLatlngFk = proyLatlngFk;
    }

    public Boolean getProyPublicable() {
        return proyPublicable;
    }

    public boolean isProyPublicable() {
        return proyPublicable != null ? proyPublicable : false;
    }

    public void setProyPublicable(Boolean proyPublicable) {
        this.proyPublicable = proyPublicable;
    }

    public Boolean getActivo() {
        return activo;
    }

    public Date getProyFechaCrea() {
        return proyFechaCrea;
    }

    public void setProyFechaCrea(Date proyFechaCrea) {
        this.proyFechaCrea = proyFechaCrea;
    }

    public Date getProyFechaAct() {
        return proyFechaAct;
    }

    public void setProyFechaAct(Date proyFechaAct) {
        this.proyFechaAct = proyFechaAct;
    }

    public Date getProyFechaEstadoAct() {
        return proyFechaEstadoAct;
    }

    public void setProyFechaEstadoAct(Date proyFechaEstadoAct) {
        this.proyFechaEstadoAct = proyFechaEstadoAct;
    }

    public Proyectos getProyectoOriginal() {
        return proyectoOriginal;
    }

    public void setProyectoOriginal(Proyectos proyectoOoriginal) {
        this.proyectoOriginal = proyectoOoriginal;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getProyUltUsuario() {
        return proyUltUsuario;
    }

    public void setProyUltUsuario(String proyUltUsuario) {
        this.proyUltUsuario = proyUltUsuario;
    }

    public Date getProyUltMod() {
        return proyUltMod;
    }

    public void setProyUltMod(Date proyUltMod) {
        this.proyUltMod = proyUltMod;
    }

    public String getProyUltOrigen() {
        return proyUltOrigen;
    }

    public void setProyUltOrigen(String proyUltOrigen) {
        this.proyUltOrigen = proyUltOrigen;
    }

    public Integer getProyVersion() {
        return proyVersion;
    }

    public void setProyVersion(Integer proyVersion) {
        this.proyVersion = proyVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proyPk != null ? proyPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Proyectos)) {
            return false;
        }
        Proyectos other = (Proyectos) object;
        if ((this.proyPk == null && other.proyPk != null) || (this.proyPk != null && !this.proyPk.equals(other.proyPk))) {
            return false;
        }
        return true;
    }

    /**
     * Compara el estado aportado con el del programa.
     *
     * @param e
     * @return true si es el estado aportado.
     */
    public boolean isEstado(Integer estPk) {
        return this.getProyEstFk().isEstado(estPk);
    }

    /**
     * Indica si el programa esta en la fase de pendiente. Ej.: PENDIENTE,
     * PENDIENTE_PMOT, PENDIENTE_PMOF.
     *
     * @return true si el estado es uno de los estados pendiente.
     */
    public boolean isEstadoPendientes() {
        return this.getProyEstFk().isPendientes();
    }

    public boolean isActivo() {
        return activo != null ? activo : false;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Proyectos[ proyPk=" + proyPk + " ]";
    }

    @Override
    public Object clone() {
        Proyectos proy = new Proyectos();
        proy.setProyAreaFk(proyAreaFk);
        proy.setProyEstFk(proyEstFk);
        proy.setProyEstPendienteFk(proyEstPendienteFk);
        proy.setProyFechaAct(proyFechaAct);
        proy.setProyFechaCrea(proyFechaCrea);
        proy.setProyFechaEstadoAct(proyFechaEstadoAct);
        proy.setProyGrp(proyGrp);
        proy.setProyNombre(proyNombre);
        proy.setProyDescripcion(proyDescripcion);
        proy.setProyObjetivo(proyObjetivo);
        proy.setProyObjPublico(proyObjPublico);
        proy.setProyOrgFk(proyOrgFk);
        proy.setProyPeso(proyPeso);
        proy.setProyPk(proyPk);
        proy.setProyProgFk(proyProgFk);
        proy.setProySemaforoAmarillo(proySemaforoAmarillo);
        proy.setProySemaforoRojo(proySemaforoRojo);
        proy.setProySituacionActual(proySituacionActual);
        proy.setProyUltMod(proyUltMod);
        proy.setProyUltOrigen(proyUltOrigen);
        proy.setProyUltUsuario(proyUltUsuario);
        proy.setProyUsrAdjuntoFk(proyUsrAdjuntoFk);
        proy.setProyUsrGerenteFk(proyUsrGerenteFk);
        proy.setProyUsrPmofedFk(proyUsrPmofedFk);
        proy.setProyUsrSponsorFk(proyUsrSponsorFk);
        proy.setProyVersion(proyVersion);
        proy.setProyectoOriginal(proyectoOriginal);
        proy.setActivo(activo);
        proy.setProyPublicable(proyPublicable);
        proy.setAreasRestringidasSet(areasRestringidasSet);
        proy.setAreasTematicasSet(areasTematicasSet);
        proy.setDocumentosSet(documentosSet);
        proy.setProyIndices(proyIndices);
        proy.setInteresadosList(interesadosList);

        proy.setProyCroFk(proyCroFk);
        proy.setProyPreFk(proyPreFk);

        return proy;
    }

    public void toSystemOut() {
        System.out.println("-- Proyectos --");
        System.out.println("Pk:" + this.proyPk != null ? this.proyPk : "");
        System.out.println("Nombre:" + this.proyNombre != null ? this.proyNombre : "");
        System.out.println("Estado:" + this.proyEstFk != null ? this.proyEstFk : "");
    }
}
