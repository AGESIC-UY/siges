/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.ws.gestion.data;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@XmlRootElement(name = "proyecto")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProyectoTO {

	@XmlElement(name = "proyPk", required = false)
	private Integer proyPk;
	@XmlElement(name = "proyNombre", required = true)
	private String proyNombre;
	@XmlElement(name = "proyDescripcion", required = true)
	private String proyDescripcion;
	@XmlElement(name = "proyFactorImpacto", required = false)
	private String proyFactorImpacto;
	@XmlElement(name = "proyObjetivo", required = true)
	private String proyObjetivo;
	@XmlElement(name = "proyObjPublico", required = true)
	private String proyObjPublico;
	@XmlElement(name = "proySituacionActual", required = true)
	private String proySituacionActual;
	@XmlElement(name = "proyAreaFk", required = true)
	private Integer proyAreaFk;
	@XmlElement(name = "proyUsrPmofedFk", required = true)
	private Integer proyUsrPmofedFk;
	@XmlElement(name = "proyUsrGerenteFk", required = true)
	private Integer proyUsrGerenteFk;
	@XmlElement(name = "proyUsrSponsorFk", required = true)
	private Integer proyUsrSponsorFk;
	@XmlElement(name = "proyUsrAdjuntoFk", required = true)
	private Integer proyUsrAdjuntoFk;
	@XmlElement(name = "presupuesto", required = false)
	private PresupuestoTO presupuesto;
	@XmlElement(name = "cronograma", required = false)
	private CronogramaTO cronograma;

	/**
	 * Agregados 05-11-17
	 */
	@XmlElement(name = "semaforoRojo", required = false)
	private Integer semaforoRojo;
	@XmlElement(name = "semaforoAmarillo", required = false)
	private Integer semaforoAmarillo;
	@XmlElement(name = "objetivoEstrategico", required = false)
	private Integer objetivoEstrategico;
	@XmlElement(name = "proyOtrInsEjeFk", required = false)
	private Integer proyOtrInsEjeFk;
	@XmlElement(name = "proyOtrContFk", required = false)
	private Integer proyOtrContFk;
	@XmlElement(name = "proyOtrEtaFk", required = false)
	private Integer proyOtrEtaFk;
	@XmlElement(name = "proyProgFk", required = false)
	private Integer proyProgFk;
	@XmlElement(name = "publicable", required = false)
	private Boolean publicable;
        
        /**
	 * Agregados 10-03-18
	 */
	@XmlElement(name = "proyOtrCatFk", required = false)
	private Integer proyOtrCatFk;
	@XmlElement(name = "latLngProyList", required = false)
	private List<LocalizacionTO> latLngProyList;                

        
        /*
        * Agregados 21-03-18
        */
        @XmlElement(name = "faseDestinoProy", required = false)
        private String faseDestinoProy;

        @XmlElement(name = "replanifProy", required = false)
        private ProyReplanificacionTO replanifProy;
	@XmlElement(name = "attrs")
	private List<AttrTO> attrs;

        public ProyectoTO() {
        }

        
	//<editor-fold defaultstate="collapsed" desc="getters-setters">
	/**
	 * @return the proyPk
	 */
	public Integer getProyPk() {
		return proyPk;
	}

	/**
	 * @param proyPk the proyPk to set
	 */
	public void setProyPk(Integer proyPk) {
		this.proyPk = proyPk;
	}

	/**
	 * @return the proyNombre
	 */
	public String getProyNombre() {
		return proyNombre;
	}

	/**
	 * @param proyNombre the proyNombre to set
	 */
	public void setProyNombre(String proyNombre) {
		this.proyNombre = proyNombre;
	}

	/**
	 * @return the proyDescripcion
	 */
	public String getProyDescripcion() {
		return proyDescripcion;
	}

	/**
	 * @param proyDescripcion the proyDescripcion to set
	 */
	public void setProyDescripcion(String proyDescripcion) {
		this.proyDescripcion = proyDescripcion;
	}

	/**
	 * @return the proyObjetivo
	 */
	public String getProyObjetivo() {
		return proyObjetivo;
	}

	/**
	 * @param proyObjetivo the proyObjetivo to set
	 */
	public void setProyObjetivo(String proyObjetivo) {
		this.proyObjetivo = proyObjetivo;
	}

	/**
	 * @return the proyObjPublico
	 */
	public String getProyObjPublico() {
		return proyObjPublico;
	}

	/**
	 * @param proyObjPublico the proyObjPublico to set
	 */
	public void setProyObjPublico(String proyObjPublico) {
		this.proyObjPublico = proyObjPublico;
	}

	/**
	 * @return the proySituacionActual
	 */
	public String getProySituacionActual() {
		return proySituacionActual;
	}

	/**
	 * @param proySituacionActual the proySituacionActual to set
	 */
	public void setProySituacionActual(String proySituacionActual) {
		this.proySituacionActual = proySituacionActual;
	}

	/**
	 * @return the presupuesto
	 */
	public PresupuestoTO getPresupuesto() {
		return presupuesto;
	}

	/**
	 * @param presupuesto the presupuesto to set
	 */
	public void setPresupuesto(PresupuestoTO presupuesto) {
		this.presupuesto = presupuesto;
	}

	/**
	 * @return the cronograma
	 */
	public CronogramaTO getCronograma() {
		return cronograma;
	}

	/**
	 * @param cronograma the cronograma to set
	 */
	public void setCronograma(CronogramaTO cronograma) {
		this.cronograma = cronograma;
	}

	/**
	 * @return the proyFactorImpacto
	 */
	public String getProyFactorImpacto() {
		return proyFactorImpacto;
	}

	/**
	 * @param proyFactorImpacto the proyFactorImpacto to set
	 */
	public void setProyFactorImpacto(String proyFactorImpacto) {
		this.proyFactorImpacto = proyFactorImpacto;
	}

	/**
	 * @return the attrs
	 */
	public List<AttrTO> getAttrs() {
		return attrs;
	}

	/**
	 * @param attrs the attrs to set
	 */
	public void setAttrs(List<AttrTO> attrs) {
		this.attrs = attrs;
	}

	/**
	 * @return the areaFk
	 */
	public Integer getAreaFk() {
		return proyAreaFk;
	}

	/**
	 * @param areaFk the areaFk to set
	 */
	public void setAreaFk(Integer areaFk) {
		this.proyAreaFk = areaFk;
	}

	/**
	 * @return the proyUsrPmofedFk
	 */
	public Integer getProyUsrPmofedFk() {
		return proyUsrPmofedFk;
	}

	/**
	 * @param proyUsrPmofedFk the proyUsrPmofedFk to set
	 */
	public void setProyUsrPmofedFk(Integer proyUsrPmofedFk) {
		this.proyUsrPmofedFk = proyUsrPmofedFk;
	}

	/**
	 * @return the proyUsrGerenteFk
	 */
	public Integer getProyUsrGerenteFk() {
		return proyUsrGerenteFk;
	}

	/**
	 * @param proyUsrGerenteFk the proyUsrGerenteFk to set
	 */
	public void setProyUsrGerenteFk(Integer proyUsrGerenteFk) {
		this.proyUsrGerenteFk = proyUsrGerenteFk;
	}

	/**
	 * @return the proyUsrSponsorFk
	 */
	public Integer getProyUsrSponsorFk() {
		return proyUsrSponsorFk;
	}

	/**
	 * @param proyUsrSponsorFk the proyUsrSponsorFk to set
	 */
	public void setProyUsrSponsorFk(Integer proyUsrSponsorFk) {
		this.proyUsrSponsorFk = proyUsrSponsorFk;
	}

	/**
	 * @return the proyUsrAdjuntoFk
	 */
	public Integer getProyUsrAdjuntoFk() {
		return proyUsrAdjuntoFk;
	}

	/**
	 * @param proyUsrAdjuntoFk the proyUsrAdjuntoFk to set
	 */
	public void setProyUsrAdjuntoFk(Integer proyUsrAdjuntoFk) {
		this.proyUsrAdjuntoFk = proyUsrAdjuntoFk;
	}

	public Integer getSemaforoRojo() {
		return semaforoRojo;
	}

	public void setSemaforoRojo(Integer semaforoRojo) {
		this.semaforoRojo = semaforoRojo;
	}

	public Integer getSemaforoAmarillo() {
		return semaforoAmarillo;
	}

	public void setSemaforoAmarillo(Integer semaforoAmarillo) {
		this.semaforoAmarillo = semaforoAmarillo;
	}

	public Integer getObjetivoEstrategico() {
		return objetivoEstrategico;
	}

	public void setObjetivoEstrategico(Integer objetivoEstrategico) {
		this.objetivoEstrategico = objetivoEstrategico;
	}

	public Integer getProyOtrInsEjeFk() {
		return proyOtrInsEjeFk;
	}

	public void setProyOtrInsEjeFk(Integer proyOtrInsEjeFk) {
		this.proyOtrInsEjeFk = proyOtrInsEjeFk;
	}

	public Integer getProyOtrContFk() {
		return proyOtrContFk;
	}

	public void setProyOtrContFk(Integer proyOtrContFk) {
		this.proyOtrContFk = proyOtrContFk;
	}

	public Integer getProyOtrEtaFk() {
		return proyOtrEtaFk;
	}

	public void setProyOtrEtaFk(Integer proyOtrEtaFk) {
		this.proyOtrEtaFk = proyOtrEtaFk;
	}

	public Integer getProyProgFk() {
		return proyProgFk;
	}

	public void setProyProgFk(Integer proyProgFk) {
		this.proyProgFk = proyProgFk;
	}

	public Boolean getPublicable() {
		return publicable;
	}

	public void setPublicable(Boolean publicable) {
		this.publicable = publicable;
	}

        public Integer getProyAreaFk() {
            return proyAreaFk;
        }

        public void setProyAreaFk(Integer proyAreaFk) {
            this.proyAreaFk = proyAreaFk;
        }

        public Integer getProyOtrCatFk() {
            return proyOtrCatFk;
        }

        public void setProyOtrCatFk(Integer proyOtrCatFk) {
            this.proyOtrCatFk = proyOtrCatFk;
        }

        public List<LocalizacionTO> getLatLngProyList() {
            return latLngProyList;
        }

        public void setLatLngProyList(List<LocalizacionTO> latLngProyList) {
            this.latLngProyList = latLngProyList;
        }

        public String getFaseDestinoProy() {
            return faseDestinoProy;
        }

        public void setFaseDestinoProy(String faseDestinoProy) {
            this.faseDestinoProy = faseDestinoProy;
        }

        public ProyReplanificacionTO getReplanifProy() {
            return replanifProy;
        }

        public void setReplanifProy(ProyReplanificacionTO replanifProy) {
            this.replanifProy = replanifProy;
        }

        
}
//</editor-fold>
        
        
