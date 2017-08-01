package com.sofis.entities.data;

import com.sofis.entities.annotations.AtributoUltimaModificacion;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "proyectos")
@XmlRootElement
public class ProyectosConCronograma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proy_pk")
    private Integer proyPk;
    //Id del programa
    @Column(name = "proy_prog_fk")
    private Integer proyProgFk;
    //Id del presupuesto
    @Column(name = "proy_pre_fk")
    private Integer proyPreFk;
    @Column(name = "proy_peso")
    private Integer proyPeso;
    @Column(name = "proy_objetivo")
    private String proyObjetivo;
    @Column(name = "proy_obj_publico")
    private String proyObjPublico;
    @Column(name = "proy_factor_impacto")
    private String proyFactorImpacto;
    @Column(name = "proy_situacion_actual")
    private String proySituacionActual;
    @Column(name = "proy_leccion_aprendida")
    private String proyLeccionAprendida;
    @Column(name = "proy_nombre")
    private String proyNombre;
    @Column(name = "proy_grp")
    private String proyGrp;
    @Column(name = "proy_semaforo_amarillo")
    private Integer proySemaforoAmarillo;
    @Column(name = "proy_semaforo_rojo")
    private Integer proySemaforoRojo;
    @JoinColumn(name = "proy_cro_fk", referencedColumnName = "cro_pk")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Cronogramas proyCroFk;
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
    @Column(name = "proy_ult_mod")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date proyUltMod;
    @Column(name = "proy_ult_origen")
    private String proyUltOrigen;

    public ProyectosConCronograma() {
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

    public String getProyFactorImpacto() {
	return proyFactorImpacto;
    }

    public void setProyFactorImpacto(String proyFactorImpacto) {
	this.proyFactorImpacto = proyFactorImpacto;
    }

    public String getProySituacionActual() {
	return proySituacionActual;
    }

    public void setProySituacionActual(String proySituacionActual) {
	this.proySituacionActual = proySituacionActual;
    }

    public String getProyLeccionAprendida() {
	return proyLeccionAprendida;
    }

    public void setProyLeccionAprendida(String proyLeccionAprendida) {
	this.proyLeccionAprendida = proyLeccionAprendida;
    }

    public String getProyNombre() {
	return proyNombre;
    }

    public void setProyNombre(String proyNombre) {
	this.proyNombre = proyNombre;
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

    public Cronogramas getProyCroFk() {
	return proyCroFk;
    }

    public void setProyCroFk(Cronogramas proyCroFk) {
	this.proyCroFk = proyCroFk;
    }

    public Boolean getActivo() {
	return activo;
    }

    public void setActivo(Boolean activo) {
	this.activo = activo;
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

    public Integer getProyProgFk() {
	return proyProgFk;
    }

    public void setProyProgFk(Integer proyProgFk) {
	this.proyProgFk = proyProgFk;
    }

    public Integer getProyPreFk() {
	return proyPreFk;
    }

    public void setProyPreFk(Integer proyPreFk) {
	this.proyPreFk = proyPreFk;
    }
}
