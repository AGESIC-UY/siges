package com.sofis.entities.tipos;

import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.ProgIndices;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.ProyIndices;
import com.sofis.entities.enums.TipoFichaEnum;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ItemInicioTO implements Serializable {

	private Integer fichaFk;
    private ProgIndices indicesProg;
    private ProyIndices indicesProy;
    private Integer tipoFicha;
    private String nombre;
    private String area;
    private String areaAbreviacion;
    private Integer areaId;
    private String responsable;
    private Integer responsableId;
    private String adjunto;
    private Integer adjuntoId;
    private Integer sponsorId;
    private Integer pmofId;
    private Estados estado;
    private Estados estadoPendiente;
    private Integer semaforoAmarillo;
    private Integer semaforoRojo;
    private Date fechaAct;
    private Date fechaEstadoAct;
    private Boolean solCambioFase;
    private Integer peso;
    private Boolean activo;
    private Programas programa;
    private String situacionActual;

    //Fase
    private String faseColor;
	
    //Actualizacion
    private Date actualizacion;
    private String actualizacionColor;
	
    //Periodo
    private Date periodoDesde;
    private Date periodoHasta;
	
    //Riesgo
    private String riesgoExposicionColor;
    private Double riesgoExposicion;
    private Integer riesgosAltosCantidad;
    private Integer riesgosVencidosCantidad;
    private String riesgosActualizacionColor;
	
    //Calidad
    private Double calidadIndice;
    private String calidadIndiceColor;
    private Integer calidadPendientes;
	
    //Metodologia
    private Double metodologiaIndice;
    private String metodologiaIndiceColor;
    private Boolean metodologiaSinAprobar;
	
    //Cronograma-Avance
    private int[] indiceAvanceFinalizado;
    private int[] indiceAvanceParcial;
	
    //Presupuesto
    private List<Moneda> indiceMonedas;
    private HashMap<Integer, String> indiceMonedaColor;
    private Presupuesto presupuesto;
    private ResultadoInicioTO inicioResultado;

    private ItemInicioTO progPadre;

    public ResultadoInicioTO getInicioResultado() {
        return inicioResultado;
    }

    public void setInicioResultado(ResultadoInicioTO inicioResultado) {
        this.inicioResultado = inicioResultado;
    }

    public Integer getFichaFk() {
        return fichaFk;
    }

    public void setFichaFk(Integer fichaFk) {
        this.fichaFk = fichaFk;
    }

    public Integer getTipoFicha() {
        return tipoFicha;
    }

    public void setTipoFicha(Integer tipoFicha) {
        this.tipoFicha = tipoFicha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaAbreviacion() {
        return areaAbreviacion;
    }

    public void setAreaAbreviacion(String areaAbreviacion) {
        this.areaAbreviacion = areaAbreviacion;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Integer getResponsableId() {
        return responsableId;
    }

    public void setResponsableId(Integer responsableId) {
        this.responsableId = responsableId;
    }

    public String getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }

    public Integer getAdjuntoId() {
        return adjuntoId;
    }

    public void setAdjuntoId(Integer adjuntoId) {
        this.adjuntoId = adjuntoId;
    }

    public Integer getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Integer sponsorId) {
        this.sponsorId = sponsorId;
    }

    public Integer getPmofId() {
        return pmofId;
    }

    public void setPmofId(Integer pmofId) {
        this.pmofId = pmofId;
    }

    public String getFase() {
        if (this.isPrograma() && this.estado.isPendientes()) {
            return "Pendiente";
        }
        return estado != null ? estado.getEstNombre() : "";
    }

    public Integer getFaseId() {
        return estado != null ? estado.getEstPk() : null;
    }

    public Date getActualizacion() {
        return actualizacion;
    }

    public void setActualizacion(Date actualizacion) {
        this.actualizacion = actualizacion;
    }

    public String getActualizacionColor() {
        return actualizacionColor;
    }

    public void setActualizacionColor(String actualizacionColor) {
        this.actualizacionColor = actualizacionColor;
    }

    public Date getPeriodoDesde() {
        return periodoDesde;
    }

    public void setPeriodoDesde(Date periodoDesde) {
        this.periodoDesde = periodoDesde;
    }

    public Date getPeriodoHasta() {
        return periodoHasta;
    }

    public void setPeriodoHasta(Date periodoHasta) {
        this.periodoHasta = periodoHasta;
    }

    public String getRiesgoExposicionColor() {
        return riesgoExposicionColor;
    }

    public void setRiesgoExposicionColor(String riesgoExposicionColor) {
        this.riesgoExposicionColor = riesgoExposicionColor;
    }

    public Double getRiesgoExposicion() {
        return riesgoExposicion;
    }

    public void setRiesgoExposicion(Double riesgoExposicion) {
        this.riesgoExposicion = riesgoExposicion;
    }

    public Integer getRiesgosAltosCantidad() {
        return riesgosAltosCantidad;
    }

    public void setRiesgosAltosCantidad(Integer riesgosAltosCantidad) {
        this.riesgosAltosCantidad = riesgosAltosCantidad;
    }

    public Integer getRiesgosVencidosCantidad() {
        return riesgosVencidosCantidad;
    }

    public void setRiesgosVencidosCantidad(Integer riesgosVencidosCantidad) {
        this.riesgosVencidosCantidad = riesgosVencidosCantidad;
    }

    public String getRiesgosActualizacionColor() {
        return riesgosActualizacionColor;
    }

    public void setRiesgosActualizacionColor(String riesgosActualizacionColor) {
        this.riesgosActualizacionColor = riesgosActualizacionColor;
    }

    public Double getCalidadIndice() {
        return calidadIndice;
    }

    public void setCalidadIndice(Double calidadIndice) {
        this.calidadIndice = calidadIndice;
    }

    public String getCalidadIndiceColor() {
        return calidadIndiceColor;
    }

    public void setCalidadIndiceColor(String calidadIndiceColor) {
        this.calidadIndiceColor = calidadIndiceColor;
    }

    public Integer getCalidadPendientes() {
        return calidadPendientes;
    }

    public void setCalidadPendientes(Integer calidadPendientes) {
        this.calidadPendientes = calidadPendientes;
    }

    public Double getMetodologiaIndice() {
        return metodologiaIndice;
    }

    public void setMetodologiaIndice(Double metodologiaIndice) {
        this.metodologiaIndice = metodologiaIndice;
    }

    public String getMetodologiaIndiceColor() {
        return metodologiaIndiceColor;
    }

    public void setMetodologiaIndiceColor(String metodologiaIndiceColor) {
        this.metodologiaIndiceColor = metodologiaIndiceColor;
    }

    public Boolean getMetodologiaSinAprobar() {
        return metodologiaSinAprobar;
    }

    public void setMetodologiaSinAprobar(Boolean metodologiaSinAprobar) {
        this.metodologiaSinAprobar = metodologiaSinAprobar;
    }

    public String getProgProyId() {
        return tipoFicha + "-" + fichaFk;
    }

    public int[] getIndiceAvanceFinalizado() {
        return indiceAvanceFinalizado;
    }

    public void setIndiceAvanceFinalizado(int[] indiceAvanceFinalizado) {
        this.indiceAvanceFinalizado = indiceAvanceFinalizado;
    }

    public boolean getHasIndiceAvanceFinalizado() {
        return indiceAvanceFinalizado != null && !(indiceAvanceFinalizado[0] == 0 && indiceAvanceFinalizado[1] == 0 && indiceAvanceFinalizado[2] == 0);
    }

    public int[] getIndiceAvanceParcial() {
        return indiceAvanceParcial;
    }

    public void setIndiceAvanceParcial(int[] indiceAvanceParcial) {
        this.indiceAvanceParcial = indiceAvanceParcial;
    }

    public boolean getHasIndiceAvanceParcial() {
        return indiceAvanceParcial != null && !(indiceAvanceParcial[0] == 0 && indiceAvanceParcial[1] == 0 && indiceAvanceParcial[2] == 0);
    }

    public List<Moneda> getIndiceMonedas() {
        return indiceMonedas;
    }

    public void setIndiceMonedas(List<Moneda> indiceMonedas) {
        this.indiceMonedas = indiceMonedas;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public HashMap<Integer, String> getIndiceMonedaColor() {
        return indiceMonedaColor;
    }

    public void setIndiceMonedaColor(HashMap<Integer, String> indiceMonedaColor) {
        this.indiceMonedaColor = indiceMonedaColor;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public Estados getEstadoPendiente() {
        return estadoPendiente;
    }

    public void setEstadoPendiente(Estados estadoPendiente) {
        this.estadoPendiente = estadoPendiente;
    }

    public Integer getSemaforoAmarillo() {
        return semaforoAmarillo;
    }

    public void setSemaforoAmarillo(Integer semaforoAmarillo) {
        this.semaforoAmarillo = semaforoAmarillo;
    }

    public Integer getSemaforoRojo() {
        return semaforoRojo;
    }

    public void setSemaforoRojo(Integer semaforoRojo) {
        this.semaforoRojo = semaforoRojo;
    }

    public Date getFechaAct() {
        return fechaAct;
    }

    public void setFechaAct(Date fechaAct) {
        this.fechaAct = fechaAct;
    }

    public Date getFechaEstadoAct() {
        return fechaEstadoAct;
    }

    public void setFechaEstadoAct(Date fechaEstadoAct) {
        this.fechaEstadoAct = fechaEstadoAct;
    }

    public String getFaseColor() {
        return faseColor;
    }

    public void setFaseColor(String faseColor) {
        this.faseColor = faseColor;
    }

    public Boolean getSolCambioFase() {
        return solCambioFase;
    }

    public void setSolCambioFase(Boolean solCambioFase) {
        this.solCambioFase = solCambioFase;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Boolean getActivo() {
        return activo;
    }

    public boolean isActivo() {
        return activo != null ? activo : false;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Programas getPrograma() {
        return programa;
    }

    public void setPrograma(Programas programa) {
        this.programa = programa;
    }

    public String getSituacionActual() {
        return situacionActual;
    }

    public void setSituacionActual(String situacionActual) {
        this.situacionActual = situacionActual;
    }

    public ProgIndices getIndicesProg() {
        return indicesProg;
    }

    public void setIndicesProg(ProgIndices indicesProg) {
        this.indicesProg = indicesProg;
    }

    public ProyIndices getIndicesProy() {
        return indicesProy;
    }

    public void setIndicesProy(ProyIndices indicesProy) {
        this.indicesProy = indicesProy;
    }

    public boolean isPrograma() {
        return this.tipoFicha.equals(TipoFichaEnum.PROGRAMA.id);
    }

    public boolean isProyecto() {
        return this.tipoFicha.equals(TipoFichaEnum.PROYECTO.id);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ItemInicioTO)) {
            return false;
        }
        ItemInicioTO other = (ItemInicioTO) object;
        if ((this.fichaFk == null && this.tipoFicha == null && other.fichaFk == null && other.tipoFicha == null)
                || (this.fichaFk != null && !this.fichaFk.equals(other.fichaFk)
                && this.tipoFicha != null && this.tipoFicha.equals(other.tipoFicha))) {
            return false;
        }
        return true;
    }

    /*
    *   11-06-2018 Nico: Getters y Setters para el nuevo atributo
     */
    public ItemInicioTO getProgPadre() {
        return progPadre;
    }

    public void setProgPadre(ItemInicioTO progPadre) {
        this.progPadre = progPadre;
    }

}
