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
@XmlRootElement(name = "presupuesto")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class PresupuestoTO {

    @XmlElement (name = "adquisicionSet", required = false)
    private List<AdquisicionTO> adquisicionSet;
    
	@XmlElement (name = "preMoneda")
	private Integer preMoneda;
	
	@XmlElement (name = "preBase")
	private Double preBase;
	
	@XmlElement (name = "fuenteFinanciamiento")
	private Integer fuenteFinanciamiento;
        
        @XmlElement (name = "preOcultarPagosConfirmados")
        private Boolean preOcultarPagosConfirmados;
	
	
    @XmlElement(name="attrs")
    private List<AttrTO> attrs;
    
    //<editor-fold defaultstate="collapsed" desc="getters-setters">
    
    /**
     * @return the adquisicionSet
     */
    public List<AdquisicionTO> getAdquisicionSet() {
        return adquisicionSet;
    }

    /**
     * @param adquisicionSet the adquisicionSet to set
     */
    public void setAdquisicionSet(List<AdquisicionTO> adquisicionSet) {
        this.adquisicionSet = adquisicionSet;
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

	public Integer getPreMoneda() {
		return preMoneda;
	}

	public void setPreMoneda(Integer preMoneda) {
		this.preMoneda = preMoneda;
	}

	public Double getPreBase() {
		return preBase;
	}

	public void setPreBase(Double preBase) {
		this.preBase = preBase;
	}

	public Integer getFuenteFinanciamiento() {
		return fuenteFinanciamiento;
	}

	public void setFuenteFinanciamiento(Integer fuenteFinanciamiento) {
		this.fuenteFinanciamiento = fuenteFinanciamiento;
	}

        public Boolean getPreOcultarPagosConfirmados() {
            return preOcultarPagosConfirmados;
        }

        public void setPreOcultarPagosConfirmados(Boolean preOcultarPagosConfirmados) {
            this.preOcultarPagosConfirmados = preOcultarPagosConfirmados;
        }
    
        
}
//</editor-fold>