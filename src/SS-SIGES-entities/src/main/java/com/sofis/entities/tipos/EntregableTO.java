package com.sofis.entities.tipos;

import java.io.Serializable;
import java.util.Date;

public class EntregableTO implements Serializable {

    private Integer id;
    private Integer numero;
    private Integer nivel;
    private Boolean esPadre;

    private String nombre;
    private String descripcion;

    private Date fechaInicio;
    private Date fechaFin;
    private Integer duracion;

    private Integer esfuerzo;
    private Integer progreso;

    private UsuarioTO coordinador;

    private boolean tieneProductos;

    private String dependencias;

    private String status;

    private boolean inicioEsHito;
    private boolean finEsHito;

    private boolean esRelevantePMO;
    private boolean eliminable;

    private Date inicioLineaBase;
    private Date finLineaBase;
    private Integer duracionLineaBase;

    private boolean esInicioProyecto;
    private boolean esFinProyecto;

    private String horasEstimadas;

    private Boolean esReferencia;
    private EntregableTO referido;
    private ProyectoTO proyectoReferido;
    private Boolean tieneVinculacionWekan;
    
    private Boolean esReferenciaDesdeOtroProyecto;


    public EntregableTO() {
    }

    public EntregableTO(Integer id, String nombre) {
            this.id = id;
            this.nombre = nombre;
    }

    public Integer getId() {
            return id;
    }

    public void setId(Integer id) {
            this.id = id;
    }

    public Integer getNumero() {
            return numero;
    }

    public void setNumero(Integer numero) {
            this.numero = numero;
    }

    public Integer getNivel() {
            return nivel;
    }

    public void setNivel(Integer nivel) {
            this.nivel = nivel;
    }

    public Boolean getEsPadre() {
            return esPadre;
    }

    public void setEsPadre(Boolean esPadre) {
            this.esPadre = esPadre;
    }

    public String getNombre() {
            return nombre;
    }

    public void setNombre(String nombre) {
            this.nombre = nombre;
    }

    public String getDescripcion() {
            return descripcion;
    }

    public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
            return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
            this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
            return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
            this.fechaFin = fechaFin;
    }

    public Integer getDuracion() {
            return duracion;
    }

    public void setDuracion(Integer duracion) {
            this.duracion = duracion;
    }

    public Integer getEsfuerzo() {
            return esfuerzo;
    }

    public void setEsfuerzo(Integer esfuerzo) {
            this.esfuerzo = esfuerzo;
    }

    public Integer getProgreso() {
            return progreso;
    }

    public void setProgreso(Integer progreso) {
            this.progreso = progreso;
    }

    public UsuarioTO getCoordinador() {
            return coordinador;
    }

    public void setCoordinador(UsuarioTO coordinador) {
            this.coordinador = coordinador;
    }

    public boolean getTieneProductos() {
            return tieneProductos;
    }

    public void setTieneProductos(boolean tieneProductos) {
            this.tieneProductos = tieneProductos;
    }

    public String getDependencias() {
            return dependencias;
    }

    public void setDependencias(String dependencias) {
            this.dependencias = dependencias;
    }

    public String getStatus() {
            return status;
    }

    public void setStatus(String status) {
            this.status = status;
    }

    public boolean getInicioEsHito() {
            return inicioEsHito;
    }

    public void setInicioEsHito(boolean inicioEsHito) {
            this.inicioEsHito = inicioEsHito;
    }

    public boolean getFinEsHito() {
            return finEsHito;
    }

    public void setFinEsHito(boolean finEsHito) {
            this.finEsHito = finEsHito;
    }

    public boolean getEsRelevantePMO() {
            return esRelevantePMO;
    }

    public void setEsRelevantePMO(boolean esRelevantePMO) {
            this.esRelevantePMO = esRelevantePMO;
    }

    public boolean getEliminable() {
            return eliminable;
    }

    public void setEliminable(boolean eliminable) {
            this.eliminable = eliminable;
    }

    public Date getInicioLineaBase() {
            return inicioLineaBase;
    }

    public void setInicioLineaBase(Date inicioLineaBase) {
            this.inicioLineaBase = inicioLineaBase;
    }

    public Date getFinLineaBase() {
            return finLineaBase;
    }

    public void setFinLineaBase(Date finLineaBase) {
            this.finLineaBase = finLineaBase;
    }

    public Integer getDuracionLineaBase() {
            return duracionLineaBase;
    }

    public void setDuracionLineaBase(Integer duracionLineaBase) {
            this.duracionLineaBase = duracionLineaBase;
    }

    public boolean getEsInicioProyecto() {
            return esInicioProyecto;
    }

    public void setEsInicioProyecto(boolean esInicioProyecto) {
            this.esInicioProyecto = esInicioProyecto;
    }

    public boolean getEsFinProyecto() {
            return esFinProyecto;
    }

    public void setEsFinProyecto(boolean esFinProyecto) {
            this.esFinProyecto = esFinProyecto;
    }

    public String getHorasEstimadas() {
            return horasEstimadas;
    }

    public void setHorasEstimadas(String horasEstimadas) {
            this.horasEstimadas = horasEstimadas;
    }

    public Boolean getEsReferencia() {
            return esReferencia;
    }

    public void setEsReferencia(Boolean esReferencia) {
            this.esReferencia = esReferencia;
    }

    public EntregableTO getReferido() {
            return referido;
    }

    public void setReferido(EntregableTO referido) {
            this.referido = referido;
    }

    public ProyectoTO getProyectoReferido() {
            return proyectoReferido;
    }

    public void setProyectoReferido(ProyectoTO proyectoReferido) {
            this.proyectoReferido = proyectoReferido;
    }

    public Boolean getTieneVinculacionWekan() {
        return tieneVinculacionWekan;
    }

    public void setTieneVinculacionWekan(Boolean tieneVinculacionWekan) {
        this.tieneVinculacionWekan = tieneVinculacionWekan;
    }

    public Boolean getEsReferenciaDesdeOtroProyecto() {
        return esReferenciaDesdeOtroProyecto;
    }

    public void setEsReferenciaDesdeOtroProyecto(Boolean esReferenciaDesdeOtroProyecto) {
        this.esReferenciaDesdeOtroProyecto = esReferenciaDesdeOtroProyecto;
    }
}